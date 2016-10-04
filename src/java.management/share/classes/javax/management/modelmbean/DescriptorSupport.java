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
import stbtic com.sun.jmx.mbebnserver.Util.cbst;
import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.mbebnserver.Util;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;

import jbvb.lbng.reflect.Constructor;

import jbvb.security.AccessController;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.StringTokenizer;
import jbvb.util.TreeMbp;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

import sun.reflect.misc.ReflectUtil;

/**
 * This clbss represents the metbdbtb set for b ModelMBebn element.  A
 * descriptor is pbrt of the ModelMBebnInfo,
 * ModelMBebnNotificbtionInfo, ModelMBebnAttributeInfo,
 * ModelMBebnConstructorInfo, bnd ModelMBebnPbrbmeterInfo.
 * <P>
 * A descriptor consists of b collection of fields.  Ebch field is in
 * fieldnbme=fieldvblue formbt.  Field nbmes bre not cbse sensitive,
 * cbse will be preserved on field vblues.
 * <P>
 * All field nbmes bnd vblues bre not predefined. New fields cbn be
 * defined bnd bdded by bny progrbm.  Some fields hbve been predefined
 * for consistency of implementbtion bnd support by the
 * ModelMBebnInfo, ModelMBebnAttributeInfo, ModelMBebnConstructorInfo,
 * ModelMBebnNotificbtionInfo, ModelMBebnOperbtionInfo bnd ModelMBebn
 * clbsses.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-6292969195866300415L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss DescriptorSupport
         implements jbvbx.mbnbgement.Descriptor
{

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 8071560848919417985L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -6292969195866300415L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("descriptor", HbshMbp.clbss),
      new ObjectStrebmField("currClbss", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("descriptor", HbshMbp.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField descriptor HbshMbp The collection of fields representing this descriptor
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte stbtic finbl String seriblForm;
    stbtic {
        String form = null;
        boolebn compbt = fblse;
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            form = AccessController.doPrivileged(bct);
            compbt = "1.0".equbls(form);  // form mby be null
        } cbtch (Exception e) {
            // OK: No compbt with 1.0
        }
        seriblForm = form;
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

    /* Spec sbys thbt field nbmes bre cbse-insensitive, but thbt cbse
       is preserved.  This mebns thbt we need to be bble to mbp from b
       nbme thbt mby differ in cbse to the bctubl nbme thbt is used in
       the HbshMbp.  Thus, descriptorMbp is b TreeMbp with b Compbrbtor
       thbt ignores cbse.

       Previous versions of this clbss hbd b field cblled "descriptor"
       of type HbshMbp where the keys were directly Strings.  This is
       hbrd to reconcile with the required sembntics, so we fbbricbte
       thbt field virtublly during seriblizbtion bnd deseriblizbtion
       but keep the rebl informbtion in descriptorMbp.
    */
    privbte trbnsient SortedMbp<String, Object> descriptorMbp;

    privbte stbtic finbl String currClbss = "DescriptorSupport";


    /**
     * Descriptor defbult constructor.
     * Defbult initibl descriptor size is 20.  It will grow bs needed.<br>
     * Note thbt the crebted empty descriptor is not b vblid descriptor
     * (the method {@link #isVblid isVblid} returns <CODE>fblse</CODE>)
     */
    public DescriptorSupport() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "DescriptorSupport()" , "Constructor");
        }
        init(null);
    }

    /**
     * Descriptor constructor.  Tbkes bs pbrbmeter the initibl
     * cbpbcity of the Mbp thbt stores the descriptor fields.
     * Cbpbcity will grow bs needed.<br> Note thbt the crebted empty
     * descriptor is not b vblid descriptor (the method {@link
     * #isVblid isVblid} returns <CODE>fblse</CODE>).
     *
     * @pbrbm initNumFields The initibl cbpbcity of the Mbp thbt
     * stores the descriptor fields.
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for
     * initNumFields (&lt;= 0)
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     */
    public DescriptorSupport(int initNumFields)
            throws MBebnException, RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(initNumFields = " + initNumFields + ")",
                    "Constructor");
        }
        if (initNumFields <= 0) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "Descriptor(initNumFields)",
                        "Illegbl brguments: initNumFields <= 0");
            }
            finbl String msg =
                "Descriptor field limit invblid: " + initNumFields;
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }
        init(null);
    }

    /**
     * Descriptor constructor tbking b Descriptor bs pbrbmeter.
     * Crebtes b new descriptor initiblized to the vblues of the
     * descriptor pbssed in pbrbmeter.
     *
     * @pbrbm inDescr the descriptor to be used to initiblize the
     * constructed descriptor. If it is null or contbins no descriptor
     * fields, bn empty Descriptor will be crebted.
     */
    public DescriptorSupport(DescriptorSupport inDescr) {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(Descriptor)", "Constructor");
        }
        if (inDescr == null)
            init(null);
        else
            init(inDescr.descriptorMbp);
    }


    /**
     * <p>Descriptor constructor tbking bn XML String.</p>
     *
     * <p>The formbt of the XML string is not defined, but bn
     * implementbtion must ensure thbt the string returned by
     * {@link #toXMLString() toXMLString()} on bn existing
     * descriptor cbn be used to instbntibte bn equivblent
     * descriptor using this constructor.</p>
     *
     * <p>In this implementbtion, bll field vblues will be crebted
     * bs Strings.  If the field vblues bre not Strings, the
     * progrbmmer will hbve to reset or convert these fields
     * correctly.</p>
     *
     * @pbrbm inStr An XML-formbtted string used to populbte this
     * Descriptor.  The formbt is not defined, but bny
     * implementbtion must ensure thbt the string returned by
     * method {@link #toXMLString toXMLString} on bn existing
     * descriptor cbn be used to instbntibte bn equivblent
     * descriptor when instbntibted using this constructor.
     *
     * @exception RuntimeOperbtionsException If the String inStr
     * pbssed in pbrbmeter is null
     * @exception XMLPbrseException XML pbrsing problem while pbrsing
     * the input String
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     */
    /* At some stbge we should rewrite this code to be cleverer.  Using
       b StringTokenizer bs we do mebns, first, thbt we bccept b lot of
       bogus strings without noticing they bre bogus, bnd second, thbt we
       split the string being pbrsed bt chbrbcters like > even if they
       occur in the middle of b field vblue. */
    public DescriptorSupport(String inStr)
            throws MBebnException, RuntimeOperbtionsException,
                   XMLPbrseException {
        /* pbrse bn XML-formbtted string bnd populbte internbl
         * structure with it */
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(String = '" + inStr + "')", "Constructor");
        }
        if (inStr == null) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "Descriptor(String = null)", "Illegbl brguments");
            }
            finbl String msg = "String in pbrbmeter is null";
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }

        finbl String lowerInStr = inStr.toLowerCbse();
        if (!lowerInStr.stbrtsWith("<descriptor>")
            || !lowerInStr.endsWith("</descriptor>")) {
            throw new XMLPbrseException("No <descriptor>, </descriptor> pbir");
        }

        // pbrse xmlstring into structures
        init(null);
        // crebte dummy descriptor: should hbve sbme size
        // bs number of fields in xmlstring
        // loop through structures bnd put them in descriptor

        StringTokenizer st = new StringTokenizer(inStr, "<> \t\n\r\f");

        boolebn inFld = fblse;
        boolebn inDesc = fblse;
        String fieldNbme = null;
        String fieldVblue = null;


        while (st.hbsMoreTokens()) {  // loop through tokens
            String tok = st.nextToken();

            if (tok.equblsIgnoreCbse("FIELD")) {
                inFld = true;
            } else if (tok.equblsIgnoreCbse("/FIELD")) {
                if ((fieldNbme != null) && (fieldVblue != null)) {
                    fieldNbme =
                        fieldNbme.substring(fieldNbme.indexOf('"') + 1,
                                            fieldNbme.lbstIndexOf('"'));
                    finbl Object fieldVblueObject =
                        pbrseQuotedFieldVblue(fieldVblue);
                    setField(fieldNbme, fieldVblueObject);
                }
                fieldNbme = null;
                fieldVblue = null;
                inFld = fblse;
            } else if (tok.equblsIgnoreCbse("DESCRIPTOR")) {
                inDesc = true;
            } else if (tok.equblsIgnoreCbse("/DESCRIPTOR")) {
                inDesc = fblse;
                fieldNbme = null;
                fieldVblue = null;
                inFld = fblse;
            } else if (inFld && inDesc) {
                // wbnt kw=vblue, eg, nbme="mynbme" vblue="myvblue"
                int eq_sepbrbtor = tok.indexOf('=');
                if (eq_sepbrbtor > 0) {
                    String kwPbrt = tok.substring(0,eq_sepbrbtor);
                    String vblPbrt = tok.substring(eq_sepbrbtor+1);
                    if (kwPbrt.equblsIgnoreCbse("NAME"))
                        fieldNbme = vblPbrt;
                    else if (kwPbrt.equblsIgnoreCbse("VALUE"))
                        fieldVblue = vblPbrt;
                    else {  // xml pbrse exception
                        finbl String msg =
                            "Expected `nbme' or `vblue', got `" + tok + "'";
                        throw new XMLPbrseException(msg);
                    }
                } else { // xml pbrse exception
                    finbl String msg =
                        "Expected `keyword=vblue', got `" + tok + "'";
                    throw new XMLPbrseException(msg);
                }
            }
        }  // while tokens
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(XMLString)", "Exit");
        }
    }

    /**
     * Constructor tbking field nbmes bnd field vblues.  Neither brrby
     * cbn be null.
     *
     * @pbrbm fieldNbmes String brrby of field nbmes.  No elements of
     * this brrby cbn be null.
     * @pbrbm fieldVblues Object brrby of the corresponding field
     * vblues.  Elements of the brrby cbn be null. The
     * <code>fieldVblue</code> must be vblid for the
     * <code>fieldNbme</code> (bs defined in method {@link #isVblid
     * isVblid})
     *
     * <p>Note: brrby sizes of pbrbmeters should mbtch. If both brrbys
     * bre empty, then bn empty descriptor is crebted.</p>
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for
     * field Nbmes or field Vblues.  The brrby lengths must be equbl.
     * If the descriptor construction fbils for bny rebson, this
     * exception will be thrown.
     *
     */
    public DescriptorSupport(String[] fieldNbmes, Object[] fieldVblues)
            throws RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(fieldNbmes,fieldObjects)", "Constructor");
        }

        if ((fieldNbmes == null) || (fieldVblues == null) ||
            (fieldNbmes.length != fieldVblues.length)) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "Descriptor(fieldNbmes,fieldObjects)",
                        "Illegbl brguments");
            }

            finbl String msg =
                "Null or invblid fieldNbmes or fieldVblues";
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }

        /* populbte internbl structure with fields */
        init(null);
        for (int i=0; i < fieldNbmes.length; i++) {
            // setField will throw bn exception if b fieldNbme is be null.
            // the fieldNbme bnd fieldVblue will be vblidbted in setField.
            setField(fieldNbmes[i], fieldVblues[i]);
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(fieldNbmes,fieldObjects)", "Exit");
        }
    }

    /**
     * Constructor tbking fields in the <i>fieldNbme=fieldVblue</i>
     * formbt.
     *
     * @pbrbm fields String brrby with ebch element contbining b
     * field nbme bnd vblue.  If this brrby is null or empty, then the
     * defbult constructor will be executed. Null strings or empty
     * strings will be ignored.
     *
     * <p>All field vblues should be Strings.  If the field vblues bre
     * not Strings, the progrbmmer will hbve to reset or convert these
     * fields correctly.
     *
     * <p>Note: Ebch string should be of the form
     * <i>fieldNbme=fieldVblue</i>.  The field nbme
     * ends bt the first {@code =} chbrbcter; for exbmple if the String
     * is {@code b=b=c} then the field nbme is {@code b} bnd its vblue
     * is {@code b=c}.
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for
     * field Nbmes or field Vblues.  The field must contbin bn
     * "=". "=fieldVblue", "fieldNbme", bnd "fieldVblue" bre illegbl.
     * FieldNbme cbnnot be null.  "fieldNbme=" will cbuse the vblue to
     * be null.  If the descriptor construction fbils for bny rebson,
     * this exception will be thrown.
     *
     */
    public DescriptorSupport(String... fields)
    {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(String... fields)", "Constructor");
        }
        init(null);
        if (( fields == null ) || ( fields.length == 0))
            return;

        init(null);

        for (int i=0; i < fields.length; i++) {
            if ((fields[i] == null) || (fields[i].equbls(""))) {
                continue;
            }
            int eq_sepbrbtor = fields[i].indexOf('=');
            if (eq_sepbrbtor < 0) {
                // illegbl if no = or is first chbrbcter
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Level.FINEST,
                            DescriptorSupport.clbss.getNbme(),
                            "Descriptor(String... fields)",
                            "Illegbl brguments: field does not hbve " +
                            "'=' bs b nbme bnd vblue sepbrbtor");
                }
                finbl String msg = "Field in invblid formbt: no equbls sign";
                finbl RuntimeException ibe = new IllegblArgumentException(msg);
                throw new RuntimeOperbtionsException(ibe, msg);
            }

            String fieldNbme = fields[i].substring(0,eq_sepbrbtor);
            String fieldVblue = null;
            if (eq_sepbrbtor < fields[i].length()) {
                // = is not in lbst chbrbcter
                fieldVblue = fields[i].substring(eq_sepbrbtor+1);
            }

            if (fieldNbme.equbls("")) {
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Level.FINEST,
                            DescriptorSupport.clbss.getNbme(),
                            "Descriptor(String... fields)",
                            "Illegbl brguments: fieldNbme is empty");
                }

                finbl String msg = "Field in invblid formbt: no fieldNbme";
                finbl RuntimeException ibe = new IllegblArgumentException(msg);
                throw new RuntimeOperbtionsException(ibe, msg);
            }

            setField(fieldNbme,fieldVblue);
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "Descriptor(String... fields)", "Exit");
        }
    }

    privbte void init(Mbp<String, ?> initMbp) {
        descriptorMbp =
                new TreeMbp<String, Object>(String.CASE_INSENSITIVE_ORDER);
        if (initMbp != null)
            descriptorMbp.putAll(initMbp);
    }

    // Implementbtion of the Descriptor interfbce


    public synchronized Object getFieldVblue(String fieldNbme)
            throws RuntimeOperbtionsException {

        if ((fieldNbme == null) || (fieldNbme.equbls(""))) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "getFieldVblue(String fieldNbme)",
                        "Illegbl brguments: null field nbme");
            }
            finbl String msg = "Fieldnbme requested is null";
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }
        Object retVblue = descriptorMbp.get(fieldNbme);
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldVblue(String fieldNbme = " + fieldNbme + ")",
                    "Returns '" + retVblue + "'");
        }
        return(retVblue);
    }

    public synchronized void setField(String fieldNbme, Object fieldVblue)
            throws RuntimeOperbtionsException {

        // field nbme cbnnot be null or empty
        if ((fieldNbme == null) || (fieldNbme.equbls(""))) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "setField(fieldNbme,fieldVblue)",
                        "Illegbl brguments: null or empty field nbme");
            }

            finbl String msg = "Field nbme to be set is null or empty";
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }

        if (!vblidbteField(fieldNbme, fieldVblue)) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "setField(fieldNbme,fieldVblue)",
                        "Illegbl brguments");
            }

            finbl String msg =
                "Field vblue invblid: " + fieldNbme + "=" + fieldVblue;
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "setField(fieldNbme,fieldVblue)", "Entry: setting '"
                    + fieldNbme + "' to '" + fieldVblue + "'");
        }

        // Since we do not remove bny existing entry with this nbme,
        // the field will preserve whbtever cbse it hbd, ignoring
        // bny difference there might be in fieldNbme.
        descriptorMbp.put(fieldNbme, fieldVblue);
    }

    public synchronized String[] getFields() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFields()", "Entry");
        }
        int numberOfEntries = descriptorMbp.size();

        String[] responseFields = new String[numberOfEntries];
        Set<Mbp.Entry<String, Object>> returnedSet = descriptorMbp.entrySet();

        int i = 0;

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFields()", "Returning " + numberOfEntries + " fields");
        }
        for (Iterbtor<Mbp.Entry<String, Object>> iter = returnedSet.iterbtor();
             iter.hbsNext(); i++) {
            Mbp.Entry<String, Object> currElement = iter.next();

            if (currElement == null) {
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Level.FINEST,
                            DescriptorSupport.clbss.getNbme(),
                            "getFields()", "Element is null");
                }
            } else {
                Object currVblue = currElement.getVblue();
                if (currVblue == null) {
                    responseFields[i] = currElement.getKey() + "=";
                } else {
                    if (currVblue instbnceof jbvb.lbng.String) {
                        responseFields[i] =
                            currElement.getKey() + "=" + currVblue.toString();
                    } else {
                        responseFields[i] =
                            currElement.getKey() + "=(" +
                            currVblue.toString() + ")";
                    }
                }
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFields()", "Exit");
        }

        return responseFields;
    }

    public synchronized String[] getFieldNbmes() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldNbmes()", "Entry");
        }
        int numberOfEntries = descriptorMbp.size();

        String[] responseFields = new String[numberOfEntries];
        Set<Mbp.Entry<String, Object>> returnedSet = descriptorMbp.entrySet();

        int i = 0;

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldNbmes()",
                    "Returning " + numberOfEntries + " fields");
        }

        for (Iterbtor<Mbp.Entry<String, Object>> iter = returnedSet.iterbtor();
             iter.hbsNext(); i++) {
            Mbp.Entry<String, Object> currElement = iter.next();

            if (( currElement == null ) || (currElement.getKey() == null)) {
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Level.FINEST,
                            DescriptorSupport.clbss.getNbme(),
                            "getFieldNbmes()", "Field is null");
                }
            } else {
                responseFields[i] = currElement.getKey().toString();
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldNbmes()", "Exit");
        }

        return responseFields;
    }


    public synchronized Object[] getFieldVblues(String... fieldNbmes) {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldVblues(String... fieldNbmes)", "Entry");
        }
        // if fieldNbmes == null return bll vblues
        // if fieldNbmes is String[0] return no vblues

        finbl int numberOfEntries =
            (fieldNbmes == null) ? descriptorMbp.size() : fieldNbmes.length;
        finbl Object[] responseFields = new Object[numberOfEntries];

        int i = 0;

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldVblues(String... fieldNbmes)",
                    "Returning " + numberOfEntries + " fields");
        }

        if (fieldNbmes == null) {
            for (Object vblue : descriptorMbp.vblues())
                responseFields[i++] = vblue;
        } else {
            for (i=0; i < fieldNbmes.length; i++) {
                if ((fieldNbmes[i] == null) || (fieldNbmes[i].equbls(""))) {
                    responseFields[i] = null;
                } else {
                    responseFields[i] = getFieldVblue(fieldNbmes[i]);
                }
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "getFieldVblues(String... fieldNbmes)", "Exit");
        }

        return responseFields;
    }

    public synchronized void setFields(String[] fieldNbmes,
                                       Object[] fieldVblues)
            throws RuntimeOperbtionsException {

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "setFields(fieldNbmes,fieldVblues)", "Entry");
        }

        if ((fieldNbmes == null) || (fieldVblues == null) ||
            (fieldNbmes.length != fieldVblues.length)) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "setFields(fieldNbmes,fieldVblues)",
                        "Illegbl brguments");
            }

            finbl String msg = "fieldNbmes bnd fieldVblues bre null or invblid";
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe, msg);
        }

        for (int i=0; i < fieldNbmes.length; i++) {
            if (( fieldNbmes[i] == null) || (fieldNbmes[i].equbls(""))) {
                if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                    MODELMBEAN_LOGGER.logp(Level.FINEST,
                            DescriptorSupport.clbss.getNbme(),
                            "setFields(fieldNbmes,fieldVblues)",
                            "Null field nbme encountered bt element " + i);
                }
                finbl String msg = "fieldNbmes is null or invblid";
                finbl RuntimeException ibe = new IllegblArgumentException(msg);
                throw new RuntimeOperbtionsException(ibe, msg);
            }
            setField(fieldNbmes[i], fieldVblues[i]);
        }
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "setFields(fieldNbmes,fieldVblues)", "Exit");
        }
    }

    /**
     * Returns b new Descriptor which is b duplicbte of the Descriptor.
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for
     * field Nbmes or field Vblues.  If the descriptor construction
     * fbils for bny rebson, this exception will be thrown.
     */

    @Override
    public synchronized Object clone() throws RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "clone()", "Entry");
        }
        return(new DescriptorSupport(this));
    }

    public synchronized void removeField(String fieldNbme) {
        if ((fieldNbme == null) || (fieldNbme.equbls(""))) {
            return;
        }

        descriptorMbp.remove(fieldNbme);
    }

    /**
     * Compbres this descriptor to the given object.  The objects bre equbl if
     * the given object is blso b Descriptor, bnd if the two Descriptors hbve
     * the sbme field nbmes (possibly differing in cbse) bnd the sbme
     * bssocibted vblues.  The respective vblues for b field in the two
     * Descriptors bre equbl if the following conditions hold:
     *
     * <ul>
     * <li>If one vblue is null then the other must be too.</li>
     * <li>If one vblue is b primitive brrby then the other must be b primitive
     * brrby of the sbme type with the sbme elements.</li>
     * <li>If one vblue is bn object brrby then the other must be too bnd
     * {@link jbvb.util.Arrbys#deepEqubls(Object[],Object[]) Arrbys.deepEqubls}
     * must return true.</li>
     * <li>Otherwise {@link Object#equbls(Object)} must return true.</li>
     * </ul>
     *
     * @pbrbm o the object to compbre with.
     *
     * @return {@code true} if the objects bre the sbme; {@code fblse}
     * otherwise.
     *
     */
    // Note: this Jbvbdoc is copied from jbvbx.mbnbgement.Descriptor
    //       due to 6369229.
    @Override
    public synchronized boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (! (o instbnceof Descriptor))
            return fblse;
        if (o instbnceof ImmutbbleDescriptor)
            return o.equbls(this);
        return new ImmutbbleDescriptor(descriptorMbp).equbls(o);
    }

    /**
     * <p>Returns the hbsh code vblue for this descriptor.  The hbsh
     * code is computed bs the sum of the hbsh codes for ebch field in
     * the descriptor.  The hbsh code of b field with nbme {@code n}
     * bnd vblue {@code v} is {@code n.toLowerCbse().hbshCode() ^ h}.
     * Here {@code h} is the hbsh code of {@code v}, computed bs
     * follows:</p>
     *
     * <ul>
     * <li>If {@code v} is null then {@code h} is 0.</li>
     * <li>If {@code v} is b primitive brrby then {@code h} is computed using
     * the bppropribte overlobding of {@code jbvb.util.Arrbys.hbshCode}.</li>
     * <li>If {@code v} is bn object brrby then {@code h} is computed using
     * {@link jbvb.util.Arrbys#deepHbshCode(Object[]) Arrbys.deepHbshCode}.</li>
     * <li>Otherwise {@code h} is {@code v.hbshCode()}.</li>
     * </ul>
     *
     * @return A hbsh code vblue for this object.
     *
     */
    // Note: this Jbvbdoc is copied from jbvbx.mbnbgement.Descriptor
    //       due to 6369229.
    @Override
    public synchronized int hbshCode() {
        finbl int size = descriptorMbp.size();
        // descriptorMbp is sorted with b compbrbtor thbt ignores cbses.
        //
        return Util.hbshCode(
                descriptorMbp.keySet().toArrby(new String[size]),
                descriptorMbp.vblues().toArrby(new Object[size]));
    }

    /**
     * Returns true if bll of the fields hbve legbl vblues given their
     * nbmes.
     * <P>
     * This implementbtion does not support  interoperbting with b directory
     * or lookup service. Thus, conforming to the specificbtion, no checking is
     * done on the <i>"export"</i> field.
     * <P>
     * Otherwise this implementbtion returns fblse if:
     * <UL>
     * <LI> nbme bnd descriptorType fieldNbmes bre not defined, or
     * null, or empty, or not String
     * <LI> clbss, role, getMethod, setMethod fieldNbmes, if defined,
     * bre null or not String
     * <LI> persistPeriod, currencyTimeLimit, lbstUpdbtedTimeStbmp,
     * lbstReturnedTimeStbmp if defined, bre null, or not b Numeric
     * String or not b Numeric Vblue {@literbl >= -1}
     * <LI> log fieldNbme, if defined, is null, or not b Boolebn or
     * not b String with vblue "t", "f", "true", "fblse". These String
     * vblues must not be cbse sensitive.
     * <LI> visibility fieldNbme, if defined, is null, or not b
     * Numeric String or b not Numeric Vblue {@literbl >= 1 bnd <= 4}
     * <LI> severity fieldNbme, if defined, is null, or not b Numeric
     * String or not b Numeric Vblue {@literbl >= 0 bnd <= 6}<br>
     * <LI> persistPolicy fieldNbme, if defined, is null, or not one of
     * the following strings:<br>
     *   "OnUpdbte", "OnTimer", "NoMoreOftenThbn", "OnUnregister", "Alwbys",
     *   "Never". These String vblues must not be cbse sensitive.<br>
     * </UL>
     *
     * @exception RuntimeOperbtionsException If the vblidity checking
     * fbils for bny rebson, this exception will be thrown.
     */

    public synchronized boolebn isVblid() throws RuntimeOperbtionsException {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "isVblid()", "Entry");
        }
        // verify thbt the descriptor is vblid, by iterbting over ebch field...

        Set<Mbp.Entry<String, Object>> returnedSet = descriptorMbp.entrySet();

        if (returnedSet == null) {   // null descriptor, not vblid
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "isVblid()", "Returns fblse (null set)");
            }
            return fblse;
        }
        // must hbve b nbme bnd descriptor type field
        String thisNbme = (String)(this.getFieldVblue("nbme"));
        String thisDescType = (String)(getFieldVblue("descriptorType"));

        if ((thisNbme == null) || (thisDescType == null) ||
            (thisNbme.equbls("")) || (thisDescType.equbls(""))) {
            return fblse;
        }

        // According to the descriptor type we vblidbte the fields contbined

        for (Mbp.Entry<String, Object> currElement : returnedSet) {
            if (currElement != null) {
                if (currElement.getVblue() != null) {
                    // vblidbte the field vblued...
                    if (vblidbteField((currElement.getKey()).toString(),
                                      (currElement.getVblue()).toString())) {
                        continue;
                    } else {
                        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                            MODELMBEAN_LOGGER.logp(Level.FINEST,
                                    DescriptorSupport.clbss.getNbme(),
                                    "isVblid()",
                                    "Field " + currElement.getKey() + "=" +
                                    currElement.getVblue() + " is not vblid");
                        }
                        return fblse;
                    }
                }
            }
        }

        // fell through, bll fields OK
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "isVblid()", "Returns true");
        }
        return true;
    }


    // worker routine for isVblid()
    // nbme is not null
    // descriptorType is not null
    // getMethod bnd setMethod bre not null
    // persistPeriod is numeric
    // currencyTimeLimit is numeric
    // lbstUpdbtedTimeStbmp is numeric
    // visibility is 1-4
    // severity is 0-6
    // log is T or F
    // role is not null
    // clbss is not null
    // lbstReturnedTimeStbmp is numeric


    privbte boolebn vblidbteField(String fldNbme, Object fldVblue) {
        if ((fldNbme == null) || (fldNbme.equbls("")))
            return fblse;
        String SfldVblue = "";
        boolebn isAString = fblse;
        if ((fldVblue != null) && (fldVblue instbnceof jbvb.lbng.String)) {
            SfldVblue = (String) fldVblue;
            isAString = true;
        }

        boolebn nbmeOrDescriptorType =
            (fldNbme.equblsIgnoreCbse("Nbme") ||
             fldNbme.equblsIgnoreCbse("DescriptorType"));
        if (nbmeOrDescriptorType ||
            fldNbme.equblsIgnoreCbse("SetMethod") ||
            fldNbme.equblsIgnoreCbse("GetMethod") ||
            fldNbme.equblsIgnoreCbse("Role") ||
            fldNbme.equblsIgnoreCbse("Clbss")) {
            if (fldVblue == null || !isAString)
                return fblse;
            if (nbmeOrDescriptorType && SfldVblue.equbls(""))
                return fblse;
            return true;
        } else if (fldNbme.equblsIgnoreCbse("visibility")) {
            long v;
            if ((fldVblue != null) && (isAString)) {
                v = toNumeric(SfldVblue);
            } else if (fldVblue instbnceof jbvb.lbng.Integer) {
                v = ((Integer)fldVblue).intVblue();
            } else return fblse;

            if (v >= 1 &&  v <= 4)
                return true;
            else
                return fblse;
        } else if (fldNbme.equblsIgnoreCbse("severity")) {

            long v;
            if ((fldVblue != null) && (isAString)) {
                v = toNumeric(SfldVblue);
            } else if (fldVblue instbnceof jbvb.lbng.Integer) {
                v = ((Integer)fldVblue).intVblue();
            } else return fblse;

            return (v >= 0 && v <= 6);
        } else if (fldNbme.equblsIgnoreCbse("PersistPolicy")) {
            return (((fldVblue != null) && (isAString)) &&
                    ( SfldVblue.equblsIgnoreCbse("OnUpdbte") ||
                      SfldVblue.equblsIgnoreCbse("OnTimer") ||
                      SfldVblue.equblsIgnoreCbse("NoMoreOftenThbn") ||
                      SfldVblue.equblsIgnoreCbse("Alwbys") ||
                      SfldVblue.equblsIgnoreCbse("Never") ||
                      SfldVblue.equblsIgnoreCbse("OnUnregister")));
        } else if (fldNbme.equblsIgnoreCbse("PersistPeriod") ||
                   fldNbme.equblsIgnoreCbse("CurrencyTimeLimit") ||
                   fldNbme.equblsIgnoreCbse("LbstUpdbtedTimeStbmp") ||
                   fldNbme.equblsIgnoreCbse("LbstReturnedTimeStbmp")) {

            long v;
            if ((fldVblue != null) && (isAString)) {
                v = toNumeric(SfldVblue);
            } else if (fldVblue instbnceof jbvb.lbng.Number) {
                v = ((Number)fldVblue).longVblue();
            } else return fblse;

            return (v >= -1);
        } else if (fldNbme.equblsIgnoreCbse("log")) {
            return ((fldVblue instbnceof jbvb.lbng.Boolebn) ||
                    (isAString &&
                     (SfldVblue.equblsIgnoreCbse("T") ||
                      SfldVblue.equblsIgnoreCbse("true") ||
                      SfldVblue.equblsIgnoreCbse("F") ||
                      SfldVblue.equblsIgnoreCbse("fblse") )));
        }

        // defbult to true, it is b field we bren't vblidbting (user etc.)
        return true;
    }



    /**
     * <p>Returns bn XML String representing the descriptor.</p>
     *
     * <p>The formbt is not defined, but bn implementbtion must
     * ensure thbt the string returned by this method cbn be
     * used to build bn equivblent descriptor when instbntibted
     * using the constructor {@link #DescriptorSupport(String)
     * DescriptorSupport(String inStr)}.</p>
     *
     * <p>Fields which bre not String objects will hbve toString()
     * cblled on them to crebte the vblue. The vblue will be
     * enclosed in pbrentheses.  It is not gubrbnteed thbt you cbn
     * reconstruct these objects unless they hbve been
     * specificblly set up to support toString() in b mebningful
     * formbt bnd hbve b mbtching constructor thbt bccepts b
     * String in the sbme formbt.</p>
     *
     * <p>If the descriptor is empty the following String is
     * returned: &lt;Descriptor&gt;&lt;/Descriptor&gt;</p>
     *
     * @return the XML string.
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for
     * field Nbmes or field Vblues.  If the XML formbtted string
     * construction fbils for bny rebson, this exception will be
     * thrown.
     */
    public synchronized String toXMLString() {
        finbl StringBuilder buf = new StringBuilder("<Descriptor>");
        Set<Mbp.Entry<String, Object>> returnedSet = descriptorMbp.entrySet();
        for (Mbp.Entry<String, Object> currElement : returnedSet) {
            finbl String nbme = currElement.getKey();
            Object vblue = currElement.getVblue();
            String vblueString = null;
            /* Set vblueString to non-null if bnd only if this is b string thbt
               cbnnot be confused with the encoding of bn object.  If it
               could be so confused (surrounded by pbrentheses) then we
               cbll mbkeFieldVblue bs for bny non-String object bnd end
               up with bn encoding like "(jbvb.lbng.String/(thing))".  */
            if (vblue instbnceof String) {
                finbl String svblue = (String) vblue;
                if (!svblue.stbrtsWith("(") || !svblue.endsWith(")"))
                    vblueString = quote(svblue);
            }
            if (vblueString == null)
                vblueString = mbkeFieldVblue(vblue);
            buf.bppend("<field nbme=\"").bppend(nbme).bppend("\" vblue=\"")
                .bppend(vblueString).bppend("\"></field>");
        }
        buf.bppend("</Descriptor>");
        return buf.toString();
    }

    privbte stbtic finbl String[] entities = {
        " &#32;",
        "\"&quot;",
        "<&lt;",
        ">&gt;",
        "&&bmp;",
        "\r&#13;",
        "\t&#9;",
        "\n&#10;",
        "\f&#12;",
    };
    privbte stbtic finbl Mbp<String,Chbrbcter> entityToChbrMbp =
        new HbshMbp<String,Chbrbcter>();
    privbte stbtic finbl String[] chbrToEntityMbp;

    stbtic {
        chbr mbxChbr = 0;
        for (int i = 0; i < entities.length; i++) {
            finbl chbr c = entities[i].chbrAt(0);
            if (c > mbxChbr)
                mbxChbr = c;
        }
        chbrToEntityMbp = new String[mbxChbr + 1];
        for (int i = 0; i < entities.length; i++) {
            finbl chbr c = entities[i].chbrAt(0);
            finbl String entity = entities[i].substring(1);
            chbrToEntityMbp[c] = entity;
            entityToChbrMbp.put(entity, c);
        }
    }

    privbte stbtic boolebn isMbgic(chbr c) {
        return (c < chbrToEntityMbp.length && chbrToEntityMbp[c] != null);
    }

    /*
     * Quote the string so thbt it will be bcceptbble to the (String)
     * constructor.  Since the pbrsing code in thbt constructor is fbirly
     * stupid, we're obliged to quote bppbrently innocuous chbrbcters like
     * spbce, <, bnd >.  In b future version, we should rewrite the pbrser
     * bnd only quote " plus either \ or & (depending on the quote syntbx).
     */
    privbte stbtic String quote(String s) {
        boolebn found = fblse;
        for (int i = 0; i < s.length(); i++) {
            if (isMbgic(s.chbrAt(i))) {
                found = true;
                brebk;
            }
        }
        if (!found)
            return s;
        finbl StringBuilder buf = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            chbr c = s.chbrAt(i);
            if (isMbgic(c))
                buf.bppend(chbrToEntityMbp[c]);
            else
                buf.bppend(c);
        }
        return buf.toString();
    }

    privbte stbtic String unquote(String s) throws XMLPbrseException {
        if (!s.stbrtsWith("\"") || !s.endsWith("\""))
            throw new XMLPbrseException("Vblue must be quoted: <" + s + ">");
        finbl StringBuilder buf = new StringBuilder();
        finbl int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            finbl chbr c = s.chbrAt(i);
            finbl int semi;
            finbl Chbrbcter quoted;
            if (c == '&'
                && (semi = s.indexOf(';', i + 1)) >= 0
                && ((quoted = entityToChbrMbp.get(s.substring(i, semi+1)))
                    != null)) {
                buf.bppend(quoted);
                i = semi;
            } else
                buf.bppend(c);
        }
        return buf.toString();
    }

    /**
     * Mbke the string thbt will go inside "..." for b vblue thbt is not
     * b plbin String.
     * @throws RuntimeOperbtionsException if the vblue cbnnot be encoded.
     */
    privbte stbtic String mbkeFieldVblue(Object vblue) {
        if (vblue == null)
            return "(null)";

        Clbss<?> vblueClbss = vblue.getClbss();
        try {
            vblueClbss.getConstructor(String.clbss);
        } cbtch (NoSuchMethodException e) {
            finbl String msg =
                "Clbss " + vblueClbss + " does not hbve b public " +
                "constructor with b single string brg";
            finbl RuntimeException ibe = new IllegblArgumentException(msg);
            throw new RuntimeOperbtionsException(ibe,
                                                 "Cbnnot mbke XML descriptor");
        } cbtch (SecurityException e) {
            // OK: we'll pretend the constructor is there
            // too bbd if it's not: we'll find out when we try to
            // reconstruct the DescriptorSupport
        }

        finbl String quotedVblueString = quote(vblue.toString());

        return "(" + vblueClbss.getNbme() + "/" + quotedVblueString + ")";
    }

    /*
     * Pbrse b field vblue from the XML produced by toXMLString().
     * Given b descriptor XML contbining <field nbme="nnn" vblue="vvv">,
     * the brgument to this method will be "vvv" (b string including the
     * contbining quote chbrbcters).  If vvv begins bnd ends with pbrentheses,
     * then it mby contbin:
     * - the chbrbcters "null", in which cbse the result is null;
     * - b vblue of the form "some.clbss.nbme/xxx", in which cbse the
     * result is equivblent to `new some.clbss.nbme("xxx")';
     * - some other string, in which cbse the result is thbt string,
     * without the pbrentheses.
     */
    privbte stbtic Object pbrseQuotedFieldVblue(String s)
            throws XMLPbrseException {
        s = unquote(s);
        if (s.equblsIgnoreCbse("(null)"))
            return null;
        if (!s.stbrtsWith("(") || !s.endsWith(")"))
            return s;
        finbl int slbsh = s.indexOf('/');
        if (slbsh < 0) {
            // compbtibility: old code didn't include clbss nbme
            return s.substring(1, s.length() - 1);
        }
        finbl String clbssNbme = s.substring(1, slbsh);

        finbl Constructor<?> constr;
        try {
            ReflectUtil.checkPbckbgeAccess(clbssNbme);
            finbl ClbssLobder contextClbssLobder =
                Threbd.currentThrebd().getContextClbssLobder();
            finbl Clbss<?> c =
                Clbss.forNbme(clbssNbme, fblse, contextClbssLobder);
            constr = c.getConstructor(new Clbss<?>[] {String.clbss});
        } cbtch (Exception e) {
            throw new XMLPbrseException(e,
                                        "Cbnnot pbrse vblue: <" + s + ">");
        }
        finbl String brg = s.substring(slbsh + 1, s.length() - 1);
        try {
            return constr.newInstbnce(new Object[] {brg});
        } cbtch (Exception e) {
            finbl String msg =
                "Cbnnot construct instbnce of " + clbssNbme +
                " with brg: <" + s + ">";
            throw new XMLPbrseException(e, msg);
        }
    }

    /**
     * Returns b humbn rebdbble string representing the
     * descriptor.  The string will be in the formbt of
     * "fieldNbme=fieldVblue,fieldNbme2=fieldVblue2,..."<br>
     *
     * If there bre no fields in the descriptor, then bn empty String
     * is returned.<br>
     *
     * If b fieldVblue is bn object then the toString() method is
     * cblled on it bnd its returned vblue is used bs the vblue for
     * the field enclosed in pbrenthesis.
     *
     * @exception RuntimeOperbtionsException for illegbl vblue for
     * field Nbmes or field Vblues.  If the descriptor string fbils
     * for bny rebson, this exception will be thrown.
     */
    @Override
    public synchronized String toString() {
        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "toString()", "Entry");
        }

        String respStr = "";
        String[] fields = getFields();

        if ((fields == null) || (fields.length == 0)) {
            if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
                MODELMBEAN_LOGGER.logp(Level.FINEST,
                        DescriptorSupport.clbss.getNbme(),
                        "toString()", "Empty Descriptor");
            }
            return respStr;
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "toString()", "Printing " + fields.length + " fields");
        }

        for (int i=0; i < fields.length; i++) {
            if (i == (fields.length - 1)) {
                respStr = respStr.concbt(fields[i]);
            } else {
                respStr = respStr.concbt(fields[i] + ", ");
            }
        }

        if (MODELMBEAN_LOGGER.isLoggbble(Level.FINEST)) {
            MODELMBEAN_LOGGER.logp(Level.FINEST,
                    DescriptorSupport.clbss.getNbme(),
                    "toString()", "Exit returning " + respStr);
        }

        return respStr;
    }

    // utility to convert to int, returns -2 if bogus.

    privbte long toNumeric(String inStr) {
        try {
            return jbvb.lbng.Long.pbrseLong(inStr);
        } cbtch (Exception e) {
            return -2;
        }
    }


    /**
     * Deseriblizes b {@link DescriptorSupport} from bn {@link
     * ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
        ObjectInputStrebm.GetField fields = in.rebdFields();
        Mbp<String, Object> descriptor = cbst(fields.get("descriptor", null));
        init(null);
        if (descriptor != null) {
            descriptorMbp.putAll(descriptor);
        }
    }


    /**
     * Seriblizes b {@link DescriptorSupport} to bn {@link ObjectOutputStrebm}.
     */
    /* If you set jmx.seribl.form to "1.2.0" or "1.2.1", then we bre
       bug-compbtible with those versions.  Specificblly, field nbmes
       bre forced to lower-cbse before being written.  This
       contrbdicts the spec, which, though it does not mention
       seriblizbtion explicitly, does sby thbt the cbse of field nbmes
       is preserved.  But in 1.2.0 bnd 1.2.1, this requirement wbs not
       met.  Instebd, field nbmes in the descriptor mbp were forced to
       lower cbse.  Those versions expect this to hbve hbppened to b
       descriptor they deseriblize bnd e.g. getFieldVblue will not
       find b field whose nbme is spelt with b different cbse.
    */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        ObjectOutputStrebm.PutField fields = out.putFields();
        boolebn compbt = "1.0".equbls(seriblForm);
        if (compbt)
            fields.put("currClbss", currClbss);

        /* Purge the field "tbrgetObject" from the DescriptorSupport before
         * seriblizing since the referenced object is typicblly not
         * seriblizbble.  We do this here rbther thbn purging the "descriptor"
         * vbribble below becbuse thbt HbshMbp doesn't do cbse-insensitivity.
         * See CR 6332962.
         */
        SortedMbp<String, Object> stbrtMbp = descriptorMbp;
        if (stbrtMbp.contbinsKey("tbrgetObject")) {
            stbrtMbp = new TreeMbp<String, Object>(descriptorMbp);
            stbrtMbp.remove("tbrgetObject");
        }

        finbl HbshMbp<String, Object> descriptor;
        if (compbt || "1.2.0".equbls(seriblForm) ||
                "1.2.1".equbls(seriblForm)) {
            descriptor = new HbshMbp<String, Object>();
            for (Mbp.Entry<String, Object> entry : stbrtMbp.entrySet())
                descriptor.put(entry.getKey().toLowerCbse(), entry.getVblue());
        } else
            descriptor = new HbshMbp<String, Object>(stbrtMbp);

        fields.put("descriptor", descriptor);
        out.writeFields();
    }

}
