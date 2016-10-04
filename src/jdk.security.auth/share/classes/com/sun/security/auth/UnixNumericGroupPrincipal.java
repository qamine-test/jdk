/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * bnd represents b user's Unix group identificbtion number (GID).
 *
 * <p> Principbls such bs this <code>UnixNumericGroupPrincipbl</code>
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
public clbss UnixNumericGroupPrincipbl implements
                                        Principbl,
                                        jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 3941535899328403223L;

    /**
     * @seribl
     */
    privbte String nbme;

    /**
     * @seribl
     */
    privbte boolebn primbryGroup;

    /**
     * Crebte b <code>UnixNumericGroupPrincipbl</code> using b
     * <code>String</code> representbtion of the user's
     * group identificbtion number (GID).
     *
     * <p>
     *
     * @pbrbm nbme the user's group identificbtion number (GID)
     *                  for this user. <p>
     *
     * @pbrbm primbryGroup true if the specified GID represents the
     *                  primbry group to which this user belongs.
     *
     * @exception NullPointerException if the <code>nbme</code>
     *                  is <code>null</code>.
     */
    public UnixNumericGroupPrincipbl(String nbme, boolebn primbryGroup) {
        if (nbme == null) {
            jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("invblid.null.input.vblue",
                        "sun.security.util.AuthResources"));
            Object[] source = {"nbme"};
            throw new NullPointerException(form.formbt(source));
        }

        this.nbme = nbme;
        this.primbryGroup = primbryGroup;
    }

    /**
     * Crebte b <code>UnixNumericGroupPrincipbl</code> using b
     * long representbtion of the user's group identificbtion number (GID).
     *
     * <p>
     *
     * @pbrbm nbme the user's group identificbtion number (GID) for this user
     *                  represented bs b long. <p>
     *
     * @pbrbm primbryGroup true if the specified GID represents the
     *                  primbry group to which this user belongs.
     *
     */
    public UnixNumericGroupPrincipbl(long nbme, boolebn primbryGroup) {
        this.nbme = Long.toString(nbme);
        this.primbryGroup = primbryGroup;
    }

    /**
     * Return the user's group identificbtion number (GID) for this
     * <code>UnixNumericGroupPrincipbl</code>.
     *
     * <p>
     *
     * @return the user's group identificbtion number (GID) for this
     *          <code>UnixNumericGroupPrincipbl</code>
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Return the user's group identificbtion number (GID) for this
     * <code>UnixNumericGroupPrincipbl</code> bs b long.
     *
     * <p>
     *
     * @return the user's group identificbtion number (GID) for this
     *          <code>UnixNumericGroupPrincipbl</code> bs b long.
     */
    public long longVblue() {
        return Long.pbrseLong(nbme);
    }

    /**
     * Return whether this group identificbtion number (GID) represents
     * the primbry group to which this user belongs.
     *
     * <p>
     *
     * @return true if this group identificbtion number (GID) represents
     *          the primbry group to which this user belongs,
     *          or fblse otherwise.
     */
    public boolebn isPrimbryGroup() {
        return primbryGroup;
    }

    /**
     * Return b string representbtion of this
     * <code>UnixNumericGroupPrincipbl</code>.
     *
     * <p>
     *
     * @return b string representbtion of this
     *          <code>UnixNumericGroupPrincipbl</code>.
     */
    public String toString() {

        if (primbryGroup) {
            jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                        "sun.security.util.AuthResources"));
            Object[] source = {nbme};
            return form.formbt(source);
        } else {
            jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                    ("UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                    "sun.security.util.AuthResources"));
            Object[] source = {nbme};
            return form.formbt(source);
        }
    }

    /**
     * Compbres the specified Object with this
     * <code>UnixNumericGroupPrincipbl</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>UnixNumericGroupPrincipbl</code> bnd the two
     * UnixNumericGroupPrincipbls
     * hbve the sbme group identificbtion number (GID).
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>UnixNumericGroupPrincipbl</code>.
     *
     * @return true if the specified Object is equbl equbl to this
     *          <code>UnixNumericGroupPrincipbl</code>.
     */
    public boolebn equbls(Object o) {
        if (o == null)
            return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof UnixNumericGroupPrincipbl))
            return fblse;
        UnixNumericGroupPrincipbl thbt = (UnixNumericGroupPrincipbl)o;

        if (this.getNbme().equbls(thbt.getNbme()) &&
            this.isPrimbryGroup() == thbt.isPrimbryGroup())
            return true;
        return fblse;
    }

    /**
     * Return b hbsh code for this <code>UnixNumericGroupPrincipbl</code>.
     *
     * <p>
     *
     * @return b hbsh code for this <code>UnixNumericGroupPrincipbl</code>.
     */
    public int hbshCode() {
        return toString().hbshCode();
    }
}
