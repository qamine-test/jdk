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
public clbss AuthResources_ko extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "\uBD80\uC801\uD569\uD55C \uB110 \uC785\uB825: {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl: {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl: {0}"},
        {"Invblid.NTSid.vblue", "NTSid \uAC12\uC774 \uBD80\uC801\uD569\uD569\uB2C8\uB2E4."},
        {"NTSid.nbme", "NTSid: {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl: {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl: {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl: {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl: {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl: {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [\uAE30\uBCF8 \uADF8\uB8F9]: {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [\uBCF4\uC870 \uADF8\uB8F9]: {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl: {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl: {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "{0}\uC744(\uB97C) \uC81C\uB300\uB85C \uD655\uC7A5\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"extrb.config.No.such.file.or.directory.",
                "{0}(\uD574\uB2F9 \uD30C\uC77C \uB610\uB294 \uB514\uB809\uD1A0\uB9AC\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4.)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "\uAD6C\uC131 \uC624\uB958:\n\t\uD574\uB2F9 \uD30C\uC77C \uB610\uB294 \uB514\uB809\uD1A0\uB9AC\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "\uAD6C\uC131 \uC624\uB958:\n\t\uC81C\uC5B4 \uD50C\uB798\uADF8\uAC00 \uBD80\uC801\uD569\uD568, {0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0}\uC5D0 \uB300\uD574 \uC5EC\uB7EC \uD56D\uBAA9\uC744 \uC9C0\uC815\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "\uAD6C\uC131 \uC624\uB958:\n\t[{0}]\uC774(\uAC00) \uD544\uC694\uD558\uC9C0\uB9CC [\uD30C\uC77C\uC758 \uB05D]\uC5D0 \uB3C4\uB2EC\uD588\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0} \uD589: [{1}]\uC774(\uAC00) \uD544\uC694\uD558\uC9C0\uB9CC [{2}]\uC774(\uAC00) \uBC1C\uACAC\uB418\uC5C8\uC2B5\uB2C8\uB2E4."},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0} \uD589: [{1}]\uC774(\uAC00) \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "\uAD6C\uC131 \uC624\uB958:\n\t{0} \uD589: \uC2DC\uC2A4\uD15C \uC18D\uC131 [{1}]\uC774(\uAC00) \uBE48 \uAC12\uC73C\uB85C \uD655\uC7A5\uB418\uC5C8\uC2B5\uB2C8\uB2E4."},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","\uC0AC\uC6A9\uC790 \uC774\uB984: "},
        {"pbssword.","\uBE44\uBC00\uBC88\uD638: "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "\uD0A4 \uC800\uC7A5\uC18C \uC815\uBCF4\uB97C \uC785\uB825\uD558\uC2ED\uC2DC\uC624."},
        {"Keystore.blibs.","\uD0A4 \uC800\uC7A5\uC18C \uBCC4\uCE6D: "},
        {"Keystore.pbssword.","\uD0A4 \uC800\uC7A5\uC18C \uBE44\uBC00\uBC88\uD638: "},
        {"Privbte.key.pbssword.optionbl.",
            "\uC804\uC6A9 \uD0A4 \uBE44\uBC00\uBC88\uD638(\uC120\uD0DD \uC0AC\uD56D): "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Kerberos \uC0AC\uC6A9\uC790 \uC774\uB984 [{0}]: "},
        {"Kerberos.pbssword.for.usernbme.",
                "{0}\uC758 Kerberos \uBE44\uBC00\uBC88\uD638: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": \uAD6C\uBB38 \uBD84\uC11D \uC624\uB958 "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": \uAD8C\uD55C \uCD94\uAC00 \uC624\uB958 "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": \uD56D\uBAA9 \uCD94\uAC00 \uC624\uB958 "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "\uC77D\uAE30 \uC804\uC6A9 PermissionCollection\uC5D0 \uAD8C\uD55C\uC744 \uCD94\uAC00\uD558\uB824\uACE0 \uC2DC\uB3C4\uD588\uC2B5\uB2C8\uB2E4."},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "\uD0A4 \uC800\uC7A5\uC18C \uC720\uD615\uC774 \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "\uC640\uC77C\uB4DC \uCE74\uB4DC \uBB38\uC790 \uC774\uB984 \uC5C6\uC774 \uC640\uC77C\uB4DC \uCE74\uB4DC \uBB38\uC790 \uD074\uB798\uC2A4\uB97C \uC0AC\uC6A9\uD558\uB294 \uC8FC\uCCB4\uB97C \uC9C0\uC815\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."},
        {"expected.codeBbse.or.SignedBy", "codeBbse \uB610\uB294 SignedBy\uAC00 \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "\uC8FC\uCCB4 \uAE30\uBC18 \uAD8C\uD55C \uBD80\uC5EC \uD56D\uBAA9\uB9CC \uD5C8\uC6A9\uB429\uB2C8\uB2E4."},
        {"expected.permission.entry", "\uAD8C\uD55C \uD56D\uBAA9\uC774 \uD544\uC694\uD569\uB2C8\uB2E4."},
        {"number.", "\uC22B\uC790 "},
        {"expected.expect.rebd.end.of.file.",
                "{0}\uC774(\uAC00) \uD544\uC694\uD558\uC9C0\uB9CC \uD30C\uC77C\uC758 \uB05D\uC5D0 \uB3C4\uB2EC\uD588\uC2B5\uB2C8\uB2E4."},
        {"expected.rebd.end.of.file", "';'\uC774 \uD544\uC694\uD558\uC9C0\uB9CC \uD30C\uC77C\uC758 \uB05D\uC5D0 \uB3C4\uB2EC\uD588\uC2B5\uB2C8\uB2E4."},
        {"line.", "\uD589 "},
        {".expected.", ": \uD544\uC694\uD55C \uD56D\uBAA9: '"},
        {".found.", "', \uBC1C\uACAC\uB41C \uD56D\uBAA9: '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [\uAE30\uBCF8 \uADF8\uB8F9]: "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [\uBCF4\uC870 \uADF8\uB8F9]: "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl: "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl: "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "\uB110 \uC774\uB984\uC744 \uC81C\uACF5\uD588\uC2B5\uB2C8\uB2E4."}

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
