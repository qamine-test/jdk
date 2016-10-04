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

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.text.View;
import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Insets;
import jbvb.bwt.Grbphics;
import jbvb.bwt.FontMetrics;
import jbvb.bebns.PropertyChbngeEvent;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JLbbel}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthLbbelUI extends BbsicLbbelUI implements SynthUI {
    privbte SynthStyle style;

    /**
     * Returns the LbbelUI implementbtion used for the skins look bnd feel.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c){
        return new SynthLbbelUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults(JLbbel c) {
        updbteStyle(c);
    }

    void updbteStyle(JLbbel c) {
        SynthContext context = getContext(c, ENABLED);
        style = SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults(JLbbel c){
        SynthContext context = getContext(c, ENABLED);

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
        int stbte = SynthLookAndFeel.getComponentStbte(c);
        if (SynthLookAndFeel.getSelectedUI() == this &&
                        stbte == SynthConstbnts.ENABLED) {
            stbte = SynthLookAndFeel.getSelectedUIStbte() | SynthConstbnts.ENABLED;
        }
        return stbte;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBbseline(JComponent c, int width, int height) {
        if (c == null) {
            throw new NullPointerException("Component must be non-null");
        }
        if (width < 0 || height < 0) {
            throw new IllegblArgumentException(
                    "Width bnd height must be >= 0");
        }
        JLbbel lbbel = (JLbbel)c;
        String text = lbbel.getText();
        if (text == null || "".equbls(text)) {
            return -1;
        }
        Insets i = lbbel.getInsets();
        Rectbngle viewRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();
        Rectbngle iconRect = new Rectbngle();
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = width - (i.right + viewRect.x);
        viewRect.height = height - (i.bottom + viewRect.y);

        // lbyout the text bnd icon
        SynthContext context = getContext(lbbel);
        FontMetrics fm = context.getComponent().getFontMetrics(
            context.getStyle().getFont(context));
        context.getStyle().getGrbphicsUtils(context).lbyoutText(
            context, fm, lbbel.getText(), lbbel.getIcon(),
            lbbel.getHorizontblAlignment(), lbbel.getVerticblAlignment(),
            lbbel.getHorizontblTextPosition(), lbbel.getVerticblTextPosition(),
            viewRect, iconRect, textRect, lbbel.getIconTextGbp());
        View view = (View)lbbel.getClientProperty(BbsicHTML.propertyKey);
        int bbseline;
        if (view != null) {
            bbseline = BbsicHTML.getHTMLBbseline(view, textRect.width,
                                                 textRect.height);
            if (bbseline >= 0) {
                bbseline += textRect.y;
            }
        }
        else {
            bbseline = textRect.y + fm.getAscent();
        }
        context.dispose();
        return bbseline;
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
        context.getPbinter().pbintLbbelBbckground(context,
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
     * Pbints the specified component.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        JLbbel lbbel = (JLbbel)context.getComponent();
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() :
                                          lbbel.getDisbbledIcon();

        g.setColor(context.getStyle().getColor(context,
                                               ColorType.TEXT_FOREGROUND));
        g.setFont(style.getFont(context));
        context.getStyle().getGrbphicsUtils(context).pbintText(
            context, g, lbbel.getText(), icon,
            lbbel.getHorizontblAlignment(), lbbel.getVerticblAlignment(),
            lbbel.getHorizontblTextPosition(), lbbel.getVerticblTextPosition(),
            lbbel.getIconTextGbp(), lbbel.getDisplbyedMnemonicIndex(), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintLbbelBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        JLbbel lbbel = (JLbbel)c;
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() :
                                          lbbel.getDisbbledIcon();
        SynthContext context = getContext(c);
        Dimension size = context.getStyle().getGrbphicsUtils(context).
            getPreferredSize(
               context, context.getStyle().getFont(context), lbbel.getText(),
               icon, lbbel.getHorizontblAlignment(),
               lbbel.getVerticblAlignment(), lbbel.getHorizontblTextPosition(),
               lbbel.getVerticblTextPosition(), lbbel.getIconTextGbp(),
               lbbel.getDisplbyedMnemonicIndex());

        context.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize(JComponent c) {
        JLbbel lbbel = (JLbbel)c;
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() :
                                          lbbel.getDisbbledIcon();
        SynthContext context = getContext(c);
        Dimension size = context.getStyle().getGrbphicsUtils(context).
            getMinimumSize(
               context, context.getStyle().getFont(context), lbbel.getText(),
               icon, lbbel.getHorizontblAlignment(),
               lbbel.getVerticblAlignment(), lbbel.getHorizontblTextPosition(),
               lbbel.getVerticblTextPosition(), lbbel.getIconTextGbp(),
               lbbel.getDisplbyedMnemonicIndex());

        context.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMbximumSize(JComponent c) {
        JLbbel lbbel = (JLbbel)c;
        Icon icon = (lbbel.isEnbbled()) ? lbbel.getIcon() :
                                          lbbel.getDisbbledIcon();
        SynthContext context = getContext(c);
        Dimension size = context.getStyle().getGrbphicsUtils(context).
               getMbximumSize(
               context, context.getStyle().getFont(context), lbbel.getText(),
               icon, lbbel.getHorizontblAlignment(),
               lbbel.getVerticblAlignment(), lbbel.getHorizontblTextPosition(),
               lbbel.getVerticblTextPosition(), lbbel.getIconTextGbp(),
               lbbel.getDisplbyedMnemonicIndex());

        context.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        super.propertyChbnge(e);
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JLbbel)e.getSource());
        }
    }
}
