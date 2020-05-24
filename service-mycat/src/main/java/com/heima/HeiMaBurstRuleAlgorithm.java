package com.heima;

import io.mycat.config.model.rule.RuleAlgorithm;
import io.mycat.route.function.AbstractPartitionAlgorithm;

public class HeiMaBurstRuleAlgorithm extends AbstractPartitionAlgorithm implements RuleAlgorithm{
    @Override
    public Integer calculate(String s) {
        return null;
    }

    @Override
    public Integer[] calculateRange(String beginValue, String endValue) {
        return super.calculateRange(beginValue, endValue);
    }
}
