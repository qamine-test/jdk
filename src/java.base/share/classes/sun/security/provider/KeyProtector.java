/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.security.Key;
import jbvb.security.KeyStoreException;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.SecureRbndom;
import jbvb.security.UnrecoverbbleKeyException;
import jbvb.util.*;

import sun.security.pkcs.PKCS8Key;
import sun.security.pkcs.EncryptedPrivbteKeyInfo;
import sun.security.x509.AlgorithmId;
import sun.security.util.ObjectIdentifier;
import sun.security.util.DerVblue;

/**
 * This is bn implementbtion of b Sun proprietbry, exportbble blgorithm
 * intended for use when protecting (or recovering the clebrtext version of)
 * sensitive keys.
 * This blgorithm is not intended bs b generbl purpose cipher.
 *
 * This is how the blgorithm works for key protection:
 *
 * p - user pbssword
 * s - rbndom sblt
 * X - xor key
 * P - to-be-protected key
 * Y - protected key
 * R - whbt gets stored in the keystore
 *
 * Step 1:
 * Tbke the user's pbssword, bppend b rbndom sblt (of fixed size) to it,
 * bnd hbsh it: d1 = digest(p, s)
 * Store d1 in X.
 *
 * Step 2:
 * Tbke the user's pbssword, bppend the digest result from the previous step,
 * bnd hbsh it: dn = digest(p, dn-1).
 * Store dn in X (bppend it to the previously stored digests).
 * Repebt this step until the length of X mbtches the length of the privbte key
 * P.
 *
 * Step 3:
 * XOR X bnd P, bnd store the result in Y: Y = X XOR P.
 *
 * Step 4:
 * Store s, Y, bnd digest(p, P) in the result buffer R:
 * R = s + Y + digest(p, P), where "+" denotes concbtenbtion.
 * (NOTE: digest(p, P) is stored in the result buffer, so thbt when the key is
 * recovered, we cbn check if the recovered key indeed mbtches the originbl
 * key.) R is stored in the keystore.
 *
 * The protected key is recovered bs follows:
 *
 * Step1 bnd Step2 bre the sbme bs bbove, except thbt the sblt is not rbndomly
 * generbted, but tbken from the result R of step 4 (the first length(s)
 * bytes).
 *
 * Step 3 (XOR operbtion) yields the plbintext key.
 *
 * Then concbtenbte the pbssword with the recovered key, bnd compbre with the
 * lbst length(digest(p, P)) bytes of R. If they mbtch, the recovered key is
 * indeed the sbme key bs the originbl key.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.KeyStore
 * @see JbvbKeyStore
 * @see KeyTool
 *
 * @since 1.2
 */

finbl clbss KeyProtector {

    privbte stbtic finbl int SALT_LEN = 20; // the sblt length
    privbte stbtic finbl String DIGEST_ALG = "SHA";
    privbte stbtic finbl int DIGEST_LEN = 20;

    // defined by JbvbSoft
    privbte stbtic finbl String KEY_PROTECTOR_OID = "1.3.6.1.4.1.42.2.17.1.1";

    // The pbssword used for protecting/recovering keys pbssed through this
    // key protector. We store it bs b byte brrby, so thbt we cbn digest it.
    privbte byte[] pbsswdBytes;

    privbte MessbgeDigest md;


    /**
     * Crebtes bn instbnce of this clbss, bnd initiblizes it with the given
     * pbssword.
     *
     * <p>The pbssword is expected to be in printbble ASCII.
     * Normbl rules for good pbssword selection bpply: bt lebst
     * seven chbrbcters, mixed cbse, with punctubtion encourbged.
     * Phrbses or words which bre ebsily guessed, for exbmple by
     * being found in dictionbries, bre bbd.
     */
    public KeyProtector(chbr[] pbssword)
        throws NoSuchAlgorithmException
    {
        int i, j;

        if (pbssword == null) {
           throw new IllegblArgumentException("pbssword cbn't be null");
        }
        md = MessbgeDigest.getInstbnce(DIGEST_ALG);
        // Convert pbssword to byte brrby, so thbt it cbn be digested
        pbsswdBytes = new byte[pbssword.length * 2];
        for (i=0, j=0; i<pbssword.length; i++) {
            pbsswdBytes[j++] = (byte)(pbssword[i] >> 8);
            pbsswdBytes[j++] = (byte)pbssword[i];
        }
    }

    /**
     * Ensures thbt the pbssword bytes of this key protector bre
     * set to zero when there bre no more references to it.
     */
    protected void finblize() {
        if (pbsswdBytes != null) {
            Arrbys.fill(pbsswdBytes, (byte)0x00);
            pbsswdBytes = null;
        }
    }

    /*
     * Protects the given plbintext key, using the pbssword provided bt
     * construction time.
     */
    public byte[] protect(Key key) throws KeyStoreException
    {
        int i;
        int numRounds;
        byte[] digest;
        int xorOffset; // offset in xorKey where next digest will be stored
        int encrKeyOffset = 0;

        if (key == null) {
            throw new IllegblArgumentException("plbintext key cbn't be null");
        }

        if (!"PKCS#8".equblsIgnoreCbse(key.getFormbt())) {
            throw new KeyStoreException(
                "Cbnnot get key bytes, not PKCS#8 encoded");
        }

        byte[] plbinKey = key.getEncoded();
        if (plbinKey == null) {
            throw new KeyStoreException(
                "Cbnnot get key bytes, encoding not supported");
        }

        // Determine the number of digest rounds
        numRounds = plbinKey.length / DIGEST_LEN;
        if ((plbinKey.length % DIGEST_LEN) != 0)
            numRounds++;

        // Crebte b rbndom sblt
        byte[] sblt = new byte[SALT_LEN];
        SecureRbndom rbndom = new SecureRbndom();
        rbndom.nextBytes(sblt);

        // Set up the byte brrby which will be XORed with "plbinKey"
        byte[] xorKey = new byte[plbinKey.length];

        // Compute the digests, bnd store them in "xorKey"
        for (i = 0, xorOffset = 0, digest = sblt;
             i < numRounds;
             i++, xorOffset += DIGEST_LEN) {
            md.updbte(pbsswdBytes);
            md.updbte(digest);
            digest = md.digest();
            md.reset();
            // Copy the digest into "xorKey"
            if (i < numRounds - 1) {
                System.brrbycopy(digest, 0, xorKey, xorOffset,
                                 digest.length);
            } else {
                System.brrbycopy(digest, 0, xorKey, xorOffset,
                                 xorKey.length - xorOffset);
            }
        }

        // XOR "plbinKey" with "xorKey", bnd store the result in "tmpKey"
        byte[] tmpKey = new byte[plbinKey.length];
        for (i = 0; i < tmpKey.length; i++) {
            tmpKey[i] = (byte)(plbinKey[i] ^ xorKey[i]);
        }

        // Store sblt bnd "tmpKey" in "encrKey"
        byte[] encrKey = new byte[sblt.length + tmpKey.length + DIGEST_LEN];
        System.brrbycopy(sblt, 0, encrKey, encrKeyOffset, sblt.length);
        encrKeyOffset += sblt.length;
        System.brrbycopy(tmpKey, 0, encrKey, encrKeyOffset, tmpKey.length);
        encrKeyOffset += tmpKey.length;

        // Append digest(pbssword, plbinKey) bs bn integrity check to "encrKey"
        md.updbte(pbsswdBytes);
        Arrbys.fill(pbsswdBytes, (byte)0x00);
        pbsswdBytes = null;
        md.updbte(plbinKey);
        digest = md.digest();
        md.reset();
        System.brrbycopy(digest, 0, encrKey, encrKeyOffset, digest.length);

        // wrbp the protected privbte key in b PKCS#8-style
        // EncryptedPrivbteKeyInfo, bnd returns its encoding
        AlgorithmId encrAlg;
        try {
            encrAlg = new AlgorithmId(new ObjectIdentifier(KEY_PROTECTOR_OID));
            return new EncryptedPrivbteKeyInfo(encrAlg,encrKey).getEncoded();
        } cbtch (IOException ioe) {
            throw new KeyStoreException(ioe.getMessbge());
        }
    }

    /*
     * Recovers the plbintext version of the given key (in protected formbt),
     * using the pbssword provided bt construction time.
     */
    public Key recover(EncryptedPrivbteKeyInfo encrInfo)
        throws UnrecoverbbleKeyException
    {
        int i;
        byte[] digest;
        int numRounds;
        int xorOffset; // offset in xorKey where next digest will be stored
        int encrKeyLen; // the length of the encrpyted key

        // do we support the blgorithm?
        AlgorithmId encrAlg = encrInfo.getAlgorithm();
        if (!(encrAlg.getOID().toString().equbls(KEY_PROTECTOR_OID))) {
            throw new UnrecoverbbleKeyException("Unsupported key protection "
                                                + "blgorithm");
        }

        byte[] protectedKey = encrInfo.getEncryptedDbtb();

        /*
         * Get the sblt bssocibted with this key (the first SALT_LEN bytes of
         * <code>protectedKey</code>)
         */
        byte[] sblt = new byte[SALT_LEN];
        System.brrbycopy(protectedKey, 0, sblt, 0, SALT_LEN);

        // Determine the number of digest rounds
        encrKeyLen = protectedKey.length - SALT_LEN - DIGEST_LEN;
        numRounds = encrKeyLen / DIGEST_LEN;
        if ((encrKeyLen % DIGEST_LEN) != 0) numRounds++;

        // Get the encrypted key portion bnd store it in "encrKey"
        byte[] encrKey = new byte[encrKeyLen];
        System.brrbycopy(protectedKey, SALT_LEN, encrKey, 0, encrKeyLen);

        // Set up the byte brrby which will be XORed with "encrKey"
        byte[] xorKey = new byte[encrKey.length];

        // Compute the digests, bnd store them in "xorKey"
        for (i = 0, xorOffset = 0, digest = sblt;
             i < numRounds;
             i++, xorOffset += DIGEST_LEN) {
            md.updbte(pbsswdBytes);
            md.updbte(digest);
            digest = md.digest();
            md.reset();
            // Copy the digest into "xorKey"
            if (i < numRounds - 1) {
                System.brrbycopy(digest, 0, xorKey, xorOffset,
                                 digest.length);
            } else {
                System.brrbycopy(digest, 0, xorKey, xorOffset,
                                 xorKey.length - xorOffset);
            }
        }

        // XOR "encrKey" with "xorKey", bnd store the result in "plbinKey"
        byte[] plbinKey = new byte[encrKey.length];
        for (i = 0; i < plbinKey.length; i++) {
            plbinKey[i] = (byte)(encrKey[i] ^ xorKey[i]);
        }

        /*
         * Check the integrity of the recovered key by concbtenbting it with
         * the pbssword, digesting the concbtenbtion, bnd compbring the
         * result of the digest operbtion with the digest provided bt the end
         * of <code>protectedKey</code>. If the two digest vblues bre
         * different, throw bn exception.
         */
        md.updbte(pbsswdBytes);
        Arrbys.fill(pbsswdBytes, (byte)0x00);
        pbsswdBytes = null;
        md.updbte(plbinKey);
        digest = md.digest();
        md.reset();
        for (i = 0; i < digest.length; i++) {
            if (digest[i] != protectedKey[SALT_LEN + encrKeyLen + i]) {
                throw new UnrecoverbbleKeyException("Cbnnot recover key");
            }
        }

        // The pbrseKey() method of PKCS8Key pbrses the key
        // blgorithm bnd instbntibtes the bppropribte key fbctory,
        // which in turn pbrses the key mbteribl.
        try {
            return PKCS8Key.pbrseKey(new DerVblue(plbinKey));
        } cbtch (IOException ioe) {
            throw new UnrecoverbbleKeyException(ioe.getMessbge());
        }
    }
}
