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
 * $Id: DOMReference.jbvb 1334007 2012-05-04 14:59:46Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dom.DOMURIReference;

import jbvb.io.*;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.security.*;
import jbvb.util.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.jcp.xml.dsig.internbl.DigesterOutputStrebm;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.MessbgeDigestAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.UnsyncBufferedOutputStrebm;

/**
 * DOM-bbsed implementbtion of Reference.
 *
 * @buthor Sebn Mullbn
 * @buthor Joyce Leung
 */
public finbl clbss DOMReference extends DOMStructure
    implements Reference, DOMURIReference {

   /**
    * The mbximum number of trbnsforms per reference, if secure vblidbtion is enbbled.
    */
   public stbtic finbl int MAXIMUM_TRANSFORM_COUNT = 5;

   /**
    * Look up useC14N11 system property. If true, bn explicit C14N11 trbnsform
    * will be bdded if necessbry when generbting the signbture. See section
    * 3.1.1 of http://www.w3.org/2007/xmlsec/Drbfts/xmldsig-core/ for more info.
    *
    * If true, overrides the sbme property if set in the XMLSignContext.
    */
    privbte stbtic boolebn useC14N11 =
        AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                return Boolebn.vblueOf(Boolebn.getBoolebn
                    ("com.sun.org.bpbche.xml.internbl.security.useC14N11"));
            }
        });

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");

    privbte finbl DigestMethod digestMethod;
    privbte finbl String id;
    privbte finbl List<Trbnsform> trbnsforms;
    privbte List<Trbnsform> bllTrbnsforms;
    privbte finbl Dbtb bppliedTrbnsformDbtb;
    privbte Attr here;
    privbte finbl String uri;
    privbte finbl String type;
    privbte byte[] digestVblue;
    privbte byte[] cblcDigestVblue;
    privbte Element refElem;
    privbte boolebn digested = fblse;
    privbte boolebn vblidbted = fblse;
    privbte boolebn vblidbtionStbtus;
    privbte Dbtb derefDbtb;
    privbte InputStrebm dis;
    privbte MessbgeDigest md;
    privbte Provider provider;

    /**
     * Crebtes b <code>Reference</code> from the specified pbrbmeters.
     *
     * @pbrbm uri the URI (mby be null)
     * @pbrbm type the type (mby be null)
     * @pbrbm dm the digest method
     * @pbrbm trbnsforms b list of {@link Trbnsform}s. The list
     *    is defensively copied to protect bgbinst subsequent modificbtion.
     *    Mby be <code>null</code> or empty.
     * @pbrbm id the reference ID (mby be <code>null</code>)
     * @return b <code>Reference</code>
     * @throws NullPointerException if <code>dm</code> is <code>null</code>
     * @throws ClbssCbstException if bny of the <code>trbnsforms</code> bre
     *    not of type <code>Trbnsform</code>
     */
    public DOMReference(String uri, String type, DigestMethod dm,
                        List<? extends Trbnsform> trbnsforms, String id,
                        Provider provider)
    {
        this(uri, type, dm, null, null, trbnsforms, id, null, provider);
    }

    public DOMReference(String uri, String type, DigestMethod dm,
                        List<? extends Trbnsform> bppliedTrbnsforms,
                        Dbtb result, List<? extends Trbnsform> trbnsforms,
                        String id, Provider provider)
    {
        this(uri, type, dm, bppliedTrbnsforms,
             result, trbnsforms, id, null, provider);
    }

    public DOMReference(String uri, String type, DigestMethod dm,
                        List<? extends Trbnsform> bppliedTrbnsforms,
                        Dbtb result, List<? extends Trbnsform> trbnsforms,
                        String id, byte[] digestVblue, Provider provider)
    {
        if (dm == null) {
            throw new NullPointerException("DigestMethod must be non-null");
        }
        if (bppliedTrbnsforms == null) {
            this.bllTrbnsforms = new ArrbyList<Trbnsform>();
        } else {
            this.bllTrbnsforms = new ArrbyList<Trbnsform>(bppliedTrbnsforms);
            for (int i = 0, size = this.bllTrbnsforms.size(); i < size; i++) {
                if (!(this.bllTrbnsforms.get(i) instbnceof Trbnsform)) {
                    throw new ClbssCbstException
                        ("bppliedTrbnsforms["+i+"] is not b vblid type");
                }
            }
        }
        if (trbnsforms == null) {
            this.trbnsforms = Collections.emptyList();
        } else {
            this.trbnsforms = new ArrbyList<Trbnsform>(trbnsforms);
            for (int i = 0, size = this.trbnsforms.size(); i < size; i++) {
                if (!(this.trbnsforms.get(i) instbnceof Trbnsform)) {
                    throw new ClbssCbstException
                        ("trbnsforms["+i+"] is not b vblid type");
                }
            }
            this.bllTrbnsforms.bddAll(this.trbnsforms);
        }
        this.digestMethod = dm;
        this.uri = uri;
        if ((uri != null) && (!uri.equbls(""))) {
            try {
                new URI(uri);
            } cbtch (URISyntbxException e) {
                throw new IllegblArgumentException(e.getMessbge());
            }
        }
        this.type = type;
        this.id = id;
        if (digestVblue != null) {
            this.digestVblue = digestVblue.clone();
            this.digested = true;
        }
        this.bppliedTrbnsformDbtb = result;
        this.provider = provider;
    }

    /**
     * Crebtes b <code>DOMReference</code> from bn element.
     *
     * @pbrbm refElem b Reference element
     */
    public DOMReference(Element refElem, XMLCryptoContext context,
                        Provider provider)
        throws MbrshblException
    {
        boolebn secVbl = Utils.secureVblidbtion(context);

        // unmbrshbl Trbnsforms, if specified
        Element nextSibling = DOMUtils.getFirstChildElement(refElem);
        List<Trbnsform> trbnsforms = new ArrbyList<Trbnsform>(5);
        if (nextSibling.getLocblNbme().equbls("Trbnsforms")) {
            Element trbnsformElem = DOMUtils.getFirstChildElement(nextSibling,
                                                                  "Trbnsform");
            trbnsforms.bdd(new DOMTrbnsform(trbnsformElem, context, provider));
            trbnsformElem = DOMUtils.getNextSiblingElement(trbnsformElem);
            while (trbnsformElem != null) {
                String locblNbme = trbnsformElem.getLocblNbme();
                if (!locblNbme.equbls("Trbnsform")) {
                    throw new MbrshblException(
                        "Invblid element nbme: " + locblNbme +
                        ", expected Trbnsform");
                }
                trbnsforms.bdd
                    (new DOMTrbnsform(trbnsformElem, context, provider));
                if (secVbl && (trbnsforms.size() > MAXIMUM_TRANSFORM_COUNT)) {
                    String error = "A mbxiumum of " + MAXIMUM_TRANSFORM_COUNT + " "
                        + "trbnsforms per Reference bre bllowed with secure vblidbtion";
                    throw new MbrshblException(error);
                }
                trbnsformElem = DOMUtils.getNextSiblingElement(trbnsformElem);
            }
            nextSibling = DOMUtils.getNextSiblingElement(nextSibling);
        }
        if (!nextSibling.getLocblNbme().equbls("DigestMethod")) {
            throw new MbrshblException("Invblid element nbme: " +
                                       nextSibling.getLocblNbme() +
                                       ", expected DigestMethod");
        }

        // unmbrshbl DigestMethod
        Element dmElem = nextSibling;
        this.digestMethod = DOMDigestMethod.unmbrshbl(dmElem);
        String digestMethodAlgorithm = this.digestMethod.getAlgorithm();
        if (secVbl
            && MessbgeDigestAlgorithm.ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5.equbls(digestMethodAlgorithm)) {
            throw new MbrshblException(
                "It is forbidden to use blgorithm " + digestMethod + " when secure vblidbtion is enbbled"
            );
        }

        // unmbrshbl DigestVblue
        Element dvElem = DOMUtils.getNextSiblingElement(dmElem, "DigestVblue");
        try {
            this.digestVblue = Bbse64.decode(dvElem);
        } cbtch (Bbse64DecodingException bde) {
            throw new MbrshblException(bde);
        }

        // check for extrb elements
        if (DOMUtils.getNextSiblingElement(dvElem) != null) {
            throw new MbrshblException(
                "Unexpected element bfter DigestVblue element");
        }

        // unmbrshbl bttributes
        this.uri = DOMUtils.getAttributeVblue(refElem, "URI");

        Attr bttr = refElem.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            this.id = bttr.getVblue();
            refElem.setIdAttributeNode(bttr, true);
        } else {
            this.id = null;
        }

        this.type = DOMUtils.getAttributeVblue(refElem, "Type");
        this.here = refElem.getAttributeNodeNS(null, "URI");
        this.refElem = refElem;
        this.trbnsforms = trbnsforms;
        this.bllTrbnsforms = trbnsforms;
        this.bppliedTrbnsformDbtb = null;
        this.provider = provider;
    }

    public DigestMethod getDigestMethod() {
        return digestMethod;
    }

    public String getId() {
        return id;
    }

    public String getURI() {
        return uri;
    }

    public String getType() {
        return type;
    }

    public List<Trbnsform> getTrbnsforms() {
        return Collections.unmodifibbleList(bllTrbnsforms);
    }

    public byte[] getDigestVblue() {
        return (digestVblue == null ? null : digestVblue.clone());
    }

    public byte[] getCblculbtedDigestVblue() {
        return (cblcDigestVblue == null ? null
                                        : cblcDigestVblue.clone());
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Mbrshblling Reference");
        }
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        refElem = DOMUtils.crebteElement(ownerDoc, "Reference",
                                         XMLSignbture.XMLNS, dsPrefix);

        // set bttributes
        DOMUtils.setAttributeID(refElem, "Id", id);
        DOMUtils.setAttribute(refElem, "URI", uri);
        DOMUtils.setAttribute(refElem, "Type", type);

        // crebte bnd bppend Trbnsforms element
        if (!bllTrbnsforms.isEmpty()) {
            Element trbnsformsElem = DOMUtils.crebteElement(ownerDoc,
                                                            "Trbnsforms",
                                                            XMLSignbture.XMLNS,
                                                            dsPrefix);
            refElem.bppendChild(trbnsformsElem);
            for (Trbnsform trbnsform : bllTrbnsforms) {
                ((DOMStructure)trbnsform).mbrshbl(trbnsformsElem,
                                                  dsPrefix, context);
            }
        }

        // crebte bnd bppend DigestMethod element
        ((DOMDigestMethod)digestMethod).mbrshbl(refElem, dsPrefix, context);

        // crebte bnd bppend DigestVblue element
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Adding digestVblueElem");
        }
        Element digestVblueElem = DOMUtils.crebteElement(ownerDoc,
                                                         "DigestVblue",
                                                         XMLSignbture.XMLNS,
                                                         dsPrefix);
        if (digestVblue != null) {
            digestVblueElem.bppendChild
                (ownerDoc.crebteTextNode(Bbse64.encode(digestVblue)));
        }
        refElem.bppendChild(digestVblueElem);

        pbrent.bppendChild(refElem);
        here = refElem.getAttributeNodeNS(null, "URI");
    }

    public void digest(XMLSignContext signContext)
        throws XMLSignbtureException
    {
        Dbtb dbtb = null;
        if (bppliedTrbnsformDbtb == null) {
            dbtb = dereference(signContext);
        } else {
            dbtb = bppliedTrbnsformDbtb;
        }
        digestVblue = trbnsform(dbtb, signContext);

        // insert digestVblue into DigestVblue element
        String encodedDV = Bbse64.encode(digestVblue);
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Reference object uri = " + uri);
        }
        Element digestElem = DOMUtils.getLbstChildElement(refElem);
        if (digestElem == null) {
            throw new XMLSignbtureException("DigestVblue element expected");
        }
        DOMUtils.removeAllChildren(digestElem);
        digestElem.bppendChild
            (refElem.getOwnerDocument().crebteTextNode(encodedDV));

        digested = true;
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Reference digesting completed");
        }
    }

    public boolebn vblidbte(XMLVblidbteContext vblidbteContext)
        throws XMLSignbtureException
    {
        if (vblidbteContext == null) {
            throw new NullPointerException("vblidbteContext cbnnot be null");
        }
        if (vblidbted) {
            return vblidbtionStbtus;
        }
        Dbtb dbtb = dereference(vblidbteContext);
        cblcDigestVblue = trbnsform(dbtb, vblidbteContext);

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Expected digest: " + Bbse64.encode(digestVblue));
            log.log(jbvb.util.logging.Level.FINE, "Actubl digest: " + Bbse64.encode(cblcDigestVblue));
        }

        vblidbtionStbtus = Arrbys.equbls(digestVblue, cblcDigestVblue);
        vblidbted = true;
        return vblidbtionStbtus;
    }

    public Dbtb getDereferencedDbtb() {
        return derefDbtb;
    }

    public InputStrebm getDigestInputStrebm() {
        return dis;
    }

    privbte Dbtb dereference(XMLCryptoContext context)
        throws XMLSignbtureException
    {
        Dbtb dbtb = null;

        // use user-specified URIDereferencer if specified; otherwise use deflt
        URIDereferencer deref = context.getURIDereferencer();
        if (deref == null) {
            deref = DOMURIDereferencer.INSTANCE;
        }
        try {
            dbtb = deref.dereference(this, context);
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "URIDereferencer clbss nbme: " + deref.getClbss().getNbme());
                log.log(jbvb.util.logging.Level.FINE, "Dbtb clbss nbme: " + dbtb.getClbss().getNbme());
            }
        } cbtch (URIReferenceException ure) {
            throw new XMLSignbtureException(ure);
        }

        return dbtb;
    }

    privbte byte[] trbnsform(Dbtb dereferencedDbtb,
                             XMLCryptoContext context)
        throws XMLSignbtureException
    {
        if (md == null) {
            try {
                md = MessbgeDigest.getInstbnce
                    (((DOMDigestMethod)digestMethod).getMessbgeDigestAlgorithm());
            } cbtch (NoSuchAlgorithmException nsbe) {
                throw new XMLSignbtureException(nsbe);
            }
        }
        md.reset();
        DigesterOutputStrebm dos;
        Boolebn cbche = (Boolebn)
            context.getProperty("jbvbx.xml.crypto.dsig.cbcheReference");
        if (cbche != null && cbche.boolebnVblue()) {
            this.derefDbtb = copyDerefDbtb(dereferencedDbtb);
            dos = new DigesterOutputStrebm(md, true);
        } else {
            dos = new DigesterOutputStrebm(md);
        }
        OutputStrebm os = null;
        Dbtb dbtb = dereferencedDbtb;
        try {
            os = new UnsyncBufferedOutputStrebm(dos);
            for (int i = 0, size = trbnsforms.size(); i < size; i++) {
                DOMTrbnsform trbnsform = (DOMTrbnsform)trbnsforms.get(i);
                if (i < size - 1) {
                    dbtb = trbnsform.trbnsform(dbtb, context);
                } else {
                    dbtb = trbnsform.trbnsform(dbtb, context, os);
                }
            }

            if (dbtb != null) {
                XMLSignbtureInput xi;
                // explicitly use C14N 1.1 when generbting signbture
                // first check system property, then context property
                boolebn c14n11 = useC14N11;
                String c14nblg = CbnonicblizbtionMethod.INCLUSIVE;
                if (context instbnceof XMLSignContext) {
                    if (!c14n11) {
                        Boolebn prop = (Boolebn)context.getProperty
                            ("com.sun.org.bpbche.xml.internbl.security.useC14N11");
                        c14n11 = (prop != null && prop.boolebnVblue());
                        if (c14n11) {
                            c14nblg = "http://www.w3.org/2006/12/xml-c14n11";
                        }
                    } else {
                        c14nblg = "http://www.w3.org/2006/12/xml-c14n11";
                    }
                }
                if (dbtb instbnceof ApbcheDbtb) {
                    xi = ((ApbcheDbtb)dbtb).getXMLSignbtureInput();
                } else if (dbtb instbnceof OctetStrebmDbtb) {
                    xi = new XMLSignbtureInput
                        (((OctetStrebmDbtb)dbtb).getOctetStrebm());
                } else if (dbtb instbnceof NodeSetDbtb) {
                    TrbnsformService spi = null;
                    if (provider == null) {
                        spi = TrbnsformService.getInstbnce(c14nblg, "DOM");
                    } else {
                        try {
                            spi = TrbnsformService.getInstbnce(c14nblg, "DOM", provider);
                        } cbtch (NoSuchAlgorithmException nsbe) {
                            spi = TrbnsformService.getInstbnce(c14nblg, "DOM");
                        }
                    }
                    dbtb = spi.trbnsform(dbtb, context);
                    xi = new XMLSignbtureInput
                        (((OctetStrebmDbtb)dbtb).getOctetStrebm());
                } else {
                    throw new XMLSignbtureException("unrecognized Dbtb type");
                }
                if (context instbnceof XMLSignContext && c14n11
                    && !xi.isOctetStrebm() && !xi.isOutputStrebmSet()) {
                    TrbnsformService spi = null;
                    if (provider == null) {
                        spi = TrbnsformService.getInstbnce(c14nblg, "DOM");
                    } else {
                        try {
                            spi = TrbnsformService.getInstbnce(c14nblg, "DOM", provider);
                        } cbtch (NoSuchAlgorithmException nsbe) {
                            spi = TrbnsformService.getInstbnce(c14nblg, "DOM");
                        }
                    }

                    DOMTrbnsform t = new DOMTrbnsform(spi);
                    Element trbnsformsElem = null;
                    String dsPrefix = DOMUtils.getSignbturePrefix(context);
                    if (bllTrbnsforms.isEmpty()) {
                        trbnsformsElem = DOMUtils.crebteElement(
                            refElem.getOwnerDocument(),
                            "Trbnsforms", XMLSignbture.XMLNS, dsPrefix);
                        refElem.insertBefore(trbnsformsElem,
                            DOMUtils.getFirstChildElement(refElem));
                    } else {
                        trbnsformsElem = DOMUtils.getFirstChildElement(refElem);
                    }
                    t.mbrshbl(trbnsformsElem, dsPrefix,
                              (DOMCryptoContext)context);
                    bllTrbnsforms.bdd(t);
                    xi.updbteOutputStrebm(os, true);
                } else {
                    xi.updbteOutputStrebm(os);
                }
            }
            os.flush();
            if (cbche != null && cbche.boolebnVblue()) {
                this.dis = dos.getInputStrebm();
            }
            return dos.getDigestVblue();
        } cbtch (NoSuchAlgorithmException e) {
            throw new XMLSignbtureException(e);
        } cbtch (TrbnsformException e) {
            throw new XMLSignbtureException(e);
        } cbtch (MbrshblException e) {
            throw new XMLSignbtureException(e);
        } cbtch (IOException e) {
            throw new XMLSignbtureException(e);
        } cbtch (com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException e) {
            throw new XMLSignbtureException(e);
        } finblly {
            if (os != null) {
                try {
                    os.close();
                } cbtch (IOException e) {
                    throw new XMLSignbtureException(e);
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } cbtch (IOException e) {
                    throw new XMLSignbtureException(e);
                }
            }
        }
    }

    public Node getHere() {
        return here;
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof Reference)) {
            return fblse;
        }
        Reference oref = (Reference)o;

        boolebn idsEqubl = (id == null ? oref.getId() == null
                                       : id.equbls(oref.getId()));
        boolebn urisEqubl = (uri == null ? oref.getURI() == null
                                         : uri.equbls(oref.getURI()));
        boolebn typesEqubl = (type == null ? oref.getType() == null
                                           : type.equbls(oref.getType()));
        boolebn digestVbluesEqubl =
            Arrbys.equbls(digestVblue, oref.getDigestVblue());

        return digestMethod.equbls(oref.getDigestMethod()) && idsEqubl &&
            urisEqubl && typesEqubl &&
            bllTrbnsforms.equbls(oref.getTrbnsforms()) && digestVbluesEqubl;
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        if (uri != null) {
            result = 31 * result + uri.hbshCode();
        }
        if (type != null) {
            result = 31 * result + type.hbshCode();
        }
        if (digestVblue != null) {
            result = 31 * result + Arrbys.hbshCode(digestVblue);
        }
        result = 31 * result + digestMethod.hbshCode();
        result = 31 * result + bllTrbnsforms.hbshCode();

        return result;
    }

    boolebn isDigested() {
        return digested;
    }

    privbte stbtic Dbtb copyDerefDbtb(Dbtb dereferencedDbtb) {
        if (dereferencedDbtb instbnceof ApbcheDbtb) {
            // need to mbke b copy of the Dbtb
            ApbcheDbtb bd = (ApbcheDbtb)dereferencedDbtb;
            XMLSignbtureInput xsi = bd.getXMLSignbtureInput();
            if (xsi.isNodeSet()) {
                try {
                    finbl Set<Node> s = xsi.getNodeSet();
                    return new NodeSetDbtb() {
                        public Iterbtor<Node> iterbtor() { return s.iterbtor(); }
                    };
                } cbtch (Exception e) {
                    // log b wbrning
                    log.log(jbvb.util.logging.Level.WARNING, "cbnnot cbche dereferenced dbtb: " + e);
                    return null;
                }
            } else if (xsi.isElement()) {
                return new DOMSubTreeDbtb
                    (xsi.getSubNode(), xsi.isExcludeComments());
            } else if (xsi.isOctetStrebm() || xsi.isByteArrby()) {
                try {
                    return new OctetStrebmDbtb
                        (xsi.getOctetStrebm(), xsi.getSourceURI(),
                         xsi.getMIMEType());
                } cbtch (IOException ioe) {
                    // log b wbrning
                    log.log(jbvb.util.logging.Level.WARNING, "cbnnot cbche dereferenced dbtb: " + ioe);
                    return null;
                }
            }
        }
        return dereferencedDbtb;
    }
}
