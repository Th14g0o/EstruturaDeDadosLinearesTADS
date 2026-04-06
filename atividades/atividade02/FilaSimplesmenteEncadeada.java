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
        if (this.i == null) this.i = novo;
        else{
            boolean seta = false;
            if (this.f != null) f.setProximo(novo);
            else seta = true;
            this.f = novo;
            if (seta) this.i.setProximo(this.f);
        }
        this.tam++;
    }

    public Object desinfileirar(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        Object deletado = i.getValor();
        this.i = this.i.getProximo();
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