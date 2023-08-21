/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author jbatista
 */
public class Peao extends Peca{

    public Peao(Cor cor, int x, int y)  {
        super(cor, x, y);
        this.movements = new Point[4];
        this.mod = new Point[4];
        this.pawn = true;
        if(this.cor == Peca.Cor.PRETO){ // Atribui os modificadore de movimento
          mod[0] = new Point(0,1);  
          mod[1] = new Point(1,1);
          mod[2] = new Point(-1,1);
          mod[3] = new Point(0,2);
        }else{
          mod[0] = new Point(0,-1);
          mod[1] = new Point(1,-1);
          mod[2] = new Point(-1,-1);
          mod[3] = new Point(0,-2);  
        }
        
        movements[0] = new Point(x+mod[0].x,y+mod[0].y);// Movimento simples para frente
        movements[1] = new Point(x+mod[1].x,y+mod[1].y);// Ataque a direita
        movements[2] = new Point(x+mod[2].x,y+mod[2].y);// Ataque a esquerda
        movements[3] = new Point(x+mod[3].x,y+mod[3].y);// Avanço duplo do primeiro movimento
    }

    @Override
    public void draw(Graphics2D g) {
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        int x0 = quadrante.x * squareWidth;
        int y0 = quadrante.y * squareHeight;
        int x1 = x0 + squareWidth;
        int y1 = y0 + squareHeight;
        
        if(this.cor == Peca.Cor.PRETO){
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 20, 360, 60, null);            
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 72, 360, 112, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Peão breto";
        } else {
            return "Peão branco";
        }
    }
    
    
    
}
