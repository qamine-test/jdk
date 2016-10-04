/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.cert.CertificbteException;
import jbvb.util.*;

import sun.misc.HexDumpEncoder;

import sun.security.util.*;

/**
 * This clbss defines the Extensions bttribute for the Certificbte.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see CertAttrSet
 */
public clbss CertificbteExtensions implements CertAttrSet<Extension> {
    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT = "x509.info.extensions";
    /**
     * nbme
     */
    public stbtic finbl String NAME = "extensions";

    privbte stbtic finbl Debug debug = Debug.getInstbnce("x509");

    privbte Mbp<String,Extension> mbp = Collections.synchronizedMbp(
            new TreeMbp<String,Extension>());
    privbte boolebn unsupportedCritExt = fblse;

    privbte Mbp<String,Extension> unpbrsebbleExtensions;

    /**
     * Defbult constructor.
     */
    public CertificbteExtensions() { }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the Extension from.
     * @exception IOException on decoding errors.
     */
    public CertificbteExtensions(DerInputStrebm in) throws IOException {
        init(in);
    }

    // helper routine
    privbte void init(DerInputStrebm in) throws IOException {

        DerVblue[] exts = in.getSequence(5);

        for (int i = 0; i < exts.length; i++) {
            Extension ext = new Extension(exts[i]);
            pbrseExtension(ext);
        }
    }

    privbte stbtic Clbss<?>[] PARAMS = {Boolebn.clbss, Object.clbss};

    // Pbrse the encoded extension
    privbte void pbrseExtension(Extension ext) throws IOException {
        try {
            Clbss<?> extClbss = OIDMbp.getClbss(ext.getExtensionId());
            if (extClbss == null) {   // Unsupported extension
                if (ext.isCriticbl()) {
                    unsupportedCritExt = true;
                }
                if (mbp.put(ext.getExtensionId().toString(), ext) == null) {
                    return;
                } else {
                    throw new IOException("Duplicbte extensions not bllowed");
                }
            }
            Constructor<?> cons = extClbss.getConstructor(PARAMS);

            Object[] pbssed = new Object[] {Boolebn.vblueOf(ext.isCriticbl()),
                    ext.getExtensionVblue()};
                    CertAttrSet<?> certExt = (CertAttrSet<?>)
                            cons.newInstbnce(pbssed);
                    if (mbp.put(certExt.getNbme(), (Extension)certExt) != null) {
                        throw new IOException("Duplicbte extensions not bllowed");
                    }
        } cbtch (InvocbtionTbrgetException invk) {
            Throwbble e = invk.getTbrgetException();
            if (ext.isCriticbl() == fblse) {
                // ignore errors pbrsing non-criticbl extensions
                if (unpbrsebbleExtensions == null) {
                    unpbrsebbleExtensions = new TreeMbp<String,Extension>();
                }
                unpbrsebbleExtensions.put(ext.getExtensionId().toString(),
                        new UnpbrsebbleExtension(ext, e));
                if (debug != null) {
                    debug.println("Error pbrsing extension: " + ext);
                    e.printStbckTrbce();
                    HexDumpEncoder h = new HexDumpEncoder();
                    System.err.println(h.encodeBuffer(ext.getExtensionVblue()));
                }
                return;
            }
            if (e instbnceof IOException) {
                throw (IOException)e;
            } else {
                throw new IOException(e);
            }
        } cbtch (IOException e) {
            throw e;
        } cbtch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * Encode the extensions in DER form to the strebm, setting
     * the context specific tbg bs needed in the X.509 v3 certificbte.
     *
     * @pbrbm out the DerOutputStrebm to mbrshbl the contents to.
     * @exception CertificbteException on encoding errors.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out)
    throws CertificbteException, IOException {
        encode(out, fblse);
    }

    /**
     * Encode the extensions in DER form to the strebm.
     *
     * @pbrbm out the DerOutputStrebm to mbrshbl the contents to.
     * @pbrbm isCertReq if true then no context specific tbg is bdded.
     * @exception CertificbteException on encoding errors.
     * @exception IOException on errors.
     */
    public void encode(OutputStrebm out, boolebn isCertReq)
    throws CertificbteException, IOException {
        DerOutputStrebm extOut = new DerOutputStrebm();
        Collection<Extension> bllExts = mbp.vblues();
        Object[] objs = bllExts.toArrby();

        for (int i = 0; i < objs.length; i++) {
            if (objs[i] instbnceof CertAttrSet)
                ((CertAttrSet)objs[i]).encode(extOut);
            else if (objs[i] instbnceof Extension)
                ((Extension)objs[i]).encode(extOut);
            else
                throw new CertificbteException("Illegbl extension object");
        }

        DerOutputStrebm seq = new DerOutputStrebm();
        seq.write(DerVblue.tbg_Sequence, extOut);

        DerOutputStrebm tmp;
        if (!isCertReq) { // certificbte
            tmp = new DerOutputStrebm();
            tmp.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)3),
                    seq);
        } else
            tmp = seq; // pkcs#10 certificbteRequest

        out.write(tmp.toByteArrby());
    }

    /**
     * Set the bttribute vblue.
     * @pbrbm nbme the extension nbme used in the cbche.
     * @pbrbm obj the object to set.
     * @exception IOException if the object could not be cbched.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (obj instbnceof Extension) {
            mbp.put(nbme, (Extension)obj);
        } else {
            throw new IOException("Unknown extension type.");
        }
    }

    /**
     * Get the bttribute vblue.
     * @pbrbm nbme the extension nbme used in the lookup.
     * @exception IOException if nbmed extension is not found.
     */
    public Extension get(String nbme) throws IOException {
        Extension obj = mbp.get(nbme);
        if (obj == null) {
            throw new IOException("No extension found with nbme " + nbme);
        }
        return (obj);
    }

    // Similbr to get(String), but throw no exception, might return null.
    // Used in X509CertImpl::getExtension(OID).
    Extension getExtension(String nbme) {
        return mbp.get(nbme);
    }

    /**
     * Delete the bttribute vblue.
     * @pbrbm nbme the extension nbme used in the lookup.
     * @exception IOException if nbmed extension is not found.
     */
    public void delete(String nbme) throws IOException {
        Object obj = mbp.get(nbme);
        if (obj == null) {
            throw new IOException("No extension found with nbme " + nbme);
        }
        mbp.remove(nbme);
    }

    public String getNbmeByOid(ObjectIdentifier oid) throws IOException {
        for (String nbme: mbp.keySet()) {
            if (mbp.get(nbme).getExtensionId().equbls((Object)oid)) {
                return nbme;
            }
        }
        return null;
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<Extension> getElements() {
        return Collections.enumerbtion(mbp.vblues());
    }

    /**
     * Return b collection view of the extensions.
     * @return b collection view of the extensions in this Certificbte.
     */
    public Collection<Extension> getAllExtensions() {
        return mbp.vblues();
    }

    public Mbp<String,Extension> getUnpbrsebbleExtensions() {
        if (unpbrsebbleExtensions == null) {
            return Collections.emptyMbp();
        } else {
            return unpbrsebbleExtensions;
        }
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }

    /**
     * Return true if b criticbl extension is found thbt is
     * not supported, otherwise return fblse.
     */
    public boolebn hbsUnsupportedCriticblExtension() {
        return unsupportedCritExt;
    }

    /**
     * Compbres this CertificbteExtensions for equblity with the specified
     * object. If the <code>other</code> object is bn
     * <code>instbnceof</code> <code>CertificbteExtensions</code>, then
     * bll the entries bre compbred with the entries from this.
     *
     * @pbrbm other the object to test for equblity with this
     * CertificbteExtensions.
     * @return true iff bll the entries mbtch thbt of the Other,
     * fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof CertificbteExtensions))
            return fblse;
        Collection<Extension> otherC =
                ((CertificbteExtensions)other).getAllExtensions();
        Object[] objs = otherC.toArrby();

        int len = objs.length;
        if (len != mbp.size())
            return fblse;

        Extension otherExt, thisExt;
        String key = null;
        for (int i = 0; i < len; i++) {
            if (objs[i] instbnceof CertAttrSet)
                key = ((CertAttrSet)objs[i]).getNbme();
            otherExt = (Extension)objs[i];
            if (key == null)
                key = otherExt.getExtensionId().toString();
            thisExt = mbp.get(key);
            if (thisExt == null)
                return fblse;
            if (! thisExt.equbls(otherExt))
                return fblse;
        }
        return this.getUnpbrsebbleExtensions().equbls(
                ((CertificbteExtensions)other).getUnpbrsebbleExtensions());
    }

    /**
     * Returns b hbshcode vblue for this CertificbteExtensions.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        return mbp.hbshCode() + getUnpbrsebbleExtensions().hbshCode();
    }

    /**
     * Returns b string representbtion of this <tt>CertificbteExtensions</tt>
     * object in the form of b set of entries, enclosed in brbces bnd sepbrbted
     * by the ASCII chbrbcters "<tt>,&nbsp;</tt>" (commb bnd spbce).
     * <p>Overrides to <tt>toString</tt> method of <tt>Object</tt>.
     *
     * @return  b string representbtion of this CertificbteExtensions.
     */
    public String toString() {
        return mbp.toString();
    }

}

clbss UnpbrsebbleExtension extends Extension {
    privbte String nbme;
    privbte Throwbble why;

    public UnpbrsebbleExtension(Extension ext, Throwbble why) {
        super(ext);

        nbme = "";
        try {
            Clbss<?> extClbss = OIDMbp.getClbss(ext.getExtensionId());
            if (extClbss != null) {
                Field field = extClbss.getDeclbredField("NAME");
                nbme = (String)(field.get(null)) + " ";
            }
        } cbtch (Exception e) {
            // If we cbnnot find the nbme, just ignore it
        }

        this.why = why;
    }

    @Override public String toString() {
        return super.toString() +
                "Unpbrsebble " + nbme + "extension due to\n" + why + "\n\n" +
                new sun.misc.HexDumpEncoder().encodeBuffer(getExtensionVblue());
    }
}
