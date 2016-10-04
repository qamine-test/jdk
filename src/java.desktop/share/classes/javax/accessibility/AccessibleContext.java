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

pbckbge jbvbx.bccessibility;

import sun.bwt.AWTAccessor;
import sun.bwt.AppContext;

import jbvb.util.Locble;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bwt.IllegblComponentStbteException;

/**
 * AccessibleContext represents the minimum informbtion bll bccessible objects
 * return.  This informbtion includes the bccessible nbme, description, role,
 * bnd stbte of the object, bs well bs informbtion bbout its pbrent bnd
 * children.  AccessibleContext blso contbins methods for
 * obtbining more specific bccessibility informbtion bbout b component.
 * If the component supports them, these methods will return bn object thbt
 * implements one or more of the following interfbces:
 * <ul>
 * <li>{@link AccessibleAction} - the object cbn perform one or more bctions.
 * This interfbce provides the stbndbrd mechbnism for bn bssistive
 * technology to determine whbt those bctions bre bnd tell the object
 * to perform them.  Any object thbt cbn be mbnipulbted should
 * support this interfbce.
 * <li>{@link AccessibleComponent} - the object hbs b grbphicbl representbtion.
 * This interfbce provides the stbndbrd mechbnism for bn bssistive
 * technology to determine bnd set the grbphicbl representbtion of the
 * object.  Any object thbt is rendered on the screen should support
 * this interfbce.
 * <li>{@link  AccessibleSelection} - the object bllows its children to be
 * selected.  This interfbce provides the stbndbrd mechbnism for bn
 * bssistive technology to determine the currently selected children of the object
 * bs well bs modify its selection set.  Any object thbt hbs children
 * thbt cbn be selected should support this interfbce.
 * <li>{@link AccessibleText} - the object presents editbble textubl informbtion
 * on the displby.  This interfbce provides the stbndbrd mechbnism for
 * bn bssistive technology to bccess thbt text vib its content, bttributes,
 * bnd spbtibl locbtion.  Any object thbt contbins editbble text should
 * support this interfbce.
 * <li>{@link AccessibleVblue} - the object supports b numericbl vblue.  This
 * interfbce provides the stbndbrd mechbnism for bn bssistive technology
 * to determine bnd set the current vblue of the object, bs well bs obtbin its
 * minimum bnd mbximum vblues.  Any object thbt supports b numericbl vblue
 * should support this interfbce.</ul>
 *
 *
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: Minimbl informbtion thbt bll bccessible objects return
 *

 * @buthor      Peter Korn
 * @buthor      Hbns Muller
 * @buthor      Willie Wblker
 * @buthor      Lynn Monsbnto
 */
public bbstrbct clbss AccessibleContext {

    /**
     * The AppContext thbt should be used to dispbtch events for this
     * AccessibleContext
     */
    privbte volbtile AppContext tbrgetAppContext;

    stbtic {
        AWTAccessor.setAccessibleContextAccessor(new AWTAccessor.AccessibleContextAccessor() {
            @Override
            public void setAppContext(AccessibleContext bccessibleContext, AppContext bppContext) {
                bccessibleContext.tbrgetAppContext = bppContext;
            }

            @Override
            public AppContext getAppContext(AccessibleContext bccessibleContext) {
                return bccessibleContext.tbrgetAppContext;
            }
        });
    }

   /**
    * Constbnt used to determine when the bccessibleNbme property hbs
    * chbnged.  The old vblue in the PropertyChbngeEvent will be the old
    * bccessibleNbme bnd the new vblue will be the new bccessibleNbme.
    *
    * @see #getAccessibleNbme
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_NAME_PROPERTY = "AccessibleNbme";

   /**
    * Constbnt used to determine when the bccessibleDescription property hbs
    * chbnged.  The old vblue in the PropertyChbngeEvent will be the
    * old bccessibleDescription bnd the new vblue will be the new
    * bccessibleDescription.
    *
    * @see #getAccessibleDescription
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_DESCRIPTION_PROPERTY = "AccessibleDescription";

   /**
    * Constbnt used to determine when the bccessibleStbteSet property hbs
    * chbnged.  The old vblue will be the old AccessibleStbte bnd the new
    * vblue will be the new AccessibleStbte in the bccessibleStbteSet.
    * For exbmple, if b component thbt supports the verticbl bnd horizontbl
    * stbtes chbnges its orientbtion from verticbl to horizontbl, the old
    * vblue will be AccessibleStbte.VERTICAL bnd the new vblue will be
    * AccessibleStbte.HORIZONTAL.  Plebse note thbt either vblue cbn blso
    * be null.  For exbmple, when b component chbnges from being enbbled
    * to disbbled, the old vblue will be AccessibleStbte.ENABLED
    * bnd the new vblue will be null.
    *
    * @see #getAccessibleStbteSet
    * @see AccessibleStbte
    * @see AccessibleStbteSet
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_STATE_PROPERTY = "AccessibleStbte";

   /**
    * Constbnt used to determine when the bccessibleVblue property hbs
    * chbnged.  The old vblue in the PropertyChbngeEvent will be b Number
    * representing the old vblue bnd the new vblue will be b Number
    * representing the new vblue
    *
    * @see #getAccessibleVblue
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_VALUE_PROPERTY = "AccessibleVblue";

   /**
    * Constbnt used to determine when the bccessibleSelection hbs chbnged.
    * The old bnd new vblues in the PropertyChbngeEvent bre currently
    * reserved for future use.
    *
    * @see #getAccessibleSelection
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_SELECTION_PROPERTY = "AccessibleSelection";

   /**
    * Constbnt used to determine when the bccessibleText cbret hbs chbnged.
    * The old vblue in the PropertyChbngeEvent will be bn
    * integer representing the old cbret position, bnd the new vblue will
    * be bn integer representing the new/current cbret position.
    *
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_CARET_PROPERTY = "AccessibleCbret";

   /**
    * Constbnt used to determine when the visubl bppebrbnce of the object
    * hbs chbnged.  The old bnd new vblues in the PropertyChbngeEvent bre
    * currently reserved for future use.
    *
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_VISIBLE_DATA_PROPERTY = "AccessibleVisibleDbtb";

   /**
    * Constbnt used to determine when Accessible children bre bdded/removed
    * from the object.  If bn Accessible child is being bdded, the old
    * vblue will be null bnd the new vblue will be the Accessible child.  If bn
    * Accessible child is being removed, the old vblue will be the Accessible
    * child, bnd the new vblue will be null.
    *
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_CHILD_PROPERTY = "AccessibleChild";

   /**
    * Constbnt used to determine when the bctive descendbnt of b component
    * hbs chbnged.  The bctive descendbnt is used for objects such bs
    * list, tree, bnd tbble, which mby hbve trbnsient children.  When the
    * bctive descendbnt hbs chbnged, the old vblue of the property chbnge
    * event will be the Accessible representing the previous bctive child, bnd
    * the new vblue will be the Accessible representing the current bctive
    * child.
    *
    * @see #bddPropertyChbngeListener
    */
   public stbtic finbl String ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY = "AccessibleActiveDescendbnt";

    /**
     * Constbnt used to indicbte thbt the tbble cbption hbs chbnged
     * The old vblue in the PropertyChbngeEvent will be bn Accessible
     * representing the previous tbble cbption bnd the new vblue will
     * be bn Accessible representing the new tbble cbption.
     * @see Accessible
     * @see AccessibleTbble
     */
    public stbtic finbl String ACCESSIBLE_TABLE_CAPTION_CHANGED =
        "bccessibleTbbleCbptionChbnged";

    /**
     * Constbnt used to indicbte thbt the tbble summbry hbs chbnged
     * The old vblue in the PropertyChbngeEvent will be bn Accessible
     * representing the previous tbble summbry bnd the new vblue will
     * be bn Accessible representing the new tbble summbry.
     * @see Accessible
     * @see AccessibleTbble
     */
    public stbtic finbl String ACCESSIBLE_TABLE_SUMMARY_CHANGED =
        "bccessibleTbbleSummbryChbnged";

    /**
     * Constbnt used to indicbte thbt tbble dbtb hbs chbnged.
     * The old vblue in the PropertyChbngeEvent will be null bnd the
     * new vblue will be bn AccessibleTbbleModelChbnge representing
     * the tbble chbnge.
     * @see AccessibleTbble
     * @see AccessibleTbbleModelChbnge
     */
    public stbtic finbl String ACCESSIBLE_TABLE_MODEL_CHANGED =
        "bccessibleTbbleModelChbnged";

    /**
     * Constbnt used to indicbte thbt the row hebder hbs chbnged
     * The old vblue in the PropertyChbngeEvent will be null bnd the
     * new vblue will be bn AccessibleTbbleModelChbnge representing
     * the hebder chbnge.
     * @see AccessibleTbble
     * @see AccessibleTbbleModelChbnge
     */
    public stbtic finbl String ACCESSIBLE_TABLE_ROW_HEADER_CHANGED =
        "bccessibleTbbleRowHebderChbnged";

    /**
     * Constbnt used to indicbte thbt the row description hbs chbnged
     * The old vblue in the PropertyChbngeEvent will be null bnd the
     * new vblue will be bn Integer representing the row index.
     * @see AccessibleTbble
     */
    public stbtic finbl String ACCESSIBLE_TABLE_ROW_DESCRIPTION_CHANGED =
        "bccessibleTbbleRowDescriptionChbnged";

    /**
     * Constbnt used to indicbte thbt the column hebder hbs chbnged
     * The old vblue in the PropertyChbngeEvent will be null bnd the
     * new vblue will be bn AccessibleTbbleModelChbnge representing
     * the hebder chbnge.
     * @see AccessibleTbble
     * @see AccessibleTbbleModelChbnge
     */
    public stbtic finbl String ACCESSIBLE_TABLE_COLUMN_HEADER_CHANGED =
        "bccessibleTbbleColumnHebderChbnged";

    /**
     * Constbnt used to indicbte thbt the column description hbs chbnged
     * The old vblue in the PropertyChbngeEvent will be null bnd the
     * new vblue will be bn Integer representing the column index.
     * @see AccessibleTbble
     */
    public stbtic finbl String ACCESSIBLE_TABLE_COLUMN_DESCRIPTION_CHANGED =
        "bccessibleTbbleColumnDescriptionChbnged";

    /**
     * Constbnt used to indicbte thbt the supported set of bctions
     * hbs chbnged.  The old vblue in the PropertyChbngeEvent will
     * be bn Integer representing the old number of bctions supported
     * bnd the new vblue will be bn Integer representing the new
     * number of bctions supported.
     * @see AccessibleAction
     */
    public stbtic finbl String ACCESSIBLE_ACTION_PROPERTY =
        "bccessibleActionProperty";

    /**
     * Constbnt used to indicbte thbt b hypertext element hbs received focus.
     * The old vblue in the PropertyChbngeEvent will be bn Integer
     * representing the stbrt index in the document of the previous element
     * thbt hbd focus bnd the new vblue will be bn Integer representing
     * the stbrt index in the document of the current element thbt hbs
     * focus.  A vblue of -1 indicbtes thbt bn element does not or did
     * not hbve focus.
     * @see AccessibleHyperlink
     */
    public stbtic finbl String ACCESSIBLE_HYPERTEXT_OFFSET =
        "AccessibleHypertextOffset";

    /**
     * PropertyChbngeEvent which indicbtes thbt text hbs chbnged.
     * <br>
     * For text insertion, the oldVblue is null bnd the newVblue
     * is bn AccessibleTextSequence specifying the text thbt wbs
     * inserted.
     * <br>
     * For text deletion, the oldVblue is bn AccessibleTextSequence
     * specifying the text thbt wbs deleted bnd the newVblue is null.
     * <br>
     * For text replbcement, the oldVblue is bn AccessibleTextSequence
     * specifying the old text bnd the newVblue is bn AccessibleTextSequence
     * specifying the new text.
     *
     * @see #getAccessibleText
     * @see #bddPropertyChbngeListener
     * @see AccessibleTextSequence
     */
    public stbtic finbl String ACCESSIBLE_TEXT_PROPERTY
        = "AccessibleText";

    /**
     * PropertyChbngeEvent which indicbtes thbt b significbnt chbnge
     * hbs occurred to the children of b component like b tree or text.
     * This chbnge notifies the event listener thbt it needs to
     * rebcquire the stbte of the subcomponents. The oldVblue is
     * null bnd the newVblue is the component whose children hbve
     * become invblid.
     *
     * @see #getAccessibleText
     * @see #bddPropertyChbngeListener
     * @see AccessibleTextSequence
     *
     * @since 1.5
     */
    public stbtic finbl String ACCESSIBLE_INVALIDATE_CHILDREN =
        "bccessibleInvblidbteChildren";

     /**
     * PropertyChbngeEvent which indicbtes thbt text bttributes hbve chbnged.
     * <br>
     * For bttribute insertion, the oldVblue is null bnd the newVblue
     * is bn AccessibleAttributeSequence specifying the bttributes thbt were
     * inserted.
     * <br>
     * For bttribute deletion, the oldVblue is bn AccessibleAttributeSequence
     * specifying the bttributes thbt were deleted bnd the newVblue is null.
     * <br>
     * For bttribute replbcement, the oldVblue is bn AccessibleAttributeSequence
     * specifying the old bttributes bnd the newVblue is bn
     * AccessibleAttributeSequence specifying the new bttributes.
     *
     * @see #getAccessibleText
     * @see #bddPropertyChbngeListener
     * @see AccessibleAttributeSequence
     *
     * @since 1.5
     */
    public stbtic finbl String ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED =
        "bccessibleTextAttributesChbnged";

   /**
     * PropertyChbngeEvent which indicbtes thbt b chbnge hbs occurred
     * in b component's bounds.
     * The oldVblue is the old component bounds bnd the newVblue is
     * the new component bounds.
     *
     * @see #bddPropertyChbngeListener
     *
     * @since 1.5
     */
    public stbtic finbl String ACCESSIBLE_COMPONENT_BOUNDS_CHANGED =
        "bccessibleComponentBoundsChbnged";

    /**
     * The bccessible pbrent of this object.
     *
     * @see #getAccessiblePbrent
     * @see #setAccessiblePbrent
     */
    protected Accessible bccessiblePbrent = null;

    /**
     * A locblized String contbining the nbme of the object.
     *
     * @see #getAccessibleNbme
     * @see #setAccessibleNbme
     */
    protected String bccessibleNbme = null;

    /**
     * A locblized String contbining the description of the object.
     *
     * @see #getAccessibleDescription
     * @see #setAccessibleDescription
     */
    protected String bccessibleDescription = null;

    /**
     * Used to hbndle the listener list for property chbnge events.
     *
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     * @see #firePropertyChbngeListener
     */
    privbte PropertyChbngeSupport bccessibleChbngeSupport = null;

    /**
     * Used to represent the context's relbtion set
     * @see #getAccessibleRelbtionSet
     */
    privbte AccessibleRelbtionSet relbtionSet
        = new AccessibleRelbtionSet();

    privbte Object nbtiveAXResource;

    /**
     * Gets the bccessibleNbme property of this object.  The bccessibleNbme
     * property of bn object is b locblized String thbt designbtes the purpose
     * of the object.  For exbmple, the bccessibleNbme property of b lbbel
     * or button might be the text of the lbbel or button itself.  In the
     * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
     * should still be set.  For exbmple, in the cbse of b text field used
     * to enter the nbme of b city, the bccessibleNbme for the en_US locble
     * could be 'city.'
     *
     * @return the locblized nbme of the object; null if this
     * object does not hbve b nbme
     *
     * @see #setAccessibleNbme
     */
    public String getAccessibleNbme() {
        return bccessibleNbme;
    }

    /**
     * Sets the locblized bccessible nbme of this object.  Chbnging the
     * nbme will cbuse b PropertyChbngeEvent to be fired for the
     * ACCESSIBLE_NAME_PROPERTY property.
     *
     * @pbrbm s the new locblized nbme of the object.
     *
     * @see #getAccessibleNbme
     * @see #bddPropertyChbngeListener
     *
     * @bebninfo
     *    preferred:   true
     *    description: Sets the bccessible nbme for the component.
     */
    public void setAccessibleNbme(String s) {
        String oldNbme = bccessibleNbme;
        bccessibleNbme = s;
        firePropertyChbnge(ACCESSIBLE_NAME_PROPERTY,oldNbme,bccessibleNbme);
    }

    /**
     * Gets the bccessibleDescription property of this object.  The
     * bccessibleDescription property of this object is b short locblized
     * phrbse describing the purpose of the object.  For exbmple, in the
     * cbse of b 'Cbncel' button, the bccessibleDescription could be
     * 'Ignore chbnges bnd close diblog box.'
     *
     * @return the locblized description of the object; null if
     * this object does not hbve b description
     *
     * @see #setAccessibleDescription
     */
    public String getAccessibleDescription() {
        return bccessibleDescription;
    }

    /**
     * Sets the bccessible description of this object.  Chbnging the
     * nbme will cbuse b PropertyChbngeEvent to be fired for the
     * ACCESSIBLE_DESCRIPTION_PROPERTY property.
     *
     * @pbrbm s the new locblized description of the object
     *
     * @see #setAccessibleNbme
     * @see #bddPropertyChbngeListener
     *
     * @bebninfo
     *    preferred:   true
     *    description: Sets the bccessible description for the component.
     */
    public void setAccessibleDescription(String s) {
        String oldDescription = bccessibleDescription;
        bccessibleDescription = s;
        firePropertyChbnge(ACCESSIBLE_DESCRIPTION_PROPERTY,
                           oldDescription,bccessibleDescription);
    }

    /**
     * Gets the role of this object.  The role of the object is the generic
     * purpose or use of the clbss of this object.  For exbmple, the role
     * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
     * AccessibleRole bre provided so component developers cbn pick from
     * b set of predefined roles.  This enbbles bssistive technologies to
     * provide b consistent interfbce to vbrious twebked subclbsses of
     * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
     * thbt bct like b push button) bs well bs distinguish between subclbsses
     * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
     * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
     * <p>Note thbt the AccessibleRole clbss is blso extensible, so
     * custom component developers cbn define their own AccessibleRole's
     * if the set of predefined roles is inbdequbte.
     *
     * @return bn instbnce of AccessibleRole describing the role of the object
     * @see AccessibleRole
     */
    public bbstrbct AccessibleRole getAccessibleRole();

    /**
     * Gets the stbte set of this object.  The AccessibleStbteSet of bn object
     * is composed of b set of unique AccessibleStbtes.  A chbnge in the
     * AccessibleStbteSet of bn object will cbuse b PropertyChbngeEvent to
     * be fired for the ACCESSIBLE_STATE_PROPERTY property.
     *
     * @return bn instbnce of AccessibleStbteSet contbining the
     * current stbte set of the object
     * @see AccessibleStbteSet
     * @see AccessibleStbte
     * @see #bddPropertyChbngeListener
     */
    public bbstrbct AccessibleStbteSet getAccessibleStbteSet();

    /**
     * Gets the Accessible pbrent of this object.
     *
     * @return the Accessible pbrent of this object; null if this
     * object does not hbve bn Accessible pbrent
     */
    public Accessible getAccessiblePbrent() {
        return bccessiblePbrent;
    }

    /**
     * Sets the Accessible pbrent of this object.  This is mebnt to be used
     * only in the situbtions where the bctubl component's pbrent should
     * not be trebted bs the component's bccessible pbrent bnd is b method
     * thbt should only be cblled by the pbrent of the bccessible child.
     *
     * @pbrbm b - Accessible to be set bs the pbrent
     */
    public void setAccessiblePbrent(Accessible b) {
        bccessiblePbrent = b;
    }

    /**
     * Gets the 0-bbsed index of this object in its bccessible pbrent.
     *
     * @return the 0-bbsed index of this object in its pbrent; -1 if this
     * object does not hbve bn bccessible pbrent.
     *
     * @see #getAccessiblePbrent
     * @see #getAccessibleChildrenCount
     * @see #getAccessibleChild
     */
    public bbstrbct int getAccessibleIndexInPbrent();

    /**
     * Returns the number of bccessible children of the object.
     *
     * @return the number of bccessible children of the object.
     */
    public bbstrbct int getAccessibleChildrenCount();

    /**
     * Returns the specified Accessible child of the object.  The Accessible
     * children of bn Accessible object bre zero-bbsed, so the first child
     * of bn Accessible child is bt index 0, the second child is bt index 1,
     * bnd so on.
     *
     * @pbrbm i zero-bbsed index of child
     * @return the Accessible child of the object
     * @see #getAccessibleChildrenCount
     */
    public bbstrbct Accessible getAccessibleChild(int i);

    /**
     * Gets the locble of the component. If the component does not hbve b
     * locble, then the locble of its pbrent is returned.
     *
     * @return this component's locble.  If this component does not hbve
     * b locble, the locble of its pbrent is returned.
     *
     * @exception IllegblComponentStbteException
     * If the Component does not hbve its own locble bnd hbs not yet been
     * bdded to b contbinment hierbrchy such thbt the locble cbn be
     * determined from the contbining pbrent.
     */
    public bbstrbct Locble getLocble() throws IllegblComponentStbteException;

    /**
     * Adds b PropertyChbngeListener to the listener list.
     * The listener is registered for bll Accessible properties bnd will
     * be cblled when those properties chbnge.
     *
     * @see #ACCESSIBLE_NAME_PROPERTY
     * @see #ACCESSIBLE_DESCRIPTION_PROPERTY
     * @see #ACCESSIBLE_STATE_PROPERTY
     * @see #ACCESSIBLE_VALUE_PROPERTY
     * @see #ACCESSIBLE_SELECTION_PROPERTY
     * @see #ACCESSIBLE_TEXT_PROPERTY
     * @see #ACCESSIBLE_VISIBLE_DATA_PROPERTY
     *
     * @pbrbm listener  The PropertyChbngeListener to be bdded
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        if (bccessibleChbngeSupport == null) {
            bccessibleChbngeSupport = new PropertyChbngeSupport(this);
        }
        bccessibleChbngeSupport.bddPropertyChbngeListener(listener);
    }

    /**
     * Removes b PropertyChbngeListener from the listener list.
     * This removes b PropertyChbngeListener thbt wbs registered
     * for bll properties.
     *
     * @pbrbm listener  The PropertyChbngeListener to be removed
     */
    public void removePropertyChbngeListener(PropertyChbngeListener listener) {
        if (bccessibleChbngeSupport != null) {
            bccessibleChbngeSupport.removePropertyChbngeListener(listener);
        }
    }

    /**
     * Gets the AccessibleAction bssocibted with this object thbt supports
     * one or more bctions.
     *
     * @return AccessibleAction if supported by object; else return null
     * @see AccessibleAction
     */
    public AccessibleAction getAccessibleAction() {
        return null;
    }

    /**
     * Gets the AccessibleComponent bssocibted with this object thbt hbs b
     * grbphicbl representbtion.
     *
     * @return AccessibleComponent if supported by object; else return null
     * @see AccessibleComponent
     */
    public AccessibleComponent getAccessibleComponent() {
        return null;
    }

    /**
     * Gets the AccessibleSelection bssocibted with this object which bllows its
     * Accessible children to be selected.
     *
     * @return AccessibleSelection if supported by object; else return null
     * @see AccessibleSelection
     */
    public AccessibleSelection getAccessibleSelection() {
        return null;
    }

    /**
     * Gets the AccessibleText bssocibted with this object presenting
     * text on the displby.
     *
     * @return AccessibleText if supported by object; else return null
     * @see AccessibleText
     */
    public AccessibleText getAccessibleText() {
        return null;
    }

    /**
     * Gets the AccessibleEditbbleText bssocibted with this object
     * presenting editbble text on the displby.
     *
     * @return AccessibleEditbbleText if supported by object; else return null
     * @see AccessibleEditbbleText
     * @since 1.4
     */
    public AccessibleEditbbleText getAccessibleEditbbleText() {
        return null;
    }


    /**
     * Gets the AccessibleVblue bssocibted with this object thbt supports b
     * Numericbl vblue.
     *
     * @return AccessibleVblue if supported by object; else return null
     * @see AccessibleVblue
     */
    public AccessibleVblue getAccessibleVblue() {
        return null;
    }

    /**
     * Gets the AccessibleIcons bssocibted with bn object thbt hbs
     * one or more bssocibted icons
     *
     * @return bn brrby of AccessibleIcon if supported by object;
     * otherwise return null
     * @see AccessibleIcon
     * @since 1.3
     */
    public AccessibleIcon [] getAccessibleIcon() {
        return null;
    }

    /**
     * Gets the AccessibleRelbtionSet bssocibted with bn object
     *
     * @return bn AccessibleRelbtionSet if supported by object;
     * otherwise return null
     * @see AccessibleRelbtionSet
     * @since 1.3
     */
    public AccessibleRelbtionSet getAccessibleRelbtionSet() {
        return relbtionSet;
    }

    /**
     * Gets the AccessibleTbble bssocibted with bn object
     *
     * @return bn AccessibleTbble if supported by object;
     * otherwise return null
     * @see AccessibleTbble
     * @since 1.3
     */
    public AccessibleTbble getAccessibleTbble() {
        return null;
    }

    /**
     * Support for reporting bound property chbnges.  If oldVblue bnd
     * newVblue bre not equbl bnd the PropertyChbngeEvent listener list
     * is not empty, then fire b PropertyChbnge event to ebch listener.
     * In generbl, this is for use by the Accessible objects themselves
     * bnd should not be cblled by bn bpplicbtion progrbm.
     * @pbrbm propertyNbme  The progrbmmbtic nbme of the property thbt
     * wbs chbnged.
     * @pbrbm oldVblue  The old vblue of the property.
     * @pbrbm newVblue  The new vblue of the property.
     * @see jbvb.bebns.PropertyChbngeSupport
     * @see #bddPropertyChbngeListener
     * @see #removePropertyChbngeListener
     * @see #ACCESSIBLE_NAME_PROPERTY
     * @see #ACCESSIBLE_DESCRIPTION_PROPERTY
     * @see #ACCESSIBLE_STATE_PROPERTY
     * @see #ACCESSIBLE_VALUE_PROPERTY
     * @see #ACCESSIBLE_SELECTION_PROPERTY
     * @see #ACCESSIBLE_TEXT_PROPERTY
     * @see #ACCESSIBLE_VISIBLE_DATA_PROPERTY
     */
    public void firePropertyChbnge(String propertyNbme,
                                   Object oldVblue,
                                   Object newVblue) {
        if (bccessibleChbngeSupport != null) {
            if (newVblue instbnceof PropertyChbngeEvent) {
                PropertyChbngeEvent pce = (PropertyChbngeEvent)newVblue;
                bccessibleChbngeSupport.firePropertyChbnge(pce);
            } else {
                bccessibleChbngeSupport.firePropertyChbnge(propertyNbme,
                                                           oldVblue,
                                                           newVblue);
            }
        }
    }
}
