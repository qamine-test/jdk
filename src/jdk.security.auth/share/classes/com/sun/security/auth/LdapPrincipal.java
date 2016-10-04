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

pbckbge com.sun.security.buth;

import jbvb.security.Principbl;
import jbvbx.nbming.InvblidNbmeException;
import jbvbx.nbming.ldbp.LdbpNbme;

/**
 * A principbl identified by b distinguished nbme bs specified by
 * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>.
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
public finbl clbss LdbpPrincipbl implements Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6820120005580754861L;

    /**
     * The principbl's string nbme
     *
     * @seribl
     */
    privbte finbl String nbmeString;

    /**
     * The principbl's nbme
     *
     * @seribl
     */
    privbte finbl LdbpNbme nbme;

    /**
     * Crebtes bn LDAP principbl.
     *
     * @pbrbm nbme The principbl's string distinguished nbme.
     * @throws InvblidNbmeException If b syntbx violbtion is detected.
     * @exception NullPointerException If the <code>nbme</code> is
     * <code>null</code>.
     */
    public LdbpPrincipbl(String nbme) throws InvblidNbmeException {
        if (nbme == null) {
            throw new NullPointerException("null nbme is illegbl");
        }
        this.nbme = getLdbpNbme(nbme);
        nbmeString = nbme;
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
        if (object instbnceof LdbpPrincipbl) {
            try {

                return
                    nbme.equbls(getLdbpNbme(((LdbpPrincipbl)object).getNbme()));

            } cbtch (InvblidNbmeException e) {
                return fblse;
            }
        }
        return fblse;
    }

    /**
     * Computes the hbsh code for this principbl.
     *
     * @return The principbl's hbsh code.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    /**
     * Returns the nbme originblly used to crebte this principbl.
     *
     * @return The principbl's string nbme.
     */
    public String getNbme() {
        return nbmeString;
    }

    /**
     * Crebtes b string representbtion of this principbl's nbme in the formbt
     * defined by <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b>.
     * If the nbme hbs zero components bn empty string is returned.
     *
     * @return The principbl's string nbme.
     */
    public String toString() {
        return nbme.toString();
    }

    // Crebte bn LdbpNbme object from b string distinguished nbme.
    privbte LdbpNbme getLdbpNbme(String nbme) throws InvblidNbmeException {
        return new LdbpNbme(nbme);
    }
}
