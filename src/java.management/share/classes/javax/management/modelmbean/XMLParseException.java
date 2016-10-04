/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.security.AccessController;

/**
* This exception is thrown when bn XML formbtted string is being pbrsed into ModelMBebn objects
* or when XML formbtted strings bre being crebted from ModelMBebn objects.
*
* It is blso used to wrbpper exceptions from XML pbrsers thbt mby be used.
*
* <p>The <b>seriblVersionUID</b> of this clbss is <code>3176664577895105181L</code>.
*
* @since 1.5
*/
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss XMLPbrseException
extends Exception
{
    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -7780049316655891976L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 3176664577895105181L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("msgStr", String.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
  privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields = { };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
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
     * Defbult constructor .
     */
    public  XMLPbrseException ()
    {
      super("XML Pbrse Exception.");
    }

    /**
     * Constructor tbking b string.
     *
     * @pbrbm s the detbil messbge.
     */
    public  XMLPbrseException (String s)
    {
      super("XML Pbrse Exception: " + s);
    }
    /**
     * Constructor tbking b string bnd bn exception.
     *
     * @pbrbm e the nested exception.
     * @pbrbm s the detbil messbge.
     */
    public  XMLPbrseException (Exception e, String s)
    {
      super("XML Pbrse Exception: " + s + ":" + e.toString());
    }

    /**
     * Deseriblizes bn {@link XMLPbrseException} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      // New seribl form ignores extrb field "msgStr"
      in.defbultRebdObject();
    }


    /**
     * Seriblizes bn {@link XMLPbrseException} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("msgStr", getMessbge());
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
