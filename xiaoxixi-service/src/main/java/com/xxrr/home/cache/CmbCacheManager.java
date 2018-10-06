package com.xxrr.home.cache;

import com.xxrr.home.cache.caches.CmbBaseCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Collection;
import java.util.Collections;

public class CmbCacheManager extends AbstractCacheManager {
    public static final String DEFAULT_EXPRESSION_PREFIX = "#{";

    public static final String DEFAULT_EXPRESSION_SUFFIX = "}";

    private final Logger LOGGER = LoggerFactory.getLogger(CmbCacheManager.class);

    private final ExpressionParser parser = new SpelExpressionParser();

    private Collection<? extends Cache> caches;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        if (caches == null)
            return Collections.emptyList();
        else
            return caches;
    }

    public Cache getCache(String name) {
        String[] splitName = splitExpressions(name);
        if (StringUtils.isNotBlank(splitName[0])) {
            name = splitName[0];
        }

        Cache cache = super.getCache(name);
        if (cache == null) {
            throw new NullPointerException((new StringBuilder()).append("No such cache defined:").append(name).toString());
        }

        if (StringUtils.isNotBlank(splitName[1])) {
            Long exTime = paserExpressions(splitName[1]);
            if (null != exTime && exTime > 0) {
                ((CmbBaseCache) cache).getExTimes().set(exTime);
            }
        }
        return cache;
    }

    public void setCaches(Collection<? extends CmbBaseCache> caches) {
        this.caches = caches;
    }

    private String[] splitExpressions(String value) {
        String[] values = new String[2];
        try {
            int prefixIndex = value.indexOf(DEFAULT_EXPRESSION_PREFIX);
            if (prefixIndex > -1) {
                int afterPrefixIndex = prefixIndex + DEFAULT_EXPRESSION_PREFIX.length();
                int suffixIndex = value.indexOf(DEFAULT_EXPRESSION_SUFFIX, afterPrefixIndex);
                if (suffixIndex > -1) {
                    values[0] = value.substring(0, prefixIndex);
                    values[1] = value.substring(prefixIndex + DEFAULT_EXPRESSION_PREFIX.length(), suffixIndex).trim();
                }
            }
        } catch (Exception e) {
            LOGGER.error("splitExpressions string-{} Fail cause {}", value, e);
        }
        return values;
    }

    private Long paserExpressions(String expression) {
        try {
            Expression ex = parser.parseExpression(expression);
            return ex.getValue(Long.class);
        } catch (Exception e) {
            LOGGER.error("paserExpressions string-{} Fail cause {}", expression, e);
            return null;
        }
    }
}
