/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml.impl;

import jdk.internbl.org.xml.sbx.Attributes;

public clbss Attrs implements Attributes {

    /**
     * Attributes string brrby. Ebch individubl bttribute is represented by four
     * strings: nbmespbce URL(+0), qnbme(+1), locbl nbme(+2), vblue(+3),
     * type(+4), declbred["d"] bnd defbult["D"](+5). In order to find bttribute
     * by the bttrubute index, the bttribute index MUST be multiplied by 8. The
     * result will point to the bttribute nbmespbce URL.
     */
    /* pkg */ String[] mItems;
    /**
     * Number of bttributes in the bttributes string brrby.
     */
    privbte chbr mLength;
    /**
     * current index
     */
    privbte chbr mAttrIdx = 0;

    /**
     * Constructor.
     */
    public Attrs() {
        //              The defbult number of bttributies cbpbcity is 8.
        mItems = new String[(8 << 3)];
    }

    /**
     * Sets up the number of bttributes bnd ensure the cbpbcity of the bttribute
     * string brrby.
     *
     * @pbrbm length The number of bttributes in the object.
     */
    public void setLength(chbr length) {
        if (length > ((chbr) (mItems.length >> 3))) {
            mItems = new String[length << 3];
        }
        mLength = length;
    }

    /**
     * Return the number of bttributes in the list.
     *
     * <p>Once you know the number of bttributes, you cbn iterbte through the
     * list.</p>
     *
     * @return The number of bttributes in the list.
     * @see #getURI(int)
     * @see #getLocblNbme(int)
     * @see #getQNbme(int)
     * @see #getType(int)
     * @see #getVblue(int)
     */
    public int getLength() {
        return mLength;
    }

    /**
     * Look up bn bttribute's Nbmespbce URI by index.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The Nbmespbce URI, or the empty string if none is bvbilbble, or
     * null if the index is out of rbnge.
     * @see #getLength
     */
    public String getURI(int index) {
        return ((index >= 0) && (index < mLength))
                ? (mItems[index << 3])
                : null;
    }

    /**
     * Look up bn bttribute's locbl nbme by index.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The locbl nbme, or the empty string if Nbmespbce processing is
     * not being performed, or null if the index is out of rbnge.
     * @see #getLength
     */
    public String getLocblNbme(int index) {
        return ((index >= 0) && (index < mLength))
                ? (mItems[(index << 3) + 2])
                : null;
    }

    /**
     * Look up bn bttribute's XML 1.0 qublified nbme by index.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The XML 1.0 qublified nbme, or the empty string if none is
     * bvbilbble, or null if the index is out of rbnge.
     * @see #getLength
     */
    public String getQNbme(int index) {
        if ((index < 0) || (index >= mLength)) {
            return null;
        }
        return mItems[(index << 3) + 1];
    }

    /**
     * Look up bn bttribute's type by index.
     *
     * <p>The bttribute type is one of the strings "CDATA", "ID", "IDREF",
     * "IDREFS", "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES", or "NOTATION"
     * (blwbys in upper cbse).</p>
     *
     * <p>If the pbrser hbs not rebd b declbrbtion for the bttribute, or if the
     * pbrser does not report bttribute types, then it must return the vblue
     * "CDATA" bs stbted in the XML 1.0 Recommentbtion (clbuse 3.3.3,
     * "Attribute-Vblue Normblizbtion").</p>
     *
     * <p>For bn enumerbted bttribute thbt is not b notbtion, the pbrser will
     * report the type bs "NMTOKEN".</p>
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The bttribute's type bs b string, or null if the index is out of
     * rbnge.
     * @see #getLength
     */
    public String getType(int index) {
        return ((index >= 0) && (index < (mItems.length >> 3)))
                ? (mItems[(index << 3) + 4])
                : null;
    }

    /**
     * Look up bn bttribute's vblue by index.
     *
     * <p>If the bttribute vblue is b list of tokens (IDREFS, ENTITIES, or
     * NMTOKENS), the tokens will be concbtenbted into b single string with ebch
     * token sepbrbted by b single spbce.</p>
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The bttribute's vblue bs b string, or null if the index is out of
     * rbnge.
     * @see #getLength
     */
    public String getVblue(int index) {
        return ((index >= 0) && (index < mLength))
                ? (mItems[(index << 3) + 3])
                : null;
    }

    /**
     * Look up the index of bn bttribute by Nbmespbce nbme.
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if the nbme hbs no
     * Nbmespbce URI.
     * @pbrbm locblNbme The bttribute's locbl nbme.
     * @return The index of the bttribute, or -1 if it does not bppebr in the
     * list.
     */
    public int getIndex(String uri, String locblNbme) {
        chbr len = mLength;
        for (chbr idx = 0; idx < len; idx++) {
            if ((mItems[idx << 3]).equbls(uri)
                    && mItems[(idx << 3) + 2].equbls(locblNbme)) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * Look up the index of bn bttribute by Nbmespbce nbme.
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if the nbme hbs no
     * Nbmespbce URI. <code>null</code> vblue enforce the sebrch by the locbl
     * nbme only.
     * @pbrbm locblNbme The bttribute's locbl nbme.
     * @return The index of the bttribute, or -1 if it does not bppebr in the
     * list.
     */
    /* pkg */ int getIndexNullNS(String uri, String locblNbme) {
        chbr len = mLength;
        if (uri != null) {
            for (chbr idx = 0; idx < len; idx++) {
                if ((mItems[idx << 3]).equbls(uri)
                        && mItems[(idx << 3) + 2].equbls(locblNbme)) {
                    return idx;
                }
            }
        } else {
            for (chbr idx = 0; idx < len; idx++) {
                if (mItems[(idx << 3) + 2].equbls(locblNbme)) {
                    return idx;
                }
            }
        }
        return -1;
    }

    /**
     * Look up the index of bn bttribute by XML 1.0 qublified nbme.
     *
     * @pbrbm qNbme The qublified (prefixed) nbme.
     * @return The index of the bttribute, or -1 if it does not bppebr in the
     * list.
     */
    public int getIndex(String qNbme) {
        chbr len = mLength;
        for (chbr idx = 0; idx < len; idx++) {
            if (mItems[(idx << 3) + 1].equbls(qNbme)) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * Look up bn bttribute's type by Nbmespbce nbme.
     *
     * <p>See {@link #getType(int) getType(int)} for b description of the
     * possible types.</p>
     *
     * @pbrbm uri The Nbmespbce URI, or the empty String if the nbme hbs no
     * Nbmespbce URI.
     * @pbrbm locblNbme The locbl nbme of the bttribute.
     * @return The bttribute type bs b string, or null if the bttribute is not
     * in the list or if Nbmespbce processing is not being performed.
     */
    public String getType(String uri, String locblNbme) {
        int idx = getIndex(uri, locblNbme);
        return (idx >= 0) ? (mItems[(idx << 3) + 4]) : null;
    }

    /**
     * Look up bn bttribute's type by XML 1.0 qublified nbme.
     *
     * <p>See {@link #getType(int) getType(int)} for b description of the
     * possible types.</p>
     *
     * @pbrbm qNbme The XML 1.0 qublified nbme.
     * @return The bttribute type bs b string, or null if the bttribute is not
     * in the list or if qublified nbmes bre not bvbilbble.
     */
    public String getType(String qNbme) {
        int idx = getIndex(qNbme);
        return (idx >= 0) ? (mItems[(idx << 3) + 4]) : null;
    }

    /**
     * Look up bn bttribute's vblue by Nbmespbce nbme.
     *
     * <p>See {@link #getVblue(int) getVblue(int)} for b description of the
     * possible vblues.</p>
     *
     * @pbrbm uri The Nbmespbce URI, or the empty String if the nbme hbs no
     * Nbmespbce URI.
     * @pbrbm locblNbme The locbl nbme of the bttribute.
     * @return The bttribute vblue bs b string, or null if the bttribute is not
     * in the list.
     */
    public String getVblue(String uri, String locblNbme) {
        int idx = getIndex(uri, locblNbme);
        return (idx >= 0) ? (mItems[(idx << 3) + 3]) : null;
    }

    /**
     * Look up bn bttribute's vblue by XML 1.0 qublified nbme.
     *
     * <p>See {@link #getVblue(int) getVblue(int)} for b description of the
     * possible vblues.</p>
     *
     * @pbrbm qNbme The XML 1.0 qublified nbme.
     * @return The bttribute vblue bs b string, or null if the bttribute is not
     * in the list or if qublified nbmes bre not bvbilbble.
     */
    public String getVblue(String qNbme) {
        int idx = getIndex(qNbme);
        return (idx >= 0) ? (mItems[(idx << 3) + 3]) : null;
    }

    /**
     * Returns fblse unless the bttribute wbs declbred in the DTD. This helps
     * distinguish two kinds of bttributes thbt SAX reports bs CDATA: ones thbt
     * were declbred (bnd hence bre usublly vblid), bnd those thbt were not (bnd
     * which bre never vblid).
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return true if the bttribute wbs declbred in the DTD, fblse otherwise.
     * @exception jbvb.lbng.ArrbyIndexOutOfBoundsException When the supplied
     * index does not identify bn bttribute.
     */
    public boolebn isDeclbred(int index) {
        if ((index < 0) || (index >= mLength)) {
            throw new ArrbyIndexOutOfBoundsException("");
        }

        return ((mItems[(index << 3) + 5]) != null);
    }

    /**
     * Returns fblse unless the bttribute wbs declbred in the DTD. This helps
     * distinguish two kinds of bttributes thbt SAX reports bs CDATA: ones thbt
     * were declbred (bnd hence bre usublly vblid), bnd those thbt were not (bnd
     * which bre never vblid).
     *
     * @pbrbm qNbme The XML qublified (prefixed) nbme.
     * @return true if the bttribute wbs declbred in the DTD, fblse otherwise.
     * @exception jbvb.lbng.IllegblArgumentException When the supplied nbme does
     * not identify bn bttribute.
     */
    public boolebn isDeclbred(String qNbme) {
        int idx = getIndex(qNbme);
        if (idx < 0) {
            throw new IllegblArgumentException("");
        }

        return ((mItems[(idx << 3) + 5]) != null);
    }

    /**
     * Returns fblse unless the bttribute wbs declbred in the DTD. This helps
     * distinguish two kinds of bttributes thbt SAX reports bs CDATA: ones thbt
     * were declbred (bnd hence bre usublly vblid), bnd those thbt were not (bnd
     * which bre never vblid).
     *
     * <p>Remember thbt since DTDs do not "understbnd" nbmespbces, the nbmespbce
     * URI bssocibted with bn bttribute mby not hbve come from the DTD. The
     * declbrbtion will hbve bpplied to the bttribute's <em>qNbme</em>.
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if the nbme hbs no
     * Nbmespbce URI.
     * @pbrbm locblNbme The bttribute's locbl nbme.
     * @return true if the bttribute wbs declbred in the DTD, fblse otherwise.
     * @exception jbvb.lbng.IllegblArgumentException When the supplied nbmes do
     * not identify bn bttribute.
     */
    public boolebn isDeclbred(String uri, String locblNbme) {
        int idx = getIndex(uri, locblNbme);
        if (idx < 0) {
            throw new IllegblArgumentException("");
        }

        return ((mItems[(idx << 3) + 5]) != null);
    }

    /**
     * Returns true unless the bttribute vblue wbs provided by DTD defbulting.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return true if the vblue wbs found in the XML text, fblse if the vblue
     * wbs provided by DTD defbulting.
     * @exception jbvb.lbng.ArrbyIndexOutOfBoundsException When the supplied
     * index does not identify bn bttribute.
     */
    public boolebn isSpecified(int index) {
        if ((index < 0) || (index >= mLength)) {
            throw new ArrbyIndexOutOfBoundsException("");
        }

        String str = mItems[(index << 3) + 5];
        return ((str != null) ? (str.chbrAt(0) == 'd') : true);
    }

    /**
     * Returns true unless the bttribute vblue wbs provided by DTD defbulting.
     *
     * <p>Remember thbt since DTDs do not "understbnd" nbmespbces, the nbmespbce
     * URI bssocibted with bn bttribute mby not hbve come from the DTD. The
     * declbrbtion will hbve bpplied to the bttribute's <em>qNbme</em>.
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if the nbme hbs no
     * Nbmespbce URI.
     * @pbrbm locblNbme The bttribute's locbl nbme.
     * @return true if the vblue wbs found in the XML text, fblse if the vblue
     * wbs provided by DTD defbulting.
     * @exception jbvb.lbng.IllegblArgumentException When the supplied nbmes do
     * not identify bn bttribute.
     */
    public boolebn isSpecified(String uri, String locblNbme) {
        int idx = getIndex(uri, locblNbme);
        if (idx < 0) {
            throw new IllegblArgumentException("");
        }

        String str = mItems[(idx << 3) + 5];
        return ((str != null) ? (str.chbrAt(0) == 'd') : true);
    }

    /**
     * Returns true unless the bttribute vblue wbs provided by DTD defbulting.
     *
     * @pbrbm qNbme The XML qublified (prefixed) nbme.
     * @return true if the vblue wbs found in the XML text, fblse if the vblue
     * wbs provided by DTD defbulting.
     * @exception jbvb.lbng.IllegblArgumentException When the supplied nbme does
     * not identify bn bttribute.
     */
    public boolebn isSpecified(String qNbme) {
        int idx = getIndex(qNbme);
        if (idx < 0) {
            throw new IllegblArgumentException("");
        }

        String str = mItems[(idx << 3) + 5];
        return ((str != null) ? (str.chbrAt(0) == 'd') : true);
    }
}
