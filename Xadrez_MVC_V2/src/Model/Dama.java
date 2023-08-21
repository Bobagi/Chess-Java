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
public class Dama extends Peca {

    public Dama(Peca.Cor cor, int x, int y) {
        super(cor, x, y);  
        this.modP = new Point[8];
        this.perpetual = true;
            
            
            //modificadores de movimento perpétuo
            
            modP[0] = new Point(1,0);
            modP[1] = new Point(-1,0);
            modP[2] = new Point(0,1);
            modP[3] = new Point(0,-1);
            modP[4] = new Point(1,1);
            modP[5] = new Point(1,-1);
            modP[6] = new Point(-1,1);
            modP[7] = new Point(-1,-1);
                                                          
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 80, 20, 120, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 80, 70, 120, 110, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Dama preta";
        } else {
            return "Dama branca";
        }
    }
    
   
}
