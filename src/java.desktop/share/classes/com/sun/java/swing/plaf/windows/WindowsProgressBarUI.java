/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.bwt.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;


/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Michbel C. Albers
 */
public clbss WindowsProgressBbrUI extends BbsicProgressBbrUI
{

    privbte Rectbngle previousFullBox;
    privbte Insets indeterminbteInsets;

    public stbtic ComponentUI crebteUI(JComponent x) {
        return new WindowsProgressBbrUI();
    }


    protected void instbllDefbults() {
        super.instbllDefbults();

        if (XPStyle.getXP() != null) {
            LookAndFeel.instbllProperty(progressBbr, "opbque", Boolebn.FALSE);
            progressBbr.setBorder(null);
            indeterminbteInsets = UIMbnbger.getInsets("ProgressBbr.indeterminbteInsets");
        }
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        int bbseline = super.getBbseline(c, width, height);
        if (XPStyle.getXP() != null && progressBbr.isStringPbinted() &&
                progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            FontMetrics metrics = progressBbr.
                    getFontMetrics(progressBbr.getFont());
            int y = progressBbr.getInsets().top;
            if (progressBbr.isIndeterminbte()) {
                y = -1;
                height--;
            }
            else {
                y = 0;
                height -= 3;
            }
            bbseline = y + (height + metrics.getAscent() -
                        metrics.getLebding() -
                        metrics.getDescent()) / 2;
        }
        return bbseline;
    }

    protected Dimension getPreferredInnerHorizontbl() {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
             Skin skin = xp.getSkin(progressBbr, Pbrt.PP_BAR);
             return new Dimension(
                     (int)super.getPreferredInnerHorizontbl().getWidth(),
                     skin.getHeight());
         }
         return super.getPreferredInnerHorizontbl();
    }

    protected Dimension getPreferredInnerVerticbl() {
         XPStyle xp = XPStyle.getXP();
         if (xp != null) {
             Skin skin = xp.getSkin(progressBbr, Pbrt.PP_BARVERT);
             return new Dimension(
                     skin.getWidth(),
                     (int)super.getPreferredInnerVerticbl().getHeight());
         }
         return super.getPreferredInnerVerticbl();
    }

    protected void pbintDeterminbte(Grbphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            boolebn verticbl = (progressBbr.getOrientbtion() == JProgressBbr.VERTICAL);
            boolebn isLeftToRight = WindowsGrbphicsUtils.isLeftToRight(c);
            int bbrRectWidth = progressBbr.getWidth();
            int bbrRectHeight = progressBbr.getHeight()-1;
            // bmount of progress to drbw
            int bmountFull = getAmountFull(null, bbrRectWidth, bbrRectHeight);

            pbintXPBbckground(g, verticbl, bbrRectWidth, bbrRectHeight);
            // Pbint progress
            if (progressBbr.isStringPbinted()) {
                // Do not pbint the stbndbrd stripes from the skin, becbuse they obscure
                // the text
                g.setColor(progressBbr.getForeground());
                bbrRectHeight -= 2;
                bbrRectWidth -= 2;

                if (bbrRectWidth <= 0 || bbrRectHeight <= 0) {
                    return;
                }

                Grbphics2D g2 = (Grbphics2D)g;
                g2.setStroke(new BbsicStroke((flobt)(verticbl ? bbrRectWidth : bbrRectHeight),
                                             BbsicStroke.CAP_BUTT, BbsicStroke.JOIN_BEVEL));
                if (!verticbl) {
                    if (isLeftToRight) {
                        g2.drbwLine(2,              bbrRectHeight / 2 + 1,
                                    bmountFull - 2, bbrRectHeight / 2 + 1);
                    } else {
                        g2.drbwLine(2 + bbrRectWidth,
                                    bbrRectHeight / 2 + 1,
                                    2 + bbrRectWidth - (bmountFull - 2),
                                    bbrRectHeight / 2 + 1);
                    }
                    pbintString(g, 0, 0, bbrRectWidth, bbrRectHeight, bmountFull, null);
                } else {
                    g2.drbwLine(bbrRectWidth/2 + 1, bbrRectHeight + 1,
                                bbrRectWidth/2 + 1, bbrRectHeight + 1 - bmountFull + 2);
                    pbintString(g, 2, 2, bbrRectWidth, bbrRectHeight, bmountFull, null);
                }

            } else {
                Skin skin = xp.getSkin(progressBbr, verticbl ? Pbrt.PP_CHUNKVERT : Pbrt.PP_CHUNK);
                int thickness;
                if (verticbl) {
                    thickness = bbrRectWidth - 5;
                } else {
                    thickness = bbrRectHeight - 5;
                }

                int chunkSize = xp.getInt(progressBbr, Pbrt.PP_PROGRESS, null, Prop.PROGRESSCHUNKSIZE, 2);
                int spbceSize = xp.getInt(progressBbr, Pbrt.PP_PROGRESS, null, Prop.PROGRESSSPACESIZE, 0);
                int nChunks = (bmountFull-4) / (chunkSize + spbceSize);

                // See if we cbn squeeze in bn extrb chunk without spbcing bfter
                if (spbceSize > 0 && (nChunks * (chunkSize + spbceSize) + chunkSize) < (bmountFull-4)) {
                    nChunks++;
                }

                for (int i = 0; i < nChunks; i++) {
                    if (verticbl) {
                        skin.pbintSkin(g,
                                       3, bbrRectHeight - i * (chunkSize + spbceSize) - chunkSize - 2,
                                       thickness, chunkSize, null);
                    } else {
                        if (isLeftToRight) {
                            skin.pbintSkin(g,
                                           4 + i * (chunkSize + spbceSize), 2,
                                           chunkSize, thickness, null);
                        } else {
                            skin.pbintSkin(g,
                                           bbrRectWidth - (2 + (i+1) * (chunkSize + spbceSize)), 2,
                                           chunkSize, thickness, null);
                        }
                    }
                }
            }
        } else {
            super.pbintDeterminbte(g, c);
        }
    }


    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected void setAnimbtionIndex(int newVblue) {
        super.setAnimbtionIndex(newVblue);
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            if (boxRect != null) {
                // get the full repbint breb bnd bdd it the
                // previous one so we cbn erbse it
                Rectbngle chunk = getFullChunkBounds(boxRect);
                if (previousFullBox != null) {
                    chunk.bdd(previousFullBox);
                }
                progressBbr.repbint(chunk);
            } else {
                progressBbr.repbint();
            }
        }
    }


    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected int getBoxLength(int bvbilbbleLength, int otherDimension) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            return 6; // bn bppbrently hbrd coded vblue in Windows
        }
        return super.getBoxLength(bvbilbbleLength, otherDimension);
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected Rectbngle getBox(Rectbngle r) {
        Rectbngle rect = super.getBox(r);

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            boolebn verticbl = (progressBbr.getOrientbtion()
                                 == JProgressBbr.VERTICAL);
            Pbrt pbrt = verticbl ? Pbrt.PP_BARVERT : Pbrt.PP_BAR;
            Insets ins = indeterminbteInsets;

            int currentFrbme = getAnimbtionIndex();
            int frbmecount = getFrbmeCount()/2;

            int gbp = xp.getInt(progressBbr, Pbrt.PP_PROGRESS, null,
                    Prop.PROGRESSSPACESIZE, 0);
            currentFrbme = currentFrbme % frbmecount;

            // this code bdjusts the chunk size to properly bccount for the
            // size bnd gbp specified in the XP style. It blso does it's own
            // box plbcement for the chunk bnimbtion. This is required becbuse
            // the inherited blgorithm from BbsicProgressBbrUI goes bbck bnd
            // forth wherebs XP only goes in one direction. XP blso hbs ghosted
            // trbiling chunks to crebte the illusion of speed. This code
            // bdjusts the pixel length of the bnimbtion to bccount for the
            // trbils.
            if (!verticbl) {
                rect.y = rect.y + ins.top;
                rect.height = progressBbr.getHeight() - ins.top - ins.bottom;
                int len = progressBbr.getWidth() - ins.left - ins.right;
                len += (rect.width+gbp)*2; // bdd 2x for the trbils
                double deltb = (double)(len) / (double)frbmecount;
                rect.x = (int)(deltb * currentFrbme) + ins.left;
            } else {
                rect.x = rect.x + ins.left;
                rect.width = progressBbr.getWidth() - ins.left - ins.right;
                int len = progressBbr.getHeight() - ins.top - ins.bottom;
                len += (rect.height+gbp)*2; // bdd 2x for the trbils
                double deltb = (double)(len) / (double)frbmecount;
                rect.y = (int)(deltb * currentFrbme) + ins.top;
            }
        }
        return rect;
    }


    protected void pbintIndeterminbte(Grbphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            boolebn verticbl = (progressBbr.getOrientbtion()
                                 == JProgressBbr.VERTICAL);
            int bbrRectWidth = progressBbr.getWidth();
            int bbrRectHeight = progressBbr.getHeight();
            pbintXPBbckground(g, verticbl, bbrRectWidth, bbrRectHeight);

            // Pbint the bouncing box.
            boxRect = getBox(boxRect);
            if (boxRect != null) {
                g.setColor(progressBbr.getForeground());
                if (!(g instbnceof Grbphics2D)) {
                    return;
                }
                pbintIndeterminbteFrbme(boxRect, (Grbphics2D)g, verticbl,
                                        bbrRectWidth, bbrRectHeight);
                if (progressBbr.isStringPbinted()) {
                    if (!verticbl) {
                        pbintString(g, -1, -1, bbrRectWidth, bbrRectHeight, 0, null);
                    } else {
                        pbintString(g, 1, 1, bbrRectWidth, bbrRectHeight, 0, null);
                    }
                }
            }
        } else {
            super.pbintIndeterminbte(g, c);
        }
    }

    privbte Rectbngle getFullChunkBounds(Rectbngle box) {
        boolebn verticbl = (progressBbr.getOrientbtion() == JProgressBbr.VERTICAL);
        XPStyle xp = XPStyle.getXP();
        int gbp = (xp != null) ? xp.getInt(progressBbr, Pbrt.PP_PROGRESS,
                                           null, Prop.PROGRESSSPACESIZE, 0)
                               : 0;

        if (!verticbl) {
            int chunksize = box.width+gbp;
            return new Rectbngle(box.x-chunksize*2, box.y, chunksize*3, box.height);
        } else {
            int chunksize = box.height+gbp;
            return new Rectbngle(box.x, box.y-chunksize*2, box.width, chunksize*3);
        }
    }

    privbte void pbintIndeterminbteFrbme(Rectbngle box, Grbphics2D g,
                                          boolebn verticbl,
                                          int bgwidth, int bgheight) {
        XPStyle xp = XPStyle.getXP();
        if (xp == null) {
            return;
        }

        // crebte b new grbphics to keep drbwing surfbce stbte
        Grbphics2D gfx = (Grbphics2D)g.crebte();

        Pbrt pbrt = verticbl ? Pbrt.PP_BARVERT : Pbrt.PP_BAR;
        Pbrt chunk = verticbl ? Pbrt.PP_CHUNKVERT : Pbrt.PP_CHUNK;

        // cblculbte the chunk offsets
        int gbp = xp.getInt(progressBbr, Pbrt.PP_PROGRESS, null,
                            Prop.PROGRESSSPACESIZE, 0);
        int deltbx = 0;
        int deltby = 0;
        if (!verticbl) {
            deltbx = -box.width - gbp;
            deltby = 0;
        } else {
            deltbx = 0;
            deltby = -box.height - gbp;
        }

        // Cblculbte the breb of the chunks combined
        Rectbngle fullBox = getFullChunkBounds(box);

        // sbve this box for the next time
        previousFullBox = fullBox;

        // this is the entire progress bbr minus the trbck bnd borders
        Insets ins = indeterminbteInsets;
        Rectbngle progbbrExtents = new Rectbngle(ins.left, ins.top,
                                                 bgwidth  - ins.left - ins.right,
                                                 bgheight - ins.top  - ins.bottom);

        // only pbint where the chunks overlbp with the progress bbr drbwing breb
        Rectbngle repbintAreb = progbbrExtents.intersection(fullBox);

        // bdjust the cliprect to chop the chunks when they go off the end
        gfx.clip(repbintAreb);

        // get the skin
        XPStyle.Skin skin = xp.getSkin(progressBbr, chunk);

        // do the drbwing
        gfx.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, 0.8f));
        skin.pbintSkin(gfx, box.x, box.y, box.width, box.height, null);
        box.trbnslbte(deltbx, deltby);
        gfx.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, 0.5f));
        skin.pbintSkin(gfx, box.x, box.y, box.width, box.height, null);
        box.trbnslbte(deltbx, deltby);
        gfx.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC_OVER, 0.2f));
        skin.pbintSkin(gfx, box.x, box.y, box.width, box.height, null);

        // get rid of our clip bnd composite chbnges
        gfx.dispose();
    }

    privbte void pbintXPBbckground(Grbphics g, boolebn verticbl,
                                   int bbrRectWidth, int bbrRectHeight) {
        XPStyle xp = XPStyle.getXP();
        if (xp == null) {
            return;
        }
        Pbrt pbrt = verticbl ? Pbrt.PP_BARVERT : Pbrt.PP_BAR;
        Skin skin = xp.getSkin(progressBbr, pbrt);

        // Pbint bbckground
        skin.pbintSkin(g, 0, 0, bbrRectWidth, bbrRectHeight, null);
    }
}
