package aulas.aula01;

public class PilhaArray implements Pilha {
    private int capacidade;
    private Object[] a;
    private int t;  // representa indice do ultimo elemento valido
    private int FC; // Fator de Crescimento, 0 = duplicação, qualquer valor diferente vai ser incremental

    public PilhaArray(int capacidade, int crescimento) {
        this.capacidade = capacidade;
        t = -1;
        FC = crescimento;
        if (crescimento <= 0) // apenas uma validação para garantir que seja valido o crescimento
            FC = 0;
        a = new Object[capacidade];
    }

    public void push(Object o) {
        if (t >= capacidade - 1) {
            if (FC == 0)
                capacidade *= 2;
            else
                capacidade += FC;
            Object b[] = new Object[capacidade];
            for (int f = 0; f < a.length; f++)
                b[f] = a[f];
            a = b;
        }
        a[++t] = o;
        // a++; so atibui no final da operação, no caso acima a mudança so ia ocorrer apos a atribuição
        // ++a; atribui no inicio da operação, no caso acima a mudança ocorre antes da atibuição
    }

    public Object pop() throws PilhaVaziaExcecao {
        if(isEmpty())
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        Object r=a[t--];
        return r;
    }

    public Object top() throws PilhaVaziaExcecao {
        if(isEmpty())
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        return a[t];
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public int size() {
        return t + 1;
    }
}