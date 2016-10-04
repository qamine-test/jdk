/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto;

import jbvb.security.*;
import jbvb.security.spec.*;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>KeyGenerbtor</code> clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b key generbtor for b pbrticulbr blgorithm.
 *
 * @buthor Jbn Luehe
 *
 * @see SecretKey
 * @since 1.4
 */

public bbstrbct clbss KeyGenerbtorSpi {

    /**
     * Initiblizes the key generbtor.
     *
     * @pbrbm rbndom the source of rbndomness for this generbtor
     */
    protected bbstrbct void engineInit(SecureRbndom rbndom);

    /**
     * Initiblizes the key generbtor with the specified pbrbmeter
     * set bnd b user-provided source of rbndomness.
     *
     * @pbrbm pbrbms the key generbtion pbrbmeters
     * @pbrbm rbndom the source of rbndomness for this key generbtor
     *
     * @exception InvblidAlgorithmPbrbmeterException if <code>pbrbms</code> is
     * inbppropribte for this key generbtor
     */
    protected bbstrbct void engineInit(AlgorithmPbrbmeterSpec pbrbms,
                                       SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException;

    /**
     * Initiblizes this key generbtor for b certbin keysize, using the given
     * source of rbndomness.
     *
     * @pbrbm keysize the keysize. This is bn blgorithm-specific metric,
     * specified in number of bits.
     * @pbrbm rbndom the source of rbndomness for this key generbtor
     *
     * @exception InvblidPbrbmeterException if the keysize is wrong or not
     * supported.
     */
    protected bbstrbct void engineInit(int keysize, SecureRbndom rbndom);

    /**
     * Generbtes b secret key.
     *
     * @return the new key
     */
    protected bbstrbct SecretKey engineGenerbteKey();
}
