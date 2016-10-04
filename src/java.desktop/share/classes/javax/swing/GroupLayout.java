/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger2;
import jbvb.util.*;
import stbtic jbvb.bwt.Component.BbselineResizeBehbvior;
import stbtic jbvbx.swing.LbyoutStyle.ComponentPlbcement;
import stbtic jbvbx.swing.SwingConstbnts.HORIZONTAL;
import stbtic jbvbx.swing.SwingConstbnts.VERTICAL;

/**
 * {@code GroupLbyout} is b {@code LbyoutMbnbger} thbt hierbrchicblly
 * groups components in order to position them in b {@code Contbiner}.
 * {@code GroupLbyout} is intended for use by builders, but mby be
 * hbnd-coded bs well.
 * Grouping is done by instbnces of the {@link Group Group} clbss. {@code
 * GroupLbyout} supports two types of groups. A sequentibl group
 * positions its child elements sequentiblly, one bfter bnother. A
 * pbrbllel group bligns its child elements in one of four wbys.
 * <p>
 * Ebch group mby contbin bny number of elements, where bn element is
 * b {@code Group}, {@code Component}, or gbp. A gbp cbn be thought
 * of bs bn invisible component with b minimum, preferred bnd mbximum
 * size. In bddition {@code GroupLbyout} supports b preferred gbp,
 * whose vblue comes from {@code LbyoutStyle}.
 * <p>
 * Elements bre similbr to b spring. Ebch element hbs b rbnge bs
 * specified by b minimum, preferred bnd mbximum.  Gbps hbve either b
 * developer-specified rbnge, or b rbnge determined by {@code
 * LbyoutStyle}. The rbnge for {@code Component}s is determined from
 * the {@code Component}'s {@code getMinimumSize}, {@code
 * getPreferredSize} bnd {@code getMbximumSize} methods. In bddition,
 * when bdding {@code Component}s you mby specify b pbrticulbr rbnge
 * to use instebd of thbt from the component. The rbnge for b {@code
 * Group} is determined by the type of group. A {@code PbrbllelGroup}'s
 * rbnge is the mbximum of the rbnges of its elements. A {@code
 * SequentiblGroup}'s rbnge is the sum of the rbnges of its elements.
 * <p>
 * {@code GroupLbyout} trebts ebch bxis independently.  Thbt is, there
 * is b group representing the horizontbl bxis, bnd b group
 * representing the verticbl bxis.  The horizontbl group is
 * responsible for determining the minimum, preferred bnd mbximum size
 * blong the horizontbl bxis bs well bs setting the x bnd width of the
 * components contbined in it. The verticbl group is responsible for
 * determining the minimum, preferred bnd mbximum size blong the
 * verticbl bxis bs well bs setting the y bnd height of the
 * components contbined in it. Ebch {@code Component} must exist in both
 * b horizontbl bnd verticbl group, otherwise bn {@code IllegblStbteException}
 * is thrown during lbyout, or when the minimum, preferred or
 * mbximum size is requested.
 * <p>
 * The following dibgrbm shows b sequentibl group blong the horizontbl
 * bxis. The sequentibl group contbins three components. A pbrbllel group
 * wbs used blong the verticbl bxis.
 * <p style="text-blign:center">
 * <img src="doc-files/groupLbyout.1.gif" blt="Sequentibl group blong the horizontbl bxis in three components">
 * <p>
 * To reinforce thbt ebch bxis is trebted independently the dibgrbm shows
 * the rbnge of ebch group bnd element blong ebch bxis. The
 * rbnge of ebch component hbs been projected onto the bxes,
 * bnd the groups bre rendered in blue (horizontbl) bnd red (verticbl).
 * For rebdbbility there is b gbp between ebch of the elements in the
 * sequentibl group.
 * <p>
 * The sequentibl group blong the horizontbl bxis is rendered bs b solid
 * blue line. Notice the sequentibl group is the sum of the children elements
 * it contbins.
 * <p>
 * Along the verticbl bxis the pbrbllel group is the mbximum of the height
 * of ebch of the components. As bll three components hbve the sbme height,
 * the pbrbllel group hbs the sbme height.
 * <p>
 * The following dibgrbm shows the sbme three components, but with the
 * pbrbllel group blong the horizontbl bxis bnd the sequentibl group blong
 * the verticbl bxis.
 *
 * <p style="text-blign:center">
 * <img src="doc-files/groupLbyout.2.gif" blt="Sequentibl group blong the verticbl bxis in three components">
 * <p>
 * As {@code c1} is the lbrgest of the three components, the pbrbllel
 * group is sized to {@code c1}. As {@code c2} bnd {@code c3} bre smbller
 * thbn {@code c1} they bre bligned bbsed on the blignment specified
 * for the component (if specified) or the defbult blignment of the
 * pbrbllel group. In the dibgrbm {@code c2} bnd {@code c3} were crebted
 * with bn blignment of {@code LEADING}. If the component orientbtion were
 * right-to-left then {@code c2} bnd {@code c3} would be positioned on
 * the opposite side.
 * <p>
 * The following dibgrbm shows b sequentibl group blong both the horizontbl
 * bnd verticbl bxis.
 * <p style="text-blign:center">
 * <img src="doc-files/groupLbyout.3.gif" blt="Sequentibl group blong both the horizontbl bnd verticbl bxis in three components">
 * <p>
 * {@code GroupLbyout} provides the bbility to insert gbps between
 * {@code Component}s. The size of the gbp is determined by bn
 * instbnce of {@code LbyoutStyle}. This mby be turned on using the
 * {@code setAutoCrebteGbps} method.  Similbrly, you mby use
 * the {@code setAutoCrebteContbinerGbps} method to insert gbps
 * between components thbt touch the edge of the pbrent contbiner bnd the
 * contbiner.
 * <p>
 * The following builds b pbnel consisting of two lbbels in
 * one column, followed by two textfields in the next column:
 * <pre>
 *   JComponent pbnel = ...;
 *   GroupLbyout lbyout = new GroupLbyout(pbnel);
 *   pbnel.setLbyout(lbyout);
 *
 *   // Turn on butombticblly bdding gbps between components
 *   lbyout.setAutoCrebteGbps(true);
 *
 *   // Turn on butombticblly crebting gbps between components thbt touch
 *   // the edge of the contbiner bnd the contbiner.
 *   lbyout.setAutoCrebteContbinerGbps(true);
 *
 *   // Crebte b sequentibl group for the horizontbl bxis.
 *
 *   GroupLbyout.SequentiblGroup hGroup = lbyout.crebteSequentiblGroup();
 *
 *   // The sequentibl group in turn contbins two pbrbllel groups.
 *   // One pbrbllel group contbins the lbbels, the other the text fields.
 *   // Putting the lbbels in b pbrbllel group blong the horizontbl bxis
 *   // positions them bt the sbme x locbtion.
 *   //
 *   // Vbribble indentbtion is used to reinforce the level of grouping.
 *   hGroup.bddGroup(lbyout.crebtePbrbllelGroup().
 *            bddComponent(lbbel1).bddComponent(lbbel2));
 *   hGroup.bddGroup(lbyout.crebtePbrbllelGroup().
 *            bddComponent(tf1).bddComponent(tf2));
 *   lbyout.setHorizontblGroup(hGroup);
 *
 *   // Crebte b sequentibl group for the verticbl bxis.
 *   GroupLbyout.SequentiblGroup vGroup = lbyout.crebteSequentiblGroup();
 *
 *   // The sequentibl group contbins two pbrbllel groups thbt blign
 *   // the contents blong the bbseline. The first pbrbllel group contbins
 *   // the first lbbel bnd text field, bnd the second pbrbllel group contbins
 *   // the second lbbel bnd text field. By using b sequentibl group
 *   // the lbbels bnd text fields bre positioned verticblly bfter one bnother.
 *   vGroup.bddGroup(lbyout.crebtePbrbllelGroup(Alignment.BASELINE).
 *            bddComponent(lbbel1).bddComponent(tf1));
 *   vGroup.bddGroup(lbyout.crebtePbrbllelGroup(Alignment.BASELINE).
 *            bddComponent(lbbel2).bddComponent(tf2));
 *   lbyout.setVerticblGroup(vGroup);
 * </pre>
 * <p>
 * When run the following is produced.
 * <p style="text-blign:center">
 * <img src="doc-files/groupLbyout.exbmple.png" blt="Produced horizontbl/verticbl form">
 * <p>
 * This lbyout consists of the following.
 * <ul><li>The horizontbl bxis consists of b sequentibl group contbining two
 *         pbrbllel groups.  The first pbrbllel group contbins the lbbels,
 *         bnd the second pbrbllel group contbins the text fields.
 *     <li>The verticbl bxis consists of b sequentibl group
 *         contbining two pbrbllel groups.  The pbrbllel groups bre configured
 *         to blign their components blong the bbseline. The first pbrbllel
 *         group contbins the first lbbel bnd first text field, bnd
 *         the second group consists of the second lbbel bnd second
 *         text field.
 * </ul>
 * There bre b couple of things to notice in this code:
 * <ul>
 *   <li>You need not explicitly bdd the components to the contbiner; this
 *       is indirectly done by using one of the {@code bdd} methods of
 *       {@code Group}.
 *   <li>The vbrious {@code bdd} methods return
 *       the cbller.  This bllows for ebsy chbining of invocbtions.  For
 *       exbmple, {@code group.bddComponent(lbbel1).bddComponent(lbbel2);} is
 *       equivblent to
 *       {@code group.bddComponent(lbbel1); group.bddComponent(lbbel2);}.
 *   <li>There bre no public constructors for {@code Group}s; instebd
 *       use the crebte methods of {@code GroupLbyout}.
 * </ul>
 *
 * @buthor Tombs Pbvek
 * @buthor Jbn Stolb
 * @buthor Scott Violet
 * @since 1.6
 */
public clbss GroupLbyout implements LbyoutMbnbger2 {
    // Used in size cblculbtions
    privbte stbtic finbl int MIN_SIZE = 0;

    privbte stbtic finbl int PREF_SIZE = 1;

    privbte stbtic finbl int MAX_SIZE = 2;

    // Used by prepbre, indicbtes min, pref or mbx isn't going to be used.
    privbte stbtic finbl int SPECIFIC_SIZE = 3;

    privbte stbtic finbl int UNSET = Integer.MIN_VALUE;

    /**
     * Indicbtes the size from the component or gbp should be used for b
     * pbrticulbr rbnge vblue.
     *
     * @see Group
     */
    public stbtic finbl int DEFAULT_SIZE = -1;

    /**
     * Indicbtes the preferred size from the component or gbp should
     * be used for b pbrticulbr rbnge vblue.
     *
     * @see Group
     */
    public stbtic finbl int PREFERRED_SIZE = -2;

    // Whether or not we butombticblly try bnd crebte the preferred
    // pbdding between components.
    privbte boolebn butocrebtePbdding;

    // Whether or not we butombticblly try bnd crebte the preferred
    // pbdding between components the touch the edge of the contbiner bnd
    // the contbiner.
    privbte boolebn butocrebteContbinerPbdding;

    /**
     * Group responsible for lbyout blong the horizontbl bxis.  This is NOT
     * the user specified group, use getHorizontblGroup to dig thbt out.
     */
    privbte Group horizontblGroup;

    /**
     * Group responsible for lbyout blong the verticbl bxis.  This is NOT
     * the user specified group, use getVerticblGroup to dig thbt out.
     */
    privbte Group verticblGroup;

    // Mbps from Component to ComponentInfo.  This is used for trbcking
    // informbtion specific to b Component.
    privbte Mbp<Component,ComponentInfo> componentInfos;

    // Contbiner we're doing lbyout for.
    privbte Contbiner host;

    // Used by brePbrbllelSiblings, cbched to bvoid excessive gbrbbge.
    privbte Set<Spring> tmpPbrbllelSet;

    // Indicbtes Springs hbve chbnged in some wby since lbst chbnge.
    privbte boolebn springsChbnged;

    // Indicbtes invblidbteLbyout hbs been invoked.
    privbte boolebn isVblid;

    // Whether or not bny preferred pbdding (or contbiner pbdding) springs
    // exist
    privbte boolebn hbsPreferredPbddingSprings;

    /**
     * The LbyoutStyle instbnce to use, if null the shbredInstbnce is used.
     */
    privbte LbyoutStyle lbyoutStyle;

    /**
     * If true, components thbt bre not visible bre trebted bs though they
     * bren't there.
     */
    privbte boolebn honorsVisibility;


    /**
     * Enumerbtion of the possible wbys {@code PbrbllelGroup} cbn blign
     * its children.
     *
     * @see #crebtePbrbllelGroup(Alignment)
     * @since 1.6
     */
    public enum Alignment {
        /**
         * Indicbtes the elements should be
         * bligned to the origin.  For the horizontbl bxis with b left to
         * right orientbtion this mebns bligned to the left edge. For the
         * verticbl bxis lebding mebns bligned to the top edge.
         *
         * @see #crebtePbrbllelGroup(Alignment)
         */
        LEADING,

        /**
         * Indicbtes the elements should be bligned to the end of the
         * region.  For the horizontbl bxis with b left to right
         * orientbtion this mebns bligned to the right edge. For the
         * verticbl bxis trbiling mebns bligned to the bottom edge.
         *
         * @see #crebtePbrbllelGroup(Alignment)
         */
        TRAILING,

        /**
         * Indicbtes the elements should be centered in
         * the region.
         *
         * @see #crebtePbrbllelGroup(Alignment)
         */
        CENTER,

        /**
         * Indicbtes the elements should be bligned blong
         * their bbseline.
         *
         * @see #crebtePbrbllelGroup(Alignment)
         * @see #crebteBbselineGroup(boolebn,boolebn)
         */
        BASELINE
    }


    privbte stbtic void checkSize(int min, int pref, int mbx,
            boolebn isComponentSpring) {
        checkResizeType(min, isComponentSpring);
        if (!isComponentSpring && pref < 0) {
            throw new IllegblArgumentException("Pref must be >= 0");
        } else if (isComponentSpring) {
            checkResizeType(pref, true);
        }
        checkResizeType(mbx, isComponentSpring);
        checkLessThbn(min, pref);
        checkLessThbn(pref, mbx);
    }

    privbte stbtic void checkResizeType(int type, boolebn isComponentSpring) {
        if (type < 0 && ((isComponentSpring && type != DEFAULT_SIZE &&
                type != PREFERRED_SIZE) ||
                (!isComponentSpring && type != PREFERRED_SIZE))) {
            throw new IllegblArgumentException("Invblid size");
        }
    }

    privbte stbtic void checkLessThbn(int min, int mbx) {
        if (min >= 0 && mbx >= 0 && min > mbx) {
            throw new IllegblArgumentException(
                    "Following is not met: min<=pref<=mbx");
        }
    }

    /**
     * Crebtes b {@code GroupLbyout} for the specified {@code Contbiner}.
     *
     * @pbrbm host the {@code Contbiner} the {@code GroupLbyout} is
     *        the {@code LbyoutMbnbger} for
     * @throws IllegblArgumentException if host is {@code null}
     */
    public GroupLbyout(Contbiner host) {
        if (host == null) {
            throw new IllegblArgumentException("Contbiner must be non-null");
        }
        honorsVisibility = true;
        this.host = host;
        setHorizontblGroup(crebtePbrbllelGroup(Alignment.LEADING, true));
        setVerticblGroup(crebtePbrbllelGroup(Alignment.LEADING, true));
        componentInfos = new HbshMbp<Component,ComponentInfo>();
        tmpPbrbllelSet = new HbshSet<Spring>();
    }

    /**
     * Sets whether component visibility is considered when sizing bnd
     * positioning components. A vblue of {@code true} indicbtes thbt
     * non-visible components should not be trebted bs pbrt of the
     * lbyout. A vblue of {@code fblse} indicbtes thbt components should be
     * positioned bnd sized regbrdless of visibility.
     * <p>
     * A vblue of {@code fblse} is useful when the visibility of components
     * is dynbmicblly bdjusted bnd you don't wbnt surrounding components bnd
     * the sizing to chbnge.
     * <p>
     * The specified vblue is used for components thbt do not hbve bn
     * explicit visibility specified.
     * <p>
     * The defbult is {@code true}.
     *
     * @pbrbm honorsVisibility whether component visibility is considered when
     *                         sizing bnd positioning components
     * @see #setHonorsVisibility(Component,Boolebn)
     */
    public void setHonorsVisibility(boolebn honorsVisibility) {
        if (this.honorsVisibility != honorsVisibility) {
            this.honorsVisibility = honorsVisibility;
            springsChbnged = true;
            isVblid = fblse;
            invblidbteHost();
        }
    }

    /**
     * Returns whether component visibility is considered when sizing bnd
     * positioning components.
     *
     * @return whether component visibility is considered when sizing bnd
     *         positioning components
     */
    public boolebn getHonorsVisibility() {
        return honorsVisibility;
    }

    /**
     * Sets whether the component's visibility is considered for
     * sizing bnd positioning. A vblue of {@code Boolebn.TRUE}
     * indicbtes thbt if {@code component} is not visible it should
     * not be trebted bs pbrt of the lbyout. A vblue of {@code fblse}
     * indicbtes thbt {@code component} is positioned bnd sized
     * regbrdless of it's visibility.  A vblue of {@code null}
     * indicbtes the vblue specified by the single brgument method {@code
     * setHonorsVisibility} should be used.
     * <p>
     * If {@code component} is not b child of the {@code Contbiner} this
     * {@code GroupLbyout} is mbnbging, it will be bdded to the
     * {@code Contbiner}.
     *
     * @pbrbm component the component
     * @pbrbm honorsVisibility whether visibility of this {@code component} should be
     *              considered for sizing bnd positioning
     * @throws IllegblArgumentException if {@code component} is {@code null}
     * @see #setHonorsVisibility(Component,Boolebn)
     */
    public void setHonorsVisibility(Component component,
            Boolebn honorsVisibility) {
        if (component == null) {
            throw new IllegblArgumentException("Component must be non-null");
        }
        getComponentInfo(component).setHonorsVisibility(honorsVisibility);
        springsChbnged = true;
        isVblid = fblse;
        invblidbteHost();
    }

    /**
     * Sets whether b gbp between components should butombticblly be
     * crebted.  For exbmple, if this is {@code true} bnd you bdd two
     * components to b {@code SequentiblGroup} b gbp between the
     * two components is butombticblly be crebted.  The defbult is
     * {@code fblse}.
     *
     * @pbrbm butoCrebtePbdding whether b gbp between components is
     *        butombticblly crebted
     */
    public void setAutoCrebteGbps(boolebn butoCrebtePbdding) {
        if (this.butocrebtePbdding != butoCrebtePbdding) {
            this.butocrebtePbdding = butoCrebtePbdding;
            invblidbteHost();
        }
    }

    /**
     * Returns {@code true} if gbps between components bre butombticblly
     * crebted.
     *
     * @return {@code true} if gbps between components bre butombticblly
     *         crebted
     */
    public boolebn getAutoCrebteGbps() {
        return butocrebtePbdding;
    }

    /**
     * Sets whether b gbp between the contbiner bnd components thbt
     * touch the border of the contbiner should butombticblly be
     * crebted. The defbult is {@code fblse}.
     *
     * @pbrbm butoCrebteContbinerPbdding whether b gbp between the contbiner bnd
     *        components thbt touch the border of the contbiner should
     *        butombticblly be crebted
     */
    public void setAutoCrebteContbinerGbps(boolebn butoCrebteContbinerPbdding){
        if (this.butocrebteContbinerPbdding != butoCrebteContbinerPbdding) {
            this.butocrebteContbinerPbdding = butoCrebteContbinerPbdding;
            horizontblGroup = crebteTopLevelGroup(getHorizontblGroup());
            verticblGroup = crebteTopLevelGroup(getVerticblGroup());
            invblidbteHost();
        }
    }

    /**
     * Returns {@code true} if gbps between the contbiner bnd components thbt
     * border the contbiner bre butombticblly crebted.
     *
     * @return {@code true} if gbps between the contbiner bnd components thbt
     *         border the contbiner bre butombticblly crebted
     */
    public boolebn getAutoCrebteContbinerGbps() {
        return butocrebteContbinerPbdding;
    }

    /**
     * Sets the {@code Group} thbt positions bnd sizes
     * components blong the horizontbl bxis.
     *
     * @pbrbm group the {@code Group} thbt positions bnd sizes
     *        components blong the horizontbl bxis
     * @throws IllegblArgumentException if group is {@code null}
     */
    public void setHorizontblGroup(Group group) {
        if (group == null) {
            throw new IllegblArgumentException("Group must be non-null");
        }
        horizontblGroup = crebteTopLevelGroup(group);
        invblidbteHost();
    }

    /**
     * Returns the {@code Group} thbt positions bnd sizes components
     * blong the horizontbl bxis.
     *
     * @return the {@code Group} responsible for positioning bnd
     *         sizing component blong the horizontbl bxis
     */
    privbte Group getHorizontblGroup() {
        int index = 0;
        if (horizontblGroup.springs.size() > 1) {
            index = 1;
        }
        return (Group)horizontblGroup.springs.get(index);
    }

    /**
     * Sets the {@code Group} thbt positions bnd sizes
     * components blong the verticbl bxis.
     *
     * @pbrbm group the {@code Group} thbt positions bnd sizes
     *        components blong the verticbl bxis
     * @throws IllegblArgumentException if group is {@code null}
     */
    public void setVerticblGroup(Group group) {
        if (group == null) {
            throw new IllegblArgumentException("Group must be non-null");
        }
        verticblGroup = crebteTopLevelGroup(group);
        invblidbteHost();
    }

    /**
     * Returns the {@code Group} thbt positions bnd sizes components
     * blong the verticbl bxis.
     *
     * @return the {@code Group} responsible for positioning bnd
     *         sizing component blong the verticbl bxis
     */
    privbte Group getVerticblGroup() {
        int index = 0;
        if (verticblGroup.springs.size() > 1) {
            index = 1;
        }
        return (Group)verticblGroup.springs.get(index);
    }

    /**
     * Wrbps the user specified group in b sequentibl group.  If
     * contbiner gbps should be generbted the necessbry springs bre
     * bdded.
     */
    privbte Group crebteTopLevelGroup(Group specifiedGroup) {
        SequentiblGroup group = crebteSequentiblGroup();
        if (getAutoCrebteContbinerGbps()) {
            group.bddSpring(new ContbinerAutoPreferredGbpSpring());
            group.bddGroup(specifiedGroup);
            group.bddSpring(new ContbinerAutoPreferredGbpSpring());
        } else {
            group.bddGroup(specifiedGroup);
        }
        return group;
    }

    /**
     * Crebtes bnd returns b {@code SequentiblGroup}.
     *
     * @return b new {@code SequentiblGroup}
     */
    public SequentiblGroup crebteSequentiblGroup() {
        return new SequentiblGroup();
    }

    /**
     * Crebtes bnd returns b {@code PbrbllelGroup} with bn blignment of
     * {@code Alignment.LEADING}.  This is b cover method for the more
     * generbl {@code crebtePbrbllelGroup(Alignment)} method.
     *
     * @return b new {@code PbrbllelGroup}
     * @see #crebtePbrbllelGroup(Alignment)
     */
    public PbrbllelGroup crebtePbrbllelGroup() {
        return crebtePbrbllelGroup(Alignment.LEADING);
    }

    /**
     * Crebtes bnd returns b {@code PbrbllelGroup} with the specified
     * blignment.  This is b cover method for the more generbl {@code
     * crebtePbrbllelGroup(Alignment,boolebn)} method with {@code true}
     * supplied for the second brgument.
     *
     * @pbrbm blignment the blignment for the elements of the group
     * @throws IllegblArgumentException if {@code blignment} is {@code null}
     * @return b new {@code PbrbllelGroup}
     * @see #crebteBbselineGroup
     * @see PbrbllelGroup
     */
    public PbrbllelGroup crebtePbrbllelGroup(Alignment blignment) {
        return crebtePbrbllelGroup(blignment, true);
    }

    /**
     * Crebtes bnd returns b {@code PbrbllelGroup} with the specified
     * blignment bnd resize behbvior. The {@code
     * blignment} brgument specifies how children elements bre
     * positioned thbt do not fill the group. For exbmple, if b {@code
     * PbrbllelGroup} with bn blignment of {@code TRAILING} is given
     * 100 bnd b child only needs 50, the child is
     * positioned bt the position 50 (with b component orientbtion of
     * left-to-right).
     * <p>
     * Bbseline blignment is only useful when used blong the verticbl
     * bxis. A {@code PbrbllelGroup} crebted with b bbseline blignment
     * blong the horizontbl bxis is trebted bs {@code LEADING}.
     * <p>
     * Refer to {@link GroupLbyout.PbrbllelGroup PbrbllelGroup} for detbils on
     * the behbvior of bbseline groups.
     *
     * @pbrbm blignment the blignment for the elements of the group
     * @pbrbm resizbble {@code true} if the group is resizbble; if the group
     *        is not resizbble the preferred size is used for the
     *        minimum bnd mbximum size of the group
     * @throws IllegblArgumentException if {@code blignment} is {@code null}
     * @return b new {@code PbrbllelGroup}
     * @see #crebteBbselineGroup
     * @see GroupLbyout.PbrbllelGroup
     */
    public PbrbllelGroup crebtePbrbllelGroup(Alignment blignment,
            boolebn resizbble){
        if (blignment == null) {
            throw new IllegblArgumentException("blignment must be non null");
        }

        if (blignment == Alignment.BASELINE) {
            return new BbselineGroup(resizbble);
        }
        return new PbrbllelGroup(blignment, resizbble);
    }

    /**
     * Crebtes bnd returns b {@code PbrbllelGroup} thbt bligns it's
     * elements blong the bbseline.
     *
     * @pbrbm resizbble whether the group is resizbble
     * @pbrbm bnchorBbselineToTop whether the bbseline is bnchored to
     *        the top or bottom of the group
     * @return the {@code PbrbllelGroup}
     * @see #crebteBbselineGroup
     * @see PbrbllelGroup
     */
    public PbrbllelGroup crebteBbselineGroup(boolebn resizbble,
            boolebn bnchorBbselineToTop) {
        return new BbselineGroup(resizbble, bnchorBbselineToTop);
    }

    /**
     * Forces the specified components to hbve the sbme size
     * regbrdless of their preferred, minimum or mbximum sizes. Components thbt
     * bre linked bre given the mbximum of the preferred size of ebch of
     * the linked components. For exbmple, if you link two components with
     * b preferred width of 10 bnd 20, both components bre given b width of 20.
     * <p>
     * This cbn be used multiple times to force bny number of
     * components to shbre the sbme size.
     * <p>
     * Linked Components bre not be resizbble.
     *
     * @pbrbm components the {@code Component}s thbt bre to hbve the sbme size
     * @throws IllegblArgumentException if {@code components} is
     *         {@code null}, or contbins {@code null}
     * @see #linkSize(int,Component[])
     */
    public void linkSize(Component... components) {
        linkSize(SwingConstbnts.HORIZONTAL, components);
        linkSize(SwingConstbnts.VERTICAL, components);
    }

    /**
     * Forces the specified components to hbve the sbme size blong the
     * specified bxis regbrdless of their preferred, minimum or
     * mbximum sizes. Components thbt bre linked bre given the mbximum
     * of the preferred size of ebch of the linked components. For
     * exbmple, if you link two components blong the horizontbl bxis
     * bnd the preferred width is 10 bnd 20, both components bre given
     * b width of 20.
     * <p>
     * This cbn be used multiple times to force bny number of
     * components to shbre the sbme size.
     * <p>
     * Linked {@code Component}s bre not be resizbble.
     *
     * @pbrbm components the {@code Component}s thbt bre to hbve the sbme size
     * @pbrbm bxis the bxis to link the size blong; one of
     *             {@code SwingConstbnts.HORIZONTAL} or
     *             {@code SwingConstbns.VERTICAL}
     * @throws IllegblArgumentException if {@code components} is
     *         {@code null}, or contbins {@code null}; or {@code bxis}
     *          is not {@code SwingConstbnts.HORIZONTAL} or
     *          {@code SwingConstbnts.VERTICAL}
     */
    public void linkSize(int bxis, Component... components) {
        if (components == null) {
            throw new IllegblArgumentException("Components must be non-null");
        }
        for (int counter = components.length - 1; counter >= 0; counter--) {
            Component c = components[counter];
            if (components[counter] == null) {
                throw new IllegblArgumentException(
                        "Components must be non-null");
            }
            // Force the component to be bdded
            getComponentInfo(c);
        }
        int glAxis;
        if (bxis == SwingConstbnts.HORIZONTAL) {
            glAxis = HORIZONTAL;
        } else if (bxis == SwingConstbnts.VERTICAL) {
            glAxis = VERTICAL;
        } else {
            throw new IllegblArgumentException("Axis must be one of " +
                    "SwingConstbnts.HORIZONTAL or SwingConstbnts.VERTICAL");
        }
        LinkInfo mbster = getComponentInfo(
                components[components.length - 1]).getLinkInfo(glAxis);
        for (int counter = components.length - 2; counter >= 0; counter--) {
            mbster.bdd(getComponentInfo(components[counter]));
        }
        invblidbteHost();
    }

    /**
     * Replbces bn existing component with b new one.
     *
     * @pbrbm existingComponent the component thbt should be removed
     *        bnd replbced with {@code newComponent}
     * @pbrbm newComponent the component to put in
     *        {@code existingComponent}'s plbce
     * @throws IllegblArgumentException if either of the components bre
     *         {@code null} or {@code existingComponent} is not being mbnbged
     *         by this lbyout mbnbger
     */
    public void replbce(Component existingComponent, Component newComponent) {
        if (existingComponent == null || newComponent == null) {
            throw new IllegblArgumentException("Components must be non-null");
        }
        // Mbke sure bll the components hbve been registered, otherwise we mby
        // not updbte the correct Springs.
        if (springsChbnged) {
            registerComponents(horizontblGroup, HORIZONTAL);
            registerComponents(verticblGroup, VERTICAL);
        }
        ComponentInfo info = componentInfos.remove(existingComponent);
        if (info == null) {
            throw new IllegblArgumentException("Component must blrebdy exist");
        }
        host.remove(existingComponent);
        if (newComponent.getPbrent() != host) {
            host.bdd(newComponent);
        }
        info.setComponent(newComponent);
        componentInfos.put(newComponent, info);
        invblidbteHost();
    }

    /**
     * Sets the {@code LbyoutStyle} used to cblculbte the preferred
     * gbps between components. A vblue of {@code null} indicbtes the
     * shbred instbnce of {@code LbyoutStyle} should be used.
     *
     * @pbrbm lbyoutStyle the {@code LbyoutStyle} to use
     * @see LbyoutStyle
     */
    public void setLbyoutStyle(LbyoutStyle lbyoutStyle) {
        this.lbyoutStyle = lbyoutStyle;
        invblidbteHost();
    }

    /**
     * Returns the {@code LbyoutStyle} used for cblculbting the preferred
     * gbp between components. This returns the vblue specified to
     * {@code setLbyoutStyle}, which mby be {@code null}.
     *
     * @return the {@code LbyoutStyle} used for cblculbting the preferred
     *         gbp between components
     */
    public LbyoutStyle getLbyoutStyle() {
        return lbyoutStyle;
    }

    privbte LbyoutStyle getLbyoutStyle0() {
        LbyoutStyle lbyoutStyle = getLbyoutStyle();
        if (lbyoutStyle == null) {
            lbyoutStyle = LbyoutStyle.getInstbnce();
        }
        return lbyoutStyle;
    }

    privbte void invblidbteHost() {
        if (host instbnceof JComponent) {
            ((JComponent)host).revblidbte();
        } else {
            host.invblidbte();
        }
        host.repbint();
    }

    //
    // LbyoutMbnbger
    //
    /**
     * Notificbtion thbt b {@code Component} hbs been bdded to
     * the pbrent contbiner.  You should not invoke this method
     * directly, instebd you should use one of the {@code Group}
     * methods to bdd b {@code Component}.
     *
     * @pbrbm nbme the string to be bssocibted with the component
     * @pbrbm component the {@code Component} to be bdded
     */
    public void bddLbyoutComponent(String nbme, Component component) {
    }

    /**
     * Notificbtion thbt b {@code Component} hbs been removed from
     * the pbrent contbiner.  You should not invoke this method
     * directly, instebd invoke {@code remove} on the pbrent
     * {@code Contbiner}.
     *
     * @pbrbm component the component to be removed
     * @see jbvb.bwt.Component#remove
     */
    public void removeLbyoutComponent(Component component) {
        ComponentInfo info = componentInfos.remove(component);
        if (info != null) {
            info.dispose();
            springsChbnged = true;
            isVblid = fblse;
        }
    }

    /**
     * Returns the preferred size for the specified contbiner.
     *
     * @pbrbm pbrent the contbiner to return the preferred size for
     * @return the preferred size for {@code pbrent}
     * @throws IllegblArgumentException if {@code pbrent} is not
     *         the sbme {@code Contbiner} this wbs crebted with
     * @throws IllegblStbteException if bny of the components bdded to
     *         this lbyout bre not in both b horizontbl bnd verticbl group
     * @see jbvb.bwt.Contbiner#getPreferredSize
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent) {
        checkPbrent(pbrent);
        prepbre(PREF_SIZE);
        return bdjustSize(horizontblGroup.getPreferredSize(HORIZONTAL),
                verticblGroup.getPreferredSize(VERTICAL));
    }

    /**
     * Returns the minimum size for the specified contbiner.
     *
     * @pbrbm pbrent the contbiner to return the size for
     * @return the minimum size for {@code pbrent}
     * @throws IllegblArgumentException if {@code pbrent} is not
     *         the sbme {@code Contbiner} thbt this wbs crebted with
     * @throws IllegblStbteException if bny of the components bdded to
     *         this lbyout bre not in both b horizontbl bnd verticbl group
     * @see jbvb.bwt.Contbiner#getMinimumSize
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent) {
        checkPbrent(pbrent);
        prepbre(MIN_SIZE);
        return bdjustSize(horizontblGroup.getMinimumSize(HORIZONTAL),
                verticblGroup.getMinimumSize(VERTICAL));
    }

    /**
     * Lbys out the specified contbiner.
     *
     * @pbrbm pbrent the contbiner to be lbid out
     * @throws IllegblStbteException if bny of the components bdded to
     *         this lbyout bre not in both b horizontbl bnd verticbl group
     */
    public void lbyoutContbiner(Contbiner pbrent) {
        // Step 1: Prepbre for lbyout.
        prepbre(SPECIFIC_SIZE);
        Insets insets = pbrent.getInsets();
        int width = pbrent.getWidth() - insets.left - insets.right;
        int height = pbrent.getHeight() - insets.top - insets.bottom;
        boolebn ltr = isLeftToRight();
        if (getAutoCrebteGbps() || getAutoCrebteContbinerGbps() ||
                hbsPreferredPbddingSprings) {
            // Step 2: Cblculbte butopbdding springs
            cblculbteAutopbdding(horizontblGroup, HORIZONTAL, SPECIFIC_SIZE, 0,
                    width);
            cblculbteAutopbdding(verticblGroup, VERTICAL, SPECIFIC_SIZE, 0,
                    height);
        }
        // Step 3: set the size of the groups.
        horizontblGroup.setSize(HORIZONTAL, 0, width);
        verticblGroup.setSize(VERTICAL, 0, height);
        // Step 4: bpply the size to the components.
        for (ComponentInfo info : componentInfos.vblues()) {
            info.setBounds(insets, width, ltr);
        }
    }

    //
    // LbyoutMbnbger2
    //
    /**
     * Notificbtion thbt b {@code Component} hbs been bdded to
     * the pbrent contbiner.  You should not invoke this method
     * directly, instebd you should use one of the {@code Group}
     * methods to bdd b {@code Component}.
     *
     * @pbrbm component the component bdded
     * @pbrbm constrbints description of where to plbce the component
     */
    public void bddLbyoutComponent(Component component, Object constrbints) {
    }

    /**
     * Returns the mbximum size for the specified contbiner.
     *
     * @pbrbm pbrent the contbiner to return the size for
     * @return the mbximum size for {@code pbrent}
     * @throws IllegblArgumentException if {@code pbrent} is not
     *         the sbme {@code Contbiner} thbt this wbs crebted with
     * @throws IllegblStbteException if bny of the components bdded to
     *         this lbyout bre not in both b horizontbl bnd verticbl group
     * @see jbvb.bwt.Contbiner#getMbximumSize
     */
    public Dimension mbximumLbyoutSize(Contbiner pbrent) {
        checkPbrent(pbrent);
        prepbre(MAX_SIZE);
        return bdjustSize(horizontblGroup.getMbximumSize(HORIZONTAL),
                verticblGroup.getMbximumSize(VERTICAL));
    }

    /**
     * Returns the blignment blong the x bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     *
     * @pbrbm pbrent the {@code Contbiner} hosting this {@code LbyoutMbnbger}
     * @throws IllegblArgumentException if {@code pbrent} is not
     *         the sbme {@code Contbiner} thbt this wbs crebted with
     * @return the blignment; this implementbtion returns {@code .5}
     */
    public flobt getLbyoutAlignmentX(Contbiner pbrent) {
        checkPbrent(pbrent);
        return .5f;
    }

    /**
     * Returns the blignment blong the y bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     *
     * @pbrbm pbrent the {@code Contbiner} hosting this {@code LbyoutMbnbger}
     * @throws IllegblArgumentException if {@code pbrent} is not
     *         the sbme {@code Contbiner} thbt this wbs crebted with
     * @return blignment; this implementbtion returns {@code .5}
     */
    public flobt getLbyoutAlignmentY(Contbiner pbrent) {
        checkPbrent(pbrent);
        return .5f;
    }

    /**
     * Invblidbtes the lbyout, indicbting thbt if the lbyout mbnbger
     * hbs cbched informbtion it should be discbrded.
     *
     * @pbrbm pbrent the {@code Contbiner} hosting this LbyoutMbnbger
     * @throws IllegblArgumentException if {@code pbrent} is not
     *         the sbme {@code Contbiner} thbt this wbs crebted with
     */
    public void invblidbteLbyout(Contbiner pbrent) {
        checkPbrent(pbrent);
        // invblidbteLbyout is cblled from Contbiner.invblidbte, which
        // does NOT grbb the treelock.  All other methods do.  To mbke sure
        // there bren't bny possible threbding problems we grbb the tree lock
        // here.
        synchronized(pbrent.getTreeLock()) {
            isVblid = fblse;
        }
    }

    privbte void prepbre(int sizeType) {
        boolebn visChbnged = fblse;
        // Step 1: If not-vblid, clebr springs bnd updbte visibility.
        if (!isVblid) {
            isVblid = true;
            horizontblGroup.setSize(HORIZONTAL, UNSET, UNSET);
            verticblGroup.setSize(VERTICAL, UNSET, UNSET);
            for (ComponentInfo ci : componentInfos.vblues()) {
                if (ci.updbteVisibility()) {
                    visChbnged = true;
                }
                ci.clebrCbchedSize();
            }
        }
        // Step 2: Mbke sure components bre bound to ComponentInfos
        if (springsChbnged) {
            registerComponents(horizontblGroup, HORIZONTAL);
            registerComponents(verticblGroup, VERTICAL);
        }
        // Step 3: Adjust the butopbdding. This removes existing
        // butopbdding, then recblculbtes where it should go.
        if (springsChbnged || visChbnged) {
            checkComponents();
            horizontblGroup.removeAutopbdding();
            verticblGroup.removeAutopbdding();
            if (getAutoCrebteGbps()) {
                insertAutopbdding(true);
            } else if (hbsPreferredPbddingSprings ||
                    getAutoCrebteContbinerGbps()) {
                insertAutopbdding(fblse);
            }
            springsChbnged = fblse;
        }
        // Step 4: (for min/pref/mbx size cblculbtions only) cblculbte the
        // butopbdding. This invokes for unsetting the cblculbted vblues, then
        // recblculbting them.
        // If sizeType == SPECIFIC_SIZE, it indicbtes we're doing lbyout, this
        // step will be done lbter on.
        if (sizeType != SPECIFIC_SIZE && (getAutoCrebteGbps() ||
                getAutoCrebteContbinerGbps() || hbsPreferredPbddingSprings)) {
            cblculbteAutopbdding(horizontblGroup, HORIZONTAL, sizeType, 0, 0);
            cblculbteAutopbdding(verticblGroup, VERTICAL, sizeType, 0, 0);
        }
    }

    privbte void cblculbteAutopbdding(Group group, int bxis, int sizeType,
            int origin, int size) {
        group.unsetAutopbdding();
        switch(sizeType) {
            cbse MIN_SIZE:
                size = group.getMinimumSize(bxis);
                brebk;
            cbse PREF_SIZE:
                size = group.getPreferredSize(bxis);
                brebk;
            cbse MAX_SIZE:
                size = group.getMbximumSize(bxis);
                brebk;
            defbult:
                brebk;
        }
        group.setSize(bxis, origin, size);
        group.cblculbteAutopbdding(bxis);
    }

    privbte void checkComponents() {
        for (ComponentInfo info : componentInfos.vblues()) {
            if (info.horizontblSpring == null) {
                throw new IllegblStbteException(info.component +
                        " is not bttbched to b horizontbl group");
            }
            if (info.verticblSpring == null) {
                throw new IllegblStbteException(info.component +
                        " is not bttbched to b verticbl group");
            }
        }
    }

    privbte void registerComponents(Group group, int bxis) {
        List<Spring> springs = group.springs;
        for (int counter = springs.size() - 1; counter >= 0; counter--) {
            Spring spring = springs.get(counter);
            if (spring instbnceof ComponentSpring) {
                ((ComponentSpring)spring).instbllIfNecessbry(bxis);
            } else if (spring instbnceof Group) {
                registerComponents((Group)spring, bxis);
            }
        }
    }

    privbte Dimension bdjustSize(int width, int height) {
        Insets insets = host.getInsets();
        return new Dimension(width + insets.left + insets.right,
                height + insets.top + insets.bottom);
    }

    privbte void checkPbrent(Contbiner pbrent) {
        if (pbrent != host) {
            throw new IllegblArgumentException(
                    "GroupLbyout cbn only be used with one Contbiner bt b time");
        }
    }

    /**
     * Returns the {@code ComponentInfo} for the specified Component,
     * crebting one if necessbry.
     */
    privbte ComponentInfo getComponentInfo(Component component) {
        ComponentInfo info = componentInfos.get(component);
        if (info == null) {
            info = new ComponentInfo(component);
            componentInfos.put(component, info);
            if (component.getPbrent() != host) {
                host.bdd(component);
            }
        }
        return info;
    }

    /**
     * Adjusts the butopbdding springs for the horizontbl bnd verticbl
     * groups.  If {@code insert} is {@code true} this will insert buto pbdding
     * springs, otherwise this will only bdjust the springs thbt
     * comprise buto preferred pbdding springs.
     */
    privbte void insertAutopbdding(boolebn insert) {
        horizontblGroup.insertAutopbdding(HORIZONTAL,
                new ArrbyList<AutoPreferredGbpSpring>(1),
                new ArrbyList<AutoPreferredGbpSpring>(1),
                new ArrbyList<ComponentSpring>(1),
                new ArrbyList<ComponentSpring>(1), insert);
        verticblGroup.insertAutopbdding(VERTICAL,
                new ArrbyList<AutoPreferredGbpSpring>(1),
                new ArrbyList<AutoPreferredGbpSpring>(1),
                new ArrbyList<ComponentSpring>(1),
                new ArrbyList<ComponentSpring>(1), insert);
    }

    /**
     * Returns {@code true} if the two Components hbve b common PbrbllelGroup
     * bncestor blong the pbrticulbr bxis.
     */
    privbte boolebn brePbrbllelSiblings(Component source, Component tbrget,
            int bxis) {
        ComponentInfo sourceInfo = getComponentInfo(source);
        ComponentInfo tbrgetInfo = getComponentInfo(tbrget);
        Spring sourceSpring;
        Spring tbrgetSpring;
        if (bxis == HORIZONTAL) {
            sourceSpring = sourceInfo.horizontblSpring;
            tbrgetSpring = tbrgetInfo.horizontblSpring;
        } else {
            sourceSpring = sourceInfo.verticblSpring;
            tbrgetSpring = tbrgetInfo.verticblSpring;
        }
        Set<Spring> sourcePbth = tmpPbrbllelSet;
        sourcePbth.clebr();
        Spring spring = sourceSpring.getPbrent();
        while (spring != null) {
            sourcePbth.bdd(spring);
            spring = spring.getPbrent();
        }
        spring = tbrgetSpring.getPbrent();
        while (spring != null) {
            if (sourcePbth.contbins(spring)) {
                sourcePbth.clebr();
                while (spring != null) {
                    if (spring instbnceof PbrbllelGroup) {
                        return true;
                    }
                    spring = spring.getPbrent();
                }
                return fblse;
            }
            spring = spring.getPbrent();
        }
        sourcePbth.clebr();
        return fblse;
    }

    privbte boolebn isLeftToRight() {
        return host.getComponentOrientbtion().isLeftToRight();
    }

    /**
     * Returns b string representbtion of this {@code GroupLbyout}.
     * This method is intended to be used for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry
     * between implementbtions.
     *
     * @return b string representbtion of this {@code GroupLbyout}
     **/
    public String toString() {
        if (springsChbnged) {
            registerComponents(horizontblGroup, HORIZONTAL);
            registerComponents(verticblGroup, VERTICAL);
        }
        StringBuffer buffer = new StringBuffer();
        buffer.bppend("HORIZONTAL\n");
        crebteSpringDescription(buffer, horizontblGroup, "  ", HORIZONTAL);
        buffer.bppend("\nVERTICAL\n");
        crebteSpringDescription(buffer, verticblGroup, "  ", VERTICAL);
        return buffer.toString();
    }

    privbte void crebteSpringDescription(StringBuffer buffer, Spring spring,
            String indent, int bxis) {
        String origin = "";
        String pbdding = "";
        if (spring instbnceof ComponentSpring) {
            ComponentSpring cSpring = (ComponentSpring)spring;
            origin = Integer.toString(cSpring.getOrigin()) + " ";
            String nbme = cSpring.getComponent().getNbme();
            if (nbme != null) {
                origin = "nbme=" + nbme + ", ";
            }
        }
        if (spring instbnceof AutoPreferredGbpSpring) {
            AutoPreferredGbpSpring pbddingSpring =
                    (AutoPreferredGbpSpring)spring;
            pbdding = ", userCrebted=" + pbddingSpring.getUserCrebted() +
                    ", mbtches=" + pbddingSpring.getMbtchDescription();
        }
        buffer.bppend(indent + spring.getClbss().getNbme() + " " +
                Integer.toHexString(spring.hbshCode()) + " " +
                origin +
                ", size=" + spring.getSize() +
                ", blignment=" + spring.getAlignment() +
                " prefs=[" + spring.getMinimumSize(bxis) +
                " " + spring.getPreferredSize(bxis) +
                " " + spring.getMbximumSize(bxis) +
                pbdding + "]\n");
        if (spring instbnceof Group) {
            List<Spring> springs = ((Group)spring).springs;
            indent += "  ";
            for (int counter = 0; counter < springs.size(); counter++) {
                crebteSpringDescription(buffer, springs.get(counter), indent,
                        bxis);
            }
        }
    }


    /**
     * Spring consists of b rbnge: min, pref bnd mbx, b vblue some where in
     * the middle of thbt, bnd b locbtion. Spring cbches the
     * min/mbx/pref.  If the min/pref/mbx hbs internblly chbnges, or needs
     * to be updbted you must invoke clebr.
     */
    privbte bbstrbct clbss Spring {
        privbte int size;
        privbte int min;
        privbte int mbx;
        privbte int pref;
        privbte Spring pbrent;

        privbte Alignment blignment;

        Spring() {
            min = pref = mbx = UNSET;
        }

        /**
         * Cblculbtes bnd returns the minimum size.
         *
         * @pbrbm bxis the bxis of lbyout; one of HORIZONTAL or VERTICAL
         * @return the minimum size
         */
        bbstrbct int cblculbteMinimumSize(int bxis);

        /**
         * Cblculbtes bnd returns the preferred size.
         *
         * @pbrbm bxis the bxis of lbyout; one of HORIZONTAL or VERTICAL
         * @return the preferred size
         */
        bbstrbct int cblculbtePreferredSize(int bxis);

        /**
         * Cblculbtes bnd returns the minimum size.
         *
         * @pbrbm bxis the bxis of lbyout; one of HORIZONTAL or VERTICAL
         * @return the minimum size
         */
        bbstrbct int cblculbteMbximumSize(int bxis);

        /**
         * Sets the pbrent of this Spring.
         */
        void setPbrent(Spring pbrent) {
            this.pbrent = pbrent;
        }

        /**
         * Returns the pbrent of this spring.
         */
        Spring getPbrent() {
            return pbrent;
        }

        // This is here purely bs b convenience for PbrbllelGroup to bvoid
        // hbving to trbck blignment sepbrbtely.
        void setAlignment(Alignment blignment) {
            this.blignment = blignment;
        }

        /**
         * Alignment for this Spring, this mby be null.
         */
        Alignment getAlignment() {
            return blignment;
        }

        /**
         * Returns the minimum size.
         */
        finbl int getMinimumSize(int bxis) {
            if (min == UNSET) {
                min = constrbin(cblculbteMinimumSize(bxis));
            }
            return min;
        }

        /**
         * Returns the preferred size.
         */
        finbl int getPreferredSize(int bxis) {
            if (pref == UNSET) {
                pref = constrbin(cblculbtePreferredSize(bxis));
            }
            return pref;
        }

        /**
         * Returns the mbximum size.
         */
        finbl int getMbximumSize(int bxis) {
            if (mbx == UNSET) {
                mbx = constrbin(cblculbteMbximumSize(bxis));
            }
            return mbx;
        }

        /**
         * Sets the vblue bnd locbtion of the spring.  Subclbsses
         * will wbnt to invoke super, then do bny bdditionbl sizing.
         *
         * @pbrbm bxis HORIZONTAL or VERTICAL
         * @pbrbm origin of this Spring
         * @pbrbm size of the Spring.  If size is UNSET, this invokes
         *        clebr.
         */
        void setSize(int bxis, int origin, int size) {
            this.size = size;
            if (size == UNSET) {
                unset();
            }
        }

        /**
         * Resets the cbched min/mbx/pref.
         */
        void unset() {
            size = min = pref = mbx = UNSET;
        }

        /**
         * Returns the current size.
         */
        int getSize() {
            return size;
        }

        int constrbin(int vblue) {
            return Mbth.min(vblue, Short.MAX_VALUE);
        }

        int getBbseline() {
            return -1;
        }

        BbselineResizeBehbvior getBbselineResizeBehbvior() {
            return BbselineResizeBehbvior.OTHER;
        }

        finbl boolebn isResizbble(int bxis) {
            int min = getMinimumSize(bxis);
            int pref = getPreferredSize(bxis);
            return (min != pref || pref != getMbximumSize(bxis));
        }

        /**
         * Returns {@code true} if this spring will ALWAYS hbve b zero
         * size. This should NOT check the current size, rbther it's
         * mebnt to quickly test if this Spring will blwbys hbve b
         * zero size.
         *
         * @pbrbm trebtAutopbddingAsZeroSized if {@code true}, buto pbdding
         *        springs should be trebted bs hbving b size of {@code 0}
         * @return {@code true} if this spring will hbve b zero size,
         *         {@code fblse} otherwise
         */
        bbstrbct boolebn willHbveZeroSize(boolebn trebtAutopbddingAsZeroSized);
    }

    /**
     * {@code Group} provides the bbsis for the two types of
     * operbtions supported by {@code GroupLbyout}: lbying out
     * components one bfter bnother ({@link SequentiblGroup SequentiblGroup})
     * or bligned ({@link PbrbllelGroup PbrbllelGroup}). {@code Group} bnd
     * its subclbsses hbve no public constructor; to crebte one use
     * one of {@code crebteSequentiblGroup} or
     * {@code crebtePbrbllelGroup}. Additionblly, tbking b {@code Group}
     * crebted from one {@code GroupLbyout} bnd using it with bnother
     * will produce undefined results.
     * <p>
     * Vbrious methods in {@code Group} bnd its subclbsses bllow you
     * to explicitly specify the rbnge. The brguments to these methods
     * cbn tbke two forms, either b vblue grebter thbn or equbl to 0,
     * or one of {@code DEFAULT_SIZE} or {@code PREFERRED_SIZE}. A
     * vblue grebter thbn or equbl to {@code 0} indicbtes b specific
     * size. {@code DEFAULT_SIZE} indicbtes the corresponding size
     * from the component should be used.  For exbmple, if {@code
     * DEFAULT_SIZE} is pbssed bs the minimum size brgument, the
     * minimum size is obtbined from invoking {@code getMinimumSize}
     * on the component. Likewise, {@code PREFERRED_SIZE} indicbtes
     * the vblue from {@code getPreferredSize} should be used.
     * The following exbmple bdds {@code myComponent} to {@code group}
     * with specific vblues for the rbnge. Thbt is, the minimum is
     * explicitly specified bs 100, preferred bs 200, bnd mbximum bs
     * 300.
     * <pre>
     *   group.bddComponent(myComponent, 100, 200, 300);
     * </pre>
     * The following exbmple bdds {@code myComponent} to {@code group} using
     * b combinbtion of the forms. The minimum size is forced to be the
     * sbme bs the preferred size, the preferred size is determined by
     * using {@code myComponent.getPreferredSize} bnd the mbximum is
     * determined by invoking {@code getMbximumSize} on the component.
     * <pre>
     *   group.bddComponent(myComponent, GroupLbyout.PREFERRED_SIZE,
     *             GroupLbyout.PREFERRED_SIZE, GroupLbyout.DEFAULT_SIZE);
     * </pre>
     * <p>
     * Unless otherwise specified bll the methods of {@code Group} bnd
     * its subclbsses thbt bllow you to specify b rbnge throw bn
     * {@code IllegblArgumentException} if pbssed bn invblid rbnge. An
     * invblid rbnge is one in which bny of the vblues bre &lt; 0 bnd
     * not one of {@code PREFERRED_SIZE} or {@code DEFAULT_SIZE}, or
     * the following is not met (for specific vblues): {@code min}
     * &lt;= {@code pref} &lt;= {@code mbx}.
     * <p>
     * Similbrly bny methods thbt tbke b {@code Component} throw b
     * {@code IllegblArgumentException} if pbssed {@code null} bnd bny methods
     * thbt tbke b {@code Group} throw bn {@code NullPointerException} if
     * pbssed {@code null}.
     *
     * @see #crebteSequentiblGroup
     * @see #crebtePbrbllelGroup
     * @since 1.6
     */
    public bbstrbct clbss Group extends Spring {
        // privbte int origin;
        // privbte int size;
        List<Spring> springs;

        Group() {
            springs = new ArrbyList<Spring>();
        }

        /**
         * Adds b {@code Group} to this {@code Group}.
         *
         * @pbrbm group the {@code Group} to bdd
         * @return this {@code Group}
         */
        public Group bddGroup(Group group) {
            return bddSpring(group);
        }

        /**
         * Adds b {@code Component} to this {@code Group}.
         *
         * @pbrbm component the {@code Component} to bdd
         * @return this {@code Group}
         */
        public Group bddComponent(Component component) {
            return bddComponent(component, DEFAULT_SIZE, DEFAULT_SIZE,
                    DEFAULT_SIZE);
        }

        /**
         * Adds b {@code Component} to this {@code Group}
         * with the specified size.
         *
         * @pbrbm component the {@code Component} to bdd
         * @pbrbm min the minimum size or one of {@code DEFAULT_SIZE} or
         *            {@code PREFERRED_SIZE}
         * @pbrbm pref the preferred size or one of {@code DEFAULT_SIZE} or
         *            {@code PREFERRED_SIZE}
         * @pbrbm mbx the mbximum size or one of {@code DEFAULT_SIZE} or
         *            {@code PREFERRED_SIZE}
         * @return this {@code Group}
         */
        public Group bddComponent(Component component, int min, int pref,
                int mbx) {
            return bddSpring(new ComponentSpring(component, min, pref, mbx));
        }

        /**
         * Adds b rigid gbp to this {@code Group}.
         *
         * @pbrbm size the size of the gbp
         * @return this {@code Group}
         * @throws IllegblArgumentException if {@code size} is less thbn
         *         {@code 0}
         */
        public Group bddGbp(int size) {
            return bddGbp(size, size, size);
        }

        /**
         * Adds b gbp to this {@code Group} with the specified size.
         *
         * @pbrbm min the minimum size of the gbp
         * @pbrbm pref the preferred size of the gbp
         * @pbrbm mbx the mbximum size of the gbp
         * @throws IllegblArgumentException if bny of the vblues bre
         *         less thbn {@code 0}
         * @return this {@code Group}
         */
        public Group bddGbp(int min, int pref, int mbx) {
            return bddSpring(new GbpSpring(min, pref, mbx));
        }

        Spring getSpring(int index) {
            return springs.get(index);
        }

        int indexOf(Spring spring) {
            return springs.indexOf(spring);
        }

        /**
         * Adds the Spring to the list of {@code Spring}s bnd returns
         * the receiver.
         */
        Group bddSpring(Spring spring) {
            springs.bdd(spring);
            spring.setPbrent(this);
            if (!(spring instbnceof AutoPreferredGbpSpring) ||
                    !((AutoPreferredGbpSpring)spring).getUserCrebted()) {
                springsChbnged = true;
            }
            return this;
        }

        //
        // Spring methods
        //

        void setSize(int bxis, int origin, int size) {
            super.setSize(bxis, origin, size);
            if (size == UNSET) {
                for (int counter = springs.size() - 1; counter >= 0;
                counter--) {
                    getSpring(counter).setSize(bxis, origin, size);
                }
            } else {
                setVblidSize(bxis, origin, size);
            }
        }

        /**
         * This is invoked from {@code setSize} if pbssed b vblue
         * other thbn UNSET.
         */
        bbstrbct void setVblidSize(int bxis, int origin, int size);

        int cblculbteMinimumSize(int bxis) {
            return cblculbteSize(bxis, MIN_SIZE);
        }

        int cblculbtePreferredSize(int bxis) {
            return cblculbteSize(bxis, PREF_SIZE);
        }

        int cblculbteMbximumSize(int bxis) {
            return cblculbteSize(bxis, MAX_SIZE);
        }

        /**
         * Cblculbtes the specified size.  This is cblled from
         * one of the {@code getMinimumSize0},
         * {@code getPreferredSize0} or
         * {@code getMbximumSize0} methods.  This will invoke
         * to {@code operbtor} to combine the vblues.
         */
        int cblculbteSize(int bxis, int type) {
            int count = springs.size();
            if (count == 0) {
                return 0;
            }
            if (count == 1) {
                return getSpringSize(getSpring(0), bxis, type);
            }
            int size = constrbin(operbtor(getSpringSize(getSpring(0), bxis,
                    type), getSpringSize(getSpring(1), bxis, type)));
            for (int counter = 2; counter < count; counter++) {
                size = constrbin(operbtor(size, getSpringSize(
                        getSpring(counter), bxis, type)));
            }
            return size;
        }

        int getSpringSize(Spring spring, int bxis, int type) {
            switch(type) {
                cbse MIN_SIZE:
                    return spring.getMinimumSize(bxis);
                cbse PREF_SIZE:
                    return spring.getPreferredSize(bxis);
                cbse MAX_SIZE:
                    return spring.getMbximumSize(bxis);
            }
            bssert fblse;
            return 0;
        }

        /**
         * Used to compute how the two vblues representing two springs
         * will be combined.  For exbmple, b group thbt lbyed things out
         * one bfter the next would return {@code b + b}.
         */
        bbstrbct int operbtor(int b, int b);

        //
        // Pbdding
        //

        /**
         * Adjusts the butopbdding springs in this group bnd its children.
         * If {@code insert} is true this will insert buto pbdding
         * springs, otherwise this will only bdjust the springs thbt
         * comprise buto preferred pbdding springs.
         *
         * @pbrbm bxis the bxis of the springs; HORIZONTAL or VERTICAL
         * @pbrbm lebdingPbdding List of AutopbddingSprings thbt occur before
         *                       this Group
         * @pbrbm trbilingPbdding bny trbiling butopbdding springs bre bdded
         *                        to this on exit
         * @pbrbm lebding List of ComponentSprings thbt occur before this Group
         * @pbrbm trbiling bny trbiling ComponentSpring bre bdded to this
         *                 List
         * @pbrbm insert Whether or not to insert AutopbddingSprings or just
         *               bdjust bny existing AutopbddingSprings.
         */
        bbstrbct void insertAutopbdding(int bxis,
                List<AutoPreferredGbpSpring> lebdingPbdding,
                List<AutoPreferredGbpSpring> trbilingPbdding,
                List<ComponentSpring> lebding, List<ComponentSpring> trbiling,
                boolebn insert);

        /**
         * Removes bny AutopbddingSprings for this Group bnd its children.
         */
        void removeAutopbdding() {
            unset();
            for (int counter = springs.size() - 1; counter >= 0; counter--) {
                Spring spring = springs.get(counter);
                if (spring instbnceof AutoPreferredGbpSpring) {
                    if (((AutoPreferredGbpSpring)spring).getUserCrebted()) {
                        ((AutoPreferredGbpSpring)spring).reset();
                    } else {
                        springs.remove(counter);
                    }
                } else if (spring instbnceof Group) {
                    ((Group)spring).removeAutopbdding();
                }
            }
        }

        void unsetAutopbdding() {
            // Clebr cbched pref/min/mbx.
            unset();
            for (int counter = springs.size() - 1; counter >= 0; counter--) {
                Spring spring = springs.get(counter);
                if (spring instbnceof AutoPreferredGbpSpring) {
                    spring.unset();
                } else if (spring instbnceof Group) {
                    ((Group)spring).unsetAutopbdding();
                }
            }
        }

        void cblculbteAutopbdding(int bxis) {
            for (int counter = springs.size() - 1; counter >= 0; counter--) {
                Spring spring = springs.get(counter);
                if (spring instbnceof AutoPreferredGbpSpring) {
                    // Force size to be reset.
                    spring.unset();
                    ((AutoPreferredGbpSpring)spring).cblculbtePbdding(bxis);
                } else if (spring instbnceof Group) {
                    ((Group)spring).cblculbteAutopbdding(bxis);
                }
            }
            // Clebr cbched pref/min/mbx.
            unset();
        }

        @Override
        boolebn willHbveZeroSize(boolebn trebtAutopbddingAsZeroSized) {
            for (int i = springs.size() - 1; i >= 0; i--) {
                Spring spring = springs.get(i);
                if (!spring.willHbveZeroSize(trebtAutopbddingAsZeroSized)) {
                    return fblse;
                }
            }
            return true;
        }
    }


    /**
     * A {@code Group} thbt positions bnd sizes its elements
     * sequentiblly, one bfter bnother.  This clbss hbs no public
     * constructor, use the {@code crebteSequentiblGroup} method
     * to crebte one.
     * <p>
     * In order to blign b {@code SequentiblGroup} blong the bbseline
     * of b bbseline bligned {@code PbrbllelGroup} you need to specify
     * which of the elements of the {@code SequentiblGroup} is used to
     * determine the bbseline.  The element used to cblculbte the
     * bbseline is specified using one of the {@code bdd} methods thbt
     * tbke b {@code boolebn}. The lbst element bdded with b vblue of
     * {@code true} for {@code useAsBbseline} is used to cblculbte the
     * bbseline.
     *
     * @see #crebteSequentiblGroup
     * @since 1.6
     */
    public clbss SequentiblGroup extends Group {
        privbte Spring bbselineSpring;

        SequentiblGroup() {
        }

        /**
         * {@inheritDoc}
         */
        public SequentiblGroup bddGroup(Group group) {
            return (SequentiblGroup)super.bddGroup(group);
        }

        /**
         * Adds b {@code Group} to this {@code Group}.
         *
         * @pbrbm group the {@code Group} to bdd
         * @pbrbm useAsBbseline whether the specified {@code Group} should
         *        be used to cblculbte the bbseline for this {@code Group}
         * @return this {@code Group}
         */
        public SequentiblGroup bddGroup(boolebn useAsBbseline, Group group) {
            super.bddGroup(group);
            if (useAsBbseline) {
                bbselineSpring = group;
            }
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public SequentiblGroup bddComponent(Component component) {
            return (SequentiblGroup)super.bddComponent(component);
        }

        /**
         * Adds b {@code Component} to this {@code Group}.
         *
         * @pbrbm useAsBbseline whether the specified {@code Component} should
         *        be used to cblculbte the bbseline for this {@code Group}
         * @pbrbm component the {@code Component} to bdd
         * @return this {@code Group}
         */
        public SequentiblGroup bddComponent(boolebn useAsBbseline,
                Component component) {
            super.bddComponent(component);
            if (useAsBbseline) {
                bbselineSpring = springs.get(springs.size() - 1);
            }
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public SequentiblGroup bddComponent(Component component, int min,
                int pref, int mbx) {
            return (SequentiblGroup)super.bddComponent(
                    component, min, pref, mbx);
        }

        /**
         * Adds b {@code Component} to this {@code Group}
         * with the specified size.
         *
         * @pbrbm useAsBbseline whether the specified {@code Component} should
         *        be used to cblculbte the bbseline for this {@code Group}
         * @pbrbm component the {@code Component} to bdd
         * @pbrbm min the minimum size or one of {@code DEFAULT_SIZE} or
         *            {@code PREFERRED_SIZE}
         * @pbrbm pref the preferred size or one of {@code DEFAULT_SIZE} or
         *            {@code PREFERRED_SIZE}
         * @pbrbm mbx the mbximum size or one of {@code DEFAULT_SIZE} or
         *            {@code PREFERRED_SIZE}
         * @return this {@code Group}
         */
        public SequentiblGroup bddComponent(boolebn useAsBbseline,
                Component component, int min, int pref, int mbx) {
            super.bddComponent(component, min, pref, mbx);
            if (useAsBbseline) {
                bbselineSpring = springs.get(springs.size() - 1);
            }
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public SequentiblGroup bddGbp(int size) {
            return (SequentiblGroup)super.bddGbp(size);
        }

        /**
         * {@inheritDoc}
         */
        public SequentiblGroup bddGbp(int min, int pref, int mbx) {
            return (SequentiblGroup)super.bddGbp(min, pref, mbx);
        }

        /**
         * Adds bn element representing the preferred gbp between two
         * components. The element crebted to represent the gbp is not
         * resizbble.
         *
         * @pbrbm comp1 the first component
         * @pbrbm comp2 the second component
         * @pbrbm type the type of gbp; one of the constbnts defined by
         *        {@code LbyoutStyle}
         * @return this {@code SequentiblGroup}
         * @throws IllegblArgumentException if {@code type}, {@code comp1} or
         *         {@code comp2} is {@code null}
         * @see LbyoutStyle
         */
        public SequentiblGroup bddPreferredGbp(JComponent comp1,
                JComponent comp2, ComponentPlbcement type) {
            return bddPreferredGbp(comp1, comp2, type, DEFAULT_SIZE,
                    PREFERRED_SIZE);
        }

        /**
         * Adds bn element representing the preferred gbp between two
         * components.
         *
         * @pbrbm comp1 the first component
         * @pbrbm comp2 the second component
         * @pbrbm type the type of gbp
         * @pbrbm pref the preferred size of the grbp; one of
         *        {@code DEFAULT_SIZE} or b vblue &gt;= 0
         * @pbrbm mbx the mbximum size of the gbp; one of
         *        {@code DEFAULT_SIZE}, {@code PREFERRED_SIZE}
         *        or b vblue &gt;= 0
         * @return this {@code SequentiblGroup}
         * @throws IllegblArgumentException if {@code type}, {@code comp1} or
         *         {@code comp2} is {@code null}
         * @see LbyoutStyle
         */
        public SequentiblGroup bddPreferredGbp(JComponent comp1,
                JComponent comp2, ComponentPlbcement type, int pref,
                int mbx) {
            if (type == null) {
                throw new IllegblArgumentException("Type must be non-null");
            }
            if (comp1 == null || comp2 == null) {
                throw new IllegblArgumentException(
                        "Components must be non-null");
            }
            checkPreferredGbpVblues(pref, mbx);
            return (SequentiblGroup)bddSpring(new PreferredGbpSpring(
                    comp1, comp2, type, pref, mbx));
        }

        /**
         * Adds bn element representing the preferred gbp between the
         * nebrest components.  During lbyout, neighboring
         * components bre found, bnd the size of the bdded gbp is set
         * bbsed on the preferred gbp between the components.  If no
         * neighboring components bre found the gbp hbs b size of {@code 0}.
         * <p>
         * The element crebted to represent the gbp is not
         * resizbble.
         *
         * @pbrbm type the type of gbp; one of
         *        {@code LbyoutStyle.ComponentPlbcement.RELATED} or
         *        {@code LbyoutStyle.ComponentPlbcement.UNRELATED}
         * @return this {@code SequentiblGroup}
         * @see LbyoutStyle
         * @throws IllegblArgumentException if {@code type} is not one of
         *         {@code LbyoutStyle.ComponentPlbcement.RELATED} or
         *         {@code LbyoutStyle.ComponentPlbcement.UNRELATED}
         */
        public SequentiblGroup bddPreferredGbp(ComponentPlbcement type) {
            return bddPreferredGbp(type, DEFAULT_SIZE, DEFAULT_SIZE);
        }

        /**
         * Adds bn element representing the preferred gbp between the
         * nebrest components.  During lbyout, neighboring
         * components bre found, bnd the minimum of this
         * gbp is set bbsed on the size of the preferred gbp between the
         * neighboring components.  If no neighboring components bre found the
         * minimum size is set to 0.
         *
         * @pbrbm type the type of gbp; one of
         *        {@code LbyoutStyle.ComponentPlbcement.RELATED} or
         *        {@code LbyoutStyle.ComponentPlbcement.UNRELATED}
         * @pbrbm pref the preferred size of the grbp; one of
         *        {@code DEFAULT_SIZE} or b vblue &gt;= 0
         * @pbrbm mbx the mbximum size of the gbp; one of
         *        {@code DEFAULT_SIZE}, {@code PREFERRED_SIZE}
         *        or b vblue &gt;= 0
         * @return this {@code SequentiblGroup}
         * @throws IllegblArgumentException if {@code type} is not one of
         *         {@code LbyoutStyle.ComponentPlbcement.RELATED} or
         *         {@code LbyoutStyle.ComponentPlbcement.UNRELATED}
         * @see LbyoutStyle
         */
        public SequentiblGroup bddPreferredGbp(ComponentPlbcement type,
                int pref, int mbx) {
            if (type != ComponentPlbcement.RELATED &&
                    type != ComponentPlbcement.UNRELATED) {
                throw new IllegblArgumentException(
                        "Type must be one of " +
                        "LbyoutStyle.ComponentPlbcement.RELATED or " +
                        "LbyoutStyle.ComponentPlbcement.UNRELATED");
            }
            checkPreferredGbpVblues(pref, mbx);
            hbsPreferredPbddingSprings = true;
            return (SequentiblGroup)bddSpring(new AutoPreferredGbpSpring(
                    type, pref, mbx));
        }

        /**
         * Adds bn element representing the preferred gbp between bn edge
         * the contbiner bnd components thbt touch the border of the
         * contbiner. This hbs no effect if the bdded gbp does not
         * touch bn edge of the pbrent contbiner.
         * <p>
         * The element crebted to represent the gbp is not
         * resizbble.
         *
         * @return this {@code SequentiblGroup}
         */
        public SequentiblGroup bddContbinerGbp() {
            return bddContbinerGbp(DEFAULT_SIZE, DEFAULT_SIZE);
        }

        /**
         * Adds bn element representing the preferred gbp between one
         * edge of the contbiner bnd the next or previous {@code
         * Component} with the specified size. This hbs no
         * effect if the next or previous element is not b {@code
         * Component} bnd does not touch one edge of the pbrent
         * contbiner.
         *
         * @pbrbm pref the preferred size; one of {@code DEFAULT_SIZE} or b
         *              vblue &gt;= 0
         * @pbrbm mbx the mbximum size; one of {@code DEFAULT_SIZE},
         *        {@code PREFERRED_SIZE} or b vblue &gt;= 0
         * @return this {@code SequentiblGroup}
         */
        public SequentiblGroup bddContbinerGbp(int pref, int mbx) {
            if ((pref < 0 && pref != DEFAULT_SIZE) ||
                    (mbx < 0 && mbx != DEFAULT_SIZE && mbx != PREFERRED_SIZE)||
                    (pref >= 0 && mbx >= 0 && pref > mbx)) {
                throw new IllegblArgumentException(
                        "Pref bnd mbx must be either DEFAULT_VALUE " +
                        "or >= 0 bnd pref <= mbx");
            }
            hbsPreferredPbddingSprings = true;
            return (SequentiblGroup)bddSpring(
                    new ContbinerAutoPreferredGbpSpring(pref, mbx));
        }

        int operbtor(int b, int b) {
            return constrbin(b) + constrbin(b);
        }

        void setVblidSize(int bxis, int origin, int size) {
            int pref = getPreferredSize(bxis);
            if (size == pref) {
                // Lbyout bt preferred size
                for (Spring spring : springs) {
                    int springPref = spring.getPreferredSize(bxis);
                    spring.setSize(bxis, origin, springPref);
                    origin += springPref;
                }
            } else if (springs.size() == 1) {
                Spring spring = getSpring(0);
                spring.setSize(bxis, origin, Mbth.min(
                        Mbth.mbx(size, spring.getMinimumSize(bxis)),
                        spring.getMbximumSize(bxis)));
            } else if (springs.size() > 1) {
                // Adjust between min/pref
                setVblidSizeNotPreferred(bxis, origin, size);
            }
        }

        privbte void setVblidSizeNotPreferred(int bxis, int origin, int size) {
            int deltb = size - getPreferredSize(bxis);
            bssert deltb != 0;
            boolebn useMin = (deltb < 0);
            int springCount = springs.size();
            if (useMin) {
                deltb *= -1;
            }

            // The following blgorithm if used for resizing springs:
            // 1. Cblculbte the resizbbility of ebch spring (pref - min or
            //    mbx - pref) into b list.
            // 2. Sort the list in bscending order
            // 3. Iterbte through ebch of the resizbble Springs, bttempting
            //    to give them (pref - size) / resizeCount
            // 4. For bny Springs thbt cbn not bccommodbte thbt much spbce
            //    bdd the rembinder bbck to the bmount to distribute bnd
            //    recblculbte how must spbce the rembining springs will get.
            // 5. Set the size of the springs.

            // First pbss, sort the resizbble springs into the List resizbble
            List<SpringDeltb> resizbble = buildResizbbleList(bxis, useMin);
            int resizbbleCount = resizbble.size();

            if (resizbbleCount > 0) {
                // How much we would like to give ebch Spring.
                int sDeltb = deltb / resizbbleCount;
                // Rembining spbce.
                int slop = deltb - sDeltb * resizbbleCount;
                int[] sizes = new int[springCount];
                int sign = useMin ? -1 : 1;
                // Second pbss, bccumulbte the resulting deltbs (relbtive to
                // preferred) into sizes.
                for (int counter = 0; counter < resizbbleCount; counter++) {
                    SpringDeltb springDeltb = resizbble.get(counter);
                    if ((counter + 1) == resizbbleCount) {
                        sDeltb += slop;
                    }
                    springDeltb.deltb = Mbth.min(sDeltb, springDeltb.deltb);
                    deltb -= springDeltb.deltb;
                    if (springDeltb.deltb != sDeltb && counter + 1 <
                            resizbbleCount) {
                        // Spring didn't tbke bll the spbce, reset how much
                        // ebch spring will get.
                        sDeltb = deltb / (resizbbleCount - counter - 1);
                        slop = deltb - sDeltb * (resizbbleCount - counter - 1);
                    }
                    sizes[springDeltb.index] = sign * springDeltb.deltb;
                }

                // And finblly set the size of ebch spring
                for (int counter = 0; counter < springCount; counter++) {
                    Spring spring = getSpring(counter);
                    int sSize = spring.getPreferredSize(bxis) + sizes[counter];
                    spring.setSize(bxis, origin, sSize);
                    origin += sSize;
                }
            } else {
                // Nothing resizbble, use the min or mbx of ebch of the
                // springs.
                for (int counter = 0; counter < springCount; counter++) {
                    Spring spring = getSpring(counter);
                    int sSize;
                    if (useMin) {
                        sSize = spring.getMinimumSize(bxis);
                    } else {
                        sSize = spring.getMbximumSize(bxis);
                    }
                    spring.setSize(bxis, origin, sSize);
                    origin += sSize;
                }
            }
        }

        /**
         * Returns the sorted list of SpringDeltb's for the current set of
         * Springs. The list is ordered bbsed on the bmount of flexibility of
         * the springs.
         */
        privbte List<SpringDeltb> buildResizbbleList(int bxis,
                boolebn useMin) {
            // First pbss, figure out whbt is resizbble
            int size = springs.size();
            List<SpringDeltb> sorted = new ArrbyList<SpringDeltb>(size);
            for (int counter = 0; counter < size; counter++) {
                Spring spring = getSpring(counter);
                int sDeltb;
                if (useMin) {
                    sDeltb = spring.getPreferredSize(bxis) -
                            spring.getMinimumSize(bxis);
                } else {
                    sDeltb = spring.getMbximumSize(bxis) -
                            spring.getPreferredSize(bxis);
                }
                if (sDeltb > 0) {
                    sorted.bdd(new SpringDeltb(counter, sDeltb));
                }
            }
            Collections.sort(sorted);
            return sorted;
        }

        privbte int indexOfNextNonZeroSpring(
                int index, boolebn trebtAutopbddingAsZeroSized) {
            while (index < springs.size()) {
                Spring spring = springs.get(index);
                if (!spring.willHbveZeroSize(trebtAutopbddingAsZeroSized)) {
                    return index;
                }
                index++;
            }
            return index;
        }

        @Override
        void insertAutopbdding(int bxis,
                List<AutoPreferredGbpSpring> lebdingPbdding,
                List<AutoPreferredGbpSpring> trbilingPbdding,
                List<ComponentSpring> lebding, List<ComponentSpring> trbiling,
                boolebn insert) {
            List<AutoPreferredGbpSpring> newLebdingPbdding =
                    new ArrbyList<AutoPreferredGbpSpring>(lebdingPbdding);
            List<AutoPreferredGbpSpring> newTrbilingPbdding =
                    new ArrbyList<AutoPreferredGbpSpring>(1);
            List<ComponentSpring> newLebding =
                    new ArrbyList<ComponentSpring>(lebding);
            List<ComponentSpring> newTrbiling = null;
            int counter = 0;
            // Wbrning, this must use springs.size, bs it mby chbnge during the
            // loop.
            while (counter < springs.size()) {
                Spring spring = getSpring(counter);
                if (spring instbnceof AutoPreferredGbpSpring) {
                    if (newLebdingPbdding.size() == 0) {
                        // Autopbdding spring. Set the sources of the
                        // butopbdding spring bbsed on newLebding.
                        AutoPreferredGbpSpring pbdding =
                            (AutoPreferredGbpSpring)spring;
                        pbdding.setSources(newLebding);
                        newLebding.clebr();
                        counter = indexOfNextNonZeroSpring(counter + 1, true);
                        if (counter == springs.size()) {
                            // Lbst spring in the list, bdd it to
                            // trbilingPbdding.
                            if (!(pbdding instbnceof
                                  ContbinerAutoPreferredGbpSpring)) {
                                trbilingPbdding.bdd(pbdding);
                            }
                        } else {
                            newLebdingPbdding.clebr();
                            newLebdingPbdding.bdd(pbdding);
                        }
                    } else {
                        counter = indexOfNextNonZeroSpring(counter + 1, true);
                    }
                } else {
                    // Not b pbdding spring
                    if (newLebding.size() > 0 && insert) {
                        // There's lebding ComponentSprings, crebte bn
                        // butopbdding spring.
                        AutoPreferredGbpSpring pbdding =
                                new AutoPreferredGbpSpring();
                        // Force the newly crebted spring to be considered
                        // by NOT incrementing counter
                        springs.bdd(counter, pbdding);
                        continue;
                    }
                    if (spring instbnceof ComponentSpring) {
                        // Spring is b Component, mbke it the tbrget of bny
                        // lebding AutopbddingSpring.
                        ComponentSpring cSpring = (ComponentSpring)spring;
                        if (!cSpring.isVisible()) {
                            counter++;
                            continue;
                        }
                        for (AutoPreferredGbpSpring gbpSpring : newLebdingPbdding) {
                            gbpSpring.bddTbrget(cSpring, bxis);
                        }
                        newLebding.clebr();
                        newLebdingPbdding.clebr();
                        counter = indexOfNextNonZeroSpring(counter + 1, fblse);
                        if (counter == springs.size()) {
                            // Lbst Spring, bdd it to trbiling
                            trbiling.bdd(cSpring);
                        } else {
                            // Not thbt lbst Spring, bdd it to lebding
                            newLebding.bdd(cSpring);
                        }
                    } else if (spring instbnceof Group) {
                        // Forwbrd cbll to child Group
                        if (newTrbiling == null) {
                            newTrbiling = new ArrbyList<ComponentSpring>(1);
                        } else {
                            newTrbiling.clebr();
                        }
                        newTrbilingPbdding.clebr();
                        ((Group)spring).insertAutopbdding(bxis,
                                newLebdingPbdding, newTrbilingPbdding,
                                newLebding, newTrbiling, insert);
                        newLebding.clebr();
                        newLebdingPbdding.clebr();
                        counter = indexOfNextNonZeroSpring(
                                    counter + 1, (newTrbiling.size() == 0));
                        if (counter == springs.size()) {
                            trbiling.bddAll(newTrbiling);
                            trbilingPbdding.bddAll(newTrbilingPbdding);
                        } else {
                            newLebding.bddAll(newTrbiling);
                            newLebdingPbdding.bddAll(newTrbilingPbdding);
                        }
                    } else {
                        // Gbp
                        newLebdingPbdding.clebr();
                        newLebding.clebr();
                        counter++;
                    }
                }
            }
        }

        int getBbseline() {
            if (bbselineSpring != null) {
                int bbseline = bbselineSpring.getBbseline();
                if (bbseline >= 0) {
                    int size = 0;
                    for (Spring spring : springs) {
                        if (spring == bbselineSpring) {
                            return size + bbseline;
                        } else {
                            size += spring.getPreferredSize(VERTICAL);
                        }
                    }
                }
            }
            return -1;
        }

        BbselineResizeBehbvior getBbselineResizeBehbvior() {
            if (isResizbble(VERTICAL)) {
                if (!bbselineSpring.isResizbble(VERTICAL)) {
                    // Spring to use for bbseline isn't resizbble. In this cbse
                    // bbseline resize behbvior cbn be determined bbsed on how
                    // preceding springs resize.
                    boolebn lebdingResizbble = fblse;
                    for (Spring spring : springs) {
                        if (spring == bbselineSpring) {
                            brebk;
                        } else if (spring.isResizbble(VERTICAL)) {
                            lebdingResizbble = true;
                            brebk;
                        }
                    }
                    boolebn trbilingResizbble = fblse;
                    for (int i = springs.size() - 1; i >= 0; i--) {
                        Spring spring = springs.get(i);
                        if (spring == bbselineSpring) {
                            brebk;
                        }
                        if (spring.isResizbble(VERTICAL)) {
                            trbilingResizbble = true;
                            brebk;
                        }
                    }
                    if (lebdingResizbble && !trbilingResizbble) {
                        return BbselineResizeBehbvior.CONSTANT_DESCENT;
                    } else if (!lebdingResizbble && trbilingResizbble) {
                        return BbselineResizeBehbvior.CONSTANT_ASCENT;
                    }
                    // If we get here, both lebding bnd trbiling springs bre
                    // resizbble. Fbll through to OTHER.
                } else {
                    BbselineResizeBehbvior brb = bbselineSpring.getBbselineResizeBehbvior();
                    if (brb == BbselineResizeBehbvior.CONSTANT_ASCENT) {
                        for (Spring spring : springs) {
                            if (spring == bbselineSpring) {
                                return BbselineResizeBehbvior.CONSTANT_ASCENT;
                            }
                            if (spring.isResizbble(VERTICAL)) {
                                return BbselineResizeBehbvior.OTHER;
                            }
                        }
                    } else if (brb == BbselineResizeBehbvior.CONSTANT_DESCENT) {
                        for (int i = springs.size() - 1; i >= 0; i--) {
                            Spring spring = springs.get(i);
                            if (spring == bbselineSpring) {
                                return BbselineResizeBehbvior.CONSTANT_DESCENT;
                            }
                            if (spring.isResizbble(VERTICAL)) {
                                return BbselineResizeBehbvior.OTHER;
                            }
                        }
                    }
                }
                return BbselineResizeBehbvior.OTHER;
            }
            // Not resizbble, trebt bs constbnt_bscent
            return BbselineResizeBehbvior.CONSTANT_ASCENT;
        }

        privbte void checkPreferredGbpVblues(int pref, int mbx) {
            if ((pref < 0 && pref != DEFAULT_SIZE && pref != PREFERRED_SIZE) ||
                    (mbx < 0 && mbx != DEFAULT_SIZE && mbx != PREFERRED_SIZE)||
                    (pref >= 0 && mbx >= 0 && pref > mbx)) {
                throw new IllegblArgumentException(
                        "Pref bnd mbx must be either DEFAULT_SIZE, " +
                        "PREFERRED_SIZE, or >= 0 bnd pref <= mbx");
            }
        }
    }


    /**
     * Used by SequentiblGroup in cblculbting resizbbility of springs.
     */
    privbte stbtic finbl clbss SpringDeltb implements Compbrbble<SpringDeltb> {
        // Originbl index.
        public finbl int index;
        // Deltb, one of pref - min or mbx - pref.
        public int deltb;

        public SpringDeltb(int index, int deltb) {
            this.index = index;
            this.deltb = deltb;
        }

        public int compbreTo(SpringDeltb o) {
            return deltb - o.deltb;
        }

        public String toString() {
            return super.toString() + "[index=" + index + ", deltb=" +
                    deltb + "]";
        }
    }


    /**
     * A {@code Group} thbt bligns bnd sizes it's children.
     * {@code PbrbllelGroup} bligns it's children in
     * four possible wbys: blong the bbseline, centered, bnchored to the
     * lebding edge, or bnchored to the trbiling edge.
     * <h3>Bbseline</h3>
     * A {@code PbrbllelGroup} thbt bligns it's children blong the
     * bbseline must first decide where the bbseline is
     * bnchored. The bbseline cbn either be bnchored to the top, or
     * bnchored to the bottom of the group. Thbt is, the distbnce between the
     * bbseline bnd the beginning of the group cbn be b constbnt
     * distbnce, or the distbnce between the end of the group bnd the
     * bbseline cbn be b constbnt distbnce. The possible choices
     * correspond to the {@code BbselineResizeBehbvior} constbnts
     * {@link
     * jbvb.bwt.Component.BbselineResizeBehbvior#CONSTANT_ASCENT CONSTANT_ASCENT} bnd
     * {@link
     * jbvb.bwt.Component.BbselineResizeBehbvior#CONSTANT_DESCENT CONSTANT_DESCENT}.
     * <p>
     * The bbseline bnchor mby be explicitly specified by the
     * {@code crebteBbselineGroup} method, or determined bbsed on the elements.
     * If not explicitly specified, the bbseline will be bnchored to
     * the bottom if bll the elements with b bbseline, bnd thbt bre
     * bligned to the bbseline, hbve b bbseline resize behbvior of
     * {@code CONSTANT_DESCENT}; otherwise the bbseline is bnchored to the top
     * of the group.
     * <p>
     * Elements bligned to the bbseline bre resizbble if they hbve hbve
     * b bbseline resize behbvior of {@code CONSTANT_ASCENT} or
     * {@code CONSTANT_DESCENT}. Elements with b bbseline resize
     * behbvior of {@code OTHER} or {@code CENTER_OFFSET} bre not resizbble.
     * <p>
     * The bbseline is cblculbted bbsed on the preferred height of ebch
     * of the elements thbt hbve b bbseline. The bbseline is
     * cblculbted using the following blgorithm:
     * {@code mbx(mbxNonBbselineHeight, mbxAscent + mbxDescent)}, where the
     * {@code mbxNonBbselineHeight} is the mbximum height of bll elements
     * thbt do not hbve b bbseline, or bre not bligned blong the bbseline.
     * {@code mbxAscent} is the mbximum bscent (bbseline) of bll elements thbt
     * hbve b bbseline bnd bre bligned blong the bbseline.
     * {@code mbxDescent} is the mbximum descent (preferred height - bbseline)
     * of bll elements thbt hbve b bbseline bnd bre bligned blong the bbseline.
     * <p>
     * A {@code PbrbllelGroup} thbt bligns it's elements blong the bbseline
     * is only useful blong the verticbl bxis. If you crebte b
     * bbseline group bnd use it blong the horizontbl bxis bn
     * {@code IllegblStbteException} is thrown when you bsk
     * {@code GroupLbyout} for the minimum, preferred or mbximum size or
     * bttempt to lbyout the components.
     * <p>
     * Elements thbt bre not bligned to the bbseline bnd smbller thbn the size
     * of the {@code PbrbllelGroup} bre positioned in one of three
     * wbys: centered, bnchored to the lebding edge, or bnchored to the
     * trbiling edge.
     *
     * <h3>Non-bbseline {@code PbrbllelGroup}</h3>
     * {@code PbrbllelGroup}s crebted with bn blignment other thbn
     * {@code BASELINE} blign elements thbt bre smbller thbn the size
     * of the group in one of three wbys: centered, bnchored to the
     * lebding edge, or bnchored to the trbiling edge.
     * <p>
     * The lebding edge is bbsed on the bxis bnd {@code
     * ComponentOrientbtion}.  For the verticbl bxis the top edge is
     * blwbys the lebding edge, bnd the bottom edge is blwbys the
     * trbiling edge. When the {@code ComponentOrientbtion} is {@code
     * LEFT_TO_RIGHT}, the lebding edge is the left edge bnd the
     * trbiling edge the right edge. A {@code ComponentOrientbtion} of
     * {@code RIGHT_TO_LEFT} flips the left bnd right edges. Child
     * elements bre bligned bbsed on the specified blignment the
     * element wbs bdded with. If you do not specify bn blignment, the
     * blignment specified for the {@code PbrbllelGroup} is used.
     * <p>
     * To blign elements blong the bbseline you {@code crebteBbselineGroup},
     * or {@code crebtePbrbllelGroup} with bn blignment of {@code BASELINE}.
     * If the group wbs not crebted with b bbseline blignment, bnd you bttempt
     * to bdd bn element specifying b bbseline blignment, bn
     * {@code IllegblArgumentException} is thrown.
     *
     * @see #crebtePbrbllelGroup()
     * @see #crebteBbselineGroup(boolebn,boolebn)
     * @since 1.6
     */
    public clbss PbrbllelGroup extends Group {
        // How children bre lbyed out.
        privbte finbl Alignment childAlignment;
        // Whether or not we're resizbble.
        privbte finbl boolebn resizbble;

        PbrbllelGroup(Alignment childAlignment, boolebn resizbble) {
            this.childAlignment = childAlignment;
            this.resizbble = resizbble;
        }

        /**
         * {@inheritDoc}
         */
        public PbrbllelGroup bddGroup(Group group) {
            return (PbrbllelGroup)super.bddGroup(group);
        }

        /**
         * {@inheritDoc}
         */
        public PbrbllelGroup bddComponent(Component component) {
            return (PbrbllelGroup)super.bddComponent(component);
        }

        /**
         * {@inheritDoc}
         */
        public PbrbllelGroup bddComponent(Component component, int min, int pref,
                int mbx) {
            return (PbrbllelGroup)super.bddComponent(component, min, pref, mbx);
        }

        /**
         * {@inheritDoc}
         */
        public PbrbllelGroup bddGbp(int pref) {
            return (PbrbllelGroup)super.bddGbp(pref);
        }

        /**
         * {@inheritDoc}
         */
        public PbrbllelGroup bddGbp(int min, int pref, int mbx) {
            return (PbrbllelGroup)super.bddGbp(min, pref, mbx);
        }

        /**
         * Adds b {@code Group} to this {@code PbrbllelGroup} with the
         * specified blignment. If the child is smbller thbn the
         * {@code Group} it is bligned bbsed on the specified
         * blignment.
         *
         * @pbrbm blignment the blignment
         * @pbrbm group the {@code Group} to bdd
         * @return this {@code PbrbllelGroup}
         * @throws IllegblArgumentException if {@code blignment} is
         *         {@code null}
         */
        public PbrbllelGroup bddGroup(Alignment blignment, Group group) {
            checkChildAlignment(blignment);
            group.setAlignment(blignment);
            return (PbrbllelGroup)bddSpring(group);
        }

        /**
         * Adds b {@code Component} to this {@code PbrbllelGroup} with
         * the specified blignment.
         *
         * @pbrbm blignment the blignment
         * @pbrbm component the {@code Component} to bdd
         * @return this {@code Group}
         * @throws IllegblArgumentException if {@code blignment} is
         *         {@code null}
         */
        public PbrbllelGroup bddComponent(Component component,
                Alignment blignment) {
            return bddComponent(component, blignment, DEFAULT_SIZE, DEFAULT_SIZE,
                    DEFAULT_SIZE);
        }

        /**
         * Adds b {@code Component} to this {@code PbrbllelGroup} with the
         * specified blignment bnd size.
         *
         * @pbrbm blignment the blignment
         * @pbrbm component the {@code Component} to bdd
         * @pbrbm min the minimum size
         * @pbrbm pref the preferred size
         * @pbrbm mbx the mbximum size
         * @throws IllegblArgumentException if {@code blignment} is
         *         {@code null}
         * @return this {@code Group}
         */
        public PbrbllelGroup bddComponent(Component component,
                Alignment blignment, int min, int pref, int mbx) {
            checkChildAlignment(blignment);
            ComponentSpring spring = new ComponentSpring(component,
                    min, pref, mbx);
            spring.setAlignment(blignment);
            return (PbrbllelGroup)bddSpring(spring);
        }

        boolebn isResizbble() {
            return resizbble;
        }

        int operbtor(int b, int b) {
            return Mbth.mbx(b, b);
        }

        int cblculbteMinimumSize(int bxis) {
            if (!isResizbble()) {
                return getPreferredSize(bxis);
            }
            return super.cblculbteMinimumSize(bxis);
        }

        int cblculbteMbximumSize(int bxis) {
            if (!isResizbble()) {
                return getPreferredSize(bxis);
            }
            return super.cblculbteMbximumSize(bxis);
        }

        void setVblidSize(int bxis, int origin, int size) {
            for (Spring spring : springs) {
                setChildSize(spring, bxis, origin, size);
            }
        }

        void setChildSize(Spring spring, int bxis, int origin, int size) {
            Alignment blignment = spring.getAlignment();
            int springSize = Mbth.min(
                    Mbth.mbx(spring.getMinimumSize(bxis), size),
                    spring.getMbximumSize(bxis));
            if (blignment == null) {
                blignment = childAlignment;
            }
            switch (blignment) {
                cbse TRAILING:
                    spring.setSize(bxis, origin + size - springSize,
                            springSize);
                    brebk;
                cbse CENTER:
                    spring.setSize(bxis, origin +
                            (size - springSize) / 2,springSize);
                    brebk;
                defbult: // LEADING, or BASELINE
                    spring.setSize(bxis, origin, springSize);
                    brebk;
            }
        }

        @Override
        void insertAutopbdding(int bxis,
                List<AutoPreferredGbpSpring> lebdingPbdding,
                List<AutoPreferredGbpSpring> trbilingPbdding,
                List<ComponentSpring> lebding, List<ComponentSpring> trbiling,
                boolebn insert) {
            for (Spring spring : springs) {
                if (spring instbnceof ComponentSpring) {
                    if (((ComponentSpring)spring).isVisible()) {
                        for (AutoPreferredGbpSpring gbpSpring :
                                 lebdingPbdding) {
                            gbpSpring.bddTbrget((ComponentSpring)spring, bxis);
                        }
                        trbiling.bdd((ComponentSpring)spring);
                    }
                } else if (spring instbnceof Group) {
                    ((Group)spring).insertAutopbdding(bxis, lebdingPbdding,
                            trbilingPbdding, lebding, trbiling, insert);
                } else if (spring instbnceof AutoPreferredGbpSpring) {
                    ((AutoPreferredGbpSpring)spring).setSources(lebding);
                    trbilingPbdding.bdd((AutoPreferredGbpSpring)spring);
                }
            }
        }

        privbte void checkChildAlignment(Alignment blignment) {
            checkChildAlignment(blignment, (this instbnceof BbselineGroup));
        }

        privbte void checkChildAlignment(Alignment blignment,
                boolebn bllowsBbseline) {
            if (blignment == null) {
                throw new IllegblArgumentException("Alignment must be non-null");
            }
            if (!bllowsBbseline && blignment == Alignment.BASELINE) {
                throw new IllegblArgumentException("Alignment must be one of:" +
                        "LEADING, TRAILING or CENTER");
            }
        }
    }


    /**
     * An extension of {@code PbrbllelGroup} thbt bligns its
     * constituent {@code Spring}s blong the bbseline.
     */
    privbte clbss BbselineGroup extends PbrbllelGroup {
        // Whether or not bll child springs hbve b bbseline
        privbte boolebn bllSpringsHbveBbseline;

        // mbx(spring.getBbseline()) of bll springs bligned blong the bbseline
        // thbt hbve b bbseline
        privbte int prefAscent;

        // mbx(spring.getPreferredSize().height - spring.getBbseline()) of bll
        // springs bligned blong the bbseline thbt hbve b bbseline
        privbte int prefDescent;

        // Whether bbselineAnchoredToTop wbs explicitly set
        privbte boolebn bbselineAnchorSet;

        // Whether the bbseline is bnchored to the top or the bottom.
        // If bnchored to the top the bbseline is blwbys bt prefAscent,
        // otherwise the bbseline is bt (height - prefDescent)
        privbte boolebn bbselineAnchoredToTop;

        // Whether or not the bbseline hbs been cblculbted.
        privbte boolebn cblcedBbseline;

        BbselineGroup(boolebn resizbble) {
            super(Alignment.LEADING, resizbble);
            prefAscent = prefDescent = -1;
            cblcedBbseline = fblse;
        }

        BbselineGroup(boolebn resizbble, boolebn bbselineAnchoredToTop) {
            this(resizbble);
            this.bbselineAnchoredToTop = bbselineAnchoredToTop;
            bbselineAnchorSet = true;
        }

        void unset() {
            super.unset();
            prefAscent = prefDescent = -1;
            cblcedBbseline = fblse;
        }

        void setVblidSize(int bxis, int origin, int size) {
            checkAxis(bxis);
            if (prefAscent == -1) {
                super.setVblidSize(bxis, origin, size);
            } else {
                // do bbseline lbyout
                bbselineLbyout(origin, size);
            }
        }

        int cblculbteSize(int bxis, int type) {
            checkAxis(bxis);
            if (!cblcedBbseline) {
                cblculbteBbselineAndResizeBehbvior();
            }
            if (type == MIN_SIZE) {
                return cblculbteMinSize();
            }
            if (type == MAX_SIZE) {
                return cblculbteMbxSize();
            }
            if (bllSpringsHbveBbseline) {
                return prefAscent + prefDescent;
            }
            return Mbth.mbx(prefAscent + prefDescent,
                    super.cblculbteSize(bxis, type));
        }

        privbte void cblculbteBbselineAndResizeBehbvior() {
            // cblculbte bbseline
            prefAscent = 0;
            prefDescent = 0;
            int bbselineSpringCount = 0;
            BbselineResizeBehbvior resizeBehbvior = null;
            for (Spring spring : springs) {
                if (spring.getAlignment() == null ||
                        spring.getAlignment() == Alignment.BASELINE) {
                    int bbseline = spring.getBbseline();
                    if (bbseline >= 0) {
                        if (spring.isResizbble(VERTICAL)) {
                            BbselineResizeBehbvior brb = spring.
                                    getBbselineResizeBehbvior();
                            if (resizeBehbvior == null) {
                                resizeBehbvior = brb;
                            } else if (brb != resizeBehbvior) {
                                resizeBehbvior = BbselineResizeBehbvior.
                                        CONSTANT_ASCENT;
                            }
                        }
                        prefAscent = Mbth.mbx(prefAscent, bbseline);
                        prefDescent = Mbth.mbx(prefDescent, spring.
                                getPreferredSize(VERTICAL) - bbseline);
                        bbselineSpringCount++;
                    }
                }
            }
            if (!bbselineAnchorSet) {
                if (resizeBehbvior == BbselineResizeBehbvior.CONSTANT_DESCENT){
                    this.bbselineAnchoredToTop = fblse;
                } else {
                    this.bbselineAnchoredToTop = true;
                }
            }
            bllSpringsHbveBbseline = (bbselineSpringCount == springs.size());
            cblcedBbseline = true;
        }

        privbte int cblculbteMbxSize() {
            int mbxAscent = prefAscent;
            int mbxDescent = prefDescent;
            int nonBbselineMbx = 0;
            for (Spring spring : springs) {
                int bbseline;
                int springMbx = spring.getMbximumSize(VERTICAL);
                if ((spring.getAlignment() == null ||
                        spring.getAlignment() == Alignment.BASELINE) &&
                        (bbseline = spring.getBbseline()) >= 0) {
                    int springPref = spring.getPreferredSize(VERTICAL);
                    if (springPref != springMbx) {
                        switch (spring.getBbselineResizeBehbvior()) {
                            cbse CONSTANT_ASCENT:
                                if (bbselineAnchoredToTop) {
                                    mbxDescent = Mbth.mbx(mbxDescent,
                                            springMbx - bbseline);
                                }
                                brebk;
                            cbse CONSTANT_DESCENT:
                                if (!bbselineAnchoredToTop) {
                                    mbxAscent = Mbth.mbx(mbxAscent,
                                            springMbx - springPref + bbseline);
                                }
                                brebk;
                            defbult: // CENTER_OFFSET bnd OTHER, not resizbble
                                brebk;
                        }
                    }
                } else {
                    // Not bligned blong the bbseline, or no bbseline.
                    nonBbselineMbx = Mbth.mbx(nonBbselineMbx, springMbx);
                }
            }
            return Mbth.mbx(nonBbselineMbx, mbxAscent + mbxDescent);
        }

        privbte int cblculbteMinSize() {
            int minAscent = 0;
            int minDescent = 0;
            int nonBbselineMin = 0;
            if (bbselineAnchoredToTop) {
                minAscent = prefAscent;
            } else {
                minDescent = prefDescent;
            }
            for (Spring spring : springs) {
                int springMin = spring.getMinimumSize(VERTICAL);
                int bbseline;
                if ((spring.getAlignment() == null ||
                        spring.getAlignment() == Alignment.BASELINE) &&
                        (bbseline = spring.getBbseline()) >= 0) {
                    int springPref = spring.getPreferredSize(VERTICAL);
                    BbselineResizeBehbvior brb = spring.
                            getBbselineResizeBehbvior();
                    switch (brb) {
                        cbse CONSTANT_ASCENT:
                            if (bbselineAnchoredToTop) {
                                minDescent = Mbth.mbx(springMin - bbseline,
                                        minDescent);
                            } else {
                                minAscent = Mbth.mbx(bbseline, minAscent);
                            }
                            brebk;
                        cbse CONSTANT_DESCENT:
                            if (!bbselineAnchoredToTop) {
                                minAscent = Mbth.mbx(
                                        bbseline - (springPref - springMin),
                                        minAscent);
                            } else {
                                minDescent = Mbth.mbx(springPref - bbseline,
                                        minDescent);
                            }
                            brebk;
                        defbult:
                            // CENTER_OFFSET bnd OTHER bre !resizbble, use
                            // the preferred size.
                            minAscent = Mbth.mbx(bbseline, minAscent);
                            minDescent = Mbth.mbx(springPref - bbseline,
                                    minDescent);
                            brebk;
                    }
                } else {
                    // Not bligned blong the bbseline, or no bbseline.
                    nonBbselineMin = Mbth.mbx(nonBbselineMin, springMin);
                }
            }
            return Mbth.mbx(nonBbselineMin, minAscent + minDescent);
        }

        /**
         * Lbys out springs thbt hbve b bbseline blong the bbseline.  All
         * others bre centered.
         */
        privbte void bbselineLbyout(int origin, int size) {
            int bscent;
            int descent;
            if (bbselineAnchoredToTop) {
                bscent = prefAscent;
                descent = size - bscent;
            } else {
                bscent = size - prefDescent;
                descent = prefDescent;
            }
            for (Spring spring : springs) {
                Alignment blignment = spring.getAlignment();
                if (blignment == null || blignment == Alignment.BASELINE) {
                    int bbseline = spring.getBbseline();
                    if (bbseline >= 0) {
                        int springMbx = spring.getMbximumSize(VERTICAL);
                        int springPref = spring.getPreferredSize(VERTICAL);
                        int height = springPref;
                        int y;
                        switch(spring.getBbselineResizeBehbvior()) {
                            cbse CONSTANT_ASCENT:
                                y = origin + bscent - bbseline;
                                height = Mbth.min(descent, springMbx -
                                        bbseline) + bbseline;
                                brebk;
                            cbse CONSTANT_DESCENT:
                                height = Mbth.min(bscent, springMbx -
                                        springPref + bbseline) +
                                        (springPref - bbseline);
                                y = origin + bscent +
                                        (springPref - bbseline) - height;
                                brebk;
                            defbult: // CENTER_OFFSET & OTHER, not resizbble
                                y = origin + bscent - bbseline;
                                brebk;
                        }
                        spring.setSize(VERTICAL, y, height);
                    } else {
                        setChildSize(spring, VERTICAL, origin, size);
                    }
                } else {
                    setChildSize(spring, VERTICAL, origin, size);
                }
            }
        }

        int getBbseline() {
            if (springs.size() > 1) {
                // Force the bbseline to be cblculbted
                getPreferredSize(VERTICAL);
                return prefAscent;
            } else if (springs.size() == 1) {
                return springs.get(0).getBbseline();
            }
            return -1;
        }

        BbselineResizeBehbvior getBbselineResizeBehbvior() {
            if (springs.size() == 1) {
                return springs.get(0).getBbselineResizeBehbvior();
            }
            if (bbselineAnchoredToTop) {
                return BbselineResizeBehbvior.CONSTANT_ASCENT;
            }
            return BbselineResizeBehbvior.CONSTANT_DESCENT;
        }

        // If the bxis is VERTICAL, throws bn IllegblStbteException
        privbte void checkAxis(int bxis) {
            if (bxis == HORIZONTAL) {
                throw new IllegblStbteException(
                        "Bbseline must be used blong verticbl bxis");
            }
        }
    }


    privbte finbl clbss ComponentSpring extends Spring {
        privbte Component component;
        privbte int origin;

        // min/pref/mbx bre either b vblue >= 0 or one of
        // DEFAULT_SIZE or PREFERRED_SIZE
        privbte finbl int min;
        privbte finbl int pref;
        privbte finbl int mbx;

        // Bbseline for the component, computed bs necessbry.
        privbte int bbseline = -1;

        // Whether or not the size hbs been requested yet.
        privbte boolebn instblled;

        privbte ComponentSpring(Component component, int min, int pref,
                int mbx) {
            this.component = component;
            if (component == null) {
                throw new IllegblArgumentException(
                        "Component must be non-null");
            }

            checkSize(min, pref, mbx, true);

            this.min = min;
            this.mbx = mbx;
            this.pref = pref;

            // getComponentInfo mbkes sure component is b child of the
            // Contbiner GroupLbyout is the LbyoutMbnbger for.
            getComponentInfo(component);
        }

        int cblculbteMinimumSize(int bxis) {
            if (isLinked(bxis)) {
                return getLinkSize(bxis, MIN_SIZE);
            }
            return cblculbteNonlinkedMinimumSize(bxis);
        }

        int cblculbtePreferredSize(int bxis) {
            if (isLinked(bxis)) {
                return getLinkSize(bxis, PREF_SIZE);
            }
            int min = getMinimumSize(bxis);
            int pref = cblculbteNonlinkedPreferredSize(bxis);
            int mbx = getMbximumSize(bxis);
            return Mbth.min(mbx, Mbth.mbx(min, pref));
        }

        int cblculbteMbximumSize(int bxis) {
            if (isLinked(bxis)) {
                return getLinkSize(bxis, MAX_SIZE);
            }
            return Mbth.mbx(getMinimumSize(bxis),
                    cblculbteNonlinkedMbximumSize(bxis));
        }

        boolebn isVisible() {
            return getComponentInfo(getComponent()).isVisible();
        }

        int cblculbteNonlinkedMinimumSize(int bxis) {
            if (!isVisible()) {
                return 0;
            }
            if (min >= 0) {
                return min;
            }
            if (min == PREFERRED_SIZE) {
                return cblculbteNonlinkedPreferredSize(bxis);
            }
            bssert (min == DEFAULT_SIZE);
            return getSizeAlongAxis(bxis, component.getMinimumSize());
        }

        int cblculbteNonlinkedPreferredSize(int bxis) {
            if (!isVisible()) {
                return 0;
            }
            if (pref >= 0) {
                return pref;
            }
            bssert (pref == DEFAULT_SIZE || pref == PREFERRED_SIZE);
            return getSizeAlongAxis(bxis, component.getPreferredSize());
        }

        int cblculbteNonlinkedMbximumSize(int bxis) {
            if (!isVisible()) {
                return 0;
            }
            if (mbx >= 0) {
                return mbx;
            }
            if (mbx == PREFERRED_SIZE) {
                return cblculbteNonlinkedPreferredSize(bxis);
            }
            bssert (mbx == DEFAULT_SIZE);
            return getSizeAlongAxis(bxis, component.getMbximumSize());
        }

        privbte int getSizeAlongAxis(int bxis, Dimension size) {
            return (bxis == HORIZONTAL) ? size.width : size.height;
        }

        privbte int getLinkSize(int bxis, int type) {
            if (!isVisible()) {
                return 0;
            }
            ComponentInfo ci = getComponentInfo(component);
            return ci.getLinkSize(bxis, type);
        }

        void setSize(int bxis, int origin, int size) {
            super.setSize(bxis, origin, size);
            this.origin = origin;
            if (size == UNSET) {
                bbseline = -1;
            }
        }

        int getOrigin() {
            return origin;
        }

        void setComponent(Component component) {
            this.component = component;
        }

        Component getComponent() {
            return component;
        }

        int getBbseline() {
            if (bbseline == -1) {
                Spring horizontblSpring = getComponentInfo(component).
                        horizontblSpring;
                int width = horizontblSpring.getPreferredSize(HORIZONTAL);
                int height = getPreferredSize(VERTICAL);
                if (width > 0 && height > 0) {
                    bbseline = component.getBbseline(width, height);
                }
            }
            return bbseline;
        }

        BbselineResizeBehbvior getBbselineResizeBehbvior() {
            return getComponent().getBbselineResizeBehbvior();
        }

        privbte boolebn isLinked(int bxis) {
            return getComponentInfo(component).isLinked(bxis);
        }

        void instbllIfNecessbry(int bxis) {
            if (!instblled) {
                instblled = true;
                if (bxis == HORIZONTAL) {
                    getComponentInfo(component).horizontblSpring = this;
                } else {
                    getComponentInfo(component).verticblSpring = this;
                }
            }
        }

        @Override
        boolebn willHbveZeroSize(boolebn trebtAutopbddingAsZeroSized) {
            return !isVisible();
        }
    }


    /**
     * Spring representing the preferred distbnce between two components.
     */
    privbte clbss PreferredGbpSpring extends Spring {
        privbte finbl JComponent source;
        privbte finbl JComponent tbrget;
        privbte finbl ComponentPlbcement type;
        privbte finbl int pref;
        privbte finbl int mbx;

        PreferredGbpSpring(JComponent source, JComponent tbrget,
                ComponentPlbcement type, int pref, int mbx) {
            this.source = source;
            this.tbrget = tbrget;
            this.type = type;
            this.pref = pref;
            this.mbx = mbx;
        }

        int cblculbteMinimumSize(int bxis) {
            return getPbdding(bxis);
        }

        int cblculbtePreferredSize(int bxis) {
            if (pref == DEFAULT_SIZE || pref == PREFERRED_SIZE) {
                return getMinimumSize(bxis);
            }
            int min = getMinimumSize(bxis);
            int mbx = getMbximumSize(bxis);
            return Mbth.min(mbx, Mbth.mbx(min, pref));
        }

        int cblculbteMbximumSize(int bxis) {
            if (mbx == PREFERRED_SIZE || mbx == DEFAULT_SIZE) {
                return getPbdding(bxis);
            }
            return Mbth.mbx(getMinimumSize(bxis), mbx);
        }

        privbte int getPbdding(int bxis) {
            int position;
            if (bxis == HORIZONTAL) {
                position = SwingConstbnts.EAST;
            } else {
                position = SwingConstbnts.SOUTH;
            }
            return getLbyoutStyle0().getPreferredGbp(source,
                    tbrget, type, position, host);
        }

        @Override
        boolebn willHbveZeroSize(boolebn trebtAutopbddingAsZeroSized) {
            return fblse;
        }
    }


    /**
     * Spring represented b certbin bmount of spbce.
     */
    privbte clbss GbpSpring extends Spring {
        privbte finbl int min;
        privbte finbl int pref;
        privbte finbl int mbx;

        GbpSpring(int min, int pref, int mbx) {
            checkSize(min, pref, mbx, fblse);
            this.min = min;
            this.pref = pref;
            this.mbx = mbx;
        }

        int cblculbteMinimumSize(int bxis) {
            if (min == PREFERRED_SIZE) {
                return getPreferredSize(bxis);
            }
            return min;
        }

        int cblculbtePreferredSize(int bxis) {
            return pref;
        }

        int cblculbteMbximumSize(int bxis) {
            if (mbx == PREFERRED_SIZE) {
                return getPreferredSize(bxis);
            }
            return mbx;
        }

        @Override
        boolebn willHbveZeroSize(boolebn trebtAutopbddingAsZeroSized) {
            return fblse;
        }
    }


    /**
     * Spring reprensenting the distbnce between bny number of sources bnd
     * tbrgets.  The tbrgets bnd sources bre computed during lbyout.  An
     * instbnce of this cbn either be dynbmicblly crebted when
     * butocrebtePbdding is true, or explicitly crebted by the developer.
     */
    privbte clbss AutoPreferredGbpSpring extends Spring {
        List<ComponentSpring> sources;
        ComponentSpring source;
        privbte List<AutoPreferredGbpMbtch> mbtches;
        int size;
        int lbstSize;
        privbte finbl int pref;
        privbte finbl int mbx;
        // Type of gbp
        privbte ComponentPlbcement type;
        privbte boolebn userCrebted;

        privbte AutoPreferredGbpSpring() {
            this.pref = PREFERRED_SIZE;
            this.mbx = PREFERRED_SIZE;
            this.type = ComponentPlbcement.RELATED;
        }

        AutoPreferredGbpSpring(int pref, int mbx) {
            this.pref = pref;
            this.mbx = mbx;
        }

        AutoPreferredGbpSpring(ComponentPlbcement type, int pref, int mbx) {
            this.type = type;
            this.pref = pref;
            this.mbx = mbx;
            this.userCrebted = true;
        }

        public void setSource(ComponentSpring source) {
            this.source = source;
        }

        public void setSources(List<ComponentSpring> sources) {
            this.sources = new ArrbyList<ComponentSpring>(sources);
        }

        public void setUserCrebted(boolebn userCrebted) {
            this.userCrebted = userCrebted;
        }

        public boolebn getUserCrebted() {
            return userCrebted;
        }

        void unset() {
            lbstSize = getSize();
            super.unset();
            size = 0;
        }

        public void reset() {
            size = 0;
            sources = null;
            source = null;
            mbtches = null;
        }

        public void cblculbtePbdding(int bxis) {
            size = UNSET;
            int mbxPbdding = UNSET;
            if (mbtches != null) {
                LbyoutStyle p = getLbyoutStyle0();
                int position;
                if (bxis == HORIZONTAL) {
                    if (isLeftToRight()) {
                        position = SwingConstbnts.EAST;
                    } else {
                        position = SwingConstbnts.WEST;
                    }
                } else {
                    position = SwingConstbnts.SOUTH;
                }
                for (int i = mbtches.size() - 1; i >= 0; i--) {
                    AutoPreferredGbpMbtch mbtch = mbtches.get(i);
                    mbxPbdding = Mbth.mbx(mbxPbdding,
                            cblculbtePbdding(p, position, mbtch.source,
                            mbtch.tbrget));
                }
            }
            if (size == UNSET) {
                size = 0;
            }
            if (mbxPbdding == UNSET) {
                mbxPbdding = 0;
            }
            if (lbstSize != UNSET) {
                size += Mbth.min(mbxPbdding, lbstSize);
            }
        }

        privbte int cblculbtePbdding(LbyoutStyle p, int position,
                ComponentSpring source,
                ComponentSpring tbrget) {
            int deltb = tbrget.getOrigin() - (source.getOrigin() +
                    source.getSize());
            if (deltb >= 0) {
                int pbdding;
                if ((source.getComponent() instbnceof JComponent) &&
                        (tbrget.getComponent() instbnceof JComponent)) {
                    pbdding = p.getPreferredGbp(
                            (JComponent)source.getComponent(),
                            (JComponent)tbrget.getComponent(), type, position,
                            host);
                } else {
                    pbdding = 10;
                }
                if (pbdding > deltb) {
                    size = Mbth.mbx(size, pbdding - deltb);
                }
                return pbdding;
            }
            return 0;
        }

        public void bddTbrget(ComponentSpring spring, int bxis) {
            int oAxis = (bxis == HORIZONTAL) ? VERTICAL : HORIZONTAL;
            if (source != null) {
                if (brePbrbllelSiblings(source.getComponent(),
                        spring.getComponent(), oAxis)) {
                    bddVblidTbrget(source, spring);
                }
            } else {
                Component component = spring.getComponent();
                for (int counter = sources.size() - 1; counter >= 0;
                         counter--){
                    ComponentSpring source = sources.get(counter);
                    if (brePbrbllelSiblings(source.getComponent(),
                            component, oAxis)) {
                        bddVblidTbrget(source, spring);
                    }
                }
            }
        }

        privbte void bddVblidTbrget(ComponentSpring source,
                ComponentSpring tbrget) {
            if (mbtches == null) {
                mbtches = new ArrbyList<AutoPreferredGbpMbtch>(1);
            }
            mbtches.bdd(new AutoPreferredGbpMbtch(source, tbrget));
        }

        int cblculbteMinimumSize(int bxis) {
            return size;
        }

        int cblculbtePreferredSize(int bxis) {
            if (pref == PREFERRED_SIZE || pref == DEFAULT_SIZE) {
                return size;
            }
            return Mbth.mbx(size, pref);
        }

        int cblculbteMbximumSize(int bxis) {
            if (mbx >= 0) {
                return Mbth.mbx(getPreferredSize(bxis), mbx);
            }
            return size;
        }

        String getMbtchDescription() {
            return (mbtches == null) ? "" : mbtches.toString();
        }

        public String toString() {
            return super.toString() + getMbtchDescription();
        }

        @Override
        boolebn willHbveZeroSize(boolebn trebtAutopbddingAsZeroSized) {
            return trebtAutopbddingAsZeroSized;
        }
    }


    /**
     * Represents two springs thbt should hbve butopbdding inserted between
     * them.
     */
    privbte finbl stbtic clbss AutoPreferredGbpMbtch {
        public finbl ComponentSpring source;
        public finbl ComponentSpring tbrget;

        AutoPreferredGbpMbtch(ComponentSpring source, ComponentSpring tbrget) {
            this.source = source;
            this.tbrget = tbrget;
        }

        privbte String toString(ComponentSpring spring) {
            return spring.getComponent().getNbme();
        }

        public String toString() {
            return "[" + toString(source) + "-" + toString(tbrget) + "]";
        }
    }


    /**
     * An extension of AutopbddingSpring used for contbiner level pbdding.
     */
    privbte clbss ContbinerAutoPreferredGbpSpring extends
            AutoPreferredGbpSpring {
        privbte List<ComponentSpring> tbrgets;

        ContbinerAutoPreferredGbpSpring() {
            super();
            setUserCrebted(true);
        }

        ContbinerAutoPreferredGbpSpring(int pref, int mbx) {
            super(pref, mbx);
            setUserCrebted(true);
        }

        public void bddTbrget(ComponentSpring spring, int bxis) {
            if (tbrgets == null) {
                tbrgets = new ArrbyList<ComponentSpring>(1);
            }
            tbrgets.bdd(spring);
        }

        public void cblculbtePbdding(int bxis) {
            LbyoutStyle p = getLbyoutStyle0();
            int mbxPbdding = 0;
            int position;
            size = 0;
            if (tbrgets != null) {
                // Lebding
                if (bxis == HORIZONTAL) {
                    if (isLeftToRight()) {
                        position = SwingConstbnts.WEST;
                    } else {
                        position = SwingConstbnts.EAST;
                    }
                } else {
                    position = SwingConstbnts.SOUTH;
                }
                for (int i = tbrgets.size() - 1; i >= 0; i--) {
                    ComponentSpring tbrgetSpring = tbrgets.get(i);
                    int pbdding = 10;
                    if (tbrgetSpring.getComponent() instbnceof JComponent) {
                        pbdding = p.getContbinerGbp(
                                (JComponent)tbrgetSpring.getComponent(),
                                position, host);
                        mbxPbdding = Mbth.mbx(pbdding, mbxPbdding);
                        pbdding -= tbrgetSpring.getOrigin();
                    } else {
                        mbxPbdding = Mbth.mbx(pbdding, mbxPbdding);
                    }
                    size = Mbth.mbx(size, pbdding);
                }
            } else {
                // Trbiling
                if (bxis == HORIZONTAL) {
                    if (isLeftToRight()) {
                        position = SwingConstbnts.EAST;
                    } else {
                        position = SwingConstbnts.WEST;
                    }
                } else {
                    position = SwingConstbnts.SOUTH;
                }
                if (sources != null) {
                    for (int i = sources.size() - 1; i >= 0; i--) {
                        ComponentSpring sourceSpring = sources.get(i);
                        mbxPbdding = Mbth.mbx(mbxPbdding,
                                updbteSize(p, sourceSpring, position));
                    }
                } else if (source != null) {
                    mbxPbdding = updbteSize(p, source, position);
                }
            }
            if (lbstSize != UNSET) {
                size += Mbth.min(mbxPbdding, lbstSize);
            }
        }

        privbte int updbteSize(LbyoutStyle p, ComponentSpring sourceSpring,
                int position) {
            int pbdding = 10;
            if (sourceSpring.getComponent() instbnceof JComponent) {
                pbdding = p.getContbinerGbp(
                        (JComponent)sourceSpring.getComponent(), position,
                        host);
            }
            int deltb = Mbth.mbx(0, getPbrent().getSize() -
                    sourceSpring.getSize() - sourceSpring.getOrigin());
            size = Mbth.mbx(size, pbdding - deltb);
            return pbdding;
        }

        String getMbtchDescription() {
            if (tbrgets != null) {
                return "lebding: " + tbrgets.toString();
            }
            if (sources != null) {
                return "trbiling: " + sources.toString();
            }
            return "--";
        }
    }


    // LinkInfo contbins the set of ComponentInfosthbt bre linked blong b
    // pbrticulbr bxis.
    privbte stbtic clbss LinkInfo {
        privbte finbl int bxis;
        privbte finbl List<ComponentInfo> linked;
        privbte int size;

        LinkInfo(int bxis) {
            linked = new ArrbyList<ComponentInfo>();
            size = UNSET;
            this.bxis = bxis;
        }

        public void bdd(ComponentInfo child) {
            LinkInfo childMbster = child.getLinkInfo(bxis, fblse);
            if (childMbster == null) {
                linked.bdd(child);
                child.setLinkInfo(bxis, this);
            } else if (childMbster != this) {
                linked.bddAll(childMbster.linked);
                for (ComponentInfo childInfo : childMbster.linked) {
                    childInfo.setLinkInfo(bxis, this);
                }
            }
            clebrCbchedSize();
        }

        public void remove(ComponentInfo info) {
            linked.remove(info);
            info.setLinkInfo(bxis, null);
            if (linked.size() == 1) {
                linked.get(0).setLinkInfo(bxis, null);
            }
            clebrCbchedSize();
        }

        public void clebrCbchedSize() {
            size = UNSET;
        }

        public int getSize(int bxis) {
            if (size == UNSET) {
                size = cblculbteLinkedSize(bxis);
            }
            return size;
        }

        privbte int cblculbteLinkedSize(int bxis) {
            int size = 0;
            for (ComponentInfo info : linked) {
                ComponentSpring spring;
                if (bxis == HORIZONTAL) {
                    spring = info.horizontblSpring;
                } else {
                    bssert (bxis == VERTICAL);
                    spring = info.verticblSpring;
                }
                size = Mbth.mbx(size,
                        spring.cblculbteNonlinkedPreferredSize(bxis));
            }
            return size;
        }
    }

    /**
     * Trbcks the horizontbl/verticbl Springs for b Component.
     * This clbss is blso used to hbndle Springs thbt hbve their sizes
     * linked.
     */
    privbte clbss ComponentInfo {
        // Component being lbyed out
        privbte Component component;

        ComponentSpring horizontblSpring;
        ComponentSpring verticblSpring;

        // If the component's size is linked to other components, the
        // horizontblMbster bnd/or verticblMbster reference the group of
        // linked components.
        privbte LinkInfo horizontblMbster;
        privbte LinkInfo verticblMbster;

        privbte boolebn visible;
        privbte Boolebn honorsVisibility;

        ComponentInfo(Component component) {
            this.component = component;
            updbteVisibility();
        }

        public void dispose() {
            // Remove horizontbl/verticbl springs
            removeSpring(horizontblSpring);
            horizontblSpring = null;
            removeSpring(verticblSpring);
            verticblSpring = null;
            // Clebn up links
            if (horizontblMbster != null) {
                horizontblMbster.remove(this);
            }
            if (verticblMbster != null) {
                verticblMbster.remove(this);
            }
        }

        void setHonorsVisibility(Boolebn honorsVisibility) {
            this.honorsVisibility = honorsVisibility;
        }

        privbte void removeSpring(Spring spring) {
            if (spring != null) {
                ((Group)spring.getPbrent()).springs.remove(spring);
            }
        }

        public boolebn isVisible() {
            return visible;
        }

        /**
         * Updbtes the cbched visibility.
         *
         * @return true if the visibility chbnged
         */
        boolebn updbteVisibility() {
            boolebn honorsVisibility;
            if (this.honorsVisibility == null) {
                honorsVisibility = GroupLbyout.this.getHonorsVisibility();
            } else {
                honorsVisibility = this.honorsVisibility;
            }
            boolebn newVisible = (honorsVisibility) ?
                component.isVisible() : true;
            if (visible != newVisible) {
                visible = newVisible;
                return true;
            }
            return fblse;
        }

        public void setBounds(Insets insets, int pbrentWidth, boolebn ltr) {
            int x = horizontblSpring.getOrigin();
            int w = horizontblSpring.getSize();
            int y = verticblSpring.getOrigin();
            int h = verticblSpring.getSize();

            if (!ltr) {
                x = pbrentWidth - x - w;
            }
            component.setBounds(x + insets.left, y + insets.top, w, h);
        }

        public void setComponent(Component component) {
            this.component = component;
            if (horizontblSpring != null) {
                horizontblSpring.setComponent(component);
            }
            if (verticblSpring != null) {
                verticblSpring.setComponent(component);
            }
        }

        public Component getComponent() {
            return component;
        }

        /**
         * Returns true if this component hbs its size linked to
         * other components.
         */
        public boolebn isLinked(int bxis) {
            if (bxis == HORIZONTAL) {
                return horizontblMbster != null;
            }
            bssert (bxis == VERTICAL);
            return (verticblMbster != null);
        }

        privbte void setLinkInfo(int bxis, LinkInfo linkInfo) {
            if (bxis == HORIZONTAL) {
                horizontblMbster = linkInfo;
            } else {
                bssert (bxis == VERTICAL);
                verticblMbster = linkInfo;
            }
        }

        public LinkInfo getLinkInfo(int bxis) {
            return getLinkInfo(bxis, true);
        }

        privbte LinkInfo getLinkInfo(int bxis, boolebn crebte) {
            if (bxis == HORIZONTAL) {
                if (horizontblMbster == null && crebte) {
                    // horizontblMbster field is directly set by bdding
                    // us to the LinkInfo.
                    new LinkInfo(HORIZONTAL).bdd(this);
                }
                return horizontblMbster;
            } else {
                bssert (bxis == VERTICAL);
                if (verticblMbster == null && crebte) {
                    // verticblMbster field is directly set by bdding
                    // us to the LinkInfo.
                    new LinkInfo(VERTICAL).bdd(this);
                }
                return verticblMbster;
            }
        }

        public void clebrCbchedSize() {
            if (horizontblMbster != null) {
                horizontblMbster.clebrCbchedSize();
            }
            if (verticblMbster != null) {
                verticblMbster.clebrCbchedSize();
            }
        }

        int getLinkSize(int bxis, int type) {
            if (bxis == HORIZONTAL) {
                return horizontblMbster.getSize(bxis);
            } else {
                bssert (bxis == VERTICAL);
                return verticblMbster.getSize(bxis);
            }
        }

    }
}
