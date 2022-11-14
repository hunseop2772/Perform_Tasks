public class VocaDTO {
    private int idx;
    private String eng;
    private String kor;
    private int lev;
    private String rdate;

    public VocaDTO(int idx, String eng, String kor, int lev, String rdate) {
        this.idx = idx;
        this.eng = eng;
        this.kor = kor;
        this.lev = lev;
        this.rdate = rdate;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getKor() {
        return kor;
    }

    public void setKor(String kor) {
        this.kor = kor;
    }

    public int getLev() {
        return lev;
    }

    public void setLev(int lev) {
        this.lev = lev;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    @Override
    public String toString() {
        return "VocaDTO{" +
                "idx=" + idx +
                ", eng='" + eng + '\'' +
                ", kor='" + kor + '\'' +
                ", lev=" + lev +
                ", rdate='" + rdate + '\'' +
                '}';
    }
}
