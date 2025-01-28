def min1 (lst):
	minSoFar = lst[0]
	for x in lst:
		if x < minSoFar:
			minSoFar = x
	return minSoFar

def min2 (lst):
	for x in lst:
		numSmaller = sum(y < x for y in lst)
		if numSmaller == 0:
			 return x

import time
import random
for f in [min1, min2]:
	print ("testing " + f.__name__)
	assert 1 == (f ([1, 12, 23, 3, 24, 25, 2]))
	assert 2 == (f ([12, 23, 3, 24, 25, 2]))
	assert 3 == (f ([12, 23, 3, 24, 25, 4]))
	assert 3 == (f ([12, 3, 24, 25]))
	base = 2000
	for multiplier in [1, 2, 4, 8]:
		length = base * multiplier
		lst = [random.random() for i in range(length)] # min random 
		#lst = [-i for i in range(length)] # min at end
		#lst = range(length) # min at beginning
		start = time.time()
		result = f (lst)
		end = time.time()
		print ("Duration of " + f.__name__ + "(" + str(len(lst)) + "): " + str(end - start))

def minPos1 (lst):
	minPosSoFar = 0
	for i in range(len(lst)):
		if lst[i] < lst[minPosSoFar]:
			minPosSoFar = i
	return minPosSoFar

assert 0 == (minPos1 ([1, 12, 23, 3, 24, 25, 2]))
assert 5 == (minPos1 ([12, 23, 3, 24, 25, 2]))
assert 2 == (minPos1 ([12, 23, 3, 24, 25]))
assert 1 == (minPos1 ([12, 3, 24, 25]))

def minPos2 (lst):
	minSoFar = lst[0]
	minPosSoFar = 0
	for i in range(len(lst)):
		if lst[i] < minSoFar:
			minPosSoFar = i
			minSoFar = lst[i]
	return minPosSoFar

assert 0 == (minPos2 ([1, 12, 23, 3, 24, 25, 2]))
assert 5 == (minPos2 ([12, 23, 3, 24, 25, 2]))
assert 2 == (minPos2 ([12, 23, 3, 24, 25]))
assert 1 == (minPos2 ([12, 3, 24, 25]))

def minPos3 (lst):
	minPosSoFar = 0
	n = len(lst)
	i = 1
	while i < n:
		if lst[i] < lst[minPosSoFar]:
			minPosSoFar = i
		i += 1
	return minPosSoFar

assert 0 == (minPos3 ([1, 12, 23, 3, 24, 25, 2]))
assert 5 == (minPos3 ([12, 23, 3, 24, 25, 2]))
assert 2 == (minPos3 ([12, 23, 3, 24, 25]))
assert 1 == (minPos3 ([12, 3, 24, 25]))

def minPosR (lst):
	return minPosHelper (lst, 1, 0)

def minPosHelper (lst, i, minPosSoFar):
	if i >= len(lst):
		return minPosSoFar
	if lst[i] < lst[minPosSoFar]:
		return minPosHelper (lst, i + 1, i)
	else:
		return minPosHelper (lst, i + 1, minPosSoFar)

assert 0 == (minPosR ([1, 12, 23, 3, 24, 25, 2]))
assert 5 == (minPosR ([12, 23, 3, 24, 25, 2]))
assert 2 == (minPosR ([12, 23, 3, 24, 25]))
assert 1 == (minPosR ([12, 3, 24, 25]))

print ("Finished tests")
