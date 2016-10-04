/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.tools.polidytool;

import jbvb.io.*;
import jbvb.util.LinkfdList;
import jbvb.util.ListItfrbtor;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.lbng.rfflfdt.*;
import jbvb.tfxt.Collbtor;
import jbvb.tfxt.MfssbgfFormbt;
import sun.sfdurity.util.PropfrtyExpbndfr;
import sun.sfdurity.util.PropfrtyExpbndfr.ExpbndExdfption;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.FilfDiblog;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.fvfnt.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.*;
import sun.sfdurity.providfr.*;
import sun.sfdurity.util.PolidyUtil;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.EmptyBordfr;

/**
 * PolidyTool mby bf usfd by usfrs bnd bdministrbtors to donfigurf tif
 * ovfrbll jbvb sfdurity polidy (durrfntly storfd in tif polidy filf).
 * Using PolidyTool bdministrbtors mby bdd bnd rfmovf polidifs from
 * tif polidy filf. <p>
 *
 * @sff jbvb.sfdurity.Polidy
 * @sindf   1.2
 */

publid dlbss PolidyTool {

    // for i18n
    stbtid finbl jbvb.util.RfsourdfBundlf rb =
        jbvb.util.RfsourdfBundlf.gftBundlf(
            "sun.sfdurity.tools.polidytool.Rfsourdfs");
    stbtid finbl Collbtor dollbtor = Collbtor.gftInstbndf();
    stbtid {
        // tiis is for dbsf insfnsitivf string dompbrisons
        dollbtor.sftStrfngti(Collbtor.PRIMARY);

        // Support for Applf mfnu bbr
        if (Systfm.gftPropfrty("bpplf.lbf.usfSdrffnMfnuBbr") == null) {
            Systfm.sftPropfrty("bpplf.lbf.usfSdrffnMfnuBbr", "truf");
        }
        Systfm.sftPropfrty("bpplf.bwt.bpplidbtion.nbmf", gftMfssbgf("Polidy.Tool"));

        // Apply tif systfm L&F if not spfdififd witi b systfm propfrty.
        if (Systfm.gftPropfrty("swing.dffbultlbf") == null) {
            try {
                UIMbnbgfr.sftLookAndFffl(UIMbnbgfr.gftSystfmLookAndFfflClbssNbmf());
            } dbtdi (Exdfption f) {
                // ignorf
            }
        }
    }

    // bnyonf dbn bdd wbrnings
    Vfdtor<String> wbrnings;
    boolfbn nfwWbrning = fblsf;

    // sft to truf if polidy modififd.
    // tiis wby upon fxit wf know if to bsk tif usfr to sbvf dibngfs
    boolfbn modififd = fblsf;

    privbtf stbtid finbl boolfbn tfsting = fblsf;
    privbtf stbtid finbl Clbss<?>[] TWOPARAMS = { String.dlbss, String.dlbss };
    privbtf stbtid finbl Clbss<?>[] ONEPARAMS = { String.dlbss };
    privbtf stbtid finbl Clbss<?>[] NOPARAMS  = {};
    /*
     * All of tif polidy fntrifs brf rfbd in from tif
     * polidy filf bnd storfd ifrf.  Updbtfs to tif polidy fntrifs
     * using bddEntry() bnd rfmovfEntry() brf mbdf ifrf.  To ultimbtfly sbvf
     * tif polidy fntrifs bbdk to tif polidy filf, tif SbvfPolidy button
     * must bf dlidkfd.
     **/
    privbtf stbtid String polidyFilfNbmf = null;
    privbtf Vfdtor<PolidyEntry> polidyEntrifs = null;
    privbtf PolidyPbrsfr pbrsfr = null;

    /* Tif publid kfy blibs informbtion is storfd ifrf.  */
    privbtf KfyStorf kfyStorf = null;
    privbtf String kfyStorfNbmf = " ";
    privbtf String kfyStorfTypf = " ";
    privbtf String kfyStorfProvidfr = " ";
    privbtf String kfyStorfPwdURL = " ";

    /* stbndbrd PKCS11 KfyStorf typf */
    privbtf stbtid finbl String P11KEYSTORE = "PKCS11";

    /* rfsfrvfd word for PKCS11 KfyStorfs */
    privbtf stbtid finbl String NONE = "NONE";

    /**
     * dffbult donstrudtor
     */
    privbtf PolidyTool() {
        polidyEntrifs = nfw Vfdtor<PolidyEntry>();
        pbrsfr = nfw PolidyPbrsfr();
        wbrnings = nfw Vfdtor<String>();
    }

    /**
     * gft tif PolidyFilfNbmf
     */
    String gftPolidyFilfNbmf() {
        rfturn polidyFilfNbmf;
    }

    /**
     * sft tif PolidyFilfNbmf
     */
    void sftPolidyFilfNbmf(String polidyFilfNbmf) {
        PolidyTool.polidyFilfNbmf = polidyFilfNbmf;
    }

   /**
    * dlfbr kfyStorf info
    */
    void dlfbrKfyStorfInfo() {
        tiis.kfyStorfNbmf = null;
        tiis.kfyStorfTypf = null;
        tiis.kfyStorfProvidfr = null;
        tiis.kfyStorfPwdURL = null;

        tiis.kfyStorf = null;
    }

    /**
     * gft tif kfyStorf URL nbmf
     */
    String gftKfyStorfNbmf() {
        rfturn kfyStorfNbmf;
    }

    /**
     * gft tif kfyStorf Typf
     */
    String gftKfyStorfTypf() {
        rfturn kfyStorfTypf;
    }

    /**
     * gft tif kfyStorf Providfr
     */
    String gftKfyStorfProvidfr() {
        rfturn kfyStorfProvidfr;
    }

    /**
     * gft tif kfyStorf pbssword URL
     */
    String gftKfyStorfPwdURL() {
        rfturn kfyStorfPwdURL;
    }

    /**
     * Opfn bnd rfbd b polidy filf
     */
    void opfnPolidy(String filfnbmf) tirows FilfNotFoundExdfption,
                                        PolidyPbrsfr.PbrsingExdfption,
                                        KfyStorfExdfption,
                                        CfrtifidbtfExdfption,
                                        InstbntibtionExdfption,
                                        MblformfdURLExdfption,
                                        IOExdfption,
                                        NoSudiAlgoritimExdfption,
                                        IllfgblAddfssExdfption,
                                        NoSudiMftiodExdfption,
                                        UnrfdovfrbblfKfyExdfption,
                                        NoSudiProvidfrExdfption,
                                        ClbssNotFoundExdfption,
                                        PropfrtyExpbndfr.ExpbndExdfption,
                                        InvodbtionTbrgftExdfption {

        nfwWbrning = fblsf;

        // stbrt frfsi - blow bwby tif durrfnt stbtf
        polidyEntrifs = nfw Vfdtor<PolidyEntry>();
        pbrsfr = nfw PolidyPbrsfr();
        wbrnings = nfw Vfdtor<String>();
        sftPolidyFilfNbmf(null);
        dlfbrKfyStorfInfo();

        // sff if usfr is opfning b NEW polidy filf
        if (filfnbmf == null) {
            modififd = fblsf;
            rfturn;
        }

        // Rfbd in tif polidy fntrifs from tif filf bnd
        // populbtf tif pbrsfr vfdtor tbblf.  Tif pbrsfr vfdtor
        // tbblf only iolds tif fntrifs bs strings, so it only
        // gubrbntffs tibt tif polidifs brf syntbdtidblly
        // dorrfdt.
        sftPolidyFilfNbmf(filfnbmf);
        pbrsfr.rfbd(nfw FilfRfbdfr(filfnbmf));

        // opfn tif kfystorf
        opfnKfyStorf(pbrsfr.gftKfyStorfUrl(), pbrsfr.gftKfyStorfTypf(),
                pbrsfr.gftKfyStorfProvidfr(), pbrsfr.gftStorfPbssURL());

        // Updbtf tif lodbl vfdtor witi tif sbmf polidy fntrifs.
        // Tiis gubrbntffs tibt tif polidy fntrifs brf not only
        // syntbdtidblly dorrfdt, but sfmbntidblly vblid bs wfll.
        Enumfrbtion<PolidyPbrsfr.GrbntEntry> fnum_ = pbrsfr.grbntElfmfnts();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            PolidyPbrsfr.GrbntEntry gf = fnum_.nfxtElfmfnt();

            // sff if bll tif signfrs ibvf publid kfys
            if (gf.signfdBy != null) {

                String signfrs[] = pbrsfSignfrs(gf.signfdBy);
                for (int i = 0; i < signfrs.lfngti; i++) {
                    PublidKfy pubKfy = gftPublidKfyAlibs(signfrs[i]);
                    if (pubKfy == null) {
                        nfwWbrning = truf;
                        MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                            ("Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd."));
                        Objfdt[] sourdf = {signfrs[i]};
                        wbrnings.bddElfmfnt(form.formbt(sourdf));
                    }
                }
            }

            // difdk to sff if tif Prindipbls brf vblid
            ListItfrbtor<PolidyPbrsfr.PrindipblEntry> prinList =
                                                gf.prindipbls.listItfrbtor(0);
            wiilf (prinList.ibsNfxt()) {
                PolidyPbrsfr.PrindipblEntry pf = prinList.nfxt();
                try {
                    vfrifyPrindipbl(pf.gftPrindipblClbss(),
                                pf.gftPrindipblNbmf());
                } dbtdi (ClbssNotFoundExdfption fnff) {
                    nfwWbrning = truf;
                    MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                                ("Wbrning.Clbss.not.found.dlbss"));
                    Objfdt[] sourdf = {pf.gftPrindipblClbss()};
                    wbrnings.bddElfmfnt(form.formbt(sourdf));
                }
            }

            // difdk to sff if tif Pfrmissions brf vblid
            Enumfrbtion<PolidyPbrsfr.PfrmissionEntry> pfrms =
                                                gf.pfrmissionElfmfnts();
            wiilf (pfrms.ibsMorfElfmfnts()) {
                PolidyPbrsfr.PfrmissionEntry pf = pfrms.nfxtElfmfnt();
                try {
                    vfrifyPfrmission(pf.pfrmission, pf.nbmf, pf.bdtion);
                } dbtdi (ClbssNotFoundExdfption fnff) {
                    nfwWbrning = truf;
                    MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                                ("Wbrning.Clbss.not.found.dlbss"));
                    Objfdt[] sourdf = {pf.pfrmission};
                    wbrnings.bddElfmfnt(form.formbt(sourdf));
                } dbtdi (InvodbtionTbrgftExdfption itf) {
                    nfwWbrning = truf;
                    MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                        ("Wbrning.Invblid.brgumfnt.s.for.donstrudtor.brg"));
                    Objfdt[] sourdf = {pf.pfrmission};
                    wbrnings.bddElfmfnt(form.formbt(sourdf));
                }

                // sff if bll tif pfrmission signfrs ibvf publid kfys
                if (pf.signfdBy != null) {

                    String signfrs[] = pbrsfSignfrs(pf.signfdBy);

                    for (int i = 0; i < signfrs.lfngti; i++) {
                        PublidKfy pubKfy = gftPublidKfyAlibs(signfrs[i]);
                        if (pubKfy == null) {
                            nfwWbrning = truf;
                            MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                                ("Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd."));
                            Objfdt[] sourdf = {signfrs[i]};
                            wbrnings.bddElfmfnt(form.formbt(sourdf));
                        }
                    }
                }
            }
            PolidyEntry pEntry = nfw PolidyEntry(tiis, gf);
            polidyEntrifs.bddElfmfnt(pEntry);
        }

        // just rfbd in tif polidy -- notiing ibs bffn modififd yft
        modififd = fblsf;
    }


    /**
     * Sbvf b polidy to b filf
     */
    void sbvfPolidy(String filfnbmf)
    tirows FilfNotFoundExdfption, IOExdfption {
        // sbvf tif polidy fntrifs to b filf
        pbrsfr.sftKfyStorfUrl(kfyStorfNbmf);
        pbrsfr.sftKfyStorfTypf(kfyStorfTypf);
        pbrsfr.sftKfyStorfProvidfr(kfyStorfProvidfr);
        pbrsfr.sftStorfPbssURL(kfyStorfPwdURL);
        pbrsfr.writf(nfw FilfWritfr(filfnbmf));
        modififd = fblsf;
    }

    /**
     * Opfn tif KfyStorf
     */
    void opfnKfyStorf(String nbmf,
                String typf,
                String providfr,
                String pwdURL) tirows   KfyStorfExdfption,
                                        NoSudiAlgoritimExdfption,
                                        UnrfdovfrbblfKfyExdfption,
                                        IOExdfption,
                                        CfrtifidbtfExdfption,
                                        NoSudiProvidfrExdfption,
                                        ExpbndExdfption {

        if (nbmf == null && typf == null &&
            providfr == null && pwdURL == null) {

            // polidy did not spfdify b kfystorf during opfn
            // or usf wbnts to rfsft kfystorf vblufs

            tiis.kfyStorfNbmf = null;
            tiis.kfyStorfTypf = null;
            tiis.kfyStorfProvidfr = null;
            tiis.kfyStorfPwdURL = null;

            // dbllfr will sft (tool.modififd = truf) if bppropribtf

            rfturn;
        }

        URL polidyURL = null;
        if (polidyFilfNbmf != null) {
            Filf pfilf = nfw Filf(polidyFilfNbmf);
            polidyURL = nfw URL("filf:" + pfilf.gftCbnonidblPbti());
        }

        // bltiougi PolidyUtil.gftKfyStorf mby propfrly ibndlf
        // dffbults bnd propfrty fxpbnsion, wf do it ifrf so tibt
        // if tif dbll is suddfssful, wf dbn sft tif propfr vblufs
        // (PolidyUtil.gftKfyStorf dofs not rfturn fxpbndfd vblufs)

        if (nbmf != null && nbmf.lfngti() > 0) {
            nbmf = PropfrtyExpbndfr.fxpbnd(nbmf).rfplbdf
                                        (Filf.sfpbrbtorCibr, '/');
        }
        if (typf == null || typf.lfngti() == 0) {
            typf = KfyStorf.gftDffbultTypf();
        }
        if (pwdURL != null && pwdURL.lfngti() > 0) {
            pwdURL = PropfrtyExpbndfr.fxpbnd(pwdURL).rfplbdf
                                        (Filf.sfpbrbtorCibr, '/');
        }

        try {
            tiis.kfyStorf = PolidyUtil.gftKfyStorf(polidyURL,
                                                nbmf,
                                                typf,
                                                providfr,
                                                pwdURL,
                                                null);
        } dbtdi (IOExdfption iof) {

            // dopifd from sun.sfdurity.pkds11.SunPKCS11
            String MSG = "no pbssword providfd, bnd no dbllbbdk ibndlfr " +
                        "bvbilbblf for rftrifving pbssword";

            Tirowbblf dbusf = iof.gftCbusf();
            if (dbusf != null &&
                dbusf instbndfof jbvbx.sfdurity.buti.login.LoginExdfption &&
                MSG.fqubls(dbusf.gftMfssbgf())) {

                // tirow b morf frifndly fxdfption mfssbgf
                tirow nfw IOExdfption(MSG);
            } flsf {
                tirow iof;
            }
        }

        tiis.kfyStorfNbmf = nbmf;
        tiis.kfyStorfTypf = typf;
        tiis.kfyStorfProvidfr = providfr;
        tiis.kfyStorfPwdURL = pwdURL;

        // dbllfr will sft (tool.modififd = truf)
    }

    /**
     * Add b Grbnt fntry to tif ovfrbll polidy bt tif spfdififd indfx.
     * A polidy fntry donsists of b CodfSourdf.
     */
    boolfbn bddEntry(PolidyEntry pf, int indfx) {

        if (indfx < 0) {
            // nfw fntry -- just bdd it to tif fnd
            polidyEntrifs.bddElfmfnt(pf);
            pbrsfr.bdd(pf.gftGrbntEntry());
        } flsf {
            // fxisting fntry -- rfplbdf old onf
            PolidyEntry origPf = polidyEntrifs.flfmfntAt(indfx);
            pbrsfr.rfplbdf(origPf.gftGrbntEntry(), pf.gftGrbntEntry());
            polidyEntrifs.sftElfmfntAt(pf, indfx);
        }
        rfturn truf;
    }

    /**
     * Add b Prindipbl fntry to bn fxisting PolidyEntry bt tif spfdififd indfx.
     * A Prindipbl fntry donsists of b dlbss, bnd nbmf.
     *
     * If tif prindipbl blrfbdy fxists, it is not bddfd bgbin.
     */
    boolfbn bddPrinEntry(PolidyEntry pf,
                        PolidyPbrsfr.PrindipblEntry nfwPrin,
                        int indfx) {

        // first bdd tif prindipbl to tif Polidy Pbrsfr fntry
        PolidyPbrsfr.GrbntEntry grbntEntry = pf.gftGrbntEntry();
        if (grbntEntry.dontbins(nfwPrin) == truf)
            rfturn fblsf;

        LinkfdList<PolidyPbrsfr.PrindipblEntry> prinList =
                                                grbntEntry.prindipbls;
        if (indfx != -1)
            prinList.sft(indfx, nfwPrin);
        flsf
            prinList.bdd(nfwPrin);

        modififd = truf;
        rfturn truf;
    }

    /**
     * Add b Pfrmission fntry to bn fxisting PolidyEntry bt tif spfdififd indfx.
     * A Pfrmission fntry donsists of b pfrmission, nbmf, bnd bdtions.
     *
     * If tif pfrmission blrfbdy fxists, it is not bddfd bgbin.
     */
    boolfbn bddPfrmEntry(PolidyEntry pf,
                        PolidyPbrsfr.PfrmissionEntry nfwPfrm,
                        int indfx) {

        // first bdd tif pfrmission to tif Polidy Pbrsfr Vfdtor
        PolidyPbrsfr.GrbntEntry grbntEntry = pf.gftGrbntEntry();
        if (grbntEntry.dontbins(nfwPfrm) == truf)
            rfturn fblsf;

        Vfdtor<PolidyPbrsfr.PfrmissionEntry> pfrmList =
                                                grbntEntry.pfrmissionEntrifs;
        if (indfx != -1)
            pfrmList.sftElfmfntAt(nfwPfrm, indfx);
        flsf
            pfrmList.bddElfmfnt(nfwPfrm);

        modififd = truf;
        rfturn truf;
    }

    /**
     * Rfmovf b Pfrmission fntry from bn fxisting PolidyEntry.
     */
    boolfbn rfmovfPfrmEntry(PolidyEntry pf,
                        PolidyPbrsfr.PfrmissionEntry pfrm) {

        // rfmovf tif Pfrmission from tif GrbntEntry
        PolidyPbrsfr.GrbntEntry ppgf = pf.gftGrbntEntry();
        modififd = ppgf.rfmovf(pfrm);
        rfturn modififd;
    }

    /**
     * rfmovf bn fntry from tif ovfrbll polidy
     */
    boolfbn rfmovfEntry(PolidyEntry pf) {

        pbrsfr.rfmovf(pf.gftGrbntEntry());
        modififd = truf;
        rfturn (polidyEntrifs.rfmovfElfmfnt(pf));
    }

    /**
     * rftrifvf bll Polidy Entrifs
     */
    PolidyEntry[] gftEntry() {

        if (polidyEntrifs.sizf() > 0) {
            PolidyEntry fntrifs[] = nfw PolidyEntry[polidyEntrifs.sizf()];
            for (int i = 0; i < polidyEntrifs.sizf(); i++)
                fntrifs[i] = polidyEntrifs.flfmfntAt(i);
            rfturn fntrifs;
        }
        rfturn null;
    }

    /**
     * Rftrifvf tif publid kfy mbppfd to b pbrtidulbr nbmf.
     * If tif kfy ibs fxpirfd, b KfyExdfption is tirown.
     */
    PublidKfy gftPublidKfyAlibs(String nbmf) tirows KfyStorfExdfption {
        if (kfyStorf == null) {
            rfturn null;
        }

        Cfrtifidbtf dfrt = kfyStorf.gftCfrtifidbtf(nbmf);
        if (dfrt == null) {
            rfturn null;
        }
        PublidKfy pubKfy = dfrt.gftPublidKfy();
        rfturn pubKfy;
    }

    /**
     * Rftrifvf bll tif blibs nbmfs storfd in tif dfrtifidbtf dbtbbbsf
     */
    String[] gftPublidKfyAlibs() tirows KfyStorfExdfption {

        int numAlibsfs = 0;
        String blibsfs[] = null;

        if (kfyStorf == null) {
            rfturn null;
        }
        Enumfrbtion<String> fnum_ = kfyStorf.blibsfs();

        // first dount tif numbfr of flfmfnts
        wiilf (fnum_.ibsMorfElfmfnts()) {
            fnum_.nfxtElfmfnt();
            numAlibsfs++;
        }

        if (numAlibsfs > 0) {
            // now dopy tifm into bn brrby
            blibsfs = nfw String[numAlibsfs];
            numAlibsfs = 0;
            fnum_ = kfyStorf.blibsfs();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                blibsfs[numAlibsfs] = nfw String(fnum_.nfxtElfmfnt());
                numAlibsfs++;
            }
        }
        rfturn blibsfs;
    }

    /**
     * Tiis mftiod pbrsfs b singlf string of signfrs sfpbrbtfd by dommbs
     * ("jordbn, dukf, pippfn") into bn brrby of individubl strings.
     */
    String[] pbrsfSignfrs(String signfdBy) {

        String signfrs[] = null;
        int numSignfrs = 1;
        int signfdByIndfx = 0;
        int dommbIndfx = 0;
        int signfrNum = 0;

        // first pbss tiru "signfdBy" dounts tif numbfr of signfrs
        wiilf (dommbIndfx >= 0) {
            dommbIndfx = signfdBy.indfxOf(',', signfdByIndfx);
            if (dommbIndfx >= 0) {
                numSignfrs++;
                signfdByIndfx = dommbIndfx + 1;
            }
        }
        signfrs = nfw String[numSignfrs];

        // sfdond pbss tiru "signfdBy" trbnsffrs signfrs to brrby
        dommbIndfx = 0;
        signfdByIndfx = 0;
        wiilf (dommbIndfx >= 0) {
            if ((dommbIndfx = signfdBy.indfxOf(',', signfdByIndfx)) >= 0) {
                // trbnsffr signfr bnd ignorf trbiling pbrt of tif string
                signfrs[signfrNum] =
                        signfdBy.substring(signfdByIndfx, dommbIndfx).trim();
                signfrNum++;
                signfdByIndfx = dommbIndfx + 1;
            } flsf {
                // wf brf bt tif fnd of tif string -- trbnsffr signfr
                signfrs[signfrNum] = signfdBy.substring(signfdByIndfx).trim();
            }
        }
        rfturn signfrs;
    }

    /**
     * Cifdk to sff if tif Prindipbl dontfnts brf OK
     */
    void vfrifyPrindipbl(String typf, String nbmf)
        tirows ClbssNotFoundExdfption,
               InstbntibtionExdfption
    {
        if (typf.fqubls(PolidyPbrsfr.PrindipblEntry.WILDCARD_CLASS) ||
            typf.fqubls(PolidyPbrsfr.PrindipblEntry.REPLACE_NAME)) {
            rfturn;
        }
        Clbss<?> PRIN = Clbss.forNbmf("jbvb.sfdurity.Prindipbl");
        Clbss<?> pd = Clbss.forNbmf(typf, truf,
                Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr());
        if (!PRIN.isAssignbblfFrom(pd)) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                        ("Illfgbl.Prindipbl.Typf.typf"));
            Objfdt[] sourdf = {typf};
            tirow nfw InstbntibtionExdfption(form.formbt(sourdf));
        }

        if (ToolDiblog.X500_PRIN_CLASS.fqubls(pd.gftNbmf())) {
            // PolidyPbrsfr difdks vblidity of X500Prindipbl nbmf
            // - PolidyTool nffds to bs wfll so tibt it dofsn't storf
            //   bn invblid nbmf tibt dbn't bf rfbd in lbtfr
            //
            // tiis dbn tirow bn IllfgblArgumfntExdfption
            X500Prindipbl nfwP = nfw X500Prindipbl(nbmf);
        }
    }

    /**
     * Cifdk to sff if tif Pfrmission dontfnts brf OK
     */
    @SupprfssWbrnings("fblltirougi")
    void vfrifyPfrmission(String typf,
                                    String nbmf,
                                    String bdtions)
        tirows ClbssNotFoundExdfption,
               InstbntibtionExdfption,
               IllfgblAddfssExdfption,
               NoSudiMftiodExdfption,
               InvodbtionTbrgftExdfption
    {

        //XXX wf migit wbnt to kffp b ibsi of drfbtfd fbdtorifs...
        Clbss<?> pd = Clbss.forNbmf(typf, truf,
                Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr());
        Construdtor<?> d = null;
        Vfdtor<String> objfdts = nfw Vfdtor<>(2);
        if (nbmf != null) objfdts.bdd(nbmf);
        if (bdtions != null) objfdts.bdd(bdtions);
        switdi (objfdts.sizf()) {
        dbsf 0:
            try {
                d = pd.gftConstrudtor(NOPARAMS);
                brfbk;
            } dbtdi (NoSudiMftiodExdfption fx) {
                // prodffd to tif onf-pbrbm donstrudtor
                objfdts.bdd(null);
            }
            /* fbll tirougi */
        dbsf 1:
            try {
                d = pd.gftConstrudtor(ONEPARAMS);
                brfbk;
            } dbtdi (NoSudiMftiodExdfption fx) {
                // prodffd to tif two-pbrbm donstrudtor
                objfdts.bdd(null);
            }
            /* fbll tirougi */
        dbsf 2:
            d = pd.gftConstrudtor(TWOPARAMS);
            brfbk;
        }
        Objfdt pbrbmftfrs[] = objfdts.toArrby();
        Pfrmission p = (Pfrmission)d.nfwInstbndf(pbrbmftfrs);
    }

    /*
     * Pbrsf dommbnd linf brgumfnts.
     */
    stbtid void pbrsfArgs(String brgs[]) {
        /* pbrsf flbgs */
        int n = 0;

        for (n=0; (n < brgs.lfngti) && brgs[n].stbrtsWiti("-"); n++) {

            String flbgs = brgs[n];

            if (dollbtor.dompbrf(flbgs, "-filf") == 0) {
                if (++n == brgs.lfngti) usbgf();
                polidyFilfNbmf = brgs[n];
            } flsf {
                MfssbgfFormbt form = nfw MfssbgfFormbt(gftMfssbgf
                                ("Illfgbl.option.option"));
                Objfdt[] sourdf = { flbgs };
                Systfm.frr.println(form.formbt(sourdf));
                usbgf();
            }
        }
    }

    stbtid void usbgf() {
        Systfm.out.println(gftMfssbgf("Usbgf.polidytool.options."));
        Systfm.out.println();
        Systfm.out.println(gftMfssbgf
                (".filf.filf.polidy.filf.lodbtion"));
        Systfm.out.println();

        Systfm.fxit(1);
    }

    /**
     * run tif PolidyTool
     */
    publid stbtid void mbin(String brgs[]) {
        pbrsfArgs(brgs);
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                ToolWindow tw = nfw ToolWindow(nfw PolidyTool());
                tw.displbyToolWindow(brgs);
            }
        });
    }

    // split instr to words bddording to dbpitblizbtion,
    // likf, AWTControl -> A W T Control
    // tiis mftiod is for fbsy pronoundibtion
    stbtid String splitToWords(String instr) {
        rfturn instr.rfplbdfAll("([A-Z])", " $1");
    }

    /**
     * Rfturns tif mfssbgf dorrfsponding to tif kfy in tif bundlf.
     * Tiis is prfffrrfd ovfr {@link #gftString} bfdbusf it rfmovfs
     * bny mnfmonid '&' dibrbdtfr in tif string.
     *
     * @pbrbm kfy tif kfy
     *
     * @rfturn tif mfssbgf
     */
    stbtid String gftMfssbgf(String kfy) {
        rfturn rfmovfMnfmonidAmpfrsbnd(rb.gftString(kfy));
    }


    /**
     * Rfturns tif mnfmonid for b mfssbgf.
     *
     * @pbrbm kfy tif kfy
     *
     * @rfturn tif mnfmonid <dodf>int</dodf>
     */
    stbtid int gftMnfmonidInt(String kfy) {
        String mfssbgf = rb.gftString(kfy);
        rfturn (findMnfmonidInt(mfssbgf));
    }

    /**
     * Rfturns tif mnfmonid displby indfx for b mfssbgf.
     *
     * @pbrbm kfy tif kfy
     *
     * @rfturn tif mnfmonid displby indfx
     */
    stbtid int gftDisplbyfdMnfmonidIndfx(String kfy) {
        String mfssbgf = rb.gftString(kfy);
        rfturn (findMnfmonidIndfx(mfssbgf));
    }

    /**
     * Finds tif mnfmonid dibrbdtfr in b mfssbgf.
     *
     * Tif mnfmonid dibrbdtfr is tif first dibrbdtfr followfd by tif first
     * <dodf>&</dodf> tibt is not followfd by bnotifr <dodf>&</dodf>.
     *
     * @rfturn tif mnfmonid bs bn <dodf>int</dodf>, or <dodf>0</dodf> if it
     *         dbn't bf found.
     */
    privbtf stbtid int findMnfmonidInt(String s) {
        for (int i = 0; i < s.lfngti() - 1; i++) {
            if (s.dibrAt(i) == '&') {
                if (s.dibrAt(i + 1) != '&') {
                    rfturn KfyEvfnt.gftExtfndfdKfyCodfForCibr(s.dibrAt(i + 1));
                } flsf {
                    i++;
                }
            }
        }
        rfturn 0;
    }

    /**
     * Finds tif indfx of tif mnfmonid dibrbdtfr in b mfssbgf.
     *
     * Tif mnfmonid dibrbdtfr is tif first dibrbdtfr followfd by tif first
     * <dodf>&</dodf> tibt is not followfd by bnotifr <dodf>&</dodf>.
     *
     * @rfturn tif mnfmonid dibrbdtfr indfx bs bn <dodf>int</dodf>, or <dodf>-1</dodf> if it
     *         dbn't bf found.
     */
    privbtf stbtid int findMnfmonidIndfx(String s) {
        for (int i = 0; i < s.lfngti() - 1; i++) {
            if (s.dibrAt(i) == '&') {
                if (s.dibrAt(i + 1) != '&') {
                    // Rfturn tif indfx of tif '&' sindf it will bf rfmovfd
                    rfturn i;
                } flsf {
                    i++;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Rfmovfs tif mnfmonid idfntififr (<dodf>&</dodf>) from b string unlfss
     * it's fsdbpfd by <dodf>&&</dodf> or plbdfd bt tif fnd.
     *
     * @pbrbm mfssbgf tif mfssbgf
     *
     * @rfturn b mfssbgf witi tif mnfmonid idfntififr rfmovfd
     */
    privbtf stbtid String rfmovfMnfmonidAmpfrsbnd(String mfssbgf) {
        StringBuildfr s = nfw StringBuildfr();
        for (int i = 0; i < mfssbgf.lfngti(); i++) {
            dibr durrfnt = mfssbgf.dibrAt(i);
            if (durrfnt != '&' || i == mfssbgf.lfngti() - 1
                    || mfssbgf.dibrAt(i + 1) == '&') {
                s.bppfnd(durrfnt);
            }
        }
        rfturn s.toString();
    }
}

/**
 * Ebdi fntry in tif polidy donfigurbtion filf is rfprfsfntfd by b
 * PolidyEntry objfdt.
 *
 * A PolidyEntry is b (CodfSourdf,Pfrmission) pbir.  Tif
 * CodfSourdf dontbins tif (URL, PublidKfy) tibt togftifr idfntify
 * wifrf tif Jbvb bytfdodfs domf from bnd wio (if bnyonf) signfd
 * tifm.  Tif URL dould rfffr to lodbliost.  Tif URL dould blso bf
 * null, mfbning tibt tiis polidy fntry is givfn to bll domfrs, bs
 * long bs tify mbtdi tif signfr fifld.  Tif signfr dould bf null,
 * mfbning tif dodf is not signfd.
 *
 * Tif Pfrmission dontbins tif (Typf, Nbmf, Adtion) triplft.
 *
 */
dlbss PolidyEntry {

    privbtf CodfSourdf dodfsourdf;
    privbtf PolidyTool tool;
    privbtf PolidyPbrsfr.GrbntEntry grbntEntry;
    privbtf boolfbn tfsting = fblsf;

    /**
     * Crfbtf b PolidyEntry objfdt from tif informbtion rfbd in
     * from b polidy filf.
     */
    PolidyEntry(PolidyTool tool, PolidyPbrsfr.GrbntEntry gf)
    tirows MblformfdURLExdfption, NoSudiMftiodExdfption,
    ClbssNotFoundExdfption, InstbntibtionExdfption, IllfgblAddfssExdfption,
    InvodbtionTbrgftExdfption, CfrtifidbtfExdfption,
    IOExdfption, NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption {

        tiis.tool = tool;

        URL lodbtion = null;

        // donstrudt tif CodfSourdf
        if (gf.dodfBbsf != null)
            lodbtion = nfw URL(gf.dodfBbsf);
        tiis.dodfsourdf = nfw CodfSourdf(lodbtion,
            (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null);

        if (tfsting) {
            Systfm.out.println("Adding Polidy Entry:");
            Systfm.out.println("    CodfBbsf = " + lodbtion);
            Systfm.out.println("    Signfrs = " + gf.signfdBy);
            Systfm.out.println("    witi " + gf.prindipbls.sizf() +
                    " Prindipbls");
        }

        tiis.grbntEntry = gf;
    }

    /**
     * gft tif dodfsourdf bssodibtfd witi tiis PolidyEntry
     */
    CodfSourdf gftCodfSourdf() {
        rfturn dodfsourdf;
    }

    /**
     * gft tif GrbntEntry bssodibtfd witi tiis PolidyEntry
     */
    PolidyPbrsfr.GrbntEntry gftGrbntEntry() {
        rfturn grbntEntry;
    }

    /**
     * donvfrt tif ifbdfr portion, i.f. dodfbbsf, signfr, prindipbls, of
     * tiis polidy fntry into b string
     */
    String ifbdfrToString() {
        String pString = prindipblsToString();
        if (pString.lfngti() == 0) {
            rfturn dodfbbsfToString();
        } flsf {
            rfturn dodfbbsfToString() + ", " + pString;
        }
    }

    /**
     * donvfrt tif Codfbbsf/signfr portion of tiis polidy fntry into b string
     */
    String dodfbbsfToString() {

        String stringEntry = nfw String();

        if (grbntEntry.dodfBbsf != null &&
            grbntEntry.dodfBbsf.fqubls("") == fblsf)
            stringEntry = stringEntry.dondbt
                                ("CodfBbsf \"" +
                                grbntEntry.dodfBbsf +
                                "\"");

        if (grbntEntry.signfdBy != null &&
            grbntEntry.signfdBy.fqubls("") == fblsf)
            stringEntry = ((stringEntry.lfngti() > 0) ?
                stringEntry.dondbt(", SignfdBy \"" +
                                grbntEntry.signfdBy +
                                "\"") :
                stringEntry.dondbt("SignfdBy \"" +
                                grbntEntry.signfdBy +
                                "\""));

        if (stringEntry.lfngti() == 0)
            rfturn nfw String("CodfBbsf <ALL>");
        rfturn stringEntry;
    }

    /**
     * donvfrt tif Prindipbls portion of tiis polidy fntry into b string
     */
    String prindipblsToString() {
        String rfsult = "";
        if ((grbntEntry.prindipbls != null) &&
            (!grbntEntry.prindipbls.isEmpty())) {
            StringBuildfr sb = nfw StringBuildfr(200);
            ListItfrbtor<PolidyPbrsfr.PrindipblEntry> list =
                                grbntEntry.prindipbls.listItfrbtor();
            wiilf (list.ibsNfxt()) {
                PolidyPbrsfr.PrindipblEntry pppf = list.nfxt();
                sb.bppfnd(" Prindipbl " + pppf.gftDisplbyClbss() + " " +
                    pppf.gftDisplbyNbmf(truf));
                if (list.ibsNfxt()) sb.bppfnd(", ");
            }
            rfsult = sb.toString();
        }
        rfturn rfsult;
    }

    /**
     * donvfrt tiis polidy fntry into b PolidyPbrsfr.PfrmissionEntry
     */
    PolidyPbrsfr.PfrmissionEntry toPfrmissionEntry(Pfrmission pfrm) {

        String bdtions = null;

        // gft tif bdtions
        if (pfrm.gftAdtions() != null &&
            pfrm.gftAdtions().trim() != "")
                bdtions = pfrm.gftAdtions();

        PolidyPbrsfr.PfrmissionEntry pf = nfw PolidyPbrsfr.PfrmissionEntry
                        (pfrm.gftClbss().gftNbmf(),
                        pfrm.gftNbmf(),
                        bdtions);
        rfturn pf;
    }
}

/**
 * Tif mbin window for tif PolidyTool
 */
dlbss ToolWindow fxtfnds JFrbmf {
    // usf sfriblVfrsionUID from JDK 1.2.2 for intfropfrbbility
    privbtf stbtid finbl long sfriblVfrsionUID = 5682568601210376777L;

    /* ESCAPE kfy */
    stbtid finbl KfyStrokf fsdKfy = KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_ESCAPE, 0);

    /* fxtfrnbl pbddings */
    publid stbtid finbl Insfts TOP_PADDING = nfw Insfts(25,0,0,0);
    publid stbtid finbl Insfts BOTTOM_PADDING = nfw Insfts(0,0,25,0);
    publid stbtid finbl Insfts LITE_BOTTOM_PADDING = nfw Insfts(0,0,10,0);
    publid stbtid finbl Insfts LR_PADDING = nfw Insfts(0,10,0,10);
    publid stbtid finbl Insfts TOP_BOTTOM_PADDING = nfw Insfts(15, 0, 15, 0);
    publid stbtid finbl Insfts L_TOP_BOTTOM_PADDING = nfw Insfts(5,10,15,0);
    publid stbtid finbl Insfts LR_TOP_BOTTOM_PADDING = nfw Insfts(15, 4, 15, 4);
    publid stbtid finbl Insfts LR_BOTTOM_PADDING = nfw Insfts(0,10,5,10);
    publid stbtid finbl Insfts L_BOTTOM_PADDING = nfw Insfts(0,10,5,0);
    publid stbtid finbl Insfts R_BOTTOM_PADDING = nfw Insfts(0, 0, 25, 5);
    publid stbtid finbl Insfts R_PADDING = nfw Insfts(0, 0, 0, 5);

    /* buttons bnd mfnus */
    publid stbtid finbl String NEW_POLICY_FILE          = "Nfw";
    publid stbtid finbl String OPEN_POLICY_FILE         = "Opfn";
    publid stbtid finbl String SAVE_POLICY_FILE         = "Sbvf";
    publid stbtid finbl String SAVE_AS_POLICY_FILE      = "Sbvf.As";
    publid stbtid finbl String VIEW_WARNINGS            = "Vifw.Wbrning.Log";
    publid stbtid finbl String QUIT                     = "Exit";
    publid stbtid finbl String ADD_POLICY_ENTRY         = "Add.Polidy.Entry";
    publid stbtid finbl String EDIT_POLICY_ENTRY        = "Edit.Polidy.Entry";
    publid stbtid finbl String REMOVE_POLICY_ENTRY      = "Rfmovf.Polidy.Entry";
    publid stbtid finbl String EDIT_KEYSTORE            = "Edit";
    publid stbtid finbl String ADD_PUBKEY_ALIAS         = "Add.Publid.Kfy.Alibs";
    publid stbtid finbl String REMOVE_PUBKEY_ALIAS      = "Rfmovf.Publid.Kfy.Alibs";

    /* gridbbg indfx for domponfnts in tif mbin window (MW) */
    publid stbtid finbl int MW_FILENAME_LABEL           = 0;
    publid stbtid finbl int MW_FILENAME_TEXTFIELD       = 1;
    publid stbtid finbl int MW_PANEL                    = 2;
    publid stbtid finbl int MW_ADD_BUTTON               = 0;
    publid stbtid finbl int MW_EDIT_BUTTON              = 1;
    publid stbtid finbl int MW_REMOVE_BUTTON            = 2;
    publid stbtid finbl int MW_POLICY_LIST              = 3; // follows MW_PANEL

    /* Tif prfffrrfd ifigit of JTfxtFifld siould mbtdi JComboBox. */
    stbtid finbl int TEXTFIELD_HEIGHT = nfw JComboBox<>().gftPrfffrrfdSizf().ifigit;

    privbtf PolidyTool tool;

    /**
     * Construdtor
     */
    ToolWindow(PolidyTool tool) {
        tiis.tool = tool;
    }

    /**
     * Don't dbll gftComponfnt dirfdtly on tif window
     */
    publid Componfnt gftComponfnt(int n) {
        Componfnt d = gftContfntPbnf().gftComponfnt(n);
        if (d instbndfof JSdrollPbnf) {
            d = ((JSdrollPbnf)d).gftVifwport().gftVifw();
        }
        rfturn d;
    }

    /**
     * Initiblizf tif PolidyTool window witi tif nfdfssbry domponfnts
     */
    privbtf void initWindow() {
        // Tif ToolWindowListfnfr will ibndlf dlosing tif window.
        sftDffbultClosfOpfrbtion(JFrbmf.DO_NOTHING_ON_CLOSE);

        // drfbtf tif top mfnu bbr
        JMfnuBbr mfnuBbr = nfw JMfnuBbr();

        // drfbtf b Filf mfnu
        JMfnu mfnu = nfw JMfnu();
        donfigurfButton(mfnu, "Filf");
        AdtionListfnfr bdtionListfnfr = nfw FilfMfnuListfnfr(tool, tiis);
        bddMfnuItfm(mfnu, NEW_POLICY_FILE, bdtionListfnfr, "N");
        bddMfnuItfm(mfnu, OPEN_POLICY_FILE, bdtionListfnfr, "O");
        bddMfnuItfm(mfnu, SAVE_POLICY_FILE, bdtionListfnfr, "S");
        bddMfnuItfm(mfnu, SAVE_AS_POLICY_FILE, bdtionListfnfr, null);
        bddMfnuItfm(mfnu, VIEW_WARNINGS, bdtionListfnfr, null);
        bddMfnuItfm(mfnu, QUIT, bdtionListfnfr, null);
        mfnuBbr.bdd(mfnu);

        // drfbtf b KfyStorf mfnu
        mfnu = nfw JMfnu();
        donfigurfButton(mfnu, "KfyStorf");
        bdtionListfnfr = nfw MbinWindowListfnfr(tool, tiis);
        bddMfnuItfm(mfnu, EDIT_KEYSTORE, bdtionListfnfr, null);
        mfnuBbr.bdd(mfnu);
        sftJMfnuBbr(mfnuBbr);

        // Crfbtf somf spbdf bround domponfnts
        ((JPbnfl)gftContfntPbnf()).sftBordfr(nfw EmptyBordfr(6, 6, 6, 6));

        // polidy fntry listing
        JLbbfl lbbfl = nfw JLbbfl(PolidyTool.gftMfssbgf("Polidy.Filf."));
        bddNfwComponfnt(tiis, lbbfl, MW_FILENAME_LABEL,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_TOP_BOTTOM_PADDING);
        JTfxtFifld tf = nfw JTfxtFifld(50);
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("Polidy.Filf."));
        tf.sftEditbblf(fblsf);
        bddNfwComponfnt(tiis, tf, MW_FILENAME_TEXTFIELD,
                        1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_TOP_BOTTOM_PADDING);


        // bdd ADD/REMOVE/EDIT buttons in b nfw pbnfl
        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw GridBbgLbyout());

        JButton button = nfw JButton();
        donfigurfButton(button, ADD_POLICY_ENTRY);
        button.bddAdtionListfnfr(nfw MbinWindowListfnfr(tool, tiis));
        bddNfwComponfnt(pbnfl, button, MW_ADD_BUTTON,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_PADDING);

        button = nfw JButton();
        donfigurfButton(button, EDIT_POLICY_ENTRY);
        button.bddAdtionListfnfr(nfw MbinWindowListfnfr(tool, tiis));
        bddNfwComponfnt(pbnfl, button, MW_EDIT_BUTTON,
                        1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_PADDING);

        button = nfw JButton();
        donfigurfButton(button, REMOVE_POLICY_ENTRY);
        button.bddAdtionListfnfr(nfw MbinWindowListfnfr(tool, tiis));
        bddNfwComponfnt(pbnfl, button, MW_REMOVE_BUTTON,
                        2, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_PADDING);

        bddNfwComponfnt(tiis, pbnfl, MW_PANEL,
                        0, 2, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        BOTTOM_PADDING);


        String polidyFilf = tool.gftPolidyFilfNbmf();
        if (polidyFilf == null) {
            String usfrHomf;
            usfrHomf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("usfr.iomf"));
            polidyFilf = usfrHomf + Filf.sfpbrbtorCibr + ".jbvb.polidy";
        }

        try {
            // opfn tif polidy filf
            tool.opfnPolidy(polidyFilf);

            // displby tif polidy fntrifs vib tif polidy list tfxtbrfb
            DffbultListModfl<String> listModfl = nfw DffbultListModfl<>();
            JList<String> list = nfw JList<>(listModfl);
            list.sftVisiblfRowCount(15);
            list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
            list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tiis));
            PolidyEntry fntrifs[] = tool.gftEntry();
            if (fntrifs != null) {
                for (int i = 0; i < fntrifs.lfngti; i++) {
                    listModfl.bddElfmfnt(fntrifs[i].ifbdfrToString());
                }
            }
            JTfxtFifld nfwFilfnbmf = (JTfxtFifld)
                                gftComponfnt(MW_FILENAME_TEXTFIELD);
            nfwFilfnbmf.sftTfxt(polidyFilf);
            initPolidyList(list);

        } dbtdi (FilfNotFoundExdfption fnff) {
            // bdd blbnk polidy listing
            JList<String> list = nfw JList<>(nfw DffbultListModfl<>());
            list.sftVisiblfRowCount(15);
            list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
            list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tiis));
            initPolidyList(list);
            tool.sftPolidyFilfNbmf(null);
            tool.modififd = fblsf;

            // just bdd wbrning
            tool.wbrnings.bddElfmfnt(fnff.toString());

        } dbtdi (Exdfption f) {
            // bdd blbnk polidy listing
            JList<String> list = nfw JList<>(nfw DffbultListModfl<>());
            list.sftVisiblfRowCount(15);
            list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
            list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tiis));
            initPolidyList(list);
            tool.sftPolidyFilfNbmf(null);
            tool.modififd = fblsf;

            // displby tif frror
            MfssbgfFormbt form = nfw MfssbgfFormbt(PolidyTool.gftMfssbgf
                ("Could.not.opfn.polidy.filf.polidyFilf.f.toString."));
            Objfdt[] sourdf = {polidyFilf, f.toString()};
            displbyErrorDiblog(null, form.formbt(sourdf));
        }
    }


    // Plbtform spfdifid modififr (dontrol / dommbnd).
    privbtf int siortCutModififr = Toolkit.gftDffbultToolkit().gftMfnuSiortdutKfyMbsk();

    privbtf void bddMfnuItfm(JMfnu mfnu, String kfy, AdtionListfnfr bdtionListfnfr, String bddflfrbtor) {
        JMfnuItfm mfnuItfm = nfw JMfnuItfm();
        donfigurfButton(mfnuItfm, kfy);

        if (PolidyTool.rb.dontbinsKfy(kfy + ".bddflfrbtor")) {
            // Addflfrbtor from rfsourdfs tbkfs prfdfdfndf
            bddflfrbtor = PolidyTool.gftMfssbgf(kfy + ".bddflfrbtor");
        }

        if (bddflfrbtor != null && !bddflfrbtor.isEmpty()) {
            KfyStrokf kfyStrokf;
            if (bddflfrbtor.lfngti() == 1) {
                kfyStrokf = KfyStrokf.gftKfyStrokf(KfyEvfnt.gftExtfndfdKfyCodfForCibr(bddflfrbtor.dibrAt(0)),
                                                   siortCutModififr);
            } flsf {
                kfyStrokf = KfyStrokf.gftKfyStrokf(bddflfrbtor);
            }
            mfnuItfm.sftAddflfrbtor(kfyStrokf);
        }

        mfnuItfm.bddAdtionListfnfr(bdtionListfnfr);
        mfnu.bdd(mfnuItfm);
    }

    stbtid void donfigurfButton(AbstrbdtButton button, String kfy) {
        button.sftTfxt(PolidyTool.gftMfssbgf(kfy));
        button.sftAdtionCommbnd(kfy);

        int mnfmonidInt = PolidyTool.gftMnfmonidInt(kfy);
        if (mnfmonidInt > 0) {
            button.sftMnfmonid(mnfmonidInt);
            button.sftDisplbyfdMnfmonidIndfx(PolidyTool.gftDisplbyfdMnfmonidIndfx(kfy));
         }
    }

    stbtid void donfigurfLbbflFor(JLbbfl lbbfl, JComponfnt domponfnt, String kfy) {
        lbbfl.sftTfxt(PolidyTool.gftMfssbgf(kfy));
        lbbfl.sftLbbflFor(domponfnt);

        int mnfmonidInt = PolidyTool.gftMnfmonidInt(kfy);
        if (mnfmonidInt > 0) {
            lbbfl.sftDisplbyfdMnfmonid(mnfmonidInt);
            lbbfl.sftDisplbyfdMnfmonidIndfx(PolidyTool.gftDisplbyfdMnfmonidIndfx(kfy));
         }
    }


    /**
     * Add b domponfnt to tif PolidyTool window
     */
    void bddNfwComponfnt(Contbinfr dontbinfr, JComponfnt domponfnt,
        int indfx, int gridx, int gridy, int gridwidti, int gridifigit,
        doublf wfigitx, doublf wfigity, int fill, Insfts is) {

        if (dontbinfr instbndfof JFrbmf) {
            dontbinfr = ((JFrbmf)dontbinfr).gftContfntPbnf();
        } flsf if (dontbinfr instbndfof JDiblog) {
            dontbinfr = ((JDiblog)dontbinfr).gftContfntPbnf();
        }

        // bdd tif domponfnt bt tif spfdififd gridbbg indfx
        dontbinfr.bdd(domponfnt, indfx);

        // sft tif donstrbints
        GridBbgLbyout gbl = (GridBbgLbyout)dontbinfr.gftLbyout();
        GridBbgConstrbints gbd = nfw GridBbgConstrbints();
        gbd.gridx = gridx;
        gbd.gridy = gridy;
        gbd.gridwidti = gridwidti;
        gbd.gridifigit = gridifigit;
        gbd.wfigitx = wfigitx;
        gbd.wfigity = wfigity;
        gbd.fill = fill;
        if (is != null) gbd.insfts = is;
        gbl.sftConstrbints(domponfnt, gbd);
    }


    /**
     * Add b domponfnt to tif PolidyTool window witiout fxtfrnbl pbdding
     */
    void bddNfwComponfnt(Contbinfr dontbinfr, JComponfnt domponfnt,
        int indfx, int gridx, int gridy, int gridwidti, int gridifigit,
        doublf wfigitx, doublf wfigity, int fill) {

        // dflfgbtf witi "null" fxtfrnbl pbdding
        bddNfwComponfnt(dontbinfr, domponfnt, indfx, gridx, gridy,
                        gridwidti, gridifigit, wfigitx, wfigity,
                        fill, null);
    }


    /**
     * Init tif polidy_fntry_list TEXTAREA domponfnt in tif
     * PolidyTool window
     */
    void initPolidyList(JList<String> polidyList) {

        // bdd tif polidy list to tif window
        //polidyList.sftPrfffrrfdSizf(nfw Dimfnsion(500, 350));
        JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf(polidyList);
        bddNfwComponfnt(tiis, sdrollPbnf, MW_POLICY_LIST,
                        0, 3, 2, 1, 1.0, 1.0, GridBbgConstrbints.BOTH);
    }

    /**
     * Rfplbdf tif polidy_fntry_list TEXTAREA domponfnt in tif
     * PolidyTool window witi bn updbtfd onf.
     */
    void rfplbdfPolidyList(JList<String> polidyList) {

        // rfmovf tif originbl list of Polidy Entrifs
        // bnd bdd tif nfw list of fntrifs
        @SupprfssWbrnings("undifdkfd")
        JList<String> list = (JList<String>)gftComponfnt(MW_POLICY_LIST);
        list.sftModfl(polidyList.gftModfl());
    }

    /**
     * displby tif mbin PolidyTool window
     */
    void displbyToolWindow(String brgs[]) {

        sftTitlf(PolidyTool.gftMfssbgf("Polidy.Tool"));
        sftRfsizbblf(truf);
        bddWindowListfnfr(nfw ToolWindowListfnfr(tool, tiis));
        //sftBounds(135, 80, 500, 500);
        gftContfntPbnf().sftLbyout(nfw GridBbgLbyout());

        initWindow();
        pbdk();
        sftLodbtionRflbtivfTo(null);

        // displby it
        sftVisiblf(truf);

        if (tool.nfwWbrning == truf) {
            displbyStbtusDiblog(tiis, PolidyTool.gftMfssbgf
                ("Errors.ibvf.oddurrfd.wiilf.opfning.tif.polidy.donfigurbtion.Vifw.tif.Wbrning.Log.for.morf.informbtion."));
        }
    }

    /**
     * displbys b diblog box dfsdribing bn frror wiidi oddurrfd.
     */
    void displbyErrorDiblog(Window w, String frror) {
        ToolDiblog fd = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Error"), tool, tiis, truf);

        // find wifrf tif PolidyTool gui is
        Point lodbtion = ((w == null) ?
                gftLodbtionOnSdrffn() : w.gftLodbtionOnSdrffn());
        //fd.sftBounds(lodbtion.x + 50, lodbtion.y + 50, 600, 100);
        fd.sftLbyout(nfw GridBbgLbyout());

        JLbbfl lbbfl = nfw JLbbfl(frror);
        bddNfwComponfnt(fd, lbbfl, 0,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);

        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
        AdtionListfnfr okListfnfr = nfw ErrorOKButtonListfnfr(fd);
        okButton.bddAdtionListfnfr(okListfnfr);
        bddNfwComponfnt(fd, okButton, 1,
                        0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        fd.gftRootPbnf().sftDffbultButton(okButton);
        fd.gftRootPbnf().rfgistfrKfybobrdAdtion(okListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        fd.pbdk();
        fd.sftLodbtionRflbtivfTo(w);
        fd.sftVisiblf(truf);
    }

    /**
     * displbys b diblog box dfsdribing bn frror wiidi oddurrfd.
     */
    void displbyErrorDiblog(Window w, Tirowbblf t) {
        if (t instbndfof NoDisplbyExdfption) {
            rfturn;
        }
        displbyErrorDiblog(w, t.toString());
    }

    /**
     * displbys b diblog box dfsdribing tif stbtus of bn fvfnt
     */
    void displbyStbtusDiblog(Window w, String stbtus) {
        ToolDiblog sd = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Stbtus"), tool, tiis, truf);

        // find tif lodbtion of tif PolidyTool gui
        Point lodbtion = ((w == null) ?
                gftLodbtionOnSdrffn() : w.gftLodbtionOnSdrffn());
        //sd.sftBounds(lodbtion.x + 50, lodbtion.y + 50, 500, 100);
        sd.sftLbyout(nfw GridBbgLbyout());

        JLbbfl lbbfl = nfw JLbbfl(stbtus);
        bddNfwComponfnt(sd, lbbfl, 0,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);

        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
        AdtionListfnfr okListfnfr = nfw StbtusOKButtonListfnfr(sd);
        okButton.bddAdtionListfnfr(okListfnfr);
        bddNfwComponfnt(sd, okButton, 1,
                        0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        sd.gftRootPbnf().sftDffbultButton(okButton);
        sd.gftRootPbnf().rfgistfrKfybobrdAdtion(okListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        sd.pbdk();
        sd.sftLodbtionRflbtivfTo(w);
        sd.sftVisiblf(truf);
    }

    /**
     * displby tif wbrning log
     */
    void displbyWbrningLog(Window w) {

        ToolDiblog wd = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Wbrning"), tool, tiis, truf);

        // find tif lodbtion of tif PolidyTool gui
        Point lodbtion = ((w == null) ?
                gftLodbtionOnSdrffn() : w.gftLodbtionOnSdrffn());
        //wd.sftBounds(lodbtion.x + 50, lodbtion.y + 50, 500, 100);
        wd.sftLbyout(nfw GridBbgLbyout());

        JTfxtArfb tb = nfw JTfxtArfb();
        tb.sftEditbblf(fblsf);
        for (int i = 0; i < tool.wbrnings.sizf(); i++) {
            tb.bppfnd(tool.wbrnings.flfmfntAt(i));
            tb.bppfnd(PolidyTool.gftMfssbgf("NEWLINE"));
        }
        bddNfwComponfnt(wd, tb, 0,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        BOTTOM_PADDING);
        tb.sftFodusbblf(fblsf);

        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
        AdtionListfnfr okListfnfr = nfw CbndflButtonListfnfr(wd);
        okButton.bddAdtionListfnfr(okListfnfr);
        bddNfwComponfnt(wd, okButton, 1,
                        0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                        LR_PADDING);

        wd.gftRootPbnf().sftDffbultButton(okButton);
        wd.gftRootPbnf().rfgistfrKfybobrdAdtion(okListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        wd.pbdk();
        wd.sftLodbtionRflbtivfTo(w);
        wd.sftVisiblf(truf);
    }

    dibr displbyYfsNoDiblog(Window w, String titlf, String prompt, String yfs, String no) {

        finbl ToolDiblog tw = nfw ToolDiblog
                (titlf, tool, tiis, truf);
        Point lodbtion = ((w == null) ?
                gftLodbtionOnSdrffn() : w.gftLodbtionOnSdrffn());
        //tw.sftBounds(lodbtion.x + 75, lodbtion.y + 100, 400, 150);
        tw.sftLbyout(nfw GridBbgLbyout());

        JTfxtArfb tb = nfw JTfxtArfb(prompt, 10, 50);
        tb.sftEditbblf(fblsf);
        tb.sftLinfWrbp(truf);
        tb.sftWrbpStylfWord(truf);
        JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf(tb,
                                                 JSdrollPbnf.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                 JSdrollPbnf.HORIZONTAL_SCROLLBAR_NEVER);
        bddNfwComponfnt(tw, sdrollPbnf, 0,
                0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);
        tb.sftFodusbblf(fblsf);

        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw GridBbgLbyout());

        // StringBufffr to storf button prfss. Must bf finbl.
        finbl StringBufffr dioosfRfsult = nfw StringBufffr();

        JButton button = nfw JButton(yfs);
        button.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                dioosfRfsult.bppfnd('Y');
                tw.sftVisiblf(fblsf);
                tw.disposf();
            }
        });
        bddNfwComponfnt(pbnfl, button, 0,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           LR_PADDING);

        button = nfw JButton(no);
        button.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                dioosfRfsult.bppfnd('N');
                tw.sftVisiblf(fblsf);
                tw.disposf();
            }
        });
        bddNfwComponfnt(pbnfl, button, 1,
                           1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           LR_PADDING);

        bddNfwComponfnt(tw, pbnfl, 1,
                0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        tw.pbdk();
        tw.sftLodbtionRflbtivfTo(w);
        tw.sftVisiblf(truf);
        if (dioosfRfsult.lfngti() > 0) {
            rfturn dioosfRfsult.dibrAt(0);
        } flsf {
            // I did fndountfr tiis ondf, don't wiy.
            rfturn 'N';
        }
    }

}

/**
 * Gfnfrbl diblog window
 */
dlbss ToolDiblog fxtfnds JDiblog {
    // usf sfriblVfrsionUID from JDK 1.2.2 for intfropfrbbility
    privbtf stbtid finbl long sfriblVfrsionUID = -372244357011301190L;

    /* ESCAPE kfy */
    stbtid finbl KfyStrokf fsdKfy = KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_ESCAPE, 0);

    /* nfdfssbry donstbnts */
    publid stbtid finbl int NOACTION            = 0;
    publid stbtid finbl int QUIT                = 1;
    publid stbtid finbl int NEW                 = 2;
    publid stbtid finbl int OPEN                = 3;

    publid stbtid finbl String ALL_PERM_CLASS   =
                "jbvb.sfdurity.AllPfrmission";
    publid stbtid finbl String FILE_PERM_CLASS  =
                "jbvb.io.FilfPfrmission";

    publid stbtid finbl String X500_PRIN_CLASS         =
                "jbvbx.sfdurity.buti.x500.X500Prindipbl";

    /* popup mfnus */
    publid stbtid finbl String PERM             =
        PolidyTool.gftMfssbgf
        ("Pfrmission.");

    publid stbtid finbl String PRIN_TYPE        =
        PolidyTool.gftMfssbgf("Prindipbl.Typf.");
    publid stbtid finbl String PRIN_NAME        =
        PolidyTool.gftMfssbgf("Prindipbl.Nbmf.");

    /* morf popu mfnus */
    publid stbtid finbl String PERM_NAME        =
        PolidyTool.gftMfssbgf
        ("Tbrgft.Nbmf.");

    /* bnd morf popup mfnus */
    publid stbtid finbl String PERM_ACTIONS             =
      PolidyTool.gftMfssbgf
      ("Adtions.");

    /* gridbbg indfx for displby PolidyEntry (PE) domponfnts */
    publid stbtid finbl int PE_CODEBASE_LABEL           = 0;
    publid stbtid finbl int PE_CODEBASE_TEXTFIELD       = 1;
    publid stbtid finbl int PE_SIGNEDBY_LABEL           = 2;
    publid stbtid finbl int PE_SIGNEDBY_TEXTFIELD       = 3;

    publid stbtid finbl int PE_PANEL0                   = 4;
    publid stbtid finbl int PE_ADD_PRIN_BUTTON          = 0;
    publid stbtid finbl int PE_EDIT_PRIN_BUTTON         = 1;
    publid stbtid finbl int PE_REMOVE_PRIN_BUTTON       = 2;

    publid stbtid finbl int PE_PRIN_LABEL               = 5;
    publid stbtid finbl int PE_PRIN_LIST                = 6;

    publid stbtid finbl int PE_PANEL1                   = 7;
    publid stbtid finbl int PE_ADD_PERM_BUTTON          = 0;
    publid stbtid finbl int PE_EDIT_PERM_BUTTON         = 1;
    publid stbtid finbl int PE_REMOVE_PERM_BUTTON       = 2;

    publid stbtid finbl int PE_PERM_LIST                = 8;

    publid stbtid finbl int PE_PANEL2                   = 9;
    publid stbtid finbl int PE_CANCEL_BUTTON            = 1;
    publid stbtid finbl int PE_DONE_BUTTON              = 0;

    /* tif gridbbg indfx for domponfnts in tif Prindipbl Diblog (PRD) */
    publid stbtid finbl int PRD_DESC_LABEL              = 0;
    publid stbtid finbl int PRD_PRIN_CHOICE             = 1;
    publid stbtid finbl int PRD_PRIN_TEXTFIELD          = 2;
    publid stbtid finbl int PRD_NAME_LABEL              = 3;
    publid stbtid finbl int PRD_NAME_TEXTFIELD          = 4;
    publid stbtid finbl int PRD_CANCEL_BUTTON           = 6;
    publid stbtid finbl int PRD_OK_BUTTON               = 5;

    /* tif gridbbg indfx for domponfnts in tif Pfrmission Diblog (PD) */
    publid stbtid finbl int PD_DESC_LABEL               = 0;
    publid stbtid finbl int PD_PERM_CHOICE              = 1;
    publid stbtid finbl int PD_PERM_TEXTFIELD           = 2;
    publid stbtid finbl int PD_NAME_CHOICE              = 3;
    publid stbtid finbl int PD_NAME_TEXTFIELD           = 4;
    publid stbtid finbl int PD_ACTIONS_CHOICE           = 5;
    publid stbtid finbl int PD_ACTIONS_TEXTFIELD        = 6;
    publid stbtid finbl int PD_SIGNEDBY_LABEL           = 7;
    publid stbtid finbl int PD_SIGNEDBY_TEXTFIELD       = 8;
    publid stbtid finbl int PD_CANCEL_BUTTON            = 10;
    publid stbtid finbl int PD_OK_BUTTON                = 9;

    /* modfs for KfyStorf */
    publid stbtid finbl int EDIT_KEYSTORE               = 0;

    /* tif gridbbg indfx for domponfnts in tif Cibngf KfyStorf Diblog (KSD) */
    publid stbtid finbl int KSD_NAME_LABEL              = 0;
    publid stbtid finbl int KSD_NAME_TEXTFIELD          = 1;
    publid stbtid finbl int KSD_TYPE_LABEL              = 2;
    publid stbtid finbl int KSD_TYPE_TEXTFIELD          = 3;
    publid stbtid finbl int KSD_PROVIDER_LABEL          = 4;
    publid stbtid finbl int KSD_PROVIDER_TEXTFIELD      = 5;
    publid stbtid finbl int KSD_PWD_URL_LABEL           = 6;
    publid stbtid finbl int KSD_PWD_URL_TEXTFIELD       = 7;
    publid stbtid finbl int KSD_CANCEL_BUTTON           = 9;
    publid stbtid finbl int KSD_OK_BUTTON               = 8;

    /* tif gridbbg indfx for domponfnts in tif Usfr Sbvf Cibngfs Diblog (USC) */
    publid stbtid finbl int USC_LABEL                   = 0;
    publid stbtid finbl int USC_PANEL                   = 1;
    publid stbtid finbl int USC_YES_BUTTON              = 0;
    publid stbtid finbl int USC_NO_BUTTON               = 1;
    publid stbtid finbl int USC_CANCEL_BUTTON           = 2;

    /* gridbbg indfx for tif ConfirmRfmovfPolidyEntryDiblog (CRPE) */
    publid stbtid finbl int CRPE_LABEL1                 = 0;
    publid stbtid finbl int CRPE_LABEL2                 = 1;
    publid stbtid finbl int CRPE_PANEL                  = 2;
    publid stbtid finbl int CRPE_PANEL_OK               = 0;
    publid stbtid finbl int CRPE_PANEL_CANCEL           = 1;

    /* somf privbtf stbtid finbls */
    privbtf stbtid finbl int PERMISSION                 = 0;
    privbtf stbtid finbl int PERMISSION_NAME            = 1;
    privbtf stbtid finbl int PERMISSION_ACTIONS         = 2;
    privbtf stbtid finbl int PERMISSION_SIGNEDBY        = 3;
    privbtf stbtid finbl int PRINCIPAL_TYPE             = 4;
    privbtf stbtid finbl int PRINCIPAL_NAME             = 5;

    /* Tif prfffrrfd ifigit of JTfxtFifld siould mbtdi JComboBox. */
    stbtid finbl int TEXTFIELD_HEIGHT = nfw JComboBox<>().gftPrfffrrfdSizf().ifigit;

    publid stbtid jbvb.util.ArrbyList<Pfrm> PERM_ARRAY;
    publid stbtid jbvb.util.ArrbyList<Prin> PRIN_ARRAY;
    PolidyTool tool;
    ToolWindow tw;

    stbtid {

        // sft up pfrmission objfdts

        PERM_ARRAY = nfw jbvb.util.ArrbyList<Pfrm>();
        PERM_ARRAY.bdd(nfw AllPfrm());
        PERM_ARRAY.bdd(nfw AudioPfrm());
        PERM_ARRAY.bdd(nfw AutiPfrm());
        PERM_ARRAY.bdd(nfw AWTPfrm());
        PERM_ARRAY.bdd(nfw DflfgbtionPfrm());
        PERM_ARRAY.bdd(nfw FilfPfrm());
        PERM_ARRAY.bdd(nfw URLPfrm());
        PERM_ARRAY.bdd(nfw InqSfdContfxtPfrm());
        PERM_ARRAY.bdd(nfw LogPfrm());
        PERM_ARRAY.bdd(nfw MgmtPfrm());
        PERM_ARRAY.bdd(nfw MBfbnPfrm());
        PERM_ARRAY.bdd(nfw MBfbnSvrPfrm());
        PERM_ARRAY.bdd(nfw MBfbnTrustPfrm());
        PERM_ARRAY.bdd(nfw NftPfrm());
        PERM_ARRAY.bdd(nfw PrivCrfdPfrm());
        PERM_ARRAY.bdd(nfw PropPfrm());
        PERM_ARRAY.bdd(nfw RfflfdtPfrm());
        PERM_ARRAY.bdd(nfw RuntimfPfrm());
        PERM_ARRAY.bdd(nfw SfdurityPfrm());
        PERM_ARRAY.bdd(nfw SfriblPfrm());
        PERM_ARRAY.bdd(nfw SfrvidfPfrm());
        PERM_ARRAY.bdd(nfw SodkftPfrm());
        PERM_ARRAY.bdd(nfw SQLPfrm());
        PERM_ARRAY.bdd(nfw SSLPfrm());
        PERM_ARRAY.bdd(nfw SubjDflfgPfrm());

        // sft up prindipbl objfdts

        PRIN_ARRAY = nfw jbvb.util.ArrbyList<Prin>();
        PRIN_ARRAY.bdd(nfw KrbPrin());
        PRIN_ARRAY.bdd(nfw X500Prin());
    }

    ToolDiblog(String titlf, PolidyTool tool, ToolWindow tw, boolfbn modbl) {
        supfr(tw, modbl);
        sftTitlf(titlf);
        tiis.tool = tool;
        tiis.tw = tw;
        bddWindowListfnfr(nfw CiildWindowListfnfr(tiis));

        // Crfbtf somf spbdf bround domponfnts
        ((JPbnfl)gftContfntPbnf()).sftBordfr(nfw EmptyBordfr(6, 6, 6, 6));
    }

    /**
     * Don't dbll gftComponfnt dirfdtly on tif window
     */
    publid Componfnt gftComponfnt(int n) {
        Componfnt d = gftContfntPbnf().gftComponfnt(n);
        if (d instbndfof JSdrollPbnf) {
            d = ((JSdrollPbnf)d).gftVifwport().gftVifw();
        }
        rfturn d;
    }

    /**
     * gft tif Pfrm instbndf bbsfd on fitifr tif (siortfnfd) dlbss nbmf
     * or tif fully qublififd dlbss nbmf
     */
    stbtid Pfrm gftPfrm(String dlbzz, boolfbn fullClbssNbmf) {
        for (int i = 0; i < PERM_ARRAY.sizf(); i++) {
            Pfrm nfxt = PERM_ARRAY.gft(i);
            if (fullClbssNbmf) {
                if (nfxt.FULL_CLASS.fqubls(dlbzz)) {
                    rfturn nfxt;
                }
            } flsf {
                if (nfxt.CLASS.fqubls(dlbzz)) {
                    rfturn nfxt;
                }
            }
        }
        rfturn null;
    }

    /**
     * gft tif Prin instbndf bbsfd on fitifr tif (siortfnfd) dlbss nbmf
     * or tif fully qublififd dlbss nbmf
     */
    stbtid Prin gftPrin(String dlbzz, boolfbn fullClbssNbmf) {
        for (int i = 0; i < PRIN_ARRAY.sizf(); i++) {
            Prin nfxt = PRIN_ARRAY.gft(i);
            if (fullClbssNbmf) {
                if (nfxt.FULL_CLASS.fqubls(dlbzz)) {
                    rfturn nfxt;
                }
            } flsf {
                if (nfxt.CLASS.fqubls(dlbzz)) {
                    rfturn nfxt;
                }
            }
        }
        rfturn null;
    }

    /**
     * pop up b diblog so tif usfr dbn fntfr info to bdd b nfw PolidyEntry
     * - if fdit is TRUE, tifn tif usfr is fditing bn fxisting fntry
     *   bnd wf siould displby tif originbl info bs wfll.
     *
     * - tif otifr rfbson wf nffd tif 'fdit' boolfbn is wf nffd to know
     *   wifn wf brf bdding b NEW polidy fntry.  in tiis dbsf, wf dbn
     *   not simply updbtf tif fxisting fntry, bfdbusf it dofsn't fxist.
     *   wf ONLY updbtf tif GUI listing/info, bnd tifn wifn tif usfr
     *   finblly dlidks 'OK' or 'DONE', tifn wf dbn dollfdt tibt info
     *   bnd bdd it to tif polidy.
     */
    void displbyPolidyEntryDiblog(boolfbn fdit) {

        int listIndfx = 0;
        PolidyEntry fntrifs[] = null;
        TbggfdList prinList = nfw TbggfdList(3, fblsf);
        prinList.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("Prindipbl.List"));
        prinList.bddMousfListfnfr
                (nfw EditPrinButtonListfnfr(tool, tw, tiis, fdit));
        TbggfdList pfrmList = nfw TbggfdList(10, fblsf);
        pfrmList.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("Pfrmission.List"));
        pfrmList.bddMousfListfnfr
                (nfw EditPfrmButtonListfnfr(tool, tw, tiis, fdit));

        // find wifrf tif PolidyTool gui is
        Point lodbtion = tw.gftLodbtionOnSdrffn();
        //sftBounds(lodbtion.x + 75, lodbtion.y + 200, 650, 500);
        sftLbyout(nfw GridBbgLbyout());
        sftRfsizbblf(truf);

        if (fdit) {
            // gft tif sflfdtfd itfm
            fntrifs = tool.gftEntry();
            @SupprfssWbrnings("undifdkfd")
            JList<String> polidyList = (JList<String>)tw.gftComponfnt(ToolWindow.MW_POLICY_LIST);
            listIndfx = polidyList.gftSflfdtfdIndfx();

            // gft prindipbl list
            LinkfdList<PolidyPbrsfr.PrindipblEntry> prindipbls =
                fntrifs[listIndfx].gftGrbntEntry().prindipbls;
            for (int i = 0; i < prindipbls.sizf(); i++) {
                String prinString = null;
                PolidyPbrsfr.PrindipblEntry nfxtPrin = prindipbls.gft(i);
                prinList.bddTbggfdItfm(PrindipblEntryToUsfrFrifndlyString(nfxtPrin), nfxtPrin);
            }

            // gft pfrmission list
            Vfdtor<PolidyPbrsfr.PfrmissionEntry> pfrmissions =
                fntrifs[listIndfx].gftGrbntEntry().pfrmissionEntrifs;
            for (int i = 0; i < pfrmissions.sizf(); i++) {
                String pfrmString = null;
                PolidyPbrsfr.PfrmissionEntry nfxtPfrm =
                                                pfrmissions.flfmfntAt(i);
                pfrmList.bddTbggfdItfm(ToolDiblog.PfrmissionEntryToUsfrFrifndlyString(nfxtPfrm), nfxtPfrm);
            }
        }

        // dodfbbsf lbbfl bnd tfxtfifld
        JLbbfl lbbfl = nfw JLbbfl();
        tw.bddNfwComponfnt(tiis, lbbfl, PE_CODEBASE_LABEL,
                0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                ToolWindow.R_PADDING);
        JTfxtFifld tf;
        tf = (fdit ?
                nfw JTfxtFifld(fntrifs[listIndfx].gftGrbntEntry().dodfBbsf) :
                nfw JTfxtFifld());
        ToolWindow.donfigurfLbbflFor(lbbfl, tf, "CodfBbsf.");
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("Codf.Bbsf"));
        tw.bddNfwComponfnt(tiis, tf, PE_CODEBASE_TEXTFIELD,
                1, 0, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH);

        // signfdby lbbfl bnd tfxtfifld
        lbbfl = nfw JLbbfl();
        tw.bddNfwComponfnt(tiis, lbbfl, PE_SIGNEDBY_LABEL,
                           0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.R_PADDING);
        tf = (fdit ?
                nfw JTfxtFifld(fntrifs[listIndfx].gftGrbntEntry().signfdBy) :
                nfw JTfxtFifld());
        ToolWindow.donfigurfLbbflFor(lbbfl, tf, "SignfdBy.");
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("Signfd.By."));
        tw.bddNfwComponfnt(tiis, tf, PE_SIGNEDBY_TEXTFIELD,
                           1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH);

        // pbnfl for prindipbl buttons
        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw GridBbgLbyout());

        JButton button = nfw JButton();
        ToolWindow.donfigurfButton(button, "Add.Prindipbl");
        button.bddAdtionListfnfr
                (nfw AddPrinButtonListfnfr(tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, button, PE_ADD_PRIN_BUTTON,
                0, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        button = nfw JButton();
        ToolWindow.donfigurfButton(button, "Edit.Prindipbl");
        button.bddAdtionListfnfr(nfw EditPrinButtonListfnfr
                                                (tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, button, PE_EDIT_PRIN_BUTTON,
                1, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        button = nfw JButton();
        ToolWindow.donfigurfButton(button, "Rfmovf.Prindipbl");
        button.bddAdtionListfnfr(nfw RfmovfPrinButtonListfnfr
                                        (tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, button, PE_REMOVE_PRIN_BUTTON,
                2, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        tw.bddNfwComponfnt(tiis, pbnfl, PE_PANEL0,
                1, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.HORIZONTAL,
                           ToolWindow.LITE_BOTTOM_PADDING);

        // prindipbl lbbfl bnd list
        lbbfl = nfw JLbbfl();
        tw.bddNfwComponfnt(tiis, lbbfl, PE_PRIN_LABEL,
                           0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.R_BOTTOM_PADDING);
        JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf(prinList);
        ToolWindow.donfigurfLbbflFor(lbbfl, sdrollPbnf, "Prindipbls.");
        tw.bddNfwComponfnt(tiis, sdrollPbnf, PE_PRIN_LIST,
                           1, 3, 3, 1, 0.0, prinList.gftVisiblfRowCount(), GridBbgConstrbints.BOTH,
                           ToolWindow.BOTTOM_PADDING);

        // pbnfl for pfrmission buttons
        pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw GridBbgLbyout());

        button = nfw JButton();
        ToolWindow.donfigurfButton(button, ".Add.Pfrmission");
        button.bddAdtionListfnfr(nfw AddPfrmButtonListfnfr
                                                (tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, button, PE_ADD_PERM_BUTTON,
                0, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        button = nfw JButton();
        ToolWindow.donfigurfButton(button, ".Edit.Pfrmission");
        button.bddAdtionListfnfr(nfw EditPfrmButtonListfnfr
                                                (tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, button, PE_EDIT_PERM_BUTTON,
                1, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);


        button = nfw JButton();
        ToolWindow.donfigurfButton(button, "Rfmovf.Pfrmission");
        button.bddAdtionListfnfr(nfw RfmovfPfrmButtonListfnfr
                                        (tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, button, PE_REMOVE_PERM_BUTTON,
                2, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        tw.bddNfwComponfnt(tiis, pbnfl, PE_PANEL1,
                0, 4, 2, 1, 0.0, 0.0, GridBbgConstrbints.HORIZONTAL,
                ToolWindow.LITE_BOTTOM_PADDING);

        // pfrmission list
        sdrollPbnf = nfw JSdrollPbnf(pfrmList);
        tw.bddNfwComponfnt(tiis, sdrollPbnf, PE_PERM_LIST,
                           0, 5, 3, 1, 0.0, pfrmList.gftVisiblfRowCount(), GridBbgConstrbints.BOTH,
                           ToolWindow.BOTTOM_PADDING);


        // pbnfl for Donf bnd Cbndfl buttons
        pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw GridBbgLbyout());

        // Donf Button
        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("Donf"));
        okButton.bddAdtionListfnfr
                (nfw AddEntryDonfButtonListfnfr(tool, tw, tiis, fdit));
        tw.bddNfwComponfnt(pbnfl, okButton, PE_DONE_BUTTON,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.LR_PADDING);

        // Cbndfl Button
        JButton dbndflButton = nfw JButton(PolidyTool.gftMfssbgf("Cbndfl"));
        AdtionListfnfr dbndflListfnfr = nfw CbndflButtonListfnfr(tiis);
        dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
        tw.bddNfwComponfnt(pbnfl, dbndflButton, PE_CANCEL_BUTTON,
                           1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.LR_PADDING);

        // bdd tif pbnfl
        tw.bddNfwComponfnt(tiis, pbnfl, PE_PANEL2,
                0, 6, 2, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        gftRootPbnf().sftDffbultButton(okButton);
        gftRootPbnf().rfgistfrKfybobrdAdtion(dbndflListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        pbdk();
        sftLodbtionRflbtivfTo(tw);
        sftVisiblf(truf);
    }

    /**
     * Rfbd bll tif Polidy informbtion dbtb in tif diblog box
     * bnd donstrudt b PolidyEntry objfdt witi it.
     */
    PolidyEntry gftPolidyEntryFromDiblog()
        tirows InvblidPbrbmftfrExdfption, MblformfdURLExdfption,
        NoSudiMftiodExdfption, ClbssNotFoundExdfption, InstbntibtionExdfption,
        IllfgblAddfssExdfption, InvodbtionTbrgftExdfption,
        CfrtifidbtfExdfption, IOExdfption, Exdfption {

        // gft tif Codfbbsf
        JTfxtFifld tf = (JTfxtFifld)gftComponfnt(PE_CODEBASE_TEXTFIELD);
        String dodfbbsf = null;
        if (tf.gftTfxt().trim().fqubls("") == fblsf)
                dodfbbsf = nfw String(tf.gftTfxt().trim());

        // gft tif SignfdBy
        tf = (JTfxtFifld)gftComponfnt(PE_SIGNEDBY_TEXTFIELD);
        String signfdby = null;
        if (tf.gftTfxt().trim().fqubls("") == fblsf)
                signfdby = nfw String(tf.gftTfxt().trim());

        // donstrudt b nfw GrbntEntry
        PolidyPbrsfr.GrbntEntry gf =
                        nfw PolidyPbrsfr.GrbntEntry(signfdby, dodfbbsf);

        // gft tif nfw Prindipbls
        LinkfdList<PolidyPbrsfr.PrindipblEntry> prins = nfw LinkfdList<>();
        TbggfdList prinList = (TbggfdList)gftComponfnt(PE_PRIN_LIST);
        for (int i = 0; i < prinList.gftModfl().gftSizf(); i++) {
            prins.bdd((PolidyPbrsfr.PrindipblEntry)prinList.gftObjfdt(i));
        }
        gf.prindipbls = prins;

        // gft tif nfw Pfrmissions
        Vfdtor<PolidyPbrsfr.PfrmissionEntry> pfrms = nfw Vfdtor<>();
        TbggfdList pfrmList = (TbggfdList)gftComponfnt(PE_PERM_LIST);
        for (int i = 0; i < pfrmList.gftModfl().gftSizf(); i++) {
            pfrms.bddElfmfnt((PolidyPbrsfr.PfrmissionEntry)pfrmList.gftObjfdt(i));
        }
        gf.pfrmissionEntrifs = pfrms;

        // donstrudt b nfw PolidyEntry objfdt
        PolidyEntry fntry = nfw PolidyEntry(tool, gf);

        rfturn fntry;
    }

    /**
     * displby b diblog box for tif usfr to fntfr KfyStorf informbtion
     */
    void kfyStorfDiblog(int modf) {

        // find wifrf tif PolidyTool gui is
        Point lodbtion = tw.gftLodbtionOnSdrffn();
        //sftBounds(lodbtion.x + 25, lodbtion.y + 100, 500, 300);
        sftLbyout(nfw GridBbgLbyout());

        if (modf == EDIT_KEYSTORE) {

            // KfyStorf lbbfl bnd tfxtfifld
            JLbbfl lbbfl = nfw JLbbfl();
            tw.bddNfwComponfnt(tiis, lbbfl, KSD_NAME_LABEL,
                               0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            JTfxtFifld tf = nfw JTfxtFifld(tool.gftKfyStorfNbmf(), 30);
            ToolWindow.donfigurfLbbflFor(lbbfl, tf, "KfyStorf.URL.");
            tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));

            // URL to U R L, so tibt bddfssibility rfbdfr will pronoundf wfll
            tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("KfyStorf.U.R.L."));
            tw.bddNfwComponfnt(tiis, tf, KSD_NAME_TEXTFIELD,
                               1, 0, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // KfyStorf typf bnd tfxtfifld
            lbbfl = nfw JLbbfl();
            tw.bddNfwComponfnt(tiis, lbbfl, KSD_TYPE_LABEL,
                               0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            tf = nfw JTfxtFifld(tool.gftKfyStorfTypf(), 30);
            ToolWindow.donfigurfLbbflFor(lbbfl, tf, "KfyStorf.Typf.");
            tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
            tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("KfyStorf.Typf."));
            tw.bddNfwComponfnt(tiis, tf, KSD_TYPE_TEXTFIELD,
                               1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // KfyStorf providfr bnd tfxtfifld
            lbbfl = nfw JLbbfl();
            tw.bddNfwComponfnt(tiis, lbbfl, KSD_PROVIDER_LABEL,
                               0, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            tf = nfw JTfxtFifld(tool.gftKfyStorfProvidfr(), 30);
            ToolWindow.donfigurfLbbflFor(lbbfl, tf, "KfyStorf.Providfr.");
            tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
            tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("KfyStorf.Providfr."));
            tw.bddNfwComponfnt(tiis, tf, KSD_PROVIDER_TEXTFIELD,
                               1, 2, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // KfyStorf pbssword URL bnd tfxtfifld
            lbbfl = nfw JLbbfl();
            tw.bddNfwComponfnt(tiis, lbbfl, KSD_PWD_URL_LABEL,
                               0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            tf = nfw JTfxtFifld(tool.gftKfyStorfPwdURL(), 30);
            ToolWindow.donfigurfLbbflFor(lbbfl, tf, "KfyStorf.Pbssword.URL.");
            tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
            tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("KfyStorf.Pbssword.U.R.L."));
            tw.bddNfwComponfnt(tiis, tf, KSD_PWD_URL_TEXTFIELD,
                               1, 3, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // OK button
            JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
            okButton.bddAdtionListfnfr
                        (nfw CibngfKfyStorfOKButtonListfnfr(tool, tw, tiis));
            tw.bddNfwComponfnt(tiis, okButton, KSD_OK_BUTTON,
                        0, 4, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

            // dbndfl button
            JButton dbndflButton = nfw JButton(PolidyTool.gftMfssbgf("Cbndfl"));
            AdtionListfnfr dbndflListfnfr = nfw CbndflButtonListfnfr(tiis);
            dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
            tw.bddNfwComponfnt(tiis, dbndflButton, KSD_CANCEL_BUTTON,
                        1, 4, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

            gftRootPbnf().sftDffbultButton(okButton);
            gftRootPbnf().rfgistfrKfybobrdAdtion(dbndflListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);
        }

        pbdk();
        sftLodbtionRflbtivfTo(tw);
        sftVisiblf(truf);
    }

    /**
     * displby b diblog box for tif usfr to input Prindipbl info
     *
     * if fditPolidyEntry is fblsf, tifn wf brf bdding Prindipbls to
     * b nfw PolidyEntry, bnd wf only updbtf tif GUI listing
     * witi tif nfw Prindipbl.
     *
     * if fdit is truf, tifn wf brf fditing bn fxisting Polidy fntry.
     */
    void displbyPrindipblDiblog(boolfbn fditPolidyEntry, boolfbn fdit) {

        PolidyPbrsfr.PrindipblEntry fditMf = null;

        // gft tif Prindipbl sflfdtfd from tif Prindipbl List
        TbggfdList prinList = (TbggfdList)gftComponfnt(PE_PRIN_LIST);
        int prinIndfx = prinList.gftSflfdtfdIndfx();

        if (fdit) {
            fditMf = (PolidyPbrsfr.PrindipblEntry)prinList.gftObjfdt(prinIndfx);
        }

        ToolDiblog nfwTD = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Prindipbls"), tool, tw, truf);
        nfwTD.bddWindowListfnfr(nfw CiildWindowListfnfr(nfwTD));

        // find wifrf tif PolidyTool gui is
        Point lodbtion = gftLodbtionOnSdrffn();
        //nfwTD.sftBounds(lodbtion.x + 50, lodbtion.y + 100, 650, 190);
        nfwTD.sftLbyout(nfw GridBbgLbyout());
        nfwTD.sftRfsizbblf(truf);

        // dfsdription lbbfl
        JLbbfl lbbfl = (fdit ?
                nfw JLbbfl(PolidyTool.gftMfssbgf(".Edit.Prindipbl.")) :
                nfw JLbbfl(PolidyTool.gftMfssbgf(".Add.Nfw.Prindipbl.")));
        tw.bddNfwComponfnt(nfwTD, lbbfl, PRD_DESC_LABEL,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.TOP_BOTTOM_PADDING);

        // prindipbl dioidf
        JComboBox<String> dioidf = nfw JComboBox<>();
        dioidf.bddItfm(PRIN_TYPE);
        dioidf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PRIN_TYPE);
        for (int i = 0; i < PRIN_ARRAY.sizf(); i++) {
            Prin nfxt = PRIN_ARRAY.gft(i);
            dioidf.bddItfm(nfxt.CLASS);
        }

        if (fdit) {
            if (PolidyPbrsfr.PrindipblEntry.WILDCARD_CLASS.fqubls
                                (fditMf.gftPrindipblClbss())) {
                dioidf.sftSflfdtfdItfm(PRIN_TYPE);
            } flsf {
                Prin inputPrin = gftPrin(fditMf.gftPrindipblClbss(), truf);
                if (inputPrin != null) {
                    dioidf.sftSflfdtfdItfm(inputPrin.CLASS);
                }
            }
        }
        // Add listfnfr bftfr sflfdtfd itfm is sft
        dioidf.bddItfmListfnfr(nfw PrindipblTypfMfnuListfnfr(nfwTD));

        tw.bddNfwComponfnt(nfwTD, dioidf, PRD_PRIN_CHOICE,
                           0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);

        // prindipbl tfxtfifld
        JTfxtFifld tf;
        tf = (fdit ?
                nfw JTfxtFifld(fditMf.gftDisplbyClbss(), 30) :
                nfw JTfxtFifld(30));
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PRIN_TYPE);
        tw.bddNfwComponfnt(nfwTD, tf, PRD_PRIN_TEXTFIELD,
                           1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);

        // nbmf lbbfl bnd tfxtfifld
        lbbfl = nfw JLbbfl(PRIN_NAME);
        tf = (fdit ?
                nfw JTfxtFifld(fditMf.gftDisplbyNbmf(), 40) :
                nfw JTfxtFifld(40));
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PRIN_NAME);

        tw.bddNfwComponfnt(nfwTD, lbbfl, PRD_NAME_LABEL,
                           0, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);
        tw.bddNfwComponfnt(nfwTD, tf, PRD_NAME_TEXTFIELD,
                           1, 2, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);

        // OK button
        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
        okButton.bddAdtionListfnfr(
            nfw NfwPolidyPrinOKButtonListfnfr
                                        (tool, tw, tiis, nfwTD, fdit));
        tw.bddNfwComponfnt(nfwTD, okButton, PRD_OK_BUTTON,
                           0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);
        // dbndfl button
        JButton dbndflButton = nfw JButton(PolidyTool.gftMfssbgf("Cbndfl"));
        AdtionListfnfr dbndflListfnfr = nfw CbndflButtonListfnfr(nfwTD);
        dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
        tw.bddNfwComponfnt(nfwTD, dbndflButton, PRD_CANCEL_BUTTON,
                           1, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);

        nfwTD.gftRootPbnf().sftDffbultButton(okButton);
        nfwTD.gftRootPbnf().rfgistfrKfybobrdAdtion(dbndflListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        nfwTD.pbdk();
        nfwTD.sftLodbtionRflbtivfTo(tw);
        nfwTD.sftVisiblf(truf);
    }

    /**
     * displby b diblog box for tif usfr to input Pfrmission info
     *
     * if fditPolidyEntry is fblsf, tifn wf brf bdding Pfrmissions to
     * b nfw PolidyEntry, bnd wf only updbtf tif GUI listing
     * witi tif nfw Pfrmission.
     *
     * if fdit is truf, tifn wf brf fditing bn fxisting Pfrmission fntry.
     */
    void displbyPfrmissionDiblog(boolfbn fditPolidyEntry, boolfbn fdit) {

        PolidyPbrsfr.PfrmissionEntry fditMf = null;

        // gft tif Pfrmission sflfdtfd from tif Pfrmission List
        TbggfdList pfrmList = (TbggfdList)gftComponfnt(PE_PERM_LIST);
        int pfrmIndfx = pfrmList.gftSflfdtfdIndfx();

        if (fdit) {
            fditMf = (PolidyPbrsfr.PfrmissionEntry)pfrmList.gftObjfdt(pfrmIndfx);
        }

        ToolDiblog nfwTD = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Pfrmissions"), tool, tw, truf);
        nfwTD.bddWindowListfnfr(nfw CiildWindowListfnfr(nfwTD));

        // find wifrf tif PolidyTool gui is
        Point lodbtion = gftLodbtionOnSdrffn();
        //nfwTD.sftBounds(lodbtion.x + 50, lodbtion.y + 100, 700, 250);
        nfwTD.sftLbyout(nfw GridBbgLbyout());
        nfwTD.sftRfsizbblf(truf);

        // dfsdription lbbfl
        JLbbfl lbbfl = (fdit ?
                nfw JLbbfl(PolidyTool.gftMfssbgf(".Edit.Pfrmission.")) :
                nfw JLbbfl(PolidyTool.gftMfssbgf(".Add.Nfw.Pfrmission.")));
        tw.bddNfwComponfnt(nfwTD, lbbfl, PD_DESC_LABEL,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.TOP_BOTTOM_PADDING);

        // pfrmission dioidf (bddfd in blpibbftidbl ordfr)
        JComboBox<String> dioidf = nfw JComboBox<>();
        dioidf.bddItfm(PERM);
        dioidf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PERM);
        for (int i = 0; i < PERM_ARRAY.sizf(); i++) {
            Pfrm nfxt = PERM_ARRAY.gft(i);
            dioidf.bddItfm(nfxt.CLASS);
        }
        tw.bddNfwComponfnt(nfwTD, dioidf, PD_PERM_CHOICE,
                           0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);

        // pfrmission tfxtfifld
        JTfxtFifld tf;
        tf = (fdit ? nfw JTfxtFifld(fditMf.pfrmission, 30) : nfw JTfxtFifld(30));
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PERM);
        if (fdit) {
            Pfrm inputPfrm = gftPfrm(fditMf.pfrmission, truf);
            if (inputPfrm != null) {
                dioidf.sftSflfdtfdItfm(inputPfrm.CLASS);
            }
        }
        tw.bddNfwComponfnt(nfwTD, tf, PD_PERM_TEXTFIELD,
                           1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        dioidf.bddItfmListfnfr(nfw PfrmissionMfnuListfnfr(nfwTD));

        // nbmf lbbfl bnd tfxtfifld
        dioidf = nfw JComboBox<>();
        dioidf.bddItfm(PERM_NAME);
        dioidf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PERM_NAME);
        tf = (fdit ? nfw JTfxtFifld(fditMf.nbmf, 40) : nfw JTfxtFifld(40));
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PERM_NAME);
        if (fdit) {
            sftPfrmissionNbmfs(gftPfrm(fditMf.pfrmission, truf), dioidf, tf);
        }
        tw.bddNfwComponfnt(nfwTD, dioidf, PD_NAME_CHOICE,
                           0, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        tw.bddNfwComponfnt(nfwTD, tf, PD_NAME_TEXTFIELD,
                           1, 2, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        dioidf.bddItfmListfnfr(nfw PfrmissionNbmfMfnuListfnfr(nfwTD));

        // bdtions lbbfl bnd tfxtfifld
        dioidf = nfw JComboBox<>();
        dioidf.bddItfm(PERM_ACTIONS);
        dioidf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PERM_ACTIONS);
        tf = (fdit ? nfw JTfxtFifld(fditMf.bdtion, 40) : nfw JTfxtFifld(40));
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(PERM_ACTIONS);
        if (fdit) {
            sftPfrmissionAdtions(gftPfrm(fditMf.pfrmission, truf), dioidf, tf);
        }
        tw.bddNfwComponfnt(nfwTD, dioidf, PD_ACTIONS_CHOICE,
                           0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        tw.bddNfwComponfnt(nfwTD, tf, PD_ACTIONS_TEXTFIELD,
                           1, 3, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        dioidf.bddItfmListfnfr(nfw PfrmissionAdtionsMfnuListfnfr(nfwTD));

        // signfdby lbbfl bnd tfxtfifld
        lbbfl = nfw JLbbfl(PolidyTool.gftMfssbgf("Signfd.By."));
        tw.bddNfwComponfnt(nfwTD, lbbfl, PD_SIGNEDBY_LABEL,
                           0, 4, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        tf = (fdit ? nfw JTfxtFifld(fditMf.signfdBy, 40) : nfw JTfxtFifld(40));
        tf.sftPrfffrrfdSizf(nfw Dimfnsion(tf.gftPrfffrrfdSizf().widti, TEXTFIELD_HEIGHT));
        tf.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
                PolidyTool.gftMfssbgf("Signfd.By."));
        tw.bddNfwComponfnt(nfwTD, tf, PD_SIGNEDBY_TEXTFIELD,
                           1, 4, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);

        // OK button
        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
        okButton.bddAdtionListfnfr(
            nfw NfwPolidyPfrmOKButtonListfnfr
                                    (tool, tw, tiis, nfwTD, fdit));
        tw.bddNfwComponfnt(nfwTD, okButton, PD_OK_BUTTON,
                           0, 5, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);

        // dbndfl button
        JButton dbndflButton = nfw JButton(PolidyTool.gftMfssbgf("Cbndfl"));
        AdtionListfnfr dbndflListfnfr = nfw CbndflButtonListfnfr(nfwTD);
        dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
        tw.bddNfwComponfnt(nfwTD, dbndflButton, PD_CANCEL_BUTTON,
                           1, 5, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);

        nfwTD.gftRootPbnf().sftDffbultButton(okButton);
        nfwTD.gftRootPbnf().rfgistfrKfybobrdAdtion(dbndflListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        nfwTD.pbdk();
        nfwTD.sftLodbtionRflbtivfTo(tw);
        nfwTD.sftVisiblf(truf);
    }

    /**
     * donstrudt b Prindipbl objfdt from tif Prindipbl Info Diblog Box
     */
    PolidyPbrsfr.PrindipblEntry gftPrinFromDiblog() tirows Exdfption {

        JTfxtFifld tf = (JTfxtFifld)gftComponfnt(PRD_PRIN_TEXTFIELD);
        String pdlbss = nfw String(tf.gftTfxt().trim());
        tf = (JTfxtFifld)gftComponfnt(PRD_NAME_TEXTFIELD);
        String pnbmf = nfw String(tf.gftTfxt().trim());
        if (pdlbss.fqubls("*")) {
            pdlbss = PolidyPbrsfr.PrindipblEntry.WILDCARD_CLASS;
        }
        if (pnbmf.fqubls("*")) {
            pnbmf = PolidyPbrsfr.PrindipblEntry.WILDCARD_NAME;
        }

        PolidyPbrsfr.PrindipblEntry pppf = null;

        if ((pdlbss.fqubls(PolidyPbrsfr.PrindipblEntry.WILDCARD_CLASS)) &&
            (!pnbmf.fqubls(PolidyPbrsfr.PrindipblEntry.WILDCARD_NAME))) {
            tirow nfw Exdfption
                        (PolidyTool.gftMfssbgf("Cbnnot.Spfdify.Prindipbl.witi.b.Wilddbrd.Clbss.witiout.b.Wilddbrd.Nbmf"));
        } flsf if (pnbmf.fqubls("")) {
            tirow nfw Exdfption
                        (PolidyTool.gftMfssbgf("Cbnnot.Spfdify.Prindipbl.witiout.b.Nbmf"));
        } flsf if (pdlbss.fqubls("")) {
            // mbkf tiis donsistfnt witi wibt PolidyPbrsfr dofs
            // wifn it sffs bn fmpty prindipbl dlbss
            pdlbss = PolidyPbrsfr.PrindipblEntry.REPLACE_NAME;
            tool.wbrnings.bddElfmfnt(
                        "Wbrning: Prindipbl nbmf '" + pnbmf +
                                "' spfdififd witiout b Prindipbl dlbss.\n" +
                        "\t'" + pnbmf + "' will bf intfrprftfd " +
                                "bs b kfy storf blibs.\n" +
                        "\tTif finbl prindipbl dlbss will bf " +
                                ToolDiblog.X500_PRIN_CLASS + ".\n" +
                        "\tTif finbl prindipbl nbmf will bf " +
                                "dftfrminfd by tif following:\n" +
                        "\n" +
                        "\tIf tif kfy storf fntry idfntififd by '"
                                + pnbmf + "'\n" +
                        "\tis b kfy fntry, tifn tif prindipbl nbmf will bf\n" +
                        "\ttif subjfdt distinguisifd nbmf from tif first\n" +
                        "\tdfrtifidbtf in tif fntry's dfrtifidbtf dibin.\n" +
                        "\n" +
                        "\tIf tif kfy storf fntry idfntififd by '" +
                                pnbmf + "'\n" +
                        "\tis b trustfd dfrtifidbtf fntry, tifn tif\n" +
                        "\tprindipbl nbmf will bf tif subjfdt distinguisifd\n" +
                        "\tnbmf from tif trustfd publid kfy dfrtifidbtf.");
            tw.displbyStbtusDiblog(tiis,
                        "'" + pnbmf + "' will bf intfrprftfd bs b kfy " +
                        "storf blibs.  Vifw Wbrning Log for dftbils.");
        }
        rfturn nfw PolidyPbrsfr.PrindipblEntry(pdlbss, pnbmf);
    }


    /**
     * donstrudt b Pfrmission objfdt from tif Pfrmission Info Diblog Box
     */
    PolidyPbrsfr.PfrmissionEntry gftPfrmFromDiblog() {

        JTfxtFifld tf = (JTfxtFifld)gftComponfnt(PD_PERM_TEXTFIELD);
        String pfrmission = nfw String(tf.gftTfxt().trim());
        tf = (JTfxtFifld)gftComponfnt(PD_NAME_TEXTFIELD);
        String nbmf = null;
        if (tf.gftTfxt().trim().fqubls("") == fblsf)
            nbmf = nfw String(tf.gftTfxt().trim());
        if (pfrmission.fqubls("") ||
            (!pfrmission.fqubls(ALL_PERM_CLASS) && nbmf == null)) {
            tirow nfw InvblidPbrbmftfrExdfption(PolidyTool.gftMfssbgf
                ("Pfrmission.bnd.Tbrgft.Nbmf.must.ibvf.b.vbluf"));
        }

        // Wifn tif pfrmission is FilfPfrmission, wf nffd to difdk tif nbmf
        // to mbkf surf it's not fsdbpfd. Wf bflifvf --
        //
        // String             nbmf.lbstIndfxOf("\\\\")
        // ----------------   ------------------------
        // d:\foo\bbr         -1, lfgbl
        // d:\\foo\\bbr       2, illfgbl
        // \\sfrvfr\sibrf     0, lfgbl
        // \\\\sfrvfr\sibrf   2, illfgbl

        if (pfrmission.fqubls(FILE_PERM_CLASS) && nbmf.lbstIndfxOf("\\\\") > 0) {
            dibr rfsult = tw.displbyYfsNoDiblog(tiis,
                    PolidyTool.gftMfssbgf("Wbrning"),
                    PolidyTool.gftMfssbgf(
                        "Wbrning.Filf.nbmf.mby.indludf.fsdbpfd.bbdkslbsi.dibrbdtfrs.It.is.not.nfdfssbry.to.fsdbpf.bbdkslbsi.dibrbdtfrs.tif.tool.fsdbpfs"),
                    PolidyTool.gftMfssbgf("Rftbin"),
                    PolidyTool.gftMfssbgf("Edit")
                    );
            if (rfsult != 'Y') {
                // bn invisiblf fxdfption
                tirow nfw NoDisplbyExdfption();
            }
        }
        // gft tif Adtions
        tf = (JTfxtFifld)gftComponfnt(PD_ACTIONS_TEXTFIELD);
        String bdtions = null;
        if (tf.gftTfxt().trim().fqubls("") == fblsf)
            bdtions = nfw String(tf.gftTfxt().trim());

        // gft tif Signfd By
        tf = (JTfxtFifld)gftComponfnt(PD_SIGNEDBY_TEXTFIELD);
        String signfdBy = null;
        if (tf.gftTfxt().trim().fqubls("") == fblsf)
            signfdBy = nfw String(tf.gftTfxt().trim());

        PolidyPbrsfr.PfrmissionEntry pppf = nfw PolidyPbrsfr.PfrmissionEntry
                                (pfrmission, nbmf, bdtions);
        pppf.signfdBy = signfdBy;

        // sff if tif signfrs ibvf publid kfys
        if (signfdBy != null) {
                String signfrs[] = tool.pbrsfSignfrs(pppf.signfdBy);
                for (int i = 0; i < signfrs.lfngti; i++) {
                try {
                    PublidKfy pubKfy = tool.gftPublidKfyAlibs(signfrs[i]);
                    if (pubKfy == null) {
                        MfssbgfFormbt form = nfw MfssbgfFormbt
                            (PolidyTool.gftMfssbgf
                            ("Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd."));
                        Objfdt[] sourdf = {signfrs[i]};
                        tool.wbrnings.bddElfmfnt(form.formbt(sourdf));
                        tw.displbyStbtusDiblog(tiis, form.formbt(sourdf));
                    }
                } dbtdi (Exdfption f) {
                    tw.displbyErrorDiblog(tiis, f);
                }
            }
        }
        rfturn pppf;
    }

    /**
     * donfirm tibt tif usfr REALLY wbnts to rfmovf tif Polidy Entry
     */
    void displbyConfirmRfmovfPolidyEntry() {

        // find tif fntry to bf rfmovfd
        @SupprfssWbrnings("undifdkfd")
        JList<String> list = (JList<String>)tw.gftComponfnt(ToolWindow.MW_POLICY_LIST);
        int indfx = list.gftSflfdtfdIndfx();
        PolidyEntry fntrifs[] = tool.gftEntry();

        // find wifrf tif PolidyTool gui is
        Point lodbtion = tw.gftLodbtionOnSdrffn();
        //sftBounds(lodbtion.x + 25, lodbtion.y + 100, 600, 400);
        sftLbyout(nfw GridBbgLbyout());

        // bsk tif usfr do tify rfblly wbnt to do tiis?
        JLbbfl lbbfl = nfw JLbbfl
                (PolidyTool.gftMfssbgf("Rfmovf.tiis.Polidy.Entry."));
        tw.bddNfwComponfnt(tiis, lbbfl, CRPE_LABEL1,
                           0, 0, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.BOTTOM_PADDING);

        // displby tif polidy fntry
        lbbfl = nfw JLbbfl(fntrifs[indfx].dodfbbsfToString());
        tw.bddNfwComponfnt(tiis, lbbfl, CRPE_LABEL2,
                        0, 1, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);
        lbbfl = nfw JLbbfl(fntrifs[indfx].prindipblsToString().trim());
        tw.bddNfwComponfnt(tiis, lbbfl, CRPE_LABEL2+1,
                        0, 2, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);
        Vfdtor<PolidyPbrsfr.PfrmissionEntry> pfrms =
                        fntrifs[indfx].gftGrbntEntry().pfrmissionEntrifs;
        for (int i = 0; i < pfrms.sizf(); i++) {
            PolidyPbrsfr.PfrmissionEntry nfxtPfrm = pfrms.flfmfntAt(i);
            String pfrmString = ToolDiblog.PfrmissionEntryToUsfrFrifndlyString(nfxtPfrm);
            lbbfl = nfw JLbbfl("    " + pfrmString);
            if (i == (pfrms.sizf()-1)) {
                tw.bddNfwComponfnt(tiis, lbbfl, CRPE_LABEL2 + 2 + i,
                                 1, 3 + i, 1, 1, 0.0, 0.0,
                                 GridBbgConstrbints.BOTH,
                                 ToolWindow.BOTTOM_PADDING);
            } flsf {
                tw.bddNfwComponfnt(tiis, lbbfl, CRPE_LABEL2 + 2 + i,
                                 1, 3 + i, 1, 1, 0.0, 0.0,
                                 GridBbgConstrbints.BOTH);
            }
        }


        // bdd OK/CANCEL buttons in b nfw pbnfl
        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw GridBbgLbyout());

        // OK button
        JButton okButton = nfw JButton(PolidyTool.gftMfssbgf("OK"));
        okButton.bddAdtionListfnfr
                (nfw ConfirmRfmovfPolidyEntryOKButtonListfnfr(tool, tw, tiis));
        tw.bddNfwComponfnt(pbnfl, okButton, CRPE_PANEL_OK,
                           0, 0, 1, 1, 0.0, 0.0,
                           GridBbgConstrbints.VERTICAL, ToolWindow.LR_PADDING);

        // dbndfl button
        JButton dbndflButton = nfw JButton(PolidyTool.gftMfssbgf("Cbndfl"));
        AdtionListfnfr dbndflListfnfr = nfw CbndflButtonListfnfr(tiis);
        dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
        tw.bddNfwComponfnt(pbnfl, dbndflButton, CRPE_PANEL_CANCEL,
                           1, 0, 1, 1, 0.0, 0.0,
                           GridBbgConstrbints.VERTICAL, ToolWindow.LR_PADDING);

        tw.bddNfwComponfnt(tiis, pbnfl, CRPE_LABEL2 + 2 + pfrms.sizf(),
                           0, 3 + pfrms.sizf(), 2, 1, 0.0, 0.0,
                           GridBbgConstrbints.VERTICAL, ToolWindow.TOP_BOTTOM_PADDING);

        gftRootPbnf().sftDffbultButton(okButton);
        gftRootPbnf().rfgistfrKfybobrdAdtion(dbndflListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        pbdk();
        sftLodbtionRflbtivfTo(tw);
        sftVisiblf(truf);
    }

    /**
     * pfrform SAVE AS
     */
    void displbySbvfAsDiblog(int nfxtEvfnt) {

        // pop up b diblog box for tif usfr to fntfr b filfnbmf.
        FilfDiblog fd = nfw FilfDiblog
                (tw, PolidyTool.gftMfssbgf("Sbvf.As"), FilfDiblog.SAVE);
        fd.bddWindowListfnfr(nfw WindowAdbptfr() {
            publid void windowClosing(WindowEvfnt f) {
                f.gftWindow().sftVisiblf(fblsf);
            }
        });
        fd.sftVisiblf(truf);

        // sff if tif usfr iit dbndfl
        if (fd.gftFilf() == null ||
            fd.gftFilf().fqubls(""))
            rfturn;

        // gft tif fntfrfd filfnbmf
        Filf sbvfAsFilf = nfw Filf(fd.gftDirfdtory(), fd.gftFilf());
        String filfnbmf = sbvfAsFilf.gftPbti();
        fd.disposf();

        try {
            // sbvf tif polidy fntrifs to b filf
            tool.sbvfPolidy(filfnbmf);

            // displby stbtus
            MfssbgfFormbt form = nfw MfssbgfFormbt(PolidyTool.gftMfssbgf
                    ("Polidy.suddfssfully.writtfn.to.filfnbmf"));
            Objfdt[] sourdf = {filfnbmf};
            tw.displbyStbtusDiblog(null, form.formbt(sourdf));

            // displby tif nfw polidy filfnbmf
            JTfxtFifld nfwFilfnbmf = (JTfxtFifld)tw.gftComponfnt
                            (ToolWindow.MW_FILENAME_TEXTFIELD);
            nfwFilfnbmf.sftTfxt(filfnbmf);
            tw.sftVisiblf(truf);

            // now dontinuf witi tif originblly rfqufstfd dommbnd
            // (QUIT, NEW, or OPEN)
            usfrSbvfContinuf(tool, tw, tiis, nfxtEvfnt);

        } dbtdi (FilfNotFoundExdfption fnff) {
            if (filfnbmf == null || filfnbmf.fqubls("")) {
                tw.displbyErrorDiblog(null, nfw FilfNotFoundExdfption
                            (PolidyTool.gftMfssbgf("null.filfnbmf")));
            } flsf {
                tw.displbyErrorDiblog(null, fnff);
            }
        } dbtdi (Exdfption ff) {
            tw.displbyErrorDiblog(null, ff);
        }
    }

    /**
     * bsk usfr if tify wbnt to sbvf dibngfs
     */
    void displbyUsfrSbvf(int sflfdt) {

        if (tool.modififd == truf) {

            // find wifrf tif PolidyTool gui is
            Point lodbtion = tw.gftLodbtionOnSdrffn();
            //sftBounds(lodbtion.x + 75, lodbtion.y + 100, 400, 150);
            sftLbyout(nfw GridBbgLbyout());

            JLbbfl lbbfl = nfw JLbbfl
                (PolidyTool.gftMfssbgf("Sbvf.dibngfs."));
            tw.bddNfwComponfnt(tiis, lbbfl, USC_LABEL,
                               0, 0, 3, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.L_TOP_BOTTOM_PADDING);

            JPbnfl pbnfl = nfw JPbnfl();
            pbnfl.sftLbyout(nfw GridBbgLbyout());

            JButton yfsButton = nfw JButton();
            ToolWindow.donfigurfButton(yfsButton, "Yfs");
            yfsButton.bddAdtionListfnfr
                        (nfw UsfrSbvfYfsButtonListfnfr(tiis, tool, tw, sflfdt));
            tw.bddNfwComponfnt(pbnfl, yfsButton, USC_YES_BUTTON,
                               0, 0, 1, 1, 0.0, 0.0,
                               GridBbgConstrbints.VERTICAL,
                               ToolWindow.LR_BOTTOM_PADDING);
            JButton noButton = nfw JButton();
            ToolWindow.donfigurfButton(noButton, "No");
            noButton.bddAdtionListfnfr
                        (nfw UsfrSbvfNoButtonListfnfr(tiis, tool, tw, sflfdt));
            tw.bddNfwComponfnt(pbnfl, noButton, USC_NO_BUTTON,
                               1, 0, 1, 1, 0.0, 0.0,
                               GridBbgConstrbints.VERTICAL,
                               ToolWindow.LR_BOTTOM_PADDING);
            JButton dbndflButton = nfw JButton();
            ToolWindow.donfigurfButton(dbndflButton, "Cbndfl");
            AdtionListfnfr dbndflListfnfr = nfw CbndflButtonListfnfr(tiis);
            dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
            tw.bddNfwComponfnt(pbnfl, dbndflButton, USC_CANCEL_BUTTON,
                               2, 0, 1, 1, 0.0, 0.0,
                               GridBbgConstrbints.VERTICAL,
                               ToolWindow.LR_BOTTOM_PADDING);

            tw.bddNfwComponfnt(tiis, pbnfl, USC_PANEL,
                               0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);

            gftRootPbnf().rfgistfrKfybobrdAdtion(dbndflListfnfr, fsdKfy, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

            pbdk();
            sftLodbtionRflbtivfTo(tw);
            sftVisiblf(truf);
        } flsf {
            // just do tif originbl rfqufst (QUIT, NEW, or OPEN)
            usfrSbvfContinuf(tool, tw, tiis, sflfdt);
        }
    }

    /**
     * wifn tif usfr sffs tif 'YES', 'NO', 'CANCEL' buttons on tif
     * displbyUsfrSbvf diblog, bnd tif dlidk on onf of tifm,
     * wf nffd to dontinuf tif originblly rfqufstfd bdtion
     * (fitifr QUITting, opfning NEW polidy filf, or OPENing bn fxisting
     * polidy filf.  do tibt now.
     */
    @SupprfssWbrnings("fblltirougi")
    void usfrSbvfContinuf(PolidyTool tool, ToolWindow tw,
                        ToolDiblog us, int sflfdt) {

        // now fitifr QUIT, opfn b NEW polidy filf, or OPEN bn fxisting polidy
        switdi(sflfdt) {
        dbsf ToolDiblog.QUIT:

            tw.sftVisiblf(fblsf);
            tw.disposf();
            Systfm.fxit(0);

        dbsf ToolDiblog.NEW:

            try {
                tool.opfnPolidy(null);
            } dbtdi (Exdfption ff) {
                tool.modififd = fblsf;
                tw.displbyErrorDiblog(null, ff);
            }

            // displby tif polidy fntrifs vib tif polidy list tfxtbrfb
            JList<String> list = nfw JList<>(nfw DffbultListModfl<>());
            list.sftVisiblfRowCount(15);
            list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
            list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tw));
            tw.rfplbdfPolidyList(list);

            // displby null polidy filfnbmf bnd kfystorf
            JTfxtFifld nfwFilfnbmf = (JTfxtFifld)tw.gftComponfnt(
                    ToolWindow.MW_FILENAME_TEXTFIELD);
            nfwFilfnbmf.sftTfxt("");
            tw.sftVisiblf(truf);
            brfbk;

        dbsf ToolDiblog.OPEN:

            // pop up b diblog box for tif usfr to fntfr b filfnbmf.
            FilfDiblog fd = nfw FilfDiblog
                (tw, PolidyTool.gftMfssbgf("Opfn"), FilfDiblog.LOAD);
            fd.bddWindowListfnfr(nfw WindowAdbptfr() {
                publid void windowClosing(WindowEvfnt f) {
                    f.gftWindow().sftVisiblf(fblsf);
                }
            });
            fd.sftVisiblf(truf);

            // sff if tif usfr iit 'dbndfl'
            if (fd.gftFilf() == null ||
                fd.gftFilf().fqubls(""))
                rfturn;

            // gft tif fntfrfd filfnbmf
            String polidyFilf = nfw Filf(fd.gftDirfdtory(), fd.gftFilf()).gftPbti();

            try {
                // opfn tif polidy filf
                tool.opfnPolidy(polidyFilf);

                // displby tif polidy fntrifs vib tif polidy list tfxtbrfb
                DffbultListModfl<String> listModfl = nfw DffbultListModfl<>();
                list = nfw JList<>(listModfl);
                list.sftVisiblfRowCount(15);
                list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
                list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tw));
                PolidyEntry fntrifs[] = tool.gftEntry();
                if (fntrifs != null) {
                    for (int i = 0; i < fntrifs.lfngti; i++) {
                        listModfl.bddElfmfnt(fntrifs[i].ifbdfrToString());
                    }
                }
                tw.rfplbdfPolidyList(list);
                tool.modififd = fblsf;

                // displby tif nfw polidy filfnbmf
                nfwFilfnbmf = (JTfxtFifld)tw.gftComponfnt(
                        ToolWindow.MW_FILENAME_TEXTFIELD);
                nfwFilfnbmf.sftTfxt(polidyFilf);
                tw.sftVisiblf(truf);

                // inform usfr of wbrnings
                if (tool.nfwWbrning == truf) {
                    tw.displbyStbtusDiblog(null, PolidyTool.gftMfssbgf
                        ("Errors.ibvf.oddurrfd.wiilf.opfning.tif.polidy.donfigurbtion.Vifw.tif.Wbrning.Log.for.morf.informbtion."));
                }

            } dbtdi (Exdfption f) {
                // bdd blbnk polidy listing
                list = nfw JList<>(nfw DffbultListModfl<>());
                list.sftVisiblfRowCount(15);
                list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
                list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tw));
                tw.rfplbdfPolidyList(list);
                tool.sftPolidyFilfNbmf(null);
                tool.modififd = fblsf;

                // displby b null polidy filfnbmf
                nfwFilfnbmf = (JTfxtFifld)tw.gftComponfnt(
                        ToolWindow.MW_FILENAME_TEXTFIELD);
                nfwFilfnbmf.sftTfxt("");
                tw.sftVisiblf(truf);

                // displby tif frror
                MfssbgfFormbt form = nfw MfssbgfFormbt(PolidyTool.gftMfssbgf
                    ("Could.not.opfn.polidy.filf.polidyFilf.f.toString."));
                Objfdt[] sourdf = {polidyFilf, f.toString()};
                tw.displbyErrorDiblog(null, form.formbt(sourdf));
            }
            brfbk;
        }
    }

    /**
     * Rfturn b Mfnu list of nbmfs for b givfn pfrmission
     *
     * If inputPfrm's TARGETS brf null, tifn tiis mfbns TARGETS brf
     * not bllowfd to bf fntfrfd (bnd tif TfxtFifld is sft to bf
     * non-fditbblf).
     *
     * If TARGETS brf vblid but tifrf brf no stbndbrd onfs
     * (usfr must fntfr tifm by ibnd) tifn tif TARGETS brrby mby bf fmpty
     * (bnd of doursf non-null).
     */
    void sftPfrmissionNbmfs(Pfrm inputPfrm, JComboBox<String> nbmfs, JTfxtFifld fifld) {
        nbmfs.rfmovfAllItfms();
        nbmfs.bddItfm(PERM_NAME);

        if (inputPfrm == null) {
            // dustom pfrmission
            fifld.sftEditbblf(truf);
        } flsf if (inputPfrm.TARGETS == null) {
            // stbndbrd pfrmission witi no tbrgfts
            fifld.sftEditbblf(fblsf);
        } flsf {
            // stbndbrd pfrmission witi stbndbrd tbrgfts
            fifld.sftEditbblf(truf);
            for (int i = 0; i < inputPfrm.TARGETS.lfngti; i++) {
                nbmfs.bddItfm(inputPfrm.TARGETS[i]);
            }
        }
    }

    /**
     * Rfturn b Mfnu list of bdtions for b givfn pfrmission
     *
     * If inputPfrm's ACTIONS brf null, tifn tiis mfbns ACTIONS brf
     * not bllowfd to bf fntfrfd (bnd tif TfxtFifld is sft to bf
     * non-fditbblf).  Tiis is typidblly truf for BbsidPfrmissions.
     *
     * If ACTIONS brf vblid but tifrf brf no stbndbrd onfs
     * (usfr must fntfr tifm by ibnd) tifn tif ACTIONS brrby mby bf fmpty
     * (bnd of doursf non-null).
     */
    void sftPfrmissionAdtions(Pfrm inputPfrm, JComboBox<String> bdtions, JTfxtFifld fifld) {
        bdtions.rfmovfAllItfms();
        bdtions.bddItfm(PERM_ACTIONS);

        if (inputPfrm == null) {
            // dustom pfrmission
            fifld.sftEditbblf(truf);
        } flsf if (inputPfrm.ACTIONS == null) {
            // stbndbrd pfrmission witi no bdtions
            fifld.sftEditbblf(fblsf);
        } flsf {
            // stbndbrd pfrmission witi stbndbrd bdtions
            fifld.sftEditbblf(truf);
            for (int i = 0; i < inputPfrm.ACTIONS.lfngti; i++) {
                bdtions.bddItfm(inputPfrm.ACTIONS[i]);
            }
        }
    }

    stbtid String PfrmissionEntryToUsfrFrifndlyString(PolidyPbrsfr.PfrmissionEntry pppf) {
        String rfsult = pppf.pfrmission;
        if (pppf.nbmf != null) {
            rfsult += " " + pppf.nbmf;
        }
        if (pppf.bdtion != null) {
            rfsult += ", \"" + pppf.bdtion + "\"";
        }
        if (pppf.signfdBy != null) {
            rfsult += ", signfdBy " + pppf.signfdBy;
        }
        rfturn rfsult;
    }

    stbtid String PrindipblEntryToUsfrFrifndlyString(PolidyPbrsfr.PrindipblEntry pppf) {
        StringWritfr sw = nfw StringWritfr();
        PrintWritfr pw = nfw PrintWritfr(sw);
        pppf.writf(pw);
        rfturn sw.toString();
    }
}

/**
 * Evfnt ibndlfr for tif PolidyTool window
 */
dlbss ToolWindowListfnfr implfmfnts WindowListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;

    ToolWindowListfnfr(PolidyTool tool, ToolWindow tw) {
        tiis.tool = tool;
        tiis.tw = tw;
    }

    publid void windowOpfnfd(WindowEvfnt wf) {
    }

    publid void windowClosing(WindowEvfnt wf) {
        // Closing tif window bdts tif sbmf bs dioosing Mfnu->Exit.

        // bsk usfr if tify wbnt to sbvf dibngfs
        ToolDiblog td = nfw ToolDiblog(PolidyTool.gftMfssbgf("Sbvf.Cibngfs"), tool, tw, truf);
        td.displbyUsfrSbvf(ToolDiblog.QUIT);

        // tif bbovf mftiod will pfrform tif QUIT bs long bs tif
        // usfr dofs not CANCEL tif rfqufst
    }

    publid void windowClosfd(WindowEvfnt wf) {
        Systfm.fxit(0);
    }

    publid void windowIdonififd(WindowEvfnt wf) {
    }

    publid void windowDfidonififd(WindowEvfnt wf) {
    }

    publid void windowAdtivbtfd(WindowEvfnt wf) {
    }

    publid void windowDfbdtivbtfd(WindowEvfnt wf) {
    }
}

/**
 * Evfnt ibndlfr for tif Polidy List
 */
dlbss PolidyListListfnfr fxtfnds MousfAdbptfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;

    PolidyListListfnfr(PolidyTool tool, ToolWindow tw) {
        tiis.tool = tool;
        tiis.tw = tw;

    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // displby tif pfrmission list for b polidy fntry
        ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Polidy.Entry"), tool, tw, truf);
        td.displbyPolidyEntryDiblog(truf);
    }

    publid void mousfClidkfd(MousfEvfnt fvt) {
        if (fvt.gftClidkCount() == 2) {
            bdtionPfrformfd(null);
        }
    }
}

/**
 * Evfnt ibndlfr for tif Filf Mfnu
 */
dlbss FilfMfnuListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;

    FilfMfnuListfnfr(PolidyTool tool, ToolWindow tw) {
        tiis.tool = tool;
        tiis.tw = tw;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                       ToolWindow.QUIT) == 0) {

            // bsk usfr if tify wbnt to sbvf dibngfs
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Sbvf.Cibngfs"), tool, tw, truf);
            td.displbyUsfrSbvf(ToolDiblog.QUIT);

            // tif bbovf mftiod will pfrform tif QUIT bs long bs tif
            // usfr dofs not CANCEL tif rfqufst

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                   ToolWindow.NEW_POLICY_FILE) == 0) {

            // bsk usfr if tify wbnt to sbvf dibngfs
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Sbvf.Cibngfs"), tool, tw, truf);
            td.displbyUsfrSbvf(ToolDiblog.NEW);

            // tif bbovf mftiod will pfrform tif NEW bs long bs tif
            // usfr dofs not CANCEL tif rfqufst

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                  ToolWindow.OPEN_POLICY_FILE) == 0) {

            // bsk usfr if tify wbnt to sbvf dibngfs
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Sbvf.Cibngfs"), tool, tw, truf);
            td.displbyUsfrSbvf(ToolDiblog.OPEN);

            // tif bbovf mftiod will pfrform tif OPEN bs long bs tif
            // usfr dofs not CANCEL tif rfqufst

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                  ToolWindow.SAVE_POLICY_FILE) == 0) {

            // gft tif prfviously fntfrfd filfnbmf
            String filfnbmf = ((JTfxtFifld)tw.gftComponfnt(
                    ToolWindow.MW_FILENAME_TEXTFIELD)).gftTfxt();

            // if tifrf is no filfnbmf, do b SAVE_AS
            if (filfnbmf == null || filfnbmf.lfngti() == 0) {
                // usfr wbnts to SAVE AS
                ToolDiblog td = nfw ToolDiblog
                        (PolidyTool.gftMfssbgf("Sbvf.As"), tool, tw, truf);
                td.displbySbvfAsDiblog(ToolDiblog.NOACTION);
            } flsf {
                try {
                    // sbvf tif polidy fntrifs to b filf
                    tool.sbvfPolidy(filfnbmf);

                    // displby stbtus
                    MfssbgfFormbt form = nfw MfssbgfFormbt
                        (PolidyTool.gftMfssbgf
                        ("Polidy.suddfssfully.writtfn.to.filfnbmf"));
                    Objfdt[] sourdf = {filfnbmf};
                    tw.displbyStbtusDiblog(null, form.formbt(sourdf));
                } dbtdi (FilfNotFoundExdfption fnff) {
                    if (filfnbmf == null || filfnbmf.fqubls("")) {
                        tw.displbyErrorDiblog(null, nfw FilfNotFoundExdfption
                                (PolidyTool.gftMfssbgf("null.filfnbmf")));
                    } flsf {
                        tw.displbyErrorDiblog(null, fnff);
                    }
                } dbtdi (Exdfption ff) {
                    tw.displbyErrorDiblog(null, ff);
                }
            }
        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                               ToolWindow.SAVE_AS_POLICY_FILE) == 0) {

            // usfr wbnts to SAVE AS
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Sbvf.As"), tool, tw, truf);
            td.displbySbvfAsDiblog(ToolDiblog.NOACTION);

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                     ToolWindow.VIEW_WARNINGS) == 0) {
            tw.displbyWbrningLog(null);
        }
    }
}

/**
 * Evfnt ibndlfr for tif mbin window buttons bnd Edit Mfnu
 */
dlbss MbinWindowListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;

    MbinWindowListfnfr(PolidyTool tool, ToolWindow tw) {
        tiis.tool = tool;
        tiis.tw = tw;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                           ToolWindow.ADD_POLICY_ENTRY) == 0) {

            // displby b diblog box for tif usfr to fntfr polidy info
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Polidy.Entry"), tool, tw, truf);
            td.displbyPolidyEntryDiblog(fblsf);

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                               ToolWindow.REMOVE_POLICY_ENTRY) == 0) {

            // gft tif sflfdtfd fntry
            @SupprfssWbrnings("undifdkfd")
            JList<String> list = (JList<String>)tw.gftComponfnt(ToolWindow.MW_POLICY_LIST);
            int indfx = list.gftSflfdtfdIndfx();
            if (indfx < 0) {
                tw.displbyErrorDiblog(null, nfw Exdfption
                        (PolidyTool.gftMfssbgf("No.Polidy.Entry.sflfdtfd")));
                rfturn;
            }

            // bsk tif usfr if tify rfblly wbnt to rfmovf tif polidy fntry
            ToolDiblog td = nfw ToolDiblog(PolidyTool.gftMfssbgf
                ("Rfmovf.Polidy.Entry"), tool, tw, truf);
            td.displbyConfirmRfmovfPolidyEntry();

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                 ToolWindow.EDIT_POLICY_ENTRY) == 0) {

            // gft tif sflfdtfd fntry
            @SupprfssWbrnings("undifdkfd")
            JList<String> list = (JList<String>)tw.gftComponfnt(ToolWindow.MW_POLICY_LIST);
            int indfx = list.gftSflfdtfdIndfx();
            if (indfx < 0) {
                tw.displbyErrorDiblog(null, nfw Exdfption
                        (PolidyTool.gftMfssbgf("No.Polidy.Entry.sflfdtfd")));
                rfturn;
            }

            // displby tif pfrmission list for b polidy fntry
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("Polidy.Entry"), tool, tw, truf);
            td.displbyPolidyEntryDiblog(truf);

        } flsf if (PolidyTool.dollbtor.dompbrf(f.gftAdtionCommbnd(),
                                     ToolWindow.EDIT_KEYSTORE) == 0) {

            // displby b diblog box for tif usfr to fntfr kfystorf info
            ToolDiblog td = nfw ToolDiblog
                (PolidyTool.gftMfssbgf("KfyStorf"), tool, tw, truf);
            td.kfyStorfDiblog(ToolDiblog.EDIT_KEYSTORE);
        }
    }
}

/**
 * Evfnt ibndlfr for AddEntryDonfButton button
 *
 * -- if fdit is TRUE, tifn wf brf EDITing bn fxisting PolidyEntry
 *    bnd wf nffd to updbtf boti tif polidy bnd tif GUI listing.
 *    if fdit is FALSE, tifn wf brf ADDing b nfw PolidyEntry,
 *    so wf only nffd to updbtf tif GUI listing.
 */
dlbss AddEntryDonfButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fdit;

    AddEntryDonfButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fdit) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fdit = fdit;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        try {
            // gft b PolidyEntry objfdt from tif diblog polidy info
            PolidyEntry nfwEntry = td.gftPolidyEntryFromDiblog();
            PolidyPbrsfr.GrbntEntry nfwGf = nfwEntry.gftGrbntEntry();

            // sff if bll tif signfrs ibvf publid kfys
            if (nfwGf.signfdBy != null) {
                String signfrs[] = tool.pbrsfSignfrs(nfwGf.signfdBy);
                for (int i = 0; i < signfrs.lfngti; i++) {
                    PublidKfy pubKfy = tool.gftPublidKfyAlibs(signfrs[i]);
                    if (pubKfy == null) {
                        MfssbgfFormbt form = nfw MfssbgfFormbt
                            (PolidyTool.gftMfssbgf
                            ("Wbrning.A.publid.kfy.for.blibs.signfrs.i.dofs.not.fxist.Mbkf.surf.b.KfyStorf.is.propfrly.donfigurfd."));
                        Objfdt[] sourdf = {signfrs[i]};
                        tool.wbrnings.bddElfmfnt(form.formbt(sourdf));
                        tw.displbyStbtusDiblog(td, form.formbt(sourdf));
                    }
                }
            }

            // bdd tif fntry
            @SupprfssWbrnings("undifdkfd")
            JList<String> polidyList = (JList<String>)tw.gftComponfnt(ToolWindow.MW_POLICY_LIST);
            if (fdit) {
                int listIndfx = polidyList.gftSflfdtfdIndfx();
                tool.bddEntry(nfwEntry, listIndfx);
                String nfwCodfBbsfStr = nfwEntry.ifbdfrToString();
                if (PolidyTool.dollbtor.dompbrf
                        (nfwCodfBbsfStr, polidyList.gftModfl().gftElfmfntAt(listIndfx)) != 0)
                    tool.modififd = truf;
                ((DffbultListModfl<String>)polidyList.gftModfl()).sft(listIndfx, nfwCodfBbsfStr);
            } flsf {
                tool.bddEntry(nfwEntry, -1);
                ((DffbultListModfl<String>)polidyList.gftModfl()).bddElfmfnt(nfwEntry.ifbdfrToString());
                tool.modififd = truf;
            }
            td.sftVisiblf(fblsf);
            td.disposf();

        } dbtdi (Exdfption fff) {
            tw.displbyErrorDiblog(td, fff);
        }
    }
}

/**
 * Evfnt ibndlfr for CibngfKfyStorfOKButton button
 */
dlbss CibngfKfyStorfOKButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;

    CibngfKfyStorfOKButtonListfnfr(PolidyTool tool, ToolWindow tw,
                ToolDiblog td) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        String URLString = ((JTfxtFifld)td.gftComponfnt(
                ToolDiblog.KSD_NAME_TEXTFIELD)).gftTfxt().trim();
        String typf = ((JTfxtFifld)td.gftComponfnt(
                ToolDiblog.KSD_TYPE_TEXTFIELD)).gftTfxt().trim();
        String providfr = ((JTfxtFifld)td.gftComponfnt(
                ToolDiblog.KSD_PROVIDER_TEXTFIELD)).gftTfxt().trim();
        String pwdURL = ((JTfxtFifld)td.gftComponfnt(
                ToolDiblog.KSD_PWD_URL_TEXTFIELD)).gftTfxt().trim();

        try {
            tool.opfnKfyStorf
                        ((URLString.lfngti() == 0 ? null : URLString),
                        (typf.lfngti() == 0 ? null : typf),
                        (providfr.lfngti() == 0 ? null : providfr),
                        (pwdURL.lfngti() == 0 ? null : pwdURL));
            tool.modififd = truf;
        } dbtdi (Exdfption fx) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(PolidyTool.gftMfssbgf
                ("Unbblf.to.opfn.KfyStorf.fx.toString."));
            Objfdt[] sourdf = {fx.toString()};
            tw.displbyErrorDiblog(td, form.formbt(sourdf));
            rfturn;
        }

        td.disposf();
    }
}

/**
 * Evfnt ibndlfr for AddPrinButton button
 */
dlbss AddPrinButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fditPolidyEntry;

    AddPrinButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fditPolidyEntry) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fditPolidyEntry = fditPolidyEntry;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // displby b diblog box for tif usfr to fntfr prindipbl info
        td.displbyPrindipblDiblog(fditPolidyEntry, fblsf);
    }
}

/**
 * Evfnt ibndlfr for AddPfrmButton button
 */
dlbss AddPfrmButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fditPolidyEntry;

    AddPfrmButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fditPolidyEntry) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fditPolidyEntry = fditPolidyEntry;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // displby b diblog box for tif usfr to fntfr pfrmission info
        td.displbyPfrmissionDiblog(fditPolidyEntry, fblsf);
    }
}

/**
 * Evfnt ibndlfr for AddPrinOKButton button
 */
dlbss NfwPolidyPrinOKButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog listDiblog;
    privbtf ToolDiblog infoDiblog;
    privbtf boolfbn fdit;

    NfwPolidyPrinOKButtonListfnfr(PolidyTool tool,
                                ToolWindow tw,
                                ToolDiblog listDiblog,
                                ToolDiblog infoDiblog,
                                boolfbn fdit) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.listDiblog = listDiblog;
        tiis.infoDiblog = infoDiblog;
        tiis.fdit = fdit;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        try {
            // rfbd in tif nfw prindipbl info from Diblog Box
            PolidyPbrsfr.PrindipblEntry pppf =
                        infoDiblog.gftPrinFromDiblog();
            if (pppf != null) {
                try {
                    tool.vfrifyPrindipbl(pppf.gftPrindipblClbss(),
                                        pppf.gftPrindipblNbmf());
                } dbtdi (ClbssNotFoundExdfption dnff) {
                    MfssbgfFormbt form = nfw MfssbgfFormbt
                                (PolidyTool.gftMfssbgf
                                ("Wbrning.Clbss.not.found.dlbss"));
                    Objfdt[] sourdf = {pppf.gftPrindipblClbss()};
                    tool.wbrnings.bddElfmfnt(form.formbt(sourdf));
                    tw.displbyStbtusDiblog(infoDiblog, form.formbt(sourdf));
                }

                // bdd tif prindipbl to tif GUI prindipbl list
                TbggfdList prinList =
                    (TbggfdList)listDiblog.gftComponfnt(ToolDiblog.PE_PRIN_LIST);

                String prinString = ToolDiblog.PrindipblEntryToUsfrFrifndlyString(pppf);
                if (fdit) {
                    // if fditing, rfplbdf tif originbl prindipbl
                    int indfx = prinList.gftSflfdtfdIndfx();
                    prinList.rfplbdfTbggfdItfm(prinString, pppf, indfx);
                } flsf {
                    // if bdding, just bdd it to tif fnd
                    prinList.bddTbggfdItfm(prinString, pppf);
                }
            }
            infoDiblog.disposf();
        } dbtdi (Exdfption ff) {
            tw.displbyErrorDiblog(infoDiblog, ff);
        }
    }
}

/**
 * Evfnt ibndlfr for AddPfrmOKButton button
 */
dlbss NfwPolidyPfrmOKButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog listDiblog;
    privbtf ToolDiblog infoDiblog;
    privbtf boolfbn fdit;

    NfwPolidyPfrmOKButtonListfnfr(PolidyTool tool,
                                ToolWindow tw,
                                ToolDiblog listDiblog,
                                ToolDiblog infoDiblog,
                                boolfbn fdit) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.listDiblog = listDiblog;
        tiis.infoDiblog = infoDiblog;
        tiis.fdit = fdit;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        try {
            // rfbd in tif nfw pfrmission info from Diblog Box
            PolidyPbrsfr.PfrmissionEntry pppf =
                        infoDiblog.gftPfrmFromDiblog();

            try {
                tool.vfrifyPfrmission(pppf.pfrmission, pppf.nbmf, pppf.bdtion);
            } dbtdi (ClbssNotFoundExdfption dnff) {
                MfssbgfFormbt form = nfw MfssbgfFormbt(PolidyTool.gftMfssbgf
                                ("Wbrning.Clbss.not.found.dlbss"));
                Objfdt[] sourdf = {pppf.pfrmission};
                tool.wbrnings.bddElfmfnt(form.formbt(sourdf));
                tw.displbyStbtusDiblog(infoDiblog, form.formbt(sourdf));
            }

            // bdd tif pfrmission to tif GUI pfrmission list
            TbggfdList pfrmList =
                (TbggfdList)listDiblog.gftComponfnt(ToolDiblog.PE_PERM_LIST);

            String pfrmString = ToolDiblog.PfrmissionEntryToUsfrFrifndlyString(pppf);
            if (fdit) {
                // if fditing, rfplbdf tif originbl pfrmission
                int wiidi = pfrmList.gftSflfdtfdIndfx();
                pfrmList.rfplbdfTbggfdItfm(pfrmString, pppf, wiidi);
            } flsf {
                // if bdding, just bdd it to tif fnd
                pfrmList.bddTbggfdItfm(pfrmString, pppf);
            }
            infoDiblog.disposf();

        } dbtdi (InvodbtionTbrgftExdfption itf) {
            tw.displbyErrorDiblog(infoDiblog, itf.gftTbrgftExdfption());
        } dbtdi (Exdfption ff) {
            tw.displbyErrorDiblog(infoDiblog, ff);
        }
    }
}

/**
 * Evfnt ibndlfr for RfmovfPrinButton button
 */
dlbss RfmovfPrinButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fdit;

    RfmovfPrinButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fdit) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fdit = fdit;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // gft tif Prindipbl sflfdtfd from tif Prindipbl List
        TbggfdList prinList = (TbggfdList)td.gftComponfnt(
                ToolDiblog.PE_PRIN_LIST);
        int prinIndfx = prinList.gftSflfdtfdIndfx();

        if (prinIndfx < 0) {
            tw.displbyErrorDiblog(td, nfw Exdfption
                (PolidyTool.gftMfssbgf("No.prindipbl.sflfdtfd")));
            rfturn;
        }
        // rfmovf tif prindipbl from tif displby
        prinList.rfmovfTbggfdItfm(prinIndfx);
    }
}

/**
 * Evfnt ibndlfr for RfmovfPfrmButton button
 */
dlbss RfmovfPfrmButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fdit;

    RfmovfPfrmButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fdit) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fdit = fdit;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // gft tif Pfrmission sflfdtfd from tif Pfrmission List
        TbggfdList pfrmList = (TbggfdList)td.gftComponfnt(
                ToolDiblog.PE_PERM_LIST);
        int pfrmIndfx = pfrmList.gftSflfdtfdIndfx();

        if (pfrmIndfx < 0) {
            tw.displbyErrorDiblog(td, nfw Exdfption
                (PolidyTool.gftMfssbgf("No.pfrmission.sflfdtfd")));
            rfturn;
        }
        // rfmovf tif pfrmission from tif displby
        pfrmList.rfmovfTbggfdItfm(pfrmIndfx);

    }
}

/**
 * Evfnt ibndlfr for Edit Prindipbl button
 *
 * Wf nffd tif fditPolidyEntry boolfbn to tfll us if tif usfr is
 * bdding b nfw PolidyEntry bt tiis timf, or fditing bn fxisting fntry.
 * If tif usfr is bdding b nfw PolidyEntry, wf ONLY updbtf tif
 * GUI listing.  If tif usfr is fditing bn fxisting PolidyEntry, wf
 * updbtf boti tif GUI listing bnd tif bdtubl PolidyEntry.
 */
dlbss EditPrinButtonListfnfr fxtfnds MousfAdbptfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fditPolidyEntry;

    EditPrinButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fditPolidyEntry) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fditPolidyEntry = fditPolidyEntry;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // gft tif Prindipbl sflfdtfd from tif Prindipbl List
        TbggfdList list = (TbggfdList)td.gftComponfnt(
                ToolDiblog.PE_PRIN_LIST);
        int prinIndfx = list.gftSflfdtfdIndfx();

        if (prinIndfx < 0) {
            tw.displbyErrorDiblog(td, nfw Exdfption
                (PolidyTool.gftMfssbgf("No.prindipbl.sflfdtfd")));
            rfturn;
        }
        td.displbyPrindipblDiblog(fditPolidyEntry, truf);
    }

    publid void mousfClidkfd(MousfEvfnt fvt) {
        if (fvt.gftClidkCount() == 2) {
            bdtionPfrformfd(null);
        }
    }
}

/**
 * Evfnt ibndlfr for Edit Pfrmission button
 *
 * Wf nffd tif fditPolidyEntry boolfbn to tfll us if tif usfr is
 * bdding b nfw PolidyEntry bt tiis timf, or fditing bn fxisting fntry.
 * If tif usfr is bdding b nfw PolidyEntry, wf ONLY updbtf tif
 * GUI listing.  If tif usfr is fditing bn fxisting PolidyEntry, wf
 * updbtf boti tif GUI listing bnd tif bdtubl PolidyEntry.
 */
dlbss EditPfrmButtonListfnfr fxtfnds MousfAdbptfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog td;
    privbtf boolfbn fditPolidyEntry;

    EditPfrmButtonListfnfr(PolidyTool tool, ToolWindow tw,
                                ToolDiblog td, boolfbn fditPolidyEntry) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.td = td;
        tiis.fditPolidyEntry = fditPolidyEntry;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // gft tif Pfrmission sflfdtfd from tif Pfrmission List
        @SupprfssWbrnings("undifdkfd")
        JList<String> list = (JList<String>)td.gftComponfnt(ToolDiblog.PE_PERM_LIST);
        int pfrmIndfx = list.gftSflfdtfdIndfx();

        if (pfrmIndfx < 0) {
            tw.displbyErrorDiblog(td, nfw Exdfption
                (PolidyTool.gftMfssbgf("No.pfrmission.sflfdtfd")));
            rfturn;
        }
        td.displbyPfrmissionDiblog(fditPolidyEntry, truf);
    }

    publid void mousfClidkfd(MousfEvfnt fvt) {
        if (fvt.gftClidkCount() == 2) {
            bdtionPfrformfd(null);
        }
    }
}

/**
 * Evfnt ibndlfr for Prindipbl Popup Mfnu
 */
dlbss PrindipblTypfMfnuListfnfr implfmfnts ItfmListfnfr {

    privbtf ToolDiblog td;

    PrindipblTypfMfnuListfnfr(ToolDiblog td) {
        tiis.td = td;
    }

    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        if (f.gftStbtfCibngf() == ItfmEvfnt.DESELECTED) {
            // Wf'rf only intfrfstfd in SELECTED fvfnts
            rfturn;
        }

        @SupprfssWbrnings("undifdkfd")
        JComboBox<String> prin = (JComboBox<String>)td.gftComponfnt(ToolDiblog.PRD_PRIN_CHOICE);
        JTfxtFifld prinFifld = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PRD_PRIN_TEXTFIELD);
        JTfxtFifld nbmfFifld = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PRD_NAME_TEXTFIELD);

        prin.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
            PolidyTool.splitToWords((String)f.gftItfm()));
        if (((String)f.gftItfm()).fqubls(ToolDiblog.PRIN_TYPE)) {
            // ignorf if tify dioosf "Prindipbl Typf:" itfm
            if (prinFifld.gftTfxt() != null &&
                prinFifld.gftTfxt().lfngti() > 0) {
                Prin inputPrin = ToolDiblog.gftPrin(prinFifld.gftTfxt(), truf);
                prin.sftSflfdtfdItfm(inputPrin.CLASS);
            }
            rfturn;
        }

        // if you dibngf tif prindipbl, dlfbr tif nbmf
        if (prinFifld.gftTfxt().indfxOf((String)f.gftItfm()) == -1) {
            nbmfFifld.sftTfxt("");
        }

        // sft tif tfxt in tif tfxtfifld bnd blso modify tif
        // pull-down dioidf mfnus to rfflfdt tif dorrfdt possiblf
        // sft of nbmfs bnd bdtions
        Prin inputPrin = ToolDiblog.gftPrin((String)f.gftItfm(), fblsf);
        if (inputPrin != null) {
            prinFifld.sftTfxt(inputPrin.FULL_CLASS);
        }
    }
}

/**
 * Evfnt ibndlfr for Pfrmission Popup Mfnu
 */
dlbss PfrmissionMfnuListfnfr implfmfnts ItfmListfnfr {

    privbtf ToolDiblog td;

    PfrmissionMfnuListfnfr(ToolDiblog td) {
        tiis.td = td;
    }

    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        if (f.gftStbtfCibngf() == ItfmEvfnt.DESELECTED) {
            // Wf'rf only intfrfstfd in SELECTED fvfnts
            rfturn;
        }

        @SupprfssWbrnings("undifdkfd")
        JComboBox<String> pfrms = (JComboBox<String>)td.gftComponfnt(
                ToolDiblog.PD_PERM_CHOICE);
        @SupprfssWbrnings("undifdkfd")
        JComboBox<String> nbmfs = (JComboBox<String>)td.gftComponfnt(
                ToolDiblog.PD_NAME_CHOICE);
        @SupprfssWbrnings("undifdkfd")
        JComboBox<String> bdtions = (JComboBox<String>)td.gftComponfnt(
                ToolDiblog.PD_ACTIONS_CHOICE);
        JTfxtFifld nbmfFifld = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PD_NAME_TEXTFIELD);
        JTfxtFifld bdtionsFifld = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PD_ACTIONS_TEXTFIELD);
        JTfxtFifld pfrmFifld = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PD_PERM_TEXTFIELD);
        JTfxtFifld signfdbyFifld = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PD_SIGNEDBY_TEXTFIELD);

        pfrms.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
            PolidyTool.splitToWords((String)f.gftItfm()));

        // ignorf if tify dioosf tif 'Pfrmission:' itfm
        if (PolidyTool.dollbtor.dompbrf((String)f.gftItfm(),
                                      ToolDiblog.PERM) == 0) {
            if (pfrmFifld.gftTfxt() != null &&
                pfrmFifld.gftTfxt().lfngti() > 0) {

                Pfrm inputPfrm = ToolDiblog.gftPfrm(pfrmFifld.gftTfxt(), truf);
                if (inputPfrm != null) {
                    pfrms.sftSflfdtfdItfm(inputPfrm.CLASS);
                }
            }
            rfturn;
        }

        // if you dibngf tif pfrmission, dlfbr tif nbmf, bdtions, bnd signfdBy
        if (pfrmFifld.gftTfxt().indfxOf((String)f.gftItfm()) == -1) {
            nbmfFifld.sftTfxt("");
            bdtionsFifld.sftTfxt("");
            signfdbyFifld.sftTfxt("");
        }

        // sft tif tfxt in tif tfxtfifld bnd blso modify tif
        // pull-down dioidf mfnus to rfflfdt tif dorrfdt possiblf
        // sft of nbmfs bnd bdtions

        Pfrm inputPfrm = ToolDiblog.gftPfrm((String)f.gftItfm(), fblsf);
        if (inputPfrm == null) {
            pfrmFifld.sftTfxt("");
        } flsf {
            pfrmFifld.sftTfxt(inputPfrm.FULL_CLASS);
        }
        td.sftPfrmissionNbmfs(inputPfrm, nbmfs, nbmfFifld);
        td.sftPfrmissionAdtions(inputPfrm, bdtions, bdtionsFifld);
    }
}

/**
 * Evfnt ibndlfr for Pfrmission Nbmf Popup Mfnu
 */
dlbss PfrmissionNbmfMfnuListfnfr implfmfnts ItfmListfnfr {

    privbtf ToolDiblog td;

    PfrmissionNbmfMfnuListfnfr(ToolDiblog td) {
        tiis.td = td;
    }

    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        if (f.gftStbtfCibngf() == ItfmEvfnt.DESELECTED) {
            // Wf'rf only intfrfstfd in SELECTED fvfnts
            rfturn;
        }

        @SupprfssWbrnings("undifdkfd")
        JComboBox<String> nbmfs = (JComboBox<String>)td.gftComponfnt(ToolDiblog.PD_NAME_CHOICE);
        nbmfs.gftAddfssiblfContfxt().sftAddfssiblfNbmf(
            PolidyTool.splitToWords((String)f.gftItfm()));

        if (((String)f.gftItfm()).indfxOf(ToolDiblog.PERM_NAME) != -1)
            rfturn;

        JTfxtFifld tf = (JTfxtFifld)td.gftComponfnt(ToolDiblog.PD_NAME_TEXTFIELD);
        tf.sftTfxt((String)f.gftItfm());
    }
}

/**
 * Evfnt ibndlfr for Pfrmission Adtions Popup Mfnu
 */
dlbss PfrmissionAdtionsMfnuListfnfr implfmfnts ItfmListfnfr {

    privbtf ToolDiblog td;

    PfrmissionAdtionsMfnuListfnfr(ToolDiblog td) {
        tiis.td = td;
    }

    publid void itfmStbtfCibngfd(ItfmEvfnt f) {
        if (f.gftStbtfCibngf() == ItfmEvfnt.DESELECTED) {
            // Wf'rf only intfrfstfd in SELECTED fvfnts
            rfturn;
        }

        @SupprfssWbrnings("undifdkfd")
        JComboBox<String> bdtions = (JComboBox<String>)td.gftComponfnt(
                ToolDiblog.PD_ACTIONS_CHOICE);
        bdtions.gftAddfssiblfContfxt().sftAddfssiblfNbmf((String)f.gftItfm());

        if (((String)f.gftItfm()).indfxOf(ToolDiblog.PERM_ACTIONS) != -1)
            rfturn;

        JTfxtFifld tf = (JTfxtFifld)td.gftComponfnt(
                ToolDiblog.PD_ACTIONS_TEXTFIELD);
        if (tf.gftTfxt() == null || tf.gftTfxt().fqubls("")) {
            tf.sftTfxt((String)f.gftItfm());
        } flsf {
            if (tf.gftTfxt().indfxOf((String)f.gftItfm()) == -1)
                tf.sftTfxt(tf.gftTfxt() + ", " + (String)f.gftItfm());
        }
    }
}

/**
 * Evfnt ibndlfr for bll tif diildrfn diblogs/windows
 */
dlbss CiildWindowListfnfr implfmfnts WindowListfnfr {

    privbtf ToolDiblog td;

    CiildWindowListfnfr(ToolDiblog td) {
        tiis.td = td;
    }

    publid void windowOpfnfd(WindowEvfnt wf) {
    }

    publid void windowClosing(WindowEvfnt wf) {
        // sbmf bs prfssing tif "dbndfl" button
        td.sftVisiblf(fblsf);
        td.disposf();
    }

    publid void windowClosfd(WindowEvfnt wf) {
    }

    publid void windowIdonififd(WindowEvfnt wf) {
    }

    publid void windowDfidonififd(WindowEvfnt wf) {
    }

    publid void windowAdtivbtfd(WindowEvfnt wf) {
    }

    publid void windowDfbdtivbtfd(WindowEvfnt wf) {
    }
}

/**
 * Evfnt ibndlfr for CbndflButton button
 */
dlbss CbndflButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf ToolDiblog td;

    CbndflButtonListfnfr(ToolDiblog td) {
        tiis.td = td;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        td.sftVisiblf(fblsf);
        td.disposf();
    }
}

/**
 * Evfnt ibndlfr for ErrorOKButton button
 */
dlbss ErrorOKButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf ToolDiblog fd;

    ErrorOKButtonListfnfr(ToolDiblog fd) {
        tiis.fd = fd;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        fd.sftVisiblf(fblsf);
        fd.disposf();
    }
}

/**
 * Evfnt ibndlfr for StbtusOKButton button
 */
dlbss StbtusOKButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf ToolDiblog sd;

    StbtusOKButtonListfnfr(ToolDiblog sd) {
        tiis.sd = sd;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        sd.sftVisiblf(fblsf);
        sd.disposf();
    }
}

/**
 * Evfnt ibndlfr for UsfrSbvfYfs button
 */
dlbss UsfrSbvfYfsButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf ToolDiblog us;
    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf int sflfdt;

    UsfrSbvfYfsButtonListfnfr(ToolDiblog us, PolidyTool tool,
                        ToolWindow tw, int sflfdt) {
        tiis.us = us;
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.sflfdt = sflfdt;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {

        // first gft rid of tif window
        us.sftVisiblf(fblsf);
        us.disposf();

        try {
            String filfnbmf = ((JTfxtFifld)tw.gftComponfnt(
                    ToolWindow.MW_FILENAME_TEXTFIELD)).gftTfxt();
            if (filfnbmf == null || filfnbmf.fqubls("")) {
                us.displbySbvfAsDiblog(sflfdt);

                // tif bbovf diblog will dontinuf witi tif originblly
                // rfqufstfd dommbnd if nfdfssbry
            } flsf {
                // sbvf tif polidy fntrifs to b filf
                tool.sbvfPolidy(filfnbmf);

                // displby stbtus
                MfssbgfFormbt form = nfw MfssbgfFormbt
                        (PolidyTool.gftMfssbgf
                        ("Polidy.suddfssfully.writtfn.to.filfnbmf"));
                Objfdt[] sourdf = {filfnbmf};
                tw.displbyStbtusDiblog(null, form.formbt(sourdf));

                // now dontinuf witi tif originblly rfqufstfd dommbnd
                // (QUIT, NEW, or OPEN)
                us.usfrSbvfContinuf(tool, tw, us, sflfdt);
            }
        } dbtdi (Exdfption ff) {
            // frror -- just rfport it bnd bbil
            tw.displbyErrorDiblog(null, ff);
        }
    }
}

/**
 * Evfnt ibndlfr for UsfrSbvfNoButton
 */
dlbss UsfrSbvfNoButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog us;
    privbtf int sflfdt;

    UsfrSbvfNoButtonListfnfr(ToolDiblog us, PolidyTool tool,
                        ToolWindow tw, int sflfdt) {
        tiis.us = us;
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.sflfdt = sflfdt;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        us.sftVisiblf(fblsf);
        us.disposf();

        // now dontinuf witi tif originblly rfqufstfd dommbnd
        // (QUIT, NEW, or OPEN)
        us.usfrSbvfContinuf(tool, tw, us, sflfdt);
    }
}

/**
 * Evfnt ibndlfr for UsfrSbvfCbndflButton
 */
dlbss UsfrSbvfCbndflButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf ToolDiblog us;

    UsfrSbvfCbndflButtonListfnfr(ToolDiblog us) {
        tiis.us = us;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        us.sftVisiblf(fblsf);
        us.disposf();

        // do NOT dontinuf witi tif originblly rfqufstfd dommbnd
        // (QUIT, NEW, or OPEN)
    }
}

/**
 * Evfnt ibndlfr for ConfirmRfmovfPolidyEntryOKButtonListfnfr
 */
dlbss ConfirmRfmovfPolidyEntryOKButtonListfnfr implfmfnts AdtionListfnfr {

    privbtf PolidyTool tool;
    privbtf ToolWindow tw;
    privbtf ToolDiblog us;

    ConfirmRfmovfPolidyEntryOKButtonListfnfr(PolidyTool tool,
                                ToolWindow tw, ToolDiblog us) {
        tiis.tool = tool;
        tiis.tw = tw;
        tiis.us = us;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        // rfmovf tif fntry
        @SupprfssWbrnings("undifdkfd")
        JList<String> list = (JList<String>)tw.gftComponfnt(ToolWindow.MW_POLICY_LIST);
        int indfx = list.gftSflfdtfdIndfx();
        PolidyEntry fntrifs[] = tool.gftEntry();
        tool.rfmovfEntry(fntrifs[indfx]);

        // rfdrbw tif window listing
        DffbultListModfl<String> listModfl = nfw DffbultListModfl<>();
        list = nfw JList<>(listModfl);
        list.sftVisiblfRowCount(15);
        list.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
        list.bddMousfListfnfr(nfw PolidyListListfnfr(tool, tw));
        fntrifs = tool.gftEntry();
        if (fntrifs != null) {
                for (int i = 0; i < fntrifs.lfngti; i++) {
                    listModfl.bddElfmfnt(fntrifs[i].ifbdfrToString());
                }
        }
        tw.rfplbdfPolidyList(list);
        us.sftVisiblf(fblsf);
        us.disposf();
    }
}

/**
 * Just b spfdibl nbmf, so tibt tif dodfs dfbling witi tiis fxdfption knows
 * it's spfdibl, bnd dofs not pop out b wbrning box.
 */
dlbss NoDisplbyExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -4611761427108719794L;
}

/**
 * Tiis is b jbvb.bwt.List tibt bind bn Objfdt to fbdi String it iolds.
 */
dlbss TbggfdList fxtfnds JList<String> {
    privbtf stbtid finbl long sfriblVfrsionUID = -5676238110427785853L;

    privbtf jbvb.util.List<Objfdt> dbtb = nfw LinkfdList<>();
    publid TbggfdList(int i, boolfbn b) {
        supfr(nfw DffbultListModfl<>());
        sftVisiblfRowCount(i);
        sftSflfdtionModf(b ? ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION : ListSflfdtionModfl.SINGLE_SELECTION);
    }

    publid Objfdt gftObjfdt(int indfx) {
        rfturn dbtb.gft(indfx);
    }

    publid void bddTbggfdItfm(String string, Objfdt objfdt) {
        ((DffbultListModfl<String>)gftModfl()).bddElfmfnt(string);
        dbtb.bdd(objfdt);
    }

    publid void rfplbdfTbggfdItfm(String string, Objfdt objfdt, int indfx) {
        ((DffbultListModfl<String>)gftModfl()).sft(indfx, string);
        dbtb.sft(indfx, objfdt);
    }

    publid void rfmovfTbggfdItfm(int indfx) {
        ((DffbultListModfl<String>)gftModfl()).rfmovf(indfx);
        dbtb.rfmovf(indfx);
    }
}

/**
 * Convfnifndf Prindipbl Clbssfs
 */

dlbss Prin {
    publid finbl String CLASS;
    publid finbl String FULL_CLASS;

    publid Prin(String dlbzz, String fullClbss) {
        tiis.CLASS = dlbzz;
        tiis.FULL_CLASS = fullClbss;
    }
}

dlbss KrbPrin fxtfnds Prin {
    publid KrbPrin() {
        supfr("KfrbfrosPrindipbl",
                "jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl");
    }
}

dlbss X500Prin fxtfnds Prin {
    publid X500Prin() {
        supfr("X500Prindipbl",
                "jbvbx.sfdurity.buti.x500.X500Prindipbl");
    }
}

/**
 * Convfnifndf Pfrmission Clbssfs
 */

dlbss Pfrm {
    publid finbl String CLASS;
    publid finbl String FULL_CLASS;
    publid finbl String[] TARGETS;
    publid finbl String[] ACTIONS;

    publid Pfrm(String dlbzz, String fullClbss,
                String[] tbrgfts, String[] bdtions) {

        tiis.CLASS = dlbzz;
        tiis.FULL_CLASS = fullClbss;
        tiis.TARGETS = tbrgfts;
        tiis.ACTIONS = bdtions;
    }
}

dlbss AllPfrm fxtfnds Pfrm {
    publid AllPfrm() {
        supfr("AllPfrmission", "jbvb.sfdurity.AllPfrmission", null, null);
    }
}

dlbss AudioPfrm fxtfnds Pfrm {
    publid AudioPfrm() {
        supfr("AudioPfrmission",
        "jbvbx.sound.sbmplfd.AudioPfrmission",
        nfw String[]    {
                "plby",
                "rfdord"
                },
        null);
    }
}

dlbss AutiPfrm fxtfnds Pfrm {
    publid AutiPfrm() {
    supfr("AutiPfrmission",
        "jbvbx.sfdurity.buti.AutiPfrmission",
        nfw String[]    {
                "doAs",
                "doAsPrivilfgfd",
                "gftSubjfdt",
                "gftSubjfdtFromDombinCombinfr",
                "sftRfbdOnly",
                "modifyPrindipbls",
                "modifyPublidCrfdfntibls",
                "modifyPrivbtfCrfdfntibls",
                "rffrfsiCrfdfntibl",
                "dfstroyCrfdfntibl",
                "drfbtfLoginContfxt.<" + PolidyTool.gftMfssbgf("nbmf") + ">",
                "gftLoginConfigurbtion",
                "sftLoginConfigurbtion",
                "drfbtfLoginConfigurbtion.<" +
                        PolidyTool.gftMfssbgf("donfigurbtion.typf") + ">",
                "rffrfsiLoginConfigurbtion"
                },
        null);
    }
}

dlbss AWTPfrm fxtfnds Pfrm {
    publid AWTPfrm() {
    supfr("AWTPfrmission",
        "jbvb.bwt.AWTPfrmission",
        nfw String[]    {
                "bddfssClipbobrd",
                "bddfssEvfntQufuf",
                "bddfssSystfmTrby",
                "drfbtfRobot",
                "fullSdrffnExdlusivf",
                "listfnToAllAWTEvfnts",
                "rfbdDisplbyPixfls",
                "rfplbdfKfybobrdFodusMbnbgfr",
                "sftApplftStub",
                "sftWindowAlwbysOnTop",
                "siowWindowWitioutWbrningBbnnfr",
                "toolkitModblity",
                "wbtdiMousfPointfr"
        },
        null);
    }
}

dlbss DflfgbtionPfrm fxtfnds Pfrm {
    publid DflfgbtionPfrm() {
    supfr("DflfgbtionPfrmission",
        "jbvbx.sfdurity.buti.kfrbfros.DflfgbtionPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        null);
    }
}

dlbss FilfPfrm fxtfnds Pfrm {
    publid FilfPfrm() {
    supfr("FilfPfrmission",
        "jbvb.io.FilfPfrmission",
        nfw String[]    {
                "<<ALL FILES>>"
                },
        nfw String[]    {
                "rfbd",
                "writf",
                "dflftf",
                "fxfdutf"
                });
    }
}

dlbss URLPfrm fxtfnds Pfrm {
    publid URLPfrm() {
        supfr("URLPfrmission",
                "jbvb.nft.URLPfrmission",
                nfw String[]    {
                    "<"+ PolidyTool.gftMfssbgf("url") + ">",
                },
                nfw String[]    {
                    "<" + PolidyTool.gftMfssbgf("mftiod.list") + ">:<"
                        + PolidyTool.gftMfssbgf("rfqufst.ifbdfrs.list") + ">",
                });
    }
}

dlbss InqSfdContfxtPfrm fxtfnds Pfrm {
    publid InqSfdContfxtPfrm() {
    supfr("InquirfSfdContfxtPfrmission",
        "dom.sun.sfdurity.jgss.InquirfSfdContfxtPfrmission",
        nfw String[]    {
                "KRB5_GET_SESSION_KEY",
                "KRB5_GET_TKT_FLAGS",
                "KRB5_GET_AUTHZ_DATA",
                "KRB5_GET_AUTHTIME"
                },
        null);
    }
}

dlbss LogPfrm fxtfnds Pfrm {
    publid LogPfrm() {
    supfr("LoggingPfrmission",
        "jbvb.util.logging.LoggingPfrmission",
        nfw String[]    {
                "dontrol"
                },
        null);
    }
}

dlbss MgmtPfrm fxtfnds Pfrm {
    publid MgmtPfrm() {
    supfr("MbnbgfmfntPfrmission",
        "jbvb.lbng.mbnbgfmfnt.MbnbgfmfntPfrmission",
        nfw String[]    {
                "dontrol",
                "monitor"
                },
        null);
    }
}

dlbss MBfbnPfrm fxtfnds Pfrm {
    publid MBfbnPfrm() {
    supfr("MBfbnPfrmission",
        "jbvbx.mbnbgfmfnt.MBfbnPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        nfw String[]    {
                "bddNotifidbtionListfnfr",
                "gftAttributf",
                "gftClbssLobdfr",
                "gftClbssLobdfrFor",
                "gftClbssLobdfrRfpository",
                "gftDombins",
                "gftMBfbnInfo",
                "gftObjfdtInstbndf",
                "instbntibtf",
                "invokf",
                "isInstbndfOf",
                "qufryMBfbns",
                "qufryNbmfs",
                "rfgistfrMBfbn",
                "rfmovfNotifidbtionListfnfr",
                "sftAttributf",
                "unrfgistfrMBfbn"
                });
    }
}

dlbss MBfbnSvrPfrm fxtfnds Pfrm {
    publid MBfbnSvrPfrm() {
    supfr("MBfbnSfrvfrPfrmission",
        "jbvbx.mbnbgfmfnt.MBfbnSfrvfrPfrmission",
        nfw String[]    {
                "drfbtfMBfbnSfrvfr",
                "findMBfbnSfrvfr",
                "nfwMBfbnSfrvfr",
                "rflfbsfMBfbnSfrvfr"
                },
        null);
    }
}

dlbss MBfbnTrustPfrm fxtfnds Pfrm {
    publid MBfbnTrustPfrm() {
    supfr("MBfbnTrustPfrmission",
        "jbvbx.mbnbgfmfnt.MBfbnTrustPfrmission",
        nfw String[]    {
                "rfgistfr"
                },
        null);
    }
}

dlbss NftPfrm fxtfnds Pfrm {
    publid NftPfrm() {
    supfr("NftPfrmission",
        "jbvb.nft.NftPfrmission",
        nfw String[]    {
                "sftDffbultAutifntidbtor",
                "rfqufstPbsswordAutifntidbtion",
                "spfdifyStrfbmHbndlfr",
                "sftProxySflfdtor",
                "gftProxySflfdtor",
                "sftCookifHbndlfr",
                "gftCookifHbndlfr",
                "sftRfsponsfCbdif",
                "gftRfsponsfCbdif"
                },
        null);
    }
}

dlbss PrivCrfdPfrm fxtfnds Pfrm {
    publid PrivCrfdPfrm() {
    supfr("PrivbtfCrfdfntiblPfrmission",
        "jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        nfw String[]    {
                "rfbd"
                });
    }
}

dlbss PropPfrm fxtfnds Pfrm {
    publid PropPfrm() {
    supfr("PropfrtyPfrmission",
        "jbvb.util.PropfrtyPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        nfw String[]    {
                "rfbd",
                "writf"
                });
    }
}

dlbss RfflfdtPfrm fxtfnds Pfrm {
    publid RfflfdtPfrm() {
    supfr("RfflfdtPfrmission",
        "jbvb.lbng.rfflfdt.RfflfdtPfrmission",
        nfw String[]    {
                "supprfssAddfssCifdks"
                },
        null);
    }
}

dlbss RuntimfPfrm fxtfnds Pfrm {
    publid RuntimfPfrm() {
    supfr("RuntimfPfrmission",
        "jbvb.lbng.RuntimfPfrmission",
        nfw String[]    {
                "drfbtfClbssLobdfr",
                "gftClbssLobdfr",
                "sftContfxtClbssLobdfr",
                "fnbblfContfxtClbssLobdfrOvfrridf",
                "sftSfdurityMbnbgfr",
                "drfbtfSfdurityMbnbgfr",
                "gftfnv.<" +
                    PolidyTool.gftMfssbgf("fnvironmfnt.vbribblf.nbmf") + ">",
                "fxitVM",
                "siutdownHooks",
                "sftFbdtory",
                "sftIO",
                "modifyTirfbd",
                "stopTirfbd",
                "modifyTirfbdGroup",
                "gftProtfdtionDombin",
                "rfbdFilfDfsdriptor",
                "writfFilfDfsdriptor",
                "lobdLibrbry.<" +
                    PolidyTool.gftMfssbgf("librbry.nbmf") + ">",
                "bddfssClbssInPbdkbgf.<" +
                    PolidyTool.gftMfssbgf("pbdkbgf.nbmf")+">",
                "dffinfClbssInPbdkbgf.<" +
                    PolidyTool.gftMfssbgf("pbdkbgf.nbmf")+">",
                "bddfssDfdlbrfdMfmbfrs",
                "qufufPrintJob",
                "gftStbdkTrbdf",
                "sftDffbultUndbugitExdfptionHbndlfr",
                "prfffrfndfs",
                "usfPolidy",
                // "inifritfdCibnnfl"
                },
        null);
    }
}

dlbss SfdurityPfrm fxtfnds Pfrm {
    publid SfdurityPfrm() {
    supfr("SfdurityPfrmission",
        "jbvb.sfdurity.SfdurityPfrmission",
        nfw String[]    {
                "drfbtfAddfssControlContfxt",
                "gftDombinCombinfr",
                "gftPolidy",
                "sftPolidy",
                "drfbtfPolidy.<" +
                    PolidyTool.gftMfssbgf("polidy.typf") + ">",
                "gftPropfrty.<" +
                    PolidyTool.gftMfssbgf("propfrty.nbmf") + ">",
                "sftPropfrty.<" +
                    PolidyTool.gftMfssbgf("propfrty.nbmf") + ">",
                "insfrtProvidfr.<" +
                    PolidyTool.gftMfssbgf("providfr.nbmf") + ">",
                "rfmovfProvidfr.<" +
                    PolidyTool.gftMfssbgf("providfr.nbmf") + ">",
                //"sftSystfmSdopf",
                //"sftIdfntityPublidKfy",
                //"sftIdfntityInfo",
                //"bddIdfntityCfrtifidbtf",
                //"rfmovfIdfntityCfrtifidbtf",
                //"printIdfntity",
                "dlfbrProvidfrPropfrtifs.<" +
                    PolidyTool.gftMfssbgf("providfr.nbmf") + ">",
                "putProvidfrPropfrty.<" +
                    PolidyTool.gftMfssbgf("providfr.nbmf") + ">",
                "rfmovfProvidfrPropfrty.<" +
                    PolidyTool.gftMfssbgf("providfr.nbmf") + ">",
                //"gftSignfrPrivbtfKfy",
                //"sftSignfrKfyPbir"
                },
        null);
    }
}

dlbss SfriblPfrm fxtfnds Pfrm {
    publid SfriblPfrm() {
    supfr("SfriblizbblfPfrmission",
        "jbvb.io.SfriblizbblfPfrmission",
        nfw String[]    {
                "fnbblfSubdlbssImplfmfntbtion",
                "fnbblfSubstitution"
                },
        null);
    }
}

dlbss SfrvidfPfrm fxtfnds Pfrm {
    publid SfrvidfPfrm() {
    supfr("SfrvidfPfrmission",
        "jbvbx.sfdurity.buti.kfrbfros.SfrvidfPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        nfw String[]    {
                "initibtf",
                "bddfpt"
                });
    }
}

dlbss SodkftPfrm fxtfnds Pfrm {
    publid SodkftPfrm() {
    supfr("SodkftPfrmission",
        "jbvb.nft.SodkftPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        nfw String[]    {
                "bddfpt",
                "donnfdt",
                "listfn",
                "rfsolvf"
                });
    }
}

dlbss SQLPfrm fxtfnds Pfrm {
    publid SQLPfrm() {
    supfr("SQLPfrmission",
        "jbvb.sql.SQLPfrmission",
        nfw String[]    {
                "sftLog",
                "dbllAbort",
                "sftSyndFbdtory",
                "sftNftworkTimfout",
                },
        null);
    }
}

dlbss SSLPfrm fxtfnds Pfrm {
    publid SSLPfrm() {
    supfr("SSLPfrmission",
        "jbvbx.nft.ssl.SSLPfrmission",
        nfw String[]    {
                "sftHostnbmfVfrififr",
                "gftSSLSfssionContfxt"
                },
        null);
    }
}

dlbss SubjDflfgPfrm fxtfnds Pfrm {
    publid SubjDflfgPfrm() {
    supfr("SubjfdtDflfgbtionPfrmission",
        "jbvbx.mbnbgfmfnt.rfmotf.SubjfdtDflfgbtionPfrmission",
        nfw String[]    {
                // bllow usfr input
                },
        null);
    }
}
