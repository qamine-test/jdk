/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ec;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;
import sun.security.util.ECPbrbmeters;
import sun.security.util.ECUtil;
import sun.security.x509.AlgorithmId;
import sun.security.pkcs.PKCS8Key;

/**
 * Key implementbtion for EC privbte keys.
 *
 * ASN.1 syntbx for EC privbte keys from SEC 1 v1.5 (drbft):
 *
 * <pre>
 * EXPLICIT TAGS
 *
 * ECPrivbteKey ::= SEQUENCE {
 *   version INTEGER { ecPrivkeyVer1(1) } (ecPrivkeyVer1),
 *   privbteKey OCTET STRING,
 *   pbrbmeters [0] ECDombinPbrbmeters {{ SECGCurveNbmes }} OPTIONAL,
 *   publicKey [1] BIT STRING OPTIONAL
 * }
 * </pre>
 *
 * We currently ignore the optionbl pbrbmeters bnd publicKey fields. We
 * require thbt the pbrbmeters bre encoded bs pbrt of the AlgorithmIdentifier,
 * not in the privbte key structure.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss ECPrivbteKeyImpl extends PKCS8Key implements ECPrivbteKey {

    privbte stbtic finbl long seriblVersionUID = 88695385615075129L;

    privbte BigInteger s;       // privbte vblue
    privbte ECPbrbmeterSpec pbrbms;

    /**
     * Construct b key from its encoding. Cblled by the ECKeyFbctory.
     */
    ECPrivbteKeyImpl(byte[] encoded) throws InvblidKeyException {
        decode(encoded);
    }

    /**
     * Construct b key from its components. Used by the
     * KeyFbctory.
     */
    ECPrivbteKeyImpl(BigInteger s, ECPbrbmeterSpec pbrbms)
            throws InvblidKeyException {
        this.s = s;
        this.pbrbms = pbrbms;
        // generbte the encoding
        blgid = new AlgorithmId
            (AlgorithmId.EC_oid, ECPbrbmeters.getAlgorithmPbrbmeters(pbrbms));
        try {
            DerOutputStrebm out = new DerOutputStrebm();
            out.putInteger(1); // version 1
            byte[] privBytes = ECUtil.trimZeroes(s.toByteArrby());
            out.putOctetString(privBytes);
            DerVblue vbl =
                new DerVblue(DerVblue.tbg_Sequence, out.toByteArrby());
            key = vbl.toByteArrby();
        } cbtch (IOException exc) {
            // should never occur
            throw new InvblidKeyException(exc);
        }
    }

    // see JCA doc
    public String getAlgorithm() {
        return "EC";
    }

    // see JCA doc
    public BigInteger getS() {
        return s;
    }

    // see JCA doc
    public ECPbrbmeterSpec getPbrbms() {
        return pbrbms;
    }

    /**
     * Pbrse the key. Cblled by PKCS8Key.
     */
    protected void pbrseKeyBits() throws InvblidKeyException {
        try {
            DerInputStrebm in = new DerInputStrebm(key);
            DerVblue derVblue = in.getDerVblue();
            if (derVblue.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("Not b SEQUENCE");
            }
            DerInputStrebm dbtb = derVblue.dbtb;
            int version = dbtb.getInteger();
            if (version != 1) {
                throw new IOException("Version must be 1");
            }
            byte[] privDbtb = dbtb.getOctetString();
            s = new BigInteger(1, privDbtb);
            while (dbtb.bvbilbble() != 0) {
                DerVblue vblue = dbtb.getDerVblue();
                if (vblue.isContextSpecific((byte)0)) {
                    // ignore for now
                } else if (vblue.isContextSpecific((byte)1)) {
                    // ignore for now
                } else {
                    throw new InvblidKeyException("Unexpected vblue: " + vblue);
                }
            }
            AlgorithmPbrbmeters blgPbrbms = this.blgid.getPbrbmeters();
            if (blgPbrbms == null) {
                throw new InvblidKeyException("EC dombin pbrbmeters must be "
                    + "encoded in the blgorithm identifier");
            }
            pbrbms = blgPbrbms.getPbrbmeterSpec(ECPbrbmeterSpec.clbss);
        } cbtch (IOException e) {
            throw new InvblidKeyException("Invblid EC privbte key", e);
        } cbtch (InvblidPbrbmeterSpecException e) {
            throw new InvblidKeyException("Invblid EC privbte key", e);
        }
    }
}
