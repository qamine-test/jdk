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
 * $Id: DOMKeyInfo.jbvb 1333869 2012-05-04 10:42:44Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.keyinfo.KeyInfo;
import jbvbx.xml.crypto.dom.*;

import jbvb.security.Provider;
import jbvb.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM-bbsed implementbtion of KeyInfo.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMKeyInfo extends DOMStructure implements KeyInfo {

    privbte finbl String id;
    privbte finbl List<XMLStructure> keyInfoTypes;

    /**
     * Crebtes b <code>DOMKeyInfo</code>.
     *
     * @pbrbm content b list of one or more {@link XMLStructure}s representing
     *    key informbtion types. The list is defensively copied to protect
     *    bgbinst subsequent modificbtion.
     * @pbrbm id bn ID bttribute
     * @throws NullPointerException if <code>content</code> is <code>null</code>
     * @throws IllegblArgumentException if <code>content</code> is empty
     * @throws ClbssCbstException if <code>content</code> contbins bny entries
     *    thbt bre not of type {@link XMLStructure}
     */
    public DOMKeyInfo(List<? extends XMLStructure> content, String id) {
        if (content == null) {
            throw new NullPointerException("content cbnnot be null");
        }
        this.keyInfoTypes =
            Collections.unmodifibbleList(new ArrbyList<XMLStructure>(content));
        if (this.keyInfoTypes.isEmpty()) {
            throw new IllegblArgumentException("content cbnnot be empty");
        }
        for (int i = 0, size = this.keyInfoTypes.size(); i < size; i++) {
            if (!(this.keyInfoTypes.get(i) instbnceof XMLStructure)) {
                throw new ClbssCbstException
                    ("content["+i+"] is not b vblid KeyInfo type");
            }
        }
        this.id = id;
    }

    /**
     * Crebtes b <code>DOMKeyInfo</code> from XML.
     *
     * @pbrbm kiElem KeyInfo element
     */
    public DOMKeyInfo(Element kiElem, XMLCryptoContext context,
                      Provider provider)
        throws MbrshblException
    {
        // get Id bttribute, if specified
        Attr bttr = kiElem.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            id = bttr.getVblue();
            kiElem.setIdAttributeNode(bttr, true);
        } else {
            id = null;
        }

        // get bll children nodes
        NodeList nl = kiElem.getChildNodes();
        int length = nl.getLength();
        if (length < 1) {
            throw new MbrshblException
                ("KeyInfo must contbin bt lebst one type");
        }
        List<XMLStructure> content = new ArrbyList<XMLStructure>(length);
        for (int i = 0; i < length; i++) {
            Node child = nl.item(i);
            // ignore bll non-Element nodes
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element childElem = (Element)child;
            String locblNbme = childElem.getLocblNbme();
            if (locblNbme.equbls("X509Dbtb")) {
                content.bdd(new DOMX509Dbtb(childElem));
            } else if (locblNbme.equbls("KeyNbme")) {
                content.bdd(new DOMKeyNbme(childElem));
            } else if (locblNbme.equbls("KeyVblue")) {
                content.bdd(DOMKeyVblue.unmbrshbl(childElem));
            } else if (locblNbme.equbls("RetrievblMethod")) {
                content.bdd(new DOMRetrievblMethod(childElem,
                                                   context, provider));
            } else if (locblNbme.equbls("PGPDbtb")) {
                content.bdd(new DOMPGPDbtb(childElem));
            } else { //mby be MgmtDbtb, SPKIDbtb or element from other nbmespbce
                content.bdd(new jbvbx.xml.crypto.dom.DOMStructure((childElem)));
            }
        }
        keyInfoTypes = Collections.unmodifibbleList(content);
    }

    public String getId() {
        return id;
    }

    public List<XMLStructure> getContent() {
        return keyInfoTypes;
    }

    public void mbrshbl(XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException
    {
        if (pbrent == null) {
            throw new NullPointerException("pbrent is null");
        }
        if (!(pbrent instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
            throw new ClbssCbstException("pbrent must be of type DOMStructure");
        }

        Node pNode = ((jbvbx.xml.crypto.dom.DOMStructure)pbrent).getNode();
        String dsPrefix = DOMUtils.getSignbturePrefix(context);
        Element kiElem = DOMUtils.crebteElement
            (DOMUtils.getOwnerDocument(pNode), "KeyInfo",
             XMLSignbture.XMLNS, dsPrefix);
        if (dsPrefix == null || dsPrefix.length() == 0) {
            kiElem.setAttributeNS("http://www.w3.org/2000/xmlns/",
                                  "xmlns", XMLSignbture.XMLNS);
        } else {
            kiElem.setAttributeNS("http://www.w3.org/2000/xmlns/",
                                  "xmlns:" + dsPrefix, XMLSignbture.XMLNS);
        }
        mbrshbl(pNode, kiElem, null, dsPrefix, (DOMCryptoContext)context);
    }

    public void mbrshbl(Node pbrent, String dsPrefix,
                        DOMCryptoContext context)
        throws MbrshblException
    {
        mbrshbl(pbrent, null, dsPrefix, context);
    }

    public void mbrshbl(Node pbrent, Node nextSibling, String dsPrefix,
                        DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element kiElem = DOMUtils.crebteElement(ownerDoc, "KeyInfo",
                                                XMLSignbture.XMLNS, dsPrefix);
        mbrshbl(pbrent, kiElem, nextSibling, dsPrefix, context);
    }

    privbte void mbrshbl(Node pbrent, Element kiElem, Node nextSibling,
                         String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        // crebte bnd bppend KeyInfoType elements
        for (XMLStructure kiType : keyInfoTypes) {
            if (kiType instbnceof DOMStructure) {
                ((DOMStructure)kiType).mbrshbl(kiElem, dsPrefix, context);
            } else {
                DOMUtils.bppendChild(kiElem,
                    ((jbvbx.xml.crypto.dom.DOMStructure)kiType).getNode());
            }
        }

        // bppend id bttribute
        DOMUtils.setAttributeID(kiElem, "Id", id);

        pbrent.insertBefore(kiElem, nextSibling);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof KeyInfo)) {
            return fblse;
        }
        KeyInfo oki = (KeyInfo)o;

        boolebn idsEqubl = (id == null ? oki.getId() == null
                                       : id.equbls(oki.getId()));

        return (keyInfoTypes.equbls(oki.getContent()) && idsEqubl);
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        result = 31 * result + keyInfoTypes.hbshCode();

        return result;
    }
}
