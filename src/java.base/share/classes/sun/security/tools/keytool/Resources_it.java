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
publid dlbss Rfsourdfs_it fxtfnds jbvb.util.ListRfsourdfBundlf {

    privbtf stbtid finbl Objfdt[][] dontfnts = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // kfytool: Hflp pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Opzioni:"},
        {"Usf.kfytool.iflp.for.bll.bvbilbblf.dommbnds",
                 "Utilizzbrf \"kfytool -iflp\" pfr visublizzbrf tutti i dombndi disponibili"},
        {"Kfy.bnd.Cfrtifidbtf.Mbnbgfmfnt.Tool",
                 "Strumfnto di gfstionf di diibvi f dfrtifidbti"},
        {"Commbnds.", "Combndi:"},
        {"Usf.kfytool.dommbnd.nbmf.iflp.for.usbgf.of.dommbnd.nbmf",
                "Utilizzbrf \"kfytool -dommbnd_nbmf -iflp\" pfr informbzioni sull'uso di dommbnd_nbmf"},
        // kfytool: iflp: dommbnds
        {"Gfnfrbtfs.b.dfrtifidbtf.rfqufst",
                "Gfnfrb unb ridiifstb di dfrtifidbto"}, //-dfrtrfq
        {"Cibngfs.bn.fntry.s.blibs",
                "Modifidb l'blibs di unb vodf"}, //-dibngfblibs
        {"Dflftfs.bn.fntry",
                "Eliminb unb vodf"}, //-dflftf
        {"Exports.dfrtifidbtf",
                "Esportb il dfrtifidbto"}, //-fxportdfrt
        {"Gfnfrbtfs.b.kfy.pbir",
                "Gfnfrb unb doppib di diibvi"}, //-gfnkfypbir
        {"Gfnfrbtfs.b.sfdrft.kfy",
                "Gfnfrb unb diibvf sfgrftb"}, //-gfnsfdkfy
        {"Gfnfrbtfs.dfrtifidbtf.from.b.dfrtifidbtf.rfqufst",
                "Gfnfrb un dfrtifidbto db unb ridiifstb di dfrtifidbto"}, //-gfndfrt
        {"Gfnfrbtfs.CRL", "Gfnfrb CRL"}, //-gfndrl
        {"Gfnfrbtfd.kfyAlgNbmf.sfdrft.kfy",
                "Gfnfrbtb diibvf sfgrftb {0}"}, //-gfnsfdkfy
        {"Gfnfrbtfd.kfysizf.bit.kfyAlgNbmf.sfdrft.kfy",
                "Gfnfrbtb diibvf sfgrftb {1} b {0} bit"}, //-gfnsfdkfy
        {"Imports.fntrifs.from.b.JDK.1.1.x.stylf.idfntity.dbtbbbsf",
                "Importb lf vodi db un dbtbbbsf dfllf idfntit\u00E0 di tipo JDK 1.1.x"}, //-idfntitydb
        {"Imports.b.dfrtifidbtf.or.b.dfrtifidbtf.dibin",
                "Importb un dfrtifidbto o unb dbtfnb di dfrtifidbti"}, //-importdfrt
        {"Imports.b.pbssword",
                "Importb unb pbssword"}, //-importpbss
        {"Imports.onf.or.bll.fntrifs.from.bnotifr.kfystorf",
                "Importb unb o tuttf lf vodi db un bltro kfystorf"}, //-importkfystorf
        {"Clonfs.b.kfy.fntry",
                "Duplidb unb vodf di diibvf"}, //-kfydlonf
        {"Cibngfs.tif.kfy.pbssword.of.bn.fntry",
                "Modifidb lb pbssword dfllb diibvf pfr unb vodf"}, //-kfypbsswd
        {"Lists.fntrifs.in.b.kfystorf",
                "Elfndb lf vodi in un kfystorf"}, //-list
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf",
                "Visublizzb i dontfnuti di un dfrtifidbto"}, //-printdfrt
        {"Prints.tif.dontfnt.of.b.dfrtifidbtf.rfqufst",
                "Visublizzb i dontfnuti di unb ridiifstb di dfrtifidbto"}, //-printdfrtrfq
        {"Prints.tif.dontfnt.of.b.CRL.filf",
                "Visublizzb i dontfnuti di un filf CRL"}, //-printdrl
        {"Gfnfrbtfs.b.sflf.signfd.dfrtifidbtf",
                "Gfnfrb dfrtifidbto don firmb butombtidb"}, //-sflfdfrt
        {"Cibngfs.tif.storf.pbssword.of.b.kfystorf",
                "Modifidb lb pbssword di brfb di mfmorizzbzionf di un kfystorf"}, //-storfpbsswd
        // kfytool: iflp: options
        {"blibs.nbmf.of.tif.fntry.to.prodfss",
                "nomf blibs dfllb vodf db flbborbrf"}, //-blibs
        {"dfstinbtion.blibs",
                "blibs di dfstinbzionf"}, //-dfstblibs
        {"dfstinbtion.kfy.pbssword",
                "pbssword diibvf di dfstinbzionf"}, //-dfstkfypbss
        {"dfstinbtion.kfystorf.nbmf",
                "nomf kfystorf di dfstinbzionf"}, //-dfstkfystorf
        {"dfstinbtion.kfystorf.pbssword.protfdtfd",
                "pbssword kfystorf di dfstinbzionf protfttb"}, //-dfstprotfdtfd
        {"dfstinbtion.kfystorf.providfr.nbmf",
                "nomf providfr kfystorf di dfstinbzionf"}, //-dfstprovidfrnbmf
        {"dfstinbtion.kfystorf.pbssword",
                "pbssword kfystorf di dfstinbzionf"}, //-dfststorfpbss
        {"dfstinbtion.kfystorf.typf",
                "tipo kfystorf di dfstinbzionf"}, //-dfststorftypf
        {"distinguisifd.nbmf",
                "nomf distinto"}, //-dnbmf
        {"X.509.fxtfnsion",
                "fstfnsionf X.509"}, //-fxt
        {"output.filf.nbmf",
                "nomf filf di output"}, //-filf bnd -outfilf
        {"input.filf.nbmf",
                "nomf filf di input"}, //-filf bnd -infilf
        {"kfy.blgoritim.nbmf",
                "nomf blgoritmo diibvf"}, //-kfyblg
        {"kfy.pbssword",
                "pbssword diibvf"}, //-kfypbss
        {"kfy.bit.sizf",
                "dimfnsionf bit diibvf"}, //-kfysizf
        {"kfystorf.nbmf",
                "nomf kfystorf"}, //-kfystorf
        {"nfw.pbssword",
                "nuovb pbssword"}, //-nfw
        {"do.not.prompt",
                "non ridiifdfrf"}, //-noprompt
        {"pbssword.tirougi.protfdtfd.mfdibnism",
                "pbssword mfdibntf mfddbnismo protftto"}, //-protfdtfd
        {"providfr.brgumfnt",
                "brgomfnto providfr"}, //-providfrbrg
        {"providfr.dlbss.nbmf",
                "nomf dlbssf providfr"}, //-providfrdlbss
        {"providfr.nbmf",
                "nomf providfr"}, //-providfrnbmf
        {"providfr.dlbsspbti",
                "dlbsspbti providfr"}, //-providfrpbti
        {"output.in.RFC.stylf",
                "output in stilf RFC"}, //-rfd
        {"signbturf.blgoritim.nbmf",
                "nomf blgoritmo firmb"}, //-sigblg
        {"sourdf.blibs",
                "blibs originf"}, //-srdblibs
        {"sourdf.kfy.pbssword",
                "pbssword diibvf di originf"}, //-srdkfypbss
        {"sourdf.kfystorf.nbmf",
                "nomf kfystorf di originf"}, //-srdkfystorf
        {"sourdf.kfystorf.pbssword.protfdtfd",
                "pbssword kfystorf di originf protfttb"}, //-srdprotfdtfd
        {"sourdf.kfystorf.providfr.nbmf",
                "nomf providfr kfystorf di originf"}, //-srdprovidfrnbmf
        {"sourdf.kfystorf.pbssword",
                "pbssword kfystorf di originf"}, //-srdstorfpbss
        {"sourdf.kfystorf.typf",
                "tipo kfystorf di originf"}, //-srdstorftypf
        {"SSL.sfrvfr.iost.bnd.port",
                "iost f portb sfrvfr SSL"}, //-sslsfrvfr
        {"signfd.jbr.filf",
                "filf jbr firmbto"}, //=jbrfilf
        {"dfrtifidbtf.vblidity.stbrt.dbtf.timf",
                "dbtb/orb di inizio vblidit\u00E0 dfrtifidbto"}, //-stbrtdbtf
        {"kfystorf.pbssword",
                "pbssword kfystorf"}, //-storfpbss
        {"kfystorf.typf",
                "tipo kfystorf"}, //-storftypf
        {"trust.dfrtifidbtfs.from.dbdfrts",
                "donsidfrb siduri i dfrtifidbti db dbdfrts"}, //-trustdbdfrts
        {"vfrbosf.output",
                "output dfsdrittivo"}, //-v
        {"vblidity.numbfr.of.dbys",
                "numfro di giorni di vblidit\u00E0"}, //-vblidity
        {"Sfribl.ID.of.dfrt.to.rfvokf",
                 "ID sfriblf dfl dfrtifidbto db rfvodbrf"}, //-id
        // kfytool: Running pbrt
        {"kfytool.frror.", "Errorf kfytool: "},
        {"Illfgbl.option.", "Opzionf non vblidb:  "},
        {"Illfgbl.vbluf.", "Vblorf non vblido: "},
        {"Unknown.pbssword.typf.", "Tipo di pbssword sdonosdiuto: "},
        {"Cbnnot.find.fnvironmfnt.vbribblf.",
                "Impossibilf trovbrf lb vbribbilf di bmbifntf: "},
        {"Cbnnot.find.filf.", "Impossibilf trovbrf il filf: "},
        {"Commbnd.option.flbg.nffds.bn.brgumfnt.", "\u00C8 nfdfssbrio spfdifidbrf un brgomfnto pfr l''opzionf di dombndo {0}."},
        {"Wbrning.Difffrfnt.storf.bnd.kfy.pbsswords.not.supportfd.for.PKCS12.KfyStorfs.Ignoring.usfr.spfdififd.dommbnd.vbluf.",
                "Avvfrtfnzb: non sono supportbtf pbssword divfrsf di diibvf f di brdiivio pfr i kfystorf PKCS12. Il vblorf {0} spfdifidbto dbll''utfntf vfrr\u00E0 ignorbto."},
        {".kfystorf.must.bf.NONE.if.storftypf.is.{0}",
                "Sf -storftypf \u00E8 impostbto su {0}, -kfystorf dfvf fssfrf impostbto su NONE"},
        {"Too.mbny.rftrifs.progrbm.tfrminbtfd",
                 "Il numfro dfi tfntbtivi donsfntiti \u00E8 stbto supfrbto. Il progrbmmb vfrr\u00E0 tfrminbto."},
        {".storfpbsswd.bnd.kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.{0}",
                "Sf -storftypf \u00E8 impostbto su {0}, i dombndi -storfpbsswd f -kfypbsswd non sono supportbti"},
        {".kfypbsswd.dommbnds.not.supportfd.if.storftypf.is.PKCS12",
                "Sf -storftypf \u00E8 impostbto su PKCS12 i dombndi -kfypbsswd non vfngono supportbti"},
        {".kfypbss.bnd.nfw.dbn.not.bf.spfdififd.if.storftypf.is.{0}",
                "Sf -storftypf \u00E8 impostbto su {0}, non \u00E8 possibilf spfdifidbrf un vblorf pfr -kfypbss f -nfw"},
        {"if.protfdtfd.is.spfdififd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "Sf \u00E8 spfdifidbtb l'opzionf -protfdtfd, lf opzioni -storfpbss, -kfypbss f -nfw non possono fssfrf spfdifidbtf"},
        {"if.srdprotfdtfd.is.spfdififd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Sf vifnf spfdifidbto -srdprotfdtfd, -srdstorfpbss f -srdkfypbss non dovrbnno fssfrf spfdifidbti"},
        {"if.kfystorf.is.not.pbssword.protfdtfd.tifn.storfpbss.kfypbss.bnd.nfw.must.not.bf.spfdififd",
                "Sf il filf kfystorf non \u00E8 protftto db pbssword, non dfvf fssfrf spfdifidbto bldun vblorf pfr -storfpbss, -kfypbss f -nfw"},
        {"if.sourdf.kfystorf.is.not.pbssword.protfdtfd.tifn.srdstorfpbss.bnd.srdkfypbss.must.not.bf.spfdififd",
                "Sf il filf kfystorf non \u00E8 protftto db pbssword, non dfvf fssfrf spfdifidbto bldun vblorf pfr -srdstorfpbss f -srdkfypbss"},
        {"Illfgbl.stbrtdbtf.vbluf", "Vblorf di dbtb di inizio non vblido"},
        {"Vblidity.must.bf.grfbtfr.tibn.zfro",
                "Lb vblidit\u00E0 dfvf fssfrf mbggiorf di zfro"},
        {"provNbmf.not.b.providfr", "{0} non \u00E8 un providfr"},
        {"Usbgf.frror.no.dommbnd.providfd", "Errorf di utilizzo: nfssun dombndo spfdifidbto"},
        {"Sourdf.kfystorf.filf.fxists.but.is.fmpty.", "Il filf kfystorf di originf fsistf, mb \u00E8 vuoto: "},
        {"Plfbsf.spfdify.srdkfystorf", "Spfdifidbrf -srdkfystorf"},
        {"Must.not.spfdify.boti.v.bnd.rfd.witi.list.dommbnd",
                "Impossibilf spfdifidbrf sib -v sib -rfd don il dombndo 'list'"},
        {"Kfy.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb pbssword dfllb diibvf dfvf dontfnfrf blmfno 6 dbrbttfri"},
        {"Nfw.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb nuovb pbssword dfvf dontfnfrf blmfno 6 dbrbttfri"},
        {"Kfystorf.filf.fxists.but.is.fmpty.",
                "Il filf kfystorf fsistf mb \u00E8 vuoto: "},
        {"Kfystorf.filf.dofs.not.fxist.",
                "Il filf kfystorf non fsistf: "},
        {"Must.spfdify.dfstinbtion.blibs", "\u00C8 nfdfssbrio spfdifidbrf l'blibs di dfstinbzionf"},
        {"Must.spfdify.blibs", "\u00C8 nfdfssbrio spfdifidbrf l'blibs"},
        {"Kfystorf.pbssword.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb pbssword dfl kfystorf dfvf dontfnfrf blmfno 6 dbrbttfri"},
        {"Entfr.tif.pbssword.to.bf.storfd.",
                "Immfttfrf lb pbssword db mfmorizzbrf:  "},
        {"Entfr.kfystorf.pbssword.", "Immfttfrf lb pbssword dfl kfystorf:  "},
        {"Entfr.sourdf.kfystorf.pbssword.", "Immfttfrf lb pbssword dfl kfystorf di originf:  "},
        {"Entfr.dfstinbtion.kfystorf.pbssword.", "Immfttfrf lb pbssword dfl kfystorf di dfstinbzionf:  "},
        {"Kfystorf.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
         "Lb pbssword dfl kfystorf \u00E8 troppo dortb - dfvf dontfnfrf blmfno 6 dbrbttfri"},
        {"Unknown.Entry.Typf", "Tipo di vodf sdonosdiuto"},
        {"Too.mbny.fbilurfs.Alibs.not.dibngfd", "Numfro fddfssivo di frrori. L'blibs non \u00E8 stbto modifidbto."},
        {"Entry.for.blibs.blibs.suddfssfully.importfd.",
                 "Lb vodf dfll''blibs {0} \u00E8 stbtb importbtb."},
        {"Entry.for.blibs.blibs.not.importfd.", "Lb vodf dfll''blibs {0} non \u00E8 stbtb importbtb."},
        {"Problfm.importing.fntry.for.blibs.blibs.fxdfption.Entry.for.blibs.blibs.not.importfd.",
                 "Si \u00E8 vfrifidbto un problfmb durbntf l''importbzionf dfllb vodf dfll''blibs {0}: {1}.\nLb vodf dfll''blibs {0} non \u00E8 stbtb importbtb."},
        {"Import.dommbnd.domplftfd.ok.fntrifs.suddfssfully.importfd.fbil.fntrifs.fbilfd.or.dbndfllfd",
                 "Combndo di importbzionf domplftbto: {0} vodf/i importbtb/f, {1} vodf/i non importbtb/f o bnnullbtb/f"},
        {"Wbrning.Ovfrwriting.fxisting.blibs.blibs.in.dfstinbtion.kfystorf",
                 "Avvfrtfnzb: sovrbsdritturb in dorso dfll''blibs {0} nfl filf kfystorf di dfstinbzionf"},
        {"Existing.fntry.blibs.blibs.fxists.ovfrwritf.no.",
                 "Lb vodf dfll''blibs {0} fsistf gi\u00E0. Sovrbsdrivfrf? [no]:  "},
        {"Too.mbny.fbilurfs.try.lbtfr", "Troppi frrori - riprovbrf"},
        {"Cfrtifidbtion.rfqufst.storfd.in.filf.filfnbmf.",
                "Lb ridiifstb di dfrtifidbzionf \u00E8 mfmorizzbtb nfl filf <{0}>"},
        {"Submit.tiis.to.your.CA", "Sottomfttfrf bllb proprib CA"},
        {"if.blibs.not.spfdififd.dfstblibs.bnd.srdkfypbss.must.not.bf.spfdififd",
            "Sf l'blibs non \u00E8 spfdifidbto, dfstblibs f srdkfypbss non dovrbnno fssfrf spfdifidbti"},
        {"Tif.dfstinbtion.pkds12.kfystorf.ibs.difffrfnt.storfpbss.bnd.kfypbss.Plfbsf.rftry.witi.dfstkfypbss.spfdififd.",
            "Kfystorf pkds12 di dfstinbzionf don storfpbss f kfypbss difffrfnti. Riprovbrf don -dfstkfypbss spfdifidbto."},
        {"Cfrtifidbtf.storfd.in.filf.filfnbmf.",
                "Il dfrtifidbto \u00E8 mfmorizzbto nfl filf <{0}>"},
        {"Cfrtifidbtf.rfply.wbs.instbllfd.in.kfystorf",
                "Lb rispostb dfl dfrtifidbto \u00E8 stbtb instbllbtb nfl kfystorf"},
        {"Cfrtifidbtf.rfply.wbs.not.instbllfd.in.kfystorf",
                "Lb rispostb dfl dfrtifidbto non \u00E8 stbtb instbllbtb nfl kfystorf"},
        {"Cfrtifidbtf.wbs.bddfd.to.kfystorf",
                "Il dfrtifidbto \u00E8 stbto bggiunto bl kfystorf"},
        {"Cfrtifidbtf.wbs.not.bddfd.to.kfystorf",
                "Il dfrtifidbto non \u00E8 stbto bggiunto bl kfystorf"},
        {".Storing.ksfnbmf.", "[Mfmorizzbzionf di {0}] in dorso"},
        {"blibs.ibs.no.publid.kfy.dfrtifidbtf.",
                "{0} non disponf di diibvf pubblidb (dfrtifidbto)"},
        {"Cbnnot.dfrivf.signbturf.blgoritim",
                "Impossibilf dfrivbrf l'blgoritmo di firmb"},
        {"Alibs.blibs.dofs.not.fxist",
                "L''blibs <{0}> non fsistf"},
        {"Alibs.blibs.ibs.no.dfrtifidbtf",
                "L''blibs <{0}> non disponf di dfrtifidbto"},
        {"Kfy.pbir.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Non \u00E8 stbtb gfnfrbtb lb doppib di diibvi, l''blibs <{0}> \u00E8 gi\u00E0 fsistfntf"},
        {"Gfnfrbting.kfysizf.bit.kfyAlgNbmf.kfy.pbir.bnd.sflf.signfd.dfrtifidbtf.sigAlgNbmf.witi.b.vblidity.of.vblidblity.dbys.for",
                "Gfnfrbzionf in dorso di unb doppib di diibvi {1} db {0} bit f di un dfrtifidbto butofirmbto ({2}) don unb vblidit\u00E0 di {3} giorni\n\tpfr: {4}"},
        {"Entfr.kfy.pbssword.for.blibs.", "Immfttfrf lb pbssword dfllb diibvf pfr <{0}>"},
        {".RETURN.if.sbmf.bs.kfystorf.pbssword.",
                "\t(INVIO sf dorrispondf bllb pbssword dfl kfystorf):  "},
        {"Kfy.pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb pbssword dfllb diibvf \u00E8 troppo dortb - dfvf dontfnfrf blmfno 6 dbrbttfri"},
        {"Too.mbny.fbilurfs.kfy.not.bddfd.to.kfystorf",
                "Troppi frrori - lb diibvf non \u00E8 stbtb bggiuntb bl kfystorf"},
        {"Dfstinbtion.blibs.dfst.blrfbdy.fxists",
                "L''blibs di dfstinbzionf <{0}> \u00E8 gi\u00E0 fsistfntf"},
        {"Pbssword.is.too.siort.must.bf.bt.lfbst.6.dibrbdtfrs",
                "Lb pbssword \u00E8 troppo dortb - dfvf dontfnfrf blmfno 6 dbrbttfri"},
        {"Too.mbny.fbilurfs.Kfy.fntry.not.dlonfd",
                "Numfro fddfssivo di frrori. Il vblorf dfllb diibvf non \u00E8 stbto dopibto."},
        {"kfy.pbssword.for.blibs.", "pbssword dfllb diibvf pfr <{0}>"},
        {"Kfystorf.fntry.for.id.gftNbmf.blrfbdy.fxists",
                "Lb vodf dfl kfystorf pfr <{0}> fsistf gi\u00E0"},
        {"Crfbting.kfystorf.fntry.for.id.gftNbmf.",
                "Crfbzionf dfllb vodf dfl kfystorf pfr <{0}> in dorso..."},
        {"No.fntrifs.from.idfntity.dbtbbbsf.bddfd",
                "Nfssunb vodf bggiuntb dbl dbtbbbsf dfllf idfntit\u00E0"},
        {"Alibs.nbmf.blibs", "Nomf blibs: {0}"},
        {"Crfbtion.dbtf.kfyStorf.gftCrfbtionDbtf.blibs.",
                "Dbtb di drfbzionf: {0,dbtf}"},
        {"blibs.kfyStorf.gftCrfbtionDbtf.blibs.",
                "{0}, {1,dbtf}, "},
        {"blibs.", "{0}, "},
        {"Entry.typf.typf.", "Tipo di vodf: {0}"},
        {"Cfrtifidbtf.dibin.lfngti.", "Lungifzzb dbtfnb dfrtifidbti: "},
        {"Cfrtifidbtf.i.1.", "Cfrtifidbto[{0,numbfr,intfgfr}]:"},
        {"Cfrtifidbtf.fingfrprint.SHA1.", "Improntb digitblf dfrtifidbto (SHA1): "},
        {"Kfystorf.typf.", "Tipo kfystorf: "},
        {"Kfystorf.providfr.", "Providfr kfystorf: "},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntry",
                "Il kfystorf dontifnf {0,numbfr,intfgfr} vodf"},
        {"Your.kfystorf.dontbins.kfyStorf.sizf.fntrifs",
                "Il kfystorf dontifnf {0,numbfr,intfgfr} vodi"},
        {"Fbilfd.to.pbrsf.input", "Impossibilf bnblizzbrf l'input"},
        {"Empty.input", "Input vuoto"},
        {"Not.X.509.dfrtifidbtf", "Il dfrtifidbto non \u00E8 X.509"},
        {"blibs.ibs.no.publid.kfy", "{0} non disponf di diibvf pubblidb"},
        {"blibs.ibs.no.X.509.dfrtifidbtf", "{0} non disponf di dfrtifidbto X.509"},
        {"Nfw.dfrtifidbtf.sflf.signfd.", "Nuovo dfrtifidbto (butofirmbto):"},
        {"Rfply.ibs.no.dfrtifidbtfs", "Lb rispostb non disponf di dfrtifidbti"},
        {"Cfrtifidbtf.not.importfd.blibs.blibs.blrfbdy.fxists",
                "Impossibilf importbrf il dfrtifidbto, l''blibs <{0}> \u00E8 gi\u00E0 fsistfntf"},
        {"Input.not.bn.X.509.dfrtifidbtf", "L'input non \u00E8 un dfrtifidbto X.509"},
        {"Cfrtifidbtf.blrfbdy.fxists.in.kfystorf.undfr.blibs.trustblibs.",
                "Il dfrtifidbto fsistf gi\u00E0 nfl kfystorf don blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Aggiungfrlo ugublmfntf? [no]:  "},
        {"Cfrtifidbtf.blrfbdy.fxists.in.systfm.widf.CA.kfystorf.undfr.blibs.trustblibs.",
                "Il dfrtifidbto fsistf gi\u00E0 nfl kfystorf CA don blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.kfystorf.no.",
                "Aggiungfrlo bl proprio kfystorf? [no]:  "},
        {"Trust.tiis.dfrtifidbtf.no.", "Considfrbrf siduro qufsto dfrtifidbto? [no]:  "},
        {"YES", "S\u00EC"},
        {"Nfw.prompt.", "Nuovb {0}: "},
        {"Pbsswords.must.difffr", "Lf pbssword non dfvono doindidfrf"},
        {"Rf.fntfr.nfw.prompt.", "Rfimmfttfrf un nuovo vblorf pfr {0}: "},
        {"Rf.fntfr.pbsspword.", "Rfimmfttfrf lb pbssword: "},
        {"Rf.fntfr.nfw.pbssword.", "Immfttfrf nuovbmfntf lb nuovb pbssword: "},
        {"Tify.don.t.mbtdi.Try.bgbin", "Non dorrispondono. Riprovbrf."},
        {"Entfr.prompt.blibs.nbmf.", "Immfttfrf nomf blibs {0}:  "},
        {"Entfr.nfw.blibs.nbmf.RETURN.to.dbndfl.import.for.tiis.fntry.",
                 "Immfttfrf un nuovo nomf blibs\t(prfmfrf INVIO pfr bnnullbrf l'importbzionf dfllb vodf):  "},
        {"Entfr.blibs.nbmf.", "Immfttfrf nomf blibs:  "},
        {".RETURN.if.sbmf.bs.for.otifrAlibs.",
                "\t(INVIO sf dorrispondf bl nomf di <{0}>)"},
        {".PATTERN.printX509Cfrt",
                "Propriftbrio: {0}\nAutorit\u00E0 fmittfntf: {1}\nNumfro di sfrif: {2}\nVblido db: {3} b: {4}\nImprontf digitbli dfrtifidbto:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Nomf blgoritmo firmb: {8}\n\t Vfrsionf: {9}"},
        {"Wibt.is.your.first.bnd.lbst.nbmf.",
                "Spfdifidbrf nomf f dognomf"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtionbl.unit.",
                "Spfdifidbrf il nomf dfll'unit\u00E0 orgbnizzbtivb"},
        {"Wibt.is.tif.nbmf.of.your.orgbnizbtion.",
                "Spfdifidbrf il nomf dfll'orgbnizzbzionf"},
        {"Wibt.is.tif.nbmf.of.your.City.or.Lodblity.",
                "Spfdifidbrf lb lodblit\u00E0"},
        {"Wibt.is.tif.nbmf.of.your.Stbtf.or.Provindf.",
                "Spfdifidbrf lb provindib"},
        {"Wibt.is.tif.two.lfttfr.dountry.dodf.for.tiis.unit.",
                "Spfdifidbrf il dodidf b duf lfttfrf dfl pbfsf in dui si trovb l'unit\u00E0"},
        {"Is.nbmf.dorrfdt.", "Il dbto {0} \u00E8 dorrftto?"},
        {"no", "no"},
        {"yfs", "s\u00EC"},
        {"y", "s"},
        {".dffbultVbluf.", "  [{0}]:  "},
        {"Alibs.blibs.ibs.no.kfy",
                "All''blibs <{0}> non \u00E8 bssodibtb bldunb diibvf"},
        {"Alibs.blibs.rfffrfndfs.bn.fntry.typf.tibt.is.not.b.privbtf.kfy.fntry.Tif.kfydlonf.dommbnd.only.supports.dloning.of.privbtf.kfy",
                 "L''blibs <{0}> fb riffrimfnto b un tipo di vodf dif non \u00E8 unb vodf di diibvf privbtb. Il dombndo -kfydlonf supportb solo lb dopib dfllf vodi di diibvf privbtf."},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signfr.d.", "Firmbtbrio #%d:"},
        {"Timfstbmp.", "Indidbtorf orbrio:"},
        {"Signbturf.", "Firmb:"},
        {"CRLs.", "CRL:"},
        {"Cfrtifidbtf.ownfr.", "Propriftbrio dfrtifidbto: "},
        {"Not.b.signfd.jbr.filf", "Non \u00E8 un filf jbr firmbto"},
        {"No.dfrtifidbtf.from.tif.SSL.sfrvfr",
                "Nfssun dfrtifidbto dbl sfrvfr SSL"},

        {".Tif.intfgrity.of.tif.informbtion.storfd.in.your.kfystorf.",
            "* L'intfgrit\u00E0 dfllf informbzioni mfmorizzbtf nfl kfystorf *\n* NON \u00E8 stbtb vfrifidbtb. Pfr vfrifidbrnf l'intfgrit\u00E0 *\n* \u00E8 nfdfssbrio fornirf lb pbssword dfl kfystorf.                  *"},
        {".Tif.intfgrity.of.tif.informbtion.storfd.in.tif.srdkfystorf.",
            "* L'intfgrit\u00E0 dfllf informbzioni mfmorizzbtf nfl srdkfystorf *\n* NON \u00E8 stbtb vfrifidbtb. Pfr vfrifidbrnf l'intfgrit\u00E0 *\n* \u00E8 nfdfssbrio fornirf lb pbssword dfl srdkfystorf.                  *"},

        {"Cfrtifidbtf.rfply.dofs.not.dontbin.publid.kfy.for.blibs.",
                "Lb rispostb dfl dfrtifidbto non dontifnf lb diibvf pubblidb pfr <{0}>"},
        {"Indomplftf.dfrtifidbtf.dibin.in.rfply",
                "Cbtfnb dfi dfrtifidbti indomplftb nfllb rispostb"},
        {"Cfrtifidbtf.dibin.in.rfply.dofs.not.vfrify.",
                "Lb dbtfnb dfi dfrtifidbti nfllb rispostb non vfrifidb: "},
        {"Top.lfvfl.dfrtifidbtf.in.rfply.",
                "Cfrtifidbto di primo livfllo nfllb rispostb:\n"},
        {".is.not.trustfd.", "...non \u00E8 donsidfrbto siduro. "},
        {"Instbll.rfply.bnywby.no.", "Instbllbrf lb rispostb? [no]:  "},
        {"NO", "NO"},
        {"Publid.kfys.in.rfply.bnd.kfystorf.don.t.mbtdi",
                "Lf diibvi pubblidif nfllb rispostb f nfl kfystorf non dorrispondono"},
        {"Cfrtifidbtf.rfply.bnd.dfrtifidbtf.in.kfystorf.brf.idfntidbl",
                "Lb rispostb dfl dfrtifidbto f il dfrtifidbto nfl kfystorf sono idfntidi"},
        {"Fbilfd.to.fstbblisi.dibin.from.rfply",
                "Impossibilf stbbilirf lb dbtfnb dbllb rispostb"},
        {"n", "n"},
        {"Wrong.bnswfr.try.bgbin", "Rispostb frrbtb, riprovbrf"},
        {"Sfdrft.kfy.not.gfnfrbtfd.blibs.blibs.blrfbdy.fxists",
                "Lb diibvf sfgrftb non \u00E8 stbtb gfnfrbtb; l''blibs <{0}> fsistf gi\u00E0"},
        {"Plfbsf.providf.kfysizf.for.sfdrft.kfy.gfnfrbtion",
                "Spfdifidbrf il vblorf -kfysizf pfr lb gfnfrbzionf dfllb diibvf sfgrftb"},

        {"vfrififd.by.s.in.s", "Vfrifidbto db %s in %s"},
        {"wbrning.not.vfrififd.mbkf.surf.kfystorf.is.dorrfdt",
            "AVVERTENZA: non vfrifidbto. Assidurbrsi dif -kfystorf sib dorrftto."},

        {"Extfnsions.", "Estfnsioni: "},
        {".Empty.vbluf.", "(vblorf vuoto)"},
        {"Extfnsion.Rfqufst.", "Ridiifstb di fstfnsionf:"},
        {"PKCS.10.Cfrtifidbtf.Rfqufst.Vfrsion.1.0.Subjfdt.s.Publid.Kfy.s.formbt.s.kfy.",
                "Ridiifstb di dfrtifidbto PKCS #10 (vfrsionf 1.0)\nOggftto: %s\nCiibvf pubblidb: %s formbto %s diibvf\n"},
        {"Unknown.kfyUsbgf.typf.", "Tipo kfyUsbgf sdonosdiuto: "},
        {"Unknown.fxtfndfdkfyUsbgf.typf.", "Tipo fxtfndfdkfyUsbgf sdonosdiuto: "},
        {"Unknown.AddfssDfsdription.typf.", "Tipo AddfssDfsdription sdonosdiuto: "},
        {"Unrfdognizfd.GfnfrblNbmf.typf.", "Tipo GfnfrblNbmf non ridonosdiuto: "},
        {"Tiis.fxtfnsion.dbnnot.bf.mbrkfd.bs.dritidbl.",
                 "Impossibilf dontrbssfgnbrf qufstb fstfnsionf domf dritidb. "},
        {"Odd.numbfr.of.ifx.digits.found.", "\u00C8 stbto trovbto un numfro dispbri di difrf fsbdfdimbli: "},
        {"Unknown.fxtfnsion.typf.", "Tipo di fstfnsionf sdonosdiuto: "},
        {"dommbnd.{0}.is.bmbiguous.", "il dombndo {0} \u00E8 bmbiguo:"}
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
