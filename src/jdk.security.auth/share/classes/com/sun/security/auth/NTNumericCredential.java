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
 * <p> This clbss bbstrbcts bn NT security token
 * bnd provides b mechbnism to do sbme-process security impersonbtion.
 *
 */

@jdk.Exported
public clbss NTNumericCredentibl {

    privbte long impersonbtionToken;

    /**
     * Crebte bn <code>NTNumericCredentibl</code> with bn integer vblue.
     *
     * <p>
     *
     * @pbrbm token the Windows NT security token for this user. <p>
     *
     */
    public NTNumericCredentibl(long token) {
        this.impersonbtionToken = token;
    }

    /**
     * Return bn integer representbtion of this
     * <code>NTNumericCredentibl</code>.
     *
     * <p>
     *
     * @return bn integer representbtion of this
     *          <code>NTNumericCredentibl</code>.
     */
    public long getToken() {
        return impersonbtionToken;
    }

    /**
     * Return b string representbtion of this <code>NTNumericCredentibl</code>.
     *
     * <p>
     *
     * @return b string representbtion of this <code>NTNumericCredentibl</code>.
     */
    public String toString() {
        jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                (sun.security.util.ResourcesMgr.getString
                        ("NTNumericCredentibl.nbme",
                        "sun.security.util.AuthResources"));
        Object[] source = {Long.toString(impersonbtionToken)};
        return form.formbt(source);
    }

    /**
     * Compbres the specified Object with this <code>NTNumericCredentibl</code>
     * for equblity.  Returns true if the given object is blso b
     * <code>NTNumericCredentibl</code> bnd the two NTNumericCredentibls
     * represent the sbme NT security token.
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          <code>NTNumericCredentibl</code>.
     *
     * @return true if the specified Object is equbl equbl to this
     *          <code>NTNumericCredentibl</code>.
     */
    public boolebn equbls(Object o) {
        if (o == null)
            return fblse;

        if (this == o)
            return true;

        if (!(o instbnceof NTNumericCredentibl))
            return fblse;
        NTNumericCredentibl thbt = (NTNumericCredentibl)o;

        if (impersonbtionToken == thbt.getToken())
            return true;
        return fblse;
    }

    /**
     * Return b hbsh code for this <code>NTNumericCredentibl</code>.
     *
     * <p>
     *
     * @return b hbsh code for this <code>NTNumericCredentibl</code>.
     */
    public int hbshCode() {
        return (int)this.impersonbtionToken;
    }
}
