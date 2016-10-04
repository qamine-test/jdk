/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.bwt.im.spi;

import jbvb.bwt.AWTException;
import jbvb.bwt.Imbge;
import jbvb.util.Locble;

/**
 * Defines methods thbt provide sufficient informbtion bbout bn input method
 * to enbble selection bnd lobding of thbt input method.
 * The input method itself is only lobded when it is bctublly used.
 *
 * @since 1.3
 */

public interfbce InputMethodDescriptor {

    /**
     * Returns the locbles supported by the corresponding input method.
     * The locble mby describe just the lbngubge, or mby blso include
     * country bnd vbribnt informbtion if needed.
     * The informbtion is used to select input methods by locble
     * ({@link jbvb.bwt.im.InputContext#selectInputMethod(Locble)}). It mby blso
     * be used to sort input methods by locble in b user-visible
     * list of input methods.
     * <p>
     * Only the input method's primbry locbles should be returned.
     * For exbmple, if b Jbpbnese input method blso hbs b pbss-through
     * mode for Rombn chbrbcters, typicblly still only Jbpbnese would
     * be returned. Thus, the list of locbles returned is typicblly
     * b subset of the locbles for which the corresponding input method's
     * implementbtion of {@link jbvb.bwt.im.spi.InputMethod#setLocble} returns true.
     * <p>
     * If {@link #hbsDynbmicLocbleList} returns true, this method is
     * cblled ebch time the informbtion is needed. This
     * gives input methods thbt depend on network resources the chbnce
     * to bdd or remove locbles bs resources become bvbilbble or
     * unbvbilbble.
     *
     * @return the locbles supported by the input method
     * @exception AWTException if it cbn be determined thbt the input method
     * is inoperbble, for exbmple, becbuse of incomplete instbllbtion.
     */
    Locble[] getAvbilbbleLocbles() throws AWTException;

    /**
     * Returns whether the list of bvbilbble locbles cbn chbnge
     * bt runtime. This mby be the cbse, for exbmple, for bdbpters
     * thbt bccess rebl input methods over the network.
     * @return whether the list of bvbilbble locbles cbn chbnge bt
     * runtime
     */
    boolebn hbsDynbmicLocbleList();

    /**
     * Returns the user-visible nbme of the corresponding
     * input method for the given input locble in the lbngubge in which
     * the nbme will be displbyed.
     * <p>
     * The inputLocble pbrbmeter specifies the locble for which text
     * is input.
     * This pbrbmeter cbn only tbke vblues obtbined from this descriptor's
     * {@link #getAvbilbbleLocbles} method or null. If it is null, bn
     * input locble independent nbme for the input method should be
     * returned.
     * <p>
     * If b nbme for the desired displby lbngubge is not bvbilbble, the
     * method mby fbll bbck to some other lbngubge.
     *
     * @pbrbm inputLocble the locble for which text input is supported, or null
     * @pbrbm displbyLbngubge the lbngubge in which the nbme will be displbyed
     * @return the user-visible nbme of the corresponding input method
     * for the given input locble in the lbngubge in which the nbme
     * will be displbyed
     */
    String getInputMethodDisplbyNbme(Locble inputLocble, Locble displbyLbngubge);

    /**
     * Returns bn icon for the corresponding input method.
     * The icon mby be used by b user interfbce for selecting input methods.
     * <p>
     * The inputLocble pbrbmeter specifies the locble for which text
     * is input.
     * This pbrbmeter cbn only tbke vblues obtbined from this descriptor's
     * {@link #getAvbilbbleLocbles} method or null. If it is null, bn
     * input locble independent icon for the input method should be
     * returned.
     * <p>
     * The icon's size should be 16&times;16 pixels.
     *
     * @pbrbm inputLocble the locble for which text input is supported, or null
     * @return bn icon for the corresponding input method, or null
     */
    Imbge getInputMethodIcon(Locble inputLocble);

    /**
     * Crebtes b new instbnce of the corresponding input method.
     *
     * @return b new instbnce of the corresponding input method
     * @exception Exception bny exception thbt mby occur while crebting the
     * input method instbnce
     */
    InputMethod crebteInputMethod() throws Exception;
}
