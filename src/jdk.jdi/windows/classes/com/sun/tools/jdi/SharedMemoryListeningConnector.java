/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A ListeningConnector bbsed on the ShbredMemoryTrbnsportService
 */
public clbss ShbredMemoryListeningConnector extends GenericListeningConnector {

    stbtic finbl String ARG_NAME = "nbme";

    public ShbredMemoryListeningConnector() {
        super(new ShbredMemoryTrbnsportService());

        bddStringArgument(
            ARG_NAME,
            getString("memory_listening.nbme.lbbel"),
            getString("memory_listening.nbme"),
            "",
            fblse);

        trbnsport = new Trbnsport() {
            public String nbme() {
                return "dt_shmem";              // compbtibility
            }
        };
    }

    // override stbrtListening so thbt "nbme" brgument cbn be
    // converted into "bddress" brgument

    public String
        stbrtListening(Mbp<String, ? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        String nbme = brgument(ARG_NAME, brgs).vblue();

        // if the nbme brgument isn't specified then we use the defbult
        // bddress for the trbnsport service.
        if (nbme.length() == 0) {
            bssert trbnsportService instbnceof ShbredMemoryTrbnsportService;
            ShbredMemoryTrbnsportService ts = (ShbredMemoryTrbnsportService)trbnsportService;
            nbme = ts.defbultAddress();
        }

        return super.stbrtListening(nbme, brgs);
    }

    public String nbme() {
        return "com.sun.jdi.ShbredMemoryListen";
    }

    public String description() {
       return getString("memory_listening.description");
    }
}
