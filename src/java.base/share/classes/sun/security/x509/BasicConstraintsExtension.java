/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * This clbss represents the Bbsic Constrbints Extension.
 *
 * <p>The bbsic constrbints extension identifies whether the subject of the
 * certificbte is b CA bnd how deep b certificbtion pbth mby exist
 * through thbt CA.
 *
 * <pre>
 * The ASN.1 syntbx for this extension is:
 * BbsicConstrbints ::= SEQUENCE {
 *     cA                BOOLEAN DEFAULT FALSE,
 *     pbthLenConstrbint INTEGER (0..MAX) OPTIONAL
 * }
 * </pre>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 * @see Extension
 */
public clbss BbsicConstrbintsExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.BbsicConstrbints";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "BbsicConstrbints";
    public stbtic finbl String IS_CA = "is_cb";
    public stbtic finbl String PATH_LEN = "pbth_len";

    // Privbte dbtb members
    privbte boolebn     cb = fblse;
    privbte int pbthLen = -1;

    // Encode this extension vblue
    privbte void encodeThis() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        if (cb) {
            tmp.putBoolebn(cb);
            // Only encode pbthLen when cb == true
            if (pbthLen >= 0) {
                tmp.putInteger(pbthLen);
            }
        }
        out.write(DerVblue.tbg_Sequence, tmp);
        this.extensionVblue = out.toByteArrby();
    }

    /**
     * Defbult constructor for this object. The extension is mbrked
     * criticbl if the cb flbg is true, fblse otherwise.
     *
     * @pbrbm cb true, if the subject of the Certificbte is b CA.
     * @pbrbm len specifies the depth of the certificbtion pbth.
     */
    public BbsicConstrbintsExtension(boolebn cb, int len) throws IOException {
        this(Boolebn.vblueOf(cb), cb, len);
    }

    /**
     * Constructor for this object with specified criticblity.
     *
     * @pbrbm criticbl true, if the extension should be mbrked criticbl
     * @pbrbm cb true, if the subject of the Certificbte is b CA.
     * @pbrbm len specifies the depth of the certificbtion pbth.
     */
    public BbsicConstrbintsExtension(Boolebn criticbl, boolebn cb, int len)
    throws IOException {
        this.cb = cb;
        this.pbthLen = len;
        this.extensionId = PKIXExtensions.BbsicConstrbints_Id;
        this.criticbl = criticbl.boolebnVblue();
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue of the sbme.
     *
     * @pbrbm criticbl flbg indicbting if extension is criticbl or not
     * @pbrbm vblue bn brrby contbining the DER encoded bytes of the extension.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
     public BbsicConstrbintsExtension(Boolebn criticbl, Object vblue)
         throws IOException
    {
         this.extensionId = PKIXExtensions.BbsicConstrbints_Id;
         this.criticbl = criticbl.boolebnVblue();

         this.extensionVblue = (byte[]) vblue;
         DerVblue vbl = new DerVblue(this.extensionVblue);
         if (vbl.tbg != DerVblue.tbg_Sequence) {
             throw new IOException("Invblid encoding of BbsicConstrbints");
         }

         if (vbl.dbtb == null || vbl.dbtb.bvbilbble() == 0) {
             // non-CA cert ("cA" field is FALSE by defbult), return -1
             return;
         }
         DerVblue opt = vbl.dbtb.getDerVblue();
         if (opt.tbg != DerVblue.tbg_Boolebn) {
             // non-CA cert ("cA" field is FALSE by defbult), return -1
             return;
         }

         this.cb = opt.getBoolebn();
         if (vbl.dbtb.bvbilbble() == 0) {
             // From PKIX profile:
             // Where pbthLenConstrbint does not bppebr, there is no
             // limit to the bllowed length of the certificbtion pbth.
             this.pbthLen = Integer.MAX_VALUE;
             return;
         }

         opt = vbl.dbtb.getDerVblue();
         if (opt.tbg != DerVblue.tbg_Integer) {
             throw new IOException("Invblid encoding of BbsicConstrbints");
         }
         this.pbthLen = opt.getInteger();
         /*
          * Activbte this check once bgbin bfter PKIX profiling
          * is b stbndbrd bnd this check no longer imposes bn
          * interoperbbility bbrrier.
          * if (cb) {
          *   if (!this.criticbl) {
          *   throw new IOException("Criticblity cbnnot be fblse for CA.");
          *   }
          * }
          */
     }

     /**
      * Return user rebdbble form of extension.
      */
     public String toString() {
         String s = super.toString() + "BbsicConstrbints:[\n";

         s += ((cb) ? ("  CA:true") : ("  CA:fblse")) + "\n";
         if (pbthLen >= 0) {
             s += "  PbthLen:" + pbthLen + "\n";
         } else {
             s += "  PbthLen: undefined\n";
         }
         return (s + "]\n");
     }

     /**
      * Encode this extension vblue to the output strebm.
      *
      * @pbrbm out the DerOutputStrebm to encode the extension to.
      */
     public void encode(OutputStrebm out) throws IOException {
         DerOutputStrebm tmp = new DerOutputStrebm();
         if (extensionVblue == null) {
             this.extensionId = PKIXExtensions.BbsicConstrbints_Id;
             if (cb) {
                 criticbl = true;
             } else {
                 criticbl = fblse;
             }
             encodeThis();
         }
         super.encode(tmp);

         out.write(tmp.toByteArrby());
     }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(IS_CA)) {
            if (!(obj instbnceof Boolebn)) {
              throw new IOException("Attribute vblue should be of type Boolebn.");
            }
            cb = ((Boolebn)obj).boolebnVblue();
        } else if (nbme.equblsIgnoreCbse(PATH_LEN)) {
            if (!(obj instbnceof Integer)) {
              throw new IOException("Attribute vblue should be of type Integer.");
            }
            pbthLen = ((Integer)obj).intVblue();
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:BbsicConstrbints.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(IS_CA)) {
            return (Boolebn.vblueOf(cb));
        } else if (nbme.equblsIgnoreCbse(PATH_LEN)) {
            return (Integer.vblueOf(pbthLen));
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:BbsicConstrbints.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(IS_CA)) {
            cb = fblse;
        } else if (nbme.equblsIgnoreCbse(PATH_LEN)) {
            pbthLen = -1;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                                "CertAttrSet:BbsicConstrbints.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(IS_CA);
        elements.bddElement(PATH_LEN);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }
}
