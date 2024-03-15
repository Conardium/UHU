
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Simulación del movimiento de un robot móvil por una casa: Actividad 7
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%SE RECOMIENDA AL EJECUTAR EL SCRIPT MAXIMIZAR LA VENTANA EMERGENTE PARA PODER
%VER MEJOR EL CAMINO QUE SIGUE EL ROBOT.

clear all
clc

j=1;

global l
global radio_rueda
global camino
global pose
global punto

%Posicion de inicio del camino y del robot
xi=127;
yi=13;

%Distancia entre los puntos (en cm).
ds=3; 

%Cargamos el mapa que recorrerá el robot
MAPA = imread('Casa.bmp');

%Transformación para colocar correctamente el origen del Sistema de Referencia
MAPA(1:end,:,:)=MAPA(end:-1:1,:,:);
image(MAPA);
axis xy

%Establecemos el tamaño de la celda, cuanto más pequeña sea, mayor precisión pero es más lento
celda = 6;

Optimal_path=A_estrella(MAPA, celda, xi, yi);

%camino=funcion_spline_cubica_varios_puntos(xc,yc,ds)';
camino=funcion_spline_cubica_varios_puntos(Optimal_path(:,1)',Optimal_path(:,2)',1);

camino = camino';

l=3.5; %distancia entre ruedas delanteras y traseras
radio_rueda=1;

%Condiciones iniciales 
pose0=[xi; yi; 0]; %Indicamos la posicion de inicio del robot

t0=0;

%Tiempo que tarda en terminar la simulación
tf=35;


%paso de integracion
h=0.1;
%vector tiempo
t=0:h:tf;
%indice de la matriz
k=0;

%inicialización valores iniciales
pose(:,k+1)=pose0;

t(k+1)=t0;

while (t0+h*k) < tf,
    %actualización
    k=k+1;
     
 %Para representar el punto objetivo sobre la trayectoria que seguirá el robot
 
 orden_minimo= minima_distancia (camino, [pose(1,k), pose(2,k)]); %Obtenemos el indice mas cercano al vehiculo

 % Para poder evitar que se pase del camino
 if orden_minimo+3 >= length(camino)
     punto=[camino(end, 1) camino(end,2)];
 else
     punto=[camino(orden_minimo+3, 1) camino(orden_minimo+3,2)];
 end

%---------------------------------------------------------------------------------------
%Controlador de alto nivel

 delta=(pose(1,k)-punto(1,1))*sin(pose(3, k)) - (pose(2,k)-punto(1,2))*cos(pose(3, k));
 Lh = sqrt((pose(1,k)-punto(1,1))^2 + (pose(2,k)-punto(1,2))^2);  

 rho = 2*delta/Lh^2

 phi=atan(rho*l);

 %#### Para evitar que pete el robot al pasarse el camino hacemos lo siguiente #####
 Ep = sqrt((camino(end,1)-pose(1,k))^2 + (camino(end,2)-pose(2,k))^2);
 kv = 0.5;
 velocidad = kv * Ep;

 %Cambiar la velocidad para que vaya más o menos rápido el robot
 if velocidad > 10
     velocidad = 10;
 elseif velocidad < -10
     velocidad = -10;
 end 
%#############################################################

velocidad_derecha = (velocidad/radio_rueda)*(1+l*rho);
velocidad_izquierda = (velocidad/radio_rueda)*(1-l*rho);  

conduccion=[velocidad_derecha velocidad_izquierda];

pose(:,k+1)=kuta_diferencial_mapa(t(k),pose(:,k),h,conduccion, MAPA);
grid on;

end

