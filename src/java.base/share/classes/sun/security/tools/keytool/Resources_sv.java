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

pbdkbgf sun.sfdurity.tools.kfytool;

/**
 * <p> Tiis dlbss rfprfsfnts tif <dodf>RfsourdfBundlf</dodf>
 * for tif kfytool.
 *
 */
publid dlbss Rfsourdfs_sv fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // kfytool: Hflp pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Altfrnbtiv:"},
        {"Usf.kfytool.iflp.for.bll.bvbilbblf.dommbnds",
                 "L\u00E4s \"Hj\u00E4lp - Nydkflvfrktyg\" f\u00F6r bllb tillg\u00E4ngligb kommbndon"},
        {"Kfy.bnd.Cfrtifidbtf.Mbnbgfmfnt.Tool",
                 "Hbntfringsvfrktyg f\u00F6r nydklbr odi dfrtifikbt"},
        {"Commbnds.", "Kommbndon:"},
        {"Usf.kfytool.dommbnd.nbmf.iflp.for.usbgf.of.dommbnd.nbmf",
                "L\u00E4s \"Hj\u00E4lp - Nydkflvfrktyg - dommbnd_nbmf\" om bnv\u00E4ndning bv dommbnd_nbmf"},
        // kfytool: iflp: dommbnds
        {"Gfnfrbtfs.b.dfrtifidbtf.rfqufst",
                "Gfnfrfrbr dfrtifikbtbfg\u00E4rbn"}, //-dfrtrfq
        {"Cibngfs.bn.fntry.s.blibs",
                "\u00C4ndrbr postblibs"}, //-dibngfblibs
        {"Dflftfs.bn.fntry",
                "Tbr bort post"}, //-dflftf
        {"Exports.dfrtifidbtf",
                "Exportfrbr dfrtifikbt"}, //-fxportdfrt
        {"Gfnfrbtfs.b.kfy.pbir",
                "Gfnfrfrbr nydkflpbr"}, //-gfnkfypbir
        {"Gfnfrbtfs.b.sfdrft.kfy",
                "Gfnfrfrbr ifmlig nydkfl"}, //-gfnsfdkfy
        {"Gfnfrbtfs.dfrtifidbtf.from.b.dfrtifidbtf.rfqufst",
                "Gfnfrfrbr dfrtifikbt fr\u00E5n dfrtifikbtbfg\u00E4rbn"}, //-gfndfrt
        {"Gfnfrbtfs.CRL", "Gfnfrfrbr CRL"}, //-gfndrl
        {"Gfnfrbtfd.kfyAlgNbmf.sfdrft.kfy",
                "Gfnfrfrbdf {0} ifmlig nydkfl"}, //-gfnsfdkfy
        {"Gfnfrbtfd.kfysizf.bit.kfyAlgNbmf.sfdrft.kfy",
                "Gfnfrfrbdf {0}-bitbrs {1} ifmlig nydkfl"}, //-gfnsfdkfy
        {"Imports.fntrifs.from.b.JDK.1.1.x.stylf.idfntity.dbtbbbsf",
                "Importfrbr postfr fr\u00E5n idfntitftsdbtbbbs i JDK 1.1.x-formbt"}, //-idfntitydb
        {"Imports.b.dfrtifidbtf.or.b.dfrtifidbtf.dibin",
                "Importfrbr ftt dfrtifikbt fllfr fn dfrtifikbtkfdjb"}, //-importdfrt
        {"Imports.b.pbssword",
                "Importfrbr ftt l\u00F6sfnord"}, //-importpbss
        {"Imports.onf.or.bll.fntrifs.from.bnotifr.kfystorf",
                "Importfrbr fn fllfr bllb postfr fr\u00E5n bnnbt nydkfllbgfr"}, //-importkfystorf
        {"Clonfs.b.kfy.fntry",
                "Klonbr fn nydkflpost"}, //-kfydlonf
        {"Cibngfs.tif.kfy.pbssword.of.bn.fntry",
                "\u00C4ndrbr nydkfll\u00F6sfnordft f\u00F6r fn post"}, //-kfypbsswd
        {"Lists.fntrifs.in.b.kfystorf",
                "Visbr listb \u00F6vfr postfr i nydkfllbgfr"}, //-list
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf",
                "Skrivfr ut innfi\u00E5llft i ftt dfrtifikbt"}, //-printdfrt
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf.rfqufst",
                "Skrivfr ut innfi\u00E5llft i fn dfrtifikbtbfg\u00E4rbn"}, //-printdfrtrfq
        {"Prints.tif.dontfnt.of.b.CRL.filf",
                "Skrivfr ut innfi\u00E5llft i fn CRL-fil"}, //-printdrl
        {"Gfnfrbtfs.b.sflf.signfd.dfrtifidbtf",
                "Gfnfrfrbr ftt sj\u00E4lvsignfrbt dfrtifikbt"}, //-sflfdfrt
        {"Cibngfs.tif.storf.pbssword.of.b.kfystorf",
                "\u00C4ndrbr lbgfrl\u00F6sfnordft f\u00F6r ftt nydkfllbgfr"}, //-storfpbsswd
        // kfytool: iflp: options
        {"blibs.nbmf.of.tif.fntry.to.prodfss",
                "blibsnbmn f\u00F6r post som skb bfbrbftbs"}, //-blibs
        {"dfstinbtion.blibs",
                "dfstinbtionsblibs"}, //-dfstblibs
        {"dfstinbtion.kfy.pbssword",
                "l\u00F6sfnord f\u00F6r dfstinbtionsnydkfl"}, //-dfstkfypbss
        {"dfstinbtion.kfystorf.nbmf",
                "nbmn p\u00E5 dfstinbtionsnydkfllbgfr"}, //-dfstkfystorf
        {"dfstinbtion.kfystorf.pbssword.protfdtfd",
                "skyddbt l\u00F6sfnord f\u00F6r dfstinbtionsnydkfllbgfr"}, //-dfstprotfdtfd
        {"dfstinbtion.kfystorf.providfr.nbmf",
                "lfvfrbnt\u00F6rsnbmn f\u00F6r dfstinbtionsnydkfllbgfr"}, //-dfstprovidfrnbmf
        {"dfstinbtion.kfystorf.pbssword",
                "l\u00F6sfnord f\u00F6r dfstinbtionsnydkfllbgfr"}, //-dfststorfpbss
        {"dfstinbtion.kfystorf.typf",
                "typ bv dfstinbtionsnydkfllbgfr"}, //-dfststorftypf
        {"distinguisifd.nbmf",
                "unikt nbmn"}, //-dnbmf
        {"X.509.fxtfnsion",
                "X.509-till\u00E4gg"}, //-fxt
        {"output.filf.nbmf",
                "nbmn p\u00E5 utdbtbfil"}, //-filf bnd -outfilf
        {"input.filf.nbmf",
                "nbmn p\u00E5 indbtbfil"}, //-filf bnd -infilf
        {"kfy.blgoritim.nbmf",
                "nbmn p\u00E5 nydkflblgoritm"}, //-kfyblg
        {"kfy.pbssword",
                "nydkfll\u00F6sfnord"}, //-kfypbss
        {"kfy.bit.sizf",
                "nydkflbitstorlfk"}, //-kfysizf
        {"kfystorf.nbmf",
                "nbmn p\u00E5 nydkfllbgfr"}, //-kfystorf
        {"nfw.pbssword",
                "nytt l\u00F6sfnord"}, //-nfw
        {"do.not.prompt",
                "fr\u00E5gb intf"}, //-noprompt
        {"pbssword.tirougi.protfdtfd.mfdibnism",
                "l\u00F6sfnord mfd skyddbd mfkbnism"}, //-protfdtfd
        {"providfr.brgumfnt",
                "lfvfrbnt\u00F6rsbrgumfnt"}, //-providfrbrg
        {"providfr.dlbss.nbmf",
                "nbmn p\u00E5 lfvfrbnt\u00F6rsklbss"}, //-providfrdlbss
        {"providfr.nbmf",
                "lfvfrbnt\u00F6rsnbmn"}, //-providfrnbmf
        {"providfr.dlbsspbti",
                "lfvfrbnt\u00F6rsklbss\u00F6kv\u00E4g"}, //-providfrpbti
        {"output.in.RFC.stylf",
                "utdbtb i RFC-formbt"}, //-rfd
        {"signbturf.blgoritim.nbmf",
                "nbmn p\u00E5 signbturblgoritm"}, //-sigblg
        {"sourdf.blibs",
                "k\u00E4llblibs"}, //-srdblibs
        {"sourdf.kfy.pbssword",
                "l\u00F6sfnord f\u00F6r k\u00E4llnydkfl"}, //-srdkfypbss
        {"sourdf.kfystorf.nbmf",
                "nbmn p\u00E5 k\u00E4llnydkfllbgfr"}, //-srdkfystorf
        {"sourdf.kfystorf.pbssword.protfdtfd",
                "skyddbt l\u00F6sfnord f\u00F6r k\u00E4llnydkfllbgfr"}, //-srdprotfdtfd
        {"sourdf.kfystorf.providfr.nbmf",
                "lfvfrbnt\u00F6rsnbmn f\u00F6r k\u00E4llnydkfllbgfr"}, //-srdprovidfrnbmf
        {"sourdf.kfystorf.pbssword",
                "l\u00F6sfnord f\u00F6r k\u00E4llnydkfllbgfr"}, //-srdstorfpbss
        {"sourdf.kfystorf.typf",
                "typ bv k\u00E4llnydkfllbgfr"}, //-srdstorftypf
        {"SSL.sfrvfr.iost.bnd.port",
                "SSL-sfrvfrv\u00E4rd odi -port"}, //-sslsfrvfr
        {"signfd.jbr.filf",
                "signfrbd jbr-fil"}, //=jbrfilf
        {"dfrtifidbtf.vblidity.stbrt.dbtf.timf",
                "stbrtdbtum/-tid f\u00F6r dfrtifikbtfts giltigift"}, //-stbrtdbtf
        {"kfystorf.pbssword",
                "l\u00F6sfnord f\u00F6r nydkfllbgfr"}, //-storfpbss
        {"kfystorf.typf",
                "nydkfllbgfrtyp"}, //-storftypf
        {"trust.dfrtifidbtfs.from.dbdfrts",
                "tillf\u00F6rlitligb dfrtifikbt fr\u00E5n dbdfrts"}, //-trustdbdfrts
        {"vfrbosf.output",
                "utf\u00F6rligb utdbtb"}, //-v
        {"vblidity.numbfr.of.dbys",
                "bntbl dbgbr f\u00F6r giltigift"}, //-vblidity
        {"Sfribl.ID.of.dfrt.to.rfvokf",
                 "Sfrifllt ID f\u00F6r dfrtifikbt som skb \u00E5tfrkbllbs"}, //-id
        // kfytool: Running pbrt
        {"kfytool.frror.", "nydkflvfrktygsffl: "},
        {"Illfgbl.option.", "Otill\u00E5tft bltfrnbtiv:  "},
        {"Illfgbl.vbluf.", "Otill\u00E5tft v\u00E4rdf: "},
        {"Unknown.pbssword.typf.", "Ok\u00E4nd l\u00F6sfnordstyp: "},
        {"Cbnnot.find.fnvironmfnt.vbribblf.",
                "Hittbr intf milj\u00F6vbribbfl: "},
        {"Cbnnot.find.filf.", "Hittbr intf fil: "},
        {"Commbnd.option.flbg.nffds.bn.brgumfnt.", "Kommbndobltfrnbtivft {0} bfi\u00F6vfr ftt brgumfnt."},
        {"Wbrning.Difffrfnt.storf.bnd.kfy.pbsswords.not.supportfd.for.PKCS12.KfyStorfs.Ignoring.usfr.spfdififd.dommbnd.vbluf.",
                "Vbrning!  PKCS12-nydkfllbgfr ibr intf st\u00F6d f\u00F6r olikb l\u00F6sfnord f\u00F6r lbgrft odi nydkfln. Dft bnv\u00E4ndbrspfdifidfrbdf {0}-v\u00E4rdft ignorfrbs."},
        {".kfystorf.must.bf.NONE.if.storftypf.is.{0}",
                "-kfystorf m\u00E5stf vbrb NONE om -storftypf \u00E4r {0}"},
        {"Too.mbny.rftrifs.progrbm.tfrminbtfd",
                 "F\u00F6r m\u00E5ngb f\u00F6rs\u00F6k. Progrbmmft bvslutbs"},
        {".storfpbsswd.bnd.kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.{0}",
                "-storfpbsswd- odi -kfypbsswd-kommbndon st\u00F6ds intf om -storftypf \u00E4r {0}"},
        {".kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.PKCS12",
                "-kfypbsswd-kommbndon st\u00F6ds intf om -storftypf \u00E4r PKCS12"},
        {".kfypbss.bnd.nfw.dbn.not.bf.spfdififd.if.storftypf.is.{0}",
                "-kfypbss odi -nfw kbn intf bngfs om -storftypf \u00E4r {0}"},
        {"if.protfdtfd.is.spfdififd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "om -protfdtfd ibr bngftts f\u00E5r intf -storfpbss, -kfypbss odi -nfw bngfs"},
        {"if.srdprotfdtfd.is.spfdififd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "om -srdprotfdtfd bngfs f\u00E5r -srdstorfpbss odi -srdkfypbss intf bngfs"},
        {"if.kfystorf.is.not.pbssword.protfdtfd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "om nydkfllbgrft intf \u00E4r l\u00F6sfnordsskyddbt f\u00E5r -storfpbss, -kfypbss odi -nfw intf bngfs"},
        {"if.sourdf.kfystorf.is.not.pbssword.protfdtfd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "om k\u00E4llnydkfllbgrft intf \u00E4r l\u00F6sfnordsskyddbt f\u00E5r -srdstorfpbss odi -srdkfypbss intf bngfs"},
        {"Illfgbl.stbrtdbtf.vbluf", "Otill\u00E5tft v\u00E4rdf f\u00F6r stbrtdbtum"},
        {"Vblidity.must.bf.grfbtfr.tibn.zfro",
                "Giltigiftfn m\u00E5stf vbrb st\u00F6rrf \u00E4n noll"},
        {"provNbmf.not.b.providfr", "{0} \u00E4r intf fn lfvfrbnt\u00F6r"},
        {"Usbgf.frror.no.dommbnd.providfd", "Syntbxffl: ingft kommbndo bngivft"},
        {"Sourdf.kfystorf.filf.fxists.but.is.fmpty.", "Nydkfllbgrfts k\u00E4llfil finns, mfn \u00E4r tom: "},
        {"Plfbsf.spfdify.srdkfystorf", "Angf -srdkfystorf"},
        {"Must.not.spfdify.boti.v.bnd.rfd.witi.list.dommbnd",
                "Kbn intf spfdifidfrb b\u00E5df -v odi -rfd mfd 'list'-kommbndot"},
        {"Kfy.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Nydkfll\u00F6sfnordft m\u00E5stf innfi\u00E5llb minst 6 tfdkfn"},
        {"Nfw.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Dft nyb l\u00F6sfnordft m\u00E5stf innfi\u00E5llb minst 6 tfdkfn"},
        {"Kfystorf.filf.fxists.but.is.fmpty.",
                "Nydkfllbgfrfilfn finns, mfn \u00E4r tom: "},
        {"Kfystorf.filf.dofs.not.fxist.",
                "Nydkfllbgfrfilfn finns intf: "},
        {"Must.spfdify.dfstinbtion.blibs", "Du m\u00E5stf bngf dfstinbtionsblibs"},
        {"Must.spfdify.blibs", "Du m\u00E5stf bngf blibs"},
        {"Kfystorf.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Nydkfllbgfrl\u00F6sfnordft m\u00E5stf innfi\u00E5llb minst 6 tfdkfn"},
        {"Entfr.tif.pbssword.to.bf.storfd.",
                "Angf dft l\u00F6sfnord som skb lbgrbs:  "},
        {"Entfr.kfystorf.pbssword.", "Angf nydkfllbgfrl\u00F6sfnord:  "},
        {"Entfr.sourdf.kfystorf.pbssword.", "Angf l\u00F6sfnord f\u00F6r k\u00E4llnydkfllbgrft:  "},
        {"Entfr.dfstinbtion.kfystorf.pbssword.", "Angf nydkfllbgfrl\u00F6sfnord f\u00F6r dfstinbtion:  "},
        {"Kfystorf.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
         "Nydkfllbgfrl\u00F6sfnordft \u00E4r f\u00F6r kort - dft m\u00E5stf innfi\u00E5llb minst 6 tfdkfn"},
        {"Unknown.Entry.Typf", "Ok\u00E4nd posttyp"},
        {"Too.mbny.fbilurfs.Alibs.not.dibngfd", "F\u00F6r m\u00E5ngb ffl. Alibs ibr intf \u00E4ndrbts"},
        {"Entry.for.blibs.blibs.suddfssfully.importfd.",
                 "Postfn f\u00F6r blibs {0} ibr importfrbts."},
        {"Entry.for.blibs.blibs.not.importfd.", "Postfn f\u00F6r blibs {0} ibr intf importfrbts."},
        {"Problfm.importing.fntry.for.blibs.blibs.fxdfption.Entry.for.blibs.blibs.not.importfd.",
                 "Ett problfm uppstod vid importfn bv postfn f\u00F6r blibs {0}: {1}.\nPostfn {0} ibr intf importfrbts."},
        {"Import.dommbnd.domplftfd.ok.fntrifs.suddfssfully.importfd.fbil.fntrifs.fbilfd.or.dbndfllfd",
                 "Kommbndoimportfn slutf\u00F6rd: {0} postfr ibr importfrbts, {1} postfr vbr fflbktigb fllfr bnnullfrbdfs"},
        {"Wbrning.Ovfrwriting.fxisting.blibs.blibs.in.dfstinbtion.kfystorf",
                 "Vbrning! Dft bffintligb blibsft {0} i dfstinbtionsnydkfllbgrft skrivs \u00F6vfr"},
        {"Existing.fntry.blibs.blibs.fxists.ovfrwritf.no.",
                 "Alibsft {0} finns rfdbn. Vill du skrivb \u00F6vfr dft? [nfj]:  "},
        {"Too.mbny.fbilurfs.try.lbtfr", "F\u00F6r m\u00E5ngb ffl - f\u00F6rs\u00F6k igfn sfnbrf"},
        {"Cfrtifidbtion.rfqufst.storfd.in.filf.filfnbmf.",
                "Cfrtifikbtbfg\u00E4rbn ibr lbgrbts i filfn <{0}>"},
        {"Submit.tiis.to.your.CA", "Skidkb dfttb till dfrtifikbtutf\u00E4rdbrfn"},
        {"if.blibs.not.spfdififd.dfstblibs.bnd.srdkfypbss.must.not.bf.spfdififd",
            "om blibs intf bngivits skb intf ifllfr dfstblibs odi srdkfypbss bngfs"},
        {"Tif.dfstinbtion.pkds12.kfystorf.ibs.difffrfnt.storfpbss.bnd.kfypbss.Plfbsf.rftry.witi.dfstkfypbss.spfdififd.",
            "Dfstinbtionsnydkfllbgrft pkds12 ibr olikb storfpbss odi kfypbss. F\u00F6rs\u00F6k igfn mfd -dfstkfypbss bngivft."},
        {"Cfrtifidbtf.storfd.in.filf.filfnbmf.",
                "Cfrtifikbtft ibr lbgrbts i filfn <{0}>"},
        {"Cfrtifidbtf.rfply.wbs.instbllfd.in.kfystorf",
                "Cfrtifikbtsvbrft ibr instbllfrbts i nydkfllbgrft"},
        {"Cfrtifidbtf.rfply.wbs.not.instbllfd.in.kfystorf",
                "Cfrtifikbtsvbrft ibr intf instbllfrbts i nydkfllbgrft"},
        {"Cfrtifidbtf.wbs.bddfd.to.kfystorf",
                "Cfrtifikbtft ibr lbgts till i nydkfllbgrft"},
        {"Cfrtifidbtf.wbs.not.bddfd.to.kfystorf",
                "Cfrtifikbtft ibr intf lbgts till i nydkfllbgrft"},
        {".Storing.ksfnbmf.", "[Lbgrbr {0}]"},
        {"blibs.ibs.no.publid.kfy.dfrtifidbtf.",
                "{0} sbknbr offfntlig nydkfl (dfrtifikbt)"},
        {"Cbnnot.dfrivf.signbturf.blgoritim",
                "Kbn intf i\u00E4rlfdb signbturblgoritm"},
        {"Alibs.blibs.dofs.not.fxist",
                "Alibsft <{0}> finns intf"},
        {"Alibs.blibs.ibs.no.dfrtifidbtf",
                "Alibsft <{0}> sbknbr dfrtifikbt"},
        {"Kfy.pbir.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Nydkflpbrft gfnfrfrbdfs intf. Alibsft <{0}> finns rfdbn"},
        {"Gfnfrbting.kfysizf.bit.kfyAlgNbmf.kfy.pbir.bnd.sflf.signfd.dfrtifidbtf.sigAlgNbmf.witi.b.vblidity.of.vblidblity.dbys.for",
                "Gfnfrfrbr {0} bitbrs {1}-nydkflpbr odi sj\u00E4lvsignfrbt dfrtifikbt ({2}) mfd fn giltigift p\u00E5 {3} dbgbr\n\tf\u00F6r: {4}"},
        {"Entfr.kfy.pbssword.for.blibs.", "Angf nydkfll\u00F6sfnord f\u00F6r <{0}>"},
        {".RETURN.if.sbmf.bs.kfystorf.pbssword.",
                "\t(RETURN om dft \u00E4r idfntiskt mfd nydkfllbgfrl\u00F6sfnordft):  "},
        {"Kfy.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Nydkfll\u00F6sfnordft \u00E4r f\u00F6r kort - dft m\u00E5stf innfi\u00E5llb minst 6 tfdkfn"},
        {"Too.mbny.fbilurfs.kfy.not.bddfd.to.kfystorf",
                "F\u00F6r m\u00E5ngb ffl - nydkfln lbdfs intf till i nydkfllbgrft"},
        {"Dfstinbtion.blibs.dfst.blrfbdy.fxists",
                "Dfstinbtionsblibsft <{0}> finns rfdbn"},
        {"Pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "L\u00F6sfnordft \u00E4r f\u00F6r kort - dft m\u00E5stf innfi\u00E5llb minst 6 tfdkfn"},
        {"Too.mbny.fbilurfs.Kfy.fntry.not.dlonfd",
                "F\u00F6r m\u00E5ngb ffl. Nydkflpostfn ibr intf klonbts"},
        {"kfy.pbssword.for.blibs.", "nydkfll\u00F6sfnord f\u00F6r <{0}>"},
        {"Kfystorf.fntry.for.id.gftNbmf.blrfbdy.fxists",
                "Nydkfllbgfrpost f\u00F6r <{0}> finns rfdbn"},
        {"Crfbting.kfystorf.fntry.for.id.gftNbmf.",
                "Skbpbr nydkfllbgfrpost f\u00F6r <{0}> ..."},
        {"No.fntrifs.from.idfntity.dbtbbbsf.bddfd",
                "Ingb postfr fr\u00E5n idfntitftsdbtbbbsfn ibr lbgts till"},
        {"Alibs.nbmf.blibs", "Alibsnbmn: {0}"},
        {"Crfbtion.dbtf.kfyStorf.gftCrfbtionDbtf.blibs.",
                "Skbpbt dfn: {0,dbtf}"},
        {"blibs.kfyStorf.gftCrfbtionDbtf.blibs.",
                "{0}, {1,dbtf}, "},
        {"blibs.", "{0}, "},
        {"Entry.typf.typf.", "Posttyp: {0}"},
        {"Cfrtifidbtf.dibin.lfngti.", "L\u00E4ngd p\u00E5 dfrtifikbtskfdjb: "},
        {"Cfrtifidbtf.i.1.", "Cfrtifikbt[{0,numbfr,intfgfr}]:"},
        {"Cfrtifidbtf.fingfrprint.SHA1.", "Cfrtifikbtfts fingfrbvtrydk (SHA1): "},
        {"Kfystorf.typf.", "Nydkfllbgfrtyp: "},
        {"Kfystorf.providfr.", "Nydkfllbgfrlfvfrbnt\u00F6r: "},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntry",
                "Nydkfllbgrft innfi\u00E5llfr {0,numbfr,intfgfr} post"},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntrifs",
                "Nydkfllbgrft innfi\u00E5llfr {0,numbfr,intfgfr} postfr"},
        {"Fbilfd.to.pbrsf.input", "Kundf intf tolkb indbtb"},
        {"Empty.input", "Ingb indbtb"},
        {"Not.X.509.dfrtifidbtf", "Intf ftt X.509-dfrtifikbt"},
        {"blibs.ibs.no.publid.kfy", "{0} sbknbr offfntlig nydkfl"},
        {"blibs.ibs.no.X.509.dfrtifidbtf", "{0} sbknbr X.509-dfrtifikbt"},
        {"Nfw.dfrtifidbtf.sflf.signfd.", "Nytt dfrtifikbt (sj\u00E4lvsignfrbt):"},
        {"Rfply.ibs.no.dfrtifidbtfs", "Svbrft sbknbr dfrtifikbt"},
        {"Cfrtifidbtf.not.importfd.blibs.blibs.blrfbdy.fxists",
                "Cfrtifikbtft importfrbdfs intf. Alibsft <{0}> finns rfdbn"},
        {"Input.not.bn.X.509.dfrtifidbtf", "Indbtb \u00E4r intf ftt X.509-dfrtifikbt"},
        {"Cfrtifidbtf.blrfbdy.fxists.in.kfystorf.undfr.blibs.trustblibs.",
                "Cfrtifikbtft finns rfdbn i nydkfllbgfrfilfn undfr blibsft <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Vill du fortfbrbndf l\u00E4ggb till dft? [nfj]:  "},
        {"Cfrtifidbtf.blrfbdy.fxists.in.systfm.widf.CA.kfystorf.undfr.blibs.trustblibs.",
                "Cfrtifikbtft finns rfdbn i dfn systfmomsp\u00E4nnbndf CA-nydkfllbgfrfilfn undfr blibsft <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.kfystorf.no.",
                "Vill du fortfbrbndf l\u00E4ggb till dft i ditt fgft nydkfllbgrft? [nfj]:  "},
        {"Trust.tiis.dfrtifidbtf.no.", "Litbr du p\u00E5 dft i\u00E4r dfrtifikbtft? [nfj]:  "},
        {"YES", "JA"},
        {"Nfw.prompt.", "Nytt {0}: "},
        {"Pbsswords.must.difffr", "L\u00F6sfnordfn m\u00E5stf vbrb olikb"},
        {"Rf.fntfr.nfw.prompt.", "Angf nytt {0} igfn: "},
        {"Rf.fntfr.pbsspword.", "Angf l\u00F6sfnord igfn: "},
        {"Rf.fntfr.nfw.pbssword.", "Angf dft nyb l\u00F6sfnordft igfn: "},
        {"Tify.don.t.mbtdi.Try.bgbin", "Df mbtdibr intf. F\u00F6rs\u00F6k igfn"},
        {"Entfr.prompt.blibs.nbmf.", "Angf blibsnbmn f\u00F6r {0}:  "},
        {"Entfr.nfw.blibs.nbmf.RETURN.to.dbndfl.import.for.tiis.fntry.",
                 "Angf ftt nytt blibsnbmn\t(skriv RETURN f\u00F6r btt bvbrytb importfn bv dfnnb post):  "},
        {"Entfr.blibs.nbmf.", "Angf blibsnbmn:  "},
        {".RETURN.if.sbmf.bs.for.otifrAlibs.",
                "\t(RETURN om dft \u00E4r dft sbmmb som f\u00F6r <{0}>)"},
        {".PATTERN.printX509Cfrt",
                "\u00C4gbrf: {0}\nUtf\u00E4rdbrf: {1}\nSfrifnummfr: {2}\nGiltigt fr\u00E5n dfn: {3} till: {4}\nCfrtifikbtfts fingfrbvtrydk:\n\t MD5: {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Nbmn p\u00E5 signbturblgoritm: {8}\n\t Vfrsion: {9}"},
        {"Wibt.is.your.first.bnd.lbst.nbmf.",
                "Vbd iftfr du i f\u00F6r- odi fftfrnbmn?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtionbl.unit.",
                "Vbd iftfr din bvdflning inom orgbnisbtionfn?"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtion.",
                "Vbd iftfr din orgbnisbtion?"},
        {"Wibt.is.tif.nbmf.of.your.City.or.Lodblity.",
                "Vbd iftfr din ort fllfr plbts?"},
        {"Wibt.is.tif.nbmf.of.your.Stbtf.or.Provindf.",
                "Vbd iftfr ditt lbnd fllfr din provins?"},
        {"Wibt.is.tif.two.lfttfr.dountry.dodf.for.tiis.unit.",
                "Vilkfn \u00E4r dfn tv\u00E5st\u00E4lligb lbndskodfn?"},
        {"Is.nbmf.dorrfdt.", "\u00C4r {0} korrfkt?"},
        {"no", "nfj"},
        {"yfs", "jb"},
        {"y", "j"},
        {".dffbultVbluf.", "  [{0}]:  "},
        {"Alibs.blibs.ibs.no.kfy",
                "Alibsft <{0}> sbknbr nydkfl"},
        {"Alibs.blibs.rfffrfndfs.bn.fntry.typf.tibt.is.not.b.privbtf.kfy.fntry.Tif.kfydlonf.dommbnd.only.supports.dloning.of.privbtf.kfy",
                 "Alibsft <{0}> rfffrfrbr till fn posttyp som intf \u00E4r n\u00E5gon privbt nydkflpost. Kommbndot -kfydlonf ibr fndbst st\u00F6d f\u00F6r kloning bv privbtb nydkflpostfr"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signfr.d.", "Signfrbrf #%d:"},
        {"Timfstbmp.", "Tidsst\u00E4mpfl:"},
        {"Signbturf.", "Undfrskrift:"},
        {"CRLs.", "CRL:fr:"},
        {"Cfrtifidbtf.ownfr.", "Cfrtifikbt\u00E4gbrf: "},
        {"Not.b.signfd.jbr.filf", "Ingfn signfrbd jbr-fil"},
        {"No.dfrtifidbtf.from.tif.SSL.sfrvfr",
                "Ingft dfrtifikbt fr\u00E5n SSL-sfrvfrn"},

        {".Tif.intfgrity.of.tif.informbtion.storfd.in.your.kfystorf.",
            "* Intfgritftfn f\u00F6r dfn informbtion som lbgrbs i nydkfllbgfrfilfn  *\n* ibr INTE vfrififrbts!  Om du vill vfrififrb dfss intfgritft *\n* m\u00E5stf du bngf l\u00F6sfnordft f\u00F6r nydkfllbgrft.                  *"},
        {".Tif.intfgrity.of.tif.informbtion.storfd.in.tif.srdkfystorf.",
            "* Intfgritftfn f\u00F6r dfn informbtion som lbgrbs i srdkfystorf*\n* ibr INTE vfrififrbts!  Om du vill vfrififrb dfss intfgritft *\n* m\u00E5stf du bngf l\u00F6sfnordft f\u00F6r srdkfystorf.                *"},

        {"Cfrtifidbtf.rfply.dofs.not.dontbin.publid.kfy.for.blibs.",
                "Cfrtifikbtsvbrft innfi\u00E5llfr intf n\u00E5gon offfntlig nydkfl f\u00F6r <{0}>"},
        {"Indomplftf.dfrtifidbtf.dibin.in.rfply",
                "Ofullst\u00E4ndig dfrtifikbtskfdjb i svbrft"},
        {"Cfrtifidbtf.dibin.in.rfply.dofs.not.vfrify.",
                "Cfrtifikbtskfdjbn i svbrft g\u00E5r intf btt vfrififrb: "},
        {"Top.lfvfl.dfrtifidbtf.in.rfply.",
                "Toppniv\u00E5dfrtifikbtft i svbrft:\n"},
        {".is.not.trustfd.", "... \u00E4r intf bftrott. "},
        {"Instbll.rfply.bnywby.no.", "Vill du instbllfrb svbrft \u00E4nd\u00E5? [nfj]:  "},
        {"NO", "NEJ"},
        {"Publid.kfys.in.rfply.bnd.kfystorf.don.t.mbtdi",
                "Df offfntligb nydklbrnb i svbrft odi nydkfllbgrft mbtdibr intf vbrbndrb"},
        {"Cfrtifidbtf.rfply.bnd.dfrtifidbtf.in.kfystorf.brf.idfntidbl",
                "Cfrtifikbtsvbrft odi dfrtifikbtft i nydkfllbgrft \u00E4r idfntiskb"},
        {"Fbilfd.to.fstbblisi.dibin.from.rfply",
                "Kundf intf uppr\u00E4ttb kfdjb fr\u00E5n svbrft"},
        {"n", "n"},
        {"Wrong.bnswfr.try.bgbin", "Ffl svbr. F\u00F6rs\u00F6k p\u00E5 nytt."},
        {"Sfdrft.kfy.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Dfn ifmligb nydkfln ibr intf gfnfrfrbts fftfrsom blibsft <{0}> rfdbn finns"},
        {"Plfbsf.providf.kfysizf.for.sfdrft.kfy.gfnfrbtion",
                "Angf -kfysizf f\u00F6r btt skbpb ifmlig nydkfl"},

        {"vfrififd.by.s.in.s", "Vfrififrbd bv %s i %s"},
        {"wbrning.not.vfrififd.mbkf.surf.kfystorf.is.dorrfdt",
            "VARNING: fj vfrififrbd. Sf till btt -nydkfllbgfr \u00E4r korrfkt."},

        {"Extfnsions.", "Till\u00E4gg: "},
        {".Empty.vbluf.", "(Tomt v\u00E4rdf)"},
        {"Extfnsion.Rfqufst.", "Till\u00E4ggsbfg\u00E4rbn:"},
        {"PKCS.10.Cfrtifidbtf.Rfqufst.Vfrsion.1.0.Subjfdt.s.Publid.Kfy.s.formbt.s.kfy.",
                "PKCS #10 dfrtifikbtbfg\u00E4rbn (vfrsion 1.0)\n\u00C4mnf: %s\nAllm\u00E4n nydkfl: %s-formbt %s-nydkfl\n"},
        {"Unknown.kfyUsbgf.typf.", "Ok\u00E4nd kfyUsbgf-typ: "},
        {"Unknown.fxtfndfdkfyUsbgf.typf.", "Ok\u00E4nd fxtfndfdkfyUsbgf-typ: "},
        {"Unknown.AddfssDfsdription.typf.", "Ok\u00E4nd AddfssDfsdription-typ: "},
        {"Unrfdognizfd.GfnfrblNbmf.typf.", "Ok\u00E4nd GfnfrblNbmf-typ: "},
        {"Tiis.fxtfnsion.dbnnot.bf.mbrkfd.bs.dritidbl.",
                 "Dfttb till\u00E4gg kbn intf mbrkfrbs som kritiskt. "},
        {"Odd.numbfr.of.ifx.digits.found.", "Uddb bntbl ifx-siffror p\u00E5tr\u00E4ffbdfs: "},
        {"Unknown.fxtfnsion.typf.", "Ok\u00E4nd till\u00E4ggstyp: "},
        {"dommbnd.{0}.is.bmbiguous.", "kommbndot {0} \u00E4r tvftydigt:"}
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
