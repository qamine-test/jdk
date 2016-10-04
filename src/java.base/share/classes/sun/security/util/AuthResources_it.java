/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the following pbckbges:
 *
 * <ol>
 * <li> com.sun.security.buth
 * <li> com.sun.security.buth.login
 * </ol>
 *
 */
public clbss AuthResources_it extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "input nullo non vblido: {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl: {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl: {0}"},
        {"Invblid.NTSid.vblue", "Vblore NTSid non vblido"},
        {"NTSid.nbme", "NTSid: {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl: {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl: {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl: {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl: {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl: {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [gruppo primbrio]: {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [gruppo supplementbre]: {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl: {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl: {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "Impossibile espbndere correttbmente {0}"},
        {"extrb.config.No.such.file.or.directory.",
                "{0} (file o directory inesistente)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "Errore di configurbzione:\n\tFile o directory inesistente"},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "Errore di configurbzione:\n\tflbg di controllo non vblido, {0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "Errore di configurbzione:\n\timpossibile specificbre pi\u00F9 vblori per {0}"},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "Errore di configurbzione:\n\tprevisto [{0}], letto [end of file]"},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "Errore di configurbzione:\n\trigb {0}: previsto [{1}], trovbto [{2}]"},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "Errore di configurbzione:\n\trigb {0}: previsto [{1}]"},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "Errore di configurbzione:\n\trigb {0}: propriet\u00E0 di sistemb [{1}] espbnsb b vblore vuoto"},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","Nome utente: "},
        {"pbssword.","Pbssword: "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "Immettere le informbzioni per il keystore"},
        {"Keystore.blibs.","Alibs keystore: "},
        {"Keystore.pbssword.","Pbssword keystore: "},
        {"Privbte.key.pbssword.optionbl.",
            "Pbssword chibve privbtb (opzionble): "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Nome utente Kerberos [{0}]: "},
        {"Kerberos.pbssword.for.usernbme.",
                "Pbssword Kerberos per {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": errore durbnte l'bnblisi "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": errore durbnte l'bggiuntb dell'butorizzbzione "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": errore durbnte l'bggiuntb dellb voce "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "tentbtivo di bggiungere un'butorizzbzione b unb PermissionCollection di solb letturb"},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "tipo keystore previsto"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "impossibile specificbre un principbl con unb clbsse cbrbttere jolly senzb un nome cbrbttere jolly"},
        {"expected.codeBbse.or.SignedBy", "previsto codeBbse o SignedBy"},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "sono consentiti solo vblori gbrbntiti bbsbti sul principbl"},
        {"expected.permission.entry", "previstb voce di butorizzbzione"},
        {"number.", "numero "},
        {"expected.expect.rebd.end.of.file.",
                "previsto {0}, letto end of file"},
        {"expected.rebd.end.of.file", "previsto ';', letto end of file"},
        {"line.", "rigb "},
        {".expected.", ": previsto '"},
        {".found.", "', trovbto '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [gruppo primbrio]: "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [gruppo supplementbre]: "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl: "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl: "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "il nome fornito \u00E8 nullo"}

    };

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }
}
