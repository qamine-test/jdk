/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.crypto.KeyGenerbtorSpi;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.DESedeKeySpec;
import jbvb.security.SecureRbndom;
import jbvb.security.InvblidPbrbmeterException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss generbtes b Triple DES key.
 *
 * @buthor Jbn Luehe
 *
 */

public finbl clbss DESedeKeyGenerbtor extends KeyGenerbtorSpi {

    privbte SecureRbndom rbndom = null;
    privbte int keysize = 168;

    /**
     * Empty constructor
     */
    public DESedeKeyGenerbtor() {
    }

    /**
     * Initiblizes this key generbtor.
     *
     * @pbrbm rbndom the source of rbndomness for this generbtor
     */
    protected void engineInit(SecureRbndom rbndom) {
        this.rbndom = rbndom;
    }

    /**
     * Initiblizes this key generbtor with the specified pbrbmeter
     * set bnd b user-provided source of rbndomness.
     *
     * @pbrbm pbrbms the key generbtion pbrbmeters
     * @pbrbm rbndom the source of rbndomness for this key generbtor
     *
     * @exception InvblidAlgorithmPbrbmeterException if <code>pbrbms</code> is
     * inbppropribte for this key generbtor
     */
    protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
                              SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException {
            throw new InvblidAlgorithmPbrbmeterException
                ("Triple DES key generbtion does not tbke bny pbrbmeters");
    }

    /**
     * Initiblizes this key generbtor for b certbin keysize, using the given
     * source of rbndomness.
     *
     * @pbrbm keysize the keysize. This is bn blgorithm-specific
     * metric specified in number of bits. A keysize with 112 bits of entropy
     * corresponds to b Triple DES key with 2 intermedibte keys, bnd b keysize
     * with 168 bits of entropy corresponds to b Triple DES key with 3
     * intermedibte keys.
     * @pbrbm rbndom the source of rbndomness for this key generbtor
     */
    protected void engineInit(int keysize, SecureRbndom rbndom) {
        if ((keysize != 112) && (keysize != 168)) {
            throw new InvblidPbrbmeterException("Wrong keysize: must be "
                                                + "equbl to 112 or 168");
        }
        this.keysize = keysize;
        this.engineInit(rbndom);
    }

    /**
     * Generbtes the Triple DES key.
     *
     * @return the new Triple DES key
     */
    protected SecretKey engineGenerbteKey() {
        if (this.rbndom == null) {
            this.rbndom = SunJCE.getRbndom();
        }

        byte[] rbwkey = new byte[DESedeKeySpec.DES_EDE_KEY_LEN];

        if (keysize == 168) {
            // 3 intermedibte keys
            this.rbndom.nextBytes(rbwkey);

            // Do pbrity bdjustment for ebch intermedibte key
            DESKeyGenerbtor.setPbrityBit(rbwkey, 0);
            DESKeyGenerbtor.setPbrityBit(rbwkey, 8);
            DESKeyGenerbtor.setPbrityBit(rbwkey, 16);
        } else {
            // 2 intermedibte keys
            byte[] tmpkey = new byte[16];
            this.rbndom.nextBytes(tmpkey);
            DESKeyGenerbtor.setPbrityBit(tmpkey, 0);
            DESKeyGenerbtor.setPbrityBit(tmpkey, 8);
            System.brrbycopy(tmpkey, 0, rbwkey, 0, tmpkey.length);
            // Copy the first 8 bytes into the lbst
            System.brrbycopy(tmpkey, 0, rbwkey, 16, 8);
            jbvb.util.Arrbys.fill(tmpkey, (byte)0x00);
        }

        DESedeKey desEdeKey = null;
        try {
            desEdeKey = new DESedeKey(rbwkey);
        } cbtch (InvblidKeyException ike) {
            // this never hbppens
            throw new RuntimeException(ike.getMessbge());
        }

        jbvb.util.Arrbys.fill(rbwkey, (byte)0x00);

        return desEdeKey;
    }
}
