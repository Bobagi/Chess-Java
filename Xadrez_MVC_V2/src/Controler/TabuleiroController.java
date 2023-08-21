/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import Model.Peca;
import View.Tabuleiro;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


/**
 *
 * @author felipelageduarte
 * 
 */
public class TabuleiroController implements  MouseListener, MouseMotionListener{

  private Tabuleiro view;
  private ModelTabuleiro model;
  private Peca mov = null;
  private Relogio relogioGlobal;
  private Relogio relogioBrancas;
  private Relogio relogioNegras;
    public void addView (Tabuleiro view){
        this.view = view;
    }
    
    public void addModel (ModelTabuleiro model){
        this.model = model;
    }
    
    public void updateClocks(){ // Dá o comando para que todos os relógios se atualizem com os dados do Model
        relogioGlobal.updateTime();
        relogioBrancas.updateTime();
        relogioNegras.updateTime();
    }
    
    public void setTime(){ // Define o novo horário de todos os relógios
        relogioGlobal.setGlobalTime();
        relogioBrancas.setWhiteTime();
        relogioNegras.setBlackTime();
    }
    /*
      USe este metodo para iniciar o seu VIEW... neste caso, antes de motra-lo
    na tela, o posicionamos no centro dela....
    */
    public void runTabuleiro() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - view.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - view.getHeight()) / 2);
        view.setLocation(x, y);
        
        view.setVisible(true);
        view.getPromotionTab().setVisible(false); // Desabilita a tela de seleção de promoção
        view.getVictoryWindow().setVisible(false); // Desabilita a tela de vitória
        view.getErrorWindow().setVisible(false); // Desabilita a janela de erro
        relogioGlobal = new Relogio(model,view.getGlobalTime(),"global",view,this); // Cria os objetos dos relógios
        relogioBrancas = new Relogio(model,view.getTurnTime(),"brancas",view,this);
        relogioNegras = new Relogio(model,view.getTurnTime(),"negras",view,this);
        
        // Relógios -----------------------
        relogioGlobal.start(); // Inicializa as threads que controlam a contagem
        relogioBrancas.start();
        relogioNegras.start();
        if(model.getTurn()==0){ // Se o valor do turno for 0, então é o turno das brancas
            view.getTurnLabel().setText("Brancas");
            synchronized (relogioNegras) {
                relogioNegras.setWaiting(true); // Coloca a thread que controla a contagem das negras em modo de aguardo
            }
        }
        else { // Se o valor do turno for 1, então é o turno das negras
            view.getTurnLabel().setText("Pretas");
            synchronized (relogioBrancas) {
                relogioBrancas.setWaiting(true); // Coloca a thread que controla a contagem das brancas em modo de aguardo
            }
        }
   
        //----------------------------------
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
       view.repaint();
       int x = e.getX();//pega as coordenadas do mouse
       int y = e.getY();
       view.getClickLabel().setText("x:"+x+"  y:"+y+"   -   Quadrante: ["+x/60+","+y/60+"]");
       verificaCasa(x/60,y/60);
        
    }
   
    private void verificaCasa(int Qx, int Qy){ // Verifica se existe alguma peça posicionada no quadrante
            Peca peca = model.findPeca(Qx, Qy);   
            if(peca != null){              
                view.getPecaLabel().setText(peca.toString());
            }else{
                view.getPecaLabel().setText("Casa Vazia");
            }    
            view.getMovLabel().setText("");
        if(mov==null && peca!=null){ // Se nenhuma peça estiver selecionada...
            int turno = model.getTurn();
            // Controle de turnos
            if((peca.getCor() == 0 && turno==0) || (peca.getCor() == 1 && turno==1)){
                mov = peca;               
                view.getMovLabel().setText("Mover "+mov.toString());
                view.SetLegalMoves(mov.getMovements(model),peca); // Chama a função que vai pintar os movimentos legais                
            }
             
        }else if(mov!=null && CanMove(Qx,Qy,mov)){ 
            view.SetLegalMoves(null,peca); // Apaga as casas que estavam pintadas
            if(peca != null){
                if(mov.getCor() == 0){ // Dá um ponto sempre que uma peça é retirada de jogo
                    ArrayList<Peca> pecas = model.getPecasPretas();
                    if(peca.LoseCondition)EndGame(0);// Se a peça removida for um rei, fim de jogo
                    pecas.remove(peca);
                    model.setScore(1,0);
                    view.getScoreLabelBrancas().setText(""+model.getScoreBrancas());
                }else{
                    ArrayList<Peca> pecas = model.getPecasBrancas();
                    if(peca.LoseCondition)EndGame(1);// Se a peça removida for um rei, fim de jogo
                    pecas.remove(peca);
                    model.setScore(0,1);
                    view.getScoreLabelPretas().setText(""+model.getScorePretas());
                }
                
                
            }
            if(mov.getFirst()) mov.setFirstFalse(); // Se era o primeiro movimento da peça, não é mais
            mov.setQuadrante(Qx, Qy);  
            if((mov.getCor()==0 && Qy==0) || (mov.getCor()==1 && Qy==7)){ // Promove o peão que atingir o outro lado do tabuleiro
                Promotion(mov);                
            }
                 
            mov = null;
            view.getMovLabel().setText("Movido");
            
            ChangeTurn(model.getTurn());
        }else{ 
            try{
            view.SetLegalMoves(null,peca); // Apaga as casas que estavam pintadas
            mov = null;
            view.getMovLabel().setText("Movimento Ilegal");
            throw new AcaoInvalida("Movimento",view);
            }catch(AcaoInvalida e){
                System.out.println("Movimento Inválido");
            }
        }
        
    }
    
    public void EndGame(int i){ // Recebe como parametro 0 ou 1(Brancas ou Pretas)
        view.getVictoryWindow().setVisible(true); // Habilita a tela de vitória
        view.getJPCanvas().setVisible(false); // Desabilita o tabuleiro
        view.getJPpanel().setVisible(false); // Desabilita a tavela de pontuação
        if(i==0)view.getVictoryLabel().setText("Brancas");
        else view.getVictoryLabel().setText("Pretas");
    }
    
   
    
    public void Promotion(Peca peca){ // Promove um peão à rainha
        //view.getPromotionTab().setVisible(true);
            Point p = peca.getPoint();
            ArrayList<Peca> pecas;
            if(peca.getCor()==1){  
                pecas = model.getPecasPretas();
                pecas.remove(peca);  
                model.AddQueen(p, 1);     
            }else{  
                pecas = model.getPecasBrancas();
                pecas.remove(peca);  
                model.AddQueen(p, 0);     
            }
               
        
        
    }
    public boolean CanMove(int x, int y, Peca peca){ // Função que retorna true caso o movimento seja legal
        Point aux = new Point(x,y);
        Point[] legals = model.GetActualLegalMove();
        int i=0;
        
        for(Point p : peca.getMovements(model)){
            if(p!=null){
            if(p.equals(aux) && legals[i]!=null){ // Pode mover                
             model.SetActualLegalMove(null); // Limpa os dados de movimentos legais da peça movida
             return true;
            }
            }
            i++;
        }
        return false;
    }
    
    public void ChangeTurn(int turn){
        if(turn==0){ // Turno da brancas
            model.setTurnTime(relogioBrancas.getMinutos(),relogioBrancas.getSegundos());
            synchronized (relogioBrancas) {
                relogioBrancas.setWaiting(true);
            }
            model.setTurn(1);
            view.getTurnLabel().setText("Pretas");
            synchronized (relogioNegras) {
                relogioNegras.setWaiting(false);
                relogioNegras.notify();
            }
        }else{ // Turno das pretas
            model.setTurnTime(relogioNegras.getMinutos(),relogioNegras.getSegundos());
            synchronized (relogioNegras) {
                relogioNegras.setWaiting(true);
            }
            model.setTurn(0);
            view.getTurnLabel().setText("Brancas");
            synchronized (relogioBrancas) {
                relogioBrancas.setWaiting(false);
                relogioBrancas.notify();
            }
        }
    }
   
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        view.getCoordenadaLabel().setText("x:"+x+"  y:"+y+"   -   Quadrante: ["+x/60+","+y/60+"]");
        view.getMouseCoord().setLocation(x, y);
        //view.repaint();
    }

}
