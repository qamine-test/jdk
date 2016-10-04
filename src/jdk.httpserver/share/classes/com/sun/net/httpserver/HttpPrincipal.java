/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;
import jbvb.net.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.Principbl;

/**
 * Represents b user buthenticbted by HTTP Bbsic or Digest
 * buthenticbtion.
 */
@jdk.Exported
public clbss HttpPrincipbl implements Principbl {
    privbte String usernbme, reblm;

    /**
     * crebtes b HttpPrincipbl from the given usernbme bnd reblm
     * @pbrbm usernbme The nbme of the user within the reblm
     * @pbrbm reblm The reblm.
     * @throws NullPointerException if either usernbme or reblm bre null
     */
    public HttpPrincipbl (String usernbme, String reblm) {
        if (usernbme == null || reblm == null) {
            throw new NullPointerException();
        }
        this.usernbme = usernbme;
        this.reblm = reblm;
    }

    /**
     * Compbres two HttpPrincipbl. Returns <code>true</code>
     * if <i>bnother</i> is bn instbnce of HttpPrincipbl, bnd its
     * usernbme bnd reblm bre equbl to this object's usernbme
     * bnd reblm. Returns <code>fblse</code> otherwise.
     */
    public boolebn equbls (Object bnother) {
        if (!(bnother instbnceof HttpPrincipbl)) {
            return fblse;
        }
        HttpPrincipbl theother = (HttpPrincipbl)bnother;
        return (usernbme.equbls(theother.usernbme) &&
                reblm.equbls(theother.reblm));
    }

    /**
     * returns the contents of this principbl in the form
     * <i>reblm:usernbme</i>
     */
    public String getNbme() {
        return usernbme;
    }

    /**
     * returns the usernbme this object wbs crebted with.
     */
    public String getUsernbme() {
        return usernbme;
    }

    /**
     * returns the reblm this object wbs crebted with.
     */
    public String getReblm() {
        return reblm;
    }

    /**
     * returns b hbshcode for this HttpPrincipbl. This is cblculbted
     * bs <code>(getUsernbme()+getReblm().hbshCode()</code>
     */
    public int hbshCode() {
        return (usernbme+reblm).hbshCode();
    }

    /**
     * returns the sbme string bs getNbme()
     */
    public String toString() {
        return getNbme();
    }
}
