/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validador;
import estruturas.Pilha;

/**
 *
 * @author gabriel
 */
public class ValidadorSIntatico {
    
    public static void validar(String expressao) throws Exception{
        if (expressao == null || expressao.trim().isEmpty()){
            throw new Exception("Erro sintatico: A expressção fornecida está vazia");
        }
        
        String expLimpa = expressao.replaceAll("\\s+","");
        
        Pilha<Character> pilhaParenteses = new Pilha<Character>();
        
        for(int i = 0; i< expLimpa.length(); i++){
            char c = expLimpa.charAt(i);
            
            if(!isCaractereValido(c)){
                throw new Exception("Erro Sintático: Caractere inválido detectado '" + c + "'.");
            }
            
            
        }
    }
    
}
