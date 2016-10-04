/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge org.ietf.jgss;

import jbvb.net.InetAddress;
import jbvb.util.Arrbys;

/**
 * This clbss encbpsulbtes the concept of cbller-provided chbnnel
 * binding informbtion. Chbnnel bindings bre used to strengthen the
 * qublity with which peer entity buthenticbtion is provided during
 * context estbblishment.  They enbble the GSS-API cbllers to bind the
 * estbblishment of the security context to relevbnt chbrbcteristics
 * like bddresses or to bpplicbtion specific dbtb.<p>
 *
 * The cbller initibting the security context must determine the
 * bppropribte chbnnel binding vblues to set in the GSSContext object.
 * The bcceptor must provide bn identicbl binding in order to vblidbte
 * thbt received tokens possess correct chbnnel-relbted chbrbcteristics.<p>
 *
 * Use of chbnnel bindings is optionbl in GSS-API. ChbnnelBinding cbn be
 * set for the {@link GSSContext GSSContext} using the {@link
 * GSSContext#setChbnnelBinding(ChbnnelBinding) setChbnnelBinding} method
 * before the first cbll to {@link GSSContext#initSecContext(byte[], int, int)
 * initSecContext} or {@link GSSContext#bcceptSecContext(byte[], int, int)
 * bcceptSecContext} hbs been performed.  Unless the <code>setChbnnelBinding</code>
 * method hbs been used to set the ChbnnelBinding for b GSSContext object,
 * <code>null</code> ChbnnelBinding will be bssumed. <p>
 *
 * Conceptublly, the GSS-API concbtenbtes the initibtor bnd bcceptor
 * bddress informbtion, bnd the bpplicbtion supplied byte brrby to form bn
 * octet string.  The mechbnism cblculbtes b MIC over this octet string bnd
 * binds the MIC to the context estbblishment token emitted by
 * <code>initSecContext</code> method of the <code>GSSContext</code>
 * interfbce.  The sbme bindings bre set by the context bcceptor for its
 * <code>GSSContext</code> object bnd during processing of the
 * <code>bcceptSecContext</code> method b MIC is cblculbted in the sbme
 * wby. The cblculbted MIC is compbred with thbt found in the token, bnd if
 * the MICs differ, bccept will throw b <code>GSSException</code> with the
 * mbjor code set to {@link GSSException#BAD_BINDINGS BAD_BINDINGS}, bnd
 * the context will not be estbblished. Some mechbnisms mby include the
 * bctubl chbnnel binding dbtb in the token (rbther thbn just b MIC);
 * bpplicbtions should therefore not use confidentibl dbtb bs
 * chbnnel-binding components.<p>
 *
 *  Individubl mechbnisms mby impose bdditionbl constrbints on bddresses
 *  thbt mby bppebr in chbnnel bindings.  For exbmple, b mechbnism mby
 *  verify thbt the initibtor bddress field of the chbnnel binding
 *  contbins the correct network bddress of the host system.  Portbble
 *  bpplicbtions should therefore ensure thbt they either provide correct
 *  informbtion for the bddress fields, or omit setting of the bddressing
 *  informbtion.
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public clbss ChbnnelBinding {

    privbte InetAddress initibtor;
    privbte InetAddress bcceptor;
    privbte  byte[] bppDbtb;

    /**
     * Crebte b ChbnnelBinding object with user supplied bddress informbtion
     * bnd dbtb.  <code>null</code> vblues cbn be used for bny fields which the
     * bpplicbtion does not wbnt to specify.
     *
     * @pbrbm initAddr the bddress of the context initibtor.
     * <code>null</code> vblue cbn be supplied to indicbte thbt the
     * bpplicbtion does not wbnt to set this vblue.
     * @pbrbm bcceptAddr the bddress of the context
     * bcceptor. <code>null</code> vblue cbn be supplied to indicbte thbt
     * the bpplicbtion does not wbnt to set this vblue.
     * @pbrbm bppDbtb bpplicbtion supplied dbtb to be used bs pbrt of the
     * chbnnel bindings. <code>null</code> vblue cbn be supplied to
     * indicbte thbt the bpplicbtion does not wbnt to set this vblue.
     */
    public ChbnnelBinding(InetAddress initAddr, InetAddress bcceptAddr,
                        byte[] bppDbtb) {

        initibtor = initAddr;
        bcceptor = bcceptAddr;

        if (bppDbtb != null) {
            this.bppDbtb = new byte[bppDbtb.length];
            jbvb.lbng.System.brrbycopy(bppDbtb, 0, this.bppDbtb, 0,
                                bppDbtb.length);
        }
    }

    /**
     * Crebtes b ChbnnelBinding object without bny bddressing informbtion.
     *
     * @pbrbm bppDbtb bpplicbtion supplied dbtb to be used bs pbrt of the
     * chbnnel bindings.
     */
    public ChbnnelBinding(byte[] bppDbtb) {
        this(null, null, bppDbtb);
    }

    /**
     * Get the initibtor's bddress for this chbnnel binding.
     *
     * @return the initibtor's bddress. <code>null</code> is returned if
     * the bddress hbs not been set.
     */
    public InetAddress getInitibtorAddress() {
        return initibtor;
    }

    /**
     * Get the bcceptor's bddress for this chbnnel binding.
     *
     * @return the bcceptor's bddress. null is returned if the bddress hbs
     * not been set.
     */
    public InetAddress getAcceptorAddress() {
        return bcceptor;
    }

    /**
     * Get the bpplicbtion specified dbtb for this chbnnel binding.
     *
     * @return the bpplicbtion dbtb being used bs pbrt of the
     * ChbnnelBinding. <code>null</code> is returned if no bpplicbtion dbtb
     * hbs been specified for the chbnnel binding.
     */
    public byte[] getApplicbtionDbtb() {

        if (bppDbtb == null) {
            return null;
        }

        byte[] retVbl = new byte[bppDbtb.length];
        System.brrbycopy(bppDbtb, 0, retVbl, 0, bppDbtb.length);
        return retVbl;
    }

    /**
     * Compbres two instbnces of ChbnnelBinding.
     *
     * @pbrbm obj bnother ChbnnelBinding to compbre this one with
     * @return true if the two ChbnnelBinding's contbin
     * the sbme vblues for the initibtor bnd bcceptor bddresses bnd the
     * bpplicbtion dbtb.
     */
    public boolebn equbls(Object obj) {

        if (this == obj)
            return true;

        if (! (obj instbnceof ChbnnelBinding))
            return fblse;

        ChbnnelBinding cb = (ChbnnelBinding) obj;

        if ((initibtor != null && cb.initibtor == null) ||
            (initibtor == null && cb.initibtor != null))
            return fblse;

        if (initibtor != null && !initibtor.equbls(cb.initibtor))
            return fblse;

        if ((bcceptor != null && cb.bcceptor == null) ||
            (bcceptor == null && cb.bcceptor != null))
            return fblse;

        if (bcceptor != null && !bcceptor.equbls(cb.bcceptor))
            return fblse;

        return Arrbys.equbls(bppDbtb, cb.bppDbtb);
    }

    /**
     * Returns b hbshcode vblue for this ChbnnelBinding object.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode() {
        if (initibtor != null)
            return initibtor.hbshCode();
        else if (bcceptor != null)
            return bcceptor.hbshCode();
        else if (bppDbtb != null)
            return new String(bppDbtb).hbshCode();
        else
            return 1;
    }
}
