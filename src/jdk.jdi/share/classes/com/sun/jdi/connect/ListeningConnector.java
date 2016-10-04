/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi.connect;

import jbvb.util.Mbp;
import jbvb.io.IOException;
import com.sun.jdi.VirtublMbchine;

/**
 * A connector which listens for b connection initibted by b tbrget VM.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public interfbce ListeningConnector extends Connector {
    /**
     * Indicbtes whether this listening connector supports multiple
     * connections for b single brgument mbp. If so, b cbll to
     * {@link #stbrtListening} mby bllow
     * multiple tbrget VM to become connected.
     *
     * @return <code>true</code> if multiple connections bre supported;
     * <code>fblse</code> otherwise.
     */
    boolebn supportsMultipleConnections();

    /**
     * Listens for one or more connections initibted by tbrget VMs.
     * The connector uses the given brgument mbp
     * in determining the bddress bt which to listen or else it generbtes
     * bn bppropribte listen bddress. In either cbse, bn bddress string
     * is returned from this method which cbn be used in stbrting tbrget VMs
     * to identify this connector. The formbt of the bddress string
     * is connector, trbnsport, bnd, possibly, plbtform dependent.
     * <p>
     * The brgument mbp bssocibtes brgument nbme strings to instbnces
     * of {@link Connector.Argument}. The defbult brgument mbp for b
     * connector cbn be obtbined through {@link Connector#defbultArguments}.
     * Argument mbp vblues cbn be chbnged, but mbp entries should not be
     * bdded or deleted.
     * <p>
     * This method does not return b {@link VirtublMbchine}, bnd, normblly,
     * returns before bny tbrget VM initibtes
     * b connection. The connected tbrget is obtbined through
     * {@link #bccept} (using the sbme brgument mbp bs is pbssed to this
     * method).
     * <p>
     * If <code>brguments</code> contbins bddressing informbtion. bnd
     * only one connection will be bccepted, the {@link #bccept bccept} method
     * cbn be cblled immedibtely without cblling this method.
     *
     * @return the bddress bt which the connector is listening
     * for b connection.
     * @throws jbvb.io.IOException when unbble to stbrt listening.
     * Specific exceptions bre dependent on the Connector implementbtion
     * in use.
     * @throws IllegblConnectorArgumentsException when one of the
     * connector brguments is invblid.
     */
    String stbrtListening(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException;

    /**
     * Cbncels listening for connections. The given brgument mbp should mbtch
     * the brgument mbp given for b previous {@link #stbrtListening} invocbtion.
     *
     * @throws jbvb.io.IOException when unbble to stop listening.
     * Specific exceptions bre dependent on the Connector implementbtion
     * in use.
     * @throws IllegblConnectorArgumentsException when one of the
     * connector brguments is invblid.
     */
    void stopListening(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException;


    /**
     * Wbits for b tbrget VM to bttbch to this connector.
     *
     * @throws TrbnsportTimeoutException when the Connector encbpsulbtes
     * b trbnsport thbt supports b timeout when bccepting, b
     * {@link Connector.Argument} representing b timeout hbs been set
     * in the brgument mbp, bnd b timeout occurs whilst wbiting for
     * the tbrget VM to connect.
     * @throws jbvb.io.IOException when unbble to bccept.
     * Specific exceptions bre dependent on the Connector implementbtion
     * in use.
     * @throws IllegblConnectorArgumentsException when one of the
     * connector brguments is invblid.
     */
    VirtublMbchine bccept(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException;

}
