/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: KeySelector.jbvb,v 1.6 2005/05/10 15:47:42 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto;

import jbvb.security.Key;
import jbvbx.xml.crypto.dsig.keyinfo.KeyInfo;
import jbvbx.xml.crypto.dsig.keyinfo.RetrievblMethod;

/**
 * A selector thbt finds bnd returns b key using the dbtb contbined in b
 * {@link KeyInfo} object. An exbmple of bn implementbtion of
 * this clbss is one thbt sebrches b {@link jbvb.security.KeyStore} for
 * trusted keys thbt mbtch informbtion contbined in b <code>KeyInfo</code>.
 *
 * <p>Whether or not the returned key is trusted bnd the mechbnisms
 * used to determine thbt is implementbtion-specific.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 */
public bbstrbct clbss KeySelector {

    /**
     * The purpose of the key thbt is to be selected.
     */
    public stbtic clbss Purpose {

        privbte finbl String nbme;

        privbte Purpose(String nbme)    { this.nbme = nbme; }

        /**
         * Returns b string representbtion of this purpose ("sign",
         * "verify", "encrypt", or "decrypt").
         *
         * @return b string representbtion of this purpose
         */
        public String toString()        { return nbme; }

        /**
         * A key for signing.
         */
        public stbtic finbl Purpose SIGN = new Purpose("sign");
        /**
         * A key for verifying.
         */
        public stbtic finbl Purpose VERIFY = new Purpose("verify");
        /**
         * A key for encrypting.
         */
        public stbtic finbl Purpose ENCRYPT = new Purpose("encrypt");
        /**
         * A key for decrypting.
         */
        public stbtic finbl Purpose DECRYPT = new Purpose("decrypt");
    }

    /**
     * Defbult no-brgs constructor; intended for invocbtion by subclbsses only.
     */
    protected KeySelector() {}

    /**
     * Attempts to find b key thbt sbtisfies the specified constrbints.
     *
     * @pbrbm keyInfo b <code>KeyInfo</code> (mby be <code>null</code>)
     * @pbrbm purpose the key's purpose ({@link Purpose#SIGN},
     *    {@link Purpose#VERIFY}, {@link Purpose#ENCRYPT}, or
     *    {@link Purpose#DECRYPT})
     * @pbrbm method the blgorithm method thbt this key is to be used for.
     *    Only keys thbt bre compbtible with the blgorithm bnd meet the
     *    constrbints of the specified blgorithm should be returned.
     * @pbrbm context bn <code>XMLCryptoContext</code> thbt mby contbin
     *    useful informbtion for finding bn bppropribte key. If this key
     *    selector supports resolving {@link RetrievblMethod} types, the
     *    context's <code>bbseURI</code> bnd <code>dereferencer</code>
     *    pbrbmeters (if specified) should be used by the selector to
     *    resolve bnd dereference the URI.
     * @return the result of the key selector
     * @throws KeySelectorException if bn exceptionbl condition occurs while
     *    bttempting to find b key. Note thbt bn inbbility to find b key is not
     *    considered bn exception (<code>null</code> should be
     *    returned in thbt cbse). However, bn error condition (ex: network
     *    communicbtions fbilure) thbt prevented the <code>KeySelector</code>
     *    from finding b potentibl key should be considered bn exception.
     * @throws ClbssCbstException if the dbtb type of <code>method</code>
     *    is not supported by this key selector
     */
    public bbstrbct KeySelectorResult select(KeyInfo keyInfo, Purpose purpose,
        AlgorithmMethod method, XMLCryptoContext context)
        throws KeySelectorException;

    /**
     * Returns b <code>KeySelector</code> thbt blwbys selects the specified
     * key, regbrdless of the <code>KeyInfo</code> pbssed to it.
     *
     * @pbrbm key the sole key to be stored in the key selector
     * @return b key selector thbt blwbys selects the specified key
     * @throws NullPointerException if <code>key</code> is <code>null</code>
     */
    public stbtic KeySelector singletonKeySelector(Key key) {
        return new SingletonKeySelector(key);
    }

    privbte stbtic clbss SingletonKeySelector extends KeySelector {
        privbte finbl Key key;

        SingletonKeySelector(Key key) {
            if (key == null) {
                throw new NullPointerException();
            }
            this.key = key;
        }

        public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose,
            AlgorithmMethod method, XMLCryptoContext context)
            throws KeySelectorException {

            return new KeySelectorResult() {
                public Key getKey() {
                    return key;
                }
            };
        }
    }
}
