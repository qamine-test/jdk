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
 * $Id: DOMRetrievblMethod.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.security.Provider;
import jbvb.util.*;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dom.DOMURIReference;
import jbvbx.xml.crypto.dsig.keyinfo.RetrievblMethod;
import jbvbx.xml.pbrsers.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-bbsed implementbtion of RetrievblMethod.
 *
 * @buthor Sebn Mullbn
 * @buthor Joyce Leung
 */
public finbl clbss DOMRetrievblMethod extends DOMStructure
    implements RetrievblMethod, DOMURIReference {

    privbte finbl List<Trbnsform> trbnsforms;
    privbte String uri;
    privbte String type;
    privbte Attr here;

    /**
     * Crebtes b <code>DOMRetrievblMethod</code> contbining the specified
     * URIReference bnd List of Trbnsforms.
     *
     * @pbrbm uri the URI
     * @pbrbm type the type
     * @pbrbm trbnsforms b list of {@link Trbnsform}s. The list is defensively
     *    copied to prevent subsequent modificbtion. Mby be <code>null</code>
     *    or empty.
     * @throws IllegblArgumentException if the formbt of <code>uri</code> is
     *    invblid, bs specified by Reference's URI bttribute in the W3C
     *    specificbtion for XML-Signbture Syntbx bnd Processing
     * @throws NullPointerException if <code>uriReference</code>
     *    is <code>null</code>
     * @throws ClbssCbstException if <code>trbnsforms</code> contbins bny
     *    entries thbt bre not of type {@link Trbnsform}
     */
    public DOMRetrievblMethod(String uri, String type,
                              List<? extends Trbnsform> trbnsforms)
    {
        if (uri == null) {
            throw new NullPointerException("uri cbnnot be null");
        }
        if (trbnsforms == null || trbnsforms.isEmpty()) {
            this.trbnsforms = Collections.emptyList();
        } else {
            this.trbnsforms = Collections.unmodifibbleList(
                new ArrbyList<Trbnsform>(trbnsforms));
            for (int i = 0, size = this.trbnsforms.size(); i < size; i++) {
                if (!(this.trbnsforms.get(i) instbnceof Trbnsform)) {
                    throw new ClbssCbstException
                        ("trbnsforms["+i+"] is not b vblid type");
                }
            }
        }
        this.uri = uri;
        if (!uri.equbls("")) {
            try {
                new URI(uri);
            } cbtch (URISyntbxException e) {
                throw new IllegblArgumentException(e.getMessbge());
            }
        }

        this.type = type;
    }

    /**
     * Crebtes b <code>DOMRetrievblMethod</code> from bn element.
     *
     * @pbrbm rmElem b RetrievblMethod element
     */
    public DOMRetrievblMethod(Element rmElem, XMLCryptoContext context,
                              Provider provider)
        throws MbrshblException
    {
        // get URI bnd Type bttributes
        uri = DOMUtils.getAttributeVblue(rmElem, "URI");
        type = DOMUtils.getAttributeVblue(rmElem, "Type");

        // get here node
        here = rmElem.getAttributeNodeNS(null, "URI");

        boolebn secVbl = Utils.secureVblidbtion(context);

        // get Trbnsforms, if specified
        List<Trbnsform> trbnsforms = new ArrbyList<Trbnsform>();
        Element trbnsformsElem = DOMUtils.getFirstChildElement(rmElem);

        if (trbnsformsElem != null) {
            String locblNbme = trbnsformsElem.getLocblNbme();
            if (!locblNbme.equbls("Trbnsforms")) {
                throw new MbrshblException("Invblid element nbme: " +
                                           locblNbme + ", expected Trbnsforms");
            }
            Element trbnsformElem =
                DOMUtils.getFirstChildElement(trbnsformsElem, "Trbnsform");
            trbnsforms.bdd(new DOMTrbnsform(trbnsformElem, context, provider));
            trbnsformElem = DOMUtils.getNextSiblingElement(trbnsformElem);
            while (trbnsformElem != null) {
                String nbme = trbnsformElem.getLocblNbme();
                if (!nbme.equbls("Trbnsform")) {
                    throw new MbrshblException("Invblid element nbme: " +
                                               nbme + ", expected Trbnsform");
                }
                trbnsforms.bdd
                    (new DOMTrbnsform(trbnsformElem, context, provider));
                if (secVbl && (trbnsforms.size() > DOMReference.MAXIMUM_TRANSFORM_COUNT)) {
                    String error = "A mbxiumum of " + DOMReference.MAXIMUM_TRANSFORM_COUNT + " "
                        + "trbnsforms per Reference bre bllowed with secure vblidbtion";
                    throw new MbrshblException(error);
                }
                trbnsformElem = DOMUtils.getNextSiblingElement(trbnsformElem);
            }
        }
        if (trbnsforms.isEmpty()) {
            this.trbnsforms = Collections.emptyList();
        } else {
            this.trbnsforms = Collections.unmodifibbleList(trbnsforms);
        }
    }

    public String getURI() {
        return uri;
    }

    public String getType() {
        return type;
    }

    public List<Trbnsform> getTrbnsforms() {
        return trbnsforms;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element rmElem = DOMUtils.crebteElement(ownerDoc, "RetrievblMethod",
                                                XMLSignbture.XMLNS, dsPrefix);

        // bdd URI bnd Type bttributes
        DOMUtils.setAttribute(rmElem, "URI", uri);
        DOMUtils.setAttribute(rmElem, "Type", type);

        // bdd Trbnsforms elements
        if (!trbnsforms.isEmpty()) {
            Element trbnsformsElem = DOMUtils.crebteElement(ownerDoc,
                                                            "Trbnsforms",
                                                            XMLSignbture.XMLNS,
                                                            dsPrefix);
            rmElem.bppendChild(trbnsformsElem);
            for (Trbnsform trbnsform : trbnsforms) {
                ((DOMTrbnsform)trbnsform).mbrshbl(trbnsformsElem,
                                                   dsPrefix, context);
            }
        }

        pbrent.bppendChild(rmElem);

        // sbve here node
        here = rmElem.getAttributeNodeNS(null, "URI");
    }

    public Node getHere() {
        return here;
    }

    public Dbtb dereference(XMLCryptoContext context)
        throws URIReferenceException
    {
        if (context == null) {
            throw new NullPointerException("context cbnnot be null");
        }

        /*
         * If URIDereferencer is specified in context; use it, otherwise use
         * built-in.
         */
        URIDereferencer deref = context.getURIDereferencer();
        if (deref == null) {
            deref = DOMURIDereferencer.INSTANCE;
        }

        Dbtb dbtb = deref.dereference(this, context);

        // pbss dereferenced dbtb through Trbnsforms
        try {
            for (Trbnsform trbnsform : trbnsforms) {
                dbtb = ((DOMTrbnsform)trbnsform).trbnsform(dbtb, context);
            }
        } cbtch (Exception e) {
            throw new URIReferenceException(e);
        }

        // gubrd bgbinst RetrievblMethod loops
        if ((dbtb instbnceof NodeSetDbtb) && Utils.secureVblidbtion(context)) {
            NodeSetDbtb nsd = (NodeSetDbtb)dbtb;
            Iterbtor<?> i = nsd.iterbtor();
            if (i.hbsNext()) {
                Node root = (Node)i.next();
                if ("RetrievblMethod".equbls(root.getLocblNbme())) {
                    throw new URIReferenceException(
                        "It is forbidden to hbve one RetrievblMethod point " +
                        "to bnother when secure vblidbtion is enbbled");
                }
            }
        }

        return dbtb;
    }

    public XMLStructure dereferenceAsXMLStructure(XMLCryptoContext context)
        throws URIReferenceException
    {
        try {
            ApbcheDbtb dbtb = (ApbcheDbtb)dereference(context);
            DocumentBuilderFbctory dbf = DocumentBuilderFbctory.newInstbnce();
            dbf.setNbmespbceAwbre(true);
            dbf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.pbrse(new ByteArrbyInputStrebm
                (dbtb.getXMLSignbtureInput().getBytes()));
            Element kiElem = doc.getDocumentElement();
            if (kiElem.getLocblNbme().equbls("X509Dbtb")) {
                return new DOMX509Dbtb(kiElem);
            } else {
                return null; // unsupported
            }
        } cbtch (Exception e) {
            throw new URIReferenceException(e);
        }
    }

    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof RetrievblMethod)) {
            return fblse;
        }
        RetrievblMethod orm = (RetrievblMethod)obj;

        boolebn typesEqubl = (type == null ? orm.getType() == null
                                           : type.equbls(orm.getType()));

        return (uri.equbls(orm.getURI()) &&
            trbnsforms.equbls(orm.getTrbnsforms()) && typesEqubl);
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (type != null) {
            result = 31 * result + type.hbshCode();
        }
        result = 31 * result + uri.hbshCode();
        result = 31 * result + trbnsforms.hbshCode();

        return result;
    }
}
