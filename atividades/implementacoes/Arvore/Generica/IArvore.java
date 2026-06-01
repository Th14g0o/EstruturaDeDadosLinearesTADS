package atividades.implementacoes.Arvore.Generica;

import java.util.Iterator;

public interface IArvore {
	/** Retorna o número de Nos da árvore */
	int size();
	/** Retorna se a Arvore estA vazia. Sempre vai ser falso, pois não permitimos remover a raiz */
	boolean isEmpty();
	/** Retorna um iterator com os elementos armazenados na árvore */
	Iterator elements();
	/** Retorna um iterator com as posições (Nos) da árvore */
	Iterator Nos();
	// Substitui 
	Object replace(No v, Object o) throws Exception;
	/** Retorna a raiz da �rvore */
	No root() throws Exception;
	/** Retorna o No pai de um No */
	No parent(No v) throws Exception;
	/** retorna os filhos de um No */
	Iterator children(No v) throws Exception;
	/** Testa se um No � interno */
	boolean isInternal(No v) throws Exception;
	/** Testa se um No � externo*/
	boolean isExternal(No v) throws Exception;
	/** Testa se um No � a raiz */
	boolean isRoot(No v) throws Exception;

	/** Adiciona um No ao ?*/
	void insert(Object o);
	/** Adiciona um filho a um No */
	void addChild(No v, Object o);
	/** Remove um No*  Só pode remover Nos externos e que tenham um pai (não seja raiz)*/
	Object remove(No v) throws Exception;
	/** Troca dois elementos de posição */
	void swapElements(No v, No w);
	/** Retorna a profundidade de um No */
	int depth(No v);
	/** Retorna a altura da Árvore */
	int height();	
}
