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

import stbtic com.sun.jmx.defbults.JmxProperties.MODELMBEAN_LOGGER;
import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessController;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.DescriptorAccess;
import jbvbx.mbnbgement.DescriptorKey;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.MBebnPbrbmeterInfo;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

/**
 * <p>The ModelMBebnOperbtionInfo object describes b mbnbgement operbtion of
 * the ModelMBebn.  It is b subclbss of MBebnOperbtionInfo with the bddition
 * of bn bssocibted Descriptor bnd bn implementbtion of the DescriptorAccess
 * interfbce.</p>
 *
 * <P id="descriptor">
 * The fields in the descriptor bre defined, but not limited to, the following.
 * Note thbt when the Type in this tbble is Number, b String thbt is the decimbl
 * representbtion of b Long cbn blso be used.</P>
 *
 * <tbble border="1" cellpbdding="5" summbry="ModelMBebnOperbtionInfo Fields">
 * <tr><th>Nbme</th><th>Type</th><th>Mebning</th></tr>
 * <tr><td>nbme</td><td>String</td>
 *     <td>Operbtion nbme.</td></tr>
 * <tr><td>descriptorType</td><td>String</td>
 *     <td>Must be "operbtion".</td></tr>
 * <tr><td>clbss</td><td>String</td>
 *     <td>Clbss where method is defined (fully qublified).</td></tr>
 * <tr><td>role</td><td>String</td>
 *     <td>Must be "operbtion", "getter", or "setter".</td></tr>
 * <tr><td>tbrgetObject</td><td>Object</td>
 *     <td>Object on which to execute this method.</td></tr>
 * <tr><td>tbrgetType</td><td>String</td>
 *     <td>type of object reference for tbrgetObject. Cbn be:
 *         ObjectReference | Hbndle | EJBHbndle | IOR | RMIReference.</td></tr>
 * <tr><td>vblue</td><td>Object</td>
 *     <td>Cbched vblue for operbtion.</td></tr>
 * <tr><td>displbyNbme</td><td>String</td>
 *     <td>Humbn rebdbble displby nbme of the operbtion.</td>
 * <tr><td>currencyTimeLimit</td><td>Number</td>
 *     <td>How long cbched vblue is vblid.</td></tr>
 * <tr><td>lbstUpdbtedTimeStbmp</td><td>Number</td>
 *     <td>When cbched vblue wbs set.</td></tr>
 * <tr><td>visibility</td><td>Number</td>
 *     <td>1-4 where 1: blwbys visible 4: rbrely visible.</td></tr>
 * <tr><td>presentbtionString</td><td>String</td>
 *     <td>XML formbtted string to describe how to present operbtion</td></tr>
 * </tbble>
 *
 * <p>The defbult descriptor will hbve nbme, descriptorType, displbyNbme bnd
 * role fields set.  The defbult vblue of the nbme bnd displbyNbme fields is
 * the operbtion nbme.</p>
 *
 * <p><b>Note:</b> becbuse of inconsistencies in previous versions of
 * this specificbtion, it is recommended not to use negbtive or zero
 * vblues for <code>currencyTimeLimit</code>.  To indicbte thbt b
 * cbched vblue is never vblid, omit the
 * <code>currencyTimeLimit</code> field.  To indicbte thbt it is
 * blwbys vblid, use b very lbrge number for this field.</p>
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>6532732096650090465L</code>.
 *
 * @since 1.5
 */

@SuppressWbrnings("seribl")  // seriblVersionUID is not constbnt
public clbss ModelMBebnOperbtionInfo extends MBebnOperbtionInfo
         implements DescriptorAccess
{

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 9087646304346171239L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 6532732096650090465L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("operbtionDescriptor", Descriptor.clbss),
      new ObjectStrebmField("currClbss", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("operbtionDescriptor", Descriptor.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField operbtionDescriptor Descriptor The descriptor
     * contbining the bppropribte metbdbtb for this instbnce
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
         * @seribl The descriptor contbining the bppropribte metbdbtb for this instbnce
         */
        privbte Descriptor operbtionDescriptor = vblidDescriptor(null);

        privbte stbtic finbl String currClbss = "ModelMBebnOperbtionInfo";

        /**
         * Constructs b ModelMBebnOperbtionInfo object with b defbult
         * descriptor. The {@link Descriptor} of the constructed
         * object will include fields contributed by bny bnnotbtions
         * on the {@code Method} object thbt contbin the {@link
         * DescriptorKey} metb-bnnotbtion.
         *
         * @pbrbm operbtionMethod The jbvb.lbng.reflect.Method object
         * describing the MBebn operbtion.
         * @pbrbm description A humbn rebdbble description of the operbtion.
         */

        public ModelMBebnOperbtionInfo(String description,
                                       Method operbtionMethod)
        {
                super(description, operbtionMethod);
                // crebte defbult descriptor
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnOperbtionInfo.clbss.getNbme(),
                            "ModelMBebnOperbtionInfo(String,Method)",
                            "Entry");
                }
                operbtionDescriptor = vblidDescriptor(null);
        }

        /**
         * Constructs b ModelMBebnOperbtionInfo object. The {@link
         * Descriptor} of the constructed object will include fields
         * contributed by bny bnnotbtions on the {@code Method} object
         * thbt contbin the {@link DescriptorKey} metb-bnnotbtion.
         *
         * @pbrbm operbtionMethod The jbvb.lbng.reflect.Method object
         * describing the MBebn operbtion.
         * @pbrbm description A humbn rebdbble description of the
         * operbtion.
         * @pbrbm descriptor An instbnce of Descriptor contbining the
         * bppropribte metbdbtb for this instbnce of the
         * ModelMBebnOperbtionInfo.  If it is null b defbult
         * descriptor will be crebted. If the descriptor does not
         * contbin the fields
         * "displbyNbme" or "role", the missing ones bre bdded with
         * their defbult vblues.
         *
         * @exception RuntimeOperbtionsException Wrbps bn
         * IllegblArgumentException. The descriptor is invblid; or
         * descriptor field "nbme" is not equbl to
         * operbtion nbme; or descriptor field "DescriptorType" is
         * not equbl to "operbtion"; or descriptor
         * optionbl field "role" is present but not equbl to "operbtion",
         * "getter", or "setter".
         *
         */

        public ModelMBebnOperbtionInfo(String description,
                                       Method operbtionMethod,
                                       Descriptor descriptor)
        {

                super(description, operbtionMethod);
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnOperbtionInfo.clbss.getNbme(),
                            "ModelMBebnOperbtionInfo(String,Method,Descriptor)",
                            "Entry");
                }
                operbtionDescriptor = vblidDescriptor(descriptor);
        }

        /**
        * Constructs b ModelMBebnOperbtionInfo object with b defbult descriptor.
        *
        * @pbrbm nbme The nbme of the method.
        * @pbrbm description A humbn rebdbble description of the operbtion.
        * @pbrbm signbture MBebnPbrbmeterInfo objects describing the
        * pbrbmeters(brguments) of the method.
        * @pbrbm type The type of the method's return vblue.
        * @pbrbm impbct The impbct of the method, one of INFO, ACTION,
        * ACTION_INFO, UNKNOWN.
        */

        public ModelMBebnOperbtionInfo(String nbme,
                                       String description,
                                       MBebnPbrbmeterInfo[] signbture,
                                       String type,
                                       int impbct)
        {

                super(nbme, description, signbture, type, impbct);
                // crebte defbult descriptor
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnOperbtionInfo.clbss.getNbme(),
                            "ModelMBebnOperbtionInfo(" +
                            "String,String,MBebnPbrbmeterInfo[],String,int)",
                            "Entry");
                }
                operbtionDescriptor = vblidDescriptor(null);
        }

        /**
        * Constructs b ModelMBebnOperbtionInfo object.
        *
        * @pbrbm nbme The nbme of the method.
        * @pbrbm description A humbn rebdbble description of the operbtion.
        * @pbrbm signbture MBebnPbrbmeterInfo objects describing the
        * pbrbmeters(brguments) of the method.
        * @pbrbm type The type of the method's return vblue.
        * @pbrbm impbct The impbct of the method, one of INFO, ACTION,
        * ACTION_INFO, UNKNOWN.
        * @pbrbm descriptor An instbnce of Descriptor contbining the
        * bppropribte metbdbtb for this instbnce of the
        * MBebnOperbtionInfo. If it is null then b defbult descriptor
        * will be crebted.  If the descriptor does not contbin
        * fields "displbyNbme" or "role",
        * the missing ones bre bdded with their defbult vblues.
        *
        * @exception RuntimeOperbtionsException Wrbps bn
        * IllegblArgumentException. The descriptor is invblid; or
        * descriptor field "nbme" is not equbl to
        * operbtion nbme; or descriptor field "DescriptorType" is
        * not equbl to "operbtion"; or descriptor optionbl
        * field "role" is present but not equbl to "operbtion", "getter", or
        * "setter".
        */

        public ModelMBebnOperbtionInfo(String nbme,
                                       String description,
                                       MBebnPbrbmeterInfo[] signbture,
                                       String type,
                                       int impbct,
                                       Descriptor descriptor)
        {
                super(nbme, description, signbture, type, impbct);
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnOperbtionInfo.clbss.getNbme(),
                            "ModelMBebnOperbtionInfo(String,String," +
                            "MBebnPbrbmeterInfo[],String,int,Descriptor)",
                            "Entry");
                }
                operbtionDescriptor = vblidDescriptor(descriptor);
        }

        /**
         * Constructs b new ModelMBebnOperbtionInfo object from this
         * ModelMBebnOperbtion Object.
         *
         * @pbrbm inInfo the ModelMBebnOperbtionInfo to be duplicbted
         *
         */

        public ModelMBebnOperbtionInfo(ModelMBebnOperbtionInfo inInfo)
        {
                super(inInfo.getNbme(),
                          inInfo.getDescription(),
                          inInfo.getSignbture(),
                          inInfo.getReturnType(),
                          inInfo.getImpbct());
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnOperbtionInfo.clbss.getNbme(),
                            "ModelMBebnOperbtionInfo(ModelMBebnOperbtionInfo)",
                            "Entry");
                }
                Descriptor newDesc = inInfo.getDescriptor();
                operbtionDescriptor = vblidDescriptor(newDesc);
        }

        /**
        * Crebtes bnd returns b new ModelMBebnOperbtionInfo which is b
        * duplicbte of this ModelMBebnOperbtionInfo.
        *
        */

        public Object clone ()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnOperbtionInfo.clbss.getNbme(),
                        "clone()", "Entry");
            }
                return(new ModelMBebnOperbtionInfo(this)) ;
        }

        /**
         * Returns b copy of the bssocibted Descriptor of the
         * ModelMBebnOperbtionInfo.
         *
         * @return Descriptor bssocibted with the
         * ModelMBebnOperbtionInfo object.
         *
         * @see #setDescriptor
         */

        public Descriptor getDescriptor()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnOperbtionInfo.clbss.getNbme(),
                        "getDescriptor()", "Entry");
            }
            if (operbtionDescriptor == null) {
                operbtionDescriptor = vblidDescriptor(null);
            }

            return((Descriptor) operbtionDescriptor.clone());
        }

        /**
         * Sets bssocibted Descriptor (full replbce) for the
         * ModelMBebnOperbtionInfo If the new Descriptor is null, then
         * the bssocibted Descriptor reverts to b defbult descriptor.
         * The Descriptor is vblidbted before it is bssigned.  If the
         * new Descriptor is invblid, then b
         * RuntimeOperbtionsException wrbpping bn
         * IllegblArgumentException is thrown.
         *
         * @pbrbm inDescriptor replbces the Descriptor bssocibted with the
         * ModelMBebnOperbtion.
         *
         * @exception RuntimeOperbtionsException Wrbps bn
         * IllegblArgumentException for invblid Descriptor.
         *
         * @see #getDescriptor
         */
        public void setDescriptor(Descriptor inDescriptor)
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnOperbtionInfo.clbss.getNbme(),
                        "setDescriptor(Descriptor)", "Entry");
            }
            operbtionDescriptor = vblidDescriptor(inDescriptor);
        }

        /**
        * Returns b string contbining the entire contents of the
        * ModelMBebnOperbtionInfo in humbn rebdbble form.
        */
        public String toString()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnOperbtionInfo.clbss.getNbme(),
                        "toString()", "Entry");
            }
                String retStr =
                    "ModelMBebnOperbtionInfo: " + this.getNbme() +
                    " ; Description: " + this.getDescription() +
                    " ; Descriptor: " + this.getDescriptor() +
                    " ; ReturnType: " + this.getReturnType() +
                    " ; Signbture: ";
                MBebnPbrbmeterInfo[] pTypes = this.getSignbture();
                for (int i=0; i < pTypes.length; i++)
                {
                        retStr = retStr.concbt((pTypes[i]).getType() + ", ");
                }
                return retStr;
        }

        /**
         * Clones the pbssed in Descriptor, sets defbult vblues, bnd checks for vblidity.
         * If the Descriptor is invblid (for instbnce by hbving the wrong "nbme"),
         * this indicbtes progrbmming error bnd b RuntimeOperbtionsException will be thrown.
         *
         * The following fields will be defbulted if they bre not blrebdy set:
         * displbyNbme=this.getNbme(),nbme=this.getNbme(),
         * descriptorType="operbtion", role="operbtion"
         *
         *
         * @pbrbm in Descriptor to be checked, or null which is equivblent to
         * bn empty Descriptor.
         * @exception RuntimeOperbtionsException if Descriptor is invblid
         */
        privbte Descriptor vblidDescriptor(finbl Descriptor in)
        throws RuntimeOperbtionsException {
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
                clone.setField("nbme", this.getNbme());
                MODELMBEAN_LOGGER.finer("Defbulting Descriptor nbme to " + this.getNbme());
            }
            if (defbulted && clone.getFieldVblue("descriptorType")==null) {
                clone.setField("descriptorType", "operbtion");
                MODELMBEAN_LOGGER.finer("Defbulting descriptorType to \"operbtion\"");
            }
            if (clone.getFieldVblue("displbyNbme") == null) {
                clone.setField("displbyNbme",this.getNbme());
                MODELMBEAN_LOGGER.finer("Defbulting Descriptor displbyNbme to " + this.getNbme());
            }
            if (clone.getFieldVblue("role") == null) {
                clone.setField("role","operbtion");
                MODELMBEAN_LOGGER.finer("Defbulting Descriptor role field to \"operbtion\"");
            }

            //Checking vblidity
            if (!clone.isVblid()) {
                 throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The isVblid() method of the Descriptor object itself returned fblse,"+
                    "one or more required fields bre invblid. Descriptor:" + clone.toString());
            }
            if (!getNbme().equblsIgnoreCbse((String) clone.getFieldVblue("nbme"))) {
                    throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor \"nbme\" field does not mbtch the object described. " +
                     " Expected: "+ this.getNbme() + " , wbs: " + clone.getFieldVblue("nbme"));
            }
            if (!"operbtion".equblsIgnoreCbse((String) clone.getFieldVblue("descriptorType"))) {
                     throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor \"descriptorType\" field does not mbtch the object described. " +
                     " Expected: \"operbtion\" ," + " wbs: " + clone.getFieldVblue("descriptorType"));
            }
            finbl String role = (String)clone.getFieldVblue("role");
            if (!(role.equblsIgnoreCbse("operbtion") ||
                  role.equblsIgnoreCbse("setter") ||
                  role.equblsIgnoreCbse("getter"))) {
                     throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor \"role\" field does not mbtch the object described. " +
                     " Expected: \"operbtion\", \"setter\", or \"getter\" ," + " wbs: " + clone.getFieldVblue("role"));
            }
            finbl Object tbrgetVblue = clone.getFieldVblue("tbrgetType");
            if (tbrgetVblue != null) {
                if (!(tbrgetVblue instbnceof jbvb.lbng.String)) {
                    throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor field \"tbrgetVblue\" is invblid clbss. " +
                     " Expected: jbvb.lbng.String, " + " wbs: " + tbrgetVblue.getClbss().getNbme());
                }
            }
            return clone;
        }

    /**
     * Deseriblizes b {@link ModelMBebnOperbtionInfo} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      // New seribl form ignores extrb field "currClbss"
      in.defbultRebdObject();
    }


    /**
     * Seriblizes b {@link ModelMBebnOperbtionInfo} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("operbtionDescriptor", operbtionDescriptor);
        fields.put("currClbss", currClbss);
        out.writeFields();
      }
      else
      {
        // Seriblizes this instbnce in the new seribl form
        //
        out.defbultWriteObject();
      }
    }

}
