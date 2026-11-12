package avaliador;

import estruturas.Pilha;

public class ConversorPosFixa {
    
    public static String converter(String infixa) throws Exception {
        Pilha<Character> pilha = new Pilha<>();
        StringBuilder saida = new StringBuilder();
        infixa = infixa.replaceAll("\\s+", "");

        for (char c : infixa.toCharArray()) {
            // Se for número (operando), vai direto para a saída
            if (Character.isDigit(c)) {
                saida.append(c);
            } 
            // Abre parêntese vai para a pilha
            else if (c == '(') {
                pilha.push(c);
            } 
            // Fecha parêntese desempilha até encontrar a abertura correspondente
            else if (c == ')') {
                while (!pilha.vazia() && pilha.topo() != '(') {
                    saida.append(pilha.pop());
                }
                if (!pilha.vazia() && pilha.topo() == '(') {
                    pilha.pop(); // Remove o '(' da pilha e descarta os parênteses
                }
            } 
            // Operadores
            else if (ValidadorSintatico.isOperador(c)) {
                while (!pilha.vazia() && pilha.topo() != '(') {
                    char topo = pilha.topo();
                    int precC = getPrecedencia(c);
                    int precTopo = getPrecedencia(topo);
                    
                    // Exponenciação (^) é associativa à direita, os demais à esquerda
                    if (precTopo > precC || (precTopo == precC && c != '^')) {
                        saida.append(pilha.pop());
                    } else {
                        break;
                    }
                }
                pilha.push(c);
            }
        }
        
        // Esvazia os operadores restantes na pilha jogando para a saída
        while (!pilha.vazia()) {
            saida.append(pilha.pop());
        }
        
        return saida.toString();
    }

    private static int getPrecedencia(char op) {
        if (op == '^') return 3;
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0; // Para sentinela '('
    }
}
