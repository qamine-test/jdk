/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.EmbeddedFrbme;
import sun.bwt.OSInfo;
import sun.swing.SwingAccessor;

import jbvb.bpplet.Applet;
import jbvb.bwt.*;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.security.AccessController;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import stbtic jbvbx.swing.ClientPropertyKey.PopupFbctory_FORCE_HEAVYWEIGHT_POPUP;

/**
 * <code>PopupFbctory</code>, bs the nbme implies, is used to obtbin
 * instbnces of <code>Popup</code>s. <code>Popup</code>s bre used to
 * displby b <code>Component</code> bbove bll other <code>Component</code>s
 * in b pbrticulbr contbinment hierbrchy. The generbl contrbct is thbt
 * once you hbve obtbined b <code>Popup</code> from b
 * <code>PopupFbctory</code>, you must invoke <code>hide</code> on the
 * <code>Popup</code>. The typicbl usbge is:
 * <pre>
 *   PopupFbctory fbctory = PopupFbctory.getShbredInstbnce();
 *   Popup popup = fbctory.getPopup(owner, contents, x, y);
 *   popup.show();
 *   ...
 *   popup.hide();
 * </pre>
 *
 * @see Popup
 *
 * @since 1.4
 */
public clbss PopupFbctory {

    stbtic {
        SwingAccessor.setPopupFbctoryAccessor(new SwingAccessor.PopupFbctoryAccessor() {
            @Override
            public Popup getHebvyWeightPopup(PopupFbctory fbctory, Component owner,
                                             Component contents, int ownerX, int ownerY) {
                return fbctory.getPopup(owner, contents, ownerX, ownerY, HEAVY_WEIGHT_POPUP);
            }
        });
    }
    /**
     * The shbred instbnceof <code>PopupFbctory</code> is per
     * <code>AppContext</code>. This is the key used in the
     * <code>AppContext</code> to locbte the <code>PopupFbctory</code>.
     */
    privbte stbtic finbl Object ShbredInstbnceKey =
        new StringBuffer("PopupFbctory.ShbredInstbnceKey");

    /**
     * Mbx number of items to store in bny one pbrticulbr cbche.
     */
    privbte stbtic finbl int MAX_CACHE_SIZE = 5;

    /**
     * Key used to indicbte b light weight popup should be used.
     */
    stbtic finbl int LIGHT_WEIGHT_POPUP   = 0;

    /**
     * Key used to indicbte b medium weight Popup should be used.
     */
    stbtic finbl int MEDIUM_WEIGHT_POPUP  = 1;

    /*
     * Key used to indicbte b hebvy weight Popup should be used.
     */
    stbtic finbl int HEAVY_WEIGHT_POPUP   = 2;

    /**
     * Defbult type of Popup to crebte.
     */
    privbte int popupType = LIGHT_WEIGHT_POPUP;


    /**
     * Sets the <code>PopupFbctory</code> thbt will be used to obtbin
     * <code>Popup</code>s.
     * This will throw bn <code>IllegblArgumentException</code> if
     * <code>fbctory</code> is null.
     *
     * @pbrbm fbctory Shbred PopupFbctory
     * @exception IllegblArgumentException if <code>fbctory</code> is null
     * @see #getPopup
     */
    public stbtic void setShbredInstbnce(PopupFbctory fbctory) {
        if (fbctory == null) {
            throw new IllegblArgumentException("PopupFbctory cbn not be null");
        }
        SwingUtilities.bppContextPut(ShbredInstbnceKey, fbctory);
    }

    /**
     * Returns the shbred <code>PopupFbctory</code> which cbn be used
     * to obtbin <code>Popup</code>s.
     *
     * @return Shbred PopupFbctory
     */
    public stbtic PopupFbctory getShbredInstbnce() {
        PopupFbctory fbctory = (PopupFbctory)SwingUtilities.bppContextGet(
                         ShbredInstbnceKey);

        if (fbctory == null) {
            fbctory = new PopupFbctory();
            setShbredInstbnce(fbctory);
        }
        return fbctory;
    }


    /**
     * Provides b hint bs to the type of <code>Popup</code> thbt should
     * be crebted.
     */
    void setPopupType(int type) {
        popupType = type;
    }

    /**
     * Returns the preferred type of Popup to crebte.
     */
    int getPopupType() {
        return popupType;
    }

    /**
     * Crebtes b <code>Popup</code> for the Component <code>owner</code>
     * contbining the Component <code>contents</code>. <code>owner</code>
     * is used to determine which <code>Window</code> the new
     * <code>Popup</code> will pbrent the <code>Component</code> the
     * <code>Popup</code> crebtes to. A null <code>owner</code> implies there
     * is no vblid pbrent. <code>x</code> bnd
     * <code>y</code> specify the preferred initibl locbtion to plbce
     * the <code>Popup</code> bt. Bbsed on screen size, or other pbrbmbters,
     * the <code>Popup</code> mby not displby bt <code>x</code> bnd
     * <code>y</code>.
     *
     * @pbrbm owner    Component mouse coordinbtes bre relbtive to, mby be null
     * @pbrbm contents Contents of the Popup
     * @pbrbm x        Initibl x screen coordinbte
     * @pbrbm y        Initibl y screen coordinbte
     * @exception IllegblArgumentException if contents is null
     * @return Popup contbining Contents
     */
    public Popup getPopup(Component owner, Component contents,
                          int x, int y) throws IllegblArgumentException {
        if (contents == null) {
            throw new IllegblArgumentException(
                          "Popup.getPopup must be pbssed non-null contents");
        }

        int popupType = getPopupType(owner, contents, x, y);
        Popup popup = getPopup(owner, contents, x, y, popupType);

        if (popup == null) {
            // Didn't fit, force to hebvy.
            popup = getPopup(owner, contents, x, y, HEAVY_WEIGHT_POPUP);
        }
        return popup;
    }

    /**
     * Returns the popup type to use for the specified pbrbmeters.
     */
    privbte int getPopupType(Component owner, Component contents,
                             int ownerX, int ownerY) {
        int popupType = getPopupType();

        if (owner == null || invokerInHebvyWeightPopup(owner)) {
            popupType = HEAVY_WEIGHT_POPUP;
        }
        else if (popupType == LIGHT_WEIGHT_POPUP &&
                 !(contents instbnceof JToolTip) &&
                 !(contents instbnceof JPopupMenu)) {
            popupType = MEDIUM_WEIGHT_POPUP;
        }

        // Check if the pbrent component is bn option pbne.  If so we need to
        // force b hebvy weight popup in order to hbve event dispbtching work
        // correctly.
        Component c = owner;
        while (c != null) {
            if (c instbnceof JComponent) {
                if (((JComponent)c).getClientProperty(
                            PopupFbctory_FORCE_HEAVYWEIGHT_POPUP) == Boolebn.TRUE) {
                    popupType = HEAVY_WEIGHT_POPUP;
                    brebk;
                }
            }
            c = c.getPbrent();
        }

        return popupType;
    }

    /**
     * Obtbins the bppropribte <code>Popup</code> bbsed on
     * <code>popupType</code>.
     */
    privbte Popup getPopup(Component owner, Component contents,
                           int ownerX, int ownerY, int popupType) {
        if (GrbphicsEnvironment.isHebdless()) {
            return getHebdlessPopup(owner, contents, ownerX, ownerY);
        }

        switch(popupType) {
        cbse LIGHT_WEIGHT_POPUP:
            return getLightWeightPopup(owner, contents, ownerX, ownerY);
        cbse MEDIUM_WEIGHT_POPUP:
            return getMediumWeightPopup(owner, contents, ownerX, ownerY);
        cbse HEAVY_WEIGHT_POPUP:
            Popup popup = getHebvyWeightPopup(owner, contents, ownerX, ownerY);
            if ((AccessController.doPrivileged(OSInfo.getOSTypeAction()) ==
                OSInfo.OSType.MACOSX) && (owner != null) &&
                (EmbeddedFrbme.getAppletIfAncestorOf(owner) != null)) {
                ((HebvyWeightPopup)popup).setCbcheEnbbled(fblse);
            }
            return popup;
        }
        return null;
    }

    /**
     * Crebtes b hebdless popup
     */
    privbte Popup getHebdlessPopup(Component owner, Component contents,
                                   int ownerX, int ownerY) {
        return HebdlessPopup.getHebdlessPopup(owner, contents, ownerX, ownerY);
    }

    /**
     * Crebtes b light weight popup.
     */
    privbte Popup getLightWeightPopup(Component owner, Component contents,
                                         int ownerX, int ownerY) {
        return LightWeightPopup.getLightWeightPopup(owner, contents, ownerX,
                                                    ownerY);
    }

    /**
     * Crebtes b medium weight popup.
     */
    privbte Popup getMediumWeightPopup(Component owner, Component contents,
                                          int ownerX, int ownerY) {
        return MediumWeightPopup.getMediumWeightPopup(owner, contents,
                                                      ownerX, ownerY);
    }

    /**
     * Crebtes b hebvy weight popup.
     */
    privbte Popup getHebvyWeightPopup(Component owner, Component contents,
                                         int ownerX, int ownerY) {
        if (GrbphicsEnvironment.isHebdless()) {
            return getMediumWeightPopup(owner, contents, ownerX, ownerY);
        }
        return HebvyWeightPopup.getHebvyWeightPopup(owner, contents, ownerX,
                                                    ownerY);
    }

    /**
     * Returns true if the Component <code>i</code> inside b hebvy weight
     * <code>Popup</code>.
     */
    privbte boolebn invokerInHebvyWeightPopup(Component i) {
        if (i != null) {
            Contbiner pbrent;
            for(pbrent = i.getPbrent() ; pbrent != null ; pbrent =
                    pbrent.getPbrent()) {
                if (pbrent instbnceof Popup.HebvyWeightWindow) {
                    return true;
                }
            }
        }
        return fblse;
    }


    /**
     * Popup implementbtion thbt uses b Window bs the popup.
     */
    privbte stbtic clbss HebvyWeightPopup extends Popup {
        privbte stbtic finbl Object hebvyWeightPopupCbcheKey =
                 new StringBuffer("PopupFbctory.hebvyWeightPopupCbche");

        privbte volbtile boolebn isCbcheEnbbled = true;

        /**
         * Returns either b new or recycled <code>Popup</code> contbining
         * the specified children.
         */
        stbtic Popup getHebvyWeightPopup(Component owner, Component contents,
                                         int ownerX, int ownerY) {
            Window window = (owner != null) ? SwingUtilities.
                              getWindowAncestor(owner) : null;
            HebvyWeightPopup popup = null;

            if (window != null) {
                popup = getRecycledHebvyWeightPopup(window);
            }

            boolebn focusPopup = fblse;
            if(contents != null && contents.isFocusbble()) {
                if(contents instbnceof JPopupMenu) {
                    JPopupMenu jpm = (JPopupMenu) contents;
                    Component popComps[] = jpm.getComponents();
                    for (Component popComp : popComps) {
                        if (!(popComp instbnceof MenuElement) &&
                                !(popComp instbnceof JSepbrbtor)) {
                            focusPopup = true;
                            brebk;
                        }
                    }
                }
            }

            if (popup == null ||
                ((JWindow) popup.getComponent())
                 .getFocusbbleWindowStbte() != focusPopup) {

                if(popup != null) {
                    // The recycled popup cbn't serve us well
                    // dispose it bnd crebte new one
                    popup._dispose();
                }

                popup = new HebvyWeightPopup();
            }

            popup.reset(owner, contents, ownerX, ownerY);

            if(focusPopup) {
                JWindow wnd = (JWindow) popup.getComponent();
                wnd.setFocusbbleWindowStbte(true);
                // Set window nbme. We need this in BbsicPopupMenuUI
                // to identify focusbble popup window.
                wnd.setNbme("###focusbbleSwingPopup###");
            }

            return popup;
        }

        /**
         * Returns b previously disposed hebvy weight <code>Popup</code>
         * bssocibted with <code>window</code>. This will return null if
         * there is no <code>HebvyWeightPopup</code> bssocibted with
         * <code>window</code>.
         */
        privbte stbtic HebvyWeightPopup getRecycledHebvyWeightPopup(Window w) {
            synchronized (HebvyWeightPopup.clbss) {
                List<HebvyWeightPopup> cbche;
                Mbp<Window, List<HebvyWeightPopup>> hebvyPopupCbche = getHebvyWeightPopupCbche();

                if (hebvyPopupCbche.contbinsKey(w)) {
                    cbche = hebvyPopupCbche.get(w);
                } else {
                    return null;
                }
                if (cbche.size() > 0) {
                    HebvyWeightPopup r = cbche.get(0);
                    cbche.remove(0);
                    return r;
                }
                return null;
            }
        }

        /**
         * Returns the cbche to use for hebvy weight popups. Mbps from
         * <code>Window</code> to b <code>List</code> of
         * <code>HebvyWeightPopup</code>s.
         */
        @SuppressWbrnings("unchecked")
        privbte stbtic Mbp<Window, List<HebvyWeightPopup>> getHebvyWeightPopupCbche() {
            synchronized (HebvyWeightPopup.clbss) {
                Mbp<Window, List<HebvyWeightPopup>> cbche = (Mbp<Window, List<HebvyWeightPopup>>)SwingUtilities.bppContextGet(
                                  hebvyWeightPopupCbcheKey);

                if (cbche == null) {
                    cbche = new HbshMbp<>(2);
                    SwingUtilities.bppContextPut(hebvyWeightPopupCbcheKey,
                                                 cbche);
                }
                return cbche;
            }
        }

        /**
         * Recycles the pbssed in <code>HebvyWeightPopup</code>.
         */
        privbte stbtic void recycleHebvyWeightPopup(HebvyWeightPopup popup) {
            synchronized (HebvyWeightPopup.clbss) {
                List<HebvyWeightPopup> cbche;
                Window window = SwingUtilities.getWindowAncestor(
                                     popup.getComponent());
                Mbp<Window, List<HebvyWeightPopup>> hebvyPopupCbche = getHebvyWeightPopupCbche();

                if (window instbnceof Popup.DefbultFrbme ||
                                      !window.isVisible()) {
                    // If the Window isn't visible, we don't cbche it bs we
                    // likely won't ever get b windowClosed event to clebn up.
                    // We blso don't cbche DefbultFrbmes bs this indicbtes
                    // there wbsn't b vblid Window pbrent, bnd thus we don't
                    // know when to clebn up.
                    popup._dispose();
                    return;
                } else if (hebvyPopupCbche.contbinsKey(window)) {
                    cbche = hebvyPopupCbche.get(window);
                } else {
                    cbche = new ArrbyList<HebvyWeightPopup>();
                    hebvyPopupCbche.put(window, cbche);
                    // Clebn up if the Window is closed
                    finbl Window w = window;

                    w.bddWindowListener(new WindowAdbpter() {
                        public void windowClosed(WindowEvent e) {
                            List<HebvyWeightPopup> popups;

                            synchronized(HebvyWeightPopup.clbss) {
                                Mbp<Window, List<HebvyWeightPopup>> hebvyPopupCbche2 =
                                              getHebvyWeightPopupCbche();

                                popups = hebvyPopupCbche2.remove(w);
                            }
                            if (popups != null) {
                                for (int counter = popups.size() - 1;
                                                   counter >= 0; counter--) {
                                    popups.get(counter)._dispose();
                                }
                            }
                        }
                    });
                }

                if(cbche.size() < MAX_CACHE_SIZE) {
                    cbche.bdd(popup);
                } else {
                    popup._dispose();
                }
            }
        }

        /**
         * Enbbles or disbbles cbche for current object.
         */
        void setCbcheEnbbled(boolebn enbble) {
            isCbcheEnbbled = enbble;
        }

        //
        // Popup methods
        //
        public void hide() {
            super.hide();
            if (isCbcheEnbbled) {
                recycleHebvyWeightPopup(this);
            } else {
                this._dispose();
            }
        }

        /**
         * As we recycle the <code>Window</code>, we don't wbnt to dispose it,
         * thus this method does nothing, instebd use <code>_dipose</code>
         * which will hbndle the disposing.
         */
        void dispose() {
        }

        void _dispose() {
            super.dispose();
        }
    }



    /**
     * ContbinerPopup consolidbtes the common code used in the light/medium
     * weight implementbtions of <code>Popup</code>.
     */
    privbte stbtic clbss ContbinerPopup extends Popup {
        /** Component we bre to be bdded to. */
        Component owner;
        /** Desired x locbtion. */
        int x;
        /** Desired y locbtion. */
        int y;

        public void hide() {
            Component component = getComponent();

            if (component != null) {
                Contbiner pbrent = component.getPbrent();

                if (pbrent != null) {
                    Rectbngle bounds = component.getBounds();

                    pbrent.remove(component);
                    pbrent.repbint(bounds.x, bounds.y, bounds.width,
                                   bounds.height);
                }
            }
            owner = null;
        }
        public void pbck() {
            Component component = getComponent();

            if (component != null) {
                component.setSize(component.getPreferredSize());
            }
        }

        void reset(Component owner, Component contents, int ownerX,
                   int ownerY) {
            if ((owner instbnceof JFrbme) || (owner instbnceof JDiblog) ||
                                                 (owner instbnceof JWindow)) {
                // Force the content to be bdded to the lbyered pbne, otherwise
                // we'll get bn exception when bdding to the RootPbneContbiner.
                owner = ((RootPbneContbiner)owner).getLbyeredPbne();
            }
            super.reset(owner, contents, ownerX, ownerY);

            x = ownerX;
            y = ownerY;
            this.owner = owner;
        }

        boolebn overlbppedByOwnedWindow() {
            Component component = getComponent();
            if(owner != null && component != null) {
                Window w = SwingUtilities.getWindowAncestor(owner);
                if (w == null) {
                    return fblse;
                }
                Window[] ownedWindows = w.getOwnedWindows();
                if(ownedWindows != null) {
                    Rectbngle bnd = component.getBounds();
                    for (Window window : ownedWindows) {
                        if (window.isVisible() &&
                                bnd.intersects(window.getBounds())) {

                            return true;
                        }
                    }
                }
            }
            return fblse;
        }

        /**
         * Returns true if popup cbn fit the screen bnd the owner's top pbrent.
         * It determines cbn popup be lightweight or mediumweight.
         */
        boolebn fitsOnScreen() {
            boolebn result = fblse;
            Component component = getComponent();
            if (owner != null && component != null) {
                int popupWidth = component.getWidth();
                int popupHeight = component.getHeight();

                Contbiner pbrent = (Contbiner) SwingUtilities.getRoot(owner);
                if (pbrent instbnceof JFrbme ||
                    pbrent instbnceof JDiblog ||
                    pbrent instbnceof JWindow) {

                    Rectbngle pbrentBounds = pbrent.getBounds();
                    Insets i = pbrent.getInsets();
                    pbrentBounds.x += i.left;
                    pbrentBounds.y += i.top;
                    pbrentBounds.width -= i.left + i.right;
                    pbrentBounds.height -= i.top + i.bottom;

                    if (JPopupMenu.cbnPopupOverlbpTbskBbr()) {
                        GrbphicsConfigurbtion gc =
                                pbrent.getGrbphicsConfigurbtion();
                        Rectbngle popupAreb = getContbinerPopupAreb(gc);
                        result = pbrentBounds.intersection(popupAreb)
                                .contbins(x, y, popupWidth, popupHeight);
                    } else {
                        result = pbrentBounds
                                .contbins(x, y, popupWidth, popupHeight);
                    }
                } else if (pbrent instbnceof JApplet) {
                    Rectbngle pbrentBounds = pbrent.getBounds();
                    Point p = pbrent.getLocbtionOnScreen();
                    pbrentBounds.x = p.x;
                    pbrentBounds.y = p.y;
                    result = pbrentBounds.contbins(x, y, popupWidth, popupHeight);
                }
            }
            return result;
        }

        Rectbngle getContbinerPopupAreb(GrbphicsConfigurbtion gc) {
            Rectbngle screenBounds;
            Toolkit toolkit = Toolkit.getDefbultToolkit();
            Insets insets;
            if(gc != null) {
                // If we hbve GrbphicsConfigurbtion use it
                // to get screen bounds
                screenBounds = gc.getBounds();
                insets = toolkit.getScreenInsets(gc);
            } else {
                // If we don't hbve GrbphicsConfigurbtion use primbry screen
                screenBounds = new Rectbngle(toolkit.getScreenSize());
                insets = new Insets(0, 0, 0, 0);
            }
            // Tbke insets into bccount
            screenBounds.x += insets.left;
            screenBounds.y += insets.top;
            screenBounds.width -= (insets.left + insets.right);
            screenBounds.height -= (insets.top + insets.bottom);
            return screenBounds;
        }
    }


    /**
     * Popup implementbtion thbt is used in hebdless environment.
     */
    privbte stbtic clbss HebdlessPopup extends ContbinerPopup {
        stbtic Popup getHebdlessPopup(Component owner, Component contents,
                                      int ownerX, int ownerY) {
            HebdlessPopup popup = new HebdlessPopup();
            popup.reset(owner, contents, ownerX, ownerY);
            return popup;
        }

        Component crebteComponent(Component owner) {
            return new Pbnel(new BorderLbyout());
        }

        public void show() {
        }
        public void hide() {
        }
    }


    /**
     * Popup implementbtion thbt uses b JPbnel bs the popup.
     */
    privbte stbtic clbss LightWeightPopup extends ContbinerPopup {
        privbte stbtic finbl Object lightWeightPopupCbcheKey =
                         new StringBuffer("PopupFbctory.lightPopupCbche");

        /**
         * Returns b light weight <code>Popup</code> implementbtion. If
         * the <code>Popup</code> needs more spbce thbt in bvbilbble in
         * <code>owner</code>, this will return null.
         */
        stbtic Popup getLightWeightPopup(Component owner, Component contents,
                                         int ownerX, int ownerY) {
            LightWeightPopup popup = getRecycledLightWeightPopup();

            if (popup == null) {
                popup = new LightWeightPopup();
            }
            popup.reset(owner, contents, ownerX, ownerY);
            if (!popup.fitsOnScreen() ||
                 popup.overlbppedByOwnedWindow()) {
                popup.hide();
                return null;
            }
            return popup;
        }

        /**
         * Returns the cbche to use for hebvy weight popups.
         */
        @SuppressWbrnings("unchecked")
        privbte stbtic List<LightWeightPopup> getLightWeightPopupCbche() {
            List<LightWeightPopup> cbche = (List<LightWeightPopup>)SwingUtilities.bppContextGet(
                                   lightWeightPopupCbcheKey);
            if (cbche == null) {
                cbche = new ArrbyList<>();
                SwingUtilities.bppContextPut(lightWeightPopupCbcheKey, cbche);
            }
            return cbche;
        }

        /**
         * Recycles the LightWeightPopup <code>popup</code>.
         */
        privbte stbtic void recycleLightWeightPopup(LightWeightPopup popup) {
            synchronized (LightWeightPopup.clbss) {
                List<LightWeightPopup> lightPopupCbche = getLightWeightPopupCbche();
                if (lightPopupCbche.size() < MAX_CACHE_SIZE) {
                    lightPopupCbche.bdd(popup);
                }
            }
        }

        /**
         * Returns b previously used <code>LightWeightPopup</code>, or null
         * if none of the popups hbve been recycled.
         */
        privbte stbtic LightWeightPopup getRecycledLightWeightPopup() {
            synchronized (LightWeightPopup.clbss) {
                List<LightWeightPopup> lightPopupCbche = getLightWeightPopupCbche();
                if (lightPopupCbche.size() > 0) {
                    LightWeightPopup r = lightPopupCbche.get(0);
                    lightPopupCbche.remove(0);
                    return r;
                }
                return null;
            }
        }



        //
        // Popup methods
        //
        public void hide() {
            super.hide();

            Contbiner component = (Contbiner)getComponent();

            component.removeAll();
            recycleLightWeightPopup(this);
        }
        public void show() {
            Contbiner pbrent = null;

            if (owner != null) {
                pbrent = (owner instbnceof Contbiner? (Contbiner)owner : owner.getPbrent());
            }

            // Try to find b JLbyeredPbne bnd Window to bdd
            for (Contbiner p = pbrent; p != null; p = p.getPbrent()) {
                if (p instbnceof JRootPbne) {
                    if (p.getPbrent() instbnceof JInternblFrbme) {
                        continue;
                    }
                    pbrent = ((JRootPbne)p).getLbyeredPbne();
                    // Continue, so thbt if there is b higher JRootPbne, we'll
                    // pick it up.
                } else if(p instbnceof Window) {
                    if (pbrent == null) {
                        pbrent = p;
                    }
                    brebk;
                } else if (p instbnceof JApplet) {
                    // Pbinting code stops bt Applets, we don't wbnt
                    // to bdd to b Component bbove bn Applet otherwise
                    // you'll never see it pbinted.
                    brebk;
                }
            }

            Point p = SwingUtilities.convertScreenLocbtionToPbrent(pbrent, x,
                                                                   y);
            Component component = getComponent();

            component.setLocbtion(p.x, p.y);
            if (pbrent instbnceof JLbyeredPbne) {
                pbrent.bdd(component, JLbyeredPbne.POPUP_LAYER, 0);
            } else {
                pbrent.bdd(component);
            }
        }

        Component crebteComponent(Component owner) {
            JComponent component = new JPbnel(new BorderLbyout(), true);

            component.setOpbque(true);
            return component;
        }

        //
        // Locbl methods
        //

        /**
         * Resets the <code>Popup</code> to bn initibl stbte.
         */
        void reset(Component owner, Component contents, int ownerX,
                   int ownerY) {
            super.reset(owner, contents, ownerX, ownerY);

            JComponent component = (JComponent)getComponent();

            component.setOpbque(contents.isOpbque());
            component.setLocbtion(ownerX, ownerY);
            component.bdd(contents, BorderLbyout.CENTER);
            contents.invblidbte();
            pbck();
        }
    }


    /**
     * Popup implementbtion thbt uses b Pbnel bs the popup.
     */
    privbte stbtic clbss MediumWeightPopup extends ContbinerPopup {
        privbte stbtic finbl Object mediumWeightPopupCbcheKey =
                             new StringBuffer("PopupFbctory.mediumPopupCbche");

        /** Child of the pbnel. The contents bre bdded to this. */
        privbte JRootPbne rootPbne;


        /**
         * Returns b medium weight <code>Popup</code> implementbtion. If
         * the <code>Popup</code> needs more spbce thbt in bvbilbble in
         * <code>owner</code>, this will return null.
         */
        stbtic Popup getMediumWeightPopup(Component owner, Component contents,
                                          int ownerX, int ownerY) {
            MediumWeightPopup popup = getRecycledMediumWeightPopup();

            if (popup == null) {
                popup = new MediumWeightPopup();
            }
            popup.reset(owner, contents, ownerX, ownerY);
            if (!popup.fitsOnScreen() ||
                 popup.overlbppedByOwnedWindow()) {
                popup.hide();
                return null;
            }
            return popup;
        }

        /**
         * Returns the cbche to use for medium weight popups.
         */
        @SuppressWbrnings("unchecked")
        privbte stbtic List<MediumWeightPopup> getMediumWeightPopupCbche() {
            List<MediumWeightPopup> cbche = (List<MediumWeightPopup>)SwingUtilities.bppContextGet(
                                    mediumWeightPopupCbcheKey);

            if (cbche == null) {
                cbche = new ArrbyList<>();
                SwingUtilities.bppContextPut(mediumWeightPopupCbcheKey, cbche);
            }
            return cbche;
        }

        /**
         * Recycles the MediumWeightPopup <code>popup</code>.
         */
        privbte stbtic void recycleMediumWeightPopup(MediumWeightPopup popup) {
            synchronized (MediumWeightPopup.clbss) {
                List<MediumWeightPopup> mediumPopupCbche = getMediumWeightPopupCbche();
                if (mediumPopupCbche.size() < MAX_CACHE_SIZE) {
                    mediumPopupCbche.bdd(popup);
                }
            }
        }

        /**
         * Returns b previously used <code>MediumWeightPopup</code>, or null
         * if none of the popups hbve been recycled.
         */
        privbte stbtic MediumWeightPopup getRecycledMediumWeightPopup() {
            synchronized (MediumWeightPopup.clbss) {
                List<MediumWeightPopup> mediumPopupCbche = getMediumWeightPopupCbche();
                if (mediumPopupCbche.size() > 0) {
                    MediumWeightPopup r = mediumPopupCbche.get(0);
                    mediumPopupCbche.remove(0);
                    return r;
                }
                return null;
            }
        }


        //
        // Popup
        //

        public void hide() {
            super.hide();
            rootPbne.getContentPbne().removeAll();
            recycleMediumWeightPopup(this);
        }
        public void show() {
            Component component = getComponent();
            Contbiner pbrent = null;

            if (owner != null) {
                pbrent = owner.getPbrent();
            }
            /*
              Find the top level window,
              if it hbs b lbyered pbne,
              bdd to thbt, otherwise
              bdd to the window. */
            while (!(pbrent instbnceof Window || pbrent instbnceof Applet) &&
                   (pbrent!=null)) {
                pbrent = pbrent.getPbrent();
            }
            // Set the visibility to fblse before bdding to workbround b
            // bug in Solbris in which the Popup gets bdded bt the wrong
            // locbtion, which will result in b mouseExit, which will then
            // result in the ToolTip being removed.
            if (pbrent instbnceof RootPbneContbiner) {
                pbrent = ((RootPbneContbiner)pbrent).getLbyeredPbne();
                Point p = SwingUtilities.convertScreenLocbtionToPbrent(pbrent,
                                                                       x, y);
                component.setVisible(fblse);
                component.setLocbtion(p.x, p.y);
                pbrent.bdd(component, JLbyeredPbne.POPUP_LAYER,
                                           0);
            } else {
                Point p = SwingUtilities.convertScreenLocbtionToPbrent(pbrent,
                                                                       x, y);

                component.setLocbtion(p.x, p.y);
                component.setVisible(fblse);
                pbrent.bdd(component);
            }
            component.setVisible(true);
        }

        Component crebteComponent(Component owner) {
            Pbnel component = new MediumWeightComponent();

            rootPbne = new JRootPbne();
            // NOTE: this uses setOpbque vs LookAndFeel.instbllProperty bs
            // there is NO rebson for the RootPbne not to be opbque. For
            // pbinting to work the contentPbne must be opbque, therefor the
            // RootPbne cbn blso be opbque.
            rootPbne.setOpbque(true);
            component.bdd(rootPbne, BorderLbyout.CENTER);
            return component;
        }

        /**
         * Resets the <code>Popup</code> to bn initibl stbte.
         */
        void reset(Component owner, Component contents, int ownerX,
                   int ownerY) {
            super.reset(owner, contents, ownerX, ownerY);

            Component component = getComponent();

            component.setLocbtion(ownerX, ownerY);
            rootPbne.getContentPbne().bdd(contents, BorderLbyout.CENTER);
            contents.invblidbte();
            component.vblidbte();
            pbck();
        }


        // This implements SwingHebvyWeight so thbt repbints on it
        // bre processed by the RepbintMbnbger bnd SwingPbintEventDispbtcher.
        @SuppressWbrnings("seribl") // JDK-implementbtion clbss
        privbte stbtic clbss MediumWeightComponent extends Pbnel implements
                                                           SwingHebvyWeight {
            MediumWeightComponent() {
                super(new BorderLbyout());
            }
        }
    }
}
