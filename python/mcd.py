class app():

    def mcd(self,a,b):
        r = a % b
        if r == 0: return b
        else: return self.mcd(b,r)            

    def mcd2(self,a,b):
        r = a%b
        while(r != 0):
            q = a//b

            self.Ls.append(self.Ls[-2]-q*self.Ls[-1])
            self.Lt.append(self.Lt[-2]-q*self.Lt[-1])

            a = b
            b = r
            r = a%b
        return b
            
    def __init__(self):
        print("ax + by = c")

        a = int(input("ingrese el valor de a: "))
        b = int(input("ingrese el valor de b: "))
        c = int(input("ingrese el valor de c: "))

        self.Ls = [1,0]
        self.Lt = [0,1]

        d = self.mcd2(a,b)

        print(f"\n{a}x + {b}y = {c}")

        print("\nEl mcd de",a,"y",b,"es")
        print("Con recursividad:",self.mcd(a,b))
        print("Con ciclos:",d)

        if(c%d==0):

            # print("s =",self.Ls)
            # print("t =",self.Lt)

            x=self.Ls[-1] * c//d
            y=self.Lt[-1] * c//d

            print(f"\n{a} * ({x}) + {b} * ({y}) =", a * x + b * y)

            print(f"x = {x} + {b//d}k")
            print(f"y = {y} - {a//d}k")

        else:
            print("la ecuación no tiene solución")
app()
