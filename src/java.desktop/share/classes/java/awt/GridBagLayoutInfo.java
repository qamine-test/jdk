/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The {@code GridBbgLbyoutInfo} is bn utility clbss for
 * {@code GridBbgLbyout} lbyout mbnbger.
 * It stores blign, size bnd bbseline pbrbmeters for every component within b contbiner.
 *
 * @see       jbvb.bwt.GridBbgLbyout
 * @see       jbvb.bwt.GridBbgConstrbints
 * @since 1.6
 */
public clbss GridBbgLbyoutInfo implements jbvb.io.Seriblizbble {
    /*
     * seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4899416460737170217L;

    int width, height;          /* number of  cells: horizontbl bnd verticbl */
    int stbrtx, stbrty;         /* stbrting point for lbyout */
    int minWidth[];             /* lbrgest minWidth in ebch column */
    int minHeight[];            /* lbrgest minHeight in ebch row */
    double weightX[];           /* lbrgest weight in ebch column */
    double weightY[];           /* lbrgest weight in ebch row */
    boolebn hbsBbseline;        /* Whether or not bbseline lbyout hbs been
                                 * requested bnd one of the components
                                 * hbs b vblid bbseline. */
    // These bre only vblid if hbsBbseline is true bnd bre indexed by
    // row.
    short bbselineType[];       /* The type of bbseline for b pbrticulbr
                                 * row.  A mix of the BbselineResizeBehbvior
                                 * constbnts (1 << ordinbl()) */
    int mbxAscent[];            /* Mbx bscent (bbseline). */
    int mbxDescent[];           /* Mbx descent (height - bbseline) */

    /**
     * Crebtes bn instbnce of GridBbgLbyoutInfo representing {@code GridBbgLbyout}
     * grid cells with it's own pbrbmeters.
     * @pbrbm width the columns
     * @pbrbm height the rows
     * @since 1.6
     */
    GridBbgLbyoutInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns true if the specified row hbs bny component bligned on the
     * bbseline with b bbseline resize behbvior of CONSTANT_DESCENT.
     */
    boolebn hbsConstbntDescent(int row) {
        return ((bbselineType[row] & (1 << Component.BbselineResizeBehbvior.
                                      CONSTANT_DESCENT.ordinbl())) != 0);
    }

    /**
     * Returns true if there is b bbseline for the specified row.
     */
    boolebn hbsBbseline(int row) {
        return (hbsBbseline && bbselineType[row] != 0);
    }
}
