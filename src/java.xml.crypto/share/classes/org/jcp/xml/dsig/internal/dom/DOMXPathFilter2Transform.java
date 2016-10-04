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
 * ===========================================================================
 *
 * (C) Copyright IBM Corp. 2003 All Rights Reserved.
 *
 * ===========================================================================
 */
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMXPbthFilter2Trbnsform.jbvb 1203789 2011-11-18 18:46:07Z mullbn $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.XPbthType;
import jbvbx.xml.crypto.dsig.spec.XPbthFilter2PbrbmeterSpec;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;

/**
 * DOM-bbsed implementbtion of XPbth Filter 2.0 Trbnsform.
 * (Uses Apbche XML-Sec Trbnsform implementbtion)
 *
 * @buthor Joyce Leung
 */
public finbl clbss DOMXPbthFilter2Trbnsform extends ApbcheTrbnsform {

    public void init(TrbnsformPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms == null) {
            throw new InvblidAlgorithmPbrbmeterException("pbrbms bre required");
        } else if (!(pbrbms instbnceof XPbthFilter2PbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("pbrbms must be of type XPbthFilter2PbrbmeterSpec");
        }
        this.pbrbms = pbrbms;
    }

    public void init(XMLStructure pbrent, XMLCryptoContext context)
        throws InvblidAlgorithmPbrbmeterException
    {
        super.init(pbrent, context);
        try {
            unmbrshblPbrbms(DOMUtils.getFirstChildElement(trbnsformElem));
        } cbtch (MbrshblException me) {
            throw new InvblidAlgorithmPbrbmeterException(me);
        }
    }

    privbte void unmbrshblPbrbms(Element curXPbthElem) throws MbrshblException
    {
        List<XPbthType> list = new ArrbyList<XPbthType>();
        while (curXPbthElem != null) {
            String xPbth = curXPbthElem.getFirstChild().getNodeVblue();
            String filterVbl = DOMUtils.getAttributeVblue(curXPbthElem,
                                                          "Filter");
            if (filterVbl == null) {
                throw new MbrshblException("filter cbnnot be null");
            }
            XPbthType.Filter filter = null;
            if (filterVbl.equbls("intersect")) {
                filter = XPbthType.Filter.INTERSECT;
            } else if (filterVbl.equbls("subtrbct")) {
                filter = XPbthType.Filter.SUBTRACT;
            } else if (filterVbl.equbls("union")) {
                filter = XPbthType.Filter.UNION;
            } else {
                throw new MbrshblException("Unknown XPbthType filter type" +
                                           filterVbl);
            }
            NbmedNodeMbp bttributes = curXPbthElem.getAttributes();
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
                list.bdd(new XPbthType(xPbth, filter, nbmespbceMbp));
            } else {
                list.bdd(new XPbthType(xPbth, filter));
            }

            curXPbthElem = DOMUtils.getNextSiblingElement(curXPbthElem);
        }
        this.pbrbms = new XPbthFilter2PbrbmeterSpec(list);
    }

    public void mbrshblPbrbms(XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException
    {
        super.mbrshblPbrbms(pbrent, context);
        XPbthFilter2PbrbmeterSpec xp =
            (XPbthFilter2PbrbmeterSpec)getPbrbmeterSpec();
        String prefix = DOMUtils.getNSPrefix(context, Trbnsform.XPATH2);
        String qnbme = (prefix == null || prefix.length() == 0)
                       ? "xmlns" : "xmlns:" + prefix;
        @SuppressWbrnings("unchecked")
        List<XPbthType> xpbthList = xp.getXPbthList();
        for (XPbthType xpbthType : xpbthList) {
            Element elem = DOMUtils.crebteElement(ownerDoc, "XPbth",
                                                  Trbnsform.XPATH2, prefix);
            elem.bppendChild
                (ownerDoc.crebteTextNode(xpbthType.getExpression()));
            DOMUtils.setAttribute(elem, "Filter",
                                  xpbthType.getFilter().toString());
            elem.setAttributeNS("http://www.w3.org/2000/xmlns/", qnbme,
                                Trbnsform.XPATH2);

            // bdd nbmespbce bttributes, if necessbry
            @SuppressWbrnings("unchecked")
            Set<Mbp.Entry<String, String>> entries =
                xpbthType.getNbmespbceMbp().entrySet();
            for (Mbp.Entry<String, String> entry : entries) {
                elem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" +
                                    entry.getKey(),
                                    entry.getVblue());
            }

            trbnsformElem.bppendChild(elem);
        }
    }
}
