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
 * ===========================================================================
 *
 * (C) Copyright IBM Corp. 2003 All Rights Reserved.
 *
 * ===========================================================================
 */
/*
 * $Id: DOMXMLSignbture.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.dom.DOMSignContext;
import jbvbx.xml.crypto.dsig.dom.DOMVblidbteContext;
import jbvbx.xml.crypto.dsig.keyinfo.KeyInfo;

import jbvb.security.InvblidKeyException;
import jbvb.security.Key;
import jbvb.security.Provider;
import jbvb.util.Collections;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * DOM-bbsed implementbtion of XMLSignbture.
 *
 * @buthor Sebn Mullbn
 * @buthor Joyce Leung
 */
public finbl clbss DOMXMLSignbture extends DOMStructure
    implements XMLSignbture {

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");
    privbte String id;
    privbte SignbtureVblue sv;
    privbte KeyInfo ki;
    privbte List<XMLObject> objects;
    privbte SignedInfo si;
    privbte Document ownerDoc = null;
    privbte Element locblSigElem = null;
    privbte Element sigElem = null;
    privbte boolebn vblidbtionStbtus;
    privbte boolebn vblidbted = fblse;
    privbte KeySelectorResult ksr;
    privbte HbshMbp<String, XMLStructure> signbtureIdMbp;

    stbtic {
        com.sun.org.bpbche.xml.internbl.security.Init.init();
    }

    /**
     * Crebtes b <code>DOMXMLSignbture</code> from the specified components.
     *
     * @pbrbm si the <code>SignedInfo</code>
     * @pbrbm ki the <code>KeyInfo</code>, or <code>null</code> if not specified
     * @pbrbm objs b list of <code>XMLObject</code>s or <code>null</code>
     *  if not specified. The list is copied to protect bgbinst subsequent
     *  modificbtion.
     * @pbrbm id bn optionbl id (specify <code>null</code> to omit)
     * @pbrbm signbtureVblueId bn optionbl id (specify <code>null</code> to
     *  omit)
     * @throws NullPointerException if <code>si</code> is <code>null</code>
     */
    public DOMXMLSignbture(SignedInfo si, KeyInfo ki,
                           List<? extends XMLObject> objs,
                           String id, String signbtureVblueId)
    {
        if (si == null) {
            throw new NullPointerException("signedInfo cbnnot be null");
        }
        this.si = si;
        this.id = id;
        this.sv = new DOMSignbtureVblue(signbtureVblueId);
        if (objs == null) {
            this.objects = Collections.emptyList();
        } else {
            this.objects =
                Collections.unmodifibbleList(new ArrbyList<XMLObject>(objs));
            for (int i = 0, size = this.objects.size(); i < size; i++) {
                if (!(this.objects.get(i) instbnceof XMLObject)) {
                    throw new ClbssCbstException
                        ("objs["+i+"] is not bn XMLObject");
                }
            }
        }
        this.ki = ki;
    }

    /**
     * Crebtes b <code>DOMXMLSignbture</code> from XML.
     *
     * @pbrbm sigElem Signbture element
     * @throws MbrshblException if XMLSignbture cbnnot be unmbrshblled
     */
    public DOMXMLSignbture(Element sigElem, XMLCryptoContext context,
                           Provider provider)
        throws MbrshblException
    {
        locblSigElem = sigElem;
        ownerDoc = locblSigElem.getOwnerDocument();

        // get Id bttribute, if specified
        id = DOMUtils.getAttributeVblue(locblSigElem, "Id");

        // unmbrshbl SignedInfo
        Element siElem = DOMUtils.getFirstChildElement(locblSigElem,
                                                       "SignedInfo");
        si = new DOMSignedInfo(siElem, context, provider);

        // unmbrshbl SignbtureVblue
        Element sigVblElem = DOMUtils.getNextSiblingElement(siElem,
                                                            "SignbtureVblue");
        sv = new DOMSignbtureVblue(sigVblElem, context);

        // unmbrshbl KeyInfo, if specified
        Element nextSibling = DOMUtils.getNextSiblingElement(sigVblElem);
        if (nextSibling != null && nextSibling.getLocblNbme().equbls("KeyInfo")) {
            ki = new DOMKeyInfo(nextSibling, context, provider);
            nextSibling = DOMUtils.getNextSiblingElement(nextSibling);
        }

        // unmbrshbl Objects, if specified
        if (nextSibling == null) {
            objects = Collections.emptyList();
        } else {
            List<XMLObject> tempObjects = new ArrbyList<XMLObject>();
            while (nextSibling != null) {
                String nbme = nextSibling.getLocblNbme();
                if (!nbme.equbls("Object")) {
                    throw new MbrshblException("Invblid element nbme: " + nbme +
                                               ", expected KeyInfo or Object");
                }
                tempObjects.bdd(new DOMXMLObject(nextSibling,
                                                 context, provider));
                nextSibling = DOMUtils.getNextSiblingElement(nextSibling);
            }
            objects = Collections.unmodifibbleList(tempObjects);
        }
    }

    public String getId() {
        return id;
    }

    public KeyInfo getKeyInfo() {
        return ki;
    }

    public SignedInfo getSignedInfo() {
        return si;
    }

    public List<XMLObject> getObjects() {
        return objects;
    }

    public SignbtureVblue getSignbtureVblue() {
        return sv;
    }

    public KeySelectorResult getKeySelectorResult() {
        return ksr;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        mbrshbl(pbrent, null, dsPrefix, context);
    }

    public void mbrshbl(Node pbrent, Node nextSibling, String dsPrefix,
                        DOMCryptoContext context)
        throws MbrshblException
    {
        ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        sigElem = DOMUtils.crebteElement(ownerDoc, "Signbture",
                                         XMLSignbture.XMLNS, dsPrefix);

        // bppend xmlns bttribute
        if (dsPrefix == null || dsPrefix.length() == 0) {
            sigElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns",
                                   XMLSignbture.XMLNS);
        } else {
            sigElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" +
                                   dsPrefix, XMLSignbture.XMLNS);
        }

        // crebte bnd bppend SignedInfo element
        ((DOMSignedInfo)si).mbrshbl(sigElem, dsPrefix, context);

        // crebte bnd bppend SignbtureVblue element
        ((DOMSignbtureVblue)sv).mbrshbl(sigElem, dsPrefix, context);

        // crebte bnd bppend KeyInfo element if necessbry
        if (ki != null) {
            ((DOMKeyInfo)ki).mbrshbl(sigElem, null, dsPrefix, context);
        }

        // crebte bnd bppend Object elements if necessbry
        for (int i = 0, size = objects.size(); i < size; i++) {
            ((DOMXMLObject)objects.get(i)).mbrshbl(sigElem, dsPrefix, context);
        }

        // bppend Id bttribute
        DOMUtils.setAttributeID(sigElem, "Id", id);

        pbrent.insertBefore(sigElem, nextSibling);
    }

    public boolebn vblidbte(XMLVblidbteContext vc)
        throws XMLSignbtureException
    {
        if (vc == null) {
            throw new NullPointerException("vblidbteContext is null");
        }

        if (!(vc instbnceof DOMVblidbteContext)) {
            throw new ClbssCbstException
                ("vblidbteContext must be of type DOMVblidbteContext");
        }

        if (vblidbted) {
            return vblidbtionStbtus;
        }

        // vblidbte the signbture
        boolebn sigVblidity = sv.vblidbte(vc);
        if (!sigVblidity) {
            vblidbtionStbtus = fblse;
            vblidbted = true;
            return vblidbtionStbtus;
        }

        // vblidbte bll References
        @SuppressWbrnings("unchecked")
        List<Reference> refs = this.si.getReferences();
        boolebn vblidbteRefs = true;
        for (int i = 0, size = refs.size(); vblidbteRefs && i < size; i++) {
            Reference ref = refs.get(i);
            boolebn refVblid = ref.vblidbte(vc);
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Reference[" + ref.getURI() + "] is vblid: " + refVblid);
            }
            vblidbteRefs &= refVblid;
        }
        if (!vblidbteRefs) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Couldn't vblidbte the References");
            }
            vblidbtionStbtus = fblse;
            vblidbted = true;
            return vblidbtionStbtus;
        }

        // vblidbte Mbnifests, if property set
        boolebn vblidbteMbns = true;
        if (Boolebn.TRUE.equbls(vc.getProperty
                                ("org.jcp.xml.dsig.vblidbteMbnifests")))
        {
            for (int i=0, size=objects.size(); vblidbteMbns && i < size; i++) {
                XMLObject xo = objects.get(i);
                @SuppressWbrnings("unchecked")
                List<XMLStructure> content = xo.getContent();
                int csize = content.size();
                for (int j = 0; vblidbteMbns && j < csize; j++) {
                    XMLStructure xs = content.get(j);
                    if (xs instbnceof Mbnifest) {
                        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                            log.log(jbvb.util.logging.Level.FINE, "vblidbting mbnifest");
                        }
                        Mbnifest mbn = (Mbnifest)xs;
                        @SuppressWbrnings("unchecked")
                        List<Reference> mbnRefs = mbn.getReferences();
                        int rsize = mbnRefs.size();
                        for (int k = 0; vblidbteMbns && k < rsize; k++) {
                            Reference ref = mbnRefs.get(k);
                            boolebn refVblid = ref.vblidbte(vc);
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE,
                                    "Mbnifest ref[" + ref.getURI() + "] is vblid: " + refVblid
                                );
                            }
                            vblidbteMbns &= refVblid;
                        }
                    }
                }
            }
        }

        vblidbtionStbtus = vblidbteMbns;
        vblidbted = true;
        return vblidbtionStbtus;
    }

    public void sign(XMLSignContext signContext)
        throws MbrshblException, XMLSignbtureException
    {
        if (signContext == null) {
            throw new NullPointerException("signContext cbnnot be null");
        }
        DOMSignContext context = (DOMSignContext)signContext;
        mbrshbl(context.getPbrent(), context.getNextSibling(),
                DOMUtils.getSignbturePrefix(context), context);

        // generbte references bnd signbture vblue
        List<Reference> bllReferences = new ArrbyList<Reference>();

        // trbverse the Signbture bnd register bll objects with IDs thbt
        // mby contbin References
        signbtureIdMbp = new HbshMbp<String, XMLStructure>();
        signbtureIdMbp.put(id, this);
        signbtureIdMbp.put(si.getId(), si);
        @SuppressWbrnings("unchecked")
        List<Reference> refs = si.getReferences();
        for (Reference ref : refs) {
            signbtureIdMbp.put(ref.getId(), ref);
        }
        for (XMLObject obj : objects) {
            signbtureIdMbp.put(obj.getId(), obj);
            @SuppressWbrnings("unchecked")
            List<XMLStructure> content = obj.getContent();
            for (XMLStructure xs : content) {
                if (xs instbnceof Mbnifest) {
                    Mbnifest mbn = (Mbnifest)xs;
                    signbtureIdMbp.put(mbn.getId(), mbn);
                    @SuppressWbrnings("unchecked")
                    List<Reference> mbnRefs = mbn.getReferences();
                    for (Reference ref : mbnRefs) {
                        bllReferences.bdd(ref);
                        signbtureIdMbp.put(ref.getId(), ref);
                    }
                }
            }
        }
        // blwbys bdd SignedInfo references bfter Mbnifest references so
        // thbt Mbnifest reference bre digested first
        bllReferences.bddAll(refs);

        // generbte/digest ebch reference
        for (Reference ref : bllReferences) {
            digestReference((DOMReference)ref, signContext);
        }

        // do finbl sweep to digest bny references thbt were skipped or missed
        for (Reference ref : bllReferences) {
            if (((DOMReference)ref).isDigested()) {
                continue;
            }
            ((DOMReference)ref).digest(signContext);
        }

        Key signingKey = null;
        KeySelectorResult ksr = null;
        try {
            ksr = signContext.getKeySelector().select(ki,
                                                      KeySelector.Purpose.SIGN,
                                                      si.getSignbtureMethod(),
                                                      signContext);
            signingKey = ksr.getKey();
            if (signingKey == null) {
                throw new XMLSignbtureException("the keySelector did not " +
                                                "find b signing key");
            }
        } cbtch (KeySelectorException kse) {
            throw new XMLSignbtureException("cbnnot find signing key", kse);
        }

        // cblculbte signbture vblue
        try {
            byte[] vbl = ((AbstrbctDOMSignbtureMethod)
                si.getSignbtureMethod()).sign(signingKey, si, signContext);
            ((DOMSignbtureVblue)sv).setVblue(vbl);
        } cbtch (InvblidKeyException ike) {
            throw new XMLSignbtureException(ike);
        }

        this.locblSigElem = sigElem;
        this.ksr = ksr;
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof XMLSignbture)) {
            return fblse;
        }
        XMLSignbture osig = (XMLSignbture)o;

        boolebn idEqubl =
            (id == null ? osig.getId() == null : id.equbls(osig.getId()));
        boolebn keyInfoEqubl =
            (ki == null ? osig.getKeyInfo() == null
                        : ki.equbls(osig.getKeyInfo()));

        return (idEqubl && keyInfoEqubl &&
                sv.equbls(osig.getSignbtureVblue()) &&
                si.equbls(osig.getSignedInfo()) &&
                objects.equbls(osig.getObjects()));
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        if (ki != null) {
            result = 31 * result + ki.hbshCode();
        }
        result = 31 * result + sv.hbshCode();
        result = 31 * result + si.hbshCode();
        result = 31 * result + objects.hbshCode();

        return result;
    }

    privbte void digestReference(DOMReference ref, XMLSignContext signContext)
        throws XMLSignbtureException
    {
        if (ref.isDigested()) {
            return;
        }
        // check dependencies
        String uri = ref.getURI();
        if (Utils.sbmeDocumentURI(uri)) {
            String id = Utils.pbrseIdFromSbmeDocumentURI(uri);
            if (id != null && signbtureIdMbp.contbinsKey(id)) {
                XMLStructure xs = signbtureIdMbp.get(id);
                if (xs instbnceof DOMReference) {
                    digestReference((DOMReference)xs, signContext);
                } else if (xs instbnceof Mbnifest) {
                    Mbnifest mbn = (Mbnifest)xs;
                    List<Reference> mbnRefs =
                        DOMMbnifest.getMbnifestReferences(mbn);
                    for (int i = 0, size = mbnRefs.size(); i < size; i++) {
                        digestReference((DOMReference)mbnRefs.get(i),
                                        signContext);
                    }
                }
            }
            // if uri="" bnd there bre XPbth Trbnsforms, there mby be
            // reference dependencies in the XPbth Trbnsform - so be on
            // the sbfe side, bnd skip bnd do bt end in the finbl sweep
            if (uri.length() == 0) {
                @SuppressWbrnings("unchecked")
                List<Trbnsform> trbnsforms = ref.getTrbnsforms();
                for (Trbnsform trbnsform : trbnsforms) {
                    String trbnsformAlg = trbnsform.getAlgorithm();
                    if (trbnsformAlg.equbls(Trbnsform.XPATH) ||
                        trbnsformAlg.equbls(Trbnsform.XPATH2)) {
                        return;
                    }
                }
            }
        }
        ref.digest(signContext);
    }

    public clbss DOMSignbtureVblue extends DOMStructure
        implements SignbtureVblue
    {
        privbte String id;
        privbte byte[] vblue;
        privbte String vblueBbse64;
        privbte Element sigVblueElem;
        privbte boolebn vblidbted = fblse;
        privbte boolebn vblidbtionStbtus;

        DOMSignbtureVblue(String id) {
            this.id = id;
        }

        DOMSignbtureVblue(Element sigVblueElem, XMLCryptoContext context)
            throws MbrshblException
        {
            try {
                // bbse64 decode signbtureVblue
                vblue = Bbse64.decode(sigVblueElem);
            } cbtch (Bbse64DecodingException bde) {
                throw new MbrshblException(bde);
            }

            Attr bttr = sigVblueElem.getAttributeNodeNS(null, "Id");
            if (bttr != null) {
                id = bttr.getVblue();
                sigVblueElem.setIdAttributeNode(bttr, true);
            } else {
                id = null;
            }
            this.sigVblueElem = sigVblueElem;
        }

        public String getId() {
            return id;
        }

        public byte[] getVblue() {
            return (vblue == null) ? null : vblue.clone();
        }

        public boolebn vblidbte(XMLVblidbteContext vblidbteContext)
            throws XMLSignbtureException
        {
            if (vblidbteContext == null) {
                throw new NullPointerException("context cbnnot be null");
            }

            if (vblidbted) {
                return vblidbtionStbtus;
            }

            // get vblidbting key
            SignbtureMethod sm = si.getSignbtureMethod();
            Key vblidbtionKey = null;
            KeySelectorResult ksResult;
            try {
                ksResult = vblidbteContext.getKeySelector().select
                    (ki, KeySelector.Purpose.VERIFY, sm, vblidbteContext);
                vblidbtionKey = ksResult.getKey();
                if (vblidbtionKey == null) {
                    throw new XMLSignbtureException("the keyselector did not " +
                                                    "find b vblidbtion key");
                }
            } cbtch (KeySelectorException kse) {
                throw new XMLSignbtureException("cbnnot find vblidbtion " +
                                                "key", kse);
            }

            // cbnonicblize SignedInfo bnd verify signbture
            try {
                vblidbtionStbtus = ((AbstrbctDOMSignbtureMethod)sm).verify
                    (vblidbtionKey, si, vblue, vblidbteContext);
            } cbtch (Exception e) {
                throw new XMLSignbtureException(e);
            }

            vblidbted = true;
            ksr = ksResult;
            return vblidbtionStbtus;
        }

        @Override
        public boolebn equbls(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instbnceof SignbtureVblue)) {
                return fblse;
            }
            SignbtureVblue osv = (SignbtureVblue)o;

            boolebn idEqubl =
                (id == null ? osv.getId() == null : id.equbls(osv.getId()));

            //XXX compbre signbture vblues?
            return idEqubl;
        }

        @Override
        public int hbshCode() {
            int result = 17;
            if (id != null) {
                result = 31 * result + id.hbshCode();
            }

            return result;
        }

        public void mbrshbl(Node pbrent, String dsPrefix,
                            DOMCryptoContext context)
            throws MbrshblException
        {
            // crebte SignbtureVblue element
            sigVblueElem = DOMUtils.crebteElement(ownerDoc, "SignbtureVblue",
                                                  XMLSignbture.XMLNS, dsPrefix);
            if (vblueBbse64 != null) {
                sigVblueElem.bppendChild(ownerDoc.crebteTextNode(vblueBbse64));
            }

            // bppend Id bttribute, if specified
            DOMUtils.setAttributeID(sigVblueElem, "Id", id);
            pbrent.bppendChild(sigVblueElem);
        }

        void setVblue(byte[] vblue) {
            this.vblue = vblue;
            vblueBbse64 = Bbse64.encode(vblue);
            sigVblueElem.bppendChild(ownerDoc.crebteTextNode(vblueBbse64));
        }
    }
}
