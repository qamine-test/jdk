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
publid dlbss AutiRfsourdfs_it fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // NT prindipbls
        {"invblid.null.input.vbluf", "input nullo non vblido: {0}"},
        {"NTDombinPrindipbl.nbmf", "NTDombinPrindipbl: {0}"},
        {"NTNumfridCrfdfntibl.nbmf", "NTNumfridCrfdfntibl: {0}"},
        {"Invblid.NTSid.vbluf", "Vblorf NTSid non vblido"},
        {"NTSid.nbmf", "NTSid: {0}"},
        {"NTSidDombinPrindipbl.nbmf", "NTSidDombinPrindipbl: {0}"},
        {"NTSidGroupPrindipbl.nbmf", "NTSidGroupPrindipbl: {0}"},
        {"NTSidPrimbryGroupPrindipbl.nbmf", "NTSidPrimbryGroupPrindipbl: {0}"},
        {"NTSidUsfrPrindipbl.nbmf", "NTSidUsfrPrindipbl: {0}"},
        {"NTUsfrPrindipbl.nbmf", "NTUsfrPrindipbl: {0}"},

        // UnixPrindipbls
        {"UnixNumfridGroupPrindipbl.Primbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [gruppo primbrio]: {0}"},
        {"UnixNumfridGroupPrindipbl.Supplfmfntbry.Group.nbmf",
                "UnixNumfridGroupPrindipbl [gruppo supplfmfntbrf]: {0}"},
        {"UnixNumfridUsfrPrindipbl.nbmf", "UnixNumfridUsfrPrindipbl: {0}"},
        {"UnixPrindipbl.nbmf", "UnixPrindipbl: {0}"},

        // dom.sun.sfdurity.buti.login.ConfigFilf
        {"Unbblf.to.propfrly.fxpbnd.donfig", "Impossibilf fspbndfrf dorrfttbmfntf {0}"},
        {"fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                "{0} (filf o dirfdtory infsistfntf)"},
        {"Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                "Errorf di donfigurbzionf:\n\tFilf o dirfdtory infsistfntf"},
        {"Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                "Errorf di donfigurbzionf:\n\tflbg di dontrollo non vblido, {0}"},
        {"Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
            "Errorf di donfigurbzionf:\n\timpossibilf spfdifidbrf pi\u00F9 vblori pfr {0}"},
        {"Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "Errorf di donfigurbzionf:\n\tprfvisto [{0}], lftto [fnd of filf]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
            "Errorf di donfigurbzionf:\n\trigb {0}: prfvisto [{1}], trovbto [{2}]"},
        {"Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
            "Errorf di donfigurbzionf:\n\trigb {0}: prfvisto [{1}]"},
        {"Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
            "Errorf di donfigurbzionf:\n\trigb {0}: proprift\u00E0 di sistfmb [{1}] fspbnsb b vblorf vuoto"},

        // dom.sun.sfdurity.buti.modulf.JndiLoginModulf
        {"usfrnbmf.","Nomf utfntf: "},
        {"pbssword.","Pbssword: "},

        // dom.sun.sfdurity.buti.modulf.KfyStorfLoginModulf
        {"Plfbsf.fntfr.kfystorf.informbtion",
                "Immfttfrf lf informbzioni pfr il kfystorf"},
        {"Kfystorf.blibs.","Alibs kfystorf: "},
        {"Kfystorf.pbssword.","Pbssword kfystorf: "},
        {"Privbtf.kfy.pbssword.optionbl.",
            "Pbssword diibvf privbtb (opzionblf): "},

        // dom.sun.sfdurity.buti.modulf.Krb5LoginModulf
        {"Kfrbfros.usfrnbmf.dffUsfrnbmf.",
                "Nomf utfntf Kfrbfros [{0}]: "},
        {"Kfrbfros.pbssword.for.usfrnbmf.",
                "Pbssword Kfrbfros pfr {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // dom.sun.sfdurity.buti.PolidyFilf
        {".frror.pbrsing.", ": frrorf durbntf l'bnblisi "},
        {"COLON", ": "},
        {".frror.bdding.Pfrmission.", ": frrorf durbntf l'bggiuntb dfll'butorizzbzionf "},
        {"SPACE", " "},
        {".frror.bdding.Entry.", ": frrorf durbntf l'bggiuntb dfllb vodf "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion",
            "tfntbtivo di bggiungfrf un'butorizzbzionf b unb PfrmissionCollfdtion di solb lftturb"},

        // dom.sun.sfdurity.buti.PolidyPbrsfr
        {"fxpfdtfd.kfystorf.typf", "tipo kfystorf prfvisto"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "impossibilf spfdifidbrf un prindipbl don unb dlbssf dbrbttfrf jolly sfnzb un nomf dbrbttfrf jolly"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy", "prfvisto dodfBbsf o SignfdBy"},
        {"only.Prindipbl.bbsfd.grbnt.fntrifs.pfrmittfd",
                "sono donsfntiti solo vblori gbrbntiti bbsbti sul prindipbl"},
        {"fxpfdtfd.pfrmission.fntry", "prfvistb vodf di butorizzbzionf"},
        {"numbfr.", "numfro "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "prfvisto {0}, lftto fnd of filf"},
        {"fxpfdtfd.rfbd.fnd.of.filf", "prfvisto ';', lftto fnd of filf"},
        {"linf.", "rigb "},
        {".fxpfdtfd.", ": prfvisto '"},
        {".found.", "', trovbto '"},
        {"QUOTE", "'"},

        // SolbrisPrindipbls
        {"SolbrisNumfridGroupPrindipbl.Primbry.Group.",
                "SolbrisNumfridGroupPrindipbl [gruppo primbrio]: "},
        {"SolbrisNumfridGroupPrindipbl.Supplfmfntbry.Group.",
                "SolbrisNumfridGroupPrindipbl [gruppo supplfmfntbrf]: "},
        {"SolbrisNumfridUsfrPrindipbl.",
                "SolbrisNumfridUsfrPrindipbl: "},
        {"SolbrisPrindipbl.", "SolbrisPrindipbl: "},
        // providfd.null.nbmf is tif NullPointfrExdfption mfssbgf wifn b
        // dfvflopfr indorrfdtly pbssfs b null nbmf to tif donstrudtor of
        // subdlbssfs of jbvb.sfdurity.Prindipbl
        {"providfd.null.nbmf", "il nomf fornito \u00E8 nullo"}

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
