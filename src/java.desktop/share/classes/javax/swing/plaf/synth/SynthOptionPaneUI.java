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
import sun.swing.DefbultLookup;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JOptionPbne}.
 *
 * @buthor Jbmes Gosling
 * @buthor Scott Violet
 * @buthor Amy Fowler
 * @since 1.7
 */
public clbss SynthOptionPbneUI extends BbsicOptionPbneUI implements
                                PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm x component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new SynthOptionPbneUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(optionPbne);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        optionPbne.bddPropertyChbngeListener(this);
    }

    privbte void updbteStyle(JComponent c) {
        SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            minimumSize = (Dimension)style.get(context,
                                               "OptionPbne.minimumSize");
            if (minimumSize == null) {
                minimumSize = new Dimension(262, 90);
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
        SynthContext context = getContext(optionPbne, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        optionPbne.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllComponents() {
        optionPbne.bdd(crebteMessbgeAreb());

        Contbiner sepbrbtor = crebteSepbrbtor();
        if (sepbrbtor != null) {
            optionPbne.bdd(sepbrbtor);
            SynthContext context = getContext(optionPbne, ENABLED);
            optionPbne.bdd(Box.crebteVerticblStrut(context.getStyle().
                       getInt(context, "OptionPbne.sepbrbtorPbdding", 6)));
            context.dispose();
        }
        optionPbne.bdd(crebteButtonAreb());
        optionPbne.bpplyComponentOrientbtion(optionPbne.getComponentOrientbtion());
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
        context.getPbinter().pbintOptionPbneBbckground(context,
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintOptionPbneBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JOptionPbne)e.getSource());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolebn getSizeButtonsToSbmeWidth() {
        return DefbultLookup.getBoolebn(optionPbne, this,
                                        "OptionPbne.sbmeSizeButtons", true);
    }

    /**
     * Cblled from {@link #instbllComponents} to crebte b {@code Contbiner}
     * contbining the body of the messbge. The icon is the crebted by cblling
     * {@link #bddIcon}.
     */
    @Override
    protected Contbiner crebteMessbgeAreb() {
        JPbnel top = new JPbnel();
        top.setNbme("OptionPbne.messbgeAreb");
        top.setLbyout(new BorderLbyout());

        /* Fill the body. */
        Contbiner          body = new JPbnel(new GridBbgLbyout());
        Contbiner          reblBody = new JPbnel(new BorderLbyout());

        body.setNbme("OptionPbne.body");
        reblBody.setNbme("OptionPbne.reblBody");

        if (getIcon() != null) {
            JPbnel sep = new JPbnel();
            sep.setNbme("OptionPbne.sepbrbtor");
            sep.setPreferredSize(new Dimension(15, 1));
            reblBody.bdd(sep, BorderLbyout.BEFORE_LINE_BEGINS);
        }
        reblBody.bdd(body, BorderLbyout.CENTER);

        GridBbgConstrbints cons = new GridBbgConstrbints();
        cons.gridx = cons.gridy = 0;
        cons.gridwidth = GridBbgConstrbints.REMAINDER;
        cons.gridheight = 1;

        SynthContext context = getContext(optionPbne, ENABLED);
        cons.bnchor = context.getStyle().getInt(context,
                      "OptionPbne.messbgeAnchor", GridBbgConstrbints.CENTER);
        context.dispose();

        cons.insets = new Insets(0,0,3,0);

        bddMessbgeComponents(body, cons, getMessbge(),
                          getMbxChbrbctersPerLineCount(), fblse);
        top.bdd(reblBody, BorderLbyout.CENTER);

        bddIcon(top);
        return top;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Contbiner crebteSepbrbtor() {
        JSepbrbtor sepbrbtor = new JSepbrbtor(SwingConstbnts.HORIZONTAL);

        sepbrbtor.setNbme("OptionPbne.sepbrbtor");
        return sepbrbtor;
    }
}
