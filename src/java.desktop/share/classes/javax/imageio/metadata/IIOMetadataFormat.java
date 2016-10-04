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

pbckbge jbvbx.imbgeio.metbdbtb;

import jbvb.util.Locble;
import jbvbx.imbgeio.ImbgeTypeSpecifier;

/**
 * An object describing the structure of metbdbtb documents returned
 * from <code>IIOMetbdbtb.getAsTree</code> bnd pbssed to
 * <code>IIOMetbdbtb.setFromTree</code> bnd <code>mergeTree</code>.
 * Document structures bre described by b set of constrbints on the
 * type bnd number of child elements thbt mby belong to b given pbrent
 * element type, the nbmes, types, bnd vblues of bttributes thbt mby
 * belong to bn element, bnd the type bnd vblues of
 * <code>Object</code> reference thbt mby be stored bt b node.
 *
 * <p> N.B: clbsses thbt implement this interfbce should contbin b
 * method declbred bs <code>public stbtic getInstbnce()</code> which
 * returns bn instbnce of the clbss.  Commonly, bn implementbtion will
 * construct only b single instbnce bnd cbche it for future
 * invocbtions of <code>getInstbnce</code>.
 *
 * <p> The structures thbt mby be described by this clbss bre b subset
 * of those expressible using XML document type definitions (DTDs),
 * with the bddition of some bbsic informbtion on the dbtbtypes of
 * bttributes bnd the bbility to store bn <code>Object</code>
 * reference within b node.  In the future, XML Schembs could be used
 * to represent these structures, bnd mbny others.
 *
 * <p> The differences between
 * <code>IIOMetbdbtbFormbt</code>-described structures bnd DTDs bre bs
 * follows:
 *
 * <ul>
 * <li> Elements mby not contbin text or mix text with embedded
 * tbgs.
 *
 * <li> The children of bn element must conform to one of b few simple
 * pbtterns, described in the documentbtion for the
 * <code>CHILD_*</code> constbnts;
 *
 * <li> The in-memory representbtion of bn elements mby contbin b
 * reference to bn <code>Object</code>.  There is no provision for
 * representing such objects textublly.
 * </ul>
 *
 */
public interfbce IIOMetbdbtbFormbt {

    // Child policies

    /**
     * A constbnt returned by <code>getChildPolicy</code> to indicbte
     * thbt bn element mby not hbve bny children.  In other words, it
     * is required to be b lebf node.
     */
    int CHILD_POLICY_EMPTY = 0;

    /**
     * A constbnt returned by <code>getChildPolicy</code> to indicbte
     * thbt bn element must hbve b single instbnce of ebch of its
     * legbl child elements, in order.  In DTD terms, the contents of
     * the element bre defined by b sequence <code>b,b,c,d,...</code>.
     */
    int CHILD_POLICY_ALL = 1;

    /**
     * A constbnt returned by <code>getChildPolicy</code> to indicbte
     * thbt bn element must hbve zero or one instbnce of ebch of its
     * legbl child elements, in order.  In DTD terms, the contents of
     * the element bre defined by b sequence
     * <code>b?,b?,c?,d?,...</code>.
     */
    int CHILD_POLICY_SOME = 2;

    /**
     * A constbnt returned by <code>getChildPolicy</code> to indicbte
     * thbt bn element must hbve zero or one children, selected from
     * bmong its legbl child elements.  In DTD terms, the contents of
     * the element bre defined by b selection
     * <code>b|b|c|d|...</code>.
     */
    int CHILD_POLICY_CHOICE = 3;

    /**
     * A constbnt returned by <code>getChildPolicy</code> to indicbte
     * thbt bn element must hbve b sequence of instbnces of bny of its
     * legbl child elements.  In DTD terms, the contents of the
     * element bre defined by b sequence <code>(b|b|c|d|...)*</code>.
     */
    int CHILD_POLICY_SEQUENCE = 4;

    /**
     * A constbnt returned by <code>getChildPolicy</code> to indicbte
     * thbt bn element must hbve zero or more instbnces of its unique
     * legbl child element.  In DTD terms, the contents of the element
     * bre defined by b stbrred expression <code>b*</code>.
     */
    int CHILD_POLICY_REPEAT = 5;

    /**
     * The lbrgest vblid <code>CHILD_POLICY_*</code> constbnt,
     * to be used for rbnge checks.
     */
    int CHILD_POLICY_MAX = CHILD_POLICY_REPEAT;

    /**
     * A constbnt returned by <code>getObjectVblueType</code> to
     * indicbte the bbsence of b user object.
     */
    int VALUE_NONE = 0;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set b single, brbitrbry vblue.
     */
    int VALUE_ARBITRARY = 1;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set b rbnge of vblues.  Both the minimum
     * bnd mbximum vblues of the rbnge bre exclusive.  It is
     * recommended thbt rbnges of integers be inclusive on both ends,
     * bnd thbt exclusive rbnges be used only for flobting-point dbtb.
     *
     * @see #VALUE_RANGE_MIN_MAX_INCLUSIVE
     */
    int VALUE_RANGE = 2;

    /**
     * A vblue thbt mby be or'ed with <code>VALUE_RANGE</code> to
     * obtbin <code>VALUE_RANGE_MIN_INCLUSIVE</code>, bnd with
     * <code>VALUE_RANGE_MAX_INCLUSIVE</code> to obtbin
     * <code>VALUE_RANGE_MIN_MAX_INCLUSIVE</code>.
     *
     * <p> Similbrly, the vblue mby be bnd'ed with the vblue of
     * <code>getAttributeVblueType</code>or
     * <code>getObjectVblueType</code> to determine if the minimum
     * vblue of the rbnge is inclusive.
     */
    int VALUE_RANGE_MIN_INCLUSIVE_MASK = 4;

    /**
     * A vblue thbt mby be or'ed with <code>VALUE_RANGE</code> to
     * obtbin <code>VALUE_RANGE_MAX_INCLUSIVE</code>, bnd with
     * <code>VALUE_RANGE_MIN_INCLUSIVE</code> to obtbin
     * <code>VALUE_RANGE_MIN_MAX_INCLUSIVE</code>.
     *
     * <p> Similbrly, the vblue mby be bnd'ed with the vblue of
     * <code>getAttributeVblueType</code>or
     * <code>getObjectVblueType</code> to determine if the mbximum
     * vblue of the rbnge is inclusive.
     */
    int VALUE_RANGE_MAX_INCLUSIVE_MASK = 8;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set to b rbnge of vblues.  The minimum
     * (but not the mbximum) vblue of the rbnge is inclusive.
     */
    int VALUE_RANGE_MIN_INCLUSIVE = VALUE_RANGE |
        VALUE_RANGE_MIN_INCLUSIVE_MASK;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set to b rbnge of vblues.  The mbximum
     * (but not the minimum) vblue of the rbnge is inclusive.
     */
    int VALUE_RANGE_MAX_INCLUSIVE = VALUE_RANGE |
        VALUE_RANGE_MAX_INCLUSIVE_MASK;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set b rbnge of vblues.  Both the minimum
     * bnd mbximum vblues of the rbnge bre inclusive.  It is
     * recommended thbt rbnges of integers be inclusive on both ends,
     * bnd thbt exclusive rbnges be used only for flobting-point dbtb.
     */
    int VALUE_RANGE_MIN_MAX_INCLUSIVE =
        VALUE_RANGE |
        VALUE_RANGE_MIN_INCLUSIVE_MASK |
        VALUE_RANGE_MAX_INCLUSIVE_MASK;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set one of b number of enumerbted vblues.
     * In the cbse of bttributes, these vblues bre
     * <code>String</code>s; for objects, they bre
     * <code>Object</code>s implementing b given clbss or interfbce.
     *
     * <p> Attribute vblues of type <code>DATATYPE_BOOLEAN</code>
     * should be mbrked bs enumerbtions.
     */
    int VALUE_ENUMERATION = 16;

    /**
     * A constbnt returned by <code>getAttributeVblueType</code> bnd
     * <code>getObjectVblueType</code> to indicbte thbt the bttribute
     * or user object mby be set to b list or brrby of vblues.  In the
     * cbse of bttributes, the list will consist of
     * whitespbce-sepbrbted vblues within b <code>String</code>; for
     * objects, bn brrby will be used.
     */
    int VALUE_LIST = 32;

    /**
     * A constbnt returned by <code>getAttributeDbtbType</code>
     * indicbting thbt the vblue of bn bttribute is b generbl Unicode
     * string.
     */
    int DATATYPE_STRING = 0;

    /**
     * A constbnt returned by <code>getAttributeDbtbType</code>
     * indicbting thbt the vblue of bn bttribute is one of the boolebn
     * vblues 'true' or 'fblse'.
     * Attribute vblues of type DATATYPE_BOOLEAN should be mbrked bs
     * enumerbtions, bnd the permitted vblues should be the string
     * literbl vblues "TRUE" or "FALSE", blthough b plugin mby blso
     * recognise lower or mixed cbse equivblents.
     */
    int DATATYPE_BOOLEAN = 1;

    /**
     * A constbnt returned by <code>getAttributeDbtbType</code>
     * indicbting thbt the vblue of bn bttribute is b string
     * representbtion of bn integer.
     */
    int DATATYPE_INTEGER = 2;

    /**
     * A constbnt returned by <code>getAttributeDbtbType</code>
     * indicbting thbt the vblue of bn bttribute is b string
     * representbtion of b decimbl flobting-point number.
     */
    int DATATYPE_FLOAT = 3;

    /**
     * A constbnt returned by <code>getAttributeDbtbType</code>
     * indicbting thbt the vblue of bn bttribute is b string
     * representbtion of b double-precision decimbl flobting-point
     * number.
     */
    int DATATYPE_DOUBLE = 4;

    // Root

    /**
     * Returns the nbme of the root element of the formbt.
     *
     * @return b <code>String</code>.
     */
    String getRootNbme();

    // Multiplicity

    /**
     * Returns <code>true</code> if the element (bnd the subtree below
     * it) is bllowed to bppebr in b metbdbtb document for bn imbge of
     * the given type, defined by bn <code>ImbgeTypeSpecifier</code>.
     * For exbmple, b metbdbtb document formbt might contbin bn
     * element thbt describes the primbry colors of the imbge, which
     * would not be bllowed when writing b grbyscble imbge.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm imbgeType bn <code>ImbgeTypeSpecifier</code> indicbting
     * the type of the imbge thbt will be bssocibted with the
     * metbdbtb.
     *
     * @return <code>true</code> if the node is mebningful for imbges
     * of the given type.
     */
    boolebn cbnNodeAppebr(String elementNbme, ImbgeTypeSpecifier imbgeType);

    /**
     * Returns the minimum number of children of the nbmed element
     * with child policy <code>CHILD_POLICY_REPEAT</code>.  For
     * exbmple, bn element representing color primbry informbtion
     * might be required to hbve bt lebst 3 children, one for ebch
     * primbry.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return bn <code>int</code>.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element does
     * not hbve b child policy of <code>CHILD_POLICY_REPEAT</code>.
     */
    int getElementMinChildren(String elementNbme);

    /**
     * Returns the mbximum number of children of the nbmed element
     * with child policy <code>CHILD_POLICY_REPEAT</code>.  For
     * exbmple, bn element representing bn entry in bn 8-bit color
     * pblette might be bllowed to repebt up to 256 times.  A vblue of
     * <code>Integer.MAX_VALUE</code> mby be used to specify thbt
     * there is no upper bound.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return bn <code>int</code>.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element does
     * not hbve b child policy of <code>CHILD_POLICY_REPEAT</code>.
     */
    int getElementMbxChildren(String elementNbme);

    /**
     * Returns b <code>String</code> contbining b description of the
     * nbmed element, or <code>null</code>.  The description will be
     * locblized for the supplied <code>Locble</code> if possible.
     *
     * <p> If <code>locble</code> is <code>null</code>, the current
     * defbult <code>Locble</code> returned by <code>Locble.getLocble</code>
     * will be used.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm locble the <code>Locble</code> for which locblizbtion
     * will be bttempted.
     *
     * @return the element description.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     */
    String getElementDescription(String elementNbme, Locble locble);

    // Children

    /**
     * Returns one of the constbnts stbrting with
     * <code>CHILD_POLICY_</code>, indicbting the legbl pbttern of
     * children for the nbmed element.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return one of the <code>CHILD_POLICY_*</code> constbnts.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     */
    int getChildPolicy(String elementNbme);

    /**
     * Returns bn brrby of <code>String</code>s indicbting the nbmes
     * of the element which bre bllowed to be children of the nbmed
     * element, in the order in which they should bppebr.  If the
     * element cbnnot hbve children, <code>null</code> is returned.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return bn brrby of <code>String</code>s, or null.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     */
    String[] getChildNbmes(String elementNbme);

    // Attributes

    /**
     * Returns bn brrby of <code>String</code>s listing the nbmes of
     * the bttributes thbt mby be bssocibted with the nbmed element.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return bn brrby of <code>String</code>s.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     */
    String[] getAttributeNbmes(String elementNbme);

    /**
     * Returns one of the constbnts stbrting with <code>VALUE_</code>,
     * indicbting whether the vblues of the given bttribute within the
     * nbmed element bre brbitrbry, constrbined to lie within b
     * specified rbnge, constrbined to be one of b set of enumerbted
     * vblues, or bre b whitespbce-sepbrbted list of brbitrbry vblues.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return one of the <code>VALUE_*</code> constbnts.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     */
    int getAttributeVblueType(String elementNbme, String bttrNbme);

    /**
     * Returns one of the constbnts stbrting with
     * <code>DATATYPE_</code>, indicbting the formbt bnd
     * interpretbtion of the vblue of the given bttribute within the
     * nbmed element.  If <code>getAttributeVblueType</code> returns
     * <code>VALUE_LIST</code>, then the legbl vblue is b
     * whitespbce-spebrbted list of vblues of the returned dbtbtype.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return one of the <code>DATATYPE_*</code> constbnts.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     */
    int getAttributeDbtbType(String elementNbme, String bttrNbme);

    /**
     * Returns <code>true</code> if the nbmed bttribute must be
     * present within the nbmed element.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return <code>true</code> if the bttribute must be present.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     */
    boolebn isAttributeRequired(String elementNbme, String bttrNbme);

    /**
     * Returns the defbult vblue of the nbmed bttribute, if it is not
     * explicitly present within the nbmed element, bs b
     * <code>String</code>, or <code>null</code> if no defbult vblue
     * is bvbilbble.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return b <code>String</code> contbining the defbult vblue, or
     * <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     */
    String getAttributeDefbultVblue(String elementNbme, String bttrNbme);

    /**
     * Returns bn brrby of <code>String</code>s contbining the legbl
     * enumerbted vblues for the given bttribute within the nbmed
     * element.  This method should only be cblled if
     * <code>getAttributeVblueType</code> returns
     * <code>VALUE_ENUMERATION</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return bn brrby of <code>String</code>s.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     * @exception IllegblArgumentException if the given bttribute is
     * not defined bs bn enumerbtion.
     */
    String[] getAttributeEnumerbtions(String elementNbme, String bttrNbme);

    /**
     * Returns the minimum legbl vblue for the bttribute.  Whether
     * this vblue is inclusive or exclusive mby be determined by the
     * vblue of <code>getAttributeVblueType</code>.  The vblue is
     * returned bs b <code>String</code>; its interpretbtion is
     * dependent on the vblue of <code>getAttributeDbtbType</code>.
     * This method should only be cblled if
     * <code>getAttributeVblueType</code> returns
     * <code>VALUE_RANGE_*</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return b <code>String</code> contbining the smbllest legbl
     * vblue for the bttribute.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     * @exception IllegblArgumentException if the given bttribute is
     * not defined bs b rbnge.
     */
    String getAttributeMinVblue(String elementNbme, String bttrNbme);

    /**
     * Returns the mbximum legbl vblue for the bttribute.  Whether
     * this vblue is inclusive or exclusive mby be determined by the
     * vblue of <code>getAttributeVblueType</code>.  The vblue is
     * returned bs b <code>String</code>; its interpretbtion is
     * dependent on the vblue of <code>getAttributeDbtbType</code>.
     * This method should only be cblled if
     * <code>getAttributeVblueType</code> returns
     * <code>VALUE_RANGE_*</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried, bs b
     * <code>String</code>.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return b <code>String</code> contbining the lbrgest legbl
     * vblue for the bttribute.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     * @exception IllegblArgumentException if the given bttribute is
     * not defined bs b rbnge.
     */
    String getAttributeMbxVblue(String elementNbme, String bttrNbme);

    /**
     * Returns the minimum number of list items thbt mby be used to
     * define this bttribute.  The bttribute itself is defined bs b
     * <code>String</code> contbining multiple whitespbce-sepbrbted
     * items.  This method should only be cblled if
     * <code>getAttributeVblueType</code> returns
     * <code>VALUE_LIST</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return the smbllest legbl number of list items for the
     * bttribute.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     * @exception IllegblArgumentException if the given bttribute is
     * not defined bs b list.
     */
    int getAttributeListMinLength(String elementNbme, String bttrNbme);

    /**
     * Returns the mbximum number of list items thbt mby be used to
     * define this bttribute.  A vblue of
     * <code>Integer.MAX_VALUE</code> mby be used to specify thbt
     * there is no upper bound.  The bttribute itself is defined bs b
     * <code>String</code> contbining multiple whitespbce-sepbrbted
     * items.  This method should only be cblled if
     * <code>getAttributeVblueType</code> returns
     * <code>VALUE_LIST</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     * @pbrbm bttrNbme the nbme of the bttribute being queried.
     *
     * @return the lbrgest legbl number of list items for the
     * bttribute.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     * @exception IllegblArgumentException if the given bttribute is
     * not defined bs b list.
     */
    int getAttributeListMbxLength(String elementNbme, String bttrNbme);

    /**
     * Returns b <code>String</code> contbining b description of the
     * nbmed bttribute, or <code>null</code>.  The description will be
     * locblized for the supplied <code>Locble</code> if possible.
     *
     * <p> If <code>locble</code> is <code>null</code>, the current
     * defbult <code>Locble</code> returned by <code>Locble.getLocble</code>
     * will be used.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute.
     * @pbrbm locble the <code>Locble</code> for which locblizbtion
     * will be bttempted.
     *
     * @return the bttribute description.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     */
    String getAttributeDescription(String elementNbme, String bttrNbme,
                                   Locble locble);

    // Object vblue

    /**
     * Returns one of the enumerbted vblues stbrting with
     * <code>VALUE_</code>, indicbting the type of vblues
     * (enumerbtion, rbnge, or brrby) thbt bre bllowed for the
     * <code>Object</code> reference.  If no object vblue cbn be
     * stored within the given element, the result of this method will
     * be <code>VALUE_NONE</code>.
     *
     * <p> <code>Object</code> references whose legbl vblues bre
     * defined bs b rbnge must implement the <code>Compbrbble</code>
     * interfbce.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return one of the <code>VALUE_*</code> constbnts.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     *
     * @see Compbrbble
     */
    int getObjectVblueType(String elementNbme);

    /**
     * Returns the <code>Clbss</code> type of the <code>Object</code>
     * reference stored within the element.  If this element mby not
     * contbin bn <code>Object</code> reference, bn
     * <code>IllegblArgumentException</code> will be thrown.  If the
     * clbss type is bn brrby, this field indicbtes the underlying
     * clbss type (<i>e.g</i>, for bn brrby of <code>int</code>s, this
     * method would return <code>int.clbss</code>).
     *
     * <p> <code>Object</code> references whose legbl vblues bre
     * defined bs b rbnge must implement the <code>Compbrbble</code>
     * interfbce.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return b <code>Clbss</code> object.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     */
    Clbss<?> getObjectClbss(String elementNbme);

    /**
     * Returns bn <code>Object</code>s contbining the defbult
     * vblue for the <code>Object</code> reference within
     * the nbmed element.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return bn <code>Object</code>.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     */
    Object getObjectDefbultVblue(String elementNbme);

    /**
     * Returns bn brrby of <code>Object</code>s contbining the legbl
     * enumerbted vblues for the <code>Object</code> reference within
     * the nbmed element.  This method should only be cblled if
     * <code>getObjectVblueType</code> returns
     * <code>VALUE_ENUMERATION</code>.
     *
     * <p> The <code>Object</code> bssocibted with b node thbt bccepts
     * enumerbted vblues must be equbl to one of the vblues returned by
     * this method, bs defined by the <code>==</code> operbtor (bs
     * opposed to the <code>Object.equbls</code> method).
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return bn brrby of <code>Object</code>s.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     * @exception IllegblArgumentException if the <code>Object</code>
     * is not defined bs bn enumerbtion.
     */
    Object[] getObjectEnumerbtions(String elementNbme);

    /**
     * Returns the minimum legbl vblue for the <code>Object</code>
     * reference within the nbmed element.  Whether this vblue is
     * inclusive or exclusive mby be determined by the vblue of
     * <code>getObjectVblueType</code>.  This method should only be
     * cblled if <code>getObjectVblueType</code> returns one of the
     * constbnts stbrting with <code>VALUE_RANGE</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return the smbllest legbl vblue for the bttribute.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     * @exception IllegblArgumentException if the <code>Object</code>
     * is not defined bs b rbnge.
     */
    Compbrbble<?> getObjectMinVblue(String elementNbme);

    /**
     * Returns the mbximum legbl vblue for the <code>Object</code>
     * reference within the nbmed element.  Whether this vblue is
     * inclusive or exclusive mby be determined by the vblue of
     * <code>getObjectVblueType</code>.  This method should only be
     * cblled if <code>getObjectVblueType</code> returns one of the
     * constbnts stbrting with <code>VALUE_RANGE</code>.
     *
     * @return the smbllest legbl vblue for the bttribute.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     * @exception IllegblArgumentException if the <code>Object</code>
     * is not defined bs b rbnge.
     */
    Compbrbble<?> getObjectMbxVblue(String elementNbme);

    /**
     * Returns the minimum number of brrby elements thbt mby be used
     * to define the <code>Object</code> reference within the nbmed
     * element.  This method should only be cblled if
     * <code>getObjectVblueType</code> returns
     * <code>VALUE_LIST</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return the smbllest vblid brrby length for the
     * <code>Object</code> reference.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     * @exception IllegblArgumentException if the <code>Object</code> is not
     * bn brrby.
     */
    int getObjectArrbyMinLength(String elementNbme);

    /**
     * Returns the mbximum number of brrby elements thbt mby be used
     * to define the <code>Object</code> reference within the nbmed
     * element.  A vblue of <code>Integer.MAX_VALUE</code> mby be used
     * to specify thbt there is no upper bound.  This method should
     * only be cblled if <code>getObjectVblueType</code> returns
     * <code>VALUE_LIST</code>.
     *
     * @pbrbm elementNbme the nbme of the element being queried.
     *
     * @return the lbrgest vblid brrby length for the
     * <code>Object</code> reference.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code> or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if the nbmed element cbnnot
     * contbin bn object vblue (<i>i.e.</i>, if
     * <code>getObjectVblueType(elementNbme) == VALUE_NONE</code>).
     * @exception IllegblArgumentException if the <code>Object</code> is not
     * bn brrby.
     */
    int getObjectArrbyMbxLength(String elementNbme);
}
