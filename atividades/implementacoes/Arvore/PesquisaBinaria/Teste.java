package atividades.implementacoes.Arvore.PesquisaBinaria;

public class Teste {
    public static void main(String[] args) {
        ArvorePesquisaBinaria arvore = new ArvorePesquisaBinaria();

        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(2);
        arvore.inserir(8);
        arvore.inserir(22);

        System.out.println("Árvore inicial:");
        arvore.mostrar();

        System.out.println("\nInserindo 25...");
        arvore.inserir(25);
        arvore.mostrar();

        System.out.println("\nRemovendo 5...");
        arvore.remover(5);
        arvore.mostrar();

        System.out.println("\nBusca 22: " + arvore.buscar(22));

        System.out.println("\nEm ordem:");
        arvore.emOrdem();

        System.out.println("Pré ordem:");
        arvore.preOrdem();

        System.out.println("Pós ordem:");
        arvore.posOrdem();

        System.out.println("Altura: " + arvore.altura());

        System.out.println("Tamanho: " + arvore.size());
    }
}
