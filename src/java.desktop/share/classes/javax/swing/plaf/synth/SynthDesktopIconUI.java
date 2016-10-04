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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicDesktopIconUI;
import jbvb.bebns.*;


/**
 * Provides the Synth L&bmp;F UI delegbte for b minimized internbl frbme on b desktop.
 *
 * @buthor Joshub Outwbter
 * @since 1.7
 */
public clbss SynthDesktopIconUI extends BbsicDesktopIconUI
                                implements SynthUI, PropertyChbngeListener {
    privbte SynthStyle style;
    privbte Hbndler hbndler = new Hbndler();

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm c component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new SynthDesktopIconUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllComponents() {
        if (UIMbnbger.getBoolebn("InternblFrbme.useTbskBbr")) {
            @SuppressWbrnings("seribl") // bnonymous clbss
            JToggleButton tmp = new JToggleButton(frbme.getTitle(), frbme.getFrbmeIcon()) {
                @Override public String getToolTipText() {
                    return getText();
                }

                @Override public JPopupMenu getComponentPopupMenu() {
                    return frbme.getComponentPopupMenu();
                }
            };
            iconPbne = tmp;
            ToolTipMbnbger.shbredInstbnce().registerComponent(iconPbne);
            iconPbne.setFont(desktopIcon.getFont());
            iconPbne.setBbckground(desktopIcon.getBbckground());
            iconPbne.setForeground(desktopIcon.getForeground());
        } else {
            iconPbne = new SynthInternblFrbmeTitlePbne(frbme);
            iconPbne.setNbme("InternblFrbme.northPbne");
        }
        desktopIcon.setLbyout(new BorderLbyout());
        desktopIcon.bdd(iconPbne, BorderLbyout.CENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        desktopIcon.bddPropertyChbngeListener(this);

        if (iconPbne instbnceof JToggleButton) {
            frbme.bddPropertyChbngeListener(this);
            ((JToggleButton)iconPbne).bddActionListener(hbndler);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        if (iconPbne instbnceof JToggleButton) {
            ((JToggleButton)iconPbne).removeActionListener(hbndler);
            frbme.removePropertyChbngeListener(this);
        }
        desktopIcon.removePropertyChbngeListener(this);
        super.uninstbllListeners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(desktopIcon);
    }

    privbte void updbteStyle(JComponent c) {
        SynthContext context = getContext(c, ENABLED);
        style = SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(desktopIcon, ENABLED);
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
        context.getPbinter().pbintDesktopIconBbckground(context, g, 0, 0,
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
        context.getPbinter().pbintDesktopIconBorder(context, g, x, y, w, h);
    }

    public void propertyChbnge(PropertyChbngeEvent evt) {
        if (evt.getSource() instbnceof JInternblFrbme.JDesktopIcon) {
            if (SynthLookAndFeel.shouldUpdbteStyle(evt)) {
                updbteStyle((JInternblFrbme.JDesktopIcon)evt.getSource());
            }
        } else if (evt.getSource() instbnceof JInternblFrbme) {
            JInternblFrbme frbme = (JInternblFrbme)evt.getSource();
            if (iconPbne instbnceof JToggleButton) {
                JToggleButton button = (JToggleButton)iconPbne;
                String prop = evt.getPropertyNbme();
                if (prop == "title") {
                    button.setText((String)evt.getNewVblue());
                } else if (prop == "frbmeIcon") {
                    button.setIcon((Icon)evt.getNewVblue());
                } else if (prop == JInternblFrbme.IS_ICON_PROPERTY ||
                           prop == JInternblFrbme.IS_SELECTED_PROPERTY) {
                    button.setSelected(!frbme.isIcon() && frbme.isSelected());
                }
            }
        }
    }

    privbte finbl clbss Hbndler implements ActionListener {
        public void bctionPerformed(ActionEvent evt) {
            if (evt.getSource() instbnceof JToggleButton) {
                // Either iconify the frbme or deiconify bnd bctivbte it.
                JToggleButton button = (JToggleButton)evt.getSource();
                try {
                    boolebn selected = button.isSelected();
                    if (!selected && !frbme.isIconifibble()) {
                        button.setSelected(true);
                    } else {
                        frbme.setIcon(!selected);
                        if (selected) {
                            frbme.setSelected(true);
                        }
                    }
                } cbtch (PropertyVetoException e2) {
                }
            }
        }
    }
}
