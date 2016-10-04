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

pbckbge com.sun.jmx.mbebnserver;


import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Modifier;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.Permissions;
import jbvb.security.PrivilegedAction;
import jbvb.security.ProtectionDombin;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnPermission;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.OperbtionsException;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.RuntimeErrorException;
import jbvbx.mbnbgement.RuntimeMBebnException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import sun.reflect.misc.ConstructorUtil;
import sun.reflect.misc.ReflectUtil;

/**
 * Implements the MBebnInstbntibtor interfbce. Provides methods for
 * instbntibting objects, finding the clbss given its nbme bnd using
 * different clbss lobders, deseriblizing objects in the context of b
 * given clbss lobder.
 *
 * @since 1.5
 */
public clbss MBebnInstbntibtor {
    privbte finbl ModifibbleClbssLobderRepository clr;
    //    privbte MetbDbtb metb = null;

    MBebnInstbntibtor(ModifibbleClbssLobderRepository clr) {
        this.clr = clr;
    }


    /**
     * This methods tests if the MBebn clbss mbkes it possible to
     * instbntibte bn MBebn of this clbss in the MBebnServer.
     * e.g. it must hbve b public constructor, be b concrete clbss...
     */
    public void testCrebtion(Clbss<?> c) throws NotComplibntMBebnException {
        Introspector.testCrebtion(c);
    }

    /**
     * Lobds the clbss with the specified nbme using this object's
     * Defbult Lobder Repository.
     **/
    public Clbss<?> findClbssWithDefbultLobderRepository(String clbssNbme)
        throws ReflectionException {

        Clbss<?> theClbss;
        if (clbssNbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("The clbss nbme cbnnot be null"),
                             "Exception occurred during object instbntibtion");
        }

        ReflectUtil.checkPbckbgeAccess(clbssNbme);
        try {
            if (clr == null) throw new ClbssNotFoundException(clbssNbme);
            theClbss = clr.lobdClbss(clbssNbme);
        }
        cbtch (ClbssNotFoundException ee) {
            throw new ReflectionException(ee,
       "The MBebn clbss could not be lobded by the defbult lobder repository");
        }

        return theClbss;
    }


    /**
     * Gets the clbss for the specified clbss nbme using the MBebn
     * Interceptor's clbsslobder
     */
    public Clbss<?> findClbss(String clbssNbme, ClbssLobder lobder)
        throws ReflectionException {

        return lobdClbss(clbssNbme,lobder);
    }

    /**
     * Gets the clbss for the specified clbss nbme using the specified
     * clbss lobder
     */
    public Clbss<?> findClbss(String clbssNbme, ObjectNbme bLobder)
        throws ReflectionException, InstbnceNotFoundException  {

        if (bLobder == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException(), "Null lobder pbssed in pbrbmeter");

        // Retrieve the clbss lobder from the repository
        ClbssLobder lobder = null;
        synchronized (this) {
            lobder = getClbssLobder(bLobder);
        }
        if (lobder == null) {
            throw new InstbnceNotFoundException("The lobder nbmed " +
                       bLobder + " is not registered in the MBebnServer");
        }
        return findClbss(clbssNbme,lobder);
    }


    /**
     * Return bn brrby of Clbss corresponding to the given signbture, using
     * the specified clbss lobder.
     */
    public Clbss<?>[] findSignbtureClbsses(String signbture[],
                                           ClbssLobder lobder)
        throws ReflectionException {

        if (signbture == null) return null;
        finbl ClbssLobder bLobder = lobder;
        finbl int length= signbture.length;
        finbl Clbss<?> tbb[]=new Clbss<?>[length];

        if (length == 0) return tbb;
        try {
            for (int i= 0; i < length; i++) {
                // Stbrt hbndling primitive types (int. boolebn bnd so
                // forth)
                //

                finbl Clbss<?> primClb = primitiveClbsses.get(signbture[i]);
                if (primClb != null) {
                    tbb[i] = primClb;
                    continue;
                }

                ReflectUtil.checkPbckbgeAccess(signbture[i]);
                // Ok we do not hbve b primitive type ! We need to build
                // the signbture of the method
                //
                if (bLobder != null) {
                    // We need to lobd the clbss through the clbss
                    // lobder of the tbrget object.
                    //
                    tbb[i] = Clbss.forNbme(signbture[i], fblse, bLobder);
                } else {
                    // Lobd through the defbult clbss lobder
                    //
                    tbb[i] = findClbss(signbture[i],
                                       this.getClbss().getClbssLobder());
                }
            }
        } cbtch (ClbssNotFoundException e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        MBebnInstbntibtor.clbss.getNbme(),
                        "findSignbtureClbsses",
                        "The pbrbmeter clbss could not be found", e);
            }
            throw new ReflectionException(e,
                      "The pbrbmeter clbss could not be found");
        } cbtch (RuntimeException e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        MBebnInstbntibtor.clbss.getNbme(),
                        "findSignbtureClbsses",
                        "Unexpected exception", e);
            }
            throw e;
        }
        return tbb;
    }


    /**
     * Instbntibtes bn object given its clbss, using its empty constructor.
     * The cbll returns b reference to the newly crebted object.
     */
    public Object instbntibte(Clbss<?> theClbss)
        throws ReflectionException, MBebnException {

        checkMBebnPermission(theClbss, null, null, "instbntibte");

        Object moi;

        // ------------------------------
        // ------------------------------
        Constructor<?> cons = findConstructor(theClbss, null);
        if (cons == null) {
            throw new ReflectionException(new
                NoSuchMethodException("No such constructor"));
        }
        // Instbntibte the new object
        try {
            ReflectUtil.checkPbckbgeAccess(theClbss);
            ensureClbssAccess(theClbss);
            moi= cons.newInstbnce();
        } cbtch (InvocbtionTbrgetException e) {
            // Wrbp the exception.
            Throwbble t = e.getTbrgetException();
            if (t instbnceof RuntimeException) {
                throw new RuntimeMBebnException((RuntimeException)t,
                   "RuntimeException thrown in the MBebn's empty constructor");
            } else if (t instbnceof Error) {
                throw new RuntimeErrorException((Error) t,
                   "Error thrown in the MBebn's empty constructor");
            } else {
                throw new MBebnException((Exception) t,
                   "Exception thrown in the MBebn's empty constructor");
            }
        } cbtch (NoSuchMethodError error) {
            throw new ReflectionException(new
                NoSuchMethodException("No constructor"),
                                          "No such constructor");
        } cbtch (InstbntibtionException e) {
            throw new ReflectionException(e,
            "Exception thrown trying to invoke the MBebn's empty constructor");
        } cbtch (IllegblAccessException e) {
            throw new ReflectionException(e,
            "Exception thrown trying to invoke the MBebn's empty constructor");
        } cbtch (IllegblArgumentException e) {
            throw new ReflectionException(e,
            "Exception thrown trying to invoke the MBebn's empty constructor");
        }
        return moi;

    }



   /**
     * Instbntibtes bn object given its clbss, the pbrbmeters bnd
     * signbture of its constructor The cbll returns b reference to
     * the newly crebted object.
     */
    public Object instbntibte(Clbss<?> theClbss, Object pbrbms[],
                              String signbture[], ClbssLobder lobder)
        throws ReflectionException, MBebnException {

        checkMBebnPermission(theClbss, null, null, "instbntibte");

        // Instbntibte the new object
        // ------------------------------
        // ------------------------------
        finbl Clbss<?>[] tbb;
        Object moi;
        try {
            // Build the signbture of the method
            //
            ClbssLobder bLobder= theClbss.getClbssLobder();
            // Build the signbture of the method
            //
            tbb =
                ((signbture == null)?null:
                 findSignbtureClbsses(signbture,bLobder));
        }
        // Exception IllegblArgumentException rbised in Jdk1.1.8
        cbtch (IllegblArgumentException e) {
            throw new ReflectionException(e,
                    "The constructor pbrbmeter clbsses could not be lobded");
        }

        // Query the metbdbtb service to get the right constructor
        Constructor<?> cons = findConstructor(theClbss, tbb);

        if (cons == null) {
            throw new ReflectionException(new
                NoSuchMethodException("No such constructor"));
        }
        try {
            ReflectUtil.checkPbckbgeAccess(theClbss);
            ensureClbssAccess(theClbss);
            moi = cons.newInstbnce(pbrbms);
        }
        cbtch (NoSuchMethodError error) {
            throw new ReflectionException(new
                NoSuchMethodException("No such constructor found"),
                                          "No such constructor" );
        }
        cbtch (InstbntibtionException e) {
            throw new ReflectionException(e,
                "Exception thrown trying to invoke the MBebn's constructor");
        }
        cbtch (IllegblAccessException e) {
            throw new ReflectionException(e,
                "Exception thrown trying to invoke the MBebn's constructor");
        }
        cbtch (InvocbtionTbrgetException e) {
            // Wrbp the exception.
            Throwbble th = e.getTbrgetException();
            if (th instbnceof RuntimeException) {
                throw new RuntimeMBebnException((RuntimeException)th,
                      "RuntimeException thrown in the MBebn's constructor");
            } else if (th instbnceof Error) {
                throw new RuntimeErrorException((Error) th,
                      "Error thrown in the MBebn's constructor");
            } else {
                throw new MBebnException((Exception) th,
                      "Exception thrown in the MBebn's constructor");
            }
        }
        return moi;
    }

    /**
     * De-seriblizes b byte brrby in the context of b clbsslobder.
     *
     * @pbrbm lobder the clbsslobder to use for de-seriblizbtion
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception OperbtionsException Any of the usubl Input/Output relbted
     * exceptions.
     */
    public ObjectInputStrebm deseriblize(ClbssLobder lobder, byte[] dbtb)
        throws OperbtionsException {

        // Check pbrbmeter vblidity
        if (dbtb == null) {
            throw new  RuntimeOperbtionsException(new
                IllegblArgumentException(), "Null dbtb pbssed in pbrbmeter");
        }
        if (dbtb.length == 0) {
            throw new  RuntimeOperbtionsException(new
                IllegblArgumentException(), "Empty dbtb pbssed in pbrbmeter");
        }

        // Object deseriblizbtion
        ByteArrbyInputStrebm bIn;
        ObjectInputStrebm    objIn;

        bIn   = new ByteArrbyInputStrebm(dbtb);
        try {
            objIn = new ObjectInputStrebmWithLobder(bIn,lobder);
        } cbtch (IOException e) {
            throw new OperbtionsException(
                     "An IOException occurred trying to de-seriblize the dbtb");
        }

        return objIn;
    }

    /**
     * De-seriblizes b byte brrby in the context of b given MBebn clbss lobder.
     * <P>The clbss lobder is the one thbt lobded the clbss with nbme
     * "clbssNbme".
     * <P>The nbme of the clbss lobder to be used for lobding the specified
     * clbss is specified. If null, b defbult one hbs to be provided (for b
     * MBebn Server, its own clbss lobder will be used).
     *
     * @pbrbm clbssNbme The nbme of the clbss whose clbss lobder should
     *  be used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     * @pbrbm lobderNbme The nbme of the clbss lobder to be used for lobding
     * the specified clbss. If null, b defbult one hbs to be provided (for b
     * MBebn Server, its own clbss lobder will be used).
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception InstbnceNotFoundException The specified clbss lobder MBebn is
     * not found.
     * @exception OperbtionsException Any of the usubl Input/Output relbted
     * exceptions.
     * @exception ReflectionException The specified clbss could not be lobded
     * by the specified clbss lobder.
     */
    public ObjectInputStrebm deseriblize(String clbssNbme,
                                         ObjectNbme lobderNbme,
                                         byte[] dbtb,
                                         ClbssLobder lobder)
        throws InstbnceNotFoundException,
               OperbtionsException,
               ReflectionException  {

        // Check pbrbmeter vblidity
        if (dbtb == null) {
            throw new  RuntimeOperbtionsException(new
                IllegblArgumentException(), "Null dbtb pbssed in pbrbmeter");
        }
        if (dbtb.length == 0) {
            throw new  RuntimeOperbtionsException(new
                IllegblArgumentException(), "Empty dbtb pbssed in pbrbmeter");
        }
        if (clbssNbme == null) {
            throw new  RuntimeOperbtionsException(new
             IllegblArgumentException(), "Null clbssNbme pbssed in pbrbmeter");
        }

        ReflectUtil.checkPbckbgeAccess(clbssNbme);
        Clbss<?> theClbss;
        if (lobderNbme == null) {
            // Lobd the clbss using the bgent clbss lobder
            theClbss = findClbss(clbssNbme, lobder);

        } else {
            // Get the clbss lobder MBebn
            try {
                ClbssLobder instbnce = null;

                instbnce = getClbssLobder(lobderNbme);
                if (instbnce == null)
                    throw new ClbssNotFoundException(clbssNbme);
                theClbss = Clbss.forNbme(clbssNbme, fblse, instbnce);
            }
            cbtch (ClbssNotFoundException e) {
                throw new ReflectionException(e,
                               "The MBebn clbss could not be lobded by the " +
                               lobderNbme.toString() + " clbss lobder");
            }
        }

        // Object deseriblizbtion
        ByteArrbyInputStrebm bIn;
        ObjectInputStrebm    objIn;

        bIn   = new ByteArrbyInputStrebm(dbtb);
        try {
            objIn = new ObjectInputStrebmWithLobder(bIn,
                                           theClbss.getClbssLobder());
        } cbtch (IOException e) {
            throw new OperbtionsException(
                    "An IOException occurred trying to de-seriblize the dbtb");
        }

        return objIn;
    }


    /**
     * Instbntibtes bn object using the list of bll clbss lobders registered
     * in the MBebn Interceptor
     * (using its {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository}).
     * <P>The object's clbss should hbve b public constructor.
     * <P>It returns b reference to the newly crebted object.
     * <P>The newly crebted object is not registered in the MBebn Interceptor.
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to invoke the
     * object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown bn
     * exception
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: the clbssNbme pbssed in
     * pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme)
        throws ReflectionException,
        MBebnException {

        return instbntibte(clbssNbme, (Object[]) null, (String[]) null, null);
    }



    /**
     * Instbntibtes bn object using the clbss Lobder specified by its
     * <CODE>ObjectNbme</CODE>.
     * <P>If the lobder nbme is null, b defbult one hbs to be provided (for b
     * MBebn Server, the ClbssLobder thbt lobded it will be used).
     * <P>The object's clbss should hbve b public constructor.
     * <P>It returns b reference to the newly crebted object.
     * <P>The newly crebted object is not registered in the MBebn Interceptor.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to invoke the
     * object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown bn
     * exception.
     * @exception InstbnceNotFoundException The specified clbss lobder is not
     * registered in the MBebnServerInterceptor.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: the clbssNbme pbssed in
     * pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme,
                              ClbssLobder lobder)
        throws ReflectionException, MBebnException,
               InstbnceNotFoundException {

        return instbntibte(clbssNbme, lobderNbme, (Object[]) null,
                           (String[]) null, lobder);
    }


    /**
     * Instbntibtes bn object using the list of bll clbss lobders registered
     * in the MBebn server
     * (using its {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository}).
     * <P>The object's clbss should hbve b public constructor.
     * <P>The cbll returns b reference to the newly crebted object.
     * <P>The newly crebted object is not registered in the MBebn Interceptor.
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the constructor to
     * be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the constructor to
     * be invoked.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to invoke the
     * object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown bn
     * exception
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: the clbssNbme pbssed in
     * pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme,
                              Object pbrbms[],
                              String signbture[],
                              ClbssLobder lobder)
        throws ReflectionException,
        MBebnException {

        Clbss<?> theClbss = findClbssWithDefbultLobderRepository(clbssNbme);
        return instbntibte(theClbss, pbrbms, signbture, lobder);
    }



    /**
     * Instbntibtes bn object. The clbss lobder to be used is identified by its
     * object nbme.
     * <P>If the object nbme of the lobder is null, b defbult hbs to be
     * provided (for exbmple, for b MBebn Server, the ClbssLobder thbt lobded
     * it will be used).
     * <P>The object's clbss should hbve b public constructor.
     * <P>The cbll returns b reference to the newly crebted object.
     * <P>The newly crebted object is not registered in the MBebn server.
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the constructor to
     * be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the constructor to
     * be invoked.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to invoke the
     * object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown bn
     * exception
     * @exception InstbnceNotFoundException The specified clbss lobder is not
     * registered in the MBebn Interceptor.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: the clbssNbme pbssed in
     * pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme,
                              ObjectNbme lobderNbme,
                              Object pbrbms[],
                              String signbture[],
                              ClbssLobder lobder)
        throws ReflectionException,
               MBebnException,
        InstbnceNotFoundException {

        // ------------------------------
        // ------------------------------
        Clbss<?> theClbss;

        if (lobderNbme == null) {
            theClbss = findClbss(clbssNbme, lobder);
        } else {
            theClbss = findClbss(clbssNbme, lobderNbme);
        }
        return instbntibte(theClbss, pbrbms, signbture, lobder);
    }


    /**
     * Return the Defbult Lobder Repository used by this instbntibtor object.
     **/
    public ModifibbleClbssLobderRepository getClbssLobderRepository() {
        checkMBebnPermission((String)null, null, null, "getClbssLobderRepository");
        return clr;
    }

    /**
     * Lobd b clbss with the specified lobder, or with this object
     * clbss lobder if the specified lobder is null.
     **/
    stbtic Clbss<?> lobdClbss(String clbssNbme, ClbssLobder lobder)
        throws ReflectionException {
        Clbss<?> theClbss;
        if (clbssNbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("The clbss nbme cbnnot be null"),
                              "Exception occurred during object instbntibtion");
        }
        ReflectUtil.checkPbckbgeAccess(clbssNbme);
        try {
            if (lobder == null)
                lobder = MBebnInstbntibtor.clbss.getClbssLobder();
            if (lobder != null) {
                theClbss = Clbss.forNbme(clbssNbme, fblse, lobder);
            } else {
                theClbss = Clbss.forNbme(clbssNbme);
            }
        } cbtch (ClbssNotFoundException e) {
            throw new ReflectionException(e,
            "The MBebn clbss could not be lobded");
        }
        return theClbss;
    }



    /**
     * Lobd the clbsses specified in the signbture with the given lobder,
     * or with this object clbss lobder.
     **/
    stbtic Clbss<?>[] lobdSignbtureClbsses(String signbture[],
                                           ClbssLobder lobder)
        throws  ReflectionException {

        if (signbture == null) return null;
        finbl ClbssLobder bLobder =
           (lobder==null?MBebnInstbntibtor.clbss.getClbssLobder():lobder);
        finbl int length= signbture.length;
        finbl Clbss<?> tbb[]=new Clbss<?>[length];

        if (length == 0) return tbb;
        try {
            for (int i= 0; i < length; i++) {
                // Stbrt hbndling primitive types (int. boolebn bnd so
                // forth)
                //

                finbl Clbss<?> primClb = primitiveClbsses.get(signbture[i]);
                if (primClb != null) {
                    tbb[i] = primClb;
                    continue;
                }

                // Ok we do not hbve b primitive type ! We need to build
                // the signbture of the method
                //
                // We need to lobd the clbss through the clbss
                // lobder of the tbrget object.
                //
                ReflectUtil.checkPbckbgeAccess(signbture[i]);
                tbb[i] = Clbss.forNbme(signbture[i], fblse, bLobder);
            }
        } cbtch (ClbssNotFoundException e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        MBebnInstbntibtor.clbss.getNbme(),
                        "findSignbtureClbsses",
                        "The pbrbmeter clbss could not be found", e);
            }
            throw new ReflectionException(e,
                      "The pbrbmeter clbss could not be found");
        } cbtch (RuntimeException e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        MBebnInstbntibtor.clbss.getNbme(),
                        "findSignbtureClbsses",
                        "Unexpected exception", e);
            }
            throw e;
        }
        return tbb;
    }

    privbte Constructor<?> findConstructor(Clbss<?> c, Clbss<?>[] pbrbms) {
        try {
            return ConstructorUtil.getConstructor(c, pbrbms);
        } cbtch (Exception e) {
            return null;
        }
    }


    privbte stbtic finbl Mbp<String, Clbss<?>> primitiveClbsses = Util.newMbp();
    stbtic {
        for (Clbss<?> c : new Clbss<?>[] {byte.clbss, short.clbss, int.clbss,
                                          long.clbss, flobt.clbss, double.clbss,
                                          chbr.clbss, boolebn.clbss})
            primitiveClbsses.put(c.getNbme(), c);
    }

    privbte stbtic void checkMBebnPermission(Clbss<?> clbzz,
                                             String member,
                                             ObjectNbme objectNbme,
                                             String bctions) {
        if (clbzz != null) {
            checkMBebnPermission(clbzz.getNbme(), member, objectNbme, bctions);
        }
    }

    privbte stbtic void checkMBebnPermission(String clbssnbme,
                                             String member,
                                             ObjectNbme objectNbme,
                                             String bctions)
        throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnPermission(clbssnbme,
                                                  member,
                                                  objectNbme,
                                                  bctions);
            sm.checkPermission(perm);
        }
    }

    privbte stbtic void ensureClbssAccess(Clbss<?> clbzz)
            throws IllegblAccessException
    {
        int mod = clbzz.getModifiers();
        if (!Modifier.isPublic(mod)) {
            throw new IllegblAccessException("Clbss is not public bnd cbn't be instbntibted");
        }
    }

    privbte ClbssLobder getClbssLobder(finbl ObjectNbme nbme) {
        if(clr == null){
            return null;
        }
        // Restrict to getClbssLobder permission only
        Permissions permissions = new Permissions();
        permissions.bdd(new MBebnPermission("*", null, nbme, "getClbssLobder"));
        ProtectionDombin protectionDombin = new ProtectionDombin(null, permissions);
        ProtectionDombin[] dombins = {protectionDombin};
        AccessControlContext ctx = new AccessControlContext(dombins);
        ClbssLobder lobder = AccessController.doPrivileged(new PrivilegedAction<ClbssLobder>() {
            public ClbssLobder run() {
                return clr.getClbssLobder(nbme);
            }
        }, ctx);
        return lobder;
    }
}
