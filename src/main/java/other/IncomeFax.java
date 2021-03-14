package other;

/**
 * @description:
 * @author: wangmingjian02
 * @create: 2020-11-01
 **/

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class IncomeFax {

    public static void main(String[] args) {
        // 小王每月工资24000，浮动奖金为0，专项扣除费为2500，北京的五险一金比例为22.2%
        getMoney(27000, 15.5);
        getMoney(24000, 15.5);
        getMoney(21000, 15.5);
    }

    public static void getMoney(int defaultSal, double month) {
        int special = 1500;
        double insuranceRate = 0.222;
        List<Double> money = getMonthlySal(defaultSal, new double[12], special, insuranceRate);
        money.add(getYearEndBonus(defaultSal, month - 12));
        print(money);
        System.out.println("税前" + defaultSal * month);
    }

    public static void print(List<Double> salArr) {
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < salArr.size() - 1; i++) {
            System.out.println((i + 1) + "月：" + df.format(salArr.get(i)));
        }
        System.out.println("年终：" + df.format(salArr.get(salArr.size() - 1)));
        System.out.println("共计：" + df.format(salArr.stream().mapToDouble(Double::doubleValue).sum()));
    }

    /**
     * @param defaultSal 基本工资
     * @param bonus      浮动奖金（绩效工资）一年内拿了几次工资就输入几个
     * @param special    专项扣除
     * @param insurance  五险一金比例
     */
    public static List<Double> getMonthlySal(double defaultSal, double[] bonus, double special, double insurance) {
        // 累计预扣缴额
        double totalNeedTax = 0.0;
        // 累计已缴税额
        double totalHadTax = 0.0;
        // 每月工资
        List<Double> salArr = new ArrayList<>();
        for (double b : bonus) {
            // 当月预扣缴额(每月的应缴税 = 基本工资 + 浮动奖金(绩效工资) - 起征点 - 专项扣除 - 五险一金)
            double shouldTax = defaultSal + b - 5000 - special - defaultSal * insurance;
            totalNeedTax += shouldTax;
            // 累计应缴税额(累计应缴税额 * 税率 - 速算扣除数`)
            double totalTax;
            if (totalNeedTax <= 36000.0) {
                totalTax = totalNeedTax * 0.03;
            } else if (totalNeedTax <= 144000.0) {
                totalTax = totalNeedTax * 0.1 - 2520;
            } else if (totalNeedTax <= 300000.0) {
                totalTax = totalNeedTax * 0.2 - 16920;
            } else if (totalNeedTax <= 420000.0) {
                totalTax = totalNeedTax * 0.25 - 31920;
            } else if (totalNeedTax <= 660000.0) {
                totalTax = totalNeedTax * 0.30 - 52920;
            } else if (totalNeedTax <= 960000.0) {
                totalTax = totalNeedTax * 0.35 - 85920;
            } else {
                totalTax = totalNeedTax * 0.45 - 181920;
            }
            // 当月应缴税(当月累计应缴税 - 上月累计应缴税(累计已缴税))
            double curTax = totalTax - totalHadTax;
            // 当月工资(当月工资 = 基本工资 + 浮动奖金(绩效工资) - 当月应缴税 - 五险一金)
            double curSal = defaultSal + b - curTax - defaultSal * insurance;
            salArr.add(curSal);
            totalHadTax = totalTax;
        }
        return salArr;
    }

    public static double getYearEndBonus(double defaultSal, double month) {
        double bonus = defaultSal * month;
        // 年终奖应缴税
        double tax;
        // 累计应缴税额(累计应缴税额 * 税率 - 速算扣除数)
        if (bonus <= 36000.0) {
            tax = bonus * 0.03;
        } else if (bonus <= 144000.0) {
            tax = bonus * 0.1 - 210;
        } else if (bonus <= 300000.0) {
            tax = bonus * 0.2 - 1410;
        } else if (bonus <= 420000.0) {
            tax = bonus * 0.25 - 2660;
        } else if (bonus <= 660000.0) {
            tax = bonus * 0.30 - 4410;
        } else if (bonus <= 960000.0) {
            tax = bonus * 0.35 - 7160;
        } else {
            tax = bonus * 0.45 - 15160;
        }
        // 税后年终奖(税后年终奖 = 税前年终奖 - 年终奖缴税)
        return bonus - tax;
    }
}
