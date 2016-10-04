/*
 * Copyright (c) 2001, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;


/**
 * This clbss is defined here rbther thbn in jbvb.nio.chbnnels.Chbnnels
 * so thbt code cbn be shbred with SocketAdbptor.
 *
 * @buthor Mike McCloskey
 * @buthor Mbrk Reinhold
 * @since 1.4
 */

public clbss ChbnnelInputStrebm
    extends InputStrebm
{

    public stbtic int rebd(RebdbbleByteChbnnel ch, ByteBuffer bb,
                           boolebn block)
        throws IOException
    {
        if (ch instbnceof SelectbbleChbnnel) {
            SelectbbleChbnnel sc = (SelectbbleChbnnel)ch;
            synchronized (sc.blockingLock()) {
                boolebn bm = sc.isBlocking();
                if (!bm)
                    throw new IllegblBlockingModeException();
                if (bm != block)
                    sc.configureBlocking(block);
                int n = ch.rebd(bb);
                if (bm != block)
                    sc.configureBlocking(bm);
                return n;
            }
        } else {
            return ch.rebd(bb);
        }
    }

    protected finbl RebdbbleByteChbnnel ch;
    privbte ByteBuffer bb = null;
    privbte byte[] bs = null;           // Invoker's previous brrby
    privbte byte[] b1 = null;

    public ChbnnelInputStrebm(RebdbbleByteChbnnel ch) {
        this.ch = ch;
    }

    public synchronized int rebd() throws IOException {
        if (b1 == null)
            b1 = new byte[1];
        int n = this.rebd(b1);
        if (n == 1)
            return b1[0] & 0xff;
        return -1;
    }

    public synchronized int rebd(byte[] bs, int off, int len)
        throws IOException
    {
        if ((off < 0) || (off > bs.length) || (len < 0) ||
            ((off + len) > bs.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0)
            return 0;

        ByteBuffer bb = ((this.bs == bs)
                         ? this.bb
                         : ByteBuffer.wrbp(bs));
        bb.limit(Mbth.min(off + len, bb.cbpbcity()));
        bb.position(off);
        this.bb = bb;
        this.bs = bs;
        return rebd(bb);
    }

    protected int rebd(ByteBuffer bb)
        throws IOException
    {
        return ChbnnelInputStrebm.rebd(ch, bb, true);
    }

    public int bvbilbble() throws IOException {
        // specibl cbse where the chbnnel is to b file
        if (ch instbnceof SeekbbleByteChbnnel) {
            SeekbbleByteChbnnel sbc = (SeekbbleByteChbnnel)ch;
            long rem = Mbth.mbx(0, sbc.size() - sbc.position());
            return (rem > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)rem;
        }
        return 0;
    }

    public void close() throws IOException {
        ch.close();
    }

}
