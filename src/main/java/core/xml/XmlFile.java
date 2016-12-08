package main.java.core.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by arvind on 07/12/16.
 * Class to bind xml file data
 * coming from xml file.
 * Object of this class will automatically be serialized/de-searialized by
 * jaxb marshaller/un-marshaller
 */
@XmlRootElement(name = "xmlFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlFile {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String nodeName;

    public XmlFile() {
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
