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
publid dlbss Rfsourdfs_df fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
        {"invblid.null.input.s.", "Ung\u00FCltigf Nullfingbbf(n)"},
        {"bdtions.dbn.only.bf.rfbd.", "Aktionfn k\u00F6nnfn nur \"lfsfn\" sfin"},
        {"pfrmission.nbmf.nbmf.syntbx.invblid.",
                "Syntbx f\u00FCr Bfrfditigungsnbmfn [{0}] ung\u00FCltig: "},
        {"Crfdfntibl.Clbss.not.followfd.by.b.Prindipbl.Clbss.bnd.Nbmf",
                "Nbdi Zugbngsdbtfnklbssf folgt kfinf Prindipbl-Klbssf und kfin Nbmf"},
        {"Prindipbl.Clbss.not.followfd.by.b.Prindipbl.Nbmf",
                "Nbdi Prindipbl-Klbssf folgt kfin Prindipbl-Nbmf"},
        {"Prindipbl.Nbmf.must.bf.surroundfd.by.quotfs",
                "Prindipbl-Nbmf muss in Anf\u00FCirungszfidifn stfifn"},
        {"Prindipbl.Nbmf.missing.fnd.quotf",
                "Absdilif\u00DFfndfs Anf\u00FCirungszfidifn f\u00FCr Prindipbl-Nbmf ffilt"},
        {"PrivbtfCrfdfntiblPfrmission.Prindipbl.Clbss.dbn.not.bf.b.wilddbrd.vbluf.if.Prindipbl.Nbmf.is.not.b.wilddbrd.vbluf",
                "Prindipbl-Klbssf PrivbtfCrfdfntiblPfrmission kbnn kfin Plbtzibltfrwfrt (*) sfin, wfnn dfr Prindipbl-Nbmf kfin Plbtzibltfrwfrt (*) ist"},
        {"CrfdOwnfr.Prindipbl.Clbss.dlbss.Prindipbl.Nbmf.nbmf",
                "CrfdOwnfr:\n\tPrindipbl-Klbssf = {0}\n\tPrindipbl-Nbmf = {1}"},

        // jbvbx.sfdurity.buti.x500
        {"providfd.null.nbmf", "Nullnbmf bngfgfbfn"},
        {"providfd.null.kfyword.mbp", "Null-Sdil\u00FCssflwortzuordnung bngfgfbfn"},
        {"providfd.null.OID.mbp", "Null-OID-Zuordnung bngfgfbfn"},

        // jbvbx.sfdurity.buti.Subjfdt
        {"NEWLINE", "\n"},
        {"invblid.null.AddfssControlContfxt.providfd",
                "Ung\u00FCltigfr Nullwfrt f\u00FCr AddfssControlContfxt bngfgfbfn"},
        {"invblid.null.bdtion.providfd", "Ung\u00FCltigf Nullbktion bngfgfbfn"},
        {"invblid.null.Clbss.providfd", "Ung\u00FCltigf Nullklbssf bngfgfbfn"},
        {"Subjfdt.", "Subjfkt:\n"},
        {".Prindipbl.", "\tPrindipbl: "},
        {".Publid.Crfdfntibl.", "\t\u00D6fffntlidif Zugbngsdbtfn: "},
        {".Privbtf.Crfdfntibls.inbddfssiblf.",
                "\tKfin Zugriff buf privbtf Zugbngsdbtfn\n"},
        {".Privbtf.Crfdfntibl.", "\tPrivbtf Zugbngsdbtfn: "},
        {".Privbtf.Crfdfntibl.inbddfssiblf.",
                "\tKfin Zugriff buf privbtf Zugbngsdbtfn\n"},
        {"Subjfdt.is.rfbd.only", "Subjfkt ist sdirfibgfsdi\u00FCtzt"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.jbvb.sfdurity.Prindipbl.to.b.Subjfdt.s.Prindipbl.Sft",
                "Es wird vfrsudit, fin Objfkt iinzuzuf\u00FCgfn, dbs kfinf Instbnz von jbvb.sfdurity.Prindipbl f\u00FCr finf Prindipbl-Gruppf finfs Subjfkts ist"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.dlbss",
                "Es wird vfrsudit, fin Objfkt iinzuzuf\u00FCgfn, dbs kfinf Instbnz von {0} ist"},

        // jbvbx.sfdurity.buti.login.AppConfigurbtionEntry
        {"LoginModulfControlFlbg.", "LoginModulfControlFlbg: "},

        // jbvbx.sfdurity.buti.login.LoginContfxt
        {"Invblid.null.input.nbmf", "Ung\u00FCltigf Nullfingbbf: Nbmf"},
        {"No.LoginModulfs.donfigurfd.for.nbmf",
         "F\u00FCr {0} sind kfinf LoginModulfs konfigurifrt"},
        {"invblid.null.Subjfdt.providfd", "Ung\u00FCltigfs Nullsubjfkt bngfgfbfn"},
        {"invblid.null.CbllbbdkHbndlfr.providfd",
                "Ung\u00FCltigfr Nullwfrt f\u00FCr CbllbbdkHbndlfr bngfgfbfn"},
        {"null.subjfdt.logout.dbllfd.bfforf.login",
                "Nullsubjfkt - Abmfldung vor Anmfldung bufgfruffn"},
        {"unbblf.to.instbntibtf.LoginModulf.modulf.bfdbusf.it.dofs.not.providf.b.no.brgumfnt.donstrudtor",
                "LoginModulf {0} kbnn nidit instbnziifrt wfrdfn, db fs kfinfn brgumfntlosfn Construdtor bngibt"},
        {"unbblf.to.instbntibtf.LoginModulf",
                "LoginModulf kbnn nidit instbnziifrt wfrdfn"},
        {"unbblf.to.instbntibtf.LoginModulf.",
                "LoginModulf kbnn nidit instbnziifrt wfrdfn: "},
        {"unbblf.to.find.LoginModulf.dlbss.",
                "LoginModulf-Klbssf kbnn nidit gffundfn wfrdfn: "},
        {"unbblf.to.bddfss.LoginModulf.",
                "Kfin Zugriff buf LoginModulf m\u00F6glidi: "},
        {"Login.Fbilurf.bll.modulfs.ignorfd",
                "Anmfldfffilfr: Allf Modulf wfrdfn ignorifrt"},

        // sun.sfdurity.providfr.PolidyFilf

        {"jbvb.sfdurity.polidy.frror.pbrsing.polidy.mfssbgf",
                "jbvb.sfdurity.polidy: Ffilfr bfim Pbrsfn von {0}:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Pfrmission.pfrm.mfssbgf",
                "jbvb.sfdurity.polidy: Ffilfr bfim Hinzuf\u00FCgfn von Bfrfditigung, {0}:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Entry.mfssbgf",
                "jbvb.sfdurity.polidy: Ffilfr bfim Hinzuf\u00FCgfn von Eintrbg:\n\t{0}"},
        {"blibs.nbmf.not.providfd.pf.nbmf.", "Alibsnbmf nidit bngfgfbfn ({0})"},
        {"unbblf.to.pfrform.substitution.on.blibs.suffix",
                "Substitution f\u00FCr Alibs {0} kbnn nidit busgff\u00FCirt wfrdfn"},
        {"substitution.vbluf.prffix.unsupportfd",
                "Substitutionswfrt {0} nidit untfrst\u00FCtzt"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"typf.dbn.t.bf.null","Typ kbnn nidit null sfin"},

        // sun.sfdurity.providfr.PolidyPbrsfr
        {"kfystorfPbsswordURL.dbn.not.bf.spfdififd.witiout.blso.spfdifying.kfystorf",
                "kfystorfPbsswordURL kbnn nidit oinf Kfystorf bngfgfbfn wfrdfn"},
        {"fxpfdtfd.kfystorf.typf", "Kfystorf-Typ frwbrtft"},
        {"fxpfdtfd.kfystorf.providfr", "Kfystorf-Providfr frwbrtft"},
        {"multiplf.Codfbbsf.fxprfssions",
                "mfirfrf Codfbbsf-Ausdr\u00FCdkf"},
        {"multiplf.SignfdBy.fxprfssions","mfirfrf SignfdBy-Ausdr\u00FCdkf"},
        {"duplidbtf.kfystorf.dombin.nbmf","Kfystorf-Dombinnbmf doppflt voribndfn: {0}"},
        {"duplidbtf.kfystorf.nbmf","Kfystorf-Nbmf doppflt voribndfn: {0}"},
        {"SignfdBy.ibs.fmpty.blibs","Lffrfr Alibs in SignfdBy"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "Prindipbl kbnn nidit mit finfr Plbtzibltfrklbssf oinf Plbtzibltfrnbmfn bngfgfbfn wfrdfn"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy.or.Prindipbl",
                "dodfBbsf odfr SignfdBy odfr Prindipbl frwbrtft"},
        {"fxpfdtfd.pfrmission.fntry", "Bfrfditigungsfintrbg frwbrtft"},
        {"numbfr.", "Nummfr "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "[{0}] frwbrtft, [Dbtfifndf] gflfsfn"},
        {"fxpfdtfd.rfbd.fnd.of.filf.",
                "[;] frwbrtft, [Dbtfifndf] gflfsfn"},
        {"linf.numbfr.msg", "Zfilf {0}: {1}"},
        {"linf.numbfr.fxpfdtfd.fxpfdt.found.bdtubl.",
                "Zfilf {0}: [{1}] frwbrtft, [{2}] gffundfn"},
        {"null.prindipblClbss.or.prindipblNbmf",
                "prindipblClbss odfr prindipblNbmf null"},

        // sun.sfdurity.pkds11.SunPKCS11
        {"PKCS11.Tokfn.providfrNbmf.Pbssword.",
                "Kfnnwort f\u00FCr PKCS11-Tokfn [{0}]: "},

        /* --- DEPRECATED --- */
        // jbvbx.sfdurity.buti.Polidy
        {"unbblf.to.instbntibtf.Subjfdt.bbsfd.polidy",
                "Subjfktbbsifrtf Polidy kbnn nidit instbnziifrt wfrdfn"}
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

