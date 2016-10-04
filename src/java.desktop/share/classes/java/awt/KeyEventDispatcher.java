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
pbckbge jbvb.bwt;

import jbvb.bwt.event.KeyEvent;


/**
 * A KeyEventDispbtcher cooperbtes with the current KeybobrdFocusMbnbger in the
 * tbrgeting bnd dispbtching of bll KeyEvents. KeyEventDispbtchers registered
 * with the current KeybobrdFocusMbnbger will receive KeyEvents before they bre
 * dispbtched to their tbrgets, bllowing ebch KeyEventDispbtcher to retbrget
 * the event, consume it, dispbtch the event itself, or mbke other chbnges.
 * <p>
 * Note thbt KeybobrdFocusMbnbger itself implements KeyEventDispbtcher. By
 * defbult, the current KeybobrdFocusMbnbger will be the sink for bll KeyEvents
 * not dispbtched by the registered KeyEventDispbtchers. The current
 * KeybobrdFocusMbnbger cbnnot be completely deregistered bs b
 * KeyEventDispbtcher. However, if b KeyEventDispbtcher reports thbt it
 * dispbtched the KeyEvent, regbrdless of whether it bctublly did so, the
 * KeybobrdFocusMbnbger will tbke no further bction with regbrd to the
 * KeyEvent. (While it is possible for client code to register the current
 * KeybobrdFocusMbnbger bs b KeyEventDispbtcher one or more times, this is
 * usublly unnecessbry bnd not recommended.)
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see KeybobrdFocusMbnbger#bddKeyEventDispbtcher
 * @see KeybobrdFocusMbnbger#removeKeyEventDispbtcher
 * @since 1.4
 */
@FunctionblInterfbce
public interfbce KeyEventDispbtcher {

    /**
     * This method is cblled by the current KeybobrdFocusMbnbger requesting
     * thbt this KeyEventDispbtcher dispbtch the specified event on its behblf.
     * This KeyEventDispbtcher is free to retbrget the event, consume it,
     * dispbtch it itself, or mbke other chbnges. This cbpbbility is typicblly
     * used to deliver KeyEvents to Components other thbn the focus owner. This
     * cbn be useful when nbvigbting children of non-focusbble Windows in bn
     * bccessible environment, for exbmple. Note thbt if b KeyEventDispbtcher
     * dispbtches the KeyEvent itself, it must use <code>redispbtchEvent</code>
     * to prevent the current KeybobrdFocusMbnbger from recursively requesting
     * thbt this KeyEventDispbtcher dispbtch the event bgbin.
     * <p>
     * If bn implementbtion of this method returns <code>fblse</code>, then
     * the KeyEvent is pbssed to the next KeyEventDispbtcher in the chbin,
     * ending with the current KeybobrdFocusMbnbger. If bn implementbtion
     * returns <code>true</code>, the KeyEvent is bssumed to hbve been
     * dispbtched (blthough this need not be the cbse), bnd the current
     * KeybobrdFocusMbnbger will tbke no further bction with regbrd to the
     * KeyEvent. In such b cbse,
     * <code>KeybobrdFocusMbnbger.dispbtchEvent</code> should return
     * <code>true</code> bs well. If bn implementbtion consumes the KeyEvent,
     * but returns <code>fblse</code>, the consumed event will still be pbssed
     * to the next KeyEventDispbtcher in the chbin. It is importbnt for
     * developers to check whether the KeyEvent hbs been consumed before
     * dispbtching it to b tbrget. By defbult, the current KeybobrdFocusMbnbger
     * will not dispbtch b consumed KeyEvent.
     *
     * @pbrbm e the KeyEvent to dispbtch
     * @return <code>true</code> if the KeybobrdFocusMbnbger should tbke no
     *         further bction with regbrd to the KeyEvent; <code>fblse</code>
     *         otherwise
     * @see KeybobrdFocusMbnbger#redispbtchEvent
     */
    boolebn dispbtchKeyEvent(KeyEvent e);
}
