# Implementação TADLista

## Classe Nó
```java
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
```

## Interface
```java
package atividades.implementacoes.TADLista;

public interface ITADLista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(Object n); 
    public boolean isLast(Object n);
    public Object first(); 
    public Object last(); 
    public Object before(int p); 
    public Object after(int p); 

    public Object replaceElement(int n, Object o); 
    public void swapElements(int n, int q); 
    public void insertBefore(int n, Object o); 
    public void insertAfter(int n, Object o); 
    public void insertFirst(Object o); 
    public void insertLast(Object o); 
    public Object remove(int n);
}
```

## Exceção
```java
package atividades.implementacoes.TADLista;

public class ETADLista extends RuntimeException {
    public ETADLista(String err){
        super(err);
    }
}
```

## Implementação em Array
```java
package atividades.implementacoes.TADLista.vetor;

import atividades.implementacoes.TADLista.ETADLista;
import atividades.implementacoes.TADLista.ITADLista;

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
        if (r >= this.size() || r < 0) throw new ETADLista("Colocação invalida.");
    }
    private void excecaoVazia(){
        if (this.isEmpty())  throw new ETADLista("Não existe nenhum elemento.");
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

        if (p == 0) throw new ETADLista("Não existe nenhum elemento antes.");

        return this.vetor[p - 1];
    }
    public Object after(int p) { 
        this.excecaoVazia();

        this.colocacaoInvalidaVazia(p);

        if (p == this.size() - 1)  throw new ETADLista("Não existe nenhum elemento depois.");

        return this.vetor[p + 1];
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
        if (n > this.size() || n < 0) throw new ETADLista("Colocação invalida.");

        if (this.size() + 1 >= this.vetor.length) redimensionar(true);
        
        deslocarDireita(n);

        this.tam++;
        this.vetor[n] = o;
    } 
    public void insertAfter(int n, Object o) {
        if (this.size() == 0) insertFirst(o);
        else{
            if (n > this.size() || n < 0) throw new ETADLista("Colocação invalida.");

            if (this.size() + 1 >= this.vetor.length) redimensionar(true);

            deslocarDireita(n + 1);

            this.tam++;
            this.vetor[n + 1] = o;
        }     
    }
    public void insertFirst(Object o) {
        if (this.size() + 1 >= this.vetor.length) redimensionar(true);

        deslocarDireita(0);
        this.tam++;
        this.vetor[0] = o;
    }
    public void insertLast(Object o) {
        if (this.size() + 1 >= this.vetor.length) redimensionar(true);

        this.vetor[this.size()] = o;
        this.tam++;
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
```

## Implementação em lista duplamente ligada
```java
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

        if (n != q){
            No noN = this.noAtRank(n);
            No noQ = this.noAtRank(q);

            Object temp = noN.getValor();
            noN.setValor(noQ.getValor());
            
            noQ.setValor(temp);
        }
    }
    public void insertBefore(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADLista("Colocação invalida.");

        if (this.size() == 0) this.insertFirst(o);
        else{
            No no = new No(o);
            No noExistente = this.noAtRank(n);
            no.setProximo(noExistente);
            no.setAnterior(noExistente.getAnterior());

            noExistente.getAnterior().setProximo(no);
            noExistente.setAnterior(no);

            this.tam++;
        }        
    } 
    public void insertAfter(int n, Object o) {
        if (n > this.size() || n < 0) throw new ETADLista("Colocação invalida.");

        if (this.size() == 0) this.insertFirst(o);
        else{
            No no = new No(o);
            No noExistente = this.noAtRank(n);
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

        No no = this.noAtRank(n);;
        no.getAnterior().setProximo(no.getProximo());
        no.getProximo().setAnterior(no.getAnterior());

        this.tam--;
        return no.getValor();   
    }
}
```

## Teste
```java
package atividades.implementacoes.TADLista;

import java.util.Scanner;

import atividades.implementacoes.TADLista.ligada.TADListaLigada;
import atividades.implementacoes.TADLista.vetor.TADListaArray;

public class TesteTADLista {
    public static void main(String[] args) {

        ITADLista lista = escolhaTeste();

        System.out.println("-----------------");

        testarIsEmpty(lista);
        testarInsercoes(lista);
        testarFirstLast(lista);
        testarBeforeAfter(lista);
        testarReplace(lista);
        testarSwap(lista);
        testarRemove(lista);
        testarExcecoes(lista);

        System.out.println("-----------------");
    }

    private static ITADLista escolhaTeste(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Opções:\n1 = Array\n2 = Ligada");

        while (true){
            System.out.print("Digite aqui: ");
            try {
                int op = sc.nextInt();
                if (op == 1) return new TADListaArray();
                else if (op == 2) return new TADListaLigada();

                System.out.println("Digite uma opção válida.");
            } catch (Exception e){
                System.out.println("Entrada inválida.");
                sc.next();
            }
        }
    }

    private static void verifica(Object esperado, Object obtido, String msg){
        if ((esperado == null && obtido != null) || (esperado != null && !esperado.equals(obtido))) {

            System.out.println("ERRO: " + msg);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido: " + obtido);
        }
    }

    private static void testarIsEmpty(ITADLista l){
        System.out.println("- Testando isEmpty");

        if (!l.isEmpty())
            System.out.println("ERRO: Lista deveria iniciar vazia");
    }

    private static void testarInsercoes(ITADLista l){
        System.out.println("- Testando inserções");

        l.insertFirst(1);   // [1]
        l.insertLast(2);    // [1,2]
        l.insertAfter(0, 3); // [1,3,2]
        l.insertBefore(0, 0); // [0,1,3,2]

        verifica(4, l.size(), "Tamanho após inserções");
    }

    private static void testarFirstLast(ITADLista l){
        System.out.println("- Testando first e last");

        verifica(0, l.first(), "Primeiro elemento");
        verifica(2, l.last(), "Último elemento");
    }

    private static void testarBeforeAfter(ITADLista l){
        System.out.println("- Testando before e after");

        verifica(1, l.after(0), "Elemento após posição 0");
        verifica(3, l.after(1), "Elemento após posição 1");
        verifica(1, l.before(2), "Elemento antes da posição 2");
    }

    private static void testarReplace(ITADLista l){
        System.out.println("- Testando replaceElement");

        Object antigo = l.replaceElement(1, 99); // [0,99,3,2]

        verifica(1, antigo, "Valor antigo");
        verifica(99, l.after(0), "Valor substituído");
    }

    private static void testarSwap(ITADLista l){
        System.out.println("- Testando swapElements");

        l.swapElements(0, 3); // troca 0 com 2 -> [2,99,3,0]

        verifica(2, l.first(), "Swap posição 0");
        verifica(0, l.last(), "Swap posição 3");
    }

    private static void testarRemove(ITADLista l){
        System.out.println("- Testando remove");

        Object removido = l.remove(1); // remove 99

        verifica(99, removido, "Elemento removido");
        verifica(3, l.size(), "Tamanho após remoção");
    }

    private static void testarExcecoes(ITADLista l){
        System.out.println("- Testando exceções");

        try {
            l.before(0);
            System.out.println("ERRO: before deveria falhar");
        } catch (Exception e) {}

        try {
            l.after(100);
            System.out.println("ERRO: after inválido");
        } catch (Exception e) {}

        try {
            l.remove(100);
            System.out.println("ERRO: remove inválido");
        } catch (Exception e) {}

        try {
            l.replaceElement(100, 5);
            System.out.println("ERRO: replace inválido");
        } catch (Exception e) {}

        try {
            l.swapElements(0, 100);
            System.out.println("ERRO: swap inválido");
        } catch (Exception e) {}
    }
}
```