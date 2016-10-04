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
pbckbge com.sun.tools.jdi;

import com.sun.jdi.VirtublMbchine;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;

/*
 * An AttbchingConnector thbt uses the SocketTrbnsportService
 */
public clbss SocketAttbchingConnector extends GenericAttbchingConnector {

    stbtic finbl String ARG_PORT = "port";
    stbtic finbl String ARG_HOST = "hostnbme";

    public SocketAttbchingConnector() {
        super(new SocketTrbnsportService());

        String defbultHostNbme = "locblhost";

        bddStringArgument(
            ARG_HOST,
            getString("socket_bttbching.host.lbbel"),
            getString("socket_bttbching.host"),
            defbultHostNbme,
            fblse);

        bddIntegerArgument(
            ARG_PORT,
            getString("socket_bttbching.port.lbbel"),
            getString("socket_bttbching.port"),
            "",
            true,
            0, Integer.MAX_VALUE);

        trbnsport = new Trbnsport() {
            public String nbme() {
                return "dt_socket";     // for compbtibility rebsons
            }
        };

    }

    /*
     * Crebte bn "bddress" from the hostnbme bnd port connector
     * brguments bnd bttbch to the tbrget VM.
     */
    public VirtublMbchine
        bttbch(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException
    {
        String host = brgument(ARG_HOST, brguments).vblue();
        if (host.length() > 0) {
            host = host + ":";
        }
        String bddress = host + brgument(ARG_PORT, brguments).vblue();
        return super.bttbch(bddress, brguments);
    }

    public String nbme() {
       return "com.sun.jdi.SocketAttbch";
    }

    public String description() {
       return getString("socket_bttbching.description");
    }
}
