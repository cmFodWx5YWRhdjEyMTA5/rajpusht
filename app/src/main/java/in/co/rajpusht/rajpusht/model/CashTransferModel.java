package in.co.rajpusht.rajpusht.model;

/**
 * Created by LENOVO on 18-Sep-18.
 */

public class CashTransferModel {
    private String name,received, submitted, due, eligible, exit;

    public CashTransferModel() {
    }

    public CashTransferModel(String name, String received, String submitted, String due, String eligible, String exit) {
        this.name = name;
        this.received = received;
        this.submitted = submitted;
        this.due = due;
        this.eligible = eligible;
        this.exit = exit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getEligible() {
        return eligible;
    }

    public void setEligible(String eligible) {
        this.eligible = eligible;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }
}
