
package airport_huelva;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Vuelo {
    
    private Aeropuerto Aero; //Instancia Singleton
    
    private String compania;
    private String destino;
    private String NumVuelo;
    private int PuertaEmbarque;
    private String estado;
    private int horaSalida;
    private int horaLlegada;
    
    
    Vuelo(){
        Aero = Aeropuerto.getAeropuerto();
    }
    
    Vuelo(String comp, String dest, String nVuelo, int pEmbarque, String est, int hSalida, int hLlega){
        Aero = Aeropuerto.getAeropuerto();
        compania=comp;
        destino = dest;
        NumVuelo = nVuelo;
        PuertaEmbarque = pEmbarque;
        estado = est;
        horaSalida = hSalida;
        horaLlegada = hLlega;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNumVuelo() {
        return NumVuelo;
    }

    public void setNumVuelo(String NumVuelo) {
        this.NumVuelo = NumVuelo;
    }

    public int getPuertaEmbarque() {
        return PuertaEmbarque;
    }

    public void setPuertaEmbarque(int PuertaEmbarque) {
        this.PuertaEmbarque = PuertaEmbarque;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(int horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    
}
