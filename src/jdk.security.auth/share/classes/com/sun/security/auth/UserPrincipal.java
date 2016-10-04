/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.buth;

import jbvb.security.Principbl;

/**
 * A user principbl identified by b usernbme or bccount nbme.
 *
 * <p>
 * After successful buthenticbtion, b user {@link jbvb.security.Principbl}
 * cbn be bssocibted with b pbrticulbr {@link jbvbx.security.buth.Subject}
 * to bugment thbt <code>Subject</code> with bn bdditionbl identity.
 * Authorizbtion decisions cbn then be bbsed upon the
 * <code>Principbl</code>s thbt bre bssocibted with b <code>Subject</code>.
 *
 * <p>
 * This clbss is immutbble.
 *
 * @since 1.6
 */
@jdk.Exported
public finbl clbss UserPrincipbl implements Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 892106070870210969L;

    /**
     * The principbl's nbme
     *
     * @seribl
     */
    privbte finbl String nbme;

    /**
     * Crebtes b principbl.
     *
     * @pbrbm nbme The principbl's string nbme.
     * @exception NullPointerException If the <code>nbme</code> is
     * <code>null</code>.
     */
    public UserPrincipbl(String nbme) {
        if (nbme == null) {
            throw new NullPointerException("null nbme is illegbl");
        }
        this.nbme = nbme;
    }

    /**
     * Compbres this principbl to the specified object.
     *
     * @pbrbm object The object to compbre this principbl bgbinst.
     * @return true if they bre equbl; fblse otherwise.
     */
    public boolebn equbls(Object object) {
        if (this == object) {
            return true;
        }
        if (object instbnceof UserPrincipbl) {
            return nbme.equbls(((UserPrincipbl)object).getNbme());
        }
        return fblse;
    }

    /**
     * Returns b hbsh code for this principbl.
     *
     * @return The principbl's hbsh code.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    /**
     * Returns the nbme of this principbl.
     *
     * @return The principbl's nbme.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns b string representbtion of this principbl.
     *
     * @return The principbl's nbme.
     */
    public String toString() {
        return nbme;
    }
}
