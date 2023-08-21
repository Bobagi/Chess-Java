/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import View.Tabuleiro;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Perin
 */
public class Relogio extends Thread {
    private int segundos;
    private int minutos;
    private final String tipo;
    DecimalFormat formato;
    private final JLabel labelTempo;
    private final ModelTabuleiro model;
    private boolean waiting=false;
    private Tabuleiro view;
    private TabuleiroController controller;
    public Relogio(ModelTabuleiro model, JLabel labelTempo, String tipo,Tabuleiro view,TabuleiroController controller){ 
        this.labelTempo = labelTempo;
        this.view = view;
        this.tipo = tipo;
        this.model = model;
        int auxTempo;
            if("global".equals(tipo))auxTempo = model.getGlobalTime();
            else auxTempo = model.getTurnTime();
        this.minutos = auxTempo/60;
        this.segundos = auxTempo%60;
    }
    
    public void setGlobalTime(){
        model.setGlobalTime(minutos,segundos);
    }
    
    public void setWhiteTime(){
        model.setWhiteTime(minutos,segundos);
    }
    
    public void setBlackTime(){
        model.setBlackTime(minutos,segundos);     
    }
    @Override
    public void run(){
        
        
        try {
            if("global".equals(tipo)){
            while(minutos<100){ // Tempo máximo de jogo é de 100 minutos
            labelTempo.setText(formatar(minutos)+":"+formatar(segundos));
            segundos+=1;
            if(segundos>=60){
                segundos=0;
                minutos+=1;
            }
            Thread.sleep(1000); // A thread é posta para dormir por 1 segundo
            }
            }else{
            while(minutos>=0 && segundos>=0){
                try{
                if(minutos==0 && segundos == 0)throw new AcaoInvalida("Tempo",view);
                }catch(AcaoInvalida e){
                System.out.println("Tempo Esgotado");
     
                controller.ChangeTurn(model.getTurn()); // Se o tempo se esgotar, o turno será passado para o outro jogador
                }
                synchronized(this){ // Thread pausada, aguardando notify
                 while(waiting){
                 try {
                     // Enquanto está no modo espera, o tempo é atualizado com o model, para que não exista divergência
                        int auxTempo = model.getTurnTime();
                        this.minutos = auxTempo/60;
                        this.segundos = auxTempo%60;
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Relogio.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                }
            labelTempo.setText(formatar(minutos)+":"+formatar(segundos));
            segundos-=1;
            if(segundos<0){
                segundos=59;
                minutos-=1;
            }
            Thread.sleep(1000);
            }    
            
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Relogio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String formatar(int num){
        formato = new DecimalFormat("00"); // Fprmata a saida para ter 2 dígitos
        return formato.format(num);
    }
    
    public int getSegundos(){
        return segundos;
    }
   
    public int getMinutos(){
        return minutos;
    }
    
    public void setWaiting(boolean value){
        waiting = value;
    }
    
    public void updateTime(){
        // Atualiza o horário dos 3 relógios com os dados do Model, utilizado quando o jogo é carregado
        int auxTempo;
            if("global".equals(tipo)){
                auxTempo = model.getGlobalTime();
                this.minutos = auxTempo/60;
                this.segundos = auxTempo%60;
            }else if("brancas".equals(tipo)){
                auxTempo = model.getWhiteTime();
                this.minutos = auxTempo/60;
                this.segundos = auxTempo%60;
            }else{
                auxTempo = model.getBlackTime();
                this.minutos = auxTempo/60;
                this.segundos = auxTempo%60;
            }
            labelTempo.setText(formatar(minutos)+":"+formatar(segundos));
        
    }
    
}

