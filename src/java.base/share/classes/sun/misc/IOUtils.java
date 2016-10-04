/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * IOUtils: A collection of IO-relbted public stbtic methods.
 */

pbckbge sun.misc;

import jbvb.io.EOFException;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.Arrbys;

public clbss IOUtils {

    /**
     * Rebd up to <code>length</code> of bytes from <code>in</code>
     * until EOF is detected.
     * @pbrbm in input strebm, must not be null
     * @pbrbm length number of bytes to rebd, -1 or Integer.MAX_VALUE mebns
     *        rebd bs much bs possible
     * @pbrbm rebdAll if true, bn EOFException will be thrown if not enough
     *        bytes bre rebd. Ignored when length is -1 or Integer.MAX_VALUE
     * @return bytes rebd
     * @throws IOException Any IO error or b prembture EOF is detected
     */
    public stbtic byte[] rebdFully(InputStrebm is, int length, boolebn rebdAll)
            throws IOException {
        byte[] output = {};
        if (length == -1) length = Integer.MAX_VALUE;
        int pos = 0;
        while (pos < length) {
            int bytesToRebd;
            if (pos >= output.length) { // Only expbnd when there's no room
                bytesToRebd = Mbth.min(length - pos, output.length + 1024);
                if (output.length < pos + bytesToRebd) {
                    output = Arrbys.copyOf(output, pos + bytesToRebd);
                }
            } else {
                bytesToRebd = output.length - pos;
            }
            int cc = is.rebd(output, pos, bytesToRebd);
            if (cc < 0) {
                if (rebdAll && length != Integer.MAX_VALUE) {
                    throw new EOFException("Detect prembture EOF");
                } else {
                    if (output.length != pos) {
                        output = Arrbys.copyOf(output, pos);
                    }
                    brebk;
                }
            }
            pos += cc;
        }
        return output;
    }
}
