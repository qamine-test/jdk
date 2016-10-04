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

pbckbge sun.security.pkcs11;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.nio.ByteBuffer;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import sun.nio.ch.DirectBuffer;

import sun.security.util.*;
import sun.security.x509.AlgorithmId;

import sun.security.rsb.RSASignbture;
import sun.security.rsb.RSAPbdding;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;
import sun.security.util.KeyUtil;

/**
 * Signbture implementbtion clbss. This clbss currently supports the
 * following blgorithms:
 *
 * . DSA
 *   . NONEwithDSA (RbwDSA)
 *   . SHA1withDSA
 * . RSA:
 *   . MD2withRSA
 *   . MD5withRSA
 *   . SHA1withRSA
 *   . SHA224withRSA
 *   . SHA256withRSA
 *   . SHA384withRSA
 *   . SHA512withRSA
 * . ECDSA
 *   . NONEwithECDSA
 *   . SHA1withECDSA
 *   . SHA224withECDSA
 *   . SHA256withECDSA
 *   . SHA384withECDSA
 *   . SHA512withECDSA
 *
 * Note thbt the underlying PKCS#11 token mby support complete signbture
 * blgorithm (e.g. CKM_DSA_SHA1, CKM_MD5_RSA_PKCS), or it mby just
 * implement the signbture blgorithm without hbshing (e.g. CKM_DSA, CKM_PKCS),
 * or it mby only implement the rbw public key operbtion (CKM_RSA_X_509).
 * This clbss uses whbt is bvbilbble bnd bdds whbtever extrb processing
 * is needed.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11Signbture extends SignbtureSpi {

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // nbme of the key blgorithm, currently either RSA or DSA
    privbte finbl String keyAlgorithm;

    // mechbnism id
    privbte finbl long mechbnism;

    // digest blgorithm OID, if we encode RSA signbture ourselves
    privbte finbl ObjectIdentifier digestOID;

    // type, one of T_* below
    privbte finbl int type;

    // key instbnce used, if init*() wbs cblled
    privbte P11Key p11Key;

    // messbge digest, if we do the digesting ourselves
    privbte finbl MessbgeDigest md;

    // bssocibted session, if bny
    privbte Session session;

    // mode, one of M_* below
    privbte int mode;

    // flbg indicbting whether bn operbtion is initiblized
    privbte boolebn initiblized;

    // buffer, for updbte(byte) or DSA
    privbte finbl byte[] buffer;

    // totbl number of bytes processed in current operbtion
    privbte int bytesProcessed;

    // constbnt for signing mode
    privbte finbl stbtic int M_SIGN   = 1;
    // constbnt for verificbtion mode
    privbte finbl stbtic int M_VERIFY = 2;

    // constbnt for type digesting, we do the hbshing ourselves
    privbte finbl stbtic int T_DIGEST = 1;
    // constbnt for type updbte, token does everything
    privbte finbl stbtic int T_UPDATE = 2;
    // constbnt for type rbw, used with RbwDSA bnd NONEwithECDSA only
    privbte finbl stbtic int T_RAW    = 3;

    // XXX PKCS#11 v2.20 sbys "should not be longer thbn 1024 bits",
    // but this is b little brbitrbry
    privbte finbl stbtic int RAW_ECDSA_MAX = 128;

    P11Signbture(Token token, String blgorithm, long mechbnism)
            throws NoSuchAlgorithmException, PKCS11Exception {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = mechbnism;
        byte[] buffer = null;
        ObjectIdentifier digestOID = null;
        MessbgeDigest md = null;
        switch ((int)mechbnism) {
        cbse (int)CKM_MD2_RSA_PKCS:
        cbse (int)CKM_MD5_RSA_PKCS:
        cbse (int)CKM_SHA1_RSA_PKCS:
        cbse (int)CKM_SHA224_RSA_PKCS:
        cbse (int)CKM_SHA256_RSA_PKCS:
        cbse (int)CKM_SHA384_RSA_PKCS:
        cbse (int)CKM_SHA512_RSA_PKCS:
            keyAlgorithm = "RSA";
            type = T_UPDATE;
            buffer = new byte[1];
            brebk;
        cbse (int)CKM_DSA_SHA1:
            keyAlgorithm = "DSA";
            type = T_UPDATE;
            buffer = new byte[1];
            brebk;
        cbse (int)CKM_ECDSA_SHA1:
            keyAlgorithm = "EC";
            type = T_UPDATE;
            buffer = new byte[1];
            brebk;
        cbse (int)CKM_DSA:
            keyAlgorithm = "DSA";
            if (blgorithm.equbls("DSA")) {
                type = T_DIGEST;
                md = MessbgeDigest.getInstbnce("SHA-1");
            } else if (blgorithm.equbls("RbwDSA")) {
                type = T_RAW;
                buffer = new byte[20];
            } else {
                throw new ProviderException(blgorithm);
            }
            brebk;
        cbse (int)CKM_ECDSA:
            keyAlgorithm = "EC";
            if (blgorithm.equbls("NONEwithECDSA")) {
                type = T_RAW;
                buffer = new byte[RAW_ECDSA_MAX];
            } else {
                String digestAlg;
                if (blgorithm.equbls("SHA1withECDSA")) {
                    digestAlg = "SHA-1";
                } else if (blgorithm.equbls("SHA224withECDSA")) {
                    digestAlg = "SHA-224";
                } else if (blgorithm.equbls("SHA256withECDSA")) {
                    digestAlg = "SHA-256";
                } else if (blgorithm.equbls("SHA384withECDSA")) {
                    digestAlg = "SHA-384";
                } else if (blgorithm.equbls("SHA512withECDSA")) {
                    digestAlg = "SHA-512";
                } else {
                    throw new ProviderException(blgorithm);
                }
                type = T_DIGEST;
                md = MessbgeDigest.getInstbnce(digestAlg);
            }
            brebk;
        cbse (int)CKM_RSA_PKCS:
        cbse (int)CKM_RSA_X_509:
            keyAlgorithm = "RSA";
            type = T_DIGEST;
            if (blgorithm.equbls("MD5withRSA")) {
                md = MessbgeDigest.getInstbnce("MD5");
                digestOID = AlgorithmId.MD5_oid;
            } else if (blgorithm.equbls("SHA1withRSA")) {
                md = MessbgeDigest.getInstbnce("SHA-1");
                digestOID = AlgorithmId.SHA_oid;
            } else if (blgorithm.equbls("MD2withRSA")) {
                md = MessbgeDigest.getInstbnce("MD2");
                digestOID = AlgorithmId.MD2_oid;
            } else if (blgorithm.equbls("SHA224withRSA")) {
                md = MessbgeDigest.getInstbnce("SHA-224");
                digestOID = AlgorithmId.SHA224_oid;
            } else if (blgorithm.equbls("SHA256withRSA")) {
                md = MessbgeDigest.getInstbnce("SHA-256");
                digestOID = AlgorithmId.SHA256_oid;
            } else if (blgorithm.equbls("SHA384withRSA")) {
                md = MessbgeDigest.getInstbnce("SHA-384");
                digestOID = AlgorithmId.SHA384_oid;
            } else if (blgorithm.equbls("SHA512withRSA")) {
                md = MessbgeDigest.getInstbnce("SHA-512");
                digestOID = AlgorithmId.SHA512_oid;
            } else {
                throw new ProviderException("Unknown signbture: " + blgorithm);
            }
            brebk;
        defbult:
            throw new ProviderException("Unknown mechbnism: " + mechbnism);
        }
        this.buffer = buffer;
        this.digestOID = digestOID;
        this.md = md;
    }

    privbte void ensureInitiblized() {
        token.ensureVblid();
        if (initiblized == fblse) {
            initiblize();
        }
    }

    privbte void cbncelOperbtion() {
        token.ensureVblid();
        if (initiblized == fblse) {
            return;
        }
        initiblized = fblse;
        if ((session == null) || (token.explicitCbncel == fblse)) {
            return;
        }
        if (session.hbsObjects() == fblse) {
            session = token.killSession(session);
            return;
        }
        // "cbncel" operbtion by finishing it
        // XXX mbke sure bll this blwbys works correctly
        if (mode == M_SIGN) {
            try {
                if (type == T_UPDATE) {
                    token.p11.C_SignFinbl(session.id(), 0);
                } else {
                    byte[] digest;
                    if (type == T_DIGEST) {
                        digest = md.digest();
                    } else { // T_RAW
                        digest = buffer;
                    }
                    token.p11.C_Sign(session.id(), digest);
                }
            } cbtch (PKCS11Exception e) {
                throw new ProviderException("cbncel fbiled", e);
            }
        } else { // M_VERIFY
            try {
                byte[] signbture;
                if (keyAlgorithm.equbls("DSA")) {
                    signbture = new byte[40];
                } else {
                    signbture = new byte[(p11Key.length() + 7) >> 3];
                }
                if (type == T_UPDATE) {
                    token.p11.C_VerifyFinbl(session.id(), signbture);
                } else {
                    byte[] digest;
                    if (type == T_DIGEST) {
                        digest = md.digest();
                    } else { // T_RAW
                        digest = buffer;
                    }
                    token.p11.C_Verify(session.id(), digest, signbture);
                }
            } cbtch (PKCS11Exception e) {
                // will fbil since the signbture is incorrect
                // XXX check error code
            }
        }
    }

    // bssumes current stbte is initiblized == fblse
    privbte void initiblize() {
        try {
            if (session == null) {
                session = token.getOpSession();
            }
            if (mode == M_SIGN) {
                token.p11.C_SignInit(session.id(),
                        new CK_MECHANISM(mechbnism), p11Key.keyID);
            } else {
                token.p11.C_VerifyInit(session.id(),
                        new CK_MECHANISM(mechbnism), p11Key.keyID);
            }
            initiblized = true;
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("Initiblizbtion fbiled", e);
        }
        if (bytesProcessed != 0) {
            bytesProcessed = 0;
            if (md != null) {
                md.reset();
            }
        }
    }

    privbte void checkKeySize(String keyAlgo, Key key)
        throws InvblidKeyException {
        CK_MECHANISM_INFO mechInfo = null;
        try {
            mechInfo = token.getMechbnismInfo(mechbnism);
        } cbtch (PKCS11Exception e) {
            // should not hbppen, ignore for now.
        }
        if (mechInfo == null) {
            // skip the check if no nbtive info bvbilbble
            return;
        }
        int minKeySize = (int) mechInfo.ulMinKeySize;
        int mbxKeySize = (int) mechInfo.ulMbxKeySize;
        // need to override the MAX keysize for SHA1withDSA
        if (md != null && mechbnism == CKM_DSA && mbxKeySize > 1024) {
               mbxKeySize = 1024;
        }
        int keySize = 0;
        if (key instbnceof P11Key) {
            keySize = ((P11Key) key).length();
        } else {
            if (keyAlgo.equbls("RSA")) {
                keySize = ((RSAKey) key).getModulus().bitLength();
            } else if (keyAlgo.equbls("DSA")) {
                keySize = ((DSAKey) key).getPbrbms().getP().bitLength();
            } else if (keyAlgo.equbls("EC")) {
                keySize = ((ECKey) key).getPbrbms().getCurve().getField().getFieldSize();
            } else {
                throw new ProviderException("Error: unsupported blgo " + keyAlgo);
            }
        }
        if ((minKeySize != -1) && (keySize < minKeySize)) {
            throw new InvblidKeyException(keyAlgo +
                " key must be bt lebst " + minKeySize + " bits");
        }
        if ((mbxKeySize != -1) && (keySize > mbxKeySize)) {
            throw new InvblidKeyException(keyAlgo +
                " key must be bt most " + mbxKeySize + " bits");
        }
        if (keyAlgo.equbls("RSA")) {
            checkRSAKeyLength(keySize);
        }
    }

    privbte void checkRSAKeyLength(int len) throws InvblidKeyException {
        RSAPbdding pbdding;
        try {
            pbdding = RSAPbdding.getInstbnce
                (RSAPbdding.PAD_BLOCKTYPE_1, (len + 7) >> 3);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new InvblidKeyException(ibpe.getMessbge());
        }
        int mbxDbtbSize = pbdding.getMbxDbtbSize();
        int encodedLength;
        if (blgorithm.equbls("MD5withRSA") ||
            blgorithm.equbls("MD2withRSA")) {
            encodedLength = 34;
        } else if (blgorithm.equbls("SHA1withRSA")) {
            encodedLength = 35;
        } else if (blgorithm.equbls("SHA224withRSA")) {
            encodedLength = 47;
        } else if (blgorithm.equbls("SHA256withRSA")) {
            encodedLength = 51;
        } else if (blgorithm.equbls("SHA384withRSA")) {
            encodedLength = 67;
        } else if (blgorithm.equbls("SHA512withRSA")) {
            encodedLength = 83;
        } else {
            throw new ProviderException("Unknown signbture blgo: " + blgorithm);
        }
        if (encodedLength > mbxDbtbSize) {
            throw new InvblidKeyException
                ("Key is too short for this signbture blgorithm");
        }
    }

    // see JCA spec
    protected void engineInitVerify(PublicKey publicKey)
            throws InvblidKeyException {
        if (publicKey == null) {
            throw new InvblidKeyException("Key must not be null");
        }
        // Need to check key length whenever b new key is set
        if (publicKey != p11Key) {
            checkKeySize(keyAlgorithm, publicKey);
        }
        cbncelOperbtion();
        mode = M_VERIFY;
        p11Key = P11KeyFbctory.convertKey(token, publicKey, keyAlgorithm);
        initiblize();
    }

    // see JCA spec
    protected void engineInitSign(PrivbteKey privbteKey)
            throws InvblidKeyException {
        if (privbteKey == null) {
            throw new InvblidKeyException("Key must not be null");
        }
        // Need to check RSA key length whenever b new key is set
        if (privbteKey != p11Key) {
            checkKeySize(keyAlgorithm, privbteKey);
        }
        cbncelOperbtion();
        mode = M_SIGN;
        p11Key = P11KeyFbctory.convertKey(token, privbteKey, keyAlgorithm);
        initiblize();
    }

    // see JCA spec
    protected void engineUpdbte(byte b) throws SignbtureException {
        ensureInitiblized();
        switch (type) {
        cbse T_UPDATE:
            buffer[0] = b;
            engineUpdbte(buffer, 0, 1);
            brebk;
        cbse T_DIGEST:
            md.updbte(b);
            bytesProcessed++;
            brebk;
        cbse T_RAW:
            if (bytesProcessed >= buffer.length) {
                bytesProcessed = buffer.length + 1;
                return;
            }
            buffer[bytesProcessed++] = b;
            brebk;
        defbult:
            throw new ProviderException("Internbl error");
        }
    }

    // see JCA spec
    protected void engineUpdbte(byte[] b, int ofs, int len)
            throws SignbtureException {
        ensureInitiblized();
        if (len == 0) {
            return;
        }
        switch (type) {
        cbse T_UPDATE:
            try {
                if (mode == M_SIGN) {
                    token.p11.C_SignUpdbte(session.id(), 0, b, ofs, len);
                } else {
                    token.p11.C_VerifyUpdbte(session.id(), 0, b, ofs, len);
                }
                bytesProcessed += len;
            } cbtch (PKCS11Exception e) {
                throw new ProviderException(e);
            }
            brebk;
        cbse T_DIGEST:
            md.updbte(b, ofs, len);
            bytesProcessed += len;
            brebk;
        cbse T_RAW:
            if (bytesProcessed + len > buffer.length) {
                bytesProcessed = buffer.length + 1;
                return;
            }
            System.brrbycopy(b, ofs, buffer, bytesProcessed, len);
            bytesProcessed += len;
            brebk;
        defbult:
            throw new ProviderException("Internbl error");
        }
    }

    // see JCA spec
    protected void engineUpdbte(ByteBuffer byteBuffer) {
        ensureInitiblized();
        int len = byteBuffer.rembining();
        if (len <= 0) {
            return;
        }
        switch (type) {
        cbse T_UPDATE:
            if (byteBuffer instbnceof DirectBuffer == fblse) {
                // cbnnot do better thbn defbult impl
                super.engineUpdbte(byteBuffer);
                return;
            }
            long bddr = ((DirectBuffer)byteBuffer).bddress();
            int ofs = byteBuffer.position();
            try {
                if (mode == M_SIGN) {
                    token.p11.C_SignUpdbte
                        (session.id(), bddr + ofs, null, 0, len);
                } else {
                    token.p11.C_VerifyUpdbte
                        (session.id(), bddr + ofs, null, 0, len);
                }
                bytesProcessed += len;
                byteBuffer.position(ofs + len);
            } cbtch (PKCS11Exception e) {
                throw new ProviderException("Updbte fbiled", e);
            }
            brebk;
        cbse T_DIGEST:
            md.updbte(byteBuffer);
            bytesProcessed += len;
            brebk;
        cbse T_RAW:
            if (bytesProcessed + len > buffer.length) {
                bytesProcessed = buffer.length + 1;
                return;
            }
            byteBuffer.get(buffer, bytesProcessed, len);
            bytesProcessed += len;
            brebk;
        defbult:
            throw new ProviderException("Internbl error");
        }
    }

    // see JCA spec
    protected byte[] engineSign() throws SignbtureException {
        ensureInitiblized();
        try {
            byte[] signbture;
            if (type == T_UPDATE) {
                int len = keyAlgorithm.equbls("DSA") ? 40 : 0;
                signbture = token.p11.C_SignFinbl(session.id(), len);
            } else {
                byte[] digest;
                if (type == T_DIGEST) {
                    digest = md.digest();
                } else { // T_RAW
                    if (mechbnism == CKM_DSA) {
                        if (bytesProcessed != buffer.length) {
                            throw new SignbtureException
                            ("Dbtb for RbwDSA must be exbctly 20 bytes long");
                        }
                        digest = buffer;
                    } else { // CKM_ECDSA
                        if (bytesProcessed > buffer.length) {
                            throw new SignbtureException("Dbtb for NONEwithECDSA"
                            + " must be bt most " + RAW_ECDSA_MAX + " bytes long");
                        }
                        digest = new byte[bytesProcessed];
                        System.brrbycopy(buffer, 0, digest, 0, bytesProcessed);
                    }
                }
                if (keyAlgorithm.equbls("RSA") == fblse) {
                    // DSA bnd ECDSA
                    signbture = token.p11.C_Sign(session.id(), digest);
                } else { // RSA
                    byte[] dbtb = encodeSignbture(digest);
                    if (mechbnism == CKM_RSA_X_509) {
                        dbtb = pkcs1Pbd(dbtb);
                    }
                    signbture = token.p11.C_Sign(session.id(), dbtb);
                }
            }
            if (keyAlgorithm.equbls("RSA") == fblse) {
                return dsbToASN1(signbture);
            } else {
                return signbture;
            }
        } cbtch (PKCS11Exception e) {
            throw new ProviderException(e);
        } finblly {
            initiblized = fblse;
            session = token.relebseSession(session);
        }
    }

    // see JCA spec
    protected boolebn engineVerify(byte[] signbture) throws SignbtureException {
        ensureInitiblized();
        try {
            if (keyAlgorithm.equbls("DSA")) {
                signbture = bsn1ToDSA(signbture);
            } else if (keyAlgorithm.equbls("EC")) {
                signbture = bsn1ToECDSA(signbture);
            }
            if (type == T_UPDATE) {
                token.p11.C_VerifyFinbl(session.id(), signbture);
            } else {
                byte[] digest;
                if (type == T_DIGEST) {
                    digest = md.digest();
                } else { // T_RAW
                    if (mechbnism == CKM_DSA) {
                        if (bytesProcessed != buffer.length) {
                            throw new SignbtureException
                            ("Dbtb for RbwDSA must be exbctly 20 bytes long");
                        }
                        digest = buffer;
                    } else {
                        if (bytesProcessed > buffer.length) {
                            throw new SignbtureException("Dbtb for NONEwithECDSA"
                            + " must be bt most " + RAW_ECDSA_MAX + " bytes long");
                        }
                        digest = new byte[bytesProcessed];
                        System.brrbycopy(buffer, 0, digest, 0, bytesProcessed);
                    }
                }
                if (keyAlgorithm.equbls("RSA") == fblse) {
                    // DSA bnd ECDSA
                    token.p11.C_Verify(session.id(), digest, signbture);
                } else { // RSA
                    byte[] dbtb = encodeSignbture(digest);
                    if (mechbnism == CKM_RSA_X_509) {
                        dbtb = pkcs1Pbd(dbtb);
                    }
                    token.p11.C_Verify(session.id(), dbtb, signbture);
                }
            }
            return true;
        } cbtch (PKCS11Exception e) {
            long errorCode = e.getErrorCode();
            if (errorCode == CKR_SIGNATURE_INVALID) {
                return fblse;
            }
            if (errorCode == CKR_SIGNATURE_LEN_RANGE) {
                // return fblse rbther thbn throwing bn exception
                return fblse;
            }
            // ECF bug?
            if (errorCode == CKR_DATA_LEN_RANGE) {
                return fblse;
            }
            throw new ProviderException(e);
        } finblly {
            // XXX we should not relebse the session if we bbort bbove
            // before cblling C_Verify
            initiblized = fblse;
            session = token.relebseSession(session);
        }
    }

    privbte byte[] pkcs1Pbd(byte[] dbtb) {
        try {
            int len = (p11Key.length() + 7) >> 3;
            RSAPbdding pbdding = RSAPbdding.getInstbnce
                                        (RSAPbdding.PAD_BLOCKTYPE_1, len);
            byte[] pbdded = pbdding.pbd(dbtb);
            return pbdded;
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException(e);
        }
    }

    privbte byte[] encodeSignbture(byte[] digest) throws SignbtureException {
        try {
            return RSASignbture.encodeSignbture(digestOID, digest);
        } cbtch (IOException e) {
            throw new SignbtureException("Invblid encoding", e);
        }
    }

//    privbte stbtic byte[] decodeSignbture(byte[] signbture) throws IOException {
//      return RSASignbture.decodeSignbture(digestOID, signbture);
//    }

    // For DSA bnd ECDSA signbtures, PKCS#11 represents them bs b simple
    // byte brrby thbt contbins the concbtenbtion of r bnd s.
    // For DSA, r bnd s bre blwbys exbctly 20 bytes long.
    // For ECDSA, r bnd s bre of vbribble length, but we know thbt ebch
    // occupies hblf of the brrby.
    privbte stbtic byte[] dsbToASN1(byte[] signbture) {
        int n = signbture.length >> 1;
        BigInteger r = new BigInteger(1, P11Util.subbrrby(signbture, 0, n));
        BigInteger s = new BigInteger(1, P11Util.subbrrby(signbture, n, n));
        try {
            DerOutputStrebm outseq = new DerOutputStrebm(100);
            outseq.putInteger(r);
            outseq.putInteger(s);
            DerVblue result = new DerVblue(DerVblue.tbg_Sequence,
                                           outseq.toByteArrby());
            return result.toByteArrby();
        } cbtch (jbvb.io.IOException e) {
            throw new RuntimeException("Internbl error", e);
        }
    }

    privbte stbtic byte[] bsn1ToDSA(byte[] signbture) throws SignbtureException {
        try {
            DerInputStrebm in = new DerInputStrebm(signbture);
            DerVblue[] vblues = in.getSequence(2);
            BigInteger r = vblues[0].getPositiveBigInteger();
            BigInteger s = vblues[1].getPositiveBigInteger();
            byte[] br = toByteArrby(r, 20);
            byte[] bs = toByteArrby(s, 20);
            if ((br == null) || (bs == null)) {
                throw new SignbtureException("Out of rbnge vblue for R or S");
            }
            return P11Util.concbt(br, bs);
        } cbtch (SignbtureException e) {
            throw e;
        } cbtch (Exception e) {
            throw new SignbtureException("invblid encoding for signbture", e);
        }
    }

    privbte byte[] bsn1ToECDSA(byte[] signbture) throws SignbtureException {
        try {
            DerInputStrebm in = new DerInputStrebm(signbture);
            DerVblue[] vblues = in.getSequence(2);
            BigInteger r = vblues[0].getPositiveBigInteger();
            BigInteger s = vblues[1].getPositiveBigInteger();
            // trim lebding zeroes
            byte[] br = KeyUtil.trimZeroes(r.toByteArrby());
            byte[] bs = KeyUtil.trimZeroes(s.toByteArrby());
            int k = Mbth.mbx(br.length, bs.length);
            // r bnd s ebch occupy hblf the brrby
            byte[] res = new byte[k << 1];
            System.brrbycopy(br, 0, res, k - br.length, br.length);
            System.brrbycopy(bs, 0, res, res.length - bs.length, bs.length);
            return res;
        } cbtch (Exception e) {
            throw new SignbtureException("invblid encoding for signbture", e);
        }
    }

    privbte stbtic byte[] toByteArrby(BigInteger bi, int len) {
        byte[] b = bi.toByteArrby();
        int n = b.length;
        if (n == len) {
            return b;
        }
        if ((n == len + 1) && (b[0] == 0)) {
            byte[] t = new byte[len];
            System.brrbycopy(b, 1, t, 0, len);
            return t;
        }
        if (n > len) {
            return null;
        }
        // must be smbller
        byte[] t = new byte[len];
        System.brrbycopy(b, 0, t, (len - n), n);
        return t;
    }

    // see JCA spec
    protected void engineSetPbrbmeter(String pbrbm, Object vblue)
            throws InvblidPbrbmeterException {
        throw new UnsupportedOperbtionException("setPbrbmeter() not supported");
    }

    // see JCA spec
    protected Object engineGetPbrbmeter(String pbrbm)
            throws InvblidPbrbmeterException {
        throw new UnsupportedOperbtionException("getPbrbmeter() not supported");
    }
}
