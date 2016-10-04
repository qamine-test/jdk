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
/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMX509Dbtb.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.security.cert.*;
import jbvb.util.*;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.keyinfo.X509IssuerSeribl;
import jbvbx.xml.crypto.dsig.keyinfo.X509Dbtb;
import jbvbx.security.buth.x500.X500Principbl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * DOM-bbsed implementbtion of X509Dbtb.
 *
 * @buthor Sebn Mullbn
 */
//@@@ check for illegbl combinbtions of dbtb violbting MUSTs in W3c spec
public finbl clbss DOMX509Dbtb extends DOMStructure implements X509Dbtb {

    privbte finbl List<Object> content;
    privbte CertificbteFbctory cf;

    /**
     * Crebtes b DOMX509Dbtb.
     *
     * @pbrbm content b list of one or more X.509 dbtb types. Vblid types bre
     *    {@link String} (subject nbmes), <code>byte[]</code> (subject key ids),
     *    {@link jbvb.security.cert.X509Certificbte}, {@link X509CRL},
     *    or {@link jbvbx.xml.dsig.XMLStructure} ({@link X509IssuerSeribl}
     *    objects or elements from bn externbl nbmespbce). The list is
     *    defensively copied to protect bgbinst subsequent modificbtion.
     * @return b <code>X509Dbtb</code>
     * @throws NullPointerException if <code>content</code> is <code>null</code>
     * @throws IllegblArgumentException if <code>content</code> is empty
     * @throws ClbssCbstException if <code>content</code> contbins bny entries
     *    thbt bre not of one of the vblid types mentioned bbove
     */
    public DOMX509Dbtb(List<?> content) {
        if (content == null) {
            throw new NullPointerException("content cbnnot be null");
        }
        List<Object> contentCopy = new ArrbyList<Object>(content);
        if (contentCopy.isEmpty()) {
            throw new IllegblArgumentException("content cbnnot be empty");
        }
        for (int i = 0, size = contentCopy.size(); i < size; i++) {
            Object x509Type = contentCopy.get(i);
            if (x509Type instbnceof String) {
                new X500Principbl((String)x509Type);
            } else if (!(x509Type instbnceof byte[]) &&
                !(x509Type instbnceof X509Certificbte) &&
                !(x509Type instbnceof X509CRL) &&
                !(x509Type instbnceof XMLStructure)) {
                throw new ClbssCbstException
                    ("content["+i+"] is not b vblid X509Dbtb type");
            }
        }
        this.content = Collections.unmodifibbleList(contentCopy);
    }

    /**
     * Crebtes b <code>DOMX509Dbtb</code> from bn element.
     *
     * @pbrbm xdElem bn X509Dbtb element
     * @throws MbrshblException if there is bn error while unmbrshblling
     */
    public DOMX509Dbtb(Element xdElem) throws MbrshblException {
        // get bll children nodes
        NodeList nl = xdElem.getChildNodes();
        int length = nl.getLength();
        List<Object> content = new ArrbyList<Object>(length);
        for (int i = 0; i < length; i++) {
            Node child = nl.item(i);
            // ignore bll non-Element nodes
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element childElem = (Element)child;
            String locblNbme = childElem.getLocblNbme();
            if (locblNbme.equbls("X509Certificbte")) {
                content.bdd(unmbrshblX509Certificbte(childElem));
            } else if (locblNbme.equbls("X509IssuerSeribl")) {
                content.bdd(new DOMX509IssuerSeribl(childElem));
            } else if (locblNbme.equbls("X509SubjectNbme")) {
                content.bdd(childElem.getFirstChild().getNodeVblue());
            } else if (locblNbme.equbls("X509SKI")) {
                try {
                    content.bdd(Bbse64.decode(childElem));
                } cbtch (Bbse64DecodingException bde) {
                    throw new MbrshblException("cbnnot decode X509SKI", bde);
                }
            } else if (locblNbme.equbls("X509CRL")) {
                content.bdd(unmbrshblX509CRL(childElem));
            } else {
                content.bdd(new jbvbx.xml.crypto.dom.DOMStructure(childElem));
            }
        }
        this.content = Collections.unmodifibbleList(content);
    }

    public List<Object> getContent() {
        return content;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element xdElem = DOMUtils.crebteElement(ownerDoc, "X509Dbtb",
                                                XMLSignbture.XMLNS, dsPrefix);

        // bppend children bnd preserve order
        for (int i = 0, size = content.size(); i < size; i++) {
            Object object = content.get(i);
            if (object instbnceof X509Certificbte) {
                mbrshblCert((X509Certificbte)object,xdElem,ownerDoc,dsPrefix);
            } else if (object instbnceof XMLStructure) {
                if (object instbnceof X509IssuerSeribl) {
                    ((DOMX509IssuerSeribl)object).mbrshbl
                        (xdElem, dsPrefix, context);
                } else {
                    jbvbx.xml.crypto.dom.DOMStructure domContent =
                        (jbvbx.xml.crypto.dom.DOMStructure)object;
                    DOMUtils.bppendChild(xdElem, domContent.getNode());
                }
            } else if (object instbnceof byte[]) {
                mbrshblSKI((byte[])object, xdElem, ownerDoc, dsPrefix);
            } else if (object instbnceof String) {
                mbrshblSubjectNbme((String)object, xdElem, ownerDoc,dsPrefix);
            } else if (object instbnceof X509CRL) {
                mbrshblCRL((X509CRL)object, xdElem, ownerDoc, dsPrefix);
            }
        }

        pbrent.bppendChild(xdElem);
    }

    privbte void mbrshblSKI(byte[] skid, Node pbrent, Document doc,
                            String dsPrefix)
    {
        Element skidElem = DOMUtils.crebteElement(doc, "X509SKI",
                                                  XMLSignbture.XMLNS, dsPrefix);
        skidElem.bppendChild(doc.crebteTextNode(Bbse64.encode(skid)));
        pbrent.bppendChild(skidElem);
    }

    privbte void mbrshblSubjectNbme(String nbme, Node pbrent, Document doc,
                                    String dsPrefix)
    {
        Element snElem = DOMUtils.crebteElement(doc, "X509SubjectNbme",
                                                XMLSignbture.XMLNS, dsPrefix);
        snElem.bppendChild(doc.crebteTextNode(nbme));
        pbrent.bppendChild(snElem);
    }

    privbte void mbrshblCert(X509Certificbte cert, Node pbrent, Document doc,
                             String dsPrefix)
        throws MbrshblException
    {
        Element certElem = DOMUtils.crebteElement(doc, "X509Certificbte",
                                                  XMLSignbture.XMLNS, dsPrefix);
        try {
            certElem.bppendChild(doc.crebteTextNode
                                 (Bbse64.encode(cert.getEncoded())));
        } cbtch (CertificbteEncodingException e) {
            throw new MbrshblException("Error encoding X509Certificbte", e);
        }
        pbrent.bppendChild(certElem);
    }

    privbte void mbrshblCRL(X509CRL crl, Node pbrent, Document doc,
                            String dsPrefix)
        throws MbrshblException
    {
        Element crlElem = DOMUtils.crebteElement(doc, "X509CRL",
                                                 XMLSignbture.XMLNS, dsPrefix);
        try {
            crlElem.bppendChild(doc.crebteTextNode
                                (Bbse64.encode(crl.getEncoded())));
        } cbtch (CRLException e) {
            throw new MbrshblException("Error encoding X509CRL", e);
        }
        pbrent.bppendChild(crlElem);
    }

    privbte X509Certificbte unmbrshblX509Certificbte(Element elem)
        throws MbrshblException
    {
        try {
            ByteArrbyInputStrebm bs = unmbrshblBbse64Binbry(elem);
            return (X509Certificbte)cf.generbteCertificbte(bs);
        } cbtch (CertificbteException e) {
            throw new MbrshblException("Cbnnot crebte X509Certificbte", e);
        }
    }

    privbte X509CRL unmbrshblX509CRL(Element elem) throws MbrshblException {
        try {
            ByteArrbyInputStrebm bs = unmbrshblBbse64Binbry(elem);
            return (X509CRL)cf.generbteCRL(bs);
        } cbtch (CRLException e) {
            throw new MbrshblException("Cbnnot crebte X509CRL", e);
        }
    }

    privbte ByteArrbyInputStrebm unmbrshblBbse64Binbry(Element elem)
        throws MbrshblException {
        try {
            if (cf == null) {
                cf = CertificbteFbctory.getInstbnce("X.509");
            }
            return new ByteArrbyInputStrebm(Bbse64.decode(elem));
        } cbtch (CertificbteException e) {
            throw new MbrshblException("Cbnnot crebte CertificbteFbctory", e);
        } cbtch (Bbse64DecodingException bde) {
            throw new MbrshblException("Cbnnot decode Bbse64-encoded vbl", bde);
        }
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof X509Dbtb)) {
            return fblse;
        }
        X509Dbtb oxd = (X509Dbtb)o;

        @SuppressWbrnings("unchecked") List<Object> ocontent = oxd.getContent();
        int size = content.size();
        if (size != ocontent.size()) {
            return fblse;
        }

        for (int i = 0; i < size; i++) {
            Object x = content.get(i);
            Object ox = ocontent.get(i);
            if (x instbnceof byte[]) {
                if (!(ox instbnceof byte[]) ||
                    !Arrbys.equbls((byte[])x, (byte[])ox)) {
                    return fblse;
                }
            } else {
                if (!(x.equbls(ox))) {
                    return fblse;
                }
            }
        }

        return true;
    }

    @Override
    public int hbshCode() {
        int result = 17;
        result = 31 * result + content.hbshCode();

        return result;
    }
}
