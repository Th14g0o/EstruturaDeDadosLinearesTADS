package atividades.implementacoes.TADLista.ligada;

import atividades.implementacoes.No;
import atividades.implementacoes.TADLista.ETADLista;
import atividades.implementacoes.TADLista.ITADLista;

public class TADListaLigada implements ITADLista {
    private No cabeca, calda;
    private int tam;

    public TADListaLigada(){
        this.tam = 0;
        this.cabeca = new No();
        this.calda = new No();

        this.cabeca.setProximo(this.calda);
        this.calda.setAnterior(this.cabeca);
    }

    private void excecaoVazia(){
        if (this.isEmpty()) throw new ETADLista("Não existe nenhum elemento.");
    }
    private void colocacaoInvalidaVazia(int r){
        if (r >= this.size() || r < 0) throw new ETADLista("Colocação invalida.");
    }

    private No FimParaInicio(int r){
        No no = calda.getAnterior();
        for(int i = this.size() - 1; i > r ; i--)
            no = no.getAnterior();
        return no;
    }
    private No InicioParaFim(int r){
        No no = cabeca.getProximo();
        for(int i = 0; i < r; i++)
            no = no.getProximo();
        return no;
    }
    private No noAtRank(int r){
        No no;
        if (r > this.size()/2) no = this.FimParaInicio(r);
        else no = this.InicioParaFim(r);
        return no;
    }


    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.tam == 0;
    }

    public boolean isFirst(Object n){
        this.excecaoVazia();
        return this.cabeca.getProximo().getValor() == n;
    } 
    public boolean isLast(Object n){
        this.excecaoVazia();
        return this.calda.getAnterior().getValor() == n;
    }
    public Object first(){
        this.excecaoVazia();

        return this.cabeca.getProximo().getValor();
    }
    public Object last(){
        this.excecaoVazia();
        return this.calda.getAnterior().getValor();

    }
    public Object before(int p) {        
        this.colocacaoInvalidaVazia(p);

        if (p == 0) throw new ETADLista("Não existe nenhum elemento antes.");

        return this.noAtRank(p - 1).getValor();
    }
        
    public Object after(int p) {          
        this.colocacaoInvalidaVazia(p);

        if (p == this.size() - 1)  throw new ETADLista("Não existe nenhum elemento depois.");
        
        return this.noAtRank(p + 1).getValor();
    }

    public Object replaceElement(int n, Object o) {
        this.colocacaoInvalidaVazia(n);

        No no = new No(o);

        No noExistente = this.noAtRank(n);
        no.setAnterior(noExistente.getAnterior());
        no.setProximo(noExistente.getProximo());
        no.getAnterior().setProximo(no);
        no.getProximo().setAnterior(no);
        return noExistente.getValor();

    }
    public void swapElements(int n, int q) { 
        this.colocacaoInvalidaVazia(n);
        this.colocacaoInvalidaVazia(q);

        No noN = this.noAtRank(n);
        No noQ = this.noAtRank(q);

        No noCopiaN = new No(noN.getValor());
        noCopiaN.setAnterior(noCopiaN.getAnterior());
        noCopiaN.setProximo(noCopiaN.getProximo());

        noN.setAnterior(noQ.getAnterior());
        noN.setProximo(noQ.getProximo());
        noN.setValor(noQ.getValor());

        noQ.setAnterior(noCopiaN.getAnterior());
        noQ.setProximo(noCopiaN.getProximo());
        noQ.setValor(noCopiaN.getValor());

    }
    public void insertBefore(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADLista("Colocação invalida.");

        if (this.size() == 0) this.insertFirst(o);

        No no = new No(o);
        No noExistente = this.noAtRank(n);
        no.setProximo(noExistente);
        no.setAnterior(noExistente.getAnterior());

        noExistente.getAnterior().setProximo(no);
        noExistente.setAnterior(no);

        
        this.tam++;
    } 
    public void insertAfter(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADLista("Colocação invalida.");

        if (this.size() == 0) this.insertFirst(o);

        No no = new No(o);
        No noExistente = this.noAtRank(n);
        no.setAnterior(noExistente);
        no.setProximo(noExistente.getProximo());

        noExistente.getProximo().setAnterior(no);
        noExistente.setProximo(no);

        this.tam++;
    }
    public void insertFirst(Object o) {
        No no = new No(o);
        no.setAnterior(this.cabeca);
        no.setProximo(this.cabeca.getProximo());

        this.cabeca.getProximo().setAnterior(no);
        this.cabeca.setProximo(no);

        this.tam++;
    }
    public void insertLast(Object o) {
        No no = new No(o);
        no.setAnterior(this.calda.getAnterior());
        no.setProximo(this.calda);

        this.calda.getAnterior().setProximo(no);
        this.calda.setAnterior(no);

        this.tam++;
    }
    public Object remove(int n) {
        this.colocacaoInvalidaVazia(n);

        

        No no = this.noAtRank(n);
        no.getAnterior().setProximo(no.getProximo());
        no.getProximo().setAnterior(no.getAnterior());

        this.tam--;
        return no.getValor();   
    }
}
