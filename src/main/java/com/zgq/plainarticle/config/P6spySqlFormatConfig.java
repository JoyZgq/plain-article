package com.zgq.plainarticle.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.time.LocalDateTime;

/**
 * 自定义 p6spy sql输出格式
 *
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

    /**
     * 过滤掉定时任务的 SQL
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss")
                + " | 耗时 " + elapsed + " ms | SQL 语句："+ sql.replaceAll("[\\s]+", " ") + ";" : "";
    }
}
