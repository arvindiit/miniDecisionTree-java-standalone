package main.java.core.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by arvind on 07/12/16.
 * Class to bind Node data
 * coming from xml file.
 * Object of this class will automatically be serialized/de-searialized by
 * jaxb marshaller/un-marshaller
 */
@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlNode {


    @XmlAttribute
    private String name;

    @XmlElement(name = "alternative")
    private List<XmlAlternative> alternativeList;

    @XmlElement(name = "xmlFile")
    private XmlFile element;

    @XmlElement(name = "node")
    @XmlElementWrapper(name = "childrennodes")
    private List<XmlNode> list;

    @XmlElement(name = "alternative")
    @XmlElementWrapper(name = "mappinglist")
    private List<XmlAlternative> mappingList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XmlAlternative> getAlternativeList() {
        return alternativeList;
    }

    public void setAlternativeList(List<XmlAlternative> alternativeList) {
        this.alternativeList = alternativeList;
    }

    public XmlFile getElement() {
        return element;
    }

    public void setElement(XmlFile element) {
        this.element = element;
    }

    public List<XmlNode> getList() {
        return list;
    }

    public void setList(List<XmlNode> list) {
        this.list = list;
    }

    public List<XmlAlternative> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<XmlAlternative> mappingList) {
        this.mappingList = mappingList;
    }


    public XmlNode getChildNode(String name){
        if(list != null) {
            return list.stream()
                    .filter(x -> name.equals(x.getName()))
                    .findAny()
                    .orElse(null);
        }

        return null;
    }
}
