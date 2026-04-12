package projetos.projeto01;

public class FilaReversao implements IFilaReversao{
    private int i = 0, f = 0, capacidade;
    private Object elementos[];
    private boolean invertido = false;

    public FilaReversao(int capacidade){
        elementos=new Object[capacidade];
        this.capacidade=capacidade;
    }

    public void enfileirar(Object o) {
        diminuirAumentar(false);

        elementos[f] = o;
        f = (f + 1) % capacidade;
    }

    private void diminuirAumentar(boolean diminuir){
        if (diminuir ? (this.tamanho() <= capacidade/3) : (tamanho() + 1 == capacidade)) {
            int novoTam;
            novoTam = diminuir ? capacidade / 2 : capacidade * 2;

            Object[] b = new Object[novoTam];
            int ii = i;

            for (int ff = 0; ff < tamanho(); ff++) {
                b[ff] = elementos[ii];
                ii = (ii + 1) % capacidade;
            }

            f = tamanho();
            i = 0; 
            capacidade = novoTam;
            elementos = b;
        }
    }

    public Object desinfileirar(){
        if(taVazio())
            throw new FilaExcecao("A Fila está vazia");

        Object temp;
        if (invertido){
            temp=elementos[f-1];
            f=(f-1) % capacidade;
        }
        else{
            temp=elementos[i];
            i=(i+1) % capacidade;
        }

        diminuirAumentar(true);
        return temp;
    }

     public Object primeiro(){
        if(taVazio())
            throw new FilaExcecao("A Fila está vazia");
        return elementos[this.invertido ? f - 1 : i];
    }

    public int tamanho() {
        return (capacidade - i + f) % capacidade;
    }

    public boolean taVazio() {
        return f == i;
    }

    public void reverse(){
        invertido = !invertido;
    }

}
