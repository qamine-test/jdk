/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.geom;

import jbvb.bwt.Shbpe;
import jbvb.bebns.ConstructorProperties;

/**
 * The <code>AffineTrbnsform</code> clbss represents b 2D bffine trbnsform
 * thbt performs b linebr mbpping from 2D coordinbtes to other 2D
 * coordinbtes thbt preserves the "strbightness" bnd
 * "pbrbllelness" of lines.  Affine trbnsformbtions cbn be constructed
 * using sequences of trbnslbtions, scbles, flips, rotbtions, bnd shebrs.
 * <p>
 * Such b coordinbte trbnsformbtion cbn be represented by b 3 row by
 * 3 column mbtrix with bn implied lbst row of [ 0 0 1 ].  This mbtrix
 * trbnsforms source coordinbtes {@code (x,y)} into
 * destinbtion coordinbtes {@code (x',y')} by considering
 * them to be b column vector bnd multiplying the coordinbte vector
 * by the mbtrix bccording to the following process:
 * <pre>
 *      [ x']   [  m00  m01  m02  ] [ x ]   [ m00x + m01y + m02 ]
 *      [ y'] = [  m10  m11  m12  ] [ y ] = [ m10x + m11y + m12 ]
 *      [ 1 ]   [   0    0    1   ] [ 1 ]   [         1         ]
 * </pre>
 * <h3><b nbme="qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b></h3>
 * <p>
 * In some vbribtions of the <code>rotbte</code> methods in the
 * <code>AffineTrbnsform</code> clbss, b double-precision brgument
 * specifies the bngle of rotbtion in rbdibns.
 * These methods hbve specibl hbndling for rotbtions of bpproximbtely
 * 90 degrees (including multiples such bs 180, 270, bnd 360 degrees),
 * so thbt the common cbse of qubdrbnt rotbtion is hbndled more
 * efficiently.
 * This specibl hbndling cbn cbuse bngles very close to multiples of
 * 90 degrees to be trebted bs if they were exbct multiples of
 * 90 degrees.
 * For smbll multiples of 90 degrees the rbnge of bngles trebted
 * bs b qubdrbnt rotbtion is bpproximbtely 0.00000121 degrees wide.
 * This section explbins why such specibl cbre is needed bnd how
 * it is implemented.
 * <p>
 * Since 90 degrees is represented bs <code>PI/2</code> in rbdibns,
 * bnd since PI is b trbnscendentbl (bnd therefore irrbtionbl) number,
 * it is not possible to exbctly represent b multiple of 90 degrees bs
 * bn exbct double precision vblue mebsured in rbdibns.
 * As b result it is theoreticblly impossible to describe qubdrbnt
 * rotbtions (90, 180, 270 or 360 degrees) using these vblues.
 * Double precision flobting point vblues cbn get very close to
 * non-zero multiples of <code>PI/2</code> but never close enough
 * for the sine or cosine to be exbctly 0.0, 1.0 or -1.0.
 * The implementbtions of <code>Mbth.sin()</code> bnd
 * <code>Mbth.cos()</code> correspondingly never return 0.0
 * for bny cbse other thbn <code>Mbth.sin(0.0)</code>.
 * These sbme implementbtions do, however, return exbctly 1.0 bnd
 * -1.0 for some rbnge of numbers bround ebch multiple of 90
 * degrees since the correct bnswer is so close to 1.0 or -1.0 thbt
 * the double precision significbnd cbnnot represent the difference
 * bs bccurbtely bs it cbn for numbers thbt bre nebr 0.0.
 * <p>
 * The net result of these issues is thbt if the
 * <code>Mbth.sin()</code> bnd <code>Mbth.cos()</code> methods
 * bre used to directly generbte the vblues for the mbtrix modificbtions
 * during these rbdibn-bbsed rotbtion operbtions then the resulting
 * trbnsform is never strictly clbssifibble bs b qubdrbnt rotbtion
 * even for b simple cbse like <code>rotbte(Mbth.PI/2.0)</code>,
 * due to minor vbribtions in the mbtrix cbused by the non-0.0 vblues
 * obtbined for the sine bnd cosine.
 * If these trbnsforms bre not clbssified bs qubdrbnt rotbtions then
 * subsequent code which bttempts to optimize further operbtions bbsed
 * upon the type of the trbnsform will be relegbted to its most generbl
 * implementbtion.
 * <p>
 * Becbuse qubdrbnt rotbtions bre fbirly common,
 * this clbss should hbndle these cbses rebsonbbly quickly, both in
 * bpplying the rotbtions to the trbnsform bnd in bpplying the resulting
 * trbnsform to the coordinbtes.
 * To fbcilitbte this optimbl hbndling, the methods which tbke bn bngle
 * of rotbtion mebsured in rbdibns bttempt to detect bngles thbt bre
 * intended to be qubdrbnt rotbtions bnd trebt them bs such.
 * These methods therefore trebt bn bngle <em>thetb</em> bs b qubdrbnt
 * rotbtion if either <code>Mbth.sin(<em>thetb</em>)</code> or
 * <code>Mbth.cos(<em>thetb</em>)</code> returns exbctly 1.0 or -1.0.
 * As b rule of thumb, this property holds true for b rbnge of
 * bpproximbtely 0.0000000211 rbdibns (or 0.00000121 degrees) bround
 * smbll multiples of <code>Mbth.PI/2.0</code>.
 *
 * @buthor Jim Grbhbm
 * @since 1.2
 */
public clbss AffineTrbnsform implements Clonebble, jbvb.io.Seriblizbble {

    /*
     * This constbnt is only useful for the cbched type field.
     * It indicbtes thbt the type hbs been decbched bnd must be recblculbted.
     */
    privbte stbtic finbl int TYPE_UNKNOWN = -1;

    /**
     * This constbnt indicbtes thbt the trbnsform defined by this object
     * is bn identity trbnsform.
     * An identity trbnsform is one in which the output coordinbtes bre
     * blwbys the sbme bs the input coordinbtes.
     * If this trbnsform is bnything other thbn the identity trbnsform,
     * the type will either be the constbnt GENERAL_TRANSFORM or b
     * combinbtion of the bppropribte flbg bits for the vbrious coordinbte
     * conversions thbt this trbnsform performs.
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_IDENTITY = 0;

    /**
     * This flbg bit indicbtes thbt the trbnsform defined by this object
     * performs b trbnslbtion in bddition to the conversions indicbted
     * by other flbg bits.
     * A trbnslbtion moves the coordinbtes by b constbnt bmount in x
     * bnd y without chbnging the length or bngle of vectors.
     * @see #TYPE_IDENTITY
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_TRANSLATION = 1;

    /**
     * This flbg bit indicbtes thbt the trbnsform defined by this object
     * performs b uniform scble in bddition to the conversions indicbted
     * by other flbg bits.
     * A uniform scble multiplies the length of vectors by the sbme bmount
     * in both the x bnd y directions without chbnging the bngle between
     * vectors.
     * This flbg bit is mutublly exclusive with the TYPE_GENERAL_SCALE flbg.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_UNIFORM_SCALE = 2;

    /**
     * This flbg bit indicbtes thbt the trbnsform defined by this object
     * performs b generbl scble in bddition to the conversions indicbted
     * by other flbg bits.
     * A generbl scble multiplies the length of vectors by different
     * bmounts in the x bnd y directions without chbnging the bngle
     * between perpendiculbr vectors.
     * This flbg bit is mutublly exclusive with the TYPE_UNIFORM_SCALE flbg.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_GENERAL_SCALE = 4;

    /**
     * This constbnt is b bit mbsk for bny of the scble flbg bits.
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @since 1.2
     */
    public stbtic finbl int TYPE_MASK_SCALE = (TYPE_UNIFORM_SCALE |
                                               TYPE_GENERAL_SCALE);

    /**
     * This flbg bit indicbtes thbt the trbnsform defined by this object
     * performs b mirror imbge flip bbout some bxis which chbnges the
     * normblly right hbnded coordinbte system into b left hbnded
     * system in bddition to the conversions indicbted by other flbg bits.
     * A right hbnded coordinbte system is one where the positive X
     * bxis rotbtes counterclockwise to overlby the positive Y bxis
     * similbr to the direction thbt the fingers on your right hbnd
     * curl when you stbre end on bt your thumb.
     * A left hbnded coordinbte system is one where the positive X
     * bxis rotbtes clockwise to overlby the positive Y bxis similbr
     * to the direction thbt the fingers on your left hbnd curl.
     * There is no mbthembticbl wby to determine the bngle of the
     * originbl flipping or mirroring trbnsformbtion since bll bngles
     * of flip bre identicbl given bn bppropribte bdjusting rotbtion.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_FLIP = 64;
    /* NOTE: TYPE_FLIP wbs bdded bfter GENERAL_TRANSFORM wbs in public
     * circulbtion bnd the flbg bits could no longer be conveniently
     * renumbered without introducing binbry incompbtibility in outside
     * code.
     */

    /**
     * This flbg bit indicbtes thbt the trbnsform defined by this object
     * performs b qubdrbnt rotbtion by some multiple of 90 degrees in
     * bddition to the conversions indicbted by other flbg bits.
     * A rotbtion chbnges the bngles of vectors by the sbme bmount
     * regbrdless of the originbl direction of the vector bnd without
     * chbnging the length of the vector.
     * This flbg bit is mutublly exclusive with the TYPE_GENERAL_ROTATION flbg.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_QUADRANT_ROTATION = 8;

    /**
     * This flbg bit indicbtes thbt the trbnsform defined by this object
     * performs b rotbtion by bn brbitrbry bngle in bddition to the
     * conversions indicbted by other flbg bits.
     * A rotbtion chbnges the bngles of vectors by the sbme bmount
     * regbrdless of the originbl direction of the vector bnd without
     * chbnging the length of the vector.
     * This flbg bit is mutublly exclusive with the
     * TYPE_QUADRANT_ROTATION flbg.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_GENERAL_ROTATION = 16;

    /**
     * This constbnt is b bit mbsk for bny of the rotbtion flbg bits.
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @since 1.2
     */
    public stbtic finbl int TYPE_MASK_ROTATION = (TYPE_QUADRANT_ROTATION |
                                                  TYPE_GENERAL_ROTATION);

    /**
     * This constbnt indicbtes thbt the trbnsform defined by this object
     * performs bn brbitrbry conversion of the input coordinbtes.
     * If this trbnsform cbn be clbssified by bny of the bbove constbnts,
     * the type will either be the constbnt TYPE_IDENTITY or b
     * combinbtion of the bppropribte flbg bits for the vbrious coordinbte
     * conversions thbt this trbnsform performs.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #getType
     * @since 1.2
     */
    public stbtic finbl int TYPE_GENERAL_TRANSFORM = 32;

    /**
     * This constbnt is used for the internbl stbte vbribble to indicbte
     * thbt no cblculbtions need to be performed bnd thbt the source
     * coordinbtes only need to be copied to their destinbtions to
     * complete the trbnsformbtion equbtion of this trbnsform.
     * @see #APPLY_TRANSLATE
     * @see #APPLY_SCALE
     * @see #APPLY_SHEAR
     * @see #stbte
     */
    stbtic finbl int APPLY_IDENTITY = 0;

    /**
     * This constbnt is used for the internbl stbte vbribble to indicbte
     * thbt the trbnslbtion components of the mbtrix (m02 bnd m12) need
     * to be bdded to complete the trbnsformbtion equbtion of this trbnsform.
     * @see #APPLY_IDENTITY
     * @see #APPLY_SCALE
     * @see #APPLY_SHEAR
     * @see #stbte
     */
    stbtic finbl int APPLY_TRANSLATE = 1;

    /**
     * This constbnt is used for the internbl stbte vbribble to indicbte
     * thbt the scbling components of the mbtrix (m00 bnd m11) need
     * to be fbctored in to complete the trbnsformbtion equbtion of
     * this trbnsform.  If the APPLY_SHEAR bit is blso set then it
     * indicbtes thbt the scbling components bre not both 0.0.  If the
     * APPLY_SHEAR bit is not blso set then it indicbtes thbt the
     * scbling components bre not both 1.0.  If neither the APPLY_SHEAR
     * nor the APPLY_SCALE bits bre set then the scbling components
     * bre both 1.0, which mebns thbt the x bnd y components contribute
     * to the trbnsformed coordinbte, but they bre not multiplied by
     * bny scbling fbctor.
     * @see #APPLY_IDENTITY
     * @see #APPLY_TRANSLATE
     * @see #APPLY_SHEAR
     * @see #stbte
     */
    stbtic finbl int APPLY_SCALE = 2;

    /**
     * This constbnt is used for the internbl stbte vbribble to indicbte
     * thbt the shebring components of the mbtrix (m01 bnd m10) need
     * to be fbctored in to complete the trbnsformbtion equbtion of this
     * trbnsform.  The presence of this bit in the stbte vbribble chbnges
     * the interpretbtion of the APPLY_SCALE bit bs indicbted in its
     * documentbtion.
     * @see #APPLY_IDENTITY
     * @see #APPLY_TRANSLATE
     * @see #APPLY_SCALE
     * @see #stbte
     */
    stbtic finbl int APPLY_SHEAR = 4;

    /*
     * For methods which combine together the stbte of two sepbrbte
     * trbnsforms bnd dispbtch bbsed upon the combinbtion, these constbnts
     * specify how fbr to shift one of the stbtes so thbt the two stbtes
     * bre mutublly non-interfering bnd provide constbnts for testing the
     * bits of the shifted (HI) stbte.  The methods in this clbss use
     * the convention thbt the stbte of "this" trbnsform is unshifted bnd
     * the stbte of the "other" or "brgument" trbnsform is shifted (HI).
     */
    privbte stbtic finbl int HI_SHIFT = 3;
    privbte stbtic finbl int HI_IDENTITY = APPLY_IDENTITY << HI_SHIFT;
    privbte stbtic finbl int HI_TRANSLATE = APPLY_TRANSLATE << HI_SHIFT;
    privbte stbtic finbl int HI_SCALE = APPLY_SCALE << HI_SHIFT;
    privbte stbtic finbl int HI_SHEAR = APPLY_SHEAR << HI_SHIFT;

    /**
     * The X coordinbte scbling element of the 3x3
     * bffine trbnsformbtion mbtrix.
     *
     * @seribl
     */
    double m00;

    /**
     * The Y coordinbte shebring element of the 3x3
     * bffine trbnsformbtion mbtrix.
     *
     * @seribl
     */
     double m10;

    /**
     * The X coordinbte shebring element of the 3x3
     * bffine trbnsformbtion mbtrix.
     *
     * @seribl
     */
     double m01;

    /**
     * The Y coordinbte scbling element of the 3x3
     * bffine trbnsformbtion mbtrix.
     *
     * @seribl
     */
     double m11;

    /**
     * The X coordinbte of the trbnslbtion element of the
     * 3x3 bffine trbnsformbtion mbtrix.
     *
     * @seribl
     */
     double m02;

    /**
     * The Y coordinbte of the trbnslbtion element of the
     * 3x3 bffine trbnsformbtion mbtrix.
     *
     * @seribl
     */
     double m12;

    /**
     * This field keeps trbck of which components of the mbtrix need to
     * be bpplied when performing b trbnsformbtion.
     * @see #APPLY_IDENTITY
     * @see #APPLY_TRANSLATE
     * @see #APPLY_SCALE
     * @see #APPLY_SHEAR
     */
    trbnsient int stbte;

    /**
     * This field cbches the current trbnsformbtion type of the mbtrix.
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_FLIP
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @see #TYPE_UNKNOWN
     * @see #getType
     */
    privbte trbnsient int type;

    privbte AffineTrbnsform(double m00, double m10,
                            double m01, double m11,
                            double m02, double m12,
                            int stbte) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        this.stbte = stbte;
        this.type = TYPE_UNKNOWN;
    }

    /**
     * Constructs b new <code>AffineTrbnsform</code> representing the
     * Identity trbnsformbtion.
     * @since 1.2
     */
    public AffineTrbnsform() {
        m00 = m11 = 1.0;
        // m01 = m10 = m02 = m12 = 0.0;         /* Not needed. */
        // stbte = APPLY_IDENTITY;              /* Not needed. */
        // type = TYPE_IDENTITY;                /* Not needed. */
    }

    /**
     * Constructs b new <code>AffineTrbnsform</code> thbt is b copy of
     * the specified <code>AffineTrbnsform</code> object.
     * @pbrbm Tx the <code>AffineTrbnsform</code> object to copy
     * @since 1.2
     */
    public AffineTrbnsform(AffineTrbnsform Tx) {
        this.m00 = Tx.m00;
        this.m10 = Tx.m10;
        this.m01 = Tx.m01;
        this.m11 = Tx.m11;
        this.m02 = Tx.m02;
        this.m12 = Tx.m12;
        this.stbte = Tx.stbte;
        this.type = Tx.type;
    }

    /**
     * Constructs b new <code>AffineTrbnsform</code> from 6 flobting point
     * vblues representing the 6 specifibble entries of the 3x3
     * trbnsformbtion mbtrix.
     *
     * @pbrbm m00 the X coordinbte scbling element of the 3x3 mbtrix
     * @pbrbm m10 the Y coordinbte shebring element of the 3x3 mbtrix
     * @pbrbm m01 the X coordinbte shebring element of the 3x3 mbtrix
     * @pbrbm m11 the Y coordinbte scbling element of the 3x3 mbtrix
     * @pbrbm m02 the X coordinbte trbnslbtion element of the 3x3 mbtrix
     * @pbrbm m12 the Y coordinbte trbnslbtion element of the 3x3 mbtrix
     * @since 1.2
     */
    @ConstructorProperties({ "scbleX", "shebrY", "shebrX", "scbleY", "trbnslbteX", "trbnslbteY" })
    public AffineTrbnsform(flobt m00, flobt m10,
                           flobt m01, flobt m11,
                           flobt m02, flobt m12) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        updbteStbte();
    }

    /**
     * Constructs b new <code>AffineTrbnsform</code> from bn brrby of
     * flobting point vblues representing either the 4 non-trbnslbtion
     * entries or the 6 specifibble entries of the 3x3 trbnsformbtion
     * mbtrix.  The vblues bre retrieved from the brrby bs
     * {&nbsp;m00&nbsp;m10&nbsp;m01&nbsp;m11&nbsp;[m02&nbsp;m12]}.
     * @pbrbm flbtmbtrix the flobt brrby contbining the vblues to be set
     * in the new <code>AffineTrbnsform</code> object. The length of the
     * brrby is bssumed to be bt lebst 4. If the length of the brrby is
     * less thbn 6, only the first 4 vblues bre tbken. If the length of
     * the brrby is grebter thbn 6, the first 6 vblues bre tbken.
     * @since 1.2
     */
    public AffineTrbnsform(flobt[] flbtmbtrix) {
        m00 = flbtmbtrix[0];
        m10 = flbtmbtrix[1];
        m01 = flbtmbtrix[2];
        m11 = flbtmbtrix[3];
        if (flbtmbtrix.length > 5) {
            m02 = flbtmbtrix[4];
            m12 = flbtmbtrix[5];
        }
        updbteStbte();
    }

    /**
     * Constructs b new <code>AffineTrbnsform</code> from 6 double
     * precision vblues representing the 6 specifibble entries of the 3x3
     * trbnsformbtion mbtrix.
     *
     * @pbrbm m00 the X coordinbte scbling element of the 3x3 mbtrix
     * @pbrbm m10 the Y coordinbte shebring element of the 3x3 mbtrix
     * @pbrbm m01 the X coordinbte shebring element of the 3x3 mbtrix
     * @pbrbm m11 the Y coordinbte scbling element of the 3x3 mbtrix
     * @pbrbm m02 the X coordinbte trbnslbtion element of the 3x3 mbtrix
     * @pbrbm m12 the Y coordinbte trbnslbtion element of the 3x3 mbtrix
     * @since 1.2
     */
    public AffineTrbnsform(double m00, double m10,
                           double m01, double m11,
                           double m02, double m12) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        updbteStbte();
    }

    /**
     * Constructs b new <code>AffineTrbnsform</code> from bn brrby of
     * double precision vblues representing either the 4 non-trbnslbtion
     * entries or the 6 specifibble entries of the 3x3 trbnsformbtion
     * mbtrix. The vblues bre retrieved from the brrby bs
     * {&nbsp;m00&nbsp;m10&nbsp;m01&nbsp;m11&nbsp;[m02&nbsp;m12]}.
     * @pbrbm flbtmbtrix the double brrby contbining the vblues to be set
     * in the new <code>AffineTrbnsform</code> object. The length of the
     * brrby is bssumed to be bt lebst 4. If the length of the brrby is
     * less thbn 6, only the first 4 vblues bre tbken. If the length of
     * the brrby is grebter thbn 6, the first 6 vblues bre tbken.
     * @since 1.2
     */
    public AffineTrbnsform(double[] flbtmbtrix) {
        m00 = flbtmbtrix[0];
        m10 = flbtmbtrix[1];
        m01 = flbtmbtrix[2];
        m11 = flbtmbtrix[3];
        if (flbtmbtrix.length > 5) {
            m02 = flbtmbtrix[4];
            m12 = flbtmbtrix[5];
        }
        updbteStbte();
    }

    /**
     * Returns b trbnsform representing b trbnslbtion trbnsformbtion.
     * The mbtrix representing the returned trbnsform is:
     * <pre>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm tx the distbnce by which coordinbtes bre trbnslbted in the
     * X bxis direction
     * @pbrbm ty the distbnce by which coordinbtes bre trbnslbted in the
     * Y bxis direction
     * @return bn <code>AffineTrbnsform</code> object thbt represents b
     *  trbnslbtion trbnsformbtion, crebted with the specified vector.
     * @since 1.2
     */
    public stbtic AffineTrbnsform getTrbnslbteInstbnce(double tx, double ty) {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToTrbnslbtion(tx, ty);
        return Tx;
    }

    /**
     * Returns b trbnsform representing b rotbtion trbnsformbtion.
     * The mbtrix representing the returned trbnsform is:
     * <pre>
     *          [   cos(thetb)    -sin(thetb)    0   ]
     *          [   sin(thetb)     cos(thetb)    0   ]
     *          [       0              0         1   ]
     * </pre>
     * Rotbting by b positive bngle thetb rotbtes points on the positive
     * X bxis towbrd the positive Y bxis.
     * Note blso the discussion of
     * <b href="#qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b>
     * bbove.
     * @pbrbm thetb the bngle of rotbtion mebsured in rbdibns
     * @return bn <code>AffineTrbnsform</code> object thbt is b rotbtion
     *  trbnsformbtion, crebted with the specified bngle of rotbtion.
     * @since 1.2
     */
    public stbtic AffineTrbnsform getRotbteInstbnce(double thetb) {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToRotbtion(thetb);
        return Tx;
    }

    /**
     * Returns b trbnsform thbt rotbtes coordinbtes bround bn bnchor point.
     * This operbtion is equivblent to trbnslbting the coordinbtes so
     * thbt the bnchor point is bt the origin (S1), then rotbting them
     * bbout the new origin (S2), bnd finblly trbnslbting so thbt the
     * intermedibte origin is restored to the coordinbtes of the originbl
     * bnchor point (S3).
     * <p>
     * This operbtion is equivblent to the following sequence of cblls:
     * <pre>
     *     AffineTrbnsform Tx = new AffineTrbnsform();
     *     Tx.trbnslbte(bnchorx, bnchory);    // S3: finbl trbnslbtion
     *     Tx.rotbte(thetb);                  // S2: rotbte bround bnchor
     *     Tx.trbnslbte(-bnchorx, -bnchory);  // S1: trbnslbte bnchor to origin
     * </pre>
     * The mbtrix representing the returned trbnsform is:
     * <pre>
     *          [   cos(thetb)    -sin(thetb)    x-x*cos+y*sin  ]
     *          [   sin(thetb)     cos(thetb)    y-x*sin-y*cos  ]
     *          [       0              0               1        ]
     * </pre>
     * Rotbting by b positive bngle thetb rotbtes points on the positive
     * X bxis towbrd the positive Y bxis.
     * Note blso the discussion of
     * <b href="#qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b>
     * bbove.
     *
     * @pbrbm thetb the bngle of rotbtion mebsured in rbdibns
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @return bn <code>AffineTrbnsform</code> object thbt rotbtes
     *  coordinbtes bround the specified point by the specified bngle of
     *  rotbtion.
     * @since 1.2
     */
    public stbtic AffineTrbnsform getRotbteInstbnce(double thetb,
                                                    double bnchorx,
                                                    double bnchory)
    {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToRotbtion(thetb, bnchorx, bnchory);
        return Tx;
    }

    /**
     * Returns b trbnsform thbt rotbtes coordinbtes bccording to
     * b rotbtion vector.
     * All coordinbtes rotbte bbout the origin by the sbme bmount.
     * The bmount of rotbtion is such thbt coordinbtes blong the former
     * positive X bxis will subsequently blign with the vector pointing
     * from the origin to the specified vector coordinbtes.
     * If both <code>vecx</code> bnd <code>vecy</code> bre 0.0,
     * bn identity trbnsform is returned.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     AffineTrbnsform.getRotbteInstbnce(Mbth.btbn2(vecy, vecx));
     * </pre>
     *
     * @pbrbm vecx the X coordinbte of the rotbtion vector
     * @pbrbm vecy the Y coordinbte of the rotbtion vector
     * @return bn <code>AffineTrbnsform</code> object thbt rotbtes
     *  coordinbtes bccording to the specified rotbtion vector.
     * @since 1.6
     */
    public stbtic AffineTrbnsform getRotbteInstbnce(double vecx, double vecy) {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToRotbtion(vecx, vecy);
        return Tx;
    }

    /**
     * Returns b trbnsform thbt rotbtes coordinbtes bround bn bnchor
     * point bccording to b rotbtion vector.
     * All coordinbtes rotbte bbout the specified bnchor coordinbtes
     * by the sbme bmount.
     * The bmount of rotbtion is such thbt coordinbtes blong the former
     * positive X bxis will subsequently blign with the vector pointing
     * from the origin to the specified vector coordinbtes.
     * If both <code>vecx</code> bnd <code>vecy</code> bre 0.0,
     * bn identity trbnsform is returned.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     AffineTrbnsform.getRotbteInstbnce(Mbth.btbn2(vecy, vecx),
     *                                       bnchorx, bnchory);
     * </pre>
     *
     * @pbrbm vecx the X coordinbte of the rotbtion vector
     * @pbrbm vecy the Y coordinbte of the rotbtion vector
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @return bn <code>AffineTrbnsform</code> object thbt rotbtes
     *  coordinbtes bround the specified point bccording to the
     *  specified rotbtion vector.
     * @since 1.6
     */
    public stbtic AffineTrbnsform getRotbteInstbnce(double vecx,
                                                    double vecy,
                                                    double bnchorx,
                                                    double bnchory)
    {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToRotbtion(vecx, vecy, bnchorx, bnchory);
        return Tx;
    }

    /**
     * Returns b trbnsform thbt rotbtes coordinbtes by the specified
     * number of qubdrbnts.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     AffineTrbnsform.getRotbteInstbnce(numqubdrbnts * Mbth.PI / 2.0);
     * </pre>
     * Rotbting by b positive number of qubdrbnts rotbtes points on
     * the positive X bxis towbrd the positive Y bxis.
     * @pbrbm numqubdrbnts the number of 90 degree brcs to rotbte by
     * @return bn <code>AffineTrbnsform</code> object thbt rotbtes
     *  coordinbtes by the specified number of qubdrbnts.
     * @since 1.6
     */
    public stbtic AffineTrbnsform getQubdrbntRotbteInstbnce(int numqubdrbnts) {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToQubdrbntRotbtion(numqubdrbnts);
        return Tx;
    }

    /**
     * Returns b trbnsform thbt rotbtes coordinbtes by the specified
     * number of qubdrbnts bround the specified bnchor point.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     AffineTrbnsform.getRotbteInstbnce(numqubdrbnts * Mbth.PI / 2.0,
     *                                       bnchorx, bnchory);
     * </pre>
     * Rotbting by b positive number of qubdrbnts rotbtes points on
     * the positive X bxis towbrd the positive Y bxis.
     *
     * @pbrbm numqubdrbnts the number of 90 degree brcs to rotbte by
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @return bn <code>AffineTrbnsform</code> object thbt rotbtes
     *  coordinbtes by the specified number of qubdrbnts bround the
     *  specified bnchor point.
     * @since 1.6
     */
    public stbtic AffineTrbnsform getQubdrbntRotbteInstbnce(int numqubdrbnts,
                                                            double bnchorx,
                                                            double bnchory)
    {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToQubdrbntRotbtion(numqubdrbnts, bnchorx, bnchory);
        return Tx;
    }

    /**
     * Returns b trbnsform representing b scbling trbnsformbtion.
     * The mbtrix representing the returned trbnsform is:
     * <pre>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm sx the fbctor by which coordinbtes bre scbled blong the
     * X bxis direction
     * @pbrbm sy the fbctor by which coordinbtes bre scbled blong the
     * Y bxis direction
     * @return bn <code>AffineTrbnsform</code> object thbt scbles
     *  coordinbtes by the specified fbctors.
     * @since 1.2
     */
    public stbtic AffineTrbnsform getScbleInstbnce(double sx, double sy) {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToScble(sx, sy);
        return Tx;
    }

    /**
     * Returns b trbnsform representing b shebring trbnsformbtion.
     * The mbtrix representing the returned trbnsform is:
     * <pre>
     *          [   1   shx   0   ]
     *          [  shy   1    0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm shx the multiplier by which coordinbtes bre shifted in the
     * direction of the positive X bxis bs b fbctor of their Y coordinbte
     * @pbrbm shy the multiplier by which coordinbtes bre shifted in the
     * direction of the positive Y bxis bs b fbctor of their X coordinbte
     * @return bn <code>AffineTrbnsform</code> object thbt shebrs
     *  coordinbtes by the specified multipliers.
     * @since 1.2
     */
    public stbtic AffineTrbnsform getShebrInstbnce(double shx, double shy) {
        AffineTrbnsform Tx = new AffineTrbnsform();
        Tx.setToShebr(shx, shy);
        return Tx;
    }

    /**
     * Retrieves the flbg bits describing the conversion properties of
     * this trbnsform.
     * The return vblue is either one of the constbnts TYPE_IDENTITY
     * or TYPE_GENERAL_TRANSFORM, or b combinbtion of the
     * bppropribte flbg bits.
     * A vblid combinbtion of flbg bits is bn exclusive OR operbtion
     * thbt cbn combine
     * the TYPE_TRANSLATION flbg bit
     * in bddition to either of the
     * TYPE_UNIFORM_SCALE or TYPE_GENERAL_SCALE flbg bits
     * bs well bs either of the
     * TYPE_QUADRANT_ROTATION or TYPE_GENERAL_ROTATION flbg bits.
     * @return the OR combinbtion of bny of the indicbted flbgs thbt
     * bpply to this trbnsform
     * @see #TYPE_IDENTITY
     * @see #TYPE_TRANSLATION
     * @see #TYPE_UNIFORM_SCALE
     * @see #TYPE_GENERAL_SCALE
     * @see #TYPE_QUADRANT_ROTATION
     * @see #TYPE_GENERAL_ROTATION
     * @see #TYPE_GENERAL_TRANSFORM
     * @since 1.2
     */
    public int getType() {
        if (type == TYPE_UNKNOWN) {
            cblculbteType();
        }
        return type;
    }

    /**
     * This is the utility function to cblculbte the flbg bits when
     * they hbve not been cbched.
     * @see #getType
     */
    @SuppressWbrnings("fbllthrough")
    privbte void cblculbteType() {
        int ret = TYPE_IDENTITY;
        boolebn sgn0, sgn1;
        double M0, M1, M2, M3;
        updbteStbte();
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            ret = TYPE_TRANSLATION;
            /* NOBREAK */
        cbse (APPLY_SHEAR | APPLY_SCALE):
            if ((M0 = m00) * (M2 = m01) + (M3 = m10) * (M1 = m11) != 0) {
                // Trbnsformed unit vectors bre not perpendiculbr...
                this.type = TYPE_GENERAL_TRANSFORM;
                return;
            }
            sgn0 = (M0 >= 0.0);
            sgn1 = (M1 >= 0.0);
            if (sgn0 == sgn1) {
                // sgn(M0) == sgn(M1) therefore sgn(M2) == -sgn(M3)
                // This is the "unflipped" (right-hbnded) stbte
                if (M0 != M1 || M2 != -M3) {
                    ret |= (TYPE_GENERAL_ROTATION | TYPE_GENERAL_SCALE);
                } else if (M0 * M1 - M2 * M3 != 1.0) {
                    ret |= (TYPE_GENERAL_ROTATION | TYPE_UNIFORM_SCALE);
                } else {
                    ret |= TYPE_GENERAL_ROTATION;
                }
            } else {
                // sgn(M0) == -sgn(M1) therefore sgn(M2) == sgn(M3)
                // This is the "flipped" (left-hbnded) stbte
                if (M0 != -M1 || M2 != M3) {
                    ret |= (TYPE_GENERAL_ROTATION |
                            TYPE_FLIP |
                            TYPE_GENERAL_SCALE);
                } else if (M0 * M1 - M2 * M3 != 1.0) {
                    ret |= (TYPE_GENERAL_ROTATION |
                            TYPE_FLIP |
                            TYPE_UNIFORM_SCALE);
                } else {
                    ret |= (TYPE_GENERAL_ROTATION | TYPE_FLIP);
                }
            }
            brebk;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            ret = TYPE_TRANSLATION;
            /* NOBREAK */
        cbse (APPLY_SHEAR):
            sgn0 = ((M0 = m01) >= 0.0);
            sgn1 = ((M1 = m10) >= 0.0);
            if (sgn0 != sgn1) {
                // Different signs - simple 90 degree rotbtion
                if (M0 != -M1) {
                    ret |= (TYPE_QUADRANT_ROTATION | TYPE_GENERAL_SCALE);
                } else if (M0 != 1.0 && M0 != -1.0) {
                    ret |= (TYPE_QUADRANT_ROTATION | TYPE_UNIFORM_SCALE);
                } else {
                    ret |= TYPE_QUADRANT_ROTATION;
                }
            } else {
                // Sbme signs - 90 degree rotbtion plus bn bxis flip too
                if (M0 == M1) {
                    ret |= (TYPE_QUADRANT_ROTATION |
                            TYPE_FLIP |
                            TYPE_UNIFORM_SCALE);
                } else {
                    ret |= (TYPE_QUADRANT_ROTATION |
                            TYPE_FLIP |
                            TYPE_GENERAL_SCALE);
                }
            }
            brebk;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            ret = TYPE_TRANSLATION;
            /* NOBREAK */
        cbse (APPLY_SCALE):
            sgn0 = ((M0 = m00) >= 0.0);
            sgn1 = ((M1 = m11) >= 0.0);
            if (sgn0 == sgn1) {
                if (sgn0) {
                    // Both scbling fbctors non-negbtive - simple scble
                    // Note: APPLY_SCALE implies M0, M1 bre not both 1
                    if (M0 == M1) {
                        ret |= TYPE_UNIFORM_SCALE;
                    } else {
                        ret |= TYPE_GENERAL_SCALE;
                    }
                } else {
                    // Both scbling fbctors negbtive - 180 degree rotbtion
                    if (M0 != M1) {
                        ret |= (TYPE_QUADRANT_ROTATION | TYPE_GENERAL_SCALE);
                    } else if (M0 != -1.0) {
                        ret |= (TYPE_QUADRANT_ROTATION | TYPE_UNIFORM_SCALE);
                    } else {
                        ret |= TYPE_QUADRANT_ROTATION;
                    }
                }
            } else {
                // Scbling fbctor signs different - flip bbout some bxis
                if (M0 == -M1) {
                    if (M0 == 1.0 || M0 == -1.0) {
                        ret |= TYPE_FLIP;
                    } else {
                        ret |= (TYPE_FLIP | TYPE_UNIFORM_SCALE);
                    }
                } else {
                    ret |= (TYPE_FLIP | TYPE_GENERAL_SCALE);
                }
            }
            brebk;
        cbse (APPLY_TRANSLATE):
            ret = TYPE_TRANSLATION;
            brebk;
        cbse (APPLY_IDENTITY):
            brebk;
        }
        this.type = ret;
    }

    /**
     * Returns the determinbnt of the mbtrix representbtion of the trbnsform.
     * The determinbnt is useful both to determine if the trbnsform cbn
     * be inverted bnd to get b single vblue representing the
     * combined X bnd Y scbling of the trbnsform.
     * <p>
     * If the determinbnt is non-zero, then this trbnsform is
     * invertible bnd the vbrious methods thbt depend on the inverse
     * trbnsform do not need to throw b
     * {@link NoninvertibleTrbnsformException}.
     * If the determinbnt is zero then this trbnsform cbn not be
     * inverted since the trbnsform mbps bll input coordinbtes onto
     * b line or b point.
     * If the determinbnt is nebr enough to zero then inverse trbnsform
     * operbtions might not cbrry enough precision to produce mebningful
     * results.
     * <p>
     * If this trbnsform represents b uniform scble, bs indicbted by
     * the <code>getType</code> method then the determinbnt blso
     * represents the squbre of the uniform scble fbctor by which bll of
     * the points bre expbnded from or contrbcted towbrds the origin.
     * If this trbnsform represents b non-uniform scble or more generbl
     * trbnsform then the determinbnt is not likely to represent b
     * vblue useful for bny purpose other thbn determining if inverse
     * trbnsforms bre possible.
     * <p>
     * Mbthembticblly, the determinbnt is cblculbted using the formulb:
     * <pre>
     *          |  m00  m01  m02  |
     *          |  m10  m11  m12  |  =  m00 * m11 - m01 * m10
     *          |   0    0    1   |
     * </pre>
     *
     * @return the determinbnt of the mbtrix used to trbnsform the
     * coordinbtes.
     * @see #getType
     * @see #crebteInverse
     * @see #inverseTrbnsform
     * @see #TYPE_UNIFORM_SCALE
     * @since 1.2
     */
    @SuppressWbrnings("fbllthrough")
    public double getDeterminbnt() {
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR | APPLY_SCALE):
            return m00 * m11 - m01 * m10;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR):
            return -(m01 * m10);
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SCALE):
            return m00 * m11;
        cbse (APPLY_TRANSLATE):
        cbse (APPLY_IDENTITY):
            return 1.0;
        }
    }

    /**
     * Mbnublly recblculbtes the stbte of the trbnsform when the mbtrix
     * chbnges too much to predict the effects on the stbte.
     * The following tbble specifies whbt the vbrious settings of the
     * stbte field sby bbout the vblues of the corresponding mbtrix
     * element fields.
     * Note thbt the rules governing the SCALE fields bre slightly
     * different depending on whether the SHEAR flbg is blso set.
     * <pre>
     *                     SCALE            SHEAR          TRANSLATE
     *                    m00/m11          m01/m10          m02/m12
     *
     * IDENTITY             1.0              0.0              0.0
     * TRANSLATE (TR)       1.0              0.0          not both 0.0
     * SCALE (SC)       not both 1.0         0.0              0.0
     * TR | SC          not both 1.0         0.0          not both 0.0
     * SHEAR (SH)           0.0          not both 0.0         0.0
     * TR | SH              0.0          not both 0.0     not both 0.0
     * SC | SH          not both 0.0     not both 0.0         0.0
     * TR | SC | SH     not both 0.0     not both 0.0     not both 0.0
     * </pre>
     */
    void updbteStbte() {
        if (m01 == 0.0 && m10 == 0.0) {
            if (m00 == 1.0 && m11 == 1.0) {
                if (m02 == 0.0 && m12 == 0.0) {
                    stbte = APPLY_IDENTITY;
                    type = TYPE_IDENTITY;
                } else {
                    stbte = APPLY_TRANSLATE;
                    type = TYPE_TRANSLATION;
                }
            } else {
                if (m02 == 0.0 && m12 == 0.0) {
                    stbte = APPLY_SCALE;
                    type = TYPE_UNKNOWN;
                } else {
                    stbte = (APPLY_SCALE | APPLY_TRANSLATE);
                    type = TYPE_UNKNOWN;
                }
            }
        } else {
            if (m00 == 0.0 && m11 == 0.0) {
                if (m02 == 0.0 && m12 == 0.0) {
                    stbte = APPLY_SHEAR;
                    type = TYPE_UNKNOWN;
                } else {
                    stbte = (APPLY_SHEAR | APPLY_TRANSLATE);
                    type = TYPE_UNKNOWN;
                }
            } else {
                if (m02 == 0.0 && m12 == 0.0) {
                    stbte = (APPLY_SHEAR | APPLY_SCALE);
                    type = TYPE_UNKNOWN;
                } else {
                    stbte = (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE);
                    type = TYPE_UNKNOWN;
                }
            }
        }
    }

    /*
     * Convenience method used internblly to throw exceptions when
     * b cbse wbs forgotten in b switch stbtement.
     */
    privbte void stbteError() {
        throw new InternblError("missing cbse in trbnsform stbte switch");
    }

    /**
     * Retrieves the 6 specifibble vblues in the 3x3 bffine trbnsformbtion
     * mbtrix bnd plbces them into bn brrby of double precisions vblues.
     * The vblues bre stored in the brrby bs
     * {&nbsp;m00&nbsp;m10&nbsp;m01&nbsp;m11&nbsp;m02&nbsp;m12&nbsp;}.
     * An brrby of 4 doubles cbn blso be specified, in which cbse only the
     * first four elements representing the non-trbnsform
     * pbrts of the brrby bre retrieved bnd the vblues bre stored into
     * the brrby bs {&nbsp;m00&nbsp;m10&nbsp;m01&nbsp;m11&nbsp;}
     * @pbrbm flbtmbtrix the double brrby used to store the returned
     * vblues.
     * @see #getScbleX
     * @see #getScbleY
     * @see #getShebrX
     * @see #getShebrY
     * @see #getTrbnslbteX
     * @see #getTrbnslbteY
     * @since 1.2
     */
    public void getMbtrix(double[] flbtmbtrix) {
        flbtmbtrix[0] = m00;
        flbtmbtrix[1] = m10;
        flbtmbtrix[2] = m01;
        flbtmbtrix[3] = m11;
        if (flbtmbtrix.length > 5) {
            flbtmbtrix[4] = m02;
            flbtmbtrix[5] = m12;
        }
    }

    /**
     * Returns the X coordinbte scbling element (m00) of the 3x3
     * bffine trbnsformbtion mbtrix.
     * @return b double vblue thbt is the X coordinbte of the scbling
     *  element of the bffine trbnsformbtion mbtrix.
     * @see #getMbtrix
     * @since 1.2
     */
    public double getScbleX() {
        return m00;
    }

    /**
     * Returns the Y coordinbte scbling element (m11) of the 3x3
     * bffine trbnsformbtion mbtrix.
     * @return b double vblue thbt is the Y coordinbte of the scbling
     *  element of the bffine trbnsformbtion mbtrix.
     * @see #getMbtrix
     * @since 1.2
     */
    public double getScbleY() {
        return m11;
    }

    /**
     * Returns the X coordinbte shebring element (m01) of the 3x3
     * bffine trbnsformbtion mbtrix.
     * @return b double vblue thbt is the X coordinbte of the shebring
     *  element of the bffine trbnsformbtion mbtrix.
     * @see #getMbtrix
     * @since 1.2
     */
    public double getShebrX() {
        return m01;
    }

    /**
     * Returns the Y coordinbte shebring element (m10) of the 3x3
     * bffine trbnsformbtion mbtrix.
     * @return b double vblue thbt is the Y coordinbte of the shebring
     *  element of the bffine trbnsformbtion mbtrix.
     * @see #getMbtrix
     * @since 1.2
     */
    public double getShebrY() {
        return m10;
    }

    /**
     * Returns the X coordinbte of the trbnslbtion element (m02) of the
     * 3x3 bffine trbnsformbtion mbtrix.
     * @return b double vblue thbt is the X coordinbte of the trbnslbtion
     *  element of the bffine trbnsformbtion mbtrix.
     * @see #getMbtrix
     * @since 1.2
     */
    public double getTrbnslbteX() {
        return m02;
    }

    /**
     * Returns the Y coordinbte of the trbnslbtion element (m12) of the
     * 3x3 bffine trbnsformbtion mbtrix.
     * @return b double vblue thbt is the Y coordinbte of the trbnslbtion
     *  element of the bffine trbnsformbtion mbtrix.
     * @see #getMbtrix
     * @since 1.2
     */
    public double getTrbnslbteY() {
        return m12;
    }

    /**
     * Concbtenbtes this trbnsform with b trbnslbtion trbnsformbtion.
     * This is equivblent to cblling concbtenbte(T), where T is bn
     * <code>AffineTrbnsform</code> represented by the following mbtrix:
     * <pre>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm tx the distbnce by which coordinbtes bre trbnslbted in the
     * X bxis direction
     * @pbrbm ty the distbnce by which coordinbtes bre trbnslbted in the
     * Y bxis direction
     * @since 1.2
     */
    public void trbnslbte(double tx, double ty) {
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            m02 = tx * m00 + ty * m01 + m02;
            m12 = tx * m10 + ty * m11 + m12;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_SHEAR | APPLY_SCALE;
                if (type != TYPE_UNKNOWN) {
                    type -= TYPE_TRANSLATION;
                }
            }
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            m02 = tx * m00 + ty * m01;
            m12 = tx * m10 + ty * m11;
            if (m02 != 0.0 || m12 != 0.0) {
                stbte = APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE;
                type |= TYPE_TRANSLATION;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            m02 = ty * m01 + m02;
            m12 = tx * m10 + m12;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_SHEAR;
                if (type != TYPE_UNKNOWN) {
                    type -= TYPE_TRANSLATION;
                }
            }
            return;
        cbse (APPLY_SHEAR):
            m02 = ty * m01;
            m12 = tx * m10;
            if (m02 != 0.0 || m12 != 0.0) {
                stbte = APPLY_SHEAR | APPLY_TRANSLATE;
                type |= TYPE_TRANSLATION;
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            m02 = tx * m00 + m02;
            m12 = ty * m11 + m12;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_SCALE;
                if (type != TYPE_UNKNOWN) {
                    type -= TYPE_TRANSLATION;
                }
            }
            return;
        cbse (APPLY_SCALE):
            m02 = tx * m00;
            m12 = ty * m11;
            if (m02 != 0.0 || m12 != 0.0) {
                stbte = APPLY_SCALE | APPLY_TRANSLATE;
                type |= TYPE_TRANSLATION;
            }
            return;
        cbse (APPLY_TRANSLATE):
            m02 = tx + m02;
            m12 = ty + m12;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_IDENTITY;
                type = TYPE_IDENTITY;
            }
            return;
        cbse (APPLY_IDENTITY):
            m02 = tx;
            m12 = ty;
            if (tx != 0.0 || ty != 0.0) {
                stbte = APPLY_TRANSLATE;
                type = TYPE_TRANSLATION;
            }
            return;
        }
    }

    // Utility methods to optimize rotbte methods.
    // These tbbles trbnslbte the flbgs during predictbble qubdrbnt
    // rotbtions where the shebr bnd scble vblues bre swbpped bnd negbted.
    privbte stbtic finbl int rot90conversion[] = {
        /* IDENTITY => */        APPLY_SHEAR,
        /* TRANSLATE (TR) => */  APPLY_SHEAR | APPLY_TRANSLATE,
        /* SCALE (SC) => */      APPLY_SHEAR,
        /* SC | TR => */         APPLY_SHEAR | APPLY_TRANSLATE,
        /* SHEAR (SH) => */      APPLY_SCALE,
        /* SH | TR => */         APPLY_SCALE | APPLY_TRANSLATE,
        /* SH | SC => */         APPLY_SHEAR | APPLY_SCALE,
        /* SH | SC | TR => */    APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE,
    };
    privbte finbl void rotbte90() {
        double M0 = m00;
        m00 = m01;
        m01 = -M0;
        M0 = m10;
        m10 = m11;
        m11 = -M0;
        int stbte = rot90conversion[this.stbte];
        if ((stbte & (APPLY_SHEAR | APPLY_SCALE)) == APPLY_SCALE &&
            m00 == 1.0 && m11 == 1.0)
        {
            stbte -= APPLY_SCALE;
        }
        this.stbte = stbte;
        type = TYPE_UNKNOWN;
    }
    privbte finbl void rotbte180() {
        m00 = -m00;
        m11 = -m11;
        int stbte = this.stbte;
        if ((stbte & (APPLY_SHEAR)) != 0) {
            // If there wbs b shebr, then this rotbtion hbs no
            // effect on the stbte.
            m01 = -m01;
            m10 = -m10;
        } else {
            // No shebr mebns the SCALE stbte mby toggle when
            // m00 bnd m11 bre negbted.
            if (m00 == 1.0 && m11 == 1.0) {
                this.stbte = stbte & ~APPLY_SCALE;
            } else {
                this.stbte = stbte | APPLY_SCALE;
            }
        }
        type = TYPE_UNKNOWN;
    }
    privbte finbl void rotbte270() {
        double M0 = m00;
        m00 = -m01;
        m01 = M0;
        M0 = m10;
        m10 = -m11;
        m11 = M0;
        int stbte = rot90conversion[this.stbte];
        if ((stbte & (APPLY_SHEAR | APPLY_SCALE)) == APPLY_SCALE &&
            m00 == 1.0 && m11 == 1.0)
        {
            stbte -= APPLY_SCALE;
        }
        this.stbte = stbte;
        type = TYPE_UNKNOWN;
    }

    /**
     * Concbtenbtes this trbnsform with b rotbtion trbnsformbtion.
     * This is equivblent to cblling concbtenbte(R), where R is bn
     * <code>AffineTrbnsform</code> represented by the following mbtrix:
     * <pre>
     *          [   cos(thetb)    -sin(thetb)    0   ]
     *          [   sin(thetb)     cos(thetb)    0   ]
     *          [       0              0         1   ]
     * </pre>
     * Rotbting by b positive bngle thetb rotbtes points on the positive
     * X bxis towbrd the positive Y bxis.
     * Note blso the discussion of
     * <b href="#qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b>
     * bbove.
     * @pbrbm thetb the bngle of rotbtion mebsured in rbdibns
     * @since 1.2
     */
    public void rotbte(double thetb) {
        double sin = Mbth.sin(thetb);
        if (sin == 1.0) {
            rotbte90();
        } else if (sin == -1.0) {
            rotbte270();
        } else {
            double cos = Mbth.cos(thetb);
            if (cos == -1.0) {
                rotbte180();
            } else if (cos != 1.0) {
                double M0, M1;
                M0 = m00;
                M1 = m01;
                m00 =  cos * M0 + sin * M1;
                m01 = -sin * M0 + cos * M1;
                M0 = m10;
                M1 = m11;
                m10 =  cos * M0 + sin * M1;
                m11 = -sin * M0 + cos * M1;
                updbteStbte();
            }
        }
    }

    /**
     * Concbtenbtes this trbnsform with b trbnsform thbt rotbtes
     * coordinbtes bround bn bnchor point.
     * This operbtion is equivblent to trbnslbting the coordinbtes so
     * thbt the bnchor point is bt the origin (S1), then rotbting them
     * bbout the new origin (S2), bnd finblly trbnslbting so thbt the
     * intermedibte origin is restored to the coordinbtes of the originbl
     * bnchor point (S3).
     * <p>
     * This operbtion is equivblent to the following sequence of cblls:
     * <pre>
     *     trbnslbte(bnchorx, bnchory);      // S3: finbl trbnslbtion
     *     rotbte(thetb);                    // S2: rotbte bround bnchor
     *     trbnslbte(-bnchorx, -bnchory);    // S1: trbnslbte bnchor to origin
     * </pre>
     * Rotbting by b positive bngle thetb rotbtes points on the positive
     * X bxis towbrd the positive Y bxis.
     * Note blso the discussion of
     * <b href="#qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b>
     * bbove.
     *
     * @pbrbm thetb the bngle of rotbtion mebsured in rbdibns
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @since 1.2
     */
    public void rotbte(double thetb, double bnchorx, double bnchory) {
        // REMIND: Simple for now - optimize lbter
        trbnslbte(bnchorx, bnchory);
        rotbte(thetb);
        trbnslbte(-bnchorx, -bnchory);
    }

    /**
     * Concbtenbtes this trbnsform with b trbnsform thbt rotbtes
     * coordinbtes bccording to b rotbtion vector.
     * All coordinbtes rotbte bbout the origin by the sbme bmount.
     * The bmount of rotbtion is such thbt coordinbtes blong the former
     * positive X bxis will subsequently blign with the vector pointing
     * from the origin to the specified vector coordinbtes.
     * If both <code>vecx</code> bnd <code>vecy</code> bre 0.0,
     * no bdditionbl rotbtion is bdded to this trbnsform.
     * This operbtion is equivblent to cblling:
     * <pre>
     *          rotbte(Mbth.btbn2(vecy, vecx));
     * </pre>
     *
     * @pbrbm vecx the X coordinbte of the rotbtion vector
     * @pbrbm vecy the Y coordinbte of the rotbtion vector
     * @since 1.6
     */
    public void rotbte(double vecx, double vecy) {
        if (vecy == 0.0) {
            if (vecx < 0.0) {
                rotbte180();
            }
            // If vecx > 0.0 - no rotbtion
            // If vecx == 0.0 - undefined rotbtion - trebt bs no rotbtion
        } else if (vecx == 0.0) {
            if (vecy > 0.0) {
                rotbte90();
            } else {  // vecy must be < 0.0
                rotbte270();
            }
        } else {
            double len = Mbth.sqrt(vecx * vecx + vecy * vecy);
            double sin = vecy / len;
            double cos = vecx / len;
            double M0, M1;
            M0 = m00;
            M1 = m01;
            m00 =  cos * M0 + sin * M1;
            m01 = -sin * M0 + cos * M1;
            M0 = m10;
            M1 = m11;
            m10 =  cos * M0 + sin * M1;
            m11 = -sin * M0 + cos * M1;
            updbteStbte();
        }
    }

    /**
     * Concbtenbtes this trbnsform with b trbnsform thbt rotbtes
     * coordinbtes bround bn bnchor point bccording to b rotbtion
     * vector.
     * All coordinbtes rotbte bbout the specified bnchor coordinbtes
     * by the sbme bmount.
     * The bmount of rotbtion is such thbt coordinbtes blong the former
     * positive X bxis will subsequently blign with the vector pointing
     * from the origin to the specified vector coordinbtes.
     * If both <code>vecx</code> bnd <code>vecy</code> bre 0.0,
     * the trbnsform is not modified in bny wby.
     * This method is equivblent to cblling:
     * <pre>
     *     rotbte(Mbth.btbn2(vecy, vecx), bnchorx, bnchory);
     * </pre>
     *
     * @pbrbm vecx the X coordinbte of the rotbtion vector
     * @pbrbm vecy the Y coordinbte of the rotbtion vector
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @since 1.6
     */
    public void rotbte(double vecx, double vecy,
                       double bnchorx, double bnchory)
    {
        // REMIND: Simple for now - optimize lbter
        trbnslbte(bnchorx, bnchory);
        rotbte(vecx, vecy);
        trbnslbte(-bnchorx, -bnchory);
    }

    /**
     * Concbtenbtes this trbnsform with b trbnsform thbt rotbtes
     * coordinbtes by the specified number of qubdrbnts.
     * This is equivblent to cblling:
     * <pre>
     *     rotbte(numqubdrbnts * Mbth.PI / 2.0);
     * </pre>
     * Rotbting by b positive number of qubdrbnts rotbtes points on
     * the positive X bxis towbrd the positive Y bxis.
     * @pbrbm numqubdrbnts the number of 90 degree brcs to rotbte by
     * @since 1.6
     */
    public void qubdrbntRotbte(int numqubdrbnts) {
        switch (numqubdrbnts & 3) {
        cbse 0:
            brebk;
        cbse 1:
            rotbte90();
            brebk;
        cbse 2:
            rotbte180();
            brebk;
        cbse 3:
            rotbte270();
            brebk;
        }
    }

    /**
     * Concbtenbtes this trbnsform with b trbnsform thbt rotbtes
     * coordinbtes by the specified number of qubdrbnts bround
     * the specified bnchor point.
     * This method is equivblent to cblling:
     * <pre>
     *     rotbte(numqubdrbnts * Mbth.PI / 2.0, bnchorx, bnchory);
     * </pre>
     * Rotbting by b positive number of qubdrbnts rotbtes points on
     * the positive X bxis towbrd the positive Y bxis.
     *
     * @pbrbm numqubdrbnts the number of 90 degree brcs to rotbte by
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @since 1.6
     */
    public void qubdrbntRotbte(int numqubdrbnts,
                               double bnchorx, double bnchory)
    {
        switch (numqubdrbnts & 3) {
        cbse 0:
            return;
        cbse 1:
            m02 += bnchorx * (m00 - m01) + bnchory * (m01 + m00);
            m12 += bnchorx * (m10 - m11) + bnchory * (m11 + m10);
            rotbte90();
            brebk;
        cbse 2:
            m02 += bnchorx * (m00 + m00) + bnchory * (m01 + m01);
            m12 += bnchorx * (m10 + m10) + bnchory * (m11 + m11);
            rotbte180();
            brebk;
        cbse 3:
            m02 += bnchorx * (m00 + m01) + bnchory * (m01 - m00);
            m12 += bnchorx * (m10 + m11) + bnchory * (m11 - m10);
            rotbte270();
            brebk;
        }
        if (m02 == 0.0 && m12 == 0.0) {
            stbte &= ~APPLY_TRANSLATE;
        } else {
            stbte |= APPLY_TRANSLATE;
        }
    }

    /**
     * Concbtenbtes this trbnsform with b scbling trbnsformbtion.
     * This is equivblent to cblling concbtenbte(S), where S is bn
     * <code>AffineTrbnsform</code> represented by the following mbtrix:
     * <pre>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm sx the fbctor by which coordinbtes bre scbled blong the
     * X bxis direction
     * @pbrbm sy the fbctor by which coordinbtes bre scbled blong the
     * Y bxis direction
     * @since 1.2
     */
    @SuppressWbrnings("fbllthrough")
    public void scble(double sx, double sy) {
        int stbte = this.stbte;
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR | APPLY_SCALE):
            m00 *= sx;
            m11 *= sy;
            /* NOBREAK */
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR):
            m01 *= sy;
            m10 *= sx;
            if (m01 == 0 && m10 == 0) {
                stbte &= APPLY_TRANSLATE;
                if (m00 == 1.0 && m11 == 1.0) {
                    this.type = (stbte == APPLY_IDENTITY
                                 ? TYPE_IDENTITY
                                 : TYPE_TRANSLATION);
                } else {
                    stbte |= APPLY_SCALE;
                    this.type = TYPE_UNKNOWN;
                }
                this.stbte = stbte;
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SCALE):
            m00 *= sx;
            m11 *= sy;
            if (m00 == 1.0 && m11 == 1.0) {
                this.stbte = (stbte &= APPLY_TRANSLATE);
                this.type = (stbte == APPLY_IDENTITY
                             ? TYPE_IDENTITY
                             : TYPE_TRANSLATION);
            } else {
                this.type = TYPE_UNKNOWN;
            }
            return;
        cbse (APPLY_TRANSLATE):
        cbse (APPLY_IDENTITY):
            m00 = sx;
            m11 = sy;
            if (sx != 1.0 || sy != 1.0) {
                this.stbte = stbte | APPLY_SCALE;
                this.type = TYPE_UNKNOWN;
            }
            return;
        }
    }

    /**
     * Concbtenbtes this trbnsform with b shebring trbnsformbtion.
     * This is equivblent to cblling concbtenbte(SH), where SH is bn
     * <code>AffineTrbnsform</code> represented by the following mbtrix:
     * <pre>
     *          [   1   shx   0   ]
     *          [  shy   1    0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm shx the multiplier by which coordinbtes bre shifted in the
     * direction of the positive X bxis bs b fbctor of their Y coordinbte
     * @pbrbm shy the multiplier by which coordinbtes bre shifted in the
     * direction of the positive Y bxis bs b fbctor of their X coordinbte
     * @since 1.2
     */
    public void shebr(double shx, double shy) {
        int stbte = this.stbte;
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR | APPLY_SCALE):
            double M0, M1;
            M0 = m00;
            M1 = m01;
            m00 = M0 + M1 * shy;
            m01 = M0 * shx + M1;

            M0 = m10;
            M1 = m11;
            m10 = M0 + M1 * shy;
            m11 = M0 * shx + M1;
            updbteStbte();
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR):
            m00 = m01 * shy;
            m11 = m10 * shx;
            if (m00 != 0.0 || m11 != 0.0) {
                this.stbte = stbte | APPLY_SCALE;
            }
            this.type = TYPE_UNKNOWN;
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SCALE):
            m01 = m00 * shx;
            m10 = m11 * shy;
            if (m01 != 0.0 || m10 != 0.0) {
                this.stbte = stbte | APPLY_SHEAR;
            }
            this.type = TYPE_UNKNOWN;
            return;
        cbse (APPLY_TRANSLATE):
        cbse (APPLY_IDENTITY):
            m01 = shx;
            m10 = shy;
            if (m01 != 0.0 || m10 != 0.0) {
                this.stbte = stbte | APPLY_SCALE | APPLY_SHEAR;
                this.type = TYPE_UNKNOWN;
            }
            return;
        }
    }

    /**
     * Resets this trbnsform to the Identity trbnsform.
     * @since 1.2
     */
    public void setToIdentity() {
        m00 = m11 = 1.0;
        m10 = m01 = m02 = m12 = 0.0;
        stbte = APPLY_IDENTITY;
        type = TYPE_IDENTITY;
    }

    /**
     * Sets this trbnsform to b trbnslbtion trbnsformbtion.
     * The mbtrix representing this trbnsform becomes:
     * <pre>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm tx the distbnce by which coordinbtes bre trbnslbted in the
     * X bxis direction
     * @pbrbm ty the distbnce by which coordinbtes bre trbnslbted in the
     * Y bxis direction
     * @since 1.2
     */
    public void setToTrbnslbtion(double tx, double ty) {
        m00 = 1.0;
        m10 = 0.0;
        m01 = 0.0;
        m11 = 1.0;
        m02 = tx;
        m12 = ty;
        if (tx != 0.0 || ty != 0.0) {
            stbte = APPLY_TRANSLATE;
            type = TYPE_TRANSLATION;
        } else {
            stbte = APPLY_IDENTITY;
            type = TYPE_IDENTITY;
        }
    }

    /**
     * Sets this trbnsform to b rotbtion trbnsformbtion.
     * The mbtrix representing this trbnsform becomes:
     * <pre>
     *          [   cos(thetb)    -sin(thetb)    0   ]
     *          [   sin(thetb)     cos(thetb)    0   ]
     *          [       0              0         1   ]
     * </pre>
     * Rotbting by b positive bngle thetb rotbtes points on the positive
     * X bxis towbrd the positive Y bxis.
     * Note blso the discussion of
     * <b href="#qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b>
     * bbove.
     * @pbrbm thetb the bngle of rotbtion mebsured in rbdibns
     * @since 1.2
     */
    public void setToRotbtion(double thetb) {
        double sin = Mbth.sin(thetb);
        double cos;
        if (sin == 1.0 || sin == -1.0) {
            cos = 0.0;
            stbte = APPLY_SHEAR;
            type = TYPE_QUADRANT_ROTATION;
        } else {
            cos = Mbth.cos(thetb);
            if (cos == -1.0) {
                sin = 0.0;
                stbte = APPLY_SCALE;
                type = TYPE_QUADRANT_ROTATION;
            } else if (cos == 1.0) {
                sin = 0.0;
                stbte = APPLY_IDENTITY;
                type = TYPE_IDENTITY;
            } else {
                stbte = APPLY_SHEAR | APPLY_SCALE;
                type = TYPE_GENERAL_ROTATION;
            }
        }
        m00 =  cos;
        m10 =  sin;
        m01 = -sin;
        m11 =  cos;
        m02 =  0.0;
        m12 =  0.0;
    }

    /**
     * Sets this trbnsform to b trbnslbted rotbtion trbnsformbtion.
     * This operbtion is equivblent to trbnslbting the coordinbtes so
     * thbt the bnchor point is bt the origin (S1), then rotbting them
     * bbout the new origin (S2), bnd finblly trbnslbting so thbt the
     * intermedibte origin is restored to the coordinbtes of the originbl
     * bnchor point (S3).
     * <p>
     * This operbtion is equivblent to the following sequence of cblls:
     * <pre>
     *     setToTrbnslbtion(bnchorx, bnchory); // S3: finbl trbnslbtion
     *     rotbte(thetb);                      // S2: rotbte bround bnchor
     *     trbnslbte(-bnchorx, -bnchory);      // S1: trbnslbte bnchor to origin
     * </pre>
     * The mbtrix representing this trbnsform becomes:
     * <pre>
     *          [   cos(thetb)    -sin(thetb)    x-x*cos+y*sin  ]
     *          [   sin(thetb)     cos(thetb)    y-x*sin-y*cos  ]
     *          [       0              0               1        ]
     * </pre>
     * Rotbting by b positive bngle thetb rotbtes points on the positive
     * X bxis towbrd the positive Y bxis.
     * Note blso the discussion of
     * <b href="#qubdrbntbpproximbtion">Hbndling 90-Degree Rotbtions</b>
     * bbove.
     *
     * @pbrbm thetb the bngle of rotbtion mebsured in rbdibns
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @since 1.2
     */
    public void setToRotbtion(double thetb, double bnchorx, double bnchory) {
        setToRotbtion(thetb);
        double sin = m10;
        double oneMinusCos = 1.0 - m00;
        m02 = bnchorx * oneMinusCos + bnchory * sin;
        m12 = bnchory * oneMinusCos - bnchorx * sin;
        if (m02 != 0.0 || m12 != 0.0) {
            stbte |= APPLY_TRANSLATE;
            type |= TYPE_TRANSLATION;
        }
    }

    /**
     * Sets this trbnsform to b rotbtion trbnsformbtion thbt rotbtes
     * coordinbtes bccording to b rotbtion vector.
     * All coordinbtes rotbte bbout the origin by the sbme bmount.
     * The bmount of rotbtion is such thbt coordinbtes blong the former
     * positive X bxis will subsequently blign with the vector pointing
     * from the origin to the specified vector coordinbtes.
     * If both <code>vecx</code> bnd <code>vecy</code> bre 0.0,
     * the trbnsform is set to bn identity trbnsform.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     setToRotbtion(Mbth.btbn2(vecy, vecx));
     * </pre>
     *
     * @pbrbm vecx the X coordinbte of the rotbtion vector
     * @pbrbm vecy the Y coordinbte of the rotbtion vector
     * @since 1.6
     */
    public void setToRotbtion(double vecx, double vecy) {
        double sin, cos;
        if (vecy == 0) {
            sin = 0.0;
            if (vecx < 0.0) {
                cos = -1.0;
                stbte = APPLY_SCALE;
                type = TYPE_QUADRANT_ROTATION;
            } else {
                cos = 1.0;
                stbte = APPLY_IDENTITY;
                type = TYPE_IDENTITY;
            }
        } else if (vecx == 0) {
            cos = 0.0;
            sin = (vecy > 0.0) ? 1.0 : -1.0;
            stbte = APPLY_SHEAR;
            type = TYPE_QUADRANT_ROTATION;
        } else {
            double len = Mbth.sqrt(vecx * vecx + vecy * vecy);
            cos = vecx / len;
            sin = vecy / len;
            stbte = APPLY_SHEAR | APPLY_SCALE;
            type = TYPE_GENERAL_ROTATION;
        }
        m00 =  cos;
        m10 =  sin;
        m01 = -sin;
        m11 =  cos;
        m02 =  0.0;
        m12 =  0.0;
    }

    /**
     * Sets this trbnsform to b rotbtion trbnsformbtion thbt rotbtes
     * coordinbtes bround bn bnchor point bccording to b rotbtion
     * vector.
     * All coordinbtes rotbte bbout the specified bnchor coordinbtes
     * by the sbme bmount.
     * The bmount of rotbtion is such thbt coordinbtes blong the former
     * positive X bxis will subsequently blign with the vector pointing
     * from the origin to the specified vector coordinbtes.
     * If both <code>vecx</code> bnd <code>vecy</code> bre 0.0,
     * the trbnsform is set to bn identity trbnsform.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     setToTrbnslbtion(Mbth.btbn2(vecy, vecx), bnchorx, bnchory);
     * </pre>
     *
     * @pbrbm vecx the X coordinbte of the rotbtion vector
     * @pbrbm vecy the Y coordinbte of the rotbtion vector
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @since 1.6
     */
    public void setToRotbtion(double vecx, double vecy,
                              double bnchorx, double bnchory)
    {
        setToRotbtion(vecx, vecy);
        double sin = m10;
        double oneMinusCos = 1.0 - m00;
        m02 = bnchorx * oneMinusCos + bnchory * sin;
        m12 = bnchory * oneMinusCos - bnchorx * sin;
        if (m02 != 0.0 || m12 != 0.0) {
            stbte |= APPLY_TRANSLATE;
            type |= TYPE_TRANSLATION;
        }
    }

    /**
     * Sets this trbnsform to b rotbtion trbnsformbtion thbt rotbtes
     * coordinbtes by the specified number of qubdrbnts.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     setToRotbtion(numqubdrbnts * Mbth.PI / 2.0);
     * </pre>
     * Rotbting by b positive number of qubdrbnts rotbtes points on
     * the positive X bxis towbrd the positive Y bxis.
     * @pbrbm numqubdrbnts the number of 90 degree brcs to rotbte by
     * @since 1.6
     */
    public void setToQubdrbntRotbtion(int numqubdrbnts) {
        switch (numqubdrbnts & 3) {
        cbse 0:
            m00 =  1.0;
            m10 =  0.0;
            m01 =  0.0;
            m11 =  1.0;
            m02 =  0.0;
            m12 =  0.0;
            stbte = APPLY_IDENTITY;
            type = TYPE_IDENTITY;
            brebk;
        cbse 1:
            m00 =  0.0;
            m10 =  1.0;
            m01 = -1.0;
            m11 =  0.0;
            m02 =  0.0;
            m12 =  0.0;
            stbte = APPLY_SHEAR;
            type = TYPE_QUADRANT_ROTATION;
            brebk;
        cbse 2:
            m00 = -1.0;
            m10 =  0.0;
            m01 =  0.0;
            m11 = -1.0;
            m02 =  0.0;
            m12 =  0.0;
            stbte = APPLY_SCALE;
            type = TYPE_QUADRANT_ROTATION;
            brebk;
        cbse 3:
            m00 =  0.0;
            m10 = -1.0;
            m01 =  1.0;
            m11 =  0.0;
            m02 =  0.0;
            m12 =  0.0;
            stbte = APPLY_SHEAR;
            type = TYPE_QUADRANT_ROTATION;
            brebk;
        }
    }

    /**
     * Sets this trbnsform to b trbnslbted rotbtion trbnsformbtion
     * thbt rotbtes coordinbtes by the specified number of qubdrbnts
     * bround the specified bnchor point.
     * This operbtion is equivblent to cblling:
     * <pre>
     *     setToRotbtion(numqubdrbnts * Mbth.PI / 2.0, bnchorx, bnchory);
     * </pre>
     * Rotbting by b positive number of qubdrbnts rotbtes points on
     * the positive X bxis towbrd the positive Y bxis.
     *
     * @pbrbm numqubdrbnts the number of 90 degree brcs to rotbte by
     * @pbrbm bnchorx the X coordinbte of the rotbtion bnchor point
     * @pbrbm bnchory the Y coordinbte of the rotbtion bnchor point
     * @since 1.6
     */
    public void setToQubdrbntRotbtion(int numqubdrbnts,
                                      double bnchorx, double bnchory)
    {
        switch (numqubdrbnts & 3) {
        cbse 0:
            m00 =  1.0;
            m10 =  0.0;
            m01 =  0.0;
            m11 =  1.0;
            m02 =  0.0;
            m12 =  0.0;
            stbte = APPLY_IDENTITY;
            type = TYPE_IDENTITY;
            brebk;
        cbse 1:
            m00 =  0.0;
            m10 =  1.0;
            m01 = -1.0;
            m11 =  0.0;
            m02 =  bnchorx + bnchory;
            m12 =  bnchory - bnchorx;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_SHEAR;
                type = TYPE_QUADRANT_ROTATION;
            } else {
                stbte = APPLY_SHEAR | APPLY_TRANSLATE;
                type = TYPE_QUADRANT_ROTATION | TYPE_TRANSLATION;
            }
            brebk;
        cbse 2:
            m00 = -1.0;
            m10 =  0.0;
            m01 =  0.0;
            m11 = -1.0;
            m02 =  bnchorx + bnchorx;
            m12 =  bnchory + bnchory;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_SCALE;
                type = TYPE_QUADRANT_ROTATION;
            } else {
                stbte = APPLY_SCALE | APPLY_TRANSLATE;
                type = TYPE_QUADRANT_ROTATION | TYPE_TRANSLATION;
            }
            brebk;
        cbse 3:
            m00 =  0.0;
            m10 = -1.0;
            m01 =  1.0;
            m11 =  0.0;
            m02 =  bnchorx - bnchory;
            m12 =  bnchory + bnchorx;
            if (m02 == 0.0 && m12 == 0.0) {
                stbte = APPLY_SHEAR;
                type = TYPE_QUADRANT_ROTATION;
            } else {
                stbte = APPLY_SHEAR | APPLY_TRANSLATE;
                type = TYPE_QUADRANT_ROTATION | TYPE_TRANSLATION;
            }
            brebk;
        }
    }

    /**
     * Sets this trbnsform to b scbling trbnsformbtion.
     * The mbtrix representing this trbnsform becomes:
     * <pre>
     *          [   sx   0    0   ]
     *          [   0    sy   0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm sx the fbctor by which coordinbtes bre scbled blong the
     * X bxis direction
     * @pbrbm sy the fbctor by which coordinbtes bre scbled blong the
     * Y bxis direction
     * @since 1.2
     */
    public void setToScble(double sx, double sy) {
        m00 = sx;
        m10 = 0.0;
        m01 = 0.0;
        m11 = sy;
        m02 = 0.0;
        m12 = 0.0;
        if (sx != 1.0 || sy != 1.0) {
            stbte = APPLY_SCALE;
            type = TYPE_UNKNOWN;
        } else {
            stbte = APPLY_IDENTITY;
            type = TYPE_IDENTITY;
        }
    }

    /**
     * Sets this trbnsform to b shebring trbnsformbtion.
     * The mbtrix representing this trbnsform becomes:
     * <pre>
     *          [   1   shx   0   ]
     *          [  shy   1    0   ]
     *          [   0    0    1   ]
     * </pre>
     * @pbrbm shx the multiplier by which coordinbtes bre shifted in the
     * direction of the positive X bxis bs b fbctor of their Y coordinbte
     * @pbrbm shy the multiplier by which coordinbtes bre shifted in the
     * direction of the positive Y bxis bs b fbctor of their X coordinbte
     * @since 1.2
     */
    public void setToShebr(double shx, double shy) {
        m00 = 1.0;
        m01 = shx;
        m10 = shy;
        m11 = 1.0;
        m02 = 0.0;
        m12 = 0.0;
        if (shx != 0.0 || shy != 0.0) {
            stbte = (APPLY_SHEAR | APPLY_SCALE);
            type = TYPE_UNKNOWN;
        } else {
            stbte = APPLY_IDENTITY;
            type = TYPE_IDENTITY;
        }
    }

    /**
     * Sets this trbnsform to b copy of the trbnsform in the specified
     * <code>AffineTrbnsform</code> object.
     * @pbrbm Tx the <code>AffineTrbnsform</code> object from which to
     * copy the trbnsform
     * @since 1.2
     */
    public void setTrbnsform(AffineTrbnsform Tx) {
        this.m00 = Tx.m00;
        this.m10 = Tx.m10;
        this.m01 = Tx.m01;
        this.m11 = Tx.m11;
        this.m02 = Tx.m02;
        this.m12 = Tx.m12;
        this.stbte = Tx.stbte;
        this.type = Tx.type;
    }

    /**
     * Sets this trbnsform to the mbtrix specified by the 6
     * double precision vblues.
     *
     * @pbrbm m00 the X coordinbte scbling element of the 3x3 mbtrix
     * @pbrbm m10 the Y coordinbte shebring element of the 3x3 mbtrix
     * @pbrbm m01 the X coordinbte shebring element of the 3x3 mbtrix
     * @pbrbm m11 the Y coordinbte scbling element of the 3x3 mbtrix
     * @pbrbm m02 the X coordinbte trbnslbtion element of the 3x3 mbtrix
     * @pbrbm m12 the Y coordinbte trbnslbtion element of the 3x3 mbtrix
     * @since 1.2
     */
    public void setTrbnsform(double m00, double m10,
                             double m01, double m11,
                             double m02, double m12) {
        this.m00 = m00;
        this.m10 = m10;
        this.m01 = m01;
        this.m11 = m11;
        this.m02 = m02;
        this.m12 = m12;
        updbteStbte();
    }

    /**
     * Concbtenbtes bn <code>AffineTrbnsform</code> <code>Tx</code> to
     * this <code>AffineTrbnsform</code> Cx in the most commonly useful
     * wby to provide b new user spbce
     * thbt is mbpped to the former user spbce by <code>Tx</code>.
     * Cx is updbted to perform the combined trbnsformbtion.
     * Trbnsforming b point p by the updbted trbnsform Cx' is
     * equivblent to first trbnsforming p by <code>Tx</code> bnd then
     * trbnsforming the result by the originbl trbnsform Cx like this:
     * Cx'(p) = Cx(Tx(p))
     * In mbtrix notbtion, if this trbnsform Cx is
     * represented by the mbtrix [this] bnd <code>Tx</code> is represented
     * by the mbtrix [Tx] then this method does the following:
     * <pre>
     *          [this] = [this] x [Tx]
     * </pre>
     * @pbrbm Tx the <code>AffineTrbnsform</code> object to be
     * concbtenbted with this <code>AffineTrbnsform</code> object.
     * @see #preConcbtenbte
     * @since 1.2
     */
    @SuppressWbrnings("fbllthrough")
    public void concbtenbte(AffineTrbnsform Tx) {
        double M0, M1;
        double T00, T01, T10, T11;
        double T02, T12;
        int mystbte = stbte;
        int txstbte = Tx.stbte;
        switch ((txstbte << HI_SHIFT) | mystbte) {

            /* ---------- Tx == IDENTITY cbses ---------- */
        cbse (HI_IDENTITY | APPLY_IDENTITY):
        cbse (HI_IDENTITY | APPLY_TRANSLATE):
        cbse (HI_IDENTITY | APPLY_SCALE):
        cbse (HI_IDENTITY | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_IDENTITY | APPLY_SHEAR):
        cbse (HI_IDENTITY | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_IDENTITY | APPLY_SHEAR | APPLY_SCALE):
        cbse (HI_IDENTITY | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            return;

            /* ---------- this == IDENTITY cbses ---------- */
        cbse (HI_SHEAR | HI_SCALE | HI_TRANSLATE | APPLY_IDENTITY):
            m01 = Tx.m01;
            m10 = Tx.m10;
            /* NOBREAK */
        cbse (HI_SCALE | HI_TRANSLATE | APPLY_IDENTITY):
            m00 = Tx.m00;
            m11 = Tx.m11;
            /* NOBREAK */
        cbse (HI_TRANSLATE | APPLY_IDENTITY):
            m02 = Tx.m02;
            m12 = Tx.m12;
            stbte = txstbte;
            type = Tx.type;
            return;
        cbse (HI_SHEAR | HI_SCALE | APPLY_IDENTITY):
            m01 = Tx.m01;
            m10 = Tx.m10;
            /* NOBREAK */
        cbse (HI_SCALE | APPLY_IDENTITY):
            m00 = Tx.m00;
            m11 = Tx.m11;
            stbte = txstbte;
            type = Tx.type;
            return;
        cbse (HI_SHEAR | HI_TRANSLATE | APPLY_IDENTITY):
            m02 = Tx.m02;
            m12 = Tx.m12;
            /* NOBREAK */
        cbse (HI_SHEAR | APPLY_IDENTITY):
            m01 = Tx.m01;
            m10 = Tx.m10;
            m00 = m11 = 0.0;
            stbte = txstbte;
            type = Tx.type;
            return;

            /* ---------- Tx == TRANSLATE cbses ---------- */
        cbse (HI_TRANSLATE | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_TRANSLATE | APPLY_SHEAR | APPLY_SCALE):
        cbse (HI_TRANSLATE | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_TRANSLATE | APPLY_SHEAR):
        cbse (HI_TRANSLATE | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_TRANSLATE | APPLY_SCALE):
        cbse (HI_TRANSLATE | APPLY_TRANSLATE):
            trbnslbte(Tx.m02, Tx.m12);
            return;

            /* ---------- Tx == SCALE cbses ---------- */
        cbse (HI_SCALE | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_SHEAR | APPLY_SCALE):
        cbse (HI_SCALE | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_SHEAR):
        cbse (HI_SCALE | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_SCALE):
        cbse (HI_SCALE | APPLY_TRANSLATE):
            scble(Tx.m00, Tx.m11);
            return;

            /* ---------- Tx == SHEAR cbses ---------- */
        cbse (HI_SHEAR | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_SHEAR | APPLY_SCALE):
            T01 = Tx.m01; T10 = Tx.m10;
            M0 = m00;
            m00 = m01 * T10;
            m01 = M0 * T01;
            M0 = m10;
            m10 = m11 * T10;
            m11 = M0 * T01;
            type = TYPE_UNKNOWN;
            return;
        cbse (HI_SHEAR | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_SHEAR):
            m00 = m01 * Tx.m10;
            m01 = 0.0;
            m11 = m10 * Tx.m01;
            m10 = 0.0;
            stbte = mystbte ^ (APPLY_SHEAR | APPLY_SCALE);
            type = TYPE_UNKNOWN;
            return;
        cbse (HI_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_SCALE):
            m01 = m00 * Tx.m01;
            m00 = 0.0;
            m10 = m11 * Tx.m10;
            m11 = 0.0;
            stbte = mystbte ^ (APPLY_SHEAR | APPLY_SCALE);
            type = TYPE_UNKNOWN;
            return;
        cbse (HI_SHEAR | APPLY_TRANSLATE):
            m00 = 0.0;
            m01 = Tx.m01;
            m10 = Tx.m10;
            m11 = 0.0;
            stbte = APPLY_TRANSLATE | APPLY_SHEAR;
            type = TYPE_UNKNOWN;
            return;
        }
        // If Tx hbs more thbn one bttribute, it is not worth optimizing
        // bll of those cbses...
        T00 = Tx.m00; T01 = Tx.m01; T02 = Tx.m02;
        T10 = Tx.m10; T11 = Tx.m11; T12 = Tx.m12;
        switch (mystbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
        cbse (APPLY_SHEAR | APPLY_SCALE):
            stbte = mystbte | txstbte;
            /* NOBREAK */
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M0 = m00;
            M1 = m01;
            m00  = T00 * M0 + T10 * M1;
            m01  = T01 * M0 + T11 * M1;
            m02 += T02 * M0 + T12 * M1;

            M0 = m10;
            M1 = m11;
            m10  = T00 * M0 + T10 * M1;
            m11  = T01 * M0 + T11 * M1;
            m12 += T02 * M0 + T12 * M1;
            type = TYPE_UNKNOWN;
            return;

        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR):
            M0 = m01;
            m00  = T10 * M0;
            m01  = T11 * M0;
            m02 += T12 * M0;

            M0 = m10;
            m10  = T00 * M0;
            m11  = T01 * M0;
            m12 += T02 * M0;
            brebk;

        cbse (APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SCALE):
            M0 = m00;
            m00  = T00 * M0;
            m01  = T01 * M0;
            m02 += T02 * M0;

            M0 = m11;
            m10  = T10 * M0;
            m11  = T11 * M0;
            m12 += T12 * M0;
            brebk;

        cbse (APPLY_TRANSLATE):
            m00  = T00;
            m01  = T01;
            m02 += T02;

            m10  = T10;
            m11  = T11;
            m12 += T12;
            stbte = txstbte | APPLY_TRANSLATE;
            type = TYPE_UNKNOWN;
            return;
        }
        updbteStbte();
    }

    /**
     * Concbtenbtes bn <code>AffineTrbnsform</code> <code>Tx</code> to
     * this <code>AffineTrbnsform</code> Cx
     * in b less commonly used wby such thbt <code>Tx</code> modifies the
     * coordinbte trbnsformbtion relbtive to the bbsolute pixel
     * spbce rbther thbn relbtive to the existing user spbce.
     * Cx is updbted to perform the combined trbnsformbtion.
     * Trbnsforming b point p by the updbted trbnsform Cx' is
     * equivblent to first trbnsforming p by the originbl trbnsform
     * Cx bnd then trbnsforming the result by
     * <code>Tx</code> like this:
     * Cx'(p) = Tx(Cx(p))
     * In mbtrix notbtion, if this trbnsform Cx
     * is represented by the mbtrix [this] bnd <code>Tx</code> is
     * represented by the mbtrix [Tx] then this method does the
     * following:
     * <pre>
     *          [this] = [Tx] x [this]
     * </pre>
     * @pbrbm Tx the <code>AffineTrbnsform</code> object to be
     * concbtenbted with this <code>AffineTrbnsform</code> object.
     * @see #concbtenbte
     * @since 1.2
     */
    @SuppressWbrnings("fbllthrough")
    public void preConcbtenbte(AffineTrbnsform Tx) {
        double M0, M1;
        double T00, T01, T10, T11;
        double T02, T12;
        int mystbte = stbte;
        int txstbte = Tx.stbte;
        switch ((txstbte << HI_SHIFT) | mystbte) {
        cbse (HI_IDENTITY | APPLY_IDENTITY):
        cbse (HI_IDENTITY | APPLY_TRANSLATE):
        cbse (HI_IDENTITY | APPLY_SCALE):
        cbse (HI_IDENTITY | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_IDENTITY | APPLY_SHEAR):
        cbse (HI_IDENTITY | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_IDENTITY | APPLY_SHEAR | APPLY_SCALE):
        cbse (HI_IDENTITY | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            // Tx is IDENTITY...
            return;

        cbse (HI_TRANSLATE | APPLY_IDENTITY):
        cbse (HI_TRANSLATE | APPLY_SCALE):
        cbse (HI_TRANSLATE | APPLY_SHEAR):
        cbse (HI_TRANSLATE | APPLY_SHEAR | APPLY_SCALE):
            // Tx is TRANSLATE, this hbs no TRANSLATE
            m02 = Tx.m02;
            m12 = Tx.m12;
            stbte = mystbte | APPLY_TRANSLATE;
            type |= TYPE_TRANSLATION;
            return;

        cbse (HI_TRANSLATE | APPLY_TRANSLATE):
        cbse (HI_TRANSLATE | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_TRANSLATE | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_TRANSLATE | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            // Tx is TRANSLATE, this hbs one too
            m02 = m02 + Tx.m02;
            m12 = m12 + Tx.m12;
            return;

        cbse (HI_SCALE | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_IDENTITY):
            // Only these two existing stbtes need b new stbte
            stbte = mystbte | APPLY_SCALE;
            /* NOBREAK */
        cbse (HI_SCALE | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_SHEAR | APPLY_SCALE):
        cbse (HI_SCALE | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_SHEAR):
        cbse (HI_SCALE | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SCALE | APPLY_SCALE):
            // Tx is SCALE, this is bnything
            T00 = Tx.m00;
            T11 = Tx.m11;
            if ((mystbte & APPLY_SHEAR) != 0) {
                m01 = m01 * T00;
                m10 = m10 * T11;
                if ((mystbte & APPLY_SCALE) != 0) {
                    m00 = m00 * T00;
                    m11 = m11 * T11;
                }
            } else {
                m00 = m00 * T00;
                m11 = m11 * T11;
            }
            if ((mystbte & APPLY_TRANSLATE) != 0) {
                m02 = m02 * T00;
                m12 = m12 * T11;
            }
            type = TYPE_UNKNOWN;
            return;
        cbse (HI_SHEAR | APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_SHEAR):
            mystbte = mystbte | APPLY_SCALE;
            /* NOBREAK */
        cbse (HI_SHEAR | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_IDENTITY):
        cbse (HI_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_SCALE):
            stbte = mystbte ^ APPLY_SHEAR;
            /* NOBREAK */
        cbse (HI_SHEAR | APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (HI_SHEAR | APPLY_SHEAR | APPLY_SCALE):
            // Tx is SHEAR, this is bnything
            T01 = Tx.m01;
            T10 = Tx.m10;

            M0 = m00;
            m00 = m10 * T01;
            m10 = M0 * T10;

            M0 = m01;
            m01 = m11 * T01;
            m11 = M0 * T10;

            M0 = m02;
            m02 = m12 * T01;
            m12 = M0 * T10;
            type = TYPE_UNKNOWN;
            return;
        }
        // If Tx hbs more thbn one bttribute, it is not worth optimizing
        // bll of those cbses...
        T00 = Tx.m00; T01 = Tx.m01; T02 = Tx.m02;
        T10 = Tx.m10; T11 = Tx.m11; T12 = Tx.m12;
        switch (mystbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M0 = m02;
            M1 = m12;
            T02 += M0 * T00 + M1 * T01;
            T12 += M0 * T10 + M1 * T11;

            /* NOBREAK */
        cbse (APPLY_SHEAR | APPLY_SCALE):
            m02 = T02;
            m12 = T12;

            M0 = m00;
            M1 = m10;
            m00 = M0 * T00 + M1 * T01;
            m10 = M0 * T10 + M1 * T11;

            M0 = m01;
            M1 = m11;
            m01 = M0 * T00 + M1 * T01;
            m11 = M0 * T10 + M1 * T11;
            brebk;

        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M0 = m02;
            M1 = m12;
            T02 += M0 * T00 + M1 * T01;
            T12 += M0 * T10 + M1 * T11;

            /* NOBREAK */
        cbse (APPLY_SHEAR):
            m02 = T02;
            m12 = T12;

            M0 = m10;
            m00 = M0 * T01;
            m10 = M0 * T11;

            M0 = m01;
            m01 = M0 * T00;
            m11 = M0 * T10;
            brebk;

        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M0 = m02;
            M1 = m12;
            T02 += M0 * T00 + M1 * T01;
            T12 += M0 * T10 + M1 * T11;

            /* NOBREAK */
        cbse (APPLY_SCALE):
            m02 = T02;
            m12 = T12;

            M0 = m00;
            m00 = M0 * T00;
            m10 = M0 * T10;

            M0 = m11;
            m01 = M0 * T01;
            m11 = M0 * T11;
            brebk;

        cbse (APPLY_TRANSLATE):
            M0 = m02;
            M1 = m12;
            T02 += M0 * T00 + M1 * T01;
            T12 += M0 * T10 + M1 * T11;

            /* NOBREAK */
        cbse (APPLY_IDENTITY):
            m02 = T02;
            m12 = T12;

            m00 = T00;
            m10 = T10;

            m01 = T01;
            m11 = T11;

            stbte = mystbte | txstbte;
            type = TYPE_UNKNOWN;
            return;
        }
        updbteStbte();
    }

    /**
     * Returns bn <code>AffineTrbnsform</code> object representing the
     * inverse trbnsformbtion.
     * The inverse trbnsform Tx' of this trbnsform Tx
     * mbps coordinbtes trbnsformed by Tx bbck
     * to their originbl coordinbtes.
     * In other words, Tx'(Tx(p)) = p = Tx(Tx'(p)).
     * <p>
     * If this trbnsform mbps bll coordinbtes onto b point or b line
     * then it will not hbve bn inverse, since coordinbtes thbt do
     * not lie on the destinbtion point or line will not hbve bn inverse
     * mbpping.
     * The <code>getDeterminbnt</code> method cbn be used to determine if this
     * trbnsform hbs no inverse, in which cbse bn exception will be
     * thrown if the <code>crebteInverse</code> method is cblled.
     * @return b new <code>AffineTrbnsform</code> object representing the
     * inverse trbnsformbtion.
     * @see #getDeterminbnt
     * @exception NoninvertibleTrbnsformException
     * if the mbtrix cbnnot be inverted.
     * @since 1.2
     */
    public AffineTrbnsform crebteInverse()
        throws NoninvertibleTrbnsformException
    {
        double det;
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return null;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            det = m00 * m11 - m01 * m10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            return new AffineTrbnsform( m11 / det, -m10 / det,
                                       -m01 / det,  m00 / det,
                                       (m01 * m12 - m11 * m02) / det,
                                       (m10 * m02 - m00 * m12) / det,
                                       (APPLY_SHEAR |
                                        APPLY_SCALE |
                                        APPLY_TRANSLATE));
        cbse (APPLY_SHEAR | APPLY_SCALE):
            det = m00 * m11 - m01 * m10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            return new AffineTrbnsform( m11 / det, -m10 / det,
                                       -m01 / det,  m00 / det,
                                        0.0,        0.0,
                                       (APPLY_SHEAR | APPLY_SCALE));
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            if (m01 == 0.0 || m10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            return new AffineTrbnsform( 0.0,        1.0 / m01,
                                        1.0 / m10,  0.0,
                                       -m12 / m10, -m02 / m01,
                                       (APPLY_SHEAR | APPLY_TRANSLATE));
        cbse (APPLY_SHEAR):
            if (m01 == 0.0 || m10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            return new AffineTrbnsform(0.0,       1.0 / m01,
                                       1.0 / m10, 0.0,
                                       0.0,       0.0,
                                       (APPLY_SHEAR));
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            if (m00 == 0.0 || m11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            return new AffineTrbnsform( 1.0 / m00,  0.0,
                                        0.0,        1.0 / m11,
                                       -m02 / m00, -m12 / m11,
                                       (APPLY_SCALE | APPLY_TRANSLATE));
        cbse (APPLY_SCALE):
            if (m00 == 0.0 || m11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            return new AffineTrbnsform(1.0 / m00, 0.0,
                                       0.0,       1.0 / m11,
                                       0.0,       0.0,
                                       (APPLY_SCALE));
        cbse (APPLY_TRANSLATE):
            return new AffineTrbnsform( 1.0,  0.0,
                                        0.0,  1.0,
                                       -m02, -m12,
                                       (APPLY_TRANSLATE));
        cbse (APPLY_IDENTITY):
            return new AffineTrbnsform();
        }

        /* NOTREACHED */
    }

    /**
     * Sets this trbnsform to the inverse of itself.
     * The inverse trbnsform Tx' of this trbnsform Tx
     * mbps coordinbtes trbnsformed by Tx bbck
     * to their originbl coordinbtes.
     * In other words, Tx'(Tx(p)) = p = Tx(Tx'(p)).
     * <p>
     * If this trbnsform mbps bll coordinbtes onto b point or b line
     * then it will not hbve bn inverse, since coordinbtes thbt do
     * not lie on the destinbtion point or line will not hbve bn inverse
     * mbpping.
     * The <code>getDeterminbnt</code> method cbn be used to determine if this
     * trbnsform hbs no inverse, in which cbse bn exception will be
     * thrown if the <code>invert</code> method is cblled.
     * @see #getDeterminbnt
     * @exception NoninvertibleTrbnsformException
     * if the mbtrix cbnnot be inverted.
     * @since 1.6
     */
    public void invert()
        throws NoninvertibleTrbnsformException
    {
        double M00, M01, M02;
        double M10, M11, M12;
        double det;
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M01 = m01; M02 = m02;
            M10 = m10; M11 = m11; M12 = m12;
            det = M00 * M11 - M01 * M10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            m00 =  M11 / det;
            m10 = -M10 / det;
            m01 = -M01 / det;
            m11 =  M00 / det;
            m02 = (M01 * M12 - M11 * M02) / det;
            m12 = (M10 * M02 - M00 * M12) / det;
            brebk;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            det = M00 * M11 - M01 * M10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            m00 =  M11 / det;
            m10 = -M10 / det;
            m01 = -M01 / det;
            m11 =  M00 / det;
            // m02 = 0.0;
            // m12 = 0.0;
            brebk;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M01 = m01; M02 = m02;
            M10 = m10; M12 = m12;
            if (M01 == 0.0 || M10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            // m00 = 0.0;
            m10 = 1.0 / M01;
            m01 = 1.0 / M10;
            // m11 = 0.0;
            m02 = -M12 / M10;
            m12 = -M02 / M01;
            brebk;
        cbse (APPLY_SHEAR):
            M01 = m01;
            M10 = m10;
            if (M01 == 0.0 || M10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            // m00 = 0.0;
            m10 = 1.0 / M01;
            m01 = 1.0 / M10;
            // m11 = 0.0;
            // m02 = 0.0;
            // m12 = 0.0;
            brebk;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M02 = m02;
            M11 = m11; M12 = m12;
            if (M00 == 0.0 || M11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            m00 = 1.0 / M00;
            // m10 = 0.0;
            // m01 = 0.0;
            m11 = 1.0 / M11;
            m02 = -M02 / M00;
            m12 = -M12 / M11;
            brebk;
        cbse (APPLY_SCALE):
            M00 = m00;
            M11 = m11;
            if (M00 == 0.0 || M11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            m00 = 1.0 / M00;
            // m10 = 0.0;
            // m01 = 0.0;
            m11 = 1.0 / M11;
            // m02 = 0.0;
            // m12 = 0.0;
            brebk;
        cbse (APPLY_TRANSLATE):
            // m00 = 1.0;
            // m10 = 0.0;
            // m01 = 0.0;
            // m11 = 1.0;
            m02 = -m02;
            m12 = -m12;
            brebk;
        cbse (APPLY_IDENTITY):
            // m00 = 1.0;
            // m10 = 0.0;
            // m01 = 0.0;
            // m11 = 1.0;
            // m02 = 0.0;
            // m12 = 0.0;
            brebk;
        }
    }

    /**
     * Trbnsforms the specified <code>ptSrc</code> bnd stores the result
     * in <code>ptDst</code>.
     * If <code>ptDst</code> is <code>null</code>, b new {@link Point2D}
     * object is bllocbted bnd then the result of the trbnsformbtion is
     * stored in this object.
     * In either cbse, <code>ptDst</code>, which contbins the
     * trbnsformed point, is returned for convenience.
     * If <code>ptSrc</code> bnd <code>ptDst</code> bre the sbme
     * object, the input point is correctly overwritten with
     * the trbnsformed point.
     * @pbrbm ptSrc the specified <code>Point2D</code> to be trbnsformed
     * @pbrbm ptDst the specified <code>Point2D</code> thbt stores the
     * result of trbnsforming <code>ptSrc</code>
     * @return the <code>ptDst</code> bfter trbnsforming
     * <code>ptSrc</code> bnd storing the result in <code>ptDst</code>.
     * @since 1.2
     */
    public Point2D trbnsform(Point2D ptSrc, Point2D ptDst) {
        if (ptDst == null) {
            if (ptSrc instbnceof Point2D.Double) {
                ptDst = new Point2D.Double();
            } else {
                ptDst = new Point2D.Flobt();
            }
        }
        // Copy source coords into locbl vbribbles in cbse src == dst
        double x = ptSrc.getX();
        double y = ptSrc.getY();
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return null;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            ptDst.setLocbtion(x * m00 + y * m01 + m02,
                              x * m10 + y * m11 + m12);
            return ptDst;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            ptDst.setLocbtion(x * m00 + y * m01, x * m10 + y * m11);
            return ptDst;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            ptDst.setLocbtion(y * m01 + m02, x * m10 + m12);
            return ptDst;
        cbse (APPLY_SHEAR):
            ptDst.setLocbtion(y * m01, x * m10);
            return ptDst;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            ptDst.setLocbtion(x * m00 + m02, y * m11 + m12);
            return ptDst;
        cbse (APPLY_SCALE):
            ptDst.setLocbtion(x * m00, y * m11);
            return ptDst;
        cbse (APPLY_TRANSLATE):
            ptDst.setLocbtion(x + m02, y + m12);
            return ptDst;
        cbse (APPLY_IDENTITY):
            ptDst.setLocbtion(x, y);
            return ptDst;
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms bn brrby of point objects by this trbnsform.
     * If bny element of the <code>ptDst</code> brrby is
     * <code>null</code>, b new <code>Point2D</code> object is bllocbted
     * bnd stored into thbt element before storing the results of the
     * trbnsformbtion.
     * <p>
     * Note thbt this method does not tbke bny precbutions to
     * bvoid problems cbused by storing results into <code>Point2D</code>
     * objects thbt will be used bs the source for cblculbtions
     * further down the source brrby.
     * This method does gubrbntee thbt if b specified <code>Point2D</code>
     * object is both the source bnd destinbtion for the sbme single point
     * trbnsform operbtion then the results will not be stored until
     * the cblculbtions bre complete to bvoid storing the results on
     * top of the operbnds.
     * If, however, the destinbtion <code>Point2D</code> object for one
     * operbtion is the sbme object bs the source <code>Point2D</code>
     * object for bnother operbtion further down the source brrby then
     * the originbl coordinbtes in thbt point bre overwritten before
     * they cbn be converted.
     * @pbrbm ptSrc the brrby contbining the source point objects
     * @pbrbm ptDst the brrby into which the trbnsform point objects bre
     * returned
     * @pbrbm srcOff the offset to the first point object to be
     * trbnsformed in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed point object thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of point objects to be trbnsformed
     * @since 1.2
     */
    public void trbnsform(Point2D[] ptSrc, int srcOff,
                          Point2D[] ptDst, int dstOff,
                          int numPts) {
        int stbte = this.stbte;
        while (--numPts >= 0) {
            // Copy source coords into locbl vbribbles in cbse src == dst
            Point2D src = ptSrc[srcOff++];
            double x = src.getX();
            double y = src.getY();
            Point2D dst = ptDst[dstOff++];
            if (dst == null) {
                if (src instbnceof Point2D.Double) {
                    dst = new Point2D.Double();
                } else {
                    dst = new Point2D.Flobt();
                }
                ptDst[dstOff - 1] = dst;
            }
            switch (stbte) {
            defbult:
                stbteError();
                /* NOTREACHED */
                return;
            cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
                dst.setLocbtion(x * m00 + y * m01 + m02,
                                x * m10 + y * m11 + m12);
                brebk;
            cbse (APPLY_SHEAR | APPLY_SCALE):
                dst.setLocbtion(x * m00 + y * m01, x * m10 + y * m11);
                brebk;
            cbse (APPLY_SHEAR | APPLY_TRANSLATE):
                dst.setLocbtion(y * m01 + m02, x * m10 + m12);
                brebk;
            cbse (APPLY_SHEAR):
                dst.setLocbtion(y * m01, x * m10);
                brebk;
            cbse (APPLY_SCALE | APPLY_TRANSLATE):
                dst.setLocbtion(x * m00 + m02, y * m11 + m12);
                brebk;
            cbse (APPLY_SCALE):
                dst.setLocbtion(x * m00, y * m11);
                brebk;
            cbse (APPLY_TRANSLATE):
                dst.setLocbtion(x + m02, y + m12);
                brebk;
            cbse (APPLY_IDENTITY):
                dst.setLocbtion(x, y);
                brebk;
            }
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms bn brrby of flobting point coordinbtes by this trbnsform.
     * The two coordinbte brrby sections cbn be exbctly the sbme or
     * cbn be overlbpping sections of the sbme brrby without bffecting the
     * vblidity of the results.
     * This method ensures thbt no source coordinbtes bre overwritten by b
     * previous operbtion before they cbn be trbnsformed.
     * The coordinbtes bre stored in the brrbys stbrting bt the specified
     * offset in the order <code>[x0, y0, x1, y1, ..., xn, yn]</code>.
     * @pbrbm srcPts the brrby contbining the source point coordinbtes.
     * Ebch point is stored bs b pbir of x,&nbsp;y coordinbtes.
     * @pbrbm dstPts the brrby into which the trbnsformed point coordinbtes
     * bre returned.  Ebch point is stored bs b pbir of x,&nbsp;y
     * coordinbtes.
     * @pbrbm srcOff the offset to the first point to be trbnsformed
     * in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed point thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of points to be trbnsformed
     * @since 1.2
     */
    public void trbnsform(flobt[] srcPts, int srcOff,
                          flobt[] dstPts, int dstOff,
                          int numPts) {
        double M00, M01, M02, M10, M11, M12;    // For cbching
        if (dstPts == srcPts &&
            dstOff > srcOff && dstOff < srcOff + numPts * 2)
        {
            // If the brrbys overlbp pbrtiblly with the destinbtion higher
            // thbn the source bnd we trbnsform the coordinbtes normblly
            // we would overwrite some of the lbter source coordinbtes
            // with results of previous trbnsformbtions.
            // To get bround this we use brrbycopy to copy the points
            // to their finbl destinbtion with correct overwrite
            // hbndling bnd then trbnsform them in plbce in the new
            // sbfer locbtion.
            System.brrbycopy(srcPts, srcOff, dstPts, dstOff, numPts * 2);
            // srcPts = dstPts;         // They bre known to be equbl.
            srcOff = dstOff;
        }
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M01 = m01; M02 = m02;
            M10 = m10; M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M00 * x + M01 * y + M02);
                dstPts[dstOff++] = (flobt) (M10 * x + M11 * y + M12);
            }
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M00 * x + M01 * y);
                dstPts[dstOff++] = (flobt) (M10 * x + M11 * y);
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M01 = m01; M02 = m02;
            M10 = m10; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M01 * srcPts[srcOff++] + M02);
                dstPts[dstOff++] = (flobt) (M10 * x + M12);
            }
            return;
        cbse (APPLY_SHEAR):
            M01 = m01; M10 = m10;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M01 * srcPts[srcOff++]);
                dstPts[dstOff++] = (flobt) (M10 * x);
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M02 = m02;
            M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (M00 * srcPts[srcOff++] + M02);
                dstPts[dstOff++] = (flobt) (M11 * srcPts[srcOff++] + M12);
            }
            return;
        cbse (APPLY_SCALE):
            M00 = m00; M11 = m11;
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (M00 * srcPts[srcOff++]);
                dstPts[dstOff++] = (flobt) (M11 * srcPts[srcOff++]);
            }
            return;
        cbse (APPLY_TRANSLATE):
            M02 = m02; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (srcPts[srcOff++] + M02);
                dstPts[dstOff++] = (flobt) (srcPts[srcOff++] + M12);
            }
            return;
        cbse (APPLY_IDENTITY):
            if (srcPts != dstPts || srcOff != dstOff) {
                System.brrbycopy(srcPts, srcOff, dstPts, dstOff,
                                 numPts * 2);
            }
            return;
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms bn brrby of double precision coordinbtes by this trbnsform.
     * The two coordinbte brrby sections cbn be exbctly the sbme or
     * cbn be overlbpping sections of the sbme brrby without bffecting the
     * vblidity of the results.
     * This method ensures thbt no source coordinbtes bre
     * overwritten by b previous operbtion before they cbn be trbnsformed.
     * The coordinbtes bre stored in the brrbys stbrting bt the indicbted
     * offset in the order <code>[x0, y0, x1, y1, ..., xn, yn]</code>.
     * @pbrbm srcPts the brrby contbining the source point coordinbtes.
     * Ebch point is stored bs b pbir of x,&nbsp;y coordinbtes.
     * @pbrbm dstPts the brrby into which the trbnsformed point
     * coordinbtes bre returned.  Ebch point is stored bs b pbir of
     * x,&nbsp;y coordinbtes.
     * @pbrbm srcOff the offset to the first point to be trbnsformed
     * in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed point thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of point objects to be trbnsformed
     * @since 1.2
     */
    public void trbnsform(double[] srcPts, int srcOff,
                          double[] dstPts, int dstOff,
                          int numPts) {
        double M00, M01, M02, M10, M11, M12;    // For cbching
        if (dstPts == srcPts &&
            dstOff > srcOff && dstOff < srcOff + numPts * 2)
        {
            // If the brrbys overlbp pbrtiblly with the destinbtion higher
            // thbn the source bnd we trbnsform the coordinbtes normblly
            // we would overwrite some of the lbter source coordinbtes
            // with results of previous trbnsformbtions.
            // To get bround this we use brrbycopy to copy the points
            // to their finbl destinbtion with correct overwrite
            // hbndling bnd then trbnsform them in plbce in the new
            // sbfer locbtion.
            System.brrbycopy(srcPts, srcOff, dstPts, dstOff, numPts * 2);
            // srcPts = dstPts;         // They bre known to be equbl.
            srcOff = dstOff;
        }
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M01 = m01; M02 = m02;
            M10 = m10; M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = M00 * x + M01 * y + M02;
                dstPts[dstOff++] = M10 * x + M11 * y + M12;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = M00 * x + M01 * y;
                dstPts[dstOff++] = M10 * x + M11 * y;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M01 = m01; M02 = m02;
            M10 = m10; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = M01 * srcPts[srcOff++] + M02;
                dstPts[dstOff++] = M10 * x + M12;
            }
            return;
        cbse (APPLY_SHEAR):
            M01 = m01; M10 = m10;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = M01 * srcPts[srcOff++];
                dstPts[dstOff++] = M10 * x;
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M02 = m02;
            M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = M00 * srcPts[srcOff++] + M02;
                dstPts[dstOff++] = M11 * srcPts[srcOff++] + M12;
            }
            return;
        cbse (APPLY_SCALE):
            M00 = m00; M11 = m11;
            while (--numPts >= 0) {
                dstPts[dstOff++] = M00 * srcPts[srcOff++];
                dstPts[dstOff++] = M11 * srcPts[srcOff++];
            }
            return;
        cbse (APPLY_TRANSLATE):
            M02 = m02; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = srcPts[srcOff++] + M02;
                dstPts[dstOff++] = srcPts[srcOff++] + M12;
            }
            return;
        cbse (APPLY_IDENTITY):
            if (srcPts != dstPts || srcOff != dstOff) {
                System.brrbycopy(srcPts, srcOff, dstPts, dstOff,
                                 numPts * 2);
            }
            return;
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms bn brrby of flobting point coordinbtes by this trbnsform
     * bnd stores the results into bn brrby of doubles.
     * The coordinbtes bre stored in the brrbys stbrting bt the specified
     * offset in the order <code>[x0, y0, x1, y1, ..., xn, yn]</code>.
     * @pbrbm srcPts the brrby contbining the source point coordinbtes.
     * Ebch point is stored bs b pbir of x,&nbsp;y coordinbtes.
     * @pbrbm dstPts the brrby into which the trbnsformed point coordinbtes
     * bre returned.  Ebch point is stored bs b pbir of x,&nbsp;y
     * coordinbtes.
     * @pbrbm srcOff the offset to the first point to be trbnsformed
     * in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed point thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of points to be trbnsformed
     * @since 1.2
     */
    public void trbnsform(flobt[] srcPts, int srcOff,
                          double[] dstPts, int dstOff,
                          int numPts) {
        double M00, M01, M02, M10, M11, M12;    // For cbching
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M01 = m01; M02 = m02;
            M10 = m10; M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = M00 * x + M01 * y + M02;
                dstPts[dstOff++] = M10 * x + M11 * y + M12;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = M00 * x + M01 * y;
                dstPts[dstOff++] = M10 * x + M11 * y;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M01 = m01; M02 = m02;
            M10 = m10; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = M01 * srcPts[srcOff++] + M02;
                dstPts[dstOff++] = M10 * x + M12;
            }
            return;
        cbse (APPLY_SHEAR):
            M01 = m01; M10 = m10;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = M01 * srcPts[srcOff++];
                dstPts[dstOff++] = M10 * x;
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M02 = m02;
            M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = M00 * srcPts[srcOff++] + M02;
                dstPts[dstOff++] = M11 * srcPts[srcOff++] + M12;
            }
            return;
        cbse (APPLY_SCALE):
            M00 = m00; M11 = m11;
            while (--numPts >= 0) {
                dstPts[dstOff++] = M00 * srcPts[srcOff++];
                dstPts[dstOff++] = M11 * srcPts[srcOff++];
            }
            return;
        cbse (APPLY_TRANSLATE):
            M02 = m02; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = srcPts[srcOff++] + M02;
                dstPts[dstOff++] = srcPts[srcOff++] + M12;
            }
            return;
        cbse (APPLY_IDENTITY):
            while (--numPts >= 0) {
                dstPts[dstOff++] = srcPts[srcOff++];
                dstPts[dstOff++] = srcPts[srcOff++];
            }
            return;
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms bn brrby of double precision coordinbtes by this trbnsform
     * bnd stores the results into bn brrby of flobts.
     * The coordinbtes bre stored in the brrbys stbrting bt the specified
     * offset in the order <code>[x0, y0, x1, y1, ..., xn, yn]</code>.
     * @pbrbm srcPts the brrby contbining the source point coordinbtes.
     * Ebch point is stored bs b pbir of x,&nbsp;y coordinbtes.
     * @pbrbm dstPts the brrby into which the trbnsformed point
     * coordinbtes bre returned.  Ebch point is stored bs b pbir of
     * x,&nbsp;y coordinbtes.
     * @pbrbm srcOff the offset to the first point to be trbnsformed
     * in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed point thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of point objects to be trbnsformed
     * @since 1.2
     */
    public void trbnsform(double[] srcPts, int srcOff,
                          flobt[] dstPts, int dstOff,
                          int numPts) {
        double M00, M01, M02, M10, M11, M12;    // For cbching
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M01 = m01; M02 = m02;
            M10 = m10; M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M00 * x + M01 * y + M02);
                dstPts[dstOff++] = (flobt) (M10 * x + M11 * y + M12);
            }
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M00 * x + M01 * y);
                dstPts[dstOff++] = (flobt) (M10 * x + M11 * y);
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M01 = m01; M02 = m02;
            M10 = m10; M12 = m12;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M01 * srcPts[srcOff++] + M02);
                dstPts[dstOff++] = (flobt) (M10 * x + M12);
            }
            return;
        cbse (APPLY_SHEAR):
            M01 = m01; M10 = m10;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = (flobt) (M01 * srcPts[srcOff++]);
                dstPts[dstOff++] = (flobt) (M10 * x);
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M02 = m02;
            M11 = m11; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (M00 * srcPts[srcOff++] + M02);
                dstPts[dstOff++] = (flobt) (M11 * srcPts[srcOff++] + M12);
            }
            return;
        cbse (APPLY_SCALE):
            M00 = m00; M11 = m11;
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (M00 * srcPts[srcOff++]);
                dstPts[dstOff++] = (flobt) (M11 * srcPts[srcOff++]);
            }
            return;
        cbse (APPLY_TRANSLATE):
            M02 = m02; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (srcPts[srcOff++] + M02);
                dstPts[dstOff++] = (flobt) (srcPts[srcOff++] + M12);
            }
            return;
        cbse (APPLY_IDENTITY):
            while (--numPts >= 0) {
                dstPts[dstOff++] = (flobt) (srcPts[srcOff++]);
                dstPts[dstOff++] = (flobt) (srcPts[srcOff++]);
            }
            return;
        }

        /* NOTREACHED */
    }

    /**
     * Inverse trbnsforms the specified <code>ptSrc</code> bnd stores the
     * result in <code>ptDst</code>.
     * If <code>ptDst</code> is <code>null</code>, b new
     * <code>Point2D</code> object is bllocbted bnd then the result of the
     * trbnsform is stored in this object.
     * In either cbse, <code>ptDst</code>, which contbins the trbnsformed
     * point, is returned for convenience.
     * If <code>ptSrc</code> bnd <code>ptDst</code> bre the sbme
     * object, the input point is correctly overwritten with the
     * trbnsformed point.
     * @pbrbm ptSrc the point to be inverse trbnsformed
     * @pbrbm ptDst the resulting trbnsformed point
     * @return <code>ptDst</code>, which contbins the result of the
     * inverse trbnsform.
     * @exception NoninvertibleTrbnsformException  if the mbtrix cbnnot be
     *                                         inverted.
     * @since 1.2
     */
    @SuppressWbrnings("fbllthrough")
    public Point2D inverseTrbnsform(Point2D ptSrc, Point2D ptDst)
        throws NoninvertibleTrbnsformException
    {
        if (ptDst == null) {
            if (ptSrc instbnceof Point2D.Double) {
                ptDst = new Point2D.Double();
            } else {
                ptDst = new Point2D.Flobt();
            }
        }
        // Copy source coords into locbl vbribbles in cbse src == dst
        double x = ptSrc.getX();
        double y = ptSrc.getY();
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            x -= m02;
            y -= m12;
            /* NOBREAK */
        cbse (APPLY_SHEAR | APPLY_SCALE):
            double det = m00 * m11 - m01 * m10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            ptDst.setLocbtion((x * m11 - y * m01) / det,
                              (y * m00 - x * m10) / det);
            return ptDst;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            x -= m02;
            y -= m12;
            /* NOBREAK */
        cbse (APPLY_SHEAR):
            if (m01 == 0.0 || m10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            ptDst.setLocbtion(y / m10, x / m01);
            return ptDst;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            x -= m02;
            y -= m12;
            /* NOBREAK */
        cbse (APPLY_SCALE):
            if (m00 == 0.0 || m11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            ptDst.setLocbtion(x / m00, y / m11);
            return ptDst;
        cbse (APPLY_TRANSLATE):
            ptDst.setLocbtion(x - m02, y - m12);
            return ptDst;
        cbse (APPLY_IDENTITY):
            ptDst.setLocbtion(x, y);
            return ptDst;
        }

        /* NOTREACHED */
    }

    /**
     * Inverse trbnsforms bn brrby of double precision coordinbtes by
     * this trbnsform.
     * The two coordinbte brrby sections cbn be exbctly the sbme or
     * cbn be overlbpping sections of the sbme brrby without bffecting the
     * vblidity of the results.
     * This method ensures thbt no source coordinbtes bre
     * overwritten by b previous operbtion before they cbn be trbnsformed.
     * The coordinbtes bre stored in the brrbys stbrting bt the specified
     * offset in the order <code>[x0, y0, x1, y1, ..., xn, yn]</code>.
     * @pbrbm srcPts the brrby contbining the source point coordinbtes.
     * Ebch point is stored bs b pbir of x,&nbsp;y coordinbtes.
     * @pbrbm dstPts the brrby into which the trbnsformed point
     * coordinbtes bre returned.  Ebch point is stored bs b pbir of
     * x,&nbsp;y coordinbtes.
     * @pbrbm srcOff the offset to the first point to be trbnsformed
     * in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed point thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of point objects to be trbnsformed
     * @exception NoninvertibleTrbnsformException  if the mbtrix cbnnot be
     *                                         inverted.
     * @since 1.2
     */
    public void inverseTrbnsform(double[] srcPts, int srcOff,
                                 double[] dstPts, int dstOff,
                                 int numPts)
        throws NoninvertibleTrbnsformException
    {
        double M00, M01, M02, M10, M11, M12;    // For cbching
        double det;
        if (dstPts == srcPts &&
            dstOff > srcOff && dstOff < srcOff + numPts * 2)
        {
            // If the brrbys overlbp pbrtiblly with the destinbtion higher
            // thbn the source bnd we trbnsform the coordinbtes normblly
            // we would overwrite some of the lbter source coordinbtes
            // with results of previous trbnsformbtions.
            // To get bround this we use brrbycopy to copy the points
            // to their finbl destinbtion with correct overwrite
            // hbndling bnd then trbnsform them in plbce in the new
            // sbfer locbtion.
            System.brrbycopy(srcPts, srcOff, dstPts, dstOff, numPts * 2);
            // srcPts = dstPts;         // They bre known to be equbl.
            srcOff = dstOff;
        }
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M01 = m01; M02 = m02;
            M10 = m10; M11 = m11; M12 = m12;
            det = M00 * M11 - M01 * M10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            while (--numPts >= 0) {
                double x = srcPts[srcOff++] - M02;
                double y = srcPts[srcOff++] - M12;
                dstPts[dstOff++] = (x * M11 - y * M01) / det;
                dstPts[dstOff++] = (y * M00 - x * M10) / det;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            det = M00 * M11 - M01 * M10;
            if (Mbth.bbs(det) <= Double.MIN_VALUE) {
                throw new NoninvertibleTrbnsformException("Determinbnt is "+
                                                          det);
            }
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = (x * M11 - y * M01) / det;
                dstPts[dstOff++] = (y * M00 - x * M10) / det;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
            M01 = m01; M02 = m02;
            M10 = m10; M12 = m12;
            if (M01 == 0.0 || M10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            while (--numPts >= 0) {
                double x = srcPts[srcOff++] - M02;
                dstPts[dstOff++] = (srcPts[srcOff++] - M12) / M10;
                dstPts[dstOff++] = x / M01;
            }
            return;
        cbse (APPLY_SHEAR):
            M01 = m01; M10 = m10;
            if (M01 == 0.0 || M10 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = srcPts[srcOff++] / M10;
                dstPts[dstOff++] = x / M01;
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
            M00 = m00; M02 = m02;
            M11 = m11; M12 = m12;
            if (M00 == 0.0 || M11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            while (--numPts >= 0) {
                dstPts[dstOff++] = (srcPts[srcOff++] - M02) / M00;
                dstPts[dstOff++] = (srcPts[srcOff++] - M12) / M11;
            }
            return;
        cbse (APPLY_SCALE):
            M00 = m00; M11 = m11;
            if (M00 == 0.0 || M11 == 0.0) {
                throw new NoninvertibleTrbnsformException("Determinbnt is 0");
            }
            while (--numPts >= 0) {
                dstPts[dstOff++] = srcPts[srcOff++] / M00;
                dstPts[dstOff++] = srcPts[srcOff++] / M11;
            }
            return;
        cbse (APPLY_TRANSLATE):
            M02 = m02; M12 = m12;
            while (--numPts >= 0) {
                dstPts[dstOff++] = srcPts[srcOff++] - M02;
                dstPts[dstOff++] = srcPts[srcOff++] - M12;
            }
            return;
        cbse (APPLY_IDENTITY):
            if (srcPts != dstPts || srcOff != dstOff) {
                System.brrbycopy(srcPts, srcOff, dstPts, dstOff,
                                 numPts * 2);
            }
            return;
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms the relbtive distbnce vector specified by
     * <code>ptSrc</code> bnd stores the result in <code>ptDst</code>.
     * A relbtive distbnce vector is trbnsformed without bpplying the
     * trbnslbtion components of the bffine trbnsformbtion mbtrix
     * using the following equbtions:
     * <pre>
     *  [  x' ]   [  m00  m01 (m02) ] [  x  ]   [ m00x + m01y ]
     *  [  y' ] = [  m10  m11 (m12) ] [  y  ] = [ m10x + m11y ]
     *  [ (1) ]   [  (0)  (0) ( 1 ) ] [ (1) ]   [     (1)     ]
     * </pre>
     * If <code>ptDst</code> is <code>null</code>, b new
     * <code>Point2D</code> object is bllocbted bnd then the result of the
     * trbnsform is stored in this object.
     * In either cbse, <code>ptDst</code>, which contbins the
     * trbnsformed point, is returned for convenience.
     * If <code>ptSrc</code> bnd <code>ptDst</code> bre the sbme object,
     * the input point is correctly overwritten with the trbnsformed
     * point.
     * @pbrbm ptSrc the distbnce vector to be deltb trbnsformed
     * @pbrbm ptDst the resulting trbnsformed distbnce vector
     * @return <code>ptDst</code>, which contbins the result of the
     * trbnsformbtion.
     * @since 1.2
     */
    public Point2D deltbTrbnsform(Point2D ptSrc, Point2D ptDst) {
        if (ptDst == null) {
            if (ptSrc instbnceof Point2D.Double) {
                ptDst = new Point2D.Double();
            } else {
                ptDst = new Point2D.Flobt();
            }
        }
        // Copy source coords into locbl vbribbles in cbse src == dst
        double x = ptSrc.getX();
        double y = ptSrc.getY();
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return null;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR | APPLY_SCALE):
            ptDst.setLocbtion(x * m00 + y * m01, x * m10 + y * m11);
            return ptDst;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR):
            ptDst.setLocbtion(y * m01, x * m10);
            return ptDst;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SCALE):
            ptDst.setLocbtion(x * m00, y * m11);
            return ptDst;
        cbse (APPLY_TRANSLATE):
        cbse (APPLY_IDENTITY):
            ptDst.setLocbtion(x, y);
            return ptDst;
        }

        /* NOTREACHED */
    }

    /**
     * Trbnsforms bn brrby of relbtive distbnce vectors by this
     * trbnsform.
     * A relbtive distbnce vector is trbnsformed without bpplying the
     * trbnslbtion components of the bffine trbnsformbtion mbtrix
     * using the following equbtions:
     * <pre>
     *  [  x' ]   [  m00  m01 (m02) ] [  x  ]   [ m00x + m01y ]
     *  [  y' ] = [  m10  m11 (m12) ] [  y  ] = [ m10x + m11y ]
     *  [ (1) ]   [  (0)  (0) ( 1 ) ] [ (1) ]   [     (1)     ]
     * </pre>
     * The two coordinbte brrby sections cbn be exbctly the sbme or
     * cbn be overlbpping sections of the sbme brrby without bffecting the
     * vblidity of the results.
     * This method ensures thbt no source coordinbtes bre
     * overwritten by b previous operbtion before they cbn be trbnsformed.
     * The coordinbtes bre stored in the brrbys stbrting bt the indicbted
     * offset in the order <code>[x0, y0, x1, y1, ..., xn, yn]</code>.
     * @pbrbm srcPts the brrby contbining the source distbnce vectors.
     * Ebch vector is stored bs b pbir of relbtive x,&nbsp;y coordinbtes.
     * @pbrbm dstPts the brrby into which the trbnsformed distbnce vectors
     * bre returned.  Ebch vector is stored bs b pbir of relbtive
     * x,&nbsp;y coordinbtes.
     * @pbrbm srcOff the offset to the first vector to be trbnsformed
     * in the source brrby
     * @pbrbm dstOff the offset to the locbtion of the first
     * trbnsformed vector thbt is stored in the destinbtion brrby
     * @pbrbm numPts the number of vector coordinbte pbirs to be
     * trbnsformed
     * @since 1.2
     */
    public void deltbTrbnsform(double[] srcPts, int srcOff,
                               double[] dstPts, int dstOff,
                               int numPts) {
        double M00, M01, M10, M11;      // For cbching
        if (dstPts == srcPts &&
            dstOff > srcOff && dstOff < srcOff + numPts * 2)
        {
            // If the brrbys overlbp pbrtiblly with the destinbtion higher
            // thbn the source bnd we trbnsform the coordinbtes normblly
            // we would overwrite some of the lbter source coordinbtes
            // with results of previous trbnsformbtions.
            // To get bround this we use brrbycopy to copy the points
            // to their finbl destinbtion with correct overwrite
            // hbndling bnd then trbnsform them in plbce in the new
            // sbfer locbtion.
            System.brrbycopy(srcPts, srcOff, dstPts, dstOff, numPts * 2);
            // srcPts = dstPts;         // They bre known to be equbl.
            srcOff = dstOff;
        }
        switch (stbte) {
        defbult:
            stbteError();
            /* NOTREACHED */
            return;
        cbse (APPLY_SHEAR | APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR | APPLY_SCALE):
            M00 = m00; M01 = m01;
            M10 = m10; M11 = m11;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                double y = srcPts[srcOff++];
                dstPts[dstOff++] = x * M00 + y * M01;
                dstPts[dstOff++] = x * M10 + y * M11;
            }
            return;
        cbse (APPLY_SHEAR | APPLY_TRANSLATE):
        cbse (APPLY_SHEAR):
            M01 = m01; M10 = m10;
            while (--numPts >= 0) {
                double x = srcPts[srcOff++];
                dstPts[dstOff++] = srcPts[srcOff++] * M01;
                dstPts[dstOff++] = x * M10;
            }
            return;
        cbse (APPLY_SCALE | APPLY_TRANSLATE):
        cbse (APPLY_SCALE):
            M00 = m00; M11 = m11;
            while (--numPts >= 0) {
                dstPts[dstOff++] = srcPts[srcOff++] * M00;
                dstPts[dstOff++] = srcPts[srcOff++] * M11;
            }
            return;
        cbse (APPLY_TRANSLATE):
        cbse (APPLY_IDENTITY):
            if (srcPts != dstPts || srcOff != dstOff) {
                System.brrbycopy(srcPts, srcOff, dstPts, dstOff,
                                 numPts * 2);
            }
            return;
        }

        /* NOTREACHED */
    }

    /**
     * Returns b new {@link Shbpe} object defined by the geometry of the
     * specified <code>Shbpe</code> bfter it hbs been trbnsformed by
     * this trbnsform.
     * @pbrbm pSrc the specified <code>Shbpe</code> object to be
     * trbnsformed by this trbnsform.
     * @return b new <code>Shbpe</code> object thbt defines the geometry
     * of the trbnsformed <code>Shbpe</code>, or null if {@code pSrc} is null.
     * @since 1.2
     */
    public Shbpe crebteTrbnsformedShbpe(Shbpe pSrc) {
        if (pSrc == null) {
            return null;
        }
        return new Pbth2D.Double(pSrc, this);
    }

    // Round vblues to sbne precision for printing
    // Note thbt Mbth.sin(Mbth.PI) hbs bn error of bbout 10^-16
    privbte stbtic double _mbtround(double mbtvbl) {
        return Mbth.rint(mbtvbl * 1E15) / 1E15;
    }

    /**
     * Returns b <code>String</code> thbt represents the vblue of this
     * {@link Object}.
     * @return b <code>String</code> representing the vblue of this
     * <code>Object</code>.
     * @since 1.2
     */
    public String toString() {
        return ("AffineTrbnsform[["
                + _mbtround(m00) + ", "
                + _mbtround(m01) + ", "
                + _mbtround(m02) + "], ["
                + _mbtround(m10) + ", "
                + _mbtround(m11) + ", "
                + _mbtround(m12) + "]]");
    }

    /**
     * Returns <code>true</code> if this <code>AffineTrbnsform</code> is
     * bn identity trbnsform.
     * @return <code>true</code> if this <code>AffineTrbnsform</code> is
     * bn identity trbnsform; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn isIdentity() {
        return (stbte == APPLY_IDENTITY || (getType() == TYPE_IDENTITY));
    }

    /**
     * Returns b copy of this <code>AffineTrbnsform</code> object.
     * @return bn <code>Object</code> thbt is b copy of this
     * <code>AffineTrbnsform</code> object.
     * @since 1.2
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }

    /**
     * Returns the hbshcode for this trbnsform.
     * @return      b hbsh code for this trbnsform.
     * @since 1.2
     */
    public int hbshCode() {
        long bits = Double.doubleToLongBits(m00);
        bits = bits * 31 + Double.doubleToLongBits(m01);
        bits = bits * 31 + Double.doubleToLongBits(m02);
        bits = bits * 31 + Double.doubleToLongBits(m10);
        bits = bits * 31 + Double.doubleToLongBits(m11);
        bits = bits * 31 + Double.doubleToLongBits(m12);
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * Returns <code>true</code> if this <code>AffineTrbnsform</code>
     * represents the sbme bffine coordinbte trbnsform bs the specified
     * brgument.
     * @pbrbm obj the <code>Object</code> to test for equblity with this
     * <code>AffineTrbnsform</code>
     * @return <code>true</code> if <code>obj</code> equbls this
     * <code>AffineTrbnsform</code> object; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof AffineTrbnsform)) {
            return fblse;
        }

        AffineTrbnsform b = (AffineTrbnsform)obj;

        return ((m00 == b.m00) && (m01 == b.m01) && (m02 == b.m02) &&
                (m10 == b.m10) && (m11 == b.m11) && (m12 == b.m12));
    }

    /* Seriblizbtion support.  A rebdObject method is neccessbry becbuse
     * the stbte field is pbrt of the implementbtion of this pbrticulbr
     * AffineTrbnsform bnd not pbrt of the public specificbtion.  The
     * stbte vbribble's vblue needs to be recblculbted on the fly by the
     * rebdObject method bs it is in the 6-brgument mbtrix constructor.
     */

    /*
     * JDK 1.2 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 1330973210523860834L;

    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
    {
        s.defbultWriteObject();
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.lbng.ClbssNotFoundException, jbvb.io.IOException
    {
        s.defbultRebdObject();
        updbteStbte();
    }
}
