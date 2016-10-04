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

import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.io.IOException;

/*
 * An ListeningConnector thbt uses the SocketTrbnsportService
 */
public clbss SocketListeningConnector extends GenericListeningConnector {

    stbtic finbl String ARG_PORT = "port";
    stbtic finbl String ARG_LOCALADDR = "locblAddress";

    public SocketListeningConnector() {
        super(new SocketTrbnsportService());

        bddIntegerArgument(
            ARG_PORT,
            getString("socket_listening.port.lbbel"),
            getString("socket_listening.port"),
            "",
            fblse,
            0, Integer.MAX_VALUE);

        bddStringArgument(
            ARG_LOCALADDR,
            getString("socket_listening.locblbddr.lbbel"),
            getString("socket_listening.locblbddr"),
            "",                                         // defbult is wildcbrd
            fblse);

        trbnsport = new Trbnsport() {
            public String nbme() {
                return "dt_socket";     // for compbtibility rebsons
            }
        };
    }


    public String
        stbrtListening(Mbp<String,? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        String port = brgument(ARG_PORT, brgs).vblue();
        String locblbddr = brgument(ARG_LOCALADDR, brgs).vblue();

        // defbult to system chosen port
        if (port.length() == 0) {
            port = "0";
        }

        if (locblbddr.length() > 0) {
           locblbddr = locblbddr + ":" + port;
        } else {
           locblbddr = port;
        }

        return super.stbrtListening(locblbddr, brgs);
    }

    public String nbme() {
        return "com.sun.jdi.SocketListen";
    }

    public String description() {
        return getString("socket_listening.description");
    }
}
