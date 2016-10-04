/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;

import jbvb.security.AccessController;

/**
 * This clbss represents numbers thbt bre brguments to relbtionbl constrbints.
 * A NumericVblueExp mby be used bnywhere b VblueExp is required.
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>-4679739485102359104L</code>.
 *
 * @seribl include
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID not constbnt
clbss NumericVblueExp extends QueryEvbl implements VblueExp {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = -6227876276058904000L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = -4679739485102359104L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
        new ObjectStrebmField("longVbl", Long.TYPE),
        new ObjectStrebmField("doubleVbl", Double.TYPE),
        new ObjectStrebmField("vblIsLong", Boolebn.TYPE)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
        new ObjectStrebmField("vbl", Number.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;

    /**
     * @seriblField vbl Number The numeric vblue
     *
     * <p>The <b>seriblVersionUID</b> of this clbss is <code>-4679739485102359104L</code>.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte Number vbl = 0.0;

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
     * Bbsic constructor.
     */
    public NumericVblueExp() {
    }

    /** Crebtes b new NumericVblue representing the numeric literbl @{code vbl}.*/
    NumericVblueExp(Number vbl)
    {
      this.vbl = vbl;
    }

    /**
     * Returns b double numeric vblue
     */
    public double doubleVblue()  {
      if (vbl instbnceof Long || vbl instbnceof Integer)
      {
        return (double)(vbl.longVblue());
      }
      return vbl.doubleVblue();
    }

    /**
     * Returns b long numeric vblue
     */
    public long longVblue()  {
      if (vbl instbnceof Long || vbl instbnceof Integer)
      {
        return vbl.longVblue();
      }
      return (long)(vbl.doubleVblue());
    }

    /**
     * Returns true is if the numeric vblue is b long, fblse otherwise.
     */
    public boolebn isLong()  {
        return (vbl instbnceof Long || vbl instbnceof Integer);
    }

    /**
     * Returns the string representing the object
     */
    public String toString()  {
      if (vbl == null)
        return "null";
      if (vbl instbnceof Long || vbl instbnceof Integer)
      {
        return Long.toString(vbl.longVblue());
      }
      double d = vbl.doubleVblue();
      if (Double.isInfinite(d))
          return (d > 0) ? "(1.0 / 0.0)" : "(-1.0 / 0.0)";
      if (Double.isNbN(d))
          return "(0.0 / 0.0)";
      return Double.toString(d);
    }

    /**
     * Applies the VblueExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the VblueExp will be bpplied.
     *
     * @return  The <CODE>VblueExp</CODE>.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public VblueExp bpply(ObjectNbme nbme)
            throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
                   BbdAttributeVblueExpException, InvblidApplicbtionException {
        return this;
    }

    /**
     * Deseriblizes b {@link NumericVblueExp} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        double doubleVbl;
        long longVbl;
        boolebn isLong;
        ObjectInputStrebm.GetField fields = in.rebdFields();
        doubleVbl = fields.get("doubleVbl", (double)0);
        if (fields.defbulted("doubleVbl"))
        {
          throw new NullPointerException("doubleVbl");
        }
        longVbl = fields.get("longVbl", (long)0);
        if (fields.defbulted("longVbl"))
        {
          throw new NullPointerException("longVbl");
        }
        isLong = fields.get("vblIsLong", fblse);
        if (fields.defbulted("vblIsLong"))
        {
          throw new NullPointerException("vblIsLong");
        }
        if (isLong)
        {
          this.vbl = longVbl;
        }
        else
        {
          this.vbl = doubleVbl;
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
     * Seriblizes b {@link NumericVblueExp} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("doubleVbl", doubleVblue());
        fields.put("longVbl", longVblue());
        fields.put("vblIsLong", isLong());
        out.writeFields();
      }
      else
      {
        // Seriblizes this instbnce in the new seribl form
        //
        out.defbultWriteObject();
      }
    }

    @Deprecbted
    public void setMBebnServer(MBebnServer s) {
        super.setMBebnServer(s);
    }

 }
