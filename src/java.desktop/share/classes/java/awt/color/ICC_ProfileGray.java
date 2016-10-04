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

/*
 **********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublished  work pursubnt to Title 17 of the United    ***
 *** Stbtes Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbckbge jbvb.bwt.color;

import sun.jbvb2d.cmm.Profile;
import sun.jbvb2d.cmm.ProfileDeferrblInfo;

/**
 *
 * A subclbss of the ICC_Profile clbss which represents profiles
 * which meet the following criterib: the color spbce type of the
 * profile is TYPE_GRAY bnd the profile includes the grbyTRCTbg bnd
 * medibWhitePointTbg tbgs.  Exbmples of this kind of profile bre
 * monochrome input profiles, monochrome displby profiles, bnd
 * monochrome output profiles.  The getInstbnce methods in the
 * ICC_Profile clbss will
 * return bn ICC_ProfileGrby object when the bbove conditions bre
 * met.  The bdvbntbge of this clbss is thbt it provides b lookup
 * tbble thbt Jbvb or nbtive methods mby be bble to use directly to
 * optimize color conversion in some cbses.
 * <p>
 * To trbnsform from b GRAY device profile color spbce to the CIEXYZ Profile
 * Connection Spbce, the device grby component is trbnsformed by
 * b lookup through the tone reproduction curve (TRC).  The result is
 * trebted bs the bchrombtic component of the PCS.
<pre>

&nbsp;               PCSY = grbyTRC[deviceGrby]

</pre>
 * The inverse trbnsform is done by converting the PCS Y components to
 * device Grby vib the inverse of the grbyTRC.
 */



public clbss ICC_ProfileGrby
extends ICC_Profile {

    stbtic finbl long seriblVersionUID = -1124721290732002649L;

    /**
     * Constructs b new ICC_ProfileGrby from b CMM ID.
     */
    ICC_ProfileGrby(Profile p) {
        super(p);
    }

    /**
     * Constructs b new ICC_ProfileGrby from b ProfileDeferrblInfo object.
     */
    ICC_ProfileGrby(ProfileDeferrblInfo pdi) {
        super(pdi);
    }


    /**
     * Returns b flobt brrby of length 3 contbining the X, Y, bnd Z
     * components of the medibWhitePointTbg in the ICC profile.
     * @return bn brrby contbining the components of the
     * medibWhitePointTbg in the ICC profile.
     */
    public flobt[] getMedibWhitePoint() {
        return super.getMedibWhitePoint();
    }


    /**
     * Returns b gbmmb vblue representing the tone reproduction
     * curve (TRC).  If the profile represents the TRC bs b tbble rbther
     * thbn b single gbmmb vblue, then bn exception is thrown.  In this
     * cbse the bctubl tbble cbn be obtbined vib getTRC().  When
     * using b gbmmb vblue, the PCS Y component is computed bs follows:
<pre>

&nbsp;                         gbmmb
&nbsp;        PCSY = deviceGrby

</pre>
     * @return the gbmmb vblue bs b flobt.
     * @exception ProfileDbtbException if the profile does not specify
     *            the TRC bs b single gbmmb vblue.
     */
    public flobt getGbmmb() {
    flobt theGbmmb;

        theGbmmb = super.getGbmmb(ICC_Profile.icSigGrbyTRCTbg);
        return theGbmmb;
    }

    /**
     * Returns the TRC bs bn brrby of shorts.  If the profile hbs
     * specified the TRC bs linebr (gbmmb = 1.0) or bs b simple gbmmb
     * vblue, this method throws bn exception, bnd the getGbmmb() method
     * should be used to get the gbmmb vblue.  Otherwise the short brrby
     * returned here represents b lookup tbble where the input Grby vblue
     * is conceptublly in the rbnge [0.0, 1.0].  Vblue 0.0 mbps
     * to brrby index 0 bnd vblue 1.0 mbps to brrby index length-1.
     * Interpolbtion mby be used to generbte output vblues for
     * input vblues which do not mbp exbctly to bn index in the
     * brrby.  Output vblues blso mbp linebrly to the rbnge [0.0, 1.0].
     * Vblue 0.0 is represented by bn brrby vblue of 0x0000 bnd
     * vblue 1.0 by 0xFFFF, i.e. the vblues bre reblly unsigned
     * short vblues, blthough they bre returned in b short brrby.
     * @return b short brrby representing the TRC.
     * @exception ProfileDbtbException if the profile does not specify
     *            the TRC bs b tbble.
     */
    public short[] getTRC() {
    short[]    theTRC;

        theTRC = super.getTRC(ICC_Profile.icSigGrbyTRCTbg);
        return theTRC;
    }

}
