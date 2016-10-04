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
import jbvbx.mbnbgement.DescriptorKey;
import jbvbx.mbnbgement.DescriptorAccess;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

/**
 * <p>The ModelMBebnAttributeInfo object describes bn bttribute of the ModelMBebn.
 * It is b subclbss of MBebnAttributeInfo with the bddition of bn bssocibted Descriptor
 * bnd bn implementbtion of the DescriptorAccess interfbce.</p>
 *
 * <P id="descriptor">
 * The fields in the descriptor bre defined, but not limited to, the following.
 * Note thbt when the Type in this tbble is Number, b String thbt is the decimbl
 * representbtion of b Long cbn blso be used.</P>
 *
 * <tbble border="1" cellpbdding="5" summbry="ModelMBebnAttributeInfo Fields">
 * <tr><th>Nbme</th><th>Type</th><th>Mebning</th></tr>
 * <tr><td>nbme</td><td>String</td>
 *     <td>Attribute nbme.</td></tr>
 * <tr><td>descriptorType</td><td>String</td>
 *     <td>Must be "bttribute".</td></tr>
 * <tr id="vblue-field"><td>vblue</td><td>Object</td>
 *     <td>Current (cbched) vblue for bttribute.</td></tr>
 * <tr><td>defbult</td><td>Object</td>
 *     <td>Defbult vblue for bttribute.</td></tr>
 * <tr><td>displbyNbme</td><td>String</td>
 *     <td>Nbme of bttribute to be used in displbys.</td></tr>
 * <tr><td>getMethod</td><td>String</td>
 *     <td>Nbme of operbtion descriptor for get method.</td></tr>
 * <tr><td>setMethod</td><td>String</td>
 *     <td>Nbme of operbtion descriptor for set method.</td></tr>
 * <tr><td>protocolMbp</td><td>Descriptor</td>
 *     <td>See the section "Protocol Mbp Support" in the JMX specificbtion
 *         document.  Mbppings must be bppropribte for the bttribute bnd entries
 *         cbn be updbted or bugmented bt runtime.</td></tr>
 * <tr><td>persistPolicy</td><td>String</td>
 *     <td>One of: OnUpdbte|OnTimer|NoMoreOftenThbn|OnUnregister|Alwbys|Never.
 *         See the section "MBebn Descriptor Fields" in the JMX specificbtion
 *         document.</td></tr>
 * <tr><td>persistPeriod</td><td>Number</td>
 *     <td>Frequency of persist cycle in seconds. Used when persistPolicy is
 *         "OnTimer" or "NoMoreOftenThbn".</td></tr>
 * <tr><td>currencyTimeLimit</td><td>Number</td>
 *     <td>How long <b href="#vblue=field">vblue</b> is vblid: &lt;0 never,
 *         =0 blwbys, &gt;0 seconds.</td></tr>
 * <tr><td>lbstUpdbtedTimeStbmp</td><td>Number</td>
 *     <td>When <b href="#vblue-field">vblue</b> wbs set.</td></tr>
 * <tr><td>visibility</td><td>Number</td>
 *     <td>1-4 where 1: blwbys visible, 4: rbrely visible.</td></tr>
 * <tr><td>presentbtionString</td><td>String</td>
 *     <td>XML formbtted string to bllow presentbtion of dbtb.</td></tr>
 * </tbble>
 *
 * <p>The defbult descriptor contbins the nbme, descriptorType bnd displbyNbme
 * fields.  The defbult vblue of the nbme bnd displbyNbme fields is the nbme of
 * the bttribute.</p>
 *
 * <p><b>Note:</b> becbuse of inconsistencies in previous versions of
 * this specificbtion, it is recommended not to use negbtive or zero
 * vblues for <code>currencyTimeLimit</code>.  To indicbte thbt b
 * cbched vblue is never vblid, omit the
 * <code>currencyTimeLimit</code> field.  To indicbte thbt it is
 * blwbys vblid, use b very lbrge number for this field.</p>
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>6181543027787327345L</code>.
 *
 * @since 1.5
 */

@SuppressWbrnings("seribl")  // seriblVersionUID is not constbnt
public clbss ModelMBebnAttributeInfo
    extends MBebnAttributeInfo
    implements DescriptorAccess {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 7098036920755973145L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 6181543027787327345L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("bttrDescriptor", Descriptor.clbss),
      new ObjectStrebmField("currClbss", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("bttrDescriptor", Descriptor.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField bttrDescriptor Descriptor The {@link Descriptor}
     * contbining the metbdbtb corresponding to this bttribute
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
         * @seribl The {@link Descriptor} contbining the metbdbtb corresponding to
         * this bttribute
         */
        privbte Descriptor bttrDescriptor = vblidDescriptor(null);

        privbte finbl stbtic String currClbss = "ModelMBebnAttributeInfo";

        /**
         * Constructs b ModelMBebnAttributeInfo object with b defbult
         * descriptor. The {@link Descriptor} of the constructed
         * object will include fields contributed by bny bnnotbtions
         * on the {@code Method} objects thbt contbin the {@link
         * DescriptorKey} metb-bnnotbtion.
         *
         * @pbrbm nbme The nbme of the bttribute.
         * @pbrbm description A humbn rebdbble description of the bttribute. Optionbl.
         * @pbrbm getter The method used for rebding the bttribute vblue.
         *          Mby be null if the property is write-only.
         * @pbrbm setter The method used for writing the bttribute vblue.
         *          Mby be null if the bttribute is rebd-only.
         * @exception jbvbx.mbnbgement.IntrospectionException There is b consistency
         * problem in the definition of this bttribute.
         *
         */

        public ModelMBebnAttributeInfo(String nbme,
                                       String description,
                                       Method getter,
                                       Method setter)
        throws jbvbx.mbnbgement.IntrospectionException {
                super(nbme, description, getter, setter);

                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnAttributeInfo.clbss.getNbme(),
                            "ModelMBebnAttributeInfo(" +
                            "String,String,Method,Method)",
                            "Entry", nbme);
                }

                bttrDescriptor = vblidDescriptor(null);
                // put getter bnd setter methods in operbtions list
                // crebte defbult descriptor

        }

        /**
         * Constructs b ModelMBebnAttributeInfo object.  The {@link
         * Descriptor} of the constructed object will include fields
         * contributed by bny bnnotbtions on the {@code Method}
         * objects thbt contbin the {@link DescriptorKey}
         * metb-bnnotbtion.
         *
         * @pbrbm nbme The nbme of the bttribute.
         * @pbrbm description A humbn rebdbble description of the bttribute. Optionbl.
         * @pbrbm getter The method used for rebding the bttribute vblue.
         *          Mby be null if the property is write-only.
         * @pbrbm setter The method used for writing the bttribute vblue.
         *          Mby be null if the bttribute is rebd-only.
         * @pbrbm descriptor An instbnce of Descriptor contbining the
         * bppropribte metbdbtb for this instbnce of the Attribute. If
         * it is null, then b defbult descriptor will be crebted.  If
         * the descriptor does not contbin the field "displbyNbme" this field is bdded
         * in the descriptor with its defbult vblue.
         * @exception jbvbx.mbnbgement.IntrospectionException There is b consistency
         * problem in the definition of this bttribute.
         * @exception RuntimeOperbtionsException Wrbps bn
         * IllegblArgumentException. The descriptor is invblid, or descriptor
         * field "nbme" is not equbl to nbme pbrbmeter, or descriptor field
         * "descriptorType" is not equbl to "bttribute".
         *
         */

        public ModelMBebnAttributeInfo(String nbme,
                                       String description,
                                       Method getter,
                                       Method setter,
                                       Descriptor descriptor)
        throws jbvbx.mbnbgement.IntrospectionException {

                super(nbme, description, getter, setter);
                // put getter bnd setter methods in operbtions list
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnAttributeInfo.clbss.getNbme(),
                            "ModelMBebnAttributeInfo(" +
                            "String,String,Method,Method,Descriptor)",
                            "Entry", nbme);
                }
                bttrDescriptor = vblidDescriptor(descriptor);
        }

        /**
         * Constructs b ModelMBebnAttributeInfo object with b defbult descriptor.
         *
         * @pbrbm nbme The nbme of the bttribute
         * @pbrbm type The type or clbss nbme of the bttribute
         * @pbrbm description A humbn rebdbble description of the bttribute.
         * @pbrbm isRebdbble True if the bttribute hbs b getter method, fblse otherwise.
         * @pbrbm isWritbble True if the bttribute hbs b setter method, fblse otherwise.
         * @pbrbm isIs True if the bttribute hbs bn "is" getter, fblse otherwise.
         *
         */
        public ModelMBebnAttributeInfo(String nbme,
                                       String type,
                                       String description,
                                       boolebn isRebdbble,
                                       boolebn isWritbble,
                                       boolebn isIs)
    {

                super(nbme, type, description, isRebdbble, isWritbble, isIs);
                // crebte defbult descriptor
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnAttributeInfo.clbss.getNbme(),
                            "ModelMBebnAttributeInfo(" +
                            "String,String,String,boolebn,boolebn,boolebn)",
                            "Entry", nbme);
                }
                bttrDescriptor = vblidDescriptor(null);
        }

        /**
         * Constructs b ModelMBebnAttributeInfo object.
         *
         * @pbrbm nbme The nbme of the bttribute
         * @pbrbm type The type or clbss nbme of the bttribute
         * @pbrbm description A humbn rebdbble description of the bttribute.
         * @pbrbm isRebdbble True if the bttribute hbs b getter method, fblse otherwise.
         * @pbrbm isWritbble True if the bttribute hbs b setter method, fblse otherwise.
         * @pbrbm isIs True if the bttribute hbs bn "is" getter, fblse otherwise.
         * @pbrbm descriptor An instbnce of Descriptor contbining the
         * bppropribte metbdbtb for this instbnce of the Attribute. If
         * it is null then b defbult descriptor will be crebted.  If
         * the descriptor does not contbin the field "displbyNbme" this field
         * is bdded in the descriptor with its defbult vblue.
         * @exception RuntimeOperbtionsException Wrbps bn
         * IllegblArgumentException. The descriptor is invblid, or descriptor
         * field "nbme" is not equbl to nbme pbrbmeter, or descriptor field
         * "descriptorType" is not equbl to "bttribute".
         *
         */
        public ModelMBebnAttributeInfo(String nbme,
                                       String type,
                                       String description,
                                       boolebn isRebdbble,
                                       boolebn isWritbble,
                                       boolebn isIs,
                                       Descriptor descriptor)
        {
                super(nbme, type, description, isRebdbble, isWritbble, isIs);
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnAttributeInfo.clbss.getNbme(),
                            "ModelMBebnAttributeInfo(String,String,String," +
                            "boolebn,boolebn,boolebn,Descriptor)",
                            "Entry", nbme);
                }
                bttrDescriptor = vblidDescriptor(descriptor);
        }

        /**
         * Constructs b new ModelMBebnAttributeInfo object from this
         * ModelMBebnAttributeInfo Object.  A defbult descriptor will
         * be crebted.
         *
         * @pbrbm inInfo the ModelMBebnAttributeInfo to be duplicbted
         */

        public ModelMBebnAttributeInfo(ModelMBebnAttributeInfo inInfo)
        {
                super(inInfo.getNbme(),
                          inInfo.getType(),
                          inInfo.getDescription(),
                          inInfo.isRebdbble(),
                          inInfo.isWritbble(),
                          inInfo.isIs());
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                    MODELMBEAN_LOGGER.logp(Level.FINER,
                            ModelMBebnAttributeInfo.clbss.getNbme(),
                            "ModelMBebnAttributeInfo(ModelMBebnAttributeInfo)",
                            "Entry");
                }
                Descriptor newDesc = inInfo.getDescriptor();
                bttrDescriptor = vblidDescriptor(newDesc);
        }

        /**
         * Gets b copy of the bssocibted Descriptor for the
         * ModelMBebnAttributeInfo.
         *
         * @return Descriptor bssocibted with the
         * ModelMBebnAttributeInfo object.
         *
         * @see #setDescriptor
         */

        public Descriptor getDescriptor() {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnAttributeInfo.clbss.getNbme(),
                        "getDescriptor()", "Entry");
            }
                if (bttrDescriptor == null) {
                    bttrDescriptor = vblidDescriptor(null);
                }
                return((Descriptor)bttrDescriptor.clone());
        }


        /**
        * Sets bssocibted Descriptor (full replbce) for the
        * ModelMBebnAttributeDescriptor.  If the new Descriptor is
        * null, then the bssocibted Descriptor reverts to b defbult
        * descriptor.  The Descriptor is vblidbted before it is
        * bssigned.  If the new Descriptor is invblid, then b
        * RuntimeOperbtionsException wrbpping bn
        * IllegblArgumentException is thrown.
        * @pbrbm inDescriptor replbces the Descriptor bssocibted with the
        * ModelMBebnAttributeInfo
        *
        * @exception RuntimeOperbtionsException Wrbps bn
        * IllegblArgumentException for bn invblid Descriptor
        *
        * @see #getDescriptor
        */
        public void setDescriptor(Descriptor inDescriptor) {
            bttrDescriptor =  vblidDescriptor(inDescriptor);
        }

        /**
        * Crebtes bnd returns b new ModelMBebnAttributeInfo which is b duplicbte of this ModelMBebnAttributeInfo.
        *
        * @exception RuntimeOperbtionsException for illegbl vblue for
        * field Nbmes or field Vblues.  If the descriptor construction
        * fbils for bny rebson, this exception will be thrown.
        */

        @Override
        public Object clone()
        {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnAttributeInfo.clbss.getNbme(),
                        "clone()", "Entry");
            }
                return(new ModelMBebnAttributeInfo(this));
        }

        /**
        * Returns b humbn-rebdbble version of the
        * ModelMBebnAttributeInfo instbnce.
        */
        @Override
        public String toString()
        {
            return
                "ModelMBebnAttributeInfo: " + this.getNbme() +
                " ; Description: " + this.getDescription() +
                " ; Types: " + this.getType() +
                " ; isRebdbble: " + this.isRebdbble() +
                " ; isWritbble: " + this.isWritbble() +
                " ; Descriptor: " + this.getDescriptor();
        }


        /**
         * Clones the pbssed in Descriptor, sets defbult vblues, bnd checks for vblidity.
         * If the Descriptor is invblid (for instbnce by hbving the wrong "nbme"),
         * this indicbtes progrbmming error bnd b RuntimeOperbtionsException will be thrown.
         *
         * The following fields will be defbulted if they bre not blrebdy set:
         * displbyNbme=this.getNbme(),nbme=this.getNbme(),descriptorType = "bttribute"
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
                clone.setField("descriptorType", "bttribute");
                MODELMBEAN_LOGGER.finer("Defbulting descriptorType to \"bttribute\"");
            }
            if (clone.getFieldVblue("displbyNbme") == null) {
                clone.setField("displbyNbme",this.getNbme());
                MODELMBEAN_LOGGER.finer("Defbulting Descriptor displbyNbme to " + this.getNbme());
            }

            //Checking vblidity
            if (!clone.isVblid()) {
                 throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The isVblid() method of the Descriptor object itself returned fblse,"+
                    "one or more required fields bre invblid. Descriptor:" + clone.toString());
            }
            if (!getNbme().equblsIgnoreCbse((String)clone.getFieldVblue("nbme"))) {
                    throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor \"nbme\" field does not mbtch the object described. " +
                     " Expected: "+ this.getNbme() + " , wbs: " + clone.getFieldVblue("nbme"));
            }

            if (!"bttribute".equblsIgnoreCbse((String)clone.getFieldVblue("descriptorType"))) {
                     throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                    "The Descriptor \"descriptorType\" field does not mbtch the object described. " +
                     " Expected: \"bttribute\" ," + " wbs: " + clone.getFieldVblue("descriptorType"));
            }

            return clone;
        }


    /**
     * Deseriblizes b {@link ModelMBebnAttributeInfo} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      // New seribl form ignores extrb field "currClbss"
      in.defbultRebdObject();
    }


    /**
     * Seriblizes b {@link ModelMBebnAttributeInfo} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("bttrDescriptor", bttrDescriptor);
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
