
//CLASE PARA COMPROBAR LOS METODOS DE LA MODIFICACIÓN

package libPruebas;
import libClases.*;

public class Examen {

	public static void main(String[] args) {
		Fecha f1= new Fecha(29, 2, 2001), f2= new Fecha(f1), f3= new Fecha(29, 2, 2004);
		Fecha fnac1= new Fecha(7, 3, 1980), fnac2= new Fecha(27, 06, 1995);
		ClienteTarifaPlana[] ct= new ClienteTarifaPlana[4];
		ClienteMovil cm1= new ClienteMovil("547B", "Luis Perez", fnac2, 50.50f, 0.03f);
		ClienteMovil cm2= (ClienteMovil) cm1.clone(); //lo crea con los mismos datos que cm1 
		ClienteMovil cm3= new ClienteMovil("777F", "Joe Sam", fnac2.diaSig(), 50.50f, 0.02f);
		ct[0] = new ClienteTarifaPlana("805W", "Luz Casal", fnac1, f3, 375.09f, "Española");
		ct[1] = new ClienteTarifaPlana("953H", "Paz Padilla", fnac2, f2, 290.00f, "Española");
		ct[2] = new ClienteTarifaPlana("106T", "Elton John", fnac2, 340.75f, "Inglesa");
		ct[3] = new ClienteTarifaPlana("467X", "Messi", fnac2.diaSig(), 300.00f, "Argentina");
		Empresa g= new Empresa();
		g.alta(cm1);
		g.alta(ct[0]);
		g.alta(ct[2]);
		g.alta(cm2);
		g.alta(ct[1]);
		g.alta(cm3);
		//1
		System.out.println("Grupo g:\n"+ g);
		System.out.println("Ganancia:"+ g.ganancia(1981));
		//2
		Mezcla[] ml= new Mezcla[4];
		
		ml[1] = cm1;
		ml[2] = cm2;
		ml[3] = new ClienteCable();
		for (int i = 1; i < 4 ; i++ )
		System.out.println("factura de " + ml[i].factura());
	}

}
