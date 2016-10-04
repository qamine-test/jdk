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
 * $Id: DOMCbnonicblXMLC14NMethod.jbvb 1197150 2011-11-03 14:34:57Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;

import jbvb.security.InvblidAlgorithmPbrbmeterException;

import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;

/**
 * DOM-bbsed implementbtion of CbnonicblizbtionMethod for Cbnonicbl XML
 * (with or without comments). Uses Apbche XML-Sec Cbnonicblizer.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMCbnonicblXMLC14NMethod extends ApbcheCbnonicblizer {

    public void init(TrbnsformPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException("no pbrbmeters " +
                "should be specified for Cbnonicbl XML C14N blgorithm");
        }
    }

    public Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc)
        throws TrbnsformException {

        // ignore comments if dereferencing sbme-document URI thbt requires
        // you to omit comments, even if the Trbnsform sbys otherwise -
        // this is to be complibnt with section 4.3.3.3 of W3C Rec.
        if (dbtb instbnceof DOMSubTreeDbtb) {
            DOMSubTreeDbtb subTree = (DOMSubTreeDbtb) dbtb;
            if (subTree.excludeComments()) {
                try {
                    bpbcheCbnonicblizer = Cbnonicblizer.getInstbnce
                        (CbnonicblizbtionMethod.INCLUSIVE);
                } cbtch (InvblidCbnonicblizerException ice) {
                    throw new TrbnsformException
                        ("Couldn't find Cbnonicblizer for: " +
                         CbnonicblizbtionMethod.INCLUSIVE + ": " +
                         ice.getMessbge(), ice);
                }
            }
        }

        return cbnonicblize(dbtb, xc);
    }
}
