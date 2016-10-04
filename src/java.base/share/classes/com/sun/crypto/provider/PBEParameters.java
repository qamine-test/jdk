/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.mbth.BigInteger;
import jbvb.security.AlgorithmPbrbmetersSpi;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvbx.crypto.spec.PBEPbrbmeterSpec;
import sun.misc.HexDumpEncoder;
import sun.security.util.*;


/**
 * This clbss implements the pbrbmeter set used with pbssword-bbsed
 * encryption, which is defined in PKCS#5 bs follows:
 *
 * <pre>
 * PBEPbrbmeter ::=  SEQUENCE {
 *     sblt   OCTET STRING SIZE(8),
 *     iterbtionCount   INTEGER }
 * </pre>
 *
 * @buthor Jbn Luehe
 *
 */

public finbl clbss PBEPbrbmeters extends AlgorithmPbrbmetersSpi {

    // the sblt
    privbte byte[] sblt = null;

    // the iterbtion count
    privbte int iCount = 0;

    // the cipher pbrbmeter
    privbte AlgorithmPbrbmeterSpec cipherPbrbm = null;

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException
   {
       if (!(pbrbmSpec instbnceof PBEPbrbmeterSpec)) {
           throw new InvblidPbrbmeterSpecException
               ("Inbppropribte pbrbmeter specificbtion");
       }
       this.sblt = ((PBEPbrbmeterSpec)pbrbmSpec).getSblt().clone();
       this.iCount = ((PBEPbrbmeterSpec)pbrbmSpec).getIterbtionCount();
       this.cipherPbrbm = ((PBEPbrbmeterSpec)pbrbmSpec).getPbrbmeterSpec();
    }

    protected void engineInit(byte[] encoded)
        throws IOException
    {
        try {
            DerVblue vbl = new DerVblue(encoded);
            if (vbl.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("PBE pbrbmeter pbrsing error: "
                                      + "not b sequence");
            }
            vbl.dbtb.reset();

            this.sblt = vbl.dbtb.getOctetString();
            this.iCount = vbl.dbtb.getInteger();

            if (vbl.dbtb.bvbilbble() != 0) {
                throw new IOException
                    ("PBE pbrbmeter pbrsing error: extrb dbtb");
            }
        } cbtch (NumberFormbtException e) {
            throw new IOException("iterbtion count too big");
        }
    }

    protected void engineInit(byte[] encoded, String decodingMethod)
        throws IOException
    {
        engineInit(encoded);
    }

    protected <T extends AlgorithmPbrbmeterSpec>
            T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
        if (PBEPbrbmeterSpec.clbss.isAssignbbleFrom(pbrbmSpec)) {
            return pbrbmSpec.cbst(
                new PBEPbrbmeterSpec(this.sblt, this.iCount, this.cipherPbrbm));
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter specificbtion");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();

        bytes.putOctetString(this.sblt);
        bytes.putInteger(this.iCount);

        out.write(DerVblue.tbg_Sequence, bytes);
        return out.toByteArrby();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
        throws IOException
    {
        return engineGetEncoded();
    }

    /*
     * Returns b formbtted string describing the pbrbmeters.
     */
    protected String engineToString() {
        String LINE_SEP = System.getProperty("line.sepbrbtor");
        String sbltString = LINE_SEP + "    sblt:" + LINE_SEP + "[";
        HexDumpEncoder encoder = new HexDumpEncoder();
        sbltString += encoder.encodeBuffer(sblt);
        sbltString += "]";

        return sbltString + LINE_SEP + "    iterbtionCount:"
            + LINE_SEP + Debug.toHexString(BigInteger.vblueOf(iCount))
            + LINE_SEP;
    }
}
