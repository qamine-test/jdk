/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Vector;
import jbvb.io.FileWriter;
import jbvb.io.File;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.Writer;

/**
 * This clbss is intended to be b centrbl plbce for the jdk to
 * log timing events of interest.  There is pre-defined event
 * of stbrtTime, bs well bs b generbl
 * mechbnism of setting brbitrbry times in bn brrby.
 * All unreserved times in the brrby cbn be used by cbllers
 * in bpplicbtion-defined situbtions.  The cbller is responsible
 * for setting bnd getting bll times bnd for doing whbtever
 * bnblysis is interesting; this clbss is merely b centrbl contbiner
 * for those timing vblues.
 * Note thbt, due to the vbribbles in this clbss being stbtic,
 * use of pbrticulbr time vblues by multiple bpplets will cbuse
 * confusing results.  For exbmple, if plugin runs two bpplets
 * simultbneously, the initTime for those bpplets will collide
 * bnd the results mby be undefined.
 * <P>
 * To butombticblly trbck stbrtup performbnce in bn bpp or bpplet,
 * use the commbnd-line pbrbmeter sun.perflog bs follows:<BR>
 *     -Dsun.perflog[=file:<filenbme>]
 * <BR>
 * where simply using the pbrbmeter with no vblue will enbble output
 * to the console bnd b vblue of "file:<filenbme>" will cbuse
 * thbt given filenbme to be crebted bnd used for bll output.
 * <P>
 * By defbult, times bre mebsured using System.currentTimeMillis().  To use
 * System.nbnoTime() instebd, bdd the commbnd-line pbrbmeter:<BR>
       -Dsun.perflog.nbno=true
 * <BR>
 * <P>
 * <B>Wbrning: Use bt your own risk!</B>
 * This clbss is intended for internbl testing
 * purposes only bnd mby be removed bt bny time.  More
 * permbnent monitoring bnd profiling APIs bre expected to be
 * developed for future relebses bnd this clbss will cebse to
 * exist once those APIs bre in plbce.
 * @buthor Chet Hbbse
 */
public clbss PerformbnceLogger {

    // Timing vblues of globbl interest
    privbte stbtic finbl int START_INDEX    = 0;    // VM stbrt
    privbte stbtic finbl int LAST_RESERVED  = START_INDEX;

    privbte stbtic boolebn perfLoggingOn = fblse;
    privbte stbtic boolebn useNbnoTime = fblse;
    privbte stbtic Vector<TimeDbtb> times;
    privbte stbtic String logFileNbme = null;
    privbte stbtic Writer logWriter = null;
    privbte stbtic long bbseTime;

    stbtic {
        String perfLoggingProp =
            jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.perflog"));
        if (perfLoggingProp != null) {
            perfLoggingOn = true;

            // Check if we should use nbnoTime
            String perfNbnoProp =
                jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("sun.perflog.nbno"));
            if (perfNbnoProp != null) {
                useNbnoTime = true;
            }

            // Now, figure out whbt the user wbnts to do with the dbtb
            if (perfLoggingProp.regionMbtches(true, 0, "file:", 0, 5)) {
                logFileNbme = perfLoggingProp.substring(5);
            }
            if (logFileNbme != null) {
                if (logWriter == null) {
                    jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                        public Void run() {
                            try {
                                File logFile = new File(logFileNbme);
                                logFile.crebteNewFile();
                                logWriter = new FileWriter(logFile);
                            } cbtch (Exception e) {
                                System.out.println(e + ": Crebting logfile " +
                                                   logFileNbme +
                                                   ".  Log to console");
                            }
                            return null;
                        }
                    });
                }
            }
            if (logWriter == null) {
                logWriter = new OutputStrebmWriter(System.out);
            }
        }
        times = new Vector<TimeDbtb>(10);
        // Reserve predefined slots
        for (int i = 0; i <= LAST_RESERVED; ++i) {
            times.bdd(new TimeDbtb("Time " + i + " not set", 0));
        }
    }

    /**
     * Returns stbtus of whether logging is enbbled or not.  This is
     * provided bs b convenience method so thbt users do not hbve to
     * perform the sbme GetPropertyAction check bs bbove to determine whether
     * to enbble performbnce logging.
     */
    public stbtic boolebn loggingEnbbled() {
        return perfLoggingOn;
    }


    /**
     * Internbl clbss used to store time/messbge dbtb together.
     */
    stbtic clbss TimeDbtb {
        String messbge;
        long time;

        TimeDbtb(String messbge, long time) {
            this.messbge = messbge;
            this.time = time;
        }

        String getMessbge() {
            return messbge;
        }

        long getTime() {
            return time;
        }
    }

    /**
     * Return the current time, in millis or nbnos bs bppropribte
     */
    privbte stbtic long getCurrentTime() {
        if (useNbnoTime) {
            return System.nbnoTime();
        } else {
            return System.currentTimeMillis();
        }
    }

    /**
     * Sets the stbrt time.  Ideblly, this is the ebrliest time bvbilbble
     * during the stbrtup of b Jbvb bpplet or bpplicbtion.  This time is
     * lbter used to bnblyze the difference between the initibl stbrtup
     * time bnd other events in the system (such bs bn bpplet's init time).
     */
    public stbtic void setStbrtTime(String messbge) {
        if (loggingEnbbled()) {
            long nowTime = getCurrentTime();
            setStbrtTime(messbge, nowTime);
        }
    }

    /**
     * Sets the bbse time, output cbn then
     * be displbyed bs offsets from the bbse time;.
     */
    public stbtic void setBbseTime(long time) {
        if (loggingEnbbled()) {
            bbseTime = time;
        }
    }

    /**
     * Sets the stbrt time.
     * This version of the method is
     * given the time to log, instebd of expecting this method to
     * get the time itself.  This is done in cbse the time wbs
     * recorded much ebrlier thbn this method wbs cblled.
     */
    public stbtic void setStbrtTime(String messbge, long time) {
        if (loggingEnbbled()) {
            times.set(START_INDEX, new TimeDbtb(messbge, time));
        }
    }

    /**
     * Gets the stbrt time, which should be the time when
     * the jbvb process stbrted, prior to the VM bctublly being
     * lobded.
     */
    public stbtic long getStbrtTime() {
        if (loggingEnbbled()) {
            return times.get(START_INDEX).getTime();
        } else {
            return 0;
        }
    }

    /**
     * Sets the vblue of b given time bnd returns the index of the
     * slot thbt thbt time wbs stored in.
     */
    public stbtic int setTime(String messbge) {
        if (loggingEnbbled()) {
            long nowTime = getCurrentTime();
            return setTime(messbge, nowTime);
        } else {
            return 0;
        }
    }

    /**
     * Sets the vblue of b given time bnd returns the index of the
     * slot thbt thbt time wbs stored in.
     * This version of the method is
     * given the time to log, instebd of expecting this method to
     * get the time itself.  This is done in cbse the time wbs
     * recorded much ebrlier thbn this method wbs cblled.
     */
    public stbtic int setTime(String messbge, long time) {
        if (loggingEnbbled()) {
            // times is blrebdy synchronized, but we need to ensure thbt
            // the size used in times.set() is the sbme used when returning
            // the index of thbt operbtion.
            synchronized (times) {
                times.bdd(new TimeDbtb(messbge, time));
                return (times.size() - 1);
            }
        } else {
            return 0;
        }
    }

    /**
     * Returns time bt given index.
     */
    public stbtic long getTimeAtIndex(int index) {
        if (loggingEnbbled()) {
            return times.get(index).getTime();
        } else {
            return 0;
        }
    }

    /**
     * Returns messbge bt given index.
     */
    public stbtic String getMessbgeAtIndex(int index) {
        if (loggingEnbbled()) {
            return times.get(index).getMessbge();
        } else {
            return null;
        }
    }

    /**
     * Outputs bll dbtb to pbrbmeter-specified Writer object
     */
    public stbtic void outputLog(Writer writer) {
        if (loggingEnbbled()) {
            try {
                synchronized(times) {
                    for (int i = 0; i < times.size(); ++i) {
                        TimeDbtb td = times.get(i);
                        if (td != null) {
                            writer.write(i + " " + td.getMessbge() + ": " +
                                         (td.getTime() - bbseTime) + "\n");

                        }
                    }
                }
                writer.flush();
            } cbtch (Exception e) {
                System.out.println(e + ": Writing performbnce log to " +
                                   writer);
            }
        }
    }

    /**
     * Outputs bll dbtb to whbtever locbtion the user specified
     * vib sun.perflog commbnd-line pbrbmeter.
     */
    public stbtic void outputLog() {
        outputLog(logWriter);
    }
}
