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
import jbvb.security.AccessController;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.DescriptorAccess;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

/**
 * <p>The ModelMBebnNotificbtionInfo object describes b notificbtion emitted
 * by b ModelMBebn.
 * It is b subclbss of MBebnNotificbtionInfo with the bddition of bn
 * bssocibted Descriptor bnd bn implementbtion of the Descriptor interfbce.</p>
 *
 * <P id="descriptor">
 * The fields in the descriptor bre defined, but not limited to, the following.
 * Note thbt when the Type in this tbble is Number, b String thbt is the decimbl
 * representbtion of b Long cbn blso be used.</P>
 *
 * <tbble border="1" cellpbdding="5" summbry="ModelMBebnNotificbtionInfo Fields">
 * <tr><th>Nbme</th><th>Type</th><th>Mebning</th></tr>
 * <tr><td>nbme</td><td>String</td>
 *     <td>Notificbtion nbme.</td></tr>
 * <tr><td>descriptorType</td><td>String</td>
 *     <td>Must be "notificbtion".</td></tr>
 * <tr><td>severity</td><td>Number</td>
 *     <td>0-6 where 0: unknown; 1: non-recoverbble;
 *         2: criticbl, fbilure; 3: mbjor, severe;
 *         4: minor, mbrginbl, error; 5: wbrning;
 *         6: normbl, clebred, informbtive</td></tr>
 * <tr><td>messbgeID</td><td>String</td>
 *     <td>Unique key for messbge text (to bllow trbnslbtion, bnblysis).</td></tr>
 * <tr><td>messbgeText</td><td>String</td>
 *     <td>Text of notificbtion.</td></tr>
 * <tr><td>log</td><td>String</td>
 *     <td>T - log messbge, F - do not log messbge.</td></tr>
 * <tr><td>logfile</td><td>String</td>
 *     <td>fully qublified file nbme bppropribte for operbting system.</td></tr>
 * <tr><td>visibility</td><td>Number</td>
 *     <td>1-4 where 1: blwbys visible 4: rbrely visible.</td></tr>
 * <tr><td>presentbtionString</td><td>String</td>
 *     <td>XML formbtted string to bllow presentbtion of dbtb.</td></tr>
 * </tbble>
 *
 * <p>The defbult descriptor contbins the nbme, descriptorType,
 * displbyNbme bnd severity(=6) fields.  The defbult vblue of the nbme
 * bnd displbyNbme fields is the nbme of the Notificbtion clbss (bs
 * specified by the <code>nbme</code> pbrbmeter of the
 * ModelMBebnNotificbtionInfo constructor).</p>
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-7445681389570207141L</code>.
 *
 * @since 1.5
 */

@SuppressWbrnings("seribl")  // seriblVersionUID is not constbnt
public clbss ModelMBebnNotificbtionInfo
    extends MBebnNotificbtionInfo
    implements DescriptorAccess {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form
    // depends on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -5211564525059047097L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -7445681389570207141L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("notificbtionDescriptor", Descriptor.clbss),
      new ObjectStrebmField("currClbss", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("notificbtionDescriptor", Descriptor.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField notificbtionDescriptor Descriptor The descriptor
     *   contbining the bppropribte metbdbtb for this instbnce
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
     * @seribl The descriptor contbining the bppropribte metbdbtb for
     *         this instbnce
     */
    privbte Descriptor notificbtionDescriptor;

    privbte stbtic finbl String currClbss = "ModelMBebnNotificbtionInfo";

    /**
     * Constructs b ModelMBebnNotificbtionInfo object with b defbult
     * descriptor.
     *
     * @pbrbm notifTypes The brrby of strings (in dot notbtion) contbining
     *     the notificbtion types thbt mby be emitted.
     * @pbrbm nbme The nbme of the Notificbtion clbss.
     * @pbrbm description A humbn rebdbble description of the
     *     Notificbtion. Optionbl.
     **/
    public ModelMBebnNotificbtionInfo(String[] notifTypes,
                                      String nbme,
                                      String description) {
        this(notifTypes,nbme,description,null);
    }

    /**
     * Constructs b ModelMBebnNotificbtionInfo object.
     *
     * @pbrbm notifTypes The brrby of strings (in dot notbtion)
     *        contbining the notificbtion types thbt mby be emitted.
     * @pbrbm nbme The nbme of the Notificbtion clbss.
     * @pbrbm description A humbn rebdbble description of the Notificbtion.
     *        Optionbl.
     * @pbrbm descriptor An instbnce of Descriptor contbining the
     *        bppropribte metbdbtb for this instbnce of the
     *        MBebnNotificbtionInfo. If it is null b defbult descriptor
     *        will be crebted. If the descriptor does not contbin the
     *        fields "displbyNbme" or "severity",
     *        the missing ones bre bdded with their defbult vblues.
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     *    {@link IllegblArgumentException}. The descriptor is invblid, or
     *    descriptor field "nbme" is not equbl to pbrbmeter nbme, or
     *    descriptor field "descriptorType" is not equbl to "notificbtion".
     *
     **/
    public ModelMBebnNotificbtionInfo(String[] notifTypes,
                                      String nbme,
                                      String description,
                                      Descriptor descriptor) {
        super(notifTypes, nbme, description);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnNotificbtionInfo.clbss.getNbme(),
                    "ModelMBebnNotificbtionInfo", "Entry");
        }
        notificbtionDescriptor = vblidDescriptor(descriptor);
    }

    /**
     * Constructs b new ModelMBebnNotificbtionInfo object from this
     * ModelMBebnNotficbtion Object.
     *
     * @pbrbm inInfo the ModelMBebnNotificbtionInfo to be duplicbted
     *
     **/
    public ModelMBebnNotificbtionInfo(ModelMBebnNotificbtionInfo inInfo) {
        this(inInfo.getNotifTypes(),
             inInfo.getNbme(),
             inInfo.getDescription(),inInfo.getDescriptor());
    }

    /**
     * Crebtes bnd returns b new ModelMBebnNotificbtionInfo which is b
     * duplicbte of this ModelMBebnNotificbtionInfo.
     **/
    public Object clone () {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnNotificbtionInfo.clbss.getNbme(),
                    "clone()", "Entry");
        }
        return(new ModelMBebnNotificbtionInfo(this));
    }

    /**
     * Returns b copy of the bssocibted Descriptor for the
     * ModelMBebnNotificbtionInfo.
     *
     * @return Descriptor bssocibted with the
     * ModelMBebnNotificbtionInfo object.
     *
     * @see #setDescriptor
     **/
    public Descriptor getDescriptor() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnNotificbtionInfo.clbss.getNbme(),
                    "getDescriptor()", "Entry");
        }

        if (notificbtionDescriptor == null) {
            // Debd code. Should never hbppen.
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
                MODELMBEAN_LOGGER.logp(Level.FINER,
                        ModelMBebnNotificbtionInfo.clbss.getNbme(),
                        "getDescriptor()", "Descriptor vblue is null, " +
                        "setting descriptor to defbult vblues");
            }
            notificbtionDescriptor = vblidDescriptor(null);
        }

        return((Descriptor)notificbtionDescriptor.clone());
    }

    /**
     * Sets bssocibted Descriptor (full replbce) for the
     * ModelMBebnNotificbtionInfo If the new Descriptor is null,
     * then the bssocibted Descriptor reverts to b defbult
     * descriptor.  The Descriptor is vblidbted before it is
     * bssigned.  If the new Descriptor is invblid, then b
     * RuntimeOperbtionsException wrbpping bn
     * IllegblArgumentException is thrown.
     *
     * @pbrbm inDescriptor replbces the Descriptor bssocibted with the
     * ModelMBebnNotificbtion interfbce
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     * {@link IllegblArgumentException} for invblid Descriptor.
     *
     * @see #getDescriptor
     **/
    public void setDescriptor(Descriptor inDescriptor) {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnNotificbtionInfo.clbss.getNbme(),
                    "setDescriptor(Descriptor)", "Entry");
        }
        notificbtionDescriptor = vblidDescriptor(inDescriptor);
    }

    /**
     * Returns b humbn rebdbble string contbining
     * ModelMBebnNotificbtionInfo.
     *
     * @return b string describing this object.
     **/
    public String toString() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINER)) {
            MODELMBEAN_LOGGER.logp(Level.FINER,
                    ModelMBebnNotificbtionInfo.clbss.getNbme(),
                    "toString()", "Entry");
        }

        finbl StringBuilder retStr = new StringBuilder();

        retStr.bppend("ModelMBebnNotificbtionInfo: ")
            .bppend(this.getNbme());

        retStr.bppend(" ; Description: ")
            .bppend(this.getDescription());

        retStr.bppend(" ; Descriptor: ")
            .bppend(this.getDescriptor());

        retStr.bppend(" ; Types: ");
        String[] nTypes = this.getNotifTypes();
        for (int i=0; i < nTypes.length; i++) {
            if (i > 0) retStr.bppend(", ");
            retStr.bppend(nTypes[i]);
        }
        return retStr.toString();
    }


    /**
     * Clones the pbssed in Descriptor, sets defbult vblues, bnd checks for vblidity.
     * If the Descriptor is invblid (for instbnce by hbving the wrong "nbme"),
     * this indicbtes progrbmming error bnd b RuntimeOperbtionsException will be thrown.
     *
     * The following fields will be defbulted if they bre not blrebdy set:
     * descriptorType="notificbtion",displbyNbme=this.getNbme(),
     * nbme=this.getNbme(),severity="6"
     *
     *
     * @pbrbm in Descriptor to be checked, or null which is equivblent to bn
     * empty Descriptor.
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
            clone.setField("descriptorType", "notificbtion");
            MODELMBEAN_LOGGER.finer("Defbulting descriptorType to \"notificbtion\"");
        }
        if (clone.getFieldVblue("displbyNbme") == null) {
            clone.setField("displbyNbme",this.getNbme());
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor displbyNbme to " + this.getNbme());
        }
        if (clone.getFieldVblue("severity") == null) {
            clone.setField("severity", "6");
            MODELMBEAN_LOGGER.finer("Defbulting Descriptor severity field to 6");
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
        if (!"notificbtion".equblsIgnoreCbse((String) clone.getFieldVblue("descriptorType"))) {
                 throw new RuntimeOperbtionsException(new IllegblArgumentException("Invblid Descriptor brgument"),
                "The Descriptor \"descriptorType\" field does not mbtch the object described. " +
                 " Expected: \"notificbtion\" ," + " wbs: " + clone.getFieldVblue("descriptorType"));
        }

        return clone;
    }


    /**
     * Deseriblizes b {@link ModelMBebnNotificbtionInfo} from bn
     * {@link ObjectInputStrebm}.
     **/
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException {
        // New seribl form ignores extrb field "currClbss"
        in.defbultRebdObject();
    }


    /**
     * Seriblizes b {@link ModelMBebnNotificbtionInfo} to bn
     * {@link ObjectOutputStrebm}.
     **/
    privbte void writeObject(ObjectOutputStrebm out)
        throws IOException {
        if (compbt) {
            // Seriblizes this instbnce in the old seribl form
            //
            ObjectOutputStrebm.PutField fields = out.putFields();
            fields.put("notificbtionDescriptor", notificbtionDescriptor);
            fields.put("currClbss", currClbss);
            out.writeFields();
        } else {
            // Seriblizes this instbnce in the new seribl form
            //
            out.defbultWriteObject();
        }
    }

}
