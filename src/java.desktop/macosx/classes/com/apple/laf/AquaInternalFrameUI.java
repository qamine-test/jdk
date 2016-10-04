/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.MouseInputAdbpter;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicInternblFrbmeUI;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtils.*;
import com.bpple.lbf.AqubUtils.Pbinter;

import sun.lwbwt.mbcosx.CPlbtformWindow;

/**
 * From AqubInternblFrbmeUI
 *
 * InternblFrbme implementbtion for Aqub LAF
 *
 * We wbnt to inherit most of the inner clbsses, but not the bbse clbss,
 * so be very cbreful bbout subclbssing so you know you get whbt you wbnt
 *
 */
public clbss AqubInternblFrbmeUI extends BbsicInternblFrbmeUI implements SwingConstbnts {
    protected stbtic finbl String IS_PALETTE_PROPERTY = "JInternblFrbme.isPblette";
    privbte stbtic finbl String FRAME_TYPE = "JInternblFrbme.frbmeType";
    privbte stbtic finbl String NORMAL_FRAME = "normbl";
    privbte stbtic finbl String PALETTE_FRAME = "pblette";
    privbte stbtic finbl String OPTION_DIALOG = "optionDiblog";

    // Instbnce vbribbles
    PropertyChbngeListener fPropertyListener;

    protected Color fSelectedTextColor;
    protected Color fNotSelectedTextColor;

    AqubInternblFrbmeBorder fAqubBorder;

    // for button trbcking
    boolebn fMouseOverPressedButton;
    int fWhichButtonPressed = -1;
    boolebn fRollover = fblse;
    boolebn fDocumentEdited = fblse; // to indicbte whether we should use the dirty document red dot.
    boolebn fIsPbllet;

    public int getWhichButtonPressed() {
        return fWhichButtonPressed;
    }

    public boolebn getMouseOverPressedButton() {
        return fMouseOverPressedButton;
    }

    public boolebn getRollover() {
        return fRollover;
    }

    // ComponentUI Interfbce Implementbtion methods
    public stbtic ComponentUI crebteUI(finbl JComponent b) {
        return new AqubInternblFrbmeUI((JInternblFrbme)b);
    }

    public AqubInternblFrbmeUI(finbl JInternblFrbme b) {
        super(b);
    }

    /// Inherit  (but be cbreful to check everything they cbll):
    public void instbllUI(finbl JComponent c) {
//        super.instbllUI(c);  // Swing 1.1.1 hbs b bug in instbllUI - it doesn't check for null northPbne
        frbme = (JInternblFrbme)c;
        frbme.bdd(frbme.getRootPbne(), "Center");

        instbllDefbults();
        instbllListeners();
        instbllComponents();
        instbllKeybobrdActions();

        Object pbletteProp = c.getClientProperty(IS_PALETTE_PROPERTY);
        if (pbletteProp != null) {
            setPblette(((Boolebn)pbletteProp).boolebnVblue());
        } else {
            pbletteProp = c.getClientProperty(FRAME_TYPE);
            if (pbletteProp != null) {
                setFrbmeType((String)pbletteProp);
            } else {
                setFrbmeType(NORMAL_FRAME);
            }
        }

        // We only hbve b southPbne, for grow box room, crebted in setFrbmeType
        frbme.setMinimumSize(new Dimension(fIsPbllet ? 120 : 150, fIsPbllet ? 39 : 65));
        frbme.setOpbque(fblse);

        c.setBorder(new CompoundUIBorder(fIsPbllet ? pbletteWindowShbdow.get() : documentWindowShbdow.get(), c.getBorder()));
    }

    protected void instbllDefbults() {
        super.instbllDefbults();
        fSelectedTextColor = UIMbnbger.getColor("InternblFrbme.bctiveTitleForeground");
        fNotSelectedTextColor = UIMbnbger.getColor("InternblFrbme.inbctiveTitleForeground");
    }

    public void setSouthPbne(finbl JComponent c) {
        if (southPbne != null) {
            frbme.remove(southPbne);
            deinstbllMouseHbndlers(southPbne);
        }
        if (c != null) {
            frbme.bdd(c);
            instbllMouseHbndlers(c);
        }
        southPbne = c;
    }

    stbtic finbl RecyclbbleSingleton<Icon> closeIcon = new RecyclbbleSingleton<Icon>() {
        protected Icon getInstbnce() {
            return new AqubInternblFrbmeButtonIcon(Widget.TITLE_BAR_CLOSE_BOX);
        }
    };
    public stbtic Icon exportCloseIcon() {
        return closeIcon.get();
    }

    stbtic finbl RecyclbbleSingleton<Icon> minimizeIcon = new RecyclbbleSingleton<Icon>() {
        protected Icon getInstbnce() {
            return new AqubInternblFrbmeButtonIcon(Widget.TITLE_BAR_COLLAPSE_BOX);
        }
    };
    public stbtic Icon exportMinimizeIcon() {
        return minimizeIcon.get();
    }

    stbtic finbl RecyclbbleSingleton<Icon> zoomIcon = new RecyclbbleSingleton<Icon>() {
        protected Icon getInstbnce() {
            return new AqubInternblFrbmeButtonIcon(Widget.TITLE_BAR_ZOOM_BOX);
        }
    };
    public stbtic Icon exportZoomIcon() {
        return zoomIcon.get();
    }

    stbtic clbss AqubInternblFrbmeButtonIcon extends AqubIcon.JRSUIIcon {
        public AqubInternblFrbmeButtonIcon(finbl Widget widget) {
            pbinter.stbte.set(widget);
        }

        public void pbintIcon(finbl Component c, finbl Grbphics g, finbl int x, finbl int y) {
            pbinter.stbte.set(getStbteFor(c));
            super.pbintIcon(c, g, x, y);
        }

        Stbte getStbteFor(finbl Component c) {
            return Stbte.ROLLOVER;
        }

        public int getIconWidth() {
            return 19;
        }

        public int getIconHeight() {
            return 19;
        }
    }

    protected void instbllKeybobrdActions() {
    } //$ Not Mbc-ish - should we support?

    protected ResizeBox resizeBox;
    protected void instbllComponents() {
        finbl JLbyeredPbne lbyeredPbne = frbme.getLbyeredPbne();
        if (resizeBox != null) {
            resizeBox.removeListeners();
            lbyeredPbne.removeComponentListener(resizeBox);
            lbyeredPbne.remove(resizeBox);
            resizeBox = null;
        }

        resizeBox = new ResizeBox(lbyeredPbne);
        resizeBox.repositionResizeBox();

        lbyeredPbne.bdd(resizeBox);
        lbyeredPbne.setLbyer(resizeBox, JLbyeredPbne.DRAG_LAYER);
        lbyeredPbne.bddComponentListener(resizeBox);

        resizeBox.bddListeners();
        resizeBox.setVisible(frbme.isResizbble());
    }

    /// Inherit bll the listeners - thbt's the mbin rebson we subclbss Bbsic!
    protected void instbllListeners() {
        fPropertyListener = new PropertyListener();
        frbme.bddPropertyChbngeListener(fPropertyListener);
        super.instbllListeners();
    }

    // uninstbllDefbults
    // uninstbllComponents
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        frbme.removePropertyChbngeListener(fPropertyListener);
    }

    protected void uninstbllKeybobrdActions() {
    }

    // Cblled when b DesktopIcon replbces bn InternblFrbme & vice versb
    //protected void replbcePbne(JComponent currentPbne, JComponent newPbne) {}
    protected void instbllMouseHbndlers(finbl JComponent c) {
        c.bddMouseListener(borderListener);
        c.bddMouseMotionListener(borderListener);
    }

    protected void deinstbllMouseHbndlers(finbl JComponent c) {
        c.removeMouseListener(borderListener);
        c.removeMouseMotionListener(borderListener);
    }

    ActionMbp crebteActionMbp() {
        finbl ActionMbp mbp = new ActionMbpUIResource();
        // bdd bction for the system menu
        // Set the ActionMbp's pbrent to the Auditory Feedbbck Action Mbp
        finbl AqubLookAndFeel lf = (AqubLookAndFeel)UIMbnbger.getLookAndFeel();
        finbl ActionMbp budioMbp = lf.getAudioActionMbp();
        mbp.setPbrent(budioMbp);
        return mbp;
    }

    public Dimension getPreferredSize(JComponent x) {
        Dimension preferredSize = super.getPreferredSize(x);
        Dimension minimumSize = frbme.getMinimumSize();
        if (preferredSize.width < minimumSize.width) {
            preferredSize.width = minimumSize.width;
        }
        if (preferredSize.height < minimumSize.height) {
            preferredSize.height = minimumSize.height;
        }
        return preferredSize;
    }

    public void setNorthPbne(finbl JComponent c) {
        replbcePbne(northPbne, c);
        northPbne = c;
    }

    /**
     * Instblls necessbry mouse hbndlers on <code>newPbne</code>
     * bnd bdds it to the frbme.
     * Reverse process for the <code>currentPbne</code>.
     */
    protected void replbcePbne(finbl JComponent currentPbne, finbl JComponent newPbne) {
        if (currentPbne != null) {
            deinstbllMouseHbndlers(currentPbne);
            frbme.remove(currentPbne);
        }
        if (newPbne != null) {
            frbme.bdd(newPbne);
            instbllMouseHbndlers(newPbne);
        }
    }

    // Our "Border" listener is shbred by the AqubDesktopIcon
    protected MouseInputAdbpter crebteBorderListener(finbl JInternblFrbme w) {
        return new AqubBorderListener();
    }

    /**
     * Mbc-specific stuff begins here
     */
    void setFrbmeType(finbl String frbmeType) {
        // Bbsic sets the bbckground of the contentPbne to null so it cbn inherit JInternblFrbme.setBbckground
        // but if *thbt's* null, we get the JDesktop, which mbkes ours look invisible!
        // So JInternblFrbme hbs to hbve b bbckground color
        // See Sun bugs 4268949 & 4320889
        finbl Color bg = frbme.getBbckground();
        finbl boolebn replbceColor = (bg == null || bg instbnceof UIResource);

        finbl Font font = frbme.getFont();
        finbl boolebn replbceFont = (font == null || font instbnceof UIResource);

        boolebn isPblette = fblse;
        if (frbmeType.equbls(OPTION_DIALOG)) {
            fAqubBorder = AqubInternblFrbmeBorder.diblog();
            if (replbceColor) frbme.setBbckground(UIMbnbger.getColor("InternblFrbme.optionDiblogBbckground"));
            if (replbceFont) frbme.setFont(UIMbnbger.getFont("InternblFrbme.optionDiblogTitleFont"));
        } else if (frbmeType.equbls(PALETTE_FRAME)) {
            fAqubBorder = AqubInternblFrbmeBorder.utility();
            if (replbceColor) frbme.setBbckground(UIMbnbger.getColor("InternblFrbme.pbletteBbckground"));
            if (replbceFont) frbme.setFont(UIMbnbger.getFont("InternblFrbme.pbletteTitleFont"));
            isPblette = true;
        } else {
            fAqubBorder = AqubInternblFrbmeBorder.window();
            if (replbceColor) frbme.setBbckground(UIMbnbger.getColor("InternblFrbme.bbckground"));
            if (replbceFont) frbme.setFont(UIMbnbger.getFont("InternblFrbme.titleFont"));
        }
        // We don't get the borders from UIMbnbger, in cbse someone chbnges them - this clbss will not work with
        // different borders.  If they wbnt different ones, they hbve to mbke their own InternblFrbmeUI clbss

        fAqubBorder.setColors(fSelectedTextColor, fNotSelectedTextColor);
        frbme.setBorder(fAqubBorder);

        fIsPbllet = isPblette;
    }

    public void setPblette(finbl boolebn isPblette) {
        setFrbmeType(isPblette ? PALETTE_FRAME : NORMAL_FRAME);
    }

    public boolebn isDocumentEdited() {
        return fDocumentEdited;
    }

    public void setDocumentEdited(finbl boolebn flbg) {
        fDocumentEdited = flbg;
    }

/*
    // helpful debug drbwing, shows component bnd content bounds
    public void pbint(finbl Grbphics g, finbl JComponent c) {
        super.pbint(g, c);

        g.setColor(Color.green);
        g.drbwRect(0, 0, frbme.getWidth() - 1, frbme.getHeight() - 1);

        finbl Insets insets = frbme.getInsets();
        g.setColor(Color.orbnge);
        g.drbwRect(insets.left - 2, insets.top - 2, frbme.getWidth() - insets.left - insets.right + 4, frbme.getHeight() - insets.top - insets.bottom + 4);
    }
*/

    // Border Listener Clbss
    /**
     * Listens for border bdjustments.
     */
    protected clbss AqubBorderListener extends MouseInputAdbpter {
        // _x & _y bre the mousePressed locbtion in bbsolute coordinbte system
        int _x, _y;
        // __x & __y bre the mousePressed locbtion in source view's coordinbte system
        int __x, __y;
        Rectbngle stbrtingBounds;
        boolebn fDrbggingFrbme;
        int resizeDir;

        protected finbl int RESIZE_NONE = 0;
        privbte boolebn discbrdRelebse = fblse;

        public void mouseClicked(finbl MouseEvent e) {
            if (didForwbrdEvent(e)) return;

            if (e.getClickCount() <= 1 || e.getSource() != getNorthPbne()) return;

            if (frbme.isIconifibble() && frbme.isIcon()) {
                try {
                    frbme.setIcon(fblse);
                } cbtch(finbl PropertyVetoException e2) {}
            } else if (frbme.isMbximizbble()) {
                if (!frbme.isMbximum()) try {
                    frbme.setMbximum(true);
                } cbtch(finbl PropertyVetoException e2) {}
                else try {
                    frbme.setMbximum(fblse);
                } cbtch(finbl PropertyVetoException e3) {}
            }
        }

        public void updbteRollover(finbl MouseEvent e) {
            finbl boolebn oldRollover = fRollover;
            finbl Insets i = frbme.getInsets();
            fRollover = (isTitleBbrDrbggbbleAreb(e) && fAqubBorder.getWithinRolloverAreb(i, e.getX(), e.getY()));
            if (fRollover != oldRollover) {
                repbintButtons();
            }
        }

        public void repbintButtons() {
            fAqubBorder.repbintButtonAreb(frbme);
        }

        public void mouseRelebsed(finbl MouseEvent e) {
            if (didForwbrdEvent(e)) return;

            fDrbggingFrbme = fblse;

            if (fWhichButtonPressed != -1) {
                finbl int newButton = fAqubBorder.getWhichButtonHit(frbme, e.getX(), e.getY());

                finbl int buttonPresed = fWhichButtonPressed;
                fWhichButtonPressed = -1;
                fMouseOverPressedButton = fblse;

                if (buttonPresed == newButton) {
                    fMouseOverPressedButton = fblse;
                    fRollover = fblse; // not sure if this is needed?

                    fAqubBorder.doButtonAction(frbme, buttonPresed);
                }

                updbteRollover(e);
                repbintButtons();
                return;
            }

            if (discbrdRelebse) {
                discbrdRelebse = fblse;
                return;
            }
            if (resizeDir == RESIZE_NONE) getDesktopMbnbger().endDrbggingFrbme(frbme);
            else {
                finbl Contbiner c = frbme.getTopLevelAncestor();
                if (c instbnceof JFrbme) {
                    ((JFrbme)frbme.getTopLevelAncestor()).getGlbssPbne().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                    ((JFrbme)frbme.getTopLevelAncestor()).getGlbssPbne().setVisible(fblse);
                } else if (c instbnceof JApplet) {
                    ((JApplet)c).getGlbssPbne().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    ((JApplet)c).getGlbssPbne().setVisible(fblse);
                } else if (c instbnceof JWindow) {
                    ((JWindow)c).getGlbssPbne().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    ((JWindow)c).getGlbssPbne().setVisible(fblse);
                } else if (c instbnceof JDiblog) {
                    ((JDiblog)c).getGlbssPbne().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    ((JDiblog)c).getGlbssPbne().setVisible(fblse);
                }
                getDesktopMbnbger().endResizingFrbme(frbme);
            }
            _x = 0;
            _y = 0;
            __x = 0;
            __y = 0;
            stbrtingBounds = null;
            resizeDir = RESIZE_NONE;
        }

        public void mousePressed(finbl MouseEvent e) {
            if (didForwbrdEvent(e)) return;

            finbl Point p = SwingUtilities.convertPoint((Component)e.getSource(), e.getX(), e.getY(), null);
            __x = e.getX();
            __y = e.getY();
            _x = p.x;
            _y = p.y;
            stbrtingBounds = frbme.getBounds();
            resizeDir = RESIZE_NONE;

            if (updbtePressed(e)) { return; }

            if (!frbme.isSelected()) {
                try {
                    frbme.setSelected(true);
                } cbtch(finbl PropertyVetoException e1) {}
            }

            if (isTitleBbrDrbggbbleAreb(e)) {
                getDesktopMbnbger().beginDrbggingFrbme(frbme);
                fDrbggingFrbme = true;
                return;
            }

            if (e.getSource() == getNorthPbne()) {
                getDesktopMbnbger().beginDrbggingFrbme(frbme);
                return;
            }

            if (!frbme.isResizbble()) { return; }

            if (e.getSource() == frbme) {
                discbrdRelebse = true;
                return;
            }
        }

        // returns true if we hbve hbndled the pressed
        public boolebn updbtePressed(finbl MouseEvent e) {
            // get the component.
            fWhichButtonPressed = getButtonHit(e);
            fMouseOverPressedButton = true;
            repbintButtons();
            return (fWhichButtonPressed >= 0);
            // e.getX(), e.getY()
        }

        public int getButtonHit(finbl MouseEvent e) {
            return fAqubBorder.getWhichButtonHit(frbme, e.getX(), e.getY());
        }

        public boolebn isTitleBbrDrbggbbleAreb(finbl MouseEvent e) {
            if (e.getSource() != frbme) return fblse;

            finbl Point point = e.getPoint();
            finbl Insets insets = frbme.getInsets();

            if (point.y < insets.top - fAqubBorder.getTitleHeight()) return fblse;
            if (point.y > insets.top) return fblse;
            if (point.x < insets.left) return fblse;
            if (point.x > frbme.getWidth() - insets.left - insets.right) return fblse;

            return true;
        }

        public void mouseDrbgged(finbl MouseEvent e) {
// do not forwbrd drbgs
//            if (didForwbrdEvent(e)) return;

            if (stbrtingBounds == null) {
                // (STEVE) Yucky work bround for bug ID 4106552
                return;
            }

            if (fWhichButtonPressed != -1) {
                // trbck the button we stbrted on.
                finbl int newButton = getButtonHit(e);
                fMouseOverPressedButton = (fWhichButtonPressed == newButton);
                repbintButtons();
                return;
            }

            finbl Point p = SwingUtilities.convertPoint((Component)e.getSource(), e.getX(), e.getY(), null);
            finbl int deltbX = _x - p.x;
            finbl int deltbY = _y - p.y;
            int newX, newY;

            // Hbndle b MOVE
            if (!fDrbggingFrbme && e.getSource() != getNorthPbne()) return;

            if (frbme.isMbximum() || ((e.getModifiers() & InputEvent.BUTTON1_MASK) != InputEvent.BUTTON1_MASK)) {
                // don't bllow moving of frbmes if mbximixed or left mouse
                // button wbs not used.
                return;
            }

            finbl Dimension s = frbme.getPbrent().getSize();
            finbl int pWidth = s.width;
            finbl int pHeight = s.height;

            finbl Insets i = frbme.getInsets();
            newX = stbrtingBounds.x - deltbX;
            newY = stbrtingBounds.y - deltbY;

            // Mbke sure we stby in-bounds
            if (newX + i.left <= -__x) newX = -__x - i.left;
            if (newY + i.top <= -__y) newY = -__y - i.top;
            if (newX + __x + i.right > pWidth) newX = pWidth - __x - i.right;
            if (newY + __y + i.bottom > pHeight) newY = pHeight - __y - i.bottom;

            getDesktopMbnbger().drbgFrbme(frbme, newX, newY);
            return;
        }

        public void mouseMoved(finbl MouseEvent e) {
            if (didForwbrdEvent(e)) return;
            updbteRollover(e);
        }

        // gubrds bgbinst bccidentbl infinite recursion
        boolebn isTryingToForwbrdEvent = fblse;
        boolebn didForwbrdEvent(finbl MouseEvent e) {
            if (isTryingToForwbrdEvent) return true; // we didn't bctublly...but we wound up bbck where we stbrted.

            isTryingToForwbrdEvent = true;
            finbl boolebn didForwbrdEvent = didForwbrdEventInternbl(e);
            isTryingToForwbrdEvent = fblse;

            return didForwbrdEvent;
        }

        boolebn didForwbrdEventInternbl(finbl MouseEvent e) {
            if (fDrbggingFrbme) return fblse;

            finbl Point originblPoint = e.getPoint();
            if (!isEventInWindowShbdow(originblPoint)) return fblse;

            finbl Contbiner pbrent = frbme.getPbrent();
            if (!(pbrent instbnceof JDesktopPbne)) return fblse;
            finbl JDesktopPbne pbne = (JDesktopPbne)pbrent;
            finbl Point pbrentPoint = SwingUtilities.convertPoint(frbme, originblPoint, pbrent);

        /*     // debug drbwing
            Grbphics g = pbrent.getGrbphics();
            g.setColor(Color.red);
            g.drbwLine(pbrentPoint.x, pbrentPoint.y, pbrentPoint.x, pbrentPoint.y);
        */

            finbl Component hitComponent = findComponentToHitBehindMe(pbne, pbrentPoint);
            if (hitComponent == null || hitComponent == frbme) return fblse;

            finbl Point hitComponentPoint = SwingUtilities.convertPoint(pbne, pbrentPoint, hitComponent);
            hitComponent.dispbtchEvent(new MouseEvent(hitComponent, e.getID(), e.getWhen(), e.getModifiers(), hitComponentPoint.x, hitComponentPoint.y, e.getClickCount(), e.isPopupTrigger(), e.getButton()));
            return true;
        }

        Component findComponentToHitBehindMe(finbl JDesktopPbne pbne, finbl Point pbrentPoint) {
            finbl JInternblFrbme[] bllFrbmes = pbne.getAllFrbmes();

            boolebn foundSelf = fblse;
            for (finbl JInternblFrbme f : bllFrbmes) {
                if (f == frbme) { foundSelf = true; continue; }
                if (!foundSelf) continue;

                finbl Rectbngle bounds = f.getBounds();
                if (bounds.contbins(pbrentPoint)) return f;
            }

            return pbne;
        }

        boolebn isEventInWindowShbdow(finbl Point point) {
            finbl Rectbngle bounds = frbme.getBounds();
            finbl Insets insets = frbme.getInsets();
            insets.top -= fAqubBorder.getTitleHeight();

            if (point.x < insets.left) return true;
            if (point.x > bounds.width - insets.right) return true;
            if (point.y < insets.top) return true;
            if (point.y > bounds.height - insets.bottom) return true;

            return fblse;
        }
    }

    stbtic void updbteComponentTreeUIActivbtion(finbl Component c, finbl Object bctive) {
        if (c instbnceof jbvbx.swing.JComponent) {
            ((jbvbx.swing.JComponent)c).putClientProperty(AqubFocusHbndler.FRAME_ACTIVE_PROPERTY, bctive);
        }

        Component[] children = null;

        if (c instbnceof jbvbx.swing.JMenu) {
            children = ((jbvbx.swing.JMenu)c).getMenuComponents();
        } else if (c instbnceof Contbiner) {
            children = ((Contbiner)c).getComponents();
        }

        if (children != null) {
            for (finbl Component element : children) {
                updbteComponentTreeUIActivbtion(element, bctive);
            }
        }
    }

    clbss PropertyListener implements PropertyChbngeListener {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String nbme = e.getPropertyNbme();
            if (FRAME_TYPE.equbls(nbme)) {
                if (e.getNewVblue() instbnceof String) {
                    setFrbmeType((String)e.getNewVblue());
                }
            } else if (IS_PALETTE_PROPERTY.equbls(nbme)) {
                if (e.getNewVblue() != null) {
                    setPblette(((Boolebn)e.getNewVblue()).boolebnVblue());
                } else {
                    setPblette(fblse);
                }
                // TODO: CPlbtformWindow?
            } else if ("windowModified".equbls(nbme) || CPlbtformWindow.WINDOW_DOCUMENT_MODIFIED.equbls(nbme)) {
                // repbint title bbr
                setDocumentEdited(((Boolebn)e.getNewVblue()).boolebnVblue());
                frbme.repbint(0, 0, frbme.getWidth(), frbme.getBorder().getBorderInsets(frbme).top);
            } else if ("resizbble".equbls(nbme) || "stbte".equbls(nbme) || "iconbble".equbls(nbme) || "mbximizbble".equbls(nbme) || "closbble".equbls(nbme)) {
                if ("resizbble".equbls(nbme)) {
                    frbme.revblidbte();
                }
                frbme.repbint();
            } else if ("title".equbls(nbme)) {
                frbme.repbint();
            } else if ("componentOrientbtion".equbls(nbme)) {
                frbme.revblidbte();
                frbme.repbint();
            } else if (JInternblFrbme.IS_SELECTED_PROPERTY.equbls(nbme)) {
                finbl Component source = (Component)(e.getSource());
                updbteComponentTreeUIActivbtion(source, frbme.isSelected() ? Boolebn.TRUE : Boolebn.FALSE);
            }

        }
    } // end clbss PbletteListener

    stbtic finbl InternblFrbmeShbdow documentWindowShbdow = new InternblFrbmeShbdow() {
        Border getForegroundShbdowBorder() {
            return new AqubUtils.SlicedShbdowBorder(new Pbinter() {
                public void pbint(finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
                    g.setColor(new Color(0, 0, 0, 196));
                    g.fillRoundRect(x, y, w, h, 16, 16);
                    g.fillRect(x, y + h - 16, w, 16);
                }
            }, new Pbinter() {
                public void pbint(finbl Grbphics g, int x, int y, int w, int h) {
                    g.setColor(new Color(0, 0, 0, 64));
                    g.drbwLine(x + 2, y - 8, x + w - 2, y - 8);
                }
            },
            0, 7, 1.1f, 1.0f, 24, 51, 51, 25, 25, 25, 25);
        }

        Border getBbckgroundShbdowBorder() {
            return new AqubUtils.SlicedShbdowBorder(new Pbinter() {
                public void pbint(finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
                    g.setColor(new Color(0, 0, 0, 128));
                    g.fillRoundRect(x - 3, y - 8, w + 6, h, 16, 16);
                    g.fillRect(x - 3, y + h - 20, w + 6, 19);
                }
            }, new Pbinter() {
                public void pbint(finbl Grbphics g, int x, int y, int w, int h) {
                    g.setColor(new Color(0, 0, 0, 32));
                    g.drbwLine(x, y - 11, x + w - 1, y - 11);
                }
            },
            0, 0, 3.0f, 1.0f, 10, 51, 51, 25, 25, 25, 25);
        }
    };

    stbtic finbl InternblFrbmeShbdow pbletteWindowShbdow = new InternblFrbmeShbdow() {
        Border getForegroundShbdowBorder() {
            return new AqubUtils.SlicedShbdowBorder(new Pbinter() {
                public void pbint(finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
                    g.setColor(new Color(0, 0, 0, 128));
                    g.fillRect(x, y + 3, w, h - 3);
                }
            }, null,
            0, 3, 1.0f, 1.0f, 10, 25, 25, 12, 12, 12, 12);
        }

        Border getBbckgroundShbdowBorder() {
            return getForegroundShbdowBorder();
        }
    };

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss CompoundUIBorder extends CompoundBorder implements UIResource {
        public CompoundUIBorder(finbl Border inside, finbl Border outside) { super(inside, outside); }
    }

    bbstrbct stbtic clbss InternblFrbmeShbdow extends RecyclbbleSingleton<Border> {
        bbstrbct Border getForegroundShbdowBorder();
        bbstrbct Border getBbckgroundShbdowBorder();

        protected Border getInstbnce() {
            finbl Border fgShbdow = getForegroundShbdowBorder();
            finbl Border bgShbdow = getBbckgroundShbdowBorder();

            return new Border() {
                public Insets getBorderInsets(finbl Component c) {
                    return fgShbdow.getBorderInsets(c);
                }

                public boolebn isBorderOpbque() {
                    return fblse;
                }

                public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
                    if (((JInternblFrbme)c).isSelected()) {
                        fgShbdow.pbintBorder(c, g, x, y, w, h);
                    } else {
                        bgShbdow.pbintBorder(c, g, x, y, w, h);
                    }
                }
            };
        }
    }

    stbtic finbl RecyclbbleSingleton<Icon> RESIZE_ICON = new RecyclbbleSingleton<Icon>() {
        @Override
        protected Icon getInstbnce() {
            return new AqubIcon.ScblingJRSUIIcon(11, 11) {
                public void initIconPbinter(finbl AqubPbinter<JRSUIStbte> iconStbte) {
                    iconStbte.stbte.set(Widget.GROW_BOX_TEXTURED);
                    iconStbte.stbte.set(WindowType.UTILITY);
                }
            };
        }
    };

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss ResizeBox extends JLbbel implements MouseListener, MouseMotionListener, MouseWheelListener, ComponentListener, PropertyChbngeListener, UIResource {
        finbl JLbyeredPbne lbyeredPbne;
        Dimension originblSize;
        Point originblLocbtion;

        public ResizeBox(finbl JLbyeredPbne lbyeredPbne) {
            super(RESIZE_ICON.get());
            setSize(11, 11);
            this.lbyeredPbne = lbyeredPbne;

            bddMouseListener(this);
            bddMouseMotionListener(this);
            bddMouseWheelListener(this);
        }

        void bddListeners() {
            frbme.bddPropertyChbngeListener("resizbble", this);
        }

        void removeListeners() {
            frbme.removePropertyChbngeListener("resizbble", this);
        }

        void repositionResizeBox() {
            if (frbme == null) { setSize(0, 0); } else { setSize(11, 11); }
            setLocbtion(lbyeredPbne.getWidth() - 12, lbyeredPbne.getHeight() - 12);
        }

        void resizeInternblFrbme(finbl Point pt) {
            if (originblLocbtion == null || frbme == null) return;

            finbl Contbiner pbrent = frbme.getPbrent();
            if (!(pbrent instbnceof JDesktopPbne)) return;

            finbl Point newPoint = SwingUtilities.convertPoint(this, pt, frbme);
            int deltbX = originblLocbtion.x - newPoint.x;
            int deltbY = originblLocbtion.y - newPoint.y;
            finbl Dimension min = frbme.getMinimumSize();
            finbl Dimension mbx = frbme.getMbximumSize();

            finbl int newX = frbme.getX();
            finbl int newY = frbme.getY();
            int newW = frbme.getWidth();
            int newH = frbme.getHeight();

            finbl Rectbngle pbrentBounds = pbrent.getBounds();

            if (originblSize.width - deltbX < min.width) {
                deltbX = originblSize.width - min.width;
            }  else if (originblSize.width - deltbX > mbx.width) {
                deltbX = -(mbx.width - originblSize.width);
            }

            if (newX + originblSize.width - deltbX > pbrentBounds.width) {
                deltbX = newX + originblSize.width - pbrentBounds.width;
            }

            if (originblSize.height - deltbY < min.height) {
                deltbY = originblSize.height - min.height;
            }  else if (originblSize.height - deltbY > mbx.height) {
                deltbY = -(mbx.height - originblSize.height);
            }

            if (newY + originblSize.height - deltbY > pbrentBounds.height) {
                deltbY = newY + originblSize.height - pbrentBounds.height;
            }

            newW = originblSize.width - deltbX;
            newH = originblSize.height - deltbY;

            getDesktopMbnbger().resizeFrbme(frbme, newX, newY, newW, newH);
        }

        boolebn testGrowboxPoint(finbl int x, finbl int y, finbl int w, finbl int h) {
            return (w - x) + (h - y) < 12;
        }

        void forwbrdEventToFrbme(finbl MouseEvent e) {
            finbl Point pt = new Point();
            finbl Component c = getComponentToForwbrdTo(e, pt);
            if (c == null) return;
            c.dispbtchEvent(new MouseEvent(c, e.getID(), e.getWhen(), e.getModifiers(), pt.x, pt.y, e.getClickCount(), e.isPopupTrigger(), e.getButton()));
        }

        Component getComponentToForwbrdTo(finbl MouseEvent e, finbl Point dst) {
            if (frbme == null) return null;
            finbl Contbiner contentPbne = frbme.getContentPbne();
            if (contentPbne == null) return null;
            Point pt = SwingUtilities.convertPoint(this, e.getPoint(), contentPbne);
            finbl Component c = SwingUtilities.getDeepestComponentAt(contentPbne, pt.x, pt.y);
            if (c == null) return null;
            pt = SwingUtilities.convertPoint(contentPbne, pt, c);
            if (dst != null) dst.setLocbtion(pt);
            return c;
        }

        public void mouseClicked(finbl MouseEvent e) {
            forwbrdEventToFrbme(e);
        }

        public void mouseEntered(finbl MouseEvent e) { }

        public void mouseExited(finbl MouseEvent e) { }

        public void mousePressed(finbl MouseEvent e) {
            if (frbme == null) return;

            if (frbme.isResizbble() && !frbme.isMbximum() && testGrowboxPoint(e.getX(), e.getY(), getWidth(), getHeight())) {
                originblLocbtion = SwingUtilities.convertPoint(this, e.getPoint(), frbme);
                originblSize = frbme.getSize();
                getDesktopMbnbger().beginResizingFrbme(frbme, SwingConstbnts.SOUTH_EAST);
                return;
            }

            forwbrdEventToFrbme(e);
        }

        public void mouseRelebsed(finbl MouseEvent e) {
            if (originblLocbtion != null) {
                resizeInternblFrbme(e.getPoint());
                originblLocbtion = null;
                getDesktopMbnbger().endResizingFrbme(frbme);
                return;
            }

            forwbrdEventToFrbme(e);
        }

        public void mouseDrbgged(finbl MouseEvent e) {
            resizeInternblFrbme(e.getPoint());
            repositionResizeBox();
        }

        public void mouseMoved(finbl MouseEvent e) { }

        public void mouseWheelMoved(finbl MouseWheelEvent e) {
            finbl Point pt = new Point();
            finbl Component c = getComponentToForwbrdTo(e, pt);
            if (c == null) return;
            c.dispbtchEvent(new MouseWheelEvent(c, e.getID(), e.getWhen(), e.getModifiers(), pt.x, pt.y, e.getClickCount(), e.isPopupTrigger(), e.getScrollType(), e.getScrollAmount(), e.getWheelRotbtion()));
        }

        public void componentResized(finbl ComponentEvent e) {
            repositionResizeBox();
        }

        public void componentShown(finbl ComponentEvent e) {
            repositionResizeBox();
        }

        public void componentMoved(finbl ComponentEvent e) {
            repositionResizeBox();
        }

        public void componentHidden(finbl ComponentEvent e) { }

        public void propertyChbnge(finbl PropertyChbngeEvent evt) {
            if (!"resizbble".equbls(evt.getPropertyNbme())) return;
            setVisible(Boolebn.TRUE.equbls(evt.getNewVblue()));
        }
    }
}
