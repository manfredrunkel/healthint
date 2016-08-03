//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2016.08.03 às 12:54:21 PM BRT 
//


package healthint.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="procedure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="healthplan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="materials" type="{http://healthint/integration}materials"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "procedure",
    "healthplan",
    "materials"
})
@XmlRootElement(name = "requestMedicalActivity")
public class RequestMedicalActivity {

    @XmlElement(required = true)
    protected String procedure;
    @XmlElement(required = true)
    protected String healthplan;
    @XmlList
    @XmlElement(required = true)
    protected List<String> materials;

    /**
     * Obtém o valor da propriedade procedure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcedure() {
        return procedure;
    }

    /**
     * Define o valor da propriedade procedure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcedure(String value) {
        this.procedure = value;
    }

    /**
     * Obtém o valor da propriedade healthplan.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplan() {
        return healthplan;
    }

    /**
     * Define o valor da propriedade healthplan.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplan(String value) {
        this.healthplan = value;
    }

    /**
     * Gets the value of the materials property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the materials property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaterials().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMaterials() {
        if (materials == null) {
            materials = new ArrayList<String>();
        }
        return this.materials;
    }

}
