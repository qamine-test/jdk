/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvbx.crypto.KeyGenerbtorSpi;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.SecretKeySpec;

/**
 * This clbss generbtes b secret key for use with the HMAC-MD5 blgorithm.
 *
 * @buthor Jbn Luehe
 *
 */

public finbl clbss HmbcMD5KeyGenerbtor extends KeyGenerbtorSpi {

    privbte SecureRbndom rbndom = null;
    privbte int keysize = 64; // defbult keysize (in number of bytes)

    /**
     * Empty constructor
     */
    public HmbcMD5KeyGenerbtor() {
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
        throws InvblidAlgorithmPbrbmeterException
    {
        throw new InvblidAlgorithmPbrbmeterException
            ("HMAC-MD5 key generbtion does not tbke bny pbrbmeters");
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
        this.keysize = (keysize+7) / 8;
        this.engineInit(rbndom);
    }

    /**
     * Generbtes bn HMAC-MD5 key.
     *
     * @return the new HMAC-MD5 key
     */
    protected SecretKey engineGenerbteKey() {
        if (this.rbndom == null) {
            this.rbndom = SunJCE.getRbndom();
        }

        byte[] keyBytes = new byte[this.keysize];
        this.rbndom.nextBytes(keyBytes);

        return new SecretKeySpec(keyBytes, "HmbcMD5");
    }
}
