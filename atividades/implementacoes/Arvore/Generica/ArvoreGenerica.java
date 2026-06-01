package atividades.implementacoes.Arvore.Generica;

import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreGenerica implements IArvore {
	No raiz;
	int tamanho;

	public ArvoreGenerica(Object o)
	{
		raiz = new No(null, o);
		tamanho = 1;
	}
	public No root()
	{
		return raiz;
	}
	public No parent(No v)
	{
		return (v.parent());
	}
	public Iterator<No> children(No v)
	{
		return v.children();
	}
	public boolean isInternal(No v)
	{
		return (v.childrenNumber() > 0);
	}
	public boolean isExternal(No v)
	{
		return (v.childrenNumber() == 0);
	}
	public boolean isRoot(No v)
	{
		return v == raiz;
	}
	public void addChild(No v, Object o)
	{
		No novo = new No(v, o);
		v.addChild(novo);
		tamanho++;
	}
	public Object remove(No v) throws Exception
	{
		No pai = v.parent();
		if (pai != null || isExternal(v))
			pai.removeChild(v);
		else
			throw new Exception();
		Object o = v.element();
		tamanho--;
		return o;
	}
	public void swapElements(No v, No w)
	{
		Object o = v.element();
		v.setElement(w.element());
		w.setElement(o);
		
	}
	public int depth(No v)
	{
		int profundidade = profundidade(v);
		return profundidade;
	}
	private int profundidade(No v)
	{
		if (v == raiz)
			return 0;
		else
			return 1 + profundidade(v.parent());
	}
	public int height()
	{
		int altura = 0;
		return altura;
	}

	private void adicionarElementos(No no, ArrayList<Object> elementos) {
		elementos.add(no.element());
		Iterator<No> it = no.children();
		while (it.hasNext()) {
			adicionarElementos(it.next(), elementos);
		}
	}
	public Iterator<Object> elements() {
		if (isEmpty()) throw new RuntimeException("Árvore vazia");
		ArrayList<Object> elementos = new ArrayList<Object>();
		adicionarElementos(raiz, elementos);
		return elementos.iterator();
	}

	public Iterator<No> Nos()
	{
		return null;
	}
	public int size()
	{
		return tamanho - 1;
	}
	public boolean isEmpty()
	{
		return tamanho == 1;
	}
	public Object replace(No v, Object o)
	{
		Object temp = v.element();
		v.setElement(o);
		return temp;
	}

	public void insert(Object o){
		
	}
}
