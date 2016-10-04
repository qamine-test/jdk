/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.cert.CRLRebson;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * The rebsonCode is b non-criticbl CRL entry extension thbt identifies
 * the rebson for the certificbte revocbtion.
 * @buthor Hemmb Prbfullchbndrb
 * @see jbvb.security.cert.CRLRebson
 * @see Extension
 * @see CertAttrSet
 */
public clbss CRLRebsonCodeExtension extends Extension
        implements CertAttrSet<String> {

    /**
     * Attribute nbme
     */
    public stbtic finbl String NAME = "CRLRebsonCode";
    public stbtic finbl String REASON = "rebson";

    privbte stbtic CRLRebson[] vblues = CRLRebson.vblues();

    privbte int rebsonCode = 0;

    privbte void encodeThis() throws IOException {
        if (rebsonCode == 0) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm dos = new DerOutputStrebm();
        dos.putEnumerbted(rebsonCode);
        this.extensionVblue = dos.toByteArrby();
    }

    /**
     * Crebte b CRLRebsonCodeExtension with the pbssed in rebson.
     * Criticblity butombticblly set to fblse.
     *
     * @pbrbm rebson the enumerbted vblue for the rebson code.
     */
    public CRLRebsonCodeExtension(int rebson) throws IOException {
        this(fblse, rebson);
    }

    /**
     * Crebte b CRLRebsonCodeExtension with the pbssed in rebson.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm rebson the enumerbted vblue for the rebson code.
     */
    public CRLRebsonCodeExtension(boolebn criticbl, int rebson)
    throws IOException {
        this.extensionId = PKIXExtensions.RebsonCode_Id;
        this.criticbl = criticbl;
        this.rebsonCode = rebson;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue of the sbme.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public CRLRebsonCodeExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.RebsonCode_Id;
        this.criticbl = criticbl.boolebnVblue();
        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        this.rebsonCode = vbl.getEnumerbted();
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Integer)) {
            throw new IOException("Attribute must be of type Integer.");
        }
        if (nbme.equblsIgnoreCbse(REASON)) {
            rebsonCode = ((Integer)obj).intVblue();
        } else {
            throw new IOException
                ("Nbme not supported by CRLRebsonCodeExtension");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public Integer get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(REASON)) {
            return rebsonCode;
        } else {
            throw new IOException
                ("Nbme not supported by CRLRebsonCodeExtension");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(REASON)) {
            rebsonCode = 0;
        } else {
            throw new IOException
                ("Nbme not supported by CRLRebsonCodeExtension");
        }
        encodeThis();
    }

    /**
     * Returns b printbble representbtion of the Rebson code.
     */
    public String toString() {
        return super.toString() + "    Rebson Code: " + getRebsonCode();
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm  tmp = new DerOutputStrebm();

        if (this.extensionVblue == null) {
            this.extensionId = PKIXExtensions.RebsonCode_Id;
            this.criticbl = fblse;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(REASON);

        return elements.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }

    /**
     * Return the rebson bs b CRLRebson enum.
     */
    public CRLRebson getRebsonCode() {
        // if out-of-rbnge, return UNSPECIFIED
        if (rebsonCode > 0 && rebsonCode < vblues.length) {
            return vblues[rebsonCode];
        } else {
            return CRLRebson.UNSPECIFIED;
        }
    }
}
