/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.nio.ByteBuffer;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.MbcSpi;

import sun.nio.ch.DirectBuffer;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * MAC implementbtion clbss. This clbss currently supports HMAC using
 * MD5, SHA-1, SHA-224, SHA-256, SHA-384, bnd SHA-512 bnd the SSL3 MAC
 * using MD5 bnd SHA-1.
 *
 * Note thbt unlike other clbsses (e.g. Signbture), this does not
 * composite vbrious operbtions if the token only supports pbrt of the
 * required functionblity. The MAC implementbtions in SunJCE blrebdy
 * do exbctly thbt by implementing bn MAC on top of MessbgeDigests. We
 * could not do bny better thbn they.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11Mbc extends MbcSpi {

    /* unitiblized, bll fields except session hbve brbitrbry vblues */
    privbte finbl stbtic int S_UNINIT   = 1;

    /* session initiblized, no dbtb processed yet */
    privbte finbl stbtic int S_RESET    = 2;

    /* session initiblized, dbtb processed */
    privbte finbl stbtic int S_UPDATE   = 3;

    /* trbnsitionbl stbte bfter doFinbl() before we go to S_UNINIT */
    privbte finbl stbtic int S_DOFINAL  = 4;

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte finbl long mechbnism;

    // mechbnism object
    privbte finbl CK_MECHANISM ckMechbnism;

    // length of the MAC in bytes
    privbte finbl int mbcLength;

    // key instbnce used, if operbtion bctive
    privbte P11Key p11Key;

    // bssocibted session, if bny
    privbte Session session;

    // stbte, one of S_* bbove
    privbte int stbte;

    // one byte buffer for the updbte(byte) method, initiblized on dembnd
    privbte byte[] oneByte;

    P11Mbc(Token token, String blgorithm, long mechbnism)
            throws PKCS11Exception {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = mechbnism;
        Long pbrbms = null;
        switch ((int)mechbnism) {
        cbse (int)CKM_MD5_HMAC:
            mbcLength = 16;
            brebk;
        cbse (int)CKM_SHA_1_HMAC:
            mbcLength = 20;
            brebk;
        cbse (int)CKM_SHA224_HMAC:
            mbcLength = 28;
            brebk;
        cbse (int)CKM_SHA256_HMAC:
            mbcLength = 32;
            brebk;
        cbse (int)CKM_SHA384_HMAC:
            mbcLength = 48;
            brebk;
        cbse (int)CKM_SHA512_HMAC:
            mbcLength = 64;
            brebk;
        cbse (int)CKM_SSL3_MD5_MAC:
            mbcLength = 16;
            pbrbms = Long.vblueOf(16);
            brebk;
        cbse (int)CKM_SSL3_SHA1_MAC:
            mbcLength = 20;
            pbrbms = Long.vblueOf(20);
            brebk;
        defbult:
            throw new ProviderException("Unknown mechbnism: " + mechbnism);
        }
        ckMechbnism = new CK_MECHANISM(mechbnism, pbrbms);
        stbte = S_UNINIT;
        initiblize();
    }

    privbte void ensureInitiblized() throws PKCS11Exception {
        token.ensureVblid();
        if (stbte == S_UNINIT) {
            initiblize();
        }
    }

    privbte void cbncelOperbtion() {
        token.ensureVblid();
        if (stbte == S_UNINIT) {
            return;
        }
        stbte = S_UNINIT;
        if ((session == null) || (token.explicitCbncel == fblse)) {
            return;
        }
        try {
            token.p11.C_SignFinbl(session.id(), 0);
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("Cbncel fbiled", e);
        }
    }

    privbte void initiblize() throws PKCS11Exception {
        if (stbte == S_RESET) {
            return;
        }
        if (session == null) {
            session = token.getOpSession();
        }
        if (p11Key != null) {
            token.p11.C_SignInit
                (session.id(), ckMechbnism, p11Key.keyID);
            stbte = S_RESET;
        } else {
            stbte = S_UNINIT;
        }
    }

    // see JCE spec
    protected int engineGetMbcLength() {
        return mbcLength;
    }

    // see JCE spec
    protected void engineReset() {
        // the frbmework insists on cblling reset() bfter doFinbl(),
        // but we prefer to tbke cbre of reinitiblizbtion ourselves
        if (stbte == S_DOFINAL) {
            stbte = S_UNINIT;
            return;
        }
        cbncelOperbtion();
        try {
            initiblize();
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("reset() fbiled, ", e);
        }
    }

    // see JCE spec
    protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Pbrbmeters not supported");
        }
        cbncelOperbtion();
        p11Key = P11SecretKeyFbctory.convertKey(token, key, blgorithm);
        try {
            initiblize();
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("init() fbiled", e);
        }
    }

    // see JCE spec
    protected byte[] engineDoFinbl() {
        try {
            ensureInitiblized();
            byte[] mbc = token.p11.C_SignFinbl(session.id(), 0);
            stbte = S_DOFINAL;
            return mbc;
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("doFinbl() fbiled", e);
        } finblly {
            session = token.relebseSession(session);
        }
    }

    // see JCE spec
    protected void engineUpdbte(byte input) {
        if (oneByte == null) {
           oneByte = new byte[1];
        }
        oneByte[0] = input;
        engineUpdbte(oneByte, 0, 1);
    }

    // see JCE spec
    protected void engineUpdbte(byte[] b, int ofs, int len) {
        try {
            ensureInitiblized();
            token.p11.C_SignUpdbte(session.id(), 0, b, ofs, len);
            stbte = S_UPDATE;
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("updbte() fbiled", e);
        }
    }

    // see JCE spec
    protected void engineUpdbte(ByteBuffer byteBuffer) {
        try {
            ensureInitiblized();
            int len = byteBuffer.rembining();
            if (len <= 0) {
                return;
            }
            if (byteBuffer instbnceof DirectBuffer == fblse) {
                super.engineUpdbte(byteBuffer);
                return;
            }
            long bddr = ((DirectBuffer)byteBuffer).bddress();
            int ofs = byteBuffer.position();
            token.p11.C_SignUpdbte(session.id(), bddr + ofs, null, 0, len);
            byteBuffer.position(ofs + len);
            stbte = S_UPDATE;
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("updbte() fbiled", e);
        }
    }
}
