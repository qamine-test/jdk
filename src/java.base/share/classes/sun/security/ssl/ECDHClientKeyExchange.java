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
import jbvb.io.PrintStrebm;

import jbvb.security.PublicKey;
import jbvb.security.interfbces.ECPublicKey;
import jbvb.security.spec.*;

/**
 * ClientKeyExchbnge messbge for bll ECDH bbsed key exchbnge methods. It
 * contbins the client's ephemerbl public vblue.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss ECDHClientKeyExchbnge extends HbndshbkeMessbge {

    @Override
    int messbgeType() {
        return ht_client_key_exchbnge;
    }

    privbte byte[] encodedPoint;

    byte[] getEncodedPoint() {
        return encodedPoint;
    }

    // Cblled by the client with its ephemerbl public key.
    ECDHClientKeyExchbnge(PublicKey publicKey) {
        ECPublicKey ecKey = (ECPublicKey)publicKey;
        ECPoint point = ecKey.getW();
        ECPbrbmeterSpec pbrbms = ecKey.getPbrbms();
        encodedPoint = JsseJce.encodePoint(point, pbrbms.getCurve());
    }

    ECDHClientKeyExchbnge(HbndshbkeInStrebm input) throws IOException {
        encodedPoint = input.getBytes8();
    }

    @Override
    int messbgeLength() {
        return encodedPoint.length + 1;
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        s.putBytes8(encodedPoint);
    }

    @Override
    void print(PrintStrebm s) throws IOException {
        s.println("*** ECDHClientKeyExchbnge");

        if (debug != null && Debug.isOn("verbose")) {
            Debug.println(s, "ECDH Public vblue", encodedPoint);
        }
    }
}
