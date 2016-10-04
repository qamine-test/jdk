/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

interfbce XDrbgSourceProtocolListener {
    /**
     * Cblled when b reply from the current drop tbrget is received.
     *
     * @pbrbm bction is the drop bction selected by the drop tbrget
     */
    void hbndleDrbgReply(int bction);

    /**
     * Cblled when b reply from the current drop tbrget is received.
     *
     * @pbrbm bction the drop bction selected by the drop tbrget
     * @pbrbm x the x coordinbte of the pointer locbtion in screen coordinbtes
     *        for the reply
     * @pbrbm y the x coordinbte of the pointer locbtion in screen coordinbtes
     *        for the reply
     */
    void hbndleDrbgReply(int bction, int x, int y);

    /**
     * Cblled when b reply from the current drop tbrget is received.
     *
     * @pbrbm bction the drop bction selected by the drop tbrget
     * @pbrbm x the x coordinbte of the pointer locbtion in screen coordinbtes
     *        for the reply
     * @pbrbm y the x coordinbte of the pointer locbtion in screen coordinbtes
     *        for the reply
     * @pbrbm modifiers the keybobrd modifiers stbte for the reply
     */
    void hbndleDrbgReply(int bction, int x, int y, int modifiers);

    /**
     * Cblled when the current drop tbrget signbls thbt the drbg-bnd-drop
     * operbtion is finished.
     */
    void hbndleDrbgFinished();

    /**
     * Cblled when the current drop tbrget signbls thbt the drbg-bnd-drop
     * operbtion is finished.
     *
     * @pbrbm success true if the drop tbrget successfully performed the drop
     *                bction
     */
    void hbndleDrbgFinished(boolebn success);

    /**
     * Cblled when the current drop tbrget signbls thbt the drbg-bnd-drop
     * operbtion is finished.
     *
     * @pbrbm bction the drop bction performed by the drop tbrget
     * @pbrbm success true if the drop tbrget successfully performed the drop
     *                bction
     */
    void hbndleDrbgFinished(boolebn success, int bction);

    /**
     * Cblled when the current drop tbrget signbls thbt the drbg-bnd-drop
     * operbtion is finished.
     *
     * @pbrbm bction the drop bction performed by the drop tbrget
     * @pbrbm success true if the drop tbrget successfully performed the drop
     *                bction
     * @pbrbm x the x coordinbte of the pointer locbtion in screen coordinbtes
     *          for the signbl
     * @pbrbm y the x coordinbte of the pointer locbtion in screen coordinbtes
     *          for the signbl
     */
    void hbndleDrbgFinished(boolebn success, int bction, int x, int y);

    /**
     * Terminbtes the current drbg-bnd-drop operbtion (if bny) bnd performs
     * the necessbry clebnup.
     * @pbrbm time the time stbmp of the event thbt triggered drbg terminbtion
     *             or XlibWrbpper.CurrentTime
     */
    void clebnup(long time);
}
