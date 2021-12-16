public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public static void main(String[] args) {

        Main M = new Main();
        M.OneThread();
        M.MultiThread();
    }

    /* Однопоточный метод */

    public void OneThread() {

        /* Заполняем массив единицами*/
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }

        /*Засекаем время и делаем рассчеты*/
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();

        /*Выводим результат*/
        System.out.println("Время работы однопоточного метода: " + (System.currentTimeMillis() - a) + " ms.");
        System.out.println();
    }

    /* Многопоточный метод */

    public void MultiThread() {

        /* Заполняем массив единицами*/
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }

        /* Создаем два полумассива*/
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        /* Засекаем время и делим массив на два*/
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.currentTimeMillis();

        /* Выводим результат разбивки массива на два*/
        System.out.println("Время разбивки массива на два (многопоточный метод): " + (System.currentTimeMillis() - a) + " ms.");

        /* Засекаем время, создаем поток для вычисления первого полумассива*/
        long a11 = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < h; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }});
        thread1.start();
        System.currentTimeMillis();

        /* Выводим результат вычисления*/
        System.out.println("Время расчета первого полумассива (многопоточный метод): " + (System.currentTimeMillis() - a11) + " ms.");

        /* Засекаем время, создаем поток для вычисления второго полумассива*/
        long a22 = System.currentTimeMillis();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < h; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }});
        thread2.start();
        System.currentTimeMillis();

        /* Выводим результат вычисления*/
        System.out.println("Время расчета второго полумассива (многопоточный метод): " + (System.currentTimeMillis() - a22) + " ms.");

        long b = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.currentTimeMillis();
        System.out.println("Время склейки массива (многопоточный метод): " + (System.currentTimeMillis() - b) + " ms.");

    }

}


