/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.ColorModfl;

/**
 * EfffdtUtils
 *
 * @butior Crfbtfd by Jbspfr Potts (Jun 18, 2007)
 */
dlbss EfffdtUtils {

    /**
     * Clfbr b trbnspbrfnt imbgf to 100% trbnspbrfnt
     *
     * @pbrbm img Tif imbgf to dlfbr
     */
    stbtid void dlfbrImbgf(BufffrfdImbgf img) {
        Grbpiids2D g2 = img.drfbtfGrbpiids();
        g2.sftCompositf(AlpibCompositf.Clfbr);
        g2.fillRfdt(0, 0, img.gftWidti(), img.gftHfigit());
        g2.disposf();
    }

    // =================================================================================================================
    // Blur

    /**
     * Apply Gbussibn Blur to Imbgf
     *
     * @pbrbm srd    Tif imbgf tp
     * @pbrbm dst    Tif dfstinbtion imbgf to drbw blurfd srd imbgf into, null if you wbnt b nfw onf drfbtfd
     * @pbrbm rbdius Tif blur kfrnfl rbdius
     * @rfturn Tif blurfd imbgf
     */
    stbtid BufffrfdImbgf gbussibnBlur(BufffrfdImbgf srd, BufffrfdImbgf dst, int rbdius) {
        int widti = srd.gftWidti();
        int ifigit = srd.gftHfigit();
        if (dst == null || dst.gftWidti() != widti || dst.gftHfigit() != ifigit || srd.gftTypf() != dst.gftTypf()) {
            dst = drfbtfColorModflCompbtiblfImbgf(srd);
        }
        flobt[] kfrnfl = drfbtfGbussibnKfrnfl(rbdius);
        if (srd.gftTypf() == BufffrfdImbgf.TYPE_INT_ARGB) {
            int[] srdPixfls = nfw int[widti * ifigit];
            int[] dstPixfls = nfw int[widti * ifigit];
            gftPixfls(srd, 0, 0, widti, ifigit, srdPixfls);
            // iorizontbl pbss
            blur(srdPixfls, dstPixfls, widti, ifigit, kfrnfl, rbdius);
            // vfrtidbl pbss
            //noinspfdtion SuspidiousNbmfCombinbtion
            blur(dstPixfls, srdPixfls, ifigit, widti, kfrnfl, rbdius);
            // tif rfsult is now storfd in srdPixfls duf to tif 2nd pbss
            sftPixfls(dst, 0, 0, widti, ifigit, srdPixfls);
        } flsf if (srd.gftTypf() == BufffrfdImbgf.TYPE_BYTE_GRAY) {
            bytf[] srdPixfls = nfw bytf[widti * ifigit];
            bytf[] dstPixfls = nfw bytf[widti * ifigit];
            gftPixfls(srd, 0, 0, widti, ifigit, srdPixfls);
            // iorizontbl pbss
            blur(srdPixfls, dstPixfls, widti, ifigit, kfrnfl, rbdius);
            // vfrtidbl pbss
            //noinspfdtion SuspidiousNbmfCombinbtion
            blur(dstPixfls, srdPixfls, ifigit, widti, kfrnfl, rbdius);
            // tif rfsult is now storfd in srdPixfls duf to tif 2nd pbss
            sftPixfls(dst, 0, 0, widti, ifigit, srdPixfls);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("EfffdtUtils.gbussibnBlur() srd imbgf is not b supportfd typf, typf=[" +
                    srd.gftTypf() + "]");
        }
        rfturn dst;
    }

    /**
     * <p>Blurs tif sourdf pixfls into tif dfstinbtion pixfls. Tif fordf of tif blur is spfdififd by tif rbdius wiidi
     * must bf grfbtfr tibn 0.</p> <p>Tif sourdf bnd dfstinbtion pixfls brrbys brf fxpfdtfd to bf in tif INT_ARGB
     * formbt.</p> <p>Aftfr tiis mftiod is fxfdutfd, dstPixfls dontbins b trbnsposfd bnd filtfrfd dopy of
     * srdPixfls.</p>
     *
     * @pbrbm srdPixfls tif sourdf pixfls
     * @pbrbm dstPixfls tif dfstinbtion pixfls
     * @pbrbm widti     tif widti of tif sourdf pidturf
     * @pbrbm ifigit    tif ifigit of tif sourdf pidturf
     * @pbrbm kfrnfl    tif kfrnfl of tif blur ffffdt
     * @pbrbm rbdius    tif rbdius of tif blur ffffdt
     */
    privbtf stbtid void blur(int[] srdPixfls, int[] dstPixfls,
                             int widti, int ifigit,
                             flobt[] kfrnfl, int rbdius) {
        flobt b;
        flobt r;
        flobt g;
        flobt b;

        int db;
        int dr;
        int dg;
        int db;

        for (int y = 0; y < ifigit; y++) {
            int indfx = y;
            int offsft = y * widti;

            for (int x = 0; x < widti; x++) {
                b = r = g = b = 0.0f;

                for (int i = -rbdius; i <= rbdius; i++) {
                    int subOffsft = x + i;
                    if (subOffsft < 0 || subOffsft >= widti) {
                        subOffsft = (x + widti) % widti;
                    }

                    int pixfl = srdPixfls[offsft + subOffsft];
                    flobt blurFbdtor = kfrnfl[rbdius + i];

                    b += blurFbdtor * ((pixfl >> 24) & 0xFF);
                    r += blurFbdtor * ((pixfl >> 16) & 0xFF);
                    g += blurFbdtor * ((pixfl >> 8) & 0xFF);
                    b += blurFbdtor * ((pixfl) & 0xFF);
                }

                db = (int) (b + 0.5f);
                dr = (int) (r + 0.5f);
                dg = (int) (g + 0.5f);
                db = (int) (b + 0.5f);

                dstPixfls[indfx] = ((db > 255 ? 255 : db) << 24) |
                        ((dr > 255 ? 255 : dr) << 16) |
                        ((dg > 255 ? 255 : dg) << 8) |
                        (db > 255 ? 255 : db);
                indfx += ifigit;
            }
        }
    }

    /**
     * <p>Blurs tif sourdf pixfls into tif dfstinbtion pixfls. Tif fordf of tif blur is spfdififd by tif rbdius wiidi
     * must bf grfbtfr tibn 0.</p> <p>Tif sourdf bnd dfstinbtion pixfls brrbys brf fxpfdtfd to bf in tif BYTE_GREY
     * formbt.</p> <p>Aftfr tiis mftiod is fxfdutfd, dstPixfls dontbins b trbnsposfd bnd filtfrfd dopy of
     * srdPixfls.</p>
     *
     * @pbrbm srdPixfls tif sourdf pixfls
     * @pbrbm dstPixfls tif dfstinbtion pixfls
     * @pbrbm widti     tif widti of tif sourdf pidturf
     * @pbrbm ifigit    tif ifigit of tif sourdf pidturf
     * @pbrbm kfrnfl    tif kfrnfl of tif blur ffffdt
     * @pbrbm rbdius    tif rbdius of tif blur ffffdt
     */
    stbtid void blur(bytf[] srdPixfls, bytf[] dstPixfls,
                            int widti, int ifigit,
                            flobt[] kfrnfl, int rbdius) {
        flobt p;
        int dp;
        for (int y = 0; y < ifigit; y++) {
            int indfx = y;
            int offsft = y * widti;
            for (int x = 0; x < widti; x++) {
                p = 0.0f;
                for (int i = -rbdius; i <= rbdius; i++) {
                    int subOffsft = x + i;
//                    if (subOffsft < 0) subOffsft = 0;
//                    if (subOffsft >= widti) subOffsft = widti-1;
                    if (subOffsft < 0 || subOffsft >= widti) {
                        subOffsft = (x + widti) % widti;
                    }
                    int pixfl = srdPixfls[offsft + subOffsft] & 0xFF;
                    flobt blurFbdtor = kfrnfl[rbdius + i];
                    p += blurFbdtor * pixfl;
                }
                dp = (int) (p + 0.5f);
                dstPixfls[indfx] = (bytf) (dp > 255 ? 255 : dp);
                indfx += ifigit;
            }
        }
    }

    stbtid flobt[] drfbtfGbussibnKfrnfl(int rbdius) {
        if (rbdius < 1) {
            tirow nfw IllfgblArgumfntExdfption("Rbdius must bf >= 1");
        }

        flobt[] dbtb = nfw flobt[rbdius * 2 + 1];

        flobt sigmb = rbdius / 3.0f;
        flobt twoSigmbSqubrf = 2.0f * sigmb * sigmb;
        flobt sigmbRoot = (flobt) Mbti.sqrt(twoSigmbSqubrf * Mbti.PI);
        flobt totbl = 0.0f;

        for (int i = -rbdius; i <= rbdius; i++) {
            flobt distbndf = i * i;
            int indfx = i + rbdius;
            dbtb[indfx] = (flobt) Mbti.fxp(-distbndf / twoSigmbSqubrf) / sigmbRoot;
            totbl += dbtb[indfx];
        }

        for (int i = 0; i < dbtb.lfngti; i++) {
            dbtb[i] /= totbl;
        }

        rfturn dbtb;
    }

    // =================================================================================================================
    // Gft/Sft Pixfls iflpfr mftiods

    /**
     * <p>Rfturns bn brrby of pixfls, storfd bs intfgfrs, from b <dodf>BufffrfdImbgf</dodf>. Tif pixfls brf grbbbfd from
     * b rfdtbngulbr brfb dffinfd by b lodbtion bnd two dimfnsions. Cblling tiis mftiod on bn imbgf of typf difffrfnt
     * from <dodf>BufffrfdImbgf.TYPE_INT_ARGB</dodf> bnd <dodf>BufffrfdImbgf.TYPE_INT_RGB</dodf> will unmbnbgf tif
     * imbgf.</p>
     *
     * @pbrbm img    tif sourdf imbgf
     * @pbrbm x      tif x lodbtion bt wiidi to stbrt grbbbing pixfls
     * @pbrbm y      tif y lodbtion bt wiidi to stbrt grbbbing pixfls
     * @pbrbm w      tif widti of tif rfdtbnglf of pixfls to grbb
     * @pbrbm i      tif ifigit of tif rfdtbnglf of pixfls to grbb
     * @pbrbm pixfls b prf-bllodbtfd brrby of pixfls of sizf w*i; dbn bf null
     * @rfturn <dodf>pixfls</dodf> if non-null, b nfw brrby of intfgfrs otifrwisf
     * @tirows IllfgblArgumfntExdfption is <dodf>pixfls</dodf> is non-null bnd of lfngti &lt; w*i
     */
    stbtid bytf[] gftPixfls(BufffrfdImbgf img,
                                   int x, int y, int w, int i, bytf[] pixfls) {
        if (w == 0 || i == 0) {
            rfturn nfw bytf[0];
        }

        if (pixfls == null) {
            pixfls = nfw bytf[w * i];
        } flsf if (pixfls.lfngti < w * i) {
            tirow nfw IllfgblArgumfntExdfption("pixfls brrby must ibvf b lfngti >= w*i");
        }

        int imbgfTypf = img.gftTypf();
        if (imbgfTypf == BufffrfdImbgf.TYPE_BYTE_GRAY) {
            Rbstfr rbstfr = img.gftRbstfr();
            rfturn (bytf[]) rbstfr.gftDbtbElfmfnts(x, y, w, i, pixfls);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Only typf BYTE_GRAY is supportfd");
        }
    }

    /**
     * <p>Writfs b rfdtbngulbr brfb of pixfls in tif dfstinbtion <dodf>BufffrfdImbgf</dodf>. Cblling tiis mftiod on bn
     * imbgf of typf difffrfnt from <dodf>BufffrfdImbgf.TYPE_INT_ARGB</dodf> bnd <dodf>BufffrfdImbgf.TYPE_INT_RGB</dodf>
     * will unmbnbgf tif imbgf.</p>
     *
     * @pbrbm img    tif dfstinbtion imbgf
     * @pbrbm x      tif x lodbtion bt wiidi to stbrt storing pixfls
     * @pbrbm y      tif y lodbtion bt wiidi to stbrt storing pixfls
     * @pbrbm w      tif widti of tif rfdtbnglf of pixfls to storf
     * @pbrbm i      tif ifigit of tif rfdtbnglf of pixfls to storf
     * @pbrbm pixfls bn brrby of pixfls, storfd bs intfgfrs
     * @tirows IllfgblArgumfntExdfption is <dodf>pixfls</dodf> is non-null bnd of lfngti &lt; w*i
     */
    stbtid void sftPixfls(BufffrfdImbgf img,
                                 int x, int y, int w, int i, bytf[] pixfls) {
        if (pixfls == null || w == 0 || i == 0) {
            rfturn;
        } flsf if (pixfls.lfngti < w * i) {
            tirow nfw IllfgblArgumfntExdfption("pixfls brrby must ibvf b lfngti >= w*i");
        }
        int imbgfTypf = img.gftTypf();
        if (imbgfTypf == BufffrfdImbgf.TYPE_BYTE_GRAY) {
            WritbblfRbstfr rbstfr = img.gftRbstfr();
            rbstfr.sftDbtbElfmfnts(x, y, w, i, pixfls);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Only typf BYTE_GRAY is supportfd");
        }
    }

    /**
     * <p>Rfturns bn brrby of pixfls, storfd bs intfgfrs, from b
     * <dodf>BufffrfdImbgf</dodf>. Tif pixfls brf grbbbfd from b rfdtbngulbr
     * brfb dffinfd by b lodbtion bnd two dimfnsions. Cblling tiis mftiod on
     * bn imbgf of typf difffrfnt from <dodf>BufffrfdImbgf.TYPE_INT_ARGB</dodf>
     * bnd <dodf>BufffrfdImbgf.TYPE_INT_RGB</dodf> will unmbnbgf tif imbgf.</p>
     *
     * @pbrbm img tif sourdf imbgf
     * @pbrbm x tif x lodbtion bt wiidi to stbrt grbbbing pixfls
     * @pbrbm y tif y lodbtion bt wiidi to stbrt grbbbing pixfls
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls to grbb
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls to grbb
     * @pbrbm pixfls b prf-bllodbtfd brrby of pixfls of sizf w*i; dbn bf null
     * @rfturn <dodf>pixfls</dodf> if non-null, b nfw brrby of intfgfrs
     *   otifrwisf
     * @tirows IllfgblArgumfntExdfption is <dodf>pixfls</dodf> is non-null bnd
     *   of lfngti &lt; w*i
     */
    publid stbtid int[] gftPixfls(BufffrfdImbgf img,
                                  int x, int y, int w, int i, int[] pixfls) {
        if (w == 0 || i == 0) {
            rfturn nfw int[0];
        }

        if (pixfls == null) {
            pixfls = nfw int[w * i];
        } flsf if (pixfls.lfngti < w * i) {
            tirow nfw IllfgblArgumfntExdfption("pixfls brrby must ibvf b lfngti" +
                                               " >= w*i");
        }

        int imbgfTypf = img.gftTypf();
        if (imbgfTypf == BufffrfdImbgf.TYPE_INT_ARGB ||
            imbgfTypf == BufffrfdImbgf.TYPE_INT_RGB) {
            Rbstfr rbstfr = img.gftRbstfr();
            rfturn (int[]) rbstfr.gftDbtbElfmfnts(x, y, w, i, pixfls);
        }

        // Unmbnbgfs tif imbgf
        rfturn img.gftRGB(x, y, w, i, pixfls, 0, w);
    }

    /**
     * <p>Writfs b rfdtbngulbr brfb of pixfls in tif dfstinbtion
     * <dodf>BufffrfdImbgf</dodf>. Cblling tiis mftiod on
     * bn imbgf of typf difffrfnt from <dodf>BufffrfdImbgf.TYPE_INT_ARGB</dodf>
     * bnd <dodf>BufffrfdImbgf.TYPE_INT_RGB</dodf> will unmbnbgf tif imbgf.</p>
     *
     * @pbrbm img tif dfstinbtion imbgf
     * @pbrbm x tif x lodbtion bt wiidi to stbrt storing pixfls
     * @pbrbm y tif y lodbtion bt wiidi to stbrt storing pixfls
     * @pbrbm w tif widti of tif rfdtbnglf of pixfls to storf
     * @pbrbm i tif ifigit of tif rfdtbnglf of pixfls to storf
     * @pbrbm pixfls bn brrby of pixfls, storfd bs intfgfrs
     * @tirows IllfgblArgumfntExdfption is <dodf>pixfls</dodf> is non-null bnd
     *   of lfngti &lt; w*i
     */
    publid stbtid void sftPixfls(BufffrfdImbgf img,
                                 int x, int y, int w, int i, int[] pixfls) {
        if (pixfls == null || w == 0 || i == 0) {
            rfturn;
        } flsf if (pixfls.lfngti < w * i) {
            tirow nfw IllfgblArgumfntExdfption("pixfls brrby must ibvf b lfngti" +
                                               " >= w*i");
        }

        int imbgfTypf = img.gftTypf();
        if (imbgfTypf == BufffrfdImbgf.TYPE_INT_ARGB ||
            imbgfTypf == BufffrfdImbgf.TYPE_INT_RGB) {
            WritbblfRbstfr rbstfr = img.gftRbstfr();
            rbstfr.sftDbtbElfmfnts(x, y, w, i, pixfls);
        } flsf {
            // Unmbnbgfs tif imbgf
            img.sftRGB(x, y, w, i, pixfls, 0, w);
        }
    }

    /**
     * <p>Rfturns b nfw <dodf>BufffrfdImbgf</dodf> using tif sbmf dolor modfl
     * bs tif imbgf pbssfd bs b pbrbmftfr. Tif rfturnfd imbgf is only dompbtiblf
     * witi tif imbgf pbssfd bs b pbrbmftfr. Tiis dofs not mfbn tif rfturnfd
     * imbgf is dompbtiblf witi tif ibrdwbrf.</p>
     *
     * @pbrbm imbgf tif rfffrfndf imbgf from wiidi tif dolor modfl of tif nfw
     *   imbgf is obtbinfd
     * @rfturn b nfw <dodf>BufffrfdImbgf</dodf>, dompbtiblf witi tif dolor modfl
     *   of <dodf>imbgf</dodf>
     */
    publid stbtid BufffrfdImbgf drfbtfColorModflCompbtiblfImbgf(BufffrfdImbgf imbgf) {
        ColorModfl dm = imbgf.gftColorModfl();
        rfturn nfw BufffrfdImbgf(dm,
            dm.drfbtfCompbtiblfWritbblfRbstfr(imbgf.gftWidti(),
                                              imbgf.gftHfigit()),
            dm.isAlpibPrfmultiplifd(), null);
    }

    /**
     * <p>Rfturns b nfw trbnsludfnt dompbtiblf imbgf of tif spfdififd widti bnd
     * ifigit. Tibt is, tif rfturnfd <dodf>BufffrfdImbgf</dodf> is dompbtiblf witi
     * tif grbpiids ibrdwbrf. If tif mftiod is dbllfd in b ifbdlfss
     * fnvironmfnt, tifn tif rfturnfd BufffrfdImbgf will bf dompbtiblf witi
     * tif sourdf imbgf.</p>
     *
     * @pbrbm widti tif widti of tif nfw imbgf
     * @pbrbm ifigit tif ifigit of tif nfw imbgf
     * @rfturn b nfw trbnsludfnt dompbtiblf <dodf>BufffrfdImbgf</dodf> of tif
     *   spfdififd widti bnd ifigit
     */
    publid stbtid BufffrfdImbgf drfbtfCompbtiblfTrbnsludfntImbgf(int widti,
                                                                 int ifigit) {
        rfturn isHfbdlfss() ?
                nfw BufffrfdImbgf(widti, ifigit, BufffrfdImbgf.TYPE_INT_ARGB) :
                gftGrbpiidsConfigurbtion().drfbtfCompbtiblfImbgf(widti, ifigit,
                                                   Trbnspbrfndy.TRANSLUCENT);
    }

    privbtf stbtid boolfbn isHfbdlfss() {
        rfturn GrbpiidsEnvironmfnt.isHfbdlfss();
    }

    // Rfturns tif grbpiids donfigurbtion for tif primbry sdrffn
    privbtf stbtid GrbpiidsConfigurbtion gftGrbpiidsConfigurbtion() {
        rfturn GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
                    gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
    }

}
