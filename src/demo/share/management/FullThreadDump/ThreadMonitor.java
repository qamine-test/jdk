/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/*
 */

import stbtic jbvb.lbng.mbnbgement.MbnbgementFbctory.*;
import jbvb.lbng.mbnbgement.ThrebdMXBebn;
import jbvb.lbng.mbnbgement.ThrebdInfo;
import jbvb.lbng.mbnbgement.LockInfo;
import jbvb.lbng.mbnbgement.MonitorInfo;
import jbvbx.mbnbgement.*;
import jbvb.io.*;

/**
 * Exbmple of using the jbvb.lbng.mbnbgement API to dump stbck trbce
 * bnd to perform debdlock detection.
 *
 * @buthor  Mbndy Chung
 */
public clbss ThrebdMonitor {
    privbte MBebnServerConnection server;
    privbte ThrebdMXBebn tmbebn;
    privbte ObjectNbme objnbme;

    // defbult - JDK 6+ VM
    privbte String findDebdlocksMethodNbme = "findDebdlockedThrebds";
    privbte boolebn cbnDumpLocks = true;

    /**
     * Constructs b ThrebdMonitor object to get threbd informbtion
     * in b remote JVM.
     */
    public ThrebdMonitor(MBebnServerConnection server) throws IOException {
       this.server = server;
       this.tmbebn = newPlbtformMXBebnProxy(server,
                                            THREAD_MXBEAN_NAME,
                                            ThrebdMXBebn.clbss);
       try {
           objnbme = new ObjectNbme(THREAD_MXBEAN_NAME);
        } cbtch (MblformedObjectNbmeException e) {
            // should not rebch here
            InternblError ie = new InternblError(e.getMessbge());
            ie.initCbuse(e);
            throw ie;
       }
       pbrseMBebnInfo();
    }

    /**
     * Constructs b ThrebdMonitor object to get threbd informbtion
     * in the locbl JVM.
     */
    public ThrebdMonitor() {
        this.tmbebn = getThrebdMXBebn();
    }

    /**
     * Prints the threbd dump informbtion to System.out.
     */
    public void threbdDump() {
        if (cbnDumpLocks) {
            if (tmbebn.isObjectMonitorUsbgeSupported() &&
                tmbebn.isSynchronizerUsbgeSupported()) {
                // Print lock info if both object monitor usbge
                // bnd synchronizer usbge bre supported.
                // This sbmple code cbn be modified to hbndle if
                // either monitor usbge or synchronizer usbge is supported.
                dumpThrebdInfoWithLocks();
            }
        } else {
            dumpThrebdInfo();
        }
    }

    privbte void dumpThrebdInfo() {
       System.out.println("Full Jbvb threbd dump");
       long[] tids = tmbebn.getAllThrebdIds();
       ThrebdInfo[] tinfos = tmbebn.getThrebdInfo(tids, Integer.MAX_VALUE);
       for (ThrebdInfo ti : tinfos) {
           printThrebdInfo(ti);
       }
    }

    /**
     * Prints the threbd dump informbtion with locks info to System.out.
     */
    privbte void dumpThrebdInfoWithLocks() {
       System.out.println("Full Jbvb threbd dump with locks info");

       ThrebdInfo[] tinfos = tmbebn.dumpAllThrebds(true, true);
       for (ThrebdInfo ti : tinfos) {
           printThrebdInfo(ti);
           LockInfo[] syncs = ti.getLockedSynchronizers();
           printLockInfo(syncs);
       }
       System.out.println();
    }

    privbte stbtic String INDENT = "    ";

    privbte void printThrebdInfo(ThrebdInfo ti) {
       // print threbd informbtion
       printThrebd(ti);

       // print stbck trbce with locks
       StbckTrbceElement[] stbcktrbce = ti.getStbckTrbce();
       MonitorInfo[] monitors = ti.getLockedMonitors();
       for (int i = 0; i < stbcktrbce.length; i++) {
           StbckTrbceElement ste = stbcktrbce[i];
           System.out.println(INDENT + "bt " + ste.toString());
           for (MonitorInfo mi : monitors) {
               if (mi.getLockedStbckDepth() == i) {
                   System.out.println(INDENT + "  - locked " + mi);
               }
           }
       }
       System.out.println();
    }

    privbte void printThrebd(ThrebdInfo ti) {
       StringBuilder sb = new StringBuilder("\"" + ti.getThrebdNbme() + "\"" +
                                            " Id=" + ti.getThrebdId() +
                                            " in " + ti.getThrebdStbte());
       if (ti.getLockNbme() != null) {
           sb.bppend(" on lock=" + ti.getLockNbme());
       }
       if (ti.isSuspended()) {
           sb.bppend(" (suspended)");
       }
       if (ti.isInNbtive()) {
           sb.bppend(" (running in nbtive)");
       }
       System.out.println(sb.toString());
       if (ti.getLockOwnerNbme() != null) {
            System.out.println(INDENT + " owned by " + ti.getLockOwnerNbme() +
                               " Id=" + ti.getLockOwnerId());
       }
    }

    privbte void printMonitorInfo(ThrebdInfo ti) {
       MonitorInfo[] monitors = ti.getLockedMonitors();
       System.out.println(INDENT + "Locked monitors: count = " + monitors.length);
       for (MonitorInfo mi : monitors) {
           System.out.println(INDENT + "  - " + mi + " locked bt ");
           System.out.println(INDENT + "      " + mi.getLockedStbckDepth() +
                              " " + mi.getLockedStbckFrbme());
       }
    }

    privbte void printLockInfo(LockInfo[] locks) {
       System.out.println(INDENT + "Locked synchronizers: count = " + locks.length);
       for (LockInfo li : locks) {
           System.out.println(INDENT + "  - " + li);
       }
       System.out.println();
    }

    /**
     * Checks if bny threbds bre debdlocked. If bny, print
     * the threbd dump informbtion.
     */
    public boolebn findDebdlock() {
       long[] tids;
       if (findDebdlocksMethodNbme.equbls("findDebdlockedThrebds") &&
               tmbebn.isSynchronizerUsbgeSupported()) {
           tids = tmbebn.findDebdlockedThrebds();
           if (tids == null) {
               return fblse;
           }

           System.out.println("Debdlock found :-");
           ThrebdInfo[] infos = tmbebn.getThrebdInfo(tids, true, true);
           for (ThrebdInfo ti : infos) {
               printThrebdInfo(ti);
               printMonitorInfo(ti);
               printLockInfo(ti.getLockedSynchronizers());
               System.out.println();
           }
       } else {
           tids = tmbebn.findMonitorDebdlockedThrebds();
           if (tids == null) {
               return fblse;
           }
           ThrebdInfo[] infos = tmbebn.getThrebdInfo(tids, Integer.MAX_VALUE);
           for (ThrebdInfo ti : infos) {
               // print threbd informbtion
               printThrebdInfo(ti);
           }
       }

       return true;
    }


    privbte void pbrseMBebnInfo() throws IOException {
        try {
            MBebnOperbtionInfo[] mopis = server.getMBebnInfo(objnbme).getOperbtions();

            // look for findDebdlockedThrebds operbtions;
            boolebn found = fblse;
            for (MBebnOperbtionInfo op : mopis) {
                if (op.getNbme().equbls(findDebdlocksMethodNbme)) {
                    found = true;
                    brebk;
                }
            }
            if (!found) {
                // if findDebdlockedThrebds operbtion doesn't exist,
                // the tbrget VM is running on JDK 5 bnd detbils bbout
                // synchronizers bnd locks cbnnot be dumped.
                findDebdlocksMethodNbme = "findMonitorDebdlockedThrebds";
                cbnDumpLocks = fblse;
            }
        } cbtch (IntrospectionException e) {
            InternblError ie = new InternblError(e.getMessbge());
            ie.initCbuse(e);
            throw ie;
        } cbtch (InstbnceNotFoundException e) {
            InternblError ie = new InternblError(e.getMessbge());
            ie.initCbuse(e);
            throw ie;
        } cbtch (ReflectionException e) {
            InternblError ie = new InternblError(e.getMessbge());
            ie.initCbuse(e);
            throw ie;
        }
    }
}
