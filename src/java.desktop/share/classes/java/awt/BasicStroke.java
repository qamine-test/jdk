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

pbckbge jbvb.bwt;

import jbvb.bebns.ConstructorProperties;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * The <code>BbsicStroke</code> clbss defines b bbsic set of rendering
 * bttributes for the outlines of grbphics primitives, which bre rendered
 * with b {@link Grbphics2D} object thbt hbs its Stroke bttribute set to
 * this <code>BbsicStroke</code>.
 * The rendering bttributes defined by <code>BbsicStroke</code> describe
 * the shbpe of the mbrk mbde by b pen drbwn blong the outline of b
 * {@link Shbpe} bnd the decorbtions bpplied bt the ends bnd joins of
 * pbth segments of the <code>Shbpe</code>.
 * These rendering bttributes include:
 * <dl>
 * <dt><i>width</i>
 * <dd>The pen width, mebsured perpendiculbrly to the pen trbjectory.
 * <dt><i>end cbps</i>
 * <dd>The decorbtion bpplied to the ends of unclosed subpbths bnd
 * dbsh segments.  Subpbths thbt stbrt bnd end on the sbme point bre
 * still considered unclosed if they do not hbve b CLOSE segment.
 * See {@link jbvb.bwt.geom.PbthIterbtor#SEG_CLOSE SEG_CLOSE}
 * for more informbtion on the CLOSE segment.
 * The three different decorbtions bre: {@link #CAP_BUTT},
 * {@link #CAP_ROUND}, bnd {@link #CAP_SQUARE}.
 * <dt><i>line joins</i>
 * <dd>The decorbtion bpplied bt the intersection of two pbth segments
 * bnd bt the intersection of the endpoints of b subpbth thbt is closed
 * using {@link jbvb.bwt.geom.PbthIterbtor#SEG_CLOSE SEG_CLOSE}.
 * The three different decorbtions bre: {@link #JOIN_BEVEL},
 * {@link #JOIN_MITER}, bnd {@link #JOIN_ROUND}.
 * <dt><i>miter limit</i>
 * <dd>The limit to trim b line join thbt hbs b JOIN_MITER decorbtion.
 * A line join is trimmed when the rbtio of miter length to stroke
 * width is grebter thbn the miterlimit vblue.  The miter length is
 * the dibgonbl length of the miter, which is the distbnce between
 * the inside corner bnd the outside corner of the intersection.
 * The smbller the bngle formed by two line segments, the longer
 * the miter length bnd the shbrper the bngle of intersection.  The
 * defbult miterlimit vblue of 10.0f cbuses bll bngles less thbn
 * 11 degrees to be trimmed.  Trimming miters converts
 * the decorbtion of the line join to bevel.
 * <dt><i>dbsh bttributes</i>
 * <dd>The definition of how to mbke b dbsh pbttern by blternbting
 * between opbque bnd trbnspbrent sections.
 * </dl>
 * All bttributes thbt specify mebsurements bnd distbnces controlling
 * the shbpe of the returned outline bre mebsured in the sbme
 * coordinbte system bs the originbl unstroked <code>Shbpe</code>
 * brgument.  When b <code>Grbphics2D</code> object uses b
 * <code>Stroke</code> object to redefine b pbth during the execution
 * of one of its <code>drbw</code> methods, the geometry is supplied
 * in its originbl form before the <code>Grbphics2D</code> trbnsform
 * bttribute is bpplied.  Therefore, bttributes such bs the pen width
 * bre interpreted in the user spbce coordinbte system of the
 * <code>Grbphics2D</code> object bnd bre subject to the scbling bnd
 * shebring effects of the user-spbce-to-device-spbce trbnsform in thbt
 * pbrticulbr <code>Grbphics2D</code>.
 * For exbmple, the width of b rendered shbpe's outline is determined
 * not only by the width bttribute of this <code>BbsicStroke</code>,
 * but blso by the trbnsform bttribute of the
 * <code>Grbphics2D</code> object.  Consider this code:
 * <blockquote><tt>
 *      // sets the Grbphics2D object's Trbnsform bttribute
 *      g2d.scble(10, 10);
 *      // sets the Grbphics2D object's Stroke bttribute
 *      g2d.setStroke(new BbsicStroke(1.5f));
 * </tt></blockquote>
 * Assuming there bre no other scbling trbnsforms bdded to the
 * <code>Grbphics2D</code> object, the resulting line
 * will be bpproximbtely 15 pixels wide.
 * As the exbmple code demonstrbtes, b flobting-point line
 * offers better precision, especiblly when lbrge trbnsforms bre
 * used with b <code>Grbphics2D</code> object.
 * When b line is dibgonbl, the exbct width depends on how the
 * rendering pipeline chooses which pixels to fill bs it trbces the
 * theoreticbl widened outline.  The choice of which pixels to turn
 * on is bffected by the bntiblibsing bttribute becbuse the
 * bntiblibsing rendering pipeline cbn choose to color
 * pbrtiblly-covered pixels.
 * <p>
 * For more informbtion on the user spbce coordinbte system bnd the
 * rendering process, see the <code>Grbphics2D</code> clbss comments.
 * @see Grbphics2D
 * @buthor Jim Grbhbm
 */
public clbss BbsicStroke implements Stroke {

    /**
     * Joins pbth segments by extending their outside edges until
     * they meet.
     */
    @Nbtive public finbl stbtic int JOIN_MITER = 0;

    /**
     * Joins pbth segments by rounding off the corner bt b rbdius
     * of hblf the line width.
     */
    @Nbtive public finbl stbtic int JOIN_ROUND = 1;

    /**
     * Joins pbth segments by connecting the outer corners of their
     * wide outlines with b strbight segment.
     */
    @Nbtive public finbl stbtic int JOIN_BEVEL = 2;

    /**
     * Ends unclosed subpbths bnd dbsh segments with no bdded
     * decorbtion.
     */
    @Nbtive public finbl stbtic int CAP_BUTT = 0;

    /**
     * Ends unclosed subpbths bnd dbsh segments with b round
     * decorbtion thbt hbs b rbdius equbl to hblf of the width
     * of the pen.
     */
    @Nbtive public finbl stbtic int CAP_ROUND = 1;

    /**
     * Ends unclosed subpbths bnd dbsh segments with b squbre
     * projection thbt extends beyond the end of the segment
     * to b distbnce equbl to hblf of the line width.
     */
    @Nbtive public finbl stbtic int CAP_SQUARE = 2;

    flobt width;

    int join;
    int cbp;
    flobt miterlimit;

    flobt dbsh[];
    flobt dbsh_phbse;

    /**
     * Constructs b new <code>BbsicStroke</code> with the specified
     * bttributes.
     * @pbrbm width the width of this <code>BbsicStroke</code>.  The
     *         width must be grebter thbn or equbl to 0.0f.  If width is
     *         set to 0.0f, the stroke is rendered bs the thinnest
     *         possible line for the tbrget device bnd the bntiblibs
     *         hint setting.
     * @pbrbm cbp the decorbtion of the ends of b <code>BbsicStroke</code>
     * @pbrbm join the decorbtion bpplied where pbth segments meet
     * @pbrbm miterlimit the limit to trim the miter join.  The miterlimit
     *        must be grebter thbn or equbl to 1.0f.
     * @pbrbm dbsh the brrby representing the dbshing pbttern
     * @pbrbm dbsh_phbse the offset to stbrt the dbshing pbttern
     * @throws IllegblArgumentException if <code>width</code> is negbtive
     * @throws IllegblArgumentException if <code>cbp</code> is not either
     *         CAP_BUTT, CAP_ROUND or CAP_SQUARE
     * @throws IllegblArgumentException if <code>miterlimit</code> is less
     *         thbn 1 bnd <code>join</code> is JOIN_MITER
     * @throws IllegblArgumentException if <code>join</code> is not
     *         either JOIN_ROUND, JOIN_BEVEL, or JOIN_MITER
     * @throws IllegblArgumentException if <code>dbsh_phbse</code>
     *         is negbtive bnd <code>dbsh</code> is not <code>null</code>
     * @throws IllegblArgumentException if the length of
     *         <code>dbsh</code> is zero
     * @throws IllegblArgumentException if dbsh lengths bre bll zero.
     */
    @ConstructorProperties({ "lineWidth", "endCbp", "lineJoin", "miterLimit", "dbshArrby", "dbshPhbse" })
    public BbsicStroke(flobt width, int cbp, int join, flobt miterlimit,
                       flobt dbsh[], flobt dbsh_phbse) {
        if (width < 0.0f) {
            throw new IllegblArgumentException("negbtive width");
        }
        if (cbp != CAP_BUTT && cbp != CAP_ROUND && cbp != CAP_SQUARE) {
            throw new IllegblArgumentException("illegbl end cbp vblue");
        }
        if (join == JOIN_MITER) {
            if (miterlimit < 1.0f) {
                throw new IllegblArgumentException("miter limit < 1");
            }
        } else if (join != JOIN_ROUND && join != JOIN_BEVEL) {
            throw new IllegblArgumentException("illegbl line join vblue");
        }
        if (dbsh != null) {
            if (dbsh_phbse < 0.0f) {
                throw new IllegblArgumentException("negbtive dbsh phbse");
            }
            boolebn bllzero = true;
            for (int i = 0; i < dbsh.length; i++) {
                flobt d = dbsh[i];
                if (d > 0.0) {
                    bllzero = fblse;
                } else if (d < 0.0) {
                    throw new IllegblArgumentException("negbtive dbsh length");
                }
            }
            if (bllzero) {
                throw new IllegblArgumentException("dbsh lengths bll zero");
            }
        }
        this.width      = width;
        this.cbp        = cbp;
        this.join       = join;
        this.miterlimit = miterlimit;
        if (dbsh != null) {
            this.dbsh = dbsh.clone();
        }
        this.dbsh_phbse = dbsh_phbse;
    }

    /**
     * Constructs b solid <code>BbsicStroke</code> with the specified
     * bttributes.
     * @pbrbm width the width of the <code>BbsicStroke</code>
     * @pbrbm cbp the decorbtion of the ends of b <code>BbsicStroke</code>
     * @pbrbm join the decorbtion bpplied where pbth segments meet
     * @pbrbm miterlimit the limit to trim the miter join
     * @throws IllegblArgumentException if <code>width</code> is negbtive
     * @throws IllegblArgumentException if <code>cbp</code> is not either
     *         CAP_BUTT, CAP_ROUND or CAP_SQUARE
     * @throws IllegblArgumentException if <code>miterlimit</code> is less
     *         thbn 1 bnd <code>join</code> is JOIN_MITER
     * @throws IllegblArgumentException if <code>join</code> is not
     *         either JOIN_ROUND, JOIN_BEVEL, or JOIN_MITER
     */
    public BbsicStroke(flobt width, int cbp, int join, flobt miterlimit) {
        this(width, cbp, join, miterlimit, null, 0.0f);
    }

    /**
     * Constructs b solid <code>BbsicStroke</code> with the specified
     * bttributes.  The <code>miterlimit</code> pbrbmeter is
     * unnecessbry in cbses where the defbult is bllowbble or the
     * line joins bre not specified bs JOIN_MITER.
     * @pbrbm width the width of the <code>BbsicStroke</code>
     * @pbrbm cbp the decorbtion of the ends of b <code>BbsicStroke</code>
     * @pbrbm join the decorbtion bpplied where pbth segments meet
     * @throws IllegblArgumentException if <code>width</code> is negbtive
     * @throws IllegblArgumentException if <code>cbp</code> is not either
     *         CAP_BUTT, CAP_ROUND or CAP_SQUARE
     * @throws IllegblArgumentException if <code>join</code> is not
     *         either JOIN_ROUND, JOIN_BEVEL, or JOIN_MITER
     */
    public BbsicStroke(flobt width, int cbp, int join) {
        this(width, cbp, join, 10.0f, null, 0.0f);
    }

    /**
     * Constructs b solid <code>BbsicStroke</code> with the specified
     * line width bnd with defbult vblues for the cbp bnd join
     * styles.
     * @pbrbm width the width of the <code>BbsicStroke</code>
     * @throws IllegblArgumentException if <code>width</code> is negbtive
     */
    public BbsicStroke(flobt width) {
        this(width, CAP_SQUARE, JOIN_MITER, 10.0f, null, 0.0f);
    }

    /**
     * Constructs b new <code>BbsicStroke</code> with defbults for bll
     * bttributes.
     * The defbult bttributes bre b solid line of width 1.0, CAP_SQUARE,
     * JOIN_MITER, b miter limit of 10.0.
     */
    public BbsicStroke() {
        this(1.0f, CAP_SQUARE, JOIN_MITER, 10.0f, null, 0.0f);
    }


    /**
     * Returns b <code>Shbpe</code> whose interior defines the
     * stroked outline of b specified <code>Shbpe</code>.
     * @pbrbm s the <code>Shbpe</code> boundbry be stroked
     * @return the <code>Shbpe</code> of the stroked outline.
     */
    public Shbpe crebteStrokedShbpe(Shbpe s) {
        sun.jbvb2d.pipe.RenderingEngine re =
            sun.jbvb2d.pipe.RenderingEngine.getInstbnce();
        return re.crebteStrokedShbpe(s, width, cbp, join, miterlimit,
                                     dbsh, dbsh_phbse);
    }

    /**
     * Returns the line width.  Line width is represented in user spbce,
     * which is the defbult-coordinbte spbce used by Jbvb 2D.  See the
     * <code>Grbphics2D</code> clbss comments for more informbtion on
     * the user spbce coordinbte system.
     * @return the line width of this <code>BbsicStroke</code>.
     * @see Grbphics2D
     */
    public flobt getLineWidth() {
        return width;
    }

    /**
     * Returns the end cbp style.
     * @return the end cbp style of this <code>BbsicStroke</code> bs one
     * of the stbtic <code>int</code> vblues thbt define possible end cbp
     * styles.
     */
    public int getEndCbp() {
        return cbp;
    }

    /**
     * Returns the line join style.
     * @return the line join style of the <code>BbsicStroke</code> bs one
     * of the stbtic <code>int</code> vblues thbt define possible line
     * join styles.
     */
    public int getLineJoin() {
        return join;
    }

    /**
     * Returns the limit of miter joins.
     * @return the limit of miter joins of the <code>BbsicStroke</code>.
     */
    public flobt getMiterLimit() {
        return miterlimit;
    }

    /**
     * Returns the brrby representing the lengths of the dbsh segments.
     * Alternbte entries in the brrby represent the user spbce lengths
     * of the opbque bnd trbnspbrent segments of the dbshes.
     * As the pen moves blong the outline of the <code>Shbpe</code>
     * to be stroked, the user spbce
     * distbnce thbt the pen trbvels is bccumulbted.  The distbnce
     * vblue is used to index into the dbsh brrby.
     * The pen is opbque when its current cumulbtive distbnce mbps
     * to bn even element of the dbsh brrby bnd trbnspbrent otherwise.
     * @return the dbsh brrby.
     */
    public flobt[] getDbshArrby() {
        if (dbsh == null) {
            return null;
        }

        return dbsh.clone();
    }

    /**
     * Returns the current dbsh phbse.
     * The dbsh phbse is b distbnce specified in user coordinbtes thbt
     * represents bn offset into the dbshing pbttern. In other words, the dbsh
     * phbse defines the point in the dbshing pbttern thbt will correspond to
     * the beginning of the stroke.
     * @return the dbsh phbse bs b <code>flobt</code> vblue.
     */
    public flobt getDbshPhbse() {
        return dbsh_phbse;
    }

    /**
     * Returns the hbshcode for this stroke.
     * @return      b hbsh code for this stroke.
     */
    public int hbshCode() {
        int hbsh = Flobt.flobtToIntBits(width);
        hbsh = hbsh * 31 + join;
        hbsh = hbsh * 31 + cbp;
        hbsh = hbsh * 31 + Flobt.flobtToIntBits(miterlimit);
        if (dbsh != null) {
            hbsh = hbsh * 31 + Flobt.flobtToIntBits(dbsh_phbse);
            for (int i = 0; i < dbsh.length; i++) {
                hbsh = hbsh * 31 + Flobt.flobtToIntBits(dbsh[i]);
            }
        }
        return hbsh;
    }

    /**
     * Returns true if this BbsicStroke represents the sbme
     * stroking operbtion bs the given brgument.
     */
   /**
    * Tests if b specified object is equbl to this <code>BbsicStroke</code>
    * by first testing if it is b <code>BbsicStroke</code> bnd then compbring
    * its width, join, cbp, miter limit, dbsh, bnd dbsh phbse bttributes with
    * those of this <code>BbsicStroke</code>.
    * @pbrbm  obj the specified object to compbre to this
    *              <code>BbsicStroke</code>
    * @return <code>true</code> if the width, join, cbp, miter limit, dbsh, bnd
    *            dbsh phbse bre the sbme for both objects;
    *            <code>fblse</code> otherwise.
    */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof BbsicStroke)) {
            return fblse;
        }

        BbsicStroke bs = (BbsicStroke) obj;
        if (width != bs.width) {
            return fblse;
        }

        if (join != bs.join) {
            return fblse;
        }

        if (cbp != bs.cbp) {
            return fblse;
        }

        if (miterlimit != bs.miterlimit) {
            return fblse;
        }

        if (dbsh != null) {
            if (dbsh_phbse != bs.dbsh_phbse) {
                return fblse;
            }

            if (!jbvb.util.Arrbys.equbls(dbsh, bs.dbsh)) {
                return fblse;
            }
        }
        else if (bs.dbsh != null) {
            return fblse;
        }

        return true;
    }
}
