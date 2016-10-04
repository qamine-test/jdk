/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.content.x509;

import jbvb.security.cert.X509Certificbte;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.RFC2253Pbrser;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @buthor $Author: coheigeb $
 */
public clbss XMLX509SubjectNbme extends SignbtureElementProxy implements XMLX509DbtbContent {

    /**
     * Constructor X509SubjectNbme
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public XMLX509SubjectNbme(Element element, String BbseURI)
        throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Constructor X509SubjectNbme
     *
     * @pbrbm doc
     * @pbrbm X509SubjectNbmeString
     */
    public XMLX509SubjectNbme(Document doc, String X509SubjectNbmeString) {
        super(doc);

        this.bddText(X509SubjectNbmeString);
    }

    /**
     * Constructor XMLX509SubjectNbme
     *
     * @pbrbm doc
     * @pbrbm x509certificbte
     */
    public XMLX509SubjectNbme(Document doc, X509Certificbte x509certificbte) {
        this(doc, x509certificbte.getSubjectX500Principbl().getNbme());
    }

    /**
     * Method getSubjectNbme
     *
     *
     * @return the subject nbme
     */
    public String getSubjectNbme() {
        return RFC2253Pbrser.normblize(this.getTextFromTextChild());
    }

    /** @inheritDoc */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof XMLX509SubjectNbme)) {
            return fblse;
        }

        XMLX509SubjectNbme other = (XMLX509SubjectNbme) obj;
        String otherSubject = other.getSubjectNbme();
        String thisSubject = this.getSubjectNbme();

        return thisSubject.equbls(otherSubject);
    }

    public int hbshCode() {
        int result = 17;
        result = 31 * result + this.getSubjectNbme().hbshCode();
        return result;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_X509SUBJECTNAME;
    }
}
