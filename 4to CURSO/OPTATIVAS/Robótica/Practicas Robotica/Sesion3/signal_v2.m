function salida = signal_v2(t, Periodo, delay, Amplitud)

salida=Amplitud*sin((2*pi/Periodo)*(t-delay));

salida(t<delay)=0;
salida(t>delay+Periodo)=0;
end

