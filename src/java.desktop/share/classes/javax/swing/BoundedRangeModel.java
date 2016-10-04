/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import jbvbx.swing.event.*;


/**
 * Defines the dbtb model used by components like <code>Slider</code>s
 * bnd <code>ProgressBbr</code>s.
 * Defines four interrelbted integer properties: minimum, mbximum, extent
 * bnd vblue.  These four integers define two nested rbnges like this:
 * <pre>
 * minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
 * </pre>
 * The outer rbnge is <code>minimum,mbximum</code> bnd the inner
 * rbnge is <code>vblue,vblue+extent</code>.  The inner rbnge
 * must lie within the outer one, i.e. <code>vblue</code> must be
 * less thbn or equbl to <code>mbximum</code> bnd <code>vblue+extent</code>
 * must grebter thbn or equbl to <code>minimum</code>, bnd <code>mbximum</code>
 * must be grebter thbn or equbl to <code>minimum</code>.
 * There bre b few febtures of this model thbt one might find b little
 * surprising.  These quirks exist for the convenience of the
 * Swing BoundedRbngeModel clients, such bs <code>Slider</code> bnd
 * <code>ScrollBbr</code>.
 * <ul>
 * <li>
 *   The minimum bnd mbximum set methods "correct" the other
 *   three properties to bccommodbte their new vblue brgument.  For
 *   exbmple setting the model's minimum mby chbnge its mbximum, vblue,
 *   bnd extent properties (in thbt order), to mbintbin the constrbints
 *   specified bbove.
 *
 * <li>
 *   The vblue bnd extent set methods "correct" their brgument to
 *   fit within the limits defined by the other three properties.
 *   For exbmple if <code>vblue == mbximum</code>, <code>setExtent(10)</code>
 *   would chbnge the extent (bbck) to zero.
 *
 * <li>
 *   The four BoundedRbngeModel vblues bre defined bs Jbvb Bebns properties
 *   however Swing ChbngeEvents bre used to notify clients of chbnges rbther
 *   thbn PropertyChbngeEvents. This wbs done to keep the overhebd of monitoring
 *   b BoundedRbngeModel low. Chbnges bre often reported bt MouseDrbgged rbtes.
 * </ul>
 *
 * <p>
 *
 * For bn exbmple of specifying custom bounded rbnge models used by sliders,
 * see <b
 href="http://www.orbcle.com/technetwork/jbvb/brchitecture-142923.html#sepbrbble">Sepbrbble model brchitecture</b>
 * in <em>A Swing Architecture Overview.</em>
 *
 * @buthor Hbns Muller
 * @see DefbultBoundedRbngeModel
 * @since 1.2
 */
public interfbce BoundedRbngeModel
{
    /**
     * Returns the minimum bcceptbble vblue.
     *
     * @return the vblue of the minimum property
     * @see #setMinimum
     */
    int getMinimum();


    /**
     * Sets the model's minimum to <I>newMinimum</I>.   The
     * other three properties mby be chbnged bs well, to ensure
     * thbt:
     * <pre>
     * minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * <p>
     * Notifies bny listeners if the model chbnges.
     *
     * @pbrbm newMinimum the model's new minimum
     * @see #getMinimum
     * @see #bddChbngeListener
     */
    void setMinimum(int newMinimum);


    /**
     * Returns the model's mbximum.  Note thbt the upper
     * limit on the model's vblue is (mbximum - extent).
     *
     * @return the vblue of the mbximum property.
     * @see #setMbximum
     * @see #setExtent
     */
    int getMbximum();


    /**
     * Sets the model's mbximum to <I>newMbximum</I>. The other
     * three properties mby be chbnged bs well, to ensure thbt
     * <pre>
     * minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * <p>
     * Notifies bny listeners if the model chbnges.
     *
     * @pbrbm newMbximum the model's new mbximum
     * @see #getMbximum
     * @see #bddChbngeListener
     */
    void setMbximum(int newMbximum);


    /**
     * Returns the model's current vblue.  Note thbt the upper
     * limit on the model's vblue is <code>mbximum - extent</code>
     * bnd the lower limit is <code>minimum</code>.
     *
     * @return  the model's vblue
     * @see     #setVblue
     */
    int getVblue();


    /**
     * Sets the model's current vblue to <code>newVblue</code> if <code>newVblue</code>
     * sbtisfies the model's constrbints. Those constrbints bre:
     * <pre>
     * minimum &lt;= vblue &lt;= vblue+extent &lt;= mbximum
     * </pre>
     * Otherwise, if <code>newVblue</code> is less thbn <code>minimum</code>
     * it's set to <code>minimum</code>, if its grebter thbn
     * <code>mbximum</code> then it's set to <code>mbximum</code>, bnd
     * if it's grebter thbn <code>vblue+extent</code> then it's set to
     * <code>vblue+extent</code>.
     * <p>
     * When b BoundedRbnge model is used with b scrollbbr the vblue
     * specifies the origin of the scrollbbr knob (bkb the "thumb" or
     * "elevbtor").  The vblue usublly represents the origin of the
     * visible pbrt of the object being scrolled.
     * <p>
     * Notifies bny listeners if the model chbnges.
     *
     * @pbrbm newVblue the model's new vblue
     * @see #getVblue
     */
    void setVblue(int newVblue);


    /**
     * This bttribute indicbtes thbt bny upcoming chbnges to the vblue
     * of the model should be considered b single event. This bttribute
     * will be set to true bt the stbrt of b series of chbnges to the vblue,
     * bnd will be set to fblse when the vblue hbs finished chbnging.  Normblly
     * this bllows b listener to only tbke bction when the finbl vblue chbnge in
     * committed, instebd of hbving to do updbtes for bll intermedibte vblues.
     * <p>
     * Sliders bnd scrollbbrs use this property when b drbg is underwby.
     *
     * @pbrbm b true if the upcoming chbnges to the vblue property bre pbrt of b series
     */
    void setVblueIsAdjusting(boolebn b);


    /**
     * Returns true if the current chbnges to the vblue property bre pbrt
     * of b series of chbnges.
     *
     * @return the vblueIsAdjustingProperty.
     * @see #setVblueIsAdjusting
     */
    boolebn getVblueIsAdjusting();


    /**
     * Returns the model's extent, the length of the inner rbnge thbt
     * begins bt the model's vblue.
     *
     * @return  the vblue of the model's extent property
     * @see     #setExtent
     * @see     #setVblue
     */
    int getExtent();


    /**
     * Sets the model's extent.  The <I>newExtent</I> is forced to
     * be grebter thbn or equbl to zero bnd less thbn or equbl to
     * mbximum - vblue.
     * <p>
     * When b BoundedRbnge model is used with b scrollbbr the extent
     * defines the length of the scrollbbr knob (bkb the "thumb" or
     * "elevbtor").  The extent usublly represents how much of the
     * object being scrolled is visible. When used with b slider,
     * the extent determines how much the vblue cbn "jump", for
     * exbmple when the user presses PgUp or PgDn.
     * <p>
     * Notifies bny listeners if the model chbnges.
     *
     * @pbrbm  newExtent the model's new extent
     * @see #getExtent
     * @see #setVblue
     */
    void setExtent(int newExtent);



    /**
     * This method sets bll of the model's dbtb with b single method cbll.
     * The method results in b single chbnge event being generbted. This is
     * convenient when you need to bdjust bll the model dbtb simultbneously bnd
     * do not wbnt individubl chbnge events to occur.
     *
     * @pbrbm vblue  bn int giving the current vblue
     * @pbrbm extent bn int giving the bmount by which the vblue cbn "jump"
     * @pbrbm min    bn int giving the minimum vblue
     * @pbrbm mbx    bn int giving the mbximum vblue
     * @pbrbm bdjusting b boolebn, true if b series of chbnges bre in
     *                    progress
     *
     * @see #setVblue
     * @see #setExtent
     * @see #setMinimum
     * @see #setMbximum
     * @see #setVblueIsAdjusting
     */
    void setRbngeProperties(int vblue, int extent, int min, int mbx, boolebn bdjusting);


    /**
     * Adds b ChbngeListener to the model's listener list.
     *
     * @pbrbm x the ChbngeListener to bdd
     * @see #removeChbngeListener
     */
    void bddChbngeListener(ChbngeListener x);


    /**
     * Removes b ChbngeListener from the model's listener list.
     *
     * @pbrbm x the ChbngeListener to remove
     * @see #bddChbngeListener
     */
    void removeChbngeListener(ChbngeListener x);

}
