package atividades.implementacoes.Arvore;

import java.util.Iterator;

public interface IArvore {
	/** Retorna a raiz da �rvore */
	No root();
	/** Retorna o No pai de um No */
	No parent(No v);

	/** retorna os filhos de um No */
	Iterator children(No v);
	/** Testa se um No � interno */
	boolean isInternal(No v);
	/** Testa se um No � externo*/
	boolean isExternal(No v);
	/** Testa se um No � a raiz */
	boolean isRoot(No v);
	/** Adiciona um filho a um No */
	void addChild(No v, Object o);
	/** Remove um No
	 *  S� pode remover Nos externos e que tenham um pai (n�o seja raiz)
	*/
	Object remove(No v) throws ;
	/** Troca dois elementos de posi��o */
	void swapElements(No v, No w);
	/** Retorna a profundidade de um No */
	int depth(No v);
	int profundidade(No v);
	/** Retorna a altura da �rvore */
	int height();
	/** Retorna um iterator com os elementos armazenados na �rvore */
	Iterator elements();
	/** Retorna um iterator com as posi��es (Nos) da �rvore */
	Iterator Nos();
	/** Retorna o n�mero de Nos da �rvore
	 */
	int size();
	/** Retorna se a �vore est� vazia. Sempre vai ser falso, pois n�o permitimos remover a raiz
	 */
	boolean isEmpty();
	Object replace(No v, Object o);
	/* In�cio da classe aninhada para armazenar o No*/
	
}
