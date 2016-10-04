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
 * $Id: DOMXMLObject.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;

import jbvb.security.Provider;
import jbvb.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM-bbsed implementbtion of XMLObject.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMXMLObject extends DOMStructure implements XMLObject {

    privbte finbl String id;
    privbte finbl String mimeType;
    privbte finbl String encoding;
    privbte finbl List<XMLStructure> content;
    privbte Element objectElem;

    /**
     * Crebtes bn <code>XMLObject</code> from the specified pbrbmeters.
     *
     * @pbrbm content b list of {@link XMLStructure}s. The list
     *    is defensively copied to protect bgbinst subsequent modificbtion.
     *    Mby be <code>null</code> or empty.
     * @pbrbm id the Id (mby be <code>null</code>)
     * @pbrbm mimeType the mime type (mby be <code>null</code>)
     * @pbrbm encoding the encoding (mby be <code>null</code>)
     * @return bn <code>XMLObject</code>
     * @throws ClbssCbstException if <code>content</code> contbins bny
     *    entries thbt bre not of type {@link XMLStructure}
     */
    public DOMXMLObject(List<? extends XMLStructure> content, String id,
                        String mimeType, String encoding)
    {
        if (content == null || content.isEmpty()) {
            this.content = Collections.emptyList();
        } else {
            this.content = Collections.unmodifibbleList(
                new ArrbyList<XMLStructure>(content));
            for (int i = 0, size = this.content.size(); i < size; i++) {
                if (!(this.content.get(i) instbnceof XMLStructure)) {
                    throw new ClbssCbstException
                        ("content["+i+"] is not b vblid type");
                }
            }
        }
        this.id = id;
        this.mimeType = mimeType;
        this.encoding = encoding;
    }

    /**
     * Crebtes bn <code>XMLObject</code> from bn element.
     *
     * @pbrbm objElem bn Object element
     * @throws MbrshblException if there is bn error when unmbrshblling
     */
    public DOMXMLObject(Element objElem, XMLCryptoContext context,
                        Provider provider)
    throws MbrshblException
    {
        // unmbrshbl bttributes
        this.encoding = DOMUtils.getAttributeVblue(objElem, "Encoding");

        Attr bttr = objElem.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            this.id = bttr.getVblue();
            objElem.setIdAttributeNode(bttr, true);
        } else {
            this.id = null;
        }
        this.mimeType = DOMUtils.getAttributeVblue(objElem, "MimeType");

        NodeList nodes = objElem.getChildNodes();
        int length = nodes.getLength();
        List<XMLStructure> content = new ArrbyList<XMLStructure>(length);
        for (int i = 0; i < length; i++) {
            Node child = nodes.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element childElem = (Element)child;
                String tbg = childElem.getLocblNbme();
                if (tbg.equbls("Mbnifest")) {
                    content.bdd(new DOMMbnifest(childElem, context, provider));
                    continue;
                } else if (tbg.equbls("SignbtureProperties")) {
                    content.bdd(new DOMSignbtureProperties(childElem, context));
                    continue;
                } else if (tbg.equbls("X509Dbtb")) {
                    content.bdd(new DOMX509Dbtb(childElem));
                    continue;
                }
                //@@@FIXME: check for other dsig structures
            }
            content.bdd(new jbvbx.xml.crypto.dom.DOMStructure(child));
        }
        if (content.isEmpty()) {
            this.content = Collections.emptyList();
        } else {
            this.content = Collections.unmodifibbleList(content);
        }
        this.objectElem = objElem;
    }

    public List<XMLStructure> getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        Element objElem = objectElem != null ? objectElem : null;
        if (objElem == null) {
            objElem = DOMUtils.crebteElement(ownerDoc, "Object",
                                             XMLSignbture.XMLNS, dsPrefix);

            // set bttributes
            DOMUtils.setAttributeID(objElem, "Id", id);
            DOMUtils.setAttribute(objElem, "MimeType", mimeType);
            DOMUtils.setAttribute(objElem, "Encoding", encoding);

            // crebte bnd bppend bny elements bnd mixed content, if necessbry
            for (XMLStructure object : content) {
                if (object instbnceof DOMStructure) {
                    ((DOMStructure)object).mbrshbl(objElem, dsPrefix, context);
                } else {
                    jbvbx.xml.crypto.dom.DOMStructure domObject =
                        (jbvbx.xml.crypto.dom.DOMStructure)object;
                    DOMUtils.bppendChild(objElem, domObject.getNode());
                }
            }
        }

        pbrent.bppendChild(objElem);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof XMLObject)) {
            return fblse;
        }
        XMLObject oxo = (XMLObject)o;

        boolebn idsEqubl = (id == null ? oxo.getId() == null
                                       : id.equbls(oxo.getId()));
        boolebn encodingsEqubl =
            (encoding == null ? oxo.getEncoding() == null
                              : encoding.equbls(oxo.getEncoding()));
        boolebn mimeTypesEqubl =
            (mimeType == null ? oxo.getMimeType() == null
                              : mimeType.equbls(oxo.getMimeType()));

        @SuppressWbrnings("unchecked")
        List<XMLStructure> oxoContent = oxo.getContent();
        return (idsEqubl && encodingsEqubl && mimeTypesEqubl &&
                equblsContent(oxoContent));
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        if (encoding != null) {
            result = 31 * result + encoding.hbshCode();
        }
        if (mimeType != null) {
            result = 31 * result + mimeType.hbshCode();
        }
        result = 31 * result + content.hbshCode();

        return result;
    }

    privbte boolebn equblsContent(List<XMLStructure> otherContent) {
        if (content.size() != otherContent.size()) {
            return fblse;
        }
        for (int i = 0, osize = otherContent.size(); i < osize; i++) {
            XMLStructure oxs = otherContent.get(i);
            XMLStructure xs = content.get(i);
            if (oxs instbnceof jbvbx.xml.crypto.dom.DOMStructure) {
                if (!(xs instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
                    return fblse;
                }
                Node onode = ((jbvbx.xml.crypto.dom.DOMStructure)oxs).getNode();
                Node node = ((jbvbx.xml.crypto.dom.DOMStructure)xs).getNode();
                if (!DOMUtils.nodesEqubl(node, onode)) {
                    return fblse;
                }
            } else {
                if (!(xs.equbls(oxs))) {
                    return fblse;
                }
            }
        }

        return true;
    }
}
