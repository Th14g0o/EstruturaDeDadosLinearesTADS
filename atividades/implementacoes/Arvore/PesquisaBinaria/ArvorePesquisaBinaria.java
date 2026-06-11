package atividades.implementacoes.Arvore.PesquisaBinaria;



public class ArvorePesquisaBinaria {

    private No raiz;
    private int tamanho;

    public boolean isEmpty() {
        return raiz == null;
    }

    public int size() {
        return tamanho;
    }

    public No root() {
        return raiz;
    }

    // =========================
    // INSERÇÃO
    // =========================
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No atual, int valor) {

        if (atual == null) {
            tamanho++;
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerdo = inserirRec(atual.esquerdo, valor);
        } else if (valor > atual.valor) {
            atual.direito = inserirRec(atual.direito, valor);
        }

        return atual;
    }

    // =========================
    // BUSCA
    // =========================
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(No atual, int valor) {

        if (atual == null)
            return false;

        if (valor == atual.valor)
            return true;

        if (valor < atual.valor)
            return buscarRec(atual.esquerdo, valor);

        return buscarRec(atual.direito, valor);
    }

    // =========================
    // REMOÇÃO
    // =========================
    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private No removerRec(No atual, int valor) {

        if (atual == null)
            return null;

        if (valor < atual.valor) {
            atual.esquerdo = removerRec(atual.esquerdo, valor);
        }
        else if (valor > atual.valor) {
            atual.direito = removerRec(atual.direito, valor);
        }
        else {

            // folha
            if (atual.esquerdo == null && atual.direito == null) {
                tamanho--;
                return null;
            }

            // um filho
            if (atual.esquerdo == null) {
                tamanho--;
                return atual.direito;
            }

            if (atual.direito == null) {
                tamanho--;
                return atual.esquerdo;
            }

            // dois filhos
            No sucessor = menor(atual.direito);
            atual.valor = sucessor.valor;
            atual.direito = removerRec(atual.direito, sucessor.valor);
        }

        return atual;
    }

    private No menor(No no) {
        while (no.esquerdo != null) {
            no = no.esquerdo;
        }
        return no;
    }

    // =========================
    // ALTURA
    // =========================
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(No no) {

        if (no == null)
            return -1;

        return 1 + Math.max(
                alturaRec(no.esquerdo),
                alturaRec(no.direito)
        );
    }

    // =========================
    // PERCURSOS
    // =========================
    public void preOrdem() {
        preOrdemRec(raiz);
        System.out.println();
    }

    private void preOrdemRec(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdemRec(no.esquerdo);
            preOrdemRec(no.direito);
        }
    }

    public void emOrdem() {
        emOrdemRec(raiz);
        System.out.println();
    }

    private void emOrdemRec(No no) {
        if (no != null) {
            emOrdemRec(no.esquerdo);
            System.out.print(no.valor + " ");
            emOrdemRec(no.direito);
        }
    }

    public void posOrdem() {
        posOrdemRec(raiz);
        System.out.println();
    }

    private void posOrdemRec(No no) {
        if (no != null) {
            posOrdemRec(no.esquerdo);
            posOrdemRec(no.direito);
            System.out.print(no.valor + " ");
        }
    }

    // =========================
    // MOSTRAR ÁRVORE
    // =========================
    public void mostrar() {
        mostrarRec(raiz, 0);
    }

    private void mostrarRec(No no, int nivel) {

        if (no == null)
            return;

        mostrarRec(no.direito, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("        ");
        }

        System.out.println(no.valor);

        mostrarRec(no.esquerdo, nivel + 1);
    }
}
