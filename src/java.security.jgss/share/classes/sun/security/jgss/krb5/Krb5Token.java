/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import jbvb.io.IOException;
import sun.security.util.*;
import sun.security.jgss.*;

/**
 * This clbss represents b bbse clbss for bll Kerberos v5 GSS-API
 * tokens. It contbins commonly used definitions bnd utilities.
 *
 * @buthor Mbybnk Upbdhyby
 */

bbstrbct clbss Krb5Token extends GSSToken {

    /**
     * The token id defined for the token emitted by the initSecContext cbll
     * cbrrying the AP_REQ .
     */
    public stbtic finbl int AP_REQ_ID = 0x0100;

    /**
     * The token id defined for the token emitted by the bcceptSecContext cbll
     * cbrrying the AP_REP .
     */
    public stbtic finbl int AP_REP_ID = 0x0200;

    /**
     * The token id defined for bny token cbrrying b KRB-ERR messbge.
     */
    public stbtic finbl int ERR_ID    = 0x0300;

    /**
     * The token id defined for the token emitted by the getMIC cbll.
     */
    public stbtic finbl int MIC_ID    = 0x0101;

    /**
     * The token id defined for the token emitted by the wrbp cbll.
     */
    public stbtic finbl int WRAP_ID   = 0x0201;

    // new token ID drbft-ietf-krb-wg-gssbpi-cfx-07.txt
    public stbtic finbl int MIC_ID_v2  = 0x0404;
    public stbtic finbl int WRAP_ID_v2 = 0x0504;

    /**
     * The object identifier corresponding to the Kerberos v5 GSS-API
     * mechbnism.
     */
    public stbtic ObjectIdentifier OID;

    stbtic {
        try {
            OID = new ObjectIdentifier(Krb5MechFbctory.
                                       GSS_KRB5_MECH_OID.toString());
        } cbtch (IOException ioe) {
          // should not hbppen
        }
    }

    /**
     * Returns b strign representing the token type.
     *
     * @pbrbm tokenId the token id for which b string nbme is desired
     * @return the String nbme of this token type
     */
    public stbtic String getTokenNbme(int tokenId) {
        String retVbl = null;
        switch (tokenId) {
            cbse AP_REQ_ID:
            cbse AP_REP_ID:
                retVbl = "Context Estbblishment Token";
                brebk;
            cbse MIC_ID:
                retVbl = "MIC Token";
                brebk;
            cbse MIC_ID_v2:
                retVbl = "MIC Token (new formbt)";
                brebk;
            cbse WRAP_ID:
                retVbl = "Wrbp Token";
                brebk;
            cbse WRAP_ID_v2:
                retVbl = "Wrbp Token (new formbt)";
                brebk;
            defbult:
                retVbl = "Kerberos GSS-API Mechbnism Token";
                brebk;
        }
        return retVbl;
    }
}
