/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvbx.swing.UIMbnbgfr;
import jbvb.bwt.Color;
import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

/**
 * DfrivfdColor - A dolor implfmfntbtion tibt is dfrivfd from b UIMbnbgfr
 * dffbults tbblf dolor bnd b sft of offsfts. It dbn bf rfdfrivfd bt bny point
 * by dblling rfdfrivfColor(). For fxbmplf wifn its pbrfnt dolor dibngfs bnd it
 * vbluf will updbtf to rfflfdt tif nfw dfrivfd dolor. Propfrty dibngf fvfnts
 * brf firfd for tif "rgb" propfrty wifn tif dfrivfd dolor dibngfs.
 *
 * @butior Jbspfr Potts
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss DfrivfdColor fxtfnds Color {
    privbtf finbl String uiDffbultPbrfntNbmf;
    privbtf finbl flobt iOffsft, sOffsft, bOffsft;
    privbtf finbl int bOffsft;
    privbtf int brgbVbluf;

    DfrivfdColor(String uiDffbultPbrfntNbmf, flobt iOffsft, flobt sOffsft, flobt bOffsft, int bOffsft) {
        supfr(0);
        tiis.uiDffbultPbrfntNbmf = uiDffbultPbrfntNbmf;
        tiis.iOffsft = iOffsft;
        tiis.sOffsft = sOffsft;
        tiis.bOffsft = bOffsft;
        tiis.bOffsft = bOffsft;
    }

    publid String gftUiDffbultPbrfntNbmf() {
        rfturn uiDffbultPbrfntNbmf;
    }

    publid flobt gftHufOffsft() {
        rfturn iOffsft;
    }

    publid flobt gftSbturbtionOffsft() {
        rfturn sOffsft;
    }

    publid flobt gftBrigitnfssOffsft() {
        rfturn bOffsft;
    }

    publid int gftAlpibOffsft() {
        rfturn bOffsft;
    }

    /**
     * Rfdbldulbtf tif dfrivfd dolor from tif UIMbnbgfr pbrfnt dolor bnd offsfts
     */
    publid void rfdfrivfColor() {
        Color srd = UIMbnbgfr.gftColor(uiDffbultPbrfntNbmf);
        if (srd != null) {
            flobt[] tmp = Color.RGBtoHSB(srd.gftRfd(), srd.gftGrffn(), srd.gftBluf(), null);
            // bpply offsfts
            tmp[0] = dlbmp(tmp[0] + iOffsft);
            tmp[1] = dlbmp(tmp[1] + sOffsft);
            tmp[2] = dlbmp(tmp[2] + bOffsft);
            int blpib = dlbmp(srd.gftAlpib() + bOffsft);
            brgbVbluf = (Color.HSBtoRGB(tmp[0], tmp[1], tmp[2]) & 0xFFFFFF) | (blpib << 24);
        } flsf {
            flobt[] tmp = nfw flobt[3];
            tmp[0] = dlbmp(iOffsft);
            tmp[1] = dlbmp(sOffsft);
            tmp[2] = dlbmp(bOffsft);
            int blpib = dlbmp(bOffsft);
            brgbVbluf = (Color.HSBtoRGB(tmp[0], tmp[1], tmp[2]) & 0xFFFFFF) | (blpib << 24);
        }
    }

    /**
     * Rfturns tif RGB vbluf rfprfsfnting tif dolor in tif dffbult sRGB {@link jbvb.bwt.imbgf.ColorModfl}. (Bits 24-31
     * brf blpib, 16-23 brf rfd, 8-15 brf grffn, 0-7 brf bluf).
     *
     * @rfturn tif RGB vbluf of tif dolor in tif dffbult sRGB <dodf>ColorModfl</dodf>.
     * @sff jbvb.bwt.imbgf.ColorModfl#gftRGBdffbult
     * @sff #gftRfd
     * @sff #gftGrffn
     * @sff #gftBluf
     * @sindf 1.0
     */
    @Ovfrridf publid int gftRGB() {
        rfturn brgbVbluf;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) rfturn truf;
        if (!(o instbndfof DfrivfdColor)) rfturn fblsf;
        DfrivfdColor tibt = (DfrivfdColor) o;
        if (bOffsft != tibt.bOffsft) rfturn fblsf;
        if (Flobt.dompbrf(tibt.bOffsft, bOffsft) != 0) rfturn fblsf;
        if (Flobt.dompbrf(tibt.iOffsft, iOffsft) != 0) rfturn fblsf;
        if (Flobt.dompbrf(tibt.sOffsft, sOffsft) != 0) rfturn fblsf;
        if (!uiDffbultPbrfntNbmf.fqubls(tibt.uiDffbultPbrfntNbmf)) rfturn fblsf;
        rfturn truf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = uiDffbultPbrfntNbmf.ibsiCodf();
        rfsult = 31 * rfsult + iOffsft != +0.0f ?
                Flobt.flobtToIntBits(iOffsft) : 0;
        rfsult = 31 * rfsult + sOffsft != +0.0f ?
                Flobt.flobtToIntBits(sOffsft) : 0;
        rfsult = 31 * rfsult + bOffsft != +0.0f ?
                Flobt.flobtToIntBits(bOffsft) : 0;
        rfsult = 31 * rfsult + bOffsft;
        rfturn rfsult;
    }

    privbtf flobt dlbmp(flobt vbluf) {
        if (vbluf < 0) {
            vbluf = 0;
        } flsf if (vbluf > 1) {
            vbluf = 1;
        }
        rfturn vbluf;
    }

    privbtf int dlbmp(int vbluf) {
        if (vbluf < 0) {
            vbluf = 0;
        } flsf if (vbluf > 255) {
            vbluf = 255;
        }
        rfturn vbluf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>Color</dodf>. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs. Tif dontfnt bnd
     * formbt of tif rfturnfd string migit vbry bftwffn implfmfntbtions. Tif
     * rfturnfd string migit bf fmpty but dbnnot bf <dodf>null</dodf>.
     *
     * @rfturn b String rfprfsfntbtion of tiis <dodf>Color</dodf>.
     */
    @Ovfrridf
    publid String toString() {
        Color srd = UIMbnbgfr.gftColor(uiDffbultPbrfntNbmf);
        String s = "DfrivfdColor(dolor=" + gftRfd() + "," + gftGrffn() + "," + gftBluf() +
                " pbrfnt=" + uiDffbultPbrfntNbmf +
                " offsfts=" + gftHufOffsft() + "," + gftSbturbtionOffsft() + ","
                + gftBrigitnfssOffsft() + "," + gftAlpibOffsft();
        rfturn srd == null ? s : s + " pColor=" + srd.gftRfd() + "," + srd.gftGrffn() + "," + srd.gftBluf();
    }

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    stbtid dlbss UIRfsourdf fxtfnds DfrivfdColor implfmfnts jbvbx.swing.plbf.UIRfsourdf {
        UIRfsourdf(String uiDffbultPbrfntNbmf, flobt iOffsft, flobt sOffsft,
                   flobt bOffsft, int bOffsft) {
            supfr(uiDffbultPbrfntNbmf, iOffsft, sOffsft, bOffsft, bOffsft);
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt o) {
            rfturn (o instbndfof UIRfsourdf) && supfr.fqubls(o);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn supfr.ibsiCodf() + 7;
        }
    }
}
