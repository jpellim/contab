package br.com.futureinnet.contab.exception;

public class BusinessException  extends Exception {
 
	private static final long serialVersionUID = 407900033327408638L;
	
	private String errMsg;

    public BusinessException() {
    }
 
    public BusinessException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
 
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
