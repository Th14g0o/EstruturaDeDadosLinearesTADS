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
        
        while (n[colocacaoNovo] < n[colocacaoPai]) {
            int o = n[colocacaoNovo];
            n[colocacaoNovo] = n[colocacaoPai];
            n[colocacaoPai] = o;
            colocacaoNovo = colocacaoPai;
            colocacaoPai = colocacaoPai / 2;
        }
    }
    
    public void insert(int o){
        if (i + 1 == capacidade){
            
        } 
        n[i++] = o;
        upHeap();
    }

    public void downHeap(){
        int atual = 1;

        int filhoEsquerdo = atual * 2;
        int filhoDireito = atual * 2 + 1;

        int menor = n[filhoEsquerdo] < n[filhoDireito] ? filhoEsquerdo : filhoDireito;

        while (n[atual] > n[menor]){
            int valorAtual = n[atual];
            n[atual] = n[menor];
            n[menor] = valorAtual;

            atual = menor;

            filhoEsquerdo = atual * 2;
            filhoDireito = atual * 2 + 1;
            menor = n[filhoEsquerdo] < n[filhoDireito] ? filhoEsquerdo : filhoDireito;
        }
    }

    public int remove(){
        int temp = n[1];
        n[1] = n[i--];
        downHeap();
        return temp;
    }
}
