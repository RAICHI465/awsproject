package assignment.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import assignment.service.PrimeNumberFileService;

/**
 * 素数を求めるクラス
 */
public class PrimeNumberServlet extends HttpServlet {

    private final File FILE = new File("../resources/assignment/prime_number.txt");

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

        PrimeNumberFileService fileService = new PrimeNumberFileService();

        // 素数一覧から既に計算済みの素数を取得する
        List<Long> primeNumberList = fileService.readFile(FILE);

        // 素数を求める
        ArrayNode results = calcPrimeNumber(from, to, primeNumberList);

        // 素数一覧を更新する
        fileService.writeFile(FILE, results);

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


