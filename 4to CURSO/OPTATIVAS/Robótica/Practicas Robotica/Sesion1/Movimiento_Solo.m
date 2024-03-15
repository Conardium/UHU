%% Movimiento solo
clear tiempo, clear giro_Cabeza;
clc

resetRotation(motor_Cabeza); %Reseta el encoder
tstart = tic;
tf = 5;

k = 1;
tiempo(k) = 0;

%Definimos motor
motor_Cabeza = motor(mi_Robot,'A');

start(motor_Cabeza);
motor_Cabeza.Speed = 10;
while tiempo(k) < tf
    k = k + 1;
    tiempo(k) = toc(tstart)
    %leer encoder
    giro_Cabeza(k)=readRotation(motor_Cabeza);
end
%Parar motor
motor_Cabeza.Speed = 0;
stop(motor_Cabeza);

plot(tiempo, giro_Cabeza, '.r')