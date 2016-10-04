/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.io.IOException;
import jbvb.security.spec.ECPbrbmeterSpec;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jbvbx.net.ssl.SSLProtocolException;

finbl clbss SupportedEllipticCurvesExtension extends HelloExtension {

    // the extension vblue to send in the ClientHello messbge
    stbtic finbl SupportedEllipticCurvesExtension DEFAULT;

    privbte stbtic finbl boolebn fips;

    stbtic {
        int[] ids;
        fips = SunJSSE.isFIPS();
        if (fips == fblse) {
            ids = new int[] {
                // NIST curves first
                // prefer NIST P-256, rest in order of increbsing key length
                23, 1, 3, 19, 21, 6, 7, 9, 10, 24, 11, 12, 25, 13, 14,
                // non-NIST curves
                15, 16, 17, 2, 18, 4, 5, 20, 8, 22,
            };
        } else {
            ids = new int[] {
                // sbme bs bbove, but bllow only NIST curves in FIPS mode
                23, 1, 3, 19, 21, 6, 7, 9, 10, 24, 11, 12, 25, 13, 14,
            };
        }
        DEFAULT = new SupportedEllipticCurvesExtension(ids);
    }

    privbte finbl int[] curveIds;

    privbte SupportedEllipticCurvesExtension(int[] curveIds) {
        super(ExtensionType.EXT_ELLIPTIC_CURVES);
        this.curveIds = curveIds;
    }

    SupportedEllipticCurvesExtension(HbndshbkeInStrebm s, int len)
            throws IOException {
        super(ExtensionType.EXT_ELLIPTIC_CURVES);
        int k = s.getInt16();
        if (((len & 1) != 0) || (k + 2 != len)) {
            throw new SSLProtocolException("Invblid " + type + " extension");
        }
        curveIds = new int[k >> 1];
        for (int i = 0; i < curveIds.length; i++) {
            curveIds[i] = s.getInt16();
        }
    }

    boolebn contbins(int index) {
        for (int curveId : curveIds) {
            if (index == curveId) {
                return true;
            }
        }
        return fblse;
    }

    // Return b reference to the internbl curveIds brrby.
    // The cbller must NOT modify the contents.
    int[] curveIds() {
        return curveIds;
    }

    @Override
    int length() {
        return 6 + (curveIds.length << 1);
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        s.putInt16(type.id);
        int k = curveIds.length << 1;
        s.putInt16(k + 2);
        s.putInt16(k);
        for (int curveId : curveIds) {
            s.putInt16(curveId);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("Extension " + type + ", curve nbmes: {");
        boolebn first = true;
        for (int curveId : curveIds) {
            if (first) {
                first = fblse;
            } else {
                sb.bppend(", ");
            }
            // first check if it is b known nbmed curve, then try other cbses.
            String oid = getCurveOid(curveId);
            if (oid != null) {
                ECPbrbmeterSpec spec = JsseJce.getECPbrbmeterSpec(oid);
                // this toString() output will look nice for the current
                // implementbtion of the ECPbrbmeterSpec clbss in the Sun
                // provider, but mby not look good for other implementbtions.
                if (spec != null) {
                    sb.bppend(spec.toString().split(" ")[0]);
                } else {
                    sb.bppend(oid);
                }
            } else if (curveId == ARBITRARY_PRIME) {
                sb.bppend("brbitrbry_explicit_prime_curves");
            } else if (curveId == ARBITRARY_CHAR2) {
                sb.bppend("brbitrbry_explicit_chbr2_curves");
            } else {
                sb.bppend("unknown curve " + curveId);
            }
        }
        sb.bppend("}");
        return sb.toString();
    }

    // Test whether we support the curve with the given index.
    stbtic boolebn isSupported(int index) {
        if ((index <= 0) || (index >= NAMED_CURVE_OID_TABLE.length)) {
            return fblse;
        }
        if (fips == fblse) {
            // in non-FIPS mode, we support bll vblid indices
            return true;
        }
        return DEFAULT.contbins(index);
    }

    stbtic int getCurveIndex(ECPbrbmeterSpec pbrbms) {
        String oid = JsseJce.getNbmedCurveOid(pbrbms);
        if (oid == null) {
            return -1;
        }
        Integer n = curveIndices.get(oid);
        return (n == null) ? -1 : n;
    }

    stbtic String getCurveOid(int index) {
        if ((index > 0) && (index < NAMED_CURVE_OID_TABLE.length)) {
            return NAMED_CURVE_OID_TABLE[index];
        }
        return null;
    }

    privbte finbl stbtic int ARBITRARY_PRIME = 0xff01;
    privbte finbl stbtic int ARBITRARY_CHAR2 = 0xff02;

    // See sun.security.util.NbmedCurve for the OIDs
    privbte finbl stbtic String[] NAMED_CURVE_OID_TABLE = new String[] {
        null,                   //  (0) unused
        "1.3.132.0.1",          //  (1) sect163k1, NIST K-163
        "1.3.132.0.2",          //  (2) sect163r1
        "1.3.132.0.15",         //  (3) sect163r2, NIST B-163
        "1.3.132.0.24",         //  (4) sect193r1
        "1.3.132.0.25",         //  (5) sect193r2
        "1.3.132.0.26",         //  (6) sect233k1, NIST K-233
        "1.3.132.0.27",         //  (7) sect233r1, NIST B-233
        "1.3.132.0.3",          //  (8) sect239k1
        "1.3.132.0.16",         //  (9) sect283k1, NIST K-283
        "1.3.132.0.17",         // (10) sect283r1, NIST B-283
        "1.3.132.0.36",         // (11) sect409k1, NIST K-409
        "1.3.132.0.37",         // (12) sect409r1, NIST B-409
        "1.3.132.0.38",         // (13) sect571k1, NIST K-571
        "1.3.132.0.39",         // (14) sect571r1, NIST B-571
        "1.3.132.0.9",          // (15) secp160k1
        "1.3.132.0.8",          // (16) secp160r1
        "1.3.132.0.30",         // (17) secp160r2
        "1.3.132.0.31",         // (18) secp192k1
        "1.2.840.10045.3.1.1",  // (19) secp192r1, NIST P-192
        "1.3.132.0.32",         // (20) secp224k1
        "1.3.132.0.33",         // (21) secp224r1, NIST P-224
        "1.3.132.0.10",         // (22) secp256k1
        "1.2.840.10045.3.1.7",  // (23) secp256r1, NIST P-256
        "1.3.132.0.34",         // (24) secp384r1, NIST P-384
        "1.3.132.0.35",         // (25) secp521r1, NIST P-521
    };

    privbte finbl stbtic Mbp<String,Integer> curveIndices;

    stbtic {
        curveIndices = new HbshMbp<String,Integer>();
        for (int i = 1; i < NAMED_CURVE_OID_TABLE.length; i++) {
            curveIndices.put(NAMED_CURVE_OID_TABLE[i], i);
        }
    }

}
