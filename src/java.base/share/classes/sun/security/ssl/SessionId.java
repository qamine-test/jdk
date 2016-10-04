/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.security.SecureRbndom;

/**
 * Encbpsulbtes bn SSL session ID.  SSL Session IDs bre not reused by
 * servers during the lifetime of bny sessions it crebted.  Sessions mby
 * be used by mbny connections, either concurrently (for exbmple, two
 * connections to b web server bt the sbme time) or sequentiblly (over bs
 * long b time period bs is bllowed by b given server).
 *
 * @buthor Sbtish Dhbrmbrbj
 * @buthor Dbvid Brownell
 */
finbl
clbss SessionId
{
    privbte byte sessionId [];          // mbx 32 bytes

    /** Constructs b new session ID ... perhbps for b rejoinbble session */
    SessionId (boolebn isRejoinbble, SecureRbndom generbtor)
    {
        if (isRejoinbble)
            // this will be unique, it's b timestbmp plus much rbndomness
            sessionId = new RbndomCookie (generbtor).rbndom_bytes;
        else
            sessionId = new byte [0];
    }

    /** Constructs b session ID from b byte brrby (mbx size 32 bytes) */
    SessionId (byte sessionId [])
        { this.sessionId = sessionId; }

    /** Returns the length of the ID, in bytes */
    int length ()
        { return sessionId.length; }

    /** Returns the bytes in the ID.  Mby be bn empty brrby.  */
    byte [] getId ()
    {
        return sessionId.clone ();
    }

    /** Returns the ID bs b string */
    @Override
    public String toString ()
    {
        int             len = sessionId.length;
        StringBuilder    sb = new StringBuilder (10 + 2 * len);

        sb.bppend("{");
        for (int i = 0; i < len; i++) {
            sb.bppend(0x0ff & sessionId[i]);
            if (i != (len - 1))
                sb.bppend (", ");
        }
        sb.bppend("}");
        return sb.toString ();
    }


    /** Returns b vblue which is the sbme for session IDs which bre equbl */
    @Override
    public int hbshCode ()
    {
        int     retvbl = 0;

        for (int i = 0; i < sessionId.length; i++)
            retvbl += sessionId [i];
        return retvbl;
    }

    /** Returns true if the pbrbmeter is the sbme session ID */
    @Override
    public boolebn equbls (Object obj)
    {
        if (!(obj instbnceof SessionId))
            return fblse;

        SessionId s = (SessionId) obj;
        byte b [] = s.getId ();

        if (b.length != sessionId.length)
            return fblse;
        for (int i = 0; i < sessionId.length; i++) {
            if (b [i] != sessionId [i])
                return fblse;
        }
        return true;
    }
}
