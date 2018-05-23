# -*- coding: utf-8 -*-
"""
Draw K-Line Graph with stock code

"""


print(__doc__)

import tushare as ts

import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.finance as mpf
import datetime
from matplotlib.pylab import date2num

data = ts.get_k_data('sh',start = '2017-10-10',end = '2018-04-19')
print(data)

def date_to_num(dates):
    num_time = []
    for date in dates:
        date_time = datetime.datetime.strptime(date,'%Y-%m-%d')
        num_date = date2num(date_time)
        num_time.append(num_date)
    return num_time

mat_data = data.as_matrix()
num_time = date_to_num(mat_data[:,0])
mat_data[:,0] = num_time
print(mat_data[:3])

fig , ax  = plt.subplots(figsize=(15,5))
fig.subplots_adjust(bottom=0.5)
mpf.candlestick_ochl(ax,mat_data,width = 0.6,colorup = 'g',colordown='r',alpha = 1.0)
plt.grid(True)
plt.xticks(rotation = 30)
plt.xlabel('Date')
plt.ylabel('Price')
ax.xaxis_date()