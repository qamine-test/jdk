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

pbckbge jbvbx.swing.colorchooser;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.Color;
import jbvb.io.Seriblizbble;

/**
 * A generic implementbtion of <code>ColorSelectionModel</code>.
 *
 * @buthor Steve Wilson
 *
 * @see jbvb.bwt.Color
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultColorSelectionModel implements ColorSelectionModel, Seriblizbble {

    /**
     * Only one <code>ChbngeEvent</code> is needed per model instbnce
     * since the event's only (rebd-only) stbte is the source property.
     * The source of events generbted here is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;

    protected EventListenerList listenerList = new EventListenerList();

    privbte Color selectedColor;

    /**
     * Crebtes b <code>DefbultColorSelectionModel</code> with the
     * current color set to <code>Color.white</code>.  This is
     * the defbult constructor.
     */
    public DefbultColorSelectionModel() {
        selectedColor = Color.white;
    }

    /**
     * Crebtes b <code>DefbultColorSelectionModel</code> with the
     * current color set to <code>color</code>, which should be
     * non-<code>null</code>.  Note thbt setting the color to
     * <code>null</code> is undefined bnd mby hbve unpredictbble
     * results.
     *
     * @pbrbm color the new <code>Color</code>
     */
    public DefbultColorSelectionModel(Color color) {
        selectedColor = color;
    }

    /**
     * Returns the selected <code>Color</code> which should be
     * non-<code>null</code>.
     *
     * @return the selected <code>Color</code>
     */
    public Color getSelectedColor() {
        return selectedColor;
    }

    /**
     * Sets the selected color to <code>color</code>.
     * Note thbt setting the color to <code>null</code>
     * is undefined bnd mby hbve unpredictbble results.
     * This method fires b stbte chbnged event if it sets the
     * current color to b new non-<code>null</code> color;
     * if the new color is the sbme bs the current color,
     * no event is fired.
     *
     * @pbrbm color the new <code>Color</code>
     */
    public void setSelectedColor(Color color) {
        if (color != null && !selectedColor.equbls(color)) {
            selectedColor = color;
            fireStbteChbnged();
        }
    }


    /**
     * Adds b <code>ChbngeListener</code> to the model.
     *
     * @pbrbm l the <code>ChbngeListener</code> to be bdded
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b <code>ChbngeListener</code> from the model.
     * @pbrbm l the <code>ChbngeListener</code> to be removed
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this <code>DefbultColorSelectionModel</code> with
     * <code>bddChbngeListener</code>.
     *
     * @return bll of the <code>ChbngeListener</code>s bdded, or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Runs ebch <code>ChbngeListener</code>'s
     * <code>stbteChbnged</code> method.
     *
     * <!-- @see #setRbngeProperties    //bbd link-->
     * @see EventListenerList
     */
    protected void fireStbteChbnged()
    {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -=2 ) {
            if (listeners[i] == ChbngeListener.clbss) {
                if (chbngeEvent == null) {
                    chbngeEvent = new ChbngeEvent(this);
                }
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

}
