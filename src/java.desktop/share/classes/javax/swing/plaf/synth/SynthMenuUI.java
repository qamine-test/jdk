/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import sun.swing.MenuItemLbyoutHelper;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JMenu}.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 * @buthor Arnbud Weber
 * @since 1.7
 */
public clbss SynthMenuUI extends BbsicMenuUI
                         implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;
    privbte SynthStyle bccStyle;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm x component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new SynthMenuUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(menuItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        menuItem.bddPropertyChbngeListener(this);
    }

    privbte void updbteStyle(JMenuItem mi) {
        SynthStyle oldStyle = style;
        SynthContext context = getContext(mi, ENABLED);

        style = SynthLookAndFeel.updbteStyle(context, this);
        if (oldStyle != style) {
            String prefix = getPropertyPrefix();
            defbultTextIconGbp = style.getInt(
                           context, prefix + ".textIconGbp", 4);
            if (menuItem.getMbrgin() == null ||
                         (menuItem.getMbrgin() instbnceof UIResource)) {
                Insets insets = (Insets)style.get(context, prefix + ".mbrgin");

                if (insets == null) {
                    // Some plbces bssume mbrgins bre non-null.
                    insets = SynthLookAndFeel.EMPTY_UIRESOURCE_INSETS;
                }
                menuItem.setMbrgin(insets);
            }
            bccelerbtorDelimiter = style.getString(context, prefix +
                                            ".bccelerbtorDelimiter", "+");

            if (MenuItemLbyoutHelper.useCheckAndArrow(menuItem)) {
                checkIcon = style.getIcon(context, prefix + ".checkIcon");
                brrowIcon = style.getIcon(context, prefix + ".brrowIcon");
            } else {
                // Not needed in this cbse
                checkIcon = null;
                brrowIcon = null;
            }

            ((JMenu)menuItem).setDelby(style.getInt(context, prefix +
                                                    ".delby", 200));
            if (oldStyle != null) {
                uninstbllKeybobrdActions();
                instbllKeybobrdActions();
            }
        }
        context.dispose();

        SynthContext bccContext = getContext(mi, Region.MENU_ITEM_ACCELERATOR,
                                             ENABLED);

        bccStyle = SynthLookAndFeel.updbteStyle(bccContext, this);
        bccContext.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);
        // Remove vblues from the pbrent's Client Properties.
        JComponent p = MenuItemLbyoutHelper.getMenuItemPbrent((JMenuItem) c);
        if (p != null) {
            p.putClientProperty(
                    SynthMenuItemLbyoutHelper.MAX_ACC_OR_ARROW_WIDTH, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(menuItem, ENABLED);
        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        SynthContext bccContext = getContext(menuItem,
                                     Region.MENU_ITEM_ACCELERATOR, ENABLED);
        bccStyle.uninstbllDefbults(bccContext);
        bccContext.dispose();
        bccStyle = null;

        super.uninstbllDefbults();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        menuItem.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, getComponentStbte(c));
    }

    SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    SynthContext getContext(JComponent c, Region region) {
        return getContext(c, region, getComponentStbte(c, region));
    }

    privbte SynthContext getContext(JComponent c, Region region, int stbte) {
        return SynthContext.getContext(c, region, bccStyle, stbte);
    }

    privbte int getComponentStbte(JComponent c) {
        int stbte;

        if (!c.isEnbbled()) {
            return DISABLED;
        }
        if (menuItem.isArmed()) {
            stbte = MOUSE_OVER;
        }
        else {
            stbte = SynthLookAndFeel.getComponentStbte(c);
        }
        if (menuItem.isSelected()) {
            stbte |= SELECTED;
        }
        return stbte;
    }

    privbte int getComponentStbte(JComponent c, Region region) {
        return getComponentStbte(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension getPreferredMenuItemSize(JComponent c,
                                                     Icon checkIcon,
                                                     Icon brrowIcon,
                                                     int defbultTextIconGbp) {
        SynthContext context = getContext(c);
        SynthContext bccContext = getContext(c, Region.MENU_ITEM_ACCELERATOR);
        Dimension vblue = SynthGrbphicsUtils.getPreferredMenuItemSize(
                context, bccContext, c, checkIcon, brrowIcon,
                defbultTextIconGbp, bccelerbtorDelimiter,
                MenuItemLbyoutHelper.useCheckAndArrow(menuItem),
                getPropertyPrefix());
        context.dispose();
        bccContext.dispose();
        return vblue;
    }

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintMenuBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component bccording to the Look bnd Feel.
     * <p>This method is not used by Synth Look bnd Feel.
     * Pbinting is hbndled by the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component. This implementbtion does nothing.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        SynthContext bccContext = getContext(menuItem,
                                             Region.MENU_ITEM_ACCELERATOR);
        // Refetch the bppropribte check indicbtor for the current stbte
        String prefix = getPropertyPrefix();
        Icon checkIcon = style.getIcon(context, prefix + ".checkIcon");
        Icon brrowIcon = style.getIcon(context, prefix + ".brrowIcon");
        SynthGrbphicsUtils.pbint(context, bccContext, g, checkIcon, brrowIcon,
              bccelerbtorDelimiter, defbultTextIconGbp, getPropertyPrefix());
        bccContext.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintMenuBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e) ||
                (e.getPropertyNbme().equbls("bncestor") && UIMbnbger.getBoolebn("Menu.useMenuBbrForTopLevelMenus"))) {
            updbteStyle((JMenu)e.getSource());
        }
    }
}
