import time

run = 0
## python lists are mutable, whereas strings are immutable 
def main0():
  print ("main0")

  l = [11, 21, 31, 41]
  k = l
  print (l)
  k.append (51)
  print (l, k)

  s = "@@@@"
  t = s
  print (s)
  s = s + "*"
  print (s, t)

#run = 1 ## uncomment to run main1
## confirm that python copies strings when necessary
## to avoid unnecessary copying, the python compiler does alias analysis
##   if s is aliased,     then (end - middle) = O(n)
##   if s is NOT aliased, then (end - middle) = O(1)
def main1 ():
  print ("main1")
  base = 2 ** 25
  length = base
  while length <= base * 32:
    length = length * 2
    t = ""
    s = "*"
    ## create a big string
    while len(s) < length:
      s = s + s
      
    start = time.time()    
    #t = s  ## try (un)commenting this
    middle = time.time()
    s = s + "*" 
    end = time.time()
    
    print ("Duration (" + str(len(s)) + "," + str(len(t)) + "): " + '{0:.8f}'.format(middle - start) + "," + '{0:.8f}'.format(end - middle))

#run = 2
## confirm that python does not copy lists
##   regardless of whether s is aliased, (end - middle) = O(1)
def main2 ():
  print ("main2")
  base = 2 ** 25
  length = base
  while length <= base * 8:
    length = length * 2
    t = []
    s = ["*"]
    ## create a big list
    while len(s) < length:
      s = s + s
    
    start = time.time()    
    #t = s ## try (un)commenting this
    middle = time.time()
    s.append(0)
    end = time.time()
    
    print ("Duration (" + str(len(s)) + "," + str(len(t)) + "): " + '{0:.8f}'.format(middle - start) + "," + '{0:.8f}'.format(end - middle))

def addOne (s):
  return s + "*"

#run = 3
## This test allows you to time list and string creation
## try uncommenting lines 1-6, one at a time.
def main3 ():
  print ("main3")
  base = 10000  
  for multiplier in [1, 2, 4, 8, 16, 32]:
    length = base * multiplier
    l = []
    s = ""
    start = time.time()
    for i in range (length):
      l.append("*")          ## 1. list append at end           
      #l.insert(len(l), "*")  ## 2. list append at end
      #l.insert(0, "*")       ## 3. list prepend at beginning
      #s = s + "*"            ## 4. string append at end
      #t = s + "*"            ## 5a. string append at end with temporary
      #s = t                  ## 5b.   (this goes with 5a)
      #s = addOne (s)         ## 6. string append with function call
    end = time.time()
    print ("Duration (" + str(length) + "): " + '{0:.8f}'.format(end - start))
 
mains = [main0, main1, main2, main3]
mains[run]()
# for m in mains:
#     m() 
