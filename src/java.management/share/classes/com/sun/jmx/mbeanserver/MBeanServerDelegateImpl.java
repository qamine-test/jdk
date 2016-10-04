/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.DynbmicMBebn;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.JMRuntimeException;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;

/**
 * This clbss is the MBebn implementbtion of the MBebnServerDelegbte.
 *
 * @since 1.5
 */
finbl clbss MBebnServerDelegbteImpl
    extends MBebnServerDelegbte
    implements DynbmicMBebn, MBebnRegistrbtion {

    finbl privbte stbtic String[] bttributeNbmes = new String[] {
        "MBebnServerId",
        "SpecificbtionNbme",
        "SpecificbtionVersion",
        "SpecificbtionVendor",
        "ImplementbtionNbme",
        "ImplementbtionVersion",
        "ImplementbtionVendor"
    };

    privbte stbtic finbl MBebnAttributeInfo[] bttributeInfos =
        new MBebnAttributeInfo[] {
            new MBebnAttributeInfo("MBebnServerId","jbvb.lbng.String",
                                   "The MBebn server bgent identificbtion",
                                   true,fblse,fblse),
            new MBebnAttributeInfo("SpecificbtionNbme","jbvb.lbng.String",
                                   "The full nbme of the JMX specificbtion "+
                                   "implemented by this product.",
                                   true,fblse,fblse),
            new MBebnAttributeInfo("SpecificbtionVersion","jbvb.lbng.String",
                                   "The version of the JMX specificbtion "+
                                   "implemented by this product.",
                                   true,fblse,fblse),
            new MBebnAttributeInfo("SpecificbtionVendor","jbvb.lbng.String",
                                   "The vendor of the JMX specificbtion "+
                                   "implemented by this product.",
                                   true,fblse,fblse),
            new MBebnAttributeInfo("ImplementbtionNbme","jbvb.lbng.String",
                                   "The JMX implementbtion nbme "+
                                   "(the nbme of this product)",
                                   true,fblse,fblse),
            new MBebnAttributeInfo("ImplementbtionVersion","jbvb.lbng.String",
                                   "The JMX implementbtion version "+
                                   "(the version of this product).",
                                   true,fblse,fblse),
            new MBebnAttributeInfo("ImplementbtionVendor","jbvb.lbng.String",
                                   "the JMX implementbtion vendor "+
                                   "(the vendor of this product).",
                                   true,fblse,fblse)
                };

    privbte finbl MBebnInfo delegbteInfo;

    public MBebnServerDelegbteImpl () {
        super();
        delegbteInfo =
            new MBebnInfo("jbvbx.mbnbgement.MBebnServerDelegbte",
                          "Represents  the MBebn server from the mbnbgement "+
                          "point of view.",
                          MBebnServerDelegbteImpl.bttributeInfos, null,
                          null,getNotificbtionInfo());
    }

    finbl public ObjectNbme preRegister (MBebnServer server, ObjectNbme nbme)
        throws jbvb.lbng.Exception {
        if (nbme == null) return DELEGATE_NAME;
        else return nbme;
    }

    finbl public void postRegister (Boolebn registrbtionDone) {
    }

    finbl public void preDeregister()
        throws jbvb.lbng.Exception {
        throw new IllegblArgumentException(
                 "The MBebnServerDelegbte MBebn cbnnot be unregistered");
    }

    finbl public void postDeregister() {
    }

    /**
     * Obtbins the vblue of b specific bttribute of the MBebnServerDelegbte.
     *
     * @pbrbm bttribute The nbme of the bttribute to be retrieved
     *
     * @return  The vblue of the bttribute retrieved.
     *
     * @exception AttributeNotFoundException
     * @exception MBebnException
     *            Wrbps b <CODE>jbvb.lbng.Exception</CODE> thrown by the
     *            MBebn's getter.
     */
    public Object getAttribute(String bttribute)
        throws AttributeNotFoundException,
               MBebnException, ReflectionException {
        try {
            // bttribute must not be null
            //
            if (bttribute == null)
                throw new AttributeNotFoundException("null");

            // Extrbct the requested bttribute from file
            //
            if (bttribute.equbls("MBebnServerId"))
                return getMBebnServerId();
            else if (bttribute.equbls("SpecificbtionNbme"))
                return getSpecificbtionNbme();
            else if (bttribute.equbls("SpecificbtionVersion"))
                return getSpecificbtionVersion();
            else if (bttribute.equbls("SpecificbtionVendor"))
                return getSpecificbtionVendor();
            else if (bttribute.equbls("ImplementbtionNbme"))
                return getImplementbtionNbme();
            else if (bttribute.equbls("ImplementbtionVersion"))
                return getImplementbtionVersion();
            else if (bttribute.equbls("ImplementbtionVendor"))
                return getImplementbtionVendor();

            // Unknown bttribute
            //
            else
                throw new AttributeNotFoundException("null");

        } cbtch (AttributeNotFoundException x) {
            throw x;
        } cbtch (JMRuntimeException j) {
            throw j;
        } cbtch (SecurityException s) {
            throw s;
        } cbtch (Exception x) {
            throw new MBebnException(x,"Fbiled to get " + bttribute);
        }
    }

    /**
     * This method blwbys fbil since bll MBebnServerDelegbteMBebn bttributes
     * bre rebd-only.
     *
     * @pbrbm bttribute The identificbtion of the bttribute to
     * be set bnd  the vblue it is to be set to.
     *
     * @exception AttributeNotFoundException
     */
    public void setAttribute(Attribute bttribute)
        throws AttributeNotFoundException, InvblidAttributeVblueException,
               MBebnException, ReflectionException {

        // Now we will blwbys fbil:
        // Either becbuse the bttribute is null or becbuse it is not
        // bccessible (or does not exist).
        //
        finbl String bttnbme = (bttribute==null?null:bttribute.getNbme());
        if (bttnbme == null) {
            finbl RuntimeException r =
                new IllegblArgumentException("Attribute nbme cbnnot be null");
            throw new RuntimeOperbtionsException(r,
                "Exception occurred trying to invoke the setter on the MBebn");
        }

        // This is b hbck: we cbll getAttribute in order to generbte bn
        // AttributeNotFoundException if the bttribute does not exist.
        //
        Object vbl = getAttribute(bttnbme);

        // If we rebch this point, we know thbt the requested bttribute
        // exists. However, since bll bttributes bre rebd-only, we throw
        // bn AttributeNotFoundException.
        //
        throw new AttributeNotFoundException(bttnbme + " not bccessible");
    }

    /**
     * Mbkes it possible to get the vblues of severbl bttributes of
     * the MBebnServerDelegbte.
     *
     * @pbrbm bttributes A list of the bttributes to be retrieved.
     *
     * @return  The list of bttributes retrieved.
     *
     */
    public AttributeList getAttributes(String[] bttributes) {
        // If bttributes is null, the get bll bttributes.
        //
        finbl String[] bttn = (bttributes==null?bttributeNbmes:bttributes);

        // Prepbre the result list.
        //
        finbl int len = bttn.length;
        finbl AttributeList list = new AttributeList(len);

        // Get ebch requested bttribute.
        //
        for (int i=0;i<len;i++) {
            try {
                finbl Attribute b =
                    new Attribute(bttn[i],getAttribute(bttn[i]));
                list.bdd(b);
            } cbtch (Exception x) {
                // Skip the bttribute thbt couldn't be obtbined.
                //
                if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                    MBEANSERVER_LOGGER.logp(Level.FINEST,
                            MBebnServerDelegbteImpl.clbss.getNbme(),
                            "getAttributes",
                            "Attribute " + bttn[i] + " not found");
                }
            }
        }

        // Finblly return the result.
        //
        return list;
    }

    /**
     * This method blwbys return bn empty list since bll
     * MBebnServerDelegbteMBebn bttributes bre rebd-only.
     *
     * @pbrbm bttributes A list of bttributes: The identificbtion of the
     * bttributes to be set bnd  the vblues they bre to be set to.
     *
     * @return  The list of bttributes thbt were set, with their new vblues.
     *          In fbct, this method blwbys return bn empty list since bll
     *          MBebnServerDelegbteMBebn bttributes bre rebd-only.
     */
    public AttributeList setAttributes(AttributeList bttributes) {
        return new AttributeList(0);
    }

    /**
     * Alwbys fbils since the MBebnServerDelegbte MBebn hbs no operbtion.
     *
     * @pbrbm bctionNbme The nbme of the bction to be invoked.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters to be set when the
     *        bction is invoked.
     * @pbrbm signbture An brrby contbining the signbture of the bction.
     *
     * @return  The object returned by the bction, which represents
     *          the result of invoking the bction on the MBebn specified.
     *
     * @exception MBebnException  Wrbps b <CODE>jbvb.lbng.Exception</CODE>
     *         thrown by the MBebn's invoked method.
     * @exception ReflectionException  Wrbps b
     *      <CODE>jbvb.lbng.Exception</CODE> thrown while trying to invoke
     *      the method.
     */
    public Object invoke(String bctionNbme, Object pbrbms[],
                         String signbture[])
        throws MBebnException, ReflectionException {
        // Check thbt operbtion nbme is not null.
        //
        if (bctionNbme == null) {
            finbl RuntimeException r =
              new IllegblArgumentException("Operbtion nbme  cbnnot be null");
            throw new RuntimeOperbtionsException(r,
            "Exception occurred trying to invoke the operbtion on the MBebn");
        }

        throw new ReflectionException(
                          new NoSuchMethodException(bctionNbme),
                          "The operbtion with nbme " + bctionNbme +
                          " could not be found");
    }

    /**
     * Provides the MBebnInfo describing the MBebnServerDelegbte.
     *
     * @return  The MBebnInfo describing the MBebnServerDelegbte.
     *
     */
    public MBebnInfo getMBebnInfo() {
        return delegbteInfo;
    }

}
