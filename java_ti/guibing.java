
class paixu{
    Comparable[] aux;
    public static void sort(Comparable[] a){
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }
    public static void sort(Comparable[] a,int lo,int hi){
        if(lo>=hi) return ;
        int mid=lo+(hi-lo)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }
    public static void merge(Comparable[] a,int lo,int mid,int hi){
        //将两个已排序的数组合并[lo,mid] [mid+1,hi]合并为[lo,hi]
        for(int i=lo;i<=hi;i++){
            aux[i]=a[i];
        }
        int k=lo;
        int j=mid+1;
        for(int l=lo;l<=hi;l++){
            if(j>mid) a[l]=aux[k++];
            if(k>hi) a[l]=aux[j++];
            if(aux[j]<aux[k]) a[l]=aux[j++];
            else a[l]=aux[k++];
        }
    }
    public static void Msort(Comparable[] a){
        int len=a.length;
        for(int sz=1;sz<len;sz*=2){//每次合并的大小都要不断翻倍
            for(int lo=0;lo<len-sz;lo+=sz+sz){//为什么lo<len-sz,因为最后只要合并到前一个sz
                //对于每个sz都要从头到尾都合并
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,len-1));
                //注意这里中间节点是lo+sz-1,尾节点为了反正最后长度超过len-1，将尾尾节点定义为最小值
            }
        }
    }
}


public class Solution {
    public int InversePairs(int [] array) {
        if(array==null||array.length<1) return 0;
        int len=array.length;
        int[] copy=new int[len];
        for (int i=0;i<len;i++){
            copy[i]=array[i];
        }
        int count=Inverse(array,copy,0,array.length-1);
        return count;
    }
    public int Inverse(int[] array,int[] copy,int lo,int hi){
        if(lo==hi) return 0;
        int mid=(lo+hi)>>1;
        int left=Inverse(array,copy,lo,mid);
        int right=Inverse(array,copy,mid+1,hi);
        int count=0;
        int i = lo, j = mid + 1;    
        int m = mid, n = hi;    
        int k = 0;    
        while (i <= m && j <= n) {    
            if (a[i] > a[j]) {    
                // 左数组比右数组大    
                temp[k++] = a[j++];    
                // 因为如果a[i]此时比右数组的当前元素a[j]大，    
                // 那么左数组中a[i]后面的元素就都比a[j]大    
                // 【因为数组此时是有序数组】    
                count += mid - i + 1;    
            } else {    
                temp[k++] = a[i++];    
            }    
        }    
        while (i <= m) {    
            temp[k++] = a[i++];    
        }    
        while (j <= n) {    
            temp[k++] = a[j++];    
        }
        for(int s=low;s<=high;s++)
        {
            array[s] = copy[s];
        }
        return (leftCount+rightCount+count)%1000000007;
    }
}

public int ReversePairs(int[] array){
    if(array==null||array.length==0) return 0;
    int len=array.length;
    int[] copy=new int[len];
    for(int i=0;i<len;i++){
        copy[i]=array[i];
    }
    int count=ReverseCore(array,copy,0,len-1);
}
public int ReverseCore(int[] array,int[] copy,int lo,int hi){
    if(lo==hi) return 0;
    int mid=(lo+hi)>>1;
    int left=ReverseCore(array,copy,lo,mid);
    int right=ReverseCore(array,copy,mid+1,hi);
    int i=lo,j=mid+1;
    int count=0;
    for(int k=lo;k<=hi;k++){
        if(i>mid) array[k]=copy[j++];
        if(j>hi) array[k]=copy[i++];
        if(copy[i]>copy[j]){
            array[k]=copy[j++];
            count=count+mid-i+1;
        }
        else
        {
            array[k]=copy[i++];
        }
    }
    for(int s=lo;s<=hi;s++){
        copy[s]=array[s];
    }
    return left+right+count;
}


public class Solution {
    public int InversePairs(int [] array) {
        if(array==null||array.length==0)
        {
            return 0;
        }
        int[] copy = new int[array.length];
        for(int i=0;i<array.length;i++)
        {
            copy[i] = array[i];
        }
        int count = InversePairsCore(array,copy,0,array.length-1);//数值过大求余
        return count;
          
    }
    private int InversePairsCore(int[] array,int[] copy,int low,int high)
    {
        if(low==high)
        {
            return 0;
        }
        int mid = (low+high)>>1;
        int leftCount = InversePairsCore(array,copy,low,mid)%1000000007;
        int rightCount = InversePairsCore(array,copy,mid+1,high)%1000000007;
        int count = 0;
        int i=low;
        int j=mid+1;
        int locCopy = high;
        for(int k=lo;k<=hi;k++){
            if(i>mid) copy[k]=array[j++];
            if(j>hi) copy[k]=array[i++];
            if(array[i]>array[j]){
                copy[k]=array[j++];
                count=count+mid-i+1;
                if(count>=1000000007)//数值过大求余
                {
                    count%=1000000007;
                }
            }
            else
            {
                copy[k]=array[i++];
            }
        }
        for(int s=low;s<=high;s++)
        {
            array[s] = copy[s];
        }
        return (leftCount+rightCount+count)%1000000007;
    }




        while(i>=low&&j>mid)
        {
            if(array[i]>array[j])
            {
                count += j-mid;
                copy[locCopy--] = array[i--];
                if(count>=1000000007)//数值过大求余
                {
                    count%=1000000007;
                }
            }
            else
            {
                copy[locCopy--] = array[j--];
            }
        }
        for(;i>=low;i--)
        {
            copy[locCopy--]=array[i];
        }
        for(;j>mid;j--)
        {
            copy[locCopy--]=array[j];
        }
        for(int s=low;s<=high;s++)
        {
            array[s] = copy[s];
        }
        return (leftCount+rightCount+count)%1000000007;
    }
}
添加笔记
