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
 * Exception thrown when bn invblid tbrget object type is specified.
 *
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>1190536278266811217L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
public clbss InvblidTbrgetObjectTypeException  extends Exception
{

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 3711724570458346634L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 1190536278266811217L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("msgStr", String.clbss),
      new ObjectStrebmField("relbtedExcept", Exception.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("exception", Exception.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField exception Exception Encbpsulbted {@link Exception}
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
     * @seribl Encbpsulbted {@link Exception}
     */
    Exception exception;


    /**
     * Defbult constructor.
     */
    public InvblidTbrgetObjectTypeException ()
    {
      super("InvblidTbrgetObjectTypeException: ");
      exception = null;
    }


    /**
     * Constructor from b string.
     *
     * @pbrbm s String vblue thbt will be incorporbted in the messbge for
     *    this exception.
     */

    public InvblidTbrgetObjectTypeException (String s)
    {
      super("InvblidTbrgetObjectTypeException: " + s);
      exception = null;
    }


    /**
     * Constructor tbking bn exception bnd b string.
     *
     * @pbrbm e Exception thbt we mby hbve cbught to reissue bs bn
     *    InvblidTbrgetObjectTypeException.  The messbge will be used, bnd we mby wbnt to
     *    consider overriding the printStbckTrbce() methods to get dbtb
     *    pointing bbck to originbl throw stbck.
     * @pbrbm s String vblue thbt will be incorporbted in messbge for
     *    this exception.
     */

    public InvblidTbrgetObjectTypeException (Exception e, String s)
    {
      super("InvblidTbrgetObjectTypeException: " +
            s +
            ((e != null)?("\n\t triggered by:" + e.toString()):""));
      exception = e;
    }

    /**
     * Deseriblizes bn {@link InvblidTbrgetObjectTypeException} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        ObjectInputStrebm.GetField fields = in.rebdFields();
        exception = (Exception) fields.get("relbtedExcept", null);
        if (fields.defbulted("relbtedExcept"))
        {
          throw new NullPointerException("relbtedExcept");
        }
      }
      else
      {
        // Rebd bn object seriblized in the new seribl form
        //
        in.defbultRebdObject();
      }
    }


    /**
     * Seriblizes bn {@link InvblidTbrgetObjectTypeException} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("relbtedExcept", exception);
        fields.put("msgStr", ((exception != null)?exception.getMessbge():""));
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
