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
public class Mochila {
    private int capacidad;
    private Objeto obj[];
    private int V[][];
    private boolean E[][];
    public Mochila(int p[], int v[], int cap){
        obj = new Objeto[p.length+1];
        obj[0] = new Objeto(0,0);
        for (int i=1; i<= p.length; i++){
            obj[i]= new Objeto(p[i-1],v[i-1]);
        }
        capacidad = cap;
    }
    //Método aplicando Programación Dinámica
    public int elegirObjetos(){
        int i,p;
        int n; // número de objetos
        n = obj.length-1;
        int m; //capacidad de la mochila
        m = capacidad;
        V = new int[obj.length][capacidad+1];
        E = new boolean[obj.length][capacidad+1];
        for (i=0; i <= n; i++){
            V[i][0]= 0;
            E[i][0]= false;
        }
        for (p=0; p <= m; p++){
            V[0][p] = 0;
            E[0][p] = false;
        }
        for (i=1; i<=n; i++){
            for (p=1;p<=m; p++){
                if (p<obj[i].getPeso()){
                    V[i][p] = V[i-1][p];
                    E[i][p] = false;
                }else{
                    if (V[i-1][p]>V[i-1][p-obj[i].getPeso()]+obj[i].getValor()){
                        V[i][p] = V[i-1][p];
                        E[i][p] = false;
                    }else{
                        V[i][p]=V[i-1][p-obj[i].getPeso()]+ obj[i].getValor();
                        E[i][p] = true;
                    }
                }
            }
        }
        return V[n][m];
    }
    public int max(int x, int y){
        if (x>y)
            return x;
        else
            return y;
    }
    public void Componer(){
        int num;
        int elem = obj.length-1;
        int cap = capacidad;
        obj[0].setElegido(0);
        while (elem!=0 && cap !=0){
            if (E[elem][cap]==true){
                obj[elem].setElegido(1);
                cap = cap - obj[elem].getPeso();
            }
            elem--;
        }
    }
    public int getV(int i, int j){
        return V[i][j];
    }
    public boolean getE(int i, int j){
        return E[i][j];
    }
    public int getValor(int i){
        return obj[i].getValor();
    }
    public int getPeso(int i){
        return obj[i].getPeso();
    }
    public int getElegido(int i){
        return obj[i].getElegido();
    }
}
