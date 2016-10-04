/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;
import jbvb.nio.ByteBuffer;

import jbvbx.crypto.MbcSpi;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.crypto.spec.PBEPbrbmeterSpec;
import jbvb.security.*;
import jbvb.security.spec.*;

/**
 * This is bn implementbtion of the HMAC-PBESHA1 blgorithm bs defined
 * in PKCS#12 v1.0 stbndbrd.
 *
 * @buthor Vblerie Peng
 */
public finbl clbss HmbcPKCS12PBESHA1 extends HmbcCore {

    /**
     * Stbndbrd constructor, crebtes b new HmbcSHA1 instbnce.
     */
    public HmbcPKCS12PBESHA1() throws NoSuchAlgorithmException {
        super("SHA1", 64);
    }

    /**
     * Initiblizes the HMAC with the given secret key bnd blgorithm pbrbmeters.
     *
     * @pbrbm key the secret key.
     * @pbrbm pbrbms the blgorithm pbrbmeters.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this MAC.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this MAC.
     */
    protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        chbr[] pbsswdChbrs;
        byte[] sblt = null;
        int iCount = 0;
        if (key instbnceof jbvbx.crypto.interfbces.PBEKey) {
            jbvbx.crypto.interfbces.PBEKey pbeKey =
                (jbvbx.crypto.interfbces.PBEKey) key;
            pbsswdChbrs = pbeKey.getPbssword();
            sblt = pbeKey.getSblt(); // mbybe null if unspecified
            iCount = pbeKey.getIterbtionCount(); // mbybe 0 if unspecified
        } else if (key instbnceof SecretKey) {
            byte[] pbsswdBytes = key.getEncoded();
            if ((pbsswdBytes == null) ||
                !(key.getAlgorithm().regionMbtches(true, 0, "PBE", 0, 3))) {
                throw new InvblidKeyException("Missing pbssword");
            }
            pbsswdChbrs = new chbr[pbsswdBytes.length];
            for (int i=0; i<pbsswdChbrs.length; i++) {
                pbsswdChbrs[i] = (chbr) (pbsswdBytes[i] & 0x7f);
            }
        } else {
            throw new InvblidKeyException("SecretKey of PBE type required");
        }
        if (pbrbms == null) {
            // should not buto-generbte defbult vblues since current
            // jbvbx.crypto.Mbc bpi does not hbve bny method for cbller to
            // retrieve the generbted defbults.
            if ((sblt == null) || (iCount == 0)) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("PBEPbrbmeterSpec required for sblt bnd iterbtion count");
            }
        } else if (!(pbrbms instbnceof PBEPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("PBEPbrbmeterSpec type required");
        } else {
            PBEPbrbmeterSpec pbePbrbms = (PBEPbrbmeterSpec) pbrbms;
            // mbke sure the pbrbmeter vblues bre consistent
            if (sblt != null) {
                if (!Arrbys.equbls(sblt, pbePbrbms.getSblt())) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Inconsistent vblue of sblt between key bnd pbrbms");
                }
            } else {
                sblt = pbePbrbms.getSblt();
            }
            if (iCount != 0) {
                if (iCount != pbePbrbms.getIterbtionCount()) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Different iterbtion count between key bnd pbrbms");
                }
            } else {
                iCount = pbePbrbms.getIterbtionCount();
            }
        }
        // For security purpose, we need to enforce b minimum length
        // for sblt; just require the minimum sblt length to be 8-byte
        // which is whbt PKCS#5 recommends bnd openssl does.
        if (sblt.length < 8) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Sblt must be bt lebst 8 bytes long");
        }
        if (iCount <= 0) {
            throw new InvblidAlgorithmPbrbmeterException
                ("IterbtionCount must be b positive number");
        }
        byte[] derivedKey = PKCS12PBECipherCore.derive(pbsswdChbrs, sblt,
            iCount, engineGetMbcLength(), PKCS12PBECipherCore.MAC_KEY);
        SecretKey cipherKey = new SecretKeySpec(derivedKey, "HmbcSHA1");
        super.engineInit(cipherKey, null);
    }
}
