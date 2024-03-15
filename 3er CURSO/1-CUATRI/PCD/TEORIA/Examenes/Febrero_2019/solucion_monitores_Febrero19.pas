program ITV19; 

const 
	nPA=15; 
	nPB=15;  

{Definicion del monitor}
monitor m;

	export
		entraA, salesA, entraB, saleB;
	var

		libreSUPER, libreTECNICO:integer;
		colasimple, coladoble: condition;
		
	procedure entraA(var cual:integer);
	begin
		if not empty(colaB) or (libreSUPER=0 and libreTECNICO=0) then delay(colaA);
		if libreTECNICO>0 then begin
			libreTECNICO=libreTECNICO-1;
			cual=1;
		end
		else begin
			libreSUPER=0;
			cual=2;
		end
	end

	procedure saleA(cual:integer);
	begin
		if cual=1 then libreTECNICO=libreTECNICO+1;
		else 	libreSUPER=1; // cual=2
		if not empty(colaB) then begin
			if libreSUPER>0 and libreTECNICO>0 then resume(colaB);
			else //no hace nada, no puede dejarlo pasar
		else
			resume(colaA);			
	end

	procedure entraB();
	begin
		if libreSUPER=0 or libreTECNICO=0 then delay(colaB);
		libreSUPER=0;
		libreTECNICO=libreTECNICO-1;
	end;

	procedure saleB();
	begin
		libreSUPER=1;
		libreTECNICO=libreTECNICO+1;
		if not empty(colaB) then resume(colaB);
		else
			resume(colaA);
			resume(colaA);
	end; 


	begin 
		libreSUPER=1;
		libreTECNICO=2;
	end; 
(* Fin del monitor *)


process type TIPOA(id:integer); 
var

begin 
	repeat	 
		m.entraA(cual);
		writeln('TIPO A EN ITV'+cual); 
		m.saleA(cual);
	forever 
end; 

process type TIPOB(id:integer); 
begin 
	repeat 
		m.entraB();
		writeln('TIPOB EN ITV'); 
		m.saleB(id);
	forever 
end; 

var 
   i,j: integer; 
   VehiculoA: array[1..nPA] of TIPOA;
   VehiculoB: array[1..nPB] of TIPOb; 
   
begin 
  cobegin 
    for i := 1 to nPA do VehiculoA[i](i); 
    for j := 1 to nPB do VehiculoB[j](j); 
  coend 
end. 

