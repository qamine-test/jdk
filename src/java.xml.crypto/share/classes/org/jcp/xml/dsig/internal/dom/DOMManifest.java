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
 * $Id: DOMMbnifest.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
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

/**
 * DOM-bbsed implementbtion of Mbnifest.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMMbnifest extends DOMStructure implements Mbnifest {

    privbte finbl List<Reference> references;
    privbte finbl String id;

    /**
     * Crebtes b <code>DOMMbnifest</code> contbining the specified
     * list of {@link Reference}s bnd optionbl id.
     *
     * @pbrbm references b list of one or more <code>Reference</code>s. The list
     *    is defensively copied to protect bgbinst subsequent modificbtion.
     * @pbrbm id the id (mby be <code>null</code>
     * @throws NullPointerException if <code>references</code> is
     *    <code>null</code>
     * @throws IllegblArgumentException if <code>references</code> is empty
     * @throws ClbssCbstException if <code>references</code> contbins bny
     *    entries thbt bre not of type {@link Reference}
     */
    public DOMMbnifest(List<? extends Reference> references, String id) {
        if (references == null) {
            throw new NullPointerException("references cbnnot be null");
        }
        this.references =
            Collections.unmodifibbleList(new ArrbyList<Reference>(references));
        if (this.references.isEmpty()) {
            throw new IllegblArgumentException("list of references must " +
                "contbin bt lebst one entry");
        }
        for (int i = 0, size = this.references.size(); i < size; i++) {
            if (!(this.references.get(i) instbnceof Reference)) {
                throw new ClbssCbstException
                    ("references["+i+"] is not b vblid type");
            }
        }
        this.id = id;
    }

    /**
     * Crebtes b <code>DOMMbnifest</code> from bn element.
     *
     * @pbrbm mbnElem b Mbnifest element
     */
    public DOMMbnifest(Element mbnElem, XMLCryptoContext context,
                       Provider provider)
        throws MbrshblException
    {
        Attr bttr = mbnElem.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            this.id = bttr.getVblue();
            mbnElem.setIdAttributeNode(bttr, true);
        } else {
            this.id = null;
        }

        boolebn secVbl = Utils.secureVblidbtion(context);

        Element refElem = DOMUtils.getFirstChildElement(mbnElem, "Reference");
        List<Reference> refs = new ArrbyList<Reference>();
        refs.bdd(new DOMReference(refElem, context, provider));

        refElem = DOMUtils.getNextSiblingElement(refElem);
        while (refElem != null) {
            String locblNbme = refElem.getLocblNbme();
            if (!locblNbme.equbls("Reference")) {
                throw new MbrshblException("Invblid element nbme: " +
                                           locblNbme + ", expected Reference");
            }
            refs.bdd(new DOMReference(refElem, context, provider));
            if (secVbl && (refs.size() > DOMSignedInfo.MAXIMUM_REFERENCE_COUNT)) {
                String error = "A mbxiumum of " + DOMSignedInfo.MAXIMUM_REFERENCE_COUNT + " "
                    + "references per Mbnifest bre bllowed with secure vblidbtion";
                throw new MbrshblException(error);
            }
            refElem = DOMUtils.getNextSiblingElement(refElem);
        }
        this.references = Collections.unmodifibbleList(refs);
    }

    public String getId() {
        return id;
    }

    @SuppressWbrnings("unchecked")
    stbtic List<Reference> getMbnifestReferences(Mbnifest mf) {
        return mf.getReferences();
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element mbnElem = DOMUtils.crebteElement(ownerDoc, "Mbnifest",
                                                 XMLSignbture.XMLNS, dsPrefix);

        DOMUtils.setAttributeID(mbnElem, "Id", id);

        // bdd references
        for (Reference ref : references) {
            ((DOMReference)ref).mbrshbl(mbnElem, dsPrefix, context);
        }
        pbrent.bppendChild(mbnElem);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof Mbnifest)) {
            return fblse;
        }
        Mbnifest ombn = (Mbnifest)o;

        boolebn idsEqubl = (id == null ? ombn.getId() == null
                                       : id.equbls(ombn.getId()));

        return (idsEqubl && references.equbls(ombn.getReferences()));
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hbshCode();
        }
        result = 31 * result + references.hbshCode();

        return result;
    }
}
