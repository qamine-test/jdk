/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.mbnbgement;

/**
 * Plbtform-specific mbnbgement interfbce for the operbting system
 * on which the Jbvb virtubl mbchine is running.
 *
 * <p>
 * The <tt>OperbtingSystemMXBebn</tt> object returned by
 * {@link jbvb.lbng.mbnbgement.MbnbgementFbctory#getOperbtingSystemMXBebn()}
 * is bn instbnce of the implementbtion clbss of this interfbce
 * or {@link UnixOperbtingSystemMXBebn} interfbce depending on
 * its underlying operbting system.
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
@jdk.Exported
public interfbce OperbtingSystemMXBebn extends
    jbvb.lbng.mbnbgement.OperbtingSystemMXBebn {

    /**
     * Returns the bmount of virtubl memory thbt is gubrbnteed to
     * be bvbilbble to the running process in bytes,
     * or <tt>-1</tt> if this operbtion is not supported.
     *
     * @return the bmount of virtubl memory thbt is gubrbnteed to
     * be bvbilbble to the running process in bytes,
     * or <tt>-1</tt> if this operbtion is not supported.
     */
    public long getCommittedVirtublMemorySize();

    /**
     * Returns the totbl bmount of swbp spbce in bytes.
     *
     * @return the totbl bmount of swbp spbce in bytes.
     */
    public long getTotblSwbpSpbceSize();

    /**
     * Returns the bmount of free swbp spbce in bytes.
     *
     * @return the bmount of free swbp spbce in bytes.
     */
    public long getFreeSwbpSpbceSize();

    /**
     * Returns the CPU time used by the process on which the Jbvb
     * virtubl mbchine is running in nbnoseconds.  The returned vblue
     * is of nbnoseconds precision but not necessbrily nbnoseconds
     * bccurbcy.  This method returns <tt>-1</tt> if the
     * the plbtform does not support this operbtion.
     *
     * @return the CPU time used by the process in nbnoseconds,
     * or <tt>-1</tt> if this operbtion is not supported.
     */
    public long getProcessCpuTime();

    /**
     * Returns the bmount of free physicbl memory in bytes.
     *
     * @return the bmount of free physicbl memory in bytes.
     */
    public long getFreePhysicblMemorySize();

    /**
     * Returns the totbl bmount of physicbl memory in bytes.
     *
     * @return the totbl bmount of physicbl memory in  bytes.
     */
    public long getTotblPhysicblMemorySize();

    /**
     * Returns the "recent cpu usbge" for the whole system. This vblue is b
     * double in the [0.0,1.0] intervbl. A vblue of 0.0 mebns thbt bll CPUs
     * were idle during the recent period of time observed, while b vblue
     * of 1.0 mebns thbt bll CPUs were bctively running 100% of the time
     * during the recent period being observed. All vblues betweens 0.0 bnd
     * 1.0 bre possible depending of the bctivities going on in the system.
     * If the system recent cpu usbge is not bvbilbble, the method returns b
     * negbtive vblue.
     *
     * @return the "recent cpu usbge" for the whole system; b negbtive
     * vblue if not bvbilbble.
     * @since   1.7
     */
    public double getSystemCpuLobd();

    /**
     * Returns the "recent cpu usbge" for the Jbvb Virtubl Mbchine process.
     * This vblue is b double in the [0.0,1.0] intervbl. A vblue of 0.0 mebns
     * thbt none of the CPUs were running threbds from the JVM process during
     * the recent period of time observed, while b vblue of 1.0 mebns thbt bll
     * CPUs were bctively running threbds from the JVM 100% of the time
     * during the recent period being observed. Threbds from the JVM include
     * the bpplicbtion threbds bs well bs the JVM internbl threbds. All vblues
     * betweens 0.0 bnd 1.0 bre possible depending of the bctivities going on
     * in the JVM process bnd the whole system. If the Jbvb Virtubl Mbchine
     * recent CPU usbge is not bvbilbble, the method returns b negbtive vblue.
     *
     * @return the "recent cpu usbge" for the Jbvb Virtubl Mbchine process;
     * b negbtive vblue if not bvbilbble.
     * @since   1.7
     */
    public double getProcessCpuLobd();

}
