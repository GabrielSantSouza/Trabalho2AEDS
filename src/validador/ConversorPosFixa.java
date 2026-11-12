package validador;

import estruturas.Pilha;

public class ConversorPosFixa {
    
    public static String converter(String expressaoInfixa) throws Exception {
        if (expressaoInfixa == null) {
            throw new IllegalArgumentException("A expressão de entrada não pode ser nula.");
        }

        StringBuilder saida = new StringBuilder();
        Pilha<Character> pilhaOperadores = new Pilha<Character>();

        // Remove espaços em branco para percorrer caractere a caractere de forma uniforme
        String expLimpa = expressaoInfixa.replaceAll("\\s+", "");

        for (int i = 0; i < expLimpa.length(); i++) {
            char c = expLimpa.charAt(i);

            // 1. Se for dígito numérico, vai direto para a saída pós-fixa
            if (Character.isDigit(c)) {
                saida.append(c).append(" ");
            } 
            // 2. Parêntese de abertura '(' é sempre empilhado
            else if (c == '(') {
                pilhaOperadores.push(c);
            } 
            // 3. Parêntese de fechamento ')': desempilha até encontrar o '(' correspondente
            else if (c == ')') {
                while (!pilhaOperadores.vazia() && pilhaOperadores.topo() != '(') {
                    saida.append(pilhaOperadores.pop()).append(" ");
                }
                // Descarta o '(' que estava no topo da pilha
                if (!pilhaOperadores.vazia()) {
                    pilhaOperadores.pop();
                }
            } 
            // 4. Se for operador (+, -, *, /, ^)
            else if (ValidadorSintatico.isOperador(c)) {
                /*
                 * Desempilha enquanto:
                 * - A pilha não estiver vazia;
                 * - O topo da pilha não for '(';
                 * - O topo possuir precedência MAIOR que o operador atual, OU
                 * - O topo possuir precedência IGUAL e for associativo à ESQUERDA (todos exceto '^').
                 */
                while (!pilhaOperadores.vazia() && pilhaOperadores.topo() != '(') {
                    char topo = pilhaOperadores.topo();
                    int precTopo = obterPrecedencia(topo);
                    int precAtual = obterPrecedencia(c);

                    if (precTopo > precAtual || (precTopo == precAtual && !isAssociativoDireita(c))) {
                        saida.append(pilhaOperadores.pop()).append(" ");
                    } else {
                        break;
                    }
                }
                // Empilha o operador atual
                pilhaOperadores.push(c);
            }
        }

        // 5. Desempilha todos os operadores remanescentes na pilha
        while (!pilhaOperadores.vazia()) {
            saida.append(pilhaOperadores.pop()).append(" ");
        }

        return saida.toString().trim();
    }

    /**
     * Retorna a ordem de precedência do operador.
     * Níveis:
     *   3 -> ^ (Exponenciação)
     *   2 -> * e / (Multiplicação e Divisão)
     *   1 -> + e - (Adição e Subtração)
     *   0 -> Demais caracteres (ex: '(' dentro da pilha)
     */
    private static int obterPrecedencia(char operador) {
        switch (operador) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Informa se o operador é associativo à direita.
     * Conforme a especificação do trabalho:
     * - '^' é associativo à direita: A ^ B ^ C = A ^ (B ^ C)
     * - '+', '-', '*', '/' são associativos à esquerda
     */
    private static boolean isAssociativoDireita(char operador) {
        return operador == '^';
    }
}
