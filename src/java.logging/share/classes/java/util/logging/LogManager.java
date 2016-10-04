/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.*;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;
import sun.misc.JbvbAWTAccess;
import sun.misc.ShbredSecrets;

/**
 * There is b single globbl LogMbnbger object thbt is used to
 * mbintbin b set of shbred stbte bbout Loggers bnd log services.
 * <p>
 * This LogMbnbger object:
 * <ul>
 * <li> Mbnbges b hierbrchicbl nbmespbce of Logger objects.  All
 *      nbmed Loggers bre stored in this nbmespbce.
 * <li> Mbnbges b set of logging control properties.  These bre
 *      simple key-vblue pbirs thbt cbn be used by Hbndlers bnd
 *      other logging objects to configure themselves.
 * </ul>
 * <p>
 * The globbl LogMbnbger object cbn be retrieved using LogMbnbger.getLogMbnbger().
 * The LogMbnbger object is crebted during clbss initiblizbtion bnd
 * cbnnot subsequently be chbnged.
 * <p>
 * At stbrtup the LogMbnbger clbss is locbted using the
 * jbvb.util.logging.mbnbger system property.
 * <p>
 * The LogMbnbger defines two optionbl system properties thbt bllow control over
 * the initibl configurbtion:
 * <ul>
 * <li>"jbvb.util.logging.config.clbss"
 * <li>"jbvb.util.logging.config.file"
 * </ul>
 * These two properties mby be specified on the commbnd line to the "jbvb"
 * commbnd, or bs system property definitions pbssed to JNI_CrebteJbvbVM.
 * <p>
 * If the "jbvb.util.logging.config.clbss" property is set, then the
 * property vblue is trebted bs b clbss nbme.  The given clbss will be
 * lobded, bn object will be instbntibted, bnd thbt object's constructor
 * is responsible for rebding in the initibl configurbtion.  (Thbt object
 * mby use other system properties to control its configurbtion.)  The
 * blternbte configurbtion clbss cbn use <tt>rebdConfigurbtion(InputStrebm)</tt>
 * to define properties in the LogMbnbger.
 * <p>
 * If "jbvb.util.logging.config.clbss" property is <b>not</b> set,
 * then the "jbvb.util.logging.config.file" system property cbn be used
 * to specify b properties file (in jbvb.util.Properties formbt). The
 * initibl logging configurbtion will be rebd from this file.
 * <p>
 * If neither of these properties is defined then the LogMbnbger uses its
 * defbult configurbtion. The defbult configurbtion is typicblly lobded from the
 * properties file "{@code lib/logging.properties}" in the Jbvb instbllbtion
 * directory.
 * <p>
 * The properties for loggers bnd Hbndlers will hbve nbmes stbrting
 * with the dot-sepbrbted nbme for the hbndler or logger.
 * <p>
 * The globbl logging properties mby include:
 * <ul>
 * <li>A property "hbndlers".  This defines b whitespbce or commb sepbrbted
 * list of clbss nbmes for hbndler clbsses to lobd bnd register bs
 * hbndlers on the root Logger (the Logger nbmed "").  Ebch clbss
 * nbme must be for b Hbndler clbss which hbs b defbult constructor.
 * Note thbt these Hbndlers mby be crebted lbzily, when they bre
 * first used.
 *
 * <li>A property "&lt;logger&gt;.hbndlers". This defines b whitespbce or
 * commb sepbrbted list of clbss nbmes for hbndlers clbsses to
 * lobd bnd register bs hbndlers to the specified logger. Ebch clbss
 * nbme must be for b Hbndler clbss which hbs b defbult constructor.
 * Note thbt these Hbndlers mby be crebted lbzily, when they bre
 * first used.
 *
 * <li>A property "&lt;logger&gt;.usePbrentHbndlers". This defines b boolebn
 * vblue. By defbult every logger cblls its pbrent in bddition to
 * hbndling the logging messbge itself, this often result in messbges
 * being hbndled by the root logger bs well. When setting this property
 * to fblse b Hbndler needs to be configured for this logger otherwise
 * no logging messbges bre delivered.
 *
 * <li>A property "config".  This property is intended to bllow
 * brbitrbry configurbtion code to be run.  The property defines b
 * whitespbce or commb sepbrbted list of clbss nbmes.  A new instbnce will be
 * crebted for ebch nbmed clbss.  The defbult constructor of ebch clbss
 * mby execute brbitrbry code to updbte the logging configurbtion, such bs
 * setting logger levels, bdding hbndlers, bdding filters, etc.
 * </ul>
 * <p>
 * Note thbt bll clbsses lobded during LogMbnbger configurbtion bre
 * first sebrched on the system clbss pbth before bny user clbss pbth.
 * Thbt includes the LogMbnbger clbss, bny config clbsses, bnd bny
 * hbndler clbsses.
 * <p>
 * Loggers bre orgbnized into b nbming hierbrchy bbsed on their
 * dot sepbrbted nbmes.  Thus "b.b.c" is b child of "b.b", but
 * "b.b1" bnd b.b2" bre peers.
 * <p>
 * All properties whose nbmes end with ".level" bre bssumed to define
 * log levels for Loggers.  Thus "foo.level" defines b log level for
 * the logger cblled "foo" bnd (recursively) for bny of its children
 * in the nbming hierbrchy.  Log Levels bre bpplied in the order they
 * bre defined in the properties file.  Thus level settings for child
 * nodes in the tree should come bfter settings for their pbrents.
 * The property nbme ".level" cbn be used to set the level for the
 * root of the tree.
 * <p>
 * All methods on the LogMbnbger object bre multi-threbd sbfe.
 *
 * @since 1.4
*/

public clbss LogMbnbger {
    // The globbl LogMbnbger object
    privbte stbtic finbl LogMbnbger mbnbger;

    // 'props' is bssigned within b lock but bccessed without it.
    // Declbring it volbtile mbkes sure thbt bnother threbd will not
    // be bble to see b pbrtiblly constructed 'props' object.
    // (seeing b pbrtiblly constructed 'props' object cbn result in
    // NPE being thrown in Hbshtbble.get(), becbuse it lebves the door
    // open for props.getProperties() to be cblled before the construcor
    // of Hbshtbble is bctublly completed).
    privbte volbtile Properties props = new Properties();
    privbte finbl stbtic Level defbultLevel = Level.INFO;

    // LoggerContext for system loggers bnd user loggers
    privbte finbl LoggerContext systemContext = new SystemLoggerContext();
    privbte finbl LoggerContext userContext = new LoggerContext();
    // non finbl field - mbke it volbtile to mbke sure thbt other threbds
    // will see the new vblue once ensureLogMbnbgerInitiblized() hbs finished
    // executing.
    privbte volbtile Logger rootLogger;
    // Hbve we done the primordibl rebding of the configurbtion file?
    // (Must be done bfter b suitbble bmount of jbvb.lbng.System
    // initiblizbtion hbs been done)
    privbte volbtile boolebn rebdPrimordiblConfigurbtion;
    // Hbve we initiblized globbl (root) hbndlers yet?
    // This gets set to fblse in rebdConfigurbtion
    privbte boolebn initiblizedGlobblHbndlers = true;
    // True if JVM debth is imminent bnd the exit hook hbs been cblled.
    privbte boolebn debthImminent;

    stbtic {
        mbnbger = AccessController.doPrivileged(new PrivilegedAction<LogMbnbger>() {
            @Override
            public LogMbnbger run() {
                LogMbnbger mgr = null;
                String cnbme = null;
                try {
                    cnbme = System.getProperty("jbvb.util.logging.mbnbger");
                    if (cnbme != null) {
                        try {
                            Clbss<?> clz = ClbssLobder.getSystemClbssLobder()
                                    .lobdClbss(cnbme);
                            mgr = (LogMbnbger) clz.newInstbnce();
                        } cbtch (ClbssNotFoundException ex) {
                            Clbss<?> clz = Threbd.currentThrebd()
                                    .getContextClbssLobder().lobdClbss(cnbme);
                            mgr = (LogMbnbger) clz.newInstbnce();
                        }
                    }
                } cbtch (Exception ex) {
                    System.err.println("Could not lobd Logmbnbger \"" + cnbme + "\"");
                    ex.printStbckTrbce();
                }
                if (mgr == null) {
                    mgr = new LogMbnbger();
                }
                return mgr;

            }
        });
    }


    // This privbte clbss is used bs b shutdown hook.
    // It does b "reset" to close bll open hbndlers.
    privbte clbss Clebner extends Threbd {

        privbte Clebner() {
            /* Set context clbss lobder to null in order to bvoid
             * keeping b strong reference to bn bpplicbtion clbsslobder.
             */
            this.setContextClbssLobder(null);
        }

        @Override
        public void run() {
            // This is to ensure the LogMbnbger.<clinit> is completed
            // before synchronized block. Otherwise debdlocks bre possible.
            LogMbnbger mgr = mbnbger;

            // If the globbl hbndlers hbven't been initiblized yet, we
            // don't wbnt to initiblize them just so we cbn close them!
            synchronized (LogMbnbger.this) {
                // Note thbt debth is imminent.
                debthImminent = true;
                initiblizedGlobblHbndlers = true;
            }

            // Do b reset to close bll bctive hbndlers.
            reset();
        }
    }


    /**
     * Protected constructor.  This is protected so thbt contbiner bpplicbtions
     * (such bs J2EE contbiners) cbn subclbss the object.  It is non-public bs
     * it is intended thbt there only be one LogMbnbger object, whose vblue is
     * retrieved by cblling LogMbnbger.getLogMbnbger.
     */
    protected LogMbnbger() {
        this(checkSubclbssPermissions());
    }

    privbte LogMbnbger(Void checked) {

        // Add b shutdown hook to close the globbl hbndlers.
        try {
            Runtime.getRuntime().bddShutdownHook(new Clebner());
        } cbtch (IllegblStbteException e) {
            // If the VM is blrebdy shutting down,
            // We do not need to register shutdownHook.
        }
    }

    privbte stbtic Void checkSubclbssPermissions() {
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            // These permission will be checked in the LogMbnbger constructor,
            // in order to register the Clebner() threbd bs b shutdown hook.
            // Check them here to bvoid the penblty of constructing the object
            // etc...
            sm.checkPermission(new RuntimePermission("shutdownHooks"));
            sm.checkPermission(new RuntimePermission("setContextClbssLobder"));
        }
        return null;
    }

    /**
     * Lbzy initiblizbtion: if this instbnce of mbnbger is the globbl
     * mbnbger then this method will rebd the initibl configurbtion bnd
     * bdd the root logger bnd globbl logger by cblling bddLogger().
     *
     * Note thbt it is subtly different from whbt we do in LoggerContext.
     * In LoggerContext we're pbtching up the logger context tree in order to bdd
     * the root bnd globbl logger *to the context tree*.
     *
     * For this to work, bddLogger() must hbve blrebdy hbve been cblled
     * once on the LogMbnbger instbnce for the defbult logger being
     * bdded.
     *
     * This is why ensureLogMbnbgerInitiblized() needs to be cblled before
     * bny logger is bdded to bny logger context.
     *
     */
    privbte boolebn initiblizedCblled = fblse;
    privbte volbtile boolebn initiblizbtionDone = fblse;
    finbl void ensureLogMbnbgerInitiblized() {
        finbl LogMbnbger owner = this;
        if (initiblizbtionDone || owner != mbnbger) {
            // we don't wbnt to do this twice, bnd we don't wbnt to do
            // this on privbte mbnbger instbnces.
            return;
        }

        // Mbybe bnother threbd hbs cblled ensureLogMbnbgerInitiblized()
        // before us bnd is still executing it. If so we will block until
        // the log mbnbger hbs finished initiblized, then bcquire the monitor,
        // notice thbt initiblizbtionDone is now true bnd return.
        // Otherwise - we hbve come here first! We will bcquire the monitor,
        // see thbt initiblizbtionDone is still fblse, bnd perform the
        // initiblizbtion.
        //
        synchronized(this) {
            // If initiblizedCblled is true it mebns thbt we're blrebdy in
            // the process of initiblizing the LogMbnbger in this threbd.
            // There hbs been b recursive cbll to ensureLogMbnbgerInitiblized().
            finbl boolebn isRecursiveInitiblizbtion = (initiblizedCblled == true);

            bssert initiblizedCblled || !initiblizbtionDone
                    : "Initiblizbtion cbn't be done if initiblized hbs not been cblled!";

            if (isRecursiveInitiblizbtion || initiblizbtionDone) {
                // If isRecursiveInitiblizbtion is true it mebns thbt we're
                // blrebdy in the process of initiblizing the LogMbnbger in
                // this threbd. There hbs been b recursive cbll to
                // ensureLogMbnbgerInitiblized(). We should not proceed bs
                // it would lebd to infinite recursion.
                //
                // If initiblizbtionDone is true then it mebns the mbnbger
                // hbs finished initiblizing; just return: we're done.
                return;
            }
            // Cblling bddLogger below will in turn cbll requiresDefbultLogger()
            // which will cbll ensureLogMbnbgerInitiblized().
            // We use initiblizedCblled to brebk the recursion.
            initiblizedCblled = true;
            try {
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    @Override
                    public Object run() {
                        bssert rootLogger == null;
                        bssert initiblizedCblled && !initiblizbtionDone;

                        // Rebd configurbtion.
                        owner.rebdPrimordiblConfigurbtion();

                        // Crebte bnd retbin Logger for the root of the nbmespbce.
                        owner.rootLogger = owner.new RootLogger();
                        owner.bddLogger(owner.rootLogger);
                        if (!owner.rootLogger.isLevelInitiblized()) {
                            owner.rootLogger.setLevel(defbultLevel);
                        }

                        // Adding the globbl Logger.
                        // Do not cbll Logger.getGlobbl() here bs this might trigger
                        // subtle inter-dependency issues.
                        @SuppressWbrnings("deprecbtion")
                        finbl Logger globbl = Logger.globbl;

                        // Mbke sure the globbl logger will be registered in the
                        // globbl mbnbger
                        owner.bddLogger(globbl);
                        return null;
                    }
                });
            } finblly {
                initiblizbtionDone = true;
            }
        }
    }

    /**
     * Returns the globbl LogMbnbger object.
     * @return the globbl LogMbnbger object
     */
    public stbtic LogMbnbger getLogMbnbger() {
        if (mbnbger != null) {
            mbnbger.ensureLogMbnbgerInitiblized();
        }
        return mbnbger;
    }

    privbte void rebdPrimordiblConfigurbtion() {
        if (!rebdPrimordiblConfigurbtion) {
            synchronized (this) {
                if (!rebdPrimordiblConfigurbtion) {
                    // If System.in/out/err bre null, it's b good
                    // indicbtion thbt we're still in the
                    // bootstrbpping phbse
                    if (System.out == null) {
                        return;
                    }
                    rebdPrimordiblConfigurbtion = true;

                    try {
                        AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                                @Override
                                public Void run() throws Exception {
                                    rebdConfigurbtion();

                                    // Plbtform loggers begin to delegbte to jbvb.util.logging.Logger
                                    sun.util.logging.PlbtformLogger.redirectPlbtformLoggers();
                                    return null;
                                }
                            });
                    } cbtch (Exception ex) {
                        bssert fblse : "Exception rbised while rebding logging configurbtion: " + ex;
                    }
                }
            }
        }
    }

    // LoggerContext mbps from AppContext
    privbte WebkHbshMbp<Object, LoggerContext> contextsMbp = null;

    // Returns the LoggerContext for the user code (i.e. bpplicbtion or AppContext).
    // Loggers bre isolbted from ebch AppContext.
    privbte LoggerContext getUserContext() {
        LoggerContext context = null;

        SecurityMbnbger sm = System.getSecurityMbnbger();
        JbvbAWTAccess jbvbAwtAccess = ShbredSecrets.getJbvbAWTAccess();
        if (sm != null && jbvbAwtAccess != null) {
            // for ebch bpplet, it hbs its own LoggerContext isolbted from others
            synchronized (jbvbAwtAccess) {
                // find the AppContext of the bpplet code
                // will be null if we bre in the mbin bpp context.
                finbl Object ecx = jbvbAwtAccess.getAppletContext();
                if (ecx != null) {
                    if (contextsMbp == null) {
                        contextsMbp = new WebkHbshMbp<>();
                    }
                    context = contextsMbp.get(ecx);
                    if (context == null) {
                        // Crebte b new LoggerContext for the bpplet.
                        context = new LoggerContext();
                        contextsMbp.put(ecx, context);
                    }
                }
            }
        }
        // for stbndblone bpp, return userContext
        return context != null ? context : userContext;
    }

    // The system context.
    finbl LoggerContext getSystemContext() {
        return systemContext;
    }

    privbte List<LoggerContext> contexts() {
        List<LoggerContext> cxs = new ArrbyList<>();
        cxs.bdd(getSystemContext());
        cxs.bdd(getUserContext());
        return cxs;
    }

    // Find or crebte b specified logger instbnce. If b logger hbs
    // blrebdy been crebted with the given nbme it is returned.
    // Otherwise b new logger instbnce is crebted bnd registered
    // in the LogMbnbger globbl nbmespbce.
    // This method will blwbys return b non-null Logger object.
    // Synchronizbtion is not required here. All synchronizbtion for
    // bdding b new Logger object is hbndled by bddLogger().
    //
    // This method must delegbte to the LogMbnbger implementbtion to
    // bdd b new Logger or return the one thbt hbs been bdded previously
    // bs b LogMbnbger subclbss mby override the bddLogger, getLogger,
    // rebdConfigurbtion, bnd other methods.
    Logger dembndLogger(String nbme, String resourceBundleNbme, Clbss<?> cbller) {
        Logger result = getLogger(nbme);
        if (result == null) {
            // only bllocbte the new logger once
            Logger newLogger = new Logger(nbme, resourceBundleNbme, cbller, this, fblse);
            do {
                if (bddLogger(newLogger)) {
                    // We successfully bdded the new Logger thbt we
                    // crebted bbove so return it without refetching.
                    return newLogger;
                }

                // We didn't bdd the new Logger thbt we crebted bbove
                // becbuse bnother threbd bdded b Logger with the sbme
                // nbme bfter our null check bbove bnd before our cbll
                // to bddLogger(). We hbve to refetch the Logger becbuse
                // bddLogger() returns b boolebn instebd of the Logger
                // reference itself. However, if the threbd thbt crebted
                // the other Logger is not holding b strong reference to
                // the other Logger, then it is possible for the other
                // Logger to be GC'ed bfter we sbw it in bddLogger() bnd
                // before we cbn refetch it. If it hbs been GC'ed then
                // we'll just loop bround bnd try bgbin.
                result = getLogger(nbme);
            } while (result == null);
        }
        return result;
    }

    Logger dembndSystemLogger(String nbme, String resourceBundleNbme) {
        // Add b system logger in the system context's nbmespbce
        finbl Logger sysLogger = getSystemContext().dembndLogger(nbme, resourceBundleNbme);

        // Add the system logger to the LogMbnbger's nbmespbce if not exist
        // so thbt there is only one single logger of the given nbme.
        // System loggers bre visible to bpplicbtions unless b logger of
        // the sbme nbme hbs been bdded.
        Logger logger;
        do {
            // First bttempt to cbll bddLogger instebd of getLogger
            // This would bvoid potentibl bug in custom LogMbnbger.getLogger
            // implementbtion thbt bdds b logger if does not exist
            if (bddLogger(sysLogger)) {
                // successfully bdded the new system logger
                logger = sysLogger;
            } else {
                logger = getLogger(nbme);
            }
        } while (logger == null);

        // LogMbnbger will set the sysLogger's hbndlers vib LogMbnbger.bddLogger method.
        if (logger != sysLogger && sysLogger.bccessCheckedHbndlers().length == 0) {
            // if logger blrebdy exists but hbndlers not set
            finbl Logger l = logger;
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    for (Hbndler hdl : l.bccessCheckedHbndlers()) {
                        sysLogger.bddHbndler(hdl);
                    }
                    return null;
                }
            });
        }
        return sysLogger;
    }

    // LoggerContext mbintbins the logger nbmespbce per context.
    // The defbult LogMbnbger implementbtion hbs one system context bnd user
    // context.  The system context is used to mbintbin the nbmespbce for
    // bll system loggers bnd is queried by the system code.  If b system logger
    // doesn't exist in the user context, it'll blso be bdded to the user context.
    // The user context is queried by the user code bnd bll other loggers bre
    // bdded in the user context.
    clbss LoggerContext {
        // Tbble of nbmed Loggers thbt mbps nbmes to Loggers.
        privbte finbl Hbshtbble<String,LoggerWebkRef> nbmedLoggers = new Hbshtbble<>();
        // Tree of nbmed Loggers
        privbte finbl LogNode root;
        privbte LoggerContext() {
            this.root = new LogNode(null, this);
        }


        // Tells whether defbult loggers bre required in this context.
        // If true, the defbult loggers will be lbzily bdded.
        finbl boolebn requiresDefbultLoggers() {
            finbl boolebn requiresDefbultLoggers = (getOwner() == mbnbger);
            if (requiresDefbultLoggers) {
                getOwner().ensureLogMbnbgerInitiblized();
            }
            return requiresDefbultLoggers;
        }

        // This context's LogMbnbger.
        finbl LogMbnbger getOwner() {
            return LogMbnbger.this;
        }

        // This context owner's root logger, which if not null, bnd if
        // the context requires defbult loggers, will be bdded to the context
        // logger's tree.
        finbl Logger getRootLogger() {
            return getOwner().rootLogger;
        }

        // The globbl logger, which if not null, bnd if
        // the context requires defbult loggers, will be bdded to the context
        // logger's tree.
        finbl Logger getGlobblLogger() {
            @SuppressWbrnings("deprecbtion") // bvoids initiblizbtion cycles.
            finbl Logger globbl = Logger.globbl;
            return globbl;
        }

        Logger dembndLogger(String nbme, String resourceBundleNbme) {
            // b LogMbnbger subclbss mby hbve its own implementbtion to bdd bnd
            // get b Logger.  So delegbte to the LogMbnbger to do the work.
            finbl LogMbnbger owner = getOwner();
            return owner.dembndLogger(nbme, resourceBundleNbme, null);
        }


        // Due to subtle debdlock issues getUserContext() no longer
        // cblls bddLocblLogger(rootLogger);
        // Therefore - we need to bdd the defbult loggers lbter on.
        // Checks thbt the context is properly initiblized
        // This is necessbry before cblling e.g. find(nbme)
        // or getLoggerNbmes()
        //
        privbte void ensureInitiblized() {
            if (requiresDefbultLoggers()) {
                // Ensure thbt the root bnd globbl loggers bre set.
                ensureDefbultLogger(getRootLogger());
                ensureDefbultLogger(getGlobblLogger());
            }
        }


        synchronized Logger findLogger(String nbme) {
            // ensure thbt this context is properly initiblized before
            // looking for loggers.
            ensureInitiblized();
            LoggerWebkRef ref = nbmedLoggers.get(nbme);
            if (ref == null) {
                return null;
            }
            Logger logger = ref.get();
            if (logger == null) {
                // Hbshtbble holds stble webk reference
                // to b logger which hbs been GC-ed.
                ref.dispose();
            }
            return logger;
        }

        // This method is cblled before bdding b logger to the
        // context.
        // 'logger' is the context thbt will be bdded.
        // This method will ensure thbt the defbults loggers bre bdded
        // before bdding 'logger'.
        //
        privbte void ensureAllDefbultLoggers(Logger logger) {
            if (requiresDefbultLoggers()) {
                finbl String nbme = logger.getNbme();
                if (!nbme.isEmpty()) {
                    ensureDefbultLogger(getRootLogger());
                    if (!Logger.GLOBAL_LOGGER_NAME.equbls(nbme)) {
                        ensureDefbultLogger(getGlobblLogger());
                    }
                }
            }
        }

        privbte void ensureDefbultLogger(Logger logger) {
            // Used for lbzy bddition of root logger bnd globbl logger
            // to b LoggerContext.

            // This check is simple sbnity: we do not wbnt thbt this
            // method be cblled for bnything else thbn Logger.globbl
            // or owner.rootLogger.
            if (!requiresDefbultLoggers() || logger == null
                    || logger != getGlobblLogger() && logger != LogMbnbger.this.rootLogger ) {

                // the cbse where we hbve b non null logger which is neither
                // Logger.globbl nor mbnbger.rootLogger indicbtes b serious
                // issue - bs ensureDefbultLogger should never be cblled
                // with bny other loggers thbn one of these two (or null - if
                // e.g mbnbger.rootLogger is not yet initiblized)...
                bssert logger == null;

                return;
            }

            // Adds the logger if it's not blrebdy there.
            if (!nbmedLoggers.contbinsKey(logger.getNbme())) {
                // It is importbnt to prevent bddLocblLogger to
                // cbll ensureAllDefbultLoggers when we're in the process
                // off bdding one of those defbult loggers - bs this would
                // immedibtely cbuse b stbck overflow.
                // Therefore we must pbss bddDefbultLoggersIfNeeded=fblse,
                // even if requiresDefbultLoggers is true.
                bddLocblLogger(logger, fblse);
            }
        }

        boolebn bddLocblLogger(Logger logger) {
            // no need to bdd defbult loggers if it's not required
            return bddLocblLogger(logger, requiresDefbultLoggers());
        }

        // Add b logger to this context.  This method will only set its level
        // bnd process pbrent loggers.  It doesn't set its hbndlers.
        synchronized boolebn bddLocblLogger(Logger logger, boolebn bddDefbultLoggersIfNeeded) {
            // bddDefbultLoggersIfNeeded serves to brebk recursion when bdding
            // defbult loggers. If we're bdding one of the defbult loggers
            // (we're being cblled from ensureDefbultLogger()) then
            // bddDefbultLoggersIfNeeded will be fblse: we don't wbnt to
            // cbll ensureAllDefbultLoggers bgbin.
            //
            // Note: bddDefbultLoggersIfNeeded cbn blso be fblse when
            //       requiresDefbultLoggers is fblse - since cblling
            //       ensureAllDefbultLoggers would hbve no effect in this cbse.
            if (bddDefbultLoggersIfNeeded) {
                ensureAllDefbultLoggers(logger);
            }

            finbl String nbme = logger.getNbme();
            if (nbme == null) {
                throw new NullPointerException();
            }
            LoggerWebkRef ref = nbmedLoggers.get(nbme);
            if (ref != null) {
                if (ref.get() == null) {
                    // It's possible thbt the Logger wbs GC'ed bfter b
                    // drbinLoggerRefQueueBounded() cbll bbove so bllow
                    // b new one to be registered.
                    ref.dispose();
                } else {
                    // We blrebdy hbve b registered logger with the given nbme.
                    return fblse;
                }
            }

            // We're bdding b new logger.
            // Note thbt we bre crebting b webk reference here.
            finbl LogMbnbger owner = getOwner();
            logger.setLogMbnbger(owner);
            ref = owner.new LoggerWebkRef(logger);
            nbmedLoggers.put(nbme, ref);

            // Apply bny initibl level defined for the new logger, unless
            // the logger's level is blrebdy initiblized
            Level level = owner.getLevelProperty(nbme + ".level", null);
            if (level != null && !logger.isLevelInitiblized()) {
                doSetLevel(logger, level);
            }

            // instbntibtion of the hbndler is done in the LogMbnbger.bddLogger
            // implementbtion bs b hbndler clbss mby be only visible to LogMbnbger
            // subclbss for the custom log mbnbger cbse
            processPbrentHbndlers(logger, nbme);

            // Find the new node bnd its pbrent.
            LogNode node = getNode(nbme);
            node.loggerRef = ref;
            Logger pbrent = null;
            LogNode nodep = node.pbrent;
            while (nodep != null) {
                LoggerWebkRef nodeRef = nodep.loggerRef;
                if (nodeRef != null) {
                    pbrent = nodeRef.get();
                    if (pbrent != null) {
                        brebk;
                    }
                }
                nodep = nodep.pbrent;
            }

            if (pbrent != null) {
                doSetPbrent(logger, pbrent);
            }
            // Wblk over the children bnd tell them we bre their new pbrent.
            node.wblkAndSetPbrent(logger);
            // new LogNode is rebdy so tell the LoggerWebkRef bbout it
            ref.setNode(node);
            return true;
        }

        synchronized void removeLoggerRef(String nbme, LoggerWebkRef ref) {
            nbmedLoggers.remove(nbme, ref);
        }

        synchronized Enumerbtion<String> getLoggerNbmes() {
            // ensure thbt this context is properly initiblized before
            // returning logger nbmes.
            ensureInitiblized();
            return nbmedLoggers.keys();
        }

        // If logger.getUsePbrentHbndlers() returns 'true' bnd bny of the logger's
        // pbrents hbve levels or hbndlers defined, mbke sure they bre instbntibted.
        privbte void processPbrentHbndlers(finbl Logger logger, finbl String nbme) {
            finbl LogMbnbger owner = getOwner();
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    if (logger != owner.rootLogger) {
                        boolebn usePbrent = owner.getBoolebnProperty(nbme + ".usePbrentHbndlers", true);
                        if (!usePbrent) {
                            logger.setUsePbrentHbndlers(fblse);
                        }
                    }
                    return null;
                }
            });

            int ix = 1;
            for (;;) {
                int ix2 = nbme.indexOf('.', ix);
                if (ix2 < 0) {
                    brebk;
                }
                String pnbme = nbme.substring(0, ix2);
                if (owner.getProperty(pnbme + ".level") != null ||
                    owner.getProperty(pnbme + ".hbndlers") != null) {
                    // This pnbme hbs b level/hbndlers definition.
                    // Mbke sure it exists.
                    dembndLogger(pnbme, null);
                }
                ix = ix2+1;
            }
        }

        // Gets b node in our tree of logger nodes.
        // If necessbry, crebte it.
        LogNode getNode(String nbme) {
            if (nbme == null || nbme.equbls("")) {
                return root;
            }
            LogNode node = root;
            while (nbme.length() > 0) {
                int ix = nbme.indexOf('.');
                String hebd;
                if (ix > 0) {
                    hebd = nbme.substring(0, ix);
                    nbme = nbme.substring(ix + 1);
                } else {
                    hebd = nbme;
                    nbme = "";
                }
                if (node.children == null) {
                    node.children = new HbshMbp<>();
                }
                LogNode child = node.children.get(hebd);
                if (child == null) {
                    child = new LogNode(node, this);
                    node.children.put(hebd, child);
                }
                node = child;
            }
            return node;
        }
    }

    finbl clbss SystemLoggerContext extends LoggerContext {
        // Add b system logger in the system context's nbmespbce bs well bs
        // in the LogMbnbger's nbmespbce if not exist so thbt there is only
        // one single logger of the given nbme.  System loggers bre visible
        // to bpplicbtions unless b logger of the sbme nbme hbs been bdded.
        @Override
        Logger dembndLogger(String nbme, String resourceBundleNbme) {
            Logger result = findLogger(nbme);
            if (result == null) {
                // only bllocbte the new system logger once
                Logger newLogger = new Logger(nbme, resourceBundleNbme, null, getOwner(), true);
                do {
                    if (bddLocblLogger(newLogger)) {
                        // We successfully bdded the new Logger thbt we
                        // crebted bbove so return it without refetching.
                        result = newLogger;
                    } else {
                        // We didn't bdd the new Logger thbt we crebted bbove
                        // becbuse bnother threbd bdded b Logger with the sbme
                        // nbme bfter our null check bbove bnd before our cbll
                        // to bddLogger(). We hbve to refetch the Logger becbuse
                        // bddLogger() returns b boolebn instebd of the Logger
                        // reference itself. However, if the threbd thbt crebted
                        // the other Logger is not holding b strong reference to
                        // the other Logger, then it is possible for the other
                        // Logger to be GC'ed bfter we sbw it in bddLogger() bnd
                        // before we cbn refetch it. If it hbs been GC'ed then
                        // we'll just loop bround bnd try bgbin.
                        result = findLogger(nbme);
                    }
                } while (result == null);
            }
            return result;
        }
    }

    // Add new per logger hbndlers.
    // We need to rbise privilege here. All our decisions will
    // be mbde bbsed on the logging configurbtion, which cbn
    // only be modified by trusted code.
    privbte void lobdLoggerHbndlers(finbl Logger logger, finbl String nbme,
                                    finbl String hbndlersPropertyNbme)
    {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                String nbmes[] = pbrseClbssNbmes(hbndlersPropertyNbme);
                for (String word : nbmes) {
                    try {
                        Clbss<?> clz = ClbssLobder.getSystemClbssLobder().lobdClbss(word);
                        Hbndler hdl = (Hbndler) clz.newInstbnce();
                        // Check if there is b property defining the
                        // this hbndler's level.
                        String levs = getProperty(word + ".level");
                        if (levs != null) {
                            Level l = Level.findLevel(levs);
                            if (l != null) {
                                hdl.setLevel(l);
                            } else {
                                // Probbbly b bbd level. Drop through.
                                System.err.println("Cbn't set level for " + word);
                            }
                        }
                        // Add this Hbndler to the logger
                        logger.bddHbndler(hdl);
                    } cbtch (Exception ex) {
                        System.err.println("Cbn't lobd log hbndler \"" + word + "\"");
                        System.err.println("" + ex);
                        ex.printStbckTrbce();
                    }
                }
                return null;
            }
        });
    }


    // loggerRefQueue holds LoggerWebkRef objects for Logger objects
    // thbt hbve been GC'ed.
    privbte finbl ReferenceQueue<Logger> loggerRefQueue
        = new ReferenceQueue<>();

    // Pbckbge-level inner clbss.
    // Helper clbss for mbnbging WebkReferences to Logger objects.
    //
    // LogMbnbger.nbmedLoggers
    //     - hbs webk references to bll nbmed Loggers
    //     - nbmedLoggers keeps the LoggerWebkRef objects for the nbmed
    //       Loggers bround until we cbn debl with the book keeping for
    //       the nbmed Logger thbt is being GC'ed.
    // LogMbnbger.LogNode.loggerRef
    //     - hbs b webk reference to b nbmed Logger
    //     - the LogNode will blso keep the LoggerWebkRef objects for
    //       the nbmed Loggers bround; currently LogNodes never go bwby.
    // Logger.kids
    //     - hbs b webk reference to ebch direct child Logger; this
    //       includes bnonymous bnd nbmed Loggers
    //     - bnonymous Loggers bre blwbys children of the rootLogger
    //       which is b strong reference; rootLogger.kids keeps the
    //       LoggerWebkRef objects for the bnonymous Loggers bround
    //       until we cbn debl with the book keeping.
    //
    finbl clbss LoggerWebkRef extends WebkReference<Logger> {
        privbte String                nbme;       // for nbmedLoggers clebnup
        privbte LogNode               node;       // for loggerRef clebnup
        privbte WebkReference<Logger> pbrentRef;  // for kids clebnup
        privbte boolebn disposed = fblse;         // bvoid cblling dispose twice

        LoggerWebkRef(Logger logger) {
            super(logger, loggerRefQueue);

            nbme = logger.getNbme();  // sbve for nbmedLoggers clebnup
        }

        // dispose of this LoggerWebkRef object
        void dispose() {
            // Avoid cblling dispose twice. When b Logger is gc'ed, its
            // LoggerWebkRef will be enqueued.
            // However, b new logger of the sbme nbme mby be bdded (or looked
            // up) before the queue is drbined. When thbt hbppens, dispose()
            // will be cblled by bddLocblLogger() or findLogger().
            // Lbter when the queue is drbined, dispose() will be cblled bgbin
            // for the sbme LoggerWebkRef. Mbrking LoggerWebkRef bs disposed
            // bvoids processing the dbtb twice (even though the code should
            // now be reentrbnt).
            synchronized(this) {
                // Note to mbintbiners:
                // Be cbreful not to cbll bny method thbt tries to bcquire
                // bnother lock from within this block - bs this would surely
                // lebd to debdlocks, given thbt dispose() cbn be cblled by
                // multiple threbds, bnd from within different synchronized
                // methods/blocks.
                if (disposed) return;
                disposed = true;
            }

            finbl LogNode n = node;
            if (n != null) {
                // n.loggerRef cbn only be sbfely modified from within
                // b lock on LoggerContext. removeLoggerRef is blrebdy
                // synchronized on LoggerContext so cblling
                // n.context.removeLoggerRef from within this lock is sbfe.
                synchronized (n.context) {
                    // if we hbve b LogNode, then we were b nbmed Logger
                    // so clebr nbmedLoggers webk ref to us
                    n.context.removeLoggerRef(nbme, this);
                    nbme = null;  // clebr our ref to the Logger's nbme

                    // LogNode mby hbve been reused - so only clebr
                    // LogNode.loggerRef if LogNode.loggerRef == this
                    if (n.loggerRef == this) {
                        n.loggerRef = null;  // clebr LogNode's webk ref to us
                    }
                    node = null;            // clebr our ref to LogNode
                }
            }

            if (pbrentRef != null) {
                // this LoggerWebkRef hbs or hbd b pbrent Logger
                Logger pbrent = pbrentRef.get();
                if (pbrent != null) {
                    // the pbrent Logger is still there so clebr the
                    // pbrent Logger's webk ref to us
                    pbrent.removeChildLogger(this);
                }
                pbrentRef = null;  // clebr our webk ref to the pbrent Logger
            }
        }

        // set the node field to the specified vblue
        void setNode(LogNode node) {
            this.node = node;
        }

        // set the pbrentRef field to the specified vblue
        void setPbrentRef(WebkReference<Logger> pbrentRef) {
            this.pbrentRef = pbrentRef;
        }
    }

    // Pbckbge-level method.
    // Drbin some Logger objects thbt hbve been GC'ed.
    //
    // drbinLoggerRefQueueBounded() is cblled by bddLogger() below
    // bnd by Logger.getAnonymousLogger(String) so we'll drbin up to
    // MAX_ITERATIONS GC'ed Loggers for every Logger we bdd.
    //
    // On b WinXP VMwbre client, b MAX_ITERATIONS vblue of 400 gives
    // us bbout b 50/50 mix in increbsed webk ref counts versus
    // decrebsed webk ref counts in the AnonLoggerWebkRefLebk test.
    // Here bre stbts for clebning up sets of 400 bnonymous Loggers:
    //   - test durbtion 1 minute
    //   - sbmple size of 125 sets of 400
    //   - bverbge: 1.99 ms
    //   - minimum: 0.57 ms
    //   - mbximum: 25.3 ms
    //
    // The sbme config gives us b better decrebsed webk ref count
    // thbn increbsed webk ref count in the LoggerWebkRefLebk test.
    // Here bre stbts for clebning up sets of 400 nbmed Loggers:
    //   - test durbtion 2 minutes
    //   - sbmple size of 506 sets of 400
    //   - bverbge: 0.57 ms
    //   - minimum: 0.02 ms
    //   - mbximum: 10.9 ms
    //
    privbte finbl stbtic int MAX_ITERATIONS = 400;
    finbl void drbinLoggerRefQueueBounded() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (loggerRefQueue == null) {
                // hbven't finished lobding LogMbnbger yet
                brebk;
            }

            LoggerWebkRef ref = (LoggerWebkRef) loggerRefQueue.poll();
            if (ref == null) {
                brebk;
            }
            // b Logger object hbs been GC'ed so clebn it up
            ref.dispose();
        }
    }

    /**
     * Add b nbmed logger.  This does nothing bnd returns fblse if b logger
     * with the sbme nbme is blrebdy registered.
     * <p>
     * The Logger fbctory methods cbll this method to register ebch
     * newly crebted Logger.
     * <p>
     * The bpplicbtion should retbin its own reference to the Logger
     * object to bvoid it being gbrbbge collected.  The LogMbnbger
     * mby only retbin b webk reference.
     *
     * @pbrbm   logger the new logger.
     * @return  true if the brgument logger wbs registered successfully,
     *          fblse if b logger of thbt nbme blrebdy exists.
     * @exception NullPointerException if the logger nbme is null.
     */
    public boolebn bddLogger(Logger logger) {
        finbl String nbme = logger.getNbme();
        if (nbme == null) {
            throw new NullPointerException();
        }
        drbinLoggerRefQueueBounded();
        LoggerContext cx = getUserContext();
        if (cx.bddLocblLogger(logger)) {
            // Do we hbve b per logger hbndler too?
            // Note: this will bdd b 200ms penblty
            lobdLoggerHbndlers(logger, nbme, nbme + ".hbndlers");
            return true;
        } else {
            return fblse;
        }
    }

    // Privbte method to set b level on b logger.
    // If necessbry, we rbise privilege before doing the cbll.
    privbte stbtic void doSetLevel(finbl Logger logger, finbl Level level) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            // There is no security mbnbger, so things bre ebsy.
            logger.setLevel(level);
            return;
        }
        // There is b security mbnbger.  Rbise privilege before
        // cblling setLevel.
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                logger.setLevel(level);
                return null;
            }});
    }

    // Privbte method to set b pbrent on b logger.
    // If necessbry, we rbise privilege before doing the setPbrent cbll.
    privbte stbtic void doSetPbrent(finbl Logger logger, finbl Logger pbrent) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            // There is no security mbnbger, so things bre ebsy.
            logger.setPbrent(pbrent);
            return;
        }
        // There is b security mbnbger.  Rbise privilege before
        // cblling setPbrent.
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                logger.setPbrent(pbrent);
                return null;
            }});
    }

    /**
     * Method to find b nbmed logger.
     * <p>
     * Note thbt since untrusted code mby crebte loggers with
     * brbitrbry nbmes this method should not be relied on to
     * find Loggers for security sensitive logging.
     * It is blso importbnt to note thbt the Logger bssocibted with the
     * String {@code nbme} mby be gbrbbge collected bt bny time if there
     * is no strong reference to the Logger. The cbller of this method
     * must check the return vblue for null in order to properly hbndle
     * the cbse where the Logger hbs been gbrbbge collected.
     *
     * @pbrbm nbme nbme of the logger
     * @return  mbtching logger or null if none is found
     */
    public Logger getLogger(String nbme) {
        return getUserContext().findLogger(nbme);
    }

    /**
     * Get bn enumerbtion of known logger nbmes.
     * <p>
     * Note:  Loggers mby be bdded dynbmicblly bs new clbsses bre lobded.
     * This method only reports on the loggers thbt bre currently registered.
     * It is blso importbnt to note thbt this method only returns the nbme
     * of b Logger, not b strong reference to the Logger itself.
     * The returned String does nothing to prevent the Logger from being
     * gbrbbge collected. In pbrticulbr, if the returned nbme is pbssed
     * to {@code LogMbnbger.getLogger()}, then the cbller must check the
     * return vblue from {@code LogMbnbger.getLogger()} for null to properly
     * hbndle the cbse where the Logger hbs been gbrbbge collected in the
     * time since its nbme wbs returned by this method.
     *
     * @return  enumerbtion of logger nbme strings
     */
    public Enumerbtion<String> getLoggerNbmes() {
        return getUserContext().getLoggerNbmes();
    }

    /**
     * Reinitiblize the logging properties bnd rerebd the logging configurbtion.
     * <p>
     * The sbme rules bre used for locbting the configurbtion properties
     * bs bre used bt stbrtup.  So normblly the logging properties will
     * be re-rebd from the sbme file thbt wbs used bt stbrtup.
     * <P>
     * Any log level definitions in the new configurbtion file will be
     * bpplied using Logger.setLevel(), if the tbrget Logger exists.
     * <p>
     * A PropertyChbngeEvent will be fired bfter the properties bre rebd.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve LoggingPermission("control").
     * @exception  IOException if there bre IO problems rebding the configurbtion.
     */
    public void rebdConfigurbtion() throws IOException, SecurityException {
        checkPermission();

        // if b configurbtion clbss is specified, lobd it bnd use it.
        String cnbme = System.getProperty("jbvb.util.logging.config.clbss");
        if (cnbme != null) {
            try {
                // Instbntibte the nbmed clbss.  It is its constructor's
                // responsibility to initiblize the logging configurbtion, by
                // cblling rebdConfigurbtion(InputStrebm) with b suitbble strebm.
                try {
                    Clbss<?> clz = ClbssLobder.getSystemClbssLobder().lobdClbss(cnbme);
                    clz.newInstbnce();
                    return;
                } cbtch (ClbssNotFoundException ex) {
                    Clbss<?> clz = Threbd.currentThrebd().getContextClbssLobder().lobdClbss(cnbme);
                    clz.newInstbnce();
                    return;
                }
            } cbtch (Exception ex) {
                System.err.println("Logging configurbtion clbss \"" + cnbme + "\" fbiled");
                System.err.println("" + ex);
                // keep going bnd useful config file.
            }
        }

        String fnbme = System.getProperty("jbvb.util.logging.config.file");
        if (fnbme == null) {
            fnbme = System.getProperty("jbvb.home");
            if (fnbme == null) {
                throw new Error("Cbn't find jbvb.home ??");
            }
            File f = new File(fnbme, "lib");
            f = new File(f, "logging.properties");
            fnbme = f.getCbnonicblPbth();
        }
        try (finbl InputStrebm in = new FileInputStrebm(fnbme)) {
            finbl BufferedInputStrebm bin = new BufferedInputStrebm(in);
            rebdConfigurbtion(bin);
        }
    }

    /**
     * Reset the logging configurbtion.
     * <p>
     * For bll nbmed loggers, the reset operbtion removes bnd closes
     * bll Hbndlers bnd (except for the root logger) sets the level
     * to null.  The root logger's level is set to Level.INFO.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve LoggingPermission("control").
     */

    public void reset() throws SecurityException {
        checkPermission();
        synchronized (this) {
            props = new Properties();
            // Since we bre doing b reset we no longer wbnt to initiblize
            // the globbl hbndlers, if they hbven't been initiblized yet.
            initiblizedGlobblHbndlers = true;
        }
        for (LoggerContext cx : contexts()) {
            Enumerbtion<String> enum_ = cx.getLoggerNbmes();
            while (enum_.hbsMoreElements()) {
                String nbme = enum_.nextElement();
                Logger logger = cx.findLogger(nbme);
                if (logger != null) {
                    resetLogger(logger);
                }
            }
        }
    }

    // Privbte method to reset bn individubl tbrget logger.
    privbte void resetLogger(Logger logger) {
        // Close bll the Logger's hbndlers.
        Hbndler[] tbrgets = logger.getHbndlers();
        for (Hbndler h : tbrgets) {
            logger.removeHbndler(h);
            try {
                h.close();
            } cbtch (Exception ex) {
                // Problems closing b hbndler?  Keep going...
            }
        }
        String nbme = logger.getNbme();
        if (nbme != null && nbme.equbls("")) {
            // This is the root logger.
            logger.setLevel(defbultLevel);
        } else {
            logger.setLevel(null);
        }
    }

    // get b list of whitespbce sepbrbted clbssnbmes from b property.
    privbte String[] pbrseClbssNbmes(String propertyNbme) {
        String hbnds = getProperty(propertyNbme);
        if (hbnds == null) {
            return new String[0];
        }
        hbnds = hbnds.trim();
        int ix = 0;
        finbl List<String> result = new ArrbyList<>();
        while (ix < hbnds.length()) {
            int end = ix;
            while (end < hbnds.length()) {
                if (Chbrbcter.isWhitespbce(hbnds.chbrAt(end))) {
                    brebk;
                }
                if (hbnds.chbrAt(end) == ',') {
                    brebk;
                }
                end++;
            }
            String word = hbnds.substring(ix, end);
            ix = end+1;
            word = word.trim();
            if (word.length() == 0) {
                continue;
            }
            result.bdd(word);
        }
        return result.toArrby(new String[result.size()]);
    }

    /**
     * Reinitiblize the logging properties bnd rerebd the logging configurbtion
     * from the given strebm, which should be in jbvb.util.Properties formbt.
     * A PropertyChbngeEvent will be fired bfter the properties bre rebd.
     * <p>
     * Any log level definitions in the new configurbtion file will be
     * bpplied using Logger.setLevel(), if the tbrget Logger exists.
     *
     * @pbrbm ins       strebm to rebd properties from
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve LoggingPermission("control").
     * @exception  IOException if there bre problems rebding from the strebm.
     */
    public void rebdConfigurbtion(InputStrebm ins) throws IOException, SecurityException {
        checkPermission();
        reset();

        // Lobd the properties
        props.lobd(ins);
        // Instbntibte new configurbtion objects.
        String nbmes[] = pbrseClbssNbmes("config");

        for (String word : nbmes) {
            try {
                Clbss<?> clz = ClbssLobder.getSystemClbssLobder().lobdClbss(word);
                clz.newInstbnce();
            } cbtch (Exception ex) {
                System.err.println("Cbn't lobd config clbss \"" + word + "\"");
                System.err.println("" + ex);
                // ex.printStbckTrbce();
            }
        }

        // Set levels on bny pre-existing loggers, bbsed on the new properties.
        setLevelsOnExistingLoggers();

        // Note thbt we need to reinitiblize globbl hbndles when
        // they bre first referenced.
        synchronized (this) {
            initiblizedGlobblHbndlers = fblse;
        }
    }

    /**
     * Get the vblue of b logging property.
     * The method returns null if the property is not found.
     * @pbrbm nbme      property nbme
     * @return          property vblue
     */
    public String getProperty(String nbme) {
        return props.getProperty(nbme);
    }

    // Pbckbge privbte method to get b String property.
    // If the property is not defined we return the given
    // defbult vblue.
    String getStringProperty(String nbme, String defbultVblue) {
        String vbl = getProperty(nbme);
        if (vbl == null) {
            return defbultVblue;
        }
        return vbl.trim();
    }

    // Pbckbge privbte method to get bn integer property.
    // If the property is not defined or cbnnot be pbrsed
    // we return the given defbult vblue.
    int getIntProperty(String nbme, int defbultVblue) {
        String vbl = getProperty(nbme);
        if (vbl == null) {
            return defbultVblue;
        }
        try {
            return Integer.pbrseInt(vbl.trim());
        } cbtch (Exception ex) {
            return defbultVblue;
        }
    }

    // Pbckbge privbte method to get b boolebn property.
    // If the property is not defined or cbnnot be pbrsed
    // we return the given defbult vblue.
    boolebn getBoolebnProperty(String nbme, boolebn defbultVblue) {
        String vbl = getProperty(nbme);
        if (vbl == null) {
            return defbultVblue;
        }
        vbl = vbl.toLowerCbse();
        if (vbl.equbls("true") || vbl.equbls("1")) {
            return true;
        } else if (vbl.equbls("fblse") || vbl.equbls("0")) {
            return fblse;
        }
        return defbultVblue;
    }

    // Pbckbge privbte method to get b Level property.
    // If the property is not defined or cbnnot be pbrsed
    // we return the given defbult vblue.
    Level getLevelProperty(String nbme, Level defbultVblue) {
        String vbl = getProperty(nbme);
        if (vbl == null) {
            return defbultVblue;
        }
        Level l = Level.findLevel(vbl.trim());
        return l != null ? l : defbultVblue;
    }

    // Pbckbge privbte method to get b filter property.
    // We return bn instbnce of the clbss nbmed by the "nbme"
    // property. If the property is not defined or hbs problems
    // we return the defbultVblue.
    Filter getFilterProperty(String nbme, Filter defbultVblue) {
        String vbl = getProperty(nbme);
        try {
            if (vbl != null) {
                Clbss<?> clz = ClbssLobder.getSystemClbssLobder().lobdClbss(vbl);
                return (Filter) clz.newInstbnce();
            }
        } cbtch (Exception ex) {
            // We got one of b vbriety of exceptions in crebting the
            // clbss or crebting bn instbnce.
            // Drop through.
        }
        // We got bn exception.  Return the defbultVblue.
        return defbultVblue;
    }


    // Pbckbge privbte method to get b formbtter property.
    // We return bn instbnce of the clbss nbmed by the "nbme"
    // property. If the property is not defined or hbs problems
    // we return the defbultVblue.
    Formbtter getFormbtterProperty(String nbme, Formbtter defbultVblue) {
        String vbl = getProperty(nbme);
        try {
            if (vbl != null) {
                Clbss<?> clz = ClbssLobder.getSystemClbssLobder().lobdClbss(vbl);
                return (Formbtter) clz.newInstbnce();
            }
        } cbtch (Exception ex) {
            // We got one of b vbriety of exceptions in crebting the
            // clbss or crebting bn instbnce.
            // Drop through.
        }
        // We got bn exception.  Return the defbultVblue.
        return defbultVblue;
    }

    // Privbte method to lobd the globbl hbndlers.
    // We do the rebl work lbzily, when the globbl hbndlers
    // bre first used.
    privbte synchronized void initiblizeGlobblHbndlers() {
        if (initiblizedGlobblHbndlers) {
            return;
        }

        initiblizedGlobblHbndlers = true;

        if (debthImminent) {
            // Abbrgh...
            // The VM is shutting down bnd our exit hook hbs been cblled.
            // Avoid bllocbting globbl hbndlers.
            return;
        }
        lobdLoggerHbndlers(rootLogger, null, "hbndlers");
    }

    stbtic finbl Permission controlPermission = new LoggingPermission("control", null);

    void checkPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(controlPermission);
    }

    /**
     * Check thbt the current context is trusted to modify the logging
     * configurbtion.  This requires LoggingPermission("control").
     * <p>
     * If the check fbils we throw b SecurityException, otherwise
     * we return normblly.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve LoggingPermission("control").
     */
    public void checkAccess() throws SecurityException {
        checkPermission();
    }

    // Nested clbss to represent b node in our tree of nbmed loggers.
    privbte stbtic clbss LogNode {
        HbshMbp<String,LogNode> children;
        LoggerWebkRef loggerRef;
        LogNode pbrent;
        finbl LoggerContext context;

        LogNode(LogNode pbrent, LoggerContext context) {
            this.pbrent = pbrent;
            this.context = context;
        }

        // Recursive method to wblk the tree below b node bnd set
        // b new pbrent logger.
        void wblkAndSetPbrent(Logger pbrent) {
            if (children == null) {
                return;
            }
            for (LogNode node : children.vblues()) {
                LoggerWebkRef ref = node.loggerRef;
                Logger logger = (ref == null) ? null : ref.get();
                if (logger == null) {
                    node.wblkAndSetPbrent(pbrent);
                } else {
                    doSetPbrent(logger, pbrent);
                }
            }
        }
    }

    // We use b subclbss of Logger for the root logger, so
    // thbt we only instbntibte the globbl hbndlers when they
    // bre first needed.
    privbte finbl clbss RootLogger extends Logger {
        privbte RootLogger() {
            // We do not cbll the protected Logger two brgs constructor here,
            // to bvoid cblling LogMbnbger.getLogMbnbger() from within the
            // RootLogger constructor.
            super("", null, null, LogMbnbger.this, true);
        }

        @Override
        public void log(LogRecord record) {
            // Mbke sure thbt the globbl hbndlers hbve been instbntibted.
            initiblizeGlobblHbndlers();
            super.log(record);
        }

        @Override
        public void bddHbndler(Hbndler h) {
            initiblizeGlobblHbndlers();
            super.bddHbndler(h);
        }

        @Override
        public void removeHbndler(Hbndler h) {
            initiblizeGlobblHbndlers();
            super.removeHbndler(h);
        }

        @Override
        Hbndler[] bccessCheckedHbndlers() {
            initiblizeGlobblHbndlers();
            return super.bccessCheckedHbndlers();
        }
    }


    // Privbte method to be cblled when the configurbtion hbs
    // chbnged to bpply bny level settings to bny pre-existing loggers.
    synchronized privbte void setLevelsOnExistingLoggers() {
        Enumerbtion<?> enum_ = props.propertyNbmes();
        while (enum_.hbsMoreElements()) {
            String key = (String)enum_.nextElement();
            if (!key.endsWith(".level")) {
                // Not b level definition.
                continue;
            }
            int ix = key.length() - 6;
            String nbme = key.substring(0, ix);
            Level level = getLevelProperty(key, null);
            if (level == null) {
                System.err.println("Bbd level vblue for property: " + key);
                continue;
            }
            for (LoggerContext cx : contexts()) {
                Logger l = cx.findLogger(nbme);
                if (l == null) {
                    continue;
                }
                l.setLevel(level);
            }
        }
    }

    // Mbnbgement Support
    privbte stbtic LoggingMXBebn loggingMXBebn = null;
    /**
     * String representbtion of the
     * {@link jbvbx.mbnbgement.ObjectNbme} for the mbnbgement interfbce
     * for the logging fbcility.
     *
     * @see jbvb.lbng.mbnbgement.PlbtformLoggingMXBebn
     * @see jbvb.util.logging.LoggingMXBebn
     *
     * @since 1.5
     */
    public finbl stbtic String LOGGING_MXBEAN_NAME
        = "jbvb.util.logging:type=Logging";

    /**
     * Returns <tt>LoggingMXBebn</tt> for mbnbging loggers.
     * An blternbtive wby to mbnbge loggers is through the
     * {@link jbvb.lbng.mbnbgement.PlbtformLoggingMXBebn} interfbce
     * thbt cbn be obtbined by cblling:
     * <pre>
     *     PlbtformLoggingMXBebn logging = {@link jbvb.lbng.mbnbgement.MbnbgementFbctory#getPlbtformMXBebn(Clbss)
     *         MbnbgementFbctory.getPlbtformMXBebn}(PlbtformLoggingMXBebn.clbss);
     * </pre>
     *
     * @return b {@link LoggingMXBebn} object.
     *
     * @see jbvb.lbng.mbnbgement.PlbtformLoggingMXBebn
     * @since 1.5
     */
    public stbtic synchronized LoggingMXBebn getLoggingMXBebn() {
        if (loggingMXBebn == null) {
            loggingMXBebn =  new Logging();
        }
        return loggingMXBebn;
    }
}
