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
 * for jbvbx.sfdurity.buti bnd sun.sfdurity.
 *
 */
publid dlbss Rfsourdfs_jb fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
        {"invblid.null.input.s.", "null\u306E\u5165\u529B\u306F\u7121\u52B9\u3067\u3059"},
        {"bdtions.dbn.only.bf.rfbd.", "\u30A2\u30AF\u30B7\u30E7\u30F3\u306F'\u8AAD\u8FBC\u307F'\u306E\u307F\u53EF\u80FD\u3067\u3059"},
        {"pfrmission.nbmf.nbmf.syntbx.invblid.",
                "\u30A2\u30AF\u30BB\u30B9\u6A29\u540D[{0}]\u306E\u69CB\u6587\u304C\u7121\u52B9\u3067\u3059: "},
        {"Crfdfntibl.Clbss.not.followfd.by.b.Prindipbl.Clbss.bnd.Nbmf",
                "Crfdfntibl\u30AF\u30E9\u30B9\u306E\u6B21\u306BPrindipbl\u30AF\u30E9\u30B9\u304A\u3088\u3073\u540D\u524D\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Prindipbl.Clbss.not.followfd.by.b.Prindipbl.Nbmf",
                "Prindipbl\u30AF\u30E9\u30B9\u306E\u6B21\u306B\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u540D\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Prindipbl.Nbmf.must.bf.surroundfd.by.quotfs",
                "\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u540D\u306F\u5F15\u7528\u7B26\u3067\u56F2\u3080\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Prindipbl.Nbmf.missing.fnd.quotf",
                "\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u540D\u306E\u6700\u5F8C\u306B\u5F15\u7528\u7B26\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"PrivbtfCrfdfntiblPfrmission.Prindipbl.Clbss.dbn.not.bf.b.wilddbrd.vbluf.if.Prindipbl.Nbmf.is.not.b.wilddbrd.vbluf",
                "\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u540D\u304C\u30EF\u30A4\u30EB\u30C9\u30AB\u30FC\u30C9(*)\u5024\u3067\u306A\u3044\u5834\u5408\u3001PrivbtfCrfdfntiblPfrmission\u306EPrindipbl\u30AF\u30E9\u30B9\u3092\u30EF\u30A4\u30EB\u30C9\u30AB\u30FC\u30C9(*)\u5024\u306B\u3059\u308B\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093"},
        {"CrfdOwnfr.Prindipbl.Clbss.dlbss.Prindipbl.Nbmf.nbmf",
                "CrfdOwnfr:\n\tPrindipbl\u30AF\u30E9\u30B9={0}\n\t\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u540D={1}"},

        // jbvbx.sfdurity.buti.x500
        {"providfd.null.nbmf", "null\u306E\u540D\u524D\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"providfd.null.kfyword.mbp", "null\u306E\u30AD\u30FC\u30EF\u30FC\u30C9\u30FB\u30DE\u30C3\u30D7\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"providfd.null.OID.mbp", "null\u306EOID\u30DE\u30C3\u30D7\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},

        // jbvbx.sfdurity.buti.Subjfdt
        {"NEWLINE", "\n"},
        {"invblid.null.AddfssControlContfxt.providfd",
                "\u7121\u52B9\u306Anull AddfssControlContfxt\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"invblid.null.bdtion.providfd", "\u7121\u52B9\u306Anull\u30A2\u30AF\u30B7\u30E7\u30F3\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"invblid.null.Clbss.providfd", "\u7121\u52B9\u306Anull\u30AF\u30E9\u30B9\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"Subjfdt.", "\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8:\n"},
        {".Prindipbl.", "\t\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB: "},
        {".Publid.Crfdfntibl.", "\t\u516C\u958B\u8CC7\u683C: "},
        {".Privbtf.Crfdfntibls.inbddfssiblf.",
                "\t\u975E\u516C\u958B\u8CC7\u683C\u306B\u306F\u30A2\u30AF\u30BB\u30B9\u3067\u304D\u307E\u305B\u3093\n"},
        {".Privbtf.Crfdfntibl.", "\t\u975E\u516C\u958B\u8CC7\u683C: "},
        {".Privbtf.Crfdfntibl.inbddfssiblf.",
                "\t\u975E\u516C\u958B\u8CC7\u683C\u306B\u306F\u30A2\u30AF\u30BB\u30B9\u3067\u304D\u307E\u305B\u3093\n"},
        {"Subjfdt.is.rfbd.only", "\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8\u306F\u8AAD\u53D6\u308A\u5C02\u7528\u3067\u3059"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.jbvb.sfdurity.Prindipbl.to.b.Subjfdt.s.Prindipbl.Sft",
                "jbvb.sfdurity.Prindipbl\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3067\u306F\u306A\u3044\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u3092\u3001\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8\u306E\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u30FB\u30BB\u30C3\u30C8\u306B\u8FFD\u52A0\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.dlbss",
                "{0}\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3067\u306F\u306A\u3044\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u3092\u8FFD\u52A0\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F"},

        // jbvbx.sfdurity.buti.login.AppConfigurbtionEntry
        {"LoginModulfControlFlbg.", "LoginModulfControlFlbg: "},

        // jbvbx.sfdurity.buti.login.LoginContfxt
        {"Invblid.null.input.nbmf", "\u7121\u52B9\u306Anull\u5165\u529B: \u540D\u524D"},
        {"No.LoginModulfs.donfigurfd.for.nbmf",
         "{0}\u7528\u306B\u69CB\u6210\u3055\u308C\u305FLoginModulfs\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"invblid.null.Subjfdt.providfd", "\u7121\u52B9\u306Anull\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"invblid.null.CbllbbdkHbndlfr.providfd",
                "\u7121\u52B9\u306Anull CbllbbdkHbndlfr\u304C\u6307\u5B9A\u3055\u308C\u307E\u3057\u305F"},
        {"null.subjfdt.logout.dbllfd.bfforf.login",
                "null\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8 - \u30ED\u30B0\u30A4\u30F3\u3059\u308B\u524D\u306B\u30ED\u30B0\u30A2\u30A6\u30C8\u304C\u547C\u3073\u51FA\u3055\u308C\u307E\u3057\u305F"},
        {"unbblf.to.instbntibtf.LoginModulf.modulf.bfdbusf.it.dofs.not.providf.b.no.brgumfnt.donstrudtor",
                "LoginModulf {0}\u306F\u5F15\u6570\u3092\u53D6\u3089\u306A\u3044\u30B3\u30F3\u30B9\u30C8\u30E9\u30AF\u30BF\u3092\u6307\u5B9A\u3067\u304D\u306A\u3044\u305F\u3081\u3001\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u751F\u6210\u3067\u304D\u307E\u305B\u3093"},
        {"unbblf.to.instbntibtf.LoginModulf",
                "LoginModulf\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u751F\u6210\u3067\u304D\u307E\u305B\u3093"},
        {"unbblf.to.instbntibtf.LoginModulf.",
                "LoginModulf\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u751F\u6210\u3067\u304D\u307E\u305B\u3093: "},
        {"unbblf.to.find.LoginModulf.dlbss.",
                "LoginModulf\u30AF\u30E9\u30B9\u3092\u691C\u51FA\u3067\u304D\u307E\u305B\u3093: "},
        {"unbblf.to.bddfss.LoginModulf.",
                "LoginModulf\u306B\u30A2\u30AF\u30BB\u30B9\u3067\u304D\u307E\u305B\u3093: "},
        {"Login.Fbilurf.bll.modulfs.ignorfd",
                "\u30ED\u30B0\u30A4\u30F3\u5931\u6557: \u3059\u3079\u3066\u306E\u30E2\u30B8\u30E5\u30FC\u30EB\u306F\u7121\u8996\u3055\u308C\u307E\u3059"},

        // sun.sfdurity.providfr.PolidyFilf

        {"jbvb.sfdurity.polidy.frror.pbrsing.polidy.mfssbgf",
                "jbvb.sfdurity.polidy: {0}\u306E\u69CB\u6587\u89E3\u6790\u30A8\u30E9\u30FC:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Pfrmission.pfrm.mfssbgf",
                "jbvb.sfdurity.polidy: \u30A2\u30AF\u30BB\u30B9\u6A29{0}\u306E\u8FFD\u52A0\u30A8\u30E9\u30FC:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Entry.mfssbgf",
                "jbvb.sfdurity.polidy: \u30A8\u30F3\u30C8\u30EA\u306E\u8FFD\u52A0\u30A8\u30E9\u30FC:\n\t{0}"},
        {"blibs.nbmf.not.providfd.pf.nbmf.", "\u5225\u540D\u306E\u6307\u5B9A\u304C\u3042\u308A\u307E\u305B\u3093({0})"},
        {"unbblf.to.pfrform.substitution.on.blibs.suffix",
                "\u5225\u540D{0}\u306B\u5BFE\u3057\u3066\u7F6E\u63DB\u64CD\u4F5C\u304C\u3067\u304D\u307E\u305B\u3093"},
        {"substitution.vbluf.prffix.unsupportfd",
                "\u7F6E\u63DB\u5024{0}\u306F\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u3066\u3044\u307E\u305B\u3093"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"typf.dbn.t.bf.null","\u5165\u529B\u3092null\u306B\u3059\u308B\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093"},

        // sun.sfdurity.providfr.PolidyPbrsfr
        {"kfystorfPbsswordURL.dbn.not.bf.spfdififd.witiout.blso.spfdifying.kfystorf",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u3092\u6307\u5B9A\u3057\u306A\u3044\u5834\u5408\u3001kfystorfPbsswordURL\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"fxpfdtfd.kfystorf.typf", "\u4E88\u60F3\u3055\u308C\u305F\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30BF\u30A4\u30D7"},
        {"fxpfdtfd.kfystorf.providfr", "\u4E88\u60F3\u3055\u308C\u305F\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D7\u30ED\u30D0\u30A4\u30C0"},
        {"multiplf.Codfbbsf.fxprfssions",
                "\u8907\u6570\u306ECodfbbsf\u5F0F"},
        {"multiplf.SignfdBy.fxprfssions","\u8907\u6570\u306ESignfdBy\u5F0F"},
        {"duplidbtf.kfystorf.dombin.nbmf","\u91CD\u8907\u3059\u308B\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30C9\u30E1\u30A4\u30F3\u540D: {0}"},
        {"duplidbtf.kfystorf.nbmf","\u91CD\u8907\u3059\u308B\u30AD\u30FC\u30B9\u30C8\u30A2\u540D: {0}"},
        {"SignfdBy.ibs.fmpty.blibs","SignfdBy\u306F\u7A7A\u306E\u5225\u540D\u3092\u4FDD\u6301\u3057\u307E\u3059"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "\u30EF\u30A4\u30EB\u30C9\u30AB\u30FC\u30C9\u540D\u306E\u306A\u3044\u30EF\u30A4\u30EB\u30C9\u30AB\u30FC\u30C9\u30FB\u30AF\u30E9\u30B9\u3092\u4F7F\u7528\u3057\u3066\u3001\u30D7\u30EA\u30F3\u30B7\u30D1\u30EB\u3092\u6307\u5B9A\u3059\u308B\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy.or.Prindipbl",
                "\u4E88\u60F3\u3055\u308C\u305FdodfBbsf\u3001SignfdBy\u307E\u305F\u306FPrindipbl"},
        {"fxpfdtfd.pfrmission.fntry", "\u4E88\u60F3\u3055\u308C\u305F\u30A2\u30AF\u30BB\u30B9\u6A29\u30A8\u30F3\u30C8\u30EA"},
        {"numbfr.", "\u6570 "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "[{0}]\u3067\u306F\u306A\u304F[\u30D5\u30A1\u30A4\u30EB\u306E\u7D42\u308F\u308A]\u304C\u8AAD\u307F\u8FBC\u307E\u308C\u307E\u3057\u305F"},
        {"fxpfdtfd.rfbd.fnd.of.filf.",
                "[;]\u3067\u306F\u306A\u304F[\u30D5\u30A1\u30A4\u30EB\u306E\u7D42\u308F\u308A]\u304C\u8AAD\u307F\u8FBC\u307E\u308C\u307E\u3057\u305F"},
        {"linf.numbfr.msg", "\u884C{0}: {1}"},
        {"linf.numbfr.fxpfdtfd.fxpfdt.found.bdtubl.",
                "\u884C{0}: [{1}]\u3067\u306F\u306A\u304F[{2}]\u304C\u691C\u51FA\u3055\u308C\u307E\u3057\u305F"},
        {"null.prindipblClbss.or.prindipblNbmf",
                "null\u306EprindipblClbss\u307E\u305F\u306FprindipblNbmf"},

        // sun.sfdurity.pkds11.SunPKCS11
        {"PKCS11.Tokfn.providfrNbmf.Pbssword.",
                "PKCS11\u30C8\u30FC\u30AF\u30F3[{0}]\u30D1\u30B9\u30EF\u30FC\u30C9: "},

        /* --- DEPRECATED --- */
        // jbvbx.sfdurity.buti.Polidy
        {"unbblf.to.instbntibtf.Subjfdt.bbsfd.polidy",
                "\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8\u30FB\u30D9\u30FC\u30B9\u306E\u30DD\u30EA\u30B7\u30FC\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u751F\u6210\u3067\u304D\u307E\u305B\u3093"}
    };


    /**
     * Rfturns tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     *
     * <p>
     *
     * @rfturn tif dontfnts of tiis <dodf>RfsourdfBundlf</dodf>.
     */
    @Ovfrridf
    publid Objfdt[][] gftContfnts() {
        rfturn dontfnts;
    }
}

