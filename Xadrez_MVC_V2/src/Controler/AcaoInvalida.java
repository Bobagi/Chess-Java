/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import View.Tabuleiro;

/**
 *
 * @author Perin
 */
public class AcaoInvalida extends Exception{ // Exception para qualquer ação inválidade realizada prlo usuário
    private final String acao;
    private Tabuleiro view;
    AcaoInvalida(String acao, Tabuleiro view){
        super();
        this.view = view;
        this.acao = acao; // Define que tipo de exceção que foi invocada, determinada pela ação
        callException();
    }
    
    private void callException(){ // Altera o a mensagem da janela de erro para cada tipo de exceção
        if(acao.equals("Movimento")){
            view.getErrorWindow().setVisible(true);
            view.getErrorLabel().setText("Movimento Ilegal");
        }else if(acao.equals("Tempo")){
            view.getErrorWindow().setVisible(true);
            view.getErrorLabel().setText("Tempo Esgotado");
        }else if(acao.equals("Save")){
            view.getErrorWindow().setVisible(true);
            view.getErrorLabel().setText("Erro ao salvar");
        }else if(acao.equals("Carregar")){
            view.getErrorWindow().setVisible(true);
            view.getErrorLabel().setText("Erro durante recuperação");
        }
    }
    
}
