package uteis.MDToHTML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.awt.Desktop;

public class MDToHTMLSimples {
    private String conteudo;
    private String titulo;

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    private void resetar(){
        this.conteudo = "";
        this.titulo = "Titulo";
    }

    public MDToHTMLSimples(){
        this.resetar();
    }

    public MDToHTMLSimples(String caminho){
        this.resetar();
        this.converterConteudo(caminho);
    }

    public void imprimir() {
        try {
            File file = new File("uteis/MDToHTML/saida.html");
            Desktop.getDesktop().browse(file.toURI());
            System.out.println("Arquivo aberto no navegador para impressão.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerarHTML(){
        this.gerarHTML(this.conteudo);
    }

    private void gerarHTML(String conteudo){
        try {
            String html = Files.readString(Path.of("uteis/MDToHTML/base.html"));
            html = html.replace("{[(conteudo)]}", conteudo);
            html = html.replace("{[(titulo)]}", this.titulo);
            Files.writeString(Path.of("uteis/MDToHTML/saida.html"), html);
            System.out.println("Arquivo gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void converterConteudo(String caminho) {
        try (Scanner scanner = new Scanner(new File(caminho))) {
            boolean emCodigo = false;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (!emCodigo && linha.startsWith("> ")){
                    linha = linha.replaceFirst(
                        ">", 
                        "<div class=\"observacao\">\n" +
                        "    <div class=\"titulo\">Observação</div>\n" +
                        "    <p>\n" 
                    );
                    linha += "    </p>\n</div>";
                }
                if (!emCodigo && linha.startsWith("- ")){
                    linha = linha.replaceFirst("-", "<li>");
                    linha += "</li>";
                }
                else if (!emCodigo && linha.startsWith("### ")){
                    linha = linha.replaceFirst("### ", "<h3>");
                    linha += "</h3>";
                }
                else if (!emCodigo && linha.startsWith("## ")){
                    linha = linha.replaceFirst("## ", "<h2>");
                    linha += "</h2>";
                }
                else if (!emCodigo && linha.startsWith("# ")){
                    linha = linha.replaceFirst("# ", "<h1>");
                    linha += "</h1>";
                }
                else if (linha.startsWith("```")){
                    if (!emCodigo) {
                        String linguagem = linha.substring(3).trim();
                        if (linguagem.isEmpty()) linguagem = "plaintext";
                        linha = "<pre><code class=\"language-" + linguagem + "\">";
                        
                        emCodigo = true;
                    } else {
                        linha = "\n</code></pre>";
                        emCodigo = false;
                    }
                }
                else if (emCodigo) 
                    linha = linha.replace("&", "&amp;")
                                 .replace("<", "&lt;")
                                 .replace(">", "&gt;");
                this.conteudo += linha + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
