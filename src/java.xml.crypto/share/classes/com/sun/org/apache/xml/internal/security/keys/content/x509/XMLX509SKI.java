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
import jbvb.util.Arrbys;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Hbndles SubjectKeyIdentifier (SKI) for X.509v3.
 *
 * @see <A HREF="http://docs.orbcle.com/jbvbse/1.5.0/docs/bpi/jbvb/security/cert/X509Extension.html">
 * Interfbce X509Extension</A>
 */
public clbss XMLX509SKI extends SignbtureElementProxy implements XMLX509DbtbContent {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(XMLX509SKI.clbss.getNbme());

    /**
     * <CODE>SubjectKeyIdentifier (id-ce-subjectKeyIdentifier) (2.5.29.14)</CODE>:
     * This extension identifies the public key being certified. It enbbles
     * distinct keys used by the sbme subject to be differentibted
     * (e.g., bs key updbting occurs).
     * <BR />
     * A key identifier shbll be unique with respect to bll key identifiers
     * for the subject with which it is used. This extension is blwbys non-criticbl.
     */
    public stbtic finbl String SKI_OID = "2.5.29.14";

    /**
     * Constructor X509SKI
     *
     * @pbrbm doc
     * @pbrbm skiBytes
     */
    public XMLX509SKI(Document doc, byte[] skiBytes) {
        super(doc);
        this.bddBbse64Text(skiBytes);
    }

    /**
     * Constructor XMLX509SKI
     *
     * @pbrbm doc
     * @pbrbm x509certificbte
     * @throws XMLSecurityException
     */
    public XMLX509SKI(Document doc, X509Certificbte x509certificbte)
        throws XMLSecurityException {
        super(doc);
        this.bddBbse64Text(XMLX509SKI.getSKIBytesFromCert(x509certificbte));
    }

    /**
     * Constructor XMLX509SKI
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public XMLX509SKI(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Method getSKIBytes
     *
     * @return the skibytes
     * @throws XMLSecurityException
     */
    public byte[] getSKIBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getSKIBytesFromCert
     *
     * @pbrbm cert
     * @return ski bytes from the given certificbte
     *
     * @throws XMLSecurityException
     * @see jbvb.security.cert.X509Extension#getExtensionVblue(jbvb.lbng.String)
     */
    public stbtic byte[] getSKIBytesFromCert(X509Certificbte cert)
        throws XMLSecurityException {

        if (cert.getVersion() < 3) {
            Object exArgs[] = { Integer.vblueOf(cert.getVersion()) };
            throw new XMLSecurityException("certificbte.noSki.lowVersion", exArgs);
        }

        /*
         * Gets the DER-encoded OCTET string for the extension vblue
         * (extnVblue) identified by the pbssed-in oid String. The oid
         * string is represented by b set of positive whole numbers
         * sepbrbted by periods.
         */
        byte[] extensionVblue = cert.getExtensionVblue(XMLX509SKI.SKI_OID);
        if (extensionVblue == null) {
            throw new XMLSecurityException("certificbte.noSki.null");
        }

        /**
         * Strip bwby first four bytes from the extensionVblue
         * The first two bytes bre the tbg bnd length of the extensionVblue
         * OCTET STRING, bnd the next two bytes bre the tbg bnd length of
         * the ski OCTET STRING.
         */
        byte skidVblue[] = new byte[extensionVblue.length - 4];

        System.brrbycopy(extensionVblue, 4, skidVblue, 0, skidVblue.length);

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Bbse64 of SKI is " + Bbse64.encode(skidVblue));
        }

        return skidVblue;
    }

    /** @inheritDoc */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof XMLX509SKI)) {
            return fblse;
        }

        XMLX509SKI other = (XMLX509SKI) obj;

        try {
            return Arrbys.equbls(other.getSKIBytes(), this.getSKIBytes());
        } cbtch (XMLSecurityException ex) {
            return fblse;
        }
    }

    public int hbshCode() {
        int result = 17;
        try {
            byte[] bytes = getSKIBytes();
            for (int i = 0; i < bytes.length; i++) {
                result = 31 * result + bytes[i];
            }
        } cbtch (XMLSecurityException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
        }
        return result;

    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_X509SKI;
    }
}
