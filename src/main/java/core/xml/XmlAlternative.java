package main.java.core.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by arvind on 07/12/16.
 * Class to bind alternative data
 * coming from xml file.
 * Object of this class will automatically be serialized/de-searialized by
 * jaxb marshaller/un-marshaller
 */
@XmlRootElement(name = "alternative")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAlternative {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String mapsTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapsTo() {
        return mapsTo;
    }

    public void setMapsTo(String mapsTo) {
        this.mapsTo = mapsTo;
    }
}
