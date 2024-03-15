package libClases;

public class ClienteTarifaPlana extends Cliente{
	private static float precioExceso = 0.15f; 
	private static float minutosTP = 300;
	private static float precioTarifa = 20;
	private String nacionalidad;
	private float minutosHablados;
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float minHablados, String Nac) {
		super(NIF, nom, fNac, fAlta); //En el caso de que me den la fecha de Alta
		minutosHablados=minHablados;
		nacionalidad=Nac;
	}
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float minHablados, String Nac) {
		super(NIF, nom, fNac); //En el caso de que NO me den la fecha de Alta
		minutosHablados=minHablados;
		nacionalidad=Nac;
	}
	
	public ClienteTarifaPlana(ClienteTarifaPlana c) {
		super(c);
		this.minutosHablados=c.minutosHablados;
		this.nacionalidad=c.nacionalidad;
	}
	
	
	
	//GETTERS
	public float getMinutos() {return minutosHablados;}
	public String getNacionalidad() {return nacionalidad;}
	public static float getLimite() {return minutosTP;}
	public static double getTarifa() {return precioTarifa;}
	
	//SETTERS
	public void setMinutos(int min) {minutosHablados=min;}
	public void setNacionalidad(String Nac) {nacionalidad=Nac;}
	public static void setTarifa(int limite, float f) {minutosTP=limite; precioTarifa=f;}
	
	public float factura() {
		return precioTarifa+(minutosHablados>minutosTP?(minutosHablados-minutosTP)*precioExceso:0);
	}
	
	public String toString() {
		return super.toString()+" "+nacionalidad+" ["+minutosTP+" por "+precioTarifa+"] "+minutosHablados+" ---> "+factura();
	}
	
	public Object clone() {
		return new ClienteTarifaPlana(this);
	}
	
	public boolean equals(Object obj) {
		return obj instanceof ClienteTarifaPlana && getNif().equals(((Cliente)obj).getNif());
	}
}
