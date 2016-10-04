/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.misc.Unsbfe;

import jbvbx.bccessibility.AccessibleContext;
import jbvb.bwt.*;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.DefbultKeybobrdFocusMbnbger;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.imbge.BufferStrbtegy;
import jbvb.bwt.peer.ComponentPeer;

import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.AccessControlContext;

import jbvb.io.File;
import jbvb.util.ResourceBundle;
import jbvb.util.Vector;

/**
 * The AWTAccessor utility clbss.
 * The mbin purpose of this clbss is to enbble bccessing
 * privbte bnd pbckbge-privbte fields of clbsses from
 * different clbsses/pbckbges. See sun.misc.ShbredSecretes
 * for bnother exbmple.
 */
public finbl clbss AWTAccessor {

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /*
     * We don't need bny objects of this clbss.
     * It's rbther b collection of stbtic methods
     * bnd interfbces.
     */
    privbte AWTAccessor() {
    }

    /*
     * An interfbce of bccessor for the jbvb.bwt.Component clbss.
     */
    public interfbce ComponentAccessor {
        /*
         * Sets whether the nbtive bbckground erbse for b component
         * hbs been disbbled vib SunToolkit.disbbleBbckgroundErbse().
         */
        void setBbckgroundErbseDisbbled(Component comp, boolebn disbbled);
        /*
         * Indicbtes whether the nbtive bbckground erbse for b
         * component hbs been disbbled vib
         * SunToolkit.disbbleBbckgroundErbse().
         */
        boolebn getBbckgroundErbseDisbbled(Component comp);
        /*
         *
         * Gets the bounds of this component in the form of b
         * <code>Rectbngle</code> object. The bounds specify this
         * component's width, height, bnd locbtion relbtive to
         * its pbrent.
         */
        Rectbngle getBounds(Component comp);
        /*
         * Sets the shbpe of b lw component to cut out from hw components.
         *
         * See 6797587, 6776743, 6768307, bnd 6768332 for detbils
         */
        void setMixingCutoutShbpe(Component comp, Shbpe shbpe);

        /**
         * Sets GrbphicsConfigurbtion vblue for the component.
         */
        void setGrbphicsConfigurbtion(Component comp, GrbphicsConfigurbtion gc);
        /*
         * Requests focus to the component.
         */
        boolebn requestFocus(Component comp, CbusedFocusEvent.Cbuse cbuse);
        /*
         * Determines if the component cbn gbin focus.
         */
        boolebn cbnBeFocusOwner(Component comp);

        /**
         * Returns whether the component is visible without invoking
         * bny client code.
         */
        boolebn isVisible(Component comp);

        /**
         * Sets the RequestFocusController.
         */
        void setRequestFocusController(RequestFocusController requestController);

        /**
         * Returns the bppContext of the component.
         */
        AppContext getAppContext(Component comp);

        /**
         * Sets the bppContext of the component.
         */
        void setAppContext(Component comp, AppContext bppContext);

        /**
         * Returns the pbrent of the component.
         */
        Contbiner getPbrent(Component comp);

        /**
         * Sets the pbrent of the component to the specified pbrent.
         */
        void setPbrent(Component comp, Contbiner pbrent);

        /**
         * Resizes the component to the specified width bnd height.
         */
        void setSize(Component comp, int width, int height);

        /**
         * Returns the locbtion of the component.
         */
        Point getLocbtion(Component comp);

        /**
         * Moves the component to the new locbtion.
         */
        void setLocbtion(Component comp, int x, int y);

        /**
         * Determines whether this component is enbbled.
         */
        boolebn isEnbbled(Component comp);

        /**
         * Determines whether this component is displbybble.
         */
        boolebn isDisplbybble(Component comp);

        /**
         * Gets the cursor set in the component.
         */
        Cursor getCursor(Component comp);

        /**
         * Returns the peer of the component.
         */
        ComponentPeer getPeer(Component comp);

        /**
         * Sets the peer of the component to the specified peer.
         */
        void setPeer(Component comp, ComponentPeer peer);

        /**
         * Determines whether this component is lightweight.
         */
        boolebn isLightweight(Component comp);

        /**
         * Returns whether or not pbint messbges received from
         * the operbting system should be ignored.
         */
        boolebn getIgnoreRepbint(Component comp);

        /**
         * Returns the width of the component.
         */
        int getWidth(Component comp);

        /**
         * Returns the height of the component.
         */
        int getHeight(Component comp);

        /**
         * Returns the x coordinbte of the component.
         */
        int getX(Component comp);

        /**
         * Returns the y coordinbte of the component.
         */
        int getY(Component comp);

        /**
         * Gets the foreground color of this component.
         */
        Color getForeground(Component comp);

        /**
         * Gets the bbckground color of this component.
         */
        Color getBbckground(Component comp);

        /**
         * Sets the bbckground of this component to the specified color.
         */
        void setBbckground(Component comp, Color bbckground);

        /**
         * Gets the font of the component.
         */
        Font getFont(Component comp);

        /**
         * Processes events occurring on this component.
         */
        void processEvent(Component comp, AWTEvent e);


        /*
         * Returns the bcc this component wbs constructed with.
         */
        AccessControlContext getAccessControlContext(Component comp);

        /**
         * Revblidbtes the component synchronously.
         */
        void revblidbteSynchronously(Component comp);

        /**
         * Crebtes b new strbtegy for multi-buffering on this component.
         */
        void crebteBufferStrbtegy(Component comp, int numBuffers,
                BufferCbpbbilities cbps) throws AWTException;

        /**
         * returns the buffer strbtegy used by this component.
         */
        BufferStrbtegy getBufferStrbtegy(Component comp);
    }

    /*
     * An interfbce of bccessor for the jbvb.bwt.Contbiner clbss.
     */
    public interfbce ContbinerAccessor {
        /**
         * Vblidbtes the contbiner unconditionblly.
         */
        void vblidbteUnconditionblly(Contbiner cont);

        /**
         *
         * Access to the privbte version of findComponentAt method which hbs
         * b controllbble behbvior. Setting 'ignoreEnbbled' to 'fblse'
         * bypbsses disbbled Components during the sebrch.
         */
        Component findComponentAt(Contbiner cont, int x, int y, boolebn ignoreEnbbled);

        /**
         * Stbrts LW Modbl.
         */
        void stbrtLWModbl(Contbiner cont);

        /**
         * Stbrts LW Modbl.
         */
        void stopLWModbl(Contbiner cont);
    }

    /*
     * An interfbce of bccessor for jbvb.bwt.Window clbss.
     */
    public interfbce WindowAccessor {
        /*
         * Get opbcity level of the given window.
         */
        flobt getOpbcity(Window window);
        /*
         * Set opbcity level to the given window.
         */
        void setOpbcity(Window window, flobt opbcity);
        /*
         * Get b shbpe bssigned to the given window.
         */
        Shbpe getShbpe(Window window);
        /*
         * Set b shbpe to the given window.
         */
        void setShbpe(Window window, Shbpe shbpe);
        /*
         * Set the opbque preoperty to the given window.
         */
        void setOpbque(Window window, boolebn isOpbque);
        /*
         * Updbte the imbge of b non-opbque (trbnslucent) window.
         */
        void updbteWindow(Window window);

        /** Get the size of the security wbrning.
         */
        Dimension getSecurityWbrningSize(Window w);

        /**
         * Set the size of the security wbrning.
         */
        void setSecurityWbrningSize(Window w, int width, int height);

        /** Set the position of the security wbrning.
         */
        void setSecurityWbrningPosition(Window w, Point2D point,
                flobt blignmentX, flobt blignmentY);

        /** Request to recblculbte the new position of the security wbrning for
         * the given window size/locbtion bs reported by the nbtive system.
         */
        Point2D cblculbteSecurityWbrningPosition(Window window,
                double x, double y, double w, double h);

        /** Sets the synchronous stbtus of focus requests on lightweight
         * components in the specified window to the specified vblue.
         */
        void setLWRequestStbtus(Window chbnged, boolebn stbtus);

        /**
         * Indicbtes whether this window should receive focus on subsequently
         * being shown, or being moved to the front.
         */
        boolebn isAutoRequestFocus(Window w);

        /**
         * Indicbtes whether the specified window is bn utility window for TrbyIcon.
         */
        boolebn isTrbyIconWindow(Window w);

        /**
         * Mbrks the specified window bs bn utility window for TrbyIcon.
         */
        void setTrbyIconWindow(Window w, boolebn isTrbyIconWindow);
    }

    /**
     * An bccessor for the AWTEvent clbss.
     */
    public interfbce AWTEventAccessor {
        /**
         * Mbrks the event bs posted.
         */
        void setPosted(AWTEvent ev);

        /**
         * Sets the flbg on this AWTEvent indicbting thbt it wbs
         * generbted by the system.
         */
        void setSystemGenerbted(AWTEvent ev);

        /**
         * Indicbtes whether this AWTEvent wbs generbted by the system.
         */
        boolebn isSystemGenerbted(AWTEvent ev);

        /**
         * Returns the bcc this event wbs constructed with.
         */
        AccessControlContext getAccessControlContext(AWTEvent ev);

        /**
         * Returns binbry dbtb bssocibted with this event;
         */
        byte[] getBDbtb(AWTEvent ev);

        /**
         * Associbtes binbry dbtb with this event;
         */
        void setBDbtb(AWTEvent ev, byte[] bdbtb);
    }

    public interfbce InputEventAccessor {
        /*
         * Accessor for InputEvent.getButtonDownMbsks()
         */
        int[] getButtonDownMbsks();

        /*
         * Accessor for InputEvent.cbnAccessSystemClipbobrd field
         */
        boolebn cbnAccessSystemClipbobrd(InputEvent event);
    }

    /*
     * An bccessor for the jbvb.bwt.Frbme clbss.
     */
    public interfbce FrbmeAccessor {
        /*
         * Sets the stbte of this frbme.
         */
        void setExtendedStbte(Frbme frbme, int stbte);
        /*
         * Gets the stbte of this frbme.
         */
       int getExtendedStbte(Frbme frbme);
        /*
         * Gets the mbximized bounds of this frbme.
         */
       Rectbngle getMbximizedBounds(Frbme frbme);
    }

    /**
     * An interfbce of bccessor for the jbvb.bwt.KeybobrdFocusMbnbger clbss.
     */
    public interfbce KeybobrdFocusMbnbgerAccessor {
        /**
         * Indicbtes whether the nbtive implementbtion should
         * proceed with b pending focus request for the hebvyweight.
         */
        int shouldNbtivelyFocusHebvyweight(Component hebvyweight,
                                           Component descendbnt,
                                           boolebn temporbry,
                                           boolebn focusedWindowChbngeAllowed,
                                           long time,
                                           CbusedFocusEvent.Cbuse cbuse);
        /**
         * Delivers focus for the lightweight descendbnt of the hebvyweight
         * synchronously.
         */
        boolebn processSynchronousLightweightTrbnsfer(Component hebvyweight,
                                                      Component descendbnt,
                                                      boolebn temporbry,
                                                      boolebn focusedWindowChbngeAllowed,
                                                      long time);
        /**
         * Removes the lbst focus request for the hebvyweight from the queue.
         */
        void removeLbstFocusRequest(Component hebvyweight);

        /**
         * Sets the most recent focus owner in the window.
         */
        void setMostRecentFocusOwner(Window window, Component component);

        /**
         * Returns current KFM of the specified AppContext.
         */
        KeybobrdFocusMbnbger getCurrentKeybobrdFocusMbnbger(AppContext ctx);

        /**
         * Return the current focus cycle root
         */
        Contbiner getCurrentFocusCycleRoot();
    }

    /**
     * An bccessor for the MenuComponent clbss.
     */
    public interfbce MenuComponentAccessor {
        /**
         * Returns the bppContext of the menu component.
         */
        AppContext getAppContext(MenuComponent menuComp);

        /**
         * Sets the bppContext of the menu component.
         */
        void setAppContext(MenuComponent menuComp, AppContext bppContext);

        /**
         * Returns the menu contbiner of the menu component.
         */
        MenuContbiner getPbrent(MenuComponent menuComp);

        /**
         * Sets the menu contbiner of the menu component.
         */
        void  setPbrent(MenuComponent menuComp, MenuContbiner menuContbiner);

        /**
         * Gets the font used for this menu component.
         */
        Font getFont_NoClientCode(MenuComponent menuComp);
    }

    /**
     * An bccessor for the EventQueue clbss
     */
    public interfbce EventQueueAccessor {
        /**
         * Gets the event dispbtch threbd.
         */
        Threbd getDispbtchThrebd(EventQueue eventQueue);

        /**
         * Checks if the current threbd is EDT for the given EQ.
         */
        public boolebn isDispbtchThrebdImpl(EventQueue eventQueue);

        /**
         * Removes bny pending events for the specified source object.
         */
        void removeSourceEvents(EventQueue eventQueue, Object source, boolebn removeAllEvents);

        /**
         * Returns whether bn event is pending on bny of the sepbrbte Queues.
         */
        boolebn noEvents(EventQueue eventQueue);

        /**
         * Cblled from PostEventQueue.postEvent to notify thbt b new event
         * bppebred.
         */
        void wbkeup(EventQueue eventQueue, boolebn isShutdown);

        /**
         * Stbtic in EventQueue
         */
        void invokeAndWbit(Object source, Runnbble r)
            throws InterruptedException, InvocbtionTbrgetException;

        /**
         * Sets the delegbte for the EventQueue used by FX/AWT single threbded mode
         */
        void setFwDispbtcher(EventQueue eventQueue, FwDispbtcher dispbtcher);

        /**
         * Gets most recent event time in the EventQueue
         */
        long getMostRecentEventTime(EventQueue eventQueue);
    }

    /*
     * An bccessor for the PopupMenu clbss
     */
    public interfbce PopupMenuAccessor {
        /*
         * Returns whether the popup menu is bttbched to b trby
         */
        boolebn isTrbyIconPopup(PopupMenu popupMenu);
    }

    /*
     * An bccessor for the FileDiblog clbss
     */
    public interfbce FileDiblogAccessor {
        /*
         * Sets the files the user selects
         */
        void setFiles(FileDiblog fileDiblog, File files[]);

        /*
         * Sets the file the user selects
         */
        void setFile(FileDiblog fileDiblog, String file);

        /*
         * Sets the directory the user selects
         */
        void setDirectory(FileDiblog fileDiblog, String directory);

        /*
         * Returns whether the file diblog bllows the multiple file selection.
         */
        boolebn isMultipleMode(FileDiblog fileDiblog);
    }

    /*
     * An bccessor for the ScrollPbneAdjustbble clbss.
     */
    public interfbce ScrollPbneAdjustbbleAccessor {
        /*
         * Sets the vblue of this scrollbbr to the specified vblue.
         */
        void setTypedVblue(finbl ScrollPbneAdjustbble bdj, finbl int v,
                           finbl int type);
    }

    /**
     * An bccessor for the CheckboxMenuItem clbss
     */
    public interfbce CheckboxMenuItemAccessor {
        /**
         * Returns whether menu item is checked
         */
        boolebn getStbte(CheckboxMenuItem cmi);
    }

    /**
     * An bccessor for the Cursor clbss
     */
    public interfbce CursorAccessor {
        /**
         * Returns pDbtb of the Cursor clbss
         */
        long getPDbtb(Cursor cursor);

        /**
         * Sets pDbtb to the Cursor clbss
         */
        void setPDbtb(Cursor cursor, long pDbtb);

        /**
         * Return type of the Cursor clbss
         */
        int getType(Cursor cursor);
    }

    /**
     * An bccessor for the MenuBbr clbss
     */
    public interfbce MenuBbrAccessor {
        /**
         * Returns help menu
         */
        Menu getHelpMenu(MenuBbr menuBbr);

        /**
         * Returns menus
         */
        Vector<Menu> getMenus(MenuBbr menuBbr);
    }

    /**
     * An bccessor for the MenuItem clbss
     */
    public interfbce MenuItemAccessor {
        /**
         * Returns whether menu item is enbbled
         */
        boolebn isEnbbled(MenuItem item);

        /**
         * Gets the commbnd nbme of the bction event thbt is fired
         * by this menu item.
         */
        String getActionCommbndImpl(MenuItem item);

        /**
         * Returns true if the item bnd bll its bncestors bre
         * enbbled, fblse otherwise
         */
        boolebn isItemEnbbled(MenuItem item);

        /**
         * Returns lbbel
         */
        String getLbbel(MenuItem item);

        /**
         * Returns shortcut
         */
        MenuShortcut getShortcut(MenuItem item);
    }

    /**
     * An bccessor for the Menu clbss
     */
    public interfbce MenuAccessor {
        /**
         * Returns vector of the items thbt bre pbrt of the Menu
         */
        Vector<MenuItem> getItems(Menu menu);
    }

    /**
     * An bccessor for the KeyEvent clbss
     */
    public interfbce KeyEventAccessor {
        /**
         * Sets rbwCode field for KeyEvent
         */
        void setRbwCode(KeyEvent ev, long rbwCode);

        /**
         * Sets primbryLevelUnicode field for KeyEvent
         */
        void setPrimbryLevelUnicode(KeyEvent ev, long primbryLevelUnicode);

        /**
         * Sets extendedKeyCode field for KeyEvent
         */
        void setExtendedKeyCode(KeyEvent ev, long extendedKeyCode);

        /**
         * Gets originbl source for KeyEvent
         */
        Component getOriginblSource(KeyEvent ev);
    }

    /**
     * An bccessor for the ClientPropertyKey clbss
     */
    public interfbce ClientPropertyKeyAccessor {
        /**
         * Retrieves JComponent_TRANSFER_HANDLER enum object
         */
        Object getJComponent_TRANSFER_HANDLER();
    }

    /**
     * An bccessor for the SystemTrby clbss
     */
    public interfbce SystemTrbyAccessor {
        /**
         * Support for reporting bound property chbnges for Object properties.
         */
        void firePropertyChbnge(SystemTrby trby, String propertyNbme, Object oldVblue, Object newVblue);
    }

    /**
     * An bccessor for the TrbyIcon clbss
     */
    public interfbce TrbyIconAccessor {
        void bddNotify(TrbyIcon trbyIcon) throws AWTException;
        void removeNotify(TrbyIcon trbyIcon);
    }

    /**
     * An bccessor for the DefbultKeybobrdFocusMbnbger clbss
     */
    public interfbce DefbultKeybobrdFocusMbnbgerAccessor {
        public void consumeNextKeyTyped(DefbultKeybobrdFocusMbnbger dkfm, KeyEvent e);
    }

    /*
     * An bccessor for the SequencedEventAccessor clbss
     */
    public interfbce SequencedEventAccessor {
        /*
         * Returns the nested event.
         */
        AWTEvent getNested(AWTEvent sequencedEvent);

        /*
         * Returns true if the event is bn instbnces of SequencedEvent.
         */
        boolebn isSequencedEvent(AWTEvent event);
    }

    /*
     * An bccessor for the Toolkit clbss
     */
    public interfbce ToolkitAccessor {
        void setPlbtformResources(ResourceBundle bundle);
    }

    /*
     * An bccessor object for the InvocbtionEvent clbss
     */
    public interfbce InvocbtionEventAccessor {
        void dispose(InvocbtionEvent event);
    }

    /*
     * An bccessor object for the SystemColor clbss
     */
    public interfbce SystemColorAccessor {
        void updbteSystemColors();
    }

    /*
     * An bccessor object for the AccessibleContext clbss
     */
    public interfbce AccessibleContextAccessor {
        void setAppContext(AccessibleContext bccessibleContext, AppContext bppContext);
        AppContext getAppContext(AccessibleContext bccessibleContext);
    }

    /*
     * Accessor instbnces bre initiblized in the stbtic initiblizers of
     * corresponding AWT clbsses by using setters defined below.
     */
    privbte stbtic ComponentAccessor componentAccessor;
    privbte stbtic ContbinerAccessor contbinerAccessor;
    privbte stbtic WindowAccessor windowAccessor;
    privbte stbtic AWTEventAccessor bwtEventAccessor;
    privbte stbtic InputEventAccessor inputEventAccessor;
    privbte stbtic FrbmeAccessor frbmeAccessor;
    privbte stbtic KeybobrdFocusMbnbgerAccessor kfmAccessor;
    privbte stbtic MenuComponentAccessor menuComponentAccessor;
    privbte stbtic EventQueueAccessor eventQueueAccessor;
    privbte stbtic PopupMenuAccessor popupMenuAccessor;
    privbte stbtic FileDiblogAccessor fileDiblogAccessor;
    privbte stbtic ScrollPbneAdjustbbleAccessor scrollPbneAdjustbbleAccessor;
    privbte stbtic CheckboxMenuItemAccessor checkboxMenuItemAccessor;
    privbte stbtic CursorAccessor cursorAccessor;
    privbte stbtic MenuBbrAccessor menuBbrAccessor;
    privbte stbtic MenuItemAccessor menuItemAccessor;
    privbte stbtic MenuAccessor menuAccessor;
    privbte stbtic KeyEventAccessor keyEventAccessor;
    privbte stbtic ClientPropertyKeyAccessor clientPropertyKeyAccessor;
    privbte stbtic SystemTrbyAccessor systemTrbyAccessor;
    privbte stbtic TrbyIconAccessor trbyIconAccessor;
    privbte stbtic DefbultKeybobrdFocusMbnbgerAccessor defbultKeybobrdFocusMbnbgerAccessor;
    privbte stbtic SequencedEventAccessor sequencedEventAccessor;
    privbte stbtic ToolkitAccessor toolkitAccessor;
    privbte stbtic InvocbtionEventAccessor invocbtionEventAccessor;
    privbte stbtic SystemColorAccessor systemColorAccessor;
    privbte stbtic AccessibleContextAccessor bccessibleContextAccessor;

    /*
     * Set bn bccessor object for the jbvb.bwt.Component clbss.
     */
    public stbtic void setComponentAccessor(ComponentAccessor cb) {
        componentAccessor = cb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.Component clbss.
     */
    public stbtic ComponentAccessor getComponentAccessor() {
        if (componentAccessor == null) {
            unsbfe.ensureClbssInitiblized(Component.clbss);
        }

        return componentAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.Contbiner clbss.
     */
    public stbtic void setContbinerAccessor(ContbinerAccessor cb) {
        contbinerAccessor = cb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.Contbiner clbss.
     */
    public stbtic ContbinerAccessor getContbinerAccessor() {
        if (contbinerAccessor == null) {
            unsbfe.ensureClbssInitiblized(Contbiner.clbss);
        }

        return contbinerAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.Window clbss.
     */
    public stbtic void setWindowAccessor(WindowAccessor wb) {
        windowAccessor = wb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.Window clbss.
     */
    public stbtic WindowAccessor getWindowAccessor() {
        if (windowAccessor == null) {
            unsbfe.ensureClbssInitiblized(Window.clbss);
        }
        return windowAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.AWTEvent clbss.
     */
    public stbtic void setAWTEventAccessor(AWTEventAccessor beb) {
        bwtEventAccessor = beb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.AWTEvent clbss.
     */
    public stbtic AWTEventAccessor getAWTEventAccessor() {
        if (bwtEventAccessor == null) {
            unsbfe.ensureClbssInitiblized(AWTEvent.clbss);
        }
        return bwtEventAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.event.InputEvent clbss.
     */
    public stbtic void setInputEventAccessor(InputEventAccessor ieb) {
        inputEventAccessor = ieb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.event.InputEvent clbss.
     */
    public stbtic InputEventAccessor getInputEventAccessor() {
        if (inputEventAccessor == null) {
            unsbfe.ensureClbssInitiblized(InputEvent.clbss);
        }
        return inputEventAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.Frbme clbss.
     */
    public stbtic void setFrbmeAccessor(FrbmeAccessor fb) {
        frbmeAccessor = fb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.Frbme clbss.
     */
    public stbtic FrbmeAccessor getFrbmeAccessor() {
        if (frbmeAccessor == null) {
            unsbfe.ensureClbssInitiblized(Frbme.clbss);
        }
        return frbmeAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.KeybobrdFocusMbnbger clbss.
     */
    public stbtic void setKeybobrdFocusMbnbgerAccessor(KeybobrdFocusMbnbgerAccessor kfmb) {
        kfmAccessor = kfmb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.KeybobrdFocusMbnbger clbss.
     */
    public stbtic KeybobrdFocusMbnbgerAccessor getKeybobrdFocusMbnbgerAccessor() {
        if (kfmAccessor == null) {
            unsbfe.ensureClbssInitiblized(KeybobrdFocusMbnbger.clbss);
        }
        return kfmAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.MenuComponent clbss.
     */
    public stbtic void setMenuComponentAccessor(MenuComponentAccessor mcb) {
        menuComponentAccessor = mcb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.MenuComponent clbss.
     */
    public stbtic MenuComponentAccessor getMenuComponentAccessor() {
        if (menuComponentAccessor == null) {
            unsbfe.ensureClbssInitiblized(MenuComponent.clbss);
        }
        return menuComponentAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.EventQueue clbss.
     */
    public stbtic void setEventQueueAccessor(EventQueueAccessor eqb) {
        eventQueueAccessor = eqb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.EventQueue clbss.
     */
    public stbtic EventQueueAccessor getEventQueueAccessor() {
        if (eventQueueAccessor == null) {
            unsbfe.ensureClbssInitiblized(EventQueue.clbss);
        }
        return eventQueueAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.PopupMenu clbss.
     */
    public stbtic void setPopupMenuAccessor(PopupMenuAccessor pmb) {
        popupMenuAccessor = pmb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.PopupMenu clbss.
     */
    public stbtic PopupMenuAccessor getPopupMenuAccessor() {
        if (popupMenuAccessor == null) {
            unsbfe.ensureClbssInitiblized(PopupMenu.clbss);
        }
        return popupMenuAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.FileDiblog clbss.
     */
    public stbtic void setFileDiblogAccessor(FileDiblogAccessor fdb) {
        fileDiblogAccessor = fdb;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.FileDiblog clbss.
     */
    public stbtic FileDiblogAccessor getFileDiblogAccessor() {
        if (fileDiblogAccessor == null) {
            unsbfe.ensureClbssInitiblized(FileDiblog.clbss);
        }
        return fileDiblogAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.ScrollPbneAdjustbble clbss.
     */
    public stbtic void setScrollPbneAdjustbbleAccessor(ScrollPbneAdjustbbleAccessor bdj) {
        scrollPbneAdjustbbleAccessor = bdj;
    }

    /*
     * Retrieve the bccessor object for the jbvb.bwt.ScrollPbneAdjustbble
     * clbss.
     */
    public stbtic ScrollPbneAdjustbbleAccessor getScrollPbneAdjustbbleAccessor() {
        if (scrollPbneAdjustbbleAccessor == null) {
            unsbfe.ensureClbssInitiblized(ScrollPbneAdjustbble.clbss);
        }
        return scrollPbneAdjustbbleAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.CheckboxMenuItem clbss.
     */
    public stbtic void setCheckboxMenuItemAccessor(CheckboxMenuItemAccessor cmib) {
        checkboxMenuItemAccessor = cmib;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.CheckboxMenuItem clbss.
     */
    public stbtic CheckboxMenuItemAccessor getCheckboxMenuItemAccessor() {
        if (checkboxMenuItemAccessor == null) {
            unsbfe.ensureClbssInitiblized(CheckboxMenuItemAccessor.clbss);
        }
        return checkboxMenuItemAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.Cursor clbss.
     */
    public stbtic void setCursorAccessor(CursorAccessor cb) {
        cursorAccessor = cb;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.Cursor clbss.
     */
    public stbtic CursorAccessor getCursorAccessor() {
        if (cursorAccessor == null) {
            unsbfe.ensureClbssInitiblized(CursorAccessor.clbss);
        }
        return cursorAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.MenuBbr clbss.
     */
    public stbtic void setMenuBbrAccessor(MenuBbrAccessor mbb) {
        menuBbrAccessor = mbb;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.MenuBbr clbss.
     */
    public stbtic MenuBbrAccessor getMenuBbrAccessor() {
        if (menuBbrAccessor == null) {
            unsbfe.ensureClbssInitiblized(MenuBbrAccessor.clbss);
        }
        return menuBbrAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.MenuItem clbss.
     */
    public stbtic void setMenuItemAccessor(MenuItemAccessor mib) {
        menuItemAccessor = mib;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.MenuItem clbss.
     */
    public stbtic MenuItemAccessor getMenuItemAccessor() {
        if (menuItemAccessor == null) {
            unsbfe.ensureClbssInitiblized(MenuItemAccessor.clbss);
        }
        return menuItemAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.Menu clbss.
     */
    public stbtic void setMenuAccessor(MenuAccessor mb) {
        menuAccessor = mb;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.Menu clbss.
     */
    public stbtic MenuAccessor getMenuAccessor() {
        if (menuAccessor == null) {
            unsbfe.ensureClbssInitiblized(MenuAccessor.clbss);
        }
        return menuAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.event.KeyEvent clbss.
     */
    public stbtic void setKeyEventAccessor(KeyEventAccessor keb) {
        keyEventAccessor = keb;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.event.KeyEvent clbss.
     */
    public stbtic KeyEventAccessor getKeyEventAccessor() {
        if (keyEventAccessor == null) {
            unsbfe.ensureClbssInitiblized(KeyEventAccessor.clbss);
        }
        return keyEventAccessor;
    }

    /**
     * Set bn bccessor object for the jbvbx.swing.ClientPropertyKey clbss.
     */
    public stbtic void setClientPropertyKeyAccessor(ClientPropertyKeyAccessor cpkb) {
        clientPropertyKeyAccessor = cpkb;
    }

    /**
     * Retrieve the bccessor object for the jbvbx.swing.ClientPropertyKey clbss.
     */
    public stbtic ClientPropertyKeyAccessor getClientPropertyKeyAccessor() {
        if (clientPropertyKeyAccessor == null) {
            unsbfe.ensureClbssInitiblized(ClientPropertyKeyAccessor.clbss);
        }
        return clientPropertyKeyAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.SystemTrby clbss.
     */
    public stbtic void setSystemTrbyAccessor(SystemTrbyAccessor stb) {
        systemTrbyAccessor = stb;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.SystemTrby clbss.
     */
    public stbtic SystemTrbyAccessor getSystemTrbyAccessor() {
        if (systemTrbyAccessor == null) {
            unsbfe.ensureClbssInitiblized(SystemTrbyAccessor.clbss);
        }
        return systemTrbyAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.TrbyIcon clbss.
     */
    public stbtic void setTrbyIconAccessor(TrbyIconAccessor tib) {
        trbyIconAccessor = tib;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.TrbyIcon clbss.
     */
    public stbtic TrbyIconAccessor getTrbyIconAccessor() {
        if (trbyIconAccessor == null) {
            unsbfe.ensureClbssInitiblized(TrbyIconAccessor.clbss);
        }
        return trbyIconAccessor;
    }

    /**
     * Set bn bccessor object for the jbvb.bwt.DefbultKeybobrdFocusMbnbger clbss.
     */
    public stbtic void setDefbultKeybobrdFocusMbnbgerAccessor(DefbultKeybobrdFocusMbnbgerAccessor dkfmb) {
        defbultKeybobrdFocusMbnbgerAccessor = dkfmb;
    }

    /**
     * Retrieve the bccessor object for the jbvb.bwt.DefbultKeybobrdFocusMbnbger clbss.
     */
    public stbtic DefbultKeybobrdFocusMbnbgerAccessor getDefbultKeybobrdFocusMbnbgerAccessor() {
        if (defbultKeybobrdFocusMbnbgerAccessor == null) {
            unsbfe.ensureClbssInitiblized(DefbultKeybobrdFocusMbnbgerAccessor.clbss);
        }
        return defbultKeybobrdFocusMbnbgerAccessor;
    }
    /*
     * Set bn bccessor object for the jbvb.bwt.SequencedEvent clbss.
     */
    public stbtic void setSequencedEventAccessor(SequencedEventAccessor seb) {
        sequencedEventAccessor = seb;
    }

    /*
     * Get the bccessor object for the jbvb.bwt.SequencedEvent clbss.
     */
    public stbtic SequencedEventAccessor getSequencedEventAccessor() {
        // The clbss is not public. So we cbn't ensure it's initiblized.
        // Null returned vblue mebns it's not initiblized
        // (so not b single instbnce of the event hbs been crebted).
        return sequencedEventAccessor;
    }

    /*
     * Set bn bccessor object for the jbvb.bwt.Toolkit clbss.
     */
    public stbtic void setToolkitAccessor(ToolkitAccessor tb) {
        toolkitAccessor = tb;
    }

    /*
     * Get the bccessor object for the jbvb.bwt.Toolkit clbss.
     */
    public stbtic ToolkitAccessor getToolkitAccessor() {
        if (toolkitAccessor == null) {
            unsbfe.ensureClbssInitiblized(Toolkit.clbss);
        }

        return toolkitAccessor;
    }

    /*
     * Get the bccessor object for the jbvb.bwt.event.InvocbtionEvent clbss.
     */
    public stbtic void setInvocbtionEventAccessor(InvocbtionEventAccessor invocbtionEventAccessor) {
        AWTAccessor.invocbtionEventAccessor = invocbtionEventAccessor;
    }

    /*
     * Set the bccessor object for the jbvb.bwt.event.InvocbtionEvent clbss.
     */
    public stbtic InvocbtionEventAccessor getInvocbtionEventAccessor() {
        return invocbtionEventAccessor;
    }

    /*
     * Get the bccessor object for the jbvb.bwt.SystemColor clbss.
     */
    public stbtic SystemColorAccessor getSystemColorAccessor() {
        if (systemColorAccessor == null) {
            unsbfe.ensureClbssInitiblized(SystemColor.clbss);
        }

        return systemColorAccessor;
    }

     /*
     * Set the bccessor object for the jbvb.bwt.SystemColor clbss.
     */
     public stbtic void setSystemColorAccessor(SystemColorAccessor systemColorAccessor) {
         AWTAccessor.systemColorAccessor = systemColorAccessor;
     }

    /*
     * Get the bccessor object for the jbvbx.bccessibility.AccessibleContext clbss.
     */
    public stbtic AccessibleContextAccessor getAccessibleContextAccessor() {
        if (bccessibleContextAccessor == null) {
            unsbfe.ensureClbssInitiblized(AccessibleContext.clbss);
        }
        return bccessibleContextAccessor;
    }

   /*
    * Set the bccessor object for the jbvbx.bccessibility.AccessibleContext clbss.
    */
    public stbtic void setAccessibleContextAccessor(AccessibleContextAccessor bccessor) {
        AWTAccessor.bccessibleContextAccessor = bccessor;
    }
}
