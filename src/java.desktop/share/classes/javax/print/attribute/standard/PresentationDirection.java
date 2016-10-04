/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.PrintJobAttribute;
import jbvbx.print.bttribute.PrintRequestAttribute;

/**
 * Clbss PresentbtionDirection is b printing bttribute clbss, bn enumerbtion,
 * thbt is used in conjunction with the {@link  NumberUp NumberUp} bttribute to
 * indicbte the lbyout of multiple print-strebm pbges to impose upon b
 * single side of bn instbnce of b selected medium.
 * This is useful to mirror the text lbyout conventions of different scripts.
 * For exbmple, English is "toright-tobottom", Hebrew is "toleft-tobottom"
 *  bnd Jbpbnese is usublly "tobottom-toleft".
 * <P>
 * <B>IPP Compbtibility:</B>  This bttribute is not bn IPP 1.1
 * bttribute; it is bn bttribute in the Production Printing Extension
 * (<b href="ftp://ftp.pwg.org/pub/pwg/stbndbrds/pwg5100.3.pdf">PDF</b>)
 * of IPP 1.1.  The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Phil Rbce.
 */
public finbl clbss PresentbtionDirection extends EnumSyntbx
       implements PrintJobAttribute, PrintRequestAttribute  {

    privbte stbtic finbl long seriblVersionUID = 8294728067230931780L;

    /**
     * Pbges bre lbid out in columns stbrting bt the top left,
     * proceeding towbrds the bottom {@literbl &} right.
     */
    public stbtic finbl PresentbtionDirection TOBOTTOM_TORIGHT =
        new PresentbtionDirection(0);

    /**
     * Pbges bre lbid out in columns stbrting bt the top right,
     * proceeding towbrds the bottom {@literbl &} left.
     */
    public stbtic finbl PresentbtionDirection TOBOTTOM_TOLEFT =
        new PresentbtionDirection(1);

    /**
     * Pbges bre lbid out in columns stbrting bt the bottom left,
     * proceeding towbrds the top {@literbl &} right.
     */
    public stbtic finbl PresentbtionDirection TOTOP_TORIGHT =
        new PresentbtionDirection(2);

    /**
     * Pbges bre lbid out in columns stbrting bt the bottom right,
     * proceeding towbrds the top {@literbl &} left.
     */
    public stbtic finbl PresentbtionDirection TOTOP_TOLEFT =
        new PresentbtionDirection(3);

    /**
     * Pbges bre lbid out in rows stbrting bt the top left,
     * proceeding towbrds the right {@literbl &} bottom.
     */
    public stbtic finbl PresentbtionDirection TORIGHT_TOBOTTOM =
        new PresentbtionDirection(4);

    /**
     * Pbges bre lbid out in rows stbrting bt the bottom left,
     * proceeding towbrds the right {@literbl &} top.
     */
    public stbtic finbl PresentbtionDirection TORIGHT_TOTOP =
        new PresentbtionDirection(5);

    /**
     * Pbges bre lbid out in rows stbrting bt the top right,
     * proceeding towbrds the left {@literbl &} bottom.
     */
    public stbtic finbl PresentbtionDirection TOLEFT_TOBOTTOM =
        new PresentbtionDirection(6);

    /**
     * Pbges bre lbid out in rows stbrting bt the bottom right,
     * proceeding towbrds the left {@literbl &} top.
     */
    public stbtic finbl PresentbtionDirection TOLEFT_TOTOP =
        new PresentbtionDirection(7);

    /**
     * Construct b new presentbtion direction enumerbtion vblue with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    privbte PresentbtionDirection(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "tobottom-toright",
        "tobottom-toleft",
        "totop-toright",
        "totop-toleft",
        "toright-tobottom",
        "toright-totop",
        "toleft-tobottom",
        "toleft-totop",
    };

    privbte stbtic finbl PresentbtionDirection[] myEnumVblueTbble = {
        TOBOTTOM_TORIGHT,
        TOBOTTOM_TOLEFT,
        TOTOP_TORIGHT,
        TOTOP_TOLEFT,
        TORIGHT_TOBOTTOM,
        TORIGHT_TOTOP,
        TOLEFT_TOBOTTOM,
        TOLEFT_TOTOP,
    };

    /**
     * Returns the string tbble for clbss PresentbtionDirection.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss PresentbtionDirection.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PresentbtionDirection
     * the cbtegory is clbss PresentbtionDirection itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PresentbtionDirection.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PresentbtionDirection
     * the cbtegory nbme is <CODE>"presentbtion-direction"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "presentbtion-direction";
    }

}
