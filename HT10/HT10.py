import networkx as nx

class Graphs():
    def __init__(self, path):
        self.graphs = []
        self.names = []
        # Crea un grafo por cada condición climática
        for i in range(4):
            self.graphs.append(self.create_graph(path, i))

    def create_graph(self, path, ind):
        try:
            graph = nx.DiGraph()  # Grafo dirigido

            with open(path, 'r') as file:
                for line in file:
                    line2 = line.strip().split(' ')
                    # Añade las aristas correspondientes
                    graph.add_edge(line2[0], line2[1], weight=float(line2[2 + ind]))
                    graph.add_edge(line2[1], line2[0], weight=float(line2[2 + ind]))
            return graph
        except Exception as e:
            print(f"Error al crear el grafo: {e}")
            return None

    def distance(self, ind, loc1, loc2):
        graph = self.graphs[ind]
        predecessor, distance = nx.floyd_warshall_predecessor_and_distance(graph)
        try:
            return distance[loc1][loc2]  # Distancia entre las ciudades
        except KeyError:
            print(f"Error: Una de las ciudades '{loc1}' o '{loc2}' no se encuentra en el grafo.")
            return None  # En caso de que una de las ciudades no esté en el grafo

    def ruta_mas_corta(self, ind, loc1, loc2):
        # Encuentra la ruta más corta y longitud entre loc1 y loc2
        graph = self.graphs[ind]
        try:
            path = nx.shortest_path(graph, source=loc1, target=loc2, weight='weight')
            length = nx.shortest_path_length(graph, source=loc1, target=loc2, weight='weight')
            return path, length  # La ruta y su longitud
        except nx.NetworkXNoPath:
            print(f"No existe una ruta entre {loc1} y {loc2}.")
            return None, float('inf')  # Si no hay ruta la distancia es infinita
        except KeyError:
            print(f"Error: Una de las ciudades '{loc1}' o '{loc2}' no se encuentra en el grafo.")
            return None, float('inf')

    def centro(self, ind):
        # Ciudad con la mínima distancia máxima a todas las demás ciudades
        graph = self.graphs[ind]
        _, distance = nx.floyd_warshall_predecessor_and_distance(graph)
        center = min(distance, key=lambda x: sum(distance[x].values()))
        return center

    def modificar_grafo(self, ind, action, city1, city2, weights=None):
        # Modifica el grafo añadiendo o eliminando conexiones
        graph = self.graphs[ind]
        if action == "remove":
            # Elimina la arista entre las ciudades
            if graph.has_edge(city1, city2):
                graph.remove_edge(city1, city2)
            if graph.has_edge(city2, city1):
                graph.remove_edge(city2, city1)
        elif action == "add" and weights:
            # Añade una arista entre las ciudades
            graph.add_edge(city1, city2, weight=weights[ind])
            graph.add_edge(city2, city1, weight=weights[ind])

class App():
    def __init__(self):
        self.Graphs = Graphs("logistica.txt")
        self.run()  # Ejecuta el programa

    def run(self):
        while True:
            # Menú
            print("1. Calcular ruta más corta")
            print("2. Calcular centro del grafo")
            print("3. Modificar el grafo")
            print("4. Salir")
            opc = input("Seleccione una opción: ")

            if opc == "1":
                self.ruta()  # Calcula la ruta más corta
            elif opc == "2":
                self.centro()  # Calcula el centro del grafo
            elif opc == "3":
                self.modificacion()  # Modifica el grafo
            elif opc == "4":
                break  # Finaliza el programa
            else:
                print("Opción no válida.")

    def ruta(self):
        try:
            # Pide al usuario que seleccione el índice del clima, las ciudades de origen y destino
            ind = int(input("Seleccione el índice del clima (0-3): "))
            if ind < 0 or ind > 3:
                raise ValueError("El índice del clima debe estar entre 0 y 3.")
            while True:
                c1 = input("Ingrese la ciudad de origen: ")
                if c1 not in self.Graphs.graphs[ind]:
                    print(f"La ciudad '{c1}' no se encuentra en la base de datos. Por favor, intente de nuevo.")
                    continue
                c2 = input("Ingrese la ciudad de destino: ")
                if c2 not in self.Graphs.graphs[ind]:
                    print(f"La ciudad '{c2}' no se encuentra en la base de datos. Por favor, intente de nuevo.")
                    continue
                break
            path, length = self.Graphs.ruta_mas_corta(ind, c1, c2)
            if path:
                print(f"Ruta más corta: {' -> '.join(path)} con una distancia de {length}")
            else:
                print("No existe una ruta entre las ciudades seleccionadas.")
        except ValueError as e:
            print(f"Error: {e}. Verifique los nombres de las ciudades y el índice del clima.")
        except Exception as e:
            print(f"Error inesperado: {e}")

    def centro(self):
        try:
            # Pide al usuario que seleccione el índice del clima y calcula el centro del grafo
            ind = int(input("Seleccione el índice del clima (0-3): "))
            if ind < 0 or ind > 3:
                raise ValueError("El índice del clima debe estar entre 0 y 3.")
            center = self.Graphs.centro(ind)
            print(f"La ciudad que queda en el centro del grafo es: {center}")
        except ValueError as e:
            print(f"Error: {e}. Verifique el índice del clima.")
        except Exception as e:
            print(f"Error: {e}")

    def modificacion(self):
        try:
            # Pide al usuario que seleccione el índice del clima y la acción a realizar (añadir o eliminar conexión)
            ind = int(input("Seleccione el índice del clima (0-3): "))
            if ind < 0 or ind > 3:
                raise ValueError("El índice del clima debe estar en el rango indicado.")
            action = int(input("¿Desea 'añadir(0)' o 'eliminar(1)' una conexión? "))
            while True:
                c1 = input("Ingrese la ciudad de origen: ")
                if c1 not in self.Graphs.graphs[ind]:
                    print(f"La ciudad '{c1}' no se encuentra en la base de datos. Por favor, intente de nuevo.")
                    continue
                c2 = input("Ingrese la ciudad de destino: ")
                if c2 not in self.Graphs.graphs[ind]:
                    print(f"La ciudad '{c2}' no se encuentra en la base de datos. Por favor, intente de nuevo.")
                    continue
                break
            if action == 0:
                # Pide los tiempos para cada condición climática
                weights = [float(input(f"Ingrese el tiempo para el clima {i}: ")) for i in range(4)]
                self.Graphs.modificar_grafo(ind, "add", c1, c2, weights)
            elif action == 1:
                self.Graphs.modificar_grafo(ind, "remove", c1, c2)
            else:
                print("Acción no válida.")

            print("Conexión modificada.")
        except ValueError as e:
            print(f"Error: {e}. Verifique los nombres de las ciudades y el índice del clima.")
        except KeyError as e:
            print(f"Error: Ciudad no encontrada. Verifique los nombres de las ciudades.")
        except Exception as e:
            print(f"Error inesperado: {e}")
            
if __name__ == "__main__":
    App()  # Inicia la aplicación
