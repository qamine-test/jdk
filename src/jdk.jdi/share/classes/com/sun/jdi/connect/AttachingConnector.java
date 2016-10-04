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

import com.sun.jdi.VirtublMbchine;
import jbvb.util.Mbp;
import jbvb.io.IOException;

/**
 * A connector which bttbches to b previously running tbrget VM.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public interfbce AttbchingConnector extends Connector {
    /**
     * Attbches to b running bpplicbtion bnd bnd returns b
     * mirror of its VM.
     * <p>
     * The connector uses the given brgument mbp in
     * bttbching the bpplicbtion. These brguments will include bddressing
     * informbtion thbt identifies the VM.
     * The brgument mbp bssocibtes brgument nbme strings to instbnces
     * of {@link Connector.Argument}. The defbult brgument mbp for b
     * connector cbn be obtbined through {@link Connector#defbultArguments}.
     * Argument mbp vblues cbn be chbnged, but mbp entries should not be
     * bdded or deleted.
     *
     * @pbrbm brguments the brgument mbp to be used in lbunching the VM.
     * @return the {@link VirtublMbchine} mirror of the tbrget VM.
     *
     * @throws TrbnsportTimeoutException when the Connector encbpsulbtes
     * b trbnsport thbt supports b timeout when bttbching, b
     * {@link Connector.Argument} representing b timeout hbs been set
     * in the brgument mbp, bnd b timeout occurs when trying to bttbch
     * to the tbrget VM.
     *
     * @throws jbvb.io.IOException when unbble to bttbch.
     * Specific exceptions bre dependent on the Connector implementbtion
     * in use.
     * @throws IllegblConnectorArgumentsException when one of the
     * connector brguments is invblid.
     */
    VirtublMbchine bttbch(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException;
}
