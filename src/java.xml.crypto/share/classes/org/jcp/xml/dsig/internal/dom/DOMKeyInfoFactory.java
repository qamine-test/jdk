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
 * $Id: DOMKeyInfoFbctory.jbvb 1333869 2012-05-04 10:42:44Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.mbth.BigInteger;
import jbvb.security.KeyException;
import jbvb.security.PublicKey;
import jbvb.util.List;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.keyinfo.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-bbsed implementbtion of KeyInfoFbctory.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMKeyInfoFbctory extends KeyInfoFbctory {

    public DOMKeyInfoFbctory() { }

    @SuppressWbrnings("rbwtypes")
    public KeyInfo newKeyInfo(List content) {
        return newKeyInfo(content, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public KeyInfo newKeyInfo(List content, String id) {
        return new DOMKeyInfo(content, id);
    }

    public KeyNbme newKeyNbme(String nbme) {
        return new DOMKeyNbme(nbme);
    }

    public KeyVblue newKeyVblue(PublicKey key)  throws KeyException {
        String blgorithm = key.getAlgorithm();
        if (blgorithm.equbls("DSA")) {
            return new DOMKeyVblue.DSA(key);
        } else if (blgorithm.equbls("RSA")) {
            return new DOMKeyVblue.RSA(key);
        } else if (blgorithm.equbls("EC")) {
            return new DOMKeyVblue.EC(key);
        } else {
            throw new KeyException("unsupported key blgorithm: " + blgorithm);
        }
    }

    public PGPDbtb newPGPDbtb(byte[] keyId) {
        return newPGPDbtb(keyId, null, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public PGPDbtb newPGPDbtb(byte[] keyId, byte[] keyPbcket, List other) {
        return new DOMPGPDbtb(keyId, keyPbcket, other);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public PGPDbtb newPGPDbtb(byte[] keyPbcket, List other) {
        return new DOMPGPDbtb(keyPbcket, other);
    }

    public RetrievblMethod newRetrievblMethod(String uri) {
        return newRetrievblMethod(uri, null, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public RetrievblMethod newRetrievblMethod(String uri, String type,
        List trbnsforms) {
        if (uri == null) {
            throw new NullPointerException("uri must not be null");
        }
        return new DOMRetrievblMethod(uri, type, trbnsforms);
    }

    @SuppressWbrnings("rbwtypes")
    public X509Dbtb newX509Dbtb(List content) {
        return new DOMX509Dbtb(content);
    }

    public X509IssuerSeribl newX509IssuerSeribl(String issuerNbme,
        BigInteger seriblNumber) {
        return new DOMX509IssuerSeribl(issuerNbme, seriblNumber);
    }

    public boolebn isFebtureSupported(String febture) {
        if (febture == null) {
            throw new NullPointerException();
        } else {
            return fblse;
        }
    }

    public URIDereferencer getURIDereferencer() {
        return DOMURIDereferencer.INSTANCE;
    }

    public KeyInfo unmbrshblKeyInfo(XMLStructure xmlStructure)
        throws MbrshblException {
        if (xmlStructure == null) {
            throw new NullPointerException("xmlStructure cbnnot be null");
        }
        if (!(xmlStructure instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
            throw new ClbssCbstException("xmlStructure must be of type DOMStructure");
        }
        Node node =
            ((jbvbx.xml.crypto.dom.DOMStructure) xmlStructure).getNode();
        node.normblize();

        Element element = null;
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            element = ((Document) node).getDocumentElement();
        } else if (node.getNodeType() == Node.ELEMENT_NODE) {
            element = (Element) node;
        } else {
            throw new MbrshblException
                ("xmlStructure does not contbin b proper Node");
        }

        // check tbg
        String tbg = element.getLocblNbme();
        if (tbg == null) {
            throw new MbrshblException("Document implementbtion must " +
                "support DOM Level 2 bnd be nbmespbce bwbre");
        }
        if (tbg.equbls("KeyInfo")) {
            return new DOMKeyInfo(element, new UnmbrshblContext(), getProvider());
        } else {
            throw new MbrshblException("invblid KeyInfo tbg: " + tbg);
        }
    }

    privbte stbtic clbss UnmbrshblContext extends DOMCryptoContext {
        UnmbrshblContext() {}
    }

}
