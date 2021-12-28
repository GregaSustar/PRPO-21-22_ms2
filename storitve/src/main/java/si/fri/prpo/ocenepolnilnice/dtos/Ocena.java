package si.fri.prpo.ocenepolnilnice.dtos;

public class Ocena {

    private Uporabnik uporabnik;
    private Integer ocena;
    private String komentar;
    private String prevod;

    public Ocena(){}

    public Ocena(Uporabnik uporabnik, Integer ocena, String komentar) {
        this.uporabnik = uporabnik;
        this.ocena = ocena;
        this.komentar = komentar;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getPrevod() {
        return prevod;
    }

    public void setPrevod(String prevod) {
        this.prevod = prevod;
    }
}
