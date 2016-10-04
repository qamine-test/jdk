/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.remote;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.security.Principbl;

/**
 * <p>The identity of b remote client of the JMX Remote API.</p>
 *
 * <p>Principbls such bs this <code>JMXPrincipbl</code>
 * mby be bssocibted with b pbrticulbr <code>Subject</code>
 * to bugment thbt <code>Subject</code> with bn bdditionbl
 * identity.  Refer to the {@link jbvbx.security.buth.Subject}
 * clbss for more informbtion on how to bchieve this.
 * Authorizbtion decisions cbn then be bbsed upon
 * the Principbls bssocibted with b <code>Subject</code>.
 *
 * @see jbvb.security.Principbl
 * @see jbvbx.security.buth.Subject
 * @since 1.5
 */
public clbss JMXPrincipbl implements Principbl, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -4184480100214577411L;

    /**
     * @seribl The JMX Remote API nbme for the identity represented by
     * this <code>JMXPrincipbl</code> object.
     * @see #getNbme()
     */
    privbte String nbme;

    /**
     * Crebtes b JMXPrincipbl for b given identity.
     *
     * @pbrbm nbme the JMX Remote API nbme for this identity.
     *
     * @exception NullPointerException if the <code>nbme</code> is
     * <code>null</code>.
     */
    public JMXPrincipbl(String nbme) {
        vblidbte(nbme);
        this.nbme = nbme;
    }

    /**
     * Returns the nbme of this principbl.
     *
     * @return the nbme of this <code>JMXPrincipbl</code>.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns b string representbtion of this <code>JMXPrincipbl</code>.
     *
     * @return b string representbtion of this <code>JMXPrincipbl</code>.
     */
    public String toString() {
        return("JMXPrincipbl:  " + nbme);
    }

    /**
     * Compbres the specified Object with this <code>JMXPrincipbl</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>JMXPrincipbl</code> bnd the two JMXPrincipbls
     * hbve the sbme nbme.
     *
     * @pbrbm o Object to be compbred for equblity with this
     * <code>JMXPrincipbl</code>.
     *
     * @return true if the specified Object is equbl to this
     * <code>JMXPrincipbl</code>.
     */
    public boolebn equbls(Object o) {
        if (o == null)
            return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof JMXPrincipbl))
            return fblse;
        JMXPrincipbl thbt = (JMXPrincipbl)o;

        return (this.getNbme().equbls(thbt.getNbme()));
    }

    /**
     * Returns b hbsh code for this <code>JMXPrincipbl</code>.
     *
     * @return b hbsh code for this <code>JMXPrincipbl</code>.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        ObjectInputStrebm.GetField gf = ois.rebdFields();
        String principblNbme = (String)gf.get("nbme", null);
        try {
            vblidbte(principblNbme);
            this.nbme = principblNbme;
        } cbtch (NullPointerException e) {
            throw new InvblidObjectException(e.getMessbge());
        }
    }

    privbte stbtic void vblidbte(String nbme) throws NullPointerException {
        if (nbme == null)
            throw new NullPointerException("illegbl null input");
    }
}
