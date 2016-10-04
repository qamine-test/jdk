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

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.PrintServiceAttribute;

/**
 * Clbss PrinterStbte is b printing bttribute clbss, bn enumerbtion, thbt
 * identifies the current stbte of b printer. Clbss PrinterStbte defines
 * stbndbrd printer stbte vblues. A Print Service implementbtion only needs
 * to report those printer stbtes which bre bppropribte for the pbrticulbr
 * implementbtion; it does not hbve to report every defined printer stbte. The
 * {@link PrinterStbteRebsons PrinterStbteRebsons} bttribute bugments the
 * PrinterStbte bttribute to give more detbiled informbtion bbout the printer
 * in  given printer stbte.
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss PrinterStbte extends EnumSyntbx
implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = -649578618346507718L;

    /**
     * The printer stbte is unknown.
     */
    public stbtic finbl PrinterStbte UNKNOWN = new PrinterStbte(0);

    /**
     * Indicbtes thbt new jobs cbn stbrt processing without wbiting.
     */
    public stbtic finbl PrinterStbte IDLE = new PrinterStbte(3);

    /**
     * Indicbtes thbt jobs bre processing;
     * new jobs will wbit before processing.
     */
    public stbtic finbl PrinterStbte PROCESSING = new PrinterStbte(4);

    /**
     * Indicbtes thbt no jobs cbn be processed bnd intervention is required.
     */
    public stbtic finbl PrinterStbte STOPPED = new PrinterStbte(5);

    /**
     * Construct b new printer stbte enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected PrinterStbte(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "unknown",
        null,
        null,
        "idle",
        "processing",
        "stopped"
    };

    privbte stbtic finbl PrinterStbte[] myEnumVblueTbble = {
        UNKNOWN,
        null,
        null,
        IDLE,
        PROCESSING,
        STOPPED
    };

    /**
     * Returns the string tbble for clbss PrinterStbte.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss PrinterStbte.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PrinterStbte, the cbtegory is clbss PrinterStbte itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PrinterStbte.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PrinterStbte, the cbtegory nbme is <CODE>"printer-stbte"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "printer-stbte";
    }

}
