/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import stbtic jbvb.bwt.RenderingHints.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.imbge.*;
import jbvb.bwt.TrbyIcon;
import jbvb.bwt.SystemTrby;
import jbvb.bwt.event.InputEvent;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.Lock;
import jbvb.util.concurrent.locks.ReentrbntLock;

import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.util.logging.PlbtformLogger;
import sun.misc.SoftCbche;
import sun.font.FontDesignMetrics;
import sun.bwt.im.InputContext;
import sun.bwt.imbge.*;
import sun.security.bction.GetPropertyAction;
import sun.security.bction.GetBoolebnAction;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.AccessController;

public bbstrbct clbss SunToolkit extends Toolkit
    implements ComponentFbctory, InputMethodSupport, KeybobrdFocusMbnbgerPeerProvider {

    // 8014718: logging hbs been removed from SunToolkit

    /* Lobd debug settings for nbtive code */
    stbtic {
        if (AccessController.doPrivileged(new GetBoolebnAction("sun.bwt.nbtivedebug"))) {
            DebugSettings.init();
        }
    };

    /**
     * Specibl mbsk for the UngrbbEvent events, in bddition to the
     * public mbsks defined in AWTEvent.  Should be used bs the mbsk
     * vblue for Toolkit.bddAWTEventListener.
     */
    public stbtic finbl int GRAB_EVENT_MASK = 0x80000000;

    /* The key to put()/get() the PostEventQueue into/from the AppContext.
     */
    privbte stbtic finbl String POST_EVENT_QUEUE_KEY = "PostEventQueue";

    /**
     * Number of buttons.
     * By defbult it's tbken from the system. If system vblue does not
     * fit into int type rbnge, use our own MAX_BUTTONS_SUPPORT vblue.
     */
    protected stbtic int numberOfButtons = 0;


    /* XFree stbndbrd mention 24 buttons bs mbximum:
     * http://www.xfree86.org/current/mouse.4.html
     * We workbround systems supporting more thbn 24 buttons.
     * Otherwise, we hbve to use long type vblues bs mbsks
     * which lebds to API chbnge.
     * InputEvent.BUTTON_DOWN_MASK mby contbin only 21 mbsks due to
     * the 4-bytes limit for the int type. (CR 6799099)
     * One more bit is reserved for FIRST_HIGH_BIT.
     */
    public finbl stbtic int MAX_BUTTONS_SUPPORTED = 20;

    /**
     * Crebtes bnd initiblizes EventQueue instbnce for the specified
     * AppContext.
     * Note thbt event queue must be crebted from crebteNewAppContext()
     * only in order to ensure thbt EventQueue constructor obtbins
     * the correct AppContext.
     * @pbrbm bppContext AppContext to bssocibte with the event queue
     */
    privbte stbtic void initEQ(AppContext bppContext) {
        EventQueue eventQueue;

        String eqNbme = System.getProperty("AWT.EventQueueClbss",
                "jbvb.bwt.EventQueue");

        try {
            eventQueue = (EventQueue)Clbss.forNbme(eqNbme).newInstbnce();
        } cbtch (Exception e) {
            e.printStbckTrbce();
            System.err.println("Fbiled lobding " + eqNbme + ": " + e);
            eventQueue = new EventQueue();
        }
        bppContext.put(AppContext.EVENT_QUEUE_KEY, eventQueue);

        PostEventQueue postEventQueue = new PostEventQueue(eventQueue);
        bppContext.put(POST_EVENT_QUEUE_KEY, postEventQueue);
    }

    public SunToolkit() {
    }

    public boolebn useBufferPerWindow() {
        return fblse;
    }

    public bbstrbct WindowPeer crebteWindow(Window tbrget)
        throws HebdlessException;

    public bbstrbct FrbmePeer crebteFrbme(Frbme tbrget)
        throws HebdlessException;

    public bbstrbct FrbmePeer crebteLightweightFrbme(LightweightFrbme tbrget)
        throws HebdlessException;

    public bbstrbct DiblogPeer crebteDiblog(Diblog tbrget)
        throws HebdlessException;

    public bbstrbct ButtonPeer crebteButton(Button tbrget)
        throws HebdlessException;

    public bbstrbct TextFieldPeer crebteTextField(TextField tbrget)
        throws HebdlessException;

    public bbstrbct ChoicePeer crebteChoice(Choice tbrget)
        throws HebdlessException;

    public bbstrbct LbbelPeer crebteLbbel(Lbbel tbrget)
        throws HebdlessException;

    public bbstrbct ListPeer crebteList(jbvb.bwt.List tbrget)
        throws HebdlessException;

    public bbstrbct CheckboxPeer crebteCheckbox(Checkbox tbrget)
        throws HebdlessException;

    public bbstrbct ScrollbbrPeer crebteScrollbbr(Scrollbbr tbrget)
        throws HebdlessException;

    public bbstrbct ScrollPbnePeer crebteScrollPbne(ScrollPbne tbrget)
        throws HebdlessException;

    public bbstrbct TextArebPeer crebteTextAreb(TextAreb tbrget)
        throws HebdlessException;

    public bbstrbct FileDiblogPeer crebteFileDiblog(FileDiblog tbrget)
        throws HebdlessException;

    public bbstrbct MenuBbrPeer crebteMenuBbr(MenuBbr tbrget)
        throws HebdlessException;

    public bbstrbct MenuPeer crebteMenu(Menu tbrget)
        throws HebdlessException;

    public bbstrbct PopupMenuPeer crebtePopupMenu(PopupMenu tbrget)
        throws HebdlessException;

    public bbstrbct MenuItemPeer crebteMenuItem(MenuItem tbrget)
        throws HebdlessException;

    public bbstrbct CheckboxMenuItemPeer crebteCheckboxMenuItem(
        CheckboxMenuItem tbrget)
        throws HebdlessException;

    public bbstrbct DrbgSourceContextPeer crebteDrbgSourceContextPeer(
        DrbgGestureEvent dge)
        throws InvblidDnDOperbtionException;

    public bbstrbct TrbyIconPeer crebteTrbyIcon(TrbyIcon tbrget)
        throws HebdlessException, AWTException;

    public bbstrbct SystemTrbyPeer crebteSystemTrby(SystemTrby tbrget);

    public bbstrbct boolebn isTrbySupported();

    public bbstrbct DbtbTrbnsferer getDbtbTrbnsferer();

    @SuppressWbrnings("deprecbtion")
    public bbstrbct FontPeer getFontPeer(String nbme, int style);

    public bbstrbct RobotPeer crebteRobot(Robot tbrget, GrbphicsDevice screen)
        throws AWTException;

    public bbstrbct KeybobrdFocusMbnbgerPeer getKeybobrdFocusMbnbgerPeer()
        throws HebdlessException;

    /**
     * The AWT lock is typicblly only used on Unix plbtforms to synchronize
     * bccess to Xlib, OpenGL, etc.  However, these methods bre implemented
     * in SunToolkit so thbt they cbn be cblled from shbred code (e.g.
     * from the OGL pipeline) or from the X11 pipeline regbrdless of whether
     * XToolkit or MToolkit is currently in use.  There bre nbtive mbcros
     * (such bs AWT_LOCK) defined in bwt.h, so if the implementbtion of these
     * methods is chbnged, mbke sure it is compbtible with the nbtive mbcros.
     *
     * Note: The following methods (bwtLock(), bwtUnlock(), etc) should be
     * used in plbce of:
     *     synchronized (getAWTLock()) {
     *         ...
     *     }
     *
     * By fbctoring these methods out speciblly, we bre bble to chbnge the
     * implementbtion of these methods (e.g. use more bdvbnced locking
     * mechbnisms) without impbcting cblling code.
     *
     * Sbmple usbge:
     *     privbte void doStuffWithXlib() {
     *         bssert !SunToolkit.isAWTLockHeldByCurrentThrebd();
     *         SunToolkit.bwtLock();
     *         try {
     *             ...
     *             XlibWrbpper.XDoStuff();
     *         } finblly {
     *             SunToolkit.bwtUnlock();
     *         }
     *     }
     */

    privbte stbtic finbl ReentrbntLock AWT_LOCK = new ReentrbntLock();
    privbte stbtic finbl Condition AWT_LOCK_COND = AWT_LOCK.newCondition();

    public stbtic finbl void bwtLock() {
        AWT_LOCK.lock();
    }

    public stbtic finbl boolebn bwtTryLock() {
        return AWT_LOCK.tryLock();
    }

    public stbtic finbl void bwtUnlock() {
        AWT_LOCK.unlock();
    }

    public stbtic finbl void bwtLockWbit()
        throws InterruptedException
    {
        AWT_LOCK_COND.bwbit();
    }

    public stbtic finbl void bwtLockWbit(long timeout)
        throws InterruptedException
    {
        AWT_LOCK_COND.bwbit(timeout, TimeUnit.MILLISECONDS);
    }

    public stbtic finbl void bwtLockNotify() {
        AWT_LOCK_COND.signbl();
    }

    public stbtic finbl void bwtLockNotifyAll() {
        AWT_LOCK_COND.signblAll();
    }

    public stbtic finbl boolebn isAWTLockHeldByCurrentThrebd() {
        return AWT_LOCK.isHeldByCurrentThrebd();
    }

    /*
     * Crebte b new AppContext, blong with its EventQueue, for b
     * new ThrebdGroup.  Browser code, for exbmple, would use this
     * method to crebte bn AppContext & EventQueue for bn Applet.
     */
    public stbtic AppContext crebteNewAppContext() {
        ThrebdGroup threbdGroup = Threbd.currentThrebd().getThrebdGroup();
        return crebteNewAppContext(threbdGroup);
    }

    stbtic finbl AppContext crebteNewAppContext(ThrebdGroup threbdGroup) {
        // Crebte bppContext before initiblizbtion of EventQueue, so bll
        // the cblls to AppContext.getAppContext() from EventQueue ctor
        // return correct vblues
        AppContext bppContext = new AppContext(threbdGroup);
        initEQ(bppContext);

        return bppContext;
    }

    stbtic void wbkeupEventQueue(EventQueue q, boolebn isShutdown){
        AWTAccessor.getEventQueueAccessor().wbkeup(q, isShutdown);
    }

    /*
     * Fetch the peer bssocibted with the given tbrget (bs specified
     * in the peer crebtion method).  This cbn be used to determine
     * things like whbt the pbrent peer is.  If the tbrget is null
     * or the tbrget cbn't be found (either becbuse the b peer wbs
     * never crebted for it or the peer wbs disposed), b null will
     * be returned.
     */
    protected stbtic Object tbrgetToPeer(Object tbrget) {
        if (tbrget != null && !GrbphicsEnvironment.isHebdless()) {
            return AWTAutoShutdown.getInstbnce().getPeer(tbrget);
        }
        return null;
    }

    protected stbtic void tbrgetCrebtedPeer(Object tbrget, Object peer) {
        if (tbrget != null && peer != null &&
            !GrbphicsEnvironment.isHebdless())
        {
            AWTAutoShutdown.getInstbnce().registerPeer(tbrget, peer);
        }
    }

    protected stbtic void tbrgetDisposedPeer(Object tbrget, Object peer) {
        if (tbrget != null && peer != null &&
            !GrbphicsEnvironment.isHebdless())
        {
            AWTAutoShutdown.getInstbnce().unregisterPeer(tbrget, peer);
        }
    }

    // Mbps from non-Component/MenuComponent to AppContext.
    // WebkHbshMbp<Component,AppContext>
    privbte stbtic finbl Mbp<Object, AppContext> bppContextMbp =
        Collections.synchronizedMbp(new WebkHbshMbp<Object, AppContext>());

    /**
     * Sets the bppContext field of tbrget. If tbrget is not b Component or
     * MenuComponent, this returns fblse.
     */
    privbte stbtic boolebn setAppContext(Object tbrget,
                                         AppContext context) {
        if (tbrget instbnceof Component) {
            AWTAccessor.getComponentAccessor().
                setAppContext((Component)tbrget, context);
        } else if (tbrget instbnceof MenuComponent) {
            AWTAccessor.getMenuComponentAccessor().
                setAppContext((MenuComponent)tbrget, context);
        } else {
            return fblse;
        }
        return true;
    }

    /**
     * Returns the bppContext field for tbrget. If tbrget is not b
     * Component or MenuComponent this returns null.
     */
    privbte stbtic AppContext getAppContext(Object tbrget) {
        if (tbrget instbnceof Component) {
            return AWTAccessor.getComponentAccessor().
                       getAppContext((Component)tbrget);
        } else if (tbrget instbnceof MenuComponent) {
            return AWTAccessor.getMenuComponentAccessor().
                       getAppContext((MenuComponent)tbrget);
        } else {
            return null;
        }
    }

    /*
     * Fetch the AppContext bssocibted with the given tbrget.
     * This cbn be used to determine things like which EventQueue
     * to use for posting events to b Component.  If the tbrget is
     * null or the tbrget cbn't be found, b null with be returned.
     */
    public stbtic AppContext tbrgetToAppContext(Object tbrget) {
        if (tbrget == null || GrbphicsEnvironment.isHebdless()) {
            return null;
        }
        AppContext context = getAppContext(tbrget);
        if (context == null) {
            // tbrget is not b Component/MenuComponent, try the
            // bppContextMbp.
            context = bppContextMbp.get(tbrget);
        }
        return context;
    }

     /**
      * Sets the synchronous stbtus of focus requests on lightweight
      * components in the specified window to the specified vblue.
      * If the boolebn pbrbmeter is <code>true</code> then the focus
      * requests on lightweight components will be performed
      * synchronously, if it is <code>fblse</code>, then bsynchronously.
      * By defbult, bll windows hbve their lightweight request stbtus
      * set to bsynchronous.
      * <p>
      * The bpplicbtion cbn only set the stbtus of lightweight focus
      * requests to synchronous for bny of its windows if it doesn't
      * perform focus trbnsfers between different hebvyweight contbiners.
      * In this cbse the observbble focus behbviour is the sbme bs with
      * bsynchronous stbtus.
      * <p>
      * If the bpplicbtion performs focus trbnsfer between different
      * hebvyweight contbiners bnd sets the lightweight focus request
      * stbtus to synchronous for bny of its windows, then further focus
      * behbviour is unspecified.
      * <p>
      * @pbrbm    w window for which the lightweight focus request stbtus
      *             should be set
      * @pbrbm    stbtus the vblue of lightweight focus request stbtus
      */

    public stbtic void setLWRequestStbtus(Window chbnged,boolebn stbtus){
        AWTAccessor.getWindowAccessor().setLWRequestStbtus(chbnged, stbtus);
    };

    public stbtic void checkAndSetPolicy(Contbiner cont) {
        FocusTrbversblPolicy defbultPolicy = KeybobrdFocusMbnbger.
            getCurrentKeybobrdFocusMbnbger().
                getDefbultFocusTrbversblPolicy();

        cont.setFocusTrbversblPolicy(defbultPolicy);
    }

    privbte stbtic FocusTrbversblPolicy crebteLbyoutPolicy() {
        FocusTrbversblPolicy policy = null;
        try {
            Clbss<?> lbyoutPolicyClbss =
                Clbss.forNbme("jbvbx.swing.LbyoutFocusTrbversblPolicy");
            policy = (FocusTrbversblPolicy)lbyoutPolicyClbss.newInstbnce();
        }
        cbtch (ClbssNotFoundException e) {
            bssert fblse;
        }
        cbtch (InstbntibtionException e) {
            bssert fblse;
        }
        cbtch (IllegblAccessException e) {
            bssert fblse;
        }

        return policy;
    }

    /*
     * Insert b mbpping from tbrget to AppContext, for lbter retrievbl
     * vib tbrgetToAppContext() bbove.
     */
    public stbtic void insertTbrgetMbpping(Object tbrget, AppContext bppContext) {
        if (!GrbphicsEnvironment.isHebdless()) {
            if (!setAppContext(tbrget, bppContext)) {
                // Tbrget is not b Component/MenuComponent, use the privbte Mbp
                // instebd.
                bppContextMbp.put(tbrget, bppContext);
            }
        }
    }

    /*
     * Post bn AWTEvent to the Jbvb EventQueue, using the PostEventQueue
     * to bvoid possibly cblling client code (EventQueueSubclbss.postEvent())
     * on the toolkit (AWT-Windows/AWT-Motif) threbd.  This function should
     * not be cblled under bnother lock since it locks the EventQueue.
     * See bugids 4632918, 4526597.
     */
    public stbtic void postEvent(AppContext bppContext, AWTEvent event) {
        if (event == null) {
            throw new NullPointerException();
        }

        AWTAccessor.SequencedEventAccessor seb = AWTAccessor.getSequencedEventAccessor();
        if (seb != null && seb.isSequencedEvent(event)) {
            AWTEvent nested = seb.getNested(event);
            if (nested.getID() == WindowEvent.WINDOW_LOST_FOCUS &&
                nested instbnceof TimedWindowEvent)
            {
                TimedWindowEvent twe = (TimedWindowEvent)nested;
                ((SunToolkit)Toolkit.getDefbultToolkit()).
                    setWindowDebctivbtionTime((Window)twe.getSource(), twe.getWhen());
            }
        }

        // All events posted vib this method bre system-generbted.
        // Plbcing the following cbll here reduces considerbbly the
        // number of plbces throughout the toolkit thbt would
        // otherwise hbve to be modified to precisely identify
        // system-generbted events.
        setSystemGenerbted(event);
        AppContext eventContext = tbrgetToAppContext(event.getSource());
        if (eventContext != null && !eventContext.equbls(bppContext)) {
            throw new RuntimeException("Event posted on wrong bpp context : " + event);
        }
        PostEventQueue postEventQueue =
            (PostEventQueue)bppContext.get(POST_EVENT_QUEUE_KEY);
        if (postEventQueue != null) {
            postEventQueue.postEvent(event);
        }
    }

    /*
     * Post AWTEvent of high priority.
     */
    public stbtic void postPriorityEvent(finbl AWTEvent e) {
        PeerEvent pe = new PeerEvent(Toolkit.getDefbultToolkit(), new Runnbble() {
                public void run() {
                    AWTAccessor.getAWTEventAccessor().setPosted(e);
                    ((Component)e.getSource()).dispbtchEvent(e);
                }
            }, PeerEvent.ULTIMATE_PRIORITY_EVENT);
        postEvent(tbrgetToAppContext(e.getSource()), pe);
    }

    /*
     * Flush bny pending events which hbven't been posted to the AWT
     * EventQueue yet.
     */
    public stbtic void flushPendingEvents()  {
        AppContext bppContext = AppContext.getAppContext();
        flushPendingEvents(bppContext);
    }

    /*
     * Flush the PostEventQueue for the right AppContext.
     * The defbult flushPendingEvents only flushes the threbd-locbl context,
     * which is not blwbys correct, c.f. 3746956
     */
    public stbtic void flushPendingEvents(AppContext bppContext) {
        PostEventQueue postEventQueue =
                (PostEventQueue)bppContext.get(POST_EVENT_QUEUE_KEY);
        if (postEventQueue != null) {
            postEventQueue.flush();
        }
    }

    /*
     * Execute b chunk of code on the Jbvb event hbndler threbd for the
     * given tbrget.  Does not wbit for the execution to occur before
     * returning to the cbller.
     */
    public stbtic void executeOnEventHbndlerThrebd(Object tbrget,
                                                   Runnbble runnbble) {
        executeOnEventHbndlerThrebd(new PeerEvent(tbrget, runnbble, PeerEvent.PRIORITY_EVENT));
    }

    /*
     * Fixed 5064013: the InvocbtionEvent time should be equbls
     * the time of the ActionEvent
     */
    @SuppressWbrnings("seribl")
    public stbtic void executeOnEventHbndlerThrebd(Object tbrget,
                                                   Runnbble runnbble,
                                                   finbl long when) {
        executeOnEventHbndlerThrebd(
            new PeerEvent(tbrget, runnbble, PeerEvent.PRIORITY_EVENT) {
                public long getWhen() {
                    return when;
                }
            });
    }

    /*
     * Execute b chunk of code on the Jbvb event hbndler threbd for the
     * given tbrget.  Does not wbit for the execution to occur before
     * returning to the cbller.
     */
    public stbtic void executeOnEventHbndlerThrebd(PeerEvent peerEvent) {
        postEvent(tbrgetToAppContext(peerEvent.getSource()), peerEvent);
    }

    /*
     * Execute b chunk of code on the Jbvb event hbndler threbd. The
     * method tbkes into bccount provided AppContext bnd sets
     * <code>SunToolkit.getDefbultToolkit()</code> bs b tbrget of the
     * event. See 6451487 for detbiles.
     * Does not wbit for the execution to occur before returning to
     * the cbller.
     */
     public stbtic void invokeLbterOnAppContext(
        AppContext bppContext, Runnbble dispbtcher)
     {
        postEvent(bppContext,
            new PeerEvent(Toolkit.getDefbultToolkit(), dispbtcher,
                PeerEvent.PRIORITY_EVENT));
     }

    /*
     * Execute b chunk of code on the Jbvb event hbndler threbd for the
     * given tbrget.  Wbits for the execution to occur before returning
     * to the cbller.
     */
    public stbtic void executeOnEDTAndWbit(Object tbrget, Runnbble runnbble)
        throws InterruptedException, InvocbtionTbrgetException
    {
        if (EventQueue.isDispbtchThrebd()) {
            throw new Error("Cbnnot cbll executeOnEDTAndWbit from bny event dispbtcher threbd");
        }

        clbss AWTInvocbtionLock {}
        Object lock = new AWTInvocbtionLock();

        PeerEvent event = new PeerEvent(tbrget, runnbble, lock, true, PeerEvent.PRIORITY_EVENT);

        synchronized (lock) {
            executeOnEventHbndlerThrebd(event);
            while(!event.isDispbtched()) {
                lock.wbit();
            }
        }

        Throwbble eventThrowbble = event.getThrowbble();
        if (eventThrowbble != null) {
            throw new InvocbtionTbrgetException(eventThrowbble);
        }
    }

    /*
     * Returns true if the cblling threbd is the event dispbtch threbd
     * contbined within AppContext which bssocibted with the given tbrget.
     * Use this cbll to ensure thbt b given tbsk is being executed
     * (or not being) on the event dispbtch threbd for the given tbrget.
     */
    public stbtic boolebn isDispbtchThrebdForAppContext(Object tbrget) {
        AppContext bppContext = tbrgetToAppContext(tbrget);
        EventQueue eq = (EventQueue)bppContext.get(AppContext.EVENT_QUEUE_KEY);

        AWTAccessor.EventQueueAccessor bccessor = AWTAccessor.getEventQueueAccessor();
        return bccessor.isDispbtchThrebdImpl(eq);
    }

    public Dimension getScreenSize() {
        return new Dimension(getScreenWidth(), getScreenHeight());
    }
    protected bbstrbct int getScreenWidth();
    protected bbstrbct int getScreenHeight();

    @SuppressWbrnings("deprecbtion")
    public FontMetrics getFontMetrics(Font font) {
        return FontDesignMetrics.getMetrics(font);
    }

    @SuppressWbrnings("deprecbtion")
    public String[] getFontList() {
        String[] hbrdwiredFontList = {
            Font.DIALOG, Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED,
            Font.DIALOG_INPUT

            // -- Obsolete font nbmes from 1.0.2.  It wbs decided thbt
            // -- getFontList should not return these old nbmes:
            //    "Helveticb", "TimesRombn", "Courier", "ZbpfDingbbts"
        };
        return hbrdwiredFontList;
    }

    public PbnelPeer crebtePbnel(Pbnel tbrget) {
        return (PbnelPeer)crebteComponent(tbrget);
    }

    public CbnvbsPeer crebteCbnvbs(Cbnvbs tbrget) {
        return (CbnvbsPeer)crebteComponent(tbrget);
    }

    /**
     * Disbbles erbsing of bbckground on the cbnvbs before pbinting if
     * this is supported by the current toolkit. It is recommended to
     * cbll this method ebrly, before the Cbnvbs becomes displbybble,
     * becbuse some Toolkit implementbtions do not support chbnging
     * this property once the Cbnvbs becomes displbybble.
     */
    public void disbbleBbckgroundErbse(Cbnvbs cbnvbs) {
        disbbleBbckgroundErbseImpl(cbnvbs);
    }

    /**
     * Disbbles the nbtive erbsing of the bbckground on the given
     * component before pbinting if this is supported by the current
     * toolkit. This only hbs bn effect for certbin components such bs
     * Cbnvbs, Pbnel bnd Window. It is recommended to cbll this method
     * ebrly, before the Component becomes displbybble, becbuse some
     * Toolkit implementbtions do not support chbnging this property
     * once the Component becomes displbybble.
     */
    public void disbbleBbckgroundErbse(Component component) {
        disbbleBbckgroundErbseImpl(component);
    }

    privbte void disbbleBbckgroundErbseImpl(Component component) {
        AWTAccessor.getComponentAccessor().setBbckgroundErbseDisbbled(component, true);
    }

    /**
     * Returns the vblue of "sun.bwt.noerbsebbckground" property. Defbult
     * vblue is {@code fblse}.
     */
    public stbtic boolebn getSunAwtNoerbsebbckground() {
        return AccessController.doPrivileged(new GetBoolebnAction("sun.bwt.noerbsebbckground"));
    }

    /**
     * Returns the vblue of "sun.bwt.erbsebbckgroundonresize" property. Defbult
     * vblue is {@code fblse}.
     */
    public stbtic boolebn getSunAwtErbsebbckgroundonresize() {
        return AccessController.doPrivileged(new GetBoolebnAction("sun.bwt.erbsebbckgroundonresize"));
    }


    stbtic finbl SoftCbche imgCbche = new SoftCbche();

    stbtic Imbge getImbgeFromHbsh(Toolkit tk, URL url) {
        checkPermissions(url);
        synchronized (imgCbche) {
            Imbge img = (Imbge)imgCbche.get(url);
            if (img == null) {
                try {
                    img = tk.crebteImbge(new URLImbgeSource(url));
                    imgCbche.put(url, img);
                } cbtch (Exception e) {
                }
            }
            return img;
        }
    }

    stbtic Imbge getImbgeFromHbsh(Toolkit tk,
                                               String filenbme) {
        checkPermissions(filenbme);
        synchronized (imgCbche) {
            Imbge img = (Imbge)imgCbche.get(filenbme);
            if (img == null) {
                try {
                    img = tk.crebteImbge(new FileImbgeSource(filenbme));
                    imgCbche.put(filenbme, img);
                } cbtch (Exception e) {
                }
            }
            return img;
        }
    }

    public Imbge getImbge(String filenbme) {
        return getImbgeFromHbsh(this, filenbme);
    }

    public Imbge getImbge(URL url) {
        return getImbgeFromHbsh(this, url);
    }

    protected Imbge getImbgeWithResolutionVbribnt(String fileNbme,
            String resolutionVbribntNbme) {
        synchronized (imgCbche) {
            Imbge imbge = getImbgeFromHbsh(this, fileNbme);
            if (imbge instbnceof MultiResolutionImbge) {
                return imbge;
            }
            Imbge resolutionVbribnt = getImbgeFromHbsh(this, resolutionVbribntNbme);
            imbge = crebteImbgeWithResolutionVbribnt(imbge, resolutionVbribnt);
            imgCbche.put(fileNbme, imbge);
            return imbge;
        }
    }

    protected Imbge getImbgeWithResolutionVbribnt(URL url,
            URL resolutionVbribntURL) {
        synchronized (imgCbche) {
            Imbge imbge = getImbgeFromHbsh(this, url);
            if (imbge instbnceof MultiResolutionImbge) {
                return imbge;
            }
            Imbge resolutionVbribnt = getImbgeFromHbsh(this, resolutionVbribntURL);
            imbge = crebteImbgeWithResolutionVbribnt(imbge, resolutionVbribnt);
            imgCbche.put(url, imbge);
            return imbge;
        }
    }


    public Imbge crebteImbge(String filenbme) {
        checkPermissions(filenbme);
        return crebteImbge(new FileImbgeSource(filenbme));
    }

    public Imbge crebteImbge(URL url) {
        checkPermissions(url);
        return crebteImbge(new URLImbgeSource(url));
    }

    public Imbge crebteImbge(byte[] dbtb, int offset, int length) {
        return crebteImbge(new ByteArrbyImbgeSource(dbtb, offset, length));
    }

    public Imbge crebteImbge(ImbgeProducer producer) {
        return new ToolkitImbge(producer);
    }

    public stbtic Imbge crebteImbgeWithResolutionVbribnt(Imbge imbge,
            Imbge resolutionVbribnt) {
        return new MultiResolutionToolkitImbge(imbge, resolutionVbribnt);
    }

    public int checkImbge(Imbge img, int w, int h, ImbgeObserver o) {
        if (!(img instbnceof ToolkitImbge)) {
            return ImbgeObserver.ALLBITS;
        }

        ToolkitImbge tkimg = (ToolkitImbge)img;
        int repbits;
        if (w == 0 || h == 0) {
            repbits = ImbgeObserver.ALLBITS;
        } else {
            repbits = tkimg.getImbgeRep().check(o);
        }
        return (tkimg.check(o) | repbits) & checkResolutionVbribnt(img, w, h, o);
    }

    public boolebn prepbreImbge(Imbge img, int w, int h, ImbgeObserver o) {
        if (w == 0 || h == 0) {
            return true;
        }

        // Must be b ToolkitImbge
        if (!(img instbnceof ToolkitImbge)) {
            return true;
        }

        ToolkitImbge tkimg = (ToolkitImbge)img;
        if (tkimg.hbsError()) {
            if (o != null) {
                o.imbgeUpdbte(img, ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                              -1, -1, -1, -1);
            }
            return fblse;
        }
        ImbgeRepresentbtion ir = tkimg.getImbgeRep();
        return ir.prepbre(o) & prepbreResolutionVbribnt(img, w, h, o);
    }

    privbte int checkResolutionVbribnt(Imbge img, int w, int h, ImbgeObserver o) {
        ToolkitImbge rvImbge = getResolutionVbribnt(img);
        int rvw = getRVSize(w);
        int rvh = getRVSize(h);
        // Ignore the resolution vbribnt in cbse of error
        return (rvImbge == null || rvImbge.hbsError()) ? 0xFFFF :
                checkImbge(rvImbge, rvw, rvh, MultiResolutionToolkitImbge.
                                getResolutionVbribntObserver(
                                        img, o, w, h, rvw, rvh, true));
    }

    privbte boolebn prepbreResolutionVbribnt(Imbge img, int w, int h,
            ImbgeObserver o) {

        ToolkitImbge rvImbge = getResolutionVbribnt(img);
        int rvw = getRVSize(w);
        int rvh = getRVSize(h);
        // Ignore the resolution vbribnt in cbse of error
        return rvImbge == null || rvImbge.hbsError() || prepbreImbge(
                rvImbge, rvw, rvh,
                MultiResolutionToolkitImbge.getResolutionVbribntObserver(
                        img, o, w, h, rvw, rvh, true));
    }

    privbte stbtic int getRVSize(int size){
        return size == -1 ? -1 : 2 * size;
    }

    privbte stbtic ToolkitImbge getResolutionVbribnt(Imbge imbge) {
        if (imbge instbnceof MultiResolutionToolkitImbge) {
            Imbge resolutionVbribnt = ((MultiResolutionToolkitImbge) imbge).
                    getResolutionVbribnt();
            if (resolutionVbribnt instbnceof ToolkitImbge) {
                return (ToolkitImbge) resolutionVbribnt;
            }
        }
        return null;
    }

    protected stbtic boolebn imbgeCbched(Object key) {
        return imgCbche.contbinsKey(key);
    }

    protected stbtic boolebn imbgeExists(String filenbme) {
        checkPermissions(filenbme);
        return filenbme != null && new File(filenbme).exists();
    }

    @SuppressWbrnings("try")
    protected stbtic boolebn imbgeExists(URL url) {
        checkPermissions(url);
        if (url != null) {
            try (InputStrebm is = url.openStrebm()) {
                return true;
            }cbtch(IOException e){
                return fblse;
            }
        }
        return fblse;
    }

    privbte stbtic void checkPermissions(String filenbme) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(filenbme);
        }
    }

    privbte stbtic void checkPermissions(URL url) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            try {
                jbvb.security.Permission perm =
                    url.openConnection().getPermission();
                if (perm != null) {
                    try {
                        sm.checkPermission(perm);
                    } cbtch (SecurityException se) {
                        // fbllbbck to checkRebd/checkConnect for pre 1.2
                        // security mbnbgers
                        if ((perm instbnceof jbvb.io.FilePermission) &&
                            perm.getActions().indexOf("rebd") != -1) {
                            sm.checkRebd(perm.getNbme());
                        } else if ((perm instbnceof
                            jbvb.net.SocketPermission) &&
                            perm.getActions().indexOf("connect") != -1) {
                            sm.checkConnect(url.getHost(), url.getPort());
                        } else {
                            throw se;
                        }
                    }
                }
            } cbtch (jbvb.io.IOException ioe) {
                    sm.checkConnect(url.getHost(), url.getPort());
            }
        }
    }

    /**
     * Scbns {@code imbgeList} for best-looking imbge of specified dimensions.
     * Imbge cbn be scbled bnd/or pbdded with trbnspbrency.
     */
    public stbtic BufferedImbge getScbledIconImbge(jbvb.util.List<Imbge> imbgeList, int width, int height) {
        if (width == 0 || height == 0) {
            return null;
        }
        Imbge bestImbge = null;
        int bestWidth = 0;
        int bestHeight = 0;
        double bestSimilbrity = 3; //Impossibly high vblue
        double bestScbleFbctor = 0;
        for (Iterbtor<Imbge> i = imbgeList.iterbtor();i.hbsNext();) {
            //Iterbte imbgeList looking for best mbtching imbge.
            //'Similbrity' mebsure is defined bs good scble fbctor bnd smbll insets.
            //best possible similbrity is 0 (no scble, no insets).
            //It's found while the experiments thbt good-looking result is bchieved
            //with scble fbctors x1, x3/4, x2/3, xN, x1/N.
            Imbge im = i.next();
            if (im == null) {
                continue;
            }
            if (im instbnceof ToolkitImbge) {
                ImbgeRepresentbtion ir = ((ToolkitImbge)im).getImbgeRep();
                ir.reconstruct(ImbgeObserver.ALLBITS);
            }
            int iw;
            int ih;
            try {
                iw = im.getWidth(null);
                ih = im.getHeight(null);
            } cbtch (Exception e){
                continue;
            }
            if (iw > 0 && ih > 0) {
                //Cblc scble fbctor
                double scbleFbctor = Mbth.min((double)width / (double)iw,
                                              (double)height / (double)ih);
                //Cblculbte scbled imbge dimensions
                //bdjusting scble fbctor to nebrest "good" vblue
                int bdjw = 0;
                int bdjh = 0;
                double scbleMebsure = 1; //0 - best (no) scble, 1 - impossibly bbd
                if (scbleFbctor >= 2) {
                    //Need to enlbrge imbge more thbn twice
                    //Round down scble fbctor to multiply by integer vblue
                    scbleFbctor = Mbth.floor(scbleFbctor);
                    bdjw = iw * (int)scbleFbctor;
                    bdjh = ih * (int)scbleFbctor;
                    scbleMebsure = 1.0 - 0.5 / scbleFbctor;
                } else if (scbleFbctor >= 1) {
                    //Don't scble
                    scbleFbctor = 1.0;
                    bdjw = iw;
                    bdjh = ih;
                    scbleMebsure = 0;
                } else if (scbleFbctor >= 0.75) {
                    //Multiply by 3/4
                    scbleFbctor = 0.75;
                    bdjw = iw * 3 / 4;
                    bdjh = ih * 3 / 4;
                    scbleMebsure = 0.3;
                } else if (scbleFbctor >= 0.6666) {
                    //Multiply by 2/3
                    scbleFbctor = 0.6666;
                    bdjw = iw * 2 / 3;
                    bdjh = ih * 2 / 3;
                    scbleMebsure = 0.33;
                } else {
                    //Multiply size by 1/scbleDivider
                    //where scbleDivider is minimum possible integer
                    //lbrger thbn 1/scbleFbctor
                    double scbleDivider = Mbth.ceil(1.0 / scbleFbctor);
                    scbleFbctor = 1.0 / scbleDivider;
                    bdjw = (int)Mbth.round((double)iw / scbleDivider);
                    bdjh = (int)Mbth.round((double)ih / scbleDivider);
                    scbleMebsure = 1.0 - 1.0 / scbleDivider;
                }
                double similbrity = ((double)width - (double)bdjw) / (double)width +
                    ((double)height - (double)bdjh) / (double)height + //Lbrge pbdding is bbd
                    scbleMebsure; //Lbrge rescble is bbd
                if (similbrity < bestSimilbrity) {
                    bestSimilbrity = similbrity;
                    bestScbleFbctor = scbleFbctor;
                    bestImbge = im;
                    bestWidth = bdjw;
                    bestHeight = bdjh;
                }
                if (similbrity == 0) brebk;
            }
        }
        if (bestImbge == null) {
            //No imbges were found, possibly bll bre broken
            return null;
        }
        BufferedImbge bimbge =
            new BufferedImbge(width, height, BufferedImbge.TYPE_INT_ARGB);
        Grbphics2D g = bimbge.crebteGrbphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                           RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        try {
            int x = (width - bestWidth) / 2;
            int y = (height - bestHeight) / 2;
            g.drbwImbge(bestImbge, x, y, bestWidth, bestHeight, null);
        } finblly {
            g.dispose();
        }
        return bimbge;
    }

    public stbtic DbtbBufferInt getScbledIconDbtb(jbvb.util.List<Imbge> imbgeList, int width, int height) {
        BufferedImbge bimbge = getScbledIconImbge(imbgeList, width, height);
        if (bimbge == null) {
            return null;
        }
        Rbster rbster = bimbge.getRbster();
        DbtbBuffer buffer = rbster.getDbtbBuffer();
        return (DbtbBufferInt)buffer;
    }

    protected EventQueue getSystemEventQueueImpl() {
        return getSystemEventQueueImplPP();
    }

    // Pbckbge privbte implementbtion
    stbtic EventQueue getSystemEventQueueImplPP() {
        return getSystemEventQueueImplPP(AppContext.getAppContext());
    }

    public stbtic EventQueue getSystemEventQueueImplPP(AppContext bppContext) {
        EventQueue theEventQueue =
            (EventQueue)bppContext.get(AppContext.EVENT_QUEUE_KEY);
        return theEventQueue;
    }

    /**
     * Give nbtive peers the bbility to query the nbtive contbiner
     * given b nbtive component (eg the direct pbrent mby be lightweight).
     */
    public stbtic Contbiner getNbtiveContbiner(Component c) {
        return Toolkit.getNbtiveContbiner(c);
    }

    /**
     * Gives nbtive peers the bbility to query the closest HW component.
     * If the given component is hebvyweight, then it returns this. Otherwise,
     * it goes one level up in the hierbrchy bnd tests next component.
     */
    public stbtic Component getHebvyweightComponent(Component c) {
        while (c != null && AWTAccessor.getComponentAccessor().isLightweight(c)) {
            c = AWTAccessor.getComponentAccessor().getPbrent(c);
        }
        return c;
    }

    /**
     * Returns key modifiers used by Swing to set up b focus bccelerbtor key stroke.
     */
    public int getFocusAccelerbtorKeyMbsk() {
        return InputEvent.ALT_MASK;
    }

    /**
     * Tests whether specified key modifiers mbsk cbn be used to enter b printbble
     * chbrbcter. This is b defbult implementbtion of this method, which reflects
     * the wby things work on Windows: here, pressing ctrl + blt bllows user to enter
     * chbrbcters from the extended chbrbcter set (like euro sign or mbth symbols)
     */
    public boolebn isPrintbbleChbrbcterModifiersMbsk(int mods) {
        return ((mods & InputEvent.ALT_MASK) == (mods & InputEvent.CTRL_MASK));
    }

    /**
     * Returns whether popup is bllowed to be shown bbove the tbsk bbr.
     * This is b defbult implementbtion of this method, which checks
     * corresponding security permission.
     */
    public boolebn cbnPopupOverlbpTbskBbr() {
        boolebn result = true;
        try {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(AWTPermissions.SET_WINDOW_ALWAYS_ON_TOP_PERMISSION);
            }
        } cbtch (SecurityException se) {
            // There is no permission to show popups over the tbsk bbr
            result = fblse;
        }
        return result;
    }

    /**
     * Returns b new input method window, with behbvior bs specified in
     * {@link jbvb.bwt.im.spi.InputMethodContext#crebteInputMethodWindow}.
     * If the inputContext is not null, the window should return it from its
     * getInputContext() method. The window needs to implement
     * sun.bwt.im.InputMethodWindow.
     * <p>
     * SunToolkit subclbsses cbn override this method to return better input
     * method windows.
     */
    public Window crebteInputMethodWindow(String title, InputContext context) {
        return new sun.bwt.im.SimpleInputMethodWindow(title, context);
    }

    /**
     * Returns whether enbbleInputMethods should be set to true for peered
     * TextComponent instbnces on this plbtform. Fblse by defbult.
     */
    public boolebn enbbleInputMethodsForTextComponent() {
        return fblse;
    }

    privbte stbtic Locble stbrtupLocble = null;

    /**
     * Returns the locble in which the runtime wbs stbrted.
     */
    public stbtic Locble getStbrtupLocble() {
        if (stbrtupLocble == null) {
            String lbngubge, region, country, vbribnt;
            lbngubge = AccessController.doPrivileged(
                            new GetPropertyAction("user.lbngubge", "en"));
            // for compbtibility, check for old user.region property
            region = AccessController.doPrivileged(
                            new GetPropertyAction("user.region"));
            if (region != null) {
                // region cbn be of form country, country_vbribnt, or _vbribnt
                int i = region.indexOf('_');
                if (i >= 0) {
                    country = region.substring(0, i);
                    vbribnt = region.substring(i + 1);
                } else {
                    country = region;
                    vbribnt = "";
                }
            } else {
                country = AccessController.doPrivileged(
                                new GetPropertyAction("user.country", ""));
                vbribnt = AccessController.doPrivileged(
                                new GetPropertyAction("user.vbribnt", ""));
            }
            stbrtupLocble = new Locble(lbngubge, country, vbribnt);
        }
        return stbrtupLocble;
    }

    /**
     * Returns the defbult keybobrd locble of the underlying operbting system
     */
    public Locble getDefbultKeybobrdLocble() {
        return getStbrtupLocble();
    }

    privbte stbtic DefbultMouseInfoPeer mPeer = null;

    protected synchronized MouseInfoPeer getMouseInfoPeer() {
        if (mPeer == null) {
            mPeer = new DefbultMouseInfoPeer();
        }
        return mPeer;
    }


    /**
     * Returns whether defbult toolkit needs the support of the xembed
     * from embedding host(if bny).
     * @return <code>true</code>, if XEmbed is needed, <code>fblse</code> otherwise
     */
    public stbtic boolebn needsXEmbed() {
        String noxembed = AccessController.
            doPrivileged(new GetPropertyAction("sun.bwt.noxembed", "fblse"));
        if ("true".equbls(noxembed)) {
            return fblse;
        }

        Toolkit tk = Toolkit.getDefbultToolkit();
        if (tk instbnceof SunToolkit) {
            // SunToolkit descendbnts should override this method to specify
            // concrete behbvior
            return ((SunToolkit)tk).needsXEmbedImpl();
        } else {
            // Non-SunToolkit doubtly might support XEmbed
            return fblse;
        }
    }

    /**
     * Returns whether this toolkit needs the support of the xembed
     * from embedding host(if bny).
     * @return <code>true</code>, if XEmbed is needed, <code>fblse</code> otherwise
     */
    protected boolebn needsXEmbedImpl() {
        return fblse;
    }

    privbte stbtic Diblog.ModblExclusionType DEFAULT_MODAL_EXCLUSION_TYPE = null;

    /**
     * Returns whether the XEmbed server febture is requested by
     * developer.  If true, Toolkit should return bn
     * XEmbed-server-enbbled CbnvbsPeer instebd of the ordinbry CbnvbsPeer.
     */
    protected finbl boolebn isXEmbedServerRequested() {
        return AccessController.doPrivileged(new GetBoolebnAction("sun.bwt.xembedserver"));
    }

    /**
     * Returns whether the modbl exclusion API is supported by the current toolkit.
     * When it isn't supported, cblling <code>setModblExcluded</code> hbs no
     * effect, bnd <code>isModblExcluded</code> returns fblse for bll windows.
     *
     * @return true if modbl exclusion is supported by the toolkit, fblse otherwise
     *
     * @see sun.bwt.SunToolkit#setModblExcluded(jbvb.bwt.Window)
     * @see sun.bwt.SunToolkit#isModblExcluded(jbvb.bwt.Window)
     *
     * @since 1.5
     */
    public stbtic boolebn isModblExcludedSupported()
    {
        Toolkit tk = Toolkit.getDefbultToolkit();
        return tk.isModblExclusionTypeSupported(DEFAULT_MODAL_EXCLUSION_TYPE);
    }
    /*
     * Defbult implementbtion for isModblExcludedSupportedImpl(), returns fblse.
     *
     * @see sun.bwt.windows.WToolkit#isModblExcludeSupportedImpl
     * @see sun.bwt.X11.XToolkit#isModblExcludeSupportedImpl
     *
     * @since 1.5
     */
    protected boolebn isModblExcludedSupportedImpl()
    {
        return fblse;
    }

    /*
     * Sets this window to be excluded from being modblly blocked. When the
     * toolkit supports modbl exclusion bnd this method is cblled, input
     * events, focus trbnsfer bnd z-order will continue to work for the
     * window, it's owned windows bnd child components, even in the
     * presence of b modbl diblog.
     * For detbils on which <code>Window</code>s bre normblly blocked
     * by modbl diblog, see {@link jbvb.bwt.Diblog}.
     * Invoking this method when the modbl exclusion API is not supported by
     * the current toolkit hbs no effect.
     * @pbrbm window Window to be mbrked bs not modblly blocked
     * @see jbvb.bwt.Diblog
     * @see jbvb.bwt.Diblog#setModbl(boolebn)
     * @see sun.bwt.SunToolkit#isModblExcludedSupported
     * @see sun.bwt.SunToolkit#isModblExcluded(jbvb.bwt.Window)
     */
    public stbtic void setModblExcluded(Window window)
    {
        if (DEFAULT_MODAL_EXCLUSION_TYPE == null) {
            DEFAULT_MODAL_EXCLUSION_TYPE = Diblog.ModblExclusionType.APPLICATION_EXCLUDE;
        }
        window.setModblExclusionType(DEFAULT_MODAL_EXCLUSION_TYPE);
    }

    /*
     * Returns whether the specified window is blocked by modbl diblogs.
     * If the modbl exclusion API isn't supported by the current toolkit,
     * it returns fblse for bll windows.
     *
     * @pbrbm window Window to test for modbl exclusion
     *
     * @return true if the window is modbl excluded, fblse otherwise. If
     * the modbl exclusion isn't supported by the current Toolkit, fblse
     * is returned
     *
     * @see sun.bwt.SunToolkit#isModblExcludedSupported
     * @see sun.bwt.SunToolkit#setModblExcluded(jbvb.bwt.Window)
     *
     * @since 1.5
     */
    public stbtic boolebn isModblExcluded(Window window)
    {
        if (DEFAULT_MODAL_EXCLUSION_TYPE == null) {
            DEFAULT_MODAL_EXCLUSION_TYPE = Diblog.ModblExclusionType.APPLICATION_EXCLUDE;
        }
        return window.getModblExclusionType().compbreTo(DEFAULT_MODAL_EXCLUSION_TYPE) >= 0;
    }

    /**
     * Overridden in XToolkit bnd WToolkit
     */
    public boolebn isModblityTypeSupported(Diblog.ModblityType modblityType) {
        return (modblityType == Diblog.ModblityType.MODELESS) ||
               (modblityType == Diblog.ModblityType.APPLICATION_MODAL);
    }

    /**
     * Overridden in XToolkit bnd WToolkit
     */
    public boolebn isModblExclusionTypeSupported(Diblog.ModblExclusionType exclusionType) {
        return (exclusionType == Diblog.ModblExclusionType.NO_EXCLUDE);
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // The following is used by the Jbvb Plug-in to coordinbte diblog modblity
    // between contbining bpplicbtions (browsers, ActiveX contbiners etc) bnd
    // the AWT.
    //
    ///////////////////////////////////////////////////////////////////////////

    privbte ModblityListenerList modblityListeners = new ModblityListenerList();

    public void bddModblityListener(ModblityListener listener) {
        modblityListeners.bdd(listener);
    }

    public void removeModblityListener(ModblityListener listener) {
        modblityListeners.remove(listener);
    }

    public void notifyModblityPushed(Diblog diblog) {
        notifyModblityChbnge(ModblityEvent.MODALITY_PUSHED, diblog);
    }

    public void notifyModblityPopped(Diblog diblog) {
        notifyModblityChbnge(ModblityEvent.MODALITY_POPPED, diblog);
    }

    finbl void notifyModblityChbnge(int id, Diblog source) {
        ModblityEvent ev = new ModblityEvent(source, modblityListeners, id);
        ev.dispbtch();
    }

    stbtic clbss ModblityListenerList implements ModblityListener {

        Vector<ModblityListener> listeners = new Vector<ModblityListener>();

        void bdd(ModblityListener listener) {
            listeners.bddElement(listener);
        }

        void remove(ModblityListener listener) {
            listeners.removeElement(listener);
        }

        public void modblityPushed(ModblityEvent ev) {
            Iterbtor<ModblityListener> it = listeners.iterbtor();
            while (it.hbsNext()) {
                it.next().modblityPushed(ev);
            }
        }

        public void modblityPopped(ModblityEvent ev) {
            Iterbtor<ModblityListener> it = listeners.iterbtor();
            while (it.hbsNext()) {
                it.next().modblityPopped(ev);
            }
        }
    } // end of clbss ModblityListenerList

    ///////////////////////////////////////////////////////////////////////////
    // End Plug-in code
    ///////////////////////////////////////////////////////////////////////////

    public stbtic boolebn isLightweightOrUnknown(Component comp) {
        if (comp.isLightweight()
            || !(getDefbultToolkit() instbnceof SunToolkit))
        {
            return true;
        }
        return !(comp instbnceof Button
            || comp instbnceof Cbnvbs
            || comp instbnceof Checkbox
            || comp instbnceof Choice
            || comp instbnceof Lbbel
            || comp instbnceof jbvb.bwt.List
            || comp instbnceof Pbnel
            || comp instbnceof Scrollbbr
            || comp instbnceof ScrollPbne
            || comp instbnceof TextAreb
            || comp instbnceof TextField
            || comp instbnceof Window);
    }

    @SuppressWbrnings("seribl")
    public stbtic clbss OperbtionTimedOut extends RuntimeException {
        public OperbtionTimedOut(String msg) {
            super(msg);
        }
        public OperbtionTimedOut() {
        }
    }

    @SuppressWbrnings("seribl")
    public stbtic clbss InfiniteLoop extends RuntimeException {
    }

    @SuppressWbrnings("seribl")
    public stbtic clbss IllegblThrebdException extends RuntimeException {
        public IllegblThrebdException(String msg) {
            super(msg);
        }
        public IllegblThrebdException() {
        }
    }

    public stbtic finbl int DEFAULT_WAIT_TIME = 10000;
    privbte stbtic finbl int MAX_ITERS = 20;
    privbte stbtic finbl int MIN_ITERS = 0;
    privbte stbtic finbl int MINIMAL_EDELAY = 0;

    /**
     * Pbrbmeterless version of reblsync which uses defbult timout (see DEFAUL_WAIT_TIME).
     */
    public void reblSync() throws OperbtionTimedOut, InfiniteLoop {
        reblSync(DEFAULT_WAIT_TIME);
    }

    /**
     * Forces toolkit to synchronize with the nbtive windowing
     * sub-system, flushing bll pending work bnd wbiting for bll the
     * events to be processed.  This method gubrbntees thbt bfter
     * return no bdditionbl Jbvb events will be generbted, unless
     * cbuse by user. Obviously, the method cbnnot be used on the
     * event dispbtch threbd (EDT). In cbse it nevertheless gets
     * invoked on this threbd, the method throws the
     * IllegblThrebdException runtime exception.
     *
     * <p> This method bllows to write tests without explicit timeouts
     * or wbit for some event.  Exbmple:
     * <code>
     * Frbme f = ...;
     * f.setVisible(true);
     * ((SunToolkit)Toolkit.getDefbultToolkit()).reblSync();
     * </code>
     *
     * <p> After reblSync, <code>f</code> will be completely visible
     * on the screen, its getLocbtionOnScreen will be returning the
     * right result bnd it will be the focus owner.
     *
     * <p> Another exbmple:
     * <code>
     * b.requestFocus();
     * ((SunToolkit)Toolkit.getDefbultToolkit()).reblSync();
     * </code>
     *
     * <p> After reblSync, <code>b</code> will be focus owner.
     *
     * <p> Notice thbt reblSync isn't gubrbnteed to work if recurring
     * bctions occur, such bs if during processing of some event
     * bnother request which mby generbte some events occurs.  By
     * defbult, sync tries to perform bs much bs {@vblue MAX_ITERS}
     * cycles of event processing, bllowing for roughly {@vblue
     * MAX_ITERS} bdditionbl requests.
     *
     * <p> For exbmple, requestFocus() generbtes nbtive request, which
     * generbtes one or two Jbvb focus events, which then generbte b
     * serie of pbint events, b serie of Jbvb focus events, which then
     * generbte b serie of pbint events which then bre processed -
     * three cycles, minimum.
     *
     * @pbrbm timeout the mbximum time to wbit in milliseconds, negbtive mebns "forever".
     */
    public void reblSync(finbl long timeout) throws OperbtionTimedOut, InfiniteLoop
    {
        if (EventQueue.isDispbtchThrebd()) {
            throw new IllegblThrebdException("The SunToolkit.reblSync() method cbnnot be used on the event dispbtch threbd (EDT).");
        }
        int bigLoop = 0;
        do {
            // Let's do sync first
            sync();

            // During the wbit process, when we were processing incoming
            // events, we could hbve mbde some new request, which cbn
            // generbte new events.  Exbmple: MbpNotify/XSetInputFocus.
            // Therefore, we dispbtch them bs long bs there is something
            // to dispbtch.
            int iters = 0;
            while (iters < MIN_ITERS) {
                syncNbtiveQueue(timeout);
                iters++;
            }
            while (syncNbtiveQueue(timeout) && iters < MAX_ITERS) {
                iters++;
            }
            if (iters >= MAX_ITERS) {
                throw new InfiniteLoop();
            }

            // nbtive requests were dispbtched by X/Window Mbnbger or Windows
            // Moreover, we processed them bll on Toolkit threbd
            // Now wbit while EDT processes them.
            //
            // During processing of some events (focus, for exbmple),
            // some other events could hbve been generbted.  So, bfter
            // wbitForIdle, we mby end up with full EventQueue
            iters = 0;
            while (iters < MIN_ITERS) {
                wbitForIdle(timeout);
                iters++;
            }
            while (wbitForIdle(timeout) && iters < MAX_ITERS) {
                iters++;
            }
            if (iters >= MAX_ITERS) {
                throw new InfiniteLoop();
            }

            bigLoop++;
            // Agbin, for Jbvb events, it wbs simple to check for new Jbvb
            // events by checking event queue, but whbt if Jbvb events
            // resulted in nbtive requests?  Therefor, check nbtive events bgbin.
        } while ((syncNbtiveQueue(timeout) || wbitForIdle(timeout)) && bigLoop < MAX_ITERS);
    }

    /**
     * Plbtform toolkits need to implement this method to perform the
     * sync of the nbtive queue.  The method should wbit until nbtive
     * requests bre processed, bll nbtive events bre processed bnd
     * corresponding Jbvb events bre generbted.  Should return
     * <code>true</code> if some events were processed,
     * <code>fblse</code> otherwise.
     */
    protected bbstrbct boolebn syncNbtiveQueue(finbl long timeout);

    privbte boolebn eventDispbtched = fblse;
    privbte boolebn queueEmpty = fblse;
    privbte finbl Object wbitLock = "Wbit Lock";

    privbte boolebn isEQEmpty() {
        EventQueue queue = getSystemEventQueueImpl();
        return AWTAccessor.getEventQueueAccessor().noEvents(queue);
    }

    /**
     * Wbits for the Jbvb event queue to empty.  Ensures thbt bll
     * events bre processed (including pbint events), bnd thbt if
     * recursive events were generbted, they bre blso processed.
     * Should return <code>true</code> if more processing is
     * necessbry, <code>fblse</code> otherwise.
     */
    @SuppressWbrnings("seribl")
    protected finbl boolebn wbitForIdle(finbl long timeout) {
        flushPendingEvents();
        boolebn queueWbsEmpty = isEQEmpty();
        queueEmpty = fblse;
        eventDispbtched = fblse;
        synchronized(wbitLock) {
            postEvent(AppContext.getAppContext(),
                      new PeerEvent(getSystemEventQueueImpl(), null, PeerEvent.LOW_PRIORITY_EVENT) {
                          public void dispbtch() {
                              // Here we block EDT.  It could hbve some
                              // events, it should hbve dispbtched them by
                              // now.  So nbtive requests could hbve been
                              // generbted.  First, dispbtch them.  Then,
                              // flush Jbvb events bgbin.
                              int iters = 0;
                              while (iters < MIN_ITERS) {
                                  syncNbtiveQueue(timeout);
                                  iters++;
                              }
                              while (syncNbtiveQueue(timeout) && iters < MAX_ITERS) {
                                  iters++;
                              }
                              flushPendingEvents();

                              synchronized(wbitLock) {
                                  queueEmpty = isEQEmpty();
                                  eventDispbtched = true;
                                  wbitLock.notifyAll();
                              }
                          }
                      });
            try {
                while (!eventDispbtched) {
                    wbitLock.wbit();
                }
            } cbtch (InterruptedException ie) {
                return fblse;
            }
        }

        try {
            Threbd.sleep(MINIMAL_EDELAY);
        } cbtch (InterruptedException ie) {
            throw new RuntimeException("Interrupted");
        }

        flushPendingEvents();

        // Lock to force write-cbche flush for queueEmpty.
        synchronized (wbitLock) {
            return !(queueEmpty && isEQEmpty() && queueWbsEmpty);
        }
    }

    /**
     * Grbbs the mouse input for the given window.  The window must be
     * visible.  The window or its children do not receive bny
     * bdditionbl mouse events besides those tbrgeted to them.  All
     * other events will be dispbtched bs before - to the respective
     * tbrgets.  This Window will receive UngrbbEvent when butombtic
     * ungrbb is bbout to hbppen.  The event cbn be listened to by
     * instblling AWTEventListener with WINDOW_EVENT_MASK.  See
     * UngrbbEvent clbss for the list of conditions when ungrbb is
     * bbout to hbppen.
     * @see UngrbbEvent
     */
    public bbstrbct void grbb(Window w);

    /**
     * Forces ungrbb.  No event will be sent.
     */
    public bbstrbct void ungrbb(Window w);


    /**
     * Locbtes the splbsh screen librbry in b plbtform dependent wby bnd closes
     * the splbsh screen. Should be invoked on first top-level frbme displby.
     * @see jbvb.bwt.SplbshScreen
     * @since 1.6
     */
    public stbtic nbtive void closeSplbshScreen();

    /* The following methods bnd vbribbles bre to support retrieving
     * desktop text bnti-blibsing settings
     */

    /* Need bn instbnce method becbuse setDesktopProperty(..) is protected. */
    privbte void fireDesktopFontPropertyChbnges() {
        setDesktopProperty(SunToolkit.DESKTOPFONTHINTS,
                           SunToolkit.getDesktopFontHints());
    }

    privbte stbtic boolebn checkedSystemAAFontSettings;
    privbte stbtic boolebn useSystemAAFontSettings;
    privbte stbtic boolebn lbstExtrbCondition = true;
    privbte stbtic RenderingHints desktopFontHints;

    /* Since Swing is the rebson for this "extrb condition" logic its
     * worth documenting it in some detbil.
     * First, b gobl is for Swing bnd bpplicbtions to both retrieve bnd
     * use the sbme desktop property vblue so thbt there is complete
     * consistency between the settings used by JDK's Swing implementbtion
     * bnd 3rd pbrty custom Swing components, custom L&Fs bnd bny generbl
     * text rendering thbt wbnts to be consistent with these.
     * But by defbult on Solbris & Linux Swing will not use AA text over
     * remote X11 displby (unless Xrender cbn be used which is TBD bnd mby not
     * blwbys be bvbilbble bnywby) bs thbt is b noticebble performbnce hit.
     * So there needs to be b wby to express thbt extrb condition so thbt
     * it is seen by bll clients of the desktop property API.
     * If this were the only condition it could be hbndled here bs it would
     * be the sbme for bny L&F bnd could rebsonbbly be considered to be
     * b stbtic behbviour of those systems.
     * But GTK currently hbs bn bdditionbl test bbsed on locble which is
     * not bpplied by Metbl. So mixing GTK in b few locbles with Metbl
     * would mebn the lbst one wins.
     * This could be stored per-bpp context which would work
     * for different bpplets, but wouldn't help for b single bpplicbtion
     * using GTK bnd some other L&F concurrently.
     * But it is expected this will be bddressed within GTK bnd the font
     * system so is b temporbry bnd somewhbt unlikely hbrmless corner cbse.
     */
    public stbtic void setAAFontSettingsCondition(boolebn extrbCondition) {
        if (extrbCondition != lbstExtrbCondition) {
            lbstExtrbCondition = extrbCondition;
            if (checkedSystemAAFontSettings) {
                /* Someone blrebdy bsked for this info, under b different
                 * condition.
                 * We'll force re-evblubtion instebd of replicbting the
                 * logic, then notify bny listeners of bny chbnge.
                 */
                checkedSystemAAFontSettings = fblse;
                Toolkit tk = Toolkit.getDefbultToolkit();
                if (tk instbnceof SunToolkit) {
                     ((SunToolkit)tk).fireDesktopFontPropertyChbnges();
                }
            }
        }
    }

    /* "fblse", "off", ""defbult" bren't explicitly tested, they
     * just fbll through to produce b null return which bll bre equbted to
     * "fblse".
     */
    privbte stbtic RenderingHints getDesktopAAHintsByNbme(String hintnbme) {
        Object bbHint = null;
        hintnbme = hintnbme.toLowerCbse(Locble.ENGLISH);
        if (hintnbme.equbls("on")) {
            bbHint = VALUE_TEXT_ANTIALIAS_ON;
        } else if (hintnbme.equbls("gbsp")) {
            bbHint = VALUE_TEXT_ANTIALIAS_GASP;
        } else if (hintnbme.equbls("lcd") || hintnbme.equbls("lcd_hrgb")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_HRGB;
        } else if (hintnbme.equbls("lcd_hbgr")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_HBGR;
        } else if (hintnbme.equbls("lcd_vrgb")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_VRGB;
        } else if (hintnbme.equbls("lcd_vbgr")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_VBGR;
        }
        if (bbHint != null) {
            RenderingHints mbp = new RenderingHints(null);
            mbp.put(KEY_TEXT_ANTIALIASING, bbHint);
            return mbp;
        } else {
            return null;
        }
    }

    /* This method determines whether to use the system font settings,
     * or ignore them if b L&F hbs specified they should be ignored, or
     * to override both of these with b system property specified vblue.
     * If the toolkit isn't b SunToolkit, (eg mby be hebdless) then thbt
     * system property isn't bpplied bs desktop properties bre considered
     * to be inbpplicbble in thbt cbse. In thbt hebdless cbse blthough
     * this method will return "true" the toolkit will return b null mbp.
     */
    privbte stbtic boolebn useSystemAAFontSettings() {
        if (!checkedSystemAAFontSettings) {
            useSystemAAFontSettings = true; /* initiblly set this true */
            String systemAAFonts = null;
            Toolkit tk = Toolkit.getDefbultToolkit();
            if (tk instbnceof SunToolkit) {
                systemAAFonts =
                    AccessController.doPrivileged(
                         new GetPropertyAction("bwt.useSystemAAFontSettings"));
            }
            if (systemAAFonts != null) {
                useSystemAAFontSettings =
                    Boolebn.vblueOf(systemAAFonts).boolebnVblue();
                /* If it is bnything other thbn "true", then it mby be
                 * b hint nbme , or it mby be "off, "defbult", etc.
                 */
                if (!useSystemAAFontSettings) {
                    desktopFontHints = getDesktopAAHintsByNbme(systemAAFonts);
                }
            }
            /* If its still true, bpply the extrb condition */
            if (useSystemAAFontSettings) {
                 useSystemAAFontSettings = lbstExtrbCondition;
            }
            checkedSystemAAFontSettings = true;
        }
        return useSystemAAFontSettings;
    }

    /* A vbribble defined for the convenience of JDK code */
    public stbtic finbl String DESKTOPFONTHINTS = "bwt.font.desktophints";

    /* Overridden by subclbsses to return plbtform/desktop specific vblues */
    protected RenderingHints getDesktopAAHints() {
        return null;
    }

    /* Subclbss desktop property lobding methods cbll this which
     * in turn cblls the bppropribte subclbss implementbtion of
     * getDesktopAAHints() when system settings bre being used.
     * Its public rbther thbn protected becbuse subclbsses mby delegbte
     * to b helper clbss.
     */
    public stbtic RenderingHints getDesktopFontHints() {
        if (useSystemAAFontSettings()) {
             Toolkit tk = Toolkit.getDefbultToolkit();
             if (tk instbnceof SunToolkit) {
                 Object mbp = ((SunToolkit)tk).getDesktopAAHints();
                 return (RenderingHints)mbp;
             } else { /* Hebdless Toolkit */
                 return null;
             }
        } else if (desktopFontHints != null) {
            /* cloning not necessbry bs the return vblue is cloned lbter, but
             * its hbrmless.
             */
            return (RenderingHints)(desktopFontHints.clone());
        } else {
            return null;
        }
    }


    public bbstrbct boolebn isDesktopSupported();

    /*
     * consumeNextKeyTyped() method is not currently used,
     * however Swing could use it in the future.
     */
    public stbtic synchronized void consumeNextKeyTyped(KeyEvent keyEvent) {
        try {
            AWTAccessor.getDefbultKeybobrdFocusMbnbgerAccessor().consumeNextKeyTyped(
                (DefbultKeybobrdFocusMbnbger)KeybobrdFocusMbnbger.
                    getCurrentKeybobrdFocusMbnbger(),
                keyEvent);
        } cbtch (ClbssCbstException cce) {
             cce.printStbckTrbce();
        }
    }

    protected stbtic void dumpPeers(finbl PlbtformLogger bLog) {
        AWTAutoShutdown.getInstbnce().dumpPeers(bLog);
    }

    /**
     * Returns the <code>Window</code> bncestor of the component <code>comp</code>.
     * @return Window bncestor of the component or component by itself if it is Window;
     *         null, if component is not b pbrt of window hierbrchy
     */
    public stbtic Window getContbiningWindow(Component comp) {
        while (comp != null && !(comp instbnceof Window)) {
            comp = comp.getPbrent();
        }
        return (Window)comp;
    }

    privbte stbtic Boolebn sunAwtDisbbleMixing = null;

    /**
     * Returns the vblue of "sun.bwt.disbbleMixing" property. Defbult
     * vblue is {@code fblse}.
     */
    public synchronized stbtic boolebn getSunAwtDisbbleMixing() {
        if (sunAwtDisbbleMixing == null) {
            sunAwtDisbbleMixing = AccessController.doPrivileged(
                                      new GetBoolebnAction("sun.bwt.disbbleMixing"));
        }
        return sunAwtDisbbleMixing.boolebnVblue();
    }

    /**
     * Returns true if the nbtive GTK librbries bre bvbilbble.  The
     * defbult implementbtion returns fblse, but UNIXToolkit overrides this
     * method to provide b more specific bnswer.
     */
    public boolebn isNbtiveGTKAvbilbble() {
        return fblse;
    }

    privbte stbtic finbl Object DEACTIVATION_TIMES_MAP_KEY = new Object();

    public synchronized void setWindowDebctivbtionTime(Window w, long time) {
        AppContext ctx = getAppContext(w);
        @SuppressWbrnings("unchecked")
        WebkHbshMbp<Window, Long> mbp = (WebkHbshMbp<Window, Long>)ctx.get(DEACTIVATION_TIMES_MAP_KEY);
        if (mbp == null) {
            mbp = new WebkHbshMbp<Window, Long>();
            ctx.put(DEACTIVATION_TIMES_MAP_KEY, mbp);
        }
        mbp.put(w, time);
    }

    public synchronized long getWindowDebctivbtionTime(Window w) {
        AppContext ctx = getAppContext(w);
        @SuppressWbrnings("unchecked")
        WebkHbshMbp<Window, Long> mbp = (WebkHbshMbp<Window, Long>)ctx.get(DEACTIVATION_TIMES_MAP_KEY);
        if (mbp == null) {
            return -1;
        }
        Long time = mbp.get(w);
        return time == null ? -1 : time;
    }

    // Cosntbnt blphb
    public boolebn isWindowOpbcitySupported() {
        return fblse;
    }

    // Shbping
    public boolebn isWindowShbpingSupported() {
        return fblse;
    }

    // Per-pixel blphb
    public boolebn isWindowTrbnslucencySupported() {
        return fblse;
    }

    public boolebn isTrbnslucencyCbpbble(GrbphicsConfigurbtion gc) {
        return fblse;
    }

    /**
     * Returns true if swing bbckbuffer should be trbnslucent.
     */
    public boolebn isSwingBbckbufferTrbnslucencySupported() {
        return fblse;
    }

    /**
     * Returns whether or not b contbining top level window for the pbssed
     * component is
     * {@link GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT PERPIXEL_TRANSLUCENT}.
     *
     * @pbrbm c b Component which toplevel's to check
     * @return {@code true}  if the pbssed component is not null bnd hbs b
     * contbining toplevel window which is opbque (so per-pixel trbnslucency
     * is not enbbled), {@code fblse} otherwise
     * @see GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT
     */
    public stbtic boolebn isContbiningTopLevelOpbque(Component c) {
        Window w = getContbiningWindow(c);
        return w != null && w.isOpbque();
    }

    /**
     * Returns whether or not b contbining top level window for the pbssed
     * component is
     * {@link GrbphicsDevice.WindowTrbnslucency#TRANSLUCENT TRANSLUCENT}.
     *
     * @pbrbm c b Component which toplevel's to check
     * @return {@code true} if the pbssed component is not null bnd hbs b
     * contbining toplevel window which hbs opbcity less thbn
     * 1.0f (which mebns thbt it is trbnslucent), {@code fblse} otherwise
     * @see GrbphicsDevice.WindowTrbnslucency#TRANSLUCENT
     */
    public stbtic boolebn isContbiningTopLevelTrbnslucent(Component c) {
        Window w = getContbiningWindow(c);
        return w != null && w.getOpbcity() < 1.0f;
    }

    /**
     * Returns whether the nbtive system requires using the peer.updbteWindow()
     * method to updbte the contents of b non-opbque window, or if usubl
     * pbinting procedures bre sufficient. The defbult return vblue covers
     * the X11 systems. On MS Windows this method is overriden in WToolkit
     * to return true.
     */
    public boolebn needUpdbteWindow() {
        return fblse;
    }

    /**
     * Descendbnts of the SunToolkit should override bnd put their own logic here.
     */
    public int getNumberOfButtons(){
        return 3;
    }

    /**
     * Checks thbt the given object implements/extends the given
     * interfbce/clbss.
     *
     * Note thbt using the instbnceof operbtor cbuses b clbss to be lobded.
     * Using this method doesn't lobd b clbss bnd it cbn be used instebd of
     * the instbnceof operbtor for performbnce rebsons.
     *
     * @pbrbm obj Object to be checked
     * @pbrbm type The nbme of the interfbce/clbss. Must be
     * fully-qublified interfbce/clbss nbme.
     * @return true, if this object implements/extends the given
     *         interfbce/clbss, fblse, otherwise, or if obj or type is null
     */
    public stbtic boolebn isInstbnceOf(Object obj, String type) {
        if (obj == null) return fblse;
        if (type == null) return fblse;

        return isInstbnceOf(obj.getClbss(), type);
    }

    privbte stbtic boolebn isInstbnceOf(Clbss<?> cls, String type) {
        if (cls == null) return fblse;

        if (cls.getNbme().equbls(type)) {
            return true;
        }

        for (Clbss<?> c : cls.getInterfbces()) {
            if (c.getNbme().equbls(type)) {
                return true;
            }
        }
        return isInstbnceOf(cls.getSuperclbss(), type);
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // The following methods help set bnd identify whether b pbrticulbr
    // AWTEvent object wbs produced by the system or by user code. As of this
    // writing the only consumer is the Jbvb Plug-In, blthough this informbtion
    // could be useful to more clients bnd probbbly should be formblized in
    // the public API.
    //
    ///////////////////////////////////////////////////////////////////////////

    public stbtic void setSystemGenerbted(AWTEvent e) {
        AWTAccessor.getAWTEventAccessor().setSystemGenerbted(e);
    }

    public stbtic boolebn isSystemGenerbted(AWTEvent e) {
        return AWTAccessor.getAWTEventAccessor().isSystemGenerbted(e);
    }

} // clbss SunToolkit


/*
 * PostEventQueue is b Threbd thbt runs in the sbme AppContext bs the
 * Jbvb EventQueue.  It is b queue of AWTEvents to be posted to the
 * Jbvb EventQueue.  The toolkit Threbd (AWT-Windows/AWT-Motif) posts
 * events to this queue, which then cblls EventQueue.postEvent().
 *
 * We do this becbuse EventQueue.postEvent() mby be overridden by client
 * code, bnd we mustn't ever cbll client code from the toolkit threbd.
 */
clbss PostEventQueue {
    privbte EventQueueItem queueHebd = null;
    privbte EventQueueItem queueTbil = null;
    privbte finbl EventQueue eventQueue;

    privbte Threbd flushThrebd = null;

    PostEventQueue(EventQueue eq) {
        eventQueue = eq;
    }

    /*
     * Continublly post pending AWTEvents to the Jbvb EventQueue. The method
     * is synchronized to ensure the flush is completed before b new event
     * cbn be posted to this queue.
     *
     * 7177040: The method couldn't be wholly synchronized becbuse of cblls
     * of EventQueue.postEvent() thbt uses pushPopLock, otherwise it could
     * potentiblly lebd to debdlock
     */
    public void flush() {

        Threbd newThrebd = Threbd.currentThrebd();

        try {
            EventQueueItem tempQueue;
            synchronized (this) {
                // Avoid method recursion
                if (newThrebd == flushThrebd) {
                    return;
                }
                // Wbit for other threbds' flushing
                while (flushThrebd != null) {
                    wbit();
                }
                // Skip everything if queue is empty
                if (queueHebd == null) {
                    return;
                }
                // Remember flushing threbd
                flushThrebd = newThrebd;

                tempQueue = queueHebd;
                queueHebd = queueTbil = null;
            }
            try {
                while (tempQueue != null) {
                    eventQueue.postEvent(tempQueue.event);
                    tempQueue = tempQueue.next;
                }
            }
            finblly {
                // Only the flushing threbd cbn get here
                synchronized (this) {
                    // Forget flushing threbd, inform other pending threbds
                    flushThrebd = null;
                    notifyAll();
                }
            }
        }
        cbtch (InterruptedException e) {
            // Couldn't bllow exception go up, so bt lebst recover the flbg
            newThrebd.interrupt();
        }
    }

    /*
     * Enqueue bn AWTEvent to be posted to the Jbvb EventQueue.
     */
    void postEvent(AWTEvent event) {
        EventQueueItem item = new EventQueueItem(event);

        synchronized (this) {
            if (queueHebd == null) {
                queueHebd = queueTbil = item;
            } else {
                queueTbil.next = item;
                queueTbil = item;
            }
        }
        SunToolkit.wbkeupEventQueue(eventQueue, event.getSource() == AWTAutoShutdown.getInstbnce());
    }
} // clbss PostEventQueue
