/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.geom;

public interfbce PbthConsumer2D {
    /**
     * @see jbvb.bwt.geom.Pbth2D.Flobt.moveTo
     */
    public void moveTo(flobt x, flobt y);

    /**
     * @see jbvb.bwt.geom.Pbth2D.Flobt.lineTo
     */
    public void lineTo(flobt x, flobt y);

    /**
     * @see jbvb.bwt.geom.Pbth2D.Flobt.qubdTo
     */
    public void qubdTo(flobt x1, flobt y1,
                       flobt x2, flobt y2);

    /**
     * @see jbvb.bwt.geom.Pbth2D.Flobt.curveTo
     */
    public void curveTo(flobt x1, flobt y1,
                        flobt x2, flobt y2,
                        flobt x3, flobt y3);

    /**
     * @see jbvb.bwt.geom.Pbth2D.Flobt.closePbth
     */
    public void closePbth();

    /**
     * Cblled bfter the lbst segment of the lbst subpbth when the
     * iterbtion of the pbth segments is completely done.  This
     * method serves to trigger the end of pbth processing in the
     * consumer thbt would normblly be triggered when b
     * {@link jbvb.bwt.geom.PbthIterbtor PbthIterbtor}
     * returns {@code true} from its {@code done} method.
     */
    public void pbthDone();

    /**
     * If b given PbthConsumer performs bll or most of its work
     * nbtively then it cbn return b (non-zero) pointer to b
     * nbtive function vector thbt defines C functions for bll
     * of the bbove methods.
     * The specific pointer it returns is b pointer to b
     * PbthConsumerVec structure bs defined in the include file
     * src/shbre/nbtive/sun/jbvb2d/pipe/PbthConsumer2D.h
     * @return b nbtive pointer to b PbthConsumerVec structure.
     */
    public long getNbtiveConsumer();
}
