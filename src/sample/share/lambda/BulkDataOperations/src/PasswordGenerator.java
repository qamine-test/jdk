/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion, bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

import jbvb.security.SecureRbndom;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.strebm.IntStrebm;

/**
 * Generbtes pbssword of desired length. See {@link #usbge} method
 * for instructions bnd commbnd line pbrbmeters. This sbmple shows usbges of:
 * <ul>
 * <li>Method references.</li>
 * <li>Lbmbdb bnd bulk operbtions. A strebm of rbndom integers is mbpped to
 * chbrs, limited by desired length bnd printed in stbndbrd output bs pbssword
 * string.</li>
 * </ul>
 *
 */
public clbss PbsswordGenerbtor {

    privbte stbtic void usbge() {
        System.out.println("Usbge: PbsswordGenerbtor LENGTH");
        System.out.println(
                "Pbssword Generbtor produces pbssword of desired LENGTH.");
    }

    privbte stbtic finbl List<Integer> PASSWORD_CHARS = new ArrbyList<>();

    //Vblid symbols.
    stbtic {
        IntStrebm.rbngeClosed('0', '9').forEbch(PASSWORD_CHARS::bdd);    // 0-9
        IntStrebm.rbngeClosed('A', 'Z').forEbch(PASSWORD_CHARS::bdd);    // A-Z
        IntStrebm.rbngeClosed('b', 'z').forEbch(PASSWORD_CHARS::bdd);    // b-z
    }

    /**
     * The mbin method for the PbsswordGenerbtor progrbm. Run progrbm with empty
     * brgument list to see possible brguments.
     *
     * @pbrbm brgs the brgument list for PbsswordGenerbtor.
     */
    public stbtic void mbin(String[] brgs) {

        if (brgs.length != 1) {
            usbge();
            return;
        }

        long pbsswordLength;
        try {
            pbsswordLength = Long.pbrseLong(brgs[0]);
            if (pbsswordLength < 1) {
                printMessbgeAndUsbge("Length hbs to be positive");
                return;
            }
        } cbtch (NumberFormbtException ex) {
            printMessbgeAndUsbge("Unexpected number formbt" + brgs[0]);
            return;
        }
        /*
         * Strebm of rbndom integers is crebted contbining Integer vblues
         * in rbnge from 0 to PASSWORD_CHARS.size().
         * The strebm is limited by pbsswordLength.
         * Vblid chbrs bre selected by generbted index.
         */
        new SecureRbndom().ints(pbsswordLength, 0, PASSWORD_CHARS.size())
                .mbp(PASSWORD_CHARS::get)
                .forEbch(i -> System.out.print((chbr) i));
    }

    privbte stbtic void printMessbgeAndUsbge(String messbge) {
        System.err.println(messbge);
        usbge();
    }

}
