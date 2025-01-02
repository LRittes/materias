def mediana(a,b,c):
    m1 = min(a,b,c)
    m2 = max(a,b,c)
    
    if a != m1 and a != m2:
        return a
    elif b != m1 and b != m2:
        return b
    return c

print(mediana(4,3,6))