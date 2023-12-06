package assignment;

public class CalculatePrimeNumber100 {

    public static void main(String[] args) {

        System.out.println("100 個の素数を表示します。");
        System.out.println("============");

        int count = 0;
        int target = 0;
        while(count < 100) {
            if (calcPrimeNumber(target)) {
                count++;
                System.out.println(target);
            }
            target++;
        }

        System.out.println("============");
        System.out.println("計：" + count + "個");
    }

    /**
     * 素数か判定する。<br>
     * <br>
     *
     * @param target
     * @return 素数の場合 true を返す。素数ではない場合 false を返す。
     */
    private static boolean calcPrimeNumber(int target) {

        // 0, 1 は素数ではないので false を返す。
        if (target < 2) {
            return false;
        }

        // 自身の平方根以下に、割り切れる数があった場合は素数でない。
        for (int deivideNumber = 2; deivideNumber <= Math.sqrt(target); deivideNumber++) {
            if (target % deivideNumber == 0) {
                return false;
            }
        }

        return true;
    }
}


