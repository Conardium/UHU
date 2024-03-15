%% Movimiento con pulsador
clear all, close all, clc


mi_Robot=legoev3('USB');
motor_Cabeza = motor(mi_Robot,'A');
sonar = sonicSensor(mi_Robot);

motor_Pulsador = touchSensor(mi_Robot, 2);
pulsacion = readTouch(motor_Pulsador);

resetRotation(motor_Cabeza); %Reseta el encoder

Periodo=7;
tf=2*Periodo;
delay=2;
Amplitud=105;

k = 1;
giro_cabeza(k) = readRotation(motor_Cabeza);
tiempo(k) = 0;

referencia(k) = signal_v2(tiempo(k), Periodo, delay, Amplitud);

error(k) = referencia(k) - giro_cabeza(k);
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
motor_Cabeza.Speed = 0;

%0.1 6 16
constante = 0.48; %o K = 0.6
while tiempo(k) < tf && pulsacion == 0
    k = k + 1;
    tiempo(k) = toc(tstart);
   
    %leer encoder
    giro_Cabeza(k)=readRotation(motor_Cabeza);
    referencia(k) = signal_v2(tiempo(k), Periodo, delay, Amplitud);
    error(k) = referencia(k) - giro_Cabeza(k);
    
    distancia(k) = readDistance(sonar);
    %Controlador proporcional
    controlador = constante*error(k);
    power = int8(controlador);
    
    if power > 100
        power = 100;
    elseif power < -100
        power = -100;
    end
    
    map_out = pinta_robot_v1(0, 0, 0, double(giro_Cabeza(k))*pi/180, 100*distancia(k), []);
    drawnow
    motor_Cabeza.Speed = power;
end

%Parar motor
motor_Cabeza.Speed = 0;
stop(motor_Cabeza);
figure
plot(tiempo, referencia, 'r'), hold on, grid on
plot(tiempo, giro_Cabeza, 'k'),
plot(tiempo, error, 'b')

