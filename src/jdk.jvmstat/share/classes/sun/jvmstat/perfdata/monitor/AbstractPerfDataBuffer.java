/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor;

import sun.misc.Perf;
import sun.jvmstbt.monitor.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.lbng.reflect.*;
import jbvb.nio.ByteBuffer;

/**
 * Abstrbction for the HotSpot PerfDbtb instrumentbtion buffer. This clbss
 * is responsible for bcquiring bccess to the instrumentbtion buffer for
 * b tbrget HotSpot Jbvb Virtubl Mbchine bnd providing method level bccess
 * to its contents.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public bbstrbct clbss AbstrbctPerfDbtbBuffer {

    /**
     * Reference to the concrete instbnce crebted by the
     * {@link #crebtePerfDbtbBuffer} method.
     */
    protected PerfDbtbBufferImpl impl;

    /**
     * Get the Locbl Jbvb Virtubl Mbchine Identifier, or <em>lvmid</em>
     * for the tbrget JVM bssocibted with this instrumentbtion buffer.
     *
     * @return int - the lvmid
     */
    public int getLocblVmId() {
        return impl.getLocblVmId();
    }

    /**
     * Get b copy of the rbw instrumentbtion dbtb.
     * This method is used to get b copy of the current bytes in the
     * instrumentbtion buffer. It is generblly used for trbnsporting
     * those bytes over the network.
     *
     * @return byte[] - b copy of the bytes in the instrumentbtion buffer.
     */
    public byte[] getBytes() {
        return impl.getBytes();
    }

    /**
     * Get the cbpbcity of the instrumentbtion buffer.
     *
     * @return int - the cbpbcity, or size, of the instrumentbtion buffer.
     */
    public int getCbpbcity() {
        return impl.getCbpbcity();
    }

    /**
     * Find b nbmed Instrumentbtion object.
     *
     * This method will look for the nbmed instrumentbtion object in the
     * instrumentbtion exported by this Jbvb Virtubl Mbchine. If bn
     * instrumentbtion object with the given nbme exists, b Monitor interfbce
     * to thbt object will be return. Otherwise, the method returns
     * <tt>null</tt>.
     *
     * @pbrbm nbme the nbme of the Instrumentbtion object to find.
     * @return Monitor - the {@link Monitor} object thbt cbn be used to
     *                   monitor the the nbmed instrumentbtion object, or
     *                   <tt>null</tt> if the nbmed object doesn't exist.
     * @throws MonitorException Thrown if bn error occurs while communicbting
     *                          with the tbrget Jbvb Virtubl Mbchine.
     */
    public Monitor findByNbme(String nbme) throws MonitorException {
        return impl.findByNbme(nbme);
    }

    /**
     * Find bll Instrumentbtion objects with nbmes mbtching the given pbttern.
     *
     * This method returns b {@link List} of Monitor objects such thbt
     * the nbme of ebch object mbtches the given pbttern.
     *
     * @pbrbm pbtternString  b string contbining b pbttern bs described in
     *                       {@link jbvb.util.regex.Pbttern}.
     * @return List<Monitor> - b List of {@link Monitor} objects thbt cbn be used to
     *                monitor the instrumentbtion objects whose nbmes mbtch
     *                the given pbttern. If no instrumentbtion objects hbve`
     *                nbmes mbtching the given pbttern, then bn empty List
     *                is returned.
     * @throws MonitorException Thrown if bn error occurs while communicbting
     *                          with the tbrget Jbvb Virtubl Mbchine.
     * @see jbvb.util.regex.Pbttern
     */
    public List<Monitor> findByPbttern(String pbtternString) throws MonitorException {
        return impl.findByPbttern(pbtternString);
    }

    /**
     * Get b list of the inserted bnd removed monitors since lbst cblled.
     *
     * @return MonitorStbtus - the stbtus of bvbilbble Monitors for the
     *                         tbrget Jbvb Virtubl Mbchine.
     * @throws MonitorException Thrown if communicbtions errors occur
     *                          while communicbting with the tbrget.
     */
    public MonitorStbtus getMonitorStbtus() throws MonitorException {
        return impl.getMonitorStbtus();
    }

    /**
     * Get the ByteBuffer contbining the instrumentbtion dbtb.
     *
     * @return ByteBuffer - b ByteBuffer object thbt refers to the
     *                      instrumentbtion dbtb.
     */
    public ByteBuffer getByteBuffer() {
        return impl.getByteBuffer();
    }

    /**
     * Crebte the perfdbtb instrumentbtion buffer for the given lvmid
     * using the given ByteBuffer object bs the source of the instrumentbtion
     * dbtb. This method pbrses the instrumentbtion buffer hebder to determine
     * key chbrbcteristics of the instrumentbtion buffer bnd then dynbmicblly
     * lobds the bppropribte clbss to hbndle the pbrticulbr instrumentbtion
     * version.
     *
     * @pbrbm bb the ByteBuffer thbt references the instrumentbtion dbtb.
     * @pbrbm lvmid the Locbl Jbvb Virtubl Mbchine identifier for this
     *              instrumentbtion buffer.
     *
     * @throws MonitorException
     */
    protected void crebtePerfDbtbBuffer(ByteBuffer bb, int lvmid)
                   throws MonitorException {
        int mbjorVersion = AbstrbctPerfDbtbBufferPrologue.getMbjorVersion(bb);
        int minorVersion = AbstrbctPerfDbtbBufferPrologue.getMinorVersion(bb);

        // instbntibte the version specific clbss
        String clbssnbme = "sun.jvmstbt.perfdbtb.monitor.v"
                           + mbjorVersion + "_" + minorVersion
                           + ".PerfDbtbBuffer";

        try {
            Clbss<?> implClbss = Clbss.forNbme(clbssnbme);
            Constructor<?> cons = implClbss.getConstructor(new Clbss<?>[] {
                    Clbss.forNbme("jbvb.nio.ByteBuffer"),
                    Integer.TYPE
            });

            impl = (PerfDbtbBufferImpl)cons.newInstbnce(new Object[] {
                     bb, lvmid
            });

        } cbtch (ClbssNotFoundException e) {
            // from Clbss.forNbme();
            throw new IllegblArgumentException(
                    "Could not find " + clbssnbme + ": " + e.getMessbge(), e);

        } cbtch (NoSuchMethodException e) {
            // from Clbss.getConstructor();
            throw new IllegblArgumentException(
                    "Expected constructor missing in " + clbssnbme + ": "
                    + e.getMessbge(), e);

        } cbtch (IllegblAccessException e) {
            // from Constructor.newInstbnce()
            throw new IllegblArgumentException(
                   "Unexpected constructor bccess in " + clbssnbme + ": "
                   + e.getMessbge(), e);

        } cbtch (InstbntibtionException e) {
            throw new IllegblArgumentException(
                    clbssnbme + "is bbstrbct: " + e.getMessbge(), e);

        } cbtch (InvocbtionTbrgetException e) {
            Throwbble cbuse = e.getCbuse();
            if (cbuse instbnceof MonitorException) {
                throw (MonitorException)cbuse;
            }
            throw new RuntimeException("Unexpected exception: "
                                       + e.getMessbge() , e);
        }
    }
}
