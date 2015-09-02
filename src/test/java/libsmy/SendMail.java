package libsmy;


import org.dom4j.CDATA;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


// Source code will do the job of triggering email after every execution.

public class SendMail

{
/*    public static void main(String args[]) throws Exception{
        SendMail etr=new SendMail();
        etr.execute("index.html");
    }*/

        //reportFileName = TestExecutionResultFileName
        public static void execute (String reportFileName)throws Exception

        {
            String pathReport = "/home/dima/IdeaProjects/stage-CMS/target/surefire-reports/index.html";
            String pathScreenshots = "/home/dima/IdeaProjects/stage-CMS/target/surefire-reports/screenshots";

            String[] to = {};  //  "<i.koshkowskyi@come2play.com>", "<inna@come2play.com>"
            String[] cc = {};
            String[] bcc = {"<d.matvienko@come2play.com>"};

            SendMail.sendMail("d.matvienko@come2play.com",  // AutomationTesterUserName
                    "G8nUHQ&Xx",  // AutomationTesterPassword
                    "smtp.gmail.com",
                    "465",  // Port for TLS/STARTTLS: 587    Port for SSL: 465
                    "true",
                    "true",
                    true,
                    "javax.net.ssl.SSLSocketFactory",  //
                    "false",
                    to,
                    cc,
                    bcc,
                    "Automation test report (stage-CMS)",  // Subject line
                    "Hello.. dear Team. Please review my Automation Test report. " +
                            "This Mail Send Automatically by Robot, after tests execution finishes. " +
                            "If you don't want to receive this mail please contact with: Dmitry Matvienko  d.matvienko@come2play.com (QA Engineer). " +
                            "Thanks :)",  // Contents if any
                    pathReport,
                    reportFileName);
        }

    public static boolean sendMail(String userName,
                                   String passWord,
                                   String host,
                                   String port,
                                   String starttls,
                                   String auth,
                                   boolean debug,
                                   String socketFactoryClass,
                                   String fallback,
                                   String[] to,
                                   String[] cc,
                                   String[] bcc,
                                   String subject,
                                   String text,
                                   String attachmentPath,
                                   String attachmentName) {

//Object Instantiation of a properties file.
        Properties props = new Properties();

        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.host", host);

        if (!"".equals(port)) {
            props.put("mail.smtp.port", port);
        }

        if (!"".equals(starttls)) {
            props.put("mail.smtp.starttls.enable", starttls);
            props.put("mail.smtp.auth", auth);
        }

        if (debug) {
            props.put("mail.smtp.debug", "true");
        } else {
            props.put("mail.smtp.debug", "false");
        }

        if (!"".equals(port)) {
            props.put("mail.smtp.socketFactory.port", port);
        }
        if (!"".equals(socketFactoryClass)) {
            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        if (!"".equals(fallback)) {
            props.put("mail.smtp.socketFactory.fallback", fallback);
        }

        try {

            Session session = Session.getDefaultInstance(props, null);

            session.setDebug(debug);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(text);
            msg.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachmentName);
            multipart.addBodyPart(messageBodyPart);


// Send the complete message parts
            msg.setContent(multipart);

            msg.setFrom(new InternetAddress(userName));

            for (int i = 0; i < to.length; i++) {
                msg.addRecipient(Message.RecipientType.TO, new
                        InternetAddress(to[i]));
            }

            for (int i = 0; i < cc.length; i++) {
                msg.addRecipient(Message.RecipientType.CC, new
                        InternetAddress(cc[i]));
            }

            for (int i = 0; i < bcc.length; i++) {
                msg.addRecipient(Message.RecipientType.BCC, new
                        InternetAddress(bcc[i]));
            }

            msg.saveChanges();

            Transport transport = session.getTransport("smtp");

            transport.connect(host, userName, passWord);

            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();

            return true;

        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
    }


}

// Add the below snippet at the end of the test execution report creation.
// SendMail.execute(ExecutionFileName);