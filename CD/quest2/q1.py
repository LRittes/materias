import pandas as pd
import numpy as np
df = pd.DataFrame(np.arange(25.).reshape(5, 5), index=['idx2', 'idx1', 'idx0', 'idx3', 'idx4'], columns=['col1', 'col2', 'col3', 'col4', 'col5'])
print(df)
print(df.sort_index())