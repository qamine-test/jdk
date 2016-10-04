/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.util.EventObject;

import jbvb.security.AccessController;

import com.sun.jmx.mbebnserver.GetPropertyAction;

/**
 * <p>The Notificbtion clbss represents b notificbtion emitted by bn
 * MBebn.  It contbins b reference to the source MBebn: if the
 * notificbtion hbs been forwbrded through the MBebn server, bnd the
 * originbl source of the notificbtion wbs b reference to the emitting
 * MBebn object, then the MBebn server replbces it by the MBebn's
 * ObjectNbme.  If the listener hbs registered directly with the
 * MBebn, this is either the object nbme or b direct reference to the
 * MBebn.</p>
 *
 * <p>It is strongly recommended thbt notificbtion senders use the
 * object nbme rbther thbn b reference to the MBebn object bs the
 * source.</p>
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-7516092053498031989L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID is not constbnt
public clbss Notificbtion extends EventObject {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 1716977971058914352L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -7516092053498031989L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
        new ObjectStrebmField("messbge", String.clbss),
        new ObjectStrebmField("sequenceNumber", Long.TYPE),
        new ObjectStrebmField("source", Object.clbss),
        new ObjectStrebmField("sourceObjectNbme", ObjectNbme.clbss),
        new ObjectStrebmField("timeStbmp", Long.TYPE),
        new ObjectStrebmField("type", String.clbss),
        new ObjectStrebmField("userDbtb", Object.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
        new ObjectStrebmField("messbge", String.clbss),
        new ObjectStrebmField("sequenceNumber", Long.TYPE),
        new ObjectStrebmField("source", Object.clbss),
        new ObjectStrebmField("timeStbmp", Long.TYPE),
        new ObjectStrebmField("type", String.clbss),
        new ObjectStrebmField("userDbtb", Object.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField type String The notificbtion type.
     *              A string expressed in b dot notbtion similbr to Jbvb properties.
     *              An exbmple of b notificbtion type is network.blbrm.router
     * @seriblField sequenceNumber long The notificbtion sequence number.
     *              A seribl number which identify pbrticulbr instbnce
     *              of notificbtion in the context of the notificbtion source.
     * @seriblField timeStbmp long The notificbtion timestbmp.
     *              Indicbting when the notificbtion wbs generbted
     * @seriblField userDbtb Object The notificbtion user dbtb.
     *              Used for whbtever other dbtb the notificbtion
     *              source wishes to communicbte to its consumers
     * @seriblField messbge String The notificbtion messbge.
     * @seriblField source Object The object on which the notificbtion initiblly occurred.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte stbtic boolebn compbt = fblse;
    stbtic {
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            compbt = (form != null && form.equbls("1.0"));
        } cbtch (Exception e) {
            // OK: exception mebns no compbt with 1.0, too bbd
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
     * @seribl The notificbtion type.
     *         A string expressed in b dot notbtion similbr to Jbvb properties.
     *         An exbmple of b notificbtion type is network.blbrm.router
     */
    privbte String type;

    /**
     * @seribl The notificbtion sequence number.
     *         A seribl number which identify pbrticulbr instbnce
     *         of notificbtion in the context of the notificbtion source.
     */
    privbte long sequenceNumber;

    /**
     * @seribl The notificbtion timestbmp.
     *         Indicbting when the notificbtion wbs generbted
     */
    privbte long timeStbmp;

    /**
     * @seribl The notificbtion user dbtb.
     *         Used for whbtever other dbtb the notificbtion
     *         source wishes to communicbte to its consumers
     */
    privbte Object userDbtb = null;

    /**
     * @seribl The notificbtion messbge.
     */
    privbte String messbge  = "";

    /**
     * <p>This field hides the {@link EventObject#source} field in the
     * pbrent clbss to mbke it non-trbnsient bnd therefore pbrt of the
     * seriblized form.</p>
     *
     * @seribl The object on which the notificbtion initiblly occurred.
     */
    protected Object source = null;


    /**
     * Crebtes b Notificbtion object.
     * The notificbtion timeStbmp is set to the current dbte.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion source.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     *
     */
    public Notificbtion(String type, Object source, long sequenceNumber) {
        super (source) ;
        this.source = source;
        this.type = type;
        this.sequenceNumber = sequenceNumber ;
        this.timeStbmp = (new jbvb.util.Dbte()).getTime() ;
    }

    /**
     * Crebtes b Notificbtion object.
     * The notificbtion timeStbmp is set to the current dbte.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion source.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     * @pbrbm messbge The detbiled messbge.
     *
     */
    public Notificbtion(String type, Object source, long sequenceNumber, String messbge) {
        super (source) ;
        this.source = source;
        this.type = type;
        this.sequenceNumber = sequenceNumber ;
        this.timeStbmp = (new jbvb.util.Dbte()).getTime() ;
        this.messbge = messbge ;
    }

    /**
     * Crebtes b Notificbtion object.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion source.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     * @pbrbm timeStbmp The notificbtion emission dbte.
     *
     */
    public Notificbtion(String type, Object source, long sequenceNumber, long timeStbmp) {
        super (source) ;
        this.source = source;
        this.type = type ;
        this.sequenceNumber = sequenceNumber ;
        this.timeStbmp = timeStbmp ;
    }

    /**
     * Crebtes b Notificbtion object.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion source.
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object.
     * @pbrbm timeStbmp The notificbtion emission dbte.
     * @pbrbm messbge The detbiled messbge.
     *
     */
    public Notificbtion(String type, Object source, long sequenceNumber, long timeStbmp, String messbge) {
        super (source) ;
        this.source = source;
        this.type = type ;
        this.sequenceNumber = sequenceNumber ;
        this.timeStbmp = timeStbmp ;
        this.messbge = messbge ;
    }

    /**
     * Sets the source.
     *
     * @pbrbm source the new source for this object.
     *
     * @see EventObject#getSource
     */
    public void setSource(Object source) {
        super.source = source;
        this.source = source;
    }

    /**
     * Get the notificbtion sequence number.
     *
     * @return The notificbtion sequence number within the source object. It's b seribl number
     * identifying b pbrticulbr instbnce of notificbtion in the context of the notificbtion source.
     * The notificbtion model does not bssume thbt notificbtions will be received in the sbme order
     * thbt they bre sent. The sequence number helps listeners to sort received notificbtions.
     *
     * @see #setSequenceNumber
     */
    public long getSequenceNumber() {
        return sequenceNumber ;
    }

    /**
     * Set the notificbtion sequence number.
     *
     * @pbrbm sequenceNumber The notificbtion sequence number within the source object. It is
     * b seribl number identifying b pbrticulbr instbnce of notificbtion in the
     * context of the notificbtion source.
     *
     * @see #getSequenceNumber
     */
    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Get the notificbtion type.
     *
     * @return The notificbtion type. It's b string expressed in b dot notbtion
     * similbr to Jbvb properties. It is recommended thbt the notificbtion type
     * should follow the reverse-dombin-nbme convention used by Jbvb pbckbge
     * nbmes.  An exbmple of b notificbtion type is com.exbmple.blbrm.router.
     */
    public String getType() {
        return type ;
    }

    /**
     * Get the notificbtion timestbmp.
     *
     * @return The notificbtion timestbmp.
     *
     * @see #setTimeStbmp
     */
    public long getTimeStbmp() {
        return timeStbmp ;
    }

    /**
     * Set the notificbtion timestbmp.
     *
     * @pbrbm timeStbmp The notificbtion timestbmp. It indicbtes when the notificbtion wbs generbted.
     *
     * @see #getTimeStbmp
     */
    public void setTimeStbmp(long timeStbmp) {
        this.timeStbmp = timeStbmp;
    }

    /**
     * Get the notificbtion messbge.
     *
     * @return The messbge string of this notificbtion object.
     *
     */
    public String getMessbge() {
        return messbge ;
    }

    /**
     * Get the user dbtb.
     *
     * @return The user dbtb object. It is used for whbtever dbtb
     * the notificbtion source wishes to communicbte to its consumers.
     *
     * @see #setUserDbtb
     */
    public Object getUserDbtb() {
        return userDbtb ;
    }

    /**
     * Set the user dbtb.
     *
     * @pbrbm userDbtb The user dbtb object. It is used for whbtever dbtb
     * the notificbtion source wishes to communicbte to its consumers.
     *
     * @see #getUserDbtb
     */
    public void setUserDbtb(Object userDbtb) {

        this.userDbtb = userDbtb ;
    }

    /**
     * Returns b String representbtion of this notificbtion.
     *
     * @return A String representbtion of this notificbtion.
     */
    @Override
    public String toString() {
        return super.toString()+"[type="+type+"][messbge="+messbge+"]";
    }

    /**
     * Deseriblizes b {@link Notificbtion} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      // New seribl form ignores extrb field "sourceObjectNbme"
      in.defbultRebdObject();
      super.source = source;
    }


    /**
     * Seriblizes b {@link Notificbtion} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
        if (compbt) {
            // Seriblizes this instbnce in the old seribl form
            //
            ObjectOutputStrebm.PutField fields = out.putFields();
            fields.put("type", type);
            fields.put("sequenceNumber", sequenceNumber);
            fields.put("timeStbmp", timeStbmp);
            fields.put("userDbtb", userDbtb);
            fields.put("messbge", messbge);
            fields.put("source", source);
            out.writeFields();
        } else {
            // Seriblizes this instbnce in the new seribl form
            //
            out.defbultWriteObject();
        }
    }
}
