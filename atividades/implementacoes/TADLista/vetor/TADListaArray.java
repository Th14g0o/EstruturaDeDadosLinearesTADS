package atividades.implementacoes.TADLista.vetor;

import atividades.implementacoes.TADLista.ITADLista;
import atividades.implementacoes.TADVetor.ETADVetor;

public class TADListaArray implements ITADLista {
    private Object[] vetor;
    private int tam;

    public TADListaArray(){
        this(1);
    }
    public TADListaArray(int capacidade){
        this.tam = 0;
        this.vetor = new Object[capacidade];
    }

    private void colocacaoInvalidaVazia(int r){
        if (r >= this.size() || r < 0) throw new ETADVetor("Colocação invalida.");
    }

    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.tam == 0;
    }
    public boolean isFirst(Object n){
        if (this.isEmpty())  throw new ETADVetor("Não existe primeiro elemento.");
        return this.size() > 0 && this.vetor[0] == n;
    } 
    public boolean isLast(Object n){
        return  this.size() > 0 && this.vetor[this.size()-1] == n;
    }

    public Object first(){
        return this.vetor[0];
    }
    public Object last(){
        return this.vetor[this.size()-1];

    }
    public Object before(Object p) {
        for (int i = 0; i < this.size();){
            if (this.vetor[i].equals(p)) return this.vetor[i-1];
        } 
        return null;
    }
    public Object after(Object p) { 
        for (int i = 0; i < this.size();){
            if (this.vetor[i].equals(p)) return this.vetor[i+1];
        } 
        return null;
    }

    public Object replaceElement(int n, Object o) {
        this.colocacaoInvalidaVazia(n); 

        Object antigo = this.vetor[n];
        this.vetor[n] = o;
        return  antigo;
    }
    public void swapElements(int n, int q) {
        this.colocacaoInvalidaVazia(n);
        this.colocacaoInvalidaVazia(q);  
    
    }
    public void insertBefore(int n, Object o) {
        this.colocacaoInvalidaVazia(n);

    } 
    public void insertAfter(int n, Object o) {
        this.colocacaoInvalidaVazia(n);

    }
    public void insertFirst(Object o) {

    }
    public void insertLast(Object o) {

    }

    public Object remove(int n) {
        this.colocacaoInvalidaVazia(n);
        return null;
    }


}
