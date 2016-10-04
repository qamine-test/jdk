/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
public clbss Resources extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Options:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "Use \"keytool -help\" for bll bvbilbble commbnds"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "Key bnd Certificbte Mbnbgement Tool"},
        {"Commbnds.", "Commbnds:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "Use \"keytool -commbnd_nbme -help\" for usbge of commbnd_nbme.\n" +
                "Use the -conf <url> option to specify b pre-configured options file."},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "Generbtes b certificbte request"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "Chbnges bn entry's blibs"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "Deletes bn entry"}, //-delete
        {"Exports.certificbte",
                "Exports certificbte"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "Generbtes b key pbir"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "Generbtes b secret key"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "Generbtes certificbte from b certificbte request"}, //-gencert
        {"Generbtes.CRL", "Generbtes CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "Generbted {0} secret key"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "Generbted {0}-bit {1} secret key"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "Imports entries from b JDK 1.1.x-style identity dbtbbbse"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "Imports b certificbte or b certificbte chbin"}, //-importcert
        {"Imports.b.pbssword",
                "Imports b pbssword"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "Imports one or bll entries from bnother keystore"}, //-importkeystore
        {"Clones.b.key.entry",
                "Clones b key entry"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "Chbnges the key pbssword of bn entry"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "Lists entries in b keystore"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "Prints the content of b certificbte"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "Prints the content of b certificbte request"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "Prints the content of b CRL file"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "Generbtes b self-signed certificbte"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "Chbnges the store pbssword of b keystore"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "blibs nbme of the entry to process"}, //-blibs
        {"destinbtion.blibs",
                "destinbtion blibs"}, //-destblibs
        {"destinbtion.key.pbssword",
                "destinbtion key pbssword"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "destinbtion keystore nbme"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "destinbtion keystore pbssword protected"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "destinbtion keystore provider nbme"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "destinbtion keystore pbssword"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "destinbtion keystore type"}, //-deststoretype
        {"distinguished.nbme",
                "distinguished nbme"}, //-dnbme
        {"X.509.extension",
                "X.509 extension"}, //-ext
        {"output.file.nbme",
                "output file nbme"}, //-file bnd -outfile
        {"input.file.nbme",
                "input file nbme"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "key blgorithm nbme"}, //-keyblg
        {"key.pbssword",
                "key pbssword"}, //-keypbss
        {"key.bit.size",
                "key bit size"}, //-keysize
        {"keystore.nbme",
                "keystore nbme"}, //-keystore
        {"new.pbssword",
                "new pbssword"}, //-new
        {"do.not.prompt",
                "do not prompt"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "pbssword through protected mechbnism"}, //-protected
        {"provider.brgument",
                "provider brgument"}, //-providerbrg
        {"provider.clbss.nbme",
                "provider clbss nbme"}, //-providerclbss
        {"provider.nbme",
                "provider nbme"}, //-providernbme
        {"provider.clbsspbth",
                "provider clbsspbth"}, //-providerpbth
        {"output.in.RFC.style",
                "output in RFC style"}, //-rfc
        {"signbture.blgorithm.nbme",
                "signbture blgorithm nbme"}, //-sigblg
        {"source.blibs",
                "source blibs"}, //-srcblibs
        {"source.key.pbssword",
                "source key pbssword"}, //-srckeypbss
        {"source.keystore.nbme",
                "source keystore nbme"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "source keystore pbssword protected"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "source keystore provider nbme"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "source keystore pbssword"}, //-srcstorepbss
        {"source.keystore.type",
                "source keystore type"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "SSL server host bnd port"}, //-sslserver
        {"signed.jbr.file",
                "signed jbr file"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "certificbte vblidity stbrt dbte/time"}, //-stbrtdbte
        {"keystore.pbssword",
                "keystore pbssword"}, //-storepbss
        {"keystore.type",
                "keystore type"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "trust certificbtes from cbcerts"}, //-trustcbcerts
        {"verbose.output",
                "verbose output"}, //-v
        {"vblidity.number.of.dbys",
                "vblidity number of dbys"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "Seribl ID of cert to revoke"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "keytool error: "},
        {"Illegbl.option.", "Illegbl option:  "},
        {"Illegbl.vblue.", "Illegbl vblue: "},
        {"Unknown.pbssword.type.", "Unknown pbssword type: "},
        {"Cbnnot.find.environment.vbribble.",
                "Cbnnot find environment vbribble: "},
        {"Cbnnot.find.file.", "Cbnnot find file: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "Commbnd option {0} needs bn brgument."},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "Wbrning:  Different store bnd key pbsswords not supported for PKCS12 KeyStores. Ignoring user-specified {0} vblue."},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore must be NONE if -storetype is {0}"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "Too mbny retries, progrbm terminbted"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "-storepbsswd bnd -keypbsswd commbnds not supported if -storetype is {0}"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "-keypbsswd commbnds not supported if -storetype is PKCS12"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "-keypbss bnd -new cbn not be specified if -storetype is {0}"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "if -protected is specified, then -storepbss, -keypbss, bnd -new must not be specified"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "if -srcprotected is specified, then -srcstorepbss bnd -srckeypbss must not be specified"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "if keystore is not pbssword protected, then -storepbss, -keypbss, bnd -new must not be specified"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "if source keystore is not pbssword protected, then -srcstorepbss bnd -srckeypbss must not be specified"},
        {"Illegbl.stbrtdbte.vblue", "Illegbl stbrtdbte vblue"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "Vblidity must be grebter thbn zero"},
        {"provNbme.not.b.provider", "{0} not b provider"},
        {"Usbge.error.no.commbnd.provided", "Usbge error: no commbnd provided"},
        {"Source.keystore.file.exists.but.is.empty.", "Source keystore file exists, but is empty: "},
        {"Plebse.specify.srckeystore", "Plebse specify -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "Must not specify both -v bnd -rfc with 'list' commbnd"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Key pbssword must be bt lebst 6 chbrbcters"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "New pbssword must be bt lebst 6 chbrbcters"},
        {"Keystore.file.exists.but.is.empty.",
                "Keystore file exists, but is empty: "},
        {"Keystore.file.does.not.exist.",
                "Keystore file does not exist: "},
        {"Must.specify.destinbtion.blibs", "Must specify destinbtion blibs"},
        {"Must.specify.blibs", "Must specify blibs"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Keystore pbssword must be bt lebst 6 chbrbcters"},
        {"Enter.the.pbssword.to.be.stored.",
                "Enter the pbssword to be stored:  "},
        {"Enter.keystore.pbssword.", "Enter keystore pbssword:  "},
        {"Enter.source.keystore.pbssword.", "Enter source keystore pbssword:  "},
        {"Enter.destinbtion.keystore.pbssword.", "Enter destinbtion keystore pbssword:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "Keystore pbssword is too short - must be bt lebst 6 chbrbcters"},
        {"Unknown.Entry.Type", "Unknown Entry Type"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "Too mbny fbilures. Alibs not chbnged"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "Entry for blibs {0} successfully imported."},
        {"Entry.for.blibs.blibs.not.imported.", "Entry for blibs {0} not imported."},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "Problem importing entry for blibs {0}: {1}.\nEntry for blibs {0} not imported."},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "Import commbnd completed:  {0} entries successfully imported, {1} entries fbiled or cbncelled"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "Wbrning: Overwriting existing blibs {0} in destinbtion keystore"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "Existing entry blibs {0} exists, overwrite? [no]:  "},
        {"Too.mbny.fbilures.try.lbter", "Too mbny fbilures - try lbter"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "Certificbtion request stored in file <{0}>"},
        {"Submit.this.to.your.CA", "Submit this to your CA"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "if blibs not specified, destblibs bnd srckeypbss must not be specified"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "The destinbtion pkcs12 keystore hbs different storepbss bnd keypbss. Plebse retry with -destkeypbss specified."},
        {"Certificbte.stored.in.file.filenbme.",
                "Certificbte stored in file <{0}>"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "Certificbte reply wbs instblled in keystore"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "Certificbte reply wbs not instblled in keystore"},
        {"Certificbte.wbs.bdded.to.keystore",
                "Certificbte wbs bdded to keystore"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "Certificbte wbs not bdded to keystore"},
        {".Storing.ksfnbme.", "[Storing {0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} hbs no public key (certificbte)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "Cbnnot derive signbture blgorithm"},
        {"Alibs.blibs.does.not.exist",
                "Alibs <{0}> does not exist"},
        {"Alibs.blibs.hbs.no.certificbte",
                "Alibs <{0}> hbs no certificbte"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "Key pbir not generbted, blibs <{0}> blrebdy exists"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "Generbting {0} bit {1} key pbir bnd self-signed certificbte ({2}) with b vblidity of {3} dbys\n\tfor: {4}"},
        {"Enter.key.pbssword.for.blibs.", "Enter key pbssword for <{0}>"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(RETURN if sbme bs keystore pbssword):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Key pbssword is too short - must be bt lebst 6 chbrbcters"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "Too mbny fbilures - key not bdded to keystore"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "Destinbtion blibs <{0}> blrebdy exists"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Pbssword is too short - must be bt lebst 6 chbrbcters"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "Too mbny fbilures. Key entry not cloned"},
        {"key.pbssword.for.blibs.", "key pbssword for <{0}>"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "Keystore entry for <{0}> blrebdy exists"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "Crebting keystore entry for <{0}> ..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "No entries from identity dbtbbbse bdded"},
        {"Alibs.nbme.blibs", "Alibs nbme: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "Crebtion dbte: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "Entry type: {0}"},
        {"Certificbte.chbin.length.", "Certificbte chbin length: "},
        {"Certificbte.i.1.", "Certificbte[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "Certificbte fingerprint (SHA1): "},
        {"Keystore.type.", "Keystore type: "},
        {"Keystore.provider.", "Keystore provider: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "Your keystore contbins {0,number,integer} entry"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "Your keystore contbins {0,number,integer} entries"},
        {"Fbiled.to.pbrse.input", "Fbiled to pbrse input"},
        {"Empty.input", "Empty input"},
        {"Not.X.509.certificbte", "Not X.509 certificbte"},
        {"blibs.hbs.no.public.key", "{0} hbs no public key"},
        {"blibs.hbs.no.X.509.certificbte", "{0} hbs no X.509 certificbte"},
        {"New.certificbte.self.signed.", "New certificbte (self-signed):"},
        {"Reply.hbs.no.certificbtes", "Reply hbs no certificbtes"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "Certificbte not imported, blibs <{0}> blrebdy exists"},
        {"Input.not.bn.X.509.certificbte", "Input not bn X.509 certificbte"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "Certificbte blrebdy exists in keystore under blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Do you still wbnt to bdd it? [no]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "Certificbte blrebdy exists in system-wide CA keystore under blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "Do you still wbnt to bdd it to your own keystore? [no]:  "},
        {"Trust.this.certificbte.no.", "Trust this certificbte? [no]:  "},
        {"YES", "YES"},
        {"New.prompt.", "New {0}: "},
        {"Pbsswords.must.differ", "Pbsswords must differ"},
        {"Re.enter.new.prompt.", "Re-enter new {0}: "},
        {"Re.enter.pbsspword.", "Re-enter pbssword: "},
        {"Re.enter.new.pbssword.", "Re-enter new pbssword: "},
        {"They.don.t.mbtch.Try.bgbin", "They don't mbtch. Try bgbin"},
        {"Enter.prompt.blibs.nbme.", "Enter {0} blibs nbme:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "Enter new blibs nbme\t(RETURN to cbncel import for this entry):  "},
        {"Enter.blibs.nbme.", "Enter blibs nbme:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(RETURN if sbme bs for <{0}>)"},
        {".PATTERN.printX509Cert",
                "Owner: {0}\nIssuer: {1}\nSeribl number: {2}\nVblid from: {3} until: {4}\nCertificbte fingerprints:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Signbture blgorithm nbme: {8}\n\t Version: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "Whbt is your first bnd lbst nbme?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "Whbt is the nbme of your orgbnizbtionbl unit?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "Whbt is the nbme of your orgbnizbtion?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "Whbt is the nbme of your City or Locblity?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "Whbt is the nbme of your Stbte or Province?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "Whbt is the two-letter country code for this unit?"},
        {"Is.nbme.correct.", "Is {0} correct?"},
        {"no", "no"},
        {"yes", "yes"},
        {"y", "y"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "Alibs <{0}> hbs no key"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "Alibs <{0}> references bn entry type thbt is not b privbte key entry.  The -keyclone commbnd only supports cloning of privbte key entries"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "Signer #%d:"},
        {"Timestbmp.", "Timestbmp:"},
        {"Signbture.", "Signbture:"},
        {"CRLs.", "CRLs:"},
        {"Certificbte.owner.", "Certificbte owner: "},
        {"Not.b.signed.jbr.file", "Not b signed jbr file"},
        {"No.certificbte.from.the.SSL.server",
                "No certificbte from the SSL server"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* The integrity of the informbtion stored in your keystore  *\n" +
            "* hbs NOT been verified!  In order to verify its integrity, *\n" +
            "* you must provide your keystore pbssword.                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* The integrity of the informbtion stored in the srckeystore*\n" +
            "* hbs NOT been verified!  In order to verify its integrity, *\n" +
            "* you must provide the srckeystore pbssword.                *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "Certificbte reply does not contbin public key for <{0}>"},
        {"Incomplete.certificbte.chbin.in.reply",
                "Incomplete certificbte chbin in reply"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "Certificbte chbin in reply does not verify: "},
        {"Top.level.certificbte.in.reply.",
                "Top-level certificbte in reply:\n"},
        {".is.not.trusted.", "... is not trusted. "},
        {"Instbll.reply.bnywby.no.", "Instbll reply bnywby? [no]:  "},
        {"NO", "NO"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "Public keys in reply bnd keystore don't mbtch"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "Certificbte reply bnd certificbte in keystore bre identicbl"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "Fbiled to estbblish chbin from reply"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "Wrong bnswer, try bgbin"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "Secret Key not generbted, blibs <{0}> blrebdy exists"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "Plebse provide -keysize for secret key generbtion"},

        {"verified.by.s.in.s", "Verified by %s in %s"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "WARNING: not verified. Mbke sure -keystore is correct."},

        {"Extensions.", "Extensions: "},
        {".Empty.vblue.", "(Empty vblue)"},
        {"Extension.Request.", "Extension Request:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "PKCS #10 Certificbte Request (Version 1.0)\n" +
                "Subject: %s\nPublic Key: %s formbt %s key\n"},
        {"Unknown.keyUsbge.type.", "Unknown keyUsbge type: "},
        {"Unknown.extendedkeyUsbge.type.", "Unknown extendedkeyUsbge type: "},
        {"Unknown.AccessDescription.type.", "Unknown AccessDescription type: "},
        {"Unrecognized.GenerblNbme.type.", "Unrecognized GenerblNbme type: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "This extension cbnnot be mbrked bs criticbl. "},
        {"Odd.number.of.hex.digits.found.", "Odd number of hex digits found: "},
        {"Unknown.extension.type.", "Unknown extension type: "},
        {"commbnd.{0}.is.bmbiguous.", "commbnd {0} is bmbiguous:"}
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
