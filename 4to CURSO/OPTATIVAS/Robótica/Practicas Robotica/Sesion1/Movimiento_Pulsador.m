%% Movimiento con pulsador
clear all, close all, clc


mi_Robot=legoev3('USB');
motor_Cabeza = motor(mi_Robot,'A'); 

motor_Pulsador = touchSensor(mi_Robot, 2);
pulsacion = readTouch(motor_Pulsador);

resetRotation(motor_Cabeza); %Reseta el encoder

tf = 5;

k = 1;
tiempo(k) = 0;

%Definimos motor
motor_Cabeza = motor(mi_Robot,'A');


%Si se sale del bucle y el motor sigue andando se tiene que parar
%manualmente --> motor_Cabeza.Speed = 0;
while pulsacion == 0
    disp 'ACTIVAR PULSADOR'
    pulsacion = readTouch(motor_Pulsador);
end

while pulsacion == 1
    disp 'ARRANCANDO'
    pulsacion = readTouch(motor_Pulsador);
end

tstart = tic;
start(motor_Cabeza);
motor_Cabeza.Speed = 10;

while tiempo(k) < tf && pulsacion == 0
    k = k + 1;
    tiempo(k) = toc(tstart)
    
    
    %leer encoder
    giro_Cabeza(k)=readRotation(motor_Cabeza);
end

%Parar motor
motor_Cabeza.Speed = 0;
stop(motor_Cabeza);

plot(tiempo, giro_Cabeza, '.r')
