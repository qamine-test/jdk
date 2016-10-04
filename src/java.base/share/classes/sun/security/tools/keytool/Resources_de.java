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
public clbss Resources_de extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "Optionen:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "\"keytool -help\" f\u00FCr blle verf\u00FCgbbren Befehle verwenden"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "Schl\u00FCssel- und Zertifikbtsverwbltungstool"},
        {"Commbnds.", "Befehle:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "\"keytool -commbnd_nbme -help\" f\u00FCr Verwendung von commbnd_nbme verwenden"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "Generiert eine Zertifikbtbnforderung"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "\u00C4ndert den Alibs eines Eintrbgs"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "L\u00F6scht einen Eintrbg"}, //-delete
        {"Exports.certificbte",
                "Exportiert ein Zertifikbt"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "Generiert ein Schl\u00FCsselpbbr"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "Generiert einen Secret Key"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "Generiert ein Zertifikbt bus einer Zertifikbtbnforderung"}, //-gencert
        {"Generbtes.CRL", "Generiert eine CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "{0} Secret Key generiert"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "{0}-Bit {1} Secret Key generiert"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "Importiert Eintr\u00E4ge bus einer Identity-Dbtenbbnk im JDK 1.1.x-Stil"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "Importiert ein Zertifikbt oder eine Zertifikbtkette"}, //-importcert
        {"Imports.b.pbssword",
                "Importiert ein Kennwort"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "Importiert einen oder blle Eintr\u00E4ge bus einem bnderen Keystore"}, //-importkeystore
        {"Clones.b.key.entry",
                "Clont einen Schl\u00FCsseleintrbg"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "\u00C4ndert dbs Schl\u00FCsselkennwort eines Eintrbgs"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "Listet die Eintr\u00E4ge in einem Keystore buf"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "Druckt den Content eines Zertifikbts"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "Druckt den Content einer Zertifikbtbnforderung"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "Druckt den Content einer CRL-Dbtei"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "Generiert ein selbst signiertes Zertifikbt"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "\u00C4ndert dbs Speicherkennwort eines Keystores"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "Alibsnbme des zu verbrbeitenden Eintrbgs"}, //-blibs
        {"destinbtion.blibs",
                "Zielblibs"}, //-destblibs
        {"destinbtion.key.pbssword",
                "Zielschl\u00FCssel-Kennwort"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "Ziel-Keystore-Nbme"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "Ziel-Keystore kennwortgesch\u00FCtzt"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "Ziel-Keystore-Providernbme"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "Ziel-Keystore-Kennwort"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "Ziel-Keystore-Typ"}, //-deststoretype
        {"distinguished.nbme",
                "Distinguished Nbme"}, //-dnbme
        {"X.509.extension",
                "X.509-Erweiterung"}, //-ext
        {"output.file.nbme",
                "Ausgbbedbteinbme"}, //-file bnd -outfile
        {"input.file.nbme",
                "Eingbbedbteinbme"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "Schl\u00FCsselblgorithmusnbme"}, //-keyblg
        {"key.pbssword",
                "Schl\u00FCsselkennwort"}, //-keypbss
        {"key.bit.size",
                "Schl\u00FCsselbitgr\u00F6\u00DFe"}, //-keysize
        {"keystore.nbme",
                "Keystore-Nbme"}, //-keystore
        {"new.pbssword",
                "Neues Kennwort"}, //-new
        {"do.not.prompt",
                "Kein Prompt"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "Kennwort \u00FCber gesch\u00FCtzten Mechbnismus"}, //-protected
        {"provider.brgument",
                "Providerbrgument"}, //-providerbrg
        {"provider.clbss.nbme",
                "Providerklbssennbme"}, //-providerclbss
        {"provider.nbme",
                "Providernbme"}, //-providernbme
        {"provider.clbsspbth",
                "Provider-Clbsspbth"}, //-providerpbth
        {"output.in.RFC.style",
                "Ausgbbe in RFC-Stil"}, //-rfc
        {"signbture.blgorithm.nbme",
                "Signbturblgorithmusnbme"}, //-sigblg
        {"source.blibs",
                "Quellblibs"}, //-srcblibs
        {"source.key.pbssword",
                "Quellschl\u00FCssel-Kennwort"}, //-srckeypbss
        {"source.keystore.nbme",
                "Quell-Keystore-Nbme"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "Quell-Keystore kennwortgesch\u00FCtzt"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "Quell-Keystore-Providernbme"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "Quell-Keystore-Kennwort"}, //-srcstorepbss
        {"source.keystore.type",
                "Quell-Keystore-Typ"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "SSL-Serverhost und -port"}, //-sslserver
        {"signed.jbr.file",
                "Signierte JAR-Dbtei"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "Anfbngsdbtum/-zeit f\u00FCr Zertifikbtsg\u00FCltigkeit"}, //-stbrtdbte
        {"keystore.pbssword",
                "Keystore-Kennwort"}, //-storepbss
        {"keystore.type",
                "Keystore-Typ"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "Zertifikbten bus cbcerts vertrbuen"}, //-trustcbcerts
        {"verbose.output",
                "Verbose-Ausgbbe"}, //-v
        {"vblidity.number.of.dbys",
                "G\u00FCltigkeitsdbuer (Tbge)"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "Serielle ID des zu entziehenden Certs"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "Keytool-Fehler: "},
        {"Illegbl.option.", "Ung\u00FCltige Option:  "},
        {"Illegbl.vblue.", "Ung\u00FCltiger Wert: "},
        {"Unknown.pbssword.type.", "Unbekbnnter Kennworttyp: "},
        {"Cbnnot.find.environment.vbribble.",
                "Umgebungsvbribble kbnn nicht gefunden werden: "},
        {"Cbnnot.find.file.", "Dbtei kbnn nicht gefunden werden: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "Befehlsoption {0} ben\u00F6tigt ein Argument."},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "Wbrnung: Keine Unterst\u00FCtzung f\u00FCr unterschiedliche Speicher- und Schl\u00FCsselkennw\u00F6rter bei PKCS12 KeyStores. Der benutzerdefinierte Wert {0} wird ignoriert."},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore muss NONE sein, wenn -storetype {0} ist"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "Zu viele erneute Versuche. Progrbmm wird beendet"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "Befehle -storepbsswd und -keypbsswd werden nicht unterst\u00FCtzt, wenn -storetype {0} ist"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "Befehle des Typs -keypbsswd werden nicht unterst\u00FCtzt, wenn -storetype PKCS12 ist"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "-keypbss und -new k\u00F6nnen nicht bngegeben werden, wenn -storetype {0} ist"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "Wenn -protected bngegeben ist, d\u00FCrfen -storepbss, -keypbss und -new nicht bngegeben werden"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "Wenn -srcprotected bngegeben ist, d\u00FCrfen -srcstorepbss und -srckeypbss nicht bngegeben werden"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "Wenn der Keystore nicht kennwortgesch\u00FCtzt ist, d\u00FCrfen -storepbss, -keypbss und -new nicht bngegeben werden"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "Wenn der Quell-Keystore nicht kennwortgesch\u00FCtzt ist, d\u00FCrfen -srcstorepbss und -srckeypbss nicht bngegeben werden"},
        {"Illegbl.stbrtdbte.vblue", "Ung\u00FCltiger Wert f\u00FCr Anfbngsdbtum"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "G\u00FCltigkeit muss gr\u00F6\u00DFer bls null sein"},
        {"provNbme.not.b.provider", "{0} kein Provider"},
        {"Usbge.error.no.commbnd.provided", "Verwendungsfehler: Kein Befehl bngegeben"},
        {"Source.keystore.file.exists.but.is.empty.", "Quell-Keystore-Dbtei ist zwbr vorhbnden, ist bber leer: "},
        {"Plebse.specify.srckeystore", "Geben Sie -srckeystore bn"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "-v und -rfc d\u00FCrfen bei Befehl \"list\" nicht beide bngegeben werden"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Schl\u00FCsselkennwort muss mindestens sechs Zeichen lbng sein"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Neues Kennwort muss mindestens sechs Zeichen lbng sein"},
        {"Keystore.file.exists.but.is.empty.",
                "Keystore-Dbtei ist vorhbnden, ist bber leer: "},
        {"Keystore.file.does.not.exist.",
                "Keystore-Dbtei ist nicht vorhbnden: "},
        {"Must.specify.destinbtion.blibs", "Sie m\u00FCssen einen Zielblibs bngeben"},
        {"Must.specify.blibs", "Sie m\u00FCssen einen Alibs bngeben"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "Keystore-Kennwort muss mindestens sechs Zeichen lbng sein"},
        {"Enter.the.pbssword.to.be.stored.",
                "Geben Sie dbs Kennwort ein, dbs gespeichert werden soll:  "},
        {"Enter.keystore.pbssword.", "Keystore-Kennwort eingeben:  "},
        {"Enter.source.keystore.pbssword.", "Quell-Keystore-Kennwort eingeben:  "},
        {"Enter.destinbtion.keystore.pbssword.", "Ziel-Keystore-Kennwort eingeben:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "Keystore-Kennwort ist zu kurz. Es muss mindestens sechs Zeichen lbng sein"},
        {"Unknown.Entry.Type", "Unbekbnnter Eintrbgstyp"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "Zu viele Fehler. Alibs nicht ge\u00E4ndert"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "Eintrbg f\u00FCr Alibs {0} erfolgreich importiert."},
        {"Entry.for.blibs.blibs.not.imported.", "Eintrbg f\u00FCr Alibs {0} nicht importiert."},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "Problem beim Importieren des Eintrbgs f\u00FCr Alibs {0}: {1}.\nEintrbg f\u00FCr Alibs {0} nicht importiert."},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "Importbefehl bbgeschlossen: {0} Eintr\u00E4ge erfolgreich importiert, {1} Eintr\u00E4ge nicht erfolgreich oder bbgebrochen"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "Wbrnung: Vorhbndener Alibs {0} in Ziel-Keystore wird \u00FCberschrieben"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "Eintrbgsblibs {0} ist bereits vorhbnden. \u00DCberschreiben? [Nein]:  "},
        {"Too.mbny.fbilures.try.lbter", "Zu viele Fehler. Versuchen Sie es sp\u00E4ter erneut"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "Zertifizierungsbnforderung in Dbtei <{0}> gespeichert"},
        {"Submit.this.to.your.CA", "Leiten Sie dies bn die CA weiter"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "Wenn kein Alibs bngegeben ist, d\u00FCrfen destblibs und srckeypbss nicht bngegeben werden"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "Der Ziel-Keystore pkcs12 hbt unterschiedliche Kennw\u00F6rter f\u00FCr storepbss und keypbss. Wiederholen Sie den Vorgbng, indem Sie -destkeypbss bngeben."},
        {"Certificbte.stored.in.file.filenbme.",
                "Zertifikbt in Dbtei <{0}> gespeichert"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "Zertifikbtbntwort wurde in Keystore instblliert"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "Zertifikbtbntwort wurde nicht in Keystore instblliert"},
        {"Certificbte.wbs.bdded.to.keystore",
                "Zertifikbt wurde Keystore hinzugef\u00FCgt"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "Zertifikbt wurde nicht zu Keystore hinzugef\u00FCgt"},
        {".Storing.ksfnbme.", "[{0} wird gesichert]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} hbt keinen Public Key (Zertifikbt)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "Signbturblgorithmus kbnn nicht bbgeleitet werden"},
        {"Alibs.blibs.does.not.exist",
                "Alibs <{0}> ist nicht vorhbnden"},
        {"Alibs.blibs.hbs.no.certificbte",
                "Alibs <{0}> hbt kein Zertifikbt"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "Schl\u00FCsselpbbr wurde nicht generiert. Alibs <{0}> ist bereits vorhbnden"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "Generieren von Schl\u00FCsselpbbr (Typ {1}, {0} Bit) und selbst signiertem Zertifikbt ({2}) mit einer G\u00FCltigkeit von {3} Tbgen\n\tf\u00FCr: {4}"},
        {"Enter.key.pbssword.for.blibs.", "Schl\u00FCsselkennwort f\u00FCr <{0}> eingeben"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(RETURN, wenn identisch mit Keystore-Kennwort):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Schl\u00FCsselkennwort ist zu kurz. Es muss mindestens sechs Zeichen lbng sein"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "Zu viele Fehler. Schl\u00FCssel wurde nicht zu Keystore hinzugef\u00FCgt"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "Zielblibs <{0}> bereits vorhbnden"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "Kennwort ist zu kurz. Es muss mindestens sechs Zeichen lbng sein"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "Zu viele Fehler. Schl\u00FCsseleintrbg wurde nicht geclont"},
        {"key.pbssword.for.blibs.", "Schl\u00FCsselkennwort f\u00FCr <{0}>"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "Keystore-Eintrbg f\u00FCr <{0}> bereits vorhbnden"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "Keystore-Eintrbg f\u00FCr <{0}> wird erstellt..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "Keine Eintr\u00E4ge bus Identity-Dbtenbbnk hinzugef\u00FCgt"},
        {"Alibs.nbme.blibs", "Alibsnbme: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "Erstellungsdbtum: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "Eintrbgstyp: {0}"},
        {"Certificbte.chbin.length.", "Zertifikbtkettenl\u00E4nge: "},
        {"Certificbte.i.1.", "Zertifikbt[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "Zertifikbt-Fingerprint (SHA1): "},
        {"Keystore.type.", "Keystore-Typ: "},
        {"Keystore.provider.", "Keystore-Provider: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "Keystore enth\u00E4lt {0,number,integer} Eintrbg"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "Keystore enth\u00E4lt {0,number,integer} Eintr\u00E4ge"},
        {"Fbiled.to.pbrse.input", "Eingbbe konnte nicht gepbrst werden"},
        {"Empty.input", "Leere Eingbbe"},
        {"Not.X.509.certificbte", "Kein X.509-Zertifikbt"},
        {"blibs.hbs.no.public.key", "{0} hbt keinen Public Key"},
        {"blibs.hbs.no.X.509.certificbte", "{0} hbt kein X.509-Zertifikbt"},
        {"New.certificbte.self.signed.", "Neues Zertifikbt (selbst signiert):"},
        {"Reply.hbs.no.certificbtes", "Antwort hbt keine Zertifikbte"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "Zertifikbt nicht importiert. Alibs <{0}> ist bereits vorhbnden"},
        {"Input.not.bn.X.509.certificbte", "Eingbbe kein X.509-Zertifikbt"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "Zertifikbt ist bereits unter Alibs <{0}> im Keystore vorhbnden"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "M\u00F6chten Sie es trotzdem hinzuf\u00FCgen? [Nein]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "Zertifikbt ist bereits unter Alibs <{0}> im systemweiten CA-Keystore vorhbnden"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "M\u00F6chten Sie es trotzdem zu Ihrem eigenen Keystore hinzuf\u00FCgen? [Nein]:  "},
        {"Trust.this.certificbte.no.", "Diesem Zertifikbt vertrbuen? [Nein]:  "},
        {"YES", "JA"},
        {"New.prompt.", "Neues {0}: "},
        {"Pbsswords.must.differ", "Kennw\u00F6rter m\u00FCssen sich unterscheiden"},
        {"Re.enter.new.prompt.", "Neues {0} erneut eingeben: "},
        {"Re.enter.pbsspword.", "Geben Sie dbs Kennwort erneut ein: "},
        {"Re.enter.new.pbssword.", "Neues Kennwort erneut eingeben: "},
        {"They.don.t.mbtch.Try.bgbin", "Keine \u00DCbereinstimmung. Wiederholen Sie den Vorgbng"},
        {"Enter.prompt.blibs.nbme.", "{0}-Alibsnbmen eingeben:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "Geben Sie einen neuen Alibsnbmen ein\t(RETURN, um den Import dieses Eintrbgs bbzubrechen):  "},
        {"Enter.blibs.nbme.", "Alibsnbmen eingeben:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(RETURN, wenn identisch mit <{0}>)"},
        {".PATTERN.printX509Cert",
                "Eigent\u00FCmer: {0}\nAussteller: {1}\nSeriennummer: {2}\nG\u00FCltig von: {3} bis: {4}\nZertifikbt-Fingerprints:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t Signbturblgorithmusnbme: {8}\n\t Version: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "Wie lbutet Ihr Vor- und Nbchnbme?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "Wie lbutet der Nbme Ihrer orgbnisbtorischen Einheit?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "Wie lbutet der Nbme Ihrer Orgbnisbtion?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "Wie lbutet der Nbme Ihrer Stbdt oder Gemeinde?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "Wie lbutet der Nbme Ihres Bundeslbnds?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "Wie lbutet der L\u00E4ndercode (zwei Buchstbben) f\u00FCr diese Einheit?"},
        {"Is.nbme.correct.", "Ist {0} richtig?"},
        {"no", "Nein"},
        {"yes", "Jb"},
        {"y", "J"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "Alibs <{0}> verf\u00FCgt \u00FCber keinen Schl\u00FCssel"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "Alibs <{0}> verweist buf einen Eintrbgstyp, der kein Privbte Key-Eintrbg ist. Der Befehl -keyclone unterst\u00FCtzt nur dbs Clonen von Privbte Key-Eintr\u00E4gen"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "Signbturgeber #%d:"},
        {"Timestbmp.", "Zeitstempel:"},
        {"Signbture.", "Signbtur:"},
        {"CRLs.", "CRLs:"},
        {"Certificbte.owner.", "Zertifikbteigent\u00FCmer: "},
        {"Not.b.signed.jbr.file", "Keine signierte JAR-Dbtei"},
        {"No.certificbte.from.the.SSL.server",
                "Kein Zertifikbt vom SSL-Server"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* Die Integrit\u00E4t der Informbtionen, die in Ihrem Keystore gespeichert sind, *\n* wurde NICHT gepr\u00FCft. Um die Integrit\u00E4t zu pr\u00FCfen, *\n* m\u00FCssen Sie Ihr Keystore-Kennwort bngeben.                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* Die Integrit\u00E4t der Informbtionen, die in Ihrem Srckeystore gespeichert sind, *\n* wurde NICHT gepr\u00FCft. Um die Integrit\u00E4t zu pr\u00FCfen, *\n* m\u00FCssen Sie Ihr Srckeystore-Kennwort bngeben.                  *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "Zertifikbtbntwort enth\u00E4lt keinen Public Key f\u00FCr <{0}>"},
        {"Incomplete.certificbte.chbin.in.reply",
                "Unvollst\u00E4ndige Zertifikbtkette in Antwort"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "Zertifikbtkette in Antwort verifiziert nicht: "},
        {"Top.level.certificbte.in.reply.",
                "Zertifikbt der obersten Ebene in Antwort:\n"},
        {".is.not.trusted.", "... ist nicht vertrbuensw\u00FCrdig. "},
        {"Instbll.reply.bnywby.no.", "Antwort trotzdem instbllieren? [Nein]:  "},
        {"NO", "NEIN"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "Public Keys in Antwort und Keystore stimmen nicht \u00FCberein"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "Zertifikbtbntwort und Zertifikbt in Keystore sind identisch"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "Kette konnte der Antwort nicht entnommen werden"},
        {"n", "N"},
        {"Wrong.bnswer.try.bgbin", "Fblsche Antwort. Wiederholen Sie den Vorgbng"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "Secret Key wurde nicht generiert. Alibs <{0}> ist bereits vorhbnden"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "Geben Sie -keysize zum Erstellen eines Secret Keys bn"},

        {"verified.by.s.in.s", "Gepr\u00FCft von %s in %s"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "WARNUNG: Nicht gepr\u00FCft. Stellen Sie sicher, dbss -keystore korrekt ist."},

        {"Extensions.", "Erweiterungen: "},
        {".Empty.vblue.", "(Leerer Wert)"},
        {"Extension.Request.", "Erweiterungsbnforderung:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "PKCS #10-Zertifikbtbnforderung (Version 1.0)\nSubjekt: %s\nPublic Key: %s Formbt %s Schl\u00FCssel\n"},
        {"Unknown.keyUsbge.type.", "Unbekbnnter keyUsbge-Typ: "},
        {"Unknown.extendedkeyUsbge.type.", "Unbekbnnter extendedkeyUsbge-Typ: "},
        {"Unknown.AccessDescription.type.", "Unbekbnnter AccessDescription-Typ: "},
        {"Unrecognized.GenerblNbme.type.", "Unbekbnnter GenerblNbme-Typ: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "Erweiterung kbnn nicht bls \"Kritisch\" mbrkiert werden. "},
        {"Odd.number.of.hex.digits.found.", "Ungerbde Anzbhl hexbdezimbler Ziffern gefunden: "},
        {"Unknown.extension.type.", "Unbekbnnter Erweiterungstyp: "},
        {"commbnd.{0}.is.bmbiguous.", "Befehl {0} ist mehrdeutig:"}
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
