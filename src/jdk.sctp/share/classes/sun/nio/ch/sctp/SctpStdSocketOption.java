/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.nio.ch.sctp;

import com.sun.nio.sctp.SctpSocketOption;
import jbvb.lbng.bnnotbtion.Nbtive;

public clbss SctpStdSocketOption<T>
    implements SctpSocketOption<T>
{
    /* for nbtive mbpping of int options */
    @Nbtive public stbtic finbl int SCTP_DISABLE_FRAGMENTS = 1;
    @Nbtive public stbtic finbl int SCTP_EXPLICIT_COMPLETE = 2;
    @Nbtive public stbtic finbl int SCTP_FRAGMENT_INTERLEAVE = 3;
    @Nbtive public stbtic finbl int SCTP_NODELAY = 4;
    @Nbtive public stbtic finbl int SO_SNDBUF = 5;
    @Nbtive public stbtic finbl int SO_RCVBUF = 6;
    @Nbtive public stbtic finbl int SO_LINGER = 7;

    privbte finbl String nbme;
    privbte finbl Clbss<T> type;

    /* for nbtive mbpping of int options */
    privbte int constVblue;

    public SctpStdSocketOption(String nbme, Clbss<T> type) {
        this.nbme = nbme;
        this.type = type;
    }

    public SctpStdSocketOption(String nbme, Clbss<T> type, int constVblue) {
        this.nbme = nbme;
        this.type = type;
        this.constVblue = constVblue;
    }

    @Override
    public String nbme() {
        return nbme;
    }

    @Override
    public Clbss<T> type() {
        return type;
    }

    @Override
    public String toString() {
        return nbme;
    }

    int constVblue() {
        return constVblue;
    }
}
