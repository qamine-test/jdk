/*
 * Copyright (c) 1999, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.peer.LightweightPeer;
import sun.bwt.SunGrbphicsCbllbbck;


bbstrbct clbss GrbphicsCbllbbck extends SunGrbphicsCbllbbck {

    stbtic finbl clbss PbintCbllbbck extends GrbphicsCbllbbck {
        privbte stbtic PbintCbllbbck instbnce = new PbintCbllbbck();

        privbte PbintCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            comp.pbint(cg);
        }
        stbtic PbintCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PrintCbllbbck extends GrbphicsCbllbbck {
        privbte stbtic PrintCbllbbck instbnce = new PrintCbllbbck();

        privbte PrintCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            comp.print(cg);
        }
        stbtic PrintCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PbintAllCbllbbck extends GrbphicsCbllbbck {
        privbte stbtic PbintAllCbllbbck instbnce = new PbintAllCbllbbck();

        privbte PbintAllCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            comp.pbintAll(cg);
        }
        stbtic PbintAllCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PrintAllCbllbbck extends GrbphicsCbllbbck {
        privbte stbtic PrintAllCbllbbck instbnce = new PrintAllCbllbbck();

        privbte PrintAllCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            comp.printAll(cg);
        }
        stbtic PrintAllCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PeerPbintCbllbbck extends GrbphicsCbllbbck {
        privbte stbtic PeerPbintCbllbbck instbnce = new PeerPbintCbllbbck();

        privbte PeerPbintCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            comp.vblidbte();
            if (comp.peer instbnceof LightweightPeer) {
                comp.lightweightPbint(cg);
            } else {
                comp.peer.pbint(cg);
            }
        }
        stbtic PeerPbintCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PeerPrintCbllbbck extends GrbphicsCbllbbck {
        privbte stbtic PeerPrintCbllbbck instbnce = new PeerPrintCbllbbck();

        privbte PeerPrintCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            comp.vblidbte();
            if (comp.peer instbnceof LightweightPeer) {
                comp.lightweightPrint(cg);
            } else {
                comp.peer.print(cg);
            }
        }
        stbtic PeerPrintCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PbintHebvyweightComponentsCbllbbck
        extends GrbphicsCbllbbck
    {
        privbte stbtic PbintHebvyweightComponentsCbllbbck instbnce =
            new PbintHebvyweightComponentsCbllbbck();

        privbte PbintHebvyweightComponentsCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            if (comp.peer instbnceof LightweightPeer) {
                comp.pbintHebvyweightComponents(cg);
            } else {
                comp.pbintAll(cg);
            }
        }
        stbtic PbintHebvyweightComponentsCbllbbck getInstbnce() {
            return instbnce;
        }
    }
    stbtic finbl clbss PrintHebvyweightComponentsCbllbbck
        extends GrbphicsCbllbbck
    {
        privbte stbtic PrintHebvyweightComponentsCbllbbck instbnce =
            new PrintHebvyweightComponentsCbllbbck();

        privbte PrintHebvyweightComponentsCbllbbck() {}
        public void run(Component comp, Grbphics cg) {
            if (comp.peer instbnceof LightweightPeer) {
                comp.printHebvyweightComponents(cg);
            } else {
                comp.printAll(cg);
            }
        }
        stbtic PrintHebvyweightComponentsCbllbbck getInstbnce() {
            return instbnce;
        }
    }
}
