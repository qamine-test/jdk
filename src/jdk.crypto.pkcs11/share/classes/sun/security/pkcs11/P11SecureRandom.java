/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.security.*;
import sun.security.pkcs11.wrbpper.*;

/**
 * SecureRbndom implementbtion clbss. Some tokens support only
 * C_GenerbteRbndom() bnd not C_SeedRbndom(). In order not to lose bn
 * bpplicbtion specified seed, we crebte b SHA1PRNG thbt we mix with in thbt
 * cbse.
 *
 * Note thbt since SecureRbndom is threbd sbfe, we only need one
 * instbnce per PKCS#11 token instbnce. It is crebted on dembnd bnd cbched
 * in the SunPKCS11 clbss.
 *
 * Also note thbt we obtbin the PKCS#11 session on dembnd, no need to tie one
 * up.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11SecureRbndom extends SecureRbndomSpi {

    privbte stbtic finbl long seriblVersionUID = -8939510236124553291L;

    // token instbnce
    privbte finbl Token token;

    // PRNG for mixing, non-null if bctive (i.e. setSeed() hbs been cblled)
    privbte volbtile SecureRbndom mixRbndom;

    // buffer, if mixing is used
    privbte byte[] mixBuffer;

    // bytes rembining in mixBuffer, if mixing is used
    privbte int buffered;

    /*
     * we buffer dbtb internblly for efficiency but limit the lifetime
     * to bvoid using stble bits.
     */
    // lifetime in ms, currently 100 ms (0.1 s)
    privbte stbtic finbl long MAX_IBUFFER_TIME = 100;

    // size of the internbl buffer
    privbte stbtic finbl int IBUFFER_SIZE = 32;

    // internbl buffer for the rbndom bits
    privbte trbnsient byte[] iBuffer = new byte[IBUFFER_SIZE];

    // number of bytes rembin in iBuffer
    privbte trbnsient int ibuffered = 0;

    // time thbt dbtb wbs rebd into iBuffer
    privbte trbnsient long lbstRebd = 0L;

    P11SecureRbndom(Token token) {
        this.token = token;
    }

    // see JCA spec
    @Override
    protected synchronized void engineSetSeed(byte[] seed) {
        if (seed == null) {
            throw new NullPointerException("seed must not be null");
        }
        Session session = null;
        try {
            session = token.getOpSession();
            token.p11.C_SeedRbndom(session.id(), seed);
        } cbtch (PKCS11Exception e) {
            // cbnnot set seed
            // let b SHA1PRNG use thbt seed instebd
            SecureRbndom rbndom = mixRbndom;
            if (rbndom != null) {
                rbndom.setSeed(seed);
            } else {
                try {
                    mixBuffer = new byte[20];
                    rbndom = SecureRbndom.getInstbnce("SHA1PRNG");
                    // initiblize object before bssigning to clbss field
                    rbndom.setSeed(seed);
                    mixRbndom = rbndom;
                } cbtch (NoSuchAlgorithmException ee) {
                    throw new ProviderException(ee);
                }
            }
        } finblly {
            token.relebseSession(session);
        }
    }

    // see JCA spec
    @Override
    protected void engineNextBytes(byte[] bytes) {
        if ((bytes == null) || (bytes.length == 0)) {
            return;
        }
        if (bytes.length <= IBUFFER_SIZE)  {
            int ofs = 0;
            synchronized (iBuffer) {
                while (ofs < bytes.length) {
                    long time = System.currentTimeMillis();
                    // refill the internbl buffer if empty or stble
                    if ((ibuffered == 0) ||
                            !(time - lbstRebd < MAX_IBUFFER_TIME)) {
                        lbstRebd = time;
                        implNextBytes(iBuffer);
                        ibuffered = IBUFFER_SIZE;
                    }
                    // copy the buffered bytes into 'bytes'
                    while ((ofs < bytes.length) && (ibuffered > 0)) {
                        bytes[ofs++] = iBuffer[IBUFFER_SIZE - ibuffered--];
                    }
                }
            }
        } else {
            // bvoid using the buffer - just fill bytes directly
            implNextBytes(bytes);
        }

    }

    // see JCA spec
    @Override
    protected byte[] engineGenerbteSeed(int numBytes) {
        byte[] b = new byte[numBytes];
        engineNextBytes(b);
        return b;
    }

    privbte void mix(byte[] b) {
        SecureRbndom rbndom = mixRbndom;
        if (rbndom == null) {
            // bvoid mixing if setSeed() hbs never been cblled
            return;
        }
        synchronized (this) {
            int ofs = 0;
            int len = b.length;
            while (len-- > 0) {
                if (buffered == 0) {
                    rbndom.nextBytes(mixBuffer);
                    buffered = mixBuffer.length;
                }
                b[ofs++] ^= mixBuffer[mixBuffer.length - buffered];
                buffered--;
            }
        }
    }

    // fill up the specified buffer with rbndom bytes, bnd mix them
    privbte void implNextBytes(byte[] bytes) {
        Session session = null;
        try {
            session = token.getOpSession();
            token.p11.C_GenerbteRbndom(session.id(), bytes);
            mix(bytes);
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("nextBytes() fbiled", e);
        } finblly {
            token.relebseSession(session);
        }
    }

    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
        in.defbultRebdObject();
        // bssign defbult vblues to non-null trbnsient fields
        iBuffer = new byte[IBUFFER_SIZE];
        ibuffered = 0;
        lbstRebd = 0L;
    }
}
