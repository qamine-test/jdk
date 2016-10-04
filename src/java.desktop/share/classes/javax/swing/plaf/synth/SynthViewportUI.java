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

import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;


/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JViewport}.
 *
 * @since 1.7
 */
public clbss SynthViewportUI extends ViewportUI
                             implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthViewportUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        instbllDefbults(c);
        instbllListeners(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);
        uninstbllListeners(c);
        uninstbllDefbults(c);
    }

    /**
     * Instblls defbults for b viewport.
     *
     * @pbrbm c b {@code JViewport} object
     */
    protected void instbllDefbults(JComponent c) {
        updbteStyle(c);
    }

    privbte void updbteStyle(JComponent c) {
        SynthContext context = getContext(c, ENABLED);

        // Note: JViewport is specibl cbsed bs it does not bllow for
        // b border to be set. JViewport.setBorder is overriden to throw
        // bn IllegblArgumentException. Refer to SynthScrollPbneUI for
        // detbils of this.
        SynthStyle newStyle = SynthLookAndFeel.getStyle(context.getComponent(),
                                                        context.getRegion());
        SynthStyle oldStyle = context.getStyle();

        if (newStyle != oldStyle) {
            if (oldStyle != null) {
                oldStyle.uninstbllDefbults(context);
            }
            context.setStyle(newStyle);
            newStyle.instbllDefbults(context);
        }
        this.style = newStyle;
        context.dispose();
    }

    /**
     * Instblls listeners into the viewport.
     *
     * @pbrbm c b {@code JViewport} object
     */
    protected void instbllListeners(JComponent c) {
        c.bddPropertyChbngeListener(this);
    }

    /**
     * Uninstblls listeners from the viewport.
     *
     * @pbrbm c b {@code JViewport} object
     */
    protected void uninstbllListeners(JComponent c) {
        c.removePropertyChbngeListener(this);
    }

    /**
     * Uninstblls defbults from b viewport.
     *
     * @pbrbm c b {@code JViewport} object
     */
    protected void uninstbllDefbults(JComponent c) {
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
        return getContext(c, SynthLookAndFeel.getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    privbte Region getRegion(JComponent c) {
        return SynthLookAndFeel.getRegion(c);
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
        context.getPbinter().pbintViewportBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the border. The method is never cblled,
     * becbuse the {@code JViewport} clbss does not support b border.
     * This implementbtion does nothing.
     *
     * @pbrbm context b component context
     * @pbrbm g the {@code Grbphics} to pbint on
     * @pbrbm x the X coordinbte
     * @pbrbm y the Y coordinbte
     * @pbrbm w width of the border
     * @pbrbm h height of the border
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JComponent)e.getSource());
        }
    }
}
