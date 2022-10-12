public class Bubble {

    private final int[] array;

    public Bubble(int[] arr) {
        this.array = arr;
        sort();
    }

    private void sort() {
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                Main.currentIndex = i + 1;
                Main.compareIndex = i;
                if (array[i] > array[i + 1]) {
                    swapped = true;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    Main.animate();
                }
            }
        }
    }
}
