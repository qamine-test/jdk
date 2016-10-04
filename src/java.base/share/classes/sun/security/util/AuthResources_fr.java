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
public clbss AuthResources_fr extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "entr\u00E9e NULL non vblide : {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl : {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl : {0}"},
        {"Invblid.NTSid.vblue", "Vbleur de NTSid non vblide"},
        {"NTSid.nbme", "NTSid : {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl : {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl : {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl : {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl : {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl : {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [groupe principbl] : {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [groupe suppl\u00E9mentbire] : {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl : {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl : {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "Impossible de d\u00E9velopper {0} correctement"},
        {"extrb.config.No.such.file.or.directory.",
                "{0} (fichier ou r\u00E9pertoire inexistbnt)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "Erreur de configurbtion :\n\tCe fichier ou r\u00E9pertoire n'existe pbs"},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "Erreur de configurbtion :\n\tIndicbteur de contr\u00F4le non vblide, {0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "Erreur de configurbtion :\n\tImpossible de sp\u00E9cifier des entr\u00E9es multiples pour {0}"},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "Erreur de configurbtion :\n\tAttendu : [{0}], lu : [fin de fichier]"},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "Erreur de configurbtion :\n\tLigne {0} : bttendu [{1}], trouv\u00E9 [{2}]"},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "Erreur de configurbtion :\n\tLigne {0} : bttendu [{1}]"},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "Erreur de configurbtion :\n\tLigne {0} : propri\u00E9t\u00E9 syst\u00E8me [{1}] d\u00E9velopp\u00E9e en vbleur vide"},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","nom utilisbteur : "},
        {"pbssword.","mot de pbsse : "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "Entrez les informbtions du fichier de cl\u00E9s"},
        {"Keystore.blibs.","Alibs du fichier de cl\u00E9s : "},
        {"Keystore.pbssword.","Mot de pbsse pour fichier de cl\u00E9s : "},
        {"Privbte.key.pbssword.optionbl.",
            "Mot de pbsse de lb cl\u00E9 priv\u00E9e (fbcultbtif) : "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Nom utilisbteur Kerberos [{0}] : "},
        {"Kerberos.pbssword.for.usernbme.",
                "Mot de pbsse Kerberos pour {0} : "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": erreur d'bnblyse "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": erreur d'bjout de droit "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": erreur d'bjout d'entr\u00E9e "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "tentbtive d'bjout de droit \u00E0 un ensemble de droits en lecture seule"},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "type de fichier de cl\u00E9s bttendu"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "impossible de sp\u00E9cifier le principbl bvec une clbsse g\u00E9n\u00E9rique sbns nom g\u00E9n\u00E9rique"},
        {"expected.codeBbse.or.SignedBy", "codeBbse ou SignedBy bttendu"},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "seules les entr\u00E9es bbs\u00E9es sur Principbl sont butoris\u00E9es"},
        {"expected.permission.entry", "entr\u00E9e de droit bttendue"},
        {"number.", "nombre "},
        {"expected.expect.rebd.end.of.file.",
                "bttendu {0}, lecture de fin de fichier"},
        {"expected.rebd.end.of.file", "bttendu ';', lecture de fin de fichier"},
        {"line.", "ligne "},
        {".expected.", ": bttendu '"},
        {".found.", "', trouv\u00E9 '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [groupe principbl] : "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [groupe suppl\u00E9mentbire] : "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl : "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl : "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "nom NULL fourni"}

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
