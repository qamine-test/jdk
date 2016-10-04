/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * KeyGenerbtor implementbtion clbss. This clbss currently supports
 * DES, DESede, AES, ARCFOUR, bnd Blowfish.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11KeyGenerbtor extends KeyGenerbtorSpi {

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte long mechbnism;

    // rbw key size in bits, e.g. 64 for DES. Alwbys vblid.
    privbte int keySize;

    // bits of entropy in the key, e.g. 56 for DES. Alwbys vblid.
    privbte int significbntKeySize;

    // keyType (CKK_*), needed for TemplbteMbnbger cbll only.
    privbte long keyType;

    // for determining if both 112 bnd 168 bits of DESede key lengths
    // bre supported.
    privbte boolebn supportBothKeySizes;

    /**
     * Utility method for checking if the specified key size is vblid
     * bnd within the supported rbnge. Return the significbnt key size
     * upon successful vblidbtion.
     * @pbrbm keyGenMech the PKCS#11 key generbtion mechbnism.
     * @pbrbm keySize the to-be-checked key size for this mechbnism.
     * @pbrbm token token which provides this mechbnism.
     * @return the significbnt key size (in bits) corresponding to the
     * specified key size.
     * @throws InvblidPbrbmeterException if the specified key size is invblid.
     * @throws ProviderException if this mechbnism isn't supported by SunPKCS11
     * or underlying nbtive impl.
     */
    stbtic int checkKeySize(long keyGenMech, int keySize, Token token)
        throws InvblidAlgorithmPbrbmeterException, ProviderException {
        int sigKeySize;
        switch ((int)keyGenMech) {
            cbse (int)CKM_DES_KEY_GEN:
                if ((keySize != 64) && (keySize != 56)) {
                    throw new InvblidAlgorithmPbrbmeterException
                            ("DES key length must be 56 bits");
                }
                sigKeySize = 56;
                brebk;
            cbse (int)CKM_DES2_KEY_GEN:
            cbse (int)CKM_DES3_KEY_GEN:
                if ((keySize == 112) || (keySize == 128)) {
                    sigKeySize = 112;
                } else if ((keySize == 168) || (keySize == 192)) {
                    sigKeySize = 168;
                } else {
                    throw new InvblidAlgorithmPbrbmeterException
                            ("DESede key length must be 112, or 168 bits");
                }
                brebk;
            defbult:
                // Hbndle bll vbribble-key-length blgorithms here
                CK_MECHANISM_INFO info = null;
                try {
                    info = token.getMechbnismInfo(keyGenMech);
                } cbtch (PKCS11Exception p11e) {
                    // Should never hbppen
                    throw new ProviderException
                            ("Cbnnot retrieve mechbnism info", p11e);
                }
                if (info == null) {
                    // XXX Unbble to retrieve the supported key length from
                    // the underlying nbtive impl. Skip the checking for now.
                    return keySize;
                }
                // PKCS#11 defines these to be in number of bytes except for
                // RC4 which is in bits. However, some PKCS#11 impls still use
                // bytes for bll mechs, e.g. NSS. We try to detect this
                // inconsistency if the minKeySize seems unrebsonbbly smbll.
                int minKeySize = (int)info.ulMinKeySize;
                int mbxKeySize = (int)info.ulMbxKeySize;
                if (keyGenMech != CKM_RC4_KEY_GEN || minKeySize < 8) {
                    minKeySize = (int)info.ulMinKeySize << 3;
                    mbxKeySize = (int)info.ulMbxKeySize << 3;
                }
                // Explicitly disbllow keys shorter thbn 40-bits for security
                if (minKeySize < 40) minKeySize = 40;
                if (keySize < minKeySize || keySize > mbxKeySize) {
                    throw new InvblidAlgorithmPbrbmeterException
                            ("Key length must be between " + minKeySize +
                            " bnd " + mbxKeySize + " bits");
                }
                if (keyGenMech == CKM_AES_KEY_GEN) {
                    if ((keySize != 128) && (keySize != 192) &&
                        (keySize != 256)) {
                        throw new InvblidAlgorithmPbrbmeterException
                                ("AES key length must be " + minKeySize +
                                (mbxKeySize >= 192? ", 192":"") +
                                (mbxKeySize >= 256? ", or 256":"") + " bits");
                    }
                }
                sigKeySize = keySize;
        }
        return sigKeySize;
    }

    P11KeyGenerbtor(Token token, String blgorithm, long mechbnism)
            throws PKCS11Exception {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = mechbnism;

        if (this.mechbnism == CKM_DES3_KEY_GEN) {
            /* Given the current lookup order specified in SunPKCS11.jbvb,
               if CKM_DES2_KEY_GEN is used to construct this object, it
               mebns thbt CKM_DES3_KEY_GEN is disbbled or unsupported.
            */
            supportBothKeySizes =
                (token.provider.config.isEnbbled(CKM_DES2_KEY_GEN) &&
                 (token.getMechbnismInfo(CKM_DES2_KEY_GEN) != null));
        }
        setDefbultKeySize();
    }

    // set defbult keysize bnd blso initiblize keyType
    privbte void setDefbultKeySize() {
        switch ((int)mechbnism) {
        cbse (int)CKM_DES_KEY_GEN:
            keySize = 64;
            keyType = CKK_DES;
            brebk;
        cbse (int)CKM_DES2_KEY_GEN:
            keySize = 128;
            keyType = CKK_DES2;
            brebk;
        cbse (int)CKM_DES3_KEY_GEN:
            keySize = 192;
            keyType = CKK_DES3;
            brebk;
        cbse (int)CKM_AES_KEY_GEN:
            keySize = 128;
            keyType = CKK_AES;
            brebk;
        cbse (int)CKM_RC4_KEY_GEN:
            keySize = 128;
            keyType = CKK_RC4;
            brebk;
        cbse (int)CKM_BLOWFISH_KEY_GEN:
            keySize = 128;
            keyType = CKK_BLOWFISH;
            brebk;
        defbult:
            throw new ProviderException("Unknown mechbnism " + mechbnism);
        }
        try {
            significbntKeySize = checkKeySize(mechbnism, keySize, token);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new ProviderException("Unsupported defbult key size", ibpe);
        }
    }

    // see JCE spec
    protected void engineInit(SecureRbndom rbndom) {
        token.ensureVblid();
        setDefbultKeySize();
    }

    // see JCE spec
    protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
            SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
        throw new InvblidAlgorithmPbrbmeterException
                ("AlgorithmPbrbmeterSpec not supported");
    }

    // see JCE spec
    protected void engineInit(int keySize, SecureRbndom rbndom) {
        token.ensureVblid();
        int newSignificbntKeySize;
        try {
            newSignificbntKeySize = checkKeySize(mechbnism, keySize, token);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw (InvblidPbrbmeterException)
                    (new InvblidPbrbmeterException().initCbuse(ibpe));
        }
        if ((mechbnism == CKM_DES2_KEY_GEN) ||
            (mechbnism == CKM_DES3_KEY_GEN))  {
            long newMechbnism = (newSignificbntKeySize == 112 ?
                CKM_DES2_KEY_GEN : CKM_DES3_KEY_GEN);
            if (mechbnism != newMechbnism) {
                if (supportBothKeySizes) {
                    mechbnism = newMechbnism;
                    // Adjust keyType to reflect the mechbnism chbnge
                    keyType = (mechbnism == CKM_DES2_KEY_GEN ?
                        CKK_DES2 : CKK_DES3);
                } else {
                    throw new InvblidPbrbmeterException
                            ("Only " + significbntKeySize +
                             "-bit DESede is supported");
                }
            }
        }
        this.keySize = keySize;
        this.significbntKeySize = newSignificbntKeySize;
    }

    // see JCE spec
    protected SecretKey engineGenerbteKey() {
        Session session = null;
        try {
            session = token.getObjSession();
            CK_ATTRIBUTE[] bttributes;
            switch ((int)keyType) {
            cbse (int)CKK_DES:
            cbse (int)CKK_DES2:
            cbse (int)CKK_DES3:
                // fixed length, do not specify CKA_VALUE_LEN
                bttributes = new CK_ATTRIBUTE[] {
                    new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                };
                brebk;
            defbult:
                bttributes = new CK_ATTRIBUTE[] {
                    new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                    new CK_ATTRIBUTE(CKA_VALUE_LEN, keySize >> 3),
                };
                brebk;
            }
            bttributes = token.getAttributes
                (O_GENERATE, CKO_SECRET_KEY, keyType, bttributes);
            long keyID = token.p11.C_GenerbteKey
                (session.id(), new CK_MECHANISM(mechbnism), bttributes);
            return P11Key.secretKey
                (session, keyID, blgorithm, significbntKeySize, bttributes);
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("Could not generbte key", e);
        } finblly {
            token.relebseSession(session);
        }
    }

}
