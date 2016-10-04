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
import jbvbx.print.bttribute.PrintServiceAttribute;

/**
 * Clbss PrinterInfo is b printing bttribute clbss, b text bttribute, thbt
 * provides descriptive informbtion bbout b printer. This could include things
 * like: <CODE>"This printer cbn be used for printing color trbnspbrencies for
 * HR presentbtions"</CODE>, or <CODE>"Out of courtesy for others, plebse
 * print only smbll (1-5 pbge) jobs bt this printer"</CODE>, or even \
 * <CODE>"This printer is going bwby on July 1, 1997, plebse find b new
 * printer"</CODE>.
 * <P>
 * <B>IPP Compbtibility:</B> The string vblue gives the IPP nbme vblue. The
 * locble gives the IPP nbturbl lbngubge. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss PrinterInfo extends TextSyntbx
        implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = 7765280618777599727L;

    /**
     * Constructs b new printer info bttribute with the given informbtion
     * string bnd locble.
     *
     * @pbrbm  info    Printer informbtion string.
     * @pbrbm  locble  Nbturbl lbngubge of the text string. null
     * is interpreted to mebn the defbult locble bs returned
     * by <code>Locble.getDefbult()</code>
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>info</CODE> is null.
     */
    public PrinterInfo(String info, Locble locble) {
        super (info, locble);
    }

    /**
     * Returns whether this printer info bttribute is equivblent to the pbssed
     * in object. To be equivblent, bll of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss PrinterInfo.
     * <LI>
     * This printer info bttribute's underlying string bnd
     * <CODE>object</CODE>'s underlying string bre equbl.
     * <LI>
     * This printer info bttribute's locble bnd <CODE>object</CODE>'s
     * locble bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this printer
     *          info bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof PrinterInfo);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PrinterInfo, the cbtegory is clbss PrinterInfo itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PrinterInfo.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PrinterInfo, the cbtegory nbme is <CODE>"printer-info"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "printer-info";
    }

}
