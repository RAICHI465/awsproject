package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 素数を求めるクラス
 */
public class PrimeNumberServlet extends HttpServlet {

    /**
     * ホストサーバから範囲を受け取り、範囲内の素数を求める。<br>
     * <br>
     *
     * @param req
     * @param resp
     */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ホストサーバから from, to を受け取る
        Long from = Long.valueOf(req.getParameter("from"));
        Long to = Long.valueOf(req.getParameter("to"));

        // 素数一覧から既に計算済みの素数を取得する
        List<Long> primeNumberList = new ArrayList<>();
        File file = new File("../resources/assignment/prime_number.txt");
        try {
            // 素数一覧読み込み
            String num;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));

            while ((num = reader.readLine()) != null) {
                primeNumberList.add(Long.valueOf(num));
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 昇順にソート
        Collections.sort(primeNumberList);

        // 素数を求める
        ArrayNode results = calcPrimeNumber(from, to, primeNumberList);

        // 素数一覧を更新する
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (JsonNode result : results) {
                writer.write(String.valueOf(result));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        resp.setContentType("application/json");
        resp.getWriter().print(results);
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

        // 素数を求めてJSONの配列に追加する
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


