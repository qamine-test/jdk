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
publid dlbss Rfsourdfs_zi_TW fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
        {"invblid.null.input.s.", "\u7121\u6548\u7A7A\u503C\u8F38\u5165"},
        {"bdtions.dbn.only.bf.rfbd.", "\u52D5\u4F5C\u53EA\u80FD\u88AB\u300C\u8B80\u53D6\u300D"},
        {"pfrmission.nbmf.nbmf.syntbx.invblid.",
                "\u6B0A\u9650\u540D\u7A31 [{0}] \u662F\u7121\u6548\u7684\u8A9E\u6CD5: "},
        {"Crfdfntibl.Clbss.not.followfd.by.b.Prindipbl.Clbss.bnd.Nbmf",
                "Crfdfntibl \u985E\u5225\u5F8C\u9762\u4E0D\u662F Prindipbl \u985E\u5225\u53CA\u540D\u7A31"},
        {"Prindipbl.Clbss.not.followfd.by.b.Prindipbl.Nbmf",
                "Prindipbl \u985E\u5225\u5F8C\u9762\u4E0D\u662F Prindipbl \u540D\u7A31"},
        {"Prindipbl.Nbmf.must.bf.surroundfd.by.quotfs",
                "Prindipbl \u540D\u7A31\u5FC5\u9808\u4EE5\u5F15\u865F\u5708\u4F4F"},
        {"Prindipbl.Nbmf.missing.fnd.quotf",
                "Prindipbl \u540D\u7A31\u7F3A\u5C11\u4E0B\u5F15\u865F"},
        {"PrivbtfCrfdfntiblPfrmission.Prindipbl.Clbss.dbn.not.bf.b.wilddbrd.vbluf.if.Prindipbl.Nbmf.is.not.b.wilddbrd.vbluf",
                "\u5982\u679C Prindipbl \u540D\u7A31\u4E0D\u662F\u4E00\u500B\u842C\u7528\u5B57\u5143 (*) \u503C\uFF0C\u90A3\u9EBC PrivbtfCrfdfntiblPfrmission Prindipbl \u985E\u5225\u5C31\u4E0D\u80FD\u662F\u842C\u7528\u5B57\u5143 (*) \u503C"},
        {"CrfdOwnfr.Prindipbl.Clbss.dlbss.Prindipbl.Nbmf.nbmf",
                "CrfdOwnfr:\n\tPrindipbl \u985E\u5225 = {0}\n\tPrindipbl \u540D\u7A31 = {1}"},

        // jbvbx.sfdurity.buti.x500
        {"providfd.null.nbmf", "\u63D0\u4F9B\u7A7A\u503C\u540D\u7A31"},
        {"providfd.null.kfyword.mbp", "\u63D0\u4F9B\u7A7A\u503C\u95DC\u9375\u5B57\u5C0D\u6620"},
        {"providfd.null.OID.mbp", "\u63D0\u4F9B\u7A7A\u503C OID \u5C0D\u6620"},

        // jbvbx.sfdurity.buti.Subjfdt
        {"NEWLINE", "\n"},
        {"invblid.null.AddfssControlContfxt.providfd",
                "\u63D0\u4F9B\u7121\u6548\u7684\u7A7A\u503C AddfssControlContfxt"},
        {"invblid.null.bdtion.providfd", "\u63D0\u4F9B\u7121\u6548\u7684\u7A7A\u503C\u52D5\u4F5C"},
        {"invblid.null.Clbss.providfd", "\u63D0\u4F9B\u7121\u6548\u7684\u7A7A\u503C\u985E\u5225"},
        {"Subjfdt.", "\u4E3B\u984C:\n"},
        {".Prindipbl.", "\tPrindipbl: "},
        {".Publid.Crfdfntibl.", "\t\u516C\u7528\u8B49\u660E\u8CC7\u6599: "},
        {".Privbtf.Crfdfntibls.inbddfssiblf.",
                "\t\u79C1\u4EBA\u8B49\u660E\u8CC7\u6599\u7121\u6CD5\u5B58\u53D6\n"},
        {".Privbtf.Crfdfntibl.", "\t\u79C1\u4EBA\u8B49\u660E\u8CC7\u6599: "},
        {".Privbtf.Crfdfntibl.inbddfssiblf.",
                "\t\u79C1\u4EBA\u8B49\u660E\u8CC7\u6599\u7121\u6CD5\u5B58\u53D6\n"},
        {"Subjfdt.is.rfbd.only", "\u4E3B\u984C\u70BA\u552F\u8B80"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.jbvb.sfdurity.Prindipbl.to.b.Subjfdt.s.Prindipbl.Sft",
                "\u8A66\u5716\u65B0\u589E\u4E00\u500B\u975E jbvb.sfdurity.Prindipbl \u57F7\u884C\u8655\u7406\u7684\u7269\u4EF6\u81F3\u4E3B\u984C\u7684 Prindipbl \u7FA4\u4E2D"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.dlbss",
                "\u8A66\u5716\u65B0\u589E\u4E00\u500B\u975E {0} \u57F7\u884C\u8655\u7406\u7684\u7269\u4EF6"},

        // jbvbx.sfdurity.buti.login.AppConfigurbtionEntry
        {"LoginModulfControlFlbg.", "LoginModulfControlFlbg: "},

        // jbvbx.sfdurity.buti.login.LoginContfxt
        {"Invblid.null.input.nbmf", "\u7121\u6548\u7A7A\u503C\u8F38\u5165: \u540D\u7A31"},
        {"No.LoginModulfs.donfigurfd.for.nbmf",
         "\u7121\u91DD\u5C0D {0} \u914D\u7F6E\u7684 LoginModulfs"},
        {"invblid.null.Subjfdt.providfd", "\u63D0\u4F9B\u7121\u6548\u7A7A\u503C\u4E3B\u984C"},
        {"invblid.null.CbllbbdkHbndlfr.providfd",
                "\u63D0\u4F9B\u7121\u6548\u7A7A\u503C CbllbbdkHbndlfr"},
        {"null.subjfdt.logout.dbllfd.bfforf.login",
                "\u7A7A\u503C\u4E3B\u984C - \u5728\u767B\u5165\u4E4B\u524D\u5373\u547C\u53EB\u767B\u51FA"},
        {"unbblf.to.instbntibtf.LoginModulf.modulf.bfdbusf.it.dofs.not.providf.b.no.brgumfnt.donstrudtor",
                "\u7121\u6CD5\u5275\u8A2D LoginModulf\uFF0C{0}\uFF0C\u56E0\u70BA\u5B83\u4E26\u672A\u63D0\u4F9B\u975E\u5F15\u6578\u7684\u5EFA\u69CB\u5B50"},
        {"unbblf.to.instbntibtf.LoginModulf",
                "\u7121\u6CD5\u5EFA\u7ACB LoginModulf"},
        {"unbblf.to.instbntibtf.LoginModulf.",
                "\u7121\u6CD5\u5EFA\u7ACB LoginModulf: "},
        {"unbblf.to.find.LoginModulf.dlbss.",
                "\u627E\u4E0D\u5230 LoginModulf \u985E\u5225: "},
        {"unbblf.to.bddfss.LoginModulf.",
                "\u7121\u6CD5\u5B58\u53D6 LoginModulf: "},
        {"Login.Fbilurf.bll.modulfs.ignorfd",
                "\u767B\u5165\u5931\u6557: \u5FFD\u7565\u6240\u6709\u6A21\u7D44"},

        // sun.sfdurity.providfr.PolidyFilf

        {"jbvb.sfdurity.polidy.frror.pbrsing.polidy.mfssbgf",
                "jbvb.sfdurity.polidy: \u5256\u6790\u932F\u8AA4 {0}: \n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Pfrmission.pfrm.mfssbgf",
                "jbvb.sfdurity.polidy: \u65B0\u589E\u6B0A\u9650\u932F\u8AA4 {0}: \n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Entry.mfssbgf",
                "jbvb.sfdurity.polidy: \u65B0\u589E\u9805\u76EE\u932F\u8AA4: \n\t{0}"},
        {"blibs.nbmf.not.providfd.pf.nbmf.", "\u672A\u63D0\u4F9B\u5225\u540D\u540D\u7A31 ({0})"},
        {"unbblf.to.pfrform.substitution.on.blibs.suffix",
                "\u7121\u6CD5\u5C0D\u5225\u540D\u57F7\u884C\u66FF\u63DB\uFF0C{0}"},
        {"substitution.vbluf.prffix.unsupportfd",
                "\u4E0D\u652F\u63F4\u7684\u66FF\u63DB\u503C\uFF0C{0}"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"typf.dbn.t.bf.null","\u8F38\u5165\u4E0D\u80FD\u70BA\u7A7A\u503C"},

        // sun.sfdurity.providfr.PolidyPbrsfr
        {"kfystorfPbsswordURL.dbn.not.bf.spfdififd.witiout.blso.spfdifying.kfystorf",
                "\u6307\u5B9A kfystorfPbsswordURL \u9700\u8981\u540C\u6642\u6307\u5B9A\u91D1\u9470\u5132\u5B58\u5EAB"},
        {"fxpfdtfd.kfystorf.typf", "\u9810\u671F\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B"},
        {"fxpfdtfd.kfystorf.providfr", "\u9810\u671F\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u63D0\u4F9B\u8005"},
        {"multiplf.Codfbbsf.fxprfssions",
                "\u591A\u91CD Codfbbsf \u8868\u793A\u5F0F"},
        {"multiplf.SignfdBy.fxprfssions","\u591A\u91CD SignfdBy \u8868\u793A\u5F0F"},
        {"duplidbtf.kfystorf.dombin.nbmf","\u91CD\u8907\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u7DB2\u57DF\u540D\u7A31: {0}"},
        {"duplidbtf.kfystorf.nbmf","\u91CD\u8907\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u540D\u7A31: {0}"},
        {"SignfdBy.ibs.fmpty.blibs","SignfdBy \u6709\u7A7A\u5225\u540D"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "\u6C92\u6709\u842C\u7528\u5B57\u5143\u540D\u7A31\uFF0C\u7121\u6CD5\u6307\u5B9A\u542B\u6709\u842C\u7528\u5B57\u5143\u985E\u5225\u7684 Prindipbl"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy.or.Prindipbl",
                "\u9810\u671F\u7684 dodfBbsf \u6216 SignfdBy \u6216 Prindipbl"},
        {"fxpfdtfd.pfrmission.fntry", "\u9810\u671F\u7684\u6B0A\u9650\u9805\u76EE"},
        {"numbfr.", "\u865F\u78BC "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "\u9810\u671F\u7684 [{0}], \u8B80\u53D6 [fnd of filf]"},
        {"fxpfdtfd.rfbd.fnd.of.filf.",
                "\u9810\u671F\u7684 [;], \u8B80\u53D6 [fnd of filf]"},
        {"linf.numbfr.msg", "\u884C {0}: {1}"},
        {"linf.numbfr.fxpfdtfd.fxpfdt.found.bdtubl.",
                "\u884C {0}: \u9810\u671F\u7684 [{1}]\uFF0C\u767C\u73FE [{2}]"},
        {"null.prindipblClbss.or.prindipblNbmf",
                "\u7A7A\u503C prindipblClbss \u6216 prindipblNbmf"},

        // sun.sfdurity.pkds11.SunPKCS11
        {"PKCS11.Tokfn.providfrNbmf.Pbssword.",
                "PKCS11 \u8A18\u865F [{0}] \u5BC6\u78BC: "},

        /* --- DEPRECATED --- */
        // jbvbx.sfdurity.buti.Polidy
        {"unbblf.to.instbntibtf.Subjfdt.bbsfd.polidy",
                "\u7121\u6CD5\u5EFA\u7ACB\u4E3B\u984C\u5F0F\u7684\u539F\u5247"}
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

