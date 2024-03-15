
%Hemos cambiado el i por k
%Con esta versión ahora podemos cerrar la gráfica sin esperar a que termine
%de llegar al final el punto

clear all
clc

Periodo=3;
delay=2;
Amplitud=90;

tf=2*Periodo; 

x=0;
y=0;
theta=pi/4
distancia = 5;
mapa=[];

k=1;
tiempo(k)=0;
alfa(k)=signal_v2(tiempo, Periodo, delay, Amplitud);
tstart=tic;

while tiempo(k)<tf
    k=k+1;
    tiempo(k)=toc(tstart);
    tiempo(k)

    alfa(k)=signal_v2(tiempo(k), Periodo, delay, Amplitud);
    mapa_out=pinta_robot_v0(x,y,theta,alfa(k)*pi/180,distancia,mapa)
    drawnow;
end

