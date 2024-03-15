% Función matriz elemental: cambia fila i por fila j
% --------------------------------------------------
function E = cambiaf(i,j,n)
    E=sym(eye(n));
    temp=E(i,:);
    E(i,:)=E(j,:);
    E(j,:)=temp;
end

