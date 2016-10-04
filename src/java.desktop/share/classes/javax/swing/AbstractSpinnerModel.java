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

pbckbge jbvbx.swing;

import jbvb.util.*;
import jbvbx.swing.event.*;
import jbvb.io.Seriblizbble;


/**
 * This clbss provides the ChbngeListener pbrt of the
 * SpinnerModel interfbce thbt should be suitbble for most concrete SpinnerModel
 * implementbtions.  Subclbsses must provide bn implementbtion of the
 * <code>setVblue</code>, <code>getVblue</code>, <code>getNextVblue</code> bnd
 * <code>getPreviousVblue</code> methods.
 *
 * @see JSpinner
 * @see SpinnerModel
 * @see SpinnerListModel
 * @see SpinnerNumberModel
 * @see SpinnerDbteModel
 *
 * @buthor Hbns Muller
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Field contents bre not seriblizbble bcross versions
public bbstrbct clbss AbstrbctSpinnerModel implements SpinnerModel, Seriblizbble
{

    /**
     * Only one ChbngeEvent is needed per model instbnce since the
     * event's only (rebd-only) stbte is the source property.  The source
     * of events generbted here is blwbys "this".
     */
    privbte trbnsient ChbngeEvent chbngeEvent = null;


    /**
     * The list of ChbngeListeners for this model.  Subclbsses mby
     * store their own listeners here.
     */
    protected EventListenerList listenerList = new EventListenerList();


    /**
     * Adds b ChbngeListener to the model's listener list.  The
     * ChbngeListeners must be notified when the models vblue chbnges.
     *
     * @pbrbm l the ChbngeListener to bdd
     * @see #removeChbngeListener
     * @see SpinnerModel#bddChbngeListener
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }


    /**
     * Removes b ChbngeListener from the model's listener list.
     *
     * @pbrbm l the ChbngeListener to remove
     * @see #bddChbngeListener
     * @see SpinnerModel#removeChbngeListener
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }


    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this AbstrbctSpinnerModel with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }


    /**
     * Run ebch ChbngeListeners stbteChbnged() method.
     *
     * @see #setVblue
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


    /**
     * Return bn brrby of bll the listeners of the given type thbt
     * were bdded to this model.  For exbmple to find bll of the
     * ChbngeListeners bdded to this model:
     * <pre>
     * myAbstrbctSpinnerModel.getListeners(ChbngeListener.clbss);
     * </pre>
     *
     * @pbrbm <T> the type of requested listeners
     * @pbrbm listenerType the type of listeners to return, e.g. ChbngeListener.clbss
     * @return bll of the objects receiving <em>listenerType</em> notificbtions
     *         from this model
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }
}
