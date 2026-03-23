package atividades.atividade01;

public class TestePilhaRubroNegro {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTES ===");

        PilhaRubroNegro pilha = criarInstancia();

        testarPilhaVazia(pilha);
        testarPushPop(pilha);
        testarTop(pilha);
        testarCrescimento(pilha);
        testarReducao(pilha);

        System.out.println("=== TESTES FINALIZADOS ===");
    }

    private static PilhaRubroNegro criarInstancia() {
        try {
            return new PilhaRubroNegroArray(1);
        } catch (Exception e) {
            throw new RuntimeException("ERRO: Não foi possível instanciar PilhaRuboNegroArray.", e);
        }
    }

    private static void testarPilhaVazia(PilhaRubroNegro p) {
        System.out.println("\n[TESTE] Pilha vazia");

        if (!p.isEmpty()) {
            System.out.println("ERRO: isEmpty() devia retornar true");
        } else {
            System.out.println("isEmpty() OK");
        }

        if (p.size() != 0) {
            System.out.println("ERRO: size() deveria ser 0");
        } else {
            System.out.println("size() OK");
        }

        try {
            p.popVermelho();
            System.out.println("ERRO: popVermelho() deveria lançar exceção");
        } catch (Exception e) {
            System.out.println("popVermelho() lançou exceção corretamente");
        }

        try {
            p.popPreto();
            System.out.println("ERRO: popPreto() deveria lançar exceção");
        } catch (Exception e) {
            System.out.println("popPreto() lançou exceção corretamente");
        }
    }

    private static void testarPushPop(PilhaRubroNegro p) {
        System.out.println("\n[TESTE] Push/Pop");

        p.pushVermelho(1);
        p.pushVermelho(2);
        p.pushPreto(10);
        p.pushPreto(20);

        if (p.size() != 4) {
            System.out.println("ERRO: size() incorreto após pushes");
        } else {
            System.out.println("size() após push OK");
        }

        try {
            Object obj = p.popVermelho();
            if (!obj.equals(2)) {
                System.out.println("ERRO: popVermelho() não segue LIFO. Valor retirado = " + obj);
            } else {
                System.out.println("popVermelho() OK");
            }

            if (!p.popPreto().equals(20)) {
                System.out.println("ERRO: popPreto() não segue LIFO");
            } else {
                System.out.println("popPreto() OK");
            }

        } catch (Exception e) {
            System.out.println("ERRO inesperado no pop: " + e.getMessage());
        }
    }

    private static void testarTop(PilhaRubroNegro p) {
        System.out.println("\n[TESTE] Top");

        try {
            p.pushVermelho(100);
            if (!p.topVermelho().equals(100)) {
                System.out.println("ERRO: topVermelho() incorreto");
            } else {
                System.out.println("topVermelho() OK");
            }

            p.pushPreto(200);
            if (!p.topPreto().equals(200)) {
                System.out.println("ERRO: topPreto() incorreto");
            } else {
                System.out.println("topPreto() OK");
            }

        } catch (Exception e) {
            System.out.println("ERRO no top: " + e.getMessage());
        }
    }

    private static void testarCrescimento(PilhaRubroNegro p) {
        System.out.println("\n[TESTE] Crescimento do array");

        PilhaRubroNegroArray pilha = (PilhaRubroNegroArray) p;

        int capacidadeInicial = pilha.capacidade();

        try {
            for (int i = 0; i < capacidadeInicial + 1; i++) {
                p.pushVermelho(i);
            }

            int novaCapacidade = pilha.capacidade();

            if (novaCapacidade == capacidadeInicial * 2) {
                System.out.println("Crescimento OK (dobrou corretamente)");
            } else {
                System.out.println("ERRO: crescimento incorreto. Esperado: "
                        + (capacidadeInicial * 2) + " | Obtido: " + novaCapacidade);
            }

        } catch (Exception e) {
            System.out.println("ERRO no crescimento: " + e.getMessage());
        }
    }

    private static void testarReducao(PilhaRubroNegro p) {
        System.out.println("\n[TESTE] Redução do array");

        PilhaRubroNegroArray pilha = (PilhaRubroNegroArray) p;

        try {
            for (int i = 0; i < 100; i++) {
                p.pushVermelho(i);
            }

            int capacidadeAntes = pilha.capacidade();

            while (p.size() > capacidadeAntes / 3) {
                p.popVermelho();
            }

            p.popVermelho();

            int capacidadeDepois = pilha.capacidade();

            if (capacidadeDepois == capacidadeAntes / 2) {
                System.out.println("Redução OK (diminuiu corretamente)");
            } else {
                System.out.println("ERRO: redução incorreta. Esperado: "
                        + (capacidadeAntes / 2) + " | Obtido: " + capacidadeDepois);
            }

        } catch (Exception e) {
            System.out.println("ERRO na redução: " + e.getMessage());
        }
    }
}