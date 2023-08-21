/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import View.Tabuleiro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Perin
 */
public class Carregar extends Thread{
    
    public Carregar(ModelTabuleiro model, TabuleiroController controller,Tabuleiro view){
        try {
            FileInputStream in = new FileInputStream(new File("Save"));
            if(in==null)throw new AcaoInvalida("Carregar",view); // Caso o arquivo não exista, um erro aparecerá
            ObjectInputStream obj = new ObjectInputStream(in);
            ModelTabuleiro newModel = (ModelTabuleiro) obj.readObject();
            model.loadGame(newModel,controller);
            obj.close();
            in.close();
        } catch (FileNotFoundException ex) {            
            System.out.println("Arquivo de carregamento não existe!");
        } catch (IOException ex) {
            System.out.println("Erro");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AcaoInvalida ex) {
            System.out.println("Arquivo de carregamento não existe!");
        }
    }       
       
}
