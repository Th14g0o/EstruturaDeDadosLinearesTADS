package aulas.aula02;

public class Fila implements IFila {
    private int i = 0, f = 0, N, incremento;
    private Object O[];

    public Fila(int N, int incremento){
        O=new Object[N];
        this.N=N; //Tamanho
        this.incremento=incremento;
    }

    public void enqueue(Object o) {
        if (size() == N - 1) { // encheu coleguinha
            int novoTam;
            if (incremento == 0)
                novoTam = N * 2;
            else
                novoTam = N + incremento;
            Object[] b = new Object[novoTam];

            int ii = i;

            for (int ff = 0; ff < size(); ff++) {

                b[ff] = O[ii];
                ii = (ii + 1) % N;
            }
            f = size(); // definir o novo final
            i = 0; // definir o novo inicio
            N = novoTam;
            O = b;
        }
        O[f] = o;
        f = (f + 1) % N;
    }

    public Object dequeue(){
        if(isEmpty())
            throw new FilaVaziaExececao("A Pilha está vazia");
        Object temp=O[i];
        i=(i+1) % N;
        return temp;
    }

     public Object first(){
        if(isEmpty())
            throw new FilaVaziaExececao("A Pilha está vazia");
        return O[i];
    }

    public int size() {
        return (N - i + f) % N;
    }

    public boolean isEmpty() {
        return f == i;
    }
}
