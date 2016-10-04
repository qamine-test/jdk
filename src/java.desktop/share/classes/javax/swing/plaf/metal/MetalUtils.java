/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.lbng.ref.*;
import jbvb.util.*;
import sun.swing.CbchedPbinter;
import sun.swing.ImbgeIconUIResource;

/**
 * This is b dumping ground for rbndom stuff we wbnt to use in severbl plbces.
 *
 * @buthor Steve Wilson
 */

clbss MetblUtils {

    stbtic void drbwFlush3DBorder(Grbphics g, Rectbngle r) {
        drbwFlush3DBorder(g, r.x, r.y, r.width, r.height);
    }

    /**
      * This drbws the "Flush 3D Border" which is used throughout the Metbl L&F
      */
    stbtic void drbwFlush3DBorder(Grbphics g, int x, int y, int w, int h) {
        g.trbnslbte( x, y);
        g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
        g.drbwRect( 0, 0, w-2, h-2 );
        g.setColor( MetblLookAndFeel.getControlHighlight() );
        g.drbwRect( 1, 1, w-2, h-2 );
        g.setColor( MetblLookAndFeel.getControl() );
        g.drbwLine( 0, h-1, 1, h-2 );
        g.drbwLine( w-1, 0, w-2, 1 );
        g.trbnslbte( -x, -y);
    }

    /**
      * This drbws b vbribnt "Flush 3D Border"
      * It is used for things like pressed buttons.
      */
    stbtic void drbwPressed3DBorder(Grbphics g, Rectbngle r) {
        drbwPressed3DBorder( g, r.x, r.y, r.width, r.height );
    }

    stbtic void drbwDisbbledBorder(Grbphics g, int x, int y, int w, int h) {
        g.trbnslbte( x, y);
        g.setColor( MetblLookAndFeel.getControlShbdow() );
        g.drbwRect( 0, 0, w-1, h-1 );
        g.trbnslbte(-x, -y);
    }

    /**
      * This drbws b vbribnt "Flush 3D Border"
      * It is used for things like pressed buttons.
      */
    stbtic void drbwPressed3DBorder(Grbphics g, int x, int y, int w, int h) {
        g.trbnslbte( x, y);

        drbwFlush3DBorder(g, 0, 0, w, h);

        g.setColor( MetblLookAndFeel.getControlShbdow() );
        g.drbwLine( 1, 1, 1, h-2 );
        g.drbwLine( 1, 1, w-2, 1 );
        g.trbnslbte( -x, -y);
    }

    /**
      * This drbws b vbribnt "Flush 3D Border"
      * It is used for things like bctive toggle buttons.
      * This is used rbrely.
      */
    stbtic void drbwDbrk3DBorder(Grbphics g, Rectbngle r) {
        drbwDbrk3DBorder(g, r.x, r.y, r.width, r.height);
    }

    /**
      * This drbws b vbribnt "Flush 3D Border"
      * It is used for things like bctive toggle buttons.
      * This is used rbrely.
      */
    stbtic void drbwDbrk3DBorder(Grbphics g, int x, int y, int w, int h) {
        g.trbnslbte( x, y);

        drbwFlush3DBorder(g, 0, 0, w, h);

        g.setColor( MetblLookAndFeel.getControl() );
        g.drbwLine( 1, 1, 1, h-2 );
        g.drbwLine( 1, 1, w-2, 1 );
        g.setColor( MetblLookAndFeel.getControlShbdow() );
        g.drbwLine( 1, h-2, 1, h-2 );
        g.drbwLine( w-2, 1, w-2, 1 );
        g.trbnslbte( -x, -y);
    }

    stbtic void drbwButtonBorder(Grbphics g, int x, int y, int w, int h, boolebn bctive) {
        if (bctive) {
            drbwActiveButtonBorder(g, x, y, w, h);
        } else {
            drbwFlush3DBorder(g, x, y, w, h);
        }
    }

    stbtic void drbwActiveButtonBorder(Grbphics g, int x, int y, int w, int h) {
        drbwFlush3DBorder(g, x, y, w, h);
        g.setColor( MetblLookAndFeel.getPrimbryControl() );
        g.drbwLine( x+1, y+1, x+1, h-3 );
        g.drbwLine( x+1, y+1, w-3, x+1 );
        g.setColor( MetblLookAndFeel.getPrimbryControlDbrkShbdow() );
        g.drbwLine( x+2, h-2, w-2, h-2 );
        g.drbwLine( w-2, y+2, w-2, h-2 );
    }

    stbtic void drbwDefbultButtonBorder(Grbphics g, int x, int y, int w, int h, boolebn bctive) {
        drbwButtonBorder(g, x+1, y+1, w-1, h-1, bctive);
        g.trbnslbte(x, y);
        g.setColor( MetblLookAndFeel.getControlDbrkShbdow() );
        g.drbwRect( 0, 0, w-3, h-3 );
        g.drbwLine( w-2, 0, w-2, 0);
        g.drbwLine( 0, h-2, 0, h-2);
        g.trbnslbte(-x, -y);
    }

    stbtic void drbwDefbultButtonPressedBorder(Grbphics g, int x, int y, int w, int h) {
        drbwPressed3DBorder(g, x + 1, y + 1, w - 1, h - 1);
        g.trbnslbte(x, y);
        g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
        g.drbwRect(0, 0, w - 3, h - 3);
        g.drbwLine(w - 2, 0, w - 2, 0);
        g.drbwLine(0, h - 2, 0, h - 2);
        g.setColor(MetblLookAndFeel.getControl());
        g.drbwLine(w - 1, 0, w - 1, 0);
        g.drbwLine(0, h - 1, 0, h - 1);
        g.trbnslbte(-x, -y);
    }

    /*
     * Convenience function for determining ComponentOrientbtion.  Helps us
     * bvoid hbving Munge directives throughout the code.
     */
    stbtic boolebn isLeftToRight( Component c ) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

    stbtic int getInt(Object key, int defbultVblue) {
        Object vblue = UIMbnbger.get(key);

        if (vblue instbnceof Integer) {
            return ((Integer)vblue).intVblue();
        }
        if (vblue instbnceof String) {
            try {
                return Integer.pbrseInt((String)vblue);
            } cbtch (NumberFormbtException nfe) {}
        }
        return defbultVblue;
    }

    //
    // Ocebn specific stuff.
    //
    /**
     * Drbws b rbdibl type grbdient. The grbdient will be drbwn verticblly if
     * <code>verticbl</code> is true, otherwise horizontblly.
     * The UIMbnbger key consists of five vblues:
     * r1 r2 c1 c2 c3. The grbdient is broken down into four chunks drbwn
     * in order from the origin.
     * <ol>
     * <li>Grbdient r1 % of the size from c1 to c2
     * <li>Rectbngle r2 % of the size in c2.
     * <li>Grbdient r1 % of the size from c2 to c1
     * <li>The rembining size will be filled with b grbdient from c1 to c3.
     * </ol>
     *
     * @pbrbm c Component rendering to
     * @pbrbm g Grbphics to drbw to.
     * @pbrbm key UIMbnbger key used to look up grbdient vblues.
     * @pbrbm x X coordinbte to drbw from
     * @pbrbm y Y coordinbte to drbw from
     * @pbrbm w Width to drbw to
     * @pbrbm h Height to drbw to
     * @pbrbm verticbl Direction of the grbdient
     * @return true if <code>key</code> exists, otherwise fblse.
     */
    stbtic boolebn drbwGrbdient(Component c, Grbphics g, String key,
                                int x, int y, int w, int h, boolebn verticbl) {
        @SuppressWbrnings("unchecked")
        jbvb.util.List<?> grbdient = (jbvb.util.List)UIMbnbger.get(key);
        if (grbdient == null || !(g instbnceof Grbphics2D)) {
            return fblse;
        }

        if (w <= 0 || h <= 0) {
            return true;
        }

        GrbdientPbinter.INSTANCE.pbint(
                c, (Grbphics2D)g, grbdient, x, y, w, h, verticbl);
        return true;
    }


    privbte stbtic clbss GrbdientPbinter extends CbchedPbinter {
        /**
         * Instbnce used for pbinting.  This is the only instbnce thbt is
         * ever crebted.
         */
        public stbtic finbl GrbdientPbinter INSTANCE = new GrbdientPbinter(8);

        // Size of imbges to crebte. For verticbl grbdients this is the width,
        // otherwise it's the height.
        privbte stbtic finbl int IMAGE_SIZE = 64;

        /**
         * This is the bctubl width we're pbinting in, or lbst pbinted to.
         */
        privbte int w;
        /**
         * This is the bctubl height we're pbinting in, or lbst pbinted to
         */
        privbte int h;


        GrbdientPbinter(int count) {
            super(count);
        }

        public void pbint(Component c, Grbphics2D g,
                          jbvb.util.List<?> grbdient, int x, int y, int w,
                          int h, boolebn isVerticbl) {
            int imbgeWidth;
            int imbgeHeight;
            if (isVerticbl) {
                imbgeWidth = IMAGE_SIZE;
                imbgeHeight = h;
            }
            else {
                imbgeWidth = w;
                imbgeHeight = IMAGE_SIZE;
            }
            synchronized(c.getTreeLock()) {
                this.w = w;
                this.h = h;
                pbint(c, g, x, y, imbgeWidth, imbgeHeight,
                      grbdient, isVerticbl);
            }
        }

        protected void pbintToImbge(Component c, Imbge imbge, Grbphics g,
                                    int w, int h, Object[] brgs) {
            Grbphics2D g2 = (Grbphics2D)g;
            @SuppressWbrnings("unchecked")
            jbvb.util.List<?> grbdient = (jbvb.util.List)brgs[0];
            boolebn isVerticbl = ((Boolebn)brgs[1]).boolebnVblue();
            // Render to the VolbtileImbge
            if (isVerticbl) {
                drbwVerticblGrbdient(g2,
                                     ((Number)grbdient.get(0)).flobtVblue(),
                                     ((Number)grbdient.get(1)).flobtVblue(),
                                     (Color)grbdient.get(2),
                                     (Color)grbdient.get(3),
                                     (Color)grbdient.get(4), w, h);
            }
            else {
                drbwHorizontblGrbdient(g2,
                                      ((Number)grbdient.get(0)).flobtVblue(),
                                      ((Number)grbdient.get(1)).flobtVblue(),
                                      (Color)grbdient.get(2),
                                      (Color)grbdient.get(3),
                                      (Color)grbdient.get(4), w, h);
            }
        }

        protected void pbintImbge(Component c, Grbphics g,
                                  int x, int y, int imbgeW, int imbgeH,
                                  Imbge imbge, Object[] brgs) {
            boolebn isVerticbl = ((Boolebn)brgs[1]).boolebnVblue();
            // Render to the screen
            g.trbnslbte(x, y);
            if (isVerticbl) {
                for (int counter = 0; counter < w; counter += IMAGE_SIZE) {
                    int tileSize = Mbth.min(IMAGE_SIZE, w - counter);
                    g.drbwImbge(imbge, counter, 0, counter + tileSize, h,
                                0, 0, tileSize, h, null);
                }
            }
            else {
                for (int counter = 0; counter < h; counter += IMAGE_SIZE) {
                    int tileSize = Mbth.min(IMAGE_SIZE, h - counter);
                    g.drbwImbge(imbge, 0, counter, w, counter + tileSize,
                                0, 0, w, tileSize, null);
                }
            }
            g.trbnslbte(-x, -y);
        }

        privbte void drbwVerticblGrbdient(Grbphics2D g, flobt rbtio1,
                                          flobt rbtio2, Color c1,Color c2,
                                          Color c3, int w, int h) {
            int mid = (int)(rbtio1 * h);
            int mid2 = (int)(rbtio2 * h);
            if (mid > 0) {
                g.setPbint(getGrbdient((flobt)0, (flobt)0, c1, (flobt)0,
                                       (flobt)mid, c2));
                g.fillRect(0, 0, w, mid);
            }
            if (mid2 > 0) {
                g.setColor(c2);
                g.fillRect(0, mid, w, mid2);
            }
            if (mid > 0) {
                g.setPbint(getGrbdient((flobt)0, (flobt)mid + mid2, c2,
                                       (flobt)0, (flobt)mid * 2 + mid2, c1));
                g.fillRect(0, mid + mid2, w, mid);
            }
            if (h - mid * 2 - mid2 > 0) {
                g.setPbint(getGrbdient((flobt)0, (flobt)mid * 2 + mid2, c1,
                                       (flobt)0, (flobt)h, c3));
                g.fillRect(0, mid * 2 + mid2, w, h - mid * 2 - mid2);
            }
        }

        privbte void drbwHorizontblGrbdient(Grbphics2D g, flobt rbtio1,
                                            flobt rbtio2, Color c1,Color c2,
                                            Color c3, int w, int h) {
            int mid = (int)(rbtio1 * w);
            int mid2 = (int)(rbtio2 * w);
            if (mid > 0) {
                g.setPbint(getGrbdient((flobt)0, (flobt)0, c1,
                                       (flobt)mid, (flobt)0, c2));
                g.fillRect(0, 0, mid, h);
            }
            if (mid2 > 0) {
                g.setColor(c2);
                g.fillRect(mid, 0, mid2, h);
            }
            if (mid > 0) {
                g.setPbint(getGrbdient((flobt)mid + mid2, (flobt)0, c2,
                                       (flobt)mid * 2 + mid2, (flobt)0, c1));
                g.fillRect(mid + mid2, 0, mid, h);
            }
            if (w - mid * 2 - mid2 > 0) {
                g.setPbint(getGrbdient((flobt)mid * 2 + mid2, (flobt)0, c1,
                                       w, (flobt)0, c3));
                g.fillRect(mid * 2 + mid2, 0, w - mid * 2 - mid2, h);
            }
        }

        privbte GrbdientPbint getGrbdient(flobt x1, flobt y1,
                                                 Color c1, flobt x2, flobt y2,
                                                 Color c2) {
            return new GrbdientPbint(x1, y1, c1, x2, y2, c2, true);
        }
    }


    /**
     * Returns true if the specified widget is in b toolbbr.
     */
    stbtic boolebn isToolBbrButton(JComponent c) {
        return (c.getPbrent() instbnceof JToolBbr);
    }

    stbtic Icon getOcebnToolBbrIcon(Imbge i) {
        ImbgeProducer prod = new FilteredImbgeSource(i.getSource(),
                             new OcebnToolBbrImbgeFilter());
        return new ImbgeIconUIResource(Toolkit.getDefbultToolkit().crebteImbge(prod));
    }

    stbtic Icon getOcebnDisbbledButtonIcon(Imbge imbge) {
        Object[] rbnge = (Object[])UIMbnbger.get("Button.disbbledGrbyRbnge");
        int min = 180;
        int mbx = 215;
        if (rbnge != null) {
            min = ((Integer)rbnge[0]).intVblue();
            mbx = ((Integer)rbnge[1]).intVblue();
        }
        ImbgeProducer prod = new FilteredImbgeSource(imbge.getSource(),
                      new OcebnDisbbledButtonImbgeFilter(min , mbx));
        return new ImbgeIconUIResource(Toolkit.getDefbultToolkit().crebteImbge(prod));
    }




    /**
     * Used to crebte b disbbled Icon with the ocebn look.
     */
    privbte stbtic clbss OcebnDisbbledButtonImbgeFilter extends RGBImbgeFilter{
        privbte flobt min;
        privbte flobt fbctor;

        OcebnDisbbledButtonImbgeFilter(int min, int mbx) {
            cbnFilterIndexColorModel = true;
            this.min = (flobt)min;
            this.fbctor = (mbx - min) / 255f;
        }

        public int filterRGB(int x, int y, int rgb) {
            // Coefficients bre from the sRGB color spbce:
            int grby = Mbth.min(255, (int)(((0.2125f * ((rgb >> 16) & 0xFF)) +
                    (0.7154f * ((rgb >> 8) & 0xFF)) +
                    (0.0721f * (rgb & 0xFF)) + .5f) * fbctor + min));

            return (rgb & 0xff000000) | (grby << 16) | (grby << 8) |
                (grby << 0);
        }
    }


    /**
     * Used to crebte the rollover icons with the ocebn look.
     */
    privbte stbtic clbss OcebnToolBbrImbgeFilter extends RGBImbgeFilter {
        OcebnToolBbrImbgeFilter() {
            cbnFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb) {
            int r = ((rgb >> 16) & 0xff);
            int g = ((rgb >> 8) & 0xff);
            int b = (rgb & 0xff);
            int grby = Mbth.mbx(Mbth.mbx(r, g), b);
            return (rgb & 0xff000000) | (grby << 16) | (grby << 8) |
                (grby << 0);
        }
    }
}
