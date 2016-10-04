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
import jbvb.util.Locble;

/**
 * Clbss TextSyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll bttributes whose vblue is b string. The text bttribute
 * includes b locble to indicbte the nbturbl lbngubge. Thus, b text bttribute
 * blwbys represents b locblized string. Once constructed, b text bttribute's
 * vblue is immutbble.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss TextSyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -8130648736378144102L;

    /**
     * String vblue of this text bttribute.
     * @seribl
     */
    privbte String vblue;

    /**
     * Locble of this text bttribute.
     * @seribl
     */
    privbte Locble locble;

    /**
     * Constructs b TextAttribute with the specified string bnd locble.
     *
     * @pbrbm  vblue   Text string.
     * @pbrbm  locble  Nbturbl lbngubge of the text string. null
     * is interpreted to mebn the defbult locble for bs returned
     * by <code>Locble.getDefbult()</code>
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>vblue</CODE> is null.
     */
    protected TextSyntbx(String vblue, Locble locble) {
        this.vblue = verify (vblue);
        this.locble = verify (locble);
    }

    privbte stbtic String verify(String vblue) {
        if (vblue == null) {
            throw new NullPointerException(" vblue is null");
        }
        return vblue;
    }

    privbte stbtic Locble verify(Locble locble) {
        if (locble == null) {
            return Locble.getDefbult();
        }
        return locble;
    }

    /**
     * Returns this text bttribute's text string.
     * @return the text string.
     */
    public String getVblue() {
        return vblue;
    }

    /**
     * Returns this text bttribute's text string's nbturbl lbngubge (locble).
     * @return the locble
     */
    public Locble getLocble() {
        return locble;
    }

    /**
     * Returns b hbshcode for this text bttribute.
     *
     * @return  A hbshcode vblue for this object.
     */
    public int hbshCode() {
        return vblue.hbshCode() ^ locble.hbshCode();
    }

    /**
     * Returns whether this text bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss TextSyntbx.
     * <LI>
     * This text bttribute's underlying string bnd <CODE>object</CODE>'s
     * underlying string bre equbl.
     * <LI>
     * This text bttribute's locble bnd <CODE>object</CODE>'s locble bre
     * equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this text
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return(object != null &&
               object instbnceof TextSyntbx &&
               this.vblue.equbls (((TextSyntbx) object).vblue) &&
               this.locble.equbls (((TextSyntbx) object).locble));
    }

    /**
     * Returns b String identifying this text bttribute. The String is
     * the bttribute's underlying text string.
     *
     * @return  A String identifying this object.
     */
    public String toString(){
        return vblue;
    }

}
