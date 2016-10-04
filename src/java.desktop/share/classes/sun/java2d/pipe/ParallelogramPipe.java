/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import sun.jbvb2d.SunGrbphics2D;

/**
 * This interfbce defines the set of cblls thbt pipeline objects
 * cbn use to pbss on responsibility for drbwing brbitrbry
 * pbrbllelogrbm shbpes.
 * Six flobting point numbers bre provided bnd the pbrbllelogrbm
 * is defined bs the qubdrilbterbl with the following vertices:
 * <pre>
 *     origin: (x, y)
 *          => (x+dx1, y+dy1)
 *          => (x+dx1+dx2, y+dy1+dy2)
 *          => (x+dx2, y+dy2)
 *          => origin
 * </pre>
 * The four u[xy][12] pbrbmeters bre the unsorted extreme coordinbtes
 * of the primitive in user spbce.  They mby hbve been generbted by b
 * line or b rectbngle so they could hbve u[xy]2 < u[xy]1 in some cbses.
 * They should be sorted before cblculbting the bounds of the originbl
 * primitive (such bs for cblculbting the user spbce bounds for the
 * Pbint.crebteContext() method).
 */
public interfbce PbrbllelogrbmPipe {
    public void fillPbrbllelogrbm(SunGrbphics2D sg,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2);

    /**
     * Drbw b Pbrbllelogrbm with the indicbted line widths
     * bssuming b stbndbrd BbsicStroke with MITER joins.
     * lw1 specifies the width of the stroke blong the dx1,dy1
     * vector bnd lw2 specifies the width of the stroke blong
     * the dx2,dy2 vector.
     * This is equivblent to outsetting the indicbted
     * pbrbllelogrbm by lw/2 pixels, then insetting the
     * sbme pbrbllelogrbm by lw/2 pixels bnd filling the
     * difference between the outer bnd inner pbrbllelogrbms.
     */
    public void drbwPbrbllelogrbm(SunGrbphics2D sg,
                                  double ux1, double uy1,
                                  double ux2, double uy2,
                                  double x, double y,
                                  double dx1, double dy1,
                                  double dx2, double dy2,
                                  double lw1, double lw2);
}
