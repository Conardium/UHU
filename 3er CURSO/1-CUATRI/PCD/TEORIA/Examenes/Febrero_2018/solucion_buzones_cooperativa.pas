program Cooperativa; 

var fresa, arandano: mailbox[1..3] of char;
	 contar: mailbox of char;



process Seleccionador1; 
begin
	repeat
	
		 sleep(); // Montando caja de fresa
		 send(contar,'f');
		 send(fresa,'f');

	forever
		 	 
end; 

process Seleccionador2; 
begin  
	repeat
		 sleep(); // Montando caja de arandanos
		 send(contar,'a');
		 send(arandano,'a');

	forever
		
end; 

process Empaquetador;
var fresas,arandanos:integer;
		 que:char;
begin  
	fresas:=0;
	arandanos:=0;
	repeat
		select
			receive(contar,que);
			if que='f' then fresas=fresas+1;
			else arandanos=arandanos+1;
		or
			when fresas>=arandanos =>
			receive(fresas,que);
			fresas=fresas-1;
		or 
			when fresas<=arandanos =>
			receive(arandanos,que);
			arandanos=arandanos-1;
		end select;
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



