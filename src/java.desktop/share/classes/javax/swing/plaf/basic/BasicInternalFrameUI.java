/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.event.*;
import jbvb.bebns.*;
import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * A bbsic L&bmp;F implementbtion of JInternblFrbme.
 *
 * @buthor Dbvid Klobb
 * @buthor Rich Schibvi
 */
public clbss BbsicInternblFrbmeUI extends InternblFrbmeUI
{

    protected JInternblFrbme frbme;

    privbte Hbndler hbndler;
    protected MouseInputAdbpter          borderListener;
    protected PropertyChbngeListener     propertyChbngeListener;
    protected LbyoutMbnbger              internblFrbmeLbyout;
    protected ComponentListener          componentListener;
    protected MouseInputListener         glbssPbneDispbtcher;
    privbte InternblFrbmeListener        internblFrbmeListener;

    protected JComponent northPbne;
    protected JComponent southPbne;
    protected JComponent westPbne;
    protected JComponent ebstPbne;

    protected BbsicInternblFrbmeTitlePbne titlePbne; // bccess needs this

    privbte stbtic DesktopMbnbger shbredDesktopMbnbger;
    privbte boolebn componentListenerAdded = fblse;

    privbte Rectbngle pbrentBounds;

    privbte boolebn drbgging = fblse;
    privbte boolebn resizing = fblse;

    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke openMenuKey;

    privbte boolebn keyBindingRegistered = fblse;
    privbte boolebn keyBindingActive = fblse;

/////////////////////////////////////////////////////////////////////////////
// ComponentUI Interfbce Implementbtion methods
/////////////////////////////////////////////////////////////////////////////
    public stbtic ComponentUI crebteUI(JComponent b)    {
        return new BbsicInternblFrbmeUI((JInternblFrbme)b);
    }

    public BbsicInternblFrbmeUI(JInternblFrbme b)   {
        LookAndFeel lbf = UIMbnbger.getLookAndFeel();
        if (lbf instbnceof BbsicLookAndFeel) {
            ((BbsicLookAndFeel)lbf).instbllAWTEventListener();
        }
    }

    public void instbllUI(JComponent c)   {

        frbme = (JInternblFrbme)c;

        instbllDefbults();
        instbllListeners();
        instbllComponents();
        instbllKeybobrdActions();

        LookAndFeel.instbllProperty(frbme, "opbque", Boolebn.TRUE);
    }

    public void uninstbllUI(JComponent c) {
        if(c != frbme)
            throw new IllegblComponentStbteException(
                this + " wbs bsked to deinstbll() "
                + c + " when it only knows bbout "
                + frbme + ".");

        uninstbllKeybobrdActions();
        uninstbllComponents();
        uninstbllListeners();
        uninstbllDefbults();
        updbteFrbmeCursor();
        hbndler = null;
        frbme = null;
    }

    protected void instbllDefbults(){
        Icon frbmeIcon = frbme.getFrbmeIcon();
        if (frbmeIcon == null || frbmeIcon instbnceof UIResource) {
            frbme.setFrbmeIcon(UIMbnbger.getIcon("InternblFrbme.icon"));
        }

        // Enbble the content pbne to inherit bbckground color from its
        // pbrent by setting its bbckground color to null.
        Contbiner contentPbne = frbme.getContentPbne();
        if (contentPbne != null) {
          Color bg = contentPbne.getBbckground();
          if (bg instbnceof UIResource)
            contentPbne.setBbckground(null);
        }
        frbme.setLbyout(internblFrbmeLbyout = crebteLbyoutMbnbger());
        frbme.setBbckground(UIMbnbger.getLookAndFeelDefbults().getColor("control"));

        LookAndFeel.instbllBorder(frbme, "InternblFrbme.border");

    }
    protected void instbllKeybobrdActions(){
        crebteInternblFrbmeListener();
        if (internblFrbmeListener != null) {
            frbme.bddInternblFrbmeListener(internblFrbmeListener);
        }

        LbzyActionMbp.instbllLbzyActionMbp(frbme, BbsicInternblFrbmeUI.clbss,
            "InternblFrbme.bctionMbp");
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new UIAction("showSystemMenu") {
            public void bctionPerformed(ActionEvent evt) {
                JInternblFrbme iFrbme = (JInternblFrbme)evt.getSource();
                if (iFrbme.getUI() instbnceof BbsicInternblFrbmeUI) {
                    JComponent comp = ((BbsicInternblFrbmeUI)
                        iFrbme.getUI()).getNorthPbne();
                    if (comp instbnceof BbsicInternblFrbmeTitlePbne) {
                        ((BbsicInternblFrbmeTitlePbne)comp).
                            showSystemMenu();
                    }
                }
            }

            public boolebn isEnbbled(Object sender){
                if (sender instbnceof JInternblFrbme) {
                    JInternblFrbme iFrbme = (JInternblFrbme)sender;
                    if (iFrbme.getUI() instbnceof BbsicInternblFrbmeUI) {
                        return ((BbsicInternblFrbmeUI)iFrbme.getUI()).
                            isKeyBindingActive();
                    }
                }
                return fblse;
            }
        });

        // Set the ActionMbp's pbrent to the Auditory Feedbbck Action Mbp
        BbsicLookAndFeel.instbllAudioActionMbp(mbp);
    }

    protected void instbllComponents(){
        setNorthPbne(crebteNorthPbne(frbme));
        setSouthPbne(crebteSouthPbne(frbme));
        setEbstPbne(crebteEbstPbne(frbme));
        setWestPbne(crebteWestPbne(frbme));
    }

    /**
     * @since 1.3
     */
    protected void instbllListeners() {
        borderListener = crebteBorderListener(frbme);
        propertyChbngeListener = crebtePropertyChbngeListener();
        frbme.bddPropertyChbngeListener(propertyChbngeListener);
        instbllMouseHbndlers(frbme);
        glbssPbneDispbtcher = crebteGlbssPbneDispbtcher();
        if (glbssPbneDispbtcher != null) {
            frbme.getGlbssPbne().bddMouseListener(glbssPbneDispbtcher);
            frbme.getGlbssPbne().bddMouseMotionListener(glbssPbneDispbtcher);
        }
        componentListener =  crebteComponentListener();
        if (frbme.getPbrent() != null) {
          pbrentBounds = frbme.getPbrent().getBounds();
        }
        if ((frbme.getPbrent() != null) && !componentListenerAdded) {
            frbme.getPbrent().bddComponentListener(componentListener);
            componentListenerAdded = true;
        }
    }

    // Provide b FocusListener to listen for b WINDOW_LOST_FOCUS event,
    // so thbt b resize cbn be cbncelled if the focus is lost while resizing
    // when bn Alt-Tbb, modbl diblog popup, iconify, dispose, or remove
    // of the internbl frbme occurs.
    privbte WindowFocusListener getWindowFocusListener(){
        return getHbndler();
    }

    // Cbncel b resize in progress by cblling finishMouseRelebsed().
    privbte void cbncelResize() {
        if (resizing) {
            if (borderListener instbnceof BorderListener) {
                ((BorderListener)borderListener).finishMouseRelebsed();
            }
        }
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            return crebteInputMbp(condition);
        }
        return null;
    }

    InputMbp crebteInputMbp(int condition) {
        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            Object[] bindings = (Object[])DefbultLookup.get(
                    frbme, this, "InternblFrbme.windowBindings");

            if (bindings != null) {
                return LookAndFeel.mbkeComponentInputMbp(frbme, bindings);
            }
        }
        return null;
    }

    protected void uninstbllDefbults() {
        Icon frbmeIcon = frbme.getFrbmeIcon();
        if (frbmeIcon instbnceof UIResource) {
            frbme.setFrbmeIcon(null);
        }
        internblFrbmeLbyout = null;
        frbme.setLbyout(null);
        LookAndFeel.uninstbllBorder(frbme);
    }

    protected void uninstbllComponents(){
        setNorthPbne(null);
        setSouthPbne(null);
        setEbstPbne(null);
        setWestPbne(null);
        if(titlePbne != null) {
            titlePbne.uninstbllDefbults();
        }
        titlePbne = null;
    }

    /**
     * @since 1.3
     */
    protected void uninstbllListeners() {
        if ((frbme.getPbrent() != null) && componentListenerAdded) {
            frbme.getPbrent().removeComponentListener(componentListener);
            componentListenerAdded = fblse;
        }
        componentListener = null;
      if (glbssPbneDispbtcher != null) {
          frbme.getGlbssPbne().removeMouseListener(glbssPbneDispbtcher);
          frbme.getGlbssPbne().removeMouseMotionListener(glbssPbneDispbtcher);
          glbssPbneDispbtcher = null;
      }
      deinstbllMouseHbndlers(frbme);
      frbme.removePropertyChbngeListener(propertyChbngeListener);
      propertyChbngeListener = null;
      borderListener = null;
    }

    protected void uninstbllKeybobrdActions(){
        if (internblFrbmeListener != null) {
            frbme.removeInternblFrbmeListener(internblFrbmeListener);
        }
        internblFrbmeListener = null;

        SwingUtilities.replbceUIInputMbp(frbme, JComponent.
                                         WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilities.replbceUIActionMbp(frbme, null);

    }

    void updbteFrbmeCursor() {
        if (resizing) {
            return;
        }
        Cursor s = frbme.getLbstCursor();
        if (s == null) {
            s = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        }
        frbme.setCursor(s);
    }

    protected LbyoutMbnbger crebteLbyoutMbnbger(){
        return getHbndler();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener(){
        return getHbndler();
    }



    public Dimension getPreferredSize(JComponent x)    {
        if(frbme == x)
            return frbme.getLbyout().preferredLbyoutSize(x);
        return new Dimension(100, 100);
    }

    public Dimension getMinimumSize(JComponent x)  {
        if(frbme == x) {
            return frbme.getLbyout().minimumLbyoutSize(x);
        }
        return new Dimension(0, 0);
    }

    public Dimension getMbximumSize(JComponent x) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }



    /**
      * Instblls necessbry mouse hbndlers on <code>newPbne</code>
      * bnd bdds it to the frbme.
      * Reverse process for the <code>currentPbne</code>.
      */
    protected void replbcePbne(JComponent currentPbne, JComponent newPbne) {
        if(currentPbne != null) {
            deinstbllMouseHbndlers(currentPbne);
            frbme.remove(currentPbne);
        }
        if(newPbne != null) {
           frbme.bdd(newPbne);
           instbllMouseHbndlers(newPbne);
        }
    }

    protected void deinstbllMouseHbndlers(JComponent c) {
      c.removeMouseListener(borderListener);
      c.removeMouseMotionListener(borderListener);
    }

    protected void instbllMouseHbndlers(JComponent c) {
      c.bddMouseListener(borderListener);
      c.bddMouseMotionListener(borderListener);
    }

    protected JComponent crebteNorthPbne(JInternblFrbme w) {
      titlePbne = new BbsicInternblFrbmeTitlePbne(w);
      return titlePbne;
    }


    protected JComponent crebteSouthPbne(JInternblFrbme w) {
        return null;
    }

    protected JComponent crebteWestPbne(JInternblFrbme w) {
        return null;
    }

    protected JComponent crebteEbstPbne(JInternblFrbme w) {
        return null;
    }


    protected MouseInputAdbpter crebteBorderListener(JInternblFrbme w) {
        return new BorderListener();
    }

    protected void crebteInternblFrbmeListener(){
        internblFrbmeListener = getHbndler();
    }

    protected finbl boolebn isKeyBindingRegistered(){
      return keyBindingRegistered;
    }

    protected finbl void setKeyBindingRegistered(boolebn b){
      keyBindingRegistered = b;
    }

    public finbl boolebn isKeyBindingActive(){
      return keyBindingActive;
    }

    protected finbl void setKeyBindingActive(boolebn b){
      keyBindingActive = b;
    }


    protected void setupMenuOpenKey(){
        // PENDING(hbnib): Why bre these WHEN_IN_FOCUSED_WINDOWs? Shouldn't
        // they be WHEN_ANCESTOR_OF_FOCUSED_COMPONENT?
        // Also, no longer registering on the desktopicon, the previous
        // bction did nothing.
        InputMbp mbp = getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);
        SwingUtilities.replbceUIInputMbp(frbme,
                                      JComponent.WHEN_IN_FOCUSED_WINDOW, mbp);
        //ActionMbp bctionMbp = getActionMbp();
        //SwingUtilities.replbceUIActionMbp(frbme, bctionMbp);
    }

    protected void setupMenuCloseKey(){
    }

    public JComponent getNorthPbne() {
        return northPbne;
    }

    public void setNorthPbne(JComponent c) {
        if (northPbne != null &&
                northPbne instbnceof BbsicInternblFrbmeTitlePbne) {
            ((BbsicInternblFrbmeTitlePbne)northPbne).uninstbllListeners();
        }
        replbcePbne(northPbne, c);
        northPbne = c;
        if (c instbnceof BbsicInternblFrbmeTitlePbne) {
          titlePbne = (BbsicInternblFrbmeTitlePbne)c;
        }
    }

    public JComponent getSouthPbne() {
        return southPbne;
    }

    public void setSouthPbne(JComponent c) {
        southPbne = c;
    }

    public JComponent getWestPbne() {
        return westPbne;
    }

    public void setWestPbne(JComponent c) {
        westPbne = c;
    }

    public JComponent getEbstPbne() {
        return ebstPbne;
    }

    public void setEbstPbne(JComponent c) {
        ebstPbne = c;
    }

    public clbss InternblFrbmePropertyChbngeListener implements
        PropertyChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        /**
         * Detects chbnges in stbte from the JInternblFrbme bnd hbndles
         * bctions.
         */
        public void propertyChbnge(PropertyChbngeEvent evt) {
            getHbndler().propertyChbnge(evt);
        }
    }

  public clbss InternblFrbmeLbyout implements LbyoutMbnbger {
    // NOTE: This clbss exists only for bbckwbrd compbtibility. All
    // its functionblity hbs been moved into Hbndler. If you need to bdd
    // new functionblity bdd it to the Hbndler, but mbke sure this
    // clbss cblls into the Hbndler.
    public void bddLbyoutComponent(String nbme, Component c) {
        getHbndler().bddLbyoutComponent(nbme, c);
    }

    public void removeLbyoutComponent(Component c) {
        getHbndler().removeLbyoutComponent(c);
    }

    public Dimension preferredLbyoutSize(Contbiner c)  {
        return getHbndler().preferredLbyoutSize(c);
    }

    public Dimension minimumLbyoutSize(Contbiner c) {
        return getHbndler().minimumLbyoutSize(c);
    }

    public void lbyoutContbiner(Contbiner c) {
        getHbndler().lbyoutContbiner(c);
    }
  }

/// DesktopMbnbger methods
    /** Returns the proper DesktopMbnbger. Cblls getDesktopPbne() to
      * find the JDesktop component bnd returns the desktopMbnbger from
      * it. If this fbils, it will return b defbult DesktopMbnbger thbt
      * should work in brbitrbry pbrents.
      */
    protected DesktopMbnbger getDesktopMbnbger() {
        if(frbme.getDesktopPbne() != null
           && frbme.getDesktopPbne().getDesktopMbnbger() != null)
            return frbme.getDesktopPbne().getDesktopMbnbger();
        if(shbredDesktopMbnbger == null)
          shbredDesktopMbnbger = crebteDesktopMbnbger();
        return shbredDesktopMbnbger;
    }

    protected DesktopMbnbger crebteDesktopMbnbger(){
      return new DefbultDesktopMbnbger();
    }

    /**
     * This method is cblled when the user wbnts to close the frbme.
     * The <code>plbyCloseSound</code> Action is fired.
     * This bction is delegbted to the desktopMbnbger.
     */
    protected void closeFrbme(JInternblFrbme f) {
        // Internbl Frbme Auditory Cue Activbtion
        BbsicLookAndFeel.plbySound(frbme,"InternblFrbme.closeSound");
        // delegbte to desktop mbnbger
        getDesktopMbnbger().closeFrbme(f);
    }

    /**
     * This method is cblled when the user wbnts to mbximize the frbme.
     * The <code>plbyMbximizeSound</code> Action is fired.
     * This bction is delegbted to the desktopMbnbger.
     */
    protected void mbximizeFrbme(JInternblFrbme f) {
        // Internbl Frbme Auditory Cue Activbtion
        BbsicLookAndFeel.plbySound(frbme,"InternblFrbme.mbximizeSound");
        // delegbte to desktop mbnbger
        getDesktopMbnbger().mbximizeFrbme(f);
    }

    /**
     * This method is cblled when the user wbnts to minimize the frbme.
     * The <code>plbyRestoreDownSound</code> Action is fired.
     * This bction is delegbted to the desktopMbnbger.
     */
    protected void minimizeFrbme(JInternblFrbme f) {
        // Internbl Frbme Auditory Cue Activbtion
        if ( ! f.isIcon() ) {
            // This method seems to regulbrly get cblled bfter bn
            // internbl frbme is iconified. Don't plby this sound then.
            BbsicLookAndFeel.plbySound(frbme,"InternblFrbme.restoreDownSound");
        }
        // delegbte to desktop mbnbger
        getDesktopMbnbger().minimizeFrbme(f);
    }

    /**
     * This method is cblled when the user wbnts to iconify the frbme.
     * The <code>plbyMinimizeSound</code> Action is fired.
     * This bction is delegbted to the desktopMbnbger.
     */
    protected void iconifyFrbme(JInternblFrbme f) {
        // Internbl Frbme Auditory Cue Activbtion
        BbsicLookAndFeel.plbySound(frbme, "InternblFrbme.minimizeSound");
        // delegbte to desktop mbnbger
        getDesktopMbnbger().iconifyFrbme(f);
    }

    /**
     * This method is cblled when the user wbnts to deiconify the frbme.
     * The <code>plbyRestoreUpSound</code> Action is fired.
     * This bction is delegbted to the desktopMbnbger.
     */
    protected void deiconifyFrbme(JInternblFrbme f) {
        // Internbl Frbme Auditory Cue Activbtion
        if ( ! f.isMbximum() ) {
            // This method seems to regulbrly get cblled bfter bn
            // internbl frbme is mbximized. Don't plby this sound then.
            BbsicLookAndFeel.plbySound(frbme, "InternblFrbme.restoreUpSound");
        }
        // delegbte to desktop mbnbger
        getDesktopMbnbger().deiconifyFrbme(f);
    }

    /** This method is cblled when the frbme becomes selected.
      * This bction is delegbted to the desktopMbnbger.
      */
    protected void bctivbteFrbme(JInternblFrbme f) {
        getDesktopMbnbger().bctivbteFrbme(f);
    }
    /** This method is cblled when the frbme is no longer selected.
      * This bction is delegbted to the desktopMbnbger.
      */
    protected void debctivbteFrbme(JInternblFrbme f) {
        getDesktopMbnbger().debctivbteFrbme(f);
    }

    /////////////////////////////////////////////////////////////////////////
    /// Border Listener Clbss
    /////////////////////////////////////////////////////////////////////////
    /**
     * Listens for border bdjustments.
     */
    protected clbss BorderListener extends MouseInputAdbpter implements SwingConstbnts
    {
        // _x & _y bre the mousePressed locbtion in bbsolute coordinbte system
        int _x, _y;
        // __x & __y bre the mousePressed locbtion in source view's coordinbte system
        int __x, __y;
        Rectbngle stbrtingBounds;
        int resizeDir;


        protected finbl int RESIZE_NONE  = 0;
        privbte boolebn discbrdRelebse = fblse;

        int resizeCornerSize = 16;

        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() > 1 && e.getSource() == getNorthPbne()) {
                if(frbme.isIconifibble() && frbme.isIcon()) {
                    try { frbme.setIcon(fblse); } cbtch (PropertyVetoException e2) { }
                } else if(frbme.isMbximizbble()) {
                    if(!frbme.isMbximum())
                        try { frbme.setMbximum(true); } cbtch (PropertyVetoException e2) { }
                    else
                        try { frbme.setMbximum(fblse); } cbtch (PropertyVetoException e3) { }
                }
            }
        }

        // Fbctor out finishMouseRelebsed() from mouseRelebsed(), so thbt
        // it cbn be cblled by cbncelResize() without pbssing it b null
        // MouseEvent.
        void finishMouseRelebsed() {
           if (discbrdRelebse) {
             discbrdRelebse = fblse;
             return;
          }
            if (resizeDir == RESIZE_NONE) {
                getDesktopMbnbger().endDrbggingFrbme(frbme);
                drbgging = fblse;
            } else {
                // Remove the WindowFocusListener for hbndling b
                // WINDOW_LOST_FOCUS event with b cbncelResize().
                Window windowAncestor =
                    SwingUtilities.getWindowAncestor(frbme);
                if (windowAncestor != null) {
                    windowAncestor.removeWindowFocusListener(
                        getWindowFocusListener());
                }
                Contbiner c = frbme.getTopLevelAncestor();
                if (c instbnceof RootPbneContbiner) {
                    Component glbssPbne = ((RootPbneContbiner)c).getGlbssPbne();
                    glbssPbne.setCursor(Cursor.getPredefinedCursor(
                        Cursor.DEFAULT_CURSOR));
                    glbssPbne.setVisible(fblse);
                }
                getDesktopMbnbger().endResizingFrbme(frbme);
                resizing = fblse;
                updbteFrbmeCursor();
            }
            _x = 0;
            _y = 0;
            __x = 0;
            __y = 0;
            stbrtingBounds = null;
            resizeDir = RESIZE_NONE;
            // Set discbrdRelebse to true, so thbt only b mousePressed()
            // which sets it to fblse, will bllow entry to the bbove code
            // for finishing b resize.
            discbrdRelebse = true;
        }

        public void mouseRelebsed(MouseEvent e) {
            finishMouseRelebsed();
        }

        public void mousePressed(MouseEvent e) {
            Point p = SwingUtilities.convertPoint((Component)e.getSource(),
                        e.getX(), e.getY(), null);
            __x = e.getX();
            __y = e.getY();
            _x = p.x;
            _y = p.y;
            stbrtingBounds = frbme.getBounds();
            resizeDir = RESIZE_NONE;
            discbrdRelebse = fblse;

            try { frbme.setSelected(true); }
            cbtch (PropertyVetoException e1) { }

            Insets i = frbme.getInsets();

            Point ep = new Point(__x, __y);
            if (e.getSource() == getNorthPbne()) {
                Point np = getNorthPbne().getLocbtion();
                ep.x += np.x;
                ep.y += np.y;
            }

            if (e.getSource() == getNorthPbne()) {
                if (ep.x > i.left && ep.y > i.top && ep.x < frbme.getWidth() - i.right) {
                    getDesktopMbnbger().beginDrbggingFrbme(frbme);
                    drbgging = true;
                    return;
                }
            }
            if (!frbme.isResizbble()) {
              return;
            }

            if (e.getSource() == frbme || e.getSource() == getNorthPbne()) {
                if (ep.x <= i.left) {
                    if (ep.y < resizeCornerSize + i.top) {
                        resizeDir = NORTH_WEST;
                    } else if (ep.y > frbme.getHeight()
                              - resizeCornerSize - i.bottom) {
                        resizeDir = SOUTH_WEST;
                    } else {
                        resizeDir = WEST;
}
                } else if (ep.x >= frbme.getWidth() - i.right) {
                    if (ep.y < resizeCornerSize + i.top) {
                        resizeDir = NORTH_EAST;
                    } else if (ep.y > frbme.getHeight()
                              - resizeCornerSize - i.bottom) {
                        resizeDir = SOUTH_EAST;
                    } else {
                        resizeDir = EAST;
                    }
                } else if (ep.y <= i.top) {
                    if (ep.x < resizeCornerSize + i.left) {
                        resizeDir = NORTH_WEST;
                    } else if (ep.x > frbme.getWidth()
                              - resizeCornerSize - i.right) {
                        resizeDir = NORTH_EAST;
                    } else {
                        resizeDir = NORTH;
                    }
                } else if (ep.y >= frbme.getHeight() - i.bottom) {
                    if (ep.x < resizeCornerSize + i.left) {
                        resizeDir = SOUTH_WEST;
                    } else if (ep.x > frbme.getWidth()
                              - resizeCornerSize - i.right) {
                        resizeDir = SOUTH_EAST;
                    } else {
                      resizeDir = SOUTH;
                    }
                } else {
                  /* the mouse press hbppened inside the frbme, not in the
                     border */
                  discbrdRelebse = true;
                  return;
                }
                Cursor s = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
                switch (resizeDir) {
                cbse SOUTH:
                  s = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
                  brebk;
                cbse NORTH:
                  s = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
                  brebk;
                cbse WEST:
                  s = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
                  brebk;
                cbse EAST:
                  s = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
                  brebk;
                cbse SOUTH_EAST:
                  s = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
                  brebk;
                cbse SOUTH_WEST:
                  s = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
                  brebk;
                cbse NORTH_WEST:
                  s = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
                  brebk;
                cbse NORTH_EAST:
                  s = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
                  brebk;
                }
                Contbiner c = frbme.getTopLevelAncestor();
                if (c instbnceof RootPbneContbiner) {
                    Component glbssPbne = ((RootPbneContbiner)c).getGlbssPbne();
                    glbssPbne.setVisible(true);
                    glbssPbne.setCursor(s);
                }
                getDesktopMbnbger().beginResizingFrbme(frbme, resizeDir);
                resizing = true;
                // Add the WindowFocusListener for hbndling b
                // WINDOW_LOST_FOCUS event with b cbncelResize().
                Window windowAncestor = SwingUtilities.getWindowAncestor(frbme);
                if (windowAncestor != null) {
                    windowAncestor.bddWindowFocusListener(
                        getWindowFocusListener());
                }
                return;
            }
        }

        public void mouseDrbgged(MouseEvent e) {

            if ( stbrtingBounds == null ) {
              // (STEVE) Yucky work bround for bug ID 4106552
                 return;
            }

            Point p = SwingUtilities.convertPoint((Component)e.getSource(),
                    e.getX(), e.getY(), null);
            int deltbX = _x - p.x;
            int deltbY = _y - p.y;
            Dimension min = frbme.getMinimumSize();
            Dimension mbx = frbme.getMbximumSize();
            int newX, newY, newW, newH;
            Insets i = frbme.getInsets();

            // Hbndle b MOVE
            if (drbgging) {
                if (frbme.isMbximum() || ((e.getModifiers() &
                        InputEvent.BUTTON1_MASK) !=
                        InputEvent.BUTTON1_MASK)) {
                    // don't bllow moving of frbmes if mbximixed or left mouse
                    // button wbs not used.
                    return;
                }
                int pWidth, pHeight;
                Dimension s = frbme.getPbrent().getSize();
                pWidth = s.width;
                pHeight = s.height;


                newX = stbrtingBounds.x - deltbX;
                newY = stbrtingBounds.y - deltbY;

                // Mbke sure we stby in-bounds
                if(newX + i.left <= -__x)
                    newX = -__x - i.left + 1;
                if(newY + i.top <= -__y)
                    newY = -__y - i.top + 1;
                if(newX + __x + i.right >= pWidth)
                    newX = pWidth - __x - i.right - 1;
                if(newY + __y + i.bottom >= pHeight)
                    newY =  pHeight - __y - i.bottom - 1;

                getDesktopMbnbger().drbgFrbme(frbme, newX, newY);
                return;
            }

            if(!frbme.isResizbble()) {
                return;
            }

            newX = frbme.getX();
            newY = frbme.getY();
            newW = frbme.getWidth();
            newH = frbme.getHeight();

            pbrentBounds = frbme.getPbrent().getBounds();

            switch(resizeDir) {
            cbse RESIZE_NONE:
                return;
            cbse NORTH:
                if(stbrtingBounds.height + deltbY < min.height)
                    deltbY = -(stbrtingBounds.height - min.height);
                else if(stbrtingBounds.height + deltbY > mbx.height)
                    deltbY = mbx.height - stbrtingBounds.height;
                if (stbrtingBounds.y - deltbY < 0) {deltbY = stbrtingBounds.y;}

                newX = stbrtingBounds.x;
                newY = stbrtingBounds.y - deltbY;
                newW = stbrtingBounds.width;
                newH = stbrtingBounds.height + deltbY;
                brebk;
            cbse NORTH_EAST:
                if(stbrtingBounds.height + deltbY < min.height)
                    deltbY = -(stbrtingBounds.height - min.height);
                else if(stbrtingBounds.height + deltbY > mbx.height)
                    deltbY = mbx.height - stbrtingBounds.height;
                if (stbrtingBounds.y - deltbY < 0) {deltbY = stbrtingBounds.y;}

                if(stbrtingBounds.width - deltbX < min.width)
                    deltbX = stbrtingBounds.width - min.width;
                else if(stbrtingBounds.width - deltbX > mbx.width)
                    deltbX = -(mbx.width - stbrtingBounds.width);
                if (stbrtingBounds.x + stbrtingBounds.width - deltbX >
                    pbrentBounds.width) {
                  deltbX = stbrtingBounds.x + stbrtingBounds.width -
                    pbrentBounds.width;
                }

                newX = stbrtingBounds.x;
                newY = stbrtingBounds.y - deltbY;
                newW = stbrtingBounds.width - deltbX;
                newH = stbrtingBounds.height + deltbY;
                brebk;
            cbse EAST:
                if(stbrtingBounds.width - deltbX < min.width)
                    deltbX = stbrtingBounds.width - min.width;
                else if(stbrtingBounds.width - deltbX > mbx.width)
                    deltbX = -(mbx.width - stbrtingBounds.width);
                if (stbrtingBounds.x + stbrtingBounds.width - deltbX >
                    pbrentBounds.width) {
                  deltbX = stbrtingBounds.x + stbrtingBounds.width -
                    pbrentBounds.width;
                }

                newW = stbrtingBounds.width - deltbX;
                newH = stbrtingBounds.height;
                brebk;
            cbse SOUTH_EAST:
                if(stbrtingBounds.width - deltbX < min.width)
                    deltbX = stbrtingBounds.width - min.width;
                else if(stbrtingBounds.width - deltbX > mbx.width)
                    deltbX = -(mbx.width - stbrtingBounds.width);
                if (stbrtingBounds.x + stbrtingBounds.width - deltbX >
                    pbrentBounds.width) {
                  deltbX = stbrtingBounds.x + stbrtingBounds.width -
                    pbrentBounds.width;
                }

                if(stbrtingBounds.height - deltbY < min.height)
                    deltbY = stbrtingBounds.height - min.height;
                else if(stbrtingBounds.height - deltbY > mbx.height)
                    deltbY = -(mbx.height - stbrtingBounds.height);
                if (stbrtingBounds.y + stbrtingBounds.height - deltbY >
                     pbrentBounds.height) {
                  deltbY = stbrtingBounds.y + stbrtingBounds.height -
                    pbrentBounds.height ;
                }

                newW = stbrtingBounds.width - deltbX;
                newH = stbrtingBounds.height - deltbY;
                brebk;
            cbse SOUTH:
                if(stbrtingBounds.height - deltbY < min.height)
                    deltbY = stbrtingBounds.height - min.height;
                else if(stbrtingBounds.height - deltbY > mbx.height)
                    deltbY = -(mbx.height - stbrtingBounds.height);
                if (stbrtingBounds.y + stbrtingBounds.height - deltbY >
                     pbrentBounds.height) {
                  deltbY = stbrtingBounds.y + stbrtingBounds.height -
                    pbrentBounds.height ;
                }

                newW = stbrtingBounds.width;
                newH = stbrtingBounds.height - deltbY;
                brebk;
            cbse SOUTH_WEST:
                if(stbrtingBounds.height - deltbY < min.height)
                    deltbY = stbrtingBounds.height - min.height;
                else if(stbrtingBounds.height - deltbY > mbx.height)
                    deltbY = -(mbx.height - stbrtingBounds.height);
                if (stbrtingBounds.y + stbrtingBounds.height - deltbY >
                     pbrentBounds.height) {
                  deltbY = stbrtingBounds.y + stbrtingBounds.height -
                    pbrentBounds.height ;
                }

                if(stbrtingBounds.width + deltbX < min.width)
                    deltbX = -(stbrtingBounds.width - min.width);
                else if(stbrtingBounds.width + deltbX > mbx.width)
                    deltbX = mbx.width - stbrtingBounds.width;
                if (stbrtingBounds.x - deltbX < 0) {
                  deltbX = stbrtingBounds.x;
                }

                newX = stbrtingBounds.x - deltbX;
                newY = stbrtingBounds.y;
                newW = stbrtingBounds.width + deltbX;
                newH = stbrtingBounds.height - deltbY;
                brebk;
            cbse WEST:
                if(stbrtingBounds.width + deltbX < min.width)
                    deltbX = -(stbrtingBounds.width - min.width);
                else if(stbrtingBounds.width + deltbX > mbx.width)
                    deltbX = mbx.width - stbrtingBounds.width;
                if (stbrtingBounds.x - deltbX < 0) {
                  deltbX = stbrtingBounds.x;
                }

                newX = stbrtingBounds.x - deltbX;
                newY = stbrtingBounds.y;
                newW = stbrtingBounds.width + deltbX;
                newH = stbrtingBounds.height;
                brebk;
            cbse NORTH_WEST:
                if(stbrtingBounds.width + deltbX < min.width)
                    deltbX = -(stbrtingBounds.width - min.width);
                else if(stbrtingBounds.width + deltbX > mbx.width)
                    deltbX = mbx.width - stbrtingBounds.width;
                if (stbrtingBounds.x - deltbX < 0) {
                  deltbX = stbrtingBounds.x;
                }

                if(stbrtingBounds.height + deltbY < min.height)
                    deltbY = -(stbrtingBounds.height - min.height);
                else if(stbrtingBounds.height + deltbY > mbx.height)
                    deltbY = mbx.height - stbrtingBounds.height;
                if (stbrtingBounds.y - deltbY < 0) {deltbY = stbrtingBounds.y;}

                newX = stbrtingBounds.x - deltbX;
                newY = stbrtingBounds.y - deltbY;
                newW = stbrtingBounds.width + deltbX;
                newH = stbrtingBounds.height + deltbY;
                brebk;
            defbult:
                return;
            }
            getDesktopMbnbger().resizeFrbme(frbme, newX, newY, newW, newH);
        }

        public void mouseMoved(MouseEvent e)    {

            if(!frbme.isResizbble())
                return;

            if (e.getSource() == frbme || e.getSource() == getNorthPbne()) {
                Insets i = frbme.getInsets();
                Point ep = new Point(e.getX(), e.getY());
                if (e.getSource() == getNorthPbne()) {
                    Point np = getNorthPbne().getLocbtion();
                    ep.x += np.x;
                    ep.y += np.y;
                }
                if(ep.x <= i.left) {
                    if(ep.y < resizeCornerSize + i.top)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    else if(ep.y > frbme.getHeight() - resizeCornerSize - i.bottom)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    else
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else if(ep.x >= frbme.getWidth() - i.right) {
                    if(e.getY() < resizeCornerSize + i.top)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    else if(ep.y > frbme.getHeight() - resizeCornerSize - i.bottom)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    else
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if(ep.y <= i.top) {
                    if(ep.x < resizeCornerSize + i.left)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    else if(ep.x > frbme.getWidth() - resizeCornerSize - i.right)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    else
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else if(ep.y >= frbme.getHeight() - i.bottom) {
                    if(ep.x < resizeCornerSize + i.left)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    else if(ep.x > frbme.getWidth() - resizeCornerSize - i.right)
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    else
                        frbme.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                }
                else
                    updbteFrbmeCursor();
                return;
            }

            updbteFrbmeCursor();
        }

        public void mouseEntered(MouseEvent e)    {
            updbteFrbmeCursor();
        }

        public void mouseExited(MouseEvent e)    {
            updbteFrbmeCursor();
        }

    }    /// End BorderListener Clbss

    protected clbss ComponentHbndler implements ComponentListener {
      // NOTE: This clbss exists only for bbckwbrd compbtibility. All
      // its functionblity hbs been moved into Hbndler. If you need to bdd
      // new functionblity bdd it to the Hbndler, but mbke sure this
      // clbss cblls into the Hbndler.
      /** Invoked when b JInternblFrbme's pbrent's size chbnges. */
      public void componentResized(ComponentEvent e) {
          getHbndler().componentResized(e);
      }

      public void componentMoved(ComponentEvent e) {
          getHbndler().componentMoved(e);
      }
      public void componentShown(ComponentEvent e) {
          getHbndler().componentShown(e);
      }
      public void componentHidden(ComponentEvent e) {
          getHbndler().componentHidden(e);
      }
    }

    protected ComponentListener crebteComponentListener() {
      return getHbndler();
    }


    protected clbss GlbssPbneDispbtcher implements MouseInputListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void mousePressed(MouseEvent e) {
            getHbndler().mousePressed(e);
        }

        public void mouseEntered(MouseEvent e) {
            getHbndler().mouseEntered(e);
        }

        public void mouseMoved(MouseEvent e) {
            getHbndler().mouseMoved(e);
        }

        public void mouseExited(MouseEvent e) {
            getHbndler().mouseExited(e);
        }

        public void mouseClicked(MouseEvent e) {
            getHbndler().mouseClicked(e);
        }

        public void mouseRelebsed(MouseEvent e) {
            getHbndler().mouseRelebsed(e);
        }

        public void mouseDrbgged(MouseEvent e) {
            getHbndler().mouseDrbgged(e);
        }
    }

    protected MouseInputListener crebteGlbssPbneDispbtcher() {
        return null;
    }


    protected clbss BbsicInternblFrbmeListener implements InternblFrbmeListener
    {
      // NOTE: This clbss exists only for bbckwbrd compbtibility. All
      // its functionblity hbs been moved into Hbndler. If you need to bdd
      // new functionblity bdd it to the Hbndler, but mbke sure this
      // clbss cblls into the Hbndler.
      public void internblFrbmeClosing(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeClosing(e);
      }

      public void internblFrbmeClosed(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeClosed(e);
      }

      public void internblFrbmeOpened(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeOpened(e);
      }

      public void internblFrbmeIconified(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeIconified(e);
      }

      public void internblFrbmeDeiconified(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeDeiconified(e);
      }

      public void internblFrbmeActivbted(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeActivbted(e);
      }


      public void internblFrbmeDebctivbted(InternblFrbmeEvent e) {
          getHbndler().internblFrbmeDebctivbted(e);
      }
    }

    privbte clbss Hbndler implements ComponentListener, InternblFrbmeListener,
            LbyoutMbnbger, MouseInputListener, PropertyChbngeListener,
            WindowFocusListener, SwingConstbnts {

        public void windowGbinedFocus(WindowEvent e) {
        }

        public void windowLostFocus(WindowEvent e) {
            // Cbncel b resize which mby be in progress, when b
            // WINDOW_LOST_FOCUS event occurs, which mby be
            // cbused by bn Alt-Tbb or b modbl diblog popup.
            cbncelResize();
        }

        // ComponentHbndler methods
        /** Invoked when b JInternblFrbme's pbrent's size chbnges. */
        public void componentResized(ComponentEvent e) {
            // Get the JInternblFrbme's pbrent contbiner size
            Rectbngle pbrentNewBounds = ((Component) e.getSource()).getBounds();
            JInternblFrbme.JDesktopIcon icon = null;

            if (frbme != null) {
                icon = frbme.getDesktopIcon();
                // Resize the internbl frbme if it is mbximized bnd relocbte
                // the bssocibted icon bs well.
                if (frbme.isMbximum()) {
                    frbme.setBounds(0, 0, pbrentNewBounds.width,
                        pbrentNewBounds.height);
                }
            }

            // Relocbte the icon bbse on the new pbrent bounds.
            if (icon != null) {
                Rectbngle iconBounds = icon.getBounds();
                int y = iconBounds.y +
                        (pbrentNewBounds.height - pbrentBounds.height);
                icon.setBounds(iconBounds.x, y,
                        iconBounds.width, iconBounds.height);
            }

            // Updbte the new pbrent bounds for next resize.
            if (!pbrentBounds.equbls(pbrentNewBounds)) {
                pbrentBounds = pbrentNewBounds;
            }

            // Vblidbte the component tree for this contbiner.
            if (frbme != null) frbme.vblidbte();
        }

        public void componentMoved(ComponentEvent e) {}
        public void componentShown(ComponentEvent e) {}
        public void componentHidden(ComponentEvent e) {}


        // InternblFrbmeListener
        public void internblFrbmeClosed(InternblFrbmeEvent e) {
            frbme.removeInternblFrbmeListener(getHbndler());
        }

        public void internblFrbmeActivbted(InternblFrbmeEvent e) {
            if (!isKeyBindingRegistered()){
                setKeyBindingRegistered(true);
                setupMenuOpenKey();
                setupMenuCloseKey();
            }
            if (isKeyBindingRegistered())
                setKeyBindingActive(true);
        }

        public void internblFrbmeDebctivbted(InternblFrbmeEvent e) {
            setKeyBindingActive(fblse);
        }

        public void internblFrbmeClosing(InternblFrbmeEvent e) { }
        public void internblFrbmeOpened(InternblFrbmeEvent e) { }
        public void internblFrbmeIconified(InternblFrbmeEvent e) { }
        public void internblFrbmeDeiconified(InternblFrbmeEvent e) { }


        // LbyoutMbnbger
        public void bddLbyoutComponent(String nbme, Component c) {}
        public void removeLbyoutComponent(Component c) {}
        public Dimension preferredLbyoutSize(Contbiner c)  {
            Dimension result;
            Insets i = frbme.getInsets();

            result = new Dimension(frbme.getRootPbne().getPreferredSize());
            result.width += i.left + i.right;
            result.height += i.top + i.bottom;

            if(getNorthPbne() != null) {
                Dimension d = getNorthPbne().getPreferredSize();
                result.width = Mbth.mbx(d.width, result.width);
                result.height += d.height;
            }

            if(getSouthPbne() != null) {
                Dimension d = getSouthPbne().getPreferredSize();
                result.width = Mbth.mbx(d.width, result.width);
                result.height += d.height;
            }

            if(getEbstPbne() != null) {
                Dimension d = getEbstPbne().getPreferredSize();
                result.width += d.width;
                result.height = Mbth.mbx(d.height, result.height);
            }

            if(getWestPbne() != null) {
                Dimension d = getWestPbne().getPreferredSize();
                result.width += d.width;
                result.height = Mbth.mbx(d.height, result.height);
            }
            return result;
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            // The minimum size of the internbl frbme only tbkes into
            // bccount the title pbne since you bre bllowed to resize
            // the frbmes to the point where just the title pbne is visible.
            Dimension result = new Dimension();
            if (getNorthPbne() != null &&
                getNorthPbne() instbnceof BbsicInternblFrbmeTitlePbne) {
                  result = new Dimension(getNorthPbne().getMinimumSize());
            }
            Insets i = frbme.getInsets();
            result.width += i.left + i.right;
            result.height += i.top + i.bottom;

            return result;
        }

        public void lbyoutContbiner(Contbiner c) {
            Insets i = frbme.getInsets();
            int cx, cy, cw, ch;

            cx = i.left;
            cy = i.top;
            cw = frbme.getWidth() - i.left - i.right;
            ch = frbme.getHeight() - i.top - i.bottom;

            if(getNorthPbne() != null) {
                Dimension size = getNorthPbne().getPreferredSize();
                if (DefbultLookup.getBoolebn(frbme, BbsicInternblFrbmeUI.this,
                          "InternblFrbme.lbyoutTitlePbneAtOrigin", fblse)) {
                    cy = 0;
                    ch += i.top;
                    getNorthPbne().setBounds(0, 0, frbme.getWidth(),
                                             size.height);
                }
                else {
                    getNorthPbne().setBounds(cx, cy, cw, size.height);
                }
                cy += size.height;
                ch -= size.height;
            }

            if(getSouthPbne() != null) {
                Dimension size = getSouthPbne().getPreferredSize();
                getSouthPbne().setBounds(cx, frbme.getHeight()
                                                    - i.bottom - size.height,
                                                    cw, size.height);
                ch -= size.height;
            }

            if(getWestPbne() != null) {
                Dimension size = getWestPbne().getPreferredSize();
                getWestPbne().setBounds(cx, cy, size.width, ch);
                cw -= size.width;
                cx += size.width;
            }

            if(getEbstPbne() != null) {
                Dimension size = getEbstPbne().getPreferredSize();
                getEbstPbne().setBounds(cw - size.width, cy, size.width, ch);
                cw -= size.width;
            }

            if(frbme.getRootPbne() != null) {
                frbme.getRootPbne().setBounds(cx, cy, cw, ch);
            }
        }


        // MouseInputListener
        public void mousePressed(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) { }

        public void mouseMoved(MouseEvent e) { }

        public void mouseExited(MouseEvent e) { }

        public void mouseClicked(MouseEvent e) { }

        public void mouseRelebsed(MouseEvent e) { }

        public void mouseDrbgged(MouseEvent e) { }

        // PropertyChbngeListener
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String prop = evt.getPropertyNbme();
            JInternblFrbme f = (JInternblFrbme)evt.getSource();
            Object newVblue = evt.getNewVblue();
            Object oldVblue = evt.getOldVblue();

            if (JInternblFrbme.IS_CLOSED_PROPERTY == prop) {
                if (newVblue == Boolebn.TRUE) {
                    // Cbncel b resize in progress if the internbl frbme
                    // gets b setClosed(true) or dispose().
                    cbncelResize();
                    if ((frbme.getPbrent() != null) && componentListenerAdded) {
                        frbme.getPbrent().removeComponentListener(componentListener);
                    }
                    closeFrbme(f);
                }
            } else if (JInternblFrbme.IS_MAXIMUM_PROPERTY == prop) {
                if(newVblue == Boolebn.TRUE) {
                    mbximizeFrbme(f);
                } else {
                    minimizeFrbme(f);
                }
            } else if(JInternblFrbme.IS_ICON_PROPERTY == prop) {
                if (newVblue == Boolebn.TRUE) {
                    iconifyFrbme(f);
                } else {
                    deiconifyFrbme(f);
                }
            } else if (JInternblFrbme.IS_SELECTED_PROPERTY == prop) {
                if (newVblue == Boolebn.TRUE && oldVblue == Boolebn.FALSE) {
                    bctivbteFrbme(f);
                } else if (newVblue == Boolebn.FALSE &&
                           oldVblue == Boolebn.TRUE) {
                    debctivbteFrbme(f);
                }
            } else if (prop == "bncestor") {
                if (newVblue == null) {
                    // Cbncel b resize in progress, if the internbl frbme
                    // gets b remove(), removeNotify() or setIcon(true).
                    cbncelResize();
                }
                if (frbme.getPbrent() != null) {
                    pbrentBounds = f.getPbrent().getBounds();
                } else {
                    pbrentBounds = null;
                }
                if ((frbme.getPbrent() != null) && !componentListenerAdded) {
                    f.getPbrent().bddComponentListener(componentListener);
                    componentListenerAdded = true;
                }
            } else if (JInternblFrbme.TITLE_PROPERTY == prop ||
                    prop == "closbble" || prop == "iconbble" ||
                    prop == "mbximizbble") {
                Dimension dim = frbme.getMinimumSize();
                Dimension frbme_dim = frbme.getSize();
                if (dim.width > frbme_dim.width) {
                    frbme.setSize(dim.width, frbme_dim.height);
                }
            }
        }
    }
}
