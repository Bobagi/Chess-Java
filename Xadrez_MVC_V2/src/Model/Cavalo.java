/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Peca.pecasImg;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author jbatista
 */
public class Cavalo extends Peca {

    public Cavalo(Peca.Cor cor, int x, int y) {
        super(cor, x, y);
        this.movements = new Point[8];
        this.mod = new Point[8];
            mod[0] = new Point(-1,2);
            mod[1] = new Point(1,2);
            mod[2] = new Point(2,1);
            mod[3] = new Point(2,-1);
            mod[4] = new Point(1,-2);
            mod[5] = new Point(-1,-2);
            mod[6] = new Point(-2,1);
            mod[7] = new Point(-2,-1);
          
                                   
        movements[0] = new Point(x+mod[0].x,y+mod[0].y);
        movements[1] = new Point(x+mod[1].x,y+mod[1].y);
        movements[2] = new Point(x+mod[2].x,y+mod[2].y);
        movements[3] = new Point(x+mod[3].x,y+mod[3].y);
        movements[4] = new Point(x+mod[4].x,y+mod[4].y);
        movements[5] = new Point(x+mod[5].x,y+mod[5].y);
        movements[6] = new Point(x+mod[6].x,y+mod[6].y);
        movements[7] = new Point(x+mod[7].x,y+mod[7].y);
        
        
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 260, 20, 300, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 260, 70, 300, 110, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Cavalo preto";
        } else {
            return "Cavalo branco";
        }
    }
    
   
}
