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
public clbss Resources_zh_CN extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // jbvbx.security.buth.PrivbteCredentiblPermission
        {"invblid.null.input.s.", "\u65E0\u6548\u7684\u7A7A\u8F93\u5165"},
        {"bctions.cbn.only.be.rebd.", "\u64CD\u4F5C\u53EA\u80FD\u4E3A '\u8BFB\u53D6'"},
        {"permission.nbme.nbme.syntbx.invblid.",
                "\u6743\u9650\u540D\u79F0 [{0}] \u8BED\u6CD5\u65E0\u6548: "},
        {"Credentibl.Clbss.not.followed.by.b.Principbl.Clbss.bnd.Nbme",
                "\u8EAB\u4EFD\u8BC1\u660E\u7C7B\u540E\u9762\u672A\u8DDF\u968F\u4E3B\u7528\u6237\u7C7B\u53CA\u540D\u79F0"},
        {"Principbl.Clbss.not.followed.by.b.Principbl.Nbme",
                "\u4E3B\u7528\u6237\u7C7B\u540E\u9762\u672A\u8DDF\u968F\u4E3B\u7528\u6237\u540D\u79F0"},
        {"Principbl.Nbme.must.be.surrounded.by.quotes",
                "\u4E3B\u7528\u6237\u540D\u79F0\u5FC5\u987B\u653E\u5728\u5F15\u53F7\u5185"},
        {"Principbl.Nbme.missing.end.quote",
                "\u4E3B\u7528\u6237\u540D\u79F0\u7F3A\u5C11\u53F3\u5F15\u53F7"},
        {"PrivbteCredentiblPermission.Principbl.Clbss.cbn.not.be.b.wildcbrd.vblue.if.Principbl.Nbme.is.not.b.wildcbrd.vblue",
                "\u5982\u679C\u4E3B\u7528\u6237\u540D\u79F0\u4E0D\u662F\u901A\u914D\u7B26 (*) \u503C, \u90A3\u4E48 PrivbteCredentiblPermission \u4E3B\u7528\u6237\u7C7B\u4E0D\u80FD\u662F\u901A\u914D\u7B26 (*) \u503C"},
        {"CredOwner.Principbl.Clbss.clbss.Principbl.Nbme.nbme",
                "CredOwner:\n\t\u4E3B\u7528\u6237\u7C7B = {0}\n\t\u4E3B\u7528\u6237\u540D\u79F0 = {1}"},

        // jbvbx.security.buth.x500
        {"provided.null.nbme", "\u63D0\u4F9B\u7684\u540D\u79F0\u4E3A\u7A7A\u503C"},
        {"provided.null.keyword.mbp", "\u63D0\u4F9B\u7684\u5173\u952E\u5B57\u6620\u5C04\u4E3A\u7A7A\u503C"},
        {"provided.null.OID.mbp", "\u63D0\u4F9B\u7684 OID \u6620\u5C04\u4E3A\u7A7A\u503C"},

        // jbvbx.security.buth.Subject
        {"NEWLINE", "\n"},
        {"invblid.null.AccessControlContext.provided",
                "\u63D0\u4F9B\u4E86\u65E0\u6548\u7684\u7A7A AccessControlContext"},
        {"invblid.null.bction.provided", "\u63D0\u4F9B\u4E86\u65E0\u6548\u7684\u7A7A\u64CD\u4F5C"},
        {"invblid.null.Clbss.provided", "\u63D0\u4F9B\u4E86\u65E0\u6548\u7684\u7A7A\u7C7B"},
        {"Subject.", "\u4E3B\u9898: \n"},
        {".Principbl.", "\t\u4E3B\u7528\u6237: "},
        {".Public.Credentibl.", "\t\u516C\u5171\u8EAB\u4EFD\u8BC1\u660E: "},
        {".Privbte.Credentibls.inbccessible.",
                "\t\u65E0\u6CD5\u8BBF\u95EE\u4E13\u7528\u8EAB\u4EFD\u8BC1\u660E\n"},
        {".Privbte.Credentibl.", "\t\u4E13\u7528\u8EAB\u4EFD\u8BC1\u660E: "},
        {".Privbte.Credentibl.inbccessible.",
                "\t\u65E0\u6CD5\u8BBF\u95EE\u4E13\u7528\u8EAB\u4EFD\u8BC1\u660E\n"},
        {"Subject.is.rebd.only", "\u4E3B\u9898\u4E3A\u53EA\u8BFB"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.jbvb.security.Principbl.to.b.Subject.s.Principbl.Set",
                "\u6B63\u5728\u5C1D\u8BD5\u5C06\u4E00\u4E2A\u975E jbvb.security.Principbl \u5B9E\u4F8B\u7684\u5BF9\u8C61\u6DFB\u52A0\u5230\u4E3B\u9898\u7684\u4E3B\u7528\u6237\u96C6\u4E2D"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.clbss",
                "\u6B63\u5728\u5C1D\u8BD5\u6DFB\u52A0\u4E00\u4E2A\u975E{0}\u5B9E\u4F8B\u7684\u5BF9\u8C61"},

        // jbvbx.security.buth.login.AppConfigurbtionEntry
        {"LoginModuleControlFlbg.", "LoginModuleControlFlbg: "},

        // jbvbx.security.buth.login.LoginContext
        {"Invblid.null.input.nbme", "\u65E0\u6548\u7A7A\u8F93\u5165: \u540D\u79F0"},
        {"No.LoginModules.configured.for.nbme",
         "\u6CA1\u6709\u4E3A{0}\u914D\u7F6E LoginModules"},
        {"invblid.null.Subject.provided", "\u63D0\u4F9B\u4E86\u65E0\u6548\u7684\u7A7A\u4E3B\u9898"},
        {"invblid.null.CbllbbckHbndler.provided",
                "\u63D0\u4F9B\u4E86\u65E0\u6548\u7684\u7A7A CbllbbckHbndler"},
        {"null.subject.logout.cblled.before.login",
                "\u7A7A\u4E3B\u9898 - \u5728\u767B\u5F55\u4E4B\u524D\u8C03\u7528\u4E86\u6CE8\u9500"},
        {"unbble.to.instbntibte.LoginModule.module.becbuse.it.does.not.provide.b.no.brgument.constructor",
                "\u65E0\u6CD5\u5B9E\u4F8B\u5316 LoginModule, {0}, \u56E0\u4E3A\u5B83\u672A\u63D0\u4F9B\u4E00\u4E2A\u65E0\u53C2\u6570\u6784\u9020\u5668"},
        {"unbble.to.instbntibte.LoginModule",
                "\u65E0\u6CD5\u5B9E\u4F8B\u5316 LoginModule"},
        {"unbble.to.instbntibte.LoginModule.",
                "\u65E0\u6CD5\u5B9E\u4F8B\u5316 LoginModule: "},
        {"unbble.to.find.LoginModule.clbss.",
                "\u65E0\u6CD5\u627E\u5230 LoginModule \u7C7B: "},
        {"unbble.to.bccess.LoginModule.",
                "\u65E0\u6CD5\u8BBF\u95EE LoginModule: "},
        {"Login.Fbilure.bll.modules.ignored",
                "\u767B\u5F55\u5931\u8D25: \u5FFD\u7565\u6240\u6709\u6A21\u5757"},

        // sun.security.provider.PolicyFile

        {"jbvb.security.policy.error.pbrsing.policy.messbge",
                "jbvb.security.policy: \u89E3\u6790{0}\u65F6\u51FA\u9519:\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Permission.perm.messbge",
                "jbvb.security.policy: \u6DFB\u52A0\u6743\u9650{0}\u65F6\u51FA\u9519:\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Entry.messbge",
                "jbvb.security.policy: \u6DFB\u52A0\u6761\u76EE\u65F6\u51FA\u9519:\n\t{0}"},
        {"blibs.nbme.not.provided.pe.nbme.", "\u672A\u63D0\u4F9B\u522B\u540D ({0})"},
        {"unbble.to.perform.substitution.on.blibs.suffix",
                "\u65E0\u6CD5\u5728\u522B\u540D {0} \u4E0A\u6267\u884C\u66FF\u4EE3"},
        {"substitution.vblue.prefix.unsupported",
                "\u66FF\u4EE3\u503C{0}\u4E0D\u53D7\u652F\u6301"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"type.cbn.t.be.null","\u7C7B\u578B\u4E0D\u80FD\u4E3A\u7A7A\u503C"},

        // sun.security.provider.PolicyPbrser
        {"keystorePbsswordURL.cbn.not.be.specified.without.blso.specifying.keystore",
                "\u4E0D\u6307\u5B9A\u5BC6\u94A5\u5E93\u65F6\u65E0\u6CD5\u6307\u5B9A keystorePbsswordURL"},
        {"expected.keystore.type", "\u5E94\u4E3A\u5BC6\u94A5\u5E93\u7C7B\u578B"},
        {"expected.keystore.provider", "\u5E94\u4E3A\u5BC6\u94A5\u5E93\u63D0\u4F9B\u65B9"},
        {"multiple.Codebbse.expressions",
                "\u591A\u4E2A\u4EE3\u7801\u5E93\u8868\u8FBE\u5F0F"},
        {"multiple.SignedBy.expressions","\u591A\u4E2A SignedBy \u8868\u8FBE\u5F0F"},
        {"duplicbte.keystore.dombin.nbme","\u5BC6\u94A5\u5E93\u57DF\u540D\u91CD\u590D: {0}"},
        {"duplicbte.keystore.nbme","\u5BC6\u94A5\u5E93\u540D\u79F0\u91CD\u590D: {0}"},
        {"SignedBy.hbs.empty.blibs","SignedBy \u6709\u7A7A\u522B\u540D"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "\u6CA1\u6709\u901A\u914D\u7B26\u540D\u79F0, \u65E0\u6CD5\u4F7F\u7528\u901A\u914D\u7B26\u7C7B\u6307\u5B9A\u4E3B\u7528\u6237"},
        {"expected.codeBbse.or.SignedBy.or.Principbl",
                "\u5E94\u4E3A codeBbse, SignedBy \u6216\u4E3B\u7528\u6237"},
        {"expected.permission.entry", "\u5E94\u4E3A\u6743\u9650\u6761\u76EE"},
        {"number.", "\u7F16\u53F7 "},
        {"expected.expect.rebd.end.of.file.",
                "\u5E94\u4E3A [{0}], \u8BFB\u53D6\u7684\u662F [\u6587\u4EF6\u7ED3\u5C3E]"},
        {"expected.rebd.end.of.file.",
                "\u5E94\u4E3A [;], \u8BFB\u53D6\u7684\u662F [\u6587\u4EF6\u7ED3\u5C3E]"},
        {"line.number.msg", "\u5217{0}: {1}"},
        {"line.number.expected.expect.found.bctubl.",
                "\u884C\u53F7 {0}: \u5E94\u4E3A [{1}], \u627E\u5230 [{2}]"},
        {"null.principblClbss.or.principblNbme",
                "principblClbss \u6216 principblNbme \u4E3A\u7A7A\u503C"},

        // sun.security.pkcs11.SunPKCS11
        {"PKCS11.Token.providerNbme.Pbssword.",
                "PKCS11 \u6807\u8BB0 [{0}] \u53E3\u4EE4: "},

        /* --- DEPRECATED --- */
        // jbvbx.security.buth.Policy
        {"unbble.to.instbntibte.Subject.bbsed.policy",
                "\u65E0\u6CD5\u5B9E\u4F8B\u5316\u57FA\u4E8E\u4E3B\u9898\u7684\u7B56\u7565"}
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

