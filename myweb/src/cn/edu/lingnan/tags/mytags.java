package cn.edu.lingnan.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.jar.JarException;

public class mytags extends TagSupport
{
    @Override
    public int doStartTag()throws JspException
    {
        try {
            pageContext.getOut().println("记住我");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Tag.SKIP_BODY;
    }
    @Override
    public int doEndTag()throws JspException
    {
        return Tag.EVAL_PAGE;
    }

}
