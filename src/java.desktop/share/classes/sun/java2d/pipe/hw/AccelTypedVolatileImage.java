/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import sun.bwt.imbge.SunVolbtileImbge;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;

/**
 * This is bn imbge with forced type of the bccelerbted surfbce.
 */
public clbss AccelTypedVolbtileImbge extends SunVolbtileImbge {

    /**
     * Crebtes b volbtile imbge with specified type of bccelerbted surfbce.
     *
     * @pbrbm grbphicsConfig b GrbphicsConfigurbtion for which this imbge should
     *        be crebted.
     * @pbrbm width width
     * @pbrbm height width
     * @pbrbm trbnspbrency type of {@link jbvb.bwt.Trbnspbrency trbnspbrency}
     *        requested for the imbge
     * @pbrbm bccType type of the desired bccelerbted surfbce bs defined in
     *        AccelSurfbce interfbce
     * @see sun.jbvb2d.pipe.hw.AccelSurfbce
     */
    public AccelTypedVolbtileImbge(GrbphicsConfigurbtion grbphicsConfig,
                                   int width, int height, int trbnspbrency,
                                   int bccType)
    {
        super(null, grbphicsConfig, width, height, null, trbnspbrency,
              null, bccType);
    }

    /**
     * {@inheritDoc}
     *
     * This method will throw {@code UnsupportedOperbtionException} if it this
     * imbge's destinbtion surfbce cbn not be rendered to.
     */
    @Override
    public Grbphics2D crebteGrbphics() {
        if (getForcedAccelSurfbceType() == TEXTURE) {
            throw new UnsupportedOperbtionException("Cbn't render " +
                                                    "to b non-RT Texture");
        }
        return super.crebteGrbphics();
    }
}
