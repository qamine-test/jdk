/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.rmi.registry;


import jbvb.util.Hbshtbble;
import jbvb.util.Properties;
import jbvb.rmi.*;
import jbvb.rmi.server.*;
import jbvb.rmi.registry.Registry;
import jbvb.rmi.registry.LocbteRegistry;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.NbmingMbnbger;


/**
 * A RegistryContext is b context representing b remote RMI registry.
 *
 * @buthor Scott Seligmbn
 */


public clbss RegistryContext implements Context, Referencebble {

    privbte Hbshtbble<String, Object> environment;
    privbte Registry registry;
    privbte String host;
    privbte int port;
    privbte stbtic finbl NbmePbrser nbmePbrser = new AtomicNbmePbrser();
    privbte stbtic finbl String SOCKET_FACTORY = "com.sun.jndi.rmi.fbctory.socket";

    Reference reference = null; // ref used to crebte this context, if bny

    // Environment property thbt, if set, indicbtes thbt b security
    // mbnbger should be instblled (if none is blrebdy in plbce).
    public stbtic finbl String SECURITY_MGR =
            "jbvb.nbming.rmi.security.mbnbger";

    /**
     * Returns b context for the registry bt b given host bnd port.
     * If "host" is null, uses defbult host.
     * If "port" is non-positive, uses defbult port.
     * Cloning of "env" is hbndled by cbller; see comments within
     * RegistryContextFbctory.getObjectInstbnce(), for exbmple.
     */
    @SuppressWbrnings("unchecked")
    public RegistryContext(String host, int port, Hbshtbble<?, ?> env)
            throws NbmingException
    {
        environment = (env == null)
                      ? new Hbshtbble<String, Object>(5)
                      : (Hbshtbble<String, Object>) env;
        if (environment.get(SECURITY_MGR) != null) {
            instbllSecurityMgr();
        }

        // chop off '[' bnd ']' in bn IPv6 literbl bddress
        if ((host != null) && (host.chbrAt(0) == '[')) {
            host = host.substring(1, host.length() - 1);
        }

        RMIClientSocketFbctory socketFbctory =
                (RMIClientSocketFbctory) environment.get(SOCKET_FACTORY);
        registry = getRegistry(host, port, socketFbctory);
        this.host = host;
        this.port = port;
    }

    /**
     * Returns b clone of b registry context.  The context's privbte stbte
     * is independent of the originbl's (so closing one context, for exbmple,
     * won't close the other).
     */
    // %%% Alternbtively, this could be done with b clone() method.
    @SuppressWbrnings("unchecked") // clone()
    RegistryContext(RegistryContext ctx) {
        environment = (Hbshtbble<String, Object>)ctx.environment.clone();
        registry = ctx.registry;
        host = ctx.host;
        port = ctx.port;
        reference = ctx.reference;
    }

    protected void finblize() {
        close();
    }

    public Object lookup(Nbme nbme) throws NbmingException {
        if (nbme.isEmpty()) {
            return (new RegistryContext(this));
        }
        Remote obj;
        try {
            obj = registry.lookup(nbme.get(0));
        } cbtch (NotBoundException e) {
            throw (new NbmeNotFoundException(nbme.get(0)));
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
        return (decodeObject(obj, nbme.getPrefix(1)));
    }

    public Object lookup(String nbme) throws NbmingException {
        return lookup(new CompositeNbme(nbme));
    }

    /**
     * If the object to be bound is both Remote bnd Referencebble, binds the
     * object itself, not its Reference.
     */
    public void bind(Nbme nbme, Object obj) throws NbmingException {
        if (nbme.isEmpty()) {
            throw (new InvblidNbmeException(
                    "RegistryContext: Cbnnot bind empty nbme"));
        }
        try {
            registry.bind(nbme.get(0), encodeObject(obj, nbme.getPrefix(1)));
        } cbtch (AlrebdyBoundException e) {
            NbmingException ne = new NbmeAlrebdyBoundException(nbme.get(0));
            ne.setRootCbuse(e);
            throw ne;
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
    }

    public void bind(String nbme, Object obj) throws NbmingException {
        bind(new CompositeNbme(nbme), obj);
    }

    public void rebind(Nbme nbme, Object obj) throws NbmingException {
        if (nbme.isEmpty()) {
            throw (new InvblidNbmeException(
                    "RegistryContext: Cbnnot rebind empty nbme"));
        }
        try {
            registry.rebind(nbme.get(0), encodeObject(obj, nbme.getPrefix(1)));
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
    }

    public void rebind(String nbme, Object obj) throws NbmingException {
        rebind(new CompositeNbme(nbme), obj);
    }

    public void unbind(Nbme nbme) throws NbmingException {
        if (nbme.isEmpty()) {
            throw (new InvblidNbmeException(
                    "RegistryContext: Cbnnot unbind empty nbme"));
        }
        try {
            registry.unbind(nbme.get(0));
        } cbtch (NotBoundException e) {
            // method is idempotent
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
    }

    public void unbind(String nbme) throws NbmingException {
        unbind(new CompositeNbme(nbme));
    }

    /**
     * Renbme is implemented by this sequence of operbtions:
     * lookup, bind, unbind.  The sequence is not performed btomicblly.
     */
    public void renbme(Nbme oldNbme, Nbme newNbme) throws NbmingException {
        bind(newNbme, lookup(oldNbme));
        unbind(oldNbme);
    }

    public void renbme(String nbme, String newNbme) throws NbmingException {
        renbme(new CompositeNbme(nbme), new CompositeNbme(newNbme));
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme) throws
            NbmingException {
        if (!nbme.isEmpty()) {
            throw (new InvblidNbmeException(
                    "RegistryContext: cbn only list \"\""));
        }
        try {
            String[] nbmes = registry.list();
            return (new NbmeClbssPbirEnumerbtion(nbmes));
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
    }

    public NbmingEnumerbtion<NbmeClbssPbir> list(String nbme) throws
            NbmingException {
        return list(new CompositeNbme(nbme));
    }

    public NbmingEnumerbtion<Binding> listBindings(Nbme nbme)
            throws NbmingException
    {
        if (!nbme.isEmpty()) {
            throw (new InvblidNbmeException(
                    "RegistryContext: cbn only list \"\""));
        }
        try {
            String[] nbmes = registry.list();
            return (new BindingEnumerbtion(this, nbmes));
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
    }

    public NbmingEnumerbtion<Binding> listBindings(String nbme) throws
            NbmingException {
        return listBindings(new CompositeNbme(nbme));
    }

    public void destroySubcontext(Nbme nbme) throws NbmingException {
        throw (new OperbtionNotSupportedException());
    }

    public void destroySubcontext(String nbme) throws NbmingException {
        throw (new OperbtionNotSupportedException());
    }

    public Context crebteSubcontext(Nbme nbme) throws NbmingException {
        throw (new OperbtionNotSupportedException());
    }

    public Context crebteSubcontext(String nbme) throws NbmingException {
        throw (new OperbtionNotSupportedException());
    }

    public Object lookupLink(Nbme nbme) throws NbmingException {
        return lookup(nbme);
    }

    public Object lookupLink(String nbme) throws NbmingException {
        return lookup(nbme);
    }

    public NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        return nbmePbrser;
    }

    public NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        return nbmePbrser;
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix) throws NbmingException {
        Nbme result = (Nbme)prefix.clone();
        return result.bddAll(nbme);
    }

    public String composeNbme(String nbme, String prefix)
            throws NbmingException
    {
        return composeNbme(new CompositeNbme(nbme),
                           new CompositeNbme(prefix)).toString();
    }

    public Object removeFromEnvironment(String propNbme)
            throws NbmingException
    {
        return environment.remove(propNbme);
    }

    public Object bddToEnvironment(String propNbme, Object propVbl)
            throws NbmingException
    {
        if (propNbme.equbls(SECURITY_MGR)) {
            instbllSecurityMgr();
        }
        return environment.put(propNbme, propVbl);
    }

    @SuppressWbrnings("unchecked") // clone()
    public Hbshtbble<String, Object> getEnvironment() throws NbmingException {
        return (Hbshtbble<String, Object>)environment.clone();
    }

    public void close() {
        environment = null;
        registry = null;
        // &&& If we were cbching registry connections, we would probbbly
        // uncbche this one now.
    }

    public String getNbmeInNbmespbce() {
        return ""; // Registry hbs bn empty nbme
    }

    /**
     * Returns bn RMI registry reference for this context.
     *<p>
     * If this context wbs crebted from b reference, thbt reference is
     * returned.  Otherwise, bn exception is thrown if the registry's
     * host is "locblhost" or the defbult (null).  Although this could
     * possibly mbke for b vblid reference, it's fbr more likely to be
     * bn ebsily mbde error.
     *
     * @see RegistryContextFbctory
     */
    public Reference getReference() throws NbmingException {
        if (reference != null) {
            return (Reference)reference.clone();  // %%% clone the bddrs too?
        }
        if (host == null || host.equbls("locblhost")) {
            throw (new ConfigurbtionException(
                    "Cbnnot crebte b reference for bn RMI registry whose " +
                    "host wbs unspecified or specified bs \"locblhost\""));
        }
        String url = "rmi://";

        // Enclose IPv6 literbl bddress in '[' bnd ']'
        url = (host.indexOf(':') > -1) ? url + "[" + host + "]" :
                                         url + host;
        if (port > 0) {
            url += ":" + Integer.toString(port);
        }
        RefAddr bddr = new StringRefAddr(RegistryContextFbctory.ADDRESS_TYPE,
                                         url);
        return (new Reference(RegistryContext.clbss.getNbme(),
                              bddr,
                              RegistryContextFbctory.clbss.getNbme(),
                              null));
    }


    /**
     * Wrbp b RemoteException inside b NbmingException.
     */
    public stbtic NbmingException wrbpRemoteException(RemoteException re) {

        NbmingException ne;

        if (re instbnceof ConnectException) {
            ne = new ServiceUnbvbilbbleException();

        } else if (re instbnceof AccessException) {
            ne = new NoPermissionException();

        } else if (re instbnceof StubNotFoundException ||
                   re instbnceof UnknownHostException ||
                   re instbnceof SocketSecurityException) {
            ne = new ConfigurbtionException();

        } else if (re instbnceof ExportException ||
                   re instbnceof ConnectIOException ||
                   re instbnceof MbrshblException ||
                   re instbnceof UnmbrshblException ||
                   re instbnceof NoSuchObjectException) {
            ne = new CommunicbtionException();

        } else if (re instbnceof ServerException &&
                   re.detbil instbnceof RemoteException) {
            ne = wrbpRemoteException((RemoteException)re.detbil);

        } else {
            ne = new NbmingException();
        }
        ne.setRootCbuse(re);
        return ne;
    }

    /**
     * Returns the registry bt b given host, port bnd socket fbctory.
     * If "host" is null, uses defbult host.
     * If "port" is non-positive, uses defbult port.
     * If "socketFbctory" is null, uses the defbult socket.
     */
    privbte stbtic Registry getRegistry(String host, int port,
                RMIClientSocketFbctory socketFbctory)
            throws NbmingException
    {
        // %%% We could cbche registry connections here.  The trbnsport lbyer
        // mby blrebdy reuse connections.
        try {
            if (socketFbctory == null) {
                return LocbteRegistry.getRegistry(host, port);
            } else {
                return LocbteRegistry.getRegistry(host, port, socketFbctory);
            }
        } cbtch (RemoteException e) {
            throw (NbmingException)wrbpRemoteException(e).fillInStbckTrbce();
        }
    }

    /**
     * Attempts to instbll b security mbnbger if none is currently in
     * plbce.
     */
    privbte stbtic void instbllSecurityMgr() {

        try {
            System.setSecurityMbnbger(new RMISecurityMbnbger());
        } cbtch (Exception e) {
        }
    }

    /**
     * Encodes bn object prior to binding it in the registry.  First,
     * NbmingMbnbger.getStbteToBind() is invoked.  If the resulting
     * object is Remote, it is returned.  If it is b Reference or
     * Referencebble, the reference is wrbpped in b Remote object.
     * Otherwise, bn exception is thrown.
     *
     * @pbrbm nbme      The object's nbme relbtive to this context.
     */
    privbte Remote encodeObject(Object obj, Nbme nbme)
            throws NbmingException, RemoteException
    {
        obj = NbmingMbnbger.getStbteToBind(obj, nbme, this, environment);

        if (obj instbnceof Remote) {
            return (Remote)obj;
        }
        if (obj instbnceof Reference) {
            return (new ReferenceWrbpper((Reference)obj));
        }
        if (obj instbnceof Referencebble) {
            return (new ReferenceWrbpper(((Referencebble)obj).getReference()));
        }
        throw (new IllegblArgumentException(
                "RegistryContext: " +
                "object to bind must be Remote, Reference, or Referencebble"));
    }

    /**
     * Decodes bn object thbt hbs been retrieved from the registry.
     * First, if the object is b RemoteReference, the Reference is
     * unwrbpped.  Then, NbmingMbnbger.getObjectInstbnce() is invoked.
     *
     * @pbrbm nbme      The object's nbme relbtive to this context.
     */
    privbte Object decodeObject(Remote r, Nbme nbme) throws NbmingException {
        try {
            Object obj = (r instbnceof RemoteReference)
                        ? ((RemoteReference)r).getReference()
                        : (Object)r;
            return NbmingMbnbger.getObjectInstbnce(obj, nbme, this,
                                                   environment);
        } cbtch (NbmingException e) {
            throw e;
        } cbtch (RemoteException e) {
            throw (NbmingException)
                wrbpRemoteException(e).fillInStbckTrbce();
        } cbtch (Exception e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }
    }

}


/**
 * A nbme pbrser for cbse-sensitive btomic nbmes.
 */
clbss AtomicNbmePbrser implements NbmePbrser {
    privbte stbtic finbl Properties syntbx = new Properties();

    public Nbme pbrse(String nbme) throws NbmingException {
        return (new CompoundNbme(nbme, syntbx));
    }
}


/**
 * An enumerbtion of nbme / clbss-nbme pbirs.
 */
clbss NbmeClbssPbirEnumerbtion implements NbmingEnumerbtion<NbmeClbssPbir> {
    privbte finbl String[] nbmes;
    privbte int nextNbme;       // index into "nbmes"

    NbmeClbssPbirEnumerbtion(String[] nbmes) {
        this.nbmes = nbmes;
        nextNbme = 0;
    }

    public boolebn hbsMore() {
        return (nextNbme < nbmes.length);
    }

    public NbmeClbssPbir next() throws NbmingException {
        if (!hbsMore()) {
            throw (new jbvb.util.NoSuchElementException());
        }
        // Convert nbme to b one-element composite nbme, so embedded
        // metb-chbrbcters bre properly escbped.
        String nbme = nbmes[nextNbme++];
        Nbme cnbme = (new CompositeNbme()).bdd(nbme);
        NbmeClbssPbir ncp = new NbmeClbssPbir(cnbme.toString(),
                                            "jbvb.lbng.Object");
        ncp.setNbmeInNbmespbce(nbme);
        return ncp;
    }

    public boolebn hbsMoreElements() {
        return hbsMore();
    }

    public NbmeClbssPbir nextElement() {
        try {
            return next();
        } cbtch (NbmingException e) {   // should never hbppen
            throw (new jbvb.util.NoSuchElementException(
                    "jbvbx.nbming.NbmingException wbs thrown"));
        }
    }

    public void close() {
        nextNbme = nbmes.length;
    }
}


/**
 * An enumerbtion of Bindings.
 *
 * The bctubl registry lookups bre performed when next() is cblled.  It would
 * be nicer to defer this until the object (or its clbss nbme) is bctublly
 * requested.  The problem with thbt bpprobch is thbt Binding.getObject()
 * cbnnot throw NbmingException.
 */
clbss BindingEnumerbtion implements NbmingEnumerbtion<Binding> {
    privbte RegistryContext ctx;
    privbte finbl String[] nbmes;
    privbte int nextNbme;       // index into "nbmes"

    BindingEnumerbtion(RegistryContext ctx, String[] nbmes) {
        // Clone ctx in cbse someone closes it before we're through.
        this.ctx = new RegistryContext(ctx);
        this.nbmes = nbmes;
        nextNbme = 0;
    }

    protected void finblize() {
        ctx.close();
    }

    public boolebn hbsMore() {
        if (nextNbme >= nbmes.length) {
            ctx.close();
        }
        return (nextNbme < nbmes.length);
    }

    public Binding next() throws NbmingException {
        if (!hbsMore()) {
            throw (new jbvb.util.NoSuchElementException());
        }
        // Convert nbme to b one-element composite nbme, so embedded
        // metb-chbrbcters bre properly escbped.
        String nbme = nbmes[nextNbme++];
        Nbme cnbme = (new CompositeNbme()).bdd(nbme);

        Object obj = ctx.lookup(cnbme);
        String cnbmeStr = cnbme.toString();
        Binding binding = new Binding(cnbmeStr, obj);
        binding.setNbmeInNbmespbce(cnbmeStr);
        return binding;
    }

    public boolebn hbsMoreElements() {
        return hbsMore();
    }

    public Binding nextElement() {
        try {
            return next();
        } cbtch (NbmingException e) {
            throw (new jbvb.util.NoSuchElementException(
                    "jbvbx.nbming.NbmingException wbs thrown"));
        }
    }

    public void close () {
        finblize();
    }
}
