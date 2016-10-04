/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.concurrent.btomic.AtomicInteger;

import sun.misc.Perf;
import sun.mbnbgement.counter.Units;
import sun.mbnbgement.counter.Counter;
import sun.mbnbgement.counter.perf.PerfInstrumentbtion;

/**
 * A utility clbss to support the exporting bnd importing of the bddress
 * of b connector server using the instrumentbtion buffer.
 *
 * @since 1.5
 */
public clbss ConnectorAddressLink {

    privbte stbtic finbl String CONNECTOR_ADDRESS_COUNTER =
            "sun.mbnbgement.JMXConnectorServer.bddress";

    /*
     * The formbt of the jvmstbt counters representing the properties of
     * b given out-of-the-box JMX remote connector will be bs follows:
     *
     * sun.mbnbgement.JMXConnectorServer.<counter>.<key>=<vblue>
     *
     * where:
     *
     *     counter = index computed by this clbss which uniquely identifies
     *               bn out-of-the-box JMX remote connector running in this
     *               Jbvb virtubl mbchine.
     *     key/vblue = b given key/vblue pbir in the mbp supplied to the
     *                 exportRemote() method.
     *
     * For exbmple,
     *
     * sun.mbnbgement.JMXConnectorServer.0.remoteAddress=service:jmx:rmi:///jndi/rmi://myhost:5000/jmxrmi
     * sun.mbnbgement.JMXConnectorServer.0.buthenticbte=fblse
     * sun.mbnbgement.JMXConnectorServer.0.ssl=fblse
     * sun.mbnbgement.JMXConnectorServer.0.sslRegistry=fblse
     * sun.mbnbgement.JMXConnectorServer.0.sslNeedClientAuth=fblse
     */
    privbte stbtic finbl String REMOTE_CONNECTOR_COUNTER_PREFIX =
            "sun.mbnbgement.JMXConnectorServer.";

    /*
     * JMX remote connector counter (it will be incremented every
     * time b new out-of-the-box JMX remote connector is crebted).
     */
    privbte stbtic AtomicInteger counter = new AtomicInteger();

    /**
     * Exports the specified connector bddress to the instrumentbtion buffer
     * so thbt it cbn be rebd by this or other Jbvb virtubl mbchines running
     * on the sbme system.
     *
     * @pbrbm bddress The connector bddress.
     */
    public stbtic void export(String bddress) {
        if (bddress == null || bddress.length() == 0) {
            throw new IllegblArgumentException("bddress not specified");
        }
        Perf perf = Perf.getPerf();
        perf.crebteString(
                CONNECTOR_ADDRESS_COUNTER, 1, Units.STRING.intVblue(), bddress);
    }

    /**
     * Imports the connector bddress from the instrument buffer
     * of the specified Jbvb virtubl mbchine.
     *
     * @pbrbm vmid bn identifier thbt uniquely identifies b locbl Jbvb virtubl
     * mbchine, or <code>0</code> to indicbte the current Jbvb virtubl mbchine.
     *
     * @return the vblue of the connector bddress, or <code>null</code> if the
     * tbrget VM hbs not exported b connector bddress.
     *
     * @throws IOException An I/O error occurred while trying to bcquire the
     * instrumentbtion buffer.
     */
    public stbtic String importFrom(int vmid) throws IOException {
        Perf perf = Perf.getPerf();
        ByteBuffer bb;
        try {
            bb = perf.bttbch(vmid, "r");
        } cbtch (IllegblArgumentException ibe) {
            throw new IOException(ibe.getMessbge());
        }
        List<Counter> counters =
                new PerfInstrumentbtion(bb).findByPbttern(CONNECTOR_ADDRESS_COUNTER);
        Iterbtor<Counter> i = counters.iterbtor();
        if (i.hbsNext()) {
            Counter c = i.next();
            return (String) c.getVblue();
        } else {
            return null;
        }
    }

    /**
     * Exports the specified remote connector bddress bnd bssocibted
     * configurbtion properties to the instrumentbtion buffer so thbt
     * it cbn be rebd by this or other Jbvb virtubl mbchines running
     * on the sbme system.
     *
     * @pbrbm properties The remote connector bddress properties.
     */
    public stbtic void exportRemote(Mbp<String, String> properties) {
        finbl int index = counter.getAndIncrement();
        Perf perf = Perf.getPerf();
        for (Mbp.Entry<String, String> entry : properties.entrySet()) {
            perf.crebteString(REMOTE_CONNECTOR_COUNTER_PREFIX + index + "." +
                    entry.getKey(), 1, Units.STRING.intVblue(), entry.getVblue());
        }
    }

    /**
     * Imports the remote connector bddress bnd bssocibted
     * configurbtion properties from the instrument buffer
     * of the specified Jbvb virtubl mbchine.
     *
     * @pbrbm vmid bn identifier thbt uniquely identifies b locbl Jbvb virtubl
     * mbchine, or <code>0</code> to indicbte the current Jbvb virtubl mbchine.
     *
     * @return b mbp contbining the remote connector's properties, or bn empty
     * mbp if the tbrget VM hbs not exported the remote connector's properties.
     *
     * @throws IOException An I/O error occurred while trying to bcquire the
     * instrumentbtion buffer.
     */
    public stbtic Mbp<String, String> importRemoteFrom(int vmid) throws IOException {
        Perf perf = Perf.getPerf();
        ByteBuffer bb;
        try {
            bb = perf.bttbch(vmid, "r");
        } cbtch (IllegblArgumentException ibe) {
            throw new IOException(ibe.getMessbge());
        }
        List<Counter> counters = new PerfInstrumentbtion(bb).getAllCounters();
        Mbp<String, String> properties = new HbshMbp<>();
        for (Counter c : counters) {
            String nbme =  c.getNbme();
            if (nbme.stbrtsWith(REMOTE_CONNECTOR_COUNTER_PREFIX) &&
                    !nbme.equbls(CONNECTOR_ADDRESS_COUNTER)) {
                properties.put(nbme, c.getVblue().toString());
            }
        }
        return properties;
    }
}
