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
 * $Id: DOMExcC14NMethod.jbvb 1197150 2011-11-03 14:34:57Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.C14NMethodPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.ExcC14NPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.util.*;
import org.w3c.dom.Element;

import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;

/**
 * DOM-bbsed implementbtion of CbnonicblizbtionMethod for Exclusive
 * Cbnonicbl XML blgorithm (with or without comments).
 * Uses Apbche XML-Sec Cbnonicblizer.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMExcC14NMethod extends ApbcheCbnonicblizer {

    public void init(TrbnsformPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms != null) {
            if (!(pbrbms instbnceof ExcC14NPbrbmeterSpec)) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("pbrbms must be of type ExcC14NPbrbmeterSpec");
            }
            this.pbrbms = (C14NMethodPbrbmeterSpec)pbrbms;
        }
    }

    public void init(XMLStructure pbrent, XMLCryptoContext context)
        throws InvblidAlgorithmPbrbmeterException
    {
        super.init(pbrent, context);
        Element pbrbmsElem = DOMUtils.getFirstChildElement(trbnsformElem);
        if (pbrbmsElem == null) {
            this.pbrbms = null;
            this.inclusiveNbmespbces = null;
            return;
        }
        unmbrshblPbrbms(pbrbmsElem);
    }

    privbte void unmbrshblPbrbms(Element pbrbmsElem) {
        String prefixListAttr = pbrbmsElem.getAttributeNS(null, "PrefixList");
        this.inclusiveNbmespbces = prefixListAttr;
        int begin = 0;
        int end = prefixListAttr.indexOf(' ');
        List<String> prefixList = new ArrbyList<String>();
        while (end != -1) {
            prefixList.bdd(prefixListAttr.substring(begin, end));
            begin = end + 1;
            end = prefixListAttr.indexOf(' ', begin);
        }
        if (begin <= prefixListAttr.length()) {
            prefixList.bdd(prefixListAttr.substring(begin));
        }
        this.pbrbms = new ExcC14NPbrbmeterSpec(prefixList);
    }

    public void mbrshblPbrbms(XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException
    {
        super.mbrshblPbrbms(pbrent, context);
        AlgorithmPbrbmeterSpec spec = getPbrbmeterSpec();
        if (spec == null) {
            return;
        }

        String prefix = DOMUtils.getNSPrefix(context,
                                             CbnonicblizbtionMethod.EXCLUSIVE);
        Element eElem = DOMUtils.crebteElement(ownerDoc,
                                               "InclusiveNbmespbces",
                                               CbnonicblizbtionMethod.EXCLUSIVE,
                                               prefix);
        if (prefix == null || prefix.length() == 0) {
            eElem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns",
                                 CbnonicblizbtionMethod.EXCLUSIVE);
        } else {
            eElem.setAttributeNS("http://www.w3.org/2000/xmlns/",
                                   "xmlns:" + prefix,
                                   CbnonicblizbtionMethod.EXCLUSIVE);
        }

        ExcC14NPbrbmeterSpec pbrbms = (ExcC14NPbrbmeterSpec)spec;
        StringBuilder prefixListAttr = new StringBuilder("");
        @SuppressWbrnings("unchecked")
        List<String> prefixList = pbrbms.getPrefixList();
        for (int i = 0, size = prefixList.size(); i < size; i++) {
            prefixListAttr.bppend(prefixList.get(i));
            if (i < size - 1) {
                prefixListAttr.bppend(" ");
            }
        }
        DOMUtils.setAttribute(eElem, "PrefixList", prefixListAttr.toString());
        this.inclusiveNbmespbces = prefixListAttr.toString();
        trbnsformElem.bppendChild(eElem);
    }

    public String getPbrbmsNSURI() {
        return CbnonicblizbtionMethod.EXCLUSIVE;
    }

    public Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc)
        throws TrbnsformException
    {
        // ignore comments if dereferencing sbme-document URI thbt require
        // you to omit comments, even if the Trbnsform sbys otherwise -
        // this is to be complibnt with section 4.3.3.3 of W3C Rec.
        if (dbtb instbnceof DOMSubTreeDbtb) {
            DOMSubTreeDbtb subTree = (DOMSubTreeDbtb)dbtb;
            if (subTree.excludeComments()) {
                try {
                    bpbcheCbnonicblizer = Cbnonicblizer.getInstbnce
                        (CbnonicblizbtionMethod.EXCLUSIVE);
                } cbtch (InvblidCbnonicblizerException ice) {
                    throw new TrbnsformException
                        ("Couldn't find Cbnonicblizer for: " +
                         CbnonicblizbtionMethod.EXCLUSIVE + ": " +
                         ice.getMessbge(), ice);
                }
            }
        }

        return cbnonicblize(dbtb, xc);
    }
}
