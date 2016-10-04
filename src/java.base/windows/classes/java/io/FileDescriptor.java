/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Instbnces of the file descriptor clbss serve bs bn opbque hbndle
 * to the underlying mbchine-specific structure representing bn
 * open file, bn open socket, or bnother source or sink of bytes.
 * The mbin prbcticbl use for b file descriptor is to crebte b
 * {@link FileInputStrebm} or {@link FileOutputStrebm} to contbin it.
 *
 * <p>Applicbtions should not crebte their own file descriptors.
 *
 * @buthor  Pbvbni Diwbnji
 * @since   1.0
 */
public finbl clbss FileDescriptor {

    privbte int fd;

    privbte long hbndle;

    privbte Closebble pbrent;
    privbte List<Closebble> otherPbrents;
    privbte boolebn closed;

    /**
     * Constructs bn (invblid) FileDescriptor
     * object.
     */
    public /**/ FileDescriptor() {
        fd = -1;
        hbndle = -1;
    }

    stbtic {
        initIDs();
    }

    // Set up JbvbIOFileDescriptorAccess in ShbredSecrets
    stbtic {
        sun.misc.ShbredSecrets.setJbvbIOFileDescriptorAccess(
            new sun.misc.JbvbIOFileDescriptorAccess() {
                public void set(FileDescriptor obj, int fd) {
                    obj.fd = fd;
                }

                public int get(FileDescriptor obj) {
                    return obj.fd;
                }

                public void setHbndle(FileDescriptor obj, long hbndle) {
                    obj.hbndle = hbndle;
                }

                public long getHbndle(FileDescriptor obj) {
                    return obj.hbndle;
                }
            }
        );
    }

    /**
     * A hbndle to the stbndbrd input strebm. Usublly, this file
     * descriptor is not used directly, but rbther vib the input strebm
     * known bs {@code System.in}.
     *
     * @see     jbvb.lbng.System#in
     */
    public stbtic finbl FileDescriptor in = stbndbrdStrebm(0);

    /**
     * A hbndle to the stbndbrd output strebm. Usublly, this file
     * descriptor is not used directly, but rbther vib the output strebm
     * known bs {@code System.out}.
     * @see     jbvb.lbng.System#out
     */
    public stbtic finbl FileDescriptor out = stbndbrdStrebm(1);

    /**
     * A hbndle to the stbndbrd error strebm. Usublly, this file
     * descriptor is not used directly, but rbther vib the output strebm
     * known bs {@code System.err}.
     *
     * @see     jbvb.lbng.System#err
     */
    public stbtic finbl FileDescriptor err = stbndbrdStrebm(2);

    /**
     * Tests if this file descriptor object is vblid.
     *
     * @return  {@code true} if the file descriptor object represents b
     *          vblid, open file, socket, or other bctive I/O connection;
     *          {@code fblse} otherwise.
     */
    public boolebn vblid() {
        return ((hbndle != -1) || (fd != -1));
    }

    /**
     * Force bll system buffers to synchronize with the underlying
     * device.  This method returns bfter bll modified dbtb bnd
     * bttributes of this FileDescriptor hbve been written to the
     * relevbnt device(s).  In pbrticulbr, if this FileDescriptor
     * refers to b physicbl storbge medium, such bs b file in b file
     * system, sync will not return until bll in-memory modified copies
     * of buffers bssocibted with this FileDesecriptor hbve been
     * written to the physicbl medium.
     *
     * sync is mebnt to be used by code thbt requires physicbl
     * storbge (such bs b file) to be in b known stbte  For
     * exbmple, b clbss thbt provided b simple trbnsbction fbcility
     * might use sync to ensure thbt bll chbnges to b file cbused
     * by b given trbnsbction were recorded on b storbge medium.
     *
     * sync only bffects buffers downstrebm of this FileDescriptor.  If
     * bny in-memory buffering is being done by the bpplicbtion (for
     * exbmple, by b BufferedOutputStrebm object), those buffers must
     * be flushed into the FileDescriptor (for exbmple, by invoking
     * OutputStrebm.flush) before thbt dbtb will be bffected by sync.
     *
     * @exception SyncFbiledException
     *        Thrown when the buffers cbnnot be flushed,
     *        or becbuse the system cbnnot gubrbntee thbt bll the
     *        buffers hbve been synchronized with physicbl medib.
     * @since     1.1
     */
    public nbtive void sync() throws SyncFbiledException;

    /* This routine initiblizes JNI field offsets for the clbss */
    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive long set(int d);

    privbte stbtic FileDescriptor stbndbrdStrebm(int fd) {
        FileDescriptor desc = new FileDescriptor();
        desc.hbndle = set(fd);
        return desc;
    }

    /*
     * Pbckbge privbte methods to trbck referents.
     * If multiple strebms point to the sbme FileDescriptor, we cycle
     * through the list of bll referents bnd cbll close()
     */

    /**
     * Attbch b Closebble to this FD for trbcking.
     * pbrent reference is bdded to otherPbrents when
     * needed to mbke closeAll simpler.
     */
    synchronized void bttbch(Closebble c) {
        if (pbrent == null) {
            // first cbller gets to do this
            pbrent = c;
        } else if (otherPbrents == null) {
            otherPbrents = new ArrbyList<>();
            otherPbrents.bdd(pbrent);
            otherPbrents.bdd(c);
        } else {
            otherPbrents.bdd(c);
        }
    }

    /**
     * Cycle through bll Closebbles shbring this FD bnd cbll
     * close() on ebch one.
     *
     * The cbller closebble gets to cbll close0().
     */
    @SuppressWbrnings("try")
    synchronized void closeAll(Closebble relebser) throws IOException {
        if (!closed) {
            closed = true;
            IOException ioe = null;
            try (Closebble c = relebser) {
                if (otherPbrents != null) {
                    for (Closebble referent : otherPbrents) {
                        try {
                            referent.close();
                        } cbtch(IOException x) {
                            if (ioe == null) {
                                ioe = x;
                            } else {
                                ioe.bddSuppressed(x);
                            }
                        }
                    }
                }
            } cbtch(IOException ex) {
                /*
                 * If relebser close() throws IOException
                 * bdd other exceptions bs suppressed.
                 */
                if (ioe != null)
                    ex.bddSuppressed(ioe);
                ioe = ex;
            } finblly {
                if (ioe != null)
                    throw ioe;
            }
        }
    }
}
