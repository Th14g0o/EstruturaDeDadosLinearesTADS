package atividades.atividade02;

public class TesteFila {

    public static void main(String[] args) {

        IFila fila = new FilaSimplesmenteEncadeada(); // sua implementação

        // 1. Teste: desenfileirar fila vazia
        try {
            fila.desinfileirar();
            System.out.println("ERRO: Não lançou exceção ao desenfileirar fila vazia");
        } catch (FilaSimplesmenteEncadeadaExcecao e) {
            System.out.println("OK: Exceção ao desenfileirar fila vazia");
        }

        // 2. Teste: primeiro elemento em fila vazia
        try {
            fila.primeiro();
            System.out.println("ERRO: Não lançou exceção ao acessar primeiro de fila vazia");
        } catch (FilaSimplesmenteEncadeadaExcecao e) {
            System.out.println("OK: Exceção ao acessar primeiro em fila vazia");
        }

        // 3. Teste: fila vazia inicialmente
        if (fila.taVazio()) {
            System.out.println("OK: Fila inicia vazia");
        } else {
            System.out.println("ERRO: Fila não iniciou vazia");
        }

        // 4. Inserindo elementos
        fila.enfileirar(10);
        fila.enfileirar(20);
        fila.enfileirar(30);

        if (fila.tamanho() == 3) {
            System.out.println("OK: Tamanho correto após inserções");
        } else {
            System.out.println("ERRO: Tamanho incorreto após inserções");
        }

        // 5. Teste: ordem FIFO
        try {
            int primeiro = (int) fila.desinfileirar();
            if (primeiro == 10) {
                System.out.println("OK: Ordem FIFO correta");
            } else {
                System.out.println("ERRO: Ordem FIFO incorreta");
            }
        } catch (Exception e) {
            System.out.println("ERRO inesperado: " + e.getMessage());
        }

        // 6. Teste: primeiro sem remover
        try {
            int primeiro = (int) fila.primeiro();
            if (primeiro == 20) {
                System.out.println("OK: Método primeiro() correto");
            } else {
                System.out.println("ERRO: Método primeiro() incorreto");
            }
        } catch (Exception e) {
            System.out.println("ERRO inesperado: " + e.getMessage());
        }

        // 7. Remover todos os elementos
        try {
            fila.desinfileirar(); // 20
            fila.desinfileirar(); // 30
            System.out.println("OK: Remover correto");
        } catch (Exception e) {
            System.out.println("ERRO ao esvaziar fila");
        }

        // 8. Teste: fila deve estar vazia novamente
        if (fila.taVazio()) {
            System.out.println("OK: Fila vazia após remoções");
        } else {
            System.out.println("ERRO: Fila não está vazia após remoções");
        }

        // 9. Teste: inserir null (caso queira testar comportamento)
        try {
            fila.enfileirar(null);
            System.out.println("ATENÇÃO: Fila aceitou null (verifique se isso é desejado)");
        } catch (Exception e) {
            System.out.println("OK: Fila não aceita null");
        }
    }
}