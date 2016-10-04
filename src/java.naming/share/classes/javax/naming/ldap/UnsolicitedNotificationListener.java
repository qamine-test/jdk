/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.nbming.event.NbmingListener;

/**
 * This interfbce is for hbndling <tt>UnsolicitedNotificbtionEvent</tt>.
 * "Unsolicited notificbtion" is defined in
 * <A HREF="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</A>.
 * It bllows the server to send unsolicited notificbtions to the client.
 * A <tt>UnsolicitedNotificbtionListener</tt> must:
 *<ol>
 * <li>Implement this interfbce bnd its method
 * <li>Implement <tt>NbmingListener.nbmingExceptionThrown()</tt> so
 * thbt it will be notified of exceptions thrown while bttempting to
 * collect unsolicited notificbtion events.
 * <li>Register with the context using one of the <tt>bddNbmingListener()</tt>
 * methods from <tt>EventContext</tt> or <tt>EventDirContext</tt>.
 * Only the <tt>NbmingListener</tt> brgument of these methods bre bpplicbble;
 * the rest bre ignored for b <tt>UnsolicitedNotificbtionListener</tt>.
 * (These brguments might be bpplicbble to the listener if it implements
 * other listener interfbces).
 *</ol>
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor Vincent Rybn
 *
 * @see UnsolicitedNotificbtionEvent
 * @see UnsolicitedNotificbtion
 * @see jbvbx.nbming.event.EventContext#bddNbmingListener
 * @see jbvbx.nbming.event.EventDirContext#bddNbmingListener
 * @see jbvbx.nbming.event.EventContext#removeNbmingListener
 * @since 1.3
 */
public interfbce UnsolicitedNotificbtionListener extends NbmingListener {

    /**
     * Cblled when bn unsolicited notificbtion hbs been received.
     *
     * @pbrbm evt The non-null UnsolicitedNotificbtionEvent
     */
     void notificbtionReceived(UnsolicitedNotificbtionEvent evt);
}
