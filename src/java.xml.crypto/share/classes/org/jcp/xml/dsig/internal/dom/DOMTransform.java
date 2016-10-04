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
 * $Id: DOMTrbnsform.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.OutputStrebm;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.Provider;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.dom.DOMSignContext;

/**
 * DOM-bbsed bbstrbct implementbtion of Trbnsform.
 *
 * @buthor Sebn Mullbn
 */
public clbss DOMTrbnsform extends DOMStructure implements Trbnsform {

    protected TrbnsformService spi;

    /**
     * Crebtes b <code>DOMTrbnsform</code>.
     *
     * @pbrbm spi the TrbnsformService
     */
    public DOMTrbnsform(TrbnsformService spi) {
        this.spi = spi;
    }

    /**
     * Crebtes b <code>DOMTrbnsform</code> from bn element. This constructor
     * invokes the bbstrbct {@link #unmbrshblPbrbms unmbrshblPbrbms} method to
     * unmbrshbl bny blgorithm-specific input pbrbmeters.
     *
     * @pbrbm trbnsElem b Trbnsform element
     */
    public DOMTrbnsform(Element trbnsElem, XMLCryptoContext context,
                        Provider provider)
        throws MbrshblException
    {
        String blgorithm = DOMUtils.getAttributeVblue(trbnsElem, "Algorithm");

        if (provider == null) {
            try {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
            } cbtch (NoSuchAlgorithmException e1) {
                throw new MbrshblException(e1);
            }
        } else {
            try {
                spi = TrbnsformService.getInstbnce(blgorithm, "DOM", provider);
            } cbtch (NoSuchAlgorithmException nsbe) {
                try {
                    spi = TrbnsformService.getInstbnce(blgorithm, "DOM");
                } cbtch (NoSuchAlgorithmException e2) {
                    throw new MbrshblException(e2);
                }
            }
        }
        try {
            spi.init(new jbvbx.xml.crypto.dom.DOMStructure(trbnsElem), context);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new MbrshblException(ibpe);
        }
    }

    public finbl AlgorithmPbrbmeterSpec getPbrbmeterSpec() {
        return spi.getPbrbmeterSpec();
    }

    public finbl String getAlgorithm() {
        return spi.getAlgorithm();
    }

    /**
     * This method invokes the bbstrbct {@link #mbrshblPbrbms mbrshblPbrbms}
     * method to mbrshbl bny blgorithm-specific pbrbmeters.
     */
    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        Element trbnsformElem = null;
        if (pbrent.getLocblNbme().equbls("Trbnsforms")) {
            trbnsformElem = DOMUtils.crebteElement(ownerDoc, "Trbnsform",
                                                   XMLSignbture.XMLNS,
                                                   dsPrefix);
        } else {
            trbnsformElem = DOMUtils.crebteElement(ownerDoc,
                                                   "CbnonicblizbtionMethod",
                                                   XMLSignbture.XMLNS,
                                                   dsPrefix);
        }
        DOMUtils.setAttribute(trbnsformElem, "Algorithm", getAlgorithm());

        spi.mbrshblPbrbms(new jbvbx.xml.crypto.dom.DOMStructure(trbnsformElem),
                          context);

        pbrent.bppendChild(trbnsformElem);
    }

    /**
     * Trbnsforms the specified dbtb using the underlying trbnsform blgorithm.
     *
     * @pbrbm dbtb the dbtb to be trbnsformed
     * @pbrbm sc the <code>XMLCryptoContext</code> contbining
     *    bdditionbl context (mby be <code>null</code> if not bpplicbble)
     * @return the trbnsformed dbtb
     * @throws NullPointerException if <code>dbtb</code> is <code>null</code>
     * @throws XMLSignbtureException if bn unexpected error occurs while
     *    executing the trbnsform
     */
    public Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc)
        throws TrbnsformException
    {
        return spi.trbnsform(dbtb, xc);
    }

    /**
     * Trbnsforms the specified dbtb using the underlying trbnsform blgorithm.
     *
     * @pbrbm dbtb the dbtb to be trbnsformed
     * @pbrbm sc the <code>XMLCryptoContext</code> contbining
     *    bdditionbl context (mby be <code>null</code> if not bpplicbble)
     * @pbrbm os the <code>OutputStrebm</code> thbt should be used to write
     *    the trbnsformed dbtb to
     * @return the trbnsformed dbtb
     * @throws NullPointerException if <code>dbtb</code> is <code>null</code>
     * @throws XMLSignbtureException if bn unexpected error occurs while
     *    executing the trbnsform
     */
    public Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc, OutputStrebm os)
        throws TrbnsformException
    {
        return spi.trbnsform(dbtb, xc, os);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof Trbnsform)) {
            return fblse;
        }
        Trbnsform otrbnsform = (Trbnsform)o;

        return (getAlgorithm().equbls(otrbnsform.getAlgorithm()) &&
                DOMUtils.pbrbmsEqubl(getPbrbmeterSpec(),
                                     otrbnsform.getPbrbmeterSpec()));
    }

    @Override
    public int hbshCode() {
        int result = 17;
        result = 31 * result + getAlgorithm().hbshCode();
        AlgorithmPbrbmeterSpec spec = getPbrbmeterSpec();
        if (spec != null) {
            result = 31 * result + spec.hbshCode();
        }

        return result;
    }

    /**
     * Trbnsforms the specified dbtb using the underlying trbnsform blgorithm.
     * This method invokes the {@link #mbrshbl mbrshbl} method bnd pbsses it
     * the specified <code>DOMSignContext</code> before trbnsforming the dbtb.
     *
     * @pbrbm dbtb the dbtb to be trbnsformed
     * @pbrbm sc the <code>XMLCryptoContext</code> contbining
     *    bdditionbl context (mby be <code>null</code> if not bpplicbble)
     * @pbrbm context the mbrshblling context
     * @return the trbnsformed dbtb
     * @throws MbrshblException if bn exception occurs while mbrshblling
     * @throws NullPointerException if <code>dbtb</code> or <code>context</code>
     *    is <code>null</code>
     * @throws XMLSignbtureException if bn unexpected error occurs while
     *    executing the trbnsform
     */
    Dbtb trbnsform(Dbtb dbtb, XMLCryptoContext xc, DOMSignContext context)
        throws MbrshblException, TrbnsformException
    {
        mbrshbl(context.getPbrent(),
                DOMUtils.getSignbturePrefix(context), context);
        return trbnsform(dbtb, xc);
    }
}
