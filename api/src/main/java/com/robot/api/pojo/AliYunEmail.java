package com.robot.api.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author robot
 * @date 2020/2/20 14:16
 *
 * 阿里云Email
 */
public class AliYunEmail  implements Serializable {
    private String accountName;
    private String fromAlias;
    private int addressType;
    private String tagName;
    private boolean replyToAddress;
    private List<String> toAddress;
    private String subject;
    private String htmlBody;
    private String method;
    private Map<String, Object> valueMap;
    private String templateHtml;



    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFromAlias() {
        return fromAlias;
    }

    public void setFromAlias(String fromAlias) {
        this.fromAlias = fromAlias;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isReplyToAddress() {
        return replyToAddress;
    }

    public void setReplyToAddress(boolean replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    public List<String> getToAddress() {
        return toAddress;
    }

    public void setToAddress(List<String> toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    public String getTemplateHtml() {
        return templateHtml;
    }

    public void setTemplateHtml(String templateHtml) {
        this.templateHtml = templateHtml;
    }
}
