from matplotlib import pyplot as plt
import numpy as np
import math

def logistica1(x, L=1.0, x0=0.0, k=0.4):
    return L/(1 + math.exp(-k * (x - x0)))

def logistica2(x):
    return logistica1(x, x0=3.0, k=0.6)

X = np.linspace(-10, 10, 201)
Y1 = list(map(logistica1, X))
Y2 = list(map(logistica2, X))

fig, (ax1, ax2) = plt.subplots(2, 1, figsize=(12, 8))
ax1.plot(X, Y1, 'r:')
ax1.plot(X, Y2, 'g')

ax2.bar([1, 2], [0.4, 0.6], color=['r', 'g'], tick_label=['k=0.4', 'k=0.6'])

ax1.set_title("Logistica")
ax2.set_title("k")