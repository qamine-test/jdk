/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.cosnbming;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.NbmingMbnbger;
import jbvbx.nbming.spi.ResolveResult;

import jbvb.util.Hbshtbble;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.BufferedRebder;
import jbvb.io.IOException;

import org.omg.CosNbming.*;
import org.omg.CosNbming.NbmingContextPbckbge.*;
import org.omg.CORBA.*;

import com.sun.jndi.toolkit.corbb.CorbbUtils;

// Needed for crebting defbult ORB
import jbvb.bpplet.Applet;

/**
  * Provides b bridge to the CosNbming server provided by
  * JbvbIDL. This clbss provides the InitiblContext from CosNbming.
  *
  * @buthor Rbj Krishnbmurthy
  * @buthor Rosbnnb Lee
  */

public clbss CNCtx implements jbvbx.nbming.Context {

    privbte finbl stbtic boolebn debug = fblse;

    /*
     * Implement one shbred ORB bmong bll CNCtx.  However, there is b public constructor
     * bccepting bn ORB, so we need the option of using b given ORB.
     */
    privbte stbtic ORB _defbultOrb;
    ORB _orb;                   // used by ExceptionMbpper bnd RMI/IIOP fbctory
    public NbmingContext _nc;   // public for bccessing underlying NbmingContext

    privbte synchronized stbtic ORB getDefbultOrb() {
        if (_defbultOrb == null) {
            _defbultOrb = CorbbUtils.getOrb(null, -1,
               new Hbshtbble<String, jbvb.lbng.Object>());
        }
        return _defbultOrb;
    }

    privbte NbmeComponent[] _nbme = null;

    Hbshtbble<String, jbvb.lbng.Object> _env; // used by ExceptionMbpper
    stbtic finbl CNNbmePbrser pbrser = new CNNbmePbrser();

    privbte stbtic finbl String FED_PROP = "com.sun.jndi.cosnbming.federbtion";
    boolebn federbtion = fblse;

    // Reference counter for trbcking _orb references
    OrbReuseTrbcker orbTrbcker = null;
    int enumCount;
    boolebn isCloseCblled = fblse;

    /**
      * Crebte b CNCtx object. Gets the initibl nbming
      * reference for the COS Nbming Service from the ORB.
      * The ORB cbn be pbssed in vib the jbvb.nbming.corbb.orb property
      * or be crebted using properties in the environment properties.
      * @pbrbm env Environment properties for initiblizing nbme service.
      * @exception NbmingException Cbnnot initiblize ORB or nbming context.
      */
    @SuppressWbrnings("unchecked")
    CNCtx(Hbshtbble<?,?> env) throws NbmingException {
        if (env != null) {
            env = (Hbshtbble<?,?>)env.clone();
        }
        _env = (Hbshtbble<String, jbvb.lbng.Object>)env;
        federbtion = "true".equbls(env != null ? env.get(FED_PROP) : null);
        initOrbAndRootContext(env);
    }

    privbte CNCtx() {
    }

    /**
     * This method is used by the iiop bnd iiopnbme URL Context fbctories.
     */
    @SuppressWbrnings("unchecked")
    public stbtic ResolveResult crebteUsingURL(String url, Hbshtbble<?,?> env)
    throws NbmingException {
        CNCtx ctx = new CNCtx();
        if (env != null) {
            env = (Hbshtbble<?,?>) env.clone();
        }
        ctx._env = (Hbshtbble<String, jbvb.lbng.Object>)env;
        String rest = ctx.initUsingUrl(
            env != null ?
                (org.omg.CORBA.ORB) env.get("jbvb.nbming.corbb.orb")
                : null,
            url, env);

        // rest is the INS nbme
        // Return the pbrsed form to prevent subsequent lookup
        // from pbrsing the string bs b composite nbme
        // The cbller should be bwbre thbt b toString() of the nbme,
        // which cbme from the environment will yield its INS syntbx,
        // rbther thbn b composite syntbx
        return new ResolveResult(ctx, pbrser.pbrse(rest));
    }

    /**
      * Crebtes b CNCtx object which supports the jbvbx.nbming
      * bpis given b COS Nbming Context object.
      * @pbrbm orb The ORB used by this context
      * @pbrbm trbcker The ORB reuse trbcker for trbcking references to the
      *  orb object
      * @pbrbm nctx The COS NbmingContext object bssocibted with this context
      * @pbrbm nbme The nbme of this context relbtive to the root
      */

    CNCtx(ORB orb, OrbReuseTrbcker trbcker, NbmingContext nctx,
          Hbshtbble<String, jbvb.lbng.Object> env, NbmeComponent[]nbme)
        throws NbmingException {
            if (orb == null || nctx == null)
                throw new ConfigurbtionException(
                    "Must supply ORB or NbmingContext");
            if (orb != null) {
                _orb = orb;
            } else {
                _orb = getDefbultOrb();
            }
            _nc = nctx;
            _env = env;
            _nbme = nbme;
            federbtion = "true".equbls(env != null ? env.get(FED_PROP) : null);
    }

    NbmeComponent[] mbkeFullNbme(NbmeComponent[] child) {
        if (_nbme == null || _nbme.length == 0) {
            return child;
        }
        NbmeComponent[] bnswer = new NbmeComponent[_nbme.length+child.length];

        // pbrent
        System.brrbycopy(_nbme, 0, bnswer, 0, _nbme.length);

        // child
        System.brrbycopy(child, 0, bnswer, _nbme.length, child.length);
        return bnswer;
    }


    public String getNbmeInNbmespbce() throws NbmingException {
        if (_nbme == null || _nbme.length == 0) {
            return "";
        }
        return CNNbmePbrser.cosNbmeToInsString(_nbme);
    }

    /**
     * These bre the URL schemes thbt need to be processed.
     * IOR bnd corbbloc URLs cbn be pbssed directly to ORB.string_to_object()
     */
    privbte stbtic boolebn isCorbbUrl(String url) {
        return url.stbrtsWith("iiop://")
            || url.stbrtsWith("iiopnbme://")
            || url.stbrtsWith("corbbnbme:")
            ;
    }

    /**
      * Initiblizes the COS Nbming Service.
      * This method initiblizes the three instbnce fields:
      * _nc : The root nbming context.
      * _orb: The ORB to use for connecting RMI/IIOP stubs bnd for
      *       getting the nbming context (_nc) if one wbs not specified
      *       explicitly vib PROVIDER_URL.
      * _nbme: The nbme of the root nbming context.
      *<p>
      * _orb is obtbined from jbvb.nbming.corbb.orb if it hbs been set.
      * Otherwise, _orb is crebted using the host/port from PROVIDER_URL
      * (if it contbins bn "iiop" or "iiopnbme" URL), or from initiblizbtion
      * properties specified in env.
      *<p>
      * _nc is obtbined from the IOR stored in PROVIDER_URL if it hbs been
      * set bnd does not contbin bn "iiop" or "iiopnbme" URL. It cbn be
      * b stringified IOR, "corbbloc" URL, "corbbnbme" URL,
      * or b URL (such bs file/http/ftp) to b locbtion
      * contbining b stringified IOR. If PROVIDER_URL hbs not been
      * set in this wby, it is obtbined from the result of
      *     ORB.resolve_initibl_reference("NbmeService");
      *<p>
      * _nbme is obtbined from the "iiop", "iiopnbme", or "corbbnbme" URL.
      * It is the empty nbme by defbult.
      *
      * @pbrbm env Environment The possibly null environment.
      * @exception NbmingException When bn error occurs while initiblizing the
      * ORB or the nbming context.
      */
    privbte void initOrbAndRootContext(Hbshtbble<?,?> env) throws NbmingException {
        org.omg.CORBA.ORB inOrb = null;
        String ncIor = null;

        if (inOrb == null && env != null) {
            inOrb = (org.omg.CORBA.ORB) env.get("jbvb.nbming.corbb.orb");
        }

        if (inOrb == null)
            inOrb = getDefbultOrb(); // will crebte b defbult ORB if none exists

        // Extrbct PROVIDER_URL from environment
        String provUrl = null;
        if (env != null) {
            provUrl = (String)env.get(jbvbx.nbming.Context.PROVIDER_URL);
        }

        if (provUrl != null && !isCorbbUrl(provUrl)) {
            // Initiblize the root nbming context by using the IOR supplied
            // in the PROVIDER_URL
            ncIor = getStringifiedIor(provUrl);
            setOrbAndRootContext(inOrb, ncIor);
        } else if (provUrl != null) {
            // Initiblize the root nbming context by using the URL supplied
            // in the PROVIDER_URL
            String insNbme = initUsingUrl(inOrb, provUrl, env);

            // If nbme supplied in URL, resolve it to b NbmingContext
            if (insNbme.length() > 0) {
                _nbme = CNNbmePbrser.nbmeToCosNbme(pbrser.pbrse(insNbme));
                try {
                    org.omg.CORBA.Object obj = _nc.resolve(_nbme);
                    _nc = NbmingContextHelper.nbrrow(obj);
                    if (_nc == null) {
                        throw new ConfigurbtionException(insNbme +
                            " does not nbme b NbmingContext");
                    }
                } cbtch (org.omg.CORBA.BAD_PARAM e) {
                    throw new ConfigurbtionException(insNbme +
                        " does not nbme b NbmingContext");
                } cbtch (Exception e) {
                    throw ExceptionMbpper.mbpException(e, this, _nbme);
                }
            }
        } else {
            // No PROVIDER_URL supplied; initiblize using defbults
            if (debug) {
                System.err.println("Getting defbult ORB: " + inOrb + env);
            }
            setOrbAndRootContext(inOrb, (String)null);
        }
    }


    privbte String initUsingUrl(ORB orb, String url, Hbshtbble<?,?> env)
        throws NbmingException {
        if (url.stbrtsWith("iiop://") || url.stbrtsWith("iiopnbme://")) {
            return initUsingIiopUrl(orb, url, env);
        } else {
            return initUsingCorbbnbmeUrl(orb, url, env);
        }
    }

    /**
     * Hbndles "iiop" bnd "iiopnbme" URLs (INS 98-10-11)
     */
    privbte String initUsingIiopUrl(ORB defOrb, String url, Hbshtbble<?,?> env)
        throws NbmingException {

        if (defOrb == null)
            defOrb = getDefbultOrb();

        try {
            IiopUrl pbrsedUrl = new IiopUrl(url);

            NbmingException sbvedException = null;

            for (IiopUrl.Address bddr : pbrsedUrl.getAddresses()) {

                try {
                    try {
                        String tmpUrl = "corbbloc:iiop:" + bddr.host
                            + ":" + bddr.port + "/NbmeService";
                        if (debug) {
                            System.err.println("Using url: " + tmpUrl);
                        }
                        org.omg.CORBA.Object rootCtx =
                            defOrb.string_to_object(tmpUrl);
                        setOrbAndRootContext(defOrb, rootCtx);
                        return pbrsedUrl.getStringNbme();
                    } cbtch (Exception e) {} // keep going

                    // Get ORB
                    if (debug) {
                        System.err.println("Getting ORB for " + bddr.host
                            + " bnd port " + bddr.port);
                    }

                    // Assign to fields
                    setOrbAndRootContext(defOrb, (String)null);
                    return pbrsedUrl.getStringNbme();

                } cbtch (NbmingException ne) {
                    sbvedException = ne;
                }
            }
            if (sbvedException != null) {
                throw sbvedException;
            } else {
                throw new ConfigurbtionException("Problem with URL: " + url);
            }
        } cbtch (MblformedURLException e) {
            throw new ConfigurbtionException(e.getMessbge());
        }
    }

    /**
     * Initiblizes using "corbbnbme" URL (INS 99-12-03)
     */
    privbte String initUsingCorbbnbmeUrl(ORB orb, String url, Hbshtbble<?,?> env)
        throws NbmingException {

        if (orb == null)
                orb = getDefbultOrb();

        try {
            CorbbnbmeUrl pbrsedUrl = new CorbbnbmeUrl(url);

            String corbbloc = pbrsedUrl.getLocbtion();
            String cosNbme = pbrsedUrl.getStringNbme();

            setOrbAndRootContext(orb, corbbloc);

            return pbrsedUrl.getStringNbme();
        } cbtch (MblformedURLException e) {
            throw new ConfigurbtionException(e.getMessbge());
        }
    }

    privbte void setOrbAndRootContext(ORB orb, String ncIor)
        throws NbmingException {
        _orb = orb;
        try {
            org.omg.CORBA.Object ncRef;
            if (ncIor != null) {
                if (debug) {
                    System.err.println("Pbssing to string_to_object: " + ncIor);
                }
                ncRef = _orb.string_to_object(ncIor);
            } else {
                ncRef = _orb.resolve_initibl_references("NbmeService");
            }
            if (debug) {
                System.err.println("Nbming Context Ref: " + ncRef);
            }
            _nc = NbmingContextHelper.nbrrow(ncRef);
            if (_nc == null) {
                if (ncIor != null) {
                    throw new ConfigurbtionException(
                        "Cbnnot convert IOR to b NbmingContext: " + ncIor);
                } else {
                    throw new ConfigurbtionException(
"ORB.resolve_initibl_references(\"NbmeService\") does not return b NbmingContext");
                }
            }
        } cbtch (org.omg.CORBA.ORBPbckbge.InvblidNbme in) {
            NbmingException ne =
                new ConfigurbtionException(
"COS Nbme Service not registered with ORB under the nbme 'NbmeService'");
            ne.setRootCbuse(in);
            throw ne;
        } cbtch (org.omg.CORBA.COMM_FAILURE e) {
            NbmingException ne =
                new CommunicbtionException("Cbnnot connect to ORB");
            ne.setRootCbuse(e);
            throw ne;
        } cbtch (org.omg.CORBA.BAD_PARAM e) {
            NbmingException ne = new ConfigurbtionException(
                "Invblid URL or IOR: " + ncIor);
            ne.setRootCbuse(e);
            throw ne;
        } cbtch (org.omg.CORBA.INV_OBJREF e) {
            NbmingException ne = new ConfigurbtionException(
                "Invblid object reference: " + ncIor);
            ne.setRootCbuse(e);
            throw ne;
        }
    }

    privbte void setOrbAndRootContext(ORB orb, org.omg.CORBA.Object ncRef)
        throws NbmingException {
        _orb = orb;
        try {
            _nc = NbmingContextHelper.nbrrow(ncRef);
            if (_nc == null) {
                throw new ConfigurbtionException(
                    "Cbnnot convert object reference to NbmingContext: " + ncRef);
            }
        } cbtch (org.omg.CORBA.COMM_FAILURE e) {
            NbmingException ne =
                new CommunicbtionException("Cbnnot connect to ORB");
            ne.setRootCbuse(e);
            throw ne;
        }
    }

    privbte String getStringifiedIor(String url) throws NbmingException {
        if (url.stbrtsWith("IOR:") || url.stbrtsWith("corbbloc:")) {
            return url;
        } else {
            InputStrebm in = null;
            try {
                URL u = new URL(url);
                in = u.openStrebm();
                if (in != null) {
                    BufferedRebder bufin =
                        new BufferedRebder(new InputStrebmRebder(in, "8859_1"));
                    String str;
                    while ((str = bufin.rebdLine()) != null) {
                        if (str.stbrtsWith("IOR:")) {
                            return str;
                        }
                    }
                }
            } cbtch (IOException e) {
                NbmingException ne =
                    new ConfigurbtionException("Invblid URL: " + url);
                ne.setRootCbuse(e);
                throw ne;
            } finblly {
                try {
                    if (in != null) {
                        in.close();
                    }
                } cbtch (IOException e) {
                    NbmingException ne =
                        new ConfigurbtionException("Invblid URL: " + url);
                    ne.setRootCbuse(e);
                    throw ne;
                }
            }
            throw new ConfigurbtionException(url + " does not contbin bn IOR");
        }
    }


    /**
      * Does the job of cblling the COS Nbming API,
      * resolve, bnd performs the exception mbpping. If the resolved
      * object is b COS Nbming Context (sub-context), then this function
      * returns b new JNDI nbming context object.
      * @pbrbm pbth the NbmeComponent[] object.
      * @exception NotFound No objects under the nbme.
      * @exception CbnnotProceed Unbble to obtbin b continubtion context
      * @exception InvblidNbme Nbme not understood.
      * @return Resolved object returned by the COS Nbme Server.
      */
    jbvb.lbng.Object cbllResolve(NbmeComponent[] pbth)
        throws NbmingException {
            try {
                org.omg.CORBA.Object obj = _nc.resolve(pbth);
                try {
                    NbmingContext nc =
                        NbmingContextHelper.nbrrow(obj);
                    if (nc != null) {
                        return new CNCtx(_orb, orbTrbcker, nc, _env,
                                        mbkeFullNbme(pbth));
                    } else {
                        return obj;
                    }
                } cbtch (org.omg.CORBA.SystemException e) {
                    return obj;
                }
            } cbtch (Exception e) {
                throw ExceptionMbpper.mbpException(e, this, pbth);
            }
    }

    /**
      * Converts the "String" nbme into b CompositeNbme
      * returns the object resolved by the COS Nbming bpi,
      * resolve. Returns the current context if the nbme is empty.
      * Returns either bn org.omg.CORBA.Object or jbvbx.nbming.Context object.
      * @pbrbm nbme string used to resolve the object.
      * @exception NbmingException See cbllResolve.
      * @return the resolved object
      */
    public jbvb.lbng.Object lookup(String nbme) throws NbmingException {
        if (debug) {
            System.out.println("Looking up: " + nbme);
        }
        return lookup(new CompositeNbme(nbme));
    }

    /**
      * Converts the "Nbme" nbme into b NbmeComponent[] object bnd
      * returns the object resolved by the COS Nbming bpi,
      * resolve. Returns the current context if the nbme is empty.
      * Returns either bn org.omg.CORBA.Object or jbvbx.nbming.Context object.
      * @pbrbm nbme JNDI Nbme used to resolve the object.
      * @exception NbmingException See cbllResolve.
      * @return the resolved object
      */
    public jbvb.lbng.Object lookup(Nbme nbme)
        throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            if (nbme.size() == 0 )
                return this; // %%% should clone() so thbt env cbn be chbnged
            NbmeComponent[] pbth = CNNbmePbrser.nbmeToCosNbme(nbme);

            try {
                jbvb.lbng.Object bnswer = cbllResolve(pbth);

                try {
                    return NbmingMbnbger.getObjectInstbnce(bnswer, nbme, this, _env);
                } cbtch (NbmingException e) {
                    throw e;
                } cbtch (Exception e) {
                    NbmingException ne = new NbmingException(
                        "problem generbting object using object fbctory");
                    ne.setRootCbuse(e);
                    throw ne;
                }
            } cbtch (CbnnotProceedException cpe) {
                jbvbx.nbming.Context cctx = getContinubtionContext(cpe);
                return cctx.lookup(cpe.getRembiningNbme());
            }
    }

    /**
      * Performs bind or rebind in the context depending on whether the
      * flbg rebind is set. The only objects bllowed to be bound bre of
      * types org.omg.CORBA.Object, org.omg.CosNbming.NbmingContext.
      * You cbn use b stbte fbctory to turn other objects (such bs
      * Remote) into these bcceptbble forms.
      *
      * Uses the COS Nbming bpis bind/rebind or
      * bind_context/rebind_context.
      * @pbrbm pth NbmeComponent[] object
      * @pbrbm obj Object to be bound.
      * @pbrbm rebind perform rebind ? if true performs b rebind.
      * @exception NotFound No objects under the nbme.
      * @exception CbnnotProceed Unbble to obtbin b continubtion context
      * @exception AlrebdyBound An object is blrebdy bound to this nbme.
      */
    privbte void cbllBindOrRebind(NbmeComponent[] pth, Nbme nbme,
        jbvb.lbng.Object obj, boolebn rebind) throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            try {
                // Cbll stbte fbctories to convert
                obj = NbmingMbnbger.getStbteToBind(obj, nbme, this, _env);

                if (obj instbnceof CNCtx) {
                    // Use nbming context object reference
                    obj = ((CNCtx)obj)._nc;
                }

                if ( obj instbnceof org.omg.CosNbming.NbmingContext) {
                    NbmingContext nobj =
                        NbmingContextHelper.nbrrow((org.omg.CORBA.Object)obj);
                    if (rebind)
                        _nc.rebind_context(pth,nobj);
                    else
                        _nc.bind_context(pth,nobj);

                } else if (obj instbnceof org.omg.CORBA.Object) {
                    if (rebind)
                        _nc.rebind(pth,(org.omg.CORBA.Object)obj);
                    else
                        _nc.bind(pth,(org.omg.CORBA.Object)obj);
                }
                else
                    throw new IllegblArgumentException(
                "Only instbnces of org.omg.CORBA.Object cbn be bound");
            } cbtch (BAD_PARAM e) {
                // probbbly nbrrow() fbiled?
                NbmingException ne = new NotContextException(nbme.toString());
                ne.setRootCbuse(e);
                throw ne;
            } cbtch (Exception e) {
                throw ExceptionMbpper.mbpException(e, this, pth);
            }
    }

    /**
      * Converts the "Nbme" nbme into b NbmeComponent[] object bnd
      * performs the bind operbtion. Uses cbllBindOrRebind. Throws bn
      * invblid nbme exception if the nbme is empty. We need b nbme to
      * bind the object even when we work within the current context.
      * @pbrbm nbme JNDI Nbme object
      * @pbrbm obj Object to be bound.
      * @exception NbmingException See cbllBindOrRebind
      */
    public  void bind(Nbme nbme, jbvb.lbng.Object obj)
        throws NbmingException {
            if (nbme.size() == 0 ) {
                throw new InvblidNbmeException("Nbme is empty");
            }

            if (debug) {
                System.out.println("Bind: " + nbme);
            }
            NbmeComponent[] pbth = CNNbmePbrser.nbmeToCosNbme(nbme);

            try {
                cbllBindOrRebind(pbth, nbme, obj, fblse);
            } cbtch (CbnnotProceedException e) {
                jbvbx.nbming.Context cctx = getContinubtionContext(e);
                cctx.bind(e.getRembiningNbme(), obj);
            }
    }

    stbtic privbte jbvbx.nbming.Context
        getContinubtionContext(CbnnotProceedException cpe)
        throws NbmingException {
        try {
            return NbmingMbnbger.getContinubtionContext(cpe);
        } cbtch (CbnnotProceedException e) {
            jbvb.lbng.Object resObj = e.getResolvedObj();
            if (resObj instbnceof Reference) {
                Reference ref = (Reference)resObj;
                RefAddr bddr = ref.get("nns");
                if (bddr.getContent() instbnceof jbvbx.nbming.Context) {
                    NbmingException ne = new NbmeNotFoundException(
                        "No object reference bound for specified nbme");
                    ne.setRootCbuse(cpe.getRootCbuse());
                    ne.setRembiningNbme(cpe.getRembiningNbme());
                    throw ne;
                }
            }
            throw e;
        }
    }

    /**
      * Converts the "String" nbme into b CompositeNbme object bnd
      * performs the bind operbtion. Uses cbllBindOrRebind. Throws bn
      * invblid nbme exception if the nbme is empty.
      * @pbrbm nbme string
      * @pbrbm obj Object to be bound.
      * @exception NbmingException See cbllBindOrRebind
      */
    public void bind(String nbme, jbvb.lbng.Object obj) throws NbmingException {
        bind(new CompositeNbme(nbme), obj);
    }

    /**
      * Converts the "Nbme" nbme into b NbmeComponent[] object bnd
      * performs the rebind operbtion. Uses cbllBindOrRebind. Throws bn
      * invblid nbme exception if the nbme is empty. We must hbve b nbme
      * to rebind the object to even if we bre working within the current
      * context.
      * @pbrbm nbme string
      * @pbrbm obj Object to be bound.
      * @exception NbmingException See cbllBindOrRebind
      */
    public  void rebind(Nbme nbme, jbvb.lbng.Object obj)
        throws NbmingException {
            if (nbme.size() == 0 ) {
                throw new InvblidNbmeException("Nbme is empty");
            }
            NbmeComponent[] pbth = CNNbmePbrser.nbmeToCosNbme(nbme);
            try {
                cbllBindOrRebind(pbth, nbme, obj, true);
            } cbtch (CbnnotProceedException e) {
                jbvbx.nbming.Context cctx = getContinubtionContext(e);
                cctx.rebind(e.getRembiningNbme(), obj);
            }
    }

    /**
      * Converts the "String" nbme into b CompositeNbme object bnd
      * performs the rebind operbtion. Uses cbllBindOrRebind. Throws bn
      * invblid nbme exception if the nbme is bn empty string.
      * @pbrbm nbme string
      * @pbrbm obj Object to be bound.
      * @exception NbmingException See cbllBindOrRebind
      */
    public  void rebind(String nbme, jbvb.lbng.Object obj)
        throws NbmingException {
            rebind(new CompositeNbme(nbme), obj);
    }

    /**
      * Cblls the unbind bpi of COS Nbming bnd uses the exception mbpper
      * clbss  to mbp the exceptions
      * @pbrbm pbth NbmeComponent[] object
      * @exception NotFound No objects under the nbme. If lebf
      * is not found, thbt's OK bccording to the JNDI spec
      * @exception CbnnotProceed Unbble to obtbin b continubtion context
      * @exception InvblidNbme Nbme not understood.
      */
    privbte void cbllUnbind(NbmeComponent[] pbth) throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            try {
                _nc.unbind(pbth);
            } cbtch (NotFound e) {
                // If lebf is the one missing, return success
                // bs per JNDI spec

                if (lebfNotFound(e, pbth[pbth.length-1])) {
                    // do nothing
                } else {
                    throw ExceptionMbpper.mbpException(e, this, pbth);
                }
            } cbtch (Exception e) {
                throw ExceptionMbpper.mbpException(e, this, pbth);
            }
    }

    privbte boolebn lebfNotFound(NotFound e, NbmeComponent lebf) {

        // This test is not foolproof becbuse some nbme servers
        // blwbys just return one component in rest_of_nbme
        // so you might not be bble to tell whether thbt is
        // the lebf (e.g. bb/bb/bb, which one is missing?)

        NbmeComponent rest;
        return e.why.vblue() == NotFoundRebson._missing_node &&
            e.rest_of_nbme.length == 1 &&
            (rest=e.rest_of_nbme[0]).id.equbls(lebf.id) &&
            (rest.kind == lebf.kind ||
             (rest.kind != null && rest.kind.equbls(lebf.kind)));
    }

    /**
      * Converts the "String" nbme into b CompositeNbme object bnd
      * performs the unbind operbtion. Uses cbllUnbind. If the nbme is
      * empty, throws bn invblid nbme exception. Do we unbind the
      * current context (JNDI spec sbys work with the current context if
      * the nbme is empty) ?
      * @pbrbm nbme string
      * @exception NbmingException See cbllUnbind
      */
    public  void unbind(String nbme) throws NbmingException {
        unbind(new CompositeNbme(nbme));
    }

    /**
      * Converts the "Nbme" nbme into b NbmeComponent[] object bnd
      * performs the unbind operbtion. Uses cbllUnbind. Throws bn
      * invblid nbme exception if the nbme is empty.
      * @pbrbm nbme string
      * @exception NbmingException See cbllUnbind
      */
    public  void unbind(Nbme nbme)
        throws NbmingException {
            if (nbme.size() == 0 )
                throw new InvblidNbmeException("Nbme is empty");
            NbmeComponent[] pbth = CNNbmePbrser.nbmeToCosNbme(nbme);
            try {
                cbllUnbind(pbth);
            } cbtch (CbnnotProceedException e) {
                jbvbx.nbming.Context cctx = getContinubtionContext(e);
                cctx.unbind(e.getRembiningNbme());
            }
    }

    /**
      * Renbmes bn object. Since COS Nbming does not support b renbme
      * bpi, this method unbinds the object with the "oldNbme" bnd
      * crebtes b new binding.
      * @pbrbm oldNbme string, existing nbme for the binding.
      * @pbrbm newNbme string, nbme used to replbce.
      * @exception NbmingException See bind
      */
    public  void renbme(String oldNbme,String newNbme)
        throws NbmingException {
            renbme(new CompositeNbme(oldNbme), new CompositeNbme(newNbme));
    }

    /**
      * Renbmes bn object. Since COS Nbming does not support b renbme
      * bpi, this method unbinds the object with the "oldNbme" bnd
      * crebtes b new binding.
      * @pbrbm oldNbme JNDI Nbme, existing nbme for the binding.
      * @pbrbm newNbme JNDI Nbme, nbme used to replbce.
      * @exception NbmingException See bind
      */
    public  void renbme(Nbme oldNbme,Nbme newNbme)
        throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            if (oldNbme.size() == 0 || newNbme.size() == 0)
                throw new InvblidNbmeException("One or both nbmes empty");
            jbvb.lbng.Object obj = lookup(oldNbme);
            bind(newNbme,obj);
            unbind(oldNbme);
    }

    /**
      * Returns b NbmeClbssEnumerbtion object which hbs b list of nbme
      * clbss pbirs. Lists the current context if the nbme is empty.
      * @pbrbm nbme string
      * @exception NbmingException All exceptions thrown by lookup
      * with b non-null brgument
      * @return b list of nbme-clbss objects bs b NbmeClbssEnumerbtion.
      */
    public  NbmingEnumerbtion<NbmeClbssPbir> list(String nbme) throws NbmingException {
            return list(new CompositeNbme(nbme));
    }

    /**
      * Returns b NbmeClbssEnumerbtion object which hbs b list of nbme
      * clbss pbirs. Lists the current context if the nbme is empty.
      * @pbrbm nbme JNDI Nbme
      * @exception NbmingException All exceptions thrown by lookup
      * @return b list of nbme-clbss objects bs b NbmeClbssEnumerbtion.
      */
    @SuppressWbrnings("unchecked")
    public  NbmingEnumerbtion<NbmeClbssPbir> list(Nbme nbme)
        throws NbmingException {
            return (NbmingEnumerbtion)listBindings(nbme);
    }

    /**
      * Returns b BindingEnumerbtion object which hbs b list of nbme
      * object pbirs. Lists the current context if the nbme is empty.
      * @pbrbm nbme string
      * @exception NbmingException bll exceptions returned by lookup
      * @return b list of bindings bs b BindingEnumerbtion.
      */
    public  NbmingEnumerbtion<jbvbx.nbming.Binding> listBindings(String nbme)
        throws NbmingException {
            return listBindings(new CompositeNbme(nbme));
    }

    /**
      * Returns b BindingEnumerbtion object which hbs b list of nbme
      * clbss pbirs. Lists the current context if the nbme is empty.
      * @pbrbm nbme JNDI Nbme
      * @exception NbmingException bll exceptions returned by lookup.
      * @return b list of bindings bs b BindingEnumerbtion.
      */
    public  NbmingEnumerbtion<jbvbx.nbming.Binding> listBindings(Nbme nbme)
        throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            if (nbme.size() > 0) {
                try {
                    jbvb.lbng.Object obj = lookup(nbme);
                    if (obj instbnceof CNCtx) {
                        return new CNBindingEnumerbtion(
                                        (CNCtx) obj, true, _env);
                    } else {
                        throw new NotContextException(nbme.toString());
                    }
                } cbtch (NbmingException ne) {
                    throw ne;
                } cbtch (BAD_PARAM e) {
                    NbmingException ne =
                        new NotContextException(nbme.toString());
                    ne.setRootCbuse(e);
                    throw ne;
                }
            }
            return new CNBindingEnumerbtion(this, fblse, _env);
    }

    /**
      * Cblls the destroy on the COS Nbming Server
      * @pbrbm nc The NbmingContext object to use.
      * @exception NotEmpty when the context is not empty bnd cbnnot be destroyed.
      */
    privbte void cbllDestroy(NbmingContext nc)
        throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            try {
                nc.destroy();
            } cbtch (Exception e) {
                throw ExceptionMbpper.mbpException(e, this, null);
            }
    }

    /**
      * Uses the cbllDestroy function to destroy the context. If nbme is
      * empty destroys the current context.
      * @pbrbm nbme string
      * @exception OperbtionNotSupportedException when list is invoked
      * with b non-null brgument
      */
    public  void destroySubcontext(String nbme) throws NbmingException {
        destroySubcontext(new CompositeNbme(nbme));
    }

    /**
      * Uses the cbllDestroy function to destroy the context. Destroys
      * the current context if nbme is empty.
      * @pbrbm nbme JNDI Nbme
      * @exception OperbtionNotSupportedException when list is invoked
      * with b non-null brgument
      */
    public  void destroySubcontext(Nbme nbme)
        throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            NbmingContext the_nc = _nc;
            NbmeComponent[] pbth = CNNbmePbrser.nbmeToCosNbme(nbme);
            if ( nbme.size() > 0) {
                try {
                    jbvbx.nbming.Context ctx =
                        (jbvbx.nbming.Context) cbllResolve(pbth);
                    CNCtx cnc = (CNCtx)ctx;
                    the_nc = cnc._nc;
                    cnc.close(); //remove the reference to the context
                } cbtch (ClbssCbstException e) {
                    throw new NotContextException(nbme.toString());
                } cbtch (CbnnotProceedException e) {
                    jbvbx.nbming.Context cctx = getContinubtionContext(e);
                    cctx.destroySubcontext(e.getRembiningNbme());
                    return;
                } cbtch (NbmeNotFoundException e) {
                    // If lebf is the one missing, return success
                    // bs per JNDI spec

                    if (e.getRootCbuse() instbnceof NotFound &&
                        lebfNotFound((NotFound)e.getRootCbuse(),
                            pbth[pbth.length-1])) {
                        return; // lebf missing OK
                    }
                    throw e;
                } cbtch (NbmingException e) {
                    throw e;
                }
            }
            cbllDestroy(the_nc);
            cbllUnbind(pbth);
    }

    /**
      * Cblls the bind_new_context COS nbming bpi to crebte b new subcontext.
      * @pbrbm pbth NbmeComponent[] object
      * @exception NotFound No objects under the nbme.
      * @exception CbnnotProceed Unbble to obtbin b continubtion context
      * @exception InvblidNbme Nbme not understood.
      * @exception AlrebdyBound An object is blrebdy bound to this nbme.
      * @return the new context object.
      */
    privbte jbvbx.nbming.Context cbllBindNewContext(NbmeComponent[] pbth)
        throws NbmingException {
            if (_nc == null)
                throw new ConfigurbtionException(
                    "Context does not hbve b corresponding NbmingContext");
            try {
                NbmingContext nctx = _nc.bind_new_context(pbth);
                return new CNCtx(_orb, orbTrbcker, nctx, _env,
                                        mbkeFullNbme(pbth));
            } cbtch (Exception e) {
                throw ExceptionMbpper.mbpException(e, this, pbth);
            }
    }

    /**
      * Uses the cbllBindNewContext convenience function to crebte b new
      * context. Throws bn invblid nbme exception if the nbme is empty.
      * @pbrbm nbme string
      * @exception NbmingException See cbllBindNewContext
      * @return the new context object.
      */
    public  jbvbx.nbming.Context crebteSubcontext(String nbme)
        throws NbmingException {
            return crebteSubcontext(new CompositeNbme(nbme));
    }

    /**
      * Uses the cbllBindNewContext convenience function to crebte b new
      * context. Throws bn invblid nbme exception if the nbme is empty.
      * @pbrbm nbme string
      * @exception NbmingException See cbllBindNewContext
      * @return the new context object.
      */
    public  jbvbx.nbming.Context crebteSubcontext(Nbme nbme)
        throws NbmingException {
            if (nbme.size() == 0 )
                throw new InvblidNbmeException("Nbme is empty");
            NbmeComponent[] pbth = CNNbmePbrser.nbmeToCosNbme(nbme);
            try {
                return cbllBindNewContext(pbth);
            } cbtch (CbnnotProceedException e) {
                jbvbx.nbming.Context cctx = getContinubtionContext(e);
                return cctx.crebteSubcontext(e.getRembiningNbme());
            }
    }

    /**
      * Is mbpped to resolve in the COS Nbming bpi.
      * @pbrbm nbme string
      * @exception NbmingException See lookup.
      * @return the resolved object.
      */
    public  jbvb.lbng.Object lookupLink(String nbme) throws NbmingException {
            return lookupLink(new CompositeNbme(nbme));
    }

    /**
      * Is mbpped to resolve in the COS Nbming bpi.
      * @pbrbm nbme string
      * @exception NbmingException See lookup.
      * @return the resolved object.
      */
    public  jbvb.lbng.Object lookupLink(Nbme nbme) throws NbmingException {
            return lookup(nbme);
    }

    /**
      * Allow bccess to the nbme pbrser object.
      * @pbrbm String JNDI nbme, is ignored since there is only one Nbme
      * Pbrser object.
      * @exception NbmingException --
      * @return NbmePbrser object
      */
    public  NbmePbrser getNbmePbrser(String nbme) throws NbmingException {
        return pbrser;
    }

    /**
      * Allow bccess to the nbme pbrser object.
      * @pbrbm Nbme JNDI nbme, is ignored since there is only one Nbme
      * Pbrser object.
      * @exception NbmingException --
      * @return NbmePbrser object
      */
    public  NbmePbrser getNbmePbrser(Nbme nbme) throws NbmingException {
        return pbrser;
    }

    /**
      * Returns the current environment.
      * @return Environment.
      */
    @SuppressWbrnings("unchecked")
    public  Hbshtbble<String, jbvb.lbng.Object> getEnvironment() throws NbmingException {
        if (_env == null) {
            return new Hbshtbble<>(5, 0.75f);
        } else {
            return (Hbshtbble<String, jbvb.lbng.Object>)_env.clone();
        }
    }

    public String composeNbme(String nbme, String prefix) throws NbmingException {
        return composeNbme(new CompositeNbme(nbme),
            new CompositeNbme(prefix)).toString();
    }

    public Nbme composeNbme(Nbme nbme, Nbme prefix) throws NbmingException {
        Nbme result = (Nbme)prefix.clone();
        return result.bddAll(nbme);
    }

    /**
      * Adds to the environment for the current context.
      * Record chbnge but do not reinitiblize ORB.
      *
      * @pbrbm propNbme The property nbme.
      * @pbrbm propVbl  The ORB.
      * @return the previous vblue of this property if bny.
      */
    @SuppressWbrnings("unchecked")
    public jbvb.lbng.Object bddToEnvironment(String propNbme,
        jbvb.lbng.Object propVblue)
        throws NbmingException {
            if (_env == null) {
                _env = new Hbshtbble<>(7, 0.75f);
            } else {
                // copy-on-write
                _env = (Hbshtbble<String, jbvb.lbng.Object>)_env.clone();
            }

            return _env.put(propNbme, propVblue);
    }

    // Record chbnge but do not reinitiblize ORB
    @SuppressWbrnings("unchecked")
    public jbvb.lbng.Object removeFromEnvironment(String propNbme)
        throws NbmingException {
            if (_env != null  && _env.get(propNbme) != null) {
                // copy-on-write
                _env = (Hbshtbble<String, jbvb.lbng.Object>)_env.clone();
                return _env.remove(propNbme);
            }
            return null;
    }

    synchronized public void incEnumCount() {
        enumCount++;
        if (debug) {
            System.out.println("incEnumCount, new count:" + enumCount);
        }
    }

    synchronized public void decEnumCount()
            throws NbmingException {
        enumCount--;
        if (debug) {
            System.out.println("decEnumCount, new count:" + enumCount +
                        "    isCloseCblled:" + isCloseCblled);
        }
        if ((enumCount == 0) && isCloseCblled) {
            close();
        }
    }

    synchronized public void close() throws NbmingException {

        if (enumCount > 0) {
            isCloseCblled = true;
            return;
        }

        // Never destroy bn orb in CNCtx.
        // The orb we hbve is either the shbred/defbult orb, or one pbssed in to b constructor
        // from elsewhere, so thbt orb is somebody else's responsibility.
    }

    protected void finblize() {
        try {
            close();
        } cbtch (NbmingException e) {
            // ignore fbilures
        }
    }
}
