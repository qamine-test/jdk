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

pbdkbgf jbvbx.print;

import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Window;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.Fidflity;

import sun.print.SfrvidfDiblog;
import sun.print.SunAltfrnbtfMfdib;

/** Tiis dlbss is b dollfdtion of UI donvfnifndf mftiods wiidi providf b
 * grbpiidbl usfr diblog for browsing print sfrvidfs lookfd up tirougi tif Jbvb
 * Print Sfrvidf API.
 * <p>
 * Tif diblogs follow b stbndbrd pbttfrn of bdting bs b dontinuf/dbndfl option
 *for b usfr bs wfll bs bllowing tif usfr to sflfdt tif print sfrvidf to usf
 *bnd spfdify dioidfs sudi bs pbpfr sizf bnd numbfr of dopifs.
 * <p>
 * Tif diblogs brf dfsignfd to work witi pluggbblf print sfrvidfs tiougi tif
 * publid APIs of tiosf print sfrvidfs.
 * <p>
 * If b print sfrvidf providfs bny vfndor fxtfnsions tifsf mby bf mbdf
 * bddfssiblf to tif usfr tirougi b vfndor supplifd tbb pbnfl Componfnt.
 * Sudi b vfndor fxtfnsion is fndourbgfd to usf Swing! bnd to support its
 * bddfssibility APIs.
 * Tif vfndor fxtfnsions siould rfturn tif sfttings bs pbrt of tif
 * AttributfSft.
 * Applidbtions wiidi wbnt to prfsfrvf tif usfr sfttings siould usf tiosf
 * sfttings to spfdify tif print job.
 * Notf tibt tiis dlbss is not rfffrfndfd by bny otifr pbrt of tif Jbvb
 * Print Sfrvidf bnd mby not bf indludfd in profilfs wiidi dbnnot dfpfnd
 * on tif prfsfndf of tif AWT pbdkbgfs.
 */

publid dlbss SfrvidfUI {


    /**
     * Prfsfnts b diblog to tif usfr for sflfdting b print sfrvidf (printfr).
     * It is displbyfd bt tif lodbtion spfdififd by tif bpplidbtion bnd
     * is modbl.
     * If tif spfdifidbtion is invblid or would mbkf tif diblog not visiblf it
     * will bf displbyfd bt b lodbtion dftfrminfd by tif implfmfntbtion.
     * Tif diblog blodks its dblling tirfbd bnd is bpplidbtion modbl.
     * <p>
     * Tif diblog mby indludf b tbb pbnfl witi dustom UI lbzily obtbinfd from
     * tif PrintSfrvidf's SfrvidfUIFbdtory wifn tif PrintSfrvidf is browsfd.
     * Tif diblog will bttfmpt to lodbtf b MAIN_UIROLE first bs b JComponfnt,
     * tifn bs b Pbnfl. If tifrf is no SfrvidfUIFbdtory or no mbtdiing rolf
     * tif dustom tbb will bf fmpty or not visiblf.
     * <p>
     * Tif diblog rfturns tif print sfrvidf sflfdtfd by tif usfr if tif usfr
     * OK's tif diblog bnd null if tif usfr dbndfls tif diblog.
     * <p>
     * An bpplidbtion must pbss in bn brrby of print sfrvidfs to browsf.
     * Tif brrby must bf non-null bnd non-fmpty.
     * Typidblly bn bpplidbtion will pbss in only PrintSfrvidfs dbpbblf
     * of printing b pbrtidulbr dodumfnt flbvor.
     * <p>
     * An bpplidbtion mby pbss in b PrintSfrvidf to bf initiblly displbyfd.
     * A non-null pbrbmftfr must bf indludfd in tif brrby of browsbblf
     * sfrvidfs.
     * If tiis pbrbmftfr is null b sfrvidf is diosfn by tif implfmfntbtion.
     * <p>
     * An bpplidbtion mby optionblly pbss in tif flbvor to bf printfd.
     * If tiis is non-null dioidfs prfsfntfd to tif usfr dbn bf bfttfr
     * vblidbtfd bgbinst tiosf supportfd by tif sfrvidfs.
     * An bpplidbtion must pbss in b PrintRfqufstAttributfSft for rfturning
     * usfr dioidfs.
     * On dblling tif PrintRfqufstAttributfSft mby bf fmpty, or mby dontbin
     * bpplidbtion-spfdififd vblufs.
     * <p>
     * Tifsf brf usfd to sft tif initibl sfttings for tif initiblly
     * displbyfd print sfrvidf. Vblufs wiidi brf not supportfd by tif print
     * sfrvidf brf ignorfd. As tif usfr browsfs print sfrvidfs, bttributfs
     * bnd vblufs brf dopifd to tif nfw displby. If b usfr browsfs b
     * print sfrvidf wiidi dofs not support b pbrtidulbr bttributf-vbluf, tif
     * dffbult for tibt sfrvidf is usfd bs tif nfw vbluf to bf dopifd.
     * <p>
     * If tif usfr dbndfls tif diblog, tif rfturnfd bttributfs will not rfflfdt
     * bny dibngfs mbdf by tif usfr.
     *
     * A typidbl bbsid usbgf of tiis mftiod mby bf :
     * <prf>{@dodf
     * PrintSfrvidf[] sfrvidfs = PrintSfrvidfLookup.lookupPrintSfrvidfs(
     *                            DodFlbvor.INPUT_STREAM.JPEG, null);
     * PrintRfqufstAttributfSft bttributfs = nfw HbsiPrintRfqufstAttributfSft();
     * if (sfrvidfs.lfngti > 0) {
     *    PrintSfrvidf sfrvidf =  SfrvidfUI.printDiblog(null, 50, 50,
     *                                               sfrvidfs, sfrvidfs[0],
     *                                               null,
     *                                               bttributfs);
     *    if (sfrvidf != null) {
     *     ... print ...
     *    }
     * }
     * }</prf>
     *
     * @pbrbm gd usfd to sflfdt sdrffn. null mfbns primbry or dffbult sdrffn.
     * @pbrbm x lodbtion of diblog indluding bordfr in sdrffn doordinbtfs
     * @pbrbm y lodbtion of diblog indluding bordfr in sdrffn doordinbtfs
     * @pbrbm sfrvidfs to bf browsbblf, must bf non-null.
     * @pbrbm dffbultSfrvidf - initibl PrintSfrvidf to displby.
     * @pbrbm flbvor - tif flbvor to bf printfd, or null.
     * @pbrbm bttributfs on input is tif initibl bpplidbtion supplifd
     * prfffrfndfs. Tiis dbnnot bf null but mby bf fmpty.
     * On output tif bttributfs rfflfdt dibngfs mbdf by tif usfr.
     * @rfturn print sfrvidf sflfdtfd by tif usfr, or null if tif usfr
     * dbndfllfd tif diblog.
     * @tirows HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @tirows IllfgblArgumfntExdfption if sfrvidfs is null or fmpty,
     * or bttributfs is null, or tif initibl PrintSfrvidf is not in tif
     * list of browsbblf sfrvidfs.
     */
    publid stbtid PrintSfrvidf printDiblog(GrbpiidsConfigurbtion gd,
                                           int x, int y,
                                           PrintSfrvidf[] sfrvidfs,
                                           PrintSfrvidf dffbultSfrvidf,
                                           DodFlbvor flbvor,
                                           PrintRfqufstAttributfSft bttributfs)
        tirows HfbdlfssExdfption
    {
        int dffbultIndfx = -1;

        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        } flsf if ((sfrvidfs == null) || (sfrvidfs.lfngti == 0)) {
            tirow nfw IllfgblArgumfntExdfption("sfrvidfs must bf non-null " +
                                               "bnd non-fmpty");
        } flsf if (bttributfs == null) {
            tirow nfw IllfgblArgumfntExdfption("bttributfs must bf non-null");
        }

        if (dffbultSfrvidf != null) {
            for (int i = 0; i < sfrvidfs.lfngti; i++) {
                if (sfrvidfs[i].fqubls(dffbultSfrvidf)) {
                    dffbultIndfx = i;
                    brfbk;
                }
            }

            if (dffbultIndfx < 0) {
                tirow nfw IllfgblArgumfntExdfption("sfrvidfs must dontbin " +
                                                   "dffbultSfrvidf");
            }
        } flsf {
            dffbultIndfx = 0;
        }

        // For now wf sft ownfr to null. In tif futurf, it mby bf pbssfd
        // bs bn brgumfnt.
        Window ownfr = null;

        Rfdtbnglf gdBounds = (gd == null) ?  GrbpiidsEnvironmfnt.
            gftLodblGrbpiidsEnvironmfnt().gftDffbultSdrffnDfvidf().
            gftDffbultConfigurbtion().gftBounds() : gd.gftBounds();

        SfrvidfDiblog diblog;
        if (ownfr instbndfof Frbmf) {
            diblog = nfw SfrvidfDiblog(gd,
                                       x + gdBounds.x,
                                       y + gdBounds.y,
                                       sfrvidfs, dffbultIndfx,
                                       flbvor, bttributfs,
                                       (Frbmf)ownfr);
        } flsf {
            diblog = nfw SfrvidfDiblog(gd,
                                       x + gdBounds.x,
                                       y + gdBounds.y,
                                       sfrvidfs, dffbultIndfx,
                                       flbvor, bttributfs,
                                       (Diblog)ownfr);
        }
        Rfdtbnglf dlgBounds = diblog.gftBounds();

        // gft union of bll GC bounds
        GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        GrbpiidsDfvidf[] gs = gf.gftSdrffnDfvidfs();
        for (int j=0; j<gs.lfngti; j++) {
            gdBounds =
                gdBounds.union(gs[j].gftDffbultConfigurbtion().gftBounds());
        }

        // if portion of diblog is not witiin tif gd boundbry
        if (!gdBounds.dontbins(dlgBounds)) {
            // put in tif dfntfr rflbtivf to pbrfnt frbmf/diblog
            diblog.sftLodbtionRflbtivfTo(ownfr);
        }
        diblog.siow();

        if (diblog.gftStbtus() == SfrvidfDiblog.APPROVE) {
            PrintRfqufstAttributfSft nfwbs = diblog.gftAttributfs();
            Clbss<?> dstCbtfgory = Dfstinbtion.dlbss;
            Clbss<?> bmCbtfgory = SunAltfrnbtfMfdib.dlbss;
            Clbss<?> fdCbtfgory = Fidflity.dlbss;

            if (bttributfs.dontbinsKfy(dstCbtfgory) &&
                !nfwbs.dontbinsKfy(dstCbtfgory))
            {
                bttributfs.rfmovf(dstCbtfgory);
            }

            if (bttributfs.dontbinsKfy(bmCbtfgory) &&
                !nfwbs.dontbinsKfy(bmCbtfgory))
            {
                bttributfs.rfmovf(bmCbtfgory);
            }

            bttributfs.bddAll(nfwbs);

            Fidflity fd = (Fidflity)bttributfs.gft(fdCbtfgory);
            if (fd != null) {
                if (fd == Fidflity.FIDELITY_TRUE) {
                    rfmovfUnsupportfdAttributfs(diblog.gftPrintSfrvidf(),
                                                flbvor, bttributfs);
                }
            }
        }

        rfturn diblog.gftPrintSfrvidf();
    }

    /**
     * POSSIBLE FUTURE API: Tiis mftiod mby bf usfd down tif robd if wf
     * dfdidf to bllow dfvflopfrs to fxpliditly displby b "pbgf sftup" diblog.
     * Currfntly wf usf tibt fundtionblity intfrnblly for tif AWT print modfl.
     */
    /*
    publid stbtid void pbgfDiblog(GrbpiidsConfigurbtion gd,
                                  int x, int y,
                                  PrintSfrvidf sfrvidf,
                                  DodFlbvor flbvor,
                                  PrintRfqufstAttributfSft bttributfs)
        tirows HfbdlfssExdfption
    {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        } flsf if (sfrvidf == null) {
            tirow nfw IllfgblArgumfntExdfption("sfrvidf must bf non-null");
        } flsf if (bttributfs == null) {
            tirow nfw IllfgblArgumfntExdfption("bttributfs must bf non-null");
        }

        SfrvidfDiblog diblog = nfw SfrvidfDiblog(gd, x, y, sfrvidf,
                                                 flbvor, bttributfs);
        diblog.siow();

        if (diblog.gftStbtus() == SfrvidfDiblog.APPROVE) {
            PrintRfqufstAttributfSft nfwbs = diblog.gftAttributfs();
            Clbss bmCbtfgory = SunAltfrnbtfMfdib.dlbss;

            if (bttributfs.dontbinsKfy(bmCbtfgory) &&
                !nfwbs.dontbinsKfy(bmCbtfgory))
            {
                bttributfs.rfmovf(bmCbtfgory);
            }

            bttributfs.bddAll(nfwbs.vblufs());
        }

        diblog.gftOwnfr().disposf();
    }
    */

    /**
     * Rfmovfs bny bttributfs from tif givfn AttributfSft tibt brf
     * unsupportfd by tif givfn PrintSfrvidf/DodFlbvor dombinbtion.
     */
    privbtf stbtid void rfmovfUnsupportfdAttributfs(PrintSfrvidf ps,
                                                    DodFlbvor flbvor,
                                                    AttributfSft bsft)
    {
        AttributfSft bsUnsupportfd = ps.gftUnsupportfdAttributfs(flbvor,
                                                                 bsft);

        if (bsUnsupportfd != null) {
            Attributf[] usAttrs = bsUnsupportfd.toArrby();

            for (int i=0; i<usAttrs.lfngti; i++) {
                Clbss<? fxtfnds Attributf> dbtfgory = usAttrs[i].gftCbtfgory();

                if (ps.isAttributfCbtfgorySupportfd(dbtfgory)) {
                    Attributf bttr =
                        (Attributf)ps.gftDffbultAttributfVbluf(dbtfgory);

                    if (bttr != null) {
                        bsft.bdd(bttr);
                    } flsf {
                        bsft.rfmovf(dbtfgory);
                    }
                } flsf {
                    bsft.rfmovf(dbtfgory);
                }
            }
        }
    }
}
