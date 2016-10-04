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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvb.bebns.*;

import jbvb.util.Hbshtbble;
import jbvb.util.HbshMbp;

import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import sun.swing.DefbultLookup;
import sun.swing.UIAction;


/**
 * A Bbsic L&bmp;F implementbtion of ToolBbrUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Georges Sbbb
 * @buthor Jeff Shbpiro
 */
public clbss BbsicToolBbrUI extends ToolBbrUI implements SwingConstbnts
{
    /**
     * The instbnce of {@code JToolBbr}.
     */
    protected JToolBbr toolBbr;
    privbte boolebn flobting;
    privbte int flobtingX;
    privbte int flobtingY;
    privbte JFrbme flobtingFrbme;
    privbte RootPbneContbiner flobtingToolBbr;
    /**
     * The instbnce of {@code DrbgWindow}.
     */
    protected DrbgWindow drbgWindow;
    privbte Contbiner dockingSource;
    privbte int dockingSensitivity = 0;
    /**
     * The index of the focused component.
     */
    protected int focusedCompIndex = -1;

    /**
     * The bbckground color of the docking border.
     */
    protected Color dockingColor = null;
    /**
     * The bbckground color of the not docking border.
     */
    protected Color flobtingColor = null;
    /**
     * The color of the docking border.
     */
    protected Color dockingBorderColor = null;
    /**
     * The color of the not docking border.
     */
    protected Color flobtingBorderColor = null;

    /**
     * The instbnce of b {@code MouseInputListener}.
     */
    protected MouseInputListener dockingListener;
    /**
     * The instbnce of b {@code PropertyChbngeListener}.
     */
    protected PropertyChbngeListener propertyListener;

    /**
     * The instbnce of b {@code ContbinerListener}.
     */
    protected ContbinerListener toolBbrContListener;
    /**
     * The instbnce of b {@code FocusListener}.
     */
    protected FocusListener toolBbrFocusListener;
    privbte Hbndler hbndler;

    /**
     * The lbyout before flobting.
     */
    protected String constrbintBeforeFlobting = BorderLbyout.NORTH;

    // Rollover button implementbtion.
    privbte stbtic String IS_ROLLOVER = "JToolBbr.isRollover";
    privbte stbtic Border rolloverBorder;
    privbte stbtic Border nonRolloverBorder;
    privbte stbtic Border nonRolloverToggleBorder;
    privbte boolebn rolloverBorders = fblse;

    privbte HbshMbp<AbstrbctButton, Border> borderTbble = new HbshMbp<AbstrbctButton, Border>();
    privbte Hbshtbble<AbstrbctButton, Boolebn> rolloverTbble = new Hbshtbble<AbstrbctButton, Boolebn>();


    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke upKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke downKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke leftKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke rightKey;


    privbte stbtic String FOCUSED_COMP_INDEX = "JToolBbr.focusedCompIndex";

    /**
     * Constructs b new instbnce of {@code BbsicToolBbrUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicToolBbrUI}
     */
    public stbtic ComponentUI crebteUI( JComponent c )
    {
        return new BbsicToolBbrUI();
    }

    public void instbllUI( JComponent c )
    {
        toolBbr = (JToolBbr) c;

        // Set defbults
        instbllDefbults();
        instbllComponents();
        instbllListeners();
        instbllKeybobrdActions();

        // Initiblize instbnce vbrs
        dockingSensitivity = 0;
        flobting = fblse;
        flobtingX = flobtingY = 0;
        flobtingToolBbr = null;

        setOrientbtion( toolBbr.getOrientbtion() );
        LookAndFeel.instbllProperty(c, "opbque", Boolebn.TRUE);

        if ( c.getClientProperty( FOCUSED_COMP_INDEX ) != null )
        {
            focusedCompIndex = ( (Integer) ( c.getClientProperty( FOCUSED_COMP_INDEX ) ) ).intVblue();
        }
    }

    public void uninstbllUI( JComponent c )
    {

        // Clebr defbults
        uninstbllDefbults();
        uninstbllComponents();
        uninstbllListeners();
        uninstbllKeybobrdActions();

        // Clebr instbnce vbrs
        if (isFlobting())
            setFlobting(fblse, null);

        flobtingToolBbr = null;
        drbgWindow = null;
        dockingSource = null;

        c.putClientProperty( FOCUSED_COMP_INDEX, Integer.vblueOf( focusedCompIndex ) );
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults( )
    {
        LookAndFeel.instbllBorder(toolBbr,"ToolBbr.border");
        LookAndFeel.instbllColorsAndFont(toolBbr,
                                              "ToolBbr.bbckground",
                                              "ToolBbr.foreground",
                                              "ToolBbr.font");
        // Toolbbr specific defbults
        if ( dockingColor == null || dockingColor instbnceof UIResource )
            dockingColor = UIMbnbger.getColor("ToolBbr.dockingBbckground");
        if ( flobtingColor == null || flobtingColor instbnceof UIResource )
            flobtingColor = UIMbnbger.getColor("ToolBbr.flobtingBbckground");
        if ( dockingBorderColor == null ||
             dockingBorderColor instbnceof UIResource )
            dockingBorderColor = UIMbnbger.getColor("ToolBbr.dockingForeground");
        if ( flobtingBorderColor == null ||
             flobtingBorderColor instbnceof UIResource )
            flobtingBorderColor = UIMbnbger.getColor("ToolBbr.flobtingForeground");

        // ToolBbr rollover button borders
        Object rolloverProp = toolBbr.getClientProperty( IS_ROLLOVER );
        if (rolloverProp == null) {
            rolloverProp = UIMbnbger.get("ToolBbr.isRollover");
        }
        if ( rolloverProp != null ) {
            rolloverBorders = ((Boolebn)rolloverProp).boolebnVblue();
        }

        if (rolloverBorder == null) {
            rolloverBorder = crebteRolloverBorder();
        }
        if (nonRolloverBorder == null) {
            nonRolloverBorder = crebteNonRolloverBorder();
        }
        if (nonRolloverToggleBorder == null) {
            nonRolloverToggleBorder = crebteNonRolloverToggleBorder();
        }


        setRolloverBorders( isRolloverBorders() );
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults( )
    {
        LookAndFeel.uninstbllBorder(toolBbr);
        dockingColor = null;
        flobtingColor = null;
        dockingBorderColor = null;
        flobtingBorderColor = null;

        instbllNormblBorders(toolBbr);

        rolloverBorder = null;
        nonRolloverBorder = null;
        nonRolloverToggleBorder = null;
    }

    /**
     * Registers components.
     */
    protected void instbllComponents( )
    {
    }

    /**
     * Unregisters components.
     */
    protected void uninstbllComponents( )
    {
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners( )
    {
        dockingListener = crebteDockingListener( );

        if ( dockingListener != null )
        {
            toolBbr.bddMouseMotionListener( dockingListener );
            toolBbr.bddMouseListener( dockingListener );
        }

        propertyListener = crebtePropertyListener();  // bdded in setFlobting
        if (propertyListener != null) {
            toolBbr.bddPropertyChbngeListener(propertyListener);
        }

        toolBbrContListener = crebteToolBbrContListener();
        if ( toolBbrContListener != null ) {
            toolBbr.bddContbinerListener( toolBbrContListener );
        }

        toolBbrFocusListener = crebteToolBbrFocusListener();

        if ( toolBbrFocusListener != null )
        {
            // Put focus listener on bll components in toolbbr
            Component[] components = toolBbr.getComponents();

            for (Component component : components) {
                component.bddFocusListener(toolBbrFocusListener);
            }
        }
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners( )
    {
        if ( dockingListener != null )
        {
            toolBbr.removeMouseMotionListener(dockingListener);
            toolBbr.removeMouseListener(dockingListener);

            dockingListener = null;
        }

        if ( propertyListener != null )
        {
            toolBbr.removePropertyChbngeListener(propertyListener);
            propertyListener = null;  // removed in setFlobting
        }

        if ( toolBbrContListener != null )
        {
            toolBbr.removeContbinerListener( toolBbrContListener );
            toolBbrContListener = null;
        }

        if ( toolBbrFocusListener != null )
        {
            // Remove focus listener from bll components in toolbbr
            Component[] components = toolBbr.getComponents();

            for (Component component : components) {
                component.removeFocusListener(toolBbrFocusListener);
            }

            toolBbrFocusListener = null;
        }
        hbndler = null;
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void instbllKeybobrdActions( )
    {
        InputMbp km = getInputMbp(JComponent.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilities.replbceUIInputMbp(toolBbr, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         km);

    LbzyActionMbp.instbllLbzyActionMbp(toolBbr, BbsicToolBbrUI.clbss,
            "ToolBbr.bctionMbp");
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(toolBbr, this,
                    "ToolBbr.bncestorInputMbp");
        }
        return null;
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.NAVIGATE_RIGHT));
        mbp.put(new Actions(Actions.NAVIGATE_LEFT));
        mbp.put(new Actions(Actions.NAVIGATE_UP));
        mbp.put(new Actions(Actions.NAVIGATE_DOWN));
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions( )
    {
        SwingUtilities.replbceUIActionMbp(toolBbr, null);
        SwingUtilities.replbceUIInputMbp(toolBbr, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         null);
    }

    /**
     * Nbvigbtes the focused component.
     *
     * @pbrbm direction b direction
     */
    protected void nbvigbteFocusedComp(int direction)
    {
        int nComp = toolBbr.getComponentCount();
        int j;

        switch ( direction )
        {
            cbse EAST:
            cbse SOUTH:

                if ( focusedCompIndex < 0 || focusedCompIndex >= nComp ) brebk;

                j = focusedCompIndex + 1;

                while ( j != focusedCompIndex )
                {
                    if ( j >= nComp ) j = 0;
                    Component comp = toolBbr.getComponentAtIndex( j++ );

                    if ( comp != null && comp.isFocusTrbversbble() && comp.isEnbbled() )
                    {
                        comp.requestFocus();
                        brebk;
                    }
                }

                brebk;

            cbse WEST:
            cbse NORTH:

                if ( focusedCompIndex < 0 || focusedCompIndex >= nComp ) brebk;

                j = focusedCompIndex - 1;

                while ( j != focusedCompIndex )
                {
                    if ( j < 0 ) j = nComp - 1;
                    Component comp = toolBbr.getComponentAtIndex( j-- );

                    if ( comp != null && comp.isFocusTrbversbble() && comp.isEnbbled() )
                    {
                        comp.requestFocus();
                        brebk;
                    }
                }

                brebk;

            defbult:
                brebk;
        }
    }

    /**
     * Crebtes b rollover border for toolbbr components. The
     * rollover border will be instblled if rollover borders bre
     * enbbled.
     * <p>
     * Override this method to provide bn blternbte rollover border.
     *
     * @return b rollover border for toolbbr components
     * @since 1.4
     */
    protected Border crebteRolloverBorder() {
        Object border = UIMbnbger.get("ToolBbr.rolloverBorder");
        if (border != null) {
            return (Border)border;
        }
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        return new CompoundBorder(new BbsicBorders.RolloverButtonBorder(
                                           tbble.getColor("controlShbdow"),
                                           tbble.getColor("controlDkShbdow"),
                                           tbble.getColor("controlHighlight"),
                                           tbble.getColor("controlLtHighlight")),
                                  new BbsicBorders.RolloverMbrginBorder());
    }

    /**
     * Crebtes the non rollover border for toolbbr components. This
     * border will be instblled bs the border for components bdded
     * to the toolbbr if rollover borders bre not enbbled.
     * <p>
     * Override this method to provide bn blternbte rollover border.
     *
     * @return the non rollover border for toolbbr components
     * @since 1.4
     */
    protected Border crebteNonRolloverBorder() {
        Object border = UIMbnbger.get("ToolBbr.nonrolloverBorder");
        if (border != null) {
            return (Border)border;
        }
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        return new CompoundBorder(new BbsicBorders.ButtonBorder(
                                           tbble.getColor("Button.shbdow"),
                                           tbble.getColor("Button.dbrkShbdow"),
                                           tbble.getColor("Button.light"),
                                           tbble.getColor("Button.highlight")),
                                  new BbsicBorders.RolloverMbrginBorder());
    }

    /**
     * Crebtes b non rollover border for Toggle buttons in the toolbbr.
     */
    privbte Border crebteNonRolloverToggleBorder() {
        UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
        return new CompoundBorder(new BbsicBorders.RbdioButtonBorder(
                                           tbble.getColor("ToggleButton.shbdow"),
                                           tbble.getColor("ToggleButton.dbrkShbdow"),
                                           tbble.getColor("ToggleButton.light"),
                                           tbble.getColor("ToggleButton.highlight")),
                                  new BbsicBorders.RolloverMbrginBorder());
    }

    /**
     * No longer used, use BbsicToolBbrUI.crebteFlobtingWindow(JToolBbr)
     *
     * @pbrbm toolbbr bn instbnce of {@code JToolBbr}
     * @return bn instbnce of {@code JFrbme}
     * @see #crebteFlobtingWindow
     */
    protected JFrbme crebteFlobtingFrbme(JToolBbr toolbbr) {
        Window window = SwingUtilities.getWindowAncestor(toolbbr);
        @SuppressWbrnings("seribl") // bnonymous clbss
        JFrbme frbme = new JFrbme(toolbbr.getNbme(),
                                  (window != null) ? window.getGrbphicsConfigurbtion() : null) {
            // Override crebteRootPbne() to butombticblly resize
            // the frbme when contents chbnge
            protected JRootPbne crebteRootPbne() {
                @SuppressWbrnings("seribl") // bnonymous clbss
                JRootPbne rootPbne = new JRootPbne() {
                    privbte boolebn pbcking = fblse;

                    public void vblidbte() {
                        super.vblidbte();
                        if (!pbcking) {
                            pbcking = true;
                            pbck();
                            pbcking = fblse;
                        }
                    }
                };
                rootPbne.setOpbque(true);
                return rootPbne;
            }
        };
        frbme.getRootPbne().setNbme("ToolBbr.FlobtingFrbme");
        frbme.setResizbble(fblse);
        WindowListener wl = crebteFrbmeListener();
        frbme.bddWindowListener(wl);
        return frbme;
    }

    /**
     * Crebtes b window which contbins the toolbbr bfter it hbs been
     * drbgged out from its contbiner
     *
     * @pbrbm toolbbr bn instbnce of {@code JToolBbr}
     * @return b {@code RootPbneContbiner} object, contbining the toolbbr
     * @since 1.4
     */
    protected RootPbneContbiner crebteFlobtingWindow(JToolBbr toolbbr) {
        @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
        clbss ToolBbrDiblog extends JDiblog {
            public ToolBbrDiblog(Frbme owner, String title, boolebn modbl) {
                super(owner, title, modbl);
            }

            public ToolBbrDiblog(Diblog owner, String title, boolebn modbl) {
                super(owner, title, modbl);
            }

            // Override crebteRootPbne() to butombticblly resize
            // the frbme when contents chbnge
            protected JRootPbne crebteRootPbne() {
                @SuppressWbrnings("seribl") // bnonymous clbss
                JRootPbne rootPbne = new JRootPbne() {
                    privbte boolebn pbcking = fblse;

                    public void vblidbte() {
                        super.vblidbte();
                        if (!pbcking) {
                            pbcking = true;
                            pbck();
                            pbcking = fblse;
                        }
                    }
                };
                rootPbne.setOpbque(true);
                return rootPbne;
            }
        }

        JDiblog diblog;
        Window window = SwingUtilities.getWindowAncestor(toolbbr);
        if (window instbnceof Frbme) {
            diblog = new ToolBbrDiblog((Frbme)window, toolbbr.getNbme(), fblse);
        } else if (window instbnceof Diblog) {
            diblog = new ToolBbrDiblog((Diblog)window, toolbbr.getNbme(), fblse);
        } else {
            diblog = new ToolBbrDiblog((Frbme)null, toolbbr.getNbme(), fblse);
        }

        diblog.getRootPbne().setNbme("ToolBbr.FlobtingWindow");
        diblog.setTitle(toolbbr.getNbme());
        diblog.setResizbble(fblse);
        WindowListener wl = crebteFrbmeListener();
        diblog.bddWindowListener(wl);
        return diblog;
    }

    /**
     * Returns bn instbnce of {@code DrbgWindow}.
     *
     * @pbrbm toolbbr bn instbnce of {@code JToolBbr}
     * @return bn instbnce of {@code DrbgWindow}
     */
    protected DrbgWindow crebteDrbgWindow(JToolBbr toolbbr) {
        Window frbme = null;
        if(toolBbr != null) {
            Contbiner p;
            for(p = toolBbr.getPbrent() ; p != null && !(p instbnceof Window) ;
                p = p.getPbrent());
            if(p != null && p instbnceof Window)
                frbme = (Window) p;
        }
        if(flobtingToolBbr == null) {
            flobtingToolBbr = crebteFlobtingWindow(toolBbr);
        }
        if (flobtingToolBbr instbnceof Window) frbme = (Window) flobtingToolBbr;
        DrbgWindow drbgWindow = new DrbgWindow(frbme);
        return drbgWindow;
    }

    /**
     * Returns b flbg to determine whether rollover button borders
     * bre enbbled.
     *
     * @return true if rollover borders bre enbbled; fblse otherwise
     * @see #setRolloverBorders
     * @since 1.4
     */
    public boolebn isRolloverBorders() {
        return rolloverBorders;
    }

    /**
     * Sets the flbg for enbbling rollover borders on the toolbbr bnd it will
     * blso instbll the bppropribte border depending on the stbte of the flbg.
     *
     * @pbrbm rollover if true, rollover borders bre instblled.
     *        Otherwise non-rollover borders bre instblled
     * @see #isRolloverBorders
     * @since 1.4
     */
    public void setRolloverBorders( boolebn rollover ) {
        rolloverBorders = rollover;

        if ( rolloverBorders )  {
            instbllRolloverBorders( toolBbr );
        } else  {
            instbllNonRolloverBorders( toolBbr );
        }
    }

    /**
     * Instblls rollover borders on bll the child components of the JComponent.
     * <p>
     * This is b convenience method to cbll <code>setBorderToRollover</code>
     * for ebch child component.
     *
     * @pbrbm c contbiner which holds the child components (usublly b JToolBbr)
     * @see #setBorderToRollover
     * @since 1.4
     */
    protected void instbllRolloverBorders ( JComponent c )  {
        // Put rollover borders on buttons
        Component[] components = c.getComponents();

        for (Component component : components) {
            if (component instbnceof JComponent) {
                ((JComponent) component).updbteUI();
                setBorderToRollover(component);
            }
        }
    }

    /**
     * Instblls non-rollover borders on bll the child components of the JComponent.
     * A non-rollover border is the border thbt is instblled on the child component
     * while it is in the toolbbr.
     * <p>
     * This is b convenience method to cbll <code>setBorderToNonRollover</code>
     * for ebch child component.
     *
     * @pbrbm c contbiner which holds the child components (usublly b JToolBbr)
     * @see #setBorderToNonRollover
     * @since 1.4
     */
    protected void instbllNonRolloverBorders ( JComponent c )  {
        // Put non-rollover borders on buttons. These borders reduce the mbrgin.
        Component[] components = c.getComponents();

        for (Component component : components) {
            if (component instbnceof JComponent) {
                ((JComponent) component).updbteUI();
                setBorderToNonRollover(component);
            }
        }
    }

    /**
     * Instblls normbl borders on bll the child components of the JComponent.
     * A normbl border is the originbl border thbt wbs instblled on the child
     * component before it wbs bdded to the toolbbr.
     * <p>
     * This is b convenience method to cbll <code>setBorderNormbl</code>
     * for ebch child component.
     *
     * @pbrbm c contbiner which holds the child components (usublly b JToolBbr)
     * @see #setBorderToNonRollover
     * @since 1.4
     */
    protected void instbllNormblBorders ( JComponent c )  {
        // Put bbck the normbl borders on buttons
        Component[] components = c.getComponents();

        for (Component component : components) {
            setBorderToNormbl(component);
        }
    }

    /**
     * Sets the border of the component to hbve b rollover border which
     * wbs crebted by the {@link #crebteRolloverBorder} method.
     *
     * @pbrbm c component which will hbve b rollover border instblled
     * @see #crebteRolloverBorder
     * @since 1.4
     */
    protected void setBorderToRollover(Component c) {
        if (c instbnceof AbstrbctButton) {
            AbstrbctButton b = (AbstrbctButton)c;

            Border border = borderTbble.get(b);
            if (border == null || border instbnceof UIResource) {
                borderTbble.put(b, b.getBorder());
            }

            // Only set the border if its the defbult border
            if (b.getBorder() instbnceof UIResource) {
                b.setBorder(getRolloverBorder(b));
            }

            rolloverTbble.put(b, b.isRolloverEnbbled()?
                              Boolebn.TRUE: Boolebn.FALSE);
            b.setRolloverEnbbled(true);
        }
    }

    /**
     * Returns b rollover border for the button.
     *
     * @pbrbm b the button to cblculbte the rollover border for
     * @return the rollover border
     * @see #setBorderToRollover
     * @since 1.6
     */
    protected Border getRolloverBorder(AbstrbctButton b) {
        return rolloverBorder;
    }

    /**
     * Sets the border of the component to hbve b non-rollover border which
     * wbs crebted by the {@link #crebteNonRolloverBorder} method.
     *
     * @pbrbm c component which will hbve b non-rollover border instblled
     * @see #crebteNonRolloverBorder
     * @since 1.4
     */
    protected void setBorderToNonRollover(Component c) {
        if (c instbnceof AbstrbctButton) {
            AbstrbctButton b = (AbstrbctButton)c;

            Border border = borderTbble.get(b);
            if (border == null || border instbnceof UIResource) {
                borderTbble.put(b, b.getBorder());
            }

            // Only set the border if its the defbult border
            if (b.getBorder() instbnceof UIResource) {
                b.setBorder(getNonRolloverBorder(b));
            }
            rolloverTbble.put(b, b.isRolloverEnbbled()?
                              Boolebn.TRUE: Boolebn.FALSE);
            b.setRolloverEnbbled(fblse);
        }
    }

    /**
     * Returns b non-rollover border for the button.
     *
     * @pbrbm b the button to cblculbte the non-rollover border for
     * @return the non-rollover border
     * @see #setBorderToNonRollover
     * @since 1.6
     */
    protected Border getNonRolloverBorder(AbstrbctButton b) {
        if (b instbnceof JToggleButton) {
            return nonRolloverToggleBorder;
        } else {
            return nonRolloverBorder;
        }
    }

    /**
     * Sets the border of the component to hbve b normbl border.
     * A normbl border is the originbl border thbt wbs instblled on the child
     * component before it wbs bdded to the toolbbr.
     *
     * @pbrbm c component which will hbve b normbl border re-instblled
     * @see #crebteNonRolloverBorder
     * @since 1.4
     */
    protected void setBorderToNormbl(Component c) {
        if (c instbnceof AbstrbctButton) {
            AbstrbctButton b = (AbstrbctButton)c;

            Border border = borderTbble.remove(b);
            b.setBorder(border);

            Boolebn vblue = rolloverTbble.remove(b);
            if (vblue != null) {
                b.setRolloverEnbbled(vblue.boolebnVblue());
            }
        }
    }

    /**
     * Sets the flobting locbtion.
     *
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     */
    public void setFlobtingLocbtion(int x, int y) {
        flobtingX = x;
        flobtingY = y;
    }

    /**
     * Returns {@code true} if the {@code JToolBbr} is flobting
     *
     * @return {@code true} if the {@code JToolBbr} is flobting
     */
    public boolebn isFlobting() {
        return flobting;
    }

    /**
     * Sets the flobting property.
     *
     * @pbrbm b {@code true} if the {@code JToolBbr} is flobting
     * @pbrbm p the position
     */
    public void setFlobting(boolebn b, Point p) {
        if (toolBbr.isFlobtbble()) {
            boolebn visible = fblse;
            Window bncestor = SwingUtilities.getWindowAncestor(toolBbr);
            if (bncestor != null) {
                visible = bncestor.isVisible();
            }
            if (drbgWindow != null)
                drbgWindow.setVisible(fblse);
            this.flobting = b;
            if (flobtingToolBbr == null) {
                flobtingToolBbr = crebteFlobtingWindow(toolBbr);
            }
            if (b == true)
            {
                if (dockingSource == null)
                {
                    dockingSource = toolBbr.getPbrent();
                    dockingSource.remove(toolBbr);
                }
                constrbintBeforeFlobting = cblculbteConstrbint();
                if ( propertyListener != null )
                    UIMbnbger.bddPropertyChbngeListener( propertyListener );
                flobtingToolBbr.getContentPbne().bdd(toolBbr,BorderLbyout.CENTER);
                if (flobtingToolBbr instbnceof Window) {
                    ((Window)flobtingToolBbr).pbck();
                    ((Window)flobtingToolBbr).setLocbtion(flobtingX, flobtingY);
                    if (visible) {
                        ((Window)flobtingToolBbr).show();
                    } else {
                        bncestor.bddWindowListener(new WindowAdbpter() {
                            public void windowOpened(WindowEvent e) {
                                ((Window)flobtingToolBbr).show();
                            }
                        });
                    }
                }
            } else {
                if (flobtingToolBbr == null)
                    flobtingToolBbr = crebteFlobtingWindow(toolBbr);
                if (flobtingToolBbr instbnceof Window) ((Window)flobtingToolBbr).setVisible(fblse);
                flobtingToolBbr.getContentPbne().remove(toolBbr);
                String constrbint = getDockingConstrbint(dockingSource,
                                                         p);
                if (constrbint == null) {
                    constrbint = BorderLbyout.NORTH;
                }
                int orientbtion = mbpConstrbintToOrientbtion(constrbint);
                setOrientbtion(orientbtion);
                if (dockingSource== null)
                    dockingSource = toolBbr.getPbrent();
                if ( propertyListener != null )
                    UIMbnbger.removePropertyChbngeListener( propertyListener );
                dockingSource.bdd(constrbint, toolBbr);
            }
            dockingSource.invblidbte();
            Contbiner dockingSourcePbrent = dockingSource.getPbrent();
            if (dockingSourcePbrent != null)
                dockingSourcePbrent.vblidbte();
            dockingSource.repbint();
        }
    }

    privbte int mbpConstrbintToOrientbtion(String constrbint)
    {
        int orientbtion = toolBbr.getOrientbtion();

        if ( constrbint != null )
        {
            if ( constrbint.equbls(BorderLbyout.EAST) || constrbint.equbls(BorderLbyout.WEST) )
                orientbtion = JToolBbr.VERTICAL;
            else if ( constrbint.equbls(BorderLbyout.NORTH) || constrbint.equbls(BorderLbyout.SOUTH) )
                orientbtion = JToolBbr.HORIZONTAL;
        }

        return orientbtion;
    }

    /**
     * Sets the tool bbr's orientbtion.
     *
     * @pbrbm orientbtion the new orientbtion
     */
    public void setOrientbtion(int orientbtion)
    {
        toolBbr.setOrientbtion( orientbtion );

        if (drbgWindow !=null)
            drbgWindow.setOrientbtion(orientbtion);
    }

    /**
     * Gets the color displbyed when over b docking breb
     *
     * @return the color displbyed when over b docking breb
     */
    public Color getDockingColor() {
        return dockingColor;
    }

    /**
     * Sets the color displbyed when over b docking breb
     *
     * @pbrbm c the new color
     */
   public void setDockingColor(Color c) {
        this.dockingColor = c;
    }

    /**
     * Gets the color displbyed when over b flobting breb
     *
     * @return the color displbyed when over b flobting breb
     */
    public Color getFlobtingColor() {
        return flobtingColor;
    }

    /**
     * Sets the color displbyed when over b flobting breb
     *
     * @pbrbm c the new color
     */
    public void setFlobtingColor(Color c) {
        this.flobtingColor = c;
    }

    privbte boolebn isBlocked(Component comp, Object constrbint) {
        if (comp instbnceof Contbiner) {
            Contbiner cont = (Contbiner)comp;
            LbyoutMbnbger lm = cont.getLbyout();
            if (lm instbnceof BorderLbyout) {
                BorderLbyout blm = (BorderLbyout)lm;
                Component c = blm.getLbyoutComponent(cont, constrbint);
                return (c != null && c != toolBbr);
            }
        }
        return fblse;
    }

    /**
     * Returns {@code true} if the {@code JToolBbr} cbn dock bt the given position.
     *
     * @pbrbm c b component
     * @pbrbm p b position
     * @return {@code true} if the {@code JToolBbr} cbn dock bt the given position
     */
    public boolebn cbnDock(Component c, Point p) {
        return (p != null && getDockingConstrbint(c, p) != null);
    }

    privbte String cblculbteConstrbint() {
        String constrbint = null;
        LbyoutMbnbger lm = dockingSource.getLbyout();
        if (lm instbnceof BorderLbyout) {
            constrbint = (String)((BorderLbyout)lm).getConstrbints(toolBbr);
        }
        return (constrbint != null) ? constrbint : constrbintBeforeFlobting;
    }



    privbte String getDockingConstrbint(Component c, Point p) {
        if (p == null) return constrbintBeforeFlobting;
        if (c.contbins(p)) {
            dockingSensitivity = (toolBbr.getOrientbtion() == JToolBbr.HORIZONTAL)
                                                ? toolBbr.getSize().height
                                                : toolBbr.getSize().width;
            // North  (Bbse distbnce on height for now!)
            if (p.y < dockingSensitivity && !isBlocked(c, BorderLbyout.NORTH)) {
                return BorderLbyout.NORTH;
            }
            // Ebst  (Bbse distbnce on height for now!)
            if (p.x >= c.getWidth() - dockingSensitivity && !isBlocked(c, BorderLbyout.EAST)) {
                return BorderLbyout.EAST;
            }
            // West  (Bbse distbnce on height for now!)
            if (p.x < dockingSensitivity && !isBlocked(c, BorderLbyout.WEST)) {
                return BorderLbyout.WEST;
            }
            if (p.y >= c.getHeight() - dockingSensitivity && !isBlocked(c, BorderLbyout.SOUTH)) {
                return BorderLbyout.SOUTH;
            }
        }
        return null;
    }

    /**
     * The method is used to drbg {@code DrbgWindow} during the {@code JToolBbr}
     * is being drbgged.
     *
     * @pbrbm position the relbtive to the {@code JTollBbr} position
     * @pbrbm origin the screen position of {@code JToolBbr} before drbgging
     */
    protected void drbgTo(Point position, Point origin)
    {
        if (toolBbr.isFlobtbble())
        {
          try
          {
            if (drbgWindow == null)
                drbgWindow = crebteDrbgWindow(toolBbr);
            Point offset = drbgWindow.getOffset();
            if (offset == null) {
                Dimension size = toolBbr.getPreferredSize();
                offset = new Point(size.width/2, size.height/2);
                drbgWindow.setOffset(offset);
            }
            Point globbl = new Point(origin.x+ position.x,
                                     origin.y+position.y);
            Point drbgPoint = new Point(globbl.x- offset.x,
                                        globbl.y- offset.y);
            if (dockingSource == null)
                dockingSource = toolBbr.getPbrent();
                constrbintBeforeFlobting = cblculbteConstrbint();
            Point dockingPosition = dockingSource.getLocbtionOnScreen();
            Point compbrisonPoint = new Point(globbl.x-dockingPosition.x,
                                              globbl.y-dockingPosition.y);
            if (cbnDock(dockingSource, compbrisonPoint)) {
                drbgWindow.setBbckground(getDockingColor());
                String constrbint = getDockingConstrbint(dockingSource,
                                                         compbrisonPoint);
                int orientbtion = mbpConstrbintToOrientbtion(constrbint);
                drbgWindow.setOrientbtion(orientbtion);
                drbgWindow.setBorderColor(dockingBorderColor);
            } else {
                drbgWindow.setBbckground(getFlobtingColor());
                drbgWindow.setBorderColor(flobtingBorderColor);
                drbgWindow.setOrientbtion(toolBbr.getOrientbtion());
            }

            drbgWindow.setLocbtion(drbgPoint.x, drbgPoint.y);
            if (drbgWindow.isVisible() == fblse) {
                Dimension size = toolBbr.getPreferredSize();
                drbgWindow.setSize(size.width, size.height);
                drbgWindow.show();
            }
          }
          cbtch ( IllegblComponentStbteException e )
          {
          }
        }
    }

    /**
     * The method is cblled bt end of drbgging to plbce the frbme in either
     * its originbl plbce or in its flobting frbme.
     *
     * @pbrbm position the relbtive to the {@code JTollBbr} position
     * @pbrbm origin the screen position of {@code JToolBbr} before drbgging
     */
    protected void flobtAt(Point position, Point origin)
    {
        if(toolBbr.isFlobtbble())
        {
          try
          {
            Point offset = drbgWindow.getOffset();
            if (offset == null) {
                offset = position;
                drbgWindow.setOffset(offset);
            }
            Point globbl = new Point(origin.x+ position.x,
                                     origin.y+position.y);
            setFlobtingLocbtion(globbl.x-offset.x,
                                globbl.y-offset.y);
            if (dockingSource != null) {
                Point dockingPosition = dockingSource.getLocbtionOnScreen();
                Point compbrisonPoint = new Point(globbl.x-dockingPosition.x,
                                                  globbl.y-dockingPosition.y);
                if (cbnDock(dockingSource, compbrisonPoint)) {
                    setFlobting(fblse, compbrisonPoint);
                } else {
                    setFlobting(true, null);
                }
            } else {
                setFlobting(true, null);
            }
            drbgWindow.setOffset(null);
          }
          cbtch ( IllegblComponentStbteException e )
          {
          }
        }
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Returns bn instbnce of {@code ContbinerListener}.
     *
     * @return bn instbnce of {@code ContbinerListener}
     */
    protected ContbinerListener crebteToolBbrContListener( )
    {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of {@code FocusListener}.
     *
     * @return bn instbnce of {@code FocusListener}
     */
    protected FocusListener crebteToolBbrFocusListener( )
    {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of {@code PropertyChbngeListener}.
     *
     * @return bn instbnce of {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebtePropertyListener()
    {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of {@code MouseInputListener}.
     *
     * @return bn instbnce of {@code MouseInputListener}
     */
    protected MouseInputListener crebteDockingListener( ) {
        getHbndler().tb = toolBbr;
        return getHbndler();
    }

    /**
     * Constructs b new instbnce of {@code WindowListener}.
     *
     * @return b new instbnce of {@code WindowListener}
     */
    protected WindowListener crebteFrbmeListener() {
        return new FrbmeListener();
    }

    /**
     * Pbints the contents of the window used for drbgging.
     *
     * @pbrbm g Grbphics to pbint to.
     * @throws NullPointerException is <code>g</code> is null
     * @since 1.5
     */
    protected void pbintDrbgWindow(Grbphics g) {
        g.setColor(drbgWindow.getBbckground());
        int w = drbgWindow.getWidth();
        int h = drbgWindow.getHeight();
        g.fillRect(0, 0, w, h);
        g.setColor(drbgWindow.getBorderColor());
        g.drbwRect(0, 0, w - 1, h - 1);
    }


    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String NAVIGATE_RIGHT = "nbvigbteRight";
        privbte stbtic finbl String NAVIGATE_LEFT = "nbvigbteLeft";
        privbte stbtic finbl String NAVIGATE_UP = "nbvigbteUp";
        privbte stbtic finbl String NAVIGATE_DOWN = "nbvigbteDown";

        public Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed(ActionEvent evt) {
            String key = getNbme();
            JToolBbr toolBbr = (JToolBbr)evt.getSource();
            BbsicToolBbrUI ui = (BbsicToolBbrUI)BbsicLookAndFeel.getUIOfType(
                     toolBbr.getUI(), BbsicToolBbrUI.clbss);

            if (NAVIGATE_RIGHT == key) {
                ui.nbvigbteFocusedComp(EAST);
            } else if (NAVIGATE_LEFT == key) {
                ui.nbvigbteFocusedComp(WEST);
            } else if (NAVIGATE_UP == key) {
                ui.nbvigbteFocusedComp(NORTH);
            } else if (NAVIGATE_DOWN == key) {
                ui.nbvigbteFocusedComp(SOUTH);
            }
        }
    }


    privbte clbss Hbndler implements ContbinerListener,
            FocusListener, MouseInputListener, PropertyChbngeListener {

        //
        // ContbinerListener
        //
        public void componentAdded(ContbinerEvent evt) {
            Component c = evt.getChild();

            if (toolBbrFocusListener != null) {
                c.bddFocusListener(toolBbrFocusListener);
            }

            if (isRolloverBorders()) {
                setBorderToRollover(c);
            } else {
                setBorderToNonRollover(c);
            }
        }

        public void componentRemoved(ContbinerEvent evt) {
            Component c = evt.getChild();

            if (toolBbrFocusListener != null) {
                c.removeFocusListener(toolBbrFocusListener);
            }

            // Revert the button border
            setBorderToNormbl(c);
        }


        //
        // FocusListener
        //
        public void focusGbined(FocusEvent evt) {
            Component c = evt.getComponent();
            focusedCompIndex = toolBbr.getComponentIndex(c);
        }

        public void focusLost(FocusEvent evt) { }


        //
        // MouseInputListener (DockingListener)
        //
        JToolBbr tb;
        boolebn isDrbgging = fblse;
        Point origin = null;

        public void mousePressed(MouseEvent evt) {
            if (!tb.isEnbbled()) {
                return;
            }
            isDrbgging = fblse;
        }

        public void mouseRelebsed(MouseEvent evt) {
            if (!tb.isEnbbled()) {
                return;
            }
            if (isDrbgging) {
                Point position = evt.getPoint();
                if (origin == null)
                    origin = evt.getComponent().getLocbtionOnScreen();
                flobtAt(position, origin);
            }
            origin = null;
            isDrbgging = fblse;
        }

        public void mouseDrbgged(MouseEvent evt) {
            if (!tb.isEnbbled()) {
                return;
            }
            isDrbgging = true;
            Point position = evt.getPoint();
            if (origin == null) {
                origin = evt.getComponent().getLocbtionOnScreen();
            }
            drbgTo(position, origin);
        }

        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent evt) {}
        public void mouseExited(MouseEvent evt) {}
        public void mouseMoved(MouseEvent evt) {}


        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String propertyNbme = evt.getPropertyNbme();
            if (propertyNbme == "lookAndFeel") {
                toolBbr.updbteUI();
            } else if (propertyNbme == "orientbtion") {
                // Sebrch for JSepbrbtor components bnd chbnge it's orientbtion
                // to mbtch the toolbbr bnd flip it's orientbtion.
                Component[] components = toolBbr.getComponents();
                int orientbtion = ((Integer)evt.getNewVblue()).intVblue();
                JToolBbr.Sepbrbtor sepbrbtor;

                for (int i = 0; i < components.length; ++i) {
                    if (components[i] instbnceof JToolBbr.Sepbrbtor) {
                        sepbrbtor = (JToolBbr.Sepbrbtor)components[i];
                        if ((orientbtion == JToolBbr.HORIZONTAL)) {
                            sepbrbtor.setOrientbtion(JSepbrbtor.VERTICAL);
                        } else {
                            sepbrbtor.setOrientbtion(JSepbrbtor.HORIZONTAL);
                        }
                        Dimension size = sepbrbtor.getSepbrbtorSize();
                        if (size != null && size.width != size.height) {
                            // Flip the orientbtion.
                            Dimension newSize =
                                new Dimension(size.height, size.width);
                            sepbrbtor.setSepbrbtorSize(newSize);
                        }
                    }
                }
            } else if (propertyNbme == IS_ROLLOVER) {
                instbllNormblBorders(toolBbr);
                setRolloverBorders(((Boolebn)evt.getNewVblue()).boolebnVblue());
            }
        }
    }

    /**
     * The clbss listens for window events.
     */
    protected clbss FrbmeListener extends WindowAdbpter {
        public void windowClosing(WindowEvent w) {
            if (toolBbr.isFlobtbble()) {
                if (drbgWindow != null)
                    drbgWindow.setVisible(fblse);
                flobting = fblse;
                if (flobtingToolBbr == null)
                    flobtingToolBbr = crebteFlobtingWindow(toolBbr);
                if (flobtingToolBbr instbnceof Window) ((Window)flobtingToolBbr).setVisible(fblse);
                flobtingToolBbr.getContentPbne().remove(toolBbr);
                String constrbint = constrbintBeforeFlobting;
                if (toolBbr.getOrientbtion() == JToolBbr.HORIZONTAL) {
                    if (constrbint == "West" || constrbint == "Ebst") {
                        constrbint = "North";
                    }
                } else {
                    if (constrbint == "North" || constrbint == "South") {
                        constrbint = "West";
                    }
                }
                if (dockingSource == null)
                    dockingSource = toolBbr.getPbrent();
                if (propertyListener != null)
                    UIMbnbger.removePropertyChbngeListener(propertyListener);
                dockingSource.bdd(toolBbr, constrbint);
                dockingSource.invblidbte();
                Contbiner dockingSourcePbrent = dockingSource.getPbrent();
                if (dockingSourcePbrent != null)
                        dockingSourcePbrent.vblidbte();
                dockingSource.repbint();
            }
        }

    }

    /**
     * The clbss listens for component events.
     */
    protected clbss ToolBbrContListener implements ContbinerListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void componentAdded( ContbinerEvent e )  {
            getHbndler().componentAdded(e);
        }

        public void componentRemoved( ContbinerEvent e ) {
            getHbndler().componentRemoved(e);
        }

    }

    /**
     * The clbss listens for focus events.
     */
    protected clbss ToolBbrFocusListener implements FocusListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void focusGbined( FocusEvent e ) {
            getHbndler().focusGbined(e);
            }

        public void focusLost( FocusEvent e ) {
            getHbndler().focusLost(e);
            }
    }

    /**
     * The clbss listens for property chbnged events.
     */
    protected clbss PropertyListener implements PropertyChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void propertyChbnge( PropertyChbngeEvent e ) {
            getHbndler().propertyChbnge(e);
            }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicToolBbrUI.
     */
    public clbss DockingListener implements MouseInputListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        /**
         * The instbnce of {@code JToolBbr}.
         */
        protected JToolBbr toolBbr;
        /**
         * {@code true} if the {@code JToolBbr} is being drbgged.
         */
        protected boolebn isDrbgging = fblse;
        /**
         * The origin point.
         */
        protected Point origin = null;

        /**
         * Constructs b new instbnce of {@code DockingListener}.
         *
         * @pbrbm t bn instbnce of {@code JToolBbr}
         */
        public DockingListener(JToolBbr t) {
            this.toolBbr = t;
            getHbndler().tb = t;
        }

        public void mouseClicked(MouseEvent e) {
        getHbndler().mouseClicked(e);
    }

        public void mousePressed(MouseEvent e) {
        getHbndler().tb = toolBbr;
        getHbndler().mousePressed(e);
        isDrbgging = getHbndler().isDrbgging;
        }

        public void mouseRelebsed(MouseEvent e) {
        getHbndler().tb = toolBbr;
        getHbndler().isDrbgging = isDrbgging;
        getHbndler().origin = origin;
        getHbndler().mouseRelebsed(e);
        isDrbgging = getHbndler().isDrbgging;
        origin = getHbndler().origin;
        }

        public void mouseEntered(MouseEvent e) {
        getHbndler().mouseEntered(e);
    }

        public void mouseExited(MouseEvent e) {
        getHbndler().mouseExited(e);
    }

        public void mouseDrbgged(MouseEvent e) {
        getHbndler().tb = toolBbr;
        getHbndler().origin = origin;
        getHbndler().mouseDrbgged(e);
        isDrbgging = getHbndler().isDrbgging;
        origin = getHbndler().origin;
        }

        public void mouseMoved(MouseEvent e) {
        getHbndler().mouseMoved(e);
        }
    }

    /**
     * The window which bppebrs during drbgging the {@code JToolBbr}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss DrbgWindow extends Window
    {
        Color borderColor = Color.grby;
        int orientbtion = toolBbr.getOrientbtion();
        Point offset; // offset of the mouse cursor inside the DrbgWindow

        DrbgWindow(Window w) {
            super(w);
        }

    /**
     * Returns the orientbtion of the toolbbr window when the toolbbr is
     * flobting. The orientbtion is either one of <code>JToolBbr.HORIZONTAL</code>
     * or <code>JToolBbr.VERTICAL</code>.
     *
     * @return the orientbtion of the toolbbr window
     * @since 1.6
     */
    public int getOrientbtion() {
        return orientbtion;
    }

        /**
         * Sets the orientbtion.
         *
         * @pbrbm o the new orientbtion
         */
        public void setOrientbtion(int o) {
            if(isShowing()) {
                if (o == this.orientbtion)
                    return;
                this.orientbtion = o;
                Dimension size = getSize();
                setSize(new Dimension(size.height, size.width));
                if (offset!=null) {
                    if( BbsicGrbphicsUtils.isLeftToRight(toolBbr) ) {
                        setOffset(new Point(offset.y, offset.x));
                    } else if( o == JToolBbr.HORIZONTAL ) {
                        setOffset(new Point( size.height-offset.y, offset.x));
                    } else {
                        setOffset(new Point(offset.y, size.width-offset.x));
                    }
                }
                repbint();
            }
        }

        /**
         * Returns the offset.
         *
         * @return the offset
         */
        public Point getOffset() {
            return offset;
        }

        /**
         * Sets the offset.
         *
         * @pbrbm p the new offset
         */
        public void setOffset(Point p) {
            this.offset = p;
        }

        /**
         * Sets the border color.
         *
         * @pbrbm c the new border color
         */
        public void setBorderColor(Color c) {
            if (this.borderColor == c)
                return;
            this.borderColor = c;
            repbint();
        }

        /**
         * Returns the border color.
         *
         * @return the border color
         */
        public Color getBorderColor() {
            return this.borderColor;
        }

        public void pbint(Grbphics g) {
            pbintDrbgWindow(g);
            // Pbint the children
            super.pbint(g);
        }
        public Insets getInsets() {
            return new Insets(1,1,1,1);
        }
    }
}
