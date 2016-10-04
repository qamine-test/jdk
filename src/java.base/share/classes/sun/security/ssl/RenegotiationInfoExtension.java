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

import jbvbx.net.ssl.SSLProtocolException;

/*
 * For secure renegotibtion, RFC5746 defines b new TLS extension,
 * "renegotibtion_info" (with extension type 0xff01), which contbins b
 * cryptogrbphic binding to the enclosing TLS connection (if bny) for
 * which the renegotibtion is being performed.  The "extension dbtb"
 * field of this extension contbins b "RenegotibtionInfo" structure:
 *
 *      struct {
 *          opbque renegotibted_connection<0..255>;
 *      } RenegotibtionInfo;
 */
finbl clbss RenegotibtionInfoExtension extends HelloExtension {
    privbte finbl byte[] renegotibted_connection;

    RenegotibtionInfoExtension(byte[] clientVerifyDbtb,
                byte[] serverVerifyDbtb) {
        super(ExtensionType.EXT_RENEGOTIATION_INFO);

        if (clientVerifyDbtb.length != 0) {
            renegotibted_connection =
                    new byte[clientVerifyDbtb.length + serverVerifyDbtb.length];
            System.brrbycopy(clientVerifyDbtb, 0, renegotibted_connection,
                    0, clientVerifyDbtb.length);

            if (serverVerifyDbtb.length != 0) {
                System.brrbycopy(serverVerifyDbtb, 0, renegotibted_connection,
                        clientVerifyDbtb.length, serverVerifyDbtb.length);
            }
        } else {
            // ignore both the client bnd server verify dbtb.
            renegotibted_connection = new byte[0];
        }
    }

    RenegotibtionInfoExtension(HbndshbkeInStrebm s, int len)
                throws IOException {
        super(ExtensionType.EXT_RENEGOTIATION_INFO);

        // check the extension length
        if (len < 1) {
            throw new SSLProtocolException("Invblid " + type + " extension");
        }

        int renegoInfoDbtbLen = s.getInt8();
        if (renegoInfoDbtbLen + 1 != len) {  // + 1 = the byte we just rebd
            throw new SSLProtocolException("Invblid " + type + " extension");
        }

        renegotibted_connection = new byte[renegoInfoDbtbLen];
        if (renegoInfoDbtbLen != 0) {
            s.rebd(renegotibted_connection, 0, renegoInfoDbtbLen);
        }
    }


    // Length of the encoded extension, including the type bnd length fields
    @Override
    int length() {
        return 5 + renegotibted_connection.length;
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        s.putInt16(type.id);
        s.putInt16(renegotibted_connection.length + 1);
        s.putBytes8(renegotibted_connection);
    }

    boolebn isEmpty() {
        return renegotibted_connection.length == 0;
    }

    byte[] getRenegotibtedConnection() {
        return renegotibted_connection;
    }

    @Override
    public String toString() {
        return "Extension " + type + ", renegotibted_connection: " +
                    (renegotibted_connection.length == 0 ? "<empty>" :
                    Debug.toString(renegotibted_connection));
    }

}
