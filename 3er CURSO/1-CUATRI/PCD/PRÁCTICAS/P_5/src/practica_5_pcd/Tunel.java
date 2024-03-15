/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_5_pcd;

import java.util.ArrayList;

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
    
    public Tunel(CanvasTunel canvas){
        System.out.println("-------- Tuneles abiertos --------");
        lateralDerchLibre = lateralIzqLibre = centroLibre = 'l'; //Inicialmente todos los tuneles están libres
        this.canvas = canvas;
    }
    
    public synchronized char entraCoche(float id) throws Exception{
        coches.add(id);
        canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
        while(lateralIzqLibre != 'l' && lateralDerchLibre != 'l' && centroLibre != 'l'){
            System.out.println("<<!>>Tuneles llenos - El coche-" + (int)id + " se espera");
            wait();
        }
        
        if (centroLibre == 'l') { //El orden de seleccion del COCHE será centro < izquierda < derecha
            centroLibre = 'c';
            coches.remove(id);
            idCentro = (int)id;
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            return 'c';
        } 
        else if (lateralIzqLibre == 'l') {
            lateralIzqLibre = 'c';
            coches.remove(id);
            idLateralIzq = (int)id;
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            return 'i';
        } 
        else {
            lateralDerchLibre = 'c';
            coches.remove(id);
            idLateralDerch = (int)id;
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            return 'd';
        }
    }
    
    public synchronized void saleCoche(char lugar){
        if (lugar == 'c'){
            centroLibre = 'l';
        }
        else if (lugar == 'i'){
            lateralIzqLibre = 'l';
        }
        else{
            lateralDerchLibre = 'l';
        }
        
        canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
        notifyAll();
    }
    
	
    private char CalcularPosFurgo(){
        
        char posicion = 'n';
        if(lateralIzqLibre == 'l' && centroLibre != 'f'){
            posicion = 'i';
        }
        else if(centroLibre == 'l' && lateralDerchLibre != 'f' && lateralIzqLibre != 'f'){
            posicion = 'c';
        }
        else if(lateralDerchLibre == 'l' && centroLibre != 'f'){
            posicion = 'd';
        }
        
        return posicion;
    }
    
    public synchronized char entraFurgo(float id) throws Exception{
        furgos.add(id);
        char posEntra = CalcularPosFurgo();
        canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
        while(posEntra == 'n') {
            System.out.println("<<!>>Tuneles llenos - La furgo-" + (int)id + " se espera");
            wait();
            posEntra = CalcularPosFurgo();
        }
        
        if (posEntra == 'i') { //El orden de seleccion de la FURGO será izquierda < centro < derecha
            lateralIzqLibre = 'f';
            furgos.remove(id);
            idLateralIzq = (int)id;
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            return 'i';
        } 
        else if (posEntra == 'c') {
            centroLibre = 'f';
            furgos.remove(id);
            idCentro = (int)id;
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            return 'c';
        } 
        else {
            lateralDerchLibre = 'f';
            furgos.remove(id);
            idLateralDerch = (int)id;
            canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
            return 'd';
        }
    }
    
    public synchronized void saleFurgo(char lugar){
        if (lugar == 'c'){
            centroLibre = 'l';
        }
        else if (lugar == 'i'){
            lateralIzqLibre = 'l';
        }
        else{
            lateralDerchLibre = 'l';
        }
        
        canvas.representa(lateralIzqLibre, lateralDerchLibre, centroLibre, coches, furgos, idLateralIzq, idCentro, idLateralDerch);
        notifyAll();
    }
}
