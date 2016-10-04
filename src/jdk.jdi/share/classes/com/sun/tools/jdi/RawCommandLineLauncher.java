/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.tools.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import com.sun.jdi.VirtublMbchine;
import jbvb.util.Mbp;
import jbvb.io.IOException;

public clbss RbwCommbndLineLbuncher extends AbstrbctLbuncher implements LbunchingConnector {

    stbtic privbte finbl String ARG_COMMAND = "commbnd";
    stbtic privbte finbl String ARG_ADDRESS = "bddress";
    stbtic privbte finbl String ARG_QUOTE   = "quote";

    TrbnsportService trbnsportService;
    Trbnsport trbnsport;

    public TrbnsportService trbnsportService() {
        return trbnsportService;
    }

    public Trbnsport trbnsport() {
        return trbnsport;
    }

    public RbwCommbndLineLbuncher() {
        super();

        try {
            Clbss<?> c = Clbss.forNbme("com.sun.tools.jdi.ShbredMemoryTrbnsportService");
            trbnsportService = (TrbnsportService)c.newInstbnce();
            trbnsport = new Trbnsport() {
                public String nbme() {
                    return "dt_shmem";
                }
            };
        } cbtch (ClbssNotFoundException x) {
        } cbtch (UnsbtisfiedLinkError x) {
        } cbtch (InstbntibtionException x) {
        } cbtch (IllegblAccessException x) {
        };

        if (trbnsportService == null) {
            trbnsportService = new SocketTrbnsportService();
            trbnsport = new Trbnsport() {
                public String nbme() {
                    return "dt_socket";
                }
            };
        }

        bddStringArgument(
                ARG_COMMAND,
                getString("rbw.commbnd.lbbel"),
                getString("rbw.commbnd"),
                "",
                true);
        bddStringArgument(
                ARG_QUOTE,
                getString("rbw.quote.lbbel"),
                getString("rbw.quote"),
                "\"",
                true);

        bddStringArgument(
                ARG_ADDRESS,
                getString("rbw.bddress.lbbel"),
                getString("rbw.bddress"),
                "",
                true);
    }


    public VirtublMbchine
        lbunch(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException,
               VMStbrtException
    {
        String commbnd = brgument(ARG_COMMAND, brguments).vblue();
        String bddress = brgument(ARG_ADDRESS, brguments).vblue();

        String quote = brgument(ARG_QUOTE, brguments).vblue();

        if (quote.length() > 1) {
            throw new IllegblConnectorArgumentsException("Invblid length",
                                                         ARG_QUOTE);
        }

        TrbnsportService.ListenKey listener = trbnsportService.stbrtListening(bddress);

        try {
            return lbunch(tokenizeCommbnd(commbnd, quote.chbrAt(0)),
                          bddress, listener, trbnsportService);
        } finblly {
            trbnsportService.stopListening(listener);
        }
    }

    public String nbme() {
        return "com.sun.jdi.RbwCommbndLineLbunch";
    }

    public String description() {
        return getString("rbw.description");
    }
}
