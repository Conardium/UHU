% Para construir cofactor i,j de la matriz A
% ------------------------------------------
function C=cofa(i,j,A)
    Tam=size(A);
    if (Tam(1)==Tam(2)) & (1<=i) & (i <= Tam(1)) & (1<=j) & (j<=Tam(2))
        A(i,:)=[];A(:,j)=[];
        C=(-1)^(i+j)*det(A);
    else
        disp('Datos incoherentes: fin de programa')
    end
end