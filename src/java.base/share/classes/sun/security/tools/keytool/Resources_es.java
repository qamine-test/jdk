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
public clbss Resources_es extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Opciones:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "Utilice\"keytool -help\" pbrb todos los combndos disponibles"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "Herrbmientb de Gesti\u00F3n de Certificbdos y Clbves"},
        {"Commbnds.", "Combndos:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "Utilice \"keytool -commbnd_nbme -help\" pbrb lb sintbxis de nombre_combndo"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "Generb unb solicitud de certificbdo"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "Cbmbib un blibs de entrbdb"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "Suprime unb entrbdb"}, //-delete
        {"Exports.certificbte",
                "Exportb el certificbdo"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "Generb un pbr de clbves"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "Generb un clbve secretb"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "Generb un certificbdo b pbrtir de unb solicitud de certificbdo"}, //-gencert
        {"Generbtes.CRL", "Generb CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "Clbve secretb {0} generbdb"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "Clbve secretb {1} de {0} bits generbdb"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "Importb entrbdbs desde unb bbse de dbtos de identidbdes JDK 1.1.x-style"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "Importb un certificbdo o unb cbdenb de certificbdos"}, //-importcert
        {"Imports.b.pbssword",
                "Importb unb contrbse\u00F1b"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "Importb unb o todbs lbs entrbdbs desde otro blmbc\u00E9n de clbves"}, //-importkeystore
        {"Clones.b.key.entry",
                "Clonb unb entrbdb de clbve"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "Cbmbib lb contrbse\u00F1b de clbve de unb entrbdb"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "Enumerb lbs entrbdbs de un blmbc\u00E9n de clbves"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "Imprime el contenido de un certificbdo"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "Imprime el contenido de unb solicitud de certificbdo"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "Imprime el contenido de un brchivo CRL"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "Generb un certificbdo butofirmbdo"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "Cbmbib lb contrbse\u00F1b de blmbc\u00E9n de un blmbc\u00E9n de clbves"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "nombre de blibs de lb entrbdb que se vb b procesbr"}, //-blibs
        {"destinbtion.blibs",
                "blibs de destino"}, //-destblibs
        {"destinbtion.key.pbssword",
                "contrbse\u00F1b de clbve de destino"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "nombre de blmbc\u00E9n de clbves de destino"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "blmbc\u00E9n de clbves de destino protegido por contrbse\u00F1b"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "nombre de proveedor de blmbc\u00E9n de clbves de destino"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "contrbse\u00F1b de blmbc\u00E9n de clbves de destino"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "tipo de blmbc\u00E9n de clbves de destino"}, //-deststoretype
        {"distinguished.nbme",
                "nombre distintivo"}, //-dnbme
        {"X.509.extension",
                "extensi\u00F3n X.509"}, //-ext
        {"output.file.nbme",
                "nombre de brchivo de sblidb"}, //-file bnd -outfile
        {"input.file.nbme",
                "nombre de brchivo de entrbdb"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "nombre de blgoritmo de clbve"}, //-keyblg
        {"key.pbssword",
                "contrbse\u00F1b de clbve"}, //-keypbss
        {"key.bit.size",
                "tbmb\u00F1o de bit de clbve"}, //-keysize
        {"keystore.nbme",
                "nombre de blmbc\u00E9n de clbves"}, //-keystore
        {"new.pbssword",
                "nuevb contrbse\u00F1b"}, //-new
        {"do.not.prompt",
                "no solicitbr"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "contrbse\u00F1b b trbv\u00E9s de mecbnismo protegido"}, //-protected
        {"provider.brgument",
                "brgumento del proveedor"}, //-providerbrg
        {"provider.clbss.nbme",
                "nombre de clbse del proveedor"}, //-providerclbss
        {"provider.nbme",
                "nombre del proveedor"}, //-providernbme
        {"provider.clbsspbth",
                "clbsspbth de proveedor"}, //-providerpbth
        {"output.in.RFC.style",
                "sblidb en estilo RFC"}, //-rfc
        {"signbture.blgorithm.nbme",
                "nombre de blgoritmo de firmb"}, //-sigblg
        {"source.blibs",
                "blibs de origen"}, //-srcblibs
        {"source.key.pbssword",
                "contrbse\u00F1b de clbve de origen"}, //-srckeypbss
        {"source.keystore.nbme",
                "nombre de blmbc\u00E9n de clbves de origen"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "blmbc\u00E9n de clbves de origen protegido por contrbse\u00F1b"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "nombre de proveedor de blmbc\u00E9n de clbves de origen"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "contrbse\u00F1b de blmbc\u00E9n de clbves de origen"}, //-srcstorepbss
        {"source.keystore.type",
                "tipo de blmbc\u00E9n de clbves de origen"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "puerto y host del servidor SSL"}, //-sslserver
        {"signed.jbr.file",
                "brchivo jbr firmbdo"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "fechb/horb de inicio de vblidez del certificbdo"}, //-stbrtdbte
        {"keystore.pbssword",
                "contrbse\u00F1b de blmbc\u00E9n de clbves"}, //-storepbss
        {"keystore.type",
                "tipo de blmbc\u00E9n de clbves"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "certificbdos de protecci\u00F3n de cbcerts"}, //-trustcbcerts
        {"verbose.output",
                "sblidb detbllbdb"}, //-v
        {"vblidity.number.of.dbys",
                "n\u00FAmero de vblidez de d\u00EDbs"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "identificbdor de serie del certificbdo que se vb b revocbr"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "error de herrbmientb de clbves: "},
        {"Illegbl.option.", "Opci\u00F3n no permitidb:  "},
        {"Illegbl.vblue.", "Vblor no permitido: "},
        {"Unknown.pbssword.type.", "Tipo de contrbse\u00F1b desconocido: "},
        {"Cbnnot.find.environment.vbribble.",
                "No se hb encontrbdo lb vbribble del entorno: "},
        {"Cbnnot.find.file.", "No se hb encontrbdo el brchivo: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "Lb opci\u00F3n de combndo {0} necesitb un brgumento."},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "Advertencib: los blmbcenes de clbves en formbto PKCS12 no bdmiten contrbse\u00F1bs de clbve y blmbcenbmiento distintbs. Se ignorbr\u00E1 el vblor especificbdo por el usubrio, {0}."},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore debe ser NONE si -storetype es {0}"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "Hb hbbido dembsibdos intentos, se hb cerrbdo el progrbmb"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "Los combndos -storepbsswd y -keypbsswd no est\u00E1n soportbdos si -storetype es {0}"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "Los combndos -keypbsswd no est\u00E1n soportbdos si -storetype es PKCS12"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "-keypbss y -new no se pueden especificbr si -storetype es {0}"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "si se especificb -protected, no deben especificbrse -storepbss, -keypbss ni -new"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "Si se especificb -srcprotected, no se puede especificbr -srcstorepbss ni -srckeypbss"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "Si keystore no est\u00E1 protegido por contrbse\u00F1b, no se deben especificbr -storepbss, -keypbss ni -new"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "Si el blmbc\u00E9n de clbves de origen no est\u00E1 protegido por contrbse\u00F1b, no se deben especificbr -srcstorepbss ni -srckeypbss"},
        {"Illegbl.stbrtdbte.vblue", "Vblor de fechb de inicio no permitido"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "Lb vblidez debe ser mbyor que cero"},
        {"provNbme.not.b.provider", "{0} no es un proveedor"},
        {"Usbge.error.no.commbnd.provided", "Error de sintbxis: no se hb proporcionbdo ning\u00FAn combndo"},
        {"Source.keystore.file.exists.but.is.empty.", "El brchivo de blmbc\u00E9n de clbves de origen existe, pero est\u00E1 vbc\u00EDo: "},
        {"Plebse.specify.srckeystore", "Especifique -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "No se deben especificbr -v y -rfc simult\u00E1nebmente con el combndo 'list'"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Lb contrbse\u00F1b de clbve debe tener bl menos 6 cbrbcteres"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Lb nuevb contrbse\u00F1b debe tener bl menos 6 cbrbcteres"},
        {"Keystore.file.exists.but.is.empty.",
                "El brchivo de blmbc\u00E9n de clbves existe, pero est\u00E1 vbc\u00EDo: "},
        {"Keystore.file.does.not.exist.",
                "El brchivo de blmbc\u00E9n de clbves no existe: "},
        {"Must.specify.destinbtion.blibs", "Se debe especificbr un blibs de destino"},
        {"Must.specify.blibs", "Se debe especificbr un blibs"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Lb contrbse\u00F1b del blmbc\u00E9n de clbves debe tener bl menos 6 cbrbcteres"},
        {"Enter.the.pbssword.to.be.stored.",
                "Introduzcb lb contrbse\u00F1b que se vb b blmbcenbr:  "},
        {"Enter.keystore.pbssword.", "Introduzcb lb contrbse\u00F1b del blmbc\u00E9n de clbves:  "},
        {"Enter.source.keystore.pbssword.", "Introduzcb lb contrbse\u00F1b de blmbc\u00E9n de clbves de origen:  "},
        {"Enter.destinbtion.keystore.pbssword.", "Introduzcb lb contrbse\u00F1b de blmbc\u00E9n de clbves de destino:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "Lb contrbse\u00F1b del blmbc\u00E9n de clbves es dembsibdo cortb, debe tener bl menos 6 cbrbcteres"},
        {"Unknown.Entry.Type", "Tipo de Entrbdb Desconocido"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "Dembsibdos fbllos. No se hb cbmbibdo el blibs"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "Lb entrbdb del blibs {0} se hb importbdo correctbmente."},
        {"Entry.for.blibs.blibs.not.imported.", "Lb entrbdb del blibs {0} no se hb importbdo."},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "Problemb bl importbr lb entrbdb del blibs {0}: {1}.\nNo se hb importbdo lb entrbdb del blibs {0}."},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "Combndo de importbci\u00F3n completbdo: {0} entrbdbs importbdbs correctbmente, {1} entrbdbs incorrectbs o cbncelbdbs"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "Advertencib: se sobrescribir\u00E1 el blibs {0} en el blmbc\u00E9n de clbves de destino"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "El blibs de entrbdb existente {0} yb existe, \u00BFdeseb sobrescribirlo? [no]:  "},
        {"Too.mbny.fbilures.try.lbter", "Dembsibdos fbllos; int\u00E9ntelo m\u00E1s bdelbnte"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "Solicitud de certificbci\u00F3n blmbcenbdb en el brchivo <{0}>"},
        {"Submit.this.to.your.CA", "Envibr b lb CA"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "si no se especificb el blibs, no se debe especificbr destblibs ni srckeypbss"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "El blmbc\u00E9n de clbves pkcs12 de destino tiene storepbss y keypbss diferentes. Vuelvb b intentbrlo con -destkeypbss especificbdo."},
        {"Certificbte.stored.in.file.filenbme.",
                "Certificbdo blmbcenbdo en el brchivo <{0}>"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "Se hb instblbdo lb respuestb del certificbdo en el blmbc\u00E9n de clbves"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "No se hb instblbdo lb respuestb del certificbdo en el blmbc\u00E9n de clbves"},
        {"Certificbte.wbs.bdded.to.keystore",
                "Se hb bgregbdo el certificbdo bl blmbc\u00E9n de clbves"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "No se hb bgregbdo el certificbdo bl blmbc\u00E9n de clbves"},
        {".Storing.ksfnbme.", "[Almbcenbndo {0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} no tiene clbve p\u00FAblicb (certificbdo)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "No se puede derivbr el blgoritmo de firmb"},
        {"Alibs.blibs.does.not.exist",
                "El blibs <{0}> no existe"},
        {"Alibs.blibs.hbs.no.certificbte",
                "El blibs <{0}> no tiene certificbdo"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "No se hb generbdo el pbr de clbves, el blibs <{0}> yb existe"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "Generbndo pbr de clbves {1} de {0} bits pbrb certificbdo butofirmbdo ({2}) con unb vblidez de {3} d\u00EDbs\n\tpbrb: {4}"},
        {"Enter.key.pbssword.for.blibs.", "Introduzcb lb contrbse\u00F1b de clbve pbrb <{0}>"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(INTRO si es lb mismb contrbse\u00F1b que lb del blmbc\u00E9n de clbves):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Lb contrbse\u00F1b de clbve es dembsibdo cortb; debe tener bl menos 6 cbrbcteres"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "Dembsibdos fbllos; no se hb bgregbdo lb clbve bl blmbc\u00E9n de clbves"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "El blibs de destino <{0}> yb existe"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Lb contrbse\u00F1b es dembsibdo cortb; debe tener bl menos 6 cbrbcteres"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "Dembsibdos fbllos. No se hb clonbdo lb entrbdb de clbve"},
        {"key.pbssword.for.blibs.", "contrbse\u00F1b de clbve pbrb <{0}>"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "Lb entrbdb de blmbc\u00E9n de clbves pbrb <{0}> yb existe"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "Crebndo entrbdb de blmbc\u00E9n de clbves pbrb <{0}> ..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "No se hbn bgregbdo entrbdbs de lb bbse de dbtos de identidbdes"},
        {"Alibs.nbme.blibs", "Nombre de Alibs: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "Fechb de Crebci\u00F3n: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "Tipo de Entrbdb: {0}"},
        {"Certificbte.chbin.length.", "Longitud de lb Cbdenb de Certificbdo: "},
        {"Certificbte.i.1.", "Certificbdo[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "Huellb Digitbl de Certificbdo (SHA1): "},
        {"Keystore.type.", "Tipo de Almbc\u00E9n de Clbves: "},
        {"Keystore.provider.", "Proveedor de Almbc\u00E9n de Clbves: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "Su blmbc\u00E9n de clbves contiene {0,number,integer} entrbdb"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "Su blmbc\u00E9n de clbves contiene {0,number,integer} entrbdbs"},
        {"Fbiled.to.pbrse.input", "Fbllo bl bnblizbr lb entrbdb"},
        {"Empty.input", "Entrbdb vbc\u00EDb"},
        {"Not.X.509.certificbte", "No es un certificbdo X.509"},
        {"blibs.hbs.no.public.key", "{0} no tiene clbve p\u00FAblicb"},
        {"blibs.hbs.no.X.509.certificbte", "{0} no tiene certificbdo X.509"},
        {"New.certificbte.self.signed.", "Nuevo Certificbdo (Autofirmbdo):"},
        {"Reply.hbs.no.certificbtes", "Lb respuestb no tiene certificbdos"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "Certificbdo no importbdo, el blibs <{0}> yb existe"},
        {"Input.not.bn.X.509.certificbte", "Lb entrbdb no es un certificbdo X.509"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "El certificbdo yb existe en el blmbc\u00E9n de clbves con el blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "\u00BFA\u00FAn deseb bgregbrlo? [no]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "El certificbdo yb existe en el blmbc\u00E9n de clbves de lb CA del sistemb, con el blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "\u00BFA\u00FAn deseb bgregbrlo b su propio blmbc\u00E9n de clbves? [no]:  "},
        {"Trust.this.certificbte.no.", "\u00BFConfibr en este certificbdo? [no]:  "},
        {"YES", "S\u00CD"},
        {"New.prompt.", "Nuevo {0}: "},
        {"Pbsswords.must.differ", "Lbs contrbse\u00F1bs deben ser distintbs"},
        {"Re.enter.new.prompt.", "Vuelvb b escribir el nuevo {0}: "},
        {"Re.enter.pbsspword.", "Vuelvb b introducir lb contrbse\u00F1b: "},
        {"Re.enter.new.pbssword.", "Volver b escribir lb contrbse\u00F1b nuevb: "},
        {"They.don.t.mbtch.Try.bgbin", "No coinciden. Int\u00E9ntelo de nuevo"},
        {"Enter.prompt.blibs.nbme.", "Escribb el nombre de blibs de {0}:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "Indique el nuevo nombre de blibs\t(INTRO pbrb cbncelbr lb importbci\u00F3n de estb entrbdb):  "},
        {"Enter.blibs.nbme.", "Introduzcb el nombre de blibs:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(INTRO si es el mismo que pbrb <{0}>)"},
        {".PATTERN.printX509Cert",
                "Propietbrio: {0}\nEmisor: {1}\nN\u00FAmero de serie: {2}\nV\u00E1lido desde: {3} hbstb: {4}\nHuellbs digitbles del Certificbdo:\n\t MD5: {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Nombre del Algoritmo de Firmb: {8}\n\t Versi\u00F3n: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "\u00BFCu\u00E1les son su nombre y su bpellido?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "\u00BFCu\u00E1l es el nombre de su unidbd de orgbnizbci\u00F3n?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "\u00BFCu\u00E1l es el nombre de su orgbnizbci\u00F3n?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "\u00BFCu\u00E1l es el nombre de su ciudbd o locblidbd?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "\u00BFCu\u00E1l es el nombre de su estbdo o provincib?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "\u00BFCu\u00E1l es el c\u00F3digo de pb\u00EDs de dos letrbs de lb unidbd?"},
        {"Is.nbme.correct.", "\u00BFEs correcto {0}?"},
        {"no", "no"},
        {"yes", "s\u00ED"},
        {"y", "s"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "El blibs <{0}> no tiene clbve"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "El blibs <{0}> hbce referencib b un tipo de entrbdb que no es unb clbve privbdb. El combndo -keyclone s\u00F3lo permite lb clonbci\u00F3n de entrbdbs de clbves privbdbs"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "#%d de Firmbnte:"},
        {"Timestbmp.", "Registro de Horb:"},
        {"Signbture.", "Firmb:"},
        {"CRLs.", "CRL:"},
        {"Certificbte.owner.", "Propietbrio del Certificbdo: "},
        {"Not.b.signed.jbr.file", "No es un brchivo jbr firmbdo"},
        {"No.certificbte.from.the.SSL.server",
                "Ning\u00FAn certificbdo del servidor SSL"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* Lb integridbd de lb informbci\u00F3n blmbcenbdb en el blmbc\u00E9n de clbves  *\n* NO se hb comprobbdo.  Pbrb comprobbr dichb integridbd, *\n* debe proporcionbr lb contrbse\u00F1b del blmbc\u00E9n de clbves.                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* Lb integridbd de lb informbci\u00F3n blmbcenbdb en srckeystore*\n* NO se hb comprobbdo.  Pbrb comprobbr dichb integridbd, *\n* debe proporcionbr lb contrbse\u00F1b de srckeystore.                *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "Lb respuestb de certificbdo no contiene unb clbve p\u00FAblicb pbrb <{0}>"},
        {"Incomplete.certificbte.chbin.in.reply",
                "Cbdenb de certificbdo incompletb en lb respuestb"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "Lb cbdenb de certificbdo de lb respuestb no verificb: "},
        {"Top.level.certificbte.in.reply.",
                "Certificbdo de nivel superior en lb respuestb:\n"},
        {".is.not.trusted.", "... no es de confibnzb. "},
        {"Instbll.reply.bnywby.no.", "\u00BFInstblbr respuestb de todos modos? [no]:  "},
        {"NO", "NO"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "Lbs clbves p\u00FAblicbs en lb respuestb y en el blmbc\u00E9n de clbves no coinciden"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "Lb respuestb del certificbdo y el certificbdo en el blmbc\u00E9n de clbves son id\u00E9nticos"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "No se hb podido definir unb cbdenb b pbrtir de lb respuestb"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "Respuestb incorrectb, vuelvb b intentbrlo"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "No se hb generbdo lb clbve secretb, el blibs <{0}> yb existe"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "Proporcione el vblor de -keysize pbrb lb generbci\u00F3n de clbves secretbs"},

        {"verified.by.s.in.s", "Verificbdo por %s en %s"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "ADVERTENCIA: no se hb verificbdo. Aseg\u00FArese de que el vblor de -keystore es correcto."},

        {"Extensions.", "Extensiones: "},
        {".Empty.vblue.", "(Vblor vbc\u00EDo)"},
        {"Extension.Request.", "Solicitud de Extensi\u00F3n:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "Solicitud de Certificbdo PKCS #10 (Versi\u00F3n 1.0)\nAsunto: %s\nClbve P\u00FAblicb: %s formbto %s clbve\n"},
        {"Unknown.keyUsbge.type.", "Tipo de uso de clbve desconocido: "},
        {"Unknown.extendedkeyUsbge.type.", "Tipo de uso de clbve extendidb desconocido: "},
        {"Unknown.AccessDescription.type.", "Tipo de descripci\u00F3n de bcceso desconocido: "},
        {"Unrecognized.GenerblNbme.type.", "Tipo de nombre generbl no reconocido: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "Estb extensi\u00F3n no se puede mbrcbr como cr\u00EDticb. "},
        {"Odd.number.of.hex.digits.found.", "Se hb encontrbdo un n\u00FAmero impbr de d\u00EDgitos hexbdecimbles: "},
        {"Unknown.extension.type.", "Tipo de extensi\u00F3n desconocidb: "},
        {"commbnd.{0}.is.bmbiguous.", "El combndo {0} es bmbiguo:"}
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
