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

import jbvb.bwt.event.*;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.im.InputContext;
import jbvb.bwt.imbge.BufferStrbtegy;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.WindowPeer;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.OptionblDbtbException;
import jbvb.io.Seriblizbble;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.AccessController;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.EventListener;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;
import jbvb.util.Set;
import jbvb.util.Vector;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvbx.bccessibility.*;
import sun.bwt.AWTAccessor;
import sun.bwt.AWTPermissions;
import sun.bwt.AppContext;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.SunToolkit;
import sun.bwt.util.IdentityArrbyList;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.pipe.Region;
import sun.security.bction.GetPropertyAction;
import sun.util.logging.PlbtformLogger;

/**
 * A {@code Window} object is b top-level window with no borders bnd no
 * menubbr.
 * The defbult lbyout for b window is {@code BorderLbyout}.
 * <p>
 * A window must hbve either b frbme, diblog, or bnother window defined bs its
 * owner when it's constructed.
 * <p>
 * In b multi-screen environment, you cbn crebte b {@code Window}
 * on b different screen device by constructing the {@code Window}
 * with {@link #Window(Window, GrbphicsConfigurbtion)}.  The
 * {@code GrbphicsConfigurbtion} object is one of the
 * {@code GrbphicsConfigurbtion} objects of the tbrget screen device.
 * <p>
 * In b virtubl device multi-screen environment in which the desktop
 * breb could spbn multiple physicbl screen devices, the bounds of bll
 * configurbtions bre relbtive to the virtubl device coordinbte system.
 * The origin of the virtubl-coordinbte system is bt the upper left-hbnd
 * corner of the primbry physicbl screen.  Depending on the locbtion of
 * the primbry screen in the virtubl device, negbtive coordinbtes bre
 * possible, bs shown in the following figure.
 * <p>
 * <img src="doc-files/MultiScreen.gif"
 * blt="Dibgrbm shows virtubl device contbining 4 physicbl screens. Primbry physicbl screen shows coords (0,0), other screen shows (-80,-100)."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * In such bn environment, when cblling {@code setLocbtion},
 * you must pbss b virtubl coordinbte to this method.  Similbrly,
 * cblling {@code getLocbtionOnScreen} on b {@code Window} returns
 * virtubl device coordinbtes.  Cbll the {@code getBounds} method
 * of b {@code GrbphicsConfigurbtion} to find its origin in the virtubl
 * coordinbte system.
 * <p>
 * The following code sets the locbtion of b {@code Window}
 * bt (10, 10) relbtive to the origin of the physicbl screen
 * of the corresponding {@code GrbphicsConfigurbtion}.  If the
 * bounds of the {@code GrbphicsConfigurbtion} is not tbken
 * into bccount, the {@code Window} locbtion would be set
 * bt (10, 10) relbtive to the virtubl-coordinbte system bnd would bppebr
 * on the primbry physicbl screen, which might be different from the
 * physicbl screen of the specified {@code GrbphicsConfigurbtion}.
 *
 * <pre>
 *      Window w = new Window(Window owner, GrbphicsConfigurbtion gc);
 *      Rectbngle bounds = gc.getBounds();
 *      w.setLocbtion(10 + bounds.x, 10 + bounds.y);
 * </pre>
 *
 * <p>
 * Note: the locbtion bnd size of top-level windows (including
 * {@code Window}s, {@code Frbme}s, bnd {@code Diblog}s)
 * bre under the control of the desktop's window mbnbgement system.
 * Cblls to {@code setLocbtion}, {@code setSize}, bnd
 * {@code setBounds} bre requests (not directives) which bre
 * forwbrded to the window mbnbgement system.  Every effort will be
 * mbde to honor such requests.  However, in some cbses the window
 * mbnbgement system mby ignore such requests, or modify the requested
 * geometry in order to plbce bnd size the {@code Window} in b wby
 * thbt more closely mbtches the desktop settings.
 * <p>
 * Due to the bsynchronous nbture of nbtive event hbndling, the results
 * returned by {@code getBounds}, {@code getLocbtion},
 * {@code getLocbtionOnScreen}, bnd {@code getSize} might not
 * reflect the bctubl geometry of the Window on screen until the lbst
 * request hbs been processed.  During the processing of subsequent
 * requests these vblues might chbnge bccordingly while the window
 * mbnbgement system fulfills the requests.
 * <p>
 * An bpplicbtion mby set the size bnd locbtion of bn invisible
 * {@code Window} brbitrbrily, but the window mbnbgement system mby
 * subsequently chbnge its size bnd/or locbtion when the
 * {@code Window} is mbde visible. One or more {@code ComponentEvent}s
 * will be generbted to indicbte the new geometry.
 * <p>
 * Windows bre cbpbble of generbting the following WindowEvents:
 * WindowOpened, WindowClosed, WindowGbinedFocus, WindowLostFocus.
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @see WindowEvent
 * @see #bddWindowListener
 * @see jbvb.bwt.BorderLbyout
 * @since       1.0
 */
public clbss Window extends Contbiner implements Accessible {

    /**
     * Enumerbtion of bvbilbble <i>window types</i>.
     *
     * A window type defines the generic visubl bppebrbnce bnd behbvior of b
     * top-level window. For exbmple, the type mby bffect the kind of
     * decorbtions of b decorbted {@code Frbme} or {@code Diblog} instbnce.
     * <p>
     * Some plbtforms mby not fully support b certbin window type. Depending on
     * the level of support, some properties of the window type mby be
     * disobeyed.
     *
     * @see   #getType
     * @see   #setType
     * @since 1.7
     */
    public stbtic enum Type {
        /**
         * Represents b <i>normbl</i> window.
         *
         * This is the defbult type for objects of the {@code Window} clbss or
         * its descendbnts. Use this type for regulbr top-level windows.
         */
        NORMAL,

        /**
         * Represents b <i>utility</i> window.
         *
         * A utility window is usublly b smbll window such bs b toolbbr or b
         * pblette. The nbtive system mby render the window with smbller
         * title-bbr if the window is either b {@code Frbme} or b {@code
         * Diblog} object, bnd if it hbs its decorbtions enbbled.
         */
        UTILITY,

        /**
         * Represents b <i>popup</i> window.
         *
         * A popup window is b temporbry window such bs b drop-down menu or b
         * tooltip. On some plbtforms, windows of thbt type mby be forcibly
         * mbde undecorbted even if they bre instbnces of the {@code Frbme} or
         * {@code Diblog} clbss, bnd hbve decorbtions enbbled.
         */
        POPUP
    }

    /**
     * This represents the wbrning messbge thbt is
     * to be displbyed in b non secure window. ie :
     * b window thbt hbs b security mbnbger instblled thbt denies
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}.
     * This messbge cbn be displbyed bnywhere in the window.
     *
     * @seribl
     * @see #getWbrningString
     */
    String      wbrningString;

    /**
     * {@code icons} is the grbphicbl wby we cbn
     * represent the frbmes bnd diblogs.
     * {@code Window} cbn't displby icon but it's
     * being inherited by owned {@code Diblog}s.
     *
     * @seribl
     * @see #getIconImbges
     * @see #setIconImbges
     */
    trbnsient jbvb.util.List<Imbge> icons;

    /**
     * Holds the reference to the component which lbst hbd focus in this window
     * before it lost focus.
     */
    privbte trbnsient Component temporbryLostComponent;

    stbtic boolebn systemSyncLWRequests = fblse;
    boolebn     syncLWRequests = fblse;
    trbnsient boolebn beforeFirstShow = true;
    privbte trbnsient boolebn disposing = fblse;
    trbnsient WindowDisposerRecord disposerRecord = null;

    stbtic finbl int OPENED = 0x01;

    /**
     * An Integer vblue representing the Window Stbte.
     *
     * @seribl
     * @since 1.2
     * @see #show
     */
    int stbte;

    /**
     * A boolebn vblue representing Window blwbys-on-top stbte
     * @since 1.5
     * @seribl
     * @see #setAlwbysOnTop
     * @see #isAlwbysOnTop
     */
    privbte boolebn blwbysOnTop;

    /**
     * Contbins bll the windows thbt hbve b peer object bssocibted,
     * i. e. between bddNotify() bnd removeNotify() cblls. The list
     * of bll Window instbnces cbn be obtbined from AppContext object.
     *
     * @since 1.6
     */
    privbte stbtic finbl IdentityArrbyList<Window> bllWindows = new IdentityArrbyList<Window>();

    /**
     * A vector contbining bll the windows this
     * window currently owns.
     * @since 1.2
     * @see #getOwnedWindows
     */
    trbnsient Vector<WebkReference<Window>> ownedWindowList =
                                            new Vector<WebkReference<Window>>();

    /*
     * We insert b webk reference into the Vector of bll Windows in AppContext
     * instebd of 'this' so thbt gbrbbge collection cbn still tbke plbce
     * correctly.
     */
    privbte trbnsient WebkReference<Window> webkThis;

    trbnsient boolebn showWithPbrent;

    /**
     * Contbins the modbl diblog thbt blocks this window, or null
     * if the window is unblocked.
     *
     * @since 1.6
     */
    trbnsient Diblog modblBlocker;

    /**
     * @seribl
     *
     * @see jbvb.bwt.Diblog.ModblExclusionType
     * @see #getModblExclusionType
     * @see #setModblExclusionType
     *
     * @since 1.6
     */
    Diblog.ModblExclusionType modblExclusionType;

    trbnsient WindowListener windowListener;
    trbnsient WindowStbteListener windowStbteListener;
    trbnsient WindowFocusListener windowFocusListener;

    trbnsient InputContext inputContext;
    privbte trbnsient Object inputContextLock = new Object();

    /**
     * Unused. Mbintbined for seriblizbtion bbckwbrd-compbtibility.
     *
     * @seribl
     * @since 1.2
     */
    privbte FocusMbnbger focusMgr;

    /**
     * Indicbtes whether this Window cbn become the focused Window.
     *
     * @seribl
     * @see #getFocusbbleWindowStbte
     * @see #setFocusbbleWindowStbte
     * @since 1.4
     */
    privbte boolebn focusbbleWindowStbte = true;

    /**
     * Indicbtes whether this window should receive focus on
     * subsequently being shown (with b cbll to {@code setVisible(true)}), or
     * being moved to the front (with b cbll to {@code toFront()}).
     *
     * @seribl
     * @see #setAutoRequestFocus
     * @see #isAutoRequestFocus
     * @since 1.7
     */
    privbte volbtile boolebn butoRequestFocus = true;

    /*
     * Indicbtes thbt this window is being shown. This flbg is set to true bt
     * the beginning of show() bnd to fblse bt the end of show().
     *
     * @see #show()
     * @see Diblog#shouldBlock
     */
    trbnsient boolebn isInShow = fblse;

    /**
     * The opbcity level of the window
     *
     * @seribl
     * @see #setOpbcity(flobt)
     * @see #getOpbcity()
     * @since 1.7
     */
    privbte flobt opbcity = 1.0f;

    /**
     * The shbpe bssigned to this window. This field is set to {@code null} if
     * no shbpe is set (rectbngulbr window).
     *
     * @seribl
     * @see #getShbpe()
     * @see #setShbpe(Shbpe)
     * @since 1.7
     */
    privbte Shbpe shbpe = null;

    privbte stbtic finbl String bbse = "win";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 4497834738069338734L;

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("jbvb.bwt.Window");

    privbte stbtic finbl boolebn locbtionByPlbtformProp;

    trbnsient boolebn isTrbyIconWindow = fblse;

    /**
     * These fields bre initiblized in the nbtive peer code
     * or vib AWTAccessor's WindowAccessor.
     */
    privbte trbnsient volbtile int securityWbrningWidth = 0;
    privbte trbnsient volbtile int securityWbrningHeight = 0;

    /**
     * These fields represent the desired locbtion for the security
     * wbrning if this window is untrusted.
     * See com.sun.bwt.SecurityWbrning for more detbils.
     */
    privbte trbnsient double securityWbrningPointX = 2.0;
    privbte trbnsient double securityWbrningPointY = 0.0;
    privbte trbnsient flobt securityWbrningAlignmentX = RIGHT_ALIGNMENT;
    privbte trbnsient flobt securityWbrningAlignmentY = TOP_ALIGNMENT;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        String s = jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("jbvb.bwt.syncLWRequests"));
        systemSyncLWRequests = (s != null && s.equbls("true"));
        s = jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("jbvb.bwt.Window.locbtionByPlbtform"));
        locbtionByPlbtformProp = (s != null && s.equbls("true"));
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
       bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * Constructs b new, initiblly invisible window in defbult size with the
     * specified {@code GrbphicsConfigurbtion}.
     * <p>
     * If there is b security mbnbger, then it is invoked to check
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}
     * to determine whether or not the window must be displbyed with
     * b wbrning bbnner.
     *
     * @pbrbm gc the {@code GrbphicsConfigurbtion} of the tbrget screen
     *     device. If {@code gc} is {@code null}, the system defbult
     *     {@code GrbphicsConfigurbtion} is bssumed
     * @exception IllegblArgumentException if {@code gc}
     *    is not from b screen device
     * @exception HebdlessException when
     *     {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    Window(GrbphicsConfigurbtion gc) {
        init(gc);
    }

    trbnsient Object bnchor = new Object();
    stbtic clbss WindowDisposerRecord implements sun.jbvb2d.DisposerRecord {
        WebkReference<Window> owner;
        finbl WebkReference<Window> webkThis;
        finbl WebkReference<AppContext> context;

        WindowDisposerRecord(AppContext context, Window victim) {
            webkThis = victim.webkThis;
            this.context = new WebkReference<AppContext>(context);
        }

        public void updbteOwner() {
            Window victim = webkThis.get();
            owner = (victim == null)
                    ? null
                    : new WebkReference<Window>(victim.getOwner());
        }

        public void dispose() {
            if (owner != null) {
                Window pbrent = owner.get();
                if (pbrent != null) {
                    pbrent.removeOwnedWindow(webkThis);
                }
            }
            AppContext bc = context.get();
            if (null != bc) {
                Window.removeFromWindowList(bc, webkThis);
            }
        }
    }

    privbte GrbphicsConfigurbtion initGC(GrbphicsConfigurbtion gc) {
        GrbphicsEnvironment.checkHebdless();

        if (gc == null) {
            gc = GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                getDefbultScreenDevice().getDefbultConfigurbtion();
        }
        setGrbphicsConfigurbtion(gc);

        return gc;
    }

    privbte void init(GrbphicsConfigurbtion gc) {
        GrbphicsEnvironment.checkHebdless();

        syncLWRequests = systemSyncLWRequests;

        webkThis = new WebkReference<Window>(this);
        bddToWindowList();

        setWbrningString();
        this.cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        this.visible = fblse;

        gc = initGC(gc);

        if (gc.getDevice().getType() !=
            GrbphicsDevice.TYPE_RASTER_SCREEN) {
            throw new IllegblArgumentException("not b screen device");
        }
        setLbyout(new BorderLbyout());

        /* offset the initibl locbtion with the originbl of the screen */
        /* bnd bny insets                                              */
        Rectbngle screenBounds = gc.getBounds();
        Insets screenInsets = getToolkit().getScreenInsets(gc);
        int x = getX() + screenBounds.x + screenInsets.left;
        int y = getY() + screenBounds.y + screenInsets.top;
        if (x != this.x || y != this.y) {
            setLocbtion(x, y);
            /* reset bfter setLocbtion */
            setLocbtionByPlbtform(locbtionByPlbtformProp);
        }

        modblExclusionType = Diblog.ModblExclusionType.NO_EXCLUDE;
        disposerRecord = new WindowDisposerRecord(bppContext, this);
        sun.jbvb2d.Disposer.bddRecord(bnchor, disposerRecord);

        SunToolkit.checkAndSetPolicy(this);
    }

    /**
     * Constructs b new, initiblly invisible window in the defbult size.
     * <p>
     * If there is b security mbnbger set, it is invoked to check
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}.
     * If thbt check fbils with b {@code SecurityException} then b wbrning
     * bbnner is crebted.
     *
     * @exception HebdlessException when
     *     {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    Window() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        init((GrbphicsConfigurbtion)null);
    }

    /**
     * Constructs b new, initiblly invisible window with the specified
     * {@code Frbme} bs its owner. The window will not be focusbble
     * unless its owner is showing on the screen.
     * <p>
     * If there is b security mbnbger set, it is invoked to check
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}.
     * If thbt check fbils with b {@code SecurityException} then b wbrning
     * bbnner is crebted.
     *
     * @pbrbm owner the {@code Frbme} to bct bs owner or {@code null}
     *    if this window hbs no owner
     * @exception IllegblArgumentException if the {@code owner}'s
     *    {@code GrbphicsConfigurbtion} is not from b screen device
     * @exception HebdlessException when
     *    {@code GrbphicsEnvironment.isHebdless} returns {@code true}
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #isShowing
     */
    public Window(Frbme owner) {
        this(owner == null ? (GrbphicsConfigurbtion)null :
            owner.getGrbphicsConfigurbtion());
        ownedInit(owner);
    }

    /**
     * Constructs b new, initiblly invisible window with the specified
     * {@code Window} bs its owner. This window will not be focusbble
     * unless its nebrest owning {@code Frbme} or {@code Diblog}
     * is showing on the screen.
     * <p>
     * If there is b security mbnbger set, it is invoked to check
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}.
     * If thbt check fbils with b {@code SecurityException} then b
     * wbrning bbnner is crebted.
     *
     * @pbrbm owner the {@code Window} to bct bs owner or
     *     {@code null} if this window hbs no owner
     * @exception IllegblArgumentException if the {@code owner}'s
     *     {@code GrbphicsConfigurbtion} is not from b screen device
     * @exception HebdlessException when
     *     {@code GrbphicsEnvironment.isHebdless()} returns
     *     {@code true}
     *
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       #isShowing
     *
     * @since     1.2
     */
    public Window(Window owner) {
        this(owner == null ? (GrbphicsConfigurbtion)null :
            owner.getGrbphicsConfigurbtion());
        ownedInit(owner);
    }

    /**
     * Constructs b new, initiblly invisible window with the specified owner
     * {@code Window} bnd b {@code GrbphicsConfigurbtion}
     * of b screen device. The Window will not be focusbble unless
     * its nebrest owning {@code Frbme} or {@code Diblog}
     * is showing on the screen.
     * <p>
     * If there is b security mbnbger set, it is invoked to check
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}. If thbt
     * check fbils with b {@code SecurityException} then b wbrning bbnner
     * is crebted.
     *
     * @pbrbm owner the window to bct bs owner or {@code null}
     *     if this window hbs no owner
     * @pbrbm gc the {@code GrbphicsConfigurbtion} of the tbrget
     *     screen device; if {@code gc} is {@code null},
     *     the system defbult {@code GrbphicsConfigurbtion} is bssumed
     * @exception IllegblArgumentException if {@code gc}
     *     is not from b screen device
     * @exception HebdlessException when
     *     {@code GrbphicsEnvironment.isHebdless()} returns
     *     {@code true}
     *
     * @see       jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see       GrbphicsConfigurbtion#getBounds
     * @see       #isShowing
     * @since     1.3
     */
    public Window(Window owner, GrbphicsConfigurbtion gc) {
        this(gc);
        ownedInit(owner);
    }

    privbte void ownedInit(Window owner) {
        this.pbrent = owner;
        if (owner != null) {
            owner.bddOwnedWindow(webkThis);
            if (owner.isAlwbysOnTop()) {
                try {
                    setAlwbysOnTop(true);
                } cbtch (SecurityException ignore) {
                }
            }
        }

        // WindowDisposerRecord requires b proper vblue of pbrent field.
        disposerRecord.updbteOwner();
    }

    /**
     * Construct b nbme for this component.  Cblled by getNbme() when the
     * nbme is null.
     */
    String constructComponentNbme() {
        synchronized (Window.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Returns the sequence of imbges to be displbyed bs the icon for this window.
     * <p>
     * This method returns b copy of the internblly stored list, so bll operbtions
     * on the returned object will not bffect the window's behbvior.
     *
     * @return    the copy of icon imbges' list for this window, or
     *            empty list if this window doesn't hbve icon imbges.
     * @see       #setIconImbges
     * @see       #setIconImbge(Imbge)
     * @since     1.6
     */
    public jbvb.util.List<Imbge> getIconImbges() {
        jbvb.util.List<Imbge> icons = this.icons;
        if (icons == null || icons.size() == 0) {
            return new ArrbyList<Imbge>();
        }
        return new ArrbyList<Imbge>(icons);
    }

    /**
     * Sets the sequence of imbges to be displbyed bs the icon
     * for this window. Subsequent cblls to {@code getIconImbges} will
     * blwbys return b copy of the {@code icons} list.
     * <p>
     * Depending on the plbtform cbpbbilities one or severbl imbges
     * of different dimensions will be used bs the window's icon.
     * <p>
     * The {@code icons} list is scbnned for the imbges of most
     * bppropribte dimensions from the beginning. If the list contbins
     * severbl imbges of the sbme size, the first will be used.
     * <p>
     * Ownerless windows with no icon specified use plbtfrom-defbult icon.
     * The icon of bn owned window mby be inherited from the owner
     * unless explicitly overridden.
     * Setting the icon to {@code null} or empty list restores
     * the defbult behbvior.
     * <p>
     * Note : Nbtive windowing systems mby use different imbges of differing
     * dimensions to represent b window, depending on the context (e.g.
     * window decorbtion, window list, tbskbbr, etc.). They could blso use
     * just b single imbge for bll contexts or no imbge bt bll.
     *
     * @pbrbm     icons the list of icon imbges to be displbyed.
     * @see       #getIconImbges()
     * @see       #setIconImbge(Imbge)
     * @since     1.6
     */
    public synchronized void setIconImbges(jbvb.util.List<? extends Imbge> icons) {
        this.icons = (icons == null) ? new ArrbyList<Imbge>() :
            new ArrbyList<Imbge>(icons);
        WindowPeer peer = (WindowPeer)this.peer;
        if (peer != null) {
            peer.updbteIconImbges();
        }
        // Alwbys send b property chbnge event
        firePropertyChbnge("iconImbge", null, null);
    }

    /**
     * Sets the imbge to be displbyed bs the icon for this window.
     * <p>
     * This method cbn be used instebd of {@link #setIconImbges setIconImbges()}
     * to specify b single imbge bs b window's icon.
     * <p>
     * The following stbtement:
     * <pre>
     *     setIconImbge(imbge);
     * </pre>
     * is equivblent to:
     * <pre>
     *     ArrbyList&lt;Imbge&gt; imbgeList = new ArrbyList&lt;Imbge&gt;();
     *     imbgeList.bdd(imbge);
     *     setIconImbges(imbgeList);
     * </pre>
     * <p>
     * Note : Nbtive windowing systems mby use different imbges of differing
     * dimensions to represent b window, depending on the context (e.g.
     * window decorbtion, window list, tbskbbr, etc.). They could blso use
     * just b single imbge for bll contexts or no imbge bt bll.
     *
     * @pbrbm     imbge the icon imbge to be displbyed.
     * @see       #setIconImbges
     * @see       #getIconImbges()
     * @since     1.6
     */
    public void setIconImbge(Imbge imbge) {
        ArrbyList<Imbge> imbgeList = new ArrbyList<Imbge>();
        if (imbge != null) {
            imbgeList.bdd(imbge);
        }
        setIconImbges(imbgeList);
    }

    /**
     * Mbkes this Window displbybble by crebting the connection to its
     * nbtive screen resource.
     * This method is cblled internblly by the toolkit bnd should
     * not be cblled directly by progrbms.
     * @see Component#isDisplbybble
     * @see Contbiner#removeNotify
     * @since 1.0
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            Contbiner pbrent = this.pbrent;
            if (pbrent != null && pbrent.getPeer() == null) {
                pbrent.bddNotify();
            }
            if (peer == null) {
                peer = getToolkit().crebteWindow(this);
            }
            synchronized (bllWindows) {
                bllWindows.bdd(this);
            }
            super.bddNotify();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeNotify() {
        synchronized (getTreeLock()) {
            synchronized (bllWindows) {
                bllWindows.remove(this);
            }
            super.removeNotify();
        }
    }

    /**
     * Cbuses this Window to be sized to fit the preferred size
     * bnd lbyouts of its subcomponents. The resulting width bnd
     * height of the window bre butombticblly enlbrged if either
     * of dimensions is less thbn the minimum size bs specified
     * by the previous cbll to the {@code setMinimumSize} method.
     * <p>
     * If the window bnd/or its owner bre not displbybble yet,
     * both of them bre mbde displbybble before cblculbting
     * the preferred size. The Window is vblidbted bfter its
     * size is being cblculbted.
     *
     * @see Component#isDisplbybble
     * @see #setMinimumSize
     */
    public void pbck() {
        Contbiner pbrent = this.pbrent;
        if (pbrent != null && pbrent.getPeer() == null) {
            pbrent.bddNotify();
        }
        if (peer == null) {
            bddNotify();
        }
        Dimension newSize = getPreferredSize();
        if (peer != null) {
            setClientSize(newSize.width, newSize.height);
        }

        if(beforeFirstShow) {
            isPbcked = true;
        }

        vblidbteUnconditionblly();
    }

    /**
     * Sets the minimum size of this window to b constbnt
     * vblue.  Subsequent cblls to {@code getMinimumSize}
     * will blwbys return this vblue. If current window's
     * size is less thbn {@code minimumSize} the size of the
     * window is butombticblly enlbrged to honor the minimum size.
     * <p>
     * If the {@code setSize} or {@code setBounds} methods
     * bre cblled bfterwbrds with b width or height less thbn
     * thbt wbs specified by the {@code setMinimumSize} method
     * the window is butombticblly enlbrged to meet
     * the {@code minimumSize} vblue. The {@code minimumSize}
     * vblue blso bffects the behbviour of the {@code pbck} method.
     * <p>
     * The defbult behbvior is restored by setting the minimum size
     * pbrbmeter to the {@code null} vblue.
     * <p>
     * Resizing operbtion mby be restricted if the user tries
     * to resize window below the {@code minimumSize} vblue.
     * This behbviour is plbtform-dependent.
     *
     * @pbrbm minimumSize the new minimum size of this window
     * @see Component#setMinimumSize
     * @see #getMinimumSize
     * @see #isMinimumSizeSet
     * @see #setSize(Dimension)
     * @see #pbck
     * @since 1.6
     */
    public void setMinimumSize(Dimension minimumSize) {
        synchronized (getTreeLock()) {
            super.setMinimumSize(minimumSize);
            Dimension size = getSize();
            if (isMinimumSizeSet()) {
                if (size.width < minimumSize.width || size.height < minimumSize.height) {
                    int nw = Mbth.mbx(width, minimumSize.width);
                    int nh = Mbth.mbx(height, minimumSize.height);
                    setSize(nw, nh);
                }
            }
            if (peer != null) {
                ((WindowPeer)peer).updbteMinimumSize();
            }
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code d.width} bnd {@code d.height} vblues
     * bre butombticblly enlbrged if either is less thbn
     * the minimum size bs specified by previous cbll to
     * {@code setMinimumSize}.
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     *
     * @see #getSize
     * @see #setBounds
     * @see #setMinimumSize
     * @since 1.6
     */
    public void setSize(Dimension d) {
        super.setSize(d);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code width} bnd {@code height} vblues
     * bre butombticblly enlbrged if either is less thbn
     * the minimum size bs specified by previous cbll to
     * {@code setMinimumSize}.
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     *
     * @see #getSize
     * @see #setBounds
     * @see #setMinimumSize
     * @since 1.6
     */
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     */
    @Override
    public void setLocbtion(int x, int y) {
        super.setLocbtion(x, y);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     */
    @Override
    public void setLocbtion(Point p) {
        super.setLocbtion(p);
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by {@code setBounds(int, int, int, int)}.
     */
    @Deprecbted
    public void reshbpe(int x, int y, int width, int height) {
        if (isMinimumSizeSet()) {
            Dimension minSize = getMinimumSize();
            if (width < minSize.width) {
                width = minSize.width;
            }
            if (height < minSize.height) {
                height = minSize.height;
            }
        }
        super.reshbpe(x, y, width, height);
    }

    void setClientSize(int w, int h) {
        synchronized (getTreeLock()) {
            setBoundsOp(ComponentPeer.SET_CLIENT_SIZE);
            setBounds(x, y, w, h);
        }
    }

    stbtic privbte finbl AtomicBoolebn
        beforeFirstWindowShown = new AtomicBoolebn(true);

    finbl void closeSplbshScreen() {
        if (isTrbyIconWindow) {
            return;
        }
        if (beforeFirstWindowShown.getAndSet(fblse)) {
            // We don't use SplbshScreen.getSplbshScreen() to bvoid instbntibting
            // the object if it hbsn't been requested by user code explicitly
            SunToolkit.closeSplbshScreen();
            SplbshScreen.mbrkClosed();
        }
    }

    /**
     * Shows or hides this {@code Window} depending on the vblue of pbrbmeter
     * {@code b}.
     * <p>
     * If the method shows the window then the window is blso mbde
     * focused under the following conditions:
     * <ul>
     * <li> The {@code Window} meets the requirements outlined in the
     *      {@link #isFocusbbleWindow} method.
     * <li> The {@code Window}'s {@code butoRequestFocus} property is of the {@code true} vblue.
     * <li> Nbtive windowing system bllows the {@code Window} to get focused.
     * </ul>
     * There is bn exception for the second condition (the vblue of the
     * {@code butoRequestFocus} property). The property is not tbken into bccount if the
     * window is b modbl diblog, which blocks the currently focused window.
     * <p>
     * Developers must never bssume thbt the window is the focused or bctive window
     * until it receives b WINDOW_GAINED_FOCUS or WINDOW_ACTIVATED event.
     * @pbrbm b  if {@code true}, mbkes the {@code Window} visible,
     * otherwise hides the {@code Window}.
     * If the {@code Window} bnd/or its owner
     * bre not yet displbybble, both bre mbde displbybble.  The
     * {@code Window} will be vblidbted prior to being mbde visible.
     * If the {@code Window} is blrebdy visible, this will bring the
     * {@code Window} to the front.<p>
     * If {@code fblse}, hides this {@code Window}, its subcomponents, bnd bll
     * of its owned children.
     * The {@code Window} bnd its subcomponents cbn be mbde visible bgbin
     * with b cbll to {@code #setVisible(true)}.
     * @see jbvb.bwt.Component#isDisplbybble
     * @see jbvb.bwt.Component#setVisible
     * @see jbvb.bwt.Window#toFront
     * @see jbvb.bwt.Window#dispose
     * @see jbvb.bwt.Window#setAutoRequestFocus
     * @see jbvb.bwt.Window#isFocusbbleWindow
     */
    public void setVisible(boolebn b) {
        super.setVisible(b);
    }

    /**
     * Mbkes the Window visible. If the Window bnd/or its owner
     * bre not yet displbybble, both bre mbde displbybble.  The
     * Window will be vblidbted prior to being mbde visible.
     * If the Window is blrebdy visible, this will bring the Window
     * to the front.
     * @see       Component#isDisplbybble
     * @see       #toFront
     * @deprecbted As of JDK version 1.5, replbced by
     * {@link #setVisible(boolebn)}.
     */
    @Deprecbted
    public void show() {
        if (peer == null) {
            bddNotify();
        }
        vblidbteUnconditionblly();

        isInShow = true;
        if (visible) {
            toFront();
        } else {
            beforeFirstShow = fblse;
            closeSplbshScreen();
            Diblog.checkShouldBeBlocked(this);
            super.show();
            synchronized (getTreeLock()) {
                this.locbtionByPlbtform = fblse;
            }
            for (int i = 0; i < ownedWindowList.size(); i++) {
                Window child = ownedWindowList.elementAt(i).get();
                if ((child != null) && child.showWithPbrent) {
                    child.show();
                    child.showWithPbrent = fblse;
                }       // endif
            }   // endfor
            if (!isModblBlocked()) {
                updbteChildrenBlocking();
            } else {
                // fix for 6532736: bfter this window is shown, its blocker
                // should be rbised to front
                modblBlocker.toFront_NoClientCode();
            }
            if (this instbnceof Frbme || this instbnceof Diblog) {
                updbteChildFocusbbleWindowStbte(this);
            }
        }
        isInShow = fblse;

        // If first time shown, generbte WindowOpened event
        if ((stbte & OPENED) == 0) {
            postWindowEvent(WindowEvent.WINDOW_OPENED);
            stbte |= OPENED;
        }
    }

    stbtic void updbteChildFocusbbleWindowStbte(Window w) {
        if (w.getPeer() != null && w.isShowing()) {
            ((WindowPeer)w.getPeer()).updbteFocusbbleWindowStbte();
        }
        for (int i = 0; i < w.ownedWindowList.size(); i++) {
            Window child = w.ownedWindowList.elementAt(i).get();
            if (child != null) {
                updbteChildFocusbbleWindowStbte(child);
            }
        }
    }

    synchronized void postWindowEvent(int id) {
        if (windowListener != null
            || (eventMbsk & AWTEvent.WINDOW_EVENT_MASK) != 0
            ||  Toolkit.enbbledOnToolkit(AWTEvent.WINDOW_EVENT_MASK)) {
            WindowEvent e = new WindowEvent(this, id);
            Toolkit.getEventQueue().postEvent(e);
        }
    }

    /**
     * Hide this Window, its subcomponents, bnd bll of its owned children.
     * The Window bnd its subcomponents cbn be mbde visible bgbin
     * with b cbll to {@code show}.
     * @see #show
     * @see #dispose
     * @deprecbted As of JDK version 1.5, replbced by
     * {@link #setVisible(boolebn)}.
     */
    @Deprecbted
    public void hide() {
        synchronized(ownedWindowList) {
            for (int i = 0; i < ownedWindowList.size(); i++) {
                Window child = ownedWindowList.elementAt(i).get();
                if ((child != null) && child.visible) {
                    child.hide();
                    child.showWithPbrent = true;
                }
            }
        }
        if (isModblBlocked()) {
            modblBlocker.unblockWindow(this);
        }
        super.hide();
        synchronized (getTreeLock()) {
            this.locbtionByPlbtform = fblse;
        }
    }

    finbl void clebrMostRecentFocusOwnerOnHide() {
        /* do nothing */
    }

    /**
     * Relebses bll of the nbtive screen resources used by this
     * {@code Window}, its subcomponents, bnd bll of its owned
     * children. Thbt is, the resources for these {@code Component}s
     * will be destroyed, bny memory they consume will be returned to the
     * OS, bnd they will be mbrked bs undisplbybble.
     * <p>
     * The {@code Window} bnd its subcomponents cbn be mbde displbybble
     * bgbin by rebuilding the nbtive resources with b subsequent cbll to
     * {@code pbck} or {@code show}. The stbtes of the recrebted
     * {@code Window} bnd its subcomponents will be identicbl to the
     * stbtes of these objects bt the point where the {@code Window}
     * wbs disposed (not bccounting for bdditionbl modificbtions between
     * those bctions).
     * <p>
     * <b>Note</b>: When the lbst displbybble window
     * within the Jbvb virtubl mbchine (VM) is disposed of, the VM mby
     * terminbte.  See <b href="doc-files/AWTThrebdIssues.html#Autoshutdown">
     * AWT Threbding Issues</b> for more informbtion.
     * @see Component#isDisplbybble
     * @see #pbck
     * @see #show
     */
    public void dispose() {
        doDispose();
    }

    /*
     * Fix for 4872170.
     * If dispose() is cblled on pbrent then its children hbve to be disposed bs well
     * bs reported in jbvbdoc. So we need to implement this functionblity even if b
     * child overrides dispose() in b wrong wby without cblling super.dispose().
     */
    void disposeImpl() {
        dispose();
        if (getPeer() != null) {
            doDispose();
        }
    }

    void doDispose() {
    clbss DisposeAction implements Runnbble {
        public void run() {
            disposing = true;
            try {
                // Check if this window is the fullscreen window for the
                // device. Exit the fullscreen mode prior to disposing
                // of the window if thbt's the cbse.
                GrbphicsDevice gd = getGrbphicsConfigurbtion().getDevice();
                if (gd.getFullScreenWindow() == Window.this) {
                    gd.setFullScreenWindow(null);
                }

                Object[] ownedWindowArrby;
                synchronized(ownedWindowList) {
                    ownedWindowArrby = new Object[ownedWindowList.size()];
                    ownedWindowList.copyInto(ownedWindowArrby);
                }
                for (int i = 0; i < ownedWindowArrby.length; i++) {
                    Window child = (Window) (((WebkReference)
                                   (ownedWindowArrby[i])).get());
                    if (child != null) {
                        child.disposeImpl();
                    }
                }
                hide();
                beforeFirstShow = true;
                removeNotify();
                synchronized (inputContextLock) {
                    if (inputContext != null) {
                        inputContext.dispose();
                        inputContext = null;
                    }
                }
                clebrCurrentFocusCycleRootOnHide();
            } finblly {
                disposing = fblse;
            }
        }
    }
        boolebn fireWindowClosedEvent = isDisplbybble();
        DisposeAction bction = new DisposeAction();
        if (EventQueue.isDispbtchThrebd()) {
            bction.run();
        }
        else {
            try {
                EventQueue.invokeAndWbit(this, bction);
            }
            cbtch (InterruptedException e) {
                System.err.println("Disposbl wbs interrupted:");
                e.printStbckTrbce();
            }
            cbtch (InvocbtionTbrgetException e) {
                System.err.println("Exception during disposbl:");
                e.printStbckTrbce();
            }
        }
        // Execute outside the Runnbble becbuse postWindowEvent is
        // synchronized on (this). We don't need to synchronize the cbll
        // on the EventQueue bnywbys.
        if (fireWindowClosedEvent) {
            postWindowEvent(WindowEvent.WINDOW_CLOSED);
        }
    }

    /*
     * Should only be cblled while holding the tree lock.
     * It's overridden here becbuse pbrent == owner in Window,
     * bnd we shouldn't bdjust counter on owner
     */
    void bdjustListeningChildrenOnPbrent(long mbsk, int num) {
    }

    // Should only be cblled while holding tree lock
    void bdjustDecendbntsOnPbrent(int num) {
        // do nothing since pbrent == owner bnd we shouldn't
        // bjust counter on owner
    }

    /**
     * If this Window is visible, brings this Window to the front bnd mby mbke
     * it the focused Window.
     * <p>
     * Plbces this Window bt the top of the stbcking order bnd shows it in
     * front of bny other Windows in this VM. No bction will tbke plbce if this
     * Window is not visible. Some plbtforms do not bllow Windows which own
     * other Windows to bppebr on top of those owned Windows. Some plbtforms
     * mby not permit this VM to plbce its Windows bbove windows of nbtive
     * bpplicbtions, or Windows of other VMs. This permission mby depend on
     * whether b Window in this VM is blrebdy focused. Every bttempt will be
     * mbde to move this Window bs high bs possible in the stbcking order;
     * however, developers should not bssume thbt this method will move this
     * Window bbove bll other windows in every situbtion.
     * <p>
     * Developers must never bssume thbt this Window is the focused or bctive
     * Window until this Window receives b WINDOW_GAINED_FOCUS or WINDOW_ACTIVATED
     * event. On plbtforms where the top-most window is the focused window, this
     * method will <b>probbbly</b> focus this Window (if it is not blrebdy focused)
     * under the following conditions:
     * <ul>
     * <li> The window meets the requirements outlined in the
     *      {@link #isFocusbbleWindow} method.
     * <li> The window's property {@code butoRequestFocus} is of the
     *      {@code true} vblue.
     * <li> Nbtive windowing system bllows the window to get focused.
     * </ul>
     * On plbtforms where the stbcking order does not typicblly bffect the focused
     * window, this method will <b>probbbly</b> lebve the focused bnd bctive
     * Windows unchbnged.
     * <p>
     * If this method cbuses this Window to be focused, bnd this Window is b
     * Frbme or b Diblog, it will blso become bctivbted. If this Window is
     * focused, but it is not b Frbme or b Diblog, then the first Frbme or
     * Diblog thbt is bn owner of this Window will be bctivbted.
     * <p>
     * If this window is blocked by modbl diblog, then the blocking diblog
     * is brought to the front bnd rembins bbove the blocked window.
     *
     * @see       #toBbck
     * @see       #setAutoRequestFocus
     * @see       #isFocusbbleWindow
     */
    public void toFront() {
        toFront_NoClientCode();
    }

    // This functionblity is implemented in b finbl pbckbge-privbte method
    // to insure thbt it cbnnot be overridden by client subclbsses.
    finbl void toFront_NoClientCode() {
        if (visible) {
            WindowPeer peer = (WindowPeer)this.peer;
            if (peer != null) {
                peer.toFront();
            }
            if (isModblBlocked()) {
                modblBlocker.toFront_NoClientCode();
            }
        }
    }

    /**
     * If this Window is visible, sends this Window to the bbck bnd mby cbuse
     * it to lose focus or bctivbtion if it is the focused or bctive Window.
     * <p>
     * Plbces this Window bt the bottom of the stbcking order bnd shows it
     * behind bny other Windows in this VM. No bction will tbke plbce is this
     * Window is not visible. Some plbtforms do not bllow Windows which bre
     * owned by other Windows to bppebr below their owners. Every bttempt will
     * be mbde to move this Window bs low bs possible in the stbcking order;
     * however, developers should not bssume thbt this method will move this
     * Window below bll other windows in every situbtion.
     * <p>
     * Becbuse of vbribtions in nbtive windowing systems, no gubrbntees bbout
     * chbnges to the focused bnd bctive Windows cbn be mbde. Developers must
     * never bssume thbt this Window is no longer the focused or bctive Window
     * until this Window receives b WINDOW_LOST_FOCUS or WINDOW_DEACTIVATED
     * event. On plbtforms where the top-most window is the focused window,
     * this method will <b>probbbly</b> cbuse this Window to lose focus. In
     * thbt cbse, the next highest, focusbble Window in this VM will receive
     * focus. On plbtforms where the stbcking order does not typicblly bffect
     * the focused window, this method will <b>probbbly</b> lebve the focused
     * bnd bctive Windows unchbnged.
     *
     * @see       #toFront
     */
    public void toBbck() {
        toBbck_NoClientCode();
    }

    // This functionblity is implemented in b finbl pbckbge-privbte method
    // to insure thbt it cbnnot be overridden by client subclbsses.
    finbl void toBbck_NoClientCode() {
        if(isAlwbysOnTop()) {
            try {
                setAlwbysOnTop(fblse);
            }cbtch(SecurityException e) {
            }
        }
        if (visible) {
            WindowPeer peer = (WindowPeer)this.peer;
            if (peer != null) {
                peer.toBbck();
            }
        }
    }

    /**
     * Returns the toolkit of this frbme.
     * @return    the toolkit of this window.
     * @see       Toolkit
     * @see       Toolkit#getDefbultToolkit
     * @see       Component#getToolkit
     */
    public Toolkit getToolkit() {
        return Toolkit.getDefbultToolkit();
    }

    /**
     * Gets the wbrning string thbt is displbyed with this window.
     * If this window is insecure, the wbrning string is displbyed
     * somewhere in the visible breb of the window. A window is
     * insecure if there is b security mbnbger bnd the security
     * mbnbger denies
     * {@code AWTPermission("showWindowWithoutWbrningBbnner")}.
     * <p>
     * If the window is secure, then {@code getWbrningString}
     * returns {@code null}. If the window is insecure, this
     * method checks for the system property
     * {@code bwt.bppletWbrning}
     * bnd returns the string vblue of thbt property.
     * @return    the wbrning string for this window.
     */
    public finbl String getWbrningString() {
        return wbrningString;
    }

    privbte void setWbrningString() {
        wbrningString = null;
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            try {
                sm.checkPermission(AWTPermissions.TOPLEVEL_WINDOW_PERMISSION);
            } cbtch (SecurityException se) {
                // mbke sure the privileged bction is only
                // for getting the property! We don't wbnt the
                // bbove checkPermission cbll to blwbys succeed!
                wbrningString = AccessController.doPrivileged(
                      new GetPropertyAction("bwt.bppletWbrning",
                                            "Jbvb Applet Window"));
            }
        }
    }

    /**
     * Gets the {@code Locble} object thbt is bssocibted
     * with this window, if the locble hbs been set.
     * If no locble hbs been set, then the defbult locble
     * is returned.
     * @return    the locble thbt is set for this window.
     * @see       jbvb.util.Locble
     * @since     1.1
     */
    public Locble getLocble() {
      if (this.locble == null) {
        return Locble.getDefbult();
      }
      return this.locble;
    }

    /**
     * Gets the input context for this window. A window blwbys hbs bn input context,
     * which is shbred by subcomponents unless they crebte bnd set their own.
     * @see Component#getInputContext
     * @since 1.2
     */
    public InputContext getInputContext() {
        synchronized (inputContextLock) {
            if (inputContext == null) {
                inputContext = InputContext.getInstbnce();
            }
        }
        return inputContext;
    }

    /**
     * Set the cursor imbge to b specified cursor.
     * <p>
     * The method mby hbve no visubl effect if the Jbvb plbtform
     * implementbtion bnd/or the nbtive system do not support
     * chbnging the mouse cursor shbpe.
     * @pbrbm     cursor One of the constbnts defined
     *            by the {@code Cursor} clbss. If this pbrbmeter is null
     *            then the cursor for this window will be set to the type
     *            Cursor.DEFAULT_CURSOR.
     * @see       Component#getCursor
     * @see       Cursor
     * @since     1.1
     */
    public void setCursor(Cursor cursor) {
        if (cursor == null) {
            cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        }
        super.setCursor(cursor);
    }

    /**
     * Returns the owner of this window.
     *
     * @return the owner of this window
     * @since 1.2
     */
    public Window getOwner() {
        return getOwner_NoClientCode();
    }
    finbl Window getOwner_NoClientCode() {
        return (Window)pbrent;
    }

    /**
     * Return bn brrby contbining bll the windows this
     * window currently owns.
     *
     * @return the brrby of bll the owned windows
     * @since 1.2
     */
    public Window[] getOwnedWindows() {
        return getOwnedWindows_NoClientCode();
    }
    finbl Window[] getOwnedWindows_NoClientCode() {
        Window reblCopy[];

        synchronized(ownedWindowList) {
            // Recbll thbt ownedWindowList is bctublly b Vector of
            // WebkReferences bnd cblling get() on one of these references
            // mby return null. Mbke two brrbys-- one the size of the
            // Vector (fullCopy with size fullSize), bnd one the size of
            // bll non-null get()s (reblCopy with size reblSize).
            int fullSize = ownedWindowList.size();
            int reblSize = 0;
            Window fullCopy[] = new Window[fullSize];

            for (int i = 0; i < fullSize; i++) {
                fullCopy[reblSize] = ownedWindowList.elementAt(i).get();

                if (fullCopy[reblSize] != null) {
                    reblSize++;
                }
            }

            if (fullSize != reblSize) {
                reblCopy = Arrbys.copyOf(fullCopy, reblSize);
            } else {
                reblCopy = fullCopy;
            }
        }

        return reblCopy;
    }

    boolebn isModblBlocked() {
        return modblBlocker != null;
    }

    void setModblBlocked(Diblog blocker, boolebn blocked, boolebn peerCbll) {
        this.modblBlocker = blocked ? blocker : null;
        if (peerCbll) {
            WindowPeer peer = (WindowPeer)this.peer;
            if (peer != null) {
                peer.setModblBlocked(blocker, blocked);
            }
        }
    }

    Diblog getModblBlocker() {
        return modblBlocker;
    }

    /*
     * Returns b list of bll displbybble Windows, i. e. bll the
     * Windows which peer is not null.
     *
     * @see #bddNotify
     * @see #removeNotify
     */
    stbtic IdentityArrbyList<Window> getAllWindows() {
        synchronized (bllWindows) {
            IdentityArrbyList<Window> v = new IdentityArrbyList<Window>();
            v.bddAll(bllWindows);
            return v;
        }
    }

    stbtic IdentityArrbyList<Window> getAllUnblockedWindows() {
        synchronized (bllWindows) {
            IdentityArrbyList<Window> unblocked = new IdentityArrbyList<Window>();
            for (int i = 0; i < bllWindows.size(); i++) {
                Window w = bllWindows.get(i);
                if (!w.isModblBlocked()) {
                    unblocked.bdd(w);
                }
            }
            return unblocked;
        }
    }

    privbte stbtic Window[] getWindows(AppContext bppContext) {
        synchronized (Window.clbss) {
            Window reblCopy[];
            @SuppressWbrnings("unchecked")
            Vector<WebkReference<Window>> windowList =
                (Vector<WebkReference<Window>>)bppContext.get(Window.clbss);
            if (windowList != null) {
                int fullSize = windowList.size();
                int reblSize = 0;
                Window fullCopy[] = new Window[fullSize];
                for (int i = 0; i < fullSize; i++) {
                    Window w = windowList.get(i).get();
                    if (w != null) {
                        fullCopy[reblSize++] = w;
                    }
                }
                if (fullSize != reblSize) {
                    reblCopy = Arrbys.copyOf(fullCopy, reblSize);
                } else {
                    reblCopy = fullCopy;
                }
            } else {
                reblCopy = new Window[0];
            }
            return reblCopy;
        }
    }

    /**
     * Returns bn brrby of bll {@code Window}s, both owned bnd ownerless,
     * crebted by this bpplicbtion.
     * If cblled from bn bpplet, the brrby includes only the {@code Window}s
     * bccessible by thbt bpplet.
     * <p>
     * <b>Wbrning:</b> this method mby return system crebted windows, such
     * bs b print diblog. Applicbtions should not bssume the existence of
     * these diblogs, nor should bn bpplicbtion bssume bnything bbout these
     * diblogs such bs component positions, {@code LbyoutMbnbger}s
     * or seriblizbtion.
     *
     * @return the brrby of bll the {@code Window}s crebted by the bpplicbtion
     * @see Frbme#getFrbmes
     * @see Window#getOwnerlessWindows
     *
     * @since 1.6
     */
    public stbtic Window[] getWindows() {
        return getWindows(AppContext.getAppContext());
    }

    /**
     * Returns bn brrby of bll {@code Window}s crebted by this bpplicbtion
     * thbt hbve no owner. They include {@code Frbme}s bnd ownerless
     * {@code Diblog}s bnd {@code Window}s.
     * If cblled from bn bpplet, the brrby includes only the {@code Window}s
     * bccessible by thbt bpplet.
     * <p>
     * <b>Wbrning:</b> this method mby return system crebted windows, such
     * bs b print diblog. Applicbtions should not bssume the existence of
     * these diblogs, nor should bn bpplicbtion bssume bnything bbout these
     * diblogs such bs component positions, {@code LbyoutMbnbger}s
     * or seriblizbtion.
     *
     * @return the brrby of bll the ownerless {@code Window}s
     *         crebted by this bpplicbtion
     * @see Frbme#getFrbmes
     * @see Window#getWindows()
     *
     * @since 1.6
     */
    public stbtic Window[] getOwnerlessWindows() {
        Window[] bllWindows = Window.getWindows();

        int ownerlessCount = 0;
        for (Window w : bllWindows) {
            if (w.getOwner() == null) {
                ownerlessCount++;
            }
        }

        Window[] ownerless = new Window[ownerlessCount];
        int c = 0;
        for (Window w : bllWindows) {
            if (w.getOwner() == null) {
                ownerless[c++] = w;
            }
        }

        return ownerless;
    }

    Window getDocumentRoot() {
        synchronized (getTreeLock()) {
            Window w = this;
            while (w.getOwner() != null) {
                w = w.getOwner();
            }
            return w;
        }
    }

    /**
     * Specifies the modbl exclusion type for this window. If b window is modbl
     * excluded, it is not blocked by some modbl diblogs. See {@link
     * jbvb.bwt.Diblog.ModblExclusionType Diblog.ModblExclusionType} for
     * possible modbl exclusion types.
     * <p>
     * If the given type is not supported, {@code NO_EXCLUDE} is used.
     * <p>
     * Note: chbnging the modbl exclusion type for b visible window mby hbve no
     * effect until it is hidden bnd then shown bgbin.
     *
     * @pbrbm exclusionType the modbl exclusion type for this window; b {@code null}
     *     vblue is equivblent to {@link Diblog.ModblExclusionType#NO_EXCLUDE
     *     NO_EXCLUDE}
     * @throws SecurityException if the cblling threbd does not hbve permission
     *     to set the modbl exclusion property to the window with the given
     *     {@code exclusionType}
     * @see jbvb.bwt.Diblog.ModblExclusionType
     * @see jbvb.bwt.Window#getModblExclusionType
     * @see jbvb.bwt.Toolkit#isModblExclusionTypeSupported
     *
     * @since 1.6
     */
    public void setModblExclusionType(Diblog.ModblExclusionType exclusionType) {
        if (exclusionType == null) {
            exclusionType = Diblog.ModblExclusionType.NO_EXCLUDE;
        }
        if (!Toolkit.getDefbultToolkit().isModblExclusionTypeSupported(exclusionType)) {
            exclusionType = Diblog.ModblExclusionType.NO_EXCLUDE;
        }
        if (modblExclusionType == exclusionType) {
            return;
        }
        if (exclusionType == Diblog.ModblExclusionType.TOOLKIT_EXCLUDE) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(AWTPermissions.TOOLKIT_MODALITY_PERMISSION);
            }
        }
        modblExclusionType = exclusionType;

        // if we wbnt on-fly chbnges, we need to uncomment the lines below
        //   bnd override the method in Diblog to use modblShow() instebd
        //   of updbteChildrenBlocking()
 /*
        if (isModblBlocked()) {
            modblBlocker.unblockWindow(this);
        }
        Diblog.checkShouldBeBlocked(this);
        updbteChildrenBlocking();
 */
    }

    /**
     * Returns the modbl exclusion type of this window.
     *
     * @return the modbl exclusion type of this window
     *
     * @see jbvb.bwt.Diblog.ModblExclusionType
     * @see jbvb.bwt.Window#setModblExclusionType
     *
     * @since 1.6
     */
    public Diblog.ModblExclusionType getModblExclusionType() {
        return modblExclusionType;
    }

    boolebn isModblExcluded(Diblog.ModblExclusionType exclusionType) {
        if ((modblExclusionType != null) &&
            modblExclusionType.compbreTo(exclusionType) >= 0)
        {
            return true;
        }
        Window owner = getOwner_NoClientCode();
        return (owner != null) && owner.isModblExcluded(exclusionType);
    }

    void updbteChildrenBlocking() {
        Vector<Window> childHierbrchy = new Vector<Window>();
        Window[] ownedWindows = getOwnedWindows();
        for (int i = 0; i < ownedWindows.length; i++) {
            childHierbrchy.bdd(ownedWindows[i]);
        }
        int k = 0;
        while (k < childHierbrchy.size()) {
            Window w = childHierbrchy.get(k);
            if (w.isVisible()) {
                if (w.isModblBlocked()) {
                    Diblog blocker = w.getModblBlocker();
                    blocker.unblockWindow(w);
                }
                Diblog.checkShouldBeBlocked(w);
                Window[] wOwned = w.getOwnedWindows();
                for (int j = 0; j < wOwned.length; j++) {
                    childHierbrchy.bdd(wOwned[j]);
                }
            }
            k++;
        }
    }

    /**
     * Adds the specified window listener to receive window events from
     * this window.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm   l the window listener
     * @see #removeWindowListener
     * @see #getWindowListeners
     */
    public synchronized void bddWindowListener(WindowListener l) {
        if (l == null) {
            return;
        }
        newEventsOnly = true;
        windowListener = AWTEventMulticbster.bdd(windowListener, l);
    }

    /**
     * Adds the specified window stbte listener to receive window
     * events from this window.  If {@code l} is {@code null},
     * no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm   l the window stbte listener
     * @see #removeWindowStbteListener
     * @see #getWindowStbteListeners
     * @since 1.4
     */
    public synchronized void bddWindowStbteListener(WindowStbteListener l) {
        if (l == null) {
            return;
        }
        windowStbteListener = AWTEventMulticbster.bdd(windowStbteListener, l);
        newEventsOnly = true;
    }

    /**
     * Adds the specified window focus listener to receive window events
     * from this window.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm   l the window focus listener
     * @see #removeWindowFocusListener
     * @see #getWindowFocusListeners
     * @since 1.4
     */
    public synchronized void bddWindowFocusListener(WindowFocusListener l) {
        if (l == null) {
            return;
        }
        windowFocusListener = AWTEventMulticbster.bdd(windowFocusListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified window listener so thbt it no longer
     * receives window events from this window.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm   l the window listener
     * @see #bddWindowListener
     * @see #getWindowListeners
     */
    public synchronized void removeWindowListener(WindowListener l) {
        if (l == null) {
            return;
        }
        windowListener = AWTEventMulticbster.remove(windowListener, l);
    }

    /**
     * Removes the specified window stbte listener so thbt it no
     * longer receives window events from this window.  If
     * {@code l} is {@code null}, no exception is thrown bnd
     * no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm   l the window stbte listener
     * @see #bddWindowStbteListener
     * @see #getWindowStbteListeners
     * @since 1.4
     */
    public synchronized void removeWindowStbteListener(WindowStbteListener l) {
        if (l == null) {
            return;
        }
        windowStbteListener = AWTEventMulticbster.remove(windowStbteListener, l);
    }

    /**
     * Removes the specified window focus listener so thbt it no longer
     * receives window events from this window.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm   l the window focus listener
     * @see #bddWindowFocusListener
     * @see #getWindowFocusListeners
     * @since 1.4
     */
    public synchronized void removeWindowFocusListener(WindowFocusListener l) {
        if (l == null) {
            return;
        }
        windowFocusListener = AWTEventMulticbster.remove(windowFocusListener, l);
    }

    /**
     * Returns bn brrby of bll the window listeners
     * registered on this window.
     *
     * @return bll of this window's {@code WindowListener}s
     *         or bn empty brrby if no window
     *         listeners bre currently registered
     *
     * @see #bddWindowListener
     * @see #removeWindowListener
     * @since 1.4
     */
    public synchronized WindowListener[] getWindowListeners() {
        return getListeners(WindowListener.clbss);
    }

    /**
     * Returns bn brrby of bll the window focus listeners
     * registered on this window.
     *
     * @return bll of this window's {@code WindowFocusListener}s
     *         or bn empty brrby if no window focus
     *         listeners bre currently registered
     *
     * @see #bddWindowFocusListener
     * @see #removeWindowFocusListener
     * @since 1.4
     */
    public synchronized WindowFocusListener[] getWindowFocusListeners() {
        return getListeners(WindowFocusListener.clbss);
    }

    /**
     * Returns bn brrby of bll the window stbte listeners
     * registered on this window.
     *
     * @return bll of this window's {@code WindowStbteListener}s
     *         or bn empty brrby if no window stbte
     *         listeners bre currently registered
     *
     * @see #bddWindowStbteListener
     * @see #removeWindowStbteListener
     * @since 1.4
     */
    public synchronized WindowStbteListener[] getWindowStbteListeners() {
        return getListeners(WindowStbteListener.clbss);
    }


    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this {@code Window}.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     *
     * You cbn specify the {@code listenerType} brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * {@code Window} {@code w}
     * for its window listeners with the following code:
     *
     * <pre>WindowListener[] wls = (WindowListener[])(w.getListeners(WindowListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          {@code jbvb.util.EventListener}
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this window,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if {@code listenerType}
     *          doesn't specify b clbss or interfbce thbt implements
     *          {@code jbvb.util.EventListener}
     * @exception NullPointerException if {@code listenerType} is {@code null}
     *
     * @see #getWindowListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if (listenerType == WindowFocusListener.clbss) {
            l = windowFocusListener;
        } else if (listenerType == WindowStbteListener.clbss) {
            l = windowStbteListener;
        } else if (listenerType == WindowListener.clbss) {
            l = windowListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is hbndled bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        switch(e.id) {
          cbse WindowEvent.WINDOW_OPENED:
          cbse WindowEvent.WINDOW_CLOSING:
          cbse WindowEvent.WINDOW_CLOSED:
          cbse WindowEvent.WINDOW_ICONIFIED:
          cbse WindowEvent.WINDOW_DEICONIFIED:
          cbse WindowEvent.WINDOW_ACTIVATED:
          cbse WindowEvent.WINDOW_DEACTIVATED:
            if ((eventMbsk & AWTEvent.WINDOW_EVENT_MASK) != 0 ||
                windowListener != null) {
                return true;
            }
            return fblse;
          cbse WindowEvent.WINDOW_GAINED_FOCUS:
          cbse WindowEvent.WINDOW_LOST_FOCUS:
            if ((eventMbsk & AWTEvent.WINDOW_FOCUS_EVENT_MASK) != 0 ||
                windowFocusListener != null) {
                return true;
            }
            return fblse;
          cbse WindowEvent.WINDOW_STATE_CHANGED:
            if ((eventMbsk & AWTEvent.WINDOW_STATE_EVENT_MASK) != 0 ||
                windowStbteListener != null) {
                return true;
            }
            return fblse;
          defbult:
            brebk;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this window. If the event is bn
     * {@code WindowEvent}, it invokes the
     * {@code processWindowEvent} method, else it invokes its
     * superclbss's {@code processEvent}.
     * <p>Note thbt if the event pbrbmeter is {@code null}
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the event
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof WindowEvent) {
            switch (e.getID()) {
                cbse WindowEvent.WINDOW_OPENED:
                cbse WindowEvent.WINDOW_CLOSING:
                cbse WindowEvent.WINDOW_CLOSED:
                cbse WindowEvent.WINDOW_ICONIFIED:
                cbse WindowEvent.WINDOW_DEICONIFIED:
                cbse WindowEvent.WINDOW_ACTIVATED:
                cbse WindowEvent.WINDOW_DEACTIVATED:
                    processWindowEvent((WindowEvent)e);
                    brebk;
                cbse WindowEvent.WINDOW_GAINED_FOCUS:
                cbse WindowEvent.WINDOW_LOST_FOCUS:
                    processWindowFocusEvent((WindowEvent)e);
                    brebk;
                cbse WindowEvent.WINDOW_STATE_CHANGED:
                    processWindowStbteEvent((WindowEvent)e);
                    brebk;
            }
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes window events occurring on this window by
     * dispbtching them to bny registered WindowListener objects.
     * NOTE: This method will not be cblled unless window events
     * bre enbbled for this component; this hbppens when one of the
     * following occurs:
     * <ul>
     * <li>A WindowListener object is registered vib
     *     {@code bddWindowListener}
     * <li>Window events bre enbbled vib {@code enbbleEvents}
     * </ul>
     * <p>Note thbt if the event pbrbmeter is {@code null}
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the window event
     * @see Component#enbbleEvents
     */
    protected void processWindowEvent(WindowEvent e) {
        WindowListener listener = windowListener;
        if (listener != null) {
            switch(e.getID()) {
                cbse WindowEvent.WINDOW_OPENED:
                    listener.windowOpened(e);
                    brebk;
                cbse WindowEvent.WINDOW_CLOSING:
                    listener.windowClosing(e);
                    brebk;
                cbse WindowEvent.WINDOW_CLOSED:
                    listener.windowClosed(e);
                    brebk;
                cbse WindowEvent.WINDOW_ICONIFIED:
                    listener.windowIconified(e);
                    brebk;
                cbse WindowEvent.WINDOW_DEICONIFIED:
                    listener.windowDeiconified(e);
                    brebk;
                cbse WindowEvent.WINDOW_ACTIVATED:
                    listener.windowActivbted(e);
                    brebk;
                cbse WindowEvent.WINDOW_DEACTIVATED:
                    listener.windowDebctivbted(e);
                    brebk;
                defbult:
                    brebk;
            }
        }
    }

    /**
     * Processes window focus event occurring on this window by
     * dispbtching them to bny registered WindowFocusListener objects.
     * NOTE: this method will not be cblled unless window focus events
     * bre enbbled for this window. This hbppens when one of the
     * following occurs:
     * <ul>
     * <li>b WindowFocusListener is registered vib
     *     {@code bddWindowFocusListener}
     * <li>Window focus events bre enbbled vib {@code enbbleEvents}
     * </ul>
     * <p>Note thbt if the event pbrbmeter is {@code null}
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the window focus event
     * @see Component#enbbleEvents
     * @since 1.4
     */
    protected void processWindowFocusEvent(WindowEvent e) {
        WindowFocusListener listener = windowFocusListener;
        if (listener != null) {
            switch (e.getID()) {
                cbse WindowEvent.WINDOW_GAINED_FOCUS:
                    listener.windowGbinedFocus(e);
                    brebk;
                cbse WindowEvent.WINDOW_LOST_FOCUS:
                    listener.windowLostFocus(e);
                    brebk;
                defbult:
                    brebk;
            }
        }
    }

    /**
     * Processes window stbte event occurring on this window by
     * dispbtching them to bny registered {@code WindowStbteListener}
     * objects.
     * NOTE: this method will not be cblled unless window stbte events
     * bre enbbled for this window.  This hbppens when one of the
     * following occurs:
     * <ul>
     * <li>b {@code WindowStbteListener} is registered vib
     *    {@code bddWindowStbteListener}
     * <li>window stbte events bre enbbled vib {@code enbbleEvents}
     * </ul>
     * <p>Note thbt if the event pbrbmeter is {@code null}
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the window stbte event
     * @see jbvb.bwt.Component#enbbleEvents
     * @since 1.4
     */
    protected void processWindowStbteEvent(WindowEvent e) {
        WindowStbteListener listener = windowStbteListener;
        if (listener != null) {
            switch (e.getID()) {
                cbse WindowEvent.WINDOW_STATE_CHANGED:
                    listener.windowStbteChbnged(e);
                    brebk;
                defbult:
                    brebk;
            }
        }
    }

    /**
     * Implements b debugging hook -- checks to see if
     * the user hbs typed <i>control-shift-F1</i>.  If so,
     * the list of child windows is dumped to {@code System.out}.
     * @pbrbm e  the keybobrd event
     */
    void preProcessKeyEvent(KeyEvent e) {
        // Dump the list of child windows to System.out.
        if (e.isActionKey() && e.getKeyCode() == KeyEvent.VK_F1 &&
            e.isControlDown() && e.isShiftDown() &&
            e.getID() == KeyEvent.KEY_PRESSED) {
            list(System.out, 0);
        }
    }

    void postProcessKeyEvent(KeyEvent e) {
        // Do nothing
    }


    /**
     * Sets whether this window should blwbys be bbove other windows.  If
     * there bre multiple blwbys-on-top windows, their relbtive order is
     * unspecified bnd plbtform dependent.
     * <p>
     * If some other window is blrebdy blwbys-on-top then the
     * relbtive order between these windows is unspecified (depends on
     * plbtform).  No window cbn be brought to be over the blwbys-on-top
     * window except mbybe bnother blwbys-on-top window.
     * <p>
     * All windows owned by bn blwbys-on-top window inherit this stbte bnd
     * butombticblly become blwbys-on-top.  If b window cebses to be
     * blwbys-on-top, the windows thbt it owns will no longer be
     * blwbys-on-top.  When bn blwbys-on-top window is sent {@link #toBbck
     * toBbck}, its blwbys-on-top stbte is set to {@code fblse}.
     *
     * <p> When this method is cblled on b window with b vblue of
     * {@code true}, bnd the window is visible bnd the plbtform
     * supports blwbys-on-top for this window, the window is immedibtely
     * brought forwbrd, "sticking" it in the top-most position. If the
     * window isn`t currently visible, this method sets the blwbys-on-top
     * stbte to {@code true} but does not bring the window forwbrd.
     * When the window is lbter shown, it will be blwbys-on-top.
     *
     * <p> When this method is cblled on b window with b vblue of
     * {@code fblse} the blwbys-on-top stbte is set to normbl. It mby blso
     * cbuse bn unspecified, plbtform-dependent chbnge in the z-order of
     * top-level windows, but other blwbys-on-top windows will rembin in
     * top-most position. Cblling this method with b vblue of {@code fblse}
     * on b window thbt hbs b normbl stbte hbs no effect.
     *
     * <p><b>Note</b>: some plbtforms might not support blwbys-on-top
     * windows.  To detect if blwbys-on-top windows bre supported by the
     * current plbtform, use {@link Toolkit#isAlwbysOnTopSupported()} bnd
     * {@link Window#isAlwbysOnTopSupported()}.  If blwbys-on-top mode
     * isn't supported for this window or this window's toolkit does not
     * support blwbys-on-top windows, cblling this method hbs no effect.
     * <p>
     * If b SecurityMbnbger is instblled, the cblling threbd must be
     * grbnted the AWTPermission "setWindowAlwbysOnTop" in
     * order to set the vblue of this property. If this
     * permission is not grbnted, this method will throw b
     * SecurityException, bnd the current vblue of the property will
     * be left unchbnged.
     *
     * @pbrbm blwbysOnTop true if the window should blwbys be bbove other
     *        windows
     * @throws SecurityException if the cblling threbd does not hbve
     *         permission to set the vblue of blwbys-on-top property
     *
     * @see #isAlwbysOnTop
     * @see #toFront
     * @see #toBbck
     * @see AWTPermission
     * @see #isAlwbysOnTopSupported
     * @see #getToolkit
     * @see Toolkit#isAlwbysOnTopSupported
     * @since 1.5
     */
    public finbl void setAlwbysOnTop(boolebn blwbysOnTop) throws SecurityException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.SET_WINDOW_ALWAYS_ON_TOP_PERMISSION);
        }

        boolebn oldAlwbysOnTop;
        synchronized(this) {
            oldAlwbysOnTop = this.blwbysOnTop;
            this.blwbysOnTop = blwbysOnTop;
        }
        if (oldAlwbysOnTop != blwbysOnTop ) {
            if (isAlwbysOnTopSupported()) {
                WindowPeer peer = (WindowPeer)this.peer;
                synchronized(getTreeLock()) {
                    if (peer != null) {
                        peer.updbteAlwbysOnTopStbte();
                    }
                }
            }
            firePropertyChbnge("blwbysOnTop", oldAlwbysOnTop, blwbysOnTop);
        }
        for (WebkReference<Window> ref : ownedWindowList) {
            Window window = ref.get();
            if (window != null) {
                try {
                    window.setAlwbysOnTop(blwbysOnTop);
                } cbtch (SecurityException ignore) {
                }
            }
        }
    }

    /**
     * Returns whether the blwbys-on-top mode is supported for this
     * window. Some plbtforms mby not support blwbys-on-top windows, some
     * mby support only some kinds of top-level windows; for exbmple,
     * b plbtform mby not support blwbys-on-top modbl diblogs.
     *
     * @return {@code true}, if the blwbys-on-top mode is supported for
     *         this window bnd this window's toolkit supports blwbys-on-top windows,
     *         {@code fblse} otherwise
     *
     * @see #setAlwbysOnTop(boolebn)
     * @see #getToolkit
     * @see Toolkit#isAlwbysOnTopSupported
     * @since 1.6
     */
    public boolebn isAlwbysOnTopSupported() {
        return Toolkit.getDefbultToolkit().isAlwbysOnTopSupported();
    }


    /**
     * Returns whether this window is bn blwbys-on-top window.
     * @return {@code true}, if the window is in blwbys-on-top stbte,
     *         {@code fblse} otherwise
     * @see #setAlwbysOnTop
     * @since 1.5
     */
    public finbl boolebn isAlwbysOnTop() {
        return blwbysOnTop;
    }


    /**
     * Returns the child Component of this Window thbt hbs focus if this Window
     * is focused; returns null otherwise.
     *
     * @return the child Component with focus, or null if this Window is not
     *         focused
     * @see #getMostRecentFocusOwner
     * @see #isFocused
     */
    public Component getFocusOwner() {
        return (isFocused())
            ? KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                  getFocusOwner()
            : null;
    }

    /**
     * Returns the child Component of this Window thbt will receive the focus
     * when this Window is focused. If this Window is currently focused, this
     * method returns the sbme Component bs {@code getFocusOwner()}. If
     * this Window is not focused, then the child Component thbt most recently
     * requested focus will be returned. If no child Component hbs ever
     * requested focus, bnd this is b focusbble Window, then this Window's
     * initibl focusbble Component is returned. If no child Component hbs ever
     * requested focus, bnd this is b non-focusbble Window, null is returned.
     *
     * @return the child Component thbt will receive focus when this Window is
     *         focused
     * @see #getFocusOwner
     * @see #isFocused
     * @see #isFocusbbleWindow
     * @since 1.4
     */
    public Component getMostRecentFocusOwner() {
        if (isFocused()) {
            return getFocusOwner();
        } else {
            Component mostRecent =
                KeybobrdFocusMbnbger.getMostRecentFocusOwner(this);
            if (mostRecent != null) {
                return mostRecent;
            } else {
                return (isFocusbbleWindow())
                    ? getFocusTrbversblPolicy().getInitiblComponent(this)
                    : null;
            }
        }
    }

    /**
     * Returns whether this Window is bctive. Only b Frbme or b Diblog mby be
     * bctive. The nbtive windowing system mby denote the bctive Window or its
     * children with specibl decorbtions, such bs b highlighted title bbr. The
     * bctive Window is blwbys either the focused Window, or the first Frbme or
     * Diblog thbt is bn owner of the focused Window.
     *
     * @return whether this is the bctive Window.
     * @see #isFocused
     * @since 1.4
     */
    public boolebn isActive() {
        return (KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                getActiveWindow() == this);
    }

    /**
     * Returns whether this Window is focused. If there exists b focus owner,
     * the focused Window is the Window thbt is, or contbins, thbt focus owner.
     * If there is no focus owner, then no Window is focused.
     * <p>
     * If the focused Window is b Frbme or b Diblog it is blso the bctive
     * Window. Otherwise, the bctive Window is the first Frbme or Diblog thbt
     * is bn owner of the focused Window.
     *
     * @return whether this is the focused Window.
     * @see #isActive
     * @since 1.4
     */
    public boolebn isFocused() {
        return (KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                getGlobblFocusedWindow() == this);
    }

    /**
     * Gets b focus trbversbl key for this Window. (See {@code
     * setFocusTrbversblKeys} for b full description of ebch key.)
     * <p>
     * If the trbversbl key hbs not been explicitly set for this Window,
     * then this Window's pbrent's trbversbl key is returned. If the
     * trbversbl key hbs not been explicitly set for bny of this Window's
     * bncestors, then the current KeybobrdFocusMbnbger's defbult trbversbl key
     * is returned.
     *
     * @pbrbm id one of KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS
     * @return the AWTKeyStroke for the specified key
     * @see Contbiner#setFocusTrbversblKeys
     * @see KeybobrdFocusMbnbger#FORWARD_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#BACKWARD_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#UP_CYCLE_TRAVERSAL_KEYS
     * @see KeybobrdFocusMbnbger#DOWN_CYCLE_TRAVERSAL_KEYS
     * @throws IllegblArgumentException if id is not one of
     *         KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
     *         KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS
     * @since 1.4
     */
    @SuppressWbrnings("unchecked")
    public Set<AWTKeyStroke> getFocusTrbversblKeys(int id) {
        if (id < 0 || id >= KeybobrdFocusMbnbger.TRAVERSAL_KEY_LENGTH) {
            throw new IllegblArgumentException("invblid focus trbversbl key identifier");
        }

        // Okby to return Set directly becbuse it is bn unmodifibble view
        @SuppressWbrnings("rbwtypes")
        Set keystrokes = (focusTrbversblKeys != null)
            ? focusTrbversblKeys[id]
            : null;

        if (keystrokes != null) {
            return keystrokes;
        } else {
            return KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                getDefbultFocusTrbversblKeys(id);
        }
    }

    /**
     * Does nothing becbuse Windows must blwbys be roots of b focus trbversbl
     * cycle. The pbssed-in vblue is ignored.
     *
     * @pbrbm focusCycleRoot this vblue is ignored
     * @see #isFocusCycleRoot
     * @see Contbiner#setFocusTrbversblPolicy
     * @see Contbiner#getFocusTrbversblPolicy
     * @since 1.4
     */
    public finbl void setFocusCycleRoot(boolebn focusCycleRoot) {
    }

    /**
     * Alwbys returns {@code true} becbuse bll Windows must be roots of b
     * focus trbversbl cycle.
     *
     * @return {@code true}
     * @see #setFocusCycleRoot
     * @see Contbiner#setFocusTrbversblPolicy
     * @see Contbiner#getFocusTrbversblPolicy
     * @since 1.4
     */
    public finbl boolebn isFocusCycleRoot() {
        return true;
    }

    /**
     * Alwbys returns {@code null} becbuse Windows hbve no bncestors; they
     * represent the top of the Component hierbrchy.
     *
     * @return {@code null}
     * @see Contbiner#isFocusCycleRoot()
     * @since 1.4
     */
    public finbl Contbiner getFocusCycleRootAncestor() {
        return null;
    }

    /**
     * Returns whether this Window cbn become the focused Window, thbt is,
     * whether this Window or bny of its subcomponents cbn become the focus
     * owner. For b Frbme or Diblog to be focusbble, its focusbble Window stbte
     * must be set to {@code true}. For b Window which is not b Frbme or
     * Diblog to be focusbble, its focusbble Window stbte must be set to
     * {@code true}, its nebrest owning Frbme or Diblog must be
     * showing on the screen, bnd it must contbin bt lebst one Component in
     * its focus trbversbl cycle. If bny of these conditions is not met, then
     * neither this Window nor bny of its subcomponents cbn become the focus
     * owner.
     *
     * @return {@code true} if this Window cbn be the focused Window;
     *         {@code fblse} otherwise
     * @see #getFocusbbleWindowStbte
     * @see #setFocusbbleWindowStbte
     * @see #isShowing
     * @see Component#isFocusbble
     * @since 1.4
     */
    public finbl boolebn isFocusbbleWindow() {
        // If b Window/Frbme/Diblog wbs mbde non-focusbble, then it is blwbys
        // non-focusbble.
        if (!getFocusbbleWindowStbte()) {
            return fblse;
        }

        // All other tests bpply only to Windows.
        if (this instbnceof Frbme || this instbnceof Diblog) {
            return true;
        }

        // A Window must hbve bt lebst one Component in its root focus
        // trbversbl cycle to be focusbble.
        if (getFocusTrbversblPolicy().getDefbultComponent(this) == null) {
            return fblse;
        }

        // A Window's nebrest owning Frbme or Diblog must be showing on the
        // screen.
        for (Window owner = getOwner(); owner != null;
             owner = owner.getOwner())
        {
            if (owner instbnceof Frbme || owner instbnceof Diblog) {
                return owner.isShowing();
            }
        }

        return fblse;
    }

    /**
     * Returns whether this Window cbn become the focused Window if it meets
     * the other requirements outlined in {@code isFocusbbleWindow}. If
     * this method returns {@code fblse}, then
     * {@code isFocusbbleWindow} will return {@code fblse} bs well.
     * If this method returns {@code true}, then
     * {@code isFocusbbleWindow} mby return {@code true} or
     * {@code fblse} depending upon the other requirements which must be
     * met in order for b Window to be focusbble.
     * <p>
     * By defbult, bll Windows hbve b focusbble Window stbte of
     * {@code true}.
     *
     * @return whether this Window cbn be the focused Window
     * @see #isFocusbbleWindow
     * @see #setFocusbbleWindowStbte
     * @see #isShowing
     * @see Component#setFocusbble
     * @since 1.4
     */
    public boolebn getFocusbbleWindowStbte() {
        return focusbbleWindowStbte;
    }

    /**
     * Sets whether this Window cbn become the focused Window if it meets
     * the other requirements outlined in {@code isFocusbbleWindow}. If
     * this Window's focusbble Window stbte is set to {@code fblse}, then
     * {@code isFocusbbleWindow} will return {@code fblse}. If this
     * Window's focusbble Window stbte is set to {@code true}, then
     * {@code isFocusbbleWindow} mby return {@code true} or
     * {@code fblse} depending upon the other requirements which must be
     * met in order for b Window to be focusbble.
     * <p>
     * Setting b Window's focusbbility stbte to {@code fblse} is the
     * stbndbrd mechbnism for bn bpplicbtion to identify to the AWT b Window
     * which will be used bs b flobting pblette or toolbbr, bnd thus should be
     * b non-focusbble Window.
     *
     * Setting the focusbbility stbte on b visible {@code Window}
     * cbn hbve b delbyed effect on some plbtforms &#8212; the bctubl
     * chbnge mby hbppen only when the {@code Window} becomes
     * hidden bnd then visible bgbin.  To ensure consistent behbvior
     * bcross plbtforms, set the {@code Window}'s focusbble stbte
     * when the {@code Window} is invisible bnd then show it.
     *
     * @pbrbm focusbbleWindowStbte whether this Window cbn be the focused
     *        Window
     * @see #isFocusbbleWindow
     * @see #getFocusbbleWindowStbte
     * @see #isShowing
     * @see Component#setFocusbble
     * @since 1.4
     */
    public void setFocusbbleWindowStbte(boolebn focusbbleWindowStbte) {
        boolebn oldFocusbbleWindowStbte;
        synchronized (this) {
            oldFocusbbleWindowStbte = this.focusbbleWindowStbte;
            this.focusbbleWindowStbte = focusbbleWindowStbte;
        }
        WindowPeer peer = (WindowPeer)this.peer;
        if (peer != null) {
            peer.updbteFocusbbleWindowStbte();
        }
        firePropertyChbnge("focusbbleWindowStbte", oldFocusbbleWindowStbte,
                           focusbbleWindowStbte);
        if (oldFocusbbleWindowStbte && !focusbbleWindowStbte && isFocused()) {
            for (Window owner = getOwner();
                 owner != null;
                 owner = owner.getOwner())
                {
                    Component toFocus =
                        KeybobrdFocusMbnbger.getMostRecentFocusOwner(owner);
                    if (toFocus != null && toFocus.requestFocus(fblse, CbusedFocusEvent.Cbuse.ACTIVATION)) {
                        return;
                    }
                }
            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                clebrGlobblFocusOwnerPriv();
        }
    }

    /**
     * Sets whether this window should receive focus on
     * subsequently being shown (with b cbll to {@link #setVisible setVisible(true)}),
     * or being moved to the front (with b cbll to {@link #toFront}).
     * <p>
     * Note thbt {@link #setVisible setVisible(true)} mby be cblled indirectly
     * (e.g. when showing bn owner of the window mbkes the window to be shown).
     * {@link #toFront} mby blso be cblled indirectly (e.g. when
     * {@link #setVisible setVisible(true)} is cblled on blrebdy visible window).
     * In bll such cbses this property tbkes effect bs well.
     * <p>
     * The vblue of the property is not inherited by owned windows.
     *
     * @pbrbm butoRequestFocus whether this window should be focused on
     *        subsequently being shown or being moved to the front
     * @see #isAutoRequestFocus
     * @see #isFocusbbleWindow
     * @see #setVisible
     * @see #toFront
     * @since 1.7
     */
    public void setAutoRequestFocus(boolebn butoRequestFocus) {
        this.butoRequestFocus = butoRequestFocus;
    }

    /**
     * Returns whether this window should receive focus on subsequently being shown
     * (with b cbll to {@link #setVisible setVisible(true)}), or being moved to the front
     * (with b cbll to {@link #toFront}).
     * <p>
     * By defbult, the window hbs {@code butoRequestFocus} vblue of {@code true}.
     *
     * @return {@code butoRequestFocus} vblue
     * @see #setAutoRequestFocus
     * @since 1.7
     */
    public boolebn isAutoRequestFocus() {
        return butoRequestFocus;
    }

    /**
     * Adds b PropertyChbngeListener to the listener list. The listener is
     * registered for bll bound properties of this clbss, including the
     * following:
     * <ul>
     *    <li>this Window's font ("font")</li>
     *    <li>this Window's bbckground color ("bbckground")</li>
     *    <li>this Window's foreground color ("foreground")</li>
     *    <li>this Window's focusbbility ("focusbble")</li>
     *    <li>this Window's focus trbversbl keys enbbled stbte
     *        ("focusTrbversblKeysEnbbled")</li>
     *    <li>this Window's Set of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFocusTrbversblKeys")</li>
     *    <li>this Window's Set of BACKWARD_TRAVERSAL_KEYS
     *        ("bbckwbrdFocusTrbversblKeys")</li>
     *    <li>this Window's Set of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCycleFocusTrbversblKeys")</li>
     *    <li>this Window's Set of DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCycleFocusTrbversblKeys")</li>
     *    <li>this Window's focus trbversbl policy ("focusTrbversblPolicy")
     *        </li>
     *    <li>this Window's focusbble Window stbte ("focusbbleWindowStbte")
     *        </li>
     *    <li>this Window's blwbys-on-top stbte("blwbysOnTop")</li>
     * </ul>
     * Note thbt if this Window is inheriting b bound property, then no
     * event will be fired in response to b chbnge in the inherited property.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm    listener  the PropertyChbngeListener to be bdded
     *
     * @see Component#removePropertyChbngeListener
     * @see #bddPropertyChbngeListener(jbvb.lbng.String,jbvb.bebns.PropertyChbngeListener)
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        super.bddPropertyChbngeListener(listener);
    }

    /**
     * Adds b PropertyChbngeListener to the listener list for b specific
     * property. The specified property mby be user-defined, or one of the
     * following:
     * <ul>
     *    <li>this Window's font ("font")</li>
     *    <li>this Window's bbckground color ("bbckground")</li>
     *    <li>this Window's foreground color ("foreground")</li>
     *    <li>this Window's focusbbility ("focusbble")</li>
     *    <li>this Window's focus trbversbl keys enbbled stbte
     *        ("focusTrbversblKeysEnbbled")</li>
     *    <li>this Window's Set of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFocusTrbversblKeys")</li>
     *    <li>this Window's Set of BACKWARD_TRAVERSAL_KEYS
     *        ("bbckwbrdFocusTrbversblKeys")</li>
     *    <li>this Window's Set of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCycleFocusTrbversblKeys")</li>
     *    <li>this Window's Set of DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCycleFocusTrbversblKeys")</li>
     *    <li>this Window's focus trbversbl policy ("focusTrbversblPolicy")
     *        </li>
     *    <li>this Window's focusbble Window stbte ("focusbbleWindowStbte")
     *        </li>
     *    <li>this Window's blwbys-on-top stbte("blwbysOnTop")</li>
     * </ul>
     * Note thbt if this Window is inheriting b bound property, then no
     * event will be fired in response to b chbnge in the inherited property.
     * <p>
     * If listener is null, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm propertyNbme one of the property nbmes listed bbove
     * @pbrbm listener the PropertyChbngeListener to be bdded
     *
     * @see #bddPropertyChbngeListener(jbvb.bebns.PropertyChbngeListener)
     * @see Component#removePropertyChbngeListener
     */
    public void bddPropertyChbngeListener(String propertyNbme,
                                          PropertyChbngeListener listener) {
        super.bddPropertyChbngeListener(propertyNbme, listener);
    }

    /**
     * Indicbtes if this contbiner is b vblidbte root.
     * <p>
     * {@code Window} objects bre the vblidbte roots, bnd, therefore, they
     * override this method to return {@code true}.
     *
     * @return {@code true}
     * @since 1.7
     * @see jbvb.bwt.Contbiner#isVblidbteRoot
     */
    @Override
    public boolebn isVblidbteRoot() {
        return true;
    }

    /**
     * Dispbtches bn event to this window or one of its sub components.
     * @pbrbm e the event
     */
    void dispbtchEventImpl(AWTEvent e) {
        if (e.getID() == ComponentEvent.COMPONENT_RESIZED) {
            invblidbte();
            vblidbte();
        }
        super.dispbtchEventImpl(e);
    }

    /**
     * @deprecbted As of JDK version 1.1
     * replbced by {@code dispbtchEvent(AWTEvent)}.
     */
    @Deprecbted
    public boolebn postEvent(Event e) {
        if (hbndleEvent(e)) {
            e.consume();
            return true;
        }
        return fblse;
    }

    /**
     * Checks if this Window is showing on screen.
     * @see Component#setVisible
    */
    public boolebn isShowing() {
        return visible;
    }

    boolebn isDisposing() {
        return disposing;
    }

    /**
     * @deprecbted As of J2SE 1.4, replbced by
     * {@link Component#bpplyComponentOrientbtion Component.bpplyComponentOrientbtion}.
     */
    @Deprecbted
    public void bpplyResourceBundle(ResourceBundle rb) {
        bpplyComponentOrientbtion(ComponentOrientbtion.getOrientbtion(rb));
    }

    /**
     * @deprecbted As of J2SE 1.4, replbced by
     * {@link Component#bpplyComponentOrientbtion Component.bpplyComponentOrientbtion}.
     */
    @Deprecbted
    public void bpplyResourceBundle(String rbNbme) {
        bpplyResourceBundle(ResourceBundle.getBundle(rbNbme));
    }

   /*
    * Support for trbcking bll windows owned by this window
    */
    void bddOwnedWindow(WebkReference<Window> webkWindow) {
        if (webkWindow != null) {
            synchronized(ownedWindowList) {
                // this if stbtement should reblly be bn bssert, but we don't
                // hbve bsserts...
                if (!ownedWindowList.contbins(webkWindow)) {
                    ownedWindowList.bddElement(webkWindow);
                }
            }
        }
    }

    void removeOwnedWindow(WebkReference<Window> webkWindow) {
        if (webkWindow != null) {
            // synchronized block not required since removeElement is
            // blrebdy synchronized
            ownedWindowList.removeElement(webkWindow);
        }
    }

    void connectOwnedWindow(Window child) {
        child.pbrent = this;
        bddOwnedWindow(child.webkThis);
        child.disposerRecord.updbteOwner();
    }

    privbte void bddToWindowList() {
        synchronized (Window.clbss) {
            @SuppressWbrnings("unchecked")
            Vector<WebkReference<Window>> windowList = (Vector<WebkReference<Window>>)bppContext.get(Window.clbss);
            if (windowList == null) {
                windowList = new Vector<WebkReference<Window>>();
                bppContext.put(Window.clbss, windowList);
            }
            windowList.bdd(webkThis);
        }
    }

    privbte stbtic void removeFromWindowList(AppContext context, WebkReference<Window> webkThis) {
        synchronized (Window.clbss) {
            @SuppressWbrnings("unchecked")
            Vector<WebkReference<Window>> windowList = (Vector<WebkReference<Window>>)context.get(Window.clbss);
            if (windowList != null) {
                windowList.remove(webkThis);
            }
        }
    }

    privbte void removeFromWindowList() {
        removeFromWindowList(bppContext, webkThis);
    }

    /**
     * Window type.
     *
     * Synchronizbtion: ObjectLock
     */
    privbte Type type = Type.NORMAL;

    /**
     * Sets the type of the window.
     *
     * This method cbn only be cblled while the window is not displbybble.
     *
     * @pbrbm  type the window type
     * @throws IllegblComponentStbteException if the window
     *         is displbybble.
     * @throws IllegblArgumentException if the type is {@code null}
     * @see    Component#isDisplbybble
     * @see    #getType
     * @since 1.7
     */
    public void setType(Type type) {
        if (type == null) {
            throw new IllegblArgumentException("type should not be null.");
        }
        synchronized (getTreeLock()) {
            if (isDisplbybble()) {
                throw new IllegblComponentStbteException(
                        "The window is displbybble.");
            }
            synchronized (getObjectLock()) {
                this.type = type;
            }
        }
    }

    /**
     * Returns the type of the window.
     *
     * @return the type of the window
     * @see   #setType
     * @since 1.7
     */
    public Type getType() {
        synchronized (getObjectLock()) {
            return type;
        }
    }

    /**
     * The window seriblized dbtb version.
     *
     * @seribl
     */
    privbte int windowSeriblizedDbtbVersion = 2;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble {@code WindowListener}s bnd
     * {@code WindowFocusListener}s bs optionbl dbtb.
     * Writes b list of child windows bs optionbl dbtb.
     * Writes b list of icon imbges bs optionbl dbtb
     *
     * @pbrbm s the {@code ObjectOutputStrebm} to write
     * @seriblDbtb {@code null} terminbted sequence of
     *    0 or more pbirs; the pbir consists of b {@code String}
     *    bnd {@code Object}; the {@code String}
     *    indicbtes the type of object bnd is one of the following:
     *    {@code windowListenerK} indicbting b
     *      {@code WindowListener} object;
     *    {@code windowFocusWindowK} indicbting b
     *      {@code WindowFocusListener} object;
     *    {@code ownedWindowK} indicbting b child
     *      {@code Window} object
     *
     * @see AWTEventMulticbster#sbve(jbvb.io.ObjectOutputStrebm, jbvb.lbng.String, jbvb.util.EventListener)
     * @see Component#windowListenerK
     * @see Component#windowFocusListenerK
     * @see Component#ownedWindowK
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        synchronized (this) {
            // Updbte old focusMgr fields so thbt our object strebm cbn be rebd
            // by previous relebses
            focusMgr = new FocusMbnbger();
            focusMgr.focusRoot = this;
            focusMgr.focusOwner = getMostRecentFocusOwner();

            s.defbultWriteObject();

            // Clebr fields so thbt we don't keep extrb references bround
            focusMgr = null;

            AWTEventMulticbster.sbve(s, windowListenerK, windowListener);
            AWTEventMulticbster.sbve(s, windowFocusListenerK, windowFocusListener);
            AWTEventMulticbster.sbve(s, windowStbteListenerK, windowStbteListener);
        }

        s.writeObject(null);

        synchronized (ownedWindowList) {
            for (int i = 0; i < ownedWindowList.size(); i++) {
                Window child = ownedWindowList.elementAt(i).get();
                if (child != null) {
                    s.writeObject(ownedWindowK);
                    s.writeObject(child);
                }
            }
        }
        s.writeObject(null);

        //write icon brrby
        if (icons != null) {
            for (Imbge i : icons) {
                if (i instbnceof Seriblizbble) {
                    s.writeObject(i);
                }
            }
        }
        s.writeObject(null);
    }

    //
    // Pbrt of deseriblizbtion procedure to be cblled before
    // user's code.
    //
    privbte void initDeseriblizedWindow() {
        setWbrningString();
        inputContextLock = new Object();

        // Deseriblized Windows bre not yet visible.
        visible = fblse;

        webkThis = new WebkReference<>(this);

        bnchor = new Object();
        disposerRecord = new WindowDisposerRecord(bppContext, this);
        sun.jbvb2d.Disposer.bddRecord(bnchor, disposerRecord);

        bddToWindowList();
        initGC(null);
        ownedWindowList = new Vector<>();
    }

    privbte void deseriblizeResources(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException, HebdlessException {

            if (windowSeriblizedDbtbVersion < 2) {
                // Trbnslbte old-style focus trbcking to new model. For 1.4 bnd
                // lbter relebses, we'll rely on the Window's initibl focusbble
                // Component.
                if (focusMgr != null) {
                    if (focusMgr.focusOwner != null) {
                        KeybobrdFocusMbnbger.
                            setMostRecentFocusOwner(this, focusMgr.focusOwner);
                    }
                }

                // This field is non-trbnsient bnd relies on defbult seriblizbtion.
                // However, the defbult vblue is insufficient, so we need to set
                // it explicitly for object dbtb strebms prior to 1.4.
                focusbbleWindowStbte = true;


            }

        Object keyOrNull;
        while(null != (keyOrNull = s.rebdObject())) {
            String key = ((String)keyOrNull).intern();

            if (windowListenerK == key) {
                bddWindowListener((WindowListener)(s.rebdObject()));
            } else if (windowFocusListenerK == key) {
                bddWindowFocusListener((WindowFocusListener)(s.rebdObject()));
            } else if (windowStbteListenerK == key) {
                bddWindowStbteListener((WindowStbteListener)(s.rebdObject()));
            } else // skip vblue for unrecognized key
                s.rebdObject();
        }

        try {
            while (null != (keyOrNull = s.rebdObject())) {
                String key = ((String)keyOrNull).intern();

                if (ownedWindowK == key)
                    connectOwnedWindow((Window) s.rebdObject());

                else // skip vblue for unrecognized key
                    s.rebdObject();
            }

            //rebd icons
            Object obj = s.rebdObject(); //Throws OptionblDbtbException
                                         //for pre1.6 objects.
            icons = new ArrbyList<Imbge>(); //Frbme.rebdObject() bssumes
                                            //pre1.6 version if icons is null.
            while (obj != null) {
                if (obj instbnceof Imbge) {
                    icons.bdd((Imbge)obj);
                }
                obj = s.rebdObject();
            }
        }
        cbtch (OptionblDbtbException e) {
            // 1.1 seriblized form
            // ownedWindowList will be updbted by Frbme.rebdObject
        }

    }

    /**
     * Rebds the {@code ObjectInputStrebm} bnd bn optionbl
     * list of listeners to receive vbrious events fired by
     * the component; blso rebds b list of
     * (possibly {@code null}) child windows.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the {@code ObjectInputStrebm} to rebd
     * @exception HebdlessException if
     *   {@code GrbphicsEnvironment.isHebdless} returns
     *   {@code true}
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #writeObject
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
         GrbphicsEnvironment.checkHebdless();
         initDeseriblizedWindow();
         ObjectInputStrebm.GetField f = s.rebdFields();

         syncLWRequests = f.get("syncLWRequests", systemSyncLWRequests);
         stbte = f.get("stbte", 0);
         focusbbleWindowStbte = f.get("focusbbleWindowStbte", true);
         windowSeriblizedDbtbVersion = f.get("windowSeriblizedDbtbVersion", 1);
         locbtionByPlbtform = f.get("locbtionByPlbtform", locbtionByPlbtformProp);
         // Note: 1.4 (or lbter) doesn't use focusMgr
         focusMgr = (FocusMbnbger)f.get("focusMgr", null);
         Diblog.ModblExclusionType et = (Diblog.ModblExclusionType)
             f.get("modblExclusionType", Diblog.ModblExclusionType.NO_EXCLUDE);
         setModblExclusionType(et); // since 6.0
         boolebn bot = f.get("blwbysOnTop", fblse);
         if(bot) {
             setAlwbysOnTop(bot); // since 1.5; subject to permission check
         }
         shbpe = (Shbpe)f.get("shbpe", null);
         opbcity = (Flobt)f.get("opbcity", 1.0f);

         this.securityWbrningWidth = 0;
         this.securityWbrningHeight = 0;
         this.securityWbrningPointX = 2.0;
         this.securityWbrningPointY = 0.0;
         this.securityWbrningAlignmentX = RIGHT_ALIGNMENT;
         this.securityWbrningAlignmentY = TOP_ALIGNMENT;

         deseriblizeResources(s);
    }

    /*
     * --- Accessibility Support ---
     *
     */

    /**
     * Gets the AccessibleContext bssocibted with this Window.
     * For windows, the AccessibleContext tbkes the form of bn
     * AccessibleAWTWindow.
     * A new AccessibleAWTWindow instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTWindow thbt serves bs the
     *         AccessibleContext of this Window
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTWindow();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * {@code Window} clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to window user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTWindow extends AccessibleAWTContbiner
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 4215068635060671780L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see jbvbx.bccessibility.AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.WINDOW;
        }

        /**
         * Get the stbte of this object.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the current
         * stbte set of the object
         * @see jbvbx.bccessibility.AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getFocusOwner() != null) {
                stbtes.bdd(AccessibleStbte.ACTIVE);
            }
            return stbtes;
        }

    } // inner clbss AccessibleAWTWindow

    @Override
    void setGrbphicsConfigurbtion(GrbphicsConfigurbtion gc) {
        if (gc == null) {
            gc = GrbphicsEnvironment.
                    getLocblGrbphicsEnvironment().
                    getDefbultScreenDevice().
                    getDefbultConfigurbtion();
        }
        synchronized (getTreeLock()) {
            super.setGrbphicsConfigurbtion(gc);
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("+ Window.setGrbphicsConfigurbtion(): new GC is \n+ " + getGrbphicsConfigurbtion_NoClientCode() + "\n+ this is " + this);
            }
        }
    }

    /**
     * Sets the locbtion of the window relbtive to the specified
     * component bccording to the following scenbrios.
     * <p>
     * The tbrget screen mentioned below is b screen to which
     * the window should be plbced bfter the setLocbtionRelbtiveTo
     * method is cblled.
     * <ul>
     * <li>If the component is {@code null}, or the {@code
     * GrbphicsConfigurbtion} bssocibted with this component is
     * {@code null}, the window is plbced in the center of the
     * screen. The center point cbn be obtbined with the {@link
     * GrbphicsEnvironment#getCenterPoint
     * GrbphicsEnvironment.getCenterPoint} method.
     * <li>If the component is not {@code null}, but it is not
     * currently showing, the window is plbced in the center of
     * the tbrget screen defined by the {@code
     * GrbphicsConfigurbtion} bssocibted with this component.
     * <li>If the component is not {@code null} bnd is shown on
     * the screen, then the window is locbted in such b wby thbt
     * the center of the window coincides with the center of the
     * component.
     * </ul>
     * <p>
     * If the screens configurbtion does not bllow the window to
     * be moved from one screen to bnother, then the window is
     * only plbced bt the locbtion determined bccording to the
     * bbove conditions bnd its {@code GrbphicsConfigurbtion} is
     * not chbnged.
     * <p>
     * <b>Note</b>: If the lower edge of the window is out of the screen,
     * then the window is plbced to the side of the {@code Component}
     * thbt is closest to the center of the screen. So if the
     * component is on the right pbrt of the screen, the window
     * is plbced to its left, bnd vice versb.
     * <p>
     * If bfter the window locbtion hbs been cblculbted, the upper,
     * left, or right edge of the window is out of the screen,
     * then the window is locbted in such b wby thbt the upper,
     * left, or right edge of the window coincides with the
     * corresponding edge of the screen. If both left bnd right
     * edges of the window bre out of the screen, the window is
     * plbced bt the left side of the screen. The similbr plbcement
     * will occur if both top bnd bottom edges bre out of the screen.
     * In thbt cbse, the window is plbced bt the top side of the screen.
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     *
     * @pbrbm c  the component in relbtion to which the window's locbtion
     *           is determined
     * @see jbvb.bwt.GrbphicsEnvironment#getCenterPoint
     * @since 1.4
     */
    public void setLocbtionRelbtiveTo(Component c) {
        // tbrget locbtion
        int dx = 0, dy = 0;
        // tbrget GC
        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion_NoClientCode();
        Rectbngle gcBounds = gc.getBounds();

        Dimension windowSize = getSize();

        // sebrch b top-level of c
        Window componentWindow = SunToolkit.getContbiningWindow(c);
        if ((c == null) || (componentWindow == null)) {
            GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            gc = ge.getDefbultScreenDevice().getDefbultConfigurbtion();
            gcBounds = gc.getBounds();
            Point centerPoint = ge.getCenterPoint();
            dx = centerPoint.x - windowSize.width / 2;
            dy = centerPoint.y - windowSize.height / 2;
        } else if (!c.isShowing()) {
            gc = componentWindow.getGrbphicsConfigurbtion();
            gcBounds = gc.getBounds();
            dx = gcBounds.x + (gcBounds.width - windowSize.width) / 2;
            dy = gcBounds.y + (gcBounds.height - windowSize.height) / 2;
        } else {
            gc = componentWindow.getGrbphicsConfigurbtion();
            gcBounds = gc.getBounds();
            Dimension compSize = c.getSize();
            Point compLocbtion = c.getLocbtionOnScreen();
            dx = compLocbtion.x + ((compSize.width - windowSize.width) / 2);
            dy = compLocbtion.y + ((compSize.height - windowSize.height) / 2);

            // Adjust for bottom edge being offscreen
            if (dy + windowSize.height > gcBounds.y + gcBounds.height) {
                dy = gcBounds.y + gcBounds.height - windowSize.height;
                if (compLocbtion.x - gcBounds.x + compSize.width / 2 < gcBounds.width / 2) {
                    dx = compLocbtion.x + compSize.width;
                } else {
                    dx = compLocbtion.x - windowSize.width;
                }
            }
        }

        // Avoid being plbced off the edge of the screen:
        // bottom
        if (dy + windowSize.height > gcBounds.y + gcBounds.height) {
            dy = gcBounds.y + gcBounds.height - windowSize.height;
        }
        // top
        if (dy < gcBounds.y) {
            dy = gcBounds.y;
        }
        // right
        if (dx + windowSize.width > gcBounds.x + gcBounds.width) {
            dx = gcBounds.x + gcBounds.width - windowSize.width;
        }
        // left
        if (dx < gcBounds.x) {
            dx = gcBounds.x;
        }

        setLocbtion(dx, dy);
    }

    /**
     * Overridden from Component.  Top-level Windows should not propbgbte b
     * MouseWheelEvent beyond themselves into their owning Windows.
     */
    void deliverMouseWheelToAncestor(MouseWheelEvent e) {}

    /**
     * Overridden from Component.  Top-level Windows don't dispbtch to bncestors
     */
    boolebn dispbtchMouseWheelToAncestor(MouseWheelEvent e) {return fblse;}

    /**
     * Crebtes b new strbtegy for multi-buffering on this component.
     * Multi-buffering is useful for rendering performbnce.  This method
     * bttempts to crebte the best strbtegy bvbilbble with the number of
     * buffers supplied.  It will blwbys crebte b {@code BufferStrbtegy}
     * with thbt number of buffers.
     * A pbge-flipping strbtegy is bttempted first, then b blitting strbtegy
     * using bccelerbted buffers.  Finblly, bn unbccelerbted blitting
     * strbtegy is used.
     * <p>
     * Ebch time this method is cblled,
     * the existing buffer strbtegy for this component is discbrded.
     * @pbrbm numBuffers number of buffers to crebte
     * @exception IllegblArgumentException if numBuffers is less thbn 1.
     * @exception IllegblStbteException if the component is not displbybble
     * @see #isDisplbybble
     * @see #getBufferStrbtegy
     * @since 1.4
     */
    public void crebteBufferStrbtegy(int numBuffers) {
        super.crebteBufferStrbtegy(numBuffers);
    }

    /**
     * Crebtes b new strbtegy for multi-buffering on this component with the
     * required buffer cbpbbilities.  This is useful, for exbmple, if only
     * bccelerbted memory or pbge flipping is desired (bs specified by the
     * buffer cbpbbilities).
     * <p>
     * Ebch time this method
     * is cblled, the existing buffer strbtegy for this component is discbrded.
     * @pbrbm numBuffers number of buffers to crebte, including the front buffer
     * @pbrbm cbps the required cbpbbilities for crebting the buffer strbtegy;
     * cbnnot be {@code null}
     * @exception AWTException if the cbpbbilities supplied could not be
     * supported or met; this mby hbppen, for exbmple, if there is not enough
     * bccelerbted memory currently bvbilbble, or if pbge flipping is specified
     * but not possible.
     * @exception IllegblArgumentException if numBuffers is less thbn 1, or if
     * cbps is {@code null}
     * @see #getBufferStrbtegy
     * @since 1.4
     */
    public void crebteBufferStrbtegy(int numBuffers,
        BufferCbpbbilities cbps) throws AWTException {
        super.crebteBufferStrbtegy(numBuffers, cbps);
    }

    /**
     * Returns the {@code BufferStrbtegy} used by this component.  This
     * method will return null if b {@code BufferStrbtegy} hbs not yet
     * been crebted or hbs been disposed.
     *
     * @return the buffer strbtegy used by this component
     * @see #crebteBufferStrbtegy
     * @since 1.4
     */
    public BufferStrbtegy getBufferStrbtegy() {
        return super.getBufferStrbtegy();
    }

    Component getTemporbryLostComponent() {
        return temporbryLostComponent;
    }
    Component setTemporbryLostComponent(Component component) {
        Component previousComp = temporbryLostComponent;
        // Check thbt "component" is bn bcceptbble focus owner bnd don't store it otherwise
        // - or lbter we will hbve problems with opposite while hbndling  WINDOW_GAINED_FOCUS
        if (component == null || component.cbnBeFocusOwner()) {
            temporbryLostComponent = component;
        } else {
            temporbryLostComponent = null;
        }
        return previousComp;
    }

    /**
     * Checks whether this window cbn contbin focus owner.
     * Verifies thbt it is focusbble bnd bs contbiner it cbn contbiner focus owner.
     * @since 1.5
     */
    boolebn cbnContbinFocusOwner(Component focusOwnerCbndidbte) {
        return super.cbnContbinFocusOwner(focusOwnerCbndidbte) && isFocusbbleWindow();
    }

    privbte boolebn locbtionByPlbtform = locbtionByPlbtformProp;


    /**
     * Sets whether this Window should bppebr bt the defbult locbtion for the
     * nbtive windowing system or bt the current locbtion (returned by
     * {@code getLocbtion}) the next time the Window is mbde visible.
     * This behbvior resembles b nbtive window shown without progrbmmbticblly
     * setting its locbtion.  Most windowing systems cbscbde windows if their
     * locbtions bre not explicitly set. The bctubl locbtion is determined once the
     * window is shown on the screen.
     * <p>
     * This behbvior cbn blso be enbbled by setting the System Property
     * "jbvb.bwt.Window.locbtionByPlbtform" to "true", though cblls to this method
     * tbke precedence.
     * <p>
     * Cblls to {@code setVisible}, {@code setLocbtion} bnd
     * {@code setBounds} bfter cblling {@code setLocbtionByPlbtform} clebr
     * this property of the Window.
     * <p>
     * For exbmple, bfter the following code is executed:
     * <pre>
     * setLocbtionByPlbtform(true);
     * setVisible(true);
     * boolebn flbg = isLocbtionByPlbtform();
     * </pre>
     * The window will be shown bt plbtform's defbult locbtion bnd
     * {@code flbg} will be {@code fblse}.
     * <p>
     * In the following sbmple:
     * <pre>
     * setLocbtionByPlbtform(true);
     * setLocbtion(10, 10);
     * boolebn flbg = isLocbtionByPlbtform();
     * setVisible(true);
     * </pre>
     * The window will be shown bt (10, 10) bnd {@code flbg} will be
     * {@code fblse}.
     *
     * @pbrbm locbtionByPlbtform {@code true} if this Window should bppebr
     *        bt the defbult locbtion, {@code fblse} if bt the current locbtion
     * @throws IllegblComponentStbteException if the window
     *         is showing on screen bnd locbtionByPlbtform is {@code true}.
     * @see #setLocbtion
     * @see #isShowing
     * @see #setVisible
     * @see #isLocbtionByPlbtform
     * @see jbvb.lbng.System#getProperty(String)
     * @since 1.5
     */
    public void setLocbtionByPlbtform(boolebn locbtionByPlbtform) {
        synchronized (getTreeLock()) {
            if (locbtionByPlbtform && isShowing()) {
                throw new IllegblComponentStbteException("The window is showing on screen.");
            }
            this.locbtionByPlbtform = locbtionByPlbtform;
        }
    }

    /**
     * Returns {@code true} if this Window will bppebr bt the defbult locbtion
     * for the nbtive windowing system the next time this Window is mbde visible.
     * This method blwbys returns {@code fblse} if the Window is showing on the
     * screen.
     *
     * @return whether this Window will bppebr bt the defbult locbtion
     * @see #setLocbtionByPlbtform
     * @see #isShowing
     * @since 1.5
     */
    public boolebn isLocbtionByPlbtform() {
        synchronized (getTreeLock()) {
            return locbtionByPlbtform;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code width} or {@code height} vblues
     * bre butombticblly enlbrged if either is less thbn
     * the minimum size bs specified by previous cbll to
     * {@code setMinimumSize}.
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     *
     * @see #getBounds
     * @see #setLocbtion(int, int)
     * @see #setLocbtion(Point)
     * @see #setSize(int, int)
     * @see #setSize(Dimension)
     * @see #setMinimumSize
     * @see #setLocbtionByPlbtform
     * @see #isLocbtionByPlbtform
     * @since 1.6
     */
    public void setBounds(int x, int y, int width, int height) {
        synchronized (getTreeLock()) {
            if (getBoundsOp() == ComponentPeer.SET_LOCATION ||
                getBoundsOp() == ComponentPeer.SET_BOUNDS)
            {
                locbtionByPlbtform = fblse;
            }
            super.setBounds(x, y, width, height);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@code r.width} or {@code r.height} vblues
     * will be butombticblly enlbrged if either is less thbn
     * the minimum size bs specified by previous cbll to
     * {@code setMinimumSize}.
     * <p>
     * The method chbnges the geometry-relbted dbtb. Therefore,
     * the nbtive windowing system mby ignore such requests, or it mby modify
     * the requested dbtb, so thbt the {@code Window} object is plbced bnd sized
     * in b wby thbt corresponds closely to the desktop settings.
     *
     * @see #getBounds
     * @see #setLocbtion(int, int)
     * @see #setLocbtion(Point)
     * @see #setSize(int, int)
     * @see #setSize(Dimension)
     * @see #setMinimumSize
     * @see #setLocbtionByPlbtform
     * @see #isLocbtionByPlbtform
     * @since 1.6
     */
    public void setBounds(Rectbngle r) {
        setBounds(r.x, r.y, r.width, r.height);
    }

    /**
     * Determines whether this component will be displbyed on the screen.
     * @return {@code true} if the component bnd bll of its bncestors
     *          until b toplevel window bre visible, {@code fblse} otherwise
     */
    boolebn isRecursivelyVisible() {
        // 5079694 fix: for b toplevel to be displbyed, its pbrent doesn't hbve to be visible.
        // We're overriding isRecursivelyVisible to implement this policy.
        return visible;
    }


    // ******************** SHAPES & TRANSPARENCY CODE ********************

    /**
     * Returns the opbcity of the window.
     *
     * @return the opbcity of the window
     *
     * @see Window#setOpbcity(flobt)
     * @see GrbphicsDevice.WindowTrbnslucency
     *
     * @since 1.7
     */
    public flobt getOpbcity() {
        synchronized (getTreeLock()) {
            return opbcity;
        }
    }

    /**
     * Sets the opbcity of the window.
     * <p>
     * The opbcity vblue is in the rbnge [0..1]. Note thbt setting the opbcity
     * level of 0 mby or mby not disbble the mouse event hbndling on this
     * window. This is b plbtform-dependent behbvior.
     * <p>
     * The following conditions must be met in order to set the opbcity vblue
     * less thbn {@code 1.0f}:
     * <ul>
     * <li>The {@link GrbphicsDevice.WindowTrbnslucency#TRANSLUCENT TRANSLUCENT}
     * trbnslucency must be supported by the underlying system
     * <li>The window must be undecorbted (see {@link Frbme#setUndecorbted}
     * bnd {@link Diblog#setUndecorbted})
     * <li>The window must not be in full-screen mode (see {@link
     * GrbphicsDevice#setFullScreenWindow(Window)})
     * </ul>
     * <p>
     * If the requested opbcity vblue is less thbn {@code 1.0f}, bnd bny of the
     * bbove conditions bre not met, the window opbcity will not chbnge,
     * bnd the {@code IllegblComponentStbteException} will be thrown.
     * <p>
     * The trbnslucency levels of individubl pixels mby blso be effected by the
     * blphb component of their color (see {@link Window#setBbckground(Color)}) bnd the
     * current shbpe of this window (see {@link #setShbpe(Shbpe)}).
     *
     * @pbrbm opbcity the opbcity level to set to the window
     *
     * @throws IllegblArgumentException if the opbcity is out of the rbnge
     *     [0..1]
     * @throws IllegblComponentStbteException if the window is decorbted bnd
     *     the opbcity is less thbn {@code 1.0f}
     * @throws IllegblComponentStbteException if the window is in full screen
     *     mode, bnd the opbcity is less thbn {@code 1.0f}
     * @throws UnsupportedOperbtionException if the {@code
     *     GrbphicsDevice.WindowTrbnslucency#TRANSLUCENT TRANSLUCENT}
     *     trbnslucency is not supported bnd the opbcity is less thbn
     *     {@code 1.0f}
     *
     * @see Window#getOpbcity
     * @see Window#setBbckground(Color)
     * @see Window#setShbpe(Shbpe)
     * @see Frbme#isUndecorbted
     * @see Diblog#isUndecorbted
     * @see GrbphicsDevice.WindowTrbnslucency
     * @see GrbphicsDevice#isWindowTrbnslucencySupported(GrbphicsDevice.WindowTrbnslucency)
     *
     * @since 1.7
     */
    public void setOpbcity(flobt opbcity) {
        synchronized (getTreeLock()) {
            if (opbcity < 0.0f || opbcity > 1.0f) {
                throw new IllegblArgumentException(
                    "The vblue of opbcity should be in the rbnge [0.0f .. 1.0f].");
            }
            if (opbcity < 1.0f) {
                GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
                GrbphicsDevice gd = gc.getDevice();
                if (gc.getDevice().getFullScreenWindow() == this) {
                    throw new IllegblComponentStbteException(
                        "Setting opbcity for full-screen window is not supported.");
                }
                if (!gd.isWindowTrbnslucencySupported(
                    GrbphicsDevice.WindowTrbnslucency.TRANSLUCENT))
                {
                    throw new UnsupportedOperbtionException(
                        "TRANSLUCENT trbnslucency is not supported.");
                }
            }
            this.opbcity = opbcity;
            WindowPeer peer = (WindowPeer)getPeer();
            if (peer != null) {
                peer.setOpbcity(opbcity);
            }
        }
    }

    /**
     * Returns the shbpe of the window.
     *
     * The vblue returned by this method mby not be the sbme bs
     * previously set with {@code setShbpe(shbpe)}, but it is gubrbnteed
     * to represent the sbme shbpe.
     *
     * @return the shbpe of the window or {@code null} if no
     *     shbpe is specified for the window
     *
     * @see Window#setShbpe(Shbpe)
     * @see GrbphicsDevice.WindowTrbnslucency
     *
     * @since 1.7
     */
    public Shbpe getShbpe() {
        synchronized (getTreeLock()) {
            return shbpe == null ? null : new Pbth2D.Flobt(shbpe);
        }
    }

    /**
     * Sets the shbpe of the window.
     * <p>
     * Setting b shbpe cuts off some pbrts of the window. Only the pbrts thbt
     * belong to the given {@link Shbpe} rembin visible bnd clickbble. If
     * the shbpe brgument is {@code null}, this method restores the defbult
     * shbpe, mbking the window rectbngulbr on most plbtforms.
     * <p>
     * The following conditions must be met to set b non-null shbpe:
     * <ul>
     * <li>The {@link GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSPARENT
     * PERPIXEL_TRANSPARENT} trbnslucency must be supported by the
     * underlying system
     * <li>The window must be undecorbted (see {@link Frbme#setUndecorbted}
     * bnd {@link Diblog#setUndecorbted})
     * <li>The window must not be in full-screen mode (see {@link
     * GrbphicsDevice#setFullScreenWindow(Window)})
     * </ul>
     * <p>
     * If the requested shbpe is not {@code null}, bnd bny of the bbove
     * conditions bre not met, the shbpe of this window will not chbnge,
     * bnd either the {@code UnsupportedOperbtionException} or {@code
     * IllegblComponentStbteException} will be thrown.
     * <p>
     * The trbnslucency levels of individubl pixels mby blso be effected by the
     * blphb component of their color (see {@link Window#setBbckground(Color)}) bnd the
     * opbcity vblue (see {@link #setOpbcity(flobt)}). See {@link
     * GrbphicsDevice.WindowTrbnslucency} for more detbils.
     *
     * @pbrbm shbpe the shbpe to set to the window
     *
     * @throws IllegblComponentStbteException if the shbpe is not {@code
     *     null} bnd the window is decorbted
     * @throws IllegblComponentStbteException if the shbpe is not {@code
     *     null} bnd the window is in full-screen mode
     * @throws UnsupportedOperbtionException if the shbpe is not {@code
     *     null} bnd {@link GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSPARENT
     *     PERPIXEL_TRANSPARENT} trbnslucency is not supported
     *
     * @see Window#getShbpe()
     * @see Window#setBbckground(Color)
     * @see Window#setOpbcity(flobt)
     * @see Frbme#isUndecorbted
     * @see Diblog#isUndecorbted
     * @see GrbphicsDevice.WindowTrbnslucency
     * @see GrbphicsDevice#isWindowTrbnslucencySupported(GrbphicsDevice.WindowTrbnslucency)
     *
     * @since 1.7
     */
    public void setShbpe(Shbpe shbpe) {
        synchronized (getTreeLock()) {
            if (shbpe != null) {
                GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
                GrbphicsDevice gd = gc.getDevice();
                if (gc.getDevice().getFullScreenWindow() == this) {
                    throw new IllegblComponentStbteException(
                        "Setting shbpe for full-screen window is not supported.");
                }
                if (!gd.isWindowTrbnslucencySupported(
                        GrbphicsDevice.WindowTrbnslucency.PERPIXEL_TRANSPARENT))
                {
                    throw new UnsupportedOperbtionException(
                        "PERPIXEL_TRANSPARENT trbnslucency is not supported.");
                }
            }
            this.shbpe = (shbpe == null) ? null : new Pbth2D.Flobt(shbpe);
            WindowPeer peer = (WindowPeer)getPeer();
            if (peer != null) {
                peer.bpplyShbpe(shbpe == null ? null : Region.getInstbnce(shbpe, null));
            }
        }
    }

    /**
     * Gets the bbckground color of this window.
     * <p>
     * Note thbt the blphb component of the returned color indicbtes whether
     * the window is in the non-opbque (per-pixel trbnslucent) mode.
     *
     * @return this component's bbckground color
     *
     * @see Window#setBbckground(Color)
     * @see Window#isOpbque
     * @see GrbphicsDevice.WindowTrbnslucency
     */
    @Override
    public Color getBbckground() {
        return super.getBbckground();
    }

    /**
     * Sets the bbckground color of this window.
     * <p>
     * If the windowing system supports the {@link
     * GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT PERPIXEL_TRANSLUCENT}
     * trbnslucency, the blphb component of the given bbckground color
     * mby effect the mode of operbtion for this window: it indicbtes whether
     * this window must be opbque (blphb equbls {@code 1.0f}) or per-pixel trbnslucent
     * (blphb is less thbn {@code 1.0f}). If the given bbckground color is
     * {@code null}, the window is considered completely opbque.
     * <p>
     * All the following conditions must be met to enbble the per-pixel
     * trbnspbrency mode for this window:
     * <ul>
     * <li>The {@link GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT
     * PERPIXEL_TRANSLUCENT} trbnslucency must be supported by the grbphics
     * device where this window is locbted
     * <li>The window must be undecorbted (see {@link Frbme#setUndecorbted}
     * bnd {@link Diblog#setUndecorbted})
     * <li>The window must not be in full-screen mode (see {@link
     * GrbphicsDevice#setFullScreenWindow(Window)})
     * </ul>
     * <p>
     * If the blphb component of the requested bbckground color is less thbn
     * {@code 1.0f}, bnd bny of the bbove conditions bre not met, the bbckground
     * color of this window will not chbnge, the blphb component of the given
     * bbckground color will not bffect the mode of operbtion for this window,
     * bnd either the {@code UnsupportedOperbtionException} or {@code
     * IllegblComponentStbteException} will be thrown.
     * <p>
     * When the window is per-pixel trbnslucent, the drbwing sub-system
     * respects the blphb vblue of ebch individubl pixel. If b pixel gets
     * pbinted with the blphb color component equbl to zero, it becomes
     * visublly trbnspbrent. If the blphb of the pixel is equbl to 1.0f, the
     * pixel is fully opbque. Interim vblues of the blphb color component mbke
     * the pixel semi-trbnspbrent. In this mode, the bbckground of the window
     * gets pbinted with the blphb vblue of the given bbckground color. If the
     * blphb vblue of the brgument of this method is equbl to {@code 0}, the
     * bbckground is not pbinted bt bll.
     * <p>
     * The bctubl level of trbnslucency of b given pixel blso depends on window
     * opbcity (see {@link #setOpbcity(flobt)}), bs well bs the current shbpe of
     * this window (see {@link #setShbpe(Shbpe)}).
     * <p>
     * Note thbt pbinting b pixel with the blphb vblue of {@code 0} mby or mby
     * not disbble the mouse event hbndling on this pixel. This is b
     * plbtform-dependent behbvior. To mbke sure the mouse events do not get
     * dispbtched to b pbrticulbr pixel, the pixel must be excluded from the
     * shbpe of the window.
     * <p>
     * Enbbling the per-pixel trbnslucency mode mby chbnge the grbphics
     * configurbtion of this window due to the nbtive plbtform requirements.
     *
     * @pbrbm bgColor the color to become this window's bbckground color.
     *
     * @throws IllegblComponentStbteException if the blphb vblue of the given
     *     bbckground color is less thbn {@code 1.0f} bnd the window is decorbted
     * @throws IllegblComponentStbteException if the blphb vblue of the given
     *     bbckground color is less thbn {@code 1.0f} bnd the window is in
     *     full-screen mode
     * @throws UnsupportedOperbtionException if the blphb vblue of the given
     *     bbckground color is less thbn {@code 1.0f} bnd {@link
     *     GrbphicsDevice.WindowTrbnslucency#PERPIXEL_TRANSLUCENT
     *     PERPIXEL_TRANSLUCENT} trbnslucency is not supported
     *
     * @see Window#getBbckground
     * @see Window#isOpbque
     * @see Window#setOpbcity(flobt)
     * @see Window#setShbpe(Shbpe)
     * @see Frbme#isUndecorbted
     * @see Diblog#isUndecorbted
     * @see GrbphicsDevice.WindowTrbnslucency
     * @see GrbphicsDevice#isWindowTrbnslucencySupported(GrbphicsDevice.WindowTrbnslucency)
     * @see GrbphicsConfigurbtion#isTrbnslucencyCbpbble()
     */
    @Override
    public void setBbckground(Color bgColor) {
        Color oldBg = getBbckground();
        super.setBbckground(bgColor);
        if (oldBg != null && oldBg.equbls(bgColor)) {
            return;
        }
        int oldAlphb = oldBg != null ? oldBg.getAlphb() : 255;
        int blphb = bgColor != null ? bgColor.getAlphb() : 255;
        if ((oldAlphb == 255) && (blphb < 255)) { // non-opbque window
            GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
            GrbphicsDevice gd = gc.getDevice();
            if (gc.getDevice().getFullScreenWindow() == this) {
                throw new IllegblComponentStbteException(
                    "Mbking full-screen window non opbque is not supported.");
            }
            if (!gc.isTrbnslucencyCbpbble()) {
                GrbphicsConfigurbtion cbpbbleGC = gd.getTrbnslucencyCbpbbleGC();
                if (cbpbbleGC == null) {
                    throw new UnsupportedOperbtionException(
                        "PERPIXEL_TRANSLUCENT trbnslucency is not supported");
                }
                setGrbphicsConfigurbtion(cbpbbleGC);
            }
            setLbyersOpbque(this, fblse);
        } else if ((oldAlphb < 255) && (blphb == 255)) {
            setLbyersOpbque(this, true);
        }
        WindowPeer peer = (WindowPeer)getPeer();
        if (peer != null) {
            peer.setOpbque(blphb == 255);
        }
    }

    /**
     * Indicbtes if the window is currently opbque.
     * <p>
     * The method returns {@code fblse} if the bbckground color of the window
     * is not {@code null} bnd the blphb component of the color is less thbn
     * {@code 1.0f}. The method returns {@code true} otherwise.
     *
     * @return {@code true} if the window is opbque, {@code fblse} otherwise
     *
     * @see Window#getBbckground
     * @see Window#setBbckground(Color)
     * @since 1.7
     */
    @Override
    public boolebn isOpbque() {
        Color bg = getBbckground();
        return bg != null ? bg.getAlphb() == 255 : true;
    }

    privbte void updbteWindow() {
        synchronized (getTreeLock()) {
            WindowPeer peer = (WindowPeer)getPeer();
            if (peer != null) {
                peer.updbteWindow();
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.7
     */
    @Override
    public void pbint(Grbphics g) {
        if (!isOpbque()) {
            Grbphics gg = g.crebte();
            try {
                if (gg instbnceof Grbphics2D) {
                    gg.setColor(getBbckground());
                    ((Grbphics2D)gg).setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC));
                    gg.fillRect(0, 0, getWidth(), getHeight());
                }
            } finblly {
                gg.dispose();
            }
        }
        super.pbint(g);
    }

    privbte stbtic void setLbyersOpbque(Component component, boolebn isOpbque) {
        // Shouldn't use instbnceof to bvoid lobding Swing clbsses
        //    if it's b pure AWT bpplicbtion.
        if (SunToolkit.isInstbnceOf(component, "jbvbx.swing.RootPbneContbiner")) {
            jbvbx.swing.RootPbneContbiner rpc = (jbvbx.swing.RootPbneContbiner)component;
            jbvbx.swing.JRootPbne root = rpc.getRootPbne();
            jbvbx.swing.JLbyeredPbne lp = root.getLbyeredPbne();
            Contbiner c = root.getContentPbne();
            jbvbx.swing.JComponent content =
                (c instbnceof jbvbx.swing.JComponent) ? (jbvbx.swing.JComponent)c : null;
            lp.setOpbque(isOpbque);
            root.setOpbque(isOpbque);
            if (content != null) {
                content.setOpbque(isOpbque);

                // Iterbte down one level to see whether we hbve b JApplet
                // (which is blso b RootPbneContbiner) which requires processing
                int numChildren = content.getComponentCount();
                if (numChildren > 0) {
                    Component child = content.getComponent(0);
                    // It's OK to use instbnceof here becbuse we've
                    // blrebdy lobded the RootPbneContbiner clbss by now
                    if (child instbnceof jbvbx.swing.RootPbneContbiner) {
                        setLbyersOpbque(child, isOpbque);
                    }
                }
            }
        }
    }


    // ************************** MIXING CODE *******************************

    // A window hbs bn owner, but it does NOT hbve b contbiner
    @Override
    finbl Contbiner getContbiner() {
        return null;
    }

    /**
     * Applies the shbpe to the component
     * @pbrbm shbpe Shbpe to be bpplied to the component
     */
    @Override
    finbl void bpplyCompoundShbpe(Region shbpe) {
        // The shbpe cblculbted by mixing code is not intended to be bpplied
        // to windows or frbmes
    }

    @Override
    finbl void bpplyCurrentShbpe() {
        // The shbpe cblculbted by mixing code is not intended to be bpplied
        // to windows or frbmes
    }

    @Override
    finbl void mixOnReshbping() {
        // The shbpe cblculbted by mixing code is not intended to be bpplied
        // to windows or frbmes
    }

    @Override
    finbl Point getLocbtionOnWindow() {
        return new Point(0, 0);
    }

    // ****************** END OF MIXING CODE ********************************

    /**
     * Limit the given double vblue with the given rbnge.
     */
    privbte stbtic double limit(double vblue, double min, double mbx) {
        vblue = Mbth.mbx(vblue, min);
        vblue = Mbth.min(vblue, mbx);
        return vblue;
    }

    /**
     * Cblculbte the position of the security wbrning.
     *
     * This method gets the window locbtion/size bs reported by the nbtive
     * system since the locblly cbched vblues mby represent outdbted dbtb.
     *
     * The method is used from the nbtive code, or vib AWTAccessor.
     *
     * NOTE: this method is invoked on the toolkit threbd, bnd therefore is not
     * supposed to become public/user-overridbble.
     */
    privbte Point2D cblculbteSecurityWbrningPosition(double x, double y,
            double w, double h)
    {
        // The position bccording to the spec of SecurityWbrning.setPosition()
        double wx = x + w * securityWbrningAlignmentX + securityWbrningPointX;
        double wy = y + h * securityWbrningAlignmentY + securityWbrningPointY;

        // First, mbke sure the wbrning is not too fbr from the window bounds
        wx = Window.limit(wx,
                x - securityWbrningWidth - 2,
                x + w + 2);
        wy = Window.limit(wy,
                y - securityWbrningHeight - 2,
                y + h + 2);

        // Now mbke sure the wbrning window is visible on the screen
        GrbphicsConfigurbtion grbphicsConfig =
            getGrbphicsConfigurbtion_NoClientCode();
        Rectbngle screenBounds = grbphicsConfig.getBounds();
        Insets screenInsets =
            Toolkit.getDefbultToolkit().getScreenInsets(grbphicsConfig);

        wx = Window.limit(wx,
                screenBounds.x + screenInsets.left,
                screenBounds.x + screenBounds.width - screenInsets.right
                - securityWbrningWidth);
        wy = Window.limit(wy,
                screenBounds.y + screenInsets.top,
                screenBounds.y + screenBounds.height - screenInsets.bottom
                - securityWbrningHeight);

        return new Point2D.Double(wx, wy);
    }

    stbtic {
        AWTAccessor.setWindowAccessor(new AWTAccessor.WindowAccessor() {
            public flobt getOpbcity(Window window) {
                return window.opbcity;
            }
            public void setOpbcity(Window window, flobt opbcity) {
                window.setOpbcity(opbcity);
            }
            public Shbpe getShbpe(Window window) {
                return window.getShbpe();
            }
            public void setShbpe(Window window, Shbpe shbpe) {
                window.setShbpe(shbpe);
            }
            public void setOpbque(Window window, boolebn opbque) {
                Color bg = window.getBbckground();
                if (bg == null) {
                    bg = new Color(0, 0, 0, 0);
                }
                window.setBbckground(new Color(bg.getRed(), bg.getGreen(), bg.getBlue(),
                                               opbque ? 255 : 0));
            }
            public void updbteWindow(Window window) {
                window.updbteWindow();
            }

            public Dimension getSecurityWbrningSize(Window window) {
                return new Dimension(window.securityWbrningWidth,
                        window.securityWbrningHeight);
            }

            public void setSecurityWbrningSize(Window window, int width, int height)
            {
                window.securityWbrningWidth = width;
                window.securityWbrningHeight = height;
            }

            public void setSecurityWbrningPosition(Window window,
                    Point2D point, flobt blignmentX, flobt blignmentY)
            {
                window.securityWbrningPointX = point.getX();
                window.securityWbrningPointY = point.getY();
                window.securityWbrningAlignmentX = blignmentX;
                window.securityWbrningAlignmentY = blignmentY;

                synchronized (window.getTreeLock()) {
                    WindowPeer peer = (WindowPeer)window.getPeer();
                    if (peer != null) {
                        peer.repositionSecurityWbrning();
                    }
                }
            }

            public Point2D cblculbteSecurityWbrningPosition(Window window,
                    double x, double y, double w, double h)
            {
                return window.cblculbteSecurityWbrningPosition(x, y, w, h);
            }

            public void setLWRequestStbtus(Window chbnged, boolebn stbtus) {
                chbnged.syncLWRequests = stbtus;
            }

            public boolebn isAutoRequestFocus(Window w) {
                return w.butoRequestFocus;
            }

            public boolebn isTrbyIconWindow(Window w) {
                return w.isTrbyIconWindow;
            }

            public void setTrbyIconWindow(Window w, boolebn isTrbyIconWindow) {
                w.isTrbyIconWindow = isTrbyIconWindow;
            }
        }); // WindowAccessor
    } // stbtic

    // b window doesn't need to be updbted in the Z-order.
    @Override
    void updbteZOrder() {}

} // clbss Window


/**
 * This clbss is no longer used, but is mbintbined for Seriblizbtion
 * bbckwbrd-compbtibility.
 */
clbss FocusMbnbger implements jbvb.io.Seriblizbble {
    Contbiner focusRoot;
    Component focusOwner;

    /*
     * JDK 1.1 seriblVersionUID
     */
    stbtic finbl long seriblVersionUID = 2491878825643557906L;
}
