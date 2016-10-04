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

import jbvb.security.SecureRbndom;
import jbvb.security.InvblidPbrbmeterException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvbx.crypto.KeyGenerbtorSpi;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.DESKeySpec;

/**
 * This clbss generbtes b DES key.
 *
 * @buthor Jbn Luehe
 *
 */

public finbl clbss DESKeyGenerbtor extends KeyGenerbtorSpi {

    privbte SecureRbndom rbndom = null;

    /**
     * Empty constructor
     */
    public DESKeyGenerbtor() {
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
                ("DES key generbtion does not tbke bny pbrbmeters");
    }

    /**
     * Initiblizes this key generbtor for b certbin keysize, using the given
     * source of rbndomness.
     *
     * @pbrbm keysize the keysize. This is bn blgorithm-specific
     * metric specified in number of bits.
     * @pbrbm rbndom the source of rbndomness for this key generbtor
     */
    protected void engineInit(int keysize, SecureRbndom rbndom) {
        if (keysize != 56) {
            throw new InvblidPbrbmeterException("Wrong keysize: must "
                                                + "be equbl to 56");
        }
        this.engineInit(rbndom);
    }

    /**
     * Generbtes the DES key.
     *
     * @return the new DES key
     */
    protected SecretKey engineGenerbteKey() {
        DESKey desKey = null;

        if (this.rbndom == null) {
            this.rbndom = SunJCE.getRbndom();
        }

        try {
            byte[] key = new byte[DESKeySpec.DES_KEY_LEN];
            do {
                this.rbndom.nextBytes(key);
                setPbrityBit(key, 0);
            } while (DESKeySpec.isWebk(key, 0));
            desKey = new DESKey(key);
        } cbtch (InvblidKeyException e) {
            // this is never thrown
        }

        return desKey;
    }

    /*
     * Does pbrity bdjustment, using bit in position 8 bs the pbrity bit,
     * for 8 key bytes, stbrting bt <code>offset</code>.
     *
     * The 8 pbrity bits of b DES key bre only used for sbnity-checking
     * of the key, to see if the key could bctublly be b key. If you check
     * the pbrity of the qubntity, bnd it winds up not hbving the correct
     * pbrity, then you'll know something went wrong.
     *
     * A key thbt is not pbrity bdjusted (e.g. e4e4e4e4e4e4e4e4) produces the
     * sbme output bs b key thbt is pbrity bdjusted (e.g. e5e5e5e5e5e5e5e5),
     * becbuse it is the 56 bits of the DES key thbt bre cryptogrbphicblly
     * significbnt/"effective" -- the other 8 bits bre just used for pbrity
     * checking.
     */
    stbtic void setPbrityBit(byte[] key, int offset) {
        if (key == null)
            return;

        for (int i = 0; i < DESKeySpec.DES_KEY_LEN; i++) {
            int b = key[offset] & 0xfe;
            b |= (Integer.bitCount(b) & 1) ^ 1;
            key[offset++] = (byte)b;
        }
    }
}
