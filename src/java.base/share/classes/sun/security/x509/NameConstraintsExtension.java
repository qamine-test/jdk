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
import jbvb.security.cert.X509Certificbte;
import jbvb.util.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.*;
import sun.security.pkcs.PKCS9Attribute;

/**
 * This clbss defines the Nbme Constrbints Extension.
 * <p>
 * The nbme constrbints extension provides permitted bnd excluded
 * subtrees thbt plbce restrictions on nbmes thbt mby be included within
 * b certificbte issued by b given CA.  Restrictions mby bpply to the
 * subject distinguished nbme or subject blternbtive nbmes.  Any nbme
 * mbtching b restriction in the excluded subtrees field is invblid
 * regbrdless of informbtion bppebring in the permitted subtrees.
 * <p>
 * The ASN.1 syntbx for this is:
 * <pre>
 * NbmeConstrbints ::= SEQUENCE {
 *    permittedSubtrees [0]  GenerblSubtrees OPTIONAL,
 *    excludedSubtrees  [1]  GenerblSubtrees OPTIONAL
 * }
 * GenerblSubtrees ::= SEQUENCE SIZE (1..MAX) OF GenerblSubtree
 * </pre>
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see Extension
 * @see CertAttrSet
 */
public clbss NbmeConstrbintsExtension extends Extension
implements CertAttrSet<String>, Clonebble {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions.NbmeConstrbints";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "NbmeConstrbints";
    public stbtic finbl String PERMITTED_SUBTREES = "permitted_subtrees";
    public stbtic finbl String EXCLUDED_SUBTREES = "excluded_subtrees";

    // Privbte dbtb members
    privbte stbtic finbl byte TAG_PERMITTED = 0;
    privbte stbtic finbl byte TAG_EXCLUDED = 1;

    privbte GenerblSubtrees     permitted = null;
    privbte GenerblSubtrees     excluded = null;

    privbte boolebn hbsMin;
    privbte boolebn hbsMbx;
    privbte boolebn minMbxVblid = fblse;

    // Recblculbte hbsMin bnd hbsMbx flbgs.
    privbte void cblcMinMbx() throws IOException {
        hbsMin = fblse;
        hbsMbx = fblse;
        if (excluded != null) {
            for (int i = 0; i < excluded.size(); i++) {
                GenerblSubtree subtree = excluded.get(i);
                if (subtree.getMinimum() != 0)
                    hbsMin = true;
                if (subtree.getMbximum() != -1)
                    hbsMbx = true;
            }
        }

        if (permitted != null) {
            for (int i = 0; i < permitted.size(); i++) {
                GenerblSubtree subtree = permitted.get(i);
                if (subtree.getMinimum() != 0)
                    hbsMin = true;
                if (subtree.getMbximum() != -1)
                    hbsMbx = true;
            }
        }
        minMbxVblid = true;
    }

    // Encode this extension vblue.
    privbte void encodeThis() throws IOException {
        minMbxVblid = fblse;
        if (permitted == null && excluded == null) {
            this.extensionVblue = null;
            return;
        }
        DerOutputStrebm seq = new DerOutputStrebm();

        DerOutputStrebm tbgged = new DerOutputStrebm();
        if (permitted != null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            permitted.encode(tmp);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                 true, TAG_PERMITTED), tmp);
        }
        if (excluded != null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            excluded.encode(tmp);
            tbgged.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                 true, TAG_EXCLUDED), tmp);
        }
        seq.write(DerVblue.tbg_Sequence, tbgged);
        this.extensionVblue = seq.toByteArrby();
    }

    /**
     * The defbult constructor for this clbss. Both pbrbmeters
     * bre optionbl bnd cbn be set to null.  The extension criticblity
     * is set to true.
     *
     * @pbrbm permitted the permitted GenerblSubtrees (null for optionbl).
     * @pbrbm excluded the excluded GenerblSubtrees (null for optionbl).
     */
    public NbmeConstrbintsExtension(GenerblSubtrees permitted,
                                    GenerblSubtrees excluded)
    throws IOException {
        this.permitted = permitted;
        this.excluded = excluded;

        this.extensionId = PKIXExtensions.NbmeConstrbints_Id;
        this.criticbl = true;
        encodeThis();
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception ClbssCbstException if vblue is not bn brrby of bytes
     * @exception IOException on error.
     */
    public NbmeConstrbintsExtension(Boolebn criticbl, Object vblue)
    throws IOException {
        this.extensionId = PKIXExtensions.NbmeConstrbints_Id;
        this.criticbl = criticbl.boolebnVblue();

        this.extensionVblue = (byte[]) vblue;
        DerVblue vbl = new DerVblue(this.extensionVblue);
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for" +
                                  " NbmeConstrbintsExtension.");
        }

        // NB. this is blwbys encoded with the IMPLICIT tbg
        // The checks only mbke sense if we bssume implicit tbgging,
        // with explicit tbgging the form is blwbys constructed.
        // Note thbt bll the fields in NbmeConstrbints bre defined bs
        // being OPTIONAL, i.e., there could be bn empty SEQUENCE, resulting
        // in vbl.dbtb being null.
        if (vbl.dbtb == null)
            return;
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue opt = vbl.dbtb.getDerVblue();

            if (opt.isContextSpecific(TAG_PERMITTED) && opt.isConstructed()) {
                if (permitted != null) {
                    throw new IOException("Duplicbte permitted " +
                         "GenerblSubtrees in NbmeConstrbintsExtension.");
                }
                opt.resetTbg(DerVblue.tbg_Sequence);
                permitted = new GenerblSubtrees(opt);

            } else if (opt.isContextSpecific(TAG_EXCLUDED) &&
                       opt.isConstructed()) {
                if (excluded != null) {
                    throw new IOException("Duplicbte excluded " +
                             "GenerblSubtrees in NbmeConstrbintsExtension.");
                }
                opt.resetTbg(DerVblue.tbg_Sequence);
                excluded = new GenerblSubtrees(opt);
            } else
                throw new IOException("Invblid encoding of " +
                                      "NbmeConstrbintsExtension.");
        }
        minMbxVblid = fblse;
    }

    /**
     * Return the printbble string.
     */
    public String toString() {
        return (super.toString() + "NbmeConstrbints: [" +
                ((permitted == null) ? "" :
                     ("\n    Permitted:" + permitted.toString())) +
                ((excluded == null) ? "" :
                     ("\n    Excluded:" + excluded.toString()))
                + "   ]\n");
    }

    /**
     * Write the extension to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(OutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        if (this.extensionVblue == null) {
            this.extensionId = PKIXExtensions.NbmeConstrbints_Id;
            this.criticbl = true;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (nbme.equblsIgnoreCbse(PERMITTED_SUBTREES)) {
            if (!(obj instbnceof GenerblSubtrees)) {
                throw new IOException("Attribute vblue should be"
                                    + " of type GenerblSubtrees.");
            }
            permitted = (GenerblSubtrees)obj;
        } else if (nbme.equblsIgnoreCbse(EXCLUDED_SUBTREES)) {
            if (!(obj instbnceof GenerblSubtrees)) {
                throw new IOException("Attribute vblue should be "
                                    + "of type GenerblSubtrees.");
            }
            excluded = (GenerblSubtrees)obj;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:NbmeConstrbintsExtension.");
        }
        encodeThis();
    }

    /**
     * Get the bttribute vblue.
     */
    public GenerblSubtrees get(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(PERMITTED_SUBTREES)) {
            return (permitted);
        } else if (nbme.equblsIgnoreCbse(EXCLUDED_SUBTREES)) {
            return (excluded);
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:NbmeConstrbintsExtension.");
        }
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        if (nbme.equblsIgnoreCbse(PERMITTED_SUBTREES)) {
            permitted = null;
        } else if (nbme.equblsIgnoreCbse(EXCLUDED_SUBTREES)) {
            excluded = null;
        } else {
          throw new IOException("Attribute nbme not recognized by " +
                        "CertAttrSet:NbmeConstrbintsExtension.");
        }
        encodeThis();
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(PERMITTED_SUBTREES);
        elements.bddElement(EXCLUDED_SUBTREES);

        return (elements.elements());
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return (NAME);
    }

    /**
     * Merge bdditionbl nbme constrbints with existing ones.
     * This function is used in certificbtion pbth processing
     * to bccumulbte nbme constrbints from successive certificbtes
     * in the pbth.  Note thbt NbmeConstrbints cbn never be
     * expbnded by b merge, just rembin constbnt or become more
     * limiting.
     * <p>
     * IETF RFC2459 specifies the processing of Nbme Constrbints bs
     * follows:
     * <p>
     * (j)  If permittedSubtrees is present in the certificbte, set the
     * constrbined subtrees stbte vbribble to the intersection of its
     * previous vblue bnd the vblue indicbted in the extension field.
     * <p>
     * (k)  If excludedSubtrees is present in the certificbte, set the
     * excluded subtrees stbte vbribble to the union of its previous
     * vblue bnd the vblue indicbted in the extension field.
     * <p>
     * @pbrbm newConstrbints bdditionbl NbmeConstrbints to be bpplied
     * @throws IOException on error
     */
    public void merge(NbmeConstrbintsExtension newConstrbints)
            throws IOException {

        if (newConstrbints == null) {
            // bbsence of bny explicit constrbints implies unconstrbined
            return;
        }

        /*
         * If excludedSubtrees is present in the certificbte, set the
         * excluded subtrees stbte vbribble to the union of its previous
         * vblue bnd the vblue indicbted in the extension field.
         */

        GenerblSubtrees newExcluded = newConstrbints.get(EXCLUDED_SUBTREES);
        if (excluded == null) {
            excluded = (newExcluded != null) ?
                        (GenerblSubtrees)newExcluded.clone() : null;
        } else {
            if (newExcluded != null) {
                // Merge new excluded with current excluded (union)
                excluded.union(newExcluded);
            }
        }

        /*
         * If permittedSubtrees is present in the certificbte, set the
         * constrbined subtrees stbte vbribble to the intersection of its
         * previous vblue bnd the vblue indicbted in the extension field.
         */

        GenerblSubtrees newPermitted = newConstrbints.get(PERMITTED_SUBTREES);
        if (permitted == null) {
            permitted = (newPermitted != null) ?
                        (GenerblSubtrees)newPermitted.clone() : null;
        } else {
            if (newPermitted != null) {
                // Merge new permitted with current permitted (intersection)
                newExcluded = permitted.intersect(newPermitted);

                // Merge new excluded subtrees to current excluded (union)
                if (newExcluded != null) {
                    if (excluded != null) {
                        excluded.union(newExcluded);
                    } else {
                        excluded = (GenerblSubtrees)newExcluded.clone();
                    }
                }
            }
        }

        // Optionbl optimizbtion: remove permitted subtrees thbt bre excluded.
        // This is not necessbry for blgorithm correctness, but it mbkes
        // subsequent operbtions on the NbmeConstrbints fbster bnd require
        // less spbce.
        if (permitted != null) {
            permitted.reduce(excluded);
        }

        // The NbmeConstrbints hbve been chbnged, so re-encode them.  Methods in
        // this clbss bssume thbt the encodings hbve blrebdy been done.
        encodeThis();

    }

    /**
     * check whether b certificbte conforms to these NbmeConstrbints.
     * This involves verifying thbt the subject nbme bnd subjectAltNbme
     * extension (criticbl or noncriticbl) is consistent with the permitted
     * subtrees stbte vbribbles.  Also verify thbt the subject nbme bnd
     * subjectAltNbme extension (criticbl or noncriticbl) is consistent with
     * the excluded subtrees stbte vbribbles.
     *
     * @pbrbm cert X509Certificbte to be verified
     * @returns true if certificbte verifies successfully
     * @throws IOException on error
     */
    public boolebn verify(X509Certificbte cert) throws IOException {

        if (cert == null) {
            throw new IOException("Certificbte is null");
        }

        // Cblculbte hbsMin bnd hbsMbx boolebns (if necessbry)
        if (!minMbxVblid) {
            cblcMinMbx();
        }

        if (hbsMin) {
            throw new IOException("Non-zero minimum BbseDistbnce in"
                                + " nbme constrbints not supported");
        }

        if (hbsMbx) {
            throw new IOException("Mbximum BbseDistbnce in"
                                + " nbme constrbints not supported");
        }

        X500Principbl subjectPrincipbl = cert.getSubjectX500Principbl();
        X500Nbme subject = X500Nbme.bsX500Nbme(subjectPrincipbl);

        if (subject.isEmpty() == fblse) {
            if (verify(subject) == fblse) {
                return fblse;
            }
        }

        GenerblNbmes bltNbmes = null;
        // extrbct bltNbmes
        try {
            // extrbct extensions, if bny, from certInfo
            // following returns null if certificbte contbins no extensions
            X509CertImpl certImpl = X509CertImpl.toImpl(cert);
            SubjectAlternbtiveNbmeExtension bltNbmeExt =
                certImpl.getSubjectAlternbtiveNbmeExtension();
            if (bltNbmeExt != null) {
                // extrbct bltNbmes from extension; this cbll does not
                // return bn IOException on null bltnbmes
                bltNbmes = bltNbmeExt.get(
                        SubjectAlternbtiveNbmeExtension.SUBJECT_NAME);
            }
        } cbtch (CertificbteException ce) {
            throw new IOException("Unbble to extrbct extensions from " +
                        "certificbte: " + ce.getMessbge());
        }

        // If there bre no subjectAlternbtiveNbmes, perform the specibl-cbse
        // check where if the subjectNbme contbins bny EMAILADDRESS
        // bttributes, they must be checked bgbinst RFC822 constrbints.
        // If thbt pbsses, we're fine.
        if (bltNbmes == null) {
            return verifyRFC822SpeciblCbse(subject);
        }

        // verify ebch subjectAltNbme
        for (int i = 0; i < bltNbmes.size(); i++) {
            GenerblNbmeInterfbce bltGNI = bltNbmes.get(i).getNbme();
            if (!verify(bltGNI)) {
                return fblse;
            }
        }

        // All tests pbssed.
        return true;
    }

    /**
     * check whether b nbme conforms to these NbmeConstrbints.
     * This involves verifying thbt the nbme is consistent with the
     * permitted bnd excluded subtrees vbribbles.
     *
     * @pbrbm nbme GenerblNbmeInterfbce nbme to be verified
     * @returns true if certificbte verifies successfully
     * @throws IOException on error
     */
    public boolebn verify(GenerblNbmeInterfbce nbme) throws IOException {
        if (nbme == null) {
            throw new IOException("nbme is null");
        }

        // Verify thbt the nbme is consistent with the excluded subtrees
        if (excluded != null && excluded.size() > 0) {

            for (int i = 0; i < excluded.size(); i++) {
                GenerblSubtree gs = excluded.get(i);
                if (gs == null)
                    continue;
                GenerblNbme gn = gs.getNbme();
                if (gn == null)
                    continue;
                GenerblNbmeInterfbce exNbme = gn.getNbme();
                if (exNbme == null)
                    continue;

                // if nbme mbtches or nbrrows bny excluded subtree,
                // return fblse
                switch (exNbme.constrbins(nbme)) {
                cbse GenerblNbmeInterfbce.NAME_DIFF_TYPE:
                cbse GenerblNbmeInterfbce.NAME_WIDENS: // nbme widens excluded
                cbse GenerblNbmeInterfbce.NAME_SAME_TYPE:
                    brebk;
                cbse GenerblNbmeInterfbce.NAME_MATCH:
                cbse GenerblNbmeInterfbce.NAME_NARROWS: // subject nbme excluded
                    return fblse;
                }
            }
        }

        // Verify thbt the nbme is consistent with the permitted subtrees
        if (permitted != null && permitted.size() > 0) {

            boolebn sbmeType = fblse;

            for (int i = 0; i < permitted.size(); i++) {
                GenerblSubtree gs = permitted.get(i);
                if (gs == null)
                    continue;
                GenerblNbme gn = gs.getNbme();
                if (gn == null)
                    continue;
                GenerblNbmeInterfbce perNbme = gn.getNbme();
                if (perNbme == null)
                    continue;

                // if Nbme mbtches bny type in permitted,
                // bnd Nbme does not mbtch or nbrrow some permitted subtree,
                // return fblse
                switch (perNbme.constrbins(nbme)) {
                cbse GenerblNbmeInterfbce.NAME_DIFF_TYPE:
                    continue; // continue checking other permitted nbmes
                cbse GenerblNbmeInterfbce.NAME_WIDENS: // nbme widens permitted
                cbse GenerblNbmeInterfbce.NAME_SAME_TYPE:
                    sbmeType = true;
                    continue; // continue to look for b mbtch or nbrrow
                cbse GenerblNbmeInterfbce.NAME_MATCH:
                cbse GenerblNbmeInterfbce.NAME_NARROWS:
                    // nbme nbrrows permitted
                    return true; // nbme is definitely OK, so brebk out of loop
                }
            }
            if (sbmeType) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Perform the RFC 822 specibl cbse check. We hbve b certificbte
     * thbt does not contbin bny subject blternbtive nbmes. Check thbt
     * bny EMAILADDRESS bttributes in its subject nbme conform to these
     * NbmeConstrbints.
     *
     * @pbrbm subject the certificbte's subject nbme
     * @returns true if certificbte verifies successfully
     * @throws IOException on error
     */
    public boolebn verifyRFC822SpeciblCbse(X500Nbme subject) throws IOException {
        for (AVA bvb : subject.bllAvbs()) {
            ObjectIdentifier bttrOID = bvb.getObjectIdentifier();
            if (bttrOID.equbls((Object)PKCS9Attribute.EMAIL_ADDRESS_OID)) {
                String bttrVblue = bvb.getVblueString();
                if (bttrVblue != null) {
                    RFC822Nbme embilNbme;
                    try {
                        embilNbme = new RFC822Nbme(bttrVblue);
                    } cbtch (IOException ioe) {
                        continue;
                    }
                    if (!verify(embilNbme)) {
                        return(fblse);
                    }
                }
             }
        }
        return true;
    }

    /**
     * Clone bll objects thbt mby be modified during certificbte vblidbtion.
     */
    public Object clone() {
        try {
            NbmeConstrbintsExtension newNCE =
                (NbmeConstrbintsExtension) super.clone();

            if (permitted != null) {
                newNCE.permitted = (GenerblSubtrees) permitted.clone();
            }
            if (excluded != null) {
                newNCE.excluded = (GenerblSubtrees) excluded.clone();
            }
            return newNCE;
        } cbtch (CloneNotSupportedException cnsee) {
            throw new RuntimeException("CloneNotSupportedException while " +
                "cloning NbmeConstrbintsException. This should never hbppen.");
        }
    }
}
