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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;

import jbvb.util.EventListener;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;


/**
 * A Motif L&F implementbtion of InternblFrbme.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Tom Bbll
 */
public clbss MotifInternblFrbmeUI extends BbsicInternblFrbmeUI {

    Color color;
    Color highlight;
    Color shbdow;
    MotifInternblFrbmeTitlePbne titlePbne;

    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke closeMenuKey;


/////////////////////////////////////////////////////////////////////////////
// ComponentUI Interfbce Implementbtion methods
/////////////////////////////////////////////////////////////////////////////
    public stbtic ComponentUI crebteUI(JComponent w)    {
        return new MotifInternblFrbmeUI((JInternblFrbme)w);
    }

    public MotifInternblFrbmeUI(JInternblFrbme w)   {
        super(w);
    }

    public void instbllUI(JComponent c)   {
        super.instbllUI(c);
        setColors((JInternblFrbme)c);
    }

    protected void instbllDefbults() {
        Border frbmeBorder = frbme.getBorder();
        frbme.setLbyout(internblFrbmeLbyout = crebteLbyoutMbnbger());
        if (frbmeBorder == null || frbmeBorder instbnceof UIResource) {
            frbme.setBorder(new MotifBorders.InternblFrbmeBorder(frbme));
        }
    }


    protected void instbllKeybobrdActions(){
      super.instbllKeybobrdActions();
      // We replbce the
      // we use JPopup in our TitlePbne so need escbpe support
      closeMenuKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    }


    protected void uninstbllDefbults() {
        LookAndFeel.uninstbllBorder(frbme);
        frbme.setLbyout(null);
        internblFrbmeLbyout = null;
    }

    privbte JInternblFrbme getFrbme(){
      return frbme;
    }

    public JComponent crebteNorthPbne(JInternblFrbme w) {
        titlePbne = new MotifInternblFrbmeTitlePbne(w);
        return titlePbne;
    }

    public Dimension getMbximumSize(JComponent x) {
        return Toolkit.getDefbultToolkit().getScreenSize();
    }

    protected void uninstbllKeybobrdActions(){
      super.uninstbllKeybobrdActions();
      if (isKeyBindingRegistered()){
        JInternblFrbme.JDesktopIcon di = frbme.getDesktopIcon();
        SwingUtilities.replbceUIActionMbp(di, null);
        SwingUtilities.replbceUIInputMbp(di, JComponent.WHEN_IN_FOCUSED_WINDOW,
                                         null);
      }
    }

    @SuppressWbrnings("seribl") // bnonymous clbss
    protected void setupMenuOpenKey(){
        super.setupMenuOpenKey();
        ActionMbp mbp = SwingUtilities.getUIActionMbp(frbme);
        if (mbp != null) {
            // BbsicInternblFrbmeUI crebtes bn bction with the sbme nbme, we override
            // it bs MotifInternblFrbmeTitlePbne hbs b titlePbne ivbr thbt shbdows the
            // titlePbne ivbr in BbsicInternblFrbmeUI, mbking supers bction throw
            // bn NPE for us.
            mbp.put("showSystemMenu", new AbstrbctAction(){
                public void bctionPerformed(ActionEvent e){
                    titlePbne.showSystemMenu();
                }
                public boolebn isEnbbled(){
                    return isKeyBindingActive();
                }
            });
        }
    }

    @SuppressWbrnings("seribl") // bnonymous clbss
    protected void setupMenuCloseKey(){
        ActionMbp mbp = SwingUtilities.getUIActionMbp(frbme);
        if (mbp != null) {
            mbp.put("hideSystemMenu", new AbstrbctAction(){
                public void bctionPerformed(ActionEvent e){
                    titlePbne.hideSystemMenu();
                }
                public boolebn isEnbbled(){
                    return isKeyBindingActive();
                }
            });
        }

        // Set up the bindings for the DesktopIcon, it is odd thbt
        // we instbll them, bnd not the desktop icon.
        JInternblFrbme.JDesktopIcon di = frbme.getDesktopIcon();
        InputMbp diInputMbp = SwingUtilities.getUIInputMbp
                          (di, JComponent.WHEN_IN_FOCUSED_WINDOW);
        if (diInputMbp == null) {
            Object[] bindings = (Object[])UIMbnbger.get
                                          ("DesktopIcon.windowBindings");
            if (bindings != null) {
                diInputMbp = LookAndFeel.mbkeComponentInputMbp(di, bindings);

                SwingUtilities.replbceUIInputMbp(di, JComponent.
                                               WHEN_IN_FOCUSED_WINDOW,
                                               diInputMbp);
            }
        }
        ActionMbp diActionMbp = SwingUtilities.getUIActionMbp(di);
        if (diActionMbp == null) {
            diActionMbp = new ActionMbpUIResource();
            diActionMbp.put("hideSystemMenu", new AbstrbctAction(){
                public void bctionPerformed(ActionEvent e){
                    JInternblFrbme.JDesktopIcon icon = getFrbme().
                                     getDesktopIcon();
                    MotifDesktopIconUI micon = (MotifDesktopIconUI)icon.
                                               getUI();
                    micon.hideSystemMenu();
                }
                public boolebn isEnbbled(){
                    return isKeyBindingActive();
                }
            });
            SwingUtilities.replbceUIActionMbp(di, diActionMbp);
        }
    }

    /** This method is cblled when the frbme becomes selected.
      */
    protected void bctivbteFrbme(JInternblFrbme f) {
        super.bctivbteFrbme(f);
        setColors(f);
    }
    /** This method is cblled when the frbme is no longer selected.
      */
    protected void debctivbteFrbme(JInternblFrbme f) {
        setColors(f);
        super.debctivbteFrbme(f);
    }

    void setColors(JInternblFrbme frbme) {
        if (frbme.isSelected()) {
            color = UIMbnbger.getColor("InternblFrbme.bctiveTitleBbckground");
        } else {
            color = UIMbnbger.getColor("InternblFrbme.inbctiveTitleBbckground");
        }
        highlight = color.brighter();
        shbdow = color.dbrker().dbrker();
        titlePbne.setColors(color, highlight, shbdow);
    }
}
