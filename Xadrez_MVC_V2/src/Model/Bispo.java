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
public class Bispo extends Peca {

    public Bispo(Peca.Cor cor, int x, int y) {
        super(cor, x, y);  
        this.modP = new Point[4];
        this.perpetual = true;
            
            
            //modificadores de movimento perpétuo
                        
            modP[0] = new Point(1,1);
            modP[1] = new Point(1,-1);
            modP[2] = new Point(-1,1);
            modP[3] = new Point(-1,-1);
                                                          
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 200, 20, 240, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 200, 70, 240, 110, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Bispo preto";
        } else {
            return "Bispo branco";
        }
    }
    
   
}
