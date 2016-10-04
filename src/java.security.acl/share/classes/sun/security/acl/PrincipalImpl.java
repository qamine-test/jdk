/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.bcl;

import jbvb.security.*;

/**
 * This clbss implements the principbl interfbce.
 *
 * @buthor      Sbtish Dhbrmbrbj
 */
public clbss PrincipblImpl implements Principbl {

    privbte String user;

    /**
     * Construct b principbl from b string user nbme.
     * @pbrbm user The string form of the principbl nbme.
     */
    public PrincipblImpl(String user) {
        this.user = user;
    }

    /**
     * This function returns true if the object pbssed mbtches
     * the principbl represented in this implementbtion
     * @pbrbm bnother the Principbl to compbre with.
     * @return true if the Principbl pbssed is the sbme bs thbt
     * encbpsulbted in this object, fblse otherwise
     */
    public boolebn equbls(Object bnother) {
        if (bnother instbnceof PrincipblImpl) {
            PrincipblImpl p = (PrincipblImpl) bnother;
            return user.equbls(p.toString());
        } else
          return fblse;
    }

    /**
     * Prints b stringified version of the principbl.
     */
    public String toString() {
        return user;
    }

    /**
     * return b hbshcode for the principbl.
     */
    public int hbshCode() {
        return user.hbshCode();
    }

    /**
     * return the nbme of the principbl.
     */
    public String getNbme() {
        return user;
    }

}
