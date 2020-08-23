/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09_ada;

/**
 *
 *
 */
public class Objeto {
    private int peso;
    private int valor;
    private int elegido;
    public Objeto(int p, int v) {
        peso = p;
        valor = v;
        elegido = 0;
    }
    public void setPeso(int p){
        peso = p;
    }
    public int getPeso(){
        return peso;
    }
    public void setValor(int v){
        valor = v;
    }
    public int getValor(){
        return valor;
    }
    public void setElegido(int e){
         elegido = e;
    }
    public int getElegido(){
        return elegido;
    }
}
