% Función matriz elemental: suma a la fila i la j por k
% -----------------------------------------------------
function E = sumaf(i,j,k,n)
    E=sym(eye(n));
    E(i,:)=E(i,:)+k*E(j,:);
end

