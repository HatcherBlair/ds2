package algs22;

public class MyMergeSort {

    // postcondition: a is sorted
    static void mergeSort(int[] a){
        int[] temp = new int[a.length];
        msAUX(a,0,a.length-1,temp);
    }

    // precondition: a[st..mid] and a[mid+1..end] are sorted
    // postcondition: a[st..end] is sorted
    static void magic(int[]a, int st, int mid,int end,int[] temp){
        for (int i= st; i<=end; i++){
            temp[i]=a[i];
        }
        int i= st; 
        int j = mid+1;
        
        for (int k=st; k<=end; k++){
            if (i>mid){ // left half done
                a[k]=temp[j];
                j++;
            }
            else if (j>end){ // right half done
                a[k]=temp[i];
                i++;
            }
            else if (temp[i]<=temp[j]){
                a[k]=temp[i];
                i++;
            }
            else{
                a[k]=temp[j];
                j++;
        }
        }


    }

    // postcondition: a[st..end] is sorted
    static void msAUX(int[]a, int st, int end,int[] temp){
        if (st>=end) return; // the portion of array to be sorted is of size 1 or 0
        int mid = (st+end)/2;  
        msAUX(a,st,mid,temp);      
        msAUX(a,mid+1,end,temp);    
        if (a[mid+1] < a[mid]) {
            magic(a,st,mid,end,temp); // merge the two sorted portions
            }



    }
    
}
