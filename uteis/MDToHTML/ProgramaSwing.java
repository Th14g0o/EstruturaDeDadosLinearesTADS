package uteis.MDToHTML;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ProgramaSwing extends JFrame {

    private JTextField txtTitulo;

    public ProgramaSwing() {
        super("Conversor MD para HTML");

        txtTitulo = new JTextField("", 20);

        JButton btnSelecionar = new JButton("Selecionar arquivo Markdown");

        btnSelecionar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(
                new File(System.getProperty("user.dir"))
            );

            int resultado = chooser.showOpenDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File arquivo = chooser.getSelectedFile();

                try {
                    MDToHTMLSimples conversor =
                        new MDToHTMLSimples(arquivo.getAbsolutePath());

                    String titulo = txtTitulo.getText().trim();

                    if (titulo.isEmpty()) {
                        titulo = arquivo.getName();
                    }

                    conversor.setTitulo(titulo);
                    conversor.gerarHTML();
                    conversor.imprimir();

                    JOptionPane.showMessageDialog(
                        this,
                        "HTML gerado com sucesso!"
                    );

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Erro: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }
            }
        });

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(txtTitulo, BorderLayout.CENTER);
        painel.add(btnSelecionar, BorderLayout.SOUTH);

        add(painel);

        setSize(400, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProgramaSwing().setVisible(true);
        });
    }
}