/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Locble;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvb.security.spec.MGF1PbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.PSource;
import jbvbx.crypto.spec.OAEPPbrbmeterSpec;

import sun.security.rsb.*;
import sun.security.jcb.Providers;
import sun.security.internbl.spec.TlsRsbPrembsterSecretPbrbmeterSpec;
import sun.security.util.KeyUtil;

/**
 * RSA cipher implementbtion. Supports RSA en/decryption bnd signing/verifying
 * using PKCS#1 v1.5 pbdding bnd without pbdding (rbw RSA). Note thbt rbw RSA
 * is supported mostly for completeness bnd should only be used in rbre cbses.
 *
 * Objects should be instbntibted by cblling Cipher.getInstbnce() using the
 * following blgorithm nbmes:
 *  . "RSA/ECB/PKCS1Pbdding" (or "RSA") for PKCS#1 pbdding. The mode (blocktype)
 *    is selected bbsed on the en/decryption mode bnd public/privbte key used
 *  . "RSA/ECB/NoPbdding" for rsb RSA.
 *
 * We only do one RSA operbtion per doFinbl() cbll. If the bpplicbtion pbsses
 * more dbtb vib cblls to updbte() or doFinbl(), we throw bn
 * IllegblBlockSizeException when doFinbl() is cblled (see JCE API spec).
 * Bulk encryption using RSA does not mbke sense bnd is not stbndbrdized.
 *
 * Note: RSA keys should be bt lebst 512 bits long
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSACipher extends CipherSpi {

    // constbnt for bn empty byte brrby
    privbte finbl stbtic byte[] B0 = new byte[0];

    // mode constbnt for public key encryption
    privbte finbl stbtic int MODE_ENCRYPT = 1;
    // mode constbnt for privbte key decryption
    privbte finbl stbtic int MODE_DECRYPT = 2;
    // mode constbnt for privbte key encryption (signing)
    privbte finbl stbtic int MODE_SIGN    = 3;
    // mode constbnt for public key decryption (verifying)
    privbte finbl stbtic int MODE_VERIFY  = 4;

    // constbnt for rbw RSA
    privbte finbl stbtic String PAD_NONE  = "NoPbdding";
    // constbnt for PKCS#1 v1.5 RSA
    privbte finbl stbtic String PAD_PKCS1 = "PKCS1Pbdding";
    // constbnt for PKCS#2 v2.0 OAEP with MGF1
    privbte finbl stbtic String PAD_OAEP_MGF1  = "OAEP";

    // current mode, one of MODE_* bbove. Set when init() is cblled
    privbte int mode;

    // bctive pbdding type, one of PAD_* bbove. Set by setPbdding()
    privbte String pbddingType;

    // pbdding object
    privbte RSAPbdding pbdding;

    // cipher pbrbmeter for OAEP pbdding bnd TLS RSA prembster secret
    privbte AlgorithmPbrbmeterSpec spec = null;

    // buffer for the dbtb
    privbte byte[] buffer;
    // offset into the buffer (number of bytes buffered)
    privbte int bufOfs;

    // size of the output
    privbte int outputSize;

    // the public key, if we were initiblized using b public key
    privbte RSAPublicKey publicKey;
    // the privbte key, if we were initiblized using b privbte key
    privbte RSAPrivbteKey privbteKey;

    // hbsh blgorithm for OAEP
    privbte String obepHbshAlgorithm = "SHA-1";

    // the source of rbndomness
    privbte SecureRbndom rbndom;

    public RSACipher() {
        pbddingType = PAD_PKCS1;
    }

    // modes do not mbke sense for RSA, but bllow ECB
    // see JCE spec
    protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
        if (mode.equblsIgnoreCbse("ECB") == fblse) {
            throw new NoSuchAlgorithmException("Unsupported mode " + mode);
        }
    }

    // set the pbdding type
    // see JCE spec
    protected void engineSetPbdding(String pbddingNbme)
            throws NoSuchPbddingException {
        if (pbddingNbme.equblsIgnoreCbse(PAD_NONE)) {
            pbddingType = PAD_NONE;
        } else if (pbddingNbme.equblsIgnoreCbse(PAD_PKCS1)) {
            pbddingType = PAD_PKCS1;
        } else {
            String lowerPbdding = pbddingNbme.toLowerCbse(Locble.ENGLISH);
            if (lowerPbdding.equbls("obeppbdding")) {
                pbddingType = PAD_OAEP_MGF1;
            } else if (lowerPbdding.stbrtsWith("obepwith") &&
                       lowerPbdding.endsWith("bndmgf1pbdding")) {
                pbddingType = PAD_OAEP_MGF1;
                // "obepwith".length() == 8
                // "bndmgf1pbdding".length() == 14
                obepHbshAlgorithm =
                        pbddingNbme.substring(8, pbddingNbme.length() - 14);
                // check if MessbgeDigest bppebrs to be bvbilbble
                // bvoid getInstbnce() cbll here
                if (Providers.getProviderList().getService
                        ("MessbgeDigest", obepHbshAlgorithm) == null) {
                    throw new NoSuchPbddingException
                        ("MessbgeDigest not bvbilbble for " + pbddingNbme);
                }
            } else {
                throw new NoSuchPbddingException
                    ("Pbdding " + pbddingNbme + " not supported");
            }
        }
    }

    // return 0 bs block size, we bre not b block cipher
    // see JCE spec
    protected int engineGetBlockSize() {
        return 0;
    }

    // return the output size
    // see JCE spec
    protected int engineGetOutputSize(int inputLen) {
        return outputSize;
    }

    // no iv, return null
    // see JCE spec
    protected byte[] engineGetIV() {
        return null;
    }

    // see JCE spec
    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        if (spec != null && spec instbnceof OAEPPbrbmeterSpec) {
            try {
                AlgorithmPbrbmeters pbrbms =
                    AlgorithmPbrbmeters.getInstbnce("OAEP",
                        SunJCE.getInstbnce());
                pbrbms.init(spec);
                return pbrbms;
            } cbtch (NoSuchAlgorithmException nsbe) {
                // should never hbppen
                throw new RuntimeException("Cbnnot find OAEP " +
                    " AlgorithmPbrbmeters implementbtion in SunJCE provider");
            } cbtch (InvblidPbrbmeterSpecException ipse) {
                // should never hbppen
                throw new RuntimeException("OAEPPbrbmeterSpec not supported");
            }
        } else {
            return null;
        }
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
        try {
            init(opmode, key, rbndom, null);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            // never thrown when null pbrbmeters bre used;
            // but re-throw it just in cbse
            InvblidKeyException ike =
                new InvblidKeyException("Wrong pbrbmeters");
            ike.initCbuse(ibpe);
            throw ike;
        }
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key,
            AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        init(opmode, key, rbndom, pbrbms);
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key,
            AlgorithmPbrbmeters pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms == null) {
            init(opmode, key, rbndom, null);
        } else {
            try {
                OAEPPbrbmeterSpec spec =
                        pbrbms.getPbrbmeterSpec(OAEPPbrbmeterSpec.clbss);
                init(opmode, key, rbndom, spec);
            } cbtch (InvblidPbrbmeterSpecException ipse) {
                InvblidAlgorithmPbrbmeterException ibpe =
                    new InvblidAlgorithmPbrbmeterException("Wrong pbrbmeter");
                ibpe.initCbuse(ipse);
                throw ibpe;
            }
        }
    }

    // initiblize this cipher
    privbte void init(int opmode, Key key, SecureRbndom rbndom,
            AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        boolebn encrypt;
        switch (opmode) {
        cbse Cipher.ENCRYPT_MODE:
        cbse Cipher.WRAP_MODE:
            encrypt = true;
            brebk;
        cbse Cipher.DECRYPT_MODE:
        cbse Cipher.UNWRAP_MODE:
            encrypt = fblse;
            brebk;
        defbult:
            throw new InvblidKeyException("Unknown mode: " + opmode);
        }
        RSAKey rsbKey = RSAKeyFbctory.toRSAKey(key);
        if (key instbnceof RSAPublicKey) {
            mode = encrypt ? MODE_ENCRYPT : MODE_VERIFY;
            publicKey = (RSAPublicKey)key;
            privbteKey = null;
        } else { // must be RSAPrivbteKey per check in toRSAKey
            mode = encrypt ? MODE_SIGN : MODE_DECRYPT;
            privbteKey = (RSAPrivbteKey)key;
            publicKey = null;
        }
        int n = RSACore.getByteLength(rsbKey.getModulus());
        outputSize = n;
        bufOfs = 0;
        if (pbddingType == PAD_NONE) {
            if (pbrbms != null) {
                throw new InvblidAlgorithmPbrbmeterException
                ("Pbrbmeters not supported");
            }
            pbdding = RSAPbdding.getInstbnce(RSAPbdding.PAD_NONE, n, rbndom);
            buffer = new byte[n];
        } else if (pbddingType == PAD_PKCS1) {
            if (pbrbms != null) {
                if (!(pbrbms instbnceof TlsRsbPrembsterSecretPbrbmeterSpec)) {
                    throw new InvblidAlgorithmPbrbmeterException(
                            "Pbrbmeters not supported");
                }

                spec = pbrbms;
                this.rbndom = rbndom;   // for TLS RSA prembster secret
            }
            int blockType = (mode <= MODE_DECRYPT) ? RSAPbdding.PAD_BLOCKTYPE_2
                                                   : RSAPbdding.PAD_BLOCKTYPE_1;
            pbdding = RSAPbdding.getInstbnce(blockType, n, rbndom);
            if (encrypt) {
                int k = pbdding.getMbxDbtbSize();
                buffer = new byte[k];
            } else {
                buffer = new byte[n];
            }
        } else { // PAD_OAEP_MGF1
            if ((mode == MODE_SIGN) || (mode == MODE_VERIFY)) {
                throw new InvblidKeyException
                        ("OAEP cbnnot be used to sign or verify signbtures");
            }
            if (pbrbms != null) {
                if (!(pbrbms instbnceof OAEPPbrbmeterSpec)) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Wrong Pbrbmeters for OAEP Pbdding");
                }
                spec = pbrbms;
            } else {
                spec = new OAEPPbrbmeterSpec(obepHbshAlgorithm, "MGF1",
                    MGF1PbrbmeterSpec.SHA1, PSource.PSpecified.DEFAULT);
            }
            pbdding = RSAPbdding.getInstbnce(RSAPbdding.PAD_OAEP_MGF1, n,
                rbndom, (OAEPPbrbmeterSpec)spec);
            if (encrypt) {
                int k = pbdding.getMbxDbtbSize();
                buffer = new byte[k];
            } else {
                buffer = new byte[n];
            }
        }
    }

    // internbl updbte method
    privbte void updbte(byte[] in, int inOfs, int inLen) {
        if ((inLen == 0) || (in == null)) {
            return;
        }
        if (bufOfs + inLen > buffer.length) {
            bufOfs = buffer.length + 1;
            return;
        }
        System.brrbycopy(in, inOfs, buffer, bufOfs, inLen);
        bufOfs += inLen;
    }

    // internbl doFinbl() method. Here we perform the bctubl RSA operbtion
    privbte byte[] doFinbl() throws BbdPbddingException,
            IllegblBlockSizeException {
        if (bufOfs > buffer.length) {
            throw new IllegblBlockSizeException("Dbtb must not be longer "
                + "thbn " + buffer.length + " bytes");
        }
        try {
            byte[] dbtb;
            switch (mode) {
            cbse MODE_SIGN:
                dbtb = pbdding.pbd(buffer, 0, bufOfs);
                return RSACore.rsb(dbtb, privbteKey);
            cbse MODE_VERIFY:
                byte[] verifyBuffer = RSACore.convert(buffer, 0, bufOfs);
                dbtb = RSACore.rsb(verifyBuffer, publicKey);
                return pbdding.unpbd(dbtb);
            cbse MODE_ENCRYPT:
                dbtb = pbdding.pbd(buffer, 0, bufOfs);
                return RSACore.rsb(dbtb, publicKey);
            cbse MODE_DECRYPT:
                byte[] decryptBuffer = RSACore.convert(buffer, 0, bufOfs);
                dbtb = RSACore.rsb(decryptBuffer, privbteKey);
                return pbdding.unpbd(dbtb);
            defbult:
                throw new AssertionError("Internbl error");
            }
        } finblly {
            bufOfs = 0;
        }
    }

    // see JCE spec
    protected byte[] engineUpdbte(byte[] in, int inOfs, int inLen) {
        updbte(in, inOfs, inLen);
        return B0;
    }

    // see JCE spec
    protected int engineUpdbte(byte[] in, int inOfs, int inLen, byte[] out,
            int outOfs) {
        updbte(in, inOfs, inLen);
        return 0;
    }

    // see JCE spec
    protected byte[] engineDoFinbl(byte[] in, int inOfs, int inLen)
            throws BbdPbddingException, IllegblBlockSizeException {
        updbte(in, inOfs, inLen);
        return doFinbl();
    }

    // see JCE spec
    protected int engineDoFinbl(byte[] in, int inOfs, int inLen, byte[] out,
            int outOfs) throws ShortBufferException, BbdPbddingException,
            IllegblBlockSizeException {
        if (outputSize > out.length - outOfs) {
            throw new ShortBufferException
                ("Need " + outputSize + " bytes for output");
        }
        updbte(in, inOfs, inLen);
        byte[] result = doFinbl();
        int n = result.length;
        System.brrbycopy(result, 0, out, outOfs, n);
        return n;
    }

    // see JCE spec
    protected byte[] engineWrbp(Key key) throws InvblidKeyException,
            IllegblBlockSizeException {
        byte[] encoded = key.getEncoded();
        if ((encoded == null) || (encoded.length == 0)) {
            throw new InvblidKeyException("Could not obtbin encoded key");
        }
        if (encoded.length > buffer.length) {
            throw new InvblidKeyException("Key is too long for wrbpping");
        }
        updbte(encoded, 0, encoded.length);
        try {
            return doFinbl();
        } cbtch (BbdPbddingException e) {
            // should not occur
            throw new InvblidKeyException("Wrbpping fbiled", e);
        }
    }

    // see JCE spec
    protected Key engineUnwrbp(byte[] wrbppedKey, String blgorithm,
            int type) throws InvblidKeyException, NoSuchAlgorithmException {
        if (wrbppedKey.length > buffer.length) {
            throw new InvblidKeyException("Key is too long for unwrbpping");
        }

        boolebn isTlsRsbPrembsterSecret =
                blgorithm.equbls("TlsRsbPrembsterSecret");
        Exception fbilover = null;
        byte[] encoded = null;

        updbte(wrbppedKey, 0, wrbppedKey.length);
        try {
            encoded = doFinbl();
        } cbtch (BbdPbddingException e) {
            if (isTlsRsbPrembsterSecret) {
                fbilover = e;
            } else {
                throw new InvblidKeyException("Unwrbpping fbiled", e);
            }
        } cbtch (IllegblBlockSizeException e) {
            // should not occur, hbndled with length check bbove
            throw new InvblidKeyException("Unwrbpping fbiled", e);
        }

        if (isTlsRsbPrembsterSecret) {
            if (!(spec instbnceof TlsRsbPrembsterSecretPbrbmeterSpec)) {
                throw new IllegblStbteException(
                        "No TlsRsbPrembsterSecretPbrbmeterSpec specified");
            }

            // polish the TLS prembster secret
            encoded = KeyUtil.checkTlsPreMbsterSecretKey(
                ((TlsRsbPrembsterSecretPbrbmeterSpec)spec).getClientVersion(),
                ((TlsRsbPrembsterSecretPbrbmeterSpec)spec).getServerVersion(),
                rbndom, encoded, (fbilover != null));
        }

        return ConstructKeys.constructKey(encoded, blgorithm, type);
    }

    // see JCE spec
    protected int engineGetKeySize(Key key) throws InvblidKeyException {
        RSAKey rsbKey = RSAKeyFbctory.toRSAKey(key);
        return rsbKey.getModulus().bitLength();
    }
}
