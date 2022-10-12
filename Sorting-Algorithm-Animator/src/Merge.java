public class Merge {

    private final int[] array;

    public Merge(int[] arr) {
        this.array = arr;
        sort(array);
    }

    private void sort(int[] arr) {
        int inputLength = arr.length;

        if (inputLength < 2) {
            return;
        }

        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = arr[i];
            Main.animate();
        }

        for (int i = midIndex; i < arr.length; i++) {
            rightHalf[i - midIndex] = arr[i];
            Main.animate();
        }

        sort(leftHalf);
        sort(rightHalf);
        merge(arr, leftHalf, rightHalf);
        Main.animate();
    }

    private void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int l = 0, r = 0, m = 0;

        while (l < leftSize && r < rightSize) {
            if (leftHalf[l] <= rightHalf[r]) {
                Main.compareIndex = l;
                arr[m] = leftHalf[l];
                Main.animate();
                l++;
            }
            else {
                Main.compareIndex = r;
                arr[m] = rightHalf[r];
                Main.animate();
                r++;
            }

            Main.currentIndex = m;
            m++;
        }

        while (l < leftSize) {
            Main.animate();
            arr[m] = leftHalf[l];
            l++;
            m++;
        }

        while (r < rightSize) {
            Main.animate();
            arr[m] = rightHalf[r];
            r++;
            m++;
        }
    }
}
