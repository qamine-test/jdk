/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.util.Mbp;

import com.sun.jdi.Bootstrbp;
import com.sun.jdi.VirtublMbchine;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;

/*
 * An AttbchingConnector to bttbch to b running VM using bny
 * TrbnsportService.
 */

public clbss GenericAttbchingConnector
        extends ConnectorImpl implements AttbchingConnector
{
    /*
     * The brguments thbt this connector supports
     */
    stbtic finbl String ARG_ADDRESS = "bddress";
    stbtic finbl String ARG_TIMEOUT = "timeout";

    TrbnsportService trbnsportService;
    Trbnsport trbnsport;

    /*
     * Initiblize b new instbnce of this connector. The connector
     * encbpsulbtes b trbnsport service bnd optionblly hbs bn
     * "bddress" connector brgument.
     */
    privbte GenericAttbchingConnector(TrbnsportService ts,
                                      boolebn bddAddressArgument)
    {
        trbnsportService = ts;
        trbnsport = new Trbnsport() {
                public String nbme() {
                    // delegbte nbme to the trbnsport service
                    return trbnsportService.nbme();
                }
            };

        if (bddAddressArgument) {
            bddStringArgument(
                ARG_ADDRESS,
                getString("generic_bttbching.bddress.lbbel"),
                getString("generic_bttbching.bddress"),
                "",
                true);
        }


        bddIntegerArgument(
                ARG_TIMEOUT,
                getString("generic_bttbching.timeout.lbbel"),
                getString("generic_bttbching.timeout"),
                "",
                fblse,
                0, Integer.MAX_VALUE);
    }

    /**
     * Initiblizes b new instbnce of this connector. This constructor
     * is used when sub-clbssing - the resulting connector will hbve
     * b "timeout" connector brgument.
     */
    protected GenericAttbchingConnector(TrbnsportService ts) {
        this(ts, fblse);
    }

    /*
     * Crebte bn instbnce of this connector. The resulting AttbchingConnector
     * will hbve bddress bnd timeout connector brguments.
     */
    public stbtic GenericAttbchingConnector crebte(TrbnsportService ts) {
        return new GenericAttbchingConnector(ts, true);
    }

    /**
     * Attbch to b tbrget VM using the specified bddress bnd Connector brguments.
     */
    public VirtublMbchine bttbch(String bddress, Mbp<String, ? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        String ts  = brgument(ARG_TIMEOUT, brgs).vblue();
        int timeout = 0;
        if (ts.length() > 0) {
            timeout = Integer.decode(ts).intVblue();
        }
        Connection connection = trbnsportService.bttbch(bddress, timeout, 0);
        return Bootstrbp.virtublMbchineMbnbger().crebteVirtublMbchine(connection);
    }

    /**
     * Attbch to b tbrget VM using the specified brguments - the bddress
     * of the tbrget VM is specified by the <code>bddress</code> connector
     * brgument.
     */
    public VirtublMbchine
        bttbch(Mbp<String,? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        String bddress = brgument(ARG_ADDRESS, brgs).vblue();
        return bttbch(bddress, brgs);
    }

    public String nbme() {
        return trbnsport.nbme() + "Attbch";
    }

    public String description() {
        return trbnsportService.description();
    }

    public Trbnsport trbnsport() {
        return trbnsport;
    }

}
