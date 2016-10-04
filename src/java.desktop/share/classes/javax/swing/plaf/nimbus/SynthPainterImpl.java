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

import jbvb.bwt.*;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.synth.SynthContext;
import jbvbx.swing.plbf.synth.SynthPbinter;
import jbvbx.swing.plbf.synth.SynthConstbnts;

import jbvbx.swing.Pbinter;


clbss SynthPbinterImpl extends SynthPbinter {
    privbte NimbusStyle style;

    SynthPbinterImpl(NimbusStyle style) {
        this.style = style;
    }

    /**
     * Pbint the provided pbinter using the provided trbnsform bt the specified
     * position bnd size. Hbndles if g is b non 2D Grbphics by pbinting vib b
     * BufferedImbge.
     */
    privbte void pbint(Pbinter<Object> p, SynthContext ctx, Grbphics g, int x, int y,
                       int w, int h, AffineTrbnsform trbnsform) {
        if (p != null) {
            if (g instbnceof Grbphics2D){
                Grbphics2D gfx = (Grbphics2D)g;
                if (trbnsform!=null){
                    gfx.trbnsform(trbnsform);
                }
                gfx.trbnslbte(x, y);
                p.pbint(gfx, ctx.getComponent(), w, h);
                gfx.trbnslbte(-x, -y);
                if (trbnsform!=null){
                    try {
                        gfx.trbnsform(trbnsform.crebteInverse());
                    } cbtch (NoninvertibleTrbnsformException e) {
                        // this should never hbppen bs we bre in control of bll
                        // cblls into this method bnd only ever pbss in simple
                        // trbnsforms of rotbte, flip bnd trbnslbtes
                        e.printStbckTrbce();
                    }
                }
            } else {
                // use imbge if we bre printing to b Jbvb 1.1 PrintGrbphics bs
                // it is not b instbnce of Grbphics2D
                BufferedImbge img = new BufferedImbge(w,h,
                        BufferedImbge.TYPE_INT_ARGB);
                Grbphics2D gfx = img.crebteGrbphics();
                if (trbnsform!=null){
                    gfx.trbnsform(trbnsform);
                }
                p.pbint(gfx, ctx.getComponent(), w, h);
                gfx.dispose();
                g.drbwImbge(img,x,y,null);
                img = null;
            }
        }
    }

    privbte void pbintBbckground(SynthContext ctx, Grbphics g, int x, int y,
                                 int w, int h, AffineTrbnsform trbnsform) {
        // if the bbckground color of the component is 100% trbnspbrent
        // then we should not pbint bny bbckground grbphics. This is b solution
        // for there being no wby of turning off Nimbus bbckground pbinting bs
        // bbsic components bre bll non-opbque by defbult.
        Component c = ctx.getComponent();
        Color bg = (c != null) ? c.getBbckground() : null;
        if (bg == null || bg.getAlphb() > 0){

            Pbinter<Object> bbckgroundPbinter = style.getBbckgroundPbinter(ctx);
            if (bbckgroundPbinter != null) {
                pbint(bbckgroundPbinter, ctx, g, x, y, w, h,trbnsform);
            }
        }
    }

    privbte void pbintForeground(SynthContext ctx, Grbphics g, int x, int y,
                                 int w, int h, AffineTrbnsform trbnsform) {
        Pbinter<Object> foregroundPbinter = style.getForegroundPbinter(ctx);
        if (foregroundPbinter != null) {
            pbint(foregroundPbinter, ctx, g, x, y, w, h,trbnsform);
        }
    }

    privbte void pbintBorder(SynthContext ctx, Grbphics g, int x, int y, int w,
                             int h, AffineTrbnsform trbnsform) {
        Pbinter<Object> borderPbinter = style.getBorderPbinter(ctx);
        if (borderPbinter != null) {
            pbint(borderPbinter, ctx, g, x, y, w, h,trbnsform);
        }
    }

    privbte void pbintBbckground(SynthContext ctx, Grbphics g, int x, int y, int w, int h, int orientbtion) {
        Component c = ctx.getComponent();
        boolebn ltr = c.getComponentOrientbtion().isLeftToRight();
        // Don't RTL flip JSpliders bs they hbndle it internbly
        if (ctx.getComponent() instbnceof JSlider) ltr = true;

        if (orientbtion == SwingConstbnts.VERTICAL && ltr) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.scble(-1, 1);
            trbnsform.rotbte(Mbth.toRbdibns(90));
            pbintBbckground(ctx, g, y, x, h, w, trbnsform);
        } else if (orientbtion == SwingConstbnts.VERTICAL) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.rotbte(Mbth.toRbdibns(90));
            trbnsform.trbnslbte(0,-(x+w));
            pbintBbckground(ctx, g, y, x, h, w, trbnsform);
        } else if (orientbtion == SwingConstbnts.HORIZONTAL && ltr) {
            pbintBbckground(ctx, g, x, y, w, h, null);
        } else {
            //horizontbl bnd right-to-left orientbtion
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBbckground(ctx, g, 0, 0, w, h, trbnsform);
        }
    }

    privbte void pbintBorder(SynthContext ctx, Grbphics g, int x, int y, int w, int h, int orientbtion) {
        Component c = ctx.getComponent();
        boolebn ltr = c.getComponentOrientbtion().isLeftToRight();
        if (orientbtion == SwingConstbnts.VERTICAL && ltr) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.scble(-1, 1);
            trbnsform.rotbte(Mbth.toRbdibns(90));
            pbintBorder(ctx, g, y, x, h, w, trbnsform);
        } else if (orientbtion == SwingConstbnts.VERTICAL) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.rotbte(Mbth.toRbdibns(90));
            trbnsform.trbnslbte(0, -(x + w));
            pbintBorder(ctx, g, y, 0, h, w, trbnsform);
        } else if (orientbtion == SwingConstbnts.HORIZONTAL && ltr) {
            pbintBorder(ctx, g, x, y, w, h, null);
        } else {
            //horizontbl bnd right-to-left orientbtion
            pbintBorder(ctx, g, x, y, w, h, null);
        }
    }

    privbte void pbintForeground(SynthContext ctx, Grbphics g, int x, int y, int w, int h, int orientbtion) {
        Component c = ctx.getComponent();
        boolebn ltr = c.getComponentOrientbtion().isLeftToRight();
        if (orientbtion == SwingConstbnts.VERTICAL && ltr) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.scble(-1, 1);
            trbnsform.rotbte(Mbth.toRbdibns(90));
            pbintForeground(ctx, g, y, x, h, w, trbnsform);
        } else if (orientbtion == SwingConstbnts.VERTICAL) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.rotbte(Mbth.toRbdibns(90));
            trbnsform.trbnslbte(0, -(x + w));
            pbintForeground(ctx, g, y, 0, h, w, trbnsform);
        } else if (orientbtion == SwingConstbnts.HORIZONTAL && ltr) {
            pbintForeground(ctx, g, x, y, w, h, null);
        } else {
            //horizontbl bnd right-to-left orientbtion
            pbintForeground(ctx, g, x, y, w, h, null);
        }
    }

    /**
     * Pbints the bbckground of bn brrow button. Arrow buttons bre crebted by
     * some components, such bs <code>JScrollBbr</code>.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintArrowButtonBbckground(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h) {
        if (context.getComponent().getComponentOrientbtion().isLeftToRight()){
            pbintBbckground(context, g, x, y, w, h, null);
        } else {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBbckground(context, g, 0, 0, w, h, trbnsform);
        }
    }

    /**
     * Pbints the border of bn brrow button. Arrow buttons bre crebted by
     * some components, such bs <code>JScrollBbr</code>.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintArrowButtonBorder(SynthContext context,
                                       Grbphics g, int x, int y,
                                       int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the foreground of bn brrow button. This method is responsible
     * for drbwing b grbphicbl representbtion of b direction, typicblly
     * bn brrow. Arrow buttons bre crebted by
     * some components, such bs <code>JScrollBbr</code>
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm direction One of SwingConstbnts.NORTH, SwingConstbnts.SOUTH
     *                  SwingConstbnts.EAST or SwingConstbnts.WEST
     */
    public void pbintArrowButtonForeground(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h,
                                           int direction) {
        //bssume thbt the pbinter is brrbnged with the brrow pointing... LEFT?
        String compNbme = context.getComponent().getNbme();
        boolebn ltr = context.getComponent().
                getComponentOrientbtion().isLeftToRight();
        // The hbrd coding for spinners here needs to be replbced by b more
        // generbl method for disbbling rotbtion
        if ("Spinner.nextButton".equbls(compNbme) ||
                "Spinner.previousButton".equbls(compNbme)) {
            if (ltr){
                pbintForeground(context, g, x, y, w, h, null);
            } else {
                AffineTrbnsform trbnsform = new AffineTrbnsform();
                trbnsform.trbnslbte(w, 0);
                trbnsform.scble(-1, 1);
                pbintForeground(context, g, x, y, w, h, trbnsform);
            }
        } else if (direction == SwingConstbnts.WEST) {
            pbintForeground(context, g, x, y, w, h, null);
        } else if (direction == SwingConstbnts.NORTH) {
            if (ltr){
                AffineTrbnsform trbnsform = new AffineTrbnsform();
                trbnsform.scble(-1, 1);
                trbnsform.rotbte(Mbth.toRbdibns(90));
                pbintForeground(context, g, y, 0, h, w, trbnsform);
            } else {
                AffineTrbnsform trbnsform = new AffineTrbnsform();
                trbnsform.rotbte(Mbth.toRbdibns(90));
                trbnsform.trbnslbte(0, -(x + w));
                pbintForeground(context, g, y, 0, h, w, trbnsform);
            }
        } else if (direction == SwingConstbnts.EAST) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(w, 0);
            trbnsform.scble(-1, 1);
            pbintForeground(context, g, x, y, w, h, trbnsform);
        } else if (direction == SwingConstbnts.SOUTH) {
            if (ltr){
                AffineTrbnsform trbnsform = new AffineTrbnsform();
                trbnsform.rotbte(Mbth.toRbdibns(-90));
                trbnsform.trbnslbte(-h, 0);
                pbintForeground(context, g, y, x, h, w, trbnsform);
            } else {
                AffineTrbnsform trbnsform = new AffineTrbnsform();
                trbnsform.scble(-1, 1);
                trbnsform.rotbte(Mbth.toRbdibns(-90));
                trbnsform.trbnslbte(-(h+y), -(w+x));
                pbintForeground(context, g, y, x, h, w, trbnsform);
            }
        }
    }

    /**
     * Pbints the bbckground of b button.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintButtonBbckground(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b button.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintButtonBorder(SynthContext context,
                                  Grbphics g, int x, int y,
                                  int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b check box menu item.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintCheckBoxMenuItemBbckground(SynthContext context,
                                                Grbphics g, int x, int y,
                                                int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b check box menu item.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintCheckBoxMenuItemBorder(SynthContext context,
                                            Grbphics g, int x, int y,
                                            int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b check box.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintCheckBoxBbckground(SynthContext context,
                                        Grbphics g, int x, int y,
                                        int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b check box.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintCheckBoxBorder(SynthContext context,
                                    Grbphics g, int x, int y,
                                    int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b color chooser.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintColorChooserBbckground(SynthContext context,
                                            Grbphics g, int x, int y,
                                            int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b color chooser.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintColorChooserBorder(SynthContext context,
                                        Grbphics g, int x, int y,
                                        int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b combo box.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintComboBoxBbckground(SynthContext context,
                                        Grbphics g, int x, int y,
                                        int w, int h) {
        if (context.getComponent().getComponentOrientbtion().isLeftToRight()){
            pbintBbckground(context, g, x, y, w, h, null);
        } else {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBbckground(context, g, 0, 0, w, h, trbnsform);
        }
    }

    /**
     * Pbints the border of b combo box.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintComboBoxBorder(SynthContext context,
                                        Grbphics g, int x, int y,
                                        int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b desktop icon.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintDesktopIconBbckground(SynthContext context,
                                        Grbphics g, int x, int y,
                                        int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b desktop icon.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintDesktopIconBorder(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b desktop pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintDesktopPbneBbckground(SynthContext context,
                                           Grbphics g, int x, int y,
                                           int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b desktop pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintDesktopPbneBorder(SynthContext context,
                                       Grbphics g, int x, int y,
                                       int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of bn editor pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintEditorPbneBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of bn editor pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintEditorPbneBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b file chooser.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintFileChooserBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b file chooser.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintFileChooserBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b formbtted text field.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintFormbttedTextFieldBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        if (context.getComponent().getComponentOrientbtion().isLeftToRight()){
            pbintBbckground(context, g, x, y, w, h, null);
        } else {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBbckground(context, g, 0, 0, w, h, trbnsform);
        }
    }

    /**
     * Pbints the border of b formbtted text field.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintFormbttedTextFieldBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        if (context.getComponent().getComponentOrientbtion().isLeftToRight()){
            pbintBorder(context, g, x, y, w, h, null);
        } else {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBorder(context, g, 0, 0, w, h, trbnsform);
        }
    }

    /**
     * Pbints the bbckground of bn internbl frbme title pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintInternblFrbmeTitlePbneBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of bn internbl frbme title pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintInternblFrbmeTitlePbneBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of bn internbl frbme.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintInternblFrbmeBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of bn internbl frbme.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintInternblFrbmeBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b lbbel.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintLbbelBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b lbbel.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintLbbelBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b list.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintListBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b list.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintListBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b menu bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintMenuBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b menu bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintMenuBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b menu item.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintMenuItemBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b menu item.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintMenuItemBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b menu.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintMenuBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b menu.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintMenuBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of bn option pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintOptionPbneBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of bn option pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintOptionPbneBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b pbnel.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintPbnelBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b pbnel.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintPbnelBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b pbssword field.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintPbsswordFieldBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b pbssword field.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintPbsswordFieldBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b popup menu.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintPopupMenuBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b popup menu.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintPopupMenuBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b progress bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintProgressBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b progress bbr. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion one of <code>JProgressBbr.HORIZONTAL</code> or
     *                    <code>JProgressBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintProgressBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of b progress bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintProgressBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b progress bbr. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion one of <code>JProgressBbr.HORIZONTAL</code> or
     *                    <code>JProgressBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintProgressBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the foreground of b progress bbr. is responsible for
     * providing bn indicbtion of the progress of the progress bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion one of <code>JProgressBbr.HORIZONTAL</code> or
     *                    <code>JProgressBbr.VERTICAL</code>
     */
    public void pbintProgressBbrForeground(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintForeground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of b rbdio button menu item.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintRbdioButtonMenuItemBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b rbdio button menu item.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintRbdioButtonMenuItemBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b rbdio button.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintRbdioButtonBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b rbdio button.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintRbdioButtonBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b root pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintRootPbneBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b root pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintRootPbneBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b scrollbbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintScrollBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b scrollbbr. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion Orientbtion of the JScrollBbr, one of
     *                    <code>JScrollBbr.HORIZONTAL</code> or
     *                    <code>JScrollBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintScrollBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of b scrollbbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintScrollBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b scrollbbr. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion Orientbtion of the JScrollBbr, one of
     *                    <code>JScrollBbr.HORIZONTAL</code> or
     *                    <code>JScrollBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintScrollBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of the thumb of b scrollbbr. The thumb provides
     * b grbphicbl indicbtion bs to how much of the Component is visible in b
     * <code>JScrollPbne</code>.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion Orientbtion of the JScrollBbr, one of
     *                    <code>JScrollBbr.HORIZONTAL</code> or
     *                    <code>JScrollBbr.VERTICAL</code>
     */
    public void pbintScrollBbrThumbBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of the thumb of b scrollbbr. The thumb provides
     * b grbphicbl indicbtion bs to how much of the Component is visible in b
     * <code>JScrollPbne</code>.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion Orientbtion of the JScrollBbr, one of
     *                    <code>JScrollBbr.HORIZONTAL</code> or
     *                    <code>JScrollBbr.VERTICAL</code>
     */
    public void pbintScrollBbrThumbBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of the trbck of b scrollbbr. The trbck contbins
     * the thumb.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintScrollBbrTrbckBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the trbck of b scrollbbr. The trbck contbins
     * the thumb. This implementbtion invokes the method of the sbme nbme without
     * the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion Orientbtion of the JScrollBbr, one of
     *                    <code>JScrollBbr.HORIZONTAL</code> or
     *                    <code>JScrollBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintScrollBbrTrbckBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of the trbck of b scrollbbr. The trbck contbins
     * the thumb.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintScrollBbrTrbckBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the trbck of b scrollbbr. The trbck contbins
     * the thumb. This implementbtion invokes the method of the sbme nbme without
     * the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion Orientbtion of the JScrollBbr, one of
     *                    <code>JScrollBbr.HORIZONTAL</code> or
     *                    <code>JScrollBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintScrollBbrTrbckBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of b scroll pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintScrollPbneBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b scroll pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintScrollPbneBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b sepbrbtor.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSepbrbtorBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b sepbrbtor. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSepbrbtor.HORIZONTAL</code> or
     *                           <code>JSepbrbtor.VERTICAL</code>
     * @since 1.6
     */
    public void pbintSepbrbtorBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of b sepbrbtor.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSepbrbtorBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b sepbrbtor. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSepbrbtor.HORIZONTAL</code> or
     *                           <code>JSepbrbtor.VERTICAL</code>
     * @since 1.6
     */
    public void pbintSepbrbtorBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the foreground of b sepbrbtor.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSepbrbtor.HORIZONTAL</code> or
     *                           <code>JSepbrbtor.VERTICAL</code>
     */
    public void pbintSepbrbtorForeground(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintForeground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of b slider.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSliderBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b slider. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void pbintSliderBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of b slider.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSliderBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b slider. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void pbintSliderBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of the thumb of b slider.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     */
    public void pbintSliderThumbBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        if (context.getComponent().getClientProperty(
                "Slider.pbintThumbArrowShbpe") == Boolebn.TRUE){
            if (orientbtion == JSlider.HORIZONTAL){
                orientbtion = JSlider.VERTICAL;
            } else {
                orientbtion = JSlider.HORIZONTAL;
            }
            pbintBbckground(context, g, x, y, w, h, orientbtion);
        } else {
            pbintBbckground(context, g, x, y, w, h, orientbtion);
        }
    }

    /**
     * Pbints the border of the thumb of b slider.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     */
    public void pbintSliderThumbBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of the trbck of b slider.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSliderTrbckBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the trbck of b slider. This implementbtion invokes
     * the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void pbintSliderTrbckBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of the trbck of b slider.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSliderTrbckBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the trbck of b slider. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void pbintSliderTrbckBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of b spinner.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSpinnerBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b spinner.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSpinnerBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the divider of b split pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSplitPbneDividerBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the divider of b split pbne. This implementbtion
     * invokes the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                           <code>JSplitPbne.VERTICAL_SPLIT</code>
     * @since 1.6
     */
    public void pbintSplitPbneDividerBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
       if (orientbtion == JSplitPbne.HORIZONTAL_SPLIT) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.scble(-1, 1);
            trbnsform.rotbte(Mbth.toRbdibns(90));
            pbintBbckground(context, g, y, x, h, w, trbnsform);
       } else {
            pbintBbckground(context, g, x, y, w, h, null);
        }
    }

    /**
     * Pbints the foreground of the divider of b split pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                           <code>JSplitPbne.VERTICAL_SPLIT</code>
     */
    public void pbintSplitPbneDividerForeground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintForeground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the divider, when the user is drbgging the divider, of b
     * split pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JSplitPbne.HORIZONTAL_SPLIT</code> or
     *                           <code>JSplitPbne.VERTICAL_SPLIT</code>
     */
    public void pbintSplitPbneDrbgDivider(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b split pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSplitPbneBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b split pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintSplitPbneBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbbedPbneBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbbedPbneBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the breb behind the tbbs of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbbedPbneTbbArebBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the breb behind the tbbs of b tbbbed pbne.
     * This implementbtion invokes the method of the sbme nbme without the
     * orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JTbbbedPbne.TOP</code>,
     *                    <code>JTbbbedPbne.LEFT</code>,
     *                    <code>JTbbbedPbne.BOTTOM</code>, or
     *                    <code>JTbbbedPbne.RIGHT</code>
     * @since 1.6
     */
    public void pbintTbbbedPbneTbbArebBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        if (orientbtion == JTbbbedPbne.LEFT) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.scble(-1, 1);
            trbnsform.rotbte(Mbth.toRbdibns(90));
            pbintBbckground(context, g, y, x, h, w, trbnsform);
        } else if (orientbtion == JTbbbedPbne.RIGHT) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.rotbte(Mbth.toRbdibns(90));
            trbnsform.trbnslbte(0, -(x + w));
            pbintBbckground(context, g, y, 0, h, w, trbnsform);
        } else if (orientbtion == JTbbbedPbne.BOTTOM) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(1, -1);
            trbnsform.trbnslbte(0,-h);
            pbintBbckground(context, g, 0, 0, w, h, trbnsform);
        } else {
            pbintBbckground(context, g, x, y, w, h, null);
        }
    }

    /**
     * Pbints the border of the breb behind the tbbs of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbbedPbneTbbArebBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the breb behind the tbbs of b tbbbed pbne. This
     * implementbtion invokes the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JTbbbedPbne.TOP</code>,
     *                    <code>JTbbbedPbne.LEFT</code>,
     *                    <code>JTbbbedPbne.BOTTOM</code>, or
     *                    <code>JTbbbedPbne.RIGHT</code>
     * @since 1.6
     */
    public void pbintTbbbedPbneTbbArebBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tbb of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm tbbIndex Index of tbb being pbinted.
     */
    public void pbintTbbbedPbneTbbBbckground(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h,
                                         int tbbIndex) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tbb of b tbbbed pbne. This implementbtion
     * invokes the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm tbbIndex Index of tbb being pbinted.
     * @pbrbm orientbtion One of <code>JTbbbedPbne.TOP</code>,
     *                    <code>JTbbbedPbne.LEFT</code>,
     *                    <code>JTbbbedPbne.BOTTOM</code>, or
     *                    <code>JTbbbedPbne.RIGHT</code>
     * @since 1.6
     */
    public void pbintTbbbedPbneTbbBbckground(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h,
                                         int tbbIndex, int orientbtion) {
        if (orientbtion == JTbbbedPbne.LEFT) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.scble(-1, 1);
            trbnsform.rotbte(Mbth.toRbdibns(90));
            pbintBbckground(context, g, y, x, h, w, trbnsform);
        } else if (orientbtion == JTbbbedPbne.RIGHT) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.rotbte(Mbth.toRbdibns(90));
            trbnsform.trbnslbte(0, -(x + w));
            pbintBbckground(context, g, y, 0, h, w, trbnsform);
        } else if (orientbtion == JTbbbedPbne.BOTTOM) {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(1, -1);
            trbnsform.trbnslbte(0,-h);
            pbintBbckground(context, g, 0, 0, w, h, trbnsform);
        } else {
            pbintBbckground(context, g, x, y, w, h, null);
        }
    }

    /**
     * Pbints the border of b tbb of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm tbbIndex Index of tbb being pbinted.
     */
    public void pbintTbbbedPbneTbbBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h,
                                         int tbbIndex) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b tbb of b tbbbed pbne. This implementbtion invokes
     * the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm tbbIndex Index of tbb being pbinted.
     * @pbrbm orientbtion One of <code>JTbbbedPbne.TOP</code>,
     *                    <code>JTbbbedPbne.LEFT</code>,
     *                    <code>JTbbbedPbne.BOTTOM</code>, or
     *                    <code>JTbbbedPbne.RIGHT</code>
     * @since 1.6
     */
    public void pbintTbbbedPbneTbbBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h,
                                         int tbbIndex, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the breb thbt contbins the content of the
     * selected tbb of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbbedPbneContentBbckground(SynthContext context,
                                         Grbphics g, int x, int y, int w,
                                         int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the breb thbt contbins the content of the
     * selected tbb of b tbbbed pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbbedPbneContentBorder(SynthContext context, Grbphics g,
                                         int x, int y, int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the hebder of b tbble.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbleHebderBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the hebder of b tbble.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbleHebderBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tbble.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbleBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b tbble.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTbbleBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b text breb.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTextArebBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b text breb.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTextArebBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b text pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTextPbneBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b text pbne.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTextPbneBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b text field.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTextFieldBbckground(SynthContext context,
                                          Grbphics g, int x, int y,
                                          int w, int h) {
        if (context.getComponent().getComponentOrientbtion().isLeftToRight()){
            pbintBbckground(context, g, x, y, w, h, null);
        } else {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBbckground(context, g, 0, 0, w, h, trbnsform);
        }
    }

    /**
     * Pbints the border of b text field.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTextFieldBorder(SynthContext context,
                                      Grbphics g, int x, int y,
                                      int w, int h) {
        if (context.getComponent().getComponentOrientbtion().isLeftToRight()){
            pbintBorder(context, g, x, y, w, h, null);
        } else {
            AffineTrbnsform trbnsform = new AffineTrbnsform();
            trbnsform.trbnslbte(x,y);
            trbnsform.scble(-1, 1);
            trbnsform.trbnslbte(-w,0);
            pbintBorder(context, g, 0, 0, w, h, trbnsform);
        }
    }

    /**
     * Pbints the bbckground of b toggle button.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToggleButtonBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b toggle button.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToggleButtonBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tool bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tool bbr. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JToolBbr.HORIZONTAL</code> or
     *                           <code>JToolBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintToolBbrBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of b tool bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b tool bbr. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JToolBbr.HORIZONTAL</code> or
     *                           <code>JToolBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintToolBbrBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of the tool bbr's content breb.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolBbrContentBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the tool bbr's content breb. This implementbtion
     * invokes the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JToolBbr.HORIZONTAL</code> or
     *                           <code>JToolBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintToolBbrContentBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of the content breb of b tool bbr.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolBbrContentBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the content breb of b tool bbr. This implementbtion
     * invokes the method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JToolBbr.HORIZONTAL</code> or
     *                           <code>JToolBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintToolBbrContentBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of the window contbining the tool bbr when it
     * hbs been detbched from its primbry frbme.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the window contbining the tool bbr when it
     * hbs been detbched from its primbry frbme. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JToolBbr.HORIZONTAL</code> or
     *                           <code>JToolBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintToolBbrDrbgWindowBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h, int orientbtion) {
        pbintBbckground(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the border of the window contbining the tool bbr when it
     * hbs been detbched from it's primbry frbme.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolBbrDrbgWindowBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the window contbining the tool bbr when it
     * hbs been detbched from it's primbry frbme. This implementbtion invokes the
     * method of the sbme nbme without the orientbtion.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     * @pbrbm orientbtion One of <code>JToolBbr.HORIZONTAL</code> or
     *                           <code>JToolBbr.VERTICAL</code>
     * @since 1.6
     */
    public void pbintToolBbrDrbgWindowBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h, int orientbtion) {
        pbintBorder(context, g, x, y, w, h, orientbtion);
    }

    /**
     * Pbints the bbckground of b tool tip.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolTipBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b tool tip.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintToolTipBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of b tree.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTreeBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b tree.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTreeBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the bbckground of the row contbining b cell in b tree.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTreeCellBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of the row contbining b cell in b tree.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTreeCellBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the focus indicbtor for b cell in b tree when it hbs focus.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintTreeCellFocus(SynthContext context,
                                   Grbphics g, int x, int y,
                                   int w, int h) {
        //TODO
    }

    /**
     * Pbints the bbckground of the viewport.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintViewportBbckground(SynthContext context,
                                     Grbphics g, int x, int y,
                                     int w, int h) {
        pbintBbckground(context, g, x, y, w, h, null);
    }

    /**
     * Pbints the border of b viewport.
     *
     * @pbrbm context SynthContext identifying the <code>JComponent</code> bnd
     *        <code>Region</code> to pbint to
     * @pbrbm g <code>Grbphics</code> to pbint to
     * @pbrbm x X coordinbte of the breb to pbint to
     * @pbrbm y Y coordinbte of the breb to pbint to
     * @pbrbm w Width of the breb to pbint to
     * @pbrbm h Height of the breb to pbint to
     */
    public void pbintViewportBorder(SynthContext context,
                                 Grbphics g, int x, int y,
                                 int w, int h) {
        pbintBorder(context, g, x, y, w, h, null);
    }
}
