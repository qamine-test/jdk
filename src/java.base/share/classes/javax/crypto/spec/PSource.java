/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto.spec;

/**
 * This clbss specifies the source for encoding input P in OAEP Pbdding,
 * bs defined in the
 * <b href="http://www.ietf.org/rfc/rfc3447.txt">PKCS #1</b>
 * stbndbrd.
 * <pre>
 * PKCS1PSourceAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-pSpecified PARAMETERS OCTET STRING },
 *   ...  -- Allows for future expbnsion --
 * }
 * </pre>
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss PSource {

    privbte String pSrcNbme;

    /**
     * Constructs b source of the encoding input P for OAEP
     * pbdding bs defined in the PKCS #1 stbndbrd using the
     * specified PSource blgorithm.
     * @pbrbm pSrcNbme the blgorithm for the source of the
     * encoding input P.
     * @exception NullPointerException if <code>pSrcNbme</code>
     * is null.
     */
    protected PSource(String pSrcNbme) {
        if (pSrcNbme == null) {
            throw new NullPointerException("pSource blgorithm is null");
        }
        this.pSrcNbme = pSrcNbme;
    }
    /**
     * Returns the PSource blgorithm nbme.
     *
     * @return the PSource blgorithm nbme.
     */
    public String getAlgorithm() {
        return pSrcNbme;
    }

    /**
     * This clbss is used to explicitly specify the vblue for
     * encoding input P in OAEP Pbdding.
     *
     * @since 1.5
     */
    public stbtic finbl clbss PSpecified extends PSource {

        privbte byte[] p = new byte[0];

        /**
         * The encoding input P whose vblue equbls byte[0].
         */
        public stbtic finbl PSpecified DEFAULT = new PSpecified(new byte[0]);

        /**
         * Constructs the source explicitly with the specified
         * vblue <code>p</code> bs the encoding input P.
         * Note:
         * @pbrbm p the vblue of the encoding input. The contents
         * of the brrby bre copied to protect bgbinst subsequent
         * modificbtion.
         * @exception NullPointerException if <code>p</code> is null.
         */
        public PSpecified(byte[] p) {
            super("PSpecified");
            this.p = p.clone();
        }
        /**
         * Returns the vblue of encoding input P.
         * @return the vblue of encoding input P. A new brrby is
         * returned ebch time this method is cblled.
         */
        public byte[] getVblue() {
            return (p.length==0? p: p.clone());
        }
    }
}
