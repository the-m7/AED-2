import networkx as nx

class Graphs():
    def __init__(self, path):
        self.graphs = []

    def create_graph(self, path):
        try:
            graph = nx.DiGraph()

            with open(path, 'r') as file:
                for line in file:
                    line2 = line.strip().split(' ')

                    