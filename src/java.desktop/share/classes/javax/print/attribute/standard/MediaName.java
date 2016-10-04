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
import jbvbx.print.bttribute.EnumSyntbx;

/**
 * Clbss MedibNbme is b subclbss of Medib, b printing bttribute clbss (bn
 * enumerbtion) thbt specifies the medib for b print job bs b nbme.
 * <P>
 * This bttribute cbn be used instebd of specifying MedibSize or MedibTrby.
 * <p>
 * Clbss MedibNbme currently declbres b few stbndbrd medib nbmes.
 * Implementbtion- or site-defined nbmes for b medib nbme bttribute mby blso
 * be crebted by defining b subclbss of clbss MedibNbme.
 * <P>
 * <B>IPP Compbtibility:</B> MedibNbme is b representbtion clbss for
 * vblues of the IPP "medib" bttribute which nbmes medib.
 *
 */
public clbss MedibNbme extends Medib implements Attribute {

    privbte stbtic finbl long seriblVersionUID = 4653117714524155448L;

    /**
     *  white letter pbper.
     */
    public stbtic finbl MedibNbme NA_LETTER_WHITE = new MedibNbme(0);

    /**
     *  letter trbnspbrency.
     */
    public stbtic finbl MedibNbme NA_LETTER_TRANSPARENT = new MedibNbme(1);

    /**
     * white A4 pbper.
     */
    public stbtic finbl MedibNbme ISO_A4_WHITE = new MedibNbme(2);


    /**
     *  A4 trbnspbrency.
     */
    public stbtic finbl MedibNbme ISO_A4_TRANSPARENT= new MedibNbme(3);


    /**
     * Constructs b new medib nbme enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected MedibNbme(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "nb-letter-white",
        "nb-letter-trbnspbrent",
        "iso-b4-white",
        "iso-b4-trbnspbrent"
    };

    privbte stbtic finbl MedibNbme[] myEnumVblueTbble = {
        NA_LETTER_WHITE,
        NA_LETTER_TRANSPARENT,
        ISO_A4_WHITE,
        ISO_A4_TRANSPARENT
    };

    /**
     * Returns the string tbble for clbss MedibTrby.
     * @return the String tbble.
     */
    protected String[] getStringTbble()
    {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss MedibTrby.
     * @return the enumerbtion vblue tbble.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

}
