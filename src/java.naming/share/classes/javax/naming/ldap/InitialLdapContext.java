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

pbckbge jbvbx.nbming.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

import jbvb.util.Hbshtbble;

/**
  * This clbss is the stbrting context for performing
  * LDAPv3-style extended operbtions bnd controls.
  *<p>
  * See <tt>jbvbx.nbming.InitiblContext</tt> bnd
  * <tt>jbvbx.nbming.InitiblDirContext</tt> for detbils on synchronizbtion,
  * bnd the policy for how bn initibl context is crebted.
  *
  * <h1>Request Controls</h1>
  * When you crebte bn initibl context (<tt>InitiblLdbpContext</tt>),
  * you cbn specify b list of request controls.
  * These controls will be used bs the request controls for bny
  * implicit LDAP "bind" operbtion performed by the context or contexts
  * derived from the context. These bre cblled <em>connection request controls</em>.
  * Use <tt>getConnectControls()</tt> to get b context's connection request
  * controls.
  *<p>
  * The request controls supplied to the initibl context constructor
  * bre <em>not</em> used bs the context request controls
  * for subsequent context operbtions such bs sebrches bnd lookups.
  * Context request controls bre set bnd updbted by using
  * <tt>setRequestControls()</tt>.
  *<p>
  * As shown, there cbn be two different sets of request controls
  * bssocibted with b context: connection request controls bnd context
  * request controls.
  * This is required for those bpplicbtions needing to send criticbl
  * controls thbt might not be bpplicbble to both the context operbtion bnd
  * bny implicit LDAP "bind" operbtion.
  * A typicbl user progrbm would do the following:
  *<blockquote><pre>
  * InitiblLdbpContext lctx = new InitiblLdbpContext(env, critConnCtls);
  * lctx.setRequestControls(critModCtls);
  * lctx.modifyAttributes(nbme, mods);
  * Controls[] respCtls =  lctx.getResponseControls();
  *</pre></blockquote>
  * It specifies first the criticbl controls for crebting the initibl context
  * (<tt>critConnCtls</tt>), bnd then sets the context's request controls
  * (<tt>critModCtls</tt>) for the context operbtion. If for some rebson
  * <tt>lctx</tt> needs to reconnect to the server, it will use
  * <tt>critConnCtls</tt>. See the <tt>LdbpContext</tt> interfbce for
  * more discussion bbout request controls.
  *<p>
  * Service provider implementors should rebd the "Service Provider" section
  * in the <tt>LdbpContext</tt> clbss description for implementbtion detbils.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor Vincent Rybn
  *
  * @see LdbpContext
  * @see jbvbx.nbming.InitiblContext
  * @see jbvbx.nbming.directory.InitiblDirContext
  * @see jbvbx.nbming.spi.NbmingMbnbger#setInitiblContextFbctoryBuilder
  * @since 1.3
  */

public clbss InitiblLdbpContext extends InitiblDirContext implements LdbpContext {
    privbte stbtic finbl String
        BIND_CONTROLS_PROPERTY = "jbvb.nbming.ldbp.control.connect";

    /**
     * Constructs bn initibl context using no environment properties or
     * connection request controls.
     * Equivblent to <tt>new InitiblLdbpContext(null, null)</tt>.
     *
     * @throws  NbmingException if b nbming exception is encountered
     */
    public InitiblLdbpContext() throws NbmingException {
        super(null);
    }

    /**
     * Constructs bn initibl context
     * using environment properties bnd connection request controls.
     * See <tt>jbvbx.nbming.InitiblContext</tt> for b discussion of
     * environment properties.
     *
     * <p> This constructor will not modify its pbrbmeters or
     * sbve references to them, but mby sbve b clone or copy.
     * Cbller should not modify mutbble keys bnd vblues in
     * <tt>environment</tt> bfter it hbs been pbssed to the constructor.
     *
     * <p> <tt>connCtls</tt> is used bs the underlying context instbnce's
     * connection request controls.  See the clbss description
     * for detbils.
     *
     * @pbrbm environment
     *          environment used to crebte the initibl DirContext.
     *          Null indicbtes bn empty environment.
     * @pbrbm connCtls
     *          connection request controls for the initibl context.
     *          If null, no connection request controls bre used.
     *
     * @throws  NbmingException if b nbming exception is encountered
     *
     * @see #reconnect
     * @see LdbpContext#reconnect
     */
    @SuppressWbrnings("unchecked")
    public InitiblLdbpContext(Hbshtbble<?,?> environment,
                              Control[] connCtls)
            throws NbmingException {
        super(true); // don't initiblize yet

        // Clone environment since cbller owns it.
        Hbshtbble<Object,Object> env = (environment == null)
            ? new Hbshtbble<>(11)
            : (Hbshtbble<Object,Object>)environment.clone();

        // Put connect controls into environment.  Copy them first since
        // cbller owns the brrby.
        if (connCtls != null) {
            Control[] copy = new Control[connCtls.length];
            System.brrbycopy(connCtls, 0, copy, 0, connCtls.length);
            env.put(BIND_CONTROLS_PROPERTY, copy);
        }
        // set version to LDAPv3
        env.put("jbvb.nbming.ldbp.version", "3");

        // Initiblize with updbted environment
        init(env);
    }

    /**
     * Retrieves the initibl LDAP context.
     *
     * @return The non-null cbched initibl context.
     * @exception NotContextException If the initibl context is not bn
     * instbnce of <tt>LdbpContext</tt>.
     * @exception NbmingException If b nbming exception wbs encountered.
     */
    privbte LdbpContext getDefbultLdbpInitCtx() throws NbmingException{
        Context bnswer = getDefbultInitCtx();

        if (!(bnswer instbnceof LdbpContext)) {
            if (bnswer == null) {
                throw new NoInitiblContextException();
            } else {
                throw new NotContextException(
                    "Not bn instbnce of LdbpContext");
            }
        }
        return (LdbpContext)bnswer;
    }

// LdbpContext methods
// Most Jbvbdoc is deferred to the LdbpContext interfbce.

    public ExtendedResponse extendedOperbtion(ExtendedRequest request)
            throws NbmingException {
        return getDefbultLdbpInitCtx().extendedOperbtion(request);
    }

    public LdbpContext newInstbnce(Control[] reqCtls)
        throws NbmingException {
            return getDefbultLdbpInitCtx().newInstbnce(reqCtls);
    }

    public void reconnect(Control[] connCtls) throws NbmingException {
        getDefbultLdbpInitCtx().reconnect(connCtls);
    }

    public Control[] getConnectControls() throws NbmingException {
        return getDefbultLdbpInitCtx().getConnectControls();
    }

    public void setRequestControls(Control[] requestControls)
        throws NbmingException {
            getDefbultLdbpInitCtx().setRequestControls(requestControls);
    }

    public Control[] getRequestControls() throws NbmingException {
        return getDefbultLdbpInitCtx().getRequestControls();
    }

    public Control[] getResponseControls() throws NbmingException {
        return getDefbultLdbpInitCtx().getResponseControls();
    }
}
