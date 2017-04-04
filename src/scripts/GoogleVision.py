
# SCRIPT MUST BE CALLED WITH "python" in command line



import argparse
import base64
import json
import sys

import googleapiclient.discovery

imagefile = str(sys.argv[1])


service = googleapiclient.discovery.build('vision', 'v1') 

with open(imagefile, 'rb') as image:
    image_content = base64.b64encode(image.read())
    service_request = service.images().annotate(body={
        'requests': [{
            'image': {
                'content': image_content.decode('UTF-8')
            },
            'features': [{
                'type': 'LABEL_DETECTION'
            }]
        }]
    })
        
        
    response = service_request.execute()

    size = len(response['responses'][0]['labelAnnotations'])
    for i in range(size):
        label = response['responses'][0]['labelAnnotations'][i]['description']
        print(label)
    

