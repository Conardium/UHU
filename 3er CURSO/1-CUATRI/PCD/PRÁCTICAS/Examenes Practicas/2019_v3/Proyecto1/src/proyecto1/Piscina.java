
package proyecto1;

/**
 *
 * @author ismae
 */
public class Piscina {
    
    private int huecosLibres = 5;
    
    //Para los niños-----------
    private int adultosEsperando = 0;
    private int adultosEnLaPiscina = 0;
    
    //EntrAdulto
    public synchronized void EntraAdulto() throws InterruptedException{        
        adultosEsperando++;
        while(huecosLibres == 0){
            System.out.println("\tLa piscina está llena, el ADULTO se espera");
            wait();
        }
        adultosEsperando--;
        adultosEnLaPiscina++;
        huecosLibres--;
        
        System.out.println("\t\t-----> El Adulto " + Thread.currentThread().getName() + " entra en la piscina; Libres= " + huecosLibres);
    }
    
    //SaleAdulto
    public synchronized void SaleAdulto(){
        adultosEnLaPiscina--;
        huecosLibres++;
        System.out.println("\t\t\t<------El Adulto " + Thread.currentThread().getName() + " sale de la piscina; Libres= " + huecosLibres);
        notifyAll();
    }
    
    //EntraNinyo
    public synchronized boolean EntraNinyo() throws InterruptedException{
        
        if(adultosEnLaPiscina != 0){
            while(huecosLibres < 2 || adultosEsperando > 0){
                wait();
                System.out.println("\tLa piscina está llena o hay Adultos esperando, el NIÑO se espera");
            }
            huecosLibres -= 2;
            System.out.println("\t\t-----> El Niño " + Thread.currentThread().getName() + " entra en la piscina; Libres= " + huecosLibres);
            return true;
        }
        else
            return false;
    }
    
    //SaleNinyo
    public synchronized void SaleNinyo(){
        huecosLibres += 2;
        System.out.println("\t\t\t<------El Niño " + Thread.currentThread().getName() + " sale de la piscina; Libres= " + huecosLibres);
        notifyAll();
    }
}
