program Cooperativa; 

{Definicion del monitor}
monitor m;

	export
		ponerarandanos, ponerfresas, embalar;
	var

		librefresa, librearandano:integer;
		colafresa, colaarandano, embalador: condition;
		
	procedure ponerfresas;
	begin
		if (librefresa=0) then delay(colafresa);
		librefresa--;
		resume(embalador);
	end

	procedure ponerarandanos;
	begin
		if (librearandano=0) then delay(colaarandano);
		librearandano--;
		resume(embalador);
	end

	procedure embalar;
	begin
		if (librefresa=3 and librearandano=3) then delay(embalador);
		if (librefresa<librearandano) then
		begin
			librearandano++;
			resume(colaarandano);
		end
		else
		begin
			librefresa++;
			resume(colafresa);
		end
	end

	begin 
		librefresa=3;
		librearandano=3;
	end; 
(* Fin del monitor *)

process Seleccionador1; 
begin
	repeat
	
		 sleep(); // Montando caja de fresa
		 m.ponerfresa();
	 
	forever
		 	 
end; 

process Seleccionador2; 
begin  
	repeat
		 sleep(); // Montando caja de arandanos
		 m.ponerarandanos();
	forever
		
end; 

process Empaquetador;
begin  
	repeat
		 m.embalar();
		 sleep(); // embalando caja y soltándola en la cinta
	forever
		
end; 

begin 
  cobegin 
	Seleccionador1;
	Seleccionador2;
	Empaquetador;
  coend 
end. 


