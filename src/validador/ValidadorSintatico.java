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
public class ValidadorSintatico {
    
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
            
            if(c == '('){
                pilhaParenteses.push(c);
            }else if(c == ')'){
                if( pilhaParenteses.vazia()){
                    throw new Exception("Erro Sintático: Parêntese de fechamento ')' sem abertura correspondente.");
                }
                pilhaParenteses.pop();
            }
            
        }
        
        if(!pilhaParenteses.vazia()){
            throw new Exception("Erro Sintático: Parêntese de abertura '(' não foi fechado.");
        }
        
        char primeiro = expLimpa.charAt(0); char ultimo = expLimpa.charAt(expLimpa.length() - 1); 
        
        if (isOperador(primeiro)) { 
            throw new Exception("Erro Sintático: A expressão não pode começar com um operador '" + primeiro + "'."); 
        } 
        
        if (isOperador(ultimo)) { 
            throw new Exception("Erro Sintático: A expressão não pode terminar com um operador '" + ultimo + "'."); 
        }
        
        for(int i = 0; i<expLimpa.length()-1; i++){
            char atual = expLimpa.charAt(i);
            char prox = expLimpa.charAt(i + 1);
            
            if (isOperador(atual) && isOperador(prox)) { 
                throw new Exception("Erro Sintático: Dois operadores consecutivos encontrados: '" + atual + prox + "'."); 
            }
            
            if (atual == '(' && isOperador(prox)) { 
                throw new Exception("Erro Sintático: Operador '" + prox + "' logo após abertura de parêntese."); 
            }
            
            if (isOperador(atual) && prox == ')') { 
                throw new Exception("Erro Sintático: Operador '" + atual + "' logo antes de fechamento de parêntese.");
            }
            
            if (atual == '(' && prox == ')') {
                throw new Exception("Erro Sintático: Parênteses vazios '()' não são permitidos.");
            }
            
            if (Character.isDigit(atual) && Character.isDigit(prox)) { 
                throw new Exception("Erro Sintático: Dois operandos adjacentes '" + atual + prox + "' sem operador entre eles.");
            }
            
            if (Character.isDigit(atual) && prox == '(') { 
                throw new Exception("Erro Sintático: Falta operador entre o operando '" + atual + "' e '('.");
            }
            
            if (atual == ')' && Character.isDigit(prox)) { 
                throw new Exception("Erro Sintático: Falta operador entre ')' e o operando '" + prox + "'.");
            }

        }
        
    }
    
    private static boolean isCaractereValido(char c) {
        return Character.isDigit(c) || isOperador(c) || c == '(' || c == ')';
    }
    
    
    public static boolean isOperador(char c) { 
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'; 
    }
    
}
