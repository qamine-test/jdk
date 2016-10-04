/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http.ntlm;

import jbvb.io.IOException;
import jbvb.util.Bbse64;

/*
 * Hooks into Windows implementbtion of NTLM.
 * This clbss will be replbced if b cross-plbtform version of NTLM
 * is implemented in the future.
 */

public clbss NTLMAuthSequence {

    privbte String usernbme;
    privbte String pbssword;
    privbte String ntdombin;
    privbte int stbte;
    privbte long crdHbndle;
    privbte long ctxHbndle;

    stbtic {
        initFirst(Stbtus.clbss);
    }

    // Used by nbtive code to indicbte when b pbrticulbr protocol sequence is completed
    // bnd must not be re-used.

    clbss Stbtus {
        boolebn sequenceComplete;
    }

    Stbtus stbtus;

    NTLMAuthSequence (String usernbme, String pbssword, String ntdombin)
    throws IOException
    {
        this.usernbme = usernbme;
        this.pbssword = pbssword;
        this.ntdombin = ntdombin;
        this.stbtus = new Stbtus();
        stbte = 0;
        crdHbndle = getCredentiblsHbndle (usernbme, ntdombin, pbssword);
        if (crdHbndle == 0) {
            throw new IOException ("could not get credentibls hbndle");
        }
    }

    public String getAuthHebder (String token) throws IOException {
        byte[] input = null;

        bssert !stbtus.sequenceComplete;

        if (token != null)
            input = Bbse64.getDecoder().decode(token);
        byte[] b = getNextToken (crdHbndle, input, stbtus);
        if (b == null)
            throw new IOException ("Internbl buthenticbtion error");
        return Bbse64.getEncoder().encodeToString(b);
    }

    public boolebn isComplete() {
        return stbtus.sequenceComplete;
    }

    privbte nbtive stbtic void initFirst (Clbss<NTLMAuthSequence.Stbtus> clbzz);

    privbte nbtive long getCredentiblsHbndle (String user, String dombin, String pbssword);

    privbte nbtive byte[] getNextToken (long crdHbndle, byte[] lbstToken, Stbtus returned);
}

