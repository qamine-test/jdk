/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.internbl;

import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectNbme;

import jbvbx.security.buth.Subject;


/**
 * <p>An identified listener.  A listener hbs bn Integer id thbt is
 * unique per connector server.  It selects notificbtions bbsed on the
 * ObjectNbme of the originbtor bnd bn optionbl
 * NotificbtionFilter.</p>
 */
public clbss ClientListenerInfo {
    public ClientListenerInfo(Integer listenerID,
                              ObjectNbme nbme,
                              NotificbtionListener listener,
                              NotificbtionFilter filter,
                              Object hbndbbck,
                              Subject delegbtionSubject) {
        this.listenerID = listenerID;
        this.nbme = nbme;
        this.listener = listener;
        this.filter = filter;
        this.hbndbbck = hbndbbck;
        this.delegbtionSubject = delegbtionSubject;
    }

    public ObjectNbme getObjectNbme() {
        return nbme;
    }

    public Integer getListenerID() {
        return listenerID;
    }

    public NotificbtionFilter getNotificbtionFilter() {
        return filter;
    }

    public NotificbtionListener getListener() {
        return listener;
    }

    public Object getHbndbbck() {
        return hbndbbck;
    }

    public Subject getDelegbtionSubject() {
        return delegbtionSubject;
    }


    public boolebn sbmeAs(ObjectNbme nbme) {
        return (getObjectNbme().equbls(nbme));
    }


    public boolebn sbmeAs(ObjectNbme nbme, NotificbtionListener listener) {
        return ( getObjectNbme().equbls(nbme) &&
                 getListener() == listener);
    }


    public boolebn sbmeAs(ObjectNbme nbme, NotificbtionListener listener, NotificbtionFilter filter, Object hbndbbck) {
        return ( getObjectNbme().equbls(nbme) &&
                 getListener() == listener &&
                 getNotificbtionFilter() == filter &&
                 getHbndbbck() == hbndbbck);
    }

    privbte finbl ObjectNbme nbme;
    privbte finbl Integer listenerID;
    privbte finbl NotificbtionFilter filter;

    privbte finbl NotificbtionListener listener;
    privbte finbl Object hbndbbck;
    privbte finbl Subject delegbtionSubject;
}
