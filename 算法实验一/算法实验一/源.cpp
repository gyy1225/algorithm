#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#define MAXSIZE 10
void swap(int *a, int *b)
{
	int temp;
	temp = *a;
	*a = *b;
	*b = temp;
}
void swap(int *a, int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}
//插入排序
void InsertionSort(int *arr, int size)
{
	int i, j, tmp;
	for (i = 1; i < size; i++) {
		if (arr[i] < arr[i - 1]) {
			tmp = arr[i];
			for (j = i - 1; j >= 0 && arr[j] > tmp; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = tmp;
		}
	}
}
//合并排序
void Merge(int *SR, int *TR, int i, int middle, int rightend)
{
	int j, k, l;
	for (k = i, j = middle + 1; i <= middle && j <= rightend; k++) {
		if (SR[i] < SR[j]) {
			TR[k] = SR[i++];
		}
		else {
			TR[k] = SR[j++];
		}
	}
	if (i <= middle) {
		for (l = 0; l <= middle - i; l++) {
			TR[k + l] = SR[i + l];
		}
	}
	if (j <= rightend) {
		for (l = 0; l <= rightend - j; l++) {
			TR[k + l] = SR[j + l];
		}
	}
}

void MergeSort(int *SR, int *TR1, int s, int t)
{
	int middle;
	int TR2[MAXSIZE + 1];
	if (s == t) {
		TR1[s] = SR[s];
	}
	else {
		middle = (s + t) / 2;
		MergeSort(SR, TR2, s, middle);
		MergeSort(SR, TR2, middle + 1, t);
		Merge(TR2, TR1, s, middle, t);
	}
}
//快速排序算法
//快速排序数组划分
int partition(int *a, int left, int right) {
	int x = a[right];
	int p = left - 1;
	for (int i = left; i < right; i++) {
		if (a[i] <= x) {
			p++;
			swap(a, p, i);
		}
	}
	swap(a, p + 1, right);
	return p + 1;
}
void QuickSort(int *a, int left, int right) {
	if (left < right) {
		int p = partition(a, left, right);
		QuickSort(a, left, p - 1);
		QuickSort(a, p + 1, right);
	}
}

//随机快速排序算法
 int randomPartition(int *a, int left, int right) {
	 int r = rand() %( right-left+1) + left; //生成一个随机数，即是主元所在位置
	 swap(a, right, r); //将主元与序列最右边元素互换位置，这样就变成了之前快排的形式。
	 return partition(a, left, right); //直接调用之前的代码
 }

 void RandomQuickSort(int *a, int left, int right) {
	 if (left < right) {
		 int p = randomPartition(a, left, right);
		 RandomQuickSort(a, left, p - 1);
		 RandomQuickSort(a, p + 1, right);
	 }
 }
 
//计数排序算法
void CountingSort(int *A, int *B, int n, int k)
{
	int *C = (int *)malloc(sizeof(int) * (k + 1));
	int i;
	for (i = 0; i <= k; i++) {
		C[i] = 0;
	}
	for (i = 0; i < n; i++) {
		C[A[i]]++;
	}
	for (i = 1; i <= k; i++) {
		C[i] = C[i] + C[i - 1];
	}
	for (i = n - 1; i >= 0; i--) {
		B[C[A[i]] - 1] = A[i];
		C[A[i]]--;
	}
}






//桶排序
void BucketSort(int *arr, int size)
{
	int max = 10;
	int i, j;
	int buckets[MAXSIZE];
	memset(buckets, 0, max * sizeof(int));
	for (i = 0; i < size; i++) {
		buckets[arr[i]]++;
	}
	for (i = 0, j = 0; i < max; i++) {
		while ((buckets[i]--) >0)
			arr[j++] = i;
	}
}
void printarr(int *a, int length) {
	for (int i = 0; i < length; i++)
	{
		printf("%d ", a[i]);
	}
	printf("\n");
}
//基数排序算法
//计数排序算法，a为待排序数组(一位数，要和atemp区分开)，b为排序好的数组,c为中间数组，atemp为原始数组(包括三位数)
void innercounting_sort(int a[], int b[], int c[], int atemp[])
{
	int i, j;
	for (i = 0; i < MAXSIZE; i++)
		c[i] = 0;
	for (j = 0; j < MAXSIZE; j++)
		c[a[j]] += 1;
	for (i = 1; i < MAXSIZE; i++)
		c[i] = c[i] + c[i - 1];
	for (j = 9; j >= 0; j--) {
		b[c[a[j]] - 1] = atemp[j];
		c[a[j]] -= 1;
	}
}
void RadixSort(int *a,int len,int size){
int atemp[10];
int i;
int b[10]; //output array
int c[10]; //temp all element are not biger than 10

		   // 个位排序
for (int i = 0; i < len; i++)
	atemp[i] = a[i] % 10;
innercounting_sort(atemp, b, c, a);
for (int i = 0; i < len; i++)
	a[i] = b[i];

// 十位排序
for (int i = 0; i < len; i++)
	atemp[i] = a[i] / 10 % 10;
innercounting_sort(atemp, b, c, a);
for (i = 0; i < len; i++)
	a[i] = b[i];
//百位排序
for (i = 0; i < len; i++)
	atemp[i] = a[i] / 100 % 10;
innercounting_sort(atemp, b, c, a);
for (i = 0; i < len; i++)
	a[i] = b[i];
}


int main() {
	int insertsort[] = {45,7,9,3,8,77,24,86,1,32};
	int mergesort_even[]= { 45,7,9,3,8,77,24,86,1,32 };
	int mergesort_odd[] = { 45,7,9,3,8,77,24,86,1 };
	int quicksort[] = { 45,7,9,3,8,77,24,86,1,32,54,123 };
	int quicksort2[] = { 45,7,9,3,8,77,24,86,1,32,54,123 };
	int countsort[] = { 4,4,8,8,8,6,2,7,1,1,3,2 };
	int bucketsort[] = { 9,2,5,7,6,3,4,8,1,12 };
	int radixsort[] = { 234,55,57,48,482,428,6,61,34,3 };
	
	InsertionSort(insertsort, 10);
	printarr(insertsort, 10);

	int merge[10] = { 0 };
	MergeSort(mergesort_even, merge, 0, 9);
	printarr(insertsort, 10);
	MergeSort(mergesort_odd, merge, 0, 8);
	printarr(insertsort, 9);

	QuickSort(quicksort, 0, 11);
	printarr(quicksort, 12);

   
	RandomQuickSort(quicksort2, 0, 11);
	printarr(quicksort2, 12);

	int count[12] = { 0 };
	CountingSort(countsort, count, 12, 8);
	printarr(count, 12);

	RadixSort(radixsort,10,3);
	printarr(radixsort, 10);

	BucketSort(bucketsort, 10);
	printarr(bucketsort, 10);
	system("pause");
}