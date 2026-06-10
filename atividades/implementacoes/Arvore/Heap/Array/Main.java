package atividades.implementacoes.Arvore.Heap.Array;

public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap(5);
        System.out.println("--------------------");
        System.out.println("Inserindo:");

        heap.insert(10);
        System.out.println(heap);

        heap.insert(5);
        System.out.println(heap);

        heap.insert(20);
        System.out.println(heap);

        heap.insert(1);
        System.out.println(heap);

        heap.insert(8);
        System.out.println(heap);
        
        System.out.println("--------------------");
        System.out.println("Removendo:");

        while (true) {
            try {
                int removido = heap.remove();
                System.out.println("Removido: " + removido);
                System.out.println(heap);
            } catch (RuntimeException e) {
                System.out.println("Heap vazio.");
                break;
            }
        }
    }
}