package model.Output;

public class OutputText {

    private String response;
    private int code;

    public OutputText() {}

    public OutputText(int code,String respose) {
        this.response = respose;
        this.code = code;
    }

    public String getRespose() {
        return response;
    }

    public void setRespose(String respose) {
        this.response = respose;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
