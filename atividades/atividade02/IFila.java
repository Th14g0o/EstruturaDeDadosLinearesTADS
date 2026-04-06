package atividades.atividade02;

public interface IFila {
    public abstract void enfileirar(Object o);
    public abstract Object desinfileirar() throws FilaSimplesmenteEncadeadaExcecao;
    public abstract int tamanho();
    public abstract boolean taVazio();
    public abstract Object primeiro() throws FilaSimplesmenteEncadeadaExcecao;
    public abstract Object ultimo() throws FilaSimplesmenteEncadeadaExcecao;
} 
