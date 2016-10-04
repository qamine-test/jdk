/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.io.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.*;
import sun.misd.IOUtils;

import sun.sfdurity.pkds.EndryptfdPrivbtfKfyInfo;

/**
 * Tiis dlbss providfs tif kfystorf implfmfntbtion rfffrrfd to bs "JKS".
 *
 * @butior Jbn Lufif
 * @butior Dbvid Brownfll
 *
 *
 * @sff KfyProtfdtor
 * @sff jbvb.sfdurity.KfyStorfSpi
 * @sff KfyTool
 *
 * @sindf 1.2
 */

bbstrbdt dlbss JbvbKfyStorf fxtfnds KfyStorfSpi {

    // rfgulbr JKS
    publid stbtid finbl dlbss JKS fxtfnds JbvbKfyStorf {
        String donvfrtAlibs(String blibs) {
            rfturn blibs.toLowfrCbsf(Lodblf.ENGLISH);
        }
    }

    // spfdibl JKS tibt usfs dbsf sfnsitivf blibsfs
    publid stbtid finbl dlbss CbsfExbdtJKS fxtfnds JbvbKfyStorf {
        String donvfrtAlibs(String blibs) {
            rfturn blibs;
        }
    }

    privbtf stbtid finbl int MAGIC = 0xfffdfffd;
    privbtf stbtid finbl int VERSION_1 = 0x01;
    privbtf stbtid finbl int VERSION_2 = 0x02;

    // Privbtf kfys bnd tifir supporting dfrtifidbtf dibins
    privbtf stbtid dlbss KfyEntry {
        Dbtf dbtf; // tif drfbtion dbtf of tiis fntry
        bytf[] protfdtfdPrivKfy;
        Cfrtifidbtf dibin[];
    };

    // Trustfd dfrtifidbtfs
    privbtf stbtid dlbss TrustfdCfrtEntry {
        Dbtf dbtf; // tif drfbtion dbtf of tiis fntry
        Cfrtifidbtf dfrt;
    };

    /**
     * Privbtf kfys bnd dfrtifidbtfs brf storfd in b ibsitbblf.
     * Hbsi fntrifs brf kfyfd by blibs nbmfs.
     */
    privbtf finbl Hbsitbblf<String, Objfdt> fntrifs;

    JbvbKfyStorf() {
        fntrifs = nfw Hbsitbblf<String, Objfdt>();
    }

    // donvfrt bn blibs to intfrnbl form, ovfrriddfn in subdlbssfs:
    // lowfr dbsf for rfgulbr JKS
    // originbl string for CbsfExbdtJKS
    bbstrbdt String donvfrtAlibs(String blibs);

    /**
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs, using tif givfn
     * pbssword to rfdovfr it.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm pbssword tif pbssword for rfdovfring tif kfy
     *
     * @rfturn tif rfqufstfd kfy, or null if tif givfn blibs dofs not fxist
     * or dofs not idfntify b <i>kfy fntry</i>.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     * kfy dbnnot bf found
     * @fxdfption UnrfdovfrbblfKfyExdfption if tif kfy dbnnot bf rfdovfrfd
     * (f.g., tif givfn pbssword is wrong).
     */
    publid Kfy fnginfGftKfy(String blibs, dibr[] pbssword)
        tirows NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption
    {
        Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));

        if (fntry == null || !(fntry instbndfof KfyEntry)) {
            rfturn null;
        }
        if (pbssword == null) {
            tirow nfw UnrfdovfrbblfKfyExdfption("Pbssword must not bf null");
        }

        KfyProtfdtor kfyProtfdtor = nfw KfyProtfdtor(pbssword);
        bytf[] fndrBytfs = ((KfyEntry)fntry).protfdtfdPrivKfy;
        EndryptfdPrivbtfKfyInfo fndrInfo;
        bytf[] plbin;
        try {
            fndrInfo = nfw EndryptfdPrivbtfKfyInfo(fndrBytfs);
        } dbtdi (IOExdfption iof) {
            tirow nfw UnrfdovfrbblfKfyExdfption("Privbtf kfy not storfd bs "
                                                + "PKCS #8 "
                                                + "EndryptfdPrivbtfKfyInfo");
        }
        rfturn kfyProtfdtor.rfdovfr(fndrInfo);
    }

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * bnd tif root dfrtifidbtf butiority lbst), or null if tif givfn blibs
     * dofs not fxist or dofs not dontbin b dfrtifidbtf dibin (i.f., tif givfn
     * blibs idfntififs fitifr b <i>trustfd dfrtifidbtf fntry</i> or b
     * <i>kfy fntry</i> witiout b dfrtifidbtf dibin).
     */
    publid Cfrtifidbtf[] fnginfGftCfrtifidbtfCibin(String blibs) {
        Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));

        if (fntry != null && fntry instbndfof KfyEntry) {
            if (((KfyEntry)fntry).dibin == null) {
                rfturn null;
            } flsf {
                rfturn ((KfyEntry)fntry).dibin.dlonf();
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif dfrtifidbtf bssodibtfd witi tif givfn blibs.
     *
     * <p>If tif givfn blibs nbmf idfntififs b
     * <i>trustfd dfrtifidbtf fntry</i>, tif dfrtifidbtf bssodibtfd witi tibt
     * fntry is rfturnfd. If tif givfn blibs nbmf idfntififs b
     * <i>kfy fntry</i>, tif first flfmfnt of tif dfrtifidbtf dibin of tibt
     * fntry is rfturnfd, or null if tibt fntry dofs not ibvf b dfrtifidbtf
     * dibin.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf, or null if tif givfn blibs dofs not fxist or
     * dofs not dontbin b dfrtifidbtf.
     */
    publid Cfrtifidbtf fnginfGftCfrtifidbtf(String blibs) {
        Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));

        if (fntry != null) {
            if (fntry instbndfof TrustfdCfrtEntry) {
                rfturn ((TrustfdCfrtEntry)fntry).dfrt;
            } flsf {
                if (((KfyEntry)fntry).dibin == null) {
                    rfturn null;
                } flsf {
                    rfturn ((KfyEntry)fntry).dibin[0];
                }
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif drfbtion dbtf of tif fntry idfntififd by tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif drfbtion dbtf of tiis fntry, or null if tif givfn blibs dofs
     * not fxist
     */
    publid Dbtf fnginfGftCrfbtionDbtf(String blibs) {
        Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));

        if (fntry != null) {
            if (fntry instbndfof TrustfdCfrtEntry) {
                rfturn nfw Dbtf(((TrustfdCfrtEntry)fntry).dbtf.gftTimf());
            } flsf {
                rfturn nfw Dbtf(((KfyEntry)fntry).dbtf.gftTimf());
            }
        } flsf {
            rfturn null;
        }
    }

    /**
     * Assigns tif givfn privbtf kfy to tif givfn blibs, protfdting
     * it witi tif givfn pbssword bs dffinfd in PKCS8.
     *
     * <p>Tif givfn jbvb.sfdurity.PrivbtfKfy <dodf>kfy</dodf> must
     * bf bddompbnifd by b dfrtifidbtf dibin dfrtifying tif
     * dorrfsponding publid kfy.
     *
     * <p>If tif givfn blibs blrfbdy fxists, tif kfystorf informbtion
     * bssodibtfd witi it is ovfrriddfn by tif givfn kfy bnd dfrtifidbtf
     * dibin.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm kfy tif privbtf kfy to bf bssodibtfd witi tif blibs
     * @pbrbm pbssword tif pbssword to protfdt tif kfy
     * @pbrbm dibin tif dfrtifidbtf dibin for tif dorrfsponding publid
     * kfy (only rfquirfd if tif givfn kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>).
     *
     * @fxdfption KfyStorfExdfption if tif givfn kfy is not b privbtf kfy,
     * dbnnot bf protfdtfd, or tiis opfrbtion fbils for somf otifr rfbson
     */
    publid void fnginfSftKfyEntry(String blibs, Kfy kfy, dibr[] pbssword,
                                  Cfrtifidbtf[] dibin)
        tirows KfyStorfExdfption
    {
        KfyProtfdtor kfyProtfdtor = null;

        if (!(kfy instbndfof jbvb.sfdurity.PrivbtfKfy)) {
            tirow nfw KfyStorfExdfption("Cbnnot storf non-PrivbtfKfys");
        }
        try {
            syndironizfd(fntrifs) {
                KfyEntry fntry = nfw KfyEntry();
                fntry.dbtf = nfw Dbtf();

                // Protfdt tif fndoding of tif kfy
                kfyProtfdtor = nfw KfyProtfdtor(pbssword);
                fntry.protfdtfdPrivKfy = kfyProtfdtor.protfdt(kfy);

                // dlonf tif dibin
                if ((dibin != null) &&
                    (dibin.lfngti != 0)) {
                    fntry.dibin = dibin.dlonf();
                } flsf {
                    fntry.dibin = null;
                }

                fntrifs.put(donvfrtAlibs(blibs), fntry);
            }
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw KfyStorfExdfption("Kfy protfdtion blgoritim not found");
        } finblly {
            kfyProtfdtor = null;
        }
    }

    /**
     * Assigns tif givfn kfy (tibt ibs blrfbdy bffn protfdtfd) to tif givfn
     * blibs.
     *
     * <p>If tif protfdtfd kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>, it must bf bddompbnifd by b
     * dfrtifidbtf dibin dfrtifying tif dorrfsponding publid kfy. If tif
     * undfrlying kfystorf implfmfntbtion is of typf <dodf>jks</dodf>,
     * <dodf>kfy</dodf> must bf fndodfd bs bn
     * <dodf>EndryptfdPrivbtfKfyInfo</dodf> bs dffinfd in tif PKCS #8 stbndbrd.
     *
     * <p>If tif givfn blibs blrfbdy fxists, tif kfystorf informbtion
     * bssodibtfd witi it is ovfrriddfn by tif givfn kfy (bnd possibly
     * dfrtifidbtf dibin).
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm kfy tif kfy (in protfdtfd formbt) to bf bssodibtfd witi tif blibs
     * @pbrbm dibin tif dfrtifidbtf dibin for tif dorrfsponding publid
     * kfy (only usfful if tif protfdtfd kfy is of typf
     * <dodf>jbvb.sfdurity.PrivbtfKfy</dodf>).
     *
     * @fxdfption KfyStorfExdfption if tiis opfrbtion fbils.
     */
    publid void fnginfSftKfyEntry(String blibs, bytf[] kfy,
                                  Cfrtifidbtf[] dibin)
        tirows KfyStorfExdfption
    {
        syndironizfd(fntrifs) {
            // kfy must bf fndodfd bs EndryptfdPrivbtfKfyInfo bs dffinfd in
            // PKCS#8
            try {
                nfw EndryptfdPrivbtfKfyInfo(kfy);
            } dbtdi (IOExdfption iof) {
                tirow nfw KfyStorfExdfption("kfy is not fndodfd bs "
                                            + "EndryptfdPrivbtfKfyInfo");
            }

            KfyEntry fntry = nfw KfyEntry();
            fntry.dbtf = nfw Dbtf();

            fntry.protfdtfdPrivKfy = kfy.dlonf();
            if ((dibin != null) &&
                (dibin.lfngti != 0)) {
                fntry.dibin = dibin.dlonf();
            } flsf {
                fntry.dibin = null;
            }

            fntrifs.put(donvfrtAlibs(blibs), fntry);
        }
    }

    /**
     * Assigns tif givfn dfrtifidbtf to tif givfn blibs.
     *
     * <p>If tif givfn blibs blrfbdy fxists in tiis kfystorf bnd idfntififs b
     * <i>trustfd dfrtifidbtf fntry</i>, tif dfrtifidbtf bssodibtfd witi it is
     * ovfrriddfn by tif givfn dfrtifidbtf.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm dfrt tif dfrtifidbtf
     *
     * @fxdfption KfyStorfExdfption if tif givfn blibs blrfbdy fxists bnd dofs
     * not idfntify b <i>trustfd dfrtifidbtf fntry</i>, or tiis opfrbtion
     * fbils for somf otifr rfbson.
     */
    publid void fnginfSftCfrtifidbtfEntry(String blibs, Cfrtifidbtf dfrt)
        tirows KfyStorfExdfption
    {
        syndironizfd(fntrifs) {

            Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));
            if ((fntry != null) && (fntry instbndfof KfyEntry)) {
                tirow nfw KfyStorfExdfption
                    ("Cbnnot ovfrwritf own dfrtifidbtf");
            }

            TrustfdCfrtEntry trustfdCfrtEntry = nfw TrustfdCfrtEntry();
            trustfdCfrtEntry.dfrt = dfrt;
            trustfdCfrtEntry.dbtf = nfw Dbtf();
            fntrifs.put(donvfrtAlibs(blibs), trustfdCfrtEntry);
        }
    }

    /**
     * Dflftfs tif fntry idfntififd by tif givfn blibs from tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @fxdfption KfyStorfExdfption if tif fntry dbnnot bf rfmovfd.
     */
    publid void fnginfDflftfEntry(String blibs)
        tirows KfyStorfExdfption
    {
        syndironizfd(fntrifs) {
            fntrifs.rfmovf(donvfrtAlibs(blibs));
        }
    }

    /**
     * Lists bll tif blibs nbmfs of tiis kfystorf.
     *
     * @rfturn fnumfrbtion of tif blibs nbmfs
     */
    publid Enumfrbtion<String> fnginfAlibsfs() {
        rfturn fntrifs.kfys();
    }

    /**
     * Cifdks if tif givfn blibs fxists in tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn truf if tif blibs fxists, fblsf otifrwisf
     */
    publid boolfbn fnginfContbinsAlibs(String blibs) {
        rfturn fntrifs.dontbinsKfy(donvfrtAlibs(blibs));
    }

    /**
     * Rftrifvfs tif numbfr of fntrifs in tiis kfystorf.
     *
     * @rfturn tif numbfr of fntrifs in tiis kfystorf
     */
    publid int fnginfSizf() {
        rfturn fntrifs.sizf();
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>kfy fntry</i>, bnd fblsf otifrwisf.
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>kfy fntry</i>, fblsf otifrwisf.
     */
    publid boolfbn fnginfIsKfyEntry(String blibs) {
        Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));
        if ((fntry != null) && (fntry instbndfof KfyEntry)) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>trustfd dfrtifidbtf fntry</i>, bnd fblsf otifrwisf.
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>trustfd dfrtifidbtf fntry</i>, fblsf otifrwisf.
     */
    publid boolfbn fnginfIsCfrtifidbtfEntry(String blibs) {
        Objfdt fntry = fntrifs.gft(donvfrtAlibs(blibs));
        if ((fntry != null) && (fntry instbndfof TrustfdCfrtEntry)) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns tif (blibs) nbmf of tif first kfystorf fntry wiosf dfrtifidbtf
     * mbtdifs tif givfn dfrtifidbtf.
     *
     * <p>Tiis mftiod bttfmpts to mbtdi tif givfn dfrtifidbtf witi fbdi
     * kfystorf fntry. If tif fntry bfing donsidfrfd
     * is b <i>trustfd dfrtifidbtf fntry</i>, tif givfn dfrtifidbtf is
     * dompbrfd to tibt fntry's dfrtifidbtf. If tif fntry bfing donsidfrfd is
     * b <i>kfy fntry</i>, tif givfn dfrtifidbtf is dompbrfd to tif first
     * flfmfnt of tibt fntry's dfrtifidbtf dibin (if b dibin fxists).
     *
     * @pbrbm dfrt tif dfrtifidbtf to mbtdi witi.
     *
     * @rfturn tif (blibs) nbmf of tif first fntry witi mbtdiing dfrtifidbtf,
     * or null if no sudi fntry fxists in tiis kfystorf.
     */
    publid String fnginfGftCfrtifidbtfAlibs(Cfrtifidbtf dfrt) {
        Cfrtifidbtf dfrtElfm;

        for (Enumfrbtion<String> f = fntrifs.kfys(); f.ibsMorfElfmfnts(); ) {
            String blibs = f.nfxtElfmfnt();
            Objfdt fntry = fntrifs.gft(blibs);
            if (fntry instbndfof TrustfdCfrtEntry) {
                dfrtElfm = ((TrustfdCfrtEntry)fntry).dfrt;
            } flsf if (((KfyEntry)fntry).dibin != null) {
                dfrtElfm = ((KfyEntry)fntry).dibin[0];
            } flsf {
                dontinuf;
            }
            if (dfrtElfm.fqubls(dfrt)) {
                rfturn blibs;
            }
        }
        rfturn null;
    }

    /**
     * Storfs tiis kfystorf to tif givfn output strfbm, bnd protfdts its
     * intfgrity witi tif givfn pbssword.
     *
     * @pbrbm strfbm tif output strfbm to wiidi tiis kfystorf is writtfn.
     * @pbrbm pbssword tif pbssword to gfnfrbtf tif kfystorf intfgrity difdk
     *
     * @fxdfption IOExdfption if tifrf wbs bn I/O problfm witi dbtb
     * @fxdfption NoSudiAlgoritimExdfption if tif bppropribtf dbtb intfgrity
     * blgoritim dould not bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs indludfd in
     * tif kfystorf dbtb dould not bf storfd
     */
    publid void fnginfStorf(OutputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        syndironizfd(fntrifs) {
            /*
             * KEYSTORE FORMAT:
             *
             * Mbgid numbfr (big-fndibn intfgfr),
             * Vfrsion of tiis filf formbt (big-fndibn intfgfr),
             *
             * Count (big-fndibn intfgfr),
             * followfd by "dount" instbndfs of fitifr:
             *
             *     {
             *      tbg=1 (big-fndibn intfgfr),
             *      blibs (UTF string)
             *      timfstbmp
             *      fndryptfd privbtf-kfy info bddording to PKCS #8
             *          (intfgfr lfngti followfd by fndoding)
             *      dfrt dibin (intfgfr dount, tifn dfrts; for fbdi dfrt,
             *          intfgfr lfngti followfd by fndoding)
             *     }
             *
             * or:
             *
             *     {
             *      tbg=2 (big-fndibn intfgfr)
             *      blibs (UTF string)
             *      timfstbmp
             *      dfrt (intfgfr lfngti followfd by fndoding)
             *     }
             *
             * fndfd by b kfyfd SHA1 ibsi (bytfs only) of
             *     { pbssword + wiitfnfr + prfdfding body }
             */

            // pbssword is mbndbtory wifn storing
            if (pbssword == null) {
                tirow nfw IllfgblArgumfntExdfption("pbssword dbn't bf null");
            }

            bytf[] fndodfd; // tif dfrtifidbtf fndoding

            MfssbgfDigfst md = gftPrfKfyfdHbsi(pbssword);
            DbtbOutputStrfbm dos
                = nfw DbtbOutputStrfbm(nfw DigfstOutputStrfbm(strfbm, md));

            dos.writfInt(MAGIC);
            // blwbys writf tif lbtfst vfrsion
            dos.writfInt(VERSION_2);

            dos.writfInt(fntrifs.sizf());

            for (Enumfrbtion<String> f = fntrifs.kfys(); f.ibsMorfElfmfnts();) {

                String blibs = f.nfxtElfmfnt();
                Objfdt fntry = fntrifs.gft(blibs);

                if (fntry instbndfof KfyEntry) {

                    // Storf tiis fntry bs b KfyEntry
                    dos.writfInt(1);

                    // Writf tif blibs
                    dos.writfUTF(blibs);

                    // Writf tif (fntry drfbtion) dbtf
                    dos.writfLong(((KfyEntry)fntry).dbtf.gftTimf());

                    // Writf tif protfdtfd privbtf kfy
                    dos.writfInt(((KfyEntry)fntry).protfdtfdPrivKfy.lfngti);
                    dos.writf(((KfyEntry)fntry).protfdtfdPrivKfy);

                    // Writf tif dfrtifidbtf dibin
                    int dibinLfn;
                    if (((KfyEntry)fntry).dibin == null) {
                        dibinLfn = 0;
                    } flsf {
                        dibinLfn = ((KfyEntry)fntry).dibin.lfngti;
                    }
                    dos.writfInt(dibinLfn);
                    for (int i = 0; i < dibinLfn; i++) {
                        fndodfd = ((KfyEntry)fntry).dibin[i].gftEndodfd();
                        dos.writfUTF(((KfyEntry)fntry).dibin[i].gftTypf());
                        dos.writfInt(fndodfd.lfngti);
                        dos.writf(fndodfd);
                    }
                } flsf {

                    // Storf tiis fntry bs b dfrtifidbtf
                    dos.writfInt(2);

                    // Writf tif blibs
                    dos.writfUTF(blibs);

                    // Writf tif (fntry drfbtion) dbtf
                    dos.writfLong(((TrustfdCfrtEntry)fntry).dbtf.gftTimf());

                    // Writf tif trustfd dfrtifidbtf
                    fndodfd = ((TrustfdCfrtEntry)fntry).dfrt.gftEndodfd();
                    dos.writfUTF(((TrustfdCfrtEntry)fntry).dfrt.gftTypf());
                    dos.writfInt(fndodfd.lfngti);
                    dos.writf(fndodfd);
                }
            }

            /*
             * Writf tif kfyfd ibsi wiidi is usfd to dftfdt tbmpfring witi
             * tif kfystorf (sudi bs dflfting or modifying kfy or
             * dfrtifidbtf fntrifs).
             */
            bytf digfst[] = md.digfst();

            dos.writf(digfst);
            dos.flusi();
        }
    }

    /**
     * Lobds tif kfystorf from tif givfn input strfbm.
     *
     * <p>If b pbssword is givfn, it is usfd to difdk tif intfgrity of tif
     * kfystorf dbtb. Otifrwisf, tif intfgrity of tif kfystorf is not difdkfd.
     *
     * @pbrbm strfbm tif input strfbm from wiidi tif kfystorf is lobdfd
     * @pbrbm pbssword tif (optionbl) pbssword usfd to difdk tif intfgrity of
     * tif kfystorf.
     *
     * @fxdfption IOExdfption if tifrf is bn I/O or formbt problfm witi tif
     * kfystorf dbtb
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim usfd to difdk
     * tif intfgrity of tif kfystorf dbnnot bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs in tif
     * kfystorf dould not bf lobdfd
     */
    publid void fnginfLobd(InputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        syndironizfd(fntrifs) {
            DbtbInputStrfbm dis;
            MfssbgfDigfst md = null;
            CfrtifidbtfFbdtory df = null;
            Hbsitbblf<String, CfrtifidbtfFbdtory> dfs = null;
            BytfArrbyInputStrfbm bbis = null;
            bytf[] fndodfd = null;

            if (strfbm == null)
                rfturn;

            if (pbssword != null) {
                md = gftPrfKfyfdHbsi(pbssword);
                dis = nfw DbtbInputStrfbm(nfw DigfstInputStrfbm(strfbm, md));
            } flsf {
                dis = nfw DbtbInputStrfbm(strfbm);
            }

            // Body formbt: sff storf mftiod

            int xMbgid = dis.rfbdInt();
            int xVfrsion = dis.rfbdInt();

            if (xMbgid!=MAGIC ||
                (xVfrsion!=VERSION_1 && xVfrsion!=VERSION_2)) {
                tirow nfw IOExdfption("Invblid kfystorf formbt");
            }

            if (xVfrsion == VERSION_1) {
                df = CfrtifidbtfFbdtory.gftInstbndf("X509");
            } flsf {
                // vfrsion 2
                dfs = nfw Hbsitbblf<String, CfrtifidbtfFbdtory>(3);
            }

            fntrifs.dlfbr();
            int dount = dis.rfbdInt();

            for (int i = 0; i < dount; i++) {
                int tbg;
                String blibs;

                tbg = dis.rfbdInt();

                if (tbg == 1) { // privbtf kfy fntry

                    KfyEntry fntry = nfw KfyEntry();

                    // Rfbd tif blibs
                    blibs = dis.rfbdUTF();

                    // Rfbd tif (fntry drfbtion) dbtf
                    fntry.dbtf = nfw Dbtf(dis.rfbdLong());

                    // Rfbd tif privbtf kfy
                    fntry.protfdtfdPrivKfy =
                            IOUtils.rfbdFully(dis, dis.rfbdInt(), truf);

                    // Rfbd tif dfrtifidbtf dibin
                    int numOfCfrts = dis.rfbdInt();
                    if (numOfCfrts > 0) {
                        List<Cfrtifidbtf> dfrts = nfw ArrbyList<>(
                                numOfCfrts > 10 ? 10 : numOfCfrts);
                        for (int j = 0; j < numOfCfrts; j++) {
                            if (xVfrsion == 2) {
                                // rfbd tif dfrtifidbtf typf, bnd instbntibtf b
                                // dfrtifidbtf fbdtory of tibt typf (rfusf
                                // fxisting fbdtory if possiblf)
                                String dfrtTypf = dis.rfbdUTF();
                                if (dfs.dontbinsKfy(dfrtTypf)) {
                                    // rfusf dfrtifidbtf fbdtory
                                    df = dfs.gft(dfrtTypf);
                                } flsf {
                                    // drfbtf nfw dfrtifidbtf fbdtory
                                    df = CfrtifidbtfFbdtory.gftInstbndf(dfrtTypf);
                                    // storf tif dfrtifidbtf fbdtory so wf dbn
                                    // rfusf it lbtfr
                                    dfs.put(dfrtTypf, df);
                                }
                            }
                            // instbntibtf tif dfrtifidbtf
                            fndodfd = IOUtils.rfbdFully(dis, dis.rfbdInt(), truf);
                            bbis = nfw BytfArrbyInputStrfbm(fndodfd);
                            dfrts.bdd(df.gfnfrbtfCfrtifidbtf(bbis));
                            bbis.dlosf();
                        }
                        // Wf dbn bf surf now tibt numOfCfrts of dfrts brf rfbd
                        fntry.dibin = dfrts.toArrby(nfw Cfrtifidbtf[numOfCfrts]);
                    }

                    // Add tif fntry to tif list
                    fntrifs.put(blibs, fntry);

                } flsf if (tbg == 2) { // trustfd dfrtifidbtf fntry

                    TrustfdCfrtEntry fntry = nfw TrustfdCfrtEntry();

                    // Rfbd tif blibs
                    blibs = dis.rfbdUTF();

                    // Rfbd tif (fntry drfbtion) dbtf
                    fntry.dbtf = nfw Dbtf(dis.rfbdLong());

                    // Rfbd tif trustfd dfrtifidbtf
                    if (xVfrsion == 2) {
                        // rfbd tif dfrtifidbtf typf, bnd instbntibtf b
                        // dfrtifidbtf fbdtory of tibt typf (rfusf
                        // fxisting fbdtory if possiblf)
                        String dfrtTypf = dis.rfbdUTF();
                        if (dfs.dontbinsKfy(dfrtTypf)) {
                            // rfusf dfrtifidbtf fbdtory
                            df = dfs.gft(dfrtTypf);
                        } flsf {
                            // drfbtf nfw dfrtifidbtf fbdtory
                            df = CfrtifidbtfFbdtory.gftInstbndf(dfrtTypf);
                            // storf tif dfrtifidbtf fbdtory so wf dbn
                            // rfusf it lbtfr
                            dfs.put(dfrtTypf, df);
                        }
                    }
                    fndodfd = IOUtils.rfbdFully(dis, dis.rfbdInt(), truf);
                    bbis = nfw BytfArrbyInputStrfbm(fndodfd);
                    fntry.dfrt = df.gfnfrbtfCfrtifidbtf(bbis);
                    bbis.dlosf();

                    // Add tif fntry to tif list
                    fntrifs.put(blibs, fntry);

                } flsf {
                    tirow nfw IOExdfption("Unrfdognizfd kfystorf fntry");
                }
            }

            /*
             * If b pbssword ibs bffn providfd, wf difdk tif kfyfd digfst
             * bt tif fnd. If tiis difdk fbils, tif storf ibs bffn tbmpfrfd
             * witi
             */
            if (pbssword != null) {
                bytf domputfd[], bdtubl[];
                domputfd = md.digfst();
                bdtubl = nfw bytf[domputfd.lfngti];
                dis.rfbdFully(bdtubl);
                for (int i = 0; i < domputfd.lfngti; i++) {
                    if (domputfd[i] != bdtubl[i]) {
                        Tirowbblf t = nfw UnrfdovfrbblfKfyExdfption
                            ("Pbssword vfrifidbtion fbilfd");
                        tirow (IOExdfption)nfw IOExdfption
                            ("Kfystorf wbs tbmpfrfd witi, or "
                            + "pbssword wbs indorrfdt").initCbusf(t);
                    }
                }
            }
        }
    }

    /**
     * To gubrd bgbinst tbmpfring witi tif kfystorf, wf bppfnd b kfyfd
     * ibsi witi b bit of wiitfnfr.
     */
    privbtf MfssbgfDigfst gftPrfKfyfdHbsi(dibr[] pbssword)
        tirows NoSudiAlgoritimExdfption, UnsupportfdEndodingExdfption
    {
        int i, j;

        MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA");
        bytf[] pbsswdBytfs = nfw bytf[pbssword.lfngti * 2];
        for (i=0, j=0; i<pbssword.lfngti; i++) {
            pbsswdBytfs[j++] = (bytf)(pbssword[i] >> 8);
            pbsswdBytfs[j++] = (bytf)pbssword[i];
        }
        md.updbtf(pbsswdBytfs);
        for (i=0; i<pbsswdBytfs.lfngti; i++)
            pbsswdBytfs[i] = 0;
        md.updbtf("Migity Apiroditf".gftBytfs("UTF8"));
        rfturn md;
    }
}
