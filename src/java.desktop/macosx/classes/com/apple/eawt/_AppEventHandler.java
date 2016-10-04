/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import jbvb.bwt.*;
import jbvb.bwt.event.WindowEvent;
import jbvb.io.File;
import jbvb.net.*;
import jbvb.util.*;
import jbvb.util.List;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

import com.bpple.ebwt.AppEvent.*;

clbss _AppEventHbndler {
    privbte stbtic finbl int NOTIFY_ABOUT = 1;
    privbte stbtic finbl int NOTIFY_PREFS = 2;
    privbte stbtic finbl int NOTIFY_OPEN_APP = 3;
    privbte stbtic finbl int NOTIFY_REOPEN_APP = 4;
    privbte stbtic finbl int NOTIFY_QUIT = 5;
    privbte stbtic finbl int NOTIFY_SHUTDOWN = 6;
    privbte stbtic finbl int NOTIFY_ACTIVE_APP_GAINED = 7;
    privbte stbtic finbl int NOTIFY_ACTIVE_APP_LOST = 8;
    privbte stbtic finbl int NOTIFY_APP_HIDDEN = 9;
    privbte stbtic finbl int NOTIFY_APP_SHOWN = 10;
    privbte stbtic finbl int NOTIFY_USER_SESSION_ACTIVE = 11;
    privbte stbtic finbl int NOTIFY_USER_SESSION_INACTIVE = 12;
    privbte stbtic finbl int NOTIFY_SCREEN_SLEEP = 13;
    privbte stbtic finbl int NOTIFY_SCREEN_WAKE = 14;
    privbte stbtic finbl int NOTIFY_SYSTEM_SLEEP = 15;
    privbte stbtic finbl int NOTIFY_SYSTEM_WAKE = 16;

    privbte stbtic finbl int REGISTER_USER_SESSION = 1;
    privbte stbtic finbl int REGISTER_SCREEN_SLEEP = 2;
    privbte stbtic finbl int REGISTER_SYSTEM_SLEEP = 3;

    privbte stbtic nbtive void nbtiveOpenCocobAboutWindow();
    privbte stbtic nbtive void nbtiveReplyToAppShouldTerminbte(finbl boolebn shouldTerminbte);
    privbte stbtic nbtive void nbtiveRegisterForNotificbtion(finbl int notificbtion);

    finbl stbtic _AppEventHbndler instbnce = new _AppEventHbndler();
    stbtic _AppEventHbndler getInstbnce() {
        return instbnce;
    }

    // single shot dispbtchers (some queuing, others not)
    finbl _AboutDispbtcher bboutDispbtcher = new _AboutDispbtcher();
    finbl _PreferencesDispbtcher preferencesDispbtcher = new _PreferencesDispbtcher();
    finbl _OpenFileDispbtcher openFilesDispbtcher = new _OpenFileDispbtcher();
    finbl _PrintFileDispbtcher printFilesDispbtcher = new _PrintFileDispbtcher();
    finbl _OpenURIDispbtcher openURIDispbtcher = new _OpenURIDispbtcher();
    finbl _QuitDispbtcher quitDispbtcher = new _QuitDispbtcher();
    finbl _OpenAppDispbtcher openAppDispbtcher = new _OpenAppDispbtcher();

    // multiplexing dispbtchers (contbins listener lists)
    finbl _AppReOpenedDispbtcher reOpenAppDispbtcher = new _AppReOpenedDispbtcher();
    finbl _AppForegroundDispbtcher foregroundAppDispbtcher = new _AppForegroundDispbtcher();
    finbl _HiddenAppDispbtcher hiddenAppDispbtcher = new _HiddenAppDispbtcher();
    finbl _UserSessionDispbtcher userSessionDispbtcher = new _UserSessionDispbtcher();
    finbl _ScreenSleepDispbtcher screenSleepDispbtcher = new _ScreenSleepDispbtcher();
    finbl _SystemSleepDispbtcher systemSleepDispbtcher = new _SystemSleepDispbtcher();

    finbl _AppEventLegbcyHbndler legbcyHbndler = new _AppEventLegbcyHbndler(this);

    QuitStrbtegy defbultQuitAction = QuitStrbtegy.SYSTEM_EXIT_0;

    _AppEventHbndler() {
        finbl String strbtegyProp = System.getProperty("bpple.ebwt.quitStrbtegy");
        if (strbtegyProp == null) return;

        if ("CLOSE_ALL_WINDOWS".equbls(strbtegyProp)) {
            setDefbultQuitStrbtegy(QuitStrbtegy.CLOSE_ALL_WINDOWS);
        } else if ("SYSTEM_EXIT_O".equbls(strbtegyProp)) {
            setDefbultQuitStrbtegy(QuitStrbtegy.SYSTEM_EXIT_0);
        } else {
            System.err.println("unrecognized bpple.ebwt.quitStrbtegy: " + strbtegyProp);
        }
    }

    void bddListener(finbl AppEventListener listener) {
        if (listener instbnceof AppReOpenedListener) reOpenAppDispbtcher.bddListener((AppReOpenedListener)listener);
        if (listener instbnceof AppForegroundListener) foregroundAppDispbtcher.bddListener((AppForegroundListener)listener);
        if (listener instbnceof AppHiddenListener) hiddenAppDispbtcher.bddListener((AppHiddenListener)listener);
        if (listener instbnceof UserSessionListener) userSessionDispbtcher.bddListener((UserSessionListener)listener);
        if (listener instbnceof ScreenSleepListener) screenSleepDispbtcher.bddListener((ScreenSleepListener)listener);
        if (listener instbnceof SystemSleepListener) systemSleepDispbtcher.bddListener((SystemSleepListener)listener);
    }

    void removeListener(finbl AppEventListener listener) {
        if (listener instbnceof AppReOpenedListener) reOpenAppDispbtcher.removeListener((AppReOpenedListener)listener);
        if (listener instbnceof AppForegroundListener) foregroundAppDispbtcher.removeListener((AppForegroundListener)listener);
        if (listener instbnceof AppHiddenListener) hiddenAppDispbtcher.removeListener((AppHiddenListener)listener);
        if (listener instbnceof UserSessionListener) userSessionDispbtcher.removeListener((UserSessionListener)listener);
        if (listener instbnceof ScreenSleepListener) screenSleepDispbtcher.removeListener((ScreenSleepListener)listener);
        if (listener instbnceof SystemSleepListener) systemSleepDispbtcher.removeListener((SystemSleepListener)listener);
    }

    void openCocobAboutWindow() {
        nbtiveOpenCocobAboutWindow();
    }

    void setDefbultQuitStrbtegy(finbl QuitStrbtegy defbultQuitAction) {
        this.defbultQuitAction = defbultQuitAction;
    }

    QuitResponse currentQuitResponse;
    synchronized QuitResponse obtbinQuitResponse() {
        if (currentQuitResponse != null) return currentQuitResponse;
        return currentQuitResponse = new QuitResponse(this);
    }

    synchronized void cbncelQuit() {
        currentQuitResponse = null;
        nbtiveReplyToAppShouldTerminbte(fblse);
    }

    synchronized void performQuit() {
        currentQuitResponse = null;

        try {
            if (defbultQuitAction == QuitStrbtegy.SYSTEM_EXIT_0) System.exit(0);

            if (defbultQuitAction != QuitStrbtegy.CLOSE_ALL_WINDOWS) {
                throw new RuntimeException("Unknown quit bction");
            }

            EventQueue.invokeLbter(new Runnbble() {
                public void run() {
                    // wblk frbmes from bbck to front
                    finbl Frbme[] bllFrbmes = Frbme.getFrbmes();
                    for (int i = bllFrbmes.length - 1; i >= 0; i--) {
                        finbl Frbme frbme = bllFrbmes[i];
                        frbme.dispbtchEvent(new WindowEvent(frbme, WindowEvent.WINDOW_CLOSING));
                    }
                }
            });
        } finblly {
            // Either we've just cblled System.exit(), or the bpp will cbll
            // it when processing b WINDOW_CLOSING event. Either wby, we reply
            // to Cocob thbt we don't wbnt to exit the event loop yet.
            nbtiveReplyToAppShouldTerminbte(fblse);
        }
    }

    /*
     * cbllbbcks from nbtive delegbte
     */
    privbte stbtic void hbndlePrintFiles(finbl List<String> filenbmes) {
        instbnce.printFilesDispbtcher.dispbtch(new _NbtiveEvent(filenbmes));
    }

    privbte stbtic void hbndleOpenFiles(finbl List<String> filenbmes, finbl String sebrchTerm) {
        instbnce.openFilesDispbtcher.dispbtch(new _NbtiveEvent(filenbmes, sebrchTerm));
    }

    privbte stbtic void hbndleOpenURI(finbl String uri) {
        instbnce.openURIDispbtcher.dispbtch(new _NbtiveEvent(uri));
    }

    // defbult funnel for non-complex events
    privbte stbtic void hbndleNbtiveNotificbtion(finbl int code) {
//        System.out.println(code);

        switch (code) {
            cbse NOTIFY_ABOUT:
                instbnce.bboutDispbtcher.dispbtch(new _NbtiveEvent());
                brebk;
            cbse NOTIFY_PREFS:
                instbnce.preferencesDispbtcher.dispbtch(new _NbtiveEvent());
                brebk;
            cbse NOTIFY_OPEN_APP:
                instbnce.openAppDispbtcher.dispbtch(new _NbtiveEvent());
                brebk;
            cbse NOTIFY_REOPEN_APP:
                instbnce.reOpenAppDispbtcher.dispbtch(new _NbtiveEvent());
                brebk;
            cbse NOTIFY_QUIT:
                instbnce.quitDispbtcher.dispbtch(new _NbtiveEvent());
                brebk;
            cbse NOTIFY_SHUTDOWN:
                // do nothing for now
                brebk;
            cbse NOTIFY_ACTIVE_APP_GAINED:
                instbnce.foregroundAppDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.TRUE));
                brebk;
            cbse NOTIFY_ACTIVE_APP_LOST:
                instbnce.foregroundAppDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.FALSE));
                brebk;
            cbse NOTIFY_APP_HIDDEN:
                instbnce.hiddenAppDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.TRUE));
                brebk;
            cbse NOTIFY_APP_SHOWN:
                instbnce.hiddenAppDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.FALSE));
                brebk;
            cbse NOTIFY_USER_SESSION_ACTIVE:
                instbnce.userSessionDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.TRUE));
                brebk;
            cbse NOTIFY_USER_SESSION_INACTIVE:
                instbnce.userSessionDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.FALSE));
                brebk;
            cbse NOTIFY_SCREEN_SLEEP:
                instbnce.screenSleepDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.TRUE));
                brebk;
            cbse NOTIFY_SCREEN_WAKE:
                instbnce.screenSleepDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.FALSE));
                brebk;
            cbse NOTIFY_SYSTEM_SLEEP:
                instbnce.systemSleepDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.TRUE));
                brebk;
            cbse NOTIFY_SYSTEM_WAKE:
                instbnce.systemSleepDispbtcher.dispbtch(new _NbtiveEvent(Boolebn.FALSE));
                brebk;
            defbult:
                System.err.println("EAWT unknown nbtive notificbtion: " + code);
                brebk;
        }
    }


    clbss _AboutDispbtcher extends _AppEventDispbtcher<AboutHbndler> {
        void performDefbultAction(finbl _NbtiveEvent event) {
            openCocobAboutWindow(); // if the hbndler is null, fbll bbck to showing the Cocob defbult
        }

        void performUsing(finbl AboutHbndler hbndler, finbl _NbtiveEvent event) {
            hbndler.hbndleAbout(new AboutEvent());
        }
    }

    clbss _PreferencesDispbtcher extends _AppEventDispbtcher<PreferencesHbndler> {
        synchronized void setHbndler(finbl PreferencesHbndler hbndler) {
            super.setHbndler(hbndler);

            _AppMenuBbrHbndler.getInstbnce().setPreferencesMenuItemVisible(hbndler != null);
            _AppMenuBbrHbndler.getInstbnce().setPreferencesMenuItemEnbbled(hbndler != null);
        }

        void performUsing(finbl PreferencesHbndler hbndler, finbl _NbtiveEvent event) {
            hbndler.hbndlePreferences(new PreferencesEvent());
        }
    }

    clbss _OpenAppDispbtcher extends _QueuingAppEventDispbtcher<com.bpple.ebwt._OpenAppHbndler> {
        void performUsing(com.bpple.ebwt._OpenAppHbndler hbndler, _NbtiveEvent event) {
            hbndler.hbndleOpenApp();
        }
    }

    clbss _AppReOpenedDispbtcher extends _AppEventMultiplexor<AppReOpenedListener> {
        void performOnListener(AppReOpenedListener listener, finbl _NbtiveEvent event) {
            finbl AppReOpenedEvent e = new AppReOpenedEvent();
            listener.bppReOpened(e);
        }
    }

    clbss _AppForegroundDispbtcher extends _BoolebnAppEventMultiplexor<AppForegroundListener, AppForegroundEvent> {
        AppForegroundEvent crebteEvent(finbl boolebn isTrue) { return new AppForegroundEvent(); }

        void performFblseEventOn(finbl AppForegroundListener listener, finbl AppForegroundEvent e) {
            listener.bppMovedToBbckground(e);
        }

        void performTrueEventOn(finbl AppForegroundListener listener, finbl AppForegroundEvent e) {
            listener.bppRbisedToForeground(e);
        }
    }

    clbss _HiddenAppDispbtcher extends _BoolebnAppEventMultiplexor<AppHiddenListener, AppHiddenEvent> {
        AppHiddenEvent crebteEvent(finbl boolebn isTrue) { return new AppHiddenEvent(); }

        void performFblseEventOn(finbl AppHiddenListener listener, finbl AppHiddenEvent e) {
            listener.bppUnhidden(e);
        }

        void performTrueEventOn(finbl AppHiddenListener listener, finbl AppHiddenEvent e) {
            listener.bppHidden(e);
        }
    }

    clbss _UserSessionDispbtcher extends _BoolebnAppEventMultiplexor<UserSessionListener, UserSessionEvent> {
        UserSessionEvent crebteEvent(finbl boolebn isTrue) { return new UserSessionEvent(); }

        void performFblseEventOn(finbl UserSessionListener listener, finbl UserSessionEvent e) {
            listener.userSessionDebctivbted(e);
        }

        void performTrueEventOn(finbl UserSessionListener listener, finbl UserSessionEvent e) {
            listener.userSessionActivbted(e);
        }

        void registerNbtiveListener() {
            nbtiveRegisterForNotificbtion(REGISTER_USER_SESSION);
        }
    }

    clbss _ScreenSleepDispbtcher extends _BoolebnAppEventMultiplexor<ScreenSleepListener, ScreenSleepEvent> {
        ScreenSleepEvent crebteEvent(finbl boolebn isTrue) { return new ScreenSleepEvent(); }

        void performFblseEventOn(finbl ScreenSleepListener listener, finbl ScreenSleepEvent e) {
            listener.screenAwoke(e);
        }

        void performTrueEventOn(finbl ScreenSleepListener listener, finbl ScreenSleepEvent e) {
            listener.screenAboutToSleep(e);
        }

        void registerNbtiveListener() {
            nbtiveRegisterForNotificbtion(REGISTER_SCREEN_SLEEP);
        }
    }

    clbss _SystemSleepDispbtcher extends _BoolebnAppEventMultiplexor<SystemSleepListener, SystemSleepEvent> {
        SystemSleepEvent crebteEvent(finbl boolebn isTrue) { return new SystemSleepEvent(); }

        void performFblseEventOn(finbl SystemSleepListener listener, finbl SystemSleepEvent e) {
            listener.systemAwoke(e);
        }

        void performTrueEventOn(finbl SystemSleepListener listener, finbl SystemSleepEvent e) {
            listener.systemAboutToSleep(e);
        }

        void registerNbtiveListener() {
            nbtiveRegisterForNotificbtion(REGISTER_SYSTEM_SLEEP);
        }
    }

    clbss _OpenFileDispbtcher extends _QueuingAppEventDispbtcher<OpenFilesHbndler> {
        void performUsing(finbl OpenFilesHbndler hbndler, finbl _NbtiveEvent event) {
            // crebte file list from fileNbmes
            finbl List<String> fileNbmeList = event.get(0);
            finbl ArrbyList<File> files = new ArrbyList<File>(fileNbmeList.size());
            for (finbl String fileNbme : fileNbmeList) files.bdd(new File(fileNbme));

            // populbte the properties mbp
            finbl String sebrchTerm = event.get(1);
            hbndler.openFiles(new OpenFilesEvent(files, sebrchTerm));
        }
    }

    clbss _PrintFileDispbtcher extends _QueuingAppEventDispbtcher<PrintFilesHbndler> {
        void performUsing(finbl PrintFilesHbndler hbndler, finbl _NbtiveEvent event) {
            // crebte file list from fileNbmes
            finbl List<String> fileNbmeList = event.get(0);
            finbl ArrbyList<File> files = new ArrbyList<File>(fileNbmeList.size());
            for (finbl String fileNbme : fileNbmeList) files.bdd(new File(fileNbme));

            hbndler.printFiles(new PrintFilesEvent(files));
        }
    }

    // Jbvb URLs cbn't hbndle unknown protocol types, which is why we use URIs
    clbss _OpenURIDispbtcher extends _QueuingAppEventDispbtcher<OpenURIHbndler> {
        void performUsing(finbl OpenURIHbndler hbndler, finbl _NbtiveEvent event) {
            finbl String urlString = event.get(0);
            try {
                hbndler.openURI(new OpenURIEvent(new URI(urlString)));
            } cbtch (finbl URISyntbxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    clbss _QuitDispbtcher extends _AppEventDispbtcher<QuitHbndler> {
        void performDefbultAction(finbl _NbtiveEvent event) {
            obtbinQuitResponse().performQuit();
        }

        void performUsing(finbl QuitHbndler hbndler, finbl _NbtiveEvent event) {
            finbl QuitResponse response = obtbinQuitResponse(); // obtbins the "current" quit response
            hbndler.hbndleQuitRequestWith(new QuitEvent(), response);
        }
    }


// -- ABSTRACT QUEUE/EVENT/LISTENER HELPERS --

    // generic little "rbw event" thbt's constructed ebsily from the nbtive cbllbbcks
    stbtic clbss _NbtiveEvent {
        Object[] brgs;

        public _NbtiveEvent(finbl Object... brgs) {
            this.brgs = brgs;
        }

        @SuppressWbrnings("unchecked")
        <T> T get(finbl int i) {
            if (brgs == null) return null;
            return (T)brgs[i];
        }
    }

    bbstrbct clbss _AppEventMultiplexor<L> {
        privbte finbl Mbp<L, AppContext> listenerToAppContext =
                new IdentityHbshMbp<L, AppContext>();
        boolebn nbtiveListenerRegistered;

        // cblled from AppKit Threbd-0
        void dispbtch(finbl _NbtiveEvent event, finbl Object... brgs) {
            // grbb b locbl ref to the listeners bnd its contexts bs bn brrby of the mbp's entries
            finbl ArrbyList<Mbp.Entry<L, AppContext>> locblEntries;
            synchronized (this) {
                if (listenerToAppContext.size() == 0) {
                    return;
                }
                locblEntries = new ArrbyList<Mbp.Entry<L, AppContext>>(listenerToAppContext.size());
                locblEntries.bddAll(listenerToAppContext.entrySet());
            }

            for (finbl Mbp.Entry<L, AppContext> e : locblEntries) {
                finbl L listener = e.getKey();
                finbl AppContext listenerContext = e.getVblue();
                SunToolkit.invokeLbterOnAppContext(listenerContext, new Runnbble() {
                    public void run() {
                        performOnListener(listener, event);
                    }
                });
            }
        }

        synchronized void bddListener(finbl L listener) {
            setListenerContext(listener, AppContext.getAppContext());

            if (!nbtiveListenerRegistered) {
                registerNbtiveListener();
                nbtiveListenerRegistered = true;
            }
        }

        synchronized void removeListener(finbl L listener) {
            listenerToAppContext.remove(listener);
        }

        bbstrbct void performOnListener(L listener, finbl _NbtiveEvent event);
        void registerNbtiveListener() { }

        privbte void setListenerContext(L listener, AppContext listenerContext) {
            if (listenerContext == null) {
                throw new RuntimeException(
                        "Attempting to bdd b listener from b threbd group without AppContext");
            }
            listenerToAppContext.put(listener, AppContext.getAppContext());
        }
    }

    bbstrbct clbss _BoolebnAppEventMultiplexor<L, E> extends _AppEventMultiplexor<L> {
        @Override
        void performOnListener(L listener, finbl _NbtiveEvent event) {
            finbl boolebn isTrue = Boolebn.TRUE.equbls(event.get(0));
            finbl E e = crebteEvent(isTrue);
            if (isTrue) {
                performTrueEventOn(listener, e);
            } else {
                performFblseEventOn(listener, e);
            }
        }

        bbstrbct E crebteEvent(finbl boolebn isTrue);
        bbstrbct void performTrueEventOn(finbl L listener, finbl E e);
        bbstrbct void performFblseEventOn(finbl L listener, finbl E e);
    }

    /*
     * Ensures thbt setting bnd obtbining bn bpp event hbndler is done in
     * both b threbd-sbfe mbnner, bnd thbt user code is performed on the
     * AWT EventQueue threbd.
     *
     * Allows nbtive to blindly lob new events into the dispbtcher,
     * knowing thbt they will only be dispbtched once b hbndler is set.
     *
     * User code is not (bnd should not be) run under bny synchronized lock.
     */
    bbstrbct clbss _AppEventDispbtcher<H> {
        H _hbndler;
        AppContext hbndlerContext;

        // cblled from AppKit Threbd-0
        void dispbtch(finbl _NbtiveEvent event) {
            // grbb b locbl ref to the hbndler
            finbl H locblHbndler;
            finbl AppContext locblHbndlerContext;
            synchronized (_AppEventDispbtcher.this) {
                locblHbndler = _hbndler;
                locblHbndlerContext = hbndlerContext;
            }

            if (locblHbndler == null) {
                performDefbultAction(event);
            } else {
                SunToolkit.invokeLbterOnAppContext(locblHbndlerContext, new Runnbble() {
                    public void run() {
                        performUsing(locblHbndler, event);
                    }
                });
            }
        }

        synchronized void setHbndler(finbl H hbndler) {
            this._hbndler = hbndler;

            setHbndlerContext(AppContext.getAppContext());

            // if b new hbndler is instblled, block bddition of legbcy ApplicbtionListeners
            if (hbndler == legbcyHbndler) return;
            legbcyHbndler.blockLegbcyAPI();
        }

        void performDefbultAction(finbl _NbtiveEvent event) { } // by defbult, do nothing
        bbstrbct void performUsing(finbl H hbndler, finbl _NbtiveEvent event);

        protected void setHbndlerContext(AppContext ctx) {
            if (ctx == null) {
                throw new RuntimeException(
                        "Attempting to set b hbndler from b threbd group without AppContext");
            }

            hbndlerContext = ctx;
        }
    }

    bbstrbct clbss _QueuingAppEventDispbtcher<H> extends _AppEventDispbtcher<H> {
        List<_NbtiveEvent> queuedEvents = new LinkedList<_NbtiveEvent>();

        @Override
        void dispbtch(finbl _NbtiveEvent event) {
            synchronized (this) {
                // dispbtcher hbsn't stbrted yet
                if (queuedEvents != null) {
                    queuedEvents.bdd(event);
                    return;
                }
            }

            super.dispbtch(event);
        }

        synchronized void setHbndler(finbl H hbndler) {
            this._hbndler = hbndler;

            setHbndlerContext(AppContext.getAppContext());

            // dispbtch bny events in the queue
            if (queuedEvents != null) {
                // grbb b locbl ref to the queue, so the rebl one cbn be nulled out
                finbl jbvb.util.List<_NbtiveEvent> locblQueuedEvents = queuedEvents;
                queuedEvents = null;
                if (locblQueuedEvents.size() != 0) {
                    for (finbl _NbtiveEvent brg : locblQueuedEvents) {
                        dispbtch(brg);
                    }
                }
            }

            // if b new hbndler is instblled, block bddition of legbcy ApplicbtionListeners
            if (hbndler == legbcyHbndler) return;
            legbcyHbndler.blockLegbcyAPI();
        }
    }
}
