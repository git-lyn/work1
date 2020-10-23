package com.lyn.juc.sf;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-05-18 23:20
 * 使用对应的堆排序算法，进行对应的数据的排序操作
 * 堆排序对应的时间复杂度为： O(n * log n)
 **/
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = {7, 65, 3, 5, 5, 7, 24, 99, 88, 71};
        heapSort104(arr);
        for (int temp : arr) {
            System.out.print(temp + " ");
        }
        System.out.println();
    }

    public static void heapSort(int[] array) {
        // 创建对应的大顶堆结构
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap22(array, i, array.length);
        }
        // 大顶堆数据进行排序
        for (int j = array.length - 1; j > 0; j--) {
            // 将大顶堆中的根元素，放到数组的最后
            swap22(array, 0, j);
            // 重新进行对应的堆排序，去掉数组的最后一个
            adjustHeap22(array, 0, j);
        }
    }

    // 进行相应的堆数据的调整和数据的处理
    public static void adjustHeap22(int[] array, int i, int length) {
        // 进行相应的数组数据的调整，实现对应的堆顶数据的处理
        int temp = array[i];
        for (int k = i * 2 + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && array[k + 1] > array[k]) {
                k++;
            }
            if (array[k] > temp) {
                swap22(array, i, k);
                i = k;
            } else {
                break;
            }
        }

    }

    /**
     * 交换数组中的两个数
     *
     * @param array
     * @param i
     * @param k
     */
    public static void swap22(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }


    public static void sort(int[] array) {
        // 按照完全二叉树的特点，从最后一个非叶子节点开始，对于整棵树进行大根堆的调整
        // 也就是说，是按照自下而上，每一层都是自右向左来进行调整的
        // 注意，这里元素的索引是从0开始的
        // 另一件需要注意的事情，这里的建堆，是用堆调整的方式来做的
        // 堆调整的逻辑在建堆和后续排序过程中复用的
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换
            // 说是交换，其实质就是把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
    }

    /**
     * @param
     * @return
     * @description 这里，是整个堆排序最关键的地方，正是因为把这个方法抽取出来，才更好理解了堆排序的精髓，会尽可能仔细讲解
     * @author yuzhao.yang
     * @time 2018年3月9日 下午2:54:38
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        // 可以参照sort中的调用逻辑，在堆建成，且完成第一次交换之后，实质上i=0；也就是说，是从根所在的最小子树开始调整的
        // 接下来的讲解，都是按照i的初始值为0来讲述的
        // 这一段很好理解，如果i=0；则k=1；k+1=2
        // 实质上，就是根节点和其左右子节点记性比较，让k指向这个不超过三个节点的子树中最大的值
        // 这里，必须要说下为什么k值是跳跃性的。
        // 首先，举个例子，如果a[0] > a[1]&&a[0]>a[2],说明0,1,2这棵树不需要调整，那么，下一步该到哪个节点了呢？肯定是a[1]所在的子树了，
        // 也就是说，是以本节点的左子节点为根的那棵小的子树
        // 而如果a[0}<a[2]呢，那就调整a[0]和a[2]的位置，然后继续调整以a[2]为根节点的那棵子树，而且肯定是从左子树开始调整的
        // 所以，这里面的用意就在于，自上而下，自左向右一点点调整整棵树的部分，直到每一颗小子树都满足大根堆的规律为止
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }

            // 如果发现子节点更大，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 下面就是非常关键的一步了
                // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
                // 所以，循环对子节点所在的树继续进行判断
                i = k;
                // 如果不用交换，那么，就直接终止循环了
            } else {
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a   元素的下标
     * @param b   元素的下标
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void heapSort3(int[] arr) {
        // 建立对应的大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap3(arr, i, arr.length);
        }
        // 进行大顶堆的排序
        for (int j = arr.length - 1; j > 0; j--) {
            swap3(arr, 0, j);
            adjustHeap3(arr, 0, j);
        }

    }

    public static void adjustHeap3(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                // 交换两个数据
                swap3(arr, i, k);
                i = k;
            } else {
                break;
            }
        }
    }

    public static void swap3(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }


    public static void heapSort4(int[] arr) {
        // 创建大顶堆的数据结构
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 创建对应的带i赢得同时在及逆行相应的数据集的和处理和对一个的
            // 实现对应的队列的是数据的狗嘎嘎嘎进行相应的数据的处理和对应的
            // 数据的整合对应的输一局的在机型相应的数据的实现给对应的
            // 实现对应的对应的
            //
            adjustHeap4(arr, i, arr.length);
        }
        // 对当前的大顶堆进行数据的排序操作
        for (int j = arr.length - 1; j > 0; j--) {
            // 将大顶堆的root数据放置到数据的尾端
            swap3(arr, 0, j);
            adjustHeap4(arr, 0, j);
        }
    }

    public static void adjustHeap4(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                swap4(arr, i, k);
                i = k;
            } else {
                break;
            }
        }
    }

    public static void swap4(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }


    public static void heapSort5(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap5(arr, i, arr.length);
        }
        // 进行对应的大顶堆的数据的排序操作
        for (int j = arr.length - 1; j > 0; j--) {
            // 将堆顶的数据放到数组的尾部
            swap5(arr, 0, j);
            adjustHeap5(arr, 0, j);
        }
    }

    public static void adjustHeap5(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                swap5(arr, i, k);
                i = k;
            } else {
                break;
            }
        }
    }

    /**
     * 实现数组中的两个数的交换操作
     *
     * @param arr
     * @param i
     * @param k
     */
    public static void swap5(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    /**
     * 进行堆顶的数据的处理操作
     *
     * @param arr
     */
    public static void heapSort6(int[] arr) {
        // 创建对应的大顶堆的数据结构
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap5(arr, i, arr.length);
        }
        // 进行大顶堆的数据的排序
        // 将堆顶的root 数据和最后一个数据进行减缓
        for (int j = arr.length - 1; j > 0; j--) {
            swap5(arr, 0, j);
            adjustHeap6(arr, 0, j);
        }
    }

    public static void adjustHeap6(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] < arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                swap5(arr, i, k);
                i = k;
            } else {
                break;
                // 吃奶你NBA的是去吗
                // y一个山东福建的侧墙在进行
                // 吃你的面对
                // shanb   shi shxiian duiying de
                // 在进行相应的数据的处理和对应的输一局的整合处理
            }
        }
    }

    /**
     * 使用堆排序算法进行数据的排序
     */
    public static void heapSort2(int[] arr) {
        // 创建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap2(arr, i, arr.length);
        }
        // 进行数据的堆排序
        for (int i = arr.length - 1; i > 0; i--) {
            swap5(arr, 0, i);
            adjustHeap2(arr, 0, i);
        }

    }

    public static void adjustHeap2(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    /**
     * 采用堆排序算法： 平均时间复杂度： n * log n     最差时间复杂度：
     * 是否稳定： 不稳定
     */
    public static void heapSort12(int[] arr) {
        // 创建对应的大顶堆数据
        for (int i = arr.length / 2 - 1; i >= 0; i++) {
            adjustHeap12(arr, i, arr.length);
        }

        // 对大顶堆数据进行排序
        for (int i = arr.length - 1; i > 0; i--) {
            swap5(arr, 0, i);
            adjustHeap12(arr, 0, i);
        }

    }

    public static void adjustHeap12(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < arr.length; k = k * 2 + 1) {
            if (k + 1 < arr.length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    // 使用堆排序算法进行数据的排序
    public static void heapSort8(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap8(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i++) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap8(arr, 0, i);
        }
    }

    public static void adjustHeap8(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k > 0; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (temp > arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    public static void heapSort9(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap9(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap9(arr, 0, i);
        }
    }

    public static void adjustHeap9(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    public static void heapSort101(int[] arr){
        for(int i = arr.length / 2 - 1;i >= 0;i--){
            adjustHeap101(arr,i,arr.length);
        }
        for(int i = arr.length - 1;i > 0;i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap101(arr,0,i);
        }
    }

    public static void adjustHeap101(int[] arr,int i,int length){
        int temp = arr[i];
        for(int k = i * 2 + 1;k < length;k=k*2+1){
            if(k + 1 < length && arr[k + 1] > arr[k]){
                k++;
            }
            if(arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }


    public static void heapSort102(int[] arr){
        for(int i = arr.length / 2 - 1;i >= 0;i--) {
            // 创建对应的大顶堆
            adjustHeap102(arr,i,arr.length);
        }
        for(int i = arr.length - 1;i > 0;i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap102(arr,0,i);
        }
    }

    public static void adjustHeap102(int[] arr,int i,int length){
        int temp = arr[i];
        for(int k = i * 2 + 1;k < length;k=k*2+1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    public static void heapSort103(int[] arr) {
        for(int i = arr.length / 2 - 1;i >= 0;i--) {
            adjustHeap103(arr,i,arr.length);
        }
        for(int i = arr.length - 1;i > 0;i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap103(arr, 0, i);
        }
    }

    public static void adjustHeap103(int[] arr,int i,int length){
        int temp = arr[i];
        for(int k = i * 2 + 1;k < length;k=k*2+1) {
            if (k + 1 < length && arr[k + 1] < arr[k]) {
                k++;
            }
            if (arr[k] < temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    public static void heapSort104(int[] arr) {
        for(int i = arr.length / 2 - 1;i >= 0;i--) {
            // 创建大顶堆
            adjustHeap104(arr, i, arr.length);
        }
        for(int i = arr.length - 1;i > 0;i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap104(arr,0,i);
        }
    }

    public static void adjustHeap104(int[] arr, int i, int length) {
        int temp = arr[i];
        for(int k = i * 2 + 1;k < length;k=k*2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }




}


