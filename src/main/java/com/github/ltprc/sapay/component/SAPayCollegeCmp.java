package com.github.ltprc.sapay.component;

import java.util.List;

import com.github.ltprc.sapay.bean.College;
import com.github.ltprc.sapay.dao.CollegeUnionSet;
import com.github.ltprc.sapay.dao.SAPayCollegeDAO;

public class SAPayCollegeCmp {

    private SAPayCollegeDAO sapayCollegeDAO = new SAPayCollegeDAO();

    public void init() {
        List<College> colleges = sapayCollegeDAO.getAllCollegeData();
        for (College college : colleges) {
            List<College> relatedColleges = sapayCollegeDAO.queryCollegeData(college.getRelatedCollegeId());
            for (College relatedCollege : relatedColleges) {
                CollegeUnionSet.forcedAddNode(college, relatedCollege);
            }
        }
    }

    public int insertCollegeData(College college) {
        List<College> existingColleges = sapayCollegeDAO.queryRepeatedCollegeData(college);
        if(!existingColleges.isEmpty()) {
            /**
             * Replace the existing data.
             */
            for (College existingCollege : existingColleges) {
                deletingTransaction(existingCollege);
            }
        }
        insertingTransaction(college);
        return 1;
    }

    public int deleteCollegeDataByCollegeId(String collegeId) {
        List<College> existingColleges = sapayCollegeDAO.queryCollegeData(collegeId);
        if(!existingColleges.isEmpty()) {
            for (College existingCollege : existingColleges) {
                deletingTransaction(existingCollege);
            }
        }
        return existingColleges.size();
    }

    public int updateCollegeDataByCollegeId(College college) {
        String collegeId = college.getCollegeId();
        deleteCollegeDataByCollegeId(collegeId);
        return insertCollegeData(college);
    }

    public List<College> queryCollegeData(College college) {
        return sapayCollegeDAO.queryCollegeData(college.getCollegeId());
    }

    public void insertingTransaction(College college) {
        sapayCollegeDAO.insertCollegeData(college);
        CollegeUnionSet.addNode(college);
    }

    public void deletingTransaction(College college) {
        CollegeUnionSet.removeNode(college);
        sapayCollegeDAO.deleteCollegeData(college);
    }

    public void save() {
        List<College> colleges = sapayCollegeDAO.getAllCollegeData();
        for (College college : colleges) {
            List<College> relatedColleges = sapayCollegeDAO.queryCollegeData(college.getRelatedCollegeId());
            for (College relatedCollege : relatedColleges) {
                CollegeUnionSet.forcedAddNode(college, relatedCollege);
            }
        }
    }
}
