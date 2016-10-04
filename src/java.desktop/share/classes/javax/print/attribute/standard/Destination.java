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
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss Destinbtion is b printing bttribute clbss, b URI, thbt is used to
 * indicbte bn blternbte destinbtion for the spooled printer formbtted
 * dbtb. Mbny PrintServices will not support the notion of b destinbtion
 * other thbn the printer device, bnd so will not support this bttribute.
 * <p>
 * A common use for this bttribute will be bpplicbtions which wbnt
 * to redirect output to b locbl disk file : eg."file:out.prn".
 * Note thbt proper construction of "file:" scheme URI instbnces should
 * be performed using the <code>toURI()</code> method of clbss
 * {@link jbvb.io.File File}.
 * See the documentbtion on thbt clbss for more informbtion.
 * <p>
 * If b destinbtion URI is specified in b PrintRequest bnd it is not
 * bccessible for output by the PrintService, b PrintException will be thrown.
 * The PrintException mby implement URIException to provide b more specific
 * cbuse.
 * <P>
 * <B>IPP Compbtibility:</B> Destinbtion is not bn IPP bttribute.
 *
 * @buthor  Phil Rbce.
 */
public finbl clbss Destinbtion extends URISyntbx
        implements PrintJobAttribute, PrintRequestAttribute {

    privbte stbtic finbl long seriblVersionUID = 6776739171700415321L;

    /**
     * Constructs b new destinbtion bttribute with the specified URI.
     *
     * @pbrbm  uri  URI.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>uri</CODE> is null.
     */
    public Destinbtion(URI uri) {
        super (uri);
    }

    /**
     * Returns whether this destinbtion bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss Destinbtion.
     * <LI>
     * This destinbtion bttribute's URI bnd <CODE>object</CODE>'s URI
     * bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this destinbtion
     *         bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) &&
                object instbnceof Destinbtion);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Destinbtion, the cbtegory is clbss Destinbtion itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Destinbtion.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Destinbtion, the cbtegory nbme is <CODE>"spool-dbtb-destinbtion"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "spool-dbtb-destinbtion";
    }

}
