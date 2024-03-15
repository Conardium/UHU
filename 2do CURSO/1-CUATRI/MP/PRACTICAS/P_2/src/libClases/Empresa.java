package libClases;

import java.util.Scanner;

public class Empresa implements Cloneable{
	private Cliente[] clientes; //Array de Clientes
	private int nClientes; //Numero de clientes
	private final int incremento=2;
	
	public Empresa() {
		clientes =  new Cliente[incremento];
		nClientes = 0;
	}
	
	public float factura() {
		float factura=0;
		
		for(int i=0; i<nClientes; i++)
			factura += clientes[i].factura();
		return factura;
	}
	
	//GETTERS
	public int getN() {return nClientes;}
	
	public int nClienteMovil() {
		int nCM=0;
		for(int i=0; i<nClientes; i++)
			if(clientes[i].getClass()==ClienteMovil.class)
				nCM++;
		return nCM;
	}
	
	public void descuento(int dto) {
		float porcentaje=(float)((100-dto))/100.0f;
		for(int i=0; i<nClientes; i++)
			if(clientes[i].getClass() == ClienteMovil.class) {
				ClienteMovil cm=(ClienteMovil)clientes[i];
				cm.setPrecioMinuto(cm.getPrecioMinuto()*porcentaje);
			}
	}
	
	//Implementamos el metodo buscarCliente que nos va a ser util para los metodos Alta y Baja
	public int buscarCliente(String dni) { //Devuelve la posicion en la que se encuentra el cliente
		boolean encontrado=false;
		int i=0;
		while(i<nClientes && !encontrado) {
			if(clientes[i].getNif().equals(dni))
				encontrado=true;
			else
				i++;
		}
		if(encontrado)
			return i;
		else
			return -1;
	}
	
	//DAR DE ALTA
	public void alta(Cliente c) { //Pasandome un Cliente ya creado
		if(c == null || buscarCliente(c.getNif()) != -1) return;
		clientes[nClientes++] = c;
		if(nClientes == clientes.length) { //Si la tabla está llena, aumentamos el tamaño
			Cliente[] aux = new Cliente[clientes.length+incremento];
			for(int i=0; i<clientes.length; i++)
				aux[i]=clientes[i];
			clientes = aux;
		}
	}
	
	public void alta() { //Sin pasarme un cliente
		Cliente c;
		String DNI, nom;
		Fecha fNac, fAlta;
		float minHabla;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("DNI: ");
		DNI = sc.nextLine();
		
		//Ahora buscamos si el DNI está o no en la Empresa
		int pos = buscarCliente(DNI);
		if(pos != -1) //Si existe
			System.out.println("Ya existe un cliente con ese dni:\n"+clientes[pos]+"\n");
			
		else { //Si NO existe
			System.out.print("Nombre: ");
			nom = sc.nextLine();
			System.out.print("Fecha de Nacimiento: ");
			fNac = Fecha.pedirFecha();
			System.out.print("Fecha de Alta: ");
			fAlta = Fecha.pedirFecha();
			System.out.print("Minutos que ha hablado: ");
			minHabla= sc.nextFloat();
			
			//Ahora preguntamos por el tipo de Cliente
			System.out.print("Tipo de Cliente (1-Movil, 2-Tarifa Plana): ");
			if(sc.nextInt() == 1) { //MOVIL
				float pXm;
				Fecha fPerman;
				System.out.print("Precio Por Minuto: ");
				pXm = sc.nextFloat();
				System.out.print("Fecha de Permanencia: ");
				fPerman = Fecha.pedirFecha();
				//Una vez tenemos todos los datos llamamos al constructor correspondiente
				c = new ClienteMovil(DNI, nom, fNac, fAlta, fPerman, minHabla, pXm);
			}
			else { //TARIFA PLANA
				String nac;
				System.out.print("Nacionalidad: ");
				nac = sc.next(); 
				c = new ClienteTarifaPlana(DNI, nom, fNac, fAlta, minHabla, nac);
			}
			//Una vez creamos al cliente, llamamos al otro metodo alta para ver si cabe en la tabla,
			//en caso contrario se aumenta el tamaño de dicha tabla
			alta(c);
		}
	}
	
	//DAR DE BAJA
	public void baja(String dni) { //Borra al cliente que tiene el dni que se pasa por parametro
		int pos = buscarCliente(dni);
		
		if(pos != -1) { //Si lo encuentra
			for(int i = pos; i<nClientes-1; i++)
				clientes[i]=clientes[i+1];
			nClientes--;
		}
	}
	
	public void baja() { //Se pedirá un DNI para borrar al cliente con ese DNI
		Scanner sc = new Scanner(System.in);
		String dniAborrar;
		System.out.print("Introduce DNI del cliente a dar de Baja: ");
		dniAborrar= sc.nextLine();
		int pos = buscarCliente(dniAborrar);
		
		if(pos == -1) //NO lo encuentra
			System.out.println("No se ha encontrado al Cliente con ese DNI");
		else { //SI lo encuentra
			Cliente c = clientes[pos];
			System.out.println(c);
			System.out.print("¿Seguro que desea eliminarlo (s/n)? ");
			if(sc.nextLine().equals("s")) {
				baja(dniAborrar);
				System.out.println("El cliente "+c.getNombre()+" con nif "+c.getNif()+" ha sido eliminado\n");
			}
			else
				System.out.println("El cliente con nif "+c.getNif()+" no se ha eliminado\n");
		}
	}
	
	//SOBRECARGAS
	public String toString() {
		String s="";
		for(int i=0; i<nClientes; i++)
			s= s+clientes[i]+"\n";
		return s;
	}
	
	public Object clone() {
		Empresa obj = null;
		try {
			obj = (Empresa)super.clone();
			obj.clientes = clientes.clone();
			for(int i=0; i<nClientes; i++)
				obj.clientes[i] = (Cliente)clientes[i].clone();
		} catch (CloneNotSupportedException e) {}
		return (Object)obj;
	}
}

