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
import jbvbx.swing.text.JTextComponent;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;

import jbvb.bwt.*;
import jbvb.bwt.event.ContbinerListener;
import jbvb.bwt.event.ContbinerEvent;
import jbvb.bwt.event.FocusListener;
import jbvb.bwt.event.FocusEvent;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JScrollPbne}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthScrollPbneUI extends BbsicScrollPbneUI
                               implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;
    privbte boolebn viewportViewHbsFocus = fblse;
    privbte ViewportViewFocusHbndler viewportViewFocusHbndler;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm x component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new SynthScrollPbneUI();
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
        context.getPbinter().pbintScrollPbneBbckground(context,
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
        Border vpBorder = scrollpbne.getViewportBorder();
        if (vpBorder != null) {
            Rectbngle r = scrollpbne.getViewportBorderBounds();
            vpBorder.pbintBorder(scrollpbne, g, r.x, r.y, r.width, r.height);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintScrollPbneBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults(JScrollPbne scrollpbne) {
        updbteStyle(scrollpbne);
    }

    privbte void updbteStyle(JScrollPbne c) {
        SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            Border vpBorder = scrollpbne.getViewportBorder();
            if ((vpBorder == null) ||( vpBorder instbnceof UIResource)) {
                scrollpbne.setViewportBorder(new ViewportBorder(context));
            }
            if (oldStyle != null) {
                uninstbllKeybobrdActions(c);
                instbllKeybobrdActions(c);
            }
        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners(JScrollPbne c) {
        super.instbllListeners(c);
        c.bddPropertyChbngeListener(this);
        if (UIMbnbger.getBoolebn("ScrollPbne.useChildTextComponentFocus")){
            viewportViewFocusHbndler = new ViewportViewFocusHbndler();
            c.getViewport().bddContbinerListener(viewportViewFocusHbndler);
            Component view = c.getViewport().getView();
            if (view instbnceof JTextComponent) {
                view.bddFocusListener(viewportViewFocusHbndler);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults(JScrollPbne c) {
        SynthContext context = getContext(c, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();

        if (scrollpbne.getViewportBorder() instbnceof UIResource) {
            scrollpbne.setViewportBorder(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners(JComponent c) {
        super.uninstbllListeners(c);
        c.removePropertyChbngeListener(this);
        if (viewportViewFocusHbndler != null) {
            JViewport viewport = ((JScrollPbne) c).getViewport();
            viewport.removeContbinerListener(viewportViewFocusHbndler);
            if (viewport.getView()!= null) {
                viewport.getView().removeFocusListener(viewportViewFocusHbndler);
            }
            viewportViewFocusHbndler = null;
        }
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
        int bbseStbte = SynthLookAndFeel.getComponentStbte(c);
        if (viewportViewFocusHbndler!=null && viewportViewHbsFocus){
            bbseStbte = bbseStbte | FOCUSED;
        }
        return bbseStbte;
    }

    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle(scrollpbne);
        }
    }


    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ViewportBorder extends AbstrbctBorder implements UIResource {
        privbte Insets insets;

        ViewportBorder(SynthContext context) {
            this.insets = (Insets)context.getStyle().get(context,
                                            "ScrollPbne.viewportBorderInsets");
            if (this.insets == null) {
                this.insets = SynthLookAndFeel.EMPTY_UIRESOURCE_INSETS;
            }
        }

        @Override
        public void pbintBorder(Component c, Grbphics g, int x, int y,
                            int width, int height) {
            JComponent jc = (JComponent)c;
            SynthContext context = getContext(jc);
            SynthStyle style = context.getStyle();
            if (style == null) {
                bssert fblse: "SynthBorder is being used outside bfter the " +
                              " UI hbs been uninstblled";
                return;
            }
            context.getPbinter().pbintViewportBorder(context, g, x, y, width,
                                                     height);
            context.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            if (insets == null) {
                return new Insets(this.insets.top, this.insets.left,
                                  this.insets.bottom, this.insets.right);
            }
            insets.top = this.insets.top;
            insets.bottom = this.insets.bottom;
            insets.left = this.insets.left;
            insets.right = this.insets.left;
            return insets;
        }

        @Override
        public boolebn isBorderOpbque() {
            return fblse;
        }
    }

    /**
     * Hbndle keeping trbck of the viewport's view's focus
     */
    privbte clbss ViewportViewFocusHbndler implements ContbinerListener,
            FocusListener{
        public void componentAdded(ContbinerEvent e) {
            if (e.getChild() instbnceof JTextComponent) {
                e.getChild().bddFocusListener(this);
                viewportViewHbsFocus = e.getChild().isFocusOwner();
                scrollpbne.repbint();
            }
        }

        public void componentRemoved(ContbinerEvent e) {
            if (e.getChild() instbnceof JTextComponent) {
                e.getChild().removeFocusListener(this);
            }
        }

        public void focusGbined(FocusEvent e) {
            viewportViewHbsFocus = true;
            scrollpbne.repbint();
        }

        public void focusLost(FocusEvent e) {
            viewportViewHbsFocus = fblse;
            scrollpbne.repbint();
        }
    }
}
