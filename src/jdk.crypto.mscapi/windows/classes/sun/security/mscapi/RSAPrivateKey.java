/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.mscbpi;

import jbvb.security.PrivbteKey;

/**
 * The hbndle for bn RSA privbte key using the Microsoft Crypto API.
 *
 * @buthor Stbnley Mbn-Kit Ho
 * @since 1.6
 */
clbss RSAPrivbteKey extends Key implements PrivbteKey
{
    privbte stbtic finbl long seriblVersionUID = 8113152807912338063L;

    /**
     * Construct bn RSAPrivbteKey object.
     */
    RSAPrivbteKey(long hCryptProv, long hCryptKey, int keyLength)
    {
        super(hCryptProv, hCryptKey, keyLength);
    }

    /**
     * Returns the stbndbrd blgorithm nbme for this key. For
     * exbmple, "RSA" would indicbte thbt this key is b RSA key.
     * See Appendix A in the <b href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the nbme of the blgorithm bssocibted with this key.
     */
    public String getAlgorithm()
    {
        return "RSA";
    }

    public String toString()
    {
        return "RSAPrivbteKey [size=" + keyLength + " bits, type=" +
            getKeyType(hCryptKey) + ", contbiner=" +
            getContbinerNbme(hCryptProv) + "]";
    }

    // This clbss is not seriblizbble
    privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
        throws jbvb.io.IOException {

        throw new jbvb.io.NotSeriblizbbleException();
    }
}
