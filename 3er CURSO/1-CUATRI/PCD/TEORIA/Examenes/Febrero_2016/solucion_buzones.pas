program Trampolines; 

const 
	np1=5; 
	np2=5;  
type
	Info = Record
		id, trampolin : Integer;
               end;
var
	pidepiscina: mailbox of Info;
	pidetrampolin,sueltapiscina: mailbox of integer;
	permiso: array[1..np1+np2] of mailbox of integer;



process Controlador;
var
	libre1metro, libre3metros, librepiscina:boolean;
	id:integer;
	cual:Info;
	
begin
	libre1metro:=true;
	libre3metro:=true;
	librepiscina:=true;

	repeat
		select
			when libre1metro=true ==>
				receive(pidetrampolin,id);
				libre1metro:=false;
				send(permiso[id],1);
		or
			when libre3metro=true ==>
				receive(pidetrampolin,id);
				libre3metro:=false;
				send(permiso[id],3);
		or	when librepiscina=true ==>
				receive(pidepiscina,cual);
				librepiscina:=false;
				if cual.trampolin = 1 then libre1metro:=true;
				else libre3metro:=true;
				send(permiso[cual.id],0); //El 0 no tiene significado
		or
			receive(sueltapiscina,id);
			librepiscina:=true; 
				
		end select;
				
	forever
end;

	

process type TSaltador(id:integer); 
var
	tr,pis:integer;
	cual:Info;
begin 
	repeat	 
		send(pidetrampolin, id);
		receive(permiso[id], tr);
		cual.trampolin=tr;
		cual.id=id;
		send(pidepiscina, cual);
		receive(permiso[id], pis);
		writeln('He saltado desde el trampolín de ..'+tr); 
		send(sueltapiscina,id);
		sleep(random(4));

	forever 
end; 



var 
   i,j: integer; 
   Saltador: array[1..np1] of Tsaltador;
   Saltadora: array[1..np2] of Tsaltador; 
   
begin 
  cobegin 
    for i := 1 to np1 do Saltador[i](i); 
    for j := np1+1 to np1+np2 do Saltadora[j](j); 

  coend 
end. 

