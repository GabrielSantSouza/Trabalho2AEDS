/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho2;

import java.util.Scanner;
import validador.*;


/**
 *
 * @author gabriel
 */
public class Trabalho2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n------------------------------------------------------");
            System.out.println("Digite a expressao infixa (ou 'sair' para encerrar):");
            System.out.print("> ");
            String entrada = scanner.nextLine();

            if (entrada.trim().equalsIgnoreCase("sair")) {
                continuar = false;
                System.out.println("\nPrograma encerrado com sucesso. Ate logo!");
                break;
            }

            try {
                // 1. Etapa de Validacao Sintatica
                System.out.println("\n[1/3] Validando sintaxe da expressao...");
                ValidadorSintatico.validar(entrada);
                System.out.println("  -> Expressao sintaticamente CORRETA!");

                // 2. Etapa de Conversao Infixa -> Pos-fixa
                System.out.println("\n[2/3] Convertendo para Notacao Pos-fixa...");
                String posFixa = ConversorPosFixa.converter(entrada);
                System.out.println("  -> Pos-fixa: " + posFixa);

                // 3. Etapa de Avaliacao Numerica da Pos-fixa
                System.out.println("\n[3/3] Calculando resultado numerico...");
                double resultado = AvaliadorPosFixa.avaliar(posFixa);

                // Exibicao formatada (se for inteiro exibe sem casas decimais)
                if (resultado == (long) resultado) {
                    System.out.printf("  -> RESULTADO FINAL: %d\n", (long) resultado);
                } else {
                    System.out.printf("  -> RESULTADO FINAL: %.4f\n", resultado);
                }

            } catch (Exception e) {
                System.out.println("\n  [FALHA DETECTADA]");
                System.out.println("  " + e.getMessage());
            }
        }

        scanner.close();
    }
    
}
