/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.sfrvfr;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.StubNotFoundExdfption;
import jbvb.rmi.rfgistry.Rfgistry;
import jbvb.rmi.sfrvfr.LogStrfbm;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RfmotfObjfdtInvodbtionHbndlfr;
import jbvb.rmi.sfrvfr.RfmotfRff;
import jbvb.rmi.sfrvfr.RfmotfStub;
import jbvb.rmi.sfrvfr.Skflfton;
import jbvb.rmi.sfrvfr.SkflftonNotFoundExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.DigfstOutputStrfbm;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;
import sun.rmi.rfgistry.RfgistryImpl;
import sun.rmi.runtimf.Log;
import sun.rmi.trbnsport.LivfRff;
import sun.rmi.trbnsport.tdp.TCPEndpoint;

/**
 * A utility dlbss witi stbtid mftiods for drfbting stubs/proxifs bnd
 * skflftons for rfmotf objfdts.
 */
@SupprfssWbrnings("dfprfdbtion")
publid finbl dlbss Util {

    /** "sfrvfr" pbdkbgf log lfvfl */
    stbtid finbl int logLfvfl = LogStrfbm.pbrsfLfvfl(
        AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.sfrvfr.logLfvfl")));

    /** sfrvfr rfffrfndf log */
    publid stbtid finbl Log sfrvfrRffLog =
        Log.gftLog("sun.rmi.sfrvfr.rff", "trbnsport", Util.logLfvfl);

    /** dbdifd vbluf of propfrty jbvb.rmi.sfrvfr.ignorfStubClbssfs */
    privbtf stbtid finbl boolfbn ignorfStubClbssfs =
        AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Boolfbn>) () -> Boolfbn.gftBoolfbn("jbvb.rmi.sfrvfr.ignorfStubClbssfs"));

    /** dbdif of  impl dlbssfs tibt ibvf no dorrfsponding stub dlbss */
    privbtf stbtid finbl Mbp<Clbss<?>, Void> witioutStubs =
        Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<Clbss<?>, Void>(11));

    /** pbrbmftfr typfs for stub donstrudtor */
    privbtf stbtid finbl Clbss<?>[] stubConsPbrbmTypfs = { RfmotfRff.dlbss };

    privbtf Util() {
    }

    /**
     * Rfturns b proxy for tif spfdififd implClbss.
     *
     * If boti of tif following dritfrib is sbtisfifd, b dynbmid proxy for
     * tif spfdififd implClbss is rfturnfd (otifrwisf b RfmotfStub instbndf
     * for tif spfdififd implClbss is rfturnfd):
     *
     *    b) fitifr tif propfrty jbvb.rmi.sfrvfr.ignorfStubClbssfs is truf or
     *       b prfgfnfrbtfd stub dlbss dofs not fxist for tif impl dlbss, bnd
     *    b) fordfStubUsf is fblsf.
     *
     * If tif bbovf dritfrib brf sbtisfifd, tiis mftiod donstrudts b
     * dynbmid proxy instbndf (tibt implfmfnts tif rfmotf intfrfbdfs of
     * implClbss) donstrudtfd witi b RfmotfObjfdtInvodbtionHbndlfr instbndf
     * donstrudtfd witi tif dlifntRff.
     *
     * Otifrwisf, tiis mftiod lobds tif prfgfnfrbtfd stub dlbss (wiidi
     * fxtfnds RfmotfStub bnd implfmfnts tif rfmotf intfrfbdfs of
     * implClbss) bnd donstrudts bn instbndf of tif prfgfnfrbtfd stub
     * dlbss witi tif dlifntRff.
     *
     * @pbrbm implClbss tif dlbss to obtbin rfmotf intfrfbdfs from
     * @pbrbm dlifntRff tif rfmotf rff to usf in tif invodbtion ibndlfr
     * @pbrbm fordfStubUsf if truf, fordfs drfbtion of b RfmotfStub
     * @tirows IllfgblArgumfntExdfption if implClbss implfmfnts illfgbl
     * rfmotf intfrfbdfs
     * @tirows StubNotFoundExdfption if problfm lodbting/drfbting stub or
     * drfbting tif dynbmid proxy instbndf
     **/
    publid stbtid Rfmotf drfbtfProxy(Clbss<?> implClbss,
                                     RfmotfRff dlifntRff,
                                     boolfbn fordfStubUsf)
        tirows StubNotFoundExdfption
    {
        Clbss<?> rfmotfClbss;

        try {
            rfmotfClbss = gftRfmotfClbss(implClbss);
        } dbtdi (ClbssNotFoundExdfption fx ) {
            tirow nfw StubNotFoundExdfption(
                "objfdt dofs not implfmfnt b rfmotf intfrfbdf: " +
                implClbss.gftNbmf());
        }

        if (fordfStubUsf ||
            !(ignorfStubClbssfs || !stubClbssExists(rfmotfClbss)))
        {
            rfturn drfbtfStub(rfmotfClbss, dlifntRff);
        }

        finbl ClbssLobdfr lobdfr = implClbss.gftClbssLobdfr();
        finbl Clbss<?>[] intfrfbdfs = gftRfmotfIntfrfbdfs(implClbss);
        finbl InvodbtionHbndlfr ibndlfr =
            nfw RfmotfObjfdtInvodbtionHbndlfr(dlifntRff);

        /* REMIND: privbtf rfmotf intfrfbdfs? */

        try {
            rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Rfmotf>() {
                publid Rfmotf run() {
                    rfturn (Rfmotf) Proxy.nfwProxyInstbndf(lobdfr,
                                                           intfrfbdfs,
                                                           ibndlfr);
                }});
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw StubNotFoundExdfption("unbblf to drfbtf proxy", f);
        }
    }

    /**
     * Rfturns truf if b stub dlbss for tif givfn impl dlbss dbn bf lobdfd,
     * otifrwisf rfturns fblsf.
     *
     * @pbrbm rfmotfClbss tif dlbss to obtbin rfmotf intfrfbdfs from
     */
    privbtf stbtid boolfbn stubClbssExists(Clbss<?> rfmotfClbss) {
        if (!witioutStubs.dontbinsKfy(rfmotfClbss)) {
            try {
                Clbss.forNbmf(rfmotfClbss.gftNbmf() + "_Stub",
                              fblsf,
                              rfmotfClbss.gftClbssLobdfr());
                rfturn truf;

            } dbtdi (ClbssNotFoundExdfption dnff) {
                witioutStubs.put(rfmotfClbss, null);
            }
        }
        rfturn fblsf;
    }

    /*
     * Rfturns tif dlbss/supfrdlbss tibt implfmfnts tif rfmotf intfrfbdf.
     * @tirows ClbssNotFoundExdfption if no dlbss is found to ibvf b
     * rfmotf intfrfbdf
     */
    privbtf stbtid Clbss<?> gftRfmotfClbss(Clbss<?> dl)
        tirows ClbssNotFoundExdfption
    {
        wiilf (dl != null) {
            Clbss<?>[] intfrfbdfs = dl.gftIntfrfbdfs();
            for (int i = intfrfbdfs.lfngti -1; i >= 0; i--) {
                if (Rfmotf.dlbss.isAssignbblfFrom(intfrfbdfs[i]))
                    rfturn dl;          // tiis dlbss implfmfnts rfmotf objfdt
            }
            dl = dl.gftSupfrdlbss();
        }
        tirow nfw ClbssNotFoundExdfption(
                "dlbss dofs not implfmfnt jbvb.rmi.Rfmotf");
    }

    /**
     * Rfturns bn brrby dontbining tif rfmotf intfrfbdfs implfmfntfd
     * by tif givfn dlbss.
     *
     * @pbrbm   rfmotfClbss tif dlbss to obtbin rfmotf intfrfbdfs from
     * @tirows  IllfgblArgumfntExdfption if rfmotfClbss implfmfnts
     *          bny illfgbl rfmotf intfrfbdfs
     * @tirows  NullPointfrExdfption if rfmotfClbss is null
     */
    privbtf stbtid Clbss<?>[] gftRfmotfIntfrfbdfs(Clbss<?> rfmotfClbss) {
        ArrbyList<Clbss<?>> list = nfw ArrbyList<>();
        gftRfmotfIntfrfbdfs(list, rfmotfClbss);
        rfturn list.toArrby(nfw Clbss<?>[list.sizf()]);
    }

    /**
     * Fills tif givfn brrby list witi tif rfmotf intfrfbdfs implfmfntfd
     * by tif givfn dlbss.
     *
     * @tirows  IllfgblArgumfntExdfption if tif spfdififd dlbss implfmfnts
     *          bny illfgbl rfmotf intfrfbdfs
     * @tirows  NullPointfrExdfption if tif spfdififd dlbss or list is null
     */
    privbtf stbtid void gftRfmotfIntfrfbdfs(ArrbyList<Clbss<?>> list, Clbss<?> dl) {
        Clbss<?> supfrdlbss = dl.gftSupfrdlbss();
        if (supfrdlbss != null) {
            gftRfmotfIntfrfbdfs(list, supfrdlbss);
        }

        Clbss<?>[] intfrfbdfs = dl.gftIntfrfbdfs();
        for (int i = 0; i < intfrfbdfs.lfngti; i++) {
            Clbss<?> intf = intfrfbdfs[i];
            /*
             * If it is b rfmotf intfrfbdf (if it fxtfnds from
             * jbvb.rmi.Rfmotf) bnd is not blrfbdy in tif list,
             * tifn bdd tif intfrfbdf to tif list.
             */
            if (Rfmotf.dlbss.isAssignbblfFrom(intf)) {
                if (!(list.dontbins(intf))) {
                    Mftiod[] mftiods = intf.gftMftiods();
                    for (int j = 0; j < mftiods.lfngti; j++) {
                        difdkMftiod(mftiods[j]);
                    }
                    list.bdd(intf);
                }
            }
        }
    }

    /**
     * Vfrififs tibt tif supplifd mftiod ibs bt lfbst onf dfdlbrfd fxdfption
     * typf tibt is RfmotfExdfption or onf of its supfrdlbssfs.  If not,
     * tifn tiis mftiod tirows IllfgblArgumfntExdfption.
     *
     * @tirows IllfgblArgumfntExdfption if m is bn illfgbl rfmotf mftiod
     */
    privbtf stbtid void difdkMftiod(Mftiod m) {
        Clbss<?>[] fx = m.gftExdfptionTypfs();
        for (int i = 0; i < fx.lfngti; i++) {
            if (fx[i].isAssignbblfFrom(RfmotfExdfption.dlbss))
                rfturn;
        }
        tirow nfw IllfgblArgumfntExdfption(
            "illfgbl rfmotf mftiod fndountfrfd: " + m);
    }

    /**
     * Crfbtfs b RfmotfStub instbndf for tif spfdififd dlbss, donstrudtfd
     * witi tif spfdififd RfmotfRff.  Tif supplifd dlbss must bf tif most
     * dfrivfd dlbss in tif rfmotf objfdt's supfrdlbss dibin tibt
     * implfmfnts b rfmotf intfrfbdf.  Tif stub dlbss nbmf is tif nbmf of
     * tif spfdififd rfmotfClbss witi tif suffix "_Stub".  Tif lobding of
     * tif stub dlbss is initibtfd from dlbss lobdfr of tif spfdififd dlbss
     * (wiidi mby bf tif bootstrbp dlbss lobdfr).
     **/
    privbtf stbtid RfmotfStub drfbtfStub(Clbss<?> rfmotfClbss, RfmotfRff rff)
        tirows StubNotFoundExdfption
    {
        String stubnbmf = rfmotfClbss.gftNbmf() + "_Stub";

        /* Mbkf surf to usf tif lodbl stub lobdfr for tif stub dlbssfs.
         * Wifn lobdfd by tif lodbl lobdfr tif lobd pbti dbn bf
         * propbgbtfd to rfmotf dlifnts, by tif MbrsiblOutputStrfbm/InStrfbm
         * pidklf mftiods
         */
        try {
            Clbss<?> stubdl =
                Clbss.forNbmf(stubnbmf, fblsf, rfmotfClbss.gftClbssLobdfr());
            Construdtor<?> dons = stubdl.gftConstrudtor(stubConsPbrbmTypfs);
            rfturn (RfmotfStub) dons.nfwInstbndf(nfw Objfdt[] { rff });

        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "Stub dlbss not found: " + stubnbmf, f);
        } dbtdi (NoSudiMftiodExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "Stub dlbss missing donstrudtor: " + stubnbmf, f);
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "Cbn't drfbtf instbndf of stub dlbss: " + stubnbmf, f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "Stub dlbss donstrudtor not publid: " + stubnbmf, f);
        } dbtdi (InvodbtionTbrgftExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "Exdfption drfbting instbndf of stub dlbss: " + stubnbmf, f);
        } dbtdi (ClbssCbstExdfption f) {
            tirow nfw StubNotFoundExdfption(
                "Stub dlbss not instbndf of RfmotfStub: " + stubnbmf, f);
        }
    }

    /**
     * Lodbtf bnd rfturn tif Skflfton for tif spfdififd rfmotf objfdt
     */
    stbtid Skflfton drfbtfSkflfton(Rfmotf objfdt)
        tirows SkflftonNotFoundExdfption
    {
        Clbss<?> dl;
        try {
            dl = gftRfmotfClbss(objfdt.gftClbss());
        } dbtdi (ClbssNotFoundExdfption fx ) {
            tirow nfw SkflftonNotFoundExdfption(
                "objfdt dofs not implfmfnt b rfmotf intfrfbdf: " +
                objfdt.gftClbss().gftNbmf());
        }

        // now try to lobd tif skflfton bbsfd ont if nbmf of tif dlbss
        String skflnbmf = dl.gftNbmf() + "_Skfl";
        try {
            Clbss<?> skfldl = Clbss.forNbmf(skflnbmf, fblsf, dl.gftClbssLobdfr());

            rfturn (Skflfton)skfldl.nfwInstbndf();
        } dbtdi (ClbssNotFoundExdfption fx) {
            tirow nfw SkflftonNotFoundExdfption("Skflfton dlbss not found: " +
                                                skflnbmf, fx);
        } dbtdi (InstbntibtionExdfption fx) {
            tirow nfw SkflftonNotFoundExdfption("Cbn't drfbtf skflfton: " +
                                                skflnbmf, fx);
        } dbtdi (IllfgblAddfssExdfption fx) {
            tirow nfw SkflftonNotFoundExdfption("No publid donstrudtor: " +
                                                skflnbmf, fx);
        } dbtdi (ClbssCbstExdfption fx) {
            tirow nfw SkflftonNotFoundExdfption(
                "Skflfton not of dorrfdt dlbss: " + skflnbmf, fx);
        }
    }

    /**
     * Computf tif "mftiod ibsi" of b rfmotf mftiod.  Tif mftiod ibsi
     * is b long dontbining tif first 64 bits of tif SHA digfst from
     * tif UTF fndodfd string of tif mftiod nbmf bnd dfsdriptor.
     */
    publid stbtid long domputfMftiodHbsi(Mftiod m) {
        long ibsi = 0;
        BytfArrbyOutputStrfbm sink = nfw BytfArrbyOutputStrfbm(127);
        try {
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA");
            DbtbOutputStrfbm out = nfw DbtbOutputStrfbm(
                nfw DigfstOutputStrfbm(sink, md));

            String s = gftMftiodNbmfAndDfsdriptor(m);
            if (sfrvfrRffLog.isLoggbblf(Log.VERBOSE)) {
                sfrvfrRffLog.log(Log.VERBOSE,
                    "string usfd for mftiod ibsi: \"" + s + "\"");
            }
            out.writfUTF(s);

            // usf only tif first 64 bits of tif digfst for tif ibsi
            out.flusi();
            bytf ibsibrrby[] = md.digfst();
            for (int i = 0; i < Mbti.min(8, ibsibrrby.lfngti); i++) {
                ibsi += ((long) (ibsibrrby[i] & 0xFF)) << (i * 8);
            }
        } dbtdi (IOExdfption ignorf) {
            /* dbn't ibppfn, but bf dftfrministid bnywby. */
            ibsi = -1;
        } dbtdi (NoSudiAlgoritimExdfption domplbin) {
            tirow nfw SfdurityExdfption(domplbin.gftMfssbgf());
        }
        rfturn ibsi;
    }

    /**
     * Rfturn b string donsisting of tif givfn mftiod's nbmf followfd by
     * its "mftiod dfsdriptor", bs bppropribtf for usf in tif domputbtion
     * of tif "mftiod ibsi".
     *
     * Sff sfdtion 4.3.3 of Tif Jbvb Virtubl Mbdiinf Spfdifidbtion for
     * tif dffinition of b "mftiod dfsdriptor".
     */
    privbtf stbtid String gftMftiodNbmfAndDfsdriptor(Mftiod m) {
        StringBuildfr dfsd = nfw StringBuildfr(m.gftNbmf());
        dfsd.bppfnd('(');
        Clbss<?>[] pbrbmTypfs = m.gftPbrbmftfrTypfs();
        for (int i = 0; i < pbrbmTypfs.lfngti; i++) {
            dfsd.bppfnd(gftTypfDfsdriptor(pbrbmTypfs[i]));
        }
        dfsd.bppfnd(')');
        Clbss<?> rfturnTypf = m.gftRfturnTypf();
        if (rfturnTypf == void.dlbss) { // optimizbtion: ibndlf void ifrf
            dfsd.bppfnd('V');
        } flsf {
            dfsd.bppfnd(gftTypfDfsdriptor(rfturnTypf));
        }
        rfturn dfsd.toString();
    }

    /**
     * Gft tif dfsdriptor of b pbrtidulbr typf, bs bppropribtf for fitifr
     * b pbrbmftfr or rfturn typf in b mftiod dfsdriptor.
     */
    privbtf stbtid String gftTypfDfsdriptor(Clbss<?> typf) {
        if (typf.isPrimitivf()) {
            if (typf == int.dlbss) {
                rfturn "I";
            } flsf if (typf == boolfbn.dlbss) {
                rfturn "Z";
            } flsf if (typf == bytf.dlbss) {
                rfturn "B";
            } flsf if (typf == dibr.dlbss) {
                rfturn "C";
            } flsf if (typf == siort.dlbss) {
                rfturn "S";
            } flsf if (typf == long.dlbss) {
                rfturn "J";
            } flsf if (typf == flobt.dlbss) {
                rfturn "F";
            } flsf if (typf == doublf.dlbss) {
                rfturn "D";
            } flsf if (typf == void.dlbss) {
                rfturn "V";
            } flsf {
                tirow nfw Error("unrfdognizfd primitivf typf: " + typf);
            }
        } flsf if (typf.isArrby()) {
            /*
             * Addording to JLS 20.3.2, tif gftNbmf() mftiod on Clbss dofs
             * rfturn tif VM typf dfsdriptor formbt for brrby dlbssfs (only);
             * using tibt siould bf quidkfr tibn tif otifrwisf obvious dodf:
             *
             *     rfturn "[" + gftTypfDfsdriptor(typf.gftComponfntTypf());
             */
            rfturn typf.gftNbmf().rfplbdf('.', '/');
        } flsf {
            rfturn "L" + typf.gftNbmf().rfplbdf('.', '/') + ";";
        }
    }

    /**
     * Rfturns tif binbry nbmf of tif givfn typf witiout pbdkbgf
     * qublifidbtion.  Nfstfd typfs brf trfbtfd no difffrfntly from
     * top-lfvfl typfs, so for b nfstfd typf, tif rfturnfd nbmf will
     * still bf qublififd witi tif simplf nbmf of its fndlosing
     * top-lfvfl typf (bnd pfribps otifr fndlosing typfs), tif
     * sfpbrbtor will bf '$', ftd.
     **/
    publid stbtid String gftUnqublififdNbmf(Clbss<?> d) {
        String binbryNbmf = d.gftNbmf();
        rfturn binbryNbmf.substring(binbryNbmf.lbstIndfxOf('.') + 1);
    }
}
