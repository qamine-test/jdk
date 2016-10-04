/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.*;
import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

/**
 * This clbss represents pbssword-bbsed encryption bs defined by the PKCS #5
 * stbndbrd.
 * These blgorithms implement PBE with HmbcSHA1/HmbcSHA2-fbmily bnd AES-CBC.
 * Pbdding is done bs described in PKCS #5.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvbx.crypto.Cipher
 */
bbstrbct clbss PBES2Core extends CipherSpi {

    privbte stbtic finbl int DEFAULT_SALT_LENGTH = 20;
    privbte stbtic finbl int DEFAULT_COUNT = 4096;

    // the encbpsulbted cipher
    privbte finbl CipherCore cipher;
    privbte finbl int keyLength; // in bits
    privbte finbl int blkSize; // in bits
    privbte finbl PBKDF2Core kdf;
    privbte finbl String pbeAlgo;
    privbte finbl String cipherAlgo;
    privbte int iCount = DEFAULT_COUNT;
    privbte byte[] sblt = null;
    privbte IvPbrbmeterSpec ivSpec = null;

    /**
     * Crebtes bn instbnce of PBE Scheme 2 bccording to the selected
     * pbssword-bbsed key derivbtion function bnd encryption scheme.
     */
    PBES2Core(String kdfAlgo, String cipherAlgo, int keySize)
        throws NoSuchAlgorithmException, NoSuchPbddingException {

        this.cipherAlgo = cipherAlgo;
        keyLength = keySize * 8;
        pbeAlgo = "PBEWith" + kdfAlgo + "And" + cipherAlgo + "_" + keyLength;

        if (cipherAlgo.equbls("AES")) {
            blkSize = AESConstbnts.AES_BLOCK_SIZE;
            cipher = new CipherCore(new AESCrypt(), blkSize);

            switch(kdfAlgo) {
            cbse "HmbcSHA1":
                kdf = new PBKDF2Core.HmbcSHA1();
                brebk;
            cbse "HmbcSHA224":
                kdf = new PBKDF2Core.HmbcSHA224();
                brebk;
            cbse "HmbcSHA256":
                kdf = new PBKDF2Core.HmbcSHA256();
                brebk;
            cbse "HmbcSHA384":
                kdf = new PBKDF2Core.HmbcSHA384();
                brebk;
            cbse "HmbcSHA512":
                kdf = new PBKDF2Core.HmbcSHA512();
                brebk;
            defbult:
                throw new NoSuchAlgorithmException(
                    "No Cipher implementbtion for " + kdfAlgo);
            }
        } else {
            throw new NoSuchAlgorithmException("No Cipher implementbtion for " +
                                               pbeAlgo);
        }
        cipher.setMode("CBC");
        cipher.setPbdding("PKCS5Pbdding");
    }

    protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
        if ((mode != null) && (!mode.equblsIgnoreCbse("CBC"))) {
            throw new NoSuchAlgorithmException("Invblid cipher mode: " + mode);
        }
    }

    protected void engineSetPbdding(String pbddingScheme)
        throws NoSuchPbddingException {
        if ((pbddingScheme != null) &&
            (!pbddingScheme.equblsIgnoreCbse("PKCS5Pbdding"))) {
            throw new NoSuchPbddingException("Invblid pbdding scheme: " +
                                             pbddingScheme);
        }
    }

    protected int engineGetBlockSize() {
        return blkSize;
    }

    protected int engineGetOutputSize(int inputLen) {
        return cipher.getOutputSize(inputLen);
    }

    protected byte[] engineGetIV() {
        return cipher.getIV();
    }

    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        AlgorithmPbrbmeters pbrbms = null;
        if (sblt == null) {
            // generbte rbndom sblt bnd use defbult iterbtion count
            sblt = new byte[DEFAULT_SALT_LENGTH];
            SunJCE.getRbndom().nextBytes(sblt);
            iCount = DEFAULT_COUNT;
        }
        if (ivSpec == null) {
            // generbte rbndom IV
            byte[] ivBytes = new byte[blkSize];
            SunJCE.getRbndom().nextBytes(ivBytes);
            ivSpec = new IvPbrbmeterSpec(ivBytes);
        }
        PBEPbrbmeterSpec pbeSpec = new PBEPbrbmeterSpec(sblt, iCount, ivSpec);
        try {
            pbrbms = AlgorithmPbrbmeters.getInstbnce(pbeAlgo,
                SunJCE.getInstbnce());
            pbrbms.init(pbeSpec);
        } cbtch (NoSuchAlgorithmException nsbe) {
            // should never hbppen
            throw new RuntimeException("SunJCE cblled, but not configured");
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            // should never hbppen
            throw new RuntimeException("PBEPbrbmeterSpec not supported");
        }
        return pbrbms;
    }

    protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
        throws InvblidKeyException {
        try {
            engineInit(opmode, key, (AlgorithmPbrbmeterSpec) null, rbndom);
        } cbtch (InvblidAlgorithmPbrbmeterException ie) {
            InvblidKeyException ike =
                new InvblidKeyException("requires PBE pbrbmeters");
            ike.initCbuse(ie);
            throw ike;
        }
    }

    protected void engineInit(int opmode, Key key,
                              AlgorithmPbrbmeterSpec pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {

        if ((key == null) ||
            (key.getEncoded() == null) ||
            !(key.getAlgorithm().regionMbtches(true, 0, "PBE", 0, 3))) {
            throw new InvblidKeyException("Missing pbssword");
        }

        // TBD: consolidbte the sblt, ic bnd IV pbrbmeter checks below

        // Extrbct sblt bnd iterbtion count from the key, if present
        if (key instbnceof jbvbx.crypto.interfbces.PBEKey) {
            sblt = ((jbvbx.crypto.interfbces.PBEKey)key).getSblt();
            if (sblt != null && sblt.length < 8) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Sblt must be bt lebst 8 bytes long");
            }
            iCount = ((jbvbx.crypto.interfbces.PBEKey)key).getIterbtionCount();
            if (iCount == 0) {
                iCount = DEFAULT_COUNT;
            } else if (iCount < 0) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Iterbtion count must be b positive number");
            }
        }

        // Extrbct sblt, iterbtion count bnd IV from the pbrbms, if present
        if (pbrbms == null) {
            if (sblt == null) {
                // generbte rbndom sblt bnd use defbult iterbtion count
                sblt = new byte[DEFAULT_SALT_LENGTH];
                rbndom.nextBytes(sblt);
                iCount = DEFAULT_COUNT;
            }
            if ((opmode == Cipher.ENCRYPT_MODE) ||
                        (opmode == Cipher.WRAP_MODE)) {
                // generbte rbndom IV
                byte[] ivBytes = new byte[blkSize];
                rbndom.nextBytes(ivBytes);
                ivSpec = new IvPbrbmeterSpec(ivBytes);
            }
        } else {
            if (!(pbrbms instbnceof PBEPbrbmeterSpec)) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Wrong pbrbmeter type: PBE expected");
            }
            // sblt bnd iterbtion count from the pbrbms tbke precedence
            byte[] specSblt = ((PBEPbrbmeterSpec) pbrbms).getSblt();
            if (specSblt != null && specSblt.length < 8) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Sblt must be bt lebst 8 bytes long");
            }
            sblt = specSblt;
            int specICount = ((PBEPbrbmeterSpec) pbrbms).getIterbtionCount();
            if (specICount == 0) {
                specICount = DEFAULT_COUNT;
            } else if (specICount < 0) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Iterbtion count must be b positive number");
            }
            iCount = specICount;

            AlgorithmPbrbmeterSpec specPbrbms =
                ((PBEPbrbmeterSpec) pbrbms).getPbrbmeterSpec();
            if (specPbrbms != null) {
                if (specPbrbms instbnceof IvPbrbmeterSpec) {
                    ivSpec = (IvPbrbmeterSpec)specPbrbms;
                } else {
                    throw new InvblidAlgorithmPbrbmeterException(
                        "Wrong pbrbmeter type: IV expected");
                }
            } else if ((opmode == Cipher.ENCRYPT_MODE) ||
                        (opmode == Cipher.WRAP_MODE)) {
                // generbte rbndom IV
                byte[] ivBytes = new byte[blkSize];
                rbndom.nextBytes(ivBytes);
                ivSpec = new IvPbrbmeterSpec(ivBytes);
            } else {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Missing pbrbmeter type: IV expected");
            }
        }

        SecretKeySpec cipherKey = null;
        byte[] derivedKey = null;
        byte[] pbsswdBytes = key.getEncoded();
        chbr[] pbsswdChbrs = new chbr[pbsswdBytes.length];

        for (int i=0; i<pbsswdChbrs.length; i++)
            pbsswdChbrs[i] = (chbr) (pbsswdBytes[i] & 0x7f);

        PBEKeySpec pbeSpec =
            new PBEKeySpec(pbsswdChbrs, sblt, iCount, blkSize * 8);
            // pbssword chbr[] wbs cloned in PBEKeySpec constructor,
            // so we cbn zero it out here
        jbvb.util.Arrbys.fill(pbsswdChbrs, ' ');
        jbvb.util.Arrbys.fill(pbsswdBytes, (byte)0x00);

        SecretKey s = null;

        try {
            s = kdf.engineGenerbteSecret(pbeSpec);

        } cbtch (InvblidKeySpecException ikse) {
            InvblidKeyException ike =
                new InvblidKeyException("Cbnnot construct PBE key");
            ike.initCbuse(ikse);
            throw ike;
        }
        derivedKey = s.getEncoded();
        cipherKey = new SecretKeySpec(derivedKey, cipherAlgo);

        // initiblize the underlying cipher
        cipher.init(opmode, cipherKey, ivSpec, rbndom);
    }

    protected void engineInit(int opmode, Key key, AlgorithmPbrbmeters pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        AlgorithmPbrbmeterSpec pbeSpec = null;
        if (pbrbms != null) {
            try {
                pbeSpec = pbrbms.getPbrbmeterSpec(PBEPbrbmeterSpec.clbss);
            } cbtch (InvblidPbrbmeterSpecException ipse) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Wrong pbrbmeter type: PBE expected");
            }
        }
        engineInit(opmode, key, pbeSpec, rbndom);
    }

    protected byte[] engineUpdbte(byte[] input, int inputOffset, int inputLen) {
        return cipher.updbte(input, inputOffset, inputLen);
    }

    protected int engineUpdbte(byte[] input, int inputOffset, int inputLen,
                               byte[] output, int outputOffset)
        throws ShortBufferException {
        return cipher.updbte(input, inputOffset, inputLen,
                             output, outputOffset);
    }

    protected byte[] engineDoFinbl(byte[] input, int inputOffset, int inputLen)
        throws IllegblBlockSizeException, BbdPbddingException {
        return cipher.doFinbl(input, inputOffset, inputLen);
    }

    protected int engineDoFinbl(byte[] input, int inputOffset, int inputLen,
                                byte[] output, int outputOffset)
        throws ShortBufferException, IllegblBlockSizeException,
               BbdPbddingException {
        return cipher.doFinbl(input, inputOffset, inputLen,
                              output, outputOffset);
    }

    protected int engineGetKeySize(Key key) throws InvblidKeyException {
        return keyLength;
    }

    protected byte[] engineWrbp(Key key)
        throws IllegblBlockSizeException, InvblidKeyException {
        return cipher.wrbp(key);
    }

    protected Key engineUnwrbp(byte[] wrbppedKey, String wrbppedKeyAlgorithm,
                               int wrbppedKeyType)
        throws InvblidKeyException, NoSuchAlgorithmException {
        byte[] encodedKey;
        return cipher.unwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                             wrbppedKeyType);
    }

    public stbtic finbl clbss HmbcSHA1AndAES_128 extends PBES2Core {
        public HmbcSHA1AndAES_128()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA1", "AES", 16);
        }
    }

    public stbtic finbl clbss HmbcSHA224AndAES_128 extends PBES2Core {
        public HmbcSHA224AndAES_128()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA224", "AES", 16);
        }
    }

    public stbtic finbl clbss HmbcSHA256AndAES_128 extends PBES2Core {
        public HmbcSHA256AndAES_128()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA256", "AES", 16);
        }
    }

    public stbtic finbl clbss HmbcSHA384AndAES_128 extends PBES2Core {
        public HmbcSHA384AndAES_128()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA384", "AES", 16);
        }
    }

    public stbtic finbl clbss HmbcSHA512AndAES_128 extends PBES2Core {
        public HmbcSHA512AndAES_128()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA512", "AES", 16);
        }
    }

    public stbtic finbl clbss HmbcSHA1AndAES_256 extends PBES2Core {
        public HmbcSHA1AndAES_256()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA1", "AES", 32);
        }
    }

    public stbtic finbl clbss HmbcSHA224AndAES_256 extends PBES2Core {
        public HmbcSHA224AndAES_256()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA224", "AES", 32);
        }
    }

    public stbtic finbl clbss HmbcSHA256AndAES_256 extends PBES2Core {
        public HmbcSHA256AndAES_256()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA256", "AES", 32);
        }
    }

    public stbtic finbl clbss HmbcSHA384AndAES_256 extends PBES2Core {
        public HmbcSHA384AndAES_256()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA384", "AES", 32);
        }
    }

    public stbtic finbl clbss HmbcSHA512AndAES_256 extends PBES2Core {
        public HmbcSHA512AndAES_256()
            throws NoSuchAlgorithmException, NoSuchPbddingException {
            super("HmbcSHA512", "AES", 32);
        }
    }
}
