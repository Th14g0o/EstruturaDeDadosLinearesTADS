package atividades.implementacoes.Arvore.Heap.Ligado;

import java.util.ArrayList;

public class Heap {
    private No raiz;
    private int tamanho;

    public Heap(int elemento) {
        raiz = new No(elemento);
        tamanho = 1;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }

    private void upHeap(No atual) {
        while (atual.pai != null && atual.elemento < atual.pai.elemento) {
            int aux = atual.elemento;
            atual.elemento = atual.pai.elemento;
            atual.pai.elemento = aux;
            atual = atual.pai;
        }
    }

    private void downHeap(No atual) {
        while (atual != null) {
            No menor = atual;

            if (atual.esquerdo != null && atual.esquerdo.elemento < menor.elemento) 
                menor = atual.esquerdo;
            if (atual.direito != null && atual.direito.elemento < menor.elemento) 
                menor = atual.direito;

            if (menor == atual) break;

            int aux = atual.elemento;
            atual.elemento = menor.elemento;
            menor.elemento = aux;

            atual = menor;
        }
    }

    private No localizar(int indice) {
        String bin = Integer.toBinaryString(indice);

        No atual = raiz;
        for (int i = 1; i < bin.length(); i++) {
            if (bin.charAt(i) == '0')
                atual = atual.esquerdo;
            else
                atual = atual.direito;
        }
        return atual;
    }

    public void insert(int elemento) {
        No novo = new No(elemento);

        if (raiz == null) {
            raiz = novo;
            tamanho++;
            return;
        }

        int pos = tamanho + 1;

        No pai = localizar(pos / 2);
        novo.pai = pai;

        if (pos % 2 == 0)
            pai.esquerdo = novo;
        else
            pai.direito = novo;

        tamanho++;
        upHeap(novo);
    }

    public int remove() {
        if (isEmpty())
            throw new RuntimeException("Heap vazio");

        int removido = raiz.elemento;

        if (tamanho == 1) {
            raiz = null;
            tamanho--;
            return removido;
        }

        No ultimo = localizar(tamanho);

        raiz.elemento = ultimo.elemento;

        if (ultimo.pai.esquerdo == ultimo)
            ultimo.pai.esquerdo = null;
        else
            ultimo.pai.direito = null;
        tamanho--;

        downHeap(raiz);
        return removido;
    }

    public int min(){
        return raiz.elemento;
    }

    private String adicionarElementos(No no) {
        String elementos = "";
        ArrayList<No> fila = new ArrayList<>();

        fila.add(raiz);

        for (int i = 0; i < fila.size(); i++) {
            No atual = fila.get(i);
            elementos += atual.elemento + " ";
            if (atual.esquerdo != null)
                fila.add(atual.esquerdo);
            if (atual.direito != null)
                fila.add(atual.direito);
        }
        return elementos;
    }
    @Override
    public String toString() {
        return adicionarElementos(raiz);
    }
}