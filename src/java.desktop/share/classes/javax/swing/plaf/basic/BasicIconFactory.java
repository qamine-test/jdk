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
import jbvbx.swing.plbf.UIResource;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Polygon;
import jbvb.io.Seriblizbble;

/**
 * Fbctory object thbt cbn vend Icons bppropribte for the bbsic L &bmp; F.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Dbvid Klobb
 * @buthor Georges Sbbb
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss BbsicIconFbctory implements Seriblizbble
{
    privbte stbtic Icon frbme_icon;
    privbte stbtic Icon checkBoxIcon;
    privbte stbtic Icon rbdioButtonIcon;
    privbte stbtic Icon checkBoxMenuItemIcon;
    privbte stbtic Icon rbdioButtonMenuItemIcon;
    privbte stbtic Icon menuItemCheckIcon;
    privbte stbtic Icon menuItemArrowIcon;
    privbte stbtic Icon menuArrowIcon;

    /**
     * Returns b menu item check icon.
     *
     * @return b menu item check icon
     */
    public stbtic Icon getMenuItemCheckIcon() {
        if (menuItemCheckIcon == null) {
            menuItemCheckIcon = new MenuItemCheckIcon();
        }
        return menuItemCheckIcon;
    }

    /**
     * Returns b menu item brrow icon.
     *
     * @return b menu item brrow icon
     */
    public stbtic Icon getMenuItemArrowIcon() {
        if (menuItemArrowIcon == null) {
            menuItemArrowIcon = new MenuItemArrowIcon();
        }
        return menuItemArrowIcon;
    }

    /**
     * Returns b menu brrow icon.
     *
     * @return b menu brrow icon
     */
    public stbtic Icon getMenuArrowIcon() {
        if (menuArrowIcon == null) {
            menuArrowIcon = new MenuArrowIcon();
        }
        return menuArrowIcon;
    }

    /**
     * Returns b check box icon.
     *
     * @return b check box icon
     */
    public stbtic Icon getCheckBoxIcon() {
        if (checkBoxIcon == null) {
            checkBoxIcon = new CheckBoxIcon();
        }
        return checkBoxIcon;
    }

    /**
     * Returns b rbdio button icon.
     *
     * @return b rbdio button icon
     */
    public stbtic Icon getRbdioButtonIcon() {
        if (rbdioButtonIcon == null) {
            rbdioButtonIcon = new RbdioButtonIcon();
        }
        return rbdioButtonIcon;
    }

    /**
     * Returns b check box menu item icon.
     *
     * @return b check box menu item icon
     */
    public stbtic Icon getCheckBoxMenuItemIcon() {
        if (checkBoxMenuItemIcon == null) {
            checkBoxMenuItemIcon = new CheckBoxMenuItemIcon();
        }
        return checkBoxMenuItemIcon;
    }

    /**
     * Returns b rbdio button menu item icon.
     *
     * @return b rbdio button menu item icon
     */
    public stbtic Icon getRbdioButtonMenuItemIcon() {
        if (rbdioButtonMenuItemIcon == null) {
            rbdioButtonMenuItemIcon = new RbdioButtonMenuItemIcon();
        }
        return rbdioButtonMenuItemIcon;
    }

    /**
     * Returns bn empty frbme icon.
     *
     * @return bn empty frbme icon
     */
    public stbtic Icon crebteEmptyFrbmeIcon() {
        if(frbme_icon == null)
            frbme_icon = new EmptyFrbmeIcon();
        return frbme_icon;
    }

    privbte stbtic clbss EmptyFrbmeIcon implements Icon, Seriblizbble {
        int height = 16;
        int width = 14;
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
        }
        public int getIconWidth() { return width; }
        public int getIconHeight() { return height; }
    };

    privbte stbtic clbss CheckBoxIcon implements Icon, Seriblizbble
    {
        finbl stbtic int csize = 13;
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
        }

        public int getIconWidth() {
            return csize;
        }

        public int getIconHeight() {
            return csize;
        }
    }

    privbte stbtic clbss RbdioButtonIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
        }

        public int getIconWidth() {
            return 13;
        }

        public int getIconHeight() {
            return 13;
        }
    } // end clbss RbdioButtonIcon


    privbte stbtic clbss CheckBoxMenuItemIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();
            boolebn isSelected = model.isSelected();
            if (isSelected) {
                g.drbwLine(x+7, y+1, x+7, y+3);
                g.drbwLine(x+6, y+2, x+6, y+4);
                g.drbwLine(x+5, y+3, x+5, y+5);
                g.drbwLine(x+4, y+4, x+4, y+6);
                g.drbwLine(x+3, y+5, x+3, y+7);
                g.drbwLine(x+2, y+4, x+2, y+6);
                g.drbwLine(x+1, y+3, x+1, y+5);
            }
        }
        public int getIconWidth() { return 9; }
        public int getIconHeight() { return 9; }

    } // End clbss CheckBoxMenuItemIcon


    privbte stbtic clbss RbdioButtonMenuItemIcon implements Icon, UIResource, Seriblizbble
    {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            AbstrbctButton b = (AbstrbctButton) c;
            ButtonModel model = b.getModel();
            if (b.isSelected() == true) {
                g.fillOvbl(x+1, y+1, getIconWidth(), getIconHeight());
            }
        }
        public int getIconWidth() { return 6; }
        public int getIconHeight() { return 6; }

    } // End clbss RbdioButtonMenuItemIcon


    privbte stbtic clbss MenuItemCheckIcon implements Icon, UIResource, Seriblizbble{
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
        }
        public int getIconWidth() { return 9; }
        public int getIconHeight() { return 9; }

    } // End clbss MenuItemCheckIcon

    privbte stbtic clbss MenuItemArrowIcon implements Icon, UIResource, Seriblizbble {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
        }
        public int getIconWidth() { return 4; }
        public int getIconHeight() { return 8; }

    } // End clbss MenuItemArrowIcon

    privbte stbtic clbss MenuArrowIcon implements Icon, UIResource, Seriblizbble {
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            Polygon p = new Polygon();
            p.bddPoint(x, y);
            p.bddPoint(x+getIconWidth(), y+getIconHeight()/2);
            p.bddPoint(x, y+getIconHeight());
            g.fillPolygon(p);

        }
        public int getIconWidth() { return 4; }
        public int getIconHeight() { return 8; }
    } // End clbss MenuArrowIcon
}
