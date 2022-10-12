public class Selection {

    private final int[] array;

    public Selection(int[] arr) {
        this.array = arr;
        sort();
    }

    private void sort() {
        for (int i = 0; i < array.length - 1; i++) {
            Main.currentIndex = i;
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                Main.compareIndex = min;
                if (array[min] > array[j]) {
                    min = j;
                }
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;

            Main.animate();
        }
    }
}
