import numpy as np
import random

def gradcheck1(f,x):
    rndstate=random.getstate()
    random.setstate(rndstate)

    fx,grad=f(x)
    h=1e-4
    it=np.nditer(x,flags=['multi_index'],op_flags=['readwrite'])
    while not it.finished:
        ix=it.multi_index
        x[ix]+=h
        random.setstate(rndstate)
        new_fx1=f(x)[0]
        x[ix]-=2*h
        random.setstate(rndstate)
        new_fx2=f(x)[0]
        num_grad=(new_fx1-new_fx2)/(2*h)

        # Compare gradients
        reldiff = abs(numgrad - grad[ix]) / max(1, abs(numgrad), abs(grad[ix]))
        if reldiff > 1e-5:
            print "Gradient check failed."
            print "First gradient error found at index %s" % str(ix)
            print "Your gradient: %f \t Numerical gradient: %f" % (
                grad[ix], numgrad)
            return

        it.iternext() # Step to next dimension

    print "Gradient check passed!"

