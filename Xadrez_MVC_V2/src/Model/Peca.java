/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jbatista
 */
public abstract class Peca implements Serializable{
    
    protected final static String imgPath = "img/pecas.png";
    protected static BufferedImage pecasImg = null;    
    protected Cor cor;
    protected Point quadrante;
    protected boolean firstMovement = true,pawn = false, perpetual = false;
    public boolean LoseCondition=false;
    public Point[] movements = null; // Posição de todos os movimentos possíveis
    protected Point[] mod = null, modP = null; // Moodificador do movimento de cada peça
    public enum Cor{
        PRETO,
        BRANCO
    }
    
    public Peca(Cor cor, int x, int y)  {       
        this.cor = cor;
        this.quadrante = new Point(x,y);
        if(pecasImg == null){
            try {
                pecasImg = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Peca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean inSquare(int x, int y){
        return x == quadrante.x && y == quadrante.y;
    }
    
    public void setQuadrante(int x, int y){
        quadrante.setLocation(x, y);
        int i = 0;
        if(movements!=null){
            for(Point p : movements){ // Faz Update nos movimentos possíveis
            if(p!=null)p.setLocation(x+mod[i].x,y+mod[i].y);
            i++;
            }
        }
    }
    public Point[] getMovements(ModelTabuleiro model){ // Atualiza movimentos extras
        Point[] auxmovements;
        if(pawn){ // Se for um peão
            auxmovements = movements;
            if(!firstMovement){ // e for seu primeiro movimento                               
            movements = new Point[3];
            movements[0] = auxmovements[0];
            movements[1] = auxmovements[1];
            movements[2] = auxmovements[2];
            auxmovements = movements;
            }
            
            int h;
            if(this.cor.equals(Peca.Cor.BRANCO)){
                h=-1;
                
            }else{
                h=1;
                
            }
            // Testa a peça na posição de ataque a direita
            Peca peca = model.findPeca(quadrante.x+1, quadrante.y+h);
            if(peca==null){
                auxmovements[1] = null;
            }else{
                auxmovements[1] = new Point(peca.quadrante);
            }
            // Testa a peça na posição de ataque a esquerda
            peca = model.findPeca(quadrante.x-1, quadrante.y+h);
            if(peca==null){
                auxmovements[2] = null;
            }else{
                auxmovements[2] = new Point(peca.quadrante);
            }
            // Testa a peça que bloqueia o caminho
            peca = model.findPeca(quadrante.x, quadrante.y+h);
            if(peca==null){
                auxmovements[0] = new Point(quadrante.x, quadrante.y+h);
            }else{
                auxmovements[0] = null;
            }
            
            
            return auxmovements;
            
        }else if(perpetual){ // Se for uma peça de movimento perpétuo (Dama,torre,bispo)
            ArrayList<Point> aux = new ArrayList<Point>(); // cria uma lsita para guardar os movimentos possíveis
            int x,y,i=0; // já que esse número não é fixo e depende do posicionamento da peça no tabuleiro
            
            for(Point p : modP){
                x = this.quadrante.x; // x e y recebem as coordenadas dos quadrantes da posição atual da peca
                y = this.quadrante.y; // e então recebem os valores dos modificadores de movimentos perpétuos 
                x += p.x;   // representando a primeira posição de movimento de cada modificador
                y += p.y;
                while((x>-1 && x<8) && (y>-1 && y<8)){
                    Peca peca = model.findPeca(x,y); // Peca encontrada no caminho
                    if(peca!=null && peca.getCor()== this.getCor()){ // Se a peca for da mesma cor
                        break;
                    }else if(peca!=null && peca.getCor()!= this.getCor()){ // Se a peca não for da mesma cor
                        aux.add(new Point(x,y));  // ela pode capturá-la, porém não pode andar pra as posições anterioes a essa peça
                        break;
                    }
                    aux.add(new Point(x,y));                               
                    x += p.x;
                    y += p.y;
                }                
            }
            
            auxmovements = new Point[aux.size()];
            for(Point p : aux){ // Passa as coordenadas dos movimentos da lista para o vetor de movimentos da peça
                auxmovements[i]=aux.get(i);
                i++;
            }
            return auxmovements;
        }else{ // Se for qualquer outra peça, se move normalmente com a Array de movimento
            return movements;
        }
        
          
    }
    
    public int getCor(){
        if(cor == Peca.Cor.PRETO){
            return 1; // Retorna 1 se a peça for preta
        }else{
            return 0; // Retorna 0 se a peça for branca
        }
    }
    
    public boolean getFirst(){
        return firstMovement;
    }
    public void setFirstFalse(){
        firstMovement = false;
    }
    
    public boolean IsPawn(){
        return pawn;
    }
    
    public abstract void draw(Graphics2D g);
    
    public Point getPoint(){
        return quadrante;
    }   
    
}
