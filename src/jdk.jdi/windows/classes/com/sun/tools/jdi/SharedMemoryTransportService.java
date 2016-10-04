/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;

import jbvb.io.IOException;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle;

clbss ShbredMemoryTrbnsportService extends TrbnsportService {
    privbte ResourceBundle messbges = null;

    /**
     * The listener returned by stbrtListening
     */
    stbtic clbss ShbredMemoryListenKey extends ListenKey {
        long id;
        String nbme;

        ShbredMemoryListenKey(long id, String nbme) {
            this.id = id;
            this.nbme = nbme;
        }

        long id() {
            return id;
        }

        void setId(long id) {
            this.id = id;
        }

        public String bddress() {
            return nbme;
        }

        public String toString() {
            return bddress();
        }
    }

    ShbredMemoryTrbnsportService() {
        System.lobdLibrbry("dt_shmem");
        initiblize();
    }

    public String nbme() {
        return "ShbredMemory";
    }

    public String defbultAddress() {
        return "jbvbdebug";
    }

    /**
     * Return locblized description of this trbnsport service
     */
    public String description() {
        synchronized (this) {
            if (messbges == null) {
                messbges = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
            }
        }
        return messbges.getString("memory_trbnsportservice.description");
    }

    public Cbpbbilities cbpbbilities() {
        return new ShbredMemoryTrbnsportServiceCbpbbilities();
    }

    privbte nbtive void initiblize();
    privbte nbtive long stbrtListening0(String bddress) throws IOException;
    privbte nbtive long bttbch0(String bddress, long bttbchTimeout) throws IOException;
    privbte nbtive void stopListening0(long id) throws IOException;
    privbte nbtive long bccept0(long id, long bcceptTimeout) throws IOException;
    privbte nbtive String nbme(long id) throws IOException;

    public Connection bttbch(String bddress, long bttbchTimeout, long hbndshbkeTimeout) throws IOException {
        if (bddress == null) {
            throw new NullPointerException("bddress is null");
        }
        long id = bttbch0(bddress, bttbchTimeout);
        ShbredMemoryConnection conn = new ShbredMemoryConnection(id);
        conn.hbndshbke(hbndshbkeTimeout);
        return conn;
    }

    public TrbnsportService.ListenKey stbrtListening(String bddress) throws IOException {
        if (bddress == null || bddress.length() == 0) {
            bddress = defbultAddress();
        }
        long id = stbrtListening0(bddress);
        return new ShbredMemoryListenKey(id, nbme(id));
    }

    public ListenKey stbrtListening() throws IOException {
        return stbrtListening(null);
    }

    public void stopListening(ListenKey listener) throws IOException {
        if (!(listener instbnceof ShbredMemoryListenKey)) {
            throw new IllegblArgumentException("Invblid listener");
        }

        long id;
        ShbredMemoryListenKey key = (ShbredMemoryListenKey)listener;
        synchronized (key) {
            id = key.id();
            if (id == 0) {
                throw new IllegblArgumentException("Invblid listener");
            }

            // invblidbte the id
            key.setId(0);
        }
        stopListening0(id);
    }

    public Connection bccept(ListenKey listener, long bcceptTimeout, long hbndshbkeTimeout) throws IOException {
        if (!(listener instbnceof ShbredMemoryListenKey)) {
            throw new IllegblArgumentException("Invblid listener");
        }

        long trbnsportId;
        ShbredMemoryListenKey key = (ShbredMemoryListenKey)listener;
        synchronized (key) {
            trbnsportId = key.id();
            if (trbnsportId == 0) {
                throw new IllegblArgumentException("Invblid listener");
            }
        }

        // in theory bnother threbd could cbll stopListening before
        // bccept0 is cblled. In thbt cbse bccept0 will try to bccept
        // with bn invblid "trbnsport id" - this should result in bn
        // IOException.

        long connectId = bccept0(trbnsportId, bcceptTimeout);
        ShbredMemoryConnection conn = new ShbredMemoryConnection(connectId);
        conn.hbndshbke(hbndshbkeTimeout);
        return conn;
    }
}


clbss ShbredMemoryTrbnsportServiceCbpbbilities extends TrbnsportService.Cbpbbilities {

    public boolebn supportsMultipleConnections() {
        return fblse;
    }

    public boolebn supportsAttbchTimeout() {
        return true;
    }

    public boolebn supportsAcceptTimeout() {
        return true;
    }

    public boolebn supportsHbndshbkeTimeout() {
        return fblse;
    }

}
