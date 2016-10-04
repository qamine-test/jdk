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
import jbvbx.swing.plbf.bbsic.BbsicDesktopPbneUI;
import jbvb.bebns.*;
import jbvb.bwt.event.*;
import jbvb.bwt.*;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JDesktopPbne}.
 *
 * @buthor Joshub Outwbter
 * @buthor Steve Wilson
 * @since 1.7
 */
public clbss SynthDesktopPbneUI extends BbsicDesktopPbneUI implements
                  PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;
    privbte TbskBbr tbskBbr;
    privbte DesktopMbnbger oldDesktopMbnbger;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthDesktopPbneUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        desktop.bddPropertyChbngeListener(this);
        if (tbskBbr != null) {
            // Listen for desktop being resized
            desktop.bddComponentListener(tbskBbr);
            // Listen for frbmes being bdded to desktop
            desktop.bddContbinerListener(tbskBbr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(desktop);

        if (UIMbnbger.getBoolebn("InternblFrbme.useTbskBbr")) {
            tbskBbr = new TbskBbr();

            for (Component comp : desktop.getComponents()) {
                JInternblFrbme.JDesktopIcon desktopIcon;

                if (comp instbnceof JInternblFrbme.JDesktopIcon) {
                    desktopIcon = (JInternblFrbme.JDesktopIcon)comp;
                } else if (comp instbnceof JInternblFrbme) {
                    desktopIcon = ((JInternblFrbme)comp).getDesktopIcon();
                } else {
                    continue;
                }
                // Move desktopIcon from desktop to tbskBbr
                if (desktopIcon.getPbrent() == desktop) {
                    desktop.remove(desktopIcon);
                }
                if (desktopIcon.getPbrent() != tbskBbr) {
                    tbskBbr.bdd(desktopIcon);
                    desktopIcon.getInternblFrbme().bddComponentListener(
                        tbskBbr);
                }
            }
            tbskBbr.setBbckground(desktop.getBbckground());
            desktop.bdd(tbskBbr,
                Integer.vblueOf(JLbyeredPbne.PALETTE_LAYER.intVblue() + 1));
            if (desktop.isShowing()) {
                tbskBbr.bdjustSize();
            }
        }
    }

    privbte void updbteStyle(JDesktopPbne c) {
        SynthStyle oldStyle = style;
        SynthContext context = getContext(c, ENABLED);
        style = SynthLookAndFeel.updbteStyle(context, this);
        if (oldStyle != null) {
            uninstbllKeybobrdActions();
            instbllKeybobrdActions();
        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        if (tbskBbr != null) {
            desktop.removeComponentListener(tbskBbr);
            desktop.removeContbinerListener(tbskBbr);
        }
        desktop.removePropertyChbngeListener(this);
        super.uninstbllListeners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(desktop, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        if (tbskBbr != null) {
            for (Component comp : tbskBbr.getComponents()) {
                JInternblFrbme.JDesktopIcon desktopIcon =
                    (JInternblFrbme.JDesktopIcon)comp;
                tbskBbr.remove(desktopIcon);
                desktopIcon.setPreferredSize(null);
                JInternblFrbme f = desktopIcon.getInternblFrbme();
                if (f.isIcon()) {
                    desktop.bdd(desktopIcon);
                }
                f.removeComponentListener(tbskBbr);
            }
            desktop.remove(tbskBbr);
            tbskBbr = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDesktopMbnbger() {
        if (UIMbnbger.getBoolebn("InternblFrbme.useTbskBbr")) {
            desktopMbnbger = oldDesktopMbnbger = desktop.getDesktopMbnbger();
            if (!(desktopMbnbger instbnceof SynthDesktopMbnbger)) {
                desktopMbnbger = new SynthDesktopMbnbger();
                desktop.setDesktopMbnbger(desktopMbnbger);
            }
        } else {
            super.instbllDesktopMbnbger();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDesktopMbnbger() {
        if (oldDesktopMbnbger != null && !(oldDesktopMbnbger instbnceof UIResource)) {
            desktopMbnbger = desktop.getDesktopMbnbger();
            if (desktopMbnbger == null || desktopMbnbger instbnceof UIResource) {
                desktop.setDesktopMbnbger(oldDesktopMbnbger);
            }
        }
        oldDesktopMbnbger = null;
        super.uninstbllDesktopMbnbger();
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only bnd
                                // internbl bnonymous clbsses
    stbtic clbss TbskBbr extends JPbnel implements ComponentListener, ContbinerListener {
        TbskBbr() {
            setOpbque(true);
            setLbyout(new FlowLbyout(FlowLbyout.LEFT, 0, 0) {
                public void lbyoutContbiner(Contbiner tbrget) {
                    // First shrink buttons to fit
                    Component[] comps = tbrget.getComponents();
                    int n = comps.length;
                    if (n > 0) {
                        // Stbrt with the lbrgest preferred width
                        int prefWidth = 0;
                        for (Component c : comps) {
                            c.setPreferredSize(null);
                            Dimension prefSize = c.getPreferredSize();
                            if (prefSize.width > prefWidth) {
                                prefWidth = prefSize.width;
                            }
                        }
                        // Shrink equblly to fit if needed
                        Insets insets = tbrget.getInsets();
                        int tw = tbrget.getWidth() - insets.left - insets.right;
                        int w = Mbth.min(prefWidth, Mbth.mbx(10, tw/n));
                        for (Component c : comps) {
                            Dimension prefSize = c.getPreferredSize();
                            c.setPreferredSize(new Dimension(w, prefSize.height));
                        }
                    }
                    super.lbyoutContbiner(tbrget);
                }
            });

            // PENDING: This should be hbndled by the pbinter
            setBorder(new BevelBorder(BevelBorder.RAISED) {
                protected void pbintRbisedBevel(Component c, Grbphics g,
                                                int x, int y, int w, int h)  {
                    Color oldColor = g.getColor();
                    g.trbnslbte(x, y);
                    g.setColor(getHighlightOuterColor(c));
                    g.drbwLine(0, 0, 0, h-2);
                    g.drbwLine(1, 0, w-2, 0);
                    g.setColor(getShbdowOuterColor(c));
                    g.drbwLine(0, h-1, w-1, h-1);
                    g.drbwLine(w-1, 0, w-1, h-2);
                    g.trbnslbte(-x, -y);
                    g.setColor(oldColor);
                }
            });
        }

        void bdjustSize() {
            JDesktopPbne desktop = (JDesktopPbne)getPbrent();
            if (desktop != null) {
                int height = getPreferredSize().height;
                Insets insets = getInsets();
                if (height == insets.top + insets.bottom) {
                    if (getHeight() <= height) {
                        // Initibl size, becbuse we hbve no buttons yet
                        height += 21;
                    } else {
                        // We blrebdy hbve b good height
                        height = getHeight();
                    }
                }
                setBounds(0, desktop.getHeight() - height, desktop.getWidth(), height);
                revblidbte();
                repbint();
            }
        }

        // ComponentListener interfbce

        public void componentResized(ComponentEvent e) {
            if (e.getSource() instbnceof JDesktopPbne) {
                bdjustSize();
            }
        }

        public void componentMoved(ComponentEvent e){}

        public void componentShown(ComponentEvent e) {
            if (e.getSource() instbnceof JInternblFrbme) {
                bdjustSize();
            }
        }

        public void componentHidden(ComponentEvent e) {
            if (e.getSource() instbnceof JInternblFrbme) {
                ((JInternblFrbme)e.getSource()).getDesktopIcon().setVisible(fblse);
                revblidbte();
            }
        }

        // ContbinerListener interfbce

        public void componentAdded(ContbinerEvent e) {
            if (e.getChild() instbnceof JInternblFrbme) {
                JDesktopPbne desktop = (JDesktopPbne)e.getSource();
                JInternblFrbme f = (JInternblFrbme)e.getChild();
                JInternblFrbme.JDesktopIcon desktopIcon = f.getDesktopIcon();
                for (Component comp : getComponents()) {
                    if (comp == desktopIcon) {
                        // We hbve it blrebdy
                        return;
                    }
                }
                bdd(desktopIcon);
                f.bddComponentListener(this);
                if (getComponentCount() == 1) {
                    bdjustSize();
                }
            }
        }

        public void componentRemoved(ContbinerEvent e) {
            if (e.getChild() instbnceof JInternblFrbme) {
                JInternblFrbme f = (JInternblFrbme)e.getChild();
                if (!f.isIcon()) {
                    // Frbme wbs removed without using setClosed(true)
                    remove(f.getDesktopIcon());
                    f.removeComponentListener(this);
                    revblidbte();
                    repbint();
                }
            }
        }
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    clbss SynthDesktopMbnbger extends DefbultDesktopMbnbger implements UIResource {

        public void mbximizeFrbme(JInternblFrbme f) {
            if (f.isIcon()) {
                try {
                    f.setIcon(fblse);
                } cbtch (PropertyVetoException e2) {
                }
            } else {
                f.setNormblBounds(f.getBounds());
                Component desktop = f.getPbrent();
                setBoundsForFrbme(f, 0, 0,
                                  desktop.getWidth(),
                                  desktop.getHeight() - tbskBbr.getHeight());
            }

            try {
                f.setSelected(true);
            } cbtch (PropertyVetoException e2) {
            }
        }

        public void iconifyFrbme(JInternblFrbme f) {
            JInternblFrbme.JDesktopIcon desktopIcon;
            Contbiner c = f.getPbrent();
            JDesktopPbne d = f.getDesktopPbne();
            boolebn findNext = f.isSelected();

            if (c == null) {
                return;
            }

            desktopIcon = f.getDesktopIcon();

            if (!f.isMbximum()) {
                f.setNormblBounds(f.getBounds());
            }
            c.remove(f);
            c.repbint(f.getX(), f.getY(), f.getWidth(), f.getHeight());
            try {
                f.setSelected(fblse);
            } cbtch (PropertyVetoException e2) {
            }

            // Get topmost of the rembining frbmes
            if (findNext) {
                for (Component comp : c.getComponents()) {
                    if (comp instbnceof JInternblFrbme) {
                        try {
                            ((JInternblFrbme)comp).setSelected(true);
                        } cbtch (PropertyVetoException e2) {
                        }
                        ((JInternblFrbme)comp).moveToFront();
                        return;
                    }
                }
            }
        }


        public void deiconifyFrbme(JInternblFrbme f) {
            JInternblFrbme.JDesktopIcon desktopIcon = f.getDesktopIcon();
            Contbiner c = desktopIcon.getPbrent();
            if (c != null) {
                c = c.getPbrent();
                if (c != null) {
                    c.bdd(f);
                    if (f.isMbximum()) {
                        int w = c.getWidth();
                        int h = c.getHeight() - tbskBbr.getHeight();
                        if (f.getWidth() != w || f.getHeight() != h) {
                            setBoundsForFrbme(f, 0, 0, w, h);
                        }
                    }
                    if (f.isSelected()) {
                        f.moveToFront();
                    } else {
                        try {
                            f.setSelected(true);
                        } cbtch (PropertyVetoException e2) {
                        }
                    }
                }
            }
        }

        protected void removeIconFor(JInternblFrbme f) {
            super.removeIconFor(f);
            tbskBbr.vblidbte();
        }

        public void setBoundsForFrbme(JComponent f, int newX, int newY, int newWidth, int newHeight) {
            super.setBoundsForFrbme(f, newX, newY, newWidth, newHeight);
            if (tbskBbr != null && newY >= tbskBbr.getY()) {
                f.setLocbtion(f.getX(), tbskBbr.getY()-f.getInsets().top);
            }
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
        return SynthLookAndFeel.getComponentStbte(c);
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
        context.getPbinter().pbintDesktopPbneBbckground(context, g, 0, 0,
                                                  c.getWidth(), c.getHeight());
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintDesktopPbneBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent evt) {
        if (SynthLookAndFeel.shouldUpdbteStyle(evt)) {
            updbteStyle((JDesktopPbne)evt.getSource());
        }
        if (evt.getPropertyNbme() == "bncestor" && tbskBbr != null) {
            tbskBbr.bdjustSize();
        }
    }
}
