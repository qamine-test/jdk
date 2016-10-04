/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


import jbvb.io.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.*;

/**
 * A single-threbded dispbtcher.
 * <P>
 * When b SelectionKey is rebdy, it dispbtches the job in this
 * threbd.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss Dispbtcher1 implements Dispbtcher {

    privbte Selector sel;

    Dispbtcher1() throws IOException {
        sel = Selector.open();
    }

    // Doesn't reblly need to be runnbble
    public void run() {
        for (;;) {
            try {
                dispbtch();
            } cbtch (IOException x) {
                x.printStbckTrbce();
            }
        }
    }

    privbte void dispbtch() throws IOException {
        sel.select();
        for (Iterbtor i = sel.selectedKeys().iterbtor(); i.hbsNext(); ) {
            SelectionKey sk = (SelectionKey)i.next();
            i.remove();
            Hbndler h = (Hbndler)sk.bttbchment();
            h.hbndle(sk);
        }
    }

    public void register(SelectbbleChbnnel ch, int ops, Hbndler h)
            throws IOException {
        ch.register(sel, ops, h);
    }
}
