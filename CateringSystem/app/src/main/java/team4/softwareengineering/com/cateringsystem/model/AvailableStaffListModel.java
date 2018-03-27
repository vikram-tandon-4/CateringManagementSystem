package team4.softwareengineering.com.cateringsystem.model;

/**
 * Created by vikra on 3/26/2018.
 */

public class AvailableStaffListModel {

    private String staffMemberName;
    private boolean selected;

    public String getStaffMemberName() {
        return staffMemberName;
    }

    public void setStaffMemberName(String staffMemberName) {
        this.staffMemberName = staffMemberName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
