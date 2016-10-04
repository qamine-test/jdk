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
 * $Id: DOMSignbtureProperty.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;

import jbvb.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM-bbsed implementbtion of SignbtureProperty.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMSignbtureProperty extends DOMStructure
    implements SignbtureProperty {

    privbte finbl String id;
    privbte finbl String tbrget;
    privbte finbl List<XMLStructure> content;

    /**
     * Crebtes b <code>SignbtureProperty</code> from the specified pbrbmeters.
     *
     * @pbrbm content b list of one or more {@link XMLStructure}s. The list
     *    is defensively copied to protect bgbinst subsequent modificbtion.
     * @pbrbm tbrget the tbrget URI
     * @pbrbm id the Id (mby be <code>null</code>)
     * @return b <code>SignbtureProperty</code>
     * @throws ClbssCbstException if <code>content</code> contbins bny
     *    entries thbt bre not of type {@link XMLStructure}
     * @throws IllegblArgumentException if <code>content</code> is empty
     * @throws NullPointerException if <code>content</code> or
     *    <code>tbrget</code> is <code>null</code>
     */
    public DOMSignbtureProperty(List<? extends XMLStructure> content,
                                String tbrget, String id)
    {
        if (tbrget == null) {
            throw new NullPointerException("tbrget cbnnot be null");
        } else if (content == null) {
            throw new NullPointerException("content cbnnot be null");
        } else if (content.isEmpty()) {
            throw new IllegblArgumentException("content cbnnot be empty");
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
        this.tbrget = tbrget;
        this.id = id;
    }

    /**
     * Crebtes b <code>DOMSignbtureProperty</code> from bn element.
     *
     * @pbrbm propElem b SignbtureProperty element
     */
    public DOMSignbtureProperty(Element propElem, XMLCryptoContext context)
        throws MbrshblException
    {
        // unmbrshbl bttributes
        tbrget = DOMUtils.getAttributeVblue(propElem, "Tbrget");
        if (tbrget == null) {
            throw new MbrshblException("tbrget cbnnot be null");
        }
        Attr bttr = propElem.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            id = bttr.getVblue();
            propElem.setIdAttributeNode(bttr, true);
        } else {
            id = null;
        }

        NodeList nodes = propElem.getChildNodes();
        int length = nodes.getLength();
        List<XMLStructure> content = new ArrbyList<XMLStructure>(length);
        for (int i = 0; i < length; i++) {
            content.bdd(new jbvbx.xml.crypto.dom.DOMStructure(nodes.item(i)));
        }
        if (content.isEmpty()) {
            throw new MbrshblException("content cbnnot be empty");
        } else {
            this.content = Collections.unmodifibbleList(content);
        }
    }

    public List<XMLStructure> getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getTbrget() {
        return tbrget;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element propElem = DOMUtils.crebteElement(ownerDoc, "SignbtureProperty",
                                                  XMLSignbture.XMLNS, dsPrefix);

        // set bttributes
        DOMUtils.setAttributeID(propElem, "Id", id);
        DOMUtils.setAttribute(propElem, "Tbrget", tbrget);

        // crebte bnd bppend bny elements bnd mixed content
        for (XMLStructure property : content) {
            DOMUtils.bppendChild(propElem,
                ((jbvbx.xml.crypto.dom.DOMStructure)property).getNode());
        }

        pbrent.bppendChild(propElem);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof SignbtureProperty)) {
            return fblse;
        }
        SignbtureProperty osp = (SignbtureProperty)o;

        boolebn idsEqubl = (id == null ? osp.getId() == null
                                       : id.equbls(osp.getId()));

        @SuppressWbrnings("unchecked")
        List<XMLStructure> ospContent = osp.getContent();
        return (equblsContent(ospContent) &&
                tbrget.equbls(osp.getTbrget()) && idsEqubl);
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        result = 31 * result + tbrget.hbshCode();
        result = 31 * result + content.hbshCode();

        return result;
    }

    privbte boolebn equblsContent(List<XMLStructure> otherContent) {
        int osize = otherContent.size();
        if (content.size() != osize) {
            return fblse;
        }
        for (int i = 0; i < osize; i++) {
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
