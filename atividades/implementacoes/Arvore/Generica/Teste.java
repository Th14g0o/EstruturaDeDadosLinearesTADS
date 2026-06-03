package atividades.implementacoes.Arvore.Generica;

import java.util.Iterator;
    
public class Teste {

    public static void main(String[] args) {
        testar(new ArvoreGenerica(1));
    }

    public static void testar(IArvore arvore) {
        try {
            System.out.println("Verificando raiz");
            No raiz = arvore.root();

            System.out.println("Adicionando 2");
            arvore.addChild(raiz, 2);

            System.out.println("Adicionando 3");
            arvore.addChild(raiz, 3);

            Iterator<No> filhosRaiz = arvore.children(raiz);

            No no2 = filhosRaiz.next();
            No no3 = filhosRaiz.next();

            System.out.println("Adicionando 4");
            arvore.addChild(no2, 4);

            System.out.println("Adicionando 5");
            arvore.addChild(no2, 5);

            Iterator<No> filhos2 = arvore.children(no2);

            No no4 = filhos2.next();
            No no5 = filhos2.next();

            System.out.println("Size");
            System.out.println(arvore.size());

            System.out.println("IsEmpty");
            System.out.println(arvore.isEmpty());

            System.out.println("Parent(4)");
            System.out.println(arvore.parent(no4).element());

            System.out.println("Children(2)");
            Iterator<No> it = arvore.children(no2);
            while (it.hasNext())
                System.out.println(it.next().element());

            System.out.println("IsInternal(2)");
            System.out.println(arvore.isInternal(no2));

            System.out.println("IsExternal(4)");
            System.out.println(arvore.isExternal(no4));

            System.out.println("IsRoot(1)");
            System.out.println(arvore.isRoot(raiz));

            System.out.println("Depth(4)");
            System.out.println(arvore.depth(no4));

            System.out.println("Height");
            System.out.println(arvore.height());

            System.out.println("Replace(3, 7)");
            arvore.replace(no3, 7);

            System.out.println("Swap(2, 7)");
            arvore.swapElements(no2, no3);

            System.out.println("Elements");
            Iterator<Object> elementos = arvore.elements();
            while (elementos.hasNext())
                System.out.println(elementos.next());

            System.out.println("Nos");
            Iterator<No> nos = arvore.Nos();
            while (nos.hasNext())
                System.out.println(nos.next().element());

            System.out.println("PreOrder");
            arvore.preOrder(raiz);
            System.out.println();

            System.out.println("PosOrder");
            arvore.posOrder(raiz);
            System.out.println();

            // ==========================
            // TESTES DE ERRO
            // ==========================

            try {
                System.out.println("Removendo nó interno");
                arvore.remove(no2);
                System.out.println("ERRO: remove permitiu remover nó interno");
            } catch (Exception e) {
                System.out.println("OK");
            }

            try {
                System.out.println("Removendo raiz");
                arvore.remove(raiz);
                System.out.println("ERRO: remove permitiu remover raiz");
            } catch (Exception e) {
                System.out.println("OK");
            }

            // ==========================
            // TESTES DE REMOÇÃO VÁLIDA
            // ==========================

            System.out.println("Remove(4)");
            arvore.remove(no4);

            System.out.println("Remove(5)");
            arvore.remove(no5);

            System.out.println("Size");
            System.out.println(arvore.size());

            System.out.println("Height");
            System.out.println(arvore.height());

            System.out.println("PreOrder");
            arvore.preOrder(raiz);
            System.out.println();

            // Agora 2 virou folha

            System.out.println("Remove(2)");
            arvore.remove(no2);

            System.out.println("Size");
            System.out.println(arvore.size());

            System.out.println("PreOrder");
            arvore.preOrder(raiz);
            System.out.println();

            // ==========================
            // TESTES FINAIS
            // ==========================

            System.out.println("Root");
            System.out.println(arvore.root().element());

            System.out.println("IsRoot(Root)");
            System.out.println(arvore.isRoot(arvore.root()));

        } catch (Exception e) {
            System.out.println("ERRO INESPERADO");
            e.printStackTrace();
        }
    }
}

