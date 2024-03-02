public class Eveniment {
    public RBTree arboreParticipanti;
    private String scopVoluntariat;
    private String nume;
    private String ora;
    private String adresa;

    public Eveniment(String nume, String ora, String adresa, String scopVoluntariat) {
        this.nume = nume;
        this.ora = ora;
        this.adresa = adresa;
        this.scopVoluntariat = scopVoluntariat;
        this.arboreParticipanti = new RBTree();
    }

    public RBTree getArboreParticipanti() {
        return arboreParticipanti;
    }

    public void setArboreParticipanti(RBTree arboreParticipanti) {
        this.arboreParticipanti = arboreParticipanti;
    }

    public String getScopVoluntariat() {
        return scopVoluntariat;
    }

    public void setScopVoluntariat(String scopVoluntariat) {
        this.scopVoluntariat = scopVoluntariat;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Eveniment{" +

                "scopVoluntariat='" + scopVoluntariat + '\'' +
                ", nume='" + nume + '\'' +
                ", ora='" + ora + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}