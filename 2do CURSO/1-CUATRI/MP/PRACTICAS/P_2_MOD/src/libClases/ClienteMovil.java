package libClases;

public class ClienteMovil extends Cliente implements Mezcla {
	private float minutosHablados;
	private Fecha fechaDePermanencia;
	private float precioPorMin;
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, Fecha permanencia, float minutosHabla, float precioMinuto) {
		super(NIF, nom, fNac, fAlta); //En el caso en el que nos den la Fecha de Alta
		fechaDePermanencia=(Fecha)permanencia.clone();
		minutosHablados=minutosHabla;
		precioPorMin=precioMinuto;
	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, float minutosHabla, float precioMinuto) {
		this(NIF, nom, fNac, getFechaPorDefecto(), minutosHabla, precioMinuto); //Sino me dan la fecha de alta, es la fecha por defecto
	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutosHabla, float precioMinuto) {
		this(NIF, nom, fNac, fAlta, new Fecha(fAlta.getDia(),fAlta.getMes(),fAlta.getAnio()+1), minutosHabla, precioMinuto);
	}
	
	public ClienteMovil(ClienteMovil c) { //Constructor de Copia
		super(c);
		this.minutosHablados=c.minutosHablados;
		this.fechaDePermanencia=(Fecha)c.fechaDePermanencia.clone();
		this.precioPorMin=c.precioPorMin;
	}
	
	//GETTERS
	public float getMinutos() {return minutosHablados;}
	public Fecha getFPermanencia() {return (Fecha)fechaDePermanencia.clone();}
	public float getPrecioMinuto() {return precioPorMin;}
	
	//SETTERS
	public void setMinutos(float m) {minutosHablados=m;}
	public void setFPermanencia(Fecha f) {fechaDePermanencia=(Fecha)f.clone();}
	public void setPrecioMinuto(float p) {precioPorMin=p;}
	
	public float factura() {
		return minutosHablados*precioPorMin;
	}
	
	public String toString() {
		return super.toString()+" "+fechaDePermanencia+" "+minutosHablados+" x "+precioPorMin+" ---> "+factura();
	}
	
	public Object clone() {
		return new ClienteMovil(this);
	}
	
	public boolean equals(Object obj) {
		return obj instanceof ClienteMovil && getNif().equals(((Cliente)obj).getNif());
	}	
}
