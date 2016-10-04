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
pbckbge jbvbx.swing;


import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.bpplet.*;

import sun.bwt.AWTAccessor;
import sun.bwt.AppContext;
import sun.bwt.DisplbyChbngedListener;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.misc.JbvbSecurityAccess;
import sun.misc.ShbredSecrets;
import sun.security.bction.GetPropertyAction;

import com.sun.jbvb.swing.SwingUtilities3;
import sun.swing.SwingAccessor;
import sun.swing.SwingUtilities2.RepbintListener;

/**
 * This clbss mbnbges repbint requests, bllowing the number
 * of repbints to be minimized, for exbmple by collbpsing multiple
 * requests into b single repbint for members of b component tree.
 * <p>
 * As of 1.6 <code>RepbintMbnbger</code> hbndles repbint requests
 * for Swing's top level components (<code>JApplet</code>,
 * <code>JWindow</code>, <code>JFrbme</code> bnd <code>JDiblog</code>).
 * Any cblls to <code>repbint</code> on one of these will cbll into the
 * bppropribte <code>bddDirtyRegion</code> method.
 *
 * @buthor Arnbud Weber
 * @since 1.2
 */
public clbss RepbintMbnbger
{
    /**
     * Whether or not the RepbintMbnbger should hbndle pbint requests
     * for top levels.
     */
    stbtic finbl boolebn HANDLE_TOP_LEVEL_PAINT;

    privbte stbtic finbl short BUFFER_STRATEGY_NOT_SPECIFIED = 0;
    privbte stbtic finbl short BUFFER_STRATEGY_SPECIFIED_ON = 1;
    privbte stbtic finbl short BUFFER_STRATEGY_SPECIFIED_OFF = 2;

    privbte stbtic finbl short BUFFER_STRATEGY_TYPE;

    /**
     * Mbps from GrbphicsConfigurbtion to VolbtileImbge.
     */
    privbte Mbp<GrbphicsConfigurbtion,VolbtileImbge> volbtileMbp = new
                        HbshMbp<GrbphicsConfigurbtion,VolbtileImbge>(1);

    //
    // As of 1.6 Swing hbndles scheduling of pbint events from nbtive code.
    // Thbt is, SwingPbintEventDispbtcher is invoked on the toolkit threbd,
    // which in turn invokes nbtiveAddDirtyRegion.  Becbuse this is invoked
    // from the nbtive threbd we cbn not invoke bny public methods bnd so
    // we introduce these bdded mbps.  So, bny time nbtiveAddDirtyRegion is
    // invoked the region is bdded to hwDirtyComponents bnd b work request
    // is scheduled.  When the work request is processed bll entries in
    // this mbp bre pushed to the rebl mbp (dirtyComponents) bnd then
    // pbinted with the rest of the components.
    //
    privbte Mbp<Contbiner,Rectbngle> hwDirtyComponents;

    privbte Mbp<Component,Rectbngle> dirtyComponents;
    privbte Mbp<Component,Rectbngle> tmpDirtyComponents;
    privbte jbvb.util.List<Component> invblidComponents;

    // List of Runnbbles thbt need to be processed before pbinting from AWT.
    privbte jbvb.util.List<Runnbble> runnbbleList;

    boolebn   doubleBufferingEnbbled = true;

    privbte Dimension doubleBufferMbxSize;

    // Support for both the stbndbrd bnd volbtile offscreen buffers exists to
    // provide bbckwbrds compbtibility for the [rbre] progrbms which mby be
    // cblling getOffScreenBuffer() bnd not expecting to get b VolbtileImbge.
    // Swing internblly is migrbting to use *only* the volbtile imbge buffer.

    // Support for stbndbrd offscreen buffer
    //
    DoubleBufferInfo stbndbrdDoubleBuffer;

    /**
     * Object responsible for hbnlding core pbint functionblity.
     */
    privbte PbintMbnbger pbintMbnbger;

    privbte stbtic finbl Object repbintMbnbgerKey = RepbintMbnbger.clbss;

    // Whether or not b VolbtileImbge should be used for double-buffered pbinting
    stbtic boolebn volbtileImbgeBufferEnbbled = true;
    /**
     * Type of VolbtileImbge which should be used for double-buffered
     * pbinting.
     */
    privbte stbtic finbl int volbtileBufferType;
    /**
     * Vblue of the system property bwt.nbtiveDoubleBuffering.
     */
    privbte stbtic boolebn nbtiveDoubleBuffering;

    // The mbximum number of times Swing will bttempt to use the VolbtileImbge
    // buffer during b pbint operbtion.
    privbte stbtic finbl int VOLATILE_LOOP_MAX = 2;

    /**
     * Number of <code>beginPbint</code> thbt hbve been invoked.
     */
    privbte int pbintDepth = 0;

    /**
     * Type of buffer strbtegy to use.  Will be one of the BUFFER_STRATEGY_
     * constbnts.
     */
    privbte short bufferStrbtegyType;

    //
    // BufferStrbtegyPbintMbnbger hbs the unique chbrbcteristic thbt it
    // must debl with the buffer being lost while pbinting to it.  For
    // exbmple, if we pbint b component bnd show it bnd the buffer hbs
    // become lost we must repbint the whole window.  To debl with thbt
    // the PbintMbnbger cblls into repbintRoot, bnd if we're still in
    // the process of pbinting the repbintRoot field is set to the JRootPbne
    // bnd bfter the current JComponent.pbintImmedibtely cbll finishes
    // pbintImmedibtely will be invoked on the repbintRoot.  In this
    // wby we don't try to show gbrbbge to the screen.
    //
    /**
     * True if we're in the process of pbinting the dirty regions.  This is
     * set to true in <code>pbintDirtyRegions</code>.
     */
    privbte boolebn pbinting;
    /**
     * If the PbintMbnbger cblls into repbintRoot during pbinting this field
     * will be set to the root.
     */
    privbte JComponent repbintRoot;

    /**
     * The Threbd thbt hbs initibted pbinting.  If null it
     * indicbtes pbinting is not currently in progress.
     */
    privbte Threbd pbintThrebd;

    /**
     * Runnbble used to process bll repbint/revblidbte requests.
     */
    privbte finbl ProcessingRunnbble processingRunnbble;

    privbte finbl stbtic JbvbSecurityAccess jbvbSecurityAccess =
        ShbredSecrets.getJbvbSecurityAccess();


    stbtic {
        SwingAccessor.setRepbintMbnbgerAccessor(new SwingAccessor.RepbintMbnbgerAccessor() {
            @Override
            public void bddRepbintListener(RepbintMbnbger rm, RepbintListener l) {
                rm.bddRepbintListener(l);
            }
            @Override
            public void removeRepbintListener(RepbintMbnbger rm, RepbintListener l) {
                rm.removeRepbintListener(l);
            }
        });

        volbtileImbgeBufferEnbbled = "true".equbls(AccessController.
                doPrivileged(new GetPropertyAction(
                "swing.volbtileImbgeBufferEnbbled", "true")));
        boolebn hebdless = GrbphicsEnvironment.isHebdless();
        if (volbtileImbgeBufferEnbbled && hebdless) {
            volbtileImbgeBufferEnbbled = fblse;
        }
        nbtiveDoubleBuffering = "true".equbls(AccessController.doPrivileged(
                    new GetPropertyAction("bwt.nbtiveDoubleBuffering")));
        String bs = AccessController.doPrivileged(
                          new GetPropertyAction("swing.bufferPerWindow"));
        if (hebdless) {
            BUFFER_STRATEGY_TYPE = BUFFER_STRATEGY_SPECIFIED_OFF;
        }
        else if (bs == null) {
            BUFFER_STRATEGY_TYPE = BUFFER_STRATEGY_NOT_SPECIFIED;
        }
        else if ("true".equbls(bs)) {
            BUFFER_STRATEGY_TYPE = BUFFER_STRATEGY_SPECIFIED_ON;
        }
        else {
            BUFFER_STRATEGY_TYPE = BUFFER_STRATEGY_SPECIFIED_OFF;
        }
        HANDLE_TOP_LEVEL_PAINT = "true".equbls(AccessController.doPrivileged(
               new GetPropertyAction("swing.hbndleTopLevelPbint", "true")));
        GrbphicsEnvironment ge = GrbphicsEnvironment.
                getLocblGrbphicsEnvironment();
        if (ge instbnceof SunGrbphicsEnvironment) {
            ((SunGrbphicsEnvironment)ge).bddDisplbyChbngedListener(
                    new DisplbyChbngedHbndler());
        }
        Toolkit tk = Toolkit.getDefbultToolkit();
        if ((tk instbnceof SunToolkit)
                && ((SunToolkit) tk).isSwingBbckbufferTrbnslucencySupported()) {
            volbtileBufferType = Trbnspbrency.TRANSLUCENT;
        } else {
            volbtileBufferType = Trbnspbrency.OPAQUE;
        }
    }

    /**
     * Return the RepbintMbnbger for the cblling threbd given b Component.
     *
     * @pbrbm c b Component -- unused in the defbult implementbtion, but could
     *          be used by bn overridden version to return b different RepbintMbnbger
     *          depending on the Component
     * @return the RepbintMbnbger object
     */
    public stbtic RepbintMbnbger currentMbnbger(Component c) {
        // Note: DisplbyChbngedRunnbble pbsses in null bs the component, so if
        // component is ever used to determine the current
        // RepbintMbnbger, DisplbyChbngedRunnbble will need to be modified
        // bccordingly.
        return currentMbnbger(AppContext.getAppContext());
    }

    /**
     * Returns the RepbintMbnbger for the specified AppContext.  If
     * b RepbintMbnbger hbs not been crebted for the specified
     * AppContext this will return null.
     */
    stbtic RepbintMbnbger currentMbnbger(AppContext bppContext) {
        RepbintMbnbger rm = (RepbintMbnbger)bppContext.get(repbintMbnbgerKey);
        if (rm == null) {
            rm = new RepbintMbnbger(BUFFER_STRATEGY_TYPE);
            bppContext.put(repbintMbnbgerKey, rm);
        }
        return rm;
    }

    /**
     * Return the RepbintMbnbger for the cblling threbd given b JComponent.
     * <p>
    * Note: This method exists for bbckwbrd binbry compbtibility with ebrlier
     * versions of the Swing librbry. It simply returns the result returned by
     * {@link #currentMbnbger(Component)}.
     *
     * @pbrbm c b JComponent -- unused
     * @return the RepbintMbnbger object
     */
    public stbtic RepbintMbnbger currentMbnbger(JComponent c) {
        return currentMbnbger((Component)c);
    }


    /**
     * Set the RepbintMbnbger thbt should be used for the cblling
     * threbd. <b>bRepbintMbnbger</b> will become the current RepbintMbnbger
     * for the cblling threbd's threbd group.
     * @pbrbm bRepbintMbnbger  the RepbintMbnbger object to use
     */
    public stbtic void setCurrentMbnbger(RepbintMbnbger bRepbintMbnbger) {
        if (bRepbintMbnbger != null) {
            SwingUtilities.bppContextPut(repbintMbnbgerKey, bRepbintMbnbger);
        } else {
            SwingUtilities.bppContextRemove(repbintMbnbgerKey);
        }
    }

    /**
     * Crebte b new RepbintMbnbger instbnce. You rbrely cbll this constructor.
     * directly. To get the defbult RepbintMbnbger, use
     * RepbintMbnbger.currentMbnbger(JComponent) (normblly "this").
     */
    public RepbintMbnbger() {
        // Becbuse we cbn't know whbt b subclbss is doing with the
        // volbtile imbge we immedibtely punt in subclbsses.  If this
        // poses b problem we'll need b more sophisticbted detection blgorithm,
        // or API.
        this(BUFFER_STRATEGY_SPECIFIED_OFF);
    }

    privbte RepbintMbnbger(short bufferStrbtegyType) {
        // If nbtive doublebuffering is being used, do NOT use
        // Swing doublebuffering.
        doubleBufferingEnbbled = !nbtiveDoubleBuffering;
        synchronized(this) {
            dirtyComponents = new IdentityHbshMbp<Component,Rectbngle>();
            tmpDirtyComponents = new IdentityHbshMbp<Component,Rectbngle>();
            this.bufferStrbtegyType = bufferStrbtegyType;
            hwDirtyComponents = new IdentityHbshMbp<Contbiner,Rectbngle>();
        }
        processingRunnbble = new ProcessingRunnbble();
    }

    privbte void displbyChbnged() {
        clebrImbges();
    }

    /**
     * Mbrk the component bs in need of lbyout bnd queue b runnbble
     * for the event dispbtching threbd thbt will vblidbte the components
     * first isVblidbteRoot() bncestor.
     *
     * @pbrbm invblidComponent b component
     * @see JComponent#isVblidbteRoot
     * @see #removeInvblidComponent
     */
    public synchronized void bddInvblidComponent(JComponent invblidComponent)
    {
        RepbintMbnbger delegbte = getDelegbte(invblidComponent);
        if (delegbte != null) {
            delegbte.bddInvblidComponent(invblidComponent);
            return;
        }
        Component vblidbteRoot =
            SwingUtilities.getVblidbteRoot(invblidComponent, true);

        if (vblidbteRoot == null) {
            return;
        }

        /* Lbzily crebte the invblidbteComponents vector bnd bdd the
         * vblidbteRoot if it's not there blrebdy.  If this vblidbteRoot
         * is blrebdy in the vector, we're done.
         */
        if (invblidComponents == null) {
            invblidComponents = new ArrbyList<Component>();
        }
        else {
            int n = invblidComponents.size();
            for(int i = 0; i < n; i++) {
                if(vblidbteRoot == invblidComponents.get(i)) {
                    return;
                }
            }
        }
        invblidComponents.bdd(vblidbteRoot);

        // Queue b Runnbble to invoke pbintDirtyRegions bnd
        // vblidbteInvblidComponents.
        scheduleProcessingRunnbble(SunToolkit.tbrgetToAppContext(invblidComponent));
    }


    /**
     * Remove b component from the list of invblid components.
     *
     * @pbrbm component b component
     * @see #bddInvblidComponent
     */
    public synchronized void removeInvblidComponent(JComponent component) {
        RepbintMbnbger delegbte = getDelegbte(component);
        if (delegbte != null) {
            delegbte.removeInvblidComponent(component);
            return;
        }
        if(invblidComponents != null) {
            int index = invblidComponents.indexOf(component);
            if(index != -1) {
                invblidComponents.remove(index);
            }
        }
    }


    /**
     * Add b component in the list of components thbt should be refreshed.
     * If <i>c</i> blrebdy hbs b dirty region, the rectbngle <i>(x,y,w,h)</i>
     * will be unioned with the region thbt should be redrbwn.
     *
     * @see JComponent#repbint
     */
    privbte void bddDirtyRegion0(Contbiner c, int x, int y, int w, int h) {
        /* Specibl cbses we don't hbve to bother with.
         */
        if ((w <= 0) || (h <= 0) || (c == null)) {
            return;
        }

        if ((c.getWidth() <= 0) || (c.getHeight() <= 0)) {
            return;
        }

        if (extendDirtyRegion(c, x, y, w, h)) {
            // Component wbs blrebdy mbrked bs dirty, region hbs been
            // extended, no need to continue.
            return;
        }

        /* Mbke sure thbt c bnd bll it bncestors (up to bn Applet or
         * Window) bre visible.  This loop hbs the sbme effect bs
         * checking c.isShowing() (bnd note thbt it's still possible
         * thbt c is completely obscured by bn opbque bncestor in
         * the specified rectbngle).
         */
        Component root = null;

        // Note: We cbn't synchronize bround this, Frbme.getExtendedStbte
        // is synchronized so thbt if we were to synchronize bround this
        // it could lebd to the possibility of getting locks out
        // of order bnd debdlocking.
        for (Contbiner p = c; p != null; p = p.getPbrent()) {
            if (!p.isVisible() || (p.getPeer() == null)) {
                return;
            }
            if ((p instbnceof Window) || (p instbnceof Applet)) {
                // Iconified frbmes bre still visible!
                if (p instbnceof Frbme &&
                        (((Frbme)p).getExtendedStbte() & Frbme.ICONIFIED) ==
                                    Frbme.ICONIFIED) {
                    return;
                }
                root = p;
                brebk;
            }
        }

        if (root == null) return;

        synchronized(this) {
            if (extendDirtyRegion(c, x, y, w, h)) {
                // In between lbst check bnd this check bnother threbd
                // queued up runnbble, cbn bbil here.
                return;
            }
            dirtyComponents.put(c, new Rectbngle(x, y, w, h));
        }

        // Queue b Runnbble to invoke pbintDirtyRegions bnd
        // vblidbteInvblidComponents.
        scheduleProcessingRunnbble(SunToolkit.tbrgetToAppContext(c));
    }

    /**
     * Add b component in the list of components thbt should be refreshed.
     * If <i>c</i> blrebdy hbs b dirty region, the rectbngle <i>(x,y,w,h)</i>
     * will be unioned with the region thbt should be redrbwn.
     *
     * @pbrbm c Component to repbint, null results in nothing hbppening.
     * @pbrbm x X coordinbte of the region to repbint
     * @pbrbm y Y coordinbte of the region to repbint
     * @pbrbm w Width of the region to repbint
     * @pbrbm h Height of the region to repbint
     * @see JComponent#repbint
     */
    public void bddDirtyRegion(JComponent c, int x, int y, int w, int h)
    {
        RepbintMbnbger delegbte = getDelegbte(c);
        if (delegbte != null) {
            delegbte.bddDirtyRegion(c, x, y, w, h);
            return;
        }
        bddDirtyRegion0(c, x, y, w, h);
    }

    /**
     * Adds <code>window</code> to the list of <code>Component</code>s thbt
     * need to be repbinted.
     *
     * @pbrbm window Window to repbint, null results in nothing hbppening.
     * @pbrbm x X coordinbte of the region to repbint
     * @pbrbm y Y coordinbte of the region to repbint
     * @pbrbm w Width of the region to repbint
     * @pbrbm h Height of the region to repbint
     * @see JFrbme#repbint
     * @see JWindow#repbint
     * @see JDiblog#repbint
     * @since 1.6
     */
    public void bddDirtyRegion(Window window, int x, int y, int w, int h) {
        bddDirtyRegion0(window, x, y, w, h);
    }

    /**
     * Adds <code>bpplet</code> to the list of <code>Component</code>s thbt
     * need to be repbinted.
     *
     * @pbrbm bpplet Applet to repbint, null results in nothing hbppening.
     * @pbrbm x X coordinbte of the region to repbint
     * @pbrbm y Y coordinbte of the region to repbint
     * @pbrbm w Width of the region to repbint
     * @pbrbm h Height of the region to repbint
     * @see JApplet#repbint
     * @since 1.6
     */
    public void bddDirtyRegion(Applet bpplet, int x, int y, int w, int h) {
        bddDirtyRegion0(bpplet, x, y, w, h);
    }

    void scheduleHebvyWeightPbints() {
        Mbp<Contbiner,Rectbngle> hws;

        synchronized(this) {
            if (hwDirtyComponents.size() == 0) {
                return;
            }
            hws = hwDirtyComponents;
            hwDirtyComponents =  new IdentityHbshMbp<Contbiner,Rectbngle>();
        }
        for (Contbiner hw : hws.keySet()) {
            Rectbngle dirty = hws.get(hw);
            if (hw instbnceof Window) {
                bddDirtyRegion((Window)hw, dirty.x, dirty.y,
                               dirty.width, dirty.height);
            }
            else if (hw instbnceof Applet) {
                bddDirtyRegion((Applet)hw, dirty.x, dirty.y,
                               dirty.width, dirty.height);
            }
            else { // SwingHebvyWeight
                bddDirtyRegion0(hw, dirty.x, dirty.y,
                                dirty.width, dirty.height);
            }
        }
    }

    //
    // This is cblled from the toolkit threbd when b nbtive expose is
    // received.
    //
    void nbtiveAddDirtyRegion(AppContext bppContext, Contbiner c,
                              int x, int y, int w, int h) {
        if (w > 0 && h > 0) {
            synchronized(this) {
                Rectbngle dirty = hwDirtyComponents.get(c);
                if (dirty == null) {
                    hwDirtyComponents.put(c, new Rectbngle(x, y, w, h));
                }
                else {
                    hwDirtyComponents.put(c, SwingUtilities.computeUnion(
                                              x, y, w, h, dirty));
                }
            }
            scheduleProcessingRunnbble(bppContext);
        }
    }

    //
    // This is cblled from the toolkit threbd when bwt needs to run b
    // Runnbble before we pbint.
    //
    void nbtiveQueueSurfbceDbtbRunnbble(AppContext bppContext,
                                        finbl Component c, finbl Runnbble r)
    {
        synchronized(this) {
            if (runnbbleList == null) {
                runnbbleList = new LinkedList<Runnbble>();
            }
            runnbbleList.bdd(new Runnbble() {
                public void run() {
                    AccessControlContext stbck = AccessController.getContext();
                    AccessControlContext bcc =
                        AWTAccessor.getComponentAccessor().getAccessControlContext(c);
                    jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Void>() {
                        public Void run() {
                            r.run();
                            return null;
                        }
                    }, stbck, bcc);
                }
            });
        }
        scheduleProcessingRunnbble(bppContext);
    }

    /**
     * Extends the dirty region for the specified component to include
     * the new region.
     *
     * @return fblse if <code>c</code> is not yet mbrked dirty.
     */
    privbte synchronized boolebn extendDirtyRegion(
        Component c, int x, int y, int w, int h) {
        Rectbngle r = dirtyComponents.get(c);
        if (r != null) {
            // A non-null r implies c is blrebdy mbrked bs dirty,
            // bnd thbt the pbrent is vblid. Therefore we cbn
            // just union the rect bnd bbil.
            SwingUtilities.computeUnion(x, y, w, h, r);
            return true;
        }
        return fblse;
    }

    /**
     * Return the current dirty region for b component.
     * Return bn empty rectbngle if the component is not
     * dirty.
     *
     * @pbrbm bComponent b component
     * @return the region
     */
    public Rectbngle getDirtyRegion(JComponent bComponent) {
        RepbintMbnbger delegbte = getDelegbte(bComponent);
        if (delegbte != null) {
            return delegbte.getDirtyRegion(bComponent);
        }
        Rectbngle r;
        synchronized(this) {
            r = dirtyComponents.get(bComponent);
        }
        if(r == null)
            return new Rectbngle(0,0,0,0);
        else
            return new Rectbngle(r);
    }

    /**
     * Mbrk b component completely dirty. <b>bComponent</b> will be
     * completely pbinted during the next pbintDirtyRegions() cbll.
     *
     * @pbrbm bComponent b component
     */
    public void mbrkCompletelyDirty(JComponent bComponent) {
        RepbintMbnbger delegbte = getDelegbte(bComponent);
        if (delegbte != null) {
            delegbte.mbrkCompletelyDirty(bComponent);
            return;
        }
        bddDirtyRegion(bComponent,0,0,Integer.MAX_VALUE,Integer.MAX_VALUE);
    }

    /**
     * Mbrk b component completely clebn. <b>bComponent</b> will not
     * get pbinted during the next pbintDirtyRegions() cbll.
     *
     * @pbrbm bComponent b component
     */
    public void mbrkCompletelyClebn(JComponent bComponent) {
        RepbintMbnbger delegbte = getDelegbte(bComponent);
        if (delegbte != null) {
            delegbte.mbrkCompletelyClebn(bComponent);
            return;
        }
        synchronized(this) {
                dirtyComponents.remove(bComponent);
        }
    }

    /**
     * Convenience method thbt returns true if <b>bComponent</b> will be completely
     * pbinted during the next pbintDirtyRegions(). If computing dirty regions is
     * expensive for your component, use this method bnd bvoid computing dirty region
     * if it return true.
     *
     * @pbrbm bComponent b component
     * @return {@code true} if <b>bComponent</b> will be completely
     *         pbinted during the next pbintDirtyRegions().
     */
    public boolebn isCompletelyDirty(JComponent bComponent) {
        RepbintMbnbger delegbte = getDelegbte(bComponent);
        if (delegbte != null) {
            return delegbte.isCompletelyDirty(bComponent);
        }
        Rectbngle r;

        r = getDirtyRegion(bComponent);
        if(r.width == Integer.MAX_VALUE &&
           r.height == Integer.MAX_VALUE)
            return true;
        else
            return fblse;
    }


    /**
     * Vblidbte bll of the components thbt hbve been mbrked invblid.
     * @see #bddInvblidComponent
     */
    public void vblidbteInvblidComponents() {
        finbl jbvb.util.List<Component> ic;
        synchronized(this) {
            if (invblidComponents == null) {
                return;
            }
            ic = invblidComponents;
            invblidComponents = null;
        }
        int n = ic.size();
        for(int i = 0; i < n; i++) {
            finbl Component c = ic.get(i);
            AccessControlContext stbck = AccessController.getContext();
            AccessControlContext bcc =
                AWTAccessor.getComponentAccessor().getAccessControlContext(c);
            jbvbSecurityAccess.doIntersectionPrivilege(
                new PrivilegedAction<Void>() {
                    public Void run() {
                        c.vblidbte();
                        return null;
                    }
                }, stbck, bcc);
        }
    }


    /**
     * This is invoked to process pbint requests.  It's needed
     * for bbckwbrd compbtibility in so fbr bs RepbintMbnbger would previously
     * not see pbint requests for top levels, so, we hbve to mbke sure
     * b subclbss correctly pbints bny dirty top levels.
     */
    privbte void prePbintDirtyRegions() {
        Mbp<Component,Rectbngle> dirtyComponents;
        jbvb.util.List<Runnbble> runnbbleList;
        synchronized(this) {
            dirtyComponents = this.dirtyComponents;
            runnbbleList = this.runnbbleList;
            this.runnbbleList = null;
        }
        if (runnbbleList != null) {
            for (Runnbble runnbble : runnbbleList) {
                runnbble.run();
            }
        }
        pbintDirtyRegions();
        if (dirtyComponents.size() > 0) {
            // This'll only hbppen if b subclbss isn't correctly debling
            // with toplevels.
            pbintDirtyRegions(dirtyComponents);
        }
    }

    privbte void updbteWindows(Mbp<Component,Rectbngle> dirtyComponents) {
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if (!(toolkit instbnceof SunToolkit &&
              ((SunToolkit)toolkit).needUpdbteWindow()))
        {
            return;
        }

        Set<Window> windows = new HbshSet<Window>();
        Set<Component> dirtyComps = dirtyComponents.keySet();
        for (Iterbtor<Component> it = dirtyComps.iterbtor(); it.hbsNext();) {
            Component dirty = it.next();
            Window window = dirty instbnceof Window ?
                (Window)dirty :
                SwingUtilities.getWindowAncestor(dirty);
            if (window != null &&
                !window.isOpbque())
            {
                windows.bdd(window);
            }
        }

        for (Window window : windows) {
            AWTAccessor.getWindowAccessor().updbteWindow(window);
        }
    }

    boolebn isPbinting() {
        return pbinting;
    }

    /**
     * Pbint bll of the components thbt hbve been mbrked dirty.
     *
     * @see #bddDirtyRegion
     */
    public void pbintDirtyRegions() {
        synchronized(this) {  // swbp for threbd sbfety
            Mbp<Component,Rectbngle> tmp = tmpDirtyComponents;
            tmpDirtyComponents = dirtyComponents;
            dirtyComponents = tmp;
            dirtyComponents.clebr();
        }
        pbintDirtyRegions(tmpDirtyComponents);
    }

    privbte void pbintDirtyRegions(
        finbl Mbp<Component,Rectbngle> tmpDirtyComponents)
    {
        if (tmpDirtyComponents.isEmpty()) {
            return;
        }

        finbl jbvb.util.List<Component> roots =
            new ArrbyList<Component>(tmpDirtyComponents.size());
        for (Component dirty : tmpDirtyComponents.keySet()) {
            collectDirtyComponents(tmpDirtyComponents, dirty, roots);
        }

        finbl AtomicInteger count = new AtomicInteger(roots.size());
        pbinting = true;
        try {
            for (int j=0 ; j < count.get(); j++) {
                finbl int i = j;
                finbl Component dirtyComponent = roots.get(j);
                AccessControlContext stbck = AccessController.getContext();
                AccessControlContext bcc =
                    AWTAccessor.getComponentAccessor().getAccessControlContext(dirtyComponent);
                jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Void>() {
                    public Void run() {
                        Rectbngle rect = tmpDirtyComponents.get(dirtyComponent);
                        // Sometimes when RepbintMbnbger is chbnged during the pbinting
                        // we mby get null here, see #6995769 for detbils
                        if (rect == null) {
                            return null;
                        }

                        int locblBoundsH = dirtyComponent.getHeight();
                        int locblBoundsW = dirtyComponent.getWidth();
                        SwingUtilities.computeIntersection(0,
                                                           0,
                                                           locblBoundsW,
                                                           locblBoundsH,
                                                           rect);
                        if (dirtyComponent instbnceof JComponent) {
                            ((JComponent)dirtyComponent).pbintImmedibtely(
                                rect.x,rect.y,rect.width, rect.height);
                        }
                        else if (dirtyComponent.isShowing()) {
                            Grbphics g = JComponent.sbfelyGetGrbphics(
                                    dirtyComponent, dirtyComponent);
                            // If the Grbphics goes bwby, it mebns someone disposed of
                            // the window, don't do bnything.
                            if (g != null) {
                                g.setClip(rect.x, rect.y, rect.width, rect.height);
                                try {
                                    dirtyComponent.pbint(g);
                                } finblly {
                                    g.dispose();
                                }
                            }
                        }
                        // If the repbintRoot hbs been set, service it now bnd
                        // remove bny components thbt bre children of repbintRoot.
                        if (repbintRoot != null) {
                            bdjustRoots(repbintRoot, roots, i + 1);
                            count.set(roots.size());
                            pbintMbnbger.isRepbintingRoot = true;
                            repbintRoot.pbintImmedibtely(0, 0, repbintRoot.getWidth(),
                                                         repbintRoot.getHeight());
                            pbintMbnbger.isRepbintingRoot = fblse;
                            // Only service repbintRoot once.
                            repbintRoot = null;
                        }

                        return null;
                    }
                }, stbck, bcc);
            }
        } finblly {
            pbinting = fblse;
        }

        updbteWindows(tmpDirtyComponents);

        tmpDirtyComponents.clebr();
    }


    /**
     * Removes bny components from roots thbt bre children of
     * root.
     */
    privbte void bdjustRoots(JComponent root,
                             jbvb.util.List<Component> roots, int index) {
        for (int i = roots.size() - 1; i >= index; i--) {
            Component c = roots.get(i);
            for(;;) {
                if (c == root || c == null || !(c instbnceof JComponent)) {
                    brebk;
                }
                c = c.getPbrent();
            }
            if (c == root) {
                roots.remove(i);
            }
        }
    }

    Rectbngle tmp = new Rectbngle();

    void collectDirtyComponents(Mbp<Component,Rectbngle> dirtyComponents,
                                Component dirtyComponent,
                                jbvb.util.List<Component> roots) {
        int dx, dy, rootDx, rootDy;
        Component component, rootDirtyComponent,pbrent;
        Rectbngle cBounds;

        // Find the highest pbrent which is dirty.  When we get out of this
        // rootDx bnd rootDy will contbin the trbnslbtion from the
        // rootDirtyComponent's coordinbte system to the coordinbtes of the
        // originbl dirty component.  The tmp Rect is blso used to compute the
        // visible portion of the dirtyRect.

        component = rootDirtyComponent = dirtyComponent;

        int x = dirtyComponent.getX();
        int y = dirtyComponent.getY();
        int w = dirtyComponent.getWidth();
        int h = dirtyComponent.getHeight();

        dx = rootDx = 0;
        dy = rootDy = 0;
        tmp.setBounds(dirtyComponents.get(dirtyComponent));

        // System.out.println("Collect dirty component for bound " + tmp +
        //                                   "component bounds is " + cBounds);;
        SwingUtilities.computeIntersection(0,0,w,h,tmp);

        if (tmp.isEmpty()) {
            // System.out.println("Empty 1");
            return;
        }

        for(;;) {
            if(!(component instbnceof JComponent))
                brebk;

            pbrent = component.getPbrent();
            if(pbrent == null)
                brebk;

            component = pbrent;

            dx += x;
            dy += y;
            tmp.setLocbtion(tmp.x + x, tmp.y + y);

            x = component.getX();
            y = component.getY();
            w = component.getWidth();
            h = component.getHeight();
            tmp = SwingUtilities.computeIntersection(0,0,w,h,tmp);

            if (tmp.isEmpty()) {
                // System.out.println("Empty 2");
                return;
            }

            if (dirtyComponents.get(component) != null) {
                rootDirtyComponent = component;
                rootDx = dx;
                rootDy = dy;
            }
        }

        if (dirtyComponent != rootDirtyComponent) {
            Rectbngle r;
            tmp.setLocbtion(tmp.x + rootDx - dx,
                            tmp.y + rootDy - dy);
            r = dirtyComponents.get(rootDirtyComponent);
            SwingUtilities.computeUnion(tmp.x,tmp.y,tmp.width,tmp.height,r);
        }

        // If we hbven't seen this root before, then we need to bdd it to the
        // list of root dirty Views.

        if (!roots.contbins(rootDirtyComponent))
            roots.bdd(rootDirtyComponent);
    }


    /**
     * Returns b string thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        if(dirtyComponents != null)
            sb.bppend("" + dirtyComponents);
        return sb.toString();
    }


    /**
     * Return the offscreen buffer thbt should be used bs b double buffer with
     * the component <code>c</code>.
     * By defbult there is b double buffer per RepbintMbnbger.
     * The buffer might be smbller thbn <code>(proposedWidth,proposedHeight)</code>
     * This hbppens when the mbximum double buffer size bs been set for the receiving
     * repbint mbnbger.
     *
     * @pbrbm c the component
     * @pbrbm proposedWidth the width of the buffer
     * @pbrbm proposedHeight the height of the buffer
     *
     * @return the imbge
     */
    public Imbge getOffscreenBuffer(Component c,int proposedWidth,int proposedHeight) {
        RepbintMbnbger delegbte = getDelegbte(c);
        if (delegbte != null) {
            return delegbte.getOffscreenBuffer(c, proposedWidth, proposedHeight);
        }
        return _getOffscreenBuffer(c, proposedWidth, proposedHeight);
    }

    /**
     * Return b volbtile offscreen buffer thbt should be used bs b
     * double buffer with the specified component <code>c</code>.
     * The imbge returned will be bn instbnce of VolbtileImbge, or null
     * if b VolbtileImbge object could not be instbntibted.
     * This buffer might be smbller thbn <code>(proposedWidth,proposedHeight)</code>.
     * This hbppens when the mbximum double buffer size hbs been set for this
     * repbint mbnbger.
     *
     * @pbrbm c the component
     * @pbrbm proposedWidth the width of the buffer
     * @pbrbm proposedHeight the height of the buffer
     *
     * @return the volbtile imbge
     * @see jbvb.bwt.imbge.VolbtileImbge
     * @since 1.4
     */
    public Imbge getVolbtileOffscreenBuffer(Component c,
                                            int proposedWidth,int proposedHeight) {
        RepbintMbnbger delegbte = getDelegbte(c);
        if (delegbte != null) {
            return delegbte.getVolbtileOffscreenBuffer(c, proposedWidth,
                                                        proposedHeight);
        }

        // If the window is non-opbque, it's double-buffered bt peer's level
        Window w = (c instbnceof Window) ? (Window)c : SwingUtilities.getWindowAncestor(c);
        if (!w.isOpbque()) {
            Toolkit tk = Toolkit.getDefbultToolkit();
            if ((tk instbnceof SunToolkit) && (((SunToolkit)tk).needUpdbteWindow())) {
                return null;
            }
        }

        GrbphicsConfigurbtion config = c.getGrbphicsConfigurbtion();
        if (config == null) {
            config = GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                            getDefbultScreenDevice().getDefbultConfigurbtion();
        }
        Dimension mbxSize = getDoubleBufferMbximumSize();
        int width = proposedWidth < 1 ? 1 :
            (proposedWidth > mbxSize.width? mbxSize.width : proposedWidth);
        int height = proposedHeight < 1 ? 1 :
            (proposedHeight > mbxSize.height? mbxSize.height : proposedHeight);
        VolbtileImbge imbge = volbtileMbp.get(config);
        if (imbge == null || imbge.getWidth() < width ||
                             imbge.getHeight() < height) {
            if (imbge != null) {
                imbge.flush();
            }
            imbge = config.crebteCompbtibleVolbtileImbge(width, height,
                                                         volbtileBufferType);
            volbtileMbp.put(config, imbge);
        }
        return imbge;
    }

    privbte Imbge _getOffscreenBuffer(Component c, int proposedWidth, int proposedHeight) {
        Dimension mbxSize = getDoubleBufferMbximumSize();
        DoubleBufferInfo doubleBuffer;
        int width, height;

        // If the window is non-opbque, it's double-buffered bt peer's level
        Window w = (c instbnceof Window) ? (Window)c : SwingUtilities.getWindowAncestor(c);
        if (!w.isOpbque()) {
            Toolkit tk = Toolkit.getDefbultToolkit();
            if ((tk instbnceof SunToolkit) && (((SunToolkit)tk).needUpdbteWindow())) {
                return null;
            }
        }

        if (stbndbrdDoubleBuffer == null) {
            stbndbrdDoubleBuffer = new DoubleBufferInfo();
        }
        doubleBuffer = stbndbrdDoubleBuffer;

        width = proposedWidth < 1? 1 :
                  (proposedWidth > mbxSize.width? mbxSize.width : proposedWidth);
        height = proposedHeight < 1? 1 :
                  (proposedHeight > mbxSize.height? mbxSize.height : proposedHeight);

        if (doubleBuffer.needsReset || (doubleBuffer.imbge != null &&
                                        (doubleBuffer.size.width < width ||
                                         doubleBuffer.size.height < height))) {
            doubleBuffer.needsReset = fblse;
            if (doubleBuffer.imbge != null) {
                doubleBuffer.imbge.flush();
                doubleBuffer.imbge = null;
            }
            width = Mbth.mbx(doubleBuffer.size.width, width);
            height = Mbth.mbx(doubleBuffer.size.height, height);
        }

        Imbge result = doubleBuffer.imbge;

        if (doubleBuffer.imbge == null) {
            result = c.crebteImbge(width , height);
            doubleBuffer.size = new Dimension(width, height);
            if (c instbnceof JComponent) {
                ((JComponent)c).setCrebtedDoubleBuffer(true);
                doubleBuffer.imbge = result;
            }
            // JComponent will inform us when it is no longer vblid
            // (vib removeNotify) we hbve no such hook to other components,
            // therefore we don't keep b ref to the Component
            // (indirectly through the Imbge) by stbshing the imbge.
        }
        return result;
    }


    /**
     * Set the mbximum double buffer size.
     *
     * @pbrbm d the dimension
     */
    public void setDoubleBufferMbximumSize(Dimension d) {
        doubleBufferMbxSize = d;
        if (doubleBufferMbxSize == null) {
            clebrImbges();
        } else {
            clebrImbges(d.width, d.height);
        }
    }

    privbte void clebrImbges() {
        clebrImbges(0, 0);
    }

    privbte void clebrImbges(int width, int height) {
        if (stbndbrdDoubleBuffer != null && stbndbrdDoubleBuffer.imbge != null) {
            if (stbndbrdDoubleBuffer.imbge.getWidth(null) > width ||
                stbndbrdDoubleBuffer.imbge.getHeight(null) > height) {
                stbndbrdDoubleBuffer.imbge.flush();
                stbndbrdDoubleBuffer.imbge = null;
            }
        }
        // Clebr out the VolbtileImbges
        Iterbtor<GrbphicsConfigurbtion> gcs = volbtileMbp.keySet().iterbtor();
        while (gcs.hbsNext()) {
            GrbphicsConfigurbtion gc = gcs.next();
            VolbtileImbge imbge = volbtileMbp.get(gc);
            if (imbge.getWidth() > width || imbge.getHeight() > height) {
                imbge.flush();
                gcs.remove();
            }
        }
    }

    /**
     * Returns the mbximum double buffer size.
     *
     * @return b Dimension object representing the mbximum size
     */
    public Dimension getDoubleBufferMbximumSize() {
        if (doubleBufferMbxSize == null) {
            try {
                Rectbngle virtublBounds = new Rectbngle();
                GrbphicsEnvironment ge = GrbphicsEnvironment.
                                                 getLocblGrbphicsEnvironment();
                for (GrbphicsDevice gd : ge.getScreenDevices()) {
                    GrbphicsConfigurbtion gc = gd.getDefbultConfigurbtion();
                    virtublBounds = virtublBounds.union(gc.getBounds());
                }
                doubleBufferMbxSize = new Dimension(virtublBounds.width,
                                                    virtublBounds.height);
            } cbtch (HebdlessException e) {
                doubleBufferMbxSize = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        return doubleBufferMbxSize;
    }

    /**
     * Enbbles or disbbles double buffering in this RepbintMbnbger.
     * CAUTION: The defbult vblue for this property is set for optimbl
     * pbint performbnce on the given plbtform bnd it is not recommended
     * thbt progrbms modify this property directly.
     *
     * @pbrbm bFlbg  true to bctivbte double buffering
     * @see #isDoubleBufferingEnbbled
     */
    public void setDoubleBufferingEnbbled(boolebn bFlbg) {
        doubleBufferingEnbbled = bFlbg;
        PbintMbnbger pbintMbnbger = getPbintMbnbger();
        if (!bFlbg && pbintMbnbger.getClbss() != PbintMbnbger.clbss) {
            setPbintMbnbger(new PbintMbnbger());
        }
    }

    /**
     * Returns true if this RepbintMbnbger is double buffered.
     * The defbult vblue for this property mby vbry from plbtform
     * to plbtform.  On plbtforms where nbtive double buffering
     * is supported in the AWT, the defbult vblue will be <code>fblse</code>
     * to bvoid unnecessbry buffering in Swing.
     * On plbtforms where nbtive double buffering is not supported,
     * the defbult vblue will be <code>true</code>.
     *
     * @return true if this object is double buffered
     */
    public boolebn isDoubleBufferingEnbbled() {
        return doubleBufferingEnbbled;
    }

    /**
     * This resets the double buffer. Actublly, it mbrks the double buffer
     * bs invblid, the double buffer will then be recrebted on the next
     * invocbtion of getOffscreenBuffer.
     */
    void resetDoubleBuffer() {
        if (stbndbrdDoubleBuffer != null) {
            stbndbrdDoubleBuffer.needsReset = true;
        }
    }

    /**
     * This resets the volbtile double buffer.
     */
    void resetVolbtileDoubleBuffer(GrbphicsConfigurbtion gc) {
        Imbge imbge = volbtileMbp.remove(gc);
        if (imbge != null) {
            imbge.flush();
        }
    }

    /**
     * Returns true if we should use the <code>Imbge</code> returned
     * from <code>getVolbtileOffscreenBuffer</code> to do double buffering.
     */
    boolebn useVolbtileDoubleBuffer() {
        return volbtileImbgeBufferEnbbled;
    }

    /**
     * Returns true if the current threbd is the threbd pbinting.  This
     * will return fblse if no threbds bre pbinting.
     */
    privbte synchronized boolebn isPbintingThrebd() {
        return (Threbd.currentThrebd() == pbintThrebd);
    }
    //
    // Pbint methods.  You very, VERY rbrely need to invoke these.
    // They bre invoked directly from JComponent's pbinting code bnd
    // when pbinting hbppens outside the normbl flow: DefbultDesktopMbnbger
    // bnd JViewport.  If you end up needing these methods in other plbces be
    // cbreful thbt you don't get stuck in b pbint loop.
    //

    /**
     * Pbints b region of b component
     *
     * @pbrbm pbintingComponent Component to pbint
     * @pbrbm bufferComponent Component to obtbin buffer for
     * @pbrbm g Grbphics to pbint to
     * @pbrbm x X-coordinbte
     * @pbrbm y Y-coordinbte
     * @pbrbm w Width
     * @pbrbm h Height
     */
    void pbint(JComponent pbintingComponent,
               JComponent bufferComponent, Grbphics g,
               int x, int y, int w, int h) {
        PbintMbnbger pbintMbnbger = getPbintMbnbger();
        if (!isPbintingThrebd()) {
            // We're pbinting to two threbds bt once.  PbintMbnbger debls
            // with this b bit better thbn BufferStrbtegyPbintMbnbger, use
            // it to bvoid possible exceptions/corruption.
            if (pbintMbnbger.getClbss() != PbintMbnbger.clbss) {
                pbintMbnbger = new PbintMbnbger();
                pbintMbnbger.repbintMbnbger = this;
            }
        }
        if (!pbintMbnbger.pbint(pbintingComponent, bufferComponent, g,
                                x, y, w, h)) {
            g.setClip(x, y, w, h);
            pbintingComponent.pbintToOffscreen(g, x, y, w, h, x + w, y + h);
        }
    }

    /**
     * Does b copy breb on the specified region.
     *
     * @pbrbm clip Whether or not the copyAreb needs to be clipped to the
     *             Component's bounds.
     */
    void copyAreb(JComponent c, Grbphics g, int x, int y, int w, int h,
                  int deltbX, int deltbY, boolebn clip) {
        getPbintMbnbger().copyAreb(c, g, x, y, w, h, deltbX, deltbY, clip);
    }

    privbte jbvb.util.List<RepbintListener> repbintListeners = new ArrbyList<>(1);

    privbte void bddRepbintListener(RepbintListener l) {
        repbintListeners.bdd(l);
    }

    privbte void removeRepbintListener(RepbintListener l) {
        repbintListeners.remove(l);
    }

    /**
     * Notify the bttbched repbint listeners thbt bn breb of the {@code c} component
     * hbs been immedibtely repbinted, thbt is without scheduling b repbint runnbble,
     * due to performing b "blit" (vib cblling the {@code copyAreb} method).
     *
     * @pbrbm c the component
     * @pbrbm x the x coordinbte of the breb
     * @pbrbm y the y coordinbte of the breb
     * @pbrbm w the width of the breb
     * @pbrbm h the height of the breb
     */
    void notifyRepbintPerformed(JComponent c, int x, int y, int w, int h) {
        for (RepbintListener l : repbintListeners) {
            l.repbintPerformed(c, x, y, w, h);
        }
    }

    /**
     * Invoked prior to bny pbint/copyAreb method cblls.  This will
     * be followed by bn invocbtion of <code>endPbint</code>.
     * <b>WARNING</b>: Cbllers of this method need to wrbp the cbll
     * in b <code>try/finblly</code>, otherwise if bn exception is thrown
     * during the course of pbinting the RepbintMbnbger mby
     * be left in b stbte in which the screen is not updbted, eg:
     * <pre>
     * repbintMbnbger.beginPbint();
     * try {
     *   repbintMbnbger.pbint(...);
     * } finblly {
     *   repbintMbnbger.endPbint();
     * }
     * </pre>
     */
    void beginPbint() {
        boolebn multiThrebdedPbint = fblse;
        int pbintDepth;
        Threbd currentThrebd = Threbd.currentThrebd();
        synchronized(this) {
            pbintDepth = this.pbintDepth;
            if (pbintThrebd == null || currentThrebd == pbintThrebd) {
                pbintThrebd = currentThrebd;
                this.pbintDepth++;
            } else {
                multiThrebdedPbint = true;
            }
        }
        if (!multiThrebdedPbint && pbintDepth == 0) {
            getPbintMbnbger().beginPbint();
        }
    }

    /**
     * Invoked bfter <code>beginPbint</code> hbs been invoked.
     */
    void endPbint() {
        if (isPbintingThrebd()) {
            PbintMbnbger pbintMbnbger = null;
            synchronized(this) {
                if (--pbintDepth == 0) {
                    pbintMbnbger = getPbintMbnbger();
                }
            }
            if (pbintMbnbger != null) {
                pbintMbnbger.endPbint();
                synchronized(this) {
                    pbintThrebd = null;
                }
            }
        }
    }

    /**
     * If possible this will show b previously rendered portion of
     * b Component.  If successful, this will return true, otherwise fblse.
     * <p>
     * WARNING: This method is invoked from the nbtive toolkit threbd, be
     * very cbreful bs to whbt methods this invokes!
     */
    boolebn show(Contbiner c, int x, int y, int w, int h) {
        return getPbintMbnbger().show(c, x, y, w, h);
    }

    /**
     * Invoked when the doubleBuffered or useTrueDoubleBuffering
     * properties of b JRootPbne chbnge.  This mby come in on bny threbd.
     */
    void doubleBufferingChbnged(JRootPbne rootPbne) {
        getPbintMbnbger().doubleBufferingChbnged(rootPbne);
    }

    /**
     * Sets the <code>PbintMbnbger</code> thbt is used to hbndle bll
     * double buffered pbinting.
     *
     * @pbrbm pbintMbnbger The PbintMbnbger to use.  Pbssing in null indicbtes
     *        the fbllbbck PbintMbnbger should be used.
     */
    void setPbintMbnbger(PbintMbnbger pbintMbnbger) {
        if (pbintMbnbger == null) {
            pbintMbnbger = new PbintMbnbger();
        }
        PbintMbnbger oldPbintMbnbger;
        synchronized(this) {
            oldPbintMbnbger = this.pbintMbnbger;
            this.pbintMbnbger = pbintMbnbger;
            pbintMbnbger.repbintMbnbger = this;
        }
        if (oldPbintMbnbger != null) {
            oldPbintMbnbger.dispose();
        }
    }

    privbte synchronized PbintMbnbger getPbintMbnbger() {
        if (pbintMbnbger == null) {
            PbintMbnbger pbintMbnbger = null;
            if (doubleBufferingEnbbled && !nbtiveDoubleBuffering) {
                switch (bufferStrbtegyType) {
                cbse BUFFER_STRATEGY_NOT_SPECIFIED:
                    Toolkit tk = Toolkit.getDefbultToolkit();
                    if (tk instbnceof SunToolkit) {
                        SunToolkit stk = (SunToolkit) tk;
                        if (stk.useBufferPerWindow()) {
                            pbintMbnbger = new BufferStrbtegyPbintMbnbger();
                        }
                    }
                    brebk;
                cbse BUFFER_STRATEGY_SPECIFIED_ON:
                    pbintMbnbger = new BufferStrbtegyPbintMbnbger();
                    brebk;
                defbult:
                    brebk;
                }
            }
            // null cbse hbndled in setPbintMbnbger
            setPbintMbnbger(pbintMbnbger);
        }
        return pbintMbnbger;
    }

    privbte void scheduleProcessingRunnbble(AppContext context) {
        if (processingRunnbble.mbrkPending()) {
            Toolkit tk = Toolkit.getDefbultToolkit();
            if (tk instbnceof SunToolkit) {
                SunToolkit.getSystemEventQueueImplPP(context).
                  postEvent(new InvocbtionEvent(Toolkit.getDefbultToolkit(),
                                                processingRunnbble));
            } else {
                Toolkit.getDefbultToolkit().getSystemEventQueue().
                      postEvent(new InvocbtionEvent(Toolkit.getDefbultToolkit(),
                                                    processingRunnbble));
            }
        }
    }


    /**
     * PbintMbnbger is used to hbndle bll double buffered pbinting for
     * Swing.  Subclbsses should cbll bbck into the JComponent method
     * <code>pbintToOffscreen</code> to hbndle the bctubl pbinting.
     */
    stbtic clbss PbintMbnbger {
        /**
         * RepbintMbnbger the PbintMbnbger hbs been instblled on.
         */
        protected RepbintMbnbger repbintMbnbger;
        boolebn isRepbintingRoot;

        /**
         * Pbints b region of b component
         *
         * @pbrbm pbintingComponent Component to pbint
         * @pbrbm bufferComponent Component to obtbin buffer for
         * @pbrbm g Grbphics to pbint to
         * @pbrbm x X-coordinbte
         * @pbrbm y Y-coordinbte
         * @pbrbm w Width
         * @pbrbm h Height
         * @return true if pbinting wbs successful.
         */
        public boolebn pbint(JComponent pbintingComponent,
                             JComponent bufferComponent, Grbphics g,
                             int x, int y, int w, int h) {
            // First bttempt to use VolbtileImbge buffer for performbnce.
            // If this fbils (which should rbrely occur), fbllbbck to b
            // stbndbrd Imbge buffer.
            boolebn pbintCompleted = fblse;
            Imbge offscreen;
            if (repbintMbnbger.useVolbtileDoubleBuffer() &&
                (offscreen = getVblidImbge(repbintMbnbger.
                getVolbtileOffscreenBuffer(bufferComponent, w, h))) != null) {
                VolbtileImbge vImbge = (jbvb.bwt.imbge.VolbtileImbge)offscreen;
                GrbphicsConfigurbtion gc = bufferComponent.
                                            getGrbphicsConfigurbtion();
                for (int i = 0; !pbintCompleted &&
                         i < RepbintMbnbger.VOLATILE_LOOP_MAX; i++) {
                    if (vImbge.vblidbte(gc) ==
                                   VolbtileImbge.IMAGE_INCOMPATIBLE) {
                        repbintMbnbger.resetVolbtileDoubleBuffer(gc);
                        offscreen = repbintMbnbger.getVolbtileOffscreenBuffer(
                            bufferComponent,w, h);
                        vImbge = (jbvb.bwt.imbge.VolbtileImbge)offscreen;
                    }
                    pbintDoubleBuffered(pbintingComponent, vImbge, g, x, y,
                                        w, h);
                    pbintCompleted = !vImbge.contentsLost();
                }
            }
            // VolbtileImbge pbinting loop fbiled, fbllbbck to regulbr
            // offscreen buffer
            if (!pbintCompleted && (offscreen = getVblidImbge(
                      repbintMbnbger.getOffscreenBuffer(
                      bufferComponent, w, h))) != null) {
                pbintDoubleBuffered(pbintingComponent, offscreen, g, x, y, w,
                                    h);
                pbintCompleted = true;
            }
            return pbintCompleted;
        }

        /**
         * Does b copy breb on the specified region.
         */
        public void copyAreb(JComponent c, Grbphics g, int x, int y, int w,
                             int h, int deltbX, int deltbY, boolebn clip) {
            g.copyAreb(x, y, w, h, deltbX, deltbY);
        }

        /**
         * Invoked prior to bny cblls to pbint or copyAreb.
         */
        public void beginPbint() {
        }

        /**
         * Invoked to indicbte pbinting hbs been completed.
         */
        public void endPbint() {
        }

        /**
         * Shows b region of b previously rendered component.  This
         * will return true if successful, fblse otherwise.  The defbult
         * implementbtion returns fblse.
         */
        public boolebn show(Contbiner c, int x, int y, int w, int h) {
            return fblse;
        }

        /**
         * Invoked when the doubleBuffered or useTrueDoubleBuffering
         * properties of b JRootPbne chbnge.  This mby come in on bny threbd.
         */
        public void doubleBufferingChbnged(JRootPbne rootPbne) {
        }

        /**
         * Pbints b portion of b component to bn offscreen buffer.
         */
        protected void pbintDoubleBuffered(JComponent c, Imbge imbge,
                            Grbphics g, int clipX, int clipY,
                            int clipW, int clipH) {
            Grbphics osg = imbge.getGrbphics();
            int bw = Mbth.min(clipW, imbge.getWidth(null));
            int bh = Mbth.min(clipH, imbge.getHeight(null));
            int x,y,mbxx,mbxy;

            try {
                for(x = clipX, mbxx = clipX+clipW; x < mbxx ;  x += bw ) {
                    for(y=clipY, mbxy = clipY + clipH; y < mbxy ; y += bh) {
                        osg.trbnslbte(-x, -y);
                        osg.setClip(x,y,bw,bh);
                        if (volbtileBufferType != Trbnspbrency.OPAQUE
                                && osg instbnceof Grbphics2D) {
                            finbl Grbphics2D g2d = (Grbphics2D) osg;
                            finbl Color oldBg = g2d.getBbckground();
                            g2d.setBbckground(c.getBbckground());
                            g2d.clebrRect(x, y, bw, bh);
                            g2d.setBbckground(oldBg);
                        }
                        c.pbintToOffscreen(osg, x, y, bw, bh, mbxx, mbxy);
                        g.setClip(x, y, bw, bh);
                        if (volbtileBufferType != Trbnspbrency.OPAQUE
                                && g instbnceof Grbphics2D) {
                            finbl Grbphics2D g2d = (Grbphics2D) g;
                            finbl Composite oldComposite = g2d.getComposite();
                            g2d.setComposite(AlphbComposite.Src);
                            g2d.drbwImbge(imbge, x, y, c);
                            g2d.setComposite(oldComposite);
                        } else {
                            g.drbwImbge(imbge, x, y, c);
                        }
                        osg.trbnslbte(x, y);
                    }
                }
            } finblly {
                osg.dispose();
            }
        }

        /**
         * If <code>imbge</code> is non-null with b positive size it
         * is returned, otherwise null is returned.
         */
        privbte Imbge getVblidImbge(Imbge imbge) {
            if (imbge != null && imbge.getWidth(null) > 0 &&
                                 imbge.getHeight(null) > 0) {
                return imbge;
            }
            return null;
        }

        /**
         * Schedules b repbint for the specified component.  This differs
         * from <code>root.repbint</code> in thbt if the RepbintMbnbger is
         * currently processing pbint requests it'll process this request
         * with the current set of requests.
         */
        protected void repbintRoot(JComponent root) {
            bssert (repbintMbnbger.repbintRoot == null);
            if (repbintMbnbger.pbinting) {
                repbintMbnbger.repbintRoot = root;
            }
            else {
                root.repbint();
            }
        }

        /**
         * Returns true if the component being pbinted is the root component
         * thbt wbs previously pbssed to <code>repbintRoot</code>.
         */
        protected boolebn isRepbintingRoot() {
            return isRepbintingRoot;
        }

        /**
         * Clebns up bny stbte.  After invoked the PbintMbnbger will no
         * longer be used bnymore.
         */
        protected void dispose() {
        }
    }


    privbte clbss DoubleBufferInfo {
        public Imbge imbge;
        public Dimension size;
        public boolebn needsReset = fblse;
    }


    /**
     * Listener instblled to detect displby chbnges. When displby chbnges,
     * schedules b cbllbbck to notify bll RepbintMbnbgers of the displby
     * chbnges. Only one DisplbyChbngedHbndler is ever instblled. The
     * singleton instbnce will schedule notificbtion for bll AppContexts.
     */
    privbte stbtic finbl clbss DisplbyChbngedHbndler implements
                                             DisplbyChbngedListener {
        public void displbyChbnged() {
            scheduleDisplbyChbnges();
        }

        public void pbletteChbnged() {
        }

        privbte void scheduleDisplbyChbnges() {
            // To bvoid threbding problems, we notify ebch RepbintMbnbger
            // on the threbd it wbs crebted on.
            for (Object c : AppContext.getAppContexts()) {
                AppContext context = (AppContext) c;
                synchronized(context) {
                    if (!context.isDisposed()) {
                        EventQueue eventQueue = (EventQueue)context.get(
                            AppContext.EVENT_QUEUE_KEY);
                        if (eventQueue != null) {
                            eventQueue.postEvent(new InvocbtionEvent(
                                Toolkit.getDefbultToolkit(),
                                new DisplbyChbngedRunnbble()));
                        }
                    }
                }
            }
        }
    }


    privbte stbtic finbl clbss DisplbyChbngedRunnbble implements Runnbble {
        public void run() {
            RepbintMbnbger.currentMbnbger((JComponent)null).displbyChbnged();
        }
    }


    /**
     * Runnbble used to process bll repbint/revblidbte requests.
     */
    privbte finbl clbss ProcessingRunnbble implements Runnbble {
        // If true, we're wbinting on the EventQueue.
        privbte boolebn pending;

        /**
         * Mbrks this processing runnbble bs pending. If this wbs not
         * blrebdy mbrked bs pending, true is returned.
         */
        public synchronized boolebn mbrkPending() {
            if (!pending) {
                pending = true;
                return true;
            }
            return fblse;
        }

        public void run() {
            synchronized (this) {
                pending = fblse;
            }
            // First pbss, flush bny hebvy pbint events into rebl pbint
            // events.  If there bre pending hebvy weight requests this will
            // result in q'ing this request up one more time.  As
            // long bs no other requests come in between now bnd the time
            // the second one is processed nothing will hbppen.  This is not
            // idebl, but the logic needed to suppress the second request is
            // more hebdbche thbn it's worth.
            scheduleHebvyWeightPbints();
            // Do the bctubl vblidbtion bnd pbinting.
            vblidbteInvblidComponents();
            prePbintDirtyRegions();
        }
    }
    privbte RepbintMbnbger getDelegbte(Component c) {
        RepbintMbnbger delegbte = SwingUtilities3.getDelegbteRepbintMbnbger(c);
        if (this == delegbte) {
            delegbte = null;
        }
        return delegbte;
    }
}
