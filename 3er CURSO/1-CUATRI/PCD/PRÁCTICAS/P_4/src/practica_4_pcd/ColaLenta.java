/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_4_pcd;

/**
 *
 * @author ismae
 */
public class ColaLenta implements ICola {
    
    private int head; //Indica el elemento cabecera
    private int tail; //Indica el último elemento
    private int capacidad; //Se usa en el constructor, indica el tamaño de la cola 
    private int numelementos; //Indica cuantos elementos hay actualmente en la cola
    private Object[] datos;
    
    CanvasCola canvas;
    
    public ColaLenta(int capacidad, CanvasCola cv){
        head = tail = numelementos = 0;
        this.capacidad = capacidad;
        datos = new Object[capacidad];
        this.canvas = cv;
    }
    
    @Override
    public int GetNum() {return numelementos;}
    
    @Override
    public synchronized void Acola(Object elemento) throws Exception { //Sincronizado

        int intentos = 0;

        while (Colallena() && (intentos < 3)) {
            wait();
            intentos++;
        }

        if (!Colallena()) { //Si hay hueco
            datos[tail] = elemento;
            Thread.sleep(10);
            tail = (tail + 1) % capacidad;
            Thread.sleep(10);
            numelementos++;

            canvas.avisa("OK");
            canvas.representa(datos, tail, head, numelementos);
            notifyAll();
        } else {
            canvas.avisa("Llena");
            throw new Exception("La cola está LLENA, el Productor se rinde");
        }
    }
    
    @Override
    public synchronized Object Desacola() throws Exception{ //Sincronizado
        
        int intentos = 0;
        
        while (Colavacia() && (intentos < 3))
        {
            wait();
            intentos++;
        }
        
        if(!Colavacia()){
            Object desacolado = datos[head];
            Thread.sleep(10);
            head = (head + 1) % capacidad;
            Thread.sleep(10);
            numelementos--;
            canvas.avisa("OK");
            canvas.representa(datos, tail, head, numelementos);
            notifyAll();
            return desacolado;
        }
        else{
            canvas.avisa("Vacia");
            throw new Exception("La cola está VACIA, el Consumidor se rinde");
        }    
    }
    
    @Override
    public Object Primero() throws Exception{
        if (Colavacia())
            throw new Exception("La cola está vacía, no se puede mostrar el primer elemento");   
        else
            return datos[head];        
    }
    
    private boolean Colavacia(){ return numelementos==0; }
    
    private boolean Colallena(){ return numelementos==capacidad; }
}
