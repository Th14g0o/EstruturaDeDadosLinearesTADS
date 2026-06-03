package uteis.MDToHTML;

public class Programa {
    public static void main(String[] args) {
        MDToHTMLSimples conversor = new MDToHTMLSimples("atividades/implementacoes/Arvore/Generica/Atividade.md");
        conversor.gerarHTML();
        conversor.imprimir();
    }
}
