import time 
import requests
import operator
import numpy as np
import sys

_url = 'https://westus.api.cognitive.microsoft.com/vision/v1.0/analyze'
_key = '35aa2769740840e596ce72ba764a61b3' #Here you have to paste your primary key
_maxNumRetries = 10

imagefile = str(sys.argv[1])

def processRequest( json, data, headers, params ):


    retries = 0
    result = None

    while True:

        response = requests.request( 'post', _url, json = json, data = data, headers = headers, params = params )

        if response.status_code == 200 or response.status_code == 201: 
            i=0
            while True:
                try:
                    label = response.json()["tags"][i]["name"];
                    score = response.json()["tags"][i]["confidence"];
                    print(label + ":" + str(score))
                    i=i+1
                except IndexError:
                    return

        else:
            print( "Error code: %d" % ( response.status_code ) )
            print( "Message: %s" % ( response.json()['error']['message'] ) )

        break
        
    return


with open( imagefile, 'rb' ) as f:
    data = f.read()

# Computer Vision parameters
params = { 'visualFeatures' : 'Tags'} 

headers = dict()
headers['Ocp-Apim-Subscription-Key'] = _key
headers['Content-Type'] = 'application/octet-stream'

json = None

result = processRequest( json, data, headers, params)
