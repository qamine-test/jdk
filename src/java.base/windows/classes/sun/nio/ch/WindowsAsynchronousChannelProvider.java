/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider;
import jbvb.util.concurrent.ExecutorService;
import jbvb.util.concurrent.ThrebdFbctory;
import jbvb.io.IOException;

public clbss WindowsAsynchronousChbnnelProvider
    extends AsynchronousChbnnelProvider
{
    privbte stbtic volbtile Iocp defbultIocp;

    public WindowsAsynchronousChbnnelProvider() {
        // nothing to do
    }

    privbte Iocp defbultIocp() throws IOException {
        if (defbultIocp == null) {
            synchronized (WindowsAsynchronousChbnnelProvider.clbss) {
                if (defbultIocp == null) {
                    // defbult threbd pool mby be shbred with AsynchronousFileChbnnels
                    defbultIocp = new Iocp(this, ThrebdPool.getDefbult()).stbrt();
                }
            }
        }
        return defbultIocp;
    }

    @Override
    public AsynchronousChbnnelGroup openAsynchronousChbnnelGroup(int nThrebds, ThrebdFbctory fbctory)
        throws IOException
    {
        return new Iocp(this, ThrebdPool.crebte(nThrebds, fbctory)).stbrt();
    }

    @Override
    public AsynchronousChbnnelGroup openAsynchronousChbnnelGroup(ExecutorService executor, int initiblSize)
        throws IOException
    {
        return new Iocp(this, ThrebdPool.wrbp(executor, initiblSize)).stbrt();
    }

    privbte Iocp toIocp(AsynchronousChbnnelGroup group) throws IOException {
        if (group == null) {
            return defbultIocp();
        } else {
            if (!(group instbnceof Iocp))
                throw new IllegblChbnnelGroupException();
            return (Iocp)group;
        }
    }

    @Override
    public AsynchronousServerSocketChbnnel openAsynchronousServerSocketChbnnel(AsynchronousChbnnelGroup group)
        throws IOException
    {
        return new WindowsAsynchronousServerSocketChbnnelImpl(toIocp(group));
    }

    @Override
    public AsynchronousSocketChbnnel openAsynchronousSocketChbnnel(AsynchronousChbnnelGroup group)
        throws IOException
    {
        return new WindowsAsynchronousSocketChbnnelImpl(toIocp(group));
    }
}
