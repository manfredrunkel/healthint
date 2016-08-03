//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2016.08.03 às 12:54:21 PM BRT 
//


package healthint.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de status complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="status">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="approvedByHealthPlanRet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="materialsAvailable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalExpenses" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "status", propOrder = {
    "approvedByHealthPlanRet",
    "materialsAvailable",
    "roomNumber",
    "totalExpenses"
})
public class Status {

    protected boolean approvedByHealthPlanRet;
    @XmlElement(required = true)
    protected String materialsAvailable;
    @XmlElement(required = true)
    protected String roomNumber;
    @XmlElement(required = true)
    protected String totalExpenses;

    /**
     * Obtém o valor da propriedade approvedByHealthPlanRet.
     * 
     */
    public boolean isApprovedByHealthPlanRet() {
        return approvedByHealthPlanRet;
    }

    /**
     * Define o valor da propriedade approvedByHealthPlanRet.
     * 
     */
    public void setApprovedByHealthPlanRet(boolean value) {
        this.approvedByHealthPlanRet = value;
    }

    /**
     * Obtém o valor da propriedade materialsAvailable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialsAvailable() {
        return materialsAvailable;
    }

    /**
     * Define o valor da propriedade materialsAvailable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialsAvailable(String value) {
        this.materialsAvailable = value;
    }

    /**
     * Obtém o valor da propriedade roomNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Define o valor da propriedade roomNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    /**
     * Obtém o valor da propriedade totalExpenses.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalExpenses() {
        return totalExpenses;
    }

    /**
     * Define o valor da propriedade totalExpenses.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalExpenses(String value) {
        this.totalExpenses = value;
    }

}
