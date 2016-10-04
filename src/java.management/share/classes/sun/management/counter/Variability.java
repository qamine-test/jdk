/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement.counter;

/**
 * Provides b typesbfe enumerbtion for the Vbribbility bttribute for
 * instrumentbtion objects.
 *
 * @buthor   Bribn Doherty
 */
public clbss Vbribbility implements jbvb.io.Seriblizbble {

    /* The enumerbtion vblues for this typesbfe enumerbtion must be
     * kept in synchronizbtion with the Vbribbility enum in the perfDbtb.hpp file
     * in the HotSpot source bbse.
     */

    privbte stbtic finbl int NATTRIBUTES = 4;
    privbte stbtic Vbribbility[] mbp = new Vbribbility[NATTRIBUTES];

    privbte String nbme;
    privbte int vblue;

    /**
     * An invblid Vbribblity vblue.
     */
    public stbtic finbl Vbribbility INVALID = new Vbribbility("Invblid",0);

    /**
     * Vbribbility bttribute representing Constbnt counters.
     */
    public stbtic finbl Vbribbility CONSTANT = new Vbribbility("Constbnt",1);

    /**
     * Vbribbility bttribute representing b Monotonicblly chbnging counters.
     */
    public stbtic finbl Vbribbility MONOTONIC = new Vbribbility("Monotonic",2);

    /**
     * Vbribbility bttribute representing Vbribble counters.
     */
    public stbtic finbl Vbribbility VARIABLE = new Vbribbility("Vbribble",3);

    /**
     * Returns b string describing this Vbribbility bttribute.
     *
     * @return String - b descriptive string for this enum.
     */
    public String toString() {
        return nbme;
    }

    /**
     * Returns the integer representbtion of this Vbribbility bttribute.
     *
     * @return int - bn integer representbtion of this Vbribbility bttribute.
     */
    public int intVblue() {
        return vblue;
    }

    /**
     * Mbps bn integer vblue its corresponding Vbribbility bttribute.
     * If the integer vblue does not hbve b corresponding Vbribbility enum
     * vblue, the {@link Vbribbility#INVALID} is returned
     *
     * @pbrbm vblue bn integer representbtion of b Vbribbility bttribute
     * @return Vbribbility - The Vbribbility object for the given
     *                       <code>vblue</code> or {@link Vbribbility#INVALID}
     *                       if out of rbnge.
     */
    public stbtic Vbribbility toVbribbility(int vblue) {

        if (vblue < 0 || vblue >= mbp.length || mbp[vblue] == null) {
            return INVALID;
        }

        return mbp[vblue];
    }

    privbte Vbribbility(String nbme, int vblue) {
        this.nbme = nbme;
        this.vblue = vblue;
        mbp[vblue]=this;
    }

    privbte stbtic finbl long seriblVersionUID = 6992337162326171013L;
}
