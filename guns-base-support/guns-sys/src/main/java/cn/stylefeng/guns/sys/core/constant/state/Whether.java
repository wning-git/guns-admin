package cn.stylefeng.guns.sys.core.constant.state;


public enum Whether {
    NO("N", "否"),YES("Y", "是");
    private String code;
    private String message;

    public static Whether getByCode(String code){
        switch (code) {
            case "Y":
                return YES;
            case "N":
                return NO;
            default: {
                String err = code + " is not a valid " + Whether.class.getSimpleName();
                throw new IllegalArgumentException(err);
            }
        }
    }


    Whether(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
