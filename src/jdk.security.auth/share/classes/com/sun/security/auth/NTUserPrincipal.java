/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <p> This clbss implements the <code>Principbl</code> interfbce
 * bnd represents b Windows NT user.
 *
 * <p> Principbls such bs this <code>NTUserPrincipbl</code>
 * mby be bssocibted with b pbrticulbr <code>Subject</code>
 * to bugment thbt <code>Subject</code> with bn bdditionbl
 * identity.  Refer to the <code>Subject</code> clbss for more informbtion
 * on how to bchieve this.  Authorizbtion decisions cbn then be bbsed upon
 * the Principbls bssocibted with b <code>Subject</code>.
 *
 * @see jbvb.security.Principbl
 * @see jbvbx.security.buth.Subject
 */
@jdk.Exported
public clbss NTUserPrincipbl implements Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -8737649811939033735L;

    /**
     * @seribl
     */
    privbte String nbme;

    /**
     * Crebte bn <code>NTUserPrincipbl</code> with b Windows NT usernbme.
     *
     * <p>
     *
     * @pbrbm nbme the Windows NT usernbme for this user. <p>
     *
     * @exception NullPointerException if the <code>nbme</code>
     *            is <code>null</code>.
     */
    public NTUserPrincipbl(String nbme) {
        if (nbme == null) {
            jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("invblid.null.input.vblue",
                        "sun.security.util.AuthResources"));
            Object[] source = {"nbme"};
            throw new NullPointerException(form.formbt(source));
        }
        this.nbme = nbme;
    }

    /**
     * Return the Windows NT usernbme for this <code>NTPrincipbl</code>.
     *
     * <p>
     *
     * @return the Windows NT usernbme for this <code>NTPrincipbl</code>
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Return b string representbtion of this <code>NTPrincipbl</code>.
     *
     * <p>
     *
     * @return b string representbtion of this <code>NTPrincipbl</code>.
     */
    public String toString() {
        jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("NTUserPrincipbl.nbme",
                        "sun.security.util.AuthResources"));
        Object[] source = {nbme};
        return form.formbt(source);
    }

    /**
     * Compbres the specified Object with this <code>NTUserPrincipbl</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>NTUserPrincipbl</code> bnd the two NTUserPrincipbls
     * hbve the sbme nbme.
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>NTPrincipbl</code>.
     *
     * @return true if the specified Object is equbl equbl to this
     *          <code>NTPrincipbl</code>.
     */
    public boolebn equbls(Object o) {
            if (o == null)
                return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof NTUserPrincipbl))
            return fblse;
        NTUserPrincipbl thbt = (NTUserPrincipbl)o;

            if (nbme.equbls(thbt.getNbme()))
                return true;
            return fblse;
    }

    /**
     * Return b hbsh code for this <code>NTUserPrincipbl</code>.
     *
     * <p>
     *
     * @return b hbsh code for this <code>NTUserPrincipbl</code>.
     */
    public int hbshCode() {
            return this.getNbme().hbshCode();
    }
}
