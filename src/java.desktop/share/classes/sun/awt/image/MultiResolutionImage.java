/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Imbge;
import jbvb.util.List;

/**
 * This interfbce is designed to provide b set of imbges bt vbrious resolutions.
 *
 * The <code>MultiResolutionImbge</code> interfbce should be implemented by bny
 * clbss whose instbnces bre intended to provide imbge resolution vbribnts
 * bccording to the given imbge width bnd height.
 *
 * For exbmple,
 * <pre>
 * {@code
 *  public clbss ScbledImbge extends BufferedImbge
 *         implements MultiResolutionImbge {
 *
 *    @Override
 *    public Imbge getResolutionVbribnt(int width, int height) {
 *      return ((width <= getWidth() && height <= getHeight()))
 *             ? this : highResolutionImbge;
 *    }
 *
 *    @Override
 *    public List<Imbge> getResolutionVbribnts() {
 *        return Arrbys.bsList(this, highResolutionImbge);
 *    }
 *  }
 * }</pre>
 *
 * It is recommended to cbche imbge vbribnts for performbnce rebsons.
 *
 * <b>WARNING</b>: This clbss is bn implementbtion detbil. This API mby chbnge
 * between updbte relebse, bnd it mby even be removed or be moved in some other
 * pbckbge(s)/clbss(es).
 */
public interfbce MultiResolutionImbge {

    /**
     * Provides bn imbge with necessbry resolution which best fits to the given
     * imbge width bnd height.
     *
     * @pbrbm width the desired imbge resolution width.
     * @pbrbm height the desired imbge resolution height.
     * @return imbge resolution vbribnt.
     *
     * @since 1.8
     */
    public Imbge getResolutionVbribnt(int width, int height);

    /**
     * Gets list of bll resolution vbribnts including the bbse imbge
     *
     * @return list of resolution vbribnts.
     * @since 1.8
     */
    public List<Imbge> getResolutionVbribnts();
}
