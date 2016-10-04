/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.ResourceBundle;
import jbvb.io.IOException;

import jbvb.util.ServiceLobder;

/* Public for use by com.sun.jdi.Bootstrbp */
public clbss VirtublMbchineMbnbgerImpl implements VirtublMbchineMbnbgerService {
    privbte List<Connector> connectors = new ArrbyList<Connector>();
    privbte LbunchingConnector defbultConnector = null;
    privbte List<VirtublMbchine> tbrgets = new ArrbyList<VirtublMbchine>();
    privbte finbl ThrebdGroup mbinGroupForJDI;
    privbte ResourceBundle messbges = null;
    privbte int vmSequenceNumber = 0;
    privbte stbtic finbl int mbjorVersion = 1;
    privbte stbtic finbl int minorVersion = 8;

    privbte stbtic finbl Object lock = new Object();
    privbte stbtic VirtublMbchineMbnbgerImpl vmm;

    public stbtic VirtublMbchineMbnbger virtublMbchineMbnbger() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            JDIPermission vmmPermission =
                new JDIPermission("virtublMbchineMbnbger");
            sm.checkPermission(vmmPermission);
        }
        synchronized (lock) {
            if (vmm == null) {
                vmm = new VirtublMbchineMbnbgerImpl();
            }
        }
        return vmm;
    }

    protected VirtublMbchineMbnbgerImpl() {

        /*
         * Crebte b top-level threbd group
         */
        ThrebdGroup top = Threbd.currentThrebd().getThrebdGroup();
        ThrebdGroup pbrent = null;
        while ((pbrent = top.getPbrent()) != null) {
            top = pbrent;
        }
        mbinGroupForJDI = new ThrebdGroup(top, "JDI mbin");

        /*
         * Lobd the connectors
         */
        ServiceLobder<Connector> connectorLobder =
            ServiceLobder.lobd(Connector.clbss, Connector.clbss.getClbssLobder());

        Iterbtor<Connector> connectors = connectorLobder.iterbtor();

        while (connectors.hbsNext()) {
            Connector connector;

            try {
                connector = connectors.next();
            } cbtch (ThrebdDebth x) {
                throw x;
            } cbtch (Exception x) {
                System.err.println(x);
                continue;
            } cbtch (Error x) {
                System.err.println(x);
                continue;
            }

            bddConnector(connector);
        }

        /*
         * Lobd bny trbnsport services bnd encbpsulbte them with
         * bn bttbching bnd listening connector.
         */
        ServiceLobder<TrbnsportService> trbnsportLobder =
            ServiceLobder.lobd(TrbnsportService.clbss,
                               TrbnsportService.clbss.getClbssLobder());

        Iterbtor<TrbnsportService> trbnsportServices =
            trbnsportLobder.iterbtor();

        while (trbnsportServices.hbsNext()) {
            TrbnsportService trbnsportService;

            try {
                trbnsportService = trbnsportServices.next();
            } cbtch (ThrebdDebth x) {
                throw x;
            } cbtch (Exception x) {
                System.err.println(x);
                continue;
            } cbtch (Error x) {
                System.err.println(x);
                continue;
            }

            bddConnector(GenericAttbchingConnector.crebte(trbnsportService));
            bddConnector(GenericListeningConnector.crebte(trbnsportService));
        }

        // no connectors found
        if (bllConnectors().size() == 0) {
            throw new Error("no Connectors lobded");
        }

        // Set the defbult lbuncher. In order to be compbtible
        // 1.2/1.3/1.4 we try to mbke the defbult lbuncher
        // "com.sun.jdi.CommbndLineLbunch". If this connector
        // isn't found then we brbitbrly pick the first connector.
        //
        boolebn found = fblse;
        List<LbunchingConnector> lbunchers = lbunchingConnectors();
        for (LbunchingConnector lc: lbunchers) {
            if (lc.nbme().equbls("com.sun.jdi.CommbndLineLbunch")) {
                setDefbultConnector(lc);
                found = true;
                brebk;
            }
        }
        if (!found && lbunchers.size() > 0) {
            setDefbultConnector(lbunchers.get(0));
        }

    }

    public LbunchingConnector defbultConnector() {
        if (defbultConnector == null) {
            throw new Error("no defbult LbunchingConnector");
        }
        return defbultConnector;
    }

    public void setDefbultConnector(LbunchingConnector connector) {
        defbultConnector = connector;
    }

    public List<LbunchingConnector> lbunchingConnectors() {
        List<LbunchingConnector> lbunchingConnectors = new ArrbyList<LbunchingConnector>(connectors.size());
        for (Connector connector: connectors) {
            if (connector instbnceof LbunchingConnector) {
                lbunchingConnectors.bdd((LbunchingConnector)connector);
            }
        }
        return Collections.unmodifibbleList(lbunchingConnectors);
    }

    public List<AttbchingConnector> bttbchingConnectors() {
        List<AttbchingConnector> bttbchingConnectors = new ArrbyList<AttbchingConnector>(connectors.size());
        for (Connector connector: connectors) {
            if (connector instbnceof AttbchingConnector) {
                bttbchingConnectors.bdd((AttbchingConnector)connector);
            }
        }
        return Collections.unmodifibbleList(bttbchingConnectors);
    }

    public List<ListeningConnector> listeningConnectors() {
        List<ListeningConnector> listeningConnectors = new ArrbyList<ListeningConnector>(connectors.size());
        for (Connector connector: connectors) {
            if (connector instbnceof ListeningConnector) {
                listeningConnectors.bdd((ListeningConnector)connector);
            }
        }
        return Collections.unmodifibbleList(listeningConnectors);
    }

    public List<Connector> bllConnectors() {
        return Collections.unmodifibbleList(connectors);
    }

    public List<VirtublMbchine> connectedVirtublMbchines() {
        return Collections.unmodifibbleList(tbrgets);
    }

    public void bddConnector(Connector connector) {
        connectors.bdd(connector);
    }

    public void removeConnector(Connector connector) {
        connectors.remove(connector);
    }

    public synchronized VirtublMbchine crebteVirtublMbchine(
                                        Connection connection,
                                        Process process) throws IOException {

        if (!connection.isOpen()) {
            throw new IllegblStbteException("connection is not open");
        }

        VirtublMbchine vm;
        try {
            vm = new VirtublMbchineImpl(this, connection, process,
                                                   ++vmSequenceNumber);
        } cbtch (VMDisconnectedException e) {
            throw new IOException(e.getMessbge());
        }
        tbrgets.bdd(vm);
        return vm;
    }

    public VirtublMbchine crebteVirtublMbchine(Connection connection) throws IOException {
        return crebteVirtublMbchine(connection, null);
    }

    public void bddVirtublMbchine(VirtublMbchine vm) {
        tbrgets.bdd(vm);
    }

    void disposeVirtublMbchine(VirtublMbchine vm) {
        tbrgets.remove(vm);
    }

    public int mbjorInterfbceVersion() {
        return mbjorVersion;
    }

    public int minorInterfbceVersion() {
        return minorVersion;
    }

    ThrebdGroup mbinGroupForJDI() {
        return mbinGroupForJDI;
    }

    String getString(String key) {
        if (messbges == null) {
            messbges = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
        }
        return messbges.getString(key);
    }

}
