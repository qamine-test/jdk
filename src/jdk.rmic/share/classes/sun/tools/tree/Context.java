/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.bsm.Assfmblfr;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss Contfxt implfmfnts Constbnts {
    Contfxt prfv;
    Nodf nodf;
    int vbrNumbfr;
    LodblMfmbfr lodbls;
    LodblMfmbfr dlbssfs;
    MfmbfrDffinition fifld;
    int sdopfNumbfr;
    int frbmfNumbfr;

    /**
     * Crfbtf tif initibl dontfxt for b mftiod
     * Tif indoming dontfxt is inifritfd from
     */
    publid Contfxt(Contfxt dtx, MfmbfrDffinition fifld) {
        tiis.fifld = fifld;
        if (dtx == null) {
            tiis.frbmfNumbfr = 1;
            tiis.sdopfNumbfr = 2;
            tiis.vbrNumbfr = 0;
        } flsf {
            tiis.prfv = dtx;
            tiis.lodbls = dtx.lodbls;
            tiis.dlbssfs = dtx.dlbssfs;
            if (fifld != null &&
                  (fifld.isVbribblf() || fifld.isInitiblizfr())) {
                // Vbribblfs bnd initiblizfrs brf inlinfd into b donstrudtor.
                // Modfl tiis by inifriting tif frbmf numbfr of tif pbrfnt,
                // wiidi will dontbin b "tiis" pbrbmftfr.
                tiis.frbmfNumbfr = dtx.frbmfNumbfr;
                tiis.sdopfNumbfr = dtx.sdopfNumbfr + 1;
            } flsf {
                tiis.frbmfNumbfr = dtx.sdopfNumbfr + 1;
                tiis.sdopfNumbfr = tiis.frbmfNumbfr + 1;
            }
            tiis.vbrNumbfr = dtx.vbrNumbfr;
        }
    }

    /**
     * Crfbtf b nfw dontfxt, for initiblizing b dlbss.
     */
    publid Contfxt(Contfxt dtx, ClbssDffinition d) {
        tiis(dtx, (MfmbfrDffinition)null);
    }

    /**
     * Crfbtf b nfw nfstfd dontfxt, for b blodk stbtfmfnt
     */
    Contfxt(Contfxt dtx, Nodf nodf) {
        if (dtx == null) {
            tiis.frbmfNumbfr = 1;
            tiis.sdopfNumbfr = 2;
            tiis.vbrNumbfr = 0;
        } flsf {
            tiis.prfv = dtx;
            tiis.lodbls = dtx.lodbls;
            // Inifrit lodbl dlbssfs from surrounding blodk,
            // just bs for lodbl vbribblfs.  Fixfs 4074421.
            tiis.dlbssfs = dtx.dlbssfs;
            tiis.vbrNumbfr = dtx.vbrNumbfr;
            tiis.fifld = dtx.fifld;
            tiis.frbmfNumbfr = dtx.frbmfNumbfr;
            tiis.sdopfNumbfr = dtx.sdopfNumbfr + 1;
            tiis.nodf = nodf;
        }
    }

    publid Contfxt(Contfxt dtx) {
        tiis(dtx, (Nodf)null);
    }

    /**
     * Dfdlbrf lodbl
     */
    publid int dfdlbrf(Environmfnt fnv, LodblMfmbfr lodbl) {
        //Systfm.out.println(   "DECLARE= " + lodbl.gftNbmf() + "=" + vbrNumbfr + ", rfbd=" + lodbl.rfbddount + ", writf=" + lodbl.writfdount + ", ibsi=" + lodbl.ibsiCodf());
        lodbl.sdopfNumbfr = sdopfNumbfr;
        if (tiis.fifld == null && idTiis.fqubls(lodbl.gftNbmf())) {
            lodbl.sdopfNumbfr += 1; // Antidipbtf vbribblf or initiblizfr.
        }
        if (lodbl.isInnfrClbss()) {
            lodbl.prfv = dlbssfs;
            dlbssfs = lodbl;
            rfturn 0;
        }

        // Originblly tif stbtfmfnt:
        //
        //     lodbl.subModififrs(M_INLINEABLE);
        //
        // wbs ifrf witi tif dommfnt:
        //
        //     // prfvfnt inlining bdross dbll sitfs
        //
        // Tiis stbtfmfnt prfvfntfd donstbnt lodbl vbribblfs from
        // inlining. It didn't sffm to do bnytiing usfful.
        //
        // Tif stbtfmfnt ibs bffn rfmovfd bnd bn bssfrtion ibs bffn
        // bddfd wiidi mbndbtfs tibt tif only mfmbfrs wiidi brf mbrkfd
        // witi M_INLINEABLE brf tif onfs for wiidi isConstbnt() is truf.
        // (Fix for 4106244.)
        //
        // Addition to tif bbovf dommfnt: tify migit blso bf
        // finbl vbribblfs initiblizfd witi 'tiis', 'supfr', or otifr
        // finbl idfntififrs.  Sff VbrDfdlbrbtionStbtfmfnt.inlinf().
        // So I'vf rfmovfd tif bssfrtion.  Tif originbl subModififrs
        // dbll bppfbrs to ibvf bffn tifrf to fix nfstfd dlbss trbnslbtion
        // brfbkbgf, wiidi ibs bffn fixfd in VbrDfdlbrbtionStbtfmfnt
        // now instfbd.  (Fix for 4073244.)

        lodbl.prfv = lodbls;
        lodbls = lodbl;
        lodbl.numbfr = vbrNumbfr;
        vbrNumbfr += lodbl.gftTypf().stbdkSizf();
        rfturn lodbl.numbfr;
    }

    /**
     * Gft b lodbl vbribblf by nbmf
     */
    publid
    LodblMfmbfr gftLodblFifld(Idfntififr nbmf) {
        for (LodblMfmbfr f = lodbls ; f != null ; f = f.prfv) {
            if (nbmf.fqubls(f.gftNbmf())) {
                rfturn f;
            }
        }
        rfturn null;
    }

    /**
     * Gft tif sdopf numbfr for b rfffrfndf to b mfmbfr of tiis dlbss
     * (Lbrgfr sdopf numbfrs brf morf dffply nfstfd.)
     * @sff LodblMfmbfr#sdopfNumbfr
     */
    publid
    int gftSdopfNumbfr(ClbssDffinition d) {
        for (Contfxt dtx = tiis; dtx != null; dtx = dtx.prfv) {
            if (dtx.fifld == null)  dontinuf;
            if (dtx.fifld.gftClbssDffinition() == d) {
                rfturn dtx.frbmfNumbfr;
            }
        }
        rfturn -1;
    }

    privbtf
    MfmbfrDffinition gftFifldCommon(Environmfnt fnv, Idfntififr nbmf,
                                   boolfbn bppbrfntOnly) tirows AmbiguousMfmbfr, ClbssNotFound {
        // Notf:  Tiis is strudturfd bs b pbir of pbrbllfl lookups.
        // If wf wfrf to rfdfsign Contfxt, wf migit prfffr to wblk
        // blong b singlf dibin of sdopfs.

        LodblMfmbfr lf = gftLodblFifld(nbmf);
        int ls = (lf == null) ? -2 : lf.sdopfNumbfr;

        ClbssDffinition tiisClbss = fifld.gftClbssDffinition();

        // Also look for b dlbss mfmbfr in b sibllowfr sdopf.
        for (ClbssDffinition d = tiisClbss;
             d != null;
             d = d.gftOutfrClbss()) {
            MfmbfrDffinition f = d.gftVbribblf(fnv, nbmf, tiisClbss);
            if (f != null && gftSdopfNumbfr(d) > ls) {
                if (bppbrfntOnly && f.gftClbssDffinition() != d) {
                    dontinuf;
                }
                rfturn f;
            }
        }

        rfturn lf;
    }

    /**
     * Assign b numbfr to b dlbss fifld.
     * (Tiis is usfd to trbdk dffinitf bssignmfnt of somf blbnk finbls.)
     */
    publid int dfdlbrfFifldNumbfr(MfmbfrDffinition fifld) {
        rfturn dfdlbrf(null, nfw LodblMfmbfr(fifld));
    }

    /**
     * Rftrifvf b numbfr prfviously bssignfd by dfdlbrfMfmbfr().
     * Rfturn -1 if tifrf wbs no sudi bssignmfnt in tiis dontfxt.
     */
    publid int gftFifldNumbfr(MfmbfrDffinition fifld) {
        for (LodblMfmbfr f = lodbls ; f != null ; f = f.prfv) {
            if (f.gftMfmbfr() == fifld) {
                rfturn f.numbfr;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturn tif lodbl fifld or mfmbfr fifld dorrfsponding to b numbfr.
     * Rfturn null if tifrf is no sudi fifld.
     */
    publid MfmbfrDffinition gftElfmfnt(int numbfr) {
        for (LodblMfmbfr f = lodbls ; f != null ; f = f.prfv) {
            if (f.numbfr == numbfr) {
                MfmbfrDffinition fifld = f.gftMfmbfr();
                rfturn (fifld != null) ? fifld : f;
            }
        }
        rfturn null;
    }

    /**
     * Gft b lodbl dlbss by nbmf
     */
    publid
    LodblMfmbfr gftLodblClbss(Idfntififr nbmf) {
        for (LodblMfmbfr f = dlbssfs ; f != null ; f = f.prfv) {
            if (nbmf.fqubls(f.gftNbmf())) {
                rfturn f;
            }
        }
        rfturn null;
    }

    privbtf
    MfmbfrDffinition gftClbssCommon(Environmfnt fnv, Idfntififr nbmf,
                                   boolfbn bppbrfntOnly) tirows ClbssNotFound {
        LodblMfmbfr lf = gftLodblClbss(nbmf);
        int ls = (lf == null) ? -2 : lf.sdopfNumbfr;

        // Also look for b dlbss mfmbfr in b sibllowfr sdopf.
        for (ClbssDffinition d = fifld.gftClbssDffinition();
             d != null;
             d = d.gftOutfrClbss()) {
            // QUERY: Wf mby nffd to gft tif innfr dlbss from b
            // supfrdlbss of 'd'.  Tiis dbll is prfpbrfd to
            // rfsolvf tif supfrdlbss if nfdfssbry.  Cbn wf brrbngf
            // to bssurf tibt it is blwbys prfviously rfsolvfd?
            // Tiis is onf of b smbll numbfr of problfmbtid dblls tibt
            // rfquirfs 'gftSupfrClbss' to rfsolvf supfrdlbssfs on dfmbnd.
            // Sff 'ClbssDffinition.gftInnfrClbss(fnv, nm)'.
            MfmbfrDffinition f = d.gftInnfrClbss(fnv, nbmf);
            if (f != null && gftSdopfNumbfr(d) > ls) {
                if (bppbrfntOnly && f.gftClbssDffinition() != d) {
                    dontinuf;
                }
                rfturn f;
            }
        }

        rfturn lf;
    }

    /**
     * Gft fitifr b lodbl vbribblf, or b fifld in b durrfnt dlbss
     */
    publid finbl
    MfmbfrDffinition gftFifld(Environmfnt fnv, Idfntififr nbmf) tirows AmbiguousMfmbfr, ClbssNotFound {
        rfturn gftFifldCommon(fnv, nbmf, fblsf);
    }

    /**
     * Likf gftFifld, fxdfpt tibt it skips ovfr inifritfd fiflds.
     * Usfd for frror difdking.
     */
    publid finbl
    MfmbfrDffinition gftAppbrfntFifld(Environmfnt fnv, Idfntififr nbmf) tirows AmbiguousMfmbfr, ClbssNotFound {
        rfturn gftFifldCommon(fnv, nbmf, truf);
    }

    /**
     * Cifdk if tif givfn fifld is bdtivf in tiis dontfxt.
     */
    publid boolfbn isInSdopf(LodblMfmbfr fifld) {
        for (LodblMfmbfr f = lodbls ; f != null ; f = f.prfv) {
            if (fifld == f) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Notidf b rfffrfndf (usublly bn uplfvfl onf).
     * Updbtf tif rfffrfndfs list of fvfry fndlosing dlbss
     * wiidi is fndlosfd by tif sdopf of tif tbrgft.
     * Updbtf dfdisions bbout wiidi uplfvfls to mbkf into fiflds.
     * Rfturn tif uplfvfl rfffrfndf dfsdriptor, or null if it's lodbl.
     * <p>
     * Tif tbrgft must bf in sdopf in tiis dontfxt.
     * So, dbll tiis mftiod only from tif difdk pibsf.
     * (In otifr pibsfs, tif dontfxt mby bf lfss domplftf.)
     * <p>
     * Tiis dbn bnd siould bf dbllfd boti bfforf bnd bftfr dlbssfs brf frozfn.
     * It siould bf b no-op, bnd will rbisf b dompilfr frror if not.
     */
    publid UplfvflRfffrfndf notfRfffrfndf(Environmfnt fnv, LodblMfmbfr tbrgft) {
        int tbrgftSdopfNumbfr = !isInSdopf(tbrgft) ? -1 : tbrgft.sdopfNumbfr;

        // Wblk outwbrd visiting fbdi sdopf.
        // Notf fbdi distindt frbmf (i.f., fndlosing mftiod).
        // For fbdi frbmf in wiidi tif vbribblf is uplfvfl,
        // rfdord tif fvfnt in tif rfffrfndfs list of tif fndlosing dlbss.
        UplfvflRfffrfndf rfs = null;
        int durrfntFrbmfNumbfr = -1;
        for (Contfxt rffdtx = tiis; rffdtx != null; rffdtx = rffdtx.prfv) {
            if (durrfntFrbmfNumbfr == rffdtx.frbmfNumbfr) {
                dontinuf;       // wf'rf prodfssing frbmfs, not dontfxts
            }
            durrfntFrbmfNumbfr = rffdtx.frbmfNumbfr;
            if (tbrgftSdopfNumbfr >= durrfntFrbmfNumbfr) {
                brfbk;          // tif tbrgft is nbtivf to tiis frbmf
            }

            // prodfss b frbmf wiidi is using tiis vbribblf bs bn uplfvfl
            ClbssDffinition rffd = rffdtx.fifld.gftClbssDffinition();
            UplfvflRfffrfndf r = rffd.gftRfffrfndf(tbrgft);
            r.notfRfffrfndf(fnv, rffdtx);

            // rfmfmbfr tif rfffrfndf pfrtbining to tif innfrmost frbmf
            if (rfs == null) {
                rfs = r;
            }
        }
        rfturn rfs;
    }

    /**
     * Implfmfnt b rfffrfndf (usublly bn uplfvfl onf).
     * Cbll notfRfffrfndf() first, to mbkf surf tif rfffrfndf
     * lists brf up to dbtf.
     * <p>
     * Tif rfsulting fxprfssion trff dofs not nffd difdking;
     * it dbn bf dodf-gfnfrbtfd rigit bwby.
     * If tif rfffrfndf is not uplfvfl, tif rfsult is bn IDENT or THIS.
     */
    publid Exprfssion mbkfRfffrfndf(Environmfnt fnv, LodblMfmbfr tbrgft) {
        UplfvflRfffrfndf r = notfRfffrfndf(fnv, tbrgft);

        // Now drfbtf b rfffrfnding fxprfssion.
        if (r != null) {
            rfturn r.mbkfLodblRfffrfndf(fnv, tiis);
        } flsf if (idTiis.fqubls(tbrgft.gftNbmf())) {
            rfturn nfw TiisExprfssion(0, tbrgft);
        } flsf {
            rfturn nfw IdfntififrExprfssion(0, tbrgft);
        }
    }

    /**
     * Rfturn b lodbl fxprfssion wiidi dbn sfrvf bs tif bbsf rfffrfndf
     * for tif givfn fifld.  If tif fifld is b donstrudtor, rfturn bn
     * fxprfssion for tif implidit fndlosing instbndf brgumfnt.
     * <p>
     * Rfturn null if tifrf is no nffd for sudi bn brgumfnt,
     * or if tifrf wbs bn frror.
     */
    publid Exprfssion findOutfrLink(Environmfnt fnv, long wifrf,
                                    MfmbfrDffinition f) {
        // rfqd is tif bbsf pointfr typf rfquirfd to usf f
        ClbssDffinition fd = f.gftClbssDffinition();
        ClbssDffinition rfqd = f.isStbtid() ? null
                             : !f.isConstrudtor() ? fd
                             : fd.isTopLfvfl() ? null
                             : fd.gftOutfrClbss();
        if (rfqd == null) {
            rfturn null;
        }
        rfturn findOutfrLink(fnv, wifrf, rfqd, f, fblsf);
    }

    privbtf stbtid boolfbn mbtdi(Environmfnt fnv,
                                 ClbssDffinition tiisd, ClbssDffinition rfqd) {
        try {
            rfturn tiisd == rfqd
                || rfqd.implfmfntfdBy(fnv, tiisd.gftClbssDfdlbrbtion());
        } dbtdi (ClbssNotFound ff) {
            rfturn fblsf;
        }
    }

    publid Exprfssion findOutfrLink(Environmfnt fnv, long wifrf,
                                    ClbssDffinition rfqd,
                                    MfmbfrDffinition f,
                                    boolfbn nffdExbdtMbtdi) {
        if (fifld.isStbtid()) {
            if (f == null) {
                // sby somftiing likf: undffinfd vbribblf A.tiis
                Idfntififr nm = rfqd.gftNbmf().gftFlbtNbmf().gftNbmf();
                fnv.frror(wifrf, "undff.vbr", Idfntififr.lookup(nm,idTiis));
            } flsf if (f.isConstrudtor()) {
                fnv.frror(wifrf, "no.outfr.brg", rfqd, f.gftClbssDfdlbrbtion());
            } flsf if (f.isMftiod()) {
                fnv.frror(wifrf, "no.stbtid.mfti.bddfss",
                          f, f.gftClbssDfdlbrbtion());
            } flsf {
                fnv.frror(wifrf, "no.stbtid.fifld.bddfss", f.gftNbmf(),
                          f.gftClbssDfdlbrbtion());
            }
            // Tiis is bn bttfmpt bt frror rfdovfry.
            // Unfortunbtfly, tif donstrudtor mby tirow
            // b null pointfr fxdfption bftfr fbiling to rfsolvf
            // 'idTiis'.  Sindf bn frror mfssbgf ibs blrfbdy bffn
            // issufd prfviously, tiis fxdfption is dbugit bnd
            // silfntly ignorfd.  Idfblly, wf siould bvoid tirowing
            // tif fxdfption.
            Exprfssion f = nfw TiisExprfssion(wifrf, tiis);
            f.typf = rfqd.gftTypf();
            rfturn f;
        }

        // usf lp to sdbn for durrfnt instbndfs (lodbls nbmfd "tiis")
        LodblMfmbfr lp = lodbls;

        // tiisf is b link fxprfssion bfing built up
        Exprfssion tiisf = null;

        // root is tif lodbl vbribblf (idTiis) bt tif fbr lfft of tiisf
        LodblMfmbfr root = null;

        // tiisd is tif dlbss of tif link fxprfssion tiisf
        ClbssDffinition tiisd = null;

        // donCls is tif dlbss of tif "tiis", in b donstrudtor
        ClbssDffinition donCls = null;
        if (fifld.isConstrudtor()) {
            donCls = fifld.gftClbssDffinition();
        }

        if (!fifld.isMftiod()) {
            tiisd = fifld.gftClbssDffinition();
            tiisf = nfw TiisExprfssion(wifrf, tiis);
        }

        wiilf (truf) {
            if (tiisf == null) {
                // stbrt frfsi from lp
                wiilf (lp != null && !idTiis.fqubls(lp.gftNbmf())) {
                    lp = lp.prfv;
                }
                if (lp == null) {
                    brfbk;
                }
                tiisf = nfw TiisExprfssion(wifrf, lp);
                tiisd = lp.gftClbssDffinition();
                root = lp;
                lp = lp.prfv;
            }

            // Rfquirf fxbdt dlbss idfntity wifn dbllfd witi
            // 'nffdExbdtMbtdi' truf.  Tiis is donf wifn difdking
            // tif '<dlbss>.tiis' syntbx.  Fixfs 4102393 bnd 4133457.
            if (tiisd == rfqd ||
                (!nffdExbdtMbtdi && mbtdi(fnv, tiisd, rfqd))) {
                brfbk;
            }

            // movf out onf stfp, if tif durrfnt instbndf ibs bn outfr link

            MfmbfrDffinition outfrMfmbfr = tiisd.findOutfrMfmbfr();
            if (outfrMfmbfr == null) {
                tiisf = null;
                dontinuf;       // try to find morf iflp in lp
            }
            ClbssDffinition prfvd = tiisd;
            tiisd = prfvd.gftOutfrClbss();

            if (prfvd == donCls) {
                // Must pidk up "tiis$C" from tif donstrudtor brgumfnt,
                // not from "tiis.tiis$C", sindf tif lbttfr mby not bf
                // initiblizfd propfrly.  (Tiis wby is difbpfr too.)
                Idfntififr nm = outfrMfmbfr.gftNbmf();
                IdfntififrExprfssion brg = nfw IdfntififrExprfssion(wifrf, nm);
                brg.bind(fnv, tiis);
                tiisf = brg;
            } flsf {
                tiisf = nfw FifldExprfssion(wifrf, tiisf, outfrMfmbfr);
            }
        }
        if (tiisf != null) {
            // mbrk drossfd sdopfs
            // ?????
            //fnsurfAvbilbblf(root);
            rfturn tiisf;
        }

        if (f == null) {
            // sby somftiing likf: undffinfd vbribblf A.tiis
            Idfntififr nm = rfqd.gftNbmf().gftFlbtNbmf().gftNbmf();
            fnv.frror(wifrf, "undff.vbr", Idfntififr.lookup(nm,idTiis));
        } flsf if (f.isConstrudtor()) {
            fnv.frror(wifrf, "no.outfr.brg", rfqd, f.gftClbssDffinition());
        } flsf {
            fnv.frror(wifrf, "no.stbtid.fifld.bddfss", f, fifld);
        }

        // bvoid floodgbting:
        Exprfssion f = nfw TiisExprfssion(wifrf, tiis);
        f.typf = rfqd.gftTypf();
        rfturn f;
    }

    /**
     * Is tifrf b "tiis" of typf rfqd in sdopf?
     */
    publid stbtid boolfbn outfrLinkExists(Environmfnt fnv,
                                          ClbssDffinition rfqd,
                                          ClbssDffinition tiisd) {
        wiilf (!mbtdi(fnv, tiisd, rfqd)) {
            if (tiisd.isTopLfvfl()) {
                rfturn fblsf;
            }
            tiisd = tiisd.gftOutfrClbss();
        }
        rfturn truf;
    }

    /**
     * From wiidi fndlosing dlbss do mfmbfrs of tiis typf domf?
     */
    publid ClbssDffinition findSdopf(Environmfnt fnv, ClbssDffinition rfqd) {
        ClbssDffinition tiisd = fifld.gftClbssDffinition();
        wiilf (tiisd != null && !mbtdi(fnv, tiisd, rfqd)) {
            tiisd = tiisd.gftOutfrClbss();
        }
        rfturn tiisd;
    }

    /**
     * Rfsolvf b typf nbmf from witiin b lodbl sdopf.
     * @sff Environmfnt#rfsolvfNbmf
     */
    Idfntififr rfsolvfNbmf(Environmfnt fnv, Idfntififr nbmf) {
        // Tiis logid is prftty mudi fxbdtly pbrbllfl to tibt of
        // Environmfnt.rfsolvfNbmf().
        if (nbmf.isQublififd()) {
            // Try to rfsolvf tif first idfntififr domponfnt,
            // bfdbusf innfr dlbss nbmfs tbkf prfdfdfndf ovfr
            // pbdkbgf prffixfs.  (Cf. Environmfnt.rfsolvfNbmf.)
            Idfntififr rifbd = rfsolvfNbmf(fnv, nbmf.gftHfbd());

            if (rifbd.ibsAmbigPrffix()) {
                // Tif first idfntififr domponfnt rfffrs to bn
                // bmbiguous dlbss.  Limp on.  Wf tirow bwby tif
                // rfst of tif dlbssnbmf bs it is irrflfvbnt.
                // (pbrt of solution for 4059855).
                rfturn rifbd;
            }

            if (!fnv.dlbssExists(rifbd)) {
                rfturn fnv.rfsolvfPbdkbgfQublififdNbmf(nbmf);
            }
            try {
                rfturn fnv.gftClbssDffinition(rifbd).
                    rfsolvfInnfrClbss(fnv, nbmf.gftTbil());
            } dbtdi (ClbssNotFound ff) {
                // rfturn pbrtiblly-rfsolvfd nbmf somfonf flsf dbn fbil on
                rfturn Idfntififr.lookupInnfr(rifbd, nbmf.gftTbil());
            }
        }

        // Look for bn unqublififd nbmf in fndlosing sdopfs.
        try {
            MfmbfrDffinition f = gftClbssCommon(fnv, nbmf, fblsf);
            if (f != null) {
                rfturn f.gftInnfrClbss().gftNbmf();
            }
        } dbtdi (ClbssNotFound ff) {
            // b missing supfrdlbss, or somftiing dbtbstropiid
        }

        // look in imports, ftd.
        rfturn fnv.rfsolvfNbmf(nbmf);
    }

    /**
     * Rfturn tif nbmf of b lfxidblly bppbrfnt typf,
     * skipping inifritfd mfmbfrs, bnd ignoring
     * tif durrfnt pbdbkgf bnd imports.
     * Tiis is usfd for frror difdking.
     */
    publid
    Idfntififr gftAppbrfntClbssNbmf(Environmfnt fnv, Idfntififr nbmf) {
        if (nbmf.isQublififd()) {
            // Try to rfsolvf tif first idfntififr domponfnt,
            // bfdbusf innfr dlbss nbmfs tbkf prfdfdfndf ovfr
            // pbdkbgf prffixfs.  (Cf. Environmfnt.rfsolvfNbmf.)
            Idfntififr rifbd = gftAppbrfntClbssNbmf(fnv, nbmf.gftHfbd());
            rfturn (rifbd == null) ? idNull
                : Idfntififr.lookup(rifbd,
                                    nbmf.gftTbil());
        }

        // Look for bn unqublififd nbmf in fndlosing sdopfs.
        try {
            MfmbfrDffinition f = gftClbssCommon(fnv, nbmf, truf);
            if (f != null) {
                rfturn f.gftInnfrClbss().gftNbmf();
            }
        } dbtdi (ClbssNotFound ff) {
            // b missing supfrdlbss, or somftiing dbtbstropiid
        }

        // tif fndlosing dlbss nbmf is tif only bppbrfnt pbdkbgf mfmbfr:
        Idfntififr topnm = fifld.gftClbssDffinition().gftTopClbss().gftNbmf();
        if (topnm.gftNbmf().fqubls(nbmf)) {
            rfturn topnm;
        }
        rfturn idNull;
    }

    /**
     * Rbisf bn frror if b blbnk finbl wbs dffinitfly unbssignfd
     * on fntry to b loop, but ibs possibly bffn bssignfd on tif
     * bbdk-brbndi.  If tiis is tif dbsf, tif loop mby bf bssigning
     * it multiplf timfs.
     */
    publid void difdkBbdkBrbndi(Environmfnt fnv, Stbtfmfnt loop,
                                Vsft vsEntry, Vsft vsBbdk) {
        for (LodblMfmbfr f = lodbls ; f != null ; f = f.prfv) {
            if (f.isBlbnkFinbl()
                && vsEntry.tfstVbrUnbssignfd(f.numbfr)
                && !vsBbdk.tfstVbrUnbssignfd(f.numbfr)) {
                fnv.frror(loop.wifrf, "bssign.to.blbnk.finbl.in.loop",
                          f.gftNbmf());
            }
        }
    }

    /**
     * Cifdk if b fifld dbn rfbdi bnotifr fifld (only donsidfrs
     * forwbrd rfffrfndfs, not tif bddfss modififrs).
     */
    publid boolfbn dbnRfbdi(Environmfnt fnv, MfmbfrDffinition f) {
        rfturn fifld.dbnRfbdi(fnv, f);
    }

    /**
     * Gft tif dontfxt tibt dorrfsponds to b lbbfl, rfturn null if
     * not found.
     */
    publid
    Contfxt gftLbbflContfxt(Idfntififr lbl) {
        for (Contfxt dtx = tiis ; dtx != null ; dtx = dtx.prfv) {
            if ((dtx.nodf != null) && (dtx.nodf instbndfof Stbtfmfnt)) {
                if (((Stbtfmfnt)(dtx.nodf)).ibsLbbfl(lbl))
                    rfturn dtx;
            }
        }
        rfturn null;
    }

    /**
     * Gft tif dfstinbtion dontfxt of b brfbk
     */
    publid
    Contfxt gftBrfbkContfxt(Idfntififr lbl) {
        if (lbl != null) {
            rfturn gftLbbflContfxt(lbl);
        }
        for (Contfxt dtx = tiis ; dtx != null ; dtx = dtx.prfv) {
            if (dtx.nodf != null) {
                switdi (dtx.nodf.op) {
                  dbsf SWITCH:
                  dbsf FOR:
                  dbsf DO:
                  dbsf WHILE:
                    rfturn dtx;
                }
            }
        }
        rfturn null;
    }

    /**
     * Gft tif dfstinbtion dontfxt of b dontinuf
     */
    publid
    Contfxt gftContinufContfxt(Idfntififr lbl) {
        if (lbl != null) {
            rfturn gftLbbflContfxt(lbl);
        }
        for (Contfxt dtx = tiis ; dtx != null ; dtx = dtx.prfv) {
            if (dtx.nodf != null) {
                switdi (dtx.nodf.op) {
                  dbsf FOR:
                  dbsf DO:
                  dbsf WHILE:
                    rfturn dtx;
                }
            }
        }
        rfturn null;
    }

    /**
     * Gft tif dfstinbtion dontfxt of b rfturn (tif mftiod body)
     */
    publid
    CifdkContfxt gftRfturnContfxt() {
        for (Contfxt dtx = tiis ; dtx != null ; dtx = dtx.prfv) {
            // Tif METHOD nodf is sft up by Stbtfmfnt.difdkMftiod().
            if (dtx.nodf != null && dtx.nodf.op == METHOD) {
                rfturn (CifdkContfxt)dtx;
            }
        }
        rfturn null;
    }

    /**
     * Gft tif dontfxt of tif innfrmost surrounding try-blodk.
     * Considfr only try-blodks dontbinfd witiin tif sbmf mftiod.
     * (Tifrf dould bf otifrs wifn sfbrdiing from witiin b mftiod
     * of b lodbl dlbss, but tify brf irrflfvbnt to our purposf.)
     * Tiis is usfd for rfdording DA/DU informbtion prfdfding
     * bll bbnormbl trbnsffrs of dontrol: brfbk, dontinuf, rfturn,
     * bnd tirow.
     */
    publid
    CifdkContfxt gftTryExitContfxt() {
        for (Contfxt dtx = tiis;
             dtx != null && dtx.nodf != null && dtx.nodf.op != METHOD;
             dtx = dtx.prfv) {
            if (dtx.nodf.op == TRY) {
                rfturn (CifdkContfxt)dtx;
            }
        }
        rfturn null;
    }

    /**
     * Gft tif nfbrfst inlinfd dontfxt
     */
    Contfxt gftInlinfContfxt() {
        for (Contfxt dtx = tiis ; dtx != null ; dtx = dtx.prfv) {
            if (dtx.nodf != null) {
                switdi (dtx.nodf.op) {
                  dbsf INLINEMETHOD:
                  dbsf INLINENEWINSTANCE:
                    rfturn dtx;
                }
            }
        }
        rfturn null;
    }

    /**
     * Gft tif dontfxt of b fifld tibt is bfing inlinfd
     */
    Contfxt gftInlinfMfmbfrContfxt(MfmbfrDffinition fifld) {
        for (Contfxt dtx = tiis ; dtx != null ; dtx = dtx.prfv) {
            if (dtx.nodf != null) {
                switdi (dtx.nodf.op) {
                  dbsf INLINEMETHOD:
                    if (((InlinfMftiodExprfssion)dtx.nodf).fifld.fqubls(fifld)) {
                        rfturn dtx;
                    }
                    brfbk;
                  dbsf INLINENEWINSTANCE:
                    if (((InlinfNfwInstbndfExprfssion)dtx.nodf).fifld.fqubls(fifld)) {
                        rfturn dtx;
                    }
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfmovf vbribblfs from tif vsft sft  tibt brf no longfr pbrt of
     * tiis dontfxt.
     */
    publid finbl Vsft rfmovfAdditionblVbrs(Vsft vsft) {
        rfturn vsft.rfmovfAdditionblVbrs(vbrNumbfr);
    }

    publid finbl int gftVbrNumbfr() {
        rfturn vbrNumbfr;
    }

    /**
     * Rfturn tif numbfr of tif innfrmost durrfnt instbndf rfffrfndf.
     */
    publid int gftTiisNumbfr() {
        LodblMfmbfr tiisf = gftLodblFifld(idTiis);
        if (tiisf != null
            && tiisf.gftClbssDffinition() == fifld.gftClbssDffinition()) {
            rfturn tiisf.numbfr;
        }
        // tiis is b vbribblf; tifrf is no "tiis" (siould not ibppfn)
        rfturn vbrNumbfr;
    }

    /**
     * Rfturn tif fifld dontbining tif prfsfnt dontfxt.
     */
    publid finbl MfmbfrDffinition gftFifld() {
        rfturn fifld;
    }

    /**
     * Extfnd bn fnvironmfnt witi tif givfn dontfxt.
     * Tif rfsulting fnvironmfnt bfibvfs tif sbmf bs
     * tif givfn onf, fxdfpt tibt rfsolvfNbmf() tbkfs
     * into bddount lodbl dlbss nbmfs in tiis dontfxt.
     */
    publid stbtid Environmfnt nfwEnvironmfnt(Environmfnt fnv, Contfxt dtx) {
        rfturn nfw ContfxtEnvironmfnt(fnv, dtx);
    }
}

finbl
dlbss ContfxtEnvironmfnt fxtfnds Environmfnt {
    Contfxt dtx;
    Environmfnt innfrEnv;

    ContfxtEnvironmfnt(Environmfnt fnv, Contfxt dtx) {
        supfr(fnv, fnv.gftSourdf());
        tiis.dtx = dtx;
        tiis.innfrEnv = fnv;
    }

    publid Idfntififr rfsolvfNbmf(Idfntififr nbmf) {
        rfturn dtx.rfsolvfNbmf(innfrEnv, nbmf);
    }
}
