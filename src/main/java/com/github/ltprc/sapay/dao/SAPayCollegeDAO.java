package com.github.ltprc.sapay.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.github.ltprc.sapay.bean.College;

public class SAPayCollegeDAO {

    private Map<String, College> database = MockedDatabase.getInstance();

    public void insertCollegeData(College college) {
        if (StringUtils.isAllEmpty(college.getCollegeId()) || database.containsKey(college.getCollegeId())) {
            throw new RuntimeException();
        }
        database.put(college.getCollegeId(), college);
    }

    public void deleteCollegeData(College college) {
        if (StringUtils.isAllEmpty(college.getCollegeId()) || !database.containsKey(college.getCollegeId())) {
            throw new RuntimeException();
        }
        database.remove(college.getCollegeId());
    }

    public void updateCollegeData(College college) {
        if (StringUtils.isAllEmpty(college.getCollegeId()) || !database.containsKey(college.getCollegeId())) {
            throw new RuntimeException();
        }
        database.put(college.getCollegeId(), college);
    }

    public List<College> queryCollegeData(String collegeId) {
        if (StringUtils.isAllEmpty(collegeId) || !database.containsKey(collegeId)) {
            throw new RuntimeException();
        }
        List<College> result = new ArrayList<>();
        result.add(database.get(collegeId));
        return result;
    }

    /**
     * Check whether there is a existing college record that has the identical collegeCode, collegeNationId, accountNo, currencyId, swiftCode, vendorId.
     * @param college
     * @return
     */
    public List<College> queryRepeatedCollegeData(College college) {
        if (StringUtils.isAllEmpty(college.getCollegeId())) {
            throw new RuntimeException();
        }
        List<College> result = new ArrayList<>();
        for (Entry<String, College> entry : database.entrySet()) {
            College existedCollege = entry.getValue();
            if (checkStringEqual(college.getCollegeCode(), existedCollege.getCollegeCode())
                    && checkStringEqual(college.getCollegeNationId(), existedCollege.getCollegeNationId())
                    && checkStringEqual(college.getAccountNo(), existedCollege.getAccountNo())
                    && checkStringEqual(college.getCurrencyId(), existedCollege.getCurrencyId())
                    && checkStringEqual(college.getSwiftCode(), existedCollege.getSwiftCode())
                    && checkStringEqual(college.getVendorId(), existedCollege.getVendorId())) {
                result.add(existedCollege);
            }
        }
        return result;
    }

    public List<College> getAllCollegeData() {
        List<College> result = new ArrayList<>();
        result.addAll(database.values());
        return result;
    }

    private boolean checkStringEqual(String str1, String str2) {
        if (str1 == str2) {
            return true;
        } else if (str1 == null || str2 == null) {
            return false;
        } else {
            return str1.equals(str2);
        }
    }
}
