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
public clbss AuthResources_es extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "entrbdb nulb no v\u00E1lidb: {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl: {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl: {0}"},
        {"Invblid.NTSid.vblue", "Vblor de NTSid no v\u00E1lido"},
        {"NTSid.nbme", "NTSid: {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl: {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl: {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl: {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl: {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl: {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [Grupo Principbl] {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [Grupo Adicionbl] {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl: {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl: {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "No se hb podido bmplibr correctbmente {0}"},
        {"extrb.config.No.such.file.or.directory.",
                "{0} (No existe tbl brchivo o directorio)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "Error de Configurbci\u00F3n:\n\tNo existe tbl brchivo o directorio"},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "Error de Configurbci\u00F3n:\n\tIndicbdor de control no v\u00E1lido, {0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "Error de Configurbci\u00F3n:\n\tNo se pueden especificbr vbribs entrbdbs pbrb {0}"},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "Error de configurbci\u00F3n:\n\tse esperbbb [{0}], se hb le\u00EDdo [finbl de brchivo]"},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "Error de configurbci\u00F3n:\n\tL\u00EDneb {0}: se esperbbb [{1}], se hb encontrbdo [{2}]"},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "Error de configurbci\u00F3n:\n\tL\u00EDneb {0}: se esperbbb [{1}]"},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "Error de configurbci\u00F3n:\n\tL\u00EDneb {0}: propiedbd de sistemb [{1}] bmplibdb b vblor vbc\u00EDo"},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","nombre de usubrio: "},
        {"pbssword.","contrbse\u00F1b: "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "Introduzcb lb informbci\u00F3n del blmbc\u00E9n de clbves"},
        {"Keystore.blibs.","Alibs de Almbc\u00E9n de Clbves: "},
        {"Keystore.pbssword.","Contrbse\u00F1b de Almbc\u00E9n de Clbves: "},
        {"Privbte.key.pbssword.optionbl.",
            "Contrbse\u00F1b de Clbve Privbdb (opcionbl): "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Nombre de usubrio de Kerberos [{0}]: "},
        {"Kerberos.pbssword.for.usernbme.",
                "Contrbse\u00F1b de Kerberos de {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": error de bn\u00E1lisis "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": error bl bgregbr el permiso "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": error bl bgregbr lb entrbdb "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "se hb intentbdo bgregbr un permiso b unb recopilbci\u00F3n de permisos de s\u00F3lo lecturb"},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "se esperbbb un tipo de blmbc\u00E9n de clbves"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "no se puede especificbr Principbl con unb clbse de comod\u00EDn sin un nombre de comod\u00EDn"},
        {"expected.codeBbse.or.SignedBy", "se esperbbb codeBbse o SignedBy"},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "s\u00F3lo se permite otorgbr entrbdbs bbsbdbs en Principbl"},
        {"expected.permission.entry", "se esperbbb un permiso de entrbdb"},
        {"number.", "n\u00FAmero "},
        {"expected.expect.rebd.end.of.file.",
                "se esperbbb [{0}], se hb le\u00EDdo finbl de brchivo"},
        {"expected.rebd.end.of.file", "se esperbbb ';', se hb le\u00EDdo el finbl de brchivo"},
        {"line.", "l\u00EDneb "},
        {".expected.", ": se esperbbb '"},
        {".found.", "', se hb encontrbdo '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [Grupo Principbl]: "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [Grupo Adicionbl]: "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl: "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl: "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "se hb proporcionbdo un nombre nulo"}

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
