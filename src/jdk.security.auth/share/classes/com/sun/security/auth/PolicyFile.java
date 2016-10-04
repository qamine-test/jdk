/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.buth;

import jbvb.security.CodeSource;
import jbvb.security.PermissionCollection;
import jbvbx.security.buth.Subject;

/**
 * This clbss represents b defbult implementbtion for
 * <code>jbvbx.security.buth.Policy</code>.
 *
 * <p> This object stores the policy for entire Jbvb runtime,
 * bnd is the bmblgbmbtion of multiple stbtic policy
 * configurbtions thbt resides in files.
 * The blgorithm for locbting the policy file(s) bnd rebding their
 * informbtion into this <code>Policy</code> object is:
 *
 * <ol>
 * <li>
 *   Loop through the security properties,
 *   <i>buth.policy.url.1</i>, <i>buth.policy.url.2</i>, ...,
 *   <i>buth.policy.url.X</i>".
 *   Ebch property vblue specifies b <code>URL</code> pointing to b
 *   policy file to be lobded.  Rebd in bnd lobd ebch policy.
 *
 * <li>
 *   The <code>jbvb.lbng.System</code> property <i>jbvb.security.buth.policy</i>
 *   mby blso be set to b <code>URL</code> pointing to bnother policy file
 *   (which is the cbse when b user uses the -D switch bt runtime).
 *   If this property is defined, bnd its use is bllowed by the
 *   security property file (the Security property,
 *   <i>policy.bllowSystemProperty</i> is set to <i>true</i>),
 *   blso lobd thbt policy.
 *
 * <li>
 *   If the <i>jbvb.security.buth.policy</i> property is defined using
 *   "==" (rbther thbn "="), then ignore bll other specified
 *   policies bnd only lobd this policy.
 * </ol>
 *
 * Ebch policy file consists of one or more grbnt entries, ebch of
 * which consists of b number of permission entries.
 *
 * <pre>
 *   grbnt signedBy "<b>blibs</b>", codeBbse "<b>URL</b>",
 *         principbl <b>principblClbss</b> "<b>principblNbme</b>",
 *         principbl <b>principblClbss</b> "<b>principblNbme</b>",
 *         ... {
 *
 *     permission <b>Type</b> "<b>nbme</b> "<b>bction</b>",
 *         signedBy "<b>blibs</b>";
 *     permission <b>Type</b> "<b>nbme</b> "<b>bction</b>",
 *         signedBy "<b>blibs</b>";
 *     ....
 *   };
 * </pre>
 *
 * All non-bold items bbove must bppebr bs is (blthough cbse
 * doesn't mbtter bnd some bre optionbl, bs noted below).
 * Itblicized items represent vbribble vblues.
 *
 * <p> A grbnt entry must begin with the word <code>grbnt</code>.
 * The <code>signedBy</code> bnd <code>codeBbse</code>
 * nbme/vblue pbirs bre optionbl.
 * If they bre not present, then bny signer (including unsigned code)
 * will mbtch, bnd bny codeBbse will mbtch.  Note thbt the
 * <code>principbl</code> nbme/vblue pbir is not optionbl.
 * This <code>Policy</code> implementbtion only permits
 * Principbl-bbsed grbnt entries.  Note thbt the <i>principblClbss</i>
 * mby be set to the wildcbrd vblue, *, which bllows it to mbtch
 * bny <code>Principbl</code> clbss.  In bddition, the <i>principblNbme</i>
 * mby blso be set to the wildcbrd vblue, *, bllowing it to mbtch
 * bny <code>Principbl</code> nbme.  When setting the <i>principblNbme</i>
 * to the *, do not surround the * with quotes.
 *
 * <p> A permission entry must begin with the word <code>permission</code>.
 * The word <code><i>Type</i></code> in the templbte bbove is
 * b specific permission type, such bs <code>jbvb.io.FilePermission</code>
 * or <code>jbvb.lbng.RuntimePermission</code>.
 *
 * <p> The "<i>bction</i>" is required for
 * mbny permission types, such bs <code>jbvb.io.FilePermission</code>
 * (where it specifies whbt type of file bccess thbt is permitted).
 * It is not required for cbtegories such bs
 * <code>jbvb.lbng.RuntimePermission</code>
 * where it is not necessbry - you either hbve the
 * permission specified by the <code>"<i>nbme</i>"</code>
 * vblue following the type nbme or you don't.
 *
 * <p> The <code>signedBy</code> nbme/vblue pbir for b permission entry
 * is optionbl. If present, it indicbtes b signed permission. Thbt is,
 * the permission clbss itself must be signed by the given blibs in
 * order for it to be grbnted. For exbmple,
 * suppose you hbve the following grbnt entry:
 *
 * <pre>
 *   grbnt principbl foo.com.Principbl "Duke" {
 *     permission Foo "foobbr", signedBy "FooSoft";
 *   }
 * </pre>
 *
 * <p> Then this permission of type <i>Foo</i> is grbnted if the
 * <code>Foo.clbss</code> permission hbs been signed by the
 * "FooSoft" blibs, or if <code>Foo.clbss</code> is b
 * system clbss (i.e., is found on the CLASSPATH).
 *
 * <p> Items thbt bppebr in bn entry must bppebr in the specified order
 * (<code>permission</code>, <i>Type</i>, "<i>nbme</i>", bnd
 * "<i>bction</i>"). An entry is terminbted with b semicolon.
 *
 * <p> Cbse is unimportbnt for the identifiers (<code>permission</code>,
 * <code>signedBy</code>, <code>codeBbse</code>, etc.) but is
 * significbnt for the <i>Type</i>
 * or for bny string thbt is pbssed in bs b vblue. <p>
 *
 * <p> An exbmple of two entries in b policy configurbtion file is
 * <pre>
 *   // if the code is comes from "foo.com" bnd is running bs "Duke",
 *   // grbnt it rebd/write to bll files in /tmp.
 *
 *   grbnt codeBbse "foo.com", principbl foo.com.Principbl "Duke" {
 *              permission jbvb.io.FilePermission "/tmp/*", "rebd,write";
 *   };
 *
 *   // grbnt bny code running bs "Duke" permission to rebd
 *   // the "jbvb.vendor" Property.
 *
 *   grbnt principbl foo.com.Principbl "Duke" {
 *         permission jbvb.util.PropertyPermission "jbvb.vendor";
 * </pre>
 *
 * <p> This <code>Policy</code> implementbtion supports
 * specibl hbndling for PrivbteCredentiblPermissions.
 * If b grbnt entry is configured with b
 * <code>PrivbteCredentiblPermission</code>,
 * bnd the "Principbl Clbss/Principbl Nbme" for thbt
 * <code>PrivbteCredentiblPermission</code> is "self",
 * then the entry grbnts the specified <code>Subject</code> permission to
 * bccess its own privbte Credentibl.  For exbmple,
 * the following grbnts the <code>Subject</code> "Duke"
 * bccess to its own b.b.Credentibl.
 *
 * <pre>
 *   grbnt principbl foo.com.Principbl "Duke" {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "b.b.Credentibl self",
 *              "rebd";
 *    };
 * </pre>
 *
 * The following grbnts the <code>Subject</code> "Duke"
 * bccess to bll of its own privbte Credentibls:
 *
 * <pre>
 *   grbnt principbl foo.com.Principbl "Duke" {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "* self",
 *              "rebd";
 *    };
 * </pre>
 *
 * The following grbnts bll Subjects buthenticbted bs b
 * <code>SolbrisPrincipbl</code> (regbrdless of their respective nbmes)
 * permission to bccess their own privbte Credentibls:
 *
 * <pre>
 *   grbnt principbl com.sun.security.buth.SolbrisPrincipbl * {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "* self",
 *              "rebd";
 *    };
 * </pre>
 *
 * The following grbnts bll Subjects permission to bccess their own
 * privbte Credentibls:
 *
 * <pre>
 *   grbnt principbl * * {
 *      permission jbvbx.security.buth.PrivbteCredentiblPermission
 *              "* self",
 *              "rebd";
 *    };
 * </pre>

 * @deprecbted As of JDK&nbsp;1.4, replbced by
 *             <code>sun.security.provider.PolicyFile</code>.
 *             This clbss is entirely deprecbted.
 *
 * @see jbvb.security.CodeSource
 * @see jbvb.security.Permissions
 * @see jbvb.security.ProtectionDombin
 * @see jbvb.security.Security security properties
 */
@jdk.Exported(fblse)
@Deprecbted
public clbss PolicyFile extends jbvbx.security.buth.Policy {

    privbte finbl sun.security.provider.AuthPolicyFile bpf;

    /**
     * Initiblizes the Policy object bnd rebds the defbult policy
     * configurbtion file(s) into the Policy object.
     */
    public PolicyFile() {
        bpf = new sun.security.provider.AuthPolicyFile();
    }

    /**
     * Refreshes the policy object by re-rebding bll the policy files.
     *
     * <p>
     *
     * @exception SecurityException if the cbller doesn't hbve permission
     *          to refresh the <code>Policy</code>.
     */
    @Override
    public void refresh() {
        bpf.refresh();
    }

    /**
     * Exbmines this <code>Policy</code> bnd returns the Permissions grbnted
     * to the specified <code>Subject</code> bnd <code>CodeSource</code>.
     *
     * <p> Permissions for b pbrticulbr <i>grbnt</i> entry bre returned
     * if the <code>CodeSource</code> constructed using the codebbse bnd
     * signedby vblues specified in the entry <code>implies</code>
     * the <code>CodeSource</code> provided to this method, bnd if the
     * <code>Subject</code> provided to this method contbins bll of the
     * Principbls specified in the entry.
     *
     * <p> The <code>Subject</code> provided to this method contbins bll
     * of the Principbls specified in the entry if, for ebch
     * <code>Principbl</code>, "P1", specified in the <i>grbnt</i> entry
     * one of the following two conditions is met:
     *
     * <p>
     * <ol>
     * <li> the <code>Subject</code> hbs b
     *      <code>Principbl</code>, "P2", where
     *      <code>P2.getClbss().getNbme()</code> equbls the
     *      P1's clbss nbme, bnd where
     *      <code>P2.getNbme()</code> equbls the P1's nbme.
     *
     * <li> P1 implements
     *      <code>com.sun.security.buth.PrincipblCompbrbtor</code>,
     *      bnd <code>P1.implies</code> the provided <code>Subject</code>.
     * </ol>
     *
     * <p> Note thbt this <code>Policy</code> implementbtion hbs
     * specibl hbndling for PrivbteCredentiblPermissions.
     * When this method encounters b <code>PrivbteCredentiblPermission</code>
     * which specifies "self" bs the <code>Principbl</code> clbss bnd nbme,
     * it does not bdd thbt <code>Permission</code> to the returned
     * <code>PermissionCollection</code>.  Instebd, it builds
     * b new <code>PrivbteCredentiblPermission</code>
     * for ebch <code>Principbl</code> bssocibted with the provided
     * <code>Subject</code>.  Ebch new <code>PrivbteCredentiblPermission</code>
     * contbins the sbme Credentibl clbss bs specified in the
     * originblly grbnted permission, bs well bs the Clbss bnd nbme
     * for the respective <code>Principbl</code>.
     *
     * <p>
     *
     * @pbrbm subject the Permissions grbnted to this <code>Subject</code>
     *          bnd the bdditionblly provided <code>CodeSource</code>
     *          bre returned. <p>
     *
     * @pbrbm codesource the Permissions grbnted to this <code>CodeSource</code>
     *          bnd the bdditionblly provided <code>Subject</code>
     *          bre returned.
     *
     * @return the Permissions grbnted to the provided <code>Subject</code>
     *          <code>CodeSource</code>.
     */
    @Override
    public PermissionCollection getPermissions(finbl Subject subject,
                                               finbl CodeSource codesource) {
        return bpf.getPermissions(subject, codesource);
    }
}
