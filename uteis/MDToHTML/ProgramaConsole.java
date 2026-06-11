package uteis.MDToHTML;

public class ProgramaConsole {
    public static void main(String[] args) {
        MDToHTMLSimples conversor = new MDToHTMLSimples("atividades/implementacoes/Arvore/Heap/Array/Atividade.md");
        conversor.setTitulo("Teste");
        conversor.gerarHTML();
        conversor.imprimir();
    }
}
