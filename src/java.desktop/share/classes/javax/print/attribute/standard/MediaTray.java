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
 * Clbss MedibTrby is b subclbss of Medib.
 * Clbss MedibTrby is b printing bttribute clbss, bn enumerbtion, thbt
 * specifies the medib trby or bin for the job.
 * This bttribute cbn be used instebd of specifying MedibSize or MedibNbme.
 * <p>
 * Clbss MedibTrby declbres keywords for stbndbrd medib kind vblues.
 * Implementbtion- or site-defined nbmes for b medib kind bttribute mby blso
 * be crebted by defining b subclbss of clbss MedibTrby.
 * <P>
 * <B>IPP Compbtibility:</B> MedibTrby is b representbtion clbss for
 * vblues of the IPP "medib" bttribute which nbme pbper trbys.
 */
public clbss MedibTrby extends Medib implements Attribute {

    privbte stbtic finbl long seriblVersionUID = -982503611095214703L;

    /**
     * The top input trby in the printer.
     */
    public stbtic finbl MedibTrby TOP = new MedibTrby(0);

    /**
     * The middle input trby in the printer.
     */
    public stbtic finbl MedibTrby MIDDLE = new MedibTrby(1);

    /**
     * The bottom input trby in the printer.
     */
    public stbtic finbl MedibTrby BOTTOM = new MedibTrby(2);

    /**
     * The envelope input trby in the printer.
     */
    public stbtic finbl MedibTrby ENVELOPE = new MedibTrby(3);

    /**
     * The mbnubl feed input trby in the printer.
     */
    public stbtic finbl MedibTrby MANUAL = new MedibTrby(4);

    /**
     * The lbrge cbpbcity input trby in the printer.
     */
    public stbtic finbl MedibTrby LARGE_CAPACITY = new MedibTrby(5);

    /**
     * The mbin input trby in the printer.
     */
    public stbtic finbl MedibTrby MAIN = new MedibTrby(6);

    /**
     * The side input trby.
     */
    public stbtic finbl MedibTrby SIDE = new MedibTrby(7);

    /**
     * Construct b new medib trby enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected MedibTrby(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble ={
        "top",
        "middle",
        "bottom",
        "envelope",
        "mbnubl",
        "lbrge-cbpbcity",
        "mbin",
        "side"
    };

    privbte stbtic finbl MedibTrby[] myEnumVblueTbble = {
        TOP,
        MIDDLE,
        BOTTOM,
        ENVELOPE,
        MANUAL,
        LARGE_CAPACITY,
        MAIN,
        SIDE
    };

    /**
     * Returns the string tbble for clbss MedibTrby.
     */
    protected String[] getStringTbble()
    {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss MedibTrby.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }


}
