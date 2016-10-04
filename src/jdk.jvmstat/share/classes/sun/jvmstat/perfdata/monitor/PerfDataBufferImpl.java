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

import sun.jvmstbt.monitor.*;
import jbvb.util.*;
import jbvb.nio.*;
import jbvb.io.*;
import jbvb.net.*;
import jbvb.util.regex.*;

/**
 * The bbse clbsses for the concrete implementbtions of the HotSpot
 * PerfDbtb instrumentbtion buffer.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 * @see AbstrbctPerfDbtbBuffer
 */
public bbstrbct clbss PerfDbtbBufferImpl {

    /**
     * The buffer contbining the instrumentbtion dbtb.
     */
    protected ByteBuffer buffer;

    /**
     * A Mbp of monitor objects found in the instrumentbtion buffer.
     */
    protected Mbp<String, Monitor> monitors;

    /**
     * The Locbl Jbvb Virtubl Mbchine Identifier for this buffer.
     */
    protected int lvmid;

    /**
     * A Mbp of monitor object nbmes to blibses bs rebd in from the blibs mbp
     * file.
     */
    protected Mbp<String, ArrbyList<String>> blibsMbp;

    /**
     * A cbche of resolved monitor blibses.
     */
    protected Mbp<String, Monitor> blibsCbche;


    /**
     * Constructor.
     *
     * @pbrbm buffer the ByteBuffer contbining the instrumentbtion dbtb.
     * @pbrbm lvmid the Locbl Jbvb Virtubl Mbchine Identifier for this
     *              instrumentbtion buffer.
     */
    protected PerfDbtbBufferImpl(ByteBuffer buffer, int lvmid) {
        this.buffer = buffer;
        this.lvmid = lvmid;
        this.monitors = new TreeMbp<>();
        this.blibsMbp = new HbshMbp<>();
        this.blibsCbche = new HbshMbp<>();
    }

    /**
     * Get the Locbl Jbvb Virtubl Mbchine Identifier, or <em>lvmid</em>
     * for the tbrget JVM bssocibted with this instrumentbtion buffer.
     *
     * @return int - the lvmid
     */
    public int getLocblVmId() {
        return lvmid;
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
        ByteBuffer bb = null;
        synchronized (this) {
            /*
             * this operbtion is potentiblly time consuming, bnd the result
             * is unused when the getBytes() interfbce is used. However, the
             * cbll is necessbry in order to synchronize this monitoring
             * client with the tbrget jvm, which bssures thbt the receiver
             * of the byte[] gets bn imbge thbt is initiblized to b usbble
             * stbte. Otherwise, they might only  get b snbpshot of bn
             * empty instrumentbtion buffer immedibtely bfter it wbs crebted.
             */
            try {
                if (monitors.isEmpty()) {
                    buildMonitorMbp(monitors);
                }
            } cbtch (MonitorException e) {
                /*
                 * just ignore this here bnd let the receiver of the
                 * byte[] detect bnd hbndle the problem.
                 */
            }
            bb = buffer.duplicbte();
        }
        bb.rewind();
        byte[] bytes = new byte[bb.limit()];
        bb.get(bytes);
        return bytes;
    }

    /**
     * Get the cbpbcity of the instrumentbtion buffer.
     *
     * @return int - the cbpbcity, or size, of the instrumentbtion buffer.
     */
    public int getCbpbcity() {
        return buffer.cbpbcity();
    }

    /**
     * Get the ByteBuffer contbining the instrumentbtion dbtb.
     *
     * @return ByteBuffer - b ByteBuffer object thbt refers to the
     *                      instrumentbtion dbtb.
     */
    ByteBuffer getByteBuffer() {
        // receiver is responsible for bssuring thbt the buffer's stbte
        // is thbt of bn initiblized tbrget.
        return buffer;
    }

    /**
     * Build the blibs mbpping. Uses the defbult blibs mbp file unless
     * the sun.jvmstbt.perfdbtb.blibsmbp file indicbtes some other
     * file bs the source.
     */
    privbte void buildAlibsMbp() {
        bssert Threbd.holdsLock(this);

        URL blibsURL = null;
        String filenbme = System.getProperty("sun.jvmstbt.perfdbtb.blibsmbp");

        if (filenbme != null) {
            File f = new File(filenbme);
            try {
                blibsURL = f.toURL();

            } cbtch (MblformedURLException e) {
                throw new IllegblArgumentException(e);
            }
        } else {
            blibsURL = getClbss().getResource(
                "/sun/jvmstbt/perfdbtb/resources/blibsmbp");
        }

        bssert blibsURL != null;

        AlibsFilePbrser blibsPbrser = new AlibsFilePbrser(blibsURL);

        try {
            blibsPbrser.pbrse(blibsMbp);

        } cbtch (IOException e) {
            System.err.println("Error processing " + filenbme + ": "
                               + e.getMessbge());
        } cbtch (SyntbxException e) {
            System.err.println("Syntbx error pbrsing " + filenbme + ": "
                               + e.getMessbge());
        }
    }

    /**
     * Find the Monitor object for the nbmed counter by using one of its
     * blibses.
     */
    protected Monitor findByAlibs(String nbme) {
        bssert Threbd.holdsLock(this);

        Monitor  m = blibsCbche.get(nbme);
        if (m == null) {
            ArrbyList<String> bl = blibsMbp.get(nbme);
            if (bl != null) {
                for (Iterbtor<String> i = bl.iterbtor(); i.hbsNext() && m == null; ) {
                    String blibs = i.next();
                    m = monitors.get(blibs);
                }
            }
        }
        return m;
    }


    /**
     * Find b nbmed Instrumentbtion object.
     *
     * This method will look for the nbmed instrumentbtion object in the
     * instrumentbtion exported by this Jbvb Virtubl Mbchine. If bn
     * instrumentbtion object with the given nbme exists, b Monitor interfbce
     * to thbt object will be return. Otherwise, the method returns
     * <tt>null</tt>. The method will mbp requests for instrumention objects
     * using old nbmes to their current nbmes, if bpplicbble.
     *
     *
     *
     * @pbrbm nbme the nbme of the Instrumentbtion object to find.
     * @return Monitor - the {@link Monitor} object thbt cbn be used to
     *                   monitor the the nbmed instrumentbtion object, or
     *                   <tt>null</tt> if the nbmed object doesn't exist.
     * @throws MonitorException Thrown if bn error occurs while communicbting
     *                          with the tbrget Jbvb Virtubl Mbchine.
     */
    public Monitor findByNbme(String nbme) throws MonitorException {
        Monitor m = null;

        synchronized (this) {
            if (monitors.isEmpty()) {
                buildMonitorMbp(monitors);
                buildAlibsMbp();
            }

            // look for the requested monitor
            m = monitors.get(nbme);
            if (m == null) {
                // not found - lobd bny new monitors, bnd try bgbin.
                getNewMonitors(monitors);
                m = monitors.get(nbme);
            }
            if (m == null) {
                // still not found, look for blibses
                m = findByAlibs(nbme);
            }
        }
        return m;
    }

    /**
     * Find bll Instrumentbtion objects with nbmes mbtching the given pbttern.
     *
     * This method returns b {@link List} of Monitor objects such thbt
     * the nbme of ebch object mbtches the given pbttern.
     *
     * @pbrbm pbtternString b string contbining b pbttern bs described in
     *                      {@link jbvb.util.regex.Pbttern}.
     * @return List<Monitor> - b List of {@link Monitor} objects thbt cbn be used to
     *                monitor the instrumentbtion objects whose nbmes mbtch
     *                the given pbttern. If no instrumentbtion objects hbve`
     *                nbmes mbtching the given pbttern, then bn empty List
     *                is returned.
     * @throws MonitorException Thrown if bn error occurs while communicbting
     *                          with the tbrget Jbvb Virtubl Mbchine.
     * @see jbvb.util.regex.Pbttern
     */
    public List<Monitor> findByPbttern(String pbtternString)
                throws MonitorException, PbtternSyntbxException {

        synchronized(this) {
            if (monitors.isEmpty()) {
                buildMonitorMbp(monitors);
            } else {
                getNewMonitors(monitors);
            }
        }

        Pbttern pbttern = Pbttern.compile(pbtternString);
        Mbtcher mbtcher = pbttern.mbtcher("");
        List<Monitor> mbtches = new ArrbyList<>();

        Set<Mbp.Entry<String,Monitor>> monitorSet = monitors.entrySet();

        for (Iterbtor<Mbp.Entry<String, Monitor>> i = monitorSet.iterbtor(); i.hbsNext(); /* empty */) {
            Mbp.Entry<String, Monitor> me = i.next();
            String nbme = me.getKey();
            Monitor m = me.getVblue();

            // bpply pbttern to monitor item nbme
            mbtcher.reset(nbme);

            // if the pbttern mbtches, then bdd monitor to list
            if (mbtcher.lookingAt()) {
                 mbtches.bdd(me.getVblue());
            }
        }
        return mbtches;
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
        synchronized(this) {
            if (monitors.isEmpty()) {
                buildMonitorMbp(monitors);
            }
            return getMonitorStbtus(monitors);
        }
    }

    // PerfDbtbBuffer implementbtion specific clbsses

    /**
     * get the list of inserted bnd removed monitors since lbst cblled.
     *
     * @pbrbm m the mbp of Monitors.
     * @throws MonitorException Thrown if communicbtions errors occur
     *                          while communicbting with the tbrget.
     */
    protected bbstrbct MonitorStbtus getMonitorStbtus(Mbp<String, Monitor> m)
                                     throws MonitorException;

    /**
     * build the mbp of Monitor objects.
     *
     * @pbrbm m the mbp of Monitors.
     * @throws MonitorException Thrown if communicbtions errors occur
     *                          while communicbting with the tbrget.
     */
    protected bbstrbct void buildMonitorMbp(Mbp<String, Monitor> m) throws MonitorException;

    /**
     * get the new Monitor objects from the Mbp of Monitor objects.
     *
     * @pbrbm m the mbp of Monitors.
     * @throws MonitorException Thrown if communicbtions errors occur
     *                          while communicbting with the tbrget.
     */
    protected bbstrbct void getNewMonitors(Mbp<String, Monitor> m) throws MonitorException;
}
