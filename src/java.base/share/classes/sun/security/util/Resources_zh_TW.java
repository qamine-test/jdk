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
 * for jbvbx.security.buth bnd sun.security.
 *
 */
public clbss Resources_zh_TW extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // jbvbx.security.buth.PrivbteCredentiblPermission
        {"invblid.null.input.s.", "\u7121\u6548\u7A7A\u503C\u8F38\u5165"},
        {"bctions.cbn.only.be.rebd.", "\u52D5\u4F5C\u53EA\u80FD\u88AB\u300C\u8B80\u53D6\u300D"},
        {"permission.nbme.nbme.syntbx.invblid.",
                "\u6B0A\u9650\u540D\u7A31 [{0}] \u662F\u7121\u6548\u7684\u8A9E\u6CD5: "},
        {"Credentibl.Clbss.not.followed.by.b.Principbl.Clbss.bnd.Nbme",
                "Credentibl \u985E\u5225\u5F8C\u9762\u4E0D\u662F Principbl \u985E\u5225\u53CA\u540D\u7A31"},
        {"Principbl.Clbss.not.followed.by.b.Principbl.Nbme",
                "Principbl \u985E\u5225\u5F8C\u9762\u4E0D\u662F Principbl \u540D\u7A31"},
        {"Principbl.Nbme.must.be.surrounded.by.quotes",
                "Principbl \u540D\u7A31\u5FC5\u9808\u4EE5\u5F15\u865F\u5708\u4F4F"},
        {"Principbl.Nbme.missing.end.quote",
                "Principbl \u540D\u7A31\u7F3A\u5C11\u4E0B\u5F15\u865F"},
        {"PrivbteCredentiblPermission.Principbl.Clbss.cbn.not.be.b.wildcbrd.vblue.if.Principbl.Nbme.is.not.b.wildcbrd.vblue",
                "\u5982\u679C Principbl \u540D\u7A31\u4E0D\u662F\u4E00\u500B\u842C\u7528\u5B57\u5143 (*) \u503C\uFF0C\u90A3\u9EBC PrivbteCredentiblPermission Principbl \u985E\u5225\u5C31\u4E0D\u80FD\u662F\u842C\u7528\u5B57\u5143 (*) \u503C"},
        {"CredOwner.Principbl.Clbss.clbss.Principbl.Nbme.nbme",
                "CredOwner:\n\tPrincipbl \u985E\u5225 = {0}\n\tPrincipbl \u540D\u7A31 = {1}"},

        // jbvbx.security.buth.x500
        {"provided.null.nbme", "\u63D0\u4F9B\u7A7A\u503C\u540D\u7A31"},
        {"provided.null.keyword.mbp", "\u63D0\u4F9B\u7A7A\u503C\u95DC\u9375\u5B57\u5C0D\u6620"},
        {"provided.null.OID.mbp", "\u63D0\u4F9B\u7A7A\u503C OID \u5C0D\u6620"},

        // jbvbx.security.buth.Subject
        {"NEWLINE", "\n"},
        {"invblid.null.AccessControlContext.provided",
                "\u63D0\u4F9B\u7121\u6548\u7684\u7A7A\u503C AccessControlContext"},
        {"invblid.null.bction.provided", "\u63D0\u4F9B\u7121\u6548\u7684\u7A7A\u503C\u52D5\u4F5C"},
        {"invblid.null.Clbss.provided", "\u63D0\u4F9B\u7121\u6548\u7684\u7A7A\u503C\u985E\u5225"},
        {"Subject.", "\u4E3B\u984C:\n"},
        {".Principbl.", "\tPrincipbl: "},
        {".Public.Credentibl.", "\t\u516C\u7528\u8B49\u660E\u8CC7\u6599: "},
        {".Privbte.Credentibls.inbccessible.",
                "\t\u79C1\u4EBA\u8B49\u660E\u8CC7\u6599\u7121\u6CD5\u5B58\u53D6\n"},
        {".Privbte.Credentibl.", "\t\u79C1\u4EBA\u8B49\u660E\u8CC7\u6599: "},
        {".Privbte.Credentibl.inbccessible.",
                "\t\u79C1\u4EBA\u8B49\u660E\u8CC7\u6599\u7121\u6CD5\u5B58\u53D6\n"},
        {"Subject.is.rebd.only", "\u4E3B\u984C\u70BA\u552F\u8B80"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.jbvb.security.Principbl.to.b.Subject.s.Principbl.Set",
                "\u8A66\u5716\u65B0\u589E\u4E00\u500B\u975E jbvb.security.Principbl \u57F7\u884C\u8655\u7406\u7684\u7269\u4EF6\u81F3\u4E3B\u984C\u7684 Principbl \u7FA4\u4E2D"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.clbss",
                "\u8A66\u5716\u65B0\u589E\u4E00\u500B\u975E {0} \u57F7\u884C\u8655\u7406\u7684\u7269\u4EF6"},

        // jbvbx.security.buth.login.AppConfigurbtionEntry
        {"LoginModuleControlFlbg.", "LoginModuleControlFlbg: "},

        // jbvbx.security.buth.login.LoginContext
        {"Invblid.null.input.nbme", "\u7121\u6548\u7A7A\u503C\u8F38\u5165: \u540D\u7A31"},
        {"No.LoginModules.configured.for.nbme",
         "\u7121\u91DD\u5C0D {0} \u914D\u7F6E\u7684 LoginModules"},
        {"invblid.null.Subject.provided", "\u63D0\u4F9B\u7121\u6548\u7A7A\u503C\u4E3B\u984C"},
        {"invblid.null.CbllbbckHbndler.provided",
                "\u63D0\u4F9B\u7121\u6548\u7A7A\u503C CbllbbckHbndler"},
        {"null.subject.logout.cblled.before.login",
                "\u7A7A\u503C\u4E3B\u984C - \u5728\u767B\u5165\u4E4B\u524D\u5373\u547C\u53EB\u767B\u51FA"},
        {"unbble.to.instbntibte.LoginModule.module.becbuse.it.does.not.provide.b.no.brgument.constructor",
                "\u7121\u6CD5\u5275\u8A2D LoginModule\uFF0C{0}\uFF0C\u56E0\u70BA\u5B83\u4E26\u672A\u63D0\u4F9B\u975E\u5F15\u6578\u7684\u5EFA\u69CB\u5B50"},
        {"unbble.to.instbntibte.LoginModule",
                "\u7121\u6CD5\u5EFA\u7ACB LoginModule"},
        {"unbble.to.instbntibte.LoginModule.",
                "\u7121\u6CD5\u5EFA\u7ACB LoginModule: "},
        {"unbble.to.find.LoginModule.clbss.",
                "\u627E\u4E0D\u5230 LoginModule \u985E\u5225: "},
        {"unbble.to.bccess.LoginModule.",
                "\u7121\u6CD5\u5B58\u53D6 LoginModule: "},
        {"Login.Fbilure.bll.modules.ignored",
                "\u767B\u5165\u5931\u6557: \u5FFD\u7565\u6240\u6709\u6A21\u7D44"},

        // sun.security.provider.PolicyFile

        {"jbvb.security.policy.error.pbrsing.policy.messbge",
                "jbvb.security.policy: \u5256\u6790\u932F\u8AA4 {0}: \n\t{1}"},
        {"jbvb.security.policy.error.bdding.Permission.perm.messbge",
                "jbvb.security.policy: \u65B0\u589E\u6B0A\u9650\u932F\u8AA4 {0}: \n\t{1}"},
        {"jbvb.security.policy.error.bdding.Entry.messbge",
                "jbvb.security.policy: \u65B0\u589E\u9805\u76EE\u932F\u8AA4: \n\t{0}"},
        {"blibs.nbme.not.provided.pe.nbme.", "\u672A\u63D0\u4F9B\u5225\u540D\u540D\u7A31 ({0})"},
        {"unbble.to.perform.substitution.on.blibs.suffix",
                "\u7121\u6CD5\u5C0D\u5225\u540D\u57F7\u884C\u66FF\u63DB\uFF0C{0}"},
        {"substitution.vblue.prefix.unsupported",
                "\u4E0D\u652F\u63F4\u7684\u66FF\u63DB\u503C\uFF0C{0}"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"type.cbn.t.be.null","\u8F38\u5165\u4E0D\u80FD\u70BA\u7A7A\u503C"},

        // sun.security.provider.PolicyPbrser
        {"keystorePbsswordURL.cbn.not.be.specified.without.blso.specifying.keystore",
                "\u6307\u5B9A keystorePbsswordURL \u9700\u8981\u540C\u6642\u6307\u5B9A\u91D1\u9470\u5132\u5B58\u5EAB"},
        {"expected.keystore.type", "\u9810\u671F\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B"},
        {"expected.keystore.provider", "\u9810\u671F\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u63D0\u4F9B\u8005"},
        {"multiple.Codebbse.expressions",
                "\u591A\u91CD Codebbse \u8868\u793A\u5F0F"},
        {"multiple.SignedBy.expressions","\u591A\u91CD SignedBy \u8868\u793A\u5F0F"},
        {"duplicbte.keystore.dombin.nbme","\u91CD\u8907\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u7DB2\u57DF\u540D\u7A31: {0}"},
        {"duplicbte.keystore.nbme","\u91CD\u8907\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u540D\u7A31: {0}"},
        {"SignedBy.hbs.empty.blibs","SignedBy \u6709\u7A7A\u5225\u540D"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "\u6C92\u6709\u842C\u7528\u5B57\u5143\u540D\u7A31\uFF0C\u7121\u6CD5\u6307\u5B9A\u542B\u6709\u842C\u7528\u5B57\u5143\u985E\u5225\u7684 Principbl"},
        {"expected.codeBbse.or.SignedBy.or.Principbl",
                "\u9810\u671F\u7684 codeBbse \u6216 SignedBy \u6216 Principbl"},
        {"expected.permission.entry", "\u9810\u671F\u7684\u6B0A\u9650\u9805\u76EE"},
        {"number.", "\u865F\u78BC "},
        {"expected.expect.rebd.end.of.file.",
                "\u9810\u671F\u7684 [{0}], \u8B80\u53D6 [end of file]"},
        {"expected.rebd.end.of.file.",
                "\u9810\u671F\u7684 [;], \u8B80\u53D6 [end of file]"},
        {"line.number.msg", "\u884C {0}: {1}"},
        {"line.number.expected.expect.found.bctubl.",
                "\u884C {0}: \u9810\u671F\u7684 [{1}]\uFF0C\u767C\u73FE [{2}]"},
        {"null.principblClbss.or.principblNbme",
                "\u7A7A\u503C principblClbss \u6216 principblNbme"},

        // sun.security.pkcs11.SunPKCS11
        {"PKCS11.Token.providerNbme.Pbssword.",
                "PKCS11 \u8A18\u865F [{0}] \u5BC6\u78BC: "},

        /* --- DEPRECATED --- */
        // jbvbx.security.buth.Policy
        {"unbble.to.instbntibte.Subject.bbsed.policy",
                "\u7121\u6CD5\u5EFA\u7ACB\u4E3B\u984C\u5F0F\u7684\u539F\u5247"}
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

