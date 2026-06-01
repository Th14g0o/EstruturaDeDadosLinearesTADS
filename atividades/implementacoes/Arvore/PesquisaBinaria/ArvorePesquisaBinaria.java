package atividades.implementacoes.Arvore.PesquisaBinaria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArvorePesquisaBinaria {
    private No raiz;
    private int size;

    public ArvorePesquisaBinaria(int o){
        this.raiz = new No(null, null, null, o);
        this.size = 1;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        if (this.size == 0){ 
            return true; 
        } 
        return false;
    }

    public int depht(No v){
        if (isRoot(v)){
            return 0;
        }
        return 1+depht(parent(v));
    }

    public int heigth(No r, No v){
        if (ArvorePesquisaBinaria.isExternal(v)){
            return 0;
        }
        else{
            int h = 0;
            Iterator<No> f = ArvorePesquisaBinaria.children(v);
            while (f.hasNext()){
                No w = f.next();
                h = Math.max(h, heigth(this.raiz, w));
            }
            return 1+h;
        }
    }

    public static Iterator<No> children(No v){
        List<No> filhos = new ArrayList<>();
        if (v.getFilhoEsquerdo() != null){
            filhos.add(v.getFilhoEsquerdo());
        }
        if (v.getFilhoDireito() != null){
            filhos.add(v.getFilhoDireito());
        }
        return filhos.iterator();
    }

    public static boolean isExternal(No v){
        if (v.getFilhoEsquerdo() == null && v.getFilhoDireito() == null){
            return true;
        }
        return false;
    }

    public static boolean isInternal(No v){
        if (v.getFilhoEsquerdo() != null || v.getFilhoDireito() != null){
            return true;
        }
        return false;
    }

    public boolean isRoot(No v){
        if (v.getPai() == null){
            return true;
        }
        return false;
    }

    public No root(){
        return this.raiz;
    }

    public static No leftChild(No v){
        return v.getFilhoEsquerdo();
    }

    public static No rightChild(No v){
        return v.getFilhoDireito();
    }

    public No parent(No v){
        return v.getPai();
    }

    public void inOrder(No v){
        if (isInternal(v)){
            inOrder(leftChild(v));
        } visite(v);
        if (isInternal(v)){
            inOrder(rightChild(v));
        } visite(v);
    }

    public void visite(No v){
        // método que vai imprimir a árvore no console
    }


    public No search(int k, No v){
        if (ArvorePesquisaBinaria.isExternal(v)){
            return v;
        }
        if (k < v.getElemento()){
            return search(k, ArvorePesquisaBinaria.leftChild(v));
        }
        else if (k == v.getElemento()){
            return v;
        }
        else{
            return search(k, ArvorePesquisaBinaria.rightChild(v));
        }
    }

    public void insert(int k, No v){
        // tentar usar search
    } 
}
