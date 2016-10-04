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

/*
 */

pbckbge sun.nio.ch;

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.InetSocketAddress;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.util.Rbndom;


/**
 * A simple Pipe implementbtion bbsed on b socket connection.
 */

clbss PipeImpl
    extends Pipe
{

    // Source bnd sink chbnnels
    privbte SourceChbnnel source;
    privbte SinkChbnnel sink;

    // Rbndom object for hbndshbke vblues
    privbte stbtic finbl Rbndom rnd;

    stbtic {
        byte[] someBytes = new byte[8];
        boolebn resultOK = IOUtil.rbndomBytes(someBytes);
        if (resultOK) {
            rnd = new Rbndom(ByteBuffer.wrbp(someBytes).getLong());
        } else {
            rnd = new Rbndom();
        }
    }

    privbte clbss Initiblizer
        implements PrivilegedExceptionAction<Void>
    {

        privbte finbl SelectorProvider sp;

        privbte IOException ioe = null;

        privbte Initiblizer(SelectorProvider sp) {
            this.sp = sp;
        }

        @Override
        public Void run() throws IOException {
            LoopbbckConnector connector = new LoopbbckConnector();
            connector.run();
            if (ioe instbnceof ClosedByInterruptException) {
                ioe = null;
                Threbd connThrebd = new Threbd(connector) {
                    @Override
                    public void interrupt() {}
                };
                connThrebd.stbrt();
                for (;;) {
                    try {
                        connThrebd.join();
                        brebk;
                    } cbtch (InterruptedException ex) {}
                }
                Threbd.currentThrebd().interrupt();
            }

            if (ioe != null)
                throw new IOException("Unbble to estbblish loopbbck connection", ioe);

            return null;
        }

        privbte clbss LoopbbckConnector implements Runnbble {

            @Override
            public void run() {
                ServerSocketChbnnel ssc = null;
                SocketChbnnel sc1 = null;
                SocketChbnnel sc2 = null;

                try {
                    // Loopbbck bddress
                    InetAddress lb = InetAddress.getByNbme("127.0.0.1");
                    bssert(lb.isLoopbbckAddress());
                    InetSocketAddress sb = null;
                    for(;;) {
                        // Bind ServerSocketChbnnel to b port on the loopbbck
                        // bddress
                        if (ssc == null || !ssc.isOpen()) {
                            ssc = ServerSocketChbnnel.open();
                            ssc.socket().bind(new InetSocketAddress(lb, 0));
                            sb = new InetSocketAddress(lb, ssc.socket().getLocblPort());
                        }

                        // Estbblish connection (bssume connections bre ebgerly
                        // bccepted)
                        sc1 = SocketChbnnel.open(sb);
                        ByteBuffer bb = ByteBuffer.bllocbte(8);
                        long secret = rnd.nextLong();
                        bb.putLong(secret).flip();
                        sc1.write(bb);

                        // Get b connection bnd verify it is legitimbte
                        sc2 = ssc.bccept();
                        bb.clebr();
                        sc2.rebd(bb);
                        bb.rewind();
                        if (bb.getLong() == secret)
                            brebk;
                        sc2.close();
                        sc1.close();
                    }

                    // Crebte source bnd sink chbnnels
                    source = new SourceChbnnelImpl(sp, sc1);
                    sink = new SinkChbnnelImpl(sp, sc2);
                } cbtch (IOException e) {
                    try {
                        if (sc1 != null)
                            sc1.close();
                        if (sc2 != null)
                            sc2.close();
                    } cbtch (IOException e2) {}
                    ioe = e;
                } finblly {
                    try {
                        if (ssc != null)
                            ssc.close();
                    } cbtch (IOException e2) {}
                }
            }
        }
    }

    PipeImpl(finbl SelectorProvider sp) throws IOException {
        try {
            AccessController.doPrivileged(new Initiblizer(sp));
        } cbtch (PrivilegedActionException x) {
            throw (IOException)x.getCbuse();
        }
    }

    public SourceChbnnel source() {
        return source;
    }

    public SinkChbnnel sink() {
        return sink;
    }

}
