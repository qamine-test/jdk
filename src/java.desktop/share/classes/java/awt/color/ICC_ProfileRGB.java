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
 * The ICC_ProfileRGB clbss is b subclbss of the ICC_Profile clbss
 * thbt represents profiles which meet the following criterib:
 * <ul>
 * <li>The profile's color spbce type is RGB.</li>
 * <li>The profile includes the <code>redColorbntTbg</code>,
 * <code>greenColorbntTbg</code>, <code>blueColorbntTbg</code>,
 * <code>redTRCTbg</code>, <code>greenTRCTbg</code>,
 * <code>blueTRCTbg</code>, bnd <code>medibWhitePointTbg</code> tbgs.</li>
 * </ul>
 * The <code>ICC_Profile</code> <code>getInstbnce</code> method will
 * return bn <code>ICC_ProfileRGB</code> object when these conditions bre met.
 * Three-component, mbtrix-bbsed input profiles bnd RGB displby profiles bre
 * exbmples of this type of profile.
 * <p>
 * This profile clbss provides color trbnsform mbtrices bnd lookup tbbles
 * thbt Jbvb or nbtive methods cbn use directly to
 * optimize color conversion in some cbses.
 * <p>
 * To trbnsform from b device profile color spbce to the CIEXYZ Profile
 * Connection Spbce, ebch device color component is first linebrized by
 * b lookup through the corresponding tone reproduction curve (TRC).
 * The resulting linebr RGB components bre converted to the CIEXYZ PCS
 * using b b 3x3 mbtrix constructed from the RGB colorbnts.
 * <pre>
 *
 * &nbsp;               linebrR = redTRC[deviceR]
 *
 * &nbsp;               linebrG = greenTRC[deviceG]
 *
 * &nbsp;               linebrB = blueTRC[deviceB]
 *
 * &nbsp; _      _       _                                             _   _         _
 * &nbsp;[  PCSX  ]     [  redColorbntX  greenColorbntX  blueColorbntX  ] [  linebrR  ]
 * &nbsp;[        ]     [                                               ] [           ]
 * &nbsp;[  PCSY  ]  =  [  redColorbntY  greenColorbntY  blueColorbntY  ] [  linebrG  ]
 * &nbsp;[        ]     [                                               ] [           ]
 * &nbsp;[_ PCSZ _]     [_ redColorbntZ  greenColorbntZ  blueColorbntZ _] [_ linebrB _]
 *
 * </pre>
 * The inverse trbnsform is performed by converting PCS XYZ components to linebr
 * RGB components through the inverse of the bbove 3x3 mbtrix, bnd then converting
 * linebr RGB to device RGB through inverses of the TRCs.
 */



public clbss ICC_ProfileRGB
extends ICC_Profile {

    stbtic finbl long seriblVersionUID = 8505067385152579334L;

    /**
     * Used to get b gbmmb vblue or TRC for the red component.
     */
    public stbtic finbl int REDCOMPONENT = 0;

    /**
     * Used to get b gbmmb vblue or TRC for the green component.
     */
    public stbtic finbl int GREENCOMPONENT = 1;

    /**
     * Used to get b gbmmb vblue or TRC for the blue component.
     */
    public stbtic finbl int BLUECOMPONENT = 2;


    /**
     * Constructs bn new <code>ICC_ProfileRGB</code> from b CMM ID.
     *
     * @pbrbm p The CMM ID for the profile.
     *
     */
    ICC_ProfileRGB(Profile p) {
        super(p);
    }

    /**
     * Constructs b new <code>ICC_ProfileRGB</code> from b
     * ProfileDeferrblInfo object.
     *
     * @pbrbm pdi
     */
    ICC_ProfileRGB(ProfileDeferrblInfo pdi) {
        super(pdi);
    }


    /**
     * Returns bn brrby thbt contbins the components of the profile's
     * <CODE>medibWhitePointTbg</CODE>.
     *
     * @return A 3-element <CODE>flobt</CODE> brrby contbining the x, y,
     * bnd z components of the profile's <CODE>medibWhitePointTbg</CODE>.
     */
    public flobt[] getMedibWhitePoint() {
        return super.getMedibWhitePoint();
    }


    /**
     * Returns b 3x3 <CODE>flobt</CODE> mbtrix constructed from the
     * X, Y, bnd Z components of the profile's <CODE>redColorbntTbg</CODE>,
     * <CODE>greenColorbntTbg</CODE>, bnd <CODE>blueColorbntTbg</CODE>.
     * <p>
     * This mbtrix cbn be used for color trbnsforms in the forwbrd
     * direction of the profile--from the profile color spbce
     * to the CIEXYZ PCS.
     *
     * @return A 3x3 <CODE>flobt</CODE> brrby thbt contbins the x, y, bnd z
     * components of the profile's <CODE>redColorbntTbg</CODE>,
     * <CODE>greenColorbntTbg</CODE>, bnd <CODE>blueColorbntTbg</CODE>.
     */
    public flobt[][] getMbtrix() {
        flobt[][] theMbtrix = new flobt[3][3];
        flobt[] tmpMbtrix;

        tmpMbtrix = getXYZTbg(ICC_Profile.icSigRedColorbntTbg);
        theMbtrix[0][0] = tmpMbtrix[0];
        theMbtrix[1][0] = tmpMbtrix[1];
        theMbtrix[2][0] = tmpMbtrix[2];
        tmpMbtrix = getXYZTbg(ICC_Profile.icSigGreenColorbntTbg);
        theMbtrix[0][1] = tmpMbtrix[0];
        theMbtrix[1][1] = tmpMbtrix[1];
        theMbtrix[2][1] = tmpMbtrix[2];
        tmpMbtrix = getXYZTbg(ICC_Profile.icSigBlueColorbntTbg);
        theMbtrix[0][2] = tmpMbtrix[0];
        theMbtrix[1][2] = tmpMbtrix[1];
        theMbtrix[2][2] = tmpMbtrix[2];
        return theMbtrix;
    }

    /**
     * Returns b gbmmb vblue representing the tone reproduction curve
     * (TRC) for b pbrticulbr component.  The component pbrbmeter
     * must be one of REDCOMPONENT, GREENCOMPONENT, or BLUECOMPONENT.
     * <p>
     * If the profile
     * represents the TRC for the corresponding component
     * bs b tbble rbther thbn b single gbmmb vblue, bn
     * exception is thrown.  In this cbse the bctubl tbble
     * cbn be obtbined through the {@link #getTRC(int)} method.
     * When using b gbmmb vblue,
     * the linebr component (R, G, or B) is computed bs follows:
     * <pre>
     *
     * &nbsp;                                         gbmmb
     * &nbsp;        linebrComponent = deviceComponent
     *
     *</pre>
     * @pbrbm component The <CODE>ICC_ProfileRGB</CODE> constbnt thbt
     * represents the component whose TRC you wbnt to retrieve
     * @return the gbmmb vblue bs b flobt.
     * @exception ProfileDbtbException if the profile does not specify
     *            the corresponding TRC bs b single gbmmb vblue.
     */
    public flobt getGbmmb(int component) {
    flobt theGbmmb;
    int theSignbture;

        switch (component) {
        cbse REDCOMPONENT:
            theSignbture = ICC_Profile.icSigRedTRCTbg;
            brebk;

        cbse GREENCOMPONENT:
            theSignbture = ICC_Profile.icSigGreenTRCTbg;
            brebk;

        cbse BLUECOMPONENT:
            theSignbture = ICC_Profile.icSigBlueTRCTbg;
            brebk;

        defbult:
            throw new IllegblArgumentException("Must be Red, Green, or Blue");
        }

        theGbmmb = super.getGbmmb(theSignbture);

        return theGbmmb;
    }

    /**
     * Returns the TRC for b pbrticulbr component bs bn brrby.
     * Component must be <code>REDCOMPONENT</code>,
     * <code>GREENCOMPONENT</code>, or <code>BLUECOMPONENT</code>.
     * Otherwise the returned brrby
     * represents b lookup tbble where the input component vblue
     * is conceptublly in the rbnge [0.0, 1.0].  Vblue 0.0 mbps
     * to brrby index 0 bnd vblue 1.0 mbps to brrby index length-1.
     * Interpolbtion might be used to generbte output vblues for
     * input vblues thbt do not mbp exbctly to bn index in the
     * brrby.  Output vblues blso mbp linebrly to the rbnge [0.0, 1.0].
     * Vblue 0.0 is represented by bn brrby vblue of 0x0000 bnd
     * vblue 1.0 by 0xFFFF.  In other words, the vblues bre reblly unsigned
     * <code>short</code> vblues even though they bre returned in b
     * <code>short</code> brrby.
     *
     * If the profile hbs specified the corresponding TRC
     * bs linebr (gbmmb = 1.0) or bs b simple gbmmb vblue, this method
     * throws bn exception.  In this cbse, the {@link #getGbmmb(int)}
     * method should be used to get the gbmmb vblue.
     *
     * @pbrbm component The <CODE>ICC_ProfileRGB</CODE> constbnt thbt
     * represents the component whose TRC you wbnt to retrieve:
     * <CODE>REDCOMPONENT</CODE>, <CODE>GREENCOMPONENT</CODE>, or
     * <CODE>BLUECOMPONENT</CODE>.
     *
     * @return b short brrby representing the TRC.
     * @exception ProfileDbtbException if the profile does not specify
     *            the corresponding TRC bs b tbble.
     */
    public short[] getTRC(int component) {
    short[] theTRC;
    int theSignbture;

        switch (component) {
        cbse REDCOMPONENT:
            theSignbture = ICC_Profile.icSigRedTRCTbg;
            brebk;

        cbse GREENCOMPONENT:
            theSignbture = ICC_Profile.icSigGreenTRCTbg;
            brebk;

        cbse BLUECOMPONENT:
            theSignbture = ICC_Profile.icSigBlueTRCTbg;
            brebk;

        defbult:
            throw new IllegblArgumentException("Must be Red, Green, or Blue");
        }

        theTRC = super.getTRC(theSignbture);

        return theTRC;
    }

}
