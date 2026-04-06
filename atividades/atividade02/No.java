package atividades.atividade02;

public class No {
    private Object valor = null;
    private No proximo = null;
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
}