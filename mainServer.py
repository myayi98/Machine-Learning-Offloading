import flask
import os

server = flask.Flask(__name__)
@server.route('/', methods=['GET', 'POST'])

def handle_request():
    image1 = flask.request.files['image1']
    image2 = flask.request.files['image2']
    image3 = flask.request.files['image3']
    image4 = flask.request.files['image4']
    predictedResult=flask.request.form['predictedResult']
    print(predictedResult)
    savePath="./predictions/"+predictedResult+"/"
    savePathExists = os.path.exists(savePath)
    if not savePathExists:
        os.makedirs(savePath)
    image1.save(savePath+image1.filename)
    image2.save(savePath+image2.filename)
    image3.save(savePath+image3.filename)
    image4.save(savePath+image4.filename)
    return "Success"

server.run(host="0.0.0.0", port=5000, debug=True)
