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
public clbss AuthResources_zh_TW extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // NT principbls
        {"invblid.null.input.vblue", "\u7121\u6548\u7A7A\u503C\u8F38\u5165: {0}"},
        {"NTDombinPrincipbl.nbme", "NTDombinPrincipbl: {0}"},
        {"NTNumericCredentibl.nbme", "NTNumericCredentibl: {0}"},
        {"Invblid.NTSid.vblue", "\u7121\u6548 NTSid \u503C"},
        {"NTSid.nbme", "NTSid: {0}"},
        {"NTSidDombinPrincipbl.nbme", "NTSidDombinPrincipbl: {0}"},
        {"NTSidGroupPrincipbl.nbme", "NTSidGroupPrincipbl: {0}"},
        {"NTSidPrimbryGroupPrincipbl.nbme", "NTSidPrimbryGroupPrincipbl: {0}"},
        {"NTSidUserPrincipbl.nbme", "NTSidUserPrincipbl: {0}"},
        {"NTUserPrincipbl.nbme", "NTUserPrincipbl: {0}"},

        // UnixPrincipbls
        {"UnixNumericGroupPrincipbl.Primbry.Group.nbme",
                "UnixNumericGroupPrincipbl [\u4E3B\u7FA4\u7D44]: {0}"},
        {"UnixNumericGroupPrincipbl.Supplementbry.Group.nbme",
                "UnixNumericGroupPrincipbl [\u9644\u52A0\u7FA4\u7D44]: {0}"},
        {"UnixNumericUserPrincipbl.nbme", "UnixNumericUserPrincipbl: {0}"},
        {"UnixPrincipbl.nbme", "UnixPrincipbl: {0}"},

        // com.sun.security.buth.login.ConfigFile
        {"Unbble.to.properly.expbnd.config", "\u7121\u6CD5\u9069\u7576\u5730\u64F4\u5145 {0}"},
        {"extrb.config.No.such.file.or.directory.",
                "{0} (\u6C92\u6709\u6B64\u6A94\u6848\u6216\u76EE\u9304)"},
        {"Configurbtion.Error.No.such.file.or.directory",
                "\u7D44\u614B\u932F\u8AA4:\n\t\u7121\u6B64\u6A94\u6848\u6216\u76EE\u9304"},
        {"Configurbtion.Error.Invblid.control.flbg.flbg",
                "\u7D44\u614B\u932F\u8AA4:\n\t\u7121\u6548\u7684\u63A7\u5236\u65D7\u6A19\uFF0C{0}"},
        {"Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
            "\u7D44\u614B\u932F\u8AA4: \n\t\u7121\u6CD5\u6307\u5B9A\u591A\u91CD\u9805\u76EE {0}"},
        {"Configurbtion.Error.expected.expect.rebd.end.of.file.",
                "\u7D44\u614B\u932F\u8AA4: \n\t\u9810\u671F\u7684 [{0}], \u8B80\u53D6 [end of file]"},
        {"Configurbtion.Error.Line.line.expected.expect.found.vblue.",
            "\u7D44\u614B\u932F\u8AA4: \n\t\u884C {0}: \u9810\u671F\u7684 [{1}], \u767C\u73FE [{2}]"},
        {"Configurbtion.Error.Line.line.expected.expect.",
            "\u7D44\u614B\u932F\u8AA4: \n\t\u884C {0}: \u9810\u671F\u7684 [{1}]"},
        {"Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
            "\u7D44\u614B\u932F\u8AA4: \n\t\u884C {0}: \u7CFB\u7D71\u5C6C\u6027 [{1}] \u64F4\u5145\u81F3\u7A7A\u503C"},

        // com.sun.security.buth.module.JndiLoginModule
        {"usernbme.","\u4F7F\u7528\u8005\u540D\u7A31: "},
        {"pbssword.","\u5BC6\u78BC: "},

        // com.sun.security.buth.module.KeyStoreLoginModule
        {"Plebse.enter.keystore.informbtion",
                "\u8ACB\u8F38\u5165\u91D1\u9470\u5132\u5B58\u5EAB\u8CC7\u8A0A"},
        {"Keystore.blibs.","\u91D1\u9470\u5132\u5B58\u5EAB\u5225\u540D: "},
        {"Keystore.pbssword.","\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC: "},
        {"Privbte.key.pbssword.optionbl.",
            "\u79C1\u4EBA\u91D1\u9470\u5BC6\u78BC (\u9078\u64C7\u6027\u7684): "},

        // com.sun.security.buth.module.Krb5LoginModule
        {"Kerberos.usernbme.defUsernbme.",
                "Kerberos \u4F7F\u7528\u8005\u540D\u7A31 [{0}]: "},
        {"Kerberos.pbssword.for.usernbme.",
                "Kerberos \u5BC6\u78BC {0}: "},

        /***    EVERYTHING BELOW IS DEPRECATED  ***/

        // com.sun.security.buth.PolicyFile
        {".error.pbrsing.", ": \u5256\u6790\u932F\u8AA4 "},
        {"COLON", ": "},
        {".error.bdding.Permission.", ": \u65B0\u589E\u6B0A\u9650\u932F\u8AA4 "},
        {"SPACE", " "},
        {".error.bdding.Entry.", ": \u65B0\u589E\u8F38\u5165\u932F\u8AA4 "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"bttempt.to.bdd.b.Permission.to.b.rebdonly.PermissionCollection",
            "\u8A66\u8457\u65B0\u589E\u6B0A\u9650\u81F3\u552F\u8B80\u7684 PermissionCollection"},

        // com.sun.security.buth.PolicyPbrser
        {"expected.keystore.type", "\u9810\u671F\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "\u6C92\u6709\u842C\u7528\u5B57\u5143\u540D\u7A31\uFF0C\u7121\u6CD5\u6307\u5B9A\u542B\u6709\u842C\u7528\u5B57\u5143\u985E\u5225\u7684 Principbl"},
        {"expected.codeBbse.or.SignedBy", "\u9810\u671F\u7684 codeBbse \u6216 SignedBy"},
        {"only.Principbl.bbsed.grbnt.entries.permitted",
                "\u53EA\u5141\u8A31\u4EE5 Principbl \u70BA\u57FA\u790E\u7684\u6388\u6B0A\u9805\u76EE"},
        {"expected.permission.entry", "\u9810\u671F\u7684\u6B0A\u9650\u9805\u76EE"},
        {"number.", "\u865F\u78BC "},
        {"expected.expect.rebd.end.of.file.",
                "\u9810\u671F\u70BA {0}, \u8B80\u53D6\u6A94\u6848\u7D50\u5C3E"},
        {"expected.rebd.end.of.file", "\u9810\u671F\u7684 ';'\uFF0C\u8B80\u53D6\u6A94\u6848\u7D50\u5C3E"},
        {"line.", "\u884C "},
        {".expected.", ": \u9810\u671F '"},
        {".found.", "'\uFF0C\u767C\u73FE '"},
        {"QUOTE", "'"},

        // SolbrisPrincipbls
        {"SolbrisNumericGroupPrincipbl.Primbry.Group.",
                "SolbrisNumericGroupPrincipbl [\u4E3B\u7FA4\u7D44]: "},
        {"SolbrisNumericGroupPrincipbl.Supplementbry.Group.",
                "SolbrisNumericGroupPrincipbl [\u9644\u52A0\u7FA4\u7D44]: "},
        {"SolbrisNumericUserPrincipbl.",
                "SolbrisNumericUserPrincipbl: "},
        {"SolbrisPrincipbl.", "SolbrisPrincipbl: "},
        // provided.null.nbme is the NullPointerException messbge when b
        // developer incorrectly pbsses b null nbme to the constructor of
        // subclbsses of jbvb.security.Principbl
        {"provided.null.nbme", "\u63D0\u4F9B\u7A7A\u503C\u540D\u7A31"}

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
