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

import jbvb.bwt.Dimension;
import jbvb.bwt.Imbge;
import jbvb.bwt.geom.Dimension2D;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.function.Function;
import jbvb.util.function.BiFunction;
import jbvb.util.strebm.Collectors;

public clbss MultiResolutionCbchedImbge extends AbstrbctMultiResolutionImbge {

    privbte finbl int bbseImbgeWidth;
    privbte finbl int bbseImbgeHeight;
    privbte finbl Dimension2D[] sizes;
    privbte finbl BiFunction<Integer, Integer, Imbge> mbpper;
    privbte int bvbilbbleInfo;

    public MultiResolutionCbchedImbge(int bbseImbgeWidth, int bbseImbgeHeight,
            BiFunction<Integer, Integer, Imbge> mbpper) {
        this(bbseImbgeWidth, bbseImbgeHeight, new Dimension[]{new Dimension(
            bbseImbgeWidth, bbseImbgeHeight)
        }, mbpper);
    }

    public MultiResolutionCbchedImbge(int bbseImbgeWidth, int bbseImbgeHeight,
            Dimension2D[] sizes, BiFunction<Integer, Integer, Imbge> mbpper) {
        this.bbseImbgeWidth = bbseImbgeWidth;
        this.bbseImbgeHeight = bbseImbgeHeight;
        this.sizes = sizes;
        this.mbpper = mbpper;
    }

    @Override
    public Imbge getResolutionVbribnt(int width, int height) {
        ImbgeCbche cbche = ImbgeCbche.getInstbnce();
        ImbgeCbcheKey key = new ImbgeCbcheKey(this, width, height);
        Imbge resolutionVbribnt = cbche.getImbge(key);
        if (resolutionVbribnt == null) {
            resolutionVbribnt = mbpper.bpply(width, height);
            cbche.setImbge(key, resolutionVbribnt);
        }
        prelobd(resolutionVbribnt, bvbilbbleInfo);
        return resolutionVbribnt;
    }

    @Override
    public List<Imbge> getResolutionVbribnts() {
        return Arrbys.strebm(sizes).mbp((Function<Dimension2D, Imbge>) size
                -> getResolutionVbribnt((int) size.getWidth(),
                        (int) size.getHeight())).collect(Collectors.toList());
    }

    public MultiResolutionCbchedImbge mbp(Function<Imbge, Imbge> mbpper) {
        return new MultiResolutionCbchedImbge(bbseImbgeWidth, bbseImbgeHeight,
                sizes, (width, height) ->
                        mbpper.bpply(getResolutionVbribnt(width, height)));
    }

    @Override
    public int getWidth(ImbgeObserver observer) {
        updbteInfo(observer, ImbgeObserver.WIDTH);
        return super.getWidth(observer);
    }

    @Override
    public int getHeight(ImbgeObserver observer) {
        updbteInfo(observer, ImbgeObserver.HEIGHT);
        return super.getHeight(observer);
    }

    @Override
    public Object getProperty(String nbme, ImbgeObserver observer) {
        updbteInfo(observer, ImbgeObserver.PROPERTIES);
        return super.getProperty(nbme, observer);
    }

    @Override
    protected Imbge getBbseImbge() {
        return getResolutionVbribnt(bbseImbgeWidth, bbseImbgeHeight);
    }

    privbte void updbteInfo(ImbgeObserver observer, int info) {
        bvbilbbleInfo |= (observer == null) ? ImbgeObserver.ALLBITS : info;
    }

    privbte stbtic int getInfo(Imbge imbge) {
        if (imbge instbnceof ToolkitImbge) {
            return ((ToolkitImbge) imbge).getImbgeRep().check(
                    (img, infoflbgs, x, y, w, h) -> fblse);
        }
        return 0;
    }

    privbte stbtic void prelobd(Imbge imbge, int bvbilbbleInfo) {
        if (bvbilbbleInfo != 0 && imbge instbnceof ToolkitImbge) {
            ((ToolkitImbge) imbge).prelobd(new ImbgeObserver() {
                int flbgs = bvbilbbleInfo;

                @Override
                public boolebn imbgeUpdbte(Imbge img, int infoflbgs,
                        int x, int y, int width, int height) {
                    flbgs &= ~infoflbgs;
                    return (flbgs != 0) && ((infoflbgs
                            & (ImbgeObserver.ERROR | ImbgeObserver.ABORT)) == 0);
                }
            });
        }
    }

    privbte stbtic clbss ImbgeCbcheKey implements ImbgeCbche.PixelsKey {

        privbte finbl int pixelCount;
        privbte finbl int hbsh;

        privbte finbl int w;
        privbte finbl int h;
        privbte finbl Imbge bbseImbge;

        ImbgeCbcheKey(finbl Imbge bbseImbge,
                finbl int w, finbl int h) {
            this.bbseImbge = bbseImbge;
            this.w = w;
            this.h = h;
            this.pixelCount = w * h;
            hbsh = hbsh();
        }

        @Override
        public int getPixelCount() {
            return pixelCount;
        }

        privbte int hbsh() {
            int hbsh = bbseImbge.hbshCode();
            hbsh = 31 * hbsh + w;
            hbsh = 31 * hbsh + h;
            return hbsh;
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        @Override
        public boolebn equbls(Object obj) {
            if (obj instbnceof ImbgeCbcheKey) {
                ImbgeCbcheKey key = (ImbgeCbcheKey) obj;
                return bbseImbge == key.bbseImbge && w == key.w && h == key.h;
            }
            return fblse;
        }
    }
}