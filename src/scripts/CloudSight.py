import cloudsight
import json
import sys
import time

imagefile = str(sys.argv[1])


auth = cloudsight.SimpleAuth('ZdwJH8S7_6maa1zK-2511A')
api = cloudsight.API(auth)

with open(imagefile, 'rb') as img:
    response = api.image_request(img, 'neco.jpg', {
        'image_request[locale]': 'en-US',
    })

    res = api.image_response(response['token'])

    jsondump = json.dumps(res)
    jsonarr =json.loads(jsondump)
	
    while(jsonarr["status"] != 'completed'):
    	#active wait for response to be received
    	time.sleep(1)
    	print(jsonarr["status"])
    print(jsonarr["name"])