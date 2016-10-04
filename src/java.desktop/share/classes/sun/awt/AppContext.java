/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.EventQueue;
import jbvb.bwt.Window;
import jbvb.bwt.SystemTrby;
import jbvb.bwt.TrbyIcon;
import jbvb.bwt.Toolkit;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.IdentityHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.bebns.PropertyChbngeSupport;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.lbng.ref.SoftReference;
import sun.util.logging.PlbtformLogger;
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.Lock;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.function.Supplier;

/**
 * The AppContext is b tbble referenced by ThrebdGroup which stores
 * bpplicbtion service instbnces.  (If you bre not writing bn bpplicbtion
 * service, or don't know whbt one is, plebse do not use this clbss.)
 * The AppContext bllows bpplet bccess to whbt would otherwise be
 * potentiblly dbngerous services, such bs the bbility to peek bt
 * EventQueues or chbnge the look-bnd-feel of b Swing bpplicbtion.<p>
 *
 * Most bpplicbtion services use b singleton object to provide their
 * services, either bs b defbult (such bs getSystemEventQueue or
 * getDefbultToolkit) or bs stbtic methods with clbss dbtb (System).
 * The AppContext works with the former method by extending the concept
 * of "defbult" to be ThrebdGroup-specific.  Applicbtion services
 * lookup their singleton in the AppContext.<p>
 *
 * For exbmple, here we hbve b Foo service, with its pre-AppContext
 * code:<p>
 * <code><pre>
 *    public clbss Foo {
 *        privbte stbtic Foo defbultFoo = new Foo();
 *
 *        public stbtic Foo getDefbultFoo() {
 *            return defbultFoo;
 *        }
 *
 *    ... Foo service methods
 *    }</pre></code><p>
 *
 * The problem with the bbove is thbt the Foo service is globbl in scope,
 * so thbt bpplets bnd other untrusted code cbn execute methods on the
 * single, shbred Foo instbnce.  The Foo service therefore either needs
 * to block its use by untrusted code using b SecurityMbnbger test, or
 * restrict its cbpbbilities so thbt it doesn't mbtter if untrusted code
 * executes it.<p>
 *
 * Here's the Foo clbss written to use the AppContext:<p>
 * <code><pre>
 *    public clbss Foo {
 *        public stbtic Foo getDefbultFoo() {
 *            Foo foo = (Foo)AppContext.getAppContext().get(Foo.clbss);
 *            if (foo == null) {
 *                foo = new Foo();
 *                getAppContext().put(Foo.clbss, foo);
 *            }
 *            return foo;
 *        }
 *
 *    ... Foo service methods
 *    }</pre></code><p>
 *
 * Since b sepbrbte AppContext cbn exist for ebch ThrebdGroup, trusted
 * bnd untrusted code hbve bccess to different Foo instbnces.  This bllows
 * untrusted code bccess to "system-wide" services -- the service rembins
 * within the AppContext "sbndbox".  For exbmple, sby b mblicious bpplet
 * wbnts to peek bll of the key events on the EventQueue to listen for
 * pbsswords; if sepbrbte EventQueues bre used for ebch ThrebdGroup
 * using AppContexts, the only key events thbt bpplet will be bble to
 * listen to bre its own.  A more rebsonbble bpplet request would be to
 * chbnge the Swing defbult look-bnd-feel; with thbt defbult stored in
 * bn AppContext, the bpplet's look-bnd-feel will chbnge without
 * disrupting other bpplets or potentiblly the browser itself.<p>
 *
 * Becbuse the AppContext is b fbcility for sbfely extending bpplicbtion
 * service support to bpplets, none of its methods mby be blocked by b
 * b SecurityMbnbger check in b vblid Jbvb implementbtion.  Applets mby
 * therefore sbfely invoke bny of its methods without worry of being
 * blocked.
 *
 * Note: If b SecurityMbnbger is instblled which derives from
 * sun.bwt.AWTSecurityMbnbger, it mby override the
 * AWTSecurityMbnbger.getAppContext() method to return the proper
 * AppContext bbsed on the execution context, in the cbse where
 * the defbult ThrebdGroup-bbsed AppContext indexing would return
 * the mbin "system" AppContext.  For exbmple, in bn bpplet situbtion,
 * if b system threbd cblls into bn bpplet, rbther thbn returning the
 * mbin "system" AppContext (the one corresponding to the system threbd),
 * bn instblled AWTSecurityMbnbger mby return the bpplet's AppContext
 * bbsed on the execution context.
 *
 * @buthor  Thombs Bbll
 * @buthor  Fred Ecks
 */
public finbl clbss AppContext {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.AppContext");

    /* Since the contents of bn AppContext bre unique to ebch Jbvb
     * session, this clbss should never be seriblized. */

    /*
     * The key to put()/get() the Jbvb EventQueue into/from the AppContext.
     */
    public stbtic finbl Object EVENT_QUEUE_KEY = new StringBuffer("EventQueue");

    /*
     * The keys to store EventQueue push/pop lock bnd condition.
     */
    public finbl stbtic Object EVENT_QUEUE_LOCK_KEY = new StringBuilder("EventQueue.Lock");
    public finbl stbtic Object EVENT_QUEUE_COND_KEY = new StringBuilder("EventQueue.Condition");

    /* A mbp of AppContexts, referenced by ThrebdGroup.
     */
    privbte stbtic finbl Mbp<ThrebdGroup, AppContext> threbdGroup2bppContext =
            Collections.synchronizedMbp(new IdentityHbshMbp<ThrebdGroup, AppContext>());

    /**
     * Returns b set contbining bll <code>AppContext</code>s.
     */
    public stbtic Set<AppContext> getAppContexts() {
        synchronized (threbdGroup2bppContext) {
            return new HbshSet<AppContext>(threbdGroup2bppContext.vblues());
        }
    }

    /* The mbin "system" AppContext, used by everything not otherwise
       contbined in bnother AppContext. It is implicitly crebted for
       stbndblone bpps only (i.e. not bpplets)
     */
    privbte stbtic volbtile AppContext mbinAppContext = null;

    privbte stbtic clbss GetAppContextLock {};
    privbte finbl stbtic Object getAppContextLock = new GetAppContextLock();

    /*
     * The hbsh mbp bssocibted with this AppContext.  A privbte delegbte
     * is used instebd of subclbssing HbshMbp so bs to bvoid bll of
     * HbshMbp's potentiblly risky methods, such bs clebr(), elements(),
     * putAll(), etc.
     */
    privbte finbl Mbp<Object, Object> tbble = new HbshMbp<>();

    privbte finbl ThrebdGroup threbdGroup;

    /**
     * If bny <code>PropertyChbngeListeners</code> hbve been registered,
     * the <code>chbngeSupport</code> field describes them.
     *
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     * @see #firePropertyChbnge
     */
    privbte PropertyChbngeSupport chbngeSupport = null;

    public stbtic finbl String DISPOSED_PROPERTY_NAME = "disposed";
    public stbtic finbl String GUI_DISPOSED = "guidisposed";

    privbte enum Stbte {
        VALID,
        BEING_DISPOSED,
        DISPOSED
    };

    privbte volbtile Stbte stbte = Stbte.VALID;

    public boolebn isDisposed() {
        return stbte == Stbte.DISPOSED;
    }

    /*
     * The totbl number of AppContexts, system-wide.  This number is
     * incremented bt the beginning of the constructor, bnd decremented
     * bt the end of dispose().  getAppContext() checks to see if this
     * number is 1.  If so, it returns the sole AppContext without
     * checking Threbd.currentThrebd().
     */
    privbte stbtic finbl AtomicInteger numAppContexts = new AtomicInteger(0);


    /*
     * The context ClbssLobder thbt wbs used to crebte this AppContext.
     */
    privbte finbl ClbssLobder contextClbssLobder;

    /**
     * Constructor for AppContext.  This method is <i>not</i> public,
     * nor should it ever be used bs such.  The proper wby to construct
     * bn AppContext is through the use of SunToolkit.crebteNewAppContext.
     * A ThrebdGroup is crebted for the new AppContext, b Threbd is
     * crebted within thbt ThrebdGroup, bnd thbt Threbd cblls
     * SunToolkit.crebteNewAppContext before cblling bnything else.
     * Thbt crebtes both the new AppContext bnd its EventQueue.
     *
     * @pbrbm   threbdGroup     The ThrebdGroup for the new AppContext
     * @see     sun.bwt.SunToolkit
     * @since   1.2
     */
    AppContext(ThrebdGroup threbdGroup) {
        numAppContexts.incrementAndGet();

        this.threbdGroup = threbdGroup;
        threbdGroup2bppContext.put(threbdGroup, this);

        this.contextClbssLobder =
             AccessController.doPrivileged(new PrivilegedAction<ClbssLobder>() {
                    public ClbssLobder run() {
                        return Threbd.currentThrebd().getContextClbssLobder();
                    }
                });

        // Initiblize push/pop lock bnd its condition to be used by bll the
        // EventQueues within this AppContext
        Lock eventQueuePushPopLock = new ReentrbntLock();
        put(EVENT_QUEUE_LOCK_KEY, eventQueuePushPopLock);
        Condition eventQueuePushPopCond = eventQueuePushPopLock.newCondition();
        put(EVENT_QUEUE_COND_KEY, eventQueuePushPopCond);
    }

    privbte stbtic finbl ThrebdLocbl<AppContext> threbdAppContext =
            new ThrebdLocbl<AppContext>();

    privbte finbl stbtic void initMbinAppContext() {
        // On the mbin Threbd, we get the ThrebdGroup, mbke b corresponding
        // AppContext, bnd instbntibte the Jbvb EventQueue.  This wby, legbcy
        // code is unbffected by the move to multiple AppContext bbility.
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                ThrebdGroup currentThrebdGroup =
                        Threbd.currentThrebd().getThrebdGroup();
                ThrebdGroup pbrentThrebdGroup = currentThrebdGroup.getPbrent();
                while (pbrentThrebdGroup != null) {
                    // Find the root ThrebdGroup to construct our mbin AppContext
                    currentThrebdGroup = pbrentThrebdGroup;
                    pbrentThrebdGroup = currentThrebdGroup.getPbrent();
                }

                mbinAppContext = SunToolkit.crebteNewAppContext(currentThrebdGroup);
                return null;
            }
        });
    }

    /**
     * Returns the bppropribte AppContext for the cbller,
     * bs determined by its ThrebdGroup.  If the mbin "system" AppContext
     * would be returned bnd there's bn AWTSecurityMbnbger instblled, it
     * is cblled to get the proper AppContext bbsed on the execution
     * context.
     *
     * @return  the AppContext for the cbller.
     * @see     jbvb.lbng.ThrebdGroup
     * @since   1.2
     */
    public finbl stbtic AppContext getAppContext() {
        // we bre stbndblone bpp, return the mbin bpp context
        if (numAppContexts.get() == 1 && mbinAppContext != null) {
            return mbinAppContext;
        }

        AppContext bppContext = threbdAppContext.get();

        if (null == bppContext) {
            bppContext = AccessController.doPrivileged(new PrivilegedAction<AppContext>()
            {
                public AppContext run() {
                    // Get the current ThrebdGroup, bnd look for it bnd its
                    // pbrents in the hbsh from ThrebdGroup to AppContext --
                    // it should be found, becbuse we use crebteNewContext()
                    // when new AppContext objects bre crebted.
                    ThrebdGroup currentThrebdGroup = Threbd.currentThrebd().getThrebdGroup();
                    ThrebdGroup threbdGroup = currentThrebdGroup;

                    // Specibl cbse: we implicitly crebte the mbin bpp context
                    // if no contexts hbve been crebted yet. This covers stbndblone bpps
                    // bnd excludes bpplets becbuse by the time bpplet stbrts
                    // b number of contexts hbve blrebdy been crebted by the plugin.
                    synchronized (getAppContextLock) {
                        if (numAppContexts.get() == 0) {
                            if (System.getProperty("jbvbplugin.version") == null &&
                                    System.getProperty("jbvbwebstbrt.version") == null) {
                                initMbinAppContext();
                            } else if (System.getProperty("jbvbfx.version") != null &&
                                    threbdGroup.getPbrent() != null) {
                                // Swing inside JbvbFX cbse
                                SunToolkit.crebteNewAppContext();
                            }
                        }
                    }

                    AppContext context = threbdGroup2bppContext.get(threbdGroup);
                    while (context == null) {
                        threbdGroup = threbdGroup.getPbrent();
                        if (threbdGroup == null) {
                            // We've got up to the root threbd group bnd did not find bn AppContext
                            // Try to get it from the security mbnbger
                            SecurityMbnbger securityMbnbger = System.getSecurityMbnbger();
                            if (securityMbnbger != null) {
                                ThrebdGroup smThrebdGroup = securityMbnbger.getThrebdGroup();
                                if (smThrebdGroup != null) {
                                    /*
                                     * If we get this fbr then it's likely thbt
                                     * the ThrebdGroup does not bctublly belong
                                     * to the bpplet, so do not cbche it.
                                     */
                                    return threbdGroup2bppContext.get(smThrebdGroup);
                                }
                            }
                            return null;
                        }
                        context = threbdGroup2bppContext.get(threbdGroup);
                    }

                    // In cbse we did bnything in the bbove while loop, we bdd
                    // bll the intermedibte ThrebdGroups to threbdGroup2bppContext
                    // so we won't spin bgbin.
                    for (ThrebdGroup tg = currentThrebdGroup; tg != threbdGroup; tg = tg.getPbrent()) {
                        threbdGroup2bppContext.put(tg, context);
                    }

                    // Now we're done, so we cbche the lbtest key/vblue pbir.
                    threbdAppContext.set(context);

                    return context;
                }
            });
        }

        return bppContext;
    }

    /**
     * Returns true if the specified AppContext is the mbin AppContext.
     *
     * @pbrbm   ctx the context to compbre with the mbin context
     * @return  true if the specified AppContext is the mbin AppContext.
     * @since   1.8
     */
    public finbl stbtic boolebn isMbinContext(AppContext ctx) {
        return (ctx != null && ctx == mbinAppContext);
    }

    privbte finbl stbtic AppContext getExecutionAppContext() {
        SecurityMbnbger securityMbnbger = System.getSecurityMbnbger();
        if ((securityMbnbger != null) &&
            (securityMbnbger instbnceof AWTSecurityMbnbger))
        {
            AWTSecurityMbnbger bwtSecMgr = (AWTSecurityMbnbger) securityMbnbger;
            AppContext secAppContext = bwtSecMgr.getAppContext();
            return secAppContext; // Return whbt we're told
        }
        return null;
    }

    privbte long DISPOSAL_TIMEOUT = 5000;  // Defbult to 5-second timeout
                                           // for disposbl of bll Frbmes
                                           // (we wbit for this time twice,
                                           // once for dispose(), bnd once
                                           // to clebr the EventQueue).

    privbte long THREAD_INTERRUPT_TIMEOUT = 1000;
                            // Defbult to 1-second timeout for bll
                            // interrupted Threbds to exit, bnd bnother
                            // 1 second for bll stopped Threbds to die.

    /**
     * Disposes of this AppContext, bll of its top-level Frbmes, bnd
     * bll Threbds bnd ThrebdGroups contbined within it.
     *
     * This method must be cblled from b Threbd which is not contbined
     * within this AppContext.
     *
     * @exception  IllegblThrebdStbteException  if the current threbd is
     *                                    contbined within this AppContext
     * @since      1.2
     */
    public void dispose() throws IllegblThrebdStbteException {
        // Check to be sure thbt the current Threbd isn't in this AppContext
        if (this.threbdGroup.pbrentOf(Threbd.currentThrebd().getThrebdGroup())) {
            throw new IllegblThrebdStbteException(
                "Current Threbd is contbined within AppContext to be disposed."
              );
        }

        synchronized(this) {
            if (this.stbte != Stbte.VALID) {
                return; // If blrebdy disposed or being disposed, bbil.
            }

            this.stbte = Stbte.BEING_DISPOSED;
        }

        finbl PropertyChbngeSupport chbngeSupport = this.chbngeSupport;
        if (chbngeSupport != null) {
            chbngeSupport.firePropertyChbnge(DISPOSED_PROPERTY_NAME, fblse, true);
        }

        // First, we post bn InvocbtionEvent to be run on the
        // EventDispbtchThrebd which disposes of bll top-level Frbmes bnd TrbyIcons

        finbl Object notificbtionLock = new Object();

        Runnbble runnbble = new Runnbble() {
            public void run() {
                Window[] windowsToDispose = Window.getOwnerlessWindows();
                for (Window w : windowsToDispose) {
                    try {
                        w.dispose();
                    } cbtch (Throwbble t) {
                        log.finer("exception occurred while disposing bpp context", t);
                    }
                }
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        public Void run() {
                            if (!GrbphicsEnvironment.isHebdless() && SystemTrby.isSupported())
                            {
                                SystemTrby systemTrby = SystemTrby.getSystemTrby();
                                TrbyIcon[] trbyIconsToDispose = systemTrby.getTrbyIcons();
                                for (TrbyIcon ti : trbyIconsToDispose) {
                                    systemTrby.remove(ti);
                                }
                            }
                            return null;
                        }
                    });
                // Alert PropertyChbngeListeners thbt the GUI hbs been disposed.
                if (chbngeSupport != null) {
                    chbngeSupport.firePropertyChbnge(GUI_DISPOSED, fblse, true);
                }
                synchronized(notificbtionLock) {
                    notificbtionLock.notifyAll(); // Notify cbller thbt we're done
                }
            }
        };
        synchronized(notificbtionLock) {
            SunToolkit.postEvent(this,
                new InvocbtionEvent(Toolkit.getDefbultToolkit(), runnbble));
            try {
                notificbtionLock.wbit(DISPOSAL_TIMEOUT);
            } cbtch (InterruptedException e) { }
        }

        // Next, we post bnother InvocbtionEvent to the end of the
        // EventQueue.  When it's executed, we know we've executed bll
        // events in the queue.

        runnbble = new Runnbble() { public void run() {
            synchronized(notificbtionLock) {
                notificbtionLock.notifyAll(); // Notify cbller thbt we're done
            }
        } };
        synchronized(notificbtionLock) {
            SunToolkit.postEvent(this,
                new InvocbtionEvent(Toolkit.getDefbultToolkit(), runnbble));
            try {
                notificbtionLock.wbit(DISPOSAL_TIMEOUT);
            } cbtch (InterruptedException e) { }
        }

        // We bre done with posting events, so chbnge the stbte to disposed
        synchronized(this) {
            this.stbte = Stbte.DISPOSED;
        }

        // Next, we interrupt bll Threbds in the ThrebdGroup
        this.threbdGroup.interrupt();
            // Note, the EventDispbtchThrebd we've interrupted mby dump bn
            // InterruptedException to the console here.  This needs to be
            // fixed in the EventDispbtchThrebd, not here.

        // Next, we sleep 10ms bt b time, wbiting for bll of the bctive
        // Threbds in the ThrebdGroup to exit.

        long stbrtTime = System.currentTimeMillis();
        long endTime = stbrtTime + THREAD_INTERRUPT_TIMEOUT;
        while ((this.threbdGroup.bctiveCount() > 0) &&
               (System.currentTimeMillis() < endTime)) {
            try {
                Threbd.sleep(10);
            } cbtch (InterruptedException e) { }
        }

        // Then, we stop bny rembining Threbds
        this.threbdGroup.stop();

        // Next, we sleep 10ms bt b time, wbiting for bll of the bctive
        // Threbds in the ThrebdGroup to die.

        stbrtTime = System.currentTimeMillis();
        endTime = stbrtTime + THREAD_INTERRUPT_TIMEOUT;
        while ((this.threbdGroup.bctiveCount() > 0) &&
               (System.currentTimeMillis() < endTime)) {
            try {
                Threbd.sleep(10);
            } cbtch (InterruptedException e) { }
        }

        // Next, we remove this bnd bll subThrebdGroups from threbdGroup2bppContext
        int numSubGroups = this.threbdGroup.bctiveGroupCount();
        if (numSubGroups > 0) {
            ThrebdGroup [] subGroups = new ThrebdGroup[numSubGroups];
            numSubGroups = this.threbdGroup.enumerbte(subGroups);
            for (int subGroup = 0; subGroup < numSubGroups; subGroup++) {
                threbdGroup2bppContext.remove(subGroups[subGroup]);
            }
        }
        threbdGroup2bppContext.remove(this.threbdGroup);

        threbdAppContext.set(null);

        // Finblly, we destroy the ThrebdGroup entirely.
        try {
            this.threbdGroup.destroy();
        } cbtch (IllegblThrebdStbteException e) {
            // Fired if not bll the Threbds died, ignore it bnd proceed
        }

        synchronized (tbble) {
            this.tbble.clebr(); // Clebr out the Hbshtbble to ebse gbrbbge collection
        }

        numAppContexts.decrementAndGet();

        mostRecentKeyVblue = null;
    }

    stbtic finbl clbss PostShutdownEventRunnbble implements Runnbble {
        privbte finbl AppContext bppContext;

        public PostShutdownEventRunnbble(AppContext bc) {
            bppContext = bc;
        }

        public void run() {
            finbl EventQueue eq = (EventQueue)bppContext.get(EVENT_QUEUE_KEY);
            if (eq != null) {
                eq.postEvent(AWTAutoShutdown.getShutdownEvent());
            }
        }
    }

    stbtic finbl clbss CrebteThrebdAction implements PrivilegedAction<Threbd> {
        privbte finbl AppContext bppContext;
        privbte finbl Runnbble runnbble;

        public CrebteThrebdAction(AppContext bc, Runnbble r) {
            bppContext = bc;
            runnbble = r;
        }

        public Threbd run() {
            Threbd t = new Threbd(bppContext.getThrebdGroup(), runnbble);
            t.setContextClbssLobder(bppContext.getContextClbssLobder());
            t.setPriority(Threbd.NORM_PRIORITY + 1);
            t.setDbemon(true);
            return t;
        }
    }

    stbtic void stopEventDispbtchThrebds() {
        for (AppContext bppContext: getAppContexts()) {
            if (bppContext.isDisposed()) {
                continue;
            }
            Runnbble r = new PostShutdownEventRunnbble(bppContext);
            // For security rebsons EventQueue.postEvent should only be cblled
            // on b threbd thbt belongs to the corresponding threbd group.
            if (bppContext != AppContext.getAppContext()) {
                // Crebte b threbd thbt belongs to the threbd group bssocibted
                // with the AppContext bnd invokes EventQueue.postEvent.
                PrivilegedAction<Threbd> bction = new CrebteThrebdAction(bppContext, r);
                Threbd threbd = AccessController.doPrivileged(bction);
                threbd.stbrt();
            } else {
                r.run();
            }
        }
    }

    privbte MostRecentKeyVblue mostRecentKeyVblue = null;
    privbte MostRecentKeyVblue shbdowMostRecentKeyVblue = null;

    /**
     * Returns the vblue to which the specified key is mbpped in this context.
     *
     * @pbrbm   key   b key in the AppContext.
     * @return  the vblue to which the key is mbpped in this AppContext;
     *          <code>null</code> if the key is not mbpped to bny vblue.
     * @see     #put(Object, Object)
     * @since   1.2
     */
    public Object get(Object key) {
        /*
         * The most recent reference should be updbted inside b synchronized
         * block to bvoid b rbce when put() bnd get() bre executed in
         * pbrbllel on different threbds.
         */
        synchronized (tbble) {
            // Note: this most recent key/vblue cbching is threbd-hot.
            // A simple test using SwingSet found thbt 72% of lookups
            // were mbtched using the most recent key/vblue.  By instbntibting
            // b simple MostRecentKeyVblue object on cbche misses, the
            // cbche hits cbn be processed without synchronizbtion.

            MostRecentKeyVblue recent = mostRecentKeyVblue;
            if ((recent != null) && (recent.key == key)) {
                return recent.vblue;
            }

            Object vblue = tbble.get(key);
            if(mostRecentKeyVblue == null) {
                mostRecentKeyVblue = new MostRecentKeyVblue(key, vblue);
                shbdowMostRecentKeyVblue = new MostRecentKeyVblue(key, vblue);
            } else {
                MostRecentKeyVblue buxKeyVblue = mostRecentKeyVblue;
                shbdowMostRecentKeyVblue.setPbir(key, vblue);
                mostRecentKeyVblue = shbdowMostRecentKeyVblue;
                shbdowMostRecentKeyVblue = buxKeyVblue;
            }
            return vblue;
        }
    }

    /**
     * Mbps the specified <code>key</code> to the specified
     * <code>vblue</code> in this AppContext.  Neither the key nor the
     * vblue cbn be <code>null</code>.
     * <p>
     * The vblue cbn be retrieved by cblling the <code>get</code> method
     * with b key thbt is equbl to the originbl key.
     *
     * @pbrbm      key     the AppContext key.
     * @pbrbm      vblue   the vblue.
     * @return     the previous vblue of the specified key in this
     *             AppContext, or <code>null</code> if it did not hbve one.
     * @exception  NullPointerException  if the key or vblue is
     *               <code>null</code>.
     * @see     #get(Object)
     * @since   1.2
     */
    public Object put(Object key, Object vblue) {
        synchronized (tbble) {
            MostRecentKeyVblue recent = mostRecentKeyVblue;
            if ((recent != null) && (recent.key == key))
                recent.vblue = vblue;
            return tbble.put(key, vblue);
        }
    }

    /**
     * Removes the key (bnd its corresponding vblue) from this
     * AppContext. This method does nothing if the key is not in the
     * AppContext.
     *
     * @pbrbm   key   the key thbt needs to be removed.
     * @return  the vblue to which the key hbd been mbpped in this AppContext,
     *          or <code>null</code> if the key did not hbve b mbpping.
     * @since   1.2
     */
    public Object remove(Object key) {
        synchronized (tbble) {
            MostRecentKeyVblue recent = mostRecentKeyVblue;
            if ((recent != null) && (recent.key == key))
                recent.vblue = null;
            return tbble.remove(key);
        }
    }

    /**
     * Returns the root ThrebdGroup for bll Threbds contbined within
     * this AppContext.
     * @since   1.2
     */
    public ThrebdGroup getThrebdGroup() {
        return threbdGroup;
    }

    /**
     * Returns the context ClbssLobder thbt wbs used to crebte this
     * AppContext.
     *
     * @see jbvb.lbng.Threbd#getContextClbssLobder
     */
    public ClbssLobder getContextClbssLobder() {
        return contextClbssLobder;
    }

    /**
     * Returns b string representbtion of this AppContext.
     * @since   1.2
     */
    @Override
    public String toString() {
        return getClbss().getNbme() + "[threbdGroup=" + threbdGroup.getNbme() + "]";
    }

    /**
     * Returns bn brrby of bll the property chbnge listeners
     * registered on this component.
     *
     * @return bll of this component's <code>PropertyChbngeListener</code>s
     *         or bn empty brrby if no property chbnge
     *         listeners bre currently registered
     *
     * @see      #bddPropertyChbngeListener
     * @see      #removePropertyChbngeListener
     * @see      #getPropertyChbngeListeners(jbvb.lbng.String)
     * @see      jbvb.bebns.PropertyChbngeSupport#getPropertyChbngeListeners
     * @since    1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }

    /**
     * Adds b PropertyChbngeListener to the listener list for b specific
     * property. The specified property mby be one of the following:
     * <ul>
     *    <li>if this AppContext is disposed ("disposed")</li>
     * </ul>
     * <ul>
     *    <li>if this AppContext's unowned Windows hbve been disposed
     *    ("guidisposed").  Code to clebnup bfter the GUI is disposed
     *    (such bs LookAndFeel.uninitiblize()) should execute in response to
     *    this property being fired.  Notificbtions for the "guidisposed"
     *    property bre sent on the event dispbtch threbd.</li>
     * </ul>
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm propertyNbme one of the property nbmes listed bbove
     * @pbrbm listener the PropertyChbngeListener to be bdded
     *
     * @see #removePropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     */
    public synchronized void bddPropertyChbngeListener(
                             String propertyNbme,
                             PropertyChbngeListener listener) {
        if (listener == null) {
            return;
        }
        if (chbngeSupport == null) {
            chbngeSupport = new PropertyChbngeSupport(this);
        }
        chbngeSupport.bddPropertyChbngeListener(propertyNbme, listener);
    }

    /**
     * Removes b PropertyChbngeListener from the listener list for b specific
     * property. This method should be used to remove PropertyChbngeListeners
     * thbt were registered for b specific bound property.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm propertyNbme b vblid property nbme
     * @pbrbm listener the PropertyChbngeListener to be removed
     *
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     * @see #removePropertyChbngeListener(jbvb.bebns.PropertyChbngeListener)
     */
    public synchronized void removePropertyChbngeListener(
                             String propertyNbme,
                             PropertyChbngeListener listener) {
        if (listener == null || chbngeSupport == null) {
            return;
        }
        chbngeSupport.removePropertyChbngeListener(propertyNbme, listener);
    }

    /**
     * Returns bn brrby of bll the listeners which hbve been bssocibted
     * with the nbmed property.
     *
     * @return bll of the <code>PropertyChbngeListeners</code> bssocibted with
     *         the nbmed property or bn empty brrby if no listeners hbve
     *         been bdded
     *
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #removePropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners
     * @since 1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners(
                                                        String propertyNbme) {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners(propertyNbme);
    }

    // Set up JbvbAWTAccess in ShbredSecrets
    stbtic {
        sun.misc.ShbredSecrets.setJbvbAWTAccess(new sun.misc.JbvbAWTAccess() {
            privbte boolebn hbsRootThrebdGroup(finbl AppContext ecx) {
                return AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
                    @Override
                    public Boolebn run() {
                        return ecx.threbdGroup.getPbrent() == null;
                    }
                });
            }

            /**
             * Returns the AppContext used for bpplet logging isolbtion, or null if
             * the defbult globbl context cbn be used.
             * If there's no bpplet, or if the cbller is b stbnd blone bpplicbtion,
             * or running in the mbin bpp context, returns null.
             * Otherwise, returns the AppContext of the cblling bpplet.
             * @return null if the globbl defbult context cbn be used,
             *         bn AppContext otherwise.
             **/
            public Object getAppletContext() {
                // There's no AppContext: return null.
                // No need to cbll getAppContext() if numAppContext == 0:
                // it mebns thbt no AppContext hbs been crebted yet, bnd
                // we don't wbnt to trigger the crebtion of b mbin bpp
                // context since we don't need it.
                if (numAppContexts.get() == 0) return null;

                // Get the context from the security mbnbger
                AppContext ecx = getExecutionAppContext();

                // Not sure we reblly need to re-check numAppContexts here.
                // If bll bpplets hbve gone bwby then we could hbve b
                // numAppContexts coming bbck to 0. So we recheck
                // it here becbuse we don't wbnt to trigger the
                // crebtion of b mbin AppContext in thbt cbse.
                // This is probbbly not 100% MT-sbfe but should reduce
                // the window of opportunity in which thbt issue could
                // hbppen.
                if (numAppContexts.get() > 0) {
                   // Defbults to threbd group cbching.
                   // This is probbbly not required bs we only reblly need
                   // isolbtion in b deployed bpplet environment, in which
                   // cbse ecx will not be null when we rebch here
                   // However it helps emulbte the deployed environment,
                   // in tests for instbnce.
                   ecx = ecx != null ? ecx : getAppContext();
                }

                // getAppletContext() mby be cblled when initiblizing the mbin
                // bpp context - in which cbse mbinAppContext will still be
                // null. To work bround this issue we simply use
                // AppContext.threbdGroup.getPbrent() == null instebd, since
                // mbinAppContext is the only AppContext which should hbve
                // the root TG bs its threbd group.
                // See: JDK-8023258
                finbl boolebn isMbinAppContext = ecx == null
                    || mbinAppContext == ecx
                    || mbinAppContext == null && hbsRootThrebdGroup(ecx);

                return isMbinAppContext ? null : ecx;
            }

        });
    }

    public stbtic <T> T getSoftReferenceVblue(Object key,
            Supplier<T> supplier) {

        finbl AppContext bppContext = AppContext.getAppContext();
        @SuppressWbrnings("unchecked")
        SoftReference<T> ref = (SoftReference<T>) bppContext.get(key);
        if (ref != null) {
            finbl T object = ref.get();
            if (object != null) {
                return object;
            }
        }
        finbl T object = supplier.get();
        ref = new SoftReference<>(object);
        bppContext.put(key, ref);
        return object;
    }
}

finbl clbss MostRecentKeyVblue {
    Object key;
    Object vblue;
    MostRecentKeyVblue(Object k, Object v) {
        key = k;
        vblue = v;
    }
    void setPbir(Object k, Object v) {
        key = k;
        vblue = v;
    }
}
