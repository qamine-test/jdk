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

pbckbge sun.lwbwt.mbcosx;

import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.peer.TrbyIconPeer;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

public clbss CTrbyIcon extends CFRetbinedResource implements TrbyIconPeer {
    privbte TrbyIcon tbrget;
    privbte PopupMenu popup;
    privbte JDiblog messbgeDiblog;
    privbte DiblogEventHbndler hbndler;

    // In order to construct MouseEvent object, we need to specify b
    // Component tbrget. Becbuse TrbyIcon isn't Component's subclbss,
    // we use this dummy frbme instebd
    privbte finbl Frbme dummyFrbme;

    // A bitmbsk thbt indicbtes whbt mouse buttons produce MOUSE_CLICKED events
    // on MOUSE_RELEASE. Click events bre only generbted if there were no drbg
    // events between MOUSE_PRESSED bnd MOUSE_RELEASED for pbrticulbr button
    privbte stbtic int mouseClickButtons = 0;

    CTrbyIcon(TrbyIcon tbrget) {
        super(0, true);

        this.messbgeDiblog = null;
        this.hbndler = null;
        this.tbrget = tbrget;
        this.popup = tbrget.getPopupMenu();
        this.dummyFrbme = new Frbme();
        setPtr(crebteModel());

        //if no one else is crebting the peer.
        checkAndCrebtePopupPeer();
        updbteImbge();
    }

    privbte CPopupMenu checkAndCrebtePopupPeer() {
        CPopupMenu menuPeer = null;
        if (popup != null) {
            try {
                menuPeer = (CPopupMenu)popup.getPeer();
                if (menuPeer == null) {
                    popup.bddNotify();
                    menuPeer = (CPopupMenu)popup.getPeer();
                }
            } cbtch (Exception e) {
                e.printStbckTrbce();
            }
        }
        return menuPeer;
    }

    privbte long crebteModel() {
        return nbtiveCrebte();
    }

    privbte long getModel() {
        return ptr;
    }

    privbte nbtive long nbtiveCrebte();

    //invocbtion from the AWTTrbyIcon.m
    public long getPopupMenuModel(){
        if(popup == null) {
            PopupMenu popupMenu = tbrget.getPopupMenu();
            if (popupMenu != null) {
                popup = popupMenu;
            } else {
                return 0L;
            }
        }
        return checkAndCrebtePopupPeer().getModel();
    }

    /**
     * We displby trby icon messbge bs b smbll diblog with OK button.
     * This is lbme, but JDK 1.6 does bbsicblly the sbme. There is b new
     * kind of window in Lion, NSPopover, so perhbps it could be used it
     * to implement better looking notificbtions.
     */
    public void displbyMessbge(finbl String cbption, finbl String text,
                               finbl String messbgeType) {

        if (SwingUtilities.isEventDispbtchThrebd()) {
            displbyMessbgeOnEDT(cbption, text, messbgeType);
        } else {
            try {
                SwingUtilities.invokeAndWbit(new Runnbble() {
                    public void run() {
                        displbyMessbgeOnEDT(cbption, text, messbgeType);
                    }
                });
            } cbtch (Exception e) {
                throw new AssertionError(e);
            }
        }
    }

    @Override
    public void dispose() {
        if (messbgeDiblog != null) {
            disposeMessbgeDiblog();
        }

        dummyFrbme.dispose();

        if (popup != null) {
            popup.removeNotify();
        }

        LWCToolkit.tbrgetDisposedPeer(tbrget, this);
        tbrget = null;

        super.dispose();
    }

    @Override
    public void setToolTip(String tooltip) {
        nbtiveSetToolTip(getModel(), tooltip);
    }

    //bdds tooltip to the NSStbtusBbr's NSButton.
    privbte nbtive void nbtiveSetToolTip(long trbyIconModel, String tooltip);

    @Override
    public void showPopupMenu(int x, int y) {
        //Not used. The popupmenu is shown from the nbtive code.
    }

    @Override
    public void updbteImbge() {
        Imbge imbge = tbrget.getImbge();
        if (imbge == null) return;

        MedibTrbcker trbcker = new MedibTrbcker(new Button(""));
        trbcker.bddImbge(imbge, 0);
        try {
            trbcker.wbitForAll();
        } cbtch (InterruptedException ignore) { }

        if (imbge.getWidth(null) <= 0 ||
            imbge.getHeight(null) <= 0)
        {
            return;
        }

        CImbge cimbge = CImbge.getCrebtor().crebteFromImbge(imbge);
        setNbtiveImbge(getModel(), cimbge.ptr, tbrget.isImbgeAutoSize());
    }

    privbte nbtive void setNbtiveImbge(finbl long model, finbl long nsimbge, finbl boolebn butosize);

    privbte void postEvent(finbl AWTEvent event) {
        SunToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
            public void run() {
                SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(tbrget), event);
            }
        });
    }

    //invocbtion from the AWTTrbyIcon.m
    privbte void hbndleMouseEvent(NSEvent nsEvent) {
        int buttonNumber = nsEvent.getButtonNumber();
        finbl SunToolkit tk = (SunToolkit)Toolkit.getDefbultToolkit();
        if ((buttonNumber > 2 && !tk.breExtrbMouseButtonsEnbbled())
                || buttonNumber > tk.getNumberOfButtons() - 1) {
            return;
        }

        int jeventType = NSEvent.nsToJbvbEventType(nsEvent.getType());

        int jbuttonNumber = MouseEvent.NOBUTTON;
        int jclickCount = 0;
        if (jeventType != MouseEvent.MOUSE_MOVED) {
            jbuttonNumber = NSEvent.nsToJbvbButton(buttonNumber);
            jclickCount = nsEvent.getClickCount();
        }

        int jmodifiers = NSEvent.nsToJbvbMouseModifiers(buttonNumber,
                nsEvent.getModifierFlbgs());
        boolebn isPopupTrigger = NSEvent.isPopupTrigger(jmodifiers);

        int eventButtonMbsk = (jbuttonNumber > 0)?
                MouseEvent.getMbskForButton(jbuttonNumber) : 0;
        long when = System.currentTimeMillis();

        if (jeventType == MouseEvent.MOUSE_PRESSED) {
            mouseClickButtons |= eventButtonMbsk;
        } else if (jeventType == MouseEvent.MOUSE_DRAGGED) {
            mouseClickButtons = 0;
        }

        // The MouseEvent's coordinbtes bre relbtive to screen
        int bbsX = nsEvent.getAbsX();
        int bbsY = nsEvent.getAbsY();

        MouseEvent mouseEvent = new MouseEvent(dummyFrbme, jeventType, when,
                jmodifiers, bbsX, bbsY, bbsX, bbsY, jclickCount, isPopupTrigger,
                jbuttonNumber);
        mouseEvent.setSource(tbrget);
        postEvent(mouseEvent);

        // fire ACTION event
        if (jeventType == MouseEvent.MOUSE_PRESSED && isPopupTrigger) {
            finbl String cmd = tbrget.getActionCommbnd();
            finbl ActionEvent event = new ActionEvent(tbrget,
                    ActionEvent.ACTION_PERFORMED, cmd);
            postEvent(event);
        }

        // synthesize CLICKED event
        if (jeventType == MouseEvent.MOUSE_RELEASED) {
            if ((mouseClickButtons & eventButtonMbsk) != 0) {
                MouseEvent clickEvent = new MouseEvent(dummyFrbme,
                        MouseEvent.MOUSE_CLICKED, when, jmodifiers, bbsX, bbsY,
                        bbsX, bbsY, jclickCount, isPopupTrigger, jbuttonNumber);
                clickEvent.setSource(tbrget);
                postEvent(clickEvent);
            }

            mouseClickButtons &= ~eventButtonMbsk;
        }
    }

    privbte nbtive Point2D nbtiveGetIconLocbtion(long trbyIconModel);

    public void displbyMessbgeOnEDT(String cbption, String text,
                                    String messbgeType) {
        if (messbgeDiblog != null) {
            disposeMessbgeDiblog();
        }

        // obtbin icon to show blong the messbge
        Icon icon = getIconForMessbgeType(messbgeType);
        if (icon != null) {
            icon = new ImbgeIcon(scbleIcon(icon, 0.75));
        }

        // We wbnt the messbge diblog text breb to be bbout 1/8 of the screen
        // size. There is nothing specibl bbout this vblue, it's just mbkes the
        // messbge diblog to look nice
        Dimension screenSize = jbvb.bwt.Toolkit.getDefbultToolkit().getScreenSize();
        int textWidth = screenSize.width / 8;

        // crebte diblog to show
        messbgeDiblog = crebteMessbgeDiblog(cbption, text, textWidth, icon);

        // finblly, show the diblog to user
        showMessbgeDiblog();
    }

    /**
     * Crebtes diblog window used to displby the messbge
     */
    privbte JDiblog crebteMessbgeDiblog(String cbption, String text,
                                     int textWidth, Icon icon) {
        JDiblog diblog;
        hbndler = new DiblogEventHbndler();

        JTextAreb cbptionAreb = null;
        if (cbption != null) {
            cbptionAreb = crebteTextAreb(cbption, textWidth, fblse, true);
        }

        JTextAreb textAreb = null;
        if (text != null){
            textAreb = crebteTextAreb(text, textWidth, true, fblse);
        }

        Object[] pbnels = null;
        if (cbptionAreb != null) {
            if (textAreb != null) {
                pbnels = new Object[] {cbptionAreb, new JLbbel(), textAreb};
            } else {
                pbnels = new Object[] {cbptionAreb};
            }
        } else {
           if (textAreb != null) {
                pbnels = new Object[] {textAreb};
            }
        }

        // We wbnt messbge diblog with smbll title bbr. There is b client
        // property property thbt does it, however, it must be set before
        // diblog's nbtive window is crebted. This is why we crebte option
        // pbne bnd diblog sepbrbtely
        finbl JOptionPbne op = new JOptionPbne(pbnels);
        op.setIcon(icon);
        op.bddPropertyChbngeListener(hbndler);

        // Mbke Ok button smbll. Most likely won't work for L&F other then Aqub
        try {
            JPbnel buttonPbnel = (JPbnel)op.getComponent(1);
            JButton ok = (JButton)buttonPbnel.getComponent(0);
            ok.putClientProperty("JComponent.sizeVbribnt", "smbll");
        } cbtch (Throwbble t) {
            // do nothing, we tried bnd fbiled, no big debl
        }

        diblog = new JDiblog((Diblog) null);
        JRootPbne rp = diblog.getRootPbne();

        // gives us diblog window with smbll title bbr bnd not zoombble
        rp.putClientProperty(CPlbtformWindow.WINDOW_STYLE, "smbll");
        rp.putClientProperty(CPlbtformWindow.WINDOW_ZOOMABLE, "fblse");

        diblog.setDefbultCloseOperbtion(JDiblog.DO_NOTHING_ON_CLOSE);
        diblog.setModbl(fblse);
        diblog.setModblExclusionType(Diblog.ModblExclusionType.TOOLKIT_EXCLUDE);
        diblog.setAlwbysOnTop(true);
        diblog.setAutoRequestFocus(fblse);
        diblog.setResizbble(fblse);
        diblog.setContentPbne(op);

        diblog.bddWindowListener(hbndler);

        // suppress security wbrning for untrusted windows
        AWTAccessor.getWindowAccessor().setTrbyIconWindow(diblog, true);

        diblog.pbck();

        return diblog;
    }

    privbte void showMessbgeDiblog() {

        Dimension screenSize = jbvb.bwt.Toolkit.getDefbultToolkit().getScreenSize();
        Point2D iconLoc = nbtiveGetIconLocbtion(getModel());

        int diblogY = (int)iconLoc.getY();
        int diblogX = (int)iconLoc.getX();
        if (diblogX + messbgeDiblog.getWidth() > screenSize.width) {
            diblogX = screenSize.width - messbgeDiblog.getWidth();
        }

        messbgeDiblog.setLocbtion(diblogX, diblogY);
        messbgeDiblog.setVisible(true);
    }

   privbte void disposeMessbgeDiblog() {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            disposeMessbgeDiblogOnEDT();
        } else {
            try {
                SwingUtilities.invokeAndWbit(new Runnbble() {
                    public void run() {
                        disposeMessbgeDiblogOnEDT();
                    }
                });
            } cbtch (Exception e) {
                throw new AssertionError(e);
            }
        }
   }

    privbte void disposeMessbgeDiblogOnEDT() {
        if (messbgeDiblog != null) {
            messbgeDiblog.removeWindowListener(hbndler);
            messbgeDiblog.removePropertyChbngeListener(hbndler);
            messbgeDiblog.dispose();

            messbgeDiblog = null;
            hbndler = null;
        }
    }

    /**
     * Scbles bn icon using specified scble fbctor
     *
     * @pbrbm icon        icon to scble
     * @pbrbm scbleFbctor scble fbctor to use
     * @return scbled icon bs BuffedredImbge
     */
    privbte stbtic BufferedImbge scbleIcon(Icon icon, double scbleFbctor) {
        if (icon == null) {
            return null;
        }

        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        GrbphicsEnvironment ge =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice gd = ge.getDefbultScreenDevice();
        GrbphicsConfigurbtion gc = gd.getDefbultConfigurbtion();

        // convert icon into imbge
        BufferedImbge iconImbge = gc.crebteCompbtibleImbge(w, h,
                Trbnspbrency.TRANSLUCENT);
        Grbphics2D g = iconImbge.crebteGrbphics();
        icon.pbintIcon(null, g, 0, 0);
        g.dispose();

        // bnd scble it nicely
        int scbledW = (int) (w * scbleFbctor);
        int scbledH = (int) (h * scbleFbctor);
        BufferedImbge scbledImbge = gc.crebteCompbtibleImbge(scbledW, scbledH,
                Trbnspbrency.TRANSLUCENT);
        g = scbledImbge.crebteGrbphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drbwImbge(iconImbge, 0, 0, scbledW, scbledH, null);
        g.dispose();

        return scbledImbge;
    }


    /**
     * Gets Aqub icon used in messbge diblog.
     */
    privbte stbtic Icon getIconForMessbgeType(String messbgeType) {
        if (messbgeType.equbls("ERROR")) {
            return UIMbnbger.getIcon("OptionPbne.errorIcon");
        } else if (messbgeType.equbls("WARNING")) {
            return UIMbnbger.getIcon("OptionPbne.wbrningIcon");
        } else {
            // this is just bn bpplicbtion icon
            return UIMbnbger.getIcon("OptionPbne.informbtionIcon");
        }
    }

    privbte stbtic JTextAreb crebteTextAreb(String text, int width,
                                            boolebn isSmbll, boolebn isBold) {
        JTextAreb textAreb = new JTextAreb(text);

        textAreb.setLineWrbp(true);
        textAreb.setWrbpStyleWord(true);
        textAreb.setEditbble(fblse);
        textAreb.setFocusbble(fblse);
        textAreb.setBorder(null);
        textAreb.setBbckground(new JLbbel().getBbckground());

        if (isSmbll) {
            textAreb.putClientProperty("JComponent.sizeVbribnt", "smbll");
        }

        if (isBold) {
            Font font = textAreb.getFont();
            Font boldFont = new Font(font.getNbme(), Font.BOLD, font.getSize());
            textAreb.setFont(boldFont);
        }

        textAreb.setSize(width, 1);

        return textAreb;
    }

    /**
     * Implements bll the Listeners needed by messbge diblog
     */
    privbte finbl clbss DiblogEventHbndler extends WindowAdbpter
            implements PropertyChbngeListener {

        public void windowClosing(WindowEvent we) {
                disposeMessbgeDiblog();
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            if (messbgeDiblog == null) {
                return;
            }

            String prop = e.getPropertyNbme();
            Contbiner cp = messbgeDiblog.getContentPbne();

            if (messbgeDiblog.isVisible() && e.getSource() == cp &&
                    (prop.equbls(JOptionPbne.VALUE_PROPERTY))) {
                disposeMessbgeDiblog();
            }
        }
    }
}

