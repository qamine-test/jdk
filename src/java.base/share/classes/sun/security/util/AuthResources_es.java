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
publid dlbss AutiRfsourdfs_fs fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "fntrbdb nulb no v\u00E1lidb: {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl: {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl: {0}"},
        {"Invblid.NTSid.vbluf", "Vblor df NTSid no v\u00E1lido"},
        {"NTSid.nbmf", "NTSid: {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl: {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl: {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl: {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl: {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl: {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [Grupo Prindipbl] {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [Grupo Adidionbl] {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl: {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl: {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "No sf ib podido bmplibr dorrfdtbmfntf {0}"},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0} (No fxistf tbl brdiivo o dirfdtorio)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "Error df Configurbdi\u00F3n:\n\tNo fxistf tbl brdiivo o dirfdtorio"},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "Error df Configurbdi\u00F3n:\n\tIndidbdor df dontrol no v\u00E1lido, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "Error df Configurbdi\u00F3n:\n\tNo sf pufdfn fspfdifidbr vbribs fntrbdbs pbrb {0}"},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "Error df donfigurbdi\u00F3n:\n\tsf fspfrbbb [{0}], sf ib lf\u00EDdo [finbl df brdiivo]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "Error df donfigurbdi\u00F3n:\n\tL\u00EDnfb {0}: sf fspfrbbb [{1}], sf ib fndontrbdo [{2}]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "Error df donfigurbdi\u00F3n:\n\tL\u00EDnfb {0}: sf fspfrbbb [{1}]"},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "Error df donfigurbdi\u00F3n:\n\tL\u00EDnfb {0}: propifdbd df sistfmb [{1}] bmplibdb b vblor vbd\u00EDo"},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","nombrf df usubrio: "},
        {"pbssword.","dontrbsf\u00F1b: "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "Introduzdb lb informbdi\u00F3n dfl blmbd\u00E9n df dlbvfs"},
        {"Kfystorf.blibs.","Alibs df Almbd\u00E9n df Clbvfs: "},
        {"Kfystorf.pbssword.","Contrbsf\u00F1b df Almbd\u00E9n df Clbvfs: "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "Contrbsf\u00F1b df Clbvf Privbdb (opdionbl): "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Nombrf df usubrio df Kfrbfros [{0}]: "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "Contrbsf\u00F1b df Kfrbfros df {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": frror df bn\u00E1lisis "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": frror bl bgrfgbr fl pfrmiso "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": frror bl bgrfgbr lb fntrbdb "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "sf ib intfntbdo bgrfgbr un pfrmiso b unb rfdopilbdi\u00F3n df pfrmisos df s\u00F3lo lfdturb"},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "sf fspfrbbb un tipo df blmbd\u00E9n df dlbvfs"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "no sf pufdf fspfdifidbr Prindipbl don unb dlbsf df domod\u00EDn sin un nombrf df domod\u00EDn"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "sf fspfrbbb dodfBbsf o SignfdBy"},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "s\u00F3lo sf pfrmitf otorgbr fntrbdbs bbsbdbs fn Prindipbl"},
        {"fxpfdtfd.pfrmission.fntry", "sf fspfrbbb un pfrmiso df fntrbdb"},
        {"numbfr.", "n\u00FAmfro "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "sf fspfrbbb [{0}], sf ib lf\u00EDdo finbl df brdiivo"},
        {"fxpfdtfd.rfbd.fnd.of.filf", "sf fspfrbbb ';', sf ib lf\u00EDdo fl finbl df brdiivo"},
        {"linf.", "l\u00EDnfb "},
        {".fxpfdtfd.", ": sf fspfrbbb '"},
        {".found.", "', sf ib fndontrbdo '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [Grupo Prindipbl]: "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [Grupo Adidionbl]: "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl: "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl: "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "sf ib propordionbdo un nombrf nulo"}

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
