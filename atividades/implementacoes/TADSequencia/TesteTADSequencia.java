package atividades.implementacoes.TADSequencia;

import atividades.implementacoes.No;
import atividades.implementacoes.TADSequencia.ligada.TADSequenciaLigada;

public class TesteTADSequencia {

    public static void main(String[] args) {

        ITADSequencia seq = new TADSequenciaLigada();;

        System.out.println("-----------------");

        testarIsEmpty(seq);
        testarInsercoes(seq);
        testarFirstLast(seq);
        testarElemAtRank(seq);
        testarBeforeAfter(seq);
        testarReplace(seq);
        testarSwap(seq);
        testarAtRankRankOf(seq);
        testarRemove(seq);
        testarExcecoes(seq);

        System.out.println("-----------------");
    }

    private static void verifica(Object esperado, Object obtido, String msg) {

        if ((esperado == null && obtido != null)
                || (esperado != null && !esperado.equals(obtido))) {

            System.out.println("ERRO: " + msg);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido: " + obtido);
        }
    }

    private static void testarIsEmpty(ITADSequencia s) {

        System.out.println("- Testando isEmpty");

        if (!s.isEmpty())
            System.out.println("ERRO: Sequência deveria iniciar vazia");
    }

    private static void testarInsercoes(ITADSequencia s) {

        System.out.println("- Testando inserções");

        s.insertFirst(1);          // [1]
        s.insertLast(2);           // [1,2]
        s.insertAtRank(1, 5);      // [1,5,2]
        s.insertAfter(0, 3);       // [1,3,5,2]
        s.insertBefore(0, 0);      // [0,1,3,5,2]

        verifica(5, s.size(), "Tamanho após inserções");
    }

    private static void testarFirstLast(ITADSequencia s) {

        System.out.println("- Testando first e last");

        verifica(0, s.first(), "Primeiro elemento");
        verifica(2, s.last(), "Último elemento");
    }

    private static void testarElemAtRank(ITADSequencia s) {

        System.out.println("- Testando elemAtRank");

        verifica(0, s.elemAtRank(0), "Elemento rank 0");
        verifica(1, s.elemAtRank(1), "Elemento rank 1");
        verifica(3, s.elemAtRank(2), "Elemento rank 2");
        verifica(5, s.elemAtRank(3), "Elemento rank 3");
        verifica(2, s.elemAtRank(4), "Elemento rank 4");
    }

    private static void testarBeforeAfter(ITADSequencia s) {

        System.out.println("- Testando before e after");

        verifica(1, s.after(0), "Elemento após posição 0");
        verifica(3, s.after(1), "Elemento após posição 1");

        verifica(1, s.before(2), "Elemento antes da posição 2");
        verifica(5, s.before(4), "Elemento antes da posição 4");
    }

    private static void testarReplace(ITADSequencia s) {

        System.out.println("- Testando replace");

        Object antigo1 = s.replaceAtRank(1, 99); // [0,99,3,5,2]
        verifica(1, antigo1, "replaceAtRank valor antigo");

        Object antigo2 = s.replaceElement(2, 77); // [0,99,77,5,2]
        verifica(3, antigo2, "replaceElement valor antigo");

        verifica(99, s.elemAtRank(1), "Valor substituído rank 1");
        verifica(77, s.elemAtRank(2), "Valor substituído rank 2");
    }

    private static void testarSwap(ITADSequencia s) {

        System.out.println("- Testando swapElements");

        s.swapElements(0, 4); // troca 0 com 2
        // [2,99,77,5,0]

        verifica(2, s.first(), "Swap posição 0");
        verifica(0, s.last(), "Swap posição 4");
    }

    private static void testarAtRankRankOf(ITADSequencia s) {

        System.out.println("- Testando atRank e rankOf");

        No no = s.atRank(2);

        verifica(77, no.getValor(), "Elemento do nó rank 2");

        int rank = s.rankOf(no);

        verifica(2, rank, "Rank do nó");
    }

    private static void testarRemove(ITADSequencia s) {

        System.out.println("- Testando remove");

        Object removido1 = s.removeAtRank(1); // remove 99
        verifica(99, removido1, "removeAtRank");

        Object removido2 = s.remove(1); // remove 77
        verifica(77, removido2, "remove");

        verifica(3, s.size(), "Tamanho após remoções");
    }

    private static void testarExcecoes(ITADSequencia s) {

        System.out.println("- Testando exceções");

        try {
            s.before(0);
            System.out.println("ERRO: before deveria falhar");
        } catch (Exception e) {}

        try {
            s.after(100);
            System.out.println("ERRO: after inválido");
        } catch (Exception e) {}

        try {
            s.elemAtRank(100);
            System.out.println("ERRO: elemAtRank inválido");
        } catch (Exception e) {}

        try {
            s.insertAtRank(100, 10);
            System.out.println("ERRO: insertAtRank inválido");
        } catch (Exception e) {}

        try {
            s.removeAtRank(100);
            System.out.println("ERRO: removeAtRank inválido");
        } catch (Exception e) {}

        try {
            s.remove(100);
            System.out.println("ERRO: remove inválido");
        } catch (Exception e) {}

        try {
            s.replaceAtRank(100, 5);
            System.out.println("ERRO: replaceAtRank inválido");
        } catch (Exception e) {}

        try {
            s.replaceElement(100, 5);
            System.out.println("ERRO: replaceElement inválido");
        } catch (Exception e) {}

        try {
            s.swapElements(0, 100);
            System.out.println("ERRO: swap inválido");
        } catch (Exception e) {}

        try {
            s.atRank(100);
            System.out.println("ERRO: atRank inválido");
        } catch (Exception e) {}
    }
}