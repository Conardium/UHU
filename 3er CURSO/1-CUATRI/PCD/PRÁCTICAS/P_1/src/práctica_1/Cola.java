/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_1;

/**
 *
 * @author ismae
 */
public class Cola implements ICola {
    
    private int head; //Indica el elemento cabecera
    private int tail; //Indica el último elemento
    private int capacidad; //Se usa en el constructor, indica el tamaño de la cola 
    private int numelementos; //Indica cuantos elementos hay actualmente en la cola
    private Object[] datos;
    
    public Cola(int capacidad){
        head = tail = numelementos = 0;
        this.capacidad = capacidad;
        datos = new Object[capacidad];
    }
    
    @Override
    public int GetNum() {return numelementos;}
    
    @Override
    public void Acola(Object elemento) throws Exception{
        if(Colallena())
            throw new Exception("La cola está llena");
        else{
            datos[(tail++)%capacidad] = elemento;
            numelementos++;
        }
    }
    
    @Override
    public Object Desacola() throws Exception{
        if(Colavacia())
            throw  new Exception("La cola está vacía");
        else{
            numelementos--;
            return datos[(head++)%capacidad];
        }
    }
    
    @Override
    public Object Primero() throws Exception{
        if (Colavacia())
            throw new Exception("La cola está vacía");
        else
            return datos[head];
    }
    
    private boolean Colavacia(){
        return numelementos==0;
    }
    
    private boolean Colallena(){
        return numelementos==capacidad;
    }
}
