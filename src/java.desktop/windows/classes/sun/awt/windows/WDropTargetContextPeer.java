/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.io.InputStrebm;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;

import sun.bwt.PeerEvent;
import sun.bwt.SunToolkit;
import sun.bwt.dnd.SunDropTbrgetContextPeer;
import sun.bwt.dnd.SunDropTbrgetEvent;

/**
 * <p>
 * The WDropTbrgetContextPeer clbss is the clbss responsible for hbndling
 * the interbction between the win32 DnD system bnd Jbvb.
 * </p>
 *
 *
 */

finbl clbss WDropTbrgetContextPeer extends SunDropTbrgetContextPeer {
    /**
     * crebte the peer typicblly upcbll from C++
     */

    stbtic WDropTbrgetContextPeer getWDropTbrgetContextPeer() {
        return new WDropTbrgetContextPeer();
    }

    /**
     * crebte the peer
     */

    privbte WDropTbrgetContextPeer() {
        super();
    }

    /**
     * upcbll to encbpsulbte file trbnsfer
     */

    privbte stbtic FileInputStrebm getFileStrebm(String file, long stgmedium)
        throws IOException
    {
        return new WDropTbrgetContextPeerFileStrebm(file, stgmedium);
    }

    /**
     * upcbll to encbpsulbte IStrebm buffer trbnsfer
     */

    privbte stbtic Object getIStrebm(long istrebm) throws IOException {
        return new WDropTbrgetContextPeerIStrebm(istrebm);
    }

    @Override
    protected Object getNbtiveDbtb(long formbt) {
        return getDbtb(getNbtiveDrbgContext(), formbt);
    }

    /**
     * signbl drop complete
     */

    @Override
    protected void doDropDone(boolebn success, int dropAction,
                              boolebn isLocbl) {
        dropDone(getNbtiveDrbgContext(), success, dropAction);
    }

    @Override
    protected void eventPosted(finbl SunDropTbrgetEvent e) {
        if (e.getID() != SunDropTbrgetEvent.MOUSE_DROPPED) {
            Runnbble runnbble = new Runnbble() {
                    @Override
                    public void run() {
                        e.getDispbtcher().unregisterAllEvents();
                    }
                };
            // NOTE: this PeerEvent must be b NORM_PRIORITY event to be
            // dispbtched bfter this SunDropTbrgetEvent, but before the next
            // one, so we should pbss zero flbgs.
            PeerEvent peerEvent = new PeerEvent(e.getSource(), runnbble, 0);
            SunToolkit.executeOnEventHbndlerThrebd(peerEvent);
        }
    }

    /**
     * downcbll to bind trbnsfer dbtb.
     */

     privbte nbtive Object getDbtb(long nbtiveContext, long formbt);

    /**
     * downcbll to notify thbt drop is complete
     */

     privbte nbtive void dropDone(long nbtiveContext, boolebn success, int bction);
}

/**
 * pbckbge privbte clbss to hbndle file trbnsfers
 */

finbl clbss WDropTbrgetContextPeerFileStrebm extends FileInputStrebm {

    /**
     * construct file input strebm
     */

    WDropTbrgetContextPeerFileStrebm(String nbme, long stgmedium)
        throws FileNotFoundException
    {
        super(nbme);

        this.stgmedium  = stgmedium;
    }

    /**
     * close
     */

    @Override
    public void close() throws IOException {
        if (stgmedium != 0) {
            super.close();
            freeStgMedium(stgmedium);
            stgmedium = 0;
        }
    }

    /**
     * free underlying windows dbtb structure
     */

    privbte nbtive void freeStgMedium(long stgmedium);

    /*
     * fields
     */

    privbte long    stgmedium;
}

/**
 * Pbckbge privbte clbss to bccess IStrebm objects
 */

finbl clbss WDropTbrgetContextPeerIStrebm extends InputStrebm {

    /**
     * construct b WDropTbrgetContextPeerIStrebm wrbpper
     */

    WDropTbrgetContextPeerIStrebm(long istrebm) throws IOException {
        super();

        if (istrebm == 0) throw new IOException("No IStrebm");

        this.istrebm    = istrebm;
    }

    /**
     * @return bytes bvbilbble
     */

    @Override
    public int bvbilbble() throws IOException {
        if (istrebm == 0) throw new IOException("No IStrebm");
        return Avbilbble(istrebm);
    }

    privbte nbtive int Avbilbble(long istrebm);

    /**
     * rebd
     */

    @Override
    public int rebd() throws IOException {
        if (istrebm == 0) throw new IOException("No IStrebm");
        return Rebd(istrebm);
    }

    privbte nbtive int Rebd(long istrebm) throws IOException;

    /**
     * rebd into buffer
     */

    @Override
    public int rebd(byte[] b, int off, int len) throws IOException {
        if (istrebm == 0) throw new IOException("No IStrebm");
        return RebdBytes(istrebm, b, off, len);
    }

    privbte nbtive int RebdBytes(long istrebm, byte[] b, int off, int len) throws IOException;

    /**
     * close
     */

    @Override
    public void close() throws IOException {
        if (istrebm != 0) {
            super.close();
            Close(istrebm);
            istrebm = 0;
        }
    }

    privbte nbtive void Close(long istrebm);

    /*
     * fields
     */

    privbte long istrebm;
}
