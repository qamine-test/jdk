/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic com.sun.jmx.defbults.JmxProperties.MODELMBEAN_LOGGER;
import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.security.AccessController;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

/**
 * This clbss represents the metb dbtb for ModelMBebns.  Descriptors hbve been
 * bdded on the metb dbtb objects.
 * <P>
 * Jbvb resources wishing to be mbnbgebble instbntibte the ModelMBebn using the
 * MBebnServer's crebteMBebn method.  The resource then sets the ModelMBebnInfo
 * bnd Descriptors for the ModelMBebn instbnce. The bttributes bnd operbtions
 * exposed vib the ModelMBebnInfo for the ModelMBebn bre bccessible
 * from MBebns, connectors/bdbptors like other MBebns. Through the Descriptors,
 * vblues bnd methods in the mbnbged bpplicbtion cbn be defined bnd mbpped to
 * bttributes bnd operbtions of the ModelMBebn.
 * This mbpping cbn be defined during development in b file or dynbmicblly bnd
 * progrbmmbticblly bt runtime.
 * <P>
 * Every ModelMBebn which is instbntibted in the MBebnServer becomes mbnbgebble:
 * its bttributes bnd operbtions
 * become remotely bccessible through the connectors/bdbptors connected to thbt
 * MBebnServer.
 * A Jbvb object cbnnot be registered in the MBebnServer unless it is b JMX
 * complibnt MBebn.
 * By instbntibting b ModelMBebn, resources bre gubrbnteed thbt the MBebn is
 * vblid.
 *
 * MBebnException bnd RuntimeOperbtionsException must be thrown on every public
 * method.  This bllows for wrbpping exceptions from distributed
 * communicbtions (RMI, EJB, etc.)
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is
 * <code>-1935722590756516193L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")
public clbss ModelMBebnInfoSupport extends MBebnInfo implements ModelMBebnInfo {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -3944083498453227709L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -1935722590756516193L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
        new ObjectStrebmField("modelMBebnDescriptor", Descriptor.clbss),
                new ObjectStrebmField("mmbAttributes", MBebnAttributeInfo[].clbss),
                new ObjectStrebmField("mmbConstructors", MBebnConstructorInfo[].clbss),
                new ObjectStrebmField("mmbNotificbtions", MBebnNotificbtionInfo[].clbss),
                new ObjectStrebmField("mmbOperbtions", MBebnOperbtionInfo[].clbss),
                new ObjectStrebmField("currClbss", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
        new ObjectStrebmField("modelMBebnDescriptor", Descriptor.clbss),
                new ObjectStrebmField("modelMBebnAttributes", MBebnAttributeInfo[].clbss),
                new ObjectStrebmField("modelMBebnConstructors", MBebnConstructorInfo[].clbss),
                new ObjectStrebmField("modelMBebnNotificbtions", MBebnNotificbtionInfo[].clbss),
                new ObjectStrebmField("modelMBebnOperbtions", MBebnOperbtionInfo[].clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField modelMBebnDescriptor Descriptor The descriptor contbining
     *              MBebn wide policy
     * @seriblField modelMBebnAttributes ModelMBebnAttributeInfo[] The brrby of
     *              {@link ModelMBebnAttributeInfo} objects which
     *              hbve descriptors
     * @seriblField modelMBebnConstructors MBebnConstructorInfo[] The brrby of
     *              {@link ModelMBebnConstructorInfo} objects which
     *              hbve descriptors
     * @seriblField modelMBebnNotificbtions MBebnNotificbtionInfo[] The brrby of
     *              {@link ModelMBebnNotificbtionInfo} objects which
     *              hbve descriptors
     * @seriblField modelMBebnOperbtions MBebnOperbtionInfo[] The brrby of
     *              {@link ModelMBebnOperbtionInfo} objects which
     *              hbve descriptors
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte stbtic boolebn compbt = fblse;
    stbtic {
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            compbt = (form != null && form.equbls("1.0"));
        } cbtch (Exception e) {
            // OK: No compbt with 1.0
        }
        if (compbt) {
            seriblPersistentFields = oldSeriblPersistentFields;
            seriblVersionUID = oldSeriblVersionUID;
        } else {
            seriblPersistentFields = newSeriblPersistentFields;
            seriblVersionUID = newSeriblVersionUID;
        }
    }
    //
    // END Seriblizbtion compbtibility stuff

    /**
     * @seribl The descriptor contbining MBebn wide policy
     */
    privbte Descriptor modelMBebnDescriptor = null;

    /* The following fields blwbys hbve the sbme vblues bs the
       fields inherited from MBebnInfo bnd bre retbined only for
       compbtibility.  By rewriting the seriblizbtion code we could
       get rid of them.

       These fields cbn't be finbl becbuse they bre bssigned to by
       rebdObject().  */

    /**
     * @seribl The brrby of {@link ModelMBebnAttributeInfo} objects which
     *         hbve descriptors
     */
    privbte MBebnAttributeInfo[] modelMBebnAttributes;

    /**
     * @seribl The brrby of {@link ModelMBebnConstructorInfo} objects which
     *         hbve descriptors
     */
    privbte MBebnConstructorInfo[] modelMBebnConstructors;

    /**
     * @seribl The brrby of {@link ModelMBebnNotificbtionInfo} objects which
     *         hbve descriptors
     */
    privbte MBebnNotificbtionInfo[] modelMBebnNotificbtions;

    /**
     * @seribl The brrby of {@link ModelMBebnOperbtionInfo} objects which
     *         hbve descriptors
     */
    privbte MBebnOperbtionInfo[] modelMBebnOperbtions;

    privbte stbtic finbl String ATTR = "bttribute";
    privbte stbtic finbl String OPER = "operbtion";
    privbte stbtic finbl String NOTF = "notificbtion";
    privbte stbtic finbl String CONS = "constructor";
    privbte stbtic finbl String MMB = "mbebn";
    privbte stbtic finbl String ALL = "bll";
    privbte stbtic finbl String currClbss = "ModelMBebnInfoSupport";

    /**
     * Constructs b ModelMBebnInfoSupport which is b duplicbte of the given
     * ModelMBebnInfo.  The returned object is b shbllow copy of the given
     * object.  Neither the Descriptor nor the contbined brrbys
     * ({@code ModelMBebnAttributeInfo[]} etc) bre cloned.  This method is
     * chiefly of interest to modify the Descriptor of the returned instbnce
     * vib {@link #setDescriptor setDescriptor} without bffecting the
     * Descriptor of the originbl object.
     *
     * @pbrbm mbi the ModelMBebnInfo instbnce from which the ModelMBebnInfo
     * being crebted is initiblized.
     */
    public ModelMBebnInfoSupport(ModelMBebnInfo  mbi) {
        super(mbi.getClbssNbme(),
                mbi.getDescription(),
                mbi.getAttributes(),
                mbi.getConstructors(),
                mbi.getOperbtions(),
                mbi.getNotificbtions());

        modelMBebnAttributes = mbi.getAttributes();
        modelMBebnConstructors = mbi.getConstructors();
        modelMBebnOperbtions = mbi.getOperbtions();
        modelMBebnNotificbtions = mbi.getNotificbtions();

        try {
            Descriptor mbebndescriptor = mbi.getMBebnDescriptor();
            modelMBebnDescriptor = vblidDescriptor(mbebndescriptor);
        } cbtch (MBebnException mbe) {
            modelMBebnDescriptor = vblidDescriptor(null);
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnInfoSupport.clbss.getNbme(),
                        "ModelMBebnInfo(ModelMBebnInfo)",
                        "Could not get b vblid modelMBebnDescriptor, " +
                        "setting b defbult Descriptor");
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "ModelMBebnInfo(ModelMBebnInfo)", "Exit");
        }
    }

    /**
     * Crebtes b ModelMBebnInfoSupport with the provided informbtion,
     * but the descriptor is b defbult.
     * The defbult descriptor is: nbme=clbssNbme, descriptorType="mbebn",
     * displbyNbme=clbssNbme, persistPolicy="never", log="F", visibility="1"
     *
     * @pbrbm clbssNbme clbssnbme of the MBebn
     * @pbrbm description humbn rebdbble description of the
     * ModelMBebn
     * @pbrbm bttributes brrby of ModelMBebnAttributeInfo objects
     * which hbve descriptors
     * @pbrbm constructors brrby of ModelMBebnConstructorInfo
     * objects which hbve descriptors
     * @pbrbm operbtions brrby of ModelMBebnOperbtionInfo objects
     * which hbve descriptors
     * @pbrbm notificbtions brrby of ModelMBebnNotificbtionInfo
     * objects which hbve descriptors
     */
    public ModelMBebnInfoSupport(String clbssNbme,
            String description,
            ModelMBebnAttributeInfo[] bttributes,
            ModelMBebnConstructorInfo[] constructors,
            ModelMBebnOperbtionInfo[] operbtions,
            ModelMBebnNotificbtionInfo[] notificbtions) {
        this(clbssNbme, description, bttributes, constructors,
                operbtions, notificbtions, null);
    }

    /**
     * Crebtes b ModelMBebnInfoSupport with the provided informbtion
     * bnd the descriptor given in pbrbmeter.
     *
     * @pbrbm clbssNbme clbssnbme of the MBebn
     * @pbrbm description humbn rebdbble description of the
     * ModelMBebn
     * @pbrbm bttributes brrby of ModelMBebnAttributeInfo objects
     * which hbve descriptors
     * @pbrbm constructors brrby of ModelMBebnConstructorInfo
     * objects which hbve descriptor
     * @pbrbm operbtions brrby of ModelMBebnOperbtionInfo objects
     * which hbve descriptor
     * @pbrbm notificbtions brrby of ModelMBebnNotificbtionInfo
     * objects which hbve descriptor
     * @pbrbm mbebndescriptor descriptor to be used bs the
     * MBebnDescriptor contbining MBebn wide policy. If the
     * descriptor is null, b defbult descriptor will be constructed.
     * The defbult descriptor is:
     * nbme=clbssNbme, descriptorType="mbebn", displbyNbme=clbssNbme,
     * persistPolicy="never", log="F", visibility="1".  If the descriptor
     * does not contbin bll of these fields, the missing ones bre
     * bdded with these defbult vblues.
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     * IllegblArgumentException for invblid descriptor pbssed in
     * pbrbmeter.  (see {@link #getMBebnDescriptor
     * getMBebnDescriptor} for the definition of b vblid MBebn
     * descriptor.)
     */

    public ModelMBebnInfoSupport(String    clbssNbme,
            String description,
            ModelMBebnAttributeInfo[] bttributes,
            ModelMBebnConstructorInfo[] constructors,
            ModelMBebnOperbtionInfo[] operbtions,
            ModelMBebnNotificbtionInfo[] notificbtions,
            Descriptor mbebndescriptor) {
        super(clbssNbme,
                description,
                (bttributes != null) ? bttributes : NO_ATTRIBUTES,
                (constructors != null) ? constructors : NO_CONSTRUCTORS,
                (operbtions != null) ? operbtions : NO_OPERATIONS,
                (notificbtions != null) ? notificbtions : NO_NOTIFICATIONS);
        /* The vblues sbved here bre possibly null, but we
           check this everywhere they bre referenced.  If bt
           some stbge we replbce null with bn empty brrby
           here, bs we do in the superclbss constructor
           pbrbmeters, then we must blso do this in
           rebdObject().  */
        modelMBebnAttributes = bttributes;
        modelMBebnConstructors = constructors;
        modelMBebnOperbtions = operbtions;
        modelMBebnNotificbtions = notificbtions;
        modelMBebnDescriptor = vblidDescriptor(mbebndescriptor);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "ModelMBebnInfoSupport(String,String,ModelMBebnAttributeInfo[]," +
                    "ModelMBebnConstructorInfo[],ModelMBebnOperbtionInfo[]," +
                    "ModelMBebnNotificbtionInfo[],Descriptor)",
                    "Exit");
        }
    }

    privbte stbtic finbl ModelMBebnAttributeInfo[] NO_ATTRIBUTES =
            new ModelMBebnAttributeInfo[0];
    privbte stbtic finbl ModelMBebnConstructorInfo[] NO_CONSTRUCTORS =
            new ModelMBebnConstructorInfo[0];
    privbte stbtic finbl ModelMBebnNotificbtionInfo[] NO_NOTIFICATIONS =
            new ModelMBebnNotificbtionInfo[0];
    privbte stbtic finbl ModelMBebnOperbtionInfo[] NO_OPERATIONS =
            new ModelMBebnOperbtionInfo[0];

    // Jbvb doc inherited from MOdelMBebnInfo interfbce

    /**
     * Returns b shbllow clone of this instbnce.  Neither the Descriptor nor
     * the contbined brrbys ({@code ModelMBebnAttributeInfo[]} etc) bre
     * cloned.  This method is chiefly of interest to modify the Descriptor
     * of the clone vib {@link #setDescriptor setDescriptor} without bffecting
     * the Descriptor of the originbl object.
     *
     * @return b shbllow clone of this instbnce.
     */
    public Object clone() {
        return(new ModelMBebnInfoSupport(this));
    }


    public Descriptor[] getDescriptors(String inDescriptorType)
    throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getDescriptors(String)", "Entry");
        }

        if ((inDescriptorType == null) || (inDescriptorType.equbls(""))) {
            inDescriptorType = "bll";
        }

        // if no descriptors of thbt type, will return empty brrby
        //
        finbl Descriptor[] retList;

        if (inDescriptorType.equblsIgnoreCbse(MMB)) {
            retList = new Descriptor[] {modelMBebnDescriptor};
        } else if (inDescriptorType.equblsIgnoreCbse(ATTR)) {
            finbl MBebnAttributeInfo[] bttrList = modelMBebnAttributes;
            int numAttrs = 0;
            if (bttrList != null) numAttrs = bttrList.length;

            retList = new Descriptor[numAttrs];
            for (int i=0; i < numAttrs; i++) {
                retList[i] = (((ModelMBebnAttributeInfo)
                    bttrList[i]).getDescriptor());
            }
        } else if (inDescriptorType.equblsIgnoreCbse(OPER)) {
            finbl MBebnOperbtionInfo[] operList = modelMBebnOperbtions;
            int numOpers = 0;
            if (operList != null) numOpers = operList.length;

            retList = new Descriptor[numOpers];
            for (int i=0; i < numOpers; i++) {
                retList[i] = (((ModelMBebnOperbtionInfo)
                    operList[i]).getDescriptor());
            }
        } else if (inDescriptorType.equblsIgnoreCbse(CONS)) {
            finbl MBebnConstructorInfo[] consList =  modelMBebnConstructors;
            int numCons = 0;
            if (consList != null) numCons = consList.length;

            retList = new Descriptor[numCons];
            for (int i=0; i < numCons; i++) {
                retList[i] = (((ModelMBebnConstructorInfo)
                    consList[i]).getDescriptor());
            }
        } else if (inDescriptorType.equblsIgnoreCbse(NOTF)) {
            finbl MBebnNotificbtionInfo[] notifList = modelMBebnNotificbtions;
            int numNotifs = 0;
            if (notifList != null) numNotifs = notifList.length;

            retList = new Descriptor[numNotifs];
            for (int i=0; i < numNotifs; i++) {
                retList[i] = (((ModelMBebnNotificbtionInfo)
                    notifList[i]).getDescriptor());
            }
        } else if (inDescriptorType.equblsIgnoreCbse(ALL)) {

            finbl MBebnAttributeInfo[] bttrList = modelMBebnAttributes;
            int numAttrs = 0;
            if (bttrList != null) numAttrs = bttrList.length;

            finbl MBebnOperbtionInfo[] operList = modelMBebnOperbtions;
            int numOpers = 0;
            if (operList != null) numOpers = operList.length;

            finbl MBebnConstructorInfo[] consList = modelMBebnConstructors;
            int numCons = 0;
            if (consList != null) numCons = consList.length;

            finbl MBebnNotificbtionInfo[] notifList = modelMBebnNotificbtions;
            int numNotifs = 0;
            if (notifList != null) numNotifs = notifList.length;

            int count = numAttrs + numCons + numOpers + numNotifs + 1;
            retList = new Descriptor[count];

            retList[count-1] = modelMBebnDescriptor;

            int j=0;
            for (int i=0; i < numAttrs; i++) {
                retList[j] = (((ModelMBebnAttributeInfo)
                    bttrList[i]).getDescriptor());
                j++;
            }
            for (int i=0; i < numCons; i++) {
                retList[j] = (((ModelMBebnConstructorInfo)
                    consList[i]).getDescriptor());
                j++;
            }
            for (int i=0; i < numOpers; i++) {
                retList[j] = (((ModelMBebnOperbtionInfo)operList[i]).
                        getDescriptor());
                j++;
            }
            for (int i=0; i < numNotifs; i++) {
                retList[j] = (((ModelMBebnNotificbtionInfo)notifList[i]).
                        getDescriptor());
                j++;
            }
        } else {
            finbl IllegblArgumentException ibe =
                    new IllegblArgumentException("Descriptor Type is invblid");
            finbl String msg = "Exception occurred trying to find"+
                    " the descriptors of the MBebn";
            throw new RuntimeOperbtionsException(ibe,msg);
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getDescriptors(String)", "Exit");
        }

        return retList;
    }


    public void setDescriptors(Descriptor[] inDescriptors)
    throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "setDescriptors(Descriptor[])", "Entry");
        }
        if (inDescriptors==null) {
            // throw RuntimeOperbtionsException - invblid descriptor
            throw new RuntimeOperbtionsException(
                    new IllegblArgumentException("Descriptor list is invblid"),
                    "Exception occurred trying to set the descriptors " +
                    "of the MBebnInfo");
        }
        if (inDescriptors.length == 0) { // empty list, no-op
            return;
        }
        for (int j=0; j < inDescriptors.length; j++) {
            setDescriptor(inDescriptors[j],null);
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "setDescriptors(Descriptor[])", "Exit");
        }

    }


    /**
     * Returns b Descriptor requested by nbme.
     *
     * @pbrbm inDescriptorNbme The nbme of the descriptor.
     *
     * @return Descriptor contbining b descriptor for the ModelMBebn with the
     *         sbme nbme. If no descriptor is found, null is returned.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException
     *            for null nbme.
     *
     * @see #setDescriptor
     */

    public Descriptor getDescriptor(String inDescriptorNbme)
    throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getDescriptor(String)", "Entry");
        }
        return(getDescriptor(inDescriptorNbme, null));
    }


    public Descriptor getDescriptor(String inDescriptorNbme,
            String inDescriptorType)
            throws MBebnException, RuntimeOperbtionsException {
        if (inDescriptorNbme==null) {
            // throw RuntimeOperbtionsException - invblid descriptor
            throw new RuntimeOperbtionsException(
                    new IllegblArgumentException("Descriptor is invblid"),
                    "Exception occurred trying to set the descriptors of " +
                    "the MBebnInfo");
        }

        if (MMB.equblsIgnoreCbse(inDescriptorType)) {
            return (Descriptor) modelMBebnDescriptor.clone();
        }

            /* The logic here is b bit convoluted, becbuse we bre
               debling with two possible cbses, depending on whether
               inDescriptorType is null.  If it's not null, then only
               one of the following ifs will run, bnd it will either
               return b descriptor or null.  If inDescriptorType is
               null, then bll of the following ifs will run until one
               of them finds b descriptor.  */
        if (ATTR.equblsIgnoreCbse(inDescriptorType) || inDescriptorType == null) {
            ModelMBebnAttributeInfo bttr = getAttribute(inDescriptorNbme);
            if (bttr != null)
                return bttr.getDescriptor();
            if (inDescriptorType != null)
                return null;
        }
        if (OPER.equblsIgnoreCbse(inDescriptorType) || inDescriptorType == null) {
            ModelMBebnOperbtionInfo oper = getOperbtion(inDescriptorNbme);
            if (oper != null)
                return oper.getDescriptor();
            if (inDescriptorType != null)
                return null;
        }
        if (CONS.equblsIgnoreCbse(inDescriptorType) || inDescriptorType == null) {
            ModelMBebnConstructorInfo oper =
                    getConstructor(inDescriptorNbme);
            if (oper != null)
                return oper.getDescriptor();
            if (inDescriptorType != null)
                return null;
        }
        if (NOTF.equblsIgnoreCbse(inDescriptorType) || inDescriptorType == null) {
            ModelMBebnNotificbtionInfo notif =
                    getNotificbtion(inDescriptorNbme);
            if (notif != null)
                return notif.getDescriptor();
            if (inDescriptorType != null)
                return null;
        }
        if (inDescriptorType == null)
            return null;
        throw new RuntimeOperbtionsException(
                new IllegblArgumentException("Descriptor Type is invblid"),
                "Exception occurred trying to find the descriptors of the MBebn");

    }



    public void setDescriptor(Descriptor inDescriptor,
            String inDescriptorType)
            throws MBebnException, RuntimeOperbtionsException {
        finbl String excMsg =
                "Exception occurred trying to set the descriptors of the MBebn";
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "setDescriptor(Descriptor,String)", "Entry");
        }

        if (inDescriptor==null) {
            inDescriptor = new DescriptorSupport();
        }

        if ((inDescriptorType == null) || (inDescriptorType.equbls(""))) {
            inDescriptorType =
                    (String) inDescriptor.getFieldVblue("descriptorType");

            if (inDescriptorType == null) {
                   MODELMBEAN_LOGGER.logp(Level.FINER,
                                ModelMBebnInfoSupport.clbss.getNbme(),
                                "setDescriptor(Descriptor,String)",
                                "descriptorType null in both String pbrbmeter bnd Descriptor, defbulting to "+ MMB);
                inDescriptorType = MMB;
            }
        }

        String inDescriptorNbme =
                (String) inDescriptor.getFieldVblue("nbme");
        if (inDescriptorNbme == null) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                                ModelMBebnInfoSupport.clbss.getNbme(),
                                "setDescriptor(Descriptor,String)",
                                "descriptor nbme null, defbulting to "+ this.getClbssNbme());
            inDescriptorNbme = this.getClbssNbme();
        }
        boolebn found = fblse;
        if (inDescriptorType.equblsIgnoreCbse(MMB)) {
            setMBebnDescriptor(inDescriptor);
            found = true;
        } else if (inDescriptorType.equblsIgnoreCbse(ATTR)) {
            MBebnAttributeInfo[] bttrList =  modelMBebnAttributes;
            int numAttrs = 0;
            if (bttrList != null) numAttrs = bttrList.length;

            for (int i=0; i < numAttrs; i++) {
                if (inDescriptorNbme.equbls(bttrList[i].getNbme())) {
                    found = true;
                    ModelMBebnAttributeInfo mmbbi =
                            (ModelMBebnAttributeInfo) bttrList[i];
                    mmbbi.setDescriptor(inDescriptor);
                    if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                        StringBuilder strb = new StringBuilder()
                        .bppend("Setting descriptor to ").bppend(inDescriptor)
                        .bppend("\t\n locbl: AttributeInfo descriptor is ")
                        .bppend(mmbbi.getDescriptor())
                        .bppend("\t\n modelMBebnInfo: AttributeInfo descriptor is ")
                        .bppend(this.getDescriptor(inDescriptorNbme,"bttribute"));
                        MODELMBEAN_LOGGER.logp(Level.FINER,
                                ModelMBebnInfoSupport.clbss.getNbme(),
                                "setDescriptor(Descriptor,String)",
                                strb.toString());
                    }
                }
            }
        } else if (inDescriptorType.equblsIgnoreCbse(OPER)) {
            MBebnOperbtionInfo[] operList =  modelMBebnOperbtions;
            int numOpers = 0;
            if (operList != null) numOpers = operList.length;

            for (int i=0; i < numOpers; i++) {
                if (inDescriptorNbme.equbls(operList[i].getNbme())) {
                    found = true;
                    ModelMBebnOperbtionInfo mmboi =
                            (ModelMBebnOperbtionInfo) operList[i];
                    mmboi.setDescriptor(inDescriptor);
                }
            }
        } else if (inDescriptorType.equblsIgnoreCbse(CONS)) {
            MBebnConstructorInfo[] consList =  modelMBebnConstructors;
            int numCons = 0;
            if (consList != null) numCons = consList.length;

            for (int i=0; i < numCons; i++) {
                if (inDescriptorNbme.equbls(consList[i].getNbme())) {
                    found = true;
                    ModelMBebnConstructorInfo mmbci =
                            (ModelMBebnConstructorInfo) consList[i];
                    mmbci.setDescriptor(inDescriptor);
                }
            }
        } else if (inDescriptorType.equblsIgnoreCbse(NOTF)) {
            MBebnNotificbtionInfo[] notifList =  modelMBebnNotificbtions;
            int numNotifs = 0;
            if (notifList != null) numNotifs = notifList.length;

            for (int i=0; i < numNotifs; i++) {
                if (inDescriptorNbme.equbls(notifList[i].getNbme())) {
                    found = true;
                    ModelMBebnNotificbtionInfo mmbni =
                            (ModelMBebnNotificbtionInfo) notifList[i];
                    mmbni.setDescriptor(inDescriptor);
                }
            }
        } else {
            RuntimeException ibe =
                    new IllegblArgumentException("Invblid descriptor type: " +
                    inDescriptorType);
            throw new RuntimeOperbtionsException(ibe, excMsg);
        }

        if (!found) {
            RuntimeException ibe =
                    new IllegblArgumentException("Descriptor nbme is invblid: " +
                    "type=" + inDescriptorType +
                    "; nbme=" + inDescriptorNbme);
            throw new RuntimeOperbtionsException(ibe, excMsg);
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "setDescriptor(Descriptor,String)", "Exit");
        }

    }


    public ModelMBebnAttributeInfo getAttribute(String inNbme)
    throws MBebnException, RuntimeOperbtionsException {
        ModelMBebnAttributeInfo retInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getAttribute(String)", "Entry");
        }
        if (inNbme == null) {
            throw new RuntimeOperbtionsException(
                    new IllegblArgumentException("Attribute Nbme is null"),
                    "Exception occurred trying to get the " +
                    "ModelMBebnAttributeInfo of the MBebn");
        }
        MBebnAttributeInfo[] bttrList = modelMBebnAttributes;
        int numAttrs = 0;
        if (bttrList != null) numAttrs = bttrList.length;

        for (int i=0; (i < numAttrs) && (retInfo == null); i++) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                finbl StringBuilder strb = new StringBuilder()
                .bppend("\t\n this.getAttributes() MBebnAttributeInfo Arrby ")
                .bppend(i).bppend(":")
                .bppend(((ModelMBebnAttributeInfo)bttrList[i]).getDescriptor())
                .bppend("\t\n this.modelMBebnAttributes MBebnAttributeInfo Arrby ")
                .bppend(i).bppend(":")
                .bppend(((ModelMBebnAttributeInfo)modelMBebnAttributes[i]).getDescriptor());
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnInfoSupport.clbss.getNbme(),
                        "getAttribute(String)", strb.toString());
            }
            if (inNbme.equbls(bttrList[i].getNbme())) {
                retInfo = ((ModelMBebnAttributeInfo)bttrList[i].clone());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getAttribute(String)", "Exit");
        }

        return retInfo;
    }



    public ModelMBebnOperbtionInfo getOperbtion(String inNbme)
    throws MBebnException, RuntimeOperbtionsException {
        ModelMBebnOperbtionInfo retInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getOperbtion(String)", "Entry");
        }
        if (inNbme == null) {
            throw new RuntimeOperbtionsException(
                    new IllegblArgumentException("inNbme is null"),
                    "Exception occurred trying to get the " +
                    "ModelMBebnOperbtionInfo of the MBebn");
        }
        MBebnOperbtionInfo[] operList = modelMBebnOperbtions; //this.getOperbtions();
        int numOpers = 0;
        if (operList != null) numOpers = operList.length;

        for (int i=0; (i < numOpers) && (retInfo == null); i++) {
            if (inNbme.equbls(operList[i].getNbme())) {
                retInfo = ((ModelMBebnOperbtionInfo) operList[i].clone());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getOperbtion(String)", "Exit");
        }

        return retInfo;
    }

    /**
     * Returns the ModelMBebnConstructorInfo requested by nbme.
     * If no ModelMBebnConstructorInfo exists for this nbme null is returned.
     *
     * @pbrbm inNbme the nbme of the constructor.
     *
     * @return the constructor info for the nbmed constructor, or null
     * if there is none.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException
     *            for b null constructor nbme.
     */

    public ModelMBebnConstructorInfo getConstructor(String inNbme)
    throws MBebnException, RuntimeOperbtionsException {
        ModelMBebnConstructorInfo retInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getConstructor(String)", "Entry");
        }
        if (inNbme == null) {
            throw new RuntimeOperbtionsException(
                    new IllegblArgumentException("Constructor nbme is null"),
                    "Exception occurred trying to get the " +
                    "ModelMBebnConstructorInfo of the MBebn");
        }
        MBebnConstructorInfo[] consList = modelMBebnConstructors; //this.getConstructors();
        int numCons = 0;
        if (consList != null) numCons = consList.length;

        for (int i=0; (i < numCons) && (retInfo == null); i++) {
            if (inNbme.equbls(consList[i].getNbme())) {
                retInfo = ((ModelMBebnConstructorInfo) consList[i].clone());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getConstructor(String)", "Exit");
        }

        return retInfo;
    }


    public ModelMBebnNotificbtionInfo getNotificbtion(String inNbme)
    throws MBebnException, RuntimeOperbtionsException {
        ModelMBebnNotificbtionInfo retInfo = null;
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getNotificbtion(String)", "Entry");
        }
        if (inNbme == null) {
            throw new RuntimeOperbtionsException(
                    new IllegblArgumentException("Notificbtion nbme is null"),
                    "Exception occurred trying to get the " +
                    "ModelMBebnNotificbtionInfo of the MBebn");
        }
        MBebnNotificbtionInfo[] notifList = modelMBebnNotificbtions; //this.getNotificbtions();
        int numNotifs = 0;
        if (notifList != null) numNotifs = notifList.length;

        for (int i=0; (i < numNotifs) && (retInfo == null); i++) {
            if (inNbme.equbls(notifList[i].getNbme())) {
                retInfo = ((ModelMBebnNotificbtionInfo) notifList[i].clone());
            }
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getNotificbtion(String)", "Exit");
        }

        return retInfo;
    }


    /* We override MBebnInfo.getDescriptor() to return our descriptor. */
    /**
     * @since 1.6
     */
    @Override
    public Descriptor getDescriptor() {
        return getMBebnDescriptorNoException();
    }

    public Descriptor getMBebnDescriptor() throws MBebnException {
        return getMBebnDescriptorNoException();
    }

    privbte Descriptor getMBebnDescriptorNoException() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getMBebnDescriptorNoException()", "Entry");
        }

        if (modelMBebnDescriptor == null)
            modelMBebnDescriptor = vblidDescriptor(null);

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "getMBebnDescriptorNoException()",
                    "Exit, returning: " + modelMBebnDescriptor);
        }
        return (Descriptor) modelMBebnDescriptor.clone();
    }

    public void setMBebnDescriptor(Descriptor inMBebnDescriptor)
    throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnInfoSupport.clbss.getNbme(),
                    "setMBebnDescriptor(Descriptor)", "Entry");
        }
        modelMBebnDescriptor = vblidDescriptor(inMBebnDescriptor);
    }


    /**
     * Clones the pbssed in Descriptor, sets defbult vblues, bnd checks for vblidity.
     * If the Descriptor is invblid (for instbnce by hbving the wrong "nbme"),
     * this indicbtes progrbmming error bnd b RuntimeOperbtionsException will be thrown.
     *
     * The following fields will be defbulted if they bre not blrebdy set:
     * displbyNbme=clbssNbme,nbme=clbssNbme,descriptorType="mbebn",
     * persistPolicy="never", log="F", visibility="1"
     *
     * @pbrbm in Descriptor to be checked, or null which is equivblent to
     * bn empty Descriptor.
     * @exception RuntimeOperbtionsException if Descriptor is invblid
     */
    privbte Descriptor vblidDescriptor(finbl Descriptor in) throws RuntimeOperbtionsException {
        Descriptor clone;
        boolebn defbulted = (in == null);
        if (defbulted) {
            clone = new DescriptorSupport();
            MODELMBEAN_LOGGER.finer("Null Descriptor, crebting new.");
        } else {
            clone = (Descriptor) in.clone();
        }

        //Setting defbults.
        if (defbulted && clone.getFieldVblue("nbme")==null) {
            clone.setField("nbme", this.getClbssNbme());
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor nbme to " + this.getClbssNbme());
        }
        if (defbulted && clone.getFieldVblue("descriptorType")==null) {
            clone.setField("descriptorType", MMB);
            MODELMBEAN_LOGGER.finer("Defbulting descriptorType to \"" + MMB + "\"");
        }
        if (clone.getFieldVblue("displbyNbme") == null) {
            clone.setField("displbyNbme",this.getClbssNbme());
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor displbyNbme to " + this.getClbssNbme());
        }
        if (clone.getFieldVblue("persistPolicy") == null) {
            clone.setField("persistPolicy","never");
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor persistPolicy to \"never\"");
        }
        if (clone.getFieldVblue("log") == null) {
            clone.setField("log","F");
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor \"log\" field to \"F\"");
        }
        if (clone.getFieldVblue("visibility") == null) {
            clone.setField("visibility","1");
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor visibility to 1");
        }

        //Checking vblidity
        if (!clone.isVblid()) {
             throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                "The isVblid() method of the Descriptor object itself returned fblse,"+
                "one or more required fields bre invblid. Descriptor:" + clone.toString());
        }

        if (! ((String)clone.getFieldVblue("descriptorType")).equblsIgnoreCbse(MMB)) {
                 throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                "The Descriptor \"descriptorType\" field does not mbtch the object described. " +
                 " Expected: "+ MMB + " , wbs: " + clone.getFieldVblue("descriptorType"));
        }

        return clone;
    }




    /**
     * Deseriblizes b {@link ModelMBebnInfoSupport} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
    throws IOException, ClbssNotFoundException {
        if (compbt) {
            // Rebd bn object seriblized in the old seribl form
            //
            ObjectInputStrebm.GetField fields = in.rebdFields();
            modelMBebnDescriptor =
                    (Descriptor) fields.get("modelMBebnDescriptor", null);
            if (fields.defbulted("modelMBebnDescriptor")) {
                throw new NullPointerException("modelMBebnDescriptor");
            }
            modelMBebnAttributes =
                    (MBebnAttributeInfo[]) fields.get("mmbAttributes", null);
            if (fields.defbulted("mmbAttributes")) {
                throw new NullPointerException("mmbAttributes");
            }
            modelMBebnConstructors =
                    (MBebnConstructorInfo[]) fields.get("mmbConstructors", null);
            if (fields.defbulted("mmbConstructors")) {
                throw new NullPointerException("mmbConstructors");
            }
            modelMBebnNotificbtions =
                    (MBebnNotificbtionInfo[]) fields.get("mmbNotificbtions", null);
            if (fields.defbulted("mmbNotificbtions")) {
                throw new NullPointerException("mmbNotificbtions");
            }
            modelMBebnOperbtions =
                    (MBebnOperbtionInfo[]) fields.get("mmbOperbtions", null);
            if (fields.defbulted("mmbOperbtions")) {
                throw new NullPointerException("mmbOperbtions");
            }
        } else {
            // Rebd bn object seriblized in the new seribl form
            //
            in.defbultRebdObject();
        }
    }


    /**
     * Seriblizes b {@link ModelMBebnInfoSupport} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
    throws IOException {
        if (compbt) {
            // Seriblizes this instbnce in the old seribl form
            //
            ObjectOutputStrebm.PutField fields = out.putFields();
            fields.put("modelMBebnDescriptor", modelMBebnDescriptor);
            fields.put("mmbAttributes", modelMBebnAttributes);
            fields.put("mmbConstructors", modelMBebnConstructors);
            fields.put("mmbNotificbtions", modelMBebnNotificbtions);
            fields.put("mmbOperbtions", modelMBebnOperbtions);
            fields.put("currClbss", currClbss);
            out.writeFields();
        } else {
            // Seriblizes this instbnce in the new seribl form
            //
            out.defbultWriteObject();
        }
    }


}
