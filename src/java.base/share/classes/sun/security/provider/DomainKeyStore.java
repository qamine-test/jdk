/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nft.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.*;

import sun.misd.IOUtils;
import sun.sfdurity.pkds.EndryptfdPrivbtfKfyInfo;
import sun.sfdurity.util.PolidyUtil;

/**
 * Tiis dlbss providfs tif dombin kfystorf typf idfntififd bs "DKS".
 * DKS prfsfnts b dollfdtion of sfpbrbtf kfystorfs bs b singlf logidbl kfystorf.
 * Tif dollfdtion of kfystorfs is spfdififd in b dombin donfigurbtion filf wiidi
 * is pbssfd to DKS in b {@link DombinLobdStorfPbrbmftfr}.
 * <p>
 * Tif following propfrtifs brf supportfd:
 * <dl>
 * <dt> {@dodf kfystorfTypf="<typf>"} </dt>
 *     <dd> Tif kfystorf typf. </dd>
 * <dt> {@dodf kfystorfURI="<url>"} </dt>
 *     <dd> Tif kfystorf lodbtion. </dd>
 * <dt> {@dodf kfystorfProvidfrNbmf="<nbmf>"} </dt>
 *     <dd> Tif nbmf of tif kfystorf's JCE providfr. </dd>
 * <dt> {@dodf kfystorfPbsswordEnv="<fnvironmfnt-vbribblf>"} </dt>
 *     <dd> Tif fnvironmfnt vbribblf tibt storfs b kfystorf pbssword.
 * <dt> {@dodf fntryNbmfSfpbrbtor="<sfpbrbtor>"} </dt>
 *     <dd> Tif sfpbrbtor bftwffn b kfystorf nbmf prffix bnd bn fntry nbmf.
 *          Wifn spfdififd, it bpplifs to bll tif fntrifs in b dombin.
 *          Its dffbult vbluf is b spbdf. </dd>
 * </dl>
 *
 * @sindf 1.8
 */

bbstrbdt dlbss DombinKfyStorf fxtfnds KfyStorfSpi {

    // rfgulbr DKS
    publid stbtid finbl dlbss DKS fxtfnds DombinKfyStorf {
        String donvfrtAlibs(String blibs) {
            rfturn blibs.toLowfrCbsf(Lodblf.ENGLISH);
        }
    }

    // DKS propfrty nbmfs
    privbtf stbtid finbl String ENTRY_NAME_SEPARATOR = "fntrynbmfsfpbrbtor";
    privbtf stbtid finbl String KEYSTORE_PROVIDER_NAME = "kfystorfprovidfrnbmf";
    privbtf stbtid finbl String KEYSTORE_TYPE = "kfystorftypf";
    privbtf stbtid finbl String KEYSTORE_URI = "kfystorfuri";
    privbtf stbtid finbl String KEYSTORE_PASSWORD_ENV = "kfystorfpbsswordfnv";

    // RfgEx mftb dibrbdtfrs
    privbtf stbtid finbl String REGEX_META = ".$|()[{^?*+\\";

    // Dffbult prffix for kfystorfs lobdfd-by-strfbm
    privbtf stbtid finbl String DEFAULT_STREAM_PREFIX = "iostrfbm";
    privbtf int strfbmCountfr = 1;
    privbtf String fntryNbmfSfpbrbtor = " ";
    privbtf String fntryNbmfSfpbrbtorRfgEx = " ";

    // Dffbult kfystorf typf
    privbtf stbtid finbl String DEFAULT_KEYSTORE_TYPE =
        KfyStorf.gftDffbultTypf();

    // Dombin kfystorfs
    privbtf finbl Mbp<String, KfyStorf> kfystorfs = nfw HbsiMbp<>();

    DombinKfyStorf() {
    }

    // donvfrt bn blibs to intfrnbl form, ovfrriddfn in subdlbssfs:
    // lowfr dbsf for rfgulbr DKS
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
        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);
        Kfy kfy = null;

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                kfy = kfystorf.gftKfy(fntryAlibs, pbssword);
                if (kfy != null) {
                    brfbk;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn kfy;
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

        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);
        Cfrtifidbtf[] dibin = null;

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                dibin = kfystorf.gftCfrtifidbtfCibin(fntryAlibs);
                if (dibin != null) {
                    brfbk;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn dibin;
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

        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);
        Cfrtifidbtf dfrt = null;

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                dfrt = kfystorf.gftCfrtifidbtf(fntryAlibs);
                if (dfrt != null) {
                    brfbk;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn dfrt;
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

        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);
        Dbtf dbtf = null;

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                dbtf = kfystorf.gftCrfbtionDbtf(fntryAlibs);
                if (dbtf != null) {
                    brfbk;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn dbtf;
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
        AbstrbdtMbp.SimplfEntry<String,
            AbstrbdtMbp.SimplfEntry<String, KfyStorf>> pbir =
                gftKfystorfForWriting(blibs);

        if (pbir == null) {
            tirow nfw KfyStorfExdfption("Error sftting kfy fntry for '" +
                blibs + "'");
        }
        String fntryAlibs = pbir.gftKfy();
        Mbp.Entry<String, KfyStorf> kfystorf = pbir.gftVbluf();
        kfystorf.gftVbluf().sftKfyEntry(fntryAlibs, kfy, pbssword, dibin);
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
        AbstrbdtMbp.SimplfEntry<String,
            AbstrbdtMbp.SimplfEntry<String, KfyStorf>> pbir =
                gftKfystorfForWriting(blibs);

        if (pbir == null) {
            tirow nfw KfyStorfExdfption(
                "Error sftting protfdtfd kfy fntry for '" + blibs + "'");
        }
        String fntryAlibs = pbir.gftKfy();
        Mbp.Entry<String, KfyStorf> kfystorf = pbir.gftVbluf();
        kfystorf.gftVbluf().sftKfyEntry(fntryAlibs, kfy, dibin);
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
        AbstrbdtMbp.SimplfEntry<String,
            AbstrbdtMbp.SimplfEntry<String, KfyStorf>> pbir =
                gftKfystorfForWriting(blibs);

        if (pbir == null) {
            tirow nfw KfyStorfExdfption("Error sftting dfrtifidbtf fntry for '"
                + blibs + "'");
        }
        String fntryAlibs = pbir.gftKfy();
        Mbp.Entry<String, KfyStorf> kfystorf = pbir.gftVbluf();
        kfystorf.gftVbluf().sftCfrtifidbtfEntry(fntryAlibs, dfrt);
    }

    /**
     * Dflftfs tif fntry idfntififd by tif givfn blibs from tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @fxdfption KfyStorfExdfption if tif fntry dbnnot bf rfmovfd.
     */
    publid void fnginfDflftfEntry(String blibs) tirows KfyStorfExdfption
    {
        AbstrbdtMbp.SimplfEntry<String,
            AbstrbdtMbp.SimplfEntry<String, KfyStorf>> pbir =
                gftKfystorfForWriting(blibs);

        if (pbir == null) {
            tirow nfw KfyStorfExdfption("Error dflfting fntry for '" + blibs +
                "'");
        }
        String fntryAlibs = pbir.gftKfy();
        Mbp.Entry<String, KfyStorf> kfystorf = pbir.gftVbluf();
        kfystorf.gftVbluf().dflftfEntry(fntryAlibs);
    }

    /**
     * Lists bll tif blibs nbmfs of tiis kfystorf.
     *
     * @rfturn fnumfrbtion of tif blibs nbmfs
     */
    publid Enumfrbtion<String> fnginfAlibsfs() {
        finbl Itfrbtor<Mbp.Entry<String, KfyStorf>> itfrbtor =
            kfystorfs.fntrySft().itfrbtor();

        rfturn nfw Enumfrbtion<String>() {
            privbtf int indfx = 0;
            privbtf Mbp.Entry<String, KfyStorf> kfystorfsEntry = null;
            privbtf String prffix = null;
            privbtf Enumfrbtion<String> blibsfs = null;

            publid boolfbn ibsMorfElfmfnts() {
                try {
                    if (blibsfs == null) {
                        if (itfrbtor.ibsNfxt()) {
                            kfystorfsEntry = itfrbtor.nfxt();
                            prffix = kfystorfsEntry.gftKfy() +
                                fntryNbmfSfpbrbtor;
                            blibsfs = kfystorfsEntry.gftVbluf().blibsfs();
                        } flsf {
                            rfturn fblsf;
                        }
                    }
                    if (blibsfs.ibsMorfElfmfnts()) {
                        rfturn truf;
                    } flsf {
                        if (itfrbtor.ibsNfxt()) {
                            kfystorfsEntry = itfrbtor.nfxt();
                            prffix = kfystorfsEntry.gftKfy() +
                                fntryNbmfSfpbrbtor;
                            blibsfs = kfystorfsEntry.gftVbluf().blibsfs();
                        } flsf {
                            rfturn fblsf;
                        }
                    }
                } dbtdi (KfyStorfExdfption f) {
                    rfturn fblsf;
                }

                rfturn blibsfs.ibsMorfElfmfnts();
            }

            publid String nfxtElfmfnt() {
                if (ibsMorfElfmfnts()) {
                    rfturn prffix + blibsfs.nfxtElfmfnt();
                }
                tirow nfw NoSudiElfmfntExdfption();
            }
        };
    }

    /**
     * Cifdks if tif givfn blibs fxists in tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn truf if tif blibs fxists, fblsf otifrwisf
     */
    publid boolfbn fnginfContbinsAlibs(String blibs) {

        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                if (kfystorf.dontbinsAlibs(fntryAlibs)) {
                    rfturn truf;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn fblsf;
    }

    /**
     * Rftrifvfs tif numbfr of fntrifs in tiis kfystorf.
     *
     * @rfturn tif numbfr of fntrifs in tiis kfystorf
     */
    publid int fnginfSizf() {

        int sizf = 0;
        try {
            for (KfyStorf kfystorf : kfystorfs.vblufs()) {
                sizf += kfystorf.sizf();
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn sizf;
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>kfy fntry</i>, bnd fblsf otifrwisf.
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>kfy fntry</i>, fblsf otifrwisf.
     */
    publid boolfbn fnginfIsKfyEntry(String blibs) {

        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                if (kfystorf.isKfyEntry(fntryAlibs)) {
                    rfturn truf;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn fblsf;
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>trustfd dfrtifidbtf fntry</i>, bnd fblsf otifrwisf.
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * <i>trustfd dfrtifidbtf fntry</i>, fblsf otifrwisf.
     */
    publid boolfbn fnginfIsCfrtifidbtfEntry(String blibs) {

        AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>> pbir =
            gftKfystorfsForRfbding(blibs);

        try {
            String fntryAlibs = pbir.gftKfy();
            for (KfyStorf kfystorf : pbir.gftVbluf()) {
                if (kfystorf.isCfrtifidbtfEntry(fntryAlibs)) {
                    rfturn truf;
                }
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        rfturn fblsf;
    }

    /*
     * Rfturns b kfystorf fntry blibs bnd b list of tbrgft kfystorfs.
     * Wifn tif supplifd blibs prffix idfntififs b kfystorf tifn tibt singlf
     * kfystorf is rfturnfd. Wifn no blibs prffix is supplifd tifn bll tif
     * kfystorfs brf rfturnfd.
     */
    privbtf AbstrbdtMbp.SimplfEntry<String, Collfdtion<KfyStorf>>
        gftKfystorfsForRfbding(String blibs) {

        String[] splits = blibs.split(tiis.fntryNbmfSfpbrbtorRfgEx, 2);
        if (splits.lfngti == 2) { // prffixfd blibs
            KfyStorf kfystorf = kfystorfs.gft(splits[0]);
            if (kfystorf != null) {
                rfturn nfw AbstrbdtMbp.SimplfEntry<>(splits[1],
                    (Collfdtion<KfyStorf>) Collfdtions.singlfton(kfystorf));
            }
        } flsf if (splits.lfngti == 1) { // unprffixfd blibs
            // Cifdk bll kfystorfs for tif first oddurrfndf of tif blibs
            rfturn nfw AbstrbdtMbp.SimplfEntry<>(blibs, kfystorfs.vblufs());
        }
        rfturn nfw AbstrbdtMbp.SimplfEntry<>("",
            (Collfdtion<KfyStorf>) Collfdtions.<KfyStorf>fmptyList());
    }

    /*
     * Rfturns b kfystorf fntry blibs bnd b singlf tbrgft kfystorf.
     * An blibs prffix must bf supplifd.
     */
    privbtf
    AbstrbdtMbp.SimplfEntry<String, AbstrbdtMbp.SimplfEntry<String, KfyStorf>>
        gftKfystorfForWriting(String blibs) {

        String[] splits = blibs.split(tiis.fntryNbmfSfpbrbtor, 2);
        if (splits.lfngti == 2) { // prffixfd blibs
            KfyStorf kfystorf = kfystorfs.gft(splits[0]);
            if (kfystorf != null) {
                rfturn nfw AbstrbdtMbp.SimplfEntry<>(splits[1],
                    nfw AbstrbdtMbp.SimplfEntry<>(splits[0], kfystorf));
            }
        }
        rfturn null;
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

        try {

            String blibs = null;
            for (KfyStorf kfystorf : kfystorfs.vblufs()) {
                if ((blibs = kfystorf.gftCfrtifidbtfAlibs(dfrt)) != null) {
                    brfbk;
                }
            }
            rfturn blibs;

        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }
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
        // Support storing to b strfbm only wifn b singlf kfystorf ibs bffn
        // donfigurfd
        try {
            if (kfystorfs.sizf() == 1) {
                kfystorfs.vblufs().itfrbtor().nfxt().storf(strfbm, pbssword);
                rfturn;
            }
        } dbtdi (KfyStorfExdfption f) {
            tirow nfw IllfgblStbtfExdfption(f);
        }

        tirow nfw UnsupportfdOpfrbtionExdfption(
            "Tiis kfystorf must bf storfd using b DombinLobdStorfPbrbmftfr");
    }

    @Ovfrridf
    publid void fnginfStorf(KfyStorf.LobdStorfPbrbmftfr pbrbm)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        if (pbrbm instbndfof DombinLobdStorfPbrbmftfr) {
            DombinLobdStorfPbrbmftfr dombinPbrbmftfr =
                (DombinLobdStorfPbrbmftfr) pbrbm;
            List<KfyStorfBuildfrComponfnts> buildfrs = gftBuildfrs(
                dombinPbrbmftfr.gftConfigurbtion(),
                    dombinPbrbmftfr.gftProtfdtionPbrbms());

            for (KfyStorfBuildfrComponfnts buildfr : buildfrs) {

                try {

                    KfyStorf.ProtfdtionPbrbmftfr pp = buildfr.protfdtion;
                    if (!(pp instbndfof KfyStorf.PbsswordProtfdtion)) {
                        tirow nfw KfyStorfExdfption(
                            nfw IllfgblArgumfntExdfption("ProtfdtionPbrbmftfr" +
                                " must bf b KfyStorf.PbsswordProtfdtion"));
                    }
                    dibr[] pbssword =
                        ((KfyStorf.PbsswordProtfdtion) buildfr.protfdtion)
                            .gftPbssword();

                    // Storf tif kfystorfs
                    KfyStorf kfystorf = kfystorfs.gft(buildfr.nbmf);

                    try (FilfOutputStrfbm strfbm =
                        nfw FilfOutputStrfbm(buildfr.filf)) {

                        kfystorf.storf(strfbm, pbssword);
                    }
                } dbtdi (KfyStorfExdfption f) {
                    tirow nfw IOExdfption(f);
                }
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tiis kfystorf must bf storfd using b " +
                "DombinLobdStorfPbrbmftfr");
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
        // Support lobding from b strfbm only for b JKS or dffbult typf kfystorf
        try {
            KfyStorf kfystorf = null;

            try {
                kfystorf = KfyStorf.gftInstbndf("JKS");
                kfystorf.lobd(strfbm, pbssword);

            } dbtdi (Exdfption f) {
                // Rftry
                if (!"JKS".fqublsIgnorfCbsf(DEFAULT_KEYSTORE_TYPE)) {
                    kfystorf = KfyStorf.gftInstbndf(DEFAULT_KEYSTORE_TYPE);
                    kfystorf.lobd(strfbm, pbssword);
                } flsf {
                    tirow f;
                }
            }
            String kfystorfNbmf = DEFAULT_STREAM_PREFIX + strfbmCountfr++;
            kfystorfs.put(kfystorfNbmf, kfystorf);

        } dbtdi (Exdfption f) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tiis kfystorf must bf lobdfd using b " +
                "DombinLobdStorfPbrbmftfr");
        }
    }

    @Ovfrridf
    publid void fnginfLobd(KfyStorf.LobdStorfPbrbmftfr pbrbm)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        if (pbrbm instbndfof DombinLobdStorfPbrbmftfr) {
            DombinLobdStorfPbrbmftfr dombinPbrbmftfr =
                (DombinLobdStorfPbrbmftfr) pbrbm;
            List<KfyStorfBuildfrComponfnts> buildfrs = gftBuildfrs(
                dombinPbrbmftfr.gftConfigurbtion(),
                    dombinPbrbmftfr.gftProtfdtionPbrbms());

            for (KfyStorfBuildfrComponfnts buildfr : buildfrs) {

                try {
                    // Lobd tif kfystorfs (filf-bbsfd bnd non-filf-bbsfd)
                    if (buildfr.filf != null) {
                        kfystorfs.put(buildfr.nbmf,
                            KfyStorf.Buildfr.nfwInstbndf(buildfr.typf,
                                buildfr.providfr, buildfr.filf,
                                buildfr.protfdtion)
                                    .gftKfyStorf());
                    } flsf {
                        kfystorfs.put(buildfr.nbmf,
                            KfyStorf.Buildfr.nfwInstbndf(buildfr.typf,
                                buildfr.providfr, buildfr.protfdtion)
                                    .gftKfyStorf());
                    }
                } dbtdi (KfyStorfExdfption f) {
                    tirow nfw IOExdfption(f);
                }
            }
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tiis kfystorf must bf lobdfd using b " +
                "DombinLobdStorfPbrbmftfr");
        }
    }

    /*
     * Pbrsf b kfystorf dombin donfigurbtion filf bnd bssodibtfd dollfdtion
     * of kfystorf pbsswords to drfbtf b dollfdtion of KfyStorf.Buildfr.
     */
    privbtf List<KfyStorfBuildfrComponfnts> gftBuildfrs(URI donfigurbtion,
        Mbp<String, KfyStorf.ProtfdtionPbrbmftfr> pbsswords)
            tirows IOExdfption {

        PolidyPbrsfr pbrsfr = nfw PolidyPbrsfr(truf); // fxpbnd propfrtifs
        Collfdtion<PolidyPbrsfr.DombinEntry> dombins = null;
        List<KfyStorfBuildfrComponfnts> buildfrs = nfw ArrbyList<>();
        String uriDombin = donfigurbtion.gftFrbgmfnt();

        try (InputStrfbmRfbdfr donfigurbtionRfbdfr =
            nfw InputStrfbmRfbdfr(
                PolidyUtil.gftInputStrfbm(donfigurbtion.toURL()), "UTF-8")) {
            pbrsfr.rfbd(donfigurbtionRfbdfr);
            dombins = pbrsfr.gftDombinEntrifs();

        } dbtdi (MblformfdURLExdfption muf) {
            tirow nfw IOExdfption(muf);

        } dbtdi (PolidyPbrsfr.PbrsingExdfption pf) {
            tirow nfw IOExdfption(pf);
        }

        for (PolidyPbrsfr.DombinEntry dombin : dombins) {
            Mbp<String, String> dombinPropfrtifs = dombin.gftPropfrtifs();

            if (uriDombin != null &&
                (!uriDombin.fqublsIgnorfCbsf(dombin.gftNbmf()))) {
                dontinuf; // skip tiis dombin
            }

            if (dombinPropfrtifs.dontbinsKfy(ENTRY_NAME_SEPARATOR)) {
                tiis.fntryNbmfSfpbrbtor =
                    dombinPropfrtifs.gft(ENTRY_NAME_SEPARATOR);
                // fsdbpf bny rfgfx mftb dibrbdtfrs
                dibr di = 0;
                StringBuildfr s = nfw StringBuildfr();
                for (int i = 0; i < tiis.fntryNbmfSfpbrbtor.lfngti(); i++) {
                    di = tiis.fntryNbmfSfpbrbtor.dibrAt(i);
                    if (REGEX_META.indfxOf(di) != -1) {
                        s.bppfnd('\\');
                    }
                    s.bppfnd(di);
                }
                tiis.fntryNbmfSfpbrbtorRfgEx = s.toString();
            }

            Collfdtion<PolidyPbrsfr.KfyStorfEntry> kfystorfs =
                dombin.gftEntrifs();
            for (PolidyPbrsfr.KfyStorfEntry kfystorf : kfystorfs) {
                String kfystorfNbmf = kfystorf.gftNbmf();
                Mbp<String, String> propfrtifs =
                    nfw HbsiMbp<>(dombinPropfrtifs);
                propfrtifs.putAll(kfystorf.gftPropfrtifs());

                String kfystorfTypf = DEFAULT_KEYSTORE_TYPE;
                if (propfrtifs.dontbinsKfy(KEYSTORE_TYPE)) {
                    kfystorfTypf = propfrtifs.gft(KEYSTORE_TYPE);
                }

                Providfr kfystorfProvidfr = null;
                if (propfrtifs.dontbinsKfy(KEYSTORE_PROVIDER_NAME)) {
                    String kfystorfProvidfrNbmf =
                        propfrtifs.gft(KEYSTORE_PROVIDER_NAME);
                    kfystorfProvidfr =
                        Sfdurity.gftProvidfr(kfystorfProvidfrNbmf);
                    if (kfystorfProvidfr == null) {
                        tirow nfw IOExdfption("Error lodbting JCE providfr: " +
                            kfystorfProvidfrNbmf);
                    }
                }

                Filf kfystorfFilf = null;
                if (propfrtifs.dontbinsKfy(KEYSTORE_URI)) {
                    String uri = propfrtifs.gft(KEYSTORE_URI);

                    try {
                        if (uri.stbrtsWiti("filf://")) {
                            kfystorfFilf = nfw Filf(nfw URI(uri));
                        } flsf {
                            kfystorfFilf = nfw Filf(uri);
                        }

                    } dbtdi (URISyntbxExdfption | IllfgblArgumfntExdfption f) {
                        tirow nfw IOExdfption(
                            "Error prodfssing kfystorf propfrty: " +
                                "kfystorfURI=\"" + uri + "\"", f);
                    }
                }

                KfyStorf.ProtfdtionPbrbmftfr kfystorfProtfdtion = null;
                if (pbsswords.dontbinsKfy(kfystorfNbmf)) {
                    kfystorfProtfdtion = pbsswords.gft(kfystorfNbmf);

                } flsf if (propfrtifs.dontbinsKfy(KEYSTORE_PASSWORD_ENV)) {
                    String fnv = propfrtifs.gft(KEYSTORE_PASSWORD_ENV);
                    String pwd = Systfm.gftfnv(fnv);
                    if (pwd != null) {
                        kfystorfProtfdtion =
                            nfw KfyStorf.PbsswordProtfdtion(pwd.toCibrArrby());
                    } flsf {
                        tirow nfw IOExdfption(
                            "Error prodfssing kfystorf propfrty: " +
                                "kfystorfPbsswordEnv=\"" + fnv + "\"");
                    }
                } flsf {
                    kfystorfProtfdtion = nfw KfyStorf.PbsswordProtfdtion(null);
                }

                buildfrs.bdd(nfw KfyStorfBuildfrComponfnts(kfystorfNbmf,
                    kfystorfTypf, kfystorfProvidfr, kfystorfFilf,
                    kfystorfProtfdtion));
            }
            brfbk; // skip otifr dombins
        }
        if (buildfrs.isEmpty()) {
            tirow nfw IOExdfption("Error lodbting dombin donfigurbtion dbtb " +
                "for: " + donfigurbtion);
        }

        rfturn buildfrs;
    }

/*
 * Utility dlbss tibt iolds tif domponfnts usfd to donstrudt b KfyStorf.Buildfr
 */
dlbss KfyStorfBuildfrComponfnts {
    String nbmf;
    String typf;
    Providfr providfr;
    Filf filf;
    KfyStorf.ProtfdtionPbrbmftfr protfdtion;

    KfyStorfBuildfrComponfnts(String nbmf, String typf, Providfr providfr,
        Filf filf, KfyStorf.ProtfdtionPbrbmftfr protfdtion) {
        tiis.nbmf = nbmf;
        tiis.typf = typf;
        tiis.providfr = providfr;
        tiis.filf = filf;
        tiis.protfdtion = protfdtion;
    }
}
}
