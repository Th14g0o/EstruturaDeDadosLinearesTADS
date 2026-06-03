# Atividade de implementação de Árvore Genérica

## Nó

```java
import java.util.ArrayList;
import java.util.Iterator;

public class No {
    private Object o;
    private No pai;
    private ArrayList<No> filhos = new ArrayList<No>();
    public No(No pai, Object o)
    {
        this.pai = pai;
        this.o = o;
    }
    public Object element()
    {
        return o;
    }
    public No parent()
    {
        return pai;
    }
    public void setElement(Object o)
    {
        this.o = o;
    }
    public void addChild(No o)
    {
        filhos.add(o);
    }
    public void removeChild(No o)
    {
        filhos.remove(o);
    }
    public int childrenNumber()
    {
        return filhos.size();
    }
    public Iterator<No> children()
    {
        return filhos.iterator();
    }
    @Override
    public String toString(){
        return String.format("Nó: %d", element());
    }
}
```

## Interface

```java
import java.util.Iterator;

public interface IArvore {
	int size();
	boolean isEmpty();
	Iterator<Object> elements();
	Iterator<No> Nos();
	Object replace(No v, Object o) throws Exception;
	No root() throws Exception;
	No parent(No v) throws Exception;
	Iterator<No> children(No v) throws Exception;
	boolean isInternal(No v) throws Exception;
	boolean isExternal(No v) throws Exception;
	boolean isRoot(No v) throws Exception;
	void addChild(No v, Object o);
	Object remove(No v) throws Exception;
	void swapElements(No v, No w);
	int depth(No v);
	int height();	
	void preOrder(No v);
	void posOrder(No v);
}
```

## Classe

```java
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
		if (v == raiz)
			throw new RuntimeException("A raiz não tem pai");
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
		if (pai != null && isExternal(v))
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
		return heightCalc(raiz);
	}

	private int heightCalc(No v){
		if (isExternal(v))
			return 0;
		else{
			int h = 0;
			Iterator<No> filhos = children(v);
			while (filhos.hasNext()) {
				No w = filhos.next();
				h = h > heightCalc(w) ? h :  heightCalc(w);
			}
			return 1 + h;
		}
	}

	private void adicionarElementos(No no, ArrayList<Object> elementos) {
		elementos.add(no.element());
		Iterator<No> filhos = no.children();
		while (filhos.hasNext()) {
			adicionarElementos(filhos.next(), elementos);
		}
	}
	public Iterator<Object> elements() {
		if (isEmpty()) throw new RuntimeException("Árvore vazia");
		ArrayList<Object> elementos = new ArrayList<Object>();
		adicionarElementos(raiz, elementos);
		return elementos.iterator();
	}

	private void adicionarNos(No no, ArrayList<No> nos) {
		nos.add(no);
		Iterator<No> filhos = no.children();
		while (filhos.hasNext()) {
			adicionarNos(filhos.next(), nos);
		}
	}
	public Iterator<No> Nos()
	{
		if (isEmpty()) throw new RuntimeException("Árvore vazia");
		ArrayList<No> nos = new ArrayList<No>();
		adicionarNos(raiz, nos);
		return nos.iterator();
	}

	public int size()
	{
		return tamanho;
	}
	public boolean isEmpty()
	{
		return tamanho == 0;
	}
	public Object replace(No v, Object o)
	{
		Object temp = v.element();
		v.setElement(o);
		return temp;
	}

	public void preOrder(No v){
		System.out.println(v);
		Iterator<No> filhos = v.children();
		while (filhos.hasNext()){
			No w = filhos.next();
			preOrder(w);
		}
	}

	public void posOrder(No v){
		Iterator<No> filhos = v.children();
		while (filhos.hasNext()){
			No w = filhos.next();
			posOrder(w);
		}
		System.out.println(v);
	}
}
```

## Teste

```java
import java.util.Iterator;
    
public class Teste {

    public static void main(String[] args) {
        testar(new ArvoreGenerica(1));
    }

    public static void testar(IArvore arvore) {
        try {
            System.out.println("Verificando raiz");
            No raiz = arvore.root();

            System.out.println("Adicionando 2");
            arvore.addChild(raiz, 2);

            System.out.println("Adicionando 3");
            arvore.addChild(raiz, 3);

            Iterator<No> filhosRaiz = arvore.children(raiz);

            No no2 = filhosRaiz.next();
            No no3 = filhosRaiz.next();

            System.out.println("Adicionando 4");
            arvore.addChild(no2, 4);

            System.out.println("Adicionando 5");
            arvore.addChild(no2, 5);

            Iterator<No> filhos2 = arvore.children(no2);

            No no4 = filhos2.next();
            No no5 = filhos2.next();

            System.out.println("Size");
            System.out.println(arvore.size());

            System.out.println("IsEmpty");
            System.out.println(arvore.isEmpty());

            System.out.println("Parent(4)");
            System.out.println(arvore.parent(no4).element());

            System.out.println("Children(2)");
            Iterator<No> it = arvore.children(no2);
            while (it.hasNext())
                System.out.println(it.next().element());

            System.out.println("IsInternal(2)");
            System.out.println(arvore.isInternal(no2));

            System.out.println("IsExternal(4)");
            System.out.println(arvore.isExternal(no4));

            System.out.println("IsRoot(1)");
            System.out.println(arvore.isRoot(raiz));

            System.out.println("Depth(4)");
            System.out.println(arvore.depth(no4));

            System.out.println("Height");
            System.out.println(arvore.height());

            System.out.println("Replace(3, 7)");
            arvore.replace(no3, 7);

            System.out.println("Swap(2, 7)");
            arvore.swapElements(no2, no3);

            System.out.println("Elements");
            Iterator<Object> elementos = arvore.elements();
            while (elementos.hasNext())
                System.out.println(elementos.next());

            System.out.println("Nos");
            Iterator<No> nos = arvore.Nos();
            while (nos.hasNext())
                System.out.println(nos.next().element());

            System.out.println("PreOrder");
            arvore.preOrder(raiz);
            System.out.println();

            System.out.println("PosOrder");
            arvore.posOrder(raiz);
            System.out.println();

            try {
                System.out.println("Removendo nó interno");
                arvore.remove(no2);
                System.out.println("ERRO: remove permitiu remover nó interno");
            } catch (Exception e) {
                System.out.println("OK");
            }

            try {
                System.out.println("Removendo raiz");
                arvore.remove(raiz);
                System.out.println("ERRO: remove permitiu remover raiz");
            } catch (Exception e) {
                System.out.println("OK");
            }

            System.out.println("Remove(4)");
            arvore.remove(no4);

            System.out.println("Remove(5)");
            arvore.remove(no5);

            System.out.println("Size");
            System.out.println(arvore.size());

            System.out.println("Height");
            System.out.println(arvore.height());

            System.out.println("PreOrder");
            arvore.preOrder(raiz);
            System.out.println();

            System.out.println("Remove(2)");
            arvore.remove(no2);

            System.out.println("Size");
            System.out.println(arvore.size());

            System.out.println("PreOrder");
            arvore.preOrder(raiz);
            System.out.println();

            System.out.println("Root");
            System.out.println(arvore.root().element());

            System.out.println("IsRoot(Root)");
            System.out.println(arvore.isRoot(arvore.root()));
      
            System.out.println("Depth(Root)");
            System.out.println(arvore.depth(raiz));
        
            try {
                System.out.println("Parent(Root)");
                System.out.println(arvore.parent(raiz));
             } catch (Exception e) {
                System.out.println("ERRO Que deu: " + e.getMessage());
            }

            System.out.println("Replace(Root, 100)");
            arvore.replace(raiz, 100);
            System.out.println(arvore.root().element());
        } catch (Exception e) {
            System.out.println("ERRO INESPERADO");
            e.printStackTrace();
        }
    }
}
```