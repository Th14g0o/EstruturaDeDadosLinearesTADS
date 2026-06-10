package atividades.implementacoes.Arvore.Heap.Ligado;

public class No {
    int elemento;
    No pai;
    No esquerdo;
    No direito;

    public No(int elemento) {
        this.elemento = elemento;
    }

    @Override
    public String toString() {
        return String.valueOf(elemento);
    }
}