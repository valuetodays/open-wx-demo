package com.billy.demo.open.entity.wx;

/**
 * @author liulei@bshf360.com
 * @since 2018-03-15 15:50
 */
public class WxVerifyMsg {
    // 微信加密签名
    private String signature;

    private String timestamp;  // 时间戳

    private String nonce;  // 随机数
    private String msg_signature; // 消息体签名
    private String encrypt_type; // 加密类型，为aes


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    public String getEncrypt_type() {
        return encrypt_type;
    }

    public void setEncrypt_type(String encrypt_type) {
        this.encrypt_type = encrypt_type;
    }
}
