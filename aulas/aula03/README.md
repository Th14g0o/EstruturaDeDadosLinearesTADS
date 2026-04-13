# Vetor, Lista e Sequência

## O Tad Vetor

Extende a noção de arranjo(array) armazenando sequências de objetos arbitrários.

Um elemento pode ser acessado, inserido ou removido através da especificação de sua **colocação**(rank)

Exceção é disparada se uma colocação incorreta é especificada

> JAVA possui duas classes que fornecem as funcionalidade de vetores: ```java.util.Vector``` e ```java.util.ArrayList```

Principais operações:
- ```object elemAtRank(integer r)```: retorna o elemento na colocação r, sem removê-lo
- ```object replaceAtRank(integer r, object o)```: substitui o elemento na colocação r por o e retorna o antigo elemento
- ```insertAtRank(integer r, object o)```: insere um novo elemento o na colocação r
- ```object removeAtRank(integer r)```: remove e retorna o elemento na colocação r

Operações adicionais
- ```size()```
- ```isEmpty()```

### Vetor baseado em arranjo (array)

**Dados essenciais**:
- Usamos um array ```V``` de tamanho ```N```
- Uma variável ```n``` mantém o tamanho do vetor (número de elementos armazenados)

**Recuperação de elemento**:
- A operação ```elemAtRank(r)``` é implementada em tempo ```O(1)``` para retornar ```V[r]```

**Inserção**:
- Na operação ```insertAtRank(r, o)```, precisamos "arrumar espaço" para o novo elemento deslocando para a direita os ```n - r``` elementos ```V[r]```, ..., ```V[n - 1]```
- No pior caso ```(r = 0)```, esta operação roda em tempo ```O(n)```

**Remoção**:
- Na operação ```removeAtRank(r)```, temos que preencher o "buraco" deixado pelo elemento removido deslocando para a esquerda os ```n - r - 1``` elementos ```V[r + 1]```, ...,``` V[n - 1]```
- No pior caso ```(r = 0)```, esta operação roda em tempo ```O(n)```

### Desempenho

- Em um Vetor implementado com arrays:
  - n O espaço usado pela estrutura de dados é O(n)
  - n size, isEmpty, elemAtRank e replaceAtRank rodam em tempo O(1)
  - n insertAtRank e removeAtRank roda em tempo O(n)
- Se usarmos um array circular, insertAtRank(0,o) e removeAtRank(0) rodam em tempo O(1)
- Na operação insertAtRank, quando o array está cheio, ao invés de disparar uma exceção, podemos substituí-lo por um maior


