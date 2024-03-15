package pkg;

public class Cuenta {
		String mNumero;
		String nTitular; 
        //List <Movimiento> mMovimientos;
		double saldo;
		
		public Cuenta(String mNumero, String nTitular, double saldo) {
			super();
			this.mNumero = mNumero;
			this.nTitular = nTitular;
			this.saldo = saldo;
		}

		public double getSaldo() {
			return saldo;
		}
		
		public String getmNumero() {
			return mNumero;
		}

		public void ingreso(double i) throws Exception {
			if(i < 0.0)
				throw new Exception("El ingreso debe de ser positivo");
			else
				saldo +=i;
		}

		public void reintegro(double i) throws Exception{
			if((saldo - i) < -500.0)
				throw new Exception("Fondos insuficientes (saldo " + getSaldo() + "€) en la cuenta " + getmNumero() + "para el reintegro de " + i + "€");
			else
				saldo-=i;
		}
		
		
}
