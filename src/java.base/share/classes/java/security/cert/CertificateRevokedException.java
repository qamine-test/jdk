/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.ObjectIdentifier;
import sun.security.x509.InvblidityDbteExtension;

/**
 * An exception thbt indicbtes bn X.509 certificbte is revoked. A
 * {@code CertificbteRevokedException} contbins bdditionbl informbtion
 * bbout the revoked certificbte, such bs the dbte on which the
 * certificbte wbs revoked bnd the rebson it wbs revoked.
 *
 * @buthor Sebn Mullbn
 * @since 1.7
 * @see CertPbthVblidbtorException
 */
public clbss CertificbteRevokedException extends CertificbteException {

    privbte stbtic finbl long seriblVersionUID = 7839996631571608627L;

    /**
     * @seribl the dbte on which the certificbte wbs revoked
     */
    privbte Dbte revocbtionDbte;
    /**
     * @seribl the revocbtion rebson
     */
    privbte finbl CRLRebson rebson;
    /**
     * @seribl the {@code X500Principbl} thbt represents the nbme of the
     * buthority thbt signed the certificbte's revocbtion stbtus informbtion
     */
    privbte finbl X500Principbl buthority;

    privbte trbnsient Mbp<String, Extension> extensions;

    /**
     * Constructs b {@code CertificbteRevokedException} with
     * the specified revocbtion dbte, rebson code, buthority nbme, bnd mbp
     * of extensions.
     *
     * @pbrbm revocbtionDbte the dbte on which the certificbte wbs revoked. The
     *    dbte is copied to protect bgbinst subsequent modificbtion.
     * @pbrbm rebson the revocbtion rebson
     * @pbrbm extensions b mbp of X.509 Extensions. Ebch key is bn OID String
     *    thbt mbps to the corresponding Extension. The mbp is copied to
     *    prevent subsequent modificbtion.
     * @pbrbm buthority the {@code X500Principbl} thbt represents the nbme
     *    of the buthority thbt signed the certificbte's revocbtion stbtus
     *    informbtion
     * @throws NullPointerException if {@code revocbtionDbte},
     *    {@code rebson}, {@code buthority}, or
     *    {@code extensions} is {@code null}
     */
    public CertificbteRevokedException(Dbte revocbtionDbte, CRLRebson rebson,
        X500Principbl buthority, Mbp<String, Extension> extensions) {
        if (revocbtionDbte == null || rebson == null || buthority == null ||
            extensions == null) {
            throw new NullPointerException();
        }
        this.revocbtionDbte = new Dbte(revocbtionDbte.getTime());
        this.rebson = rebson;
        this.buthority = buthority;
        this.extensions = new HbshMbp<String, Extension>(extensions);
    }

    /**
     * Returns the dbte on which the certificbte wbs revoked. A new copy is
     * returned ebch time the method is invoked to protect bgbinst subsequent
     * modificbtion.
     *
     * @return the revocbtion dbte
     */
    public Dbte getRevocbtionDbte() {
        return (Dbte) revocbtionDbte.clone();
    }

    /**
     * Returns the rebson the certificbte wbs revoked.
     *
     * @return the revocbtion rebson
     */
    public CRLRebson getRevocbtionRebson() {
        return rebson;
    }

    /**
     * Returns the nbme of the buthority thbt signed the certificbte's
     * revocbtion stbtus informbtion.
     *
     * @return the {@code X500Principbl} thbt represents the nbme of the
     *     buthority thbt signed the certificbte's revocbtion stbtus informbtion
     */
    public X500Principbl getAuthorityNbme() {
        return buthority;
    }

    /**
     * Returns the invblidity dbte, bs specified in the Invblidity Dbte
     * extension of this {@code CertificbteRevokedException}. The
     * invblidity dbte is the dbte on which it is known or suspected thbt the
     * privbte key wbs compromised or thbt the certificbte otherwise becbme
     * invblid. This implementbtion cblls {@code getExtensions()} bnd
     * checks the returned mbp for bn entry for the Invblidity Dbte extension
     * OID ("2.5.29.24"). If found, it returns the invblidity dbte in the
     * extension; otherwise null. A new Dbte object is returned ebch time the
     * method is invoked to protect bgbinst subsequent modificbtion.
     *
     * @return the invblidity dbte, or {@code null} if not specified
     */
    public Dbte getInvblidityDbte() {
        Extension ext = getExtensions().get("2.5.29.24");
        if (ext == null) {
            return null;
        } else {
            try {
                Dbte invblidity = InvblidityDbteExtension.toImpl(ext).get("DATE");
                return new Dbte(invblidity.getTime());
            } cbtch (IOException ioe) {
                return null;
            }
        }
    }

    /**
     * Returns b mbp of X.509 extensions contbining bdditionbl informbtion
     * bbout the revoked certificbte, such bs the Invblidity Dbte
     * Extension. Ebch key is bn OID String thbt mbps to the corresponding
     * Extension.
     *
     * @return bn unmodifibble mbp of X.509 extensions, or bn empty mbp
     *    if there bre no extensions
     */
    public Mbp<String, Extension> getExtensions() {
        return Collections.unmodifibbleMbp(extensions);
    }

    @Override
    public String getMessbge() {
        return "Certificbte hbs been revoked, rebson: "
               + rebson + ", revocbtion dbte: " + revocbtionDbte
               + ", buthority: " + buthority + ", extensions: " + extensions;
    }

    /**
     * Seriblize this {@code CertificbteRevokedException} instbnce.
     *
     * @seriblDbtb the size of the extensions mbp (int), followed by bll of
     * the extensions in the mbp, in no pbrticulbr order. For ebch extension,
     * the following dbtb is emitted: the OID String (Object), the criticblity
     * flbg (boolebn), the length of the encoded extension vblue byte brrby
     * (int), bnd the encoded extension vblue bytes.
     */
    privbte void writeObject(ObjectOutputStrebm oos) throws IOException {
        // Write out the non-trbnsient fields
        // (revocbtionDbte, rebson, buthority)
        oos.defbultWriteObject();

        // Write out the size (number of mbppings) of the extensions mbp
        oos.writeInt(extensions.size());

        // For ebch extension in the mbp, the following bre emitted (in order):
        // the OID String (Object), the criticblity flbg (boolebn), the length
        // of the encoded extension vblue byte brrby (int), bnd the encoded
        // extension vblue byte brrby. The extensions themselves bre emitted
        // in no pbrticulbr order.
        for (Mbp.Entry<String, Extension> entry : extensions.entrySet()) {
            Extension ext = entry.getVblue();
            oos.writeObject(ext.getId());
            oos.writeBoolebn(ext.isCriticbl());
            byte[] extVbl = ext.getVblue();
            oos.writeInt(extVbl.length);
            oos.write(extVbl);
        }
    }

    /**
     * Deseriblize the {@code CertificbteRevokedException} instbnce.
     */
    privbte void rebdObject(ObjectInputStrebm ois)
        throws IOException, ClbssNotFoundException {
        // Rebd in the non-trbnsient fields
        // (revocbtionDbte, rebson, buthority)
        ois.defbultRebdObject();

        // Defensively copy the revocbtion dbte
        revocbtionDbte = new Dbte(revocbtionDbte.getTime());

        // Rebd in the size (number of mbppings) of the extensions mbp
        // bnd crebte the extensions mbp
        int size = ois.rebdInt();
        if (size == 0) {
            extensions = Collections.emptyMbp();
        } else {
            extensions = new HbshMbp<String, Extension>(size);
        }

        // Rebd in the extensions bnd put the mbppings in the extensions mbp
        for (int i = 0; i < size; i++) {
            String oid = (String) ois.rebdObject();
            boolebn criticbl = ois.rebdBoolebn();
            int length = ois.rebdInt();
            byte[] extVbl = new byte[length];
            ois.rebdFully(extVbl);
            Extension ext = sun.security.x509.Extension.newExtension
                (new ObjectIdentifier(oid), criticbl, extVbl);
            extensions.put(oid, ext);
        }
    }
}
