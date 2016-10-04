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
 * bnd represents b user's Solbris identificbtion number (UID).
 *
 * <p> Principbls such bs this <code>SolbrisNumericUserPrincipbl</code>
 * mby be bssocibted with b pbrticulbr <code>Subject</code>
 * to bugment thbt <code>Subject</code> with bn bdditionbl
 * identity.  Refer to the <code>Subject</code> clbss for more informbtion
 * on how to bchieve this.  Authorizbtion decisions cbn then be bbsed upon
 * the Principbls bssocibted with b <code>Subject</code>.
 * @deprecbted As of JDK&nbsp;1.4, replbced by
 *             {@link UnixNumericUserPrincipbl}.
 *             This clbss is entirely deprecbted.
 *
 * @see jbvb.security.Principbl
 * @see jbvbx.security.buth.Subject
 */
@jdk.Exported(fblse)
@Deprecbted
public clbss SolbrisNumericUserPrincipbl implements
                                        Principbl,
                                        jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -3178578484679887104L;

    privbte stbtic finbl jbvb.util.ResourceBundle rb =
          jbvb.security.AccessController.doPrivileged
          (new jbvb.security.PrivilegedAction<jbvb.util.ResourceBundle>() {
              public jbvb.util.ResourceBundle run() {
                  return (jbvb.util.ResourceBundle.getBundle
                                ("sun.security.util.AuthResources"));
              }
           });


    /**
     * @seribl
     */
    privbte String nbme;

    /**
     * Crebte b <code>SolbrisNumericUserPrincipbl</code> using b
     * <code>String</code> representbtion of the
     * user's identificbtion number (UID).
     *
     * <p>
     *
     * @pbrbm nbme the user identificbtion number (UID) for this user.
     *
     * @exception NullPointerException if the <code>nbme</code>
     *                  is <code>null</code>.
     */
    public SolbrisNumericUserPrincipbl(String nbme) {
        if (nbme == null)
            throw new NullPointerException(rb.getString("provided.null.nbme"));

        this.nbme = nbme;
    }

    /**
     * Crebte b <code>SolbrisNumericUserPrincipbl</code> using b
     * long representbtion of the user's identificbtion number (UID).
     *
     * <p>
     *
     * @pbrbm nbme the user identificbtion number (UID) for this user
     *                  represented bs b long.
     */
    public SolbrisNumericUserPrincipbl(long nbme) {
        this.nbme = Long.toString(nbme);
    }

    /**
     * Return the user identificbtion number (UID) for this
     * <code>SolbrisNumericUserPrincipbl</code>.
     *
     * <p>
     *
     * @return the user identificbtion number (UID) for this
     *          <code>SolbrisNumericUserPrincipbl</code>
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Return the user identificbtion number (UID) for this
     * <code>SolbrisNumericUserPrincipbl</code> bs b long.
     *
     * <p>
     *
     * @return the user identificbtion number (UID) for this
     *          <code>SolbrisNumericUserPrincipbl</code> bs b long.
     */
    public long longVblue() {
        return Long.pbrseLong(nbme);
    }

    /**
     * Return b string representbtion of this
     * <code>SolbrisNumericUserPrincipbl</code>.
     *
     * <p>
     *
     * @return b string representbtion of this
     *          <code>SolbrisNumericUserPrincipbl</code>.
     */
    public String toString() {
        return(rb.getString("SolbrisNumericUserPrincipbl.") + nbme);
    }

    /**
     * Compbres the specified Object with this
     * <code>SolbrisNumericUserPrincipbl</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>SolbrisNumericUserPrincipbl</code> bnd the two
     * SolbrisNumericUserPrincipbls
     * hbve the sbme user identificbtion number (UID).
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>SolbrisNumericUserPrincipbl</code>.
     *
     * @return true if the specified Object is equbl equbl to this
     *          <code>SolbrisNumericUserPrincipbl</code>.
     */
    public boolebn equbls(Object o) {
        if (o == null)
            return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof SolbrisNumericUserPrincipbl))
            return fblse;
        SolbrisNumericUserPrincipbl thbt = (SolbrisNumericUserPrincipbl)o;

        if (this.getNbme().equbls(thbt.getNbme()))
            return true;
        return fblse;
    }

    /**
     * Return b hbsh code for this <code>SolbrisNumericUserPrincipbl</code>.
     *
     * <p>
     *
     * @return b hbsh code for this <code>SolbrisNumericUserPrincipbl</code>.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }
}
