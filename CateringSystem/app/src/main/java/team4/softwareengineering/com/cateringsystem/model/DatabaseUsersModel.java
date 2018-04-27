package team4.softwareengineering.com.cateringsystem.model;

import android.widget.DatePicker;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vikra on 4/19/2018.
 */

public class DatabaseUsersModel implements Serializable {

    private int userColumnUserId;
    private String userColumnUtaId;
    private String userColumnFirstName;
    private String userColumnLastName;
    private String userColumnEmailId;
    private String userColumnPhoneNumber;
    private String userColumnCategory;
    private String userColumnPassword;
    private String userColumnTimestamp;
   // private Date userColumnTimestamp;
    private String userColumnStatus;
    private String userColumnAddress;

    public int getUserColumnUserId() {
        return userColumnUserId;
    }

    public void setUserColumnUserId(int userColumnUserId) {
        this.userColumnUserId = userColumnUserId;
    }

    public String getUserColumnUtaId() {
        return userColumnUtaId;
    }

    public void setUserColumnUtaId(String userColumnUtaId) {
        this.userColumnUtaId = userColumnUtaId;
    }

    public String getUserColumnFirstName() {
        return userColumnFirstName;
    }

    public void setUserColumnFirstName(String userColumnFirstName) {
        this.userColumnFirstName = userColumnFirstName;
    }

    public String getUserColumnLastName() {
        return userColumnLastName;
    }

    public void setUserColumnLastName(String userColumnLastName) {
        this.userColumnLastName = userColumnLastName;
    }

    public String getUserColumnEmailId() {
        return userColumnEmailId;
    }

    public void setUserColumnEmailId(String userColumnEmailId) {
        this.userColumnEmailId = userColumnEmailId;
    }

    public String getUserColumnPhoneNumber() {
        return userColumnPhoneNumber;
    }

    public void setUserColumnPhoneNumber(String userColumnPhoneNumber) {
        this.userColumnPhoneNumber = userColumnPhoneNumber;
    }

    public String getUserColumnCategory() {
        return userColumnCategory;
    }

    public void setUserColumnCategory(String userColumnCategory) {
        this.userColumnCategory = userColumnCategory;
    }

    public String getUserColumnPassword() {
        return userColumnPassword;
    }

    public void setUserColumnPassword(String userColumnPassword) {
        this.userColumnPassword = userColumnPassword;
    }

    public String getUserColumnTimestamp() {
        return userColumnTimestamp;
    }
   public void setUserColumnTimestamp(String userColumnTimestamp) {
       this.userColumnTimestamp = userColumnTimestamp;
   }

    public String getUserColumnStatus() {
        return userColumnStatus;
    }

    public void setUserColumnStatus(String userColumnStatus) {
        this.userColumnStatus = userColumnStatus;
    }

    public String getUserColumnAddress() {
        return userColumnAddress;
    }

    public void setUserColumnAddress(String userColumnAddress) {
        this.userColumnAddress = userColumnAddress;
    }
}
