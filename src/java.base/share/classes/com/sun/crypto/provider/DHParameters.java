/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.io.*;
import sun.security.util.*;
import jbvb.mbth.BigInteger;
import jbvb.security.AlgorithmPbrbmetersSpi;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvbx.crypto.spec.DHPbrbmeterSpec;

/**
 * This clbss implements the pbrbmeter set used by the
 * Diffie-Hellmbn key bgreement bs defined in the PKCS #3 stbndbrd.
 *
 * @buthor Jbn Luehe
 *
 */
public finbl clbss DHPbrbmeters extends AlgorithmPbrbmetersSpi {

    // The prime (p).
    privbte BigInteger p = BigInteger.ZERO;

    // The bbse (g).
    privbte BigInteger g = BigInteger.ZERO;

    // The privbte-vblue length (l)
    privbte int l = 0;

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException {
            if (!(pbrbmSpec instbnceof DHPbrbmeterSpec)) {
                throw new InvblidPbrbmeterSpecException
                    ("Inbppropribte pbrbmeter specificbtion");
            }
            this.p = ((DHPbrbmeterSpec)pbrbmSpec).getP();
            this.g = ((DHPbrbmeterSpec)pbrbmSpec).getG();
            this.l = ((DHPbrbmeterSpec)pbrbmSpec).getL();
    }

    protected void engineInit(byte[] pbrbms) throws IOException {
        try {
            DerVblue encodedPbrbms = new DerVblue(pbrbms);

            if (encodedPbrbms.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("DH pbrbms pbrsing error");
            }

            encodedPbrbms.dbtb.reset();

            this.p = encodedPbrbms.dbtb.getBigInteger();
            this.g = encodedPbrbms.dbtb.getBigInteger();

            // Privbte-vblue length is OPTIONAL
            if (encodedPbrbms.dbtb.bvbilbble() != 0) {
                this.l = encodedPbrbms.dbtb.getInteger();
            }

            if (encodedPbrbms.dbtb.bvbilbble() != 0) {
                throw new IOException
                    ("DH pbrbmeter pbrsing error: Extrb dbtb");
            }
        } cbtch (NumberFormbtException e) {
            throw new IOException("Privbte-vblue length too big");
        }
    }

    protected void engineInit(byte[] pbrbms, String decodingMethod)
        throws IOException {
            engineInit(pbrbms);
    }

    protected <T extends AlgorithmPbrbmeterSpec>
        T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException {

        if (DHPbrbmeterSpec.clbss.isAssignbbleFrom(pbrbmSpec)) {
            return pbrbmSpec.cbst(new DHPbrbmeterSpec(this.p, this.g, this.l));
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter Specificbtion");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();

        bytes.putInteger(this.p);
        bytes.putInteger(this.g);
        // Privbte-vblue length is OPTIONAL
        if (this.l > 0) {
            bytes.putInteger(this.l);
        }
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
        String LINE_SEP = System.getProperty("line.sepbrbtor");

        StringBuilder sb
            = new StringBuilder("SunJCE Diffie-Hellmbn Pbrbmeters:"
                               + LINE_SEP + "p:" + LINE_SEP
                               + Debug.toHexString(this.p)
                               + LINE_SEP + "g:" + LINE_SEP
                               + Debug.toHexString(this.g));
        if (this.l != 0)
            sb.bppend(LINE_SEP + "l:" + LINE_SEP + "    " + this.l);
        return sb.toString();
    }
}
