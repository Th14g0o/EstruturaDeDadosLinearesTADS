
# Fila

Segue o esquema FIFO(First in First out).

Inserções são feitas no fim da fila e remoções no início da fila

**Operações principais**:
- ```enqueue(object)```: insere um
elemento no fim da fila
- ```object dequeue()```: remove e
retorna o elemento do início
da fila

**Operações auxiliares**:
- ```object first()```: retorna o elemento do início sem removê-lo
- ```integer size()```: retorna o número de elementos armazenados
- ```boolean isEmpty()```: indica se há elementos na fila 

**Exceções**:
- Tentar remover o ver um elemento do início da fila levanta a exceção ```EFilaVazia```

## Fila baseada em array

- Use um array de tamanho N de forma circular
Duas variáveis mantêm informações do início e fim da fila 
- i índice do elemento do início
- f índice imediatamente após o último elemento
- A posição f no array fica vazia

Usamos o operador módulo (resto da divisão)

```
Algoritmo tamanho()
    retorne (N - i + f) % N

Algoritmo estaVazia()
    return (i = f)
```

- Operação enfileirar levanta uma exceção se o array está cehio
- Esta exceção é dependente da implementação

```
Algoritmo enfileirar(o)
    Se (tamanho() = N - 1)então
        throw EFilaCheia
    senão
        Q[f] <- o
        f <-> (f + 1) % N
```

- Operação desenfileirar levanta uma exceção se a fila está vazia
- Esta exceção é específica do TAD Fila

```
Algoritmo enfileirar(o)
    Se (tamanho() = N - 1)então
        throw EFilaCheia
    senão
        Q[f] <- o
        f <-> (f + 1) % N
```

**Fila crescente baseada em array**

- Em uma operação enqueue(enfileirar), quando o array está cheio, ao invés de levantar uma exceção, podemos substituir o array por um maior
- Similar ao que fizemos com Pilhas
- A operação enfileirar tem tempo de execução amortizado
  - O(n) com estratégia incremental
  - O(1) com estratégia de duplicação


**Interface**

> Requer EFilaVazia

```java
public interface Fila {
    public int tamanho();
    public boolean estaVazia();
    public Object inicio() throws EFilaVazia;
    public void enfileirar(Object o);
    public Object desenfileirar() throws EFilaVazia;
}
```

# Deque

Permite inserir e remover do inicio e do fim, como se fosse uma fila dupla.

**Operações principais**:

- inserirInicio(object):
- object removerInicio():
- inserirFim(object):
- object removerFim():

**Operações auxiliares**:

- object primeiro():
- object ultimo():
- int tamanho():
- boolean estaVazia():

## Lista ligada

- Sequencias de no(que armazena o elemento)
- Tem a referencia do inicio(no inicial)
- Um no tem referencia do proximo
- o ultimo aponta para null

```java
public class No {
    private Object elemento;
    private No proximo;
    public Object getElemento() {
        return elemento;
    }
    public void setElemento(Object o){
        elemento = o;
    }
}
```

## Pilhas com listas ligadas

- Pode-se implementar uma pilha com uma lista ligada
- O elemento do topo é armazenado no primeiro nó da lista
- O espaço usado é O(n) e cada operação roda em tempo O(1)
- Pode-se implementar uma fila com uma lista ligada
  - O elemento do início é o primeiro nó
  - O elemento do fim é o último nó
- O espaço usado é O(n) e cada operação roda em tempo O(1)

**Interface**

interface de uma fila

```java
public interface IFila {
    public abstract void enqueue(Object o);
    public abstract Object dequeue();
    public abstract int size();
    public abstract boolean isEmpty();
}
```

**Classe completa**

```java
public class Fila implements IFila {
    int i = 0, f = 0, N, incremento;
    Object O[];

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

    public int size() {
        return (N - i + f) % N;
    }

    public boolean isEmpty() {
        return f == i;
    }
}
```