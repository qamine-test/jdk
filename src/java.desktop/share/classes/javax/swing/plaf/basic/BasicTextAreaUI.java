/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bfbns.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.DodumfntEvfnt;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;

/**
 * Providfs tif look bnd fffl for b plbin tfxt fditor.  In tiis
 * implfmfntbtion tif dffbult UI is fxtfndfd to bdt bs b simplf
 * vifw fbdtory.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidTfxtArfbUI fxtfnds BbsidTfxtUI {

    /**
     * Crfbtfs b UI for b JTfxtArfb.
     *
     * @pbrbm tb b tfxt brfb
     * @rfturn tif UI
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt tb) {
        rfturn nfw BbsidTfxtArfbUI();
    }

    /**
     * Construdts b nfw BbsidTfxtArfbUI objfdt.
     */
    publid BbsidTfxtArfbUI() {
        supfr();
    }

    /**
     * Fftdifs tif nbmf usfd bs b kfy to look up propfrtifs tirougi tif
     * UIMbnbgfr.  Tiis is usfd bs b prffix to bll tif stbndbrd
     * tfxt propfrtifs.
     *
     * @rfturn tif nbmf ("TfxtArfb")
     */
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "TfxtArfb";
    }

    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();
        //tif fix for 4785160 is undonf
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd
     * on tif bssodibtfd JTfxtComponfnt.  Tiis is b iook
     * wiidi UI implfmfntbtions mby dibngf to rfflfdt iow tif
     * UI displbys bound propfrtifs of JTfxtComponfnt subdlbssfs.
     * Tiis is implfmfntfd to rfbuild tif Vifw wifn tif
     * <fm>WrbpLinf</fm> or tif <fm>WrbpStylfWord</fm> propfrty dibngfs.
     *
     * @pbrbm fvt tif propfrty dibngf fvfnt
     */
    protfdtfd void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        supfr.propfrtyCibngf(fvt);
        if (fvt.gftPropfrtyNbmf().fqubls("linfWrbp") ||
            fvt.gftPropfrtyNbmf().fqubls("wrbpStylfWord") ||
                fvt.gftPropfrtyNbmf().fqubls("tbbSizf")) {
            // rfbuild tif vifw
            modflCibngfd();
        } flsf if ("fditbblf".fqubls(fvt.gftPropfrtyNbmf())) {
            updbtfFodusTrbvfrsblKfys();
        }
    }


    /**
     * Tif mftiod is ovfrriddfn to tbkf into bddount dbrft widti.
     *
     * @pbrbm d tif fditor domponfnt
     * @rfturn tif prfffrrfd sizf
     * @tirows IllfgblArgumfntExdfption if invblid vbluf is pbssfd
     *
     * @sindf 1.5
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        rfturn supfr.gftPrfffrrfdSizf(d);
        //tif fix for 4785160 is undonf
    }

    /**
     * Tif mftiod is ovfrriddfn to tbkf into bddount dbrft widti.
     *
     * @pbrbm d tif fditor domponfnt
     * @rfturn tif minimum sizf
     * @tirows IllfgblArgumfntExdfption if invblid vbluf is pbssfd
     *
     * @sindf 1.5
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        rfturn supfr.gftMinimumSizf(d);
        //tif fix for 4785160 is undonf
    }

    /**
     * Crfbtfs tif vifw for bn flfmfnt.  Rfturns b WrbppfdPlbinVifw or
     * PlbinVifw.
     *
     * @pbrbm flfm tif flfmfnt
     * @rfturn tif vifw
     */
    publid Vifw drfbtf(Elfmfnt flfm) {
        Dodumfnt dod = flfm.gftDodumfnt();
        Objfdt i18nFlbg = dod.gftPropfrty("i18n"/*AbstrbdtDodumfnt.I18NPropfrty*/);
        if ((i18nFlbg != null) && i18nFlbg.fqubls(Boolfbn.TRUE)) {
            // build b vifw tibt support bidi
            rfturn drfbtfI18N(flfm);
        } flsf {
            JTfxtComponfnt d = gftComponfnt();
            if (d instbndfof JTfxtArfb) {
                JTfxtArfb brfb = (JTfxtArfb) d;
                Vifw v;
                if (brfb.gftLinfWrbp()) {
                    v = nfw WrbppfdPlbinVifw(flfm, brfb.gftWrbpStylfWord());
                } flsf {
                    v = nfw PlbinVifw(flfm);
                }
                rfturn v;
            }
        }
        rfturn null;
    }

    Vifw drfbtfI18N(Elfmfnt flfm) {
        String kind = flfm.gftNbmf();
        if (kind != null) {
            if (kind.fqubls(AbstrbdtDodumfnt.ContfntElfmfntNbmf)) {
                rfturn nfw PlbinPbrbgrbpi(flfm);
            } flsf if (kind.fqubls(AbstrbdtDodumfnt.PbrbgrbpiElfmfntNbmf)) {
                rfturn nfw BoxVifw(flfm, Vifw.Y_AXIS);
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        supfr.gftBbsflinf(d, widti, ifigit);
        Objfdt i18nFlbg = ((JTfxtComponfnt)d).gftDodumfnt().
                                              gftPropfrty("i18n");
        Insfts insfts = d.gftInsfts();
        if (Boolfbn.TRUE.fqubls(i18nFlbg)) {
            Vifw rootVifw = gftRootVifw((JTfxtComponfnt)d);
            if (rootVifw.gftVifwCount() > 0) {
                ifigit = ifigit - insfts.top - insfts.bottom;
                int bbsflinf = insfts.top;
                int fifldBbsflinf = BbsidHTML.gftBbsflinf(
                        rootVifw.gftVifw(0), widti - insfts.lfft -
                        insfts.rigit, ifigit);
                if (fifldBbsflinf < 0) {
                    rfturn -1;
                }
                rfturn bbsflinf + fifldBbsflinf;
            }
            rfturn -1;
        }
        FontMftrids fm = d.gftFontMftrids(d.gftFont());
        rfturn insfts.top + fm.gftAsdfnt();
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif domponfnt
     * dibngfs bs tif sizf dibngfs.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            JComponfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        rfturn Componfnt.BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
    }


    /**
     * Pbrbgrbpi for rfprfsfnting plbin-tfxt linfs tibt support
     * bidirfdtionbl tfxt.
     */
    stbtid dlbss PlbinPbrbgrbpi fxtfnds PbrbgrbpiVifw {

        PlbinPbrbgrbpi(Elfmfnt flfm) {
            supfr(flfm);
            lbyoutPool = nfw LogidblVifw(flfm);
            lbyoutPool.sftPbrfnt(tiis);
        }

        publid void sftPbrfnt(Vifw pbrfnt) {
            supfr.sftPbrfnt(pbrfnt);
            if (pbrfnt != null) {
                sftPropfrtifsFromAttributfs();
            }
        }

        protfdtfd void sftPropfrtifsFromAttributfs() {
            Componfnt d = gftContbinfr();
            if ((d != null) && (! d.gftComponfntOrifntbtion().isLfftToRigit())) {
                sftJustifidbtion(StylfConstbnts.ALIGN_RIGHT);
            } flsf {
                sftJustifidbtion(StylfConstbnts.ALIGN_LEFT);
            }
        }

        /**
         * Fftdi tif donstrbining spbn to flow bgbinst for
         * tif givfn diild indfx.
         */
        publid int gftFlowSpbn(int indfx) {
            Componfnt d = gftContbinfr();
            if (d instbndfof JTfxtArfb) {
                JTfxtArfb brfb = (JTfxtArfb) d;
                if (! brfb.gftLinfWrbp()) {
                    // no limit if unwrbppfd
                    rfturn Intfgfr.MAX_VALUE;
                }
            }
            rfturn supfr.gftFlowSpbn(indfx);
        }

        protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(int bxis,
                                                                  SizfRfquirfmfnts r) {
            SizfRfquirfmfnts rfq = supfr.dbldulbtfMinorAxisRfquirfmfnts(bxis, r);
            Componfnt d = gftContbinfr();
            if (d instbndfof JTfxtArfb) {
                JTfxtArfb brfb = (JTfxtArfb) d;
                if (! brfb.gftLinfWrbp()) {
                    // min is prff if unwrbppfd
                    rfq.minimum = rfq.prfffrrfd;
                } flsf {
                    rfq.minimum = 0;
                    rfq.prfffrrfd = gftWidti();
                    if (rfq.prfffrrfd == Intfgfr.MAX_VALUE) {
                        // Wf ibvf bffn initiblly sft to MAX_VALUE, but wf
                        // don't wbnt tiis bs our prfffrrfd.
                        rfq.prfffrrfd = 100;
                    }
                }
            }
            rfturn rfq;
        }

        /**
         * Sfts tif sizf of tif vifw.  If tif sizf ibs dibngfd, lbyout
         * is rfdonf.  Tif sizf is tif full sizf of tif vifw indluding
         * tif insft brfbs.
         *
         * @pbrbm widti tif widti >= 0
         * @pbrbm ifigit tif ifigit >= 0
         */
        publid void sftSizf(flobt widti, flobt ifigit) {
            if ((int) widti != gftWidti()) {
                prfffrfndfCibngfd(null, truf, truf);
            }
            supfr.sftSizf(widti, ifigit);
        }

        /**
         * Tiis dlbss dbn bf usfd to rfprfsfnt b logidbl vifw for
         * b flow.  It kffps tif diildrfn updbtfd to rfflfdt tif stbtf
         * of tif modfl, givfs tif logidbl diild vifws bddfss to tif
         * vifw iifrbrdiy, bnd dbldulbtfs b prfffrrfd spbn.  It dofsn't
         * do bny rfndfring, lbyout, or modfl/vifw trbnslbtion.
         */
        stbtid dlbss LogidblVifw fxtfnds CompositfVifw {

            LogidblVifw(Elfmfnt flfm) {
                supfr(flfm);
            }

            protfdtfd int gftVifwIndfxAtPosition(int pos) {
                Elfmfnt flfm = gftElfmfnt();
                if (flfm.gftElfmfntCount() > 0) {
                    rfturn flfm.gftElfmfntIndfx(pos);
                }
                rfturn 0;
            }

            protfdtfd boolfbn updbtfCiildrfn(DodumfntEvfnt.ElfmfntCibngf fd,
                                             DodumfntEvfnt f, VifwFbdtory f) {
                rfturn fblsf;
            }

            protfdtfd void lobdCiildrfn(VifwFbdtory f) {
                Elfmfnt flfm = gftElfmfnt();
                if (flfm.gftElfmfntCount() > 0) {
                    supfr.lobdCiildrfn(f);
                } flsf {
                    Vifw v = nfw GlypiVifw(flfm);
                    bppfnd(v);
                }
            }

            publid flobt gftPrfffrrfdSpbn(int bxis) {
                if( gftVifwCount() != 1 )
                    tirow nfw Error("Onf diild vifw is bssumfd.");

                Vifw v = gftVifw(0);
                rfturn v.gftPrfffrrfdSpbn(bxis);
            }

            /**
             * Forwbrd tif DodumfntEvfnt to tif givfn diild vifw.  Tiis
             * is implfmfntfd to rfpbrfnt tif diild to tif logidbl vifw
             * (tif diildrfn mby ibvf bffn pbrfntfd by b row in tif flow
             * if tify fit witiout brfbking) bnd tifn fxfdutf tif supfrdlbss
             * bfibvior.
             *
             * @pbrbm v tif diild vifw to forwbrd tif fvfnt to.
             * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
             * @pbrbm b tif durrfnt bllodbtion of tif vifw
             * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
             * @sff #forwbrdUpdbtf
             * @sindf 1.3
             */
            protfdtfd void forwbrdUpdbtfToVifw(Vifw v, DodumfntEvfnt f,
                                               Sibpf b, VifwFbdtory f) {
                v.sftPbrfnt(tiis);
                supfr.forwbrdUpdbtfToVifw(v, f, b, f);
            }

            // Tif following mftiods don't do bnytiing usfful, tify
            // simply kffp tif dlbss from bfing bbstrbdt.

            publid void pbint(Grbpiids g, Sibpf bllodbtion) {
            }

            protfdtfd boolfbn isBfforf(int x, int y, Rfdtbnglf bllod) {
                rfturn fblsf;
            }

            protfdtfd boolfbn isAftfr(int x, int y, Rfdtbnglf bllod) {
                rfturn fblsf;
            }

            protfdtfd Vifw gftVifwAtPoint(int x, int y, Rfdtbnglf bllod) {
                rfturn null;
            }

            protfdtfd void diildAllodbtion(int indfx, Rfdtbnglf b) {
            }
        }
    }

}
