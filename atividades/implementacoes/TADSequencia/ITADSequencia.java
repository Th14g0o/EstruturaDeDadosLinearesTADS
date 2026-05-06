package atividades.implementacoes.TADSequencia;

import atividades.implementacoes.No;

public interface ITADSequencia {
    public Object elemAtRank(int r);
    public Object replaceAtRank(int r, Object o);
    public void insertAtRank(int r, Object o);
    public Object removeAtRank(int r);
    public int size();
    public boolean isEmpty();

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

    public No atRank(int r);
    public int rankOf(No n);
} 