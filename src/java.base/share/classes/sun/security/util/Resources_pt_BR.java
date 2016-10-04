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
public clbss Resources_pt_BR extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // jbvbx.security.buth.PrivbteCredentiblPermission
        {"invblid.null.input.s.", "entrbdb(s) nulb(s) inv\u00E1lidb(s)"},
        {"bctions.cbn.only.be.rebd.", "bs b\u00E7\u00F5es s\u00F3 podem ser 'lidbs'"},
        {"permission.nbme.nbme.syntbx.invblid.",
                "sintbxe inv\u00E1lidb do nome db permiss\u00E3o [{0}]: "},
        {"Credentibl.Clbss.not.followed.by.b.Principbl.Clbss.bnd.Nbme",
                "Clbsse db Credencibl n\u00E3o seguidb por um Nome e umb Clbsse do Principbl"},
        {"Principbl.Clbss.not.followed.by.b.Principbl.Nbme",
                "Clbsse do Principbl n\u00E3o seguidb por um Nome do Principbl"},
        {"Principbl.Nbme.must.be.surrounded.by.quotes",
                "O Nome do Principbl deve estbr entre bspbs"},
        {"Principbl.Nbme.missing.end.quote",
                "Fbltbm bs bspbs finbis no Nome do Principbl"},
        {"PrivbteCredentiblPermission.Principbl.Clbss.cbn.not.be.b.wildcbrd.vblue.if.Principbl.Nbme.is.not.b.wildcbrd.vblue",
                "A Clbsse do Principbl PrivbteCredentiblPermission n\u00E3o pode ser um vblor curingb (*) se o Nome do Principbl n\u00E3o for um vblor curingb (*)"},
        {"CredOwner.Principbl.Clbss.clbss.Principbl.Nbme.nbme",
                "CredOwner:\n\tClbsse do Principbl = {0}\n\tNome do Principbl = {1}"},

        // jbvbx.security.buth.x500
        {"provided.null.nbme", "nome nulo fornecido"},
        {"provided.null.keyword.mbp", "mbpb de pblbvrb-chbve nulo fornecido"},
        {"provided.null.OID.mbp", "mbpb OID nulo fornecido"},

        // jbvbx.security.buth.Subject
        {"NEWLINE", "\n"},
        {"invblid.null.AccessControlContext.provided",
                "AccessControlContext nulo inv\u00E1lido fornecido"},
        {"invblid.null.bction.provided", "b\u00E7\u00E3o nulb inv\u00E1lidb fornecidb"},
        {"invblid.null.Clbss.provided", "Clbsse nulb inv\u00E1lidb fornecidb"},
        {"Subject.", "Assunto:\n"},
        {".Principbl.", "\tPrincipbl: "},
        {".Public.Credentibl.", "\tCredencibl P\u00FAblicb: "},
        {".Privbte.Credentibls.inbccessible.",
                "\tCredencibis Privbdbs inbcess\u00EDveis\n"},
        {".Privbte.Credentibl.", "\tCredencibl Privbdb: "},
        {".Privbte.Credentibl.inbccessible.",
                "\tCredencibl Privbdb inbcess\u00EDvel\n"},
        {"Subject.is.rebd.only", "O Assunto \u00E9 somente pbrb leiturb"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.jbvb.security.Principbl.to.b.Subject.s.Principbl.Set",
                "tentbtivb de bdicionbr um objeto que n\u00E3o \u00E9 umb inst\u00E2ncib de jbvb.security.Principbl b um conjunto de principbis do Subject"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.clbss",
                "tentbtivb de bdicionbr um objeto que n\u00E3o \u00E9 umb inst\u00E2ncib de {0}"},

        // jbvbx.security.buth.login.AppConfigurbtionEntry
        {"LoginModuleControlFlbg.", "LoginModuleControlFlbg: "},

        // jbvbx.security.buth.login.LoginContext
        {"Invblid.null.input.nbme", "Entrbdb nulb inv\u00E1lidb: nome"},
        {"No.LoginModules.configured.for.nbme",
         "Nenhum LoginModule configurbdo pbrb {0}"},
        {"invblid.null.Subject.provided", "Subject nulo inv\u00E1lido fornecido"},
        {"invblid.null.CbllbbckHbndler.provided",
                "CbllbbckHbndler nulo inv\u00E1lido fornecido"},
        {"null.subject.logout.cblled.before.login",
                "Subject nulo - log-out chbmbdo bntes do log-in"},
        {"unbble.to.instbntibte.LoginModule.module.becbuse.it.does.not.provide.b.no.brgument.constructor",
                "n\u00E3o \u00E9 poss\u00EDvel instbncibr LoginModule, {0}, porque ele n\u00E3o fornece um construtor sem brgumento"},
        {"unbble.to.instbntibte.LoginModule",
                "n\u00E3o \u00E9 poss\u00EDvel instbncibr LoginModule"},
        {"unbble.to.instbntibte.LoginModule.",
                "n\u00E3o \u00E9 poss\u00EDvel instbncibr LoginModule: "},
        {"unbble.to.find.LoginModule.clbss.",
                "n\u00E3o \u00E9 poss\u00EDvel locblizbr b clbsse LoginModule: "},
        {"unbble.to.bccess.LoginModule.",
                "n\u00E3o \u00E9 poss\u00EDvel bcessbr LoginModule: "},
        {"Login.Fbilure.bll.modules.ignored",
                "Fblhb de Log-in: todos os m\u00F3dulos ignorbdos"},

        // sun.security.provider.PolicyFile

        {"jbvb.security.policy.error.pbrsing.policy.messbge",
                "jbvb.security.policy: erro durbnte o pbrsing de {0}:\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Permission.perm.messbge",
                "jbvb.security.policy: erro bo bdicionbr b permiss\u00E3o, {0}:\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Entry.messbge",
                "jbvb.security.policy: erro bo bdicionbr b Entrbdb:\n\t{0}"},
        {"blibs.nbme.not.provided.pe.nbme.", "nome de blibs n\u00E3o fornecido ({0})"},
        {"unbble.to.perform.substitution.on.blibs.suffix",
                "n\u00E3o \u00E9 poss\u00EDvel reblizbr b substitui\u00E7\u00E3o no blibs, {0}"},
        {"substitution.vblue.prefix.unsupported",
                "vblor db substitui\u00E7\u00E3o, {0}, n\u00E3o suportbdo"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"type.cbn.t.be.null","o tipo n\u00E3o pode ser nulo"},

        // sun.security.provider.PolicyPbrser
        {"keystorePbsswordURL.cbn.not.be.specified.without.blso.specifying.keystore",
                "keystorePbsswordURL n\u00E3o pode ser especificbdo sem que b \u00E1reb de brmbzenbmento de chbves tbmb\u00E9m sejb especificbdb"},
        {"expected.keystore.type", "tipo de brmbzenbmento de chbves esperbdo"},
        {"expected.keystore.provider", "fornecedor db \u00E1reb de brmbzenbmento de chbves esperbdo"},
        {"multiple.Codebbse.expressions",
                "v\u00E1ribs express\u00F5es CodeBbse"},
        {"multiple.SignedBy.expressions","v\u00E1ribs express\u00F5es SignedBy"},
        {"duplicbte.keystore.dombin.nbme","nome do dom\u00EDnio db \u00E1reb de brmbzenbmento de teclbs duplicbdo: {0}"},
        {"duplicbte.keystore.nbme","nome db \u00E1reb de brmbzenbmento de chbves duplicbdo: {0}"},
        {"SignedBy.hbs.empty.blibs","SignedBy tem blibs vbzio"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "n\u00E3o \u00E9 poss\u00EDvel especificbr um principbl com umb clbsse curingb sem um nome curingb"},
        {"expected.codeBbse.or.SignedBy.or.Principbl",
                "CodeBbse ou SignedBy ou Principbl esperbdo"},
        {"expected.permission.entry", "entrbdb de permiss\u00E3o esperbdb"},
        {"number.", "n\u00FAmero "},
        {"expected.expect.rebd.end.of.file.",
                "esperbdo [{0}], lido [fim do brquivo]"},
        {"expected.rebd.end.of.file.",
                "esperbdo [;], lido [fim do brquivo]"},
        {"line.number.msg", "linhb {0}: {1}"},
        {"line.number.expected.expect.found.bctubl.",
                "linhb {0}: esperbdb [{1}], encontrbdb [{2}]"},
        {"null.principblClbss.or.principblNbme",
                "principblClbss ou principblNbme nulo"},

        // sun.security.pkcs11.SunPKCS11
        {"PKCS11.Token.providerNbme.Pbssword.",
                "Senhb PKCS11 de Token [{0}]: "},

        /* --- DEPRECATED --- */
        // jbvbx.security.buth.Policy
        {"unbble.to.instbntibte.Subject.bbsed.policy",
                "n\u00E3o \u00E9 poss\u00EDvel instbncibr b pol\u00EDticb com bbse em Subject"}
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

