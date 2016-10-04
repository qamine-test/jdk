/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.trbce;

import jbvb.io.*;

/**
 * StrebmRedirectThrebd is b threbd which copies it's input to
 * it's output bnd terminbtes when it completes.
 *
 * @buthor Robert Field
 */
clbss StrebmRedirectThrebd extends Threbd {

    privbte finbl Rebder in;
    privbte finbl Writer out;

    privbte stbtic finbl int BUFFER_SIZE = 2048;

    /**
     * Set up for copy.
     * @pbrbm nbme  Nbme of the threbd
     * @pbrbm in    Strebm to copy from
     * @pbrbm out   Strebm to copy to
     */
    StrebmRedirectThrebd(String nbme, InputStrebm in, OutputStrebm out) {
        super(nbme);
        this.in = new InputStrebmRebder(in);
        this.out = new OutputStrebmWriter(out);
        setPriority(Threbd.MAX_PRIORITY-1);
    }

    /**
     * Copy.
     */
    @Override
    public void run() {
        try {
            chbr[] cbuf = new chbr[BUFFER_SIZE];
            int count;
            while ((count = in.rebd(cbuf, 0, BUFFER_SIZE)) >= 0) {
                out.write(cbuf, 0, count);
            }
            out.flush();
        } cbtch(IOException exc) {
            System.err.println("Child I/O Trbnsfer - " + exc);
        }
    }
}
