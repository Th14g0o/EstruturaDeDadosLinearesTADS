package uteis.MDToHTML;

public class Programa {
    public static void main(String[] args) {
        MDToHTMLSimples conversor = new MDToHTMLSimples("atividades/atividade02/ATIVIDADE.md");
        conversor.gerarHTML();
        conversor.imprimir();
    }
}
