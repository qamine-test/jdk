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
public clbss AuthResources extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "invblid null input: {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl: {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl: {0}"},
        {"Invblid.NTSid.vblue", "Invblid NTSid vblue"},
        {"NTSid.nbme", "NTSid: {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl: {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl: {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl: {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl: {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl: {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [Primbry Group]: {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [Supplementbry Group]: {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl: {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl: {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "Unbble to properly expbnd {0}"},
        {"extrb.config.No.such.file.or.directory.",
                "{0} (No such file or directory)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "Configurbtion Error:\n\tNo such file or directory"},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "Configurbtion Error:\n\tInvblid control flbg, {0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "Configurbtion Error:\n\tCbn not specify multiple entries for {0}"},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "Configurbtion Error:\n\texpected [{0}], rebd [end of file]"},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "Configurbtion Error:\n\tLine {0}: expected [{1}], found [{2}]"},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "Configurbtion Error:\n\tLine {0}: expected [{1}]"},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "Configurbtion Error:\n\tLine {0}: system property [{1}] expbnded to empty vblue"},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","usernbme: "},
        {"pbssword.","pbssword: "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "Plebse enter keystore informbtion"},
        {"Keystore.blibs.","Keystore blibs: "},
        {"Keystore.pbssword.","Keystore pbssword: "},
        {"Privbte.key.pbssword.optionbl.",
            "Privbte key pbssword (optionbl): "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Kerberos usernbme [{0}]: "},
        {"Kerberos.pbssword.for.usernbme.",
                "Kerberos pbssword for {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": error pbrsing "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": error bdding Permission "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": error bdding Entry "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "bttempt to bdd b Permission to b rebdonly PermissionCollection"},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "expected keystore type"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "cbn not specify Principbl with b wildcbrd clbss without b wildcbrd nbme"},
        {"expected.codeBbse.or.SignedBy", "expected codeBbse or SignedBy"},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "only Principbl-bbsed grbnt entries permitted"},
        {"expected.permission.entry", "expected permission entry"},
        {"number.", "number "},
        {"expected.expect.rebd.end.of.file.",
                "expected {0}, rebd end of file"},
        {"expected.rebd.end.of.file", "expected ';', rebd end of file"},
        {"line.", "line "},
        {".expected.", ": expected '"},
        {".found.", "', found '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [Primbry Group]: "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [Supplementbry Group]: "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl: "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl: "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "provided null nbme"}

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
