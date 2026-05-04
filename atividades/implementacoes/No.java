package atividades.implementacoes;

public class No {
    private Object valor = null;
    private No proximo = null;
    private No anterior = null;

    public No(){
        this(null);
    }
    public No(Object valor){
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }
    public void setValor(Object o){
        valor = o;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
    public No getProximo() {
        return proximo;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
    public No getAnterior() {
        return anterior;
    }
}