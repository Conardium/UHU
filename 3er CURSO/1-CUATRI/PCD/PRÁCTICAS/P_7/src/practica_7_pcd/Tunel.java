/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_7_pcd;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ismae
 */
public class Tunel {
    
    private volatile char lateralIzqLibre, lateralDerchLibre, centroLibre; //l = libre     c=coche     f=furgo 
    private int idLateralIzq, idCentro, idLateralDerch; //Ids de los vehiculos que entran en los tuneles
    private ArrayList<Float> coches = new ArrayList<Float>(); //Cola de espera Coche
    private ArrayList<Float> furgos = new ArrayList<Float>(); //Cola de espera Furgo
    CanvasTunel canvas;
    
    
    //===================== NEW =====================
    int cochesLavandose, furgosLavandose = 0;
    int cochesEnEspera, furgosEnEspera = 0;
    int huecosLibres = 3;
    
    final Lock mutex = new ReentrantLock(true);
    final Condition colaCoches = mutex.newCondition();
    final Condition colaFurgos = mutex.newCondition();
    //===============================================
    
    
    public Tunel(CanvasTunel canvas){
        System.out.println("-------- Tuneles abiertos --------");
        lateralDerchLibre = lateralIzqLibre = centroLibre = 'l'; //Inicialmente todos los tuneles están libres
        this.canvas = canvas;
    }
    
    public char entraCoche(float id) throws Exception{
        mutex.lock();
        try {
            coches.add(id);
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            while (huecosLibres == 0 || cochesLavandose == 2) {
                System.out.println("<<!>>Tuneles llenos - El coche-" + (int) id + " se espera");
                cochesEnEspera++;
                colaCoches.await();
                cochesEnEspera--;
            }
            
            huecosLibres--;
            cochesLavandose++;

            if (centroLibre == 'l') { //El orden de seleccion del COCHE será centro < izquierda < derecha
                centroLibre = 'c';
                coches.remove(id);
                idCentro = (int) id;
                canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
                return 'c';
            } else if (lateralIzqLibre == 'l') {
                lateralIzqLibre = 'c';
                coches.remove(id);
                idLateralIzq = (int) id;
                canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
                return 'i';
            } else {
                lateralDerchLibre = 'c';
                coches.remove(id);
                idLateralDerch = (int) id;
                canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
                return 'd';
            }

        } finally {mutex.unlock();}
    }
    
    public void saleCoche(char lugar){
        mutex.lock();
        try {
            huecosLibres++;
            cochesLavandose--;

            if (lugar == 'c') {
                centroLibre = 'l';
            } else if (lugar == 'i') {
                lateralIzqLibre = 'l';
            } else {
                lateralDerchLibre = 'l';
            }
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            
            if(furgosEnEspera > 0 && (furgosLavandose < 2 || cochesEnEspera == 0)){
                colaFurgos.signal();
            }
            else{
                colaCoches.signal();
            }
            
        } finally {mutex.unlock();}
    }
    
    
    public char entraFurgo(float id) throws Exception{
        mutex.lock();
        try {
            furgos.add(id);
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            while (huecosLibres == 0 || (furgosLavandose == 2 && cochesEnEspera > 0)) {
                System.out.println("<<!>>Tuneles llenos - La furgo-" + (int) id + " se espera");
                furgosEnEspera++;
                colaFurgos.await();
                furgosEnEspera--;
            }

            huecosLibres--;
            furgosLavandose++;
            
            if (lateralIzqLibre == 'l') { //El orden de seleccion de la FURGO será izquierda < centro < derecha
                lateralIzqLibre = 'f';
                furgos.remove(id);
                idLateralIzq = (int) id;
                canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
                return 'i';
            } else if (centroLibre == 'l') {
                centroLibre = 'f';
                furgos.remove(id);
                idCentro = (int) id;
                canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
                return 'c';
            } else {
                lateralDerchLibre = 'f';
                furgos.remove(id);
                idLateralDerch = (int) id;
                canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
                return 'd';
            }
        } finally {mutex.unlock();}
    }
    
    public void saleFurgo(char lugar){
        mutex.lock();
        try {
            huecosLibres++;
            furgosLavandose--;
            
            if (lugar == 'c') {
                centroLibre = 'l';
            } else if (lugar == 'i') {
                lateralIzqLibre = 'l';
            } else {
                lateralDerchLibre = 'l';
            }

            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            
            if(furgosEnEspera == 0 || (cochesEnEspera > 0 && furgosLavandose == 2)){
                colaCoches.signal();
            }
            else{
                colaFurgos.signal();
            }
            
        } finally {mutex.unlock();}
    }
}
