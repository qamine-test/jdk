/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
publid dlbss AutiRfsourdfs_zi_CN fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "\u65E0\u6548\u7684\u7A7A\u8F93\u5165: {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl: {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl: {0}"},
        {"Invblid.NTSid.vbluf", "\u65E0\u6548\u7684 NTSid \u503C"},
        {"NTSid.nbmf", "NTSid: {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl: {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl: {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl: {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl: {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl: {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [\u4E3B\u7EC4]: {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [\u8865\u5145\u7EC4]: {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl: {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl: {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "\u65E0\u6CD5\u6B63\u786E\u6269\u5C55{0}"},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0} (\u6CA1\u6709\u8FD9\u6837\u7684\u6587\u4EF6\u6216\u76EE\u5F55)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "\u914D\u7F6E\u9519\u8BEF:\n\t\u6CA1\u6709\u6B64\u6587\u4EF6\u6216\u76EE\u5F55"},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "\u914D\u7F6E\u9519\u8BEF: \n\t\u65E0\u6548\u7684\u63A7\u5236\u6807\u8BB0, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "\u914D\u7F6E\u9519\u8BEF:\n\t\u65E0\u6CD5\u6307\u5B9A{0}\u7684\u591A\u4E2A\u6761\u76EE"},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "\u914D\u7F6E\u9519\u8BEF: \n\t\u5E94\u4E3A [{0}], \u8BFB\u53D6\u7684\u662F [\u6587\u4EF6\u7ED3\u5C3E]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "\u914D\u7F6E\u9519\u8BEF: \n\t\u884C {0}: \u5E94\u4E3A [{1}], \u627E\u5230 [{2}]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "\u914D\u7F6E\u9519\u8BEF: \n\t\u884C {0}: \u5E94\u4E3A [{1}]"},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "\u914D\u7F6E\u9519\u8BEF: \n\t\u884C {0}: \u7CFB\u7EDF\u5C5E\u6027 [{1}] \u6269\u5C55\u5230\u7A7A\u503C"},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","\u7528\u6237\u540D: "},
        {"pbssword.","\u53E3\u4EE4: "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "\u8BF7\u8F93\u5165\u5BC6\u94A5\u5E93\u4FE1\u606F"},
        {"Kfystorf.blibs.","\u5BC6\u94A5\u5E93\u522B\u540D: "},
        {"Kfystorf.pbssword.","\u5BC6\u94A5\u5E93\u53E3\u4EE4: "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "\u79C1\u6709\u5BC6\u94A5\u53E3\u4EE4 (\u53EF\u9009): "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Kfrbfros \u7528\u6237\u540D [{0}]: "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "{0}\u7684 Kfrbfros \u53E3\u4EE4: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": \u89E3\u6790\u65F6\u51FA\u9519 "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": \u6DFB\u52A0\u6743\u9650\u65F6\u51FA\u9519 "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": \u6DFB\u52A0\u6761\u76EE\u65F6\u51FA\u9519 "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "\u5C1D\u8BD5\u5C06\u6743\u9650\u6DFB\u52A0\u81F3\u53EA\u8BFB\u7684 PfrmissionCollfdtion"},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "\u5E94\u4E3A\u5BC6\u94A5\u5E93\u7C7B\u578B"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "\u6CA1\u6709\u901A\u914D\u7B26\u540D\u79F0, \u65E0\u6CD5\u4F7F\u7528\u901A\u914D\u7B26\u7C7B\u6307\u5B9A\u4E3B\u7528\u6237"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "\u5E94\u4E3A dodfBbsf \u6216 SignfdBy"},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "\u53EA\u5141\u8BB8\u57FA\u4E8E\u4E3B\u7528\u6237\u7684\u6388\u6743\u6761\u76EE"},
        {"fxpfdtfd.pfrmission.fntry", "\u5E94\u4E3A\u6743\u9650\u6761\u76EE"},
        {"numbfr.", "\u7F16\u53F7 "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "\u5E94\u4E3A{0}, \u8BFB\u53D6\u7684\u662F\u6587\u4EF6\u7ED3\u5C3E"},
        {"fxpfdtfd.rfbd.fnd.of.filf", "\u5E94\u4E3A ';', \u8BFB\u53D6\u7684\u662F\u6587\u4EF6\u7ED3\u5C3E"},
        {"linf.", "\u884C "},
        {".fxpfdtfd.", ": \u5E94\u4E3A '"},
        {".found.", "', \u627E\u5230 '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [\u4E3B\u7EC4]: "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [\u8865\u5145\u7EC4]: "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl: "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl: "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "\u63D0\u4F9B\u7684\u540D\u79F0\u4E3A\u7A7A\u503C"}

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
