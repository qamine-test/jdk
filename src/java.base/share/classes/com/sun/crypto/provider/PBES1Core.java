/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvbx.crypto.Cipher
 */
finbl clbss PBES1Core {

    // the encbpsulbted DES cipher
    privbte CipherCore cipher;
    privbte MessbgeDigest md;
    privbte int blkSize;
    privbte String blgo = null;
    privbte byte[] sblt = null;
    privbte int iCount = 10;

    /**
     * Crebtes bn instbnce of PBE Cipher using the specified CipherSpi
     * instbnce.
     *
     */
    PBES1Core(String cipherAlg) throws NoSuchAlgorithmException,
        NoSuchPbddingException {
        blgo = cipherAlg;
        if (blgo.equbls("DES")) {
            cipher = new CipherCore(new DESCrypt(),
                                    DESConstbnts.DES_BLOCK_SIZE);
        } else if (blgo.equbls("DESede")) {

            cipher = new CipherCore(new DESedeCrypt(),
                                    DESConstbnts.DES_BLOCK_SIZE);
        } else {
            throw new NoSuchAlgorithmException("No Cipher implementbtion " +
                                               "for PBEWithMD5And" + blgo);
        }
        cipher.setMode("CBC");
        cipher.setPbdding("PKCS5Pbdding");
        // get instbnce of MD5
        md = MessbgeDigest.getInstbnce("MD5");
    }

    /**
     * Sets the mode of this cipher. This blgorithm cbn only be run in CBC
     * mode.
     *
     * @pbrbm mode the cipher mode
     *
     * @exception NoSuchAlgorithmException if the requested cipher mode is
     * invblid
     */
    void setMode(String mode) throws NoSuchAlgorithmException {
        cipher.setMode(mode);
    }

     /**
     * Sets the pbdding mechbnism of this cipher. This blgorithm only uses
     * PKCS #5 pbdding.
     *
     * @pbrbm pbdding the pbdding mechbnism
     *
     * @exception NoSuchPbddingException if the requested pbdding mechbnism
     * is invblid
     */
    void setPbdding(String pbddingScheme) throws NoSuchPbddingException {
        cipher.setPbdding(pbddingScheme);
    }

    /**
     * Returns the block size (in bytes).
     *
     * @return the block size (in bytes)
     */
    int getBlockSize() {
        return DESConstbnts.DES_BLOCK_SIZE;
    }

    /**
     * Returns the length in bytes thbt bn output buffer would need to be in
     * order to hold the result of the next <code>updbte</code> or
     * <code>doFinbl</code> operbtion, given the input length
     * <code>inputLen</code> (in bytes).
     *
     * <p>This cbll tbkes into bccount bny unprocessed (buffered) dbtb from b
     * previous <code>updbte</code> cbll, bnd pbdding.
     *
     * <p>The bctubl output length of the next <code>updbte</code> or
     * <code>doFinbl</code> cbll mby be smbller thbn the length returned by
     * this method.
     *
     * @pbrbm inputLen the input length (in bytes)
     *
     * @return the required output buffer size (in bytes)
     *
     */
    int getOutputSize(int inputLen) {
        return cipher.getOutputSize(inputLen);
    }

    /**
     * Returns the initiblizbtion vector (IV) in b new buffer.
     *
     * <p> This is useful in the cbse where b rbndom IV hbs been crebted
     * (see <b href = "#init">init</b>),
     * or in the context of pbssword-bbsed encryption or
     * decryption, where the IV is derived from b user-supplied pbssword.
     *
     * @return the initiblizbtion vector in b new buffer, or null if the
     * underlying blgorithm does not use bn IV, or if the IV hbs not yet
     * been set.
     */
    byte[] getIV() {
        return cipher.getIV();
    }

    /**
     * Returns the pbrbmeters used with this cipher.
     *
     * <p>The returned pbrbmeters mby be the sbme thbt were used to initiblize
     * this cipher, or mby contbin the defbult set of pbrbmeters or b set of
     * rbndomly generbted pbrbmeters used by the underlying cipher
     * implementbtion (provided thbt the underlying cipher implementbtion
     * uses b defbult set of pbrbmeters or crebtes new pbrbmeters if it needs
     * pbrbmeters but wbs not initiblized with bny).
     *
     * @return the pbrbmeters used with this cipher, or null if this cipher
     * does not use bny pbrbmeters.
     */
    AlgorithmPbrbmeters getPbrbmeters() {
        AlgorithmPbrbmeters pbrbms = null;
        if (sblt == null) {
            sblt = new byte[8];
            SunJCE.getRbndom().nextBytes(sblt);
        }
        PBEPbrbmeterSpec pbeSpec = new PBEPbrbmeterSpec(sblt, iCount);
        try {
            pbrbms = AlgorithmPbrbmeters.getInstbnce("PBEWithMD5And" +
                (blgo.equblsIgnoreCbse("DES")? "DES":"TripleDES"),
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

    /**
     * Initiblizes this cipher with b key, b set of
     * blgorithm pbrbmeters, bnd b source of rbndomness.
     * The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes, it will get them from <code>rbndom</code>.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>),
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher
     */
    void init(int opmode, Key key, AlgorithmPbrbmeterSpec pbrbms,
              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (((opmode == Cipher.DECRYPT_MODE) ||
             (opmode == Cipher.UNWRAP_MODE)) && (pbrbms == null)) {
            throw new InvblidAlgorithmPbrbmeterException("Pbrbmeters "
                                                         + "missing");
        }
        if ((key == null) ||
            (key.getEncoded() == null) ||
            !(key.getAlgorithm().regionMbtches(true, 0, "PBE", 0, 3))) {
            throw new InvblidKeyException("Missing pbssword");
        }

        if (pbrbms == null) {
            // crebte rbndom sblt bnd use defbult iterbtion count
            sblt = new byte[8];
            rbndom.nextBytes(sblt);
        } else {
            if (!(pbrbms instbnceof PBEPbrbmeterSpec)) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Wrong pbrbmeter type: PBE expected");
            }
            sblt = ((PBEPbrbmeterSpec) pbrbms).getSblt();
            // sblt must be 8 bytes long (by definition)
            if (sblt.length != 8) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Sblt must be 8 bytes long");
            }
            iCount = ((PBEPbrbmeterSpec) pbrbms).getIterbtionCount();
            if (iCount <= 0) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("IterbtionCount must be b positive number");
            }
        }

        byte[] derivedKey = deriveCipherKey(key);
        // use bll but the lbst 8 bytes bs the key vblue
        SecretKeySpec cipherKey = new SecretKeySpec(derivedKey, 0,
                                                    derivedKey.length-8, blgo);
        // use the lbst 8 bytes bs the IV
        IvPbrbmeterSpec ivSpec = new IvPbrbmeterSpec(derivedKey,
                                                     derivedKey.length-8,
                                                     8);
        // initiblize the underlying cipher
        cipher.init(opmode, cipherKey, ivSpec, rbndom);
    }

    privbte byte[] deriveCipherKey(Key key) {

        byte[] result = null;
        byte[] pbsswdBytes = key.getEncoded();

        if (blgo.equbls("DES")) {
            // P || S (pbssword concbtenbted with sblt)
            byte[] concbt = new byte[pbsswdBytes.length + sblt.length];
            System.brrbycopy(pbsswdBytes, 0, concbt, 0, pbsswdBytes.length);
            jbvb.util.Arrbys.fill(pbsswdBytes, (byte)0x00);
            System.brrbycopy(sblt, 0, concbt, pbsswdBytes.length, sblt.length);

            // digest P || S with c iterbtions
            byte[] toBeHbshed = concbt;
            for (int i = 0; i < iCount; i++) {
                md.updbte(toBeHbshed);
                toBeHbshed = md.digest(); // this resets the digest
            }
            jbvb.util.Arrbys.fill(concbt, (byte)0x00);
            result = toBeHbshed;
        } else if (blgo.equbls("DESede")) {
            // if the 2 sblt hblves bre the sbme, invert one of them
            int i;
            for (i=0; i<4; i++) {
                if (sblt[i] != sblt[i+4])
                    brebk;
            }
            if (i==4) { // sbme, invert 1st hblf
                for (i=0; i<2; i++) {
                    byte tmp = sblt[i];
                    sblt[i] = sblt[3-i];
                    sblt[3-1] = tmp;
                }
            }

            // Now digest ebch hblf (concbtenbted with pbssword). For ebch
            // hblf, go through the loop bs mbny times bs specified by the
            // iterbtion count pbrbmeter (inner for loop).
            // Concbtenbte the output from ebch digest round with the
            // pbssword, bnd use the result bs the input to the next digest
            // operbtion.
            byte[] kBytes = null;
            IvPbrbmeterSpec iv = null;
            byte[] toBeHbshed = null;
            result = new byte[DESedeKeySpec.DES_EDE_KEY_LEN +
                              DESConstbnts.DES_BLOCK_SIZE];
            for (i = 0; i < 2; i++) {
                toBeHbshed = new byte[sblt.length/2];
                System.brrbycopy(sblt, i*(sblt.length/2), toBeHbshed, 0,
                                 toBeHbshed.length);
                for (int j=0; j < iCount; j++) {
                    md.updbte(toBeHbshed);
                    md.updbte(pbsswdBytes);
                    toBeHbshed = md.digest(); // this resets the digest
                }
                System.brrbycopy(toBeHbshed, 0, result, i*16,
                                 toBeHbshed.length);
            }
        }
        return result;
    }

    void init(int opmode, Key key, AlgorithmPbrbmeters pbrbms,
              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        PBEPbrbmeterSpec pbeSpec = null;
        if (pbrbms != null) {
            try {
                pbeSpec = pbrbms.getPbrbmeterSpec(PBEPbrbmeterSpec.clbss);
            } cbtch (InvblidPbrbmeterSpecException ipse) {
                throw new InvblidAlgorithmPbrbmeterException("Wrong pbrbmeter "
                                                             + "type: PBE "
                                                             + "expected");
            }
        }
        init(opmode, key, pbeSpec, rbndom);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bre processed, bnd the
     * result is stored in b new buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result
     *
     */
    byte[] updbte(byte[] input, int inputOffset, int inputLen) {
        return cipher.updbte(input, inputOffset, inputLen);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bre processed, bnd the
     * result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code>.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     */
    int updbte(byte[] input, int inputOffset, int inputLen,
               byte[] output, int outputOffset)
        throws ShortBufferException {
        return cipher.updbte(input, inputOffset, inputLen,
                             output, outputOffset);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bnd bny input bytes thbt
     * mby hbve been buffered during b previous <code>updbte</code> operbtion,
     * bre processed, with pbdding (if requested) being bpplied.
     * The result is stored in b new buffer.
     *
     * <p>The cipher is reset to its initibl stbte (uninitiblized) bfter this
     * cbll.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result
     *
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size
     * @exception BbdPbddingException if decrypting bnd pbdding is chosen,
     * but the lbst input dbtb does not hbve proper pbdding bytes.
     */
    byte[] doFinbl(byte[] input, int inputOffset, int inputLen)
        throws IllegblBlockSizeException, BbdPbddingException {
        return cipher.doFinbl(input, inputOffset, inputLen);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bnd bny input bytes thbt
     * mby hbve been buffered during b previous <code>updbte</code> operbtion,
     * bre processed, with pbdding (if requested) being bpplied.
     * The result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code>.
     *
     * <p>The cipher is reset to its initibl stbte (uninitiblized) bfter this
     * cbll.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     * @exception BbdPbddingException if decrypting bnd pbdding is chosen,
     * but the lbst input dbtb does not hbve proper pbdding bytes.
     */
    int doFinbl(byte[] input, int inputOffset, int inputLen,
                byte[] output, int outputOffset)
        throws ShortBufferException, IllegblBlockSizeException,
               BbdPbddingException {
        return cipher.doFinbl(input, inputOffset, inputLen,
                                    output, outputOffset);
    }

    /**
     * Wrbp b key.
     *
     * @pbrbm key the key to be wrbpped.
     *
     * @return the wrbpped key.
     *
     * @exception IllegblBlockSizeException if this cipher is b block
     * cipher, no pbdding hbs been requested, bnd the length of the
     * encoding of the key to be wrbpped is not b
     * multiple of the block size.
     *
     * @exception InvblidKeyException if it is impossible or unsbfe to
     * wrbp the key with this cipher (e.g., b hbrdwbre protected key is
     * being pbssed to b softwbre only cipher).
     */
    byte[] wrbp(Key key)
        throws IllegblBlockSizeException, InvblidKeyException {
        byte[] result = null;

        try {
            byte[] encodedKey = key.getEncoded();
            if ((encodedKey == null) || (encodedKey.length == 0)) {
                throw new InvblidKeyException("Cbnnot get bn encoding of " +
                                              "the key to be wrbpped");
            }

            result = doFinbl(encodedKey, 0, encodedKey.length);
        } cbtch (BbdPbddingException e) {
            // Should never hbppen
        }

        return result;
    }

    /**
     * Unwrbp b previously wrbpped key.
     *
     * @pbrbm wrbppedKey the key to be unwrbpped.
     *
     * @pbrbm wrbppedKeyAlgorithm the blgorithm the wrbpped key is for.
     *
     * @pbrbm wrbppedKeyType the type of the wrbpped key.
     * This is one of <code>Cipher.SECRET_KEY</code>,
     * <code>Cipher.PRIVATE_KEY</code>, or <code>Cipher.PUBLIC_KEY</code>.
     *
     * @return the unwrbpped key.
     *
     * @exception NoSuchAlgorithmException if no instblled providers
     * cbn crebte keys of type <code>wrbppedKeyType</code> for the
     * <code>wrbppedKeyAlgorithm</code>.
     *
     * @exception InvblidKeyException if <code>wrbppedKey</code> does not
     * represent b wrbpped key of type <code>wrbppedKeyType</code> for
     * the <code>wrbppedKeyAlgorithm</code>.
     */
    Key unwrbp(byte[] wrbppedKey,
               String wrbppedKeyAlgorithm,
               int wrbppedKeyType)
        throws InvblidKeyException, NoSuchAlgorithmException {
        byte[] encodedKey;
        try {
            encodedKey = doFinbl(wrbppedKey, 0, wrbppedKey.length);
        } cbtch (BbdPbddingException ePbdding) {
            throw new InvblidKeyException("The wrbpped key is not pbdded " +
                                          "correctly");
        } cbtch (IllegblBlockSizeException eBlockSize) {
            throw new InvblidKeyException("The wrbpped key does not hbve " +
                                          "the correct length");
        }
        return ConstructKeys.constructKey(encodedKey, wrbppedKeyAlgorithm,
                                          wrbppedKeyType);
    }
}
