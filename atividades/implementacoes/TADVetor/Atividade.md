# Implementação TADVetor

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
package atividades.implementacoes.TADVetor;

public interface ITADVetor {
    public Object elemAtRank(int r);
    public Object replaceAtRank(int r, Object o);
    public void insertAtRank(int r, Object o);
    public Object removeAtRank(int r);
    public int size();
    public boolean isEmpty();
}
```

## Exceção
```java
package atividades.implementacoes.TADVetor;

public class ETADVetor extends RuntimeException {
    public ETADVetor(String err){
        super(err);
    }
}
```

## Implementação em Array
```java
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
```

## Implementação em lista duplamente ligada
```java
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
        this.calda.setAnterior(this.cabeca);
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

        No novo = new No(o);

        No noExistente;

        if (r == this.size()) {
            noExistente = this.calda;
        } else {
            noExistente = this.noAtRank(r);
        }

        novo.setProximo(noExistente);
        novo.setAnterior(noExistente.getAnterior());

        noExistente.getAnterior().setProximo(novo);
        noExistente.setAnterior(novo);
        
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
```
## Teste
```java
package atividades.implementacoes.TADVetor;

import atividades.implementacoes.TADVetor.ligada.TADVetorLigado;
import atividades.implementacoes.TADVetor.vetor.TADVetorArrray;
import java.util.Scanner;

public class TesteTADVetor {
    public static void main(String[] args) {

        ITADVetor vetor = escolhaTeste(); 

        System.out.println("-----------------");

        testarIsEmpty(vetor);
        testarInsert(vetor);
        testarElemAtRank(vetor);
        testarReplace(vetor);
        testarRemove(vetor);
        testarExcecoes(vetor);

        System.out.println("-----------------");
    }

    private static ITADVetor escolhaTeste(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Opções:\n1 = Array\n2 = Ligada");
        int opFinal = 0;
        while (opFinal == 0){
            System.out.println("Digite aqui:");
            try {
                int op = sc.nextInt();
                if (op == 1) return new TADVetorArrray();
                else if (op == 2)  {
                    sc.close();
                    return new TADVetorLigado();
                }
                else  System.out.println("Digite uma opção valida.");
            } 
            catch (Exception e) {
               System.out.println("Digite uma opção valida.");
            }
        }
        sc.close();
        return new TADVetorArrray();
    }

    private static void verificaIgualdade(Object esperado, Object obtido, String msg) {
        if ((esperado == null && obtido != null) || (esperado != null && !esperado.equals(obtido))) {
            System.out.println("ERRO: " + msg);
            System.out.println("ESPERADO: " + esperado);
            System.out.println("OBTIDO: " + obtido);
        }
    }

    private static void testarIsEmpty(ITADVetor v) {
        System.out.println("- Testando isEmpty");
        if (!v.isEmpty()) System.out.println("ERRO: Vetor deveria iniciar vazio");
    }

    private static void testarInsert(ITADVetor v) {
        System.out.println("- Testando insertAtRank");

        v.insertAtRank(0, 1);
        verificaIgualdade(1, v.size(), "Tamanho após inserção do 1");

        v.insertAtRank(1, 2);
        verificaIgualdade(2, v.size(), "Tamanho após inserção do 2");

        v.insertAtRank(1, 3); // 1 3 2
        verificaIgualdade(3, v.size(), "Tamanho após inserções");

        verificaIgualdade(1, v.elemAtRank(0), "Elemento na posição 0");
        verificaIgualdade(3, v.elemAtRank(1), "Elemento na posição 1");
        verificaIgualdade(2, v.elemAtRank(2), "Elemento na posição 2");
    }

    private static void testarElemAtRank(ITADVetor v) {
        System.out.println("- Testando elemAtRank");

        verificaIgualdade(1, v.elemAtRank(0), "elemAtRank 0");
        verificaIgualdade(3, v.elemAtRank(1), "elemAtRank 1");
        verificaIgualdade(2, v.elemAtRank(2), "elemAtRank 2");
    }

    private static void testarReplace(ITADVetor v) {
        System.out.println("- Testando replaceAtRank");

        Object antigo = v.replaceAtRank(1, 4); // 1 4 2

        verificaIgualdade(3, antigo, "Valor antigo retornado");
        verificaIgualdade(4, v.elemAtRank(1), "Valor substituído corretamente");
    }

    private static void testarRemove(ITADVetor v) {
        System.out.println("- Testando removeAtRank");

        Object removido = v.removeAtRank(1); // remove 4 -> 1 2

        verificaIgualdade(4, removido, "Elemento removido correto");
        verificaIgualdade(2, v.size(), "Tamanho após remoção");
        verificaIgualdade(1, v.elemAtRank(0), "Elemento 0 após remoção");
        verificaIgualdade(2, v.elemAtRank(1), "Elemento 1 após remoção");
    }

    private static void testarExcecoes(ITADVetor v) {
        System.out.println("- Testando exceções");

        try {
            v.elemAtRank(10);
            System.out.println("ERRO: Não lançou exceção para índice inválido");
        } catch (Exception e) {}

         try {
            v.replaceAtRank(10, 5);
            System.out.println("ERRO: Não lançou exceção em replace inválido");
        } catch (Exception e) {}

        try {
            v.insertAtRank(10, "Z");
            System.out.println("ERRO: Não lançou exceção em insert inválido");
        } catch (Exception e) {}

        try {
            v.removeAtRank(10);
            System.out.println("ERRO: Não lançou exceção em remove inválido");
        } catch (Exception e) {}
    }
}
```