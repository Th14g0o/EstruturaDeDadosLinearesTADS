# Arvore

## O que é uma árvore

- Em Computação, é um modelo abstrato de uma estrutura hierárquica

- Uma árvore consiste de nós com uma relação pai-filho

## Motivação

- Uma das estrutura de dados não-lineares mais importantes da computação.

- Diversas aplicações necessitam de estruturas mais complexas que as listas estudadas até agora, como listas e filas.

- Diversos problemas podem ser modelados através de árvores.

- Permitem uma gama de algoritmos muito mais rápidos do que no uso de estruturas de dados lineares, tais como listas.

## Aplicações

- Relação de hierarquia entre classes Java e .Net Sistemas de arquivos

- Representação de documentos (livro, capítulo, ...) Respostas a questões sim/não: árvore de decisão

- Expressões aritméticas

- Relacionamento entre tabelas em um banco de dados (grupos, subgrupos, produtos)

## Terminologia de árvore

- Raiz (root): Nó sem pai (A)

- Nó interno: Nó com, pelo menos, um filho (A, B, C, F)

- Nó externo (nó folha): Nó sem filhos(E, I, J, K, G, H, D)

- Ancestral de um nó: pai, avô, bisavô, etc.

- Profundidade de um nó: Número de ancestrais

- Altura de um árvore: Profundidade máxima (3)

- Descendente de um nó: filho, neto, bisneto, etc.

- Sub-árvore: árvore formada por um nó e seus descendentes

## TAD árvore
**Métodos genéricos**:
- `integer size()`: retorna o número de nós da árvore
- `integer height()`: Retorna a altura
- `boolean isEmpty()`: indica se a árvore é vazia
- `Iterator elements()`: retorna um iterador para os elementos da árvore
- `Iterator nos()`: retorna um iterador para os nós da árvore
**Métodos de acesso**:
- `No root()`: retorna o nó raiz
- `No parent(No)`: retorna o nó pai de um nó
- `Iterator children(No)`: retorna um iterador para os filhos de um nó

**Métodos de consulta**:
- `boolean isInternal(No)`: Verifica se o Nó é interno
- `boolean isExternal(No)`: Verifica se o Nó é externo ou folha
- `boolean isRoot(No)`: Verifica se o Nó é Raiz
- `integer depth(No)`: Retorna a profundidade de um No
**Métodos de atualização**:
- `Object replace(No, o)`: Altera o objeto armazenado em um Nó

> [!NOTE]
> Métodos adicionais podem ser definidos pela estrutura que extende/implementa o TAD árvore

### Profundidade

- A profundidade de um nó v pode ser definida recursivamente como:
  - Se v for raiz, então a profundidade é 0
  - Senão, a profundidade é 1 mais a profundidade do pai de v

```
Algoritmo depth(v)
    se (isRoot(v))
        retorne 0
    senão
     retorne 1+depth(parent(v))
```

### Altura

- A altura de um nó v pode ser definida
recursivamente como:
  - Se v for externo, então a altura é 0
  - Senão, a altura é 1 mais a maior altura de um filho de v


**O(n)**
```
Algoritmo altura(v) 
    se (isExternal(v))
        retorne 0
    senão
        h=0
        para cada w em children(v)
        h=max(h,altura(w))
        retorne 1+h
```
**O(n2)**:
```
Algoritmo altura(v)
    h=0
    para cada w em nos()
    se (isExternal(w))
    h=max(h,depth(w))
    retorne
```

### Travessia pré ordem
- Uma travessia visita os nós de numa árvore de uma forma sistemática
- Em uma travessia pré-ordem, um nó é visitado antes de seus descendentes 
- Aplicação: imprimir um documento estruturado
- Enquanto percorre já vai "operando"

```
Algoritmo preOrder(v)
    visite(v)
    para cada filho w de v
    preorder (w)
```

### Travessia pós ordem
- Em uma travessia pós-ordem, um nó é visitado depois de seus descendentes
- Aplicação: Computar o espaço usado por diretórios, subdiretórios e arquivos
- Opera do final pro inicio

```
Algoritmo postOrder(v)
    para cada filho w of v
        postOrder (w)
    visite(v)
```

## Árvore binária

[...]

## Árvore de expressões aritméticas

[...]

## Árvore de decisão

[...]

## Propriedades de AB (BT)

[...]

## TAD Árvore Binaria

[...]

### Travessia em ordem

[...]

### Impressão de expressões aritm.

[...]

### Avaliação de expressões aritm.

[...]

## Estrutura de dados para árvores

[...]

## Estrutura de dados para AB

[...]

## Árvore de pesquisa binária

[...]

### Busca

[...]

## inserção

[...]

### remoção

- Para executar remove(4), procuramos pela chave 4
- Para executar remove (2), procuramos pela chave 2 
- Fazemos um caminhamento em ordem na subárvore direita do nó 2 e até encontrar o primeiro nó
- "o(1) para remover, porem, o(h) pra buscar"
- Remoção de nó com 2 filhos. Remove e substitui pelo menor maior que 2
  - Começo pelo filho direito e vai pegando os filhos a esquerda

> Mundaça só de valor

> o(h) -> o(logN) em balanceadas

#### Desempenho

- Considere um dicionário com n itens, implementado com uma árvore binária de pesquisa de altura h
  - o espaço usado é O(n)
  - métodos find, insert e remove executam em tempo O(h)
- A altura h é O(n) no pior caso e O(log n) no melhor caso