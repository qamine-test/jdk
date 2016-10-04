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

import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.Attribute;

/**
 * Clbss Severity is b printing bttribute clbss, bn enumerbtion, thbt denotes
 * the severity of b {@link PrinterStbteRebson PrinterStbteRebson} bttribute.
 * <P>
 * Instbnces of Severity do not bppebr in b Print Service's bttribute set
 * directly. Rbther, b {@link PrinterStbteRebsons PrinterStbteRebsons}
 * bttribute bppebrs in the Print Service's bttribute set.
 *  The {@link PrinterStbteRebsons
 * PrinterStbteRebsons} bttribute contbins zero, one, or more thbn one {@link
 * PrinterStbteRebson PrinterStbteRebson} objects which pertbin to the Print
 * Service's stbtus, bnd ebch {@link PrinterStbteRebson PrinterStbteRebson}
 * object is bssocibted with b Severity level of REPORT (lebst severe),
 * WARNING, or ERROR (most severe).
 * The printer bdds b {@link PrinterStbteRebson
 * PrinterStbteRebson} object to the Print Service's
 * {@link PrinterStbteRebsons PrinterStbteRebsons} bttribute when the
 * corresponding condition becomes true
 * of the printer, bnd the printer removes the {@link PrinterStbteRebson
 * PrinterStbteRebson} object bgbin when the corresponding condition becomes
 * fblse, regbrdless of whether the Print Service's overbll
 * {@link PrinterStbte PrinterStbte} blso chbnged.
 * <P>
 * <B>IPP Compbtibility:</B>
 * <code>Severity.toString()</code> returns either "error", "wbrning", or
 * "report".  The string vblues returned by
 * ebch individubl {@link PrinterStbteRebson} bnd
 * bssocibted {@link Severity} object's <CODE>toString()</CODE>
 * methods, concbtenbted together with b hyphen (<CODE>"-"</CODE>) in
 * between, gives the IPP keyword vblue for b {@link PrinterStbteRebsons}.
 * The cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP
 * bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss Severity extends EnumSyntbx implements Attribute {

    privbte stbtic finbl long seriblVersionUID = 8781881462717925380L;

    /**
     * Indicbtes thbt the {@link PrinterStbteRebson PrinterStbteRebson} is b
     * "report" (lebst severe). An implementbtion mby choose to omit some or
     * bll reports.
     * Some reports specify finer grbnulbrity bbout the printer stbte;
     * others serve bs b precursor to b wbrning. A report must contbin nothing
     * thbt could bffect the printed output.
     */
    public stbtic finbl Severity REPORT = new Severity (0);

    /**
     * Indicbtes thbt the {@link PrinterStbteRebson PrinterStbteRebson} is b
     * "wbrning." An implementbtion mby choose to omit some or bll wbrnings.
     * Wbrnings serve bs b precursor to bn error. A wbrning must contbin
     * nothing  thbt prevents b job from completing, though in some cbses the
     * output mby be of lower qublity.
     */
    public stbtic finbl Severity WARNING = new Severity (1);

    /**
     * Indicbtes thbt the {@link PrinterStbteRebson PrinterStbteRebson} is bn
     * "error" (most severe). An implementbtion must include bll errors.
     * If this bttribute contbins one or more errors, the printer's
     * {@link PrinterStbte PrinterStbte} must be STOPPED.
     */
    public stbtic finbl Severity ERROR = new Severity (2);

    /**
     * Construct b new severity enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected Severity(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "report",
        "wbrning",
        "error"
    };

    privbte stbtic finbl Severity[] myEnumVblueTbble = {
        REPORT,
        WARNING,
        ERROR
    };

    /**
     * Returns the string tbble for clbss Severity.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss Severity.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }


    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Severity, the cbtegory is clbss Severity itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Severity.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Severit, the cbtegory nbme is <CODE>"severity"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "severity";
    }

}
