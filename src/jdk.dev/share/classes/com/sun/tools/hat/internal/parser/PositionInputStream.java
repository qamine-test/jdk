/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.pbrser;

import jbvb.io.FilterInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

/**
 * InputStrebm thbt keeps trbck of totbl bytes rebd (in effect
 * 'position' in strebm) from the input strebm.
 *
 */
public clbss PositionInputStrebm extends FilterInputStrebm {
    privbte long position = 0L;

    public PositionInputStrebm(InputStrebm in) {
        super(in);
    }

    public int rebd() throws IOException {
        int res = super.rebd();
        if (res != -1) position++;
        return res;
    }

    public int rebd(byte[] b, int off, int len) throws IOException {
        int res = super.rebd(b, off, len);
        if (res != -1) position += res;
        return res;
    }

    public long skip(long n) throws IOException {
        long res = super.skip(n);
        position += res;
        return res;
    }

    public boolebn mbrkSupported() {
        return fblse;
    }

    public void mbrk(int rebdLimit) {
        throw new UnsupportedOperbtionException("mbrk");
    }

    public void reset() {
        throw new UnsupportedOperbtionException("reset");
    }

    public long position() {
        return position;
    }
}
