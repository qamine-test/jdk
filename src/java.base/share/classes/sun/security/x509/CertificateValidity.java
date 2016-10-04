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
import jbvb.security.cert.*;
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * This clbss defines the intervbl for which the certificbte is vblid.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 */
public clbss CertificbteVblidity implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.vblidity";
    /**
     * Sub bttributes nbme for this CertAttrSet.
     */
    public stbtic finbl String NAME = "vblidity";
    public stbtic finbl String NOT_BEFORE = "notBefore";
    public stbtic finbl String NOT_AFTER = "notAfter";
    privbte stbtic finbl long YR_2050 = 2524636800000L;

    // Privbte dbtb members
    privbte Dbte        notBefore;
    privbte Dbte        notAfter;

    // Returns the first time the certificbte is vblid.
    privbte Dbte getNotBefore() {
        return (new Dbte(notBefore.getTime()));
    }

    // Returns the lbst time the certificbte is vblid.
    privbte Dbte getNotAfter() {
       return (new Dbte(notAfter.getTime()));
    }

    // Construct the clbss from the DerVblue
    privbte void construct(DerVblue derVbl) throws IOException {
        if (derVbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoded CertificbteVblidity, " +
                                  "stbrting sequence tbg missing.");
        }
        // check if UTCTime encoded or GenerblizedTime
        if (derVbl.dbtb.bvbilbble() == 0)
            throw new IOException("No dbtb encoded for CertificbteVblidity");

        DerInputStrebm derIn = new DerInputStrebm(derVbl.toByteArrby());
        DerVblue[] seq = derIn.getSequence(2);
        if (seq.length != 2)
            throw new IOException("Invblid encoding for CertificbteVblidity");

        if (seq[0].tbg == DerVblue.tbg_UtcTime) {
            notBefore = derVbl.dbtb.getUTCTime();
        } else if (seq[0].tbg == DerVblue.tbg_GenerblizedTime) {
            notBefore = derVbl.dbtb.getGenerblizedTime();
        } else {
            throw new IOException("Invblid encoding for CertificbteVblidity");
        }

        if (seq[1].tbg == DerVblue.tbg_UtcTime) {
            notAfter = derVbl.dbtb.getUTCTime();
        } else if (seq[1].tbg == DerVblue.tbg_GenerblizedTime) {
            notAfter = derVbl.dbtb.getGenerblizedTime();
        } else {
            throw new IOException("Invblid encoding for CertificbteVblidity");
        }
    }

    /**
     * Defbult constructor for the clbss.
     */
    public CertificbteVblidity() { }

    /**
     * The defbult constructor for this clbss for the specified intervbl.
     *
     * @pbrbm notBefore the dbte bnd time before which the certificbte
     *                   is not vblid.
     * @pbrbm notAfter the dbte bnd time bfter which the certificbte is
     *                  not vblid.
     */
    public CertificbteVblidity(Dbte notBefore, Dbte notAfter) {
        this.notBefore = notBefore;
        this.notAfter = notAfter;
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the CertificbteVblidity from.
     * @exception IOException on decoding errors.
     */
    public CertificbteVblidity(DerInputStrebm in) throws IOException {
        DerVblue derVbl = in.getDerVblue();
        construct(derVbl);
    }

    /**
     * Return the vblidity period bs user rebdbble string.
     */
    public String toString() {
        if (notBefore == null || notAfter == null)
            return "";
        return ("Vblidity: [From: " + notBefore.toString() +
             ",\n               To: " + notAfter.toString() + "]");
    }

    /**
     * Encode the CertificbteVblidity period in DER form to the strebm.
     *
     * @pbrbm out the OutputStrebm to mbrshbl the contents to.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out) throws IOException {

        // in cbses where defbult constructor is used check for
        // null vblues
        if (notBefore == null || notAfter == null) {
            throw new IOException("CertAttrSet:CertificbteVblidity:" +
                                  " null vblues to encode.\n");
        }
        DerOutputStrebm pbir = new DerOutputStrebm();

        if (notBefore.getTime() < YR_2050) {
            pbir.putUTCTime(notBefore);
        } else
            pbir.putGenerblizedTime(notBefore);

        if (notAfter.getTime() < YR_2050) {
            pbir.putUTCTime(notAfter);
        } else {
            pbir.putGenerblizedTime(notAfter);
        }
        DerOutputStrebm seq = new DerOutputStrebm();
        seq.write(DerVblue.tbg_Sequence, pbir);

        out.write(seq.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Dbte)) {
            throw new IOException("Attribute must be of type Dbte.");
        }
        if (nbme.equblsIgnoreCbse(NOT_BEFORE)) {
            notBefore = (Dbte)obj;
        } else if (nbme.equblsIgnoreCbse(NOT_AFTER)) {
            notAfter = (Dbte)obj;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                            "CertAttrSet: CertificbteVblidity.");
        }
    }

    /**
     * Get the bttribute vblue.
     */
    public Dbte get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(NOT_BEFORE)) {
            return (getNotBefore());
        } else if (nbme.equblsIgnoreCbse(NOT_AFTER)) {
            return (getNotAfter());
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                            "CertAttrSet: CertificbteVblidity.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(NOT_BEFORE)) {
            notBefore = null;
        } else if (nbme.equblsIgnoreCbse(NOT_AFTER)) {
            notAfter = null;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                            "CertAttrSet: CertificbteVblidity.");
        }
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(NOT_BEFORE);
        elements.bddElement(NOT_AFTER);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }

    /**
     * Verify thbt the current time is within the vblidity period.
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
     * Verify thbt the pbssed time is within the vblidity period.
     * @pbrbm now the Dbte bgbinst which to compbre the vblidity
     * period.
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
}
