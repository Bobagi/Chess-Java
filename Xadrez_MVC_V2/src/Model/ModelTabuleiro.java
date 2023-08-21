/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controler.TabuleiroController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jbatista
 * ESTA CLASSE contem o MODELO DE DADOS da sua aplicação !!!!!
 * COloque nela APENAS os dados: acesso a um banco de dados, queries de SQL, suas pecas de xadrez, 
 * etc..
 */
public class ModelTabuleiro implements Serializable, Observer{
    private ArrayList<Peca> pecasPretas;
    private ArrayList<Peca> pecasBrancas;
    private Point[] legal;
    private int scoreBrancas = 0,scorePretas=0,turno = 0;
    private int minutoGlobal = 0;
    private int segundoGlobal = 0;
    private int minutoBrancas=1,segundoBrancas=5;
    private int minutoNegras=1,segundoNegras=5;
    public ModelTabuleiro()  {
        this.pecasPretas = new ArrayList<Peca>();
        this.pecasBrancas  = new ArrayList<Peca>();
        init();
    }
    
    public void setGlobalTime(int minutos, int segundos){
        segundoGlobal = segundos;
        minutoGlobal = minutos;
    }
    
    public void setWhiteTime(int minutos, int segundos){
        segundoBrancas = segundos;
        minutoBrancas = minutos;
    }
    
    public void setBlackTime(int minutos, int segundos){
        segundoNegras = segundos;
        minutoNegras = minutos;
    }
    
    public boolean loadGame(ModelTabuleiro save, TabuleiroController controller){
        // Carrega cada item contido no arquivo em disco
        // dessa forma, é possível que o jogo seja salvo e recarregado em uma mesma sessão,
        // sem ter de fechar a aplicação para faze-lo
        this.legal = save.legal;
        this.pecasPretas = save.pecasPretas;
        this.pecasBrancas = save.pecasBrancas;
        this.minutoGlobal = save.minutoGlobal;
        this.segundoGlobal = save.segundoGlobal;
        this.minutoBrancas = save.minutoBrancas;
        this.segundoBrancas = save.segundoBrancas;
        this.minutoNegras = save.minutoNegras;
        this.segundoNegras = save.segundoNegras;
        this.scoreBrancas = save.scoreBrancas;
        this.scorePretas = save.scorePretas;
        this.turno = save.turno;
        controller.updateClocks(); // Atualiza o horário dos relógios
        return true;
    }
    
    public int getGlobalTime(){
        return segundoGlobal+(minutoGlobal*60);
    }
    
    public int getTurnTime(){
        if(turno==0)return segundoBrancas+(minutoBrancas*60);
        else return segundoNegras+(minutoNegras*60); 
    }
    
    public int getWhiteTime(){
        return segundoBrancas+(minutoBrancas*60);
    }
    
    public int getBlackTime(){
        return segundoNegras+(minutoNegras*60); 
    }
    
    public void setTurnTime(int minutos, int segundos){ // Guarda o tempo restante pelo jogador que passou o turno, e acrescenta em 5 segundos o seu tempo.
        if(turno==0){
            segundoBrancas = segundos+5;
            if(segundoBrancas>59){
                segundoBrancas-=60;
                minutoBrancas = minutos+1;
            }else{
                minutoBrancas = minutos;
            }
            
            
        }else{
            segundoNegras = segundos+5;
            if(segundoNegras>59){
                segundoNegras-=60;
                minutoNegras = minutos+1;
            }else{
                minutoNegras = minutos;
            }
                 
        }
    }
   
    private void init() {
        
        //inicializa time branco
        for(int i=0;i<8;i++)pecasBrancas.add(new Peao(Peca.Cor.BRANCO,i,6)); // Peões
        pecasBrancas.add(new Rei(Peca.Cor.BRANCO,4,7));
        pecasBrancas.add(new Dama(Peca.Cor.BRANCO,3,7));
        pecasBrancas.add(new Cavalo(Peca.Cor.BRANCO,1,7));
        pecasBrancas.add(new Cavalo(Peca.Cor.BRANCO,6,7));
        pecasBrancas.add(new Bispo(Peca.Cor.BRANCO,5,7));
        pecasBrancas.add(new Bispo(Peca.Cor.BRANCO,2,7));
        pecasBrancas.add(new Torre(Peca.Cor.BRANCO,0,7));
        pecasBrancas.add(new Torre(Peca.Cor.BRANCO,7,7));
 
        //inicializa time preto
        for(int i=0;i<8;i++)pecasPretas.add(new Peao(Peca.Cor.PRETO,i,1)); // Peões
        pecasPretas.add(new Rei(Peca.Cor.PRETO,4,0));
        pecasPretas.add(new Dama(Peca.Cor.PRETO,3,0));
        pecasPretas.add(new Cavalo(Peca.Cor.PRETO,1,0));
        pecasPretas.add(new Cavalo(Peca.Cor.PRETO,6,0));
        pecasPretas.add(new Bispo(Peca.Cor.PRETO,5,0));
        pecasPretas.add(new Bispo(Peca.Cor.PRETO,2,0));
        pecasPretas.add(new Torre(Peca.Cor.PRETO,0,0));
        pecasPretas.add(new Torre(Peca.Cor.PRETO,7,0));
        
        
    }
    
    public ArrayList<Peca> getPecasBrancas(){
        return pecasBrancas;
    }
    
    public ArrayList<Peca> getPecasPretas(){
        return pecasPretas;
    }
    
    public Peca findPeca(int x, int y) {
        Peca peca = null;
        
        //desenha pecas Brancas
        for(Peca p : pecasBrancas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        //desenha pecas pretas
        for(Peca p : pecasPretas){
            if(p.inSquare(x,y)){
                return p;
            }
        }
        
        return peca;
    }
    
    public void AddQueen(Point p,int cor){
        if(cor==1){
            pecasPretas.add(new Dama(Peca.Cor.PRETO,p.x,p.y));
        }else{
            pecasBrancas.add(new Dama(Peca.Cor.BRANCO,p.x,p.y));
        }
        
    }
    public void draw(Graphics2D g){
        //desenha pecas Brancas
        for(Peca p : pecasBrancas){
            p.draw(g);
        }
        
        //desenha pecas pretas
        for(Peca p : pecasPretas){
            p.draw(g);
        }
    }
    
    public void SetActualLegalMove(Point[] x){ // Define os movimentos legais da peça selecionada atualmente
        legal = x;
    }
    
    public Point[] GetActualLegalMove(){
        return legal;
    }
    
    public int getScoreBrancas(){
        return scoreBrancas;
    }
    
    public int getScorePretas(){
        return scorePretas;
    }
    
    public void setScore(int x, int y){
        scoreBrancas += x;
        scorePretas += y;
    }
    
    public int getTurn(){
        return turno;
    }
    
    public void setTurn(int turn){
        turno=turn;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }
    
}
