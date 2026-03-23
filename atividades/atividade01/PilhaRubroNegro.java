package atividades.atividade01;

public interface PilhaRubroNegro {
    public int size();
    public boolean isEmpty();
    public int capacidade();

    public Object topVermelho() throws PilhaVaziaExcecao;
    public void pushVermelho(Object o);
    public Object popVermelho() throws PilhaVaziaExcecao;

    public Object topPreto() throws PilhaVaziaExcecao;
    public void pushPreto(Object o);
    public Object popPreto() throws PilhaVaziaExcecao;
}
