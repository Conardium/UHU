function mapa_out=pinta_robot_v0(x,y,theta,alfa,distancia,mapa)

%-------------------------------------------------
% función para pintar el robot y el mapa versión 0
%               19/10/22 FGB
%-------------------------------------------------


persistent robot cabeza

if isempty(robot)
    robot = hgtransform;
end

if isempty(cabeza)
    cabeza = hgtransform('Parent',robot);
end



M = makehgtform('translate',[x y 0], 'zrotate',theta);
robot.Matrix=M;

%cuerpo del robot
R=rectangle('Position',[-1.5 -1.5 3 3],'Parent',robot);
R.LineWidth=2;

%ruedas
rueda_derecha=hgtransform('Parent',robot);
rd=rectangle('Position',[-0.5 -0.1 1 0.2],'Parent',rueda_derecha);
M = makehgtform('translate',[0 1 0]);
rueda_derecha.Matrix=M;

rueda_izquierda=hgtransform('Parent',robot);
ri=rectangle('Position',[-0.5 -0.1 1 0.2],'Parent',rueda_izquierda);
M = makehgtform('translate',[0 -1 0]);
rueda_izquierda.Matrix=M;


%cabeza del robot

ca=rectangle('Position',[-0.25 -0.5 0.5 1],'Parent',cabeza);
M = makehgtform('translate',[1 0 0],'zrotate',alfa);
cabeza.Matrix=M;

Mt=robot.Matrix*cabeza.Matrix;


axis([-10 10 -10 10]);

mapa_out=mapa;


end
