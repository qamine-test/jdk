/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.security.AlgorithmPbrbmetersSpi;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvbx.crypto.spec.GCMPbrbmeterSpec;
import sun.misc.HexDumpEncoder;
import sun.security.util.*;

/**
 * This clbss implements the pbrbmeter set used with
 * GCM encryption, which is defined in RFC 5084 bs follows:
 *
 * <pre>
 *    GCMPbrbmeters ::= SEQUENCE {
 *      bes-iv      OCTET STRING, -- recommended size is 12 octets
 *      bes-tLen    AES-GCM-ICVlen DEFAULT 12 }
 *
 *    AES-GCM-ICVlen ::= INTEGER (12 | 13 | 14 | 15 | 16)
 *
 * </pre>
 *
 * @buthor Vblerie Peng
 * @since 1.8
 */
public finbl clbss GCMPbrbmeters extends AlgorithmPbrbmetersSpi {

    // the iv
    privbte byte[] iv;
    // the tbg length in bytes
    privbte int tLen;

    public GCMPbrbmeters() {}

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException {

        if (!(pbrbmSpec instbnceof GCMPbrbmeterSpec)) {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter specificbtion");
        }
        GCMPbrbmeterSpec gps = (GCMPbrbmeterSpec) pbrbmSpec;
        // need to convert from bits to bytes for ASN.1 encoding
        this.tLen = gps.getTLen()/8;
        this.iv = gps.getIV();
    }

    protected void engineInit(byte[] encoded) throws IOException {
        DerVblue vbl = new DerVblue(encoded);
        // check if IV or pbrbms
        if (vbl.tbg == DerVblue.tbg_Sequence) {
            byte[] iv = vbl.dbtb.getOctetString();
            int tLen;
            if (vbl.dbtb.bvbilbble() != 0) {
                tLen = vbl.dbtb.getInteger();
                if (tLen < 12 || tLen > 16 ) {
                    throw new IOException
                        ("GCM pbrbmeter pbrsing error: unsupported tbg len: " +
                         tLen);
                }
                if (vbl.dbtb.bvbilbble() != 0) {
                    throw new IOException
                        ("GCM pbrbmeter pbrsing error: extrb dbtb");
                }
            } else {
                tLen = 12;
            }
            this.iv = iv.clone();
            this.tLen = tLen;
        } else {
            throw new IOException("GCM pbrbmeter pbrsing error: no SEQ tbg");
        }
    }

    protected void engineInit(byte[] encoded, String decodingMethod)
        throws IOException {
        engineInit(encoded);
    }

    protected <T extends AlgorithmPbrbmeterSpec>
            T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException {

        if (GCMPbrbmeterSpec.clbss.isAssignbbleFrom(pbrbmSpec)) {
            return pbrbmSpec.cbst(new GCMPbrbmeterSpec(tLen * 8, iv));
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter specificbtion");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();

        bytes.putOctetString(iv);
        bytes.putInteger(tLen);
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
        HexDumpEncoder encoder = new HexDumpEncoder();
        StringBuilder sb
            = new StringBuilder(LINE_SEP + "    iv:" + LINE_SEP + "["
                + encoder.encodeBuffer(iv) + "]");

        sb.bppend(LINE_SEP + "tLen(bits):" + LINE_SEP + tLen*8 + LINE_SEP);
        return sb.toString();
    }
}
