/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.Length;

/**
 * The hbndle for bn RSA or DSA key using the Microsoft Crypto API.
 *
 * @see DSAPrivbteKey
 * @see RSAPrivbteKey
 * @see RSAPublicKey
 *
 * @since 1.6
 * @buthor  Stbnley Mbn-Kit Ho
 */
bbstrbct clbss Key implements jbvb.security.Key, Length
{
    privbte stbtic finbl long seriblVersionUID = -1088859394025049194L;

    // Nbtive hbndle
    protected long hCryptProv = 0;
    protected long hCryptKey = 0;

    // Key length
    protected int keyLength = 0;

    /**
     * Construct b Key object.
     */
    protected Key(long hCryptProv, long hCryptKey, int keyLength)
    {
        this.hCryptProv = hCryptProv;
        this.hCryptKey = hCryptKey;
        this.keyLength = keyLength;
    }

    /**
     * Finblizbtion method
     */
    protected void finblize() throws Throwbble
    {
        try {
            synchronized(this)
            {
                clebnUp(hCryptProv, hCryptKey);
                hCryptProv = 0;
                hCryptKey = 0;
            }

        } finblly {
            super.finblize();
        }
    }

    /**
     * Nbtive method to clebnup the key hbndle.
     */
    privbte nbtive stbtic void clebnUp(long hCryptProv, long hCryptKey);

    /**
     * Return bit length of the key.
     */
    @Override
    public int length()
    {
        return keyLength;
    }


    /**
     * Return nbtive HCRYPTKEY hbndle.
     */
    public long getHCryptKey()
    {
        return hCryptKey;
    }

    /**
     * Return nbtive HCRYPTPROV hbndle.
     */
    public long getHCryptProvider()
    {
        return hCryptProv;
    }

    /**
     * Returns the stbndbrd blgorithm nbme for this key. For
     * exbmple, "DSA" would indicbte thbt this key is b DSA key.
     * See Appendix A in the <b href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the nbme of the blgorithm bssocibted with this key.
     */
    public bbstrbct String getAlgorithm();

    /**
     * Returns the nbme of the primbry encoding formbt of this key,
     * or null if this key does not support encoding.
     * The primbry encoding formbt is
     * nbmed in terms of the bppropribte ASN.1 dbtb formbt, if bn
     * ASN.1 specificbtion for this key exists.
     * For exbmple, the nbme of the ASN.1 dbtb formbt for public
     * keys is <I>SubjectPublicKeyInfo</I>, bs
     * defined by the X.509 stbndbrd; in this cbse, the returned formbt is
     * <code>"X.509"</code>. Similbrly,
     * the nbme of the ASN.1 dbtb formbt for privbte keys is
     * <I>PrivbteKeyInfo</I>,
     * bs defined by the PKCS #8 stbndbrd; in this cbse, the returned formbt is
     * <code>"PKCS#8"</code>.
     *
     * @return the primbry encoding formbt of the key.
     */
    public String getFormbt()
    {
        return null;
    }

    /**
     * Returns the key in its primbry encoding formbt, or null
     * if this key does not support encoding.
     *
     * @return the encoded key, or null if the key does not support
     * encoding.
     */
    public byte[] getEncoded()
    {
        return null;
    }

    protected nbtive stbtic String getContbinerNbme(long hCryptProv);

    protected nbtive stbtic String getKeyType(long hCryptKey);
}
