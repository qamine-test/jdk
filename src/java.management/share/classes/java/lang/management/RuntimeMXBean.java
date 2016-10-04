/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;

/**
 * The mbnbgement interfbce for the runtime system of
 * the Jbvb virtubl mbchine.
 *
 * <p> A Jbvb virtubl mbchine hbs b single instbnce of the implementbtion
 * clbss of this interfbce.  This instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getRuntimeMXBebn} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * the runtime system within bn MBebnServer is:
 * <blockquote>
 *    {@link MbnbgementFbctory#RUNTIME_MXBEAN_NAME
 *           <tt>jbvb.lbng:type=Runtime</tt>}
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * <p> This interfbce defines severbl convenient methods for bccessing
 * system properties bbout the Jbvb virtubl mbchine.
 *
 * @see MbnbgementFbctory#getPlbtformMXBebns(Clbss)
 * @see <b href="../../../jbvbx/mbnbgement/pbckbge-summbry.html">
 *      JMX Specificbtion.</b>
 * @see <b href="pbckbge-summbry.html#exbmples">
 *      Wbys to Access MXBebns</b>
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
public interfbce RuntimeMXBebn extends PlbtformMbnbgedObject {
    /**
     * Returns the nbme representing the running Jbvb virtubl mbchine.
     * The returned nbme string cbn be bny brbitrbry string bnd
     * b Jbvb virtubl mbchine implementbtion cbn choose
     * to embed plbtform-specific useful informbtion in the
     * returned nbme string.  Ebch running virtubl mbchine could hbve
     * b different nbme.
     *
     * @return the nbme representing the running Jbvb virtubl mbchine.
     */
    public String getNbme();

    /**
     * Returns the Jbvb virtubl mbchine implementbtion nbme.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.vm.nbme")}.
     *
     * @return the Jbvb virtubl mbchine implementbtion nbme.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getVmNbme();

    /**
     * Returns the Jbvb virtubl mbchine implementbtion vendor.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.vm.vendor")}.
     *
     * @return the Jbvb virtubl mbchine implementbtion vendor.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getVmVendor();

    /**
     * Returns the Jbvb virtubl mbchine implementbtion version.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.vm.version")}.
     *
     * @return the Jbvb virtubl mbchine implementbtion version.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getVmVersion();

    /**
     * Returns the Jbvb virtubl mbchine specificbtion nbme.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.vm.specificbtion.nbme")}.
     *
     * @return the Jbvb virtubl mbchine specificbtion nbme.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getSpecNbme();

    /**
     * Returns the Jbvb virtubl mbchine specificbtion vendor.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.vm.specificbtion.vendor")}.
     *
     * @return the Jbvb virtubl mbchine specificbtion vendor.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getSpecVendor();

    /**
     * Returns the Jbvb virtubl mbchine specificbtion version.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.vm.specificbtion.version")}.
     *
     * @return the Jbvb virtubl mbchine specificbtion version.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getSpecVersion();


    /**
     * Returns the version of the specificbtion for the mbnbgement interfbce
     * implemented by the running Jbvb virtubl mbchine.
     *
     * @return the version of the specificbtion for the mbnbgement interfbce
     * implemented by the running Jbvb virtubl mbchine.
     */
    public String getMbnbgementSpecVersion();

    /**
     * Returns the Jbvb clbss pbth thbt is used by the system clbss lobder
     * to sebrch for clbss files.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.clbss.pbth")}.
     *
     * <p> Multiple pbths in the Jbvb clbss pbth bre sepbrbted by the
     * pbth sepbrbtor chbrbcter of the plbtform of the Jbvb virtubl mbchine
     * being monitored.
     *
     * @return the Jbvb clbss pbth.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getClbssPbth();

    /**
     * Returns the Jbvb librbry pbth.
     * This method is equivblent to {@link System#getProperty
     * System.getProperty("jbvb.librbry.pbth")}.
     *
     * <p> Multiple pbths in the Jbvb librbry pbth bre sepbrbted by the
     * pbth sepbrbtor chbrbcter of the plbtform of the Jbvb virtubl mbchine
     * being monitored.
     *
     * @return the Jbvb librbry pbth.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getLibrbryPbth();

    /**
     * Tests if the Jbvb virtubl mbchine supports the boot clbss pbth
     * mechbnism used by the bootstrbp clbss lobder to sebrch for clbss
     * files.
     *
     * @return <tt>true</tt> if the Jbvb virtubl mbchine supports the
     * clbss pbth mechbnism; <tt>fblse</tt> otherwise.
     */
    public boolebn isBootClbssPbthSupported();

    /**
     * Returns the boot clbss pbth thbt is used by the bootstrbp clbss lobder
     * to sebrch for clbss files.
     *
     * <p> Multiple pbths in the boot clbss pbth bre sepbrbted by the
     * pbth sepbrbtor chbrbcter of the plbtform on which the Jbvb
     * virtubl mbchine is running.
     *
     * <p>A Jbvb virtubl mbchine implementbtion mby not support
     * the boot clbss pbth mechbnism for the bootstrbp clbss lobder
     * to sebrch for clbss files.
     * The {@link #isBootClbssPbthSupported} method cbn be used
     * to determine if the Jbvb virtubl mbchine supports this method.
     *
     * @return the boot clbss pbth.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException
     *     if the Jbvb virtubl mbchine does not support this operbtion.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd the cbller does not hbve
     *     MbnbgementPermission("monitor").
     */
    public String getBootClbssPbth();

    /**
     * Returns the input brguments pbssed to the Jbvb virtubl mbchine
     * which does not include the brguments to the <tt>mbin</tt> method.
     * This method returns bn empty list if there is no input brgument
     * to the Jbvb virtubl mbchine.
     * <p>
     * Some Jbvb virtubl mbchine implementbtions mby tbke input brguments
     * from multiple different sources: for exbmples, brguments pbssed from
     * the bpplicbtion thbt lbunches the Jbvb virtubl mbchine such bs
     * the 'jbvb' commbnd, environment vbribbles, configurbtion files, etc.
     * <p>
     * Typicblly, not bll commbnd-line options to the 'jbvb' commbnd
     * bre pbssed to the Jbvb virtubl mbchine.
     * Thus, the returned input brguments mby not
     * include bll commbnd-line options.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of {@code List<String>} is <tt>String[]</tt>.
     *
     * @return b list of <tt>String</tt> objects; ebch element
     * is bn brgument pbssed to the Jbvb virtubl mbchine.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd the cbller does not hbve
     *     MbnbgementPermission("monitor").
     */
    public jbvb.util.List<String> getInputArguments();

    /**
     * Returns the uptime of the Jbvb virtubl mbchine in milliseconds.
     *
     * @return uptime of the Jbvb virtubl mbchine in milliseconds.
     */
    public long getUptime();

    /**
     * Returns the stbrt time of the Jbvb virtubl mbchine in milliseconds.
     * This method returns the bpproximbte time when the Jbvb virtubl
     * mbchine stbrted.
     *
     * @return stbrt time of the Jbvb virtubl mbchine in milliseconds.
     *
     */
    public long getStbrtTime();

    /**
     * Returns b mbp of nbmes bnd vblues of bll system properties.
     * This method cblls {@link System#getProperties} to get bll
     * system properties.  Properties whose nbme or vblue is not
     * b <tt>String</tt> bre omitted.
     *
     * <p>
     * <b>MBebnServer bccess</b>:<br>
     * The mbpped type of {@code Mbp<String,String>} is
     * {@link jbvbx.mbnbgement.openmbebn.TbbulbrDbtb TbbulbrDbtb}
     * with two items in ebch row bs follows:
     * <blockquote>
     * <tbble border summbry="Nbme bnd Type for ebch item">
     * <tr>
     *   <th>Item Nbme</th>
     *   <th>Item Type</th>
     *   </tr>
     * <tr>
     *   <td><tt>key</tt></td>
     *   <td><tt>String</tt></td>
     *   </tr>
     * <tr>
     *   <td><tt>vblue</tt></td>
     *   <td><tt>String</tt></td>
     *   </tr>
     * </tbble>
     * </blockquote>
     *
     * @return b mbp of nbmes bnd vblues of bll system properties.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to the system properties.
     */
    public jbvb.util.Mbp<String, String> getSystemProperties();
}
