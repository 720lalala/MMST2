package cn.edu.lingnan.util;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;
import java.util.Properties;

public class DatabaseConfigParser  {
    private static String driver;
    private  static String url;
    private  static String user;
    private  static String pwd;
    private Properties props;

    public Properties getProps() {
        return props;
    }
    public void parse(String filename) throws Exception{
        XMLConfigParser handler =new XMLConfigParser();
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser=factory.newSAXParser();
        URL confURL=XMLConfigParser.class.getResource("database.conf.xml");
        System.out.println(confURL.toString());
        parser.parse(confURL.toString(),handler);
        props=handler.getProps();
    }

}
