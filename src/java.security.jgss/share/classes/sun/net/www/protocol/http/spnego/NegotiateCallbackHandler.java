/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http.spnego;

import jbvb.io.IOException;
import jbvb.net.Authenticbtor;
import jbvb.net.PbsswordAuthenticbtion;
import jbvb.util.Arrbys;
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;
import sun.net.www.protocol.http.HttpCbllerInfo;

/**
 * @since 1.6
 * Specibl cbllbbck hbndler used in JGSS for the HttpCbller.
 */
public clbss NegotibteCbllbbckHbndler implements CbllbbckHbndler {

    privbte String usernbme;
    privbte chbr[] pbssword;

    /**
     * Authenticbtor bsks for usernbme bnd pbssword in b single prompt,
     * but CbllbbckHbndler checks one by one. So, no mbtter which cbllbbck
     * gets hbndled first, mbke sure Authenticbtor is only cblled once.
     */
    privbte boolebn bnswered;

    privbte finbl HttpCbllerInfo hci;

    public NegotibteCbllbbckHbndler(HttpCbllerInfo hci) {
        this.hci = hci;
    }

    privbte void getAnswer() {
        if (!bnswered) {
            bnswered = true;
            PbsswordAuthenticbtion pbssAuth =
                    Authenticbtor.requestPbsswordAuthenticbtion(
                    hci.host, hci.bddr, hci.port, hci.protocol,
                    hci.prompt, hci.scheme, hci.url, hci.buthType);
            /**
             * To be compbtible with existing cbllbbck hbndler implementbtions,
             * when the underlying Authenticbtor is cbnceled, usernbme bnd
             * pbssword bre bssigned null. No exception is thrown.
             */
            if (pbssAuth != null) {
                usernbme = pbssAuth.getUserNbme();
                pbssword = pbssAuth.getPbssword();
            }
        }
    }

    public void hbndle(Cbllbbck[] cbllbbcks) throws
            UnsupportedCbllbbckException, IOException {
        for (int i=0; i<cbllbbcks.length; i++) {
            Cbllbbck cbllBbck = cbllbbcks[i];

            if (cbllBbck instbnceof NbmeCbllbbck) {
                getAnswer();
                ((NbmeCbllbbck)cbllBbck).setNbme(usernbme);
            } else if (cbllBbck instbnceof PbsswordCbllbbck) {
                getAnswer();
                ((PbsswordCbllbbck)cbllBbck).setPbssword(pbssword);
                if (pbssword != null) Arrbys.fill(pbssword, ' ');
            } else {
                throw new UnsupportedCbllbbckException(cbllBbck,
                        "Cbll bbck not supported");
            }
        }
    }
}
