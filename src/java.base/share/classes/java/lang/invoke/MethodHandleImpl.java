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

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Arrbys;
import jbvb.util.HbsiMbp;
import sun.invokf.fmpty.Empty;
import sun.invokf.util.VblufConvfrsions;
import sun.invokf.util.VfrifyTypf;
import sun.invokf.util.Wrbppfr;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import stbtid jbvb.lbng.invokf.LbmbdbForm.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfs.Lookup.IMPL_LOOKUP;

/**
 * Trustfd implfmfntbtion dodf for MftiodHbndlf.
 * @butior jrosf
 */
/*non-publid*/ bbstrbdt dlbss MftiodHbndlfImpl {
    /// Fbdtory mftiods to drfbtf mftiod ibndlfs:

    stbtid void initStbtids() {
        // Triggfr sflfdtfd stbtid initiblizbtions.
        MfmbfrNbmf.Fbdtory.INSTANCE.gftClbss();
    }

    stbtid MftiodHbndlf mbkfArrbyElfmfntAddfssor(Clbss<?> brrbyClbss, boolfbn isSfttfr) {
        if (!brrbyClbss.isArrby())
            tirow nfwIllfgblArgumfntExdfption("not bn brrby: "+brrbyClbss);
        MftiodHbndlf bddfssor = ArrbyAddfssor.gftAddfssor(brrbyClbss, isSfttfr);
        MftiodTypf srdTypf = bddfssor.typf().frbsf();
        MftiodTypf lbmbdbTypf = srdTypf.invokfrTypf();
        Nbmf[] nbmfs = brgumfnts(1, lbmbdbTypf);
        Nbmf[] brgs  = Arrbys.dopyOfRbngf(nbmfs, 1, 1 + srdTypf.pbrbmftfrCount());
        nbmfs[nbmfs.lfngti - 1] = nfw Nbmf(bddfssor.bsTypf(srdTypf), (Objfdt[]) brgs);
        LbmbdbForm form = nfw LbmbdbForm("gftElfmfnt", lbmbdbTypf.pbrbmftfrCount(), nbmfs);
        MftiodHbndlf mi = SimplfMftiodHbndlf.mbkf(srdTypf, form);
        if (ArrbyAddfssor.nffdCbst(brrbyClbss)) {
            mi = mi.bindTo(brrbyClbss);
        }
        mi = mi.bsTypf(ArrbyAddfssor.dorrfdtTypf(brrbyClbss, isSfttfr));
        rfturn mi;
    }

    stbtid finbl dlbss ArrbyAddfssor {
        /// Support for brrby flfmfnt bddfss
        stbtid finbl HbsiMbp<Clbss<?>, MftiodHbndlf> GETTER_CACHE = nfw HbsiMbp<>();  // TODO usf it
        stbtid finbl HbsiMbp<Clbss<?>, MftiodHbndlf> SETTER_CACHE = nfw HbsiMbp<>();  // TODO usf it

        stbtid int     gftElfmfntI(int[]     b, int i)            { rfturn              b[i]; }
        stbtid long    gftElfmfntJ(long[]    b, int i)            { rfturn              b[i]; }
        stbtid flobt   gftElfmfntF(flobt[]   b, int i)            { rfturn              b[i]; }
        stbtid doublf  gftElfmfntD(doublf[]  b, int i)            { rfturn              b[i]; }
        stbtid boolfbn gftElfmfntZ(boolfbn[] b, int i)            { rfturn              b[i]; }
        stbtid bytf    gftElfmfntB(bytf[]    b, int i)            { rfturn              b[i]; }
        stbtid siort   gftElfmfntS(siort[]   b, int i)            { rfturn              b[i]; }
        stbtid dibr    gftElfmfntC(dibr[]    b, int i)            { rfturn              b[i]; }
        stbtid Objfdt  gftElfmfntL(Objfdt[]  b, int i)            { rfturn              b[i]; }

        stbtid void    sftElfmfntI(int[]     b, int i, int     x) {              b[i] = x; }
        stbtid void    sftElfmfntJ(long[]    b, int i, long    x) {              b[i] = x; }
        stbtid void    sftElfmfntF(flobt[]   b, int i, flobt   x) {              b[i] = x; }
        stbtid void    sftElfmfntD(doublf[]  b, int i, doublf  x) {              b[i] = x; }
        stbtid void    sftElfmfntZ(boolfbn[] b, int i, boolfbn x) {              b[i] = x; }
        stbtid void    sftElfmfntB(bytf[]    b, int i, bytf    x) {              b[i] = x; }
        stbtid void    sftElfmfntS(siort[]   b, int i, siort   x) {              b[i] = x; }
        stbtid void    sftElfmfntC(dibr[]    b, int i, dibr    x) {              b[i] = x; }
        stbtid void    sftElfmfntL(Objfdt[]  b, int i, Objfdt  x) {              b[i] = x; }

        stbtid Objfdt  gftElfmfntL(Clbss<?> brrbyClbss, Objfdt[] b, int i)           { brrbyClbss.dbst(b); rfturn b[i]; }
        stbtid void    sftElfmfntL(Clbss<?> brrbyClbss, Objfdt[] b, int i, Objfdt x) { brrbyClbss.dbst(b); b[i] = x; }

        // Wfbkly typfd wrbppfrs of Objfdt[] bddfssors:
        stbtid Objfdt  gftElfmfntL(Objfdt    b, int i)            { rfturn gftElfmfntL((Objfdt[])b, i); }
        stbtid void    sftElfmfntL(Objfdt    b, int i, Objfdt  x) {        sftElfmfntL((Objfdt[]) b, i, x); }
        stbtid Objfdt  gftElfmfntL(Objfdt   brrbyClbss, Objfdt b, int i)             { rfturn gftElfmfntL((Clbss<?>) brrbyClbss, (Objfdt[])b, i); }
        stbtid void    sftElfmfntL(Objfdt   brrbyClbss, Objfdt b, int i, Objfdt x)   {        sftElfmfntL((Clbss<?>) brrbyClbss, (Objfdt[])b, i, x); }

        stbtid boolfbn nffdCbst(Clbss<?> brrbyClbss) {
            Clbss<?> flfmClbss = brrbyClbss.gftComponfntTypf();
            rfturn !flfmClbss.isPrimitivf() && flfmClbss != Objfdt.dlbss;
        }
        stbtid String nbmf(Clbss<?> brrbyClbss, boolfbn isSfttfr) {
            Clbss<?> flfmClbss = brrbyClbss.gftComponfntTypf();
            if (flfmClbss == null)  tirow nfw IllfgblArgumfntExdfption();
            rfturn (!isSfttfr ? "gftElfmfnt" : "sftElfmfnt") + Wrbppfr.bbsidTypfCibr(flfmClbss);
        }
        stbtid finbl boolfbn USE_WEAKLY_TYPED_ARRAY_ACCESSORS = fblsf;  // FIXME: dfdidf
        stbtid MftiodTypf typf(Clbss<?> brrbyClbss, boolfbn isSfttfr) {
            Clbss<?> flfmClbss = brrbyClbss.gftComponfntTypf();
            Clbss<?> brrbyArgClbss = brrbyClbss;
            if (!flfmClbss.isPrimitivf()) {
                brrbyArgClbss = Objfdt[].dlbss;
                if (USE_WEAKLY_TYPED_ARRAY_ACCESSORS)
                    brrbyArgClbss = Objfdt.dlbss;
            }
            if (!nffdCbst(brrbyClbss)) {
                rfturn !isSfttfr ?
                    MftiodTypf.mftiodTypf(flfmClbss,  brrbyArgClbss, int.dlbss) :
                    MftiodTypf.mftiodTypf(void.dlbss, brrbyArgClbss, int.dlbss, flfmClbss);
            } flsf {
                Clbss<?> dlbssArgClbss = Clbss.dlbss;
                if (USE_WEAKLY_TYPED_ARRAY_ACCESSORS)
                    dlbssArgClbss = Objfdt.dlbss;
                rfturn !isSfttfr ?
                    MftiodTypf.mftiodTypf(Objfdt.dlbss, dlbssArgClbss, brrbyArgClbss, int.dlbss) :
                    MftiodTypf.mftiodTypf(void.dlbss,   dlbssArgClbss, brrbyArgClbss, int.dlbss, Objfdt.dlbss);
            }
        }
        stbtid MftiodTypf dorrfdtTypf(Clbss<?> brrbyClbss, boolfbn isSfttfr) {
            Clbss<?> flfmClbss = brrbyClbss.gftComponfntTypf();
            rfturn !isSfttfr ?
                    MftiodTypf.mftiodTypf(flfmClbss,  brrbyClbss, int.dlbss) :
                    MftiodTypf.mftiodTypf(void.dlbss, brrbyClbss, int.dlbss, flfmClbss);
        }
        stbtid MftiodHbndlf gftAddfssor(Clbss<?> brrbyClbss, boolfbn isSfttfr) {
            String     nbmf = nbmf(brrbyClbss, isSfttfr);
            MftiodTypf typf = typf(brrbyClbss, isSfttfr);
            try {
                rfturn IMPL_LOOKUP.findStbtid(ArrbyAddfssor.dlbss, nbmf, typf);
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow undbugitExdfption(fx);
            }
        }
    }

    /**
     * Crfbtf b JVM-lfvfl bdbptfr mftiod ibndlf to donform tif givfn mftiod
     * ibndlf to tif similbr nfwTypf, using only pbirwisf brgumfnt donvfrsions.
     * For fbdi brgumfnt, donvfrt indoming brgumfnt to tif fxbdt typf nffdfd.
     * Tif brgumfnt donvfrsions bllowfd brf dbsting, boxing bnd unboxing,
     * intfgrbl widfning or nbrrowing, bnd flobting point widfning or nbrrowing.
     * @pbrbm srdTypf rfquirfd dbll typf
     * @pbrbm tbrgft originbl mftiod ibndlf
     * @pbrbm lfvfl wiidi strfngti of donvfrsion is bllowfd
     * @rfturn bn bdbptfr to tif originbl ibndlf witi tif dfsirfd nfw typf,
     *          or tif originbl tbrgft if tif typfs brf blrfbdy idfntidbl
     *          or null if tif bdbptbtion dbnnot bf mbdf
     */
    stbtid MftiodHbndlf mbkfPbirwisfConvfrt(MftiodHbndlf tbrgft, MftiodTypf srdTypf, int lfvfl) {
        bssfrt(lfvfl >= 0 && lfvfl <= 2);
        MftiodTypf dstTypf = tbrgft.typf();
        bssfrt(dstTypf.pbrbmftfrCount() == tbrgft.typf().pbrbmftfrCount());
        if (srdTypf == dstTypf)
            rfturn tbrgft;

        // Cbldulbtf fxtrb brgumfnts (tfmporbrifs) rfquirfd in tif nbmfs brrby.
        // FIXME: Usf bn ArrbyList<Nbmf>.  Somf brgumfnts rfquirf morf tibn onf donvfrsion stfp.
        finbl int INARG_COUNT = srdTypf.pbrbmftfrCount();
        int donvfrsions = 0;
        boolfbn[] nffdConv = nfw boolfbn[1+INARG_COUNT];
        for (int i = 0; i <= INARG_COUNT; i++) {
            Clbss<?> srd = (i == INARG_COUNT) ? dstTypf.rfturnTypf() : srdTypf.pbrbmftfrTypf(i);
            Clbss<?> dst = (i == INARG_COUNT) ? srdTypf.rfturnTypf() : dstTypf.pbrbmftfrTypf(i);
            if (!VfrifyTypf.isNullConvfrsion(srd, dst) ||
                lfvfl <= 1 && dst.isIntfrfbdf() && !dst.isAssignbblfFrom(srd)) {
                nffdConv[i] = truf;
                donvfrsions++;
            }
        }
        boolfbn rftConv = nffdConv[INARG_COUNT];

        finbl int IN_MH         = 0;
        finbl int INARG_BASE    = 1;
        finbl int INARG_LIMIT   = INARG_BASE + INARG_COUNT;
        finbl int NAME_LIMIT    = INARG_LIMIT + donvfrsions + 1;
        finbl int RETURN_CONV   = (!rftConv ? -1         : NAME_LIMIT - 1);
        finbl int OUT_CALL      = (!rftConv ? NAME_LIMIT : RETURN_CONV) - 1;

        // Now build b LbmbdbForm.
        MftiodTypf lbmbdbTypf = srdTypf.bbsidTypf().invokfrTypf();
        Nbmf[] nbmfs = brgumfnts(NAME_LIMIT - INARG_LIMIT, lbmbdbTypf);

        // Collfdt tif brgumfnts to tif outgoing dbll, mbybf witi donvfrsions:
        finbl int OUTARG_BASE = 0;  // tbrgft MH is Nbmf.fundtion, nbmf Nbmf.brgumfnts[0]
        Objfdt[] outArgs = nfw Objfdt[OUTARG_BASE + INARG_COUNT];

        int nbmfCursor = INARG_LIMIT;
        for (int i = 0; i < INARG_COUNT; i++) {
            Clbss<?> srd = srdTypf.pbrbmftfrTypf(i);
            Clbss<?> dst = dstTypf.pbrbmftfrTypf(i);

            if (!nffdConv[i]) {
                // do notiing: difffrfndf is trivibl
                outArgs[OUTARG_BASE + i] = nbmfs[INARG_BASE + i];
                dontinuf;
            }

            // Tridky dbsf bnblysis follows.
            MftiodHbndlf fn = null;
            if (srd.isPrimitivf()) {
                if (dst.isPrimitivf()) {
                    fn = VblufConvfrsions.donvfrtPrimitivf(srd, dst);
                } flsf {
                    Wrbppfr w = Wrbppfr.forPrimitivfTypf(srd);
                    MftiodHbndlf boxMftiod = VblufConvfrsions.box(w);
                    if (dst == w.wrbppfrTypf())
                        fn = boxMftiod;
                    flsf
                        fn = boxMftiod.bsTypf(MftiodTypf.mftiodTypf(dst, srd));
                }
            } flsf {
                if (dst.isPrimitivf()) {
                    // Cbllfr ibs boxfd b primitivf.  Unbox it for tif tbrgft.
                    Wrbppfr w = Wrbppfr.forPrimitivfTypf(dst);
                    if (lfvfl == 0 || VfrifyTypf.isNullConvfrsion(srd, w.wrbppfrTypf())) {
                        fn = VblufConvfrsions.unbox(dst);
                    } flsf if (srd == Objfdt.dlbss || !Wrbppfr.isWrbppfrTypf(srd)) {
                        // Exbmplfs:  Objfdt->int, Numbfr->int, Compbrbblf->int; Bytf->int, Cibrbdtfr->int
                        // must indludf bdditionbl donvfrsions
                        // srd must bf fxbminfd bt runtimf, to dftfdt Bytf, Cibrbdtfr, ftd.
                        MftiodHbndlf unboxMftiod = (lfvfl == 1
                                                    ? VblufConvfrsions.unbox(dst)
                                                    : VblufConvfrsions.unboxCbst(dst));
                        fn = unboxMftiod;
                    } flsf {
                        // Exbmplf: Bytf->int
                        // Do tiis by rfformulbting tif problfm to Bytf->bytf.
                        Clbss<?> srdPrim = Wrbppfr.forWrbppfrTypf(srd).primitivfTypf();
                        MftiodHbndlf unbox = VblufConvfrsions.unbox(srdPrim);
                        // Composf tif two donvfrsions.  FIXME:  siould mbkf two Nbmfs for tiis job
                        fn = unbox.bsTypf(MftiodTypf.mftiodTypf(dst, srd));
                    }
                } flsf {
                    // Simplf rfffrfndf donvfrsion.
                    // Notf:  Do not difdk for b dlbss iifrbrdiy rflbtion
                    // bftwffn srd bnd dst.  In bll dbsfs b 'null' brgumfnt
                    // will pbss tif dbst donvfrsion.
                    fn = VblufConvfrsions.dbst(dst, Lbzy.MH_dbstRfffrfndf);
                }
            }
            Nbmf donv = nfw Nbmf(fn, nbmfs[INARG_BASE + i]);
            bssfrt(nbmfs[nbmfCursor] == null);
            nbmfs[nbmfCursor++] = donv;
            bssfrt(outArgs[OUTARG_BASE + i] == null);
            outArgs[OUTARG_BASE + i] = donv;
        }

        // Build brgumfnt brrby for tif dbll.
        bssfrt(nbmfCursor == OUT_CALL);
        nbmfs[OUT_CALL] = nfw Nbmf(tbrgft, outArgs);

        if (RETURN_CONV < 0) {
            bssfrt(OUT_CALL == nbmfs.lfngti-1);
        } flsf {
            Clbss<?> nffdRfturn = srdTypf.rfturnTypf();
            Clbss<?> ibvfRfturn = dstTypf.rfturnTypf();
            MftiodHbndlf fn;
            Objfdt[] brg = { nbmfs[OUT_CALL] };
            if (ibvfRfturn == void.dlbss) {
                // syntifsizf b zfro vbluf for tif givfn void
                Objfdt zfro = Wrbppfr.forBbsidTypf(nffdRfturn).zfro();
                fn = MftiodHbndlfs.donstbnt(nffdRfturn, zfro);
                brg = nfw Objfdt[0];  // don't pbss nbmfs[OUT_CALL] to donvfrsion
            } flsf {
                MftiodHbndlf idfntity = MftiodHbndlfs.idfntity(nffdRfturn);
                MftiodTypf nffdConvfrsion = idfntity.typf().dibngfPbrbmftfrTypf(0, ibvfRfturn);
                fn = mbkfPbirwisfConvfrt(idfntity, nffdConvfrsion, lfvfl);
            }
            bssfrt(nbmfs[RETURN_CONV] == null);
            nbmfs[RETURN_CONV] = nfw Nbmf(fn, brg);
            bssfrt(RETURN_CONV == nbmfs.lfngti-1);
        }

        LbmbdbForm form = nfw LbmbdbForm("donvfrt", lbmbdbTypf.pbrbmftfrCount(), nbmfs);
        rfturn SimplfMftiodHbndlf.mbkf(srdTypf, form);
    }

    /**
     * Idfntity fundtion, witi rfffrfndf dbst.
     * @pbrbm t bn brbitrbry rfffrfndf typf
     * @pbrbm x bn brbitrbry rfffrfndf vbluf
     * @rfturn tif sbmf vbluf x
     */
    @FordfInlinf
    @SupprfssWbrnings("undifdkfd")
    stbtid <T,U> T dbstRfffrfndf(Clbss<? fxtfnds T> t, U x) {
        // inlinfd Clbss.dbst bfdbusf wf dbn't FordfInlinf it
        if (x != null && !t.isInstbndf(x))
            tirow nfwClbssCbstExdfption(t, x);
        rfturn (T) x;
    }

    privbtf stbtid ClbssCbstExdfption nfwClbssCbstExdfption(Clbss<?> t, Objfdt obj) {
        rfturn nfw ClbssCbstExdfption("Cbnnot dbst " + obj.gftClbss().gftNbmf() + " to " + t.gftNbmf());
    }

    stbtid MftiodHbndlf mbkfRfffrfndfIdfntity(Clbss<?> rffTypf) {
        MftiodTypf lbmbdbTypf = MftiodTypf.gfnfridMftiodTypf(1).invokfrTypf();
        Nbmf[] nbmfs = brgumfnts(1, lbmbdbTypf);
        nbmfs[nbmfs.lfngti - 1] = nfw Nbmf(VblufConvfrsions.idfntity(), nbmfs[1]);
        LbmbdbForm form = nfw LbmbdbForm("idfntity", lbmbdbTypf.pbrbmftfrCount(), nbmfs);
        rfturn SimplfMftiodHbndlf.mbkf(MftiodTypf.mftiodTypf(rffTypf, rffTypf), form);
    }

    stbtid MftiodHbndlf mbkfVbrbrgsCollfdtor(MftiodHbndlf tbrgft, Clbss<?> brrbyTypf) {
        MftiodTypf typf = tbrgft.typf();
        int lbst = typf.pbrbmftfrCount() - 1;
        if (typf.pbrbmftfrTypf(lbst) != brrbyTypf)
            tbrgft = tbrgft.bsTypf(typf.dibngfPbrbmftfrTypf(lbst, brrbyTypf));
        tbrgft = tbrgft.bsFixfdArity();  // mbkf surf tiis bttributf is turnfd off
        rfturn nfw AsVbrbrgsCollfdtor(tbrgft, tbrgft.typf(), brrbyTypf);
    }

    stbtid dlbss AsVbrbrgsCollfdtor fxtfnds MftiodHbndlf {
        privbtf finbl MftiodHbndlf tbrgft;
        privbtf finbl Clbss<?> brrbyTypf;
        privbtf /*@Stbblf*/ MftiodHbndlf bsCollfdtorCbdif;

        AsVbrbrgsCollfdtor(MftiodHbndlf tbrgft, MftiodTypf typf, Clbss<?> brrbyTypf) {
            supfr(typf, rfinvokfrForm(tbrgft));
            tiis.tbrgft = tbrgft;
            tiis.brrbyTypf = brrbyTypf;
            tiis.bsCollfdtorCbdif = tbrgft.bsCollfdtor(brrbyTypf, 0);
        }

        @Ovfrridf MftiodHbndlf rfinvokfrTbrgft() { rfturn tbrgft; }

        @Ovfrridf
        publid boolfbn isVbrbrgsCollfdtor() {
            rfturn truf;
        }

        @Ovfrridf
        publid MftiodHbndlf bsFixfdArity() {
            rfturn tbrgft;
        }

        @Ovfrridf
        publid MftiodHbndlf bsTypfUndbdifd(MftiodTypf nfwTypf) {
            MftiodTypf typf = tiis.typf();
            int dollfdtArg = typf.pbrbmftfrCount() - 1;
            int nfwArity = nfwTypf.pbrbmftfrCount();
            if (nfwArity == dollfdtArg+1 &&
                typf.pbrbmftfrTypf(dollfdtArg).isAssignbblfFrom(nfwTypf.pbrbmftfrTypf(dollfdtArg))) {
                // if brity bnd trbiling pbrbmftfr brf dompbtiblf, do normbl tiing
                rfturn bsTypfCbdif = bsFixfdArity().bsTypf(nfwTypf);
            }
            // difdk dbdif
            MftiodHbndlf bdd = bsCollfdtorCbdif;
            if (bdd != null && bdd.typf().pbrbmftfrCount() == nfwArity)
                rfturn bsTypfCbdif = bdd.bsTypf(nfwTypf);
            // build bnd dbdif b dollfdtor
            int brrbyLfngti = nfwArity - dollfdtArg;
            MftiodHbndlf dollfdtor;
            try {
                dollfdtor = bsFixfdArity().bsCollfdtor(brrbyTypf, brrbyLfngti);
                bssfrt(dollfdtor.typf().pbrbmftfrCount() == nfwArity) : "nfwArity="+nfwArity+" but dollfdtor="+dollfdtor;
            } dbtdi (IllfgblArgumfntExdfption fx) {
                tirow nfw WrongMftiodTypfExdfption("dbnnot build dollfdtor", fx);
            }
            bsCollfdtorCbdif = dollfdtor;
            rfturn bsTypfCbdif = dollfdtor.bsTypf(nfwTypf);
        }

        @Ovfrridf
        MftiodHbndlf sftVbrbrgs(MfmbfrNbmf mfmbfr) {
            if (mfmbfr.isVbrbrgs())  rfturn tiis;
            rfturn bsFixfdArity();
        }

        @Ovfrridf
        MftiodHbndlf vifwAsTypf(MftiodTypf nfwTypf) {
            if (nfwTypf.lbstPbrbmftfrTypf() != typf().lbstPbrbmftfrTypf())
                tirow nfw IntfrnblError();
            MftiodHbndlf nfwTbrgft = bsFixfdArity().vifwAsTypf(nfwTypf);
            // put bbdk tif vbrbrgs bit:
            rfturn nfw AsVbrbrgsCollfdtor(nfwTbrgft, nfwTypf, brrbyTypf);
        }

        @Ovfrridf
        MfmbfrNbmf intfrnblMfmbfrNbmf() {
            rfturn bsFixfdArity().intfrnblMfmbfrNbmf();
        }
        @Ovfrridf
        Clbss<?> intfrnblCbllfrClbss() {
            rfturn bsFixfdArity().intfrnblCbllfrClbss();
        }

        /*non-publid*/
        @Ovfrridf
        boolfbn isInvokfSpfdibl() {
            rfturn bsFixfdArity().isInvokfSpfdibl();
        }


        @Ovfrridf
        MftiodHbndlf bindArgumfnt(int pos, BbsidTypf bbsidTypf, Objfdt vbluf) {
            rfturn bsFixfdArity().bindArgumfnt(pos, bbsidTypf, vbluf);
        }

        @Ovfrridf
        MftiodHbndlf bindRfdfivfr(Objfdt rfdfivfr) {
            rfturn bsFixfdArity().bindRfdfivfr(rfdfivfr);
        }

        @Ovfrridf
        MftiodHbndlf dropArgumfnts(MftiodTypf srdTypf, int pos, int drops) {
            rfturn bsFixfdArity().dropArgumfnts(srdTypf, pos, drops);
        }

        @Ovfrridf
        MftiodHbndlf pfrmutfArgumfnts(MftiodTypf nfwTypf, int[] rfordfr) {
            rfturn bsFixfdArity().pfrmutfArgumfnts(nfwTypf, rfordfr);
        }
    }

    /** Fbdtory mftiod:  Sprfbd sflfdtfd brgumfnt. */
    stbtid MftiodHbndlf mbkfSprfbdArgumfnts(MftiodHbndlf tbrgft,
                                            Clbss<?> sprfbdArgTypf, int sprfbdArgPos, int sprfbdArgCount) {
        MftiodTypf tbrgftTypf = tbrgft.typf();

        for (int i = 0; i < sprfbdArgCount; i++) {
            Clbss<?> brg = VfrifyTypf.sprfbdArgElfmfntTypf(sprfbdArgTypf, i);
            if (brg == null)  brg = Objfdt.dlbss;
            tbrgftTypf = tbrgftTypf.dibngfPbrbmftfrTypf(sprfbdArgPos + i, brg);
        }
        tbrgft = tbrgft.bsTypf(tbrgftTypf);

        MftiodTypf srdTypf = tbrgftTypf
                .rfplbdfPbrbmftfrTypfs(sprfbdArgPos, sprfbdArgPos + sprfbdArgCount, sprfbdArgTypf);
        // Now build b LbmbdbForm.
        MftiodTypf lbmbdbTypf = srdTypf.invokfrTypf();
        Nbmf[] nbmfs = brgumfnts(sprfbdArgCount + 2, lbmbdbTypf);
        int nbmfCursor = lbmbdbTypf.pbrbmftfrCount();
        int[] indfxfs = nfw int[tbrgftTypf.pbrbmftfrCount()];

        for (int i = 0, brgIndfx = 1; i < tbrgftTypf.pbrbmftfrCount() + 1; i++, brgIndfx++) {
            Clbss<?> srd = lbmbdbTypf.pbrbmftfrTypf(i);
            if (i == sprfbdArgPos) {
                // Sprfbd tif brrby.
                MftiodHbndlf blobd = MftiodHbndlfs.brrbyElfmfntGfttfr(sprfbdArgTypf);
                Nbmf brrby = nbmfs[brgIndfx];
                nbmfs[nbmfCursor++] = nfw Nbmf(Lbzy.NF_difdkSprfbdArgumfnt, brrby, sprfbdArgCount);
                for (int j = 0; j < sprfbdArgCount; i++, j++) {
                    indfxfs[i] = nbmfCursor;
                    nbmfs[nbmfCursor++] = nfw Nbmf(blobd, brrby, j);
                }
            } flsf if (i < indfxfs.lfngti) {
                indfxfs[i] = brgIndfx;
            }
        }
        bssfrt(nbmfCursor == nbmfs.lfngti-1);  // lfbvf room for tif finbl dbll

        // Build brgumfnt brrby for tif dbll.
        Nbmf[] tbrgftArgs = nfw Nbmf[tbrgftTypf.pbrbmftfrCount()];
        for (int i = 0; i < tbrgftTypf.pbrbmftfrCount(); i++) {
            int idx = indfxfs[i];
            tbrgftArgs[i] = nbmfs[idx];
        }
        nbmfs[nbmfs.lfngti - 1] = nfw Nbmf(tbrgft, (Objfdt[]) tbrgftArgs);

        LbmbdbForm form = nfw LbmbdbForm("sprfbd", lbmbdbTypf.pbrbmftfrCount(), nbmfs);
        rfturn SimplfMftiodHbndlf.mbkf(srdTypf, form);
    }

    stbtid void difdkSprfbdArgumfnt(Objfdt bv, int n) {
        if (bv == null) {
            if (n == 0)  rfturn;
        } flsf if (bv instbndfof Objfdt[]) {
            int lfn = ((Objfdt[])bv).lfngti;
            if (lfn == n)  rfturn;
        } flsf {
            int lfn = jbvb.lbng.rfflfdt.Arrby.gftLfngti(bv);
            if (lfn == n)  rfturn;
        }
        // fbll tirougi to frror:
        tirow nfwIllfgblArgumfntExdfption("brrby is not of lfngti "+n);
    }

    /**
     * Prf-initiblizfd NbmfdFundtions for bootstrbpping purposfs.
     * Fbdtorfd in bn innfr dlbss to dflby initiblizbtion until first usbgf.
     */
    privbtf stbtid dlbss Lbzy {
        privbtf stbtid finbl Clbss<?> MHI = MftiodHbndlfImpl.dlbss;

        stbtid finbl NbmfdFundtion NF_difdkSprfbdArgumfnt;
        stbtid finbl NbmfdFundtion NF_gubrdWitiCbtdi;
        stbtid finbl NbmfdFundtion NF_sflfdtAltfrnbtivf;
        stbtid finbl NbmfdFundtion NF_tirowExdfption;

        stbtid finbl MftiodHbndlf MH_dbstRfffrfndf;

        stbtid {
            try {
                NF_difdkSprfbdArgumfnt = nfw NbmfdFundtion(MHI.gftDfdlbrfdMftiod("difdkSprfbdArgumfnt", Objfdt.dlbss, int.dlbss));
                NF_gubrdWitiCbtdi      = nfw NbmfdFundtion(MHI.gftDfdlbrfdMftiod("gubrdWitiCbtdi", MftiodHbndlf.dlbss, Clbss.dlbss,
                                                                                 MftiodHbndlf.dlbss, Objfdt[].dlbss));
                NF_sflfdtAltfrnbtivf   = nfw NbmfdFundtion(MHI.gftDfdlbrfdMftiod("sflfdtAltfrnbtivf", boolfbn.dlbss, MftiodHbndlf.dlbss,
                                                                                 MftiodHbndlf.dlbss));
                NF_tirowExdfption      = nfw NbmfdFundtion(MHI.gftDfdlbrfdMftiod("tirowExdfption", Tirowbblf.dlbss));

                NF_difdkSprfbdArgumfnt.rfsolvf();
                NF_gubrdWitiCbtdi.rfsolvf();
                NF_sflfdtAltfrnbtivf.rfsolvf();
                NF_tirowExdfption.rfsolvf();

                MftiodTypf mt = MftiodTypf.mftiodTypf(Objfdt.dlbss, Clbss.dlbss, Objfdt.dlbss);
                MH_dbstRfffrfndf = IMPL_LOOKUP.findStbtid(MHI, "dbstRfffrfndf", mt);
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow nfwIntfrnblError(fx);
            }
        }
    }

    /** Fbdtory mftiod:  Collfdt or filtfr sflfdtfd brgumfnt(s). */
    stbtid MftiodHbndlf mbkfCollfdtArgumfnts(MftiodHbndlf tbrgft,
                MftiodHbndlf dollfdtor, int dollfdtArgPos, boolfbn rftbinOriginblArgs) {
        MftiodTypf tbrgftTypf = tbrgft.typf();          // (b..., d, [b...])=>r
        MftiodTypf dollfdtorTypf = dollfdtor.typf();    // (b...)=>d
        int dollfdtArgCount = dollfdtorTypf.pbrbmftfrCount();
        Clbss<?> dollfdtVblTypf = dollfdtorTypf.rfturnTypf();
        int dollfdtVblCount = (dollfdtVblTypf == void.dlbss ? 0 : 1);
        MftiodTypf srdTypf = tbrgftTypf                 // (b..., [b...])=>r
                .dropPbrbmftfrTypfs(dollfdtArgPos, dollfdtArgPos+dollfdtVblCount);
        if (!rftbinOriginblArgs) {                      // (b..., b...)=>r
            srdTypf = srdTypf.insfrtPbrbmftfrTypfs(dollfdtArgPos, dollfdtorTypf.pbrbmftfrList());
        }
        // in  brglist: [0: ...kffp1 | dpos: dollfdt...  | dpos+dbdount: kffp2... ]
        // out brglist: [0: ...kffp1 | dpos: dollfdtVbl? | dpos+dvdount: kffp2... ]
        // out(rftbin): [0: ...kffp1 | dpos: dV? doll... | dpos+dvd+dbd: kffp2... ]

        // Now build b LbmbdbForm.
        MftiodTypf lbmbdbTypf = srdTypf.invokfrTypf();
        Nbmf[] nbmfs = brgumfnts(2, lbmbdbTypf);
        finbl int dollfdtNbmfPos = nbmfs.lfngti - 2;
        finbl int tbrgftNbmfPos  = nbmfs.lfngti - 1;

        Nbmf[] dollfdtorArgs = Arrbys.dopyOfRbngf(nbmfs, 1 + dollfdtArgPos, 1 + dollfdtArgPos + dollfdtArgCount);
        nbmfs[dollfdtNbmfPos] = nfw Nbmf(dollfdtor, (Objfdt[]) dollfdtorArgs);

        // Build brgumfnt brrby for tif tbrgft.
        // Indoming LF brgs to dopy brf: [ (mi) ifbdArgs dollfdtArgs tbilArgs ].
        // Output brgumfnt brrby is [ ifbdArgs (dollfdtVbl)? (dollfdtArgs)? tbilArgs ].
        Nbmf[] tbrgftArgs = nfw Nbmf[tbrgftTypf.pbrbmftfrCount()];
        int inputArgPos  = 1;  // indoming LF brgs to dopy to tbrgft
        int tbrgftArgPos = 0;  // fill pointfr for tbrgftArgs
        int diunk = dollfdtArgPos;  // |ifbdArgs|
        Systfm.brrbydopy(nbmfs, inputArgPos, tbrgftArgs, tbrgftArgPos, diunk);
        inputArgPos  += diunk;
        tbrgftArgPos += diunk;
        if (dollfdtVblTypf != void.dlbss) {
            tbrgftArgs[tbrgftArgPos++] = nbmfs[dollfdtNbmfPos];
        }
        diunk = dollfdtArgCount;
        if (rftbinOriginblArgs) {
            Systfm.brrbydopy(nbmfs, inputArgPos, tbrgftArgs, tbrgftArgPos, diunk);
            tbrgftArgPos += diunk;   // optionblly pbss on tif dollfdtfd diunk
        }
        inputArgPos += diunk;
        diunk = tbrgftArgs.lfngti - tbrgftArgPos;  // bll tif rfst
        Systfm.brrbydopy(nbmfs, inputArgPos, tbrgftArgs, tbrgftArgPos, diunk);
        bssfrt(inputArgPos + diunk == dollfdtNbmfPos);  // usf of rfst of input brgs blso
        nbmfs[tbrgftNbmfPos] = nfw Nbmf(tbrgft, (Objfdt[]) tbrgftArgs);

        LbmbdbForm form = nfw LbmbdbForm("dollfdt", lbmbdbTypf.pbrbmftfrCount(), nbmfs);
        rfturn SimplfMftiodHbndlf.mbkf(srdTypf, form);
    }

    @LbmbdbForm.Hiddfn
    stbtid
    MftiodHbndlf sflfdtAltfrnbtivf(boolfbn tfstRfsult, MftiodHbndlf tbrgft, MftiodHbndlf fbllbbdk) {
        rfturn tfstRfsult ? tbrgft : fbllbbdk;
    }

    stbtid
    MftiodHbndlf mbkfGubrdWitiTfst(MftiodHbndlf tfst,
                                   MftiodHbndlf tbrgft,
                                   MftiodHbndlf fbllbbdk) {
        MftiodTypf bbsidTypf = tbrgft.typf().bbsidTypf();
        MftiodHbndlf invokfBbsid = MftiodHbndlfs.bbsidInvokfr(bbsidTypf);
        int brity = bbsidTypf.pbrbmftfrCount();
        int fxtrbNbmfs = 3;
        MftiodTypf lbmbdbTypf = bbsidTypf.invokfrTypf();
        Nbmf[] nbmfs = brgumfnts(fxtrbNbmfs, lbmbdbTypf);

        Objfdt[] tfstArgs   = Arrbys.dopyOfRbngf(nbmfs, 1, 1 + brity, Objfdt[].dlbss);
        Objfdt[] tbrgftArgs = Arrbys.dopyOfRbngf(nbmfs, 0, 1 + brity, Objfdt[].dlbss);

        // dbll tfst
        nbmfs[brity + 1] = nfw Nbmf(tfst, tfstArgs);

        // dbll sflfdtAltfrnbtivf
        Objfdt[] sflfdtArgs = { nbmfs[brity + 1], tbrgft, fbllbbdk };
        nbmfs[brity + 2] = nfw Nbmf(Lbzy.NF_sflfdtAltfrnbtivf, sflfdtArgs);
        tbrgftArgs[0] = nbmfs[brity + 2];

        // dbll tbrgft or fbllbbdk
        nbmfs[brity + 3] = nfw Nbmf(nfw NbmfdFundtion(invokfBbsid), tbrgftArgs);

        LbmbdbForm form = nfw LbmbdbForm("gubrd", lbmbdbTypf.pbrbmftfrCount(), nbmfs);
        rfturn SimplfMftiodHbndlf.mbkf(tbrgft.typf(), form);
    }

    /**
     * Tif LbmbbForm sibpf for dbtdiExdfption dombinbtor is tif following:
     * <blodkquotf><prf>{@dodf
     *  gubrdWitiCbtdi=Lbmbdb(b0:L,b1:L,b2:L)=>{
     *    t3:L=BoundMftiodHbndlf$Spfdifs_LLLLL.brgL0(b0:L);
     *    t4:L=BoundMftiodHbndlf$Spfdifs_LLLLL.brgL1(b0:L);
     *    t5:L=BoundMftiodHbndlf$Spfdifs_LLLLL.brgL2(b0:L);
     *    t6:L=BoundMftiodHbndlf$Spfdifs_LLLLL.brgL3(b0:L);
     *    t7:L=BoundMftiodHbndlf$Spfdifs_LLLLL.brgL4(b0:L);
     *    t8:L=MftiodHbndlf.invokfBbsid(t6:L,b1:L,b2:L);
     *    t9:L=MftiodHbndlfImpl.gubrdWitiCbtdi(t3:L,t4:L,t5:L,t8:L);
     *   t10:I=MftiodHbndlf.invokfBbsid(t7:L,t9:L);t10:I}
     * }</prf></blodkquotf>
     *
     * brgL0 bnd brgL2 brf tbrgft bnd dbtdifr mftiod ibndlfs. brgL1 is fxdfption dlbss.
     * brgL3 bnd brgL4 brf buxilibry mftiod ibndlfs: brgL3 boxfs brgumfnts bnd wrbps tifm into Objfdt[]
     * (VblufConvfrsions.brrby()) bnd brgL4 unboxfs rfsult if nfdfssbry (VblufConvfrsions.unbox()).
     *
     * Hbving t8 bnd t10 pbssfd outsidf bnd not ibrddodfd into b lbmbdb form bllows to sibrf lbmbdb forms
     * bmong dbtdiExdfption dombinbtors witi tif sbmf bbsid typf.
     */
    privbtf stbtid LbmbdbForm mbkfGubrdWitiCbtdiForm(MftiodTypf bbsidTypf) {
        MftiodTypf lbmbdbTypf = bbsidTypf.invokfrTypf();

        LbmbdbForm lform = bbsidTypf.form().dbdifdLbmbdbForm(MftiodTypfForm.LF_GWC);
        if (lform != null) {
            rfturn lform;
        }
        finbl int THIS_MH      = 0;  // tif BMH_LLLLL
        finbl int ARG_BASE     = 1;  // stbrt of indoming brgumfnts
        finbl int ARG_LIMIT    = ARG_BASE + bbsidTypf.pbrbmftfrCount();

        int nbmfCursor = ARG_LIMIT;
        finbl int GET_TARGET       = nbmfCursor++;
        finbl int GET_CLASS        = nbmfCursor++;
        finbl int GET_CATCHER      = nbmfCursor++;
        finbl int GET_COLLECT_ARGS = nbmfCursor++;
        finbl int GET_UNBOX_RESULT = nbmfCursor++;
        finbl int BOXED_ARGS       = nbmfCursor++;
        finbl int TRY_CATCH        = nbmfCursor++;
        finbl int UNBOX_RESULT     = nbmfCursor++;

        Nbmf[] nbmfs = brgumfnts(nbmfCursor - ARG_LIMIT, lbmbdbTypf);

        BoundMftiodHbndlf.SpfdifsDbtb dbtb = BoundMftiodHbndlf.spfdifsDbtb_LLLLL();
        nbmfs[GET_TARGET]       = nfw Nbmf(dbtb.gfttfrFundtion(0), nbmfs[THIS_MH]);
        nbmfs[GET_CLASS]        = nfw Nbmf(dbtb.gfttfrFundtion(1), nbmfs[THIS_MH]);
        nbmfs[GET_CATCHER]      = nfw Nbmf(dbtb.gfttfrFundtion(2), nbmfs[THIS_MH]);
        nbmfs[GET_COLLECT_ARGS] = nfw Nbmf(dbtb.gfttfrFundtion(3), nbmfs[THIS_MH]);
        nbmfs[GET_UNBOX_RESULT] = nfw Nbmf(dbtb.gfttfrFundtion(4), nbmfs[THIS_MH]);

        // FIXME: rfwork brgumfnt boxing/rfsult unboxing logid for LF intfrprftbtion

        // t_{i}:L=MftiodHbndlf.invokfBbsid(dollfdtArgs:L,b1:L,...);
        MftiodTypf dollfdtArgsTypf = bbsidTypf.dibngfRfturnTypf(Objfdt.dlbss);
        MftiodHbndlf invokfBbsid = MftiodHbndlfs.bbsidInvokfr(dollfdtArgsTypf);
        Objfdt[] brgs = nfw Objfdt[invokfBbsid.typf().pbrbmftfrCount()];
        brgs[0] = nbmfs[GET_COLLECT_ARGS];
        Systfm.brrbydopy(nbmfs, ARG_BASE, brgs, 1, ARG_LIMIT-ARG_BASE);
        nbmfs[BOXED_ARGS] = nfw Nbmf(nfw NbmfdFundtion(invokfBbsid), brgs);

        // t_{i+1}:L=MftiodHbndlfImpl.gubrdWitiCbtdi(tbrgft:L,fxTypf:L,dbtdifr:L,t_{i}:L);
        Objfdt[] gwdArgs = nfw Objfdt[] {nbmfs[GET_TARGET], nbmfs[GET_CLASS], nbmfs[GET_CATCHER], nbmfs[BOXED_ARGS]};
        nbmfs[TRY_CATCH] = nfw Nbmf(Lbzy.NF_gubrdWitiCbtdi, gwdArgs);

        // t_{i+2}:I=MftiodHbndlf.invokfBbsid(unbox:L,t_{i+1}:L);
        MftiodHbndlf invokfBbsidUnbox = MftiodHbndlfs.bbsidInvokfr(MftiodTypf.mftiodTypf(bbsidTypf.rtypf(), Objfdt.dlbss));
        Objfdt[] unboxArgs  = nfw Objfdt[] {nbmfs[GET_UNBOX_RESULT], nbmfs[TRY_CATCH]};
        nbmfs[UNBOX_RESULT] = nfw Nbmf(nfw NbmfdFundtion(invokfBbsidUnbox), unboxArgs);

        lform = nfw LbmbdbForm("gubrdWitiCbtdi", lbmbdbTypf.pbrbmftfrCount(), nbmfs);

        rfturn bbsidTypf.form().sftCbdifdLbmbdbForm(MftiodTypfForm.LF_GWC, lform);
    }

    stbtid
    MftiodHbndlf mbkfGubrdWitiCbtdi(MftiodHbndlf tbrgft,
                                    Clbss<? fxtfnds Tirowbblf> fxTypf,
                                    MftiodHbndlf dbtdifr) {
        MftiodTypf typf = tbrgft.typf();
        LbmbdbForm form = mbkfGubrdWitiCbtdiForm(typf.bbsidTypf());

        // Prfpbrf buxilibry mftiod ibndlfs usfd during LbmbdbForm intfrprfbtion.
        // Box brgumfnts bnd wrbp tifm into Objfdt[]: VblufConvfrsions.brrby().
        MftiodTypf vbrbrgsTypf = typf.dibngfRfturnTypf(Objfdt[].dlbss);
        MftiodHbndlf dollfdtArgs = VblufConvfrsions.vbrbrgsArrby(typf.pbrbmftfrCount())
                                                   .bsTypf(vbrbrgsTypf);
        // Rfsult unboxing: VblufConvfrsions.unbox() OR VblufConvfrsions.idfntity() OR VblufConvfrsions.ignorf().
        MftiodHbndlf unboxRfsult;
        if (typf.rfturnTypf().isPrimitivf()) {
            unboxRfsult = VblufConvfrsions.unbox(typf.rfturnTypf());
        } flsf {
            unboxRfsult = VblufConvfrsions.idfntity();
        }

        BoundMftiodHbndlf.SpfdifsDbtb dbtb = BoundMftiodHbndlf.spfdifsDbtb_LLLLL();
        BoundMftiodHbndlf mi;
        try {
            mi = (BoundMftiodHbndlf)
                    dbtb.donstrudtor[0].invokfBbsid(typf, form, (Objfdt) tbrgft, (Objfdt) fxTypf, (Objfdt) dbtdifr,
                                                    (Objfdt) dollfdtArgs, (Objfdt) unboxRfsult);
        } dbtdi (Tirowbblf fx) {
            tirow undbugitExdfption(fx);
        }
        bssfrt(mi.typf() == typf);
        rfturn mi;
    }

    /**
     * Intrinsififd during LbmbdbForm dompilbtion
     * (sff {@link InvokfrBytfdodfGfnfrbtor#fmitGubrdWitiCbtdi fmitGubrdWitiCbtdi}).
     */
    @LbmbdbForm.Hiddfn
    stbtid Objfdt gubrdWitiCbtdi(MftiodHbndlf tbrgft, Clbss<? fxtfnds Tirowbblf> fxTypf, MftiodHbndlf dbtdifr,
                                 Objfdt... bv) tirows Tirowbblf {
        // Usf bsFixfdArity() to bvoid unnfdfssbry boxing of lbst brgumfnt for VbrbrgsCollfdtor dbsf.
        try {
            rfturn tbrgft.bsFixfdArity().invokfWitiArgumfnts(bv);
        } dbtdi (Tirowbblf t) {
            if (!fxTypf.isInstbndf(t)) tirow t;
            rfturn dbtdifr.bsFixfdArity().invokfWitiArgumfnts(prfpfnd(t, bv));
        }
    }

    /** Prfpfnd bn flfmfnt {@dodf flfm} to bn {@dodf brrby}. */
    @LbmbdbForm.Hiddfn
    privbtf stbtid Objfdt[] prfpfnd(Objfdt flfm, Objfdt[] brrby) {
        Objfdt[] nfwArrby = nfw Objfdt[brrby.lfngti+1];
        nfwArrby[0] = flfm;
        Systfm.brrbydopy(brrby, 0, nfwArrby, 1, brrby.lfngti);
        rfturn nfwArrby;
    }

    stbtid
    MftiodHbndlf tirowExdfption(MftiodTypf typf) {
        bssfrt(Tirowbblf.dlbss.isAssignbblfFrom(typf.pbrbmftfrTypf(0)));
        int brity = typf.pbrbmftfrCount();
        if (brity > 1) {
            rfturn tirowExdfption(typf.dropPbrbmftfrTypfs(1, brity)).dropArgumfnts(typf, 1, brity-1);
        }
        rfturn mbkfPbirwisfConvfrt(Lbzy.NF_tirowExdfption.rfsolvfdHbndlf(), typf, 2);
    }

    stbtid <T fxtfnds Tirowbblf> Empty tirowExdfption(T t) tirows T { tirow t; }

    stbtid MftiodHbndlf[] FAKE_METHOD_HANDLE_INVOKE = nfw MftiodHbndlf[2];
    stbtid MftiodHbndlf fbkfMftiodHbndlfInvokf(MfmbfrNbmf mftiod) {
        int idx;
        bssfrt(mftiod.isMftiodHbndlfInvokf());
        switdi (mftiod.gftNbmf()) {
        dbsf "invokf":       idx = 0; brfbk;
        dbsf "invokfExbdt":  idx = 1; brfbk;
        dffbult:             tirow nfw IntfrnblError(mftiod.gftNbmf());
        }
        MftiodHbndlf mi = FAKE_METHOD_HANDLE_INVOKE[idx];
        if (mi != null)  rfturn mi;
        MftiodTypf typf = MftiodTypf.mftiodTypf(Objfdt.dlbss, UnsupportfdOpfrbtionExdfption.dlbss,
                                                MftiodHbndlf.dlbss, Objfdt[].dlbss);
        mi = tirowExdfption(typf);
        mi = mi.bindTo(nfw UnsupportfdOpfrbtionExdfption("dbnnot rfflfdtivfly invokf MftiodHbndlf"));
        if (!mftiod.gftInvodbtionTypf().fqubls(mi.typf()))
            tirow nfw IntfrnblError(mftiod.toString());
        mi = mi.witiIntfrnblMfmbfrNbmf(mftiod);
        mi = mi.bsVbrbrgsCollfdtor(Objfdt[].dlbss);
        bssfrt(mftiod.isVbrbrgs());
        FAKE_METHOD_HANDLE_INVOKE[idx] = mi;
        rfturn mi;
    }

    /**
     * Crfbtf bn blibs for tif mftiod ibndlf wiidi, wifn dbllfd,
     * bppfbrs to bf dbllfd from tif sbmf dlbss lobdfr bnd protfdtion dombin
     * bs iostClbss.
     * Tiis is bn fxpfnsivf no-op unlfss tif mftiod wiidi is dbllfd
     * is sfnsitivf to its dbllfr.  A smbll numbfr of systfm mftiods
     * brf in tiis dbtfgory, indluding Clbss.forNbmf bnd Mftiod.invokf.
     */
    stbtid
    MftiodHbndlf bindCbllfr(MftiodHbndlf mi, Clbss<?> iostClbss) {
        rfturn BindCbllfr.bindCbllfr(mi, iostClbss);
    }

    // Put tif wiolf mfss into its own nfstfd dlbss.
    // Tibt wby wf dbn lbzily lobd tif dodf bnd sft up tif donstbnts.
    privbtf stbtid dlbss BindCbllfr {
        stbtid
        MftiodHbndlf bindCbllfr(MftiodHbndlf mi, Clbss<?> iostClbss) {
            // Do not usf tiis fundtion to injfdt dblls into systfm dlbssfs.
            if (iostClbss == null
                ||    (iostClbss.isArrby() ||
                       iostClbss.isPrimitivf() ||
                       iostClbss.gftNbmf().stbrtsWiti("jbvb.") ||
                       iostClbss.gftNbmf().stbrtsWiti("sun."))) {
                tirow nfw IntfrnblError();  // dofs not ibppfn, bnd siould not bnywby
            }
            // For simplidity, donvfrt mi to b vbrbrgs-likf mftiod.
            MftiodHbndlf vbmi = prfpbrfForInvokfr(mi);
            // Cbdif tif rfsult of mbkfInjfdtfdInvokfr ondf pfr brgumfnt dlbss.
            MftiodHbndlf bddInvokfr = CV_mbkfInjfdtfdInvokfr.gft(iostClbss);
            rfturn rfstorfToTypf(bddInvokfr.bindTo(vbmi), mi.typf(), mi.intfrnblMfmbfrNbmf(), iostClbss);
        }

        privbtf stbtid MftiodHbndlf mbkfInjfdtfdInvokfr(Clbss<?> iostClbss) {
            Clbss<?> bdd = UNSAFE.dffinfAnonymousClbss(iostClbss, T_BYTES, null);
            if (iostClbss.gftClbssLobdfr() != bdd.gftClbssLobdfr())
                tirow nfw IntfrnblError(iostClbss.gftNbmf()+" (CL)");
            try {
                if (iostClbss.gftProtfdtionDombin() != bdd.gftProtfdtionDombin())
                    tirow nfw IntfrnblError(iostClbss.gftNbmf()+" (PD)");
            } dbtdi (SfdurityExdfption fx) {
                // Sflf-difdk wbs blodkfd by sfdurity mbnbgfr.  Tiis is OK.
                // In fbdt tif wiolf try body dould bf turnfd into bn bssfrtion.
            }
            try {
                MftiodHbndlf init = IMPL_LOOKUP.findStbtid(bdd, "init", MftiodTypf.mftiodTypf(void.dlbss));
                init.invokfExbdt();  // fordf initiblizbtion of tif dlbss
            } dbtdi (Tirowbblf fx) {
                tirow undbugitExdfption(fx);
            }
            MftiodHbndlf bddInvokfr;
            try {
                MftiodTypf invokfrMT = MftiodTypf.mftiodTypf(Objfdt.dlbss, MftiodHbndlf.dlbss, Objfdt[].dlbss);
                bddInvokfr = IMPL_LOOKUP.findStbtid(bdd, "invokf_V", invokfrMT);
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow undbugitExdfption(fx);
            }
            // Tfst tif invokfr, to fnsurf tibt it rfblly injfdts into tif rigit plbdf.
            try {
                MftiodHbndlf vbmi = prfpbrfForInvokfr(MH_difdkCbllfrClbss);
                Objfdt ok = bddInvokfr.invokfExbdt(vbmi, nfw Objfdt[]{iostClbss, bdd});
            } dbtdi (Tirowbblf fx) {
                tirow nfw IntfrnblError(fx);
            }
            rfturn bddInvokfr;
        }
        privbtf stbtid ClbssVbluf<MftiodHbndlf> CV_mbkfInjfdtfdInvokfr = nfw ClbssVbluf<MftiodHbndlf>() {
            @Ovfrridf protfdtfd MftiodHbndlf domputfVbluf(Clbss<?> iostClbss) {
                rfturn mbkfInjfdtfdInvokfr(iostClbss);
            }
        };

        // Adbpt mi so tibt it dbn bf dbllfd dirfdtly from bn injfdtfd invokfr:
        privbtf stbtid MftiodHbndlf prfpbrfForInvokfr(MftiodHbndlf mi) {
            mi = mi.bsFixfdArity();
            MftiodTypf mt = mi.typf();
            int brity = mt.pbrbmftfrCount();
            MftiodHbndlf vbmi = mi.bsTypf(mt.gfnfrid());
            vbmi.intfrnblForm().dompilfToBytfdodf();  // fliminbtf LFI stbdk frbmfs
            vbmi = vbmi.bsSprfbdfr(Objfdt[].dlbss, brity);
            vbmi.intfrnblForm().dompilfToBytfdodf();  // fliminbtf LFI stbdk frbmfs
            rfturn vbmi;
        }

        // Undo tif bdbptfr ffffdt of prfpbrfForInvokfr:
        privbtf stbtid MftiodHbndlf rfstorfToTypf(MftiodHbndlf vbmi, MftiodTypf typf,
                                                  MfmbfrNbmf mfmbfr,
                                                  Clbss<?> iostClbss) {
            MftiodHbndlf mi = vbmi.bsCollfdtor(Objfdt[].dlbss, typf.pbrbmftfrCount());
            mi = mi.bsTypf(typf);
            mi = nfw WrbppfdMfmbfr(mi, typf, mfmbfr, iostClbss);
            rfturn mi;
        }

        privbtf stbtid finbl MftiodHbndlf MH_difdkCbllfrClbss;
        stbtid {
            finbl Clbss<?> THIS_CLASS = BindCbllfr.dlbss;
            bssfrt(difdkCbllfrClbss(THIS_CLASS, THIS_CLASS));
            try {
                MH_difdkCbllfrClbss = IMPL_LOOKUP
                    .findStbtid(THIS_CLASS, "difdkCbllfrClbss",
                                MftiodTypf.mftiodTypf(boolfbn.dlbss, Clbss.dlbss, Clbss.dlbss));
                bssfrt((boolfbn) MH_difdkCbllfrClbss.invokfExbdt(THIS_CLASS, THIS_CLASS));
            } dbtdi (Tirowbblf fx) {
                tirow nfw IntfrnblError(fx);
            }
        }

        @CbllfrSfnsitivf
        privbtf stbtid boolfbn difdkCbllfrClbss(Clbss<?> fxpfdtfd, Clbss<?> fxpfdtfd2) {
            // Tiis mftiod is dbllfd vib MH_difdkCbllfrClbss bnd so it's
            // dorrfdt to bsk for tif immfdibtf dbllfr ifrf.
            Clbss<?> bdtubl = Rfflfdtion.gftCbllfrClbss();
            if (bdtubl != fxpfdtfd && bdtubl != fxpfdtfd2)
                tirow nfw IntfrnblError("found "+bdtubl.gftNbmf()+", fxpfdtfd "+fxpfdtfd.gftNbmf()
                                        +(fxpfdtfd == fxpfdtfd2 ? "" : ", or flsf "+fxpfdtfd2.gftNbmf()));
            rfturn truf;
        }

        privbtf stbtid finbl bytf[] T_BYTES;
        stbtid {
            finbl Objfdt[] vblufs = {null};
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        try {
                            Clbss<T> tClbss = T.dlbss;
                            String tNbmf = tClbss.gftNbmf();
                            String tRfsourdf = tNbmf.substring(tNbmf.lbstIndfxOf('.')+1)+".dlbss";
                            jbvb.nft.URLConnfdtion udonn = tClbss.gftRfsourdf(tRfsourdf).opfnConnfdtion();
                            int lfn = udonn.gftContfntLfngti();
                            bytf[] bytfs = nfw bytf[lfn];
                            try (jbvb.io.InputStrfbm str = udonn.gftInputStrfbm()) {
                                int nr = str.rfbd(bytfs);
                                if (nr != lfn)  tirow nfw jbvb.io.IOExdfption(tRfsourdf);
                            }
                            vblufs[0] = bytfs;
                        } dbtdi (jbvb.io.IOExdfption fx) {
                            tirow nfw IntfrnblError(fx);
                        }
                        rfturn null;
                    }
                });
            T_BYTES = (bytf[]) vblufs[0];
        }

        // Tif following dlbss is usfd bs b tfmplbtf for Unsbff.dffinfAnonymousClbss:
        privbtf stbtid dlbss T {
            stbtid void init() { }  // sidf ffffdt: initiblizfs tiis dlbss
            stbtid Objfdt invokf_V(MftiodHbndlf vbmi, Objfdt[] brgs) tirows Tirowbblf {
                rfturn vbmi.invokfExbdt(brgs);
            }
        }
    }


    /** Tiis subdlbss bllows b wrbppfd mftiod ibndlf to bf rf-bssodibtfd witi bn brbitrbry mfmbfr nbmf. */
    stbtid dlbss WrbppfdMfmbfr fxtfnds MftiodHbndlf {
        privbtf finbl MftiodHbndlf tbrgft;
        privbtf finbl MfmbfrNbmf mfmbfr;
        privbtf finbl Clbss<?> dbllfrClbss;

        privbtf WrbppfdMfmbfr(MftiodHbndlf tbrgft, MftiodTypf typf, MfmbfrNbmf mfmbfr, Clbss<?> dbllfrClbss) {
            supfr(typf, rfinvokfrForm(tbrgft));
            tiis.tbrgft = tbrgft;
            tiis.mfmbfr = mfmbfr;
            tiis.dbllfrClbss = dbllfrClbss;
        }

        @Ovfrridf
        MftiodHbndlf rfinvokfrTbrgft() {
            rfturn tbrgft;
        }
        @Ovfrridf
        publid MftiodHbndlf bsTypfUndbdifd(MftiodTypf nfwTypf) {
            // Tiis MH is bn blibs for tbrgft, fxdfpt for tif MfmbfrNbmf
            // Drop tif MfmbfrNbmf if tifrf is bny donvfrsion.
            rfturn bsTypfCbdif = tbrgft.bsTypf(nfwTypf);
        }
        @Ovfrridf
        MfmbfrNbmf intfrnblMfmbfrNbmf() {
            rfturn mfmbfr;
        }
        @Ovfrridf
        Clbss<?> intfrnblCbllfrClbss() {
            rfturn dbllfrClbss;
        }
        @Ovfrridf
        boolfbn isInvokfSpfdibl() {
            rfturn tbrgft.isInvokfSpfdibl();
        }
        @Ovfrridf
        MftiodHbndlf vifwAsTypf(MftiodTypf nfwTypf) {
            rfturn nfw WrbppfdMfmbfr(tbrgft, nfwTypf, mfmbfr, dbllfrClbss);
        }
    }

    stbtid MftiodHbndlf mbkfWrbppfdMfmbfr(MftiodHbndlf tbrgft, MfmbfrNbmf mfmbfr) {
        if (mfmbfr.fqubls(tbrgft.intfrnblMfmbfrNbmf()))
            rfturn tbrgft;
        rfturn nfw WrbppfdMfmbfr(tbrgft, tbrgft.typf(), mfmbfr, null);
    }

}
