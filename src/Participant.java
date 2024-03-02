public class Participant {
    private String numeIntreg;
    private String nrTelefon;
    private int varsta;
    public Participant(String numeIntreg, String nrTelefon, int varsta) {
        this.numeIntreg = numeIntreg;
        this.nrTelefon = nrTelefon;
        this.varsta = varsta;
    }

    public String getNumeIntreg() {
        return numeIntreg;
    }

    public void setNumeIntreg(String numeIntreg) {
        this.numeIntreg = numeIntreg;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "numeIntreg='" + numeIntreg + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}
