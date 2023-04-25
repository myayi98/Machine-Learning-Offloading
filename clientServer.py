import flask
import os
import pickle
import tensorflow as tf
import numpy as np
from PIL import Image
from tensorflow import keras
import sys
import cv2
import copy

server = flask.Flask(__name__)
@server.route('/', methods=['GET', 'POST'])

def handle_request():
    capturedImage = flask.request.files['image']
    predictedResult=str(getClass(capturedImage))
    response=predictedResult
    return response
    
def getClass(img):
    img = Image.open(img)
    img = np.asarray(img)
    img_array = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    img_array = np.expand_dims(img_array, 2)
    size=(14, 14)
    img_array=cv2.resize(img_array, size)
    data = np.array(img_array)/255
    data = np.array(data.flatten())
    #if np.sum(data) > np.sum(1 - data):
    if np.average(data) > 0.5:
        data = 1 - data
    loadedModel = keras.models.load_model("finalized_model")
    predictedResult=loadedModel.predict(data.reshape(1, 196))[0]
    return predictedResult

server.run(host="0.0.0.0", port=5000, debug=True)
