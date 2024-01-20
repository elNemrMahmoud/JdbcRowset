package jets.iti.java;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class User{

    private String id;
    private String deptId;
    private String fname;
    private String lname;
    private Date dob;


    public User() {
        // Default constructor
    }

    public User(String id, String deptId, String fname, String lname, Date dob) {
        this.id = id;
        this.deptId = deptId;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", deptId='" + deptId + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                '}';
    }
}