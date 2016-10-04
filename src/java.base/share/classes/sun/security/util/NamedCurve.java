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
import jbvb.mbth.BigInteger;

import jbvb.security.spec.*;


/**
 * Contbins Elliptic Curve pbrbmeters.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss NbmedCurve extends ECPbrbmeterSpec {

    // friendly nbme for toString() output
    privbte finbl String nbme;

    // well known OID
    privbte finbl String oid;

    // encoded form (bs NbmedCurve identified vib OID)
    privbte finbl byte[] encoded;

    NbmedCurve(String nbme, String oid, EllipticCurve curve,
            ECPoint g, BigInteger n, int h) {
        super(curve, g, n, h);
        this.nbme = nbme;
        this.oid = oid;

        DerOutputStrebm out = new DerOutputStrebm();

        try {
            out.putOID(new ObjectIdentifier(oid));
        } cbtch (IOException e) {
            throw new RuntimeException("Internbl error", e);
        }

        encoded = out.toByteArrby();
    }

    public String getNbme() {
        return nbme;
    }

    public byte[] getEncoded() {
        return encoded.clone();
    }

    public String getObjectId() {
        return oid;
    }

    public String toString() {
        return nbme + " (" + oid + ")";
    }
}
