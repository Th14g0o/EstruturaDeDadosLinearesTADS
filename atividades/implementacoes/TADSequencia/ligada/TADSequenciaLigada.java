package atividades.implementacoes.TADSequencia.ligada;

import atividades.implementacoes.No;
import atividades.implementacoes.TADSequencia.ITADSequencia;
import atividades.implementacoes.TADSequencia.ETADSequencia;

public class TADSequenciaLigada implements ITADSequencia {
    private No cabeca, calda;
    private int tam;

    public TADSequenciaLigada(){
        this.tam = 0;
        this.cabeca = new No();
        this.calda = new No();

        this.cabeca.setProximo(this.calda);
        this.calda.setAnterior(this.cabeca);
    }

    private void excecaoVazia(){
        if (this.isEmpty()) throw new ETADSequencia("Não existe nenhum elemento.");
    }
    private void colocacaoInvalidaVazia(int r){
        if (r >= this.size() || r < 0) throw new ETADSequencia("Colocação invalida.");
    }

    public int rankOf(No n){
        No no = this.cabeca.getProximo();
        int i = 0;
        while (no != null){
            if (no.equals(n)) return i;
            i++;
            no = no.getProximo();
        }
       throw new ETADSequencia("Não existe nó igual.");
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
    public No atRank(int r){
        this.colocacaoInvalidaVazia(r);

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

        if (p == 0) throw new ETADSequencia("Não existe nenhum elemento antes.");

        return this.atRank(p - 1).getValor();
    }
        
    public Object after(int p) {          
        this.colocacaoInvalidaVazia(p);

        if (p == this.size() - 1)  throw new ETADSequencia("Não existe nenhum elemento depois.");
        
        return this.atRank(p + 1).getValor();
    }

    public Object replaceAtRank(int r, Object o){
        this.colocacaoInvalidaVazia(r);

        No no = new No(o);

        No noExistente = this.atRank(r);
        no.setAnterior(noExistente.getAnterior());
        no.setProximo(noExistente.getProximo());
        no.getAnterior().setProximo(no);
        no.getProximo().setAnterior(no);
        return noExistente.getValor();    
    }

    public Object replaceElement(int n, Object o) {
        this.colocacaoInvalidaVazia(n);

        No no = new No(o);

        No noExistente = this.atRank(n);
        no.setAnterior(noExistente.getAnterior());
        no.setProximo(noExistente.getProximo());
        no.getAnterior().setProximo(no);
        no.getProximo().setAnterior(no);
        return noExistente.getValor();

    }
    public void swapElements(int n, int q) { 
        this.colocacaoInvalidaVazia(n);
        this.colocacaoInvalidaVazia(q);

        if (n != q){
            No noN = this.atRank(n);
            No noQ = this.atRank(q);

            Object temp = noN.getValor();
            noN.setValor(noQ.getValor());
            
            noQ.setValor(temp);
        }
    }
    public void insertBefore(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADSequencia("Colocação invalida.");

        if (this.size() == 0) this.insertFirst(o);
        else{
            No no = new No(o);
            No noExistente = this.atRank(n);
            no.setProximo(noExistente);
            no.setAnterior(noExistente.getAnterior());

            noExistente.getAnterior().setProximo(no);
            noExistente.setAnterior(no);

            this.tam++;
        }        
    } 
    public void insertAfter(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADSequencia("Colocação invalida.");

        if (this.size() == 0) this.insertFirst(o);
        else{
            No no = new No(o);
            No noExistente = this.atRank(n);
            no.setAnterior(noExistente);
            no.setProximo(noExistente.getProximo());

            noExistente.getProximo().setAnterior(no);
            noExistente.setProximo(no);

            this.tam++;
        }        
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

        No no = this.atRank(n);;
        no.getAnterior().setProximo(no.getProximo());
        no.getProximo().setAnterior(no.getAnterior());

        this.tam--;
        return no.getValor();   
    }

    public void insertAtRank(int r, Object o){
        if (r > this.size() || r < 0) throw new ETADSequencia("Colocação invalida.");

        No novo = new No(o);

        No noExistente;

        if (r == this.size()) {
            noExistente = this.calda;
        } else {
            noExistente = this.atRank(r);
        }

        novo.setProximo(noExistente);
        novo.setAnterior(noExistente.getAnterior());

        noExistente.getAnterior().setProximo(novo);
        noExistente.setAnterior(novo);
        
        this.tam++;
    }

    public Object removeAtRank(int r){
        this.colocacaoInvalidaVazia(r);

        No no = this.atRank(r);
        No noAnterior = no.getAnterior();
        No noProximo = no.getProximo();
        noAnterior.setProximo(noProximo);
        noProximo.setAnterior(noAnterior);
        this.tam--;
        return no.getValor();        
    }

    public Object elemAtRank(int r){
        this.colocacaoInvalidaVazia(r);
        return this.atRank(r).getValor();
    }
}

