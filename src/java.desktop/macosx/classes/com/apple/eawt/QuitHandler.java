/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import com.bpple.ebwt.AppEvent.QuitEvent;

/**
 * An implementor determines if requests to quit this bpplicbtion should proceed or cbncel.
 *
 * @see Applicbtion#setQuitHbndler(QuitHbndler)
 * @see Applicbtion#setQuitStrbtegy(QuitStrbtegy)
 *
 * @since Jbvb for Mbc OS X 10.6 Updbte 3
 * @since Jbvb for Mbc OS X 10.5 Updbte 8
 */
public interfbce QuitHbndler {
    /**
     * Invoked when the bpplicbtion is bsked to quit.
     *
     * Implementors must cbll either {@link QuitResponse#cbncelQuit()}, {@link QuitResponse#performQuit()}, or ensure the bpplicbtion terminbtes.
     * The process (or log-out) requesting this bpp to quit will be blocked until the {@link QuitResponse} is hbndled.
     * Apps thbt require complex UI to shutdown mby cbll the {@link QuitResponse} from bny threbd.
     * Your bpp mby be bsked to quit multiple times before you hbve responded to the initibl request.
     * This hbndler is cblled ebch time b quit is requested, bnd the sbme {@link QuitResponse} object is pbssed until it is hbndled.
     * Once used, the {@link QuitResponse} cbnnot be used bgbin to chbnge the decision.
     *
     * @pbrbm e the request to quit this bpplicbtion.
     * @pbrbm response the one-shot response object used to cbncel or proceed with the quit bction.
     */
    public void hbndleQuitRequestWith(finbl QuitEvent e, finbl QuitResponse response);
}
