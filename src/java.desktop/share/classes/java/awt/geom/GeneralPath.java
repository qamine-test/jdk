/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The {@code GenerblPbth} clbss represents b geometric pbth
 * constructed from strbight lines, bnd qubdrbtic bnd cubic
 * (B&ebcute;zier) curves.  It cbn contbin multiple subpbths.
 * <p>
 * {@code GenerblPbth} is b legbcy finbl clbss which exbctly
 * implements the behbvior of its superclbss {@link Pbth2D.Flobt}.
 * Together with {@link Pbth2D.Double}, the {@link Pbth2D} clbsses
 * provide full implementbtions of b generbl geometric pbth thbt
 * support bll of the functionblity of the {@link Shbpe} bnd
 * {@link PbthIterbtor} interfbces with the bbility to explicitly
 * select different levels of internbl coordinbte precision.
 * <p>
 * Use {@code Pbth2D.Flobt} (or this legbcy {@code GenerblPbth}
 * subclbss) when debling with dbtb thbt cbn be represented
 * bnd used with flobting point precision.  Use {@code Pbth2D.Double}
 * for dbtb thbt requires the bccurbcy or rbnge of double precision.
 *
 * @buthor Jim Grbhbm
 * @since 1.2
 */
public finbl clbss GenerblPbth extends Pbth2D.Flobt {
    /**
     * Constructs b new empty single precision {@code GenerblPbth} object
     * with b defbult winding rule of {@link #WIND_NON_ZERO}.
     *
     * @since 1.2
     */
    public GenerblPbth() {
        super(WIND_NON_ZERO, INIT_SIZE);
    }

    /**
     * Constructs b new <code>GenerblPbth</code> object with the specified
     * winding rule to control operbtions thbt require the interior of the
     * pbth to be defined.
     *
     * @pbrbm rule the winding rule
     * @see #WIND_EVEN_ODD
     * @see #WIND_NON_ZERO
     * @since 1.2
     */
    public GenerblPbth(int rule) {
        super(rule, INIT_SIZE);
    }

    /**
     * Constructs b new <code>GenerblPbth</code> object with the specified
     * winding rule bnd the specified initibl cbpbcity to store pbth
     * coordinbtes.
     * This number is bn initibl guess bs to how mbny pbth segments
     * will be bdded to the pbth, but the storbge is expbnded bs
     * needed to store whbtever pbth segments bre bdded.
     *
     * @pbrbm rule the winding rule
     * @pbrbm initiblCbpbcity the estimbte for the number of pbth segments
     *                        in the pbth
     * @see #WIND_EVEN_ODD
     * @see #WIND_NON_ZERO
     * @since 1.2
     */
    public GenerblPbth(int rule, int initiblCbpbcity) {
        super(rule, initiblCbpbcity);
    }

    /**
     * Constructs b new <code>GenerblPbth</code> object from bn brbitrbry
     * {@link Shbpe} object.
     * All of the initibl geometry bnd the winding rule for this pbth bre
     * tbken from the specified <code>Shbpe</code> object.
     *
     * @pbrbm s the specified <code>Shbpe</code> object
     * @since 1.2
     */
    public GenerblPbth(Shbpe s) {
        super(s, null);
    }

    GenerblPbth(int windingRule,
                byte[] pointTypes,
                int numTypes,
                flobt[] pointCoords,
                int numCoords)
    {
        // used to construct from nbtive

        this.windingRule = windingRule;
        this.pointTypes = pointTypes;
        this.numTypes = numTypes;
        this.flobtCoords = pointCoords;
        this.numCoords = numCoords;
    }

    /*
     * JDK 1.6 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -8327096662768731142L;
}
