package main.domain;

/**
 * Clasa derivată din Items care reprezintă articolele de tip medicamente pentru animale.
 */
public class Pharmacy extends Items{
	private String expirationDate, issue;
    private boolean prescriptionRequired;

    /**
     * Constructor implicit pentru un obiect Pharmacy.
     */
    //public Pharmacy() {}
     
    /**
     * Constructor pentru inițializarea unui articol de tip medicament.
     *
     * @param expirationDate      Data de expirare a medicamentului
     * @param prescriptionRequired Necesitatea prescripției medicale
     * @param issue               Problema rezolvată de medicament
     */
    public Pharmacy(int id, String name, String brand, String forAnimal, double price, int stock, String expirationDate, boolean prescriptionRequired, String issue) {
        super(id, name, brand, forAnimal, price, stock);
        this.expirationDate = expirationDate;
        this.prescriptionRequired = prescriptionRequired;
        this.issue = issue;
    }

    public String getExpirationDate() {
        return expirationDate;}

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;}

    public boolean isPrescriptionRequired() {
        return prescriptionRequired;}

    public void setPrescriptionRequired(boolean prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;}

    public String getIssueResolved() {
        return issue;}

    public void setIssueResolved(String issue) {
        this.issue = issue;}

    /**
     * Returnează o descriere a articolului de tip medicament sub formă de șir de caractere.
     *
     * @return Detaliile articolului de tip medicament
     */
    @Override
    public String toString() {
        return super.toString() + "\nExpiration Date: " + expirationDate + "\nPrescription Required: " + prescriptionRequired + "\nRecommended to combat: " + issue;
    }
}
