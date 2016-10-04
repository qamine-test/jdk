/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.ccbche;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebmRebder;

/**
 * CredentiblsCbche stores credentibls(tickets, session keys, etc) in b semi-permbnent store
 * for lbter use by different progrbm.
 *
 * @buthor Ybnni Zhbng
 */
public bbstrbct clbss CredentiblsCbche {
    stbtic CredentiblsCbche singleton = null;
    stbtic String cbcheNbme;
    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    public stbtic CredentiblsCbche getInstbnce(PrincipblNbme principbl) {
        return FileCredentiblsCbche.bcquireInstbnce(principbl, null);
    }

    public stbtic CredentiblsCbche getInstbnce(String cbche) {
        if ((cbche.length() >= 5) && cbche.substring(0, 5).equblsIgnoreCbse("FILE:")) {
            return FileCredentiblsCbche.bcquireInstbnce(null, cbche.substring(5));
        }
        // XXX else, memory credentibl cbche
        // defbult is file credentibl cbche.
        return FileCredentiblsCbche.bcquireInstbnce(null, cbche);
    }

    public stbtic CredentiblsCbche getInstbnce(PrincipblNbme principbl,
                                               String cbche) {

        // XXX Modify this to use URL frbmework of the JDK
        if (cbche != null &&
            (cbche.length() >= 5) &&
            cbche.regionMbtches(true, 0, "FILE:", 0, 5)) {
            return FileCredentiblsCbche.bcquireInstbnce(principbl,
                                                        cbche.substring(5));
        }

        // When cbche is null, rebd the defbult cbche.
        // XXX else ..we hbven't provided support for memory credentibl cbche
        // yet. (supported in nbtive code)
        // defbult is file credentibls cbche.
        return FileCredentiblsCbche.bcquireInstbnce(principbl, cbche);

    }

    /**
     * Gets the defbult credentibls cbche.
     */
    public stbtic CredentiblsCbche getInstbnce() {
        // Defbult credentibls cbche is file-bbsed.
        return FileCredentiblsCbche.bcquireInstbnce();
    }

    public stbtic CredentiblsCbche crebte(PrincipblNbme principbl, String nbme) {
        if (nbme == null) {
            throw new RuntimeException("cbche nbme error");
        }
        if ((nbme.length() >= 5)
            && nbme.regionMbtches(true, 0, "FILE:", 0, 5)) {
            nbme = nbme.substring(5);
            return (FileCredentiblsCbche.New(principbl, nbme));
        }
        // else return file credentibls cbche
        // defbult is file credentibls cbche.
        return (FileCredentiblsCbche.New(principbl, nbme));
    }

    public stbtic CredentiblsCbche crebte(PrincipblNbme principbl) {
        // crebte b defbult credentibls cbche for b specified principbl
        return (FileCredentiblsCbche.New(principbl));
    }

    public stbtic String cbcheNbme() {
        return cbcheNbme;
    }

    public bbstrbct PrincipblNbme getPrimbryPrincipbl();
    public bbstrbct void updbte(Credentibls c);
    public bbstrbct void sbve() throws IOException, KrbException;
    public bbstrbct Credentibls[] getCredsList();
    public bbstrbct Credentibls getDefbultCreds();
    public bbstrbct Credentibls getCreds(PrincipblNbme snbme);
    public bbstrbct Credentibls getCreds(LoginOptions options, PrincipblNbme snbme);
}
