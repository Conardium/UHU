package libClases;

public class Cliente implements Cloneable, Proceso {
	
	private static int contador = 1;
	private static final Fecha fecha_defecto = new Fecha(1,1,2018);
	
	private final String nif; //dni del cliente (letra incluida) (NO puede cambiar)
	private final int codCliente;  //codigo único (y fijo) generado por la aplicación
	private String nombre; //nombre completo del cliente (SI se puede modificar) 
	private final Fecha fechaNac; //fecha nacimiento del cliente (NO se puede cambiar)
	private final Fecha fechaAlta; //fecha de alta del cliente (SI se puede modificar)
	
	public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta) {
		nif=NIF;
		nombre=nom;
		fechaNac=(Fecha)fNac.clone(); // new Fecha(fNac);
		fechaAlta=(Fecha)fAlta.clone();
		codCliente = contador++; //Inicializamos la const codCliente
		
	}
	
	public Cliente(String NIF, String nom, Fecha fNac) {
		this(NIF, nom, fNac, fecha_defecto);
	}
	
	public Cliente(Cliente c) { //Constructor de copia
		this(c.nif,c.nombre,c.fechaNac,c.fechaAlta);
	}
	
	public String toString() { //Metodo toString
		String s="";
		s=s+nif+" "+fechaNac+": "+nombre+" ("+codCliente+" - "+fechaAlta+")";
		return s;
	}
	
	//GETTERS
	public final String getNif() {return nif;}
	public final int getCodCliente() {return codCliente;}
	public final String getNombre() {return nombre;}
	public final Fecha getFechaNac() {return (Fecha)fechaNac.clone();}
	public final Fecha getFechaAlta() {return (Fecha)fechaAlta.clone();}
	public final static Fecha getFechaPorDefecto() {return (Fecha)fecha_defecto.clone();}
	
	//SETTERS (solo nombre, fechaAlta y fecha_defecto ya que se pueden modificar)
	public final void setNombre(String nom) {nombre = nom;}
	public final void setFechaAlta(Fecha f) {fechaAlta.setFecha(f.getDia(),f.getMes(),f.getAnio());}
	public final static void setFechaPorDefecto(Fecha f) {fecha_defecto.setFecha(f.getDia(),f.getMes(),f.getAnio());}
	
	public /*abstract*/ float factura() {return 0;/*NO hace nada*/} //Se hace abstracto para no poder crear objetos de esta clase
	
	public void ver() {
		System.out.println(this);
	}
	
	public Object clone() {
		return new Cliente(this); //Llama al Contructor de Copia
	}
	
	public boolean equals(Object obj) { //Metodo equals
		if(this==obj) return true; //Si apuntan al mismo sitio son iguales
		if(obj==null) return false;
		if(getClass() != obj.getClass()) //Si los dos no son de la misma clase, false
			return false;
		return obj.getClass() == Cliente.class && nif.equals(((Cliente)obj).nif);
	}
}
