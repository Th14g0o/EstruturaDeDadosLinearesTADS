package atividades.implementacoes.TADLista;

import java.util.Scanner;

import atividades.implementacoes.TADLista.ligada.TADListaLigada;
import atividades.implementacoes.TADLista.vetor.TADListaArray;

public class TesteTADLista {
    public static void main(String[] args) {

        ITADLista lista = escolhaTeste();

        System.out.println("-----------------");

        testarIsEmpty(lista);
        testarInsercoes(lista);
        testarFirstLast(lista);
        testarBeforeAfter(lista);
        testarReplace(lista);
        testarSwap(lista);
        testarRemove(lista);
        testarExcecoes(lista);

        System.out.println("-----------------");
    }

    private static ITADLista escolhaTeste(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Opções:\n1 = Array\n2 = Ligada");

        while (true){
            System.out.print("Digite aqui: ");
            try {
                int op = sc.nextInt();
                if (op == 1) return new TADListaArray();
                else if (op == 2) return new TADListaLigada();

                System.out.println("Digite uma opção válida.");
            } catch (Exception e){
                System.out.println("Entrada inválida.");
                sc.next();
            }
        }
    }

    private static void verifica(Object esperado, Object obtido, String msg){
        if ((esperado == null && obtido != null) || (esperado != null && !esperado.equals(obtido))) {

            System.out.println("ERRO: " + msg);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido: " + obtido);
        }
    }

    private static void testarIsEmpty(ITADLista l){
        System.out.println("- Testando isEmpty");

        if (!l.isEmpty())
            System.out.println("ERRO: Lista deveria iniciar vazia");
    }

    private static void testarInsercoes(ITADLista l){
        System.out.println("- Testando inserções");

        l.insertFirst(1);   // [1]
        l.insertLast(2);    // [1,2]
        l.insertAfter(0, 3); // [1,3,2]
        l.insertBefore(0, 0); // [0,1,3,2]

        verifica(4, l.size(), "Tamanho após inserções");
    }

    private static void testarFirstLast(ITADLista l){
        System.out.println("- Testando first e last");

        verifica(0, l.first(), "Primeiro elemento");
        verifica(2, l.last(), "Último elemento");
    }

    private static void testarBeforeAfter(ITADLista l){
        System.out.println("- Testando before e after");

        verifica(1, l.after(0), "Elemento após posição 0");
        verifica(3, l.after(1), "Elemento após posição 1");
        verifica(1, l.before(2), "Elemento antes da posição 2");
    }

    private static void testarReplace(ITADLista l){
        System.out.println("- Testando replaceElement");

        Object antigo = l.replaceElement(1, 99); // [0,99,3,2]

        verifica(1, antigo, "Valor antigo");
        verifica(99, l.after(0), "Valor substituído");
    }

    private static void testarSwap(ITADLista l){
        System.out.println("- Testando swapElements");

        l.swapElements(0, 3); // troca 0 com 2 -> [2,99,3,0]

        verifica(2, l.first(), "Swap posição 0");
        verifica(0, l.last(), "Swap posição 3");
    }

    private static void testarRemove(ITADLista l){
        System.out.println("- Testando remove");

        Object removido = l.remove(1); // remove 99

        verifica(99, removido, "Elemento removido");
        verifica(3, l.size(), "Tamanho após remoção");
    }

    private static void testarExcecoes(ITADLista l){
        System.out.println("- Testando exceções");

        try {
            l.before(0);
            System.out.println("ERRO: before deveria falhar");
        } catch (Exception e) {}

        try {
            l.after(100);
            System.out.println("ERRO: after inválido");
        } catch (Exception e) {}

        try {
            l.remove(100);
            System.out.println("ERRO: remove inválido");
        } catch (Exception e) {}

        try {
            l.replaceElement(100, 5);
            System.out.println("ERRO: replace inválido");
        } catch (Exception e) {}

        try {
            l.swapElements(0, 100);
            System.out.println("ERRO: swap inválido");
        } catch (Exception e) {}
    }
}
