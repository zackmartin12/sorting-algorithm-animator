public class Quick {

    private final int[] array;
    private final int start;
    private final int end;

    public Quick(int[] arr, int s, int e) {
        this.array = arr;
        this.start = s;
        this.end = e;

        sort(array, start, end);
    }

    private void sort(int[] arr, int s, int e) {
        if (e <= s) {
            return;
        }

        int pivot = partition(arr, s, e);

        Main.animate();

        sort(arr, s, pivot - 1);
        sort(arr, pivot + 1, e);
    }

    private int partition(int[] arr, int s, int e) {
        int pivot = arr[e];

        int i = s - 1;
        for (int j = s; j <= e - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                Main.compareIndex = i;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                Main.animate();
            }
            Main.currentIndex = j + 1;
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[e];
        arr[e] = temp;

        Main.animate();

        return i;
    }
}
