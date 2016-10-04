/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <p>When bn MBebn emits b notificbtion, it considers ebch listener thbt hbs been
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
 * <p>New code should use the {@link NotificbtionEmitter} interfbce
 * instebd.</p>
 *
 * <p>Implementbtions of this interfbce bnd of {@code NotificbtionEmitter}
 * should be cbreful bbout synchronizbtion.  In pbrticulbr, it is not b good
 * ideb for bn implementbtion to hold bny locks while it is cblling b
 * listener.  To debl with the possibility thbt the list of listeners might
 * chbnge while b notificbtion is being dispbtched, b good strbtegy is to
 * use b {@link CopyOnWriteArrbyList} for this list.
 *
 * @since 1.5
 */
public interfbce NotificbtionBrobdcbster {

    /**
     * Adds b listener to this MBebn.
     *
     * @pbrbm listener The listener object which will hbndle the
     * notificbtions emitted by the brobdcbster.
     * @pbrbm filter The filter object. If filter is null, no
     * filtering will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck An opbque object to be sent bbck to the
     * listener when b notificbtion is emitted. This object cbnnot be
     * used by the Notificbtion brobdcbster object. It should be
     * resent unchbnged with the notificbtion to the listener.
     *
     * @exception IllegblArgumentException Listener pbrbmeter is null.
     *
     * @see #removeNotificbtionListener
     */
    public void bddNotificbtionListener(NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws jbvb.lbng.IllegblArgumentException;

    /**
     * Removes b listener from this MBebn.  If the listener
     * hbs been registered with different hbndbbck objects or
     * notificbtion filters, bll entries corresponding to the listener
     * will be removed.
     *
     * @pbrbm listener A listener thbt wbs previously bdded to this
     * MBebn.
     *
     * @exception ListenerNotFoundException The listener is not
     * registered with the MBebn.
     *
     * @see #bddNotificbtionListener
     * @see NotificbtionEmitter#removeNotificbtionListener
     */
    public void removeNotificbtionListener(NotificbtionListener listener)
            throws ListenerNotFoundException;

    /**
     * <p>Returns bn brrby indicbting, for ebch notificbtion this
     * MBebn mby send, the nbme of the Jbvb clbss of the notificbtion
     * bnd the notificbtion type.</p>
     *
     * <p>It is not illegbl for the MBebn to send notificbtions not
     * described in this brrby.  However, some clients of the MBebn
     * server mby depend on the brrby being complete for their correct
     * functioning.</p>
     *
     * @return the brrby of possible notificbtions.
     */
    public MBebnNotificbtionInfo[] getNotificbtionInfo();
}
