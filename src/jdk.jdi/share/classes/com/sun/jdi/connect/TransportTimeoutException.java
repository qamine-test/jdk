/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This exception mby be thrown bs b result of b timeout
 * when bttbching to b tbrget VM, or wbiting to bccept b
 * connection from b tbrget VM.
 *
 * <p> When bttbching to b tbrget VM, using {@link
 * AttbchingConnector#bttbch bttbch} this
 * exception mby be thrown if the connector supports b timeout
 * {@link Connector.Argument connector brgument}. Similibrly,
 * when wbiting to bccept b connection from b tbrget VM,
 * using {@link ListeningConnector#bccept bccept} this
 * exception mby be thrown if the connector supports b
 * timeout connector brgument when bccepting.
 *
 * <p> In bddition, for developers crebting {@link
 * com.sun.jdi.connect.spi.TrbnsportService TrbnsportService}
 * implementbtions this exception is thrown when
 * {@link com.sun.jdi.connect.spi.TrbnsportService#bttbch bttbch}
 * times out when estbblishing b connection to b tbrget VM,
 * or {@link com.sun.jdi.connect.spi.TrbnsportService#bccept
 * bccept} times out while wbiting for b tbrget VM to connect. </p>
 *
 * @see AttbchingConnector#bttbch
 * @see ListeningConnector#bccept
 * @see com.sun.jdi.connect.spi.TrbnsportService#bttbch
 * @see com.sun.jdi.connect.spi.TrbnsportService#bccept
 *
 * @since 1.5
 */
@jdk.Exported
public clbss TrbnsportTimeoutException extends jbvb.io.IOException {
    privbte stbtic finbl long seriblVersionUID = 4107035242623365074L;
    /**
     * Constructs b <tt>TrbnsportTimeoutException</tt> with no detbil
     * messbge.
     */
    public TrbnsportTimeoutException() {
    }


    /**
     * Constructs b <tt>TrbnsportTimeoutException</tt> with the
     * specified detbil messbge.
     *
     * @pbrbm messbge the detbil messbge pertbining to this exception.
     */
    public TrbnsportTimeoutException(String messbge) {
        super(messbge);
    }

}
