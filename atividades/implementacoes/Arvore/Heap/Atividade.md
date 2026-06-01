# Implementação HEAP com Array

## Classe Heap

```java
package atividades.implementacoes.Arvore.Heap;

public class Heap {
    private int n[];
    private int capacidade;
    private int i;

    public Heap(int t){
        capacidade = t + 1;
        n = new int[capacidade];
        i = 1;
    }

    public void upHeap(){
        int colocacaoNovo = i;
        int colocacaoPai = i / 2;
        
        while (colocacaoNovo > 1 && n[colocacaoNovo] < n[colocacaoPai]) {
            int o = n[colocacaoNovo];
            n[colocacaoNovo] = n[colocacaoPai];
            n[colocacaoPai] = o;
            colocacaoNovo = colocacaoPai;
            colocacaoPai = colocacaoPai / 2;
        }
    }
    
    public void insert(int o){
        if (i == capacidade) {
            capacidade *= 2;
            int[] novo = new int[capacidade];
            for (int j = 0; j < i; j++) {
                novo[j] = n[j];
            }
            n = novo;
        }

        n[i] = o;
        upHeap();
        i++;
    }

    public void downHeap(){
        int atual = 1;

        while (atual * 2 < i) {
            int filhoEsquerdo = atual * 2;
            int filhoDireito = atual * 2 + 1;

            int menor = filhoEsquerdo;
            if (filhoDireito < i && n[filhoDireito] < n[filhoEsquerdo]) 
                menor = filhoDireito;

            if (n[atual] <= n[menor]) break;

            int temp = n[atual];
            n[atual] = n[menor];
            n[menor] = temp;

            atual = menor;
        }
    }

    public int remove(){
       if (isEmpty()) {
            throw new RuntimeException("Heap vazio");
        }

        int removido = n[1];

        i--;
        n[1] = n[i];

        if (i > 1) {
            downHeap();
        }

        return removido;
    }

    public boolean isEmpty(){
        return i == 1;
    }

    public int size(){
        return i - 1;
    }
}

```