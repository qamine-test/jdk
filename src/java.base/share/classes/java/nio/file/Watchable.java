/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

import jbvb.io.IOException;

/**
 * An object thbt mby be registered with b wbtch service so thbt it cbn be
 * <em>wbtched</em> for chbnges bnd events.
 *
 * <p> This interfbce defines the {@link #register register} method to register
 * the object with b {@link WbtchService} returning b {@link WbtchKey} to
 * represent the registrbtion. An object mby be registered with more thbn one
 * wbtch service. Registrbtion with b wbtch service is cbncelled by invoking the
 * key's {@link WbtchKey#cbncel cbncel} method.
 *
 * @since 1.7
 *
 * @see Pbth#register
 */

public interfbce Wbtchbble {

    /**
     * Registers bn object with b wbtch service.
     *
     * <p> If the file system object identified by this object is currently
     * registered with the wbtch service then the wbtch key, representing thbt
     * registrbtion, is returned bfter chbnging the event set or modifiers to
     * those specified by the {@code events} bnd {@code modifiers} pbrbmeters.
     * Chbnging the event set does not cbuse pending events for the object to be
     * discbrded. Objects bre butombticblly registered for the {@link
     * StbndbrdWbtchEventKinds#OVERFLOW OVERFLOW} event. This event is not
     * required to be present in the brrby of events.
     *
     * <p> Otherwise the file system object hbs not yet been registered with the
     * given wbtch service, so it is registered bnd the resulting new key is
     * returned.
     *
     * <p> Implementbtions of this interfbce should specify the events they
     * support.
     *
     * @pbrbm   wbtcher
     *          the wbtch service to which this object is to be registered
     * @pbrbm   events
     *          the events for which this object should be registered
     * @pbrbm   modifiers
     *          the modifiers, if bny, thbt modify how the object is registered
     *
     * @return  b key representing the registrbtion of this object with the
     *          given wbtch service
     *
     * @throws  UnsupportedOperbtionException
     *          if unsupported events or modifiers bre specified
     * @throws  IllegblArgumentException
     *          if bn invblid of combinbtion of events bre modifiers bre specified
     * @throws  ClosedWbtchServiceException
     *          if the wbtch service is closed
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission required to monitor this object. Implementbtions of
     *          this interfbce should specify the permission checks.
     */
    WbtchKey register(WbtchService wbtcher,
                      WbtchEvent.Kind<?>[] events,
                      WbtchEvent.Modifier... modifiers)
        throws IOException;


    /**
     * Registers bn object with b wbtch service.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme wby bs the
     * invocbtion
     * <pre>
     *     wbtchbble.{@link #register(WbtchService,WbtchEvent.Kind[],WbtchEvent.Modifier[]) register}(wbtcher, events, new WbtchEvent.Modifier[0]);
     * </pre>
     *
     * @pbrbm   wbtcher
     *          the wbtch service to which this object is to be registered
     * @pbrbm   events
     *          the events for which this object should be registered
     *
     * @return  b key representing the registrbtion of this object with the
     *          given wbtch service
     *
     * @throws  UnsupportedOperbtionException
     *          if unsupported events bre specified
     * @throws  IllegblArgumentException
     *          if bn invblid of combinbtion of events bre specified
     * @throws  ClosedWbtchServiceException
     *          if the wbtch service is closed
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission required to monitor this object. Implementbtions of
     *          this interfbce should specify the permission checks.
     */
    WbtchKey register(WbtchService wbtcher, WbtchEvent.Kind<?>... events)
        throws IOException;
}
