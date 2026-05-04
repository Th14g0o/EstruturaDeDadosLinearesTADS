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
    private void excecaoVazia(){
        if (this.isEmpty())  throw new ETADVetor("Não existe nenhum elemento.");
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

    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.tam == 0;
    }
    public boolean isFirst(Object n){
        this.excecaoVazia();
        return this.size() > 0 && this.vetor[0] == n;
    } 
    public boolean isLast(Object n){
        return  this.size() > 0 && this.vetor[this.size()-1] == n;
    }

    public Object first(){
        this.excecaoVazia();
        return this.vetor[0];
    }
    public Object last(){
        this.excecaoVazia();
        return this.vetor[this.size()-1];

    }
    public Object before(int p) {
        this.excecaoVazia();

        this.colocacaoInvalidaVazia(p);

        if (p == 0) throw new ETADVetor("Não existe nenhum elemento antes.");

        return this.vetor[p];
    }
    public Object after(int p) { 
        this.excecaoVazia();

        this.colocacaoInvalidaVazia(p);

        if (p == this.size() - 1)  throw new ETADVetor("Não existe nenhum elemento depois.");

        return this.vetor[p];
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

        Object antigo = this.vetor[n];
        this.vetor[n] = this.vetor[q];
        this.vetor[q] = antigo;
    
    }
    public void insertBefore(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADVetor("Colocação invalida.");

        if (this.size() + 1 >= this.vetor.length) redimensionar(true);
        
        deslocarDireita(n);

        this.tam++;
        this.vetor[n] = o;
    } 
    public void insertAfter(int n, Object o) {
        if (this.size() == 0) insertBefore(n, o);
        else{
            if (n > this.size() || n < 0) throw new ETADVetor("Colocação invalida.");

            if (this.size() + 1 >= this.vetor.length) redimensionar(true);

            deslocarDireita(n + 1);

            this.tam++;
            this.vetor[n + 1] = o;
        }     
    }
    public void insertFirst(Object o) {
        this.excecaoVazia();

        if (this.size() + 1 >= this.vetor.length) redimensionar(true);

        deslocarDireita(0);
        this.tam++;
        this.vetor[0] = o;
    }
    public void insertLast(Object o) {
        this.excecaoVazia();

        if (this.size() + 1 >= this.vetor.length) redimensionar(true);

        this.tam++;
        this.vetor[this.size()] = o;
    }

    public Object remove(int n) {
        this.colocacaoInvalidaVazia(n);      

        Object antigo = this.vetor[n];
        deslocarEsquerda(n);     
        tam--;
        if (this.size() + 1 <= this.vetor.length / 3) redimensionar(false);
        return antigo;
    }


}
