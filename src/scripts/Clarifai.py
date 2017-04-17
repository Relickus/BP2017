from clarifai.rest import ClarifaiApp
import json
import sys

imagefile = str(sys.argv[1])


app = ClarifaiApp("7vrfUeKVnqG0c2yYIoUZ_Ga2LmMC3NWdsMolV-Ro","r4QUpi_ot490wdv8j4Ql287EziZubwWA7-v7Gx47")
model = app.models.get("general-v1.3")

res = model.predict_by_filename(imagefile)
jsonres = json.dumps(res,indent=2)
jsonarr = json.loads(jsonres)

i=0
while True:
	try:
		label = jsonarr["outputs"][0]["data"]["concepts"][i]["name"]
		score = jsonarr["outputs"][0]["data"]["concepts"][i]["value"]		
		print(label + ":" + str(score))
		i=i+1
	except IndexError:
		break
