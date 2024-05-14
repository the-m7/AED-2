# ejemplo. Encontrar ruta mas corta
# con algoritmo de Floyd

import networkx as nx
g = nx.DiGraph()
g.add_edge('1','2',weight=8,type=1)
g.add_edge('1','3',weight=5,type=2)
g.add_edge('2','1',weight=3,type=3)
g.add_edge('3','2',weight=2,type=4)

distancia = [[0,0,0],[0,0,0],[0,0,0]]  #matriz 3 x 3 para resultado
predecesor, distance = nx.floyd_warshall_predecessor_and_distance(g)
print ("distancia mas corta: ")

for i in distance:  #guardar el resultado en la matriz distancia
    dis = distance[i]
    for j in dis:
        ii = int(i)-1
        jj = int(j)-1
        distancia[ii][jj] = dis[j]
for f in distancia:
    print (f)

print ("predecesores: ")
print (predecesor)







