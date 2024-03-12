import simpy,random,numpy

# fmt: off
RANDOM_SEED = 43
CPU_NUM = 1
CPU_VEL = 6
MEMORIA_RAM = 100
INSTRUCTIONS = 10

PROCESS = [25,50,100,150,200] # 25, 50, 100, 150, 200

INTERVALOS = [10,5,1] 
# fmt: on

def main():
    # env = simpy.Environment()

    # RAM = simpy.Container(env, MEMORIA_RAM, MEMORIA_RAM)
    # cpu = simpy.Resource(env, CPU_NUM)
    
    random.seed(RANDOM_SEED)
    
    # Estadísticas
    promedios = {}
    desviaciones = {}

    for intervalo in INTERVALOS:
        promedios[intervalo] = []
        desviaciones[intervalo] = []

    def process2(env, name, intervalo):
        
        # print(name)
        
        yield env.timeout(random.expovariate(1.0 / interval))

        arrival = env.now

        memory = random.randint(1,10)

        # print(RAM.level)

        with RAM.get(memory) as request:
            yield request

            waiting_memory = env.now - arrival

            with cpu.request() as req:

                waiting_cpu = env.now - arrival - waiting_memory

                for i in range(INSTRUCTIONS):
                    yield env.timeout(1/CPU_VEL)

                runtime = env.now - arrival

                RAM.put(memory)

                # if intervalo != INTERVALOS[-1]:
                #     print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                #     env.process(process2(env, f"{name}_{random.randint(1,100)}"))

                promedios[intervalo].append(runtime)

                # print(name, runtime)
    for procesos in PROCESS: 
        # print("\nProcesos:",procesos)
        for interval in INTERVALOS:
            env = simpy.Environment()
            RAM = simpy.Container(env, MEMORIA_RAM, MEMORIA_RAM)
            cpu = simpy.Resource(env, CPU_NUM)

            for i in range(procesos):
                # print(random.expovariate(1.0 / interval))
                env.process(process2(env, f"Process: {i+1}", interval))
        
            env.run()

        
        # Imprimir resultados
        for intervalo in INTERVALOS:
            if len(promedios[intervalo]) > 0:
                promedio = sum(promedios[intervalo]) / len(promedios[intervalo])
                desviacion = numpy.std(promedios[intervalo])
                # print(intervalo,": ")
                # print("Promedio:",promedio)
                # print("StDev:",desviacion)
                print(procesos,intervalo,promedio,desviacion)


    # print(promedios)


main()