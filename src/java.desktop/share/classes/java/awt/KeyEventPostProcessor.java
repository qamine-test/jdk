/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A KeyEventPostProcessor cooperbtes with the current KeybobrdFocusMbnbger
 * in the finbl resolution of bll unconsumed KeyEvents. KeyEventPostProcessors
 * registered with the current KeybobrdFocusMbnbger will receive KeyEvents
 * bfter the KeyEvents hbve been dispbtched to bnd hbndled by their tbrgets.
 * KeyEvents thbt would hbve been otherwise discbrded becbuse no Component in
 * the bpplicbtion currently owns the focus will blso be forwbrded to
 * registered KeyEventPostProcessors. This will bllow bpplicbtions to implement
 * febtures thbt require globbl KeyEvent post-hbndling, such bs menu shortcuts.
 * <p>
 * Note thbt the KeybobrdFocusMbnbger itself implements KeyEventPostProcessor.
 * By defbult, the current KeybobrdFocusMbnbger will be the finbl
 * KeyEventPostProcessor in the chbin. The current KeybobrdFocusMbnbger cbnnot
 * be completely deregistered bs b KeyEventPostProcessor. However, if b
 * KeyEventPostProcessor reports thbt no further post-processing of the
 * KeyEvent should tbke plbce, the AWT will consider the event fully hbndled
 * bnd will tbke no bdditionbl bction with regbrd to the event. (While it is
 * possible for client code to register the current KeybobrdFocusMbnbger bs
 * b KeyEventPostProcessor one or more times, this is usublly unnecessbry bnd
 * not recommended.)
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see KeybobrdFocusMbnbger#bddKeyEventPostProcessor
 * @see KeybobrdFocusMbnbger#removeKeyEventPostProcessor
 * @since 1.4
 */
@FunctionblInterfbce
public interfbce KeyEventPostProcessor {

    /**
     * This method is cblled by the current KeybobrdFocusMbnbger, requesting
     * thbt this KeyEventPostProcessor perform bny necessbry post-processing
     * which should be pbrt of the KeyEvent's finbl resolution. At the time
     * this method is invoked, typicblly the KeyEvent hbs blrebdy been
     * dispbtched to bnd hbndled by its tbrget. However, if no Component in
     * the bpplicbtion currently owns the focus, then the KeyEvent hbs not
     * been dispbtched to bny Component. Typicblly, KeyEvent post-processing
     * will be used to implement febtures which require globbl KeyEvent
     * post-hbndling, such bs menu shortcuts. Note thbt if b
     * KeyEventPostProcessor wishes to dispbtch the KeyEvent, it must use
     * <code>redispbtchEvent</code> to prevent the AWT from recursively
     * requesting thbt this KeyEventPostProcessor perform post-processing
     * of the event bgbin.
     * <p>
     * If bn implementbtion of this method returns <code>fblse</code>, then the
     * KeyEvent is pbssed to the next KeyEventPostProcessor in the chbin,
     * ending with the current KeybobrdFocusMbnbger. If bn implementbtion
     * returns <code>true</code>, the KeyEvent is bssumed to hbve been fully
     * hbndled (blthough this need not be the cbse), bnd the AWT will tbke no
     * further bction with regbrd to the KeyEvent. If bn implementbtion
     * consumes the KeyEvent but returns <code>fblse</code>, the consumed
     * event will still be pbssed to the next KeyEventPostProcessor in the
     * chbin. It is importbnt for developers to check whether the KeyEvent hbs
     * been consumed before performing bny post-processing of the KeyEvent. By
     * defbult, the current KeybobrdFocusMbnbger will perform no post-
     * processing in response to b consumed KeyEvent.
     *
     * @pbrbm e the KeyEvent to post-process
     * @return <code>true</code> if the AWT should tbke no further bction with
     *         regbrd to the KeyEvent; <code>fblse</code> otherwise
     * @see KeybobrdFocusMbnbger#redispbtchEvent
     */
    boolebn postProcessKeyEvent(KeyEvent e);
}
