package binary;

public class Binary {

    static int[] indx(int nums[], int target) {

        int ans[] = new int[2];

        int c[] = { -1, -1 };

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {

            int mid = (i + j) / 2;
            if (nums[mid] < target)
                i = mid + 1;
            else
                j = mid;
        }

        if (nums[i] != target)
            return c;

        else {
            ans[0] = i;
            j = nums.length - 1;
        }

        while (i < j) {

            int mid = (i + j) / 2 + 1;

            if (nums[mid] > target)
                j = mid - 1;
            else
                i = mid;
        }

        ans[1] = j;
        return ans;
    }

    public static void main(String[] args) {

        int a[] = { 1, 2, 3, 5, 5, 5, 5, 5, 7, 8, 8, 8, 9 };
        int ans[] = indx(a, 5);
        System.out.println(ans[0] + " " + ans[1]);

    }

}
