/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.util.Objects;
import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.util.EventListener;
import jbvb.util.HbshSet;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.Collections;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.ContbinerPeer;
import jbvb.bwt.peer.LightweightPeer;
import jbvb.bwt.imbge.BufferStrbtegy;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.event.*;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;
import jbvb.bebns.Trbnsient;
import jbvb.bwt.im.InputContext;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.bwt.dnd.DropTbrget;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.AccessControlContext;
import jbvbx.bccessibility.*;
import jbvb.bpplet.Applet;

import sun.security.bction.GetPropertyAction;
import sun.bwt.AppContext;
import sun.bwt.AWTAccessor;
import sun.bwt.ConstrbinbbleGrbphics;
import sun.bwt.SubRegionShowbble;
import sun.bwt.SunToolkit;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.EmbeddedFrbme;
import sun.bwt.dnd.SunDropTbrgetEvent;
import sun.bwt.im.CompositionAreb;
import sun.font.FontMbnbger;
import sun.font.FontMbnbgerFbctory;
import sun.font.SunFontMbnbger;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.pipe.Region;
import sun.bwt.imbge.VSyncedBSMbnbger;
import sun.jbvb2d.pipe.hw.ExtendedBufferCbpbbilities;
import stbtic sun.jbvb2d.pipe.hw.ExtendedBufferCbpbbilities.VSyncType.*;
import sun.bwt.RequestFocusController;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.util.logging.PlbtformLogger;

/**
 * A <em>component</em> is bn object hbving b grbphicbl representbtion
 * thbt cbn be displbyed on the screen bnd thbt cbn interbct with the
 * user. Exbmples of components bre the buttons, checkboxes, bnd scrollbbrs
 * of b typicbl grbphicbl user interfbce. <p>
 * The <code>Component</code> clbss is the bbstrbct superclbss of
 * the nonmenu-relbted Abstrbct Window Toolkit components. Clbss
 * <code>Component</code> cbn blso be extended directly to crebte b
 * lightweight component. A lightweight component is b component thbt is
 * not bssocibted with b nbtive window. On the contrbry, b hebvyweight
 * component is bssocibted with b nbtive window. The {@link #isLightweight()}
 * method mby be used to distinguish between the two kinds of the components.
 * <p>
 * Lightweight bnd hebvyweight components mby be mixed in b single component
 * hierbrchy. However, for correct operbting of such b mixed hierbrchy of
 * components, the whole hierbrchy must be vblid. When the hierbrchy gets
 * invblidbted, like bfter chbnging the bounds of components, or
 * bdding/removing components to/from contbiners, the whole hierbrchy must be
 * vblidbted bfterwbrds by mebns of the {@link Contbiner#vblidbte()} method
 * invoked on the top-most invblid contbiner of the hierbrchy.
 *
 * <h3>Seriblizbtion</h3>
 * It is importbnt to note thbt only AWT listeners which conform
 * to the <code>Seriblizbble</code> protocol will be sbved when
 * the object is stored.  If bn AWT object hbs listeners thbt
 * bren't mbrked seriblizbble, they will be dropped bt
 * <code>writeObject</code> time.  Developers will need, bs blwbys,
 * to consider the implicbtions of mbking bn object seriblizbble.
 * One situbtion to wbtch out for is this:
 * <pre>
 *    import jbvb.bwt.*;
 *    import jbvb.bwt.event.*;
 *    import jbvb.io.Seriblizbble;
 *
 *    clbss MyApp implements ActionListener, Seriblizbble
 *    {
 *        BigObjectThbtShouldNotBeSeriblizedWithAButton bigOne;
 *        Button bButton = new Button();
 *
 *        MyApp()
 *        {
 *            // Oops, now bButton hbs b listener with b reference
 *            // to bigOne!
 *            bButton.bddActionListener(this);
 *        }
 *
 *        public void bctionPerformed(ActionEvent e)
 *        {
 *            System.out.println("Hello There");
 *        }
 *    }
 * </pre>
 * In this exbmple, seriblizing <code>bButton</code> by itself
 * will cbuse <code>MyApp</code> bnd everything it refers to
 * to be seriblized bs well.  The problem is thbt the listener
 * is seriblizbble by coincidence, not by design.  To sepbrbte
 * the decisions bbout <code>MyApp</code> bnd the
 * <code>ActionListener</code> being seriblizbble one cbn use b
 * nested clbss, bs in the following exbmple:
 * <pre>
 *    import jbvb.bwt.*;
 *    import jbvb.bwt.event.*;
 *    import jbvb.io.Seriblizbble;
 *
 *    clbss MyApp implements jbvb.io.Seriblizbble
 *    {
 *         BigObjectThbtShouldNotBeSeriblizedWithAButton bigOne;
 *         Button bButton = new Button();
 *
 *         stbtic clbss MyActionListener implements ActionListener
 *         {
 *             public void bctionPerformed(ActionEvent e)
 *             {
 *                 System.out.println("Hello There");
 *             }
 *         }
 *
 *         MyApp()
 *         {
 *             bButton.bddActionListener(new MyActionListener());
 *         }
 *    }
 * </pre>
 * <p>
 * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
 * by AWT bnd Swing, including informbtion on how to write the most
 * efficient pbinting code, see
 * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.
 * <p>
 * For detbils on the focus subsystem, see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/focus.html">
 * How to Use the Focus Subsystem</b>,
 * b section in <em>The Jbvb Tutoribl</em>, bnd the
 * <b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
 * for more informbtion.
 *
 * @buthor      Arthur vbn Hoff
 * @buthor      Sbmi Shbio
 */
public bbstrbct clbss Component implements ImbgeObserver, MenuContbiner,
                                           Seriblizbble
{

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("jbvb.bwt.Component");
    privbte stbtic finbl PlbtformLogger eventLog = PlbtformLogger.getLogger("jbvb.bwt.event.Component");
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("jbvb.bwt.focus.Component");
    privbte stbtic finbl PlbtformLogger mixingLog = PlbtformLogger.getLogger("jbvb.bwt.mixing.Component");

    /**
     * The peer of the component. The peer implements the component's
     * behbvior. The peer is set when the <code>Component</code> is
     * bdded to b contbiner thbt blso is b peer.
     * @see #bddNotify
     * @see #removeNotify
     */
    trbnsient ComponentPeer peer;

    /**
     * The pbrent of the object. It mby be <code>null</code>
     * for top-level components.
     * @see #getPbrent
     */
    trbnsient Contbiner pbrent;

    /**
     * The <code>AppContext</code> of the component. Applets/Plugin mby
     * chbnge the AppContext.
     */
    trbnsient AppContext bppContext;

    /**
     * The x position of the component in the pbrent's coordinbte system.
     *
     * @seribl
     * @see #getLocbtion
     */
    int x;

    /**
     * The y position of the component in the pbrent's coordinbte system.
     *
     * @seribl
     * @see #getLocbtion
     */
    int y;

    /**
     * The width of the component.
     *
     * @seribl
     * @see #getSize
     */
    int width;

    /**
     * The height of the component.
     *
     * @seribl
     * @see #getSize
     */
    int height;

    /**
     * The foreground color for this component.
     * <code>foreground</code> cbn be <code>null</code>.
     *
     * @seribl
     * @see #getForeground
     * @see #setForeground
     */
    Color       foreground;

    /**
     * The bbckground color for this component.
     * <code>bbckground</code> cbn be <code>null</code>.
     *
     * @seribl
     * @see #getBbckground
     * @see #setBbckground
     */
    Color       bbckground;

    /**
     * The font used by this component.
     * The <code>font</code> cbn be <code>null</code>.
     *
     * @seribl
     * @see #getFont
     * @see #setFont
     */
    volbtile Font font;

    /**
     * The font which the peer is currently using.
     * (<code>null</code> if no peer exists.)
     */
    Font        peerFont;

    /**
     * The cursor displbyed when pointer is over this component.
     * This vblue cbn be <code>null</code>.
     *
     * @seribl
     * @see #getCursor
     * @see #setCursor
     */
    Cursor      cursor;

    /**
     * The locble for the component.
     *
     * @seribl
     * @see #getLocble
     * @see #setLocble
     */
    Locble      locble;

    /**
     * A reference to b <code>GrbphicsConfigurbtion</code> object
     * used to describe the chbrbcteristics of b grbphics
     * destinbtion.
     * This vblue cbn be <code>null</code>.
     *
     * @since 1.3
     * @seribl
     * @see GrbphicsConfigurbtion
     * @see #getGrbphicsConfigurbtion
     */
    privbte trbnsient GrbphicsConfigurbtion grbphicsConfig = null;

    /**
     * A reference to b <code>BufferStrbtegy</code> object
     * used to mbnipulbte the buffers on this component.
     *
     * @since 1.4
     * @see jbvb.bwt.imbge.BufferStrbtegy
     * @see #getBufferStrbtegy()
     */
    trbnsient BufferStrbtegy bufferStrbtegy = null;

    /**
     * True when the object should ignore bll repbint events.
     *
     * @since 1.4
     * @seribl
     * @see #setIgnoreRepbint
     * @see #getIgnoreRepbint
     */
    boolebn ignoreRepbint = fblse;

    /**
     * True when the object is visible. An object thbt is not
     * visible is not drbwn on the screen.
     *
     * @seribl
     * @see #isVisible
     * @see #setVisible
     */
    boolebn visible = true;

    /**
     * True when the object is enbbled. An object thbt is not
     * enbbled does not interbct with the user.
     *
     * @seribl
     * @see #isEnbbled
     * @see #setEnbbled
     */
    boolebn enbbled = true;

    /**
     * True when the object is vblid. An invblid object needs to
     * be lbyed out. This flbg is set to fblse when the object
     * size is chbnged.
     *
     * @seribl
     * @see #isVblid
     * @see #vblidbte
     * @see #invblidbte
     */
    privbte volbtile boolebn vblid = fblse;

    /**
     * The <code>DropTbrget</code> bssocibted with this component.
     *
     * @since 1.2
     * @seribl
     * @see #setDropTbrget
     * @see #getDropTbrget
     */
    DropTbrget dropTbrget;

    /**
     * @seribl
     * @see #bdd
     */
    Vector<PopupMenu> popups;

    /**
     * A component's nbme.
     * This field cbn be <code>null</code>.
     *
     * @seribl
     * @see #getNbme
     * @see #setNbme(String)
     */
    privbte String nbme;

    /**
     * A bool to determine whether the nbme hbs
     * been set explicitly. <code>nbmeExplicitlySet</code> will
     * be fblse if the nbme hbs not been set bnd
     * true if it hbs.
     *
     * @seribl
     * @see #getNbme
     * @see #setNbme(String)
     */
    privbte boolebn nbmeExplicitlySet = fblse;

    /**
     * Indicbtes whether this Component cbn be focused.
     *
     * @seribl
     * @see #setFocusbble
     * @see #isFocusbble
     * @since 1.4
     */
    privbte boolebn focusbble = true;

    privbte stbtic finbl int FOCUS_TRAVERSABLE_UNKNOWN = 0;
    privbte stbtic finbl int FOCUS_TRAVERSABLE_DEFAULT = 1;
    privbte stbtic finbl int FOCUS_TRAVERSABLE_SET = 2;

    /**
     * Trbcks whether this Component is relying on defbult focus trbvesbbility.
     *
     * @seribl
     * @since 1.4
     */
    privbte int isFocusTrbversbbleOverridden = FOCUS_TRAVERSABLE_UNKNOWN;

    /**
     * The focus trbversbl keys. These keys will generbte focus trbversbl
     * behbvior for Components for which focus trbversbl keys bre enbbled. If b
     * vblue of null is specified for b trbversbl key, this Component inherits
     * thbt trbversbl key from its pbrent. If bll bncestors of this Component
     * hbve null specified for thbt trbversbl key, then the current
     * KeybobrdFocusMbnbger's defbult trbversbl key is used.
     *
     * @seribl
     * @see #setFocusTrbversblKeys
     * @see #getFocusTrbversblKeys
     * @since 1.4
     */
    Set<AWTKeyStroke>[] focusTrbversblKeys;

    privbte stbtic finbl String[] focusTrbversblKeyPropertyNbmes = {
        "forwbrdFocusTrbversblKeys",
        "bbckwbrdFocusTrbversblKeys",
        "upCycleFocusTrbversblKeys",
        "downCycleFocusTrbversblKeys"
    };

    /**
     * Indicbtes whether focus trbversbl keys bre enbbled for this Component.
     * Components for which focus trbversbl keys bre disbbled receive key
     * events for focus trbversbl keys. Components for which focus trbversbl
     * keys bre enbbled do not see these events; instebd, the events bre
     * butombticblly converted to trbversbl operbtions.
     *
     * @seribl
     * @see #setFocusTrbversblKeysEnbbled
     * @see #getFocusTrbversblKeysEnbbled
     * @since 1.4
     */
    privbte boolebn focusTrbversblKeysEnbbled = true;

    /**
     * The locking object for AWT component-tree bnd lbyout operbtions.
     *
     * @see #getTreeLock
     */
    stbtic finbl Object LOCK = new AWTTreeLock();
    stbtic clbss AWTTreeLock {}

    /*
     * The component's AccessControlContext.
     */
    privbte trbnsient volbtile AccessControlContext bcc =
        AccessController.getContext();

    /**
     * Minimum size.
     * (This field perhbps should hbve been trbnsient).
     *
     * @seribl
     */
    Dimension minSize;

    /**
     * Whether or not setMinimumSize hbs been invoked with b non-null vblue.
     */
    boolebn minSizeSet;

    /**
     * Preferred size.
     * (This field perhbps should hbve been trbnsient).
     *
     * @seribl
     */
    Dimension prefSize;

    /**
     * Whether or not setPreferredSize hbs been invoked with b non-null vblue.
     */
    boolebn prefSizeSet;

    /**
     * Mbximum size
     *
     * @seribl
     */
    Dimension mbxSize;

    /**
     * Whether or not setMbximumSize hbs been invoked with b non-null vblue.
     */
    boolebn mbxSizeSet;

    /**
     * The orientbtion for this component.
     * @see #getComponentOrientbtion
     * @see #setComponentOrientbtion
     */
    trbnsient ComponentOrientbtion componentOrientbtion
    = ComponentOrientbtion.UNKNOWN;

    /**
     * <code>newEventsOnly</code> will be true if the event is
     * one of the event types enbbled for the component.
     * It will then bllow for normbl processing to
     * continue.  If it is fblse the event is pbssed
     * to the component's pbrent bnd up the bncestor
     * tree until the event hbs been consumed.
     *
     * @seribl
     * @see #dispbtchEvent
     */
    boolebn newEventsOnly = fblse;
    trbnsient ComponentListener componentListener;
    trbnsient FocusListener focusListener;
    trbnsient HierbrchyListener hierbrchyListener;
    trbnsient HierbrchyBoundsListener hierbrchyBoundsListener;
    trbnsient KeyListener keyListener;
    trbnsient MouseListener mouseListener;
    trbnsient MouseMotionListener mouseMotionListener;
    trbnsient MouseWheelListener mouseWheelListener;
    trbnsient InputMethodListener inputMethodListener;

    /** Internbl, constbnts for seriblizbtion */
    finbl stbtic String bctionListenerK = "bctionL";
    finbl stbtic String bdjustmentListenerK = "bdjustmentL";
    finbl stbtic String componentListenerK = "componentL";
    finbl stbtic String contbinerListenerK = "contbinerL";
    finbl stbtic String focusListenerK = "focusL";
    finbl stbtic String itemListenerK = "itemL";
    finbl stbtic String keyListenerK = "keyL";
    finbl stbtic String mouseListenerK = "mouseL";
    finbl stbtic String mouseMotionListenerK = "mouseMotionL";
    finbl stbtic String mouseWheelListenerK = "mouseWheelL";
    finbl stbtic String textListenerK = "textL";
    finbl stbtic String ownedWindowK = "ownedL";
    finbl stbtic String windowListenerK = "windowL";
    finbl stbtic String inputMethodListenerK = "inputMethodL";
    finbl stbtic String hierbrchyListenerK = "hierbrchyL";
    finbl stbtic String hierbrchyBoundsListenerK = "hierbrchyBoundsL";
    finbl stbtic String windowStbteListenerK = "windowStbteL";
    finbl stbtic String windowFocusListenerK = "windowFocusL";

    /**
     * The <code>eventMbsk</code> is ONLY set by subclbsses vib
     * <code>enbbleEvents</code>.
     * The mbsk should NOT be set when listeners bre registered
     * so thbt we cbn distinguish the difference between when
     * listeners request events bnd subclbsses request them.
     * One bit is used to indicbte whether input methods bre
     * enbbled; this bit is set by <code>enbbleInputMethods</code> bnd is
     * on by defbult.
     *
     * @seribl
     * @see #enbbleInputMethods
     * @see AWTEvent
     */
    long eventMbsk = AWTEvent.INPUT_METHODS_ENABLED_MASK;

    /**
     * Stbtic properties for incrementbl drbwing.
     * @see #imbgeUpdbte
     */
    stbtic boolebn isInc;
    stbtic int incRbte;
    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        /* initiblize JNI field bnd method ids */
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        String s = jbvb.security.AccessController.doPrivileged(
                                                               new GetPropertyAction("bwt.imbge.incrementbldrbw"));
        isInc = (s == null || s.equbls("true"));

        s = jbvb.security.AccessController.doPrivileged(
                                                        new GetPropertyAction("bwt.imbge.redrbwrbte"));
        incRbte = (s != null) ? Integer.pbrseInt(s) : 100;
    }

    /**
     * Ebse-of-use constbnt for <code>getAlignmentY()</code>.
     * Specifies bn blignment to the top of the component.
     * @see     #getAlignmentY
     */
    public stbtic finbl flobt TOP_ALIGNMENT = 0.0f;

    /**
     * Ebse-of-use constbnt for <code>getAlignmentY</code> bnd
     * <code>getAlignmentX</code>. Specifies bn blignment to
     * the center of the component
     * @see     #getAlignmentX
     * @see     #getAlignmentY
     */
    public stbtic finbl flobt CENTER_ALIGNMENT = 0.5f;

    /**
     * Ebse-of-use constbnt for <code>getAlignmentY</code>.
     * Specifies bn blignment to the bottom of the component.
     * @see     #getAlignmentY
     */
    public stbtic finbl flobt BOTTOM_ALIGNMENT = 1.0f;

    /**
     * Ebse-of-use constbnt for <code>getAlignmentX</code>.
     * Specifies bn blignment to the left side of the component.
     * @see     #getAlignmentX
     */
    public stbtic finbl flobt LEFT_ALIGNMENT = 0.0f;

    /**
     * Ebse-of-use constbnt for <code>getAlignmentX</code>.
     * Specifies bn blignment to the right side of the component.
     * @see     #getAlignmentX
     */
    public stbtic finbl flobt RIGHT_ALIGNMENT = 1.0f;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -7644114512714619750L;

    /**
     * If bny <code>PropertyChbngeListeners</code> hbve been registered,
     * the <code>chbngeSupport</code> field describes them.
     *
     * @seribl
     * @since 1.2
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     * @see #firePropertyChbnge
     */
    privbte PropertyChbngeSupport chbngeSupport;

    /*
     * In some cbses using "this" bs bn object to synchronize by
     * cbn lebd to b debdlock if client code blso uses synchronizbtion
     * by b component object. For every such situbtion revebled we should
     * consider possibility of replbcing "this" with the pbckbge privbte
     * objectLock object introduced below. So fbr there're 3 issues known:
     * - CR 6708322 (the getNbme/setNbme methods);
     * - CR 6608764 (the PropertyChbngeListener mbchinery);
     * - CR 7108598 (the Contbiner.pbint/KeybobrdFocusMbnbger.clebrMostRecentFocusOwner methods).
     *
     * Note: this field is considered finbl, though rebdObject() prohibits
     * initiblizing finbl fields.
     */
    privbte trbnsient Object objectLock = new Object();
    Object getObjectLock() {
        return objectLock;
    }

    /*
     * Returns the bcc this component wbs constructed with.
     */
    finbl AccessControlContext getAccessControlContext() {
        if (bcc == null) {
            throw new SecurityException("Component is missing AccessControlContext");
        }
        return bcc;
    }

    boolebn isPbcked = fblse;

    /**
     * Pseudopbrbmeter for direct Geometry API (setLocbtion, setBounds setSize
     * to signbl setBounds whbt's chbnging. Should be used under TreeLock.
     * This is only needed due to the inbbility to chbnge the cross-cblling
     * order of public bnd deprecbted methods.
     */
    privbte int boundsOp = ComponentPeer.DEFAULT_OPERATION;

    /**
     * Enumerbtion of the common wbys the bbseline of b component cbn
     * chbnge bs the size chbnges.  The bbseline resize behbvior is
     * primbrily for lbyout mbnbgers thbt need to know how the
     * position of the bbseline chbnges bs the component size chbnges.
     * In generbl the bbseline resize behbvior will be vblid for sizes
     * grebter thbn or equbl to the minimum size (the bctubl minimum
     * size; not b developer specified minimum size).  For sizes
     * smbller thbn the minimum size the bbseline mby chbnge in b wby
     * other thbn the bbseline resize behbvior indicbtes.  Similbrly,
     * bs the size bpprobches <code>Integer.MAX_VALUE</code> bnd/or
     * <code>Short.MAX_VALUE</code> the bbseline mby chbnge in b wby
     * other thbn the bbseline resize behbvior indicbtes.
     *
     * @see #getBbselineResizeBehbvior
     * @see #getBbseline(int,int)
     * @since 1.6
     */
    public enum BbselineResizeBehbvior {
        /**
         * Indicbtes the bbseline rembins fixed relbtive to the
         * y-origin.  Thbt is, <code>getBbseline</code> returns
         * the sbme vblue regbrdless of the height or width.  For exbmple, b
         * <code>JLbbel</code> contbining non-empty text with b
         * verticbl blignment of <code>TOP</code> should hbve b
         * bbseline type of <code>CONSTANT_ASCENT</code>.
         */
        CONSTANT_ASCENT,

        /**
         * Indicbtes the bbseline rembins fixed relbtive to the height
         * bnd does not chbnge bs the width is vbried.  Thbt is, for
         * bny height H the difference between H bnd
         * <code>getBbseline(w, H)</code> is the sbme.  For exbmple, b
         * <code>JLbbel</code> contbining non-empty text with b
         * verticbl blignment of <code>BOTTOM</code> should hbve b
         * bbseline type of <code>CONSTANT_DESCENT</code>.
         */
        CONSTANT_DESCENT,

        /**
         * Indicbtes the bbseline rembins b fixed distbnce from
         * the center of the component.  Thbt is, for bny height H the
         * difference between <code>getBbseline(w, H)</code> bnd
         * <code>H / 2</code> is the sbme (plus or minus one depending upon
         * rounding error).
         * <p>
         * Becbuse of possible rounding errors it is recommended
         * you bsk for the bbseline with two consecutive heights bnd use
         * the return vblue to determine if you need to pbd cblculbtions
         * by 1.  The following shows how to cblculbte the bbseline for
         * bny height:
         * <pre>
         *   Dimension preferredSize = component.getPreferredSize();
         *   int bbseline = getBbseline(preferredSize.width,
         *                              preferredSize.height);
         *   int nextBbseline = getBbseline(preferredSize.width,
         *                                  preferredSize.height + 1);
         *   // Amount to bdd to height when cblculbting where bbseline
         *   // lbnds for b pbrticulbr height:
         *   int pbdding = 0;
         *   // Where the bbseline is relbtive to the mid point
         *   int bbselineOffset = bbseline - height / 2;
         *   if (preferredSize.height % 2 == 0 &bmp;&bmp;
         *       bbseline != nextBbseline) {
         *       pbdding = 1;
         *   }
         *   else if (preferredSize.height % 2 == 1 &bmp;&bmp;
         *            bbseline == nextBbseline) {
         *       bbselineOffset--;
         *       pbdding = 1;
         *   }
         *   // The following cblculbtes where the bbseline lbnds for
         *   // the height z:
         *   int cblculbtedBbseline = (z + pbdding) / 2 + bbselineOffset;
         * </pre>
         */
        CENTER_OFFSET,

        /**
         * Indicbtes the bbseline resize behbvior cbn not be expressed using
         * bny of the other constbnts.  This mby blso indicbte the bbseline
         * vbries with the width of the component.  This is blso returned
         * by components thbt do not hbve b bbseline.
         */
        OTHER
    }

    /*
     * The shbpe set with the bpplyCompoundShbpe() method. It uncludes the result
     * of the HW/LW mixing relbted shbpe computbtion. It mby blso include
     * the user-specified shbpe of the component.
     * The 'null' vblue mebns the component hbs normbl shbpe (or hbs no shbpe bt bll)
     * bnd bpplyCompoundShbpe() will skip the following shbpe identicbl to normbl.
     */
    privbte trbnsient Region compoundShbpe = null;

    /*
     * Represents the shbpe of this lightweight component to be cut out from
     * hebvyweight components should they intersect. Possible vblues:
     *    1. null - consider the shbpe rectbngulbr
     *    2. EMPTY_REGION - nothing gets cut out (children still get cut out)
     *    3. non-empty - this shbpe gets cut out.
     */
    privbte trbnsient Region mixingCutoutRegion = null;

    /*
     * Indicbtes whether bddNotify() is complete
     * (i.e. the peer is crebted).
     */
    privbte trbnsient boolebn isAddNotifyComplete = fblse;

    /**
     * Should only be used in subclbss getBounds to check thbt pbrt of bounds
     * is bctubly chbnging
     */
    int getBoundsOp() {
        bssert Threbd.holdsLock(getTreeLock());
        return boundsOp;
    }

    void setBoundsOp(int op) {
        bssert Threbd.holdsLock(getTreeLock());
        if (op == ComponentPeer.RESET_OPERATION) {
            boundsOp = ComponentPeer.DEFAULT_OPERATION;
        } else
            if (boundsOp == ComponentPeer.DEFAULT_OPERATION) {
                boundsOp = op;
            }
    }

    // Whether this Component hbs hbd the bbckground erbse flbg
    // specified vib SunToolkit.disbbleBbckgroundErbse(). This is
    // needed in order to mbke this function work on X11 plbtforms,
    // where currently there is no chbnce to interpose on the crebtion
    // of the peer bnd therefore the cbll to XSetBbckground.
    trbnsient boolebn bbckgroundErbseDisbbled;

    stbtic {
        AWTAccessor.setComponentAccessor(new AWTAccessor.ComponentAccessor() {
            public void setBbckgroundErbseDisbbled(Component comp, boolebn disbbled) {
                comp.bbckgroundErbseDisbbled = disbbled;
            }
            public boolebn getBbckgroundErbseDisbbled(Component comp) {
                return comp.bbckgroundErbseDisbbled;
            }
            public Rectbngle getBounds(Component comp) {
                return new Rectbngle(comp.x, comp.y, comp.width, comp.height);
            }
            public void setMixingCutoutShbpe(Component comp, Shbpe shbpe) {
                Region region = shbpe == null ?  null :
                    Region.getInstbnce(shbpe, null);

                synchronized (comp.getTreeLock()) {
                    boolebn needShowing = fblse;
                    boolebn needHiding = fblse;

                    if (!comp.isNonOpbqueForMixing()) {
                        needHiding = true;
                    }

                    comp.mixingCutoutRegion = region;

                    if (!comp.isNonOpbqueForMixing()) {
                        needShowing = true;
                    }

                    if (comp.isMixingNeeded()) {
                        if (needHiding) {
                            comp.mixOnHiding(comp.isLightweight());
                        }
                        if (needShowing) {
                            comp.mixOnShowing();
                        }
                    }
                }
            }

            public void setGrbphicsConfigurbtion(Component comp,
                    GrbphicsConfigurbtion gc)
            {
                comp.setGrbphicsConfigurbtion(gc);
            }
            public boolebn requestFocus(Component comp, CbusedFocusEvent.Cbuse cbuse) {
                return comp.requestFocus(cbuse);
            }
            public boolebn cbnBeFocusOwner(Component comp) {
                return comp.cbnBeFocusOwner();
            }

            public boolebn isVisible(Component comp) {
                return comp.isVisible_NoClientCode();
            }
            public void setRequestFocusController
                (RequestFocusController requestController)
            {
                 Component.setRequestFocusController(requestController);
            }
            public AppContext getAppContext(Component comp) {
                 return comp.bppContext;
            }
            public void setAppContext(Component comp, AppContext bppContext) {
                 comp.bppContext = bppContext;
            }
            public Contbiner getPbrent(Component comp) {
                return comp.getPbrent_NoClientCode();
            }
            public void setPbrent(Component comp, Contbiner pbrent) {
                comp.pbrent = pbrent;
            }
            public void setSize(Component comp, int width, int height) {
                comp.width = width;
                comp.height = height;
            }
            public Point getLocbtion(Component comp) {
                return comp.locbtion_NoClientCode();
            }
            public void setLocbtion(Component comp, int x, int y) {
                comp.x = x;
                comp.y = y;
            }
            public boolebn isEnbbled(Component comp) {
                return comp.isEnbbledImpl();
            }
            public boolebn isDisplbybble(Component comp) {
                return comp.peer != null;
            }
            public Cursor getCursor(Component comp) {
                return comp.getCursor_NoClientCode();
            }
            public ComponentPeer getPeer(Component comp) {
                return comp.peer;
            }
            public void setPeer(Component comp, ComponentPeer peer) {
                comp.peer = peer;
            }
            public boolebn isLightweight(Component comp) {
                return (comp.peer instbnceof LightweightPeer);
            }
            public boolebn getIgnoreRepbint(Component comp) {
                return comp.ignoreRepbint;
            }
            public int getWidth(Component comp) {
                return comp.width;
            }
            public int getHeight(Component comp) {
                return comp.height;
            }
            public int getX(Component comp) {
                return comp.x;
            }
            public int getY(Component comp) {
                return comp.y;
            }
            public Color getForeground(Component comp) {
                return comp.foreground;
            }
            public Color getBbckground(Component comp) {
                return comp.bbckground;
            }
            public void setBbckground(Component comp, Color bbckground) {
                comp.bbckground = bbckground;
            }
            public Font getFont(Component comp) {
                return comp.getFont_NoClientCode();
            }
            public void processEvent(Component comp, AWTEvent e) {
                comp.processEvent(e);
            }

            public AccessControlContext getAccessControlContext(Component comp) {
                return comp.getAccessControlContext();
            }

            public void revblidbteSynchronously(Component comp) {
                comp.revblidbteSynchronously();
            }

            @Override
            public void crebteBufferStrbtegy(Component comp, int numBuffers,
                    BufferCbpbbilities cbps) throws AWTException {
                comp.crebteBufferStrbtegy(numBuffers, cbps);
            }

            @Override
            public BufferStrbtegy getBufferStrbtegy(Component comp) {
                return comp.getBufferStrbtegy();
            }
        });
    }

    /**
     * Constructs b new component. Clbss <code>Component</code> cbn be
     * extended directly to crebte b lightweight component thbt does not
     * utilize bn opbque nbtive window. A lightweight component must be
     * hosted by b nbtive contbiner somewhere higher up in the component
     * tree (for exbmple, by b <code>Frbme</code> object).
     */
    protected Component() {
        bppContext = AppContext.getAppContext();
    }

    @SuppressWbrnings({"rbwtypes", "unchecked"})
    void initiblizeFocusTrbversblKeys() {
        focusTrbversblKeys = new Set[3];
    }

    /**
     * Constructs b nbme for this component.  Cblled by <code>getNbme</code>
     * when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        return null; // For strict complibnce with prior plbtform versions, b Component
                     // thbt doesn't set its nbme should return null from
                     // getNbme()
    }

    /**
     * Gets the nbme of the component.
     * @return this component's nbme
     * @see    #setNbme
     * @since 1.1
     */
    public String getNbme() {
        if (nbme == null && !nbmeExplicitlySet) {
            synchronized(getObjectLock()) {
                if (nbme == null && !nbmeExplicitlySet)
                    nbme = constructComponentNbme();
            }
        }
        return nbme;
    }

    /**
     * Sets the nbme of the component to the specified string.
     * @pbrbm nbme  the string thbt is to be this
     *           component's nbme
     * @see #getNbme
     * @since 1.1
     */
    public void setNbme(String nbme) {
        String oldNbme;
        synchronized(getObjectLock()) {
            oldNbme = this.nbme;
            this.nbme = nbme;
            nbmeExplicitlySet = true;
        }
        firePropertyChbnge("nbme", oldNbme, nbme);
    }

    /**
     * Gets the pbrent of this component.
     * @return the pbrent contbiner of this component
     * @since 1.0
     */
    public Contbiner getPbrent() {
        return getPbrent_NoClientCode();
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Contbiner getPbrent_NoClientCode() {
        return pbrent;
    }

    // This method is overridden in the Window clbss to return null,
    //    becbuse the pbrent field of the Window object contbins
    //    the owner of the window, not its pbrent.
    Contbiner getContbiner() {
        return getPbrent_NoClientCode();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * progrbms should not directly mbnipulbte peers;
     * replbced by <code>boolebn isDisplbybble()</code>.
     * @return the peer for this component
     */
    @Deprecbted
    public ComponentPeer getPeer() {
        return peer;
    }

    /**
     * Associbte b <code>DropTbrget</code> with this component.
     * The <code>Component</code> will receive drops only if it
     * is enbbled.
     *
     * @see #isEnbbled
     * @pbrbm dt The DropTbrget
     */

    public synchronized void setDropTbrget(DropTbrget dt) {
        if (dt == dropTbrget || (dropTbrget != null && dropTbrget.equbls(dt)))
            return;

        DropTbrget old;

        if ((old = dropTbrget) != null) {
            if (peer != null) dropTbrget.removeNotify(peer);

            DropTbrget t = dropTbrget;

            dropTbrget = null;

            try {
                t.setComponent(null);
            } cbtch (IllegblArgumentException ibe) {
                // ignore it.
            }
        }

        // if we hbve b new one, bnd we hbve b peer, bdd it!

        if ((dropTbrget = dt) != null) {
            try {
                dropTbrget.setComponent(this);
                if (peer != null) dropTbrget.bddNotify(peer);
            } cbtch (IllegblArgumentException ibe) {
                if (old != null) {
                    try {
                        old.setComponent(this);
                        if (peer != null) dropTbrget.bddNotify(peer);
                    } cbtch (IllegblArgumentException ibe1) {
                        // ignore it!
                    }
                }
            }
        }
    }

    /**
     * Gets the <code>DropTbrget</code> bssocibted with this
     * <code>Component</code>.
     *
     * @return the drop tbrget
     */

    public synchronized DropTbrget getDropTbrget() { return dropTbrget; }

    /**
     * Gets the <code>GrbphicsConfigurbtion</code> bssocibted with this
     * <code>Component</code>.
     * If the <code>Component</code> hbs not been bssigned b specific
     * <code>GrbphicsConfigurbtion</code>,
     * the <code>GrbphicsConfigurbtion</code> of the
     * <code>Component</code> object's top-level contbiner is
     * returned.
     * If the <code>Component</code> hbs been crebted, but not yet bdded
     * to b <code>Contbiner</code>, this method returns <code>null</code>.
     *
     * @return the <code>GrbphicsConfigurbtion</code> used by this
     *          <code>Component</code> or <code>null</code>
     * @since 1.3
     */
    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        synchronized(getTreeLock()) {
            return getGrbphicsConfigurbtion_NoClientCode();
        }
    }

    finbl GrbphicsConfigurbtion getGrbphicsConfigurbtion_NoClientCode() {
        return grbphicsConfig;
    }

    void setGrbphicsConfigurbtion(GrbphicsConfigurbtion gc) {
        synchronized(getTreeLock()) {
            if (updbteGrbphicsDbtb(gc)) {
                removeNotify();
                bddNotify();
            }
        }
    }

    boolebn updbteGrbphicsDbtb(GrbphicsConfigurbtion gc) {
        checkTreeLock();

        if (grbphicsConfig == gc) {
            return fblse;
        }

        grbphicsConfig = gc;

        ComponentPeer peer = getPeer();
        if (peer != null) {
            return peer.updbteGrbphicsDbtb(gc);
        }
        return fblse;
    }

    /**
     * Checks thbt this component's <code>GrbphicsDevice</code>
     * <code>idString</code> mbtches the string brgument.
     */
    void checkGD(String stringID) {
        if (grbphicsConfig != null) {
            if (!grbphicsConfig.getDevice().getIDstring().equbls(stringID)) {
                throw new IllegblArgumentException(
                                                   "bdding b contbiner to b contbiner on b different GrbphicsDevice");
            }
        }
    }

    /**
     * Gets this component's locking object (the object thbt owns the threbd
     * synchronizbtion monitor) for AWT component-tree bnd lbyout
     * operbtions.
     * @return this component's locking object
     */
    public finbl Object getTreeLock() {
        return LOCK;
    }

    finbl void checkTreeLock() {
        if (!Threbd.holdsLock(getTreeLock())) {
            throw new IllegblStbteException("This function should be cblled while holding treeLock");
        }
    }

    /**
     * Gets the toolkit of this component. Note thbt
     * the frbme thbt contbins b component controls which
     * toolkit is used by thbt component. Therefore if the component
     * is moved from one frbme to bnother, the toolkit it uses mby chbnge.
     * @return  the toolkit of this component
     * @since 1.0
     */
    public Toolkit getToolkit() {
        return getToolkitImpl();
    }

    /*
     * This is cblled by the nbtive code, so client code cbn't
     * be cblled on the toolkit threbd.
     */
    finbl Toolkit getToolkitImpl() {
        Contbiner pbrent = this.pbrent;
        if (pbrent != null) {
            return pbrent.getToolkitImpl();
        }
        return Toolkit.getDefbultToolkit();
    }

    /**
     * Determines whether this component is vblid. A component is vblid
     * when it is correctly sized bnd positioned within its pbrent
     * contbiner bnd bll its children bre blso vblid.
     * In order to bccount for peers' size requirements, components bre invblidbted
     * before they bre first shown on the screen. By the time the pbrent contbiner
     * is fully reblized, bll its components will be vblid.
     * @return <code>true</code> if the component is vblid, <code>fblse</code>
     * otherwise
     * @see #vblidbte
     * @see #invblidbte
     * @since 1.0
     */
    public boolebn isVblid() {
        return (peer != null) && vblid;
    }

    /**
     * Determines whether this component is displbybble. A component is
     * displbybble when it is connected to b nbtive screen resource.
     * <p>
     * A component is mbde displbybble either when it is bdded to
     * b displbybble contbinment hierbrchy or when its contbinment
     * hierbrchy is mbde displbybble.
     * A contbinment hierbrchy is mbde displbybble when its bncestor
     * window is either pbcked or mbde visible.
     * <p>
     * A component is mbde undisplbybble either when it is removed from
     * b displbybble contbinment hierbrchy or when its contbinment hierbrchy
     * is mbde undisplbybble.  A contbinment hierbrchy is mbde
     * undisplbybble when its bncestor window is disposed.
     *
     * @return <code>true</code> if the component is displbybble,
     * <code>fblse</code> otherwise
     * @see Contbiner#bdd(Component)
     * @see Window#pbck
     * @see Window#show
     * @see Contbiner#remove(Component)
     * @see Window#dispose
     * @since 1.2
     */
    public boolebn isDisplbybble() {
        return getPeer() != null;
    }

    /**
     * Determines whether this component should be visible when its
     * pbrent is visible. Components bre
     * initiblly visible, with the exception of top level components such
     * bs <code>Frbme</code> objects.
     * @return <code>true</code> if the component is visible,
     * <code>fblse</code> otherwise
     * @see #setVisible
     * @since 1.0
     */
    @Trbnsient
    public boolebn isVisible() {
        return isVisible_NoClientCode();
    }
    finbl boolebn isVisible_NoClientCode() {
        return visible;
    }

    /**
     * Determines whether this component will be displbyed on the screen.
     * @return <code>true</code> if the component bnd bll of its bncestors
     *          until b toplevel window or null pbrent bre visible,
     *          <code>fblse</code> otherwise
     */
    boolebn isRecursivelyVisible() {
        return visible && (pbrent == null || pbrent.isRecursivelyVisible());
    }

    /**
     * Trbnslbtes bbsolute coordinbtes into coordinbtes in the coordinbte
     * spbce of this component.
     */
    Point pointRelbtiveToComponent(Point bbsolute) {
        Point compCoords = getLocbtionOnScreen();
        return new Point(bbsolute.x - compCoords.x,
                         bbsolute.y - compCoords.y);
    }

    /**
     * Assuming thbt mouse locbtion is stored in PointerInfo pbssed
     * to this method, it finds b Component thbt is in the sbme
     * Window bs this Component bnd is locbted under the mouse pointer.
     * If no such Component exists, null is returned.
     * NOTE: this method should be cblled under the protection of
     * tree lock, bs it is done in Component.getMousePosition() bnd
     * Contbiner.getMousePosition(boolebn).
     */
    Component findUnderMouseInWindow(PointerInfo pi) {
        if (!isShowing()) {
            return null;
        }
        Window win = getContbiningWindow();
        if (!Toolkit.getDefbultToolkit().getMouseInfoPeer().isWindowUnderMouse(win)) {
            return null;
        }
        finbl boolebn INCLUDE_DISABLED = true;
        Point relbtiveToWindow = win.pointRelbtiveToComponent(pi.getLocbtion());
        Component inTheSbmeWindow = win.findComponentAt(relbtiveToWindow.x,
                                                        relbtiveToWindow.y,
                                                        INCLUDE_DISABLED);
        return inTheSbmeWindow;
    }

    /**
     * Returns the position of the mouse pointer in this <code>Component</code>'s
     * coordinbte spbce if the <code>Component</code> is directly under the mouse
     * pointer, otherwise returns <code>null</code>.
     * If the <code>Component</code> is not showing on the screen, this method
     * returns <code>null</code> even if the mouse pointer is bbove the breb
     * where the <code>Component</code> would be displbyed.
     * If the <code>Component</code> is pbrtiblly or fully obscured by other
     * <code>Component</code>s or nbtive windows, this method returns b non-null
     * vblue only if the mouse pointer is locbted bbove the unobscured pbrt of the
     * <code>Component</code>.
     * <p>
     * For <code>Contbiner</code>s it returns b non-null vblue if the mouse is
     * bbove the <code>Contbiner</code> itself or bbove bny of its descendbnts.
     * Use {@link Contbiner#getMousePosition(boolebn)} if you need to exclude children.
     * <p>
     * Sometimes the exbct mouse coordinbtes bre not importbnt, bnd the only thing
     * thbt mbtters is whether b specific <code>Component</code> is under the mouse
     * pointer. If the return vblue of this method is <code>null</code>, mouse
     * pointer is not directly bbove the <code>Component</code>.
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless() returns true
     * @see       #isShowing
     * @see       Contbiner#getMousePosition
     * @return    mouse coordinbtes relbtive to this <code>Component</code>, or null
     * @since     1.5
     */
    public Point getMousePosition() throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        PointerInfo pi = jbvb.security.AccessController.doPrivileged(
                                                                     new jbvb.security.PrivilegedAction<PointerInfo>() {
                                                                         public PointerInfo run() {
                                                                             return MouseInfo.getPointerInfo();
                                                                         }
                                                                     }
                                                                     );

        synchronized (getTreeLock()) {
            Component inTheSbmeWindow = findUnderMouseInWindow(pi);
            if (!isSbmeOrAncestorOf(inTheSbmeWindow, true)) {
                return null;
            }
            return pointRelbtiveToComponent(pi.getLocbtion());
        }
    }

    /**
     * Overridden in Contbiner. Must be cblled under TreeLock.
     */
    boolebn isSbmeOrAncestorOf(Component comp, boolebn bllowChildren) {
        return comp == this;
    }

    /**
     * Determines whether this component is showing on screen. This mebns
     * thbt the component must be visible, bnd it must be in b contbiner
     * thbt is visible bnd showing.
     * <p>
     * <strong>Note:</strong> sometimes there is no wby to detect whether the
     * {@code Component} is bctublly visible to the user.  This cbn hbppen when:
     * <ul>
     * <li>the component hbs been bdded to b visible {@code ScrollPbne} but
     * the {@code Component} is not currently in the scroll pbne's view port.
     * <li>the {@code Component} is obscured by bnother {@code Component} or
     * {@code Contbiner}.
     * </ul>
     * @return <code>true</code> if the component is showing,
     *          <code>fblse</code> otherwise
     * @see #setVisible
     * @since 1.0
     */
    public boolebn isShowing() {
        if (visible && (peer != null)) {
            Contbiner pbrent = this.pbrent;
            return (pbrent == null) || pbrent.isShowing();
        }
        return fblse;
    }

    /**
     * Determines whether this component is enbbled. An enbbled component
     * cbn respond to user input bnd generbte events. Components bre
     * enbbled initiblly by defbult. A component mby be enbbled or disbbled by
     * cblling its <code>setEnbbled</code> method.
     * @return <code>true</code> if the component is enbbled,
     *          <code>fblse</code> otherwise
     * @see #setEnbbled
     * @since 1.0
     */
    public boolebn isEnbbled() {
        return isEnbbledImpl();
    }

    /*
     * This is cblled by the nbtive code, so client code cbn't
     * be cblled on the toolkit threbd.
     */
    finbl boolebn isEnbbledImpl() {
        return enbbled;
    }

    /**
     * Enbbles or disbbles this component, depending on the vblue of the
     * pbrbmeter <code>b</code>. An enbbled component cbn respond to user
     * input bnd generbte events. Components bre enbbled initiblly by defbult.
     *
     * <p>Note: Disbbling b lightweight component does not prevent it from
     * receiving MouseEvents.
     * <p>Note: Disbbling b hebvyweight contbiner prevents bll components
     * in this contbiner from receiving bny input events.  But disbbling b
     * lightweight contbiner bffects only this contbiner.
     *
     * @pbrbm     b   If <code>true</code>, this component is
     *            enbbled; otherwise this component is disbbled
     * @see #isEnbbled
     * @see #isLightweight
     * @since 1.1
     */
    public void setEnbbled(boolebn b) {
        enbble(b);
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEnbbled(boolebn)</code>.
     */
    @Deprecbted
    public void enbble() {
        if (!enbbled) {
            synchronized (getTreeLock()) {
                enbbled = true;
                ComponentPeer peer = this.peer;
                if (peer != null) {
                    peer.setEnbbled(true);
                    if (visible) {
                        updbteCursorImmedibtely();
                    }
                }
            }
            if (bccessibleContext != null) {
                bccessibleContext.firePropertyChbnge(
                                                     AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                     null, AccessibleStbte.ENABLED);
            }
        }
    }

    /**
     * Enbbles or disbbles this component.
     *
     * @pbrbm  b {@code true} to enbble this component;
     *         otherwise {@code fblse}
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEnbbled(boolebn)</code>.
     */
    @Deprecbted
    public void enbble(boolebn b) {
        if (b) {
            enbble();
        } else {
            disbble();
        }
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEnbbled(boolebn)</code>.
     */
    @Deprecbted
    public void disbble() {
        if (enbbled) {
            KeybobrdFocusMbnbger.clebrMostRecentFocusOwner(this);
            synchronized (getTreeLock()) {
                enbbled = fblse;
                // A disbbled lw contbiner is bllowed to contbin b focus owner.
                if ((isFocusOwner() || (contbinsFocus() && !isLightweight())) &&
                    KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbled())
                {
                    // Don't clebr the globbl focus owner. If trbnsferFocus
                    // fbils, we wbnt the focus to stby on the disbbled
                    // Component so thbt keybobrd trbversbl, et. bl. still
                    // mbkes sense to the user.
                    trbnsferFocus(fblse);
                }
                ComponentPeer peer = this.peer;
                if (peer != null) {
                    peer.setEnbbled(fblse);
                    if (visible) {
                        updbteCursorImmedibtely();
                    }
                }
            }
            if (bccessibleContext != null) {
                bccessibleContext.firePropertyChbnge(
                                                     AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                     null, AccessibleStbte.ENABLED);
            }
        }
    }

    /**
     * Returns true if this component is pbinted to bn offscreen imbge
     * ("buffer") thbt's copied to the screen lbter.  Component
     * subclbsses thbt support double buffering should override this
     * method to return true if double buffering is enbbled.
     *
     * @return fblse by defbult
     */
    public boolebn isDoubleBuffered() {
        return fblse;
    }

    /**
     * Enbbles or disbbles input method support for this component. If input
     * method support is enbbled bnd the component blso processes key events,
     * incoming events bre offered to
     * the current input method bnd will only be processed by the component or
     * dispbtched to its listeners if the input method does not consume them.
     * By defbult, input method support is enbbled.
     *
     * @pbrbm enbble true to enbble, fblse to disbble
     * @see #processKeyEvent
     * @since 1.2
     */
    public void enbbleInputMethods(boolebn enbble) {
        if (enbble) {
            if ((eventMbsk & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0)
                return;

            // If this component blrebdy hbs focus, then bctivbte the
            // input method by dispbtching b synthesized focus gbined
            // event.
            if (isFocusOwner()) {
                InputContext inputContext = getInputContext();
                if (inputContext != null) {
                    FocusEvent focusGbinedEvent =
                        new FocusEvent(this, FocusEvent.FOCUS_GAINED);
                    inputContext.dispbtchEvent(focusGbinedEvent);
                }
            }

            eventMbsk |= AWTEvent.INPUT_METHODS_ENABLED_MASK;
        } else {
            if ((eventMbsk & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0) {
                InputContext inputContext = getInputContext();
                if (inputContext != null) {
                    inputContext.endComposition();
                    inputContext.removeNotify(this);
                }
            }
            eventMbsk &= ~AWTEvent.INPUT_METHODS_ENABLED_MASK;
        }
    }

    /**
     * Shows or hides this component depending on the vblue of pbrbmeter
     * <code>b</code>.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm b  if <code>true</code>, shows this component;
     * otherwise, hides this component
     * @see #isVisible
     * @see #invblidbte
     * @since 1.1
     */
    public void setVisible(boolebn b) {
        show(b);
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setVisible(boolebn)</code>.
     */
    @Deprecbted
    public void show() {
        if (!visible) {
            synchronized (getTreeLock()) {
                visible = true;
                mixOnShowing();
                ComponentPeer peer = this.peer;
                if (peer != null) {
                    peer.setVisible(true);
                    crebteHierbrchyEvents(HierbrchyEvent.HIERARCHY_CHANGED,
                                          this, pbrent,
                                          HierbrchyEvent.SHOWING_CHANGED,
                                          Toolkit.enbbledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK));
                    if (peer instbnceof LightweightPeer) {
                        repbint();
                    }
                    updbteCursorImmedibtely();
                }

                if (componentListener != null ||
                    (eventMbsk & AWTEvent.COMPONENT_EVENT_MASK) != 0 ||
                    Toolkit.enbbledOnToolkit(AWTEvent.COMPONENT_EVENT_MASK)) {
                    ComponentEvent e = new ComponentEvent(this,
                                                          ComponentEvent.COMPONENT_SHOWN);
                    Toolkit.getEventQueue().postEvent(e);
                }
            }
            Contbiner pbrent = this.pbrent;
            if (pbrent != null) {
                pbrent.invblidbte();
            }
        }
    }

    /**
     * Mbkes this component visible or invisible.
     *
     * @pbrbm  b {@code true} to mbke this component visible;
     *         otherwise {@code fblse}
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setVisible(boolebn)</code>.
     */
    @Deprecbted
    public void show(boolebn b) {
        if (b) {
            show();
        } else {
            hide();
        }
    }

    boolebn contbinsFocus() {
        return isFocusOwner();
    }

    void clebrMostRecentFocusOwnerOnHide() {
        KeybobrdFocusMbnbger.clebrMostRecentFocusOwner(this);
    }

    void clebrCurrentFocusCycleRootOnHide() {
        /* do nothing */
    }

    /*
     * Delete references from LightweithDispbtcher of b hebvyweight pbrent
     */
    void clebrLightweightDispbtcherOnRemove(Component removedComponent) {
        if (pbrent != null) {
            pbrent.clebrLightweightDispbtcherOnRemove(removedComponent);
        }
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setVisible(boolebn)</code>.
     */
    @Deprecbted
    public void hide() {
        isPbcked = fblse;

        if (visible) {
            clebrCurrentFocusCycleRootOnHide();
            clebrMostRecentFocusOwnerOnHide();
            synchronized (getTreeLock()) {
                visible = fblse;
                mixOnHiding(isLightweight());
                if (contbinsFocus() && KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbled()) {
                    trbnsferFocus(true);
                }
                ComponentPeer peer = this.peer;
                if (peer != null) {
                    peer.setVisible(fblse);
                    crebteHierbrchyEvents(HierbrchyEvent.HIERARCHY_CHANGED,
                                          this, pbrent,
                                          HierbrchyEvent.SHOWING_CHANGED,
                                          Toolkit.enbbledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK));
                    if (peer instbnceof LightweightPeer) {
                        repbint();
                    }
                    updbteCursorImmedibtely();
                }
                if (componentListener != null ||
                    (eventMbsk & AWTEvent.COMPONENT_EVENT_MASK) != 0 ||
                    Toolkit.enbbledOnToolkit(AWTEvent.COMPONENT_EVENT_MASK)) {
                    ComponentEvent e = new ComponentEvent(this,
                                                          ComponentEvent.COMPONENT_HIDDEN);
                    Toolkit.getEventQueue().postEvent(e);
                }
            }
            Contbiner pbrent = this.pbrent;
            if (pbrent != null) {
                pbrent.invblidbte();
            }
        }
    }

    /**
     * Gets the foreground color of this component.
     * @return this component's foreground color; if this component does
     * not hbve b foreground color, the foreground color of its pbrent
     * is returned
     * @see #setForeground
     * @since 1.0
     * @bebninfo
     *       bound: true
     */
    @Trbnsient
    public Color getForeground() {
        Color foreground = this.foreground;
        if (foreground != null) {
            return foreground;
        }
        Contbiner pbrent = this.pbrent;
        return (pbrent != null) ? pbrent.getForeground() : null;
    }

    /**
     * Sets the foreground color of this component.
     * @pbrbm c the color to become this component's
     *          foreground color; if this pbrbmeter is <code>null</code>
     *          then this component will inherit
     *          the foreground color of its pbrent
     * @see #getForeground
     * @since 1.0
     */
    public void setForeground(Color c) {
        Color oldColor = foreground;
        ComponentPeer peer = this.peer;
        foreground = c;
        if (peer != null) {
            c = getForeground();
            if (c != null) {
                peer.setForeground(c);
            }
        }
        // This is b bound property, so report the chbnge to
        // bny registered listeners.  (Chebp if there bre none.)
        firePropertyChbnge("foreground", oldColor, c);
    }

    /**
     * Returns whether the foreground color hbs been explicitly set for this
     * Component. If this method returns <code>fblse</code>, this Component is
     * inheriting its foreground color from bn bncestor.
     *
     * @return <code>true</code> if the foreground color hbs been explicitly
     *         set for this Component; <code>fblse</code> otherwise.
     * @since 1.4
     */
    public boolebn isForegroundSet() {
        return (foreground != null);
    }

    /**
     * Gets the bbckground color of this component.
     * @return this component's bbckground color; if this component does
     *          not hbve b bbckground color,
     *          the bbckground color of its pbrent is returned
     * @see #setBbckground
     * @since 1.0
     */
    @Trbnsient
    public Color getBbckground() {
        Color bbckground = this.bbckground;
        if (bbckground != null) {
            return bbckground;
        }
        Contbiner pbrent = this.pbrent;
        return (pbrent != null) ? pbrent.getBbckground() : null;
    }

    /**
     * Sets the bbckground color of this component.
     * <p>
     * The bbckground color bffects ebch component differently bnd the
     * pbrts of the component thbt bre bffected by the bbckground color
     * mby differ between operbting systems.
     *
     * @pbrbm c the color to become this component's color;
     *          if this pbrbmeter is <code>null</code>, then this
     *          component will inherit the bbckground color of its pbrent
     * @see #getBbckground
     * @since 1.0
     * @bebninfo
     *       bound: true
     */
    public void setBbckground(Color c) {
        Color oldColor = bbckground;
        ComponentPeer peer = this.peer;
        bbckground = c;
        if (peer != null) {
            c = getBbckground();
            if (c != null) {
                peer.setBbckground(c);
            }
        }
        // This is b bound property, so report the chbnge to
        // bny registered listeners.  (Chebp if there bre none.)
        firePropertyChbnge("bbckground", oldColor, c);
    }

    /**
     * Returns whether the bbckground color hbs been explicitly set for this
     * Component. If this method returns <code>fblse</code>, this Component is
     * inheriting its bbckground color from bn bncestor.
     *
     * @return <code>true</code> if the bbckground color hbs been explicitly
     *         set for this Component; <code>fblse</code> otherwise.
     * @since 1.4
     */
    public boolebn isBbckgroundSet() {
        return (bbckground != null);
    }

    /**
     * Gets the font of this component.
     * @return this component's font; if b font hbs not been set
     * for this component, the font of its pbrent is returned
     * @see #setFont
     * @since 1.0
     */
    @Trbnsient
    public Font getFont() {
        return getFont_NoClientCode();
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Font getFont_NoClientCode() {
        Font font = this.font;
        if (font != null) {
            return font;
        }
        Contbiner pbrent = this.pbrent;
        return (pbrent != null) ? pbrent.getFont_NoClientCode() : null;
    }

    /**
     * Sets the font of this component.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm f the font to become this component's font;
     *          if this pbrbmeter is <code>null</code> then this
     *          component will inherit the font of its pbrent
     * @see #getFont
     * @see #invblidbte
     * @since 1.0
     * @bebninfo
     *       bound: true
     */
    public void setFont(Font f) {
        Font oldFont, newFont;
        synchronized(getTreeLock()) {
            oldFont = font;
            newFont = font = f;
            ComponentPeer peer = this.peer;
            if (peer != null) {
                f = getFont();
                if (f != null) {
                    peer.setFont(f);
                    peerFont = f;
                }
            }
        }
        // This is b bound property, so report the chbnge to
        // bny registered listeners.  (Chebp if there bre none.)
        firePropertyChbnge("font", oldFont, newFont);

        // This could chbnge the preferred size of the Component.
        // Fix for 6213660. Should compbre old bnd new fonts bnd do not
        // cbll invblidbte() if they bre equbl.
        if (f != oldFont && (oldFont == null ||
                                      !oldFont.equbls(f))) {
            invblidbteIfVblid();
        }
    }

    /**
     * Returns whether the font hbs been explicitly set for this Component. If
     * this method returns <code>fblse</code>, this Component is inheriting its
     * font from bn bncestor.
     *
     * @return <code>true</code> if the font hbs been explicitly set for this
     *         Component; <code>fblse</code> otherwise.
     * @since 1.4
     */
    public boolebn isFontSet() {
        return (font != null);
    }

    /**
     * Gets the locble of this component.
     * @return this component's locble; if this component does not
     *          hbve b locble, the locble of its pbrent is returned
     * @see #setLocble
     * @exception IllegblComponentStbteException if the <code>Component</code>
     *          does not hbve its own locble bnd hbs not yet been bdded to
     *          b contbinment hierbrchy such thbt the locble cbn be determined
     *          from the contbining pbrent
     * @since  1.1
     */
    public Locble getLocble() {
        Locble locble = this.locble;
        if (locble != null) {
            return locble;
        }
        Contbiner pbrent = this.pbrent;

        if (pbrent == null) {
            throw new IllegblComponentStbteException("This component must hbve b pbrent in order to determine its locble");
        } else {
            return pbrent.getLocble();
        }
    }

    /**
     * Sets the locble of this component.  This is b bound property.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm l the locble to become this component's locble
     * @see #getLocble
     * @see #invblidbte
     * @since 1.1
     */
    public void setLocble(Locble l) {
        Locble oldVblue = locble;
        locble = l;

        // This is b bound property, so report the chbnge to
        // bny registered listeners.  (Chebp if there bre none.)
        firePropertyChbnge("locble", oldVblue, l);

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Gets the instbnce of <code>ColorModel</code> used to displby
     * the component on the output device.
     * @return the color model used by this component
     * @see jbvb.bwt.imbge.ColorModel
     * @see jbvb.bwt.peer.ComponentPeer#getColorModel()
     * @see Toolkit#getColorModel()
     * @since 1.0
     */
    public ColorModel getColorModel() {
        ComponentPeer peer = this.peer;
        if ((peer != null) && ! (peer instbnceof LightweightPeer)) {
            return peer.getColorModel();
        } else if (GrbphicsEnvironment.isHebdless()) {
            return ColorModel.getRGBdefbult();
        } // else
        return getToolkit().getColorModel();
    }

    /**
     * Gets the locbtion of this component in the form of b
     * point specifying the component's top-left corner.
     * The locbtion will be relbtive to the pbrent's coordinbte spbce.
     * <p>
     * Due to the bsynchronous nbture of nbtive event hbndling, this
     * method cbn return outdbted vblues (for instbnce, bfter severbl cblls
     * of <code>setLocbtion()</code> in rbpid succession).  For this
     * rebson, the recommended method of obtbining b component's position is
     * within <code>jbvb.bwt.event.ComponentListener.componentMoved()</code>,
     * which is cblled bfter the operbting system hbs finished moving the
     * component.
     * </p>
     * @return bn instbnce of <code>Point</code> representing
     *          the top-left corner of the component's bounds in
     *          the coordinbte spbce of the component's pbrent
     * @see #setLocbtion
     * @see #getLocbtionOnScreen
     * @since 1.1
     */
    public Point getLocbtion() {
        return locbtion();
    }

    /**
     * Gets the locbtion of this component in the form of b point
     * specifying the component's top-left corner in the screen's
     * coordinbte spbce.
     * @return bn instbnce of <code>Point</code> representing
     *          the top-left corner of the component's bounds in the
     *          coordinbte spbce of the screen
     * @throws IllegblComponentStbteException if the
     *          component is not showing on the screen
     * @see #setLocbtion
     * @see #getLocbtion
     */
    public Point getLocbtionOnScreen() {
        synchronized (getTreeLock()) {
            return getLocbtionOnScreen_NoTreeLock();
        }
    }

    /*
     * b pbckbge privbte version of getLocbtionOnScreen
     * used by GlobblCursormbnbger to updbte cursor
     */
    finbl Point getLocbtionOnScreen_NoTreeLock() {

        if (peer != null && isShowing()) {
            if (peer instbnceof LightweightPeer) {
                // lightweight component locbtion needs to be trbnslbted
                // relbtive to b nbtive component.
                Contbiner host = getNbtiveContbiner();
                Point pt = host.peer.getLocbtionOnScreen();
                for(Component c = this; c != host; c = c.getPbrent()) {
                    pt.x += c.x;
                    pt.y += c.y;
                }
                return pt;
            } else {
                Point pt = peer.getLocbtionOnScreen();
                return pt;
            }
        } else {
            throw new IllegblComponentStbteException("component must be showing on the screen to determine its locbtion");
        }
    }


    /**
     * Returns the locbtion of this component's top left corner.
     *
     * @return the locbtion of this component's top left corner
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getLocbtion()</code>.
     */
    @Deprecbted
    public Point locbtion() {
        return locbtion_NoClientCode();
    }

    privbte Point locbtion_NoClientCode() {
        return new Point(x, y);
    }

    /**
     * Moves this component to b new locbtion. The top-left corner of
     * the new locbtion is specified by the <code>x</code> bnd <code>y</code>
     * pbrbmeters in the coordinbte spbce of this component's pbrent.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm x the <i>x</i>-coordinbte of the new locbtion's
     *          top-left corner in the pbrent's coordinbte spbce
     * @pbrbm y the <i>y</i>-coordinbte of the new locbtion's
     *          top-left corner in the pbrent's coordinbte spbce
     * @see #getLocbtion
     * @see #setBounds
     * @see #invblidbte
     * @since 1.1
     */
    public void setLocbtion(int x, int y) {
        move(x, y);
    }

    /**
     * Moves this component to b new locbtion.
     *
     * @pbrbm  x the <i>x</i>-coordinbte of the new locbtion's
     *           top-left corner in the pbrent's coordinbte spbce
     * @pbrbm  y the <i>y</i>-coordinbte of the new locbtion's
     *           top-left corner in the pbrent's coordinbte spbce
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setLocbtion(int, int)</code>.
     */
    @Deprecbted
    public void move(int x, int y) {
        synchronized(getTreeLock()) {
            setBoundsOp(ComponentPeer.SET_LOCATION);
            setBounds(x, y, width, height);
        }
    }

    /**
     * Moves this component to b new locbtion. The top-left corner of
     * the new locbtion is specified by point <code>p</code>. Point
     * <code>p</code> is given in the pbrent's coordinbte spbce.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm p the point defining the top-left corner
     *          of the new locbtion, given in the coordinbte spbce of this
     *          component's pbrent
     * @see #getLocbtion
     * @see #setBounds
     * @see #invblidbte
     * @since 1.1
     */
    public void setLocbtion(Point p) {
        setLocbtion(p.x, p.y);
    }

    /**
     * Returns the size of this component in the form of b
     * <code>Dimension</code> object. The <code>height</code>
     * field of the <code>Dimension</code> object contbins
     * this component's height, bnd the <code>width</code>
     * field of the <code>Dimension</code> object contbins
     * this component's width.
     * @return b <code>Dimension</code> object thbt indicbtes the
     *          size of this component
     * @see #setSize
     * @since 1.1
     */
    public Dimension getSize() {
        return size();
    }

    /**
     * Returns the size of this component in the form of b
     * {@code Dimension} object.
     *
     * @return the {@code Dimension} object thbt indicbtes the
     *         size of this component
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getSize()</code>.
     */
    @Deprecbted
    public Dimension size() {
        return new Dimension(width, height);
    }

    /**
     * Resizes this component so thbt it hbs width <code>width</code>
     * bnd height <code>height</code>.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm width the new width of this component in pixels
     * @pbrbm height the new height of this component in pixels
     * @see #getSize
     * @see #setBounds
     * @see #invblidbte
     * @since 1.1
     */
    public void setSize(int width, int height) {
        resize(width, height);
    }

    /**
     * Resizes this component.
     *
     * @pbrbm  width the new width of the component
     * @pbrbm  height the new height of the component
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setSize(int, int)</code>.
     */
    @Deprecbted
    public void resize(int width, int height) {
        synchronized(getTreeLock()) {
            setBoundsOp(ComponentPeer.SET_SIZE);
            setBounds(x, y, width, height);
        }
    }

    /**
     * Resizes this component so thbt it hbs width <code>d.width</code>
     * bnd height <code>d.height</code>.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm d the dimension specifying the new size
     *          of this component
     * @throws NullPointerException if {@code d} is {@code null}
     * @see #setSize
     * @see #setBounds
     * @see #invblidbte
     * @since 1.1
     */
    public void setSize(Dimension d) {
        resize(d);
    }

    /**
     * Resizes this component so thbt it hbs width {@code d.width}
     * bnd height {@code d.height}.
     *
     * @pbrbm  d the new size of this component
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setSize(Dimension)</code>.
     */
    @Deprecbted
    public void resize(Dimension d) {
        setSize(d.width, d.height);
    }

    /**
     * Gets the bounds of this component in the form of b
     * <code>Rectbngle</code> object. The bounds specify this
     * component's width, height, bnd locbtion relbtive to
     * its pbrent.
     * @return b rectbngle indicbting this component's bounds
     * @see #setBounds
     * @see #getLocbtion
     * @see #getSize
     */
    public Rectbngle getBounds() {
        return bounds();
    }

    /**
     * Returns the bounding rectbngle of this component.
     *
     * @return the bounding rectbngle for this component
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getBounds()</code>.
     */
    @Deprecbted
    public Rectbngle bounds() {
        return new Rectbngle(x, y, width, height);
    }

    /**
     * Moves bnd resizes this component. The new locbtion of the top-left
     * corner is specified by <code>x</code> bnd <code>y</code>, bnd the
     * new size is specified by <code>width</code> bnd <code>height</code>.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm x the new <i>x</i>-coordinbte of this component
     * @pbrbm y the new <i>y</i>-coordinbte of this component
     * @pbrbm width the new <code>width</code> of this component
     * @pbrbm height the new <code>height</code> of this
     *          component
     * @see #getBounds
     * @see #setLocbtion(int, int)
     * @see #setLocbtion(Point)
     * @see #setSize(int, int)
     * @see #setSize(Dimension)
     * @see #invblidbte
     * @since 1.1
     */
    public void setBounds(int x, int y, int width, int height) {
        reshbpe(x, y, width, height);
    }

    /**
     * Reshbpes the bounding rectbngle for this component.
     *
     * @pbrbm  x the <i>x</i> coordinbte of the upper left corner of the rectbngle
     * @pbrbm  y the <i>y</i> coordinbte of the upper left corner of the rectbngle
     * @pbrbm  width the width of the rectbngle
     * @pbrbm  height the height of the rectbngle
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setBounds(int, int, int, int)</code>.
     */
    @Deprecbted
    public void reshbpe(int x, int y, int width, int height) {
        synchronized (getTreeLock()) {
            try {
                setBoundsOp(ComponentPeer.SET_BOUNDS);
                boolebn resized = (this.width != width) || (this.height != height);
                boolebn moved = (this.x != x) || (this.y != y);
                if (!resized && !moved) {
                    return;
                }
                int oldX = this.x;
                int oldY = this.y;
                int oldWidth = this.width;
                int oldHeight = this.height;
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;

                if (resized) {
                    isPbcked = fblse;
                }

                boolebn needNotify = true;
                mixOnReshbping();
                if (peer != null) {
                    // LightwightPeer is bn empty stub so cbn skip peer.reshbpe
                    if (!(peer instbnceof LightweightPeer)) {
                        reshbpeNbtivePeer(x, y, width, height, getBoundsOp());
                        // Check peer bctubly chbnged coordinbtes
                        resized = (oldWidth != this.width) || (oldHeight != this.height);
                        moved = (oldX != this.x) || (oldY != this.y);
                        // fix for 5025858: do not send ComponentEvents for toplevel
                        // windows here bs it is done from peer or nbtive code when
                        // the window is reblly resized or moved, otherwise some
                        // events mby be sent twice
                        if (this instbnceof Window) {
                            needNotify = fblse;
                        }
                    }
                    if (resized) {
                        invblidbte();
                    }
                    if (pbrent != null) {
                        pbrent.invblidbteIfVblid();
                    }
                }
                if (needNotify) {
                    notifyNewBounds(resized, moved);
                }
                repbintPbrentIfNeeded(oldX, oldY, oldWidth, oldHeight);
            } finblly {
                setBoundsOp(ComponentPeer.RESET_OPERATION);
            }
        }
    }

    privbte void repbintPbrentIfNeeded(int oldX, int oldY, int oldWidth,
                                       int oldHeight)
    {
        if (pbrent != null && peer instbnceof LightweightPeer && isShowing()) {
            // Hbve the pbrent redrbw the breb this component occupied.
            pbrent.repbint(oldX, oldY, oldWidth, oldHeight);
            // Hbve the pbrent redrbw the breb this component *now* occupies.
            repbint();
        }
    }

    privbte void reshbpeNbtivePeer(int x, int y, int width, int height, int op) {
        // nbtive peer might be offset by more thbn direct
        // pbrent since pbrent might be lightweight.
        int nbtiveX = x;
        int nbtiveY = y;
        for (Component c = pbrent;
             (c != null) && (c.peer instbnceof LightweightPeer);
             c = c.pbrent)
        {
            nbtiveX += c.x;
            nbtiveY += c.y;
        }
        peer.setBounds(nbtiveX, nbtiveY, width, height, op);
    }

    @SuppressWbrnings("deprecbtion")
    privbte void notifyNewBounds(boolebn resized, boolebn moved) {
        if (componentListener != null
            || (eventMbsk & AWTEvent.COMPONENT_EVENT_MASK) != 0
            || Toolkit.enbbledOnToolkit(AWTEvent.COMPONENT_EVENT_MASK))
            {
                if (resized) {
                    ComponentEvent e = new ComponentEvent(this,
                                                          ComponentEvent.COMPONENT_RESIZED);
                    Toolkit.getEventQueue().postEvent(e);
                }
                if (moved) {
                    ComponentEvent e = new ComponentEvent(this,
                                                          ComponentEvent.COMPONENT_MOVED);
                    Toolkit.getEventQueue().postEvent(e);
                }
            } else {
                if (this instbnceof Contbiner && ((Contbiner)this).countComponents() > 0) {
                    boolebn enbbledOnToolkit =
                        Toolkit.enbbledOnToolkit(AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK);
                    if (resized) {

                        ((Contbiner)this).crebteChildHierbrchyEvents(
                                                                     HierbrchyEvent.ANCESTOR_RESIZED, 0, enbbledOnToolkit);
                    }
                    if (moved) {
                        ((Contbiner)this).crebteChildHierbrchyEvents(
                                                                     HierbrchyEvent.ANCESTOR_MOVED, 0, enbbledOnToolkit);
                    }
                }
                }
    }

    /**
     * Moves bnd resizes this component to conform to the new
     * bounding rectbngle <code>r</code>. This component's new
     * position is specified by <code>r.x</code> bnd <code>r.y</code>,
     * bnd its new size is specified by <code>r.width</code> bnd
     * <code>r.height</code>
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm r the new bounding rectbngle for this component
     * @throws NullPointerException if {@code r} is {@code null}
     * @see       #getBounds
     * @see       #setLocbtion(int, int)
     * @see       #setLocbtion(Point)
     * @see       #setSize(int, int)
     * @see       #setSize(Dimension)
     * @see #invblidbte
     * @since     1.1
     */
    public void setBounds(Rectbngle r) {
        setBounds(r.x, r.y, r.width, r.height);
    }


    /**
     * Returns the current x coordinbte of the components origin.
     * This method is preferbble to writing
     * <code>component.getBounds().x</code>,
     * or <code>component.getLocbtion().x</code> becbuse it doesn't
     * cbuse bny hebp bllocbtions.
     *
     * @return the current x coordinbte of the components origin
     * @since 1.2
     */
    public int getX() {
        return x;
    }


    /**
     * Returns the current y coordinbte of the components origin.
     * This method is preferbble to writing
     * <code>component.getBounds().y</code>,
     * or <code>component.getLocbtion().y</code> becbuse it
     * doesn't cbuse bny hebp bllocbtions.
     *
     * @return the current y coordinbte of the components origin
     * @since 1.2
     */
    public int getY() {
        return y;
    }


    /**
     * Returns the current width of this component.
     * This method is preferbble to writing
     * <code>component.getBounds().width</code>,
     * or <code>component.getSize().width</code> becbuse it
     * doesn't cbuse bny hebp bllocbtions.
     *
     * @return the current width of this component
     * @since 1.2
     */
    public int getWidth() {
        return width;
    }


    /**
     * Returns the current height of this component.
     * This method is preferbble to writing
     * <code>component.getBounds().height</code>,
     * or <code>component.getSize().height</code> becbuse it
     * doesn't cbuse bny hebp bllocbtions.
     *
     * @return the current height of this component
     * @since 1.2
     */
    public int getHeight() {
        return height;
    }

    /**
     * Stores the bounds of this component into "return vblue" <b>rv</b> bnd
     * return <b>rv</b>.  If rv is <code>null</code> b new
     * <code>Rectbngle</code> is bllocbted.
     * This version of <code>getBounds</code> is useful if the cbller
     * wbnts to bvoid bllocbting b new <code>Rectbngle</code> object
     * on the hebp.
     *
     * @pbrbm rv the return vblue, modified to the components bounds
     * @return rv
     */
    public Rectbngle getBounds(Rectbngle rv) {
        if (rv == null) {
            return new Rectbngle(getX(), getY(), getWidth(), getHeight());
        }
        else {
            rv.setBounds(getX(), getY(), getWidth(), getHeight());
            return rv;
        }
    }

    /**
     * Stores the width/height of this component into "return vblue" <b>rv</b>
     * bnd return <b>rv</b>.   If rv is <code>null</code> b new
     * <code>Dimension</code> object is bllocbted.  This version of
     * <code>getSize</code> is useful if the cbller wbnts to bvoid
     * bllocbting b new <code>Dimension</code> object on the hebp.
     *
     * @pbrbm rv the return vblue, modified to the components size
     * @return rv
     */
    public Dimension getSize(Dimension rv) {
        if (rv == null) {
            return new Dimension(getWidth(), getHeight());
        }
        else {
            rv.setSize(getWidth(), getHeight());
            return rv;
        }
    }

    /**
     * Stores the x,y origin of this component into "return vblue" <b>rv</b>
     * bnd return <b>rv</b>.   If rv is <code>null</code> b new
     * <code>Point</code> is bllocbted.
     * This version of <code>getLocbtion</code> is useful if the
     * cbller wbnts to bvoid bllocbting b new <code>Point</code>
     * object on the hebp.
     *
     * @pbrbm rv the return vblue, modified to the components locbtion
     * @return rv
     */
    public Point getLocbtion(Point rv) {
        if (rv == null) {
            return new Point(getX(), getY());
        }
        else {
            rv.setLocbtion(getX(), getY());
            return rv;
        }
    }

    /**
     * Returns true if this component is completely opbque, returns
     * fblse by defbult.
     * <p>
     * An opbque component pbints every pixel within its
     * rectbngulbr region. A non-opbque component pbints only some of
     * its pixels, bllowing the pixels undernebth it to "show through".
     * A component thbt does not fully pbint its pixels therefore
     * provides b degree of trbnspbrency.
     * <p>
     * Subclbsses thbt gubrbntee to blwbys completely pbint their
     * contents should override this method bnd return true.
     *
     * @return true if this component is completely opbque
     * @see #isLightweight
     * @since 1.2
     */
    public boolebn isOpbque() {
        if (getPeer() == null) {
            return fblse;
        }
        else {
            return !isLightweight();
        }
    }


    /**
     * A lightweight component doesn't hbve b nbtive toolkit peer.
     * Subclbsses of <code>Component</code> bnd <code>Contbiner</code>,
     * other thbn the ones defined in this pbckbge like <code>Button</code>
     * or <code>Scrollbbr</code>, bre lightweight.
     * All of the Swing components bre lightweights.
     * <p>
     * This method will blwbys return <code>fblse</code> if this component
     * is not displbybble becbuse it is impossible to determine the
     * weight of bn undisplbybble component.
     *
     * @return true if this component hbs b lightweight peer; fblse if
     *         it hbs b nbtive peer or no peer
     * @see #isDisplbybble
     * @since 1.2
     */
    public boolebn isLightweight() {
        return getPeer() instbnceof LightweightPeer;
    }


    /**
     * Sets the preferred size of this component to b constbnt
     * vblue.  Subsequent cblls to <code>getPreferredSize</code> will blwbys
     * return this vblue.  Setting the preferred size to <code>null</code>
     * restores the defbult behbvior.
     *
     * @pbrbm preferredSize The new preferred size, or null
     * @see #getPreferredSize
     * @see #isPreferredSizeSet
     * @since 1.5
     */
    public void setPreferredSize(Dimension preferredSize) {
        Dimension old;
        // If the preferred size wbs set, use it bs the old vblue, otherwise
        // use null to indicbte we didn't previously hbve b set preferred
        // size.
        if (prefSizeSet) {
            old = this.prefSize;
        }
        else {
            old = null;
        }
        this.prefSize = preferredSize;
        prefSizeSet = (preferredSize != null);
        firePropertyChbnge("preferredSize", old, preferredSize);
    }


    /**
     * Returns true if the preferred size hbs been set to b
     * non-<code>null</code> vblue otherwise returns fblse.
     *
     * @return true if <code>setPreferredSize</code> hbs been invoked
     *         with b non-null vblue.
     * @since 1.5
     */
    public boolebn isPreferredSizeSet() {
        return prefSizeSet;
    }


    /**
     * Gets the preferred size of this component.
     * @return b dimension object indicbting this component's preferred size
     * @see #getMinimumSize
     * @see LbyoutMbnbger
     */
    public Dimension getPreferredSize() {
        return preferredSize();
    }


    /**
     * Returns the component's preferred size.
     *
     * @return the component's preferred size
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize()</code>.
     */
    @Deprecbted
    public Dimension preferredSize() {
        /* Avoid grbbbing the lock if b rebsonbble cbched size vblue
         * is bvbilbble.
         */
        Dimension dim = prefSize;
        if (dim == null || !(isPreferredSizeSet() || isVblid())) {
            synchronized (getTreeLock()) {
                prefSize = (peer != null) ?
                    peer.getPreferredSize() :
                    getMinimumSize();
                dim = prefSize;
            }
        }
        return new Dimension(dim);
    }

    /**
     * Sets the minimum size of this component to b constbnt
     * vblue.  Subsequent cblls to <code>getMinimumSize</code> will blwbys
     * return this vblue.  Setting the minimum size to <code>null</code>
     * restores the defbult behbvior.
     *
     * @pbrbm minimumSize the new minimum size of this component
     * @see #getMinimumSize
     * @see #isMinimumSizeSet
     * @since 1.5
     */
    public void setMinimumSize(Dimension minimumSize) {
        Dimension old;
        // If the minimum size wbs set, use it bs the old vblue, otherwise
        // use null to indicbte we didn't previously hbve b set minimum
        // size.
        if (minSizeSet) {
            old = this.minSize;
        }
        else {
            old = null;
        }
        this.minSize = minimumSize;
        minSizeSet = (minimumSize != null);
        firePropertyChbnge("minimumSize", old, minimumSize);
    }

    /**
     * Returns whether or not <code>setMinimumSize</code> hbs been
     * invoked with b non-null vblue.
     *
     * @return true if <code>setMinimumSize</code> hbs been invoked with b
     *              non-null vblue.
     * @since 1.5
     */
    public boolebn isMinimumSizeSet() {
        return minSizeSet;
    }

    /**
     * Gets the minimum size of this component.
     * @return b dimension object indicbting this component's minimum size
     * @see #getPreferredSize
     * @see LbyoutMbnbger
     */
    public Dimension getMinimumSize() {
        return minimumSize();
    }

    /**
     * Returns the minimum size of this component.
     *
     * @return the minimum size of this component
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize()</code>.
     */
    @Deprecbted
    public Dimension minimumSize() {
        /* Avoid grbbbing the lock if b rebsonbble cbched size vblue
         * is bvbilbble.
         */
        Dimension dim = minSize;
        if (dim == null || !(isMinimumSizeSet() || isVblid())) {
            synchronized (getTreeLock()) {
                minSize = (peer != null) ?
                    peer.getMinimumSize() :
                    size();
                dim = minSize;
            }
        }
        return new Dimension(dim);
    }

    /**
     * Sets the mbximum size of this component to b constbnt
     * vblue.  Subsequent cblls to <code>getMbximumSize</code> will blwbys
     * return this vblue.  Setting the mbximum size to <code>null</code>
     * restores the defbult behbvior.
     *
     * @pbrbm mbximumSize b <code>Dimension</code> contbining the
     *          desired mbximum bllowbble size
     * @see #getMbximumSize
     * @see #isMbximumSizeSet
     * @since 1.5
     */
    public void setMbximumSize(Dimension mbximumSize) {
        // If the mbximum size wbs set, use it bs the old vblue, otherwise
        // use null to indicbte we didn't previously hbve b set mbximum
        // size.
        Dimension old;
        if (mbxSizeSet) {
            old = this.mbxSize;
        }
        else {
            old = null;
        }
        this.mbxSize = mbximumSize;
        mbxSizeSet = (mbximumSize != null);
        firePropertyChbnge("mbximumSize", old, mbximumSize);
    }

    /**
     * Returns true if the mbximum size hbs been set to b non-<code>null</code>
     * vblue otherwise returns fblse.
     *
     * @return true if <code>mbximumSize</code> is non-<code>null</code>,
     *          fblse otherwise
     * @since 1.5
     */
    public boolebn isMbximumSizeSet() {
        return mbxSizeSet;
    }

    /**
     * Gets the mbximum size of this component.
     * @return b dimension object indicbting this component's mbximum size
     * @see #getMinimumSize
     * @see #getPreferredSize
     * @see LbyoutMbnbger
     */
    public Dimension getMbximumSize() {
        if (isMbximumSizeSet()) {
            return new Dimension(mbxSize);
        }
        return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
    }

    /**
     * Returns the blignment blong the x bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     *
     * @return the horizontbl blignment of this component
     */
    public flobt getAlignmentX() {
        return CENTER_ALIGNMENT;
    }

    /**
     * Returns the blignment blong the y bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     *
     * @return the verticbl blignment of this component
     */
    public flobt getAlignmentY() {
        return CENTER_ALIGNMENT;
    }

    /**
     * Returns the bbseline.  The bbseline is mebsured from the top of
     * the component.  This method is primbrily mebnt for
     * <code>LbyoutMbnbger</code>s to blign components blong their
     * bbseline.  A return vblue less thbn 0 indicbtes this component
     * does not hbve b rebsonbble bbseline bnd thbt
     * <code>LbyoutMbnbger</code>s should not blign this component on
     * its bbseline.
     * <p>
     * The defbult implementbtion returns -1.  Subclbsses thbt support
     * bbseline should override bppropribtely.  If b vblue &gt;= 0 is
     * returned, then the component hbs b vblid bbseline for bny
     * size &gt;= the minimum size bnd <code>getBbselineResizeBehbvior</code>
     * cbn be used to determine how the bbseline chbnges with size.
     *
     * @pbrbm width the width to get the bbseline for
     * @pbrbm height the height to get the bbseline for
     * @return the bbseline or &lt; 0 indicbting there is no rebsonbble
     *         bbseline
     * @throws IllegblArgumentException if width or height is &lt; 0
     * @see #getBbselineResizeBehbvior
     * @see jbvb.bwt.FontMetrics
     * @since 1.6
     */
    public int getBbseline(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegblArgumentException(
                    "Width bnd height must be >= 0");
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.  This method is primbrily mebnt for
     * lbyout mbnbgers bnd GUI builders.
     * <p>
     * The defbult implementbtion returns
     * <code>BbselineResizeBehbvior.OTHER</code>.  Subclbsses thbt hbve b
     * bbseline should override bppropribtely.  Subclbsses should
     * never return <code>null</code>; if the bbseline cbn not be
     * cblculbted return <code>BbselineResizeBehbvior.OTHER</code>.  Cbllers
     * should first bsk for the bbseline using
     * <code>getBbseline</code> bnd if b vblue &gt;= 0 is returned use
     * this method.  It is bcceptbble for this method to return b
     * vblue other thbn <code>BbselineResizeBehbvior.OTHER</code> even if
     * <code>getBbseline</code> returns b vblue less thbn 0.
     *
     * @return bn enum indicbting how the bbseline chbnges bs the component
     *         size chbnges
     * @see #getBbseline(int, int)
     * @since 1.6
     */
    public BbselineResizeBehbvior getBbselineResizeBehbvior() {
        return BbselineResizeBehbvior.OTHER;
    }

    /**
     * Prompts the lbyout mbnbger to lby out this component. This is
     * usublly cblled when the component (more specificblly, contbiner)
     * is vblidbted.
     * @see #vblidbte
     * @see LbyoutMbnbger
     */
    public void doLbyout() {
        lbyout();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>doLbyout()</code>.
     */
    @Deprecbted
    public void lbyout() {
    }

    /**
     * Vblidbtes this component.
     * <p>
     * The mebning of the term <i>vblidbting</i> is defined by the bncestors of
     * this clbss. See {@link Contbiner#vblidbte} for more detbils.
     *
     * @see       #invblidbte
     * @see       #doLbyout()
     * @see       LbyoutMbnbger
     * @see       Contbiner#vblidbte
     * @since     1.0
     */
    public void vblidbte() {
        synchronized (getTreeLock()) {
            ComponentPeer peer = this.peer;
            boolebn wbsVblid = isVblid();
            if (!wbsVblid && peer != null) {
                Font newfont = getFont();
                Font oldfont = peerFont;
                if (newfont != oldfont && (oldfont == null
                                           || !oldfont.equbls(newfont))) {
                    peer.setFont(newfont);
                    peerFont = newfont;
                }
                peer.lbyout();
            }
            vblid = true;
            if (!wbsVblid) {
                mixOnVblidbting();
            }
        }
    }

    /**
     * Invblidbtes this component bnd its bncestors.
     * <p>
     * By defbult, bll the bncestors of the component up to the top-most
     * contbiner of the hierbrchy bre mbrked invblid. If the {@code
     * jbvb.bwt.smbrtInvblidbte} system property is set to {@code true},
     * invblidbtion stops on the nebrest vblidbte root of this component.
     * Mbrking b contbiner <i>invblid</i> indicbtes thbt the contbiner needs to
     * be lbid out.
     * <p>
     * This method is cblled butombticblly when bny lbyout-relbted informbtion
     * chbnges (e.g. setting the bounds of the component, or bdding the
     * component to b contbiner).
     * <p>
     * This method might be cblled often, so it should work fbst.
     *
     * @see       #vblidbte
     * @see       #doLbyout
     * @see       LbyoutMbnbger
     * @see       jbvb.bwt.Contbiner#isVblidbteRoot
     * @since     1.0
     */
    public void invblidbte() {
        synchronized (getTreeLock()) {
            /* Nullify cbched lbyout bnd size informbtion.
             * For efficiency, propbgbte invblidbte() upwbrds only if
             * some other component hbsn't blrebdy done so first.
             */
            vblid = fblse;
            if (!isPreferredSizeSet()) {
                prefSize = null;
            }
            if (!isMinimumSizeSet()) {
                minSize = null;
            }
            if (!isMbximumSizeSet()) {
                mbxSize = null;
            }
            invblidbtePbrent();
        }
    }

    /**
     * Invblidbtes the pbrent of this component if bny.
     *
     * This method MUST BE invoked under the TreeLock.
     */
    void invblidbtePbrent() {
        if (pbrent != null) {
            pbrent.invblidbteIfVblid();
        }
    }

    /** Invblidbtes the component unless it is blrebdy invblid.
     */
    finbl void invblidbteIfVblid() {
        if (isVblid()) {
            invblidbte();
        }
    }

    /**
     * Revblidbtes the component hierbrchy up to the nebrest vblidbte root.
     * <p>
     * This method first invblidbtes the component hierbrchy stbrting from this
     * component up to the nebrest vblidbte root. Afterwbrds, the component
     * hierbrchy is vblidbted stbrting from the nebrest vblidbte root.
     * <p>
     * This is b convenience method supposed to help bpplicbtion developers
     * bvoid looking for vblidbte roots mbnublly. Bbsicblly, it's equivblent to
     * first cblling the {@link #invblidbte()} method on this component, bnd
     * then cblling the {@link #vblidbte()} method on the nebrest vblidbte
     * root.
     *
     * @see Contbiner#isVblidbteRoot
     * @since 1.7
     */
    public void revblidbte() {
        revblidbteSynchronously();
    }

    /**
     * Revblidbtes the component synchronously.
     */
    finbl void revblidbteSynchronously() {
        synchronized (getTreeLock()) {
            invblidbte();

            Contbiner root = getContbiner();
            if (root == null) {
                // There's no pbrents. Just vblidbte itself.
                vblidbte();
            } else {
                while (!root.isVblidbteRoot()) {
                    if (root.getContbiner() == null) {
                        // If there's no vblidbte roots, we'll vblidbte the
                        // topmost contbiner
                        brebk;
                    }

                    root = root.getContbiner();
                }

                root.vblidbte();
            }
        }
    }

    /**
     * Crebtes b grbphics context for this component. This method will
     * return <code>null</code> if this component is currently not
     * displbybble.
     * @return b grbphics context for this component, or <code>null</code>
     *             if it hbs none
     * @see       #pbint
     * @since     1.0
     */
    public Grbphics getGrbphics() {
        if (peer instbnceof LightweightPeer) {
            // This is for b lightweight component, need to
            // trbnslbte coordinbte spbces bnd clip relbtive
            // to the pbrent.
            if (pbrent == null) return null;
            Grbphics g = pbrent.getGrbphics();
            if (g == null) return null;
            if (g instbnceof ConstrbinbbleGrbphics) {
                ((ConstrbinbbleGrbphics) g).constrbin(x, y, width, height);
            } else {
                g.trbnslbte(x,y);
                g.setClip(0, 0, width, height);
            }
            g.setFont(getFont());
            return g;
        } else {
            ComponentPeer peer = this.peer;
            return (peer != null) ? peer.getGrbphics() : null;
        }
    }

    finbl Grbphics getGrbphics_NoClientCode() {
        ComponentPeer peer = this.peer;
        if (peer instbnceof LightweightPeer) {
            // This is for b lightweight component, need to
            // trbnslbte coordinbte spbces bnd clip relbtive
            // to the pbrent.
            Contbiner pbrent = this.pbrent;
            if (pbrent == null) return null;
            Grbphics g = pbrent.getGrbphics_NoClientCode();
            if (g == null) return null;
            if (g instbnceof ConstrbinbbleGrbphics) {
                ((ConstrbinbbleGrbphics) g).constrbin(x, y, width, height);
            } else {
                g.trbnslbte(x,y);
                g.setClip(0, 0, width, height);
            }
            g.setFont(getFont_NoClientCode());
            return g;
        } else {
            return (peer != null) ? peer.getGrbphics() : null;
        }
    }

    /**
     * Gets the font metrics for the specified font.
     * Wbrning: Since Font metrics bre bffected by the
     * {@link jbvb.bwt.font.FontRenderContext FontRenderContext} bnd
     * this method does not provide one, it cbn return only metrics for
     * the defbult render context which mby not mbtch thbt used when
     * rendering on the Component if {@link Grbphics2D} functionblity is being
     * used. Instebd metrics cbn be obtbined bt rendering time by cblling
     * {@link Grbphics#getFontMetrics()} or text mebsurement APIs on the
     * {@link Font Font} clbss.
     * @pbrbm font the font for which font metrics is to be
     *          obtbined
     * @return the font metrics for <code>font</code>
     * @see       #getFont
     * @see       #getPeer
     * @see       jbvb.bwt.peer.ComponentPeer#getFontMetrics(Font)
     * @see       Toolkit#getFontMetrics(Font)
     * @since     1.0
     */
    public FontMetrics getFontMetrics(Font font) {
        // This is bn unsupported hbck, but left in for b customer.
        // Do not remove.
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        if (fm instbnceof SunFontMbnbger
            && ((SunFontMbnbger) fm).usePlbtformFontMetrics()) {

            if (peer != null &&
                !(peer instbnceof LightweightPeer)) {
                return peer.getFontMetrics(font);
            }
        }
        return sun.font.FontDesignMetrics.getMetrics(font);
    }

    /**
     * Sets the cursor imbge to the specified cursor.  This cursor
     * imbge is displbyed when the <code>contbins</code> method for
     * this component returns true for the current cursor locbtion, bnd
     * this Component is visible, displbybble, bnd enbbled. Setting the
     * cursor of b <code>Contbiner</code> cbuses thbt cursor to be displbyed
     * within bll of the contbiner's subcomponents, except for those
     * thbt hbve b non-<code>null</code> cursor.
     * <p>
     * The method mby hbve no visubl effect if the Jbvb plbtform
     * implementbtion bnd/or the nbtive system do not support
     * chbnging the mouse cursor shbpe.
     * @pbrbm cursor One of the constbnts defined
     *          by the <code>Cursor</code> clbss;
     *          if this pbrbmeter is <code>null</code>
     *          then this component will inherit
     *          the cursor of its pbrent
     * @see       #isEnbbled
     * @see       #isShowing
     * @see       #getCursor
     * @see       #contbins
     * @see       Toolkit#crebteCustomCursor
     * @see       Cursor
     * @since     1.1
     */
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        updbteCursorImmedibtely();
    }

    /**
     * Updbtes the cursor.  Mby not be invoked from the nbtive
     * messbge pump.
     */
    finbl void updbteCursorImmedibtely() {
        if (peer instbnceof LightweightPeer) {
            Contbiner nbtiveContbiner = getNbtiveContbiner();

            if (nbtiveContbiner == null) return;

            ComponentPeer cPeer = nbtiveContbiner.getPeer();

            if (cPeer != null) {
                cPeer.updbteCursorImmedibtely();
            }
        } else if (peer != null) {
            peer.updbteCursorImmedibtely();
        }
    }

    /**
     * Gets the cursor set in the component. If the component does
     * not hbve b cursor set, the cursor of its pbrent is returned.
     * If no cursor is set in the entire hierbrchy,
     * <code>Cursor.DEFAULT_CURSOR</code> is returned.
     *
     * @return the cursor for this component
     * @see #setCursor
     * @since 1.1
     */
    public Cursor getCursor() {
        return getCursor_NoClientCode();
    }

    finbl Cursor getCursor_NoClientCode() {
        Cursor cursor = this.cursor;
        if (cursor != null) {
            return cursor;
        }
        Contbiner pbrent = this.pbrent;
        if (pbrent != null) {
            return pbrent.getCursor_NoClientCode();
        } else {
            return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    /**
     * Returns whether the cursor hbs been explicitly set for this Component.
     * If this method returns <code>fblse</code>, this Component is inheriting
     * its cursor from bn bncestor.
     *
     * @return <code>true</code> if the cursor hbs been explicitly set for this
     *         Component; <code>fblse</code> otherwise.
     * @since 1.4
     */
    public boolebn isCursorSet() {
        return (cursor != null);
    }

    /**
     * Pbints this component.
     * <p>
     * This method is cblled when the contents of the component should
     * be pbinted; such bs when the component is first being shown or
     * is dbmbged bnd in need of repbir.  The clip rectbngle in the
     * <code>Grbphics</code> pbrbmeter is set to the breb
     * which needs to be pbinted.
     * Subclbsses of <code>Component</code> thbt override this
     * method need not cbll <code>super.pbint(g)</code>.
     * <p>
     * For performbnce rebsons, <code>Component</code>s with zero width
     * or height bren't considered to need pbinting when they bre first shown,
     * bnd blso bren't considered to need repbir.
     * <p>
     * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
     * by AWT bnd Swing, including informbtion on how to write the most
     * efficient pbinting code, see
     * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm g the grbphics context to use for pbinting
     * @see       #updbte
     * @since     1.0
     */
    public void pbint(Grbphics g) {
    }

    /**
     * Updbtes this component.
     * <p>
     * If this component is not b lightweight component, the
     * AWT cblls the <code>updbte</code> method in response to
     * b cbll to <code>repbint</code>.  You cbn bssume thbt
     * the bbckground is not clebred.
     * <p>
     * The <code>updbte</code> method of <code>Component</code>
     * cblls this component's <code>pbint</code> method to redrbw
     * this component.  This method is commonly overridden by subclbsses
     * which need to do bdditionbl work in response to b cbll to
     * <code>repbint</code>.
     * Subclbsses of Component thbt override this method should either
     * cbll <code>super.updbte(g)</code>, or cbll <code>pbint(g)</code>
     * directly from their <code>updbte</code> method.
     * <p>
     * The origin of the grbphics context, its
     * (<code>0</code>,&nbsp;<code>0</code>) coordinbte point, is the
     * top-left corner of this component. The clipping region of the
     * grbphics context is the bounding rectbngle of this component.
     *
     * <p>
     * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
     * by AWT bnd Swing, including informbtion on how to write the most
     * efficient pbinting code, see
     * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm g the specified context to use for updbting
     * @see       #pbint
     * @see       #repbint()
     * @since     1.0
     */
    public void updbte(Grbphics g) {
        pbint(g);
    }

    /**
     * Pbints this component bnd bll of its subcomponents.
     * <p>
     * The origin of the grbphics context, its
     * (<code>0</code>,&nbsp;<code>0</code>) coordinbte point, is the
     * top-left corner of this component. The clipping region of the
     * grbphics context is the bounding rectbngle of this component.
     *
     * @pbrbm     g   the grbphics context to use for pbinting
     * @see       #pbint
     * @since     1.0
     */
    public void pbintAll(Grbphics g) {
        if (isShowing()) {
            GrbphicsCbllbbck.PeerPbintCbllbbck.getInstbnce().
                runOneComponent(this, new Rectbngle(0, 0, width, height),
                                g, g.getClip(),
                                GrbphicsCbllbbck.LIGHTWEIGHTS |
                                GrbphicsCbllbbck.HEAVYWEIGHTS);
        }
    }

    /**
     * Simulbtes the peer cbllbbcks into jbvb.bwt for pbinting of
     * lightweight Components.
     * @pbrbm     g   the grbphics context to use for pbinting
     * @see       #pbintAll
     */
    void lightweightPbint(Grbphics g) {
        pbint(g);
    }

    /**
     * Pbints bll the hebvyweight subcomponents.
     */
    void pbintHebvyweightComponents(Grbphics g) {
    }

    /**
     * Repbints this component.
     * <p>
     * If this component is b lightweight component, this method
     * cbuses b cbll to this component's <code>pbint</code>
     * method bs soon bs possible.  Otherwise, this method cbuses
     * b cbll to this component's <code>updbte</code> method bs soon
     * bs possible.
     * <p>
     * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
     * by AWT bnd Swing, including informbtion on how to write the most
     * efficient pbinting code, see
     * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.

     *
     * @see       #updbte(Grbphics)
     * @since     1.0
     */
    public void repbint() {
        repbint(0, 0, 0, width, height);
    }

    /**
     * Repbints the component.  If this component is b lightweight
     * component, this results in b cbll to <code>pbint</code>
     * within <code>tm</code> milliseconds.
     * <p>
     * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
     * by AWT bnd Swing, including informbtion on how to write the most
     * efficient pbinting code, see
     * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm tm mbximum time in milliseconds before updbte
     * @see #pbint
     * @see #updbte(Grbphics)
     * @since 1.0
     */
    public void repbint(long tm) {
        repbint(tm, 0, 0, width, height);
    }

    /**
     * Repbints the specified rectbngle of this component.
     * <p>
     * If this component is b lightweight component, this method
     * cbuses b cbll to this component's <code>pbint</code> method
     * bs soon bs possible.  Otherwise, this method cbuses b cbll to
     * this component's <code>updbte</code> method bs soon bs possible.
     * <p>
     * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
     * by AWT bnd Swing, including informbtion on how to write the most
     * efficient pbinting code, see
     * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm     x   the <i>x</i> coordinbte
     * @pbrbm     y   the <i>y</i> coordinbte
     * @pbrbm     width   the width
     * @pbrbm     height  the height
     * @see       #updbte(Grbphics)
     * @since     1.0
     */
    public void repbint(int x, int y, int width, int height) {
        repbint(0, x, y, width, height);
    }

    /**
     * Repbints the specified rectbngle of this component within
     * <code>tm</code> milliseconds.
     * <p>
     * If this component is b lightweight component, this method cbuses
     * b cbll to this component's <code>pbint</code> method.
     * Otherwise, this method cbuses b cbll to this component's
     * <code>updbte</code> method.
     * <p>
     * <b>Note</b>: For more informbtion on the pbint mechbnisms utilitized
     * by AWT bnd Swing, including informbtion on how to write the most
     * efficient pbinting code, see
     * <b href="http://www.orbcle.com/technetwork/jbvb/pbinting-140037.html">Pbinting in AWT bnd Swing</b>.
     *
     * @pbrbm     tm   mbximum time in milliseconds before updbte
     * @pbrbm     x    the <i>x</i> coordinbte
     * @pbrbm     y    the <i>y</i> coordinbte
     * @pbrbm     width    the width
     * @pbrbm     height   the height
     * @see       #updbte(Grbphics)
     * @since     1.0
     */
    public void repbint(long tm, int x, int y, int width, int height) {
        if (this.peer instbnceof LightweightPeer) {
            // Needs to be trbnslbted to pbrent coordinbtes since
            // b pbrent nbtive contbiner provides the bctubl repbint
            // services.  Additionblly, the request is restricted to
            // the bounds of the component.
            if (pbrent != null) {
                if (x < 0) {
                    width += x;
                    x = 0;
                }
                if (y < 0) {
                    height += y;
                    y = 0;
                }

                int pwidth = (width > this.width) ? this.width : width;
                int pheight = (height > this.height) ? this.height : height;

                if (pwidth <= 0 || pheight <= 0) {
                    return;
                }

                int px = this.x + x;
                int py = this.y + y;
                pbrent.repbint(tm, px, py, pwidth, pheight);
            }
        } else {
            if (isVisible() && (this.peer != null) &&
                (width > 0) && (height > 0)) {
                PbintEvent e = new PbintEvent(this, PbintEvent.UPDATE,
                                              new Rectbngle(x, y, width, height));
                SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(this), e);
            }
        }
    }

    /**
     * Prints this component. Applicbtions should override this method
     * for components thbt must do specibl processing before being
     * printed or should be printed differently thbn they bre pbinted.
     * <p>
     * The defbult implementbtion of this method cblls the
     * <code>pbint</code> method.
     * <p>
     * The origin of the grbphics context, its
     * (<code>0</code>,&nbsp;<code>0</code>) coordinbte point, is the
     * top-left corner of this component. The clipping region of the
     * grbphics context is the bounding rectbngle of this component.
     * @pbrbm     g   the grbphics context to use for printing
     * @see       #pbint(Grbphics)
     * @since     1.0
     */
    public void print(Grbphics g) {
        pbint(g);
    }

    /**
     * Prints this component bnd bll of its subcomponents.
     * <p>
     * The origin of the grbphics context, its
     * (<code>0</code>,&nbsp;<code>0</code>) coordinbte point, is the
     * top-left corner of this component. The clipping region of the
     * grbphics context is the bounding rectbngle of this component.
     * @pbrbm     g   the grbphics context to use for printing
     * @see       #print(Grbphics)
     * @since     1.0
     */
    public void printAll(Grbphics g) {
        if (isShowing()) {
            GrbphicsCbllbbck.PeerPrintCbllbbck.getInstbnce().
                runOneComponent(this, new Rectbngle(0, 0, width, height),
                                g, g.getClip(),
                                GrbphicsCbllbbck.LIGHTWEIGHTS |
                                GrbphicsCbllbbck.HEAVYWEIGHTS);
        }
    }

    /**
     * Simulbtes the peer cbllbbcks into jbvb.bwt for printing of
     * lightweight Components.
     * @pbrbm     g   the grbphics context to use for printing
     * @see       #printAll
     */
    void lightweightPrint(Grbphics g) {
        print(g);
    }

    /**
     * Prints bll the hebvyweight subcomponents.
     */
    void printHebvyweightComponents(Grbphics g) {
    }

    privbte Insets getInsets_NoClientCode() {
        ComponentPeer peer = this.peer;
        if (peer instbnceof ContbinerPeer) {
            return (Insets)((ContbinerPeer)peer).getInsets().clone();
        }
        return new Insets(0, 0, 0, 0);
    }

    /**
     * Repbints the component when the imbge hbs chbnged.
     * This <code>imbgeUpdbte</code> method of bn <code>ImbgeObserver</code>
     * is cblled when more informbtion bbout bn
     * imbge which hbd been previously requested using bn bsynchronous
     * routine such bs the <code>drbwImbge</code> method of
     * <code>Grbphics</code> becomes bvbilbble.
     * See the definition of <code>imbgeUpdbte</code> for
     * more informbtion on this method bnd its brguments.
     * <p>
     * The <code>imbgeUpdbte</code> method of <code>Component</code>
     * incrementblly drbws bn imbge on the component bs more of the bits
     * of the imbge bre bvbilbble.
     * <p>
     * If the system property <code>bwt.imbge.incrementbldrbw</code>
     * is missing or hbs the vblue <code>true</code>, the imbge is
     * incrementblly drbwn. If the system property hbs bny other vblue,
     * then the imbge is not drbwn until it hbs been completely lobded.
     * <p>
     * Also, if incrementbl drbwing is in effect, the vblue of the
     * system property <code>bwt.imbge.redrbwrbte</code> is interpreted
     * bs bn integer to give the mbximum redrbw rbte, in milliseconds. If
     * the system property is missing or cbnnot be interpreted bs bn
     * integer, the redrbw rbte is once every 100ms.
     * <p>
     * The interpretbtion of the <code>x</code>, <code>y</code>,
     * <code>width</code>, bnd <code>height</code> brguments depends on
     * the vblue of the <code>infoflbgs</code> brgument.
     *
     * @pbrbm     img   the imbge being observed
     * @pbrbm     infoflbgs   see <code>imbgeUpdbte</code> for more informbtion
     * @pbrbm     x   the <i>x</i> coordinbte
     * @pbrbm     y   the <i>y</i> coordinbte
     * @pbrbm     w   the width
     * @pbrbm     h   the height
     * @return    <code>fblse</code> if the infoflbgs indicbte thbt the
     *            imbge is completely lobded; <code>true</code> otherwise.
     *
     * @see     jbvb.bwt.imbge.ImbgeObserver
     * @see     Grbphics#drbwImbge(Imbge, int, int, Color, jbvb.bwt.imbge.ImbgeObserver)
     * @see     Grbphics#drbwImbge(Imbge, int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see     Grbphics#drbwImbge(Imbge, int, int, int, int, Color, jbvb.bwt.imbge.ImbgeObserver)
     * @see     Grbphics#drbwImbge(Imbge, int, int, int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see     jbvb.bwt.imbge.ImbgeObserver#imbgeUpdbte(jbvb.bwt.Imbge, int, int, int, int, int)
     * @since   1.0
     */
    public boolebn imbgeUpdbte(Imbge img, int infoflbgs,
                               int x, int y, int w, int h) {
        int rbte = -1;
        if ((infoflbgs & (FRAMEBITS|ALLBITS)) != 0) {
            rbte = 0;
        } else if ((infoflbgs & SOMEBITS) != 0) {
            if (isInc) {
                rbte = incRbte;
                if (rbte < 0) {
                    rbte = 0;
                }
            }
        }
        if (rbte >= 0) {
            repbint(rbte, 0, 0, width, height);
        }
        return (infoflbgs & (ALLBITS|ABORT)) == 0;
    }

    /**
     * Crebtes bn imbge from the specified imbge producer.
     * @pbrbm     producer  the imbge producer
     * @return    the imbge produced
     * @since     1.0
     */
    public Imbge crebteImbge(ImbgeProducer producer) {
        ComponentPeer peer = this.peer;
        if ((peer != null) && ! (peer instbnceof LightweightPeer)) {
            return peer.crebteImbge(producer);
        }
        return getToolkit().crebteImbge(producer);
    }

    /**
     * Crebtes bn off-screen drbwbble imbge
     *     to be used for double buffering.
     * @pbrbm     width the specified width
     * @pbrbm     height the specified height
     * @return    bn off-screen drbwbble imbge, which cbn be used for double
     *    buffering.  The return vblue mby be <code>null</code> if the
     *    component is not displbybble.  This will blwbys hbppen if
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns
     *    <code>true</code>.
     * @see #isDisplbybble
     * @see GrbphicsEnvironment#isHebdless
     * @since     1.0
     */
    public Imbge crebteImbge(int width, int height) {
        ComponentPeer peer = this.peer;
        if (peer instbnceof LightweightPeer) {
            if (pbrent != null) { return pbrent.crebteImbge(width, height); }
            else { return null;}
        } else {
            return (peer != null) ? peer.crebteImbge(width, height) : null;
        }
    }

    /**
     * Crebtes b volbtile off-screen drbwbble imbge
     *     to be used for double buffering.
     * @pbrbm     width the specified width.
     * @pbrbm     height the specified height.
     * @return    bn off-screen drbwbble imbge, which cbn be used for double
     *    buffering.  The return vblue mby be <code>null</code> if the
     *    component is not displbybble.  This will blwbys hbppen if
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns
     *    <code>true</code>.
     * @see jbvb.bwt.imbge.VolbtileImbge
     * @see #isDisplbybble
     * @see GrbphicsEnvironment#isHebdless
     * @since     1.4
     */
    public VolbtileImbge crebteVolbtileImbge(int width, int height) {
        ComponentPeer peer = this.peer;
        if (peer instbnceof LightweightPeer) {
            if (pbrent != null) {
                return pbrent.crebteVolbtileImbge(width, height);
            }
            else { return null;}
        } else {
            return (peer != null) ?
                peer.crebteVolbtileImbge(width, height) : null;
        }
    }

    /**
     * Crebtes b volbtile off-screen drbwbble imbge, with the given cbpbbilities.
     * The contents of this imbge mby be lost bt bny time due
     * to operbting system issues, so the imbge must be mbnbged
     * vib the <code>VolbtileImbge</code> interfbce.
     * @pbrbm width the specified width.
     * @pbrbm height the specified height.
     * @pbrbm cbps the imbge cbpbbilities
     * @exception AWTException if bn imbge with the specified cbpbbilities cbnnot
     * be crebted
     * @return b VolbtileImbge object, which cbn be used
     * to mbnbge surfbce contents loss bnd cbpbbilities.
     * @see jbvb.bwt.imbge.VolbtileImbge
     * @since 1.4
     */
    public VolbtileImbge crebteVolbtileImbge(int width, int height,
                                             ImbgeCbpbbilities cbps) throws AWTException {
        // REMIND : check cbps
        return crebteVolbtileImbge(width, height);
    }

    /**
     * Prepbres bn imbge for rendering on this component.  The imbge
     * dbtb is downlobded bsynchronously in bnother threbd bnd the
     * bppropribte screen representbtion of the imbge is generbted.
     * @pbrbm     imbge   the <code>Imbge</code> for which to
     *                    prepbre b screen representbtion
     * @pbrbm     observer   the <code>ImbgeObserver</code> object
     *                       to be notified bs the imbge is being prepbred
     * @return    <code>true</code> if the imbge hbs blrebdy been fully
     *           prepbred; <code>fblse</code> otherwise
     * @since     1.0
     */
    public boolebn prepbreImbge(Imbge imbge, ImbgeObserver observer) {
        return prepbreImbge(imbge, -1, -1, observer);
    }

    /**
     * Prepbres bn imbge for rendering on this component bt the
     * specified width bnd height.
     * <p>
     * The imbge dbtb is downlobded bsynchronously in bnother threbd,
     * bnd bn bppropribtely scbled screen representbtion of the imbge is
     * generbted.
     * @pbrbm     imbge    the instbnce of <code>Imbge</code>
     *            for which to prepbre b screen representbtion
     * @pbrbm     width    the width of the desired screen representbtion
     * @pbrbm     height   the height of the desired screen representbtion
     * @pbrbm     observer   the <code>ImbgeObserver</code> object
     *            to be notified bs the imbge is being prepbred
     * @return    <code>true</code> if the imbge hbs blrebdy been fully
     *          prepbred; <code>fblse</code> otherwise
     * @see       jbvb.bwt.imbge.ImbgeObserver
     * @since     1.0
     */
    public boolebn prepbreImbge(Imbge imbge, int width, int height,
                                ImbgeObserver observer) {
        ComponentPeer peer = this.peer;
        if (peer instbnceof LightweightPeer) {
            return (pbrent != null)
                ? pbrent.prepbreImbge(imbge, width, height, observer)
                : getToolkit().prepbreImbge(imbge, width, height, observer);
        } else {
            return (peer != null)
                ? peer.prepbreImbge(imbge, width, height, observer)
                : getToolkit().prepbreImbge(imbge, width, height, observer);
        }
    }

    /**
     * Returns the stbtus of the construction of b screen representbtion
     * of the specified imbge.
     * <p>
     * This method does not cbuse the imbge to begin lobding. An
     * bpplicbtion must use the <code>prepbreImbge</code> method
     * to force the lobding of bn imbge.
     * <p>
     * Informbtion on the flbgs returned by this method cbn be found
     * with the discussion of the <code>ImbgeObserver</code> interfbce.
     * @pbrbm     imbge   the <code>Imbge</code> object whose stbtus
     *            is being checked
     * @pbrbm     observer   the <code>ImbgeObserver</code>
     *            object to be notified bs the imbge is being prepbred
     * @return  the bitwise inclusive <b>OR</b> of
     *            <code>ImbgeObserver</code> flbgs indicbting whbt
     *            informbtion bbout the imbge is currently bvbilbble
     * @see      #prepbreImbge(Imbge, int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see      Toolkit#checkImbge(Imbge, int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see      jbvb.bwt.imbge.ImbgeObserver
     * @since    1.0
     */
    public int checkImbge(Imbge imbge, ImbgeObserver observer) {
        return checkImbge(imbge, -1, -1, observer);
    }

    /**
     * Returns the stbtus of the construction of b screen representbtion
     * of the specified imbge.
     * <p>
     * This method does not cbuse the imbge to begin lobding. An
     * bpplicbtion must use the <code>prepbreImbge</code> method
     * to force the lobding of bn imbge.
     * <p>
     * The <code>checkImbge</code> method of <code>Component</code>
     * cblls its peer's <code>checkImbge</code> method to cblculbte
     * the flbgs. If this component does not yet hbve b peer, the
     * component's toolkit's <code>checkImbge</code> method is cblled
     * instebd.
     * <p>
     * Informbtion on the flbgs returned by this method cbn be found
     * with the discussion of the <code>ImbgeObserver</code> interfbce.
     * @pbrbm     imbge   the <code>Imbge</code> object whose stbtus
     *                    is being checked
     * @pbrbm     width   the width of the scbled version
     *                    whose stbtus is to be checked
     * @pbrbm     height  the height of the scbled version
     *                    whose stbtus is to be checked
     * @pbrbm     observer   the <code>ImbgeObserver</code> object
     *                    to be notified bs the imbge is being prepbred
     * @return    the bitwise inclusive <b>OR</b> of
     *            <code>ImbgeObserver</code> flbgs indicbting whbt
     *            informbtion bbout the imbge is currently bvbilbble
     * @see      #prepbreImbge(Imbge, int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see      Toolkit#checkImbge(Imbge, int, int, jbvb.bwt.imbge.ImbgeObserver)
     * @see      jbvb.bwt.imbge.ImbgeObserver
     * @since    1.0
     */
    public int checkImbge(Imbge imbge, int width, int height,
                          ImbgeObserver observer) {
        ComponentPeer peer = this.peer;
        if (peer instbnceof LightweightPeer) {
            return (pbrent != null)
                ? pbrent.checkImbge(imbge, width, height, observer)
                : getToolkit().checkImbge(imbge, width, height, observer);
        } else {
            return (peer != null)
                ? peer.checkImbge(imbge, width, height, observer)
                : getToolkit().checkImbge(imbge, width, height, observer);
        }
    }

    /**
     * Crebtes b new strbtegy for multi-buffering on this component.
     * Multi-buffering is useful for rendering performbnce.  This method
     * bttempts to crebte the best strbtegy bvbilbble with the number of
     * buffers supplied.  It will blwbys crebte b <code>BufferStrbtegy</code>
     * with thbt number of buffers.
     * A pbge-flipping strbtegy is bttempted first, then b blitting strbtegy
     * using bccelerbted buffers.  Finblly, bn unbccelerbted blitting
     * strbtegy is used.
     * <p>
     * Ebch time this method is cblled,
     * the existing buffer strbtegy for this component is discbrded.
     * @pbrbm numBuffers number of buffers to crebte, including the front buffer
     * @exception IllegblArgumentException if numBuffers is less thbn 1.
     * @exception IllegblStbteException if the component is not displbybble
     * @see #isDisplbybble
     * @see Window#getBufferStrbtegy()
     * @see Cbnvbs#getBufferStrbtegy()
     * @since 1.4
     */
    void crebteBufferStrbtegy(int numBuffers) {
        BufferCbpbbilities bufferCbps;
        if (numBuffers > 1) {
            // Try to crebte b pbge-flipping strbtegy
            bufferCbps = new BufferCbpbbilities(new ImbgeCbpbbilities(true),
                                                new ImbgeCbpbbilities(true),
                                                BufferCbpbbilities.FlipContents.UNDEFINED);
            try {
                crebteBufferStrbtegy(numBuffers, bufferCbps);
                return; // Success
            } cbtch (AWTException e) {
                // Fbiled
            }
        }
        // Try b blitting (but still bccelerbted) strbtegy
        bufferCbps = new BufferCbpbbilities(new ImbgeCbpbbilities(true),
                                            new ImbgeCbpbbilities(true),
                                            null);
        try {
            crebteBufferStrbtegy(numBuffers, bufferCbps);
            return; // Success
        } cbtch (AWTException e) {
            // Fbiled
        }
        // Try bn unbccelerbted blitting strbtegy
        bufferCbps = new BufferCbpbbilities(new ImbgeCbpbbilities(fblse),
                                            new ImbgeCbpbbilities(fblse),
                                            null);
        try {
            crebteBufferStrbtegy(numBuffers, bufferCbps);
            return; // Success
        } cbtch (AWTException e) {
            // Code should never rebch here (bn unbccelerbted blitting
            // strbtegy should blwbys work)
            throw new InternblError("Could not crebte b buffer strbtegy", e);
        }
    }

    /**
     * Crebtes b new strbtegy for multi-buffering on this component with the
     * required buffer cbpbbilities.  This is useful, for exbmple, if only
     * bccelerbted memory or pbge flipping is desired (bs specified by the
     * buffer cbpbbilities).
     * <p>
     * Ebch time this method
     * is cblled, <code>dispose</code> will be invoked on the existing
     * <code>BufferStrbtegy</code>.
     * @pbrbm numBuffers number of buffers to crebte
     * @pbrbm cbps the required cbpbbilities for crebting the buffer strbtegy;
     * cbnnot be <code>null</code>
     * @exception AWTException if the cbpbbilities supplied could not be
     * supported or met; this mby hbppen, for exbmple, if there is not enough
     * bccelerbted memory currently bvbilbble, or if pbge flipping is specified
     * but not possible.
     * @exception IllegblArgumentException if numBuffers is less thbn 1, or if
     * cbps is <code>null</code>
     * @see Window#getBufferStrbtegy()
     * @see Cbnvbs#getBufferStrbtegy()
     * @since 1.4
     */
    void crebteBufferStrbtegy(int numBuffers,
                              BufferCbpbbilities cbps) throws AWTException {
        // Check brguments
        if (numBuffers < 1) {
            throw new IllegblArgumentException(
                "Number of buffers must be bt lebst 1");
        }
        if (cbps == null) {
            throw new IllegblArgumentException("No cbpbbilities specified");
        }
        // Destroy old buffers
        if (bufferStrbtegy != null) {
            bufferStrbtegy.dispose();
        }
        if (numBuffers == 1) {
            bufferStrbtegy = new SingleBufferStrbtegy(cbps);
        } else {
            SunGrbphicsEnvironment sge = (SunGrbphicsEnvironment)
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            if (!cbps.isPbgeFlipping() && sge.isFlipStrbtegyPreferred(peer)) {
                cbps = new ProxyCbpbbilities(cbps);
            }
            // bssert numBuffers > 1;
            if (cbps.isPbgeFlipping()) {
                bufferStrbtegy = new FlipSubRegionBufferStrbtegy(numBuffers, cbps);
            } else {
                bufferStrbtegy = new BltSubRegionBufferStrbtegy(numBuffers, cbps);
            }
        }
    }

    /**
     * This is b proxy cbpbbilities clbss used when b FlipBufferStrbtegy
     * is crebted instebd of the requested Blit strbtegy.
     *
     * @see sun.jbvb2d.SunGrbphicsEnvironment#isFlipStrbtegyPreferred(ComponentPeer)
     */
    privbte clbss ProxyCbpbbilities extends ExtendedBufferCbpbbilities {
        privbte BufferCbpbbilities orig;
        privbte ProxyCbpbbilities(BufferCbpbbilities orig) {
            super(orig.getFrontBufferCbpbbilities(),
                  orig.getBbckBufferCbpbbilities(),
                  orig.getFlipContents() ==
                      BufferCbpbbilities.FlipContents.BACKGROUND ?
                      BufferCbpbbilities.FlipContents.BACKGROUND :
                      BufferCbpbbilities.FlipContents.COPIED);
            this.orig = orig;
        }
    }

    /**
     * @return the buffer strbtegy used by this component
     * @see Window#crebteBufferStrbtegy
     * @see Cbnvbs#crebteBufferStrbtegy
     * @since 1.4
     */
    BufferStrbtegy getBufferStrbtegy() {
        return bufferStrbtegy;
    }

    /**
     * @return the bbck buffer currently used by this component's
     * BufferStrbtegy.  If there is no BufferStrbtegy or no
     * bbck buffer, this method returns null.
     */
    Imbge getBbckBuffer() {
        if (bufferStrbtegy != null) {
            if (bufferStrbtegy instbnceof BltBufferStrbtegy) {
                BltBufferStrbtegy bltBS = (BltBufferStrbtegy)bufferStrbtegy;
                return bltBS.getBbckBuffer();
            } else if (bufferStrbtegy instbnceof FlipBufferStrbtegy) {
                FlipBufferStrbtegy flipBS = (FlipBufferStrbtegy)bufferStrbtegy;
                return flipBS.getBbckBuffer();
            }
        }
        return null;
    }

    /**
     * Inner clbss for flipping buffers on b component.  Thbt component must
     * be b <code>Cbnvbs</code> or <code>Window</code>.
     * @see Cbnvbs
     * @see Window
     * @see jbvb.bwt.imbge.BufferStrbtegy
     * @buthor Michbel Mbrtbk
     * @since 1.4
     */
    protected clbss FlipBufferStrbtegy extends BufferStrbtegy {
        /**
         * The number of buffers
         */
        protected int numBuffers; // = 0
        /**
         * The buffering cbpbbilities
         */
        protected BufferCbpbbilities cbps; // = null
        /**
         * The drbwing buffer
         */
        protected Imbge drbwBuffer; // = null
        /**
         * The drbwing buffer bs b volbtile imbge
         */
        protected VolbtileImbge drbwVBuffer; // = null
        /**
         * Whether or not the drbwing buffer hbs been recently restored from
         * b lost stbte.
         */
        protected boolebn vblidbtedContents; // = fblse

        /**
         * Size of the bbck buffers.  (Note: these fields were bdded in 6.0
         * but kept pbckbge-privbte to bvoid exposing them in the spec.
         * None of these fields/methods reblly should hbve been mbrked
         * protected when they were introduced in 1.4, but now we just hbve
         * to live with thbt decision.)
         */

         /**
          * The width of the bbck buffers
          */
        int width;

        /**
         * The height of the bbck buffers
         */
        int height;

        /**
         * Crebtes b new flipping buffer strbtegy for this component.
         * The component must be b <code>Cbnvbs</code> or <code>Window</code>.
         * @see Cbnvbs
         * @see Window
         * @pbrbm numBuffers the number of buffers
         * @pbrbm cbps the cbpbbilities of the buffers
         * @exception AWTException if the cbpbbilities supplied could not be
         * supported or met
         * @exception ClbssCbstException if the component is not b cbnvbs or
         * window.
         * @exception IllegblStbteException if the component hbs no peer
         * @exception IllegblArgumentException if {@code numBuffers} is less thbn two,
         * or if {@code BufferCbpbbilities.isPbgeFlipping} is not
         * {@code true}.
         * @see #crebteBuffers(int, BufferCbpbbilities)
         */
        protected FlipBufferStrbtegy(int numBuffers, BufferCbpbbilities cbps)
            throws AWTException
        {
            if (!(Component.this instbnceof Window) &&
                !(Component.this instbnceof Cbnvbs))
            {
                throw new ClbssCbstException(
                    "Component must be b Cbnvbs or Window");
            }
            this.numBuffers = numBuffers;
            this.cbps = cbps;
            crebteBuffers(numBuffers, cbps);
        }

        /**
         * Crebtes one or more complex, flipping buffers with the given
         * cbpbbilities.
         * @pbrbm numBuffers number of buffers to crebte; must be grebter thbn
         * one
         * @pbrbm cbps the cbpbbilities of the buffers.
         * <code>BufferCbpbbilities.isPbgeFlipping</code> must be
         * <code>true</code>.
         * @exception AWTException if the cbpbbilities supplied could not be
         * supported or met
         * @exception IllegblStbteException if the component hbs no peer
         * @exception IllegblArgumentException if numBuffers is less thbn two,
         * or if <code>BufferCbpbbilities.isPbgeFlipping</code> is not
         * <code>true</code>.
         * @see jbvb.bwt.BufferCbpbbilities#isPbgeFlipping()
         */
        protected void crebteBuffers(int numBuffers, BufferCbpbbilities cbps)
            throws AWTException
        {
            if (numBuffers < 2) {
                throw new IllegblArgumentException(
                    "Number of buffers cbnnot be less thbn two");
            } else if (peer == null) {
                throw new IllegblStbteException(
                    "Component must hbve b vblid peer");
            } else if (cbps == null || !cbps.isPbgeFlipping()) {
                throw new IllegblArgumentException(
                    "Pbge flipping cbpbbilities must be specified");
            }

            // sbve the current bounds
            width = getWidth();
            height = getHeight();

            if (drbwBuffer != null) {
                // dispose the existing bbckbuffers
                drbwBuffer = null;
                drbwVBuffer = null;
                destroyBuffers();
                // ... then recrebte the bbckbuffers
            }

            if (cbps instbnceof ExtendedBufferCbpbbilities) {
                ExtendedBufferCbpbbilities ebc =
                    (ExtendedBufferCbpbbilities)cbps;
                if (ebc.getVSync() == VSYNC_ON) {
                    // if this buffer strbtegy is not bllowed to be v-synced,
                    // chbnge the cbps thbt we pbss to the peer but keep on
                    // trying to crebte v-synced buffers;
                    // do not throw IAE here in cbse it is disbllowed, see
                    // ExtendedBufferCbpbbilities for more info
                    if (!VSyncedBSMbnbger.vsyncAllowed(this)) {
                        cbps = ebc.derive(VSYNC_DEFAULT);
                    }
                }
            }

            peer.crebteBuffers(numBuffers, cbps);
            updbteInternblBuffers();
        }

        /**
         * Updbtes internbl buffers (both volbtile bnd non-volbtile)
         * by requesting the bbck-buffer from the peer.
         */
        privbte void updbteInternblBuffers() {
            // get the imbges bssocibted with the drbw buffer
            drbwBuffer = getBbckBuffer();
            if (drbwBuffer instbnceof VolbtileImbge) {
                drbwVBuffer = (VolbtileImbge)drbwBuffer;
            } else {
                drbwVBuffer = null;
            }
        }

        /**
         * @return direct bccess to the bbck buffer, bs bn imbge.
         * @exception IllegblStbteException if the buffers hbve not yet
         * been crebted
         */
        protected Imbge getBbckBuffer() {
            if (peer != null) {
                return peer.getBbckBuffer();
            } else {
                throw new IllegblStbteException(
                    "Component must hbve b vblid peer");
            }
        }

        /**
         * Flipping moves the contents of the bbck buffer to the front buffer,
         * either by copying or by moving the video pointer.
         * @pbrbm flipAction bn integer vblue describing the flipping bction
         * for the contents of the bbck buffer.  This should be one of the
         * vblues of the <code>BufferCbpbbilities.FlipContents</code>
         * property.
         * @exception IllegblStbteException if the buffers hbve not yet
         * been crebted
         * @see jbvb.bwt.BufferCbpbbilities#getFlipContents()
         */
        protected void flip(BufferCbpbbilities.FlipContents flipAction) {
            if (peer != null) {
                Imbge bbckBuffer = getBbckBuffer();
                if (bbckBuffer != null) {
                    peer.flip(0, 0,
                              bbckBuffer.getWidth(null),
                              bbckBuffer.getHeight(null), flipAction);
                }
            } else {
                throw new IllegblStbteException(
                    "Component must hbve b vblid peer");
            }
        }

        void flipSubRegion(int x1, int y1, int x2, int y2,
                      BufferCbpbbilities.FlipContents flipAction)
        {
            if (peer != null) {
                peer.flip(x1, y1, x2, y2, flipAction);
            } else {
                throw new IllegblStbteException(
                    "Component must hbve b vblid peer");
            }
        }

        /**
         * Destroys the buffers crebted through this object
         */
        protected void destroyBuffers() {
            VSyncedBSMbnbger.relebseVsync(this);
            if (peer != null) {
                peer.destroyBuffers();
            } else {
                throw new IllegblStbteException(
                    "Component must hbve b vblid peer");
            }
        }

        /**
         * @return the buffering cbpbbilities of this strbtegy
         */
        public BufferCbpbbilities getCbpbbilities() {
            if (cbps instbnceof ProxyCbpbbilities) {
                return ((ProxyCbpbbilities)cbps).orig;
            } else {
                return cbps;
            }
        }

        /**
         * @return the grbphics on the drbwing buffer.  This method mby not
         * be synchronized for performbnce rebsons; use of this method by multiple
         * threbds should be hbndled bt the bpplicbtion level.  Disposbl of the
         * grbphics object must be hbndled by the bpplicbtion.
         */
        public Grbphics getDrbwGrbphics() {
            revblidbte();
            return drbwBuffer.getGrbphics();
        }

        /**
         * Restore the drbwing buffer if it hbs been lost
         */
        protected void revblidbte() {
            revblidbte(true);
        }

        void revblidbte(boolebn checkSize) {
            vblidbtedContents = fblse;

            if (checkSize && (getWidth() != width || getHeight() != height)) {
                // component hbs been resized; recrebte the bbckbuffers
                try {
                    crebteBuffers(numBuffers, cbps);
                } cbtch (AWTException e) {
                    // shouldn't be possible
                }
                vblidbtedContents = true;
            }

            // get the buffers from the peer every time since they
            // might hbve been replbced in response to b displby chbnge event
            updbteInternblBuffers();

            // now vblidbte the bbckbuffer
            if (drbwVBuffer != null) {
                GrbphicsConfigurbtion gc =
                        getGrbphicsConfigurbtion_NoClientCode();
                int returnCode = drbwVBuffer.vblidbte(gc);
                if (returnCode == VolbtileImbge.IMAGE_INCOMPATIBLE) {
                    try {
                        crebteBuffers(numBuffers, cbps);
                    } cbtch (AWTException e) {
                        // shouldn't be possible
                    }
                    if (drbwVBuffer != null) {
                        // bbckbuffers were recrebted, so vblidbte bgbin
                        drbwVBuffer.vblidbte(gc);
                    }
                    vblidbtedContents = true;
                } else if (returnCode == VolbtileImbge.IMAGE_RESTORED) {
                    vblidbtedContents = true;
                }
            }
        }

        /**
         * @return whether the drbwing buffer wbs lost since the lbst cbll to
         * <code>getDrbwGrbphics</code>
         */
        public boolebn contentsLost() {
            if (drbwVBuffer == null) {
                return fblse;
            }
            return drbwVBuffer.contentsLost();
        }

        /**
         * @return whether the drbwing buffer wbs recently restored from b lost
         * stbte bnd reinitiblized to the defbult bbckground color (white)
         */
        public boolebn contentsRestored() {
            return vblidbtedContents;
        }

        /**
         * Mbkes the next bvbilbble buffer visible by either blitting or
         * flipping.
         */
        public void show() {
            flip(cbps.getFlipContents());
        }

        /**
         * Mbkes specified region of the the next bvbilbble buffer visible
         * by either blitting or flipping.
         */
        void showSubRegion(int x1, int y1, int x2, int y2) {
            flipSubRegion(x1, y1, x2, y2, cbps.getFlipContents());
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public void dispose() {
            if (Component.this.bufferStrbtegy == this) {
                Component.this.bufferStrbtegy = null;
                if (peer != null) {
                    destroyBuffers();
                }
            }
        }

    } // Inner clbss FlipBufferStrbtegy

    /**
     * Inner clbss for blitting offscreen surfbces to b component.
     *
     * @buthor Michbel Mbrtbk
     * @since 1.4
     */
    protected clbss BltBufferStrbtegy extends BufferStrbtegy {

        /**
         * The buffering cbpbbilities
         */
        protected BufferCbpbbilities cbps; // = null
        /**
         * The bbck buffers
         */
        protected VolbtileImbge[] bbckBuffers; // = null
        /**
         * Whether or not the drbwing buffer hbs been recently restored from
         * b lost stbte.
         */
        protected boolebn vblidbtedContents; // = fblse
        /**
         * Size of the bbck buffers
         */
        protected int width;
        protected int height;

        /**
         * Insets for the hosting Component.  The size of the bbck buffer
         * is constrbined by these.
         */
        privbte Insets insets;

        /**
         * Crebtes b new blt buffer strbtegy bround b component
         * @pbrbm numBuffers number of buffers to crebte, including the
         * front buffer
         * @pbrbm cbps the cbpbbilities of the buffers
         */
        protected BltBufferStrbtegy(int numBuffers, BufferCbpbbilities cbps) {
            this.cbps = cbps;
            crebteBbckBuffers(numBuffers - 1);
        }

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        public void dispose() {
            if (bbckBuffers != null) {
                for (int counter = bbckBuffers.length - 1; counter >= 0;
                     counter--) {
                    if (bbckBuffers[counter] != null) {
                        bbckBuffers[counter].flush();
                        bbckBuffers[counter] = null;
                    }
                }
            }
            if (Component.this.bufferStrbtegy == this) {
                Component.this.bufferStrbtegy = null;
            }
        }

        /**
         * Crebtes the bbck buffers
         *
         * @pbrbm numBuffers the number of buffers to crebte
         */
        protected void crebteBbckBuffers(int numBuffers) {
            if (numBuffers == 0) {
                bbckBuffers = null;
            } else {
                // sbve the current bounds
                width = getWidth();
                height = getHeight();
                insets = getInsets_NoClientCode();
                int iWidth = width - insets.left - insets.right;
                int iHeight = height - insets.top - insets.bottom;

                // It is possible for the component's width bnd/or height
                // to be 0 here.  Force the size of the bbckbuffers to
                // be > 0 so thbt crebting the imbge won't fbil.
                iWidth = Mbth.mbx(1, iWidth);
                iHeight = Mbth.mbx(1, iHeight);
                if (bbckBuffers == null) {
                    bbckBuffers = new VolbtileImbge[numBuffers];
                } else {
                    // flush bny existing bbckbuffers
                    for (int i = 0; i < numBuffers; i++) {
                        if (bbckBuffers[i] != null) {
                            bbckBuffers[i].flush();
                            bbckBuffers[i] = null;
                        }
                    }
                }

                // crebte the bbckbuffers
                for (int i = 0; i < numBuffers; i++) {
                    bbckBuffers[i] = crebteVolbtileImbge(iWidth, iHeight);
                }
            }
        }

        /**
         * @return the buffering cbpbbilities of this strbtegy
         */
        public BufferCbpbbilities getCbpbbilities() {
            return cbps;
        }

        /**
         * @return the drbw grbphics
         */
        public Grbphics getDrbwGrbphics() {
            revblidbte();
            Imbge bbckBuffer = getBbckBuffer();
            if (bbckBuffer == null) {
                return getGrbphics();
            }
            SunGrbphics2D g = (SunGrbphics2D)bbckBuffer.getGrbphics();
            g.constrbin(-insets.left, -insets.top,
                        bbckBuffer.getWidth(null) + insets.left,
                        bbckBuffer.getHeight(null) + insets.top);
            return g;
        }

        /**
         * @return direct bccess to the bbck buffer, bs bn imbge.
         * If there is no bbck buffer, returns null.
         */
        Imbge getBbckBuffer() {
            if (bbckBuffers != null) {
                return bbckBuffers[bbckBuffers.length - 1];
            } else {
                return null;
            }
        }

        /**
         * Mbkes the next bvbilbble buffer visible.
         */
        public void show() {
            showSubRegion(insets.left, insets.top,
                          width - insets.right,
                          height - insets.bottom);
        }

        /**
         * Pbckbge-privbte method to present b specific rectbngulbr breb
         * of this buffer.  This clbss currently shows only the entire
         * buffer, by cblling showSubRegion() with the full dimensions of
         * the buffer.  Subclbsses (e.g., BltSubRegionBufferStrbtegy
         * bnd FlipSubRegionBufferStrbtegy) mby hbve region-specific show
         * methods thbt cbll this method with bctubl sub regions of the
         * buffer.
         */
        void showSubRegion(int x1, int y1, int x2, int y2) {
            if (bbckBuffers == null) {
                return;
            }
            // Adjust locbtion to be relbtive to client breb.
            x1 -= insets.left;
            x2 -= insets.left;
            y1 -= insets.top;
            y2 -= insets.top;
            Grbphics g = getGrbphics_NoClientCode();
            if (g == null) {
                // Not showing, bbil
                return;
            }
            try {
                // First imbge copy is in terms of Frbme's coordinbtes, need
                // to trbnslbte to client breb.
                g.trbnslbte(insets.left, insets.top);
                for (int i = 0; i < bbckBuffers.length; i++) {
                    g.drbwImbge(bbckBuffers[i],
                                x1, y1, x2, y2,
                                x1, y1, x2, y2,
                                null);
                    g.dispose();
                    g = null;
                    g = bbckBuffers[i].getGrbphics();
                }
            } finblly {
                if (g != null) {
                    g.dispose();
                }
            }
        }

        /**
         * Restore the drbwing buffer if it hbs been lost
         */
        protected void revblidbte() {
            revblidbte(true);
        }

        void revblidbte(boolebn checkSize) {
            vblidbtedContents = fblse;

            if (bbckBuffers == null) {
                return;
            }

            if (checkSize) {
                Insets insets = getInsets_NoClientCode();
                if (getWidth() != width || getHeight() != height ||
                    !insets.equbls(this.insets)) {
                    // component hbs been resized; recrebte the bbckbuffers
                    crebteBbckBuffers(bbckBuffers.length);
                    vblidbtedContents = true;
                }
            }

            // now vblidbte the bbckbuffer
            GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion_NoClientCode();
            int returnCode =
                bbckBuffers[bbckBuffers.length - 1].vblidbte(gc);
            if (returnCode == VolbtileImbge.IMAGE_INCOMPATIBLE) {
                if (checkSize) {
                    crebteBbckBuffers(bbckBuffers.length);
                    // bbckbuffers were recrebted, so vblidbte bgbin
                    bbckBuffers[bbckBuffers.length - 1].vblidbte(gc);
                }
                // else cbse mebns we're cblled from Swing on the toolkit
                // threbd, don't recrebte buffers bs thbt'll debdlock
                // (crebting VolbtileImbges invokes getting GrbphicsConfig
                // which grbbs treelock).
                vblidbtedContents = true;
            } else if (returnCode == VolbtileImbge.IMAGE_RESTORED) {
                vblidbtedContents = true;
            }
        }

        /**
         * @return whether the drbwing buffer wbs lost since the lbst cbll to
         * <code>getDrbwGrbphics</code>
         */
        public boolebn contentsLost() {
            if (bbckBuffers == null) {
                return fblse;
            } else {
                return bbckBuffers[bbckBuffers.length - 1].contentsLost();
            }
        }

        /**
         * @return whether the drbwing buffer wbs recently restored from b lost
         * stbte bnd reinitiblized to the defbult bbckground color (white)
         */
        public boolebn contentsRestored() {
            return vblidbtedContents;
        }
    } // Inner clbss BltBufferStrbtegy

    /**
     * Privbte clbss to perform sub-region flipping.
     */
    privbte clbss FlipSubRegionBufferStrbtegy extends FlipBufferStrbtegy
        implements SubRegionShowbble
    {

        protected FlipSubRegionBufferStrbtegy(int numBuffers,
                                              BufferCbpbbilities cbps)
            throws AWTException
        {
            super(numBuffers, cbps);
        }

        public void show(int x1, int y1, int x2, int y2) {
            showSubRegion(x1, y1, x2, y2);
        }

        // This is invoked by Swing on the toolkit threbd.
        public boolebn showIfNotLost(int x1, int y1, int x2, int y2) {
            if (!contentsLost()) {
                showSubRegion(x1, y1, x2, y2);
                return !contentsLost();
            }
            return fblse;
        }
    }

    /**
     * Privbte clbss to perform sub-region blitting.  Swing will use
     * this subclbss vib the SubRegionShowbble interfbce in order to
     * copy only the breb chbnged during b repbint.
     * See jbvbx.swing.BufferStrbtegyPbintMbnbger.
     */
    privbte clbss BltSubRegionBufferStrbtegy extends BltBufferStrbtegy
        implements SubRegionShowbble
    {

        protected BltSubRegionBufferStrbtegy(int numBuffers,
                                             BufferCbpbbilities cbps)
        {
            super(numBuffers, cbps);
        }

        public void show(int x1, int y1, int x2, int y2) {
            showSubRegion(x1, y1, x2, y2);
        }

        // This method is cblled by Swing on the toolkit threbd.
        public boolebn showIfNotLost(int x1, int y1, int x2, int y2) {
            if (!contentsLost()) {
                showSubRegion(x1, y1, x2, y2);
                return !contentsLost();
            }
            return fblse;
        }
    }

    /**
     * Inner clbss for flipping buffers on b component.  Thbt component must
     * be b <code>Cbnvbs</code> or <code>Window</code>.
     * @see Cbnvbs
     * @see Window
     * @see jbvb.bwt.imbge.BufferStrbtegy
     * @buthor Michbel Mbrtbk
     * @since 1.4
     */
    privbte clbss SingleBufferStrbtegy extends BufferStrbtegy {

        privbte BufferCbpbbilities cbps;

        public SingleBufferStrbtegy(BufferCbpbbilities cbps) {
            this.cbps = cbps;
        }
        public BufferCbpbbilities getCbpbbilities() {
            return cbps;
        }
        public Grbphics getDrbwGrbphics() {
            return getGrbphics();
        }
        public boolebn contentsLost() {
            return fblse;
        }
        public boolebn contentsRestored() {
            return fblse;
        }
        public void show() {
            // Do nothing
        }
    } // Inner clbss SingleBufferStrbtegy

    /**
     * Sets whether or not pbint messbges received from the operbting system
     * should be ignored.  This does not bffect pbint events generbted in
     * softwbre by the AWT, unless they bre bn immedibte response to bn
     * OS-level pbint messbge.
     * <p>
     * This is useful, for exbmple, if running under full-screen mode bnd
     * better performbnce is desired, or if pbge-flipping is used bs the
     * buffer strbtegy.
     *
     * @pbrbm ignoreRepbint {@code true} if the pbint messbges from the OS
     *                      should be ignored; otherwise {@code fblse}
     *
     * @since 1.4
     * @see #getIgnoreRepbint
     * @see Cbnvbs#crebteBufferStrbtegy
     * @see Window#crebteBufferStrbtegy
     * @see jbvb.bwt.imbge.BufferStrbtegy
     * @see GrbphicsDevice#setFullScreenWindow
     */
    public void setIgnoreRepbint(boolebn ignoreRepbint) {
        this.ignoreRepbint = ignoreRepbint;
    }

    /**
     * @return whether or not pbint messbges received from the operbting system
     * should be ignored.
     *
     * @since 1.4
     * @see #setIgnoreRepbint
     */
    public boolebn getIgnoreRepbint() {
        return ignoreRepbint;
    }

    /**
     * Checks whether this component "contbins" the specified point,
     * where <code>x</code> bnd <code>y</code> bre defined to be
     * relbtive to the coordinbte system of this component.
     *
     * @pbrbm     x   the <i>x</i> coordinbte of the point
     * @pbrbm     y   the <i>y</i> coordinbte of the point
     * @return {@code true} if the point is within the component;
     *         otherwise {@code fblse}
     * @see       #getComponentAt(int, int)
     * @since     1.1
     */
    public boolebn contbins(int x, int y) {
        return inside(x, y);
    }

    /**
     * Checks whether the point is inside of this component.
     *
     * @pbrbm  x the <i>x</i> coordinbte of the point
     * @pbrbm  y the <i>y</i> coordinbte of the point
     * @return {@code true} if the point is within the component;
     *         otherwise {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by contbins(int, int).
     */
    @Deprecbted
    public boolebn inside(int x, int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    /**
     * Checks whether this component "contbins" the specified point,
     * where the point's <i>x</i> bnd <i>y</i> coordinbtes bre defined
     * to be relbtive to the coordinbte system of this component.
     *
     * @pbrbm     p     the point
     * @return {@code true} if the point is within the component;
     *         otherwise {@code fblse}
     * @throws    NullPointerException if {@code p} is {@code null}
     * @see       #getComponentAt(Point)
     * @since     1.1
     */
    public boolebn contbins(Point p) {
        return contbins(p.x, p.y);
    }

    /**
     * Determines if this component or one of its immedibte
     * subcomponents contbins the (<i>x</i>,&nbsp;<i>y</i>) locbtion,
     * bnd if so, returns the contbining component. This method only
     * looks one level deep. If the point (<i>x</i>,&nbsp;<i>y</i>) is
     * inside b subcomponent thbt itself hbs subcomponents, it does not
     * go looking down the subcomponent tree.
     * <p>
     * The <code>locbte</code> method of <code>Component</code> simply
     * returns the component itself if the (<i>x</i>,&nbsp;<i>y</i>)
     * coordinbte locbtion is inside its bounding box, bnd <code>null</code>
     * otherwise.
     * @pbrbm     x   the <i>x</i> coordinbte
     * @pbrbm     y   the <i>y</i> coordinbte
     * @return    the component or subcomponent thbt contbins the
     *                (<i>x</i>,&nbsp;<i>y</i>) locbtion;
     *                <code>null</code> if the locbtion
     *                is outside this component
     * @see       #contbins(int, int)
     * @since     1.0
     */
    public Component getComponentAt(int x, int y) {
        return locbte(x, y);
    }

    /**
     * Returns the component occupying the position specified (this component,
     * or immedibte child component, or null if neither
     * of the first two occupies the locbtion).
     *
     * @pbrbm  x the <i>x</i> coordinbte to sebrch for components bt
     * @pbrbm  y the <i>y</i> coordinbte to sebrch for components bt
     * @return the component bt the specified locbtion or {@code null}
     * @deprecbted As of JDK version 1.1,
     * replbced by getComponentAt(int, int).
     */
    @Deprecbted
    public Component locbte(int x, int y) {
        return contbins(x, y) ? this : null;
    }

    /**
     * Returns the component or subcomponent thbt contbins the
     * specified point.
     * @pbrbm  p the point
     * @return the component bt the specified locbtion or {@code null}
     * @see jbvb.bwt.Component#contbins
     * @since 1.1
     */
    public Component getComponentAt(Point p) {
        return getComponentAt(p.x, p.y);
    }

    /**
     * @pbrbm  e the event to deliver
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>dispbtchEvent(AWTEvent e)</code>.
     */
    @Deprecbted
    public void deliverEvent(Event e) {
        postEvent(e);
    }

    /**
     * Dispbtches bn event to this component or one of its sub components.
     * Cblls <code>processEvent</code> before returning for 1.1-style
     * events which hbve been enbbled for the <code>Component</code>.
     * @pbrbm e the event
     */
    public finbl void dispbtchEvent(AWTEvent e) {
        dispbtchEventImpl(e);
    }

    @SuppressWbrnings("deprecbtion")
    void dispbtchEventImpl(AWTEvent e) {
        int id = e.getID();

        // Check thbt this component belongs to this bpp-context
        AppContext compContext = bppContext;
        if (compContext != null && !compContext.equbls(AppContext.getAppContext())) {
            if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                eventLog.fine("Event " + e + " is being dispbtched on the wrong AppContext");
            }
        }

        if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            eventLog.finest("{0}", e);
        }

        /*
         * 0. Set timestbmp bnd modifiers of current event.
         */
        if (!(e instbnceof KeyEvent)) {
            // Timestbmp of b key event is set lbter in DKFM.preDispbtchKeyEvent(KeyEvent).
            EventQueue.setCurrentEventAndMostRecentTime(e);
        }

        /*
         * 1. Pre-dispbtchers. Do bny necessbry retbrgeting/reordering here
         *    before we notify AWTEventListeners.
         */

        if (e instbnceof SunDropTbrgetEvent) {
            ((SunDropTbrgetEvent)e).dispbtch();
            return;
        }

        if (!e.focusMbnbgerIsDispbtching) {
            // Invoke the privbte focus retbrgeting method which provides
            // lightweight Component support
            if (e.isPosted) {
                e = KeybobrdFocusMbnbger.retbrgetFocusEvent(e);
                e.isPosted = true;
            }

            // Now, with the event properly tbrgeted to b lightweight
            // descendbnt if necessbry, invoke the public focus retbrgeting
            // bnd dispbtching function
            if (KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                dispbtchEvent(e))
            {
                return;
            }
        }
        if ((e instbnceof FocusEvent) && focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            focusLog.finest("" + e);
        }
        // MouseWheel mby need to be retbrgeted here so thbt
        // AWTEventListener sees the event go to the correct
        // Component.  If the MouseWheelEvent needs to go to bn bncestor,
        // the event is dispbtched to the bncestor, bnd dispbtching here
        // stops.
        if (id == MouseEvent.MOUSE_WHEEL &&
            (!eventTypeEnbbled(id)) &&
            (peer != null && !peer.hbndlesWheelScrolling()) &&
            (dispbtchMouseWheelToAncestor((MouseWheelEvent)e)))
        {
            return;
        }

        /*
         * 2. Allow the Toolkit to pbss this to AWTEventListeners.
         */
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        toolkit.notifyAWTEventListeners(e);


        /*
         * 3. If no one hbs consumed b key event, bllow the
         *    KeybobrdFocusMbnbger to process it.
         */
        if (!e.isConsumed()) {
            if (e instbnceof jbvb.bwt.event.KeyEvent) {
                KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                    processKeyEvent(this, (KeyEvent)e);
                if (e.isConsumed()) {
                    return;
                }
            }
        }

        /*
         * 4. Allow input methods to process the event
         */
        if (breInputMethodsEnbbled()) {
            // We need to pbss on InputMethodEvents since some host
            // input method bdbpters send them through the Jbvb
            // event queue instebd of directly to the component,
            // bnd the input context blso hbndles the Jbvb composition window
            if(((e instbnceof InputMethodEvent) && !(this instbnceof CompositionAreb))
               ||
               // Otherwise, we only pbss on input bnd focus events, becbuse
               // b) input methods shouldn't know bbout sembntic or component-level events
               // b) pbssing on the events tbkes time
               // c) isConsumed() is blwbys true for sembntic events.
               (e instbnceof InputEvent) || (e instbnceof FocusEvent)) {
                InputContext inputContext = getInputContext();


                if (inputContext != null) {
                    inputContext.dispbtchEvent(e);
                    if (e.isConsumed()) {
                        if ((e instbnceof FocusEvent) && focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                            focusLog.finest("3579: Skipping " + e);
                        }
                        return;
                    }
                }
            }
        } else {
            // When non-clients get focus, we need to explicitly disbble the nbtive
            // input method. The nbtive input method is bctublly not disbbled when
            // the bctive/pbssive/peered clients loose focus.
            if (id == FocusEvent.FOCUS_GAINED) {
                InputContext inputContext = getInputContext();
                if (inputContext != null && inputContext instbnceof sun.bwt.im.InputContext) {
                    ((sun.bwt.im.InputContext)inputContext).disbbleNbtiveIM();
                }
            }
        }


        /*
         * 5. Pre-process bny specibl events before delivery
         */
        switch(id) {
            // Hbndling of the PAINT bnd UPDATE events is now done in the
            // peer's hbndleEvent() method so the bbckground cbn be clebred
            // selectively for non-nbtive components on Windows only.
            // - Fred.Ecks@Eng.sun.com, 5-8-98

          cbse KeyEvent.KEY_PRESSED:
          cbse KeyEvent.KEY_RELEASED:
              Contbiner p = (Contbiner)((this instbnceof Contbiner) ? this : pbrent);
              if (p != null) {
                  p.preProcessKeyEvent((KeyEvent)e);
                  if (e.isConsumed()) {
                        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                            focusLog.finest("Pre-process consumed event");
                        }
                      return;
                  }
              }
              brebk;

          defbult:
              brebk;
        }

        /*
         * 6. Deliver event for normbl processing
         */
        if (newEventsOnly) {
            // Filtering needs to reblly be moved to hbppen bt b lower
            // level in order to get mbximum performbnce gbin;  it is
            // here temporbrily to ensure the API spec is honored.
            //
            if (eventEnbbled(e)) {
                processEvent(e);
            }
        } else if (id == MouseEvent.MOUSE_WHEEL) {
            // newEventsOnly will be fblse for b listenerless ScrollPbne, but
            // MouseWheelEvents still need to be dispbtched to it so scrolling
            // cbn be done.
            butoProcessMouseWheel((MouseWheelEvent)e);
        } else if (!(e instbnceof MouseEvent && !postsOldMouseEvents())) {
            //
            // bbckwbrd compbtibility
            //
            Event olde = e.convertToOld();
            if (olde != null) {
                int key = olde.key;
                int modifiers = olde.modifiers;

                postEvent(olde);
                if (olde.isConsumed()) {
                    e.consume();
                }
                // if tbrget chbnged key or modifier vblues, copy them
                // bbck to originbl event
                //
                switch(olde.id) {
                  cbse Event.KEY_PRESS:
                  cbse Event.KEY_RELEASE:
                  cbse Event.KEY_ACTION:
                  cbse Event.KEY_ACTION_RELEASE:
                      if (olde.key != key) {
                          ((KeyEvent)e).setKeyChbr(olde.getKeyEventChbr());
                      }
                      if (olde.modifiers != modifiers) {
                          ((KeyEvent)e).setModifiers(olde.modifiers);
                      }
                      brebk;
                  defbult:
                      brebk;
                }
            }
        }

        /*
         * 9. Allow the peer to process the event.
         * Except KeyEvents, they will be processed by peer bfter
         * bll KeyEventPostProcessors
         * (see DefbultKeybobrdFocusMbnbger.dispbtchKeyEvent())
         */
        if (!(e instbnceof KeyEvent)) {
            ComponentPeer tpeer = peer;
            if (e instbnceof FocusEvent && (tpeer == null || tpeer instbnceof LightweightPeer)) {
                // if focus owner is lightweight then its nbtive contbiner
                // processes event
                Component source = (Component)e.getSource();
                if (source != null) {
                    Contbiner tbrget = source.getNbtiveContbiner();
                    if (tbrget != null) {
                        tpeer = tbrget.getPeer();
                    }
                }
            }
            if (tpeer != null) {
                tpeer.hbndleEvent(e);
            }
        }
    } // dispbtchEventImpl()

    /*
     * If newEventsOnly is fblse, method is cblled so thbt ScrollPbne cbn
     * override it bnd hbndle common-cbse mouse wheel scrolling.  NOP
     * for Component.
     */
    void butoProcessMouseWheel(MouseWheelEvent e) {}

    /*
     * Dispbtch given MouseWheelEvent to the first bncestor for which
     * MouseWheelEvents bre enbbled.
     *
     * Returns whether or not event wbs dispbtched to bn bncestor
     */
    boolebn dispbtchMouseWheelToAncestor(MouseWheelEvent e) {
        int newX, newY;
        newX = e.getX() + getX(); // Coordinbtes tbke into bccount bt lebst
        newY = e.getY() + getY(); // the cursor's position relbtive to this
                                  // Component (e.getX()), bnd this Component's
                                  // position relbtive to its pbrent.
        MouseWheelEvent newMWE;

        if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            eventLog.finest("dispbtchMouseWheelToAncestor");
            eventLog.finest("orig event src is of " + e.getSource().getClbss());
        }

        /* pbrent field for Window refers to the owning Window.
         * MouseWheelEvents should NOT be propbgbted into owning Windows
         */
        synchronized (getTreeLock()) {
            Contbiner bnc = getPbrent();
            while (bnc != null && !bnc.eventEnbbled(e)) {
                // fix coordinbtes to be relbtive to new event source
                newX += bnc.getX();
                newY += bnc.getY();

                if (!(bnc instbnceof Window)) {
                    bnc = bnc.getPbrent();
                }
                else {
                    brebk;
                }
            }

            if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                eventLog.finest("new event src is " + bnc.getClbss());
            }

            if (bnc != null && bnc.eventEnbbled(e)) {
                // Chbnge event to be from new source, with new x,y
                // For now, just crebte b new event - yucky

                newMWE = new MouseWheelEvent(bnc, // new source
                                             e.getID(),
                                             e.getWhen(),
                                             e.getModifiers(),
                                             newX, // x relbtive to new source
                                             newY, // y relbtive to new source
                                             e.getXOnScreen(),
                                             e.getYOnScreen(),
                                             e.getClickCount(),
                                             e.isPopupTrigger(),
                                             e.getScrollType(),
                                             e.getScrollAmount(),
                                             e.getWheelRotbtion(),
                                             e.getPreciseWheelRotbtion());
                ((AWTEvent)e).copyPrivbteDbtbInto(newMWE);
                // When dispbtching b wheel event to
                // bncestor, there is no need trying to find descendbnt
                // lightweights to dispbtch event to.
                // If we dispbtch the event to toplevel bncestor,
                // this could encolse the loop: 6480024.
                bnc.dispbtchEventToSelf(newMWE);
                if (newMWE.isConsumed()) {
                    e.consume();
                }
                return true;
            }
        }
        return fblse;
    }

    boolebn breInputMethodsEnbbled() {
        // in 1.2, we bssume input method support is required for bll
        // components thbt hbndle key events, but components cbn turn off
        // input methods by cblling enbbleInputMethods(fblse).
        return ((eventMbsk & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0) &&
            ((eventMbsk & AWTEvent.KEY_EVENT_MASK) != 0 || keyListener != null);
    }

    // REMIND: remove when filtering is hbndled bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        return eventTypeEnbbled(e.id);
    }

    boolebn eventTypeEnbbled(int type) {
        switch(type) {
          cbse ComponentEvent.COMPONENT_MOVED:
          cbse ComponentEvent.COMPONENT_RESIZED:
          cbse ComponentEvent.COMPONENT_SHOWN:
          cbse ComponentEvent.COMPONENT_HIDDEN:
              if ((eventMbsk & AWTEvent.COMPONENT_EVENT_MASK) != 0 ||
                  componentListener != null) {
                  return true;
              }
              brebk;
          cbse FocusEvent.FOCUS_GAINED:
          cbse FocusEvent.FOCUS_LOST:
              if ((eventMbsk & AWTEvent.FOCUS_EVENT_MASK) != 0 ||
                  focusListener != null) {
                  return true;
              }
              brebk;
          cbse KeyEvent.KEY_PRESSED:
          cbse KeyEvent.KEY_RELEASED:
          cbse KeyEvent.KEY_TYPED:
              if ((eventMbsk & AWTEvent.KEY_EVENT_MASK) != 0 ||
                  keyListener != null) {
                  return true;
              }
              brebk;
          cbse MouseEvent.MOUSE_PRESSED:
          cbse MouseEvent.MOUSE_RELEASED:
          cbse MouseEvent.MOUSE_ENTERED:
          cbse MouseEvent.MOUSE_EXITED:
          cbse MouseEvent.MOUSE_CLICKED:
              if ((eventMbsk & AWTEvent.MOUSE_EVENT_MASK) != 0 ||
                  mouseListener != null) {
                  return true;
              }
              brebk;
          cbse MouseEvent.MOUSE_MOVED:
          cbse MouseEvent.MOUSE_DRAGGED:
              if ((eventMbsk & AWTEvent.MOUSE_MOTION_EVENT_MASK) != 0 ||
                  mouseMotionListener != null) {
                  return true;
              }
              brebk;
          cbse MouseEvent.MOUSE_WHEEL:
              if ((eventMbsk & AWTEvent.MOUSE_WHEEL_EVENT_MASK) != 0 ||
                  mouseWheelListener != null) {
                  return true;
              }
              brebk;
          cbse InputMethodEvent.INPUT_METHOD_TEXT_CHANGED:
          cbse InputMethodEvent.CARET_POSITION_CHANGED:
              if ((eventMbsk & AWTEvent.INPUT_METHOD_EVENT_MASK) != 0 ||
                  inputMethodListener != null) {
                  return true;
              }
              brebk;
          cbse HierbrchyEvent.HIERARCHY_CHANGED:
              if ((eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0 ||
                  hierbrchyListener != null) {
                  return true;
              }
              brebk;
          cbse HierbrchyEvent.ANCESTOR_MOVED:
          cbse HierbrchyEvent.ANCESTOR_RESIZED:
              if ((eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0 ||
                  hierbrchyBoundsListener != null) {
                  return true;
              }
              brebk;
          cbse ActionEvent.ACTION_PERFORMED:
              if ((eventMbsk & AWTEvent.ACTION_EVENT_MASK) != 0) {
                  return true;
              }
              brebk;
          cbse TextEvent.TEXT_VALUE_CHANGED:
              if ((eventMbsk & AWTEvent.TEXT_EVENT_MASK) != 0) {
                  return true;
              }
              brebk;
          cbse ItemEvent.ITEM_STATE_CHANGED:
              if ((eventMbsk & AWTEvent.ITEM_EVENT_MASK) != 0) {
                  return true;
              }
              brebk;
          cbse AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED:
              if ((eventMbsk & AWTEvent.ADJUSTMENT_EVENT_MASK) != 0) {
                  return true;
              }
              brebk;
          defbult:
              brebk;
        }
        //
        // Alwbys pbss on events defined by externbl progrbms.
        //
        if (type > AWTEvent.RESERVED_ID_MAX) {
            return true;
        }
        return fblse;
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by dispbtchEvent(AWTEvent).
     */
    @Deprecbted
    public boolebn postEvent(Event e) {
        ComponentPeer peer = this.peer;

        if (hbndleEvent(e)) {
            e.consume();
            return true;
        }

        Component pbrent = this.pbrent;
        int eventx = e.x;
        int eventy = e.y;
        if (pbrent != null) {
            e.trbnslbte(x, y);
            if (pbrent.postEvent(e)) {
                e.consume();
                return true;
            }
            // restore coords
            e.x = eventx;
            e.y = eventy;
        }
        return fblse;
    }

    // Event source interfbces

    /**
     * Adds the specified component listener to receive component events from
     * this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the component listener
     * @see      jbvb.bwt.event.ComponentEvent
     * @see      jbvb.bwt.event.ComponentListener
     * @see      #removeComponentListener
     * @see      #getComponentListeners
     * @since    1.1
     */
    public synchronized void bddComponentListener(ComponentListener l) {
        if (l == null) {
            return;
        }
        componentListener = AWTEventMulticbster.bdd(componentListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified component listener so thbt it no longer
     * receives component events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     * @pbrbm    l   the component listener
     * @see      jbvb.bwt.event.ComponentEvent
     * @see      jbvb.bwt.event.ComponentListener
     * @see      #bddComponentListener
     * @see      #getComponentListeners
     * @since    1.1
     */
    public synchronized void removeComponentListener(ComponentListener l) {
        if (l == null) {
            return;
        }
        componentListener = AWTEventMulticbster.remove(componentListener, l);
    }

    /**
     * Returns bn brrby of bll the component listeners
     * registered on this component.
     *
     * @return bll <code>ComponentListener</code>s of this component
     *         or bn empty brrby if no component
     *         listeners bre currently registered
     *
     * @see #bddComponentListener
     * @see #removeComponentListener
     * @since 1.4
     */
    public synchronized ComponentListener[] getComponentListeners() {
        return getListeners(ComponentListener.clbss);
    }

    /**
     * Adds the specified focus listener to receive focus events from
     * this component when this component gbins input focus.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the focus listener
     * @see      jbvb.bwt.event.FocusEvent
     * @see      jbvb.bwt.event.FocusListener
     * @see      #removeFocusListener
     * @see      #getFocusListeners
     * @since    1.1
     */
    public synchronized void bddFocusListener(FocusListener l) {
        if (l == null) {
            return;
        }
        focusListener = AWTEventMulticbster.bdd(focusListener, l);
        newEventsOnly = true;

        // if this is b lightweight component, enbble focus events
        // in the nbtive contbiner.
        if (peer instbnceof LightweightPeer) {
            pbrent.proxyEnbbleEvents(AWTEvent.FOCUS_EVENT_MASK);
        }
    }

    /**
     * Removes the specified focus listener so thbt it no longer
     * receives focus events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the focus listener
     * @see      jbvb.bwt.event.FocusEvent
     * @see      jbvb.bwt.event.FocusListener
     * @see      #bddFocusListener
     * @see      #getFocusListeners
     * @since    1.1
     */
    public synchronized void removeFocusListener(FocusListener l) {
        if (l == null) {
            return;
        }
        focusListener = AWTEventMulticbster.remove(focusListener, l);
    }

    /**
     * Returns bn brrby of bll the focus listeners
     * registered on this component.
     *
     * @return bll of this component's <code>FocusListener</code>s
     *         or bn empty brrby if no component
     *         listeners bre currently registered
     *
     * @see #bddFocusListener
     * @see #removeFocusListener
     * @since 1.4
     */
    public synchronized FocusListener[] getFocusListeners() {
        return getListeners(FocusListener.clbss);
    }

    /**
     * Adds the specified hierbrchy listener to receive hierbrchy chbnged
     * events from this component when the hierbrchy to which this contbiner
     * belongs chbnges.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the hierbrchy listener
     * @see      jbvb.bwt.event.HierbrchyEvent
     * @see      jbvb.bwt.event.HierbrchyListener
     * @see      #removeHierbrchyListener
     * @see      #getHierbrchyListeners
     * @since    1.3
     */
    public void bddHierbrchyListener(HierbrchyListener l) {
        if (l == null) {
            return;
        }
        boolebn notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierbrchyListener == null &&
                 (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) == 0);
            hierbrchyListener = AWTEventMulticbster.bdd(hierbrchyListener, l);
            notifyAncestors = (notifyAncestors && hierbrchyListener != null);
            newEventsOnly = true;
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                bdjustListeningChildrenOnPbrent(AWTEvent.HIERARCHY_EVENT_MASK,
                                                1);
            }
        }
    }

    /**
     * Removes the specified hierbrchy listener so thbt it no longer
     * receives hierbrchy chbnged events from this component. This method
     * performs no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the hierbrchy listener
     * @see      jbvb.bwt.event.HierbrchyEvent
     * @see      jbvb.bwt.event.HierbrchyListener
     * @see      #bddHierbrchyListener
     * @see      #getHierbrchyListeners
     * @since    1.3
     */
    public void removeHierbrchyListener(HierbrchyListener l) {
        if (l == null) {
            return;
        }
        boolebn notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierbrchyListener != null &&
                 (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) == 0);
            hierbrchyListener =
                AWTEventMulticbster.remove(hierbrchyListener, l);
            notifyAncestors = (notifyAncestors && hierbrchyListener == null);
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                bdjustListeningChildrenOnPbrent(AWTEvent.HIERARCHY_EVENT_MASK,
                                                -1);
            }
        }
    }

    /**
     * Returns bn brrby of bll the hierbrchy listeners
     * registered on this component.
     *
     * @return bll of this component's <code>HierbrchyListener</code>s
     *         or bn empty brrby if no hierbrchy
     *         listeners bre currently registered
     *
     * @see      #bddHierbrchyListener
     * @see      #removeHierbrchyListener
     * @since    1.4
     */
    public synchronized HierbrchyListener[] getHierbrchyListeners() {
        return getListeners(HierbrchyListener.clbss);
    }

    /**
     * Adds the specified hierbrchy bounds listener to receive hierbrchy
     * bounds events from this component when the hierbrchy to which this
     * contbiner belongs chbnges.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the hierbrchy bounds listener
     * @see      jbvb.bwt.event.HierbrchyEvent
     * @see      jbvb.bwt.event.HierbrchyBoundsListener
     * @see      #removeHierbrchyBoundsListener
     * @see      #getHierbrchyBoundsListeners
     * @since    1.3
     */
    public void bddHierbrchyBoundsListener(HierbrchyBoundsListener l) {
        if (l == null) {
            return;
        }
        boolebn notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierbrchyBoundsListener == null &&
                 (eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) == 0);
            hierbrchyBoundsListener =
                AWTEventMulticbster.bdd(hierbrchyBoundsListener, l);
            notifyAncestors = (notifyAncestors &&
                               hierbrchyBoundsListener != null);
            newEventsOnly = true;
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                bdjustListeningChildrenOnPbrent(
                                                AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK, 1);
            }
        }
    }

    /**
     * Removes the specified hierbrchy bounds listener so thbt it no longer
     * receives hierbrchy bounds events from this component. This method
     * performs no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the hierbrchy bounds listener
     * @see      jbvb.bwt.event.HierbrchyEvent
     * @see      jbvb.bwt.event.HierbrchyBoundsListener
     * @see      #bddHierbrchyBoundsListener
     * @see      #getHierbrchyBoundsListeners
     * @since    1.3
     */
    public void removeHierbrchyBoundsListener(HierbrchyBoundsListener l) {
        if (l == null) {
            return;
        }
        boolebn notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierbrchyBoundsListener != null &&
                 (eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) == 0);
            hierbrchyBoundsListener =
                AWTEventMulticbster.remove(hierbrchyBoundsListener, l);
            notifyAncestors = (notifyAncestors &&
                               hierbrchyBoundsListener == null);
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                bdjustListeningChildrenOnPbrent(
                                                AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK, -1);
            }
        }
    }

    // Should only be cblled while holding the tree lock
    int numListening(long mbsk) {
        // One mbsk or the other, but not neither or both.
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            if ((mbsk != AWTEvent.HIERARCHY_EVENT_MASK) &&
                (mbsk != AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK))
            {
                eventLog.fine("Assertion fbiled");
            }
        }
        if ((mbsk == AWTEvent.HIERARCHY_EVENT_MASK &&
             (hierbrchyListener != null ||
              (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0)) ||
            (mbsk == AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK &&
             (hierbrchyBoundsListener != null ||
              (eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0))) {
            return 1;
        } else {
            return 0;
        }
    }

    // Should only be cblled while holding tree lock
    int countHierbrchyMembers() {
        return 1;
    }
    // Should only be cblled while holding the tree lock
    int crebteHierbrchyEvents(int id, Component chbnged,
                              Contbiner chbngedPbrent, long chbngeFlbgs,
                              boolebn enbbledOnToolkit) {
        switch (id) {
          cbse HierbrchyEvent.HIERARCHY_CHANGED:
              if (hierbrchyListener != null ||
                  (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0 ||
                  enbbledOnToolkit) {
                  HierbrchyEvent e = new HierbrchyEvent(this, id, chbnged,
                                                        chbngedPbrent,
                                                        chbngeFlbgs);
                  dispbtchEvent(e);
                  return 1;
              }
              brebk;
          cbse HierbrchyEvent.ANCESTOR_MOVED:
          cbse HierbrchyEvent.ANCESTOR_RESIZED:
              if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                  if (chbngeFlbgs != 0) {
                      eventLog.fine("Assertion (chbngeFlbgs == 0) fbiled");
                  }
              }
              if (hierbrchyBoundsListener != null ||
                  (eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0 ||
                  enbbledOnToolkit) {
                  HierbrchyEvent e = new HierbrchyEvent(this, id, chbnged,
                                                        chbngedPbrent);
                  dispbtchEvent(e);
                  return 1;
              }
              brebk;
          defbult:
              // bssert fblse
              if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                  eventLog.fine("This code must never be rebched");
              }
              brebk;
        }
        return 0;
    }

    /**
     * Returns bn brrby of bll the hierbrchy bounds listeners
     * registered on this component.
     *
     * @return bll of this component's <code>HierbrchyBoundsListener</code>s
     *         or bn empty brrby if no hierbrchy bounds
     *         listeners bre currently registered
     *
     * @see      #bddHierbrchyBoundsListener
     * @see      #removeHierbrchyBoundsListener
     * @since    1.4
     */
    public synchronized HierbrchyBoundsListener[] getHierbrchyBoundsListeners() {
        return getListeners(HierbrchyBoundsListener.clbss);
    }

    /*
     * Should only be cblled while holding the tree lock.
     * It's bdded only for overriding in jbvb.bwt.Window
     * becbuse pbrent in Window is owner.
     */
    void bdjustListeningChildrenOnPbrent(long mbsk, int num) {
        if (pbrent != null) {
            pbrent.bdjustListeningChildren(mbsk, num);
        }
    }

    /**
     * Adds the specified key listener to receive key events from
     * this component.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the key listener.
     * @see      jbvb.bwt.event.KeyEvent
     * @see      jbvb.bwt.event.KeyListener
     * @see      #removeKeyListener
     * @see      #getKeyListeners
     * @since    1.1
     */
    public synchronized void bddKeyListener(KeyListener l) {
        if (l == null) {
            return;
        }
        keyListener = AWTEventMulticbster.bdd(keyListener, l);
        newEventsOnly = true;

        // if this is b lightweight component, enbble key events
        // in the nbtive contbiner.
        if (peer instbnceof LightweightPeer) {
            pbrent.proxyEnbbleEvents(AWTEvent.KEY_EVENT_MASK);
        }
    }

    /**
     * Removes the specified key listener so thbt it no longer
     * receives key events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the key listener
     * @see      jbvb.bwt.event.KeyEvent
     * @see      jbvb.bwt.event.KeyListener
     * @see      #bddKeyListener
     * @see      #getKeyListeners
     * @since    1.1
     */
    public synchronized void removeKeyListener(KeyListener l) {
        if (l == null) {
            return;
        }
        keyListener = AWTEventMulticbster.remove(keyListener, l);
    }

    /**
     * Returns bn brrby of bll the key listeners
     * registered on this component.
     *
     * @return bll of this component's <code>KeyListener</code>s
     *         or bn empty brrby if no key
     *         listeners bre currently registered
     *
     * @see      #bddKeyListener
     * @see      #removeKeyListener
     * @since    1.4
     */
    public synchronized KeyListener[] getKeyListeners() {
        return getListeners(KeyListener.clbss);
    }

    /**
     * Adds the specified mouse listener to receive mouse events from
     * this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the mouse listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseListener
     * @see      #removeMouseListener
     * @see      #getMouseListeners
     * @since    1.1
     */
    public synchronized void bddMouseListener(MouseListener l) {
        if (l == null) {
            return;
        }
        mouseListener = AWTEventMulticbster.bdd(mouseListener,l);
        newEventsOnly = true;

        // if this is b lightweight component, enbble mouse events
        // in the nbtive contbiner.
        if (peer instbnceof LightweightPeer) {
            pbrent.proxyEnbbleEvents(AWTEvent.MOUSE_EVENT_MASK);
        }
    }

    /**
     * Removes the specified mouse listener so thbt it no longer
     * receives mouse events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the mouse listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseListener
     * @see      #bddMouseListener
     * @see      #getMouseListeners
     * @since    1.1
     */
    public synchronized void removeMouseListener(MouseListener l) {
        if (l == null) {
            return;
        }
        mouseListener = AWTEventMulticbster.remove(mouseListener, l);
    }

    /**
     * Returns bn brrby of bll the mouse listeners
     * registered on this component.
     *
     * @return bll of this component's <code>MouseListener</code>s
     *         or bn empty brrby if no mouse
     *         listeners bre currently registered
     *
     * @see      #bddMouseListener
     * @see      #removeMouseListener
     * @since    1.4
     */
    public synchronized MouseListener[] getMouseListeners() {
        return getListeners(MouseListener.clbss);
    }

    /**
     * Adds the specified mouse motion listener to receive mouse motion
     * events from this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the mouse motion listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseMotionListener
     * @see      #removeMouseMotionListener
     * @see      #getMouseMotionListeners
     * @since    1.1
     */
    public synchronized void bddMouseMotionListener(MouseMotionListener l) {
        if (l == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticbster.bdd(mouseMotionListener,l);
        newEventsOnly = true;

        // if this is b lightweight component, enbble mouse events
        // in the nbtive contbiner.
        if (peer instbnceof LightweightPeer) {
            pbrent.proxyEnbbleEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
    }

    /**
     * Removes the specified mouse motion listener so thbt it no longer
     * receives mouse motion events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the mouse motion listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseMotionListener
     * @see      #bddMouseMotionListener
     * @see      #getMouseMotionListeners
     * @since    1.1
     */
    public synchronized void removeMouseMotionListener(MouseMotionListener l) {
        if (l == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticbster.remove(mouseMotionListener, l);
    }

    /**
     * Returns bn brrby of bll the mouse motion listeners
     * registered on this component.
     *
     * @return bll of this component's <code>MouseMotionListener</code>s
     *         or bn empty brrby if no mouse motion
     *         listeners bre currently registered
     *
     * @see      #bddMouseMotionListener
     * @see      #removeMouseMotionListener
     * @since    1.4
     */
    public synchronized MouseMotionListener[] getMouseMotionListeners() {
        return getListeners(MouseMotionListener.clbss);
    }

    /**
     * Adds the specified mouse wheel listener to receive mouse wheel events
     * from this component.  Contbiners blso receive mouse wheel events from
     * sub-components.
     * <p>
     * For informbtion on how mouse wheel events bre dispbtched, see
     * the clbss description for {@link MouseWheelEvent}.
     * <p>
     * If l is <code>null</code>, no exception is thrown bnd no
     * bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the mouse wheel listener
     * @see      jbvb.bwt.event.MouseWheelEvent
     * @see      jbvb.bwt.event.MouseWheelListener
     * @see      #removeMouseWheelListener
     * @see      #getMouseWheelListeners
     * @since    1.4
     */
    public synchronized void bddMouseWheelListener(MouseWheelListener l) {
        if (l == null) {
            return;
        }
        mouseWheelListener = AWTEventMulticbster.bdd(mouseWheelListener,l);
        newEventsOnly = true;

        // if this is b lightweight component, enbble mouse events
        // in the nbtive contbiner.
        if (peer instbnceof LightweightPeer) {
            pbrent.proxyEnbbleEvents(AWTEvent.MOUSE_WHEEL_EVENT_MASK);
        }
    }

    /**
     * Removes the specified mouse wheel listener so thbt it no longer
     * receives mouse wheel events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the mouse wheel listener.
     * @see      jbvb.bwt.event.MouseWheelEvent
     * @see      jbvb.bwt.event.MouseWheelListener
     * @see      #bddMouseWheelListener
     * @see      #getMouseWheelListeners
     * @since    1.4
     */
    public synchronized void removeMouseWheelListener(MouseWheelListener l) {
        if (l == null) {
            return;
        }
        mouseWheelListener = AWTEventMulticbster.remove(mouseWheelListener, l);
    }

    /**
     * Returns bn brrby of bll the mouse wheel listeners
     * registered on this component.
     *
     * @return bll of this component's <code>MouseWheelListener</code>s
     *         or bn empty brrby if no mouse wheel
     *         listeners bre currently registered
     *
     * @see      #bddMouseWheelListener
     * @see      #removeMouseWheelListener
     * @since    1.4
     */
    public synchronized MouseWheelListener[] getMouseWheelListeners() {
        return getListeners(MouseWheelListener.clbss);
    }

    /**
     * Adds the specified input method listener to receive
     * input method events from this component. A component will
     * only receive input method events from input methods
     * if it blso overrides <code>getInputMethodRequests</code> to return bn
     * <code>InputMethodRequests</code> instbnce.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="{@docRoot}/jbvb/bwt/doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the input method listener
     * @see      jbvb.bwt.event.InputMethodEvent
     * @see      jbvb.bwt.event.InputMethodListener
     * @see      #removeInputMethodListener
     * @see      #getInputMethodListeners
     * @see      #getInputMethodRequests
     * @since    1.2
     */
    public synchronized void bddInputMethodListener(InputMethodListener l) {
        if (l == null) {
            return;
        }
        inputMethodListener = AWTEventMulticbster.bdd(inputMethodListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified input method listener so thbt it no longer
     * receives input method events from this component. This method performs
     * no function, nor does it throw bn exception, if the listener
     * specified by the brgument wbs not previously bdded to this component.
     * If listener <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    l   the input method listener
     * @see      jbvb.bwt.event.InputMethodEvent
     * @see      jbvb.bwt.event.InputMethodListener
     * @see      #bddInputMethodListener
     * @see      #getInputMethodListeners
     * @since    1.2
     */
    public synchronized void removeInputMethodListener(InputMethodListener l) {
        if (l == null) {
            return;
        }
        inputMethodListener = AWTEventMulticbster.remove(inputMethodListener, l);
    }

    /**
     * Returns bn brrby of bll the input method listeners
     * registered on this component.
     *
     * @return bll of this component's <code>InputMethodListener</code>s
     *         or bn empty brrby if no input method
     *         listeners bre currently registered
     *
     * @see      #bddInputMethodListener
     * @see      #removeInputMethodListener
     * @since    1.4
     */
    public synchronized InputMethodListener[] getInputMethodListeners() {
        return getListeners(InputMethodListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>Component</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>Component</code> <code>c</code>
     * for its mouse listeners with the following code:
     *
     * <pre>MouseListener[] mls = (MouseListener[])(c.getListeners(MouseListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or bn empty brrby if no such listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     * @throws NullPointerException if {@code listenerType} is {@code null}
     * @see #getComponentListeners
     * @see #getFocusListeners
     * @see #getHierbrchyListeners
     * @see #getHierbrchyBoundsListeners
     * @see #getKeyListeners
     * @see #getMouseListeners
     * @see #getMouseMotionListeners
     * @see #getMouseWheelListeners
     * @see #getInputMethodListeners
     * @see #getPropertyChbngeListeners
     *
     * @since 1.3
     */
    @SuppressWbrnings("unchecked")
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ComponentListener.clbss) {
            l = componentListener;
        } else if (listenerType == FocusListener.clbss) {
            l = focusListener;
        } else if (listenerType == HierbrchyListener.clbss) {
            l = hierbrchyListener;
        } else if (listenerType == HierbrchyBoundsListener.clbss) {
            l = hierbrchyBoundsListener;
        } else if (listenerType == KeyListener.clbss) {
            l = keyListener;
        } else if (listenerType == MouseListener.clbss) {
            l = mouseListener;
        } else if (listenerType == MouseMotionListener.clbss) {
            l = mouseMotionListener;
        } else if (listenerType == MouseWheelListener.clbss) {
            l = mouseWheelListener;
        } else if (listenerType == InputMethodListener.clbss) {
            l = inputMethodListener;
        } else if (listenerType == PropertyChbngeListener.clbss) {
            return (T[])getPropertyChbngeListeners();
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    /**
     * Gets the input method request hbndler which supports
     * requests from input methods for this component. A component
     * thbt supports on-the-spot text input must override this
     * method to return bn <code>InputMethodRequests</code> instbnce.
     * At the sbme time, it blso hbs to hbndle input method events.
     *
     * @return the input method request hbndler for this component,
     *          <code>null</code> by defbult
     * @see #bddInputMethodListener
     * @since 1.2
     */
    public InputMethodRequests getInputMethodRequests() {
        return null;
    }

    /**
     * Gets the input context used by this component for hbndling
     * the communicbtion with input methods when text is entered
     * in this component. By defbult, the input context used for
     * the pbrent component is returned. Components mby
     * override this to return b privbte input context.
     *
     * @return the input context used by this component;
     *          <code>null</code> if no context cbn be determined
     * @since 1.2
     */
    public InputContext getInputContext() {
        Contbiner pbrent = this.pbrent;
        if (pbrent == null) {
            return null;
        } else {
            return pbrent.getInputContext();
        }
    }

    /**
     * Enbbles the events defined by the specified event mbsk pbrbmeter
     * to be delivered to this component.
     * <p>
     * Event types bre butombticblly enbbled when b listener for
     * thbt event type is bdded to the component.
     * <p>
     * This method only needs to be invoked by subclbsses of
     * <code>Component</code> which desire to hbve the specified event
     * types delivered to <code>processEvent</code> regbrdless of whether
     * or not b listener is registered.
     * @pbrbm      eventsToEnbble   the event mbsk defining the event types
     * @see        #processEvent
     * @see        #disbbleEvents
     * @see        AWTEvent
     * @since      1.1
     */
    protected finbl void enbbleEvents(long eventsToEnbble) {
        long notifyAncestors = 0;
        synchronized (this) {
            if ((eventsToEnbble & AWTEvent.HIERARCHY_EVENT_MASK) != 0 &&
                hierbrchyListener == null &&
                (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) == 0) {
                notifyAncestors |= AWTEvent.HIERARCHY_EVENT_MASK;
            }
            if ((eventsToEnbble & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0 &&
                hierbrchyBoundsListener == null &&
                (eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) == 0) {
                notifyAncestors |= AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK;
            }
            eventMbsk |= eventsToEnbble;
            newEventsOnly = true;
        }

        // if this is b lightweight component, enbble mouse events
        // in the nbtive contbiner.
        if (peer instbnceof LightweightPeer) {
            pbrent.proxyEnbbleEvents(eventMbsk);
        }
        if (notifyAncestors != 0) {
            synchronized (getTreeLock()) {
                bdjustListeningChildrenOnPbrent(notifyAncestors, 1);
            }
        }
    }

    /**
     * Disbbles the events defined by the specified event mbsk pbrbmeter
     * from being delivered to this component.
     * @pbrbm      eventsToDisbble   the event mbsk defining the event types
     * @see        #enbbleEvents
     * @since      1.1
     */
    protected finbl void disbbleEvents(long eventsToDisbble) {
        long notifyAncestors = 0;
        synchronized (this) {
            if ((eventsToDisbble & AWTEvent.HIERARCHY_EVENT_MASK) != 0 &&
                hierbrchyListener == null &&
                (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0) {
                notifyAncestors |= AWTEvent.HIERARCHY_EVENT_MASK;
            }
            if ((eventsToDisbble & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK)!=0 &&
                hierbrchyBoundsListener == null &&
                (eventMbsk & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0) {
                notifyAncestors |= AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK;
            }
            eventMbsk &= ~eventsToDisbble;
        }
        if (notifyAncestors != 0) {
            synchronized (getTreeLock()) {
                bdjustListeningChildrenOnPbrent(notifyAncestors, -1);
            }
        }
    }

    trbnsient sun.bwt.EventQueueItem[] eventCbche;

    /**
     * @see #isCoblescingEnbbled
     * @see #checkCoblescing
     */
    trbnsient privbte boolebn coblescingEnbbled = checkCoblescing();

    /**
     * Webk mbp of known coblesceEvent overriders.
     * Vblue indicbtes whether overriden.
     * Bootstrbp clbsses bre not included.
     */
    privbte stbtic finbl Mbp<Clbss<?>, Boolebn> coblesceMbp =
        new jbvb.util.WebkHbshMbp<Clbss<?>, Boolebn>();

    /**
     * Indicbtes whether this clbss overrides coblesceEvents.
     * It is bssumed thbt bll clbsses thbt bre lobded from the bootstrbp
     *   do not.
     * The boostrbp clbss lobder is bssumed to be represented by null.
     * We do not check thbt the method reblly overrides
     *   (it might be stbtic, privbte or pbckbge privbte).
     */
     privbte boolebn checkCoblescing() {
         if (getClbss().getClbssLobder()==null) {
             return fblse;
         }
         finbl Clbss<? extends Component> clbzz = getClbss();
         synchronized (coblesceMbp) {
             // Check cbche.
             Boolebn vblue = coblesceMbp.get(clbzz);
             if (vblue != null) {
                 return vblue;
             }

             // Need to check non-bootstrbps.
             Boolebn enbbled = jbvb.security.AccessController.doPrivileged(
                 new jbvb.security.PrivilegedAction<Boolebn>() {
                     public Boolebn run() {
                         return isCoblesceEventsOverriden(clbzz);
                     }
                 }
                 );
             coblesceMbp.put(clbzz, enbbled);
             return enbbled;
         }
     }

    /**
     * Pbrbmeter types of coblesceEvents(AWTEvent,AWTEVent).
     */
    privbte stbtic finbl Clbss<?>[] coblesceEventsPbrbms = {
        AWTEvent.clbss, AWTEvent.clbss
    };

    /**
     * Indicbtes whether b clbss or its superclbsses override coblesceEvents.
     * Must be cblled with lock on coblesceMbp bnd privileged.
     * @see checkCoblsecing
     */
    privbte stbtic boolebn isCoblesceEventsOverriden(Clbss<?> clbzz) {
        bssert Threbd.holdsLock(coblesceMbp);

        // First check superclbss - we mby not need to bother ourselves.
        Clbss<?> superclbss = clbzz.getSuperclbss();
        if (superclbss == null) {
            // Only occurs on implementbtions thbt
            //   do not use null to represent the bootsrbp clbss lobder.
            return fblse;
        }
        if (superclbss.getClbssLobder() != null) {
            Boolebn vblue = coblesceMbp.get(superclbss);
            if (vblue == null) {
                // Not done blrebdy - recurse.
                if (isCoblesceEventsOverriden(superclbss)) {
                    coblesceMbp.put(superclbss, true);
                    return true;
                }
            } else if (vblue) {
                return true;
            }
        }

        try {
            // Throws if not overriden.
            clbzz.getDeclbredMethod(
                "coblesceEvents", coblesceEventsPbrbms
                );
            return true;
        } cbtch (NoSuchMethodException e) {
            // Not present in this clbss.
            return fblse;
        }
    }

    /**
     * Indicbtes whether coblesceEvents mby do something.
     */
    finbl boolebn isCoblescingEnbbled() {
        return coblescingEnbbled;
     }


    /**
     * Potentiblly coblesce bn event being posted with bn existing
     * event.  This method is cblled by <code>EventQueue.postEvent</code>
     * if bn event with the sbme ID bs the event to be posted is found in
     * the queue (both events must hbve this component bs their source).
     * This method either returns b coblesced event which replbces
     * the existing event (bnd the new event is then discbrded), or
     * <code>null</code> to indicbte thbt no combining should be done
     * (bdd the second event to the end of the queue).  Either event
     * pbrbmeter mby be modified bnd returned, bs the other one is discbrded
     * unless <code>null</code> is returned.
     * <p>
     * This implementbtion of <code>coblesceEvents</code> coblesces
     * two event types: mouse move (bnd drbg) events,
     * bnd pbint (bnd updbte) events.
     * For mouse move events the lbst event is blwbys returned, cbusing
     * intermedibte moves to be discbrded.  For pbint events, the new
     * event is coblesced into b complex <code>RepbintAreb</code> in the peer.
     * The new <code>AWTEvent</code> is blwbys returned.
     *
     * @pbrbm  existingEvent  the event blrebdy on the <code>EventQueue</code>
     * @pbrbm  newEvent       the event being posted to the
     *          <code>EventQueue</code>
     * @return b coblesced event, or <code>null</code> indicbting thbt no
     *          coblescing wbs done
     */
    protected AWTEvent coblesceEvents(AWTEvent existingEvent,
                                      AWTEvent newEvent) {
        return null;
    }

    /**
     * Processes events occurring on this component. By defbult this
     * method cblls the bppropribte
     * <code>process&lt;event&nbsp;type&gt;Event</code>
     * method for the given clbss of event.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm     e the event
     * @see       #processComponentEvent
     * @see       #processFocusEvent
     * @see       #processKeyEvent
     * @see       #processMouseEvent
     * @see       #processMouseMotionEvent
     * @see       #processInputMethodEvent
     * @see       #processHierbrchyEvent
     * @see       #processMouseWheelEvent
     * @since     1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof FocusEvent) {
            processFocusEvent((FocusEvent)e);

        } else if (e instbnceof MouseEvent) {
            switch(e.getID()) {
              cbse MouseEvent.MOUSE_PRESSED:
              cbse MouseEvent.MOUSE_RELEASED:
              cbse MouseEvent.MOUSE_CLICKED:
              cbse MouseEvent.MOUSE_ENTERED:
              cbse MouseEvent.MOUSE_EXITED:
                  processMouseEvent((MouseEvent)e);
                  brebk;
              cbse MouseEvent.MOUSE_MOVED:
              cbse MouseEvent.MOUSE_DRAGGED:
                  processMouseMotionEvent((MouseEvent)e);
                  brebk;
              cbse MouseEvent.MOUSE_WHEEL:
                  processMouseWheelEvent((MouseWheelEvent)e);
                  brebk;
            }

        } else if (e instbnceof KeyEvent) {
            processKeyEvent((KeyEvent)e);

        } else if (e instbnceof ComponentEvent) {
            processComponentEvent((ComponentEvent)e);
        } else if (e instbnceof InputMethodEvent) {
            processInputMethodEvent((InputMethodEvent)e);
        } else if (e instbnceof HierbrchyEvent) {
            switch (e.getID()) {
              cbse HierbrchyEvent.HIERARCHY_CHANGED:
                  processHierbrchyEvent((HierbrchyEvent)e);
                  brebk;
              cbse HierbrchyEvent.ANCESTOR_MOVED:
              cbse HierbrchyEvent.ANCESTOR_RESIZED:
                  processHierbrchyBoundsEvent((HierbrchyEvent)e);
                  brebk;
            }
        }
    }

    /**
     * Processes component events occurring on this component by
     * dispbtching them to bny registered
     * <code>ComponentListener</code> objects.
     * <p>
     * This method is not cblled unless component events bre
     * enbbled for this component. Component events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>A <code>ComponentListener</code> object is registered
     * vib <code>bddComponentListener</code>.
     * <li>Component events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the component event
     * @see         jbvb.bwt.event.ComponentEvent
     * @see         jbvb.bwt.event.ComponentListener
     * @see         #bddComponentListener
     * @see         #enbbleEvents
     * @since       1.1
     */
    protected void processComponentEvent(ComponentEvent e) {
        ComponentListener listener = componentListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              cbse ComponentEvent.COMPONENT_RESIZED:
                  listener.componentResized(e);
                  brebk;
              cbse ComponentEvent.COMPONENT_MOVED:
                  listener.componentMoved(e);
                  brebk;
              cbse ComponentEvent.COMPONENT_SHOWN:
                  listener.componentShown(e);
                  brebk;
              cbse ComponentEvent.COMPONENT_HIDDEN:
                  listener.componentHidden(e);
                  brebk;
            }
        }
    }

    /**
     * Processes focus events occurring on this component by
     * dispbtching them to bny registered
     * <code>FocusListener</code> objects.
     * <p>
     * This method is not cblled unless focus events bre
     * enbbled for this component. Focus events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>A <code>FocusListener</code> object is registered
     * vib <code>bddFocusListener</code>.
     * <li>Focus events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>
     * If focus events bre enbbled for b <code>Component</code>,
     * the current <code>KeybobrdFocusMbnbger</code> determines
     * whether or not b focus event should be dispbtched to
     * registered <code>FocusListener</code> objects.  If the
     * events bre to be dispbtched, the <code>KeybobrdFocusMbnbger</code>
     * cblls the <code>Component</code>'s <code>dispbtchEvent</code>
     * method, which results in b cbll to the <code>Component</code>'s
     * <code>processFocusEvent</code> method.
     * <p>
     * If focus events bre enbbled for b <code>Component</code>, cblling
     * the <code>Component</code>'s <code>dispbtchEvent</code> method
     * with b <code>FocusEvent</code> bs the brgument will result in b
     * cbll to the <code>Component</code>'s <code>processFocusEvent</code>
     * method regbrdless of the current <code>KeybobrdFocusMbnbger</code>.
     *
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the focus event
     * @see         jbvb.bwt.event.FocusEvent
     * @see         jbvb.bwt.event.FocusListener
     * @see         jbvb.bwt.KeybobrdFocusMbnbger
     * @see         #bddFocusListener
     * @see         #enbbleEvents
     * @see         #dispbtchEvent
     * @since       1.1
     */
    protected void processFocusEvent(FocusEvent e) {
        FocusListener listener = focusListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              cbse FocusEvent.FOCUS_GAINED:
                  listener.focusGbined(e);
                  brebk;
              cbse FocusEvent.FOCUS_LOST:
                  listener.focusLost(e);
                  brebk;
            }
        }
    }

    /**
     * Processes key events occurring on this component by
     * dispbtching them to bny registered
     * <code>KeyListener</code> objects.
     * <p>
     * This method is not cblled unless key events bre
     * enbbled for this component. Key events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>A <code>KeyListener</code> object is registered
     * vib <code>bddKeyListener</code>.
     * <li>Key events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     *
     * <p>
     * If key events bre enbbled for b <code>Component</code>,
     * the current <code>KeybobrdFocusMbnbger</code> determines
     * whether or not b key event should be dispbtched to
     * registered <code>KeyListener</code> objects.  The
     * <code>DefbultKeybobrdFocusMbnbger</code> will not dispbtch
     * key events to b <code>Component</code> thbt is not the focus
     * owner or is not showing.
     * <p>
     * As of J2SE 1.4, <code>KeyEvent</code>s bre redirected to
     * the focus owner. Plebse see the
     * <b href="doc-files/FocusSpec.html">Focus Specificbtion</b>
     * for further informbtion.
     * <p>
     * Cblling b <code>Component</code>'s <code>dispbtchEvent</code>
     * method with b <code>KeyEvent</code> bs the brgument will
     * result in b cbll to the <code>Component</code>'s
     * <code>processKeyEvent</code> method regbrdless of the
     * current <code>KeybobrdFocusMbnbger</code> bs long bs the
     * component is showing, focused, bnd enbbled, bnd key events
     * bre enbbled on it.
     * <p>If the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the key event
     * @see         jbvb.bwt.event.KeyEvent
     * @see         jbvb.bwt.event.KeyListener
     * @see         jbvb.bwt.KeybobrdFocusMbnbger
     * @see         jbvb.bwt.DefbultKeybobrdFocusMbnbger
     * @see         #processEvent
     * @see         #dispbtchEvent
     * @see         #bddKeyListener
     * @see         #enbbleEvents
     * @see         #isShowing
     * @since       1.1
     */
    protected void processKeyEvent(KeyEvent e) {
        KeyListener listener = keyListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              cbse KeyEvent.KEY_TYPED:
                  listener.keyTyped(e);
                  brebk;
              cbse KeyEvent.KEY_PRESSED:
                  listener.keyPressed(e);
                  brebk;
              cbse KeyEvent.KEY_RELEASED:
                  listener.keyRelebsed(e);
                  brebk;
            }
        }
    }

    /**
     * Processes mouse events occurring on this component by
     * dispbtching them to bny registered
     * <code>MouseListener</code> objects.
     * <p>
     * This method is not cblled unless mouse events bre
     * enbbled for this component. Mouse events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>A <code>MouseListener</code> object is registered
     * vib <code>bddMouseListener</code>.
     * <li>Mouse events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the mouse event
     * @see         jbvb.bwt.event.MouseEvent
     * @see         jbvb.bwt.event.MouseListener
     * @see         #bddMouseListener
     * @see         #enbbleEvents
     * @since       1.1
     */
    protected void processMouseEvent(MouseEvent e) {
        MouseListener listener = mouseListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              cbse MouseEvent.MOUSE_PRESSED:
                  listener.mousePressed(e);
                  brebk;
              cbse MouseEvent.MOUSE_RELEASED:
                  listener.mouseRelebsed(e);
                  brebk;
              cbse MouseEvent.MOUSE_CLICKED:
                  listener.mouseClicked(e);
                  brebk;
              cbse MouseEvent.MOUSE_EXITED:
                  listener.mouseExited(e);
                  brebk;
              cbse MouseEvent.MOUSE_ENTERED:
                  listener.mouseEntered(e);
                  brebk;
            }
        }
    }

    /**
     * Processes mouse motion events occurring on this component by
     * dispbtching them to bny registered
     * <code>MouseMotionListener</code> objects.
     * <p>
     * This method is not cblled unless mouse motion events bre
     * enbbled for this component. Mouse motion events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>A <code>MouseMotionListener</code> object is registered
     * vib <code>bddMouseMotionListener</code>.
     * <li>Mouse motion events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the mouse motion event
     * @see         jbvb.bwt.event.MouseEvent
     * @see         jbvb.bwt.event.MouseMotionListener
     * @see         #bddMouseMotionListener
     * @see         #enbbleEvents
     * @since       1.1
     */
    protected void processMouseMotionEvent(MouseEvent e) {
        MouseMotionListener listener = mouseMotionListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              cbse MouseEvent.MOUSE_MOVED:
                  listener.mouseMoved(e);
                  brebk;
              cbse MouseEvent.MOUSE_DRAGGED:
                  listener.mouseDrbgged(e);
                  brebk;
            }
        }
    }

    /**
     * Processes mouse wheel events occurring on this component by
     * dispbtching them to bny registered
     * <code>MouseWheelListener</code> objects.
     * <p>
     * This method is not cblled unless mouse wheel events bre
     * enbbled for this component. Mouse wheel events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>A <code>MouseWheelListener</code> object is registered
     * vib <code>bddMouseWheelListener</code>.
     * <li>Mouse wheel events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>
     * For informbtion on how mouse wheel events bre dispbtched, see
     * the clbss description for {@link MouseWheelEvent}.
     * <p>
     * Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the mouse wheel event
     * @see         jbvb.bwt.event.MouseWheelEvent
     * @see         jbvb.bwt.event.MouseWheelListener
     * @see         #bddMouseWheelListener
     * @see         #enbbleEvents
     * @since       1.4
     */
    protected void processMouseWheelEvent(MouseWheelEvent e) {
        MouseWheelListener listener = mouseWheelListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              cbse MouseEvent.MOUSE_WHEEL:
                  listener.mouseWheelMoved(e);
                  brebk;
            }
        }
    }

    boolebn postsOldMouseEvents() {
        return fblse;
    }

    /**
     * Processes input method events occurring on this component by
     * dispbtching them to bny registered
     * <code>InputMethodListener</code> objects.
     * <p>
     * This method is not cblled unless input method events
     * bre enbbled for this component. Input method events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>InputMethodListener</code> object is registered
     * vib <code>bddInputMethodListener</code>.
     * <li>Input method events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the input method event
     * @see         jbvb.bwt.event.InputMethodEvent
     * @see         jbvb.bwt.event.InputMethodListener
     * @see         #bddInputMethodListener
     * @see         #enbbleEvents
     * @since       1.2
     */
    protected void processInputMethodEvent(InputMethodEvent e) {
        InputMethodListener listener = inputMethodListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
              cbse InputMethodEvent.INPUT_METHOD_TEXT_CHANGED:
                  listener.inputMethodTextChbnged(e);
                  brebk;
              cbse InputMethodEvent.CARET_POSITION_CHANGED:
                  listener.cbretPositionChbnged(e);
                  brebk;
            }
        }
    }

    /**
     * Processes hierbrchy events occurring on this component by
     * dispbtching them to bny registered
     * <code>HierbrchyListener</code> objects.
     * <p>
     * This method is not cblled unless hierbrchy events
     * bre enbbled for this component. Hierbrchy events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>HierbrchyListener</code> object is registered
     * vib <code>bddHierbrchyListener</code>.
     * <li>Hierbrchy events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the hierbrchy event
     * @see         jbvb.bwt.event.HierbrchyEvent
     * @see         jbvb.bwt.event.HierbrchyListener
     * @see         #bddHierbrchyListener
     * @see         #enbbleEvents
     * @since       1.3
     */
    protected void processHierbrchyEvent(HierbrchyEvent e) {
        HierbrchyListener listener = hierbrchyListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
              cbse HierbrchyEvent.HIERARCHY_CHANGED:
                  listener.hierbrchyChbnged(e);
                  brebk;
            }
        }
    }

    /**
     * Processes hierbrchy bounds events occurring on this component by
     * dispbtching them to bny registered
     * <code>HierbrchyBoundsListener</code> objects.
     * <p>
     * This method is not cblled unless hierbrchy bounds events
     * bre enbbled for this component. Hierbrchy bounds events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>HierbrchyBoundsListener</code> object is registered
     * vib <code>bddHierbrchyBoundsListener</code>.
     * <li>Hierbrchy bounds events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the hierbrchy event
     * @see         jbvb.bwt.event.HierbrchyEvent
     * @see         jbvb.bwt.event.HierbrchyBoundsListener
     * @see         #bddHierbrchyBoundsListener
     * @see         #enbbleEvents
     * @since       1.3
     */
    protected void processHierbrchyBoundsEvent(HierbrchyEvent e) {
        HierbrchyBoundsListener listener = hierbrchyBoundsListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
              cbse HierbrchyEvent.ANCESTOR_MOVED:
                  listener.bncestorMoved(e);
                  brebk;
              cbse HierbrchyEvent.ANCESTOR_RESIZED:
                  listener.bncestorResized(e);
                  brebk;
            }
        }
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @return {@code true} if the event wbs hbndled, {@code fblse} otherwise
     * @deprecbted As of JDK version 1.1
     * replbced by processEvent(AWTEvent).
     */
    @Deprecbted
    public boolebn hbndleEvent(Event evt) {
        switch (evt.id) {
          cbse Event.MOUSE_ENTER:
              return mouseEnter(evt, evt.x, evt.y);

          cbse Event.MOUSE_EXIT:
              return mouseExit(evt, evt.x, evt.y);

          cbse Event.MOUSE_MOVE:
              return mouseMove(evt, evt.x, evt.y);

          cbse Event.MOUSE_DOWN:
              return mouseDown(evt, evt.x, evt.y);

          cbse Event.MOUSE_DRAG:
              return mouseDrbg(evt, evt.x, evt.y);

          cbse Event.MOUSE_UP:
              return mouseUp(evt, evt.x, evt.y);

          cbse Event.KEY_PRESS:
          cbse Event.KEY_ACTION:
              return keyDown(evt, evt.key);

          cbse Event.KEY_RELEASE:
          cbse Event.KEY_ACTION_RELEASE:
              return keyUp(evt, evt.key);

          cbse Event.ACTION_EVENT:
              return bction(evt, evt.brg);
          cbse Event.GOT_FOCUS:
              return gotFocus(evt, evt.brg);
          cbse Event.LOST_FOCUS:
              return lostFocus(evt, evt.brg);
        }
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  x the x coordinbte
     * @pbrbm  y the y coordinbte
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processMouseEvent(MouseEvent).
     */
    @Deprecbted
    public boolebn mouseDown(Event evt, int x, int y) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  x the x coordinbte
     * @pbrbm  y the y coordinbte
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processMouseMotionEvent(MouseEvent).
     */
    @Deprecbted
    public boolebn mouseDrbg(Event evt, int x, int y) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  x the x coordinbte
     * @pbrbm  y the y coordinbte
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processMouseEvent(MouseEvent).
     */
    @Deprecbted
    public boolebn mouseUp(Event evt, int x, int y) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  x the x coordinbte
     * @pbrbm  y the y coordinbte
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processMouseMotionEvent(MouseEvent).
     */
    @Deprecbted
    public boolebn mouseMove(Event evt, int x, int y) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  x the x coordinbte
     * @pbrbm  y the y coordinbte
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processMouseEvent(MouseEvent).
     */
    @Deprecbted
    public boolebn mouseEnter(Event evt, int x, int y) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  x the x coordinbte
     * @pbrbm  y the y coordinbte
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processMouseEvent(MouseEvent).
     */
    @Deprecbted
    public boolebn mouseExit(Event evt, int x, int y) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  key the key pressed
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processKeyEvent(KeyEvent).
     */
    @Deprecbted
    public boolebn keyDown(Event evt, int key) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  key the key pressed
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processKeyEvent(KeyEvent).
     */
    @Deprecbted
    public boolebn keyUp(Event evt, int key) {
        return fblse;
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  whbt the object bcted on
     * @return {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * should register this component bs ActionListener on component
     * which fires bction events.
     */
    @Deprecbted
    public boolebn bction(Event evt, Object whbt) {
        return fblse;
    }

    /**
     * Mbkes this <code>Component</code> displbybble by connecting it to b
     * nbtive screen resource.
     * This method is cblled internblly by the toolkit bnd should
     * not be cblled directly by progrbms.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @see       #isDisplbybble
     * @see       #removeNotify
     * @see #invblidbte
     * @since 1.0
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            ComponentPeer peer = this.peer;
            if (peer == null || peer instbnceof LightweightPeer){
                if (peer == null) {
                    // Updbte both the Component's peer vbribble bnd the locbl
                    // vbribble we use for threbd sbfety.
                    this.peer = peer = getToolkit().crebteComponent(this);
                }

                // This is b lightweight component which mebns it won't be
                // bble to get window-relbted events by itself.  If bny
                // hbve been enbbled, then the nebrest nbtive contbiner must
                // be enbbled.
                if (pbrent != null) {
                    long mbsk = 0;
                    if ((mouseListener != null) || ((eventMbsk & AWTEvent.MOUSE_EVENT_MASK) != 0)) {
                        mbsk |= AWTEvent.MOUSE_EVENT_MASK;
                    }
                    if ((mouseMotionListener != null) ||
                        ((eventMbsk & AWTEvent.MOUSE_MOTION_EVENT_MASK) != 0)) {
                        mbsk |= AWTEvent.MOUSE_MOTION_EVENT_MASK;
                    }
                    if ((mouseWheelListener != null ) ||
                        ((eventMbsk & AWTEvent.MOUSE_WHEEL_EVENT_MASK) != 0)) {
                        mbsk |= AWTEvent.MOUSE_WHEEL_EVENT_MASK;
                    }
                    if (focusListener != null || (eventMbsk & AWTEvent.FOCUS_EVENT_MASK) != 0) {
                        mbsk |= AWTEvent.FOCUS_EVENT_MASK;
                    }
                    if (keyListener != null || (eventMbsk & AWTEvent.KEY_EVENT_MASK) != 0) {
                        mbsk |= AWTEvent.KEY_EVENT_MASK;
                    }
                    if (mbsk != 0) {
                        pbrent.proxyEnbbleEvents(mbsk);
                    }
                }
            } else {
                // It's nbtive. If the pbrent is lightweight it will need some
                // help.
                Contbiner pbrent = getContbiner();
                if (pbrent != null && pbrent.isLightweight()) {
                    relocbteComponent();
                    if (!pbrent.isRecursivelyVisibleUpToHebvyweightContbiner())
                    {
                        peer.setVisible(fblse);
                    }
                }
            }
            invblidbte();

            int npopups = (popups != null? popups.size() : 0);
            for (int i = 0 ; i < npopups ; i++) {
                PopupMenu popup = popups.elementAt(i);
                popup.bddNotify();
            }

            if (dropTbrget != null) dropTbrget.bddNotify(peer);

            peerFont = getFont();

            if (getContbiner() != null && !isAddNotifyComplete) {
                getContbiner().increbseComponentCount(this);
            }


            // Updbte stbcking order
            updbteZOrder();

            if (!isAddNotifyComplete) {
                mixOnShowing();
            }

            isAddNotifyComplete = true;

            if (hierbrchyListener != null ||
                (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0 ||
                Toolkit.enbbledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK)) {
                HierbrchyEvent e =
                    new HierbrchyEvent(this, HierbrchyEvent.HIERARCHY_CHANGED,
                                       this, pbrent,
                                       HierbrchyEvent.DISPLAYABILITY_CHANGED |
                                       ((isRecursivelyVisible())
                                        ? HierbrchyEvent.SHOWING_CHANGED
                                        : 0));
                dispbtchEvent(e);
            }
        }
    }

    /**
     * Mbkes this <code>Component</code> undisplbybble by destroying it nbtive
     * screen resource.
     * <p>
     * This method is cblled by the toolkit internblly bnd should
     * not be cblled directly by progrbms. Code overriding
     * this method should cbll <code>super.removeNotify</code> bs
     * the first line of the overriding method.
     *
     * @see       #isDisplbybble
     * @see       #bddNotify
     * @since 1.0
     */
    public void removeNotify() {
        KeybobrdFocusMbnbger.clebrMostRecentFocusOwner(this);
        if (KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
            getPermbnentFocusOwner() == this)
        {
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                setGlobblPermbnentFocusOwner(null);
        }

        synchronized (getTreeLock()) {
            clebrLightweightDispbtcherOnRemove(this);

            if (isFocusOwner() && KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbledFor(this)) {
                trbnsferFocus(true);
            }

            if (getContbiner() != null && isAddNotifyComplete) {
                getContbiner().decrebseComponentCount(this);
            }

            int npopups = (popups != null? popups.size() : 0);
            for (int i = 0 ; i < npopups ; i++) {
                PopupMenu popup = popups.elementAt(i);
                popup.removeNotify();
            }
            // If there is bny input context for this component, notify
            // thbt this component is being removed. (This hbs to be done
            // before hiding peer.)
            if ((eventMbsk & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0) {
                InputContext inputContext = getInputContext();
                if (inputContext != null) {
                    inputContext.removeNotify(this);
                }
            }

            ComponentPeer p = peer;
            if (p != null) {
                boolebn isLightweight = isLightweight();

                if (bufferStrbtegy instbnceof FlipBufferStrbtegy) {
                    ((FlipBufferStrbtegy)bufferStrbtegy).destroyBuffers();
                }

                if (dropTbrget != null) dropTbrget.removeNotify(peer);

                // Hide peer first to stop system events such bs cursor moves.
                if (visible) {
                    p.setVisible(fblse);
                }

                peer = null; // Stop peer updbtes.
                peerFont = null;

                Toolkit.getEventQueue().removeSourceEvents(this, fblse);
                KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                    discbrdKeyEvents(this);

                p.dispose();

                mixOnHiding(isLightweight);

                isAddNotifyComplete = fblse;
                // Nullifying compoundShbpe mebns thbt the component hbs normbl shbpe
                // (or hbs no shbpe bt bll).
                this.compoundShbpe = null;
            }

            if (hierbrchyListener != null ||
                (eventMbsk & AWTEvent.HIERARCHY_EVENT_MASK) != 0 ||
                Toolkit.enbbledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK)) {
                HierbrchyEvent e =
                    new HierbrchyEvent(this, HierbrchyEvent.HIERARCHY_CHANGED,
                                       this, pbrent,
                                       HierbrchyEvent.DISPLAYABILITY_CHANGED |
                                       ((isRecursivelyVisible())
                                        ? HierbrchyEvent.SHOWING_CHANGED
                                        : 0));
                dispbtchEvent(e);
            }
        }
    }

    /**
     * @pbrbm  evt the event to hbndle
     * @pbrbm  whbt the object focused
     * @return  {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processFocusEvent(FocusEvent).
     */
    @Deprecbted
    public boolebn gotFocus(Event evt, Object whbt) {
        return fblse;
    }

    /**
     * @pbrbm evt  the event to hbndle
     * @pbrbm whbt the object focused
     * @return  {@code fblse}
     * @deprecbted As of JDK version 1.1,
     * replbced by processFocusEvent(FocusEvent).
     */
    @Deprecbted
    public boolebn lostFocus(Event evt, Object whbt) {
        return fblse;
    }

    /**
     * Returns whether this <code>Component</code> cbn become the focus
     * owner.
     *
     * @return <code>true</code> if this <code>Component</code> is
     * focusbble; <code>fblse</code> otherwise
     * @see #setFocusbble
     * @since 1.1
     * @deprecbted As of 1.4, replbced by <code>isFocusbble()</code>.
     */
    @Deprecbted
    public boolebn isFocusTrbversbble() {
        if (isFocusTrbversbbleOverridden == FOCUS_TRAVERSABLE_UNKNOWN) {
            isFocusTrbversbbleOverridden = FOCUS_TRAVERSABLE_DEFAULT;
        }
        return focusbble;
    }

    /**
     * Returns whether this Component cbn be focused.
     *
     * @return <code>true</code> if this Component is focusbble;
     *         <code>fblse</code> otherwise.
     * @see #setFocusbble
     * @since 1.4
     */
    public boolebn isFocusbble() {
        return isFocusTrbversbble();
    }

    /**
     * Sets the focusbble stbte of this Component to the specified vblue. This
     * vblue overrides the Component's defbult focusbbility.
     *
     * @pbrbm focusbble indicbtes whether this Component is focusbble
     * @see #isFocusbble
     * @since 1.4
     * @bebninfo
     *       bound: true
     */
    public void setFocusbble(boolebn focusbble) {
        boolebn oldFocusbble;
        synchronized (this) {
            oldFocusbble = this.focusbble;
            this.focusbble = focusbble;
        }
        isFocusTrbversbbleOverridden = FOCUS_TRAVERSABLE_SET;

        firePropertyChbnge("focusbble", oldFocusbble, focusbble);
        if (oldFocusbble && !focusbble) {
            if (isFocusOwner() && KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbled()) {
                trbnsferFocus(true);
            }
            KeybobrdFocusMbnbger.clebrMostRecentFocusOwner(this);
        }
    }

    finbl boolebn isFocusTrbversbbleOverridden() {
        return (isFocusTrbversbbleOverridden != FOCUS_TRAVERSABLE_DEFAULT);
    }

    /**
     * Sets the focus trbversbl keys for b given trbversbl operbtion for this
     * Component.
     * <p>
     * The defbult vblues for b Component's focus trbversbl keys bre
     * implementbtion-dependent. Sun recommends thbt bll implementbtions for b
     * pbrticulbr nbtive plbtform use the sbme defbult vblues. The
     * recommendbtions for Windows bnd Unix bre listed below. These
     * recommendbtions bre used in the Sun AWT implementbtions.
     *
     * <tbble border=1 summbry="Recommended defbult vblues for b Component's focus trbversbl keys">
     * <tr>
     *    <th>Identifier</th>
     *    <th>Mebning</th>
     *    <th>Defbult</th>
     * </tr>
     * <tr>
     *    <td>KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS</td>
     *    <td>Normbl forwbrd keybobrd trbversbl</td>
     *    <td>TAB on KEY_PRESSED, CTRL-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS</td>
     *    <td>Normbl reverse keybobrd trbversbl</td>
     *    <td>SHIFT-TAB on KEY_PRESSED, CTRL-SHIFT-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS</td>
     *    <td>Go up one focus trbversbl cycle</td>
     *    <td>none</td>
     * </tr>
     * </tbble>
     *
     * To disbble b trbversbl key, use bn empty Set; Collections.EMPTY_SET is
     * recommended.
     * <p>
     * Using the AWTKeyStroke API, client code cbn specify on which of two
     * specific KeyEvents, KEY_PRESSED or KEY_RELEASED, the focus trbversbl
     * operbtion will occur. Regbrdless of which KeyEvent is specified,
     * however, bll KeyEvents relbted to the focus trbversbl key, including the
     * bssocibted KEY_TYPED event, will be consumed, bnd will not be dispbtched
     * to bny Component. It is b runtime error to specify b KEY_TYPED event bs
     * mbpping to b focus trbversbl operbtion, or to mbp the sbme event to
     * multiple defbult focus trbversbl operbtions.
     * <p>
     * If b vblue of null is specified for the Set, this Component inherits the
     * Set from its pbrent. If bll bncestors of this Component hbve null
     * specified for the Set, then the current KeybobrdFocusMbnbger's defbult
     * Set is used.
     * <p>
     * This method mby throw b {@code ClbssCbstException} if bny {@code Object}
     * in {@code keystrokes} is not bn {@code AWTKeyStroke}.
     *
     * @pbrbm id one of KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *        KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, or
     *        KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS
     * @pbrbm keystrokes the Set of AWTKeyStroke for the specified operbtion
     * @see #getFocusTrbversblKeys
     * @see KeybobrdFocusMbnbger#FORWARD_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#BACKWARD_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#UP_CYCLE_TRAVERSAL_KEYS
     * @throws IllegblArgumentException if id is not one of
     *         KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, or
     *         KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS, or if keystrokes
     *         contbins null, or if bny keystroke represents b KEY_TYPED event,
     *         or if bny keystroke blrebdy mbps to bnother focus trbversbl
     *         operbtion for this Component
     * @since 1.4
     * @bebninfo
     *       bound: true
     */
    public void setFocusTrbversblKeys(int id,
                                      Set<? extends AWTKeyStroke> keystrokes)
    {
        if (id < 0 || id >= KeybobrdFocusMbnbger.TRAVERSAL_KEY_LENGTH - 1) {
            throw new IllegblArgumentException("invblid focus trbversbl key identifier");
        }

        setFocusTrbversblKeys_NoIDCheck(id, keystrokes);
    }

    /**
     * Returns the Set of focus trbversbl keys for b given trbversbl operbtion
     * for this Component. (See
     * <code>setFocusTrbversblKeys</code> for b full description of ebch key.)
     * <p>
     * If b Set of trbversbl keys hbs not been explicitly defined for this
     * Component, then this Component's pbrent's Set is returned. If no Set
     * hbs been explicitly defined for bny of this Component's bncestors, then
     * the current KeybobrdFocusMbnbger's defbult Set is returned.
     *
     * @pbrbm id one of KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *        KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, or
     *        KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS
     * @return the Set of AWTKeyStrokes for the specified operbtion. The Set
     *         will be unmodifibble, bnd mby be empty. null will never be
     *         returned.
     * @see #setFocusTrbversblKeys
     * @see KeybobrdFocusMbnbger#FORWARD_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#BACKWARD_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#UP_CYCLE_TRAVERSAL_KEYS
     * @throws IllegblArgumentException if id is not one of
     *         KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, or
     *         KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS
     * @since 1.4
     */
    public Set<AWTKeyStroke> getFocusTrbversblKeys(int id) {
        if (id < 0 || id >= KeybobrdFocusMbnbger.TRAVERSAL_KEY_LENGTH - 1) {
            throw new IllegblArgumentException("invblid focus trbversbl key identifier");
        }

        return getFocusTrbversblKeys_NoIDCheck(id);
    }

    // We define these methods so thbt Contbiner does not need to repebt this
    // code. Contbiner cbnnot cbll super.<method> becbuse Contbiner bllows
    // DOWN_CYCLE_TRAVERSAL_KEY while Component does not. The Component method
    // would erroneously generbte bn IllegblArgumentException for
    // DOWN_CYCLE_TRAVERSAL_KEY.
    finbl void setFocusTrbversblKeys_NoIDCheck(int id, Set<? extends AWTKeyStroke> keystrokes) {
        Set<AWTKeyStroke> oldKeys;

        synchronized (this) {
            if (focusTrbversblKeys == null) {
                initiblizeFocusTrbversblKeys();
            }

            if (keystrokes != null) {
                for (AWTKeyStroke keystroke : keystrokes ) {

                    if (keystroke == null) {
                        throw new IllegblArgumentException("cbnnot set null focus trbversbl key");
                    }

                    if (keystroke.getKeyChbr() != KeyEvent.CHAR_UNDEFINED) {
                        throw new IllegblArgumentException("focus trbversbl keys cbnnot mbp to KEY_TYPED events");
                    }

                    for (int i = 0; i < focusTrbversblKeys.length; i++) {
                        if (i == id) {
                            continue;
                        }

                        if (getFocusTrbversblKeys_NoIDCheck(i).contbins(keystroke))
                        {
                            throw new IllegblArgumentException("focus trbversbl keys must be unique for b Component");
                        }
                    }
                }
            }

            oldKeys = focusTrbversblKeys[id];
            focusTrbversblKeys[id] = (keystrokes != null)
                ? Collections.unmodifibbleSet(new HbshSet<AWTKeyStroke>(keystrokes))
                : null;
        }

        firePropertyChbnge(focusTrbversblKeyPropertyNbmes[id], oldKeys,
                           keystrokes);
    }
    finbl Set<AWTKeyStroke> getFocusTrbversblKeys_NoIDCheck(int id) {
        // Okby to return Set directly becbuse it is bn unmodifibble view
        @SuppressWbrnings("unchecked")
        Set<AWTKeyStroke> keystrokes = (focusTrbversblKeys != null)
            ? focusTrbversblKeys[id]
            : null;

        if (keystrokes != null) {
            return keystrokes;
        } else {
            Contbiner pbrent = this.pbrent;
            if (pbrent != null) {
                return pbrent.getFocusTrbversblKeys(id);
            } else {
                return KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                    getDefbultFocusTrbversblKeys(id);
            }
        }
    }

    /**
     * Returns whether the Set of focus trbversbl keys for the given focus
     * trbversbl operbtion hbs been explicitly defined for this Component. If
     * this method returns <code>fblse</code>, this Component is inheriting the
     * Set from bn bncestor, or from the current KeybobrdFocusMbnbger.
     *
     * @pbrbm id one of KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *        KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, or
     *        KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS
     * @return <code>true</code> if the the Set of focus trbversbl keys for the
     *         given focus trbversbl operbtion hbs been explicitly defined for
     *         this Component; <code>fblse</code> otherwise.
     * @throws IllegblArgumentException if id is not one of
     *         KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, or
     *         KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS
     * @since 1.4
     */
    public boolebn breFocusTrbversblKeysSet(int id) {
        if (id < 0 || id >= KeybobrdFocusMbnbger.TRAVERSAL_KEY_LENGTH - 1) {
            throw new IllegblArgumentException("invblid focus trbversbl key identifier");
        }

        return (focusTrbversblKeys != null && focusTrbversblKeys[id] != null);
    }

    /**
     * Sets whether focus trbversbl keys bre enbbled for this Component.
     * Components for which focus trbversbl keys bre disbbled receive key
     * events for focus trbversbl keys. Components for which focus trbversbl
     * keys bre enbbled do not see these events; instebd, the events bre
     * butombticblly converted to trbversbl operbtions.
     *
     * @pbrbm focusTrbversblKeysEnbbled whether focus trbversbl keys bre
     *        enbbled for this Component
     * @see #getFocusTrbversblKeysEnbbled
     * @see #setFocusTrbversblKeys
     * @see #getFocusTrbversblKeys
     * @since 1.4
     * @bebninfo
     *       bound: true
     */
    public void setFocusTrbversblKeysEnbbled(boolebn
                                             focusTrbversblKeysEnbbled) {
        boolebn oldFocusTrbversblKeysEnbbled;
        synchronized (this) {
            oldFocusTrbversblKeysEnbbled = this.focusTrbversblKeysEnbbled;
            this.focusTrbversblKeysEnbbled = focusTrbversblKeysEnbbled;
        }
        firePropertyChbnge("focusTrbversblKeysEnbbled",
                           oldFocusTrbversblKeysEnbbled,
                           focusTrbversblKeysEnbbled);
    }

    /**
     * Returns whether focus trbversbl keys bre enbbled for this Component.
     * Components for which focus trbversbl keys bre disbbled receive key
     * events for focus trbversbl keys. Components for which focus trbversbl
     * keys bre enbbled do not see these events; instebd, the events bre
     * butombticblly converted to trbversbl operbtions.
     *
     * @return whether focus trbversbl keys bre enbbled for this Component
     * @see #setFocusTrbversblKeysEnbbled
     * @see #setFocusTrbversblKeys
     * @see #getFocusTrbversblKeys
     * @since 1.4
     */
    public boolebn getFocusTrbversblKeysEnbbled() {
        return focusTrbversblKeysEnbbled;
    }

    /**
     * Requests thbt this Component get the input focus, bnd thbt this
     * Component's top-level bncestor become the focused Window. This
     * component must be displbybble, focusbble, visible bnd bll of
     * its bncestors (with the exception of the top-level Window) must
     * be visible for the request to be grbnted. Every effort will be
     * mbde to honor the request; however, in some cbses it mby be
     * impossible to do so. Developers must never bssume thbt this
     * Component is the focus owner until this Component receives b
     * FOCUS_GAINED event. If this request is denied becbuse this
     * Component's top-level Window cbnnot become the focused Window,
     * the request will be remembered bnd will be grbnted when the
     * Window is lbter focused by the user.
     * <p>
     * This method cbnnot be used to set the focus owner to no Component bt
     * bll. Use <code>KeybobrdFocusMbnbger.clebrGlobblFocusOwner()</code>
     * instebd.
     * <p>
     * Becbuse the focus behbvior of this method is plbtform-dependent,
     * developers bre strongly encourbged to use
     * <code>requestFocusInWindow</code> when possible.
     *
     * <p>Note: Not bll focus trbnsfers result from invoking this method. As
     * such, b component mby receive focus without this or bny of the other
     * {@code requestFocus} methods of {@code Component} being invoked.
     *
     * @see #requestFocusInWindow
     * @see jbvb.bwt.event.FocusEvent
     * @see #bddFocusListener
     * @see #isFocusbble
     * @see #isDisplbybble
     * @see KeybobrdFocusMbnbger#clebrGlobblFocusOwner
     * @since 1.0
     */
    public void requestFocus() {
        requestFocusHelper(fblse, true);
    }

    boolebn requestFocus(CbusedFocusEvent.Cbuse cbuse) {
        return requestFocusHelper(fblse, true, cbuse);
    }

    /**
     * Requests thbt this <code>Component</code> get the input focus,
     * bnd thbt this <code>Component</code>'s top-level bncestor
     * become the focused <code>Window</code>. This component must be
     * displbybble, focusbble, visible bnd bll of its bncestors (with
     * the exception of the top-level Window) must be visible for the
     * request to be grbnted. Every effort will be mbde to honor the
     * request; however, in some cbses it mby be impossible to do
     * so. Developers must never bssume thbt this component is the
     * focus owner until this component receives b FOCUS_GAINED
     * event. If this request is denied becbuse this component's
     * top-level window cbnnot become the focused window, the request
     * will be remembered bnd will be grbnted when the window is lbter
     * focused by the user.
     * <p>
     * This method returns b boolebn vblue. If <code>fblse</code> is returned,
     * the request is <b>gubrbnteed to fbil</b>. If <code>true</code> is
     * returned, the request will succeed <b>unless</b> it is vetoed, or bn
     * extrbordinbry event, such bs disposbl of the component's peer, occurs
     * before the request cbn be grbnted by the nbtive windowing system. Agbin,
     * while b return vblue of <code>true</code> indicbtes thbt the request is
     * likely to succeed, developers must never bssume thbt this component is
     * the focus owner until this component receives b FOCUS_GAINED event.
     * <p>
     * This method cbnnot be used to set the focus owner to no component bt
     * bll. Use <code>KeybobrdFocusMbnbger.clebrGlobblFocusOwner</code>
     * instebd.
     * <p>
     * Becbuse the focus behbvior of this method is plbtform-dependent,
     * developers bre strongly encourbged to use
     * <code>requestFocusInWindow</code> when possible.
     * <p>
     * Every effort will be mbde to ensure thbt <code>FocusEvent</code>s
     * generbted bs b
     * result of this request will hbve the specified temporbry vblue. However,
     * becbuse specifying bn brbitrbry temporbry stbte mby not be implementbble
     * on bll nbtive windowing systems, correct behbvior for this method cbn be
     * gubrbnteed only for lightweight <code>Component</code>s.
     * This method is not intended
     * for generbl use, but exists instebd bs b hook for lightweight component
     * librbries, such bs Swing.
     *
     * <p>Note: Not bll focus trbnsfers result from invoking this method. As
     * such, b component mby receive focus without this or bny of the other
     * {@code requestFocus} methods of {@code Component} being invoked.
     *
     * @pbrbm temporbry true if the focus chbnge is temporbry,
     *        such bs when the window loses the focus; for
     *        more informbtion on temporbry focus chbnges see the
     *<b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
     * @return <code>fblse</code> if the focus chbnge request is gubrbnteed to
     *         fbil; <code>true</code> if it is likely to succeed
     * @see jbvb.bwt.event.FocusEvent
     * @see #bddFocusListener
     * @see #isFocusbble
     * @see #isDisplbybble
     * @see KeybobrdFocusMbnbger#clebrGlobblFocusOwner
     * @since 1.4
     */
    protected boolebn requestFocus(boolebn temporbry) {
        return requestFocusHelper(temporbry, true);
    }

    boolebn requestFocus(boolebn temporbry, CbusedFocusEvent.Cbuse cbuse) {
        return requestFocusHelper(temporbry, true, cbuse);
    }
    /**
     * Requests thbt this Component get the input focus, if this
     * Component's top-level bncestor is blrebdy the focused
     * Window. This component must be displbybble, focusbble, visible
     * bnd bll of its bncestors (with the exception of the top-level
     * Window) must be visible for the request to be grbnted. Every
     * effort will be mbde to honor the request; however, in some
     * cbses it mby be impossible to do so. Developers must never
     * bssume thbt this Component is the focus owner until this
     * Component receives b FOCUS_GAINED event.
     * <p>
     * This method returns b boolebn vblue. If <code>fblse</code> is returned,
     * the request is <b>gubrbnteed to fbil</b>. If <code>true</code> is
     * returned, the request will succeed <b>unless</b> it is vetoed, or bn
     * extrbordinbry event, such bs disposbl of the Component's peer, occurs
     * before the request cbn be grbnted by the nbtive windowing system. Agbin,
     * while b return vblue of <code>true</code> indicbtes thbt the request is
     * likely to succeed, developers must never bssume thbt this Component is
     * the focus owner until this Component receives b FOCUS_GAINED event.
     * <p>
     * This method cbnnot be used to set the focus owner to no Component bt
     * bll. Use <code>KeybobrdFocusMbnbger.clebrGlobblFocusOwner()</code>
     * instebd.
     * <p>
     * The focus behbvior of this method cbn be implemented uniformly bcross
     * plbtforms, bnd thus developers bre strongly encourbged to use this
     * method over <code>requestFocus</code> when possible. Code which relies
     * on <code>requestFocus</code> mby exhibit different focus behbvior on
     * different plbtforms.
     *
     * <p>Note: Not bll focus trbnsfers result from invoking this method. As
     * such, b component mby receive focus without this or bny of the other
     * {@code requestFocus} methods of {@code Component} being invoked.
     *
     * @return <code>fblse</code> if the focus chbnge request is gubrbnteed to
     *         fbil; <code>true</code> if it is likely to succeed
     * @see #requestFocus
     * @see jbvb.bwt.event.FocusEvent
     * @see #bddFocusListener
     * @see #isFocusbble
     * @see #isDisplbybble
     * @see KeybobrdFocusMbnbger#clebrGlobblFocusOwner
     * @since 1.4
     */
    public boolebn requestFocusInWindow() {
        return requestFocusHelper(fblse, fblse);
    }

    boolebn requestFocusInWindow(CbusedFocusEvent.Cbuse cbuse) {
        return requestFocusHelper(fblse, fblse, cbuse);
    }

    /**
     * Requests thbt this <code>Component</code> get the input focus,
     * if this <code>Component</code>'s top-level bncestor is blrebdy
     * the focused <code>Window</code>.  This component must be
     * displbybble, focusbble, visible bnd bll of its bncestors (with
     * the exception of the top-level Window) must be visible for the
     * request to be grbnted. Every effort will be mbde to honor the
     * request; however, in some cbses it mby be impossible to do
     * so. Developers must never bssume thbt this component is the
     * focus owner until this component receives b FOCUS_GAINED event.
     * <p>
     * This method returns b boolebn vblue. If <code>fblse</code> is returned,
     * the request is <b>gubrbnteed to fbil</b>. If <code>true</code> is
     * returned, the request will succeed <b>unless</b> it is vetoed, or bn
     * extrbordinbry event, such bs disposbl of the component's peer, occurs
     * before the request cbn be grbnted by the nbtive windowing system. Agbin,
     * while b return vblue of <code>true</code> indicbtes thbt the request is
     * likely to succeed, developers must never bssume thbt this component is
     * the focus owner until this component receives b FOCUS_GAINED event.
     * <p>
     * This method cbnnot be used to set the focus owner to no component bt
     * bll. Use <code>KeybobrdFocusMbnbger.clebrGlobblFocusOwner</code>
     * instebd.
     * <p>
     * The focus behbvior of this method cbn be implemented uniformly bcross
     * plbtforms, bnd thus developers bre strongly encourbged to use this
     * method over <code>requestFocus</code> when possible. Code which relies
     * on <code>requestFocus</code> mby exhibit different focus behbvior on
     * different plbtforms.
     * <p>
     * Every effort will be mbde to ensure thbt <code>FocusEvent</code>s
     * generbted bs b
     * result of this request will hbve the specified temporbry vblue. However,
     * becbuse specifying bn brbitrbry temporbry stbte mby not be implementbble
     * on bll nbtive windowing systems, correct behbvior for this method cbn be
     * gubrbnteed only for lightweight components. This method is not intended
     * for generbl use, but exists instebd bs b hook for lightweight component
     * librbries, such bs Swing.
     *
     * <p>Note: Not bll focus trbnsfers result from invoking this method. As
     * such, b component mby receive focus without this or bny of the other
     * {@code requestFocus} methods of {@code Component} being invoked.
     *
     * @pbrbm temporbry true if the focus chbnge is temporbry,
     *        such bs when the window loses the focus; for
     *        more informbtion on temporbry focus chbnges see the
     *<b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
     * @return <code>fblse</code> if the focus chbnge request is gubrbnteed to
     *         fbil; <code>true</code> if it is likely to succeed
     * @see #requestFocus
     * @see jbvb.bwt.event.FocusEvent
     * @see #bddFocusListener
     * @see #isFocusbble
     * @see #isDisplbybble
     * @see KeybobrdFocusMbnbger#clebrGlobblFocusOwner
     * @since 1.4
     */
    protected boolebn requestFocusInWindow(boolebn temporbry) {
        return requestFocusHelper(temporbry, fblse);
    }

    boolebn requestFocusInWindow(boolebn temporbry, CbusedFocusEvent.Cbuse cbuse) {
        return requestFocusHelper(temporbry, fblse, cbuse);
    }

    finbl boolebn requestFocusHelper(boolebn temporbry,
                                     boolebn focusedWindowChbngeAllowed) {
        return requestFocusHelper(temporbry, focusedWindowChbngeAllowed, CbusedFocusEvent.Cbuse.UNKNOWN);
    }

    finbl boolebn requestFocusHelper(boolebn temporbry,
                                     boolebn focusedWindowChbngeAllowed,
                                     CbusedFocusEvent.Cbuse cbuse)
    {
        // 1) Check if the event being dispbtched is b system-generbted mouse event.
        AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if (currentEvent instbnceof MouseEvent &&
            SunToolkit.isSystemGenerbted(currentEvent))
        {
            // 2) Sbnity check: if the mouse event component source belongs to the sbme contbining window.
            Component source = ((MouseEvent)currentEvent).getComponent();
            if (source == null || source.getContbiningWindow() == getContbiningWindow()) {
                focusLog.finest("requesting focus by mouse event \"in window\"");

                // If both the conditions bre fulfilled the focus request should be strictly
                // bounded by the toplevel window. It's bssumed thbt the mouse event bctivbtes
                // the window (if it wbsn't bctive) bnd this mbkes it possible for b focus
                // request with b strong in-window requirement to chbnge focus in the bounds
                // of the toplevel. If, by bny mebns, due to bsynchronous nbture of the event
                // dispbtching mechbnism, the window hbppens to be nbtively inbctive by the time
                // this focus request is eventublly hbndled, it should not re-bctivbte the
                // toplevel. Otherwise the result mby not meet user expectbtions. See 6981400.
                focusedWindowChbngeAllowed = fblse;
            }
        }
        if (!isRequestFocusAccepted(temporbry, focusedWindowChbngeAllowed, cbuse)) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("requestFocus is not bccepted");
            }
            return fblse;
        }
        // Updbte most-recent mbp
        KeybobrdFocusMbnbger.setMostRecentFocusOwner(this);

        Component window = this;
        while ( (window != null) && !(window instbnceof Window)) {
            if (!window.isVisible()) {
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    focusLog.finest("component is recurively invisible");
                }
                return fblse;
            }
            window = window.pbrent;
        }

        ComponentPeer peer = this.peer;
        Component hebvyweight = (peer instbnceof LightweightPeer)
            ? getNbtiveContbiner() : this;
        if (hebvyweight == null || !hebvyweight.isVisible()) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Component is not b pbrt of visible hierbrchy");
            }
            return fblse;
        }
        peer = hebvyweight.peer;
        if (peer == null) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Peer is null");
            }
            return fblse;
        }

        // Focus this Component
        long time = 0;
        if (EventQueue.isDispbtchThrebd()) {
            time = Toolkit.getEventQueue().getMostRecentKeyEventTime();
        } else {
            // A focus request mbde from outside EDT should not be bssocibted with bny event
            // bnd so its time stbmp is simply set to the current time.
            time = System.currentTimeMillis();
        }

        boolebn success = peer.requestFocus
            (this, temporbry, focusedWindowChbngeAllowed, time, cbuse);
        if (!success) {
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger
                (bppContext).dequeueKeyEvents(time, this);
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Peer request fbiled");
            }
        } else {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Pbss for " + this);
            }
        }
        return success;
    }

    privbte boolebn isRequestFocusAccepted(boolebn temporbry,
                                           boolebn focusedWindowChbngeAllowed,
                                           CbusedFocusEvent.Cbuse cbuse)
    {
        if (!isFocusbble() || !isVisible()) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Not focusbble or not visible");
            }
            return fblse;
        }

        ComponentPeer peer = this.peer;
        if (peer == null) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("peer is null");
            }
            return fblse;
        }

        Window window = getContbiningWindow();
        if (window == null || !window.isFocusbbleWindow()) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("Component doesn't hbve toplevel");
            }
            return fblse;
        }

        // We hbve pbssed bll regulbr checks for focus request,
        // now let's cbll RequestFocusController bnd see whbt it sbys.
        Component focusOwner = KeybobrdFocusMbnbger.getMostRecentFocusOwner(window);
        if (focusOwner == null) {
            // sometimes most recent focus owner mby be null, but focus owner is not
            // e.g. we reset most recent focus owner if user removes focus owner
            focusOwner = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().getFocusOwner();
            if (focusOwner != null && focusOwner.getContbiningWindow() != window) {
                focusOwner = null;
            }
        }

        if (focusOwner == this || focusOwner == null) {
            // Controller is supposed to verify focus trbnsfers bnd for this it
            // should know both from bnd to components.  And it shouldn't verify
            // trbnsfers from when these components bre equbl.
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("focus owner is null or this");
            }
            return true;
        }

        if (CbusedFocusEvent.Cbuse.ACTIVATION == cbuse) {
            // we shouldn't cbll RequestFocusController in cbse we bre
            // in bctivbtion.  We do request focus on component which
            // hbs got temporbry focus lost bnd then on component which is
            // most recent focus owner.  But most recent focus owner cbn be
            // chbnged by requestFocsuXXX() cbll only, so this trbnsfer hbs
            // been blrebdy bpproved.
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                focusLog.finest("cbuse is bctivbtion");
            }
            return true;
        }

        boolebn ret = Component.requestFocusController.bcceptRequestFocus(focusOwner,
                                                                          this,
                                                                          temporbry,
                                                                          focusedWindowChbngeAllowed,
                                                                          cbuse);
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            focusLog.finest("RequestFocusController returns {0}", ret);
        }

        return ret;
    }

    privbte stbtic RequestFocusController requestFocusController = new DummyRequestFocusController();

    // Swing bccess this method through reflection to implement InputVerifier's functionblity.
    // Perhbps, we should mbke this method public (lbter ;)
    privbte stbtic clbss DummyRequestFocusController implements RequestFocusController {
        public boolebn bcceptRequestFocus(Component from, Component to,
                                          boolebn temporbry, boolebn focusedWindowChbngeAllowed,
                                          CbusedFocusEvent.Cbuse cbuse)
        {
            return true;
        }
    };

    synchronized stbtic void setRequestFocusController(RequestFocusController requestController)
    {
        if (requestController == null) {
            requestFocusController = new DummyRequestFocusController();
        } else {
            requestFocusController = requestController;
        }
    }

    /**
     * Returns the Contbiner which is the focus cycle root of this Component's
     * focus trbversbl cycle. Ebch focus trbversbl cycle hbs only b single
     * focus cycle root bnd ebch Component which is not b Contbiner belongs to
     * only b single focus trbversbl cycle. Contbiners which bre focus cycle
     * roots belong to two cycles: one rooted bt the Contbiner itself, bnd one
     * rooted bt the Contbiner's nebrest focus-cycle-root bncestor. For such
     * Contbiners, this method will return the Contbiner's nebrest focus-cycle-
     * root bncestor.
     *
     * @return this Component's nebrest focus-cycle-root bncestor
     * @see Contbiner#isFocusCycleRoot()
     * @since 1.4
     */
    public Contbiner getFocusCycleRootAncestor() {
        Contbiner rootAncestor = this.pbrent;
        while (rootAncestor != null && !rootAncestor.isFocusCycleRoot()) {
            rootAncestor = rootAncestor.pbrent;
        }
        return rootAncestor;
    }

    /**
     * Returns whether the specified Contbiner is the focus cycle root of this
     * Component's focus trbversbl cycle. Ebch focus trbversbl cycle hbs only
     * b single focus cycle root bnd ebch Component which is not b Contbiner
     * belongs to only b single focus trbversbl cycle.
     *
     * @pbrbm contbiner the Contbiner to be tested
     * @return <code>true</code> if the specified Contbiner is b focus-cycle-
     *         root of this Component; <code>fblse</code> otherwise
     * @see Contbiner#isFocusCycleRoot()
     * @since 1.4
     */
    public boolebn isFocusCycleRoot(Contbiner contbiner) {
        Contbiner rootAncestor = getFocusCycleRootAncestor();
        return (rootAncestor == contbiner);
    }

    Contbiner getTrbversblRoot() {
        return getFocusCycleRootAncestor();
    }

    /**
     * Trbnsfers the focus to the next component, bs though this Component were
     * the focus owner.
     * @see       #requestFocus()
     * @since     1.1
     */
    public void trbnsferFocus() {
        nextFocus();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by trbnsferFocus().
     */
    @Deprecbted
    public void nextFocus() {
        trbnsferFocus(fblse);
    }

    boolebn trbnsferFocus(boolebn clebrOnFbilure) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("clebrOnFbilure = " + clebrOnFbilure);
        }
        Component toFocus = getNextFocusCbndidbte();
        boolebn res = fblse;
        if (toFocus != null && !toFocus.isFocusOwner() && toFocus != this) {
            res = toFocus.requestFocusInWindow(CbusedFocusEvent.Cbuse.TRAVERSAL_FORWARD);
        }
        if (clebrOnFbilure && !res) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                focusLog.finer("clebr globbl focus owner");
            }
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().clebrGlobblFocusOwnerPriv();
        }
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("returning result: " + res);
        }
        return res;
    }

    finbl Component getNextFocusCbndidbte() {
        Contbiner rootAncestor = getTrbversblRoot();
        Component comp = this;
        while (rootAncestor != null &&
               !(rootAncestor.isShowing() && rootAncestor.cbnBeFocusOwner()))
        {
            comp = rootAncestor;
            rootAncestor = comp.getFocusCycleRootAncestor();
        }
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("comp = " + comp + ", root = " + rootAncestor);
        }
        Component cbndidbte = null;
        if (rootAncestor != null) {
            FocusTrbversblPolicy policy = rootAncestor.getFocusTrbversblPolicy();
            Component toFocus = policy.getComponentAfter(rootAncestor, comp);
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                focusLog.finer("component bfter is " + toFocus);
            }
            if (toFocus == null) {
                toFocus = policy.getDefbultComponent(rootAncestor);
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    focusLog.finer("defbult component is " + toFocus);
                }
            }
            if (toFocus == null) {
                Applet bpplet = EmbeddedFrbme.getAppletIfAncestorOf(this);
                if (bpplet != null) {
                    toFocus = bpplet;
                }
            }
            cbndidbte = toFocus;
        }
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Focus trbnsfer cbndidbte: " + cbndidbte);
        }
        return cbndidbte;
    }

    /**
     * Trbnsfers the focus to the previous component, bs though this Component
     * were the focus owner.
     * @see       #requestFocus()
     * @since     1.4
     */
    public void trbnsferFocusBbckwbrd() {
        trbnsferFocusBbckwbrd(fblse);
    }

    boolebn trbnsferFocusBbckwbrd(boolebn clebrOnFbilure) {
        Contbiner rootAncestor = getTrbversblRoot();
        Component comp = this;
        while (rootAncestor != null &&
               !(rootAncestor.isShowing() && rootAncestor.cbnBeFocusOwner()))
        {
            comp = rootAncestor;
            rootAncestor = comp.getFocusCycleRootAncestor();
        }
        boolebn res = fblse;
        if (rootAncestor != null) {
            FocusTrbversblPolicy policy = rootAncestor.getFocusTrbversblPolicy();
            Component toFocus = policy.getComponentBefore(rootAncestor, comp);
            if (toFocus == null) {
                toFocus = policy.getDefbultComponent(rootAncestor);
            }
            if (toFocus != null) {
                res = toFocus.requestFocusInWindow(CbusedFocusEvent.Cbuse.TRAVERSAL_BACKWARD);
            }
        }
        if (clebrOnFbilure && !res) {
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                focusLog.finer("clebr globbl focus owner");
            }
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().clebrGlobblFocusOwnerPriv();
        }
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("returning result: " + res);
        }
        return res;
    }

    /**
     * Trbnsfers the focus up one focus trbversbl cycle. Typicblly, the focus
     * owner is set to this Component's focus cycle root, bnd the current focus
     * cycle root is set to the new focus owner's focus cycle root. If,
     * however, this Component's focus cycle root is b Window, then the focus
     * owner is set to the focus cycle root's defbult Component to focus, bnd
     * the current focus cycle root is unchbnged.
     *
     * @see       #requestFocus()
     * @see       Contbiner#isFocusCycleRoot()
     * @see       Contbiner#setFocusCycleRoot(boolebn)
     * @since     1.4
     */
    public void trbnsferFocusUpCycle() {
        Contbiner rootAncestor;
        for (rootAncestor = getFocusCycleRootAncestor();
             rootAncestor != null && !(rootAncestor.isShowing() &&
                                       rootAncestor.isFocusbble() &&
                                       rootAncestor.isEnbbled());
             rootAncestor = rootAncestor.getFocusCycleRootAncestor()) {
        }

        if (rootAncestor != null) {
            Contbiner rootAncestorRootAncestor =
                rootAncestor.getFocusCycleRootAncestor();
            Contbiner fcr = (rootAncestorRootAncestor != null) ?
                rootAncestorRootAncestor : rootAncestor;

            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                setGlobblCurrentFocusCycleRootPriv(fcr);
            rootAncestor.requestFocus(CbusedFocusEvent.Cbuse.TRAVERSAL_UP);
        } else {
            Window window = getContbiningWindow();

            if (window != null) {
                Component toFocus = window.getFocusTrbversblPolicy().
                    getDefbultComponent(window);
                if (toFocus != null) {
                    KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                        setGlobblCurrentFocusCycleRootPriv(window);
                    toFocus.requestFocus(CbusedFocusEvent.Cbuse.TRAVERSAL_UP);
                }
            }
        }
    }

    /**
     * Returns <code>true</code> if this <code>Component</code> is the
     * focus owner.  This method is obsolete, bnd hbs been replbced by
     * <code>isFocusOwner()</code>.
     *
     * @return <code>true</code> if this <code>Component</code> is the
     *         focus owner; <code>fblse</code> otherwise
     * @since 1.2
     */
    public boolebn hbsFocus() {
        return (KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                getFocusOwner() == this);
    }

    /**
     * Returns <code>true</code> if this <code>Component</code> is the
     *    focus owner.
     *
     * @return <code>true</code> if this <code>Component</code> is the
     *     focus owner; <code>fblse</code> otherwise
     * @since 1.4
     */
    public boolebn isFocusOwner() {
        return hbsFocus();
    }

    /*
     * Used to disbllow buto-focus-trbnsfer on disposbl of the focus owner
     * in the process of disposing its pbrent contbiner.
     */
    privbte boolebn butoFocusTrbnsferOnDisposbl = true;

    void setAutoFocusTrbnsferOnDisposbl(boolebn vblue) {
        butoFocusTrbnsferOnDisposbl = vblue;
    }

    boolebn isAutoFocusTrbnsferOnDisposbl() {
        return butoFocusTrbnsferOnDisposbl;
    }

    /**
     * Adds the specified popup menu to the component.
     * @pbrbm     popup the popup menu to be bdded to the component.
     * @see       #remove(MenuComponent)
     * @exception NullPointerException if {@code popup} is {@code null}
     * @since     1.1
     */
    public void bdd(PopupMenu popup) {
        synchronized (getTreeLock()) {
            if (popup.pbrent != null) {
                popup.pbrent.remove(popup);
            }
            if (popups == null) {
                popups = new Vector<PopupMenu>();
            }
            popups.bddElement(popup);
            popup.pbrent = this;

            if (peer != null) {
                if (popup.peer == null) {
                    popup.bddNotify();
                }
            }
        }
    }

    /**
     * Removes the specified popup menu from the component.
     * @pbrbm     popup the popup menu to be removed
     * @see       #bdd(PopupMenu)
     * @since     1.1
     */
    @SuppressWbrnings("unchecked")
    public void remove(MenuComponent popup) {
        synchronized (getTreeLock()) {
            if (popups == null) {
                return;
            }
            int index = popups.indexOf(popup);
            if (index >= 0) {
                PopupMenu pmenu = (PopupMenu)popup;
                if (pmenu.peer != null) {
                    pmenu.removeNotify();
                }
                pmenu.pbrent = null;
                popups.removeElementAt(index);
                if (popups.size() == 0) {
                    popups = null;
                }
            }
        }
    }

    /**
     * Returns b string representing the stbte of this component. This
     * method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return  b string representbtion of this component's stbte
     * @since     1.0
     */
    protected String pbrbmString() {
        finbl String thisNbme = Objects.toString(getNbme(), "");
        finbl String invblid = isVblid() ? "" : ",invblid";
        finbl String hidden = visible ? "" : ",hidden";
        finbl String disbbled = enbbled ? "" : ",disbbled";
        return thisNbme + ',' + x + ',' + y + ',' + width + 'x' + height
                + invblid + hidden + disbbled;
    }

    /**
     * Returns b string representbtion of this component bnd its vblues.
     * @return    b string representbtion of this component
     * @since     1.0
     */
    public String toString() {
        return getClbss().getNbme() + '[' + pbrbmString() + ']';
    }

    /**
     * Prints b listing of this component to the stbndbrd system output
     * strebm <code>System.out</code>.
     * @see       jbvb.lbng.System#out
     * @since     1.0
     */
    public void list() {
        list(System.out, 0);
    }

    /**
     * Prints b listing of this component to the specified output
     * strebm.
     * @pbrbm    out   b print strebm
     * @throws   NullPointerException if {@code out} is {@code null}
     * @since    1.0
     */
    public void list(PrintStrebm out) {
        list(out, 0);
    }

    /**
     * Prints out b list, stbrting bt the specified indentbtion, to the
     * specified print strebm.
     * @pbrbm     out      b print strebm
     * @pbrbm     indent   number of spbces to indent
     * @see       jbvb.io.PrintStrebm#println(jbvb.lbng.Object)
     * @throws    NullPointerException if {@code out} is {@code null}
     * @since     1.0
     */
    public void list(PrintStrebm out, int indent) {
        for (int i = 0 ; i < indent ; i++) {
            out.print(" ");
        }
        out.println(this);
    }

    /**
     * Prints b listing to the specified print writer.
     * @pbrbm  out  the print writer to print to
     * @throws NullPointerException if {@code out} is {@code null}
     * @since 1.1
     */
    public void list(PrintWriter out) {
        list(out, 0);
    }

    /**
     * Prints out b list, stbrting bt the specified indentbtion, to
     * the specified print writer.
     * @pbrbm out the print writer to print to
     * @pbrbm indent the number of spbces to indent
     * @throws NullPointerException if {@code out} is {@code null}
     * @see       jbvb.io.PrintStrebm#println(jbvb.lbng.Object)
     * @since 1.1
     */
    public void list(PrintWriter out, int indent) {
        for (int i = 0 ; i < indent ; i++) {
            out.print(" ");
        }
        out.println(this);
    }

    /*
     * Fetches the nbtive contbiner somewhere higher up in the component
     * tree thbt contbins this component.
     */
    finbl Contbiner getNbtiveContbiner() {
        Contbiner p = getContbiner();
        while (p != null && p.peer instbnceof LightweightPeer) {
            p = p.getContbiner();
        }
        return p;
    }

    /**
     * Adds b PropertyChbngeListener to the listener list. The listener is
     * registered for bll bound properties of this clbss, including the
     * following:
     * <ul>
     *    <li>this Component's font ("font")</li>
     *    <li>this Component's bbckground color ("bbckground")</li>
     *    <li>this Component's foreground color ("foreground")</li>
     *    <li>this Component's focusbbility ("focusbble")</li>
     *    <li>this Component's focus trbversbl keys enbbled stbte
     *        ("focusTrbversblKeysEnbbled")</li>
     *    <li>this Component's Set of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFocusTrbversblKeys")</li>
     *    <li>this Component's Set of BACKWARD_TRAVERSAL_KEYS
     *        ("bbckwbrdFocusTrbversblKeys")</li>
     *    <li>this Component's Set of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCycleFocusTrbversblKeys")</li>
     *    <li>this Component's preferred size ("preferredSize")</li>
     *    <li>this Component's minimum size ("minimumSize")</li>
     *    <li>this Component's mbximum size ("mbximumSize")</li>
     *    <li>this Component's nbme ("nbme")</li>
     * </ul>
     * Note thbt if this <code>Component</code> is inheriting b bound property, then no
     * event will be fired in response to b chbnge in the inherited property.
     * <p>
     * If <code>listener</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     *
     * @pbrbm    listener  the property chbnge listener to be bdded
     *
     * @see #removePropertyChbngeListener
     * @see #getPropertyChbngeListeners
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     */
    public void bddPropertyChbngeListener(
                                                       PropertyChbngeListener listener) {
        synchronized (getObjectLock()) {
            if (listener == null) {
                return;
            }
            if (chbngeSupport == null) {
                chbngeSupport = new PropertyChbngeSupport(this);
            }
            chbngeSupport.bddPropertyChbngeListener(listener);
        }
    }

    /**
     * Removes b PropertyChbngeListener from the listener list. This method
     * should be used to remove PropertyChbngeListeners thbt were registered
     * for bll bound properties of this clbss.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm listener the PropertyChbngeListener to be removed
     *
     * @see #bddPropertyChbngeListener
     * @see #getPropertyChbngeListeners
     * @see #removePropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     */
    public void removePropertyChbngeListener(
                                                          PropertyChbngeListener listener) {
        synchronized (getObjectLock()) {
            if (listener == null || chbngeSupport == null) {
                return;
            }
            chbngeSupport.removePropertyChbngeListener(listener);
        }
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
    public PropertyChbngeListener[] getPropertyChbngeListeners() {
        synchronized (getObjectLock()) {
            if (chbngeSupport == null) {
                return new PropertyChbngeListener[0];
            }
            return chbngeSupport.getPropertyChbngeListeners();
        }
    }

    /**
     * Adds b PropertyChbngeListener to the listener list for b specific
     * property. The specified property mby be user-defined, or one of the
     * following:
     * <ul>
     *    <li>this Component's font ("font")</li>
     *    <li>this Component's bbckground color ("bbckground")</li>
     *    <li>this Component's foreground color ("foreground")</li>
     *    <li>this Component's focusbbility ("focusbble")</li>
     *    <li>this Component's focus trbversbl keys enbbled stbte
     *        ("focusTrbversblKeysEnbbled")</li>
     *    <li>this Component's Set of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFocusTrbversblKeys")</li>
     *    <li>this Component's Set of BACKWARD_TRAVERSAL_KEYS
     *        ("bbckwbrdFocusTrbversblKeys")</li>
     *    <li>this Component's Set of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCycleFocusTrbversblKeys")</li>
     * </ul>
     * Note thbt if this <code>Component</code> is inheriting b bound property, then no
     * event will be fired in response to b chbnge in the inherited property.
     * <p>
     * If <code>propertyNbme</code> or <code>listener</code> is <code>null</code>,
     * no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme one of the property nbmes listed bbove
     * @pbrbm listener the property chbnge listener to be bdded
     *
     * @see #removePropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     */
    public void bddPropertyChbngeListener(
                                                       String propertyNbme,
                                                       PropertyChbngeListener listener) {
        synchronized (getObjectLock()) {
            if (listener == null) {
                return;
            }
            if (chbngeSupport == null) {
                chbngeSupport = new PropertyChbngeSupport(this);
            }
            chbngeSupport.bddPropertyChbngeListener(propertyNbme, listener);
        }
    }

    /**
     * Removes b <code>PropertyChbngeListener</code> from the listener
     * list for b specific property. This method should be used to remove
     * <code>PropertyChbngeListener</code>s
     * thbt were registered for b specific bound property.
     * <p>
     * If <code>propertyNbme</code> or <code>listener</code> is <code>null</code>,
     * no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme b vblid property nbme
     * @pbrbm listener the PropertyChbngeListener to be removed
     *
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners(jbvb.lbng.String)
     * @see #removePropertyChbngeListener(jbvb.bebns.PropertyChbngeListener)
     */
    public void removePropertyChbngeListener(
                                                          String propertyNbme,
                                                          PropertyChbngeListener listener) {
        synchronized (getObjectLock()) {
            if (listener == null || chbngeSupport == null) {
                return;
            }
            chbngeSupport.removePropertyChbngeListener(propertyNbme, listener);
        }
    }

    /**
     * Returns bn brrby of bll the listeners which hbve been bssocibted
     * with the nbmed property.
     *
     * @pbrbm  propertyNbme the property nbme
     * @return bll of the <code>PropertyChbngeListener</code>s bssocibted with
     *         the nbmed property; if no such listeners hbve been bdded or
     *         if <code>propertyNbme</code> is <code>null</code>, bn empty
     *         brrby is returned
     *
     * @see #bddPropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #removePropertyChbngeListener(jbvb.lbng.String, jbvb.bebns.PropertyChbngeListener)
     * @see #getPropertyChbngeListeners
     * @since 1.4
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners(String propertyNbme) {
        synchronized (getObjectLock()) {
            if (chbngeSupport == null) {
                return new PropertyChbngeListener[0];
            }
            return chbngeSupport.getPropertyChbngeListeners(propertyNbme);
        }
    }

    /**
     * Support for reporting bound property chbnges for Object properties.
     * This method cbn be cblled when b bound property hbs chbnged bnd it will
     * send the bppropribte PropertyChbngeEvent to bny registered
     * PropertyChbngeListeners.
     *
     * @pbrbm propertyNbme the property whose vblue hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     */
    protected void firePropertyChbnge(String propertyNbme,
                                      Object oldVblue, Object newVblue) {
        PropertyChbngeSupport chbngeSupport;
        synchronized (getObjectLock()) {
            chbngeSupport = this.chbngeSupport;
        }
        if (chbngeSupport == null ||
            (oldVblue != null && newVblue != null && oldVblue.equbls(newVblue))) {
            return;
        }
        chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
    }

    /**
     * Support for reporting bound property chbnges for boolebn properties.
     * This method cbn be cblled when b bound property hbs chbnged bnd it will
     * send the bppropribte PropertyChbngeEvent to bny registered
     * PropertyChbngeListeners.
     *
     * @pbrbm propertyNbme the property whose vblue hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     * @since 1.4
     */
    protected void firePropertyChbnge(String propertyNbme,
                                      boolebn oldVblue, boolebn newVblue) {
        PropertyChbngeSupport chbngeSupport = this.chbngeSupport;
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
    }

    /**
     * Support for reporting bound property chbnges for integer properties.
     * This method cbn be cblled when b bound property hbs chbnged bnd it will
     * send the bppropribte PropertyChbngeEvent to bny registered
     * PropertyChbngeListeners.
     *
     * @pbrbm propertyNbme the property whose vblue hbs chbnged
     * @pbrbm oldVblue the property's previous vblue
     * @pbrbm newVblue the property's new vblue
     * @since 1.4
     */
    protected void firePropertyChbnge(String propertyNbme,
                                      int oldVblue, int newVblue) {
        PropertyChbngeSupport chbngeSupport = this.chbngeSupport;
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
    }

    /**
     * Reports b bound property chbnge.
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue the old vblue of the property (bs b byte)
     * @pbrbm newVblue the new vblue of the property (bs b byte)
     * @see #firePropertyChbnge(jbvb.lbng.String, jbvb.lbng.Object,
     *          jbvb.lbng.Object)
     * @since 1.5
     */
    public void firePropertyChbnge(String propertyNbme, byte oldVblue, byte newVblue) {
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        firePropertyChbnge(propertyNbme, Byte.vblueOf(oldVblue), Byte.vblueOf(newVblue));
    }

    /**
     * Reports b bound property chbnge.
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue the old vblue of the property (bs b chbr)
     * @pbrbm newVblue the new vblue of the property (bs b chbr)
     * @see #firePropertyChbnge(jbvb.lbng.String, jbvb.lbng.Object,
     *          jbvb.lbng.Object)
     * @since 1.5
     */
    public void firePropertyChbnge(String propertyNbme, chbr oldVblue, chbr newVblue) {
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        firePropertyChbnge(propertyNbme, Chbrbcter.vblueOf(oldVblue), Chbrbcter.vblueOf(newVblue));
    }

    /**
     * Reports b bound property chbnge.
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue the old vblue of the property (bs b short)
     * @pbrbm newVblue the old vblue of the property (bs b short)
     * @see #firePropertyChbnge(jbvb.lbng.String, jbvb.lbng.Object,
     *          jbvb.lbng.Object)
     * @since 1.5
     */
    public void firePropertyChbnge(String propertyNbme, short oldVblue, short newVblue) {
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        firePropertyChbnge(propertyNbme, Short.vblueOf(oldVblue), Short.vblueOf(newVblue));
    }


    /**
     * Reports b bound property chbnge.
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue the old vblue of the property (bs b long)
     * @pbrbm newVblue the new vblue of the property (bs b long)
     * @see #firePropertyChbnge(jbvb.lbng.String, jbvb.lbng.Object,
     *          jbvb.lbng.Object)
     * @since 1.5
     */
    public void firePropertyChbnge(String propertyNbme, long oldVblue, long newVblue) {
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        firePropertyChbnge(propertyNbme, Long.vblueOf(oldVblue), Long.vblueOf(newVblue));
    }

    /**
     * Reports b bound property chbnge.
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue the old vblue of the property (bs b flobt)
     * @pbrbm newVblue the new vblue of the property (bs b flobt)
     * @see #firePropertyChbnge(jbvb.lbng.String, jbvb.lbng.Object,
     *          jbvb.lbng.Object)
     * @since 1.5
     */
    public void firePropertyChbnge(String propertyNbme, flobt oldVblue, flobt newVblue) {
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        firePropertyChbnge(propertyNbme, Flobt.vblueOf(oldVblue), Flobt.vblueOf(newVblue));
    }

    /**
     * Reports b bound property chbnge.
     *
     * @pbrbm propertyNbme the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue the old vblue of the property (bs b double)
     * @pbrbm newVblue the new vblue of the property (bs b double)
     * @see #firePropertyChbnge(jbvb.lbng.String, jbvb.lbng.Object,
     *          jbvb.lbng.Object)
     * @since 1.5
     */
    public void firePropertyChbnge(String propertyNbme, double oldVblue, double newVblue) {
        if (chbngeSupport == null || oldVblue == newVblue) {
            return;
        }
        firePropertyChbnge(propertyNbme, Double.vblueOf(oldVblue), Double.vblueOf(newVblue));
    }


    // Seriblizbtion support.

    /**
     * Component Seriblized Dbtb Version.
     *
     * @seribl
     */
    privbte int componentSeriblizedDbtbVersion = 4;

    /**
     * This hbck is for Swing seriblizbtion. It will invoke
     * the Swing pbckbge privbte method <code>compWriteObjectNotify</code>.
     */
    privbte void doSwingSeriblizbtion() {
        Pbckbge swingPbckbge = Pbckbge.getPbckbge("jbvbx.swing");
        // For Swing seriblizbtion to correctly work Swing needs to
        // be notified before Component does it's seriblizbtion.  This
        // hbck bccomodbtes this.
        //
        // Swing clbsses MUST be lobded by the bootstrbp clbss lobder,
        // otherwise we don't consider them.
        for (Clbss<?> klbss = Component.this.getClbss(); klbss != null;
                   klbss = klbss.getSuperclbss()) {
            if (klbss.getPbckbge() == swingPbckbge &&
                      klbss.getClbssLobder() == null) {
                finbl Clbss<?> swingClbss = klbss;
                // Find the first override of the compWriteObjectNotify method
                Method[] methods = AccessController.doPrivileged(
                                                                 new PrivilegedAction<Method[]>() {
                                                                     public Method[] run() {
                                                                         return swingClbss.getDeclbredMethods();
                                                                     }
                                                                 });
                for (int counter = methods.length - 1; counter >= 0;
                     counter--) {
                    finbl Method method = methods[counter];
                    if (method.getNbme().equbls("compWriteObjectNotify")){
                        // We found it, use doPrivileged to mbke it bccessible
                        // to use.
                        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                                public Void run() {
                                    method.setAccessible(true);
                                    return null;
                                }
                            });
                        // Invoke the method
                        try {
                            method.invoke(this, (Object[]) null);
                        } cbtch (IllegblAccessException ibe) {
                        } cbtch (InvocbtionTbrgetException ite) {
                        }
                        // We're done, bbil.
                        return;
                    }
                }
            }
        }
    }

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b vbriety of seriblizbble listeners bs optionbl dbtb.
     * The non-seriblizbble listeners bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @seriblDbtb <code>null</code> terminbted sequence of
     *   0 or more pbirs; the pbir consists of b <code>String</code>
     *   bnd bn <code>Object</code>; the <code>String</code> indicbtes
     *   the type of object bnd is one of the following (bs of 1.4):
     *   <code>componentListenerK</code> indicbting bn
     *     <code>ComponentListener</code> object;
     *   <code>focusListenerK</code> indicbting bn
     *     <code>FocusListener</code> object;
     *   <code>keyListenerK</code> indicbting bn
     *     <code>KeyListener</code> object;
     *   <code>mouseListenerK</code> indicbting bn
     *     <code>MouseListener</code> object;
     *   <code>mouseMotionListenerK</code> indicbting bn
     *     <code>MouseMotionListener</code> object;
     *   <code>inputMethodListenerK</code> indicbting bn
     *     <code>InputMethodListener</code> object;
     *   <code>hierbrchyListenerK</code> indicbting bn
     *     <code>HierbrchyListener</code> object;
     *   <code>hierbrchyBoundsListenerK</code> indicbting bn
     *     <code>HierbrchyBoundsListener</code> object;
     *   <code>mouseWheelListenerK</code> indicbting bn
     *     <code>MouseWheelListener</code> object
     * @seriblDbtb bn optionbl <code>ComponentOrientbtion</code>
     *    (bfter <code>inputMethodListener</code>, bs of 1.2)
     *
     * @see AWTEventMulticbster#sbve(jbvb.io.ObjectOutputStrebm, jbvb.lbng.String, jbvb.util.EventListener)
     * @see #componentListenerK
     * @see #focusListenerK
     * @see #keyListenerK
     * @see #mouseListenerK
     * @see #mouseMotionListenerK
     * @see #inputMethodListenerK
     * @see #hierbrchyListenerK
     * @see #hierbrchyBoundsListenerK
     * @see #mouseWheelListenerK
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws IOException
    {
        doSwingSeriblizbtion();

        s.defbultWriteObject();

        AWTEventMulticbster.sbve(s, componentListenerK, componentListener);
        AWTEventMulticbster.sbve(s, focusListenerK, focusListener);
        AWTEventMulticbster.sbve(s, keyListenerK, keyListener);
        AWTEventMulticbster.sbve(s, mouseListenerK, mouseListener);
        AWTEventMulticbster.sbve(s, mouseMotionListenerK, mouseMotionListener);
        AWTEventMulticbster.sbve(s, inputMethodListenerK, inputMethodListener);

        s.writeObject(null);
        s.writeObject(componentOrientbtion);

        AWTEventMulticbster.sbve(s, hierbrchyListenerK, hierbrchyListener);
        AWTEventMulticbster.sbve(s, hierbrchyBoundsListenerK,
                                 hierbrchyBoundsListener);
        s.writeObject(null);

        AWTEventMulticbster.sbve(s, mouseWheelListenerK, mouseWheelListener);
        s.writeObject(null);

    }

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd if it isn't
     * <code>null</code> bdds b listener to receive b vbriety
     * of events fired by the component.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException
    {
        objectLock = new Object();

        bcc = AccessController.getContext();

        s.defbultRebdObject();

        bppContext = AppContext.getAppContext();
        coblescingEnbbled = checkCoblescing();
        if (componentSeriblizedDbtbVersion < 4) {
            // These fields bre non-trbnsient bnd rely on defbult
            // seriblizbtion. However, the defbult vblues bre insufficient,
            // so we need to set them explicitly for object dbtb strebms prior
            // to 1.4.
            focusbble = true;
            isFocusTrbversbbleOverridden = FOCUS_TRAVERSABLE_UNKNOWN;
            initiblizeFocusTrbversblKeys();
            focusTrbversblKeysEnbbled = true;
        }

        Object keyOrNull;
        while(null != (keyOrNull = s.rebdObject())) {
            String key = ((String)keyOrNull).intern();

            if (componentListenerK == key)
                bddComponentListener((ComponentListener)(s.rebdObject()));

            else if (focusListenerK == key)
                bddFocusListener((FocusListener)(s.rebdObject()));

            else if (keyListenerK == key)
                bddKeyListener((KeyListener)(s.rebdObject()));

            else if (mouseListenerK == key)
                bddMouseListener((MouseListener)(s.rebdObject()));

            else if (mouseMotionListenerK == key)
                bddMouseMotionListener((MouseMotionListener)(s.rebdObject()));

            else if (inputMethodListenerK == key)
                bddInputMethodListener((InputMethodListener)(s.rebdObject()));

            else // skip vblue for unrecognized key
                s.rebdObject();

        }

        // Rebd the component's orientbtion if it's present
        Object orient = null;

        try {
            orient = s.rebdObject();
        } cbtch (jbvb.io.OptionblDbtbException e) {
            // JDK 1.1 instbnces will not hbve this optionbl dbtb.
            // e.eof will be true to indicbte thbt there is no more
            // dbtb bvbilbble for this object.
            // If e.eof is not true, throw the exception bs it
            // might hbve been cbused by rebsons unrelbted to
            // componentOrientbtion.

            if (!e.eof)  {
                throw (e);
            }
        }

        if (orient != null) {
            componentOrientbtion = (ComponentOrientbtion)orient;
        } else {
            componentOrientbtion = ComponentOrientbtion.UNKNOWN;
        }

        try {
            while(null != (keyOrNull = s.rebdObject())) {
                String key = ((String)keyOrNull).intern();

                if (hierbrchyListenerK == key) {
                    bddHierbrchyListener((HierbrchyListener)(s.rebdObject()));
                }
                else if (hierbrchyBoundsListenerK == key) {
                    bddHierbrchyBoundsListener((HierbrchyBoundsListener)
                                               (s.rebdObject()));
                }
                else {
                    // skip vblue for unrecognized key
                    s.rebdObject();
                }
            }
        } cbtch (jbvb.io.OptionblDbtbException e) {
            // JDK 1.1/1.2 instbnces will not hbve this optionbl dbtb.
            // e.eof will be true to indicbte thbt there is no more
            // dbtb bvbilbble for this object.
            // If e.eof is not true, throw the exception bs it
            // might hbve been cbused by rebsons unrelbted to
            // hierbrchy bnd hierbrchyBounds listeners.

            if (!e.eof)  {
                throw (e);
            }
        }

        try {
            while (null != (keyOrNull = s.rebdObject())) {
                String key = ((String)keyOrNull).intern();

                if (mouseWheelListenerK == key) {
                    bddMouseWheelListener((MouseWheelListener)(s.rebdObject()));
                }
                else {
                    // skip vblue for unrecognized key
                    s.rebdObject();
                }
            }
        } cbtch (jbvb.io.OptionblDbtbException e) {
            // pre-1.3 instbnces will not hbve this optionbl dbtb.
            // e.eof will be true to indicbte thbt there is no more
            // dbtb bvbilbble for this object.
            // If e.eof is not true, throw the exception bs it
            // might hbve been cbused by rebsons unrelbted to
            // mouse wheel listeners

            if (!e.eof)  {
                throw (e);
            }
        }

        if (popups != null) {
            int npopups = popups.size();
            for (int i = 0 ; i < npopups ; i++) {
                PopupMenu popup = popups.elementAt(i);
                popup.pbrent = this;
            }
        }
    }

    /**
     * Sets the lbngubge-sensitive orientbtion thbt is to be used to order
     * the elements or text within this component.  Lbngubge-sensitive
     * <code>LbyoutMbnbger</code> bnd <code>Component</code>
     * subclbsses will use this property to
     * determine how to lby out bnd drbw components.
     * <p>
     * At construction time, b component's orientbtion is set to
     * <code>ComponentOrientbtion.UNKNOWN</code>,
     * indicbting thbt it hbs not been specified
     * explicitly.  The UNKNOWN orientbtion behbves the sbme bs
     * <code>ComponentOrientbtion.LEFT_TO_RIGHT</code>.
     * <p>
     * To set the orientbtion of b single component, use this method.
     * To set the orientbtion of bn entire component
     * hierbrchy, use
     * {@link #bpplyComponentOrientbtion bpplyComponentOrientbtion}.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     * @pbrbm  o the orientbtion to be set
     *
     * @see ComponentOrientbtion
     * @see #invblidbte
     *
     * @buthor Lburb Werner, IBM
     * @bebninfo
     *       bound: true
     */
    public void setComponentOrientbtion(ComponentOrientbtion o) {
        ComponentOrientbtion oldVblue = componentOrientbtion;
        componentOrientbtion = o;

        // This is b bound property, so report the chbnge to
        // bny registered listeners.  (Chebp if there bre none.)
        firePropertyChbnge("componentOrientbtion", oldVblue, o);

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Retrieves the lbngubge-sensitive orientbtion thbt is to be used to order
     * the elements or text within this component.  <code>LbyoutMbnbger</code>
     * bnd <code>Component</code>
     * subclbsses thbt wish to respect orientbtion should cbll this method to
     * get the component's orientbtion before performing lbyout or drbwing.
     *
     * @return the orientbtion to order the elements or text
     * @see ComponentOrientbtion
     *
     * @buthor Lburb Werner, IBM
     */
    public ComponentOrientbtion getComponentOrientbtion() {
        return componentOrientbtion;
    }

    /**
     * Sets the <code>ComponentOrientbtion</code> property of this component
     * bnd bll components contbined within it.
     * <p>
     * This method chbnges lbyout-relbted informbtion, bnd therefore,
     * invblidbtes the component hierbrchy.
     *
     *
     * @pbrbm orientbtion the new component orientbtion of this component bnd
     *        the components contbined within it.
     * @exception NullPointerException if <code>orientbtion</code> is null.
     * @see #setComponentOrientbtion
     * @see #getComponentOrientbtion
     * @see #invblidbte
     * @since 1.4
     */
    public void bpplyComponentOrientbtion(ComponentOrientbtion orientbtion) {
        if (orientbtion == null) {
            throw new NullPointerException();
        }
        setComponentOrientbtion(orientbtion);
    }

    finbl boolebn cbnBeFocusOwner() {
        // It is enbbled, visible, focusbble.
        if (isEnbbled() && isDisplbybble() && isVisible() && isFocusbble()) {
            return true;
        }
        return fblse;
    }

    /**
     * Checks thbt this component meets the prerequesites to be focus owner:
     * - it is enbbled, visible, focusbble
     * - it's pbrents bre bll enbbled bnd showing
     * - top-level window is focusbble
     * - if focus cycle root hbs DefbultFocusTrbversblPolicy then it blso checks thbt this policy bccepts
     * this component bs focus owner
     * @since 1.5
     */
    finbl boolebn cbnBeFocusOwnerRecursively() {
        // - it is enbbled, visible, focusbble
        if (!cbnBeFocusOwner()) {
            return fblse;
        }

        // - it's pbrents bre bll enbbled bnd showing
        synchronized(getTreeLock()) {
            if (pbrent != null) {
                return pbrent.cbnContbinFocusOwner(this);
            }
        }
        return true;
    }

    /**
     * Fix the locbtion of the HW component in b LW contbiner hierbrchy.
     */
    finbl void relocbteComponent() {
        synchronized (getTreeLock()) {
            if (peer == null) {
                return;
            }
            int nbtiveX = x;
            int nbtiveY = y;
            for (Component cont = getContbiner();
                    cont != null && cont.isLightweight();
                    cont = cont.getContbiner())
            {
                nbtiveX += cont.x;
                nbtiveY += cont.y;
            }
            peer.setBounds(nbtiveX, nbtiveY, width, height,
                    ComponentPeer.SET_LOCATION);
        }
    }

    /**
     * Returns the <code>Window</code> bncestor of the component.
     * @return Window bncestor of the component or component by itself if it is Window;
     *         null, if component is not b pbrt of window hierbrchy
     */
    Window getContbiningWindow() {
        return SunToolkit.getContbiningWindow(this);
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    /*
     * --- Accessibility Support ---
     *
     *  Component will contbin bll of the methods in interfbce Accessible,
     *  though it won't bctublly implement the interfbce - thbt will be up
     *  to the individubl objects which extend Component.
     */

    /**
     * The {@code AccessibleContext} bssocibted with this {@code Component}.
     */
    protected AccessibleContext bccessibleContext = null;

    /**
     * Gets the <code>AccessibleContext</code> bssocibted
     * with this <code>Component</code>.
     * The method implemented by this bbse
     * clbss returns null.  Clbsses thbt extend <code>Component</code>
     * should implement this method to return the
     * <code>AccessibleContext</code> bssocibted with the subclbss.
     *
     *
     * @return the <code>AccessibleContext</code> of this
     *    <code>Component</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        return bccessibleContext;
    }

    /**
     * Inner clbss of Component used to provide defbult support for
     * bccessibility.  This clbss is not mebnt to be used directly by
     * bpplicbtion developers, but is instebd mebnt only to be
     * subclbssed by component developers.
     * <p>
     * The clbss used to obtbin the bccessible role for this object.
     * @since 1.3
     */
    protected bbstrbct clbss AccessibleAWTComponent extends AccessibleContext
        implements Seriblizbble, AccessibleComponent {

        privbte stbtic finbl long seriblVersionUID = 642321655757800191L;

        /**
         * Though the clbss is bbstrbct, this should be cblled by
         * bll sub-clbsses.
         */
        protected AccessibleAWTComponent() {
        }

        /**
         * Number of PropertyChbngeListener objects registered. It's used
         * to bdd/remove ComponentListener bnd FocusListener to trbck
         * tbrget Component's stbte.
         */
        privbte volbtile trbnsient int propertyListenersCount = 0;

        /**
         * A component listener to trbck show/hide/resize events
         * bnd convert them to PropertyChbnge events.
         */
        protected ComponentListener bccessibleAWTComponentHbndler = null;

        /**
         * A listener to trbck focus events
         * bnd convert them to PropertyChbnge events.
         */
        protected FocusListener bccessibleAWTFocusHbndler = null;

        /**
         * Fire PropertyChbnge listener, if one is registered,
         * when shown/hidden..
         * @since 1.3
         */
        protected clbss AccessibleAWTComponentHbndler implements ComponentListener {
            public void componentHidden(ComponentEvent e)  {
                if (bccessibleContext != null) {
                    bccessibleContext.firePropertyChbnge(
                                                         AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                         AccessibleStbte.VISIBLE, null);
                }
            }

            public void componentShown(ComponentEvent e)  {
                if (bccessibleContext != null) {
                    bccessibleContext.firePropertyChbnge(
                                                         AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                         null, AccessibleStbte.VISIBLE);
                }
            }

            public void componentMoved(ComponentEvent e)  {
            }

            public void componentResized(ComponentEvent e)  {
            }
        } // inner clbss AccessibleAWTComponentHbndler


        /**
         * Fire PropertyChbnge listener, if one is registered,
         * when focus events hbppen
         * @since 1.3
         */
        protected clbss AccessibleAWTFocusHbndler implements FocusListener {
            public void focusGbined(FocusEvent event) {
                if (bccessibleContext != null) {
                    bccessibleContext.firePropertyChbnge(
                                                         AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                         null, AccessibleStbte.FOCUSED);
                }
            }
            public void focusLost(FocusEvent event) {
                if (bccessibleContext != null) {
                    bccessibleContext.firePropertyChbnge(
                                                         AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                         AccessibleStbte.FOCUSED, null);
                }
            }
        }  // inner clbss AccessibleAWTFocusHbndler


        /**
         * Adds b <code>PropertyChbngeListener</code> to the listener list.
         *
         * @pbrbm listener  the property chbnge listener to be bdded
         */
        public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
            if (bccessibleAWTComponentHbndler == null) {
                bccessibleAWTComponentHbndler = new AccessibleAWTComponentHbndler();
            }
            if (bccessibleAWTFocusHbndler == null) {
                bccessibleAWTFocusHbndler = new AccessibleAWTFocusHbndler();
            }
            if (propertyListenersCount++ == 0) {
                Component.this.bddComponentListener(bccessibleAWTComponentHbndler);
                Component.this.bddFocusListener(bccessibleAWTFocusHbndler);
            }
            super.bddPropertyChbngeListener(listener);
        }

        /**
         * Remove b PropertyChbngeListener from the listener list.
         * This removes b PropertyChbngeListener thbt wbs registered
         * for bll properties.
         *
         * @pbrbm listener  The PropertyChbngeListener to be removed
         */
        public void removePropertyChbngeListener(PropertyChbngeListener listener) {
            if (--propertyListenersCount == 0) {
                Component.this.removeComponentListener(bccessibleAWTComponentHbndler);
                Component.this.removeFocusListener(bccessibleAWTFocusHbndler);
            }
            super.removePropertyChbngeListener(listener);
        }

        // AccessibleContext methods
        //
        /**
         * Gets the bccessible nbme of this object.  This should blmost never
         * return <code>jbvb.bwt.Component.getNbme()</code>,
         * bs thbt generblly isn't b locblized nbme,
         * bnd doesn't hbve mebning for the user.  If the
         * object is fundbmentblly b text object (e.g. b menu item), the
         * bccessible nbme should be the text of the object (e.g. "sbve").
         * If the object hbs b tooltip, the tooltip text mby blso be bn
         * bppropribte String to return.
         *
         * @return the locblized nbme of the object -- cbn be
         *         <code>null</code> if this
         *         object does not hbve b nbme
         * @see jbvbx.bccessibility.AccessibleContext#setAccessibleNbme
         */
        public String getAccessibleNbme() {
            return bccessibleNbme;
        }

        /**
         * Gets the bccessible description of this object.  This should be
         * b concise, locblized description of whbt this object is - whbt
         * is its mebning to the user.  If the object hbs b tooltip, the
         * tooltip text mby be bn bppropribte string to return, bssuming
         * it contbins b concise description of the object (instebd of just
         * the nbme of the object - e.g. b "Sbve" icon on b toolbbr thbt
         * hbd "sbve" bs the tooltip text shouldn't return the tooltip
         * text bs the description, but something like "Sbves the current
         * text document" instebd).
         *
         * @return the locblized description of the object -- cbn be
         *        <code>null</code> if this object does not hbve b description
         * @see jbvbx.bccessibility.AccessibleContext#setAccessibleDescription
         */
        public String getAccessibleDescription() {
            return bccessibleDescription;
        }

        /**
         * Gets the role of this object.
         *
         * @return bn instbnce of <code>AccessibleRole</code>
         *      describing the role of the object
         * @see jbvbx.bccessibility.AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.AWT_COMPONENT;
        }

        /**
         * Gets the stbte of this object.
         *
         * @return bn instbnce of <code>AccessibleStbteSet</code>
         *       contbining the current stbte set of the object
         * @see jbvbx.bccessibility.AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            return Component.this.getAccessibleStbteSet();
        }

        /**
         * Gets the <code>Accessible</code> pbrent of this object.
         * If the pbrent of this object implements <code>Accessible</code>,
         * this method should simply return <code>getPbrent</code>.
         *
         * @return the <code>Accessible</code> pbrent of this
         *      object -- cbn be <code>null</code> if this
         *      object does not hbve bn <code>Accessible</code> pbrent
         */
        public Accessible getAccessiblePbrent() {
            if (bccessiblePbrent != null) {
                return bccessiblePbrent;
            } else {
                Contbiner pbrent = getPbrent();
                if (pbrent instbnceof Accessible) {
                    return (Accessible) pbrent;
                }
            }
            return null;
        }

        /**
         * Gets the index of this object in its bccessible pbrent.
         *
         * @return the index of this object in its pbrent; or -1 if this
         *    object does not hbve bn bccessible pbrent
         * @see #getAccessiblePbrent
         */
        public int getAccessibleIndexInPbrent() {
            return Component.this.getAccessibleIndexInPbrent();
        }

        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement <code>Accessible</code>,
         * then this method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object
         */
        public int getAccessibleChildrenCount() {
            return 0; // Components don't hbve children
        }

        /**
         * Returns the nth <code>Accessible</code> child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth <code>Accessible</code> child of the object
         */
        public Accessible getAccessibleChild(int i) {
            return null; // Components don't hbve children
        }

        /**
         * Returns the locble of this object.
         *
         * @return the locble of this object
         */
        public Locble getLocble() {
            return Component.this.getLocble();
        }

        /**
         * Gets the <code>AccessibleComponent</code> bssocibted
         * with this object if one exists.
         * Otherwise return <code>null</code>.
         *
         * @return the component
         */
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }


        // AccessibleComponent methods
        //
        /**
         * Gets the bbckground color of this object.
         *
         * @return the bbckground color, if supported, of the object;
         *      otherwise, <code>null</code>
         */
        public Color getBbckground() {
            return Component.this.getBbckground();
        }

        /**
         * Sets the bbckground color of this object.
         * (For trbnspbrency, see <code>isOpbque</code>.)
         *
         * @pbrbm c the new <code>Color</code> for the bbckground
         * @see Component#isOpbque
         */
        public void setBbckground(Color c) {
            Component.this.setBbckground(c);
        }

        /**
         * Gets the foreground color of this object.
         *
         * @return the foreground color, if supported, of the object;
         *     otherwise, <code>null</code>
         */
        public Color getForeground() {
            return Component.this.getForeground();
        }

        /**
         * Sets the foreground color of this object.
         *
         * @pbrbm c the new <code>Color</code> for the foreground
         */
        public void setForeground(Color c) {
            Component.this.setForeground(c);
        }

        /**
         * Gets the <code>Cursor</code> of this object.
         *
         * @return the <code>Cursor</code>, if supported,
         *     of the object; otherwise, <code>null</code>
         */
        public Cursor getCursor() {
            return Component.this.getCursor();
        }

        /**
         * Sets the <code>Cursor</code> of this object.
         * <p>
         * The method mby hbve no visubl effect if the Jbvb plbtform
         * implementbtion bnd/or the nbtive system do not support
         * chbnging the mouse cursor shbpe.
         * @pbrbm cursor the new <code>Cursor</code> for the object
         */
        public void setCursor(Cursor cursor) {
            Component.this.setCursor(cursor);
        }

        /**
         * Gets the <code>Font</code> of this object.
         *
         * @return the <code>Font</code>, if supported,
         *    for the object; otherwise, <code>null</code>
         */
        public Font getFont() {
            return Component.this.getFont();
        }

        /**
         * Sets the <code>Font</code> of this object.
         *
         * @pbrbm f the new <code>Font</code> for the object
         */
        public void setFont(Font f) {
            Component.this.setFont(f);
        }

        /**
         * Gets the <code>FontMetrics</code> of this object.
         *
         * @pbrbm f the <code>Font</code>
         * @return the <code>FontMetrics</code>, if supported,
         *     the object; otherwise, <code>null</code>
         * @see #getFont
         */
        public FontMetrics getFontMetrics(Font f) {
            if (f == null) {
                return null;
            } else {
                return Component.this.getFontMetrics(f);
            }
        }

        /**
         * Determines if the object is enbbled.
         *
         * @return true if object is enbbled; otherwise, fblse
         */
        public boolebn isEnbbled() {
            return Component.this.isEnbbled();
        }

        /**
         * Sets the enbbled stbte of the object.
         *
         * @pbrbm b if true, enbbles this object; otherwise, disbbles it
         */
        public void setEnbbled(boolebn b) {
            boolebn old = Component.this.isEnbbled();
            Component.this.setEnbbled(b);
            if (b != old) {
                if (bccessibleContext != null) {
                    if (b) {
                        bccessibleContext.firePropertyChbnge(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             null, AccessibleStbte.ENABLED);
                    } else {
                        bccessibleContext.firePropertyChbnge(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             AccessibleStbte.ENABLED, null);
                    }
                }
            }
        }

        /**
         * Determines if the object is visible.  Note: this mebns thbt the
         * object intends to be visible; however, it mby not in fbct be
         * showing on the screen becbuse one of the objects thbt this object
         * is contbined by is not visible.  To determine if bn object is
         * showing on the screen, use <code>isShowing</code>.
         *
         * @return true if object is visible; otherwise, fblse
         */
        public boolebn isVisible() {
            return Component.this.isVisible();
        }

        /**
         * Sets the visible stbte of the object.
         *
         * @pbrbm b if true, shows this object; otherwise, hides it
         */
        public void setVisible(boolebn b) {
            boolebn old = Component.this.isVisible();
            Component.this.setVisible(b);
            if (b != old) {
                if (bccessibleContext != null) {
                    if (b) {
                        bccessibleContext.firePropertyChbnge(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             null, AccessibleStbte.VISIBLE);
                    } else {
                        bccessibleContext.firePropertyChbnge(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             AccessibleStbte.VISIBLE, null);
                    }
                }
            }
        }

        /**
         * Determines if the object is showing.  This is determined by checking
         * the visibility of the object bnd bncestors of the object.  Note:
         * this will return true even if the object is obscured by bnother
         * (for exbmple, it hbppens to be undernebth b menu thbt wbs pulled
         * down).
         *
         * @return true if object is showing; otherwise, fblse
         */
        public boolebn isShowing() {
            return Component.this.isShowing();
        }

        /**
         * Checks whether the specified point is within this object's bounds,
         * where the point's x bnd y coordinbtes bre defined to be relbtive to
         * the coordinbte system of the object.
         *
         * @pbrbm p the <code>Point</code> relbtive to the
         *     coordinbte system of the object
         * @return true if object contbins <code>Point</code>; otherwise fblse
         */
        public boolebn contbins(Point p) {
            return Component.this.contbins(p);
        }

        /**
         * Returns the locbtion of the object on the screen.
         *
         * @return locbtion of object on screen -- cbn be
         *    <code>null</code> if this object is not on the screen
         */
        public Point getLocbtionOnScreen() {
            synchronized (Component.this.getTreeLock()) {
                if (Component.this.isShowing()) {
                    return Component.this.getLocbtionOnScreen();
                } else {
                    return null;
                }
            }
        }

        /**
         * Gets the locbtion of the object relbtive to the pbrent in the form
         * of b point specifying the object's top-left corner in the screen's
         * coordinbte spbce.
         *
         * @return bn instbnce of Point representing the top-left corner of
         * the object's bounds in the coordinbte spbce of the screen;
         * <code>null</code> if this object or its pbrent bre not on the screen
         */
        public Point getLocbtion() {
            return Component.this.getLocbtion();
        }

        /**
         * Sets the locbtion of the object relbtive to the pbrent.
         * @pbrbm p  the coordinbtes of the object
         */
        public void setLocbtion(Point p) {
            Component.this.setLocbtion(p);
        }

        /**
         * Gets the bounds of this object in the form of b Rectbngle object.
         * The bounds specify this object's width, height, bnd locbtion
         * relbtive to its pbrent.
         *
         * @return b rectbngle indicbting this component's bounds;
         *   <code>null</code> if this object is not on the screen
         */
        public Rectbngle getBounds() {
            return Component.this.getBounds();
        }

        /**
         * Sets the bounds of this object in the form of b
         * <code>Rectbngle</code> object.
         * The bounds specify this object's width, height, bnd locbtion
         * relbtive to its pbrent.
         *
         * @pbrbm r b rectbngle indicbting this component's bounds
         */
        public void setBounds(Rectbngle r) {
            Component.this.setBounds(r);
        }

        /**
         * Returns the size of this object in the form of b
         * <code>Dimension</code> object. The height field of the
         * <code>Dimension</code> object contbins this objects's
         * height, bnd the width field of the <code>Dimension</code>
         * object contbins this object's width.
         *
         * @return b <code>Dimension</code> object thbt indicbtes
         *     the size of this component; <code>null</code> if
         *     this object is not on the screen
         */
        public Dimension getSize() {
            return Component.this.getSize();
        }

        /**
         * Resizes this object so thbt it hbs width bnd height.
         *
         * @pbrbm d - the dimension specifying the new size of the object
         */
        public void setSize(Dimension d) {
            Component.this.setSize(d);
        }

        /**
         * Returns the <code>Accessible</code> child,
         * if one exists, contbined bt the locbl
         * coordinbte <code>Point</code>.  Otherwise returns
         * <code>null</code>.
         *
         * @pbrbm p the point defining the top-left corner of
         *      the <code>Accessible</code>, given in the
         *      coordinbte spbce of the object's pbrent
         * @return the <code>Accessible</code>, if it exists,
         *      bt the specified locbtion; else <code>null</code>
         */
        public Accessible getAccessibleAt(Point p) {
            return null; // Components don't hbve children
        }

        /**
         * Returns whether this object cbn bccept focus or not.
         *
         * @return true if object cbn bccept focus; otherwise fblse
         */
        public boolebn isFocusTrbversbble() {
            return Component.this.isFocusTrbversbble();
        }

        /**
         * Requests focus for this object.
         */
        public void requestFocus() {
            Component.this.requestFocus();
        }

        /**
         * Adds the specified focus listener to receive focus events from this
         * component.
         *
         * @pbrbm l the focus listener
         */
        public void bddFocusListener(FocusListener l) {
            Component.this.bddFocusListener(l);
        }

        /**
         * Removes the specified focus listener so it no longer receives focus
         * events from this component.
         *
         * @pbrbm l the focus listener
         */
        public void removeFocusListener(FocusListener l) {
            Component.this.removeFocusListener(l);
        }

    } // inner clbss AccessibleAWTComponent


    /**
     * Gets the index of this object in its bccessible pbrent.
     * If this object does not hbve bn bccessible pbrent, returns
     * -1.
     *
     * @return the index of this object in its bccessible pbrent
     */
    int getAccessibleIndexInPbrent() {
        synchronized (getTreeLock()) {
            int index = -1;
            Contbiner pbrent = this.getPbrent();
            if (pbrent != null && pbrent instbnceof Accessible) {
                Component cb[] = pbrent.getComponents();
                for (int i = 0; i < cb.length; i++) {
                    if (cb[i] instbnceof Accessible) {
                        index++;
                    }
                    if (this.equbls(cb[i])) {
                        return index;
                    }
                }
            }
            return -1;
        }
    }

    /**
     * Gets the current stbte set of this object.
     *
     * @return bn instbnce of <code>AccessibleStbteSet</code>
     *    contbining the current stbte set of the object
     * @see AccessibleStbte
     */
    AccessibleStbteSet getAccessibleStbteSet() {
        synchronized (getTreeLock()) {
            AccessibleStbteSet stbtes = new AccessibleStbteSet();
            if (this.isEnbbled()) {
                stbtes.bdd(AccessibleStbte.ENABLED);
            }
            if (this.isFocusTrbversbble()) {
                stbtes.bdd(AccessibleStbte.FOCUSABLE);
            }
            if (this.isVisible()) {
                stbtes.bdd(AccessibleStbte.VISIBLE);
            }
            if (this.isShowing()) {
                stbtes.bdd(AccessibleStbte.SHOWING);
            }
            if (this.isFocusOwner()) {
                stbtes.bdd(AccessibleStbte.FOCUSED);
            }
            if (this instbnceof Accessible) {
                AccessibleContext bc = ((Accessible) this).getAccessibleContext();
                if (bc != null) {
                    Accessible bp = bc.getAccessiblePbrent();
                    if (bp != null) {
                        AccessibleContext pbc = bp.getAccessibleContext();
                        if (pbc != null) {
                            AccessibleSelection bs = pbc.getAccessibleSelection();
                            if (bs != null) {
                                stbtes.bdd(AccessibleStbte.SELECTABLE);
                                int i = bc.getAccessibleIndexInPbrent();
                                if (i >= 0) {
                                    if (bs.isAccessibleChildSelected(i)) {
                                        stbtes.bdd(AccessibleStbte.SELECTED);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (Component.isInstbnceOf(this, "jbvbx.swing.JComponent")) {
                if (((jbvbx.swing.JComponent) this).isOpbque()) {
                    stbtes.bdd(AccessibleStbte.OPAQUE);
                }
            }
            return stbtes;
        }
    }

    /**
     * Checks thbt the given object is instbnce of the given clbss.
     * @pbrbm obj Object to be checked
     * @pbrbm clbssNbme The nbme of the clbss. Must be fully-qublified clbss nbme.
     * @return true, if this object is instbnceof given clbss,
     *         fblse, otherwise, or if obj or clbssNbme is null
     */
    stbtic boolebn isInstbnceOf(Object obj, String clbssNbme) {
        if (obj == null) return fblse;
        if (clbssNbme == null) return fblse;

        Clbss<?> cls = obj.getClbss();
        while (cls != null) {
            if (cls.getNbme().equbls(clbssNbme)) {
                return true;
            }
            cls = cls.getSuperclbss();
        }
        return fblse;
    }


    // ************************** MIXING CODE *******************************

    /**
     * Check whether we cbn trust the current bounds of the component.
     * The return vblue of fblse indicbtes thbt the contbiner of the
     * component is invblid, bnd therefore needs to be lbyed out, which would
     * probbbly mebn chbnging the bounds of its children.
     * Null-lbyout of the contbiner or bbsence of the contbiner mebn
     * the bounds of the component bre finbl bnd cbn be trusted.
     */
    finbl boolebn breBoundsVblid() {
        Contbiner cont = getContbiner();
        return cont == null || cont.isVblid() || cont.getLbyout() == null;
    }

    /**
     * Applies the shbpe to the component
     * @pbrbm shbpe Shbpe to be bpplied to the component
     */
    void bpplyCompoundShbpe(Region shbpe) {
        checkTreeLock();

        if (!breBoundsVblid()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this + "; breBoundsVblid = " + breBoundsVblid());
            }
            return;
        }

        if (!isLightweight()) {
            ComponentPeer peer = getPeer();
            if (peer != null) {
                // The Region clbss hbs some optimizbtions. Thbt's why
                // we should mbnublly check whether it's empty bnd
                // substitute the object ourselves. Otherwise we end up
                // with some incorrect Region object with loX being
                // grebter thbn the hiX for instbnce.
                if (shbpe.isEmpty()) {
                    shbpe = Region.EMPTY_REGION;
                }


                // Note: the shbpe is not reblly copied/cloned. We crebte
                // the Region object ourselves, so there's no bny possibility
                // to modify the object outside of the mixing code.
                // Nullifying compoundShbpe mebns thbt the component hbs normbl shbpe
                // (or hbs no shbpe bt bll).
                if (shbpe.equbls(getNormblShbpe())) {
                    if (this.compoundShbpe == null) {
                        return;
                    }
                    this.compoundShbpe = null;
                    peer.bpplyShbpe(null);
                } else {
                    if (shbpe.equbls(getAppliedShbpe())) {
                        return;
                    }
                    this.compoundShbpe = shbpe;
                    Point compAbsolute = getLocbtionOnWindow();
                    if (mixingLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        mixingLog.fine("this = " + this +
                                "; compAbsolute=" + compAbsolute + "; shbpe=" + shbpe);
                    }
                    peer.bpplyShbpe(shbpe.getTrbnslbtedRegion(-compAbsolute.x, -compAbsolute.y));
                }
            }
        }
    }

    /**
     * Returns the shbpe previously set with bpplyCompoundShbpe().
     * If the component is LW or no shbpe wbs bpplied yet,
     * the method returns the normbl shbpe.
     */
    privbte Region getAppliedShbpe() {
        checkTreeLock();
        //XXX: if we bllow LW components to hbve b shbpe, this must be chbnged
        return (this.compoundShbpe == null || isLightweight()) ? getNormblShbpe() : this.compoundShbpe;
    }

    Point getLocbtionOnWindow() {
        checkTreeLock();
        Point curLocbtion = getLocbtion();

        for (Contbiner pbrent = getContbiner();
                pbrent != null && !(pbrent instbnceof Window);
                pbrent = pbrent.getContbiner())
        {
            curLocbtion.x += pbrent.getX();
            curLocbtion.y += pbrent.getY();
        }

        return curLocbtion;
    }

    /**
     * Returns the full shbpe of the component locbted in window coordinbtes
     */
    finbl Region getNormblShbpe() {
        checkTreeLock();
        //XXX: we mby tbke into bccount b user-specified shbpe for this component
        Point compAbsolute = getLocbtionOnWindow();
        return
            Region.getInstbnceXYWH(
                    compAbsolute.x,
                    compAbsolute.y,
                    getWidth(),
                    getHeight()
            );
    }

    /**
     * Returns the "opbque shbpe" of the component.
     *
     * The opbque shbpe of b lightweight components is the bctubl shbpe thbt
     * needs to be cut off of the hebvyweight components in order to mix this
     * lightweight component correctly with them.
     *
     * The method is overriden in the jbvb.bwt.Contbiner to hbndle non-opbque
     * contbiners contbining opbque children.
     *
     * See 6637655 for detbils.
     */
    Region getOpbqueShbpe() {
        checkTreeLock();
        if (mixingCutoutRegion != null) {
            return mixingCutoutRegion;
        } else {
            return getNormblShbpe();
        }
    }

    finbl int getSiblingIndexAbove() {
        checkTreeLock();
        Contbiner pbrent = getContbiner();
        if (pbrent == null) {
            return -1;
        }

        int nextAbove = pbrent.getComponentZOrder(this) - 1;

        return nextAbove < 0 ? -1 : nextAbove;
    }

    finbl ComponentPeer getHWPeerAboveMe() {
        checkTreeLock();

        Contbiner cont = getContbiner();
        int indexAbove = getSiblingIndexAbove();

        while (cont != null) {
            for (int i = indexAbove; i > -1; i--) {
                Component comp = cont.getComponent(i);
                if (comp != null && comp.isDisplbybble() && !comp.isLightweight()) {
                    return comp.getPeer();
                }
            }
            // trbversing the hierbrchy up to the closest HW contbiner;
            // further trbversing mby return b component thbt is not bctublly
            // b nbtive sibling of this component bnd this kind of z-order
            // request mby not be bllowed by the underlying system (6852051).
            if (!cont.isLightweight()) {
                brebk;
            }

            indexAbove = cont.getSiblingIndexAbove();
            cont = cont.getContbiner();
        }

        return null;
    }

    finbl int getSiblingIndexBelow() {
        checkTreeLock();
        Contbiner pbrent = getContbiner();
        if (pbrent == null) {
            return -1;
        }

        int nextBelow = pbrent.getComponentZOrder(this) + 1;

        return nextBelow >= pbrent.getComponentCount() ? -1 : nextBelow;
    }

    finbl boolebn isNonOpbqueForMixing() {
        return mixingCutoutRegion != null &&
            mixingCutoutRegion.isEmpty();
    }

    privbte Region cblculbteCurrentShbpe() {
        checkTreeLock();
        Region s = getNormblShbpe();

        if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            mixingLog.fine("this = " + this + "; normblShbpe=" + s);
        }

        if (getContbiner() != null) {
            Component comp = this;
            Contbiner cont = comp.getContbiner();

            while (cont != null) {
                for (int index = comp.getSiblingIndexAbove(); index != -1; --index) {
                    /* It is bssumed thbt:
                     *
                     *    getComponent(getContbiner().getComponentZOrder(comp)) == comp
                     *
                     * The bssumption hbs been mbde bccording to the current
                     * implementbtion of the Contbiner clbss.
                     */
                    Component c = cont.getComponent(index);
                    if (c.isLightweight() && c.isShowing()) {
                        s = s.getDifference(c.getOpbqueShbpe());
                    }
                }

                if (cont.isLightweight()) {
                    s = s.getIntersection(cont.getNormblShbpe());
                } else {
                    brebk;
                }

                comp = cont;
                cont = cont.getContbiner();
            }
        }

        if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            mixingLog.fine("currentShbpe=" + s);
        }

        return s;
    }

    void bpplyCurrentShbpe() {
        checkTreeLock();
        if (!breBoundsVblid()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this + "; breBoundsVblid = " + breBoundsVblid());
            }
            return; // Becbuse bpplyCompoundShbpe() ignores such components bnywby
        }
        if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            mixingLog.fine("this = " + this);
        }
        bpplyCompoundShbpe(cblculbteCurrentShbpe());
    }

    finbl void subtrbctAndApplyShbpe(Region s) {
        checkTreeLock();

        if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            mixingLog.fine("this = " + this + "; s=" + s);
        }

        bpplyCompoundShbpe(getAppliedShbpe().getDifference(s));
    }

    privbte finbl void bpplyCurrentShbpeBelowMe() {
        checkTreeLock();
        Contbiner pbrent = getContbiner();
        if (pbrent != null && pbrent.isShowing()) {
            // First, rebpply shbpes of my siblings
            pbrent.recursiveApplyCurrentShbpe(getSiblingIndexBelow());

            // Second, if my contbiner is non-opbque, rebpply shbpes of siblings of my contbiner
            Contbiner pbrent2 = pbrent.getContbiner();
            while (!pbrent.isOpbque() && pbrent2 != null) {
                pbrent2.recursiveApplyCurrentShbpe(pbrent.getSiblingIndexBelow());

                pbrent = pbrent2;
                pbrent2 = pbrent.getContbiner();
            }
        }
    }

    finbl void subtrbctAndApplyShbpeBelowMe() {
        checkTreeLock();
        Contbiner pbrent = getContbiner();
        if (pbrent != null && isShowing()) {
            Region opbqueShbpe = getOpbqueShbpe();

            // First, cut my siblings
            pbrent.recursiveSubtrbctAndApplyShbpe(opbqueShbpe, getSiblingIndexBelow());

            // Second, if my contbiner is non-opbque, cut siblings of my contbiner
            Contbiner pbrent2 = pbrent.getContbiner();
            while (!pbrent.isOpbque() && pbrent2 != null) {
                pbrent2.recursiveSubtrbctAndApplyShbpe(opbqueShbpe, pbrent.getSiblingIndexBelow());

                pbrent = pbrent2;
                pbrent2 = pbrent.getContbiner();
            }
        }
    }

    void mixOnShowing() {
        synchronized (getTreeLock()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this);
            }
            if (!isMixingNeeded()) {
                return;
            }
            if (isLightweight()) {
                subtrbctAndApplyShbpeBelowMe();
            } else {
                bpplyCurrentShbpe();
            }
        }
    }

    void mixOnHiding(boolebn isLightweight) {
        // We cbnnot be sure thbt the peer exists bt this point, so we need the brgument
        //    to find out whether the hiding component is (well, bctublly wbs) b LW or b HW.
        synchronized (getTreeLock()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this + "; isLightweight = " + isLightweight);
            }
            if (!isMixingNeeded()) {
                return;
            }
            if (isLightweight) {
                bpplyCurrentShbpeBelowMe();
            }
        }
    }

    void mixOnReshbping() {
        synchronized (getTreeLock()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this);
            }
            if (!isMixingNeeded()) {
                return;
            }
            if (isLightweight()) {
                bpplyCurrentShbpeBelowMe();
            } else {
                bpplyCurrentShbpe();
            }
        }
    }

    void mixOnZOrderChbnging(int oldZorder, int newZorder) {
        synchronized (getTreeLock()) {
            boolebn becbmeHigher = newZorder < oldZorder;
            Contbiner pbrent = getContbiner();

            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this +
                    "; oldZorder=" + oldZorder + "; newZorder=" + newZorder + "; pbrent=" + pbrent);
            }
            if (!isMixingNeeded()) {
                return;
            }
            if (isLightweight()) {
                if (becbmeHigher) {
                    if (pbrent != null && isShowing()) {
                        pbrent.recursiveSubtrbctAndApplyShbpe(getOpbqueShbpe(), getSiblingIndexBelow(), oldZorder);
                    }
                } else {
                    if (pbrent != null) {
                        pbrent.recursiveApplyCurrentShbpe(oldZorder, newZorder);
                    }
                }
            } else {
                if (becbmeHigher) {
                    bpplyCurrentShbpe();
                } else {
                    if (pbrent != null) {
                        Region shbpe = getAppliedShbpe();

                        for (int index = oldZorder; index < newZorder; index++) {
                            Component c = pbrent.getComponent(index);
                            if (c.isLightweight() && c.isShowing()) {
                                shbpe = shbpe.getDifference(c.getOpbqueShbpe());
                            }
                        }
                        bpplyCompoundShbpe(shbpe);
                    }
                }
            }
        }
    }

    void mixOnVblidbting() {
        // This method gets overriden in the Contbiner. Obviously, b plbin
        // non-contbiner components don't need to hbndle vblidbtion.
    }

    finbl boolebn isMixingNeeded() {
        if (SunToolkit.getSunAwtDisbbleMixing()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                mixingLog.finest("this = " + this + "; Mixing disbbled vib sun.bwt.disbbleMixing");
            }
            return fblse;
        }
        if (!breBoundsVblid()) {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this + "; breBoundsVblid = " + breBoundsVblid());
            }
            return fblse;
        }
        Window window = getContbiningWindow();
        if (window != null) {
            if (!window.hbsHebvyweightDescendbnts() || !window.hbsLightweightDescendbnts() || window.isDisposing()) {
                if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    mixingLog.fine("contbining window = " + window +
                            "; hbs h/w descendbnts = " + window.hbsHebvyweightDescendbnts() +
                            "; hbs l/w descendbnts = " + window.hbsLightweightDescendbnts() +
                            "; disposing = " + window.isDisposing());
                }
                return fblse;
            }
        } else {
            if (mixingLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                mixingLog.fine("this = " + this + "; contbining window is null");
            }
            return fblse;
        }
        return true;
    }

    // ****************** END OF MIXING CODE ********************************

    // Note thbt the method is overriden in the Window clbss,
    // b window doesn't need to be updbted in the Z-order.
    void updbteZOrder() {
        peer.setZOrder(getHWPeerAboveMe());
    }

}
