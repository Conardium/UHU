% -------------------------------------------------------------------
% Cociente y resto POSITIVO de una división entre dos enteros enteros
% -------------------------------------------------------------------
function [q,r]=divide(a,b)
    % Comprobamos si el divisor es nulo
    % ---------------------------------
    if b==0
        fprintf('\n Error, divisor igual a cero.\n\n');
        return
    end
    
    % Forzamos que las variables sean simbólicas
    % ------------------------------------------
    a=sym(a);b=sym(b);
    
    % Resto positivo y menor que abs(b)
    % ---------------------------------
    r=rem(a,b);
    if r<0
        r=r+abs(b);
    end
    
    % Hallamos el cociente entero
    % ---------------------------
    q=(a-r)/b;
end
% -------------------------------------------------------------------