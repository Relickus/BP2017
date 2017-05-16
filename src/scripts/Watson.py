
#  !!!!!!! NOTE: this service currently doesnt work since free trial of this service expired on the day of thesis submition 

import json
from os.path import join, dirname
from watson_developer_cloud import VisualRecognitionV3
import sys

imagefile = str(sys.argv[1])

visual_recognition = VisualRecognitionV3('2016-05-20', api_key='93bc4d7f17a8d0bd011394b97e14b8be369b11ee')


with open(imagefile, 'rb') as img:
    result = visual_recognition.classify(images_file=img,
                                              threshold=0.75)
    jsonresult = json.dumps(result,indent=2)


jsonarr = json.loads(jsonresult)
i=0
while True:
	try:
		label = jsonarr["images"][0]["classifiers"][0]["classes"][i]["class"]
		score = jsonarr["images"][0]["classifiers"][0]["classes"][i]["score"]
		print(label + ":" + str(score))
		i=i+1
	except IndexError:
		break

    