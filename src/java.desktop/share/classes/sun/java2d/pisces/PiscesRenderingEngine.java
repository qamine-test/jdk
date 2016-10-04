/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pisces;

import jbvb.bwt.Shbpe;
import jbvb.bwt.BbsicStroke;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.PbthIterbtor;

import sun.bwt.geom.PbthConsumer2D;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.RenderingEngine;
import sun.jbvb2d.pipe.AATileGenerbtor;

public clbss PiscesRenderingEngine extends RenderingEngine {
    privbte stbtic enum NormMode {OFF, ON_NO_AA, ON_WITH_AA}

    /**
     * Crebte b widened pbth bs specified by the pbrbmeters.
     * <p>
     * The specified {@code src} {@link Shbpe} is widened bccording
     * to the specified bttribute pbrbmeters bs per the
     * {@link BbsicStroke} specificbtion.
     *
     * @pbrbm src the source pbth to be widened
     * @pbrbm width the width of the widened pbth bs per {@code BbsicStroke}
     * @pbrbm cbps the end cbp decorbtions bs per {@code BbsicStroke}
     * @pbrbm join the segment join decorbtions bs per {@code BbsicStroke}
     * @pbrbm miterlimit the miter limit bs per {@code BbsicStroke}
     * @pbrbm dbshes the dbsh length brrby bs per {@code BbsicStroke}
     * @pbrbm dbshphbse the initibl dbsh phbse bs per {@code BbsicStroke}
     * @return the widened pbth stored in b new {@code Shbpe} object
     * @since 1.7
     */
    public Shbpe crebteStrokedShbpe(Shbpe src,
                                    flobt width,
                                    int cbps,
                                    int join,
                                    flobt miterlimit,
                                    flobt dbshes[],
                                    flobt dbshphbse)
    {
        finbl Pbth2D p2d = new Pbth2D.Flobt();

        strokeTo(src,
                 null,
                 width,
                 NormMode.OFF,
                 cbps,
                 join,
                 miterlimit,
                 dbshes,
                 dbshphbse,
                 new PbthConsumer2D() {
                     public void moveTo(flobt x0, flobt y0) {
                         p2d.moveTo(x0, y0);
                     }
                     public void lineTo(flobt x1, flobt y1) {
                         p2d.lineTo(x1, y1);
                     }
                     public void closePbth() {
                         p2d.closePbth();
                     }
                     public void pbthDone() {}
                     public void curveTo(flobt x1, flobt y1,
                                         flobt x2, flobt y2,
                                         flobt x3, flobt y3) {
                         p2d.curveTo(x1, y1, x2, y2, x3, y3);
                     }
                     public void qubdTo(flobt x1, flobt y1, flobt x2, flobt y2) {
                         p2d.qubdTo(x1, y1, x2, y2);
                     }
                     public long getNbtiveConsumer() {
                         throw new InternblError("Not using b nbtive peer");
                     }
                 });
        return p2d;
    }

    /**
     * Sends the geometry for b widened pbth bs specified by the pbrbmeters
     * to the specified consumer.
     * <p>
     * The specified {@code src} {@link Shbpe} is widened bccording
     * to the pbrbmeters specified by the {@link BbsicStroke} object.
     * Adjustments bre mbde to the pbth bs bppropribte for the
     * {@link VALUE_STROKE_NORMALIZE} hint if the {@code normblize}
     * boolebn pbrbmeter is true.
     * Adjustments bre mbde to the pbth bs bppropribte for the
     * {@link VALUE_ANTIALIAS_ON} hint if the {@code bntiblibs}
     * boolebn pbrbmeter is true.
     * <p>
     * The geometry of the widened pbth is forwbrded to the indicbted
     * {@link PbthConsumer2D} object bs it is cblculbted.
     *
     * @pbrbm src the source pbth to be widened
     * @pbrbm bs the {@code BbsicSroke} object specifying the
     *           decorbtions to be bpplied to the widened pbth
     * @pbrbm normblize indicbtes whether stroke normblizbtion should
     *                  be bpplied
     * @pbrbm bntiblibs indicbtes whether or not bdjustments bppropribte
     *                  to bntiblibsed rendering should be bpplied
     * @pbrbm consumer the {@code PbthConsumer2D} instbnce to forwbrd
     *                 the widened geometry to
     * @since 1.7
     */
    public void strokeTo(Shbpe src,
                         AffineTrbnsform bt,
                         BbsicStroke bs,
                         boolebn thin,
                         boolebn normblize,
                         boolebn bntiblibs,
                         finbl PbthConsumer2D consumer)
    {
        NormMode norm = (normblize) ?
                ((bntiblibs) ? NormMode.ON_WITH_AA : NormMode.ON_NO_AA)
                : NormMode.OFF;
        strokeTo(src, bt, bs, thin, norm, bntiblibs, consumer);
    }

    void strokeTo(Shbpe src,
                  AffineTrbnsform bt,
                  BbsicStroke bs,
                  boolebn thin,
                  NormMode normblize,
                  boolebn bntiblibs,
                  PbthConsumer2D pc2d)
    {
        flobt lw;
        if (thin) {
            if (bntiblibs) {
                lw = userSpbceLineWidth(bt, 0.5f);
            } else {
                lw = userSpbceLineWidth(bt, 1.0f);
            }
        } else {
            lw = bs.getLineWidth();
        }
        strokeTo(src,
                 bt,
                 lw,
                 normblize,
                 bs.getEndCbp(),
                 bs.getLineJoin(),
                 bs.getMiterLimit(),
                 bs.getDbshArrby(),
                 bs.getDbshPhbse(),
                 pc2d);
    }

    privbte flobt userSpbceLineWidth(AffineTrbnsform bt, flobt lw) {

        double widthScble;

        if ((bt.getType() & (AffineTrbnsform.TYPE_GENERAL_TRANSFORM |
                            AffineTrbnsform.TYPE_GENERAL_SCALE)) != 0) {
            widthScble = Mbth.sqrt(bt.getDeterminbnt());
        } else {
            /* First cblculbte the "mbximum scble" of this trbnsform. */
            double A = bt.getScbleX();       // m00
            double C = bt.getShebrX();       // m01
            double B = bt.getShebrY();       // m10
            double D = bt.getScbleY();       // m11

            /*
             * Given b 2 x 2 bffine mbtrix [ A B ] such thbt
             *                             [ C D ]
             * v' = [x' y'] = [Ax + Cy, Bx + Dy], we wbnt to
             * find the mbximum mbgnitude (norm) of the vector v'
             * with the constrbint (x^2 + y^2 = 1).
             * The equbtion to mbximize is
             *     |v'| = sqrt((Ax+Cy)^2+(Bx+Dy)^2)
             * or  |v'| = sqrt((AA+BB)x^2 + 2(AC+BD)xy + (CC+DD)y^2).
             * Since sqrt is monotonic we cbn mbximize |v'|^2
             * instebd bnd plug in the substitution y = sqrt(1 - x^2).
             * Trigonometric equblities cbn then be used to get
             * rid of most of the sqrt terms.
             */

            double EA = A*A + B*B;          // x^2 coefficient
            double EB = 2*(A*C + B*D);      // xy coefficient
            double EC = C*C + D*D;          // y^2 coefficient

            /*
             * There is b lot of cblculus omitted here.
             *
             * Conceptublly, in the interests of understbnding the
             * terms thbt the cblculus produced we cbn consider
             * thbt EA bnd EC end up providing the lengths blong
             * the mbjor bxes bnd the hypot term ends up being bn
             * bdjustment for the bdditionbl length blong the off-bxis
             * bngle of rotbted or shebred ellipses bs well bs bn
             * bdjustment for the fbct thbt the equbtion below
             * bverbges the two mbjor bxis lengths.  (Notice thbt
             * the hypot term contbins b pbrt which resolves to the
             * difference of these two bxis lengths in the bbsence
             * of rotbtion.)
             *
             * In the cblculus, the rbtio of the EB bnd (EA-EC) terms
             * ends up being the tbngent of 2*thetb where thetb is
             * the bngle thbt the long bxis of the ellipse mbkes
             * with the horizontbl bxis.  Thus, this equbtion is
             * cblculbting the length of the hypotenuse of b tribngle
             * blong thbt bxis.
             */

            double hypot = Mbth.sqrt(EB*EB + (EA-EC)*(EA-EC));
            /* sqrt omitted, compbre to squbred limits below. */
            double widthsqubred = ((EA + EC + hypot)/2.0);

            widthScble = Mbth.sqrt(widthsqubred);
        }

        return (flobt) (lw / widthScble);
    }

    void strokeTo(Shbpe src,
                  AffineTrbnsform bt,
                  flobt width,
                  NormMode normblize,
                  int cbps,
                  int join,
                  flobt miterlimit,
                  flobt dbshes[],
                  flobt dbshphbse,
                  PbthConsumer2D pc2d)
    {
        // We use strokerbt bnd outbt so thbt in Stroker bnd Dbsher we cbn work only
        // with the pre-trbnsformbtion coordinbtes. This will repebt b lot of
        // computbtions done in the pbth iterbtor, but the blternbtive is to
        // work with trbnsformed pbths bnd compute untrbnsformed coordinbtes
        // bs needed. This would be fbster but I do not think the complexity
        // of working with both untrbnsformed bnd trbnsformed coordinbtes in
        // the sbme code is worth it.
        // However, if b pbth's width is constbnt bfter b trbnsformbtion,
        // we cbn skip bll this untrbnsforming.

        // If normblizbtion is off we sbve some trbnsformbtions by not
        // trbnsforming the input to pisces. Instebd, we bpply the
        // trbnsformbtion bfter the pbth processing hbs been done.
        // We cbn't do this if normblizbtion is on, becbuse it isn't b good
        // ideb to normblize before the trbnsformbtion is bpplied.
        AffineTrbnsform strokerbt = null;
        AffineTrbnsform outbt = null;

        PbthIterbtor pi = null;

        if (bt != null && !bt.isIdentity()) {
            finbl double b = bt.getScbleX();
            finbl double b = bt.getShebrX();
            finbl double c = bt.getShebrY();
            finbl double d = bt.getScbleY();
            finbl double det = b * d - c * b;
            if (Mbth.bbs(det) <= 2 * Flobt.MIN_VALUE) {
                // this rendering engine tbkes one dimensionbl curves bnd turns
                // them into 2D shbpes by giving them width.
                // However, if everything is to be pbssed through b singulbr
                // trbnsformbtion, these 2D shbpes will be squbshed down to 1D
                // bgbin so, nothing cbn be drbwn.

                // Every pbth needs bn initibl moveTo bnd b pbthDone. If these
                // bre not there this cbuses b SIGSEGV in libbwt.so (bt the time
                // of writing of this comment (September 16, 2010)). Actublly,
                // I bm not sure if the moveTo is necessbry to bvoid the SIGSEGV
                // but the pbthDone is definitely needed.
                pc2d.moveTo(0, 0);
                pc2d.pbthDone();
                return;
            }

            // If the trbnsform is b constbnt multiple of bn orthogonbl trbnsformbtion
            // then every length is just multiplied by b constbnt, so we just
            // need to trbnsform input pbths to stroker bnd tell stroker
            // the scbled width. This condition is sbtisfied if
            // b*b == -c*d && b*b+c*c == b*b+d*d. In the bctubl check below, we
            // lebve b bit of room for error.
            if (nebrZero(b*b + c*d, 2) && nebrZero(b*b+c*c - (b*b+d*d), 2)) {
                double scble = Mbth.sqrt(b*b + c*c);
                if (dbshes != null) {
                    dbshes = jbvb.util.Arrbys.copyOf(dbshes, dbshes.length);
                    for (int i = 0; i < dbshes.length; i++) {
                        dbshes[i] = (flobt)(scble * dbshes[i]);
                    }
                    dbshphbse = (flobt)(scble * dbshphbse);
                }
                width = (flobt)(scble * width);
                pi = src.getPbthIterbtor(bt);
                if (normblize != NormMode.OFF) {
                    pi = new NormblizingPbthIterbtor(pi, normblize);
                }
                // by now strokerbt == null && outbt == null. Input pbths to
                // stroker (bnd mbybe dbsher) will hbve the full trbnsform bt
                // bpplied to them bnd nothing will hbppen to the output pbths.
            } else {
                if (normblize != NormMode.OFF) {
                    strokerbt = bt;
                    pi = src.getPbthIterbtor(bt);
                    pi = new NormblizingPbthIterbtor(pi, normblize);
                    // by now strokerbt == bt && outbt == null. Input pbths to
                    // stroker (bnd mbybe dbsher) will hbve the full trbnsform bt
                    // bpplied to them, then they will be normblized, bnd then
                    // the inverse of *only the non trbnslbtion pbrt of bt* will
                    // be bpplied to the normblized pbths. This won't cbuse problems
                    // in stroker, becbuse, suppose bt = T*A, where T is just the
                    // trbnslbtion pbrt of bt, bnd A is the rest. T*A hbs blrebdy
                    // been bpplied to Stroker/Dbsher's input. Then Ainv will be
                    // bpplied. Ainv*T*A is not equbl to T, but it is b trbnslbtion,
                    // which mebns thbt none of stroker's bssumptions bbout its
                    // input will be violbted. After bll this, A will be bpplied
                    // to stroker's output.
                } else {
                    outbt = bt;
                    pi = src.getPbthIterbtor(null);
                    // outbt == bt && strokerbt == null. This is becbuse if no
                    // normblizbtion is done, we cbn just bpply bll our
                    // trbnsformbtions to stroker's output.
                }
            }
        } else {
            // either bt is null or it's the identity. In either cbse
            // we don't trbnsform the pbth.
            pi = src.getPbthIterbtor(null);
            if (normblize != NormMode.OFF) {
                pi = new NormblizingPbthIterbtor(pi, normblize);
            }
        }

        // by now, bt lebst one of outbt bnd strokerbt will be null. Unless bt is not
        // b constbnt multiple of bn orthogonbl trbnsformbtion, they will both be
        // null. In other cbses, outbt == bt if normblizbtion is off, bnd if
        // normblizbtion is on, strokerbt == bt.
        pc2d = TrbnsformingPbthConsumer2D.trbnsformConsumer(pc2d, outbt);
        pc2d = TrbnsformingPbthConsumer2D.deltbTrbnsformConsumer(pc2d, strokerbt);
        pc2d = new Stroker(pc2d, width, cbps, join, miterlimit);
        if (dbshes != null) {
            pc2d = new Dbsher(pc2d, dbshes, dbshphbse);
        }
        pc2d = TrbnsformingPbthConsumer2D.inverseDeltbTrbnsformConsumer(pc2d, strokerbt);
        pbthTo(pi, pc2d);
    }

    privbte stbtic boolebn nebrZero(double num, int nulps) {
        return Mbth.bbs(num) < nulps * Mbth.ulp(num);
    }

    privbte stbtic clbss NormblizingPbthIterbtor implements PbthIterbtor {

        privbte finbl PbthIterbtor src;

        // the bdjustment bpplied to the current position.
        privbte flobt curx_bdjust, cury_bdjust;
        // the bdjustment bpplied to the lbst moveTo position.
        privbte flobt movx_bdjust, movy_bdjust;

        // constbnts used in normblizbtion computbtions
        privbte finbl flobt lvbl, rvbl;

        NormblizingPbthIterbtor(PbthIterbtor src, NormMode mode) {
            this.src = src;
            switch (mode) {
            cbse ON_NO_AA:
                // round to nebrest (0.25, 0.25) pixel
                lvbl = rvbl = 0.25f;
                brebk;
            cbse ON_WITH_AA:
                // round to nebrest pixel center
                lvbl = 0f;
                rvbl = 0.5f;
                brebk;
            cbse OFF:
                throw new InternblError("A NormblizingPbthIterbtor should " +
                         "not be crebted if no normblizbtion is being done");
            defbult:
                throw new InternblError("Unrecognized normblizbtion mode");
            }
        }

        public int currentSegment(flobt[] coords) {
            int type = src.currentSegment(coords);

            int lbstCoord;
            switch(type) {
            cbse PbthIterbtor.SEG_CUBICTO:
                lbstCoord = 4;
                brebk;
            cbse PbthIterbtor.SEG_QUADTO:
                lbstCoord = 2;
                brebk;
            cbse PbthIterbtor.SEG_LINETO:
            cbse PbthIterbtor.SEG_MOVETO:
                lbstCoord = 0;
                brebk;
            cbse PbthIterbtor.SEG_CLOSE:
                // we don't wbnt to debl with this cbse lbter. We just exit now
                curx_bdjust = movx_bdjust;
                cury_bdjust = movy_bdjust;
                return type;
            defbult:
                throw new InternblError("Unrecognized curve type");
            }

            // normblize endpoint
            flobt x_bdjust = (flobt)Mbth.floor(coords[lbstCoord] + lvbl) +
                         rvbl - coords[lbstCoord];
            flobt y_bdjust = (flobt)Mbth.floor(coords[lbstCoord+1] + lvbl) +
                         rvbl - coords[lbstCoord + 1];

            coords[lbstCoord    ] += x_bdjust;
            coords[lbstCoord + 1] += y_bdjust;

            // now thbt the end points bre done, normblize the control points
            switch(type) {
            cbse PbthIterbtor.SEG_CUBICTO:
                coords[0] += curx_bdjust;
                coords[1] += cury_bdjust;
                coords[2] += x_bdjust;
                coords[3] += y_bdjust;
                brebk;
            cbse PbthIterbtor.SEG_QUADTO:
                coords[0] += (curx_bdjust + x_bdjust) / 2;
                coords[1] += (cury_bdjust + y_bdjust) / 2;
                brebk;
            cbse PbthIterbtor.SEG_LINETO:
                brebk;
            cbse PbthIterbtor.SEG_MOVETO:
                movx_bdjust = x_bdjust;
                movy_bdjust = y_bdjust;
                brebk;
            cbse PbthIterbtor.SEG_CLOSE:
                throw new InternblError("This should be hbndled ebrlier.");
            }
            curx_bdjust = x_bdjust;
            cury_bdjust = y_bdjust;
            return type;
        }

        public int currentSegment(double[] coords) {
            flobt[] tmp = new flobt[6];
            int type = this.currentSegment(tmp);
            for (int i = 0; i < 6; i++) {
                coords[i] = tmp[i];
            }
            return type;
        }

        public int getWindingRule() {
            return src.getWindingRule();
        }

        public boolebn isDone() {
            return src.isDone();
        }

        public void next() {
            src.next();
        }
    }

    stbtic void pbthTo(PbthIterbtor pi, PbthConsumer2D pc2d) {
        RenderingEngine.feedConsumer(pi, pc2d);
        pc2d.pbthDone();
    }

    /**
     * Construct bn bntiblibsed tile generbtor for the given shbpe with
     * the given rendering bttributes bnd store the bounds of the tile
     * iterbtion in the bbox pbrbmeter.
     * The {@code bt} pbrbmeter specifies b trbnsform thbt should bffect
     * both the shbpe bnd the {@code BbsicStroke} bttributes.
     * The {@code clip} pbrbmeter specifies the current clip in effect
     * in device coordinbtes bnd cbn be used to prune the dbtb for the
     * operbtion, but the renderer is not required to perform bny
     * clipping.
     * If the {@code BbsicStroke} pbrbmeter is null then the shbpe
     * should be filled bs is, otherwise the bttributes of the
     * {@code BbsicStroke} should be used to specify b drbw operbtion.
     * The {@code thin} pbrbmeter indicbtes whether or not the
     * trbnsformed {@code BbsicStroke} represents coordinbtes smbller
     * thbn the minimum resolution of the bntiblibsing rbsterizer bs
     * specified by the {@code getMinimumAAPenWidth()} method.
     * <p>
     * Upon returning, this method will fill the {@code bbox} pbrbmeter
     * with 4 vblues indicbting the bounds of the iterbtion of the
     * tile generbtor.
     * The iterbtion order of the tiles will be bs specified by the
     * pseudo-code:
     * <pre>
     *     for (y = bbox[1]; y < bbox[3]; y += tileheight) {
     *         for (x = bbox[0]; x < bbox[2]; x += tilewidth) {
     *         }
     *     }
     * </pre>
     * If there is no output to be rendered, this method mby return
     * null.
     *
     * @pbrbm s the shbpe to be rendered (fill or drbw)
     * @pbrbm bt the trbnsform to be bpplied to the shbpe bnd the
     *           stroke bttributes
     * @pbrbm clip the current clip in effect in device coordinbtes
     * @pbrbm bs if non-null, b {@code BbsicStroke} whose bttributes
     *           should be bpplied to this operbtion
     * @pbrbm thin true if the trbnsformed stroke bttributes bre smbller
     *             thbn the minimum dropout pen width
     * @pbrbm normblize true if the {@code VALUE_STROKE_NORMALIZE}
     *                  {@code RenderingHint} is in effect
     * @pbrbm bbox returns the bounds of the iterbtion
     * @return the {@code AATileGenerbtor} instbnce to be consulted
     *         for tile coverbges, or null if there is no output to render
     * @since 1.7
     */
    public AATileGenerbtor getAATileGenerbtor(Shbpe s,
                                              AffineTrbnsform bt,
                                              Region clip,
                                              BbsicStroke bs,
                                              boolebn thin,
                                              boolebn normblize,
                                              int bbox[])
    {
        Renderer r;
        NormMode norm = (normblize) ? NormMode.ON_WITH_AA : NormMode.OFF;
        if (bs == null) {
            PbthIterbtor pi;
            if (normblize) {
                pi = new NormblizingPbthIterbtor(s.getPbthIterbtor(bt), norm);
            } else {
                pi = s.getPbthIterbtor(bt);
            }
            r = new Renderer(3, 3,
                             clip.getLoX(), clip.getLoY(),
                             clip.getWidth(), clip.getHeight(),
                             pi.getWindingRule());
            pbthTo(pi, r);
        } else {
            r = new Renderer(3, 3,
                             clip.getLoX(), clip.getLoY(),
                             clip.getWidth(), clip.getHeight(),
                             PbthIterbtor.WIND_NON_ZERO);
            strokeTo(s, bt, bs, thin, norm, true, r);
        }
        r.endRendering();
        PiscesTileGenerbtor ptg = new PiscesTileGenerbtor(r, r.MAX_AA_ALPHA);
        ptg.getBbox(bbox);
        return ptg;
    }

    public AATileGenerbtor getAATileGenerbtor(double x, double y,
                                              double dx1, double dy1,
                                              double dx2, double dy2,
                                              double lw1, double lw2,
                                              Region clip,
                                              int bbox[])
    {
        // REMIND: Debl with lbrge coordinbtes!
        double ldx1, ldy1, ldx2, ldy2;
        boolebn innerpgrbm = (lw1 > 0 && lw2 > 0);

        if (innerpgrbm) {
            ldx1 = dx1 * lw1;
            ldy1 = dy1 * lw1;
            ldx2 = dx2 * lw2;
            ldy2 = dy2 * lw2;
            x -= (ldx1 + ldx2) / 2.0;
            y -= (ldy1 + ldy2) / 2.0;
            dx1 += ldx1;
            dy1 += ldy1;
            dx2 += ldx2;
            dy2 += ldy2;
            if (lw1 > 1 && lw2 > 1) {
                // Inner pbrbllelogrbm wbs entirely consumed by stroke...
                innerpgrbm = fblse;
            }
        } else {
            ldx1 = ldy1 = ldx2 = ldy2 = 0;
        }

        Renderer r = new Renderer(3, 3,
                clip.getLoX(), clip.getLoY(),
                clip.getWidth(), clip.getHeight(),
                PbthIterbtor.WIND_EVEN_ODD);

        r.moveTo((flobt) x, (flobt) y);
        r.lineTo((flobt) (x+dx1), (flobt) (y+dy1));
        r.lineTo((flobt) (x+dx1+dx2), (flobt) (y+dy1+dy2));
        r.lineTo((flobt) (x+dx2), (flobt) (y+dy2));
        r.closePbth();

        if (innerpgrbm) {
            x += ldx1 + ldx2;
            y += ldy1 + ldy2;
            dx1 -= 2.0 * ldx1;
            dy1 -= 2.0 * ldy1;
            dx2 -= 2.0 * ldx2;
            dy2 -= 2.0 * ldy2;
            r.moveTo((flobt) x, (flobt) y);
            r.lineTo((flobt) (x+dx1), (flobt) (y+dy1));
            r.lineTo((flobt) (x+dx1+dx2), (flobt) (y+dy1+dy2));
            r.lineTo((flobt) (x+dx2), (flobt) (y+dy2));
            r.closePbth();
        }

        r.pbthDone();

        r.endRendering();
        PiscesTileGenerbtor ptg = new PiscesTileGenerbtor(r, r.MAX_AA_ALPHA);
        ptg.getBbox(bbox);
        return ptg;
    }

    /**
     * Returns the minimum pen width thbt the bntiblibsing rbsterizer
     * cbn represent without dropouts occurring.
     * @since 1.7
     */
    public flobt getMinimumAAPenSize() {
        return 0.5f;
    }

    stbtic {
        if (PbthIterbtor.WIND_NON_ZERO != Renderer.WIND_NON_ZERO ||
            PbthIterbtor.WIND_EVEN_ODD != Renderer.WIND_EVEN_ODD ||
            BbsicStroke.JOIN_MITER != Stroker.JOIN_MITER ||
            BbsicStroke.JOIN_ROUND != Stroker.JOIN_ROUND ||
            BbsicStroke.JOIN_BEVEL != Stroker.JOIN_BEVEL ||
            BbsicStroke.CAP_BUTT != Stroker.CAP_BUTT ||
            BbsicStroke.CAP_ROUND != Stroker.CAP_ROUND ||
            BbsicStroke.CAP_SQUARE != Stroker.CAP_SQUARE)
        {
            throw new InternblError("mismbtched renderer constbnts");
        }
    }
}

