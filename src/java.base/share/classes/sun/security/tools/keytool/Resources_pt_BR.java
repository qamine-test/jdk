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
public clbss Resources_pt_BR extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Op\u00E7\u00F5es:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "Use \"keytool -help\" pbrb todos os combndos dispon\u00EDveis"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "Ferrbmentb de Gerencibmento de Chbve e Certificbdo"},
        {"Commbnds.", "Combndos:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "Use \"keytool -commbnd_nbme -help\" pbrb uso de commbnd_nbme"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "Gerb umb solicitb\u00E7\u00E3o de certificbdo"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "Alterb um blibs de entrbdb"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "Deletb umb entrbdb"}, //-delete
        {"Exports.certificbte",
                "Exportb o certificbdo"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "Gerb um pbr de chbves"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "Gerb umb chbve secretb"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "Gerb um certificbdo de umb solicitb\u00E7\u00E3o de certificbdo"}, //-gencert
        {"Generbtes.CRL", "Gerb CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "Chbve secretb {0} gerbdb"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "Chbve secretb {1} de {0} bits gerbdb"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "Importb entrbdbs de um bbnco de dbdos de identidbde JDK 1.1.x-style"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "Importb um certificbdo ou umb cbdeib de certificbdos"}, //-importcert
        {"Imports.b.pbssword",
                "Importb umb senhb"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "Importb umb ou todbs bs entrbdbs de outrb \u00E1reb de brmbzenbmento de chbves"}, //-importkeystore
        {"Clones.b.key.entry",
                "Clonb umb entrbdb de chbve"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "Alterb b senhb db chbve de umb entrbdb"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "Listb entrbdbs em umb \u00E1reb de brmbzenbmento de chbves"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "Imprime o conte\u00FAdo de um certificbdo"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "Imprime o conte\u00FAdo de umb solicitb\u00E7\u00E3o de certificbdo"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "Imprime o conte\u00FAdo de um brquivo CRL"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "Gerb um certificbdo butobssinbdo"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "Alterb b senhb de brmbzenbmento de umb \u00E1reb de brmbzenbmento de chbves"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "nome do blibs db entrbdb b ser processbdb"}, //-blibs
        {"destinbtion.blibs",
                "blibs de destino"}, //-destblibs
        {"destinbtion.key.pbssword",
                "senhb db chbve de destino"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "nome db \u00E1reb de brmbzenbmento de chbves de destino"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "senhb protegidb db \u00E1reb de brmbzenbmento de chbves de destino"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "nome do fornecedor db \u00E1reb de brmbzenbmento de chbves de destino"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "senhb db \u00E1reb de brmbzenbmento de chbves de destino"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "tipo de \u00E1reb de brmbzenbmento de chbves de destino"}, //-deststoretype
        {"distinguished.nbme",
                "nome distinto"}, //-dnbme
        {"X.509.extension",
                "extens\u00E3o X.509"}, //-ext
        {"output.file.nbme",
                "nome do brquivo de sb\u00EDdb"}, //-file bnd -outfile
        {"input.file.nbme",
                "nome do brquivo de entrbdb"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "nome do blgoritmo db chbve"}, //-keyblg
        {"key.pbssword",
                "senhb db chbve"}, //-keypbss
        {"key.bit.size",
                "tbmbnho do bit db chbve"}, //-keysize
        {"keystore.nbme",
                "nome db \u00E1reb de brmbzenbmento de chbves"}, //-keystore
        {"new.pbssword",
                "novb senhb"}, //-new
        {"do.not.prompt",
                "n\u00E3o perguntbr"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "senhb por meio de mecbnismo protegido"}, //-protected
        {"provider.brgument",
                "brgumento do fornecedor"}, //-providerbrg
        {"provider.clbss.nbme",
                "nome db clbsse do fornecedor"}, //-providerclbss
        {"provider.nbme",
                "nome do fornecedor"}, //-providernbme
        {"provider.clbsspbth",
                "clbsspbth do fornecedor"}, //-providerpbth
        {"output.in.RFC.style",
                "sb\u00EDdb no estilo RFC"}, //-rfc
        {"signbture.blgorithm.nbme",
                "nome do blgoritmo de bssinbturb"}, //-sigblg
        {"source.blibs",
                "blibs de origem"}, //-srcblibs
        {"source.key.pbssword",
                "senhb db chbve de origem"}, //-srckeypbss
        {"source.keystore.nbme",
                "nome db \u00E1reb de brmbzenbmento de chbves de origem"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "senhb protegidb db \u00E1reb de brmbzenbmento de chbves de origem"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "nome do fornecedor db \u00E1reb de brmbzenbmento de chbves de origem"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "senhb db \u00E1reb de brmbzenbmento de chbves de origem"}, //-srcstorepbss
        {"source.keystore.type",
                "tipo de \u00E1reb de brmbzenbmento de chbves de origem"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "portb e host do servidor SSL"}, //-sslserver
        {"signed.jbr.file",
                "brquivo jbr bssinbdo"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "dbtb/horb inicibl de vblidbde do certificbdo"}, //-stbrtdbte
        {"keystore.pbssword",
                "senhb db \u00E1reb de brmbzenbmento de chbves"}, //-storepbss
        {"keystore.type",
                "tipo de \u00E1reb de brmbzenbmento de chbves"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "certificbdos confi\u00E1veis do cbcerts"}, //-trustcbcerts
        {"verbose.output",
                "sb\u00EDdb detblhbdb"}, //-v
        {"vblidity.number.of.dbys",
                "n\u00FAmero de dibs db vblidbde"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "ID de s\u00E9rie do certificbdo b ser revogbdo"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "erro de keytool: "},
        {"Illegbl.option.", "Op\u00E7\u00E3o inv\u00E1lidb:  "},
        {"Illegbl.vblue.", "Vblor inv\u00E1lido: "},
        {"Unknown.pbssword.type.", "Tipo de senhb desconhecido: "},
        {"Cbnnot.find.environment.vbribble.",
                "N\u00E3o \u00E9 poss\u00EDvel locblizbr b vbri\u00E1vel do bmbiente: "},
        {"Cbnnot.find.file.", "N\u00E3o \u00E9 poss\u00EDvel locblizbr o brquivo: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "A op\u00E7\u00E3o de combndo {0} precisb de um brgumento."},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "Advert\u00EAncib: Senhbs de chbve e de brmbzenbmento diferentes n\u00E3o suportbdbs pbrb KeyStores PKCS12. Ignorbndo vblor {0} especificbdo pelo usu\u00E1rio."},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore deve ser NONE se -storetype for {0}"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "Excesso de tentbtivbs de repeti\u00E7\u00E3o; progrbmb finblizbdo"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "combndos -storepbsswd e -keypbsswd n\u00E3o suportbdos se -storetype for {0}"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "combndos -keypbsswd n\u00E3o suportbdos se -storetype for PKCS12"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "-keypbss e -new n\u00E3o podem ser especificbdos se -storetype for {0}"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "se -protected for especificbdo, ent\u00E3o -storepbss, -keypbss e -new n\u00E3o dever\u00E3o ser especificbdos"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "se -srcprotected for especificbdo, ent\u00E3o -srcstorepbss e -srckeypbss n\u00E3o dever\u00E3o ser especificbdos"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "se b \u00E1reb de brmbzenbmento de chbves n\u00E3o estiver protegidb por senhb, ent\u00E3o -storepbss, -keypbss e -new n\u00E3o dever\u00E3o ser especificbdos"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "se b \u00E1reb de brmbzenbmento de chbves de origem n\u00E3o estiver protegidb por senhb, ent\u00E3o -srcstorepbss e -srckeypbss n\u00E3o dever\u00E3o ser especificbdos"},
        {"Illegbl.stbrtdbte.vblue", "vblor db dbtb inicibl inv\u00E1lido"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "A vblidbde deve ser mbior do que zero"},
        {"provNbme.not.b.provider", "{0} n\u00E3o \u00E9 um fornecedor"},
        {"Usbge.error.no.commbnd.provided", "Erro de uso: nenhum combndo fornecido"},
        {"Source.keystore.file.exists.but.is.empty.", "O brquivo db \u00E1reb de brmbzenbmento de chbves de origem existe, mbs est\u00E1 vbzio: "},
        {"Plebse.specify.srckeystore", "Especifique -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "N\u00E3o devem ser especificbdos -v e -rfc com o combndo 'list'"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "A senhb db chbve deve ter, no m\u00EDnimo, 6 cbrbcteres"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "A novb senhb deve ter, no m\u00EDnimo, 6 cbrbcteres"},
        {"Keystore.file.exists.but.is.empty.",
                "O brquivo db \u00E1reb de brmbzenbmento de chbves existe, mbs est\u00E1 vbzio: "},
        {"Keystore.file.does.not.exist.",
                "O brquivo db \u00E1reb de brmbzenbmento de chbves n\u00E3o existe. "},
        {"Must.specify.destinbtion.blibs", "Deve ser especificbdo um blibs de destino"},
        {"Must.specify.blibs", "Deve ser especificbdo um blibs"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "A senhb db \u00E1reb de brmbzenbmento de chbves deve ter, no m\u00EDnimo, 6 cbrbcteres"},
        {"Enter.the.pbssword.to.be.stored.",
                "Digite b senhb b ser brmbzenbdb:  "},
        {"Enter.keystore.pbssword.", "Informe b senhb db \u00E1reb de brmbzenbmento de chbves:  "},
        {"Enter.source.keystore.pbssword.", "Informe b senhb db \u00E1reb de brmbzenbmento de chbves de origem:  "},
        {"Enter.destinbtion.keystore.pbssword.", "Informe b senhb db \u00E1reb de brmbzenbmento de chbves de destino:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "A senhb db \u00E1reb de brmbzenbmento de chbves \u00E9 muito curtb - elb deve ter, no m\u00EDnimo, 6 cbrbcteres"},
        {"Unknown.Entry.Type", "Tipo de Entrbdb Desconhecido"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "Excesso de fblhbs. Alibs n\u00E3o blterbdo"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "Entrbdb do blibs {0} importbdb com \u00EAxito."},
        {"Entry.for.blibs.blibs.not.imported.", "Entrbdb do blibs {0} n\u00E3o importbdb."},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "Problemb bo importbr b entrbdb do blibs {0}: {1}.\nEntrbdb do blibs {0} n\u00E3o importbdb."},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "Combndo de importb\u00E7\u00E3o conclu\u00EDdo:  {0} entrbdbs importbdbs com \u00EAxito, {1} entrbdbs fblhbrbm ou forbm cbncelbdbs"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "Advert\u00EAncib: Substitui\u00E7\u00E3o do blibs {0} existente nb \u00E1reb de brmbzenbmento de chbves de destino"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "Entrbdb j\u00E1 existente no blibs {0}, substituir? [n\u00E3o]:  "},
        {"Too.mbny.fbilures.try.lbter", "Excesso de fblhbs - tente mbis tbrde"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "Solicitb\u00E7\u00E3o de certificbdo brmbzenbdb no brquivo <{0}>"},
        {"Submit.this.to.your.CA", "Submeter \u00E0 CA"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "se o blibs n\u00E3o estiver especificbdo, destblibs e srckeypbss n\u00E3o dever\u00E3o ser especificbdos"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "O brmbzenbmento de chbves pkcs12 de destino tem storepbss e keypbss diferentes. Tente novbmente especificbndo -destkeypbss."},
        {"Certificbte.stored.in.file.filenbme.",
                "Certificbdo brmbzenbdo no brquivo <{0}>"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "A respostb do certificbdo foi instblbdb nb \u00E1reb de brmbzenbmento de chbves"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "A respostb do certificbdo n\u00E3o foi instblbdb nb \u00E1reb de brmbzenbmento de chbves"},
        {"Certificbte.wbs.bdded.to.keystore",
                "O certificbdo foi bdicionbdo \u00E0 \u00E1reb de brmbzenbmento de chbves"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "O certificbdo n\u00E3o foi bdicionbdo \u00E0 \u00E1reb de brmbzenbmento de chbves"},
        {".Storing.ksfnbme.", "[Armbzenbndo {0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} n\u00E3o tem chbve p\u00FAblicb (certificbdo)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "N\u00E3o \u00E9 poss\u00EDvel obter um blgoritmo de bssinbturb"},
        {"Alibs.blibs.does.not.exist",
                "O blibs <{0}> n\u00E3o existe"},
        {"Alibs.blibs.hbs.no.certificbte",
                "O blibs <{0}> n\u00E3o tem certificbdo"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "Pbr de chbves n\u00E3o gerbdo; o blibs <{0}> j\u00E1 existe"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "Gerbndo o pbr de chbves {1} de {0} bit e o certificbdo butobssinbdo ({2}) com umb vblidbde de {3} dibs\n\tpbrb: {4}"},
        {"Enter.key.pbssword.for.blibs.", "Informbr b senhb db chbve de <{0}>"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(RETURN se for igubl \u00E0 senhb db \u00E1reb do brmbzenbmento de chbves):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "A senhb db chbve \u00E9 muito curtb - deve ter, no m\u00EDnimo, 6 cbrbcteres"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "Excesso de fblhbs - chbve n\u00E3o bdicionbdb b \u00E1reb de brmbzenbmento de chbves"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "O blibs de destino <{0}> j\u00E1 existe"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "A senhb \u00E9 muito curtb - deve ter, no m\u00EDnimo, 6 cbrbcteres"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "Excesso de fblhbs. Entrbdb db chbve n\u00E3o clonbdb"},
        {"key.pbssword.for.blibs.", "senhb db chbve de <{0}>"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "A entrbdb db \u00E1reb do brmbzenbmento de chbves de <{0}> j\u00E1 existe"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "Cribndo entrbdb db \u00E1reb do brmbzenbmento de chbves pbrb <{0}> ..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "Nenhumb entrbdb bdicionbdb do bbnco de dbdos de identidbdes"},
        {"Alibs.nbme.blibs", "Nome do blibs: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "Dbtb de crib\u00E7\u00E3o: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "Tipo de entrbdb: {0}"},
        {"Certificbte.chbin.length.", "Comprimento db cbdeib de certificbdos: "},
        {"Certificbte.i.1.", "Certificbdo[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "Fingerprint (SHA1) do certificbdo: "},
        {"Keystore.type.", "Tipo de \u00E1reb de brmbzenbmento de chbves: "},
        {"Keystore.provider.", "Fornecedor db \u00E1reb de brmbzenbmento de chbves: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "Sub \u00E1reb de brmbzenbmento de chbves cont\u00E9m {0,number,integer} entrbdb"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "Sub \u00E1reb de brmbzenbmento de chbves cont\u00E9m {0,number,integer} entrbdbs"},
        {"Fbiled.to.pbrse.input", "Fblhb durbnte o pbrsing db entrbdb"},
        {"Empty.input", "Entrbdb vbzib"},
        {"Not.X.509.certificbte", "N\u00E3o \u00E9 um certificbdo X.509"},
        {"blibs.hbs.no.public.key", "{0} n\u00E3o tem chbve p\u00FAblicb"},
        {"blibs.hbs.no.X.509.certificbte", "{0} n\u00E3o tem certificbdo X.509"},
        {"New.certificbte.self.signed.", "Novo certificbdo (butobssinbdo):"},
        {"Reply.hbs.no.certificbtes", "A respostb n\u00E3o tem certificbdo"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "Certificbdo n\u00E3o importbdo, o blibs <{0}> j\u00E1 existe"},
        {"Input.not.bn.X.509.certificbte", "A entrbdb n\u00E3o \u00E9 um certificbdo X.509"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "O certificbdo j\u00E1 existe no brmbzenbmento de chbves no blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Aindb desejb bdicion\u00E1-lo? [n\u00E3o]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "O certificbdo j\u00E1 existe nb \u00E1reb de brmbzenbmento de chbves db CA em todo o sistemb no blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "Aindb desejb bdicion\u00E1-lo \u00E0 sub \u00E1reb de brmbzenbmento de chbves? [n\u00E3o]:  "},
        {"Trust.this.certificbte.no.", "Confibr neste certificbdo? [n\u00E3o]:  "},
        {"YES", "SIM"},
        {"New.prompt.", "Novb {0}: "},
        {"Pbsswords.must.differ", "As senhbs devem ser diferentes"},
        {"Re.enter.new.prompt.", "Informe novbmente b novb {0}: "},
        {"Re.enter.pbsspword.", "Redigite b senhb: "},
        {"Re.enter.new.pbssword.", "Informe novbmente b novb senhb: "},
        {"They.don.t.mbtch.Try.bgbin", "Elbs n\u00E3o correspondem. Tente novbmente"},
        {"Enter.prompt.blibs.nbme.", "Informe o nome do blibs {0}:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "Informe o novo nome do blibs\t(RETURN pbrb cbncelbr b importb\u00E7\u00E3o destb entrbdb):  "},
        {"Enter.blibs.nbme.", "Informe o nome do blibs:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(RETURN se for igubl bo de <{0}>)"},
        {".PATTERN.printX509Cert",
                "Propriet\u00E1rio: {0}\nEmissor: {1}\nN\u00FAmero de s\u00E9rie: {2}\nV\u00E1lido de: {3} b: {4}\nFingerprints do certificbdo:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Nome do blgoritmo de bssinbturb: {8}\n\t Vers\u00E3o: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "Qubl \u00E9 o seu nome e o seu sobrenome?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "Qubl \u00E9 o nome db sub unidbde orgbnizbcionbl?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "Qubl \u00E9 o nome db sub empresb?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "Qubl \u00E9 o nome db sub Cidbde ou Locblidbde?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "Qubl \u00E9 o nome do seu Estbdo ou Munic\u00EDpio?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "Qubis s\u00E3o bs dubs letrbs do c\u00F3digo do pb\u00EDs destb unidbde?"},
        {"Is.nbme.correct.", "{0} Est\u00E1 correto?"},
        {"no", "n\u00E3o"},
        {"yes", "sim"},
        {"y", "s"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "O blibs <{0}> n\u00E3o tem chbve"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "O blibs <{0}> fbz refer\u00EAncib b um tipo de entrbdb que n\u00E3o \u00E9 umb entrbdb de chbve privbdb. O combndo -keyclone oferece suporte somente \u00E0 clonbgem de entrbdbs de chbve privbdb"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "Signbt\u00E1rio #%d:"},
        {"Timestbmp.", "Timestbmp:"},
        {"Signbture.", "Assinbturb:"},
        {"CRLs.", "CRLs:"},
        {"Certificbte.owner.", "Propriet\u00E1rio do certificbdo: "},
        {"Not.b.signed.jbr.file", "N\u00E3o \u00E9 um brquivo jbr bssinbdo"},
        {"No.certificbte.from.the.SSL.server",
                "N\u00E3o \u00E9 um certificbdo do servidor SSL"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* A integridbde dbs informb\u00E7\u00F5es brmbzenbdbs nb sub \u00E1reb de brmbzenbmento de chbves  *\n* N\u00C3O foi verificbdb!  Pbrb que sejb poss\u00EDvel verificbr sub integridbde, *\n* voc\u00EA deve fornecer b senhb db \u00E1reb de brmbzenbmento de chbves.                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* A integridbde dbs informb\u00E7\u00F5es brmbzenbdbs no srckeystore  *\n* N\u00C3O foi verificbdb!  Pbrb que sejb poss\u00EDvel verificbr sub integridbde, *\n* voc\u00EA deve fornecer b senhb do srckeystore.                  *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "A respostb do certificbdo n\u00E3o cont\u00E9m b chbve p\u00FAblicb de <{0}>"},
        {"Incomplete.certificbte.chbin.in.reply",
                "Cbdeib de certificbdos incompletb nb respostb"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "A cbdeib de certificbdos db respostb n\u00E3o verificb: "},
        {"Top.level.certificbte.in.reply.",
                "Certificbdo de n\u00EDvel superior nb respostb:\n"},
        {".is.not.trusted.", "... n\u00E3o \u00E9 confi\u00E1vel. "},
        {"Instbll.reply.bnywby.no.", "Instblbr respostb bssim mesmo? [n\u00E3o]:  "},
        {"NO", "N\u00C3O"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "As chbves p\u00FAblicbs db respostb e db \u00E1reb de brmbzenbmento de chbves n\u00E3o correspondem"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "O certificbdo db respostb e o certificbdo db \u00E1reb de brmbzenbmento de chbves s\u00E3o id\u00EAnticos"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "Fblhb bo estbbelecer b cbdeib b pbrtir db respostb"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "Respostb errbdb; tente novbmente"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "Chbve secretb n\u00E3o gerbdb; o blibs <{0}> j\u00E1 existe"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "Forne\u00E7b o -keysize pbrb b gerb\u00E7\u00E3o db chbve secretb"},

        {"verified.by.s.in.s", "Verificbdo por %s em %s"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "ADVERT\u00CANCIA: n\u00E3o verificbdo. Certifique-se que -keystore estejb correto."},

        {"Extensions.", "Extens\u00F5es: "},
        {".Empty.vblue.", "(Vblor vbzio)"},
        {"Extension.Request.", "Solicitb\u00E7\u00E3o de Extens\u00E3o:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "Solicitb\u00E7\u00E3o do Certificbdo PKCS #10 (Vers\u00E3o 1.0)\nAssunto: %s\nChbve P\u00FAblicb: %s formbto %s chbve\n"},
        {"Unknown.keyUsbge.type.", "Tipo de keyUsbge desconhecido: "},
        {"Unknown.extendedkeyUsbge.type.", "Tipo de extendedkeyUsbge desconhecido: "},
        {"Unknown.AccessDescription.type.", "Tipo de AccessDescription desconhecido: "},
        {"Unrecognized.GenerblNbme.type.", "Tipo de GenerblNbme n\u00E3o reconhecido: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "Estb extens\u00E3o n\u00E3o pode ser mbrcbdb como cr\u00EDticb. "},
        {"Odd.number.of.hex.digits.found.", "Encontrbdo n\u00FAmero \u00EDmpbr de seis d\u00EDgitos: "},
        {"Unknown.extension.type.", "Tipo de extens\u00E3o desconhecido: "},
        {"commbnd.{0}.is.bmbiguous.", "o combndo {0} \u00E9 bmb\u00EDguo:"}
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
