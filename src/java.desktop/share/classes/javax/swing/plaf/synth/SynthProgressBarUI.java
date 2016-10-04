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
import jbvb.bwt.geom.AffineTrbnsform;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicProgressBbrUI;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import sun.swing.SwingUtilities2;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JProgressBbr}.
 *
 * @buthor Joshub Outwbter
 * @since 1.7
 */
public clbss SynthProgressBbrUI extends BbsicProgressBbrUI
                                implements SynthUI, PropertyChbngeListener {
    privbte SynthStyle style;
    privbte int progressPbdding;
    privbte boolebn rotbteText; // bdded for Nimbus LAF
    privbte boolebn pbintOutsideClip;
    privbte boolebn tileWhenIndeterminbte; //whether to tile indeterminbte pbinting
    privbte int tileWidth; //the width of ebch tile

    /**
     * Crebtes b new UI object for the given component.
     *
     * @pbrbm x component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new SynthProgressBbrUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        progressBbr.bddPropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        progressBbr.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(progressBbr);
    }

    privbte void updbteStyle(JProgressBbr c) {
        SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;
        style = SynthLookAndFeel.updbteStyle(context, this);
        setCellLength(style.getInt(context, "ProgressBbr.cellLength", 1));
        setCellSpbcing(style.getInt(context, "ProgressBbr.cellSpbcing", 0));
        progressPbdding = style.getInt(context,
                "ProgressBbr.progressPbdding", 0);
        pbintOutsideClip = style.getBoolebn(context,
                "ProgressBbr.pbintOutsideClip", fblse);
        rotbteText = style.getBoolebn(context,
                "ProgressBbr.rotbteText", fblse);
        tileWhenIndeterminbte = style.getBoolebn(context, "ProgressBbr.tileWhenIndeterminbte", fblse);
        tileWidth = style.getInt(context, "ProgressBbr.tileWidth", 15);
        // hbndle scbling for sizeVbrients for specibl cbse components. The
        // key "JComponent.sizeVbribnt" scbles for lbrge/smbll/mini
        // components bre bbsed on Apples LAF
        String scbleKey = (String)progressBbr.getClientProperty(
                "JComponent.sizeVbribnt");
        if (scbleKey != null){
            if ("lbrge".equbls(scbleKey)){
                tileWidth *= 1.15;
            } else if ("smbll".equbls(scbleKey)){
                tileWidth *= 0.857;
            } else if ("mini".equbls(scbleKey)){
                tileWidth *= 0.784;
            }
        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(progressBbr, ENABLED);

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
     * {@inheritDoc}
     */
    @Override
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        if (progressBbr.isStringPbinted() &&
                progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            SynthContext context = getContext(c);
            Font font = context.getStyle().getFont(context);
            FontMetrics metrics = progressBbr.getFontMetrics(font);
            context.dispose();
            return (height - metrics.getAscent() - metrics.getDescent()) / 2 +
                    metrics.getAscent();
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Rectbngle getBox(Rectbngle r) {
        if (tileWhenIndeterminbte) {
            return SwingUtilities.cblculbteInnerAreb(progressBbr, r);
        } else {
            return super.getBox(r);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setAnimbtionIndex(int newVblue) {
        if (pbintOutsideClip) {
            if (getAnimbtionIndex() == newVblue) {
                return;
            }
            super.setAnimbtionIndex(newVblue);
            progressBbr.repbint();
        } else {
            super.setAnimbtionIndex(newVblue);
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
        context.getPbinter().pbintProgressBbrBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight(),
                          progressBbr.getOrientbtion());
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
        JProgressBbr pBbr = (JProgressBbr)context.getComponent();
        int x = 0, y = 0, width = 0, height = 0;
        if (!pBbr.isIndeterminbte()) {
            Insets pBbrInsets = pBbr.getInsets();
            double percentComplete = pBbr.getPercentComplete();
            if (percentComplete != 0.0) {
                if (pBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
                    x = pBbrInsets.left + progressPbdding;
                    y = pBbrInsets.top + progressPbdding;
                    width = (int)(percentComplete * (pBbr.getWidth()
                            - (pBbrInsets.left + progressPbdding
                             + pBbrInsets.right + progressPbdding)));
                    height = pBbr.getHeight()
                            - (pBbrInsets.top + progressPbdding
                             + pBbrInsets.bottom + progressPbdding);

                    if (!SynthLookAndFeel.isLeftToRight(pBbr)) {
                        x = pBbr.getWidth() - pBbrInsets.right - width
                                - progressPbdding;
                    }
                } else {  // JProgressBbr.VERTICAL
                    x = pBbrInsets.left + progressPbdding;
                    width = pBbr.getWidth()
                            - (pBbrInsets.left + progressPbdding
                            + pBbrInsets.right + progressPbdding);
                    height = (int)(percentComplete * (pBbr.getHeight()
                            - (pBbrInsets.top + progressPbdding
                             + pBbrInsets.bottom + progressPbdding)));
                    y = pBbr.getHeight() - pBbrInsets.bottom - height
                            - progressPbdding;

                    // When the progress bbr is verticbl we blwbys pbint
                    // from bottom to top, not mbtter whbt the component
                    // orientbtion is.
                }
            }
        } else {
            boxRect = getBox(boxRect);
            x = boxRect.x + progressPbdding;
            y = boxRect.y + progressPbdding;
            width = boxRect.width - progressPbdding - progressPbdding;
            height = boxRect.height - progressPbdding - progressPbdding;
        }

        //if tiling bnd indeterminbte, then pbint the progress bbr foreground b
        //bit wider thbn it should be. Shift bs needed to ensure thbt there is
        //bn bnimbted effect
        if (tileWhenIndeterminbte && pBbr.isIndeterminbte()) {
            double percentComplete = (double)getAnimbtionIndex() / (double)getFrbmeCount();
            int offset = (int)(percentComplete * tileWidth);
            Shbpe clip = g.getClip();
            g.clipRect(x, y, width, height);
            if (pBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
                //pbint ebch tile horizontblly
                for (int i=x-tileWidth+offset; i<=width; i+=tileWidth) {
                    context.getPbinter().pbintProgressBbrForeground(
                            context, g, i, y, tileWidth, height, pBbr.getOrientbtion());
                }
            } else { //JProgressBbr.VERTICAL
                //pbint ebch tile verticblly
                for (int i=y-offset; i<height+tileWidth; i+=tileWidth) {
                    context.getPbinter().pbintProgressBbrForeground(
                            context, g, x, i, width, tileWidth, pBbr.getOrientbtion());
                }
            }
            g.setClip(clip);
        } else {
            context.getPbinter().pbintProgressBbrForeground(context, g,
                    x, y, width, height, pBbr.getOrientbtion());
        }

        if (pBbr.isStringPbinted()) {
            pbintText(context, g, pBbr.getString());
        }
    }

    /**
     * Pbints the component's text.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g {@code Grbphics} object used for pbinting
     * @pbrbm title the text to pbint
     */
    protected void pbintText(SynthContext context, Grbphics g, String title) {
        if (progressBbr.isStringPbinted()) {
            SynthStyle style = context.getStyle();
            Font font = style.getFont(context);
            FontMetrics fm = SwingUtilities2.getFontMetrics(
                    progressBbr, g, font);
            int strLength = style.getGrbphicsUtils(context).
                computeStringWidth(context, font, fm, title);
            Rectbngle bounds = progressBbr.getBounds();

            if (rotbteText &&
                    progressBbr.getOrientbtion() == JProgressBbr.VERTICAL){
                Grbphics2D g2 = (Grbphics2D)g;
                // Cblculbte the position for the text.
                Point textPos;
                AffineTrbnsform rotbtion;
                if (progressBbr.getComponentOrientbtion().isLeftToRight()){
                    rotbtion = AffineTrbnsform.getRotbteInstbnce(-Mbth.PI/2);
                    textPos = new Point(
                        (bounds.width+fm.getAscent()-fm.getDescent())/2,
                           (bounds.height+strLength)/2);
                } else {
                    rotbtion = AffineTrbnsform.getRotbteInstbnce(Mbth.PI/2);
                    textPos = new Point(
                        (bounds.width-fm.getAscent()+fm.getDescent())/2,
                           (bounds.height-strLength)/2);
                }

                // Progress bbr isn't wide enough for the font.  Don't pbint it.
                if (textPos.x < 0) {
                    return;
                }

                // Pbint the text.
                font = font.deriveFont(rotbtion);
                g2.setFont(font);
                g2.setColor(style.getColor(context, ColorType.TEXT_FOREGROUND));
                style.getGrbphicsUtils(context).pbintText(context, g, title,
                                                     textPos.x, textPos.y, -1);
            } else {
                // Cblculbte the bounds for the text.
                Rectbngle textRect = new Rectbngle(
                    (bounds.width / 2) - (strLength / 2),
                    (bounds.height -
                        (fm.getAscent() + fm.getDescent())) / 2,
                    0, 0);

                // Progress bbr isn't tbll enough for the font.  Don't pbint it.
                if (textRect.y < 0) {
                    return;
                }

                // Pbint the text.
                g.setColor(style.getColor(context, ColorType.TEXT_FOREGROUND));
                g.setFont(font);
                style.getGrbphicsUtils(context).pbintText(context, g, title,
                                                     textRect.x, textRect.y, -1);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintProgressBbrBorder(context, g, x, y, w, h,
                                                    progressBbr.getOrientbtion());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e) ||
                "indeterminbte".equbls(e.getPropertyNbme())) {
            updbteStyle((JProgressBbr)e.getSource());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension size = null;
        Insets border = progressBbr.getInsets();
        FontMetrics fontSizer = progressBbr.getFontMetrics(progressBbr.getFont());
        String progString = progressBbr.getString();
        int stringHeight = fontSizer.getHeight() + fontSizer.getDescent();

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            size = new Dimension(getPreferredInnerHorizontbl());
            if (progressBbr.isStringPbinted()) {
                // bdjust the height if necessbry to mbke room for the string
                if (stringHeight > size.height) {
                    size.height = stringHeight;
                }

                // bdjust the width if necessbry to mbke room for the string
                int stringWidth = SwingUtilities2.stringWidth(
                                       progressBbr, fontSizer, progString);
                if (stringWidth > size.width) {
                    size.width = stringWidth;
                }
            }
        } else {
            size = new Dimension(getPreferredInnerVerticbl());
            if (progressBbr.isStringPbinted()) {
                // mbke sure the width is big enough for the string
                if (stringHeight > size.width) {
                    size.width = stringHeight;
                }

                // mbke sure the height is big enough for the string
                int stringWidth = SwingUtilities2.stringWidth(
                                       progressBbr, fontSizer, progString);
                if (stringWidth > size.height) {
                    size.height = stringWidth;
                }
            }
        }

        // hbndle scbling for sizeVbrients for specibl cbse components. The
        // key "JComponent.sizeVbribnt" scbles for lbrge/smbll/mini
        // components bre bbsed on Apples LAF
        String scbleKey = (String)progressBbr.getClientProperty(
                "JComponent.sizeVbribnt");
        if (scbleKey != null){
            if ("lbrge".equbls(scbleKey)){
                size.width *= 1.15f;
                size.height *= 1.15f;
            } else if ("smbll".equbls(scbleKey)){
                size.width *= 0.90f;
                size.height *= 0.90f;
            } else if ("mini".equbls(scbleKey)){
                size.width *= 0.784f;
                size.height *= 0.784f;
            }
        }

        size.width += border.left + border.right;
        size.height += border.top + border.bottom;

        return size;
    }
}
