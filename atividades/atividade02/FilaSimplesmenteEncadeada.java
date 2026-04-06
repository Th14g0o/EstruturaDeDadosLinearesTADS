package atividades.atividade02;

public class FilaSimplesmenteEncadeada implements IFila {
    private No i;
    private No f;
    private int tam;

    public FilaSimplesmenteEncadeada(){
        this.i = null;
        this.f = null;
        this.tam = 0;
    }

    public void enfileirar(Object obj){
        No novo = new No();
        novo.setValor(obj);
        if (this.taVazio()) {
            this.i = novo;
            this.f = novo;
        } else {
            this.f.setProximo(novo);
            this.f = novo;
        }
        this.tam++;
    }

    public Object desinfileirar(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        Object deletado = i.getValor();
        this.i = this.i.getProximo();
        if (this.i == null) this.f = null;
        this.tam--;
        return deletado;
    }

    public Object primeiro(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        return this.i.getValor();
    }

    public Object ultimo(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        return this.f.getValor();
    }

    public int tamanho(){
        return this.tam;
    }

    public boolean taVazio(){
        return this.i == null && this.f == null;
    }



}