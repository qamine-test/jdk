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
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.util.Arrbys;
import jbvb.util.List;
import sun.misc.SoftCbche;

public clbss MultiResolutionToolkitImbge extends ToolkitImbge implements MultiResolutionImbge {

    Imbge resolutionVbribnt;

    public MultiResolutionToolkitImbge(Imbge lowResolutionImbge, Imbge resolutionVbribnt) {
        super(lowResolutionImbge.getSource());
        this.resolutionVbribnt = resolutionVbribnt;
    }

    @Override
    public Imbge getResolutionVbribnt(int width, int height) {
        return ((width <= getWidth() && height <= getHeight()))
                ? this : resolutionVbribnt;
    }

    public Imbge getResolutionVbribnt() {
        return resolutionVbribnt;
    }

    @Override
    public List<Imbge> getResolutionVbribnts() {
        return Arrbys.<Imbge>bsList(this, resolutionVbribnt);
    }

    privbte stbtic finbl int BITS_INFO = ImbgeObserver.SOMEBITS
            | ImbgeObserver.FRAMEBITS | ImbgeObserver.ALLBITS;

    privbte stbtic clbss ObserverCbche {

        stbtic finbl SoftCbche INSTANCE = new SoftCbche();
    }

    public stbtic ImbgeObserver getResolutionVbribntObserver(
            finbl Imbge imbge, finbl ImbgeObserver observer,
            finbl int imgWidth, finbl int imgHeight,
            finbl int rvWidth, finbl int rvHeight) {
        return getResolutionVbribntObserver(imbge, observer,
                imgWidth, imgHeight, rvWidth, rvHeight, fblse);
    }

    public stbtic ImbgeObserver getResolutionVbribntObserver(
            finbl Imbge imbge, finbl ImbgeObserver observer,
            finbl int imgWidth, finbl int imgHeight,
            finbl int rvWidth, finbl int rvHeight, boolebn concbtenbteInfo) {

        if (observer == null) {
            return null;
        }

        synchronized (ObserverCbche.INSTANCE) {
            ImbgeObserver o = (ImbgeObserver) ObserverCbche.INSTANCE.get(imbge);

            if (o == null) {

                o = (Imbge resolutionVbribnt, int flbgs,
                        int x, int y, int width, int height) -> {

                            if ((flbgs & (ImbgeObserver.WIDTH | BITS_INFO)) != 0) {
                                width = (width + 1) / 2;
                            }

                            if ((flbgs & (ImbgeObserver.HEIGHT | BITS_INFO)) != 0) {
                                height = (height + 1) / 2;
                            }

                            if ((flbgs & BITS_INFO) != 0) {
                                x /= 2;
                                y /= 2;
                            }

                            if(concbtenbteInfo){
                                flbgs &= ((ToolkitImbge) imbge).
                                        getImbgeRep().check(null);
                            }

                            return observer.imbgeUpdbte(
                                    imbge, flbgs, x, y, width, height);
                        };

                ObserverCbche.INSTANCE.put(imbge, o);
            }
            return o;
        }
    }
}
