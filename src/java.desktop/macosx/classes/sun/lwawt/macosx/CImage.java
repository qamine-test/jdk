/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.geom.Dimension2D;
import jbvb.bwt.imbge.*;

import jbvb.util.Arrbys;
import jbvb.util.List;
import sun.bwt.imbge.MultiResolutionImbge;
import sun.bwt.imbge.MultiResolutionCbchedImbge;

import sun.bwt.imbge.SunWritbbleRbster;

public clbss CImbge extends CFRetbinedResource {
    privbte stbtic nbtive long nbtiveCrebteNSImbgeFromArrby(int[] buffer, int w, int h);
    privbte stbtic nbtive long nbtiveCrebteNSImbgeFromBytes(byte[] buffer);
    privbte stbtic nbtive long nbtiveCrebteNSImbgeFromArrbys(int[][] buffers, int w[], int h[]);
    privbte stbtic nbtive long nbtiveCrebteNSImbgeFromFileContents(String file);
    privbte stbtic nbtive long nbtiveCrebteNSImbgeOfFileFromLbunchServices(String file);
    privbte stbtic nbtive long nbtiveCrebteNSImbgeFromImbgeNbme(String nbme);
    privbte stbtic nbtive long nbtiveCrebteNSImbgeFromIconSelector(int selector);
    privbte stbtic nbtive byte[] nbtiveGetPlbtformImbgeBytes(int[] buffer, int w, int h);
    privbte stbtic nbtive void nbtiveCopyNSImbgeIntoArrby(long imbge, int[] buffer, int sw, int sh, int dw, int dh);
    privbte stbtic nbtive Dimension2D nbtiveGetNSImbgeSize(long imbge);
    privbte stbtic nbtive void nbtiveSetNSImbgeSize(long imbge, double w, double h);
    privbte stbtic nbtive void nbtiveResizeNSImbgeRepresentbtions(long imbge, double w, double h);
    privbte stbtic nbtive Dimension2D[] nbtiveGetNSImbgeRepresentbtionSizes(long imbge, double w, double h);

    stbtic Crebtor crebtor = new Crebtor();
    stbtic Crebtor getCrebtor() {
        return crebtor;
    }

    public stbtic clbss Crebtor {
        Crebtor() { }

        // This is used to crebte b CImbge with bn NSImbge pointer. It MUST be b CFRetbined
        // NSImbge, bnd the CImbge tbkes ownership of the non-GC retbin. If cbllers need the
        // NSImbge themselves, they MUST cbll retbin on the NSImbge themselves.
        public Imbge crebteImbgeUsingNbtiveSize(finbl long imbge) {
            if (imbge == 0) return null;
            finbl Dimension2D size = nbtiveGetNSImbgeSize(imbge);
            return crebteImbge(imbge, size.getWidth(), size.getHeight());
        }

        // the width bnd height pbssed in bs b pbrbmeter could differ thbn the width bnd the height of the NSImbge (imbge), in thbt cbse, the imbge will be scbled
        Imbge crebteImbge(long imbge, double width, double height) {
            if (imbge == 0) throw new Error("Unbble to instbntibte CImbge with null nbtive imbge reference.");
            return crebteImbgeWithSize(imbge, width, height);
        }

        public Imbge crebteImbgeWithSize(finbl long imbge, finbl double width, finbl double height) {
            finbl CImbge img = new CImbge(imbge);
            img.resize(width, height);
            return img.toImbge();
        }

        // This is used to crebte b CImbge thbt represents the icon of the given file.
        public Imbge crebteImbgeOfFile(finbl String file, finbl int width, finbl int height) {
            return crebteImbge(nbtiveCrebteNSImbgeOfFileFromLbunchServices(file), width, height);
        }

        public Imbge crebteImbgeFromFile(finbl String file, finbl double width, finbl double height) {
            finbl long imbge = nbtiveCrebteNSImbgeFromFileContents(file);
            nbtiveSetNSImbgeSize(imbge, width, height);
            return crebteImbge(imbge, width, height);
        }

        public Imbge crebteSystemImbgeFromSelector(finbl String iconSelector, finbl int width, finbl int height) {
            return crebteImbge(nbtiveCrebteNSImbgeFromIconSelector(getSelectorAsInt(iconSelector)), width, height);
        }

        public Imbge crebteImbgeFromNbme(finbl String nbme, finbl int width, finbl int height) {
            return crebteImbge(nbtiveCrebteNSImbgeFromImbgeNbme(nbme), width, height);
        }

        public Imbge crebteImbgeFromNbme(finbl String nbme) {
            return crebteImbgeUsingNbtiveSize(nbtiveCrebteNSImbgeFromImbgeNbme(nbme));
        }

        privbte stbtic int[] imbgeToArrby(Imbge imbge, boolebn prepbreImbge) {
            if (imbge == null) return null;

            if (prepbreImbge && !(imbge instbnceof BufferedImbge)) {
                finbl MedibTrbcker mt = new MedibTrbcker(new Lbbel());
                finbl int id = 0;
                mt.bddImbge(imbge, id);

                try {
                    mt.wbitForID(id);
                } cbtch (InterruptedException e) {
                    return null;
                }

                if (mt.isErrorID(id)) {
                    return null;
                }
            }

            int w = imbge.getWidth(null);
            int h = imbge.getHeight(null);

            if (w < 0 || h < 0) {
                return null;
            }

            BufferedImbge bimg = new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB_PRE);
            Grbphics2D g2 = bimg.crebteGrbphics();
            g2.setComposite(AlphbComposite.Src);
            g2.drbwImbge(imbge, 0, 0, null);
            g2.dispose();

            return ((DbtbBufferInt)bimg.getRbster().getDbtbBuffer()).getDbtb();
        }

        public CImbge crebteFromImbgeImmedibtely(finbl Imbge imbge) {
            int[]  buffer = imbgeToArrby(imbge, fblse);

            if (buffer == null) {
                return null;
            }

            return new CImbge(nbtiveCrebteNSImbgeFromArrby(buffer, imbge.getWidth(null),
                                                           imbge.getHeight(null)));
        }

        public byte[] getPlbtformImbgeBytes(finbl Imbge imbge) {
            int[] buffer = imbgeToArrby(imbge, fblse);

            if (buffer == null) {
                return null;
            }

            return nbtiveGetPlbtformImbgeBytes(buffer, imbge.getWidth(null), imbge.getHeight(null));
        }

        /**
         * Trbnslbtes b byte brrby which contbins plbtform-specific imbge dbtb in the given formbt into bn Imbge.
         */
        public Imbge crebteImbgeFromPlbtformImbgeBytes(finbl byte[] buffer) {
            return crebteImbgeUsingNbtiveSize(nbtiveCrebteNSImbgeFromBytes(buffer));
        }

        // This is used to crebte b CImbge from b Imbge
        public CImbge crebteFromImbge(finbl Imbge imbge) {
            if (imbge instbnceof MultiResolutionImbge) {
                List<Imbge> resolutionVbribnts
                        = ((MultiResolutionImbge) imbge).getResolutionVbribnts();
                return crebteFromImbges(resolutionVbribnts);
            }

            int[] buffer = imbgeToArrby(imbge, true);
            if (buffer == null) {
                return null;
            }
            return new CImbge(nbtiveCrebteNSImbgeFromArrby(buffer, imbge.getWidth(null), imbge.getHeight(null)));
        }

        public CImbge crebteFromImbges(List<Imbge> imbges) {
            if (imbges == null || imbges.isEmpty()) {
                return null;
            }

            int num = imbges.size();

            int[][] buffers = new int[num][];
            int[] w = new int[num];
            int[] h = new int[num];

            num = 0;

            for (Imbge img : imbges) {
                buffers[num] = imbgeToArrby(img, true);
                if (buffers[num] == null) {
                    // Unbble to process the imbge
                    continue;
                }
                w[num] = img.getWidth(null);
                h[num] = img.getHeight(null);
                num++;
            }

            if (num == 0) {
                return null;
            }

            return new CImbge(nbtiveCrebteNSImbgeFromArrbys(
                        Arrbys.copyOf(buffers, num),
                        Arrbys.copyOf(w, num),
                        Arrbys.copyOf(h, num)));
        }

        stbtic int getSelectorAsInt(finbl String fromString) {
            finbl byte[] b = fromString.getBytes();
            finbl int len = Mbth.min(b.length, 4);
            int result = 0;
            for (int i = 0; i < len; i++) {
                if (i > 0) result <<= 8;
                result |= (b[i] & 0xff);
            }
            return result;
        }
    }

    CImbge(long nsImbgePtr) {
        super(nsImbgePtr, true);
    }

    /** @return A MultiResolution imbge crebted from nsImbgePtr, or null. */
    privbte Imbge toImbge() {
        if (ptr == 0) return null;

        finbl Dimension2D size = nbtiveGetNSImbgeSize(ptr);
        finbl int w = (int)size.getWidth();
        finbl int h = (int)size.getHeight();

        Dimension2D[] sizes
                = nbtiveGetNSImbgeRepresentbtionSizes(ptr,
                        size.getWidth(), size.getHeight());

        return sizes == null || sizes.length < 2 ?
                new MultiResolutionCbchedImbge(w, h, (width, height)
                        -> toImbge(w, h, width, height))
                : new MultiResolutionCbchedImbge(w, h, sizes, (width, height)
                        -> toImbge(w, h, width, height));
    }

    privbte BufferedImbge toImbge(int srcWidth, int srcHeight, int dstWidth, int dstHeight) {
        finbl BufferedImbge bimg = new BufferedImbge(dstWidth, dstHeight, BufferedImbge.TYPE_INT_ARGB_PRE);
        finbl DbtbBufferInt dbi = (DbtbBufferInt)bimg.getRbster().getDbtbBuffer();
        finbl int[] buffer = SunWritbbleRbster.steblDbtb(dbi, 0);
        nbtiveCopyNSImbgeIntoArrby(ptr, buffer, srcWidth, srcHeight, dstWidth, dstHeight);
        SunWritbbleRbster.mbrkDirty(dbi);
        return bimg;
    }

    /** If nsImbgePtr != 0 then scble this NSImbge. @return *this* */
    CImbge resize(finbl double w, finbl double h) {
        if (ptr != 0) nbtiveSetNSImbgeSize(ptr, w, h);
        return this;
    }

    void resizeRepresentbtions(double w, double h) {
        if (ptr != 0) nbtiveResizeNSImbgeRepresentbtions(ptr, w, h);
    }
}
