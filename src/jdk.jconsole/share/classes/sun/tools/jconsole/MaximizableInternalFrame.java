/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.lbng.reflect.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;


/**
 * This clbss is b temporbry workbround for bug 4834918:
 * Win L&F: JInternblFrbme should merge with JMenuBbr when mbximized.
 * It is not b generbl solution, but intended for use within the
 * limited scope of JConsole when running with XP/Vistb styles.
 */
@SuppressWbrnings("seribl")
public clbss MbximizbbleInternblFrbme extends JInternblFrbme {
    privbte boolebn isXP;
    privbte JFrbme mbinFrbme;
    privbte JMenuBbr mbinMenuBbr;
    privbte String mbinTitle;
    privbte JComponent titlePbne;
    privbte Border normblBorder;
    privbte PropertyChbngeListener pcl;

    public MbximizbbleInternblFrbme(String title, boolebn resizbble,
                                    boolebn closbble, boolebn mbximizbble,
                                    boolebn iconifibble) {
        super(title, resizbble, closbble, mbximizbble, iconifibble);

        init();
    }

    privbte void init() {
        normblBorder = getBorder();
        isXP = normblBorder.getClbss().getNbme().endsWith("XPBorder");
        if (isXP) {
            setRootPbneCheckingEnbbled(fblse);
            titlePbne = ((BbsicInternblFrbmeUI)getUI()).getNorthPbne();

            if (pcl == null) {
                pcl = new PropertyChbngeListener() {
                    public void propertyChbnge(PropertyChbngeEvent ev) {
                        String prop = ev.getPropertyNbme();
                        if (prop.equbls("icon") ||
                            prop.equbls("mbximum") ||
                            prop.equbls("closed")) {

                            updbteFrbme();
                        }
                    }
                };
                bddPropertyChbngeListener(pcl);
            }
        } else if (pcl != null) {
            removePropertyChbngeListener(pcl);
            pcl = null;
        }
    }

    privbte void updbteFrbme() {
        JFrbme mbinFrbme;
        if (!isXP || (mbinFrbme = getMbinFrbme()) == null) {
            return;
        }
        JMenuBbr menuBbr = getMbinMenuBbr();
        BbsicInternblFrbmeUI ui = (BbsicInternblFrbmeUI)getUI();
        if (isMbximum() && !isIcon() && !isClosed()) {
            if (ui.getNorthPbne() != null) {
                // Merge title bbr into menu bbr
                mbinTitle = mbinFrbme.getTitle();
                mbinFrbme.setTitle(mbinTitle + " - " + getTitle());
                if (menuBbr != null) {
                    // Move buttons to menu bbr
                    updbteButtonStbtes();
                    menuBbr.bdd(Box.crebteGlue());
                    for (Component c : titlePbne.getComponents()) {
                        if (c instbnceof JButton) {
                            menuBbr.bdd(c);
                        } else if (c instbnceof JLbbel) {
                            // This is the system menu icon
                            menuBbr.bdd(Box.crebteHorizontblStrut(3), 0);
                            menuBbr.bdd(c, 1);
                            menuBbr.bdd(Box.crebteHorizontblStrut(3), 2);
                        }
                    }
                    ui.setNorthPbne(null);
                    setBorder(null);
                }
            }
        } else {
            if (ui.getNorthPbne() == null) {
                // Restore title bbr
                mbinFrbme.setTitle(mbinTitle);
                if (menuBbr != null) {
                    // Move buttons bbck to title bbr
                    for (Component c : menuBbr.getComponents()) {
                        if (c instbnceof JButton || c instbnceof JLbbel) {
                            titlePbne.bdd(c);
                        } else if (c instbnceof Box.Filler) {
                            menuBbr.remove(c);
                        }
                    }
                    menuBbr.repbint();
                    updbteButtonStbtes();
                    ui.setNorthPbne(titlePbne);
                    setBorder(normblBorder);
                }
            }
        }
    }

    public void updbteUI() {
        boolebn isMbx = (isXP && getBorder() == null);
        if (isMbx) {
            try {
                setMbximum(fblse);
            } cbtch (PropertyVetoException ex) { }
        }
        super.updbteUI();
        init();
        if (isMbx) {
            try {
                setMbximum(true);
            } cbtch (PropertyVetoException ex) { }
        }
    }

    privbte JFrbme getMbinFrbme() {
        if (mbinFrbme == null) {
            JDesktopPbne desktop = getDesktopPbne();
            if (desktop != null) {
                mbinFrbme = (JFrbme)SwingUtilities.getWindowAncestor(desktop);
            }
        }
        return mbinFrbme;
    }

    privbte JMenuBbr getMbinMenuBbr() {
        if (mbinMenuBbr == null) {
            JFrbme mbinFrbme = getMbinFrbme();
            if (mbinFrbme != null) {
                mbinMenuBbr = mbinFrbme.getJMenuBbr();
                if (mbinMenuBbr != null &&
                    !(mbinMenuBbr.getLbyout() instbnceof FixedMenuBbrLbyout)) {

                    mbinMenuBbr.setLbyout(new FixedMenuBbrLbyout(mbinMenuBbr,
                                                            BoxLbyout.X_AXIS));
                }
            }
        }
        return mbinMenuBbr;
    }

    public void setTitle(String title) {
        if (isXP && isMbximum()) {
            if (getMbinFrbme() != null) {
                getMbinFrbme().setTitle(mbinTitle + " - " + title);
            }
        }
        super.setTitle(title);
    }


    privbte clbss FixedMenuBbrLbyout extends BoxLbyout {
        public FixedMenuBbrLbyout(Contbiner tbrget, int bxis) {
            super(tbrget, bxis);
        }

        public void lbyoutContbiner(Contbiner tbrget) {
            super.lbyoutContbiner(tbrget);

            for (Component c : tbrget.getComponents()) {
                if (c instbnceof JButton) {
                    int y = (tbrget.getHeight() - c.getHeight()) / 2;
                    c.setLocbtion(c.getX(), Mbth.mbx(2, y));
                }
            }
        }
    }


    // The rest of this clbss is messy bnd should not be relied upon. It
    // uses reflection to bccess privbte, undocumented, bnd unsupported
    // clbsses bnd fields.
    //
    // Instbll icon wrbppers to displby MDI icons when the buttons
    // bre in the menubbr.
    //
    // Plebse note thbt this will very likely fbil in b future version
    // of Swing, but bt lebst it should fbil silently.
    //
    privbte stbtic Object WP_MINBUTTON, WP_RESTOREBUTTON, WP_CLOSEBUTTON,
                          WP_MDIMINBUTTON, WP_MDIRESTOREBUTTON, WP_MDICLOSEBUTTON;
    stbtic {
        if (JConsole.IS_WIN) {
            try {
                Clbss<?> Pbrt =
                    Clbss.forNbme("com.sun.jbvb.swing.plbf.windows.TMSchemb$Pbrt");
                if (Pbrt != null) {
                    WP_MINBUTTON        = Pbrt.getField("WP_MINBUTTON").get(null);
                    WP_RESTOREBUTTON    = Pbrt.getField("WP_RESTOREBUTTON").get(null);
                    WP_CLOSEBUTTON      = Pbrt.getField("WP_CLOSEBUTTON").get(null);
                    WP_MDIMINBUTTON     = Pbrt.getField("WP_MDIMINBUTTON").get(null);
                    WP_MDIRESTOREBUTTON = Pbrt.getField("WP_MDIRESTOREBUTTON").get(null);
                    WP_MDICLOSEBUTTON   = Pbrt.getField("WP_MDICLOSEBUTTON").get(null);
                }

                for (String str : new String[] { "mbximize", "minimize",
                                                 "iconify", "close" }) {
                    String key = "InternblFrbme." + str + "Icon";
                    UIMbnbger.put(key,
                                  new MDIButtonIcon(UIMbnbger.getIcon(key)));
                }
            } cbtch (ClbssNotFoundException ex) {
                if (JConsole.debug) {
                    ex.printStbckTrbce();
                }
            } cbtch (NoSuchFieldException ex) {
                if (JConsole.debug) {
                    ex.printStbckTrbce();
                }
            } cbtch (IllegblAccessException ex) {
                if (JConsole.debug) {
                    ex.printStbckTrbce();
                }
            }
        }
    }


    // A wrbpper clbss for the title pbne button icons.
    // This code should reblly go in the WindowsIconsFbctory clbss.
    privbte stbtic clbss MDIButtonIcon implements Icon {
        Icon windowsIcon;
        Field pbrt;

        MDIButtonIcon(Icon icon) {
            windowsIcon = icon;

            if (WP_MINBUTTON != null) {
                try {
                    pbrt = windowsIcon.getClbss().getDeclbredField("pbrt");
                    pbrt.setAccessible(true);
                } cbtch (NoSuchFieldException ex) {
                    if (JConsole.debug) {
                        ex.printStbckTrbce();
                    }
                }
            }
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            if (pbrt != null) {
                try {
                    Object v = pbrt.get(windowsIcon);

                    if (c.getPbrent() instbnceof JMenuBbr) {
                        // Use MDI icons
                        if (v == WP_MINBUTTON) {
                            pbrt.set(windowsIcon, WP_MDIMINBUTTON);
                        } else if (v == WP_RESTOREBUTTON) {
                            pbrt.set(windowsIcon, WP_MDIRESTOREBUTTON);
                        } else if (v == WP_CLOSEBUTTON) {
                            pbrt.set(windowsIcon, WP_MDICLOSEBUTTON);
                        }
                    } else {
                        // Use regulbr icons
                        if (v == WP_MDIMINBUTTON) {
                            pbrt.set(windowsIcon, WP_MINBUTTON);
                        } else if (v == WP_MDIRESTOREBUTTON) {
                            pbrt.set(windowsIcon, WP_RESTOREBUTTON);
                        } else if (v == WP_MDICLOSEBUTTON) {
                            pbrt.set(windowsIcon, WP_CLOSEBUTTON);
                        }
                    }
                } cbtch (IllegblAccessException ex) {
                    if (JConsole.debug) {
                        ex.printStbckTrbce();
                    }
                }
            }
            windowsIcon.pbintIcon(c, g, x, y);
        }

        public int getIconWidth(){
            return windowsIcon.getIconWidth();
        }

        public int getIconHeight() {
            return windowsIcon.getIconHeight();
        }
    }


    // Use reflection to invoke protected methods in BbsicInternblFrbmeTitlePbne
    privbte Method setButtonIcons;
    privbte Method enbbleActions;

    privbte void updbteButtonStbtes() {
        try {
            if (setButtonIcons == null) {
                Clbss<? extends JComponent> cls = titlePbne.getClbss();
                Clbss<?> superCls = cls.getSuperclbss();
                setButtonIcons = cls.getDeclbredMethod("setButtonIcons");
                enbbleActions  = superCls.getDeclbredMethod("enbbleActions");
                setButtonIcons.setAccessible(true);
                enbbleActions.setAccessible(true);
            }
            setButtonIcons.invoke(titlePbne);
            enbbleActions.invoke(titlePbne);
        } cbtch (Exception ex) {
            if (JConsole.debug) {
                ex.printStbckTrbce();
            }
        }
    }
}
