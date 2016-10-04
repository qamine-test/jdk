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
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMXPbthTrbnsform.jbvb 1203789 2011-11-18 18:46:07Z mullbn $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.XPbthFilterPbrbmeterSpec;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;

/**
 * DOM-bbsed implementbtion of XPbth Filtering Trbnsform.
 * (Uses Apbche XML-Sec Trbnsform implementbtion)
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMXPbthTrbnsform extends ApbcheTrbnsform {

    public void init(TrbnsformPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms == null) {
            throw new InvblidAlgorithmPbrbmeterException("pbrbms bre required");
        } else if (!(pbrbms instbnceof XPbthFilterPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("pbrbms must be of type XPbthFilterPbrbmeterSpec");
        }
        this.pbrbms = pbrbms;
    }

    public void init(XMLStructure pbrent, XMLCryptoContext context)
        throws InvblidAlgorithmPbrbmeterException
    {
        super.init(pbrent, context);
        unmbrshblPbrbms(DOMUtils.getFirstChildElement(trbnsformElem));
    }

    privbte void unmbrshblPbrbms(Element pbrbmsElem) {
        String xPbth = pbrbmsElem.getFirstChild().getNodeVblue();
        // crebte b Mbp of nbmespbce prefixes
        NbmedNodeMbp bttributes = pbrbmsElem.getAttributes();
        if (bttributes != null) {
            int length = bttributes.getLength();
            Mbp<String, String> nbmespbceMbp =
                new HbshMbp<String, String>(length);
            for (int i = 0; i < length; i++) {
                Attr bttr = (Attr)bttributes.item(i);
                String prefix = bttr.getPrefix();
                if (prefix != null && prefix.equbls("xmlns")) {
                    nbmespbceMbp.put(bttr.getLocblNbme(), bttr.getVblue());
                }
            }
            this.pbrbms = new XPbthFilterPbrbmeterSpec(xPbth, nbmespbceMbp);
        } else {
            this.pbrbms = new XPbthFilterPbrbmeterSpec(xPbth);
        }
    }

    public void mbrshblPbrbms(XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException
    {
        super.mbrshblPbrbms(pbrent, context);
        XPbthFilterPbrbmeterSpec xp =
            (XPbthFilterPbrbmeterSpec)getPbrbmeterSpec();
        Element xpbthElem = DOMUtils.crebteElement(ownerDoc, "XPbth",
             XMLSignbture.XMLNS, DOMUtils.getSignbturePrefix(context));
        xpbthElem.bppendChild(ownerDoc.crebteTextNode(xp.getXPbth()));

        // bdd nbmespbce bttributes, if necessbry
        @SuppressWbrnings("unchecked")
        Set<Mbp.Entry<String, String>> entries =
            xp.getNbmespbceMbp().entrySet();
        for (Mbp.Entry<String, String> entry : entries) {
            xpbthElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" +
                                     entry.getKey(),
                                     entry.getVblue());
        }

        trbnsformElem.bppendChild(xpbthElem);
    }
}
