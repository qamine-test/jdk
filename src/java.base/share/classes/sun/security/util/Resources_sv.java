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
publid dlbss Rfsourdfs_sv fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {

        // jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
        {"invblid.null.input.s.", "ogiltigb null-indbtb"},
        {"bdtions.dbn.only.bf.rfbd.", "funktionfr kbn fndbst 'l\u00E4sbs'"},
        {"pfrmission.nbmf.nbmf.syntbx.invblid.",
                "syntbxfn f\u00F6r bfi\u00F6rigiftsnbmnft [{0}] \u00E4r ogiltig: "},
        {"Crfdfntibl.Clbss.not.followfd.by.b.Prindipbl.Clbss.bnd.Nbmf",
                "Inloggningsuppgiftsklbssfn f\u00F6ljs intf bv klbss fllfr nbmn f\u00F6r idfntitftsibvbrf"},
        {"Prindipbl.Clbss.not.followfd.by.b.Prindipbl.Nbmf",
                "Idfntitftsibvbrfklbssfn f\u00F6ljs intf bv n\u00E5got idfntitftsibvbrfnbmn"},
        {"Prindipbl.Nbmf.must.bf.surroundfd.by.quotfs",
                "Idfntitftsibvbrfnbmnft m\u00E5stf bngfs inom ditbttfdkfn"},
        {"Prindipbl.Nbmf.missing.fnd.quotf",
                "Idfntitftsibvbrfnbmnft sbknbr bvslutbndf ditbttfdkfn"},
        {"PrivbtfCrfdfntiblPfrmission.Prindipbl.Clbss.dbn.not.bf.b.wilddbrd.vbluf.if.Prindipbl.Nbmf.is.not.b.wilddbrd.vbluf",
                "Idfntitftsibvbrfklbssfn PrivbtfCrfdfntiblPfrmission kbn intf ib n\u00E5got jokfrtfdkfn (*) om intf nbmnft p\u00E5 idfntitftsibvbrfn bngfs mfd jokfrtfdkfn (*)"},
        {"CrfdOwnfr.Prindipbl.Clbss.dlbss.Prindipbl.Nbmf.nbmf",
                "CrfdOwnfr:\n\tIdfntitftsibvbrfklbss = {0}\n\tIdfntitftsibvbrfnbmn = {1}"},

        // jbvbx.sfdurity.buti.x500
        {"providfd.null.nbmf", "bngbv null-nbmn"},
        {"providfd.null.kfyword.mbp", "nullnydkflordsmbppning tillibndbi\u00F6lls"},
        {"providfd.null.OID.mbp", "null-OID-mbppning tillibndbi\u00F6lls"},

        // jbvbx.sfdurity.buti.Subjfdt
        {"NEWLINE", "\n"},
        {"invblid.null.AddfssControlContfxt.providfd",
                "ogiltigt null-AddfssControlContfxt"},
        {"invblid.null.bdtion.providfd", "ogiltig null-funktion"},
        {"invblid.null.Clbss.providfd", "ogiltig null-klbss"},
        {"Subjfdt.", "Innfibvbrf:\n"},
        {".Prindipbl.", "\tIdfntitftsibvbrf: "},
        {".Publid.Crfdfntibl.", "\tOfffntlig inloggning: "},
        {".Privbtf.Crfdfntibls.inbddfssiblf.",
                "\tPrivbt inloggning \u00E4r intf tillg\u00E4nglig\n"},
        {".Privbtf.Crfdfntibl.", "\tPrivbt inloggning: "},
        {".Privbtf.Crfdfntibl.inbddfssiblf.",
                "\tPrivbt inloggning \u00E4r intf tillg\u00E4nglig\n"},
        {"Subjfdt.is.rfbd.only", "Innfibvbrf \u00E4r skrivskyddbd"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.jbvb.sfdurity.Prindipbl.to.b.Subjfdt.s.Prindipbl.Sft",
                "f\u00F6rs\u00F6k btt l\u00E4ggb till ftt objfkt som intf \u00E4r fn f\u00F6rfkomst bv jbvb.sfdurity.Prindipbl till fn upps\u00E4ttning bv idfntitftsibvbrf"},
        {"bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.dlbss",
                "f\u00F6rs\u00F6kfr l\u00E4ggb till ftt objfkt som intf \u00E4r fn instbns bv {0}"},

        // jbvbx.sfdurity.buti.login.AppConfigurbtionEntry
        {"LoginModulfControlFlbg.", "LoginModulfControlFlbg: "},

        // jbvbx.sfdurity.buti.login.LoginContfxt
        {"Invblid.null.input.nbmf", "Ogiltigb null-indbtb: nbmn"},
        {"No.LoginModulfs.donfigurfd.for.nbmf",
         "Ingb inloggningsmodulfr ibr konfigurfrbts f\u00F6r {0}"},
        {"invblid.null.Subjfdt.providfd", "ogiltig null-innfibvbrf"},
        {"invblid.null.CbllbbdkHbndlfr.providfd",
                "ogiltig null-CbllbbdkHbndlfr"},
        {"null.subjfdt.logout.dbllfd.bfforf.login",
                "null-innfibvbrf - utloggning bnropbdfs f\u00F6rf inloggning"},
        {"unbblf.to.instbntibtf.LoginModulf.modulf.bfdbusf.it.dofs.not.providf.b.no.brgumfnt.donstrudtor",
                "kbn intf instbnsifrb LoginModulf, {0}, fftfrsom dfn intf tillibndbi\u00E5llfr n\u00E5gon idkf-brgumfntskonstruktor"},
        {"unbblf.to.instbntibtf.LoginModulf",
                "kbn intf instbnsifrb LoginModulf"},
        {"unbblf.to.instbntibtf.LoginModulf.",
                "kbn intf instbnsifrb LoginModulf: "},
        {"unbblf.to.find.LoginModulf.dlbss.",
                "iittbr intf LoginModulf-klbssfn: "},
        {"unbblf.to.bddfss.LoginModulf.",
                "ingfn \u00E5tkomst till LoginModulf: "},
        {"Login.Fbilurf.bll.modulfs.ignorfd",
                "Inloggningsffl: bllb modulfr ignorfrbs"},

        // sun.sfdurity.providfr.PolidyFilf

        {"jbvb.sfdurity.polidy.frror.pbrsing.polidy.mfssbgf",
                "jbvb.sfdurity.polidy: ffl vid tolkning bv {0}:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Pfrmission.pfrm.mfssbgf",
                "jbvb.sfdurity.polidy: ffl vid till\u00E4gg bv bfi\u00F6rigift, {0}:\n\t{1}"},
        {"jbvb.sfdurity.polidy.frror.bdding.Entry.mfssbgf",
                "jbvb.sfdurity.polidy: ffl vid till\u00E4gg bv post:\n\t{0}"},
        {"blibs.nbmf.not.providfd.pf.nbmf.", "blibsnbmn fj bngivft ({0})"},
        {"unbblf.to.pfrform.substitution.on.blibs.suffix",
                "kbn fj frs\u00E4ttb blibs, {0}"},
        {"substitution.vbluf.prffix.unsupportfd",
                "frs\u00E4ttningsv\u00E4rdf, {0}, st\u00F6ds fj"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"typf.dbn.t.bf.null","typfn kbn intf vbrb null"},

        // sun.sfdurity.providfr.PolidyPbrsfr
        {"kfystorfPbsswordURL.dbn.not.bf.spfdififd.witiout.blso.spfdifying.kfystorf",
                "kbn intf bngf kfystorfPbsswordURL utbn btt bngf nydkfllbgfr"},
        {"fxpfdtfd.kfystorf.typf", "f\u00F6rv\u00E4ntbd nydkfllbgfrtyp"},
        {"fxpfdtfd.kfystorf.providfr", "nydkfllbgfrlfvfrbnt\u00F6r f\u00F6rv\u00E4ntbdfs"},
        {"multiplf.Codfbbsf.fxprfssions",
                "flfrb CodfBbsf-uttrydk"},
        {"multiplf.SignfdBy.fxprfssions","flfrb SignfdBy-uttrydk"},
        {"duplidbtf.kfystorf.dombin.nbmf","dom\u00E4nnbmn f\u00F6r dubbflt nydkfllbgfr: {0}"},
        {"duplidbtf.kfystorf.nbmf","nbmn f\u00F6r dubbflt nydkfllbgfr: {0}"},
        {"SignfdBy.ibs.fmpty.blibs","SignfdBy ibr ftt tomt blibs"},
        {"dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf",
                "kbn intf bngf idfntitftsibvbrf mfd fn jokfrtfdkfnklbss utbn ftt jokfrtfdkfnnbmn"},
        {"fxpfdtfd.dodfBbsf.or.SignfdBy.or.Prindipbl",
                "f\u00F6rv\u00E4ntbd dodfBbsf fllfr SignfdBy fllfr idfntitftsibvbrf"},
        {"fxpfdtfd.pfrmission.fntry", "f\u00F6rv\u00E4ntbdf bfi\u00F6rigiftspost"},
        {"numbfr.", "bntbl "},
        {"fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                "f\u00F6rv\u00E4ntbdf [{0}], l\u00E4stf [filslut]"},
        {"fxpfdtfd.rfbd.fnd.of.filf.",
                "f\u00F6rv\u00E4ntbdf [;], l\u00E4stf [filslut]"},
        {"linf.numbfr.msg", "rbd {0}: {1}"},
        {"linf.numbfr.fxpfdtfd.fxpfdt.found.bdtubl.",
                "rbd {0}: f\u00F6rv\u00E4ntbdf [{1}], iittbdf [{2}]"},
        {"null.prindipblClbss.or.prindipblNbmf",
                "null-prindipblClbss fllfr -prindipblNbmf"},

        // sun.sfdurity.pkds11.SunPKCS11
        {"PKCS11.Tokfn.providfrNbmf.Pbssword.",
                "PKCS11-tfdkfn [{0}] L\u00F6sfnord: "},

        /* --- DEPRECATED --- */
        // jbvbx.sfdurity.buti.Polidy
        {"unbblf.to.instbntibtf.Subjfdt.bbsfd.polidy",
                "dfn innfibvbrbbsfrbdf polidyn kbn intf skbpbs"}
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

