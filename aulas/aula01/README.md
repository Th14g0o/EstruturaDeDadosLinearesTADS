# Aula Pilha

## Tipos abstratis de dadis(TADs)

Abstração de estrutura

Um TAD especifica:
- Dados
- Metodos
- Execeções

## Pilha

Carrega a ideia de "empilhar" valores, objetos.

Lista onde operações occorem do topo para baixo.

## TAD Pilha

Armazena objetos arbitrarios

inserções e remoções segue o esquema LIFO("LAST IN, FIRST OUT", "O ULTIMO A ENTRAR, O ULTIMO A SAIR")

### Principais operações:

- ```push()```: insere um elemento 
- ```pop()```: remove e retorna o ultimo elemento inserido

### Operações auxiliares:

- ```top()```: retorna o ultimo elemento inserido
- ```size()```: retorna quantidade de elemento
- ```isEmpty()```: indica se existe elemento

### Exeções

```Top``` ou ```pop``` em uma pilha vazia. *Execeção de pilha vazia*

## Aplicações
- Voltar do navegador
- CTRL + Z

## Pilhas baseadas em Array

### Pop

o TAD Guarda o indice do elemento do topo

A posição controla a existencia do valor, o pop subtrai do indice -1 e não apaga o valor, ele deixa, mas, é como se não existice(Isso para evitar comando dinessario, "lista[i] = null").

No inicio do metodo ja verifica se ta vazio, se tiver lança exceção

### Push

se o indice do elemento do topo chegar a lagura lançaremos uma exceção por hora, depois lidaderemos com isso.

## Desempenho e Limitações

### Desempenho
- espaço ```O(n)```
- Operações ```O(1)```

### Limitações

- Tamnho maximo definido
- Colocar valor com lista cheia por causa da implementação em array

## Pilha crescente baseada em array

### Estrategia 1 - Incremental

criar um novo array com maior capacidade aumenta com base en um valor constante, copia os valores do antigo array pro novo e sobrescrevo o original pelo novo

> Problema: Copia é ```O(n)```

### Estrategia 2 - Duplicação

criar um novo array com maior capacidade com o dobro da capacidade do orignal, copia os valores do antigo array pro novo e sobrescrevo o original pelo novo

> Problema: Copia é ```O(n)```

## Media ao inves de BigOh do pior caso

calcular o tempo total(```T(n)```) necessario para realizar uma serie de ```n``` operações push

```T(n)/n```

### Incremental

n = 1000 -> elemenos
c = 100 -> incremento
k = n/100 = 10

n + c + 2c + 3c ... + kc =
n + c(1 +2+3...+k)
n + ck(k+1)/2

que resulta em uma media de O(n), o que não é desejado.


### Duplicação

toda operação vai ser O(n)

a media vai ser O(1)

ele creces exponecialmente então cade vez menos vai ter copias. diferentemente da incremental