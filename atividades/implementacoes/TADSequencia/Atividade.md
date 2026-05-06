# Implementação TADSequencia

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
package atividades.implementacoes.TADSequencia;

import atividades.implementacoes.No;

public interface ITADSequencia {
    public Object elemAtRank(int r);
    public Object replaceAtRank(int r, Object o);
    public void insertAtRank(int r, Object o);
    public Object removeAtRank(int r);
    public int size();
    public boolean isEmpty();

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

    public No atRank(int r);
    public int rankOf(No n);
} 
```

## Exceção
```java
package atividades.implementacoes.TADSequencia;

public class ETADSequencia extends RuntimeException {
    public ETADSequencia(String err){
        super(err);
    }
}
```

## Implementação em lista duplamente ligada
```java
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
```

## Teste
```java
package atividades.implementacoes.TADSequencia;

import atividades.implementacoes.No;
import atividades.implementacoes.TADSequencia.ligada.TADSequenciaLigada;

public class TesteTADSequencia {

    public static void main(String[] args) {

        ITADSequencia seq = new TADSequenciaLigada();;

        System.out.println("-----------------");

        testarIsEmpty(seq);
        testarInsercoes(seq);
        testarFirstLast(seq);
        testarElemAtRank(seq);
        testarBeforeAfter(seq);
        testarReplace(seq);
        testarSwap(seq);
        testarAtRankRankOf(seq);
        testarRemove(seq);
        testarExcecoes(seq);

        System.out.println("-----------------");
    }

    private static void verifica(Object esperado, Object obtido, String msg) {

        if ((esperado == null && obtido != null)
                || (esperado != null && !esperado.equals(obtido))) {

            System.out.println("ERRO: " + msg);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido: " + obtido);
        }
    }

    private static void testarIsEmpty(ITADSequencia s) {

        System.out.println("- Testando isEmpty");

        if (!s.isEmpty())
            System.out.println("ERRO: Sequência deveria iniciar vazia");
    }

    private static void testarInsercoes(ITADSequencia s) {

        System.out.println("- Testando inserções");

        s.insertFirst(1);          // [1]
        s.insertLast(2);           // [1,2]
        s.insertAtRank(1, 5);      // [1,5,2]
        s.insertAfter(0, 3);       // [1,3,5,2]
        s.insertBefore(0, 0);      // [0,1,3,5,2]

        verifica(5, s.size(), "Tamanho após inserções");
    }

    private static void testarFirstLast(ITADSequencia s) {

        System.out.println("- Testando first e last");

        verifica(0, s.first(), "Primeiro elemento");
        verifica(2, s.last(), "Último elemento");
    }

    private static void testarElemAtRank(ITADSequencia s) {

        System.out.println("- Testando elemAtRank");

        verifica(0, s.elemAtRank(0), "Elemento rank 0");
        verifica(1, s.elemAtRank(1), "Elemento rank 1");
        verifica(3, s.elemAtRank(2), "Elemento rank 2");
        verifica(5, s.elemAtRank(3), "Elemento rank 3");
        verifica(2, s.elemAtRank(4), "Elemento rank 4");
    }

    private static void testarBeforeAfter(ITADSequencia s) {

        System.out.println("- Testando before e after");

        verifica(1, s.after(0), "Elemento após posição 0");
        verifica(3, s.after(1), "Elemento após posição 1");

        verifica(1, s.before(2), "Elemento antes da posição 2");
        verifica(5, s.before(4), "Elemento antes da posição 4");
    }

    private static void testarReplace(ITADSequencia s) {

        System.out.println("- Testando replace");

        Object antigo1 = s.replaceAtRank(1, 99); // [0,99,3,5,2]
        verifica(1, antigo1, "replaceAtRank valor antigo");

        Object antigo2 = s.replaceElement(2, 77); // [0,99,77,5,2]
        verifica(3, antigo2, "replaceElement valor antigo");

        verifica(99, s.elemAtRank(1), "Valor substituído rank 1");
        verifica(77, s.elemAtRank(2), "Valor substituído rank 2");
    }

    private static void testarSwap(ITADSequencia s) {

        System.out.println("- Testando swapElements");

        s.swapElements(0, 4); // troca 0 com 2
        // [2,99,77,5,0]

        verifica(2, s.first(), "Swap posição 0");
        verifica(0, s.last(), "Swap posição 4");
    }

    private static void testarAtRankRankOf(ITADSequencia s) {

        System.out.println("- Testando atRank e rankOf");

        No no = s.atRank(2);

        verifica(77, no.getValor(), "Elemento do nó rank 2");

        int rank = s.rankOf(no);

        verifica(2, rank, "Rank do nó");
    }

    private static void testarRemove(ITADSequencia s) {

        System.out.println("- Testando remove");

        Object removido1 = s.removeAtRank(1); // remove 99
        verifica(99, removido1, "removeAtRank");

        Object removido2 = s.remove(1); // remove 77
        verifica(77, removido2, "remove");

        verifica(3, s.size(), "Tamanho após remoções");
    }

    private static void testarExcecoes(ITADSequencia s) {

        System.out.println("- Testando exceções");

        try {
            s.before(0);
            System.out.println("ERRO: before deveria falhar");
        } catch (Exception e) {}

        try {
            s.after(100);
            System.out.println("ERRO: after inválido");
        } catch (Exception e) {}

        try {
            s.elemAtRank(100);
            System.out.println("ERRO: elemAtRank inválido");
        } catch (Exception e) {}

        try {
            s.insertAtRank(100, 10);
            System.out.println("ERRO: insertAtRank inválido");
        } catch (Exception e) {}

        try {
            s.removeAtRank(100);
            System.out.println("ERRO: removeAtRank inválido");
        } catch (Exception e) {}

        try {
            s.remove(100);
            System.out.println("ERRO: remove inválido");
        } catch (Exception e) {}

        try {
            s.replaceAtRank(100, 5);
            System.out.println("ERRO: replaceAtRank inválido");
        } catch (Exception e) {}

        try {
            s.replaceElement(100, 5);
            System.out.println("ERRO: replaceElement inválido");
        } catch (Exception e) {}

        try {
            s.swapElements(0, 100);
            System.out.println("ERRO: swap inválido");
        } catch (Exception e) {}

        try {
            s.atRank(100);
            System.out.println("ERRO: atRank inválido");
        } catch (Exception e) {}
    }
}
```