def numFives1 (a, i=0):
	if len(a) == i: 
		return 0
	result = numFives1 (a, i+1)
	if (a[i] == 5.0):
		result += 1
	return result

def numFives2 (a):
	if len(a) == 0: 
		return 0
	result = numFives2 (a[1:])
	if (a[0] == 5.0):
		result += 1
	return result

import time
import sys
sys.setrecursionlimit(10**6)
for f in [numFives1, numFives2]:
	print ("testing " + f.__name__)
	assert 0 == (f ([ ]))
	assert 1 == (f ([ 5 ]))
	assert 0 == (f ([ 11 ]))
	assert 3 == (f ([ 5, 5, 5 ]))
	assert 0 == (f ([ 11, 21, 31, 41 ]))
	assert 1 == (f ([ 5, 11, 21, 31, 41 ]))
	assert 1 == (f ([ 11, 21, 31, 41, 5 ]))
	assert 1 == (f ([ 11, 21, 5, 31, 41 ]))
	assert 3 == (f ([ 11, 21, 5, 31, 5, 41, 5]))
	base = 2000
	for multiplier in [1, 2, 4, 8]:
		length = base * multiplier
		lst = [i for i in range(length)]				
		start = time.time()
		result = f (lst)
		end = time.time()
		print ("Duration of " + f.__name__ + "(" + str(len(lst)) + "): " + str(end - start))

print ("Finished tests")
