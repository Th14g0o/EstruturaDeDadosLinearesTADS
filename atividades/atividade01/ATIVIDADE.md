# Atividade Pilha

A ideia foi aproveitar a mesma estrutura de pilha feita em sala, pensando apenas em incrementá-la com a lógica das ações da “cor preta”, uma vez que a vermelha parecia se encaixar no que foi feito nas aulas, mantendo a mesma lógica de desempenho.

```java
package atividades.atividade01;

public interface PilhaRubroNegro {
    public int size();
    public boolean isEmpty();
    public int capacidade();

    public Object topVermelho() throws PilhaVaziaExcecao;
    public void pushVermelho(Object o);
    public Object popVermelho() throws PilhaVaziaExcecao;

    public Object topPreto() throws PilhaVaziaExcecao;
    public void pushPreto(Object o);
    public Object popPreto() throws PilhaVaziaExcecao;
}
```

Começando pelos métodos quantitativos, o `size` agora é representado pela quantidade de elementos pretos e vermelhos inseridos. Para manter o controle, criei dois índices indicando o estado atual. Eles possuem progressão semelhante, mas deve existir um cálculo para o preto, pois ele não deve sobrescrever o vermelho, e sim ir para o “lado” direito do vetor.

```java
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
}
```

Definidos os atributos iniciais acima, implementei o `size()`, que agora é dado por `tPreto + 1` somado a `tVermelho + 1`. A pilha está vazia quando a soma de `tPreto` com `tVermelho` é igual a `-2`. O método `capacidade()` apenas retorna a capacidade, pois optei por mantê-la como atributo privado, assim como na pilha feita em sala, mas precisei dela nos testes de incremento e redução do vetor.

```java
public boolean isEmpty() {
    return tPreto + tVermelho == -2;
}

public int size() { // espaço total que ocupo
    return tPreto + tVermelho + 2;
}   

public int capacidade(){
    return capacidade;
}
```

Agora passei a implementar de fato as pilhas “coloridas”. A pilha vermelha, a princípio, segue exatamente a mesma lógica da pilha feita em sala. A ideia foi generalizar o aumento do array para atender tanto a parte vermelha quanto a preta, além da redução no `pop`. Segui a lógica do aumento, com a diferença de que ele deve ser usado antes da inserção, enquanto a redução ocorre após a remoção — por isso as condições são diferentes.

```java
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
```

No método `aumentar`, a principal diferença em relação ao original foi guardar a capacidade anterior. Para definir a posição dos elementos pretos, utilizei `capacidade - 1`, normalizando a capacidade como índice e subtraindo o valor de `tPreto`.

Enquanto o vermelho segue a mesma lógica da pilha vista em sala, a parte preta exigiu um novo laço que percorre e reposiciona os elementos usando a capacidade antiga (para leitura) e a nova (para escrita), garantindo que fiquem no lado direito.

Já o método `diminuir` verifica se o tamanho atual, após a remoção, é menor ou igual a um terço da capacidade, conforme solicitado na atividade. Caso seja, segue a mesma lógica de cópia usada no aumento.

Com isso, implementei os métodos `push` e `pop`. A pilha vermelha permanece igual à implementação original, enquanto a preta adiciona o cálculo de índice, utilizando a capacidade como base para posicionamento.

```java
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
```

Os métodos `top` seguem a mesma lógica do `pop`, porém sem decrementar o índice:

```java
public Object topPreto() throws PilhaVaziaExcecao {
    if(isEmpty())
        throw new PilhaVaziaExcecao("A Pilha está vazia");
    return a[this.capacidade - 1 - (tPreto)];
}

public Object topVermelho() throws PilhaVaziaExcecao {
    if(isEmpty())
        throw new PilhaVaziaExcecao("A Pilha está vazia");
    return a[tVermelho];
}
```

Com isso, finalizei a implementação da pilha “rubro-negra” em array. Em seguida, realizei testes básicos: verifiquei se a pilha estava vazia, se o `size` retornava 0 e se as exceções eram lançadas corretamente. Testei também `push` e `pop` para garantir o comportamento LIFO, além dos métodos `top`. Por fim, validei o crescimento e a redução da capacidade do array.

## Código completo da exceção

```java
package atividades.atividade01;

public class PilhaVaziaExcecao extends RuntimeException {
    public PilhaVaziaExcecao(String err){
        super(err);
    }
}
```

## Código completo da interface

```java
package atividades.atividade01;

public interface PilhaRubroNegro {
    public int size();
    public boolean isEmpty();
    public int capacidade();

    public Object topVermelho() throws PilhaVaziaExcecao;
    public void pushVermelho(Object o);
    public Object popVermelho() throws PilhaVaziaExcecao;

    public Object topPreto() throws PilhaVaziaExcecao;
    public void pushPreto(Object o);
    public Object popPreto() throws PilhaVaziaExcecao;
}
```

## Código completo da implementação em array

```java
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
```