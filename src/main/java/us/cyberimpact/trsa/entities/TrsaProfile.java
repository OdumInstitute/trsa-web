/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.cyberimpact.trsa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asone
 */
@Entity
@Table(name = "TRSAPROFILE", schema = "PUBLIC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrsaProfile.findAll", query = "SELECT t FROM TrsaProfile t"),
    @NamedQuery(name = "TrsaProfile.findById", query = "SELECT t FROM TrsaProfile t WHERE t.id = :id"),
    @NamedQuery(name = "TrsaProfile.findByInstallation", query = "SELECT t FROM TrsaProfile t WHERE t.installation = :installation"),
    @NamedQuery(name = "TrsaProfile.findByEmail", query = "SELECT t FROM TrsaProfile t WHERE t.email = :email"),
    @NamedQuery(name = "TrsaProfile.findByApitoken", query = "SELECT t FROM TrsaProfile t WHERE t.apitoken = :apitoken"),
    @NamedQuery(name = "TrsaProfile.findByDatastoragelocation", query = "SELECT t FROM TrsaProfile t WHERE t.datastoragelocation = :datastoragelocation"),
    @NamedQuery(name = "TrsaProfile.findByDataaccessinfo", query = "SELECT t FROM TrsaProfile t WHERE t.dataaccessinfo = :dataaccessinfo"),
    @NamedQuery(name = "TrsaProfile.findByNotaryserviceurl", query = "SELECT t FROM TrsaProfile t WHERE t.notaryserviceurl = :notaryserviceurl"),
    @NamedQuery(name = "TrsaProfile.findBySafeserviceurl", query = "SELECT t FROM TrsaProfile t WHERE t.safeserviceurl = :safeserviceurl")})
public class TrsaProfile implements Serializable {


    private static final long serialVersionUID = 1L;
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "INSTALLATION", nullable = false, length = 255)
    private String installation;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;
    
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DATAVERSEURL", nullable = false, length = 255)
    private String dataverseurl;

    
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "APITOKEN", nullable = false, length = 255)
    private String apitoken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DATASTORAGELOCATION", nullable = false, length = 255)
    private String datastoragelocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DATAACCESSINFO", nullable = true, length = 255)
    private String dataaccessinfo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOTARYSERVICEURL", nullable = false, length = 255)
    private String notaryserviceurl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SAFESERVICEURL", nullable = true, length = 255)
    private String safeserviceurl;

    public TrsaProfile() {
    }

    public TrsaProfile(Long id) {
        this.id = id;
    }

    public TrsaProfile(Long id, String installation, String email, String dataverseurl, String apitoken, String datastoragelocation, String dataaccessinfo, String notaryserviceurl, String safeserviceurl) {
        this.id = id;
        this.installation = installation;
        this.email = email;
        this.dataverseurl = dataverseurl;
        this.apitoken = apitoken;
        this.datastoragelocation = datastoragelocation;
        this.dataaccessinfo = dataaccessinfo;
        this.notaryserviceurl = notaryserviceurl;
        this.safeserviceurl = safeserviceurl;
    }

    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstallation() {
        return installation;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken;
    }

    public String getDatastoragelocation() {
        return datastoragelocation;
    }

    public void setDatastoragelocation(String datastoragelocation) {
        this.datastoragelocation = datastoragelocation;
    }

    public String getDataaccessinfo() {
        return dataaccessinfo;
    }

    public void setDataaccessinfo(String dataaccessinfo) {
        this.dataaccessinfo = dataaccessinfo;
    }

    public String getNotaryserviceurl() {
        return notaryserviceurl;
    }

    public void setNotaryserviceurl(String notaryserviceurl) {
        this.notaryserviceurl = notaryserviceurl;
    }

    public String getSafeserviceurl() {
        return safeserviceurl;
    }

    public void setSafeserviceurl(String safeserviceurl) {
        this.safeserviceurl = safeserviceurl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrsaProfile)) {
            return false;
        }
        TrsaProfile other = (TrsaProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrsaProfile{" + "id=" + id + ", installation=" + installation 
                + ", email=" + email + ", dataverseurl=" + dataverseurl 
                + ", apitoken=" + apitoken + ", datastoragelocation=" 
                + datastoragelocation + ", dataaccessinfo=" + dataaccessinfo 
                + ", notaryserviceurl=" + notaryserviceurl + ", safeserviceurl=" 
                + safeserviceurl + '}';
    }

    public String getDataverseurl() {
        return dataverseurl;
    }

    public void setDataverseurl(String dataverseurl) {
        this.dataverseurl = dataverseurl;
    }
    
}
