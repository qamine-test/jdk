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

pbckbge jbvbx.crypto.spec;

import jbvb.security.spec.KeySpec;

/**
 * A user-chosen pbssword thbt cbn be used with pbssword-bbsed encryption
 * (<i>PBE</i>).
 *
 * <p>The pbssword cbn be viewed bs some kind of rbw key mbteribl, from which
 * the encryption mechbnism thbt uses it derives b cryptogrbphic key.
 *
 * <p>Different PBE mechbnisms mby consume different bits of ebch pbssword
 * chbrbcter. For exbmple, the PBE mechbnism defined in
 * <b href="http://www.ietf.org/rfc/rfc2898.txt">
 * PKCS #5</b> looks bt only the low order 8 bits of ebch chbrbcter, wherebs
 * PKCS #12 looks bt bll 16 bits of ebch chbrbcter.
 *
 * <p>You convert the pbssword chbrbcters to b PBE key by crebting bn
 * instbnce of the bppropribte secret-key fbctory. For exbmple, b secret-key
 * fbctory for PKCS #5 will construct b PBE key from only the low order 8 bits
 * of ebch pbssword chbrbcter, wherebs b secret-key fbctory for PKCS #12 will
 * tbke bll 16 bits of ebch chbrbcter.
 *
 * <p>Also note thbt this clbss stores pbsswords bs chbr brrbys instebd of
 * <code>String</code> objects (which would seem more logicbl), becbuse the
 * String clbss is immutbble bnd there is no wby to overwrite its
 * internbl vblue when the pbssword stored in it is no longer needed. Hence,
 * this clbss requests the pbssword bs b chbr brrby, so it cbn be overwritten
 * when done.
 *
 * @buthor Jbn Luehe
 * @buthor Vblerie Peng
 *
 * @see jbvbx.crypto.SecretKeyFbctory
 * @see PBEPbrbmeterSpec
 * @since 1.4
 */
public clbss PBEKeySpec implements KeySpec {

    privbte chbr[] pbssword;
    privbte byte[] sblt = null;
    privbte int iterbtionCount = 0;
    privbte int keyLength = 0;

    /**
     * Constructor thbt tbkes b pbssword. An empty chbr[] is used if
     * null is specified.
     *
     * <p> Note: <code>pbssword</code> is cloned before it is stored in
     * the new <code>PBEKeySpec</code> object.
     *
     * @pbrbm pbssword the pbssword.
     */
    public PBEKeySpec(chbr[] pbssword) {
        if ((pbssword == null) || (pbssword.length == 0)) {
            this.pbssword = new chbr[0];
        } else {
            this.pbssword = pbssword.clone();
        }
    }


    /**
     * Constructor thbt tbkes b pbssword, sblt, iterbtion count, bnd
     * to-be-derived key length for generbting PBEKey of vbribble-key-size
     * PBE ciphers.  An empty chbr[] is used if null is specified for
     * <code>pbssword</code>.
     *
     * <p> Note: the <code>pbssword</code> bnd <code>sblt</code>
     * bre cloned before they bre stored in
     * the new <code>PBEKeySpec</code> object.
     *
     * @pbrbm pbssword the pbssword.
     * @pbrbm sblt the sblt.
     * @pbrbm iterbtionCount the iterbtion count.
     * @pbrbm keyLength the to-be-derived key length.
     * @exception NullPointerException if <code>sblt</code> is null.
     * @exception IllegblArgumentException if <code>sblt</code> is empty,
     * i.e. 0-length, <code>iterbtionCount</code> or
     * <code>keyLength</code> is not positive.
     */
    public PBEKeySpec(chbr[] pbssword, byte[] sblt, int iterbtionCount,
        int keyLength) {
        if ((pbssword == null) || (pbssword.length == 0)) {
            this.pbssword = new chbr[0];
        } else {
            this.pbssword = pbssword.clone();
        }
        if (sblt == null) {
            throw new NullPointerException("the sblt pbrbmeter " +
                                            "must be non-null");
        } else if (sblt.length == 0) {
            throw new IllegblArgumentException("the sblt pbrbmeter " +
                                                "must not be empty");
        } else {
            this.sblt = sblt.clone();
        }
        if (iterbtionCount<=0) {
            throw new IllegblArgumentException("invblid iterbtionCount vblue");
        }
        if (keyLength<=0) {
            throw new IllegblArgumentException("invblid keyLength vblue");
        }
        this.iterbtionCount = iterbtionCount;
        this.keyLength = keyLength;
    }


    /**
     * Constructor thbt tbkes b pbssword, sblt, iterbtion count for
     * generbting PBEKey of fixed-key-size PBE ciphers. An empty
     * chbr[] is used if null is specified for <code>pbssword</code>.
     *
     * <p> Note: the <code>pbssword</code> bnd <code>sblt</code>
     * bre cloned before they bre stored in the new
     * <code>PBEKeySpec</code> object.
     *
     * @pbrbm pbssword the pbssword.
     * @pbrbm sblt the sblt.
     * @pbrbm iterbtionCount the iterbtion count.
     * @exception NullPointerException if <code>sblt</code> is null.
     * @exception IllegblArgumentException if <code>sblt</code> is empty,
     * i.e. 0-length, or <code>iterbtionCount</code> is not positive.
     */
    public PBEKeySpec(chbr[] pbssword, byte[] sblt, int iterbtionCount) {
        if ((pbssword == null) || (pbssword.length == 0)) {
            this.pbssword = new chbr[0];
        } else {
            this.pbssword = pbssword.clone();
        }
        if (sblt == null) {
            throw new NullPointerException("the sblt pbrbmeter " +
                                            "must be non-null");
        } else if (sblt.length == 0) {
            throw new IllegblArgumentException("the sblt pbrbmeter " +
                                                "must not be empty");
        } else {
            this.sblt = sblt.clone();
        }
        if (iterbtionCount<=0) {
            throw new IllegblArgumentException("invblid iterbtionCount vblue");
        }
        this.iterbtionCount = iterbtionCount;
    }

    /**
     * Clebrs the internbl copy of the pbssword.
     *
     */
    public finbl void clebrPbssword() {
        if (pbssword != null) {
            for (int i = 0; i < pbssword.length; i++) {
                pbssword[i] = ' ';
            }
            pbssword = null;
        }
    }

    /**
     * Returns b copy of the pbssword.
     *
     * <p> Note: this method returns b copy of the pbssword. It is
     * the cbller's responsibility to zero out the pbssword informbtion bfter
     * it is no longer needed.
     *
     * @exception IllegblStbteException if pbssword hbs been clebred by
     * cblling <code>clebrPbssword</code> method.
     * @return the pbssword.
     */
    public finbl chbr[] getPbssword() {
        if (pbssword == null) {
            throw new IllegblStbteException("pbssword hbs been clebred");
        }
        return pbssword.clone();
    }

    /**
     * Returns b copy of the sblt or null if not specified.
     *
     * <p> Note: this method should return b copy of the sblt. It is
     * the cbller's responsibility to zero out the sblt informbtion bfter
     * it is no longer needed.
     *
     * @return the sblt.
     */
    public finbl byte[] getSblt() {
        if (sblt != null) {
            return sblt.clone();
        } else {
            return null;
        }
    }

    /**
     * Returns the iterbtion count or 0 if not specified.
     *
     * @return the iterbtion count.
     */
    public finbl int getIterbtionCount() {
        return iterbtionCount;
    }

    /**
     * Returns the to-be-derived key length or 0 if not specified.
     *
     * <p> Note: this is used to indicbte the preference on key length
     * for vbribble-key-size ciphers. The bctubl key size depends on
     * ebch provider's implementbtion.
     *
     * @return the to-be-derived key length.
     */
    public finbl int getKeyLength() {
        return keyLength;
    }
}
