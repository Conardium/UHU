clear all, close all, clc
mi_Robot=legoev3('USB');
motor_Cabeza = motor(mi_Robot,'A'); 

motor_Cabeza.Speed = 10;

start(motor_Cabeza);

motor_Cabeza.Speed = 0; % Parar motor suavemente.

stop(motor_Cabeza); %Suelta el motor.

%% ENCODER - Lector grados cabeza
giro_Cabeza=readRotation(motor_Cabeza) %Valor en grados (A veces se trabaja en radianes)

resetRotation(motor_Cabeza); %Reseta el encoder

