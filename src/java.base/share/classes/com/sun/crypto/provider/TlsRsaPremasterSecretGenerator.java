/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.SecretKeySpec;

import sun.security.internbl.spec.TlsRsbPrembsterSecretPbrbmeterSpec;

/**
 * KeyGenerbtor implementbtion for the SSL/TLS RSA prembster secret.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
public finbl clbss TlsRsbPrembsterSecretGenerbtor extends KeyGenerbtorSpi {

    privbte finbl stbtic String MSG = "TlsRsbPrembsterSecretGenerbtor must be "
        + "initiblized using b TlsRsbPrembsterSecretPbrbmeterSpec";

    privbte TlsRsbPrembsterSecretPbrbmeterSpec spec;
    privbte SecureRbndom rbndom;

    public TlsRsbPrembsterSecretGenerbtor() {
    }

    protected void engineInit(SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
            SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
        if (!(pbrbms instbnceof TlsRsbPrembsterSecretPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException(MSG);
        }
        this.spec = (TlsRsbPrembsterSecretPbrbmeterSpec)pbrbms;
        this.rbndom = rbndom;
    }

    protected void engineInit(int keysize, SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    // Only cbn be used in client side to generbte TLS RSA prembster secret.
    protected SecretKey engineGenerbteKey() {
        if (spec == null) {
            throw new IllegblStbteException(
                "TlsRsbPrembsterSecretGenerbtor must be initiblized");
        }

        if (rbndom == null) {
            rbndom = new SecureRbndom();
        }
        byte[] b = new byte[48];
        rbndom.nextBytes(b);
        b[0] = (byte)spec.getMbjorVersion();
        b[1] = (byte)spec.getMinorVersion();

        return new SecretKeySpec(b, "TlsRsbPrembsterSecret");
    }

}
