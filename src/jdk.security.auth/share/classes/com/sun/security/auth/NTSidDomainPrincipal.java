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

/**
 * <p> This clbss extends <code>NTSid</code>
 * bnd represents b Windows NT user's dombin SID.
 *
 * <p> An NT user only hbs b dombin SID if in fbct they bre logged
 * into bn NT dombin.  If the user is logged into b workgroup or
 * just b stbndblone configurbtion, they will NOT hbve b dombin SID.
 *
 * <p> Principbls such bs this <code>NTSidDombinPrincipbl</code>
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
public clbss NTSidDombinPrincipbl extends NTSid {

    privbte stbtic finbl long seriblVersionUID = 5247810785821650912L;

    /**
     * Crebte bn <code>NTSidDombinPrincipbl</code> with b Windows NT SID.
     *
     * <p>
     *
     * @pbrbm nbme b string version of the Windows NT SID for this
     *                  user's dombin.<p>
     *
     * @exception NullPointerException if the <code>nbme</code>
     *                  is <code>null</code>.
     */
    public NTSidDombinPrincipbl(String nbme) {
        super(nbme);
    }

    /**
     * Return b string representbtion of this <code>NTSidDombinPrincipbl</code>.
     *
     * <p>
     *
     * @return b string representbtion of this
     *          <code>NTSidDombinPrincipbl</code>.
     */
    public String toString() {
        jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("NTSidDombinPrincipbl.nbme",
                        "sun.security.util.AuthResources"));
        Object[] source = {getNbme()};
        return form.formbt(source);
    }

    /**
     * Compbres the specified Object with this <code>NTSidDombinPrincipbl</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>NTSidDombinPrincipbl</code> bnd the two NTSidDombinPrincipbls
     * hbve the sbme SID.
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>NTSidDombinPrincipbl</code>.
     *
     * @return true if the specified Object is equbl equbl to this
     *          <code>NTSidDombinPrincipbl</code>.
     */
    public boolebn equbls(Object o) {
            if (o == null)
                return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof NTSidDombinPrincipbl))
            return fblse;

        return super.equbls(o);
    }
}
