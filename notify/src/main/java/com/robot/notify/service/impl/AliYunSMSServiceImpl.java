package com.robot.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.robot.api.pojo.AliYunEmail;
import com.robot.notify.service.AliYunSMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author robot
 * @date 2020/2/16 23:07
 **/
@Service
public class AliYunSMSServiceImpl implements AliYunSMSService {

    private static final Logger logger = LoggerFactory.getLogger(AliYunSMSService.class);

    @Resource
    private TemplateEngine templateEngine;

    @NacosValue(value = "${aliYunAccessKeySecret:123}", autoRefreshed = true)
    private String aliYunAccessKeySecret;

    @NacosValue(value = "${aliYunAccessKeyId:123}",autoRefreshed = true)
    private String aliYunAccessKeyId;
    //发送email
    @Override
    public boolean sendEmail(AliYunEmail aliYunEmail) {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的”cn-hangzhou”替换为”ap-southeast-1”、或”ap-southeast-2”。
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliYunAccessKeyId, aliYunAccessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendMailRequest request = new SingleSendMailRequest();
            request.setAccountName(aliYunEmail.getAccountName());
            request.setFromAlias(aliYunEmail.getFromAlias());
            request.setAddressType(1);
            request.setAddressType(aliYunEmail.getAddressType());
            request.setTagName(aliYunEmail.getTagName());
            request.setReplyToAddress(true);
            request.setReplyToAddress(aliYunEmail.isReplyToAddress());
            request.setToAddress(ctvString(aliYunEmail.getToAddress()));
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress(“邮箱1,邮箱2”);
            request.setSubject(aliYunEmail.getSubject());
            //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
            //注意：文本邮件的大小限制为3M，过大的文本会导致连接超时或413错误
            Context context = new Context();
            context.setVariables(aliYunEmail.getValueMap());
            String html = templateEngine.process(aliYunEmail.getTemplateHtml(), context);
            request.setHtmlBody(html);
            //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
            //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
            request.setMethod(MethodType.POST);
            //开启需要备案，0关闭，1开启
//            request.setClickTrace("0");
            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (Exception e) {
            logger.info("sendMail fail aliYunEmail:{}", JSONObject.toJSONString(aliYunEmail), e);
            return false;
        }
        return true;
    }


    private static String createHtml(String code, String operation) {
        String head = "<div id=\"qm_con_body\"><div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\" style=\"\">\n" +
                "<style type=\"text/css\">\n" +
                "\n" +
                "  /** RESET STYLES **/\n" +
                "  .qmbox p{margin:1em 0;} /*Yahoo paragraph fix*/\n" +
                "  .qmbox table td{border-collapse:collapse;} /*This resolves the Outlook 07, 10, and Gmail td padding issue fix*/ \n" +
                "  .qmbox img, .qmbox a img{border:0; height:auto; outline:none; text-decoration:none;} /* Remove the borders that appear when linking images with \"border:none\" and \"outline:none\" */\n" +
                "  @-ms-viewport{width: device-width;}\n" +
                "  .qmbox h1, .qmbox h2, .qmbox h3, .qmbox h4, .qmbox h5, .qmbox h6{display:block !important; margin:0 !important; padding:0 !important;}\n" +
                "  .qmbox body{height:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100 margin:0}</style>\n" +
                " <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                "  <tbody><tr>\n" +
                "   <td align=\"center\" bgcolor=\"#dddddd\" style=\"padding: 0px 20px;\" class=\"nullPad\">\n" +
                "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"container\">\n" +
                "     <tbody><tr>\n" +
                "      <td align=\"center\" bgcolor=\"#ffffff\">\n" +
                "       \n" +
                "                            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                "                                <tbody><tr>\n" +
                "                                    <td align=\"center\" valign=\"middle\" style=\"padding: 13px 40px;\" class=\"mobilePad\">\n" +
                "                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                "                                            <tbody><tr>\n" +
                "                                                <td align=\"left\" valign=\"middle\">\n" +
                "                                                    <a target=\"_blank\" rel=\"noopener\"><img src=\"http://47.102.137.205/home/images/robot.png\" width=\"150\" height=\"150\" border=\"0\" style=\"display: block;\"></a>\n" +
                "                                                </td>\n" +
                "                                            \n" +
                "                                                \n" +
                "                                            </tr>\n" +
                "                                        </tbody></table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody></table>\n" +
                "                            \n" +
                "      </td>\n" +
                "     </tr>\n" +
                "    </tbody></table>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                " </tbody></table>\n" +
                " \n" +
                "  \n" +
                " <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                "  <tbody><tr>\n" +
                "   <td align=\"center\" bgcolor=\"#dddddd\" style=\"padding: 0px 20px;\" class=\"nullPad\">\n" +
                "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"container\">\n" +
                "     <tbody><tr>\n" +
                "      <td align=\"center\" bgcolor=\"#ffffff\">\n" +
                "       \n" +
                "                            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                "                                <tbody><tr>\n" +
                "                                    <td align=\"left\" valign=\"top\" width=\"100%\" style=\"font-family: 'Arial', sans-serif; font-size: 14px; mso-line-height-rule: exactly; line-height: 22px; color: #939598; padding: 30px 30px 30px;\" class=\"mobilePad\">\n" +
                "                                        <span style=\"color: #000000;\"><b>尊敬的用户您的验证码为：</b></span><br><br>\n";
        String html = "<span style=\"color: #000000;\"><b>\"%s\"</b></span><br><br>\n" +
                "                               您当前的操作是<a target=\"_blank\" style=\"color: #4dafd4; text-decoration: none;\" rel=\"noopener\"><b>\"%s\"</b></a>，5分钟内有效，请勿向任何人泄露。</b></span><br><br>\n";
        String tail =
                "\n" +
                        "\t\t\t\t\t\t\t\t\t\t如果您本人没有通过登录验证请求此验证码，请立即前往“ <a target=\"_blank\" style=\"color: #4dafd4; text-decoration: none;\" rel=\"noopener\"><b>我的帐户</b></a> ”页面更改密码。 如果您需要支持，请联系 <a  target=\"_blank\" style=\"color: #4dafd4; text-decoration: none;\" rel=\"noopener\"><b>小R帮助</b></a>。\n" +
                        "                                        \n" +
                        "                                        <br><br>\n" +
                        "                                        祝您使用愉快，<br>小R\n" +
                        "                                    </td>\n" +
                        "                                </tr>\n" +
                        "                            </tbody></table>\n" +
                        "                            \n" +
                        "      </td>\n" +
                        "     </tr>\n" +
                        "    </tbody></table>\n" +
                        "   </td>\n" +
                        "  </tr>\n" +
                        " </tbody></table>\n" +
                        " \n" +
                        "   <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                        "  <tbody><tr>\n" +
                        "   <td align=\"center\" bgcolor=\"#dddddd\" style=\"padding: 0px 20px;\" class=\"nullPad\">\n" +
                        "    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"600\" class=\"container\">\n" +
                        "     <tbody><tr>\n" +
                        "      <td align=\"center\" bgcolor=\"#ffffff\" height=\"1\" style=\"font-size: 0px; mso-line-height-rule: exactly; line-height: 1px; height: 1px; padding: 0px 30px;\" class=\"mobilePad\">\n" +
                        "       <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                        "        <tbody><tr>\n" +
                        "         <td align=\"center\" bgcolor=\"#dddddd\" height=\"1\" style=\"font-size: 0px; mso-line-height-rule: exactly; line-height: 1px; height: 1px;\">&nbsp;\n" +
                        "          \n" +
                        "         </td>\n" +
                        "        </tr>\n" +
                        "       </tbody></table>\n" +
                        "      </td>\n" +
                        "     </tr>\n" +
                        "    </tbody></table>\n" +
                        "   </td>\n" +
                        "  </tr>\n" +
                        " </tbody></table>\n" +
                        "  \n" +
                        " <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                        "  <tbody><tr>\n" +
                        "   <td align=\"center\" bgcolor=\"#dddddd\" style=\"padding: 0px 20px;\" class=\"nullPad\">\n" +
                        "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"container\">\n" +
                        "     <tbody><tr>\n" +
                        "      <td align=\"center\" bgcolor=\"#ffffff\">\n" +
                        "       \n" +
                        "                            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                        "                                <tbody><tr>\n" +
                        "                                    <td align=\"left\" valign=\"top\" width=\"100%\" style=\" padding: 30px;\" class=\"mobilePad\">\n" +
                        "                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                        "                                            <tbody><tr>\n" +
                        "                                                <td align=\"left\" valign=\"top\" style=\"font-family: 'Arial', sans-serif; font-size: 11px; mso-line-height-rule: exactly; line-height: 12px; color: #939598; padding-bottom: 12px;\">\n" +
                        "                                                    如果您需要技术支持，请联系qq:1240220123\n" +
                        "                                                </td>\n" +
                        "                                            </tr>\n" +
                        "                                        </tbody></table>\n" +
                        "                                    </td>\n" +
                        "                                </tr>\n" +
                        "                            </tbody></table>\n" +
                        "                            \n" +
                        "      </td>\n" +
                        "     </tr>\n" +
                        "    </tbody></table>\n" +
                        "   </td>\n" +
                        "  </tr>\n" +
                        " </tbody></table>\n" +
                        " \n" +
                        " <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                        "  <tbody><tr>\n" +
                        "   <td align=\"center\" bgcolor=\"#dddddd\" height=\"50\" style=\"font-size: 0px; mso-line-height-rule: exactly; line-height: 50px; height: 50px;\">&nbsp;\n" +
                        "    \n" +
                        "   </td>\n" +
                        "  </tr>\n" +
                        " </tbody></table>\n" +
                        " \n" +
                        " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"620\" class=\"mobileHide\">\n" +
                        "  <tbody><tr>\n" +
                        "   <td height=\"1\" style=\"font-size: 1px; line-height: 1px; min-width: 620px;\">&nbsp;</td>\n" +
                        "  </tr>\n" +
                        " </tbody></table>\n" +
                        "  <img src=\"https://click.e.ea.com/open.aspx?ffcb10-fec717767163077c-fe171376736605757d1d79-fe961372776d007c75-ff981675-fe251275726c037d761575-fec116747d6d017f\" width=\"1\" height=\"1\">\n" +
                        "<style type=\"text/css\">.qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}</style></div></div>";
        return head + String.format(html, code, operation) + tail;
    }


    private static String ctvString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();

        for (String b : list) {
            stringBuffer.append(b + ",");
        }
        String s = stringBuffer.toString();
        s = s.substring(0, s.length() - 1);
        return s;
    }
}
