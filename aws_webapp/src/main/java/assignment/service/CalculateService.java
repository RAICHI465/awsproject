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

        ObjectMapper mapper = new ObjectMapper();
        // JSON の配列
        ArrayNode array = mapper.createArrayNode();

        // 素数の内 2 のみ偶数のため、ここで追加しておく
        if (from <= 2) {
            array.add(2);
        }

        // 偶数は素数ではないので、プラス１して奇数にする
        if (from % 2 == 0) {
            from++;
        }

        boolean isPrimeNumber = true;
        double sqrt = 0.0;

        // ここに来る target は奇数のため、プラス 2 ずつして偶数を飛ばす
        for(Long target = from; target <= to; target = target + 2) {

            // 1 以下に素数はないのでスキップする
            if (target < 2) {
                continue;
            }

            // 素数を求める
            sqrt = Math.sqrt(target);
            // 奇数が偶数で割り切れることはないので、プラス 2 ずつして偶数を飛ばす
            for (int deivideNumber = 3; deivideNumber <= sqrt; deivideNumber = deivideNumber + 2) {
                if (target % deivideNumber == 0) {
                    // 自身の平方根以下に、割り切れる数があった場合は素数でない
                    isPrimeNumber = false;
                    break;
                }
            }

            if (isPrimeNumber == true) {
                // JSON の配列に追加する
                array.add(target);
            } else {
                isPrimeNumber = true;
                continue;
            }
        }

        return array;
    }
}
