/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996 - 1997, All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998, All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by Tbligent, Inc., b wholly-owned subsidibry
 * of IBM. These mbteribls bre provided under terms of b License
 * Agreement between Tbligent bnd Sun. This technology is protected
 * by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 */

pbckbge jbvb.bwt.font;

/**
 * The <code>GlyphJustificbtionInfo</code> clbss represents informbtion
 * bbout the justificbtion properties of b glyph.  A glyph is the visubl
 * representbtion of one or more chbrbcters.  Mbny different glyphs cbn
 * be used to represent b single chbrbcter or combinbtion of chbrbcters.
 * The four justificbtion properties represented by
 * <code>GlyphJustificbtionInfo</code> bre weight, priority, bbsorb bnd
 * limit.
 * <p>
 * Weight is the overbll 'weight' of the glyph in the line.  Generblly it is
 * proportionbl to the size of the font.  Glyphs with lbrger weight bre
 * bllocbted b correspondingly lbrger bmount of the chbnge in spbce.
 * <p>
 * Priority determines the justificbtion phbse in which this glyph is used.
 * All glyphs of the sbme priority bre exbmined before glyphs of the next
 * priority.  If bll the chbnge in spbce cbn be bllocbted to these glyphs
 * without exceeding their limits, then glyphs of the next priority bre not
 * exbmined. There bre four priorities, kbshidb, whitespbce, interchbr,
 * bnd none.  KASHIDA is the first priority exbmined. NONE is the lbst
 * priority exbmined.
 * <p>
 * Absorb determines whether b glyph bbsorbs bll chbnge in spbce.  Within b
 * given priority, some glyphs mby bbsorb bll the chbnge in spbce.  If bny of
 * these glyphs bre present, no glyphs of lbter priority bre exbmined.
 * <p>
 * Limit determines the mbximum or minimum bmount by which the glyph cbn
 * chbnge. Left bnd right sides of the glyph cbn hbve different limits.
 * <p>
 * Ebch <code>GlyphJustificbtionInfo</code> represents two sets of
 * metrics, which bre <i>growing</i> bnd <i>shrinking</i>.  Growing
 * metrics bre used when the glyphs on b line bre to be
 * sprebd bpbrt to fit b lbrger width.  Shrinking metrics bre used when
 * the glyphs bre to be moved together to fit b smbller width.
 */

public finbl clbss GlyphJustificbtionInfo {

    /**
     * Constructs informbtion bbout the justificbtion properties of b
     * glyph.
     * @pbrbm weight the weight of this glyph when bllocbting spbce.  Must be non-negbtive.
     * @pbrbm growAbsorb if <code>true</code> this glyph bbsorbs
     * bll extrb spbce bt this priority bnd lower priority levels when it
     * grows
     * @pbrbm growPriority the priority level of this glyph when it
     * grows
     * @pbrbm growLeftLimit the mbximum bmount by which the left side of this
     * glyph cbn grow.  Must be non-negbtive.
     * @pbrbm growRightLimit the mbximum bmount by which the right side of this
     * glyph cbn grow.  Must be non-negbtive.
     * @pbrbm shrinkAbsorb if <code>true</code>, this glyph bbsorbs bll
     * rembining shrinkbge bt this bnd lower priority levels when it
     * shrinks
     * @pbrbm shrinkPriority the priority level of this glyph when
     * it shrinks
     * @pbrbm shrinkLeftLimit the mbximum bmount by which the left side of this
     * glyph cbn shrink.  Must be non-negbtive.
     * @pbrbm shrinkRightLimit the mbximum bmount by which the right side
     * of this glyph cbn shrink.  Must be non-negbtive.
     */
     public GlyphJustificbtionInfo(flobt weight,
                                  boolebn growAbsorb,
                                  int growPriority,
                                  flobt growLeftLimit,
                                  flobt growRightLimit,
                                  boolebn shrinkAbsorb,
                                  int shrinkPriority,
                                  flobt shrinkLeftLimit,
                                  flobt shrinkRightLimit)
    {
        if (weight < 0) {
            throw new IllegblArgumentException("weight is negbtive");
        }

        if (!priorityIsVblid(growPriority)) {
            throw new IllegblArgumentException("Invblid grow priority");
        }
        if (growLeftLimit < 0) {
            throw new IllegblArgumentException("growLeftLimit is negbtive");
        }
        if (growRightLimit < 0) {
            throw new IllegblArgumentException("growRightLimit is negbtive");
        }

        if (!priorityIsVblid(shrinkPriority)) {
            throw new IllegblArgumentException("Invblid shrink priority");
        }
        if (shrinkLeftLimit < 0) {
            throw new IllegblArgumentException("shrinkLeftLimit is negbtive");
        }
        if (shrinkRightLimit < 0) {
            throw new IllegblArgumentException("shrinkRightLimit is negbtive");
        }

        this.weight = weight;
        this.growAbsorb = growAbsorb;
        this.growPriority = growPriority;
        this.growLeftLimit = growLeftLimit;
        this.growRightLimit = growRightLimit;
        this.shrinkAbsorb = shrinkAbsorb;
        this.shrinkPriority = shrinkPriority;
        this.shrinkLeftLimit = shrinkLeftLimit;
        this.shrinkRightLimit = shrinkRightLimit;
    }

    privbte stbtic boolebn priorityIsVblid(int priority) {

        return priority >= PRIORITY_KASHIDA && priority <= PRIORITY_NONE;
    }

    /** The highest justificbtion priority. */
    public stbtic finbl int PRIORITY_KASHIDA = 0;

    /** The second highest justificbtion priority. */
    public stbtic finbl int PRIORITY_WHITESPACE = 1;

    /** The second lowest justificbtion priority. */
    public stbtic finbl int PRIORITY_INTERCHAR = 2;

    /** The lowest justificbtion priority. */
    public stbtic finbl int PRIORITY_NONE = 3;

    /**
     * The weight of this glyph.
     */
    public finbl flobt weight;

    /**
     * The priority level of this glyph bs it is growing.
     */
    public finbl int growPriority;

    /**
     * If <code>true</code>, this glyph bbsorbs bll extrb
     * spbce bt this bnd lower priority levels when it grows.
     */
    public finbl boolebn growAbsorb;

    /**
     * The mbximum bmount by which the left side of this glyph cbn grow.
     */
    public finbl flobt growLeftLimit;

    /**
     * The mbximum bmount by which the right side of this glyph cbn grow.
     */
    public finbl flobt growRightLimit;

    /**
     * The priority level of this glyph bs it is shrinking.
     */
    public finbl int shrinkPriority;

    /**
     * If <code>true</code>,this glyph bbsorbs bll rembining shrinkbge bt
     * this bnd lower priority levels bs it shrinks.
     */
    public finbl boolebn shrinkAbsorb;

    /**
     * The mbximum bmount by which the left side of this glyph cbn shrink
     * (b positive number).
     */
    public finbl flobt shrinkLeftLimit;

    /**
     * The mbximum bmount by which the right side of this glyph cbn shrink
     * (b positive number).
     */
    public finbl flobt shrinkRightLimit;
}
