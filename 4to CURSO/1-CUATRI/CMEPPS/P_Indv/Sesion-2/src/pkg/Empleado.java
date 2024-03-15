package pkg;

public class Empleado {
	
	public static float calculoNominaBruta(String tipo, float ventasMes, float horasExtra)
	{
		/*El empleado puede ser Vendedor o Encargado. El salario base será 2000 euros si el empleado es de tipo vendedor, y 
		de 2500 euros si es de tipo encargado. Se le sumará a esta cantidad una prima de 100 euros si las ventas del mes 
		son mayores o iguales que 1000 euros, y de 200 euros si fuese de 1500 euros o más. Por último, cada hora extra 
		se pagará a 30 euros.*/
		float salarioBase=0;
		
		if(tipo.equals("Encargado")) salarioBase=2000;
		else if(tipo.equals("Vendedor")) salarioBase=2500;
		
		if(ventasMes >= 1000) {
			if(ventasMes >= 1500)
				return salarioBase+200+30*horasExtra;
			else
				return salarioBase+100+30*horasExtra;
		}
			
		return salarioBase+30*horasExtra;
	}
	
	public static float calculoNominaNeta(float nominaBruta) {
		/*Si la nomina bruta es menor de 2100 euros, no se aplicará ninguna retención. Para nominas superiores a 2100 pero
		menores de 2500 euros se les aplicará un 15%. Para salarios a partir de 2500 euros se les aplicará un 18%.
		El método devuelve nominaBruta * (1-retencion).*/
		
		if(nominaBruta >= 2100)
			if(nominaBruta >= 2500)
				return nominaBruta * (1-0.18f);
			else
				return nominaBruta * (1-0.15f);
		else
			return nominaBruta;
	}
}
