import networkx as nx

class Graphs():
    def __init__(self, path):
        self.graphs = []
        self.names = []

        for i in range(4):
            self.graphs.append(self.create_graph(path,i))

    def create_graph(self, path, ind):
        try:
            graph = nx.DiGraph()

            with open(path, 'r') as file:
                for line in file:
                    line2 = line.strip().split(' ')

                    graph.add_edge(line2[0],line2[1],weight=line2[2+ind])
                    graph.add_edge(line2[1],line2[2],weight=line2[2+ind])
            return graph
        except Exception:
            print(Exception)

    def distance(self, ind, loc1, loc2):
        graph = self.graphs[ind]
        predecesor, distance = nx.floyd_warshall_predecessor_and_distance(graph)
        return distance[loc1][loc2]
            
class app():
    def __init__(self):
        self.Graphs = Graphs("logistica.txt")

app()   

                    