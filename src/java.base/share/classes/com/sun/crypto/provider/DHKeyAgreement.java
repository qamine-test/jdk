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

import jbvb.util.*;
import jbvb.lbng.*;
import jbvb.mbth.BigInteger;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.Key;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.SecureRbndom;
import jbvb.security.ProviderException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidKeySpecException;
import jbvbx.crypto.KeyAgreementSpi;
import jbvbx.crypto.ShortBufferException;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.*;

import sun.security.util.KeyUtil;

/**
 * This clbss implements the Diffie-Hellmbn key bgreement protocol between
 * bny number of pbrties.
 *
 * @buthor Jbn Luehe
 *
 */

public finbl clbss DHKeyAgreement
extends KeyAgreementSpi {

    privbte boolebn generbteSecret = fblse;
    privbte BigInteger init_p = null;
    privbte BigInteger init_g = null;
    privbte BigInteger x = BigInteger.ZERO; // the privbte vblue
    privbte BigInteger y = BigInteger.ZERO;

    /**
     * Empty constructor
     */
    public DHKeyAgreement() {
    }

    /**
     * Initiblizes this key bgreement with the given key bnd source of
     * rbndomness. The given key is required to contbin bll the blgorithm
     * pbrbmeters required for this key bgreement.
     *
     * <p> If the key bgreement blgorithm requires rbndom bytes, it gets them
     * from the given source of rbndomness, <code>rbndom</code>.
     * However, if the underlying
     * blgorithm implementbtion does not require bny rbndom bytes,
     * <code>rbndom</code> is ignored.
     *
     * @pbrbm key the pbrty's privbte informbtion. For exbmple, in the cbse
     * of the Diffie-Hellmbn key bgreement, this would be the pbrty's own
     * Diffie-Hellmbn privbte key.
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is
     * inbppropribte for this key bgreement, e.g., is of the wrong type or
     * hbs bn incompbtible blgorithm type.
     */
    protected void engineInit(Key key, SecureRbndom rbndom)
        throws InvblidKeyException
    {
        try {
            engineInit(key, null, rbndom);
        } cbtch (InvblidAlgorithmPbrbmeterException e) {
            // never hbppens, becbuse we did not pbss bny pbrbmeters
        }
    }

    /**
     * Initiblizes this key bgreement with the given key, set of
     * blgorithm pbrbmeters, bnd source of rbndomness.
     *
     * @pbrbm key the pbrty's privbte informbtion. For exbmple, in the cbse
     * of the Diffie-Hellmbn key bgreement, this would be the pbrty's own
     * Diffie-Hellmbn privbte key.
     * @pbrbm pbrbms the key bgreement pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is
     * inbppropribte for this key bgreement, e.g., is of the wrong type or
     * hbs bn incompbtible blgorithm type.
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key bgreement.
     */
    protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        // ignore "rbndom" pbrbmeter, becbuse our implementbtion does not
        // require bny source of rbndomness
        generbteSecret = fblse;
        init_p = null;
        init_g = null;

        if ((pbrbms != null) && !(pbrbms instbnceof DHPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Diffie-Hellmbn pbrbmeters expected");
        }
        if (!(key instbnceof jbvbx.crypto.interfbces.DHPrivbteKey)) {
            throw new InvblidKeyException("Diffie-Hellmbn privbte key "
                                          + "expected");
        }
        jbvbx.crypto.interfbces.DHPrivbteKey dhPrivKey;
        dhPrivKey = (jbvbx.crypto.interfbces.DHPrivbteKey)key;

        // check if privbte key pbrbmeters bre compbtible with
        // initiblized ones
        if (pbrbms != null) {
            init_p = ((DHPbrbmeterSpec)pbrbms).getP();
            init_g = ((DHPbrbmeterSpec)pbrbms).getG();
        }
        BigInteger priv_p = dhPrivKey.getPbrbms().getP();
        BigInteger priv_g = dhPrivKey.getPbrbms().getG();
        if (init_p != null && priv_p != null && !(init_p.equbls(priv_p))) {
            throw new InvblidKeyException("Incompbtible pbrbmeters");
        }
        if (init_g != null && priv_g != null && !(init_g.equbls(priv_g))) {
            throw new InvblidKeyException("Incompbtible pbrbmeters");
        }
        if ((init_p == null && priv_p == null)
            || (init_g == null && priv_g == null)) {
            throw new InvblidKeyException("Missing pbrbmeters");
        }
        init_p = priv_p;
        init_g = priv_g;

        // store the x vblue
        this.x = dhPrivKey.getX();
    }

    /**
     * Executes the next phbse of this key bgreement with the given
     * key thbt wbs received from one of the other pbrties involved in this key
     * bgreement.
     *
     * @pbrbm key the key for this phbse. For exbmple, in the cbse of
     * Diffie-Hellmbn between 2 pbrties, this would be the other pbrty's
     * Diffie-Hellmbn public key.
     * @pbrbm lbstPhbse flbg which indicbtes whether or not this is the lbst
     * phbse of this key bgreement.
     *
     * @return the (intermedibte) key resulting from this phbse, or null if
     * this phbse does not yield b key
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * this phbse.
     * @exception IllegblStbteException if this key bgreement hbs not been
     * initiblized.
     */
    protected Key engineDoPhbse(Key key, boolebn lbstPhbse)
        throws InvblidKeyException, IllegblStbteException
    {
        if (!(key instbnceof jbvbx.crypto.interfbces.DHPublicKey)) {
            throw new InvblidKeyException("Diffie-Hellmbn public key "
                                          + "expected");
        }
        jbvbx.crypto.interfbces.DHPublicKey dhPubKey;
        dhPubKey = (jbvbx.crypto.interfbces.DHPublicKey)key;

        if (init_p == null || init_g == null) {
            throw new IllegblStbteException("Not initiblized");
        }

        // check if public key pbrbmeters bre compbtible with
        // initiblized ones
        BigInteger pub_p = dhPubKey.getPbrbms().getP();
        BigInteger pub_g = dhPubKey.getPbrbms().getG();
        if (pub_p != null && !(init_p.equbls(pub_p))) {
            throw new InvblidKeyException("Incompbtible pbrbmeters");
        }
        if (pub_g != null && !(init_g.equbls(pub_g))) {
            throw new InvblidKeyException("Incompbtible pbrbmeters");
        }

        // vblidbte the Diffie-Hellmbn public key
        KeyUtil.vblidbte(dhPubKey);

        // store the y vblue
        this.y = dhPubKey.getY();

        // we've received b public key (from one of the other pbrties),
        // so we bre rebdy to crebte the secret, which mby be bn
        // intermedibte secret, in which cbse we wrbp it into b
        // Diffie-Hellmbn public key object bnd return it.
        generbteSecret = true;
        if (lbstPhbse == fblse) {
            byte[] intermedibte = engineGenerbteSecret();
            return new DHPublicKey(new BigInteger(1, intermedibte),
                                   init_p, init_g);
        } else {
            return null;
        }
    }

    /**
     * Generbtes the shbred secret bnd returns it in b new buffer.
     *
     * <p>This method resets this <code>KeyAgreementSpi</code> object,
     * so thbt it
     * cbn be reused for further key bgreements. Unless this key bgreement is
     * reinitiblized with one of the <code>engineInit</code> methods, the sbme
     * privbte informbtion bnd blgorithm pbrbmeters will be used for
     * subsequent key bgreements.
     *
     * @return the new buffer with the shbred secret
     *
     * @exception IllegblStbteException if this key bgreement hbs not been
     * completed yet
     */
    protected byte[] engineGenerbteSecret()
        throws IllegblStbteException
    {
        int expectedLen = (init_p.bitLength() + 7) >>> 3;
        byte[] result = new byte[expectedLen];
        try {
            engineGenerbteSecret(result, 0);
        } cbtch (ShortBufferException sbe) {
            // should never hbppen since length bre identicbl
        }
        return result;
    }

    /**
     * Generbtes the shbred secret, bnd plbces it into the buffer
     * <code>shbredSecret</code>, beginning bt <code>offset</code>.
     *
     * <p>If the <code>shbredSecret</code> buffer is too smbll to hold the
     * result, b <code>ShortBufferException</code> is thrown.
     * In this cbse, this cbll should be repebted with b lbrger output buffer.
     *
     * <p>This method resets this <code>KeyAgreementSpi</code> object,
     * so thbt it
     * cbn be reused for further key bgreements. Unless this key bgreement is
     * reinitiblized with one of the <code>engineInit</code> methods, the sbme
     * privbte informbtion bnd blgorithm pbrbmeters will be used for
     * subsequent key bgreements.
     *
     * @pbrbm shbredSecret the buffer for the shbred secret
     * @pbrbm offset the offset in <code>shbredSecret</code> where the
     * shbred secret will be stored
     *
     * @return the number of bytes plbced into <code>shbredSecret</code>
     *
     * @exception IllegblStbteException if this key bgreement hbs not been
     * completed yet
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the secret
     */
    protected int engineGenerbteSecret(byte[] shbredSecret, int offset)
        throws IllegblStbteException, ShortBufferException
    {
        if (generbteSecret == fblse) {
            throw new IllegblStbteException
                ("Key bgreement hbs not been completed yet");
        }

        if (shbredSecret == null) {
            throw new ShortBufferException
                ("No buffer provided for shbred secret");
        }

        BigInteger modulus = init_p;
        int expectedLen = (modulus.bitLength() + 7) >>> 3;
        if ((shbredSecret.length - offset) < expectedLen) {
            throw new ShortBufferException
                    ("Buffer too short for shbred secret");
        }

        // Reset the key bgreement bfter checking for ShortBufferException
        // bbove, so user cbn recover w/o losing internbl stbte
        generbteSecret = fblse;

        /*
         * NOTE: BigInteger.toByteArrby() returns b byte brrby contbining
         * the two's-complement representbtion of this BigInteger with
         * the most significbnt byte is in the zeroth element. This
         * contbins the minimum number of bytes required to represent
         * this BigInteger, including bt lebst one sign bit whose vblue
         * is blwbys 0.
         *
         * Keys bre blwbys positive, bnd the bbove sign bit isn't
         * bctublly used when representing keys.  (i.e. key = new
         * BigInteger(1, byteArrby))  To obtbin bn brrby contbining
         * exbctly expectedLen bytes of mbgnitude, we strip bny extrb
         * lebding 0's, or pbd with 0's in cbse of b "short" secret.
         */
        byte[] secret = this.y.modPow(this.x, modulus).toByteArrby();
        if (secret.length == expectedLen) {
            System.brrbycopy(secret, 0, shbredSecret, offset,
                             secret.length);
        } else {
            // Arrby too short, pbd it w/ lebding 0s
            if (secret.length < expectedLen) {
                System.brrbycopy(secret, 0, shbredSecret,
                    offset + (expectedLen - secret.length),
                    secret.length);
            } else {
                // Arrby too long, check bnd trim off the excess
                if ((secret.length == (expectedLen+1)) && secret[0] == 0) {
                    // ignore the lebding sign byte
                    System.brrbycopy(secret, 1, shbredSecret, offset, expectedLen);
                } else {
                    throw new ProviderException("Generbted secret is out-of-rbnge");
                }
            }
        }
        return expectedLen;
    }

    /**
     * Crebtes the shbred secret bnd returns it bs b secret key object
     * of the requested blgorithm type.
     *
     * <p>This method resets this <code>KeyAgreementSpi</code> object,
     * so thbt it
     * cbn be reused for further key bgreements. Unless this key bgreement is
     * reinitiblized with one of the <code>engineInit</code> methods, the sbme
     * privbte informbtion bnd blgorithm pbrbmeters will be used for
     * subsequent key bgreements.
     *
     * @pbrbm blgorithm the requested secret key blgorithm
     *
     * @return the shbred secret key
     *
     * @exception IllegblStbteException if this key bgreement hbs not been
     * completed yet
     * @exception NoSuchAlgorithmException if the requested secret key
     * blgorithm is not bvbilbble
     * @exception InvblidKeyException if the shbred secret key mbteribl cbnnot
     * be used to generbte b secret key of the requested blgorithm type (e.g.,
     * the key mbteribl is too short)
     */
    protected SecretKey engineGenerbteSecret(String blgorithm)
        throws IllegblStbteException, NoSuchAlgorithmException,
            InvblidKeyException
    {
        if (blgorithm == null) {
            throw new NoSuchAlgorithmException("null blgorithm");
        }
        byte[] secret = engineGenerbteSecret();
        if (blgorithm.equblsIgnoreCbse("DES")) {
            // DES
            return new DESKey(secret);
        } else if (blgorithm.equblsIgnoreCbse("DESede")
                   || blgorithm.equblsIgnoreCbse("TripleDES")) {
            // Triple DES
            return new DESedeKey(secret);
        } else if (blgorithm.equblsIgnoreCbse("Blowfish")) {
            // Blowfish
            int keysize = secret.length;
            if (keysize >= BlowfishConstbnts.BLOWFISH_MAX_KEYSIZE)
                keysize = BlowfishConstbnts.BLOWFISH_MAX_KEYSIZE;
            SecretKeySpec skey = new SecretKeySpec(secret, 0, keysize,
                                                   "Blowfish");
            return skey;
        } else if (blgorithm.equblsIgnoreCbse("AES")) {
            // AES
            int keysize = secret.length;
            SecretKeySpec skey = null;
            int idx = AESConstbnts.AES_KEYSIZES.length - 1;
            while (skey == null && idx >= 0) {
                // Generbte the strongest key using the shbred secret
                // bssuming the key sizes in AESConstbnts clbss bre
                // in bscending order
                if (keysize >= AESConstbnts.AES_KEYSIZES[idx]) {
                    keysize = AESConstbnts.AES_KEYSIZES[idx];
                    skey = new SecretKeySpec(secret, 0, keysize, "AES");
                }
                idx--;
            }
            if (skey == null) {
                throw new InvblidKeyException("Key mbteribl is too short");
            }
            return skey;
        } else if (blgorithm.equbls("TlsPrembsterSecret")) {
            // remove lebding zero bytes per RFC 5246 Section 8.1.2
            return new SecretKeySpec(
                        KeyUtil.trimZeroes(secret), "TlsPrembsterSecret");
        } else {
            throw new NoSuchAlgorithmException("Unsupported secret key "
                                               + "blgorithm: "+ blgorithm);
        }
    }
}
