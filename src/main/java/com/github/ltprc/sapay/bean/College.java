package com.github.ltprc.sapay.bean;

/**
 * College POJO.
 * @author tuoli
 *
 */
public class College {

    /**
     * The unique sequence id for the college data.
     */
    private String collegeId;

    /**
     * Id which represents the identity of the college data.
     */
    private String collegeCode;

    /**
     * College name in English.
     */
    private String collegeName;

    /**
     * College name in Chinese.
     */
    private String collegeNameCN;

    /**
     * Id of nation/region.
     */
    private String collegeNationId;

    /**
     * Account number.
     */
    private String accountNo;

    /**
     * AccountName.
     */
    private String accountName;

    /**
     * Currency type id.
     */
    private String currencyId;

    /**
     * Swift code.
     */
    private String swiftCode;

    /**
     * Vendor id.
     */
    private String vendorId;

    /**
     * (Only for storage) Related college id.
     */
    private String relatedCollegeId;

    /**
     * (Only for storage) size of the collection.
     */
    private int size;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeNameCN() {
        return collegeNameCN;
    }

    public void setCollegeNameCN(String collegeNameCN) {
        this.collegeNameCN = collegeNameCN;
    }

    public String getCollegeNationId() {
        return collegeNationId;
    }

    public void setCollegeNationId(String collegeNationId) {
        this.collegeNationId = collegeNationId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getRelatedCollegeId() {
        return relatedCollegeId;
    }

    public void setRelatedCollegeId(String relatedCollegeId) {
        this.relatedCollegeId = relatedCollegeId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
