public class hw00 {
    public static int[] windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= 0) {
                continue;
            }
            for (int j = 1; j < n + 1; j++) {
                if (i + j < a.length) {
                    a[i] += a[i+j];
                }
            }
        }
        return a;
    }
    public static void main(String[] args) {
        int[] a = {1, -1, -1, 10, 5, -1};
        int n = 2;
        windowPosSum(a, n);

        System.out.println(java.util.Arrays.toString(a));
    }
}