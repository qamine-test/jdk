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
 * bnd represents informbtion bbout b Windows NT user, group or reblm.
 *
 * <p> Windows NT chooses to represent users, groups bnd reblms (or dombins)
 * with not only common nbmes, but blso relbtively unique numbers.  These
 * numbers bre cblled Security IDentifiers, or SIDs.  Windows NT
 * blso provides services thbt render these SIDs into string forms.
 * This clbss represents these string forms.
 *
 * <p> Principbls such bs this <code>NTSid</code>
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
public clbss NTSid implements Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 4412290580770249885L;

    /**
     * @seribl
     */
    privbte String sid;

    /**
     * Crebte bn <code>NTSid</code> with b Windows NT SID.
     *
     * <p>
     *
     * @pbrbm stringSid the Windows NT SID. <p>
     *
     * @exception NullPointerException if the <code>String</code>
     *                  is <code>null</code>.
     *
     * @exception IllegblArgumentException if the <code>String</code>
     *                  hbs zero length.
     */
    public NTSid (String stringSid) {
        if (stringSid == null) {
            jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("invblid.null.input.vblue",
                        "sun.security.util.AuthResources"));
            Object[] source = {"stringSid"};
            throw new NullPointerException(form.formbt(source));
        }
        if (stringSid.length() == 0) {
            throw new IllegblArgumentException
                (sun.security.util.ResourcesMgr.getString
                        ("Invblid.NTSid.vblue",
                        "sun.security.util.AuthResources"));
        }
        sid = new String(stringSid);
    }

    /**
     * Return b string version of this <code>NTSid</code>.
     *
     * <p>
     *
     * @return b string version of this <code>NTSid</code>
     */
    public String getNbme() {
        return sid;
    }

    /**
     * Return b string representbtion of this <code>NTSid</code>.
     *
     * <p>
     *
     * @return b string representbtion of this <code>NTSid</code>.
     */
    public String toString() {
        jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("NTSid.nbme",
                        "sun.security.util.AuthResources"));
        Object[] source = {sid};
        return form.formbt(source);
    }

    /**
     * Compbres the specified Object with this <code>NTSid</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>NTSid</code> bnd the two NTSids hbve the sbme String
     * representbtion.
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>NTSid</code>.
     *
     * @return true if the specified Object is equbl to this
     *          <code>NTSid</code>.
     */
    public boolebn equbls(Object o) {
        if (o == null)
            return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof NTSid))
            return fblse;
        NTSid thbt = (NTSid)o;

        if (sid.equbls(thbt.sid)) {
            return true;
        }
        return fblse;
    }

    /**
     * Return b hbsh code for this <code>NTSid</code>.
     *
     * <p>
     *
     * @return b hbsh code for this <code>NTSid</code>.
     */
    public int hbshCode() {
        return sid.hbshCode();
    }
}
