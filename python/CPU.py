#Programa que simula a una CPU
#

import simpy
import random
import numpy as np

RANDOM_SEED = 42

# Parámetros para configurar la simulación
INTERVALOS = [10, 5, 1] ##aumenta la carga
MEMORIA_TOTAL = 100 #RAM disponible
CAPACIDAD_CPU = 1 #CPU's disponibles 
VELOCIDAD_CPU = 3  # Instrucciones por cierta unidad de tiempo
INSTRUCCIONES_MAX = 10 #max de instrucciones 


# Recursos y Procedimientos

##Programa Principal
def main():
    env = simpy.Environment()
    random.seed(RANDOM_SEED)

    RAM = simpy.Container(env, init=MEMORIA_TOTAL, capacity=MEMORIA_TOTAL)
    cpu = simpy.Resource(env, capacity=CAPACIDAD_CPU)

    # Estadísticas
    promedios = {}
    desviaciones = {}

    for intervalo in INTERVALOS:
        promedios[intervalo] = []
        desviaciones[intervalo] = []

    def proceso(env, nombre):
        # Tiempo de llegada
        tiempo_llegada = env.now

        # Memoria requerida
        memoria = random.randint(1, 10)

        # Solicitar memoria
        with RAM.get(memoria) as request:
            yield request

            # Tiempo en espera por memoria
            tiempo_espera_memoria = env.now - tiempo_llegada

            # Estado "Ready"
            yield cpu.request()

            # Tiempo en espera por CPU
            tiempo_espera_cpu = env.now - tiempo_llegada - tiempo_espera_memoria

            # Ejecución en CPU
            for _ in range(INSTRUCCIONES_MAX):
                yield env.timeout(1 / VELOCIDAD_CPU)

            # Fin de la ejecución
            tiempo_ejecucion = env.now - tiempo_llegada

            # Liberar memoria
            RAM.put(memoria)

            # Generar siguiente proceso
            if intervalo != 10:
                env.process(proceso(env, f"{nombre}_{random.randint(1, 100)}"))

            # Estadísticas
            promedios[intervalo].append(tiempo_ejecucion)

    # Ejecutar la simulación
    for i in range(200):
        env.process(proceso(env, f"Proceso_{i + 1}"))

    env.run()

    # Imprimir resultados
    for intervalo in INTERVALOS:
        if len(promedios[intervalo]) > 0:
            promedio = sum(promedios[intervalo]) / len(promedios[intervalo])
            desviacion = np.std(promedios[intervalo])
    else:
        promedio = "N/A"
        desviacion = "N/A"


    print(f"Intervalo: {intervalo}")
    print(f"Promedio: {promedio}")
    print(f"Desviación estándar: {desviacion}")

main()