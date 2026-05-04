package atividades.implementacoes.TADVetor.ligada;

import atividades.implementacoes.No;
import atividades.implementacoes.TADVetor.ETADVetor;
import atividades.implementacoes.TADVetor.ITADVetor;

public class TADVetorLigado implements ITADVetor {
    private No cabeca, calda;
    private int tam;

    public TADVetorLigado(){
        this.tam = 0;
        this.cabeca = new No();
        this.calda = new No();

        this.cabeca.setProximo(this.calda);
        this.calda.setProximo(this.cabeca);
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

    private void colocacaoInvalidaVazia(int r){
        if (r >= this.size() || r < 0) throw new ETADVetor("Colocação invalida.");
    }

    public Object elemAtRank(int r){
        this.colocacaoInvalidaVazia(r);
        return this.noAtRank(r).getValor();
    }

    public Object replaceAtRank(int r, Object o){
        this.colocacaoInvalidaVazia(r);

        No no = new No(o);

        No noExistente = this.noAtRank(r);
        no.setAnterior(noExistente.getAnterior());
        no.setProximo(noExistente.getProximo());
        no.getAnterior().setProximo(no);
        no.getProximo().setAnterior(no);
        return noExistente.getValor();    
    }

    public void insertAtRank(int r, Object o){
        if (r > this.size() || r < 0) throw new ETADVetor("Colocação invalida.");
    
        No no = new No(o);

        if (this.size() == 0){
            this.cabeca.setProximo(no);
            no.setAnterior(this.cabeca);
            this.calda.setAnterior(no);
            no.setProximo(this.calda);
        }
        else{
            No noExistente = this.noAtRank(r);
            if (this.size() == r){
                no.setProximo(noExistente.getProximo());
                no.setAnterior(noExistente);
                noExistente.getProximo().setAnterior(no);
                noExistente.setProximo(no);
            } 
            else{
                no.setProximo(noExistente);
                no.setAnterior(noExistente.getAnterior());
                no.getAnterior().setProximo(no);
                noExistente.setAnterior(no);   
            }
        } 
        
        this.tam++;
    }

    public Object removeAtRank(int r){
        this.colocacaoInvalidaVazia(r);

        No no = this.noAtRank(r);
        No noAnterior = no.getAnterior();
        No noProximo = no.getProximo();
        noAnterior.setProximo(noProximo);
        noProximo.setAnterior(noAnterior);
        this.tam--;
        return no.getValor();        
    }

    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.tam == 0;
    }
    
}
