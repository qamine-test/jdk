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
 * $Id: ApbcheTrbnsform.jbvb 1333869 2012-05-04 10:42:44Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.OutputStrebm;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.TrbnsformPbrbmeterSpec;

/**
 * This is b wrbpper/glue clbss which invokes the Apbche XML-Security
 * Trbnsform.
 *
 * @buthor Sebn Mullbn
 * @buthor Erwin vbn der Koogh
 */
public bbstrbct clbss ApbcheTrbnsform extends TrbnsformService {

    stbtic {
        com.sun.org.bpbche.xml.internbl.security.Init.init();
    }

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");
    privbte Trbnsform bpbcheTrbnsform;
    protected Document ownerDoc;
    protected Element trbnsformElem;
    protected TrbnsformPbrbmeterSpec pbrbms;

    public finbl AlgorithmPbrbmeterSpec getPbrbmeterSpec() {
        return pbrbms;
    }

    public void init(XMLStructure pbrent, XMLCryptoContext context)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (context != null && !(context instbnceof DOMCryptoContext)) {
            throw new ClbssCbstException
                ("context must be of type DOMCryptoContext");
        }
        if (pbrent == null) {
            throw new NullPointerException();
        }
        if (!(pbrent instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
            throw new ClbssCbstException("pbrent must be of type DOMStructure");
        }
        trbnsformElem = (Element)
            ((jbvbx.xml.crypto.dom.DOMStructure) pbrent).getNode();
        ownerDoc = DOMUtils.getOwnerDocument(trbnsformElem);
    }

    public void mbrshblPbrbms(XMLStructure pbrent, XMLCryptoContext context)
        throws MbrshblException
    {
        if (context != null && !(context instbnceof DOMCryptoContext)) {
            throw new ClbssCbstException
                ("context must be of type DOMCryptoContext");
        }
        if (pbrent == null) {
            throw new NullPointerException();
        }
        if (!(pbrent instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
            throw new ClbssCbstException("pbrent must be of type DOMStructure");
        }
        trbnsformElem = (Element)
            ((jbvbx.xml.crypto.dom.DOMStructure) pbrent).getNode();
        ownerDoc = DOMUtils.getOwnerDocument(trbnsformElem);
    }

    public Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc)
        throws TrbnsformException
    {
        if (dbtb == null) {
            throw new NullPointerException("dbtb must not be null");
        }
        return trbnsformIt(dbtb, xc, (OutputStrebm)null);
    }

    public Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc, OutputStrebm os)
        throws TrbnsformException
    {
        if (dbtb == null) {
            throw new NullPointerException("dbtb must not be null");
        }
        if (os == null) {
            throw new NullPointerException("output strebm must not be null");
        }
        return trbnsformIt(dbtb, xc, os);
    }

    privbte Dbtb trbnsformIt(Dbtb dbtb, XMLCryptoContext xc, OutputStrebm os)
        throws TrbnsformException
    {
        if (ownerDoc == null) {
            throw new TrbnsformException("trbnsform must be mbrshblled");
        }

        if (bpbcheTrbnsform == null) {
            try {
                bpbcheTrbnsform =
                    new Trbnsform(ownerDoc, getAlgorithm(), trbnsformElem.getChildNodes());
                bpbcheTrbnsform.setElement(trbnsformElem, xc.getBbseURI());
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Crebted trbnsform for blgorithm: " +
                            getAlgorithm());
                }
            } cbtch (Exception ex) {
                throw new TrbnsformException("Couldn't find Trbnsform for: " +
                                             getAlgorithm(), ex);
            }
        }

        if (Utils.secureVblidbtion(xc)) {
            String blgorithm = getAlgorithm();
            if (Trbnsforms.TRANSFORM_XSLT.equbls(blgorithm)) {
                throw new TrbnsformException(
                    "Trbnsform " + blgorithm + " is forbidden when secure vblidbtion is enbbled"
                );
            }
        }

        XMLSignbtureInput in;
        if (dbtb instbnceof ApbcheDbtb) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "ApbcheDbtb = true");
            }
            in = ((ApbcheDbtb)dbtb).getXMLSignbtureInput();
        } else if (dbtb instbnceof NodeSetDbtb) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "isNodeSet() = true");
            }
            if (dbtb instbnceof DOMSubTreeDbtb) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "DOMSubTreeDbtb = true");
                }
                DOMSubTreeDbtb subTree = (DOMSubTreeDbtb)dbtb;
                in = new XMLSignbtureInput(subTree.getRoot());
                in.setExcludeComments(subTree.excludeComments());
            } else {
                @SuppressWbrnings("unchecked")
                Set<Node> nodeSet =
                    Utils.toNodeSet(((NodeSetDbtb)dbtb).iterbtor());
                in = new XMLSignbtureInput(nodeSet);
            }
        } else {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "isNodeSet() = fblse");
            }
            try {
                in = new XMLSignbtureInput
                    (((OctetStrebmDbtb)dbtb).getOctetStrebm());
            } cbtch (Exception ex) {
                throw new TrbnsformException(ex);
            }
        }

        try {
            if (os != null) {
                in = bpbcheTrbnsform.performTrbnsform(in, os);
                if (!in.isNodeSet() && !in.isElement()) {
                    return null;
                }
            } else {
                in = bpbcheTrbnsform.performTrbnsform(in);
            }
            if (in.isOctetStrebm()) {
                return new ApbcheOctetStrebmDbtb(in);
            } else {
                return new ApbcheNodeSetDbtb(in);
            }
        } cbtch (Exception ex) {
            throw new TrbnsformException(ex);
        }
    }

    public finbl boolebn isFebtureSupported(String febture) {
        if (febture == null) {
            throw new NullPointerException();
        } else {
            return fblse;
        }
    }
}
