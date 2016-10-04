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

import jbvb.util.Set;
import jbvbx.mbnbgement.remote.NotificbtionResult;
import jbvbx.mbnbgement.remote.TbrgetedNotificbtion;

/** A buffer of notificbtions received from bn MBebn server. */
public interfbce NotificbtionBuffer {
    /**
     * <p>Fetch notificbtions thbt mbtch the given listeners.</p>
     *
     * <p>The operbtion only considers notificbtions with b sequence
     * number bt lebst <code>stbrtSequenceNumber</code>.  It will tbke
     * no longer thbn <code>timeout</code>, bnd will return no more
     * thbn <code>mbxNotificbtions</code> different notificbtions.</p>
     *
     * <p>If there bre no notificbtions mbtching the criterib, the
     * operbtion will block until one brrives, subject to the
     * timeout.</p>
     *
     * @pbrbm filter bn object thbt will bdd notificbtions to b
     * {@code List<TbrgetedNotificbtion>} if they mbtch the current
     * listeners with their filters.
     * @pbrbm stbrtSequenceNumber the first sequence number to
     * consider.
     * @pbrbm timeout the mbximum time to wbit.  Mby be 0 to indicbte
     * not to wbit if there bre no notificbtions.
     * @pbrbm mbxNotificbtions the mbximum number of notificbtions to
     * return.  Mby be 0 to indicbte b wbit for eligible notificbtions
     * thbt will return b usbble <code>nextSequenceNumber</code>.  The
     * {@link TbrgetedNotificbtion} brrby in the returned {@link
     * NotificbtionResult} mby contbin more thbn this number of
     * elements but will not contbin more thbn this number of
     * different notificbtions.
     */
    public NotificbtionResult
        fetchNotificbtions(NotificbtionBufferFilter filter,
                           long stbrtSequenceNumber,
                           long timeout,
                           int mbxNotificbtions)
            throws InterruptedException;

    /**
     * <p>Discbrd this buffer.</p>
     */
    public void dispose();
}
