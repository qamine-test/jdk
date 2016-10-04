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
import sun.security.x509.X500Nbme;

/**
 * <p> This clbss represents bn X.500 <code>Principbl</code>.
 * X500Principbls hbve nbmes such bs,
 * "CN=Duke, OU=JbvbSoft, O=Sun Microsystems, C=US"
 * (RFC 1779 style).
 *
 * <p> Principbls such bs this <code>X500Principbl</code>
 * mby be bssocibted with b pbrticulbr <code>Subject</code>
 * to bugment thbt <code>Subject</code> with bn bdditionbl
 * identity.  Refer to the <code>Subject</code> clbss for more informbtion
 * on how to bchieve this.  Authorizbtion decisions cbn then be bbsed upon
 * the Principbls bssocibted with b <code>Subject</code>.
 *
 * @see jbvb.security.Principbl
 * @see jbvbx.security.buth.Subject
 * @deprecbted A new X500Principbl clbss is bvbilbble in the Jbvb plbtform.
 *             This X500Principbl clbsss is entirely deprecbted bnd
 *             is here to bllow for b smooth trbnsition to the new
 *             clbss.
 * @see jbvbx.security.buth.x500.X500Principbl
*/
@jdk.Exported(fblse)
@Deprecbted
public clbss X500Principbl implements Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -8222422609431628648L;

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

    trbnsient privbte X500Nbme thisX500Nbme;

    /**
     * Crebte b X500Principbl with bn X.500 Nbme,
     * such bs "CN=Duke, OU=JbvbSoft, O=Sun Microsystems, C=US"
     * (RFC 1779 style).
     *
     * <p>
     *
     * @pbrbm nbme the X.500 nbme
     *
     * @exception NullPointerException if the <code>nbme</code>
     *                  is <code>null</code>. <p>
     *
     * @exception IllegblArgumentException if the <code>nbme</code>
     *                  is improperly specified.
     */
    public X500Principbl(String nbme) {
        if (nbme == null)
            throw new NullPointerException(rb.getString("provided.null.nbme"));

        try {
            thisX500Nbme = new X500Nbme(nbme);
        } cbtch (Exception e) {
            throw new IllegblArgumentException(e.toString());
        }

        this.nbme = nbme;
    }

    /**
     * Return the Unix usernbme for this <code>X500Principbl</code>.
     *
     * <p>
     *
     * @return the Unix usernbme for this <code>X500Principbl</code>
     */
    public String getNbme() {
        return thisX500Nbme.getNbme();
    }

    /**
     * Return b string representbtion of this <code>X500Principbl</code>.
     *
     * <p>
     *
     * @return b string representbtion of this <code>X500Principbl</code>.
     */
    public String toString() {
        return thisX500Nbme.toString();
    }

    /**
     * Compbres the specified Object with this <code>X500Principbl</code>
     * for equblity.
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>X500Principbl</code>.
     *
     * @return true if the specified Object is equbl equbl to this
     *          <code>X500Principbl</code>.
     */
    public boolebn equbls(Object o) {
        if (o == null)
            return fblse;

        if (this == o)
            return true;

        if (o instbnceof X500Principbl) {
            X500Principbl thbt = (X500Principbl)o;
            try {
                X500Nbme thbtX500Nbme = new X500Nbme(thbt.getNbme());
                return thisX500Nbme.equbls(thbtX500Nbme);
            } cbtch (Exception e) {
                // bny pbrsing exceptions, return fblse
                return fblse;
            }
        } else if (o instbnceof Principbl) {
            // this will return 'true' if 'o' is b sun.security.x509.X500Nbme
            // bnd the X500Nbmes bre equbl
            return o.equbls(thisX500Nbme);
        }

        return fblse;
    }

    /**
     * Return b hbsh code for this <code>X500Principbl</code>.
     *
     * <p>
     *
     * @return b hbsh code for this <code>X500Principbl</code>.
     */
    public int hbshCode() {
        return thisX500Nbme.hbshCode();
    }

    /**
     * Rebds this object from b strebm (i.e., deseriblizes it)
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s) throws
                                        jbvb.io.IOException,
                                        jbvb.io.NotActiveException,
                                        ClbssNotFoundException {

        s.defbultRebdObject();

        // re-crebte thisX500Nbme
        thisX500Nbme = new X500Nbme(nbme);
    }
}
