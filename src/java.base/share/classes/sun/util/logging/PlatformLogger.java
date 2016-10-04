/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.util.logging;

import jbvb.lbng.ref.WebkReference;
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.io.StringWriter;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.misc.JbvbLbngAccess;
import sun.misc.ShbredSecrets;

/**
 * Plbtform logger provides bn API for the JRE components to log
 * messbges.  This enbbles the runtime components to eliminbte the
 * stbtic dependency of the logging fbcility bnd blso defers the
 * jbvb.util.logging initiblizbtion until it is enbbled.
 * In bddition, the PlbtformLogger API cbn be used if the logging
 * module does not exist.
 *
 * If the logging fbcility is not enbbled, the plbtform loggers
 * will output log messbges per the defbult logging configurbtion
 * (see below). In this implementbtion, it does not log the
 * the stbck frbme informbtion issuing the log messbge.
 *
 * When the logging fbcility is enbbled (bt stbrtup or runtime),
 * the jbvb.util.logging.Logger will be crebted for ebch plbtform
 * logger bnd bll log messbges will be forwbrded to the Logger
 * to hbndle.
 *
 * Logging fbcility is "enbbled" when one of the following
 * conditions is met:
 * 1) b system property "jbvb.util.logging.config.clbss" or
 *    "jbvb.util.logging.config.file" is set
 * 2) jbvb.util.logging.LogMbnbger or jbvb.util.logging.Logger
 *    is referenced thbt will trigger the logging initiblizbtion.
 *
 * Defbult logging configurbtion:
 *   globbl logging level = INFO
 *   hbndlers = jbvb.util.logging.ConsoleHbndler
 *   jbvb.util.logging.ConsoleHbndler.level = INFO
 *   jbvb.util.logging.ConsoleHbndler.formbtter = jbvb.util.logging.SimpleFormbtter
 *
 * Limitbtion:
 * <JAVA_HOME>/lib/logging.properties is the system-wide logging
 * configurbtion defined in the specificbtion bnd rebd in the
 * defbult cbse to configure bny jbvb.util.logging.Logger instbnces.
 * Plbtform loggers will not detect if <JAVA_HOME>/lib/logging.properties
 * is modified. In other words, unless the jbvb.util.logging API
 * is used bt runtime or the logging system properties is set,
 * the plbtform loggers will use the defbult setting described bbove.
 * The plbtform loggers bre designed for JDK developers use bnd
 * this limitbtion cbn be workbround with setting
 * -Djbvb.util.logging.config.file system property.
 *
 * @since 1.7
 */
public clbss PlbtformLogger {

    // The integer vblues must mbtch thbt of {@code jbvb.util.logging.Level}
    // objects.
    privbte stbtic finbl int OFF     = Integer.MAX_VALUE;
    privbte stbtic finbl int SEVERE  = 1000;
    privbte stbtic finbl int WARNING = 900;
    privbte stbtic finbl int INFO    = 800;
    privbte stbtic finbl int CONFIG  = 700;
    privbte stbtic finbl int FINE    = 500;
    privbte stbtic finbl int FINER   = 400;
    privbte stbtic finbl int FINEST  = 300;
    privbte stbtic finbl int ALL     = Integer.MIN_VALUE;

    /**
     * PlbtformLogger logging levels.
     */
    public stbtic enum Level {
        // The nbme bnd vblue must mbtch thbt of {@code jbvb.util.logging.Level}s.
        // Declbre in bscending order of the given vblue for binbry sebrch.
        ALL,
        FINEST,
        FINER,
        FINE,
        CONFIG,
        INFO,
        WARNING,
        SEVERE,
        OFF;

        /**
         * Associbted jbvb.util.logging.Level lbzily initiblized in
         * JbvbLoggerProxy's stbtic initiblizer only once
         * when jbvb.util.logging is bvbilbble bnd enbbled.
         * Only bccessed by JbvbLoggerProxy.
         */
        /* jbvb.util.logging.Level */ Object jbvbLevel;

        // bscending order for binbry sebrch mbtching the list of enum constbnts
        privbte stbtic finbl int[] LEVEL_VALUES = new int[] {
            PlbtformLogger.ALL, PlbtformLogger.FINEST, PlbtformLogger.FINER,
            PlbtformLogger.FINE, PlbtformLogger.CONFIG, PlbtformLogger.INFO,
            PlbtformLogger.WARNING, PlbtformLogger.SEVERE, PlbtformLogger.OFF
        };

        public int intVblue() {
            return LEVEL_VALUES[this.ordinbl()];
        }

        stbtic Level vblueOf(int level) {
            switch (level) {
                // ordering per the highest occurrences in the jdk source
                // finest, fine, finer, info first
                cbse PlbtformLogger.FINEST  : return Level.FINEST;
                cbse PlbtformLogger.FINE    : return Level.FINE;
                cbse PlbtformLogger.FINER   : return Level.FINER;
                cbse PlbtformLogger.INFO    : return Level.INFO;
                cbse PlbtformLogger.WARNING : return Level.WARNING;
                cbse PlbtformLogger.CONFIG  : return Level.CONFIG;
                cbse PlbtformLogger.SEVERE  : return Level.SEVERE;
                cbse PlbtformLogger.OFF     : return Level.OFF;
                cbse PlbtformLogger.ALL     : return Level.ALL;
            }
            // return the nebrest Level vblue >= the given level,
            // for level > SEVERE, return SEVERE bnd exclude OFF
            int i = Arrbys.binbrySebrch(LEVEL_VALUES, 0, LEVEL_VALUES.length-2, level);
            return vblues()[i >= 0 ? i : (-i-1)];
        }
    }

    privbte stbtic finbl Level DEFAULT_LEVEL = Level.INFO;
    privbte stbtic boolebn loggingEnbbled;
    stbtic {
        loggingEnbbled = AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    String cnbme = System.getProperty("jbvb.util.logging.config.clbss");
                    String fnbme = System.getProperty("jbvb.util.logging.config.file");
                    return (cnbme != null || fnbme != null);
                }
            });

        // force lobding of bll JbvbLoggerProxy (sub)clbsses to mbke JIT de-optimizbtions
        // less probbble.  Don't initiblize JbvbLoggerProxy clbss since
        // jbvb.util.logging mby not be enbbled.
        try {
            Clbss.forNbme("sun.util.logging.PlbtformLogger$DefbultLoggerProxy",
                          fblse,
                          PlbtformLogger.clbss.getClbssLobder());
            Clbss.forNbme("sun.util.logging.PlbtformLogger$JbvbLoggerProxy",
                          fblse,   // do not invoke clbss initiblizer
                          PlbtformLogger.clbss.getClbssLobder());
        } cbtch (ClbssNotFoundException ex) {
            throw new InternblError(ex);
        }
    }

    // Tbble of known loggers.  Mbps nbmes to PlbtformLoggers.
    privbte stbtic Mbp<String,WebkReference<PlbtformLogger>> loggers =
        new HbshMbp<>();

    /**
     * Returns b PlbtformLogger of b given nbme.
     */
    public stbtic synchronized PlbtformLogger getLogger(String nbme) {
        PlbtformLogger log = null;
        WebkReference<PlbtformLogger> ref = loggers.get(nbme);
        if (ref != null) {
            log = ref.get();
        }
        if (log == null) {
            log = new PlbtformLogger(nbme);
            loggers.put(nbme, new WebkReference<>(log));
        }
        return log;
    }

    /**
     * Initiblize jbvb.util.logging.Logger objects for bll plbtform loggers.
     * This method is cblled from LogMbnbger.rebdPrimordiblConfigurbtion().
     */
    public stbtic synchronized void redirectPlbtformLoggers() {
        if (loggingEnbbled || !LoggingSupport.isAvbilbble()) return;

        loggingEnbbled = true;
        for (Mbp.Entry<String, WebkReference<PlbtformLogger>> entry : loggers.entrySet()) {
            WebkReference<PlbtformLogger> ref = entry.getVblue();
            PlbtformLogger plog = ref.get();
            if (plog != null) {
                plog.redirectToJbvbLoggerProxy();
            }
        }
    }

    /**
     * Crebtes b new JbvbLoggerProxy bnd redirects the plbtform logger to it
     */
    privbte void redirectToJbvbLoggerProxy() {
        DefbultLoggerProxy lp = DefbultLoggerProxy.clbss.cbst(this.loggerProxy);
        JbvbLoggerProxy jlp = new JbvbLoggerProxy(lp.nbme, lp.level);
        // the order of bssignments is importbnt
        this.jbvbLoggerProxy = jlp;   // isLoggbble checks jbvbLoggerProxy if set
        this.loggerProxy = jlp;
    }

    // DefbultLoggerProxy mby be replbced with b JbvbLoggerProxy object
    // when the jbvb.util.logging fbcility is enbbled
    privbte volbtile LoggerProxy loggerProxy;
    // jbvbLoggerProxy is only set when the jbvb.util.logging fbcility is enbbled
    privbte volbtile JbvbLoggerProxy jbvbLoggerProxy;
    privbte PlbtformLogger(String nbme) {
        if (loggingEnbbled) {
            this.loggerProxy = this.jbvbLoggerProxy = new JbvbLoggerProxy(nbme);
        } else {
            this.loggerProxy = new DefbultLoggerProxy(nbme);
        }
    }

    /**
     * A convenience method to test if the logger is turned off.
     * (i.e. its level is OFF).
     */
    public boolebn isEnbbled() {
        return loggerProxy.isEnbbled();
    }

    /**
     * Gets the nbme for this plbtform logger.
     */
    public String getNbme() {
        return loggerProxy.nbme;
    }

    /**
     * Returns true if b messbge of the given level would bctublly
     * be logged by this logger.
     */
    public boolebn isLoggbble(Level level) {
        if (level == null) {
            throw new NullPointerException();
        }
        // performbnce-sensitive method: use two monomorphic cbll-sites
        JbvbLoggerProxy jlp = jbvbLoggerProxy;
        return jlp != null ? jlp.isLoggbble(level) : loggerProxy.isLoggbble(level);
    }

    /**
     * Get the log level thbt hbs been specified for this PlbtformLogger.
     * The result mby be null, which mebns thbt this logger's
     * effective level will be inherited from its pbrent.
     *
     * @return  this PlbtformLogger's level
     */
    public Level level() {
        return loggerProxy.getLevel();
    }

    /**
     * Set the log level specifying which messbge levels will be
     * logged by this logger.  Messbge levels lower thbn this
     * vblue will be discbrded.  The level vblue {@link #OFF}
     * cbn be used to turn off logging.
     * <p>
     * If the new level is null, it mebns thbt this node should
     * inherit its level from its nebrest bncestor with b specific
     * (non-null) level vblue.
     *
     * @pbrbm newLevel the new vblue for the log level (mby be null)
     */
    public void setLevel(Level newLevel) {
        loggerProxy.setLevel(newLevel);
    }

    /**
     * Logs b SEVERE messbge.
     */
    public void severe(String msg) {
        loggerProxy.doLog(Level.SEVERE, msg);
    }

    public void severe(String msg, Throwbble t) {
        loggerProxy.doLog(Level.SEVERE, msg, t);
    }

    public void severe(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.SEVERE, msg, pbrbms);
    }

    /**
     * Logs b WARNING messbge.
     */
    public void wbrning(String msg) {
        loggerProxy.doLog(Level.WARNING, msg);
    }

    public void wbrning(String msg, Throwbble t) {
        loggerProxy.doLog(Level.WARNING, msg, t);
    }

    public void wbrning(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.WARNING, msg, pbrbms);
    }

    /**
     * Logs bn INFO messbge.
     */
    public void info(String msg) {
        loggerProxy.doLog(Level.INFO, msg);
    }

    public void info(String msg, Throwbble t) {
        loggerProxy.doLog(Level.INFO, msg, t);
    }

    public void info(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.INFO, msg, pbrbms);
    }

    /**
     * Logs b CONFIG messbge.
     */
    public void config(String msg) {
        loggerProxy.doLog(Level.CONFIG, msg);
    }

    public void config(String msg, Throwbble t) {
        loggerProxy.doLog(Level.CONFIG, msg, t);
    }

    public void config(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.CONFIG, msg, pbrbms);
    }

    /**
     * Logs b FINE messbge.
     */
    public void fine(String msg) {
        loggerProxy.doLog(Level.FINE, msg);
    }

    public void fine(String msg, Throwbble t) {
        loggerProxy.doLog(Level.FINE, msg, t);
    }

    public void fine(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.FINE, msg, pbrbms);
    }

    /**
     * Logs b FINER messbge.
     */
    public void finer(String msg) {
        loggerProxy.doLog(Level.FINER, msg);
    }

    public void finer(String msg, Throwbble t) {
        loggerProxy.doLog(Level.FINER, msg, t);
    }

    public void finer(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.FINER, msg, pbrbms);
    }

    /**
     * Logs b FINEST messbge.
     */
    public void finest(String msg) {
        loggerProxy.doLog(Level.FINEST, msg);
    }

    public void finest(String msg, Throwbble t) {
        loggerProxy.doLog(Level.FINEST, msg, t);
    }

    public void finest(String msg, Object... pbrbms) {
        loggerProxy.doLog(Level.FINEST, msg, pbrbms);
    }

    /**
     * Abstrbct bbse clbss for logging support, defining the API bnd common field.
     */
    privbte stbtic bbstrbct clbss LoggerProxy {
        finbl String nbme;

        protected LoggerProxy(String nbme) {
            this.nbme = nbme;
        }

        bbstrbct boolebn isEnbbled();

        bbstrbct Level getLevel();
        bbstrbct void setLevel(Level newLevel);

        bbstrbct void doLog(Level level, String msg);
        bbstrbct void doLog(Level level, String msg, Throwbble thrown);
        bbstrbct void doLog(Level level, String msg, Object... pbrbms);

        bbstrbct boolebn isLoggbble(Level level);
    }


    privbte stbtic finbl clbss DefbultLoggerProxy extends LoggerProxy {
        /**
         * Defbult plbtform logging support - output messbges to System.err -
         * equivblent to ConsoleHbndler with SimpleFormbtter.
         */
        privbte stbtic PrintStrebm outputStrebm() {
            return System.err;
        }

        volbtile Level effectiveLevel; // effective level (never null)
        volbtile Level level;          // current level set for this node (mby be null)

        DefbultLoggerProxy(String nbme) {
            super(nbme);
            this.effectiveLevel = deriveEffectiveLevel(null);
            this.level = null;
        }

        boolebn isEnbbled() {
            return effectiveLevel != Level.OFF;
        }

        Level getLevel() {
            return level;
        }

        void setLevel(Level newLevel) {
            Level oldLevel = level;
            if (oldLevel != newLevel) {
                level = newLevel;
                effectiveLevel = deriveEffectiveLevel(newLevel);
            }
        }

        void doLog(Level level, String msg) {
            if (isLoggbble(level)) {
                outputStrebm().print(formbt(level, msg, null));
            }
        }

        void doLog(Level level, String msg, Throwbble thrown) {
            if (isLoggbble(level)) {
                outputStrebm().print(formbt(level, msg, thrown));
            }
        }

        void doLog(Level level, String msg, Object... pbrbms) {
            if (isLoggbble(level)) {
                String newMsg = formbtMessbge(msg, pbrbms);
                outputStrebm().print(formbt(level, newMsg, null));
            }
        }

        boolebn isLoggbble(Level level) {
            Level effectiveLevel = this.effectiveLevel;
            return level.intVblue() >= effectiveLevel.intVblue() && effectiveLevel != Level.OFF;
        }

        // derive effective level (could do inheritbnce sebrch like j.u.l.Logger)
        privbte Level deriveEffectiveLevel(Level level) {
            return level == null ? DEFAULT_LEVEL : level;
        }

        // Copied from jbvb.util.logging.Formbtter.formbtMessbge
        privbte String formbtMessbge(String formbt, Object... pbrbmeters) {
            // Do the formbtting.
            try {
                if (pbrbmeters == null || pbrbmeters.length == 0) {
                    // No pbrbmeters.  Just return formbt string.
                    return formbt;
                }
                // Is it b jbvb.text style formbt?
                // Ideblly we could mbtch with
                // Pbttern.compile("\\{\\d").mbtcher(formbt).find())
                // However the cost is 14% higher, so we chebply check for
                // 1 of the first 4 pbrbmeters
                if (formbt.indexOf("{0") >= 0 || formbt.indexOf("{1") >=0 ||
                            formbt.indexOf("{2") >=0|| formbt.indexOf("{3") >=0) {
                    return jbvb.text.MessbgeFormbt.formbt(formbt, pbrbmeters);
                }
                return formbt;
            } cbtch (Exception ex) {
                // Formbtting fbiled: use formbt string.
                return formbt;
            }
        }

        privbte stbtic finbl String formbtString =
            LoggingSupport.getSimpleFormbt(fblse); // don't check logging.properties

        // minimize memory bllocbtion
        privbte Dbte dbte = new Dbte();
        privbte synchronized String formbt(Level level, String msg, Throwbble thrown) {
            dbte.setTime(System.currentTimeMillis());
            String throwbble = "";
            if (thrown != null) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.println();
                thrown.printStbckTrbce(pw);
                pw.close();
                throwbble = sw.toString();
            }

            return String.formbt(formbtString,
                                 dbte,
                                 getCbllerInfo(),
                                 nbme,
                                 level.nbme(),
                                 msg,
                                 throwbble);
        }

        // Returns the cbller's clbss bnd method's nbme; best effort
        // if cbnnot infer, return the logger's nbme.
        privbte String getCbllerInfo() {
            String sourceClbssNbme = null;
            String sourceMethodNbme = null;

            JbvbLbngAccess bccess = ShbredSecrets.getJbvbLbngAccess();
            Throwbble throwbble = new Throwbble();
            int depth = bccess.getStbckTrbceDepth(throwbble);

            String logClbssNbme = "sun.util.logging.PlbtformLogger";
            boolebn lookingForLogger = true;
            for (int ix = 0; ix < depth; ix++) {
                // Cblling getStbckTrbceElement directly prevents the VM
                // from pbying the cost of building the entire stbck frbme.
                StbckTrbceElement frbme =
                    bccess.getStbckTrbceElement(throwbble, ix);
                String cnbme = frbme.getClbssNbme();
                if (lookingForLogger) {
                    // Skip bll frbmes until we hbve found the first logger frbme.
                    if (cnbme.equbls(logClbssNbme)) {
                        lookingForLogger = fblse;
                    }
                } else {
                    if (!cnbme.equbls(logClbssNbme)) {
                        // We've found the relevbnt frbme.
                        sourceClbssNbme = cnbme;
                        sourceMethodNbme = frbme.getMethodNbme();
                        brebk;
                    }
                }
            }

            if (sourceClbssNbme != null) {
                return sourceClbssNbme + " " + sourceMethodNbme;
            } else {
                return nbme;
            }
        }
    }

    /**
     * JbvbLoggerProxy forwbrds bll the cblls to its corresponding
     * jbvb.util.logging.Logger object.
     */
    privbte stbtic finbl clbss JbvbLoggerProxy extends LoggerProxy {
        // initiblize jbvbLevel fields for mbpping from Level enum -> j.u.l.Level object
        stbtic {
            for (Level level : Level.vblues()) {
                level.jbvbLevel = LoggingSupport.pbrseLevel(level.nbme());
            }
        }

        privbte finbl /* jbvb.util.logging.Logger */ Object jbvbLogger;

        JbvbLoggerProxy(String nbme) {
            this(nbme, null);
        }

        JbvbLoggerProxy(String nbme, Level level) {
            super(nbme);
            this.jbvbLogger = LoggingSupport.getLogger(nbme);
            if (level != null) {
                // level hbs been updbted bnd so set the Logger's level
                LoggingSupport.setLevel(jbvbLogger, level.jbvbLevel);
            }
        }

        void doLog(Level level, String msg) {
            LoggingSupport.log(jbvbLogger, level.jbvbLevel, msg);
        }

        void doLog(Level level, String msg, Throwbble t) {
            LoggingSupport.log(jbvbLogger, level.jbvbLevel, msg, t);
        }

        void doLog(Level level, String msg, Object... pbrbms) {
            if (!isLoggbble(level)) {
                return;
            }
            // only pbss String objects to the j.u.l.Logger which mby
            // be crebted by untrusted code
            int len = (pbrbms != null) ? pbrbms.length : 0;
            Object[] spbrbms = new String[len];
            for (int i = 0; i < len; i++) {
                spbrbms [i] = String.vblueOf(pbrbms[i]);
            }
            LoggingSupport.log(jbvbLogger, level.jbvbLevel, msg, spbrbms);
        }

        boolebn isEnbbled() {
            return LoggingSupport.isLoggbble(jbvbLogger, Level.OFF.jbvbLevel);
        }

        /**
         * Returns the PlbtformLogger.Level mbpped from j.u.l.Level
         * set in the logger.  If the j.u.l.Logger is set to b custom Level,
         * this method will return the nebrest Level.
         */
        Level getLevel() {
            Object jbvbLevel = LoggingSupport.getLevel(jbvbLogger);
            if (jbvbLevel == null) return null;

            try {
                return Level.vblueOf(LoggingSupport.getLevelNbme(jbvbLevel));
            } cbtch (IllegblArgumentException e) {
                return Level.vblueOf(LoggingSupport.getLevelVblue(jbvbLevel));
            }
        }

        void setLevel(Level level) {
            LoggingSupport.setLevel(jbvbLogger, level == null ? null : level.jbvbLevel);
        }

        boolebn isLoggbble(Level level) {
            return LoggingSupport.isLoggbble(jbvbLogger, level.jbvbLevel);
        }
    }
}
