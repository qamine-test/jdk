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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * This clbss defines the version of the X509 Certificbte.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 */
public clbss CertificbteVersion implements CertAttrSet<String> {
    /**
     * X509Certificbte Version 1
     */
    public stbtic finbl int     V1 = 0;
    /**
     * X509Certificbte Version 2
     */
    public stbtic finbl int     V2 = 1;
    /**
     * X509Certificbte Version 3
     */
    public stbtic finbl int     V3 = 2;
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.version";
    /**
     * Sub bttributes nbme for this CertAttrSet.
     */
    public stbtic finbl String NAME = "version";
    public stbtic finbl String VERSION = "number";

    // Privbte dbtb members
    int version = V1;

    // Returns the version number.
    privbte int getVersion() {
        return(version);
    }

    // Construct the clbss from the pbssed DerVblue
    privbte void construct(DerVblue derVbl) throws IOException {
        if (derVbl.isConstructed() && derVbl.isContextSpecific()) {
            derVbl = derVbl.dbtb.getDerVblue();
            version = derVbl.getInteger();
            if (derVbl.dbtb.bvbilbble() != 0) {
                throw new IOException("X.509 version, bbd formbt");
            }
        }
    }

    /**
     * The defbult constructor for this clbss,
     *  sets the version to 0 (i.e. X.509 version 1).
     */
    public CertificbteVersion() {
        version = V1;
    }

    /**
     * The constructor for this clbss for the required version.
     *
     * @pbrbm version the version for the certificbte.
     * @exception IOException if the version is not vblid.
     */
    public CertificbteVersion(int version) throws IOException {

        // check thbt it is b vblid version
        if (version == V1 || version == V2 || version == V3)
            this.version = version;
        else {
            throw new IOException("X.509 Certificbte version " +
                                   version + " not supported.\n");
        }
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the CertificbteVersion from.
     * @exception IOException on decoding errors.
     */
    public CertificbteVersion(DerInputStrebm in) throws IOException {
        version = V1;
        DerVblue derVbl = in.getDerVblue();

        construct(derVbl);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed strebm.
     *
     * @pbrbm in the InputStrebm to rebd the CertificbteVersion from.
     * @exception IOException on decoding errors.
     */
    public CertificbteVersion(InputStrebm in) throws IOException {
        version = V1;
        DerVblue derVbl = new DerVblue(in);

        construct(derVbl);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DerVblue.
     *
     * @pbrbm vbl the Der encoded vblue.
     * @exception IOException on decoding errors.
     */
    public CertificbteVersion(DerVblue vbl) throws IOException {
        version = V1;

        construct(vbl);
    }

    /**
     * Return the version number of the certificbte.
     */
    public String toString() {
        return("Version: V" + (version+1));
    }

    /**
     * Encode the CertificbteVersion period in DER form to the strebm.
     *
     * @pbrbm out the OutputStrebm to mbrshbl the contents to.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        // Nothing for defbult
        if (version == V1) {
            return;
        }
        DerOutputStrebm tmp = new DerOutputStrebm();
        tmp.putInteger(version);

        DerOutputStrebm seq = new DerOutputStrebm();
        seq.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0),
                  tmp);

        out.write(seq.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Integer)) {
            throw new IOException("Attribute must be of type Integer.");
        }
        if (nbme.equblsIgnoreCbse(VERSION)) {
            version = ((Integer)obj).intVblue();
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet: CertificbteVersion.");
        }
    }

    /**
     * Get the bttribute vblue.
     */
    public Integer get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(VERSION)) {
            return(getVersion());
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet: CertificbteVersion.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(VERSION)) {
            version = V1;
        } else {
            throw new IOException("Attribute nbme not recognized by " +
                                  "CertAttrSet: CertificbteVersion.");
        }
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(VERSION);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return(NAME);
    }

    /**
     * Compbre versions.
     */
    public int compbre(int vers) {
        return(version - vers);
    }
}
