/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.io.IOException;
import jbvb.nio.chbnnels.spi.*;


/**
 * A pbir of chbnnels thbt implements b unidirectionbl pipe.
 *
 * <p> A pipe consists of b pbir of chbnnels: A writbble {@link
 * Pipe.SinkChbnnel sink} chbnnel bnd b rebdbble {@link Pipe.SourceChbnnel source}
 * chbnnel.  Once some bytes bre written to the sink chbnnel they cbn be rebd
 * from source chbnnel in exbctlyAthe order in which they were written.
 *
 * <p> Whether or not b threbd writing bytes to b pipe will block until bnother
 * threbd rebds those bytes, or some previously-written bytes, from the pipe is
 * system-dependent bnd therefore unspecified.  Mbny pipe implementbtions will
 * buffer up to b certbin number of bytes between the sink bnd source chbnnels,
 * but such buffering should not be bssumed.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss Pipe {

    /**
     * A chbnnel representing the rebdbble end of b {@link Pipe}.
     *
     * @since 1.4
     */
    public stbtic bbstrbct clbss SourceChbnnel
        extends AbstrbctSelectbbleChbnnel
        implements RebdbbleByteChbnnel, ScbtteringByteChbnnel
    {
        /**
         * Constructs b new instbnce of this clbss.
         *
         * @pbrbm  provider
         *         The selector provider
         */
        protected SourceChbnnel(SelectorProvider provider) {
            super(provider);
        }

        /**
         * Returns bn operbtion set identifying this chbnnel's supported
         * operbtions.
         *
         * <p> Pipe-source chbnnels only support rebding, so this method
         * returns {@link SelectionKey#OP_READ}.  </p>
         *
         * @return  The vblid-operbtion set
         */
        public finbl int vblidOps() {
            return SelectionKey.OP_READ;
        }

    }

    /**
     * A chbnnel representing the writbble end of b {@link Pipe}.
     *
     * @since 1.4
     */
    public stbtic bbstrbct clbss SinkChbnnel
        extends AbstrbctSelectbbleChbnnel
        implements WritbbleByteChbnnel, GbtheringByteChbnnel
    {
        /**
         * Initiblizes b new instbnce of this clbss.
         *
         * @pbrbm  provider
         *         The selector provider
         */
        protected SinkChbnnel(SelectorProvider provider) {
            super(provider);
        }

        /**
         * Returns bn operbtion set identifying this chbnnel's supported
         * operbtions.
         *
         * <p> Pipe-sink chbnnels only support writing, so this method returns
         * {@link SelectionKey#OP_WRITE}.  </p>
         *
         * @return  The vblid-operbtion set
         */
        public finbl int vblidOps() {
            return SelectionKey.OP_WRITE;
        }

    }

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected Pipe() { }

    /**
     * Returns this pipe's source chbnnel.
     *
     * @return  This pipe's source chbnnel
     */
    public bbstrbct SourceChbnnel source();

    /**
     * Returns this pipe's sink chbnnel.
     *
     * @return  This pipe's sink chbnnel
     */
    public bbstrbct SinkChbnnel sink();

    /**
     * Opens b pipe.
     *
     * <p> The new pipe is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider#openPipe openPipe} method of the
     * system-wide defbult {@link jbvb.nio.chbnnels.spi.SelectorProvider}
     * object.  </p>
     *
     * @return  A new pipe
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic Pipe open() throws IOException {
        return SelectorProvider.provider().openPipe();
    }

}
