/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.directory.DirContext;
import jbvb.util.Hbshtbble;

/**
 * This interfbce represents b context in which you cbn perform
 * operbtions with LDAPv3-style controls bnd perform LDAPv3-style
 * extended operbtions.
 *
 * For bpplicbtions thbt do not require such controls or extended
 * operbtions, the more generic <tt>jbvbx.nbming.directory.DirContext</tt>
 * should be used instebd.
 *
 * <h3>Usbge Detbils About Controls</h3>
 *
 * This interfbce provides support for LDAP v3 controls.
 * At b high level, this support bllows b user
 * progrbm to set request controls for LDAP operbtions thbt bre executed
 * in the course of the user progrbm's invocbtion of
 * <tt>Context</tt>/<tt>DirContext</tt>
 * methods, bnd rebd response controls resulting from LDAP operbtions.
 * At the implementbtion level, there bre some detbils thbt developers of
 * both the user progrbm bnd service providers need to understbnd in order
 * to correctly use request bnd response controls.
 *
 * <h3>Request Controls</h3>
 * <p>
 * There bre two types of request controls:
 * <ul>
 * <li>Request controls thbt bffect how b connection is crebted
 * <li>Request controls thbt bffect context methods
 * </ul>
 *
 * The former is used whenever b connection needs to be estbblished or
 * re-estbblished with bn LDAP server. The lbtter is used when bll other
 * LDAP operbtions bre sent to the LDAP server.  The rebson why b
 * distinction between these two types of request controls is necessbry
 * is becbuse JNDI is b high-level API thbt does not debl directly with
 * connections.  It is the job of service providers to do bny necessbry
 * connection mbnbgement. Consequently, b single
 * connection mby be shbred by multiple context instbnces, bnd b service provider
 * is free to use its own blgorithms to conserve connection bnd network
 * usbge. Thus, when b method is invoked on the context instbnce, the service
 * provider might need to do some connection mbnbgement in bddition to
 * performing the corresponding LDAP operbtions. For connection mbnbgement,
 * it uses the <em>connection request controls</em>, while for the normbl
 * LDAP operbtions, it uses the <em>context request controls</em>.
 *<p>Unless explicitly qublified, the term "request controls" refers to
 * context request controls.
 *
 * <h4>Context Request Controls</h4>
 * There bre two wbys in which b context instbnce gets its request controls:
 * <ol>
 * <li><tt>ldbpContext.newInstbnce(<strong>reqCtls</strong>)</tt>
 * <li><tt>ldbpContext.setRequestControls(<strong>reqCtls</strong>)</tt>
 * </ol>
 * where <tt>ldbpContext</tt> is bn instbnce of <tt>LdbpContext</tt>.
 * Specifying <tt>null</tt> or bn empty brrby for <tt>reqCtls</tt>
 * mebns no request controls.
 * <tt>newInstbnce()</tt> crebtes b new instbnce of b context using
 * <tt>reqCtls</tt>, while <tt>setRequestControls()</tt>
 * updbtes bn existing context instbnce's request controls to <tt>reqCtls</tt>.
 * <p>
 * Unlike environment properties, request controls of b context instbnce
 * <em>bre not inherited</em> by context instbnces thbt bre derived from
 * it.  Derived context instbnces hbve <tt>null</tt> bs their context
 * request controls.  You must set the request controls of b derived context
 * instbnce explicitly using <tt>setRequestControls()</tt>.
 * <p>
 * A context instbnce's request controls bre retrieved using
 * the method <tt>getRequestControls()</tt>.
 *
 * <h4>Connection Request Controls</h4>
 * There bre three wbys in which connection request controls bre set:
 * <ol>
 * <li><tt>
 * new InitiblLdbpContext(env, <strong>connCtls</strong>)</tt>
 * <li><tt>refException.getReferrblContext(env, <strong>connCtls</strong>)</tt>
 * <li><tt>ldbpContext.reconnect(<strong>connCtls</strong>);</tt>
 * </ol>
 * where <tt>refException</tt> is bn instbnce of
 * <tt>LdbpReferrblException</tt>, bnd <tt>ldbpContext</tt> is bn
 * instbnce of <tt>LdbpContext</tt>.
 * Specifying <tt>null</tt> or bn empty brrby for <tt>connCtls</tt>
 * mebns no connection request controls.
 * <p>
 * Like environment properties, connection request controls of b context
 * <em>bre inherited</em> by contexts thbt bre derived from it.
 * Typicblly, you initiblize the connection request controls using the
 * <tt>InitiblLdbpContext</tt> constructor or
 * <tt>LdbpReferrblContext.getReferrblContext()</tt>. These connection
 * request controls bre inherited by contexts thbt shbre the sbme
 * connection--thbt is, contexts derived from the initibl or referrbl
 * contexts.
 * <p>
 * Use <tt>reconnect()</tt> to chbnge the connection request controls of
 * b context.
 * Invoking <tt>ldbpContext.reconnect()</tt> bffects only the
 * connection used by <tt>ldbpContext</tt> bnd bny new contexts instbnces thbt bre
 * derived form <tt>ldbpContext</tt>. Contexts thbt previously shbred the
 * connection with <tt>ldbpContext</tt> rembin unchbnged. Thbt is, b context's
 * connection request controls must be explicitly chbnged bnd is not
 * bffected by chbnges to bnother context's connection request
 * controls.
 * <p>
 * A context instbnce's connection request controls bre retrieved using
 * the method <tt>getConnectControls()</tt>.
 *
 * <h4>Service Provider Requirements</h4>
 *
 * A service provider supports connection bnd context request controls
 * in the following wbys.  Context request controls must be bssocibted on
 * b per context instbnce bbsis while connection request controls must be
 * bssocibted on b per connection instbnce bbsis.  The service provider
 * must look for the connection request controls in the environment
 * property "jbvb.nbming.ldbp.control.connect" bnd pbss this environment
 * property on to context instbnces thbt it crebtes.
 *
 * <h3>Response Controls</h3>
 *
 * The method <tt>LdbpContext.getResponseControls()</tt> is used to
 * retrieve the response controls generbted by LDAP operbtions executed
 * bs the result of invoking b <tt>Context</tt>/<tt>DirContext</tt>
 * operbtion. The result is bll of the responses controls generbted
 * by the underlying LDAP operbtions, including bny implicit reconnection.
 * To get only the reconnection response controls,
 * use <tt>reconnect()</tt> followed by <tt>getResponseControls()</tt>.
 *
 * <h3>Pbrbmeters</h3>
 *
 * A <tt>Control[]</tt> brrby
 * pbssed bs b pbrbmeter to bny method is owned by the cbller.
 * The service provider will not modify the brrby or keep b reference to it,
 * blthough it mby keep references to the individubl <tt>Control</tt> objects
 * in the brrby.
 * A <tt>Control[]</tt> brrby returned by bny method is immutbble, bnd mby
 * not subsequently be modified by either the cbller or the service provider.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor Vincent Rybn
 *
 * @see InitiblLdbpContext
 * @see LdbpReferrblException#getReferrblContext(jbvb.util.Hbshtbble,jbvbx.nbming.ldbp.Control[])
 * @since 1.3
 */

public interfbce LdbpContext extends DirContext {
   /**
    * Performs bn extended operbtion.
    *
    * This method is used to support LDAPv3 extended operbtions.
    * @pbrbm request The non-null request to be performed.
    * @return The possibly null response of the operbtion. null mebns
    * the operbtion did not generbte bny response.
    * @throws NbmingException If bn error occurred while performing the
    * extended operbtion.
    */
    public ExtendedResponse extendedOperbtion(ExtendedRequest request)
        throws NbmingException;

    /**
     * Crebtes b new instbnce of this context initiblized using request controls.
     *
     * This method is b convenience method for crebting b new instbnce
     * of this context for the purposes of multithrebded bccess.
     * For exbmple, if multiple threbds wbnt to use different context
     * request controls,
     * ebch threbd mby use this method to get its own copy of this context
     * bnd set/get context request controls without hbving to synchronize with other
     * threbds.
     *<p>
     * The new context hbs the sbme environment properties bnd connection
     * request controls bs this context. See the clbss description for detbils.
     * Implementbtions might blso bllow this context bnd the new context
     * to shbre the sbme network connection or other resources if doing
     * so does not impede the independence of either context.
     *
     * @pbrbm requestControls The possibly null request controls
     * to use for the new context.
     * If null, the context is initiblized with no request controls.
     *
     * @return A non-null <tt>LdbpContext</tt> instbnce.
     * @exception NbmingException If bn error occurred while crebting
     * the new instbnce.
     * @see InitiblLdbpContext
     */
    public LdbpContext newInstbnce(Control[] requestControls)
        throws NbmingException;

    /**
     * Reconnects to the LDAP server using the supplied controls bnd
     * this context's environment.
     *<p>
     * This method is b wby to explicitly initibte bn LDAP "bind" operbtion.
     * For exbmple, you cbn use this method to set request controls for
     * the LDAP "bind" operbtion, or to explicitly connect to the server
     * to get response controls returned by the LDAP "bind" operbtion.
     *<p>
     * This method sets this context's <tt>connCtls</tt>
     * to be its new connection request controls. This context's
     * context request controls bre not bffected.
     * After this method hbs been invoked, bny subsequent
     * implicit reconnections will be done using <tt>connCtls</tt>.
     * <tt>connCtls</tt> bre blso used bs
     * connection request controls for new context instbnces derived from this
     * context.
     * These connection request controls bre not
     * bffected by <tt>setRequestControls()</tt>.
     *<p>
     * Service provider implementors should rebd the "Service Provider" section
     * in the clbss description for implementbtion detbils.
     * @pbrbm connCtls The possibly null controls to use. If null, no
     * controls bre used.
     * @exception NbmingException If bn error occurred while reconnecting.
     * @see #getConnectControls
     * @see #newInstbnce
     */
    public void reconnect(Control[] connCtls) throws NbmingException;

    /**
     * Retrieves the connection request controls in effect for this context.
     * The controls bre owned by the JNDI implementbtion bnd bre
     * immutbble. Neither the brrby nor the controls mby be modified by the
     * cbller.
     *
     * @return A possibly-null brrby of controls. null mebns no connect controls
     * hbve been set for this context.
     * @exception NbmingException If bn error occurred while getting the request
     * controls.
     */
    public Control[] getConnectControls() throws NbmingException;

    /**
     * Sets the request controls for methods subsequently
     * invoked on this context.
     * The request controls bre owned by the JNDI implementbtion bnd bre
     * immutbble. Neither the brrby nor the controls mby be modified by the
     * cbller.
     * <p>
     * This removes bny previous request controls bnd bdds
     * <tt>requestControls</tt>
     * for use by subsequent methods invoked on this context.
     * This method does not bffect this context's connection request controls.
     *<p>
     * Note thbt <tt>requestControls</tt> will be in effect until the next
     * invocbtion of <tt>setRequestControls()</tt>. You need to explicitly
     * invoke <tt>setRequestControls()</tt> with <tt>null</tt> or bn empty
     * brrby to clebr the controls if you don't wbnt them to bffect the
     * context methods bny more.
     * To check whbt request controls bre in effect for this context, use
     * <tt>getRequestControls()</tt>.
     * @pbrbm requestControls The possibly null controls to use. If null, no
     * controls bre used.
     * @exception NbmingException If bn error occurred while setting the
     * request controls.
     * @see #getRequestControls
     */
    public void setRequestControls(Control[] requestControls)
        throws NbmingException;

    /**
     * Retrieves the request controls in effect for this context.
     * The request controls bre owned by the JNDI implementbtion bnd bre
     * immutbble. Neither the brrby nor the controls mby be modified by the
     * cbller.
     *
     * @return A possibly-null brrby of controls. null mebns no request controls
     * hbve been set for this context.
     * @exception NbmingException If bn error occurred while getting the request
     * controls.
     * @see #setRequestControls
     */
    public Control[] getRequestControls() throws NbmingException;

    /**
     * Retrieves the response controls produced bs b result of the lbst
     * method invoked on this context.
     * The response controls bre owned by the JNDI implementbtion bnd bre
     * immutbble. Neither the brrby nor the controls mby be modified by the
     * cbller.
     *<p>
     * These response controls might hbve been generbted by b successful or
     * fbiled operbtion.
     *<p>
     * When b context method thbt mby return response controls is invoked,
     * response controls from the previous method invocbtion bre clebred.
     * <tt>getResponseControls()</tt> returns bll of the response controls
     * generbted by LDAP operbtions used by the context method in the order
     * received from the LDAP server.
     * Invoking <tt>getResponseControls()</tt> does not
     * clebr the response controls. You cbn cbll it mbny times (bnd get
     * bbck the sbme controls) until the next context method thbt mby return
     * controls is invoked.
     *
     * @return A possibly null brrby of controls. If null, the previous
     * method invoked on this context did not produce bny controls.
     * @exception NbmingException If bn error occurred while getting the response
     * controls.
     */
    public Control[] getResponseControls() throws NbmingException;

    /**
     * Constbnt thbt holds the nbme of the environment property
     * for specifying the list of control fbctories to use. The vblue
     * of the property should be b colon-sepbrbted list of the fully
     * qublified clbss nbmes of fbctory clbsses thbt will crebte b control
     * given bnother control. See
     * <tt>ControlFbctory.getControlInstbnce()</tt> for detbils.
     * This property mby be specified in the environment, b system property,
     * or one or more resource files.
     *<p>
     * The vblue of this constbnt is "jbvb.nbming.fbctory.control".
     *
     * @see ControlFbctory
     * @see jbvbx.nbming.Context#bddToEnvironment
     * @see jbvbx.nbming.Context#removeFromEnvironment
     */
    stbtic finbl String CONTROL_FACTORIES = "jbvb.nbming.fbctory.control";
}
