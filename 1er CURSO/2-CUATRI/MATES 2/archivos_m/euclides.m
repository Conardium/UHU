% Algoritmo de Euclides
% ---------------------
clc
a=input(' Introduce a: ');
disp(' ');
a=sym(a);A=a;
b=input(' Introduce b: ');
disp(' ');
b=sym(b);B=b;

% Bucle de Euclides
% -----------------
seguir=true;
while seguir
    % Hallamos cociente y resto de a entre b
    % --------------------------------------
    fprintf('\n\n& Divisor: %s   & Dividendo: %s \n\n',A,B);
    [q,r]=divide(A,B);
    fprintf('Cociente: %s   Resto: %s \n\n',q,r);
    if r==0
        seguir=false;
    else
        fprintf('mcd(%s,%s)=mcd(%s,%s)\n\n',A,B,B,r);
        pause
        A=B;
        B=r;
    end
end
fprintf('mcd(%s,%s)=%s \n\n',a,b,abs(B)')
