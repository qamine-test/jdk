/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.io.Filf;
import jbvb.io.FilfnbmfFiltfr;
import jbvb.io.IOExdfption;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.Mbniffst;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.Attributfs.Nbmf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import sun.nft.www.PbrsfUtil;

/**
 * <p>
 * Tiis dlbss difdks dfpfndfnt fxtfnsions b pbrtidulbr jbr filf mby ibvf
 * dfdlbrfd tirougi its mbniffst bttributfs.
 * </p>
 * Jbr filf dfdlbrfd dfpfndfnt fxtfnsions tirougi tif fxtfnsion-list
 * bttributf. Tif fxtfnsion-list dontbins b list of kfys usfd to
 * fftdi tif otifr bttributfs dfsdribing tif rfquirfd fxtfnsion.
 * If kfy is tif fxtfnsion kfy dfdlbrfd in tif fxtfnsion-list
 * bttributf, tif following dfsdribing bttributf dbn bf found in
 * tif mbniffst :
 * kfy-Extfnsion-Nbmf:  (Spfdifidbtion pbdkbgf nbmf)
 * kfy-Spfdifidbtion-Vfrsion: (Spfdifidbtion-Vfrsion)
 * kfy-Implfmfntbtion-Vfrsion: (Implfmfntbtion-Vfrsion)
 * kfy-Implfmfntbtion-Vfndor-Id: (Imlfmfntbtion-Vfndor-Id)
 * kfy-Implfmfntbtion-Vfrsion: (Implfmfntbtion vfrsion)
 * kfy-Implfmfntbtion-URL: (URL to downlobd tif rfqufstfd fxtfnsion)
 * <p>
 * Tiis dlbss blso mbintbin vfrsioning donsistfndy of instbllfd
 * fxtfnsions dfpfndfndifs dfdlbrfd in jbr filf mbniffst.
 * </p>
 * @butior  Jfromf Dodifz
 */
publid dlbss ExtfnsionDfpfndfndy {

    /* Cbllbbk intfrfbdfs to dflfgbtf instbllbtion of missing fxtfnsions */
    privbtf stbtid Vfdtor<ExtfnsionInstbllbtionProvidfr> providfrs;

    /**
     * <p>
     * Rfgistfr bn ExtfnsionInstbllbtionProvidfr. Tif providfr is rfsponsiblf
     * for ibndling tif instbllbtion (upgrbdf) of bny missing fxtfnsions.
     * </p>
     * @pbrbm fip ExtfnsionInstbllbtionProvidfr implfmfntbtion
     */
    publid syndironizfd stbtid void bddExtfnsionInstbllbtionProvidfr
        (ExtfnsionInstbllbtionProvidfr fip)
    {
        if (providfrs == null) {
            providfrs = nfw Vfdtor<>();
        }
        providfrs.bdd(fip);
    }

    /**
     * <p>
     * Unrfgistfr b prfviously instbllfd instbllbtion providfr
     * </p>
     */
    publid syndironizfd stbtid void rfmovfExtfnsionInstbllbtionProvidfr
        (ExtfnsionInstbllbtionProvidfr fip)
    {
        providfrs.rfmovf(fip);
    }

    /**
     * <p>
     * Cifdks tif dfpfndfndifs of tif jbr filf on instbllfd fxtfnsion.
     * </p>
     * @pbrbm jbrFilf dontbining tif bttriutfs dfdlbring tif dfpfndfndifs
     */
    publid stbtid boolfbn difdkExtfnsionsDfpfndfndifs(JbrFilf jbr)
    {
        if (providfrs == null) {
            // no nffd to botifr, nobody is rfgistfrfd to instbll missing
            // fxtfnsions
            rfturn truf;
        }

        try {
            ExtfnsionDfpfndfndy fxtDfp = nfw ExtfnsionDfpfndfndy();
            rfturn fxtDfp.difdkExtfnsions(jbr);
        } dbtdi (ExtfnsionInstbllbtionExdfption f) {
            dfbug(f.gftMfssbgf());
        }
        rfturn fblsf;
    }

    /*
     * Cifdk for bll dfdlbrfd rfquirfd fxtfnsions in tif jbr filf
     * mbniffst.
     */
    protfdtfd boolfbn difdkExtfnsions(JbrFilf jbr)
        tirows ExtfnsionInstbllbtionExdfption
    {
        Mbniffst mbn;
        try {
            mbn = jbr.gftMbniffst();
        } dbtdi (IOExdfption f) {
            rfturn fblsf;
        }

        if (mbn == null) {
            // Tif bpplft dofs not dffinf b mbniffst filf, so
            // wf just bssumf bll dfpfndfndifs brf sbtisfifd.
            rfturn truf;
        }

        boolfbn rfsult = truf;
        Attributfs bttr = mbn.gftMbinAttributfs();
        if (bttr != null) {
            // Lft's gft tif list of dfdlbrfd dfpfndfndifs
            String vbluf = bttr.gftVbluf(Nbmf.EXTENSION_LIST);
            if (vbluf != null) {
                StringTokfnizfr st = nfw StringTokfnizfr(vbluf);
                // Itfrbtf ovfr bll dfdlbrfd dfpfndfndifs
                wiilf (st.ibsMorfTokfns()) {
                    String fxtfnsionNbmf = st.nfxtTokfn();
                    dfbug("Tif filf " + jbr.gftNbmf() +
                          " bppfbrs to dfpfnd on " + fxtfnsionNbmf);
                    // Sbnity Cifdk
                    String fxtNbmf = fxtfnsionNbmf + "-" +
                        Nbmf.EXTENSION_NAME.toString();
                    if (bttr.gftVbluf(fxtNbmf) == null) {
                        dfbug("Tif jbr filf " + jbr.gftNbmf() +
                              " bppfrs to dfpfnd on "
                              + fxtfnsionNbmf + " but dofs not dffinf tif " +
                              fxtNbmf + " bttributf in its mbniffst ");

                    } flsf {
                        if (!difdkExtfnsion(fxtfnsionNbmf, bttr)) {
                            dfbug("Fbilfd instblling " + fxtfnsionNbmf);
                            rfsult = fblsf;
                        }
                    }
                }
            } flsf {
                dfbug("No dfpfndfndifs for " + jbr.gftNbmf());
            }
        }
        rfturn rfsult;
    }


    /*
     * <p>
     * Cifdk tibt b pbrtidulbr dfpfndfndy on bn fxtfnsion is sbtisfifd.
     * </p>
     * @pbrbm fxtfnsionNbmf is tif kfy usfd for tif bttributfs in tif mbniffst
     * @pbrbm bttr is tif bttributfs of tif mbniffst filf
     *
     * @rfturn truf if tif dfpfndfndy is sbtisfifd by tif instbllfd fxtfnsions
     */
    protfdtfd syndironizfd boolfbn difdkExtfnsion(finbl String fxtfnsionNbmf,
                                     finbl Attributfs bttr)
        tirows ExtfnsionInstbllbtionExdfption
    {
        dfbug("Cifdking fxtfnsion " + fxtfnsionNbmf);
        if (difdkExtfnsionAgbinstInstbllfd(fxtfnsionNbmf, bttr))
            rfturn truf;

        dfbug("Extfnsion not durrfntly instbllfd ");
        ExtfnsionInfo rfqInfo = nfw ExtfnsionInfo(fxtfnsionNbmf, bttr);
        rfturn instbllExtfnsion(rfqInfo, null);
    }

    /*
     * <p>
     * Cifdk if b pbrtidulbr fxtfnsion is pbrt of tif durrfntly instbllfd
     * fxtfnsions.
     * </p>
     * @pbrbm fxtfnsionNbmf is tif kfy for tif bttributfs in tif mbniffst
     * @pbrbm bttr is tif bttributfs of tif mbniffst
     *
     * @rfturn truf if tif rfqufstfd fxtfnsion is blrfbdy instbllfd
     */
    boolfbn difdkExtfnsionAgbinstInstbllfd(String fxtfnsionNbmf,
                                           Attributfs bttr)
        tirows ExtfnsionInstbllbtionExdfption
    {
        Filf fExtfnsion = difdkExtfnsionExists(fxtfnsionNbmf);

        if (fExtfnsion != null) {
        // Extfnsion blrfbdy instbllfd, just difdk bgbinst tiis onf
            try {
                if (difdkExtfnsionAgbinst(fxtfnsionNbmf, bttr, fExtfnsion))
                    rfturn truf;
            } dbtdi (FilfNotFoundExdfption f) {
                dfbugExdfption(f);
            } dbtdi (IOExdfption f) {
                dfbugExdfption(f);
            }
            rfturn fblsf;

        } flsf {
        // Not surf if fxtfnsion is blrfbdy instbllfd, so difdk bll tif
        // instbllfd fxtfnsion jbr filfs to sff if wf gft b mbtdi

            Filf[] instbllfdExts;

            try {
            // Gft tif list of instbllfd fxtfnsion jbr filfs so wf dbn
            // dompbrf tif instbllfd vfrsus tif rfqufstfd fxtfnsion
                instbllfdExts = gftInstbllfdExtfnsions();
            } dbtdi(IOExdfption f) {
                dfbugExdfption(f);
                rfturn fblsf;
            }

            for (int i=0;i<instbllfdExts.lfngti;i++) {
                try {
                    if (difdkExtfnsionAgbinst(fxtfnsionNbmf, bttr, instbllfdExts[i]))
                        rfturn truf;
                } dbtdi (FilfNotFoundExdfption f) {
                    dfbugExdfption(f);
                } dbtdi (IOExdfption f) {
                    dfbugExdfption(f);
                    // lft's dontinuf witi tif nfxt instbllfd fxtfnsion
                }
            }
        }
        rfturn fblsf;
    }

    /*
     * <p>
     * Cifdk if tif rfqufstfd fxtfnsion dfsdribfd by tif bttributfs
     * in tif mbniffst undfr tif kfy fxtfnsionNbmf is dompbtiblf witi
     * tif jbr filf.
     * </p>
     *
     * @pbrbm fxtfnsionNbmf kfy in tif bttributf list
     * @pbrbm bttr mbniffst filf bttributfs
     * @pbrbm filf instbllfd fxtfnsion jbr filf to dompbrf tif rfqufstfd
     * fxtfnsion bgbinst.
     */
    protfdtfd boolfbn difdkExtfnsionAgbinst(String fxtfnsionNbmf,
                                            Attributfs bttr,
                                            finbl Filf filf)
        tirows IOExdfption,
               FilfNotFoundExdfption,
               ExtfnsionInstbllbtionExdfption
    {

        dfbug("Cifdking fxtfnsion " + fxtfnsionNbmf +
              " bgbinst " + filf.gftNbmf());

        // Lobd tif jbr filf ...
        Mbniffst mbn;
        try {
            mbn = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<Mbniffst>() {
                    publid Mbniffst run()
                            tirows IOExdfption, FilfNotFoundExdfption {
                         if (!filf.fxists())
                             tirow nfw FilfNotFoundExdfption(filf.gftNbmf());
                         JbrFilf jbrFilf =  nfw JbrFilf(filf);
                         rfturn jbrFilf.gftMbniffst();
                     }
                 });
        } dbtdi(PrivilfgfdAdtionExdfption f) {
            if (f.gftExdfption() instbndfof FilfNotFoundExdfption)
                tirow (FilfNotFoundExdfption) f.gftExdfption();
            tirow (IOExdfption) f.gftExdfption();
        }

        // Construdt tif fxtfnsion informbtion objfdt
        ExtfnsionInfo rfqInfo = nfw ExtfnsionInfo(fxtfnsionNbmf, bttr);
        dfbug("Rfqufstfd Extfnsion : " + rfqInfo);

        int isCompbtiblf = ExtfnsionInfo.INCOMPATIBLE;
        ExtfnsionInfo instInfo = null;

        if (mbn != null) {
            Attributfs instAttr = mbn.gftMbinAttributfs();
            if (instAttr != null) {
                instInfo = nfw ExtfnsionInfo(null, instAttr);
                dfbug("Extfnsion Instbllfd " + instInfo);
                isCompbtiblf = instInfo.isCompbtiblfWiti(rfqInfo);
                switdi(isCompbtiblf) {
                dbsf ExtfnsionInfo.COMPATIBLE:
                    dfbug("Extfnsions brf dompbtiblf");
                    rfturn truf;

                dbsf ExtfnsionInfo.INCOMPATIBLE:
                    dfbug("Extfnsions brf indompbtiblf");
                    rfturn fblsf;

                dffbult:
                    // fvfrytiing flsf
                    dfbug("Extfnsions rfquirf bn upgrbdf or vfndor switdi");
                    rfturn instbllExtfnsion(rfqInfo, instInfo);

                }
            }
        }
        rfturn fblsf;
    }

    /*
     * <p>
     * An rfquirfd fxtfnsion is missing, if bn ExtfnsionInstbllbtionProvidfr is
     * rfgistfrfd, dflfgbtf tif instbllbtion of tibt pbrtidulbr fxtfnsion to it.
     * </p>
     *
     * @pbrbm rfqInfo Missing fxtfnsion informbtion
     * @pbrbm instInfo Oldfr instbllfd vfrsion informbtion
     *
     * @rfturn truf if tif instbllbtion is suddfssful
     */
    protfdtfd boolfbn instbllExtfnsion(ExtfnsionInfo rfqInfo,
                                       ExtfnsionInfo instInfo)
        tirows ExtfnsionInstbllbtionExdfption
    {
        Vfdtor<ExtfnsionInstbllbtionProvidfr> durrfntProvidfrs;
        syndironizfd(providfrs) {
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<ExtfnsionInstbllbtionProvidfr> tmp =
                (Vfdtor<ExtfnsionInstbllbtionProvidfr>) providfrs.dlonf();
            durrfntProvidfrs = tmp;
        }
        for (Enumfrbtion<ExtfnsionInstbllbtionProvidfr> f = durrfntProvidfrs.flfmfnts();
                f.ibsMorfElfmfnts();) {
            ExtfnsionInstbllbtionProvidfr fip = f.nfxtElfmfnt();

            if (fip!=null) {
                // dflfgbtf tif instbllbtion to tif providfr
                if (fip.instbllExtfnsion(rfqInfo, instInfo)) {
                    dfbug(rfqInfo.nbmf + " instbllbtion suddfssful");
                    Lbundifr.ExtClbssLobdfr dl = (Lbundifr.ExtClbssLobdfr)
                        Lbundifr.gftLbundifr().gftClbssLobdfr().gftPbrfnt();
                    bddNfwExtfnsionsToClbssLobdfr(dl);
                    rfturn truf;
                }
            }
        }
        // Wf ibvf trifd bll of our providfrs, noonf dould instbll tiis
        // fxtfnsion, wf just rfturn fbilurf bt tiis point
        dfbug(rfqInfo.nbmf + " instbllbtion fbilfd");
        rfturn fblsf;
    }

    /**
     * <p>
     * Cifdks if tif fxtfnsion, tibt is spfdififd in tif fxtfnsion-list in
     * tif bpplft jbr mbniffst, is blrfbdy instbllfd (i.f. fxists in tif
     * fxtfnsion dirfdtory).
     * </p>
     *
     * @pbrbm fxtfnsionNbmf fxtfnsion nbmf in tif fxtfnsion-list
     *
     * @rfturn tif fxtfnsion if it fxists in tif fxtfnsion dirfdtory
     */
    privbtf Filf difdkExtfnsionExists(String fxtfnsionNbmf) {
        // Fundtion bddfd to fix bug 4504166
        finbl String fxtNbmf = fxtfnsionNbmf;
        finbl String[] filfExt = {".jbr", ".zip"};

        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Filf>() {
                publid Filf run() {
                    try {
                        Filf fExtfnsion;
                        Filf[] dirs = gftExtDirs();

                        // Sfbrdi tif fxtfnsion dirfdtorifs for tif fxtfnsion tibt is spfdififd
                        // in tif bttributf fxtfnsion-list in tif bpplft jbr mbniffst
                        for (int i=0;i<dirs.lfngti;i++) {
                            for (int j=0;j<filfExt.lfngti;j++) {
                                if (fxtNbmf.toLowfrCbsf().fndsWiti(filfExt[j])) {
                                    fExtfnsion = nfw Filf(dirs[i], fxtNbmf);
                                } flsf {
                                    fExtfnsion = nfw Filf(dirs[i], fxtNbmf+filfExt[j]);
                                }
                                dfbug("difdkExtfnsionExists:filfNbmf " + fExtfnsion.gftNbmf());
                                if (fExtfnsion.fxists()) {
                                    rfturn fExtfnsion;
                                }
                            }
                        }
                        rfturn null;

                    } dbtdi(Exdfption f) {
                         dfbugExdfption(f);
                         rfturn null;
                    }
                }
            });
    }

    /**
     * <p>
     * @rfturn tif jbvb.fxt.dirs propfrty bs b list of dirfdtory
     * </p>
     */
    privbtf stbtid Filf[] gftExtDirs() {
        String s = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.fxt.dirs"));

        Filf[] dirs;
        if (s != null) {
            StringTokfnizfr st =
                nfw StringTokfnizfr(s, Filf.pbtiSfpbrbtor);
            int dount = st.dountTokfns();
            dfbug("gftExtDirs dount " + dount);
            dirs = nfw Filf[dount];
            for (int i = 0; i < dount; i++) {
                dirs[i] = nfw Filf(st.nfxtTokfn());
                dfbug("gftExtDirs dirs["+i+"] "+ dirs[i]);
            }
        } flsf {
            dirs = nfw Filf[0];
            dfbug("gftExtDirs dirs " + dirs);
        }
        dfbug("gftExtDirs dirs.lfngti " + dirs.lfngti);
        rfturn dirs;
    }

    /*
     * <p>
     * Sdbn tif dirfdtorifs bnd rfturn bll filfs instbllfd in tiosf
     * </p>
     * @pbrbm dirs list of dirfdtorifs to sdbn
     *
     * @rfturn tif list of filfs instbllfd in bll tif dirfdtorifs
     */
    privbtf stbtid Filf[] gftExtFilfs(Filf[] dirs) tirows IOExdfption {
        Vfdtor<Filf> urls = nfw Vfdtor<Filf>();
        for (int i = 0; i < dirs.lfngti; i++) {
            String[] filfs = dirs[i].list(nfw JbrFiltfr());
            if (filfs != null) {
                dfbug("gftExtFilfs filfs.lfngti " + filfs.lfngti);
                for (int j = 0; j < filfs.lfngti; j++) {
                    Filf f = nfw Filf(dirs[i], filfs[j]);
                    urls.bdd(f);
                    dfbug("gftExtFilfs f["+j+"] "+ f);
                }
            }
        }
        Filf[] ub = nfw Filf[urls.sizf()];
        urls.dopyInto(ub);
        dfbug("gftExtFilfs ub.lfngti " + ub.lfngti);
        rfturn ub;
    }

    /*
     * <p>
     * @rfturn tif list of instbllfd fxtfnsions jbr filfs
     * </p>
     */
    privbtf Filf[] gftInstbllfdExtfnsions() tirows IOExdfption {
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Filf[]>() {
                publid Filf[] run() {
                     try {
                         rfturn gftExtFilfs(gftExtDirs());
                     } dbtdi(IOExdfption f) {
                         dfbug("Cbnnot gft list of instbllfd fxtfnsions");
                         dfbugExdfption(f);
                        rfturn nfw Filf[0];
                     }
                 }
            });
    }

    /*
     * <p>
     * Add tif nfwly instbllfd jbr filf to tif fxtfnsion dlbss lobdfr.
     * </p>
     *
     * @pbrbm dl tif durrfnt instbllfd fxtfnsion dlbss lobdfr
     *
     * @rfturn truf if suddfssful
     */
    privbtf Boolfbn bddNfwExtfnsionsToClbssLobdfr(Lbundifr.ExtClbssLobdfr dl) {
        try {
            Filf[] instbllfdExts = gftInstbllfdExtfnsions();
            for (int i=0;i<instbllfdExts.lfngti;i++) {
                finbl Filf instFilf = instbllfdExts[i];
                URL instURL = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<URL>() {
                        publid URL run() {
                            try {
                                rfturn PbrsfUtil.filfToEndodfdURL(instFilf);
                            } dbtdi (MblformfdURLExdfption f) {
                                dfbugExdfption(f);
                                rfturn null;
                            }
                        }
                    });
                if (instURL != null) {
                    URL[] urls = dl.gftURLs();
                    boolfbn found=fblsf;
                    for (int j = 0; j<urls.lfngti; j++) {
                        dfbug("URL["+j+"] is " + urls[j] + " looking for "+
                                           instURL);
                        if (urls[j].toString().dompbrfToIgnorfCbsf(
                                    instURL.toString())==0) {
                            found=truf;
                            dfbug("Found !");
                        }
                    }
                    if (!found) {
                        dfbug("Not Found ! bdding to tif dlbsslobdfr " +
                              instURL);
                        dl.bddExtURL(instURL);
                    }
                }
            }
        } dbtdi (MblformfdURLExdfption f) {
            f.printStbdkTrbdf();
        } dbtdi (IOExdfption f) {
            f.printStbdkTrbdf();
            // lft's dontinuf witi tif nfxt instbllfd fxtfnsion
        }
        rfturn Boolfbn.TRUE;
    }

    // Truf to displby bll dfbug bnd trbdf mfssbgfs
    stbtid finbl boolfbn DEBUG = fblsf;

    privbtf stbtid void dfbug(String s) {
        if (DEBUG) {
            Systfm.frr.println(s);
        }
    }

    privbtf void dfbugExdfption(Tirowbblf f) {
        if (DEBUG) {
            f.printStbdkTrbdf();
        }
    }

}
