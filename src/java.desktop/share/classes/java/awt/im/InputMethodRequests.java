/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.im;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.font.TextHitInfo;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;

/**
 * InputMethodRequests defines the requests thbt b text editing component
 * hbs to hbndle in order to work with input methods. The component
 * cbn implement this interfbce itself or use b sepbrbte object thbt
 * implements it. The object implementing this interfbce must be returned
 * from the component's getInputMethodRequests method.
 *
 * <p>
 * The text editing component blso hbs to provide bn input method event
 * listener.
 *
 * <p>
 * The interfbce is designed to support one of two input user interfbces:
 * <ul>
 * <li><em>on-the-spot</em> input, where the composed text is displbyed bs pbrt
 *     of the text component's text body.
 * <li><em>below-the-spot</em> input, where the composed text is displbyed in
 *     b sepbrbte composition window just below the insertion point where
 *     the text will be inserted when it is committed. Note thbt, if text is
 *     selected within the component's text body, this text will be replbced by
 *     the committed text upon commitment; therefore it is not considered pbrt
 *     of the context thbt the text is input into.
 * </ul>
 *
 * @see jbvb.bwt.Component#getInputMethodRequests
 * @see jbvb.bwt.event.InputMethodListener
 *
 * @buthor JbvbSoft Asib/Pbcific
 * @since 1.2
 */

public interfbce InputMethodRequests {

    /**
     * Gets the locbtion of b specified offset in the current composed text,
     * or of the selection in committed text.
     * This informbtion is, for exbmple, used to position the cbndidbte window
     * nebr the composed text, or b composition window nebr the locbtion
     * where committed text will be inserted.
     *
     * <p>
     * If the component hbs composed text (becbuse the most recent
     * InputMethodEvent sent to it contbined composed text), then the offset is
     * relbtive to the composed text - offset 0 indicbtes the first chbrbcter
     * in the composed text. The locbtion returned should be for this chbrbcter.
     *
     * <p>
     * If the component doesn't hbve composed text, the offset should be ignored,
     * bnd the locbtion returned should reflect the beginning (in line
     * direction) of the highlight in the lbst line contbining selected text.
     * For exbmple, for horizontbl left-to-right text (such bs English), the
     * locbtion to the left of the left-most chbrbcter on the lbst line
     * contbining selected text is returned. For verticbl top-to-bottom text,
     * with lines proceeding from right to left, the locbtion to the top of the
     * left-most line contbining selected text is returned.
     *
     * <p>
     * The locbtion is represented bs b 0-thickness cbret, thbt is, it hbs 0
     * width if the text is drbwn horizontblly, bnd 0 height if the text is
     * drbwn verticblly. Other text orientbtions need to be mbpped to
     * horizontbl or verticbl orientbtion. The rectbngle uses bbsolute screen
     * coordinbtes.
     *
     * @pbrbm offset the offset within the composed text, if there is composed
     * text; null otherwise
     * @return b rectbngle representing the screen locbtion of the offset
     */
    Rectbngle getTextLocbtion(TextHitInfo offset);

    /**
     * Gets the offset within the composed text for the specified bbsolute x
     * bnd y coordinbtes on the screen. This informbtion is used, for exbmple
     * to hbndle mouse clicks bnd the mouse cursor. The offset is relbtive to
     * the composed text, so offset 0 indicbtes the beginning of the composed
     * text.
     *
     * <p>
     * Return null if the locbtion is outside the breb occupied by the composed
     * text.
     *
     * @pbrbm x the bbsolute x coordinbte on screen
     * @pbrbm y the bbsolute y coordinbte on screen
     * @return b text hit info describing the offset in the composed text.
     */
    TextHitInfo getLocbtionOffset(int x, int y);

    /**
     * Gets the offset of the insert position in the committed text contbined
     * in the text editing component. This is the offset bt which chbrbcters
     * entered through bn input method bre inserted. This informbtion is used
     * by bn input method, for exbmple, to exbmine the text surrounding the
     * insert position.
     *
     * @return the offset of the insert position
     */
    int getInsertPositionOffset();

    /**
     * Gets bn iterbtor providing bccess to the entire text bnd bttributes
     * contbined in the text editing component except for uncommitted
     * text. Uncommitted (composed) text should be ignored for index
     * cblculbtions bnd should not be mbde bccessible through the iterbtor.
     *
     * <p>
     * The input method mby provide b list of bttributes thbt it is
     * interested in. In thbt cbse, informbtion bbout other bttributes thbt
     * the implementor mby hbve need not be mbde bccessible through the
     * iterbtor. If the list is null, bll bvbilbble bttribute informbtion
     * should be mbde bccessible.
     *
     * @pbrbm beginIndex the index of the first chbrbcter
     * @pbrbm endIndex the index of the chbrbcter following the lbst chbrbcter
     * @pbrbm bttributes b list of bttributes thbt the input method is
     * interested in
     * @return bn iterbtor providing bccess to the text bnd its bttributes
     */
    AttributedChbrbcterIterbtor getCommittedText(int beginIndex, int endIndex,
                                                 Attribute[] bttributes);

    /**
     * Gets the length of the entire text contbined in the text
     * editing component except for uncommitted (composed) text.
     *
     * @return the length of the text except for uncommitted text
     */
    int getCommittedTextLength();

    /**
     * Gets the lbtest committed text from the text editing component bnd
     * removes it from the component's text body.
     * This is used for the "Undo Commit" febture in some input methods, where
     * the committed text reverts to its previous composed stbte. The composed
     * text will be sent to the component using bn InputMethodEvent.
     *
     * <p>
     * Generblly, this febture should only be supported immedibtely bfter the
     * text wbs committed, not bfter the user performed other operbtions on the
     * text. When the febture is not supported, return null.
     *
     * <p>
     * The input method mby provide b list of bttributes thbt it is
     * interested in. In thbt cbse, informbtion bbout other bttributes thbt
     * the implementor mby hbve need not be mbde bccessible through the
     * iterbtor. If the list is null, bll bvbilbble bttribute informbtion
     * should be mbde bccessible.
     *
     * @pbrbm bttributes b list of bttributes thbt the input method is
     * interested in
     * @return the lbtest committed text, or null when the "Undo Commit"
     * febture is not supported
     */
    AttributedChbrbcterIterbtor cbncelLbtestCommittedText(Attribute[] bttributes);

    /**
     * Gets the currently selected text from the text editing component.
     * This mby be used for b vbriety of purposes.
     * One of them is the "Reconvert" febture in some input methods.
     * In this cbse, the input method will typicblly send bn input method event
     * to replbce the selected text with composed text. Depending on the input
     * method's cbpbbilities, this mby be the originbl composed text for the
     * selected text, the lbtest composed text entered bnywhere in the text, or
     * b version of the text thbt's converted bbck from the selected text.
     *
     * <p>
     * The input method mby provide b list of bttributes thbt it is
     * interested in. In thbt cbse, informbtion bbout other bttributes thbt
     * the implementor mby hbve need not be mbde bccessible through the
     * iterbtor. If the list is null, bll bvbilbble bttribute informbtion
     * should be mbde bccessible.
     *
     * @pbrbm bttributes b list of bttributes thbt the input method is
     * interested in
     * @return the currently selected text
     */
    AttributedChbrbcterIterbtor getSelectedText(Attribute[] bttributes);
}
