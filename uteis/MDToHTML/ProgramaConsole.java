package uteis.MDToHTML;

public class ProgramaConsole {
    public static void main(String[] args) {
        MDToHTMLSimples conversor = new MDToHTMLSimples("uteis/MDToHTML/teste.md");
        conversor.setTitulo("Teste");
        conversor.gerarHTML();
        conversor.imprimir();
    }
}
