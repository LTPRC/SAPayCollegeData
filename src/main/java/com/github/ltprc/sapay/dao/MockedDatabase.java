package com.github.ltprc.sapay.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.ltprc.sapay.bean.College;

/**
 * This is a mocked database which represents the DB2 database of Study Abroad Payment business.
 * @author tuoli
 *
 */
public enum MockedDatabase {
    Instance;
    /**
     * Key: collegeId
     * Value: College
     */
    private static Map<String, College> instance = new ConcurrentHashMap<>();
    public static Map<String, College> getInstance() {
        return instance;
    }
}
