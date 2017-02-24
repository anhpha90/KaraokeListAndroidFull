package vn.com.pqs.model;

import java.io.Serializable;

/**
 * Created by long on 03/12/2016.
 */
public class DiaDiem implements Serializable {
    private int id;
    private String FullName;
    private String FullNameNA;
    private String wardId;
    private String ward;
    private String wardNA;
    private String wardType;
    private String location;
    private String districtId;
    private String district;
    private String districtNA;
    private String districtType;
    private String provinceId;
    private String province;
    private String provinceNA;
    private String provinceType;

    public DiaDiem() {
    }

    public DiaDiem(int id, String fullName, String fullNameNA, String wardId, String ward, String wardNA, String wardType, String location, String districtId, String district, String districtNA, String districtType, String provinceId, String province, String provinceNA, String provinceType) {
        this.id = id;
        FullName = fullName;
        FullNameNA = fullNameNA;
        this.wardId = wardId;
        this.ward = ward;
        this.wardNA = wardNA;
        this.wardType = wardType;
        this.location = location;
        this.districtId = districtId;
        this.district = district;
        this.districtNA = districtNA;
        this.districtType = districtType;
        this.provinceId = provinceId;
        this.province = province;
        this.provinceNA = provinceNA;
        this.provinceType = provinceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getFullNameNA() {
        return FullNameNA;
    }

    public void setFullNameNA(String fullNameNA) {
        FullNameNA = fullNameNA;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getWardNA() {
        return wardNA;
    }

    public void setWardNA(String wardNA) {
        this.wardNA = wardNA;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictNA() {
        return districtNA;
    }

    public void setDistrictNA(String districtNA) {
        this.districtNA = districtNA;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceNA() {
        return provinceNA;
    }

    public void setProvinceNA(String provinceNA) {
        this.provinceNA = provinceNA;
    }

    public String getProvinceType() {
        return provinceType;
    }

    public void setProvinceType(String provinceType) {
        this.provinceType = provinceType;
    }
}