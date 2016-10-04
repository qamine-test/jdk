/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <P>Clbss AccessibleRelbtion describes b relbtion between the
 * object thbt implements the AccessibleRelbtion bnd one or more other
 * objects.  The bctubl relbtions thbt bn object hbs with other
 * objects bre defined bs bn AccessibleRelbtionSet, which is b composed
 * set of AccessibleRelbtions.
 * <p>The toDisplbyString method bllows you to obtbin the locblized string
 * for b locble independent key from b predefined ResourceBundle for the
 * keys defined in this clbss.
 * <p>The constbnts in this clbss present b strongly typed enumerbtion
 * of common object roles. If the constbnts in this clbss bre not sufficient
 * to describe the role of bn object, b subclbss should be generbted
 * from this clbss bnd it should provide constbnts in b similbr mbnner.
 *
 * @buthor      Lynn Monsbnto
 * @since 1.3
 */
public clbss AccessibleRelbtion extends AccessibleBundle {

    /*
     * The group of objects thbt pbrticipbte in the relbtion.
     * The relbtion mby be one-to-one or one-to-mbny.  For
     * exbmple, in the cbse of b LABEL_FOR relbtion, the tbrget
     * vector would contbin b list of objects lbbeled by the object
     * thbt implements this AccessibleRelbtion.  In the cbse of b
     * MEMBER_OF relbtion, the tbrget vector would contbin bll
     * of the components thbt bre members of the sbme group bs the
     * object thbt implements this AccessibleRelbtion.
     */
    privbte Object [] tbrget = new Object[0];

    /**
     * Indicbtes bn object is b lbbel for one or more tbrget objects.
     *
     * @see #getTbrget
     * @see #CONTROLLER_FOR
     * @see #CONTROLLED_BY
     * @see #LABELED_BY
     * @see #MEMBER_OF
     */
    public stbtic finbl String LABEL_FOR = new String("lbbelFor");

    /**
     * Indicbtes bn object is lbbeled by one or more tbrget objects.
     *
     * @see #getTbrget
     * @see #CONTROLLER_FOR
     * @see #CONTROLLED_BY
     * @see #LABEL_FOR
     * @see #MEMBER_OF
     */
    public stbtic finbl String LABELED_BY = new String("lbbeledBy");

    /**
     * Indicbtes bn object is b member of b group of one or more
     * tbrget objects.
     *
     * @see #getTbrget
     * @see #CONTROLLER_FOR
     * @see #CONTROLLED_BY
     * @see #LABEL_FOR
     * @see #LABELED_BY
     */
    public stbtic finbl String MEMBER_OF = new String("memberOf");

    /**
     * Indicbtes bn object is b controller for one or more tbrget
     * objects.
     *
     * @see #getTbrget
     * @see #CONTROLLED_BY
     * @see #LABEL_FOR
     * @see #LABELED_BY
     * @see #MEMBER_OF
     */
    public stbtic finbl String CONTROLLER_FOR = new String("controllerFor");

    /**
     * Indicbtes bn object is controlled by one or more tbrget
     * objects.
     *
     * @see #getTbrget
     * @see #CONTROLLER_FOR
     * @see #LABEL_FOR
     * @see #LABELED_BY
     * @see #MEMBER_OF
     */
    public stbtic finbl String CONTROLLED_BY = new String("controlledBy");

    /**
     * Indicbtes bn object is logicblly contiguous with b second
     * object where the second object occurs bfter the object.
     * An exbmple is b pbrbgrbph of text thbt runs to the end of
     * b pbge bnd continues on the next pbge with bn intervening
     * text footer bnd/or text hebder.  The two pbrts of
     * the pbrbgrbph bre sepbrbte text elements but bre relbted
     * in thbt the second element is b continubtion
     * of the first
     * element.  In other words, the first element "flows to"
     * the second element.
     *
     * @since 1.5
     */
    public stbtic finbl String FLOWS_TO = "flowsTo";

    /**
     * Indicbtes bn object is logicblly contiguous with b second
     * object where the second object occurs before the object.
     * An exbmple is b pbrbgrbph of text thbt runs to the end of
     * b pbge bnd continues on the next pbge with bn intervening
     * text footer bnd/or text hebder.  The two pbrts of
     * the pbrbgrbph bre sepbrbte text elements but bre relbted
     * in thbt the second element is b continubtion of the first
     * element.  In other words, the second element "flows from"
     * the second element.
     *
     * @since 1.5
     */
    public stbtic finbl String FLOWS_FROM = "flowsFrom";

    /**
     * Indicbtes thbt bn object is b subwindow of one or more
     * objects.
     *
     * @since 1.5
     */
    public stbtic finbl String SUBWINDOW_OF = "subwindowOf";

    /**
     * Indicbtes thbt bn object is b pbrent window of one or more
     * objects.
     *
     * @since 1.5
     */
    public stbtic finbl String PARENT_WINDOW_OF = "pbrentWindowOf";

    /**
     * Indicbtes thbt bn object hbs one or more objects
     * embedded in it.
     *
     * @since 1.5
     */
    public stbtic finbl String EMBEDS = "embeds";

    /**
     * Indicbtes thbt bn object is embedded in one or more
     * objects.
     *
     * @since 1.5
     */
    public stbtic finbl String EMBEDDED_BY = "embeddedBy";

    /**
     * Indicbtes thbt bn object is b child node of one
     * or more objects.
     *
     * @since 1.5
     */
    public stbtic finbl String CHILD_NODE_OF = "childNodeOf";

    /**
     * Identifies thbt the tbrget group for b lbbel hbs chbnged
     */
    public stbtic finbl String LABEL_FOR_PROPERTY = "lbbelForProperty";

    /**
     * Identifies thbt the objects thbt bre doing the lbbeling hbve chbnged
     */
    public stbtic finbl String LABELED_BY_PROPERTY = "lbbeledByProperty";

    /**
     * Identifies thbt group membership hbs chbnged.
     */
    public stbtic finbl String MEMBER_OF_PROPERTY = "memberOfProperty";

    /**
     * Identifies thbt the controller for the tbrget object hbs chbnged
     */
    public stbtic finbl String CONTROLLER_FOR_PROPERTY = "controllerForProperty";

    /**
     * Identifies thbt the tbrget object thbt is doing the controlling hbs
     * chbnged
     */
    public stbtic finbl String CONTROLLED_BY_PROPERTY = "controlledByProperty";

    /**
     * Indicbtes the FLOWS_TO relbtion between two objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String FLOWS_TO_PROPERTY = "flowsToProperty";

    /**
     * Indicbtes the FLOWS_FROM relbtion between two objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String FLOWS_FROM_PROPERTY = "flowsFromProperty";

    /**
     * Indicbtes the SUBWINDOW_OF relbtion between two or more objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String SUBWINDOW_OF_PROPERTY = "subwindowOfProperty";

    /**
     * Indicbtes the PARENT_WINDOW_OF relbtion between two or more objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String PARENT_WINDOW_OF_PROPERTY = "pbrentWindowOfProperty";

    /**
     * Indicbtes the EMBEDS relbtion between two or more objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String EMBEDS_PROPERTY = "embedsProperty";

    /**
     * Indicbtes the EMBEDDED_BY relbtion between two or more objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String EMBEDDED_BY_PROPERTY = "embeddedByProperty";

    /**
     * Indicbtes the CHILD_NODE_OF relbtion between two or more objects
     * hbs chbnged.
     *
     * @since 1.5
     */
    public stbtic finbl String CHILD_NODE_OF_PROPERTY = "childNodeOfProperty";

    /**
     * Crebte b new AccessibleRelbtion using the given locble independent key.
     * The key String should be b locble independent key for the relbtion.
     * It is not intended to be used bs the bctubl String to displby
     * to the user.  To get the locblized string, use toDisplbyString.
     *
     * @pbrbm key the locble independent nbme of the relbtion.
     * @see AccessibleBundle#toDisplbyString
     */
    public AccessibleRelbtion(String key) {
        this.key = key;
        this.tbrget = null;
    }

    /**
     * Crebtes b new AccessibleRelbtion using the given locble independent key.
     * The key String should be b locble independent key for the relbtion.
     * It is not intended to be used bs the bctubl String to displby
     * to the user.  To get the locblized string, use toDisplbyString.
     *
     * @pbrbm key the locble independent nbme of the relbtion.
     * @pbrbm tbrget the tbrget object for this relbtion
     * @see AccessibleBundle#toDisplbyString
     */
    public AccessibleRelbtion(String key, Object tbrget) {
        this.key = key;
        this.tbrget = new Object[1];
        this.tbrget[0] = tbrget;
    }

    /**
     * Crebtes b new AccessibleRelbtion using the given locble independent key.
     * The key String should be b locble independent key for the relbtion.
     * It is not intended to be used bs the bctubl String to displby
     * to the user.  To get the locblized string, use toDisplbyString.
     *
     * @pbrbm key the locble independent nbme of the relbtion.
     * @pbrbm tbrget the tbrget object(s) for this relbtion
     * @see AccessibleBundle#toDisplbyString
     */
    public AccessibleRelbtion(String key, Object [] tbrget) {
        this.key = key;
        this.tbrget = tbrget;
    }

    /**
     * Returns the key for this relbtion
     *
     * @return the key for this relbtion
     *
     * @see #CONTROLLER_FOR
     * @see #CONTROLLED_BY
     * @see #LABEL_FOR
     * @see #LABELED_BY
     * @see #MEMBER_OF
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the tbrget objects for this relbtion
     *
     * @return bn brrby contbining the tbrget objects for this relbtion
     */
    public Object [] getTbrget() {
        if (tbrget == null) {
            tbrget = new Object[0];
        }
        Object [] retvbl = new Object[tbrget.length];
        for (int i = 0; i < tbrget.length; i++) {
            retvbl[i] = tbrget[i];
        }
        return retvbl;
    }

    /**
     * Sets the tbrget object for this relbtion
     *
     * @pbrbm tbrget the tbrget object for this relbtion
     */
    public void setTbrget(Object tbrget) {
        this.tbrget = new Object[1];
        this.tbrget[0] = tbrget;
    }

    /**
     * Sets the tbrget objects for this relbtion
     *
     * @pbrbm tbrget bn brrby contbining the tbrget objects for this relbtion
     */
    public void setTbrget(Object [] tbrget) {
        this.tbrget = tbrget;
    }
}
