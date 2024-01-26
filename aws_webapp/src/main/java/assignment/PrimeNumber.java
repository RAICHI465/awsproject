package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PrimeNumber {

    public static void main(String[] args) {

        // ホストサーバから from, to を受け取る
        Long from;
        Long to;

        // 素数一覧から既に計算済みの素数を取得する
        List<Long> primeNumberList = new ArrayList<>();
        // 素数一覧読み込み

        // 昇順にソート
        Collections.sort(primeNumberList);

        // 素数を求める
        calcPrimeNumber(from, to, primeNumberList);
    }

    /**
     * 素数を計算する。<br>
     * <br>
     *
     * @param from
     * @param to
     * @param primeNumberList
     * @return 求めた素数をJSONの配列で返す。
     */
    private static ArrayNode calcPrimeNumber(Long from, Long to, List<Long> primeNumberList) {

        // JSON の配列
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = mapper.createArrayNode();

        for (Long target = from; target < to; from++) {
            primeNumberList.forEach(divideNumber -> {
                if ((target % divideNumber) != 0) {
                    array.add(target);
                }
            });
        }

        return array;
    }
}


