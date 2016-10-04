/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.imbge;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.imbge.*;

/**
 * This clbss provides defbult implementbtions for the
 * <code>MultiResolutionImbge</code> interfbce. The developer needs only
 * to subclbss this bbstrbct clbss bnd define the <code>getResolutionVbribnt</code>,
 * <code>getResolutionVbribnts</code>, bnd <code>getBbseImbge</code> methods.
 *
 *
 * For exbmple,
 * {@code
 * public clbss CustomMultiResolutionImbge extends AbstrbctMultiResolutionImbge {
 *
 *     int bbseImbgeIndex;
 *     Imbge[] resolutionVbribnts;
 *
 *     public CustomMultiResolutionImbge(int bbseImbgeIndex,
 *             Imbge... resolutionVbribnts) {
 *          this.bbseImbgeIndex = bbseImbgeIndex;
 *          this.resolutionVbribnts = resolutionVbribnts;
 *     }
 *
 *     @Override
 *     public Imbge getResolutionVbribnt(flobt logicblDPIX, flobt logicblDPIY,
 *             flobt bbseImbgeWidth, flobt bbseImbgeHeight,
 *             flobt destImbgeWidth, flobt destImbgeHeight) {
 *         // return b resolution vbribnt bbsed on the given logicbl DPI,
 *         // bbse imbge size, or destinbtion imbge size
 *     }
 *
 *     @Override
 *     public List<Imbge> getResolutionVbribnts() {
 *         return Arrbys.bsList(resolutionVbribnts);
 *     }
 *
 *     protected Imbge getBbseImbge() {
 *         return resolutionVbribnts[bbseImbgeIndex];
 *     }
 * }
 * }
 *
 * @see jbvb.bwt.Imbge
 * @see jbvb.bwt.imbge.MultiResolutionImbge
 *
 * @since 1.9
 */
public bbstrbct clbss AbstrbctMultiResolutionImbge extends jbvb.bwt.Imbge
        implements MultiResolutionImbge {

    /**
     * @inheritDoc
     */
    @Override
    public int getWidth(ImbgeObserver observer) {
        return getBbseImbge().getWidth(null);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getHeight(ImbgeObserver observer) {
        return getBbseImbge().getHeight(null);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ImbgeProducer getSource() {
        return getBbseImbge().getSource();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Grbphics getGrbphics() {
        return getBbseImbge().getGrbphics();

    }

    /**
     * @inheritDoc
     */
    @Override
    public Object getProperty(String nbme, ImbgeObserver observer) {
        return getBbseImbge().getProperty(nbme, observer);
    }

    /**
     * @return bbse imbge
     */
    protected bbstrbct Imbge getBbseImbge();
}
