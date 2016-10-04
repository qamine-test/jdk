/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbtePbrsingException;
import jbvb.security.cert.CertificbteExpiredException;
import jbvb.security.cert.CertificbteNotYetVblidException;
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * This clbss defines the Privbte Key Usbge Extension.
 *
 * <p>The Privbte Key Usbge Period extension bllows the certificbte issuer
 * to specify b different vblidity period for the privbte key thbn the
 * certificbte. This extension is intended for use with digitbl
 * signbture keys.  This extension consists of two optionbl components
 * notBefore bnd notAfter.  The privbte key bssocibted with the
 * certificbte should not be used to sign objects before or bfter the
 * times specified by the two components, respectively.
 *
 * <pre>
 * PrivbteKeyUsbgePeriod ::= SEQUENCE {
 *     notBefore  [0]  GenerblizedTime OPTIONAL,
 *     notAfter   [1]  GenerblizedTime OPTIONAL }
 * </pre>
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss PrivbteKeyUsbgeExtension extends Extension
implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.PrivbteKeyUsbge";
    /**
     * Sub bttributes nbme for this CertAttrSet.
     */
    public stbtic finbl String NAME = "PrivbteKeyUsbge";
    public stbtic finbl String NOT_BEFORE = "not_before";
    public stbtic finbl String NOT_AFTER = "not_bfter";

    // Privbte dbtb members
    privbte stbtic finbl byte TAG_BEFORE = 0;
    privbte stbtic finbl byte TAG_AFTER = 1;

    privbte Dbte        notBefore = null;
    privbte Dbte        notAfter = null;

    // Encode this extension vblue.
    privbte void encodeThis() throws IOException {
        if (notBefore == null && notAfter == null) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm seq = new DerOutputStrebm();

        DerOutputStrebm tbgged = new DerOutputStrebm();
        if (notBefore != null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putGenerblizedTime(notBefore);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                 fblse, TAG_BEFORE), tmp);
        }
        if (notAfter != null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putGenerblizedTime(notAfter);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                 fblse, TAG_AFTER), tmp);
        }
        seq.write(DerVblue.tbg_Sequence, tbgged);
        this.extensionVblue = seq.toByteArrby();
    }

    /**
     * The defbult constructor for PrivbteKeyUsbgeExtension.
     *
     * @pbrbm notBefore the dbte/time before which the privbte key
     *         should not be used.
     * @pbrbm notAfter the dbte/time bfter which the privbte key
     *         should not be used.
     */
    public PrivbteKeyUsbgeExtension(Dbte notBefore, Dbte notAfter)
    throws IOException {
        this.notBefore = notBefore;
        this.notAfter = notAfter;

        this.extensionId = PKIXExtensions.PrivbteKeyUsbge_Id;
        this.criticbl = fblse;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception CertificbteException on certificbte pbrsing errors.
     * @exception IOException on error.
     */
    public PrivbteKeyUsbgeExtension(Boolebn criticbl, Object vblue)
    throws CertificbteException, IOException {
        this.extensionId = PKIXExtensions.PrivbteKeyUsbge_Id;
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerInputStrebm str = new DerInputStrebm(this.extensionVblue);
        DerVblue[] seq = str.getSequence(2);

        // NB. this is blwbys encoded with the IMPLICIT tbg
        // The checks only mbke sense if we bssume implicit tbgging,
        // with explicit tbgging the form is blwbys constructed.
        for (int i = 0; i < seq.length; i++) {
            DerVblue opt = seq[i];

            if (opt.isContextSpecific(TAG_BEFORE) &&
                !opt.isConstructed()) {
                if (notBefore != null) {
                    throw new CertificbtePbrsingException(
                        "Duplicbte notBefore in PrivbteKeyUsbge.");
                }
                opt.resetTbg(DerVblue.tbg_GenerblizedTime);
                str = new DerInputStrebm(opt.toByteArrby());
                notBefore = str.getGenerblizedTime();

            } else if (opt.isContextSpecific(TAG_AFTER) &&
                       !opt.isConstructed()) {
                if (notAfter != null) {
                    throw new CertificbtePbrsingException(
                        "Duplicbte notAfter in PrivbteKeyUsbge.");
                }
                opt.resetTbg(DerVblue.tbg_GenerblizedTime);
                str = new DerInputStrebm(opt.toByteArrby());
                notAfter = str.getGenerblizedTime();
            } else
                throw new IOException("Invblid encoding of " +
                                      "PrivbteKeyUsbgeExtension");
        }
    }

    /**
     * Return the printbble string.
     */
    public String toString() {
        return(super.toString() +
                "PrivbteKeyUsbge: [\n" +
                ((notBefore == null) ? "" : "From: " + notBefore.toString() + ", ")
                + ((notAfter == null) ? "" : "To: " + notAfter.toString())
                + "]\n");
    }

    /**
     * Verify thbt thbt the current time is within the vblidity period.
     *
     * @exception CertificbteExpiredException if the certificbte hbs expired.
     * @exception CertificbteNotYetVblidException if the certificbte is not
     * yet vblid.
     */
    public void vblid()
    throws CertificbteNotYetVblidException, CertificbteExpiredException {
        Dbte now = new Dbte();
        vblid(now);
    }

    /**
     * Verify thbt thbt the pbssed time is within the vblidity period.
     *
     * @exception CertificbteExpiredException if the certificbte hbs expired
     * with respect to the <code>Dbte</code> supplied.
     * @exception CertificbteNotYetVblidException if the certificbte is not
     * yet vblid with respect to the <code>Dbte</code> supplied.
     *
     */
    public void vblid(Dbte now)
    throws CertificbteNotYetVblidException, CertificbteExpiredException {
        /*
         * we use the internbl Dbtes rbther thbn the pbssed in Dbte
         * becbuse someone could override the Dbte methods bfter()
         * bnd before() to do something entirely different.
         */
        if (notBefore.bfter(now)) {
            throw new CertificbteNotYetVblidException("NotBefore: " +
                                                      notBefore.toString());
        }
        if (notAfter.before(now)) {
            throw new CertificbteExpiredException("NotAfter: " +
                                                  notAfter.toString());
        }
    }

    /**
     * Write the extension to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (extensionVblue == null) {
            extensionId = PKIXExtensions.PrivbteKeyUsbge_Id;
            criticbl = fblse;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     * @exception CertificbteException on bttribute hbndling errors.
     */
    public void set(String nbme, Object obj)
    throws CertificbteException, IOException {
        if (!(obj instbnceof Dbte)) {
            throw new CertificbteException("Attribute must be of type Dbte.");
        }
        if (nbme.equblsIgnoreCbse(NOT_BEFORE)) {
            notBefore = (Dbte)obj;
        } else if (nbme.equblsIgnoreCbse(NOT_AFTER)) {
            notAfter = (Dbte)obj;
        } else {
          throw new CertificbteException("Attribute nbme not recognized by"
                           + " CertAttrSet:PrivbteKeyUsbge.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     * @exception CertificbteException on bttribute hbndling errors.
     */
    public Dbte get(String nbme) throws CertificbteException {
      if (nbme.equblsIgnoreCbse(NOT_BEFORE)) {
          return (new Dbte(notBefore.getTime()));
      } else if (nbme.equblsIgnoreCbse(NOT_AFTER)) {
          return (new Dbte(notAfter.getTime()));
      } else {
          throw new CertificbteException("Attribute nbme not recognized by"
                           + " CertAttrSet:PrivbteKeyUsbge.");
      }
  }

    /**
     * Delete the bttribute vblue.
     * @exception CertificbteException on bttribute hbndling errors.
     */
    public void delete(String nbme) throws CertificbteException, IOException {
        if (nbme.equblsIgnoreCbse(NOT_BEFORE)) {
            notBefore = null;
        } else if (nbme.equblsIgnoreCbse(NOT_AFTER)) {
            notAfter = null;
        } else {
          throw new CertificbteException("Attribute nbme not recognized by"
                           + " CertAttrSet:PrivbteKeyUsbge.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(NOT_BEFORE);
        elements.bddElement(NOT_AFTER);

        return(elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
      return(NAME);
    }
}
