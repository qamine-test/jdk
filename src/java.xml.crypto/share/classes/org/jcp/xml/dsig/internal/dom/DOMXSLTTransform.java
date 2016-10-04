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
 * $Id: DOMXSLTTrbnsform.jbvb 1197150 2011-11-03 14:34:57Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.XSLTTrbnsformPbrbmeterSpec;

/**
 * DOM-bbsed implementbtion of XSLT Trbnsform.
 * (Uses Apbche XML-Sec Trbnsform implementbtion)
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMXSLTTrbnsform extends ApbcheTrbnsform {

    public void init(TrbnsformPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException {
        if (pbrbms == null) {
            throw new InvblidAlgorithmPbrbmeterException("pbrbms bre required");
        }
        if (!(pbrbms instbnceof XSLTTrbnsformPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException("unrecognized pbrbms");
        }
        this.pbrbms = pbrbms;
    }

    public void init(XMLStructure pbrent, XMLCryptoContext context)
        throws InvblidAlgorithmPbrbmeterException {

        super.init(pbrent, context);
        unmbrshblPbrbms(DOMUtils.getFirstChildElement(trbnsformElem));
    }

    privbte void unmbrshblPbrbms(Element sheet) {
        this.pbrbms = new XSLTTrbnsformPbrbmeterSpec
            (new jbvbx.xml.crypto.dom.DOMStructure(sheet));
    }

    public void mbrshblPbrbms(XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException {
        super.mbrshblPbrbms(pbrent, context);
        XSLTTrbnsformPbrbmeterSpec xp =
            (XSLTTrbnsformPbrbmeterSpec) getPbrbmeterSpec();
        Node xsltElem =
            ((jbvbx.xml.crypto.dom.DOMStructure) xp.getStylesheet()).getNode();
        DOMUtils.bppendChild(trbnsformElem, xsltElem);
    }
}
