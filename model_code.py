import numpy as np 
import pandas as pd 
import pickle
import matplotlib.pyplot as plt 
from PIL import Image
import sys
import cv2


from keras import  backend as K
from keras.datasets import mnist
from keras.models import Sequential
from keras.layers import Dense, Activation, Dropout
from tensorflow.keras.utils import to_categorical




(x_tr, y_tr),(x_te, y_te) = mnist.load_data()
print(np.shape(x_tr))

x_train, y_train = [], []
for i in range(len(x_tr)):
    x_train.append(x_tr[i][:14,:14])
    x_train.append(x_tr[i][:14,14:])
    x_train.append(x_tr[i][14:,:14])
    x_train.append(x_tr[i][14:,14:])
    y_train.append(y_tr[i])
    y_train.append(y_tr[i])
    y_train.append(y_tr[i])
    y_train.append(y_tr[i])


x_test, y_test = [], []
for i in range(len(x_te)):
    x_test.append(x_te[i][:14,:14])
    x_test.append(x_te[i][:14,14:])
    x_test.append(x_te[i][14:,:14])
    x_test.append(x_te[i][14:,14:])
    y_test.append(y_te[i])
    y_test.append(y_te[i])
    y_test.append(y_te[i])
    y_test.append(y_te[i])

x_train = np.array(x_train)
y_train = np.array(y_train)
x_test = np.array(x_test)
y_test = np.array(y_test)

print(np.shape(x_train),np.shape(y_train),np.shape(x_test),np.shape(y_test))
unique, counts = np.unique(y_train, return_counts=True)
print("Train labels: ", dict(zip(unique, counts)))


unique, counts = np.unique(y_test, return_counts=True)
print("\nTest labels: ", dict(zip(unique, counts)))

num_labels = len(np.unique(y_train))
y_train = to_categorical(y_train)
y_test = to_categorical(y_test)

image_size = x_train.shape[1]
input_size = image_size * image_size

x_train = np.reshape(x_train, [-1, input_size])
x_train = x_train.astype('float32') / 255
x_test = np.reshape(x_test, [-1, input_size])
x_test = x_test.astype('float32') / 255

batch_size = 512
hidden_units = 256
dropout = 0.15

model = Sequential()
model.add(Dense(hidden_units, input_dim=input_size))
model.add(Activation('relu'))
#model.add(Dropout(dropout))
model.add(Dense(hidden_units))
model.add(Activation('relu'))
#model.add(Dropout(dropout))
model.add(Dense(num_labels))
model.add(Activation('softmax'))

#model.summary()

model.compile(loss='categorical_crossentropy', 
              optimizer='adam',
              metrics=['accuracy'])

model.fit(x_train, y_train, epochs=20, batch_size=batch_size)

loss, acc = model.evaluate(x_test, y_test, batch_size=batch_size)
print("\nTest accuracy: %.1f%%" % (100.0 * acc))
filename = 'finalized_model'
model.save(filename)

 
