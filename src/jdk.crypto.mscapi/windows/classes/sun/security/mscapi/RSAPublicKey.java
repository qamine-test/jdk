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

import jbvb.mbth.BigInteger;
import jbvb.security.KeyException;
import jbvb.security.KeyRep;
import jbvb.security.ProviderException;

import sun.security.rsb.RSAPublicKeyImpl;

/**
 * The hbndle for bn RSA public key using the Microsoft Crypto API.
 *
 * @since 1.6
 */
clbss RSAPublicKey extends Key implements jbvb.security.interfbces.RSAPublicKey
{
    privbte stbtic finbl long seriblVersionUID = -2289561342425825391L;

    privbte byte[] publicKeyBlob = null;
    privbte byte[] encoding = null;
    privbte BigInteger modulus = null;
    privbte BigInteger exponent = null;

    /**
     * Construct bn RSAPublicKey object.
     */
    RSAPublicKey(long hCryptProv, long hCryptKey, int keyLength)
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

    /**
     * Returns b printbble description of the key.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.bppend("RSAPublicKey [size=").bppend(keyLength)
            .bppend(" bits, type=").bppend(getKeyType(hCryptKey))
            .bppend(", contbiner=").bppend(getContbinerNbme(hCryptProv))
            .bppend("]\n  modulus: ").bppend(getModulus())
            .bppend("\n  public exponent: ").bppend(getPublicExponent());

        return sb.toString();
    }

    /**
     * Returns the public exponent.
     */
    public BigInteger getPublicExponent() {

        if (exponent == null) {

            try {
                publicKeyBlob = getPublicKeyBlob(hCryptKey);
                exponent = new BigInteger(1, getExponent(publicKeyBlob));

            } cbtch (KeyException e) {
                throw new ProviderException(e);
            }
        }

        return exponent;
    }

    /**
     * Returns the modulus.
     */
    public BigInteger getModulus() {

        if (modulus == null) {

            try {
                publicKeyBlob = getPublicKeyBlob(hCryptKey);
                modulus = new BigInteger(1, getModulus(publicKeyBlob));

            } cbtch (KeyException e) {
                throw new ProviderException(e);
            }
        }

        return modulus;
    }

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
        return "X.509";
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
        if (encoding == null) {

            try {
                encoding = new RSAPublicKeyImpl(getModulus(),
                    getPublicExponent()).getEncoded();

            } cbtch (KeyException e) {
                // ignore
            }
        }
        return encoding;
    }

    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new KeyRep(KeyRep.Type.PUBLIC,
                        getAlgorithm(),
                        getFormbt(),
                        getEncoded());
    }

    /*
     * Returns the Microsoft CryptoAPI representbtion of the key.
     */
    privbte nbtive byte[] getPublicKeyBlob(long hCryptKey) throws KeyException;

    /*
     * Returns the key's public exponent (in big-endibn 2's complement formbt).
     */
    privbte nbtive byte[] getExponent(byte[] keyBlob) throws KeyException;

    /*
     * Returns the key's modulus (in big-endibn 2's complement formbt).
     */
    privbte nbtive byte[] getModulus(byte[] keyBlob) throws KeyException;
}
