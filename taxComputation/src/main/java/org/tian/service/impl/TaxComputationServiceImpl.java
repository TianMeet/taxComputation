package org.tian.service.impl;

import org.tian.service.TaxComputationService;

public class TaxComputationServiceImpl implements TaxComputationService {

    @Override
    public double[] computationTax(double income) {
        double deduction = 0; // 免税额/专项扣除，根据实际情况设定
        double taxableIncome = income - deduction;

        // 如果月收入低于等于5000元，则不需要缴纳个人所得税
        if (taxableIncome <= 5000) {
            return new double[]{income, 0, 0}; // 返回税后收入、税率（百分比）、个人所得税
        }

        // 计算个人所得税
        double taxRate;

        if (taxableIncome <= 8000) {
            taxRate = 0.03; // 5000到8000的档位，税率为3%
        } else if (taxableIncome <= 17000) {
            taxRate = 0.1; // 8000到17000的档位，税率为10%
        } else if (taxableIncome <= 30000) {
            taxRate = 0.2; // 17000到30000的档位，税率为20%
        } else if (taxableIncome <= 40000) {
            taxRate = 0.25; // 30000到40000的档位，税率为25%
        } else if (taxableIncome <= 60000) {
            taxRate = 0.35; // 40000到60000的档位，税率为35%
        } else {
            taxRate = 0.45; // 60000以上的档位，税率为45%
        }

        double tax = (taxableIncome - 5000) * taxRate;

        double[] result = {income - tax, taxRate * 100, tax}; // 返回税后收入、税率（百分比）、个人所得税
        return result;
    }

}
