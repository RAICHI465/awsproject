package assignment.service;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 素数一覧を操作するクラス
 */
public class PrimeNumberFileService {

    /**
     * 素数一覧から素数を読み取る。<br>
     * <br>
     *
     * @param file
     * @return 素数のリスト
     */
    public List<Long> readFile(File file) {

        List<Long> primeNumberList = new ArrayList<>();

        try {

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

        return primeNumberList;
    }

    /**
     * 素数一覧を更新する。<br>
     * <br>
     *
     * @param file
     * @param results
     */
    public void writeFile(File file, ArrayNode results) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (JsonNode result : results) {

                writer.write(String.valueOf(result));
                writer.newLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
