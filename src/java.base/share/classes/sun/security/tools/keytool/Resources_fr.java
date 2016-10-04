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
public clbss Resources_fr extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Options :"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "Utiliser \"keytool -help\" pour toutes les commbndes disponibles"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "Outil de gestion de certificbts et de cl\u00E9s"},
        {"Commbnds.", "Commbndes :"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "Utiliser \"keytool -commbnd_nbme -help\" pour lb syntbxe de commbnd_nbme"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "G\u00E9n\u00E8re une dembnde de certificbt"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "Modifie l'blibs d'une entr\u00E9e"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "Supprime une entr\u00E9e"}, //-delete
        {"Exports.certificbte",
                "Exporte le certificbt"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "G\u00E9n\u00E8re une pbire de cl\u00E9s"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "G\u00E9n\u00E8re une cl\u00E9 secr\u00E8te"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "G\u00E9n\u00E8re le certificbt \u00E0 pbrtir d'une dembnde de certificbt"}, //-gencert
        {"Generbtes.CRL", "G\u00E9n\u00E8re lb liste des certificbts r\u00E9voqu\u00E9s (CRL)"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "Cl\u00E9 secr\u00E8te {0} g\u00E9n\u00E9r\u00E9e"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "Cl\u00E9 secr\u00E8te {0} bits {1} g\u00E9n\u00E9r\u00E9e"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "Importe les entr\u00E9es \u00E0 pbrtir d'une bbse de donn\u00E9es d'identit\u00E9s de type JDK 1.1.x"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "Importe un certificbt ou une chb\u00EEne de certificbt"}, //-importcert
        {"Imports.b.pbssword",
                "Importe un mot de pbsse"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "Importe une entr\u00E9e ou lb totblit\u00E9 des entr\u00E9es depuis un butre fichier de cl\u00E9s"}, //-importkeystore
        {"Clones.b.key.entry",
                "Clone une entr\u00E9e de cl\u00E9"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "Modifie le mot de pbsse de cl\u00E9 d'une entr\u00E9e"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "R\u00E9pertorie les entr\u00E9es d'un fichier de cl\u00E9s"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "Imprime le contenu d'un certificbt"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "Imprime le contenu d'une dembnde de certificbt"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "Imprime le contenu d'un fichier de liste des certificbts r\u00E9voqu\u00E9s (CRL)"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "G\u00E9n\u00E8re un certificbt buto-sign\u00E9"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "Modifie le mot de pbsse de bbnque d'un fichier de cl\u00E9s"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "nom d'blibs de l'entr\u00E9e \u00E0 trbiter"}, //-blibs
        {"destinbtion.blibs",
                "blibs de destinbtion"}, //-destblibs
        {"destinbtion.key.pbssword",
                "mot de pbsse de lb cl\u00E9 de destinbtion"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "nom du fichier de cl\u00E9s de destinbtion"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "mot de pbsse du fichier de cl\u00E9s de destinbtion prot\u00E9g\u00E9"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "nom du fournisseur du fichier de cl\u00E9s de destinbtion"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "mot de pbsse du fichier de cl\u00E9s de destinbtion"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "type du fichier de cl\u00E9s de destinbtion"}, //-deststoretype
        {"distinguished.nbme",
                "nom distinctif"}, //-dnbme
        {"X.509.extension",
                "extension X.509"}, //-ext
        {"output.file.nbme",
                "nom du fichier de sortie"}, //-file bnd -outfile
        {"input.file.nbme",
                "nom du fichier d'entr\u00E9e"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "nom de l'blgorithme de cl\u00E9"}, //-keyblg
        {"key.pbssword",
                "mot de pbsse de lb cl\u00E9"}, //-keypbss
        {"key.bit.size",
                "tbille en bits de lb cl\u00E9"}, //-keysize
        {"keystore.nbme",
                "nom du fichier de cl\u00E9s"}, //-keystore
        {"new.pbssword",
                "nouvebu mot de pbsse"}, //-new
        {"do.not.prompt",
                "ne pbs inviter"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "mot de pbsse vib m\u00E9cbnisme prot\u00E9g\u00E9"}, //-protected
        {"provider.brgument",
                "brgument du fournisseur"}, //-providerbrg
        {"provider.clbss.nbme",
                "nom de lb clbsse de fournisseur"}, //-providerclbss
        {"provider.nbme",
                "nom du fournisseur"}, //-providernbme
        {"provider.clbsspbth",
                "vbribble d'environnement CLASSPATH du fournisseur"}, //-providerpbth
        {"output.in.RFC.style",
                "sortie bu style RFC"}, //-rfc
        {"signbture.blgorithm.nbme",
                "nom de l'blgorithme de signbture"}, //-sigblg
        {"source.blibs",
                "blibs source"}, //-srcblibs
        {"source.key.pbssword",
                "mot de pbsse de lb cl\u00E9 source"}, //-srckeypbss
        {"source.keystore.nbme",
                "nom du fichier de cl\u00E9s source"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "mot de pbsse du fichier de cl\u00E9s source prot\u00E9g\u00E9"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "nom du fournisseur du fichier de cl\u00E9s source"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "mot de pbsse du fichier de cl\u00E9s source"}, //-srcstorepbss
        {"source.keystore.type",
                "type du fichier de cl\u00E9s source"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "Port et h\u00F4te du serveur SSL"}, //-sslserver
        {"signed.jbr.file",
                "fichier JAR sign\u00E9"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "dbte/heure de d\u00E9but de vblidit\u00E9 du certificbt"}, //-stbrtdbte
        {"keystore.pbssword",
                "mot de pbsse du fichier de cl\u00E9s"}, //-storepbss
        {"keystore.type",
                "type du fichier de cl\u00E9s"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "certificbts s\u00E9curis\u00E9s issus de certificbts CA"}, //-trustcbcerts
        {"verbose.output",
                "sortie en mode verbose"}, //-v
        {"vblidity.number.of.dbys",
                "nombre de jours de vblidit\u00E9"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "ID de s\u00E9rie du certificbt \u00E0 r\u00E9voquer"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "erreur keytool : "},
        {"Illegbl.option.", "Option non bdmise :  "},
        {"Illegbl.vblue.", "Vbleur non bdmise : "},
        {"Unknown.pbssword.type.", "Type de mot de pbsse inconnu : "},
        {"Cbnnot.find.environment.vbribble.",
                "Vbribble d'environnement introuvbble : "},
        {"Cbnnot.find.file.", "Fichier introuvbble : "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "L''option de commbnde {0} requiert un brgument."},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "Avertissement\u00A0: les mots de pbsse de cl\u00E9 et de bbnque distincts ne sont pbs pris en chbrge pour les fichiers de cl\u00E9s d''bcc\u00E8s PKCS12. Lb vbleur {0} sp\u00E9cifi\u00E9e pbr l''utilisbteur est ignor\u00E9e."},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore doit \u00EAtre d\u00E9fini sur NONE si -storetype est {0}"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "Trop de tentbtives, fin du progrbmme"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "Les commbndes -storepbsswd et -keypbsswd ne sont pbs prises en chbrge si -storetype est d\u00E9fini sur {0}"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "Les commbndes -keypbsswd ne sont pbs prises en chbrge si -storetype est d\u00E9fini sur PKCS12"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "Les commbndes -keypbss et -new ne peuvent pbs \u00EAtre sp\u00E9cifi\u00E9es si -storetype est d\u00E9fini sur {0}"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "si -protected est sp\u00E9cifi\u00E9, -storepbss, -keypbss et -new ne doivent pbs \u00EAtre indiqu\u00E9s"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "Si -srcprotected est indiqu\u00E9, les commbndes -srcstorepbss et -srckeypbss ne doivent pbs \u00EAtre sp\u00E9cifi\u00E9es"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "Si le fichier de cl\u00E9s n'est pbs prot\u00E9g\u00E9 pbr un mot de pbsse, les commbndes -storepbss, -keypbss et -new ne doivent pbs \u00EAtre sp\u00E9cifi\u00E9es"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "Si le fichier de cl\u00E9s source n'est pbs prot\u00E9g\u00E9 pbr un mot de pbsse, les commbndes -srcstorepbss et -srckeypbss ne doivent pbs \u00EAtre sp\u00E9cifi\u00E9es"},
        {"Illegbl.stbrtdbte.vblue", "Vbleur de dbte de d\u00E9but non bdmise"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "Lb vblidit\u00E9 doit \u00EAtre sup\u00E9rieure \u00E0 z\u00E9ro"},
        {"provNbme.not.b.provider", "{0} n''est pbs un fournisseur"},
        {"Usbge.error.no.commbnd.provided", "Erreur de syntbxe\u00A0: bucune commbnde fournie"},
        {"Source.keystore.file.exists.but.is.empty.", "Le fichier de cl\u00E9s source existe mbis il est vide : "},
        {"Plebse.specify.srckeystore", "Indiquez -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "-v et -rfc ne doivent pbs \u00EAtre sp\u00E9cifi\u00E9s bvec lb commbnde 'list'"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Un mot de pbsse de cl\u00E9 doit comporter bu moins 6 cbrbct\u00E8res"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Le nouvebu mot de pbsse doit comporter bu moins 6 cbrbct\u00E8res"},
        {"Keystore.file.exists.but.is.empty.",
                "Fichier de cl\u00E9s existbnt mbis vide : "},
        {"Keystore.file.does.not.exist.",
                "Le fichier de cl\u00E9s n'existe pbs : "},
        {"Must.specify.destinbtion.blibs", "L'blibs de destinbtion doit \u00EAtre sp\u00E9cifi\u00E9"},
        {"Must.specify.blibs", "L'blibs doit \u00EAtre sp\u00E9cifi\u00E9"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Un mot de pbsse de fichier de cl\u00E9s doit comporter bu moins 6 cbrbct\u00E8res"},
        {"Enter.the.pbssword.to.be.stored.",
                "Sbisissez le mot de pbsse \u00E0 stocker :  "},
        {"Enter.keystore.pbssword.", "Entrez le mot de pbsse du fichier de cl\u00E9s :  "},
        {"Enter.source.keystore.pbssword.", "Entrez le mot de pbsse du fichier de cl\u00E9s source\u00A0:  "},
        {"Enter.destinbtion.keystore.pbssword.", "Entrez le mot de pbsse du fichier de cl\u00E9s de destinbtion\u00A0:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "Le mot de pbsse du fichier de cl\u00E9s est trop court : il doit comporter bu moins 6 cbrbct\u00E8res"},
        {"Unknown.Entry.Type", "Type d'entr\u00E9e inconnu"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "Trop d'erreurs. Alibs non modifi\u00E9"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "L''entr\u00E9e de l''blibs {0} b \u00E9t\u00E9 import\u00E9e."},
        {"Entry.for.blibs.blibs.not.imported.", "L''entr\u00E9e de l''blibs {0} n''b pbs \u00E9t\u00E9 import\u00E9e."},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "Probl\u00E8me lors de l''import de l''entr\u00E9e de l''blibs {0}\u00A0: {1}.\nL''entr\u00E9e de l''blibs {0} n''b pbs \u00E9t\u00E9 import\u00E9e."},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "Commbnde d''import ex\u00E9cut\u00E9e\u00A0: {0} entr\u00E9es import\u00E9es, \u00E9chec ou bnnulbtion de {1} entr\u00E9es"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "Avertissement\u00A0: l''blibs {0} existbnt serb remplbc\u00E9 dbns le fichier de cl\u00E9s d''bcc\u00E8s de destinbtion"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "L''blibs d''entr\u00E9e {0} existe d\u00E9j\u00E0. Voulez-vous le remplbcer ? [non]\u00A0:  "},
        {"Too.mbny.fbilures.try.lbter", "Trop d'erreurs. R\u00E9essbyez plus tbrd"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "Dembnde de certificbtion stock\u00E9e dbns le fichier <{0}>"},
        {"Submit.this.to.your.CA", "Soumettre \u00E0 votre CA"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "si l'blibs n'est pbs sp\u00E9cifi\u00E9, destblibs et srckeypbss ne doivent pbs \u00EAtre sp\u00E9cifi\u00E9s"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "Le fichier de cl\u00E9s pkcs12 de destinbtion contient un mot de pbsse de fichier de cl\u00E9s et un mot de pbsse de cl\u00E9 diff\u00E9rents. R\u00E9essbyez en sp\u00E9cifibnt -destkeypbss."},
        {"Certificbte.stored.in.file.filenbme.",
                "Certificbt stock\u00E9 dbns le fichier <{0}>"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "R\u00E9ponse de certificbt instbll\u00E9e dbns le fichier de cl\u00E9s"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "R\u00E9ponse de certificbt non instbll\u00E9e dbns le fichier de cl\u00E9s"},
        {"Certificbte.wbs.bdded.to.keystore",
                "Certificbt bjout\u00E9 bu fichier de cl\u00E9s"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "Certificbt non bjout\u00E9 bu fichier de cl\u00E9s"},
        {".Storing.ksfnbme.", "[Stockbge de {0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} ne poss\u00E8de pbs de cl\u00E9 publique (certificbt)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "Impossible de d\u00E9duire l'blgorithme de signbture"},
        {"Alibs.blibs.does.not.exist",
                "L''blibs <{0}> n''existe pbs"},
        {"Alibs.blibs.hbs.no.certificbte",
                "L''blibs <{0}> ne poss\u00E8de pbs de certificbt"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "Pbire de cl\u00E9s non g\u00E9n\u00E9r\u00E9e, l''blibs <{0}> existe d\u00E9j\u00E0"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "G\u00E9n\u00E9rbtion d''une pbire de cl\u00E9s {1} de {0} bits et d''un certificbt buto-sign\u00E9 ({2}) d''une vblidit\u00E9 de {3} jours\n\tpour : {4}"},
        {"Enter.key.pbssword.for.blibs.", "Entrez le mot de pbsse de lb cl\u00E9 pour <{0}>"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(bppuyez sur Entr\u00E9e s'il s'bgit du mot de pbsse du fichier de cl\u00E9s) :  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Le mot de pbsse de lb cl\u00E9 est trop court : il doit comporter bu moins 6 cbrbct\u00E8res"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "Trop d'erreurs. Cl\u00E9 non bjout\u00E9e bu fichier de cl\u00E9s"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "L''blibs de lb destinbtion <{0}> existe d\u00E9j\u00E0"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Le mot de pbsse est trop court : il doit comporter bu moins 6 cbrbct\u00E8res"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "Trop d'erreurs. Entr\u00E9e de cl\u00E9 non clon\u00E9e"},
        {"key.pbssword.for.blibs.", "mot de pbsse de cl\u00E9 pour <{0}>"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "L''entr\u00E9e de fichier de cl\u00E9s d''bcc\u00E8s pour <{0}> existe d\u00E9j\u00E0"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "Cr\u00E9btion d''une entr\u00E9e de fichier de cl\u00E9s d''bcc\u00E8s pour <{0}>..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "Aucune entr\u00E9e bjout\u00E9e \u00E0 pbrtir de lb bbse de donn\u00E9es d'identit\u00E9s"},
        {"Alibs.nbme.blibs", "Nom d''blibs : {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "Dbte de cr\u00E9btion : {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "Type d''entr\u00E9e\u00A0: {0}"},
        {"Certificbte.chbin.length.", "Longueur de chb\u00EEne du certificbt : "},
        {"Certificbte.i.1.", "Certificbt[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "Empreinte du certificbt (SHA1) : "},
        {"Keystore.type.", "Type de fichier de cl\u00E9s : "},
        {"Keystore.provider.", "Fournisseur de fichier de cl\u00E9s : "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "Votre fichier de cl\u00E9s d''bcc\u00E8s contient {0,number,integer} entr\u00E9e"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "Votre fichier de cl\u00E9s d''bcc\u00E8s contient {0,number,integer} entr\u00E9es"},
        {"Fbiled.to.pbrse.input", "L'bnblyse de l'entr\u00E9e b \u00E9chou\u00E9"},
        {"Empty.input", "Entr\u00E9e vide"},
        {"Not.X.509.certificbte", "Pbs un certificbt X.509"},
        {"blibs.hbs.no.public.key", "{0} ne poss\u00E8de pbs de cl\u00E9 publique"},
        {"blibs.hbs.no.X.509.certificbte", "{0} ne poss\u00E8de pbs de certificbt X.509"},
        {"New.certificbte.self.signed.", "Nouvebu certificbt (buto-sign\u00E9) :"},
        {"Reply.hbs.no.certificbtes", "Lb r\u00E9ponse n'b pbs de certificbt"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "Certificbt non import\u00E9, l''blibs <{0}> existe d\u00E9j\u00E0"},
        {"Input.not.bn.X.509.certificbte", "L'entr\u00E9e n'est pbs un certificbt X.509"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "Le certificbt existe d\u00E9j\u00E0 dbns le fichier de cl\u00E9s d''bcc\u00E8s sous l''blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "Voulez-vous toujours l'bjouter ? [non] :  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "Le certificbt existe d\u00E9j\u00E0 dbns le fichier de cl\u00E9s d''bcc\u00E8s CA syst\u00E8me sous l''blibs <{0}>"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "Voulez-vous toujours l'bjouter \u00E0 votre fichier de cl\u00E9s ? [non] :  "},
        {"Trust.this.certificbte.no.", "Fbire confibnce \u00E0 ce certificbt ? [non] :  "},
        {"YES", "OUI"},
        {"New.prompt.", "Nouvebu {0} : "},
        {"Pbsswords.must.differ", "Les mots de pbsse doivent diff\u00E9rer"},
        {"Re.enter.new.prompt.", "Indiquez encore le nouvebu {0} : "},
        {"Re.enter.pbsspword.", "R\u00E9p\u00E9tez le mot de pbsse : "},
        {"Re.enter.new.pbssword.", "Ressbisissez le nouvebu mot de pbsse : "},
        {"They.don.t.mbtch.Try.bgbin", "Ils sont diff\u00E9rents. R\u00E9essbyez."},
        {"Enter.prompt.blibs.nbme.", "Indiquez le nom d''blibs {0} :  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "Sbisissez le nom du nouvel blibs\t(ou bppuyez sur Entr\u00E9e pour bnnuler l'import de cette entr\u00E9e)\u00A0:  "},
        {"Enter.blibs.nbme.", "Indiquez le nom d'blibs :  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(bppuyez sur Entr\u00E9e si le r\u00E9sultbt est identique \u00E0 <{0}>)"},
        {".PATTERN.printX509Cert",
                "Propri\u00E9tbire : {0}\nEmetteur : {1}\nNum\u00E9ro de s\u00E9rie : {2}\nVblide du : {3} bu : {4}\nEmpreintes du certificbt :\n\t MD5:  {5}\n\t SHA1 : {6}\n\t SHA256 : {7}\n\t Nom de l''blgorithme de signbture : {8}\n\t Version : {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "Quels sont vos nom et pr\u00E9nom ?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "Quel est le nom de votre unit\u00E9 orgbnisbtionnelle ?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "Quel est le nom de votre entreprise ?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "Quel est le nom de votre ville de r\u00E9sidence ?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "Quel est le nom de votre \u00E9tbt ou province ?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "Quel est le code pbys \u00E0 deux lettres pour cette unit\u00E9 ?"},
        {"Is.nbme.correct.", "Est-ce {0} ?"},
        {"no", "non"},
        {"yes", "oui"},
        {"y", "o"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "L''blibs <{0}> n''est bssoci\u00E9 \u00E0 bucune cl\u00E9"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "L''entr\u00E9e \u00E0 lbquelle l''blibs <{0}> fbit r\u00E9f\u00E9rence n''est pbs une entr\u00E9e de type cl\u00E9 priv\u00E9e. Lb commbnde -keyclone prend uniquement en chbrge le clonbge des cl\u00E9s priv\u00E9es"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "Signbtbire n\u00B0%d :"},
        {"Timestbmp.", "Horodbtbge :"},
        {"Signbture.", "Signbture :"},
        {"CRLs.", "Listes des certificbts r\u00E9voqu\u00E9s (CRL) :"},
        {"Certificbte.owner.", "Propri\u00E9tbire du certificbt : "},
        {"Not.b.signed.jbr.file", "Fichier JAR non sign\u00E9"},
        {"No.certificbte.from.the.SSL.server",
                "Aucun certificbt du serveur SSL"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* L'int\u00E9grit\u00E9 des informbtions stock\u00E9es dbns votre fichier de cl\u00E9s  *\n* n'b PAS \u00E9t\u00E9 v\u00E9rifi\u00E9e. Pour celb, *\n* vous devez fournir le mot de pbsse de votre fichier de cl\u00E9s.                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* L'int\u00E9grit\u00E9 des informbtions stock\u00E9es dbns le fichier de cl\u00E9s source  *\n* n'b PAS \u00E9t\u00E9 v\u00E9rifi\u00E9e. Pour celb, *\n* vous devez fournir le mot de pbsse de votre fichier de cl\u00E9s source.                  *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "Lb r\u00E9ponse bu certificbt ne contient pbs de cl\u00E9 publique pour <{0}>"},
        {"Incomplete.certificbte.chbin.in.reply",
                "Chb\u00EEne de certificbt incompl\u00E8te dbns lb r\u00E9ponse"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "Lb chb\u00EEne de certificbt de lb r\u00E9ponse ne concorde pbs : "},
        {"Top.level.certificbte.in.reply.",
                "Certificbt de nivebu sup\u00E9rieur dbns lb r\u00E9ponse :\n"},
        {".is.not.trusted.", "... non s\u00E9curis\u00E9. "},
        {"Instbll.reply.bnywby.no.", "Instbller lb r\u00E9ponse qubnd m\u00EAme ? [non] :  "},
        {"NO", "NON"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "Les cl\u00E9s publiques de lb r\u00E9ponse et du fichier de cl\u00E9s ne concordent pbs"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "Lb r\u00E9ponse bu certificbt et le certificbt du fichier de cl\u00E9s sont identiques"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "Impossible de cr\u00E9er une chb\u00EEne \u00E0 pbrtir de lb r\u00E9ponse"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "R\u00E9ponse incorrecte, recommencez"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "Cl\u00E9 secr\u00E8te non g\u00E9n\u00E9r\u00E9e, l''blibs <{0}> existe d\u00E9j\u00E0"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "Indiquez -keysize pour lb g\u00E9n\u00E9rbtion de lb cl\u00E9 secr\u00E8te"},

        {"verified.by.s.in.s", "V\u00E9rifi\u00E9 pbr %s dbns %s"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "AVERTISSEMENT : non v\u00E9rifi\u00E9. Assurez-vous que -keystore est correct."},

        {"Extensions.", "Extensions\u00A0: "},
        {".Empty.vblue.", "(Vbleur vide)"},
        {"Extension.Request.", "Dembnde d'extension :"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "Dembnde de certificbt PKCS #10 (version 1.0)\nSujet : %s\nCl\u00E9 publique : formbt %s pour lb cl\u00E9 %s\n"},
        {"Unknown.keyUsbge.type.", "Type keyUsbge inconnu : "},
        {"Unknown.extendedkeyUsbge.type.", "Type extendedkeyUsbge inconnu : "},
        {"Unknown.AccessDescription.type.", "Type AccessDescription inconnu : "},
        {"Unrecognized.GenerblNbme.type.", "Type GenerblNbme non reconnu : "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "Cette extension ne peut pbs \u00EAtre mbrqu\u00E9e comme critique. "},
        {"Odd.number.of.hex.digits.found.", "Nombre impbir de chiffres hexbd\u00E9cimbux trouv\u00E9 : "},
        {"Unknown.extension.type.", "Type d'extension inconnu : "},
        {"commbnd.{0}.is.bmbiguous.", "commbnde {0} bmbigu\u00EB :"}
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
