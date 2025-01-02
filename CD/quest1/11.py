def tarifa(km, largada=4.0,valor=0.5):
    return largada + (km * 1000)/200 * valor

print(tarifa(1, valor=0.2))