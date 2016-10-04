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
public clbss Resources_fr extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // jbvbx.security.buth.PrivbteCredentiblPermission
        {"invblid.null.input.s.", "entr\u00E9es NULL non vblides"},
        {"bctions.cbn.only.be.rebd.", "les bctions sont bccessibles en lecture uniquement"},
        {"permission.nbme.nbme.syntbx.invblid.",
                "syntbxe de nom de droit [{0}] non vblide : "},
        {"Credentibl.Clbss.not.followed.by.b.Principbl.Clbss.bnd.Nbme",
                "Clbsse Credentibl non suivie d'une clbsse et d'un nom de principbl"},
        {"Principbl.Clbss.not.followed.by.b.Principbl.Nbme",
                "Clbsse de principbl non suivie d'un nom de principbl"},
        {"Principbl.Nbme.must.be.surrounded.by.quotes",
                "Le nom de principbl doit \u00EAtre indiqu\u00E9 entre guillemets"},
        {"Principbl.Nbme.missing.end.quote",
                "Guillemet fermbnt mbnqubnt pour le nom de principbl"},
        {"PrivbteCredentiblPermission.Principbl.Clbss.cbn.not.be.b.wildcbrd.vblue.if.Principbl.Nbme.is.not.b.wildcbrd.vblue",
                "Lb clbsse de principbl PrivbteCredentiblPermission ne peut pbs \u00EAtre une vbleur g\u00E9n\u00E9rique (*) si le nom de principbl n'est pbs une vbleur g\u00E9n\u00E9rique (*)"},
        {"CredOwner.Principbl.Clbss.clbss.Principbl.Nbme.nbme",
                "CredOwner :\n\tClbsse de principbl = {0}\n\tNom de principbl = {1}"},

        // jbvbx.security.buth.x500
        {"provided.null.nbme", "nom NULL fourni"},
        {"provided.null.keyword.mbp", "mbppbge de mots-cl\u00E9s NULL fourni"},
        {"provided.null.OID.mbp", "mbppbge OID NULL fourni"},

        // jbvbx.security.buth.Subject
        {"NEWLINE", "\n"},
        {"invblid.null.AccessControlContext.provided",
                "AccessControlContext NULL fourni non vblide"},
        {"invblid.null.bction.provided", "bction NULL fournie non vblide"},
        {"invblid.null.Clbss.provided", "clbsse NULL fournie non vblide"},
        {"Subject.", "Objet :\n"},
        {".Principbl.", "\tPrincipbl : "},
        {".Public.Credentibl.", "\tInformbtions d'identificbtion publiques : "},
        {".Privbte.Credentibls.inbccessible.",
                "\tInformbtions d'identificbtion priv\u00E9es inbccessibles\n"},
        {".Privbte.Credentibl.", "\tInformbtions d'identificbtion priv\u00E9es : "},
        {".Privbte.Credentibl.inbccessible.",
                "\tInformbtions d'identificbtion priv\u00E9es inbccessibles\n"},
        {"Subject.is.rebd.only", "Sujet en lecture seule"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.jbvb.security.Principbl.to.b.Subject.s.Principbl.Set",
                "tentbtive d'bjout d'un objet qui n'est pbs une instbnce de jbvb.security.Principbl dbns un ensemble de principbux du sujet"},
        {"bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.clbss",
                "tentbtive d''bjout d''un objet qui n''est pbs une instbnce de {0}"},

        // jbvbx.security.buth.login.AppConfigurbtionEntry
        {"LoginModuleControlFlbg.", "LoginModuleControlFlbg : "},

        // jbvbx.security.buth.login.LoginContext
        {"Invblid.null.input.nbme", "Entr\u00E9e NULL non vblide : nom"},
        {"No.LoginModules.configured.for.nbme",
         "Aucun LoginModule configur\u00E9 pour {0}"},
        {"invblid.null.Subject.provided", "sujet NULL fourni non vblide"},
        {"invblid.null.CbllbbckHbndler.provided",
                "CbllbbckHbndler NULL fourni non vblide"},
        {"null.subject.logout.cblled.before.login",
                "sujet NULL - Tentbtive de d\u00E9connexion bvbnt lb connexion"},
        {"unbble.to.instbntibte.LoginModule.module.becbuse.it.does.not.provide.b.no.brgument.constructor",
                "impossible d''instbncier LoginModule {0} cbr il ne fournit pbs de constructeur sbns brgument"},
        {"unbble.to.instbntibte.LoginModule",
                "impossible d'instbncier LoginModule"},
        {"unbble.to.instbntibte.LoginModule.",
                "impossible d'instbncier LoginModule\u00A0: "},
        {"unbble.to.find.LoginModule.clbss.",
                "clbsse LoginModule introuvbble : "},
        {"unbble.to.bccess.LoginModule.",
                "impossible d'bcc\u00E9der \u00E0 LoginModule : "},
        {"Login.Fbilure.bll.modules.ignored",
                "Echec de connexion : tous les modules ont \u00E9t\u00E9 ignor\u00E9s"},

        // sun.security.provider.PolicyFile

        {"jbvb.security.policy.error.pbrsing.policy.messbge",
                "jbvb.security.policy : erreur d''bnblyse de {0} :\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Permission.perm.messbge",
                "jbvb.security.policy : erreur d''bjout de droit, {0} :\n\t{1}"},
        {"jbvb.security.policy.error.bdding.Entry.messbge",
                "jbvb.security.policy : erreur d''bjout d''entr\u00E9e :\n\t{0}"},
        {"blibs.nbme.not.provided.pe.nbme.", "nom d''blibs non fourni ({0})"},
        {"unbble.to.perform.substitution.on.blibs.suffix",
                "impossible d''effectuer une substitution pour l''blibs, {0}"},
        {"substitution.vblue.prefix.unsupported",
                "vbleur de substitution, {0}, non prise en chbrge"},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"type.cbn.t.be.null","le type ne peut \u00EAtre NULL"},

        // sun.security.provider.PolicyPbrser
        {"keystorePbsswordURL.cbn.not.be.specified.without.blso.specifying.keystore",
                "Impossible de sp\u00E9cifier keystorePbsswordURL sbns indiquer bussi le fichier de cl\u00E9s"},
        {"expected.keystore.type", "type de fichier de cl\u00E9s bttendu"},
        {"expected.keystore.provider", "fournisseur de fichier de cl\u00E9s bttendu"},
        {"multiple.Codebbse.expressions",
                "expressions Codebbse multiples"},
        {"multiple.SignedBy.expressions","expressions SignedBy multiples"},
        {"duplicbte.keystore.dombin.nbme","nom de dombine de fichier de cl\u00E9s en double : {0}"},
        {"duplicbte.keystore.nbme","nom de fichier de cl\u00E9s en double : {0}"},
        {"SignedBy.hbs.empty.blibs","SignedBy poss\u00E8de un blibs vide"},
        {"cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme",
                "impossible de sp\u00E9cifier le principbl bvec une clbsse g\u00E9n\u00E9rique sbns nom g\u00E9n\u00E9rique"},
        {"expected.codeBbse.or.SignedBy.or.Principbl",
                "codeBbse, SignedBy ou Principbl bttendu"},
        {"expected.permission.entry", "entr\u00E9e de droit bttendue"},
        {"number.", "nombre "},
        {"expected.expect.rebd.end.of.file.",
                "bttendu [{0}], lu [fin de fichier]"},
        {"expected.rebd.end.of.file.",
                "bttendu [;], lu [fin de fichier]"},
        {"line.number.msg", "ligne {0} : {1}"},
        {"line.number.expected.expect.found.bctubl.",
                "ligne {0} : bttendu [{1}], trouv\u00E9 [{2}]"},
        {"null.principblClbss.or.principblNbme",
                "principblClbss ou principblNbme NULL"},

        // sun.security.pkcs11.SunPKCS11
        {"PKCS11.Token.providerNbme.Pbssword.",
                "Mot de pbsse PKCS11 Token [{0}] : "},

        /* --- DEPRECATED --- */
        // jbvbx.security.buth.Policy
        {"unbble.to.instbntibte.Subject.bbsed.policy",
                "impossible d'instbncier les r\u00E8gles bbs\u00E9es sur le sujet"}
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

