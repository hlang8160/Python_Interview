#coding=utf-8
import numpy as np
def softmax(x):
    origin_shape=x.shape
    if len(x.shape)>1:
        #如果输入的x是矩阵,对每一行进行操作，返回一个
        exp_minmax=lambda x: np.exp(x-np.max(x))
        denom=lambda x: 1/np.sum(x)
        # 按照行对每个值先求出exp
        x=np.apply_along_axis(exp_minmax,1,x)#对每一个数都求exp(x-max)
        denominator=np.apply_along_axis(denom,1,x)#之后按照行求得每行的总和，是一个1×原矩阵列数
        if len(denominator.shape)==1:
            denominator=denominator.reshape((denominator.shape[0],1))#最后将denominator换成原有的列数×1
        x=x*denominator
    else:
        x_max=np.max(x)
        x=x-x_max
        numerator=np.exp(x)#将原有向量求出exp(x)
        denominator=1.0/np.sum(numerator)#求出向量的和
        x=numerator.dot(denominator)#修改了原有向量
    assert x.shape==origin_shape
    return x
def test_softmax():
    print "Running basic tests..."
    test1 = softmax(np.array([1, 2]))
    print test1
    ans1 = np.array([0.26894142, 0.73105858])
    assert np.allclose(test1, ans1, rtol=1e-05, atol=1e-06)

    test2 = softmax(np.array([[1001, 1002], [3, 4]]))
    print test2
    ans2 = np.array([
        [0.26894142, 0.73105858],
        [0.26894142, 0.73105858]])
    assert np.allclose(test2, ans2, rtol=1e-05, atol=1e-06)

    test3 = softmax(np.array([[-1001, -1002]]))
    print test3
    ans3 = np.array([0.73105858, 0.26894142])
    assert np.allclose(test3, ans3, rtol=1e-05, atol=1e-06)

    print "You should be able to verify these results by hand!\n"

if __name__=="__main__":
    test_softmax()
