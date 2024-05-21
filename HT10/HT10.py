import networkx as nx

class Graphs():
    def __init__(self, path):
        self.path = path
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
    
    def modificar_grafo(self, ind, action, c1, c2, clima=None, weights=None):
        # Modifica el grafo según la acción especificada
        graph = self.graphs[ind]
        
        if action == "interruption":
            # Indica una interrupción de tráfico entre un par de ciudades
            if graph.has_edge(c1, c2):
                graph.remove_edge(c1, c2)
            if graph.has_edge(c2, c1):
                graph.remove_edge(c2, c1)
        elif action == "new_connection" and weights:
            # Establece una nueva conexión entre ciudad1 y ciudad2 con los tiempos especificados
            graph.add_edge(c1, c2, weight=weights[0])
            graph.add_edge(c2, c1, weight=weights[0])
        elif action == "weather" and clima:
            # Indica el clima entre un par de ciudades
            if graph.has_edge(c1, c2):
                graph[c1][c2]['weather'] = clima
            if graph.has_edge(c2, c1):
                graph[c2][c1]['weather'] = clima

        # Actualizar el archivo después de la modificación
        self.archivo()

    def archivo(self):
        # Escribir los datos del grafo en el archivo
        with open("logistica.txt", 'w') as file:  # Modificar para sobrescribir
            for graph in self.graphs:
                for edge in graph.edges():
                    weight_list = [str(graph[edge[0]][edge[1]]['weight']) for _ in range(4)]
                    file.write(f"{edge[0]} {edge[1]} {' '.join(weight_list)}\n")

class App():
    def __init__(self):
        self.Graphs = Graphs("logistica.txt")
        self.run()  # Ejecuta el programa

    def run(self):
        while True:
            # Menú
            print("\nMENU DE OPCIONES:")
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
            # Pide al usuario que seleccione el clima
            print("\nOPCIONES DE CLIMA:")
            print("0) Con clima normal")
            print("1) Con lluvia")
            print("2) Con nieve")
            print("3) Con tormenta")
            clima_option = input("Seleccione una opción de clima (0, 1, 2, 3): ")

            if clima_option not in ['0', '1', '2', '3']:
                raise ValueError("Opción de clima no válida.")

            clima_index = int(clima_option)

            while True:
                # Pide al usuario las ciudades de origen y destino
                c1 = input("Ingrese la ciudad de origen: ")
                if c1 not in self.Graphs.graphs[clima_index]:
                    print(f"La ciudad '{c1}' no se encuentra en la base de datos. Por favor, intente de nuevo.")
                    continue
                c2 = input("Ingrese la ciudad de destino: ")
                if c2 not in self.Graphs.graphs[clima_index]:
                    print(f"La ciudad '{c2}' no se encuentra en la base de datos. Por favor, intente de nuevo.")
                    continue
                break

            path, length = self.Graphs.ruta_mas_corta(clima_index, c1, c2)
            if path:
                print(f"Ruta más corta de {c1} a {c2}:")
                print(" -> ".join(path))
                print(f"Distancia: {length}")
            else:
                print("No existe una ruta entre las ciudades seleccionadas.")
        except ValueError as e:
            print(f"Error: {e}. Verifique los nombres de las ciudades y la opción de clima.")
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
            # Pide al usuario la acción a realizar y los detalles correspondientes
            ind = int(input("Seleccione el índice del clima (0-3): "))
            if ind < 0 or ind > 3:
                raise ValueError("El índice del clima debe estar entre 0 y 3.")

            print("\nOPCIONES DE MODIFICACIÓN:")
            print("1. Interrupción de tráfico entre ciudades")
            print("2. Establecer nueva conexión entre ciudades")
            print("3. Indicar clima entre ciudades")
            action = input("Seleccione una opción de modificación: ")

            if action == "1":
                c1 = input("Ingrese la primera ciudad: ")
                c2 = input("Ingrese la segunda ciudad: ")
                self.Graphs.modificar_grafo(ind, "interruption", c1, c2)
            elif action == "2":
                c1 = input("Ingrese la primera ciudad: ")
                c2 = input("Ingrese la segunda ciudad: ")
                weight = float(input("Ingrese el tiempo de la nueva conexión: "))
                self.Graphs.modificar_grafo(ind, "new_connection", c1, c2, weights=[weight])
            elif action == "3":
                c1 = input("Ingrese la primera ciudad: ")
                c2 = input("Ingrese la segunda ciudad: ")
                clima = input("Ingrese el clima (normal, lluvia, nieve o tormenta): ")
                self.Graphs.modificar_grafo(ind, "weather", c1, c2, clima=clima)
            else:
                print("Opción no válida.")
        except ValueError as e:
            print(f"Error: {e}.")
        except Exception as e:
            print(f"Error: {e}")

if __name__ == "__main__":
    App()
