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
 * $Id: DOMSignedInfo.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.security.Provider;
import jbvb.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.UnsyncBufferedOutputStrebm;

/**
 * DOM-bbsed implementbtion of SignedInfo.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMSignedInfo extends DOMStructure implements SignedInfo {

    /**
     * The mbximum number of references per Mbnifest, if secure vblidbtion is enbbled.
     */
    public stbtic finbl int MAXIMUM_REFERENCE_COUNT = 30;

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");

    /** Signbture - NOT Recommended RSAwithMD5 */
    privbte stbtic finbl String ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5 =
        Constbnts.MoreAlgorithmsSpecNS + "rsb-md5";

    /** HMAC - NOT Recommended HMAC-MD5 */
    privbte stbtic finbl String ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5 =
        Constbnts.MoreAlgorithmsSpecNS + "hmbc-md5";

    privbte List<Reference> references;
    privbte CbnonicblizbtionMethod cbnonicblizbtionMethod;
    privbte SignbtureMethod signbtureMethod;
    privbte String id;
    privbte Document ownerDoc;
    privbte Element locblSiElem;
    privbte InputStrebm cbnonDbtb;

    /**
     * Crebtes b <code>DOMSignedInfo</code> from the specified pbrbmeters. Use
     * this constructor when the <code>Id</code> is not specified.
     *
     * @pbrbm cm the cbnonicblizbtion method
     * @pbrbm sm the signbture method
     * @pbrbm references the list of references. The list is copied.
     * @throws NullPointerException if
     *    <code>cm</code>, <code>sm</code>, or <code>references</code> is
     *    <code>null</code>
     * @throws IllegblArgumentException if <code>references</code> is empty
     * @throws ClbssCbstException if bny of the references bre not of
     *    type <code>Reference</code>
     */
    public DOMSignedInfo(CbnonicblizbtionMethod cm, SignbtureMethod sm,
                         List<? extends Reference> references) {
        if (cm == null || sm == null || references == null) {
            throw new NullPointerException();
        }
        this.cbnonicblizbtionMethod = cm;
        this.signbtureMethod = sm;
        this.references = Collections.unmodifibbleList(
            new ArrbyList<Reference>(references));
        if (this.references.isEmpty()) {
            throw new IllegblArgumentException("list of references must " +
                "contbin bt lebst one entry");
        }
        for (int i = 0, size = this.references.size(); i < size; i++) {
            Object obj = this.references.get(i);
            if (!(obj instbnceof Reference)) {
                throw new ClbssCbstException("list of references contbins " +
                    "bn illegbl type");
            }
        }
    }

    /**
     * Crebtes b <code>DOMSignedInfo</code> from the specified pbrbmeters.
     *
     * @pbrbm cm the cbnonicblizbtion method
     * @pbrbm sm the signbture method
     * @pbrbm references the list of references. The list is copied.
     * @pbrbm id bn optionbl identifer thbt will bllow this
     *    <code>SignedInfo</code> to be referenced by other signbtures bnd
     *    objects
     * @throws NullPointerException if <code>cm</code>, <code>sm</code>,
     *    or <code>references</code> is <code>null</code>
     * @throws IllegblArgumentException if <code>references</code> is empty
     * @throws ClbssCbstException if bny of the references bre not of
     *    type <code>Reference</code>
     */
    public DOMSignedInfo(CbnonicblizbtionMethod cm, SignbtureMethod sm,
                         List<? extends Reference> references, String id) {
        this(cm, sm, references);
        this.id = id;
    }

    /**
     * Crebtes b <code>DOMSignedInfo</code> from bn element.
     *
     * @pbrbm siElem b SignedInfo element
     */
    public DOMSignedInfo(Element siElem, XMLCryptoContext context, Provider provider)
        throws MbrshblException {
        locblSiElem = siElem;
        ownerDoc = siElem.getOwnerDocument();

        // get Id bttribute, if specified
        id = DOMUtils.getAttributeVblue(siElem, "Id");

        // unmbrshbl CbnonicblizbtionMethod
        Element cmElem = DOMUtils.getFirstChildElement(siElem,
                                                       "CbnonicblizbtionMethod");
        cbnonicblizbtionMethod = new DOMCbnonicblizbtionMethod(cmElem, context,
                                                               provider);

        // unmbrshbl SignbtureMethod
        Element smElem = DOMUtils.getNextSiblingElement(cmElem,
                                                        "SignbtureMethod");
        signbtureMethod = DOMSignbtureMethod.unmbrshbl(smElem);

        boolebn secVbl = Utils.secureVblidbtion(context);

        String signbtureMethodAlgorithm = signbtureMethod.getAlgorithm();
        if (secVbl && ((ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5.equbls(signbtureMethodAlgorithm)
                || ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5.equbls(signbtureMethodAlgorithm)))) {
            throw new MbrshblException(
                "It is forbidden to use blgorithm " + signbtureMethod + " when secure vblidbtion is enbbled"
            );
        }

        // unmbrshbl References
        ArrbyList<Reference> refList = new ArrbyList<Reference>(5);
        Element refElem = DOMUtils.getNextSiblingElement(smElem, "Reference");
        refList.bdd(new DOMReference(refElem, context, provider));

        refElem = DOMUtils.getNextSiblingElement(refElem);
        while (refElem != null) {
            String nbme = refElem.getLocblNbme();
            if (!nbme.equbls("Reference")) {
                throw new MbrshblException("Invblid element nbme: " +
                                           nbme + ", expected Reference");
            }
            refList.bdd(new DOMReference(refElem, context, provider));

            if (secVbl && (refList.size() > MAXIMUM_REFERENCE_COUNT)) {
                String error = "A mbxiumum of " + MAXIMUM_REFERENCE_COUNT + " "
                    + "references per Mbnifest bre bllowed with secure vblidbtion";
                throw new MbrshblException(error);
            }
            refElem = DOMUtils.getNextSiblingElement(refElem);
        }
        references = Collections.unmodifibbleList(refList);
    }

    public CbnonicblizbtionMethod getCbnonicblizbtionMethod() {
        return cbnonicblizbtionMethod;
    }

    public SignbtureMethod getSignbtureMethod() {
        return signbtureMethod;
    }

    public String getId() {
        return id;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public InputStrebm getCbnonicblizedDbtb() {
        return cbnonDbtb;
    }

    public void cbnonicblize(XMLCryptoContext context, ByteArrbyOutputStrebm bos)
        throws XMLSignbtureException {
        if (context == null) {
            throw new NullPointerException("context cbnnot be null");
        }

        OutputStrebm os = new UnsyncBufferedOutputStrebm(bos);

        DOMSubTreeDbtb subTree = new DOMSubTreeDbtb(locblSiElem, true);
        try {
            ((DOMCbnonicblizbtionMethod)
                cbnonicblizbtionMethod).cbnonicblize(subTree, context, os);
        } cbtch (TrbnsformException te) {
            throw new XMLSignbtureException(te);
        }

        try {
            os.flush();
        } cbtch (IOException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
            // Impossible
        }

        byte[] signedInfoBytes = bos.toByteArrby();

        // this whole block should only be done if logging is enbbled
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbnonicblized SignedInfo:");
            StringBuilder sb = new StringBuilder(signedInfoBytes.length);
            for (int i = 0; i < signedInfoBytes.length; i++) {
                sb.bppend((chbr)signedInfoBytes[i]);
            }
            log.log(jbvb.util.logging.Level.FINE, sb.toString());
            log.log(jbvb.util.logging.Level.FINE, "Dbtb to be signed/verified:" + Bbse64.encode(signedInfoBytes));
        }

        this.cbnonDbtb = new ByteArrbyInputStrebm(signedInfoBytes);

        try {
            os.close();
        } cbtch (IOException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
            // Impossible
        }
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element siElem = DOMUtils.crebteElement(ownerDoc, "SignedInfo",
                                                XMLSignbture.XMLNS, dsPrefix);

        // crebte bnd bppend CbnonicblizbtionMethod element
        DOMCbnonicblizbtionMethod dcm =
            (DOMCbnonicblizbtionMethod)cbnonicblizbtionMethod;
        dcm.mbrshbl(siElem, dsPrefix, context);

        // crebte bnd bppend SignbtureMethod element
        ((DOMStructure)signbtureMethod).mbrshbl(siElem, dsPrefix, context);

        // crebte bnd bppend Reference elements
        for (Reference reference : references) {
            ((DOMReference)reference).mbrshbl(siElem, dsPrefix, context);
        }

        // bppend Id bttribute
        DOMUtils.setAttributeID(siElem, "Id", id);

        pbrent.bppendChild(siElem);
        locblSiElem = siElem;
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof SignedInfo)) {
            return fblse;
        }
        SignedInfo osi = (SignedInfo)o;

        boolebn idEqubl = (id == null ? osi.getId() == null
                                      : id.equbls(osi.getId()));

        return (cbnonicblizbtionMethod.equbls(osi.getCbnonicblizbtionMethod())
                && signbtureMethod.equbls(osi.getSignbtureMethod()) &&
                references.equbls(osi.getReferences()) && idEqubl);
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        result = 31 * result + cbnonicblizbtionMethod.hbshCode();
        result = 31 * result + signbtureMethod.hbshCode();
        result = 31 * result + references.hbshCode();

        return result;
    }
}
