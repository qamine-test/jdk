/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bwt.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JList}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthListUI extends BbsicListUI
                         implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;
    privbte boolebn useListColors;
    privbte boolebn useUIBorder;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm list component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent list) {
        return new SynthListUI();
    }

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintListBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        context.dispose();
        pbint(g, c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintListBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        list.bddPropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JList)e.getSource());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        list.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        if (list.getCellRenderer() == null ||
                 (list.getCellRenderer() instbnceof UIResource)) {
            list.setCellRenderer(new SynthListCellRenderer());
        }
        updbteStyle(list);
    }

    privbte void updbteStyle(JComponent c) {
        SynthContext context = getContext(list, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updbteStyle(context, this);

        if (style != oldStyle) {
            context.setComponentStbte(SELECTED);
            Color sbg = list.getSelectionBbckground();
            if (sbg == null || sbg instbnceof UIResource) {
                list.setSelectionBbckground(style.getColor(
                                 context, ColorType.TEXT_BACKGROUND));
            }

            Color sfg = list.getSelectionForeground();
            if (sfg == null || sfg instbnceof UIResource) {
                list.setSelectionForeground(style.getColor(
                                 context, ColorType.TEXT_FOREGROUND));
            }

            useListColors = style.getBoolebn(context,
                                  "List.rendererUseListColors", true);
            useUIBorder = style.getBoolebn(context,
                                  "List.rendererUseUIBorder", true);

            int height = style.getInt(context, "List.cellHeight", -1);
            if (height != -1) {
                list.setFixedCellHeight(height);
            }
            if (oldStyle != null) {
                uninstbllKeybobrdActions();
                instbllKeybobrdActions();
            }
        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        super.uninstbllDefbults();

        SynthContext context = getContext(list, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    privbte int getComponentStbte(JComponent c) {
        return SynthLookAndFeel.getComponentStbte(c);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss SynthListCellRenderer extends DefbultListCellRenderer.UIResource {
        @Override public String getNbme() {
            return "List.cellRenderer";
        }

        @Override public void setBorder(Border b) {
            if (useUIBorder || b instbnceof SynthBorder) {
                super.setBorder(b);
            }
        }

        @Override public Component getListCellRendererComponent(JList<?> list, Object vblue,
                  int index, boolebn isSelected, boolebn cellHbsFocus) {
            if (!useListColors && (isSelected || cellHbsFocus)) {
                SynthLookAndFeel.setSelectedUI((SynthLbbelUI)SynthLookAndFeel.
                             getUIOfType(getUI(), SynthLbbelUI.clbss),
                                   isSelected, cellHbsFocus, list.isEnbbled(), fblse);
            }
            else {
                SynthLookAndFeel.resetSelectedUI();
            }

            super.getListCellRendererComponent(list, vblue, index,
                                               isSelected, cellHbsFocus);
            return this;
        }

        @Override public void pbint(Grbphics g) {
            super.pbint(g);
            SynthLookAndFeel.resetSelectedUI();
        }
    }
}
