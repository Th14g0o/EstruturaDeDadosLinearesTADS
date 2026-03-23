package atividades.atividade01;

public class PilhaRubroNegroArray implements PilhaRubroNegro {
    private int capacidade;
    private Object[] a;

    private int tVermelho; // representa indice do ultimo elemento valido, da esquerda para direita
    private int tPreto;    // representa indice do ultimo elemento valido, da direita para esquerda
     
    public PilhaRubroNegroArray(int capacidade) {
        this.capacidade = capacidade;
        tPreto          = -1;
        tVermelho       = -1;
        a               = new Object[capacidade];
    }

    private void aumentar(){
        if (this.size() + 1 >= capacidade) {
            int capacidadeAntiga = capacidade;
            capacidade *= 2;
            Object b[] = new Object[capacidade];
            for (int f = 0; f <= tVermelho; f++)
                b[f] = a[f]; // vermelho
            for (int f = 0; f <= tPreto; f++)
                b[capacidade - 1 - f] = a[capacidadeAntiga - 1 - f]; // preto
            a = b;
        }
    }
    private void diminuir(){
        if (this.size() <= capacidade/3) {
            int capacidadeAntiga = capacidade;
            capacidade /= 2;
            Object b[] = new Object[capacidade];
            for (int f = 0; f <= tVermelho; f++)
                b[f] = a[f]; // vermelho
            for (int f = 0; f <= tPreto; f++)
                b[capacidade - 1 - f] = a[capacidadeAntiga - 1 - f]; // preto
            a = b;
        }
    }

    public void pushVermelho(Object o) {
        this.aumentar();
        a[++tVermelho] = o;
    }

    public Object popVermelho() throws PilhaVaziaExcecao {
        if(isEmpty())
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        Object r=a[tVermelho--];
        this.diminuir();
        return r;
    }

    public Object topVermelho() throws PilhaVaziaExcecao {
        if(isEmpty())
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        return a[tVermelho];
    }

    public void pushPreto(Object o) {
        this.aumentar();
        a[this.capacidade - 1 - (++tPreto)] = o;
    }

    public Object popPreto() throws PilhaVaziaExcecao {
        if(isEmpty())
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        Object r=a[this.capacidade - 1 - (tPreto--)];
        this.diminuir();
        return r;
    }

    public Object topPreto() throws PilhaVaziaExcecao {
        if(isEmpty())
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        return a[this.capacidade - 1 - (tPreto)];
    }

    public boolean isEmpty() {
        return tPreto + tVermelho == -2;
    }

    public int size() { // espaço total que ocupo
        return tPreto + tVermelho + 2;
    }

    public int capacidade(){
        return capacidade;
    }
}