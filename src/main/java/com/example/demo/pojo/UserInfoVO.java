package com.example.demo.pojo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class UserInfoVO extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Range(min = 1900, max = 2100)
    @NotBlank
    private int birthYear;
    @NotBlank
    @Range(min = 1, max = 12)
    private int birthMonth;
    @NotBlank
    @Range(min = 1, max = 31)
    private int birthDate;

    public UserInfoVO() {

    }

    /**
     * @return the birthYear
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * @param birthYear the birthYear to set
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * @return the birthMonth
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /**
     * @param birthMonth the birthMonth to set
     */
    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    /**
     * @return the birthDate
     */
    public int getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

}
