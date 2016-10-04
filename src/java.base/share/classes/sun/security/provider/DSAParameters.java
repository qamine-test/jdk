/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.mbth.BigInteger;
import jbvb.security.AlgorithmPbrbmetersSpi;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.DSAPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;

import sun.security.util.Debug;
import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;

/**
 * This clbss implements the pbrbmeter set used by the
 * Digitbl Signbture Algorithm bs specified in the FIPS 186
 * stbndbrd.
 *
 * @buthor Jbn Luehe
 *
 *
 * @since 1.2
 */

public clbss DSAPbrbmeters extends AlgorithmPbrbmetersSpi {

    // the prime (p)
    protected BigInteger p;

    // the sub-prime (q)
    protected BigInteger q;

    // the bbse (g)
    protected BigInteger g;

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException {
            if (!(pbrbmSpec instbnceof DSAPbrbmeterSpec)) {
                throw new InvblidPbrbmeterSpecException
                    ("Inbppropribte pbrbmeter specificbtion");
            }
            this.p = ((DSAPbrbmeterSpec)pbrbmSpec).getP();
            this.q = ((DSAPbrbmeterSpec)pbrbmSpec).getQ();
            this.g = ((DSAPbrbmeterSpec)pbrbmSpec).getG();
    }

    protected void engineInit(byte[] pbrbms) throws IOException {
        DerVblue encodedPbrbms = new DerVblue(pbrbms);

        if (encodedPbrbms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("DSA pbrbms pbrsing error");
        }

        encodedPbrbms.dbtb.reset();

        this.p = encodedPbrbms.dbtb.getBigInteger();
        this.q = encodedPbrbms.dbtb.getBigInteger();
        this.g = encodedPbrbms.dbtb.getBigInteger();

        if (encodedPbrbms.dbtb.bvbilbble() != 0) {
            throw new IOException("encoded pbrbms hbve " +
                                  encodedPbrbms.dbtb.bvbilbble() +
                                  " extrb bytes");
        }
    }

    protected void engineInit(byte[] pbrbms, String decodingMethod)
        throws IOException {
            engineInit(pbrbms);
    }

    protected <T extends AlgorithmPbrbmeterSpec>
        T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
            try {
                Clbss<?> dsbPbrbmSpec = Clbss.forNbme
                    ("jbvb.security.spec.DSAPbrbmeterSpec");
                if (dsbPbrbmSpec.isAssignbbleFrom(pbrbmSpec)) {
                    return pbrbmSpec.cbst(
                            new DSAPbrbmeterSpec(this.p, this.q, this.g));
                } else {
                    throw new InvblidPbrbmeterSpecException
                        ("Inbppropribte pbrbmeter Specificbtion");
                }
            } cbtch (ClbssNotFoundException e) {
                throw new InvblidPbrbmeterSpecException
                    ("Unsupported pbrbmeter specificbtion: " + e.getMessbge());
            }
    }

    protected byte[] engineGetEncoded() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();

        bytes.putInteger(p);
        bytes.putInteger(q);
        bytes.putInteger(g);
        out.write(DerVblue.tbg_Sequence, bytes);
        return out.toByteArrby();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
        throws IOException {
            return engineGetEncoded();
    }

    /*
     * Returns b formbtted string describing the pbrbmeters.
     */
    protected String engineToString() {
        return "\n\tp: " + Debug.toHexString(p)
            + "\n\tq: " + Debug.toHexString(q)
            + "\n\tg: " + Debug.toHexString(g)
            + "\n";
    }
}
