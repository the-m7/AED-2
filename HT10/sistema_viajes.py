import networkx as nx
import matplotlib.pyplot as plt

class SistemaViajes:
    def __init__(self, nombre_archivo):
        # Constructor de la clase SistemaViajes.
        # Inicializa un objeto SistemaViajes con un grafo basado en los datos proporcionados en un archivo.
        self.grafo = self.leer_archivo(nombre_archivo)
    
    def leer_archivo(self, nombre_archivo):
        try:
            # Crea un grafo dirigido utilizando NetworkX.
            grafo = nx.DiGraph()
            # Abre el archivo proporcionado en modo lectura.
            with open(nombre_archivo, 'r') as file:
                # Lee cada línea del archivo.
                for linea in file:
                    # Divide la línea en sus componentes (estación origen, estación destino, costo).
                    datos = linea.strip().split(',')
                    # Elimina las comillas y espacios extra de las estaciones.
                    estacion_origen = datos[0].strip()[1:-1]
                    estacion_destino = datos[1].strip()[1:-1]
                    # Convierte el costo a entero.
                    costo = int(datos[2].strip())
                    # Agrega la conexión entre las estaciones al grafo (en ambas direcciones para rutas simétricas).
                    grafo.add_edge(estacion_origen, estacion_destino, weight=costo)
                    grafo.add_edge(estacion_destino, estacion_origen, weight=costo)  # Rutas simétricas
            
            return grafo
        except Exception:
            print("Error al leer el archivo.")
    
    def mostrar_destinos(self, estacion_salida):
        try:
            # Obtiene los destinos posibles desde una estación de salida dada.
            destinos = list(self.grafo.neighbors(estacion_salida))
            if destinos:
                print(f"Posibles destinos desde {estacion_salida}:")
                # Itera sobre cada destino posible y muestra su costo.
                for destino in destinos:
                    costo = self.grafo[estacion_salida][destino]['weight']
                    print(f"- {destino}: Costo = {costo}")
            else:
                print(f"No hay destinos disponibles desde {estacion_salida}")
        except Exception:
            print("Error al mostrar los destinos.")
    
    def dijkstra(self, estacion_origen):
        try:
            # Implementación del algoritmo de Dijkstra para encontrar las distancias más cortas.
            distancias = {nodo: float('inf') for nodo in self.grafo.nodes}
            distancias[estacion_origen] = 0
            visitados = set()
            
            while len(visitados) < len(self.grafo.nodes):
                # Encuentra el nodo no visitado más cercano.
                nodo_actual = min((nodo for nodo in distancias.items() if nodo[0] not in visitados), key=lambda x: x[1])[0]
                visitados.add(nodo_actual)
                # Actualiza las distancias a los vecinos del nodo actual.
                for vecino in self.grafo.neighbors(nodo_actual):
                    peso_arista = self.grafo[nodo_actual][vecino]['weight']
                    nueva_distancia = distancias[nodo_actual] + peso_arista
                    if nueva_distancia < distancias[vecino]:
                        distancias[vecino] = nueva_distancia
            
            return distancias
        except Exception:
            print("Error en el algoritmo de Dijkstra.")
    
    def dibujar_grafo(self):
        # Dibuja el grafo utilizando Matplotlib y NetworkX.
        posiciones = nx.spring_layout(self.grafo)
        nx.draw(self.grafo, pos=posiciones, with_labels=True, node_size=700, node_color='skyblue', font_size=10, font_weight='bold', arrowsize=10)
        # Agrega etiquetas a las aristas con los pesos.
        edge_labels = nx.get_edge_attributes(self.grafo, 'weight')
        nx.draw_networkx_edge_labels(self.grafo, pos=posiciones, edge_labels=edge_labels)
        # Muestra el título del grafo y lo dibuja.
        plt.title("Grafo de Estaciones")
        plt.show()

def mostrar_menu():
    # Función que muestra el menú de opciones al usuario.
    print("==== Menú ====")
    print("1. Ver posibles destinos desde una estación")
    print("2. Calcular las distancias más cortas usando Dijkstra")
    print("3. Mostrar grafo")
    print("4. Salir")

if __name__ == "__main__":
    # Inicia el programa creando un objeto SistemaViajes.
    sistema_viajes = SistemaViajes('rutas.txt')

    while True:
        # Bucle principal que muestra el menú y realiza acciones según la opción seleccionada.
        mostrar_menu()
        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            estacion_salida = input("Ingrese el nombre de la estación de salida: ")
            sistema_viajes.mostrar_destinos(estacion_salida)
        elif opcion == "2":
            estacion_salida = input("Ingrese el nombre de la estación de salida: ")
            distancias_desde_estacion = sistema_viajes.dijkstra(estacion_salida)
            print("Distancias desde", estacion_salida + ":")
            for estacion, distancia in distancias_desde_estacion.items():
                print(f"{estacion}: {distancia}")
        elif opcion == "3":
            sistema_viajes.dibujar_grafo()
        elif opcion == "4":
            print("Saliendo del programa...")
            break
        else:
            print("Opción inválida. Por favor, seleccione una opción válida.")
