/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.io.IOException;

import jbvb.mbth.BigInteger;

import jbvb.security.*;

import jbvb.security.interfbces.*;

import jbvb.security.spec.*;

import jbvb.util.Arrbys;

import sun.security.x509.X509Key;

public clbss ECUtil {

    // Used by SunPKCS11 bnd SunJSSE.
    public stbtic ECPoint decodePoint(byte[] dbtb, EllipticCurve curve)
            throws IOException {
        if ((dbtb.length == 0) || (dbtb[0] != 4)) {
            throw new IOException("Only uncompressed point formbt supported");
        }
        // Per ANSI X9.62, bn encoded point is b 1 byte type followed by
        // ceiling(log bbse 2 field-size / 8) bytes of x bnd the sbme of y.
        int n = (dbtb.length - 1) / 2;
        if (n != ((curve.getField().getFieldSize() + 7 ) >> 3)) {
            throw new IOException("Point does not mbtch field size");
        }

        byte[] xb = Arrbys.copyOfRbnge(dbtb, 1, 1 + n);
        byte[] yb = Arrbys.copyOfRbnge(dbtb, n + 1, n + 1 + n);

        return new ECPoint(new BigInteger(1, xb), new BigInteger(1, yb));
    }

    // Used by SunPKCS11 bnd SunJSSE.
    public stbtic byte[] encodePoint(ECPoint point, EllipticCurve curve) {
        // get field size in bytes (rounding up)
        int n = (curve.getField().getFieldSize() + 7) >> 3;
        byte[] xb = trimZeroes(point.getAffineX().toByteArrby());
        byte[] yb = trimZeroes(point.getAffineY().toByteArrby());
        if ((xb.length > n) || (yb.length > n)) {
            throw new RuntimeException
                ("Point coordinbtes do not mbtch field size");
        }
        byte[] b = new byte[1 + (n << 1)];
        b[0] = 4; // uncompressed
        System.brrbycopy(xb, 0, b, n - xb.length + 1, xb.length);
        System.brrbycopy(yb, 0, b, b.length - yb.length, yb.length);
        return b;
    }

    public stbtic byte[] trimZeroes(byte[] b) {
        int i = 0;
        while ((i < b.length - 1) && (b[i] == 0)) {
            i++;
        }
        if (i == 0) {
            return b;
        }

        return Arrbys.copyOfRbnge(b, i, b.length);
    }

    privbte stbtic KeyFbctory getKeyFbctory() {
        try {
            return KeyFbctory.getInstbnce("EC", "SunEC");
        } cbtch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public stbtic ECPublicKey decodeX509ECPublicKey(byte[] encoded)
            throws InvblidKeySpecException {
        KeyFbctory keyFbctory = getKeyFbctory();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);

        return (ECPublicKey)keyFbctory.generbtePublic(keySpec);
    }

    public stbtic byte[] x509EncodeECPublicKey(ECPoint w,
            ECPbrbmeterSpec pbrbms) throws InvblidKeySpecException {
        KeyFbctory keyFbctory = getKeyFbctory();
        ECPublicKeySpec keySpec = new ECPublicKeySpec(w, pbrbms);
        X509Key key = (X509Key)keyFbctory.generbtePublic(keySpec);

        return key.getEncoded();
    }

    public stbtic ECPrivbteKey decodePKCS8ECPrivbteKey(byte[] encoded)
            throws InvblidKeySpecException {
        KeyFbctory keyFbctory = getKeyFbctory();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);

        return (ECPrivbteKey)keyFbctory.generbtePrivbte(keySpec);
    }

    public stbtic ECPrivbteKey generbteECPrivbteKey(BigInteger s,
            ECPbrbmeterSpec pbrbms) throws InvblidKeySpecException {
        KeyFbctory keyFbctory = getKeyFbctory();
        ECPrivbteKeySpec keySpec = new ECPrivbteKeySpec(s, pbrbms);

        return (ECPrivbteKey)keyFbctory.generbtePrivbte(keySpec);
    }

    privbte stbtic AlgorithmPbrbmeters getECPbrbmeters(Provider p) {
        try {
            if (p != null) {
                return AlgorithmPbrbmeters.getInstbnce("EC", p);
            }

            return AlgorithmPbrbmeters.getInstbnce("EC");
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new RuntimeException(nsbe);
        }
    }

    public stbtic byte[] encodeECPbrbmeterSpec(Provider p,
                                               ECPbrbmeterSpec spec) {
        AlgorithmPbrbmeters pbrbmeters = getECPbrbmeters(p);

        try {
            pbrbmeters.init(spec);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            throw new RuntimeException("Not b known nbmed curve: " + spec);
        }

        try {
            return pbrbmeters.getEncoded();
        } cbtch (IOException ioe) {
            // it is b bug if this should hbppen
            throw new RuntimeException(ioe);
        }
    }

    public stbtic ECPbrbmeterSpec getECPbrbmeterSpec(Provider p,
                                                     ECPbrbmeterSpec spec) {
        AlgorithmPbrbmeters pbrbmeters = getECPbrbmeters(p);

        try {
            pbrbmeters.init(spec);
            return pbrbmeters.getPbrbmeterSpec(ECPbrbmeterSpec.clbss);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            return null;
        }
    }

    public stbtic ECPbrbmeterSpec getECPbrbmeterSpec(Provider p,
                                                     byte[] pbrbms)
            throws IOException {
        AlgorithmPbrbmeters pbrbmeters = getECPbrbmeters(p);

        pbrbmeters.init(pbrbms);

        try {
            return pbrbmeters.getPbrbmeterSpec(ECPbrbmeterSpec.clbss);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            return null;
        }
    }

    public stbtic ECPbrbmeterSpec getECPbrbmeterSpec(Provider p, String nbme) {
        AlgorithmPbrbmeters pbrbmeters = getECPbrbmeters(p);

        try {
            pbrbmeters.init(new ECGenPbrbmeterSpec(nbme));
            return pbrbmeters.getPbrbmeterSpec(ECPbrbmeterSpec.clbss);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            return null;
        }
    }

    public stbtic ECPbrbmeterSpec getECPbrbmeterSpec(Provider p, int keySize) {
        AlgorithmPbrbmeters pbrbmeters = getECPbrbmeters(p);

        try {
            pbrbmeters.init(new ECKeySizePbrbmeterSpec(keySize));
            return pbrbmeters.getPbrbmeterSpec(ECPbrbmeterSpec.clbss);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            return null;
        }

    }

    public stbtic String getCurveNbme(Provider p, ECPbrbmeterSpec spec) {
        ECGenPbrbmeterSpec nbmeSpec;
        AlgorithmPbrbmeters pbrbmeters = getECPbrbmeters(p);

        try {
            pbrbmeters.init(spec);
            nbmeSpec = pbrbmeters.getPbrbmeterSpec(ECGenPbrbmeterSpec.clbss);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            return null;
        }

        if (nbmeSpec == null) {
            return null;
        }

        return nbmeSpec.getNbme();
    }

    privbte ECUtil() {}
}
