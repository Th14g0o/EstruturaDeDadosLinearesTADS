# Atividade fila

## Objetivo

Implementar uma fila simplesmente encadeada

## Ideia

Ter referencia do nó inicial e final e o tamnho para não precisar percorrer.

## Implementações

- Exceção
```java
public class FilaSimplesmenteEncadeadaExcecao extends RuntimeException {
    public FilaSimplesmenteEncadeadaExcecao(String err){
        super(err);
    }
}

```

- Interface
```java
public interface IFila {
    public abstract void enfileirar(Object o);
    public abstract Object desinfileirar() throws FilaSimplesmenteEncadeadaExcecao;
    public abstract int tamanho();
    public abstract boolean taVazio();
    public abstract Object primeiro() throws FilaSimplesmenteEncadeadaExcecao;
    public abstract Object ultimo() throws FilaSimplesmenteEncadeadaExcecao;
} 
```

- Nó
```java
public class No {
    private Object valor = null;
    private No proximo = null;
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
}
```

- Classe
```java
public class FilaSimplesmenteEncadeada implements IFila {
    private No i;
    private No f;
    private int tam;

    public FilaSimplesmenteEncadeada(){
        this.i = null;
        this.f = null;
        this.tam = 0;
    }

    public void enfileirar(Object obj){
        No novo = new No();
        novo.setValor(obj);
        if (this.taVazio()) {
            this.i = novo;
            this.f = novo;
        } else {
            this.f.setProximo(novo);
            this.f = novo;
        }
        this.tam++;
    }

    public Object desinfileirar(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        Object deletado = i.getValor();
        this.i = this.i.getProximo();
        if (this.i == null) this.f = null;
        this.tam--;
        return deletado;
    }

    public Object primeiro(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        return this.i.getValor();
    }

    public Object ultimo(){
        if (this.taVazio()) throw new FilaSimplesmenteEncadeadaExcecao("A fila simplesmente encadeada está vazia");
        return this.f.getValor();
    }

    public int tamanho(){
        return this.tam;
    }

    public boolean taVazio(){
        return this.i == null && this.f == null;
    }



}
```
