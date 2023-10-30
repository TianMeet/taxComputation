package org.tian.service.servlet;

import org.tian.service.ClientForTax;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/computeTax")
public class TaxComputationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=UTF-8");

        // 获取从JSP页面传递过来的参数
        double income = Double.parseDouble(req.getParameter("income"));
        double deduction = Double.parseDouble(req.getParameter("deduction"));

        // 进行个人所得税的计算逻辑
        double[] result = computeTax(income, deduction);

        // 构造JSON格式的响应
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("currentSalary", result[0]);
        resultMap.put("taxRate", result[1]);
        resultMap.put("taxResult", result[2]);

        // 将计算结果发送回JSP页面
        resp.getWriter().write(mapToJson(resultMap));
    }

    private double[] computeTax(double income, double deduction) {
        // 调用ClientForTax的计算方法
        double[] result = ClientForTax.computeTax(income - deduction);
        return result;
    }

    // 将Map转换成JSON格式的字符串
    private String mapToJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                json.append("\"").append(entry.getValue()).append("\",");
            } else {
                json.append(entry.getValue()).append(",");
            }
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1); // 移除最后一个逗号
        }
        json.append("}");
        return json.toString();
    }
}
