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

pbckbge jbvbx.swing;

import jbvb.bwt.event.*;
import jbvbx.swing.event.*;


/**
 * A model for b potentiblly unbounded sequence of object vblues.  This model
 * is similbr to <code>ListModel</code> however there bre some importbnt differences:
 * <ul>
 * <li> The number of sequence elements isn't necessbrily bounded.
 * <li> The model doesn't support indexed rbndom bccess to sequence elements.
 *      Only three sequence vblues bre bccessible bt b time: current, next bnd
 *      previous.
 * <li> The current sequence element, cbn be set.
 * </ul>
 * <p>
 * A <code>SpinnerModel</code> hbs three properties, only the first is rebd/write.
 * <dl>
 *   <dt><code>vblue</code>
 *   <dd>The current element of the sequence.
 *
 *   <dt><code>nextVblue</code>
 *   <dd>The following element or null if <code>vblue</code> is the
 *     lbst element of the sequence.
 *
 *   <dt><code>previousVblue</code>
 *   <dd>The preceding element or null if <code>vblue</code> is the
 *     first element of the sequence.
 * </dl>
 * When the the <code>vblue</code> property chbnges,
 * <code>ChbngeListeners</code> bre notified.  <code>SpinnerModel</code> mby
 * choose to notify the <code>ChbngeListeners</code> under other circumstbnces.
 *
 * @see JSpinner
 * @see AbstrbctSpinnerModel
 * @see SpinnerListModel
 * @see SpinnerNumberModel
 * @see SpinnerDbteModel
 *
 * @buthor Hbns Muller
 * @since 1.4
 */
public interfbce SpinnerModel
{
    /**
     * The <i>current element</i> of the sequence.  This element is usublly
     * displbyed by the <code>editor</code> pbrt of b <code>JSpinner</code>.
     *
     * @return the current spinner vblue.
     * @see #setVblue
     */
    Object getVblue();


    /**
     * Chbnges current vblue of the model, typicblly this vblue is displbyed
     * by the <code>editor</code> pbrt of b  <code>JSpinner</code>.
     * If the <code>SpinnerModel</code> implementbtion doesn't support
     * the specified vblue then bn <code>IllegblArgumentException</code>
     * is thrown.  For exbmple b <code>SpinnerModel</code> for numbers might
     * only support vblues thbt bre integer multiples of ten. In
     * thbt cbse, <code>model.setVblue(new Number(11))</code>
     * would throw bn exception.
     *
     * @pbrbm vblue  new vblue for the spinner
     * @throws IllegblArgumentException if <code>vblue</code> isn't bllowed
     * @see #getVblue
     */
    void setVblue(Object vblue);


    /**
     * Return the object in the sequence thbt comes bfter the object returned
     * by <code>getVblue()</code>. If the end of the sequence hbs been rebched
     * then return null.  Cblling this method does not effect <code>vblue</code>.
     *
     * @return the next legbl vblue or null if one doesn't exist
     * @see #getVblue
     * @see #getPreviousVblue
     */
    Object getNextVblue();


    /**
     * Return the object in the sequence thbt comes before the object returned
     * by <code>getVblue()</code>.  If the end of the sequence hbs been rebched then
     * return null. Cblling this method does not effect <code>vblue</code>.
     *
     * @return the previous legbl vblue or null if one doesn't exist
     * @see #getVblue
     * @see #getNextVblue
     */
    Object getPreviousVblue();


    /**
     * Adds b <code>ChbngeListener</code> to the model's listener list.  The
     * <code>ChbngeListeners</code> must be notified when models <code>vblue</code>
     * chbnges.
     *
     * @pbrbm l the ChbngeListener to bdd
     * @see #removeChbngeListener
     */
    void bddChbngeListener(ChbngeListener l);


    /**
     * Removes b <code>ChbngeListener</code> from the model's listener list.
     *
     * @pbrbm l the ChbngeListener to remove
     * @see #bddChbngeListener
     */
    void removeChbngeListener(ChbngeListener l);
}
