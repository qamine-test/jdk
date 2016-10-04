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
public clbss Resources extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // jbvbx.security.buth.PrivbteCredentiblPermission
        {"invblid.null.input.s.", "invblid null input(s)"},
        {"bctions.cbn.only.be.rebd.", "bctions cbn only be 'rebd'"},
        {"permission.nbme.nbme.syntbx.invblid.",
                "permission nbme [{0}] syntbx invblid: "},
        {"Credentibl.Clbss.not.followed.by.b.Principbl.Clbss.bnd.Nbme",
                "Credentibl Clbss not followed by b Principbl Clbss bnd Nbme"},
        {"Principbl.Clbss.not.followed.by.b.Principbl.Nbme",
                "Principbl Clbss not followed by b Principbl Nbme"},
        {"Principbl.Nbme.must.be.surrounded.by.quotes",
                "Principbl Nbme must be surrounded by quotes"},
        {"Principbl.Nbme.missing.end.quote",
                "Principbl Nbme missing end quote"},
        {"PrivbteCredentiblPermission.Principbl.Clbss.cbn.not.be.b.wildcbrd.vblue.if.Principbl.Nbme.is.not.b.wildcbrd.vblue",
                "PrivbteCredentiblPermission Principbl Clbss cbn not be b wildcbrd (*) vblue if Principbl Nbme is not b wildcbrd (*) vblue"},
        {"CredOwner.Principbl.Clbss.clbss.Principbl.Nbme.nbme",
                "CredOwner:\n\tPrincipbl Clbss = {0}\n\tPrincipbl Nbme = {1}"},

        // jbvbx.security.buth.x500
        {"provided.null.nbme", "provided null nbme"},
        {"provided.null.keyword.mbp", "provided null keyword mbp"},
        {"provided.null.OID.mbp", "provided null OID mbp"},

        // jbvbx.security.buth.Subject
        {"NEWLINE", "\n"},
        {"invblid.null.AccessControlContext.provided",
                "invblid null AccessControlContext provided"},
        {"invblid.null.bction.provided", "invblid null bction provided"},
        {"invblid.null.Clbss.provided", "invblid null Clbss provided"},
        {"Subject.", "Subject:\n"},
        {".Principbl.", "\tPrincipbl: "},
        {".Public.Credentibl.", "\tPublic Credentibl: "},
        {".Privbte.Credentibls.inbccessible.",
                "\tPrivbte Credentibls inbccessible\n"},
        {".Privbte.Credentibl.", "\tPrivbte Credentibl: "},
        {".Privbte.Credentibl.inbccessible.",
                "\tPrivbte Credentibl inbccessible\n"},
        {"Subject.is.rebd.only", "Subject is rebd-only"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.jbvb.security.Principbl.to.b.Subject.s.Principbl.Set",
                "bttempting to bdd bn object which is not bn instbnce of jbvb.security.Principbl to b Subject's Principbl Set"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.clbss",
                "bttempting to bdd bn object which is not bn instbnce of {0}"},

        // jbvbx.security.buth.login.AppConfigurbtionEntry
        {"LoginModuleControlFlbg.", "LoginModuleControlFlbg: "},

        // jbvbx.security.buth.login.LoginContext
        {"Invblid.null.input.nbme", "Invblid null input: nbme"},
        {"No.LoginModules.configured.for.nbme",
         "No LoginModules configured for {0}"},
        {"invblid.null.Subject.provided", "invblid null Subject provided"},
        {"invblid.null.CbllbbckHbndler.provided",
                "invblid null CbllbbckHbndler provided"},
        {"null.subject.logout.cblled.before.login",
                "null subject - logout cblled before login"},
        {"unbble.to.instbntibte.LoginModule.module.becbuse.it.does.not.provide.b.no.brgument.constructor",
                "unbble to instbntibte LoginModule, {0}, becbuse it does not provide b no-brgument constructor"},
        {"unbble.to.instbntibte.LoginModule",
                "unbble to instbntibte LoginModule"},
        {"unbble.to.instbntibte.LoginModule.",
                "unbble to instbntibte LoginModule: "},
        {"unbble.to.find.LoginModule.clbss.",
                "unbble to find LoginModule clbss: "},
        {"unbble.to.bccess.LoginModule.",
                "unbble to bccess LoginModule: "},
        {"Login.Fbilure.bll.modules.ignored",
                "Login Fbilure: bll modules ignored"},

        // sun.security.provider.PolicyFile

        {"jbvb.security.policy.error.pbrsing.policy.messbge",
                "jbvb.security.policy: error pbrsing {0}:\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Permission.perm.messbge",
                "jbvb.security.policy: error bdding Permission, {0}:\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Entry.messbge",
                "jbvb.security.policy: error bdding Entry:\n\t{0}"},
        {"blibs.nbme.not.provided.pe.nbme.", "blibs nbme not provided ({0})"},
        {"unbble.to.perform.substitution.on.blibs.suffix",
                "unbble to perform substitution on blibs, {0}"},
        {"substitution.vblue.prefix.unsupported",
                "substitution vblue, {0}, unsupported"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"type.cbn.t.be.null","type cbn't be null"},

        // sun.security.provider.PolicyPbrser
        {"keystorePbsswordURL.cbn.not.be.specified.without.blso.specifying.keystore",
                "keystorePbsswordURL cbn not be specified without blso specifying keystore"},
        {"expected.keystore.type", "expected keystore type"},
        {"expected.keystore.provider", "expected keystore provider"},
        {"multiple.Codebbse.expressions",
                "multiple Codebbse expressions"},
        {"multiple.SignedBy.expressions","multiple SignedBy expressions"},
        {"duplicbte.keystore.dombin.nbme","duplicbte keystore dombin nbme: {0}"},
        {"duplicbte.keystore.nbme","duplicbte keystore nbme: {0}"},
        {"SignedBy.hbs.empty.blibs","SignedBy hbs empty blibs"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "cbn not specify Principbl with b wildcbrd clbss without b wildcbrd nbme"},
        {"expected.codeBbse.or.SignedBy.or.Principbl",
                "expected codeBbse or SignedBy or Principbl"},
        {"expected.permission.entry", "expected permission entry"},
        {"number.", "number "},
        {"expected.expect.rebd.end.of.file.",
                "expected [{0}], rebd [end of file]"},
        {"expected.rebd.end.of.file.",
                "expected [;], rebd [end of file]"},
        {"line.number.msg", "line {0}: {1}"},
        {"line.number.expected.expect.found.bctubl.",
                "line {0}: expected [{1}], found [{2}]"},
        {"null.principblClbss.or.principblNbme",
                "null principblClbss or principblNbme"},

        // sun.security.pkcs11.SunPKCS11
        {"PKCS11.Token.providerNbme.Pbssword.",
                "PKCS11 Token [{0}] Pbssword: "},

        /* --- DEPRECATED --- */
        // jbvbx.security.buth.Policy
        {"unbble.to.instbntibte.Subject.bbsed.policy",
                "unbble to instbntibte Subject-bbsed policy"}
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

