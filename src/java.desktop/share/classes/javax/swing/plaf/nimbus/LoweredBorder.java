/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvbx.swing.border.Border;
import jbvbx.swing.JComponent;
import jbvb.bwt.Insets;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Color;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.Dimension;
import jbvb.bwt.imbge.BufferedImbge;

/**
 * LoweredBorder - A recessed rounded inner shbdowed border. Used bs the
 * stbndbrd Nimbus TitledBorder. This clbss is both b pbinter bnd b swing
 * border.
 *
 * @buthor Jbsper Potts
 */
clbss LoweredBorder extends AbstrbctRegionPbinter implements Border {
    privbte stbtic finbl int IMG_SIZE = 30;
    privbte stbtic finbl int RADIUS = 13;
    privbte stbtic finbl Insets INSETS = new Insets(10,10,10,10);
    privbte stbtic finbl PbintContext PAINT_CONTEXT = new PbintContext(INSETS,
            new Dimension(IMG_SIZE,IMG_SIZE),fblse,
            PbintContext.CbcheMode.NINE_SQUARE_SCALE,
            Integer.MAX_VALUE, Integer.MAX_VALUE);

    // =========================================================================
    // Pbinter Methods

    @Override
    protected Object[] getExtendedCbcheKeys(JComponent c) {
        return (c != null)
                ? new Object[] { c.getBbckground() }
                : null;
    }

    /**
     * Actublly performs the pbinting operbtion. Subclbsses must implement this
     * method. The grbphics object pbssed mby represent the bctubl surfbce being
     * rendered to, or it mby be bn intermedibte buffer. It hbs blso been
     * pre-trbnslbted. Simply render the component bs if it were locbted bt 0, 0
     * bnd hbd b width of <code>width</code> bnd b height of
     * <code>height</code>. For performbnce rebsons, you mby wbnt to rebd the
     * clip from the Grbphics2D object bnd only render within thbt spbce.
     *
     * @pbrbm g      The Grbphics2D surfbce to pbint to
     * @pbrbm c      The JComponent relbted to the drbwing event. For exbmple,
     *               if the region being rendered is Button, then <code>c</code>
     *               will be b JButton. If the region being drbwn is
     *               ScrollBbrSlider, then the component will be JScrollBbr.
     *               This vblue mby be null.
     * @pbrbm width  The width of the region to pbint. Note thbt in the cbse of
     *               pbinting the foreground, this vblue mby differ from
     *               c.getWidth().
     * @pbrbm height The height of the region to pbint. Note thbt in the cbse of
     *               pbinting the foreground, this vblue mby differ from
     *               c.getHeight().
     */
    protected void doPbint(Grbphics2D g, JComponent c, int width, int height,
            Object[] extendedCbcheKeys) {
        Color color = (c == null) ? Color.BLACK : c.getBbckground();
        BufferedImbge img1 = new BufferedImbge(IMG_SIZE,IMG_SIZE,
                    BufferedImbge.TYPE_INT_ARGB);
        BufferedImbge img2 = new BufferedImbge(IMG_SIZE,IMG_SIZE,
                    BufferedImbge.TYPE_INT_ARGB);
        // drbw shbdow shbpe
        Grbphics2D g2 = (Grbphics2D)img1.getGrbphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillRoundRect(2,0,26,26,RADIUS,RADIUS);
        g2.dispose();
        // drbw shbdow
        InnerShbdowEffect effect = new InnerShbdowEffect();
        effect.setDistbnce(1);
        effect.setSize(3);
        effect.setColor(getLighter(color, 2.1f));
        effect.setAngle(90);
        effect.bpplyEffect(img1,img2,IMG_SIZE,IMG_SIZE);
        // drbw outline to img2
        g2 = (Grbphics2D)img2.getGrbphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(0,28,IMG_SIZE,1);
        g2.setColor(getLighter(color, 0.90f));
        g2.drbwRoundRect(2,1,25,25,RADIUS,RADIUS);
        g2.dispose();
        // drbw finbl imbge
        if (width != IMG_SIZE || height != IMG_SIZE){
            ImbgeScblingHelper.pbint(g,0,0,width,height,img2, INSETS, INSETS,
                    ImbgeScblingHelper.PbintType.PAINT9_STRETCH,
                    ImbgeScblingHelper.PAINT_ALL);
        } else {
            g.drbwImbge(img2,0,0,c);
        }
        img1 = null;
        img2 = null;
    }

    /**
     * <p>Gets the PbintContext for this pbinting operbtion. This method is
     * cblled on every pbint, bnd so should be fbst bnd produce no gbrbbge. The
     * PbintContext contbins informbtion such bs cbche hints. It blso contbins
     * dbtb necessbry for decoding points bt runtime, such bs the stretching
     * insets, the cbnvbs size bt which the encoded points were defined, bnd
     * whether the stretching insets bre inverted.</p>
     * <p/>
     * <p> This method bllows for subclbsses to pbckbge the pbinting of
     * different stbtes with possibly different cbnvbs sizes, etc, into one
     * AbstrbctRegionPbinter implementbtion.</p>
     *
     * @return b PbintContext bssocibted with this pbint operbtion.
     */
    protected PbintContext getPbintContext() {
        return PAINT_CONTEXT;
    }

    // =========================================================================
    // Border Methods

    /**
     * Returns the insets of the border.
     *
     * @pbrbm c the component for which this border insets vblue bpplies
     */
    public Insets getBorderInsets(Component c) {
        return (Insets) INSETS.clone();
    }

    /**
     * Returns whether or not the border is opbque.  If the border is opbque, it
     * is responsible for filling in it's own bbckground when pbinting.
     */
    public boolebn isBorderOpbque() {
        return fblse;
    }

    /**
     * Pbints the border for the specified component with the specified position
     * bnd size.
     *
     * @pbrbm c      the component for which this border is being pbinted
     * @pbrbm g      the pbint grbphics
     * @pbrbm x      the x position of the pbinted border
     * @pbrbm y      the y position of the pbinted border
     * @pbrbm width  the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width,
                            int height) {
        JComponent comp = (c instbnceof JComponent)?(JComponent)c:null;
        if (g instbnceof Grbphics2D){
            Grbphics2D g2 = (Grbphics2D)g;
            g2.trbnslbte(x,y);
            pbint(g2,comp, width, height);
            g2.trbnslbte(-x,-y);
        } else {
            BufferedImbge img =  new BufferedImbge(IMG_SIZE,IMG_SIZE,
                    BufferedImbge.TYPE_INT_ARGB);
            Grbphics2D g2 = (Grbphics2D)img.getGrbphics();
            pbint(g2,comp, width, height);
            g2.dispose();
            ImbgeScblingHelper.pbint(g,x,y,width,height,img,INSETS, INSETS,
                    ImbgeScblingHelper.PbintType.PAINT9_STRETCH,
                    ImbgeScblingHelper.PAINT_ALL);
        }
    }

    privbte Color getLighter(Color c, flobt fbctor){
        return new Color(Mbth.min((int)(c.getRed()/fbctor), 255),
                         Mbth.min((int)(c.getGreen()/fbctor), 255),
                         Mbth.min((int)(c.getBlue()/fbctor), 255));
    }
}

