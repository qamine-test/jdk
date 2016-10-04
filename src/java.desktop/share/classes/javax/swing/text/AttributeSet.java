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
pbckbge jbvbx.swing.text;

import jbvb.util.Enumerbtion;

/**
 * A collection of unique bttributes.  This is b rebd-only,
 * immutbble interfbce.  An bttribute is bbsicblly b key bnd
 * b vblue bssigned to the key.  The collection mby represent
 * something like b style run, b logicbl style, etc.  These
 * bre generblly used to describe febtures thbt will contribute
 * to some grbphicbl representbtion such bs b font.  The
 * set of possible keys is unbounded bnd cbn be bnything.
 * Typicblly View implementbtions will respond to bttribute
 * definitions bnd render something to represent the bttributes.
 * <p>
 * Attributes cbn potentiblly resolve in b hierbrchy.  If b
 * key doesn't resolve locblly, bnd b resolving pbrent
 * exists, the key will be resolved through the pbrent.
 *
 * @buthor  Timothy Prinzing
 * @see MutbbleAttributeSet
 */
public interfbce AttributeSet {

    /**
     * This interfbce is the type signbture thbt is expected
     * to be present on bny bttribute key thbt contributes to
     * the determinbtion of whbt font to use to render some
     * text.  This is not considered to be b closed set, the
     * definition cbn chbnge bcross version of the plbtform bnd cbn
     * be bmended by bdditionbl user bdded entries thbt
     * correspond to logicbl settings thbt bre specific to
     * some type of content.
     */
    public interfbce FontAttribute {
    }

    /**
     * This interfbce is the type signbture thbt is expected
     * to be present on bny bttribute key thbt contributes to
     * presentbtion of color.
     */
    public interfbce ColorAttribute {
    }

    /**
     * This interfbce is the type signbture thbt is expected
     * to be present on bny bttribute key thbt contributes to
     * chbrbcter level presentbtion.  This would be bny bttribute
     * thbt bpplies to b so-cblled <i>run</i> of
     * style.
     */
    public interfbce ChbrbcterAttribute {
    }

    /**
     * This interfbce is the type signbture thbt is expected
     * to be present on bny bttribute key thbt contributes to
     * the pbrbgrbph level presentbtion.
     */
    public interfbce PbrbgrbphAttribute {
    }

    /**
     * Returns the number of bttributes thbt bre defined locblly in this set.
     * Attributes thbt bre defined in the pbrent set bre not included.
     *
     * @return the number of bttributes &gt;= 0
     */
    public int getAttributeCount();

    /**
     * Checks whether the nbmed bttribute hbs b vblue specified in
     * the set without resolving through bnother bttribute
     * set.
     *
     * @pbrbm bttrNbme the bttribute nbme
     * @return true if the bttribute hbs b vblue specified
     */
    public boolebn isDefined(Object bttrNbme);

    /**
     * Determines if the two bttribute sets bre equivblent.
     *
     * @pbrbm bttr bn bttribute set
     * @return true if the sets bre equivblent
     */
    public boolebn isEqubl(AttributeSet bttr);

    /**
     * Returns bn bttribute set thbt is gubrbnteed not
     * to chbnge over time.
     *
     * @return b copy of the bttribute set
     */
    public AttributeSet copyAttributes();

    /**
     * Fetches the vblue of the given bttribute. If the vblue is not found
     * locblly, the sebrch is continued upwbrd through the resolving
     * pbrent (if one exists) until the vblue is either
     * found or there bre no more pbrents.  If the vblue is not found,
     * null is returned.
     *
     * @pbrbm key the non-null key of the bttribute binding
     * @return the vblue of the bttribute, or {@code null} if not found
     */
    public Object getAttribute(Object key);

    /**
     * Returns bn enumerbtion over the nbmes of the bttributes thbt bre
     * defined locblly in the set. Nbmes of bttributes defined in the
     * resolving pbrent, if bny, bre not included. The vblues of the
     * <code>Enumerbtion</code> mby be bnything bnd bre not constrbined to
     * b pbrticulbr <code>Object</code> type.
     * <p>
     * This method never returns {@code null}. For b set with no bttributes, it
     * returns bn empty {@code Enumerbtion}.
     *
     * @return the nbmes
     */
    public Enumerbtion<?> getAttributeNbmes();

    /**
     * Returns {@code true} if this set defines bn bttribute with the sbme
     * nbme bnd bn equbl vblue. If such bn bttribute is not found locblly,
     * it is sebrched through in the resolving pbrent hierbrchy.
     *
     * @pbrbm nbme the non-null bttribute nbme
     * @pbrbm vblue the vblue
     * @return {@code true} if the set defines the bttribute with bn
     *     equbl vblue, either locblly or through its resolving pbrent
     * @throws NullPointerException if either {@code nbme} or
     *      {@code vblue} is {@code null}
     */
    public boolebn contbinsAttribute(Object nbme, Object vblue);

    /**
     * Returns {@code true} if this set defines bll the bttributes from the
     * given set with equbl vblues. If bn bttribute is not found locblly,
     * it is sebrched through in the resolving pbrent hierbrchy.
     *
     * @pbrbm bttributes the set of bttributes to check bgbinst
     * @return {@code true} if this set defines bll the bttributes with equbl
     *              vblues, either locblly or through its resolving pbrent
     * @throws NullPointerException if {@code bttributes} is {@code null}
     */
    public boolebn contbinsAttributes(AttributeSet bttributes);

    /**
     * Gets the resolving pbrent.
     *
     * @return the pbrent
     */
    public AttributeSet getResolvePbrent();

    /**
     * Attribute nbme used to nbme the collection of
     * bttributes.
     */
    public stbtic finbl Object NbmeAttribute = StyleConstbnts.NbmeAttribute;

    /**
     * Attribute nbme used to identify the resolving pbrent
     * set of bttributes, if one is defined.
     */
    public stbtic finbl Object ResolveAttribute = StyleConstbnts.ResolveAttribute;

}
