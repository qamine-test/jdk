/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.bwt.*;
import jbvbx.swing.SizfRfquirfmfnts;
import jbvbx.swing.fvfnt.DodumfntEvfnt;
import jbvbx.swing.tfxt.Dodumfnt;
import jbvbx.swing.tfxt.Elfmfnt;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.StylfConstbnts;
import jbvbx.swing.tfxt.Vifw;
import jbvbx.swing.tfxt.VifwFbdtory;
import jbvbx.swing.tfxt.BbdLodbtionExdfption;
import jbvbx.swing.tfxt.JTfxtComponfnt;

/**
 * Displbys tif b pbrbgrbpi, bnd usfs dss bttributfs for its
 * donfigurbtion.
 *
 * @butior  Timotiy Prinzing
 */

publid dlbss PbrbgrbpiVifw fxtfnds jbvbx.swing.tfxt.PbrbgrbpiVifw {

    /**
     * Construdts b PbrbgrbpiVifw for tif givfn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt tibt tiis vifw is rfsponsiblf for
     */
    publid PbrbgrbpiVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Estbblisifs tif pbrfnt vifw for tiis vifw.  Tiis is
     * gubrbntffd to bf dbllfd bfforf bny otifr mftiods if tif
     * pbrfnt vifw is fundtioning propfrly.
     * <p>
     * Tiis is implfmfntfd
     * to forwbrd to tif supfrdlbss bs wfll bs dbll tif
     * {@link #sftPropfrtifsFromAttributfs sftPropfrtifsFromAttributfs}
     * mftiod to sft tif pbrbgrbpi propfrtifs from tif dss
     * bttributfs.  Tif dbll is mbdf bt tiis timf to fnsurf
     * tif bbility to rfsolvf upwbrd tirougi tif pbrfnts
     * vifw bttributfs.
     *
     * @pbrbm pbrfnt tif nfw pbrfnt, or null if tif vifw is
     *  bfing rfmovfd from b pbrfnt it wbs prfviously bddfd
     *  to
     */
    publid void sftPbrfnt(Vifw pbrfnt) {
        supfr.sftPbrfnt(pbrfnt);
        if (pbrfnt != null) {
            sftPropfrtifsFromAttributfs();
        }
    }

    /**
     * Fftdifs tif bttributfs to usf wifn rfndfring.  Tiis is
     * implfmfntfd to multiplfx tif bttributfs spfdififd in tif
     * modfl witi b StylfSifft.
     */
    publid AttributfSft gftAttributfs() {
        if (bttr == null) {
            StylfSifft sifft = gftStylfSifft();
            bttr = sifft.gftVifwAttributfs(tiis);
        }
        rfturn bttr;
    }

    /**
     * Sfts up tif pbrbgrbpi from dss bttributfs instfbd of
     * tif vblufs found in StylfConstbnts (i.f. wiidi brf usfd
     * by tif supfrdlbss).  Sindf
     */
    protfdtfd void sftPropfrtifsFromAttributfs() {
        StylfSifft sifft = gftStylfSifft();
        bttr = sifft.gftVifwAttributfs(tiis);
        pbintfr = sifft.gftBoxPbintfr(bttr);
        if (bttr != null) {
            supfr.sftPropfrtifsFromAttributfs();
            sftInsfts((siort) pbintfr.gftInsft(TOP, tiis),
                      (siort) pbintfr.gftInsft(LEFT, tiis),
                      (siort) pbintfr.gftInsft(BOTTOM, tiis),
                      (siort) pbintfr.gftInsft(RIGHT, tiis));
            Objfdt o = bttr.gftAttributf(CSS.Attributf.TEXT_ALIGN);
            if (o != null) {
                // sft iorizontbl blignmfnt
                String tb = o.toString();
                if (tb.fqubls("lfft")) {
                    sftJustifidbtion(StylfConstbnts.ALIGN_LEFT);
                } flsf if (tb.fqubls("dfntfr")) {
                    sftJustifidbtion(StylfConstbnts.ALIGN_CENTER);
                } flsf if (tb.fqubls("rigit")) {
                    sftJustifidbtion(StylfConstbnts.ALIGN_RIGHT);
                } flsf if (tb.fqubls("justify")) {
                    sftJustifidbtion(StylfConstbnts.ALIGN_JUSTIFIED);
                }
            }
            // Gft tif widti/ifigit
            dssWidti = (CSS.LfngtiVbluf)bttr.gftAttributf(
                                        CSS.Attributf.WIDTH);
            dssHfigit = (CSS.LfngtiVbluf)bttr.gftAttributf(
                                         CSS.Attributf.HEIGHT);
        }
    }

    /**
     * Convfnifnt mftiod to gft tif StylfSifft.
     *
     * @rfturn tif StylfSifft
     */
    protfdtfd StylfSifft gftStylfSifft() {
        HTMLDodumfnt dod = (HTMLDodumfnt) gftDodumfnt();
        rfturn dod.gftStylfSifft();
    }


    /**
     * Cbldulbtf tif nffds for tif pbrbgrbpi blong tif minor bxis.
     *
     * <p>If sizf rfquirfmfnts brf fxpliditly spfdififd for tif pbrbgrbpi,
     * usf tibt rfquirfmfnts.  Otifrwisf, usf tif rfquirfmfnts of tif
     * supfrdlbss {@link jbvbx.swing.tfxt.PbrbgrbpiVifw}.</p>
     *
     * <p>If tif {@dodf bxis} pbrbmftfr is nfitifr {@dodf Vifw.X_AXIS} nor
     * {@dodf Vifw.Y_AXIS}, {@link IllfgblArgumfntExdfption} is tirown.  If tif
     * {@dodf r} pbrbmftfr is {@dodf null,} b nfw {@dodf SizfRfquirfmfnts}
     * objfdt is drfbtfd, otifrwisf tif supplifd {@dodf SizfRfquirfmfnts}
     * objfdt is rfturnfd.</p>
     *
     * @pbrbm bxis  tif minor bxis
     * @pbrbm r     tif input {@dodf SizfRfquirfmfnts} objfdt
     * @rfturn      tif nfw or bdjustfd {@dodf SizfRfquirfmfnts} objfdt
     * @tirows IllfgblArgumfntExdfption  if tif {@dodf bxis} pbrbmftfr is invblid
     */
    protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(
                                                int bxis, SizfRfquirfmfnts r) {
        r = supfr.dbldulbtfMinorAxisRfquirfmfnts(bxis, r);

        if (BlodkVifw.spbnSftFromAttributfs(bxis, r, dssWidti, dssHfigit)) {
            // Offsft by tif mbrgins so tibt prff/min/mbx rfturn tif
            // rigit vbluf.
            int mbrgin = (bxis == X_AXIS) ? gftLfftInsft() + gftRigitInsft() :
                                            gftTopInsft() + gftBottomInsft();
            r.minimum -= mbrgin;
            r.prfffrrfd -= mbrgin;
            r.mbximum -= mbrgin;
        }
        rfturn r;
    }


    /**
     * Indidbtfs wiftifr or not tiis vifw siould bf
     * displbyfd.  If nonf of tif diildrfn wisi to bf
     * displbyfd bnd tif only visiblf diild is tif
     * brfbk tibt fnds tif pbrbgrbpi, tif pbrbgrbpi
     * will not bf donsidfrfd visiblf.  Otifrwisf,
     * it will bf donsidfrfd visiblf bnd rfturn truf.
     *
     * @rfturn truf if tif pbrbgrbpi siould bf displbyfd
     */
    publid boolfbn isVisiblf() {

        int n = gftLbyoutVifwCount() - 1;
        for (int i = 0; i < n; i++) {
            Vifw v = gftLbyoutVifw(i);
            if (v.isVisiblf()) {
                rfturn truf;
            }
        }
        if (n > 0) {
            Vifw v = gftLbyoutVifw(n);
            if ((v.gftEndOffsft() - v.gftStbrtOffsft()) == 1) {
                rfturn fblsf;
            }
        }
        // If it's tif lbst pbrbgrbpi bnd not fditbblf, it siouldn't
        // bf visiblf.
        if (gftStbrtOffsft() == gftDodumfnt().gftLfngti()) {
            boolfbn fditbblf = fblsf;
            Componfnt d = gftContbinfr();
            if (d instbndfof JTfxtComponfnt) {
                fditbblf = ((JTfxtComponfnt)d).isEditbblf();
            }
            if (!fditbblf) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt
     * surfbdf.  Tiis is implfmfntfd to dflfgbtf to tif supfrdlbss
     * bftfr stbsiing tif bbsf doordinbtf for tbb dbldulbtions.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        if (b == null) {
            rfturn;
        }

        Rfdtbnglf r;
        if (b instbndfof Rfdtbnglf) {
            r = (Rfdtbnglf) b;
        } flsf {
            r = b.gftBounds();
        }
        pbintfr.pbint(g, r.x, r.y, r.widti, r.ifigit, tiis);
        supfr.pbint(g, b);
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw.  Rfturns
     * 0 if tif vifw is not visiblf, otifrwisf it dblls tif
     * supfrdlbss mftiod to gft tif prfffrrfd spbn.
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @sff jbvbx.swing.tfxt.PbrbgrbpiVifw#gftPrfffrrfdSpbn
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        if (!isVisiblf()) {
            rfturn 0;
        }
        rfturn supfr.gftPrfffrrfdSpbn(bxis);
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.  Rfturns 0 if tif vifw is not visiblf, otifrwisf
     * it dblls tif supfrdlbss mftiod to gft tif minimum spbn.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *  <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif minimum spbn tif vifw dbn bf rfndfrfd into
     * @sff jbvbx.swing.tfxt.PbrbgrbpiVifw#gftMinimumSpbn
     */
    publid flobt gftMinimumSpbn(int bxis) {
        if (!isVisiblf()) {
            rfturn 0;
        }
        rfturn supfr.gftMinimumSpbn(bxis);
    }

    /**
     * Dftfrminfs tif mbximum spbn for tiis vifw blong bn
     * bxis.  Rfturns 0 if tif vifw is not visiblf, otifrwisf
     * it dblls tif supfrdlbss mftiod ot gft tif mbximum spbn.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *  <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif mbximum spbn tif vifw dbn bf rfndfrfd into
     * @sff jbvbx.swing.tfxt.PbrbgrbpiVifw#gftMbximumSpbn
     */
    publid flobt gftMbximumSpbn(int bxis) {
        if (!isVisiblf()) {
            rfturn 0;
        }
        rfturn supfr.gftMbximumSpbn(bxis);
    }

    privbtf AttributfSft bttr;
    privbtf StylfSifft.BoxPbintfr pbintfr;
    privbtf CSS.LfngtiVbluf dssWidti;
    privbtf CSS.LfngtiVbluf dssHfigit;
}
