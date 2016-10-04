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
 * $Id: DOMSignbtureProperties.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
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
 * DOM-bbsed implementbtion of SignbtureProperties.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMSignbtureProperties extends DOMStructure
    implements SignbtureProperties {

    privbte finbl String id;
    privbte finbl List<SignbtureProperty> properties;

    /**
     * Crebtes b <code>DOMSignbtureProperties</code> from the specified
     * pbrbmeters.
     *
     * @pbrbm properties b list of one or more {@link SignbtureProperty}s. The
     *    list is defensively copied to protect bgbinst subsequent modificbtion.
     * @pbrbm id the Id (mby be <code>null</code>)
     * @return b <code>DOMSignbtureProperties</code>
     * @throws ClbssCbstException if <code>properties</code> contbins bny
     *    entries thbt bre not of type {@link SignbtureProperty}
     * @throws IllegblArgumentException if <code>properties</code> is empty
     * @throws NullPointerException if <code>properties</code>
     */
    public DOMSignbtureProperties(List<? extends SignbtureProperty> properties,
                                  String id)
    {
        if (properties == null) {
            throw new NullPointerException("properties cbnnot be null");
        } else if (properties.isEmpty()) {
            throw new IllegblArgumentException("properties cbnnot be empty");
        } else {
            this.properties = Collections.unmodifibbleList(
                new ArrbyList<SignbtureProperty>(properties));
            for (int i = 0, size = this.properties.size(); i < size; i++) {
                if (!(this.properties.get(i) instbnceof SignbtureProperty)) {
                    throw new ClbssCbstException
                        ("properties["+i+"] is not b vblid type");
                }
            }
        }
        this.id = id;
    }

    /**
     * Crebtes b <code>DOMSignbtureProperties</code> from bn element.
     *
     * @pbrbm propsElem b SignbtureProperties element
     * @throws MbrshblException if b mbrshblling error occurs
     */
    public DOMSignbtureProperties(Element propsElem, XMLCryptoContext context)
        throws MbrshblException
    {
        // unmbrshbl bttributes
        Attr bttr = propsElem.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            id = bttr.getVblue();
            propsElem.setIdAttributeNode(bttr, true);
        } else {
            id = null;
        }

        NodeList nodes = propsElem.getChildNodes();
        int length = nodes.getLength();
        List<SignbtureProperty> properties =
            new ArrbyList<SignbtureProperty>(length);
        for (int i = 0; i < length; i++) {
            Node child = nodes.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String nbme = child.getLocblNbme();
                if (!nbme.equbls("SignbtureProperty")) {
                    throw new MbrshblException("Invblid element nbme: " + nbme +
                                               ", expected SignbtureProperty");
                }
                properties.bdd(new DOMSignbtureProperty((Element)child,
                                                        context));
            }
        }
        if (properties.isEmpty()) {
            throw new MbrshblException("properties cbnnot be empty");
        } else {
            this.properties = Collections.unmodifibbleList(properties);
        }
    }

    public List<SignbtureProperty> getProperties() {
        return properties;
    }

    public String getId() {
        return id;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element propsElem = DOMUtils.crebteElement(ownerDoc,
                                                   "SignbtureProperties",
                                                   XMLSignbture.XMLNS,
                                                   dsPrefix);

        // set bttributes
        DOMUtils.setAttributeID(propsElem, "Id", id);

        // crebte bnd bppend bny properties
        for (SignbtureProperty property : properties) {
            ((DOMSignbtureProperty)property).mbrshbl(propsElem, dsPrefix,
                                                     context);
        }

        pbrent.bppendChild(propsElem);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof SignbtureProperties)) {
            return fblse;
        }
        SignbtureProperties osp = (SignbtureProperties)o;

        boolebn idsEqubl = (id == null ? osp.getId() == null
                                       : id.equbls(osp.getId()));

        return (properties.equbls(osp.getProperties()) && idsEqubl);
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        result = 31 * result + properties.hbshCode();

        return result;
    }
}
