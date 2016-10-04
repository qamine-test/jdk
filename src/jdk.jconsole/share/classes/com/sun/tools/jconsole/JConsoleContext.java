/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jconsole;

import jbvbx.mbnbgement.MBebnServerConnection;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.event.SwingPropertyChbngeSupport;

/**
 * {@code JConsoleContext} represents b JConsole connection to b tbrget
 * bpplicbtion.
 * <p>
 * {@code JConsoleContext} notifies bny {@code PropertyChbngeListeners}
 * bbout the {@linkplbin #CONNECTION_STATE_PROPERTY <i>ConnectionStbte</i>}
 * property chbnge to {@link ConnectionStbte#CONNECTED CONNECTED} bnd
 * {@link ConnectionStbte#DISCONNECTED DISCONNECTED}.
 * The {@code JConsoleContext} instbnce will be the source for
 * bny generbted events.
 * <p>
 *
 * @since 1.6
 */
@jdk.Exported
public interfbce JConsoleContext {
    /**
     * The {@link ConnectionStbte ConnectionStbte} bound property nbme.
     */
    public stbtic String CONNECTION_STATE_PROPERTY = "connectionStbte";

    /**
     * Vblues for the {@linkplbin #CONNECTION_STATE_PROPERTY
     * <i>ConnectionStbte</i>} bound property.
     */
    @jdk.Exported
    public enum ConnectionStbte {
        /**
         * The connection hbs been successfully estbblished.
         */
        CONNECTED,
        /**
         * No connection present.
         */
        DISCONNECTED,
        /**
         * The connection is being bttempted.
         */
        CONNECTING
    }

    /**
     * Returns the {@link MBebnServerConnection MBebnServerConnection} for the
     * connection to bn bpplicbtion.  The returned
     * {@code MBebnServerConnection} object becomes invblid when
     * the connection stbte is chbnged to the
     * {@link ConnectionStbte#DISCONNECTED DISCONNECTED} stbte.
     *
     * @return the {@code MBebnServerConnection} for the
     * connection to bn bpplicbtion.
     */
    public MBebnServerConnection getMBebnServerConnection();

    /**
     * Returns the current connection stbte.
     * @return the current connection stbte.
     */
    public ConnectionStbte getConnectionStbte();

    /**
     * Add b {@link jbvb.bebns.PropertyChbngeListener PropertyChbngeListener}
     * to the listener list.
     * The listener is registered for bll properties.
     * The sbme listener object mby be bdded more thbn once, bnd will be cblled
     * bs mbny times bs it is bdded.
     * If {@code listener} is {@code null}, no exception is thrown bnd
     * no bction is tbken.
     *
     * @pbrbm listener  The {@code PropertyChbngeListener} to be bdded
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener);

    /**
     * Removes b {@link jbvb.bebns.PropertyChbngeListener PropertyChbngeListener}
     * from the listener list. This
     * removes b {@code PropertyChbngeListener} thbt wbs registered for bll
     * properties. If {@code listener} wbs bdded more thbn once to the sbme
     * event source, it will be notified one less time bfter being removed. If
     * {@code listener} is {@code null}, or wbs never bdded, no exception is
     * thrown bnd no bction is tbken.
     *
     * @pbrbm listener the {@code PropertyChbngeListener} to be removed
     */
    public void removePropertyChbngeListener(PropertyChbngeListener listener);
}
