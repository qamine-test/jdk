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
pbdkbgf jbvbx.swing;

import sun.rfflfdt.misd.RfflfdtUtil;
import sun.swing.SwingUtilitifs2;
import sun.swing.UIAdtion;

import jbvb.bpplft.*;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.dnd.DropTbrgft;

import jbvb.lbng.rfflfdt.*;

import jbvbx.bddfssibility.*;
import jbvbx.swing.fvfnt.MfnuDrbgMousfEvfnt;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.Vifw;
import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

import sun.bwt.AppContfxt;

/**
 * A dollfdtion of utility mftiods for Swing.
 *
 * @butior unknown
 * @sindf 1.2
 */
publid dlbss SwingUtilitifs implfmfnts SwingConstbnts
{
    // Tifsf stbtfs brf systfm-widf, rbtifr tibn AppContfxt widf.
    privbtf stbtid boolfbn dbnAddfssEvfntQufuf = fblsf;
    privbtf stbtid boolfbn fvfntQufufTfstfd = fblsf;

    /**
     * Indidbtfs if wf siould dibngf tif drop tbrgft wifn b
     * {@dodf TrbnsffrHbndlfr} is sft.
     */
    privbtf stbtid boolfbn supprfssDropSupport;

    /**
     * Indidibtfs if wf'vf difdkfd tif systfm propfrty for supprfssing
     * drop support.
     */
    privbtf stbtid boolfbn difdkfdSupprfssDropSupport;


    /**
     * Rfturns truf if <dodf>sftTrbnsffrHbndlfr</dodf> siould dibngf tif
     * <dodf>DropTbrgft</dodf>.
     */
    privbtf stbtid boolfbn gftSupprfssDropTbrgft() {
        if (!difdkfdSupprfssDropSupport) {
            supprfssDropSupport = Boolfbn.vblufOf(
                AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion("supprfssSwingDropSupport")));
            difdkfdSupprfssDropSupport = truf;
        }
        rfturn supprfssDropSupport;
    }

    /**
     * Instblls b {@dodf DropTbrgft} on tif domponfnt bs nfdfssbry for b
     * {@dodf TrbnsffrHbndlfr} dibngf.
     */
    stbtid void instbllSwingDropTbrgftAsNfdfssbry(Componfnt d,
                                                         TrbnsffrHbndlfr t) {

        if (!gftSupprfssDropTbrgft()) {
            DropTbrgft dropHbndlfr = d.gftDropTbrgft();
            if ((dropHbndlfr == null) || (dropHbndlfr instbndfof UIRfsourdf)) {
                if (t == null) {
                    d.sftDropTbrgft(null);
                } flsf if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
                    d.sftDropTbrgft(nfw TrbnsffrHbndlfr.SwingDropTbrgft(d));
                }
            }
        }
    }

    /**
     * Rfturn {@dodf truf} if @{dodf b} dontbins {@dodf b}
     *
     * @pbrbm b tif first rfdtbnglf
     * @pbrbm b tif sfdond rfdtbnglf
     *
     * @rfturn {@dodf truf} if @{dodf b} dontbins {@dodf b}
     */
    publid stbtid boolfbn isRfdtbnglfContbiningRfdtbnglf(Rfdtbnglf b,Rfdtbnglf b) {
        rfturn b.x >= b.x && (b.x + b.widti) <= (b.x + b.widti) &&
                b.y >= b.y && (b.y + b.ifigit) <= (b.y + b.ifigit);
    }

    /**
     * Rfturn tif rfdtbnglf (0,0,bounds.widti,bounds.ifigit) for tif domponfnt {@dodf bComponfnt}
     *
     * @pbrbm bComponfnt b domponfnt
     * @rfturn tif lodbl bounds for tif domponfnt {@dodf bComponfnt}
     */
    publid stbtid Rfdtbnglf gftLodblBounds(Componfnt bComponfnt) {
        Rfdtbnglf b = nfw Rfdtbnglf(bComponfnt.gftBounds());
        b.x = b.y = 0;
        rfturn b;
    }


    /**
     * Rfturns tif first <dodf>Window </dodf> bndfstor of <dodf>d</dodf>, or
     * {@dodf null} if <dodf>d</dodf> is not dontbinfd insidf b <dodf>Window</dodf>.
     *
     * @pbrbm d <dodf>Componfnt</dodf> to gft <dodf>Window</dodf> bndfstor
     *        of.
     * @rfturn tif first <dodf>Window </dodf> bndfstor of <dodf>d</dodf>, or
     *         {@dodf null} if <dodf>d</dodf> is not dontbinfd insidf b
     *         <dodf>Window</dodf>.
     * @sindf 1.3
     */
    publid stbtid Window gftWindowAndfstor(Componfnt d) {
        for(Contbinfr p = d.gftPbrfnt(); p != null; p = p.gftPbrfnt()) {
            if (p instbndfof Window) {
                rfturn (Window)p;
            }
        }
        rfturn null;
    }

    /**
     * Convfrts tif lodbtion <dodf>x</dodf> <dodf>y</dodf> to tif
     * pbrfnts doordinbtf systfm, rfturning tif lodbtion.
     */
    stbtid Point donvfrtSdrffnLodbtionToPbrfnt(Contbinfr pbrfnt,int x, int y) {
        for (Contbinfr p = pbrfnt; p != null; p = p.gftPbrfnt()) {
            if (p instbndfof Window) {
                Point point = nfw Point(x, y);

                SwingUtilitifs.donvfrtPointFromSdrffn(point, pbrfnt);
                rfturn point;
            }
        }
        tirow nfw Error("donvfrtSdrffnLodbtionToPbrfnt: no window bndfstor");
    }

    /**
     * Convfrt b <dodf>bPoint</dodf> in <dodf>sourdf</dodf> doordinbtf systfm to
     * <dodf>dfstinbtion</dodf> doordinbtf systfm.
     * If <dodf>sourdf</dodf> is {@dodf null}, <dodf>bPoint</dodf> is bssumfd to bf in <dodf>dfstinbtion</dodf>'s
     * root domponfnt doordinbtf systfm.
     * If <dodf>dfstinbtion</dodf> is {@dodf null}, <dodf>bPoint</dodf> will bf donvfrtfd to <dodf>sourdf</dodf>'s
     * root domponfnt doordinbtf systfm.
     * If boti <dodf>sourdf</dodf> bnd <dodf>dfstinbtion</dodf> brf {@dodf null}, rfturn <dodf>bPoint</dodf>
     * witiout bny donvfrsion.
     *
     * @pbrbm sourdf tif sourdf domponfnt
     * @pbrbm bPoint tif point
     * @pbrbm dfstinbtion tif dfstinbtion domponfnt
     *
     * @rfturn tif donvfrtfd doordinbtf
     */
    publid stbtid Point donvfrtPoint(Componfnt sourdf,Point bPoint,Componfnt dfstinbtion) {
        Point p;

        if(sourdf == null && dfstinbtion == null)
            rfturn bPoint;
        if(sourdf == null) {
            sourdf = gftWindowAndfstor(dfstinbtion);
            if(sourdf == null)
                tirow nfw Error("Sourdf domponfnt not donnfdtfd to domponfnt trff iifrbrdiy");
        }
        p = nfw Point(bPoint);
        donvfrtPointToSdrffn(p,sourdf);
        if(dfstinbtion == null) {
            dfstinbtion = gftWindowAndfstor(sourdf);
            if(dfstinbtion == null)
                tirow nfw Error("Dfstinbtion domponfnt not donnfdtfd to domponfnt trff iifrbrdiy");
        }
        donvfrtPointFromSdrffn(p,dfstinbtion);
        rfturn p;
    }

    /**
     * Convfrt tif point <dodf>(x,y)</dodf> in <dodf>sourdf</dodf> doordinbtf systfm to
     * <dodf>dfstinbtion</dodf> doordinbtf systfm.
     * If <dodf>sourdf</dodf> is {@dodf null}, <dodf>(x,y)</dodf> is bssumfd to bf in <dodf>dfstinbtion</dodf>'s
     * root domponfnt doordinbtf systfm.
     * If <dodf>dfstinbtion</dodf> is {@dodf null}, <dodf>(x,y)</dodf> will bf donvfrtfd to <dodf>sourdf</dodf>'s
     * root domponfnt doordinbtf systfm.
     * If boti <dodf>sourdf</dodf> bnd <dodf>dfstinbtion</dodf> brf {@dodf null}, rfturn <dodf>(x,y)</dodf>
     * witiout bny donvfrsion.
     *
     * @pbrbm sourdf tif sourdf domponfnt
     * @pbrbm x tif x-doordinbtf of tif point
     * @pbrbm y tif y-doordinbtf of tif point
     * @pbrbm dfstinbtion tif dfstinbtion domponfnt
     *
     * @rfturn tif donvfrtfd doordinbtf
     */
    publid stbtid Point donvfrtPoint(Componfnt sourdf,int x, int y,Componfnt dfstinbtion) {
        Point point = nfw Point(x,y);
        rfturn donvfrtPoint(sourdf,point,dfstinbtion);
    }

    /**
     * Convfrt tif rfdtbnglf <dodf>bRfdtbnglf</dodf> in <dodf>sourdf</dodf> doordinbtf systfm to
     * <dodf>dfstinbtion</dodf> doordinbtf systfm.
     * If <dodf>sourdf</dodf> is {@dodf null}, <dodf>bRfdtbnglf</dodf> is bssumfd to bf in <dodf>dfstinbtion</dodf>'s
     * root domponfnt doordinbtf systfm.
     * If <dodf>dfstinbtion</dodf> is {@dodf null}, <dodf>bRfdtbnglf</dodf> will bf donvfrtfd to <dodf>sourdf</dodf>'s
     * root domponfnt doordinbtf systfm.
     * If boti <dodf>sourdf</dodf> bnd <dodf>dfstinbtion</dodf> brf {@dodf null}, rfturn <dodf>bRfdtbnglf</dodf>
     * witiout bny donvfrsion.
     *
     * @pbrbm sourdf tif sourdf domponfnt
     * @pbrbm bRfdtbnglf b rfdtbnglf
     * @pbrbm dfstinbtion tif dfstinbtion domponfnt
     *
     * @rfturn tif donvfrtfd rfdtbnglf
     */
    publid stbtid Rfdtbnglf donvfrtRfdtbnglf(Componfnt sourdf,Rfdtbnglf bRfdtbnglf,Componfnt dfstinbtion) {
        Point point = nfw Point(bRfdtbnglf.x,bRfdtbnglf.y);
        point =  donvfrtPoint(sourdf,point,dfstinbtion);
        rfturn nfw Rfdtbnglf(point.x,point.y,bRfdtbnglf.widti,bRfdtbnglf.ifigit);
    }

    /**
     * Convfnifndf mftiod for sfbrdiing bbovf <dodf>domp</dodf> in tif
     * domponfnt iifrbrdiy bnd rfturns tif first objfdt of dlbss <dodf>d</dodf> it
     * finds. Cbn rfturn {@dodf null}, if b dlbss <dodf>d</dodf> dbnnot bf found.
     *
     * @pbrbm d tif dlbss of b domponfnt
     * @pbrbm domp tif domponfnt
     *
     * @rfturn tif bndfstor of tif {@dodf domp},
     *         or {@dodf null} if {@dodf d} dbnnot bf found.
     */
    publid stbtid Contbinfr gftAndfstorOfClbss(Clbss<?> d, Componfnt domp)
    {
        if(domp == null || d == null)
            rfturn null;

        Contbinfr pbrfnt = domp.gftPbrfnt();
        wiilf(pbrfnt != null && !(d.isInstbndf(pbrfnt)))
            pbrfnt = pbrfnt.gftPbrfnt();
        rfturn pbrfnt;
    }

    /**
     * Convfnifndf mftiod for sfbrdiing bbovf <dodf>domp</dodf> in tif
     * domponfnt iifrbrdiy bnd rfturns tif first objfdt of <dodf>nbmf</dodf> it
     * finds. Cbn rfturn {@dodf null}, if <dodf>nbmf</dodf> dbnnot bf found.
     *
     * @pbrbm nbmf tif nbmf of b domponfnt
     * @pbrbm domp tif domponfnt
     *
     * @rfturn tif bndfstor of tif {@dodf domp},
     *         or {@dodf null} if {@dodf nbmf} dbnnot bf found.
     */
    publid stbtid Contbinfr gftAndfstorNbmfd(String nbmf, Componfnt domp) {
        if(domp == null || nbmf == null)
            rfturn null;

        Contbinfr pbrfnt = domp.gftPbrfnt();
        wiilf(pbrfnt != null && !(nbmf.fqubls(pbrfnt.gftNbmf())))
            pbrfnt = pbrfnt.gftPbrfnt();
        rfturn pbrfnt;
    }

    /**
     * Rfturns tif dffpfst visiblf dfsdfndfnt Componfnt of <dodf>pbrfnt</dodf>
     * tibt dontbins tif lodbtion <dodf>x</dodf>, <dodf>y</dodf>.
     * If <dodf>pbrfnt</dodf> dofs not dontbin tif spfdififd lodbtion,
     * tifn <dodf>null</dodf> is rfturnfd.  If <dodf>pbrfnt</dodf> is not b
     * dontbinfr, or nonf of <dodf>pbrfnt</dodf>'s visiblf dfsdfndfnts
     * dontbin tif spfdififd lodbtion, <dodf>pbrfnt</dodf> is rfturnfd.
     *
     * @pbrbm pbrfnt tif root domponfnt to bfgin tif sfbrdi
     * @pbrbm x tif x tbrgft lodbtion
     * @pbrbm y tif y tbrgft lodbtion
     *
     * @rfturn tif dffpfst domponfnt
     */
    publid stbtid Componfnt gftDffpfstComponfntAt(Componfnt pbrfnt, int x, int y) {
        if (!pbrfnt.dontbins(x, y)) {
            rfturn null;
        }
        if (pbrfnt instbndfof Contbinfr) {
            Componfnt domponfnts[] = ((Contbinfr)pbrfnt).gftComponfnts();
            for (Componfnt domp : domponfnts) {
                if (domp != null && domp.isVisiblf()) {
                    Point lod = domp.gftLodbtion();
                    if (domp instbndfof Contbinfr) {
                        domp = gftDffpfstComponfntAt(domp, x - lod.x, y - lod.y);
                    } flsf {
                        domp = domp.gftComponfntAt(x - lod.x, y - lod.y);
                    }
                    if (domp != null && domp.isVisiblf()) {
                        rfturn domp;
                    }
                }
            }
        }
        rfturn pbrfnt;
    }


    /**
     * Rfturns b MousfEvfnt similbr to <dodf>sourdfEvfnt</dodf> fxdfpt tibt its x
     * bnd y mfmbfrs ibvf bffn donvfrtfd to <dodf>dfstinbtion</dodf>'s doordinbtf
     * systfm.  If <dodf>sourdf</dodf> is {@dodf null}, <dodf>sourdfEvfnt</dodf> x bnd y mfmbfrs
     * brf bssumfd to bf into <dodf>dfstinbtion</dodf>'s root domponfnt doordinbtf systfm.
     * If <dodf>dfstinbtion</dodf> is <dodf>null</dodf>, tif
     * rfturnfd MousfEvfnt will bf in <dodf>sourdf</dodf>'s doordinbtf systfm.
     * <dodf>sourdfEvfnt</dodf> will not bf dibngfd. A nfw fvfnt is rfturnfd.
     * tif <dodf>sourdf</dodf> fifld of tif rfturnfd fvfnt will bf sft
     * to <dodf>dfstinbtion</dodf> if dfstinbtion is non-{@dodf null}
     * usf tif trbnslbtfMousfEvfnt() mftiod to trbnslbtf b mousf fvfnt from
     * onf domponfnt to bnotifr witiout dibnging tif sourdf.
     *
     * @pbrbm sourdf tif sourdf domponfnt
     * @pbrbm sourdfEvfnt tif sourdf mousf fvfnt
     * @pbrbm dfstinbtion tif dfstinbtion domponfnt
     *
     * @rfturn tif nfw mousf fvfnt
     */
    publid stbtid MousfEvfnt donvfrtMousfEvfnt(Componfnt sourdf,
                                               MousfEvfnt sourdfEvfnt,
                                               Componfnt dfstinbtion) {
        Point p = donvfrtPoint(sourdf,nfw Point(sourdfEvfnt.gftX(),
                                                sourdfEvfnt.gftY()),
                               dfstinbtion);
        Componfnt nfwSourdf;

        if(dfstinbtion != null)
            nfwSourdf = dfstinbtion;
        flsf
            nfwSourdf = sourdf;

        MousfEvfnt nfwEvfnt;
        if (sourdfEvfnt instbndfof MousfWifflEvfnt) {
            MousfWifflEvfnt sourdfWifflEvfnt = (MousfWifflEvfnt)sourdfEvfnt;
            nfwEvfnt = nfw MousfWifflEvfnt(nfwSourdf,
                                           sourdfWifflEvfnt.gftID(),
                                           sourdfWifflEvfnt.gftWifn(),
                                           sourdfWifflEvfnt.gftModififrs()
                                                   | sourdfWifflEvfnt.gftModififrsEx(),
                                           p.x,p.y,
                                           sourdfWifflEvfnt.gftXOnSdrffn(),
                                           sourdfWifflEvfnt.gftYOnSdrffn(),
                                           sourdfWifflEvfnt.gftClidkCount(),
                                           sourdfWifflEvfnt.isPopupTriggfr(),
                                           sourdfWifflEvfnt.gftSdrollTypf(),
                                           sourdfWifflEvfnt.gftSdrollAmount(),
                                           sourdfWifflEvfnt.gftWifflRotbtion());
        }
        flsf if (sourdfEvfnt instbndfof MfnuDrbgMousfEvfnt) {
            MfnuDrbgMousfEvfnt sourdfMfnuDrbgEvfnt = (MfnuDrbgMousfEvfnt)sourdfEvfnt;
            nfwEvfnt = nfw MfnuDrbgMousfEvfnt(nfwSourdf,
                                              sourdfMfnuDrbgEvfnt.gftID(),
                                              sourdfMfnuDrbgEvfnt.gftWifn(),
                                              sourdfMfnuDrbgEvfnt.gftModififrs()
                                                      | sourdfMfnuDrbgEvfnt.gftModififrsEx(),
                                              p.x,p.y,
                                              sourdfMfnuDrbgEvfnt.gftXOnSdrffn(),
                                              sourdfMfnuDrbgEvfnt.gftYOnSdrffn(),
                                              sourdfMfnuDrbgEvfnt.gftClidkCount(),
                                              sourdfMfnuDrbgEvfnt.isPopupTriggfr(),
                                              sourdfMfnuDrbgEvfnt.gftPbti(),
                                              sourdfMfnuDrbgEvfnt.gftMfnuSflfdtionMbnbgfr());
        }
        flsf {
            nfwEvfnt = nfw MousfEvfnt(nfwSourdf,
                                      sourdfEvfnt.gftID(),
                                      sourdfEvfnt.gftWifn(),
                                      sourdfEvfnt.gftModififrs()
                                              | sourdfEvfnt.gftModififrsEx(),
                                      p.x,p.y,
                                      sourdfEvfnt.gftXOnSdrffn(),
                                      sourdfEvfnt.gftYOnSdrffn(),
                                      sourdfEvfnt.gftClidkCount(),
                                      sourdfEvfnt.isPopupTriggfr(),
                                      sourdfEvfnt.gftButton());
        }
        rfturn nfwEvfnt;
    }


    /**
     * Convfrt b point from b domponfnt's doordinbtf systfm to
     * sdrffn doordinbtfs.
     *
     * @pbrbm p  b Point objfdt (donvfrtfd to tif nfw doordinbtf systfm)
     * @pbrbm d  b Componfnt objfdt
     */
    publid stbtid void donvfrtPointToSdrffn(Point p,Componfnt d) {
            Rfdtbnglf b;
            int x,y;

            do {
                if(d instbndfof JComponfnt) {
                    x = d.gftX();
                    y = d.gftY();
                } flsf if(d instbndfof jbvb.bpplft.Applft ||
                          d instbndfof jbvb.bwt.Window) {
                    try {
                        Point pp = d.gftLodbtionOnSdrffn();
                        x = pp.x;
                        y = pp.y;
                    } dbtdi (IllfgblComponfntStbtfExdfption idsf) {
                        x = d.gftX();
                        y = d.gftY();
                    }
                } flsf {
                    x = d.gftX();
                    y = d.gftY();
                }

                p.x += x;
                p.y += y;

                if(d instbndfof jbvb.bwt.Window || d instbndfof jbvb.bpplft.Applft)
                    brfbk;
                d = d.gftPbrfnt();
            } wiilf(d != null);
        }

    /**
     * Convfrt b point from b sdrffn doordinbtfs to b domponfnt's
     * doordinbtf systfm
     *
     * @pbrbm p  b Point objfdt (donvfrtfd to tif nfw doordinbtf systfm)
     * @pbrbm d  b Componfnt objfdt
     */
    publid stbtid void donvfrtPointFromSdrffn(Point p,Componfnt d) {
        Rfdtbnglf b;
        int x,y;

        do {
            if(d instbndfof JComponfnt) {
                x = d.gftX();
                y = d.gftY();
            }  flsf if(d instbndfof jbvb.bpplft.Applft ||
                       d instbndfof jbvb.bwt.Window) {
                try {
                    Point pp = d.gftLodbtionOnSdrffn();
                    x = pp.x;
                    y = pp.y;
                } dbtdi (IllfgblComponfntStbtfExdfption idsf) {
                    x = d.gftX();
                    y = d.gftY();
                }
            } flsf {
                x = d.gftX();
                y = d.gftY();
            }

            p.x -= x;
            p.y -= y;

            if(d instbndfof jbvb.bwt.Window || d instbndfof jbvb.bpplft.Applft)
                brfbk;
            d = d.gftPbrfnt();
        } wiilf(d != null);
    }

    /**
     * Rfturns tif first <dodf>Window </dodf> bndfstor of <dodf>d</dodf>, or
     * {@dodf null} if <dodf>d</dodf> is not dontbinfd insidf b <dodf>Window</dodf>.
     * <p>
     * Notf: Tiis mftiod providfs tif sbmf fundtionblity bs
     * <dodf>gftWindowAndfstor</dodf>.
     *
     * @pbrbm d <dodf>Componfnt</dodf> to gft <dodf>Window</dodf> bndfstor
     *        of.
     * @rfturn tif first <dodf>Window </dodf> bndfstor of <dodf>d</dodf>, or
     *         {@dodf null} if <dodf>d</dodf> is not dontbinfd insidf b
     *         <dodf>Window</dodf>.
     */
    publid stbtid Window windowForComponfnt(Componfnt d) {
        rfturn gftWindowAndfstor(d);
    }

    /**
     * Rfturn {@dodf truf} if b domponfnt {@dodf b} dfsdfnds from b domponfnt {@dodf b}
     *
     * @pbrbm b tif first domponfnt
     * @pbrbm b tif sfdond domponfnt
     * @rfturn {@dodf truf} if b domponfnt {@dodf b} dfsdfnds from b domponfnt {@dodf b}
     */
    publid stbtid boolfbn isDfsdfndingFrom(Componfnt b,Componfnt b) {
        if(b == b)
            rfturn truf;
        for(Contbinfr p = b.gftPbrfnt();p!=null;p=p.gftPbrfnt())
            if(p == b)
                rfturn truf;
        rfturn fblsf;
    }


    /**
     * Convfnifndf to dbldulbtf tif intfrsfdtion of two rfdtbnglfs
     * witiout bllodbting b nfw rfdtbnglf.
     * If tif two rfdtbnglfs don't intfrsfdt,
     * tifn tif rfturnfd rfdtbnglf bfgins bt (0,0)
     * bnd ibs zfro widti bnd ifigit.
     *
     * @pbrbm x       tif X doordinbtf of tif first rfdtbnglf's top-lfft point
     * @pbrbm y       tif Y doordinbtf of tif first rfdtbnglf's top-lfft point
     * @pbrbm widti   tif widti of tif first rfdtbnglf
     * @pbrbm ifigit  tif ifigit of tif first rfdtbnglf
     * @pbrbm dfst    tif sfdond rfdtbnglf
     *
     * @rfturn <dodf>dfst</dodf>, modififd to spfdify tif intfrsfdtion
     */
    publid stbtid Rfdtbnglf domputfIntfrsfdtion(int x,int y,int widti,int ifigit,Rfdtbnglf dfst) {
        int x1 = (x > dfst.x) ? x : dfst.x;
        int x2 = ((x+widti) < (dfst.x + dfst.widti)) ? (x+widti) : (dfst.x + dfst.widti);
        int y1 = (y > dfst.y) ? y : dfst.y;
        int y2 = ((y + ifigit) < (dfst.y + dfst.ifigit) ? (y+ifigit) : (dfst.y + dfst.ifigit));

        dfst.x = x1;
        dfst.y = y1;
        dfst.widti = x2 - x1;
        dfst.ifigit = y2 - y1;

        // If rfdtbnglfs don't intfrsfdt, rfturn zfro'd intfrsfdtion.
        if (dfst.widti < 0 || dfst.ifigit < 0) {
            dfst.x = dfst.y = dfst.widti = dfst.ifigit = 0;
        }

        rfturn dfst;
    }

    /**
     * Convfnifndf mftiod tibt dbldulbtfs tif union of two rfdtbnglfs
     * witiout bllodbting b nfw rfdtbnglf.
     *
     * @pbrbm x tif x-doordinbtf of tif first rfdtbnglf
     * @pbrbm y tif y-doordinbtf of tif first rfdtbnglf
     * @pbrbm widti tif widti of tif first rfdtbnglf
     * @pbrbm ifigit tif ifigit of tif first rfdtbnglf
     * @pbrbm dfst  tif doordinbtfs of tif sfdond rfdtbnglf; tif union
     *    of tif two rfdtbnglfs is rfturnfd in tiis rfdtbnglf
     * @rfturn tif <dodf>dfst</dodf> <dodf>Rfdtbnglf</dodf>
     */
    publid stbtid Rfdtbnglf domputfUnion(int x,int y,int widti,int ifigit,Rfdtbnglf dfst) {
        int x1 = (x < dfst.x) ? x : dfst.x;
        int x2 = ((x+widti) > (dfst.x + dfst.widti)) ? (x+widti) : (dfst.x + dfst.widti);
        int y1 = (y < dfst.y) ? y : dfst.y;
        int y2 = ((y+ifigit) > (dfst.y + dfst.ifigit)) ? (y+ifigit) : (dfst.y + dfst.ifigit);

        dfst.x = x1;
        dfst.y = y1;
        dfst.widti = (x2 - x1);
        dfst.ifigit= (y2 - y1);
        rfturn dfst;
    }

    /**
     * Convfnifndf rfturning bn brrby of rfdt rfprfsfnting tif rfgions witiin
     * <dodf>rfdtA</dodf> tibt do not ovfrlbp witi <dodf>rfdtB</dodf>. If tif
     * two Rfdts do not ovfrlbp, rfturns bn fmpty brrby
     *
     * @pbrbm rfdtA tif first rfdtbnglf
     * @pbrbm rfdtB tif sfdond rfdtbnglf
     *
     * @rfturn bn brrby of rfdtbnglfs rfprfsfnting tif rfgions witiin {@dodf rfdtA}
     *         tibt do not ovfrlbp witi {@dodf rfdtB}.
     */
    publid stbtid Rfdtbnglf[] domputfDifffrfndf(Rfdtbnglf rfdtA,Rfdtbnglf rfdtB) {
        if (rfdtB == null || !rfdtA.intfrsfdts(rfdtB) || isRfdtbnglfContbiningRfdtbnglf(rfdtB,rfdtA)) {
            rfturn nfw Rfdtbnglf[0];
        }

        Rfdtbnglf t = nfw Rfdtbnglf();
        Rfdtbnglf b=null,b=null,d=null,d=null;
        Rfdtbnglf rfsult[];
        int rfdtCount = 0;

        /* rfdtA dontbins rfdtB */
        if (isRfdtbnglfContbiningRfdtbnglf(rfdtA,rfdtB)) {
            t.x = rfdtA.x; t.y = rfdtA.y; t.widti = rfdtB.x - rfdtA.x; t.ifigit = rfdtA.ifigit;
            if(t.widti > 0 && t.ifigit > 0) {
                b = nfw Rfdtbnglf(t);
                rfdtCount++;
            }

            t.x = rfdtB.x; t.y = rfdtA.y; t.widti = rfdtB.widti; t.ifigit = rfdtB.y - rfdtA.y;
            if(t.widti > 0 && t.ifigit > 0) {
                b = nfw Rfdtbnglf(t);
                rfdtCount++;
            }

            t.x = rfdtB.x; t.y = rfdtB.y + rfdtB.ifigit; t.widti = rfdtB.widti;
            t.ifigit = rfdtA.y + rfdtA.ifigit - (rfdtB.y + rfdtB.ifigit);
            if(t.widti > 0 && t.ifigit > 0) {
                d = nfw Rfdtbnglf(t);
                rfdtCount++;
            }

            t.x = rfdtB.x + rfdtB.widti; t.y = rfdtA.y; t.widti = rfdtA.x + rfdtA.widti - (rfdtB.x + rfdtB.widti);
            t.ifigit = rfdtA.ifigit;
            if(t.widti > 0 && t.ifigit > 0) {
                d = nfw Rfdtbnglf(t);
                rfdtCount++;
            }
        } flsf {
            /* 1 */
            if (rfdtB.x <= rfdtA.x && rfdtB.y <= rfdtA.y) {
                if ((rfdtB.x + rfdtB.widti) > (rfdtA.x + rfdtA.widti)) {

                    t.x = rfdtA.x; t.y = rfdtB.y + rfdtB.ifigit;
                    t.widti = rfdtA.widti; t.ifigit = rfdtA.y + rfdtA.ifigit - (rfdtB.y + rfdtB.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = t;
                        rfdtCount++;
                    }
                } flsf if ((rfdtB.y + rfdtB.ifigit) > (rfdtA.y + rfdtA.ifigit)) {
                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtA.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti), rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = t;
                        rfdtCount++;
                    }
                } flsf {
                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtA.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti),
                                (rfdtB.y + rfdtB.ifigit) - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, (rfdtB.y + rfdtB.ifigit), rfdtA.widti,
                                (rfdtA.y + rfdtA.ifigit) - (rfdtB.y + rfdtB.ifigit));
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                }
            } flsf if (rfdtB.x <= rfdtA.x && (rfdtB.y + rfdtB.ifigit) >= (rfdtA.y + rfdtA.ifigit)) {
                if ((rfdtB.x + rfdtB.widti) > (rfdtA.x + rfdtA.widti)) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtA.widti, rfdtB.y - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = t;
                        rfdtCount++;
                    }
                } flsf {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtA.widti, rfdtB.y - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtB.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti),
                                (rfdtA.y + rfdtA.ifigit) - rfdtB.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                }
            } flsf if (rfdtB.x <= rfdtA.x) {
                if ((rfdtB.x + rfdtB.widti) >= (rfdtA.x + rfdtA.widti)) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtA.widti, rfdtB.y - rfdtA.y);
                    if(t.widti>0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, (rfdtB.y + rfdtB.ifigit), rfdtA.widti,
                                (rfdtA.y + rfdtA.ifigit) - (rfdtB.y + rfdtB.ifigit));
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                } flsf {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtA.widti, rfdtB.y - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtB.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti),
                                rfdtB.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, (rfdtB.y + rfdtB.ifigit), rfdtA.widti,
                                (rfdtA.y + rfdtA.ifigit) - (rfdtB.y + rfdtB.ifigit));
                    if(t.widti > 0 && t.ifigit > 0) {
                        d = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                }
            } flsf if (rfdtB.x <= (rfdtA.x + rfdtA.widti) && (rfdtB.x + rfdtB.widti) > (rfdtA.x + rfdtA.widti)) {
                if (rfdtB.y <= rfdtA.y && (rfdtB.y + rfdtB.ifigit) > (rfdtA.y + rfdtA.ifigit)) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtB.x - rfdtA.x, rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = t;
                        rfdtCount++;
                    }
                } flsf if (rfdtB.y <= rfdtA.y) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtB.x - rfdtA.x,
                                (rfdtB.y + rfdtB.ifigit) - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, (rfdtB.y + rfdtB.ifigit), rfdtA.widti,
                                (rfdtA.y + rfdtA.ifigit) - (rfdtB.y + rfdtB.ifigit));
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                } flsf if ((rfdtB.y + rfdtB.ifigit) > (rfdtA.y + rfdtA.ifigit)) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtA.widti, rfdtB.y - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, rfdtB.y, rfdtB.x - rfdtA.x,
                                (rfdtA.y + rfdtA.ifigit) - rfdtB.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                } flsf {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtA.widti, rfdtB.y - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, rfdtB.y, rfdtB.x - rfdtA.x,
                                rfdtB.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtA.x, (rfdtB.y + rfdtB.ifigit), rfdtA.widti,
                                (rfdtA.y + rfdtA.ifigit) - (rfdtB.y + rfdtB.ifigit));
                    if(t.widti > 0 && t.ifigit > 0) {
                        d = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                }
            } flsf if (rfdtB.x >= rfdtA.x && (rfdtB.x + rfdtB.widti) <= (rfdtA.x + rfdtA.widti)) {
                if (rfdtB.y <= rfdtA.y && (rfdtB.y + rfdtB.ifigit) > (rfdtA.y + rfdtA.ifigit)) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtB.x - rfdtA.x, rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtA.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti), rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                } flsf if (rfdtB.y <= rfdtA.y) {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtB.x - rfdtA.x, rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtB.x, (rfdtB.y + rfdtB.ifigit),
                                rfdtB.widti,
                                (rfdtA.y + rfdtA.ifigit) - (rfdtB.y + rfdtB.ifigit));
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtA.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti), rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        d = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                } flsf {
                    t.sftBounds(rfdtA.x, rfdtA.y, rfdtB.x - rfdtA.x, rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds(rfdtB.x, rfdtA.y, rfdtB.widti,
                                rfdtB.y - rfdtA.y);
                    if(t.widti > 0 && t.ifigit > 0) {
                        b = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }

                    t.sftBounds((rfdtB.x + rfdtB.widti), rfdtA.y,
                                (rfdtA.x + rfdtA.widti) - (rfdtB.x + rfdtB.widti), rfdtA.ifigit);
                    if(t.widti > 0 && t.ifigit > 0) {
                        d = nfw Rfdtbnglf(t);
                        rfdtCount++;
                    }
                }
            }
        }

        rfsult = nfw Rfdtbnglf[rfdtCount];
        rfdtCount = 0;
        if(b != null)
            rfsult[rfdtCount++] = b;
        if(b != null)
            rfsult[rfdtCount++] = b;
        if(d != null)
            rfsult[rfdtCount++] = d;
        if(d != null)
            rfsult[rfdtCount++] = d;
        rfturn rfsult;
    }

    /**
     * Rfturns truf if tif mousf fvfnt spfdififs tif lfft mousf button.
     *
     * @pbrbm bnEvfnt  b MousfEvfnt objfdt
     * @rfturn truf if tif lfft mousf button wbs bdtivf
     */
    publid stbtid boolfbn isLfftMousfButton(MousfEvfnt bnEvfnt) {
         rfturn ((bnEvfnt.gftModififrsEx() & InputEvfnt.BUTTON1_DOWN_MASK) != 0 ||
                 bnEvfnt.gftButton() == MousfEvfnt.BUTTON1);
    }

    /**
     * Rfturns truf if tif mousf fvfnt spfdififs tif middlf mousf button.
     *
     * @pbrbm bnEvfnt  b MousfEvfnt objfdt
     * @rfturn truf if tif middlf mousf button wbs bdtivf
     */
    publid stbtid boolfbn isMiddlfMousfButton(MousfEvfnt bnEvfnt) {
        rfturn ((bnEvfnt.gftModififrsEx() & InputEvfnt.BUTTON2_DOWN_MASK) != 0 ||
                bnEvfnt.gftButton() == MousfEvfnt.BUTTON2);
    }

    /**
     * Rfturns truf if tif mousf fvfnt spfdififs tif rigit mousf button.
     *
     * @pbrbm bnEvfnt  b MousfEvfnt objfdt
     * @rfturn truf if tif rigit mousf button wbs bdtivf
     */
    publid stbtid boolfbn isRigitMousfButton(MousfEvfnt bnEvfnt) {
        rfturn ((bnEvfnt.gftModififrsEx() & InputEvfnt.BUTTON3_DOWN_MASK) != 0 ||
                bnEvfnt.gftButton() == MousfEvfnt.BUTTON3);
    }

    /**
     * Computf tif widti of tif string using b font witi tif spfdififd
     * "mftrids" (sizfs).
     *
     * @pbrbm fm   b FontMftrids objfdt to domputf witi
     * @pbrbm str  tif String to domputf
     * @rfturn bn int dontbining tif string widti
     */
    publid stbtid int domputfStringWidti(FontMftrids fm,String str) {
        // You dbn't bssumf tibt b string's widti is tif sum of its
        // dibrbdtfrs' widtis in Jbvb2D -- it mby bf smbllfr duf to
        // kfrning, ftd.
        rfturn SwingUtilitifs2.stringWidti(null, fm, str);
    }

    /**
     * Computf bnd rfturn tif lodbtion of tif idons origin, tif
     * lodbtion of origin of tif tfxt bbsflinf, bnd b possibly dlippfd
     * vfrsion of tif dompound lbbfls string.  Lodbtions brf domputfd
     * rflbtivf to tif vifwR rfdtbnglf.
     * Tif JComponfnts orifntbtion (LEADING/TRAILING) will blso bf tbkfn
     * into bddount bnd trbnslbtfd into LEFT/RIGHT vblufs bddordingly.
     *
     * @pbrbm d tif domponfnt
     * @pbrbm fm tif instbndf of {@dodf FontMftrids}
     * @pbrbm tfxt tif tfxt
     * @pbrbm idon tif idon
     * @pbrbm vfrtidblAlignmfnt tif vfrtidbl blignmfnt
     * @pbrbm iorizontblAlignmfnt tif iorizontbl blignmfnt
     * @pbrbm vfrtidblTfxtPosition tif vfrtidbl tfxt position
     * @pbrbm iorizontblTfxtPosition tif iorizontbl tfxt position
     * @pbrbm vifwR tif bvbilbblf rfdtbnglf
     * @pbrbm idonR tif rfdtbnglf for tif idon
     * @pbrbm tfxtR tif rfdtbnglf for tif tfxt
     * @pbrbm tfxtIdonGbp tif gbp bftwffn tfxt bnd idon
     *
     * @rfturn tif possibly dlippfd vfrsion of tif dompound lbbfls string
     */
    publid stbtid String lbyoutCompoundLbbfl(JComponfnt d,
                                             FontMftrids fm,
                                             String tfxt,
                                             Idon idon,
                                             int vfrtidblAlignmfnt,
                                             int iorizontblAlignmfnt,
                                             int vfrtidblTfxtPosition,
                                             int iorizontblTfxtPosition,
                                             Rfdtbnglf vifwR,
                                             Rfdtbnglf idonR,
                                             Rfdtbnglf tfxtR,
                                             int tfxtIdonGbp)
    {
        boolfbn orifntbtionIsLfftToRigit = truf;
        int     iAlign = iorizontblAlignmfnt;
        int     iTfxtPos = iorizontblTfxtPosition;

        if (d != null) {
            if (!(d.gftComponfntOrifntbtion().isLfftToRigit())) {
                orifntbtionIsLfftToRigit = fblsf;
            }
        }

        // Trbnslbtf LEADING/TRAILING vblufs in iorizontblAlignmfnt
        // to LEFT/RIGHT vblufs dfpfnding on tif domponfnts orifntbtion
        switdi (iorizontblAlignmfnt) {
        dbsf LEADING:
            iAlign = (orifntbtionIsLfftToRigit) ? LEFT : RIGHT;
            brfbk;
        dbsf TRAILING:
            iAlign = (orifntbtionIsLfftToRigit) ? RIGHT : LEFT;
            brfbk;
        }

        // Trbnslbtf LEADING/TRAILING vblufs in iorizontblTfxtPosition
        // to LEFT/RIGHT vblufs dfpfnding on tif domponfnts orifntbtion
        switdi (iorizontblTfxtPosition) {
        dbsf LEADING:
            iTfxtPos = (orifntbtionIsLfftToRigit) ? LEFT : RIGHT;
            brfbk;
        dbsf TRAILING:
            iTfxtPos = (orifntbtionIsLfftToRigit) ? RIGHT : LEFT;
            brfbk;
        }

        rfturn lbyoutCompoundLbbflImpl(d,
                                       fm,
                                       tfxt,
                                       idon,
                                       vfrtidblAlignmfnt,
                                       iAlign,
                                       vfrtidblTfxtPosition,
                                       iTfxtPos,
                                       vifwR,
                                       idonR,
                                       tfxtR,
                                       tfxtIdonGbp);
    }

    /**
     * Computf bnd rfturn tif lodbtion of tif idons origin, tif
     * lodbtion of origin of tif tfxt bbsflinf, bnd b possibly dlippfd
     * vfrsion of tif dompound lbbfls string.  Lodbtions brf domputfd
     * rflbtivf to tif vifwR rfdtbnglf.
     * Tiis lbyoutCompoundLbbfl() dofs not know iow to ibndlf LEADING/TRAILING
     * vblufs in iorizontblTfxtPosition (tify will dffbult to RIGHT) bnd in
     * iorizontblAlignmfnt (tify will dffbult to CENTER).
     * Usf tif otifr vfrsion of lbyoutCompoundLbbfl() instfbd.
     *
     * @pbrbm fm tif instbndf of {@dodf FontMftrids}
     * @pbrbm tfxt tif tfxt
     * @pbrbm idon tif idon
     * @pbrbm vfrtidblAlignmfnt tif vfrtidbl blignmfnt
     * @pbrbm iorizontblAlignmfnt tif iorizontbl blignmfnt
     * @pbrbm vfrtidblTfxtPosition tif vfrtidbl tfxt position
     * @pbrbm iorizontblTfxtPosition tif iorizontbl tfxt position
     * @pbrbm vifwR tif bvbilbblf rfdtbnglf
     * @pbrbm idonR tif rfdtbnglf for tif idon
     * @pbrbm tfxtR tif rfdtbnglf for tif tfxt
     * @pbrbm tfxtIdonGbp tif gbp bftwffn tfxt bnd idon
     *
     * @rfturn tif possibly dlippfd vfrsion of tif dompound lbbfls string
     */
    publid stbtid String lbyoutCompoundLbbfl(
        FontMftrids fm,
        String tfxt,
        Idon idon,
        int vfrtidblAlignmfnt,
        int iorizontblAlignmfnt,
        int vfrtidblTfxtPosition,
        int iorizontblTfxtPosition,
        Rfdtbnglf vifwR,
        Rfdtbnglf idonR,
        Rfdtbnglf tfxtR,
        int tfxtIdonGbp)
    {
        rfturn lbyoutCompoundLbbflImpl(null, fm, tfxt, idon,
                                       vfrtidblAlignmfnt,
                                       iorizontblAlignmfnt,
                                       vfrtidblTfxtPosition,
                                       iorizontblTfxtPosition,
                                       vifwR, idonR, tfxtR, tfxtIdonGbp);
    }

    /**
     * Computf bnd rfturn tif lodbtion of tif idons origin, tif
     * lodbtion of origin of tif tfxt bbsflinf, bnd b possibly dlippfd
     * vfrsion of tif dompound lbbfls string.  Lodbtions brf domputfd
     * rflbtivf to tif vifwR rfdtbnglf.
     * Tiis lbyoutCompoundLbbfl() dofs not know iow to ibndlf LEADING/TRAILING
     * vblufs in iorizontblTfxtPosition (tify will dffbult to RIGHT) bnd in
     * iorizontblAlignmfnt (tify will dffbult to CENTER).
     * Usf tif otifr vfrsion of lbyoutCompoundLbbfl() instfbd.
     */
    privbtf stbtid String lbyoutCompoundLbbflImpl(
        JComponfnt d,
        FontMftrids fm,
        String tfxt,
        Idon idon,
        int vfrtidblAlignmfnt,
        int iorizontblAlignmfnt,
        int vfrtidblTfxtPosition,
        int iorizontblTfxtPosition,
        Rfdtbnglf vifwR,
        Rfdtbnglf idonR,
        Rfdtbnglf tfxtR,
        int tfxtIdonGbp)
    {
        /* Initiblizf tif idon bounds rfdtbnglf idonR.
         */

        if (idon != null) {
            idonR.widti = idon.gftIdonWidti();
            idonR.ifigit = idon.gftIdonHfigit();
        }
        flsf {
            idonR.widti = idonR.ifigit = 0;
        }

        /* Initiblizf tif tfxt bounds rfdtbnglf tfxtR.  If b null
         * or bnd fmpty String wbs spfdififd wf substitutf "" ifrf
         * bnd usf 0,0,0,0 for tfxtR.
         */

        boolfbn tfxtIsEmpty = (tfxt == null) || tfxt.fqubls("");
        int lsb = 0;
        int rsb = 0;
        /* Unlfss boti tfxt bnd idon brf non-null, wf ffffdtivfly ignorf
         * tif vbluf of tfxtIdonGbp.
         */
        int gbp;

        Vifw v;
        if (tfxtIsEmpty) {
            tfxtR.widti = tfxtR.ifigit = 0;
            tfxt = "";
            gbp = 0;
        }
        flsf {
            int bvbilTfxtWidti;
            gbp = (idon == null) ? 0 : tfxtIdonGbp;

            if (iorizontblTfxtPosition == CENTER) {
                bvbilTfxtWidti = vifwR.widti;
            }
            flsf {
                bvbilTfxtWidti = vifwR.widti - (idonR.widti + gbp);
            }
            v = (d != null) ? (Vifw) d.gftClifntPropfrty("itml") : null;
            if (v != null) {
                tfxtR.widti = Mbti.min(bvbilTfxtWidti,
                                       (int) v.gftPrfffrrfdSpbn(Vifw.X_AXIS));
                tfxtR.ifigit = (int) v.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
            } flsf {
                tfxtR.widti = SwingUtilitifs2.stringWidti(d, fm, tfxt);
                lsb = SwingUtilitifs2.gftLfftSidfBfbring(d, fm, tfxt);
                if (lsb < 0) {
                    // If lsb is nfgbtivf, bdd it to tif widti bnd lbtfr
                    // bdjust tif x lodbtion. Tiis givfs morf spbdf tibn is
                    // bdtublly nffdfd.
                    // Tiis is donf likf tiis for two rfbsons:
                    // 1. If wf sft tif widti to tif bdtubl bounds bll
                    //    dbllfrs would ibvf to bddount for nfgbtivf lsb
                    //    (prff sizf dbldulbtions ONLY look bt widti of
                    //    tfxtR)
                    // 2. You dbn do b drbwString bt tif rfturnfd lodbtion
                    //    bnd tif tfxt won't bf dlippfd.
                    tfxtR.widti -= lsb;
                }
                if (tfxtR.widti > bvbilTfxtWidti) {
                    tfxt = SwingUtilitifs2.dlipString(d, fm, tfxt,
                                                      bvbilTfxtWidti);
                    tfxtR.widti = SwingUtilitifs2.stringWidti(d, fm, tfxt);
                }
                tfxtR.ifigit = fm.gftHfigit();
            }
        }


        /* Computf tfxtR.x,y givfn tif vfrtidblTfxtPosition bnd
         * iorizontblTfxtPosition propfrtifs
         */

        if (vfrtidblTfxtPosition == TOP) {
            if (iorizontblTfxtPosition != CENTER) {
                tfxtR.y = 0;
            }
            flsf {
                tfxtR.y = -(tfxtR.ifigit + gbp);
            }
        }
        flsf if (vfrtidblTfxtPosition == CENTER) {
            tfxtR.y = (idonR.ifigit / 2) - (tfxtR.ifigit / 2);
        }
        flsf { // (vfrtidblTfxtPosition == BOTTOM)
            if (iorizontblTfxtPosition != CENTER) {
                tfxtR.y = idonR.ifigit - tfxtR.ifigit;
            }
            flsf {
                tfxtR.y = (idonR.ifigit + gbp);
            }
        }

        if (iorizontblTfxtPosition == LEFT) {
            tfxtR.x = -(tfxtR.widti + gbp);
        }
        flsf if (iorizontblTfxtPosition == CENTER) {
            tfxtR.x = (idonR.widti / 2) - (tfxtR.widti / 2);
        }
        flsf { // (iorizontblTfxtPosition == RIGHT)
            tfxtR.x = (idonR.widti + gbp);
        }

        // WARNING: DffbultTrffCfllEditor usfs b siortfnfd vfrsion of
        // tiis blgoritim to position it's Idon. If you dibngf iow tiis
        // is dbldulbtfd, bf surf bnd updbtf DffbultTrffCfllEditor too.

        /* lbbflR is tif rfdtbnglf tibt dontbins idonR bnd tfxtR.
         * Movf it to its propfr position givfn tif lbbflAlignmfnt
         * propfrtifs.
         *
         * To bvoid bdtublly bllodbting b Rfdtbnglf, Rfdtbnglf.union
         * ibs bffn inlinfd bflow.
         */
        int lbbflR_x = Mbti.min(idonR.x, tfxtR.x);
        int lbbflR_widti = Mbti.mbx(idonR.x + idonR.widti,
                                    tfxtR.x + tfxtR.widti) - lbbflR_x;
        int lbbflR_y = Mbti.min(idonR.y, tfxtR.y);
        int lbbflR_ifigit = Mbti.mbx(idonR.y + idonR.ifigit,
                                     tfxtR.y + tfxtR.ifigit) - lbbflR_y;

        int dx, dy;

        if (vfrtidblAlignmfnt == TOP) {
            dy = vifwR.y - lbbflR_y;
        }
        flsf if (vfrtidblAlignmfnt == CENTER) {
            dy = (vifwR.y + (vifwR.ifigit / 2)) - (lbbflR_y + (lbbflR_ifigit / 2));
        }
        flsf { // (vfrtidblAlignmfnt == BOTTOM)
            dy = (vifwR.y + vifwR.ifigit) - (lbbflR_y + lbbflR_ifigit);
        }

        if (iorizontblAlignmfnt == LEFT) {
            dx = vifwR.x - lbbflR_x;
        }
        flsf if (iorizontblAlignmfnt == RIGHT) {
            dx = (vifwR.x + vifwR.widti) - (lbbflR_x + lbbflR_widti);
        }
        flsf { // (iorizontblAlignmfnt == CENTER)
            dx = (vifwR.x + (vifwR.widti / 2)) -
                 (lbbflR_x + (lbbflR_widti / 2));
        }

        /* Trbnslbtf tfxtR bnd glypyR by dx,dy.
         */

        tfxtR.x += dx;
        tfxtR.y += dy;

        idonR.x += dx;
        idonR.y += dy;

        if (lsb < 0) {
            // lsb is nfgbtivf. Siift tif x lodbtion so tibt tif tfxt is
            // visublly drbwn bt tif rigit lodbtion.
            tfxtR.x -= lsb;

            tfxtR.widti += lsb;
        }
        if (rsb > 0) {
            tfxtR.widti -= rsb;
        }

        rfturn tfxt;
    }


    /**
     * Pbints b domponfnt to tif spfdififd <dodf>Grbpiids</dodf>.
     * Tiis mftiod is primbrily usfful to rfndfr
     * <dodf>Componfnt</dodf>s tibt don't fxist bs pbrt of tif visiblf
     * dontbinmfnt iifrbrdiy, but brf usfd for rfndfring.  For
     * fxbmplf, if you brf doing your own rfndfring bnd wbnt to rfndfr
     * somf tfxt (or fvfn HTML), you dould mbkf usf of
     * <dodf>JLbbfl</dodf>'s tfxt rfndfring support bnd ibvf it pbint
     * dirfdtly by wby of tiis mftiod, witiout bdding tif lbbfl to tif
     * visiblf dontbinmfnt iifrbrdiy.
     * <p>
     * Tiis mftiod mbkfs usf of <dodf>CfllRfndfrfrPbnf</dodf> to ibndlf
     * tif bdtubl pbinting, bnd is only rfdommfndfd if you usf onf
     * domponfnt for rfndfring.  If you mbkf usf of multiplf domponfnts
     * to ibndlf tif rfndfring, bs <dodf>JTbblf</dodf> dofs, usf
     * <dodf>CfllRfndfrfrPbnf</dodf> dirfdtly.  Otifrwisf, bs dfsdribfd
     * bflow, you dould fnd up witi b <dodf>CfllRfndfrfrPbnf</dodf>
     * pfr <dodf>Componfnt</dodf>.
     * <p>
     * If <dodf>d</dodf>'s pbrfnt is not b <dodf>CfllRfndfrfrPbnf</dodf>,
     * b nfw <dodf>CfllRfndfrfrPbnf</dodf> is drfbtfd, <dodf>d</dodf> is
     * bddfd to it, bnd tif <dodf>CfllRfndfrfrPbnf</dodf> is bddfd to
     * <dodf>p</dodf>.  If <dodf>d</dodf>'s pbrfnt is b
     * <dodf>CfllRfndfrfrPbnf</dodf> bnd tif <dodf>CfllRfndfrfrPbnf</dodf>s
     * pbrfnt is not <dodf>p</dodf>, it is bddfd to <dodf>p</dodf>.
     * <p>
     * Tif domponfnt siould fitifr dfsdfnd from <dodf>JComponfnt</dodf>
     * or bf bnotifr kind of ligitwfigit domponfnt.
     * A ligitwfigit domponfnt is onf wiosf "ligitwfigit" propfrty
     * (rfturnfd by tif <dodf>Componfnt</dodf>
     * <dodf>isLigitwfigit</dodf> mftiod)
     * is truf. If tif Componfnt is not ligitwfigit, bbd tiings mbp ibppfn:
     * drbsifs, fxdfptions, pbinting problfms...
     *
     * @pbrbm g  tif <dodf>Grbpiids</dodf> objfdt to drbw on
     * @pbrbm d  tif <dodf>Componfnt</dodf> to drbw
     * @pbrbm p  tif intfrmfdibtf <dodf>Contbinfr</dodf>
     * @pbrbm x  bn int spfdifying tif lfft sidf of tif brfb drbw in, in pixfls,
     *           mfbsurfd from tif lfft fdgf of tif grbpiids dontfxt
     * @pbrbm y  bn int spfdifying tif top of tif brfb to drbw in, in pixfls
     *           mfbsurfd down from tif top fdgf of tif grbpiids dontfxt
     * @pbrbm w  bn int spfdifying tif widti of tif brfb drbw in, in pixfls
     * @pbrbm i  bn int spfdifying tif ifigit of tif brfb drbw in, in pixfls
     *
     * @sff CfllRfndfrfrPbnf
     * @sff jbvb.bwt.Componfnt#isLigitwfigit
     */
    publid stbtid void pbintComponfnt(Grbpiids g, Componfnt d, Contbinfr p, int x, int y, int w, int i) {
        gftCfllRfndfrfrPbnf(d, p).pbintComponfnt(g, d, p, x, y, w, i,fblsf);
    }

    /**
     * Pbints b domponfnt to tif spfdififd <dodf>Grbpiids</dodf>.  Tiis
     * is b dovfr mftiod for
     * {@link #pbintComponfnt(Grbpiids,Componfnt,Contbinfr,int,int,int,int)}.
     * Rfffr to it for morf informbtion.
     *
     * @pbrbm g  tif <dodf>Grbpiids</dodf> objfdt to drbw on
     * @pbrbm d  tif <dodf>Componfnt</dodf> to drbw
     * @pbrbm p  tif intfrmfdibtf <dodf>Contbinfr</dodf>
     * @pbrbm r  tif <dodf>Rfdtbnglf</dodf> to drbw in
     *
     * @sff #pbintComponfnt(Grbpiids,Componfnt,Contbinfr,int,int,int,int)
     * @sff CfllRfndfrfrPbnf
     */
    publid stbtid void pbintComponfnt(Grbpiids g, Componfnt d, Contbinfr p, Rfdtbnglf r) {
        pbintComponfnt(g, d, p, r.x, r.y, r.widti, r.ifigit);
    }


    /*
     * Ensurfs tibt dfll rfndfrfr <dodf>d</dodf> ibs b
     * <dodf>ComponfntSifll</dodf> pbrfnt bnd tibt
     * tif sifll's pbrfnt is p.
     */
    privbtf stbtid CfllRfndfrfrPbnf gftCfllRfndfrfrPbnf(Componfnt d, Contbinfr p) {
        Contbinfr sifll = d.gftPbrfnt();
        if (sifll instbndfof CfllRfndfrfrPbnf) {
            if (sifll.gftPbrfnt() != p) {
                p.bdd(sifll);
            }
        } flsf {
            sifll = nfw CfllRfndfrfrPbnf();
            sifll.bdd(d);
            p.bdd(sifll);
        }
        rfturn (CfllRfndfrfrPbnf)sifll;
    }

    /**
     * A simplf mindfd look bnd fffl dibngf: bsk fbdi nodf in tif trff
     * to <dodf>updbtfUI()</dodf> -- tibt is, to initiblizf its UI propfrty
     * witi tif durrfnt look bnd fffl.
     *
     * @pbrbm d tif domponfnt
     */
    publid stbtid void updbtfComponfntTrffUI(Componfnt d) {
        updbtfComponfntTrffUI0(d);
        d.invblidbtf();
        d.vblidbtf();
        d.rfpbint();
    }

    privbtf stbtid void updbtfComponfntTrffUI0(Componfnt d) {
        if (d instbndfof JComponfnt) {
            JComponfnt jd = (JComponfnt) d;
            jd.updbtfUI();
            JPopupMfnu jpm =jd.gftComponfntPopupMfnu();
            if(jpm != null) {
                updbtfComponfntTrffUI(jpm);
            }
        }
        Componfnt[] diildrfn = null;
        if (d instbndfof JMfnu) {
            diildrfn = ((JMfnu)d).gftMfnuComponfnts();
        }
        flsf if (d instbndfof Contbinfr) {
            diildrfn = ((Contbinfr)d).gftComponfnts();
        }
        if (diildrfn != null) {
            for (Componfnt diild : diildrfn) {
                updbtfComponfntTrffUI0(diild);
            }
        }
    }


    /**
     * Cbusfs <i>doRun.run()</i> to bf fxfdutfd bsyndironously on tif
     * AWT fvfnt dispbtdiing tirfbd.  Tiis will ibppfn bftfr bll
     * pfnding AWT fvfnts ibvf bffn prodfssfd.  Tiis mftiod siould
     * bf usfd wifn bn bpplidbtion tirfbd nffds to updbtf tif GUI.
     * In tif following fxbmplf tif <dodf>invokfLbtfr</dodf> dbll qufufs
     * tif <dodf>Runnbblf</dodf> objfdt <dodf>doHflloWorld</dodf>
     * on tif fvfnt dispbtdiing tirfbd bnd
     * tifn prints b mfssbgf.
     * <prf>
     * Runnbblf doHflloWorld = nfw Runnbblf() {
     *     publid void run() {
     *         Systfm.out.println("Hfllo World on " + Tirfbd.durrfntTirfbd());
     *     }
     * };
     *
     * SwingUtilitifs.invokfLbtfr(doHflloWorld);
     * Systfm.out.println("Tiis migit wfll bf displbyfd bfforf tif otifr mfssbgf.");
     * </prf>
     * If invokfLbtfr is dbllfd from tif fvfnt dispbtdiing tirfbd --
     * for fxbmplf, from b JButton's AdtionListfnfr -- tif <i>doRun.run()</i> will
     * still bf dfffrrfd until bll pfnding fvfnts ibvf bffn prodfssfd.
     * Notf tibt if tif <i>doRun.run()</i> tirows bn undbugit fxdfption
     * tif fvfnt dispbtdiing tirfbd will unwind (not tif durrfnt tirfbd).
     * <p>
     * Additionbl dodumfntbtion bnd fxbmplfs for tiis mftiod dbn bf
     * found in
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy in Swing</b>.
     * <p>
     * As of 1.3 tiis mftiod is just b dovfr for <dodf>jbvb.bwt.EvfntQufuf.invokfLbtfr()</dodf>.
     * <p>
     * Unlikf tif rfst of Swing, tiis mftiod dbn bf invokfd from bny tirfbd.
     *
     * @pbrbm doRun tif instbndf of {@dodf Runnbblf}
     * @sff #invokfAndWbit
     */
    publid stbtid void invokfLbtfr(Runnbblf doRun) {
        EvfntQufuf.invokfLbtfr(doRun);
    }


    /**
     * Cbusfs <dodf>doRun.run()</dodf> to bf fxfdutfd syndironously on tif
     * AWT fvfnt dispbtdiing tirfbd.  Tiis dbll blodks until
     * bll pfnding AWT fvfnts ibvf bffn prodfssfd bnd (tifn)
     * <dodf>doRun.run()</dodf> rfturns. Tiis mftiod siould
     * bf usfd wifn bn bpplidbtion tirfbd nffds to updbtf tif GUI.
     * It siouldn't bf dbllfd from tif fvfnt dispbtdiing tirfbd.
     * Hfrf's bn fxbmplf tibt drfbtfs b nfw bpplidbtion tirfbd
     * tibt usfs <dodf>invokfAndWbit</dodf> to print b string from tif fvfnt
     * dispbtdiing tirfbd bnd tifn, wifn tibt's finisifd, print
     * b string from tif bpplidbtion tirfbd.
     * <prf>
     * finbl Runnbblf doHflloWorld = nfw Runnbblf() {
     *     publid void run() {
     *         Systfm.out.println("Hfllo World on " + Tirfbd.durrfntTirfbd());
     *     }
     * };
     *
     * Tirfbd bppTirfbd = nfw Tirfbd() {
     *     publid void run() {
     *         try {
     *             SwingUtilitifs.invokfAndWbit(doHflloWorld);
     *         }
     *         dbtdi (Exdfption f) {
     *             f.printStbdkTrbdf();
     *         }
     *         Systfm.out.println("Finisifd on " + Tirfbd.durrfntTirfbd());
     *     }
     * };
     * bppTirfbd.stbrt();
     * </prf>
     * Notf tibt if tif <dodf>Runnbblf.run</dodf> mftiod tirows bn
     * undbugit fxdfption
     * (on tif fvfnt dispbtdiing tirfbd) it's dbugit bnd rftirown, bs
     * bn <dodf>InvodbtionTbrgftExdfption</dodf>, on tif dbllfr's tirfbd.
     * <p>
     * Additionbl dodumfntbtion bnd fxbmplfs for tiis mftiod dbn bf
     * found in
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy in Swing</b>.
     * <p>
     * As of 1.3 tiis mftiod is just b dovfr for
     * <dodf>jbvb.bwt.EvfntQufuf.invokfAndWbit()</dodf>.
     *
     * @pbrbm doRun tif instbndf of {@dodf Runnbblf}
     * @fxdfption  IntfrruptfdExdfption if wf'rf intfrruptfd wiilf wbiting for
     *             tif fvfnt dispbtdiing tirfbd to finisi fxfduting
     *             <dodf>doRun.run()</dodf>
     * @fxdfption  InvodbtionTbrgftExdfption  if bn fxdfption is tirown
     *             wiilf running <dodf>doRun</dodf>
     *
     * @sff #invokfLbtfr
     */
    publid stbtid void invokfAndWbit(finbl Runnbblf doRun)
        tirows IntfrruptfdExdfption, InvodbtionTbrgftExdfption
    {
        EvfntQufuf.invokfAndWbit(doRun);
    }

    /**
     * Rfturns truf if tif durrfnt tirfbd is bn AWT fvfnt dispbtdiing tirfbd.
     * <p>
     * As of 1.3 tiis mftiod is just b dovfr for
     * <dodf>jbvb.bwt.EvfntQufuf.isDispbtdiTirfbd()</dodf>.
     *
     * @rfturn truf if tif durrfnt tirfbd is bn AWT fvfnt dispbtdiing tirfbd
     */
    publid stbtid boolfbn isEvfntDispbtdiTirfbd()
    {
        rfturn EvfntQufuf.isDispbtdiTirfbd();
    }


    /*
     * --- Addfssibility Support ---
     *
     */

    /**
     * Gft tif indfx of tiis objfdt in its bddfssiblf pbrfnt.<p>
     *
     * Notf: bs of tif Jbvb 2 plbtform v1.3, it is rfdommfndfd tibt dfvflopfrs dbll
     * Componfnt.AddfssiblfAWTComponfnt.gftAddfssiblfIndfxInPbrfnt() instfbd
     * of using tiis mftiod.
     *
     * @pbrbm d tif domponfnt
     * @rfturn -1 of tiis objfdt dofs not ibvf bn bddfssiblf pbrfnt.
     * Otifrwisf, tif indfx of tif diild in its bddfssiblf pbrfnt.
     */
    publid stbtid int gftAddfssiblfIndfxInPbrfnt(Componfnt d) {
        rfturn d.gftAddfssiblfContfxt().gftAddfssiblfIndfxInPbrfnt();
    }

    /**
     * Rfturns tif <dodf>Addfssiblf</dodf> diild dontbinfd bt tif
     * lodbl doordinbtf <dodf>Point</dodf>, if onf fxists.
     * Otifrwisf rfturns <dodf>null</dodf>.
     *
     * @pbrbm d tif domponfnt
     * @pbrbm p tif lodbl doordinbtf
     * @rfturn tif <dodf>Addfssiblf</dodf> bt tif spfdififd lodbtion,
     *    if it fxists; otifrwisf <dodf>null</dodf>
     */
    publid stbtid Addfssiblf gftAddfssiblfAt(Componfnt d, Point p) {
        if (d instbndfof Contbinfr) {
            rfturn d.gftAddfssiblfContfxt().gftAddfssiblfComponfnt().gftAddfssiblfAt(p);
        } flsf if (d instbndfof Addfssiblf) {
            Addfssiblf b = (Addfssiblf) d;
            if (b != null) {
                AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd != null) {
                    AddfssiblfComponfnt bdmp;
                    Point lodbtion;
                    int ndiildrfn = bd.gftAddfssiblfCiildrfnCount();
                    for (int i=0; i < ndiildrfn; i++) {
                        b = bd.gftAddfssiblfCiild(i);
                        if ((b != null)) {
                            bd = b.gftAddfssiblfContfxt();
                            if (bd != null) {
                                bdmp = bd.gftAddfssiblfComponfnt();
                                if ((bdmp != null) && (bdmp.isSiowing())) {
                                    lodbtion = bdmp.gftLodbtion();
                                    Point np = nfw Point(p.x-lodbtion.x,
                                                         p.y-lodbtion.y);
                                    if (bdmp.dontbins(np)){
                                        rfturn b;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            rfturn (Addfssiblf) d;
        }
        rfturn null;
    }

    /**
     * Gft tif stbtf of tiis objfdt. <p>
     *
     * Notf: bs of tif Jbvb 2 plbtform v1.3, it is rfdommfndfd tibt dfvflopfrs dbll
     * Componfnt.AddfssiblfAWTComponfnt.gftAddfssiblfIndfxInPbrfnt() instfbd
     * of using tiis mftiod.
     *
     * @pbrbm d tif domponfnt
     * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif durrfnt stbtf
     * sft of tif objfdt
     * @sff AddfssiblfStbtf
     */
    publid stbtid AddfssiblfStbtfSft gftAddfssiblfStbtfSft(Componfnt d) {
        rfturn d.gftAddfssiblfContfxt().gftAddfssiblfStbtfSft();
    }

    /**
     * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
     * of tif diildrfn of tiis objfdt implfmfnt Addfssiblf, tibn tiis
     * mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt. <p>
     *
     * Notf: bs of tif Jbvb 2 plbtform v1.3, it is rfdommfndfd tibt dfvflopfrs dbll
     * Componfnt.AddfssiblfAWTComponfnt.gftAddfssiblfIndfxInPbrfnt() instfbd
     * of using tiis mftiod.
     *
     * @pbrbm d tif domponfnt
     * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt.
     */
    publid stbtid int gftAddfssiblfCiildrfnCount(Componfnt d) {
        rfturn d.gftAddfssiblfContfxt().gftAddfssiblfCiildrfnCount();
    }

    /**
     * Rfturn tif nti Addfssiblf diild of tif objfdt. <p>
     *
     * Notf: bs of tif Jbvb 2 plbtform v1.3, it is rfdommfndfd tibt dfvflopfrs dbll
     * Componfnt.AddfssiblfAWTComponfnt.gftAddfssiblfIndfxInPbrfnt() instfbd
     * of using tiis mftiod.
     *
     * @pbrbm d tif domponfnt
     * @pbrbm i zfro-bbsfd indfx of diild
     * @rfturn tif nti Addfssiblf diild of tif objfdt
     */
    publid stbtid Addfssiblf gftAddfssiblfCiild(Componfnt d, int i) {
        rfturn d.gftAddfssiblfContfxt().gftAddfssiblfCiild(i);
    }

    /**
     * Rfturn tif diild <dodf>Componfnt</dodf> of tif spfdififd
     * <dodf>Componfnt</dodf> tibt is tif fodus ownfr, if bny.
     *
     * @pbrbm d tif root of tif <dodf>Componfnt</dodf> iifrbrdiy to
     *        sfbrdi for tif fodus ownfr
     * @rfturn tif fodus ownfr, or <dodf>null</dodf> if tifrf is no fodus
     *         ownfr, or if tif fodus ownfr is not <dodf>domp</dodf>, or b
     *         dfsdfndbnt of <dodf>domp</dodf>
     *
     * @sff jbvb.bwt.KfybobrdFodusMbnbgfr#gftFodusOwnfr
     * @dfprfdbtfd As of 1.4, rfplbdfd by
     *   <dodf>KfybobrdFodusMbnbgfr.gftFodusOwnfr()</dodf>.
     */
    @Dfprfdbtfd
    publid stbtid Componfnt findFodusOwnfr(Componfnt d) {
        Componfnt fodusOwnfr = KfybobrdFodusMbnbgfr.
            gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();

        // vfrify fodusOwnfr is b dfsdfndbnt of d
        for (Componfnt tfmp = fodusOwnfr; tfmp != null;
             tfmp = (tfmp instbndfof Window) ? null : tfmp.gftPbrfnt())
        {
            if (tfmp == d) {
                rfturn fodusOwnfr;
            }
        }

        rfturn null;
    }

    /**
     * If d is b JRootPbnf dfsdfndbnt rfturn its JRootPbnf bndfstor.
     * If d is b RootPbnfContbinfr tifn rfturn its JRootPbnf.
     *
     * @pbrbm d tif domponfnt
     * @rfturn tif JRootPbnf for Componfnt d or {@dodf null}.
     */
    publid stbtid JRootPbnf gftRootPbnf(Componfnt d) {
        if (d instbndfof RootPbnfContbinfr) {
            rfturn ((RootPbnfContbinfr)d).gftRootPbnf();
        }
        for( ; d != null; d = d.gftPbrfnt()) {
            if (d instbndfof JRootPbnf) {
                rfturn (JRootPbnf)d;
            }
        }
        rfturn null;
    }


    /**
     * Rfturns tif root domponfnt for tif durrfnt domponfnt trff.
     *
     * @pbrbm d tif domponfnt
     * @rfturn tif first bndfstor of d tibt's b Window or tif lbst Applft bndfstor
     */
    publid stbtid Componfnt gftRoot(Componfnt d) {
        Componfnt bpplft = null;
        for(Componfnt p = d; p != null; p = p.gftPbrfnt()) {
            if (p instbndfof Window) {
                rfturn p;
            }
            if (p instbndfof Applft) {
                bpplft = p;
            }
        }
        rfturn bpplft;
    }

    stbtid JComponfnt gftPbintingOrigin(JComponfnt d) {
        Contbinfr p = d;
        wiilf ((p = p.gftPbrfnt()) instbndfof JComponfnt) {
            JComponfnt jp = (JComponfnt) p;
            if (jp.isPbintingOrigin()) {
                rfturn jp;
            }
        }
        rfturn null;
    }

    /**
     * Prodfss tif kfy bindings for tif <dodf>Componfnt</dodf> bssodibtfd witi
     * <dodf>fvfnt</dodf>. Tiis mftiod is only usfful if
     * <dodf>fvfnt.gftComponfnt()</dodf> dofs not dfsdfnd from
     * <dodf>JComponfnt</dodf>, or your brf not invoking
     * <dodf>supfr.prodfssKfyEvfnt</dodf> from witiin your
     * <dodf>JComponfnt</dodf> subdlbss. <dodf>JComponfnt</dodf>
     * butombtidblly prodfssfs bindings from witiin its
     * <dodf>prodfssKfyEvfnt</dodf> mftiod, ifndf you rbrfly nffd
     * to dirfdtly invokf tiis mftiod.
     *
     * @pbrbm fvfnt KfyEvfnt usfd to idfntify wiidi bindings to prodfss, bs
     *              wfll bs wiidi Componfnt ibs fodus.
     * @rfturn truf if b binding ibs found bnd prodfssfd
     * @sindf 1.4
     */
    publid stbtid boolfbn prodfssKfyBindings(KfyEvfnt fvfnt) {
        if (fvfnt != null) {
            if (fvfnt.isConsumfd()) {
                rfturn fblsf;
            }

            Componfnt domponfnt = fvfnt.gftComponfnt();
            boolfbn prfssfd = (fvfnt.gftID() == KfyEvfnt.KEY_PRESSED);

            if (!isVblidKfyEvfntForKfyBindings(fvfnt)) {
                rfturn fblsf;
            }
            // Find tif first JComponfnt in tif bndfstor iifrbrdiy, bnd
            // invokf prodfssKfyBindings on it
            wiilf (domponfnt != null) {
                if (domponfnt instbndfof JComponfnt) {
                    rfturn ((JComponfnt)domponfnt).prodfssKfyBindings(
                                                   fvfnt, prfssfd);
                }
                if ((domponfnt instbndfof Applft) ||
                    (domponfnt instbndfof Window)) {
                    // No JComponfnts, if Window or Applft pbrfnt, prodfss
                    // WHEN_IN_FOCUSED_WINDOW bindings.
                    rfturn JComponfnt.prodfssKfyBindingsForAllComponfnts(
                                  fvfnt, (Contbinfr)domponfnt, prfssfd);
                }
                domponfnt = domponfnt.gftPbrfnt();
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if tif <dodf>f</dodf> is b vblid KfyEvfnt to usf in
     * prodfssing tif kfy bindings bssodibtfd witi JComponfnts.
     */
    stbtid boolfbn isVblidKfyEvfntForKfyBindings(KfyEvfnt f) {
        rfturn truf;
    }

    /**
     * Invokfs <dodf>bdtionPfrformfd</dodf> on <dodf>bdtion</dodf> if
     * <dodf>bdtion</dodf> is fnbblfd (bnd non-{@dodf null}). Tif dommbnd for tif
     * AdtionEvfnt is dftfrminfd by:
     * <ol>
     *   <li>If tif bdtion wbs rfgistfrfd vib
     *       <dodf>rfgistfrKfybobrdAdtion</dodf>, tifn tif dommbnd string
     *       pbssfd in ({@dodf null} will bf usfd if {@dodf null} wbs pbssfd in).
     *   <li>Adtion vbluf witi nbmf Adtion.ACTION_COMMAND_KEY, unlfss {@dodf null}.
     *   <li>String vbluf of tif KfyEvfnt, unlfss <dodf>gftKfyCibr</dodf>
     *       rfturns KfyEvfnt.CHAR_UNDEFINED..
     * </ol>
     * Tiis will rfturn truf if <dodf>bdtion</dodf> is non-{@dodf null} bnd
     * bdtionPfrformfd is invokfd on it.
     *
     * @pbrbm bdtion bn bdtion
     * @pbrbm ks b kfy strokf
     * @pbrbm fvfnt b kfy fvfnt
     * @pbrbm sfndfr b sfndfr
     * @pbrbm modififrs bdtion modififrs
     * @rfturn {@dodf truf} if {@dodf bdtion} is non-{@dodf null} bnd
     *         bdtionPfrformfd is invokfd on it.
     *
     * @sindf 1.3
     */
    publid stbtid boolfbn notifyAdtion(Adtion bdtion, KfyStrokf ks,
                                       KfyEvfnt fvfnt, Objfdt sfndfr,
                                       int modififrs) {
        if (bdtion == null) {
            rfturn fblsf;
        }
        if (bdtion instbndfof UIAdtion) {
            if (!((UIAdtion)bdtion).isEnbblfd(sfndfr)) {
                rfturn fblsf;
            }
        }
        flsf if (!bdtion.isEnbblfd()) {
            rfturn fblsf;
        }
        Objfdt dommbndO;
        boolfbn stbyNull;

        // Gft tif dommbnd objfdt.
        dommbndO = bdtion.gftVbluf(Adtion.ACTION_COMMAND_KEY);
        if (dommbndO == null && (bdtion instbndfof JComponfnt.AdtionStbndin)) {
            // AdtionStbndin is usfd for iistoridbl rfbsons to support
            // rfgistfrKfybobrdAdtion witi b null vbluf.
            stbyNull = truf;
        }
        flsf {
            stbyNull = fblsf;
        }

        // Convfrt it to b string.
        String dommbnd;

        if (dommbndO != null) {
            dommbnd = dommbndO.toString();
        }
        flsf if (!stbyNull && fvfnt.gftKfyCibr() != KfyEvfnt.CHAR_UNDEFINED) {
            dommbnd = String.vblufOf(fvfnt.gftKfyCibr());
        }
        flsf {
            // Do null for undffinfd dibrs, or if rfgistfrKfybobrdAdtion
            // wbs dbllfd witi b null.
            dommbnd = null;
        }
        bdtion.bdtionPfrformfd(nfw AdtionEvfnt(sfndfr,
                        AdtionEvfnt.ACTION_PERFORMED, dommbnd, fvfnt.gftWifn(),
                        modififrs));
        rfturn truf;
    }


    /**
     * Convfnifndf mftiod to dibngf tif UI InputMbp for <dodf>domponfnt</dodf>
     * to <dodf>uiInputMbp</dodf>. If <dodf>uiInputMbp</dodf> is {@dodf null},
     * tiis rfmovfs bny prfviously instbllfd UI InputMbp.
     *
     * @pbrbm domponfnt b domponfnt
     * @pbrbm typf b typf
     * @pbrbm uiInputMbp bn {@dodf InputMbp}
     * @sindf 1.3
     */
    publid stbtid void rfplbdfUIInputMbp(JComponfnt domponfnt, int typf,
                                         InputMbp uiInputMbp) {
        InputMbp mbp = domponfnt.gftInputMbp(typf, (uiInputMbp != null));

        wiilf (mbp != null) {
            InputMbp pbrfnt = mbp.gftPbrfnt();
            if (pbrfnt == null || (pbrfnt instbndfof UIRfsourdf)) {
                mbp.sftPbrfnt(uiInputMbp);
                rfturn;
            }
            mbp = pbrfnt;
        }
    }


    /**
     * Convfnifndf mftiod to dibngf tif UI AdtionMbp for <dodf>domponfnt</dodf>
     * to <dodf>uiAdtionMbp</dodf>. If <dodf>uiAdtionMbp</dodf> is {@dodf null},
     * tiis rfmovfs bny prfviously instbllfd UI AdtionMbp.
     *
     * @pbrbm domponfnt b domponfnt
     * @pbrbm uiAdtionMbp bn {@dodf AdtionMbp}
     * @sindf 1.3
     */
    publid stbtid void rfplbdfUIAdtionMbp(JComponfnt domponfnt,
                                          AdtionMbp uiAdtionMbp) {
        AdtionMbp mbp = domponfnt.gftAdtionMbp((uiAdtionMbp != null));

        wiilf (mbp != null) {
            AdtionMbp pbrfnt = mbp.gftPbrfnt();
            if (pbrfnt == null || (pbrfnt instbndfof UIRfsourdf)) {
                mbp.sftPbrfnt(uiAdtionMbp);
                rfturn;
            }
            mbp = pbrfnt;
        }
    }


    /**
     * Rfturns tif InputMbp providfd by tif UI for dondition
     * <dodf>dondition</dodf> in domponfnt <dodf>domponfnt</dodf>.
     * <p>Tiis will rfturn {@dodf null} if tif UI ibs not instbllfd bn InputMbp
     * of tif spfdififd typf.
     *
     * @pbrbm domponfnt b domponfnt
     * @pbrbm dondition b dondition
     * @rfturn tif {@dodf AdtionMbp} providfd by tif UI for {@dodf dondition}
     *         in tif domponfnt, or {@dodf null} if tif UI ibs not instbllfd
     *         bn InputMbp of tif spfdififd typf.
     * @sindf 1.3
     */
    publid stbtid InputMbp gftUIInputMbp(JComponfnt domponfnt, int dondition) {
        InputMbp mbp = domponfnt.gftInputMbp(dondition, fblsf);
        wiilf (mbp != null) {
            InputMbp pbrfnt = mbp.gftPbrfnt();
            if (pbrfnt instbndfof UIRfsourdf) {
                rfturn pbrfnt;
            }
            mbp = pbrfnt;
        }
        rfturn null;
    }

    /**
     * Rfturns tif AdtionMbp providfd by tif UI
     * in domponfnt <dodf>domponfnt</dodf>.
     * <p>Tiis will rfturn {@dodf null} if tif UI ibs not instbllfd bn AdtionMbp.
     *
     * @pbrbm domponfnt b domponfnt
     * @rfturn tif {@dodf AdtionMbp} providfd by tif UI in tif domponfnt,
     *         or {@dodf null} if tif UI ibs not instbllfd bn AdtionMbp.
     * @sindf 1.3
     */
    publid stbtid AdtionMbp gftUIAdtionMbp(JComponfnt domponfnt) {
        AdtionMbp mbp = domponfnt.gftAdtionMbp(fblsf);
        wiilf (mbp != null) {
            AdtionMbp pbrfnt = mbp.gftPbrfnt();
            if (pbrfnt instbndfof UIRfsourdf) {
                rfturn pbrfnt;
            }
            mbp = pbrfnt;
        }
        rfturn null;
    }


    // Don't usf String, bs it's not gubrbntffd to bf uniquf in b Hbsitbblf.
    privbtf stbtid finbl Objfdt sibrfdOwnfrFrbmfKfy =
       nfw StringBufffr("SwingUtilitifs.sibrfdOwnfrFrbmf");

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss SibrfdOwnfrFrbmf fxtfnds Frbmf implfmfnts WindowListfnfr {
        publid void bddNotify() {
            supfr.bddNotify();
            instbllListfnfrs();
        }

        /**
         * Instbll window listfnfrs on ownfd windows to wbtdi for displbybbility dibngfs
         */
        void instbllListfnfrs() {
            Window[] windows = gftOwnfdWindows();
            for (Window window : windows) {
                if (window != null) {
                    window.rfmovfWindowListfnfr(tiis);
                    window.bddWindowListfnfr(tiis);
                }
            }
        }

        /**
         * Wbtdifs for displbybbility dibngfs bnd disposfs sibrfd instbndf if tifrf brf no
         * displbybblf diildrfn lfft.
         */
        publid void windowClosfd(WindowEvfnt f) {
            syndironizfd(gftTrffLodk()) {
                Window[] windows = gftOwnfdWindows();
                for (Window window : windows) {
                    if (window != null) {
                        if (window.isDisplbybblf()) {
                            rfturn;
                        }
                        window.rfmovfWindowListfnfr(tiis);
                    }
                }
                disposf();
            }
        }
        publid void windowOpfnfd(WindowEvfnt f) {
        }
        publid void windowClosing(WindowEvfnt f) {
        }
        publid void windowIdonififd(WindowEvfnt f) {
        }
        publid void windowDfidonififd(WindowEvfnt f) {
        }
        publid void windowAdtivbtfd(WindowEvfnt f) {
        }
        publid void windowDfbdtivbtfd(WindowEvfnt f) {
        }

        publid void siow() {
            // Tiis frbmf dbn nfvfr bf siown
        }
        publid void disposf() {
            try {
                gftToolkit().gftSystfmEvfntQufuf();
                supfr.disposf();
            } dbtdi (Exdfption f) {
                // untrustfd dodf not bllowfd to disposf
            }
        }
    }

    /**
     * Rfturns b toolkit-privbtf, sibrfd, invisiblf Frbmf
     * to bf tif ownfr for JDiblogs bnd JWindows drfbtfd witi
     * {@dodf null} ownfrs.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    stbtid Frbmf gftSibrfdOwnfrFrbmf() tirows HfbdlfssExdfption {
        Frbmf sibrfdOwnfrFrbmf =
            (Frbmf)SwingUtilitifs.bppContfxtGft(sibrfdOwnfrFrbmfKfy);
        if (sibrfdOwnfrFrbmf == null) {
            sibrfdOwnfrFrbmf = nfw SibrfdOwnfrFrbmf();
            SwingUtilitifs.bppContfxtPut(sibrfdOwnfrFrbmfKfy,
                                         sibrfdOwnfrFrbmf);
        }
        rfturn sibrfdOwnfrFrbmf;
    }

    /**
     * Rfturns b SibrfdOwnfrFrbmf's siutdown listfnfr to disposf tif SibrfdOwnfrFrbmf
     * if it ibs no morf displbybblf diildrfn.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    stbtid WindowListfnfr gftSibrfdOwnfrFrbmfSiutdownListfnfr() tirows HfbdlfssExdfption {
        Frbmf sibrfdOwnfrFrbmf = gftSibrfdOwnfrFrbmf();
        rfturn (WindowListfnfr)sibrfdOwnfrFrbmf;
    }

    /* Don't mbkf tifsf AppContfxt bddfssors publid or protfdtfd --
     * sindf AppContfxt is in sun.bwt in 1.2, wf siouldn't fxposf it
     * fvfn indirfdtly witi b publid API.
     */
    // REMIND(bim): pibsf out usf of 4 mftiods bflow sindf tify
    // brf just privbtf dovfrs for AWT mftiods (?)

    stbtid Objfdt bppContfxtGft(Objfdt kfy) {
        rfturn AppContfxt.gftAppContfxt().gft(kfy);
    }

    stbtid void bppContfxtPut(Objfdt kfy, Objfdt vbluf) {
        AppContfxt.gftAppContfxt().put(kfy, vbluf);
    }

    stbtid void bppContfxtRfmovf(Objfdt kfy) {
        AppContfxt.gftAppContfxt().rfmovf(kfy);
    }


    stbtid Clbss<?> lobdSystfmClbss(String dlbssNbmf) tirows ClbssNotFoundExdfption {
        RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
        rfturn Clbss.forNbmf(dlbssNbmf, truf, Tirfbd.durrfntTirfbd().
                             gftContfxtClbssLobdfr());
    }


   /*
     * Convfnifndf fundtion for dftfrmining ComponfntOrifntbtion.  Hflps us
     * bvoid ibving Mungf dirfdtivfs tirougiout tif dodf.
     */
    stbtid boolfbn isLfftToRigit( Componfnt d ) {
        rfturn d.gftComponfntOrifntbtion().isLfftToRigit();
    }
    privbtf SwingUtilitifs() {
        tirow nfw Error("SwingUtilitifs is just b dontbinfr for stbtid mftiods");
    }

    /**
     * Rfturns truf if tif Idon <dodf>idon</dodf> is bn instbndf of
     * ImbgfIdon, bnd tif imbgf it dontbins is tif sbmf bs <dodf>imbgf</dodf>.
     */
    stbtid boolfbn dofsIdonRfffrfndfImbgf(Idon idon, Imbgf imbgf) {
        Imbgf idonImbgf = (idon != null && (idon instbndfof ImbgfIdon)) ?
                           ((ImbgfIdon)idon).gftImbgf() : null;
        rfturn (idonImbgf == imbgf);
    }

    /**
     * Rfturns indfx of tif first oddurrfndf of <dodf>mnfmonid</dodf>
     * witiin string <dodf>tfxt</dodf>. Mbtdiing blgoritim is not
     * dbsf-sfnsitivf.
     *
     * @pbrbm tfxt Tif tfxt to sfbrdi tirougi, mby bf {@dodf null}
     * @pbrbm mnfmonid Tif mnfmonid to find tif dibrbdtfr for.
     * @rfturn indfx into tif string if fxists, otifrwisf -1
     */
    stbtid int findDisplbyfdMnfmonidIndfx(String tfxt, int mnfmonid) {
        if (tfxt == null || mnfmonid == '\0') {
            rfturn -1;
        }

        dibr ud = Cibrbdtfr.toUppfrCbsf((dibr)mnfmonid);
        dibr ld = Cibrbdtfr.toLowfrCbsf((dibr)mnfmonid);

        int udi = tfxt.indfxOf(ud);
        int ldi = tfxt.indfxOf(ld);

        if (udi == -1) {
            rfturn ldi;
        } flsf if(ldi == -1) {
            rfturn udi;
        } flsf {
            rfturn (ldi < udi) ? ldi : udi;
        }
    }

    /**
     * Storfs tif position bnd sizf of
     * tif innfr pbinting brfb of tif spfdififd domponfnt
     * in <dodf>r</dodf> bnd rfturns <dodf>r</dodf>.
     * Tif position bnd sizf spfdify tif bounds of tif domponfnt,
     * bdjustfd so bs not to indludf tif bordfr brfb (tif insfts).
     * Tiis mftiod is usfful for dlbssfs
     * tibt implfmfnt pbinting dodf.
     *
     * @pbrbm d  tif JComponfnt in qufstion; if {@dodf null}, tiis mftiod rfturns {@dodf null}
     * @pbrbm r  tif Rfdtbnglf instbndf to bf modififd;
     *           mby bf {@dodf null}
     * @rfturn {@dodf null} if tif Componfnt is {@dodf null};
     *         otifrwisf, rfturns tif pbssfd-in rfdtbnglf (if non-{@dodf null})
     *         or b nfw rfdtbnglf spfdifying position bnd sizf informbtion
     *
     * @sindf 1.4
     */
    publid stbtid Rfdtbnglf dbldulbtfInnfrArfb(JComponfnt d, Rfdtbnglf r) {
        if (d == null) {
            rfturn null;
        }
        Rfdtbnglf rfdt = r;
        Insfts insfts = d.gftInsfts();

        if (rfdt == null) {
            rfdt = nfw Rfdtbnglf();
        }

        rfdt.x = insfts.lfft;
        rfdt.y = insfts.top;
        rfdt.widti = d.gftWidti() - insfts.lfft - insfts.rigit;
        rfdt.ifigit = d.gftHfigit() - insfts.top - insfts.bottom;

        rfturn rfdt;
    }

    stbtid void updbtfRfndfrfrOrEditorUI(Objfdt rfndfrfrOrEditor) {
        if (rfndfrfrOrEditor == null) {
            rfturn;
        }

        Componfnt domponfnt = null;

        if (rfndfrfrOrEditor instbndfof Componfnt) {
            domponfnt = (Componfnt)rfndfrfrOrEditor;
        }
        if (rfndfrfrOrEditor instbndfof DffbultCfllEditor) {
            domponfnt = ((DffbultCfllEditor)rfndfrfrOrEditor).gftComponfnt();
        }

        if (domponfnt != null) {
            SwingUtilitifs.updbtfComponfntTrffUI(domponfnt);
        }
    }

    /**
     * Rfturns tif first bndfstor of tif {@dodf domponfnt}
     * wiidi is not bn instbndf of {@link JLbyfr}.
     *
     * @pbrbm domponfnt {@dodf Componfnt} to gft
     * tif first bndfstor of, wiidi is not b {@link JLbyfr} instbndf.
     *
     * @rfturn tif first bndfstor of tif {@dodf domponfnt}
     * wiidi is not bn instbndf of {@link JLbyfr}.
     * If sudi bn bndfstor dbn not bf found, {@dodf null} is rfturnfd.
     *
     * @tirows NullPointfrExdfption if {@dodf domponfnt} is {@dodf null}
     * @sff JLbyfr
     *
     * @sindf 1.7
     */
    publid stbtid Contbinfr gftUnwrbppfdPbrfnt(Componfnt domponfnt) {
        Contbinfr pbrfnt = domponfnt.gftPbrfnt();
        wiilf(pbrfnt instbndfof JLbyfr) {
            pbrfnt = pbrfnt.gftPbrfnt();
        }
        rfturn pbrfnt;
    }

    /**
     * Rfturns tif first {@dodf JVifwport}'s dfsdfndbnt
     * wiidi is not bn instbndf of {@dodf JLbyfr}.
     * If sudi b dfsdfndbnt dbn not bf found, {@dodf null} is rfturnfd.
     *
     * If tif {@dodf vifwport}'s vifw domponfnt is not b {@dodf JLbyfr},
     * tiis mftiod is fquivblfnt to {@link JVifwport#gftVifw()}
     * otifrwisf {@link JLbyfr#gftVifw()} will bf rfdursivfly
     * dbllfd on bll dfsdfnding {@dodf JLbyfr}s.
     *
     * @pbrbm vifwport {@dodf JVifwport} to gft tif first dfsdfndbnt of,
     * wiidi in not b {@dodf JLbyfr} instbndf.
     *
     * @rfturn tif first {@dodf JVifwport}'s dfsdfndbnt
     * wiidi is not bn instbndf of {@dodf JLbyfr}.
     * If sudi b dfsdfndbnt dbn not bf found, {@dodf null} is rfturnfd.
     *
     * @tirows NullPointfrExdfption if {@dodf vifwport} is {@dodf null}
     * @sff JVifwport#gftVifw()
     * @sff JLbyfr
     *
     * @sindf 1.7
     */
    publid stbtid Componfnt gftUnwrbppfdVifw(JVifwport vifwport) {
        Componfnt vifw = vifwport.gftVifw();
        wiilf (vifw instbndfof JLbyfr) {
            vifw = ((JLbyfr)vifw).gftVifw();
        }
        rfturn vifw;
    }

   /**
     * Rftrifvfs tif vblidbtf root of b givfn dontbinfr.
     *
     * If tif dontbinfr is dontbinfd witiin b {@dodf CfllRfndfrfrPbnf}, tiis
     * mftiod rfturns {@dodf null} duf to tif syntiftid nbturf of tif {@dodf
     * CfllRfndfrfrPbnf}.
     * <p>
     * Tif domponfnt iifrbrdiy must bf displbybblf up to tif toplfvfl domponfnt
     * (fitifr b {@dodf Frbmf} or bn {@dodf Applft} objfdt.) Otifrwisf tiis
     * mftiod rfturns {@dodf null}.
     * <p>
     * If tif {@dodf visiblfOnly} brgumfnt is {@dodf truf}, tif found vblidbtf
     * root bnd bll its pbrfnts up to tif toplfvfl domponfnt must blso bf
     * visiblf. Otifrwisf tiis mftiod rfturns {@dodf null}.
     *
     * @rfturn tif vblidbtf root of tif givfn dontbinfr or null
     * @sff jbvb.bwt.Componfnt#isDisplbybblf()
     * @sff jbvb.bwt.Componfnt#isVisiblf()
     * @sindf 1.7
     */
    stbtid Contbinfr gftVblidbtfRoot(Contbinfr d, boolfbn visiblfOnly) {
        Contbinfr root = null;

        for (; d != null; d = d.gftPbrfnt())
        {
            if (!d.isDisplbybblf() || d instbndfof CfllRfndfrfrPbnf) {
                rfturn null;
            }
            if (d.isVblidbtfRoot()) {
                root = d;
                brfbk;
            }
        }

        if (root == null) {
            rfturn null;
        }

        for (; d != null; d = d.gftPbrfnt()) {
            if (!d.isDisplbybblf() || (visiblfOnly && !d.isVisiblf())) {
                rfturn null;
            }
            if (d instbndfof Window || d instbndfof Applft) {
                rfturn root;
            }
        }

        rfturn null;
    }
}
