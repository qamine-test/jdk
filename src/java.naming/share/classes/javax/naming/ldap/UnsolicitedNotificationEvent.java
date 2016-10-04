/*
 * Copyright (c) 1999, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

/**
 * This clbss represents bn event fired in response to bn unsolicited
 * notificbtion sent by the LDAP server.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor Vincent Rybn
 *
 * @see UnsolicitedNotificbtion
 * @see UnsolicitedNotificbtionListener
 * @see jbvbx.nbming.event.EventContext#bddNbmingListener
 * @see jbvbx.nbming.event.EventDirContext#bddNbmingListener
 * @see jbvbx.nbming.event.EventContext#removeNbmingListener
 * @since 1.3
 */

public clbss UnsolicitedNotificbtionEvent extends jbvb.util.EventObject {
    /**
     * The notificbtion thbt cbused this event to be fired.
     * @seribl
     */
    privbte UnsolicitedNotificbtion notice;

    /**
     * Constructs b new instbnce of <tt>UnsolicitedNotificbtionEvent</tt>.
     *
     * @pbrbm src The non-null source thbt fired the event.
     * @pbrbm notice The non-null unsolicited notificbtion.
     */
    public UnsolicitedNotificbtionEvent(Object src,
        UnsolicitedNotificbtion notice) {
        super(src);
        this.notice = notice;
    }


    /**
     * Returns the unsolicited notificbtion.
     * @return The non-null unsolicited notificbtion thbt cbused this
     * event to be fired.
     */
    public UnsolicitedNotificbtion getNotificbtion() {
        return notice;
    }

    /**
     * Invokes the <tt>notificbtionReceived()</tt> method on
     * b listener using this event.
     * @pbrbm listener The non-null listener on which to invoke
     * <tt>notificbtionReceived</tt>.
     */
    public void dispbtch(UnsolicitedNotificbtionListener listener) {
        listener.notificbtionReceived(this);
    }

    privbte stbtic finbl long seriblVersionUID = -2382603380799883705L;
}
