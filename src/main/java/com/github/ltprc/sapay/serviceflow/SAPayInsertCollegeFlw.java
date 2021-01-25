package com.github.ltprc.sapay.serviceflow;

import java.util.ArrayList;
import java.util.List;

import com.github.ltprc.sapay.bean.College;
import com.github.ltprc.sapay.component.SAPayCollegeCmp;

public class SAPayInsertCollegeFlw {

    public static void main(String[] args) {

        SAPayCollegeCmp sapayCollegeCmp = new SAPayCollegeCmp();
        /**
         * Sync database.
         */
        sapayCollegeCmp.init();
        /**
         * Insert data.
         */
        List<College> inputList = new ArrayList<>();
        College college1 = new College();
        college1.setAccountName("ACCOUNTNAME");
        college1.setAccountNo("1234567890123456789");
        college1.setCollegeCode("COLLEGECODE");
        college1.setCollegeId("CI00000001");
        college1.setCollegeName("COLLEGENAME");
        college1.setCollegeNameCN("学校名称");
        college1.setCollegeNationId("001");
        college1.setCurrencyId("010");
        college1.setSwiftCode("12345678901");
        college1.setVendorId("ZFB");
        inputList.add(college1);
        for (College college : inputList) {
            sapayCollegeCmp.insertCollegeData(college);
        }
    }
}
