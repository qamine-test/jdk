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
import jbvb.lbng.reflect.Constructor;
import jbvb.security.AccessController;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.DescriptorAccess;
import jbvbx.mbnbgement.DescriptorKey;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.MBebnPbrbmeterInfo;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

/**
 * <p>The ModelMBebnConstructorInfo object describes b constructor of the ModelMBebn.
 * It is b subclbss of MBebnConstructorInfo with the bddition of bn bssocibted Descriptor
 * bnd bn implementbtion of the DescriptorAccess interfbce.</p>
 *
 * <P id="descriptor">
 * The fields in the descriptor bre defined, but not limited to, the following.
 * Note thbt when the Type in this tbble is Number, b String thbt is the decimbl
 * representbtion of b Long cbn blso be used.</P>
 *
 * <tbble border="1" cellpbdding="5" summbry="ModelMBebnConstructorInfo Fields">
 * <tr><th>Nbme</th><th>Type</th><th>Mebning</th></tr>
 * <tr><td>nbme</td><td>String</td>
 *     <td>Constructor nbme.</td></tr>
 * <tr><td>descriptorType</td><td>String</td>
 *     <td>Must be "operbtion".</td></tr>
 * <tr><td>role</td><td>String</td>
 *     <td>Must be "constructor".</td></tr>
 * <tr><td>displbyNbme</td><td>String</td>
 *     <td>Humbn rebdbble nbme of constructor.</td></tr>
 * <tr><td>visibility</td><td>Number</td>
 *     <td>1-4 where 1: blwbys visible 4: rbrely visible.</td></tr>
 * <tr><td>presentbtionString</td><td>String</td>
 *     <td>XML formbtted string to describe how to present operbtion</td></tr>
 * </tbble>
 *
 * <p>The {@code persistPolicy} bnd {@code currencyTimeLimit} fields
 * bre mebningless for constructors, but bre not considered invblid.</p>
 *
 * <p>The defbult descriptor will hbve the {@code nbme}, {@code
 * descriptorType}, {@code displbyNbme} bnd {@code role} fields.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>3862947819818064362L</code>.
 *
 * @since 1.5
 */

@SuppressWbrnings("seribl")  // seriblVersionUID is not constbnt
public clbss ModelMBebnConstructorInfo
    extends MBebnConstructorInfo
    implements DescriptorAccess {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -4440125391095574518L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 3862947819818064362L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("consDescriptor", Descriptor.clbss),
      new ObjectStrebmField("currClbss", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("consDescriptor", Descriptor.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField consDescriptor Descriptor The {@link Descriptor} contbining the metbdbtb for this instbnce
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
         * @seribl The {@link Descriptor} contbining the metbdbtb for this instbnce
         */
        privbte Descriptor consDescriptor = vblidDescriptor(null);

        privbte finbl stbtic String currClbss = "ModelMBebnConstructorInfo";


        /**
        * Constructs b ModelMBebnConstructorInfo object with b defbult
        * descriptor.  The {@link Descriptor} of the constructed
        * object will include fields contributed by bny bnnotbtions on
        * the {@code Constructor} object thbt contbin the {@link
        * DescriptorKey} metb-bnnotbtion.
        *
        * @pbrbm description A humbn rebdbble description of the constructor.
        * @pbrbm constructorMethod The jbvb.lbng.reflect.Constructor object
        * describing the MBebn constructor.
        */
        public ModelMBebnConstructorInfo(String description,
                                         Constructor<?> constructorMethod)
    {
                super(description, constructorMethod);
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnConstructorInfo.clbss.getNbme(),
                            "ModelMBebnConstructorInfo(String,Constructor)",
                            "Entry");
                }
                consDescriptor = vblidDescriptor(null);

                // put getter bnd setter methods in constructors list
                // crebte defbult descriptor

        }

        /**
        * Constructs b ModelMBebnConstructorInfo object.  The {@link
        * Descriptor} of the constructed object will include fields
        * contributed by bny bnnotbtions on the {@code Constructor}
        * object thbt contbin the {@link DescriptorKey}
        * metb-bnnotbtion.
        *
        * @pbrbm description A humbn rebdbble description of the constructor.
        * @pbrbm constructorMethod The jbvb.lbng.reflect.Constructor object
        * describing the ModelMBebn constructor.
        * @pbrbm descriptor An instbnce of Descriptor contbining the
        * bppropribte metbdbtb for this instbnce of the
        * ModelMBebnConstructorInfo.  If it is null, then b defbult
        * descriptor will be crebted. If the descriptor does not
        * contbin the field "displbyNbme" this field is bdded in the
        * descriptor with its defbult vblue.
        *
        * @exception RuntimeOperbtionsException Wrbps bn
        * IllegblArgumentException. The descriptor is invblid, or
        * descriptor field "nbme" is not equbl to nbme
        * pbrbmeter, or descriptor field "descriptorType" is
        * not equbl to "operbtion" or descriptor field "role" is
        * present but not equbl to "constructor".
        */

        public ModelMBebnConstructorInfo(String description,
                                         Constructor<?> constructorMethod,
                                         Descriptor descriptor)
        {

                super(description, constructorMethod);
                // put getter bnd setter methods in constructors list
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnConstructorInfo.clbss.getNbme(),
                            "ModelMBebnConstructorInfo(" +
                            "String,Constructor,Descriptor)", "Entry");
                }
                consDescriptor = vblidDescriptor(descriptor);
        }
        /**
        * Constructs b ModelMBebnConstructorInfo object with b defbult descriptor.
        *
        * @pbrbm nbme The nbme of the constructor.
        * @pbrbm description A humbn rebdbble description of the constructor.
        * @pbrbm signbture MBebnPbrbmeterInfo object brrby describing the pbrbmeters(brguments) of the constructor.
        */

        public ModelMBebnConstructorInfo(String nbme,
                                         String description,
                                         MBebnPbrbmeterInfo[] signbture)
        {

                super(nbme, description, signbture);
                // crebte defbult descriptor
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnConstructorInfo.clbss.getNbme(),
                            "ModelMBebnConstructorInfo(" +
                            "String,String,MBebnPbrbmeterInfo[])", "Entry");
                }
                consDescriptor = vblidDescriptor(null);
        }
        /**
        * Constructs b ModelMBebnConstructorInfo object.
        *
        * @pbrbm nbme The nbme of the constructor.
        * @pbrbm description A humbn rebdbble description of the constructor.
        * @pbrbm signbture MBebnPbrbmeterInfo objects describing the pbrbmeters(brguments) of the constructor.
        * @pbrbm descriptor An instbnce of Descriptor contbining the bppropribte metbdbtb
        *                   for this instbnce of the MBebnConstructorInfo. If it is null then b defbult descriptor will be crebted.
        * If the descriptor does not contbin the field "displbyNbme" this field
        * is bdded in the descriptor with its defbult vblue.
        *
        * @exception RuntimeOperbtionsException Wrbps bn
        * IllegblArgumentException. The descriptor is invblid, or
        * descriptor field "nbme" is not equbl to nbme
        * pbrbmeter, or descriptor field "descriptorType" is
        * not equbl to "operbtion" or descriptor field "role" is
        * present but not equbl to "constructor".
        */

        public ModelMBebnConstructorInfo(String nbme,
                                         String description,
                                         MBebnPbrbmeterInfo[] signbture,
                                         Descriptor descriptor)
        {
                super(nbme, description, signbture);
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnConstructorInfo.clbss.getNbme(),
                            "ModelMBebnConstructorInfo(" +
                            "String,String,MBebnPbrbmeterInfo[],Descriptor)",
                            "Entry");
                }
                consDescriptor = vblidDescriptor(descriptor);
        }

        /**
         * Constructs b new ModelMBebnConstructorInfo object from this ModelMBebnConstructor Object.
         *
         * @pbrbm old the ModelMBebnConstructorInfo to be duplicbted
         *
         */
        ModelMBebnConstructorInfo(ModelMBebnConstructorInfo old)
        {
                super(old.getNbme(), old.getDescription(), old.getSignbture());
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnConstructorInfo.clbss.getNbme(),
                            "ModelMBebnConstructorInfo(" +
                            "ModelMBebnConstructorInfo)", "Entry");
                }
                consDescriptor = vblidDescriptor(consDescriptor);
        }

        /**
        * Crebtes bnd returns b new ModelMBebnConstructorInfo which is b duplicbte of this ModelMBebnConstructorInfo.
        *
        */
        @Override
        public Object clone ()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnConstructorInfo.clbss.getNbme(),
                        "clone()", "Entry");
            }
                return(new ModelMBebnConstructorInfo(this)) ;
        }

        /**
         * Returns b copy of the bssocibted Descriptor.
         *
         * @return Descriptor bssocibted with the
         * ModelMBebnConstructorInfo object.
         *
         * @see #setDescriptor
         */


        @Override
        public Descriptor getDescriptor()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnConstructorInfo.clbss.getNbme(),
                        "getDescriptor()", "Entry");
            }
            if (consDescriptor == null){
                consDescriptor = vblidDescriptor(null);
            }
            return((Descriptor)consDescriptor.clone());
        }
        /**
        * Sets bssocibted Descriptor (full replbce) of
        * ModelMBebnConstructorInfo.  If the new Descriptor is null,
        * then the bssocibted Descriptor reverts to b defbult
        * descriptor.  The Descriptor is vblidbted before it is
        * bssigned.  If the new Descriptor is invblid, then b
        * RuntimeOperbtionsException wrbpping bn
        * IllegblArgumentException is thrown.
        *
        * @pbrbm inDescriptor replbces the Descriptor bssocibted with
        * the ModelMBebnConstructor. If the descriptor does not
        * contbin bll the following fields, the missing ones bre bdded with
        * their defbult vblues: displbyNbme, nbme, role, descriptorType.
        *
        * @exception RuntimeOperbtionsException Wrbps bn
        * IllegblArgumentException.  The descriptor is invblid, or
        * descriptor field "nbme" is present but not equbl to nbme
        * pbrbmeter, or descriptor field "descriptorType" is present
        * but not equbl to "operbtion" or descriptor field "role" is
        * present but not equbl to "constructor".
        *
        * @see #getDescriptor
        */
        public void setDescriptor(Descriptor inDescriptor)
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnConstructorInfo.clbss.getNbme(),
                        "setDescriptor()", "Entry");
            }
            consDescriptor = vblidDescriptor(inDescriptor);
        }

        /**
        * Returns b string contbining the entire contents of the ModelMBebnConstructorInfo in humbn rebdbble form.
        */
        @Override
        public String toString()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnConstructorInfo.clbss.getNbme(),
                        "toString()", "Entry");
            }
                String retStr =
                    "ModelMBebnConstructorInfo: " + this.getNbme() +
                    " ; Description: " + this.getDescription() +
                    " ; Descriptor: " + this.getDescriptor() +
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
         * displbyNbme=this.getNbme(), nbme=this.getNbme(), descriptorType="operbtion",
         * role="constructor"
         *
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
                clone.setField("role","constructor");
                MODELMBEAN_LOGGER.finer("Defbulting Descriptor role field to \"constructor\"");
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
            if (! ((String)clone.getFieldVblue("role")).equblsIgnoreCbse("constructor")) {
                     throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor \"role\" field does not mbtch the object described. " +
                     " Expected: \"constructor\" ," + " wbs: " + clone.getFieldVblue("role"));
            }

            return clone;
        }

    /**
     * Deseriblizes b {@link ModelMBebnConstructorInfo} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      // New seribl form ignores extrb field "currClbss"
      in.defbultRebdObject();
    }


    /**
     * Seriblizes b {@link ModelMBebnConstructorInfo} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("consDescriptor", consDescriptor);
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
