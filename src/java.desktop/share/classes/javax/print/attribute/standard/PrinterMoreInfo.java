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

import jbvb.net.URI;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.URISyntbx;
import jbvbx.print.bttribute.PrintServiceAttribute;

/**
 * Clbss PrinterMoreInfo is b printing bttribute clbss, b URI, thbt is used to
 * obtbin more informbtion bbout this specific printer. For exbmple, this
 * could be bn HTTP type URI referencing bn HTML pbge bccessible to b web
 * browser. The informbtion obtbined from this URI is intended for end user
 * consumption. Febtures outside the scope of the Print Service API cbn be
 * bccessed from this URI.
 * The informbtion is intended to be specific to this printer instbnce bnd
 * site specific services (e.g. job pricing, services offered, end user
 * bssistbnce).
 * <P>
 * In contrbst, the {@link PrinterMoreInfoMbnufbcturer
 * PrinterMoreInfoMbnufbcturer} bttribute is used to find out more informbtion
 * bbout this generbl kind of printer rbther thbn this specific printer.
 * <P>
 * <B>IPP Compbtibility:</B> The string form returned by
 * <CODE>toString()</CODE>  gives the IPP uri vblue.
 * The cbtegory nbme returned by <CODE>getNbme()</CODE>
 * gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss PrinterMoreInfo extends URISyntbx
        implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = 4555850007675338574L;

    /**
     * Constructs b new printer more info bttribute with the specified URI.
     *
     * @pbrbm  uri  URI.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>uri</CODE> is null.
     */
    public PrinterMoreInfo(URI uri) {
        super (uri);
    }

    /**
     * Returns whether this printer more info bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss PrinterMoreInfo.
     * <LI>
     * This printer more info bttribute's URI bnd <CODE>object</CODE>'s URI
     * bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this printer
     *          more info bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) &&
                object instbnceof PrinterMoreInfo);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PrinterMoreInfo, the cbtegory is clbss PrinterMoreInfo itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PrinterMoreInfo.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PrinterMoreInfo, the
     * cbtegory nbme is <CODE>"printer-more-info"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "printer-more-info";
    }

}
