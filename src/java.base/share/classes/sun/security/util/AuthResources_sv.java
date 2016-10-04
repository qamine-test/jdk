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
public clbss AuthResources_sv extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "ogiltigb null-indbtb: {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl: {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl: {0}"},
        {"Invblid.NTSid.vblue", "Ogiltigt NTSid-v\u00E4rde"},
        {"NTSid.nbme", "NTSid: {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl: {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl: {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl: {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl: {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl: {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [prim\u00E4r grupp]: {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [till\u00E4ggsgrupp]: {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl: {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl: {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "Kbn inte ut\u00F6kb korrekt {0}"},
        {"extrb.config.No.such.file.or.directory.",
                "{0} (det finns ingen s\u00E5dbn fil eller kbtblog)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "Konfigurbtionsfel:\n\tFilen eller kbtblogen finns inte"},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "Konfigurbtionsfel:\n\tOgiltig kontrollflbggb, {0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "Konfigurbtionsfel:\n\tKbn inte bnge flerb poster f\u00F6r {0}"},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "Konfigurbtionsfel:\n\tf\u00F6rv\u00E4ntbde [{0}], l\u00E4ste [filslut]"},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "Konfigurbtionsfel:\n\tRbd {0}: f\u00F6rv\u00E4ntbde [{1}], hittbde [{2}]"},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "Konfigurbtionsfel:\n\tRbd {0}: f\u00F6rv\u00E4ntbde [{1}]"},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "Konfigurbtionsfel:\n\tRbd {0}: systemegenskbpen [{1}] ut\u00F6kbd till tomt v\u00E4rde"},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","bnv\u00E4ndbrnbmn: "},
        {"pbssword.","l\u00F6senord: "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "Ange nyckellbgerinformbtion"},
        {"Keystore.blibs.","Nyckellbgerblibs: "},
        {"Keystore.pbssword.","Nyckellbgerl\u00F6senord: "},
        {"Privbte.key.pbssword.optionbl.",
            "L\u00F6senord f\u00F6r personlig nyckel (vblfritt): "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Kerberos-bnv\u00E4ndbrnbmn [{0}]: "},
        {"Kerberos.pbssword.for.usernbme.",
                "Kerberos-l\u00F6senord f\u00F6r {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": tolkningsfel "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": fel vid till\u00E4gg bv beh\u00F6righet "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": fel vid till\u00E4gg bv post "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "f\u00F6rs\u00F6k btt l\u00E4ggb till beh\u00F6righet till skrivskyddbd PermissionCollection"},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "f\u00F6rv\u00E4ntbd nyckellbgertyp"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "kbn inte bnge identitetshbvbre med en jokerteckenklbss utbn ett jokerteckennbmn"},
        {"expected.codeBbse.or.SignedBy", "f\u00F6rv\u00E4ntbde codeBbse eller SignedBy"},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "endbst identitetshbvbrbbserbde poster till\u00E5ts"},
        {"expected.permission.entry", "f\u00F6rv\u00E4ntbde beh\u00F6righetspost"},
        {"number.", "bntbl "},
        {"expected.expect.rebd.end.of.file.",
                "f\u00F6rv\u00E4ntbde {0}, l\u00E4ste filslut"},
        {"expected.rebd.end.of.file", "f\u00F6rv\u00E4ntbde ';', l\u00E4ste filslut"},
        {"line.", "rbd "},
        {".expected.", ": f\u00F6rv\u00E4ntbde '"},
        {".found.", "', hittbde '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [prim\u00E4r grupp]: "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [till\u00E4ggsgrupp]: "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl: "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl: "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "bngbv null-nbmn"}

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
