package projetos.projeto01;

public interface IFilaReversao {
    public abstract void enfileirar(Object o);
    public abstract Object desinfileirar() throws FilaExcecao;
    public abstract int tamanho();
    public abstract boolean taVazio();
    public abstract Object primeiro() throws FilaExcecao;
    public void reverse();
} 
