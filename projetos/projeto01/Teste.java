package projetos.projeto01;

public class Teste {
     public static void main(String[] args) {
        FilaReversao fila = new FilaReversao(1);
        try {
            System.out.println("=== TESTE 1: Enfileirar");
            fila.enfileirar(1);
            fila.enfileirar(2);
            fila.enfileirar(3);
            fila.enfileirar(4);

            System.out.println("Tamanho: " + fila.tamanho()); //  4
            System.out.println("Primeiro: " + fila.primeiro()); //  1

            System.out.println("\n=== TESTE 2: Desenfileirar");
            System.out.println(fila.desinfileirar()); // 1
            System.out.println(fila.desinfileirar()); // 2

            System.out.println("Tamanho: " + fila.tamanho()); //  2

            System.out.println("\n=== TESTE 3: Inverter");
            fila.reverse();

            // [3, 4] -> [4, 3]

            System.out.println("Primeiro após inverter: " + fila.primeiro()); // 4

            System.out.println("\n=== TESTE 4: Comportamento após inversão");

            System.out.println(fila.desinfileirar()); // 4
            System.out.println(fila.desinfileirar()); // 3

            System.out.println("Fila vazia? " + fila.taVazio()); // true

            System.out.println("\n=== TESTE 5: Inserir após inversão");

            fila.enfileirar(10);
            fila.enfileirar(20);
            fila.enfileirar(30);

            System.out.println("Primeiro: " + fila.primeiro()); // 39

            System.out.println(fila.desinfileirar());
            System.out.println(fila.desinfileirar());
            System.out.println(fila.desinfileirar());

            System.out.println("Fila vazia? " + fila.taVazio());

            System.out.println("\n=== TESTE 6: Reverter novamente ");

            fila.enfileirar(100);
            fila.enfileirar(200);
            fila.enfileirar(300);

            fila.reverse(); // volta ao normal

            System.out.println(fila.desinfileirar()); // 100
            System.out.println(fila.desinfileirar()); // 200
            System.out.println(fila.desinfileirar()); // 300

            fila.reverse(); // inverte

            fila.enfileirar(100);
            fila.enfileirar(200);
            fila.enfileirar(300);

            System.out.println(fila.desinfileirar()); // 300
            System.out.println(fila.desinfileirar()); // 200
            System.out.println(fila.desinfileirar()); // 100

            fila.reverse(); // volta ao normal

            for (int i = 0; i < 10; i++) fila.enfileirar(i + 1);

            for (int i = 0; i < 5; i++)
                System.out.println(fila.desinfileirar()); // 1, 2, 3,4, 5

            fila.reverse(); // inverte

            for (int i = 0; i < 5; i++) System.out.println(fila.desinfileirar());  // 10, 9 , 8, 7, 6

            System.out.println("\n=== TESTE 7: Exceção");

            try {
                fila.desinfileirar();
            } catch (FilaExcecao e) {
                System.out.println("Exceção capturada corretamente ao desenfileirar vazio.");
            }

            try {
                fila.primeiro();
            } catch (FilaExcecao e) {
                System.out.println("Exceção capturada corretamente ao acessar primeiro de fila vazia.");
            }

            System.out.println("\n=== TESTE FINALIZADO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
