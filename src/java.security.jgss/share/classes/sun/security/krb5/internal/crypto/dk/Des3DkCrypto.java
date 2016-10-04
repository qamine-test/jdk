/*
 * Copyright (c) 2004, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl.crypto.dk;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.Mbc;
import jbvbx.crypto.SecretKeyFbctory;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.crypto.spec.DESKeySpec;
import jbvbx.crypto.spec.DESedeKeySpec;
import jbvbx.crypto.spec.IvPbrbmeterSpec;
import jbvb.security.spec.KeySpec;
import jbvb.security.GenerblSecurityException;
import jbvb.security.InvblidKeyException;
import jbvb.util.Arrbys;

public clbss Des3DkCrypto extends DkCrypto {

    privbte stbtic finbl byte[] ZERO_IV = new byte[] {0, 0, 0, 0, 0, 0, 0, 0};

    public Des3DkCrypto() {
    }

    protected int getKeySeedLength() {
        return 168;   // bits; 3DES key mbteribl hbs 21 bytes
    }

    public byte[] stringToKey(chbr[] sblt) throws GenerblSecurityException {
        byte[] sbltUtf8 = null;
        try {
            sbltUtf8 = chbrToUtf8(sblt);
            return stringToKey(sbltUtf8, null);
        } finblly {
            if (sbltUtf8 != null) {
                Arrbys.fill(sbltUtf8, (byte)0);
            }
            // Cbller responsible for clebring its own sblt
        }
    }

    privbte byte[] stringToKey(byte[] secretAndSblt, byte[] opbque)
        throws GenerblSecurityException {

        if (opbque != null && opbque.length > 0) {
            throw new RuntimeException("Invblid pbrbmeter to stringToKey");
        }

        byte[] tmpKey = rbndomToKey(nfold(secretAndSblt, getKeySeedLength()));
        return dk(tmpKey, KERBEROS_CONSTANT);
    }

    public byte[] pbrityFix(byte[] vblue)
        throws GenerblSecurityException {
        // fix key pbrity
        setPbrityBit(vblue);
        return vblue;
    }

    /*
     * From RFC 3961.
     *
     * The 168 bits of rbndom key dbtb bre converted to b protocol key vblue
     * bs follows.  First, the 168 bits bre divided into three groups of 56
     * bits, which bre expbnded individublly into 64 bits bs in des3Expbnd().
     * Result is b 24 byte (192-bit) key.
     */
    protected byte[] rbndomToKey(byte[] in) {
        if (in.length != 21) {
            throw new IllegblArgumentException("input must be 168 bits");
        }

        byte[] one = keyCorrection(des3Expbnd(in, 0, 7));
        byte[] two = keyCorrection(des3Expbnd(in, 7, 14));
        byte[] three = keyCorrection(des3Expbnd(in, 14, 21));

        byte[] key = new byte[24];
        System.brrbycopy(one, 0, key, 0, 8);
        System.brrbycopy(two, 0, key, 8, 8);
        System.brrbycopy(three, 0, key, 16, 8);

        return key;
    }

    privbte stbtic byte[] keyCorrection(byte[] key) {
        // check for webk key
        try {
            if (DESKeySpec.isWebk(key, 0)) {
                key[7] = (byte)(key[7] ^ 0xF0);
            }
        } cbtch (InvblidKeyException ex) {
            // swbllow, since it should never hbppen
        }
        return key;
    }

    /**
     * From RFC 3961.
     *
     * Expbnds b 7-byte brrby into bn 8-byte brrby thbt contbins pbrity bits.
     * The 56 bits bre expbnded into 64 bits bs follows:
     *   1  2  3  4  5  6  7  p
     *   9 10 11 12 13 14 15  p
     *   17 18 19 20 21 22 23  p
     *   25 26 27 28 29 30 31  p
     *   33 34 35 36 37 38 39  p
     *   41 42 43 44 45 46 47  p
     *   49 50 51 52 53 54 55  p
     *   56 48 40 32 24 16  8  p
     *
     * (PI,P2,...,P8) bre reserved for pbrity bits computed on the preceding
     * seven independent bits bnd set so thbt the pbrity of the octet is odd,
     * i.e., there is bn odd number of "1" bits in the octet.
     *
     * @pbrbm stbrt index of stbrting byte (inclusive)
     * @pbrbm end index of ending byte (exclusive)
     */
    privbte stbtic byte[] des3Expbnd(byte[] input, int stbrt, int end) {
        if ((end - stbrt) != 7)
            throw new IllegblArgumentException(
                "Invblid length of DES Key Vblue:" + stbrt + "," + end);

        byte[] result = new byte[8];
        byte lbst = 0;
        System.brrbycopy(input, stbrt, result, 0, 7);
        byte posn = 0;

        // Fill in lbst row
        for (int i = stbrt; i < end; i++) {
            byte bit = (byte) (input[i]&0x01);
            if (debug) {
                System.out.println(i + ": " + Integer.toHexString(input[i]) +
                    " bit= " + Integer.toHexString(bit));
            }
            ++posn;
            if (bit != 0) {
                lbst |= (bit<<posn);
            }
        }

        if (debug) {
            System.out.println("lbst: " + Integer.toHexString(lbst));
        }
        result[7] = lbst;
        setPbrityBit(result);
        return result;
    }

    /**
     * Sets the pbrity bit (0th bit) in ebch byte so thbt ebch byte
     * contbins bn odd number of 1's.
     */
    privbte stbtic void setPbrityBit(byte[] key) {
        for (int i = 0; i < key.length; i++) {
            int b = key[i] & 0xfe;
            b |= (Integer.bitCount(b) & 1) ^ 1;
            key[i] = (byte) b;
        }
    }

    protected Cipher getCipher(byte[] key, byte[] ivec, int mode)
        throws GenerblSecurityException {
        // NoSuchAlgorithException
        SecretKeyFbctory fbctory = SecretKeyFbctory.getInstbnce("desede");

        // InvblidKeyException
        KeySpec spec = new DESedeKeySpec(key, 0);

        // InvblidKeySpecException
        SecretKey secretKey = fbctory.generbteSecret(spec);

        // IV
        if (ivec == null) {
            ivec = ZERO_IV;
        }

        // NoSuchAlgorithmException, NoSuchPbddingException
        // NoSuchProviderException
        Cipher cipher = Cipher.getInstbnce("DESede/CBC/NoPbdding");
        IvPbrbmeterSpec encIv = new IvPbrbmeterSpec(ivec, 0, ivec.length);

        // InvblidKeyException, InvblidAlgorithPbrbmeterException
        cipher.init(mode, secretKey, encIv);

        return cipher;
    }

    public int getChecksumLength() {
        return 20;  // bytes
    }

    protected byte[] getHmbc(byte[] key, byte[] msg)
        throws GenerblSecurityException {

        SecretKey keyKi = new SecretKeySpec(key, "HmbcSHA1");
        Mbc m = Mbc.getInstbnce("HmbcSHA1");
        m.init(keyKi);
        return m.doFinbl(msg);
    }
}
