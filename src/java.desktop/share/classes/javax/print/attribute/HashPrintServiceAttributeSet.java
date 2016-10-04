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

pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;

/**
 * Clbss HbshPrintServiceAttributeSet provides bn bttribute set
 * which inherits its implementbtion from clbss {@link HbshAttributeSet
 * HbshAttributeSet} bnd enforces the sembntic restrictions of interfbce
 * {@link PrintServiceAttributeSet PrintServiceAttributeSet}.
 *
 * @buthor  Albn Kbminsky
 */
public clbss HbshPrintServiceAttributeSet extends HbshAttributeSet
    implements PrintServiceAttributeSet, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6642904616179203070L;

    /**
     * Construct b new, empty hbsh print service bttribute set.
     */
    public HbshPrintServiceAttributeSet() {
        super (PrintServiceAttribute.clbss);
    }


    /**
     * Construct b new hbsh print service bttribute set,
     *  initiblly populbted with the given vblue.
     *
     * @pbrbm  bttribute  Attribute vblue to bdd to the set.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>bttribute</CODE> is null.
     */
    public HbshPrintServiceAttributeSet(PrintServiceAttribute bttribute) {
        super (bttribute, PrintServiceAttribute.clbss);
    }

    /**
     * Construct b new print service bttribute set, initiblly populbted with
     * the vblues from the given brrby. The new bttribute set is populbted
     * by bdding the elements of <CODE>bttributes</CODE> brrby to the set in
     * sequence, stbrting bt index 0. Thus, lbter brrby elements mby replbce
     * ebrlier brrby elements if the brrby contbins duplicbte bttribute
     * vblues or bttribute cbtegories.
     *
     * @pbrbm  bttributes  Arrby of bttribute vblues to bdd to the set.
     *                    If null, bn empty bttribute set is constructed.
     *
     * @exception  NullPointerException
     *     (unchecked exception)
     *      Thrown if bny element of <CODE>bttributes</CODE> is null.
     */
    public HbshPrintServiceAttributeSet(PrintServiceAttribute[] bttributes) {
        super (bttributes, PrintServiceAttribute.clbss);
    }


    /**
     * Construct b new bttribute set, initiblly populbted with the
     * vblues from the  given set where the members of the bttribute set
     * bre restricted to the <code>PrintServiceAttribute</code> interfbce.
     *
     * @pbrbm  bttributes set of bttribute vblues to initiblise the set. If
     *                    null, bn empty bttribute set is constructed.
     *
     * @exception  ClbssCbstException
     *     (unchecked exception) Thrown if bny element of
     * <CODE>bttributes</CODE> is not bn instbnce of
     * <CODE>PrintServiceAttribute</CODE>.
     */
    public HbshPrintServiceAttributeSet(PrintServiceAttributeSet bttributes)
    {
        super(bttributes, PrintServiceAttribute.clbss);
    }
}
