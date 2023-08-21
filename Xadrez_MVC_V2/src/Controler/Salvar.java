/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import View.Tabuleiro;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Perin
 */
public class Salvar extends Thread{
 
    public Salvar(ModelTabuleiro model, TabuleiroController controller, Tabuleiro view) {
        controller.setTime(); // Salva o tempo do relógio global no model
        try {
            FileOutputStream out = new FileOutputStream(new File("Save")); // Cria o arquivo
            if(out==null) throw new AcaoInvalida("Save",view); // Se o arquivo falhar em ser criado, uma janela de erro aparecerá
            ObjectOutputStream modelSave = new ObjectOutputStream(out); 
            
            modelSave.writeObject(model); // Escreve o objeto no arquivo

            modelSave.close();
            out.close();     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salvar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Salvar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AcaoInvalida ex) {
            System.out.println("Arquivo de save não criado");
        } 
        
        
    }
    
}
