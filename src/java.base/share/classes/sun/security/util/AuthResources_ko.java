/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

/**
 * <p> Tiis dlbss rfprfsfnts tif <dodf>RfsourdfBundlf</dodf>
 * for tif following pbdkbgfs:
 *
 * <ol>
 * <li> dom.sun.sfdurity.buti
 * <li> dom.sun.sfdurity.buti.login
 * </ol>
 *
 */
publid dlbss AutiRfsourdfs_ko fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "\uBD80\uC801\uD569\uD55C \uB110 \uC785\uB825: {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl: {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl: {0}"},
        {"Invblid.NTSid.vbluf", "NTSid \uAC12\uC774 \uBD80\uC801\uD569\uD569\uB2C8\uB2E4."},
        {"NTSid.nbmf", "NTSid: {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl: {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl: {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl: {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl: {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl: {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [\uAE30\uBCF8 \uADF8\uB8F9]: {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [\uBCF4\uC870 \uADF8\uB8F9]: {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl: {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl: {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "{0}\uC744(\uB97C) \uC81C\uB300\uB85C \uD655\uC7A5\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0}(\uD574\uB2F9 \uD30C\uC77C \uB610\uB294 \uB514\uB809\uD1A0\uB9AC\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4.)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "\uAD6C\uC131 \uC624\uB958:\n\t\uD574\uB2F9 \uD30C\uC77C \uB610\uB294 \uB514\uB809\uD1A0\uB9AC\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "\uAD6C\uC131 \uC624\uB958:\n\t\uC81C\uC5B4 \uD50C\uB798\uADF8\uAC00 \uBD80\uC801\uD569\uD568, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0}\uC5D0 \uB300\uD574 \uC5EC\uB7EC \uD56D\uBAA9\uC744 \uC9C0\uC815\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "\uAD6C\uC131 \uC624\uB958:\n\t[{0}]\uC774(\uAC00) \uD544\uC694\uD558\uC9C0\uB9CC [\uD30C\uC77C\uC758 \uB05D]\uC5D0 \uB3C4\uB2EC\uD588\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0} \uD589: [{1}]\uC774(\uAC00) \uD544\uC694\uD558\uC9C0\uB9CC [{2}]\uC774(\uAC00) \uBC1C\uACAC\uB418\uC5C8\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0} \uD589: [{1}]\uC774(\uAC00) \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0} \uD589: \uC2DC\uC2A4\uD15C \uC18D\uC131 [{1}]\uC774(\uAC00) \uBE48 \uAC12\uC73C\uB85C \uD655\uC7A5\uB418\uC5C8\uC2B5\uB2C8\uB2E4."},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","\uC0AC\uC6A9\uC790 \uC774\uB984: "},
        {"pbssword.","\uBE44\uBC00\uBC88\uD638: "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "\uD0A4 \uC800\uC7A5\uC18C \uC815\uBCF4\uB97C \uC785\uB825\uD558\uC2ED\uC2DC\uC624."},
        {"Kfystorf.blibs.","\uD0A4 \uC800\uC7A5\uC18C \uBCC4\uCE6D: "},
        {"Kfystorf.pbssword.","\uD0A4 \uC800\uC7A5\uC18C \uBE44\uBC00\uBC88\uD638: "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "\uC804\uC6A9 \uD0A4 \uBE44\uBC00\uBC88\uD638(\uC120\uD0DD \uC0AC\uD56D): "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Kfrbfros \uC0AC\uC6A9\uC790 \uC774\uB984 [{0}]: "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "{0}\uC758 Kfrbfros \uBE44\uBC00\uBC88\uD638: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": \uAD6C\uBB38 \uBD84\uC11D \uC624\uB958 "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": \uAD8C\uD55C \uCD94\uAC00 \uC624\uB958 "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": \uD56D\uBAA9 \uCD94\uAC00 \uC624\uB958 "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "\uC77D\uAE30 \uC804\uC6A9 PfrmissionCollfdtion\uC5D0 \uAD8C\uD55C\uC744 \uCD94\uAC00\uD558\uB824\uACE0 \uC2DC\uB3C4\uD588\uC2B5\uB2C8\uB2E4."},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "\uD0A4 \uC800\uC7A5\uC18C \uC720\uD615\uC774 \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "\uC640\uC77C\uB4DC \uCE74\uB4DC \uBB38\uC790 \uC774\uB984 \uC5C6\uC774 \uC640\uC77C\uB4DC \uCE74\uB4DC \uBB38\uC790 \uD074\uB798\uC2A4\uB97C \uC0AC\uC6A9\uD558\uB294 \uC8FC\uCCB4\uB97C \uC9C0\uC815\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "dodfBbsf \uB610\uB294 SignfdBy\uAC00 \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "\uC8FC\uCCB4 \uAE30\uBC18 \uAD8C\uD55C \uBD80\uC5EC \uD56D\uBAA9\uB9CC \uD5C8\uC6A9\uB429\uB2C8\uB2E4."},
        {"fxpfdtfd.pfrmission.fntry", "\uAD8C\uD55C \uD56D\uBAA9\uC774 \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"numbfr.", "\uC22B\uC790 "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "{0}\uC774(\uAC00) \uD544\uC694\uD558\uC9C0\uB9CC \uD30C\uC77C\uC758 \uB05D\uC5D0 \uB3C4\uB2EC\uD588\uC2B5\uB2C8\uB2E4."},
        {"fxpfdtfd.rfbd.fnd.of.filf", "';'\uC774 \uD544\uC694\uD558\uC9C0\uB9CC \uD30C\uC77C\uC758 \uB05D\uC5D0 \uB3C4\uB2EC\uD588\uC2B5\uB2C8\uB2E4."},
        {"linf.", "\uD589 "},
        {".fxpfdtfd.", ": \uD544\uC694\uD55C \uD56D\uBAA9: '"},
        {".found.", "', \uBC1C\uACAC\uB41C \uD56D\uBAA9: '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [\uAE30\uBCF8 \uADF8\uB8F9]: "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [\uBCF4\uC870 \uADF8\uB8F9]: "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl: "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl: "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "\uB110 \uC774\uB984\uC744 \uC81C\uACF5\uD588\uC2B5\uB2C8\uB2E4."}

    };

    /**
     * Rfturns tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     *
     * <p>
     *
     * @rfturn tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     */
    publid Objfdt[][] gftContfnts() {
        rfturn dontfnts;
    }
}
