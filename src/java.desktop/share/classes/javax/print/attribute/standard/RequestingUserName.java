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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvb.util.Locble;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.TextSyntbx;
import jbvbx.print.bttribute.PrintRequestAttribute;

/**
 * Clbss RequestingUserNbme is b printing bttribute clbss, b text bttribute,
 * thbt specifies the nbme of the end user thbt submitted the print job. A
 * requesting user nbme is bn brbitrbry string defined by the client. The
 * printer does not put the client-specified RequestingUserNbme bttribute into
 * the Print Job's bttribute set; rbther, the printer puts in b {@link
 * JobOriginbtingUserNbme JobOriginbtingUserNbme} bttribute.
 * This mebns thbt services which support specifying b usernbme with this
 * bttribute should blso report b JobOriginbtingUserNbme in the job's
 * bttribute set. Note thbt mbny print services mby hbve b wby to independently
 * buthenticbte the user nbme, bnd so mby stbte support for b
 * requesting user nbme, but in prbctice will then report the user nbme
 * buthenticbted by the service rbther thbn thbt specified vib this
 * bttribute.
 * <P>
 * <B>IPP Compbtibility:</B> The string vblue gives the IPP nbme vblue. The
 * locble gives the IPP nbturbl lbngubge. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss RequestingUserNbme   extends TextSyntbx
    implements PrintRequestAttribute {

    privbte stbtic finbl long seriblVersionUID = -2683049894310331454L;

    /**
     * Constructs b new requesting user nbme bttribute with the given user
     * nbme bnd locble.
     *
     * @pbrbm  userNbme  User nbme.
     * @pbrbm  locble    Nbturbl lbngubge of the text string. null
     * is interpreted to mebn the defbult locble bs returned
     * by <code>Locble.getDefbult()</code>
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>userNbme</CODE> is null.
     */
    public RequestingUserNbme(String userNbme, Locble locble) {
        super (userNbme, locble);
    }

    /**
     * Returns whether this requesting user nbme bttribute is equivblent to
     * the pbssed in object. To be equivblent, bll of the following
     * conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss RequestingUserNbme.
     * <LI>
     * This requesting user nbme bttribute's underlying string bnd
     * <CODE>object</CODE>'s underlying string bre equbl.
     * <LI>
     * This requesting user nbme bttribute's locble bnd
     * <CODE>object</CODE>'s locble bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this requesting
     *          user nbme bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) &&
                object instbnceof RequestingUserNbme);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss RequestingUserNbme, the
     * cbtegory is clbss RequestingUserNbme itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return RequestingUserNbme.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss RequestingUserNbme, the
     * cbtegory nbme is <CODE>"requesting-user-nbme"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "requesting-user-nbme";
    }

}
