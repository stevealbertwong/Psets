import numpy as np

string1 = "SADAS love cara so      much , withlove     from      stevenlove"

string3 = "1 2 3 4 5 6"

print np.random.randn(10,6)
print "\n"
print np.random.randn(10,6)[2,2] # 2nd row, 2nd col -> only 1 item
print "\n"
print np.random.randn(10,6)[:,:] # : means all -> all rows, all cols
print "\n"
print np.random.randn(10,6)[2,:4] # 2nd row, first 4 cols
print "\n"
print np.random.randn(10,6)[2,:] # 2nd row, all cols
print "\n"
print np.random.randn(10,6)[:3,4:] # first 3 rows, last 2 cols
print "\n"
print np.random.randn(10,6)[8:,:] # last 2 rows, all cols

print "\n"
print np.random.randn(10,6).shape[0]

# print string1.lower().decode("utf-8")
# print string3.split()[4:]
# print string3.split()[:4]
#
#
# my_string = "blah, lots  ,  of ,  spaces, here "
# print [x.strip() for x in my_string.split(',')]