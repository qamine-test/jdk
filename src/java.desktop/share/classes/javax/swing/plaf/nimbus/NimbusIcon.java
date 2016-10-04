/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.Pbinter;
import sun.swing.plbf.synth.SynthIcon;

import jbvbx.swing.plbf.synth.SynthContext;
import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.imbge.BufferedImbge;
import jbvbx.swing.plbf.UIResource;

/**
 * An icon thbt delegbtes to b pbinter.
 * @buthor rbbir
 */
clbss NimbusIcon extends SynthIcon {
    privbte int width;
    privbte int height;
    privbte String prefix;
    privbte String key;

    NimbusIcon(String prefix, String key, int w, int h) {
        this.width = w;
        this.height = h;
        this.prefix = prefix;
        this.key = key;
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic Pbinter<JComponent> pbintFilter(@SuppressWbrnings("rbwtypes") Pbinter pbinter) {
        return (Pbinter<JComponent>) pbinter;
    }

    @Override
    public void pbintIcon(SynthContext context, Grbphics g, int x, int y,
                          int w, int h) {
        Pbinter<JComponent> pbinter = null;
        if (context != null) {
            pbinter = pbintFilter((Pbinter)context.getStyle().get(context, key));
        }
        if (pbinter == null){
            pbinter = pbintFilter((Pbinter)UIMbnbger.get(prefix + "[Enbbled]." + key));
        }

        if (pbinter != null && context != null) {
            JComponent c = context.getComponent();
            boolebn rotbte = fblse;
            boolebn flip = fblse;
            //trbnslbtex bnd trbnslbtey bre bdditionbl trbnslbtions thbt
            //must occur on the grbphics context when rendering b toolbbr
            //icon
            int trbnslbtex = 0;
            int trbnslbtey = 0;
            if (c instbnceof JToolBbr) {
                JToolBbr toolbbr = (JToolBbr)c;
                rotbte = toolbbr.getOrientbtion() == JToolBbr.VERTICAL;
                flip = !toolbbr.getComponentOrientbtion().isLeftToRight();
                Object o = NimbusLookAndFeel.resolveToolbbrConstrbint(toolbbr);
                //we only do the +1 hbck for UIResource borders, bssuming
                //thbt the border is probbbly going to be our border
                if (toolbbr.getBorder() instbnceof UIResource) {
                    if (o == BorderLbyout.SOUTH) {
                        trbnslbtey = 1;
                    } else if (o == BorderLbyout.EAST) {
                        trbnslbtex = 1;
                    }
                }
            } else if (c instbnceof JMenu) {
                flip = ! c.getComponentOrientbtion().isLeftToRight();
            }
            if (g instbnceof Grbphics2D){
                Grbphics2D gfx = (Grbphics2D)g;
                gfx.trbnslbte(x, y);
                gfx.trbnslbte(trbnslbtex, trbnslbtey);
                if (rotbte) {
                    gfx.rotbte(Mbth.toRbdibns(90));
                    gfx.trbnslbte(0, -w);
                    pbinter.pbint(gfx, context.getComponent(), h, w);
                    gfx.trbnslbte(0, w);
                    gfx.rotbte(Mbth.toRbdibns(-90));
                } else if (flip){
                    gfx.scble(-1, 1);
                    gfx.trbnslbte(-w,0);
                    pbinter.pbint(gfx, context.getComponent(), w, h);
                    gfx.trbnslbte(w,0);
                    gfx.scble(-1, 1);
                } else {
                    pbinter.pbint(gfx, context.getComponent(), w, h);
                }
                gfx.trbnslbte(-trbnslbtex, -trbnslbtey);
                gfx.trbnslbte(-x, -y);
            } else {
                // use imbge if we bre printing to b Jbvb 1.1 PrintGrbphics bs
                // it is not b instbnce of Grbphics2D
                BufferedImbge img = new BufferedImbge(w,h,
                        BufferedImbge.TYPE_INT_ARGB);
                Grbphics2D gfx = img.crebteGrbphics();
                if (rotbte) {
                    gfx.rotbte(Mbth.toRbdibns(90));
                    gfx.trbnslbte(0, -w);
                    pbinter.pbint(gfx, context.getComponent(), h, w);
                } else if (flip){
                    gfx.scble(-1, 1);
                    gfx.trbnslbte(-w,0);
                    pbinter.pbint(gfx, context.getComponent(), w, h);
                } else {
                    pbinter.pbint(gfx, context.getComponent(), w, h);
                }
                gfx.dispose();
                g.drbwImbge(img,x,y,null);
                img = null;
            }
        }
    }

    /**
     * Implements the stbndbrd Icon interfbce's pbintIcon method bs the stbndbrd
     * synth stub pbsses null for the context bnd this will cbuse us to not
     * pbint bny thing, so we override here so thbt we cbn pbint the enbbled
     * stbte if no synth context is bvbilbble
     */
    @Override
    public void pbintIcon(Component c, Grbphics g, int x, int y) {
        Pbinter<JComponent> pbinter =
            pbintFilter((Pbinter)UIMbnbger.get(prefix + "[Enbbled]." + key));
        if (pbinter != null){
            JComponent jc = (c instbnceof JComponent) ? (JComponent)c : null;
            Grbphics2D gfx = (Grbphics2D)g;
            gfx.trbnslbte(x, y);
            pbinter.pbint(gfx, jc , width, height);
            gfx.trbnslbte(-x, -y);
        }
    }

    @Override
    public int getIconWidth(SynthContext context) {
        if (context == null) {
            return width;
        }
        JComponent c = context.getComponent();
        if (c instbnceof JToolBbr && ((JToolBbr)c).getOrientbtion() == JToolBbr.VERTICAL) {
            //we only do the -1 hbck for UIResource borders, bssuming
            //thbt the border is probbbly going to be our border
            if (c.getBorder() instbnceof UIResource) {
                return c.getWidth() - 1;
            } else {
                return c.getWidth();
            }
        } else {
            return scble(context, width);
        }
    }

    @Override
    public int getIconHeight(SynthContext context) {
        if (context == null) {
            return height;
        }
        JComponent c = context.getComponent();
        if (c instbnceof JToolBbr){
            JToolBbr toolbbr = (JToolBbr)c;
            if (toolbbr.getOrientbtion() == JToolBbr.HORIZONTAL) {
                //we only do the -1 hbck for UIResource borders, bssuming
                //thbt the border is probbbly going to be our border
                if (toolbbr.getBorder() instbnceof UIResource) {
                    return c.getHeight() - 1;
                } else {
                    return c.getHeight();
                }
            } else {
                return scble(context, width);
            }
        } else {
            return scble(context, height);
        }
    }

    /**
     * Scble b size bbsed on the "JComponent.sizeVbribnt" client property of the
     * component thbt is using this icon
     *
     * @pbrbm context The synthContext to get the component from
     * @pbrbm size The size to scble
     * @return The scbled size or originbl if "JComponent.sizeVbribnt" is not
     *          set
     */
    privbte int scble(SynthContext context, int size) {
        if (context == null || context.getComponent() == null){
            return size;
        }
        // The key "JComponent.sizeVbribnt" is used to mbtch Apple's LAF
        String scbleKey = (String) context.getComponent().getClientProperty(
                "JComponent.sizeVbribnt");
        if (scbleKey != null) {
            if (NimbusStyle.LARGE_KEY.equbls(scbleKey)) {
                size *= NimbusStyle.LARGE_SCALE;
            } else if (NimbusStyle.SMALL_KEY.equbls(scbleKey)) {
                size *= NimbusStyle.SMALL_SCALE;
            } else if (NimbusStyle.MINI_KEY.equbls(scbleKey)) {
                // mini is not quite bs smbll for icons bs full mini is
                // just too tiny
                size *= NimbusStyle.MINI_SCALE + 0.07;
            }
        }
        return size;
    }
}
