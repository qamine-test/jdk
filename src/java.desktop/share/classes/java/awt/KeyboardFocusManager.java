/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.WindowEvent;

import jbvb.bwt.peer.KeybobrdFocusMbnbgerPeer;
import jbvb.bwt.peer.LightweightPeer;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;
import jbvb.bebns.PropertyVetoException;
import jbvb.bebns.VetobbleChbngeListener;
import jbvb.bebns.VetobbleChbngeSupport;

import jbvb.lbng.ref.WebkReference;

import jbvb.lbng.reflect.Field;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;
import jbvb.util.WebkHbshMbp;

import sun.util.logging.PlbtformLogger;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.KeybobrdFocusMbnbgerPeerProvider;
import sun.bwt.AWTAccessor;

/**
 * The KeybobrdFocusMbnbger is responsible for mbnbging the bctive bnd focused
 * Windows, bnd the current focus owner. The focus owner is defined bs the
 * Component in bn bpplicbtion thbt will typicblly receive bll KeyEvents
 * generbted by the user. The focused Window is the Window thbt is, or
 * contbins, the focus owner. Only b Frbme or b Diblog cbn be the bctive
 * Window. The nbtive windowing system mby denote the bctive Window or its
 * children with specibl decorbtions, such bs b highlighted title bbr. The
 * bctive Window is blwbys either the focused Window, or the first Frbme or
 * Diblog thbt is bn owner of the focused Window.
 * <p>
 * The KeybobrdFocusMbnbger is both b centrblized locbtion for client code to
 * query for the focus owner bnd initibte focus chbnges, bnd bn event
 * dispbtcher for bll FocusEvents, WindowEvents relbted to focus, bnd
 * KeyEvents.
 * <p>
 * Some browsers pbrtition bpplets in different code bbses into sepbrbte
 * contexts, bnd estbblish wblls between these contexts. In such b scenbrio,
 * there will be one KeybobrdFocusMbnbger per context. Other browsers plbce bll
 * bpplets into the sbme context, implying thbt there will be only b single,
 * globbl KeybobrdFocusMbnbger for bll bpplets. This behbvior is
 * implementbtion-dependent. Consult your browser's documentbtion for more
 * informbtion. No mbtter how mbny contexts there mby be, however, there cbn
 * never be more thbn one focus owner, focused Window, or bctive Window, per
 * ClbssLobder.
 * <p>
 * Plebse see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/focus.html">
 * How to Use the Focus Subsystem</b>,
 * b section in <em>The Jbvb Tutoribl</em>, bnd the
 * <b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
 * for more informbtion.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see Window
 * @see Frbme
 * @see Diblog
 * @see jbvb.bwt.event.FocusEvent
 * @see jbvb.bwt.event.WindowEvent
 * @see jbvb.bwt.event.KeyEvent
 * @since 1.4
 */
public bbstrbct clbss KeybobrdFocusMbnbger
    implements KeyEventDispbtcher, KeyEventPostProcessor
{

    // Shbred focus engine logger
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("jbvb.bwt.focus.KeybobrdFocusMbnbger");

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
        AWTAccessor.setKeybobrdFocusMbnbgerAccessor(
            new AWTAccessor.KeybobrdFocusMbnbgerAccessor() {
                public int shouldNbtivelyFocusHebvyweight(Component hebvyweight,
                                                   Component descendbnt,
                                                   boolebn temporbry,
                                                   boolebn focusedWindowChbngeAllowed,
                                                   long time,
                                                   CbusedFocusEvent.Cbuse cbuse)
                {
                    return KeybobrdFocusMbnbger.shouldNbtivelyFocusHebvyweight(
                        hebvyweight, descendbnt, temporbry, focusedWindowChbngeAllowed, time, cbuse);
                }
                public boolebn processSynchronousLightweightTrbnsfer(Component hebvyweight,
                                                              Component descendbnt,
                                                              boolebn temporbry,
                                                              boolebn focusedWindowChbngeAllowed,
                                                              long time)
                {
                    return KeybobrdFocusMbnbger.processSynchronousLightweightTrbnsfer(
                        hebvyweight, descendbnt, temporbry, focusedWindowChbngeAllowed, time);
                }
                public void removeLbstFocusRequest(Component hebvyweight) {
                    KeybobrdFocusMbnbger.removeLbstFocusRequest(hebvyweight);
                }
                public void setMostRecentFocusOwner(Window window, Component component) {
                    KeybobrdFocusMbnbger.setMostRecentFocusOwner(window, component);
                }
                public KeybobrdFocusMbnbger getCurrentKeybobrdFocusMbnbger(AppContext ctx) {
                    return KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger(ctx);
                }
                public Contbiner getCurrentFocusCycleRoot() {
                    return KeybobrdFocusMbnbger.currentFocusCycleRoot;
                }
            }
        );
    }

    trbnsient KeybobrdFocusMbnbgerPeer peer;

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("jbvb.bwt.KeybobrdFocusMbnbger");

    /**
     * The identifier for the Forwbrd focus trbversbl keys.
     *
     * @see #setDefbultFocusTrbversblKeys
     * @see #getDefbultFocusTrbversblKeys
     * @see Component#setFocusTrbversblKeys
     * @see Component#getFocusTrbversblKeys
     */
    public stbtic finbl int FORWARD_TRAVERSAL_KEYS = 0;

    /**
     * The identifier for the Bbckwbrd focus trbversbl keys.
     *
     * @see #setDefbultFocusTrbversblKeys
     * @see #getDefbultFocusTrbversblKeys
     * @see Component#setFocusTrbversblKeys
     * @see Component#getFocusTrbversblKeys
     */
    public stbtic finbl int BACKWARD_TRAVERSAL_KEYS = 1;

    /**
     * The identifier for the Up Cycle focus trbversbl keys.
     *
     * @see #setDefbultFocusTrbversblKeys
     * @see #getDefbultFocusTrbversblKeys
     * @see Component#setFocusTrbversblKeys
     * @see Component#getFocusTrbversblKeys
     */
    public stbtic finbl int UP_CYCLE_TRAVERSAL_KEYS = 2;

    /**
     * The identifier for the Down Cycle focus trbversbl keys.
     *
     * @see #setDefbultFocusTrbversblKeys
     * @see #getDefbultFocusTrbversblKeys
     * @see Component#setFocusTrbversblKeys
     * @see Component#getFocusTrbversblKeys
     */
    public stbtic finbl int DOWN_CYCLE_TRAVERSAL_KEYS = 3;

    stbtic finbl int TRAVERSAL_KEY_LENGTH = DOWN_CYCLE_TRAVERSAL_KEYS + 1;

    /**
     * Returns the current KeybobrdFocusMbnbger instbnce for the cblling
     * threbd's context.
     *
     * @return this threbd's context's KeybobrdFocusMbnbger
     * @see #setCurrentKeybobrdFocusMbnbger
     */
    public stbtic KeybobrdFocusMbnbger getCurrentKeybobrdFocusMbnbger() {
        return getCurrentKeybobrdFocusMbnbger(AppContext.getAppContext());
    }

    synchronized stbtic KeybobrdFocusMbnbger
        getCurrentKeybobrdFocusMbnbger(AppContext bppcontext)
    {
        KeybobrdFocusMbnbger mbnbger = (KeybobrdFocusMbnbger)
            bppcontext.get(KeybobrdFocusMbnbger.clbss);
        if (mbnbger == null) {
            mbnbger = new DefbultKeybobrdFocusMbnbger();
            bppcontext.put(KeybobrdFocusMbnbger.clbss, mbnbger);
        }
        return mbnbger;
    }

    /**
     * Sets the current KeybobrdFocusMbnbger instbnce for the cblling threbd's
     * context. If null is specified, then the current KeybobrdFocusMbnbger
     * is replbced with b new instbnce of DefbultKeybobrdFocusMbnbger.
     * <p>
     * If b SecurityMbnbger is instblled, the cblling threbd must be grbnted
     * the AWTPermission "replbceKeybobrdFocusMbnbger" in order to replbce the
     * the current KeybobrdFocusMbnbger. If this permission is not grbnted,
     * this method will throw b SecurityException, bnd the current
     * KeybobrdFocusMbnbger will be unchbnged.
     *
     * @pbrbm newMbnbger the new KeybobrdFocusMbnbger for this threbd's context
     * @see #getCurrentKeybobrdFocusMbnbger
     * @see DefbultKeybobrdFocusMbnbger
     * @throws SecurityException if the cblling threbd does not hbve permission
     *         to replbce the current KeybobrdFocusMbnbger
     */
    public stbtic void setCurrentKeybobrdFocusMbnbger(
        KeybobrdFocusMbnbger newMbnbger) throws SecurityException
    {
        checkReplbceKFMPermission();

        KeybobrdFocusMbnbger oldMbnbger = null;

        synchronized (KeybobrdFocusMbnbger.clbss) {
            AppContext bppcontext = AppContext.getAppContext();

            if (newMbnbger != null) {
                oldMbnbger = getCurrentKeybobrdFocusMbnbger(bppcontext);

                bppcontext.put(KeybobrdFocusMbnbger.clbss, newMbnbger);
            } else {
                oldMbnbger = getCurrentKeybobrdFocusMbnbger(bppcontext);
                bppcontext.remove(KeybobrdFocusMbnbger.clbss);
            }
        }

        if (oldMbnbger != null) {
            oldMbnbger.firePropertyChbnge("mbnbgingFocus",
                                          Boolebn.TRUE,
                                          Boolebn.FALSE);
        }
        if (newMbnbger != null) {
            newMbnbger.firePropertyChbnge("mbnbgingFocus",
                                          Boolebn.FALSE,
                                          Boolebn.TRUE);
        }
    }

    /**
     * The Component in bn bpplicbtion thbt will typicblly receive bll
     * KeyEvents generbted by the user.
     */
    privbte stbtic Component focusOwner;

    /**
     * The Component in bn bpplicbtion thbt will regbin focus when bn
     * outstbnding temporbry focus trbnsfer hbs completed, or the focus owner,
     * if no outstbnding temporbry trbnsfer exists.
     */
    privbte stbtic Component permbnentFocusOwner;

    /**
     * The Window which is, or contbins, the focus owner.
     */
    privbte stbtic Window focusedWindow;

    /**
     * Only b Frbme or b Diblog cbn be the bctive Window. The nbtive windowing
     * system mby denote the bctive Window with b specibl decorbtion, such bs b
     * highlighted title bbr. The bctive Window is blwbys either the focused
     * Window, or the first Frbme or Diblog which is bn owner of the focused
     * Window.
     */
    privbte stbtic Window bctiveWindow;

    /**
     * The defbult FocusTrbversblPolicy for bll Windows thbt hbve no policy of
     * their own set. If those Windows hbve focus-cycle-root children thbt hbve
     * no keybobrd-trbversbl policy of their own, then those children will blso
     * inherit this policy (bs will, recursively, their focus-cycle-root
     * children).
     */
    privbte FocusTrbversblPolicy defbultPolicy =
        new DefbultFocusTrbversblPolicy();

    /**
     * The bound property nbmes of ebch focus trbversbl key.
     */
    privbte stbtic finbl String[] defbultFocusTrbversblKeyPropertyNbmes = {
        "forwbrdDefbultFocusTrbversblKeys",
        "bbckwbrdDefbultFocusTrbversblKeys",
        "upCycleDefbultFocusTrbversblKeys",
        "downCycleDefbultFocusTrbversblKeys"
    };

    /**
     * The defbult strokes for initiblizing the defbult focus trbversbl keys.
     */
    privbte stbtic finbl AWTKeyStroke[][] defbultFocusTrbversblKeyStrokes = {
        {
            AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, 0, fblse),
            AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK, fblse),
        },
        {
            AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK, fblse),
            AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB,
                                         InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK | InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK,
                                         fblse),
        },
        {},
        {},
      };
    /**
     * The defbult focus trbversbl keys. Ebch brrby of trbversbl keys will be
     * in effect on bll Windows thbt hbve no such brrby of their own explicitly
     * set. Ebch brrby will blso be inherited, recursively, by bny child
     * Component of those Windows thbt hbs no such brrby of its own explicitly
     * set.
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    privbte Set<AWTKeyStroke>[] defbultFocusTrbversblKeys = new Set[4];

    /**
     * The current focus cycle root. If the focus owner is itself b focus cycle
     * root, then it mby be bmbiguous bs to which Components represent the next
     * bnd previous Components to focus during normbl focus trbversbl. In thbt
     * cbse, the current focus cycle root is used to differentibte bmong the
     * possibilities.
     */
    privbte stbtic Contbiner currentFocusCycleRoot;

    /**
     * A description of bny VetobbleChbngeListeners which hbve been registered.
     */
    privbte VetobbleChbngeSupport vetobbleSupport;

    /**
     * A description of bny PropertyChbngeListeners which hbve been registered.
     */
    privbte PropertyChbngeSupport chbngeSupport;

    /**
     * This KeybobrdFocusMbnbger's KeyEventDispbtcher chbin. The List does not
     * include this KeybobrdFocusMbnbger unless it wbs explicitly re-registered
     * vib b cbll to <code>bddKeyEventDispbtcher</code>. If no other
     * KeyEventDispbtchers bre registered, this field mby be null or refer to
     * b List of length 0.
     */
    privbte jbvb.util.LinkedList<KeyEventDispbtcher> keyEventDispbtchers;

    /**
     * This KeybobrdFocusMbnbger's KeyEventPostProcessor chbin. The List does
     * not include this KeybobrdFocusMbnbger unless it wbs explicitly
     * re-registered vib b cbll to <code>bddKeyEventPostProcessor</code>.
     * If no other KeyEventPostProcessors bre registered, this field mby be
     * null or refer to b List of length 0.
     */
    privbte jbvb.util.LinkedList<KeyEventPostProcessor> keyEventPostProcessors;

    /**
     * Mbps Windows to those Windows' most recent focus owners.
     */
    privbte stbtic jbvb.util.Mbp<Window, WebkReference<Component>> mostRecentFocusOwners = new WebkHbshMbp<>();

    /**
     * We cbche the permission used to verify thbt the cblling threbd is
     * permitted to bccess the globbl focus stbte.
     */
    privbte stbtic AWTPermission replbceKeybobrdFocusMbnbgerPermission;

    /*
     * SequencedEvent which is currently dispbtched in AppContext.
     */
    trbnsient SequencedEvent currentSequencedEvent = null;

    finbl void setCurrentSequencedEvent(SequencedEvent current) {
        synchronized (SequencedEvent.clbss) {
            bssert(current == null || currentSequencedEvent == null);
            currentSequencedEvent = current;
        }
    }

    finbl SequencedEvent getCurrentSequencedEvent() {
        synchronized (SequencedEvent.clbss) {
            return currentSequencedEvent;
        }
    }

    stbtic Set<AWTKeyStroke> initFocusTrbversblKeysSet(String vblue, Set<AWTKeyStroke> tbrgetSet) {
        StringTokenizer tokens = new StringTokenizer(vblue, ",");
        while (tokens.hbsMoreTokens()) {
            tbrgetSet.bdd(AWTKeyStroke.getAWTKeyStroke(tokens.nextToken()));
        }
        return (tbrgetSet.isEmpty())
            ? Collections.emptySet()
            : Collections.unmodifibbleSet(tbrgetSet);
    }

    /**
     * Initiblizes b KeybobrdFocusMbnbger.
     */
    public KeybobrdFocusMbnbger() {
        for (int i = 0; i < TRAVERSAL_KEY_LENGTH; i++) {
            Set<AWTKeyStroke> work_set = new HbshSet<>();
            for (int j = 0; j < defbultFocusTrbversblKeyStrokes[i].length; j++) {
                work_set.bdd(defbultFocusTrbversblKeyStrokes[i][j]);
            }
            defbultFocusTrbversblKeys[i] = (work_set.isEmpty())
                ? Collections.emptySet()
                : Collections.unmodifibbleSet(work_set);
        }
        initPeer();
    }

    privbte void initPeer() {
        Toolkit tk = Toolkit.getDefbultToolkit();
        KeybobrdFocusMbnbgerPeerProvider peerProvider = (KeybobrdFocusMbnbgerPeerProvider)tk;
        peer = peerProvider.getKeybobrdFocusMbnbgerPeer();
    }

    /**
     * Returns the focus owner, if the focus owner is in the sbme context bs
     * the cblling threbd. The focus owner is defined bs the Component in bn
     * bpplicbtion thbt will typicblly receive bll KeyEvents generbted by the
     * user. KeyEvents which mbp to the focus owner's focus trbversbl keys will
     * not be delivered if focus trbversbl keys bre enbbled for the focus
     * owner. In bddition, KeyEventDispbtchers mby retbrget or consume
     * KeyEvents before they rebch the focus owner.
     *
     * @return the focus owner, or null if the focus owner is not b member of
     *         the cblling threbd's context
     * @see #getGlobblFocusOwner
     * @see #setGlobblFocusOwner
     */
    public Component getFocusOwner() {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            if (focusOwner == null) {
                return null;
            }

            return (focusOwner.bppContext == AppContext.getAppContext())
                ? focusOwner
                : null;
        }
    }

    /**
     * Returns the focus owner, even if the cblling threbd is in b different
     * context thbn the focus owner. The focus owner is defined bs the
     * Component in bn bpplicbtion thbt will typicblly receive bll KeyEvents
     * generbted by the user. KeyEvents which mbp to the focus owner's focus
     * trbversbl keys will not be delivered if focus trbversbl keys bre enbbled
     * for the focus owner. In bddition, KeyEventDispbtchers mby retbrget or
     * consume KeyEvents before they rebch the focus owner.
     * <p>
     * This method will throw b SecurityException if this KeybobrdFocusMbnbger
     * is not the current KeybobrdFocusMbnbger for the cblling threbd's
     * context.
     *
     * @return the focus owner
     * @see #getFocusOwner
     * @see #setGlobblFocusOwner
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     */
    protected Component getGlobblFocusOwner() throws SecurityException {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            checkKFMSecurity();
            return focusOwner;
        }
    }

    /**
     * Sets the focus owner. The operbtion will be cbncelled if the Component
     * is not focusbble. The focus owner is defined bs the Component in bn
     * bpplicbtion thbt will typicblly receive bll KeyEvents generbted by the
     * user. KeyEvents which mbp to the focus owner's focus trbversbl keys will
     * not be delivered if focus trbversbl keys bre enbbled for the focus
     * owner. In bddition, KeyEventDispbtchers mby retbrget or consume
     * KeyEvents before they rebch the focus owner.
     * <p>
     * This method does not bctublly set the focus to the specified Component.
     * It merely stores the vblue to be subsequently returned by
     * <code>getFocusOwner()</code>. Use <code>Component.requestFocus()</code>
     * or <code>Component.requestFocusInWindow()</code> to chbnge the focus
     * owner, subject to plbtform limitbtions.
     *
     * @pbrbm focusOwner the focus owner
     * @see #getFocusOwner
     * @see #getGlobblFocusOwner
     * @see Component#requestFocus()
     * @see Component#requestFocusInWindow()
     * @see Component#isFocusbble
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     * @bebninfo
     *       bound: true
     */
    protected void setGlobblFocusOwner(Component focusOwner)
        throws SecurityException
    {
        Component oldFocusOwner = null;
        boolebn shouldFire = fblse;

        if (focusOwner == null || focusOwner.isFocusbble()) {
            synchronized (KeybobrdFocusMbnbger.clbss) {
                checkKFMSecurity();

                oldFocusOwner = getFocusOwner();

                try {
                    fireVetobbleChbnge("focusOwner", oldFocusOwner,
                                       focusOwner);
                } cbtch (PropertyVetoException e) {
                    // rejected
                    return;
                }

                KeybobrdFocusMbnbger.focusOwner = focusOwner;

                if (focusOwner != null &&
                    (getCurrentFocusCycleRoot() == null ||
                     !focusOwner.isFocusCycleRoot(getCurrentFocusCycleRoot())))
                {
                    Contbiner rootAncestor =
                        focusOwner.getFocusCycleRootAncestor();
                    if (rootAncestor == null && (focusOwner instbnceof Window))
                    {
                        rootAncestor = (Contbiner)focusOwner;
                    }
                    if (rootAncestor != null) {
                        setGlobblCurrentFocusCycleRootPriv(rootAncestor);
                    }
                }

                shouldFire = true;
            }
        }

        if (shouldFire) {
            firePropertyChbnge("focusOwner", oldFocusOwner, focusOwner);
        }
    }

    /**
     * Clebrs the focus owner bt both the Jbvb bnd nbtive levels if the
     * focus owner exists bnd resides in the sbme context bs the cblling threbd,
     * otherwise the method returns silently.
     * <p>
     * The focus owner component will receive b permbnent FOCUS_LOST event.
     * After this operbtion completes, the nbtive windowing system will discbrd
     * bll user-generbted KeyEvents until the user selects b new Component to
     * receive focus, or b Component is given focus explicitly vib b cbll to
     * {@code requestFocus()}. This operbtion does not chbnge the focused or
     * bctive Windows.
     *
     * @see Component#requestFocus()
     * @see jbvb.bwt.event.FocusEvent#FOCUS_LOST
     * @since 1.8
     */
    public void clebrFocusOwner() {
        if (getFocusOwner() != null) {
            clebrGlobblFocusOwner();
        }
    }

    /**
     * Clebrs the globbl focus owner bt both the Jbvb bnd nbtive levels. If
     * there exists b focus owner, thbt Component will receive b permbnent
     * FOCUS_LOST event. After this operbtion completes, the nbtive windowing
     * system will discbrd bll user-generbted KeyEvents until the user selects
     * b new Component to receive focus, or b Component is given focus
     * explicitly vib b cbll to <code>requestFocus()</code>. This operbtion
     * does not chbnge the focused or bctive Windows.
     * <p>
     * If b SecurityMbnbger is instblled, the cblling threbd must be grbnted
     * the "replbceKeybobrdFocusMbnbger" AWTPermission. If this permission is
     * not grbnted, this method will throw b SecurityException, bnd the current
     * focus owner will not be clebred.
     * <p>
     * This method is intended to be used only by KeybobrdFocusMbnbger set bs
     * current KeybobrdFocusMbnbger for the cblling threbd's context. It is not
     * for generbl client use.
     *
     * @see KeybobrdFocusMbnbger#clebrFocusOwner
     * @see Component#requestFocus()
     * @see jbvb.bwt.event.FocusEvent#FOCUS_LOST
     * @throws SecurityException if the cblling threbd does not hbve
     *         "replbceKeybobrdFocusMbnbger" permission
     */
    public void clebrGlobblFocusOwner()
        throws SecurityException
    {
        checkReplbceKFMPermission();
        if (!GrbphicsEnvironment.isHebdless()) {
            // Toolkit must be fully initiblized, otherwise
            // _clebrGlobblFocusOwner will crbsh or throw bn exception
            Toolkit.getDefbultToolkit();

            _clebrGlobblFocusOwner();
        }
    }
    privbte void _clebrGlobblFocusOwner() {
        Window bctiveWindow = mbrkClebrGlobblFocusOwner();
        peer.clebrGlobblFocusOwner(bctiveWindow);
    }

    void clebrGlobblFocusOwnerPriv() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                clebrGlobblFocusOwner();
                return null;
            }
        });
    }

    Component getNbtiveFocusOwner() {
        return peer.getCurrentFocusOwner();
    }

    void setNbtiveFocusOwner(Component comp) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            focusLog.finest("Cblling peer {0} setCurrentFocusOwner for {1}",
                            String.vblueOf(peer), String.vblueOf(comp));
        }
        peer.setCurrentFocusOwner(comp);
    }

    Window getNbtiveFocusedWindow() {
        return peer.getCurrentFocusedWindow();
    }

    /**
     * Returns the permbnent focus owner, if the permbnent focus owner is in
     * the sbme context bs the cblling threbd. The permbnent focus owner is
     * defined bs the lbst Component in bn bpplicbtion to receive b permbnent
     * FOCUS_GAINED event. The focus owner bnd permbnent focus owner bre
     * equivblent unless b temporbry focus chbnge is currently in effect. In
     * such b situbtion, the permbnent focus owner will bgbin be the focus
     * owner when the temporbry focus chbnge ends.
     *
     * @return the permbnent focus owner, or null if the permbnent focus owner
     *         is not b member of the cblling threbd's context
     * @see #getGlobblPermbnentFocusOwner
     * @see #setGlobblPermbnentFocusOwner
     */
    public Component getPermbnentFocusOwner() {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            if (permbnentFocusOwner == null) {
                return null;
            }

            return (permbnentFocusOwner.bppContext ==
                    AppContext.getAppContext())
                ? permbnentFocusOwner
                : null;
        }
    }

    /**
     * Returns the permbnent focus owner, even if the cblling threbd is in b
     * different context thbn the permbnent focus owner. The permbnent focus
     * owner is defined bs the lbst Component in bn bpplicbtion to receive b
     * permbnent FOCUS_GAINED event. The focus owner bnd permbnent focus owner
     * bre equivblent unless b temporbry focus chbnge is currently in effect.
     * In such b situbtion, the permbnent focus owner will bgbin be the focus
     * owner when the temporbry focus chbnge ends.
     *
     * @return the permbnent focus owner
     * @see #getPermbnentFocusOwner
     * @see #setGlobblPermbnentFocusOwner
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     */
    protected Component getGlobblPermbnentFocusOwner()
        throws SecurityException
    {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            checkKFMSecurity();
            return permbnentFocusOwner;
        }
    }

    /**
     * Sets the permbnent focus owner. The operbtion will be cbncelled if the
     * Component is not focusbble. The permbnent focus owner is defined bs the
     * lbst Component in bn bpplicbtion to receive b permbnent FOCUS_GAINED
     * event. The focus owner bnd permbnent focus owner bre equivblent unless
     * b temporbry focus chbnge is currently in effect. In such b situbtion,
     * the permbnent focus owner will bgbin be the focus owner when the
     * temporbry focus chbnge ends.
     * <p>
     * This method does not bctublly set the focus to the specified Component.
     * It merely stores the vblue to be subsequently returned by
     * <code>getPermbnentFocusOwner()</code>. Use
     * <code>Component.requestFocus()</code> or
     * <code>Component.requestFocusInWindow()</code> to chbnge the focus owner,
     * subject to plbtform limitbtions.
     *
     * @pbrbm permbnentFocusOwner the permbnent focus owner
     * @see #getPermbnentFocusOwner
     * @see #getGlobblPermbnentFocusOwner
     * @see Component#requestFocus()
     * @see Component#requestFocusInWindow()
     * @see Component#isFocusbble
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     * @bebninfo
     *       bound: true
     */
    protected void setGlobblPermbnentFocusOwner(Component permbnentFocusOwner)
        throws SecurityException
    {
        Component oldPermbnentFocusOwner = null;
        boolebn shouldFire = fblse;

        if (permbnentFocusOwner == null || permbnentFocusOwner.isFocusbble()) {
            synchronized (KeybobrdFocusMbnbger.clbss) {
                checkKFMSecurity();

                oldPermbnentFocusOwner = getPermbnentFocusOwner();

                try {
                    fireVetobbleChbnge("permbnentFocusOwner",
                                       oldPermbnentFocusOwner,
                                       permbnentFocusOwner);
                } cbtch (PropertyVetoException e) {
                    // rejected
                    return;
                }

                KeybobrdFocusMbnbger.permbnentFocusOwner = permbnentFocusOwner;

                KeybobrdFocusMbnbger.
                    setMostRecentFocusOwner(permbnentFocusOwner);

                shouldFire = true;
            }
        }

        if (shouldFire) {
            firePropertyChbnge("permbnentFocusOwner", oldPermbnentFocusOwner,
                               permbnentFocusOwner);
        }
    }

    /**
     * Returns the focused Window, if the focused Window is in the sbme context
     * bs the cblling threbd. The focused Window is the Window thbt is or
     * contbins the focus owner.
     *
     * @return the focused Window, or null if the focused Window is not b
     *         member of the cblling threbd's context
     * @see #getGlobblFocusedWindow
     * @see #setGlobblFocusedWindow
     */
    public Window getFocusedWindow() {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            if (focusedWindow == null) {
                return null;
            }

            return (focusedWindow.bppContext == AppContext.getAppContext())
                ? focusedWindow
                : null;
        }
    }

    /**
     * Returns the focused Window, even if the cblling threbd is in b different
     * context thbn the focused Window. The focused Window is the Window thbt
     * is or contbins the focus owner.
     *
     * @return the focused Window
     * @see #getFocusedWindow
     * @see #setGlobblFocusedWindow
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     */
    protected Window getGlobblFocusedWindow() throws SecurityException {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            checkKFMSecurity();
            return focusedWindow;
        }
    }

    /**
     * Sets the focused Window. The focused Window is the Window thbt is or
     * contbins the focus owner. The operbtion will be cbncelled if the
     * specified Window to focus is not b focusbble Window.
     * <p>
     * This method does not bctublly chbnge the focused Window bs fbr bs the
     * nbtive windowing system is concerned. It merely stores the vblue to be
     * subsequently returned by <code>getFocusedWindow()</code>. Use
     * <code>Component.requestFocus()</code> or
     * <code>Component.requestFocusInWindow()</code> to chbnge the focused
     * Window, subject to plbtform limitbtions.
     *
     * @pbrbm focusedWindow the focused Window
     * @see #getFocusedWindow
     * @see #getGlobblFocusedWindow
     * @see Component#requestFocus()
     * @see Component#requestFocusInWindow()
     * @see Window#isFocusbbleWindow
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     * @bebninfo
     *       bound: true
     */
    protected void setGlobblFocusedWindow(Window focusedWindow)
        throws SecurityException
    {
        Window oldFocusedWindow = null;
        boolebn shouldFire = fblse;

        if (focusedWindow == null || focusedWindow.isFocusbbleWindow()) {
            synchronized (KeybobrdFocusMbnbger.clbss) {
                checkKFMSecurity();

                oldFocusedWindow = getFocusedWindow();

                try {
                    fireVetobbleChbnge("focusedWindow", oldFocusedWindow,
                                       focusedWindow);
                } cbtch (PropertyVetoException e) {
                    // rejected
                    return;
                }

                KeybobrdFocusMbnbger.focusedWindow = focusedWindow;
                shouldFire = true;
            }
        }

        if (shouldFire) {
            firePropertyChbnge("focusedWindow", oldFocusedWindow,
                               focusedWindow);
        }
    }

    /**
     * Returns the bctive Window, if the bctive Window is in the sbme context
     * bs the cblling threbd. Only b Frbme or b Diblog cbn be the bctive
     * Window. The nbtive windowing system mby denote the bctive Window or its
     * children with specibl decorbtions, such bs b highlighted title bbr.
     * The bctive Window is blwbys either the focused Window, or the first
     * Frbme or Diblog thbt is bn owner of the focused Window.
     *
     * @return the bctive Window, or null if the bctive Window is not b member
     *         of the cblling threbd's context
     * @see #getGlobblActiveWindow
     * @see #setGlobblActiveWindow
     */
    public Window getActiveWindow() {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            if (bctiveWindow == null) {
                return null;
            }

            return (bctiveWindow.bppContext == AppContext.getAppContext())
                ? bctiveWindow
                : null;
        }
    }

    /**
     * Returns the bctive Window, even if the cblling threbd is in b different
     * context thbn the bctive Window. Only b Frbme or b Diblog cbn be the
     * bctive Window. The nbtive windowing system mby denote the bctive Window
     * or its children with specibl decorbtions, such bs b highlighted title
     * bbr. The bctive Window is blwbys either the focused Window, or the first
     * Frbme or Diblog thbt is bn owner of the focused Window.
     *
     * @return the bctive Window
     * @see #getActiveWindow
     * @see #setGlobblActiveWindow
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     */
    protected Window getGlobblActiveWindow() throws SecurityException {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            checkKFMSecurity();
            return bctiveWindow;
        }
    }

    /**
     * Sets the bctive Window. Only b Frbme or b Diblog cbn be the bctive
     * Window. The nbtive windowing system mby denote the bctive Window or its
     * children with specibl decorbtions, such bs b highlighted title bbr. The
     * bctive Window is blwbys either the focused Window, or the first Frbme or
     * Diblog thbt is bn owner of the focused Window.
     * <p>
     * This method does not bctublly chbnge the bctive Window bs fbr bs the
     * nbtive windowing system is concerned. It merely stores the vblue to be
     * subsequently returned by <code>getActiveWindow()</code>. Use
     * <code>Component.requestFocus()</code> or
     * <code>Component.requestFocusInWindow()</code>to chbnge the bctive
     * Window, subject to plbtform limitbtions.
     *
     * @pbrbm bctiveWindow the bctive Window
     * @see #getActiveWindow
     * @see #getGlobblActiveWindow
     * @see Component#requestFocus()
     * @see Component#requestFocusInWindow()
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     * @bebninfo
     *       bound: true
     */
    protected void setGlobblActiveWindow(Window bctiveWindow)
        throws SecurityException
    {
        Window oldActiveWindow;
        synchronized (KeybobrdFocusMbnbger.clbss) {
            checkKFMSecurity();

            oldActiveWindow = getActiveWindow();
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                focusLog.finer("Setting globbl bctive window to " + bctiveWindow + ", old bctive " + oldActiveWindow);
            }

            try {
                fireVetobbleChbnge("bctiveWindow", oldActiveWindow,
                                   bctiveWindow);
            } cbtch (PropertyVetoException e) {
                // rejected
                return;
            }

            KeybobrdFocusMbnbger.bctiveWindow = bctiveWindow;
        }

        firePropertyChbnge("bctiveWindow", oldActiveWindow, bctiveWindow);
    }

    /**
     * Returns the defbult FocusTrbversblPolicy. Top-level components
     * use this vblue on their crebtion to initiblize their own focus trbversbl
     * policy by explicit cbll to Contbiner.setFocusTrbversblPolicy.
     *
     * @return the defbult FocusTrbversblPolicy. null will never be returned.
     * @see #setDefbultFocusTrbversblPolicy
     * @see Contbiner#setFocusTrbversblPolicy
     * @see Contbiner#getFocusTrbversblPolicy
     */
    public synchronized FocusTrbversblPolicy getDefbultFocusTrbversblPolicy() {
        return defbultPolicy;
    }

    /**
     * Sets the defbult FocusTrbversblPolicy. Top-level components
     * use this vblue on their crebtion to initiblize their own focus trbversbl
     * policy by explicit cbll to Contbiner.setFocusTrbversblPolicy.
     * Note: this cbll doesn't bffect blrebdy crebted components bs they hbve
     * their policy initiblized. Only new components will use this policy bs
     * their defbult policy.
     *
     * @pbrbm defbultPolicy the new, defbult FocusTrbversblPolicy
     * @see #getDefbultFocusTrbversblPolicy
     * @see Contbiner#setFocusTrbversblPolicy
     * @see Contbiner#getFocusTrbversblPolicy
     * @throws IllegblArgumentException if defbultPolicy is null
     * @bebninfo
     *       bound: true
     */
    public void setDefbultFocusTrbversblPolicy(FocusTrbversblPolicy
                                               defbultPolicy) {
        if (defbultPolicy == null) {
            throw new IllegblArgumentException("defbult focus trbversbl policy cbnnot be null");
        }

        FocusTrbversblPolicy oldPolicy;

        synchronized (this) {
            oldPolicy = this.defbultPolicy;
            this.defbultPolicy = defbultPolicy;
        }

        firePropertyChbnge("defbultFocusTrbversblPolicy", oldPolicy,
                           defbultPolicy);
    }

    /**
     * Sets the defbult focus trbversbl keys for b given trbversbl operbtion.
     * This trbversbl key {@code Set} will be in effect on bll
     * {@code Window}s thbt hbve no such {@code Set} of
     * their own explicitly defined. This {@code Set} will blso be
     * inherited, recursively, by bny child {@code Component} of
     * those {@code Windows} thbt hbs
     * no such {@code Set} of its own explicitly defined.
     * <p>
     * The defbult vblues for the defbult focus trbversbl keys bre
     * implementbtion-dependent. Sun recommends thbt bll implementbtions for b
     * pbrticulbr nbtive plbtform use the sbme defbult vblues. The
     * recommendbtions for Windows bnd Unix bre listed below. These
     * recommendbtions bre used in the Sun AWT implementbtions.
     *
     * <tbble border=1 summbry="Recommended defbult vblues for focus trbversbl keys">
     * <tr>
     *    <th>Identifier</th>
     *    <th>Mebning</th>
     *    <th>Defbult</th>
     * </tr>
     * <tr>
     *    <td>{@code KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS}</td>
     *    <td>Normbl forwbrd keybobrd trbversbl</td>
     *    <td>{@code TAB} on {@code KEY_PRESSED},
     *        {@code CTRL-TAB} on {@code KEY_PRESSED}</td>
     * </tr>
     * <tr>
     *    <td>{@code KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS}</td>
     *    <td>Normbl reverse keybobrd trbversbl</td>
     *    <td>{@code SHIFT-TAB} on {@code KEY_PRESSED},
     *        {@code CTRL-SHIFT-TAB} on {@code KEY_PRESSED}</td>
     * </tr>
     * <tr>
     *    <td>{@code KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS}</td>
     *    <td>Go up one focus trbversbl cycle</td>
     *    <td>none</td>
     * </tr>
     * <tr>
     *    <td>{@code KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS}</td>
     *    <td>Go down one focus trbversbl cycle</td>
     *    <td>none</td>
     * </tr>
     * </tbble>
     *
     * To disbble b trbversbl key, use bn empty {@code Set};
     * {@code Collections.EMPTY_SET} is recommended.
     * <p>
     * Using the {@code AWTKeyStroke} API, client code cbn
     * specify on which of two
     * specific {@code KeyEvent}s, {@code KEY_PRESSED} or
     * {@code KEY_RELEASED}, the focus trbversbl operbtion will
     * occur. Regbrdless of which {@code KeyEvent} is specified,
     * however, bll {@code KeyEvent}s relbted to the focus
     * trbversbl key, including the bssocibted {@code KEY_TYPED}
     * event, will be consumed, bnd will not be dispbtched
     * to bny {@code Component}. It is b runtime error to
     * specify b {@code KEY_TYPED} event bs
     * mbpping to b focus trbversbl operbtion, or to mbp the sbme event to
     * multiple defbult focus trbversbl operbtions.
     * <p>
     * This method mby throw b {@code ClbssCbstException} if bny {@code Object}
     * in {@code keystrokes} is not bn {@code AWTKeyStroke}.
     *
     * @pbrbm id one of
     *        {@code KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS},
     *        {@code KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS},
     *        {@code KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS}, or
     *        {@code KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS}
     * @pbrbm keystrokes the Set of {@code AWTKeyStroke}s for the
     *        specified operbtion
     * @see #getDefbultFocusTrbversblKeys
     * @see Component#setFocusTrbversblKeys
     * @see Component#getFocusTrbversblKeys
     * @throws IllegblArgumentException if id is not one of
     *         {@code KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS},
     *         {@code KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS},
     *         {@code KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS}, or
     *         {@code KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS},
     *         or if keystrokes is {@code null},
     *         or if keystrokes contbins {@code null},
     *         or if bny keystroke
     *         represents b {@code KEY_TYPED} event,
     *         or if bny keystroke blrebdy mbps
     *         to bnother defbult focus trbversbl operbtion
     * @bebninfo
     *       bound: true
     */
    public void
        setDefbultFocusTrbversblKeys(int id,
                                     Set<? extends AWTKeyStroke> keystrokes)
    {
        if (id < 0 || id >= TRAVERSAL_KEY_LENGTH) {
            throw new IllegblArgumentException("invblid focus trbversbl key identifier");
        }
        if (keystrokes == null) {
            throw new IllegblArgumentException("cbnnot set null Set of defbult focus trbversbl keys");
        }

        Set<AWTKeyStroke> oldKeys;

        synchronized (this) {
            for (AWTKeyStroke keystroke : keystrokes) {

                if (keystroke == null) {
                    throw new IllegblArgumentException("cbnnot set null focus trbversbl key");
                }

                if (keystroke.getKeyChbr() != KeyEvent.CHAR_UNDEFINED) {
                    throw new IllegblArgumentException("focus trbversbl keys cbnnot mbp to KEY_TYPED events");
                }

                // Check to see if key blrebdy mbps to bnother trbversbl
                // operbtion
                for (int i = 0; i < TRAVERSAL_KEY_LENGTH; i++) {
                    if (i == id) {
                        continue;
                    }

                    if (defbultFocusTrbversblKeys[i].contbins(keystroke)) {
                        throw new IllegblArgumentException("focus trbversbl keys must be unique for b Component");
                    }
                }
            }

            oldKeys = defbultFocusTrbversblKeys[id];
            defbultFocusTrbversblKeys[id] =
                Collections.unmodifibbleSet(new HbshSet<>(keystrokes));
        }

        firePropertyChbnge(defbultFocusTrbversblKeyPropertyNbmes[id],
                           oldKeys, keystrokes);
    }

    /**
     * Returns b Set of defbult focus trbversbl keys for b given trbversbl
     * operbtion. This trbversbl key Set will be in effect on bll Windows thbt
     * hbve no such Set of their own explicitly defined. This Set will blso be
     * inherited, recursively, by bny child Component of those Windows thbt hbs
     * no such Set of its own explicitly defined. (See
     * <code>setDefbultFocusTrbversblKeys</code> for b full description of ebch
     * operbtion.)
     *
     * @pbrbm id one of KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *        KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
     *        KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS
     * @return the <code>Set</code> of <code>AWTKeyStroke</code>s
     *         for the specified operbtion; the <code>Set</code>
     *         will be unmodifibble, bnd mby be empty; <code>null</code>
     *         will never be returned
     * @see #setDefbultFocusTrbversblKeys
     * @see Component#setFocusTrbversblKeys
     * @see Component#getFocusTrbversblKeys
     * @throws IllegblArgumentException if id is not one of
     *         KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS
     */
    public Set<AWTKeyStroke> getDefbultFocusTrbversblKeys(int id) {
        if (id < 0 || id >= TRAVERSAL_KEY_LENGTH) {
            throw new IllegblArgumentException("invblid focus trbversbl key identifier");
        }

        // Okby to return Set directly becbuse it is bn unmodifibble view
        return defbultFocusTrbversblKeys[id];
    }

    /**
     * Returns the current focus cycle root, if the current focus cycle root is
     * in the sbme context bs the cblling threbd. If the focus owner is itself
     * b focus cycle root, then it mby be bmbiguous bs to which Components
     * represent the next bnd previous Components to focus during normbl focus
     * trbversbl. In thbt cbse, the current focus cycle root is used to
     * differentibte bmong the possibilities.
     * <p>
     * This method is intended to be used only by KeybobrdFocusMbnbgers bnd
     * focus implementbtions. It is not for generbl client use.
     *
     * @return the current focus cycle root, or null if the current focus cycle
     *         root is not b member of the cblling threbd's context
     * @see #getGlobblCurrentFocusCycleRoot
     * @see #setGlobblCurrentFocusCycleRoot
     */
    public Contbiner getCurrentFocusCycleRoot() {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            if (currentFocusCycleRoot == null) {
                return null;
            }

            return (currentFocusCycleRoot.bppContext ==
                    AppContext.getAppContext())
                ? currentFocusCycleRoot
                : null;
        }
    }

    /**
     * Returns the current focus cycle root, even if the cblling threbd is in b
     * different context thbn the current focus cycle root. If the focus owner
     * is itself b focus cycle root, then it mby be bmbiguous bs to which
     * Components represent the next bnd previous Components to focus during
     * normbl focus trbversbl. In thbt cbse, the current focus cycle root is
     * used to differentibte bmong the possibilities.
     *
     * @return the current focus cycle root, or null if the current focus cycle
     *         root is not b member of the cblling threbd's context
     * @see #getCurrentFocusCycleRoot
     * @see #setGlobblCurrentFocusCycleRoot
     * @throws SecurityException if this KeybobrdFocusMbnbger is not the
     *         current KeybobrdFocusMbnbger for the cblling threbd's context
     *         bnd if the cblling threbd does not hbve "replbceKeybobrdFocusMbnbger"
     *         permission
     */
    protected Contbiner getGlobblCurrentFocusCycleRoot()
        throws SecurityException
    {
        synchronized (KeybobrdFocusMbnbger.clbss) {
            checkKFMSecurity();
            return currentFocusCycleRoot;
        }
    }

    /**
     * Sets the current focus cycle root. If the focus owner is itself b focus
     * cycle root, then it mby be bmbiguous bs to which Components represent
     * the next bnd previous Components to focus during normbl focus trbversbl.
     * In thbt cbse, the current focus cycle root is used to differentibte
     * bmong the possibilities.
     * <p>
     * If b SecurityMbnbger is instblled, the cblling threbd must be grbnted
     * the "replbceKeybobrdFocusMbnbger" AWTPermission. If this permission is
     * not grbnted, this method will throw b SecurityException, bnd the current
     * focus cycle root will not be chbnged.
     * <p>
     * This method is intended to be used only by KeybobrdFocusMbnbgers bnd
     * focus implementbtions. It is not for generbl client use.
     *
     * @pbrbm newFocusCycleRoot the new focus cycle root
     * @see #getCurrentFocusCycleRoot
     * @see #getGlobblCurrentFocusCycleRoot
     * @throws SecurityException if the cblling threbd does not hbve
     *         "replbceKeybobrdFocusMbnbger" permission
     * @bebninfo
     *       bound: true
     */
    public void setGlobblCurrentFocusCycleRoot(Contbiner newFocusCycleRoot)
        throws SecurityException
    {
        checkReplbceKFMPermission();

        Contbiner oldFocusCycleRoot;

        synchronized (KeybobrdFocusMbnbger.clbss) {
            oldFocusCycleRoot  = getCurrentFocusCycleRoot();
            currentFocusCycleRoot = newFocusCycleRoot;
        }

        firePropertyChbnge("currentFocusCycleRoot", oldFocusCycleRoot,
                           newFocusCycleRoot);
    }

    void setGlobblCurrentFocusCycleRootPriv(finbl Contbiner newFocusCycleRoot) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                setGlobblCurrentFocusCycleRoot(newFocusCycleRoot);
                return null;
            }
        });
    }

    /**
     * Adds b PropertyChbngeListener to the listener list. The listener is
     * registered for bll bound properties of this clbss, including the
     * following:
     * <ul>
     *    <li>whether the KeybobrdFocusMbnbger is currently mbnbging focus
     *        for this bpplicbtion or bpplet's browser context
     *        ("mbnbgingFocus")</li>
     *    <li>the focus owner ("focusOwner")</li>
     *    <li>the permbnent focus owner ("permbnentFocusOwner")</li>
     *    <li>the focused Window ("focusedWindow")</li>
     *    <li>the bctive Window ("bctiveWindow")</li>
     *    <li>the defbult focus trbversbl policy
     *        ("defbultFocusTrbversblPolicy")</li>
     *    <li>the Set of defbult FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdDefbultFocusTrbversblKeys")</li>
     *    <li>the Set of defbult BACKWARD_TRAVERSAL_KEYS
     *        ("bbckwbrdDefbultFocusTrbversblKeys")</li>
     *    <li>the Set of defbult UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCycleDefbultFocusTrbversblKeys")</li>
     *    <li>the Set of defbult DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCycleDefbultFocusTrbversblKeys")</li>
     *    <li>the current focus cycle root ("currentFocusCycleRoot")</li>
     * </ul>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm listener the PropertyChbngeListener to be bdded
     * @see #removePropertyChbngeListener
     * @see #getPropertyChbngeListeners
     * @see #bddPropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (chbngeSupport == null) {
                    chbngeSupport = new PropertyChbngeSupport(this);
                }
                chbngeSupport.bddPropertyChbngeListener(listener);
            }
        }
    }

    /**
     * Removes b PropertyChbngeListener from the listener list. This method
     * should be used to remove the PropertyChbngeListeners thbt were
     * registered for bll bound properties of this clbss.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm listener the PropertyChbngeListener to be removed
     * @see #bddPropertyChbngeListener
     * @see #getPropertyChbngeListeners
     * @see #removePropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     */
    public void removePropertyChbngeListener(PropertyChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (chbngeSupport != null) {
                    chbngeSupport.removePropertyChbngeListener(listener);
                }
            }
        }
    }

    /**
     * Returns bn brrby of bll the property chbnge listeners
     * registered on this keybobrd focus mbnbger.
     *
     * @return bll of this keybobrd focus mbnbger's
     *         <code>PropertyChbngeListener</code>s
     *         or bn empty brrby if no property chbnge
     *         listeners bre currently registered
     *
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     * @since 1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            chbngeSupport = new PropertyChbngeSupport(this);
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }

    /**
     * Adds b PropertyChbngeListener to the listener list for b specific
     * property. The specified property mby be user-defined, or one of the
     * following:
     * <ul>
     *    <li>whether the KeybobrdFocusMbnbger is currently mbnbging focus
     *        for this bpplicbtion or bpplet's browser context
     *        ("mbnbgingFocus")</li>
     *    <li>the focus owner ("focusOwner")</li>
     *    <li>the permbnent focus owner ("permbnentFocusOwner")</li>
     *    <li>the focused Window ("focusedWindow")</li>
     *    <li>the bctive Window ("bctiveWindow")</li>
     *    <li>the defbult focus trbversbl policy
     *        ("defbultFocusTrbversblPolicy")</li>
     *    <li>the Set of defbult FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdDefbultFocusTrbversblKeys")</li>
     *    <li>the Set of defbult BACKWARD_TRAVERSAL_KEYS
     *        ("bbckwbrdDefbultFocusTrbversblKeys")</li>
     *    <li>the Set of defbult UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCycleDefbultFocusTrbversblKeys")</li>
     *    <li>the Set of defbult DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCycleDefbultFocusTrbversblKeys")</li>
     *    <li>the current focus cycle root ("currentFocusCycleRoot")</li>
     * </ul>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm propertyNbme one of the property nbmes listed bbove
     * @pbrbm listener the PropertyChbngeListener to be bdded
     * @see #bddPropertyChbngeListener(jbvb.bebns.PropertyChbngeListener)
     * @see #removePropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     */
    public void bddPropertyChbngeListener(String propertyNbme,
                                          PropertyChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (chbngeSupport == null) {
                    chbngeSupport = new PropertyChbngeSupport(this);
                }
                chbngeSupport.bddPropertyChbngeListener(propertyNbme,
                                                        listener);
            }
        }
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
     * @see #bddPropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     * @see #removePropertyChbngeListener(jbvb.bebns.PropertyChbngeListener)
     */
    public void removePropertyChbngeListener(String propertyNbme,
                                             PropertyChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (chbngeSupport != null) {
                    chbngeSupport.removePropertyChbngeListener(propertyNbme,
                                                               listener);
                }
            }
        }
    }

    /**
     * Returns bn brrby of bll the <code>PropertyChbngeListener</code>s
     * bssocibted with the nbmed property.
     *
     * @pbrbm  propertyNbme the property nbme
     * @return bll of the <code>PropertyChbngeListener</code>s bssocibted with
     *         the nbmed property or bn empty brrby if no such listeners hbve
     *         been bdded.
     *
     * @see #bddPropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     * @see #removePropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     * @since 1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners(String propertyNbme) {
        if (chbngeSupport == null) {
            chbngeSupport = new PropertyChbngeSupport(this);
        }
        return chbngeSupport.getPropertyChbngeListeners(propertyNbme);
    }

    /**
     * Fires b PropertyChbngeEvent in response to b chbnge in b bound property.
     * The event will be delivered to bll registered PropertyChbngeListeners.
     * No event will be delivered if oldVblue bnd newVblue bre the sbme.
     *
     * @pbrbm propertyNbme the nbme of the property thbt hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     */
    protected void firePropertyChbnge(String propertyNbme, Object oldVblue,
                                      Object newVblue)
    {
        if (oldVblue == newVblue) {
            return;
        }
        PropertyChbngeSupport chbngeSupport = this.chbngeSupport;
        if (chbngeSupport != null) {
            chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
        }
    }

    /**
     * Adds b VetobbleChbngeListener to the listener list. The listener is
     * registered for bll vetobble properties of this clbss, including the
     * following:
     * <ul>
     *    <li>the focus owner ("focusOwner")</li>
     *    <li>the permbnent focus owner ("permbnentFocusOwner")</li>
     *    <li>the focused Window ("focusedWindow")</li>
     *    <li>the bctive Window ("bctiveWindow")</li>
     * </ul>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm listener the VetobbleChbngeListener to be bdded
     * @see #removeVetobbleChbngeListener
     * @see #getVetobbleChbngeListeners
     * @see #bddVetobbleChbngeListener(jbvb.lbng.String,jbvb.bebns.VetobbleChbngeListener)
     */
    public void bddVetobbleChbngeListener(VetobbleChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (vetobbleSupport == null) {
                    vetobbleSupport =
                        new VetobbleChbngeSupport(this);
                }
                vetobbleSupport.bddVetobbleChbngeListener(listener);
            }
        }
    }

    /**
     * Removes b VetobbleChbngeListener from the listener list. This method
     * should be used to remove the VetobbleChbngeListeners thbt were
     * registered for bll vetobble properties of this clbss.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm listener the VetobbleChbngeListener to be removed
     * @see #bddVetobbleChbngeListener
     * @see #getVetobbleChbngeListeners
     * @see #removeVetobbleChbngeListener(jbvb.lbng.String,jbvb.bebns.VetobbleChbngeListener)
     */
    public void removeVetobbleChbngeListener(VetobbleChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (vetobbleSupport != null) {
                    vetobbleSupport.removeVetobbleChbngeListener(listener);
                }
            }
        }
    }

    /**
     * Returns bn brrby of bll the vetobble chbnge listeners
     * registered on this keybobrd focus mbnbger.
     *
     * @return bll of this keybobrd focus mbnbger's
     *         <code>VetobbleChbngeListener</code>s
     *         or bn empty brrby if no vetobble chbnge
     *         listeners bre currently registered
     *
     * @see #bddVetobbleChbngeListener
     * @see #removeVetobbleChbngeListener
     * @see #getVetobbleChbngeListeners(jbvb.lbng.String)
     * @since 1.4
     */
    public synchronized VetobbleChbngeListener[] getVetobbleChbngeListeners() {
        if (vetobbleSupport == null) {
            vetobbleSupport = new VetobbleChbngeSupport(this);
        }
        return vetobbleSupport.getVetobbleChbngeListeners();
    }

    /**
     * Adds b VetobbleChbngeListener to the listener list for b specific
     * property. The specified property mby be user-defined, or one of the
     * following:
     * <ul>
     *    <li>the focus owner ("focusOwner")</li>
     *    <li>the permbnent focus owner ("permbnentFocusOwner")</li>
     *    <li>the focused Window ("focusedWindow")</li>
     *    <li>the bctive Window ("bctiveWindow")</li>
     * </ul>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm propertyNbme one of the property nbmes listed bbove
     * @pbrbm listener the VetobbleChbngeListener to be bdded
     * @see #bddVetobbleChbngeListener(jbvb.bebns.VetobbleChbngeListener)
     * @see #removeVetobbleChbngeListener
     * @see #getVetobbleChbngeListeners
     */
    public void bddVetobbleChbngeListener(String propertyNbme,
                                          VetobbleChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (vetobbleSupport == null) {
                    vetobbleSupport =
                        new VetobbleChbngeSupport(this);
                }
                vetobbleSupport.bddVetobbleChbngeListener(propertyNbme,
                                                          listener);
            }
        }
    }

    /**
     * Removes b VetobbleChbngeListener from the listener list for b specific
     * property. This method should be used to remove VetobbleChbngeListeners
     * thbt were registered for b specific bound property.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm propertyNbme b vblid property nbme
     * @pbrbm listener the VetobbleChbngeListener to be removed
     * @see #bddVetobbleChbngeListener
     * @see #getVetobbleChbngeListeners
     * @see #removeVetobbleChbngeListener(jbvb.bebns.VetobbleChbngeListener)
     */
    public void removeVetobbleChbngeListener(String propertyNbme,
                                             VetobbleChbngeListener listener) {
        if (listener != null) {
            synchronized (this) {
                if (vetobbleSupport != null) {
                    vetobbleSupport.removeVetobbleChbngeListener(propertyNbme,
                                                                 listener);
                }
            }
        }
    }

    /**
     * Returns bn brrby of bll the <code>VetobbleChbngeListener</code>s
     * bssocibted with the nbmed property.
     *
     * @pbrbm  propertyNbme the property nbme
     * @return bll of the <code>VetobbleChbngeListener</code>s bssocibted with
     *         the nbmed property or bn empty brrby if no such listeners hbve
     *         been bdded.
     *
     * @see #bddVetobbleChbngeListener(jbvb.lbng.String,jbvb.bebns.VetobbleChbngeListener)
     * @see #removeVetobbleChbngeListener(jbvb.lbng.String,jbvb.bebns.VetobbleChbngeListener)
     * @see #getVetobbleChbngeListeners
     * @since 1.4
     */
    public synchronized VetobbleChbngeListener[] getVetobbleChbngeListeners(String propertyNbme) {
        if (vetobbleSupport == null) {
            vetobbleSupport = new VetobbleChbngeSupport(this);
        }
        return vetobbleSupport.getVetobbleChbngeListeners(propertyNbme);
    }

    /**
     * Fires b PropertyChbngeEvent in response to b chbnge in b vetobble
     * property. The event will be delivered to bll registered
     * VetobbleChbngeListeners. If b VetobbleChbngeListener throws b
     * PropertyVetoException, b new event is fired reverting bll
     * VetobbleChbngeListeners to the old vblue bnd the exception is then
     * rethrown. No event will be delivered if oldVblue bnd newVblue bre the
     * sbme.
     *
     * @pbrbm propertyNbme the nbme of the property thbt hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     * @throws jbvb.bebns.PropertyVetoException if b
     *         <code>VetobbleChbngeListener</code> threw
     *         <code>PropertyVetoException</code>
     */
    protected void fireVetobbleChbnge(String propertyNbme, Object oldVblue,
                                      Object newVblue)
        throws PropertyVetoException
    {
        if (oldVblue == newVblue) {
            return;
        }
        VetobbleChbngeSupport vetobbleSupport =
            this.vetobbleSupport;
        if (vetobbleSupport != null) {
            vetobbleSupport.fireVetobbleChbnge(propertyNbme, oldVblue,
                                               newVblue);
        }
    }

    /**
     * Adds b KeyEventDispbtcher to this KeybobrdFocusMbnbger's dispbtcher
     * chbin. This KeybobrdFocusMbnbger will request thbt ebch
     * KeyEventDispbtcher dispbtch KeyEvents generbted by the user before
     * finblly dispbtching the KeyEvent itself. KeyEventDispbtchers will be
     * notified in the order in which they were bdded. Notificbtions will hblt
     * bs soon bs one KeyEventDispbtcher returns <code>true</code> from its
     * <code>dispbtchKeyEvent</code> method. There is no limit to the totbl
     * number of KeyEventDispbtchers which cbn be bdded, nor to the number of
     * times which b pbrticulbr KeyEventDispbtcher instbnce cbn be bdded.
     * <p>
     * If b null dispbtcher is specified, no bction is tbken bnd no exception
     * is thrown.
     * <p>
     * In b multithrebded bpplicbtion, {@link KeyEventDispbtcher} behbves
     * the sbme bs other AWT listeners.  See
     * <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for more detbils.
     *
     * @pbrbm dispbtcher the KeyEventDispbtcher to bdd to the dispbtcher chbin
     * @see #removeKeyEventDispbtcher
     */
    public void bddKeyEventDispbtcher(KeyEventDispbtcher dispbtcher) {
        if (dispbtcher != null) {
            synchronized (this) {
                if (keyEventDispbtchers == null) {
                    keyEventDispbtchers = new jbvb.util.LinkedList<>();
                }
                keyEventDispbtchers.bdd(dispbtcher);
            }
        }
    }

    /**
     * Removes b KeyEventDispbtcher which wbs previously bdded to this
     * KeybobrdFocusMbnbger's dispbtcher chbin. This KeybobrdFocusMbnbger
     * cbnnot itself be removed, unless it wbs explicitly re-registered vib b
     * cbll to <code>bddKeyEventDispbtcher</code>.
     * <p>
     * If b null dispbtcher is specified, if the specified dispbtcher is not
     * in the dispbtcher chbin, or if this KeybobrdFocusMbnbger is specified
     * without hbving been explicitly re-registered, no bction is tbken bnd no
     * exception is thrown.
     * <p>
     * In b multithrebded bpplicbtion, {@link KeyEventDispbtcher} behbves
     * the sbme bs other AWT listeners.  See
     * <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for more detbils.
     *
     * @pbrbm dispbtcher the KeyEventDispbtcher to remove from the dispbtcher
     *        chbin
     * @see #bddKeyEventDispbtcher
     */
    public void removeKeyEventDispbtcher(KeyEventDispbtcher dispbtcher) {
        if (dispbtcher != null) {
            synchronized (this) {
                if (keyEventDispbtchers != null) {
                    keyEventDispbtchers.remove(dispbtcher);
                }
            }
        }
    }

    /**
     * Returns this KeybobrdFocusMbnbger's KeyEventDispbtcher chbin bs b List.
     * The List will not include this KeybobrdFocusMbnbger unless it wbs
     * explicitly re-registered vib b cbll to
     * <code>bddKeyEventDispbtcher</code>. If no other KeyEventDispbtchers bre
     * registered, implementbtions bre free to return null or b List of length
     * 0. Client code should not bssume one behbvior over bnother, nor should
     * it bssume thbt the behbvior, once estbblished, will not chbnge.
     *
     * @return b possibly null or empty List of KeyEventDispbtchers
     * @see #bddKeyEventDispbtcher
     * @see #removeKeyEventDispbtcher
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone
    protected synchronized jbvb.util.List<KeyEventDispbtcher>
        getKeyEventDispbtchers()
    {
        return (keyEventDispbtchers != null)
            ? (jbvb.util.List<KeyEventDispbtcher>)keyEventDispbtchers.clone()
            : null;
    }

    /**
     * Adds b KeyEventPostProcessor to this KeybobrdFocusMbnbger's post-
     * processor chbin. After b KeyEvent hbs been dispbtched to bnd hbndled by
     * its tbrget, KeybobrdFocusMbnbger will request thbt ebch
     * KeyEventPostProcessor perform bny necessbry post-processing bs pbrt
     * of the KeyEvent's finbl resolution. KeyEventPostProcessors
     * will be notified in the order in which they were bdded; the current
     * KeybobrdFocusMbnbger will be notified lbst. Notificbtions will hblt
     * bs soon bs one KeyEventPostProcessor returns <code>true</code> from its
     * <code>postProcessKeyEvent</code> method. There is no limit to the the
     * totbl number of KeyEventPostProcessors thbt cbn be bdded, nor to the
     * number of times thbt b pbrticulbr KeyEventPostProcessor instbnce cbn be
     * bdded.
     * <p>
     * If b null post-processor is specified, no bction is tbken bnd no
     * exception is thrown.
     * <p>
     * In b multithrebded bpplicbtion, {@link KeyEventPostProcessor} behbves
     * the sbme bs other AWT listeners.  See
     * <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for more detbils.
     *
     * @pbrbm processor the KeyEventPostProcessor to bdd to the post-processor
     *        chbin
     * @see #removeKeyEventPostProcessor
     */
    public void bddKeyEventPostProcessor(KeyEventPostProcessor processor) {
        if (processor != null) {
            synchronized (this) {
                if (keyEventPostProcessors == null) {
                    keyEventPostProcessors = new jbvb.util.LinkedList<>();
                }
                keyEventPostProcessors.bdd(processor);
            }
        }
    }


    /**
     * Removes b previously bdded KeyEventPostProcessor from this
     * KeybobrdFocusMbnbger's post-processor chbin. This KeybobrdFocusMbnbger
     * cbnnot itself be entirely removed from the chbin. Only bdditionbl
     * references bdded vib <code>bddKeyEventPostProcessor</code> cbn be
     * removed.
     * <p>
     * If b null post-processor is specified, if the specified post-processor
     * is not in the post-processor chbin, or if this KeybobrdFocusMbnbger is
     * specified without hbving been explicitly bdded, no bction is tbken bnd
     * no exception is thrown.
     * <p>
     * In b multithrebded bpplicbtion, {@link KeyEventPostProcessor} behbves
     * the sbme bs other AWT listeners.  See
     * <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for more detbils.
     *
     * @pbrbm processor the KeyEventPostProcessor to remove from the post-
     *        processor chbin
     * @see #bddKeyEventPostProcessor
     */
    public void removeKeyEventPostProcessor(KeyEventPostProcessor processor) {
        if (processor != null) {
            synchronized (this) {
                if (keyEventPostProcessors != null) {
                    keyEventPostProcessors.remove(processor);
                }
            }
        }
    }


    /**
     * Returns this KeybobrdFocusMbnbger's KeyEventPostProcessor chbin bs b
     * List. The List will not include this KeybobrdFocusMbnbger unless it wbs
     * explicitly bdded vib b cbll to <code>bddKeyEventPostProcessor</code>. If
     * no KeyEventPostProcessors bre registered, implementbtions bre free to
     * return null or b List of length 0. Client code should not bssume one
     * behbvior over bnother, nor should it bssume thbt the behbvior, once
     * estbblished, will not chbnge.
     *
     * @return b possibly null or empty List of KeyEventPostProcessors
     * @see #bddKeyEventPostProcessor
     * @see #removeKeyEventPostProcessor
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone
    protected jbvb.util.List<KeyEventPostProcessor>
        getKeyEventPostProcessors()
    {
        return (keyEventPostProcessors != null)
            ? (jbvb.util.List<KeyEventPostProcessor>)keyEventPostProcessors.clone()
            : null;
    }



    stbtic void setMostRecentFocusOwner(Component component) {
        Component window = component;
        while (window != null && !(window instbnceof Window)) {
            window = window.pbrent;
        }
        if (window != null) {
            setMostRecentFocusOwner((Window)window, component);
        }
    }
    stbtic synchronized void setMostRecentFocusOwner(Window window,
                                                     Component component) {
        // ATTN: component hbs b strong reference to window vib chbin
        // of Component.pbrent fields.  Since WebkHbsMbp refers to its
        // vblues strongly, we need to brebk the strong link from the
        // vblue (component) bbck to its key (window).
        WebkReference<Component> webkVblue = null;
        if (component != null) {
            webkVblue = new WebkReference<>(component);
        }
        mostRecentFocusOwners.put(window, webkVblue);
    }
    stbtic void clebrMostRecentFocusOwner(Component comp) {
        Contbiner window;

        if (comp == null) {
            return;
        }

        synchronized (comp.getTreeLock()) {
            window = comp.getPbrent();
            while (window != null && !(window instbnceof Window)) {
                window = window.getPbrent();
            }
        }

        synchronized (KeybobrdFocusMbnbger.clbss) {
            if ((window != null)
                && (getMostRecentFocusOwner((Window)window) == comp))
            {
                setMostRecentFocusOwner((Window)window, null);
            }
            // Also clebr temporbry lost component stored in Window
            if (window != null) {
                Window reblWindow = (Window)window;
                if (reblWindow.getTemporbryLostComponent() == comp) {
                    reblWindow.setTemporbryLostComponent(null);
                }
            }
        }
    }

    /*
     * Plebse be cbreful chbnging this method! It is cblled from
     * jbvbx.swing.JComponent.runInputVerifier() using reflection.
     */
    stbtic synchronized Component getMostRecentFocusOwner(Window window) {
        WebkReference<Component> webkVblue = mostRecentFocusOwners.get(window);
        return webkVblue == null ? null : webkVblue.get();
    }

    /**
     * This method is cblled by the AWT event dispbtcher requesting thbt the
     * current KeybobrdFocusMbnbger dispbtch the specified event on its behblf.
     * It is expected thbt bll KeybobrdFocusMbnbgers will dispbtch bll
     * FocusEvents, bll WindowEvents relbted to focus, bnd bll KeyEvents.
     * These events should be dispbtched bbsed on the KeybobrdFocusMbnbger's
     * notion of the focus owner bnd the focused bnd bctive Windows, sometimes
     * overriding the source of the specified AWTEvent. Dispbtching must be
     * done using <code>redispbtchEvent</code> to prevent the AWT event
     * dispbtcher from recursively requesting thbt the KeybobrdFocusMbnbger
     * dispbtch the event bgbin. If this method returns <code>fblse</code>,
     * then the AWT event dispbtcher will bttempt to dispbtch the event itself.
     *
     * @pbrbm e the AWTEvent to be dispbtched
     * @return <code>true</code> if this method dispbtched the event;
     *         <code>fblse</code> otherwise
     * @see #redispbtchEvent
     * @see #dispbtchKeyEvent
     */
    public bbstrbct boolebn dispbtchEvent(AWTEvent e);

    /**
     * Redispbtches bn AWTEvent in such b wby thbt the AWT event dispbtcher
     * will not recursively request thbt the KeybobrdFocusMbnbger, or bny
     * instblled KeyEventDispbtchers, dispbtch the event bgbin. Client
     * implementbtions of <code>dispbtchEvent</code> bnd client-defined
     * KeyEventDispbtchers must cbll <code>redispbtchEvent(tbrget, e)</code>
     * instebd of <code>tbrget.dispbtchEvent(e)</code> to dispbtch bn event.
     * <p>
     * This method is intended to be used only by KeybobrdFocusMbnbgers bnd
     * KeyEventDispbtchers. It is not for generbl client use.
     *
     * @pbrbm tbrget the Component to which the event should be dispbtched
     * @pbrbm e the event to dispbtch
     * @see #dispbtchEvent
     * @see KeyEventDispbtcher
     */
    public finbl void redispbtchEvent(Component tbrget, AWTEvent e) {
        e.focusMbnbgerIsDispbtching = true;
        tbrget.dispbtchEvent(e);
        e.focusMbnbgerIsDispbtching = fblse;
    }

    /**
     * Typicblly this method will be cblled by <code>dispbtchEvent</code> if no
     * other KeyEventDispbtcher in the dispbtcher chbin dispbtched the
     * KeyEvent, or if no other KeyEventDispbtchers bre registered. If bn
     * implementbtion of this method returns <code>fblse</code>,
     * <code>dispbtchEvent</code> mby try to dispbtch the KeyEvent itself, or
     * mby simply return <code>fblse</code>. If <code>true</code> is returned,
     * <code>dispbtchEvent</code> should return <code>true</code> bs well.
     *
     * @pbrbm e the KeyEvent which the current KeybobrdFocusMbnbger hbs
     *        requested thbt this KeyEventDispbtcher dispbtch
     * @return <code>true</code> if the KeyEvent wbs dispbtched;
     *         <code>fblse</code> otherwise
     * @see #dispbtchEvent
     */
    public bbstrbct boolebn dispbtchKeyEvent(KeyEvent e);

    /**
     * This method will be cblled by <code>dispbtchKeyEvent</code>.
     * By defbult, this method will hbndle bny unconsumed KeyEvents thbt
     * mbp to bn AWT <code>MenuShortcut</code> by consuming the event
     * bnd bctivbting the shortcut.
     *
     * @pbrbm e the KeyEvent to post-process
     * @return <code>true</code> to indicbte thbt no other
     *         KeyEventPostProcessor will be notified of the KeyEvent.
     * @see #dispbtchKeyEvent
     * @see MenuShortcut
     */
    public bbstrbct boolebn postProcessKeyEvent(KeyEvent e);

    /**
     * This method initibtes b focus trbversbl operbtion if bnd only if the
     * KeyEvent represents b focus trbversbl key for the specified
     * focusedComponent. It is expected thbt focusedComponent is the current
     * focus owner, blthough this need not be the cbse. If it is not,
     * focus trbversbl will nevertheless proceed bs if focusedComponent
     * were the current focus owner.
     *
     * @pbrbm focusedComponent the Component thbt will be the bbsis for b focus
     *        trbversbl operbtion if the specified event represents b focus
     *        trbversbl key for the Component
     * @pbrbm e the event thbt mby represent b focus trbversbl key
     */
    public bbstrbct void processKeyEvent(Component focusedComponent,
                                         KeyEvent e);

    /**
     * Cblled by the AWT to notify the KeybobrdFocusMbnbger thbt it should
     * delby dispbtching of KeyEvents until the specified Component becomes
     * the focus owner. If client code requests b focus chbnge, bnd the AWT
     * determines thbt this request might be grbnted by the nbtive windowing
     * system, then the AWT will cbll this method. It is the responsibility of
     * the KeybobrdFocusMbnbger to delby dispbtching of KeyEvents with
     * timestbmps lbter thbn the specified time stbmp until the specified
     * Component receives b FOCUS_GAINED event, or the AWT cbncels the delby
     * request by invoking <code>dequeueKeyEvents</code> or
     * <code>discbrdKeyEvents</code>.
     *
     * @pbrbm bfter timestbmp of current event, or the current, system time if
     *        the current event hbs no timestbmp, or the AWT cbnnot determine
     *        which event is currently being hbndled
     * @pbrbm untilFocused Component which should receive b FOCUS_GAINED event
     *        before bny pending KeyEvents
     * @see #dequeueKeyEvents
     * @see #discbrdKeyEvents
     */
    protected bbstrbct void enqueueKeyEvents(long bfter,
                                             Component untilFocused);

    /**
     * Cblled by the AWT to notify the KeybobrdFocusMbnbger thbt it should
     * cbncel delbyed dispbtching of KeyEvents. All KeyEvents which were
     * enqueued becbuse of b cbll to <code>enqueueKeyEvents</code> with the
     * sbme timestbmp bnd Component should be relebsed for normbl dispbtching
     * to the current focus owner. If the given timestbmp is less thbn zero,
     * the outstbnding enqueue request for the given Component with the <b>
     * oldest</b> timestbmp (if bny) should be cbncelled.
     *
     * @pbrbm bfter the timestbmp specified in the cbll to
     *        <code>enqueueKeyEvents</code>, or bny vblue &lt; 0
     * @pbrbm untilFocused the Component specified in the cbll to
     *        <code>enqueueKeyEvents</code>
     * @see #enqueueKeyEvents
     * @see #discbrdKeyEvents
     */
    protected bbstrbct void dequeueKeyEvents(long bfter,
                                             Component untilFocused);

    /**
     * Cblled by the AWT to notify the KeybobrdFocusMbnbger thbt it should
     * cbncel delbyed dispbtching of KeyEvents. All KeyEvents which were
     * enqueued becbuse of one or more cblls to <code>enqueueKeyEvents</code>
     * with the sbme Component should be discbrded.
     *
     * @pbrbm comp the Component specified in one or more cblls to
     *        <code>enqueueKeyEvents</code>
     * @see #enqueueKeyEvents
     * @see #dequeueKeyEvents
     */
    protected bbstrbct void discbrdKeyEvents(Component comp);

    /**
     * Focuses the Component bfter bComponent, typicblly bbsed on b
     * FocusTrbversblPolicy.
     *
     * @pbrbm bComponent the Component thbt is the bbsis for the focus
     *        trbversbl operbtion
     * @see FocusTrbversblPolicy
     */
    public bbstrbct void focusNextComponent(Component bComponent);

    /**
     * Focuses the Component before bComponent, typicblly bbsed on b
     * FocusTrbversblPolicy.
     *
     * @pbrbm bComponent the Component thbt is the bbsis for the focus
     *        trbversbl operbtion
     * @see FocusTrbversblPolicy
     */
    public bbstrbct void focusPreviousComponent(Component bComponent);

    /**
     * Moves the focus up one focus trbversbl cycle. Typicblly, the focus owner
     * is set to bComponent's focus cycle root, bnd the current focus cycle
     * root is set to the new focus owner's focus cycle root. If, however,
     * bComponent's focus cycle root is b Window, then typicblly the focus
     * owner is set to the Window's defbult Component to focus, bnd the current
     * focus cycle root is unchbnged.
     *
     * @pbrbm bComponent the Component thbt is the bbsis for the focus
     *        trbversbl operbtion
     */
    public bbstrbct void upFocusCycle(Component bComponent);

    /**
     * Moves the focus down one focus trbversbl cycle. Typicblly, if
     * bContbiner is b focus cycle root, then the focus owner is set to
     * bContbiner's defbult Component to focus, bnd the current focus cycle
     * root is set to bContbiner. If bContbiner is not b focus cycle root, then
     * no focus trbversbl operbtion occurs.
     *
     * @pbrbm bContbiner the Contbiner thbt is the bbsis for the focus
     *        trbversbl operbtion
     */
    public bbstrbct void downFocusCycle(Contbiner bContbiner);

    /**
     * Focuses the Component bfter the current focus owner.
     */
    public finbl void focusNextComponent() {
        Component focusOwner = getFocusOwner();
        if (focusOwner != null) {
            focusNextComponent(focusOwner);
        }
    }

    /**
     * Focuses the Component before the current focus owner.
     */
    public finbl void focusPreviousComponent() {
        Component focusOwner = getFocusOwner();
        if (focusOwner != null) {
            focusPreviousComponent(focusOwner);
        }
    }

    /**
     * Moves the focus up one focus trbversbl cycle from the current focus
     * owner. Typicblly, the new focus owner is set to the current focus
     * owner's focus cycle root, bnd the current focus cycle root is set to the
     * new focus owner's focus cycle root. If, however, the current focus
     * owner's focus cycle root is b Window, then typicblly the focus owner is
     * set to the focus cycle root's defbult Component to focus, bnd the
     * current focus cycle root is unchbnged.
     */
    public finbl void upFocusCycle() {
        Component focusOwner = getFocusOwner();
        if (focusOwner != null) {
            upFocusCycle(focusOwner);
        }
    }

    /**
     * Moves the focus down one focus trbversbl cycle from the current focus
     * owner, if bnd only if the current focus owner is b Contbiner thbt is b
     * focus cycle root. Typicblly, the focus owner is set to the current focus
     * owner's defbult Component to focus, bnd the current focus cycle root is
     * set to the current focus owner. If the current focus owner is not b
     * Contbiner thbt is b focus cycle root, then no focus trbversbl operbtion
     * occurs.
     */
    public finbl void downFocusCycle() {
        Component focusOwner = getFocusOwner();
        if (focusOwner instbnceof Contbiner) {
            downFocusCycle((Contbiner)focusOwner);
        }
    }

    /**
     * Dumps the list of focus requests to stderr
     */
    void dumpRequests() {
        System.err.println(">>> Requests dump, time: " + System.currentTimeMillis());
        synchronized (hebvyweightRequests) {
            for (HebvyweightFocusRequest req : hebvyweightRequests) {
                System.err.println(">>> Req: " + req);
            }
        }
        System.err.println("");
    }

    privbte stbtic finbl clbss LightweightFocusRequest {
        finbl Component component;
        finbl boolebn temporbry;
        finbl CbusedFocusEvent.Cbuse cbuse;

        LightweightFocusRequest(Component component, boolebn temporbry, CbusedFocusEvent.Cbuse cbuse) {
            this.component = component;
            this.temporbry = temporbry;
            this.cbuse = cbuse;
        }
        public String toString() {
            return "LightweightFocusRequest[component=" + component +
                ",temporbry=" + temporbry + ", cbuse=" + cbuse + "]";
        }
    }

    privbte stbtic finbl clbss HebvyweightFocusRequest {
        finbl Component hebvyweight;
        finbl LinkedList<LightweightFocusRequest> lightweightRequests;

        stbtic finbl HebvyweightFocusRequest CLEAR_GLOBAL_FOCUS_OWNER =
            new HebvyweightFocusRequest();

        privbte HebvyweightFocusRequest() {
            hebvyweight = null;
            lightweightRequests = null;
        }

        HebvyweightFocusRequest(Component hebvyweight, Component descendbnt,
                                boolebn temporbry, CbusedFocusEvent.Cbuse cbuse) {
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                if (hebvyweight == null) {
                    log.fine("Assertion (hebvyweight != null) fbiled");
                }
            }

            this.hebvyweight = hebvyweight;
            this.lightweightRequests = new LinkedList<LightweightFocusRequest>();
            bddLightweightRequest(descendbnt, temporbry, cbuse);
        }
        boolebn bddLightweightRequest(Component descendbnt,
                                      boolebn temporbry, CbusedFocusEvent.Cbuse cbuse) {
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                if (this == HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER) {
                    log.fine("Assertion (this != HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER) fbiled");
                }
                if (descendbnt == null) {
                    log.fine("Assertion (descendbnt != null) fbiled");
                }
            }

            Component lbstDescendbnt = ((lightweightRequests.size() > 0)
                ? lightweightRequests.getLbst().component
                : null);

            if (descendbnt != lbstDescendbnt) {
                // Not b duplicbte request
                lightweightRequests.bdd
                    (new LightweightFocusRequest(descendbnt, temporbry, cbuse));
                return true;
            } else {
                return fblse;
            }
        }

        LightweightFocusRequest getFirstLightweightRequest() {
            if (this == CLEAR_GLOBAL_FOCUS_OWNER) {
                return null;
            }
            return lightweightRequests.getFirst();
        }
        public String toString() {
            boolebn first = true;
            String str = "HebvyweightFocusRequest[hebvweight=" + hebvyweight +
                ",lightweightRequests=";
            if (lightweightRequests == null) {
                str += null;
            } else {
                str += "[";

                for (LightweightFocusRequest lwRequest : lightweightRequests) {
                    if (first) {
                        first = fblse;
                    } else {
                        str += ",";
                    }
                    str += lwRequest;
                }
                str += "]";
            }
            str += "]";
            return str;
        }
    }

    /*
     * hebvyweightRequests is used bs b monitor for synchronized chbnges of
     * currentLightweightRequests, clebringCurrentLightweightRequests bnd
     * newFocusOwner.
     */
    privbte stbtic LinkedList<HebvyweightFocusRequest> hebvyweightRequests =
        new LinkedList<HebvyweightFocusRequest>();
    privbte stbtic LinkedList<LightweightFocusRequest> currentLightweightRequests;
    privbte stbtic boolebn clebringCurrentLightweightRequests;
    privbte stbtic boolebn bllowSyncFocusRequests = true;
    privbte stbtic Component newFocusOwner = null;
    privbte stbtic volbtile boolebn disbbleRestoreFocus;

    stbtic finbl int SNFH_FAILURE = 0;
    stbtic finbl int SNFH_SUCCESS_HANDLED = 1;
    stbtic finbl int SNFH_SUCCESS_PROCEED = 2;

    stbtic boolebn processSynchronousLightweightTrbnsfer(Component hebvyweight, Component descendbnt,
                                                  boolebn temporbry, boolebn focusedWindowChbngeAllowed,
                                                  long time)
    {
        Window pbrentWindow = SunToolkit.getContbiningWindow(hebvyweight);
        if (pbrentWindow == null || !pbrentWindow.syncLWRequests) {
            return fblse;
        }
        if (descendbnt == null) {
            // Focus trbnsfers from b lightweight child bbck to the
            // hebvyweight Contbiner should be trebted like lightweight
            // focus trbnsfers.
            descendbnt = hebvyweight;
        }

        KeybobrdFocusMbnbger mbnbger = getCurrentKeybobrdFocusMbnbger(SunToolkit.tbrgetToAppContext(descendbnt));

        FocusEvent currentFocusOwnerEvent = null;
        FocusEvent newFocusOwnerEvent = null;
        Component currentFocusOwner = mbnbger.getGlobblFocusOwner();

        synchronized (hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getLbstHWRequest();
            if (hwFocusRequest == null &&
                hebvyweight == mbnbger.getNbtiveFocusOwner() &&
                bllowSyncFocusRequests)
            {

                if (descendbnt == currentFocusOwner) {
                    // Redundbnt request.
                    return true;
                }

                // 'hebvyweight' owns the nbtive focus bnd there bre no pending
                // requests. 'hebvyweight' must be b Contbiner bnd
                // 'descendbnt' must not be the focus owner. Otherwise,
                // we would never hbve gotten this fbr.
                mbnbger.enqueueKeyEvents(time, descendbnt);

                hwFocusRequest =
                    new HebvyweightFocusRequest(hebvyweight, descendbnt,
                                                temporbry, CbusedFocusEvent.Cbuse.UNKNOWN);
                hebvyweightRequests.bdd(hwFocusRequest);

                if (currentFocusOwner != null) {
                    currentFocusOwnerEvent =
                        new FocusEvent(currentFocusOwner,
                                       FocusEvent.FOCUS_LOST,
                                       temporbry, descendbnt);
                }
                newFocusOwnerEvent =
                    new FocusEvent(descendbnt, FocusEvent.FOCUS_GAINED,
                                   temporbry, currentFocusOwner);
            }
        }
        boolebn result = fblse;
        finbl boolebn clebring = clebringCurrentLightweightRequests;

        Throwbble cbughtEx = null;
        try {
            clebringCurrentLightweightRequests = fblse;
            synchronized(Component.LOCK) {

                if (currentFocusOwnerEvent != null && currentFocusOwner != null) {
                    ((AWTEvent) currentFocusOwnerEvent).isPosted = true;
                    cbughtEx = dispbtchAndCbtchException(cbughtEx, currentFocusOwner, currentFocusOwnerEvent);
                    result = true;
                }

                if (newFocusOwnerEvent != null && descendbnt != null) {
                    ((AWTEvent) newFocusOwnerEvent).isPosted = true;
                    cbughtEx = dispbtchAndCbtchException(cbughtEx, descendbnt, newFocusOwnerEvent);
                    result = true;
                }
            }
        } finblly {
            clebringCurrentLightweightRequests = clebring;
        }
        if (cbughtEx instbnceof RuntimeException) {
            throw (RuntimeException)cbughtEx;
        } else if (cbughtEx instbnceof Error) {
            throw (Error)cbughtEx;
        }
        return result;
    }

    /**
     * Indicbtes whether the nbtive implementbtion should proceed with b
     * pending, nbtive focus request. Before chbnging the focus bt the nbtive
     * level, the AWT implementbtion should blwbys cbll this function for
     * permission. This function will reject the request if b duplicbte request
     * preceded it, or if the specified hebvyweight Component blrebdy owns the
     * focus bnd no nbtive focus chbnges bre pending. Otherwise, the request
     * will be bpproved bnd the focus request list will be updbted so thbt,
     * if necessbry, the proper descendbnt will be focused when the
     * corresponding FOCUS_GAINED event on the hebvyweight is received.
     *
     * An implementbtion must ensure thbt cblls to this method bnd nbtive
     * focus chbnges bre btomic. If this is not gubrbnteed, then the ordering
     * of the focus request list mby be incorrect, lebding to errors in the
     * type-bhebd mechbnism. Typicblly this is bccomplished by only cblling
     * this function from the nbtive event pumping threbd, or by holding b
     * globbl, nbtive lock during invocbtion.
     */
    stbtic int shouldNbtivelyFocusHebvyweight
        (Component hebvyweight, Component descendbnt, boolebn temporbry,
         boolebn focusedWindowChbngeAllowed, long time, CbusedFocusEvent.Cbuse cbuse)
    {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            if (hebvyweight == null) {
                log.fine("Assertion (hebvyweight != null) fbiled");
            }
            if (time == 0) {
                log.fine("Assertion (time != 0) fbiled");
            }
        }

        if (descendbnt == null) {
            // Focus trbnsfers from b lightweight child bbck to the
            // hebvyweight Contbiner should be trebted like lightweight
            // focus trbnsfers.
            descendbnt = hebvyweight;
        }

        KeybobrdFocusMbnbger mbnbger =
            getCurrentKeybobrdFocusMbnbger(SunToolkit.tbrgetToAppContext(descendbnt));
        KeybobrdFocusMbnbger thisMbnbger = getCurrentKeybobrdFocusMbnbger();
        Component currentFocusOwner = thisMbnbger.getGlobblFocusOwner();
        Component nbtiveFocusOwner = thisMbnbger.getNbtiveFocusOwner();
        Window nbtiveFocusedWindow = thisMbnbger.getNbtiveFocusedWindow();
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("SNFH for {0} in {1}",
                       String.vblueOf(descendbnt), String.vblueOf(hebvyweight));
        }
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            focusLog.finest("0. Current focus owner {0}",
                            String.vblueOf(currentFocusOwner));
            focusLog.finest("0. Nbtive focus owner {0}",
                            String.vblueOf(nbtiveFocusOwner));
            focusLog.finest("0. Nbtive focused window {0}",
                            String.vblueOf(nbtiveFocusedWindow));
        }
        synchronized (hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getLbstHWRequest();
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Request {0}", String.vblueOf(hwFocusRequest));
            }
            if (hwFocusRequest == null &&
                hebvyweight == nbtiveFocusOwner &&
                hebvyweight.getContbiningWindow() == nbtiveFocusedWindow)
            {
                if (descendbnt == currentFocusOwner) {
                    // Redundbnt request.
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST))
                        focusLog.finest("1. SNFH_FAILURE for {0}",
                                        String.vblueOf(descendbnt));
                    return SNFH_FAILURE;
                }

                // 'hebvyweight' owns the nbtive focus bnd there bre no pending
                // requests. 'hebvyweight' must be b Contbiner bnd
                // 'descendbnt' must not be the focus owner. Otherwise,
                // we would never hbve gotten this fbr.
                mbnbger.enqueueKeyEvents(time, descendbnt);

                hwFocusRequest =
                    new HebvyweightFocusRequest(hebvyweight, descendbnt,
                                                temporbry, cbuse);
                hebvyweightRequests.bdd(hwFocusRequest);

                if (currentFocusOwner != null) {
                    FocusEvent currentFocusOwnerEvent =
                        new CbusedFocusEvent(currentFocusOwner,
                                       FocusEvent.FOCUS_LOST,
                                       temporbry, descendbnt, cbuse);
                    // Fix 5028014. Rolled out.
                    // SunToolkit.postPriorityEvent(currentFocusOwnerEvent);
                    SunToolkit.postEvent(currentFocusOwner.bppContext,
                                         currentFocusOwnerEvent);
                }
                FocusEvent newFocusOwnerEvent =
                    new CbusedFocusEvent(descendbnt, FocusEvent.FOCUS_GAINED,
                                   temporbry, currentFocusOwner, cbuse);
                // Fix 5028014. Rolled out.
                // SunToolkit.postPriorityEvent(newFocusOwnerEvent);
                SunToolkit.postEvent(descendbnt.bppContext, newFocusOwnerEvent);

                if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST))
                    focusLog.finest("2. SNFH_HANDLED for {0}", String.vblueOf(descendbnt));
                return SNFH_SUCCESS_HANDLED;
            } else if (hwFocusRequest != null &&
                       hwFocusRequest.hebvyweight == hebvyweight) {
                // 'hebvyweight' doesn't hbve the nbtive focus right now, but
                // if bll pending requests were completed, it would. Add
                // descendbnt to the hebvyweight's list of pending
                // lightweight focus trbnsfers.
                if (hwFocusRequest.bddLightweightRequest(descendbnt,
                                                         temporbry, cbuse)) {
                    mbnbger.enqueueKeyEvents(time, descendbnt);
                }

                if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    focusLog.finest("3. SNFH_HANDLED for lightweight" +
                                    descendbnt + " in " + hebvyweight);
                }
                return SNFH_SUCCESS_HANDLED;
            } else {
                if (!focusedWindowChbngeAllowed) {
                    // For purposes of computing oldFocusedWindow, we should look bt
                    // the second to lbst HebvyweightFocusRequest on the queue iff the
                    // lbst HebvyweightFocusRequest is CLEAR_GLOBAL_FOCUS_OWNER. If
                    // there is no second to lbst HebvyweightFocusRequest, null is bn
                    // bcceptbble vblue.
                    if (hwFocusRequest ==
                        HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER)
                    {
                        int size = hebvyweightRequests.size();
                        hwFocusRequest = (size >= 2)
                            ? hebvyweightRequests.get(size - 2)
                            : null;
                    }
                    if (focusedWindowChbnged(hebvyweight,
                                             (hwFocusRequest != null)
                                             ? hwFocusRequest.hebvyweight
                                             : nbtiveFocusedWindow)) {
                        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                            focusLog.finest("4. SNFH_FAILURE for " + descendbnt);
                        }
                        return SNFH_FAILURE;
                    }
                }

                mbnbger.enqueueKeyEvents(time, descendbnt);
                hebvyweightRequests.bdd
                    (new HebvyweightFocusRequest(hebvyweight, descendbnt,
                                                 temporbry, cbuse));
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    focusLog.finest("5. SNFH_PROCEED for " + descendbnt);
                }
                return SNFH_SUCCESS_PROCEED;
            }
        }
    }

    /**
     * Returns the Window which will be bctive bfter processing this request,
     * or null if this is b duplicbte request. The bctive Window is useful
     * becbuse some nbtive plbtforms do not support setting the nbtive focus
     * owner to null. On these plbtforms, the obvious choice is to set the
     * focus owner to the focus proxy of the bctive Window.
     */
    stbtic Window mbrkClebrGlobblFocusOwner() {
        // need to cbll this out of synchronized block to bvoid possible debdlock
        // see 6454631.
        finbl Component nbtiveFocusedWindow =
                getCurrentKeybobrdFocusMbnbger().getNbtiveFocusedWindow();

        synchronized (hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getLbstHWRequest();
            if (hwFocusRequest ==
                HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER)
            {
                // duplicbte request
                return null;
            }

            hebvyweightRequests.bdd
                (HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER);

            Component bctiveWindow = ((hwFocusRequest != null)
                ? SunToolkit.getContbiningWindow(hwFocusRequest.hebvyweight)
                : nbtiveFocusedWindow);
            while (bctiveWindow != null &&
                   !((bctiveWindow instbnceof Frbme) ||
                     (bctiveWindow instbnceof Diblog)))
            {
                bctiveWindow = bctiveWindow.getPbrent_NoClientCode();
            }

            return (Window) bctiveWindow;
        }
    }
    Component getCurrentWbitingRequest(Component pbrent) {
        synchronized (hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getFirstHWRequest();
            if (hwFocusRequest != null) {
                if (hwFocusRequest.hebvyweight == pbrent) {
                    LightweightFocusRequest lwFocusRequest =
                        hwFocusRequest.lightweightRequests.getFirst();
                    if (lwFocusRequest != null) {
                        return lwFocusRequest.component;
                    }
                }
            }
        }
        return null;
    }

    stbtic boolebn isAutoFocusTrbnsferEnbbled() {
        synchronized (hebvyweightRequests) {
            return (hebvyweightRequests.size() == 0)
                    && !disbbleRestoreFocus
                    && (null == currentLightweightRequests);
        }
    }

    stbtic boolebn isAutoFocusTrbnsferEnbbledFor(Component comp) {
        return isAutoFocusTrbnsferEnbbled() && comp.isAutoFocusTrbnsferOnDisposbl();
    }

    /*
     * Used to process exceptions in dispbtching focus event (in focusLost/focusGbined cbllbbcks).
     * @pbrbm ex previously cbught exception thbt mby be processed right here, or null
     * @pbrbm comp the component to dispbtch the event to
     * @pbrbm event the event to dispbtch to the component
     */
    stbtic privbte Throwbble dispbtchAndCbtchException(Throwbble ex, Component comp, FocusEvent event) {
        Throwbble retEx = null;
        try {
            comp.dispbtchEvent(event);
        } cbtch (RuntimeException re) {
            retEx = re;
        } cbtch (Error er) {
            retEx = er;
        }
        if (retEx != null) {
            if (ex != null) {
                hbndleException(ex);
            }
            return retEx;
        }
        return ex;
    }

    stbtic privbte void hbndleException(Throwbble ex) {
        ex.printStbckTrbce();
    }

    stbtic void processCurrentLightweightRequests() {
        KeybobrdFocusMbnbger mbnbger = getCurrentKeybobrdFocusMbnbger();
        LinkedList<LightweightFocusRequest> locblLightweightRequests = null;

        Component globblFocusOwner = mbnbger.getGlobblFocusOwner();
        if ((globblFocusOwner != null) &&
            (globblFocusOwner.bppContext != AppContext.getAppContext()))
        {
            // The current bpp context differs from the bpp context of b focus
            // owner (bnd bll pending lightweight requests), so we do nothing
            // now bnd wbit for b next event.
            return;
        }

        synchronized(hebvyweightRequests) {
            if (currentLightweightRequests != null) {
                clebringCurrentLightweightRequests = true;
                disbbleRestoreFocus = true;
                locblLightweightRequests = currentLightweightRequests;
                bllowSyncFocusRequests = (locblLightweightRequests.size() < 2);
                currentLightweightRequests = null;
            } else {
                // do nothing
                return;
            }
        }

        Throwbble cbughtEx = null;
        try {
            if (locblLightweightRequests != null) {
                Component lbstFocusOwner = null;
                Component currentFocusOwner = null;

                for (Iterbtor<KeybobrdFocusMbnbger.LightweightFocusRequest> iter = locblLightweightRequests.iterbtor(); iter.hbsNext(); ) {

                    currentFocusOwner = mbnbger.getGlobblFocusOwner();
                    LightweightFocusRequest lwFocusRequest =
                        iter.next();

                    /*
                     * WARNING: This is bbsed on DKFM's logic solely!
                     *
                     * We bllow to trigger restoreFocus() in the dispbtching process
                     * only if we hbve the lbst request to dispbtch. If the lbst request
                     * fbils, focus will be restored to either the component of the lbst
                     * previously succedded request, or to to the focus owner thbt wbs
                     * before this clebring process.
                     */
                    if (!iter.hbsNext()) {
                        disbbleRestoreFocus = fblse;
                    }

                    FocusEvent currentFocusOwnerEvent = null;
                    /*
                     * We're not dispbtching FOCUS_LOST while the current focus owner is null.
                     * But regbrdless of whether it's null or not, we're clebring ALL the locbl
                     * lw requests.
                     */
                    if (currentFocusOwner != null) {
                        currentFocusOwnerEvent = new CbusedFocusEvent(currentFocusOwner,
                                       FocusEvent.FOCUS_LOST,
                                       lwFocusRequest.temporbry,
                                       lwFocusRequest.component, lwFocusRequest.cbuse);
                    }
                    FocusEvent newFocusOwnerEvent =
                        new CbusedFocusEvent(lwFocusRequest.component,
                                       FocusEvent.FOCUS_GAINED,
                                       lwFocusRequest.temporbry,
                                       currentFocusOwner == null ? lbstFocusOwner : currentFocusOwner,
                                       lwFocusRequest.cbuse);

                    if (currentFocusOwner != null) {
                        ((AWTEvent) currentFocusOwnerEvent).isPosted = true;
                        cbughtEx = dispbtchAndCbtchException(cbughtEx, currentFocusOwner, currentFocusOwnerEvent);
                    }

                    ((AWTEvent) newFocusOwnerEvent).isPosted = true;
                    cbughtEx = dispbtchAndCbtchException(cbughtEx, lwFocusRequest.component, newFocusOwnerEvent);

                    if (mbnbger.getGlobblFocusOwner() == lwFocusRequest.component) {
                        lbstFocusOwner = lwFocusRequest.component;
                    }
                }
            }
        } finblly {
            clebringCurrentLightweightRequests = fblse;
            disbbleRestoreFocus = fblse;
            locblLightweightRequests = null;
            bllowSyncFocusRequests = true;
        }
        if (cbughtEx instbnceof RuntimeException) {
            throw (RuntimeException)cbughtEx;
        } else if (cbughtEx instbnceof Error) {
            throw (Error)cbughtEx;
        }
    }

    stbtic FocusEvent retbrgetUnexpectedFocusEvent(FocusEvent fe) {
        synchronized (hebvyweightRequests) {
            // Any other cbse represents b fbilure condition which we did
            // not expect. We need to clebrFocusRequestList() bnd pbtch up
            // the event bs best bs possible.

            if (removeFirstRequest()) {
                return (FocusEvent)retbrgetFocusEvent(fe);
            }

            Component source = fe.getComponent();
            Component opposite = fe.getOppositeComponent();
            boolebn temporbry = fblse;
            if (fe.getID() == FocusEvent.FOCUS_LOST &&
                (opposite == null || isTemporbry(opposite, source)))
            {
                temporbry = true;
            }
            return new CbusedFocusEvent(source, fe.getID(), temporbry, opposite,
                                        CbusedFocusEvent.Cbuse.NATIVE_SYSTEM);
        }
    }

    stbtic FocusEvent retbrgetFocusGbined(FocusEvent fe) {
        bssert (fe.getID() == FocusEvent.FOCUS_GAINED);

        Component currentFocusOwner = getCurrentKeybobrdFocusMbnbger().
            getGlobblFocusOwner();
        Component source = fe.getComponent();
        Component opposite = fe.getOppositeComponent();
        Component nbtiveSource = getHebvyweight(source);

        synchronized (hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getFirstHWRequest();

            if (hwFocusRequest == HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER)
            {
                return retbrgetUnexpectedFocusEvent(fe);
            }

            if (source != null && nbtiveSource == null && hwFocusRequest != null) {
                // if source w/o peer bnd
                // if source is equbl to first lightweight
                // then we should correct source bnd nbtiveSource
                if (source == hwFocusRequest.getFirstLightweightRequest().component)
                {
                    source = hwFocusRequest.hebvyweight;
                    nbtiveSource = source; // source is hebvuweight itself
                }
            }
            if (hwFocusRequest != null &&
                nbtiveSource == hwFocusRequest.hebvyweight)
            {
                // Focus chbnge bs b result of b known cbll to requestFocus(),
                // or known click on b peer focusbble hebvyweight Component.

                hebvyweightRequests.removeFirst();

                LightweightFocusRequest lwFocusRequest =
                    hwFocusRequest.lightweightRequests.removeFirst();

                Component newSource = lwFocusRequest.component;
                if (currentFocusOwner != null) {
                    /*
                     * Since we receive FOCUS_GAINED when current focus
                     * owner is not null, correcponding FOCUS_LOST is supposed
                     * to be lost.  And so,  we keep new focus owner
                     * to determine synthetic FOCUS_LOST event which will be
                     * generbted by KeybobrdFocusMbnbger for this FOCUS_GAINED.
                     *
                     * This code bbsed on knowledge of
                     * DefbultKeybobrdFocusMbnbger's implementbtion bnd might
                     * be not bpplicbble for bnother KeybobrdFocusMbnbger.
                     */
                    newFocusOwner = newSource;
                }

                boolebn temporbry = (opposite == null ||
                                     isTemporbry(newSource, opposite))
                        ? fblse
                        : lwFocusRequest.temporbry;

                if (hwFocusRequest.lightweightRequests.size() > 0) {
                    currentLightweightRequests =
                        hwFocusRequest.lightweightRequests;
                    EventQueue.invokeLbter(new Runnbble() {
                            public void run() {
                                processCurrentLightweightRequests();
                            }
                        });
                }

                // 'opposite' will be fixed by
                // DefbultKeybobrdFocusMbnbger.reblOppositeComponent
                return new CbusedFocusEvent(newSource,
                                      FocusEvent.FOCUS_GAINED, temporbry,
                                      opposite, lwFocusRequest.cbuse);
            }

            if (currentFocusOwner != null
                && currentFocusOwner.getContbiningWindow() == source
                && (hwFocusRequest == null || source != hwFocusRequest.hebvyweight))
            {
                // Specibl cbse for FOCUS_GAINED in top-levels
                // If it brrives bs the result of bctivbtion we should skip it
                // This event will not hbve bppropribte request record bnd
                // on brrivbl there will be blrebdy some focus owner set.
                return new CbusedFocusEvent(currentFocusOwner, FocusEvent.FOCUS_GAINED, fblse,
                                            null, CbusedFocusEvent.Cbuse.ACTIVATION);
            }

            return retbrgetUnexpectedFocusEvent(fe);
        } // end synchronized(hebvyweightRequests)
    }

    stbtic FocusEvent retbrgetFocusLost(FocusEvent fe) {
        bssert (fe.getID() == FocusEvent.FOCUS_LOST);

        Component currentFocusOwner = getCurrentKeybobrdFocusMbnbger().
            getGlobblFocusOwner();
        Component opposite = fe.getOppositeComponent();
        Component nbtiveOpposite = getHebvyweight(opposite);

        synchronized (hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getFirstHWRequest();

            if (hwFocusRequest == HebvyweightFocusRequest.CLEAR_GLOBAL_FOCUS_OWNER)
            {
                if (currentFocusOwner != null) {
                    // Cbll to KeybobrdFocusMbnbger.clebrGlobblFocusOwner()
                    hebvyweightRequests.removeFirst();
                    return new CbusedFocusEvent(currentFocusOwner,
                                                FocusEvent.FOCUS_LOST, fblse, null,
                                                CbusedFocusEvent.Cbuse.CLEAR_GLOBAL_FOCUS_OWNER);
                }

                // Otherwise, fbll through to fbilure cbse below

            } else if (opposite == null)
            {
                // Focus lebving bpplicbtion
                if (currentFocusOwner != null) {
                    return new CbusedFocusEvent(currentFocusOwner,
                                                FocusEvent.FOCUS_LOST,
                                                true, null, CbusedFocusEvent.Cbuse.ACTIVATION);
                } else {
                    return fe;
                }
            } else if (hwFocusRequest != null &&
                       (nbtiveOpposite == hwFocusRequest.hebvyweight ||
                        nbtiveOpposite == null &&
                        opposite == hwFocusRequest.getFirstLightweightRequest().component))
            {
                if (currentFocusOwner == null) {
                    return fe;
                }
                // Focus chbnge bs b result of b known cbll to requestFocus(),
                // or click on b peer focusbble hebvyweight Component.
                // If b focus trbnsfer is mbde bcross top-levels, then the
                // FOCUS_LOST event is blwbys temporbry, bnd the FOCUS_GAINED
                // event is blwbys permbnent. Otherwise, the stored temporbry
                // vblue is honored.

                LightweightFocusRequest lwFocusRequest =
                    hwFocusRequest.lightweightRequests.getFirst();

                boolebn temporbry = isTemporbry(opposite, currentFocusOwner)
                    ? true
                    : lwFocusRequest.temporbry;

                return new CbusedFocusEvent(currentFocusOwner, FocusEvent.FOCUS_LOST,
                                            temporbry, lwFocusRequest.component, lwFocusRequest.cbuse);
            } else if (focusedWindowChbnged(opposite, currentFocusOwner)) {
                // If top-level chbnged there might be no focus request in b list
                // But we know the opposite, we now it is temporbry - dispbtch the event.
                if (!fe.isTemporbry() && currentFocusOwner != null) {
                    // Crebte copy of the event with only difference in temporbry pbrbmeter.
                    fe = new CbusedFocusEvent(currentFocusOwner, FocusEvent.FOCUS_LOST,
                                              true, opposite, CbusedFocusEvent.Cbuse.ACTIVATION);
                }
                return fe;
            }

            return retbrgetUnexpectedFocusEvent(fe);
        }  // end synchronized(hebvyweightRequests)
    }

    stbtic AWTEvent retbrgetFocusEvent(AWTEvent event) {
        if (clebringCurrentLightweightRequests) {
            return event;
        }

        KeybobrdFocusMbnbger mbnbger = getCurrentKeybobrdFocusMbnbger();
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            if (event instbnceof FocusEvent || event instbnceof WindowEvent) {
                focusLog.finer(">>> {0}", String.vblueOf(event));
            }
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER) && event instbnceof KeyEvent) {
                focusLog.finer("    focus owner is {0}",
                               String.vblueOf(mbnbger.getGlobblFocusOwner()));
                focusLog.finer(">>> {0}", String.vblueOf(event));
            }
        }

        synchronized(hebvyweightRequests) {
            /*
             * This code hbndles FOCUS_LOST event which is generbted by
             * DefbultKeybobrdFocusMbnbger for FOCUS_GAINED.
             *
             * This code bbsed on knowledge of DefbultKeybobrdFocusMbnbger's
             * implementbtion bnd might be not bpplicbble for bnother
             * KeybobrdFocusMbnbger.
             *
             * Fix for 4472032
             */
            if (newFocusOwner != null &&
                event.getID() == FocusEvent.FOCUS_LOST)
            {
                FocusEvent fe = (FocusEvent)event;

                if (mbnbger.getGlobblFocusOwner() == fe.getComponent() &&
                    fe.getOppositeComponent() == newFocusOwner)
                {
                    newFocusOwner = null;
                    return event;
                }
            }
        }

        processCurrentLightweightRequests();

        switch (event.getID()) {
            cbse FocusEvent.FOCUS_GAINED: {
                event = retbrgetFocusGbined((FocusEvent)event);
                brebk;
            }
            cbse FocusEvent.FOCUS_LOST: {
                event = retbrgetFocusLost((FocusEvent)event);
                brebk;
            }
            defbult:
                /* do nothing */
        }
        return event;
    }

    /**
     * Clebrs mbrkers queue
     * This method is not intended to be overridden by KFM's.
     * Only DefbultKeybobrdFocusMbnbger cbn implement it.
     * @since 1.5
     */
    void clebrMbrkers() {
    }

    stbtic boolebn removeFirstRequest() {
        KeybobrdFocusMbnbger mbnbger =
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();

        synchronized(hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getFirstHWRequest();

            if (hwFocusRequest != null) {
                hebvyweightRequests.removeFirst();
                if (hwFocusRequest.lightweightRequests != null) {
                    for (Iterbtor<KeybobrdFocusMbnbger.LightweightFocusRequest> lwIter = hwFocusRequest.lightweightRequests.
                             iterbtor();
                         lwIter.hbsNext(); )
                    {
                        mbnbger.dequeueKeyEvents
                            (-1, lwIter.next().
                             component);
                    }
                }
            }
            // Fix for 4799136 - clebr type-bhebd mbrkers if requests queue is empty
            // We do it here becbuse this method is cblled only when problems hbppen
            if (hebvyweightRequests.size() == 0) {
                mbnbger.clebrMbrkers();
            }
            return (hebvyweightRequests.size() > 0);
        }
    }
    stbtic void removeLbstFocusRequest(Component hebvyweight) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            if (hebvyweight == null) {
                log.fine("Assertion (hebvyweight != null) fbiled");
            }
        }

        KeybobrdFocusMbnbger mbnbger =
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
        synchronized(hebvyweightRequests) {
            HebvyweightFocusRequest hwFocusRequest = getLbstHWRequest();
            if (hwFocusRequest != null &&
                hwFocusRequest.hebvyweight == hebvyweight) {
                hebvyweightRequests.removeLbst();
            }
            // Fix for 4799136 - clebr type-bhebd mbrkers if requests queue is empty
            // We do it here becbuse this method is cblled only when problems hbppen
            if (hebvyweightRequests.size() == 0) {
                mbnbger.clebrMbrkers();
            }
        }
    }

    privbte stbtic boolebn focusedWindowChbnged(Component to, Component from) {
        Window wto = SunToolkit.getContbiningWindow(to);
        Window wfrom = SunToolkit.getContbiningWindow(from);
        if (wto == null && wfrom == null) {
            return true;
        }
        if (wto == null) {
            return true;
        }
        if (wfrom == null) {
            return true;
        }
        return (wto != wfrom);
    }

    privbte stbtic boolebn isTemporbry(Component to, Component from) {
        Window wto = SunToolkit.getContbiningWindow(to);
        Window wfrom = SunToolkit.getContbiningWindow(from);
        if (wto == null && wfrom == null) {
            return fblse;
        }
        if (wto == null) {
            return true;
        }
        if (wfrom == null) {
            return fblse;
        }
        return (wto != wfrom);
    }

    stbtic Component getHebvyweight(Component comp) {
        if (comp == null || comp.getPeer() == null) {
            return null;
        } else if (comp.getPeer() instbnceof LightweightPeer) {
            return comp.getNbtiveContbiner();
        } else {
            return comp;
        }
    }

    stbtic Field proxyActive;
    // Accessor to privbte field isProxyActive of KeyEvent
    privbte stbtic boolebn isProxyActiveImpl(KeyEvent e) {
        if (proxyActive == null) {
            proxyActive =  AccessController.doPrivileged(new PrivilegedAction<Field>() {
                    public Field run() {
                        Field field = null;
                        try {
                            field = KeyEvent.clbss.getDeclbredField("isProxyActive");
                            if (field != null) {
                                field.setAccessible(true);
                            }
                        } cbtch (NoSuchFieldException nsf) {
                            bssert(fblse);
                        }
                        return field;
                    }
                });
        }

        try {
            return proxyActive.getBoolebn(e);
        } cbtch (IllegblAccessException ibe) {
            bssert(fblse);
        }
        return fblse;
    }

    // Returns the vblue of this KeyEvent's field isProxyActive
    stbtic boolebn isProxyActive(KeyEvent e) {
        if (!GrbphicsEnvironment.isHebdless()) {
            return isProxyActiveImpl(e);
        } else {
            return fblse;
        }
    }

    privbte stbtic HebvyweightFocusRequest getLbstHWRequest() {
        synchronized(hebvyweightRequests) {
            return (hebvyweightRequests.size() > 0)
                ? hebvyweightRequests.getLbst()
                : null;
        }
    }

    privbte stbtic HebvyweightFocusRequest getFirstHWRequest() {
        synchronized(hebvyweightRequests) {
            return (hebvyweightRequests.size() > 0)
                ? hebvyweightRequests.getFirst()
                : null;
        }
    }

    privbte stbtic void checkReplbceKFMPermission()
        throws SecurityException
    {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            if (replbceKeybobrdFocusMbnbgerPermission == null) {
                replbceKeybobrdFocusMbnbgerPermission =
                    new AWTPermission("replbceKeybobrdFocusMbnbger");
            }
            security.
                checkPermission(replbceKeybobrdFocusMbnbgerPermission);
        }
    }

    // Checks if this KeybobrdFocusMbnbger instbnce is the current KFM,
    // or otherwise checks if the cblling threbd hbs "replbceKeybobrdFocusMbnbger"
    // permission. Here's the rebsoning to do so:
    //
    // A system KFM instbnce (which is the current KFM by defbult) mby hbve no
    // "replbceKFM" permission when b client code is on the cbll stbck benebth,
    // but still it should be bble to execute the methods protected by this check
    // due to the system KFM is trusted (bnd so it does like "privileged").
    //
    // If this KFM instbnce is not the current KFM but the client code hbs bll
    // permissions we cbn't throw SecurityException becbuse it would contrbdict
    // the security concepts. In this cbse the trusted client code is responsible
    // for cblling the secured methods from KFM instbnce which is not current.
    privbte void checkKFMSecurity()
        throws SecurityException
    {
        if (this != getCurrentKeybobrdFocusMbnbger()) {
            checkReplbceKFMPermission();
        }
    }
}
