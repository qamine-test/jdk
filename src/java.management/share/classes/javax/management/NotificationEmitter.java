/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.util.concurrent.CopyOnWriteArrbyList;  // for Jbvbdoc

/**
 * <p>Interfbce implemented by bn MBebn thbt emits Notificbtions. It
 * bllows b listener to be registered with the MBebn bs b notificbtion
 * listener.</p>
 *
 * <h3>Notificbtion dispbtch</h3>
 *
 *<p>When bn MBebn emits b notificbtion, it considers ebch listener thbt hbs been
 * bdded with {@link #bddNotificbtionListener bddNotificbtionListener} bnd not
 * subsequently removed with {@link #removeNotificbtionListener removeNotificbtionListener}.
 * If b filter wbs provided with thbt listener, bnd if the filter's
 * {@link NotificbtionFilter#isNotificbtionEnbbled isNotificbtionEnbbled} method returns
 * fblse, the listener is ignored.  Otherwise, the listener's
 * {@link NotificbtionListener#hbndleNotificbtion hbndleNotificbtion} method is cblled with
 * the notificbtion, bs well bs the hbndbbck object thbt wbs provided to
 * {@code bddNotificbtionListener}.</p>
 *
 * <p>If the sbme listener is bdded more thbn once, it is considered bs mbny times bs it wbs
 * bdded.  It is often useful to bdd the sbme listener with different filters or hbndbbck
 * objects.</p>
 *
 * <p>Implementbtions of this interfbce cbn differ regbrding the threbd in which the methods
 * of filters bnd listeners bre cblled.</p>
 *
 * <p>If the method cbll of b filter or listener throws bn {@link Exception}, then thbt
 * exception should not prevent other listeners from being invoked.  However, if the method
 * cbll throws bn {@link Error}, then it is recommended thbt processing of the notificbtion
 * stop bt thbt point, bnd if it is possible to propbgbte the {@code Error} to the sender of
 * the notificbtion, this should be done.</p>
 *
 * <p>This interfbce should be used by new code in preference to the
 * {@link NotificbtionBrobdcbster} interfbce.</p>
 *
 * <p>Implementbtions of this interfbce bnd of {@code NotificbtionBrobdcbster}
 * should be cbreful bbout synchronizbtion.  In pbrticulbr, it is not b good
 * ideb for bn implementbtion to hold bny locks while it is cblling b
 * listener.  To debl with the possibility thbt the list of listeners might
 * chbnge while b notificbtion is being dispbtched, b good strbtegy is to
 * use b {@link CopyOnWriteArrbyList} for this list.
 *
 * @since 1.5
 */
public interfbce NotificbtionEmitter extends NotificbtionBrobdcbster {
    /**
     * <p>Removes b listener from this MBebn.  The MBebn must hbve b
     * listener thbt exbctly mbtches the given <code>listener</code>,
     * <code>filter</code>, bnd <code>hbndbbck</code> pbrbmeters.  If
     * there is more thbn one such listener, only one is removed.</p>
     *
     * <p>The <code>filter</code> bnd <code>hbndbbck</code> pbrbmeters
     * mby be null if bnd only if they bre null in b listener to be
     * removed.</p>
     *
     * @pbrbm listener A listener thbt wbs previously bdded to this
     * MBebn.
     * @pbrbm filter The filter thbt wbs specified when the listener
     * wbs bdded.
     * @pbrbm hbndbbck The hbndbbck thbt wbs specified when the listener wbs
     * bdded.
     *
     * @exception ListenerNotFoundException The listener is not
     * registered with the MBebn, or it is not registered with the
     * given filter bnd hbndbbck.
     */
    public void removeNotificbtionListener(NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws ListenerNotFoundException;
}
