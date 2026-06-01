package atividades.implementacoes.Arvore.PesquisaBinaria;

import java.util.ArrayList;
import java.util.Iterator;

public class No {
    private No filhoEsquerdo;
    private No filhoDireito;
    private No pai;
    private int elemento;

    public No(No filhoEsquerdo, No filhoDireito, No pai, int o){
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
        this.pai = pai;
        this.elemento = o;
    }

    public No getFilhoEsquerdo(){
        return this.filhoEsquerdo;
    }

    public No getFilhoDireito(){
        return this.filhoDireito;
    }

    public No getPai(){
        return this.pai;
    }

    public int getElemento(){
        return this.elemento;
    }

    public void setFilhoEsquerdo(No v){
        this.filhoEsquerdo = v;
    }

    public void setFilhoDireito(No v){
        this.filhoDireito = v;
    }

    public void setPai(No v){
        this.pai = v;
    }

    public void setElemento(int o){
        this.elemento = o;
    }

}
