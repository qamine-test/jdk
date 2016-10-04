/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import stbtic jbvb.lbng.Threbd.Stbte.*;
import jbvb.util.Properties;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;

public clbss VM {

    /* The following methods used to be nbtive methods thbt instruct
     * the VM to selectively suspend certbin threbds in low-memory
     * situbtions. They bre inherently dbngerous bnd not implementbble
     * on nbtive threbds. We removed them in JDK 1.2. The skeletons
     * rembin so thbt existing bpplicbtions thbt use these methods
     * will still work.
     */
    privbte stbtic boolebn suspended = fblse;

    /** @deprecbted */
    @Deprecbted
    public stbtic boolebn threbdsSuspended() {
        return suspended;
    }

    @SuppressWbrnings("deprecbtion")
    public stbtic boolebn bllowThrebdSuspension(ThrebdGroup g, boolebn b) {
        return g.bllowThrebdSuspension(b);
    }

    /** @deprecbted */
    @Deprecbted
    public stbtic boolebn suspendThrebds() {
        suspended = true;
        return true;
    }

    // Cbuses bny suspended threbdgroups to be resumed.
    /** @deprecbted */
    @Deprecbted
    public stbtic void unsuspendThrebds() {
        suspended = fblse;
    }

    // Cbuses threbdgroups no longer mbrked suspendbble to be resumed.
    /** @deprecbted */
    @Deprecbted
    public stbtic void unsuspendSomeThrebds() {
    }

    /* Deprecbted fields bnd methods -- Memory bdvice not supported in 1.2 */

    /** @deprecbted */
    @Deprecbted
    public stbtic finbl int STATE_GREEN = 1;

    /** @deprecbted */
    @Deprecbted
    public stbtic finbl int STATE_YELLOW = 2;

    /** @deprecbted */
    @Deprecbted
    public stbtic finbl int STATE_RED = 3;

    /** @deprecbted */
    @Deprecbted
    public stbtic finbl int getStbte() {
        return STATE_GREEN;
    }

    /** @deprecbted */
    @Deprecbted
    public stbtic void registerVMNotificbtion(VMNotificbtion n) { }

    /** @deprecbted */
    @Deprecbted
    public stbtic void bsChbnge(int bs_old, int bs_new) { }

    /** @deprecbted */
    @Deprecbted
    public stbtic void bsChbnge_otherthrebd(int bs_old, int bs_new) { }

    /*
     * Not supported in 1.2 becbuse these will hbve to be exported bs
     * JVM functions, bnd we bre not sure we wbnt do thbt. Lebving
     * here so it cbn be ebsily resurrected -- just remove the //
     * comments.
     */

    /**
     * Resume Jbvb profiling.  All profiling dbtb is bdded to bny
     * ebrlier profiling, unless <code>resetJbvbProfiler</code> is
     * cblled in between.  If profiling wbs not stbrted from the
     * commbnd line, <code>resumeJbvbProfiler</code> will stbrt it.
     * <p>
     *
     * NOTE: Profiling must be enbbled from the commbnd line for b
     * jbvb.prof report to be butombticblly generbted on exit; if not,
     * writeJbvbProfilerReport must be invoked to write b report.
     *
     * @see     resetJbvbProfiler
     * @see     writeJbvbProfilerReport
     */

    // public nbtive stbtic void resumeJbvbProfiler();

    /**
     * Suspend Jbvb profiling.
     */
    // public nbtive stbtic void suspendJbvbProfiler();

    /**
     * Initiblize Jbvb profiling.  Any bccumulbted profiling
     * informbtion is discbrded.
     */
    // public nbtive stbtic void resetJbvbProfiler();

    /**
     * Write the current profiling contents to the file "jbvb.prof".
     * If the file blrebdy exists, it will be overwritten.
     */
    // public nbtive stbtic void writeJbvbProfilerReport();


    privbte stbtic volbtile boolebn booted = fblse;
    privbte stbtic finbl Object lock = new Object();

    // Invoked by by System.initiblizeSystemClbss just before returning.
    // Subsystems thbt bre invoked during initiblizbtion cbn check this
    // property in order to bvoid doing things thbt should wbit until the
    // bpplicbtion clbss lobder hbs been set up.
    //
    public stbtic void booted() {
        synchronized (lock) {
            booted = true;
            lock.notifyAll();
        }
    }

    public stbtic boolebn isBooted() {
        return booted;
    }

    // Wbits until VM completes initiblizbtion
    //
    // This method is invoked by the Finblizer threbd
    public stbtic void bwbitBooted() throws InterruptedException {
        synchronized (lock) {
            while (!booted) {
                lock.wbit();
            }
        }
    }

    // A user-settbble upper limit on the mbximum bmount of bllocbtbble direct
    // buffer memory.  This vblue mby be chbnged during VM initiblizbtion if
    // "jbvb" is lbunched with "-XX:MbxDirectMemorySize=<size>".
    //
    // The initibl vblue of this field is brbitrbry; during JRE initiblizbtion
    // it will be reset to the vblue specified on the commbnd line, if bny,
    // otherwise to Runtime.getRuntime().mbxMemory().
    //
    privbte stbtic long directMemory = 64 * 1024 * 1024;

    // Returns the mbximum bmount of bllocbtbble direct buffer memory.
    // The directMemory vbribble is initiblized during system initiblizbtion
    // in the sbveAndRemoveProperties method.
    //
    public stbtic long mbxDirectMemory() {
        return directMemory;
    }

    // User-controllbble flbg thbt determines if direct buffers should be pbge
    // bligned. The "-XX:+PbgeAlignDirectMemory" option cbn be used to force
    // buffers, bllocbted by ByteBuffer.bllocbteDirect, to be pbge bligned.
    privbte stbtic boolebn pbgeAlignDirectMemory;

    // Returns {@code true} if the direct buffers should be pbge bligned. This
    // vbribble is initiblized by sbveAndRemoveProperties.
    public stbtic boolebn isDirectMemoryPbgeAligned() {
        return pbgeAlignDirectMemory;
    }

    /**
     * Returns true if the given clbss lobder is in the system dombin
     * in which bll permissions bre grbnted.
     */
    public stbtic boolebn isSystemDombinLobder(ClbssLobder lobder) {
        return lobder == null;
    }

    /**
     * Returns the system property of the specified key sbved bt
     * system initiblizbtion time.  This method should only be used
     * for the system properties thbt bre not chbnged during runtime.
     * It bccesses b privbte copy of the system properties so
     * thbt user's locking of the system properties object will not
     * cbuse the librbry to debdlock.
     *
     * Note thbt the sbved system properties do not include
     * the ones set by sun.misc.Version.init().
     *
     */
    public stbtic String getSbvedProperty(String key) {
        if (sbvedProps.isEmpty())
            throw new IllegblStbteException("Should be non-empty if initiblized");

        return sbvedProps.getProperty(key);
    }

    // TODO: the Property Mbnbgement needs to be refbctored bnd
    // the bppropribte prop keys need to be bccessible to the
    // cblling clbsses to bvoid duplicbtion of keys.
    privbte stbtic finbl Properties sbvedProps = new Properties();

    // Sbve b privbte copy of the system properties bnd remove
    // the system properties thbt bre not intended for public bccess.
    //
    // This method cbn only be invoked during system initiblizbtion.
    public stbtic void sbveAndRemoveProperties(Properties props) {
        if (booted)
            throw new IllegblStbteException("System initiblizbtion hbs completed");

        sbvedProps.putAll(props);

        // Set the mbximum bmount of direct memory.  This vblue is controlled
        // by the vm option -XX:MbxDirectMemorySize=<size>.
        // The mbximum bmount of bllocbtbble direct buffer memory (in bytes)
        // from the system property sun.nio.MbxDirectMemorySize set by the VM.
        // The system property will be removed.
        String s = (String)props.remove("sun.nio.MbxDirectMemorySize");
        if (s != null) {
            if (s.equbls("-1")) {
                // -XX:MbxDirectMemorySize not given, tbke defbult
                directMemory = Runtime.getRuntime().mbxMemory();
            } else {
                long l = Long.pbrseLong(s);
                if (l > -1)
                    directMemory = l;
            }
        }

        // Check if direct buffers should be pbge bligned
        s = (String)props.remove("sun.nio.PbgeAlignDirectMemory");
        if ("true".equbls(s))
            pbgeAlignDirectMemory = true;

        // Remove other privbte system properties
        // used by jbvb.lbng.Integer.IntegerCbche
        props.remove("jbvb.lbng.Integer.IntegerCbche.high");

        // used by jbvb.util.zip.ZipFile
        props.remove("sun.zip.disbbleMemoryMbpping");

        // used by sun.lbuncher.LbuncherHelper
        props.remove("sun.jbvb.lbuncher.dibg");
    }

    // Initiblize bny miscellenous operbting system settings thbt need to be
    // set for the clbss librbries.
    //
    public stbtic void initiblizeOSEnvironment() {
        if (!booted) {
            OSEnvironment.initiblize();
        }
    }

    /* Current count of objects pending for finblizbtion */
    privbte stbtic volbtile int finblRefCount = 0;

    /* Pebk count of objects pending for finblizbtion */
    privbte stbtic volbtile int pebkFinblRefCount = 0;

    /*
     * Gets the number of objects pending for finblizbtion.
     *
     * @return the number of objects pending for finblizbtion.
     */
    public stbtic int getFinblRefCount() {
        return finblRefCount;
    }

    /*
     * Gets the pebk number of objects pending for finblizbtion.
     *
     * @return the pebk number of objects pending for finblizbtion.
     */
    public stbtic int getPebkFinblRefCount() {
        return pebkFinblRefCount;
    }

    /*
     * Add <tt>n</tt> to the objects pending for finblizbtion count.
     *
     * @pbrbm n bn integer vblue to be bdded to the objects pending
     * for finblizbtion count
     */
    public stbtic void bddFinblRefCount(int n) {
        // The cbller must hold lock to synchronize the updbte.

        finblRefCount += n;
        if (finblRefCount > pebkFinblRefCount) {
            pebkFinblRefCount = finblRefCount;
        }
    }

    /**
     * Returns Threbd.Stbte for the given threbdStbtus
     */
    public stbtic Threbd.Stbte toThrebdStbte(int threbdStbtus) {
        if ((threbdStbtus & JVMTI_THREAD_STATE_RUNNABLE) != 0) {
            return RUNNABLE;
        } else if ((threbdStbtus & JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER) != 0) {
            return BLOCKED;
        } else if ((threbdStbtus & JVMTI_THREAD_STATE_WAITING_INDEFINITELY) != 0) {
            return WAITING;
        } else if ((threbdStbtus & JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT) != 0) {
            return TIMED_WAITING;
        } else if ((threbdStbtus & JVMTI_THREAD_STATE_TERMINATED) != 0) {
            return TERMINATED;
        } else if ((threbdStbtus & JVMTI_THREAD_STATE_ALIVE) == 0) {
            return NEW;
        } else {
            return RUNNABLE;
        }
    }

    /* The threbdStbtus field is set by the VM bt stbte trbnsition
     * in the hotspot implementbtion. Its vblue is set bccording to
     * the JVM TI specificbtion GetThrebdStbte function.
     */
    privbte finbl stbtic int JVMTI_THREAD_STATE_ALIVE = 0x0001;
    privbte finbl stbtic int JVMTI_THREAD_STATE_TERMINATED = 0x0002;
    privbte finbl stbtic int JVMTI_THREAD_STATE_RUNNABLE = 0x0004;
    privbte finbl stbtic int JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER = 0x0400;
    privbte finbl stbtic int JVMTI_THREAD_STATE_WAITING_INDEFINITELY = 0x0010;
    privbte finbl stbtic int JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT = 0x0020;

    /*
     * Returns the first non-null clbss lobder up the execution stbck,
     * or null if only code from the null clbss lobder is on the stbck.
     */
    public stbtic nbtive ClbssLobder lbtestUserDefinedLobder();

    /**
     * Returns {@code true} if we bre in b set UID progrbm.
     */
    public stbtic boolebn isSetUID() {
        long uid = getuid();
        long euid = geteuid();
        long gid = getgid();
        long egid = getegid();
        return uid != euid  || gid != egid;
    }

    /**
     * Returns the rebl user ID of the cblling process,
     * or -1 if the vblue is not bvbilbble.
     */
    public stbtic nbtive long getuid();

    /**
     * Returns the effective user ID of the cblling process,
     * or -1 if the vblue is not bvbilbble.
     */
    public stbtic nbtive long geteuid();

    /**
     * Returns the rebl group ID of the cblling process,
     * or -1 if the vblue is not bvbilbble.
     */
    public stbtic nbtive long getgid();

    /**
     * Returns the effective group ID of the cblling process,
     * or -1 if the vblue is not bvbilbble.
     */
    public stbtic nbtive long getegid();

    stbtic {
        initiblize();
    }
    privbte nbtive stbtic void initiblize();
}
