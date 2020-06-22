package cn.edu.lingnan.util;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.util.Properties;

public class XMLConfigParser extends DefaultHandler {
    private Properties props;
    private StringBuffer currentValue= new StringBuffer();
    public XMLConfigParser(){
        this.props=new Properties();
    }

    public Properties getProps() {
        return props;
    }
    public void startElement(String uri, String localName , String qName, Attributes attributes)
        throws SAXException{
        currentValue.delete(0,currentValue.length());
    }
    public  void characters(char[] ch,int start,int length) throws SAXException{
        currentValue.append(ch,start,length);
    }
    public void endElement(String uri, String localName , String qName)throws SAXException{
        props.put(qName.toLowerCase(),currentValue.toString().trim());
    }
}
