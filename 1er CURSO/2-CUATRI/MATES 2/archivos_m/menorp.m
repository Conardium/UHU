%=============================================================
% Localiza un menor principal en una matriz                  |
%-------------------------------------------------------------
% Menor-> Almacenará el primer menor principal que encuentre |
% r    -> El rango de la matriz A o sea, dimension de Menor  |
% F,C  -> Filas y columnas de A de las que Menor forma parte |
%=============================================================
function [Menor,r,F,C]=menorp(A)
    % Rango de A y comprobamos que A no es la matriz nula
    % ---------------------------------------------------
    r=rank(A);
    if r == 0
        disp('Rango nulo: no hay menores principales')
        F=[0];C=[0];Menor=[];
        return
    end
    
    % Número de filas y columnas de A
    % ------------------------------- 
    [nf,nc]=size(A);
    
    % Todas las combinaciones posibles de r filas y su número
    % -------------------------------------------------------
    fpos=1:nf;
    Filas=sym(nchoosek(fpos,r));
    ncombif=size(Filas,1);
    
    % Todas las combinaciones posibles de r columnas y su número
    % ----------------------------------------------------------
    cpos=1:nc;
    Columnas=sym(nchoosek(cpos,r));
    ncombic=size(Columnas,1);
   
    % Comprobamos hasta que encontremos el primer menor principal
    % -----------------------------------------------------------
    for i=1:ncombif
        for j=1:ncombic
            Menor=A(Filas(i,:),Columnas(j,:));
            if det(Menor) ~= 0
                F=Filas(i,:);
                C=Columnas(j,:);
                return
            end
        end
    end   
end