/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.tools.keytool;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the keytool.
 *
 */
public clbss Resources_sv extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Alternbtiv:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "L\u00E4s \"Hj\u00E4lp - Nyckelverktyg\" f\u00F6r bllb tillg\u00E4ngligb kommbndon"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "Hbnteringsverktyg f\u00F6r nycklbr och certifikbt"},
        {"Commbnds.", "Kommbndon:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "L\u00E4s \"Hj\u00E4lp - Nyckelverktyg - commbnd_nbme\" om bnv\u00E4ndning bv commbnd_nbme"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "Genererbr certifikbtbeg\u00E4rbn"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "\u00C4ndrbr postblibs"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "Tbr bort post"}, //-delete
        {"Exports.certificbte",
                "Exporterbr certifikbt"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "Genererbr nyckelpbr"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "Genererbr hemlig nyckel"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "Genererbr certifikbt fr\u00E5n certifikbtbeg\u00E4rbn"}, //-gencert
        {"Generbtes.CRL", "Genererbr CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "Genererbde {0} hemlig nyckel"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "Genererbde {0}-bitbrs {1} hemlig nyckel"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "Importerbr poster fr\u00E5n identitetsdbtbbbs i JDK 1.1.x-formbt"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "Importerbr ett certifikbt eller en certifikbtkedjb"}, //-importcert
        {"Imports.b.pbssword",
                "Importerbr ett l\u00F6senord"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "Importerbr en eller bllb poster fr\u00E5n bnnbt nyckellbger"}, //-importkeystore
        {"Clones.b.key.entry",
                "Klonbr en nyckelpost"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "\u00C4ndrbr nyckell\u00F6senordet f\u00F6r en post"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "Visbr listb \u00F6ver poster i nyckellbger"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "Skriver ut inneh\u00E5llet i ett certifikbt"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "Skriver ut inneh\u00E5llet i en certifikbtbeg\u00E4rbn"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "Skriver ut inneh\u00E5llet i en CRL-fil"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "Genererbr ett sj\u00E4lvsignerbt certifikbt"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "\u00C4ndrbr lbgerl\u00F6senordet f\u00F6r ett nyckellbger"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "blibsnbmn f\u00F6r post som skb bebrbetbs"}, //-blibs
        {"destinbtion.blibs",
                "destinbtionsblibs"}, //-destblibs
        {"destinbtion.key.pbssword",
                "l\u00F6senord f\u00F6r destinbtionsnyckel"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "nbmn p\u00E5 destinbtionsnyckellbger"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "skyddbt l\u00F6senord f\u00F6r destinbtionsnyckellbger"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "leverbnt\u00F6rsnbmn f\u00F6r destinbtionsnyckellbger"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "l\u00F6senord f\u00F6r destinbtionsnyckellbger"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "typ bv destinbtionsnyckellbger"}, //-deststoretype
        {"distinguished.nbme",
                "unikt nbmn"}, //-dnbme
        {"X.509.extension",
                "X.509-till\u00E4gg"}, //-ext
        {"output.file.nbme",
                "nbmn p\u00E5 utdbtbfil"}, //-file bnd -outfile
        {"input.file.nbme",
                "nbmn p\u00E5 indbtbfil"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "nbmn p\u00E5 nyckelblgoritm"}, //-keyblg
        {"key.pbssword",
                "nyckell\u00F6senord"}, //-keypbss
        {"key.bit.size",
                "nyckelbitstorlek"}, //-keysize
        {"keystore.nbme",
                "nbmn p\u00E5 nyckellbger"}, //-keystore
        {"new.pbssword",
                "nytt l\u00F6senord"}, //-new
        {"do.not.prompt",
                "fr\u00E5gb inte"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "l\u00F6senord med skyddbd mekbnism"}, //-protected
        {"provider.brgument",
                "leverbnt\u00F6rsbrgument"}, //-providerbrg
        {"provider.clbss.nbme",
                "nbmn p\u00E5 leverbnt\u00F6rsklbss"}, //-providerclbss
        {"provider.nbme",
                "leverbnt\u00F6rsnbmn"}, //-providernbme
        {"provider.clbsspbth",
                "leverbnt\u00F6rsklbss\u00F6kv\u00E4g"}, //-providerpbth
        {"output.in.RFC.style",
                "utdbtb i RFC-formbt"}, //-rfc
        {"signbture.blgorithm.nbme",
                "nbmn p\u00E5 signbturblgoritm"}, //-sigblg
        {"source.blibs",
                "k\u00E4llblibs"}, //-srcblibs
        {"source.key.pbssword",
                "l\u00F6senord f\u00F6r k\u00E4llnyckel"}, //-srckeypbss
        {"source.keystore.nbme",
                "nbmn p\u00E5 k\u00E4llnyckellbger"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "skyddbt l\u00F6senord f\u00F6r k\u00E4llnyckellbger"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "leverbnt\u00F6rsnbmn f\u00F6r k\u00E4llnyckellbger"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "l\u00F6senord f\u00F6r k\u00E4llnyckellbger"}, //-srcstorepbss
        {"source.keystore.type",
                "typ bv k\u00E4llnyckellbger"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "SSL-serverv\u00E4rd och -port"}, //-sslserver
        {"signed.jbr.file",
                "signerbd jbr-fil"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "stbrtdbtum/-tid f\u00F6r certifikbtets giltighet"}, //-stbrtdbte
        {"keystore.pbssword",
                "l\u00F6senord f\u00F6r nyckellbger"}, //-storepbss
        {"keystore.type",
                "nyckellbgertyp"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "tillf\u00F6rlitligb certifikbt fr\u00E5n cbcerts"}, //-trustcbcerts
        {"verbose.output",
                "utf\u00F6rligb utdbtb"}, //-v
        {"vblidity.number.of.dbys",
                "bntbl dbgbr f\u00F6r giltighet"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "Seriellt ID f\u00F6r certifikbt som skb \u00E5terkbllbs"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "nyckelverktygsfel: "},
        {"Illegbl.option.", "Otill\u00E5tet blternbtiv:  "},
        {"Illegbl.vblue.", "Otill\u00E5tet v\u00E4rde: "},
        {"Unknown.pbssword.type.", "Ok\u00E4nd l\u00F6senordstyp: "},
        {"Cbnnot.find.environment.vbribble.",
                "Hittbr inte milj\u00F6vbribbel: "},
        {"Cbnnot.find.file.", "Hittbr inte fil: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "Kommbndoblternbtivet {0} beh\u00F6ver ett brgument."},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "Vbrning!  PKCS12-nyckellbger hbr inte st\u00F6d f\u00F6r olikb l\u00F6senord f\u00F6r lbgret och nyckeln. Det bnv\u00E4ndbrspecificerbde {0}-v\u00E4rdet ignorerbs."},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore m\u00E5ste vbrb NONE om -storetype \u00E4r {0}"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "F\u00F6r m\u00E5ngb f\u00F6rs\u00F6k. Progrbmmet bvslutbs"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "-storepbsswd- och -keypbsswd-kommbndon st\u00F6ds inte om -storetype \u00E4r {0}"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "-keypbsswd-kommbndon st\u00F6ds inte om -storetype \u00E4r PKCS12"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "-keypbss och -new kbn inte bnges om -storetype \u00E4r {0}"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "om -protected hbr bngetts f\u00E5r inte -storepbss, -keypbss och -new bnges"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "om -srcprotected bnges f\u00E5r -srcstorepbss och -srckeypbss inte bnges"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "om nyckellbgret inte \u00E4r l\u00F6senordsskyddbt f\u00E5r -storepbss, -keypbss och -new inte bnges"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "om k\u00E4llnyckellbgret inte \u00E4r l\u00F6senordsskyddbt f\u00E5r -srcstorepbss och -srckeypbss inte bnges"},
        {"Illegbl.stbrtdbte.vblue", "Otill\u00E5tet v\u00E4rde f\u00F6r stbrtdbtum"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "Giltigheten m\u00E5ste vbrb st\u00F6rre \u00E4n noll"},
        {"provNbme.not.b.provider", "{0} \u00E4r inte en leverbnt\u00F6r"},
        {"Usbge.error.no.commbnd.provided", "Syntbxfel: inget kommbndo bngivet"},
        {"Source.keystore.file.exists.but.is.empty.", "Nyckellbgrets k\u00E4llfil finns, men \u00E4r tom: "},
        {"Plebse.specify.srckeystore", "Ange -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "Kbn inte specificerb b\u00E5de -v och -rfc med 'list'-kommbndot"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Nyckell\u00F6senordet m\u00E5ste inneh\u00E5llb minst 6 tecken"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Det nyb l\u00F6senordet m\u00E5ste inneh\u00E5llb minst 6 tecken"},
        {"Keystore.file.exists.but.is.empty.",
                "Nyckellbgerfilen finns, men \u00E4r tom: "},
        {"Keystore.file.does.not.exist.",
                "Nyckellbgerfilen finns inte: "},
        {"Must.specify.destinbtion.blibs", "Du m\u00E5ste bnge destinbtionsblibs"},
        {"Must.specify.blibs", "Du m\u00E5ste bnge blibs"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Nyckellbgerl\u00F6senordet m\u00E5ste inneh\u00E5llb minst 6 tecken"},
        {"Enter.the.pbssword.to.be.stored.",
                "Ange det l\u00F6senord som skb lbgrbs:  "},
        {"Enter.keystore.pbssword.", "Ange nyckellbgerl\u00F6senord:  "},
        {"Enter.source.keystore.pbssword.", "Ange l\u00F6senord f\u00F6r k\u00E4llnyckellbgret:  "},
        {"Enter.destinbtion.keystore.pbssword.", "Ange nyckellbgerl\u00F6senord f\u00F6r destinbtion:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "Nyckellbgerl\u00F6senordet \u00E4r f\u00F6r kort - det m\u00E5ste inneh\u00E5llb minst 6 tecken"},
        {"Unknown.Entry.Type", "Ok\u00E4nd posttyp"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "F\u00F6r m\u00E5ngb fel. Alibs hbr inte \u00E4ndrbts"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "Posten f\u00F6r blibs {0} hbr importerbts."},
        {"Entry.for.blibs.blibs.not.imported.", "Posten f\u00F6r blibs {0} hbr inte importerbts."},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "Ett problem uppstod vid importen bv posten f\u00F6r blibs {0}: {1}.\nPosten {0} hbr inte importerbts."},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "Kommbndoimporten slutf\u00F6rd: {0} poster hbr importerbts, {1} poster vbr felbktigb eller bnnullerbdes"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "Vbrning! Det befintligb blibset {0} i destinbtionsnyckellbgret skrivs \u00F6ver"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "Alibset {0} finns redbn. Vill du skrivb \u00F6ver det? [nej]:  "},
        {"Too.mbny.fbilures.try.lbter", "F\u00F6r m\u00E5ngb fel - f\u00F6rs\u00F6k igen senbre"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "Certifikbtbeg\u00E4rbn hbr lbgrbts i filen <{0}>"},
        {"Submit.this.to.your.CA", "Skickb dettb till certifikbtutf\u00E4rdbren"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "om blibs inte bngivits skb inte heller destblibs och srckeypbss bnges"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "Destinbtionsnyckellbgret pkcs12 hbr olikb storepbss och keypbss. F\u00F6rs\u00F6k igen med -destkeypbss bngivet."},
        {"Certificbte.stored.in.file.filenbme.",
                "Certifikbtet hbr lbgrbts i filen <{0}>"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "Certifikbtsvbret hbr instbllerbts i nyckellbgret"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "Certifikbtsvbret hbr inte instbllerbts i nyckellbgret"},
        {"Certificbte.wbs.bdded.to.keystore",
                "Certifikbtet hbr lbgts till i nyckellbgret"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "Certifikbtet hbr inte lbgts till i nyckellbgret"},
        {".Storing.ksfnbme.", "[Lbgrbr {0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} sbknbr offentlig nyckel (certifikbt)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "Kbn inte h\u00E4rledb signbturblgoritm"},
        {"Alibs.blibs.does.not.exist",
                "Alibset <{0}> finns inte"},
        {"Alibs.blibs.hbs.no.certificbte",
                "Alibset <{0}> sbknbr certifikbt"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "Nyckelpbret genererbdes inte. Alibset <{0}> finns redbn"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "Genererbr {0} bitbrs {1}-nyckelpbr och sj\u00E4lvsignerbt certifikbt ({2}) med en giltighet p\u00E5 {3} dbgbr\n\tf\u00F6r: {4}"},
        {"Enter.key.pbssword.for.blibs.", "Ange nyckell\u00F6senord f\u00F6r <{0}>"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(RETURN om det \u00E4r identiskt med nyckellbgerl\u00F6senordet):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Nyckell\u00F6senordet \u00E4r f\u00F6r kort - det m\u00E5ste inneh\u00E5llb minst 6 tecken"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "F\u00F6r m\u00E5ngb fel - nyckeln lbdes inte till i nyckellbgret"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "Destinbtionsblibset <{0}> finns redbn"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "L\u00F6senordet \u00E4r f\u00F6r kort - det m\u00E5ste inneh\u00E5llb minst 6 tecken"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "F\u00F6r m\u00E5ngb fel. Nyckelposten hbr inte klonbts"},
        {"key.pbssword.for.blibs.", "nyckell\u00F6senord f\u00F6r <{0}>"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "Nyckellbgerpost f\u00F6r <{0}> finns redbn"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "Skbpbr nyckellbgerpost f\u00F6r <{0}> ..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "Ingb poster fr\u00E5n identitetsdbtbbbsen hbr lbgts till"},
        {"Alibs.nbme.blibs", "Alibsnbmn: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "Skbpbt den: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "Posttyp: {0}"},
        {"Certificbte.chbin.length.", "L\u00E4ngd p\u00E5 certifikbtskedjb: "},
        {"Certificbte.i.1.", "Certifikbt[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "Certifikbtets fingerbvtryck (SHA1): "},
        {"Keystore.type.", "Nyckellbgertyp: "},
        {"Keystore.provider.", "Nyckellbgerleverbnt\u00F6r: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "Nyckellbgret inneh\u00E5ller {0,number,integer} post"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "Nyckellbgret inneh\u00E5ller {0,number,integer} poster"},
        {"Fbiled.to.pbrse.input", "Kunde inte tolkb indbtb"},
        {"Empty.input", "Ingb indbtb"},
        {"Not.X.509.certificbte", "Inte ett X.509-certifikbt"},
        {"blibs.hbs.no.public.key", "{0} sbknbr offentlig nyckel"},
        {"blibs.hbs.no.X.509.certificbte", "{0} sbknbr X.509-certifikbt"},
        {"New.certificbte.self.signed.", "Nytt certifikbt (sj\u00E4lvsignerbt):"},
        {"Reply.hbs.no.certificbtes", "Svbret sbknbr certifikbt"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "Certifikbtet importerbdes inte. Alibset <{0}> finns redbn"},
        {"Input.not.bn.X.509.certificbte", "Indbtb \u00E4r inte ett X.509-certifikbt"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "Certifikbtet finns redbn i nyckellbgerfilen under blibset <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Vill du fortfbrbnde l\u00E4ggb till det? [nej]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "Certifikbtet finns redbn i den systemomsp\u00E4nnbnde CA-nyckellbgerfilen under blibset <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "Vill du fortfbrbnde l\u00E4ggb till det i ditt eget nyckellbgret? [nej]:  "},
        {"Trust.this.certificbte.no.", "Litbr du p\u00E5 det h\u00E4r certifikbtet? [nej]:  "},
        {"YES", "JA"},
        {"New.prompt.", "Nytt {0}: "},
        {"Pbsswords.must.differ", "L\u00F6senorden m\u00E5ste vbrb olikb"},
        {"Re.enter.new.prompt.", "Ange nytt {0} igen: "},
        {"Re.enter.pbsspword.", "Ange l\u00F6senord igen: "},
        {"Re.enter.new.pbssword.", "Ange det nyb l\u00F6senordet igen: "},
        {"They.don.t.mbtch.Try.bgbin", "De mbtchbr inte. F\u00F6rs\u00F6k igen"},
        {"Enter.prompt.blibs.nbme.", "Ange blibsnbmn f\u00F6r {0}:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "Ange ett nytt blibsnbmn\t(skriv RETURN f\u00F6r btt bvbrytb importen bv dennb post):  "},
        {"Enter.blibs.nbme.", "Ange blibsnbmn:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(RETURN om det \u00E4r det sbmmb som f\u00F6r <{0}>)"},
        {".PATTERN.printX509Cert",
                "\u00C4gbre: {0}\nUtf\u00E4rdbre: {1}\nSerienummer: {2}\nGiltigt fr\u00E5n den: {3} till: {4}\nCertifikbtets fingerbvtryck:\n\t MD5: {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Nbmn p\u00E5 signbturblgoritm: {8}\n\t Version: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "Vbd heter du i f\u00F6r- och efternbmn?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "Vbd heter din bvdelning inom orgbnisbtionen?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "Vbd heter din orgbnisbtion?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "Vbd heter din ort eller plbts?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "Vbd heter ditt lbnd eller din provins?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "Vilken \u00E4r den tv\u00E5st\u00E4lligb lbndskoden?"},
        {"Is.nbme.correct.", "\u00C4r {0} korrekt?"},
        {"no", "nej"},
        {"yes", "jb"},
        {"y", "j"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "Alibset <{0}> sbknbr nyckel"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "Alibset <{0}> refererbr till en posttyp som inte \u00E4r n\u00E5gon privbt nyckelpost. Kommbndot -keyclone hbr endbst st\u00F6d f\u00F6r kloning bv privbtb nyckelposter"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "Signerbre #%d:"},
        {"Timestbmp.", "Tidsst\u00E4mpel:"},
        {"Signbture.", "Underskrift:"},
        {"CRLs.", "CRL:er:"},
        {"Certificbte.owner.", "Certifikbt\u00E4gbre: "},
        {"Not.b.signed.jbr.file", "Ingen signerbd jbr-fil"},
        {"No.certificbte.from.the.SSL.server",
                "Inget certifikbt fr\u00E5n SSL-servern"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* Integriteten f\u00F6r den informbtion som lbgrbs i nyckellbgerfilen  *\n* hbr INTE verifierbts!  Om du vill verifierb dess integritet *\n* m\u00E5ste du bnge l\u00F6senordet f\u00F6r nyckellbgret.                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* Integriteten f\u00F6r den informbtion som lbgrbs i srckeystore*\n* hbr INTE verifierbts!  Om du vill verifierb dess integritet *\n* m\u00E5ste du bnge l\u00F6senordet f\u00F6r srckeystore.                *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "Certifikbtsvbret inneh\u00E5ller inte n\u00E5gon offentlig nyckel f\u00F6r <{0}>"},
        {"Incomplete.certificbte.chbin.in.reply",
                "Ofullst\u00E4ndig certifikbtskedjb i svbret"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "Certifikbtskedjbn i svbret g\u00E5r inte btt verifierb: "},
        {"Top.level.certificbte.in.reply.",
                "Toppniv\u00E5certifikbtet i svbret:\n"},
        {".is.not.trusted.", "... \u00E4r inte betrott. "},
        {"Instbll.reply.bnywby.no.", "Vill du instbllerb svbret \u00E4nd\u00E5? [nej]:  "},
        {"NO", "NEJ"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "De offentligb nycklbrnb i svbret och nyckellbgret mbtchbr inte vbrbndrb"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "Certifikbtsvbret och certifikbtet i nyckellbgret \u00E4r identiskb"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "Kunde inte uppr\u00E4ttb kedjb fr\u00E5n svbret"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "Fel svbr. F\u00F6rs\u00F6k p\u00E5 nytt."},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "Den hemligb nyckeln hbr inte genererbts eftersom blibset <{0}> redbn finns"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "Ange -keysize f\u00F6r btt skbpb hemlig nyckel"},

        {"verified.by.s.in.s", "Verifierbd bv %s i %s"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "VARNING: ej verifierbd. Se till btt -nyckellbger \u00E4r korrekt."},

        {"Extensions.", "Till\u00E4gg: "},
        {".Empty.vblue.", "(Tomt v\u00E4rde)"},
        {"Extension.Request.", "Till\u00E4ggsbeg\u00E4rbn:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "PKCS #10 certifikbtbeg\u00E4rbn (version 1.0)\n\u00C4mne: %s\nAllm\u00E4n nyckel: %s-formbt %s-nyckel\n"},
        {"Unknown.keyUsbge.type.", "Ok\u00E4nd keyUsbge-typ: "},
        {"Unknown.extendedkeyUsbge.type.", "Ok\u00E4nd extendedkeyUsbge-typ: "},
        {"Unknown.AccessDescription.type.", "Ok\u00E4nd AccessDescription-typ: "},
        {"Unrecognized.GenerblNbme.type.", "Ok\u00E4nd GenerblNbme-typ: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "Dettb till\u00E4gg kbn inte mbrkerbs som kritiskt. "},
        {"Odd.number.of.hex.digits.found.", "Uddb bntbl hex-siffror p\u00E5tr\u00E4ffbdes: "},
        {"Unknown.extension.type.", "Ok\u00E4nd till\u00E4ggstyp: "},
        {"commbnd.{0}.is.bmbiguous.", "kommbndot {0} \u00E4r tvetydigt:"}
    };


    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
