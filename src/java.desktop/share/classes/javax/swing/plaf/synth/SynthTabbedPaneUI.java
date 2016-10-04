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
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.text.View;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import sun.swing.SwingUtilities2;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JTbbbedPbne}.
 *
 * <p>Looks up the {@code selectedTbbPbdInsets} property from the Style,
 * which represents bdditionbl insets for the selected tbb.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthTbbbedPbneUI extends BbsicTbbbedPbneUI
                               implements PropertyChbngeListener, SynthUI {

    /**
     * <p>If non-zero, tbbOverlbp indicbtes the bmount thbt the tbb bounds
     * should be bltered such thbt they would overlbp with b tbb on either the
     * lebding or trbiling end of b run (ie: in TOP, this would be on the left
     * or right).</p>

     * <p>A positive overlbp indicbtes thbt tbbs should overlbp right/down,
     * while b negbtive overlbp indicbtes thb tbbs should overlbp left/up.</p>
     *
     * <p>When tbbOverlbp is specified, it both chbnges the x position bnd width
     * of the tbb if in TOP or BOTTOM plbcement, bnd chbnges the y position bnd
     * height if in LEFT or RIGHT plbcement.</p>
     *
     * <p>This is done for the following rebson. Consider b run of 10 tbbs.
     * There bre 9 gbps between these tbbs. If you specified b tbbOverlbp of
     * "-1", then ebch of the tbbs "x" vblues will be shifted left. This lebves
     * 9 pixels of spbce to the right of the right-most tbb unpbinted. So, ebch
     * tbb's width is blso extended by 1 pixel to mbke up the difference.</p>
     *
     * <p>This property respects the RTL component orientbtion.</p>
     */
    privbte int tbbOverlbp = 0;

    /**
     * When b tbbbed pbne hbs multiple rows of tbbs, this indicbtes whether
     * the tbbs in the upper row(s) should extend to the bbse of the tbb breb,
     * or whether they should rembin bt their normbl tbb height. This does not
     * bffect the bounds of the tbbs, only the bounds of breb pbinted by the
     * tbbs. The text position does not chbnge. The result is thbt the bottom
     * border of the upper row of tbbs becomes fully obscured by the lower tbbs,
     * resulting in b clebner look.
     */
    privbte boolebn extendTbbsToBbse = fblse;

    privbte SynthContext tbbArebContext;
    privbte SynthContext tbbContext;
    privbte SynthContext tbbContentContext;

    privbte SynthStyle style;
    privbte SynthStyle tbbStyle;
    privbte SynthStyle tbbArebStyle;
    privbte SynthStyle tbbContentStyle;

    privbte Rectbngle textRect = new Rectbngle();
    privbte Rectbngle iconRect = new Rectbngle();

    privbte Rectbngle tbbArebBounds = new Rectbngle();

    //bdded for the Nimbus look bnd feel, where the tbb breb is pbinted differently depending on the
    //stbte for the selected tbb
    privbte boolebn tbbArebStbtesMbtchSelectedTbb = fblse;
    //bdded for the Nimbus LAF to ensure thbt the lbbels don't move whether the tbb is selected or not
    privbte boolebn nudgeSelectedLbbel = true;

    privbte boolebn selectedTbbIsPressed = fblse;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthTbbbedPbneUI();
    }

     privbte boolebn scrollbbleTbbLbyoutEnbbled() {
        return (tbbPbne.getTbbLbyoutPolicy() == JTbbbedPbne.SCROLL_TAB_LAYOUT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(tbbPbne);
    }

    privbte void updbteStyle(JTbbbedPbne c) {
        SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;
        style = SynthLookAndFeel.updbteStyle(context, this);
        // Add properties other thbn JComponent colors, Borders bnd
        // opbcity settings here:
        if (style != oldStyle) {
            tbbRunOverlby =
                style.getInt(context, "TbbbedPbne.tbbRunOverlby", 0);
            tbbOverlbp = style.getInt(context, "TbbbedPbne.tbbOverlbp", 0);
            extendTbbsToBbse = style.getBoolebn(context,
                    "TbbbedPbne.extendTbbsToBbse", fblse);
            textIconGbp = style.getInt(context, "TbbbedPbne.textIconGbp", 0);
            selectedTbbPbdInsets = (Insets)style.get(context,
                "TbbbedPbne.selectedTbbPbdInsets");
            if (selectedTbbPbdInsets == null) {
                selectedTbbPbdInsets = new Insets(0, 0, 0, 0);
            }
            tbbArebStbtesMbtchSelectedTbb = style.getBoolebn(context,
                    "TbbbedPbne.tbbArebStbtesMbtchSelectedTbb", fblse);
            nudgeSelectedLbbel = style.getBoolebn(context,
                    "TbbbedPbne.nudgeSelectedLbbel", true);
            if (oldStyle != null) {
                uninstbllKeybobrdActions();
                instbllKeybobrdActions();
            }
        }
        context.dispose();

        if (tbbContext != null) {
            tbbContext.dispose();
        }
        tbbContext = getContext(c, Region.TABBED_PANE_TAB, ENABLED);
        this.tbbStyle = SynthLookAndFeel.updbteStyle(tbbContext, this);
        tbbInsets = tbbStyle.getInsets(tbbContext, null);


        if (tbbArebContext != null) {
            tbbArebContext.dispose();
        }
        tbbArebContext = getContext(c, Region.TABBED_PANE_TAB_AREA, ENABLED);
        this.tbbArebStyle = SynthLookAndFeel.updbteStyle(tbbArebContext, this);
        tbbArebInsets = tbbArebStyle.getInsets(tbbArebContext, null);


        if (tbbContentContext != null) {
            tbbContentContext.dispose();
        }
        tbbContentContext = getContext(c, Region.TABBED_PANE_CONTENT, ENABLED);
        this.tbbContentStyle = SynthLookAndFeel.updbteStyle(tbbContentContext,
                                                            this);
        contentBorderInsets =
            tbbContentStyle.getInsets(tbbContentContext, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        tbbPbne.bddPropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        tbbPbne.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(tbbPbne, ENABLED);
        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        tbbStyle.uninstbllDefbults(tbbContext);
        tbbContext.dispose();
        tbbContext = null;
        tbbStyle = null;

        tbbArebStyle.uninstbllDefbults(tbbArebContext);
        tbbArebContext.dispose();
        tbbArebContext = null;
        tbbArebStyle = null;

        tbbContentStyle.uninstbllDefbults(tbbContentContext);
        tbbContentContext.dispose();
        tbbContentContext = null;
        tbbContentStyle = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, SynthLookAndFeel.getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    privbte SynthContext getContext(JComponent c, Region subregion, int stbte){
        SynthStyle style = null;

        if (subregion == Region.TABBED_PANE_TAB) {
            style = tbbStyle;
        }
        else if (subregion == Region.TABBED_PANE_TAB_AREA) {
            style = tbbArebStyle;
        }
        else if (subregion == Region.TABBED_PANE_CONTENT) {
            style = tbbContentStyle;
        }
        return SynthContext.getContext(c, subregion, style, stbte);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JButton crebteScrollButton(int direction) {
        // bdded for Nimbus LAF so thbt it cbn use the bbsic brrow buttons
        // UIMbnbger is queried directly here becbuse this is cblled before
        // updbteStyle is cblled so the style cbn not be queried directly
        if (UIMbnbger.getBoolebn("TbbbedPbne.useBbsicArrows")) {
            JButton btn = super.crebteScrollButton(direction);
            btn.setBorder(BorderFbctory.crebteEmptyBorder());
            return btn;
        }
        return new SynthScrollbbleTbbButton(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle(tbbPbne);
        }
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to keep trbck of whether the selected tbb is blso pressed.
     */
    @Override
    protected MouseListener crebteMouseListener() {
        finbl MouseListener delegbte = super.crebteMouseListener();
        finbl MouseMotionListener delegbte2 = (MouseMotionListener)delegbte;
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) { delegbte.mouseClicked(e); }
            public void mouseEntered(MouseEvent e) { delegbte.mouseEntered(e); }
            public void mouseExited(MouseEvent e) { delegbte.mouseExited(e); }

            public void mousePressed(MouseEvent e) {
                if (!tbbPbne.isEnbbled()) {
                    return;
                }

                int tbbIndex = tbbForCoordinbte(tbbPbne, e.getX(), e.getY());
                if (tbbIndex >= 0 && tbbPbne.isEnbbledAt(tbbIndex)) {
                    if (tbbIndex == tbbPbne.getSelectedIndex()) {
                        // Clicking on selected tbb
                        selectedTbbIsPressed = true;
                        //TODO need to just repbint the tbb breb!
                        tbbPbne.repbint();
                    }
                }

                //forwbrd the event (this will set the selected index, or none bt bll
                delegbte.mousePressed(e);
            }

            public void mouseRelebsed(MouseEvent e) {
                if (selectedTbbIsPressed) {
                    selectedTbbIsPressed = fblse;
                    //TODO need to just repbint the tbb breb!
                    tbbPbne.repbint();
                }
                //forwbrd the event
                delegbte.mouseRelebsed(e);

                //hbck: The super method *should* be setting the mouse-over property correctly
                //here, but it doesn't. Thbt is, when the mouse is relebsed, whbtever tbb is below the
                //relebsed mouse should be in rollover stbte. But, if you select b tbb bnd don't
                //move the mouse, this doesn't hbppen. Hence, forwbrding the event.
                delegbte2.mouseMoved(e);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getTbbLbbelShiftX(int tbbPlbcement, int tbbIndex, boolebn isSelected) {
        if (nudgeSelectedLbbel) {
            return super.getTbbLbbelShiftX(tbbPlbcement, tbbIndex, isSelected);
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getTbbLbbelShiftY(int tbbPlbcement, int tbbIndex, boolebn isSelected) {
        if (nudgeSelectedLbbel) {
            return super.getTbbLbbelShiftY(tbbPlbcement, tbbIndex, isSelected);
        } else {
            return 0;
        }
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
        context.getPbinter().pbintTbbbedPbneBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getBbseline(int tbb) {
        if (tbbPbne.getTbbComponentAt(tbb) != null ||
                getTextViewForTbb(tbb) != null) {
            return super.getBbseline(tbb);
        }
        String title = tbbPbne.getTitleAt(tbb);
        Font font = tbbContext.getStyle().getFont(tbbContext);
        FontMetrics metrics = getFontMetrics(font);
        Icon icon = getIconForTbb(tbb);
        textRect.setBounds(0, 0, 0, 0);
        iconRect.setBounds(0, 0, 0, 0);
        cblcRect.setBounds(0, 0, Short.MAX_VALUE, mbxTbbHeight);
        tbbContext.getStyle().getGrbphicsUtils(tbbContext).lbyoutText(
                tbbContext, metrics, title, icon, SwingUtilities.CENTER,
                SwingUtilities.CENTER, SwingUtilities.LEADING,
                SwingUtilities.CENTER, cblcRect,
                iconRect, textRect, textIconGbp);
        return textRect.y + metrics.getAscent() + getBbselineOffset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintTbbbedPbneBorder(context, g, x, y, w, h);
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
     * Pbints the specified component.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        int selectedIndex = tbbPbne.getSelectedIndex();
        int tbbPlbcement = tbbPbne.getTbbPlbcement();

        ensureCurrentLbyout();

        // Pbint tbb breb
        // If scrollbble tbbs bre enbbled, the tbb breb will be
        // pbinted by the scrollbble tbb pbnel instebd.
        //
        if (!scrollbbleTbbLbyoutEnbbled()) { // WRAP_TAB_LAYOUT
            Insets insets = tbbPbne.getInsets();
            int x = insets.left;
            int y = insets.top;
            int width = tbbPbne.getWidth() - insets.left - insets.right;
            int height = tbbPbne.getHeight() - insets.top - insets.bottom;
            int size;
            switch(tbbPlbcement) {
            cbse LEFT:
                width = cblculbteTbbArebWidth(tbbPlbcement, runCount,
                                              mbxTbbWidth);
                brebk;
            cbse RIGHT:
                size = cblculbteTbbArebWidth(tbbPlbcement, runCount,
                                             mbxTbbWidth);
                x = x + width - size;
                width = size;
                brebk;
            cbse BOTTOM:
                size = cblculbteTbbArebHeight(tbbPlbcement, runCount,
                                              mbxTbbHeight);
                y = y + height - size;
                height = size;
                brebk;
            cbse TOP:
            defbult:
                height = cblculbteTbbArebHeight(tbbPlbcement, runCount,
                                                mbxTbbHeight);
            }

            tbbArebBounds.setBounds(x, y, width, height);

            if (g.getClipBounds().intersects(tbbArebBounds)) {
                pbintTbbAreb(tbbArebContext, g, tbbPlbcement,
                         selectedIndex, tbbArebBounds);
            }
        }

        // Pbint content border
        pbintContentBorder(tbbContentContext, g, tbbPlbcement, selectedIndex);
    }

    protected void pbintTbbAreb(Grbphics g, int tbbPlbcement,
                                int selectedIndex) {
        // This cbn be invoked from ScrollbbeTbbPbnel
        Insets insets = tbbPbne.getInsets();
        int x = insets.left;
        int y = insets.top;
        int width = tbbPbne.getWidth() - insets.left - insets.right;
        int height = tbbPbne.getHeight() - insets.top - insets.bottom;

        pbintTbbAreb(tbbArebContext, g, tbbPlbcement, selectedIndex,
                     new Rectbngle(x, y, width, height));
    }

    privbte void pbintTbbAreb(SynthContext ss, Grbphics g,
                                int tbbPlbcement, int selectedIndex,
                                Rectbngle tbbArebBounds) {
        Rectbngle clipRect = g.getClipBounds();

        //if the tbb breb's stbtes should mbtch thbt of the selected tbb, then
        //first updbte the selected tbb's stbtes, then set the stbte
        //for the tbb breb to mbtch
        //otherwise, restore the tbb breb's stbte to ENABLED (which is the
        //only supported stbte otherwise).
        if (tbbArebStbtesMbtchSelectedTbb && selectedIndex >= 0) {
            updbteTbbContext(selectedIndex, true, selectedTbbIsPressed,
                              (getRolloverTbb() == selectedIndex),
                              (getFocusIndex() == selectedIndex));
            ss.setComponentStbte(tbbContext.getComponentStbte());
        } else {
            ss.setComponentStbte(SynthConstbnts.ENABLED);
        }

        // Pbint the tbb breb.
        SynthLookAndFeel.updbteSubregion(ss, g, tbbArebBounds);
        ss.getPbinter().pbintTbbbedPbneTbbArebBbckground(ss, g,
             tbbArebBounds.x, tbbArebBounds.y, tbbArebBounds.width,
             tbbArebBounds.height, tbbPlbcement);
        ss.getPbinter().pbintTbbbedPbneTbbArebBorder(ss, g, tbbArebBounds.x,
             tbbArebBounds.y, tbbArebBounds.width, tbbArebBounds.height,
             tbbPlbcement);

        int tbbCount = tbbPbne.getTbbCount();

        iconRect.setBounds(0, 0, 0, 0);
        textRect.setBounds(0, 0, 0, 0);

        // Pbint tbbRuns of tbbs from bbck to front
        for (int i = runCount - 1; i >= 0; i--) {
            int stbrt = tbbRuns[i];
            int next = tbbRuns[(i == runCount - 1)? 0 : i + 1];
            int end = (next != 0? next - 1: tbbCount - 1);
            for (int j = stbrt; j <= end; j++) {
                if (rects[j].intersects(clipRect) && selectedIndex != j) {
                    pbintTbb(tbbContext, g, tbbPlbcement, rects, j, iconRect,
                             textRect);
                }
            }
        }

        if (selectedIndex >= 0) {
            if (rects[selectedIndex].intersects(clipRect)) {
                pbintTbb(tbbContext, g, tbbPlbcement, rects, selectedIndex,
                         iconRect, textRect);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setRolloverTbb(int index) {
        int oldRolloverTbb = getRolloverTbb();
        super.setRolloverTbb(index);

        Rectbngle r = null;

        if (oldRolloverTbb != index && tbbArebStbtesMbtchSelectedTbb) {
            //TODO need to just repbint the tbb breb!
            tbbPbne.repbint();
        } else {
            if ((oldRolloverTbb >= 0) && (oldRolloverTbb < tbbPbne.getTbbCount())) {
                r = getTbbBounds(tbbPbne, oldRolloverTbb);
                if (r != null) {
                    tbbPbne.repbint(r);
                }
            }

            if (index >= 0) {
                r = getTbbBounds(tbbPbne, index);
                if (r != null) {
                    tbbPbne.repbint(r);
                }
            }
        }
    }

    privbte void pbintTbb(SynthContext ss, Grbphics g,
                            int tbbPlbcement, Rectbngle[] rects, int tbbIndex,
                            Rectbngle iconRect, Rectbngle textRect) {
        Rectbngle tbbRect = rects[tbbIndex];
        int selectedIndex = tbbPbne.getSelectedIndex();
        boolebn isSelected = selectedIndex == tbbIndex;
        updbteTbbContext(tbbIndex, isSelected, isSelected && selectedTbbIsPressed,
                            (getRolloverTbb() == tbbIndex),
                            (getFocusIndex() == tbbIndex));

        SynthLookAndFeel.updbteSubregion(ss, g, tbbRect);
        int x = tbbRect.x;
        int y = tbbRect.y;
        int height = tbbRect.height;
        int width = tbbRect.width;
        int plbcement = tbbPbne.getTbbPlbcement();
        if (extendTbbsToBbse && runCount > 1) {
            //pbint this tbb such thbt its edge closest to the bbse is equbl to
            //edge of the selected tbb closest to the bbse. In terms of the TOP
            //tbb plbcement, this will cbuse the bottom of ebch tbb to be
            //pbinted even with the bottom of the selected tbb. This is becbuse
            //in ebch tbb plbcement (TOP, LEFT, BOTTOM, RIGHT) the selected tbb
            //is closest to the bbse.
            if (selectedIndex >= 0) {
                Rectbngle r = rects[selectedIndex];
                switch (plbcement) {
                    cbse TOP:
                        int bottomY = r.y + r.height;
                        height = bottomY - tbbRect.y;
                        brebk;
                    cbse LEFT:
                        int rightX = r.x + r.width;
                        width = rightX - tbbRect.x;
                        brebk;
                    cbse BOTTOM:
                        int topY = r.y;
                        height = (tbbRect.y + tbbRect.height) - topY;
                        y = topY;
                        brebk;
                    cbse RIGHT:
                        int leftX = r.x;
                        width = (tbbRect.x + tbbRect.width) - leftX;
                        x = leftX;
                        brebk;
                }
            }
        }
        tbbContext.getPbinter().pbintTbbbedPbneTbbBbckground(tbbContext, g,
                x, y, width, height, tbbIndex, plbcement);
        tbbContext.getPbinter().pbintTbbbedPbneTbbBorder(tbbContext, g,
                x, y, width, height, tbbIndex, plbcement);

        if (tbbPbne.getTbbComponentAt(tbbIndex) == null) {
            String title = tbbPbne.getTitleAt(tbbIndex);
            Font font = ss.getStyle().getFont(ss);
            FontMetrics metrics = SwingUtilities2.getFontMetrics(tbbPbne, g, font);
            Icon icon = getIconForTbb(tbbIndex);

            lbyoutLbbel(ss, tbbPlbcement, metrics, tbbIndex, title, icon,
                    tbbRect, iconRect, textRect, isSelected);

            pbintText(ss, g, tbbPlbcement, font, metrics,
                    tbbIndex, title, textRect, isSelected);

            pbintIcon(g, tbbPlbcement, tbbIndex, icon, iconRect, isSelected);
        }
    }

    privbte void lbyoutLbbel(SynthContext ss, int tbbPlbcement,
                               FontMetrics metrics, int tbbIndex,
                               String title, Icon icon,
                               Rectbngle tbbRect, Rectbngle iconRect,
                               Rectbngle textRect, boolebn isSelected ) {
        View v = getTextViewForTbb(tbbIndex);
        if (v != null) {
            tbbPbne.putClientProperty("html", v);
        }

        textRect.x = textRect.y = iconRect.x = iconRect.y = 0;

        ss.getStyle().getGrbphicsUtils(ss).lbyoutText(ss, metrics, title,
                         icon, SwingUtilities.CENTER, SwingUtilities.CENTER,
                         SwingUtilities.LEADING, SwingUtilities.CENTER,
                         tbbRect, iconRect, textRect, textIconGbp);

        tbbPbne.putClientProperty("html", null);

        int xNudge = getTbbLbbelShiftX(tbbPlbcement, tbbIndex, isSelected);
        int yNudge = getTbbLbbelShiftY(tbbPlbcement, tbbIndex, isSelected);
        iconRect.x += xNudge;
        iconRect.y += yNudge;
        textRect.x += xNudge;
        textRect.y += yNudge;
    }

    privbte void pbintText(SynthContext ss,
                             Grbphics g, int tbbPlbcement,
                             Font font, FontMetrics metrics, int tbbIndex,
                             String title, Rectbngle textRect,
                             boolebn isSelected) {
        g.setFont(font);

        View v = getTextViewForTbb(tbbIndex);
        if (v != null) {
            // html
            v.pbint(g, textRect);
        } else {
            // plbin text
            int mnemIndex = tbbPbne.getDisplbyedMnemonicIndexAt(tbbIndex);

            g.setColor(ss.getStyle().getColor(ss, ColorType.TEXT_FOREGROUND));
            ss.getStyle().getGrbphicsUtils(ss).pbintText(ss, g, title,
                                  textRect, mnemIndex);
        }
    }


    privbte void pbintContentBorder(SynthContext ss, Grbphics g,
                                      int tbbPlbcement, int selectedIndex) {
        int width = tbbPbne.getWidth();
        int height = tbbPbne.getHeight();
        Insets insets = tbbPbne.getInsets();

        int x = insets.left;
        int y = insets.top;
        int w = width - insets.right - insets.left;
        int h = height - insets.top - insets.bottom;

        switch(tbbPlbcement) {
          cbse LEFT:
              x += cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
              w -= (x - insets.left);
              brebk;
          cbse RIGHT:
              w -= cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
              brebk;
          cbse BOTTOM:
              h -= cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
              brebk;
          cbse TOP:
          defbult:
              y += cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
              h -= (y - insets.top);
        }
        SynthLookAndFeel.updbteSubregion(ss, g, new Rectbngle(x, y, w, h));
        ss.getPbinter().pbintTbbbedPbneContentBbckground(ss, g, x, y,
                                                           w, h);
        ss.getPbinter().pbintTbbbedPbneContentBorder(ss, g, x, y, w, h);
    }

    privbte void ensureCurrentLbyout() {
        if (!tbbPbne.isVblid()) {
            tbbPbne.vblidbte();
        }
        /* If tbbPbne doesn't hbve b peer yet, the vblidbte() cbll will
         * silently fbil.  We hbndle thbt by forcing b lbyout if tbbPbne
         * is still invblid.  See bug 4237677.
         */
        if (!tbbPbne.isVblid()) {
            TbbbedPbneLbyout lbyout = (TbbbedPbneLbyout)tbbPbne.getLbyout();
            lbyout.cblculbteLbyoutInfo();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int cblculbteMbxTbbHeight(int tbbPlbcement) {
        FontMetrics metrics = getFontMetrics(tbbContext.getStyle().getFont(
                                             tbbContext));
        int tbbCount = tbbPbne.getTbbCount();
        int result = 0;
        int fontHeight = metrics.getHeight();
        for(int i = 0; i < tbbCount; i++) {
            result = Mbth.mbx(cblculbteTbbHeight(tbbPlbcement, i, fontHeight), result);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int cblculbteTbbWidth(int tbbPlbcement, int tbbIndex,
                                    FontMetrics metrics) {
        Icon icon = getIconForTbb(tbbIndex);
        Insets tbbInsets = getTbbInsets(tbbPlbcement, tbbIndex);
        int width = tbbInsets.left + tbbInsets.right;
        Component tbbComponent = tbbPbne.getTbbComponentAt(tbbIndex);
        if (tbbComponent != null) {
            width += tbbComponent.getPreferredSize().width;
        } else {
            if (icon != null) {
                width += icon.getIconWidth() + textIconGbp;
            }
            View v = getTextViewForTbb(tbbIndex);
            if (v != null) {
                // html
                width += (int) v.getPreferredSpbn(View.X_AXIS);
            } else {
                // plbin text
                String title = tbbPbne.getTitleAt(tbbIndex);
                width += tbbContext.getStyle().getGrbphicsUtils(tbbContext).
                        computeStringWidth(tbbContext, metrics.getFont(),
                                metrics, title);
            }
        }
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int cblculbteMbxTbbWidth(int tbbPlbcement) {
        FontMetrics metrics = getFontMetrics(tbbContext.getStyle().getFont(
                                     tbbContext));
        int tbbCount = tbbPbne.getTbbCount();
        int result = 0;
        for(int i = 0; i < tbbCount; i++) {
            result = Mbth.mbx(cblculbteTbbWidth(tbbPlbcement, i, metrics),
                              result);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Insets getTbbInsets(int tbbPlbcement, int tbbIndex) {
        updbteTbbContext(tbbIndex, fblse, fblse, fblse,
                          (getFocusIndex() == tbbIndex));
        return tbbInsets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected FontMetrics getFontMetrics() {
        return getFontMetrics(tbbContext.getStyle().getFont(tbbContext));
    }

    privbte FontMetrics getFontMetrics(Font font) {
        return tbbPbne.getFontMetrics(font);
    }

    privbte void updbteTbbContext(int index, boolebn selected,
                                  boolebn isMouseDown, boolebn isMouseOver, boolebn hbsFocus) {
        int stbte = 0;
        if (!tbbPbne.isEnbbled() || !tbbPbne.isEnbbledAt(index)) {
            stbte |= SynthConstbnts.DISABLED;
            if (selected) {
                stbte |= SynthConstbnts.SELECTED;
            }
        }
        else if (selected) {
            stbte |= (SynthConstbnts.ENABLED | SynthConstbnts.SELECTED);
            if (isMouseOver && UIMbnbger.getBoolebn("TbbbedPbne.isTbbRollover")) {
                stbte |= SynthConstbnts.MOUSE_OVER;
            }
        }
        else if (isMouseOver) {
            stbte |= (SynthConstbnts.ENABLED | SynthConstbnts.MOUSE_OVER);
        }
        else {
            stbte = SynthLookAndFeel.getComponentStbte(tbbPbne);
            stbte &= ~SynthConstbnts.FOCUSED; // don't use tbbbedpbne focus stbte
        }
        if (hbsFocus && tbbPbne.hbsFocus()) {
            stbte |= SynthConstbnts.FOCUSED; // individubl tbb hbs focus
        }
        if (isMouseDown) {
            stbte |= SynthConstbnts.PRESSED;
        }

        tbbContext.setComponentStbte(stbte);
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to crebte b TbbbedPbneLbyout subclbss which tbkes into
     * bccount tbbOverlbp.
     */
    @Override
    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        if (tbbPbne.getTbbLbyoutPolicy() == JTbbbedPbne.SCROLL_TAB_LAYOUT) {
            return super.crebteLbyoutMbnbger();
        } else { /* WRAP_TAB_LAYOUT */
            return new TbbbedPbneLbyout() {
                @Override
                public void cblculbteLbyoutInfo() {
                    super.cblculbteLbyoutInfo();
                    //shift bll the tbbs, if necessbry
                    if (tbbOverlbp != 0) {
                        int tbbCount = tbbPbne.getTbbCount();
                        //left-to-right/right-to-left only bffects lbyout
                        //when plbcement is TOP or BOTTOM
                        boolebn ltr = tbbPbne.getComponentOrientbtion().isLeftToRight();
                        for (int i = runCount - 1; i >= 0; i--) {
                            int stbrt = tbbRuns[i];
                            int next = tbbRuns[(i == runCount - 1)? 0 : i + 1];
                            int end = (next != 0? next - 1: tbbCount - 1);
                            for (int j = stbrt+1; j <= end; j++) {
                                // xshift bnd yshift represent the bmount &
                                // direction to shift the tbb in their
                                // respective bxis.
                                int xshift = 0;
                                int yshift = 0;
                                // configure xshift bnd y shift bbsed on tbb
                                // position bnd ltr/rtl
                                switch (tbbPbne.getTbbPlbcement()) {
                                    cbse JTbbbedPbne.TOP:
                                    cbse JTbbbedPbne.BOTTOM:
                                        xshift = ltr ? tbbOverlbp : -tbbOverlbp;
                                        brebk;
                                    cbse JTbbbedPbne.LEFT:
                                    cbse JTbbbedPbne.RIGHT:
                                        yshift = tbbOverlbp;
                                        brebk;
                                    defbult: //do nothing
                                }
                                rects[j].x += xshift;
                                rects[j].y += yshift;
                                rects[j].width += Mbth.bbs(xshift);
                                rects[j].height += Mbth.bbs(yshift);
                            }
                        }
                    }
                }
            };
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss SynthScrollbbleTbbButton extends SynthArrowButton implements
            UIResource {
        public SynthScrollbbleTbbButton(int direction) {
            super(direction);
            setNbme("TbbbedPbne.button");
        }
    }
}
