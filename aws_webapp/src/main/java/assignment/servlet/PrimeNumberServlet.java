package assignment.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.node.ArrayNode;

import assignment.service.CalculateService;

/**
 * サーブレット
 */
public class PrimeNumberServlet extends HttpServlet {

    /**
     * パラメータを受け取る。<br>
     * <br>
     *
     * @param req
     * @param resp
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // from, to を受け取る
        Long from = Long.valueOf(req.getParameter("from"));
        Long to = Long.valueOf(req.getParameter("to"));

        // 素数を求める
        CalculateService calculateService = new CalculateService();
        ArrayNode results = calculateService.primeNumber(from, to);

        resp.setContentType("application/json");
        resp.getWriter().print(results);
    }
}


