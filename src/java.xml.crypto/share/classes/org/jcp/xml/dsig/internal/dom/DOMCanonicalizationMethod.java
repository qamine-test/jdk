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
 * $Id: DOMCbnonicblizbtionMethod.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.io.OutputStrebm;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.Provider;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import org.w3c.dom.Element;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;

/**
 * DOM-bbsed bbstrbct implementbtion of CbnonicblizbtionMethod.
 *
 * @buthor Sebn Mullbn
 */
public clbss DOMCbnonicblizbtionMethod extends DOMTrbnsform
    implements CbnonicblizbtionMethod {

    /**
     * Crebtes b <code>DOMCbnonicblizbtionMethod</code>.
     *
     * @pbrbm spi TrbnsformService
     */
    public DOMCbnonicblizbtionMethod(TrbnsformService spi)
        throws InvblidAlgorithmPbrbmeterException
    {
        super(spi);
        if (!(spi instbnceof ApbcheCbnonicblizer) &&
                !isC14Nblg(spi.getAlgorithm())) {
            throw new InvblidAlgorithmPbrbmeterException(
                "Illegbl CbnonicblizbtionMethod");
        }
    }

    /**
     * Crebtes b <code>DOMCbnonicblizbtionMethod</code> from bn element. This
     * ctor invokes the bbstrbct {@link #unmbrshblPbrbms unmbrshblPbrbms}
     * method to unmbrshbl bny blgorithm-specific input pbrbmeters.
     *
     * @pbrbm cmElem b CbnonicblizbtionMethod element
     */
    public DOMCbnonicblizbtionMethod(Element cmElem, XMLCryptoContext context,
                                     Provider provider)
        throws MbrshblException
    {
        super(cmElem, context, provider);
        if (!(spi instbnceof ApbcheCbnonicblizer) &&
                !isC14Nblg(spi.getAlgorithm())) {
            throw new MbrshblException("Illegbl CbnonicblizbtionMethod");
        }
    }

    /**
     * Cbnonicblizes the specified dbtb using the underlying cbnonicblizbtion
     * blgorithm. This is b convenience method thbt is equivblent to invoking
     * the {@link #trbnsform trbnsform} method.
     *
     * @pbrbm dbtb the dbtb to be cbnonicblized
     * @pbrbm xc the <code>XMLCryptoContext</code> contbining
     *     bdditionbl context (mby be <code>null</code> if not bpplicbble)
     * @return the cbnonicblized dbtb
     * @throws NullPointerException if <code>dbtb</code> is <code>null</code>
     * @throws TrbnsformException if bn unexpected error occurs while
     *    cbnonicblizing the dbtb
     */
    public Dbtb cbnonicblize(Dbtb dbtb, XMLCryptoContext xc)
        throws TrbnsformException
    {
        return trbnsform(dbtb, xc);
    }

    public Dbtb cbnonicblize(Dbtb dbtb, XMLCryptoContext xc, OutputStrebm os)
        throws TrbnsformException
    {
        return trbnsform(dbtb, xc, os);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof CbnonicblizbtionMethod)) {
            return fblse;
        }
        CbnonicblizbtionMethod ocm = (CbnonicblizbtionMethod)o;

        return (getAlgorithm().equbls(ocm.getAlgorithm()) &&
            DOMUtils.pbrbmsEqubl(getPbrbmeterSpec(), ocm.getPbrbmeterSpec()));
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

    privbte stbtic boolebn isC14Nblg(String blg) {
        return (blg.equbls(CbnonicblizbtionMethod.INCLUSIVE) ||
                blg.equbls(CbnonicblizbtionMethod.INCLUSIVE_WITH_COMMENTS) ||
                blg.equbls(CbnonicblizbtionMethod.EXCLUSIVE) ||
                blg.equbls(CbnonicblizbtionMethod.EXCLUSIVE_WITH_COMMENTS) ||
                blg.equbls(DOMCbnonicblXMLC14N11Method.C14N_11) ||
                blg.equbls(DOMCbnonicblXMLC14N11Method.C14N_11_WITH_COMMENTS));
    }
}
