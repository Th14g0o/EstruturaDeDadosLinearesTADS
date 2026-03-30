package aulas.aula02;

public interface IFila {
    public abstract void enqueue(Object o);
    public abstract Object dequeue() throws FilaVaziaExececao;
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract Object first() throws FilaVaziaExececao;
} 
