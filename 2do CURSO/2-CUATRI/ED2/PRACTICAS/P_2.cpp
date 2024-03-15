
//----------------------------- Ejercicio 1 -----------------------------
template <typename T>
int numHojas(const Arbin<T>& a)
{
	return numHojas(a, a.getRaiz());
}

template <typename T>
int numHojas(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
	if(r.arbolVacio()) //Si el arbol está vacío devolvemos 0
		return 0;
	
	//Si el nodo raiz no tiene hijos ni por la izq ni por la der entonces devuelve 1
	else if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
		return 1;
	
	//En otro caso, devuelve el num. de hojas del subIzq + num. de hojas del subDer
	else
		return numHojas(a, a.subIzq(r)) + numHojas(a, a.subDer(r));
}


//----------------------------- Ejercicio 2 -----------------------------
template <typename T>
const Arbin<T>& simetrico(const Arbin<T>& a)
{
	return *simetrico<T>(a, a.getRaiz());
}

const Arbin<T>* simetrico(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
	if(r.arbolVacio())
		return new Arbin<T>();
	
	else
		return new Arbin<T>(r.observar(), *simetrico(a, a.subDer(r)), *simetrico(a, a.subIzq(r)));
}


//----------------------------- Ejercicio 3 -----------------------------
template <typename T>
void recorridoZigZag(const Arbin<T>& a, char sentido)
{
	recorridoZigZag(a, a.getRaiz(), sentido);
}

template <typename T>
void recorridoZigZag(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, char sentido)
{
	if(!r.arbolVacio()) //Si el arbol no está vacio hacemos lo siguiente:
	{
		cout << r.observar() << " "; //Mostramos el nodo por pantalla
		
		if(sentido = 'I')
			recorridoZigZag(a, a.subIzq(r), 'D');
		else
			recorridoZigZag(a, a.subDer(r), 'I');
		
	}
}


//----------------------------- Ejercicio 4 -----------------------------
template <typename T>
bool compensado(const Arbin<T>& a)
{
	return compensado(a, a.getRaiz());
}

bool compensado(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
	if(r.arbolVacio() || (a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()))
		return true;	
	else
	{
		//Si sus hijos no estan compensados, no hace falta continuar
		if(!(compensado(a, a.subIzq(r))) && !(compensado(a, a.subDer(r))))
			return false;
		else
			return (hijos(a, a.subIzq(r)) - hijos(a, subDer(r)) <= 1); 
	}
}

int hijos(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
	if(r.arbolVacio())
		return 0;
	else
		return hijos(a, a.subIzq(r)) + hijos(a, subDer(r)) + 1;
	//El +1 es para contar al nodo padre
}


//----------------------------- Ejercicio 5 -----------------------------
template <typename T>
void concatenacion(const Arbin<T>& a)
{
	concatenacion(a, a.getRaiz());
}

template <typename T>
void concatenacion(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
	if(r.arbolVacio())
		cout << "";
	
	if(a.subDer(r).arbolVacio() && a.subIzq(r).arbolVacio)
		cout << "\n";
	
	else
	{
		cout << r.observar();
		concatenacion(a, a.subIzq(r));
		concatenacion(a, a.subDer(r));
	}
}


//----------------------------- Ejercicio 6 (ABB) -----------------------------
int siguienteMayor(const ABB<int>& a, int x) throw (noHaySiguienteMayor)
{
	return siguienteMayor(a, a.getRaiz(), x);
}

int siguienteMayor(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x)
{
	if(r.arbolVacio())
		throw NoHaySiguienteMayor("El siguiente no está en el arbol");
	else
	{
		if(r.observar() <= x)
			return siguienteMayor(a, a.subDer(r), x);
		else
		{
			try
			{
				return siguienteMayor(a, subIzq(r), x);
			}catch(NoHaySiguienteMayor ex){
				r.observar();
			}
		}
		
		
	}
}


//----------------------------- Ejercicio 7 (ABB) -----------------------------
int recorrido(const ABB<int>& a, int x)
{
	int n = 0;
	return recorrido(a, a.getRaiz(), x, n);
}

int recorrido(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x, int& n)
{
	int posicion = 0; //n nos indicará la posicion del elemento a buscar
	if(!r.arbolVacio())
	{
		
	}
	
	return pos;
}