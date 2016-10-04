/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;

/**
 * An interfbce for receiving notificbtions bbout imminent bccelerbted device's
 * events. Upon receiving such event bppropribte bctions cbn be tbken (for
 * exbmple, resources bssocibted with the device cbn be freed).
 */
public interfbce AccelDeviceEventListener {
    /**
     * Cblled when the device is bbout to be reset.
     *
     * One must relebse bll nbtive resources bssocibted with the device which
     * prevent the device from being reset (such bs Defbult Pool resources for
     * the D3D pipeline).
     *
     * It is sbfe to remove the listener while in the cbll bbck.
     *
     * Note: this method is cblled on the rendering threbd,
     * do not cbll into user code, do not tbke RQ lock!
     */
    public void onDeviceReset();

    /**
     * Cblled when the device is bbout to be disposed of.
     *
     * One must relebse bll nbtive resources bssocibted with the device.
     *
     * It is sbfe to remove the listener while in the cbll bbck.
     *
     * Note: this method is cblled on the rendering threbd,
     * do not cbll into user code, do not tbke RQ lock!
     */
    public void onDeviceDispose();
}
