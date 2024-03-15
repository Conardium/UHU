package libClases;

import java.util.Scanner; //Para el "cin"

public final class Fecha implements Cloneable, Proceso {
	private int dia;
	private int mes;
	private int anio;
	
	public Fecha(int dia, int mes, int anio) { //Constructor
		setFecha(dia,mes,anio);
	}
	
	public Fecha(Fecha f) { //Constructor de Copia
		dia=f.dia;
		mes=f.mes;
		anio=f.anio;
	}
	
	public int getDia() {return dia;}
	public int getMes() {return mes;}
	public int getAnio() {return anio;}
	
	public boolean bisiesto() {
		return ((anio%400==0) || (anio%4==0 && anio%100!=0));

	}
	
	public void setFecha(int d, int m, int a){
		dia=d;
		mes=m;
		anio=a;
		
		if(m < 1)
			mes=1;
		else if(m > 12)
			mes=12;
		
		int maxDias[] = {0,31,bisiesto()?29:28,31,30,31,30,31,31,30,31,30,31};
		
		if(d < 1)
			dia=1;
		else if(d > maxDias[mes])
			dia=maxDias[mes];
	}
	
	public String toString() { //Metodo toString
		String s="";
		if(dia < 10) s=s+0;
		s=s+dia+"/";
		if(mes < 10) s=s+0;
		s=s+mes+"/"+anio;
		return s;
	}
	
	public void ver() {
		System.out.print(this/*toString()*/);
	}
	
	public static Fecha pedirFecha() { //Para pedir por teclado
		Fecha fecha = null;
		boolean valida = false;
		Scanner sc= new Scanner(System.in);
		int dia, mes, anio;
		do {
			System.out.print("Introduce la Fecha (dd/mm/aaaa): ");
			String cadena= sc.next();
			String[] tokens = cadena.split("/");
			try { //Apartir de aqui vamos a controlar excepciones
				if(tokens.length !=3)
					throw new NumberFormatException();
				
				dia = Integer.parseInt(tokens[0]); //parseInt lanza la excepcion
				mes = Integer.parseInt(tokens[1]); //NumberFormatException() si no
				anio= Integer.parseInt(tokens[2]); //puede convertir el String a int
				
				fecha = new Fecha(dia, mes, anio);
				
				if(fecha.getDia() != dia || fecha.getMes() != mes)
					throw new NumberFormatException();
				
				valida=true;
			}catch(NumberFormatException e) {
				System.out.println("Fecha no valida");
			}
		}while(!valida);
		//sc.close();
		return fecha;
	}
	
	public static boolean mayor(Fecha f1, Fecha f2) {
		if(f1.anio*1000+f1.mes*100+f1.dia > f2.anio*1000+f2.mes*100+f2.dia)
			return true;
		else
			return false;
	}
	
	public Object clone() { //Metodo Clone
		return new Fecha(this);
	}
	
	public boolean equals(Object obj) { //Metodo Equals(iguales)
		if(this==obj) return true; //Si apuntan al mismo sitio son iguales
		if(obj==null) return false;
		if(getClass() != obj.getClass()) //Si los dos no son de la misma clase, false
			return false;
		Fecha c = (Fecha) obj; //Convertimos obj en un Objeto tipo Fecha
		return (dia==c.dia && mes==c.mes && anio==c.anio);
	}
	
	public Fecha diaSig() {
		Fecha f = new Fecha(this);
		f.dia++;
		
		int maxDias[] = {0,31,bisiesto()?29:28,31,30,31,30,31,31,30,31,30,31};
		
		if(f.dia > maxDias[f.mes]) {
			f.dia -= maxDias[f.mes]; //Se podría poner f.dia=1 ya que el metodo solo incrementa de 1 en 1
			f.mes++;
			if(f.mes > 12) {
				f.mes = 1;
				f.anio++;
			}
		}
		return f;
	}
	
	
	
	
	
	//######### MODIFICACION ############
	public int EsMenor(int a1, int a2) { //Devuelve 1 si el primer parametro es menor que el segundo, 0 en caso contrario
		int cierto=0;
		if(a1 < a2)
			cierto=1;
		return cierto;
	}
	//##############################
	
	
	
	
	
	
	public static void main(String[] args) {
		 Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004);
		 final Fecha f4=new Fecha(05,12,2023); //es constante la referencia f4
		 System.out.println("Fechas: " + f1.toString() + ", "+f2+ ", " +f3+ ", " +f4+ "\n");
		 f1=new Fecha(31,12,2016); //31/12/2016
		 f4.setFecha(28, 2, 2008); //pero no es constante el objeto al que apunta
		 System.out.println(f1 +" "+ f2.toString() +" " + f3 +" "+ f4+" "+ f1);
		 f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7); //f1=18/02/2001
		 f3=Fecha.pedirFecha(); //pide una fecha por teclado
		 f3.ver();
		 if (f3.bisiesto() && Fecha.mayor(f2,f1))
		 System.out.print("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
	}
}
