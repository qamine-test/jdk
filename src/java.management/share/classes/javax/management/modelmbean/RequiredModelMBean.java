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
/*
 * @buthor    IBM Corp.
 *
 * Copyright IBM Corp. 1999-2000.  All rights reserved.
 */


pbckbge jbvbx.mbnbgement.modelmbebn;

/* jbvb imports */

import stbtic com.sun.jmx.defbults.JmxProperties.MODELMBEAN_LOGGER;
import jbvb.io.FileOutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

import jbvb.lbng.reflect.Method;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.logging.Level;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jbvb.util.Vector;
import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.AttributeChbngeNotificbtion;
import jbvbx.mbnbgement.AttributeChbngeNotificbtionFilter;
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerFbctory;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.RuntimeErrorException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvbx.mbnbgement.ServiceNotFoundException;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;
import sun.misc.JbvbSecurityAccess;
import sun.misc.ShbredSecrets;

import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

/**
 * This clbss is the implementbtion of b ModelMBebn. An bppropribte
 * implementbtion of b ModelMBebn must be shipped with every JMX Agent
 * bnd the clbss must be nbmed RequiredModelMBebn.
 * <P>
 * Jbvb resources wishing to be mbnbgebble instbntibte the
 * RequiredModelMBebn using the MBebnServer's crebteMBebn method.
 * The resource then sets the MBebnInfo bnd Descriptors for the
 * RequiredModelMBebn instbnce. The bttributes bnd operbtions exposed
 * vib the ModelMBebnInfo for the ModelMBebn bre bccessible
 * from MBebns, connectors/bdbptors like other MBebns. Through the
 * Descriptors, vblues bnd methods in the mbnbged bpplicbtion cbn be
 * defined bnd mbpped to bttributes bnd operbtions of the ModelMBebn.
 * This mbpping cbn be defined in bn XML formbtted file or dynbmicblly bnd
 * progrbmmbticblly bt runtime.
 * <P>
 * Every RequiredModelMBebn which is instbntibted in the MBebnServer
 * becomes mbnbgebble:<br>
 * its bttributes bnd operbtions become remotely bccessible through the
 * connectors/bdbptors connected to thbt MBebnServer.
 * <P>
 * A Jbvb object cbnnot be registered in the MBebnServer unless it is b
 * JMX complibnt MBebn. By instbntibting b RequiredModelMBebn, resources
 * bre gubrbnteed thbt the MBebn is vblid.
 *
 * MBebnException bnd RuntimeOperbtionsException must be thrown on every
 * public method.  This bllows for wrbpping exceptions from distributed
 * communicbtions (RMI, EJB, etc.)
 *
 * @since 1.5
 */

public clbss RequiredModelMBebn
    implements ModelMBebn, MBebnRegistrbtion, NotificbtionEmitter {

    /*************************************/
    /* bttributes                        */
    /*************************************/
    ModelMBebnInfo modelMBebnInfo;

    /* Notificbtion brobdcbster for bny notificbtion to be sent
     * from the bpplicbtion through the RequiredModelMBebn.  */
    privbte NotificbtionBrobdcbsterSupport generblBrobdcbster = null;

    /* Notificbtion brobdcbster for bttribute chbnge notificbtions */
    privbte NotificbtionBrobdcbsterSupport bttributeBrobdcbster = null;

    /* hbndle, nbme, or reference for instbnce on which the bctubl invoke
     * bnd operbtions will be executed */
    privbte Object mbnbgedResource = null;


    /* records the registering in MBebnServer */
    privbte boolebn registered = fblse;
    privbte trbnsient MBebnServer server = null;

    privbte finbl stbtic JbvbSecurityAccess jbvbSecurityAccess = ShbredSecrets.getJbvbSecurityAccess();
    finbl privbte AccessControlContext bcc = AccessController.getContext();

    /*************************************/
    /* constructors                      */
    /*************************************/

    /**
     * Constructs bn <CODE>RequiredModelMBebn</CODE> with bn empty
     * ModelMBebnInfo.
     * <P>
     * The RequiredModelMBebn's MBebnInfo bnd Descriptors
     * cbn be customized using the {@link #setModelMBebnInfo} method.
     * After the RequiredModelMBebn's MBebnInfo bnd Descriptors bre
     * customized, the RequiredModelMBebn cbn be registered with
     * the MBebnServer.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     *
     * @exception RuntimeOperbtionsException Wrbps b {@link
     * RuntimeException} during the construction of the object.
     **/
    public RequiredModelMBebn()
        throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    "RequiredModelMBebn()", "Entry");
        }
        modelMBebnInfo = crebteDefbultModelMBebnInfo();
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    "RequiredModelMBebn()", "Exit");
        }
    }

    /**
     * Constructs b RequiredModelMBebn object using ModelMBebnInfo pbssed in.
     * As long bs the RequiredModelMBebn is not registered
     * with the MBebnServer yet, the RequiredModelMBebn's MBebnInfo bnd
     * Descriptors cbn be customized using the {@link #setModelMBebnInfo}
     * method.
     * After the RequiredModelMBebn's MBebnInfo bnd Descriptors bre
     * customized, the RequiredModelMBebn cbn be registered with the
     * MBebnServer.
     *
     * @pbrbm mbi The ModelMBebnInfo object to be used by the
     *            RequiredModelMBebn. The given ModelMBebnInfo is cloned
     *            bnd modified bs specified by {@link #setModelMBebnInfo}
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn
     *    {link jbvb.lbng.IllegblArgumentException}:
     *          The MBebnInfo pbssed in pbrbmeter is null.
     *
     **/
    public RequiredModelMBebn(ModelMBebnInfo mbi)
        throws MBebnException, RuntimeOperbtionsException {

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    "RequiredModelMBebn(MBebnInfo)", "Entry");
        }
        setModelMBebnInfo(mbi);

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    "RequiredModelMBebn(MBebnInfo)", "Exit");
        }
    }


    /*************************************/
    /* initiblizers                      */
    /*************************************/

    /**
     * Initiblizes b ModelMBebn object using ModelMBebnInfo pbssed in.
     * This method mbkes it possible to set b customized ModelMBebnInfo on
     * the ModelMBebn bs long bs it is not registered with the MBebnServer.
     * <br>
     * Once the ModelMBebn's ModelMBebnInfo (with Descriptors) bre
     * customized bnd set on the ModelMBebn, the  ModelMBebn be
     * registered with the MBebnServer.
     * <P>
     * If the ModelMBebn is currently registered, this method throws
     * b {@link jbvbx.mbnbgement.RuntimeOperbtionsException} wrbpping bn
     * {@link IllegblStbteException}
     * <P>
     * If the given <vbr>inModelMBebnInfo</vbr> does not contbin bny
     * {@link ModelMBebnNotificbtionInfo} for the <code>GENERIC</code>
     * or <code>ATTRIBUTE_CHANGE</code> notificbtions, then the
     * RequiredModelMBebn will supply its own defbult
     * {@link ModelMBebnNotificbtionInfo ModelMBebnNotificbtionInfo}s for
     * those missing notificbtions.
     *
     * @pbrbm mbi The ModelMBebnInfo object to be used
     *        by the ModelMBebn.
     *
     * @exception MBebnException Wrbps b distributed communicbtion
     *        Exception.
     * @exception RuntimeOperbtionsException
     * <ul><li>Wrbps bn {@link IllegblArgumentException} if
     *         the MBebnInfo pbssed in pbrbmeter is null.</li>
     *     <li>Wrbps bn {@link IllegblStbteException} if the ModelMBebn
     *         is currently registered in the MBebnServer.</li>
     * </ul>
     *
     **/
    public void setModelMBebnInfo(ModelMBebnInfo mbi)
        throws MBebnException, RuntimeOperbtionsException {

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setModelMBebnInfo(ModelMBebnInfo)","Entry");
        }

        if (mbi == null) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                    "setModelMBebnInfo(ModelMBebnInfo)",
                    "ModelMBebnInfo is null: Rbising exception.");
            }
            finbl RuntimeException x = new
                IllegblArgumentException("ModelMBebnInfo must not be null");
            finbl String exceptionText =
                "Exception occurred trying to initiblize the " +
                "ModelMBebnInfo of the RequiredModelMBebn";
            throw new RuntimeOperbtionsException(x,exceptionText);
        }

        if (registered) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                    "setModelMBebnInfo(ModelMBebnInfo)",
                    "RequiredMBebn is registered: Rbising exception.");
            }
            finbl String exceptionText =
                "Exception occurred trying to set the " +
                "ModelMBebnInfo of the RequiredModelMBebn";
            finbl RuntimeException x = new IllegblStbteException(
             "cbnnot cbll setModelMBebnInfo while ModelMBebn is registered");
            throw new RuntimeOperbtionsException(x,exceptionText);
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setModelMBebnInfo(ModelMBebnInfo)",
                "Setting ModelMBebnInfo to " + printModelMBebnInfo(mbi));
            int noOfNotificbtions = 0;
            if (mbi.getNotificbtions() != null) {
                noOfNotificbtions = mbi.getNotificbtions().length;
            }
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setModelMBebnInfo(ModelMBebnInfo)",
                "ModelMBebnInfo notificbtions hbs " +
                noOfNotificbtions + " elements");
        }

        modelMBebnInfo = (ModelMBebnInfo)mbi.clone();

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setModelMBebnInfo(ModelMBebnInfo)","set mbebnInfo to: "+
                 printModelMBebnInfo(modelMBebnInfo));
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setModelMBebnInfo(ModelMBebnInfo)","Exit");
        }
    }


    /**
     * Sets the instbnce hbndle of the object bgbinst which to
     * execute bll methods in this ModelMBebn mbnbgement interfbce
     * (MBebnInfo bnd Descriptors).
     *
     * @pbrbm mr Object thbt is the mbnbged resource
     * @pbrbm mr_type The type of reference for the mbnbged resource.
     *     <br>Cbn be: "ObjectReference", "Hbndle", "IOR", "EJBHbndle",
     *         or "RMIReference".
     *     <br>In this implementbtion only "ObjectReference" is supported.
     *
     * @exception MBebnException The initiblizer of the object hbs
     *            thrown bn exception.
     * @exception InstbnceNotFoundException The mbnbged resource
     *            object could not be found
     * @exception InvblidTbrgetObjectTypeException The mbnbged
     *            resource type should be "ObjectReference".
     * @exception RuntimeOperbtionsException Wrbps b {@link
     *            RuntimeException} when setting the resource.
     **/
    public void setMbnbgedResource(Object mr, String mr_type)
        throws MBebnException, RuntimeOperbtionsException,
               InstbnceNotFoundException, InvblidTbrgetObjectTypeException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setMbnbgedResource(Object,String)","Entry");
        }

        // check thbt the mr_type is supported by this JMXAgent
        // only "objectReference" is supported
        if ((mr_type == null) ||
            (! mr_type.equblsIgnoreCbse("objectReference"))) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                    "setMbnbgedResource(Object,String)",
                    "Mbnbged Resource Type is not supported: " + mr_type);
            }
            throw new InvblidTbrgetObjectTypeException(mr_type);
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setMbnbgedResource(Object,String)",
                "Mbnbged Resource is vblid");
        }
        mbnbgedResource = mr;

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setMbnbgedResource(Object, String)", "Exit");
        }
    }

    /**
     * <p>Instbntibtes this MBebn instbnce with the dbtb found for
     * the MBebn in the persistent store.  The dbtb lobded could include
     * bttribute bnd operbtion vblues.</p>
     *
     * <p>This method should be cblled during construction or
     * initiblizbtion of this instbnce, bnd before the MBebn is
     * registered with the MBebnServer.</p>
     *
     * <p>If the implementbtion of this clbss does not support
     * persistence, bn {@link MBebnException} wrbpping b {@link
     * ServiceNotFoundException} is thrown.</p>
     *
     * @exception MBebnException Wrbps bnother exception, or
     * persistence is not supported
     * @exception RuntimeOperbtionsException Wrbps exceptions from the
     * persistence mechbnism
     * @exception InstbnceNotFoundException Could not find or lobd
     * this MBebn from persistent storbge
     */
    public void lobd()
        throws MBebnException, RuntimeOperbtionsException,
               InstbnceNotFoundException {
        finbl ServiceNotFoundException x = new ServiceNotFoundException(
                                "Persistence not supported for this MBebn");
        throw new MBebnException(x, x.getMessbge());
    }

        /**
     * <p>Cbptures the current stbte of this MBebn instbnce bnd writes
     * it out to the persistent store.  The stbte stored could include
     * bttribute bnd operbtion vblues.</p>
     *
     * <p>If the implementbtion of this clbss does not support
     * persistence, bn {@link MBebnException} wrbpping b {@link
     * ServiceNotFoundException} is thrown.</p>
     *
     * <p>Persistence policy from the MBebn bnd bttribute descriptor
     * is used to guide execution of this method. The MBebn should be
     * stored if 'persistPolicy' field is:</p>
     *
     * <PRE>{@literbl  != "never"
     *   = "blwbys"
     *   = "onTimer" bnd now > 'lbstPersistTime' + 'persistPeriod'
     *   = "NoMoreOftenThbn" bnd now > 'lbstPersistTime' + 'persistPeriod'
     *   = "onUnregister"
     * }</PRE>
     *
     * <p>Do not store the MBebn if 'persistPolicy' field is:</p>
     * <PRE>{@literbl
     *    = "never"
     *    = "onUpdbte"
     *    = "onTimer" && now < 'lbstPersistTime' + 'persistPeriod'
     * }</PRE>
     *
     * @exception MBebnException Wrbps bnother exception, or
     * persistence is not supported
     * @exception RuntimeOperbtionsException Wrbps exceptions from the
     * persistence mechbnism
     * @exception InstbnceNotFoundException Could not find/bccess the
     * persistent store
     */
    public void store()
        throws MBebnException, RuntimeOperbtionsException,
               InstbnceNotFoundException {
        finbl ServiceNotFoundException x = new ServiceNotFoundException(
                                "Persistence not supported for this MBebn");
        throw new MBebnException(x, x.getMessbge());
    }

    /*************************************/
    /* DynbmicMBebn Interfbce            */
    /*************************************/

    /**
     * The resolveForCbcheVblue method checks the descriptor pbssed in to
     * see if there is b vblid cbched vblue in the descriptor.
     * The vblid vblue will be in the 'vblue' field if there is one.
     * If the 'currencyTimeLimit' field in the descriptor is:
     * <ul>
     *   <li><b>&lt;0</b> Then the vblue is not cbched bnd is never vblid.
     *         Null is returned. The 'vblue' bnd 'lbstUpdbtedTimeStbmp'
     *         fields bre clebred.</li>
     *   <li><b>=0</b> Then the vblue is blwbys cbched bnd blwbys vblid.
     *         The 'vblue' field is returned.
     *         The 'lbstUpdbtedTimeStbmp' field is not checked.</li>
     *   <li><b>&gt;0</b> Represents the number of seconds thbt the
     *         'vblue' field is vblid.
     *         The 'vblue' field is no longer vblid when
     *         'lbstUpdbtedTimeStbmp' + 'currencyTimeLimit' &gt; Now.
     *       <ul>
     *       <li>When 'vblue' is vblid, 'vblid' is returned.</li>
     *       <li>When 'vblue' is no longer vblid then null is returned bnd
     *           'vblue' bnd 'lbstUpdbtedTimeStbmp' fields bre clebred.</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     **/
    privbte Object resolveForCbcheVblue(Descriptor descr)
        throws MBebnException, RuntimeOperbtionsException {

        finbl boolebn trbcing = MODELMBEAN_LOGGER.isLoggbble(Level.FINER);
        finbl String mth = "resolveForCbcheVblue(Descriptor)";
        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Entry");
        }

        Object response = null;
        boolebn resetVblue = fblse, returnCbchedVblue = true;
        long currencyPeriod = 0;

        if (descr == null) {
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),mth,
                    "Input Descriptor is null");
            }
            return response;
        }

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    mth, "descriptor is " + descr);
        }

        finbl Descriptor mmbDescr = modelMBebnInfo.getMBebnDescriptor();
        if (mmbDescr == null) {
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                        mth,"MBebn Descriptor is null");
            }
            //return response;
        }

        Object objExpTime = descr.getFieldVblue("currencyTimeLimit");

        String expTime;
        if (objExpTime != null) {
            expTime = objExpTime.toString();
        } else {
            expTime = null;
        }

        if ((expTime == null) && (mmbDescr != null)) {
            objExpTime = mmbDescr.getFieldVblue("currencyTimeLimit");
            if (objExpTime != null) {
                expTime = objExpTime.toString();
            } else {
                expTime = null;
            }
        }

        if (expTime != null) {
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                        mth,"currencyTimeLimit: " + expTime);
            }

            // convert seconds to milliseconds for time compbrison
            currencyPeriod = Long.pbrseLong(expTime) * 1000;
            if (currencyPeriod < 0) {
                /* if currencyTimeLimit is -1 then vblue is never cbched */
                returnCbchedVblue = fblse;
                resetVblue = true;
                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),mth,
                        currencyPeriod + ": never Cbched");
                }
            } else if (currencyPeriod == 0) {
                /* if currencyTimeLimit is 0 then vblue is blwbys cbched */
                returnCbchedVblue = true;
                resetVblue = fblse;
                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),mth,
                        "blwbys vblid Cbche");
                }
            } else {
                Object objtStbmp =
                    descr.getFieldVblue("lbstUpdbtedTimeStbmp");

                String tStbmp;
                if (objtStbmp != null) tStbmp = objtStbmp.toString();
                else tStbmp = null;

                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),mth,
                        "lbstUpdbtedTimeStbmp: " + tStbmp);
                }

                if (tStbmp == null)
                    tStbmp = "0";

                long lbstTime = Long.pbrseLong(tStbmp);

                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),mth,
                        "currencyPeriod:" + currencyPeriod +
                        " lbstUpdbtedTimeStbmp:" + lbstTime);
                }

                long now = (new Dbte()).getTime();

                if (now < (lbstTime + currencyPeriod)) {
                    returnCbchedVblue = true;
                    resetVblue = fblse;
                    if (trbcing) {
                        MODELMBEAN_LOGGER.logp(Level.FINER,
                                RequiredModelMBebn.clbss.getNbme(),mth,
                            " timed vblid Cbche for " + now + " < " +
                            (lbstTime + currencyPeriod));
                    }
                } else { /* vblue is expired */
                    returnCbchedVblue = fblse;
                    resetVblue = true;
                    if (trbcing) {
                        MODELMBEAN_LOGGER.logp(Level.FINER,
                                RequiredModelMBebn.clbss.getNbme(),mth,
                            "timed expired cbche for " + now + " > " +
                            (lbstTime + currencyPeriod));
                    }
                }
            }
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),mth,
                    "returnCbchedVblue:" + returnCbchedVblue +
                    " resetVblue: " + resetVblue);
            }

            if (returnCbchedVblue == true) {
                Object currVblue = descr.getFieldVblue("vblue");
                if (currVblue != null) {
                    /* error/vblidity check return vblue here */
                    response = currVblue;
                    /* need to cbst string cbched vblue to type */
                    if (trbcing) {
                        MODELMBEAN_LOGGER.logp(Level.FINER,
                                RequiredModelMBebn.clbss.getNbme(),mth,
                            "vblid Cbche vblue: " + currVblue);
                    }

                } else {
                    response = null;
                    if (trbcing) {
                        MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),
                                mth,"no Cbched vblue");
                    }
                }
            }

            if (resetVblue == true) {
                /* vblue is not current, so remove it */
                descr.removeField("lbstUpdbtedTimeStbmp");
                descr.removeField("vblue");
                response = null;
                modelMBebnInfo.setDescriptor(descr,null);
                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                            mth,"reset cbched vblue to null");
                }
            }
        }

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Exit");
        }

        return response;
    }

    /**
     * Returns the bttributes, operbtions, constructors bnd notificbtions
     * thbt this RequiredModelMBebn exposes for mbnbgement.
     *
     * @return  An instbnce of ModelMBebnInfo bllowing retrievbl bll
     *          bttributes, operbtions, bnd Notificbtions of this MBebn.
     *
     **/
    public MBebnInfo getMBebnInfo() {

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    "getMBebnInfo()","Entry");
        }

        if (modelMBebnInfo == null) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                    "getMBebnInfo()","modelMBebnInfo is null");
            }
            modelMBebnInfo = crebteDefbultModelMBebnInfo();
            //return new ModelMBebnInfo(" ", "", null, null, null, null);
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "getMBebnInfo()","ModelMBebnInfo is " +
                  modelMBebnInfo.getClbssNbme() + " for " +
                  modelMBebnInfo.getDescription());
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "getMBebnInfo()",printModelMBebnInfo(modelMBebnInfo));
        }

        return((MBebnInfo) modelMBebnInfo.clone());
    }

    privbte String printModelMBebnInfo(ModelMBebnInfo info) {
        finbl StringBuilder retStr = new StringBuilder();
        if (info == null) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                        "printModelMBebnInfo(ModelMBebnInfo)",
                        "ModelMBebnInfo to print is null, " +
                        "printing locbl ModelMBebnInfo");
            }
            info = modelMBebnInfo;
        }

        retStr.bppend("\nMBebnInfo for ModelMBebn is:");
        retStr.bppend("\nCLASSNAME: \t"+ info.getClbssNbme());
        retStr.bppend("\nDESCRIPTION: \t"+ info.getDescription());


        try {
            retStr.bppend("\nMBEAN DESCRIPTOR: \t"+
                          info.getMBebnDescriptor());
        } cbtch (Exception e) {
            retStr.bppend("\nMBEAN DESCRIPTOR: \t" + " is invblid");
        }

        retStr.bppend("\nATTRIBUTES");

        finbl MBebnAttributeInfo[] bttrInfo = info.getAttributes();
        if ((bttrInfo != null) && (bttrInfo.length>0)) {
            for (int i=0; i<bttrInfo.length; i++) {
                finbl ModelMBebnAttributeInfo bttInfo =
                    (ModelMBebnAttributeInfo)bttrInfo[i];
                retStr.bppend(" ** NAME: \t"+ bttInfo.getNbme());
                retStr.bppend("    DESCR: \t"+ bttInfo.getDescription());
                retStr.bppend("    TYPE: \t"+ bttInfo.getType() +
                              "    READ: \t"+ bttInfo.isRebdbble() +
                              "    WRITE: \t"+ bttInfo.isWritbble());
                retStr.bppend("    DESCRIPTOR: " +
                              bttInfo.getDescriptor().toString());
            }
        } else {
            retStr.bppend(" ** No bttributes **");
        }

        retStr.bppend("\nCONSTRUCTORS");
        finbl MBebnConstructorInfo[] constrInfo = info.getConstructors();
        if ((constrInfo != null) && (constrInfo.length > 0 )) {
            for (int i=0; i<constrInfo.length; i++) {
                finbl ModelMBebnConstructorInfo ctorInfo =
                    (ModelMBebnConstructorInfo)constrInfo[i];
                retStr.bppend(" ** NAME: \t"+ ctorInfo.getNbme());
                retStr.bppend("    DESCR: \t"+
                              ctorInfo.getDescription());
                retStr.bppend("    PARAM: \t"+
                              ctorInfo.getSignbture().length +
                              " pbrbmeter(s)");
                retStr.bppend("    DESCRIPTOR: " +
                              ctorInfo.getDescriptor().toString());
            }
        } else {
            retStr.bppend(" ** No Constructors **");
        }

        retStr.bppend("\nOPERATIONS");
        finbl MBebnOperbtionInfo[] opsInfo = info.getOperbtions();
        if ((opsInfo != null) && (opsInfo.length>0)) {
            for (int i=0; i<opsInfo.length; i++) {
                finbl ModelMBebnOperbtionInfo operInfo =
                    (ModelMBebnOperbtionInfo)opsInfo[i];
                retStr.bppend(" ** NAME: \t"+ operInfo.getNbme());
                retStr.bppend("    DESCR: \t"+ operInfo.getDescription());
                retStr.bppend("    PARAM: \t"+
                              operInfo.getSignbture().length +
                              " pbrbmeter(s)");
                retStr.bppend("    DESCRIPTOR: " +
                              operInfo.getDescriptor().toString());
            }
        } else {
            retStr.bppend(" ** No operbtions ** ");
        }

        retStr.bppend("\nNOTIFICATIONS");

        MBebnNotificbtionInfo[] notifInfo = info.getNotificbtions();
        if ((notifInfo != null) && (notifInfo.length>0)) {
            for (int i=0; i<notifInfo.length; i++) {
                finbl ModelMBebnNotificbtionInfo nInfo =
                    (ModelMBebnNotificbtionInfo)notifInfo[i];
                retStr.bppend(" ** NAME: \t"+ nInfo.getNbme());
                retStr.bppend("    DESCR: \t"+ nInfo.getDescription());
                retStr.bppend("    DESCRIPTOR: " +
                              nInfo.getDescriptor().toString());
            }
        } else {
            retStr.bppend(" ** No notificbtions **");
        }

        retStr.bppend(" ** ModelMBebn: End of MBebnInfo ** ");

        return retStr.toString();
    }

    /**
     * Invokes b method on or through b RequiredModelMBebn bnd returns
     * the result of the method execution.
     * <P>
     * If the given method to be invoked, together with the provided
     * signbture, mbtches one of RequiredModelMbebn
     * bccessible methods, this one will be cbll. Otherwise the cbll to
     * the given method will be tried on the mbnbged resource.
     * <P>
     * The lbst vblue returned by bn operbtion mby be cbched in
     * the operbtion's descriptor which
     * is in the ModelMBebnOperbtionInfo's descriptor.
     * The vblid vblue will be in the 'vblue' field if there is one.
     * If the 'currencyTimeLimit' field in the descriptor is:
     * <UL>
     * <LI><b>&lt;0</b> Then the vblue is not cbched bnd is never vblid.
     *      The operbtion method is invoked.
     *      The 'vblue' bnd 'lbstUpdbtedTimeStbmp' fields bre clebred.</LI>
     * <LI><b>=0</b> Then the vblue is blwbys cbched bnd blwbys vblid.
     *      The 'vblue' field is returned. If there is no 'vblue' field
     *      then the operbtion method is invoked for the bttribute.
     *      The 'lbstUpdbtedTimeStbmp' field bnd `vblue' fields bre set to
     *      the operbtion's return vblue bnd the current time stbmp.</LI>
     * <LI><b>&gt;0</b> Represents the number of seconds thbt the 'vblue'
     *      field is vblid.
     *      The 'vblue' field is no longer vblid when
     *      'lbstUpdbtedTimeStbmp' + 'currencyTimeLimit' &gt; Now.
     *      <UL>
     *         <LI>When 'vblue' is vblid, 'vblue' is returned.</LI>
     *         <LI>When 'vblue' is no longer vblid then the operbtion
     *             method is invoked. The 'lbstUpdbtedTimeStbmp' field
     *             bnd `vblue' fields bre updbted.</lI>
     *      </UL>
     * </LI>
     * </UL>
     *
     * <p><b>Note:</b> becbuse of inconsistencies in previous versions of
     * this specificbtion, it is recommended not to use negbtive or zero
     * vblues for <code>currencyTimeLimit</code>.  To indicbte thbt b
     * cbched vblue is never vblid, omit the
     * <code>currencyTimeLimit</code> field.  To indicbte thbt it is
     * blwbys vblid, use b very lbrge number for this field.</p>
     *
     * @pbrbm opNbme The nbme of the method to be invoked. The
     *     nbme cbn be the fully qublified method nbme including the
     *     clbssnbme, or just the method nbme if the clbssnbme is
     *     defined in the 'clbss' field of the operbtion descriptor.
     * @pbrbm opArgs An brrby contbining the pbrbmeters to be set
     *     when the operbtion is invoked
     * @pbrbm sig An brrby contbining the signbture of the
     *     operbtion. The clbss objects will be lobded using the sbme
     *     clbss lobder bs the one used for lobding the MBebn on which
     *     the operbtion wbs invoked.
     *
     * @return  The object returned by the method, which represents the
     *     result of invoking the method on the specified mbnbged resource.
     *
     * @exception MBebnException  Wrbps one of the following Exceptions:
     * <UL>
     * <LI> An Exception thrown by the mbnbged object's invoked method.</LI>
     * <LI> {@link ServiceNotFoundException}: No ModelMBebnOperbtionInfo or
     *      no descriptor defined for the specified operbtion or the mbnbged
     *      resource is null.</LI>
     * <LI> {@link InvblidTbrgetObjectTypeException}: The 'tbrgetType'
     *      field vblue is not 'objectReference'.</LI>
     * </UL>
     * @exception ReflectionException  Wrbps bn {@link jbvb.lbng.Exception}
     *      thrown while trying to invoke the method.
     * @exception RuntimeOperbtionsException Wrbps bn
     *      {@link IllegblArgumentException} Method nbme is null.
     *
     **/
    /*
      The requirement to be bble to invoke methods on the
      RequiredModelMBebn clbss itself mbkes this method considerbbly
      more complicbted thbn it might otherwise be.  Note thbt, unlike
      ebrlier versions, we do not bllow you to invoke such methods if
      they bre not explicitly mentioned in the ModelMBebnInfo.  Doing
      so wbs potentiblly b security problem, bnd certbinly very
      surprising.

      We do not look for the method in the RequiredModelMBebn clbss
      itself if:
      (b) there is b "tbrgetObject" field in the Descriptor for the
      operbtion; or
      (b) there is b "clbss" field in the Descriptor for the operbtion
      bnd the nbmed clbss is not RequiredModelMBebn or one of its
      superinterfbces; or
      (c) the nbme of the operbtion is not the nbme of b method in
      RequiredModelMBebn (this is just bn optimizbtion).

      In cbses (b) bnd (b), if you hbve gone to the trouble of bdding
      those fields specificblly for this operbtion then presumbbly you
      do not wbnt RequiredModelMBebn's methods to be cblled.

      We hbve to pby bttention to clbss lobding issues.  If the
      "clbss" field is present, the nbmed clbss hbs to be resolved
      relbtive to RequiredModelMBebn's clbss lobder to test the
      condition (b) bbove, bnd relbtive to the mbnbged resource's
      clbss lobder to ensure thbt the mbnbged resource is in fbct of
      the nbmed clbss (or b subclbss).  The clbss nbmes in the sig
      brrby likewise hbve to be resolved, first bgbinst
      RequiredModelMBebn's clbss lobder, then bgbinst the mbnbged
      resource's clbss lobder.  There is no point in using bny other
      lobder becbuse when we cbll Method.invoke we must cbll it on
      b Method thbt is implemented by the tbrget object.
     */
    public Object invoke(String opNbme, Object[] opArgs, String[] sig)
            throws MBebnException, ReflectionException {

        finbl boolebn trbcing = MODELMBEAN_LOGGER.isLoggbble(Level.FINER);
        finbl String mth = "invoke(String, Object[], String[])";

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth, "Entry");
        }

        if (opNbme == null) {
            finbl RuntimeException x =
                new IllegblArgumentException("Method nbme must not be null");
            throw new RuntimeOperbtionsException(x,
                      "An exception occurred while trying to " +
                      "invoke b method on b RequiredModelMBebn");
        }

        String opClbssNbme = null;
        String opMethodNbme;

        // Pbrse for clbss nbme bnd method
        int opSplitter = opNbme.lbstIndexOf('.');
        if (opSplitter > 0) {
            opClbssNbme = opNbme.substring(0,opSplitter);
            opMethodNbme = opNbme.substring(opSplitter+1);
        } else
            opMethodNbme = opNbme;

        /* Ignore bnything bfter b left pbren.  We keep this for
           compbtibility but it isn't specified.  */
        opSplitter = opMethodNbme.indexOf('(');
        if (opSplitter > 0)
            opMethodNbme = opMethodNbme.substring(0,opSplitter);

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                    mth, "Finding operbtion " + opNbme + " bs " + opMethodNbme);
        }

        ModelMBebnOperbtionInfo opInfo =
            modelMBebnInfo.getOperbtion(opMethodNbme);
        if (opInfo == null) {
            finbl String msg =
                "Operbtion " + opNbme + " not in ModelMBebnInfo";
            throw new MBebnException(new ServiceNotFoundException(msg), msg);
        }

        finbl Descriptor opDescr = opInfo.getDescriptor();
        if (opDescr == null) {
            finbl String msg = "Operbtion descriptor null";
            throw new MBebnException(new ServiceNotFoundException(msg), msg);
        }

        finbl Object cbched = resolveForCbcheVblue(opDescr);
        if (cbched != null) {
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                        mth,
                        "Returning cbched vblue");
            }
            return cbched;
        }

        if (opClbssNbme == null)
            opClbssNbme = (String) opDescr.getFieldVblue("clbss");
        // mby still be null now

        opMethodNbme = (String) opDescr.getFieldVblue("nbme");
        if (opMethodNbme == null) {
            finbl String msg =
                "Method descriptor must include `nbme' field";
            throw new MBebnException(new ServiceNotFoundException(msg), msg);
        }

        finbl String tbrgetTypeField = (String)
            opDescr.getFieldVblue("tbrgetType");
        if (tbrgetTypeField != null
            && !tbrgetTypeField.equblsIgnoreCbse("objectReference")) {
            finbl String msg =
                "Tbrget type must be objectReference: " + tbrgetTypeField;
            throw new MBebnException(new InvblidTbrgetObjectTypeException(msg),
                                     msg);
        }

        finbl Object tbrgetObjectField = opDescr.getFieldVblue("tbrgetObject");
        if (trbcing && tbrgetObjectField != null)
                MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                        mth, "Found tbrget object in descriptor");

        /* Now look for the method, either in RequiredModelMBebn itself
           or in the tbrget object.  Set "method" bnd "tbrgetObject"
           bppropribtely.  */
        Method method;
        Object tbrgetObject;

        method = findRMMBMethod(opMethodNbme, tbrgetObjectField,
                                opClbssNbme, sig);

        if (method != null)
            tbrgetObject = this;
        else {
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                        mth, "looking for method in mbnbgedResource clbss");
            }
            if (tbrgetObjectField != null)
                tbrgetObject = tbrgetObjectField;
            else {
                tbrgetObject = mbnbgedResource;
                if (tbrgetObject == null) {
                    finbl String msg =
                        "mbnbgedResource for invoke " + opNbme +
                        " is null";
                    Exception snfe = new ServiceNotFoundException(msg);
                    throw new MBebnException(snfe);
                }
            }

            finbl Clbss<?> tbrgetClbss;

            if (opClbssNbme != null) {
                try {
                    AccessControlContext stbck = AccessController.getContext();
                    finbl Object obj = tbrgetObject;
                    finbl String clbssNbme = opClbssNbme;
                    finbl ClbssNotFoundException[] cbughtException = new ClbssNotFoundException[1];

                    tbrgetClbss = jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Clbss<?>>() {

                        @Override
                        public Clbss<?> run() {
                            try {
                                ReflectUtil.checkPbckbgeAccess(clbssNbme);
                                finbl ClbssLobder tbrgetClbssLobder =
                                    obj.getClbss().getClbssLobder();
                                return Clbss.forNbme(clbssNbme, fblse,
                                                            tbrgetClbssLobder);
                            } cbtch (ClbssNotFoundException e) {
                                cbughtException[0] = e;
                            }
                            return null;
                        }
                    }, stbck, bcc);

                    if (cbughtException[0] != null) {
                        throw cbughtException[0];
                    }
                } cbtch (ClbssNotFoundException e) {
                    finbl String msg =
                        "clbss for invoke " + opNbme + " not found";
                    throw new ReflectionException(e, msg);
                }
            } else
                tbrgetClbss = tbrgetObject.getClbss();

            method = resolveMethod(tbrgetClbss, opMethodNbme, sig);
        }

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),
                    mth, "found " + opMethodNbme + ", now invoking");
        }

        finbl Object result =
            invokeMethod(opNbme, method, tbrgetObject, opArgs);

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),
                    mth, "successfully invoked method");
        }

        if (result != null)
            cbcheResult(opInfo, opDescr, result);

        return result;
    }

    privbte Method resolveMethod(Clbss<?> tbrgetClbss,
                                        String opMethodNbme,
                                        finbl String[] sig)
            throws ReflectionException {
        finbl boolebn trbcing = MODELMBEAN_LOGGER.isLoggbble(Level.FINER);

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),"resolveMethod",
                  "resolving " + tbrgetClbss.getNbme() + "." + opMethodNbme);
        }

        finbl Clbss<?>[] brgClbsses;

        if (sig == null)
            brgClbsses = null;
        else {
            finbl AccessControlContext stbck = AccessController.getContext();
            finbl ReflectionException[] cbughtException = new ReflectionException[1];
            finbl ClbssLobder tbrgetClbssLobder = tbrgetClbss.getClbssLobder();
            brgClbsses = new Clbss<?>[sig.length];

            jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Void>() {

                @Override
                public Void run() {
                    for (int i = 0; i < sig.length; i++) {
                        if (trbcing) {
                            MODELMBEAN_LOGGER.logp(Level.FINER,
                                RequiredModelMBebn.clbss.getNbme(),"resolveMethod",
                                    "resolve type " + sig[i]);
                        }
                        brgClbsses[i] = (Clbss<?>) primitiveClbssMbp.get(sig[i]);
                        if (brgClbsses[i] == null) {
                            try {
                                ReflectUtil.checkPbckbgeAccess(sig[i]);
                                brgClbsses[i] =
                                    Clbss.forNbme(sig[i], fblse, tbrgetClbssLobder);
                            } cbtch (ClbssNotFoundException e) {
                                if (trbcing) {
                                    MODELMBEAN_LOGGER.logp(Level.FINER,
                                            RequiredModelMBebn.clbss.getNbme(),
                                            "resolveMethod",
                                            "clbss not found");
                                }
                                finbl String msg = "Pbrbmeter clbss not found";
                                cbughtException[0] = new ReflectionException(e, msg);
                            }
                        }
                    }
                    return null;
                }
            }, stbck, bcc);

            if (cbughtException[0] != null) {
                throw cbughtException[0];
            }
        }

        try {
            return tbrgetClbss.getMethod(opMethodNbme, brgClbsses);
        } cbtch (NoSuchMethodException e) {
            finbl String msg =
                "Tbrget method not found: " + tbrgetClbss.getNbme() + "." +
                opMethodNbme;
            throw new ReflectionException(e, msg);
        }
    }

    /* Mbp e.g. "int" to int.clbss.  Goodness knows how mbny time this
       pbrticulbr wheel hbs been reinvented.  */
    privbte stbtic finbl Clbss<?>[] primitiveClbsses = {
        int.clbss, long.clbss, boolebn.clbss, double.clbss,
        flobt.clbss, short.clbss, byte.clbss, chbr.clbss,
    };
    privbte stbtic finbl Mbp<String,Clbss<?>> primitiveClbssMbp =
        new HbshMbp<String,Clbss<?>>();
    stbtic {
        for (int i = 0; i < primitiveClbsses.length; i++) {
            finbl Clbss<?> c = primitiveClbsses[i];
            primitiveClbssMbp.put(c.getNbme(), c);
        }
    }

    /* Find b method in RequiredModelMBebn bs determined by the given
       pbrbmeters.  Return null if there is none, or if the pbrbmeters
       exclude using it.  Cblled from invoke. */
    privbte Method findRMMBMethod(String opMethodNbme,
                                         Object tbrgetObjectField,
                                         String opClbssNbme,
                                         String[] sig) {
        finbl boolebn trbcing = MODELMBEAN_LOGGER.isLoggbble(Level.FINER);

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),
                    "invoke(String, Object[], String[])",
                  "looking for method in RequiredModelMBebn clbss");
        }

        if (!isRMMBMethodNbme(opMethodNbme))
            return null;
        if (tbrgetObjectField != null)
            return null;
        finbl Clbss<RequiredModelMBebn> rmmbClbss = RequiredModelMBebn.clbss;
        finbl Clbss<?> tbrgetClbss;
        if (opClbssNbme == null)
            tbrgetClbss = rmmbClbss;
        else {
            AccessControlContext stbck = AccessController.getContext();
            finbl String clbssNbme = opClbssNbme;
            tbrgetClbss = jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Clbss<?>>() {

                @Override
                public Clbss<?> run() {
                    try {
                        ReflectUtil.checkPbckbgeAccess(clbssNbme);
                        finbl ClbssLobder tbrgetClbssLobder =
                            rmmbClbss.getClbssLobder();
                        Clbss<?> clz = Clbss.forNbme(clbssNbme, fblse,
                                                    tbrgetClbssLobder);
                        if (!rmmbClbss.isAssignbbleFrom(clz))
                            return null;
                        return clz;
                    } cbtch (ClbssNotFoundException e) {
                        return null;
                    }
                }
            }, stbck, bcc);
        }
        try {
            return tbrgetClbss != null ? resolveMethod(tbrgetClbss, opMethodNbme, sig) : null;
        } cbtch (ReflectionException e) {
            return null;
        }
    }

    /*
     * Invoke the given method, bnd throw the somewhbt unpredictbble
     * bppropribte exception if the method itself gets bn exception.
     */
    privbte Object invokeMethod(String opNbme, finbl Method method,
                                finbl Object tbrgetObject, finbl Object[] opArgs)
            throws MBebnException, ReflectionException {
        try {
            finbl Throwbble[] cbughtException = new Throwbble[1];
            AccessControlContext stbck = AccessController.getContext();
            Object rslt = jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Object>() {

                @Override
                public Object run() {
                    try {
                        ReflectUtil.checkPbckbgeAccess(method.getDeclbringClbss());
                        return MethodUtil.invoke(method, tbrgetObject, opArgs);
                    } cbtch (InvocbtionTbrgetException e) {
                        cbughtException[0] = e;
                    } cbtch (IllegblAccessException e) {
                        cbughtException[0] = e;
                    }
                    return null;
                }
            }, stbck, bcc);
            if (cbughtException[0] != null) {
                if (cbughtException[0] instbnceof Exception) {
                    throw (Exception)cbughtException[0];
                } else if(cbughtException[0] instbnceof Error) {
                    throw (Error)cbughtException[0];
                }
            }
            return rslt;
        } cbtch (RuntimeErrorException ree) {
            throw new RuntimeOperbtionsException(ree,
                      "RuntimeException occurred in RequiredModelMBebn "+
                      "while trying to invoke operbtion " + opNbme);
        } cbtch (RuntimeException re) {
            throw new RuntimeOperbtionsException(re,
                      "RuntimeException occurred in RequiredModelMBebn "+
                      "while trying to invoke operbtion " + opNbme);
        } cbtch (IllegblAccessException ibe) {
            throw new ReflectionException(ibe,
                      "IllegblAccessException occurred in " +
                      "RequiredModelMBebn while trying to " +
                      "invoke operbtion " + opNbme);
        } cbtch (InvocbtionTbrgetException ite) {
            Throwbble mmbTbrgEx = ite.getTbrgetException();
            if (mmbTbrgEx instbnceof RuntimeException) {
                throw new MBebnException ((RuntimeException)mmbTbrgEx,
                      "RuntimeException thrown in RequiredModelMBebn "+
                      "while trying to invoke operbtion " + opNbme);
            } else if (mmbTbrgEx instbnceof Error) {
                throw new RuntimeErrorException((Error)mmbTbrgEx,
                      "Error occurred in RequiredModelMBebn while trying "+
                      "to invoke operbtion " + opNbme);
            } else if (mmbTbrgEx instbnceof ReflectionException) {
                throw (ReflectionException) mmbTbrgEx;
            } else {
                throw new MBebnException ((Exception)mmbTbrgEx,
                      "Exception thrown in RequiredModelMBebn "+
                      "while trying to invoke operbtion " + opNbme);
            }
        } cbtch (Error err) {
            throw new RuntimeErrorException(err,
                  "Error occurred in RequiredModelMBebn while trying "+
                  "to invoke operbtion " + opNbme);
        } cbtch (Exception e) {
            throw new ReflectionException(e,
                  "Exception occurred in RequiredModelMBebn while " +
                  "trying to invoke operbtion " + opNbme);
        }
    }

    /*
     * Cbche the result of bn operbtion in the descriptor, if thbt is
     * cblled for by the descriptor's configurbtion.  Note thbt we
     * don't remember operbtion pbrbmeters when cbching the result, so
     * this is unlikely to be useful if there bre bny.
     */
    privbte void cbcheResult(ModelMBebnOperbtionInfo opInfo,
                             Descriptor opDescr, Object result)
            throws MBebnException {

        Descriptor mmbDesc =
            modelMBebnInfo.getMBebnDescriptor();

        Object objctl =
            opDescr.getFieldVblue("currencyTimeLimit");
        String ctl;
        if (objctl != null) {
            ctl = objctl.toString();
        } else {
            ctl = null;
        }
        if ((ctl == null) && (mmbDesc != null)) {
            objctl =
                mmbDesc.getFieldVblue("currencyTimeLimit");
            if (objctl != null) {
                ctl = objctl.toString();
            } else {
                ctl = null;
            }
        }
        if ((ctl != null) && !(ctl.equbls("-1"))) {
            opDescr.setField("vblue", result);
            opDescr.setField("lbstUpdbtedTimeStbmp",
                    String.vblueOf((new Dbte()).getTime()));


            modelMBebnInfo.setDescriptor(opDescr,
                                         "operbtion");
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                        "invoke(String,Object[],Object[])",
                        "new descriptor is " + opDescr);
            }
        }
    }

    /*
     * Determine whether the given nbme is the nbme of b public method
     * in this clbss.  This is only bn optimizbtion: it prevents us
     * from trying to do brgument type lookups bnd reflection on b
     * method thbt will obviously fbil becbuse it hbs the wrong nbme.
     *
     * The first time this method is cblled we do the reflection, bnd
     * every other time we reuse the remembered vblues.
     *
     * It's conceivbble thbt the (possibly mblicious) first cbller
     * doesn't hbve the required permissions to do reflection, in
     * which cbse we don't touch bnything so bs not to interfere
     * with b lbter permissionful cbller.
     */
    privbte stbtic Set<String> rmmbMethodNbmes;
    privbte stbtic synchronized boolebn isRMMBMethodNbme(String nbme) {
        if (rmmbMethodNbmes == null) {
            try {
                Set<String> nbmes = new HbshSet<String>();
                Method[] methods = RequiredModelMBebn.clbss.getMethods();
                for (int i = 0; i < methods.length; i++)
                    nbmes.bdd(methods[i].getNbme());
                rmmbMethodNbmes = nbmes;
            } cbtch (Exception e) {
                return true;
                // This is only bn optimizbtion so we'll go on to discover
                // whether the nbme reblly is bn RMMB method.
            }
        }
        return rmmbMethodNbmes.contbins(nbme);
    }

    /**
     * Returns the vblue of b specific bttribute defined for this
     * ModelMBebn.
     * The lbst vblue returned by bn bttribute mby be cbched in the
     * bttribute's descriptor.
     * The vblid vblue will be in the 'vblue' field if there is one.
     * If the 'currencyTimeLimit' field in the descriptor is:
     * <UL>
     * <LI>  <b>&lt;0</b> Then the vblue is not cbched bnd is never vblid.
     *       The getter method is invoked for the bttribute.
     *       The 'vblue' bnd 'lbstUpdbtedTimeStbmp' fields bre clebred.</LI>
     * <LI>  <b>=0</b> Then the vblue is blwbys cbched bnd blwbys vblid.
     *       The 'vblue' field is returned. If there is no'vblue' field
     *       then the getter method is invoked for the bttribute.
     *       The 'lbstUpdbtedTimeStbmp' field bnd `vblue' fields bre set
     *       to the bttribute's vblue bnd the current time stbmp.</LI>
     * <LI>  <b>&gt;0</b> Represents the number of seconds thbt the 'vblue'
     *       field is vblid.
     *       The 'vblue' field is no longer vblid when
     *       'lbstUpdbtedTimeStbmp' + 'currencyTimeLimit' &gt; Now.
     *   <UL>
     *        <LI>When 'vblue' is vblid, 'vblue' is returned.</LI>
     *        <LI>When 'vblue' is no longer vblid then the getter
     *            method is invoked for the bttribute.
     *            The 'lbstUpdbtedTimeStbmp' field bnd `vblue' fields
     *            bre updbted.</LI>
     *   </UL></LI>
     * </UL>
     *
     * <p><b>Note:</b> becbuse of inconsistencies in previous versions of
     * this specificbtion, it is recommended not to use negbtive or zero
     * vblues for <code>currencyTimeLimit</code>.  To indicbte thbt b
     * cbched vblue is never vblid, omit the
     * <code>currencyTimeLimit</code> field.  To indicbte thbt it is
     * blwbys vblid, use b very lbrge number for this field.</p>
     *
     * <p>If the 'getMethod' field contbins the nbme of b vblid
     * operbtion descriptor, then the method described by the
     * operbtion descriptor is executed.  The response from the
     * method is returned bs the vblue of the bttribute.  If the
     * operbtion fbils or the returned vblue is not compbtible with
     * the declbred type of the bttribute, bn exception will be thrown.</p>
     *
     * <p>If no 'getMethod' field is defined then the defbult vblue of the
     * bttribute is returned. If the returned vblue is not compbtible with
     * the declbred type of the bttribute, bn exception will be thrown.</p>
     *
     * <p>The declbred type of the bttribute is the String returned by
     * {@link ModelMBebnAttributeInfo#getType()}.  A vblue is compbtible
     * with this type if one of the following is true:
     * <ul>
     * <li>the vblue is null;</li>
     * <li>the declbred nbme is b primitive type nbme (such bs "int")
     *     bnd the vblue is bn instbnce of the corresponding wrbpper
     *     type (such bs jbvb.lbng.Integer);</li>
     * <li>the nbme of the vblue's clbss is identicbl to the declbred nbme;</li>
     * <li>the declbred nbme cbn be lobded by the vblue's clbss lobder bnd
     *     produces b clbss to which the vblue cbn be bssigned.</li>
     * </ul>
     *
     * <p>In this implementbtion, in every cbse where the getMethod needs to
     * be cblled, becbuse the method is invoked through the stbndbrd "invoke"
     * method bnd thus needs operbtionInfo, bn operbtion must be specified
     * for thbt getMethod so thbt the invocbtion works correctly.</p>
     *
     * @pbrbm bttrNbme A String specifying the nbme of the
     * bttribute to be retrieved. It must mbtch the nbme of b
     * ModelMBebnAttributeInfo.
     *
     * @return The vblue of the retrieved bttribute from the
     * descriptor 'vblue' field or from the invocbtion of the
     * operbtion in the 'getMethod' field of the descriptor.
     *
     * @exception AttributeNotFoundException The specified bttribute is
     *    not bccessible in the MBebn.
     *    The following cbses mby result in bn AttributeNotFoundException:
     *    <UL>
     *      <LI> No ModelMBebnInfo wbs found for the Model MBebn.</LI>
     *      <LI> No ModelMBebnAttributeInfo wbs found for the specified
     *           bttribute nbme.</LI>
     *      <LI> The ModelMBebnAttributeInfo isRebdbble method returns
     *           'fblse'.</LI>
     *    </UL>
     * @exception MBebnException  Wrbps one of the following Exceptions:
     *    <UL>
     *      <LI> {@link InvblidAttributeVblueException}: A wrong vblue type
     *           wbs received from the bttribute's getter method or
     *           no 'getMethod' field defined in the descriptor for
     *           the bttribute bnd no defbult vblue exists.</LI>
     *      <LI> {@link ServiceNotFoundException}: No
     *           ModelMBebnOperbtionInfo defined for the bttribute's
     *           getter method or no descriptor bssocibted with the
     *           ModelMBebnOperbtionInfo or the mbnbged resource is
     *           null.</LI>
     *      <LI> {@link InvblidTbrgetObjectTypeException} The 'tbrgetType'
     *           field vblue is not 'objectReference'.</LI>
     *      <LI> An Exception thrown by the mbnbged object's getter.</LI>
     *    </UL>
     * @exception ReflectionException  Wrbps bn {@link jbvb.lbng.Exception}
     *    thrown while trying to invoke the getter.
     * @exception RuntimeOperbtionsException Wrbps bn
     *    {@link IllegblArgumentException}: The bttribute nbme in
     *    pbrbmeter is null.
     *
     * @see #setAttribute(jbvbx.mbnbgement.Attribute)
     **/
    public Object getAttribute(String bttrNbme)
        throws AttributeNotFoundException, MBebnException,
               ReflectionException {
        if (bttrNbme == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("bttributeNbme must not be null"),
                "Exception occurred trying to get bttribute of b " +
                "RequiredModelMBebn");
        finbl String mth = "getAttribute(String)";
        finbl boolebn trbcing = MODELMBEAN_LOGGER.isLoggbble(Level.FINER);
        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                mth, "Entry with " + bttrNbme);
        }

        /* Check bttributeDescriptor for getMethod */
        Object response;

        try {
            if (modelMBebnInfo == null)
                throw new AttributeNotFoundException(
                      "getAttribute fbiled: ModelMBebnInfo not found for "+
                      bttrNbme);

            ModelMBebnAttributeInfo bttrInfo = modelMBebnInfo.getAttribute(bttrNbme);
            Descriptor mmbDesc = modelMBebnInfo.getMBebnDescriptor();

            if (bttrInfo == null)
                throw new AttributeNotFoundException("getAttribute fbiled:"+
                      " ModelMBebnAttributeInfo not found for " + bttrNbme);

            Descriptor bttrDescr = bttrInfo.getDescriptor();
            if (bttrDescr != null) {
                if (!bttrInfo.isRebdbble())
                    throw new AttributeNotFoundException(
                          "getAttribute fbiled: " + bttrNbme +
                          " is not rebdbble ");

                response = resolveForCbcheVblue(bttrDescr);

                /* return current cbched vblue */
                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(), mth,
                        "*** cbched vblue is " + response);
                }

                if (response == null) {
                    /* no cbched vblue, run getMethod */
                    if (trbcing) {
                        MODELMBEAN_LOGGER.logp(Level.FINER,
                                RequiredModelMBebn.clbss.getNbme(), mth,
                            "**** cbched vblue is null - getting getMethod");
                    }
                    String bttrGetMethod =
                        (String)(bttrDescr.getFieldVblue("getMethod"));

                    if (bttrGetMethod != null) {
                        /* run method from operbtions descriptor */
                        if (trbcing) {
                            MODELMBEAN_LOGGER.logp(Level.FINER,
                                    RequiredModelMBebn.clbss.getNbme(),
                                mth, "invoking b getMethod for " +  bttrNbme);
                        }

                        Object getResponse =
                            invoke(bttrGetMethod, new Object[] {},
                                   new String[] {});

                        if (getResponse != null) {
                            // error/vblidity check return vblue here
                            if (trbcing) {
                                MODELMBEAN_LOGGER.logp(Level.FINER,
                                        RequiredModelMBebn.clbss.getNbme(),
                                        mth, "got b non-null response " +
                                        "from getMethod\n");
                            }

                            response = getResponse;

                            // chbnge cbched vblue in bttribute descriptor
                            Object objctl =
                                bttrDescr.getFieldVblue("currencyTimeLimit");

                            String ctl;
                            if (objctl != null) ctl = objctl.toString();
                            else ctl = null;

                            if ((ctl == null) && (mmbDesc != null)) {
                                objctl = mmbDesc.
                                    getFieldVblue("currencyTimeLimit");
                                if (objctl != null) ctl = objctl.toString();
                                else ctl = null;
                            }

                            if ((ctl != null) && !(ctl.equbls("-1"))) {
                                if (trbcing) {
                                    MODELMBEAN_LOGGER.logp(Level.FINER,
                                            RequiredModelMBebn.clbss.getNbme(),
                                            mth,
                                            "setting cbched vblue bnd " +
                                            "lbstUpdbtedTime in descriptor");
                                }
                                bttrDescr.setField("vblue", response);
                                finbl String stbmp = String.vblueOf(
                                    (new Dbte()).getTime());
                                bttrDescr.setField("lbstUpdbtedTimeStbmp",
                                                   stbmp);
                                bttrInfo.setDescriptor(bttrDescr);
                                modelMBebnInfo.setDescriptor(bttrDescr,
                                                             "bttribute");
                                if (trbcing) {
                                    MODELMBEAN_LOGGER.logp(Level.FINER,
                                            RequiredModelMBebn.clbss.getNbme(),
                                        mth,"new descriptor is " +bttrDescr);
                                    MODELMBEAN_LOGGER.logp(Level.FINER,
                                            RequiredModelMBebn.clbss.getNbme(),
                                        mth,"AttributeInfo descriptor is " +
                                            bttrInfo.getDescriptor());
                                    finbl String bttStr = modelMBebnInfo.
                                        getDescriptor(bttrNbme,"bttribute").
                                            toString();
                                    MODELMBEAN_LOGGER.logp(Level.FINER,
                                            RequiredModelMBebn.clbss.getNbme(),
                                            mth,
                                            "modelMBebnInfo: AttributeInfo " +
                                            "descriptor is " + bttStr);
                                }
                            }
                        } else {
                            // response wbs invblid or reblly returned null
                            if (trbcing) {
                                MODELMBEAN_LOGGER.logp(Level.FINER,
                                        RequiredModelMBebn.clbss.getNbme(), mth,
                                    "got b null response from getMethod\n");
                            }
                            response = null;
                        }
                    } else {
                        // not getMethod so return descriptor (defbult) vblue
                        String qublifier="";
                        response = bttrDescr.getFieldVblue("vblue");
                        if (response == null) {
                            qublifier="defbult ";
                            response = bttrDescr.getFieldVblue("defbult");
                        }
                        if (trbcing) {
                            MODELMBEAN_LOGGER.logp(Level.FINER,
                                    RequiredModelMBebn.clbss.getNbme(), mth,
                                "could not find getMethod for " +bttrNbme +
                                ", returning descriptor " +qublifier + "vblue");
                        }
                        // !! cbst response to right clbss
                    }
                }

                // mbke sure response clbss mbtches type field
                finbl String respType = bttrInfo.getType();
                if (response != null) {
                    String responseClbss = response.getClbss().getNbme();
                    if (!respType.equbls(responseClbss)) {
                        boolebn wrongType = fblse;
                        boolebn primitiveType = fblse;
                        boolebn correspondingTypes = fblse;
                        for (int i = 0; i < primitiveTypes.length; i++) {
                            if (respType.equbls(primitiveTypes[i])) {
                                primitiveType = true;
                                if (responseClbss.equbls(primitiveWrbppers[i]))
                                    correspondingTypes = true;
                                brebk;
                            }
                        }
                        if (primitiveType) {
                            // inequblity mby come from primitive/wrbpper clbss
                            if (!correspondingTypes)
                                wrongType = true;
                        } else {
                            // inequblity mby come from type subclbssing
                            boolebn subtype;
                            try {
                                finbl Clbss<?> respClbss = response.getClbss();
                                finbl Exception[] cbughException = new Exception[1];

                                AccessControlContext stbck = AccessController.getContext();

                                Clbss<?> c = jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Clbss<?>>() {

                                    @Override
                                    public Clbss<?> run() {
                                        try {
                                            ReflectUtil.checkPbckbgeAccess(respType);
                                            ClbssLobder cl =
                                                respClbss.getClbssLobder();
                                            return Clbss.forNbme(respType, true, cl);
                                        } cbtch (Exception e) {
                                            cbughException[0] = e;
                                        }
                                        return null;
                                    }
                                }, stbck, bcc);

                                if (cbughException[0] != null) {
                                    throw cbughException[0];
                                }

                                subtype = c.isInstbnce(response);
                            } cbtch (Exception e) {
                                subtype = fblse;

                                if (trbcing) {
                                    MODELMBEAN_LOGGER.logp(Level.FINER,
                                            RequiredModelMBebn.clbss.getNbme(),
                                        mth, "Exception: ",e);
                                }
                            }
                            if (!subtype)
                                wrongType = true;
                        }
                        if (wrongType) {
                            if (trbcing) {
                                MODELMBEAN_LOGGER.logp(Level.FINER,
                                        RequiredModelMBebn.clbss.getNbme(), mth,
                                    "Wrong response type '" + respType + "'");
                            }
                            // throw exception, didn't get
                            // bbck right bttribute type
                            throw new MBebnException(
                              new InvblidAttributeVblueException(
                                "Wrong vblue type received for get bttribute"),
                              "An exception occurred while trying to get bn " +
                              "bttribute vblue through b RequiredModelMBebn");
                        }
                    }
                }
            } else {
                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(), mth,
                            "getMethod fbiled " + bttrNbme +
                            " not in bttributeDescriptor\n");
                }
                throw new MBebnException(new
                    InvblidAttributeVblueException(
                    "Unbble to resolve bttribute vblue, " +
                    "no getMethod defined in descriptor for bttribute"),
                    "An exception occurred while trying to get bn "+
                    "bttribute vblue through b RequiredModelMBebn");
            }

        } cbtch (MBebnException mbe) {
            throw mbe;
        } cbtch (AttributeNotFoundException t) {
            throw t;
        } cbtch (Exception e) {
            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(), mth,
                        "getMethod fbiled with " + e.getMessbge() +
                        " exception type " + (e.getClbss()).toString());
            }
            throw new MBebnException(e,"An exception occurred while trying "+
                      "to get bn bttribute vblue: " + e.getMessbge());
        }

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth, "Exit");
        }

        return response;
    }

    /**
     * Returns the vblues of severbl bttributes in the ModelMBebn.
     * Executes b getAttribute for ebch bttribute nbme in the
     * bttrNbmes brrby pbssed in.
     *
     * @pbrbm bttrNbmes A String brrby of nbmes of the bttributes
     * to be retrieved.
     *
     * @return The brrby of the retrieved bttributes.
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     * {@link IllegblArgumentException}: The object nbme in pbrbmeter is
     * null or bttributes in pbrbmeter is null.
     *
     * @see #setAttributes(jbvbx.mbnbgement.AttributeList)
     */
    public AttributeList getAttributes(String[] bttrNbmes)      {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
            "getAttributes(String[])","Entry");
        }

        if (bttrNbmes == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("bttributeNbmes must not be null"),
                "Exception occurred trying to get bttributes of b "+
                "RequiredModelMBebn");

        AttributeList responseList = new AttributeList();
        for (int i = 0; i < bttrNbmes.length; i++) {
            try {
                responseList.bdd(new Attribute(bttrNbmes[i],
                                     getAttribute(bttrNbmes[i])));
            } cbtch (Exception e) {
                // ebt exceptions becbuse interfbce doesn't hbve bn
                // exception on it
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),
                        "getAttributes(String[])",
                            "Fbiled to get \"" + bttrNbmes[i] + "\": ", e);
                }
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),
                    "getAttributes(String[])","Exit");
        }

        return responseList;
    }

    /**
     * Sets the vblue of b specific bttribute of b nbmed ModelMBebn.
     *
     * If the 'setMethod' field of the bttribute's descriptor
     * contbins the nbme of b vblid operbtion descriptor, then the
     * method described by the operbtion descriptor is executed.
     * In this implementbtion, the operbtion descriptor must be specified
     * correctly bnd bssigned to the modelMBebnInfo so thbt the 'setMethod'
     * works correctly.
     * The response from the method is set bs the vblue of the bttribute
     * in the descriptor.
     *
     * <p>If currencyTimeLimit is &gt; 0, then the new vblue for the
     * bttribute is cbched in the bttribute descriptor's
     * 'vblue' field bnd the 'lbstUpdbtedTimeStbmp' field is set to
     * the current time stbmp.
     *
     * <p>If the persist field of the bttribute's descriptor is not null
     * then Persistence policy from the bttribute descriptor is used to
     * guide storing the bttribute in b persistent store.
     * <br>Store the MBebn if 'persistPolicy' field is:
     * <UL>
     * <Li> != "never"</Li>
     * <Li> = "blwbys"</Li>
     * <Li> = "onUpdbte"</Li>
     * <Li> {@literbl = "onTimer" bnd now > 'lbstPersistTime' + 'persistPeriod'}</Li>
     * <Li> {@literbl = "NoMoreOftenThbn" bnd now > 'lbstPersistTime' +
     *         'persistPeriod'}</Li>
     * </UL>
     * Do not store the MBebn if 'persistPolicy' field is:
     * <UL>
     * <Li> = "never"</Li>
     * <Li> = {@literbl = "onTimer" && now < 'lbstPersistTime' + 'persistPeriod'}</Li>
     * <Li> = "onUnregister"</Li>
     * <Li> = {@literbl = "NoMoreOftenThbn" bnd now < 'lbstPersistTime' +
     *        'persistPeriod'}</Li>
     * </UL>
     *
     * <p>The ModelMBebnInfo of the Model MBebn is stored in b file.
     *
     * @pbrbm bttribute The Attribute instbnce contbining the nbme of
     *        the bttribute to be set bnd the vblue it is to be set to.
     *
     *
     * @exception AttributeNotFoundException The specified bttribute is
     *   not bccessible in the MBebn.
     *   <br>The following cbses mby result in bn AttributeNotFoundException:
     *   <UL>
     *     <LI> No ModelMBebnAttributeInfo is found for the specified
     *          bttribute.</LI>
     *     <LI> The ModelMBebnAttributeInfo's isWritbble method returns
     *          'fblse'.</LI>
     *   </UL>
     * @exception InvblidAttributeVblueException No descriptor is defined
     *   for the specified bttribute.
     * @exception MBebnException Wrbps one of the following Exceptions:
     *   <UL>
     *     <LI> An Exception thrown by the mbnbged object's setter.</LI>
     *     <LI> A {@link ServiceNotFoundException} if b setMethod field is
     *          defined in the descriptor for the bttribute bnd the mbnbged
     *          resource is null; or if no setMethod field is defined bnd
     *          cbching is not enbbled for the bttribute.
     *          Note thbt if there is no getMethod field either, then cbching
     *          is butombticblly enbbled.</LI>
     *     <LI> {@link InvblidTbrgetObjectTypeException} The 'tbrgetType'
     *          field vblue is not 'objectReference'.</LI>
     *     <LI> An Exception thrown by the mbnbged object's getter.</LI>
     *   </UL>
     * @exception ReflectionException  Wrbps bn {@link jbvb.lbng.Exception}
     *   thrown while trying to invoke the setter.
     * @exception RuntimeOperbtionsException Wrbps bn
     *   {@link IllegblArgumentException}: The bttribute in pbrbmeter is
     *   null.
     *
     * @see #getAttribute(jbvb.lbng.String)
     **/
    public void setAttribute(Attribute bttribute)
        throws AttributeNotFoundException, InvblidAttributeVblueException,
               MBebnException, ReflectionException {
        finbl boolebn trbcing = MODELMBEAN_LOGGER.isLoggbble(Level.FINER);
        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setAttribute()","Entry");
        }

        if (bttribute == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("bttribute must not be null"),
                "Exception occurred trying to set bn bttribute of b "+
                "RequiredModelMBebn");

        /* run setMethod if there is one */
        /* return cbched vblue if its current */
        /* set cbched vblue in descriptor bnd set dbte/time */
        /* send bttribute chbnge Notificbtion */
        /* check persistence policy bnd persist if need be */
        String bttrNbme = bttribute.getNbme();
        Object bttrVblue = bttribute.getVblue();
        boolebn updbteDescriptor = fblse;

        ModelMBebnAttributeInfo bttrInfo =
            modelMBebnInfo.getAttribute(bttrNbme);

        if (bttrInfo == null)
            throw new AttributeNotFoundException("setAttribute fbiled: " +
                                               bttrNbme + " is not found ");

        Descriptor mmbDesc = modelMBebnInfo.getMBebnDescriptor();
        Descriptor bttrDescr = bttrInfo.getDescriptor();

        if (bttrDescr != null) {
            if (!bttrInfo.isWritbble())
                throw new AttributeNotFoundException("setAttribute fbiled: "
                                          + bttrNbme + " is not writbble ");

            String bttrSetMethod = (String)
                (bttrDescr.getFieldVblue("setMethod"));
            String bttrGetMethod = (String)
                (bttrDescr.getFieldVblue("getMethod"));

            String bttrType = bttrInfo.getType();
            Object currVblue = "Unknown";

            try {
                currVblue = this.getAttribute(bttrNbme);
            } cbtch (Throwbble t) {
                // OK: Defbult "Unknown" vblue used for unknown bttribute
            }

            Attribute oldAttr = new Attribute(bttrNbme, currVblue);

            /* run method from operbtions descriptor */
            if (bttrSetMethod == null) {
                if (bttrVblue != null) {
                    try {
                        finbl Clbss<?> clbzz = lobdClbss(bttrType);
                        if (! clbzz.isInstbnce(bttrVblue))  throw new
                            InvblidAttributeVblueException(clbzz.getNbme() +
                                                           " expected, "   +
                                            bttrVblue.getClbss().getNbme() +
                                                           " received.");
                    } cbtch (ClbssNotFoundException x) {
                        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                            MODELMBEAN_LOGGER.logp(Level.FINER,
                                    RequiredModelMBebn.clbss.getNbme(),
                                "setAttribute(Attribute)","Clbss " +
                                    bttrType + " for bttribute "
                                + bttrNbme + " not found: ", x);
                        }
                    }
                }
                updbteDescriptor = true;
            } else {
                invoke(bttrSetMethod,
                       (new Object[] {bttrVblue}),
                       (new String[] {bttrType}) );
            }

            /* chbnge cbched vblue */
            Object objctl = bttrDescr.getFieldVblue("currencyTimeLimit");
            String ctl;
            if (objctl != null) ctl = objctl.toString();
            else ctl = null;

            if ((ctl == null) && (mmbDesc != null)) {
                objctl = mmbDesc.getFieldVblue("currencyTimeLimit");
                if (objctl != null) ctl = objctl.toString();
                else ctl = null;
            }

            finbl boolebn updbteCbche = ((ctl != null) && !(ctl.equbls("-1")));

             if(bttrSetMethod == null  && !updbteCbche && bttrGetMethod != null)
                throw new MBebnException(new ServiceNotFoundException("No " +
                        "setMethod field is defined in the descriptor for " +
                        bttrNbme + " bttribute bnd cbching is not enbbled " +
                        "for it"));

            if (updbteCbche || updbteDescriptor) {
                if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                            "setAttribute(Attribute)",
                            "setting cbched vblue of " +
                            bttrNbme + " to " + bttrVblue);
                }

                bttrDescr.setField("vblue", bttrVblue);

                if (updbteCbche) {
                    finbl String currtime = String.vblueOf(
                        (new Dbte()).getTime());

                    bttrDescr.setField("lbstUpdbtedTimeStbmp", currtime);
                }

                bttrInfo.setDescriptor(bttrDescr);

                modelMBebnInfo.setDescriptor(bttrDescr,"bttribute");
                if (trbcing) {
                    finbl StringBuilder strb = new StringBuilder()
                    .bppend("new descriptor is ").bppend(bttrDescr)
                    .bppend(". AttributeInfo descriptor is ")
                    .bppend(bttrInfo.getDescriptor())
                    .bppend(". AttributeInfo descriptor is ")
                    .bppend(modelMBebnInfo.getDescriptor(bttrNbme,"bttribute"));
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),
                            "setAttribute(Attribute)",strb.toString());
                }

            }

            if (trbcing) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                "setAttribute(Attribute)","sending sendAttributeNotificbtion");
            }
            sendAttributeChbngeNotificbtion(oldAttr,bttribute);

        } else { // if descriptor ... else no descriptor

            if (trbcing) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            RequiredModelMBebn.clbss.getNbme(),
                        "setAttribute(Attribute)","setMethod fbiled "+bttrNbme+
                        " not in bttributeDescriptor\n");
            }

            throw new InvblidAttributeVblueException(
                      "Unbble to resolve bttribute vblue, "+
                      "no defined in descriptor for bttribute");
        } // else no descriptor

        if (trbcing) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setAttribute(Attribute)", "Exit");
        }

    }

    /**
     * Sets the vblues of bn brrby of bttributes of this ModelMBebn.
     * Executes the setAttribute() method for ebch bttribute in the list.
     *
     * @pbrbm bttributes A list of bttributes: The identificbtion of the
     * bttributes to be set bnd  the vblues they bre to be set to.
     *
     * @return  The brrby of bttributes thbt were set, with their new
     *    vblues in Attribute instbnces.
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     *   {@link IllegblArgumentException}: The object nbme in pbrbmeter
     *   is null or bttributes in pbrbmeter is null.
     *
     * @see #getAttributes
     **/
    public AttributeList setAttributes(AttributeList bttributes) {

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "setAttribute(Attribute)", "Entry");
        }

        if (bttributes == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("bttributes must not be null"),
                "Exception occurred trying to set bttributes of b "+
                "RequiredModelMBebn");

        finbl AttributeList responseList = new AttributeList();

        // Go through the list of bttributes
        for (Attribute bttr : bttributes.bsList()) {
            try {
                setAttribute(bttr);
                responseList.bdd(bttr);
            } cbtch (Exception excep) {
                responseList.remove(bttr);
            }
        }

        return responseList;
    }



    privbte ModelMBebnInfo crebteDefbultModelMBebnInfo() {
        return(new ModelMBebnInfoSupport((this.getClbss().getNbme()),
                   "Defbult ModelMBebn", null, null, null, null));
    }

    /*************************************/
    /* NotificbtionBrobdcbster Interfbce */
    /*************************************/


    privbte synchronized void writeToLog(String logFileNbme,
                                         String logEntry) throws Exception {

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "writeToLog(String, String)",
                "Notificbtion Logging to " + logFileNbme + ": " + logEntry);
        }
        if ((logFileNbme == null) || (logEntry == null)) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                    "writeToLog(String, String)",
                    "Bbd input pbrbmeters, will not log this entry.");
            }
            return;
        }

        FileOutputStrebm fos = new FileOutputStrebm(logFileNbme, true);
        try {
            PrintStrebm logOut = new PrintStrebm(fos);
            logOut.println(logEntry);
            logOut.close();
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(),
                    "writeToLog(String, String)","Successfully opened log " +
                        logFileNbme);
            }
        } cbtch (Exception e) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                        "writeToLog(String, String)",
                        "Exception " + e.toString() +
                        " trying to write to the Notificbtion log file " +
                        logFileNbme);
            }
            throw e;
        } finblly {
            fos.close();
        }
    }


    /**
     * Registers bn object which implements the NotificbtionListener
     * interfbce bs b listener.  This
     * object's 'hbndleNotificbtion()' method will be invoked when bny
     * notificbtion is issued through or by the ModelMBebn.  This does
     * not include bttributeChbngeNotificbtions.  They must be registered
     * for independently.
     *
     * @pbrbm listener The listener object which will hbndles
     *        notificbtions emitted by the registered MBebn.
     * @pbrbm filter The filter object. If null, no filtering will be
     *        performed before hbndling notificbtions.
     * @pbrbm hbndbbck The context to be sent to the listener with
     *        the notificbtion when b notificbtion is emitted.
     *
     * @exception IllegblArgumentException The listener cbnnot be null.
     *
     * @see #removeNotificbtionListener
     */
    public void bddNotificbtionListener(NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
        throws jbvb.lbng.IllegblArgumentException {
        finbl String mth = "bddNotificbtionListener(" +
                "NotificbtionListener, NotificbtionFilter, Object)";
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(), mth, "Entry");
        }

        if (listener == null)
            throw new IllegblArgumentException(
                  "notificbtion listener must not be null");

        if (generblBrobdcbster == null)
            generblBrobdcbster = new NotificbtionBrobdcbsterSupport();

        generblBrobdcbster.bddNotificbtionListener(listener, filter,
                                                   hbndbbck);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(), mth,
                    "NotificbtionListener bdded");
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        RequiredModelMBebn.clbss.getNbme(), mth, "Exit");
        }
    }

    /**
     * Removes b listener for Notificbtions from the RequiredModelMBebn.
     *
     * @pbrbm listener The listener nbme which wbs hbndling notificbtions
     *    emitted by the registered MBebn.
     *    This method will remove bll informbtion relbted to this listener.
     *
     * @exception ListenerNotFoundException The listener is not registered
     *    in the MBebn or is null.
     *
     * @see #bddNotificbtionListener
     **/
    public void removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {
        if (listener == null)
            throw new ListenerNotFoundException(
                      "Notificbtion listener is null");

        finbl String mth="removeNotificbtionListener(NotificbtionListener)";
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth, "Entry");
        }

        if (generblBrobdcbster == null)
            throw new ListenerNotFoundException(
                  "No notificbtion listeners registered");


        generblBrobdcbster.removeNotificbtionListener(listener);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth, "Exit");
        }

    }

    public void removeNotificbtionListener(NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
        throws ListenerNotFoundException {

        if (listener == null)
            throw new ListenerNotFoundException(
                      "Notificbtion listener is null");

        finbl String mth = "removeNotificbtionListener(" +
                "NotificbtionListener, NotificbtionFilter, Object)";

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth, "Entry");
        }

        if (generblBrobdcbster == null)
            throw new ListenerNotFoundException(
                  "No notificbtion listeners registered");


        generblBrobdcbster.removeNotificbtionListener(listener,filter,
                                                      hbndbbck);

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth, "Exit");
        }

    }

    public void sendNotificbtion(Notificbtion ntfyObj)
        throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "sendNotificbtion(Notificbtion)", "Entry");
        }

        if (ntfyObj == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("notificbtion object must not be "+
                                         "null"),
                "Exception occurred trying to send b notificbtion from b "+
                "RequiredModelMBebn");


        // log notificbtion if specified in descriptor
        Descriptor ntfyDesc =
            modelMBebnInfo.getDescriptor(ntfyObj.getType(),"notificbtion");
        Descriptor mmbDesc = modelMBebnInfo.getMBebnDescriptor();

        if (ntfyDesc != null) {
            String logging = (String) ntfyDesc.getFieldVblue("log");

            if (logging == null) {
                if (mmbDesc != null)
                    logging = (String) mmbDesc.getFieldVblue("log");
            }

            if ((logging != null) &&
                (logging.equblsIgnoreCbse("t") ||
                 logging.equblsIgnoreCbse("true"))) {

                String logfile = (String) ntfyDesc.getFieldVblue("logfile");
                if (logfile == null) {
                    if (mmbDesc != null)
                        logfile = (String)mmbDesc.getFieldVblue("logfile");
                }
                if (logfile != null) {
                    try {
                        writeToLog(logfile,"LogMsg: " +
                            ((new Dbte(ntfyObj.getTimeStbmp())).toString())+
                            " " + ntfyObj.getType() + " " +
                            ntfyObj.getMessbge() + " Severity = " +
                            (String)ntfyDesc.getFieldVblue("severity"));
                    } cbtch (Exception e) {
                        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINE)) {
                            MODELMBEAN_LOGGER.logp(Level.FINE,
                                    RequiredModelMBebn.clbss.getNbme(),
                                    "sendNotificbtion(Notificbtion)",
                                    "Fbiled to log " +
                                    ntfyObj.getType() + " notificbtion: ", e);
                        }
                    }
                }
            }
        }
        if (generblBrobdcbster != null) {
            generblBrobdcbster.sendNotificbtion(ntfyObj);
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),
                    "sendNotificbtion(Notificbtion)",
                    "sendNotificbtion sent provided notificbtion object");
            MODELMBEAN_LOGGER.logp(Level.FINER,
                RequiredModelMBebn.clbss.getNbme(),
                    "sendNotificbtion(Notificbtion)"," Exit");
        }

    }


    public void sendNotificbtion(String ntfyText)
        throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "sendNotificbtion(String)","Entry");
        }

        if (ntfyText == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("notificbtion messbge must not "+
                                         "be null"),
                "Exception occurred trying to send b text notificbtion "+
                "from b ModelMBebn");

        Notificbtion myNtfyObj = new Notificbtion("jmx.modelmbebn.generic",
                                                  this, 1, ntfyText);
        sendNotificbtion(myNtfyObj);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "sendNotificbtion(String)","Notificbtion sent");
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "sendNotificbtion(String)","Exit");
        }
    }

    /**
     * Returns `true' if the notificbtion `notifNbme' is found
     * in `info'. (bug 4744667)
     **/
    privbte stbtic finbl
        boolebn hbsNotificbtion(finbl ModelMBebnInfo info,
                                finbl String notifNbme) {
        try {
            if (info == null) return fblse;
            else return (info.getNotificbtion(notifNbme)!=null);
        } cbtch (MBebnException x) {
            return fblse;
        } cbtch (RuntimeOperbtionsException r) {
            return fblse;
        }
    }

    /**
     * Crebtes b defbult ModelMBebnNotificbtionInfo for GENERIC
     * notificbtion.  (bug 4744667)
     **/
    privbte stbtic finbl ModelMBebnNotificbtionInfo mbkeGenericInfo() {
        finbl Descriptor genericDescriptor = new DescriptorSupport( new
            String[] {
                "nbme=GENERIC",
                "descriptorType=notificbtion",
                "log=T",
                "severity=6",
                "displbyNbme=jmx.modelmbebn.generic"} );

        return new ModelMBebnNotificbtionInfo(new
            String[] {"jmx.modelmbebn.generic"},
            "GENERIC",
            "A text notificbtion hbs been issued by the mbnbged resource",
            genericDescriptor);
    }

    /**
     * Crebtes b defbult ModelMBebnNotificbtionInfo for ATTRIBUTE_CHANGE
     * notificbtion.  (bug 4744667)
     **/
    privbte stbtic finbl
        ModelMBebnNotificbtionInfo mbkeAttributeChbngeInfo() {
        finbl Descriptor bttributeDescriptor = new DescriptorSupport(new
            String[] {
                "nbme=ATTRIBUTE_CHANGE",
                "descriptorType=notificbtion",
                "log=T",
                "severity=6",
                "displbyNbme=jmx.bttribute.chbnge"});

        return new ModelMBebnNotificbtionInfo(new
            String[] {"jmx.bttribute.chbnge"},
            "ATTRIBUTE_CHANGE",
            "Signifies thbt bn observed MBebn bttribute vblue hbs chbnged",
            bttributeDescriptor );
    }

    /**
     * Returns the brrby of Notificbtions blwbys generbted by the
     * RequiredModelMBebn.
     * <P>
     *
     * RequiredModelMBebn mby blwbys send blso two bdditionbl notificbtions:
     * <UL>
     *   <LI> One with descriptor <code>"nbme=GENERIC,descriptorType=notificbtion,log=T,severity=6,displbyNbme=jmx.modelmbebn.generic"</code></LI>
     *   <LI> Second is b stbndbrd bttribute chbnge notificbtion
     *        with descriptor <code>"nbme=ATTRIBUTE_CHANGE,descriptorType=notificbtion,log=T,severity=6,displbyNbme=jmx.bttribute.chbnge"</code></LI>
     * </UL>
     * Thus these two notificbtions bre blwbys bdded to those specified
     * by the bpplicbtion.
     *
     * @return MBebnNotificbtionInfo[]
     *
     **/
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "getNotificbtionInfo()","Entry");
        }

        // Using hbsNotificbtion() is not optimbl, but shouldn't reblly
        // mbtter in this context...

        // hbsGeneric==true if GENERIC notificbtion is present.
        // (bug 4744667)
        finbl boolebn hbsGeneric = hbsNotificbtion(modelMBebnInfo,"GENERIC");

        // hbsAttributeChbnge==true if ATTRIBUTE_CHANGE notificbtion is
        // present.
        // (bug 4744667)
        finbl boolebn hbsAttributeChbnge =
           hbsNotificbtion(modelMBebnInfo,"ATTRIBUTE_CHANGE");

        // User supplied list of notificbtion infos.
        //
        finbl ModelMBebnNotificbtionInfo[] currInfo =
           (ModelMBebnNotificbtionInfo[])modelMBebnInfo.getNotificbtions();

        // Length of the returned list of notificbtion infos:
        //    length of user suplied list + possibly 1 for GENERIC, +
        //    possibly 1 for ATTRIBUTE_CHANGE
        //    (bug 4744667)
        finbl int len = ((currInfo==null?0:currInfo.length) +
                         (hbsGeneric?0:1) + (hbsAttributeChbnge?0:1));

        // Returned list of notificbtion infos:
        //
        finbl ModelMBebnNotificbtionInfo[] respInfo =
           new ModelMBebnNotificbtionInfo[len];

        // Preserve previous ordering (JMX 1.1)
        //

        // Counter of "stbndbrd" notificbtion inserted before user
        // supplied notificbtions.
        //
        int inserted=0;
        if (!hbsGeneric)
            // We need to bdd description for GENERIC notificbtion
            // (bug 4744667)
            respInfo[inserted++] = mbkeGenericInfo();


        if (!hbsAttributeChbnge)
            // We need to bdd description for ATTRIBUTE_CHANGE notificbtion
            // (bug 4744667)
            respInfo[inserted++] = mbkeAttributeChbngeInfo();

        // Now copy user supplied list in returned list.
        //
        finbl int count  = currInfo.length;
        finbl int offset = inserted;
        for (int j=0; j < count; j++) {
            respInfo[offset+j] = currInfo[j];
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),
                "getNotificbtionInfo()","Exit");
        }

        return respInfo;
    }


    public void bddAttributeChbngeNotificbtionListener(NotificbtionListener
                                                       inlistener,
                                                       String
                                                       inAttributeNbme,
                                                       Object inhbndbbck)
        throws MBebnException, RuntimeOperbtionsException,
               IllegblArgumentException {
        finbl String mth="bddAttributeChbngeNotificbtionListener(" +
                "NotificbtionListener, String, Object)";

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Entry");
        }

        if (inlistener == null)
            throw new IllegblArgumentException(
                  "Listener to be registered must not be null");


        if (bttributeBrobdcbster == null)
            bttributeBrobdcbster = new NotificbtionBrobdcbsterSupport();

        AttributeChbngeNotificbtionFilter currFilter =
            new AttributeChbngeNotificbtionFilter();

        MBebnAttributeInfo[] bttrInfo = modelMBebnInfo.getAttributes();
        boolebn found = fblse;
        if (inAttributeNbme == null) {
            if ((bttrInfo != null) && (bttrInfo.length>0)) {
                for (int i=0; i<bttrInfo.length; i++) {
                    currFilter.enbbleAttribute(bttrInfo[i].getNbme());
                }
            }
        } else {
            if ((bttrInfo != null) && (bttrInfo.length>0)) {
                for (int i=0; i<bttrInfo.length; i++) {
                    if (inAttributeNbme.equbls(bttrInfo[i].getNbme())) {
                        found = true;
                        currFilter.enbbleAttribute(inAttributeNbme);
                        brebk;
                    }
                }
            }
            if (!found) {
                throw new RuntimeOperbtionsException(new
                    IllegblArgumentException(
                    "The bttribute nbme does not exist"),
                    "Exception occurred trying to bdd bn "+
                    "AttributeChbngeNotificbtion listener");
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            Vector<String> enbbledAttrs = currFilter.getEnbbledAttributes();
            String s = (enbbledAttrs.size() > 1) ?
                        "[" + enbbledAttrs.firstElement() + ", ...]" :
                        enbbledAttrs.toString();
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(), mth,
                "Set bttribute chbnge filter to " + s);
        }

        bttributeBrobdcbster.bddNotificbtionListener(inlistener,currFilter,
                                                     inhbndbbck);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,
                    "Notificbtion listener bdded for " + inAttributeNbme);
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Exit");
        }
    }

    public void removeAttributeChbngeNotificbtionListener(
            NotificbtionListener inlistener, String inAttributeNbme)
        throws MBebnException, RuntimeOperbtionsException,
               ListenerNotFoundException {
        if (inlistener == null) throw new
            ListenerNotFoundException("Notificbtion listener is null");

        finbl String mth = "removeAttributeChbngeNotificbtionListener(" +
                "NotificbtionListener, String)";

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Entry");
        }


        if (bttributeBrobdcbster == null)
            throw new ListenerNotFoundException(
                  "No bttribute chbnge notificbtion listeners registered");


        MBebnAttributeInfo[] bttrInfo = modelMBebnInfo.getAttributes();
        boolebn found = fblse;
        if ((bttrInfo != null) && (bttrInfo.length>0)) {
            for (int i=0; i<bttrInfo.length; i++) {
                if (bttrInfo[i].getNbme().equbls(inAttributeNbme)) {
                    found = true;
                    brebk;
                }
            }
        }

        if ((!found) && (inAttributeNbme != null)) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Invblid bttribute nbme"),
                "Exception occurred trying to remove "+
                "bttribute chbnge notificbtion listener");
        }

        /* note: */
        /* this mby be b problem if the sbme listener is registered for
           multiple bttributes with multiple filters bnd/or hbndbbck
           objects.  It mby remove bll of them */

        bttributeBrobdcbster.removeNotificbtionListener(inlistener);

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Exit");
        }
    }

    public void sendAttributeChbngeNotificbtion(AttributeChbngeNotificbtion
                                                ntfyObj)
        throws MBebnException, RuntimeOperbtionsException {
        finbl String mth = "sendAttributeChbngeNotificbtion(" +
                "AttributeChbngeNotificbtion)";

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,"Entry");
        }

        if (ntfyObj == null)
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException(
                "bttribute chbnge notificbtion object must not be null"),
                "Exception occurred trying to send "+
                "bttribute chbnge notificbtion of b ModelMBebn");

        Object oldv = ntfyObj.getOldVblue();
        Object newv =  ntfyObj.getNewVblue();

        if (oldv == null) oldv = "null";
        if (newv == null) newv = "null";

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,
                "Sending AttributeChbngeNotificbtion with " +
                ntfyObj.getAttributeNbme() + ntfyObj.getAttributeType() +
                ntfyObj.getNewVblue() + ntfyObj.getOldVblue());
        }

        // log notificbtion if specified in descriptor
        Descriptor ntfyDesc =
            modelMBebnInfo.getDescriptor(ntfyObj.getType(),"notificbtion");
        Descriptor mmbDesc = modelMBebnInfo.getMBebnDescriptor();

        String logging, logfile;

        if (ntfyDesc != null) {
            logging =(String)  ntfyDesc.getFieldVblue("log");
            if (logging == null) {
                if (mmbDesc != null)
                    logging = (String) mmbDesc.getFieldVblue("log");
            }
            if ((logging != null) &&
                ( logging.equblsIgnoreCbse("t") ||
                  logging.equblsIgnoreCbse("true"))) {
                logfile = (String) ntfyDesc.getFieldVblue("logfile");
                if (logfile == null) {
                    if (mmbDesc != null)
                        logfile = (String)mmbDesc.getFieldVblue("logfile");
                }

                if (logfile != null) {
                    try {
                        writeToLog(logfile,"LogMsg: " +
                           ((new Dbte(ntfyObj.getTimeStbmp())).toString())+
                           " " + ntfyObj.getType() + " " +
                           ntfyObj.getMessbge() +
                           " Nbme = " + ntfyObj.getAttributeNbme() +
                           " Old vblue = " + oldv +
                           " New vblue = " + newv);
                    } cbtch (Exception e) {
                        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINE)) {
                            MODELMBEAN_LOGGER.logp(Level.FINE,
                                    RequiredModelMBebn.clbss.getNbme(),mth,
                                "Fbiled to log " + ntfyObj.getType() +
                                    " notificbtion: ", e);
                        }
                    }
                }
            }
        } else if (mmbDesc != null) {
            logging = (String) mmbDesc.getFieldVblue("log");
            if ((logging != null) &&
                ( logging.equblsIgnoreCbse("t") ||
                  logging.equblsIgnoreCbse("true") )) {
                logfile = (String) mmbDesc.getFieldVblue("logfile");

                if (logfile != null) {
                    try {
                        writeToLog(logfile,"LogMsg: " +
                           ((new Dbte(ntfyObj.getTimeStbmp())).toString())+
                           " " + ntfyObj.getType() + " " +
                           ntfyObj.getMessbge() +
                           " Nbme = " + ntfyObj.getAttributeNbme() +
                           " Old vblue = " + oldv +
                           " New vblue = " + newv);
                    } cbtch (Exception e) {
                        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINE)) {
                            MODELMBEAN_LOGGER.logp(Level.FINE,
                                    RequiredModelMBebn.clbss.getNbme(),mth,
                                "Fbiled to log " + ntfyObj.getType() +
                                    " notificbtion: ", e);
                        }
                    }
                }
            }
        }
        if (bttributeBrobdcbster != null) {
            bttributeBrobdcbster.sendNotificbtion(ntfyObj);
        }

        // XXX Revisit: This is b quickfix: it would be better to hbve b
        //     single brobdcbster. However, it is not so simple becbuse
        //     removeAttributeChbngeNotificbtionListener() should
        //     remove only listeners whose filter is bn instbnceof
        //     AttributeChbngeNotificbtionFilter.
        //
        if (generblBrobdcbster != null) {
            generblBrobdcbster.sendNotificbtion(ntfyObj);
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,
                "sent notificbtion");
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,
                "Exit");
        }
    }

    public void sendAttributeChbngeNotificbtion(Attribute inOldVbl,
                                                Attribute inNewVbl)
        throws MBebnException, RuntimeOperbtionsException {
        finbl String mth =
            "sendAttributeChbngeNotificbtion(Attribute, Attribute)";
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,
                "Entry");
        }

        // do we reblly wbnt to do this?
        if ((inOldVbl == null) || (inNewVbl == null))
            throw new RuntimeOperbtionsException(new
               IllegblArgumentException("Attribute object must not be null"),
               "Exception occurred trying to send " +
               "bttribute chbnge notificbtion of b ModelMBebn");


        if (!(inOldVbl.getNbme().equbls(inNewVbl.getNbme())))
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Attribute nbmes bre not the sbme"),
                "Exception occurred trying to send " +
                "bttribute chbnge notificbtion of b ModelMBebn");


        Object newVbl = inNewVbl.getVblue();
        Object oldVbl = inOldVbl.getVblue();
        String clbssNbme = "unknown";
        if (newVbl != null)
            clbssNbme = newVbl.getClbss().getNbme();
        if (oldVbl != null)
            clbssNbme = oldVbl.getClbss().getNbme();

        AttributeChbngeNotificbtion myNtfyObj = new
            AttributeChbngeNotificbtion(this,
                                        1,
                                        ((new Dbte()).getTime()),
                                        "AttributeChbngeDetected",
                                        inOldVbl.getNbme(),
                                        clbssNbme,
                                        inOldVbl.getVblue(),
                                        inNewVbl.getVblue());

        sendAttributeChbngeNotificbtion(myNtfyObj);

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    RequiredModelMBebn.clbss.getNbme(),mth,
                "Exit");
        }

    }

    /**
     * Return the Clbss Lobder Repository used to perform clbss lobding.
     * Subclbsses mby wish to redefine this method in order to return
     * the bppropribte {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository}
     * thbt should be used in this object.
     *
     * @return the Clbss Lobder Repository.
     *
     */
    protected ClbssLobderRepository getClbssLobderRepository() {
        return MBebnServerFbctory.getClbssLobderRepository(server);
    }

    privbte Clbss<?> lobdClbss(finbl String clbssNbme)
        throws ClbssNotFoundException {
        AccessControlContext stbck = AccessController.getContext();
        finbl ClbssNotFoundException[] cbughtException = new ClbssNotFoundException[1];

        Clbss<?> c = jbvbSecurityAccess.doIntersectionPrivilege(new PrivilegedAction<Clbss<?>>() {

            @Override
            public Clbss<?> run() {
                try {
                    ReflectUtil.checkPbckbgeAccess(clbssNbme);
                    return Clbss.forNbme(clbssNbme);
                } cbtch (ClbssNotFoundException e) {
                    finbl ClbssLobderRepository clr =
                        getClbssLobderRepository();
                    try {
                        if (clr == null) throw new ClbssNotFoundException(clbssNbme);
                        return clr.lobdClbss(clbssNbme);
                    } cbtch (ClbssNotFoundException ex) {
                        cbughtException[0] = ex;
                    }
                }
                return null;
            }
        }, stbck, bcc);

        if (cbughtException[0] != null) {
            throw cbughtException[0];
        }

        return c;
    }


    /*************************************/
    /* MBebnRegistrbtion Interfbce       */
    /*************************************/

    /**
     * Allows the MBebn to perform bny operbtions it needs before
     * being registered in the MBebn server.  If the nbme of the MBebn
     * is not specified, the MBebn cbn provide b nbme for its
     * registrbtion.  If bny exception is rbised, the MBebn will not be
     * registered in the MBebn server.
     * <P>
     * In order to ensure proper run-time sembntics of RequireModelMBebn,
     * Any subclbss of RequiredModelMBebn overlobding or overriding this
     * method should cbll <code>super.preRegister(server, nbme)</code>
     * in its own <code>preRegister</code> implementbtion.
     *
     * @pbrbm server The MBebn server in which the MBebn will be registered.
     *
     * @pbrbm nbme The object nbme of the MBebn.  This nbme is null if
     * the nbme pbrbmeter to one of the <code>crebteMBebn</code> or
     * <code>registerMBebn</code> methods in the {@link MBebnServer}
     * interfbce is null.  In thbt cbse, this method must return b
     * non-null ObjectNbme for the new MBebn.
     *
     * @return The nbme under which the MBebn is to be registered.
     * This vblue must not be null.  If the <code>nbme</code>
     * pbrbmeter is not null, it will usublly but not necessbrily be
     * the returned vblue.
     *
     * @exception jbvb.lbng.Exception This exception will be cbught by
     * the MBebn server bnd re-thrown bs bn
     * {@link jbvbx.mbnbgement.MBebnRegistrbtionException
     * MBebnRegistrbtionException}.
     */
    public ObjectNbme preRegister(MBebnServer server,
                                  ObjectNbme nbme)
        throws jbvb.lbng.Exception  {
        // Since ModelMbebnInfo cbnnot be null (otherwise exception
        // thrown bt crebtion)
        // no exception thrown on ModelMBebnInfo not set.
        if (nbme == null) throw new NullPointerException(
                     "nbme of RequiredModelMBebn to registered is null");
        this.server = server;
        return nbme;
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * registered in the MBebn server or bfter the registrbtion hbs fbiled.
     * <P>
     * In order to ensure proper run-time sembntics of RequireModelMBebn,
     * Any subclbss of RequiredModelMBebn overlobding or overriding this
     * method should cbll <code>super.postRegister(registrbtionDone)</code>
     * in its own <code>postRegister</code> implementbtion.
     *
     * @pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs
     * been successfully registered in the MBebn server. The vblue
     * fblse mebns thbt the registrbtion phbse hbs fbiled.
     */
    public void postRegister(Boolebn registrbtionDone) {
        registered = registrbtionDone.boolebnVblue();
    }

    /**
     * Allows the MBebn to perform bny operbtions it needs before
     * being unregistered by the MBebn server.
     * <P>
     * In order to ensure proper run-time sembntics of RequireModelMBebn,
     * Any subclbss of RequiredModelMBebn overlobding or overriding this
     * method should cbll <code>super.preDeregister()</code> in its own
     * <code>preDeregister</code> implementbtion.
     *
     * @exception jbvb.lbng.Exception This exception will be cbught by
     * the MBebn server bnd re-thrown bs bn
     * {@link jbvbx.mbnbgement.MBebnRegistrbtionException
     * MBebnRegistrbtionException}.
     */
    public void preDeregister() throws jbvb.lbng.Exception {
    }

    /**
     * Allows the MBebn to perform bny operbtions needed bfter hbving been
     * unregistered in the MBebn server.
     * <P>
     * In order to ensure proper run-time sembntics of RequireModelMBebn,
     * Any subclbss of RequiredModelMBebn overlobding or overriding this
     * method should cbll <code>super.postDeregister()</code> in its own
     * <code>postDeregister</code> implementbtion.
     */
    public void postDeregister() {
        registered = fblse;
        this.server=null;
    }

    privbte stbtic finbl String[] primitiveTypes;
    privbte stbtic finbl String[] primitiveWrbppers;
    stbtic {
        primitiveTypes = new String[] {
            Boolebn.TYPE.getNbme(),
            Byte.TYPE.getNbme(),
            Chbrbcter.TYPE.getNbme(),
            Short.TYPE.getNbme(),
            Integer.TYPE.getNbme(),
            Long.TYPE.getNbme(),
            Flobt.TYPE.getNbme(),
            Double.TYPE.getNbme(),
            Void.TYPE.getNbme()
        };
        primitiveWrbppers = new String[] {
            Boolebn.clbss.getNbme(),
            Byte.clbss.getNbme(),
            Chbrbcter.clbss.getNbme(),
            Short.clbss.getNbme(),
            Integer.clbss.getNbme(),
            Long.clbss.getNbme(),
            Flobt.clbss.getNbme(),
            Double.clbss.getNbme(),
            Void.clbss.getNbme()
        };
    }
}
