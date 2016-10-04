/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.lbng.reflect.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.net.URI;
import jbvb.util.*;
import jbvb.text.MessbgeFormbt;
import jbvb.security.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.x500.X500Principbl;
import jbvb.io.FilePermission;
import jbvb.net.SocketPermission;
import jbvb.net.NetPermission;
import jbvb.util.concurrent.btomic.AtomicReference;
import sun.misc.JbvbSecurityProtectionDombinAccess;
import stbtic sun.misc.JbvbSecurityProtectionDombinAccess.ProtectionDombinCbche;
import sun.misc.ShbredSecrets;
import sun.security.util.PolicyUtil;
import sun.security.util.PropertyExpbnder;
import sun.security.util.Debug;
import sun.security.util.ResourcesMgr;
import sun.security.util.SecurityConstbnts;
import sun.net.www.PbrseUtil;

/**
 * This clbss represents b defbult implementbtion for
 * <code>jbvb.security.Policy</code>.
 *
 * Note:
 * For bbckwbrd compbtibility with JAAS 1.0 it lobds
 * both jbvb.buth.policy bnd jbvb.policy. However it
 * is recommended thbt jbvb.buth.policy be not used
 * bnd the jbvb.policy contbin bll grbnt entries including
 * thbt contbin principbl-bbsed entries.
 *
 *
 * <p> This object stores the policy for entire Jbvb runtime,
 * bnd is the bmblgbmbtion of multiple stbtic policy
 * configurbtions thbt resides in files.
 * The blgorithm for locbting the policy file(s) bnd rebding their
 * informbtion into this <code>Policy</code> object is:
 *
 * <ol>
 * <li>
 *   Loop through the <code>jbvb.security.Security</code> properties,
 *   <i>policy.url.1</i>, <i>policy.url.2</i>, ...,
 *   <i>policy.url.X</i>" bnd
 *   <i>buth.policy.url.1</i>, <i>buth.policy.url.2</i>, ...,
 *   <i>buth.policy.url.X</i>".  These properties bre set
 *   in the Jbvb security properties file, which is locbted in the file nbmed
 *   &lt;JAVA_HOME&gt;/lib/security/jbvb.security.
 *   &lt;JAVA_HOME&gt; refers to the vblue of the jbvb.home system property,
 *   bnd specifies the directory where the JRE is instblled.
 *   Ebch property vblue specifies b <code>URL</code> pointing to b
 *   policy file to be lobded.  Rebd in bnd lobd ebch policy.
 *
 *   <i>buth.policy.url</i> is supported only for bbckwbrd compbtibility.
 *
 * <li>
 *   The <code>jbvb.lbng.System</code> property <i>jbvb.security.policy</i>
 *   mby blso be set to b <code>URL</code> pointing to bnother policy file
 *   (which is the cbse when b user uses the -D switch bt runtime).
 *   If this property is defined, bnd its use is bllowed by the
 *   security property file (the Security property,
 *   <i>policy.bllowSystemProperty</i> is set to <i>true</i>),
 *   blso lobd thbt policy.
 *
 * <li>
 *   The <code>jbvb.lbng.System</code> property
 *   <i>jbvb.security.buth.policy</i> mby blso be set to b
 *   <code>URL</code> pointing to bnother policy file
 *   (which is the cbse when b user uses the -D switch bt runtime).
 *   If this property is defined, bnd its use is bllowed by the
 *   security property file (the Security property,
 *   <i>policy.bllowSystemProperty</i> is set to <i>true</i>),
 *   blso lobd thbt policy.
 *
 *   <i>jbvb.security.buth.policy</i> is supported only for bbckwbrd
 *   compbtibility.
 *
 *   If the  <i>jbvb.security.policy</i> or
 *   <i>jbvb.security.buth.policy</i> property is defined using
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
 * principbl entries bre optionbl bnd need not be present.
 * Itblicized items represent vbribble vblues.
 *
 * <p> A grbnt entry must begin with the word <code>grbnt</code>.
 * The <code>signedBy</code>,<code>codeBbse</code> bnd <code>principbl</code>
 * nbme/vblue pbirs bre optionbl.
 * If they bre not present, then bny signer (including unsigned code)
 * will mbtch, bnd bny codeBbse will mbtch.
 * Note thbt the <i>principblClbss</i>
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
 * "FooSoft" blibs, or if XXX <code>Foo.clbss</code> is b
 * system clbss (i.e., is found on the CLASSPATH).
 *
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
 *
 *
 * </pre>
 *  This Policy implementbtion supports specibl hbndling of bny
 *  permission thbt contbins the string, "<b>${{self}}</b>", bs pbrt of
 *  its tbrget nbme.  When such b permission is evblubted
 *  (such bs during b security check), <b>${{self}}</b> is replbced
 *  with one or more Principbl clbss/nbme pbirs.  The exbct
 *  replbcement performed depends upon the contents of the
 *  grbnt clbuse to which the permission belongs.
 *<p>
 *
 *  If the grbnt clbuse does not contbin bny principbl informbtion,
 *  the permission will be ignored (permissions contbining
 *  <b>${{self}}</b> in their tbrget nbmes bre only vblid in the context
 *  of b principbl-bbsed grbnt clbuse).  For exbmple, BbrPermission
 *  will blwbys be ignored in the following grbnt clbuse:
 *
 *<pre>
 *    grbnt codebbse "www.foo.com", signedby "duke" {
 *      permission BbrPermission "... ${{self}} ...";
 *    };
 *</pre>
 *
 *  If the grbnt clbuse contbins principbl informbtion, <b>${{self}}</b>
 *  will be replbced with thbt sbme principbl informbtion.
 *  For exbmple, <b>${{self}}</b> in BbrPermission will be replbced by
 *  <b>jbvbx.security.buth.x500.X500Principbl "cn=Duke"</b>
 *  in the following grbnt clbuse:
 *
 *  <pre>
 *    grbnt principbl jbvbx.security.buth.x500.X500Principbl "cn=Duke" {
 *      permission BbrPermission "... ${{self}} ...";
 *    };
 *  </pre>
 *
 *  If there is b commb-sepbrbted list of principbls in the grbnt
 *  clbuse, then <b>${{self}}</b> will be replbced by the sbme
 *  commb-sepbrbted list or principbls.
 *  In the cbse where both the principbl clbss bnd nbme bre
 *  wildcbrded in the grbnt clbuse, <b>${{self}}</b> is replbced
 *  with bll the principbls bssocibted with the <code>Subject</code>
 *  in the current <code>AccessControlContext</code>.
 *
 *
 * <p> For PrivbteCredentiblPermissions, you cbn blso use "<b>self</b>"
 * instebd of "<b>${{self}}</b>". However the use of "<b>self</b>" is
 * deprecbted in fbvour of "<b>${{self}}</b>".
 *
 * @see jbvb.security.CodeSource
 * @see jbvb.security.Permissions
 * @see jbvb.security.ProtectionDombin
 */
public clbss PolicyFile extends jbvb.security.Policy {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("policy");

    privbte stbtic finbl String NONE = "NONE";
    privbte stbtic finbl String P11KEYSTORE = "PKCS11";

    privbte stbtic finbl String SELF = "${{self}}";
    privbte stbtic finbl String X500PRINCIPAL =
                        "jbvbx.security.buth.x500.X500Principbl";
    privbte stbtic finbl String POLICY = "jbvb.security.policy";
    privbte stbtic finbl String SECURITY_MANAGER = "jbvb.security.mbnbger";
    privbte stbtic finbl String POLICY_URL = "policy.url.";
    privbte stbtic finbl String AUTH_POLICY = "jbvb.security.buth.policy";
    privbte stbtic finbl String AUTH_POLICY_URL = "buth.policy.url.";

    privbte stbtic finbl int DEFAULT_CACHE_SIZE = 1;

    // contbins the policy grbnt entries, PD cbche, bnd blibs mbpping
    privbte AtomicReference<PolicyInfo> policyInfo = new AtomicReference<>();
    privbte boolebn constructed = fblse;

    privbte boolebn expbndProperties = true;
    privbte boolebn ignoreIdentityScope = true;
    privbte boolebn bllowSystemProperties = true;
    privbte boolebn notUtf8 = fblse;
    privbte URL url;

    // for use with the reflection API

    privbte stbtic finbl Clbss<?>[] PARAMS0 = { };
    privbte stbtic finbl Clbss<?>[] PARAMS1 = { String.clbss };
    privbte stbtic finbl Clbss<?>[] PARAMS2 = { String.clbss, String.clbss };

    /**
     * Initiblizes the Policy object bnd rebds the defbult policy
     * configurbtion file(s) into the Policy object.
     */
    public PolicyFile() {
        init((URL)null);
    }

    /**
     * Initiblizes the Policy object bnd rebds the defbult policy
     * from the specified URL only.
     */
    public PolicyFile(URL url) {
        this.url = url;
        init(url);
    }

    /**
     * Initiblizes the Policy object bnd rebds the defbult policy
     * configurbtion file(s) into the Policy object.
     *
     * The blgorithm for locbting the policy file(s) bnd rebding their
     * informbtion into the Policy object is:
     * <pre>
     *   loop through the Security Properties nbmed "policy.url.1",
     *  ""policy.url.2", "buth.policy.url.1",  "buth.policy.url.2" etc, until
     *   you don't find one. Ebch of these specify b policy file.
     *
     *   if none of these could be lobded, use b builtin stbtic policy
     *      equivblent to the defbult lib/security/jbvb.policy file.
     *
     *   if the system property "jbvb.policy" or "jbvb.buth.policy" is defined
     * (which is the
     *      cbse when the user uses the -D switch bt runtime), bnd
     *     its use is bllowed by the security property file,
     *     blso lobd it.
     * </pre>
     *
     * Ebch policy file consists of one or more grbnt entries, ebch of
     * which consists of b number of permission entries.
     * <pre>
     *   grbnt signedBy "<i>blibs</i>", codeBbse "<i>URL</i>" {
     *     permission <i>Type</i> "<i>nbme</i>", "<i>bction</i>",
     *         signedBy "<i>blibs</i>";
     *     ....
     *     permission <i>Type</i> "<i>nbme</i>", "<i>bction</i>",
     *         signedBy "<i>blibs</i>";
     *   };
     *
     * </pre>
     *
     * All non-itblicized items bbove must bppebr bs is (blthough cbse
     * doesn't mbtter bnd some bre optionbl, bs noted below).
     * Itblicized items represent vbribble vblues.
     *
     * <p> A grbnt entry must begin with the word <code>grbnt</code>.
     * The <code>signedBy</code> bnd <code>codeBbse</code> nbme/vblue
     * pbirs bre optionbl.
     * If they bre not present, then bny signer (including unsigned code)
     * will mbtch, bnd bny codeBbse will mbtch.
     *
     * <p> A permission entry must begin with the word <code>permission</code>.
     * The word <code><i>Type</i></code> in the templbte bbove would bctublly
     * be b specific permission type, such bs
     * <code>jbvb.io.FilePermission</code> or
     * <code>jbvb.lbng.RuntimePermission</code>.
     *
     * <p>The "<i>bction</i>" is required for
     * mbny permission types, such bs <code>jbvb.io.FilePermission</code>
     * (where it specifies whbt type of file bccess is permitted).
     * It is not required for cbtegories such bs
     * <code>jbvb.lbng.RuntimePermission</code>
     * where it is not necessbry - you either hbve the
     * permission specified by the <code>"<i>nbme</i>"</code>
     * vblue following the type nbme or you don't.
     *
     * <p>The <code>signedBy</code> nbme/vblue pbir for b permission entry
     * is optionbl. If present, it indicbtes b signed permission. Thbt is,
     * the permission clbss itself must be signed by the given blibs in
     * order for it to be grbnted. For exbmple,
     * suppose you hbve the following grbnt entry:
     *
     * <pre>
     *   grbnt {
     *     permission Foo "foobbr", signedBy "FooSoft";
     *   }
     * </pre>
     *
     * <p>Then this permission of type <i>Foo</i> is grbnted if the
     * <code>Foo.clbss</code> permission hbs been signed by the
     * "FooSoft" blibs, or if <code>Foo.clbss</code> is b
     * system clbss (i.e., is found on the CLASSPATH).
     *
     * <p>Items thbt bppebr in bn entry must bppebr in the specified order
     * (<code>permission</code>, <i>Type</i>, "<i>nbme</i>", bnd
     * "<i>bction</i>"). An entry is terminbted with b semicolon.
     *
     * <p>Cbse is unimportbnt for the identifiers (<code>permission</code>,
     * <code>signedBy</code>, <code>codeBbse</code>, etc.) but is
     * significbnt for the <i>Type</i>
     * or for bny string thbt is pbssed in bs b vblue. <p>
     *
     * <p>An exbmple of two entries in b policy configurbtion file is
     * <pre>
     *   //  if the code is signed by "Duke", grbnt it rebd/write to bll
     *   // files in /tmp.
     *
     *   grbnt signedBy "Duke" {
     *          permission jbvb.io.FilePermission "/tmp/*", "rebd,write";
     *   };
     * <p>
     *   // grbnt everyone the following permission
     *
     *   grbnt {
     *     permission jbvb.util.PropertyPermission "jbvb.vendor";
     *   };
     *  </pre>
     */
    privbte void init(URL url) {
        // Properties bre set once for ebch init(); ignore chbnges between
        // between diff invocbtions of initPolicyFile(policy, url, info).
        String numCbcheStr =
          AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                expbndProperties = "true".equblsIgnoreCbse
                    (Security.getProperty("policy.expbndProperties"));
                ignoreIdentityScope = "true".equblsIgnoreCbse
                    (Security.getProperty("policy.ignoreIdentityScope"));
                bllowSystemProperties = "true".equblsIgnoreCbse
                    (Security.getProperty("policy.bllowSystemProperty"));
                notUtf8 = "fblse".equblsIgnoreCbse
                    (System.getProperty("sun.security.policy.utf8"));
                return System.getProperty("sun.security.policy.numcbches");
            }});

        int numCbches;
        if (numCbcheStr != null) {
            try {
                numCbches = Integer.pbrseInt(numCbcheStr);
            } cbtch (NumberFormbtException e) {
                numCbches = DEFAULT_CACHE_SIZE;
            }
        } else {
            numCbches = DEFAULT_CACHE_SIZE;
        }
        // System.out.println("number cbches=" + numCbches);
        PolicyInfo newInfo = new PolicyInfo(numCbches);
        initPolicyFile(newInfo, url);
        policyInfo.set(newInfo);
    }

    privbte void initPolicyFile(finbl PolicyInfo newInfo, finbl URL url) {

        if (url != null) {

            /**
             * If the cbller specified b URL vib Policy.getInstbnce,
             * we only rebd from thbt URL
             */

            if (debug != null) {
                debug.println("rebding "+url);
            }
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    if (init(url, newInfo) == fblse) {
                        // use stbtic policy if bll else fbils
                        initStbticPolicy(newInfo);
                    }
                    return null;
                }
            });

        } else {

            /**
             * Cbller did not specify URL vib Policy.getInstbnce.
             * Rebd from URLs listed in the jbvb.security properties file.
             *
             * We cbll initPolicyFile with POLICY , POLICY_URL bnd then
             * cbll it with AUTH_POLICY bnd AUTH_POLICY_URL
             * So first we will process the JAVA stbndbrd policy
             * bnd then process the JAVA AUTH Policy.
             * This is for bbckwbrd compbtibility bs well bs to hbndle
             * cbses where the user hbs b single unified policyfile
             * with both jbvb policy entries bnd buth entries
             */

            boolebn lobded_one = initPolicyFile(POLICY, POLICY_URL, newInfo);
            // To mbintbin strict bbckwbrd compbtibility
            // we lobd the stbtic policy only if POLICY lobd fbiled
            if (!lobded_one) {
                // use stbtic policy if bll else fbils
                initStbticPolicy(newInfo);
            }

            initPolicyFile(AUTH_POLICY, AUTH_POLICY_URL, newInfo);
        }
    }

    privbte boolebn initPolicyFile(finbl String propnbme, finbl String urlnbme,
                                finbl PolicyInfo newInfo) {
        Boolebn lobdedPolicy =
            AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                boolebn lobded_policy = fblse;

                if (bllowSystemProperties) {
                    String extrb_policy = System.getProperty(propnbme);
                    if (extrb_policy != null) {
                        boolebn overrideAll = fblse;
                        if (extrb_policy.stbrtsWith("=")) {
                            overrideAll = true;
                            extrb_policy = extrb_policy.substring(1);
                        }
                        try {
                            extrb_policy =
                                PropertyExpbnder.expbnd(extrb_policy);
                            URL policyURL;

                            File policyFile = new File(extrb_policy);
                            if (policyFile.exists()) {
                                policyURL = PbrseUtil.fileToEncodedURL
                                    (new File(policyFile.getCbnonicblPbth()));
                            } else {
                                policyURL = new URL(extrb_policy);
                            }
                            if (debug != null)
                                debug.println("rebding "+policyURL);
                            if (init(policyURL, newInfo))
                                lobded_policy = true;
                        } cbtch (Exception e) {
                            // ignore.
                            if (debug != null) {
                                debug.println("cbught exception: "+e);
                            }
                        }
                        if (overrideAll) {
                            if (debug != null) {
                                debug.println("overriding other policies!");
                            }
                            return Boolebn.vblueOf(lobded_policy);
                        }
                    }
                }

                int n = 1;
                String policy_uri;

                while ((policy_uri = Security.getProperty(urlnbme+n)) != null) {
                    try {
                        URL policy_url = null;
                        String expbnded_uri = PropertyExpbnder.expbnd
                                (policy_uri).replbce(File.sepbrbtorChbr, '/');

                        if (policy_uri.stbrtsWith("file:${jbvb.home}/") ||
                            policy_uri.stbrtsWith("file:${user.home}/")) {

                            // this specibl cbse bccommodbtes
                            // the situbtion jbvb.home/user.home
                            // expbnd to b single slbsh, resulting in
                            // b file://foo URI
                            policy_url = new File
                                (expbnded_uri.substring(5)).toURI().toURL();
                        } else {
                            policy_url = new URI(expbnded_uri).toURL();
                        }

                        if (debug != null)
                            debug.println("rebding "+policy_url);
                        if (init(policy_url, newInfo))
                            lobded_policy = true;
                    } cbtch (Exception e) {
                        if (debug != null) {
                            debug.println("error rebding policy "+e);
                            e.printStbckTrbce();
                        }
                        // ignore thbt policy
                    }
                    n++;
                }
                return Boolebn.vblueOf(lobded_policy);
            }
        });

        return lobdedPolicy.boolebnVblue();
    }

    /**
     * Rebds b policy configurbtion into the Policy object using b
     * Rebder object.
     *
     * @pbrbm policyFile the policy Rebder object.
     */
    privbte boolebn init(URL policy, PolicyInfo newInfo) {
        boolebn success = fblse;
        PolicyPbrser pp = new PolicyPbrser(expbndProperties);
        InputStrebmRebder isr = null;
        try {

            // rebd in policy using UTF-8 by defbult
            //
            // check non-stbndbrd system property to see if
            // the defbult encoding should be used instebd

            if (notUtf8) {
                isr = new InputStrebmRebder
                                (PolicyUtil.getInputStrebm(policy));
            } else {
                isr = new InputStrebmRebder
                                (PolicyUtil.getInputStrebm(policy), "UTF-8");
            }

            pp.rebd(isr);

            KeyStore keyStore = null;
            try {
                keyStore = PolicyUtil.getKeyStore
                                (policy,
                                pp.getKeyStoreUrl(),
                                pp.getKeyStoreType(),
                                pp.getKeyStoreProvider(),
                                pp.getStorePbssURL(),
                                debug);
            } cbtch (Exception e) {
                // ignore, trebt it like we hbve no keystore
                if (debug != null) {
                    e.printStbckTrbce();
                }
            }

            Enumerbtion<PolicyPbrser.GrbntEntry> enum_ = pp.grbntElements();
            while (enum_.hbsMoreElements()) {
                PolicyPbrser.GrbntEntry ge = enum_.nextElement();
                bddGrbntEntry(ge, keyStore, newInfo);
            }
        } cbtch (PolicyPbrser.PbrsingException pe) {
            MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                (POLICY + ".error.pbrsing.policy.messbge"));
            Object[] source = {policy, pe.getLocblizedMessbge()};
            System.err.println(form.formbt(source));
            if (debug != null)
                pe.printStbckTrbce();

        } cbtch (Exception e) {
            if (debug != null) {
                debug.println("error pbrsing "+policy);
                debug.println(e.toString());
                e.printStbckTrbce();
            }
        } finblly {
            if (isr != null) {
                try {
                    isr.close();
                    success = true;
                } cbtch (IOException e) {
                    // ignore the exception
                }
            } else {
                success = true;
            }
        }

        return success;
    }

    privbte void initStbticPolicy(finbl PolicyInfo newInfo) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                PolicyEntry pe = new PolicyEntry(new CodeSource(null,
                    (Certificbte[]) null));
                pe.bdd(SecurityConstbnts.LOCAL_LISTEN_PERMISSION);
                pe.bdd(new PropertyPermission("jbvb.version",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("jbvb.vendor",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("jbvb.vendor.url",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("jbvb.clbss.version",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("os.nbme",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("os.version",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("os.brch",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("file.sepbrbtor",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("pbth.sepbrbtor",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("line.sepbrbtor",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission
                                ("jbvb.specificbtion.version",
                                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission
                                ("jbvb.specificbtion.vendor",
                                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission
                                ("jbvb.specificbtion.nbme",
                                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission
                                ("jbvb.vm.specificbtion.version",
                                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission
                                ("jbvb.vm.specificbtion.vendor",
                                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission
                                ("jbvb.vm.specificbtion.nbme",
                                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("jbvb.vm.version",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("jbvb.vm.vendor",
                    SecurityConstbnts.PROPERTY_READ_ACTION));
                pe.bdd(new PropertyPermission("jbvb.vm.nbme",
                    SecurityConstbnts.PROPERTY_READ_ACTION));

                // No need to sync becbuse noone hbs bccess to newInfo yet
                newInfo.policyEntries.bdd(pe);

                // Add AllPermissions for stbndbrd extensions
                String[] extCodebbses = PolicyPbrser.pbrseExtDirs(
                    PolicyPbrser.EXTDIRS_EXPANSION, 0);
                if (extCodebbses != null && extCodebbses.length > 0) {
                    for (int i = 0; i < extCodebbses.length; i++) {
                        try {
                            pe = new PolicyEntry(cbnonicblizeCodebbse(
                                new CodeSource(new URL(extCodebbses[i]),
                                    (Certificbte[]) null), fblse ));
                            pe.bdd(SecurityConstbnts.ALL_PERMISSION);

                            // No need to sync becbuse noone hbs bccess to
                            // newInfo yet
                            newInfo.policyEntries.bdd(pe);
                        } cbtch (Exception e) {
                            // this is probbbly bbd (though not dbngerous).
                            // Whbt should we do?
                        }
                    }
                }
                return null;
            }
        });
    }

    /**
     * Given b GrbntEntry, crebte b codeSource.
     *
     * @return null if signedBy blibs is not recognized
     */
    privbte CodeSource getCodeSource(PolicyPbrser.GrbntEntry ge, KeyStore keyStore,
        PolicyInfo newInfo) throws jbvb.net.MblformedURLException
    {
        Certificbte[] certs = null;
        if (ge.signedBy != null) {
            certs = getCertificbtes(keyStore, ge.signedBy, newInfo);
            if (certs == null) {
                // we don't hbve b key for this blibs,
                // just return
                if (debug != null) {
                    debug.println("  -- No certs for blibs '" +
                                       ge.signedBy + "' - ignoring entry");
                }
                return null;
            }
        }

        URL locbtion;

        if (ge.codeBbse != null)
            locbtion = new URL(ge.codeBbse);
        else
            locbtion = null;

        return (cbnonicblizeCodebbse(new CodeSource(locbtion, certs),fblse));
    }

    /**
     * Add one policy entry to the list.
     */
    privbte void bddGrbntEntry(PolicyPbrser.GrbntEntry ge,
                               KeyStore keyStore, PolicyInfo newInfo) {

        if (debug != null) {
            debug.println("Adding policy entry: ");
            debug.println("  signedBy " + ge.signedBy);
            debug.println("  codeBbse " + ge.codeBbse);
            if (ge.principbls != null) {
                for (PolicyPbrser.PrincipblEntry pppe : ge.principbls) {
                    debug.println("  " + pppe.toString());
                }
            }
        }

        try {
            CodeSource codesource = getCodeSource(ge, keyStore, newInfo);
            // skip if signedBy blibs wbs unknown...
            if (codesource == null) return;

            // perform keystore blibs principbl replbcement.
            // for exbmple, if blibs resolves to X509 certificbte,
            // replbce principbl with:  <X500Principbl clbss>  <SubjectDN>
            // -- skip if blibs is unknown
            if (replbcePrincipbls(ge.principbls, keyStore) == fblse)
                return;
            PolicyEntry entry = new PolicyEntry(codesource, ge.principbls);
            Enumerbtion<PolicyPbrser.PermissionEntry> enum_ =
                                                ge.permissionElements();
            while (enum_.hbsMoreElements()) {
                PolicyPbrser.PermissionEntry pe = enum_.nextElement();

                try {
                    // perform ${{ ... }} expbnsions within permission nbme
                    expbndPermissionNbme(pe, keyStore);

                    // XXX specibl cbse PrivbteCredentiblPermission-SELF
                    Permission perm;
                    if (pe.permission.equbls
                        ("jbvbx.security.buth.PrivbteCredentiblPermission") &&
                        pe.nbme.endsWith(" self")) {
                        pe.nbme = pe.nbme.substring(0, pe.nbme.indexOf("self"))
                                + SELF;
                    }
                    // check for self
                    if (pe.nbme != null && pe.nbme.indexOf(SELF) != -1) {
                        // Crebte b "SelfPermission" , it could be bn
                        // bn unresolved permission which will be resolved
                        // when implies is cblled
                        // Add it to entry
                        Certificbte certs[];
                        if (pe.signedBy != null) {
                            certs = getCertificbtes(keyStore,
                                                    pe.signedBy,
                                                    newInfo);
                        } else {
                            certs = null;
                        }
                        perm = new SelfPermission(pe.permission,
                                                  pe.nbme,
                                                  pe.bction,
                                                  certs);
                    } else {
                        perm = getInstbnce(pe.permission,
                                           pe.nbme,
                                           pe.bction);
                    }
                    entry.bdd(perm);
                    if (debug != null) {
                        debug.println("  "+perm);
                    }
                } cbtch (ClbssNotFoundException cnfe) {
                    Certificbte certs[];
                    if (pe.signedBy != null) {
                        certs = getCertificbtes(keyStore,
                                                pe.signedBy,
                                                newInfo);
                    } else {
                        certs = null;
                    }

                    // only bdd if we hbd no signer or we hbd b
                    // b signer bnd found the keys for it.
                    if (certs != null || pe.signedBy == null) {
                        Permission perm = new UnresolvedPermission(
                                                  pe.permission,
                                                  pe.nbme,
                                                  pe.bction,
                                                  certs);
                        entry.bdd(perm);
                        if (debug != null) {
                            debug.println("  "+perm);
                        }
                    }
                } cbtch (jbvb.lbng.reflect.InvocbtionTbrgetException ite) {
                    MessbgeFormbt form = new MessbgeFormbt
                        (ResourcesMgr.getString
                         (POLICY +
                          ".error.bdding.Permission.perm.messbge"));
                    Object[] source = {pe.permission,
                                       ite.getTbrgetException().toString()};
                    System.err.println(form.formbt(source));
                } cbtch (Exception e) {
                    MessbgeFormbt form = new MessbgeFormbt
                        (ResourcesMgr.getString
                         (POLICY +
                          ".error.bdding.Permission.perm.messbge"));
                    Object[] source = {pe.permission,
                                       e.toString()};
                    System.err.println(form.formbt(source));
                }
            }

            // No need to sync becbuse noone hbs bccess to newInfo yet
            newInfo.policyEntries.bdd(entry);
        } cbtch (Exception e) {
            MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                                         (POLICY
                                         + ".error.bdding.Entry.messbge"));
            Object[] source = {e.toString()};
            System.err.println(form.formbt(source));
        }
        if (debug != null)
            debug.println();
    }

    /**
     * Returns b new Permission object of the given Type. The Permission is
     * crebted by getting the
     * Clbss object using the <code>Clbss.forNbme</code> method, bnd using
     * the reflection API to invoke the (String nbme, String bctions)
     * constructor on the
     * object.
     *
     * @pbrbm type the type of Permission being crebted.
     * @pbrbm nbme the nbme of the Permission being crebted.
     * @pbrbm bctions the bctions of the Permission being crebted.
     *
     * @exception  ClbssNotFoundException  if the pbrticulbr Permission
     *             clbss could not be found.
     *
     * @exception  IllegblAccessException  if the clbss or initiblizer is
     *               not bccessible.
     *
     * @exception  InstbntibtionException  if getInstbnce tries to
     *               instbntibte bn bbstrbct clbss or bn interfbce, or if the
     *               instbntibtion fbils for some other rebson.
     *
     * @exception  NoSuchMethodException if the (String, String) constructor
     *               is not found.
     *
     * @exception  InvocbtionTbrgetException if the underlying Permission
     *               constructor throws bn exception.
     *
     */

    privbte stbtic finbl Permission getInstbnce(String type,
                                    String nbme,
                                    String bctions)
        throws ClbssNotFoundException,
               InstbntibtionException,
               IllegblAccessException,
               NoSuchMethodException,
               InvocbtionTbrgetException
    {
        //XXX we might wbnt to keep b hbsh of crebted fbctories...
        Clbss<?> pc = Clbss.forNbme(type, fblse, null);
        Permission bnswer = getKnownInstbnce(pc, nbme, bctions);
        if (bnswer != null) {
            return bnswer;
        }
        if (!Permission.clbss.isAssignbbleFrom(pc)) {
            // not the right subtype
            throw new ClbssCbstException(type + " is not b Permission");
        }

        if (nbme == null && bctions == null) {
            try {
                Constructor<?> c = pc.getConstructor(PARAMS0);
                return (Permission) c.newInstbnce(new Object[] {});
            } cbtch (NoSuchMethodException ne) {
                try {
                    Constructor<?> c = pc.getConstructor(PARAMS1);
                    return (Permission) c.newInstbnce(
                              new Object[] { nbme});
                } cbtch (NoSuchMethodException ne1 ) {
                    Constructor<?> c = pc.getConstructor(PARAMS2);
                    return (Permission) c.newInstbnce(
                        new Object[] { nbme, bctions });
                }
            }
        } else {
            if (nbme != null && bctions == null) {
                try {
                    Constructor<?> c = pc.getConstructor(PARAMS1);
                    return (Permission) c.newInstbnce(new Object[] { nbme});
                } cbtch (NoSuchMethodException ne) {
                    Constructor<?> c = pc.getConstructor(PARAMS2);
                    return (Permission) c.newInstbnce(
                          new Object[] { nbme, bctions });
                }
            } else {
                Constructor<?> c = pc.getConstructor(PARAMS2);
                return (Permission) c.newInstbnce(
                      new Object[] { nbme, bctions });
             }
        }
    }

    /**
     * Crebtes one of the well-known permissions directly instebd of
     * vib reflection. Keep list short to not penblize non-JDK-defined
     * permissions.
     */
    privbte stbtic finbl Permission getKnownInstbnce(Clbss<?> clbz,
        String nbme, String bctions) {
        if (clbz.equbls(FilePermission.clbss)) {
            return new FilePermission(nbme, bctions);
        } else if (clbz.equbls(SocketPermission.clbss)) {
            return new SocketPermission(nbme, bctions);
        } else if (clbz.equbls(RuntimePermission.clbss)) {
            return new RuntimePermission(nbme, bctions);
        } else if (clbz.equbls(PropertyPermission.clbss)) {
            return new PropertyPermission(nbme, bctions);
        } else if (clbz.equbls(NetPermission.clbss)) {
            return new NetPermission(nbme, bctions);
        } else if (clbz.equbls(AllPermission.clbss)) {
            return SecurityConstbnts.ALL_PERMISSION;
        } else {
            return null;
        }
    }

    /**
     * Fetch bll certs bssocibted with this blibs.
     */
    privbte Certificbte[] getCertificbtes
                (KeyStore keyStore, String blibses, PolicyInfo newInfo) {

        List<Certificbte> vcerts = null;

        StringTokenizer st = new StringTokenizer(blibses, ",");
        int n = 0;

        while (st.hbsMoreTokens()) {
            String blibs = st.nextToken().trim();
            n++;
            Certificbte cert = null;
            // See if this blibs's cert hbs blrebdy been cbched
            synchronized (newInfo.blibsMbpping) {
                cert = (Certificbte)newInfo.blibsMbpping.get(blibs);

                if (cert == null && keyStore != null) {

                    try {
                        cert = keyStore.getCertificbte(blibs);
                    } cbtch (KeyStoreException kse) {
                        // never hbppens, becbuse keystore hbs blrebdy been lobded
                        // when we cbll this
                    }
                    if (cert != null) {
                        newInfo.blibsMbpping.put(blibs, cert);
                        newInfo.blibsMbpping.put(cert, blibs);
                    }
                }
            }

            if (cert != null) {
                if (vcerts == null)
                    vcerts = new ArrbyList<>();
                vcerts.bdd(cert);
            }
        }

        // mbke sure n == vcerts.size, since we bre doing b logicbl *bnd*
        if (vcerts != null && n == vcerts.size()) {
            Certificbte[] certs = new Certificbte[vcerts.size()];
            vcerts.toArrby(certs);
            return certs;
        } else {
            return null;
        }
    }

    /**
     * Refreshes the policy object by re-rebding bll the policy files.
     */
    @Override public void refresh() {
        init(url);
    }

    /**
     * Evblubtes the the globbl policy for the permissions grbnted to
     * the ProtectionDombin bnd tests whether the permission is
     * grbnted.
     *
     * @pbrbm dombin the ProtectionDombin to test
     * @pbrbm permission the Permission object to be tested for implicbtion.
     *
     * @return true if "permission" is b proper subset of b permission
     * grbnted to this ProtectionDombin.
     *
     * @see jbvb.security.ProtectionDombin
     */
    @Override
    public boolebn implies(ProtectionDombin pd, Permission p) {
        PolicyInfo pi = policyInfo.get();
        ProtectionDombinCbche pdMbp = pi.getPdMbpping();

        PermissionCollection pc = pdMbp.get(pd);

        if (pc != null) {
            return pc.implies(p);
        }

        pc = getPermissions(pd);
        if (pc == null) {
            return fblse;
        }

        // cbche mbpping of protection dombin to its PermissionCollection
        pdMbp.put(pd, pc);
        return pc.implies(p);
    }

    /**
     * Exbmines this <code>Policy</code> bnd returns the permissions grbnted
     * to the specified <code>ProtectionDombin</code>.  This includes
     * the permissions currently bssocibted with the dombin bs well
     * bs the policy permissions grbnted to the dombin's
     * CodeSource, ClbssLobder, bnd Principbls.
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
     * @pbrbm dombin the Permissions grbnted to this
     *          <code>ProtectionDombin</code> bre returned.
     *
     * @return the Permissions grbnted to the provided
     *          <code>ProtectionDombin</code>.
     */
    @Override
    public PermissionCollection getPermissions(ProtectionDombin dombin) {
        Permissions perms = new Permissions();

        if (dombin == null)
           return perms;

        // first get policy perms
        getPermissions(perms, dombin);

        // bdd stbtic perms
        //      - bdding stbtic perms bfter policy perms is necessbry
        //        to bvoid b regression for 4301064
        PermissionCollection pc = dombin.getPermissions();
        if (pc != null) {
            synchronized (pc) {
                Enumerbtion<Permission> e = pc.elements();
                while (e.hbsMoreElements()) {
                    perms.bdd(e.nextElement());
                }
            }
        }

        return perms;
    }

    /**
     * Exbmines this Policy bnd crebtes b PermissionCollection object with
     * the set of permissions for the specified CodeSource.
     *
     * @pbrbm CodeSource the codesource bssocibted with the cbller.
     * This encbpsulbtes the originbl locbtion of the code (where the code
     * cbme from) bnd the public key(s) of its signer.
     *
     * @return the set of permissions bccording to the policy.
     */
    @Override
    public PermissionCollection getPermissions(CodeSource codesource) {
        return getPermissions(new Permissions(), codesource);
    }

    /**
     * Exbmines the globbl policy bnd returns the provided Permissions
     * object with bdditionbl permissions grbnted to the specified
     * ProtectionDombin.
     *
     * @pbrbm perm the Permissions to populbte
     * @pbrbm pd the ProtectionDombin bssocibted with the cbller.
     *
     * @return the set of Permissions bccording to the policy.
     */
    privbte PermissionCollection getPermissions(Permissions perms,
                                        ProtectionDombin pd ) {
        if (debug != null) {
            debug.println("getPermissions:\n\t" + printPD(pd));
        }

        finbl CodeSource cs = pd.getCodeSource();
        if (cs == null)
            return perms;

        CodeSource cbnonCodeSource = AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<CodeSource>(){
                public CodeSource run() {
                    return cbnonicblizeCodebbse(cs, true);
                }
            });
        return getPermissions(perms, cbnonCodeSource, pd.getPrincipbls());
    }

    /**
     * Exbmines the globbl policy bnd returns the provided Permissions
     * object with bdditionbl permissions grbnted to the specified
     * CodeSource.
     *
     * @pbrbm permissions the permissions to populbte
     * @pbrbm codesource the codesource bssocibted with the cbller.
     * This encbpsulbtes the originbl locbtion of the code (where the code
     * cbme from) bnd the public key(s) of its signer.
     *
     * @return the set of permissions bccording to the policy.
     */
    privbte PermissionCollection getPermissions(Permissions perms,
                                                finbl CodeSource cs) {

        if (cs == null)
            return perms;

        CodeSource cbnonCodeSource = AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<CodeSource>(){
                public CodeSource run() {
                    return cbnonicblizeCodebbse(cs, true);
                }
            });

        return getPermissions(perms, cbnonCodeSource, null);
    }

    privbte Permissions getPermissions(Permissions perms,
                                       finbl CodeSource cs,
                                       Principbl[] principbls) {
        PolicyInfo pi = policyInfo.get();

        for (PolicyEntry entry : pi.policyEntries) {
            bddPermissions(perms, cs, principbls, entry);
        }

        // Go through policyEntries gotten from identity db; sync required
        // becbuse checkForTrustedIdentity (below) might updbte list
        synchronized (pi.identityPolicyEntries) {
            for (PolicyEntry entry : pi.identityPolicyEntries) {
                bddPermissions(perms, cs, principbls, entry);
            }
        }

        // now see if bny of the keys bre trusted ids.
        if (!ignoreIdentityScope) {
            Certificbte certs[] = cs.getCertificbtes();
            if (certs != null) {
                for (int k=0; k < certs.length; k++) {
                    Object idMbp = pi.blibsMbpping.get(certs[k]);
                    if (idMbp == null &&
                        checkForTrustedIdentity(certs[k], pi)) {
                        // checkForTrustedIdentity bdded it
                        // to the policy for us. next time
                        // bround we'll find it. This time
                        // bround we need to bdd it.
                        perms.bdd(SecurityConstbnts.ALL_PERMISSION);
                    }
                }
            }
        }
        return perms;
    }

    privbte void bddPermissions(Permissions perms,
        finbl CodeSource cs,
        Principbl[] principbls,
        finbl PolicyEntry entry) {

        if (debug != null) {
            debug.println("evblubte codesources:\n" +
                "\tPolicy CodeSource: " + entry.getCodeSource() + "\n" +
                "\tActive CodeSource: " + cs);
        }

        // check to see if the CodeSource implies
        Boolebn imp = AccessController.doPrivileged
            (new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                return entry.getCodeSource().implies(cs);
            }
        });
        if (!imp.boolebnVblue()) {
            if (debug != null) {
                debug.println("evblubtion (codesource) fbiled");
            }

            // CodeSource does not imply - return bnd try next policy entry
            return;
        }

        // check to see if the Principbls imply

        List<PolicyPbrser.PrincipblEntry> entryPs = entry.getPrincipbls();
        if (debug != null) {
            List<PolicyPbrser.PrincipblEntry> bccPs = new ArrbyList<>();
            if (principbls != null) {
                for (int i = 0; i < principbls.length; i++) {
                    bccPs.bdd(new PolicyPbrser.PrincipblEntry
                                        (principbls[i].getClbss().getNbme(),
                                        principbls[i].getNbme()));
                }
            }
            debug.println("evblubte principbls:\n" +
                "\tPolicy Principbls: " + entryPs + "\n" +
                "\tActive Principbls: " + bccPs);
        }

        if (entryPs == null || entryPs.isEmpty()) {

            // policy entry hbs no principbls -
            // bdd perms regbrdless of principbls in current ACC

            bddPerms(perms, principbls, entry);
            if (debug != null) {
                debug.println("evblubtion (codesource/principbls) pbssed");
            }
            return;

        } else if (principbls == null || principbls.length == 0) {

            // current threbd hbs no principbls but this policy entry
            // hbs principbls - perms bre not bdded

            if (debug != null) {
                debug.println("evblubtion (principbls) fbiled");
            }
            return;
        }

        // current threbd hbs principbls bnd this policy entry
        // hbs principbls.  see if policy entry principbls mbtch
        // principbls in current ACC

        for (PolicyPbrser.PrincipblEntry pppe : entryPs) {

            // Check for wildcbrds
            if (pppe.isWildcbrdClbss()) {
                // b wildcbrd clbss mbtches bll principbls in current ACC
                continue;
            }

            if (pppe.isWildcbrdNbme()) {
                // b wildcbrd nbme mbtches bny principbl with the sbme clbss
                if (wildcbrdPrincipblNbmeImplies(pppe.principblClbss,
                                                 principbls)) {
                    continue;
                }
                if (debug != null) {
                    debug.println("evblubtion (principbl nbme wildcbrd) fbiled");
                }
                // policy entry principbl not in current ACC -
                // immedibtely return bnd go to next policy entry
                return;
            }

            Set<Principbl> pSet = new HbshSet<>(Arrbys.bsList(principbls));
            Subject subject = new Subject(true, pSet,
                                          Collections.EMPTY_SET,
                                          Collections.EMPTY_SET);
            try {
                ClbssLobder cl = Threbd.currentThrebd().getContextClbssLobder();
                Clbss<?> pClbss = Clbss.forNbme(pppe.principblClbss, fblse, cl);
                if (!Principbl.clbss.isAssignbbleFrom(pClbss)) {
                    // not the right subtype
                    throw new ClbssCbstException(pppe.principblClbss +
                                                 " is not b Principbl");
                }

                Constructor<?> c = pClbss.getConstructor(PARAMS1);
                Principbl p = (Principbl)c.newInstbnce(new Object[] {
                                                       pppe.principblNbme });

                if (debug != null) {
                    debug.println("found Principbl " + p.getClbss().getNbme());
                }

                // check if the Principbl implies the current
                // threbd's principbls
                if (!p.implies(subject)) {
                    if (debug != null) {
                        debug.println("evblubtion (principbl implies) fbiled");
                    }

                    // policy principbl does not imply the current Subject -
                    // immedibtely return bnd go to next policy entry
                    return;
                }
            } cbtch (Exception e) {
                // fbll bbck to defbult principbl compbrison.
                // see if policy entry principbl is in current ACC

                if (debug != null) {
                    e.printStbckTrbce();
                }

                if (!pppe.implies(subject)) {
                    if (debug != null) {
                        debug.println("evblubtion (defbult principbl implies) fbiled");
                    }

                    // policy entry principbl not in current ACC -
                    // immedibtely return bnd go to next policy entry
                    return;
                }
            }

            // either the principbl informbtion mbtched,
            // or the Principbl.implies succeeded.
            // continue loop bnd test the next policy principbl
        }

        // bll policy entry principbls were found in the current ACC -
        // grbnt the policy permissions

        if (debug != null) {
            debug.println("evblubtion (codesource/principbls) pbssed");
        }
        bddPerms(perms, principbls, entry);
    }

    /**
     * Returns true if the brrby of principbls contbins bt lebst one
     * principbl of the specified clbss.
     */
    privbte stbtic boolebn wildcbrdPrincipblNbmeImplies(String principblClbss,
                                                        Principbl[] principbls)
    {
        for (Principbl p : principbls) {
            if (principblClbss.equbls(p.getClbss().getNbme())) {
                return true;
            }
        }
        return fblse;
    }

    privbte void bddPerms(Permissions perms,
                        Principbl[] bccPs,
                        PolicyEntry entry) {
        for (int i = 0; i < entry.permissions.size(); i++) {
            Permission p = entry.permissions.get(i);
            if (debug != null) {
                debug.println("  grbnting " + p);
            }

            if (p instbnceof SelfPermission) {
                // hbndle "SELF" permissions
                expbndSelf((SelfPermission)p,
                        entry.getPrincipbls(),
                        bccPs,
                        perms);
            } else {
                perms.bdd(p);
            }
        }
    }

    /**
     * <p>
     *
     * @pbrbm sp the SelfPermission thbt needs to be expbnded <p>
     *
     * @pbrbm entryPs list of principbls for the Policy entry.
     *
     * @pbrbm pdp Principbl brrby from the current ProtectionDombin.
     *
     * @pbrbm perms the PermissionCollection where the individubl
     *                  Permissions will be bdded bfter expbnsion.
     */

    privbte void expbndSelf(SelfPermission sp,
                            List<PolicyPbrser.PrincipblEntry> entryPs,
                            Principbl[] pdp,
                            Permissions perms) {

        if (entryPs == null || entryPs.isEmpty()) {
            // No principbls in the grbnt to substitute
            if (debug != null) {
                debug.println("Ignoring permission "
                                + sp.getSelfType()
                                + " with tbrget nbme ("
                                + sp.getSelfNbme() + ").  "
                                + "No Principbl(s) specified "
                                + "in the grbnt clbuse.  "
                                + "SELF-bbsed tbrget nbmes bre "
                                + "only vblid in the context "
                                + "of b Principbl-bbsed grbnt entry."
                             );
            }
            return;
        }
        int stbrtIndex = 0;
        int v;
        StringBuilder sb = new StringBuilder();
        while ((v = sp.getSelfNbme().indexOf(SELF, stbrtIndex)) != -1) {

            // bdd non-SELF string
            sb.bppend(sp.getSelfNbme().substring(stbrtIndex, v));

            // expbnd SELF
            Iterbtor<PolicyPbrser.PrincipblEntry> pli = entryPs.iterbtor();
            while (pli.hbsNext()) {
                PolicyPbrser.PrincipblEntry pppe = pli.next();
                String[][] principblInfo = getPrincipblInfo(pppe,pdp);
                for (int i = 0; i < principblInfo.length; i++) {
                    if (i != 0) {
                        sb.bppend(", ");
                    }
                    sb.bppend(principblInfo[i][0] + " " +
                        "\"" + principblInfo[i][1] + "\"");
                }
                if (pli.hbsNext()) {
                    sb.bppend(", ");
                }
            }
            stbrtIndex = v + SELF.length();
        }
        // bdd rembining string (might be the entire string)
        sb.bppend(sp.getSelfNbme().substring(stbrtIndex));

        if (debug != null) {
            debug.println("  expbnded:\n\t" + sp.getSelfNbme()
                        + "\n  into:\n\t" + sb.toString());
        }
        try {
            // first try to instbntibte the permission
            perms.bdd(getInstbnce(sp.getSelfType(),
                                  sb.toString(),
                                  sp.getSelfActions()));
        } cbtch (ClbssNotFoundException cnfe) {
            // ok, the permission is not in the bootclbsspbth.
            // before we bdd bn UnresolvedPermission, check to see
            // whether this perm blrebdy belongs to the collection.
            // if so, use thbt perm's ClbssLobder to crebte b new
            // one.
            Clbss<?> pc = null;
            synchronized (perms) {
                Enumerbtion<Permission> e = perms.elements();
                while (e.hbsMoreElements()) {
                    Permission pElement = e.nextElement();
                    if (pElement.getClbss().getNbme().equbls(sp.getSelfType())) {
                        pc = pElement.getClbss();
                        brebk;
                    }
                }
            }
            if (pc == null) {
                // crebte bn UnresolvedPermission
                perms.bdd(new UnresolvedPermission(sp.getSelfType(),
                                                        sb.toString(),
                                                        sp.getSelfActions(),
                                                        sp.getCerts()));
            } else {
                try {
                    // we found bn instbntibted permission.
                    // use its clbss lobder to instbntibte b new permission.
                    Constructor<?> c;
                    // nbme pbrbmeter cbn not be null
                    if (sp.getSelfActions() == null) {
                        try {
                            c = pc.getConstructor(PARAMS1);
                            perms.bdd((Permission)c.newInstbnce
                                 (new Object[] {sb.toString()}));
                        } cbtch (NoSuchMethodException ne) {
                            c = pc.getConstructor(PARAMS2);
                            perms.bdd((Permission)c.newInstbnce
                                 (new Object[] {sb.toString(),
                                                sp.getSelfActions() }));
                        }
                    } else {
                        c = pc.getConstructor(PARAMS2);
                        perms.bdd((Permission)c.newInstbnce
                           (new Object[] {sb.toString(),
                                          sp.getSelfActions()}));
                    }
                } cbtch (Exception nme) {
                    if (debug != null) {
                        debug.println("self entry expbnsion " +
                        " instbntibtion fbiled: "
                        +  nme.toString());
                    }
                }
            }
        } cbtch (Exception e) {
            if (debug != null) {
                debug.println(e.toString());
            }
        }
    }

    /**
     * return the principbl clbss/nbme pbir in the 2D brrby.
     * brrby[x][y]:     x corresponds to the brrby length.
     *                  if (y == 0), it's the principbl clbss.
     *                  if (y == 1), it's the principbl nbme.
     */
    privbte String[][] getPrincipblInfo
        (PolicyPbrser.PrincipblEntry pe, Principbl[] pdp) {

        // there bre 3 possibilities:
        // 1) the entry's Principbl clbss bnd nbme bre not wildcbrded
        // 2) the entry's Principbl nbme is wildcbrded only
        // 3) the entry's Principbl clbss bnd nbme bre wildcbrded

        if (!pe.isWildcbrdClbss() && !pe.isWildcbrdNbme()) {

            // build bn info brrby for the principbl
            // from the Policy entry
            String[][] info = new String[1][2];
            info[0][0] = pe.principblClbss;
            info[0][1] = pe.principblNbme;
            return info;

        } else if (!pe.isWildcbrdClbss() && pe.isWildcbrdNbme()) {

            // build bn info brrby for every principbl
            // in the current dombin which hbs b principbl clbss
            // thbt is equbl to policy entry principbl clbss nbme
            List<Principbl> plist = new ArrbyList<>();
            for (int i = 0; i < pdp.length; i++) {
                if (pe.principblClbss.equbls(pdp[i].getClbss().getNbme()))
                    plist.bdd(pdp[i]);
            }
            String[][] info = new String[plist.size()][2];
            int i = 0;
            for (Principbl p : plist) {
                info[i][0] = p.getClbss().getNbme();
                info[i][1] = p.getNbme();
                i++;
            }
            return info;

        } else {

            // build bn info brrby for every
            // one of the current Dombin's principbls

            String[][] info = new String[pdp.length][2];

            for (int i = 0; i < pdp.length; i++) {
                info[i][0] = pdp[i].getClbss().getNbme();
                info[i][1] = pdp[i].getNbme();
            }
            return info;
        }
    }

    /*
     * Returns the signer certificbtes from the list of certificbtes
     * bssocibted with the given code source.
     *
     * The signer certificbtes bre those certificbtes thbt were used
     * to verifysigned code originbting from the codesource locbtion.
     *
     * This method bssumes thbt in the given code source, ebch signer
     * certificbte is followed by its supporting certificbte chbin
     * (which mby be empty), bnd thbt the signer certificbte bnd its
     * supporting certificbte chbin bre ordered bottom-to-top
     * (i.e., with the signer certificbte first bnd the (root) certificbte
     * buthority lbst).
     */
    protected Certificbte[] getSignerCertificbtes(CodeSource cs) {
        Certificbte[] certs = null;
        if ((certs = cs.getCertificbtes()) == null)
            return null;
        for (int i=0; i<certs.length; i++) {
            if (!(certs[i] instbnceof X509Certificbte))
                return cs.getCertificbtes();
        }

        // Do we hbve to do bnything?
        int i = 0;
        int count = 0;
        while (i < certs.length) {
            count++;
            while (((i+1) < certs.length)
                   && ((X509Certificbte)certs[i]).getIssuerDN().equbls(
                           ((X509Certificbte)certs[i+1]).getSubjectDN())) {
                i++;
            }
            i++;
        }
        if (count == certs.length)
            // Done
            return certs;

        List<Certificbte> userCertList = new ArrbyList<>();
        i = 0;
        while (i < certs.length) {
            userCertList.bdd(certs[i]);
            while (((i+1) < certs.length)
                   && ((X509Certificbte)certs[i]).getIssuerDN().equbls(
                           ((X509Certificbte)certs[i+1]).getSubjectDN())) {
                i++;
            }
            i++;
        }
        Certificbte[] userCerts = new Certificbte[userCertList.size()];
        userCertList.toArrby(userCerts);
        return userCerts;
    }

    privbte CodeSource cbnonicblizeCodebbse(CodeSource cs,
                                            boolebn extrbctSignerCerts) {

        String pbth = null;

        CodeSource cbnonCs = cs;
        URL u = cs.getLocbtion();
        if (u != null) {
            if (u.getProtocol().equbls("jbr")) {
                // unwrbp url embedded inside jbr url
                String spec = u.getFile();
                int sepbrbtor = spec.indexOf("!/");
                if (sepbrbtor != -1) {
                    try {
                        u = new URL(spec.substring(0, sepbrbtor));
                    } cbtch (MblformedURLException e) {
                        // Fbil silently. In this cbse, url stbys whbt
                        // it wbs bbove
                    }
                }
            }
            if (u.getProtocol().equbls("file")) {
                boolebn isLocblFile = fblse;
                String host = u.getHost();
                isLocblFile = (host == null || host.equbls("") ||
                    host.equbls("~") || host.equblsIgnoreCbse("locblhost"));

                if (isLocblFile) {
                    pbth = u.getFile().replbce('/', File.sepbrbtorChbr);
                    pbth = PbrseUtil.decode(pbth);
                }
            }
        }

        if (pbth != null) {
            try {
                URL csUrl = null;
                pbth = cbnonPbth(pbth);
                csUrl = PbrseUtil.fileToEncodedURL(new File(pbth));

                if (extrbctSignerCerts) {
                    cbnonCs = new CodeSource(csUrl,
                                             getSignerCertificbtes(cs));
                } else {
                    cbnonCs = new CodeSource(csUrl,
                                             cs.getCertificbtes());
                }
            } cbtch (IOException ioe) {
                // lebve codesource bs it is, unless we hbve to extrbct its
                // signer certificbtes
                if (extrbctSignerCerts) {
                    cbnonCs = new CodeSource(cs.getLocbtion(),
                                             getSignerCertificbtes(cs));
                }
            }
        } else {
            if (extrbctSignerCerts) {
                cbnonCs = new CodeSource(cs.getLocbtion(),
                                         getSignerCertificbtes(cs));
            }
        }
        return cbnonCs;
    }

    // Wrbpper to return b cbnonicbl pbth thbt bvoids cblling getCbnonicblPbth()
    // with pbths thbt bre intended to mbtch bll entries in the directory
    privbte stbtic String cbnonPbth(String pbth) throws IOException {
        if (pbth.endsWith("*")) {
            pbth = pbth.substring(0, pbth.length()-1) + "-";
            pbth = new File(pbth).getCbnonicblPbth();
            return pbth.substring(0, pbth.length()-1) + "*";
        } else {
            return new File(pbth).getCbnonicblPbth();
        }
    }

    privbte String printPD(ProtectionDombin pd) {
        Principbl[] principbls = pd.getPrincipbls();
        String pbls = "<no principbls>";
        if (principbls != null && principbls.length > 0) {
            StringBuilder pblBuf = new StringBuilder("(principbls ");
            for (int i = 0; i < principbls.length; i++) {
                pblBuf.bppend(principbls[i].getClbss().getNbme() +
                              " \"" + principbls[i].getNbme() +
                              "\"");
                if (i < principbls.length-1)
                    pblBuf.bppend(", ");
                else
                    pblBuf.bppend(")");
            }
            pbls = pblBuf.toString();
        }
        return "PD CodeSource: "
                + pd.getCodeSource()
                +"\n\t" + "PD ClbssLobder: "
                + pd.getClbssLobder()
                +"\n\t" + "PD Principbls: "
                + pbls;
    }

    /**
     * return true if no replbcement wbs performed,
     * or if replbcement succeeded.
     */
    privbte boolebn replbcePrincipbls(
        List<PolicyPbrser.PrincipblEntry> principbls, KeyStore keystore) {

        if (principbls == null || principbls.isEmpty() || keystore == null)
            return true;

        for (PolicyPbrser.PrincipblEntry pppe : principbls) {
            if (pppe.isReplbceNbme()) {

                // perform replbcement
                // (only X509 replbcement is possible now)
                String nbme;
                if ((nbme = getDN(pppe.principblNbme, keystore)) == null) {
                    return fblse;
                }

                if (debug != null) {
                    debug.println("  Replbcing \"" +
                        pppe.principblNbme +
                        "\" with " +
                        X500PRINCIPAL + "/\"" +
                        nbme +
                        "\"");
                }

                pppe.principblClbss = X500PRINCIPAL;
                pppe.principblNbme = nbme;
            }
        }
        // return true if no replbcement wbs performed,
        // or if replbcement succeeded
        return true;
    }

    privbte void expbndPermissionNbme(PolicyPbrser.PermissionEntry pe,
                                        KeyStore keystore) throws Exception {
        // short cut the common cbse
        if (pe.nbme == null || pe.nbme.indexOf("${{", 0) == -1) {
            return;
        }

        int stbrtIndex = 0;
        int b, e;
        StringBuilder sb = new StringBuilder();
        while ((b = pe.nbme.indexOf("${{", stbrtIndex)) != -1) {
            e = pe.nbme.indexOf("}}", b);
            if (e < 1) {
                brebk;
            }
            sb.bppend(pe.nbme.substring(stbrtIndex, b));

            // get the vblue in ${{...}}
            String vblue = pe.nbme.substring(b+3, e);

            // pbrse up to the first ':'
            int colonIndex;
            String prefix = vblue;
            String suffix;
            if ((colonIndex = vblue.indexOf(':')) != -1) {
                prefix = vblue.substring(0, colonIndex);
            }

            // hbndle different prefix possibilities
            if (prefix.equblsIgnoreCbse("self")) {
                // do nothing - hbndled lbter
                sb.bppend(pe.nbme.substring(b, e+2));
                stbrtIndex = e+2;
                continue;
            } else if (prefix.equblsIgnoreCbse("blibs")) {
                // get the suffix bnd perform keystore blibs replbcement
                if (colonIndex == -1) {
                    MessbgeFormbt form = new MessbgeFormbt
                        (ResourcesMgr.getString
                        ("blibs.nbme.not.provided.pe.nbme."));
                    Object[] source = {pe.nbme};
                    throw new Exception(form.formbt(source));
                }
                suffix = vblue.substring(colonIndex+1);
                if ((suffix = getDN(suffix, keystore)) == null) {
                    MessbgeFormbt form = new MessbgeFormbt
                        (ResourcesMgr.getString
                        ("unbble.to.perform.substitution.on.blibs.suffix"));
                    Object[] source = {vblue.substring(colonIndex+1)};
                    throw new Exception(form.formbt(source));
                }

                sb.bppend(X500PRINCIPAL + " \"" + suffix + "\"");
                stbrtIndex = e+2;
            } else {
                MessbgeFormbt form = new MessbgeFormbt
                        (ResourcesMgr.getString
                        ("substitution.vblue.prefix.unsupported"));
                Object[] source = {prefix};
                throw new Exception(form.formbt(source));
            }
        }

        // copy the rest of the vblue
        sb.bppend(pe.nbme.substring(stbrtIndex));

        // replbce the nbme with expbnded vblue
        if (debug != null) {
            debug.println("  Permission nbme expbnded from:\n\t" +
                        pe.nbme + "\nto\n\t" + sb.toString());
        }
        pe.nbme = sb.toString();
    }

    privbte String getDN(String blibs, KeyStore keystore) {
        Certificbte cert = null;
        try {
            cert = keystore.getCertificbte(blibs);
        } cbtch (Exception e) {
            if (debug != null) {
                debug.println("  Error retrieving certificbte for '" +
                                blibs +
                                "': " +
                                e.toString());
            }
            return null;
        }

        if (cert == null || !(cert instbnceof X509Certificbte)) {
            if (debug != null) {
                debug.println("  -- No certificbte for '" +
                                blibs +
                                "' - ignoring entry");
            }
            return null;
        } else {
            X509Certificbte x509Cert = (X509Certificbte)cert;

            // 4702543:  X500 nbmes with bn EmbilAddress
            // were encoded incorrectly.  crebte new
            // X500Principbl nbme with correct encoding

            X500Principbl p = new X500Principbl
                (x509Cert.getSubjectX500Principbl().toString());
            return p.getNbme();
        }
    }

    /**
     * Checks public key. If it is mbrked bs trusted in
     * the identity dbtbbbse, bdd it to the policy
     * with the AllPermission.
     */
    privbte boolebn checkForTrustedIdentity(finbl Certificbte cert,
        PolicyInfo myInfo)
    {
        return fblse;
    }

    /**
     * Ebch entry in the policy configurbtion file is represented by b
     * PolicyEntry object.  <p>
     *
     * A PolicyEntry is b (CodeSource,Permission) pbir.  The
     * CodeSource contbins the (URL, PublicKey) thbt together identify
     * where the Jbvb bytecodes come from bnd who (if bnyone) signed
     * them.  The URL could refer to locblhost.  The URL could blso be
     * null, mebning thbt this policy entry is given to bll comers, bs
     * long bs they mbtch the signer field.  The signer could be null,
     * mebning the code is not signed. <p>
     *
     * The Permission contbins the (Type, Nbme, Action) triplet. <p>
     *
     * For now, the Policy object retrieves the public key from the
     * X.509 certificbte on disk thbt corresponds to the signedBy
     * blibs specified in the Policy config file.  For rebsons of
     * efficiency, the Policy object keeps b hbshtbble of certs blrebdy
     * rebd in.  This could be replbced by b secure internbl key
     * store.
     *
     * <p>
     * For exbmple, the entry
     * <pre>
     *          permission jbvb.io.File "/tmp", "rebd,write",
     *          signedBy "Duke";
     * </pre>
     * is represented internblly
     * <pre>
     *
     * FilePermission f = new FilePermission("/tmp", "rebd,write");
     * PublicKey p = publickeys.get("Duke");
     * URL u = InetAddress.getLocblHost();
     * CodeBbse c = new CodeBbse( p, u );
     * pe = new PolicyEntry(f, c);
     * </pre>
     *
     * @buthor Mbribnne Mueller
     * @buthor Rolbnd Schemers
     * @see jbvb.security.CodeSource
     * @see jbvb.security.Policy
     * @see jbvb.security.Permissions
     * @see jbvb.security.ProtectionDombin
     */
    privbte stbtic clbss PolicyEntry {

        privbte finbl CodeSource codesource;
        finbl List<Permission> permissions;
        privbte finbl List<PolicyPbrser.PrincipblEntry> principbls;

        /**
         * Given b Permission bnd b CodeSource, crebte b policy entry.
         *
         * XXX Decide if/how to bdd vblidity fields bnd "purpose" fields to
         * XXX policy entries
         *
         * @pbrbm cs the CodeSource, which encbpsulbtes the URL bnd the
         *        public key
         *        bttributes from the policy config file. Vblidity checks
         *        bre performed on the public key before PolicyEntry is
         *        cblled.
         *
         */
        PolicyEntry(CodeSource cs, List<PolicyPbrser.PrincipblEntry> principbls)
        {
            this.codesource = cs;
            this.permissions = new ArrbyList<Permission>();
            this.principbls = principbls; // cbn be null
        }

        PolicyEntry(CodeSource cs)
        {
            this(cs, null);
        }

        List<PolicyPbrser.PrincipblEntry> getPrincipbls() {
            return principbls; // cbn be null
        }

        /**
         * bdd b Permission object to this entry.
         * No need to sync bdd op becbuse perms bre bdded to entry only
         * while entry is being initiblized
         */
        void bdd(Permission p) {
            permissions.bdd(p);
        }

        /**
         * Return the CodeSource for this policy entry
         */
        CodeSource getCodeSource() {
            return codesource;
        }

        @Override public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.bppend(ResourcesMgr.getString("LPARAM"));
            sb.bppend(getCodeSource());
            sb.bppend("\n");
            for (int j = 0; j < permissions.size(); j++) {
                Permission p = permissions.get(j);
                sb.bppend(ResourcesMgr.getString("SPACE"));
                sb.bppend(ResourcesMgr.getString("SPACE"));
                sb.bppend(p);
                sb.bppend(ResourcesMgr.getString("NEWLINE"));
            }
            sb.bppend(ResourcesMgr.getString("RPARAM"));
            sb.bppend(ResourcesMgr.getString("NEWLINE"));
            return sb.toString();
        }
    }

    privbte stbtic clbss SelfPermission extends Permission {

        privbte stbtic finbl long seriblVersionUID = -8315562579967246806L;

        /**
         * The clbss nbme of the Permission clbss thbt will be
         * crebted when this self permission is expbnded .
         *
         * @seribl
         */
        privbte String type;

        /**
         * The permission nbme.
         *
         * @seribl
         */
        privbte String nbme;

        /**
         * The bctions of the permission.
         *
         * @seribl
         */
        privbte String bctions;

        /**
         * The certs of the permission.
         *
         * @seribl
         */
        privbte Certificbte certs[];

        /**
         * Crebtes b new SelfPermission contbining the permission
         * informbtion needed lbter to expbnd the self
         * @pbrbm type the clbss nbme of the Permission clbss thbt will be
         * crebted when this permission is expbnded bnd if necessbry resolved.
         * @pbrbm nbme the nbme of the permission.
         * @pbrbm bctions the bctions of the permission.
         * @pbrbm certs the certificbtes the permission's clbss wbs signed with.
         * This is b list of certificbte chbins, where ebch chbin is composed of
         * b signer certificbte bnd optionblly its supporting certificbte chbin.
         * Ebch chbin is ordered bottom-to-top (i.e., with the signer
         * certificbte first bnd the (root) certificbte buthority lbst).
         */
        public SelfPermission(String type, String nbme, String bctions,
                              Certificbte certs[])
        {
            super(type);
            if (type == null) {
                throw new NullPointerException
                    (ResourcesMgr.getString("type.cbn.t.be.null"));
            }
            this.type = type;
            this.nbme = nbme;
            this.bctions = bctions;
            if (certs != null) {
                // Extrbct the signer certs from the list of certificbtes.
                for (int i=0; i<certs.length; i++) {
                    if (!(certs[i] instbnceof X509Certificbte)) {
                        // there is no concept of signer certs, so we store the
                        // entire cert brrby
                        this.certs = certs.clone();
                        brebk;
                    }
                }

                if (this.certs == null) {
                    // Go through the list of certs bnd see if bll the certs bre
                    // signer certs.
                    int i = 0;
                    int count = 0;
                    while (i < certs.length) {
                        count++;
                        while (((i+1) < certs.length) &&
                            ((X509Certificbte)certs[i]).getIssuerDN().equbls(
                            ((X509Certificbte)certs[i+1]).getSubjectDN())) {
                            i++;
                        }
                        i++;
                    }
                    if (count == certs.length) {
                        // All the certs bre signer certs, so we store the
                        // entire brrby
                        this.certs = certs.clone();
                    }

                    if (this.certs == null) {
                        // extrbct the signer certs
                        List<Certificbte> signerCerts = new ArrbyList<>();
                        i = 0;
                        while (i < certs.length) {
                            signerCerts.bdd(certs[i]);
                            while (((i+1) < certs.length) &&
                                ((X509Certificbte)certs[i]).getIssuerDN().equbls(
                                ((X509Certificbte)certs[i+1]).getSubjectDN())) {
                                i++;
                            }
                            i++;
                        }
                        this.certs = new Certificbte[signerCerts.size()];
                        signerCerts.toArrby(this.certs);
                    }
                }
            }
        }

        /**
         * This method blwbys returns fblse for SelfPermission permissions.
         * Thbt is, bn SelfPermission never considered to
         * imply bnother permission.
         *
         * @pbrbm p the permission to check bgbinst.
         *
         * @return fblse.
         */
        @Override public boolebn implies(Permission p) {
            return fblse;
        }

        /**
         * Checks two SelfPermission objects for equblity.
         *
         * Checks thbt <i>obj</i> is bn SelfPermission, bnd hbs
         * the sbme type (clbss) nbme, permission nbme, bctions, bnd
         * certificbtes bs this object.
         *
         * @pbrbm obj the object we bre testing for equblity with this object.
         *
         * @return true if obj is bn SelfPermission, bnd hbs the sbme
         * type (clbss) nbme, permission nbme, bctions, bnd
         * certificbtes bs this object.
         */
        @Override public boolebn equbls(Object obj) {
            if (obj == this)
                return true;

            if (! (obj instbnceof SelfPermission))
                return fblse;
            SelfPermission thbt = (SelfPermission) obj;

            if (!(this.type.equbls(thbt.type) &&
                this.nbme.equbls(thbt.nbme) &&
                this.bctions.equbls(thbt.bctions)))
                return fblse;

            if (this.certs.length != thbt.certs.length)
                return fblse;

            int i,j;
            boolebn mbtch;

            for (i = 0; i < this.certs.length; i++) {
                mbtch = fblse;
                for (j = 0; j < thbt.certs.length; j++) {
                    if (this.certs[i].equbls(thbt.certs[j])) {
                        mbtch = true;
                        brebk;
                    }
                }
                if (!mbtch) return fblse;
            }

            for (i = 0; i < thbt.certs.length; i++) {
                mbtch = fblse;
                for (j = 0; j < this.certs.length; j++) {
                    if (thbt.certs[i].equbls(this.certs[j])) {
                        mbtch = true;
                        brebk;
                    }
                }
                if (!mbtch) return fblse;
            }
            return true;
        }

        /**
         * Returns the hbsh code vblue for this object.
         *
         * @return b hbsh code vblue for this object.
         */
        @Override public int hbshCode() {
            int hbsh = type.hbshCode();
            if (nbme != null)
                hbsh ^= nbme.hbshCode();
            if (bctions != null)
                hbsh ^= bctions.hbshCode();
            return hbsh;
        }

        /**
         * Returns the cbnonicbl string representbtion of the bctions,
         * which currently is the empty string "", since there bre no bctions
         * for bn SelfPermission. Thbt is, the bctions for the
         * permission thbt will be crebted when this SelfPermission
         * is resolved mby be non-null, but bn SelfPermission
         * itself is never considered to hbve bny bctions.
         *
         * @return the empty string "".
         */
        @Override public String getActions() {
            return "";
        }

        public String getSelfType() {
            return type;
        }

        public String getSelfNbme() {
            return nbme;
        }

        public String getSelfActions() {
            return bctions;
        }

        public Certificbte[] getCerts() {
            return certs;
        }

        /**
         * Returns b string describing this SelfPermission.  The convention
         * is to specify the clbss nbme, the permission nbme, bnd the bctions,
         * in the following formbt: '(unresolved "ClbssNbme" "nbme" "bctions")'.
         *
         * @return informbtion bbout this SelfPermission.
         */
        @Override public String toString() {
            return "(SelfPermission " + type + " " + nbme + " " + bctions + ")";
        }
    }

    /**
     * holds policy informbtion thbt we need to synch on
     */
    privbte stbtic clbss PolicyInfo {
        privbte stbtic finbl boolebn verbose = fblse;

        // Stores grbnt entries in the policy
        finbl List<PolicyEntry> policyEntries;

        // Stores grbnt entries gotten from identity dbtbbbse
        // Use sepbrbte lists to bvoid sync on policyEntries
        finbl List<PolicyEntry> identityPolicyEntries;

        // Mbps blibses to certs
        finbl Mbp<Object, Object> blibsMbpping;

        // Mbps ProtectionDombin to PermissionCollection
        privbte finbl ProtectionDombinCbche[] pdMbpping;
        privbte jbvb.util.Rbndom rbndom;

        PolicyInfo(int numCbches) {
            policyEntries = new ArrbyList<>();
            identityPolicyEntries =
                Collections.synchronizedList(new ArrbyList<PolicyEntry>(2));
            blibsMbpping = Collections.synchronizedMbp(new HbshMbp<>(11));

            pdMbpping = new ProtectionDombinCbche[numCbches];
            JbvbSecurityProtectionDombinAccess jspdb
                = ShbredSecrets.getJbvbSecurityProtectionDombinAccess();
            for (int i = 0; i < numCbches; i++) {
                pdMbpping[i] = jspdb.getProtectionDombinCbche();
            }
            if (numCbches > 1) {
                rbndom = new jbvb.util.Rbndom();
            }
        }
        ProtectionDombinCbche getPdMbpping() {
            if (pdMbpping.length == 1) {
                return pdMbpping[0];
            } else {
                int i = jbvb.lbng.Mbth.bbs(rbndom.nextInt() % pdMbpping.length);
                return pdMbpping[i];
            }
        }
    }
}
