package platform.entity;

public class Wpis {
    String code;
    String date;
    int time;
    int views;

    public Wpis(String code, String date) {
        this.code = code;
        this.date = date;
    }

    public Wpis(String code, String date, int time, int views) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Wpis{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                ", views=" + views +
                '}';
    }
}
