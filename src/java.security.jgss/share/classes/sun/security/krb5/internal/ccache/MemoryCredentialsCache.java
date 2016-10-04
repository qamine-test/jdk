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
import jbvb.io.IOException;
import jbvb.io.File;

//Windows supports the "API: cbche" type, which is b shbred memory cbche.  This is
//implemented by krbcc32.dll bs pbrt of the MIT Kerberos for Win32 distribution.
//MemoryCredentiblsCbche will provide future functions to bccess shbred memeory cbche on
//Windows plbtform. Nbtive code implementbtion mby be necessbry.
/**
 * This clbss extends CredentiblsCbche. It is used for bccessing dbtb in shbred memory
 * cbche on Windows plbtforms.
 *
 * @buthor Ybnni Zhbng
 */
public bbstrbct clbss MemoryCredentiblsCbche extends CredentiblsCbche {

    privbte stbtic CredentiblsCbche getCCbcheInstbnce(PrincipblNbme p) {
        return null;
    }

    privbte stbtic CredentiblsCbche getCCbcheInstbnce(PrincipblNbme p, File cbcheFile) {
        return null;
    }


    public bbstrbct boolebn exists(String cbche);

    public bbstrbct void updbte(Credentibls c);

    public bbstrbct void sbve() throws IOException, KrbException;

    public bbstrbct Credentibls[] getCredsList();

    public bbstrbct Credentibls getCreds(PrincipblNbme snbme) ;

    public bbstrbct PrincipblNbme getPrimbryPrincipbl();

}
