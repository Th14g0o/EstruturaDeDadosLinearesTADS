package atividades.implementacoes.TADVetor;

import atividades.implementacoes.TADVetor.ligada.TADVetorLigado;
import atividades.implementacoes.TADVetor.vetor.TADVetorArrray;
import java.util.Scanner;

public class TesteTADVetor {
    public static void main(String[] args) {

        ITADVetor vetor = escolhaTeste(); 

        System.out.println("-----------------");

        testarIsEmpty(vetor);
        testarInsert(vetor);
        testarElemAtRank(vetor);
        testarReplace(vetor);
        testarRemove(vetor);
        testarExcecoes(vetor);

        System.out.println("-----------------");
    }

    private static ITADVetor escolhaTeste(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Opções:\n1 = Array\n2 = Ligada");
        int opFinal = 0;
        while (opFinal == 0){
            System.out.println("Digite aqui:");
            try {
                int op = sc.nextInt();
                if (op == 1) return new TADVetorArrray();
                else if (op == 2) return new TADVetorLigado();
                else  System.out.println("Digite uma opção valida.");
            } 
            catch (Exception e) {
               System.out.println("Digite uma opção valida.");
            }
        }
        return new TADVetorArrray();
    }

    private static void verificaIgualdade(Object esperado, Object obtido, String msg) {
        if ((esperado == null && obtido != null) || (esperado != null && !esperado.equals(obtido))) {
            System.out.println("ERRO: " + msg);
            System.out.println("ESPERADO: " + esperado);
            System.out.println("OBTIDO: " + obtido);
        }
    }

    private static void testarIsEmpty(ITADVetor v) {
        System.out.println("- Testando isEmpty");
        if (!v.isEmpty()) System.out.println("ERRO: Vetor deveria iniciar vazio");
    }

    private static void testarInsert(ITADVetor v) {
        System.out.println("- Testando insertAtRank");

        v.insertAtRank(0, 1);
        verificaIgualdade(1, v.size(), "Tamanho após inserção do 1");

        v.insertAtRank(1, 2);
        verificaIgualdade(2, v.size(), "Tamanho após inserção do 2");

        v.insertAtRank(1, 3); // 1 3 2
        verificaIgualdade(3, v.size(), "Tamanho após inserções");

        verificaIgualdade(1, v.elemAtRank(0), "Elemento na posição 0");
        verificaIgualdade(3, v.elemAtRank(1), "Elemento na posição 1");
        verificaIgualdade(2, v.elemAtRank(2), "Elemento na posição 2");
    }

    private static void testarElemAtRank(ITADVetor v) {
        System.out.println("- Testando elemAtRank");

        verificaIgualdade(1, v.elemAtRank(0), "elemAtRank 0");
        verificaIgualdade(3, v.elemAtRank(1), "elemAtRank 1");
        verificaIgualdade(2, v.elemAtRank(2), "elemAtRank 2");
    }

    private static void testarReplace(ITADVetor v) {
        System.out.println("- Testando replaceAtRank");

        Object antigo = v.replaceAtRank(1, 4); // 1 4 2

        verificaIgualdade(3, antigo, "Valor antigo retornado");
        verificaIgualdade(4, v.elemAtRank(1), "Valor substituído corretamente");
    }

    private static void testarRemove(ITADVetor v) {
        System.out.println("- Testando removeAtRank");

        Object removido = v.removeAtRank(1); // remove 4 -> 1 2

        verificaIgualdade(4, removido, "Elemento removido correto");
        verificaIgualdade(2, v.size(), "Tamanho após remoção");
        verificaIgualdade(1, v.elemAtRank(0), "Elemento 0 após remoção");
        verificaIgualdade(2, v.elemAtRank(1), "Elemento 1 após remoção");
    }

    private static void testarExcecoes(ITADVetor v) {
        System.out.println("- Testando exceções");

        try {
            v.elemAtRank(10);
            System.out.println("ERRO: Não lançou exceção para índice inválido");
        } catch (Exception e) {}

         try {
            v.replaceAtRank(10, 5);
            System.out.println("ERRO: Não lançou exceção em replace inválido");
        } catch (Exception e) {}

        try {
            v.insertAtRank(10, "Z");
            System.out.println("ERRO: Não lançou exceção em insert inválido");
        } catch (Exception e) {}

        try {
            v.removeAtRank(10);
            System.out.println("ERRO: Não lançou exceção em remove inválido");
        } catch (Exception e) {}
    }
}