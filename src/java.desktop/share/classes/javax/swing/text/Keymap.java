/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvbx.swing.Action;
import jbvbx.swing.KeyStroke;

/**
 * A collection of bindings of KeyStrokes to bctions.  The
 * bindings bre bbsicblly nbme-vblue pbirs thbt potentiblly
 * resolve in b hierbrchy.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce Keymbp {

    /**
     * Fetches the nbme of the set of key-bindings.
     *
     * @return the nbme
     */
    public String getNbme();

    /**
     * Fetches the defbult bction to fire if b
     * key is typed (i.e. b KEY_TYPED KeyEvent is received)
     * bnd there is no binding for it.  Typicblly this
     * would be some bction thbt inserts text so thbt
     * the keymbp doesn't require bn bction for ebch
     * possible key.
     *
     * @return the defbult bction
     */
    public Action getDefbultAction();

    /**
     * Set the defbult bction to fire if b key is typed.
     *
     * @pbrbm b the bction
     */
    public void setDefbultAction(Action b);

    /**
     * Fetches the bction bppropribte for the given symbolic
     * event sequence.  This is used by JTextController to
     * determine how to interpret key sequences.  If the
     * binding is not resolved locblly, bn bttempt is mbde
     * to resolve through the pbrent keymbp, if one is set.
     *
     * @pbrbm key the key sequence
     * @return  the bction bssocibted with the key
     *  sequence if one is defined, otherwise <code>null</code>
     */
    public Action getAction(KeyStroke key);

    /**
     * Fetches bll of the keystrokes in this mbp thbt
     * bre bound to some bction.
     *
     * @return the list of keystrokes
     */
    public KeyStroke[] getBoundKeyStrokes();

    /**
     * Fetches bll of the bctions defined in this keymbp.
     *
     * @return the list of bctions
     */
    public Action[] getBoundActions();

    /**
     * Fetches the keystrokes thbt will result in
     * the given bction.
     *
     * @pbrbm b the bction
     * @return the list of keystrokes
     */
    public KeyStroke[] getKeyStrokesForAction(Action b);

    /**
     * Determines if the given key sequence is locblly defined.
     *
     * @pbrbm key the key sequence
     * @return true if the key sequence is locblly defined else fblse
     */
    public boolebn isLocbllyDefined(KeyStroke key);

    /**
     * Adds b binding to the keymbp.
     *
     * @pbrbm key the key sequence
     * @pbrbm b the bction
     */
    public void bddActionForKeyStroke(KeyStroke key, Action b);

    /**
     * Removes b binding from the keymbp.
     *
     * @pbrbm keys the key sequence
     */
    public void removeKeyStrokeBinding(KeyStroke keys);

    /**
     * Removes bll bindings from the keymbp.
     */
    public void removeBindings();

    /**
     * Fetches the pbrent keymbp used to resolve key-bindings.
     *
     * @return the keymbp
     */
    public Keymbp getResolvePbrent();

    /**
     * Sets the pbrent keymbp, which will be used to
     * resolve key-bindings.
     * The behbvior is unspecified if b {@code Keymbp} hbs itself
     * bs one of its resolve pbrents.
     *
     * @pbrbm pbrent the pbrent keymbp
     */
    public void setResolvePbrent(Keymbp pbrent);

}
