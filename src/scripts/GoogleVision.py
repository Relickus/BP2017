# SCRIPT MUST BE CALLED WITH "python" in command line



import argparse
import base64
import json
import sys
from oauth2client.client import GoogleCredentials
from googleapiclient.discovery import build


imagefile = str(sys.argv[1])

service = build("vision", "v1", developerKey='AIzaSyBAnIieoASIcLVpfQQsLE03DbrcmKJ9ZDo')


with open(imagefile, 'rb') as image:
    image_content = base64.b64encode(image.read())
    service_request = service.images().annotate(body={
        'requests': [{
            'image': {
                'content': image_content.decode('UTF-8')
            },
            'features': [{
                'type': 'LABEL_DETECTION',
                'maxResults': 5
            }]
        }]
    })
        
        
    response = service_request.execute()


    size = len(response['responses'][0]['labelAnnotations'])
    for i in range(size):
        label = response['responses'][0]['labelAnnotations'][i]['description']
        score = response['responses'][0]['labelAnnotations'][i]['score']
        print(label + ":" +str(score))
    

