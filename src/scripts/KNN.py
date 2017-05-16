import os,sys
import scipy
from PIL import Image
import numpy as np
import math
import array
from collections import Counter 

SCALEFACTOR = 15
IMAGEDIMENSION = SCALEFACTOR * SCALEFACTOR * 3


def euclideanDistance(im1,im2):

	diffArr = im1 - im2
	powArr = diffArr.astype(int)**2
	sumVal = sum(powArr)
	return math.sqrt(sumVal)

def manhattanDistance(im1,im2):
	diffArr = im1 - im2
	diffArr = np.absolute(diffArr)

	return sum(diffArr)

def getSubdirectories(parent_dir):
    return [name for name in os.listdir(parent_dir)
            if os.path.isdir(os.path.join(parent_dir, name))]

def getSimilarityValue(im1,im2):

	if(len(im1) > len(im2)):
		im1 = im1[0:len(im2)]

	elif(len(im2) > len(im1)):
		im2 = im2[0:len(im1)]

	if inputDistance == "euclid":
		return euclideanDistance(im1,im2)
	elif inputDistance == "manhattan":
		return manhattanDistance(im1,im2)


def loadDataset():
	if not os.path.exists(datasetPath):
		print()
		sys.exit()
	for classFolderIterator in os.listdir(datasetPath):
		if(os.path.isfile(os.path.join(datasetPath,classFolderIterator))):
			continue

		currentClass = classFolderIterator.lower()
		for imageIterator in os.listdir(os.path.join(datasetPath,classFolderIterator)):
			imageFileName = os.path.join(datasetPath,classFolderIterator,imageIterator)#

			try:
				im = Image.open(imageFileName)
				scaledIm = im.resize((SCALEFACTOR,SCALEFACTOR))
				sampleImageArr =  np.array(scaledIm).flatten()
				datasetMatrix.append([sampleImageArr,currentClass])

			except IOError:
				continue
		

def datasetFileExists():
	return os.path.isfile(datasetMatrixFilePath)

def addResult(val,imgclass,valuesArr,classesArr):

	if(val == 0):	# the same image
		return

	if len(valuesArr) < parameterK:
		valuesArr.append(val)
		classesArr.append(imgclass)
	else:
		if(val < np.amax(valuesArr)):
			replaceIndex = np.argmax(valuesArr)
			valuesArr[replaceIndex] = val
			classesArr[replaceIndex] = imgclass


	return

def getWinnerClass(valuesArr, classesArr):

	sortedArr = [item for items, c in Counter(classesArr).most_common() for item in [items] * c]
	occurArr = Counter(sortedArr).most_common()

	myDict = dict()
	for i in range(0,len(classesArr)):
		s = classesArr[i]
		myDict[s] = 0

	if( len(occurArr) == 1 or (occurArr[0][1] != occurArr[1][1]) ): #majority class exists
		return occurArr[0][0]


	else: #no majority class 
		bestOccur = occurArr[0][1]

		for i in range(0,len(classesArr)):
			s = classesArr[i]
			myDict[s] += valuesArr[i]

		for elem in occurArr:	
			if elem[1] != bestOccur:
				del myDict[elem[0]]

		for key in myDict:
			myDict[key] = myDict[key] / bestOccur

		winnerClass = min(myDict, key=myDict.get)

		return winnerClass


def classifyImage(inputImage):

	valuesArr = []
	classesArr = []

	im = Image.open(inputImage)
	scaledIm = im.resize((SCALEFACTOR,SCALEFACTOR))

	processedImageArr = np.array(scaledIm).flatten()

	for i in range(0,len(datasetMatrix)):
		sampleImageArr = datasetMatrix[i][0]
		val = getSimilarityValue(processedImageArr,sampleImageArr)
		addResult(val,datasetMatrix[i][1],valuesArr,classesArr)


	return getWinnerClass(valuesArr,classesArr)
#----------------------------------------------------------

global referenceImage
payloadArr = list()
#-----------------------------------------------------

if len(sys.argv) < 5:
	print()
	sys.exit()

datasetPath = str(sys.argv[1])
inputImage = str(sys.argv[2])
inputK = str(sys.argv[3])
inputDistance = str(sys.argv[4])
scalingFactor = str(sys.argv[5])

parameterK = int(inputK)
parameterDistance = inputDistance


datasetMatrixFilePath = os.path.join(datasetPath,"datasetMatrix.npy")


datasetMatrix = []

if ( datasetFileExists() == False ):

	SCALEFACTOR = int(scalingFactor)
	loadDataset()
	np.save(datasetMatrixFilePath,datasetMatrix)
else:
	datasetMatrix = np.load(datasetMatrixFilePath)

resClass = classifyImage(inputImage)

print(resClass + ":" + str(0))