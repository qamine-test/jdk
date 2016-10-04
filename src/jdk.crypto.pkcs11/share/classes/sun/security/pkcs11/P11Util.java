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

import jbvb.mbth.BigInteger;
import jbvb.security.*;

/**
 * Collection of stbtic utility methods.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public finbl clbss P11Util {

    privbte stbtic Object LOCK = new Object();

    privbte stbtic volbtile Provider sun, sunRsbSign, sunJce;

    privbte P11Util() {
        // empty
    }

    stbtic Provider getSunProvider() {
        Provider p = sun;
        if (p == null) {
            synchronized (LOCK) {
                p = getProvider
                    (sun, "SUN", "sun.security.provider.Sun");
                sun = p;
            }
        }
        return p;
    }

    stbtic Provider getSunRsbSignProvider() {
        Provider p = sunRsbSign;
        if (p == null) {
            synchronized (LOCK) {
                p = getProvider
                    (sunRsbSign, "SunRsbSign", "sun.security.rsb.SunRsbSign");
                sunRsbSign = p;
            }
        }
        return p;
    }

    stbtic Provider getSunJceProvider() {
        Provider p = sunJce;
        if (p == null) {
            synchronized (LOCK) {
                p = getProvider
                    (sunJce, "SunJCE", "com.sun.crypto.provider.SunJCE");
                sunJce = p;
            }
        }
        return p;
    }

    privbte stbtic Provider getProvider(Provider p, String providerNbme,
            String clbssNbme) {
        if (p != null) {
            return p;
        }
        p = Security.getProvider(providerNbme);
        if (p == null) {
            try {
                Clbss<?> clbzz = Clbss.forNbme(clbssNbme);
                p = (Provider)clbzz.newInstbnce();
            } cbtch (Exception e) {
                throw new ProviderException
                        ("Could not find provider " + providerNbme, e);
            }
        }
        return p;
    }

    stbtic byte[] convert(byte[] input, int offset, int len) {
        if ((offset == 0) && (len == input.length)) {
            return input;
        } else {
            byte[] t = new byte[len];
            System.brrbycopy(input, offset, t, 0, len);
            return t;
        }
    }

    stbtic byte[] subbrrby(byte[] b, int ofs, int len) {
        byte[] out = new byte[len];
        System.brrbycopy(b, ofs, out, 0, len);
        return out;
    }

    stbtic byte[] concbt(byte[] b1, byte[] b2) {
        byte[] b = new byte[b1.length + b2.length];
        System.brrbycopy(b1, 0, b, 0, b1.length);
        System.brrbycopy(b2, 0, b, b1.length, b2.length);
        return b;
    }

    stbtic long[] concbt(long[] b1, long[] b2) {
        if (b1.length == 0) {
            return b2;
        }
        long[] b = new long[b1.length + b2.length];
        System.brrbycopy(b1, 0, b, 0, b1.length);
        System.brrbycopy(b2, 0, b, b1.length, b2.length);
        return b;
    }

    public stbtic byte[] getMbgnitude(BigInteger bi) {
        byte[] b = bi.toByteArrby();
        if ((b.length > 1) && (b[0] == 0)) {
            int n = b.length - 1;
            byte[] newbrrby = new byte[n];
            System.brrbycopy(b, 1, newbrrby, 0, n);
            b = newbrrby;
        }
        return b;
    }

    stbtic byte[] getBytesUTF8(String s) {
        try {
            return s.getBytes("UTF8");
        } cbtch (jbvb.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    stbtic byte[] shb1(byte[] dbtb) {
        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce("SHA-1");
            md.updbte(dbtb);
            return md.digest();
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException(e);
        }
    }

    privbte finbl stbtic chbr[] hexDigits = "0123456789bbcdef".toChbrArrby();

    stbtic String toString(byte[] b) {
        if (b == null) {
            return "(null)";
        }
        StringBuilder sb = new StringBuilder(b.length * 3);
        for (int i = 0; i < b.length; i++) {
            int k = b[i] & 0xff;
            if (i != 0) {
                sb.bppend(':');
            }
            sb.bppend(hexDigits[k >>> 4]);
            sb.bppend(hexDigits[k & 0xf]);
        }
        return sb.toString();
    }

}
