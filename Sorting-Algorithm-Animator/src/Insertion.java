public class Insertion {

    private final int[] array;

    public Insertion(int[] arr) {
        this.array = arr;
        sort();
    }

    private void sort() {
        for (int i = 1; i < array.length; i++) {
            Main.currentIndex = i;
            int j = i;

            while ((j > 0) && (array[j - 1] > array[j])) {
                Main.compareIndex = j - 1;
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;

                Main.animate();

                j--;
            }
        }
    }
}
