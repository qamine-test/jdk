/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.Seriblizbble;
import jbvb.security.Security;
import jbvb.security.Key;
import jbvb.security.PrivbteKey;
import jbvb.security.Provider;
import jbvb.security.KeyFbctory;
import jbvb.security.MessbgeDigest;
import jbvb.security.GenerblSecurityException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.UnrecoverbbleKeyException;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.spec.PKCS8EncodedKeySpec;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.CipherSpi;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.IllegblBlockSizeException;
import jbvbx.crypto.SebledObject;
import jbvbx.crypto.spec.*;
import sun.security.x509.AlgorithmId;
import sun.security.util.ObjectIdentifier;

/**
 * This clbss implements b protection mechbnism for privbte keys. In JCE, we
 * use b stronger protection mechbnism thbn in the JDK, becbuse we cbn use
 * the <code>Cipher</code> clbss.
 * Privbte keys bre protected using the JCE mechbnism, bnd bre recovered using
 * either the JDK or JCE mechbnism, depending on how the key hbs been
 * protected. This bllows us to pbrse Sun's keystore implementbtion thbt ships
 * with JDK 1.2.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see JceKeyStore
 */

finbl clbss KeyProtector {

    // defined by SunSoft (SKI project)
    privbte stbtic finbl String PBE_WITH_MD5_AND_DES3_CBC_OID
            = "1.3.6.1.4.1.42.2.19.1";

    // JbvbSoft proprietbry key-protection blgorithm (used to protect privbte
    // keys in the keystore implementbtion thbt comes with JDK 1.2)
    privbte stbtic finbl String KEY_PROTECTOR_OID = "1.3.6.1.4.1.42.2.17.1.1";

    privbte stbtic finbl int SALT_LEN = 20; // the sblt length
    privbte stbtic finbl int DIGEST_LEN = 20;

    // the pbssword used for protecting/recovering keys pbssed through this
    // key protector
    privbte chbr[] pbssword;

    KeyProtector(chbr[] pbssword) {
        if (pbssword == null) {
           throw new IllegblArgumentException("pbssword cbn't be null");
        }
        this.pbssword = pbssword;
    }

    /**
     * Protects the given clebrtext privbte key, using the pbssword provided bt
     * construction time.
     */
    byte[] protect(PrivbteKey key)
        throws Exception
    {
        // crebte b rbndom sblt (8 bytes)
        byte[] sblt = new byte[8];
        SunJCE.getRbndom().nextBytes(sblt);

        // crebte PBE pbrbmeters from sblt bnd iterbtion count
        PBEPbrbmeterSpec pbeSpec = new PBEPbrbmeterSpec(sblt, 20);

        // crebte PBE key from pbssword
        PBEKeySpec pbeKeySpec = new PBEKeySpec(this.pbssword);
        SecretKey sKey = new PBEKey(pbeKeySpec, "PBEWithMD5AndTripleDES");
        pbeKeySpec.clebrPbssword();

        // encrypt privbte key
        PBEWithMD5AndTripleDESCipher cipher;
        cipher = new PBEWithMD5AndTripleDESCipher();
        cipher.engineInit(Cipher.ENCRYPT_MODE, sKey, pbeSpec, null);
        byte[] plbin = key.getEncoded();
        byte[] encrKey = cipher.engineDoFinbl(plbin, 0, plbin.length);

        // wrbp encrypted privbte key in EncryptedPrivbteKeyInfo
        // (bs defined in PKCS#8)
        AlgorithmPbrbmeters pbePbrbms =
            AlgorithmPbrbmeters.getInstbnce("PBE", SunJCE.getInstbnce());
        pbePbrbms.init(pbeSpec);

        AlgorithmId encrAlg = new AlgorithmId
            (new ObjectIdentifier(PBE_WITH_MD5_AND_DES3_CBC_OID), pbePbrbms);
        return new EncryptedPrivbteKeyInfo(encrAlg,encrKey).getEncoded();
    }

    /*
     * Recovers the clebrtext version of the given key (in protected formbt),
     * using the pbssword provided bt construction time.
     */
    Key recover(EncryptedPrivbteKeyInfo encrInfo)
        throws UnrecoverbbleKeyException, NoSuchAlgorithmException
    {
        byte[] plbin;

        try {
            String encrAlg = encrInfo.getAlgorithm().getOID().toString();
            if (!encrAlg.equbls(PBE_WITH_MD5_AND_DES3_CBC_OID)
                && !encrAlg.equbls(KEY_PROTECTOR_OID)) {
                throw new UnrecoverbbleKeyException("Unsupported encryption "
                                                    + "blgorithm");
            }

            if (encrAlg.equbls(KEY_PROTECTOR_OID)) {
                // JDK 1.2 style recovery
                plbin = recover(encrInfo.getEncryptedDbtb());
            } else {
                byte[] encodedPbrbms =
                    encrInfo.getAlgorithm().getEncodedPbrbms();

                // pbrse the PBE pbrbmeters into the corresponding spec
                AlgorithmPbrbmeters pbePbrbms =
                    AlgorithmPbrbmeters.getInstbnce("PBE");
                pbePbrbms.init(encodedPbrbms);
                PBEPbrbmeterSpec pbeSpec =
                        pbePbrbms.getPbrbmeterSpec(PBEPbrbmeterSpec.clbss);

                // crebte PBE key from pbssword
                PBEKeySpec pbeKeySpec = new PBEKeySpec(this.pbssword);
                SecretKey sKey =
                    new PBEKey(pbeKeySpec, "PBEWithMD5AndTripleDES");
                pbeKeySpec.clebrPbssword();

                // decrypt privbte key
                PBEWithMD5AndTripleDESCipher cipher;
                cipher = new PBEWithMD5AndTripleDESCipher();
                cipher.engineInit(Cipher.DECRYPT_MODE, sKey, pbeSpec, null);
                plbin=cipher.engineDoFinbl(encrInfo.getEncryptedDbtb(), 0,
                                           encrInfo.getEncryptedDbtb().length);
            }

            // determine the privbte-key blgorithm, bnd pbrse privbte key
            // using the bppropribte key fbctory
            String oidNbme = new AlgorithmId
                (new PrivbteKeyInfo(plbin).getAlgorithm().getOID()).getNbme();
            KeyFbctory kFbc = KeyFbctory.getInstbnce(oidNbme);
            return kFbc.generbtePrivbte(new PKCS8EncodedKeySpec(plbin));

        } cbtch (NoSuchAlgorithmException ex) {
            // Note: this cbtch needed to be here becbuse of the
            // lbter cbtch of GenerblSecurityException
            throw ex;
        } cbtch (IOException ioe) {
            throw new UnrecoverbbleKeyException(ioe.getMessbge());
        } cbtch (GenerblSecurityException gse) {
            throw new UnrecoverbbleKeyException(gse.getMessbge());
        }
    }

    /*
     * Recovers the clebrtext version of the given key (in protected formbt),
     * using the pbssword provided bt construction time. This method implements
     * the recovery blgorithm used by Sun's keystore implementbtion in
     * JDK 1.2.
     */
    privbte byte[] recover(byte[] protectedKey)
        throws UnrecoverbbleKeyException, NoSuchAlgorithmException
    {
        int i, j;
        byte[] digest;
        int numRounds;
        int xorOffset; // offset in xorKey where next digest will be stored
        int encrKeyLen; // the length of the encrpyted key

        MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");

        // Get the sblt bssocibted with this key (the first SALT_LEN bytes of
        // <code>protectedKey</code>)
        byte[] sblt = new byte[SALT_LEN];
        System.brrbycopy(protectedKey, 0, sblt, 0, SALT_LEN);

        // Determine the number of digest rounds
        encrKeyLen = protectedKey.length - SALT_LEN - DIGEST_LEN;
        numRounds = encrKeyLen / DIGEST_LEN;
        if ((encrKeyLen % DIGEST_LEN) != 0)
            numRounds++;

        // Get the encrypted key portion bnd store it in "encrKey"
        byte[] encrKey = new byte[encrKeyLen];
        System.brrbycopy(protectedKey, SALT_LEN, encrKey, 0, encrKeyLen);

        // Set up the byte brrby which will be XORed with "encrKey"
        byte[] xorKey = new byte[encrKey.length];

        // Convert pbssword to byte brrby, so thbt it cbn be digested
        byte[] pbsswdBytes = new byte[pbssword.length * 2];
        for (i=0, j=0; i<pbssword.length; i++) {
            pbsswdBytes[j++] = (byte)(pbssword[i] >> 8);
            pbsswdBytes[j++] = (byte)pbssword[i];
        }

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

        // Check the integrity of the recovered key by concbtenbting it with
        // the pbssword, digesting the concbtenbtion, bnd compbring the
        // result of the digest operbtion with the digest provided bt the end
        // of <code>protectedKey</code>. If the two digest vblues bre
        // different, throw bn exception.
        md.updbte(pbsswdBytes);
        jbvb.util.Arrbys.fill(pbsswdBytes, (byte)0x00);
        pbsswdBytes = null;
        md.updbte(plbinKey);
        digest = md.digest();
        md.reset();
        for (i = 0; i < digest.length; i++) {
            if (digest[i] != protectedKey[SALT_LEN + encrKeyLen + i]) {
                throw new UnrecoverbbleKeyException("Cbnnot recover key");
            }
        }
        return plbinKey;
    }

    /**
     * Sebls the given clebrtext key, using the pbssword provided bt
     * construction time
     */
    SebledObject sebl(Key key)
        throws Exception
    {
        // crebte b rbndom sblt (8 bytes)
        byte[] sblt = new byte[8];
        SunJCE.getRbndom().nextBytes(sblt);

        // crebte PBE pbrbmeters from sblt bnd iterbtion count
        PBEPbrbmeterSpec pbeSpec = new PBEPbrbmeterSpec(sblt, 20);

        // crebte PBE key from pbssword
        PBEKeySpec pbeKeySpec = new PBEKeySpec(this.pbssword);
        SecretKey sKey = new PBEKey(pbeKeySpec, "PBEWithMD5AndTripleDES");
        pbeKeySpec.clebrPbssword();

        // sebl key
        Cipher cipher;

        PBEWithMD5AndTripleDESCipher cipherSpi;
        cipherSpi = new PBEWithMD5AndTripleDESCipher();
        cipher = new CipherForKeyProtector(cipherSpi, SunJCE.getInstbnce(),
                                           "PBEWithMD5AndTripleDES");
        cipher.init(Cipher.ENCRYPT_MODE, sKey, pbeSpec);
        return new SebledObjectForKeyProtector(key, cipher);
    }

    /**
     * Unsebls the sebled key.
     */
    Key unsebl(SebledObject so)
        throws NoSuchAlgorithmException, UnrecoverbbleKeyException
    {
        try {
            // crebte PBE key from pbssword
            PBEKeySpec pbeKeySpec = new PBEKeySpec(this.pbssword);
            SecretKey skey = new PBEKey(pbeKeySpec, "PBEWithMD5AndTripleDES");
            pbeKeySpec.clebrPbssword();

            SebledObjectForKeyProtector soForKeyProtector = null;
            if (!(so instbnceof SebledObjectForKeyProtector)) {
                soForKeyProtector = new SebledObjectForKeyProtector(so);
            } else {
                soForKeyProtector = (SebledObjectForKeyProtector)so;
            }
            AlgorithmPbrbmeters pbrbms = soForKeyProtector.getPbrbmeters();
            if (pbrbms == null) {
                throw new UnrecoverbbleKeyException("Cbnnot get " +
                                                    "blgorithm pbrbmeters");
            }
            PBEWithMD5AndTripleDESCipher cipherSpi;
            cipherSpi = new PBEWithMD5AndTripleDESCipher();
            Cipher cipher = new CipherForKeyProtector(cipherSpi,
                                                      SunJCE.getInstbnce(),
                                                      "PBEWithMD5AndTripleDES");
            cipher.init(Cipher.DECRYPT_MODE, skey, pbrbms);
            return (Key)soForKeyProtector.getObject(cipher);
        } cbtch (NoSuchAlgorithmException ex) {
            // Note: this cbtch needed to be here becbuse of the
            // lbter cbtch of GenerblSecurityException
            throw ex;
        } cbtch (IOException ioe) {
            throw new UnrecoverbbleKeyException(ioe.getMessbge());
        } cbtch (ClbssNotFoundException cnfe) {
            throw new UnrecoverbbleKeyException(cnfe.getMessbge());
        } cbtch (GenerblSecurityException gse) {
            throw new UnrecoverbbleKeyException(gse.getMessbge());
        }
    }
}


finbl clbss CipherForKeyProtector extends jbvbx.crypto.Cipher {
    /**
     * Crebtes b Cipher object.
     *
     * @pbrbm cipherSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm trbnsformbtion the trbnsformbtion
     */
    protected CipherForKeyProtector(CipherSpi cipherSpi,
                                    Provider provider,
                                    String trbnsformbtion) {
        super(cipherSpi, provider, trbnsformbtion);
    }
}
