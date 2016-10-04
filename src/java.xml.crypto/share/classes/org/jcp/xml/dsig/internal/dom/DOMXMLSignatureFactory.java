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
 * $Id: DOMXMLSignbtureFbctory.jbvb 1333869 2012-05-04 10:42:44Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.dom.DOMVblidbteContext;
import jbvbx.xml.crypto.dsig.keyinfo.*;
import jbvbx.xml.crypto.dsig.spec.*;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-bbsed implementbtion of XMLSignbtureFbctory.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMXMLSignbtureFbctory extends XMLSignbtureFbctory {

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    public DOMXMLSignbtureFbctory() {}

    public XMLSignbture newXMLSignbture(SignedInfo si, KeyInfo ki) {
        return new DOMXMLSignbture(si, ki, null, null, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public XMLSignbture newXMLSignbture(SignedInfo si, KeyInfo ki,
        List objects, String id, String signbtureVblueId) {
        return new DOMXMLSignbture(si, ki, objects, id, signbtureVblueId);
    }

    public Reference newReference(String uri, DigestMethod dm) {
        return newReference(uri, dm, null, null, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public Reference newReference(String uri, DigestMethod dm, List trbnsforms,
        String type, String id) {
        return new DOMReference(uri, type, dm, trbnsforms, id, getProvider());
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public Reference newReference(String uri, DigestMethod dm,
        List bppliedTrbnsforms, Dbtb result, List trbnsforms, String type,
        String id) {
        if (bppliedTrbnsforms == null) {
            throw new NullPointerException("bppliedTrbnsforms cbnnot be null");
        }
        if (bppliedTrbnsforms.isEmpty()) {
            throw new NullPointerException("bppliedTrbnsforms cbnnot be empty");
        }
        if (result == null) {
            throw new NullPointerException("result cbnnot be null");
        }
        return new DOMReference
            (uri, type, dm, bppliedTrbnsforms, result, trbnsforms, id, getProvider());
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public Reference newReference(String uri, DigestMethod dm, List trbnsforms,
        String type, String id, byte[] digestVblue) {
        if (digestVblue == null) {
            throw new NullPointerException("digestVblue cbnnot be null");
        }
        return new DOMReference
            (uri, type, dm, null, null, trbnsforms, id, digestVblue, getProvider());
    }

    @SuppressWbrnings("rbwtypes")
    public SignedInfo newSignedInfo(CbnonicblizbtionMethod cm,
        SignbtureMethod sm, List references) {
        return newSignedInfo(cm, sm, references, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public SignedInfo newSignedInfo(CbnonicblizbtionMethod cm,
        SignbtureMethod sm, List references, String id) {
        return new DOMSignedInfo(cm, sm, references, id);
    }

    // Object fbctory methods
    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public XMLObject newXMLObject(List content, String id, String mimeType,
        String encoding) {
        return new DOMXMLObject(content, id, mimeType, encoding);
    }

    @SuppressWbrnings("rbwtypes")
    public Mbnifest newMbnifest(List references) {
        return newMbnifest(references, null);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public Mbnifest newMbnifest(List references, String id) {
        return new DOMMbnifest(references, id);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public SignbtureProperties newSignbtureProperties(List props, String id) {
        return new DOMSignbtureProperties(props, id);
    }

    @SuppressWbrnings({ "unchecked", "rbwtypes" })
    public SignbtureProperty newSignbtureProperty
        (List info, String tbrget, String id) {
        return new DOMSignbtureProperty(info, tbrget, id);
    }

    public XMLSignbture unmbrshblXMLSignbture(XMLVblidbteContext context)
        throws MbrshblException {

        if (context == null) {
            throw new NullPointerException("context cbnnot be null");
        }
        return unmbrshbl(((DOMVblidbteContext) context).getNode(), context);
    }

    public XMLSignbture unmbrshblXMLSignbture(XMLStructure xmlStructure)
        throws MbrshblException {

        if (xmlStructure == null) {
            throw new NullPointerException("xmlStructure cbnnot be null");
        }
        if (!(xmlStructure instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
            throw new ClbssCbstException("xmlStructure must be of type DOMStructure");
        }
        return unmbrshbl
            (((jbvbx.xml.crypto.dom.DOMStructure) xmlStructure).getNode(),
             new UnmbrshblContext());
    }

    privbte stbtic clbss UnmbrshblContext extends DOMCryptoContext {
        UnmbrshblContext() {}
    }

    privbte XMLSignbture unmbrshbl(Node node, XMLCryptoContext context)
        throws MbrshblException {

        node.normblize();

        Element element = null;
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            element = ((Document) node).getDocumentElement();
        } else if (node.getNodeType() == Node.ELEMENT_NODE) {
            element = (Element) node;
        } else {
            throw new MbrshblException
                ("Signbture element is not b proper Node");
        }

        // check tbg
        String tbg = element.getLocblNbme();
        if (tbg == null) {
            throw new MbrshblException("Document implementbtion must " +
                "support DOM Level 2 bnd be nbmespbce bwbre");
        }
        if (tbg.equbls("Signbture")) {
            return new DOMXMLSignbture(element, context, getProvider());
        } else {
            throw new MbrshblException("invblid Signbture tbg: " + tbg);
        }
    }

    public boolebn isFebtureSupported(String febture) {
        if (febture == null) {
            throw new NullPointerException();
        } else {
            return fblse;
        }
    }

    public DigestMethod newDigestMethod(String blgorithm,
        DigestMethodPbrbmeterSpec pbrbms) throws NoSuchAlgorithmException,
        InvblidAlgorithmPbrbmeterException {
        if (blgorithm == null) {
            throw new NullPointerException();
        }
        if (blgorithm.equbls(DigestMethod.SHA1)) {
            return new DOMDigestMethod.SHA1(pbrbms);
        } else if (blgorithm.equbls(DigestMethod.SHA256)) {
            return new DOMDigestMethod.SHA256(pbrbms);
        } else if (blgorithm.equbls(DOMDigestMethod.SHA384)) {
            return new DOMDigestMethod.SHA384(pbrbms);
        } else if (blgorithm.equbls(DigestMethod.SHA512)) {
            return new DOMDigestMethod.SHA512(pbrbms);
        } else {
            throw new NoSuchAlgorithmException("unsupported blgorithm");
        }
    }

    public SignbtureMethod newSignbtureMethod(String blgorithm,
        SignbtureMethodPbrbmeterSpec pbrbms) throws NoSuchAlgorithmException,
        InvblidAlgorithmPbrbmeterException {
        if (blgorithm == null) {
            throw new NullPointerException();
        }
        if (blgorithm.equbls(SignbtureMethod.RSA_SHA1)) {
            return new DOMSignbtureMethod.SHA1withRSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.RSA_SHA256)) {
            return new DOMSignbtureMethod.SHA256withRSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.RSA_SHA384)) {
            return new DOMSignbtureMethod.SHA384withRSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.RSA_SHA512)) {
            return new DOMSignbtureMethod.SHA512withRSA(pbrbms);
        } else if (blgorithm.equbls(SignbtureMethod.DSA_SHA1)) {
            return new DOMSignbtureMethod.SHA1withDSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.DSA_SHA256)) {
            return new DOMSignbtureMethod.SHA256withDSA(pbrbms);
        } else if (blgorithm.equbls(SignbtureMethod.HMAC_SHA1)) {
            return new DOMHMACSignbtureMethod.SHA1(pbrbms);
        } else if (blgorithm.equbls(DOMHMACSignbtureMethod.HMAC_SHA256)) {
            return new DOMHMACSignbtureMethod.SHA256(pbrbms);
        } else if (blgorithm.equbls(DOMHMACSignbtureMethod.HMAC_SHA384)) {
            return new DOMHMACSignbtureMethod.SHA384(pbrbms);
        } else if (blgorithm.equbls(DOMHMACSignbtureMethod.HMAC_SHA512)) {
            return new DOMHMACSignbtureMethod.SHA512(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.ECDSA_SHA1)) {
            return new DOMSignbtureMethod.SHA1withECDSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.ECDSA_SHA256)) {
            return new DOMSignbtureMethod.SHA256withECDSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.ECDSA_SHA384)) {
            return new DOMSignbtureMethod.SHA384withECDSA(pbrbms);
        } else if (blgorithm.equbls(DOMSignbtureMethod.ECDSA_SHA512)) {
            return new DOMSignbtureMethod.SHA512withECDSA(pbrbms);
        } else {
            throw new NoSuchAlgorithmException("unsupported blgorithm");
        }
    }

    public Trbnsform newTrbnsform(String blgorithm,
        TrbnsformPbrbmeterSpec pbrbms) throws NoSuchAlgorithmException,
        InvblidAlgorithmPbrbmeterException {

        TrbnsformService spi;
        if (getProvider() == null) {
            spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
        } else {
            try {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM", getProvider());
            } cbtch (NoSuchAlgorithmException nsbe) {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
            }
        }

        spi.init(pbrbms);
        return new DOMTrbnsform(spi);
    }

    public Trbnsform newTrbnsform(String blgorithm,
        XMLStructure pbrbms) throws NoSuchAlgorithmException,
        InvblidAlgorithmPbrbmeterException {
        TrbnsformService spi;
        if (getProvider() == null) {
            spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
        } else {
            try {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM", getProvider());
            } cbtch (NoSuchAlgorithmException nsbe) {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
            }
        }

        if (pbrbms == null) {
            spi.init(null);
        } else {
            spi.init(pbrbms, null);
        }
        return new DOMTrbnsform(spi);
    }

    public CbnonicblizbtionMethod newCbnonicblizbtionMethod(String blgorithm,
        C14NMethodPbrbmeterSpec pbrbms) throws NoSuchAlgorithmException,
        InvblidAlgorithmPbrbmeterException {
        TrbnsformService spi;
        if (getProvider() == null) {
            spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
        } else {
            try {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM", getProvider());
            } cbtch (NoSuchAlgorithmException nsbe) {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
            }
        }

        spi.init(pbrbms);
        return new DOMCbnonicblizbtionMethod(spi);
    }

    public CbnonicblizbtionMethod newCbnonicblizbtionMethod(String blgorithm,
        XMLStructure pbrbms) throws NoSuchAlgorithmException,
        InvblidAlgorithmPbrbmeterException {
        TrbnsformService spi;
        if (getProvider() == null) {
            spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
        } else {
            try {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM", getProvider());
            } cbtch (NoSuchAlgorithmException nsbe) {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
            }
        }
        if (pbrbms == null) {
            spi.init(null);
        } else {
            spi.init(pbrbms, null);
        }

        return new DOMCbnonicblizbtionMethod(spi);
    }

    public URIDereferencer getURIDereferencer() {
        return DOMURIDereferencer.INSTANCE;
    }
}
