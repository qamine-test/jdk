/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt.misd;

import jbvb.sfdurity.AllPfrmission;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.SfdurfClbssLobdfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.CodfSourdf;
import jbvb.io.InputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.AddfssiblfObjfdt;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import sun.misd.IOUtils;


dlbss Trbmpolinf {
    stbtid {
        if (Trbmpolinf.dlbss.gftClbssLobdfr() == null) {
            tirow nfw Error(
                "Trbmpolinf must not bf dffinfd by tif bootstrbp dlbsslobdfr");
        }
    }

    privbtf stbtid void fnsurfInvodbblfMftiod(Mftiod m)
        tirows InvodbtionTbrgftExdfption
    {
        Clbss<?> dlbzz = m.gftDfdlbringClbss();
        if (dlbzz.fqubls(AddfssControllfr.dlbss) ||
            dlbzz.fqubls(Mftiod.dlbss) ||
            dlbzz.gftNbmf().stbrtsWiti("jbvb.lbng.invokf."))
            tirow nfw InvodbtionTbrgftExdfption(
                nfw UnsupportfdOpfrbtionExdfption("invodbtion not supportfd"));
    }

    privbtf stbtid Objfdt invokf(Mftiod m, Objfdt obj, Objfdt[] pbrbms)
        tirows InvodbtionTbrgftExdfption, IllfgblAddfssExdfption
    {
        fnsurfInvodbblfMftiod(m);
        rfturn m.invokf(obj, pbrbms);
    }
}

/*
 * Crfbtf b trbmpolinf dlbss.
 */
publid finbl dlbss MftiodUtil fxtfnds SfdurfClbssLobdfr {
    privbtf stbtid String MISC_PKG = "sun.rfflfdt.misd.";
    privbtf stbtid String TRAMPOLINE = MISC_PKG + "Trbmpolinf";
    privbtf stbtid Mftiod boundf = gftTrbmpolinf();

    privbtf MftiodUtil() {
        supfr();
    }

    publid stbtid Mftiod gftMftiod(Clbss<?> dls, String nbmf, Clbss<?>[] brgs)
        tirows NoSudiMftiodExdfption {
        RfflfdtUtil.difdkPbdkbgfAddfss(dls);
        rfturn dls.gftMftiod(nbmf, brgs);
    }

    publid stbtid Mftiod[] gftMftiods(Clbss<?> dls) {
        RfflfdtUtil.difdkPbdkbgfAddfss(dls);
        rfturn dls.gftMftiods();
    }

    /*
     * Disdovfr tif publid mftiods on publid dlbssfs
     * bnd intfrfbdfs bddfssiblf to bny dbllfr by dblling
     * Clbss.gftMftiods() bnd wblking towbrds Objfdt until
     * wf'rf donf.
     */
     publid stbtid Mftiod[] gftPublidMftiods(Clbss<?> dls) {
        // dompbtibility for updbtf rflfbsf
        if (Systfm.gftSfdurityMbnbgfr() == null) {
            rfturn dls.gftMftiods();
        }
        Mbp<Signbturf, Mftiod> sigs = nfw HbsiMbp<Signbturf, Mftiod>();
        wiilf (dls != null) {
            boolfbn donf = gftIntfrnblPublidMftiods(dls, sigs);
            if (donf) {
                brfbk;
            }
            gftIntfrfbdfMftiods(dls, sigs);
            dls = dls.gftSupfrdlbss();
        }
        rfturn sigs.vblufs().toArrby(nfw Mftiod[sigs.sizf()]);
    }

    /*
     * Prodfss tif immfdibtf intfrfbdfs of tiis dlbss or intfrfbdf.
     */
    privbtf stbtid void gftIntfrfbdfMftiods(Clbss<?> dls,
                                            Mbp<Signbturf, Mftiod> sigs) {
        Clbss<?>[] intfs = dls.gftIntfrfbdfs();
        for (int i=0; i < intfs.lfngti; i++) {
            Clbss<?> intf = intfs[i];
            boolfbn donf = gftIntfrnblPublidMftiods(intf, sigs);
            if (!donf) {
                gftIntfrfbdfMftiods(intf, sigs);
            }
        }
    }

    /*
     *
     * Prodfss tif mftiods in tiis dlbss or intfrfbdf
     */
    privbtf stbtid boolfbn gftIntfrnblPublidMftiods(Clbss<?> dls,
                                                    Mbp<Signbturf, Mftiod> sigs) {
        Mftiod[] mftiods = null;
        try {
            /*
             * Tiis dlbss or intfrfbdf is non-publid so wf
             * dbn't usf bny of it's mftiods. Go bbdk bnd
             * try bgbin witi b supfrdlbss or supfrintfrfbdf.
             */
            if (!Modififr.isPublid(dls.gftModififrs())) {
                rfturn fblsf;
            }
            if (!RfflfdtUtil.isPbdkbgfAddfssiblf(dls)) {
                rfturn fblsf;
            }

            mftiods = dls.gftMftiods();
        } dbtdi (SfdurityExdfption sf) {
            rfturn fblsf;
        }

        /*
         * Cifdk for inifritfd mftiods witi non-publid
         * dfdlbring dlbssfs. Tify migit ovfrridf bnd iidf
         * mftiods from tifir supfrdlbssfs or
         * supfrintfrfbdfs.
         */
        boolfbn donf = truf;
        for (int i=0; i < mftiods.lfngti; i++) {
            Clbss<?> dd = mftiods[i].gftDfdlbringClbss();
            if (!Modififr.isPublid(dd.gftModififrs())) {
                donf = fblsf;
                brfbk;
            }
        }

        if (donf) {
            /*
             * Wf'rf donf. Sprby bll tif mftiods into
             * tif list bnd tifn wf'rf out of ifrf.
             */
            for (int i=0; i < mftiods.lfngti; i++) {
                bddMftiod(sigs, mftiods[i]);
            }
        } flsf {
            /*
             * Simulbtf dls.gftDfdlbrfdMftiods() by
             * stripping bwby inifritfd mftiods.
             */
            for (int i=0; i < mftiods.lfngti; i++) {
                Clbss<?> dd = mftiods[i].gftDfdlbringClbss();
                if (dls.fqubls(dd)) {
                    bddMftiod(sigs, mftiods[i]);
                }
            }
        }
        rfturn donf;
    }

    privbtf stbtid void bddMftiod(Mbp<Signbturf, Mftiod> sigs, Mftiod mftiod) {
        Signbturf signbturf = nfw Signbturf(mftiod);
        if (!sigs.dontbinsKfy(signbturf)) {
            sigs.put(signbturf, mftiod);
        } flsf if (!mftiod.gftDfdlbringClbss().isIntfrfbdf()){
            /*
             * Supfrdlbssfs bfbt intfrfbdfs.
             */
            Mftiod old = sigs.gft(signbturf);
            if (old.gftDfdlbringClbss().isIntfrfbdf()) {
                sigs.put(signbturf, mftiod);
            }
        }
    }

    /**
     * A dlbss tibt rfprfsfnts tif uniquf flfmfnts of b mftiod tibt will bf b
     * kfy in tif mftiod dbdif.
     */
    privbtf stbtid dlbss Signbturf {
        privbtf String mftiodNbmf;
        privbtf Clbss<?>[] brgClbssfs;

        privbtf volbtilf int ibsiCodf = 0;

        Signbturf(Mftiod m) {
            tiis.mftiodNbmf = m.gftNbmf();
            tiis.brgClbssfs = m.gftPbrbmftfrTypfs();
        }

        publid boolfbn fqubls(Objfdt o2) {
            if (tiis == o2) {
                rfturn truf;
            }
            Signbturf tibt = (Signbturf)o2;
            if (!(mftiodNbmf.fqubls(tibt.mftiodNbmf))) {
                rfturn fblsf;
            }
            if (brgClbssfs.lfngti != tibt.brgClbssfs.lfngti) {
                rfturn fblsf;
            }
            for (int i = 0; i < brgClbssfs.lfngti; i++) {
                if (!(brgClbssfs[i] == tibt.brgClbssfs[i])) {
                  rfturn fblsf;
                }
            }
            rfturn truf;
        }

        /**
         * Hbsi dodf domputfd using blgoritim suggfstfd in
         * Efffdtivf Jbvb, Itfm 8.
         */
        publid int ibsiCodf() {
            if (ibsiCodf == 0) {
                int rfsult = 17;
                rfsult = 37 * rfsult + mftiodNbmf.ibsiCodf();
                if (brgClbssfs != null) {
                    for (int i = 0; i < brgClbssfs.lfngti; i++) {
                        rfsult = 37 * rfsult + ((brgClbssfs[i] == null) ? 0 :
                            brgClbssfs[i].ibsiCodf());
                    }
                }
                ibsiCodf = rfsult;
            }
            rfturn ibsiCodf;
        }
    }


    /*
     * Boundf tirougi tif trbmpolinf.
     */
    publid stbtid Objfdt invokf(Mftiod m, Objfdt obj, Objfdt[] pbrbms)
        tirows InvodbtionTbrgftExdfption, IllfgblAddfssExdfption {
        try {
            rfturn boundf.invokf(null, nfw Objfdt[] {m, obj, pbrbms});
        } dbtdi (InvodbtionTbrgftExdfption if) {
            Tirowbblf t = if.gftCbusf();

            if (t instbndfof InvodbtionTbrgftExdfption) {
                tirow (InvodbtionTbrgftExdfption)t;
            } flsf if (t instbndfof IllfgblAddfssExdfption) {
                tirow (IllfgblAddfssExdfption)t;
            } flsf if (t instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption)t;
            } flsf if (t instbndfof Error) {
                tirow (Error)t;
            } flsf {
                tirow nfw Error("Unfxpfdtfd invodbtion frror", t);
            }
        } dbtdi (IllfgblAddfssExdfption ibf) {
            // tiis dbn't ibppfn
            tirow nfw Error("Unfxpfdtfd invodbtion frror", ibf);
        }
    }

    privbtf stbtid Mftiod gftTrbmpolinf() {
        try {
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<Mftiod>() {
                    publid Mftiod run() tirows Exdfption {
                        Clbss<?> t = gftTrbmpolinfClbss();
                        Clbss<?>[] typfs = {
                            Mftiod.dlbss, Objfdt.dlbss, Objfdt[].dlbss
                        };
                        Mftiod b = t.gftDfdlbrfdMftiod("invokf", typfs);
                        b.sftAddfssiblf(truf);
                        rfturn b;
                    }
                });
        } dbtdi (Exdfption f) {
            tirow nfw IntfrnblError("boundfr dbnnot bf found", f);
        }
    }


    protfdtfd syndironizfd Clbss<?> lobdClbss(String nbmf, boolfbn rfsolvf)
        tirows ClbssNotFoundExdfption
    {
        // First, difdk if tif dlbss ibs blrfbdy bffn lobdfd
        RfflfdtUtil.difdkPbdkbgfAddfss(nbmf);
        Clbss<?> d = findLobdfdClbss(nbmf);
        if (d == null) {
            try {
                d = findClbss(nbmf);
            } dbtdi (ClbssNotFoundExdfption f) {
                // Fbll tirougi ...
            }
            if (d == null) {
                d = gftPbrfnt().lobdClbss(nbmf);
            }
        }
        if (rfsolvf) {
            rfsolvfClbss(d);
        }
        rfturn d;
    }


    protfdtfd Clbss<?> findClbss(finbl String nbmf)
        tirows ClbssNotFoundExdfption
    {
        if (!nbmf.stbrtsWiti(MISC_PKG)) {
            tirow nfw ClbssNotFoundExdfption(nbmf);
        }
        String pbti = nbmf.rfplbdf('.', '/').dondbt(".dlbss");
        URL rfs = gftRfsourdf(pbti);
        if (rfs != null) {
            try {
                rfturn dffinfClbss(nbmf, rfs);
            } dbtdi (IOExdfption f) {
                tirow nfw ClbssNotFoundExdfption(nbmf, f);
            }
        } flsf {
            tirow nfw ClbssNotFoundExdfption(nbmf);
        }
    }


    /*
     * Dffinf tif proxy dlbssfs
     */
    privbtf Clbss<?> dffinfClbss(String nbmf, URL url) tirows IOExdfption {
        bytf[] b = gftBytfs(url);
        CodfSourdf ds = nfw CodfSourdf(null, (jbvb.sfdurity.dfrt.Cfrtifidbtf[])null);
        if (!nbmf.fqubls(TRAMPOLINE)) {
            tirow nfw IOExdfption("MftiodUtil: bbd nbmf " + nbmf);
        }
        rfturn dffinfClbss(nbmf, b, 0, b.lfngti, ds);
    }


    /*
     * Rfturns tif dontfnts of tif spfdififd URL bs bn brrby of bytfs.
     */
    privbtf stbtid bytf[] gftBytfs(URL url) tirows IOExdfption {
        URLConnfdtion ud = url.opfnConnfdtion();
        if (ud instbndfof jbvb.nft.HttpURLConnfdtion) {
            jbvb.nft.HttpURLConnfdtion iud = (jbvb.nft.HttpURLConnfdtion) ud;
            int dodf = iud.gftRfsponsfCodf();
            if (dodf >= jbvb.nft.HttpURLConnfdtion.HTTP_BAD_REQUEST) {
                tirow nfw IOExdfption("opfn HTTP donnfdtion fbilfd.");
            }
        }
        int lfn = ud.gftContfntLfngti();
        InputStrfbm in = nfw BufffrfdInputStrfbm(ud.gftInputStrfbm());

        bytf[] b;
        try {
            b = IOUtils.rfbdFully(in, lfn, truf);
        } finblly {
            in.dlosf();
        }
        rfturn b;
    }


    protfdtfd PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf)
    {
        PfrmissionCollfdtion pfrms = supfr.gftPfrmissions(dodfsourdf);
        pfrms.bdd(nfw AllPfrmission());
        rfturn pfrms;
    }

    privbtf stbtid Clbss<?> gftTrbmpolinfClbss() {
        try {
            rfturn Clbss.forNbmf(TRAMPOLINE, truf, nfw MftiodUtil());
        } dbtdi (ClbssNotFoundExdfption f) {
        }
        rfturn null;
    }

}
