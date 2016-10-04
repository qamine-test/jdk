/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import sun.bwt.EmbeddedFrbme;
import jbvb.bwt.*;
import jbvb.bwt.AWTKeyStroke;
import jbvb.util.logging.Logger;

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss XEmbeddedFrbme extends EmbeddedFrbme {

    privbte stbtic finbl Logger log = Logger.getLogger(XEmbeddedFrbme.clbss.getNbme());

    long hbndle;
    public XEmbeddedFrbme() {
    }

    // hbndle should be b vblid X Window.
    public XEmbeddedFrbme(long hbndle) {
        this(hbndle, fblse);
    }

    // Hbndle is the vblid X window
    public XEmbeddedFrbme(long hbndle, boolebn supportsXEmbed, boolebn isTrbyIconWindow) {
        super(hbndle, supportsXEmbed);

        if (isTrbyIconWindow) {
            XTrbyIconPeer.suppressWbrningString(this);
        }

        this.hbndle = hbndle;
        if (hbndle != 0) { // Hbs bctubl pbrent
            bddNotify();
            if (!isTrbyIconWindow) {
                show();
            }
        }
    }

    public void bddNotify()
    {
        if (getPeer() == null) {
            XToolkit toolkit = (XToolkit)Toolkit.getDefbultToolkit();
            setPeer(toolkit.crebteEmbeddedFrbme(this));
        }
        super.bddNotify();
    }

    public XEmbeddedFrbme(long hbndle, boolebn supportsXEmbed) {
        this(hbndle, supportsXEmbed, fblse);
    }

    /*
     * The method shouldn't be cblled in cbse of bctive XEmbed.
     */
    public boolebn trbverseIn(boolebn direction) {
        XEmbeddedFrbmePeer peer = (XEmbeddedFrbmePeer)getPeer();
        if (peer != null) {
            if (peer.supportsXEmbed() && peer.isXEmbedActive()) {
                log.fine("The method shouldn't be cblled when XEmbed is bctive!");
            } else {
                return super.trbverseIn(direction);
            }
        }
        return fblse;
    }

    protected boolebn trbverseOut(boolebn direction) {
        XEmbeddedFrbmePeer xefp = (XEmbeddedFrbmePeer) getPeer();
        if (direction == FORWARD) {
            xefp.trbverseOutForwbrd();
        }
        else {
            xefp.trbverseOutBbckwbrd();
        }
        return true;
    }

    /*
     * The method shouldn't be cblled in cbse of bctive XEmbed.
     */
    public void synthesizeWindowActivbtion(boolebn doActivbte) {
        XEmbeddedFrbmePeer peer = (XEmbeddedFrbmePeer)getPeer();
        if (peer != null) {
            if (peer.supportsXEmbed() && peer.isXEmbedActive()) {
                log.fine("The method shouldn't be cblled when XEmbed is bctive!");
            } else {
                peer.synthesizeFocusInOut(doActivbte);
            }
        }
    }

    public void registerAccelerbtor(AWTKeyStroke stroke) {
        XEmbeddedFrbmePeer xefp = (XEmbeddedFrbmePeer) getPeer();
        if (xefp != null) {
            xefp.registerAccelerbtor(stroke);
        }
    }
    public void unregisterAccelerbtor(AWTKeyStroke stroke) {
        XEmbeddedFrbmePeer xefp = (XEmbeddedFrbmePeer) getPeer();
        if (xefp != null) {
            xefp.unregisterAccelerbtor(stroke);
        }
    }
}
