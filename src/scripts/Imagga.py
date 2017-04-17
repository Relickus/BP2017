import requests
import sys
import json

api_key = 'acc_e14f8e725912acc'
api_secret = '5f4800dc3a85e9a5e0bff6b36aa3cd61'

image_path = str(sys.argv[1])

response = requests.post('https://api.imagga.com/v1/content',
auth=(api_key, api_secret),
files={'image': open(image_path, 'rb')})


jsonres = json.dumps(response.json(),indent=2)
jsonarr = json.loads(jsonres)

content = jsonarr["uploaded"][0]["id"]

response = requests.get('https://api.imagga.com/v1/tagging?content=%s' % content,
                        auth=(api_key, api_secret))


jsonres = json.dumps(response.json(),indent=2)
jsonarr = json.loads(jsonres)

i=0
while True:
	try:
		label = jsonarr["results"][0]["tags"][i]["tag"]
		score = jsonarr["results"][0]["tags"][i]["confidence"]		
		print(label + ":" + str(score))
		i=i+1
	except IndexError:
		break
