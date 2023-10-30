<!-- inputForm.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人所得税计算</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }
        h1 {
            border: 1px solid  cornflowerblue;
            padding: 20px;
            border-radius: 5px;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 32px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #336699;
            color: #ffffff;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #265985;
        }

        #result {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .title {
            text-align: center;
        }
    </style>
</head>
<body>
<form action="computeTax" method="post" id="taxForm">
    <h1 class="title">个人所得税计算器</h1>
    <label for="income">月收入（税前）：</label>
    <input type="number" id="income" name="income" required>

    <label for="deduction">免税额/专项扣除（可选）：</label>
    <input type="number" id="deduction" name="deduction">

    <input type="submit" value="计算个人所得税">
    <input type="reset" value="重置">

    <div id="result">
        <h3>计算结果：</h3>
        <p class="color">当前月工资：<span id="currentSalary">无</span></p>
        <p>税率：<span id="taxRate">无</span></p>
        <p class="color">应缴纳个人所得税：<span id="taxResult">无</span> </p>
    </div>
</form>

<script>
    var taxForm = document.getElementById('taxForm');

    taxForm.addEventListener('submit', function (event) {
        event.preventDefault();
        calculateTax();
    });

    taxForm.addEventListener('reset', function () {
        resetResults();
    });

    function calculateTax() {
        // 获取输入的月收入和免税额/专项扣除
        var income = parseFloat(document.getElementById('income').value);
        var deduction = parseFloat(document.getElementById('deduction').value) || 0;

        // 发送POST请求到Servlet
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'computeTax', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 解析JSON格式的响应
                var response = JSON.parse(xhr.responseText);

                // 显示计算结果
                document.getElementById('currentSalary').textContent = response.currentSalary !== undefined ? response.currentSalary + ' 元' : '无';
                document.getElementById('taxRate').textContent = response.taxRate !== undefined ? response.taxRate + '%' : '无';
                document.getElementById('taxResult').textContent = response.taxResult !== undefined ? response.taxResult + ' 元' : '无';
            }
        };
        xhr.send('income=' + income + '&deduction=' + deduction);
    }

    function resetResults() {
        document.getElementById('currentSalary').textContent = '无';
        document.getElementById('taxRate').textContent = '无';
        document.getElementById('taxResult').textContent = '无';
    }
</script>
</body>
</html>
