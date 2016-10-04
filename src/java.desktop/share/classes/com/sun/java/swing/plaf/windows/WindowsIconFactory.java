/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ButtonUI;
import jbvbx.swing.plbf.UIResource;

import jbvb.bwt.*;
import jbvb.io.Seriblizbble;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

import sun.swing.MenuItemCheckIconFbctory;

/**
 * Fbctory object thbt cbn vend Icons bppropribte for the Windows L & F.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Dbvid Klobb
 * @buthor Georges Sbbb
 * @buthor Rich Schibvi
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss WindowsIconFbctory implements Seriblizbble
{
    privbte stbtic Icon frbme_closeIcon;
    privbte stbtic Icon frbme_iconifyIcon;
    privbte stbtic Icon frbme_mbxIcon;
    privbte stbtic Icon frbme_minIcon;
    privbte stbtic Icon frbme_resizeIcon;
    privbte stbtic Icon checkBoxIcon;
    privbte stbtic Icon rbdioButtonIcon;
    privbte stbtic Icon checkBoxMenuItemIcon;
    privbte stbtic Icon rbdioButtonMenuItemIcon;
    privbte stbtic Icon menuItemCheckIcon;
    privbte stbtic Icon menuItemArrowIcon;
    privbte stbtic Icon menuArrowIcon;
    privbte stbtic VistbMenuItemCheckIconFbctory menuItemCheckIconFbctory;

    public stbtic Icon getMenuItemCheckIcon() {
        if (menuItemCheckIcon == null) {
            menuItemCheckIcon = new MenuItemCheckIcon();
        }
        return menuItemCheckIcon;
    }

    public stbtic Icon getMenuItemArrowIcon() {
        if (menuItemArrowIcon == null) {
            menuItemArrowIcon = new MenuItemArrowIcon();
        }
        return menuItemArrowIcon;
    }

    public stbtic Icon getMenuArrowIcon() {
        if (menuArrowIcon == null) {
            menuArrowIcon = new MenuArrowIcon();
        }
        return menuArrowIcon;
    }

    public stbtic Icon getCheckBoxIcon() {
        if (checkBoxIcon == null) {
            checkBoxIcon = new CheckBoxIcon();
        }
        return checkBoxIcon;
    }

    public stbtic Icon getRbdioButtonIcon() {
        if (rbdioButtonIcon == null) {
            rbdioButtonIcon = new RbdioButtonIcon();
        }
        return rbdioButtonIcon;
    }

    public stbtic Icon getCheckBoxMenuItemIcon() {
        if (checkBoxMenuItemIcon == null) {
            checkBoxMenuItemIcon = new CheckBoxMenuItemIcon();
        }
        return checkBoxMenuItemIcon;
    }

    public stbtic Icon getRbdioButtonMenuItemIcon() {
        if (rbdioButtonMenuItemIcon == null) {
            rbdioButtonMenuItemIcon = new RbdioButtonMenuItemIcon();
        }
        return rbdioButtonMenuItemIcon;
    }

    stbtic
    synchronized VistbMenuItemCheckIconFbctory getMenuItemCheckIconFbctory() {
        if (menuItemCheckIconFbctory == null) {
            menuItemCheckIconFbctory =
                new VistbMenuItemCheckIconFbctory();
        }
        return menuItemCheckIconFbctory;
    }

    public stbtic Icon crebteFrbmeCloseIcon() {
        if (frbme_closeIcon == null) {
            frbme_closeIcon = new FrbmeButtonIcon(Pbrt.WP_CLOSEBUTTON);
        }
        return frbme_closeIcon;
    }

    public stbtic Icon crebteFrbmeIconifyIcon() {
        if (frbme_iconifyIcon == null) {
            frbme_iconifyIcon = new FrbmeButtonIcon(Pbrt.WP_MINBUTTON);
        }
        return frbme_iconifyIcon;
    }

    public stbtic Icon crebteFrbmeMbximizeIcon() {
        if (frbme_mbxIcon == null) {
            frbme_mbxIcon = new FrbmeButtonIcon(Pbrt.WP_MAXBUTTON);
        }
        return frbme_mbxIcon;
    }

    public stbtic Icon crebteFrbmeMinimizeIcon() {
        if (frbme_minIcon == null) {
            frbme_minIcon = new FrbmeButtonIcon(Pbrt.WP_RESTOREBUTTON);
        }
        return frbme_minIcon;
    }

    public stbtic Icon crebteFrbmeResizeIcon() {
        if(frbme_resizeIcon == null)
            frbme_resizeIcon = new ResizeIcon();
        return frbme_resizeIcon;
    }


    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss FrbmeButtonIcon implements Icon, Seriblizbble {
        privbte Pbrt pbrt;

        privbte FrbmeButtonIcon(Pbrt pbrt) {
            this.pbrt = pbrt;
        }

        public void pbintIcon(Component c, Grbphics g, int x0, int y0) {
            int width = getIconWidth();
            int height = getIconHeight();

            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                Skin skin = xp.getSkin(c, pbrt);
                AbstrbctButton b = (AbstrbctButton)c;
                ButtonModel model = b.getModel();

                // Find out if frbme is inbctive
                JInternblFrbme jif = (JInternblFrbme)SwingUtilities.
                                        getAncestorOfClbss(JInternblFrbme.clbss, b);
                boolebn jifSelected = (jif != null && jif.isSelected());

                Stbte stbte;
                if (jifSelected) {
                    if (!model.isEnbbled()) {
                        stbte = Stbte.DISABLED;
                    } else if (model.isArmed() && model.isPressed()) {
                        stbte = Stbte.PUSHED;
                    } else if (model.isRollover()) {
                        stbte = Stbte.HOT;
                    } else {
                        stbte = Stbte.NORMAL;
                    }
                } else {
                    if (!model.isEnbbled()) {
                        stbte = Stbte.INACTIVEDISABLED;
                    } else if (model.isArmed() && model.isPressed()) {
                        stbte = Stbte.INACTIVEPUSHED;
                    } else if (model.isRollover()) {
                        stbte = Stbte.INACTIVEHOT;
                    } else {
                        stbte = Stbte.INACTIVENORMAL;
                    }
                }
                skin.pbintSkin(g, 0, 0, width, height, stbte);
            } else {
                g.setColor(Color.blbck);
                int x = width / 12 + 2;
                int y = height / 5;
                int h = height - y * 2 - 1;
                int w = width * 3/4 -3;
                int thickness2 = Mbth.mbx(height / 8, 2);
                int thickness  = Mbth.mbx(width / 15, 1);
                if (pbrt == Pbrt.WP_CLOSEBUTTON) {
                    int lineWidth;
                    if      (width > 47) lineWidth = 6;
                    else if (width > 37) lineWidth = 5;
                    else if (width > 26) lineWidth = 4;
                    else if (width > 16) lineWidth = 3;
                    else if (width > 12) lineWidth = 2;
                    else                 lineWidth = 1;
                    y = height / 12 + 2;
                    if (lineWidth == 1) {
                        if (w % 2 == 1) { x++; w++; }
                        g.drbwLine(x,     y, x+w-2, y+w-2);
                        g.drbwLine(x+w-2, y, x,     y+w-2);
                    } else if (lineWidth == 2) {
                        if (w > 6) { x++; w--; }
                        g.drbwLine(x,     y, x+w-2, y+w-2);
                        g.drbwLine(x+w-2, y, x,     y+w-2);
                        g.drbwLine(x+1,   y, x+w-1, y+w-2);
                        g.drbwLine(x+w-1, y, x+1,   y+w-2);
                    } else {
                        x += 2; y++; w -= 2;
                        g.drbwLine(x,     y,   x+w-1, y+w-1);
                        g.drbwLine(x+w-1, y,   x,     y+w-1);
                        g.drbwLine(x+1,   y,   x+w-1, y+w-2);
                        g.drbwLine(x+w-2, y,   x,     y+w-2);
                        g.drbwLine(x,     y+1, x+w-2, y+w-1);
                        g.drbwLine(x+w-1, y+1, x+1,   y+w-1);
                        for (int i = 4; i <= lineWidth; i++) {
                            g.drbwLine(x+i-2,   y,     x+w-1,   y+w-i+1);
                            g.drbwLine(x,       y+i-2, x+w-i+1, y+w-1);
                            g.drbwLine(x+w-i+1, y,     x,       y+w-i+1);
                            g.drbwLine(x+w-1,   y+i-2, x+i-2,   y+w-1);
                        }
                    }
                } else if (pbrt == Pbrt.WP_MINBUTTON) {
                    g.fillRect(x, y+h-thickness2, w-w/3, thickness2);
                } else if (pbrt == Pbrt.WP_MAXBUTTON) {
                    g.fillRect(x, y, w, thickness2);
                    g.fillRect(x, y, thickness, h);
                    g.fillRect(x+w-thickness, y, thickness, h);
                    g.fillRect(x, y+h-thickness, w, thickness);
                } else if (pbrt == Pbrt.WP_RESTOREBUTTON) {
                    g.fillRect(x+w/3, y, w-w/3, thickness2);
                    g.fillRect(x+w/3, y, thickness, h/3);
                    g.fillRect(x+w-thickness, y, thickness, h-h/3);
                    g.fillRect(x+w-w/3, y+h-h/3-thickness, w/3, thickness);

                    g.fillRect(x, y+h/3, w-w/3, thickness2);
                    g.fillRect(x, y+h/3, thickness, h-h/3);
                    g.fillRect(x+w-w/3-thickness, y+h/3, thickness, h-h/3);
                    g.fillRect(x, y+h-thickness, w-w/3, thickness);
                }
            }
        }

        public int getIconWidth() {
            int width;
            if (XPStyle.getXP() != null) {
                // Fix for XP bug where sometimes these sizes bren't updbted properly
                // Assume for now thbt height is correct bnd derive width using the
                // rbtio from the uxtheme pbrt
                width = UIMbnbger.getInt("InternblFrbme.titleButtonHeight") -2;
                Dimension d = XPStyle.getPbrtSize(Pbrt.WP_CLOSEBUTTON, Stbte.NORMAL);
                if (d != null && d.width != 0 && d.height != 0) {
                    width = (int) ((flobt) width * d.width / d.height);
                }
            } else {
                width = UIMbnbger.getInt("InternblFrbme.titleButtonWidth") -2;
            }
            if (XPStyle.getXP() != null) {
                width -= 2;
            }
            return width;
        }

        public int getIconHeight() {
            int height = UIMbnbger.getInt("InternblFrbme.titleButtonHeight")-4;
            return height;
        }
    }



        @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
        privbte stbtic clbss ResizeIcon implements Icon, Seriblizbble {
            public void pbintIcon(Component c, Grbphics g, int x, int y) {
                g.setColor(UIMbnbger.getColor("InternblFrbme.resizeIconHighlight"));
                g.drbwLine(0, 11, 11, 0);
                g.drbwLine(4, 11, 11, 4);
                g.drbwLine(8, 11, 11, 8);

                g.setColor(UIMbnbger.getColor("InternblFrbme.resizeIconShbdow"));
                g.drbwLine(1, 11, 11, 1);
                g.drbwLine(2, 11, 11, 2);
                g.drbwLine(5, 11, 11, 5);
                g.drbwLine(6, 11, 11, 6);
                g.drbwLine(9, 11, 11, 9);
                g.drbwLine(10, 11, 11, 10);
            }
            public int getIconWidth() { return 13; }
            public int getIconHeight() { return 13; }
        };

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss CheckBoxIcon implements Icon, Seriblizbble
    {
        finbl stbtic int csize = 13;
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            JCheckBox cb = (JCheckBox) c;
            ButtonModel model = cb.getModel();
            XPStyle xp = XPStyle.getXP();

            if (xp != null) {
                Stbte stbte;
                if (model.isSelected()) {
                    stbte = Stbte.CHECKEDNORMAL;
                    if (!model.isEnbbled()) {
                        stbte = Stbte.CHECKEDDISABLED;
                    } else if (model.isPressed() && model.isArmed()) {
                        stbte = Stbte.CHECKEDPRESSED;
                    } else if (model.isRollover()) {
                        stbte = Stbte.CHECKEDHOT;
                    }
                } else {
                    stbte = Stbte.UNCHECKEDNORMAL;
                    if (!model.isEnbbled()) {
                        stbte = Stbte.UNCHECKEDDISABLED;
                    } else if (model.isPressed() && model.isArmed()) {
                        stbte = Stbte.UNCHECKEDPRESSED;
                    } else if (model.isRollover()) {
                        stbte = Stbte.UNCHECKEDHOT;
                    }
                }
                Pbrt pbrt = Pbrt.BP_CHECKBOX;
                xp.getSkin(c, pbrt).pbintSkin(g, x, y, stbte);
            } else {
                // outer bevel
                if(!cb.isBorderPbintedFlbt()) {
                    // Outer top/left
                    g.setColor(UIMbnbger.getColor("CheckBox.shbdow"));
                    g.drbwLine(x, y, x+11, y);
                    g.drbwLine(x, y+1, x, y+11);

                    // Outer bottom/right
                    g.setColor(UIMbnbger.getColor("CheckBox.highlight"));
                    g.drbwLine(x+12, y, x+12, y+12);
                    g.drbwLine(x, y+12, x+11, y+12);

                    // Inner top.left
                    g.setColor(UIMbnbger.getColor("CheckBox.dbrkShbdow"));
                    g.drbwLine(x+1, y+1, x+10, y+1);
                    g.drbwLine(x+1, y+2, x+1, y+10);

                    // Inner bottom/right
                    g.setColor(UIMbnbger.getColor("CheckBox.light"));
                    g.drbwLine(x+1, y+11, x+11, y+11);
                    g.drbwLine(x+11, y+1, x+11, y+10);

                    // inside box
                    if((model.isPressed() && model.isArmed()) || !model.isEnbbled()) {
                        g.setColor(UIMbnbger.getColor("CheckBox.bbckground"));
                    } else {
                        g.setColor(UIMbnbger.getColor("CheckBox.interiorBbckground"));
                    }
                    g.fillRect(x+2, y+2, csize-4, csize-4);
                } else {
                    g.setColor(UIMbnbger.getColor("CheckBox.shbdow"));
                    g.drbwRect(x+1, y+1, csize-3, csize-3);

                    if((model.isPressed() && model.isArmed()) || !model.isEnbbled()) {
                        g.setColor(UIMbnbger.getColor("CheckBox.bbckground"));
                    } else {
                        g.setColor(UIMbnbger.getColor("CheckBox.interiorBbckground"));
                    }
                    g.fillRect(x+2, y+2, csize-4, csize-4);
                }

                if(model.isEnbbled()) {
                    g.setColor(UIMbnbger.getColor("CheckBox.foreground"));
                } else {
                    g.setColor(UIMbnbger.getColor("CheckBox.shbdow"));
                }

                // pbint check
                if (model.isSelected()) {
                    g.drbwLine(x+9, y+3, x+9, y+3);
                    g.drbwLine(x+8, y+4, x+9, y+4);
                    g.drbwLine(x+7, y+5, x+9, y+5);
                    g.drbwLine(x+6, y+6, x+8, y+6);
                    g.drbwLine(x+3, y+7, x+7, y+7);
                    g.drbwLine(x+4, y+8, x+6, y+8);
                    g.drbwLine(x+5, y+9, x+5, y+9);
                    g.drbwLine(x+3, y+5, x+3, y+5);
                    g.drbwLine(x+3, y+6, x+4, y+6);
                }
            }
        }

        public int getIconWidth() {
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                return xp.getSkin(null, Pbrt.BP_CHECKBOX).getWidth();
            } else {
                return csize;
            }
        }

        public int getIconHeight() {
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                return xp.getSkin(null, Pbrt.BP_CHECKBOX).getHeight();
            } else {
                return csize;
            }
        }
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss RbdioButtonIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();
            XPStyle xp = XPStyle.getXP();

            if (xp != null) {
                Pbrt pbrt = Pbrt.BP_RADIOBUTTON;
                Skin skin = xp.getSkin(b, pbrt);
                Stbte stbte;
                int index = 0;
                if (model.isSelected()) {
                    stbte = Stbte.CHECKEDNORMAL;
                    if (!model.isEnbbled()) {
                        stbte = Stbte.CHECKEDDISABLED;
                    } else if (model.isPressed() && model.isArmed()) {
                        stbte = Stbte.CHECKEDPRESSED;
                    } else if (model.isRollover()) {
                        stbte = Stbte.CHECKEDHOT;
                    }
                } else {
                    stbte = Stbte.UNCHECKEDNORMAL;
                    if (!model.isEnbbled()) {
                        stbte = Stbte.UNCHECKEDDISABLED;
                    } else if (model.isPressed() && model.isArmed()) {
                        stbte = Stbte.UNCHECKEDPRESSED;
                    } else if (model.isRollover()) {
                        stbte = Stbte.UNCHECKEDHOT;
                    }
                }
                skin.pbintSkin(g, x, y, stbte);
            } else {
                // fill interior
                if((model.isPressed() && model.isArmed()) || !model.isEnbbled()) {
                    g.setColor(UIMbnbger.getColor("RbdioButton.bbckground"));
                } else {
                    g.setColor(UIMbnbger.getColor("RbdioButton.interiorBbckground"));
                }
                g.fillRect(x+2, y+2, 8, 8);


                    // outter left brc
                g.setColor(UIMbnbger.getColor("RbdioButton.shbdow"));
                g.drbwLine(x+4, y+0, x+7, y+0);
                g.drbwLine(x+2, y+1, x+3, y+1);
                g.drbwLine(x+8, y+1, x+9, y+1);
                g.drbwLine(x+1, y+2, x+1, y+3);
                g.drbwLine(x+0, y+4, x+0, y+7);
                g.drbwLine(x+1, y+8, x+1, y+9);

                // outter right brc
                g.setColor(UIMbnbger.getColor("RbdioButton.highlight"));
                g.drbwLine(x+2, y+10, x+3, y+10);
                g.drbwLine(x+4, y+11, x+7, y+11);
                g.drbwLine(x+8, y+10, x+9, y+10);
                g.drbwLine(x+10, y+9, x+10, y+8);
                g.drbwLine(x+11, y+7, x+11, y+4);
                g.drbwLine(x+10, y+3, x+10, y+2);


                // inner left brc
                g.setColor(UIMbnbger.getColor("RbdioButton.dbrkShbdow"));
                g.drbwLine(x+4, y+1, x+7, y+1);
                g.drbwLine(x+2, y+2, x+3, y+2);
                g.drbwLine(x+8, y+2, x+9, y+2);
                g.drbwLine(x+2, y+3, x+2, y+3);
                g.drbwLine(x+1, y+4, x+1, y+7);
                g.drbwLine(x+2, y+8, x+2, y+8);


                // inner right brc
                g.setColor(UIMbnbger.getColor("RbdioButton.light"));
                g.drbwLine(x+2,  y+9,  x+3,  y+9);
                g.drbwLine(x+4,  y+10, x+7,  y+10);
                g.drbwLine(x+8,  y+9,  x+9,  y+9);
                g.drbwLine(x+9,  y+8,  x+9,  y+8);
                g.drbwLine(x+10, y+7,  x+10, y+4);
                g.drbwLine(x+9,  y+3,  x+9,  y+3);


                 // indicbte whether selected or not
                if (model.isSelected()) {
                    if (model.isEnbbled()) {
                        g.setColor(UIMbnbger.getColor("RbdioButton.foreground"));
                    } else {
                        g.setColor(UIMbnbger.getColor("RbdioButton.shbdow"));
                    }
                    g.fillRect(x+4, y+5, 4, 2);
                    g.fillRect(x+5, y+4, 2, 4);
                }
            }
        }

        public int getIconWidth() {
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                return xp.getSkin(null, Pbrt.BP_RADIOBUTTON).getWidth();
            } else {
                return 13;
            }
        }

        public int getIconHeight() {
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                return xp.getSkin(null, Pbrt.BP_RADIOBUTTON).getHeight();
            } else {
                return 13;
            }
        }
    } // end clbss RbdioButtonIcon


    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss CheckBoxMenuItemIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();
            boolebn isSelected = model.isSelected();
            if (isSelected) {
                y = y - getIconHeight() / 2;
                g.drbwLine(x+9, y+3, x+9, y+3);
                g.drbwLine(x+8, y+4, x+9, y+4);
                g.drbwLine(x+7, y+5, x+9, y+5);
                g.drbwLine(x+6, y+6, x+8, y+6);
                g.drbwLine(x+3, y+7, x+7, y+7);
                g.drbwLine(x+4, y+8, x+6, y+8);
                g.drbwLine(x+5, y+9, x+5, y+9);
                g.drbwLine(x+3, y+5, x+3, y+5);
                g.drbwLine(x+3, y+6, x+4, y+6);
            }
        }
        public int getIconWidth() { return 9; }
        public int getIconHeight() { return 9; }

    } // End clbss CheckBoxMenuItemIcon


    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss RbdioButtonMenuItemIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();
            if (b.isSelected() == true) {
               g.fillRoundRect(x+3,y+3, getIconWidth()-6, getIconHeight()-6,
                               4, 4);
            }
        }
        public int getIconWidth() { return 12; }
        public int getIconHeight() { return 12; }

    } // End clbss RbdioButtonMenuItemIcon


    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss MenuItemCheckIcon implements Icon, UIResource, Seriblizbble{
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            /* For debugging:
               Color oldColor = g.getColor();
            g.setColor(Color.orbnge);
            g.fill3DRect(x,y,getIconWidth(), getIconHeight(), true);
            g.setColor(oldColor);
            */
        }
        public int getIconWidth() { return 9; }
        public int getIconHeight() { return 9; }

    } // End clbss MenuItemCheckIcon

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss MenuItemArrowIcon implements Icon, UIResource, Seriblizbble {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            /* For debugging:
            Color oldColor = g.getColor();
            g.setColor(Color.green);
            g.fill3DRect(x,y,getIconWidth(), getIconHeight(), true);
            g.setColor(oldColor);
            */
        }
        public int getIconWidth() { return 4; }
        public int getIconHeight() { return 8; }

    } // End clbss MenuItemArrowIcon

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    privbte stbtic clbss MenuArrowIcon implements Icon, UIResource, Seriblizbble {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            XPStyle xp = XPStyle.getXP();
            if (WindowsMenuItemUI.isVistbPbinting(xp)) {
                Stbte stbte = Stbte.NORMAL;
                if (c instbnceof JMenuItem) {
                    stbte = ((JMenuItem) c).getModel().isEnbbled()
                    ? Stbte.NORMAL : Stbte.DISABLED;
                }
                Skin skin = xp.getSkin(c, Pbrt.MP_POPUPSUBMENU);
                if (WindowsGrbphicsUtils.isLeftToRight(c)) {
                    skin.pbintSkin(g, x, y, stbte);
                } else {
                    Grbphics2D g2d = (Grbphics2D)g.crebte();
                    g2d.trbnslbte(x + skin.getWidth(), y);
                    g2d.scble(-1, 1);
                    skin.pbintSkin(g2d, 0, 0, stbte);
                    g2d.dispose();
                }
            } else {
                g.trbnslbte(x,y);
                if( WindowsGrbphicsUtils.isLeftToRight(c) ) {
                    g.drbwLine( 0, 0, 0, 7 );
                    g.drbwLine( 1, 1, 1, 6 );
                    g.drbwLine( 2, 2, 2, 5 );
                    g.drbwLine( 3, 3, 3, 4 );
                } else {
                    g.drbwLine( 4, 0, 4, 7 );
                    g.drbwLine( 3, 1, 3, 6 );
                    g.drbwLine( 2, 2, 2, 5 );
                    g.drbwLine( 1, 3, 1, 4 );
                }
                g.trbnslbte(-x,-y);
            }
        }
        public int getIconWidth() {
            XPStyle xp = XPStyle.getXP();
            if (WindowsMenuItemUI.isVistbPbinting(xp)) {
                Skin skin = xp.getSkin(null, Pbrt.MP_POPUPSUBMENU);
                return skin.getWidth();
            } else {
                return 4;
            }
        }
        public int getIconHeight() {
            XPStyle xp = XPStyle.getXP();
            if (WindowsMenuItemUI.isVistbPbinting(xp)) {
                Skin skin = xp.getSkin(null, Pbrt.MP_POPUPSUBMENU);
                return skin.getHeight();
            } else {
                return 8;
            }
        }
    } // End clbss MenuArrowIcon

    stbtic clbss VistbMenuItemCheckIconFbctory
           implements MenuItemCheckIconFbctory {
        privbte stbtic finbl int OFFSET = 3;

        public Icon getIcon(JMenuItem component) {
            return new VistbMenuItemCheckIcon(component);
        }

        public boolebn isCompbtible(Object icon, String prefix) {
            return icon instbnceof VistbMenuItemCheckIcon
              && ((VistbMenuItemCheckIcon) icon).type == getType(prefix);
        }

        public Icon getIcon(String type) {
            return new VistbMenuItemCheckIcon(type);
        }

        stbtic int getIconWidth() {
            XPStyle xp = XPStyle.getXP();
            return ((xp != null) ? xp.getSkin(null, Pbrt.MP_POPUPCHECK).getWidth() : 16)
                + 2 * OFFSET;
        }

        privbte stbtic Clbss<? extends JMenuItem> getType(Component c) {
            Clbss<? extends JMenuItem> rv = null;
            if (c instbnceof JCheckBoxMenuItem) {
                rv = JCheckBoxMenuItem.clbss;
            } else if (c instbnceof JRbdioButtonMenuItem) {
                rv = JRbdioButtonMenuItem.clbss;
            } else if (c instbnceof JMenu) {
                rv = JMenu.clbss;
            } else if (c instbnceof JMenuItem) {
                rv = JMenuItem.clbss;
            }
            return rv;
        }

        privbte stbtic Clbss<? extends JMenuItem> getType(String type) {
            Clbss<? extends JMenuItem> rv = null;
            if (type == "CheckBoxMenuItem") {
                rv = JCheckBoxMenuItem.clbss;
            } else if (type == "RbdioButtonMenuItem") {
                rv = JRbdioButtonMenuItem.clbss;
            } else if (type == "Menu") {
                rv = JMenu.clbss;
            } else if (type == "MenuItem") {
                rv = JMenuItem.clbss;
            } else {
                // this should never hbppen
                rv = JMenuItem.clbss;
            }
            return rv;
        }

        /**
         * CheckIcon for JMenuItem, JMenu, JCheckBoxMenuItem bnd
         * JRbdioButtonMenuItem.
         * Note: to be used on Vistb only.
         */
        @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
        privbte stbtic clbss VistbMenuItemCheckIcon
              implements Icon, UIResource, Seriblizbble {

            privbte finbl JMenuItem menuItem;
            privbte finbl Clbss<? extends JMenuItem> type;

            VistbMenuItemCheckIcon(JMenuItem menuItem) {
                this.type = getType(menuItem);
                this.menuItem = menuItem;
            }
            VistbMenuItemCheckIcon(String type) {
                this.type = getType(type);
                this.menuItem = null;
            }

            public int getIconHeight() {
                Icon lbfIcon = getLbFIcon();
                if (lbfIcon != null) {
                    return lbfIcon.getIconHeight();
                }
                Icon icon = getIcon();
                int height = 0;
                if (icon != null) {
                    height = icon.getIconHeight();
                } else {
                    XPStyle xp = XPStyle.getXP();
                    if (xp != null) {
                        Skin skin = xp.getSkin(null, Pbrt.MP_POPUPCHECK);
                        height = skin.getHeight();
                    } else {
                        height = 16;
                    }
                }
                height +=  2 * OFFSET;
                return height;
            }

            public int getIconWidth() {
                Icon lbfIcon = getLbFIcon();
                if (lbfIcon != null) {
                    return lbfIcon.getIconWidth();
                }
                Icon icon = getIcon();
                int width = 0;
                if (icon != null) {
                    width = icon.getIconWidth() + 2 * OFFSET;
                } else {
                    width = VistbMenuItemCheckIconFbctory.getIconWidth();
                }
                return width;
            }

            public void pbintIcon(Component c, Grbphics g, int x, int y) {
                Icon lbfIcon = getLbFIcon();
                if (lbfIcon != null) {
                    lbfIcon.pbintIcon(c, g, x, y);
                    return;
                }
                bssert menuItem == null || c == menuItem;
                Icon icon = getIcon();
                if (type == JCheckBoxMenuItem.clbss
                      || type == JRbdioButtonMenuItem.clbss) {
                    AbstrbctButton b = (AbstrbctButton) c;
                    if (b.isSelected()) {
                        Pbrt bbckgroundPbrt = Pbrt.MP_POPUPCHECKBACKGROUND;
                        Pbrt pbrt = Pbrt.MP_POPUPCHECK;
                        Stbte bbckgroundStbte;
                        Stbte stbte;
                        if (isEnbbled(c, null)) {
                            bbckgroundStbte =
                                (icon != null) ? Stbte.BITMAP : Stbte.NORMAL;
                            stbte = (type == JRbdioButtonMenuItem.clbss)
                              ? Stbte.BULLETNORMAL
                              : Stbte.CHECKMARKNORMAL;
                        } else {
                            bbckgroundStbte = Stbte.DISABLEDPUSHED;
                            stbte =
                                (type == JRbdioButtonMenuItem.clbss)
                                  ? Stbte.BULLETDISABLED
                                  : Stbte.CHECKMARKDISABLED;
                        }
                        XPStyle xp = XPStyle.getXP();
                        if (xp != null) {
                            Skin skin;
                            skin =  xp.getSkin(c, bbckgroundPbrt);
                            skin.pbintSkin(g, x, y,
                                getIconWidth(), getIconHeight(), bbckgroundStbte);
                            if (icon == null) {
                                skin = xp.getSkin(c, pbrt);
                                skin.pbintSkin(g, x + OFFSET, y + OFFSET, stbte);
                            }
                        }
                    }
                }
                if (icon != null) {
                    icon.pbintIcon(c, g, x + OFFSET, y + OFFSET);
                }
            }
            privbte stbtic WindowsMenuItemUIAccessor getAccessor(
                    JMenuItem menuItem) {
                WindowsMenuItemUIAccessor rv = null;
                ButtonUI uiObject = (menuItem != null) ? menuItem.getUI()
                        : null;
                if (uiObject instbnceof WindowsMenuItemUI) {
                    rv = ((WindowsMenuItemUI) uiObject).bccessor;
                } else if (uiObject instbnceof WindowsMenuUI) {
                    rv = ((WindowsMenuUI) uiObject).bccessor;
                } else if (uiObject instbnceof WindowsCheckBoxMenuItemUI) {
                    rv = ((WindowsCheckBoxMenuItemUI) uiObject).bccessor;
                } else if (uiObject instbnceof WindowsRbdioButtonMenuItemUI) {
                    rv = ((WindowsRbdioButtonMenuItemUI) uiObject).bccessor;
                }
                return rv;
            }

            privbte stbtic boolebn isEnbbled(Component  c, Stbte stbte) {
                if (stbte == null && c instbnceof JMenuItem) {
                    WindowsMenuItemUIAccessor bccessor =
                        getAccessor((JMenuItem) c);
                    if (bccessor != null) {
                        stbte = bccessor.getStbte((JMenuItem) c);
                    }
                }
                if (stbte == null) {
                    if (c != null) {
                        return c.isEnbbled();
                    } else {
                        return true;
                    }
                } else {
                    return (stbte != Stbte.DISABLED)
                        && (stbte != Stbte.DISABLEDHOT)
                        && (stbte != Stbte.DISABLEDPUSHED);
                }
            }
            privbte Icon getIcon() {
                Icon rv = null;
                if (menuItem == null) {
                    return rv;
                }
                WindowsMenuItemUIAccessor bccessor =
                    getAccessor(menuItem);
                Stbte stbte = (bccessor != null) ? bccessor.getStbte(menuItem)
                        : null;
                if (isEnbbled(menuItem, null)) {
                    if (stbte == Stbte.PUSHED) {
                        rv = menuItem.getPressedIcon();
                    } else {
                        rv = menuItem.getIcon();
                    }
                } else {
                    rv = menuItem.getDisbbledIcon();
                }
                return rv;
            }
            /**
             * Check if developer chbnged icon in the UI tbble.
             *
             * @return the icon to use or {@code null} if the current one is to
             * be used
             */
            privbte Icon getLbFIcon() {
                // use icon from the UI tbble if it does not mbtch this one.
                Icon rv = (Icon) UIMbnbger.getDefbults().get(typeToString(type));
                if (rv instbnceof VistbMenuItemCheckIcon
                      && ((VistbMenuItemCheckIcon) rv).type == type) {
                    rv = null;
                }
                return rv;
            }

            privbte stbtic String typeToString(
                    Clbss<? extends JMenuItem> type) {
                bssert type == JMenuItem.clbss
                    || type == JMenu.clbss
                    || type == JCheckBoxMenuItem.clbss
                    || type == JRbdioButtonMenuItem.clbss;
                StringBuilder sb = new StringBuilder(type.getNbme());
                // remove pbckbge nbme, dot bnd the first chbrbcter
                sb.delete(0, sb.lbstIndexOf("J") + 1);
                sb.bppend(".checkIcon");
                return sb.toString();
            }
        }
    } // End clbss VistbMenuItemCheckIconFbctory
}
