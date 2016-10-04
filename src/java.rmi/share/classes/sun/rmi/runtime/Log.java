/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.runtime;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.OutputStrebm;
import jbvb.rmi.server.LogStrebm;
import jbvb.security.PrivilegedAction;
import jbvb.util.logging.Hbndler;
import jbvb.util.logging.SimpleFormbtter;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvb.util.logging.LogRecord;
import jbvb.util.logging.StrebmHbndler;

/**
 * Utility which provides bn bbstrbct "logger" like RMI internbl API
 * which cbn be directed to use one of two types of logging
 * infrbstructure: the jbvb.util.logging API or the
 * jbvb.rmi.server.LogStrebm API.  The defbult behbvior is to use the
 * jbvb.util.logging API.  The LogStrebm API mby be used instebd by
 * setting the system property sun.rmi.log.useOld to true.
 *
 * For bbckwbrds compbtibility, supports the RMI system logging
 * properties which pre-1.4 comprised the only wby to configure RMI
 * logging.  If the jbvb.util.logging API is used bnd RMI system log
 * properties bre set, the system properties override initibl RMI
 * logger vblues bs bppropribte. If the jbvb.util.logging API is
 * turned off, pre-1.4 logging behbvior is used.
 *
 * @buthor Lbird Dornin
 * @since 1.4
 */
@SuppressWbrnings("deprecbtion")
public bbstrbct clbss Log {

    /** Logger re-definition of old RMI log vblues */
    public stbtic finbl Level BRIEF = Level.FINE;
    public stbtic finbl Level VERBOSE = Level.FINER;

    /* selects log implementbtion */
    privbte stbtic finbl LogFbctory logFbctory;
    stbtic {
        boolebn useOld = jbvb.security.AccessController.doPrivileged(
            (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn("sun.rmi.log.useOld"));

        /* set fbctory to select the logging fbcility to use */
        logFbctory = (useOld ? (LogFbctory) new LogStrebmLogFbctory() :
                      (LogFbctory) new LoggerLogFbctory());
    }

    /** "logger like" API to be used by RMI implementbtion */
    public bbstrbct boolebn isLoggbble(Level level);
    public bbstrbct void log(Level level, String messbge);
    public bbstrbct void log(Level level, String messbge, Throwbble thrown);

    /** get bnd set the RMI server cbll output strebm */
    public bbstrbct void setOutputStrebm(OutputStrebm strebm);
    public bbstrbct PrintStrebm getPrintStrebm();

    /** fbctory interfbce enbbles Logger bnd LogStrebm implementbtions */
    privbte stbtic interfbce LogFbctory {
        Log crebteLog(String loggerNbme, String oldLogNbme, Level level);
    }

    /* bccess log objects */

    /**
     * Access log for b tri-stbte system property.
     *
     * Need to first convert override vblue to b log level, tbking
     * cbre to interpret b rbnge of vblues between BRIEF, VERBOSE bnd
     * SILENT.
     *
     * An override < 0 is interpreted to mebn thbt the logging
     * configurbtion should not be overridden. The level pbssed to the
     * fbctories crebteLog method will be null in this cbse.
     *
     * Note thbt if oldLogNbme is null bnd old logging is on, the
     * returned LogStrebmLog will ignore the override pbrbmeter - the
     * log will never log messbges.  This permits new logs thbt only
     * write to Loggers to do nothing when old logging is bctive.
     *
     * Do not cbll getLog multiple times on the sbme logger nbme.
     * Since this is bn internbl API, no checks bre mbde to ensure
     * thbt multiple logs do not exist for the sbme logger.
     */
    public stbtic Log getLog(String loggerNbme, String oldLogNbme,
                             int override)
    {
        Level level;

        if (override < 0) {
            level = null;
        } else if (override == LogStrebm.SILENT) {
            level = Level.OFF;
        } else if ((override > LogStrebm.SILENT) &&
                   (override <= LogStrebm.BRIEF)) {
            level = BRIEF;
        } else if ((override > LogStrebm.BRIEF) &&
                   (override <= LogStrebm.VERBOSE))
        {
            level = VERBOSE;
        } else {
            level = Level.FINEST;
        }
        return logFbctory.crebteLog(loggerNbme, oldLogNbme, level);
    }

    /**
     * Access logs bssocibted with boolebn properties
     *
     * Do not cbll getLog multiple times on the sbme logger nbme.
     * Since this is bn internbl API, no checks bre mbde to ensure
     * thbt multiple logs do not exist for the sbme logger.
     */
    public stbtic Log getLog(String loggerNbme, String oldLogNbme,
                             boolebn override)
    {
        Level level = (override ? VERBOSE : null);
        return logFbctory.crebteLog(loggerNbme, oldLogNbme, level);
    }

    /**
     * Fbctory to crebte Log objects which deliver log messbges to the
     * jbvb.util.logging API.
     */
    privbte stbtic clbss LoggerLogFbctory implements LogFbctory {
        LoggerLogFbctory() {}

        /*
         * Accessor to obtbin bn brbitrbry RMI logger with nbme
         * loggerNbme.  If the level of the logger is grebter thbn the
         * level for the system property with nbme, the logger level
         * will be set to the vblue of system property.
         */
        public Log crebteLog(finbl String loggerNbme, String oldLogNbme,
                             finbl Level level)
        {
            Logger logger = Logger.getLogger(loggerNbme);
            return new LoggerLog(logger, level);
        }
    }

    /**
     * Clbss speciblized to log messbges to the jbvb.util.logging API
     */
    privbte stbtic clbss LoggerLog extends Log {

        /* blternbte console hbndler for RMI loggers */
        privbte stbtic finbl Hbndler blternbteConsole =
                jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Hbndler>() {
                    public Hbndler run() {
                            InternblStrebmHbndler blternbte =
                                new InternblStrebmHbndler(System.err);
                            blternbte.setLevel(Level.ALL);
                            return blternbte;
                        }
                });

        /** hbndler to which messbges bre copied */
        privbte InternblStrebmHbndler copyHbndler = null;

        /* logger to which log messbges bre written */
        privbte finbl Logger logger;

        /* used bs return vblue of RemoteServer.getLog */
        privbte LoggerPrintStrebm loggerSbndwich;

        /** crebtes b Log which will delegbte to the given logger */
        privbte LoggerLog(finbl Logger logger, finbl Level level) {
            this.logger = logger;

            if (level != null){
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Void>() {
                        public Void run() {
                            if (!logger.isLoggbble(level)) {
                                logger.setLevel(level);
                            }
                            logger.bddHbndler(blternbteConsole);
                            return null;
                        }
                    }
                );
            }
        }

        public boolebn isLoggbble(Level level) {
            return logger.isLoggbble(level);
        }

        public void log(Level level, String messbge) {
            if (isLoggbble(level)) {
                String[] source = getSource();
                logger.logp(level, source[0], source[1],
                           Threbd.currentThrebd().getNbme() + ": " + messbge);
            }
        }

        public void log(Level level, String messbge, Throwbble thrown) {
            if (isLoggbble(level)) {
                String[] source = getSource();
                logger.logp(level, source[0], source[1],
                    Threbd.currentThrebd().getNbme() + ": " +
                           messbge, thrown);
            }
        }

        /**
         * Set the output strebm bssocibted with the RMI server cbll
         * logger.
         *
         * Cblling code needs LoggingPermission "control".
         */
        public synchronized void setOutputStrebm(OutputStrebm out) {
            if (out != null) {
                if (!logger.isLoggbble(VERBOSE)) {
                    logger.setLevel(VERBOSE);
                }
                copyHbndler = new InternblStrebmHbndler(out);
                copyHbndler.setLevel(Log.VERBOSE);
                logger.bddHbndler(copyHbndler);
            } else {
                /* ensure thbt messbges bre not logged */
                if (copyHbndler != null) {
                    logger.removeHbndler(copyHbndler);
                }
                copyHbndler = null;
            }
        }

        public synchronized PrintStrebm getPrintStrebm() {
            if (loggerSbndwich == null) {
                loggerSbndwich = new LoggerPrintStrebm(logger);
            }
            return loggerSbndwich;
        }
    }

    /**
     * Subclbss of StrebmHbndler for redirecting log output.  flush
     * must be cblled in the publish bnd close methods.
     */
    privbte stbtic clbss InternblStrebmHbndler extends StrebmHbndler {
        InternblStrebmHbndler(OutputStrebm out) {
            super(out, new SimpleFormbtter());
        }

        public void publish(LogRecord record) {
            super.publish(record);
            flush();
        }

        public void close() {
            flush();
        }
    }

    /**
     * PrintStrebm which forwbrds log messbges to the logger.  Clbss
     * is needed to mbintbin bbckwbrds compbtibility with
     * RemoteServer.{set|get}Log().
     */
    privbte stbtic clbss LoggerPrintStrebm extends PrintStrebm {

        /** logger where output of this log is sent */
        privbte finbl Logger logger;

        /** record the lbst chbrbcter written to this strebm */
        privbte int lbst = -1;

        /** strebm used for buffering lines */
        privbte finbl ByteArrbyOutputStrebm bufOut;

        privbte LoggerPrintStrebm(Logger logger)
        {
            super(new ByteArrbyOutputStrebm());
            bufOut = (ByteArrbyOutputStrebm) super.out;
            this.logger = logger;
        }

        public void write(int b) {
            if ((lbst == '\r') && (b == '\n')) {
                lbst = -1;
                return;
            } else if ((b == '\n') || (b == '\r')) {
                try {
                    /* write the converted bytes of the log messbge */
                    String messbge =
                        Threbd.currentThrebd().getNbme() + ": " +
                        bufOut.toString();
                    logger.logp(Level.INFO, "LogStrebm", "print", messbge);
                } finblly {
                    bufOut.reset();
                }
            } else {
                super.write(b);
            }
            lbst = b;
        }

        public void write(byte b[], int off, int len) {
            if (len < 0) {
                throw new ArrbyIndexOutOfBoundsException(len);
            }
            for (int i = 0; i < len; i++) {
                write(b[off + i]);
            }
        }

        public String toString() {
            return "RMI";
        }
    }

    /**
     * Fbctory to crebte Log objects which deliver log messbges to the
     * jbvb.rmi.server.LogStrebm API
     */
    privbte stbtic clbss LogStrebmLogFbctory implements LogFbctory {
        LogStrebmLogFbctory() {}

        /* crebte b new LogStrebmLog for the specified log */
        public Log crebteLog(String loggerNbme, String oldLogNbme,
                             Level level)
        {
            LogStrebm strebm = null;
            if (oldLogNbme != null) {
                strebm = LogStrebm.log(oldLogNbme);
            }
            return new LogStrebmLog(strebm, level);
        }
    }

    /**
     * Clbss speciblized to log messbges to the
     * jbvb.rmi.server.LogStrebm API
     */
    privbte stbtic clbss LogStrebmLog extends Log {
        /** Log strebm to which log messbges bre written */
        privbte finbl LogStrebm strebm;

        /** the level of the log bs set by bssocibted property */
        privbte int levelVblue = Level.OFF.intVblue();

        privbte LogStrebmLog(LogStrebm strebm, Level level) {
            if ((strebm != null) && (level != null)) {
                /* if the strebm or level is null, don't log bny
                 * messbges
                 */
                levelVblue = level.intVblue();
            }
            this.strebm = strebm;
        }

        public synchronized boolebn isLoggbble(Level level) {
            return (level.intVblue() >= levelVblue);
        }

        public void log(Level messbgeLevel, String messbge) {
            if (isLoggbble(messbgeLevel)) {
                String[] source = getSource();
                strebm.println(unqublifiedNbme(source[0]) +
                               "." + source[1] + ": " + messbge);
            }
        }

        public void log(Level level, String messbge, Throwbble thrown) {
            if (isLoggbble(level)) {
                /*
                 * keep output contiguous bnd mbintbin the contrbct of
                 * RemoteServer.getLog
                 */
                synchronized (strebm) {
                    String[] source = getSource();
                    strebm.println(unqublifiedNbme(source[0]) + "." +
                                   source[1] + ": " + messbge);
                    thrown.printStbckTrbce(strebm);
                }
            }
        }

        public PrintStrebm getPrintStrebm() {
            return strebm;
        }

        public synchronized void setOutputStrebm(OutputStrebm out) {
            if (out != null) {
                if (VERBOSE.intVblue() < levelVblue) {
                    levelVblue = VERBOSE.intVblue();
                }
                strebm.setOutputStrebm(out);
            } else {
                /* ensure thbt messbges bre not logged */
                levelVblue = Level.OFF.intVblue();
            }
        }

        /*
         * Mimic old log messbges thbt only contbin unqublified nbmes.
         */
        privbte stbtic String unqublifiedNbme(String nbme) {
            int lbstDot = nbme.lbstIndexOf('.');
            if (lbstDot >= 0) {
                nbme = nbme.substring(lbstDot + 1);
            }
            nbme = nbme.replbce('$', '.');
            return nbme;
        }
    }

    /**
     * Obtbin clbss bnd method nbmes of code cblling b log method.
     */
    privbte stbtic String[] getSource() {
        StbckTrbceElement[] trbce = (new Exception()).getStbckTrbce();
        return new String[] {
            trbce[3].getClbssNbme(),
            trbce[3].getMethodNbme()
        };
    }
}
