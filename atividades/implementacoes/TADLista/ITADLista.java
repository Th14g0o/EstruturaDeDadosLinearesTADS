package atividades.implementacoes.TADLista;

public interface ITADLista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(Object n); 
    public boolean isLast(Object n);
    public Object first(); 
    public Object last(); 
    public Object before(int p); 
    public Object after(int p); 

    public Object replaceElement(int n, Object o); 
    public void swapElements(int n, int q); 
    public void insertBefore(int n, Object o); 
    public void insertAfter(int n, Object o); 
    public void insertFirst(Object o); 
    public void insertLast(Object o); 
    public Object remove(int n);
}
