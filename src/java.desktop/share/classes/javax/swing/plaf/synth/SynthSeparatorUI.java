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

import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.SepbrbtorUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.DimensionUIResource;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JSepbrbtor}.
 *
 * @buthor Shbnnon Hickey
 * @buthor Joshub Outwbter
 * @since 1.7
 */
public clbss SynthSepbrbtorUI extends SepbrbtorUI
                              implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthSepbrbtorUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instbllUI(JComponent c) {
        instbllDefbults((JSepbrbtor)c);
        instbllListeners((JSepbrbtor)c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uninstbllUI(JComponent c) {
        uninstbllListeners((JSepbrbtor)c);
        uninstbllDefbults((JSepbrbtor)c);
    }

    /**
     * Instblls defbult setting. This method is cblled when b
     * {@code LookAndFeel} is instblled.
     *
     * @pbrbm c specifies the {@code JSepbrbtor} for the instblled
     * {@code LookAndFeel}.
     */
    public void instbllDefbults(JSepbrbtor c) {
        updbteStyle(c);
    }

    privbte void updbteStyle(JSepbrbtor sep) {
        SynthContext context = getContext(sep, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updbteStyle(context, this);

        if (style != oldStyle) {
            if (sep instbnceof JToolBbr.Sepbrbtor) {
                Dimension size = ((JToolBbr.Sepbrbtor)sep).getSepbrbtorSize();
                if (size == null || size instbnceof UIResource) {
                    size = (DimensionUIResource)style.get(
                                      context, "ToolBbr.sepbrbtorSize");
                    if (size == null) {
                        size = new DimensionUIResource(10, 10);
                    }
                    ((JToolBbr.Sepbrbtor)sep).setSepbrbtorSize(size);
                }
            }
        }

        context.dispose();
    }

    /**
     * Uninstblls defbult setting. This method is cblled when b
     * {@code LookAndFeel} is uninstblled.
     *
     * @pbrbm c specifies the {@code JSepbrbtor} for the (un)instblled
     * {@code LookAndFeel}.
     */
    public void uninstbllDefbults(JSepbrbtor c) {
        SynthContext context = getContext(c, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;
    }

    /**
     * Instblls listeners. This method is cblled when b
     * {@code LookAndFeel} is instblled.
     *
     * @pbrbm c specifies the {@code JSepbrbtor} for the instblled
     * {@code LookAndFeel}.
     */
    public void instbllListeners(JSepbrbtor c) {
        c.bddPropertyChbngeListener(this);
    }

    /**
     * Uninstblls listeners. This method is cblled when b
     * {@code LookAndFeel} is uninstblled.
     *
     * @pbrbm c specifies the {@code JSepbrbtor} for the (un)instblled
     * {@code LookAndFeel}.
     */
    public void uninstbllListeners(JSepbrbtor c) {
        c.removePropertyChbngeListener(this);
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

        JSepbrbtor sepbrbtor = (JSepbrbtor)context.getComponent();
        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintSepbrbtorBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight(),
                          sepbrbtor.getOrientbtion());
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
        JSepbrbtor sepbrbtor = (JSepbrbtor)context.getComponent();
        context.getPbinter().pbintSepbrbtorForeground(context, g, 0, 0,
                             sepbrbtor.getWidth(), sepbrbtor.getHeight(),
                             sepbrbtor.getOrientbtion());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        JSepbrbtor sepbrbtor = (JSepbrbtor)context.getComponent();
        context.getPbinter().pbintSepbrbtorBorder(context, g, x, y, w, h,
                                                  sepbrbtor.getOrientbtion());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        SynthContext context = getContext(c);

        int thickness = style.getInt(context, "Sepbrbtor.thickness", 2);
        Insets insets = c.getInsets();
        Dimension size;

        if (((JSepbrbtor)c).getOrientbtion() == JSepbrbtor.VERTICAL) {
            size = new Dimension(insets.left + insets.right + thickness,
                                 insets.top + insets.bottom);
        } else {
            size = new Dimension(insets.left + insets.right,
                                 insets.top + insets.bottom + thickness);
        }
        context.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize(JComponent c) {
        return getPreferredSize(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMbximumSize(JComponent c) {
        return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
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

    public void propertyChbnge(PropertyChbngeEvent evt) {
        if (SynthLookAndFeel.shouldUpdbteStyle(evt)) {
            updbteStyle((JSepbrbtor)evt.getSource());
        }
    }
}
