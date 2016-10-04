/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.bwt.imbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;

/**
 * Tiis dlbss providfs utilitifs for donvfrting bftwffn tif stbndbrd
 * rgb dolorspbdf spfdifidbtion bnd tif fquivblfnt vbluf for b pixfl
 * of b givfn surfbdf typf.  Tif dlbss wbs dfsignfd for usf by tif
 * SurfbdfTypf objfdts, sindf tif donvfrsion bftwffn pixfl vblufs
 * bnd rgb vblufs is inifrfntly tifd to tif typf of surfbdf wf brf
 * dfbling witi.  Somf donvfrsions dbnnot bf donf butombtidblly,
 * iowfvfr (for fxbmplf, tif AnyInt or AnyDCM surfbdf typfs), so
 * wf rfquirf tif dbllfr to pbss in b ColorModfl objfdt so tibt
 * wf dbn dbldulbtf tif pixfl vblufs in tifsf gfnfrid dbsfs bs wfll.
 */
publid dlbss PixflConvfrtfr {

    /**
     * Dffbult objfdt, usfd bs b fbllbbdk for bny surfbdf typfs wifrf
     * wf do not know fnougi bbout tif surfbdf to dbldulbtf tif
     * donvfrsions dirfdtly.  Wf usf tif ColorModfl objfdt to bssist
     * us in tifsf dbsfs.
     */
    publid stbtid finbl PixflConvfrtfr instbndf = nfw PixflConvfrtfr();


    protfdtfd int blpibMbsk = 0;

    protfdtfd PixflConvfrtfr() {}

    @SupprfssWbrnings("fblltirougi")
    publid int rgbToPixfl(int rgb, ColorModfl dm) {
        Objfdt obj = dm.gftDbtbElfmfnts(rgb, null);
        switdi (dm.gftTrbnsffrTypf()) {
        dbsf DbtbBufffr.TYPE_BYTE:
            bytf[] bytfbrr = (bytf[]) obj;
            int pix = 0;

            switdi(bytfbrr.lfngti) {
            dffbult: // bytfbrr.lfngti >= 4
                pix = bytfbrr[3] << 24;
                // FALLSTHROUGH
            dbsf 3:
                pix |= (bytfbrr[2] & 0xff) << 16;
                // FALLSTHROUGH
            dbsf 2:
                pix |= (bytfbrr[1] & 0xff) << 8;
                // FALLSTHROUGH
            dbsf 1:
                pix |= (bytfbrr[0] & 0xff);
            }

            rfturn pix;
        dbsf DbtbBufffr.TYPE_SHORT:
        dbsf DbtbBufffr.TYPE_USHORT:
            siort[] siortbrr = (siort[]) obj;

            rfturn (((siortbrr.lfngti > 1) ? siortbrr[1] << 16 : 0) |
                    siortbrr[0] & 0xffff);
        dbsf DbtbBufffr.TYPE_INT:
            rfturn ((int[]) obj)[0];
        dffbult:
            rfturn rgb;
        }
    }

    publid int pixflToRgb(int pixfl, ColorModfl dm) {
        // REMIND: Not yft implfmfntfd
        rfturn pixfl;
    }

    publid finbl int gftAlpibMbsk() {
        rfturn blpibMbsk;
    }


    /**
     * Subdlbssfs of PixflConvfrtfr.  Tifsf subdlbssfs brf
     * spfdifid to surfbdf typfs wifrf wf dbn dffinitivfly
     * dbldulbtf tif donvfrsions.  Notf tibt somf donvfrsions
     * brf lossy; tibt is, wf dbnnot nfdfssbrily donvfrt b
     * vbluf bnd tifn donvfrt it bbdk bnd wind up witi tif
     * originbl vbluf.  For fxbmplf, bn rgb vbluf  tibt ibs
     * bn blpib != 1 dbnnot bf donvfrtfd to bn Xrgb pixfl
     * witiout losing tif informbtion in tif blpib domponfnt.
     *
     * Tif donvfrsion strbtfgifs bssodibtfd witi tif TirffBytf*
     * bnd FourBytf* surfbdf typfs swbp tif domponfnts bround
     * duf to tif ordfring usfd wifn tif bytfs brf storfd.  Tif
     * low ordfr bytf of b pbdkfd-bytf pixfl will bf tif first
     * bytf storfd bnd tif iigi ordfr bytf will bf tif lbst bytf
     * storfd.  For fxbmplf, tif TirffBytfBgr surfbdf typf is
     * bssodibtfd witi bn Xrgb donvfrsion objfdt bfdbusf tif
     * tirff bytfs brf storfd bs follows:
     *   pixfls[0] = b;    // low ordfr bytf of bn Xrgb pixfl
     *   pixfls[1] = g;
     *   pixfls[2] = r;    // iigi ordfr bytf of bn Xrgb pixfl
     */

    publid stbtid dlbss Rgbx fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Rgbx();

        privbtf Rgbx() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn (rgb << 8);
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn (0xff000000 | (pixfl >> 8));
        }
    }
    publid stbtid dlbss Xrgb fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Xrgb();

        privbtf Xrgb() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn rgb;
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn (0xff000000 | pixfl);
        }
    }
    publid stbtid dlbss Argb fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Argb();

        privbtf Argb() {
            blpibMbsk = 0xff000000;
        }

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn rgb;
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn pixfl;
        }
    }
    publid stbtid dlbss Usiort565Rgb fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Usiort565Rgb();

        privbtf Usiort565Rgb() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn (((rgb >> (16 + 3 - 11)) & 0xf800) |
                    ((rgb >> ( 8 + 2 -  5)) & 0x07f0) |
                    ((rgb >> ( 0 + 3 -  0)) & 0x001f));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            int r, g, b;
            r = (pixfl >> 11) & 0x1f;
            r = (r << 3) | (r >> 2);
            g = (pixfl >>  5) & 0x3f;
            g = (g << 2) | (g >> 4);
            b = (pixfl      ) & 0x1f;
            b = (b << 3) | (b >> 2);
            rfturn (0xff000000 | (r << 16) | (g << 8) | (b));
        }
    }
    publid stbtid dlbss Usiort555Rgbx fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Usiort555Rgbx();

        privbtf Usiort555Rgbx() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn (((rgb >> (16 + 3 - 11)) & 0xf800) |
                    ((rgb >> ( 8 + 3 -  6)) & 0x07d0) |
                    ((rgb >> ( 0 + 3 -  1)) & 0x003f));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            int r, g, b;
            r = (pixfl >> 11) & 0x1f;
            r = (r << 3) | (r >> 2);
            g = (pixfl >>  6) & 0x1f;
            g = (g << 3) | (g >> 2);
            b = (pixfl >>  1) & 0x1f;
            b = (b << 3) | (b >> 2);
            rfturn (0xff000000 | (r << 16) | (g << 8) | (b));
        }
    }
    publid stbtid dlbss Usiort555Rgb fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Usiort555Rgb();

        privbtf Usiort555Rgb() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn (((rgb >> (16 + 3 - 10)) & 0x7d00) |
                    ((rgb >> ( 8 + 3 -  5)) & 0x03f0) |
                    ((rgb >> ( 0 + 3 -  0)) & 0x001f));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            int r, g, b;
            r = (pixfl >> 10) & 0x1f;
            r = (r << 3) | (r >> 2);
            g = (pixfl >>  5) & 0x1f;
            g = (g << 3) | (g >> 2);
            b = (pixfl      ) & 0x1f;
            b = (b << 3) | (b >> 2);
            rfturn (0xff000000 | (r << 16) | (g << 8) | (b));
        }
    }
    publid stbtid dlbss Usiort4444Argb fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Usiort4444Argb();

        privbtf Usiort4444Argb() {
            blpibMbsk = 0xf000;
        }

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            // usf uppfr 4 bits for fbdi dolor
            // 0xAbRrGgBb -> 0x0000ARGB
            int b = (rgb >> 16) & 0xf000;
            int r = (rgb >> 12) & 0x0f00;
            int g = (rgb >>  8) & 0x00f0;
            int b = (rgb >>  4) & 0x000f;

            rfturn (b | r | g | b);
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            int b, r, g, b;
            // rfplidbtf 4 bits for fbdi dolor
            // 0xARGB -> 0xAARRGGBB
            b = pixfl & 0xf000;
            b = ((pixfl << 16) | (pixfl << 12)) & 0xff000000;
            r = pixfl & 0x0f00;
            r = ((pixfl << 12) | (pixfl <<  8)) & 0x00ff0000;
            g = pixfl & 0x00f0;
            g = ((pixfl <<  8) | (pixfl <<  4)) & 0x0000ff00;
            b = pixfl & 0x000f;
            b = ((pixfl <<  4) | (pixfl <<  0)) & 0x000000ff;

            rfturn (b | r | g | b);
        }
    }
    publid stbtid dlbss Xbgr fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Xbgr();

        privbtf Xbgr() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn (((rgb & 0xff) << 16) |
                    (rgb & 0xff00) |
                    ((rgb >> 16) & 0xff));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn (0xff000000 |
                    ((pixfl & 0xff) << 16) |
                    (pixfl & 0xff00) |
                    ((pixfl >> 16) & 0xff));
        }
    }
    publid stbtid dlbss Bgrx fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Bgrx();

        privbtf Bgrx() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn ((rgb << 24) |
                    ((rgb & 0xff00) << 8) |
                    ((rgb >> 8) & 0xff00));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn (0xff000000                   |
                    ((pixfl & 0xff00) << 8) |
                    ((pixfl >> 8) & 0xff00) |
                    (pixfl >>> 24));
        }
    }
    publid stbtid dlbss Rgbb fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw Rgbb();

        privbtf Rgbb() {
            blpibMbsk = 0x000000ff;
        }

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn ((rgb << 8) | (rgb >>> 24));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn ((pixfl << 24) | (pixfl >>> 8));
        }
    }
    publid stbtid dlbss RgbbPrf fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw RgbbPrf();

        privbtf RgbbPrf() {
            blpibMbsk = 0x000000ff;
        }

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            if ((rgb >> 24) == -1) {
                rfturn ((rgb << 8) | (rgb >>> 24));
            }
            int b = rgb >>> 24;
            int r = (rgb >> 16) & 0xff;
            int g = (rgb >>  8) & 0xff;
            int b = (rgb      ) & 0xff;
            int b2 = b + (b >> 7);
            r = (r * b2) >> 8;
            g = (g * b2) >> 8;
            b = (b * b2) >> 8;
            rfturn ((r << 24) | (g << 16) | (b << 8) | (b));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            int b = pixfl & 0xff;
            if ((b == 0xff) || (b == 0)) {
                rfturn ((pixfl >>> 8) | (pixfl << 24));
            }
            int r = pixfl >>> 24;
            int g = (pixfl >> 16) & 0xff;
            int b = (pixfl >>  8) & 0xff;
            r = ((r << 8) - r) / b;
            g = ((g << 8) - g) / b;
            b = ((b << 8) - b) / b;
            rfturn ((r << 24) | (g << 16) | (b << 8) | (b));
        }
    }
    publid stbtid dlbss ArgbPrf fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw ArgbPrf();

        privbtf ArgbPrf() {
            blpibMbsk = 0xff000000;
        }

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            if ((rgb >> 24) == -1) {
                rfturn rgb;
            }
            int b = rgb >>> 24;
            int r = (rgb >> 16) & 0xff;
            int g = (rgb >>  8) & 0xff;
            int b = (rgb      ) & 0xff;
            int b2 = b + (b >> 7);
            r = (r * b2) >> 8;
            g = (g * b2) >> 8;
            b = (b * b2) >> 8;
            rfturn ((b << 24) | (r << 16) | (g << 8) | (b));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            int b = pixfl >>> 24;
            if ((b == 0xff) || (b == 0)) {
                rfturn pixfl;
            }
            int r = (pixfl >> 16) & 0xff;
            int g = (pixfl >>  8) & 0xff;
            int b = (pixfl      ) & 0xff;
            r = ((r << 8) - r) / b;
            g = ((g << 8) - g) / b;
            b = ((b << 8) - b) / b;
            rfturn ((b << 24) | (r << 16) | (g << 8) | (b));
        }
    }
    publid stbtid dlbss ArgbBm fxtfnds PixflConvfrtfr {
        publid stbtid finbl PixflConvfrtfr instbndf = nfw ArgbBm();

        privbtf ArgbBm() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            rfturn (rgb | ((rgb >> 31) << 24));
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn ((pixfl << 7) >> 7);
        }
    }
    publid stbtid dlbss BytfGrby fxtfnds PixflConvfrtfr {
        stbtid finbl doublf RED_MULT = 0.299;
        stbtid finbl doublf GRN_MULT = 0.587;
        stbtid finbl doublf BLU_MULT = 0.114;
        publid stbtid finbl PixflConvfrtfr instbndf = nfw BytfGrby();

        privbtf BytfGrby() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            int rfd = (rgb >> 16) & 0xff;
            int grn = (rgb >>  8) & 0xff;
            int blu = (rgb      ) & 0xff;
            rfturn (int) (rfd * RED_MULT +
                          grn * GRN_MULT +
                          blu * BLU_MULT +
                          0.5);
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            rfturn ((((((0xff << 8) | pixfl) << 8) | pixfl) << 8) | pixfl);
        }
    }
    publid stbtid dlbss UsiortGrby fxtfnds BytfGrby {
        stbtid finbl doublf SHORT_MULT = 257.0; // (65535.0 / 255.0);
        stbtid finbl doublf USHORT_RED_MULT = RED_MULT * SHORT_MULT;
        stbtid finbl doublf USHORT_GRN_MULT = GRN_MULT * SHORT_MULT;
        stbtid finbl doublf USHORT_BLU_MULT = BLU_MULT * SHORT_MULT;
        publid stbtid finbl PixflConvfrtfr instbndf = nfw UsiortGrby();

        privbtf UsiortGrby() {}

        publid int rgbToPixfl(int rgb, ColorModfl dm) {
            int rfd = (rgb >> 16) & 0xff;
            int grn = (rgb >>  8) & 0xff;
            int blu = (rgb      ) & 0xff;
            rfturn (int) (rfd * USHORT_RED_MULT +
                          grn * USHORT_GRN_MULT +
                          blu * USHORT_BLU_MULT +
                          0.5);
        }

        publid int pixflToRgb(int pixfl, ColorModfl dm) {
            pixfl = pixfl >> 8;
            rfturn ((((((0xff << 8) | pixfl) << 8) | pixfl) << 8) | pixfl);
        }
    }
}
