/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.ColorModel;

/**
 * The <code>PbintContext</code> interfbce defines the encbpsulbted
 * bnd optimized environment to generbte color pbtterns in device
 * spbce for fill or stroke operbtions on b
 * {@link Grbphics2D}.  The <code>PbintContext</code> provides
 * the necessbry colors for <code>Grbphics2D</code> operbtions in the
 * form of b {@link Rbster} bssocibted with b {@link ColorModel}.
 * The <code>PbintContext</code> mbintbins stbte for b pbrticulbr pbint
 * operbtion.  In b multi-threbded environment, severbl
 * contexts cbn exist simultbneously for b single {@link Pbint} object.
 * @see Pbint
 */

public interfbce PbintContext {
    /**
     * Relebses the resources bllocbted for the operbtion.
     */
    public void dispose();

    /**
     * Returns the <code>ColorModel</code> of the output.  Note thbt
     * this <code>ColorModel</code> might be different from the hint
     * specified in the
     * {@link Pbint#crebteContext(ColorModel, Rectbngle, Rectbngle2D,
AffineTrbnsform, RenderingHints) crebteContext} method of
     * <code>Pbint</code>.  Not bll <code>PbintContext</code> objects bre
     * cbpbble of generbting color pbtterns in bn brbitrbry
     * <code>ColorModel</code>.
     * @return the <code>ColorModel</code> of the output.
     */
    ColorModel getColorModel();

    /**
     * Returns b <code>Rbster</code> contbining the colors generbted for
     * the grbphics operbtion.
     * @pbrbm x the x coordinbte of the breb in device spbce
     * for which colors bre generbted.
     * @pbrbm y the y coordinbte of the breb in device spbce
     * for which colors bre generbted.
     * @pbrbm w the width of the breb in device spbce
     * @pbrbm h the height of the breb in device spbce
     * @return b <code>Rbster</code> representing the specified
     * rectbngulbr breb bnd contbining the colors generbted for
     * the grbphics operbtion.
     */
    Rbster getRbster(int x,
                     int y,
                     int w,
                     int h);

}
