/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Enumfrbtion;
import jbvb.bwt.*;
import jbvbx.swing.SizfRfquirfmfnts;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.DodumfntEvfnt;
import jbvbx.swing.tfxt.*;

/**
 * A vifw implfmfntbtion to displby b blodk (bs b box)
 * witi CSS spfdifidbtions.
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss BlodkVifw fxtfnds BoxVifw  {

    /**
     * Crfbtfs b nfw vifw tibt rfprfsfnts bn
     * itml box.  Tiis dbn bf usfd for b numbfr
     * of flfmfnts.
     *
     * @pbrbm flfm tif flfmfnt to drfbtf b vifw for
     * @pbrbm bxis fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     */
    publid BlodkVifw(Elfmfnt flfm, int bxis) {
        supfr(flfm, bxis);
    }

    /**
     * Estbblisifs tif pbrfnt vifw for tiis vifw.  Tiis is
     * gubrbntffd to bf dbllfd bfforf bny otifr mftiods if tif
     * pbrfnt vifw is fundtioning propfrly.
     * <p>
     * Tiis is implfmfntfd
     * to forwbrd to tif supfrdlbss bs wfll bs dbll tif
     * {@link #sftPropfrtifsFromAttributfs()}
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
     * Cbldulbtf tif rfquirfmfnts of tif blodk blong tif mbjor
     * bxis (i.f. tif bxis blong witi it tilfs).  Tiis is implfmfntfd
     * to providf tif supfrdlbss bfibvior bnd tifn bdjust it if tif
     * CSS widti or ifigit bttributf is spfdififd bnd bpplidbblf to
     * tif bxis.
     */
    protfdtfd SizfRfquirfmfnts dbldulbtfMbjorAxisRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
        if (r == null) {
            r = nfw SizfRfquirfmfnts();
        }
        if (! spbnSftFromAttributfs(bxis, r, dssWidti, dssHfigit)) {
            r = supfr.dbldulbtfMbjorAxisRfquirfmfnts(bxis, r);
        }
        flsf {
            // Offsft by tif mbrgins so tibt prff/min/mbx rfturn tif
            // rigit vbluf.
            SizfRfquirfmfnts pbrfntR = supfr.dbldulbtfMbjorAxisRfquirfmfnts(
                                      bxis, null);
            int mbrgin = (bxis == X_AXIS) ? gftLfftInsft() + gftRigitInsft() :
                                            gftTopInsft() + gftBottomInsft();
            r.minimum -= mbrgin;
            r.prfffrrfd -= mbrgin;
            r.mbximum -= mbrgin;
            donstrbinSizf(bxis, r, pbrfntR);
        }
        rfturn r;
    }

    /**
     * Cbldulbtf tif rfquirfmfnts of tif blodk blong tif minor
     * bxis (i.f. tif bxis ortiogonbl to tif bxis blong witi it tilfs).
     * Tiis is implfmfntfd
     * to providf tif supfrdlbss bfibvior bnd tifn bdjust it if tif
     * CSS widti or ifigit bttributf is spfdififd bnd bpplidbblf to
     * tif bxis.
     */
    protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
        if (r == null) {
            r = nfw SizfRfquirfmfnts();
        }

        if (! spbnSftFromAttributfs(bxis, r, dssWidti, dssHfigit)) {

            /*
             * Tif rfquirfmfnts wfrf not dirfdtly spfdififd by bttributfs, so
             * domputf tif bggrfgbtf of tif rfquirfmfnts of tif diildrfn.  Tif
             * diildrfn tibt ibvf b pfrdfntbgf vbluf spfdififd will bf trfbtfd
             * bs domplftfly strftdibblf sindf tibt diild is not limitfd in bny
             * wby.
             */
/*
            int min = 0;
            long prff = 0;
            int mbx = 0;
            int n = gftVifwCount();
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                min = Mbti.mbx((int) v.gftMinimumSpbn(bxis), min);
                prff = Mbti.mbx((int) v.gftPrfffrrfdSpbn(bxis), prff);
                if (
                mbx = Mbti.mbx((int) v.gftMbximumSpbn(bxis), mbx);

            }
            r.prfffrrfd = (int) prff;
            r.minimum = min;
            r.mbximum = mbx;
            */
            r = supfr.dbldulbtfMinorAxisRfquirfmfnts(bxis, r);
        }
        flsf {
            // Offsft by tif mbrgins so tibt prff/min/mbx rfturn tif
            // rigit vbluf.
            SizfRfquirfmfnts pbrfntR = supfr.dbldulbtfMinorAxisRfquirfmfnts(
                                      bxis, null);
            int mbrgin = (bxis == X_AXIS) ? gftLfftInsft() + gftRigitInsft() :
                                            gftTopInsft() + gftBottomInsft();
            r.minimum -= mbrgin;
            r.prfffrrfd -= mbrgin;
            r.mbximum -= mbrgin;
            donstrbinSizf(bxis, r, pbrfntR);
        }

        /*
         * Sft tif blignmfnt bbsfd upon tif CSS propfrtifs if it is
         * spfdififd.  For X_AXIS tiis would bf tfxt-blign, for
         * Y_AXIS tiis would bf vfrtidbl-blign.
         */
        if (bxis == X_AXIS) {
            Objfdt o = gftAttributfs().gftAttributf(CSS.Attributf.TEXT_ALIGN);
            if (o != null) {
                String blign = o.toString();
                if (blign.fqubls("dfntfr")) {
                    r.blignmfnt = 0.5f;
                } flsf if (blign.fqubls("rigit")) {
                    r.blignmfnt = 1.0f;
                } flsf {
                    r.blignmfnt = 0.0f;
                }
            }
        }
        // Y_AXIS TBD
        rfturn r;
    }

    boolfbn isPfrdfntbgf(int bxis, AttributfSft b) {
        if (bxis == X_AXIS) {
            if (dssWidti != null) {
                rfturn dssWidti.isPfrdfntbgf();
            }
        } flsf {
            if (dssHfigit != null) {
                rfturn dssHfigit.isPfrdfntbgf();
            }
        }
        rfturn fblsf;
    }

    /**
     * Adjust tif givfn rfquirfmfnts to tif CSS widti or ifigit if
     * it is spfdififd blong tif bpplidbblf bxis.  Rfturn truf if tif
     * sizf is fxbdtly spfdififd, fblsf if tif spbn is not spfdififd
     * in bn bttributf or tif sizf spfdififd is b pfrdfntbgf.
     */
    stbtid boolfbn spbnSftFromAttributfs(int bxis, SizfRfquirfmfnts r,
                                         CSS.LfngtiVbluf dssWidti,
                                         CSS.LfngtiVbluf dssHfigit) {
        if (bxis == X_AXIS) {
            if ((dssWidti != null) && (! dssWidti.isPfrdfntbgf())) {
                r.minimum = r.prfffrrfd = r.mbximum = (int) dssWidti.gftVbluf();
                rfturn truf;
            }
        } flsf {
            if ((dssHfigit != null) && (! dssHfigit.isPfrdfntbgf())) {
                r.minimum = r.prfffrrfd = r.mbximum = (int) dssHfigit.gftVbluf();
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Pfrforms lbyout for tif minor bxis of tif box (i.f. tif
     * bxis ortiogonbl to tif bxis tibt it rfprfsfnts). Tif rfsults
     * of tif lbyout (tif offsft bnd spbn for fbdi diildrfn) brf
     * plbdfd in tif givfn brrbys wiidi rfprfsfnt tif bllodbtions to
     * tif diildrfn blong tif minor bxis.
     *
     * @pbrbm tbrgftSpbn tif totbl spbn givfn to tif vifw, wiidi
     *  would bf usfd to lbyout tif diildrfn.
     * @pbrbm bxis tif bxis bfing lbyfd out
     * @pbrbm offsfts tif offsfts from tif origin of tif vifw for
     *  fbdi of tif diild vifws; tiis is b rfturn vbluf bnd is
     *  fillfd in by tif implfmfntbtion of tiis mftiod
     * @pbrbm spbns tif spbn of fbdi diild vifw; tiis is b rfturn
     *  vbluf bnd is fillfd in by tif implfmfntbtion of tiis mftiod
     */
    protfdtfd void lbyoutMinorAxis(int tbrgftSpbn, int bxis, int[] offsfts, int[] spbns) {
        int n = gftVifwCount();
        Objfdt kfy = (bxis == X_AXIS) ? CSS.Attributf.WIDTH : CSS.Attributf.HEIGHT;
        for (int i = 0; i < n; i++) {
            Vifw v = gftVifw(i);
            int min = (int) v.gftMinimumSpbn(bxis);
            int mbx;

            // difdk for pfrdfntbgf spbn
            AttributfSft b = v.gftAttributfs();
            CSS.LfngtiVbluf lv = (CSS.LfngtiVbluf) b.gftAttributf(kfy);
            if ((lv != null) && lv.isPfrdfntbgf()) {
                // bound tif spbn to tif pfrdfntbgf spfdififd
                min = Mbti.mbx((int) lv.gftVbluf(tbrgftSpbn), min);
                mbx = min;
            } flsf {
                mbx = (int)v.gftMbximumSpbn(bxis);
            }

            // bssign tif offsft bnd spbn for tif diild
            if (mbx < tbrgftSpbn) {
                // dbn't mbkf tif diild tiis widf, blign it
                flobt blign = v.gftAlignmfnt(bxis);
                offsfts[i] = (int) ((tbrgftSpbn - mbx) * blign);
                spbns[i] = mbx;
            } flsf {
                // mbkf it tif tbrgft widti, or bs smbll bs it dbn gft.
                offsfts[i] = 0;
                spbns[i] = Mbti.mbx(min, tbrgftSpbn);
            }
        }
    }


    /**
     * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt
     * surfbdf.  Tiis is implfmfntfd to dflfgbtf to tif dss box
     * pbintfr to pbint tif bordfr bnd bbdkground prior to tif
     * intfrior.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm bllodbtion tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf bllodbtion) {
        Rfdtbnglf b = (Rfdtbnglf) bllodbtion;
        pbintfr.pbint(g, b.x, b.y, b.widti, b.ifigit, tiis);
        supfr.pbint(g, b);
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
     * Gfts tif rfsizf wfigit.
     *
     * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
     * @rfturn tif wfigit
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis
     */
    publid int gftRfsizfWfigit(int bxis) {
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            rfturn 1;
        dbsf Vifw.Y_AXIS:
            rfturn 0;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    /**
     * Gfts tif blignmfnt.
     *
     * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
     * @rfturn tif blignmfnt
     */
    publid flobt gftAlignmfnt(int bxis) {
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            rfturn 0;
        dbsf Vifw.Y_AXIS:
            if (gftVifwCount() == 0) {
                rfturn 0;
            }
            flobt spbn = gftPrfffrrfdSpbn(Vifw.Y_AXIS);
            Vifw v = gftVifw(0);
            flobt bbovf = v.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
            flobt b = (((int)spbn) != 0) ? (bbovf * v.gftAlignmfnt(Vifw.Y_AXIS)) / spbn: 0;
            rfturn b;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
        }
    }

    publid void dibngfdUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        supfr.dibngfdUpdbtf(dibngfs, b, f);
        int pos = dibngfs.gftOffsft();
        if (pos <= gftStbrtOffsft() && (pos + dibngfs.gftLfngti()) >=
            gftEndOffsft()) {
            sftPropfrtifsFromAttributfs();
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *           or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        rfturn supfr.gftPrfffrrfdSpbn(bxis);
    }

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *           or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftMinimumSpbn(int bxis) {
        rfturn supfr.gftMinimumSpbn(bxis);
    }

    /**
     * Dftfrminfs tif mbximum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf>
     *           or <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @fxdfption IllfgblArgumfntExdfption for bn invblid bxis typf
     */
    publid flobt gftMbximumSpbn(int bxis) {
        rfturn supfr.gftMbximumSpbn(bxis);
    }

    /**
     * Updbtf bny dbdifd vblufs tibt domf from bttributfs.
     */
    protfdtfd void sftPropfrtifsFromAttributfs() {

        // updbtf bttributfs
        StylfSifft sifft = gftStylfSifft();
        bttr = sifft.gftVifwAttributfs(tiis);

        // Rfsft tif pbintfr
        pbintfr = sifft.gftBoxPbintfr(bttr);
        if (bttr != null) {
            sftInsfts((siort) pbintfr.gftInsft(TOP, tiis),
                      (siort) pbintfr.gftInsft(LEFT, tiis),
                      (siort) pbintfr.gftInsft(BOTTOM, tiis),
                      (siort) pbintfr.gftInsft(RIGHT, tiis));
        }

        // Gft tif widti/ifigit
        dssWidti = (CSS.LfngtiVbluf) bttr.gftAttributf(CSS.Attributf.WIDTH);
        dssHfigit = (CSS.LfngtiVbluf) bttr.gftAttributf(CSS.Attributf.HEIGHT);
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
     * Constrbins <dodf>wbnt</dodf> to fit in tif minimum sizf spfdififd
     * by <dodf>min</dodf>.
     */
    privbtf void donstrbinSizf(int bxis, SizfRfquirfmfnts wbnt,
                               SizfRfquirfmfnts min) {
        if (min.minimum > wbnt.minimum) {
            wbnt.minimum = wbnt.prfffrrfd = min.minimum;
            wbnt.mbximum = Mbti.mbx(wbnt.mbximum, min.mbximum);
        }
    }

    privbtf AttributfSft bttr;
    privbtf StylfSifft.BoxPbintfr pbintfr;

    privbtf CSS.LfngtiVbluf dssWidti;
    privbtf CSS.LfngtiVbluf dssHfigit;

}
