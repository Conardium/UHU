// ----- Ejercicio 1 ------

template <typename T>
T verticeMaxCoste(const Grafo<T, float> &G)
{
	T verticeMax;
	map<T, float> costes;
	float maxCoste = -1;
	
	Conjunto<Vertice<T> > vertices = G.vertices();
	while(!vertices.esVacio())
		costes[vertices.quitar().getObj()] = 0;
	
	Conjunto<Arista<T, float> > aristas = G.aristas();
	while(!aristas.esVacio())
	{
		Arista<T, float> tmp = aristas.quitar();
		costes[tmp.getOrigen()] += tmp.getEtiqueta(); //costes[1] += 3
	}
	
	for(typename map<T, float>::iterator it = costes.begin(); it != costes.end(); it++)
	{
		if(it->second >= maxCoste)
		{
			verticeMax = it->first;
			maxCoste = it->seocnd;
		}
	}
	
	return verticeMax;
}

//---------------------------------------------
// ----- Ejercicio 2 ------

template <typename T, typename U>
void inaccesibles(const Grafo<T, U> &G)
{
	Conjunto<Vertice<T> > vertices = G.vertices();
	while(!vertices.esVacio())
	{
		Vertice<T> tmp = vertices.quitar();
		if(G.antecesores(tmp.getObj()).esVacio())
			cout << tmp.getObj() << " "; 
	}
}

//---------------------------------------------
// ----- Ejercicio 3 ------

template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd){
    queue<T> cola;
    cola.push(vo);
    Conjunto<T> cerrados;
    T actual = vo;

    do{
        Conjunto<Vertice<T> > adyacentes = G.adyacentes(actual);
        while(!adyacentes.esVacio()){
            T tmp = adyacentes.quitar().getObj();
            if(!cerrados.pertenece(tmp))
                cola.push(tmp);
        }
        cerrados.anadir(actual);
        actual = cola.front();
        cola.pop();
    }while(actual != vd && !cola.empty());

    return actual == vd;
}

//---------------------------------------------
// ----- Ejercicio 4 ------

template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste)
{
	Conjunto<T> recorrido;
	recorrido.anadir(u);
	
	Conjunto<Arista<T, float> > adyacentes = G.aristas();
	while(!adyacentes.esVacio())
	{
		Arista<T, float> tmp = adyacentes.quitar();
		if(tmp.getOrigen() == u && maxCoste-tmp.getEtiqueta() >= 0)
			caminosAcotados(G, tmp.getDestino(), maxCoste-tmp.getEtiqueta(), recorrido);
	}
}

void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste, Conjunto<T> recorrido)
{
	recorrido.anadir(u);
	
	Conjunto<Arista<T, float> > adyacentes = G.aristas();
	while(!adyacentes.esVacio())
	{
		Arista<T, float> tmp = adyacentes.quitar();
		if(tmp.getOrigen() == u && maxCoste-tmp.getEtiqueta() >= 0)
			caminosAcotados(G, tmp.getDestino(), maxCoste-tmp.getEtiqueta(), recorrido);
	}
	
	while(!recorrido.esVacio())
		cout << recorrido.quitar() << "<-";
	cout << endl;
}


//---------------------------------------------
// ----- Ejercicio 5 ------

template <typename T, typename U>
T outConectado(const Grafo<T, U> &G)
{
	T solucion; //El vertice que devolvera como solucion
	map<T, int> valor; //Creamos un diccionario (guardará el vertice[T] y el numero de arista que tiene, tanto de entrada como de salida [int])
	
	Conjunto<Vertice<T> > vertices = G.vertices(); //Guardamos en un conjunto todos los vertices del grafo
	while(!vertices.esVacio())
		valor[vertices.quitar().getObj()] = 0; //Establecemos el numero de aristas conectadas a cada vertice a 0
	
	Conjunto<Arista<T, float> > aristas = G.aristas(); //Guardamos en otro conjunto todas las aristas del grafo
	while(!aristas.esVacio())
	{
		Arista<T, float> aristaTMP = aristas.quitar(); //Cogemos y quitamos una arista del conjunto
		valor[aristaTMP.getOrigen()] += 1; //Incrementamos a 1 el vertice que tenga esa arista como salida
		valor[aristaTMP.getDestino()] -= 1; //Drecementamos a 1 el vertice que tenga esa arista como entrada
	}
	
	bool encontrado = false;
	typename map<T, int>::iterator it = valor.begin();
	while(it != valor.end() && !encontrado)
	{
		if(it->second > 0)
		{
			solucion = it->first;
			encontrado = true;
		}
		it++;
	}
	
	return solucion;
}

//---------------------------------------------
// ----- Ejercicio 6 ------

template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v)
{
	Conjunto<T> visitados;
	visitados.anadir(v);
	cout << v << " ";
	
	Conjunto<Vertice<T> > adyacentes = G.adyacentes(v);
	while(!adyacentes.esVacio())
		recorrido_profundidad(G, adyacentes.quitar().getObj(), visitados);
}

template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v, Conjunto<T> visitados)
{
	visitados.anadir(v);
	cout << v << " ";
	
	Conjunto<Vertice<T> > adyacentes = G.adyacentes(v);
	while(!adyacentes.esVacio())
	{
		T tmp = adyacentes.quitar().getObj();
		if(!visitados.pertenece(tmp))
			recorrido_profundidad(G, tmp, visitados);
	}
}






//****************************************************************************************************
//************************************* EJERCICIOS EXTRAS ********************************************
//****************************************************************************************************

//Devuelve verdadero si existe un camino en G con exactamente la longitud proporcionada partiendo del vértice u
template <typename T, typename U>
bool caminoLongitud(const Grafo<T, U>& G, const T& u, int longitud)
{
	bool encontrado = false;
	
	Conjunto<Vertice<T>> adyacentes = G.adyacentes(u);
	while(!adyacentes.esVacio()  && !encontrado)
		encontrado = caminoLongitud(G, adyacentes.quitar().getObj(), longitud-1; encontrado);
	
	return encontrado;
}

bool caminoLongitud(const Grafo<T, U>& G, const T& u, int longitud, bool encontrado)
{
	if(longitud == 0)
		return true;
	else
	{
		Conjunto<Vertice<T>> adyacentes = G.adyacentes(u);
		while(!adyacentes.esVacio() && !encontrado)
			encontrado = caminoLongitud(G, adyacentes.quitar().getObj(), longitud-1; encontrado);
	}
	
	return encontrado;
}



//Muestra cuantas aristas con un coste superior al dado salen de cada vértice 
template <typename T, typename U>
void cuentaAristas(const Grafo<T, U>& G, float coste)
{
	map<T, int> diccionario;
	
	Conjunto<Vertice<T>> vertices = G.vertices();
	while(!vertices.esVacio())
		diccionario[vertices.quitar().getObj()] = 0;
	
	Conjunto<Arista<T, U>> aristas = G.aristas();
	while(!aristas.esVacio())
	{
		Arista<T, float> tmp = aristas.quitar();
		if(tmp.getEtiqueta() > coste)
			diccionario[tmp.getOrigen()] += 1;
	}
	
	for(typename map<T, int>::iterator it = diccionario.begin(); it != diccionario.end(); it++)
		cout << "El Vertice " << it->first << " tiene " << it->second << " aristas más grandes que " << coste << endl;
	
}