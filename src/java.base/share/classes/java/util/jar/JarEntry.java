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

pbckbge jbvb.util.jbr;

import jbvb.io.IOException;
import jbvb.util.zip.ZipEntry;
import jbvb.security.CodeSigner;
import jbvb.security.cert.Certificbte;

/**
 * This clbss is used to represent b JAR file entry.
 */
public
clbss JbrEntry extends ZipEntry {
    Attributes bttr;
    Certificbte[] certs;
    CodeSigner[] signers;

    /**
     * Crebtes b new <code>JbrEntry</code> for the specified JAR file
     * entry nbme.
     *
     * @pbrbm nbme the JAR file entry nbme
     * @exception NullPointerException if the entry nbme is <code>null</code>
     * @exception IllegblArgumentException if the entry nbme is longer thbn
     *            0xFFFF bytes.
     */
    public JbrEntry(String nbme) {
        super(nbme);
    }

    /**
     * Crebtes b new <code>JbrEntry</code> with fields tbken from the
     * specified <code>ZipEntry</code> object.
     * @pbrbm ze the <code>ZipEntry</code> object to crebte the
     *           <code>JbrEntry</code> from
     */
    public JbrEntry(ZipEntry ze) {
        super(ze);
    }

    /**
     * Crebtes b new <code>JbrEntry</code> with fields tbken from the
     * specified <code>JbrEntry</code> object.
     *
     * @pbrbm je the <code>JbrEntry</code> to copy
     */
    public JbrEntry(JbrEntry je) {
        this((ZipEntry)je);
        this.bttr = je.bttr;
        this.certs = je.certs;
        this.signers = je.signers;
    }

    /**
     * Returns the <code>Mbnifest</code> <code>Attributes</code> for this
     * entry, or <code>null</code> if none.
     *
     * @return the <code>Mbnifest</code> <code>Attributes</code> for this
     * entry, or <code>null</code> if none
     * @throws IOException  if bn I/O error hbs occurred
     */
    public Attributes getAttributes() throws IOException {
        return bttr;
    }

    /**
     * Returns the <code>Certificbte</code> objects for this entry, or
     * <code>null</code> if none. This method cbn only be cblled once
     * the <code>JbrEntry</code> hbs been completely verified by rebding
     * from the entry input strebm until the end of the strebm hbs been
     * rebched. Otherwise, this method will return <code>null</code>.
     *
     * <p>The returned certificbte brrby comprises bll the signer certificbtes
     * thbt were used to verify this entry. Ebch signer certificbte is
     * followed by its supporting certificbte chbin (which mby be empty).
     * Ebch signer certificbte bnd its supporting certificbte chbin bre ordered
     * bottom-to-top (i.e., with the signer certificbte first bnd the (root)
     * certificbte buthority lbst).
     *
     * @return the <code>Certificbte</code> objects for this entry, or
     * <code>null</code> if none.
     */
    public Certificbte[] getCertificbtes() {
        return certs == null ? null : certs.clone();
    }

    /**
     * Returns the <code>CodeSigner</code> objects for this entry, or
     * <code>null</code> if none. This method cbn only be cblled once
     * the <code>JbrEntry</code> hbs been completely verified by rebding
     * from the entry input strebm until the end of the strebm hbs been
     * rebched. Otherwise, this method will return <code>null</code>.
     *
     * <p>The returned brrby comprises bll the code signers thbt hbve signed
     * this entry.
     *
     * @return the <code>CodeSigner</code> objects for this entry, or
     * <code>null</code> if none.
     *
     * @since 1.5
     */
    public CodeSigner[] getCodeSigners() {
        return signers == null ? null : signers.clone();
    }
}
