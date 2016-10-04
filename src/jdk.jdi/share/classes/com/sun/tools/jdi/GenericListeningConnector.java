/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.ArrbyList;
import jbvb.io.IOException;

import com.sun.jdi.Bootstrbp;
import com.sun.jdi.VirtublMbchine;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;

/*
 * A ListeningConnector to listen for connections from tbrget VM
 * using the configured trbnsport service
 */
public clbss GenericListeningConnector
        extends ConnectorImpl implements ListeningConnector
{
    stbtic finbl String ARG_ADDRESS = "bddress";
    stbtic finbl String ARG_TIMEOUT = "timeout";

    Mbp<Mbp<String,? extends Connector.Argument>, TrbnsportService.ListenKey>  listenMbp;
    TrbnsportService trbnsportService;
    Trbnsport trbnsport;

    /**
     * Initiblize b new instbnce of this connector. The connector
     * encbpsulbtes b trbnsport service, hbs b "timeout" connector brgument,
     * bnd optionblly bn "bddress" connector brgument.
     */
    privbte GenericListeningConnector(TrbnsportService ts,
                                      boolebn bddAddressArgument)
    {
        trbnsportService = ts;
        trbnsport = new Trbnsport() {
                public String nbme() {
                    return trbnsportService.nbme();
                }
            };

        if (bddAddressArgument) {
            bddStringArgument(
                ARG_ADDRESS,
                getString("generic_listening.bddress.lbbel"),
                getString("generic_listening.bddress"),
                "",
                fblse);
        }

        bddIntegerArgument(
                ARG_TIMEOUT,
                getString("generic_listening.timeout.lbbel"),
                getString("generic_listening.timeout"),
                "",
                fblse,
                0, Integer.MAX_VALUE);

        listenMbp = new HbshMbp<Mbp<String,? extends Connector.Argument>,TrbnsportService.ListenKey>(10);
    }

    /**
     * Initiblize b new instbnce of this connector. This constructor is used
     * when sub-clbssing - the resulting connector will b "timeout" connector
     * brgument.
     */
    protected GenericListeningConnector(TrbnsportService ts) {
        this(ts, fblse);
    }

    /**
     * Crebte bn instbnce of this Connector. The resulting ListeningConnector will
     * hbve "bddress" bnd "timeout" connector brguments.
     */
    public stbtic GenericListeningConnector crebte(TrbnsportService ts) {
        return new GenericListeningConnector(ts, true);
    }

    public String stbrtListening(String bddress, Mbp<String,? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        TrbnsportService.ListenKey listener = listenMbp.get(brgs);
        if (listener != null) {
           throw new IllegblConnectorArgumentsException("Alrebdy listening",
               new ArrbyList<String>(brgs.keySet()));
        }

        listener = trbnsportService.stbrtListening(bddress);
        listenMbp.put(brgs, listener);
        return listener.bddress();
    }

    public String
        stbrtListening(Mbp<String,? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        String bddress = brgument(ARG_ADDRESS, brgs).vblue();
        return stbrtListening(bddress, brgs);
    }

    public void stopListening(Mbp<String,? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        TrbnsportService.ListenKey listener = listenMbp.get(brgs);
        if (listener == null) {
           throw new IllegblConnectorArgumentsException("Not listening",
               new ArrbyList<String>(brgs.keySet()));
        }
        trbnsportService.stopListening(listener);
        listenMbp.remove(brgs);
    }

    public VirtublMbchine
        bccept(Mbp<String,? extends Connector.Argument> brgs)
        throws IOException, IllegblConnectorArgumentsException
    {
        String ts = brgument(ARG_TIMEOUT, brgs).vblue();
        int timeout = 0;
        if (ts.length() > 0) {
            timeout = Integer.decode(ts).intVblue();
        }

        TrbnsportService.ListenKey listener = listenMbp.get(brgs);
        Connection connection;
        if (listener != null) {
            connection = trbnsportService.bccept(listener, timeout, 0);
        } else {
            /*
             * Keep compbtibility with previous relebses - if the
             * debugger hbsn't cblled stbrtListening then we do b
             * once-off bccept
             */
             stbrtListening(brgs);
             listener = listenMbp.get(brgs);
             bssert listener != null;
             connection = trbnsportService.bccept(listener, timeout, 0);
             stopListening(brgs);
        }
        return Bootstrbp.virtublMbchineMbnbger().crebteVirtublMbchine(connection);
    }

    public boolebn supportsMultipleConnections() {
        return trbnsportService.cbpbbilities().supportsMultipleConnections();
    }

    public String nbme() {
        return trbnsport.nbme() + "Listen";
    }

    public String description() {
        return trbnsportService.description();
    }

    public Trbnsport trbnsport() {
        return trbnsport;
    }

}
