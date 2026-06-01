package atividades.implementacoes.Arvore.Generica;

import java.util.Iterator;

import atividades.implementacoes.Arvore.No;

public class ArvoreGenerica {
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
	public Iterator children(No v)
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
	public Iterator elements()
	{
		return null;
	}
	public Iterator Nos()
	{
		return null;
	}
	public int size()
	{
		return 0;
	}
	public boolean isEmpty()
	{
		return false;
	}
	public Object replace(No v, Object o)
	{
		return null;
	}
}
