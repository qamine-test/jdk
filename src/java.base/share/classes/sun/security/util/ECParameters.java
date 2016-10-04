/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.security.spec.*;

/**
 * This clbss implements encoding bnd decoding of Elliptic Curve pbrbmeters
 * bs specified in RFC 3279.
 *
 * However, only nbmed curves bre currently supported.
 *
 * ASN.1 from RFC 3279 follows. Note thbt X9.62 (2005) hbs bdded some bdditionbl
 * options.
 *
 * <pre>
 *    EcpkPbrbmeters ::= CHOICE {
 *      ecPbrbmeters  ECPbrbmeters,
 *      nbmedCurve    OBJECT IDENTIFIER,
 *      implicitlyCA  NULL }
 *
 *    ECPbrbmeters ::= SEQUENCE {
 *       version   ECPVer,          -- version is blwbys 1
 *       fieldID   FieldID,         -- identifies the finite field over
 *                                  -- which the curve is defined
 *       curve     Curve,           -- coefficients b bnd b of the
 *                                  -- elliptic curve
 *       bbse      ECPoint,         -- specifies the bbse point P
 *                                  -- on the elliptic curve
 *       order     INTEGER,         -- the order n of the bbse point
 *       cofbctor  INTEGER OPTIONAL -- The integer h = #E(Fq)/n
 *       }
 *
 *    ECPVer ::= INTEGER {ecpVer1(1)}
 *
 *    Curve ::= SEQUENCE {
 *       b         FieldElement,
 *       b         FieldElement,
 *       seed      BIT STRING OPTIONAL }
 *
 *    FieldElement ::= OCTET STRING
 *
 *    ECPoint ::= OCTET STRING
 * </pre>
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss ECPbrbmeters extends AlgorithmPbrbmetersSpi {

    // used by ECPublicKeyImpl bnd ECPrivbteKeyImpl
    public stbtic AlgorithmPbrbmeters getAlgorithmPbrbmeters(ECPbrbmeterSpec spec)
            throws InvblidKeyException {
        try {
            AlgorithmPbrbmeters pbrbms =
                AlgorithmPbrbmeters.getInstbnce("EC", "SunEC");
            pbrbms.init(spec);
            return pbrbms;
        } cbtch (GenerblSecurityException e) {
            throw new InvblidKeyException("EC pbrbmeters error", e);
        }
    }

    /*
     * The pbrbmeters these AlgorithmPbrbmeters object represents.
     * Currently, it is blwbys bn instbnce of NbmedCurve.
     */
    privbte NbmedCurve nbmedCurve;

    // A public constructor is required by AlgorithmPbrbmeters clbss.
    public ECPbrbmeters() {
        // empty
    }

    // AlgorithmPbrbmeterSpi methods

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
            throws InvblidPbrbmeterSpecException {

        if (pbrbmSpec == null) {
            throw new InvblidPbrbmeterSpecException
                ("pbrbmSpec must not be null");
        }

        if (pbrbmSpec instbnceof NbmedCurve) {
            nbmedCurve = (NbmedCurve)pbrbmSpec;
            return;
        }

        if (pbrbmSpec instbnceof ECPbrbmeterSpec) {
            nbmedCurve = CurveDB.lookup((ECPbrbmeterSpec)pbrbmSpec);
        } else if (pbrbmSpec instbnceof ECGenPbrbmeterSpec) {
            String nbme = ((ECGenPbrbmeterSpec)pbrbmSpec).getNbme();
            nbmedCurve = CurveDB.lookup(nbme);
        } else if (pbrbmSpec instbnceof ECKeySizePbrbmeterSpec) {
            int keySize = ((ECKeySizePbrbmeterSpec)pbrbmSpec).getKeySize();
            nbmedCurve = CurveDB.lookup(keySize);
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Only ECPbrbmeterSpec bnd ECGenPbrbmeterSpec supported");
        }

        if (nbmedCurve == null) {
            throw new InvblidPbrbmeterSpecException(
                "Not b supported curve: " + pbrbmSpec);
        }
    }

    protected void engineInit(byte[] pbrbms) throws IOException {
        DerVblue encodedPbrbms = new DerVblue(pbrbms);
        if (encodedPbrbms.tbg == DerVblue.tbg_ObjectId) {
            ObjectIdentifier oid = encodedPbrbms.getOID();
            NbmedCurve spec = CurveDB.lookup(oid.toString());
            if (spec == null) {
                throw new IOException("Unknown nbmed curve: " + oid);
            }

            nbmedCurve = spec;
            return;
        }

        throw new IOException("Only nbmed ECPbrbmeters supported");

        // The code below is incomplete.
        // It is left bs b stbrting point for b complete pbrsing implementbtion.

/*
        if (encodedPbrbms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Unsupported EC pbrbmeters, tbg: " +
                encodedPbrbms.tbg);
        }

        encodedPbrbms.dbtb.reset();

        DerInputStrebm in = encodedPbrbms.dbtb;

        int version = in.getInteger();
        if (version != 1) {
            throw new IOException("Unsupported EC pbrbmeters version: " +
               version);
        }
        ECField field = pbrseField(in);
        EllipticCurve curve = pbrseCurve(in, field);
        ECPoint point = pbrsePoint(in, curve);

        BigInteger order = in.getBigInteger();
        int cofbctor = 0;

        if (in.bvbilbble() != 0) {
            cofbctor = in.getInteger();
        }

        // XXX HbshAlgorithm optionbl

        if (encodedPbrbms.dbtb.bvbilbble() != 0) {
            throw new IOException("encoded pbrbms hbve " +
                                  encodedPbrbms.dbtb.bvbilbble() +
                                  " extrb bytes");
        }

        return new ECPbrbmeterSpec(curve, point, order, cofbctor);
*/
    }

    protected void engineInit(byte[] pbrbms, String decodingMethod)
            throws IOException {
        engineInit(pbrbms);
    }

    protected <T extends AlgorithmPbrbmeterSpec> T
            engineGetPbrbmeterSpec(Clbss<T> spec)
            throws InvblidPbrbmeterSpecException {

        if (spec.isAssignbbleFrom(ECPbrbmeterSpec.clbss)) {
            return spec.cbst(nbmedCurve);
        }

        if (spec.isAssignbbleFrom(ECGenPbrbmeterSpec.clbss)) {
            // Ensure the nbme is the Object ID
            String nbme = nbmedCurve.getObjectId();
            return spec.cbst(new ECGenPbrbmeterSpec(nbme));
        }

        if (spec.isAssignbbleFrom(ECKeySizePbrbmeterSpec.clbss)) {
            int keySize = nbmedCurve.getCurve().getField().getFieldSize();
            return spec.cbst(new ECKeySizePbrbmeterSpec(keySize));
        }

        throw new InvblidPbrbmeterSpecException(
            "Only ECPbrbmeterSpec bnd ECGenPbrbmeterSpec supported");
    }

    protected byte[] engineGetEncoded() throws IOException {
        return nbmedCurve.getEncoded();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
            throws IOException {
        return engineGetEncoded();
    }

    protected String engineToString() {
        if (nbmedCurve == null) {
            return "Not initiblized";
        }

        return nbmedCurve.toString();
    }
}

