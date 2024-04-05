def mcd(a,b):
    r = a % b
    if r == 0: return b
    else: return mcd(b,r)

def mcd2(a,b):
    r = a%b
    while(r != 0):
        a = b
        b = r
        r = a%b
    return b
        

a = 437
b = 57

print("El mcd de",a,"y",b,"es")
print("Con recursividad:",mcd(a,b))
print("Con ciclos:",mcd2(a,b))