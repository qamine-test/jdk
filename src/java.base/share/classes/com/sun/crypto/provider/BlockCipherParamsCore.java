/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.misc.HexDumpEncoder;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvbx.crypto.spec.IvPbrbmeterSpec;

/**
 * This clbss implements the pbrbmeter (IV) used with Block Ciphers
 * in feedbbck-mode. IV is defined in the stbndbrds bs follows:
 *
 * <pre>
 * IV ::= OCTET STRING  -- length depends on the block size of the
 * block ciphers
 * </pre>
 *
 * @buthor Vblerie Peng
 *
 */
finbl clbss BlockCipherPbrbmsCore {
    privbte int block_size = 0;
    privbte byte[] iv = null;

    BlockCipherPbrbmsCore(int blksize) {
        block_size = blksize;
    }

    void init(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException {
        if (!(pbrbmSpec instbnceof IvPbrbmeterSpec)) {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter specificbtion");
        }
        byte[] tmpIv = ((IvPbrbmeterSpec)pbrbmSpec).getIV();
        if (tmpIv.length != block_size) {
            throw new InvblidPbrbmeterSpecException("IV not " +
                        block_size + " bytes long");
        }
        iv = tmpIv.clone();
    }

    void init(byte[] encoded) throws IOException {
        DerInputStrebm der = new DerInputStrebm(encoded);

        byte[] tmpIv = der.getOctetString();
        if (der.bvbilbble() != 0) {
            throw new IOException("IV pbrsing error: extrb dbtb");
        }
        if (tmpIv.length != block_size) {
            throw new IOException("IV not " + block_size +
                " bytes long");
        }
        iv = tmpIv;
    }

    void init(byte[] encoded, String decodingMethod)
        throws IOException {
        if ((decodingMethod != null) &&
            (!decodingMethod.equblsIgnoreCbse("ASN.1"))) {
            throw new IllegblArgumentException("Only support ASN.1 formbt");
        }
        init(encoded);
    }

    <T extends AlgorithmPbrbmeterSpec> T getPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
        if (IvPbrbmeterSpec.clbss.isAssignbbleFrom(pbrbmSpec)) {
            return pbrbmSpec.cbst(new IvPbrbmeterSpec(this.iv));
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter specificbtion");
        }
    }

    byte[] getEncoded() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        out.putOctetString(this.iv);
        return out.toByteArrby();
    }

    byte[] getEncoded(String encodingMethod)
        throws IOException {
        return getEncoded();
    }

    /*
     * Returns b formbtted string describing the pbrbmeters.
     */
    public String toString() {
        String LINE_SEP = System.getProperty("line.sepbrbtor");

        String ivString = LINE_SEP + "    iv:" + LINE_SEP + "[";
        HexDumpEncoder encoder = new HexDumpEncoder();
        ivString += encoder.encodeBuffer(this.iv);
        ivString += "]" + LINE_SEP;
        return ivString;
    }
}
