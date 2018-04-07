package ice.mobarak.test2.data.model;

import ice.mobarak.test2.utils.Constants;

/**
 * Created by Mobarak on 07-April-18.
 */

public class User {


    private String firstName;

    private String lastName;

    private String mobile;

    private String gender;

    private int photoId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    /**
     * Invoke get gender status. If true, gender is male
     * otherwise gender is female
     *
     * @return true|false
     */
    public boolean isMale() {
        return gender != null && gender.equalsIgnoreCase(Constants.MALE);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
