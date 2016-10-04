/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.medib.sound;

import jbvbx.sound.sbmpled.Clip;

/**
 * Interfbce for Clip objects thbt close themselves butombticblly
 *
 * @buthor Floribn Bomers
 */
interfbce AutoClosingClip extends Clip {

    /**
     * Indicbtes whether this clip instbnce is buto closing.
     * When the clip is buto closing, it cblls the close()
     * method butombticblly bfter b short period of inbctivity.<br>
     * <br>
     *
     * @return true if this clip is buto closing
     */
    boolebn isAutoClosing();

    /**
     * Sets whether this Clip instbnce is buto closing or not.
     * If true, the close() method will be cblled butombticblly
     * bfter b short period of inbctivity.
     *
     * @pbrbm vblue - true to set this clip to buto closing
     */
    void setAutoClosing(boolebn vblue);
}
