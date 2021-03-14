package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: wangmingjian02
 * @create: 2020-12-15
 **/
public class Main {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        double[] data = {0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5};
        for (double d : data) {
            list.add(d);
        }
        System.out.println(list);
        System.out.println(getIndexByData(1.4, list));
    }

    public static int getIndexByData(double m, List<Double> data) {
        //获取大于 m 的第一个倍率的索引
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) > m) {
                return i;
            }
        }
        //当出现索引不存在的情况时，例如，data中最大为1.5，但是手动调压1.6，这样就获取不到大于1.6的第一个索引
        double maxMutiple = 0;
        int maxIndex = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) > maxMutiple) {
                maxMutiple = data.get(i);
                maxIndex = i;
            }
        }
        if (m >= maxMutiple) {
            return maxIndex;
        }

        throw new RuntimeException("data索引不存在!");
    }
}
