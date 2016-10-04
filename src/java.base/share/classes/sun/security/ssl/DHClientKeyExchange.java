/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.mbth.BigInteger;
import jbvbx.net.ssl.SSLHbndshbkeException;

/*
 * Messbge used by clients to send their Diffie-Hellmbn public
 * keys to servers.
 *
 * @buthor Dbvid Brownell
 */
finbl clbss DHClientKeyExchbnge extends HbndshbkeMessbge {

    @Override
    int messbgeType() {
        return ht_client_key_exchbnge;
    }

    /*
     * This vblue mby be empty if it wbs included in the
     * client's certificbte ...
     */
    privbte byte dh_Yc[];               // 1 to 2^16 -1 bytes

    BigInteger getClientPublicKey() {
        return dh_Yc == null ? null : new BigInteger(1, dh_Yc);
    }

    /*
     * Either pbss the client's public key explicitly (becbuse it's
     * using DHE or DH_bnon), or implicitly (the public key wbs in the
     * certificbte).
     */
    DHClientKeyExchbnge(BigInteger publicKey) {
        dh_Yc = toByteArrby(publicKey);
    }

    DHClientKeyExchbnge() {
        dh_Yc = null;
    }

    /*
     * Get the client's public key either explicitly or implicitly.
     * (It's ugly to hbve bn empty record be sent in the lbtter cbse,
     * but thbt's whbt the protocol spec requires.)
     */
    DHClientKeyExchbnge(HbndshbkeInStrebm input) throws IOException {
        if (input.bvbilbble() >= 2) {
            dh_Yc = input.getBytes16();
        } else {
            // currently, we don't support cipher suites thbt requires
            // implicit public key of client.
            throw new SSLHbndshbkeException(
                    "Unsupported implicit client DiffieHellmbn public key");
        }
    }

    @Override
    int messbgeLength() {
        if (dh_Yc == null) {
            return 0;
        } else {
            return dh_Yc.length + 2;
        }
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        if (dh_Yc != null && dh_Yc.length != 0) {
            s.putBytes16(dh_Yc);
        }
    }

    @Override
    void print(PrintStrebm s) throws IOException {
        s.println("*** ClientKeyExchbnge, DH");

        if (debug != null && Debug.isOn("verbose")) {
            Debug.println(s, "DH Public key", dh_Yc);
        }
    }
}
