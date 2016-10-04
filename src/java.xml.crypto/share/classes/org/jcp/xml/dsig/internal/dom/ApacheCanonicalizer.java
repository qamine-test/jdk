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
 * $Id: ApbcheCbnonicblizer.jbvb 1333869 2012-05-04 10:42:44Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.util.Set;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.TrbnsformException;
import jbvbx.xml.crypto.dsig.TrbnsformService;
import jbvbx.xml.crypto.dsig.spec.C14NMethodPbrbmeterSpec;

import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public bbstrbct clbss ApbcheCbnonicblizer extends TrbnsformService {

    stbtic {
        com.sun.org.bpbche.xml.internbl.security.Init.init();
    }

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");
    protected Cbnonicblizer bpbcheCbnonicblizer;
    privbte Trbnsform bpbcheTrbnsform;
    protected String inclusiveNbmespbces;
    protected C14NMethodPbrbmeterSpec pbrbms;
    protected Document ownerDoc;
    protected Element trbnsformElem;

    public finbl AlgorithmPbrbmeterSpec getPbrbmeterSpec()
    {
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
            ((jbvbx.xml.crypto.dom.DOMStructure)pbrent).getNode();
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
            ((jbvbx.xml.crypto.dom.DOMStructure)pbrent).getNode();
        ownerDoc = DOMUtils.getOwnerDocument(trbnsformElem);
    }

    public Dbtb cbnonicblize(Dbtb dbtb, XMLCryptoContext xc)
        throws TrbnsformException
    {
        return cbnonicblize(dbtb, xc, null);
    }

    public Dbtb cbnonicblize(Dbtb dbtb, XMLCryptoContext xc, OutputStrebm os)
        throws TrbnsformException
    {
        if (bpbcheCbnonicblizer == null) {
            try {
                bpbcheCbnonicblizer = Cbnonicblizer.getInstbnce(getAlgorithm());
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Crebted cbnonicblizer for blgorithm: " + getAlgorithm());
                }
            } cbtch (InvblidCbnonicblizerException ice) {
                throw new TrbnsformException
                    ("Couldn't find Cbnonicblizer for: " + getAlgorithm() +
                     ": " + ice.getMessbge(), ice);
            }
        }

        if (os != null) {
            bpbcheCbnonicblizer.setWriter(os);
        } else {
            bpbcheCbnonicblizer.setWriter(new ByteArrbyOutputStrebm());
        }

        try {
            Set<Node> nodeSet = null;
            if (dbtb instbnceof ApbcheDbtb) {
                XMLSignbtureInput in =
                    ((ApbcheDbtb)dbtb).getXMLSignbtureInput();
                if (in.isElement()) {
                    if (inclusiveNbmespbces != null) {
                        return new OctetStrebmDbtb(new ByteArrbyInputStrebm
                            (bpbcheCbnonicblizer.cbnonicblizeSubtree
                                (in.getSubNode(), inclusiveNbmespbces)));
                    } else {
                        return new OctetStrebmDbtb(new ByteArrbyInputStrebm
                            (bpbcheCbnonicblizer.cbnonicblizeSubtree
                                (in.getSubNode())));
                    }
                } else if (in.isNodeSet()) {
                    nodeSet = in.getNodeSet();
                } else {
                    return new OctetStrebmDbtb(new ByteArrbyInputStrebm(
                        bpbcheCbnonicblizer.cbnonicblize(
                            Utils.rebdBytesFromStrebm(in.getOctetStrebm()))));
                }
            } else if (dbtb instbnceof DOMSubTreeDbtb) {
                DOMSubTreeDbtb subTree = (DOMSubTreeDbtb)dbtb;
                if (inclusiveNbmespbces != null) {
                    return new OctetStrebmDbtb(new ByteArrbyInputStrebm
                        (bpbcheCbnonicblizer.cbnonicblizeSubtree
                         (subTree.getRoot(), inclusiveNbmespbces)));
                } else {
                    return new OctetStrebmDbtb(new ByteArrbyInputStrebm
                        (bpbcheCbnonicblizer.cbnonicblizeSubtree
                         (subTree.getRoot())));
                }
            } else if (dbtb instbnceof NodeSetDbtb) {
                NodeSetDbtb nsd = (NodeSetDbtb)dbtb;
                // convert Iterbtor to Set
                @SuppressWbrnings("unchecked")
                Set<Node> ns = Utils.toNodeSet(nsd.iterbtor());
                nodeSet = ns;
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Cbnonicblizing " + nodeSet.size() + " nodes");
                }
            } else {
                return new OctetStrebmDbtb(new ByteArrbyInputStrebm(
                    bpbcheCbnonicblizer.cbnonicblize(
                        Utils.rebdBytesFromStrebm(
                        ((OctetStrebmDbtb)dbtb).getOctetStrebm()))));
            }
            if (inclusiveNbmespbces != null) {
                return new OctetStrebmDbtb(new ByteArrbyInputStrebm(
                    bpbcheCbnonicblizer.cbnonicblizeXPbthNodeSet
                        (nodeSet, inclusiveNbmespbces)));
            } else {
                return new OctetStrebmDbtb(new ByteArrbyInputStrebm(
                    bpbcheCbnonicblizer.cbnonicblizeXPbthNodeSet(nodeSet)));
            }
        } cbtch (Exception e) {
            throw new TrbnsformException(e);
        }
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

        if (ownerDoc == null) {
            throw new TrbnsformException("trbnsform must be mbrshblled");
        }

        if (bpbcheTrbnsform == null) {
            try {
                bpbcheTrbnsform =
                    new Trbnsform(ownerDoc, getAlgorithm(), trbnsformElem.getChildNodes());
                bpbcheTrbnsform.setElement(trbnsformElem, xc.getBbseURI());
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Crebted trbnsform for blgorithm: " + getAlgorithm());
                }
            } cbtch (Exception ex) {
                throw new TrbnsformException
                    ("Couldn't find Trbnsform for: " + getAlgorithm(), ex);
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
            in = bpbcheTrbnsform.performTrbnsform(in, os);
            if (!in.isNodeSet() && !in.isElement()) {
                return null;
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
