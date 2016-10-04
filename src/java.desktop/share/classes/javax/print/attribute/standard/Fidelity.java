/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Clbss Fidelity is b printing bttribute clbss, bn enumerbtion,
 * thbt indicbtes whether totbl fidelity to client supplied print request
 * bttributes is required.
 * If FIDELITY_TRUE is specified bnd b service cbnnot print the job exbctly
 * bs specified it must reject the job.
 * If FIDELITY_FALSE is specified b rebsonbble bttempt to print the job is
 * bcceptbble. If not supplied the defbult is FIDELITY_FALSE.
 *
 * <P>
 * <B>IPP Compbtibility:</B> The IPP boolebn vblue is "true" for FIDELITY_TRUE
 * bnd "fblse" for FIDELITY_FALSE. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 * See <b href="http://www.ietf.org/rfc/rfc2911.txt">RFC 2911</b> Section 15.1 for
 * b fuller description of the IPP fidelity bttribute.
 *
 */
public finbl clbss Fidelity extends EnumSyntbx
        implements PrintJobAttribute, PrintRequestAttribute {

    privbte stbtic finbl long seriblVersionUID = 6320827847329172308L;

    /**
     * The job must be printed exbctly bs specified. or else rejected.
     */
    public stbtic finbl Fidelity
        FIDELITY_TRUE = new Fidelity(0);

    /**
     * The printer should mbke rebsonbble bttempts to print the job,
     * even if it cbnnot print it exbctly bs specified.
     */
    public stbtic finbl Fidelity
        FIDELITY_FALSE = new Fidelity(1);

    /**
     * Construct b new fidelity enumerbtion vblue with the
     * given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected Fidelity(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "true",
        "fblse"
    };


    privbte stbtic finbl Fidelity[] myEnumVblueTbble = {
        FIDELITY_TRUE,
        FIDELITY_FALSE
    };

    /**
     * Returns the string tbble for clbss Fidelity.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss Fidelity.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }   /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Fidelity the cbtegory is clbss Fidelity itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Fidelity.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Fidelity the cbtegory nbme is
     * <CODE>"ipp-bttribute-fidelity"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "ipp-bttribute-fidelity";
    }

}
