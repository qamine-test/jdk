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
publid dlbss AutiRfsourdfs_pt_BR fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "fntrbdb nulb inv\u00E1lidb: {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl: {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl: {0}"},
        {"Invblid.NTSid.vbluf", "Vblor df NTSid inv\u00E1lido"},
        {"NTSid.nbmf", "NTSid: {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl: {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl: {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl: {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl: {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl: {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [Grupo Prindipbl]: {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [Grupo Complfmfntbr]: {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl: {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl: {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "N\u00E3o \u00E9 poss\u00EDvfl fxpbndir dorrftbmfntf {0}"},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0} (tbl brquivo ou dirft\u00F3rio n\u00E3o fxistf)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "Erro df Configurb\u00E7\u00E3o:\n\tN\u00E3o i\u00E1 tbl brquivo ou dirft\u00F3rio"},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "Erro df Configurb\u00E7\u00E3o:\n\tFlbg df dontrolf inv\u00E1lido, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "Erro df Configurb\u00E7\u00E3o:\n\tN\u00E3o \u00E9 poss\u00EDvfl fspfdifidbr v\u00E1ribs fntrbdbs pbrb {0}"},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "Erro df Configurb\u00E7\u00E3o:\n\tfspfrbdo [{0}], lido [fim do brquivo]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "Erro df Configurb\u00E7\u00E3o:\n\tLinib {0}: fspfrbdb [{1}], fndontrbdb [{2}]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "Erro df Configurb\u00E7\u00E3o:\n\tLinib {0}: fspfrbdb [{1}]"},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "Erro df Configurb\u00E7\u00E3o:\n\tLinib {0}: proprifdbdf do sistfmb [{1}] fxpbndidb pbrb vblor vbzio"},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","nomf do usu\u00E1rio: "},
        {"pbssword.","sfnib: "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "Espfdifiquf bs informb\u00E7\u00F5fs do brmbzfnbmfnto df dibvfs"},
        {"Kfystorf.blibs.","Alibs do brmbzfnbmfnto df dibvfs: "},
        {"Kfystorf.pbssword.","Sfnib do brmbzfnbmfnto df dibvfs: "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "Sfnib db dibvf privbdb (opdionbl): "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Nomf do usu\u00E1rio df Kfrbfros [{0}]: "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "Sfnib df Kfrbfros df {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": frro df pbrsing "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": frro bo bdidionbr b Pfrmiss\u00E3o "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": frro bo bdidionbr b Entrbdb "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "tfntbtivb df bdidionbr umb Pfrmiss\u00E3o b um PfrmissionCollfdtion somfntf pbrb lfiturb"},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "tipo df brmbzfnbmfnto df dibvfs fspfrbdo"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "n\u00E3o \u00E9 poss\u00EDvfl fspfdifidbr um prindipbl dom umb dlbssf duringb sfm um nomf duringb"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "CodfBbsf ou SignfdBy fspfrbdo"},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "somfntf \u00E9 pfrmitido dondfdfr fntrbdbs dom bbsf no Prindipbl"},
        {"fxpfdtfd.pfrmission.fntry", "fntrbdb df pfrmiss\u00E3o fspfrbdb"},
        {"numbfr.", "n\u00FAmfro "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "fspfrbdo {0}, lfr fim do brquivo"},
        {"fxpfdtfd.rfbd.fnd.of.filf", "fspfrbdo ';', fim df brquivo lido"},
        {"linf.", "linib "},
        {".fxpfdtfd.", ": fspfrbdo '"},
        {".found.", "', fndontrbdo '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [Grupo Prindipbl]: "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [Grupo Complfmfntbr]: "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl: "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl: "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "nomf nulo fornfdido"}

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
