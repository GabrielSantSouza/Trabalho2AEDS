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
public class AvaliadorPosFixa {
    public static double avaliar(String expressaoPosFixa) throws Exception {
        if (expressaoPosFixa == null || expressaoPosFixa.trim().isEmpty()) {
            throw new Exception("Erro de Avaliação: Expressão pós-fixa vazia.");
        }

        Pilha<Double> pilhaOperandos = new Pilha<Double>();

        // Divide a string pós-fixa pelos espaços em branco
        String[] tokens = expressaoPosFixa.trim().split("\\s+");

        for (String token : tokens) {
            // Se for um operador suportado
            if (token.length() == 1 && ValidadorSintatico.isOperador(token.charAt(0))) {
                char operador = token.charAt(0);

                if (pilhaOperandos.tamanho() < 2) {
                    throw new Exception("Erro de Avaliação: Expressão malformada, operandos insuficientes.");
                }

                // Ordem de desempilhamento: o primeiro desempilhado é o operando da direita (b)
                double b = pilhaOperandos.pop();
                double a = pilhaOperandos.pop();

                double resultado = executarOperacao(a, b, operador);
                pilhaOperandos.push(resultado);

            } else {
                // Se não for operador, é um operando numérico
                try {
                    double numero = Double.parseDouble(token);
                    pilhaOperandos.push(numero);
                } catch (NumberFormatException e) {
                    throw new Exception("Erro de Avaliação: Token inválido encontrado: '" + token + "'.");
                }
            }
        }

        // Ao término, deve sobrar exatamente 1 valor na pilha (o resultado final)
        if (pilhaOperandos.tamanho() != 1) {
            throw new Exception("Erro de Avaliação: Expressão inconsistente, sobraram operandos na pilha.");
        }

        return pilhaOperandos.pop();
    }

    /**
     * Executa a operação aritmética entre dois operandos.
     */
    private static double executarOperacao(double a, double b, char operador) throws Exception {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0.0) {
                    throw new ArithmeticException("Erro Aritmético: Divisão por zero não é permitida.");
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new Exception("Erro: Operador desconhecido '" + operador + "'.");
        }
    }

}
