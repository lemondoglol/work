import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		MergeSort ob = new MergeSort();
		int[] arr = {4,1,2,5,6,2,11};
		System.out.println(Arrays.toString(ob.sort(arr)));
	}
	
	public int[] sort(int[] nums) {
		if(nums.length <= 1) return nums;
		int mid = nums.length / 2;
		int[] leftArr = new int[mid];
		int size = nums.length % 2 == 0 ? mid : mid + 1;
		int[] rightArr = new int[size];
		for(int i = 0; i < mid; i++) leftArr[i] = nums[i];
		for(int i = 0; i < size; i++) rightArr[i] = nums[mid + i];
		return merge(sort(leftArr), sort(rightArr));
	}
	
	public int[] merge(int[] nums1, int[] nums2) {
		int i = 0, j = 0, index = 0;
		int[] res = new int[nums1.length + nums2.length];
		while(i < nums1.length && j < nums2.length) {
			if(nums1[i] < nums2[j]) {
				res[index++] = nums1[i++];
			}else {
				res[index++] = nums2[j++];
			}
		}
		while(i < nums1.length) res[index++] = nums1[i++];
		while(j < nums2.length) res[index++] = nums2[j++];
		return res;
	}

}
