package assignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 素数を求めるサービス
 */
public class CalculateService {

    /**
     * 素数を求める。<br>
     * <br>
     *
     * @param from
     * @param to
     * @return 求めた素数を JSON の配列で返す
     */
    public ArrayNode primeNumber(Long from, Long to) {

        // JSON の配列
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = mapper.createArrayNode();

        // 偶数だったらプラス１して奇数にする
        if (from % 2 == 0) {
            from++;
        }

        // 素数を求める
        boolean isPrimeNumber = true;
        double sqrt = 0.0;
        for(Long target = from; target <= to; target = target + 2) {

            // 0, 1 は素数ではないので false を返す。
            if (target < 2) {
                continue;
            }

            // 自身の平方根以下に、割り切れる数があった場合は素数でない。
            sqrt = Math.sqrt(target);
            for (int deivideNumber = 3; deivideNumber <= sqrt; deivideNumber = deivideNumber + 2) {
                if (target % deivideNumber == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }

            if (isPrimeNumber == true) {
                // JSON の配列に追加
                array.add(target);
            } else {
                isPrimeNumber = true;
                continue;
            }
        }

        return array;
    }
}
