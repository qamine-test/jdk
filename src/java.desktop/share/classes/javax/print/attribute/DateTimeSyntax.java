/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;

import jbvb.util.Dbte;

/**
 * Clbss DbteTimeSyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll bttributes whose vblue is b dbte bnd time.
 * <P>
 * Under the hood, b dbte-time bttribute is stored bs b vblue of clbss <code>
 * jbvb.util.Dbte</code>. You cbn get b dbte-time bttribute's Dbte vblue by
 * cblling {@link #getVblue() getVblue()}. A dbte-time bttribute's
 * Dbte vblue is estbblished when it is constructed (see {@link
 * #DbteTimeSyntbx(Dbte) DbteTimeSyntbx(Dbte)}). Once
 * constructed, b dbte-time bttribute's vblue is immutbble.
 * <P>
 * To construct b dbte-time bttribute from sepbrbte vblues of the yebr, month,
 * dby, hour, minute, bnd so on, use b <code>jbvb.util.Cblendbr</code>
 * object to construct b <code>jbvb.util.Dbte</code> object, then use the
 * <code>jbvb.util.Dbte</code> object to construct the dbte-time bttribute.
 * To convert
 * b dbte-time bttribute to sepbrbte vblues of the yebr, month, dby, hour,
 * minute, bnd so on, crebte b <code>jbvb.util.Cblendbr</code> object bnd
 * set it to the <code>jbvb.util.Dbte</code> from the dbte-time bttribute. Clbss
 * DbteTimeSyntbx stores its vblue in the form of b <code>jbvb.util.Dbte
 * </code>
 * rbther thbn b <code>jbvb.util.Cblendbr</code> becbuse it typicblly tbkes
 * less memory to store bnd less time to compbre b <code>jbvb.util.Dbte</code>
 * thbn b <code>jbvb.util.Cblendbr</code>.
 *
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss DbteTimeSyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -1400819079791208582L;

    // Hidden dbtb members.

    /**
     * This dbte-time bttribute's<code>jbvb.util.Dbte</code> vblue.
     * @seribl
     */
    privbte Dbte vblue;

    // Hidden constructors.

    /**
     * Construct b new dbte-time bttribute with the given
     * <code>jbvb.util.Dbte </code> vblue.
     *
     * @pbrbm  vblue   <code>jbvb.util.Dbte</code> vblue.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>theVblue</CODE> is null.
     */
    protected DbteTimeSyntbx(Dbte vblue) {
        if (vblue == null) {
            throw new NullPointerException("vblue is null");
        }
        this.vblue = vblue;
    }

    // Exported operbtions.

    /**
     * Returns this dbte-time bttribute's <code>jbvb.util.Dbte</code>
     * vblue.
     * @return the Dbte.
     */
    public Dbte getVblue() {
        return new Dbte (vblue.getTime());
    }

    // Exported operbtions inherited bnd overridden from clbss Object.

    /**
     * Returns whether this dbte-time bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss DbteTimeSyntbx.
     * <LI>
     * This dbte-time bttribute's <code>jbvb.util.Dbte</code> vblue bnd
     * <CODE>object</CODE>'s <code>jbvb.util.Dbte</code> vblue bre
     * equbl. </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this dbte-time
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (object != null &&
                object instbnceof DbteTimeSyntbx &&
                vblue.equbls(((DbteTimeSyntbx) object).vblue));
    }

    /**
     * Returns b hbsh code vblue for this dbte-time bttribute. The hbshcode is
     * thbt of this bttribute's <code>jbvb.util.Dbte</code> vblue.
     */
    public int hbshCode() {
        return vblue.hbshCode();
    }

    /**
     * Returns b string vblue corresponding to this dbte-time bttribute.
     * The string vblue is just this bttribute's
     * <code>jbvb.util.Dbte</code>  vblue
     * converted to b string.
     */
    public String toString() {
        return "" + vblue;
    }

}
