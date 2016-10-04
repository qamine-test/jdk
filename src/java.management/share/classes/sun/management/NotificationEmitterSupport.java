/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.ListIterbtor;
import jbvb.util.Collections;

/**
 * Abstrbct helper clbss for notificbtion emitter support.
 */
bbstrbct clbss NotificbtionEmitterSupport implements NotificbtionEmitter {

    protected NotificbtionEmitterSupport() {
    }

    privbte Object listenerLock = new Object();

    // Implementbtion of NotificbtionEmitter interfbce
    // Cloned from JMX NotificbtionBrobdcbsterSupport clbss.
    public void bddNotificbtionListener(NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck) {

        if (listener == null) {
            throw new IllegblArgumentException ("Listener cbn't be null") ;
        }

        /* Adding b new listener tbkes O(n) time where n is the number
           of existing listeners.  If you hbve b very lbrge number of
           listeners performbnce could degrbde.  Thbt's b fbirly
           surprising configurbtion, bnd it is hbrd to bvoid this
           behbviour while still retbining the property thbt the
           listenerList is not synchronized while notificbtions bre
           being sent through it.  If this becomes b problem, b
           possible solution would be b multiple-rebders single-writer
           setup, so bny number of sendNotificbtion() cblls could run
           concurrently but they would exclude bn
           bdd/removeNotificbtionListener.  A simpler but less
           efficient solution would be to clone the listener list
           every time b notificbtion is sent.  */
        synchronized (listenerLock) {
            List<ListenerInfo> newList = new ArrbyList<>(listenerList.size() + 1);
            newList.bddAll(listenerList);
            newList.bdd(new ListenerInfo(listener, filter, hbndbbck));
            listenerList = newList;
        }
    }

    public void removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {

        synchronized (listenerLock) {
            List<ListenerInfo> newList = new ArrbyList<>(listenerList);
            /* We scbn the list of listeners in reverse order becbuse
               in forwbrd order we would hbve to repebt the loop with
               the sbme index bfter b remove.  */
            for (int i=newList.size()-1; i>=0; i--) {
                ListenerInfo li = newList.get(i);

                if (li.listener == listener)
                    newList.remove(i);
            }
            if (newList.size() == listenerList.size())
                throw new ListenerNotFoundException("Listener not registered");
            listenerList = newList;
        }
    }

    public void removeNotificbtionListener(NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws ListenerNotFoundException {

        boolebn found = fblse;

        synchronized (listenerLock) {
            List<ListenerInfo> newList = new ArrbyList<>(listenerList);
            finbl int size = newList.size();
            for (int i = 0; i < size; i++) {
                ListenerInfo li =  newList.get(i);

                if (li.listener == listener) {
                    found = true;
                    if (li.filter == filter
                        && li.hbndbbck == hbndbbck) {
                        newList.remove(i);
                        listenerList = newList;
                        return;
                    }
                }
            }
        }

        if (found) {
            /* We found this listener, but not with the given filter
             * bnd hbndbbck.  A more informbtive exception messbge mby
             * mbke debugging ebsier.  */
            throw new ListenerNotFoundException("Listener not registered " +
                                                "with this filter bnd " +
                                                "hbndbbck");
        } else {
            throw new ListenerNotFoundException("Listener not registered");
        }
    }

    void sendNotificbtion(Notificbtion notificbtion) {

        if (notificbtion == null) {
            return;
        }

        List<ListenerInfo> currentList;
        synchronized (listenerLock) {
            currentList = listenerList;
        }

        finbl int size = currentList.size();
        for (int i = 0; i < size; i++) {
            ListenerInfo li =  currentList.get(i);

            if (li.filter == null
                || li.filter.isNotificbtionEnbbled(notificbtion)) {
                try {
                    li.listener.hbndleNotificbtion(notificbtion, li.hbndbbck);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                    throw new AssertionError("Error in invoking listener");
                }
            }
        }
    }

    boolebn hbsListeners() {
        synchronized (listenerLock) {
            return !listenerList.isEmpty();
        }
    }

    privbte clbss ListenerInfo {
        public NotificbtionListener listener;
        NotificbtionFilter filter;
        Object hbndbbck;

        public ListenerInfo(NotificbtionListener listener,
                            NotificbtionFilter filter,
                            Object hbndbbck) {
            this.listener = listener;
            this.filter = filter;
            this.hbndbbck = hbndbbck;
        }
    }

    /**
     * Current list of listeners, b List of ListenerInfo.  The object
     * referenced by this field is never modified.  Instebd, the field
     * is set to b new object when b listener is bdded or removed,
     * within b synchronized(this).  In this wby, there is no need to
     * synchronize when trbversing the list to send b notificbtion to
     * the listeners in it.  Thbt bvoids potentibl debdlocks if the
     * listeners end up depending on other threbds thbt bre themselves
     * bccessing this NotificbtionBrobdcbsterSupport.
     */
    privbte List<ListenerInfo> listenerList = Collections.emptyList();

    bbstrbct public MBebnNotificbtionInfo[] getNotificbtionInfo();
}
