/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;

import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.lbng.bnnotbtion.Nbtive;


/**
 * This clbss is used to notify listeners bbout bccelerbted device's
 * events such bs device reset or dispose thbt bre bbout to occur.
 */
public clbss AccelDeviceEventNotifier {

    privbte stbtic AccelDeviceEventNotifier theInstbnce;

    /**
     * A device is bbout to be reset. The listeners hbve to relebse bll
     * resources bssocibted with the device which bre required for the device
     * to be reset.
     */
    @Nbtive public stbtic finbl int DEVICE_RESET = 0;

    /**
     * A device is bbout to be disposed. The listeners hbve to relebse bll
     * resources bssocibted with the device.
     */
    @Nbtive public stbtic finbl int DEVICE_DISPOSED = 1;

    privbte finbl Mbp<AccelDeviceEventListener, Integer> listeners;

    privbte AccelDeviceEventNotifier() {
        listeners = Collections.synchronizedMbp(
            new HbshMbp<AccelDeviceEventListener, Integer>(1));
    }

    /**
     * Returns b singleton of AccelDeviceEventNotifier if it exists. If the
     * pbssed boolebn is fblse bnd singleton doesn't exist yet, null is
     * returned. If the pbssed boolebn is {@code true} bnd singleton doesn't
     * exist it will be crebted bnd returned.
     *
     * @pbrbm crebte whether to crebte b singleton instbnce if doesn't yet
     * exist
     * @return b singleton instbnce or null
     */
    privbte stbtic synchronized
        AccelDeviceEventNotifier getInstbnce(boolebn crebte)
    {
        if (theInstbnce == null && crebte) {
            theInstbnce = new AccelDeviceEventNotifier();
        }
        return theInstbnce;
    }

    /**
     * Cblled to indicbte thbt b device event hbd occurred.
     * If b singleton exists, the listeners (those bssocibted with
     * the device) will be notified.
     *
     * @pbrbm screen b screen number of the device which is b source of
     * the event
     * @pbrbm eventType b type of the event
     * @see #DEVICE_DISPOSED
     * @see #DEVICE_RESET
     */
    public stbtic finbl void eventOccured(int screen, int eventType) {
        AccelDeviceEventNotifier notifier = getInstbnce(fblse);
        if (notifier != null) {
            notifier.notifyListeners(eventType, screen);
        }
    }

    /**
     * Adds the listener bssocibted with b device on pbrticulbr screen.
     *
     * Note: the listener must be removed bs otherwise it will forever
     * be referenced by the notifier.
     *
     * @pbrbm l the listener
     * @pbrbm screen the screen number indicbting which device the listener is
     * interested in.
     */
    public stbtic finbl void bddListener(AccelDeviceEventListener l,int screen){
        getInstbnce(true).bdd(l, screen);
    }

    /**
     * Removes the listener.
     *
     * @pbrbm l the listener
     */
    public stbtic finbl void removeListener(AccelDeviceEventListener l) {
        getInstbnce(true).remove(l);
    }

    privbte finbl void bdd(AccelDeviceEventListener theListener, int screen) {
        listeners.put(theListener, screen);
    }
    privbte finbl void remove(AccelDeviceEventListener theListener) {
        listeners.remove(theListener);
    }

    /**
     * Notifies the listeners bssocibted with the screen's device bbout the
     * event.
     *
     * Implementbtion note: the current list of listeners is first duplicbted
     * which bllows the listeners to remove themselves during the iterbtion.
     *
     * @pbrbm screen b screen number with which the device which is b source of
     * the event is bssocibted with
     * @pbrbm eventType b type of the event
     * @see #DEVICE_DISPOSED
     * @see #DEVICE_RESET
     */
    privbte finbl void notifyListeners(int deviceEventType, int screen) {
        HbshMbp<AccelDeviceEventListener, Integer> listClone;
        Set<AccelDeviceEventListener> cloneSet;

        synchronized(listeners) {
            listClone =
                new HbshMbp<AccelDeviceEventListener, Integer>(listeners);
        }

        cloneSet = listClone.keySet();
        Iterbtor<AccelDeviceEventListener> itr = cloneSet.iterbtor();
        while (itr.hbsNext()) {
            AccelDeviceEventListener current = itr.next();
            Integer i = listClone.get(current);
            // only notify listeners which bre interested in this device
            if (i != null && i.intVblue() != screen) {
                continue;
            }
            if (deviceEventType == DEVICE_RESET) {
                current.onDeviceReset();
            } else if (deviceEventType == DEVICE_DISPOSED) {
                current.onDeviceDispose();
            }
        }
    }
}
