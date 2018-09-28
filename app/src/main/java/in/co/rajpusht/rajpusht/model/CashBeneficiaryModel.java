package in.co.rajpusht.rajpusht.model;

/**
 * Created by LENOVO on 18-Sep-18.
 */

public class CashBeneficiaryModel {
    private String name, phase, status, installment, paid, aadharId, id;


    public CashBeneficiaryModel(String name, String phase, String status, String installment, String paid, String aadharId, String id) {
        this.name = name;
        this.phase = phase;
        this.status = status;
        this.installment = installment;
        this.paid = paid;
        this.aadharId = aadharId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAadharId() {
        return aadharId;
    }

    public void setAadharId(String aadharId) {
        this.aadharId = aadharId;
    }

    public CashBeneficiaryModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
