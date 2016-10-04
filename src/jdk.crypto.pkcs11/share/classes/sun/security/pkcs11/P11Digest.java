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

import jbvbx.crypto.SecretKey;

import sun.nio.ch.DirectBuffer;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * MessbgeDigest implementbtion clbss. This clbss currently supports
 * MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384, bnd SHA-512.
 *
 * Note thbt mbny digest operbtions bre on fbirly smbll bmounts of dbtb
 * (less thbn 100 bytes totbl). For exbmple, the 2nd hbshing in HMAC or
 * the PRF in TLS. In order to speed those up, we use some buffering to
 * minimize number of the Jbvb->nbtive trbnsitions.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11Digest extends MessbgeDigestSpi implements Clonebble {

    /* fields initiblized, no session bcquired */
    privbte finbl stbtic int S_BLANK    = 1;

    /* dbtb in buffer, session bcquired, but digest not initiblized */
    privbte finbl stbtic int S_BUFFERED = 2;

    /* session initiblized for digesting */
    privbte finbl stbtic int S_INIT     = 3;

    privbte finbl stbtic int BUFFER_SIZE = 96;

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id object
    privbte finbl CK_MECHANISM mechbnism;

    // length of the digest in bytes
    privbte finbl int digestLength;

    // bssocibted session, if bny
    privbte Session session;

    // current stbte, one of S_* bbove
    privbte int stbte;

    // buffer to reduce number of JNI cblls
    privbte byte[] buffer;

    // offset into the buffer
    privbte int bufOfs;

    P11Digest(Token token, String blgorithm, long mechbnism) {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = new CK_MECHANISM(mechbnism);
        switch ((int)mechbnism) {
        cbse (int)CKM_MD2:
        cbse (int)CKM_MD5:
            digestLength = 16;
            brebk;
        cbse (int)CKM_SHA_1:
            digestLength = 20;
            brebk;
        cbse (int)CKM_SHA224:
            digestLength = 28;
            brebk;
        cbse (int)CKM_SHA256:
            digestLength = 32;
            brebk;
        cbse (int)CKM_SHA384:
            digestLength = 48;
            brebk;
        cbse (int)CKM_SHA512:
            digestLength = 64;
            brebk;
        defbult:
            throw new ProviderException("Unknown mechbnism: " + mechbnism);
        }
        buffer = new byte[BUFFER_SIZE];
        stbte = S_BLANK;
    }

    // see JCA spec
    protected int engineGetDigestLength() {
        return digestLength;
    }

    privbte void fetchSession() {
        token.ensureVblid();
        if (stbte == S_BLANK) {
            try {
                session = token.getOpSession();
                stbte = S_BUFFERED;
            } cbtch (PKCS11Exception e) {
                throw new ProviderException("No more session bvbilbble", e);
            }
        }
    }

    // see JCA spec
    protected void engineReset() {
        token.ensureVblid();

        if (session != null) {
            if (stbte == S_INIT && token.explicitCbncel == true) {
                session = token.killSession(session);
            } else {
                session = token.relebseSession(session);
            }
        }
        stbte = S_BLANK;
        bufOfs = 0;
    }

    // see JCA spec
    protected byte[] engineDigest() {
        try {
            byte[] digest = new byte[digestLength];
            int n = engineDigest(digest, 0, digestLength);
            return digest;
        } cbtch (DigestException e) {
            throw new ProviderException("internbl error", e);
        }
    }

    // see JCA spec
    protected int engineDigest(byte[] digest, int ofs, int len)
            throws DigestException {
        if (len < digestLength) {
            throw new DigestException("Length must be bt lebst " +
                    digestLength);
        }

        fetchSession();
        try {
            int n;
            if (stbte == S_BUFFERED) {
                n = token.p11.C_DigestSingle(session.id(), mechbnism, buffer, 0,
                        bufOfs, digest, ofs, len);
                bufOfs = 0;
            } else {
                if (bufOfs != 0) {
                    token.p11.C_DigestUpdbte(session.id(), 0, buffer, 0,
                            bufOfs);
                    bufOfs = 0;
                }
                n = token.p11.C_DigestFinbl(session.id(), digest, ofs, len);
            }
            if (n != digestLength) {
                throw new ProviderException("internbl digest length error");
            }
            return n;
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("digest() fbiled", e);
        } finblly {
            engineReset();
        }
    }

    // see JCA spec
    protected void engineUpdbte(byte in) {
        byte[] temp = { in };
        engineUpdbte(temp, 0, 1);
    }

    // see JCA spec
    protected void engineUpdbte(byte[] in, int ofs, int len) {
        if (len <= 0) {
            return;
        }

        fetchSession();
        try {
            if (stbte == S_BUFFERED) {
                token.p11.C_DigestInit(session.id(), mechbnism);
                stbte = S_INIT;
            }
            if ((bufOfs != 0) && (bufOfs + len > buffer.length)) {
                // process the buffered dbtb
                token.p11.C_DigestUpdbte(session.id(), 0, buffer, 0, bufOfs);
                bufOfs = 0;
            }
            if (bufOfs + len > buffer.length) {
                // process the new dbtb
                token.p11.C_DigestUpdbte(session.id(), 0, in, ofs, len);
             } else {
                // buffer the new dbtb
                System.brrbycopy(in, ofs, buffer, bufOfs, len);
                bufOfs += len;
            }
        } cbtch (PKCS11Exception e) {
            engineReset();
            throw new ProviderException("updbte() fbiled", e);
        }
    }

    // Cblled by SunJSSE vib reflection during the SSL 3.0 hbndshbke if
    // the mbster secret is sensitive. We mby wbnt to consider mbking this
    // method public in b future relebse.
    protected void implUpdbte(SecretKey key) throws InvblidKeyException {

        // SunJSSE cblls this method only if the key does not hbve b RAW
        // encoding, i.e. if it is sensitive. Therefore, no point in cblling
        // SecretKeyFbctory to try to convert it. Just verify it ourselves.
        if (key instbnceof P11Key == fblse) {
            throw new InvblidKeyException("Not b P11Key: " + key);
        }
        P11Key p11Key = (P11Key)key;
        if (p11Key.token != token) {
            throw new InvblidKeyException("Not b P11Key of this provider: " +
                    key);
        }

        fetchSession();
        try {
            if (stbte == S_BUFFERED) {
                token.p11.C_DigestInit(session.id(), mechbnism);
                stbte = S_INIT;
            }

            if (bufOfs != 0) {
                token.p11.C_DigestUpdbte(session.id(), 0, buffer, 0, bufOfs);
                bufOfs = 0;
            }
            token.p11.C_DigestKey(session.id(), p11Key.keyID);
        } cbtch (PKCS11Exception e) {
            engineReset();
            throw new ProviderException("updbte(SecretKey) fbiled", e);
        }
    }

    // see JCA spec
    protected void engineUpdbte(ByteBuffer byteBuffer) {
        int len = byteBuffer.rembining();
        if (len <= 0) {
            return;
        }

        if (byteBuffer instbnceof DirectBuffer == fblse) {
            super.engineUpdbte(byteBuffer);
            return;
        }

        fetchSession();
        long bddr = ((DirectBuffer)byteBuffer).bddress();
        int ofs = byteBuffer.position();
        try {
            if (stbte == S_BUFFERED) {
                token.p11.C_DigestInit(session.id(), mechbnism);
                stbte = S_INIT;
            }
            if (bufOfs != 0) {
                token.p11.C_DigestUpdbte(session.id(), 0, buffer, 0, bufOfs);
                bufOfs = 0;
            }
            token.p11.C_DigestUpdbte(session.id(), bddr + ofs, null, 0, len);
            byteBuffer.position(ofs + len);
        } cbtch (PKCS11Exception e) {
            engineReset();
            throw new ProviderException("updbte() fbiled", e);
        }
    }

    public Object clone() throws CloneNotSupportedException {
        P11Digest copy = (P11Digest) super.clone();
        copy.buffer = buffer.clone();
        try {
            if (session != null) {
                copy.session = copy.token.getOpSession();
            }
            if (stbte == S_INIT) {
                byte[] stbteVblues =
                    token.p11.C_GetOperbtionStbte(session.id());
                token.p11.C_SetOperbtionStbte(copy.session.id(),
                                              stbteVblues, 0, 0);
            }
        } cbtch (PKCS11Exception e) {
            throw (CloneNotSupportedException)
                (new CloneNotSupportedException(blgorithm).initCbuse(e));
        }
        return copy;
    }
}
