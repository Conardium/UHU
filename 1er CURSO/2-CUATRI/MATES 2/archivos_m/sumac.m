% Función matriz elemental: suma a la columna i la j por k
%---------------------------------------------------------
function E = sumac(i,j,a,n)
    E=sym(eye(n));
    E(:,i)=E(:,i)+a*E(:,j);
end

