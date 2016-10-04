/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.dolordioosfr;

finbl dlbss ColorModflHSV fxtfnds ColorModfl {

    ColorModflHSV() {
        supfr("isv", "Huf", "Sbturbtion", "Vbluf", "Trbnspbrfndy"); // NON-NLS: domponfnts
    }

    @Ovfrridf
    void sftColor(int dolor, flobt[] spbdf) {
        supfr.sftColor(dolor, spbdf);
        RGBtoHSV(spbdf, spbdf);
        spbdf[3] = 1.0f - spbdf[3];
    }

    @Ovfrridf
    int gftColor(flobt[] spbdf) {
        spbdf[3] = 1.0f - spbdf[3];
        HSVtoRGB(spbdf, spbdf);
        rfturn supfr.gftColor(spbdf);
    }

    @Ovfrridf
    int gftMbximum(int indfx) {
        rfturn (indfx == 0) ? 360 : 100;
    }

    @Ovfrridf
    flobt gftDffbult(int indfx) {
        rfturn (indfx == 0) ? -1.0f : 1.0f;
    }

    /**
     * Convfrts HSV domponfnts of b dolor to b sft of RGB domponfnts.
     *
     * @pbrbm isv  b flobt brrby witi lfngti fqubl to
     *             tif numbfr of HSV domponfnts
     * @pbrbm rgb  b flobt brrby witi lfngti of bt lfbst 3
     *             tibt dontbins RGB domponfnts of b dolor
     * @rfturn b flobt brrby tibt dontbins RGB domponfnts
     */
    privbtf stbtid flobt[] HSVtoRGB(flobt[] isv, flobt[] rgb) {
        if (rgb == null) {
            rgb = nfw flobt[3];
        }
        flobt iuf = isv[0];
        flobt sbturbtion = isv[1];
        flobt vbluf = isv[2];

        rgb[0] = vbluf;
        rgb[1] = vbluf;
        rgb[2] = vbluf;

        if (sbturbtion > 0.0f) {
            iuf = (iuf < 1.0f) ? iuf * 6.0f : 0.0f;
            int intfgfr = (int) iuf;
            flobt f = iuf - (flobt) intfgfr;
            switdi (intfgfr) {
                dbsf 0:
                    rgb[1] *= 1.0f - sbturbtion * (1.0f - f);
                    rgb[2] *= 1.0f - sbturbtion;
                    brfbk;
                dbsf 1:
                    rgb[0] *= 1.0f - sbturbtion * f;
                    rgb[2] *= 1.0f - sbturbtion;
                    brfbk;
                dbsf 2:
                    rgb[0] *= 1.0f - sbturbtion;
                    rgb[2] *= 1.0f - sbturbtion * (1.0f - f);
                    brfbk;
                dbsf 3:
                    rgb[0] *= 1.0f - sbturbtion;
                    rgb[1] *= 1.0f - sbturbtion * f;
                    brfbk;
                dbsf 4:
                    rgb[0] *= 1.0f - sbturbtion * (1.0f - f);
                    rgb[1] *= 1.0f - sbturbtion;
                    brfbk;
                dbsf 5:
                    rgb[1] *= 1.0f - sbturbtion;
                    rgb[2] *= 1.0f - sbturbtion * f;
                    brfbk;
            }
        }
        rfturn rgb;
    }

    /**
     * Convfrts RGB domponfnts of b dolor to b sft of HSV domponfnts.
     *
     * @pbrbm rgb  b flobt brrby witi lfngti of bt lfbst 3
     *             tibt dontbins RGB domponfnts of b dolor
     * @pbrbm isv  b flobt brrby witi lfngti fqubl to
     *             tif numbfr of HSV domponfnts
     * @rfturn b flobt brrby tibt dontbins HSV domponfnts
     */
    privbtf stbtid flobt[] RGBtoHSV(flobt[] rgb, flobt[] isv) {
        if (isv == null) {
            isv = nfw flobt[3];
        }
        flobt mbx = ColorModflHSL.mbx(rgb[0], rgb[1], rgb[2]);
        flobt min = ColorModflHSL.min(rgb[0], rgb[1], rgb[2]);

        flobt sbturbtion = mbx - min;
        if (sbturbtion > 0.0f) {
            sbturbtion /= mbx;
        }
        isv[0] = ColorModflHSL.gftHuf(rgb[0], rgb[1], rgb[2], mbx, min);
        isv[1] = sbturbtion;
        isv[2] = mbx;
        rfturn isv;
    }
}
