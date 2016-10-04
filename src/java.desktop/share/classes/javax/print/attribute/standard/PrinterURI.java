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

import jbvb.net.URI;
import jbvb.util.Locble;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.URISyntbx;
import jbvbx.print.bttribute.PrintServiceAttribute;

/**
 * Clbss PrinterURI is b printing bttribute clbss, b URI, thbt specifies the
 * globblly unique nbme of b printer.  If it hbs such b nbme, bn bdministrbtor
 * determines b printer's URI bnd sets this bttribute to thbt nbme.
 * <P>
 * <B>IPP Compbtibility:</B>  This implements the
 * IPP printer-uri bttribute. The string form returned by
 * <CODE>toString()</CODE>  gives the IPP printer-uri vblue.
 * The cbtegory nbme returned by <CODE>getNbme()</CODE>
 * gives the IPP bttribute nbme.
 *
 * @buthor  Robert Herriot
 */

public finbl clbss PrinterURI extends URISyntbx
        implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = 7923912792485606497L;

    /**
     * Constructs b new PrinterURI bttribute with the specified URI.
     *
     * @pbrbm  uri  URI of the printer
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>uri</CODE> is null.
     */
    public PrinterURI(URI uri) {
        super (uri);
    }

    /**
     * Returns whether this printer nbme bttribute is equivblent to the pbssed
     * in object. To be equivblent, bll of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss PrinterURI.
     * <LI>
     * This PrinterURI bttribute's underlying URI bnd
     * <CODE>object</CODE>'s underlying URI bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this PrinterURI
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof PrinterURI);
    }

   /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PrinterURI bnd bny vendor-defined subclbsses, the cbtegory is
     * clbss PrinterURI itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PrinterURI.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PrinterURI bnd bny vendor-defined subclbsses, the cbtegory
     * nbme is <CODE>"printer-uri"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "printer-uri";
    }

}
