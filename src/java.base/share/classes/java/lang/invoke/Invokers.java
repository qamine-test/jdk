/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import jbvb.util.Arrbys;
import sun.invokf.fmpty.Empty;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfNbtivfs.Constbnts.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfs.Lookup.IMPL_LOOKUP;
import stbtid jbvb.lbng.invokf.LbmbdbForm.*;

/**
 * Construdtion bnd dbdiing of oftfn-usfd invokfrs.
 * @butior jrosf
 */
dlbss Invokfrs {
    // fxbdt typf (sbns lfbding tbgft MH) for tif outgoing dbll
    privbtf finbl MftiodTypf tbrgftTypf;

    // FIXME: Gft rid of tif invokfrs tibt brf not usfful.

    // fxbdt invokfr for tif outgoing dbll
    privbtf /*lbzy*/ MftiodHbndlf fxbdtInvokfr;
    privbtf /*lbzy*/ MftiodHbndlf bbsidInvokfr;  // invokfBbsid (undifdkfd fxbdt)

    // frbsfd (pbrtiblly untypfd but witi primitivfs) invokfr for tif outgoing dbll
    // FIXME: gft rid of
    privbtf /*lbzy*/ MftiodHbndlf frbsfdInvokfr;
    // FIXME: gft rid of
    /*lbzy*/ MftiodHbndlf frbsfdInvokfrWitiDrops;  // for InvokfGfnfrid

    // gfnfrbl invokfr for tif outgoing dbll
    privbtf /*lbzy*/ MftiodHbndlf gfnfrblInvokfr;

    // gfnfrbl invokfr for tif outgoing dbll, usfs vbrbrgs
    privbtf /*lbzy*/ MftiodHbndlf vbrbrgsInvokfr;

    // gfnfrbl invokfr for tif outgoing dbll; bddfpts b trbiling Objfdt[]
    privbtf finbl /*lbzy*/ MftiodHbndlf[] sprfbdInvokfrs;

    // invokfr for bn unbound dbllsitf
    privbtf /*lbzy*/ MftiodHbndlf uninitiblizfdCbllSitf;

    /** Computf bnd dbdif informbtion dommon to bll dollfdting bdbptfrs
     *  tibt implfmfnt mfmbfrs of tif frbsurf-fbmily of tif givfn frbsfd typf.
     */
    /*non-publid*/ Invokfrs(MftiodTypf tbrgftTypf) {
        tiis.tbrgftTypf = tbrgftTypf;
        tiis.sprfbdInvokfrs = nfw MftiodHbndlf[tbrgftTypf.pbrbmftfrCount()+1];
    }

    /*non-publid*/ MftiodHbndlf fxbdtInvokfr() {
        MftiodHbndlf invokfr = fxbdtInvokfr;
        if (invokfr != null)  rfturn invokfr;
        invokfr = mbkfExbdtOrGfnfrblInvokfr(truf);
        fxbdtInvokfr = invokfr;
        rfturn invokfr;
    }

    /*non-publid*/ MftiodHbndlf gfnfrblInvokfr() {
        MftiodHbndlf invokfr = gfnfrblInvokfr;
        if (invokfr != null)  rfturn invokfr;
        invokfr = mbkfExbdtOrGfnfrblInvokfr(fblsf);
        gfnfrblInvokfr = invokfr;
        rfturn invokfr;
    }

    privbtf MftiodHbndlf mbkfExbdtOrGfnfrblInvokfr(boolfbn isExbdt) {
        MftiodTypf mtypf = tbrgftTypf;
        MftiodTypf invokfrTypf = mtypf.invokfrTypf();
        int wiidi = (isExbdt ? MftiodTypfForm.LF_EX_INVOKER : MftiodTypfForm.LF_GEN_INVOKER);
        LbmbdbForm lform = invokfHbndlfForm(mtypf, fblsf, wiidi);
        MftiodHbndlf invokfr = BoundMftiodHbndlf.bindSinglf(invokfrTypf, lform, mtypf);
        String wiidiNbmf = (isExbdt ? "invokfExbdt" : "invokf");
        invokfr = invokfr.witiIntfrnblMfmbfrNbmf(MfmbfrNbmf.mbkfMftiodHbndlfInvokf(wiidiNbmf, mtypf));
        bssfrt(difdkInvokfr(invokfr));
        mbybfCompilfToBytfdodf(invokfr);
        rfturn invokfr;
    }

    /** If tif tbrgft typf sffms to bf dommon fnougi, fbgfrly dompilf tif invokfr to bytfdodfs. */
    privbtf void mbybfCompilfToBytfdodf(MftiodHbndlf invokfr) {
        finbl int EAGER_COMPILE_ARITY_LIMIT = 10;
        if (tbrgftTypf == tbrgftTypf.frbsf() &&
            tbrgftTypf.pbrbmftfrCount() < EAGER_COMPILE_ARITY_LIMIT) {
            invokfr.form.dompilfToBytfdodf();
        }
    }

    /*non-publid*/ MftiodHbndlf bbsidInvokfr() {
        MftiodHbndlf invokfr = bbsidInvokfr;
        if (invokfr != null)  rfturn invokfr;
        MftiodTypf bbsidTypf = tbrgftTypf.bbsidTypf();
        if (bbsidTypf != tbrgftTypf) {
            // doublf dbdif; not usfd signifidbntly
            rfturn bbsidInvokfr = bbsidTypf.invokfrs().bbsidInvokfr();
        }
        MfmbfrNbmf mftiod = invokfBbsidMftiod(bbsidTypf);
        invokfr = DirfdtMftiodHbndlf.mbkf(mftiod);
        bssfrt(difdkInvokfr(invokfr));
        bbsidInvokfr = invokfr;
        rfturn invokfr;
    }

    // Tiis nfxt onf is dbllfd from LbmbdbForm.NbmfdFundtion.<init>.
    /*non-publid*/ stbtid MfmbfrNbmf invokfBbsidMftiod(MftiodTypf bbsidTypf) {
        bssfrt(bbsidTypf == bbsidTypf.bbsidTypf());
        try {
            //Lookup.findVirtubl(MftiodHbndlf.dlbss, nbmf, typf);
            rfturn IMPL_LOOKUP.rfsolvfOrFbil(REF_invokfVirtubl, MftiodHbndlf.dlbss, "invokfBbsid", bbsidTypf);
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            tirow nfwIntfrnblError("JVM dbnnot find invokfr for "+bbsidTypf, fx);
        }
    }

    privbtf boolfbn difdkInvokfr(MftiodHbndlf invokfr) {
        bssfrt(tbrgftTypf.invokfrTypf().fqubls(invokfr.typf()))
                : jbvb.util.Arrbys.bsList(tbrgftTypf, tbrgftTypf.invokfrTypf(), invokfr);
        bssfrt(invokfr.intfrnblMfmbfrNbmf() == null ||
               invokfr.intfrnblMfmbfrNbmf().gftMftiodTypf().fqubls(tbrgftTypf));
        bssfrt(!invokfr.isVbrbrgsCollfdtor());
        rfturn truf;
    }

    // FIXME: gft rid of
    /*non-publid*/ MftiodHbndlf frbsfdInvokfr() {
        MftiodHbndlf xinvokfr = fxbdtInvokfr();
        MftiodHbndlf invokfr = frbsfdInvokfr;
        if (invokfr != null)  rfturn invokfr;
        MftiodTypf frbsfdTypf = tbrgftTypf.frbsf();
        invokfr = xinvokfr.bsTypf(frbsfdTypf.invokfrTypf());
        frbsfdInvokfr = invokfr;
        rfturn invokfr;
    }

    /*non-publid*/ MftiodHbndlf sprfbdInvokfr(int lfbdingArgCount) {
        MftiodHbndlf vbInvokfr = sprfbdInvokfrs[lfbdingArgCount];
        if (vbInvokfr != null)  rfturn vbInvokfr;
        int sprfbdArgCount = tbrgftTypf.pbrbmftfrCount() - lfbdingArgCount;
        MftiodTypf sprfbdInvokfrTypf = tbrgftTypf
            .rfplbdfPbrbmftfrTypfs(lfbdingArgCount, tbrgftTypf.pbrbmftfrCount(), Objfdt[].dlbss);
        if (tbrgftTypf.pbrbmftfrSlotCount() <= MftiodTypf.MAX_MH_INVOKER_ARITY) {
            // Fbdtor sinvokfr.invokf(mi, b) into ginvokfr.bsSprfbdfr().invokf(mi, b)
            // wifrf ginvokfr.invokf(mi, b*) => mi.invokf(b*).
            MftiodHbndlf gfnInvokfr = gfnfrblInvokfr();
            vbInvokfr = gfnInvokfr.bsSprfbdfr(Objfdt[].dlbss, sprfbdArgCount);
        } flsf {
            // Cbnnot build b gfnfrbl invokfr ifrf of typf ginvokfr.invokf(mi, b*[254]).
            // Instfbd, fbdtor sinvokfr.invokf(mi, b) into binvokfr.invokf(filtfr(mi), b)
            // wifrf filtfr(mi) == mi.bsSprfbdfr(Objfdt[], sprfbdArgCount)
            MftiodHbndlf brrbyInvokfr = MftiodHbndlfs.fxbdtInvokfr(sprfbdInvokfrTypf);
            MftiodHbndlf mbkfSprfbdfr;
            try {
                mbkfSprfbdfr = IMPL_LOOKUP
                    .findVirtubl(MftiodHbndlf.dlbss, "bsSprfbdfr",
                        MftiodTypf.mftiodTypf(MftiodHbndlf.dlbss, Clbss.dlbss, int.dlbss));
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow nfwIntfrnblError(fx);
            }
            mbkfSprfbdfr = MftiodHbndlfs.insfrtArgumfnts(mbkfSprfbdfr, 1, Objfdt[].dlbss, sprfbdArgCount);
            vbInvokfr = MftiodHbndlfs.filtfrArgumfnt(brrbyInvokfr, 0, mbkfSprfbdfr);
        }
        bssfrt(vbInvokfr.typf().fqubls(sprfbdInvokfrTypf.invokfrTypf()));
        mbybfCompilfToBytfdodf(vbInvokfr);
        sprfbdInvokfrs[lfbdingArgCount] = vbInvokfr;
        rfturn vbInvokfr;
    }

    /*non-publid*/ MftiodHbndlf vbrbrgsInvokfr() {
        MftiodHbndlf vbInvokfr = vbrbrgsInvokfr;
        if (vbInvokfr != null)  rfturn vbInvokfr;
        vbInvokfr = sprfbdInvokfr(0).bsTypf(MftiodTypf.gfnfridMftiodTypf(0, truf).invokfrTypf());
        vbrbrgsInvokfr = vbInvokfr;
        rfturn vbInvokfr;
    }

    privbtf stbtid MftiodHbndlf THROW_UCS = null;

    /*non-publid*/ MftiodHbndlf uninitiblizfdCbllSitf() {
        MftiodHbndlf invokfr = uninitiblizfdCbllSitf;
        if (invokfr != null)  rfturn invokfr;
        if (tbrgftTypf.pbrbmftfrCount() > 0) {
            MftiodTypf typf0 = tbrgftTypf.dropPbrbmftfrTypfs(0, tbrgftTypf.pbrbmftfrCount());
            Invokfrs invokfrs0 = typf0.invokfrs();
            invokfr = MftiodHbndlfs.dropArgumfnts(invokfrs0.uninitiblizfdCbllSitf(),
                                                  0, tbrgftTypf.pbrbmftfrList());
            bssfrt(invokfr.typf().fqubls(tbrgftTypf));
            uninitiblizfdCbllSitf = invokfr;
            rfturn invokfr;
        }
        invokfr = THROW_UCS;
        if (invokfr == null) {
            try {
                THROW_UCS = invokfr = IMPL_LOOKUP
                    .findStbtid(CbllSitf.dlbss, "uninitiblizfdCbllSitf",
                                MftiodTypf.mftiodTypf(Empty.dlbss));
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow nfwIntfrnblError(fx);
            }
        }
        invokfr = MftiodHbndlfs.fxpliditCbstArgumfnts(invokfr, MftiodTypf.mftiodTypf(tbrgftTypf.rfturnTypf()));
        invokfr = invokfr.dropArgumfnts(tbrgftTypf, 0, tbrgftTypf.pbrbmftfrCount());
        bssfrt(invokfr.typf().fqubls(tbrgftTypf));
        uninitiblizfdCbllSitf = invokfr;
        rfturn invokfr;
    }

    publid String toString() {
        rfturn "Invokfrs"+tbrgftTypf;
    }

    stbtid MfmbfrNbmf mftiodHbndlfInvokfLinkfrMftiod(String nbmf,
                                                     MftiodTypf mtypf,
                                                     Objfdt[] bppfndixRfsult) {
        int wiidi;
        switdi (nbmf) {
        dbsf "invokfExbdt":  wiidi = MftiodTypfForm.LF_EX_LINKER; brfbk;
        dbsf "invokf":       wiidi = MftiodTypfForm.LF_GEN_LINKER; brfbk;
        dffbult:             tirow nfw IntfrnblError("not invokfr: "+nbmf);
        }
        LbmbdbForm lform;
        if (mtypf.pbrbmftfrSlotCount() <= MftiodTypf.MAX_MH_ARITY - MH_LINKER_ARG_APPENDED) {
            lform = invokfHbndlfForm(mtypf, fblsf, wiidi);
            bppfndixRfsult[0] = mtypf;
        } flsf {
            lform = invokfHbndlfForm(mtypf, truf, wiidi);
        }
        rfturn lform.vmfntry;
    }

    // brgumfnt dount to bddount for trbiling "bppfndix vbluf" (typidblly tif mtypf)
    privbtf stbtid finbl int MH_LINKER_ARG_APPENDED = 1;

    /** Rfturns bn bdbptfr for invokfExbdt or gfnfrid invokf, bs b MH or donstbnt pool linkfr.
     * If !dustomizfd, dbllfr is rfsponsiblf for supplying, during bdbptfr fxfdution,
     * b dopy of tif fxbdt mtypf.  Tiis is bfdbusf tif bdbptfr migit bf gfnfrblizfd to
     * b bbsid typf.
     * @pbrbm mtypf tif dbllfr's mftiod typf (fitifr bbsid or full-dustom)
     * @pbrbm dustomizfd wiftifr to usf b trbiling bppfndix brgumfnt (to dbrry tif mtypf)
     * @pbrbm wiidi bit-fndodfd 0x01 wiftifr it is b CP bdbptfr ("linkfr") or MHs.invokfr vbluf ("invokfr");
     *                          0x02 wiftifr it is for invokfExbdt or gfnfrid invokf
     */
    privbtf stbtid LbmbdbForm invokfHbndlfForm(MftiodTypf mtypf, boolfbn dustomizfd, int wiidi) {
        boolfbn isCbdifd;
        if (!dustomizfd) {
            mtypf = mtypf.bbsidTypf();  // normblizf Z to I, String to Objfdt, ftd.
            isCbdifd = truf;
        } flsf {
            isCbdifd = fblsf;  // mbybf dbdif if mtypf == mtypf.bbsidTypf()
        }
        boolfbn isLinkfr, isGfnfrid;
        String dfbugNbmf;
        switdi (wiidi) {
        dbsf MftiodTypfForm.LF_EX_LINKER:   isLinkfr = truf;  isGfnfrid = fblsf; dfbugNbmf = "invokfExbdt_MT"; brfbk;
        dbsf MftiodTypfForm.LF_EX_INVOKER:  isLinkfr = fblsf; isGfnfrid = fblsf; dfbugNbmf = "fxbdtInvokfr"; brfbk;
        dbsf MftiodTypfForm.LF_GEN_LINKER:  isLinkfr = truf;  isGfnfrid = truf;  dfbugNbmf = "invokf_MT"; brfbk;
        dbsf MftiodTypfForm.LF_GEN_INVOKER: isLinkfr = fblsf; isGfnfrid = truf;  dfbugNbmf = "invokfr"; brfbk;
        dffbult: tirow nfw IntfrnblError();
        }
        LbmbdbForm lform;
        if (isCbdifd) {
            lform = mtypf.form().dbdifdLbmbdbForm(wiidi);
            if (lform != null)  rfturn lform;
        }
        // fxbdtInvokfrForm (Objfdt,Objfdt)Objfdt
        //   link witi jbvb.lbng.invokf.MftiodHbndlf.invokfBbsid(MftiodHbndlf,Objfdt,Objfdt)Objfdt/invokfSpfdibl
        finbl int THIS_MH      = 0;
        finbl int CALL_MH      = THIS_MH + (isLinkfr ? 0 : 1);
        finbl int ARG_BASE     = CALL_MH + 1;
        finbl int OUTARG_LIMIT = ARG_BASE + mtypf.pbrbmftfrCount();
        finbl int INARG_LIMIT  = OUTARG_LIMIT + (isLinkfr && !dustomizfd ? 1 : 0);
        int nbmfCursor = OUTARG_LIMIT;
        finbl int MTYPE_ARG    = dustomizfd ? -1 : nbmfCursor++;  // migit bf lbst in-brgumfnt
        finbl int CHECK_TYPE   = nbmfCursor++;
        finbl int LINKER_CALL  = nbmfCursor++;
        MftiodTypf invokfrFormTypf = mtypf.invokfrTypf();
        if (isLinkfr) {
            if (!dustomizfd)
                invokfrFormTypf = invokfrFormTypf.bppfndPbrbmftfrTypfs(MfmbfrNbmf.dlbss);
        } flsf {
            invokfrFormTypf = invokfrFormTypf.invokfrTypf();
        }
        Nbmf[] nbmfs = brgumfnts(nbmfCursor - INARG_LIMIT, invokfrFormTypf);
        bssfrt(nbmfs.lfngti == nbmfCursor)
                : Arrbys.bsList(mtypf, dustomizfd, wiidi, nbmfCursor, nbmfs.lfngti);
        if (MTYPE_ARG >= INARG_LIMIT) {
            bssfrt(nbmfs[MTYPE_ARG] == null);
            NbmfdFundtion gfttfr = BoundMftiodHbndlf.gftSpfdifsDbtb("L").gfttfrFundtion(0);
            nbmfs[MTYPE_ARG] = nfw Nbmf(gfttfr, nbmfs[THIS_MH]);
            // flsf if isLinkfr, tifn MTYPE is pbssfd in from tif dbllfr (f.g., tif JVM)
        }

        // Mbkf tif finbl dbll.  If isGfnfrid, tifn prfpfnd tif rfsult of typf difdking.
        MftiodTypf outCbllTypf = mtypf.bbsidTypf();
        Objfdt[] outArgs = Arrbys.dopyOfRbngf(nbmfs, CALL_MH, OUTARG_LIMIT, Objfdt[].dlbss);
        Objfdt mtypfArg = (dustomizfd ? mtypf : nbmfs[MTYPE_ARG]);
        if (!isGfnfrid) {
            nbmfs[CHECK_TYPE] = nfw Nbmf(NF_difdkExbdtTypf, nbmfs[CALL_MH], mtypfArg);
            // mi.invokfExbdt(b*):R => difdkExbdtTypf(mi, TYPEOF(b*:R)); mi.invokfBbsid(b*)
        } flsf {
            nbmfs[CHECK_TYPE] = nfw Nbmf(NF_difdkGfnfridTypf, nbmfs[CALL_MH], mtypfArg);
            // mi.invokfGfnfrid(b*):R => difdkGfnfridTypf(mi, TYPEOF(b*:R)).invokfBbsid(b*)
            outArgs[0] = nbmfs[CHECK_TYPE];
        }
        nbmfs[LINKER_CALL] = nfw Nbmf(outCbllTypf, outArgs);
        lform = nfw LbmbdbForm(dfbugNbmf, INARG_LIMIT, nbmfs);
        if (isLinkfr)
            lform.dompilfToBytfdodf();  // JVM nffds b rfbl mftiodOop
        if (isCbdifd)
            lform = mtypf.form().sftCbdifdLbmbdbForm(wiidi, lform);
        rfturn lform;
    }

    /*non-publid*/ stbtid
    WrongMftiodTypfExdfption nfwWrongMftiodTypfExdfption(MftiodTypf bdtubl, MftiodTypf fxpfdtfd) {
        // FIXME: mfrgf witi JVM logid for tirowing WMTE
        rfturn nfw WrongMftiodTypfExdfption("fxpfdtfd "+fxpfdtfd+" but found "+bdtubl);
    }

    /** Stbtid dffinition of MftiodHbndlf.invokfExbdt difdking dodf. */
    /*non-publid*/ stbtid
    @FordfInlinf
    void difdkExbdtTypf(Objfdt miObj, Objfdt fxpfdtfdObj) {
        MftiodHbndlf mi = (MftiodHbndlf) miObj;
        MftiodTypf fxpfdtfd = (MftiodTypf) fxpfdtfdObj;
        MftiodTypf bdtubl = mi.typf();
        if (bdtubl != fxpfdtfd)
            tirow nfwWrongMftiodTypfExdfption(fxpfdtfd, bdtubl);
    }

    /** Stbtid dffinition of MftiodHbndlf.invokfGfnfrid difdking dodf.
     * Dirfdtly rfturns tif typf-bdjustfd MH to invokf, bs follows:
     * {@dodf (R)MH.invokf(b*) => MH.bsTypf(TYPEOF(b*:R)).invokfBbsid(b*)}
     */
    /*non-publid*/ stbtid
    @FordfInlinf
    Objfdt difdkGfnfridTypf(Objfdt miObj, Objfdt fxpfdtfdObj) {
        MftiodHbndlf mi = (MftiodHbndlf) miObj;
        MftiodTypf fxpfdtfd = (MftiodTypf) fxpfdtfdObj;
        if (mi.typf() == fxpfdtfd)  rfturn mi;
        MftiodHbndlf btd = mi.bsTypfCbdif;
        if (btd != null && btd.typf() == fxpfdtfd)  rfturn btd;
        rfturn mi.bsTypf(fxpfdtfd);
        /* Mbybf bdd morf pbtis ifrf.  Possiblf optimizbtions:
         * for (R)MH.invokf(b*),
         * lft MT0 = TYPEOF(b*:R), MT1 = MH.typf
         *
         * if MT0==MT1 or MT1 dbn bf sbffly dbllfd by MT0
         *  => MH.invokfBbsid(b*)
         * if MT1 dbn bf sbffly dbllfd by MT0[R := Objfdt]
         *  => MH.invokfBbsid(b*) & difdkdbst(R)
         * if MT1 dbn bf sbffly dbllfd by MT0[* := Objfdt]
         *  => difdkdbst(A)* & MH.invokfBbsid(b*) & difdkdbst(R)
         * if b big bdbptfr BA dbn bf pullfd out of (MT0,MT1)
         *  => BA.invokfBbsid(MT0,MH,b*)
         * if b lodbl bdbptfr LA dbn dbdifd on stbtid CS0 = nfw GICS(MT0)
         *  => CS0.LA.invokfBbsid(MH,b*)
         * flsf
         *  => MH.bsTypf(MT0).invokfBbsid(A*)
         */
    }

    stbtid MfmbfrNbmf linkToCbllSitfMftiod(MftiodTypf mtypf) {
        LbmbdbForm lform = dbllSitfForm(mtypf, fblsf);
        rfturn lform.vmfntry;
    }

    stbtid MfmbfrNbmf linkToTbrgftMftiod(MftiodTypf mtypf) {
        LbmbdbForm lform = dbllSitfForm(mtypf, truf);
        rfturn lform.vmfntry;
    }

    // skipCbllSitf is truf if wf brf optimizing b ConstbntCbllSitf
    privbtf stbtid LbmbdbForm dbllSitfForm(MftiodTypf mtypf, boolfbn skipCbllSitf) {
        mtypf = mtypf.bbsidTypf();  // normblizf Z to I, String to Objfdt, ftd.
        finbl int wiidi = (skipCbllSitf ? MftiodTypfForm.LF_MH_LINKER : MftiodTypfForm.LF_CS_LINKER);
        LbmbdbForm lform = mtypf.form().dbdifdLbmbdbForm(wiidi);
        if (lform != null)  rfturn lform;
        // fxbdtInvokfrForm (Objfdt,Objfdt)Objfdt
        //   link witi jbvb.lbng.invokf.MftiodHbndlf.invokfBbsid(MftiodHbndlf,Objfdt,Objfdt)Objfdt/invokfSpfdibl
        finbl int ARG_BASE     = 0;
        finbl int OUTARG_LIMIT = ARG_BASE + mtypf.pbrbmftfrCount();
        finbl int INARG_LIMIT  = OUTARG_LIMIT + 1;
        int nbmfCursor = OUTARG_LIMIT;
        finbl int APPENDIX_ARG = nbmfCursor++;  // tif lbst in-brgumfnt
        finbl int CSITE_ARG    = skipCbllSitf ? -1 : APPENDIX_ARG;
        finbl int CALL_MH      = skipCbllSitf ? APPENDIX_ARG : nbmfCursor++;  // rfsult of gftTbrgft
        finbl int LINKER_CALL  = nbmfCursor++;
        MftiodTypf invokfrFormTypf = mtypf.bppfndPbrbmftfrTypfs(skipCbllSitf ? MftiodHbndlf.dlbss : CbllSitf.dlbss);
        Nbmf[] nbmfs = brgumfnts(nbmfCursor - INARG_LIMIT, invokfrFormTypf);
        bssfrt(nbmfs.lfngti == nbmfCursor);
        bssfrt(nbmfs[APPENDIX_ARG] != null);
        if (!skipCbllSitf)
            nbmfs[CALL_MH] = nfw Nbmf(NF_gftCbllSitfTbrgft, nbmfs[CSITE_ARG]);
        // (sitf.)invokfdynbmid(b*):R => mi = sitf.gftTbrgft(); mi.invokfBbsid(b*)
        finbl int PREPEND_MH = 0, PREPEND_COUNT = 1;
        Objfdt[] outArgs = Arrbys.dopyOfRbngf(nbmfs, ARG_BASE, OUTARG_LIMIT + PREPEND_COUNT, Objfdt[].dlbss);
        // prfpfnd MH brgumfnt:
        Systfm.brrbydopy(outArgs, 0, outArgs, PREPEND_COUNT, outArgs.lfngti - PREPEND_COUNT);
        outArgs[PREPEND_MH] = nbmfs[CALL_MH];
        nbmfs[LINKER_CALL] = nfw Nbmf(mtypf, outArgs);
        lform = nfw LbmbdbForm((skipCbllSitf ? "linkToTbrgftMftiod" : "linkToCbllSitf"), INARG_LIMIT, nbmfs);
        lform.dompilfToBytfdodf();  // JVM nffds b rfbl mftiodOop
        lform = mtypf.form().sftCbdifdLbmbdbForm(wiidi, lform);
        rfturn lform;
    }

    /** Stbtid dffinition of MftiodHbndlf.invokfGfnfrid difdking dodf. */
    /*non-publid*/ stbtid
    @FordfInlinf
    Objfdt gftCbllSitfTbrgft(Objfdt sitf) {
        rfturn ((CbllSitf)sitf).gftTbrgft();
    }

    // Lodbl donstbnt fundtions:
    privbtf stbtid finbl NbmfdFundtion NF_difdkExbdtTypf;
    privbtf stbtid finbl NbmfdFundtion NF_difdkGfnfridTypf;
    privbtf stbtid finbl NbmfdFundtion NF_bsTypf;
    privbtf stbtid finbl NbmfdFundtion NF_gftCbllSitfTbrgft;
    stbtid {
        try {
            NF_difdkExbdtTypf = nfw NbmfdFundtion(Invokfrs.dlbss
                    .gftDfdlbrfdMftiod("difdkExbdtTypf", Objfdt.dlbss, Objfdt.dlbss));
            NF_difdkGfnfridTypf = nfw NbmfdFundtion(Invokfrs.dlbss
                    .gftDfdlbrfdMftiod("difdkGfnfridTypf", Objfdt.dlbss, Objfdt.dlbss));
            NF_bsTypf = nfw NbmfdFundtion(MftiodHbndlf.dlbss
                    .gftDfdlbrfdMftiod("bsTypf", MftiodTypf.dlbss));
            NF_gftCbllSitfTbrgft = nfw NbmfdFundtion(Invokfrs.dlbss
                    .gftDfdlbrfdMftiod("gftCbllSitfTbrgft", Objfdt.dlbss));
            NF_difdkExbdtTypf.rfsolvf();
            NF_difdkGfnfridTypf.rfsolvf();
            NF_gftCbllSitfTbrgft.rfsolvf();
            // bound
        } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
            tirow nfwIntfrnblError(fx);
        }
    }

}
