package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Authenticator;

public class Mail {
    
    private static final String USERNAME = "your email";
    private static final String PASSWORD = "password";

    public static void envoyer(String email) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = prepareMessage(session, email);
        Transport.send(message);
        System.out.println("Message envoyé avec succès.");
    }
    
    private static Message prepareMessage(Session session, String email){ 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Une nouvelle Rendez Vous a été Ajouté Avec Succées ");
            
            // create a multi-part message
            MimeMultipart multipart = new MimeMultipart("related");
            
            // first part (the html)
           // first part (the html)
BodyPart messageBodyPart = new MimeBodyPart();
String htmlText = "<html><head><style>body {background-image: url('data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBQTFBgVFBQZGRgaGBwaGhkaGRgYGhoYGRsZGhsbGBobIi0kGx0pHhgYJTclKS4wNDQ0GiM5PzkyPi0yNDABCwsLEA8QHhISHjIpIykyMjYyMjIyMjIyMjUyMjIyNTIyMjIyMjIyMjIyMjI+MjAyMjIyMjI1NTIyMjIyMjIyMv/AABEIALcBEwMBIgACEQEDEQH/xAAbAAABBQEBAAAAAAAAAAAAAAAAAgMEBQYBB//EAEcQAAIBAgQDBQUFBgQEBAcAAAECEQADBBIhMQVBUQYiYXGBEzKRobEHcoLB8BQjQlKS0WJzsuEzU6LxtMLE0hUWJDREVKP/xAAZAQADAQEBAAAAAAAAAAAAAAAAAgMBBAX/xAAnEQACAgICAgEEAgMAAAAAAAAAAQIRAzESIUFRBCJhcfAjsRMUMv/aAAwDAQACEQMRAD8A9DvSR6V54bgHFrDE7oU9QWH/AJq9HuJpXmHa+bWJS8NkuqfQwfqK7JasjDyj0W/h2BDLr5VYYe6SNap/btbYMNUfWOk66fWrfDXFcSv+486JPrsRIkHWomJ4cj6jut1Gx8xUyIpPtR6VNqxk6KZcG3IqfWn8Ngczd/l/D/erIKNZ23ioWLYoVcdIPjFCirGeSRLuV5V29RrGITEroUcZiP4kOjA9ZUkegr1S1eW4JHqOhrHfaVgs2FZ420P1HzHzpvFCx2XloZrakbECm7aDMVNRuyd72mBw77n2YU+adw/NalW279WTtE6EohU6Eg+BI+lTbGNup/FmHRtfnvTeTv04LZPKsaT2am1os8PxJG0bunx29DU8VnTY2nrU3C4jJo3u/T/aufJiW4lYz9ltRSQ0iRSqgVCimL7wNBJJAA6k/lzPgDSfauN0J+6yn/VloMsk0Uwl8ExDA+II+e3zp+g0KKKKACiiigAooooAKKKKACiiigAooooAKKKKAKaKw/2i8Nz2HddwMxHUqRqPQ/Kt3lqs7Q4cPh7gInuN9DXXvo5107K/s1c9vgrLHWUAJ/xJ3Z+Iqbh7D2zI9fEVQfZriJwhSdbd119DDfma2ykEQa3l0DXbQy1zMJmkW21rjoUOm1OIRpW9UL5FqxpOKse0SPhTqCnEpGxkUlqzcQyvqOvmKi9sbouYK5puNQeUa1pGtiZqk7RkG21uN0f/AEmhNPQaKH7Ob2bhyD+V7i/9ZYfJq0Fq33pOgrN/ZU4/Y3WPdvMPiiH861LHM4HKni+jJbZPtoN4pJPeinVGlNKNaVMBN0aiuhJ35ilstQeM8RXC4d7za5F7qn+Jzoi+rEek0X6B0u2W/D37uU8vpU2vIML9o+IQy9m033c6T8WarnBfacrMq3MMUBYAsLgYLJjMQVGg3pJ/HndpBD5MKps3w7zzyXT8R3+AgfiNZrtB21TB3/YtZdu4GzKV5zplMdOtWPG3K4DEMrEMLFxswJBDZGJII2Oadq8Sv4l7jZrju7RGZ2Z2gbCWMxqfjW/Hwqdt6E+TncElHbPe8NiRespdQEZlW4oMTDANGnMgketTFMjSvPvs24zfvM9m4+ZLdpcgyqCoBygSACdOtb2yYleh0+6dR+Y9Kjkg4ycWdGOalFMforldpCgUU1bvI3usp8iDTtABRXJrtAFdjcS4ZUtKrMQWOZioVRA1IBMkmB5HpT+CxHtEDQQdQVO4ZSVYHrDAiai45mt3FuBGZcpVggzMNZVgu5E5gY6jxp3hasEl1yszM2XmAzEqDHPLE+M1rSoRN2T6KKKwcKKKKACiiigCrJjesfx3tMHR7eHTPMpnMkMdiLaKC1yJ1Oijma59oXE3t21s2yQ1whdN4PL1irjs9wW3hLSogGeBnfmx568h0FdSOc857PYbi2HZ2s2AQ5zOr5dTJggFgQdTWiwHbp7dwWuIYdrDHZwGKHxg8vImtvct6SNxUHHcPtYu21u6gZT8VPVTyIra66NcrfaLBXDqCpBBEgjUEHYg9KRGkVk+xwvYS8+Buh2Qd+zcytljmhbYHmB51r3TWhPwY0KVtj+v1vTgNJWBvSXvKvrSmj7b1Q8W1c/cYfEVZPxADYVExFlbk96CPLn/AN62CrZjMl9ln/BvjpfP+lf7Vr7a/vDVR2T4CcF7ZfaBxcfOO7lK7yCJPhrV7aTvE00ekZLtkxRTca11macqxMSSQTA2GgI1Ov8ASabPtJ/gPqy/kaRM0eYV5p9pfFs9xMMp7tvvv99h3R+FDP4/CvRy7gSyrHMhiYHWCo0rwzjIuC9eF3/iZ3z/AHpMx4dPCKrgVy/BD5EmlXs01vsBfbDrcW4vtGUN7IiNDqBnn3o8InSedV3Huyl7B21uOyOpIDZM3cY7TIEg7T15V67YvK1tHGzIrDyYAj61Qdto/YbzNzCAeZdIpo5ZN9mSwRUW16HuxeOTGYL2dzvd023BO4ACmY6qVPmxrEdteAixiSuHsXBb9mplVd1zEtPeM9BpNK+znHvbxD21kh0J0EwyTBjxDMPFilehri76+8xOvO2wEaaDuDXpOnWptvFkda9DJRy40nv2ROxXALeHtriFZ892whdWIygkBzGgI1nc1kO3vFjcxItqxAtjWCR33AJ26KEHnmrd47jYt2LjvEqC0CYK6QNdZYkJ5mvGrl1nZnfvMzFm/wATMSW+JJpsEXKTlIXPJQioRLYYnG2URjcxCK+qMXuBW56SY218RrtXqHYzihxOFVnbM6Eo5O5ZdifEqVPrVX214jYucPDKyt7QobUESYZSxA5QoYHptzqi+zfiPs8Q1knu3VkffST81zf0iln/ACY3KqaGh/HkUbtNG+4e0nUZtdG0aDHXMYq0qr4dc7xUncCASSee2Y9PAbVOvHSBuTHx3+Un0rlezsjoiXMMHObKNdjmIMcjsflTuFUr3TOuokgjloIA+EVnOIdq7AdrLJdARirFIGbLIhSrBgJ15bVP4Px61iO4jMXUFu8pBKg5ZJ2Jhh6mmcJJW0Ipxuky+fagmK421Juch1PyGp/t61MqEt0HxI/KlKZrJcS7RMMSq27n7sFVuArsQ5D6kT7vStRYuq8MpBVhII2Pj9PhTSg4pN+RIzUm0iRRRRSjhRRRQB5T2rYNxDDKdvaIPmv963Vg5wWGizE9fKvPu2lplxltlElXV/wjU6/hjxmvSMPaCW1XpXU3RBoae2Ocmm1t5CR1+lOX7wWBzppLubf0NMmxaFeVdJO9E11lkUwHCZ/vTa3OREeH63riXI0Ohp10DiPgaw0j3MMrQRoQQfAwZqvd2t3AG097yKkiCKnIjK4VufPkfKme0Fg3LOdTDIZHiswR9D6Ukn5Q8d0xu3jJAk94aHxHKpaYlYmYgSfIc6o8AiO4tPci4UL5QJhQQJbpqdOuvSn8Rwy8pAQhgTO8Tl1jXr06A0KTCUY3Vl7hLpiTuxkg7joPQQPOTzqXM1mlv4i2O9ZcwRsrHSQDEbmJNS8LxK7AzWSDrKwwbSNpHjzj15nTVoWXTpl6yyNa8t+0fheS4t8DR/3bn/GgBRj4skf0GvRLHEszKjLGbZpOUtqSqllEkd3+rwqN2l4UMTYe3zdYUnk6y6HwEyp8GrMc+MrJ5IcotIy/YPtLb9muHv3AjW9EZyFVk5LmOgZZiDuIjY1X/aB2kS/lw9lgyK2Z3XVWYSFVT/EBJJO0x0qh/wDlbGcrIPldsN9HpSdk8aWANhlBIBaUIUE6sYbYb+ldXGClys5HObjwo2P2WcKhXxDDVjlX7qn82B/oU1qsX2twVrMHxCyr5GAzMVbvaNlBj3W18Kf4Jhls20tqIUKAPwgAT4x9KzfCeDPeQspQKb18MCTuMTe1yhYJg9a5J1KbcjtgnGCUTR4x7GLtXES4rqAyPlYNlkEMDGzAEkDqBXlXZzg+fHLh7o0Rm9oOR9nMjxUkDzBr0bgOEFrGYlBH/Dw5JChcxJxBJIHPx8Kxf2h8L9nfF0Du3BB++gAB9UKeqtT4XTcU9onnjaU2tPs3fEuEpiEv2WA2XIY91gndI6QflpXl/AyUdr+3sULj/MPctr/WwJ8Eaq1MUy7XCvk5H0NWWIOSwic7h9s/3dUtD4Z3/GKtDG4Ljd3+shPIpvlVV+on2e1uNX/8gnzS2f8Ay1rux3aG5iWdLzAssFSAF7raHQdGyj8VUq9ln9k5yjN+xo2XmLrOXKn0QD1qOq/sPEANkBUE8sjqAW8gTPmlLPhJNRSspBzi05N0SrZcYrGGzHtpfJpJj2n7zKP5su3rVl2eAXiN7RR+7bMF90N+6LhfAPmqzv8AZGxcuG7nuKzMWOVl0YnWJWRrNK4Z2bt4Vy6O7EqVhssQSpOwH8oHrUZZI0/wVjjkpL8l2jMV0A6asQdPSlFjIkRy0M7x4DnFOqIEUm+JHlrXOdR5xxXhzpiMjZS1x8ywTEO5CzIEGa1OIxTYPCW5AzjKoUmROs6jwmpd+yruHawrMpGVj7wgyup21ql4+f2rE28OGyjKZMTDMpbUaagL/wBVdPPnSlpds5uPC2tvQvBdrWd1RragMwUsGOkmJgjx61rRWa7U8NzWVcED2SmdNTOUadNqueFYn2tlH5lRPmNG+YNRmotJxRTG5JtSZOoooqZYznFcqnMVnRZiJ0dYnwmp9x4Waj8VsF1gbkR6yGH+mkYwnKAOQiuraRBbIXF3ylW5GVJ6HkfrUPC48ZxOneyOOjfwt5EVOuurqEb+L5GqHH4RlaR7ywCP51Goj/ENx1oba0PBJ9M0L38pIO4EjxX/AG+lOWcUp51leJYu4pVrZzDQlCYKnnlncHXTTeq9MZcmUkDeNyPDxpXkSdDRwto9CKrcEfPmDUG3cZXyHkf18qj8BxedRJE84/W/0qdjbPeDDmNR5f7H5U33QlU2mP4qMgJ5ER66fmKWqh0I5ERSb+ts+Q+opOBPdoFMXhkycXkae0Rh6hZ/IVvWtEjT3hqv3htPgdj4E1i+IJk4nh26uy/1qoH51ukFZNgdtsGAI2InXfyPjXSKbt91ivIyy+f8Y+JB/EelPRUhgNZPt5xb2GHYKYd/3axuGZe+3hlQwD1uCtVebKpMTHLqeQHiTA9a8a7c8S9viiimVtSgjZnJm4w837vkgquGHKRHPLjEzOQdB8KXbYowZNGUhlI3DAyD8RWtwPYi4UVrzFGcFltjKGCKAWe47GEAldIY94aDWIPEOzDrZ/abDe1tD3oADIIBkgEhkylWzA7MCQNY7f8AJBurOH/HNK6PU+z/ABAX7Nu6vMAx05EehDL+GnOyP/25/wA/E/8AiLtYX7NOKQXsMdPfTyMKw+OQ/ic1rF7NWiWZMXibYZ2col8KgZ2LNlWNASSfWuHLBRbR34puSTRN4ef/AK/F/wCVhv8A1FZDt1x+4t5bVm4yZVzuUYqSXjIpjogUx1etRYwNrBreuG9cuFlBuPccOwt2gxgQBpDMPNhXkmLxTXrj3H952LnwJMwPACB6U/x8alK/CEz5HGKj5bLFe0GL/wD2HPmc3+oGpWNvZxh8W/eMhL2g1eyVMkDSWtldP8JpC8BZEW47oRqSkXM2gMT3RoSF1kA5hB1FK4a/thes5VXOmdFUGBdsgsAoJJlkzjfpXS+O14OZctS8nqmDxKMbhDAhirgyCChRBI8O6axHaiyL+Iw1lPeNtUbmVDGRPkuZo6VkbF5lBCuVDCGAJAYdDG4/vV5wD90l/Fc0XJb/AM273QR91dT4GoLDw+pP90Xlm5ri1+S0xvau8l11tBMitlUkOSQvdkkMJkgn1qy7N9o3vXfZ3QglSVK55kQSO8x/hDHT+UVlcMQ9tbYUAAsWPMMfdcnksDKf7xScHea1cVwO8jgx5HUHz1HrVHhi4tV2IsslJO+j1O/hQ7Zo2096NvCD+hSrFvJMzqNpB205AeFOYW6pQQQRAgzuDqD6gg+tdusDEcjmPkP+9cHej0OtjqiAPKsj2ozWMRbxCASVI1BIzKCNYI3Vv+mthVbxrh5v28oy5gwZc05ZGhmNdia3G0pd6FyRtdbKTtHxlTZVUZGzqc8GSsQdIOms79KveCYX2VhEO4Et95u8fmaz9nszczqXFoKGBOUtMAyQBlAM7fD12ApsjiklEXGpNtyO0UUVIsQLqyBHUH51S8SdicgMa1dFoFZji+J/eQp1rojoklbEsC1yVIK7RrOmkg+c1L4gim2SyyQOuvypGBtwBUnFmEOn51RLoL7MDeDsSW7o6a7eZ3pzBasABp+t6VjAzuSetScBa1AFciX1HddRJnZ9St5kU90NrPIA9K1WOuhQh6vl+IJ1+FYnh4c3uchjp4ya0+PuyETfWflH51eGjmyr6iyxBi2fT6ik4OdKYdpVUmOfoNB+fwqwwNvWelO+k2RMd2rbJi7TjZb1sn0VifkDW8UVk+0fCXv3BkGgdJMjQw4/OtXb2HkKSWkCOvaVhDKDz1AOtNjCW/8Alr/SKfFdqVm0MjDICCEUEbHKK8U4/g3wGNYIYyOLlokT3WOZd94Mr+E17jWH+0/hHtLC4hR3rRho523IB/pbKfAFqtgnUqemR+RDlG1tETB9tbN32dxyLdxQVuI3usrFCcjkQNUGjRoTroJicR7ZW7Np7eECl3O6rFqyoRLaqsgZ2CIuwyyTuBFYCiuv/XhdnG/kSqi64AxspdxezW19na8b10FZg6HKmdiD4U8na3Hf8/8A/nZ/9lI4wotCxhCcoQB7xAki7dyl9BuUt5VHkaY43hrNq8wsOXs6ZWJBnQZhIABhp5dPMslFu2t/0K3JKouq/suOHcdv4pzhsRdzJeU25KIuV2g23lFBMOFEbQxqDw/htwhrjIMltytwEiQyjUZTuAcoPmN9ai432YyLbVkdEUXCWnNeBOZk10Gw5bbczY8bdmyXlJCYhQ7KCQvt7fcuSu05oYferKp/T1Y13/13Rd4p1Ad8rZv3zM/JkdCF78Q6sHwwVQARk95ogQ+xOANzFK3K2M8/4j3UHxObyQ1Qe3YqELsUGoUscoPULMCvTfs+4d7Ox7QjW4c/4dVT5Zm/HU8n0Qf3K4/rmvsaS8qIMzMVGgkuwEnQbmqHtdhFu4UshzBTnHeLA5JDRr/IXP4RUrjGJdWIDlYCsJCFBE6mYYkMFO4Goqyw8XLIIhgyyOYIOo15g/Q1xRuNSOySUrieSYa6UMr0I12IPIx+tKkOmdgVGtw6DXRyYK6+O3gRzpeM4c9u49sKxCtCkKTKnVT/AEkVZ4BnRFd7ZVcOrsrFCMzuyhQSd4Yg+S16Dkto4YxemJ4hxC4rlLbkJbAtrB07gClvVgdat+zmPvJe9nez99ZUOCDI1BE9QGHnVLwHCh7iliAMwVSeb7jfeAM0czlH8VXvavELauWMhlrfe3k5QVgMTqZyn51GdXwrwVhdc78mwQQAPCl03acMoYbEAjyOtOVwneFFFFABRRRQBTcQv5VPlWPtXPaXC3jVnxHElyddBMVEwGFkZgNRv410fYWKpNsuMKsUrGe7SsPtSrw0qvgl5MpicNLVO4ZgDO1WAwuY/wBhV1g8Llid4+H61qbio9sq8japFZf4IbYa5b1uEaqdieeU9fDnRw7Bs5zMrA8ywiPIVoYoqam0K3Yw+ERgAy7DQ7H404cqDoKcqtxLFm015AetEVyZknSI+KxoRhoWZyMirBJKEEk66AcyeoqdaxI7oZShOgkggnoCCRPhUBrKi4jMB3UZGP8ALnKOhP8AhlWHwpXESrrGixIaI1Xw6GOfLrTtJuhU6LgV2ofCbxuWbbtuyAknQnxI8d/WptSY6Co+PRWturrmVlKlf5s3dy+sx61IqNc7zRyUSfvEEAegk+q1iBmQTsFhP+Wx87z/AJKKk2exWDtkOLIJUhgC91pKmY1eOXMRV4cXDFcjEieaAGOnen5VJtXZAJUjwMSPhpVnOXtkVjj6PD+PYd7eKvK5zNnZs38wc51b1Vgauew2Pw9h7jYiMuQFSVzANMERB7xBAnwNW32kcMHcvoNu4/3WJZD6NmX1Sshbu2xYZMje1NxWDz3QgUjLE75iTt66RXYnzx0cTi4ZGx/E4LuHEIV9k11kUT31bKXysvgukgnl1r0LsTwZThF9oobOxuAMqtlzAKuUMCBKqGP3hXnfCsCb91LQ0zsAT0Uau3ooY+le4WAtq1J7qqsn/CoGg9FAHpUfkzaSiX+NBNuRAwuEwzlhbCEo2VsqWu6wAMHu9CKnYcZSV+Gw2AHLwy/A1UYDB3VxL4ghES8nfTMcwZQoRmkRmiQY089zcXm91hqPDWR+pHmRXGztjoynahj+0kSY/ZyYkwSHWCR1En4mtBwJowlj/KtgeZUAUnHcNw95yzkFihtnvkd0mdADoZA1qVbtoFVE91QFAGwAER/SD8RTymnFISMGpNjtu0CBM/1MNOWx6UxxLBi5aZNYYFdSTBPunXo0VPpLLIipptOyrSao8xSwSqScotlg+oDIwYmQDqSRCjxWKcwqtisSubd3lvBRqQPAKIq44p2cd7zuj21Vu9DMQQx0bQDaZPrVjwDs+2Hcu7Kxy5QFnSSJOvl8zXY8seN32cUcUuVV0aMCu0UVxHcFFFFABRRRQBg3lmHTp9asMGmRJad9wKZspqKtUszbA9a6Y+yc34GM4ifnQWnapS4YTqNOlcw2GzGf4QfjT8khCThrCqAY1ifKnbZkk/r9a0q5XVWB+v10qDdjIWDXabBpQNK0adaoC6P6/WrCmLtmdaaDS6ZkkRWwz+0z6lSNh18etJxfDkYd5ZHSWC+qgxU23mGnKlFS3vfCjk0w4o5hlhB5fLl8qcoNNu8Uu2bocZwBURGhZO7d4+vKfAQPSn1eajpaElDOmq/dOw9CCPIDrWpJPsCuVXa4cu+YnYqDB1Bb2fT/ABa/Orm2DAkCY1jaecTVKAqXZkSHOsgncyAACdRPTY1e23BAI2IkeRrZMWJX8XwCYi2bTzDqykiJAicwnSQwQjxArP2vs8wo3uXm82QfRK1qCWLfhHpv89PwilxRGckqTCUIydtGfwHZfD4VjdtI2bLEszN3SQWAB0kgEep61e4m37S26AwWVlncd4EA/OnDTVuQGVYkDuztzyzHLl6Usm32zYxUekU/tLOKu+zNoe1tlDdzKJQA5ggf+IMeQ0ylp5A3hQAKqgASNAIAA10HLkPWqLh3B8RbxD32uW/3hXOoRjoukKxYR5kGr+3qSfQem/z09KJV4NjflDNzHWkLKzqpRQ7DbKrEgE+ZBos3iwzFWXK7LBjVQSubTkYzelUvHuK+xUuqAOxKDOh7wRjqde8vNdeZ61I4HjBcVXKtmvAkxmZQUARpnRRpp1reD42HNcqL6m7hgfTz5U2jwIIMjTY/Gdq7JJGhAGusemx/UVMexqCGAGoAHX+8fKpdRWQ5wwUnx06eJqXWsEFFFFYaFFFFABRRRQBmMMgcBrZkdNiPSrHDMYg1FyMuiwD9PhU/Do8DM3yGtdMnS7I7HVSmbd6CUMAjUeK/rSn4I2NQcThGc+/zJErqpP8AKQRFIu9msmFgNT+jSrd5TsRpvUOzgts7u34io+AP51Nt2lUQFA9Kx0CsUwmkbU5RFKmbRxWrrHSuZK69ACDcoF0VGxGGJ9xo8D/eqe9eu2/eQx1HeHy29adRQJNmizim3E8xWdXjA5mnBxEmINaorwwcZei0dHHugHwmD86g3uK5GHtEZSOo3Uxm19Afwim//iTClDG5xBEjoYitfZte0WVriNtvdcH7ve+lOnEyO6GJ5SpUT45o0qpw2EeZQ5B/V8jVzh7BEZmLH0A+ApJJIyvQ8igAAchFEUqK4aU0RcMa02ozNoxBjlGo8ZBp25qNK5Ysxqd/pRaoyuxfsBzLH8TD6GlqoAgUqa7SFDM8Y4OLiBHuMBblg7DMSCGLAmRmgDlEab1Y8HtKltVtmUCCDPPUnSNCZn12qfcsqxBZQSJiRMSIPxFLRANAAB4CKZybVE1BJ2QXVzcKknIBPTfkCNarSz3GJUnKGjeIHL5CrjEWSzIQSIbXyg/2A9aUmHjNr7xny0A/KmjOkZKDbHUEAAU5TVlMoAmY5nc+dO1MogooooNCiiigAooooAgWrWgzakCJp8Cha7VGySRyK5kpUUVlm0cUV2iuUAdoomuUGnaGFcrhaijBDDpUa4QQZqQzVW8VLG22QEk6GOlUiG3Rlr9+Xbuysn9TUj9qDEQcsCAsGPpv41DGINsw6x57fH+9XFi5bABYqJ1G0nyApYq/J0SdeDlrMTqsjqJNWGGtnfJHnArthw2wJHkamexzLEAT4j8qrdHPJ34H7YipNp55UhUgAUotGgqMnZi6HSazl3jlz9o9kuXKHCnQl9xrvERrMeFXwXrTTuBsPOK2KSerCVvXQw92SRL5iTlADAeGqj6mnsRYdg4BEMpAkkENEDYbeNO4fU+VSaSTpjRVog4TBlVtyRmVQrEa5gAdJPKTNTqK7St2MlQUUUUGnK7RRQAUUUUAFFFFABRRRQAUUUUARVNLFNKacU1RokjtFdFcIrDTlFFE1oBQTQaQxoA6WpDNRNNM9MkY2DPTOhBB1B5UFqSKqo9C2UmP4VBzW5Yc0OpH3eopu08DRR+dXzDWZg9aiYrCszZrbATuAPyOlJxrRVZL6YjB4kkxl1q0CsdIqvs4O6YzXCF6DSfhFXFsACB/vQ5OhZJX0FpIEV1WHOlNXFsE7/Cp2vJlCGcnbalLYJ8PrUhUA5Uul5+hlH2ItoFEU5RRSjhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQBCFLU0UVVkxYooopQOVw0UVqA5NJJoorUYM3Gpg60UVaOhWIpQFdopjDjCnLcATEn5UUUrNF5iactLRRU5GomIgFLooqDLBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH//2Q==');}</style></head><body>"
                    + "<h1>Confirmation de l'ajout de réservation</h1>"
                    + "<p>Bonjour,</p>"
                    + "<p>Voici la confirmation de l'ajout du Rendez Vous :</p>"
                    + "<p>Merci pour la visite de notre site ! :</p>"
                    + "<p>à Bientot :</p>"
                    + "<p>Cordialement,</p>"
                    + "<p>L'équipe de support</p>"
        + "<p><img src=\"cid:image\"></p>"
                    + "</body></html>";

messageBodyPart.setContent(htmlText, "text/html");

            
            // add image to the email
            MimeBodyPart imagePart = new MimeBodyPart();
            DataSource fds = new FileDataSource("C:\\Users\\amine\\Bureau\\logopi.png");
            imagePart.setDataHandler(new DataHandler(fds));
            imagePart.setHeader("Content-ID", "<image>");
            
            // add the parts to the multi-part
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(imagePart);
            
            // set the multi-part as email content
            message.setContent(multipart);
            
            return message;
        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}
