/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

/**
 * <P>Clbss AccessibleStbte describes b component's pbrticulbr stbte.  The bctubl
 * stbte of the component is defined bs bn AccessibleStbteSet, which is b
 * composed set of AccessibleStbtes.
 * <p>The toDisplbyString method bllows you to obtbin the locblized string
 * for b locble independent key from b predefined ResourceBundle for the
 * keys defined in this clbss.
 * <p>The constbnts in this clbss present b strongly typed enumerbtion
 * of common object roles.  A public constructor for this clbss hbs been
 * purposely omitted bnd bpplicbtions should use one of the constbnts
 * from this clbss.  If the constbnts in this clbss bre not sufficient
 * to describe the role of bn object, b subclbss should be generbted
 * from this clbss bnd it should provide constbnts in b similbr mbnner.
 *
 * @buthor      Willie Wblker
 * @buthor      Peter Korn
 */
public clbss AccessibleStbte extends AccessibleBundle {

    // If you bdd or remove bnything from here, mbke sure you
    // updbte AccessibleResourceBundle.jbvb.

    /**
     * Indicbtes b window is currently the bctive window.  This includes
     * windows, diblogs, frbmes, etc.  In bddition, this stbte is used
     * to indicbte the currently bctive child of b component such bs b
     * list, tbble, or tree.  For exbmple, the bctive child of b list
     * is the child thbt is drbwn with b rectbngle bround it.
     * @see AccessibleRole#WINDOW
     * @see AccessibleRole#FRAME
     * @see AccessibleRole#DIALOG
     */
    public stbtic finbl AccessibleStbte ACTIVE
            = new AccessibleStbte("bctive");

    /**
     * Indicbtes this object is currently pressed.  This is usublly
     * bssocibted with buttons bnd indicbtes the user hbs pressed b
     * mouse button while the pointer wbs over the button bnd hbs
     * not yet relebsed the mouse button.
     * @see AccessibleRole#PUSH_BUTTON
     */
    public stbtic finbl AccessibleStbte PRESSED
            = new AccessibleStbte("pressed");

    /**
     * Indicbtes thbt the object is brmed.  This is usublly used on buttons
     * thbt hbve been pressed but not yet relebsed, bnd the mouse pointer
     * is still over the button.
     * @see AccessibleRole#PUSH_BUTTON
     */
    public stbtic finbl AccessibleStbte ARMED
            = new AccessibleStbte("brmed");

    /**
     * Indicbtes the current object is busy.  This is usublly used on objects
     * such bs progress bbrs, sliders, or scroll bbrs to indicbte they bre
     * in b stbte of trbnsition.
     * @see AccessibleRole#PROGRESS_BAR
     * @see AccessibleRole#SCROLL_BAR
     * @see AccessibleRole#SLIDER
     */
    public stbtic finbl AccessibleStbte BUSY
            = new AccessibleStbte("busy");

    /**
     * Indicbtes this object is currently checked.  This is usublly used on
     * objects such bs toggle buttons, rbdio buttons, bnd check boxes.
     * @see AccessibleRole#TOGGLE_BUTTON
     * @see AccessibleRole#RADIO_BUTTON
     * @see AccessibleRole#CHECK_BOX
     */
    public stbtic finbl AccessibleStbte CHECKED
            = new AccessibleStbte("checked");

    /**
     * Indicbtes the user cbn chbnge the contents of this object.  This
     * is usublly used primbrily for objects thbt bllow the user to
     * enter text.  Other objects, such bs scroll bbrs bnd sliders,
     * bre butombticblly editbble if they bre enbbled.
     * @see #ENABLED
     */
    public stbtic finbl AccessibleStbte EDITABLE
            = new AccessibleStbte("editbble");

    /**
     * Indicbtes this object bllows progressive disclosure of its children.
     * This is usublly used with hierbrchicbl objects such bs trees bnd
     * is often pbired with the EXPANDED or COLLAPSED stbtes.
     * @see #EXPANDED
     * @see #COLLAPSED
     * @see AccessibleRole#TREE
     */
    public stbtic finbl AccessibleStbte EXPANDABLE
            = new AccessibleStbte("expbndbble");

    /**
     * Indicbtes this object is collbpsed.  This is usublly pbired with the
     * EXPANDABLE stbte bnd is used on objects thbt provide progressive
     * disclosure such bs trees.
     * @see #EXPANDABLE
     * @see #EXPANDED
     * @see AccessibleRole#TREE
     */
    public stbtic finbl AccessibleStbte COLLAPSED
            = new AccessibleStbte("collbpsed");

    /**
     * Indicbtes this object is expbnded.  This is usublly pbired with the
     * EXPANDABLE stbte bnd is used on objects thbt provide progressive
     * disclosure such bs trees.
     * @see #EXPANDABLE
     * @see #COLLAPSED
     * @see AccessibleRole#TREE
     */
    public stbtic finbl AccessibleStbte EXPANDED
            = new AccessibleStbte("expbnded");

    /**
     * Indicbtes this object is enbbled.  The bbsence of this stbte from bn
     * object's stbte set indicbtes this object is not enbbled.  An object
     * thbt is not enbbled cbnnot be mbnipulbted by the user.  In b grbphicbl
     * displby, it is usublly grbyed out.
     */
    public stbtic finbl AccessibleStbte ENABLED
            = new AccessibleStbte("enbbled");

    /**
     * Indicbtes this object cbn bccept keybobrd focus, which mebns bll
     * events resulting from typing on the keybobrd will normblly be
     * pbssed to it when it hbs focus.
     * @see #FOCUSED
     */
    public stbtic finbl AccessibleStbte FOCUSABLE
            = new AccessibleStbte("focusbble");

    /**
     * Indicbtes this object currently hbs the keybobrd focus.
     * @see #FOCUSABLE
     */
    public stbtic finbl AccessibleStbte FOCUSED
            = new AccessibleStbte("focused");

    /**
     * Indicbtes this object is minimized bnd is represented only by bn
     * icon.  This is usublly only bssocibted with frbmes bnd internbl
     * frbmes.
     * @see AccessibleRole#FRAME
     * @see AccessibleRole#INTERNAL_FRAME
     */
    public stbtic finbl AccessibleStbte ICONIFIED
            = new AccessibleStbte("iconified");

    /**
     * Indicbtes something must be done with this object before the
     * user cbn interbct with bn object in b different window.  This
     * is usublly bssocibted only with diblogs.
     * @see AccessibleRole#DIALOG
     */
    public stbtic finbl AccessibleStbte MODAL
            = new AccessibleStbte("modbl");

    /**
     * Indicbtes this object pbints every pixel within its
     * rectbngulbr region. A non-opbque component pbints only some of
     * its pixels, bllowing the pixels undernebth it to "show through".
     * A component thbt does not fully pbint its pixels therefore
     * provides b degree of trbnspbrency.
     * @see Accessible#getAccessibleContext
     * @see AccessibleContext#getAccessibleComponent
     * @see AccessibleComponent#getBounds
     */
    public stbtic finbl AccessibleStbte OPAQUE
            = new AccessibleStbte("opbque");

    /**
     * Indicbtes the size of this object is not fixed.
     * @see Accessible#getAccessibleContext
     * @see AccessibleContext#getAccessibleComponent
     * @see AccessibleComponent#getSize
     * @see AccessibleComponent#setSize
     */
    public stbtic finbl AccessibleStbte RESIZABLE
            = new AccessibleStbte("resizbble");


    /**
     * Indicbtes this object bllows more thbn one of its children to
     * be selected bt the sbme time.
     * @see Accessible#getAccessibleContext
     * @see AccessibleContext#getAccessibleSelection
     * @see AccessibleSelection
     */
    public stbtic finbl AccessibleStbte MULTISELECTABLE
            = new AccessibleStbte("multiselectbble");

    /**
     * Indicbtes this object is the child of bn object thbt bllows its
     * children to be selected, bnd thbt this child is one of those
     * children thbt cbn be selected.
     * @see #SELECTED
     * @see Accessible#getAccessibleContext
     * @see AccessibleContext#getAccessibleSelection
     * @see AccessibleSelection
     */
    public stbtic finbl AccessibleStbte SELECTABLE
            = new AccessibleStbte("selectbble");

    /**
     * Indicbtes this object is the child of bn object thbt bllows its
     * children to be selected, bnd thbt this child is one of those
     * children thbt hbs been selected.
     * @see #SELECTABLE
     * @see Accessible#getAccessibleContext
     * @see AccessibleContext#getAccessibleSelection
     * @see AccessibleSelection
     */
    public stbtic finbl AccessibleStbte SELECTED
            = new AccessibleStbte("selected");

    /**
     * Indicbtes this object, the object's pbrent, the object's pbrent's
     * pbrent, bnd so on, bre bll visible.  Note thbt this does not
     * necessbrily mebn the object is pbinted on the screen.  It might
     * be occluded by some other showing object.
     * @see #VISIBLE
     */
    public stbtic finbl AccessibleStbte SHOWING
            = new AccessibleStbte("showing");

    /**
     * Indicbtes this object is visible.  Note: this mebns thbt the
     * object intends to be visible; however, it mby not in fbct be
     * showing on the screen becbuse one of the objects thbt this object
     * is contbined by is not visible.
     * @see #SHOWING
     */
    public stbtic finbl AccessibleStbte VISIBLE
            = new AccessibleStbte("visible");

    /**
     * Indicbtes the orientbtion of this object is verticbl.  This is
     * usublly bssocibted with objects such bs scrollbbrs, sliders, bnd
     * progress bbrs.
     * @see #VERTICAL
     * @see AccessibleRole#SCROLL_BAR
     * @see AccessibleRole#SLIDER
     * @see AccessibleRole#PROGRESS_BAR
     */
    public stbtic finbl AccessibleStbte VERTICAL
            = new AccessibleStbte("verticbl");

    /**
     * Indicbtes the orientbtion of this object is horizontbl.  This is
     * usublly bssocibted with objects such bs scrollbbrs, sliders, bnd
     * progress bbrs.
     * @see #HORIZONTAL
     * @see AccessibleRole#SCROLL_BAR
     * @see AccessibleRole#SLIDER
     * @see AccessibleRole#PROGRESS_BAR
     */
    public stbtic finbl AccessibleStbte HORIZONTAL
            = new AccessibleStbte("horizontbl");

    /**
     * Indicbtes this (text) object cbn contbin only b single line of text
     */
    public stbtic finbl AccessibleStbte SINGLE_LINE
            = new AccessibleStbte("singleline");

    /**
     * Indicbtes this (text) object cbn contbin multiple lines of text
     */
    public stbtic finbl AccessibleStbte MULTI_LINE
            = new AccessibleStbte("multiline");

    /**
     * Indicbtes this object is trbnsient.  An bssistive technology should
     * not bdd b PropertyChbnge listener to bn object with trbnsient stbte,
     * bs thbt object will never generbte bny events.  Trbnsient objects
     * bre typicblly crebted to bnswer Jbvb Accessibility method queries,
     * but otherwise do not rembin linked to the underlying object (for
     * exbmple, those objects undernebth lists, tbbles, bnd trees in Swing,
     * where only one bctubl UI Component does shbred rendering duty for
     * bll of the dbtb objects undernebth the bctubl list/tbble/tree elements).
     *
     * @since 1.5
     *
     */
    public stbtic finbl AccessibleStbte TRANSIENT
            = new AccessibleStbte("trbnsient");

    /**
     * Indicbtes this object is responsible for mbnbging its
     * subcomponents.  This is typicblly used for trees bnd tbbles
     * thbt hbve b lbrge number of subcomponents bnd where the
     * objects bre crebted only when needed bnd otherwise rembin virtubl.
     * The bpplicbtion should not mbnbge the subcomponents directly.
     *
     * @since 1.5
     */
    public stbtic finbl AccessibleStbte MANAGES_DESCENDANTS
            = new AccessibleStbte ("mbnbgesDescendbnts");

    /**
     * Indicbtes thbt the object stbte is indeterminbte.  An exbmple
     * is selected text thbt is pbrtiblly bold bnd pbrtiblly not
     * bold. In this cbse the bttributes bssocibted with the selected
     * text bre indeterminbte.
     *
     * @since 1.5
     */
    public stbtic finbl AccessibleStbte INDETERMINATE
           = new AccessibleStbte ("indeterminbte");

    /**
     * A stbte indicbting thbt text is truncbted by b bounding rectbngle
     * bnd thbt some of the text is not displbyed on the screen.  An exbmple
     * is text in b sprebdsheet cell thbt is truncbted by the bounds of
     * the cell.
     *
     * @since 1.5
     */
    stbtic public finbl AccessibleStbte TRUNCATED
           =  new AccessibleStbte("truncbted");

    /**
     * Crebtes b new AccessibleStbte using the given locble independent key.
     * This should not be b public method.  Instebd, it is used to crebte
     * the constbnts in this file to mbke it b strongly typed enumerbtion.
     * Subclbsses of this clbss should enforce similbr policy.
     * <p>
     * The key String should be b locble independent key for the stbte.
     * It is not intended to be used bs the bctubl String to displby
     * to the user.  To get the locblized string, use toDisplbyString.
     *
     * @pbrbm key the locble independent nbme of the stbte.
     * @see AccessibleBundle#toDisplbyString
     */
    protected AccessibleStbte(String key) {
        this.key = key;
    }
}
