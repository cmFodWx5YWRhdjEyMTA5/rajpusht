package in.co.rajpusht.rajpusht.model;

/**
 * Created by LENOVO on 20-Sep-18.
 */

public class CashCorrectionModel {

        private String name, reason, due, id;

    public CashCorrectionModel() {

    }


    public CashCorrectionModel(String name, String reason, String due, String id) {

        this.name = name;
        this.reason = reason;
        this.due = due;
        this.id = id;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
