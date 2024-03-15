% Función matriz elemental: multiplica fila i por k
% -------------------------------------------------
function E = kf(i,k,n)
    E=sym(eye(n));
    E(i,:)=k*E(i,:);
end

