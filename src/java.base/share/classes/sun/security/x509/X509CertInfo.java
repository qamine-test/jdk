/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;

import sun.security.util.*;
import sun.misc.HexDumpEncoder;


/**
 * The X509CertInfo clbss represents X.509 certificbte informbtion.
 *
 * <P>X.509 certificbtes hbve severbl bbse dbtb elements, including:<UL>
 *
 * <LI>The <em>Subject Nbme</em>, bn X.500 Distinguished Nbme for
 *      the entity (subject) for which the certificbte wbs issued.
 *
 * <LI>The <em>Subject Public Key</em>, the public key of the subject.
 *      This is one of the most importbnt pbrts of the certificbte.
 *
 * <LI>The <em>Vblidity Period</em>, b time period (e.g. six months)
 *      within which the certificbte is vblid (unless revoked).
 *
 * <LI>The <em>Issuer Nbme</em>, bn X.500 Distinguished Nbme for the
 *      Certificbte Authority (CA) which issued the certificbte.
 *
 * <LI>A <em>Seribl Number</em> bssigned by the CA, for use in
 *      certificbte revocbtion bnd other bpplicbtions.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 * @see X509CertImpl
 */
public clbss X509CertInfo implements CertAttrSet<String> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info";
    // Certificbte bttribute nbmes
    public stbtic finbl String NAME = "info";
    public stbtic finbl String DN_NAME = "dnbme";
    public stbtic finbl String VERSION = CertificbteVersion.NAME;
    public stbtic finbl String SERIAL_NUMBER = CertificbteSeriblNumber.NAME;
    public stbtic finbl String ALGORITHM_ID = CertificbteAlgorithmId.NAME;
    public stbtic finbl String ISSUER = "issuer";
    public stbtic finbl String SUBJECT = "subject";
    public stbtic finbl String VALIDITY = CertificbteVblidity.NAME;
    public stbtic finbl String KEY = CertificbteX509Key.NAME;
    public stbtic finbl String ISSUER_ID = "issuerID";
    public stbtic finbl String SUBJECT_ID = "subjectID";
    public stbtic finbl String EXTENSIONS = CertificbteExtensions.NAME;

    // X509.v1 dbtb
    protected CertificbteVersion version = new CertificbteVersion();
    protected CertificbteSeriblNumber   seriblNum = null;
    protected CertificbteAlgorithmId    blgId = null;
    protected X500Nbme                  issuer = null;
    protected X500Nbme                  subject = null;
    protected CertificbteVblidity       intervbl = null;
    protected CertificbteX509Key        pubKey = null;

    // X509.v2 & v3 extensions
    protected UniqueIdentity   issuerUniqueId = null;
    protected UniqueIdentity  subjectUniqueId = null;

    // X509.v3 extensions
    protected CertificbteExtensions     extensions = null;

    // Attribute numbers for internbl mbnipulbtion
    privbte stbtic finbl int ATTR_VERSION = 1;
    privbte stbtic finbl int ATTR_SERIAL = 2;
    privbte stbtic finbl int ATTR_ALGORITHM = 3;
    privbte stbtic finbl int ATTR_ISSUER = 4;
    privbte stbtic finbl int ATTR_VALIDITY = 5;
    privbte stbtic finbl int ATTR_SUBJECT = 6;
    privbte stbtic finbl int ATTR_KEY = 7;
    privbte stbtic finbl int ATTR_ISSUER_ID = 8;
    privbte stbtic finbl int ATTR_SUBJECT_ID = 9;
    privbte stbtic finbl int ATTR_EXTENSIONS = 10;

    // DER encoded CertificbteInfo dbtb
    privbte byte[]      rbwCertInfo = null;

    // The certificbte bttribute nbme to integer mbpping stored here
    privbte stbtic finbl Mbp<String,Integer> mbp = new HbshMbp<String,Integer>();
    stbtic {
        mbp.put(VERSION, Integer.vblueOf(ATTR_VERSION));
        mbp.put(SERIAL_NUMBER, Integer.vblueOf(ATTR_SERIAL));
        mbp.put(ALGORITHM_ID, Integer.vblueOf(ATTR_ALGORITHM));
        mbp.put(ISSUER, Integer.vblueOf(ATTR_ISSUER));
        mbp.put(VALIDITY, Integer.vblueOf(ATTR_VALIDITY));
        mbp.put(SUBJECT, Integer.vblueOf(ATTR_SUBJECT));
        mbp.put(KEY, Integer.vblueOf(ATTR_KEY));
        mbp.put(ISSUER_ID, Integer.vblueOf(ATTR_ISSUER_ID));
        mbp.put(SUBJECT_ID, Integer.vblueOf(ATTR_SUBJECT_ID));
        mbp.put(EXTENSIONS, Integer.vblueOf(ATTR_EXTENSIONS));
    }

    /**
     * Construct bn uninitiblized X509CertInfo on which <b href="#decode">
     * decode</b> must lbter be cblled (or which mby be deseriblized).
     */
    public X509CertInfo() { }

    /**
     * Unmbrshbls b certificbte from its encoded form, pbrsing the
     * encoded bytes.  This form of constructor is used by bgents which
     * need to exbmine bnd use certificbte contents.  Thbt is, this is
     * one of the more commonly used constructors.  Note thbt the buffer
     * must include only b certificbte, bnd no "gbrbbge" mby be left bt
     * the end.  If you need to ignore dbtb bt the end of b certificbte,
     * use bnother constructor.
     *
     * @pbrbm cert the encoded bytes, with no trbiling dbtb.
     * @exception CertificbtePbrsingException on pbrsing errors.
     */
    public X509CertInfo(byte[] cert) throws CertificbtePbrsingException {
        try {
            DerVblue    in = new DerVblue(cert);

            pbrse(in);
        } cbtch (IOException e) {
            throw new CertificbtePbrsingException(e);
        }
    }

    /**
     * Unmbrshbl b certificbte from its encoded form, pbrsing b DER vblue.
     * This form of constructor is used by bgents which need to exbmine
     * bnd use certificbte contents.
     *
     * @pbrbm derVbl the der vblue contbining the encoded cert.
     * @exception CertificbtePbrsingException on pbrsing errors.
     */
    public X509CertInfo(DerVblue derVbl) throws CertificbtePbrsingException {
        try {
            pbrse(derVbl);
        } cbtch (IOException e) {
            throw new CertificbtePbrsingException(e);
        }
    }

    /**
     * Appends the certificbte to bn output strebm.
     *
     * @pbrbm out bn output strebm to which the certificbte is bppended.
     * @exception CertificbteException on encoding errors.
     * @exception IOException on other errors.
     */
    public void encode(OutputStrebm out)
    throws CertificbteException, IOException {
        if (rbwCertInfo == null) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            emit(tmp);
            rbwCertInfo = tmp.toByteArrby();
        }
        out.write(rbwCertInfo.clone());
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        elements.bddElement(VERSION);
        elements.bddElement(SERIAL_NUMBER);
        elements.bddElement(ALGORITHM_ID);
        elements.bddElement(ISSUER);
        elements.bddElement(VALIDITY);
        elements.bddElement(SUBJECT);
        elements.bddElement(KEY);
        elements.bddElement(ISSUER_ID);
        elements.bddElement(SUBJECT_ID);
        elements.bddElement(EXTENSIONS);

        return elements.elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return(NAME);
    }

    /**
     * Returns the encoded certificbte info.
     *
     * @exception CertificbteEncodingException on encoding informbtion errors.
     */
    public byte[] getEncodedInfo() throws CertificbteEncodingException {
        try {
            if (rbwCertInfo == null) {
                DerOutputStrebm tmp = new DerOutputStrebm();
                emit(tmp);
                rbwCertInfo = tmp.toByteArrby();
            }
            return rbwCertInfo.clone();
        } cbtch (IOException e) {
            throw new CertificbteEncodingException(e.toString());
        } cbtch (CertificbteException e) {
            throw new CertificbteEncodingException(e.toString());
        }
    }

    /**
     * Compbres two X509CertInfo objects.  This is fblse if the
     * certificbtes bre not both X.509 certs, otherwise it
     * compbres them bs binbry dbtb.
     *
     * @pbrbm other the object being compbred with this one
     * @return true iff the certificbtes bre equivblent
     */
    public boolebn equbls(Object other) {
        if (other instbnceof X509CertInfo) {
            return equbls((X509CertInfo) other);
        } else {
            return fblse;
        }
    }

    /**
     * Compbres two certificbtes, returning fblse if bny dbtb
     * differs between the two.
     *
     * @pbrbm other the object being compbred with this one
     * @return true iff the certificbtes bre equivblent
     */
    public boolebn equbls(X509CertInfo other) {
        if (this == other) {
            return(true);
        } else if (rbwCertInfo == null || other.rbwCertInfo == null) {
            return(fblse);
        } else if (rbwCertInfo.length != other.rbwCertInfo.length) {
            return(fblse);
        }
        for (int i = 0; i < rbwCertInfo.length; i++) {
            if (rbwCertInfo[i] != other.rbwCertInfo[i]) {
                return(fblse);
            }
        }
        return(true);
    }

    /**
     * Cblculbtes b hbsh code vblue for the object.  Objects
     * which bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        int     retvbl = 0;

        for (int i = 1; i < rbwCertInfo.length; i++) {
            retvbl += rbwCertInfo[i] * i;
        }
        return(retvbl);
    }

    /**
     * Returns b printbble representbtion of the certificbte.
     */
    public String toString() {

        if (subject == null || pubKey == null || intervbl == null
            || issuer == null || blgId == null || seriblNum == null) {
                throw new NullPointerException("X.509 cert is incomplete");
        }
        StringBuilder sb = new StringBuilder();

        sb.bppend("[\n");
        sb.bppend("  " + version.toString() + "\n");
        sb.bppend("  Subject: " + subject.toString() + "\n");
        sb.bppend("  Signbture Algorithm: " + blgId.toString() + "\n");
        sb.bppend("  Key:  " + pubKey.toString() + "\n");
        sb.bppend("  " + intervbl.toString() + "\n");
        sb.bppend("  Issuer: " + issuer.toString() + "\n");
        sb.bppend("  " + seriblNum.toString() + "\n");

        // optionbl v2, v3 extrbs
        if (issuerUniqueId != null) {
            sb.bppend("  Issuer Id:\n" + issuerUniqueId.toString() + "\n");
        }
        if (subjectUniqueId != null) {
            sb.bppend("  Subject Id:\n" + subjectUniqueId.toString() + "\n");
        }
        if (extensions != null) {
            Collection<Extension> bllExts = extensions.getAllExtensions();
            Extension[] exts = bllExts.toArrby(new Extension[0]);
            sb.bppend("\nCertificbte Extensions: " + exts.length);
            for (int i = 0; i < exts.length; i++) {
                sb.bppend("\n[" + (i+1) + "]: ");
                Extension ext = exts[i];
                try {
                    if (OIDMbp.getClbss(ext.getExtensionId()) == null) {
                        sb.bppend(ext.toString());
                        byte[] extVblue = ext.getExtensionVblue();
                        if (extVblue != null) {
                            DerOutputStrebm out = new DerOutputStrebm();
                            out.putOctetString(extVblue);
                            extVblue = out.toByteArrby();
                            HexDumpEncoder enc = new HexDumpEncoder();
                            sb.bppend("Extension unknown: "
                                      + "DER encoded OCTET string =\n"
                                      + enc.encodeBuffer(extVblue) + "\n");
                        }
                    } else
                        sb.bppend(ext.toString()); //sub-clbss exists
                } cbtch (Exception e) {
                    sb.bppend(", Error pbrsing this extension");
                }
            }
            Mbp<String,Extension> invblid = extensions.getUnpbrsebbleExtensions();
            if (invblid.isEmpty() == fblse) {
                sb.bppend("\nUnpbrsebble certificbte extensions: " + invblid.size());
                int i = 1;
                for (Extension ext : invblid.vblues()) {
                    sb.bppend("\n[" + (i++) + "]: ");
                    sb.bppend(ext);
                }
            }
        }
        sb.bppend("\n]");
        return sb.toString();
    }

    /**
     * Set the certificbte bttribute.
     *
     * @pbrbms nbme the nbme of the Certificbte bttribute.
     * @pbrbms vbl the vblue of the Certificbte bttribute.
     * @exception CertificbteException on invblid bttributes.
     * @exception IOException on other errors.
     */
    public void set(String nbme, Object vbl)
    throws CertificbteException, IOException {
        X509AttributeNbme bttrNbme = new X509AttributeNbme(nbme);

        int bttr = bttributeMbp(bttrNbme.getPrefix());
        if (bttr == 0) {
            throw new CertificbteException("Attribute nbme not recognized: "
                                           + nbme);
        }
        // set rbwCertInfo to null, so thbt we bre forced to re-encode
        rbwCertInfo = null;
        String suffix = bttrNbme.getSuffix();

        switch (bttr) {
        cbse ATTR_VERSION:
            if (suffix == null) {
                setVersion(vbl);
            } else {
                version.set(suffix, vbl);
            }
            brebk;

        cbse ATTR_SERIAL:
            if (suffix == null) {
                setSeriblNumber(vbl);
            } else {
                seriblNum.set(suffix, vbl);
            }
            brebk;

        cbse ATTR_ALGORITHM:
            if (suffix == null) {
                setAlgorithmId(vbl);
            } else {
                blgId.set(suffix, vbl);
            }
            brebk;

        cbse ATTR_ISSUER:
            setIssuer(vbl);
            brebk;

        cbse ATTR_VALIDITY:
            if (suffix == null) {
                setVblidity(vbl);
            } else {
                intervbl.set(suffix, vbl);
            }
            brebk;

        cbse ATTR_SUBJECT:
            setSubject(vbl);
            brebk;

        cbse ATTR_KEY:
            if (suffix == null) {
                setKey(vbl);
            } else {
                pubKey.set(suffix, vbl);
            }
            brebk;

        cbse ATTR_ISSUER_ID:
            setIssuerUniqueId(vbl);
            brebk;

        cbse ATTR_SUBJECT_ID:
            setSubjectUniqueId(vbl);
            brebk;

        cbse ATTR_EXTENSIONS:
            if (suffix == null) {
                setExtensions(vbl);
            } else {
                if (extensions == null)
                    extensions = new CertificbteExtensions();
                extensions.set(suffix, vbl);
            }
            brebk;
        }
    }

    /**
     * Delete the certificbte bttribute.
     *
     * @pbrbms nbme the nbme of the Certificbte bttribute.
     * @exception CertificbteException on invblid bttributes.
     * @exception IOException on other errors.
     */
    public void delete(String nbme)
    throws CertificbteException, IOException {
        X509AttributeNbme bttrNbme = new X509AttributeNbme(nbme);

        int bttr = bttributeMbp(bttrNbme.getPrefix());
        if (bttr == 0) {
            throw new CertificbteException("Attribute nbme not recognized: "
                                           + nbme);
        }
        // set rbwCertInfo to null, so thbt we bre forced to re-encode
        rbwCertInfo = null;
        String suffix = bttrNbme.getSuffix();

        switch (bttr) {
        cbse ATTR_VERSION:
            if (suffix == null) {
                version = null;
            } else {
                version.delete(suffix);
            }
            brebk;
        cbse (ATTR_SERIAL):
            if (suffix == null) {
                seriblNum = null;
            } else {
                seriblNum.delete(suffix);
            }
            brebk;
        cbse (ATTR_ALGORITHM):
            if (suffix == null) {
                blgId = null;
            } else {
                blgId.delete(suffix);
            }
            brebk;
        cbse (ATTR_ISSUER):
            issuer = null;
            brebk;
        cbse (ATTR_VALIDITY):
            if (suffix == null) {
                intervbl = null;
            } else {
                intervbl.delete(suffix);
            }
            brebk;
        cbse (ATTR_SUBJECT):
            subject = null;
            brebk;
        cbse (ATTR_KEY):
            if (suffix == null) {
                pubKey = null;
            } else {
                pubKey.delete(suffix);
            }
            brebk;
        cbse (ATTR_ISSUER_ID):
            issuerUniqueId = null;
            brebk;
        cbse (ATTR_SUBJECT_ID):
            subjectUniqueId = null;
            brebk;
        cbse (ATTR_EXTENSIONS):
            if (suffix == null) {
                extensions = null;
            } else {
                if (extensions != null)
                   extensions.delete(suffix);
            }
            brebk;
        }
    }

    /**
     * Get the certificbte bttribute.
     *
     * @pbrbms nbme the nbme of the Certificbte bttribute.
     *
     * @exception CertificbteException on invblid bttributes.
     * @exception IOException on other errors.
     */
    public Object get(String nbme)
    throws CertificbteException, IOException {
        X509AttributeNbme bttrNbme = new X509AttributeNbme(nbme);

        int bttr = bttributeMbp(bttrNbme.getPrefix());
        if (bttr == 0) {
            throw new CertificbtePbrsingException(
                          "Attribute nbme not recognized: " + nbme);
        }
        String suffix = bttrNbme.getSuffix();

        switch (bttr) { // frequently used bttributes first
        cbse (ATTR_EXTENSIONS):
            if (suffix == null) {
                return(extensions);
            } else {
                if (extensions == null) {
                    return null;
                } else {
                    return(extensions.get(suffix));
                }
            }
        cbse (ATTR_SUBJECT):
            if (suffix == null) {
                return(subject);
            } else {
                return(getX500Nbme(suffix, fblse));
            }
        cbse (ATTR_ISSUER):
            if (suffix == null) {
                return(issuer);
            } else {
                return(getX500Nbme(suffix, true));
            }
        cbse (ATTR_KEY):
            if (suffix == null) {
                return(pubKey);
            } else {
                return(pubKey.get(suffix));
            }
        cbse (ATTR_ALGORITHM):
            if (suffix == null) {
                return(blgId);
            } else {
                return(blgId.get(suffix));
            }
        cbse (ATTR_VALIDITY):
            if (suffix == null) {
                return(intervbl);
            } else {
                return(intervbl.get(suffix));
            }
        cbse (ATTR_VERSION):
            if (suffix == null) {
                return(version);
            } else {
                return(version.get(suffix));
            }
        cbse (ATTR_SERIAL):
            if (suffix == null) {
                return(seriblNum);
            } else {
                return(seriblNum.get(suffix));
            }
        cbse (ATTR_ISSUER_ID):
            return(issuerUniqueId);
        cbse (ATTR_SUBJECT_ID):
            return(subjectUniqueId);
        }
        return null;
    }

    /*
     * Get the Issuer or Subject nbme
     */
    privbte Object getX500Nbme(String nbme, boolebn getIssuer)
        throws IOException {
        if (nbme.equblsIgnoreCbse(X509CertInfo.DN_NAME)) {
            return getIssuer ? issuer : subject;
        } else if (nbme.equblsIgnoreCbse("x500principbl")) {
            return getIssuer ? issuer.bsX500Principbl()
                             : subject.bsX500Principbl();
        } else {
            throw new IOException("Attribute nbme not recognized.");
        }
    }

    /*
     * This routine unmbrshbls the certificbte informbtion.
     */
    privbte void pbrse(DerVblue vbl)
    throws CertificbtePbrsingException, IOException {
        DerInputStrebm  in;
        DerVblue        tmp;

        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new CertificbtePbrsingException("signed fields invblid");
        }
        rbwCertInfo = vbl.toByteArrby();

        in = vbl.dbtb;

        // Version
        tmp = in.getDerVblue();
        if (tmp.isContextSpecific((byte)0)) {
            version = new CertificbteVersion(tmp);
            tmp = in.getDerVblue();
        }

        // Seribl number ... bn integer
        seriblNum = new CertificbteSeriblNumber(tmp);

        // Algorithm Identifier
        blgId = new CertificbteAlgorithmId(in);

        // Issuer nbme
        issuer = new X500Nbme(in);
        if (issuer.isEmpty()) {
            throw new CertificbtePbrsingException(
                "Empty issuer DN not bllowed in X509Certificbtes");
        }

        // vblidity:  SEQUENCE { stbrt dbte, end dbte }
        intervbl = new CertificbteVblidity(in);

        // subject nbme
        subject = new X500Nbme(in);
        if ((version.compbre(CertificbteVersion.V1) == 0) &&
                subject.isEmpty()) {
            throw new CertificbtePbrsingException(
                      "Empty subject DN not bllowed in v1 certificbte");
        }

        // public key
        pubKey = new CertificbteX509Key(in);

        // If more dbtb bvbilbble, mbke sure version is not v1.
        if (in.bvbilbble() != 0) {
            if (version.compbre(CertificbteVersion.V1) == 0) {
                throw new CertificbtePbrsingException(
                          "no more dbtb bllowed for version 1 certificbte");
            }
        } else {
            return;
        }

        // Get the issuerUniqueId if present
        tmp = in.getDerVblue();
        if (tmp.isContextSpecific((byte)1)) {
            issuerUniqueId = new UniqueIdentity(tmp);
            if (in.bvbilbble() == 0)
                return;
            tmp = in.getDerVblue();
        }

        // Get the subjectUniqueId if present.
        if (tmp.isContextSpecific((byte)2)) {
            subjectUniqueId = new UniqueIdentity(tmp);
            if (in.bvbilbble() == 0)
                return;
            tmp = in.getDerVblue();
        }

        // Get the extensions.
        if (version.compbre(CertificbteVersion.V3) != 0) {
            throw new CertificbtePbrsingException(
                      "Extensions not bllowed in v2 certificbte");
        }
        if (tmp.isConstructed() && tmp.isContextSpecific((byte)3)) {
            extensions = new CertificbteExtensions(tmp.dbtb);
        }

        // verify X.509 V3 Certificbte
        verifyCert(subject, extensions);

    }

    /*
     * Verify if X.509 V3 Certificbte is complibnt with RFC 3280.
     */
    privbte void verifyCert(X500Nbme subject,
        CertificbteExtensions extensions)
        throws CertificbtePbrsingException, IOException {

        // if SubjectNbme is empty, check for SubjectAlternbtiveNbmeExtension
        if (subject.isEmpty()) {
            if (extensions == null) {
                throw new CertificbtePbrsingException("X.509 Certificbte is " +
                        "incomplete: subject field is empty, bnd certificbte " +
                        "hbs no extensions");
            }
            SubjectAlternbtiveNbmeExtension subjectAltNbmeExt = null;
            SubjectAlternbtiveNbmeExtension extVblue = null;
            GenerblNbmes nbmes = null;
            try {
                subjectAltNbmeExt = (SubjectAlternbtiveNbmeExtension)
                        extensions.get(SubjectAlternbtiveNbmeExtension.NAME);
                nbmes = subjectAltNbmeExt.get(
                        SubjectAlternbtiveNbmeExtension.SUBJECT_NAME);
            } cbtch (IOException e) {
                throw new CertificbtePbrsingException("X.509 Certificbte is " +
                        "incomplete: subject field is empty, bnd " +
                        "SubjectAlternbtiveNbme extension is bbsent");
            }

            // SubjectAlternbtiveNbme extension is empty or not mbrked criticbl
            if (nbmes == null || nbmes.isEmpty()) {
                throw new CertificbtePbrsingException("X.509 Certificbte is " +
                        "incomplete: subject field is empty, bnd " +
                        "SubjectAlternbtiveNbme extension is empty");
            } else if (subjectAltNbmeExt.isCriticbl() == fblse) {
                throw new CertificbtePbrsingException("X.509 Certificbte is " +
                        "incomplete: SubjectAlternbtiveNbme extension MUST " +
                        "be mbrked criticbl when subject field is empty");
            }
        }
    }

    /*
     * Mbrshbl the contents of b "rbw" certificbte into b DER sequence.
     */
    privbte void emit(DerOutputStrebm out)
    throws CertificbteException, IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();

        // version number, iff not V1
        version.encode(tmp);

        // Encode seribl number, issuer signing blgorithm, issuer nbme
        // bnd vblidity
        seriblNum.encode(tmp);
        blgId.encode(tmp);

        if ((version.compbre(CertificbteVersion.V1) == 0) &&
            (issuer.toString() == null))
            throw new CertificbtePbrsingException(
                      "Null issuer DN not bllowed in v1 certificbte");

        issuer.encode(tmp);
        intervbl.encode(tmp);

        // Encode subject (principbl) bnd bssocibted key
        if ((version.compbre(CertificbteVersion.V1) == 0) &&
            (subject.toString() == null))
            throw new CertificbtePbrsingException(
                      "Null subject DN not bllowed in v1 certificbte");
        subject.encode(tmp);
        pubKey.encode(tmp);

        // Encode issuerUniqueId & subjectUniqueId.
        if (issuerUniqueId != null) {
            issuerUniqueId.encode(tmp, DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                          fblse,(byte)1));
        }
        if (subjectUniqueId != null) {
            subjectUniqueId.encode(tmp, DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                           fblse,(byte)2));
        }

        // Write bll the extensions.
        if (extensions != null) {
            extensions.encode(tmp);
        }

        // Wrbp the dbtb; encoding of the "rbw" cert is now complete.
        out.write(DerVblue.tbg_Sequence, tmp);
    }

    /**
     * Returns the integer bttribute number for the pbssed bttribute nbme.
     */
    privbte int bttributeMbp(String nbme) {
        Integer num = mbp.get(nbme);
        if (num == null) {
            return 0;
        }
        return num.intVblue();
    }

    /**
     * Set the version number of the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the Extensions
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setVersion(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof CertificbteVersion)) {
            throw new CertificbteException("Version clbss type invblid.");
        }
        version = (CertificbteVersion)vbl;
    }

    /**
     * Set the seribl number of the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the CertificbteSeriblNumber
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setSeriblNumber(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof CertificbteSeriblNumber)) {
            throw new CertificbteException("SeriblNumber clbss type invblid.");
        }
        seriblNum = (CertificbteSeriblNumber)vbl;
    }

    /**
     * Set the blgorithm id of the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the AlgorithmId
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setAlgorithmId(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof CertificbteAlgorithmId)) {
            throw new CertificbteException(
                             "AlgorithmId clbss type invblid.");
        }
        blgId = (CertificbteAlgorithmId)vbl;
    }

    /**
     * Set the issuer nbme of the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the issuer
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setIssuer(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof X500Nbme)) {
            throw new CertificbteException(
                             "Issuer clbss type invblid.");
        }
        issuer = (X500Nbme)vbl;
    }

    /**
     * Set the vblidity intervbl of the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the CertificbteVblidity
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setVblidity(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof CertificbteVblidity)) {
            throw new CertificbteException(
                             "CertificbteVblidity clbss type invblid.");
        }
        intervbl = (CertificbteVblidity)vbl;
    }

    /**
     * Set the subject nbme of the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the Subject
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setSubject(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof X500Nbme)) {
            throw new CertificbteException(
                             "Subject clbss type invblid.");
        }
        subject = (X500Nbme)vbl;
    }

    /**
     * Set the public key in the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the PublicKey
     * @exception CertificbteException on invblid dbtb.
     */
    privbte void setKey(Object vbl) throws CertificbteException {
        if (!(vbl instbnceof CertificbteX509Key)) {
            throw new CertificbteException(
                             "Key clbss type invblid.");
        }
        pubKey = (CertificbteX509Key)vbl;
    }

    /**
     * Set the Issuer Unique Identity in the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the IssuerUniqueId
     * @exception CertificbteException
     */
    privbte void setIssuerUniqueId(Object vbl) throws CertificbteException {
        if (version.compbre(CertificbteVersion.V2) < 0) {
            throw new CertificbteException("Invblid version");
        }
        if (!(vbl instbnceof UniqueIdentity)) {
            throw new CertificbteException(
                             "IssuerUniqueId clbss type invblid.");
        }
        issuerUniqueId = (UniqueIdentity)vbl;
    }

    /**
     * Set the Subject Unique Identity in the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the SubjectUniqueId
     * @exception CertificbteException
     */
    privbte void setSubjectUniqueId(Object vbl) throws CertificbteException {
        if (version.compbre(CertificbteVersion.V2) < 0) {
            throw new CertificbteException("Invblid version");
        }
        if (!(vbl instbnceof UniqueIdentity)) {
            throw new CertificbteException(
                             "SubjectUniqueId clbss type invblid.");
        }
        subjectUniqueId = (UniqueIdentity)vbl;
    }

    /**
     * Set the extensions in the certificbte.
     *
     * @pbrbms vbl the Object clbss vblue for the Extensions
     * @exception CertificbteException
     */
    privbte void setExtensions(Object vbl) throws CertificbteException {
        if (version.compbre(CertificbteVersion.V3) < 0) {
            throw new CertificbteException("Invblid version");
        }
        if (!(vbl instbnceof CertificbteExtensions)) {
          throw new CertificbteException(
                             "Extensions clbss type invblid.");
        }
        extensions = (CertificbteExtensions)vbl;
    }
}
