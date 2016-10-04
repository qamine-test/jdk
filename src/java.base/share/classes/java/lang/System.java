/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.lbng;

import jbvb.io.*;
import jbvb.lbng.reflect.Executbble;
import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.security.AccessControlContext;
import jbvb.util.Properties;
import jbvb.util.PropertyPermission;
import jbvb.util.StringTokenizer;
import jbvb.util.Mbp;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.AllPermission;
import jbvb.nio.chbnnels.Chbnnel;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import sun.nio.ch.Interruptible;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.security.util.SecurityConstbnts;
import sun.reflect.bnnotbtion.AnnotbtionType;

/**
 * The <code>System</code> clbss contbins severbl useful clbss fields
 * bnd methods. It cbnnot be instbntibted.
 *
 * <p>Among the fbcilities provided by the <code>System</code> clbss
 * bre stbndbrd input, stbndbrd output, bnd error output strebms;
 * bccess to externblly defined properties bnd environment
 * vbribbles; b mebns of lobding files bnd librbries; bnd b utility
 * method for quickly copying b portion of bn brrby.
 *
 * @buthor  unbscribed
 * @since   1.0
 */
public finbl clbss System {

    /* register the nbtives vib the stbtic initiblizer.
     *
     * VM will invoke the initiblizeSystemClbss method to complete
     * the initiblizbtion for this clbss sepbrbted from clinit.
     * Note thbt to use properties set by the VM, see the constrbints
     * described in the initiblizeSystemClbss method.
     */
    privbte stbtic nbtive void registerNbtives();
    stbtic {
        registerNbtives();
    }

    /** Don't let bnyone instbntibte this clbss */
    privbte System() {
    }

    /**
     * The "stbndbrd" input strebm. This strebm is blrebdy
     * open bnd rebdy to supply input dbtb. Typicblly this strebm
     * corresponds to keybobrd input or bnother input source specified by
     * the host environment or user.
     */
    public finbl stbtic InputStrebm in = null;

    /**
     * The "stbndbrd" output strebm. This strebm is blrebdy
     * open bnd rebdy to bccept output dbtb. Typicblly this strebm
     * corresponds to displby output or bnother output destinbtion
     * specified by the host environment or user.
     * <p>
     * For simple stbnd-blone Jbvb bpplicbtions, b typicbl wby to write
     * b line of output dbtb is:
     * <blockquote><pre>
     *     System.out.println(dbtb)
     * </pre></blockquote>
     * <p>
     * See the <code>println</code> methods in clbss <code>PrintStrebm</code>.
     *
     * @see     jbvb.io.PrintStrebm#println()
     * @see     jbvb.io.PrintStrebm#println(boolebn)
     * @see     jbvb.io.PrintStrebm#println(chbr)
     * @see     jbvb.io.PrintStrebm#println(chbr[])
     * @see     jbvb.io.PrintStrebm#println(double)
     * @see     jbvb.io.PrintStrebm#println(flobt)
     * @see     jbvb.io.PrintStrebm#println(int)
     * @see     jbvb.io.PrintStrebm#println(long)
     * @see     jbvb.io.PrintStrebm#println(jbvb.lbng.Object)
     * @see     jbvb.io.PrintStrebm#println(jbvb.lbng.String)
     */
    public finbl stbtic PrintStrebm out = null;

    /**
     * The "stbndbrd" error output strebm. This strebm is blrebdy
     * open bnd rebdy to bccept output dbtb.
     * <p>
     * Typicblly this strebm corresponds to displby output or bnother
     * output destinbtion specified by the host environment or user. By
     * convention, this output strebm is used to displby error messbges
     * or other informbtion thbt should come to the immedibte bttention
     * of b user even if the principbl output strebm, the vblue of the
     * vbribble <code>out</code>, hbs been redirected to b file or other
     * destinbtion thbt is typicblly not continuously monitored.
     */
    public finbl stbtic PrintStrebm err = null;

    /* The security mbnbger for the system.
     */
    privbte stbtic volbtile SecurityMbnbger security = null;

    /**
     * Rebssigns the "stbndbrd" input strebm.
     *
     * <p>First, if there is b security mbnbger, its <code>checkPermission</code>
     * method is cblled with b <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to rebssign the "stbndbrd" input strebm.
     *
     * @pbrbm in the new stbndbrd input strebm.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow
     *        rebssigning of the stbndbrd input strebm.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     *
     * @since   1.1
     */
    public stbtic void setIn(InputStrebm in) {
        checkIO();
        setIn0(in);
    }

    /**
     * Rebssigns the "stbndbrd" output strebm.
     *
     * <p>First, if there is b security mbnbger, its <code>checkPermission</code>
     * method is cblled with b <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to rebssign the "stbndbrd" output strebm.
     *
     * @pbrbm out the new stbndbrd output strebm
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow
     *        rebssigning of the stbndbrd output strebm.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     *
     * @since   1.1
     */
    public stbtic void setOut(PrintStrebm out) {
        checkIO();
        setOut0(out);
    }

    /**
     * Rebssigns the "stbndbrd" error output strebm.
     *
     * <p>First, if there is b security mbnbger, its <code>checkPermission</code>
     * method is cblled with b <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to rebssign the "stbndbrd" error output strebm.
     *
     * @pbrbm err the new stbndbrd error output strebm.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        <code>checkPermission</code> method doesn't bllow
     *        rebssigning of the stbndbrd error output strebm.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     *
     * @since   1.1
     */
    public stbtic void setErr(PrintStrebm err) {
        checkIO();
        setErr0(err);
    }

    privbte stbtic volbtile Console cons = null;
    /**
     * Returns the unique {@link jbvb.io.Console Console} object bssocibted
     * with the current Jbvb virtubl mbchine, if bny.
     *
     * @return  The system console, if bny, otherwise <tt>null</tt>.
     *
     * @since   1.6
     */
     public stbtic Console console() {
         if (cons == null) {
             synchronized (System.clbss) {
                 cons = sun.misc.ShbredSecrets.getJbvbIOAccess().console();
             }
         }
         return cons;
     }

    /**
     * Returns the chbnnel inherited from the entity thbt crebted this
     * Jbvb virtubl mbchine.
     *
     * <p> This method returns the chbnnel obtbined by invoking the
     * {@link jbvb.nio.chbnnels.spi.SelectorProvider#inheritedChbnnel
     * inheritedChbnnel} method of the system-wide defbult
     * {@link jbvb.nio.chbnnels.spi.SelectorProvider} object. </p>
     *
     * <p> In bddition to the network-oriented chbnnels described in
     * {@link jbvb.nio.chbnnels.spi.SelectorProvider#inheritedChbnnel
     * inheritedChbnnel}, this method mby return other kinds of
     * chbnnels in the future.
     *
     * @return  The inherited chbnnel, if bny, otherwise <tt>null</tt>.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd it does not
     *          permit bccess to the chbnnel.
     *
     * @since 1.5
     */
    public stbtic Chbnnel inheritedChbnnel() throws IOException {
        return SelectorProvider.provider().inheritedChbnnel();
    }

    privbte stbtic void checkIO() {
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("setIO"));
        }
    }

    privbte stbtic nbtive void setIn0(InputStrebm in);
    privbte stbtic nbtive void setOut0(PrintStrebm out);
    privbte stbtic nbtive void setErr0(PrintStrebm err);

    /**
     * Sets the System security.
     *
     * <p> If there is b security mbnbger blrebdy instblled, this method first
     * cblls the security mbnbger's <code>checkPermission</code> method
     * with b <code>RuntimePermission("setSecurityMbnbger")</code>
     * permission to ensure it's ok to replbce the existing
     * security mbnbger.
     * This mby result in throwing b <code>SecurityException</code>.
     *
     * <p> Otherwise, the brgument is estbblished bs the current
     * security mbnbger. If the brgument is <code>null</code> bnd no
     * security mbnbger hbs been estbblished, then no bction is tbken bnd
     * the method simply returns.
     *
     * @pbrbm      s   the security mbnbger.
     * @exception  SecurityException  if the security mbnbger hbs blrebdy
     *             been set bnd its <code>checkPermission</code> method
     *             doesn't bllow it to be replbced.
     * @see #getSecurityMbnbger
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.lbng.RuntimePermission
     */
    public stbtic
    void setSecurityMbnbger(finbl SecurityMbnbger s) {
        try {
            s.checkPbckbgeAccess("jbvb.lbng");
        } cbtch (Exception e) {
            // no-op
        }
        setSecurityMbnbger0(s);
    }

    privbte stbtic synchronized
    void setSecurityMbnbger0(finbl SecurityMbnbger s) {
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            // bsk the currently instblled security mbnbger if we
            // cbn replbce it.
            sm.checkPermission(new RuntimePermission
                                     ("setSecurityMbnbger"));
        }

        if ((s != null) && (s.getClbss().getClbssLobder() != null)) {
            // New security mbnbger clbss is not on bootstrbp clbsspbth.
            // Cbuse policy to get initiblized before we instbll the new
            // security mbnbger, in order to prevent infinite loops when
            // trying to initiblize the policy (which usublly involves
            // bccessing some security bnd/or system properties, which in turn
            // cblls the instblled security mbnbger's checkPermission method
            // which will loop infinitely if there is b non-system clbss
            // (in this cbse: the new security mbnbger clbss) on the stbck).
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    s.getClbss().getProtectionDombin().implies
                        (SecurityConstbnts.ALL_PERMISSION);
                    return null;
                }
            });
        }

        security = s;
    }

    /**
     * Gets the system security interfbce.
     *
     * @return  if b security mbnbger hbs blrebdy been estbblished for the
     *          current bpplicbtion, then thbt security mbnbger is returned;
     *          otherwise, <code>null</code> is returned.
     * @see     #setSecurityMbnbger
     */
    public stbtic SecurityMbnbger getSecurityMbnbger() {
        return security;
    }

    /**
     * Returns the current time in milliseconds.  Note thbt
     * while the unit of time of the return vblue is b millisecond,
     * the grbnulbrity of the vblue depends on the underlying
     * operbting system bnd mby be lbrger.  For exbmple, mbny
     * operbting systems mebsure time in units of tens of
     * milliseconds.
     *
     * <p> See the description of the clbss <code>Dbte</code> for
     * b discussion of slight discrepbncies thbt mby brise between
     * "computer time" bnd coordinbted universbl time (UTC).
     *
     * @return  the difference, mebsured in milliseconds, between
     *          the current time bnd midnight, Jbnubry 1, 1970 UTC.
     * @see     jbvb.util.Dbte
     */
    public stbtic nbtive long currentTimeMillis();

    /**
     * Returns the current vblue of the running Jbvb Virtubl Mbchine's
     * high-resolution time source, in nbnoseconds.
     *
     * <p>This method cbn only be used to mebsure elbpsed time bnd is
     * not relbted to bny other notion of system or wbll-clock time.
     * The vblue returned represents nbnoseconds since some fixed but
     * brbitrbry <i>origin</i> time (perhbps in the future, so vblues
     * mby be negbtive).  The sbme origin is used by bll invocbtions of
     * this method in bn instbnce of b Jbvb virtubl mbchine; other
     * virtubl mbchine instbnces bre likely to use b different origin.
     *
     * <p>This method provides nbnosecond precision, but not necessbrily
     * nbnosecond resolution (thbt is, how frequently the vblue chbnges)
     * - no gubrbntees bre mbde except thbt the resolution is bt lebst bs
     * good bs thbt of {@link #currentTimeMillis()}.
     *
     * <p>Differences in successive cblls thbt spbn grebter thbn
     * bpproximbtely 292 yebrs (2<sup>63</sup> nbnoseconds) will not
     * correctly compute elbpsed time due to numericbl overflow.
     *
     * <p>The vblues returned by this method become mebningful only when
     * the difference between two such vblues, obtbined within the sbme
     * instbnce of b Jbvb virtubl mbchine, is computed.
     *
     * <p> For exbmple, to mebsure how long some code tbkes to execute:
     *  <pre> {@code
     * long stbrtTime = System.nbnoTime();
     * // ... the code being mebsured ...
     * long estimbtedTime = System.nbnoTime() - stbrtTime;}</pre>
     *
     * <p>To compbre two nbnoTime vblues
     *  <pre> {@code
     * long t0 = System.nbnoTime();
     * ...
     * long t1 = System.nbnoTime();}</pre>
     *
     * one should use {@code t1 - t0 < 0}, not {@code t1 < t0},
     * becbuse of the possibility of numericbl overflow.
     *
     * @return the current vblue of the running Jbvb Virtubl Mbchine's
     *         high-resolution time source, in nbnoseconds
     * @since 1.5
     */
    public stbtic nbtive long nbnoTime();

    /**
     * Copies bn brrby from the specified source brrby, beginning bt the
     * specified position, to the specified position of the destinbtion brrby.
     * A subsequence of brrby components bre copied from the source
     * brrby referenced by <code>src</code> to the destinbtion brrby
     * referenced by <code>dest</code>. The number of components copied is
     * equbl to the <code>length</code> brgument. The components bt
     * positions <code>srcPos</code> through
     * <code>srcPos+length-1</code> in the source brrby bre copied into
     * positions <code>destPos</code> through
     * <code>destPos+length-1</code>, respectively, of the destinbtion
     * brrby.
     * <p>
     * If the <code>src</code> bnd <code>dest</code> brguments refer to the
     * sbme brrby object, then the copying is performed bs if the
     * components bt positions <code>srcPos</code> through
     * <code>srcPos+length-1</code> were first copied to b temporbry
     * brrby with <code>length</code> components bnd then the contents of
     * the temporbry brrby were copied into positions
     * <code>destPos</code> through <code>destPos+length-1</code> of the
     * destinbtion brrby.
     * <p>
     * If <code>dest</code> is <code>null</code>, then b
     * <code>NullPointerException</code> is thrown.
     * <p>
     * If <code>src</code> is <code>null</code>, then b
     * <code>NullPointerException</code> is thrown bnd the destinbtion
     * brrby is not modified.
     * <p>
     * Otherwise, if bny of the following is true, bn
     * <code>ArrbyStoreException</code> is thrown bnd the destinbtion is
     * not modified:
     * <ul>
     * <li>The <code>src</code> brgument refers to bn object thbt is not bn
     *     brrby.
     * <li>The <code>dest</code> brgument refers to bn object thbt is not bn
     *     brrby.
     * <li>The <code>src</code> brgument bnd <code>dest</code> brgument refer
     *     to brrbys whose component types bre different primitive types.
     * <li>The <code>src</code> brgument refers to bn brrby with b primitive
     *    component type bnd the <code>dest</code> brgument refers to bn brrby
     *     with b reference component type.
     * <li>The <code>src</code> brgument refers to bn brrby with b reference
     *    component type bnd the <code>dest</code> brgument refers to bn brrby
     *     with b primitive component type.
     * </ul>
     * <p>
     * Otherwise, if bny of the following is true, bn
     * <code>IndexOutOfBoundsException</code> is
     * thrown bnd the destinbtion is not modified:
     * <ul>
     * <li>The <code>srcPos</code> brgument is negbtive.
     * <li>The <code>destPos</code> brgument is negbtive.
     * <li>The <code>length</code> brgument is negbtive.
     * <li><code>srcPos+length</code> is grebter thbn
     *     <code>src.length</code>, the length of the source brrby.
     * <li><code>destPos+length</code> is grebter thbn
     *     <code>dest.length</code>, the length of the destinbtion brrby.
     * </ul>
     * <p>
     * Otherwise, if bny bctubl component of the source brrby from
     * position <code>srcPos</code> through
     * <code>srcPos+length-1</code> cbnnot be converted to the component
     * type of the destinbtion brrby by bssignment conversion, bn
     * <code>ArrbyStoreException</code> is thrown. In this cbse, let
     * <b><i>k</i></b> be the smbllest nonnegbtive integer less thbn
     * length such thbt <code>src[srcPos+</code><i>k</i><code>]</code>
     * cbnnot be converted to the component type of the destinbtion
     * brrby; when the exception is thrown, source brrby components from
     * positions <code>srcPos</code> through
     * <code>srcPos+</code><i>k</i><code>-1</code>
     * will blrebdy hbve been copied to destinbtion brrby positions
     * <code>destPos</code> through
     * <code>destPos+</code><i>k</I><code>-1</code> bnd no other
     * positions of the destinbtion brrby will hbve been modified.
     * (Becbuse of the restrictions blrebdy itemized, this
     * pbrbgrbph effectively bpplies only to the situbtion where both
     * brrbys hbve component types thbt bre reference types.)
     *
     * @pbrbm      src      the source brrby.
     * @pbrbm      srcPos   stbrting position in the source brrby.
     * @pbrbm      dest     the destinbtion brrby.
     * @pbrbm      destPos  stbrting position in the destinbtion dbtb.
     * @pbrbm      length   the number of brrby elements to be copied.
     * @exception  IndexOutOfBoundsException  if copying would cbuse
     *               bccess of dbtb outside brrby bounds.
     * @exception  ArrbyStoreException  if bn element in the <code>src</code>
     *               brrby could not be stored into the <code>dest</code> brrby
     *               becbuse of b type mismbtch.
     * @exception  NullPointerException if either <code>src</code> or
     *               <code>dest</code> is <code>null</code>.
     */
    public stbtic nbtive void brrbycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);

    /**
     * Returns the sbme hbsh code for the given object bs
     * would be returned by the defbult method hbshCode(),
     * whether or not the given object's clbss overrides
     * hbshCode().
     * The hbsh code for the null reference is zero.
     *
     * @pbrbm x object for which the hbshCode is to be cblculbted
     * @return  the hbshCode
     * @since   1.1
     */
    public stbtic nbtive int identityHbshCode(Object x);

    /**
     * System properties. The following properties bre gubrbnteed to be defined:
     * <dl>
     * <dt>jbvb.version         <dd>Jbvb version number
     * <dt>jbvb.vendor          <dd>Jbvb vendor specific string
     * <dt>jbvb.vendor.url      <dd>Jbvb vendor URL
     * <dt>jbvb.home            <dd>Jbvb instbllbtion directory
     * <dt>jbvb.clbss.version   <dd>Jbvb clbss version number
     * <dt>jbvb.clbss.pbth      <dd>Jbvb clbsspbth
     * <dt>os.nbme              <dd>Operbting System Nbme
     * <dt>os.brch              <dd>Operbting System Architecture
     * <dt>os.version           <dd>Operbting System Version
     * <dt>file.sepbrbtor       <dd>File sepbrbtor ("/" on Unix)
     * <dt>pbth.sepbrbtor       <dd>Pbth sepbrbtor (":" on Unix)
     * <dt>line.sepbrbtor       <dd>Line sepbrbtor ("\n" on Unix)
     * <dt>user.nbme            <dd>User bccount nbme
     * <dt>user.home            <dd>User home directory
     * <dt>user.dir             <dd>User's current working directory
     * </dl>
     */

    privbte stbtic Properties props;
    privbte stbtic nbtive Properties initProperties(Properties props);

    /**
     * Determines the current system properties.
     * <p>
     * First, if there is b security mbnbger, its
     * <code>checkPropertiesAccess</code> method is cblled with no
     * brguments. This mby result in b security exception.
     * <p>
     * The current set of system properties for use by the
     * {@link #getProperty(String)} method is returned bs b
     * <code>Properties</code> object. If there is no current set of
     * system properties, b set of system properties is first crebted bnd
     * initiblized. This set of system properties blwbys includes vblues
     * for the following keys:
     * <tbble summbry="Shows property keys bnd bssocibted vblues">
     * <tr><th>Key</th>
     *     <th>Description of Associbted Vblue</th></tr>
     * <tr><td><code>jbvb.version</code></td>
     *     <td>Jbvb Runtime Environment version</td></tr>
     * <tr><td><code>jbvb.vendor</code></td>
     *     <td>Jbvb Runtime Environment vendor</td></tr>
     * <tr><td><code>jbvb.vendor.url</code></td>
     *     <td>Jbvb vendor URL</td></tr>
     * <tr><td><code>jbvb.home</code></td>
     *     <td>Jbvb instbllbtion directory</td></tr>
     * <tr><td><code>jbvb.vm.specificbtion.version</code></td>
     *     <td>Jbvb Virtubl Mbchine specificbtion version</td></tr>
     * <tr><td><code>jbvb.vm.specificbtion.vendor</code></td>
     *     <td>Jbvb Virtubl Mbchine specificbtion vendor</td></tr>
     * <tr><td><code>jbvb.vm.specificbtion.nbme</code></td>
     *     <td>Jbvb Virtubl Mbchine specificbtion nbme</td></tr>
     * <tr><td><code>jbvb.vm.version</code></td>
     *     <td>Jbvb Virtubl Mbchine implementbtion version</td></tr>
     * <tr><td><code>jbvb.vm.vendor</code></td>
     *     <td>Jbvb Virtubl Mbchine implementbtion vendor</td></tr>
     * <tr><td><code>jbvb.vm.nbme</code></td>
     *     <td>Jbvb Virtubl Mbchine implementbtion nbme</td></tr>
     * <tr><td><code>jbvb.specificbtion.version</code></td>
     *     <td>Jbvb Runtime Environment specificbtion  version</td></tr>
     * <tr><td><code>jbvb.specificbtion.vendor</code></td>
     *     <td>Jbvb Runtime Environment specificbtion  vendor</td></tr>
     * <tr><td><code>jbvb.specificbtion.nbme</code></td>
     *     <td>Jbvb Runtime Environment specificbtion  nbme</td></tr>
     * <tr><td><code>jbvb.clbss.version</code></td>
     *     <td>Jbvb clbss formbt version number</td></tr>
     * <tr><td><code>jbvb.clbss.pbth</code></td>
     *     <td>Jbvb clbss pbth</td></tr>
     * <tr><td><code>jbvb.librbry.pbth</code></td>
     *     <td>List of pbths to sebrch when lobding librbries</td></tr>
     * <tr><td><code>jbvb.io.tmpdir</code></td>
     *     <td>Defbult temp file pbth</td></tr>
     * <tr><td><code>jbvb.compiler</code></td>
     *     <td>Nbme of JIT compiler to use</td></tr>
     * <tr><td><code>jbvb.ext.dirs</code></td>
     *     <td>Pbth of extension directory or directories</td></tr>
     * <tr><td><code>os.nbme</code></td>
     *     <td>Operbting system nbme</td></tr>
     * <tr><td><code>os.brch</code></td>
     *     <td>Operbting system brchitecture</td></tr>
     * <tr><td><code>os.version</code></td>
     *     <td>Operbting system version</td></tr>
     * <tr><td><code>file.sepbrbtor</code></td>
     *     <td>File sepbrbtor ("/" on UNIX)</td></tr>
     * <tr><td><code>pbth.sepbrbtor</code></td>
     *     <td>Pbth sepbrbtor (":" on UNIX)</td></tr>
     * <tr><td><code>line.sepbrbtor</code></td>
     *     <td>Line sepbrbtor ("\n" on UNIX)</td></tr>
     * <tr><td><code>user.nbme</code></td>
     *     <td>User's bccount nbme</td></tr>
     * <tr><td><code>user.home</code></td>
     *     <td>User's home directory</td></tr>
     * <tr><td><code>user.dir</code></td>
     *     <td>User's current working directory</td></tr>
     * </tbble>
     * <p>
     * Multiple pbths in b system property vblue bre sepbrbted by the pbth
     * sepbrbtor chbrbcter of the plbtform.
     * <p>
     * Note thbt even if the security mbnbger does not permit the
     * <code>getProperties</code> operbtion, it mby choose to permit the
     * {@link #getProperty(String)} operbtion.
     *
     * @return     the system properties
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *              to the system properties.
     * @see        #setProperties
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkPropertiesAccess()
     * @see        jbvb.util.Properties
     */
    public stbtic Properties getProperties() {
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }

        return props;
    }

    /**
     * Returns the system-dependent line sepbrbtor string.  It blwbys
     * returns the sbme vblue - the initibl vblue of the {@linkplbin
     * #getProperty(String) system property} {@code line.sepbrbtor}.
     *
     * <p>On UNIX systems, it returns {@code "\n"}; on Microsoft
     * Windows systems it returns {@code "\r\n"}.
     *
     * @return the system-dependent line sepbrbtor string
     * @since 1.7
     */
    public stbtic String lineSepbrbtor() {
        return lineSepbrbtor;
    }

    privbte stbtic String lineSepbrbtor;

    /**
     * Sets the system properties to the <code>Properties</code>
     * brgument.
     * <p>
     * First, if there is b security mbnbger, its
     * <code>checkPropertiesAccess</code> method is cblled with no
     * brguments. This mby result in b security exception.
     * <p>
     * The brgument becomes the current set of system properties for use
     * by the {@link #getProperty(String)} method. If the brgument is
     * <code>null</code>, then the current set of system properties is
     * forgotten.
     *
     * @pbrbm      props   the new system properties.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *              to the system properties.
     * @see        #getProperties
     * @see        jbvb.util.Properties
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkPropertiesAccess()
     */
    public stbtic void setProperties(Properties props) {
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertiesAccess();
        }
        if (props == null) {
            props = new Properties();
            initProperties(props);
        }
        System.props = props;
    }

    /**
     * Gets the system property indicbted by the specified key.
     * <p>
     * First, if there is b security mbnbger, its
     * <code>checkPropertyAccess</code> method is cblled with the key bs
     * its brgument. This mby result in b SecurityException.
     * <p>
     * If there is no current set of system properties, b set of system
     * properties is first crebted bnd initiblized in the sbme mbnner bs
     * for the <code>getProperties</code> method.
     *
     * @pbrbm      key   the nbme of the system property.
     * @return     the string vblue of the system property,
     *             or <code>null</code> if there is no property with thbt key.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertyAccess</code> method doesn't bllow
     *              bccess to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegblArgumentException if <code>key</code> is empty.
     * @see        #setProperty
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see        jbvb.lbng.System#getProperties()
     */
    public stbtic String getProperty(String key) {
        checkKey(key);
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertyAccess(key);
        }

        return props.getProperty(key);
    }

    /**
     * Gets the system property indicbted by the specified key.
     * <p>
     * First, if there is b security mbnbger, its
     * <code>checkPropertyAccess</code> method is cblled with the
     * <code>key</code> bs its brgument.
     * <p>
     * If there is no current set of system properties, b set of system
     * properties is first crebted bnd initiblized in the sbme mbnner bs
     * for the <code>getProperties</code> method.
     *
     * @pbrbm      key   the nbme of the system property.
     * @pbrbm      def   b defbult vblue.
     * @return     the string vblue of the system property,
     *             or the defbult vblue if there is no property with thbt key.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertyAccess</code> method doesn't bllow
     *             bccess to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegblArgumentException if <code>key</code> is empty.
     * @see        #setProperty
     * @see        jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see        jbvb.lbng.System#getProperties()
     */
    public stbtic String getProperty(String key, String def) {
        checkKey(key);
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertyAccess(key);
        }

        return props.getProperty(key, def);
    }

    /**
     * Sets the system property indicbted by the specified key.
     * <p>
     * First, if b security mbnbger exists, its
     * <code>SecurityMbnbger.checkPermission</code> method
     * is cblled with b <code>PropertyPermission(key, "write")</code>
     * permission. This mby result in b SecurityException being thrown.
     * If no exception is thrown, the specified property is set to the given
     * vblue.
     *
     * @pbrbm      key   the nbme of the system property.
     * @pbrbm      vblue the vblue of the system property.
     * @return     the previous vblue of the system property,
     *             or <code>null</code> if it did not hbve one.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPermission</code> method doesn't bllow
     *             setting of the specified property.
     * @exception  NullPointerException if <code>key</code> or
     *             <code>vblue</code> is <code>null</code>.
     * @exception  IllegblArgumentException if <code>key</code> is empty.
     * @see        #getProperty
     * @see        jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see        jbvb.lbng.System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     * @see        jbvb.util.PropertyPermission
     * @see        SecurityMbnbger#checkPermission
     * @since      1.2
     */
    public stbtic String setProperty(String key, String vblue) {
        checkKey(key);
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new PropertyPermission(key,
                SecurityConstbnts.PROPERTY_WRITE_ACTION));
        }

        return (String) props.setProperty(key, vblue);
    }

    /**
     * Removes the system property indicbted by the specified key.
     * <p>
     * First, if b security mbnbger exists, its
     * <code>SecurityMbnbger.checkPermission</code> method
     * is cblled with b <code>PropertyPermission(key, "write")</code>
     * permission. This mby result in b SecurityException being thrown.
     * If no exception is thrown, the specified property is removed.
     *
     * @pbrbm      key   the nbme of the system property to be removed.
     * @return     the previous string vblue of the system property,
     *             or <code>null</code> if there wbs no property with thbt key.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkPropertyAccess</code> method doesn't bllow
     *              bccess to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegblArgumentException if <code>key</code> is empty.
     * @see        #getProperty
     * @see        #setProperty
     * @see        jbvb.util.Properties
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.SecurityMbnbger#checkPropertiesAccess()
     * @since 1.5
     */
    public stbtic String clebrProperty(String key) {
        checkKey(key);
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new PropertyPermission(key, "write"));
        }

        return (String) props.remove(key);
    }

    privbte stbtic void checkKey(String key) {
        if (key == null) {
            throw new NullPointerException("key cbn't be null");
        }
        if (key.equbls("")) {
            throw new IllegblArgumentException("key cbn't be empty");
        }
    }

    /**
     * Gets the vblue of the specified environment vbribble. An
     * environment vbribble is b system-dependent externbl nbmed
     * vblue.
     *
     * <p>If b security mbnbger exists, its
     * {@link SecurityMbnbger#checkPermission checkPermission}
     * method is cblled with b
     * <code>{@link RuntimePermission}("getenv."+nbme)</code>
     * permission.  This mby result in b {@link SecurityException}
     * being thrown.  If no exception is thrown the vblue of the
     * vbribble <code>nbme</code> is returned.
     *
     * <p><b nbme="EnvironmentVSSystemProperties"><i>System
     * properties</i> bnd <i>environment vbribbles</i></b> bre both
     * conceptublly mbppings between nbmes bnd vblues.  Both
     * mechbnisms cbn be used to pbss user-defined informbtion to b
     * Jbvb process.  Environment vbribbles hbve b more globbl effect,
     * becbuse they bre visible to bll descendbnts of the process
     * which defines them, not just the immedibte Jbvb subprocess.
     * They cbn hbve subtly different sembntics, such bs cbse
     * insensitivity, on different operbting systems.  For these
     * rebsons, environment vbribbles bre more likely to hbve
     * unintended side effects.  It is best to use system properties
     * where possible.  Environment vbribbles should be used when b
     * globbl effect is desired, or when bn externbl system interfbce
     * requires bn environment vbribble (such bs <code>PATH</code>).
     *
     * <p>On UNIX systems the blphbbetic cbse of <code>nbme</code> is
     * typicblly significbnt, while on Microsoft Windows systems it is
     * typicblly not.  For exbmple, the expression
     * <code>System.getenv("FOO").equbls(System.getenv("foo"))</code>
     * is likely to be true on Microsoft Windows.
     *
     * @pbrbm  nbme the nbme of the environment vbribble
     * @return the string vblue of the vbribble, or <code>null</code>
     *         if the vbribble is not defined in the system environment
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>
     * @throws SecurityException
     *         if b security mbnbger exists bnd its
     *         {@link SecurityMbnbger#checkPermission checkPermission}
     *         method doesn't bllow bccess to the environment vbribble
     *         <code>nbme</code>
     * @see    #getenv()
     * @see    ProcessBuilder#environment()
     */
    public stbtic String getenv(String nbme) {
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getenv."+nbme));
        }

        return ProcessEnvironment.getenv(nbme);
    }


    /**
     * Returns bn unmodifibble string mbp view of the current system environment.
     * The environment is b system-dependent mbpping from nbmes to
     * vblues which is pbssed from pbrent to child processes.
     *
     * <p>If the system does not support environment vbribbles, bn
     * empty mbp is returned.
     *
     * <p>The returned mbp will never contbin null keys or vblues.
     * Attempting to query the presence of b null key or vblue will
     * throw b {@link NullPointerException}.  Attempting to query
     * the presence of b key or vblue which is not of type
     * {@link String} will throw b {@link ClbssCbstException}.
     *
     * <p>The returned mbp bnd its collection views mby not obey the
     * generbl contrbct of the {@link Object#equbls} bnd
     * {@link Object#hbshCode} methods.
     *
     * <p>The returned mbp is typicblly cbse-sensitive on bll plbtforms.
     *
     * <p>If b security mbnbger exists, its
     * {@link SecurityMbnbger#checkPermission checkPermission}
     * method is cblled with b
     * <code>{@link RuntimePermission}("getenv.*")</code>
     * permission.  This mby result in b {@link SecurityException} being
     * thrown.
     *
     * <p>When pbssing informbtion to b Jbvb subprocess,
     * <b href=#EnvironmentVSSystemProperties>system properties</b>
     * bre generblly preferred over environment vbribbles.
     *
     * @return the environment bs b mbp of vbribble nbmes to vblues
     * @throws SecurityException
     *         if b security mbnbger exists bnd its
     *         {@link SecurityMbnbger#checkPermission checkPermission}
     *         method doesn't bllow bccess to the process environment
     * @see    #getenv(String)
     * @see    ProcessBuilder#environment()
     * @since  1.5
     */
    public stbtic jbvb.util.Mbp<String,String> getenv() {
        SecurityMbnbger sm = getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getenv.*"));
        }

        return ProcessEnvironment.getenv();
    }

    /**
     * Terminbtes the currently running Jbvb Virtubl Mbchine. The
     * brgument serves bs b stbtus code; by convention, b nonzero stbtus
     * code indicbtes bbnormbl terminbtion.
     * <p>
     * This method cblls the <code>exit</code> method in clbss
     * <code>Runtime</code>. This method never returns normblly.
     * <p>
     * The cbll <code>System.exit(n)</code> is effectively equivblent to
     * the cbll:
     * <blockquote><pre>
     * Runtime.getRuntime().exit(n)
     * </pre></blockquote>
     *
     * @pbrbm      stbtus   exit stbtus.
     * @throws  SecurityException
     *        if b security mbnbger exists bnd its <code>checkExit</code>
     *        method doesn't bllow exit with the specified stbtus.
     * @see        jbvb.lbng.Runtime#exit(int)
     */
    public stbtic void exit(int stbtus) {
        Runtime.getRuntime().exit(stbtus);
    }

    /**
     * Runs the gbrbbge collector.
     * <p>
     * Cblling the <code>gc</code> method suggests thbt the Jbvb Virtubl
     * Mbchine expend effort towbrd recycling unused objects in order to
     * mbke the memory they currently occupy bvbilbble for quick reuse.
     * When control returns from the method cbll, the Jbvb Virtubl
     * Mbchine hbs mbde b best effort to reclbim spbce from bll discbrded
     * objects.
     * <p>
     * The cbll <code>System.gc()</code> is effectively equivblent to the
     * cbll:
     * <blockquote><pre>
     * Runtime.getRuntime().gc()
     * </pre></blockquote>
     *
     * @see     jbvb.lbng.Runtime#gc()
     */
    public stbtic void gc() {
        Runtime.getRuntime().gc();
    }

    /**
     * Runs the finblizbtion methods of bny objects pending finblizbtion.
     * <p>
     * Cblling this method suggests thbt the Jbvb Virtubl Mbchine expend
     * effort towbrd running the <code>finblize</code> methods of objects
     * thbt hbve been found to be discbrded but whose <code>finblize</code>
     * methods hbve not yet been run. When control returns from the
     * method cbll, the Jbvb Virtubl Mbchine hbs mbde b best effort to
     * complete bll outstbnding finblizbtions.
     * <p>
     * The cbll <code>System.runFinblizbtion()</code> is effectively
     * equivblent to the cbll:
     * <blockquote><pre>
     * Runtime.getRuntime().runFinblizbtion()
     * </pre></blockquote>
     *
     * @see     jbvb.lbng.Runtime#runFinblizbtion()
     */
    public stbtic void runFinblizbtion() {
        Runtime.getRuntime().runFinblizbtion();
    }

    /**
     * Enbble or disbble finblizbtion on exit; doing so specifies thbt the
     * finblizers of bll objects thbt hbve finblizers thbt hbve not yet been
     * butombticblly invoked bre to be run before the Jbvb runtime exits.
     * By defbult, finblizbtion on exit is disbbled.
     *
     * <p>If there is b security mbnbger,
     * its <code>checkExit</code> method is first cblled
     * with 0 bs its brgument to ensure the exit is bllowed.
     * This could result in b SecurityException.
     *
     * @deprecbted  This method is inherently unsbfe.  It mby result in
     *      finblizers being cblled on live objects while other threbds bre
     *      concurrently mbnipulbting those objects, resulting in errbtic
     *      behbvior or debdlock.
     * @pbrbm vblue indicbting enbbling or disbbling of finblizbtion
     * @throws  SecurityException
     *        if b security mbnbger exists bnd its <code>checkExit</code>
     *        method doesn't bllow the exit.
     *
     * @see     jbvb.lbng.Runtime#exit(int)
     * @see     jbvb.lbng.Runtime#gc()
     * @see     jbvb.lbng.SecurityMbnbger#checkExit(int)
     * @since   1.1
     */
    @Deprecbted
    public stbtic void runFinblizersOnExit(boolebn vblue) {
        Runtime.runFinblizersOnExit(vblue);
    }

    /**
     * Lobds the nbtive librbry specified by the filenbme brgument.  The filenbme
     * brgument must be bn bbsolute pbth nbme.
     *
     * If the filenbme brgument, when stripped of bny plbtform-specific librbry
     * prefix, pbth, bnd file extension, indicbtes b librbry whose nbme is,
     * for exbmple, L, bnd b nbtive librbry cblled L is stbticblly linked
     * with the VM, then the JNI_OnLobd_L function exported by the librbry
     * is invoked rbther thbn bttempting to lobd b dynbmic librbry.
     * A filenbme mbtching the brgument does not hbve to exist in the
     * file system.
     * See the JNI Specificbtion for more detbils.
     *
     * Otherwise, the filenbme brgument is mbpped to b nbtive librbry imbge in
     * bn implementbtion-dependent mbnner.
     *
     * <p>
     * The cbll <code>System.lobd(nbme)</code> is effectively equivblent
     * to the cbll:
     * <blockquote><pre>
     * Runtime.getRuntime().lobd(nbme)
     * </pre></blockquote>
     *
     * @pbrbm      filenbme   the file to lobd.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkLink</code> method doesn't bllow
     *             lobding of the specified dynbmic librbry
     * @exception  UnsbtisfiedLinkError  if either the filenbme is not bn
     *             bbsolute pbth nbme, the nbtive librbry is not stbticblly
     *             linked with the VM, or the librbry cbnnot be mbpped to
     *             b nbtive librbry imbge by the host system.
     * @exception  NullPointerException if <code>filenbme</code> is
     *             <code>null</code>
     * @see        jbvb.lbng.Runtime#lobd(jbvb.lbng.String)
     * @see        jbvb.lbng.SecurityMbnbger#checkLink(jbvb.lbng.String)
     */
    @CbllerSensitive
    public stbtic void lobd(String filenbme) {
        Runtime.getRuntime().lobd0(Reflection.getCbllerClbss(), filenbme);
    }

    /**
     * Lobds the nbtive librbry specified by the <code>libnbme</code>
     * brgument.  The <code>libnbme</code> brgument must not contbin bny plbtform
     * specific prefix, file extension or pbth. If b nbtive librbry
     * cblled <code>libnbme</code> is stbticblly linked with the VM, then the
     * JNI_OnLobd_<code>libnbme</code> function exported by the librbry is invoked.
     * See the JNI Specificbtion for more detbils.
     *
     * Otherwise, the libnbme brgument is lobded from b system librbry
     * locbtion bnd mbpped to b nbtive librbry imbge in bn implementbtion-
     * dependent mbnner.
     * <p>
     * The cbll <code>System.lobdLibrbry(nbme)</code> is effectively
     * equivblent to the cbll
     * <blockquote><pre>
     * Runtime.getRuntime().lobdLibrbry(nbme)
     * </pre></blockquote>
     *
     * @pbrbm      libnbme   the nbme of the librbry.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             <code>checkLink</code> method doesn't bllow
     *             lobding of the specified dynbmic librbry
     * @exception  UnsbtisfiedLinkError if either the libnbme brgument
     *             contbins b file pbth, the nbtive librbry is not stbticblly
     *             linked with the VM,  or the librbry cbnnot be mbpped to b
     *             nbtive librbry imbge by the host system.
     * @exception  NullPointerException if <code>libnbme</code> is
     *             <code>null</code>
     * @see        jbvb.lbng.Runtime#lobdLibrbry(jbvb.lbng.String)
     * @see        jbvb.lbng.SecurityMbnbger#checkLink(jbvb.lbng.String)
     */
    @CbllerSensitive
    public stbtic void lobdLibrbry(String libnbme) {
        Runtime.getRuntime().lobdLibrbry0(Reflection.getCbllerClbss(), libnbme);
    }

    /**
     * Mbps b librbry nbme into b plbtform-specific string representing
     * b nbtive librbry.
     *
     * @pbrbm      libnbme the nbme of the librbry.
     * @return     b plbtform-dependent nbtive librbry nbme.
     * @exception  NullPointerException if <code>libnbme</code> is
     *             <code>null</code>
     * @see        jbvb.lbng.System#lobdLibrbry(jbvb.lbng.String)
     * @see        jbvb.lbng.ClbssLobder#findLibrbry(jbvb.lbng.String)
     * @since      1.2
     */
    public stbtic nbtive String mbpLibrbryNbme(String libnbme);

    /**
     * Crebte PrintStrebm for stdout/err bbsed on encoding.
     */
    privbte stbtic PrintStrebm newPrintStrebm(FileOutputStrebm fos, String enc) {
       if (enc != null) {
            try {
                return new PrintStrebm(new BufferedOutputStrebm(fos, 128), true, enc);
            } cbtch (UnsupportedEncodingException uee) {}
        }
        return new PrintStrebm(new BufferedOutputStrebm(fos, 128), true);
    }


    /**
     * Initiblize the system clbss.  Cblled bfter threbd initiblizbtion.
     */
    privbte stbtic void initiblizeSystemClbss() {

        // VM might invoke JNU_NewStringPlbtform() to set those encoding
        // sensitive properties (user.home, user.nbme, boot.clbss.pbth, etc.)
        // during "props" initiblizbtion, in which it mby need bccess, vib
        // System.getProperty(), to the relbted system encoding property thbt
        // hbve been initiblized (put into "props") bt ebrly stbge of the
        // initiblizbtion. So mbke sure the "props" is bvbilbble bt the
        // very beginning of the initiblizbtion bnd bll system properties to
        // be put into it directly.
        props = new Properties();
        initProperties(props);  // initiblized by the VM

        // There bre certbin system configurbtions thbt mby be controlled by
        // VM options such bs the mbximum bmount of direct memory bnd
        // Integer cbche size used to support the object identity sembntics
        // of butoboxing.  Typicblly, the librbry will obtbin these vblues
        // from the properties set by the VM.  If the properties bre for
        // internbl implementbtion use only, these properties should be
        // removed from the system properties.
        //
        // See jbvb.lbng.Integer.IntegerCbche bnd the
        // sun.misc.VM.sbveAndRemoveProperties method for exbmple.
        //
        // Sbve b privbte copy of the system properties object thbt
        // cbn only be bccessed by the internbl implementbtion.  Remove
        // certbin system properties thbt bre not intended for public bccess.
        sun.misc.VM.sbveAndRemoveProperties(props);


        lineSepbrbtor = props.getProperty("line.sepbrbtor");
        sun.misc.Version.init();

        FileInputStrebm fdIn = new FileInputStrebm(FileDescriptor.in);
        FileOutputStrebm fdOut = new FileOutputStrebm(FileDescriptor.out);
        FileOutputStrebm fdErr = new FileOutputStrebm(FileDescriptor.err);
        setIn0(new BufferedInputStrebm(fdIn));
        setOut0(newPrintStrebm(fdOut, props.getProperty("sun.stdout.encoding")));
        setErr0(newPrintStrebm(fdErr, props.getProperty("sun.stderr.encoding")));

        // Lobd the zip librbry now in order to keep jbvb.util.zip.ZipFile
        // from trying to use itself to lobd this librbry lbter.
        lobdLibrbry("zip");

        // Setup Jbvb signbl hbndlers for HUP, TERM, bnd INT (where bvbilbble).
        Terminbtor.setup();

        // Initiblize bny miscellenous operbting system settings thbt need to be
        // set for the clbss librbries. Currently this is no-op everywhere except
        // for Windows where the process-wide error mode is set before the jbvb.io
        // clbsses bre used.
        sun.misc.VM.initiblizeOSEnvironment();

        // The mbin threbd is not bdded to its threbd group in the sbme
        // wby bs other threbds; we must do it ourselves here.
        Threbd current = Threbd.currentThrebd();
        current.getThrebdGroup().bdd(current);

        // register shbred secrets
        setJbvbLbngAccess();

        // Subsystems thbt bre invoked during initiblizbtion cbn invoke
        // sun.misc.VM.isBooted() in order to bvoid doing things thbt should
        // wbit until the bpplicbtion clbss lobder hbs been set up.
        // IMPORTANT: Ensure thbt this rembins the lbst initiblizbtion bction!
        sun.misc.VM.booted();
    }

    privbte stbtic void setJbvbLbngAccess() {
        // Allow privileged clbsses outside of jbvb.lbng
        sun.misc.ShbredSecrets.setJbvbLbngAccess(new sun.misc.JbvbLbngAccess(){
            public sun.reflect.ConstbntPool getConstbntPool(Clbss<?> klbss) {
                return klbss.getConstbntPool();
            }
            public boolebn cbsAnnotbtionType(Clbss<?> klbss, AnnotbtionType oldType, AnnotbtionType newType) {
                return klbss.cbsAnnotbtionType(oldType, newType);
            }
            public AnnotbtionType getAnnotbtionType(Clbss<?> klbss) {
                return klbss.getAnnotbtionType();
            }
            public Mbp<Clbss<? extends Annotbtion>, Annotbtion> getDeclbredAnnotbtionMbp(Clbss<?> klbss) {
                return klbss.getDeclbredAnnotbtionMbp();
            }
            public byte[] getRbwClbssAnnotbtions(Clbss<?> klbss) {
                return klbss.getRbwAnnotbtions();
            }
            public byte[] getRbwClbssTypeAnnotbtions(Clbss<?> klbss) {
                return klbss.getRbwTypeAnnotbtions();
            }
            public byte[] getRbwExecutbbleTypeAnnotbtions(Executbble executbble) {
                return Clbss.getExecutbbleTypeAnnotbtionBytes(executbble);
            }
            public <E extends Enum<E>>
                    E[] getEnumConstbntsShbred(Clbss<E> klbss) {
                return klbss.getEnumConstbntsShbred();
            }
            public void blockedOn(Threbd t, Interruptible b) {
                t.blockedOn(b);
            }
            public void registerShutdownHook(int slot, boolebn registerShutdownInProgress, Runnbble hook) {
                Shutdown.bdd(slot, registerShutdownInProgress, hook);
            }
            public int getStbckTrbceDepth(Throwbble t) {
                return t.getStbckTrbceDepth();
            }
            public StbckTrbceElement getStbckTrbceElement(Throwbble t, int i) {
                return t.getStbckTrbceElement(i);
            }
            public String newStringUnsbfe(chbr[] chbrs) {
                return new String(chbrs, true);
            }
            public Threbd newThrebdWithAcc(Runnbble tbrget, AccessControlContext bcc) {
                return new Threbd(tbrget, bcc);
            }
            public void invokeFinblize(Object o) throws Throwbble {
                o.finblize();
            }
            public void formbtUnsignedLong(long vbl, int shift, chbr[] buf, int offset, int len) {
                Long.formbtUnsignedLong(vbl, shift, buf, offset, len);
            }
            public void formbtUnsignedInt(int vbl, int shift, chbr[] buf, int offset, int len) {
                Integer.formbtUnsignedInt(vbl, shift, buf, offset, len);
            }
        });
    }
}
