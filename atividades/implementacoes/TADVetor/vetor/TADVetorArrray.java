package atividades.implementacoes.TADVetor.vetor;

import atividades.implementacoes.TADVetor.ETADVetor;
import atividades.implementacoes.TADVetor.ITADVetor;

public class TADVetorArrray implements ITADVetor {
    private Object[] vetor;
    private int tam;

    private void colocacaoInvalidaVazia(int r){
        if (r >= this.size() || r < 0) throw new ETADVetor("Colocação invalida.");
    }

    private void redimensionar(boolean aumentar) {
        int capacidade = aumentar ? this.vetor.length * 2 : this.vetor.length / 2;
        Object[] novoVetor = new Object[capacidade];
        for (int i = 0; i <= this.size(); i++) novoVetor[i] = this.vetor[i];
        this.vetor = novoVetor;
    }

    private void deslocarDireita(int r){
        for (int i = this.size() - r; i >= r ; i--){
            this.vetor[i + 1] = this.vetor[i];
        }
    }

    private void deslocarEsquerda(int r){
        for (int i = this.size() - r - 1; i >= r ; i--){
            this.vetor[i] = this.vetor[i + 1];
        }
    }

    public TADVetorArrray(){
        this(1);
    }

    public TADVetorArrray(int capacidade){
        this.tam = 0;
        this.vetor = new Object[capacidade];
    }

    public Object elemAtRank(int r){
        this.colocacaoInvalidaVazia(r);   

        return this.vetor[r];
    }

    public Object replaceAtRank(int r, Object o){
        this.colocacaoInvalidaVazia(r); 

        Object antigo = this.vetor[r];
        this.vetor[r] = o;
        return  antigo;
    }

    public void insertAtRank(int r, Object o){
        if (r > this.size() || r < 0) throw new ETADVetor("Colocação invalida.");;

        if (this.size() + 1 >= this.vetor.length) redimensionar(true);

        deslocarDireita(r);

        this.tam++;
        this.vetor[r] = o;
    }

    public Object removeAtRank(int r){
        this.colocacaoInvalidaVazia(r);      

        Object antigo = this.vetor[r];
        deslocarEsquerda(r);     
        tam--;
        if (this.size() + 1 <= this.vetor.length / 3) redimensionar(false);
        return antigo;
    }
    
    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.tam == 0;
    }

}
