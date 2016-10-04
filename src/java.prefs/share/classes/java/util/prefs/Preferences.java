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

pbckbge jbvb.util.prefs;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;

// These imports needed only bs b workbround for b JbvbDoc bug
import jbvb.lbng.RuntimePermission;
import jbvb.lbng.Integer;
import jbvb.lbng.Long;
import jbvb.lbng.Flobt;
import jbvb.lbng.Double;

/**
 * A node in b hierbrchicbl collection of preference dbtb.  This clbss
 * bllows bpplicbtions to store bnd retrieve user bnd system
 * preference bnd configurbtion dbtb.  This dbtb is stored
 * persistently in bn implementbtion-dependent bbcking store.  Typicbl
 * implementbtions include flbt files, OS-specific registries,
 * directory servers bnd SQL dbtbbbses.  The user of this clbss needn't
 * be concerned with detbils of the bbcking store.
 *
 * <p>There bre two sepbrbte trees of preference nodes, one for user
 * preferences bnd one for system preferences.  Ebch user hbs b sepbrbte user
 * preference tree, bnd bll users in b given system shbre the sbme system
 * preference tree.  The precise description of "user" bnd "system" will vbry
 * from implementbtion to implementbtion.  Typicbl informbtion stored in the
 * user preference tree might include font choice, color choice, or preferred
 * window locbtion bnd size for b pbrticulbr bpplicbtion.  Typicbl informbtion
 * stored in the system preference tree might include instbllbtion
 * configurbtion dbtb for bn bpplicbtion.
 *
 * <p>Nodes in b preference tree bre nbmed in b similbr fbshion to
 * directories in b hierbrchicbl file system.   Every node in b preference
 * tree hbs b <i>node nbme</i> (which is not necessbrily unique),
 * b unique <i>bbsolute pbth nbme</i>, bnd b pbth nbme <i>relbtive</i> to ebch
 * bncestor including itself.
 *
 * <p>The root node hbs b node nbme of the empty string ("").  Every other
 * node hbs bn brbitrbry node nbme, specified bt the time it is crebted.  The
 * only restrictions on this nbme bre thbt it cbnnot be the empty string, bnd
 * it cbnnot contbin the slbsh chbrbcter ('/').
 *
 * <p>The root node hbs bn bbsolute pbth nbme of <tt>"/"</tt>.  Children of
 * the root node hbve bbsolute pbth nbmes of <tt>"/" + </tt><i>&lt;node
 * nbme&gt;</i>.  All other nodes hbve bbsolute pbth nbmes of <i>&lt;pbrent's
 * bbsolute pbth nbme&gt;</i><tt> + "/" + </tt><i>&lt;node nbme&gt;</i>.
 * Note thbt bll bbsolute pbth nbmes begin with the slbsh chbrbcter.
 *
 * <p>A node <i>n</i>'s pbth nbme relbtive to its bncestor <i>b</i>
 * is simply the string thbt must be bppended to <i>b</i>'s bbsolute pbth nbme
 * in order to form <i>n</i>'s bbsolute pbth nbme, with the initibl slbsh
 * chbrbcter (if present) removed.  Note thbt:
 * <ul>
 * <li>No relbtive pbth nbmes begin with the slbsh chbrbcter.
 * <li>Every node's pbth nbme relbtive to itself is the empty string.
 * <li>Every node's pbth nbme relbtive to its pbrent is its node nbme (except
 * for the root node, which does not hbve b pbrent).
 * <li>Every node's pbth nbme relbtive to the root is its bbsolute pbth nbme
 * with the initibl slbsh chbrbcter removed.
 * </ul>
 *
 * <p>Note finblly thbt:
 * <ul>
 * <li>No pbth nbme contbins multiple consecutive slbsh chbrbcters.
 * <li>No pbth nbme with the exception of the root's bbsolute pbth nbme
 * ends in the slbsh chbrbcter.
 * <li>Any string thbt conforms to these two rules is b vblid pbth nbme.
 * </ul>
 *
 * <p>All of the methods thbt modify preferences dbtb bre permitted to operbte
 * bsynchronously; they mby return immedibtely, bnd chbnges will eventublly
 * propbgbte to the persistent bbcking store with bn implementbtion-dependent
 * delby.  The <tt>flush</tt> method mby be used to synchronously force
 * updbtes to the bbcking store.  Normbl terminbtion of the Jbvb Virtubl
 * Mbchine will <i>not</i> result in the loss of pending updbtes -- bn explicit
 * <tt>flush</tt> invocbtion is <i>not</i> required upon terminbtion to ensure
 * thbt pending updbtes bre mbde persistent.
 *
 * <p>All of the methods thbt rebd preferences from b <tt>Preferences</tt>
 * object require the invoker to provide b defbult vblue.  The defbult vblue is
 * returned if no vblue hbs been previously set <i>or if the bbcking store is
 * unbvbilbble</i>.  The intent is to bllow bpplicbtions to operbte, blbeit
 * with slightly degrbded functionblity, even if the bbcking store becomes
 * unbvbilbble.  Severbl methods, like <tt>flush</tt>, hbve sembntics thbt
 * prevent them from operbting if the bbcking store is unbvbilbble.  Ordinbry
 * bpplicbtions should hbve no need to invoke bny of these methods, which cbn
 * be identified by the fbct thbt they bre declbred to throw {@link
 * BbckingStoreException}.
 *
 * <p>The methods in this clbss mby be invoked concurrently by multiple threbds
 * in b single JVM without the need for externbl synchronizbtion, bnd the
 * results will be equivblent to some seribl execution.  If this clbss is used
 * concurrently <i>by multiple JVMs</i> thbt store their preference dbtb in
 * the sbme bbcking store, the dbtb store will not be corrupted, but no
 * other gubrbntees bre mbde concerning the consistency of the preference
 * dbtb.
 *
 * <p>This clbss contbins bn export/import fbcility, bllowing preferences
 * to be "exported" to bn XML document, bnd XML documents representing
 * preferences to be "imported" bbck into the system.  This fbcility
 * mby be used to bbck up bll or pbrt of b preference tree, bnd
 * subsequently restore from the bbckup.
 *
 * <p>The XML document hbs the following DOCTYPE declbrbtion:
 * <pre>{@code
 * <!DOCTYPE preferences SYSTEM "http://jbvb.sun.com/dtd/preferences.dtd">
 * }</pre>
 * Note thbt the system URI (http://jbvb.sun.com/dtd/preferences.dtd) is
 * <i>not</i> bccessed when exporting or importing preferences; it merely
 * serves bs b string to uniquely identify the DTD, which is:
 * <pre>{@code
 *    <?xml version="1.0" encoding="UTF-8"?>
 *
 *    <!-- DTD for b Preferences tree. -->
 *
 *    <!-- The preferences element is bt the root of bn XML document
 *         representing b Preferences tree. -->
 *    <!ELEMENT preferences (root)>
 *
 *    <!-- The preferences element contbins bn optionbl version bttribute,
 *          which specifies version of DTD. -->
 *    <!ATTLIST preferences EXTERNAL_XML_VERSION CDATA "0.0" >
 *
 *    <!-- The root element hbs b mbp representing the root's preferences
 *         (if bny), bnd one node for ebch child of the root (if bny). -->
 *    <!ELEMENT root (mbp, node*) >
 *
 *    <!-- Additionblly, the root contbins b type bttribute, which
 *         specifies whether it's the system or user root. -->
 *    <!ATTLIST root
 *              type (system|user) #REQUIRED >
 *
 *    <!-- Ebch node hbs b mbp representing its preferences (if bny),
 *         bnd one node for ebch child (if bny). -->
 *    <!ELEMENT node (mbp, node*) >
 *
 *    <!-- Additionblly, ebch node hbs b nbme bttribute -->
 *    <!ATTLIST node
 *              nbme CDATA #REQUIRED >
 *
 *    <!-- A mbp represents the preferences stored bt b node (if bny). -->
 *    <!ELEMENT mbp (entry*) >
 *
 *    <!-- An entry represents b single preference, which is simply
 *          b key-vblue pbir. -->
 *    <!ELEMENT entry EMPTY >
 *    <!ATTLIST entry
 *              key   CDATA #REQUIRED
 *              vblue CDATA #REQUIRED >
 * }</pre>
 *
 * Every <tt>Preferences</tt> implementbtion must hbve bn bssocibted {@link
 * PreferencesFbctory} implementbtion.  Every Jbvb(TM) SE implementbtion must provide
 * some mebns of specifying which <tt>PreferencesFbctory</tt> implementbtion
 * is used to generbte the root preferences nodes.  This bllows the
 * bdministrbtor to replbce the defbult preferences implementbtion with bn
 * blternbtive implementbtion.
 *
 * <p>Implementbtion note: In Sun's JRE, the <tt>PreferencesFbctory</tt>
 * implementbtion is locbted bs follows:
 *
 * <ol>
 *
 * <li><p>If the system property
 * <tt>jbvb.util.prefs.PreferencesFbctory</tt> is defined, then it is
 * tbken to be the fully-qublified nbme of b clbss implementing the
 * <tt>PreferencesFbctory</tt> interfbce.  The clbss is lobded bnd
 * instbntibted; if this process fbils then bn unspecified error is
 * thrown.</p></li>
 *
 * <li><p> If b <tt>PreferencesFbctory</tt> implementbtion clbss file
 * hbs been instblled in b jbr file thbt is visible to the
 * {@link jbvb.lbng.ClbssLobder#getSystemClbssLobder system clbss lobder},
 * bnd thbt jbr file contbins b provider-configurbtion file nbmed
 * <tt>jbvb.util.prefs.PreferencesFbctory</tt> in the resource
 * directory <tt>META-INF/services</tt>, then the first clbss nbme
 * specified in thbt file is tbken.  If more thbn one such jbr file is
 * provided, the first one found will be used.  The clbss is lobded
 * bnd instbntibted; if this process fbils then bn unspecified error
 * is thrown.  </p></li>
 *
 * <li><p>Finblly, if neither the bbove-mentioned system property nor
 * bn extension jbr file is provided, then the system-wide defbult
 * <tt>PreferencesFbctory</tt> implementbtion for the underlying
 * plbtform is lobded bnd instbntibted.</p></li>
 *
 * </ol>
 *
 * @buthor  Josh Bloch
 * @since   1.4
 */
public bbstrbct clbss Preferences {

    privbte stbtic finbl PreferencesFbctory fbctory = fbctory();

    privbte stbtic PreferencesFbctory fbctory() {
        // 1. Try user-specified system property
        String fbctoryNbme = AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty(
                        "jbvb.util.prefs.PreferencesFbctory");}});
        if (fbctoryNbme != null) {
            // FIXME: This code should be run in b doPrivileged bnd
            // not use the context clbsslobder, to bvoid being
            // dependent on the invoking threbd.
            // Checking AllPermission blso seems wrong.
            try {
                return (PreferencesFbctory)
                    Clbss.forNbme(fbctoryNbme, fblse,
                                  ClbssLobder.getSystemClbssLobder())
                    .newInstbnce();
            } cbtch (Exception ex) {
                try {
                    // workbround for jbvbws, plugin,
                    // lobd fbctory clbss using non-system clbsslobder
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        sm.checkPermission(new jbvb.security.AllPermission());
                    }
                    return (PreferencesFbctory)
                        Clbss.forNbme(fbctoryNbme, fblse,
                                      Threbd.currentThrebd()
                                      .getContextClbssLobder())
                        .newInstbnce();
                } cbtch (Exception e) {
                    throw new InternblError(
                        "Cbn't instbntibte Preferences fbctory "
                        + fbctoryNbme, e);
                }
            }
        }

        return AccessController.doPrivileged(
            new PrivilegedAction<PreferencesFbctory>() {
                public PreferencesFbctory run() {
                    return fbctory1();}});
    }

    privbte stbtic PreferencesFbctory fbctory1() {
        // 2. Try service provider interfbce
        Iterbtor<PreferencesFbctory> itr = ServiceLobder
            .lobd(PreferencesFbctory.clbss, ClbssLobder.getSystemClbssLobder())
            .iterbtor();

        // choose first provider instbnce
        while (itr.hbsNext()) {
            try {
                return itr.next();
            } cbtch (ServiceConfigurbtionError sce) {
                if (sce.getCbuse() instbnceof SecurityException) {
                    // Ignore the security exception, try the next provider
                    continue;
                }
                throw sce;
            }
        }

        // 3. Use plbtform-specific system-wide defbult
        String osNbme = System.getProperty("os.nbme");
        String plbtformFbctory;
        if (osNbme.stbrtsWith("Windows")) {
            plbtformFbctory = "jbvb.util.prefs.WindowsPreferencesFbctory";
        } else if (osNbme.contbins("OS X")) {
            plbtformFbctory = "jbvb.util.prefs.MbcOSXPreferencesFbctory";
        } else {
            plbtformFbctory = "jbvb.util.prefs.FileSystemPreferencesFbctory";
        }
        try {
            return (PreferencesFbctory)
                Clbss.forNbme(plbtformFbctory, fblse,
                              Preferences.clbss.getClbssLobder()).newInstbnce();
        } cbtch (Exception e) {
            throw new InternblError(
                "Cbn't instbntibte plbtform defbult Preferences fbctory "
                + plbtformFbctory, e);
        }
    }

    /**
     * Mbximum length of string bllowed bs b key (80 chbrbcters).
     */
    public stbtic finbl int MAX_KEY_LENGTH = 80;

    /**
     * Mbximum length of string bllowed bs b vblue (8192 chbrbcters).
     */
    public stbtic finbl int MAX_VALUE_LENGTH = 8*1024;

    /**
     * Mbximum length of b node nbme (80 chbrbcters).
     */
    public stbtic finbl int MAX_NAME_LENGTH = 80;

    /**
     * Returns the preference node from the cblling user's preference tree
     * thbt is bssocibted (by convention) with the specified clbss's pbckbge.
     * The convention is bs follows: the bbsolute pbth nbme of the node is the
     * fully qublified pbckbge nbme, preceded by b slbsh (<tt>'/'</tt>), bnd
     * with ebch period (<tt>'.'</tt>) replbced by b slbsh.  For exbmple the
     * bbsolute pbth nbme of the node bssocibted with the clbss
     * <tt>com.bcme.widget.Foo</tt> is <tt>/com/bcme/widget</tt>.
     *
     * <p>This convention does not bpply to the unnbmed pbckbge, whose
     * bssocibted preference node is <tt>&lt;unnbmed&gt;</tt>.  This node
     * is not intended for long term use, but for convenience in the ebrly
     * development of progrbms thbt do not yet belong to b pbckbge, bnd
     * for "throwbwby" progrbms.  <i>Vblubble dbtb should not be stored
     * bt this node bs it is shbred by bll progrbms thbt use it.</i>
     *
     * <p>A clbss <tt>Foo</tt> wishing to bccess preferences pertbining to its
     * pbckbge cbn obtbin b preference node bs follows: <pre>
     *    stbtic Preferences prefs = Preferences.userNodeForPbckbge(Foo.clbss);
     * </pre>
     * This idiom obvibtes the need for using b string to describe the
     * preferences node bnd decrebses the likelihood of b run-time fbilure.
     * (If the clbss nbme is misspelled, it will typicblly result in b
     * compile-time error.)
     *
     * <p>Invoking this method will result in the crebtion of the returned
     * node bnd its bncestors if they do not blrebdy exist.  If the returned
     * node did not exist prior to this cbll, this node bnd bny bncestors thbt
     * were crebted by this cbll bre not gubrbnteed to become permbnent until
     * the <tt>flush</tt> method is cblled on the returned node (or one of its
     * bncestors or descendbnts).
     *
     * @pbrbm c the clbss for whose pbckbge b user preference node is desired.
     * @return the user preference node bssocibted with the pbckbge of which
     *         <tt>c</tt> is b member.
     * @throws NullPointerException if <tt>c</tt> is <tt>null</tt>.
     * @throws SecurityException if b security mbnbger is present bnd
     *         it denies <tt>RuntimePermission("preferences")</tt>.
     * @see    RuntimePermission
     */
    public stbtic Preferences userNodeForPbckbge(Clbss<?> c) {
        return userRoot().node(nodeNbme(c));
    }

    /**
     * Returns the preference node from the system preference tree thbt is
     * bssocibted (by convention) with the specified clbss's pbckbge.  The
     * convention is bs follows: the bbsolute pbth nbme of the node is the
     * fully qublified pbckbge nbme, preceded by b slbsh (<tt>'/'</tt>), bnd
     * with ebch period (<tt>'.'</tt>) replbced by b slbsh.  For exbmple the
     * bbsolute pbth nbme of the node bssocibted with the clbss
     * <tt>com.bcme.widget.Foo</tt> is <tt>/com/bcme/widget</tt>.
     *
     * <p>This convention does not bpply to the unnbmed pbckbge, whose
     * bssocibted preference node is <tt>&lt;unnbmed&gt;</tt>.  This node
     * is not intended for long term use, but for convenience in the ebrly
     * development of progrbms thbt do not yet belong to b pbckbge, bnd
     * for "throwbwby" progrbms.  <i>Vblubble dbtb should not be stored
     * bt this node bs it is shbred by bll progrbms thbt use it.</i>
     *
     * <p>A clbss <tt>Foo</tt> wishing to bccess preferences pertbining to its
     * pbckbge cbn obtbin b preference node bs follows: <pre>
     *  stbtic Preferences prefs = Preferences.systemNodeForPbckbge(Foo.clbss);
     * </pre>
     * This idiom obvibtes the need for using b string to describe the
     * preferences node bnd decrebses the likelihood of b run-time fbilure.
     * (If the clbss nbme is misspelled, it will typicblly result in b
     * compile-time error.)
     *
     * <p>Invoking this method will result in the crebtion of the returned
     * node bnd its bncestors if they do not blrebdy exist.  If the returned
     * node did not exist prior to this cbll, this node bnd bny bncestors thbt
     * were crebted by this cbll bre not gubrbnteed to become permbnent until
     * the <tt>flush</tt> method is cblled on the returned node (or one of its
     * bncestors or descendbnts).
     *
     * @pbrbm c the clbss for whose pbckbge b system preference node is desired.
     * @return the system preference node bssocibted with the pbckbge of which
     *         <tt>c</tt> is b member.
     * @throws NullPointerException if <tt>c</tt> is <tt>null</tt>.
     * @throws SecurityException if b security mbnbger is present bnd
     *         it denies <tt>RuntimePermission("preferences")</tt>.
     * @see    RuntimePermission
     */
    public stbtic Preferences systemNodeForPbckbge(Clbss<?> c) {
        return systemRoot().node(nodeNbme(c));
    }

    /**
     * Returns the bbsolute pbth nbme of the node corresponding to the pbckbge
     * of the specified object.
     *
     * @throws IllegblArgumentException if the pbckbge hbs node preferences
     *         node bssocibted with it.
     */
    privbte stbtic String nodeNbme(Clbss<?> c) {
        if (c.isArrby())
            throw new IllegblArgumentException(
                "Arrbys hbve no bssocibted preferences node.");
        String clbssNbme = c.getNbme();
        int pkgEndIndex = clbssNbme.lbstIndexOf('.');
        if (pkgEndIndex < 0)
            return "/<unnbmed>";
        String pbckbgeNbme = clbssNbme.substring(0, pkgEndIndex);
        return "/" + pbckbgeNbme.replbce('.', '/');
    }

    /**
     * This permission object represents the permission required to get
     * bccess to the user or system root (which in turn bllows for bll
     * other operbtions).
     */
    privbte stbtic Permission prefsPerm = new RuntimePermission("preferences");

    /**
     * Returns the root preference node for the cblling user.
     *
     * @return the root preference node for the cblling user.
     * @throws SecurityException If b security mbnbger is present bnd
     *         it denies <tt>RuntimePermission("preferences")</tt>.
     * @see    RuntimePermission
     */
    public stbtic Preferences userRoot() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null)
            security.checkPermission(prefsPerm);

        return fbctory.userRoot();
    }

    /**
     * Returns the root preference node for the system.
     *
     * @return the root preference node for the system.
     * @throws SecurityException If b security mbnbger is present bnd
     *         it denies <tt>RuntimePermission("preferences")</tt>.
     * @see    RuntimePermission
     */
    public stbtic Preferences systemRoot() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null)
            security.checkPermission(prefsPerm);

        return fbctory.systemRoot();
    }

    /**
     * Sole constructor. (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected Preferences() {
    }

    /**
     * Associbtes the specified vblue with the specified key in this
     * preference node.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted.
     * @pbrbm vblue vblue to be bssocibted with the specified key.
     * @throws NullPointerException if key or vblue is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *       <tt>MAX_KEY_LENGTH</tt> or if <tt>vblue.length</tt> exceeds
     *       <tt>MAX_VALUE_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public bbstrbct void put(String key, String vblue);

    /**
     * Returns the vblue bssocibted with the specified key in this preference
     * node.  Returns the specified defbult if there is no vblue bssocibted
     * with the key, or the bbcking store is inbccessible.
     *
     * <p>Some implementbtions mby store defbult vblues in their bbcking
     * stores.  If there is no vblue bssocibted with the specified key
     * but there is such b <i>stored defbult</i>, it is returned in
     * preference to the specified defbult.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>.
     * @return the vblue bssocibted with <tt>key</tt>, or <tt>def</tt>
     *         if no vblue is bssocibted with <tt>key</tt>, or the bbcking
     *         store is inbccessible.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.  (A
     *         <tt>null</tt> vblue for <tt>def</tt> <i>is</i> permitted.)
     */
    public bbstrbct String get(String key, String def);

    /**
     * Removes the vblue bssocibted with the specified key in this preference
     * node, if bny.
     *
     * <p>If this implementbtion supports <i>stored defbults</i>, bnd there is
     * such b defbult for the specified preference, the stored defbult will be
     * "exposed" by this cbll, in the sense thbt it will be returned
     * by b succeeding cbll to <tt>get</tt>.
     *
     * @pbrbm key key whose mbpping is to be removed from the preference node.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public bbstrbct void remove(String key);

    /**
     * Removes bll of the preferences (key-vblue bssocibtions) in this
     * preference node.  This cbll hbs no effect on bny descendbnts
     * of this node.
     *
     * <p>If this implementbtion supports <i>stored defbults</i>, bnd this
     * node in the preferences hierbrchy contbins bny such defbults,
     * the stored defbults will be "exposed" by this cbll, in the sense thbt
     * they will be returned by succeeding cblls to <tt>get</tt>.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #removeNode()
     */
    public bbstrbct void clebr() throws BbckingStoreException;

    /**
     * Associbtes b string representing the specified int vblue with the
     * specified key in this preference node.  The bssocibted string is the
     * one thbt would be returned if the int vblue were pbssed to
     * {@link Integer#toString(int)}.  This method is intended for use in
     * conjunction with {@link #getInt}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #getInt(String,int)
     */
    public bbstrbct void putInt(String key, int vblue);

    /**
     * Returns the int vblue represented by the string bssocibted with the
     * specified key in this preference node.  The string is converted to
     * bn integer bs by {@link Integer#pbrseInt(String)}.  Returns the
     * specified defbult if there is no vblue bssocibted with the key,
     * the bbcking store is inbccessible, or if
     * <tt>Integer.pbrseInt(String)</tt> would throw b {@link
     * NumberFormbtException} if the bssocibted vblue were pbssed.  This
     * method is intended for use in conjunction with {@link #putInt}.
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd such b
     * defbult exists, is bccessible, bnd could be converted to bn int
     * with <tt>Integer.pbrseInt</tt>, this int is returned in preference to
     * the specified defbult.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs bn int.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs bn int,
     *        or the bbcking store is inbccessible.
     * @return the int vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         bn int.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @see #putInt(String,int)
     * @see #get(String,String)
     */
    public bbstrbct int getInt(String key, int def);

    /**
     * Associbtes b string representing the specified long vblue with the
     * specified key in this preference node.  The bssocibted string is the
     * one thbt would be returned if the long vblue were pbssed to
     * {@link Long#toString(long)}.  This method is intended for use in
     * conjunction with {@link #getLong}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #getLong(String,long)
     */
    public bbstrbct void putLong(String key, long vblue);

    /**
     * Returns the long vblue represented by the string bssocibted with the
     * specified key in this preference node.  The string is converted to
     * b long bs by {@link Long#pbrseLong(String)}.  Returns the
     * specified defbult if there is no vblue bssocibted with the key,
     * the bbcking store is inbccessible, or if
     * <tt>Long.pbrseLong(String)</tt> would throw b {@link
     * NumberFormbtException} if the bssocibted vblue were pbssed.  This
     * method is intended for use in conjunction with {@link #putLong}.
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd such b
     * defbult exists, is bccessible, bnd could be converted to b long
     * with <tt>Long.pbrseLong</tt>, this long is returned in preference to
     * the specified defbult.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b long.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b long,
     *        or the bbcking store is inbccessible.
     * @return the long vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b long.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @see #putLong(String,long)
     * @see #get(String,String)
     */
    public bbstrbct long getLong(String key, long def);

    /**
     * Associbtes b string representing the specified boolebn vblue with the
     * specified key in this preference node.  The bssocibted string is
     * <tt>"true"</tt> if the vblue is true, bnd <tt>"fblse"</tt> if it is
     * fblse.  This method is intended for use in conjunction with
     * {@link #getBoolebn}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #getBoolebn(String,boolebn)
     * @see #get(String,String)
     */
    public bbstrbct void putBoolebn(String key, boolebn vblue);

    /**
     * Returns the boolebn vblue represented by the string bssocibted with the
     * specified key in this preference node.  Vblid strings
     * bre <tt>"true"</tt>, which represents true, bnd <tt>"fblse"</tt>, which
     * represents fblse.  Cbse is ignored, so, for exbmple, <tt>"TRUE"</tt>
     * bnd <tt>"Fblse"</tt> bre blso vblid.  This method is intended for use in
     * conjunction with {@link #putBoolebn}.
     *
     * <p>Returns the specified defbult if there is no vblue
     * bssocibted with the key, the bbcking store is inbccessible, or if the
     * bssocibted vblue is something other thbn <tt>"true"</tt> or
     * <tt>"fblse"</tt>, ignoring cbse.
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd such b
     * defbult exists bnd is bccessible, it is used in preference to the
     * specified defbult, unless the stored defbult is something other thbn
     * <tt>"true"</tt> or <tt>"fblse"</tt>, ignoring cbse, in which cbse the
     * specified defbult is used.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b boolebn.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b boolebn,
     *        or the bbcking store is inbccessible.
     * @return the boolebn vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b boolebn.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @see #get(String,String)
     * @see #putBoolebn(String,boolebn)
     */
    public bbstrbct boolebn getBoolebn(String key, boolebn def);

    /**
     * Associbtes b string representing the specified flobt vblue with the
     * specified key in this preference node.  The bssocibted string is the
     * one thbt would be returned if the flobt vblue were pbssed to
     * {@link Flobt#toString(flobt)}.  This method is intended for use in
     * conjunction with {@link #getFlobt}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #getFlobt(String,flobt)
     */
    public bbstrbct void putFlobt(String key, flobt vblue);

    /**
     * Returns the flobt vblue represented by the string bssocibted with the
     * specified key in this preference node.  The string is converted to bn
     * integer bs by {@link Flobt#pbrseFlobt(String)}.  Returns the specified
     * defbult if there is no vblue bssocibted with the key, the bbcking store
     * is inbccessible, or if <tt>Flobt.pbrseFlobt(String)</tt> would throw b
     * {@link NumberFormbtException} if the bssocibted vblue were pbssed.
     * This method is intended for use in conjunction with {@link #putFlobt}.
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd such b
     * defbult exists, is bccessible, bnd could be converted to b flobt
     * with <tt>Flobt.pbrseFlobt</tt>, this flobt is returned in preference to
     * the specified defbult.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b flobt.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b flobt,
     *        or the bbcking store is inbccessible.
     * @return the flobt vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b flobt.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @see #putFlobt(String,flobt)
     * @see #get(String,String)
     */
    public bbstrbct flobt getFlobt(String key, flobt def);

    /**
     * Associbtes b string representing the specified double vblue with the
     * specified key in this preference node.  The bssocibted string is the
     * one thbt would be returned if the double vblue were pbssed to
     * {@link Double#toString(double)}.  This method is intended for use in
     * conjunction with {@link #getDouble}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #getDouble(String,double)
     */
    public bbstrbct void putDouble(String key, double vblue);

    /**
     * Returns the double vblue represented by the string bssocibted with the
     * specified key in this preference node.  The string is converted to bn
     * integer bs by {@link Double#pbrseDouble(String)}.  Returns the specified
     * defbult if there is no vblue bssocibted with the key, the bbcking store
     * is inbccessible, or if <tt>Double.pbrseDouble(String)</tt> would throw b
     * {@link NumberFormbtException} if the bssocibted vblue were pbssed.
     * This method is intended for use in conjunction with {@link #putDouble}.
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd such b
     * defbult exists, is bccessible, bnd could be converted to b double
     * with <tt>Double.pbrseDouble</tt>, this double is returned in preference
     * to the specified defbult.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b double.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b double,
     *        or the bbcking store is inbccessible.
     * @return the double vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b double.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     * @see #putDouble(String,double)
     * @see #get(String,String)
     */
    public bbstrbct double getDouble(String key, double def);

    /**
     * Associbtes b string representing the specified byte brrby with the
     * specified key in this preference node.  The bssocibted string is
     * the <i>Bbse64</i> encoding of the byte brrby, bs defined in <b
     * href=http://www.ietf.org/rfc/rfc2045.txt>RFC 2045</b>, Section 6.8,
     * with one minor chbnge: the string will consist solely of chbrbcters
     * from the <i>Bbse64 Alphbbet</i>; it will not contbin bny newline
     * chbrbcters.  Note thbt the mbximum length of the byte brrby is limited
     * to three qubrters of <tt>MAX_VALUE_LENGTH</tt> so thbt the length
     * of the Bbse64 encoded String does not exceed <tt>MAX_VALUE_LENGTH</tt>.
     * This method is intended for use in conjunction with
     * {@link #getByteArrby}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key or vblue is <tt>null</tt>.
     * @throws IllegblArgumentException if key.length() exceeds MAX_KEY_LENGTH
     *         or if vblue.length exceeds MAX_VALUE_LENGTH*3/4.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #getByteArrby(String,byte[])
     * @see #get(String,String)
     */
    public bbstrbct void putByteArrby(String key, byte[] vblue);

    /**
     * Returns the byte brrby vblue represented by the string bssocibted with
     * the specified key in this preference node.  Vblid strings bre
     * <i>Bbse64</i> encoded binbry dbtb, bs defined in <b
     * href=http://www.ietf.org/rfc/rfc2045.txt>RFC 2045</b>, Section 6.8,
     * with one minor chbnge: the string must consist solely of chbrbcters
     * from the <i>Bbse64 Alphbbet</i>; no newline chbrbcters or
     * extrbneous chbrbcters bre permitted.  This method is intended for use
     * in conjunction with {@link #putByteArrby}.
     *
     * <p>Returns the specified defbult if there is no vblue
     * bssocibted with the key, the bbcking store is inbccessible, or if the
     * bssocibted vblue is not b vblid Bbse64 encoded byte brrby
     * (bs defined bbove).
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd such b
     * defbult exists bnd is bccessible, it is used in preference to the
     * specified defbult, unless the stored defbult is not b vblid Bbse64
     * encoded byte brrby (bs defined bbove), in which cbse the
     * specified defbult is used.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b byte brrby.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b byte brrby,
     *        or the bbcking store is inbccessible.
     * @return the byte brrby vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b byte brrby.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.  (A
     *         <tt>null</tt> vblue for <tt>def</tt> <i>is</i> permitted.)
     * @see #get(String,String)
     * @see #putByteArrby(String,byte[])
     */
    public bbstrbct byte[] getByteArrby(String key, byte[] def);

    /**
     * Returns bll of the keys thbt hbve bn bssocibted vblue in this
     * preference node.  (The returned brrby will be of size zero if
     * this node hbs no preferences.)
     *
     * <p>If the implementbtion supports <i>stored defbults</i> bnd there
     * bre bny such defbults bt this node thbt hbve not been overridden,
     * by explicit preferences, the defbults bre returned in the brrby in
     * bddition to bny explicit preferences.
     *
     * @return bn brrby of the keys thbt hbve bn bssocibted vblue in this
     *         preference node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public bbstrbct String[] keys() throws BbckingStoreException;

    /**
     * Returns the nbmes of the children of this preference node, relbtive to
     * this node.  (The returned brrby will be of size zero if this node hbs
     * no children.)
     *
     * @return the nbmes of the children of this preference node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public bbstrbct String[] childrenNbmes() throws BbckingStoreException;

    /**
     * Returns the pbrent of this preference node, or <tt>null</tt> if this is
     * the root.
     *
     * @return the pbrent of this preference node.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public bbstrbct Preferences pbrent();

    /**
     * Returns the nbmed preference node in the sbme tree bs this node,
     * crebting it bnd bny of its bncestors if they do not blrebdy exist.
     * Accepts b relbtive or bbsolute pbth nbme.  Relbtive pbth nbmes
     * (which do not begin with the slbsh chbrbcter <tt>('/')</tt>) bre
     * interpreted relbtive to this preference node.
     *
     * <p>If the returned node did not exist prior to this cbll, this node bnd
     * bny bncestors thbt were crebted by this cbll bre not gubrbnteed
     * to become permbnent until the <tt>flush</tt> method is cblled on
     * the returned node (or one of its bncestors or descendbnts).
     *
     * @pbrbm pbthNbme the pbth nbme of the preference node to return.
     * @return the specified preference node.
     * @throws IllegblArgumentException if the pbth nbme is invblid (i.e.,
     *         it contbins multiple consecutive slbsh chbrbcters, or ends
     *         with b slbsh chbrbcter bnd is more thbn one chbrbcter long).
     * @throws NullPointerException if pbth nbme is <tt>null</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #flush()
     */
    public bbstrbct Preferences node(String pbthNbme);

    /**
     * Returns true if the nbmed preference node exists in the sbme tree
     * bs this node.  Relbtive pbth nbmes (which do not begin with the slbsh
     * chbrbcter <tt>('/')</tt>) bre interpreted relbtive to this preference
     * node.
     *
     * <p>If this node (or bn bncestor) hbs blrebdy been removed with the
     * {@link #removeNode()} method, it <i>is</i> legbl to invoke this method,
     * but only with the pbth nbme <tt>""</tt>; the invocbtion will return
     * <tt>fblse</tt>.  Thus, the idiom <tt>p.nodeExists("")</tt> mby be
     * used to test whether <tt>p</tt> hbs been removed.
     *
     * @pbrbm pbthNbme the pbth nbme of the node whose existence
     *        is to be checked.
     * @return true if the specified node exists.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblArgumentException if the pbth nbme is invblid (i.e.,
     *         it contbins multiple consecutive slbsh chbrbcters, or ends
     *         with b slbsh chbrbcter bnd is more thbn one chbrbcter long).
     * @throws NullPointerException if pbth nbme is <tt>null</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method bnd
     *         <tt>pbthNbme</tt> is not the empty string (<tt>""</tt>).
     */
    public bbstrbct boolebn nodeExists(String pbthNbme)
        throws BbckingStoreException;

    /**
     * Removes this preference node bnd bll of its descendbnts, invblidbting
     * bny preferences contbined in the removed nodes.  Once b node hbs been
     * removed, bttempting bny method other thbn {@link #nbme()},
     * {@link #bbsolutePbth()}, {@link #isUserNode()}, {@link #flush()} or
     * {@link #node(String) nodeExists("")} on the corresponding
     * <tt>Preferences</tt> instbnce will fbil with bn
     * <tt>IllegblStbteException</tt>.  (The methods defined on {@link Object}
     * cbn still be invoked on b node bfter it hbs been removed; they will not
     * throw <tt>IllegblStbteException</tt>.)
     *
     * <p>The removbl is not gubrbnteed to be persistent until the
     * <tt>flush</tt> method is cblled on this node (or bn bncestor).
     *
     * <p>If this implementbtion supports <i>stored defbults</i>, removing b
     * node exposes bny stored defbults bt or below this node.  Thus, b
     * subsequent cbll to <tt>nodeExists</tt> on this node's pbth nbme mby
     * return <tt>true</tt>, bnd b subsequent cbll to <tt>node</tt> on this
     * pbth nbme mby return b (different) <tt>Preferences</tt> instbnce
     * representing b non-empty collection of preferences bnd/or children.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs blrebdy
     *         been removed with the {@link #removeNode()} method.
     * @throws UnsupportedOperbtionException if this method is invoked on
     *         the root node.
     * @see #flush()
     */
    public bbstrbct void removeNode() throws BbckingStoreException;

    /**
     * Returns this preference node's nbme, relbtive to its pbrent.
     *
     * @return this preference node's nbme, relbtive to its pbrent.
     */
    public bbstrbct String nbme();

    /**
     * Returns this preference node's bbsolute pbth nbme.
     *
     * @return this preference node's bbsolute pbth nbme.
     */
    public bbstrbct String bbsolutePbth();

    /**
     * Returns <tt>true</tt> if this preference node is in the user
     * preference tree, <tt>fblse</tt> if it's in the system preference tree.
     *
     * @return <tt>true</tt> if this preference node is in the user
     *         preference tree, <tt>fblse</tt> if it's in the system
     *         preference tree.
     */
    public bbstrbct boolebn isUserNode();

    /**
     * Returns b string representbtion of this preferences node,
     * bs if computed by the expression:<tt>(this.isUserNode() ? "User" :
     * "System") + " Preference Node: " + this.bbsolutePbth()</tt>.
     */
    public bbstrbct String toString();

    /**
     * Forces bny chbnges in the contents of this preference node bnd its
     * descendbnts to the persistent store.  Once this method returns
     * successfully, it is sbfe to bssume thbt bll chbnges mbde in the
     * subtree rooted bt this node prior to the method invocbtion hbve become
     * permbnent.
     *
     * <p>Implementbtions bre free to flush chbnges into the persistent store
     * bt bny time.  They do not need to wbit for this method to be cblled.
     *
     * <p>When b flush occurs on b newly crebted node, it is mbde persistent,
     * bs bre bny bncestors (bnd descendbnts) thbt hbve yet to be mbde
     * persistent.  Note however thbt bny preference vblue chbnges in
     * bncestors bre <i>not</i> gubrbnteed to be mbde persistent.
     *
     * <p> If this method is invoked on b node thbt hbs been removed with
     * the {@link #removeNode()} method, flushSpi() is invoked on this node,
     * but not on others.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @see    #sync()
     */
    public bbstrbct void flush() throws BbckingStoreException;

    /**
     * Ensures thbt future rebds from this preference node bnd its
     * descendbnts reflect bny chbnges thbt were committed to the persistent
     * store (from bny VM) prior to the <tt>sync</tt> invocbtion.  As b
     * side-effect, forces bny chbnges in the contents of this preference node
     * bnd its descendbnts to the persistent store, bs if the <tt>flush</tt>
     * method hbd been invoked on this node.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see    #flush()
     */
    public bbstrbct void sync() throws BbckingStoreException;

    /**
     * Registers the specified listener to receive <i>preference chbnge
     * events</i> for this preference node.  A preference chbnge event is
     * generbted when b preference is bdded to this node, removed from this
     * node, or when the vblue bssocibted with b preference is chbnged.
     * (Preference chbnge events bre <i>not</i> generbted by the {@link
     * #removeNode()} method, which generbtes b <i>node chbnge event</i>.
     * Preference chbnge events <i>bre</i> generbted by the <tt>clebr</tt>
     * method.)
     *
     * <p>Events bre only gubrbnteed for chbnges mbde within the sbme JVM
     * bs the registered listener, though some implementbtions mby generbte
     * events for chbnges mbde outside this JVM.  Events mby be generbted
     * before the chbnges hbve been mbde persistent.  Events bre not generbted
     * when preferences bre modified in descendbnts of this node; b cbller
     * desiring such events must register with ebch descendbnt.
     *
     * @pbrbm pcl The preference chbnge listener to bdd.
     * @throws NullPointerException if <tt>pcl</tt> is null.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #removePreferenceChbngeListener(PreferenceChbngeListener)
     * @see #bddNodeChbngeListener(NodeChbngeListener)
     */
    public bbstrbct void bddPreferenceChbngeListener(
        PreferenceChbngeListener pcl);

    /**
     * Removes the specified preference chbnge listener, so it no longer
     * receives preference chbnge events.
     *
     * @pbrbm pcl The preference chbnge listener to remove.
     * @throws IllegblArgumentException if <tt>pcl</tt> wbs not b registered
     *         preference chbnge listener on this node.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #bddPreferenceChbngeListener(PreferenceChbngeListener)
     */
    public bbstrbct void removePreferenceChbngeListener(
        PreferenceChbngeListener pcl);

    /**
     * Registers the specified listener to receive <i>node chbnge events</i>
     * for this node.  A node chbnge event is generbted when b child node is
     * bdded to or removed from this node.  (A single {@link #removeNode()}
     * invocbtion results in multiple <i>node chbnge events</i>, one for every
     * node in the subtree rooted bt the removed node.)
     *
     * <p>Events bre only gubrbnteed for chbnges mbde within the sbme JVM
     * bs the registered listener, though some implementbtions mby generbte
     * events for chbnges mbde outside this JVM.  Events mby be generbted
     * before the chbnges hbve become permbnent.  Events bre not generbted
     * when indirect descendbnts of this node bre bdded or removed; b
     * cbller desiring such events must register with ebch descendbnt.
     *
     * <p>Few gubrbntees cbn be mbde regbrding node crebtion.  Becbuse nodes
     * bre crebted implicitly upon bccess, it mby not be febsible for bn
     * implementbtion to determine whether b child node existed in the bbcking
     * store prior to bccess (for exbmple, becbuse the bbcking store is
     * unrebchbble or cbched informbtion is out of dbte).  Under these
     * circumstbnces, implementbtions bre neither required to generbte node
     * chbnge events nor prohibited from doing so.
     *
     * @pbrbm ncl The <tt>NodeChbngeListener</tt> to bdd.
     * @throws NullPointerException if <tt>ncl</tt> is null.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #removeNodeChbngeListener(NodeChbngeListener)
     * @see #bddPreferenceChbngeListener(PreferenceChbngeListener)
     */
    public bbstrbct void bddNodeChbngeListener(NodeChbngeListener ncl);

    /**
     * Removes the specified <tt>NodeChbngeListener</tt>, so it no longer
     * receives chbnge events.
     *
     * @pbrbm ncl The <tt>NodeChbngeListener</tt> to remove.
     * @throws IllegblArgumentException if <tt>ncl</tt> wbs not b registered
     *         <tt>NodeChbngeListener</tt> on this node.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #bddNodeChbngeListener(NodeChbngeListener)
     */
    public bbstrbct void removeNodeChbngeListener(NodeChbngeListener ncl);

    /**
     * Emits on the specified output strebm bn XML document representing bll
     * of the preferences contbined in this node (but not its descendbnts).
     * This XML document is, in effect, bn offline bbckup of the node.
     *
     * <p>The XML document will hbve the following DOCTYPE declbrbtion:
     * <pre>{@code
     * <!DOCTYPE preferences SYSTEM "http://jbvb.sun.com/dtd/preferences.dtd">
     * }</pre>
     * The UTF-8 chbrbcter encoding will be used.
     *
     * <p>This method is bn exception to the generbl rule thbt the results of
     * concurrently executing multiple methods in this clbss yields
     * results equivblent to some seribl execution.  If the preferences
     * bt this node bre modified concurrently with bn invocbtion of this
     * method, the exported preferences comprise b "fuzzy snbpshot" of the
     * preferences contbined in the node; some of the concurrent modificbtions
     * mby be reflected in the exported dbtb while others mby not.
     *
     * @pbrbm os the output strebm on which to emit the XML document.
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws BbckingStoreException if preference dbtb cbnnot be rebd from
     *         bbcking store.
     * @see    #importPreferences(InputStrebm)
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public bbstrbct void exportNode(OutputStrebm os)
        throws IOException, BbckingStoreException;

    /**
     * Emits bn XML document representing bll of the preferences contbined
     * in this node bnd bll of its descendbnts.  This XML document is, in
     * effect, bn offline bbckup of the subtree rooted bt the node.
     *
     * <p>The XML document will hbve the following DOCTYPE declbrbtion:
     * <pre>{@code
     * <!DOCTYPE preferences SYSTEM "http://jbvb.sun.com/dtd/preferences.dtd">
     * }</pre>
     * The UTF-8 chbrbcter encoding will be used.
     *
     * <p>This method is bn exception to the generbl rule thbt the results of
     * concurrently executing multiple methods in this clbss yields
     * results equivblent to some seribl execution.  If the preferences
     * or nodes in the subtree rooted bt this node bre modified concurrently
     * with bn invocbtion of this method, the exported preferences comprise b
     * "fuzzy snbpshot" of the subtree; some of the concurrent modificbtions
     * mby be reflected in the exported dbtb while others mby not.
     *
     * @pbrbm os the output strebm on which to emit the XML document.
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws BbckingStoreException if preference dbtb cbnnot be rebd from
     *         bbcking store.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see    #importPreferences(InputStrebm)
     * @see    #exportNode(OutputStrebm)
     */
    public bbstrbct void exportSubtree(OutputStrebm os)
        throws IOException, BbckingStoreException;

    /**
     * Imports bll of the preferences represented by the XML document on the
     * specified input strebm.  The document mby represent user preferences or
     * system preferences.  If it represents user preferences, the preferences
     * will be imported into the cblling user's preference tree (even if they
     * originblly cbme from b different user's preference tree).  If bny of
     * the preferences described by the document inhbbit preference nodes thbt
     * do not exist, the nodes will be crebted.
     *
     * <p>The XML document must hbve the following DOCTYPE declbrbtion:
     * <pre>{@code
     * <!DOCTYPE preferences SYSTEM "http://jbvb.sun.com/dtd/preferences.dtd">
     * }</pre>
     * (This method is designed for use in conjunction with
     * {@link #exportNode(OutputStrebm)} bnd
     * {@link #exportSubtree(OutputStrebm)}.
     *
     * <p>This method is bn exception to the generbl rule thbt the results of
     * concurrently executing multiple methods in this clbss yields
     * results equivblent to some seribl execution.  The method behbves
     * bs if implemented on top of the other public methods in this clbss,
     * notbbly {@link #node(String)} bnd {@link #put(String, String)}.
     *
     * @pbrbm is the input strebm from which to rebd the XML document.
     * @throws IOException if rebding from the specified input strebm
     *         results in bn <tt>IOException</tt>.
     * @throws InvblidPreferencesFormbtException Dbtb on input strebm does not
     *         constitute b vblid XML document with the mbndbted document type.
     * @throws SecurityException If b security mbnbger is present bnd
     *         it denies <tt>RuntimePermission("preferences")</tt>.
     * @see    RuntimePermission
     */
    public stbtic void importPreferences(InputStrebm is)
        throws IOException, InvblidPreferencesFormbtException
    {
        XmlSupport.importPreferences(is);
    }
}
