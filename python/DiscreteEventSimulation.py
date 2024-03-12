import simpy
import random
import math 

RANDOM_SEED = 1000
random.seed(RANDOM_SEED) # Generar números aleatorios con la misma semilla

env = simpy.Environment()  # Ambiente de simulacion

MEMORIA_RAM_TOTAL = 100
VELOCIDAD_CPU = 3  # Instrucciones x unidad de tiempo
UNIDAD_TIEMPO_CPU = 1  # Tiempo en el que se atiende un proceso

memoria_disponible = simpy.Resource(env, capacity=MEMORIA_RAM_TOTAL) # indica el ambiente y su capacidad
cpu = simpy.Resource(env, capacity=VELOCIDAD_CPU) # indica el procesador y su capacidad
RAM = simpy.Container(env, capacity=MEMORIA_RAM_TOTAL, init=MEMORIA_RAM_TOTAL)  # Memoria RAM

ready = simpy.Resource(env, capacity=10)  # Cola de procesos "Ready"
waiting = simpy.Resource(env, capacity=10)  # Cola de procesos "Waiting"

tiempos_totales = []

def proceso(env):
  suma_tiempos=0
  tiempo_llegada = env.now
  # Solicitar cantidad de memoria RAM
  memoria_solicitada = random.randint(1, 10)
  print(f" Se solicita {memoria_solicitada} de memoria")
  
  # Cola para solicitar memoria
  yield RAM.get(memoria_solicitada)

  ## si hay memoria disponible pasa a ready, sino a waiting !!!
  if RAM.level >= memoria_solicitada:
    with ready.request() as req_ready:
      yield req_ready
      # Cola para usar el CPU
      with cpu.request() as req:
        yield req
        print(f" Ejecutando proceso")
        # Ejecución de instrucciones
        for i in range(random.randint(1, 10)):  # Se ejecuta un número aleatorio de instrucciones
          print(f" Se ejecutó una instrucción")
          yield env.timeout(UNIDAD_TIEMPO_CPU)
  else:
    with waiting.request() as req:
      yield req
      # Estado del proceso
      numero_aleatorio = random.randint(1, 21)
      if numero_aleatorio == 1:
        print(f" Se va a la cola de espera")  
        # Waiting      
        with waiting.request() as req:
          yield req
          print(f" Salió de la cola de espera") 
      else:
        #Ready
        print(f" Se va a la cola de ready")   
          
  # Liberar memoria
  yield RAM.put(memoria_solicitada)
  print(f" Se liberaron {memoria_solicitada} de memoria")
  # Cálculo del tiempo total
  tiempo_total = env.now - tiempo_llegada
  print(f"tiempo total:{tiempo_total}")
  tiempos_totales.append(tiempo_total)

# inicia los procesos 
for i in range(10):
  if RAM.level > 0 and cpu.capacity>0:
    env.process(proceso(env))
env.run()

# Cálculo del promedio y desviación estándar
promedio_tiempo = 0
varianza = 0
suma_tiempos = 0

if tiempos_totales:
   for tiempo in tiempos_totales:
     suma_tiempos+= tiempo
   try:
      promedio_tiempo = suma_tiempos / len(tiempos_totales) 
   except ZeroDivisionError:
      pass
   for tiempo in tiempos_totales:
    diferencia_al_cuadrado = (tiempo - promedio_tiempo)**2
    varianza += diferencia_al_cuadrado
    varianza /= len(tiempos_totales)
  
# Impresión de resultados
if tiempos_totales:
  print(f"Promedio de tiempo en el sistema: {promedio_tiempo}")
  print(f"Desviación estándar: {math.sqrt(varianza)}")
else:
  pass
