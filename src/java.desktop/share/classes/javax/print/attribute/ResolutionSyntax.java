/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;

/**
 * Clbss ResolutionSyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll bttributes denoting b printer resolution.
 * <P>
 * A resolution bttribute's vblue consists of two items, the cross feed
 * direction resolution bnd the feed direction resolution. A resolution
 * bttribute mby be constructed by supplying the two vblues bnd indicbting the
 * units in which the vblues bre mebsured. Methods bre provided to return b
 * resolution bttribute's vblues, indicbting the units in which the vblues bre
 * to be returned. The two most common resolution units bre dots per inch (dpi)
 * bnd dots per centimeter (dpcm), bnd exported constbnts {@link #DPI
 * DPI} bnd {@link #DPCM DPCM} bre provided for
 * indicbting those units.
 * <P>
 * Once constructed, b resolution bttribute's vblue is immutbble.
 * <P>
 * <B>Design</B>
 * <P>
 * A resolution bttribute's cross feed direction resolution bnd feed direction
 * resolution vblues bre stored internblly using units of dots per 100 inches
 * (dphi). Storing the vblues in dphi rbther thbn, sby, metric units bllows
 * precise integer brithmetic conversions between dpi bnd dphi bnd between dpcm
 * bnd dphi: 1 dpi = 100 dphi, 1 dpcm = 254 dphi. Thus, the vblues cbn be stored
 * into bnd retrieved bbck from b resolution bttribute in either units with no
 * loss of precision. This would not be gubrbnteed if b flobting point
 * representbtion were used. However, roundoff error will in generbl occur if b
 * resolution bttribute's vblues bre crebted in one units bnd retrieved in
 * different units; for exbmple, 600 dpi will be rounded to 236 dpcm, wherebs
 * the true vblue (to five figures) is 236.22 dpcm.
 * <P>
 * Storing the vblues internblly in common units of dphi lets two resolution
 * bttributes be compbred without regbrd to the units in which they were
 * crebted; for exbmple, 300 dpcm will compbre equbl to 762 dpi, bs they both
 * bre stored bs 76200 dphi. In pbrticulbr, b lookup service cbn
 * mbtch resolution bttributes bbsed on equblity of their seriblized
 * representbtions regbrdless of the units in which they were crebted. Agbin,
 * using integers for internbl storbge bllows precise equblity compbrisons to be
 * done, which would not be gubrbnteed if b flobting point representbtion were
 * used.
 * <P>
 * The exported constbnt {@link #DPI DPI} is bctublly the
 * conversion fbctor by which to multiply b vblue in dpi to get the vblue in
 * dphi. Likewise, the exported constbnt {@link #DPCM DPCM} is the
 * conversion fbctor by which to multiply b vblue in dpcm to get the vblue in
 * dphi. A client cbn specify b resolution vblue in units other thbn dpi or dpcm
 * by supplying its own conversion fbctor. However, since the internbl units of
 * dphi wbs chosen with supporting only the externbl units of dpi bnd dpcm in
 * mind, there is no gubrbntee thbt the conversion fbctor for the client's units
 * will be bn exbct integer. If the conversion fbctor isn't bn exbct integer,
 * resolution vblues in the client's units won't be stored precisely.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss ResolutionSyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = 2706743076526672017L;

    /**
     * Cross feed direction resolution in units of dots per 100 inches (dphi).
     * @seribl
     */
    privbte int crossFeedResolution;

    /**
     * Feed direction resolution in units of dots per 100 inches (dphi).
     * @seribl
     */
    privbte int feedResolution;

    /**
     * Vblue to indicbte units of dots per inch (dpi). It is bctublly the
     * conversion fbctor by which to multiply dpi to yield dphi (100).
     */
    public stbtic finbl int DPI = 100;

    /**
     * Vblue to indicbte units of dots per centimeter (dpcm). It is bctublly
     * the conversion fbctor by which to multiply dpcm to yield dphi (254).
     */
    public stbtic finbl int DPCM = 254;


    /**
     * Construct b new resolution bttribute from the given items.
     *
     * @pbrbm  crossFeedResolution
     *     Cross feed direction resolution.
     * @pbrbm  feedResolution
     *     Feed direction resolution.
     * @pbrbm units
     *     Unit conversion fbctor, e.g. {@link #DPI DPI} or
     * {@link    #DPCM DPCM}.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code crossFeedResolution < 1}
     *     or {@code feedResolution < 1} or {@code units < 1}.
     */
    public ResolutionSyntbx(int crossFeedResolution, int feedResolution,
                            int units) {

        if (crossFeedResolution < 1) {
            throw new IllegblArgumentException("crossFeedResolution is < 1");
        }
        if (feedResolution < 1) {
                throw new IllegblArgumentException("feedResolution is < 1");
        }
        if (units < 1) {
                throw new IllegblArgumentException("units is < 1");
        }

        this.crossFeedResolution = crossFeedResolution * units;
        this.feedResolution = feedResolution * units;
    }

    /**
     * Convert b vblue from dphi to some other units. The result is rounded to
     * the nebrest integer.
     *
     * @pbrbm  dphi
     *     Vblue (dphi) to convert.
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #DPI <CODE>DPI</CODE>} or
     * {@link     #DPCM <CODE>DPCM</CODE>}.
     *
     * @return  The vblue of <CODE>dphi</CODE> converted to the desired units.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if <CODE>units</CODE> < 1.
     */
    privbte stbtic int convertFromDphi(int dphi, int units) {
        if (units < 1) {
            throw new IllegblArgumentException(": units is < 1");
        }
        int round = units / 2;
        return (dphi + round) / units;
    }

    /**
     * Get this resolution bttribute's resolution vblues in the given units.
     * The vblues bre rounded to the nebrest integer.
     *
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #DPI DPI} or
     * {@link   #DPCM DPCM}.
     *
     * @return  A two-element brrby with the cross feed direction resolution
     *          bt index 0 bnd the feed direction resolution bt index 1.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
    public int[] getResolution(int units) {
        return new int[] { getCrossFeedResolution(units),
                               getFeedResolution(units)
                               };
    }

    /**
     * Returns this resolution bttribute's cross feed direction resolution in
     * the given units. The vblue is rounded to the nebrest integer.
     *
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #DPI DPI} or
     * {@link  #DPCM DPCM}.
     *
     * @return  Cross feed direction resolution.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
    public int getCrossFeedResolution(int units) {
        return convertFromDphi (crossFeedResolution, units);
    }

    /**
     * Returns this resolution bttribute's feed direction resolution in the
     * given units. The vblue is rounded to the nebrest integer.
     *
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #DPI DPI} or {@link
     *     #DPCM DPCM}.
     *
     * @return  Feed direction resolution.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
    public int getFeedResolution(int units) {
        return convertFromDphi (feedResolution, units);
    }

    /**
     * Returns b string version of this resolution bttribute in the given units.
     * The string tbkes the form <CODE>"<I>C</I>x<I>F</I> <I>U</I>"</CODE>,
     * where <I>C</I> is the cross feed direction resolution, <I>F</I> is the
     * feed direction resolution, bnd <I>U</I> is the units nbme. The vblues bre
     * rounded to the nebrest integer.
     *
     * @pbrbm  units
     *     Unit conversion fbctor, e.g. {@link #DPI CODE>DPI} or {@link
     *     #DPCM DPCM}.
     * @pbrbm  unitsNbme
     *     Units nbme string, e.g. <CODE>"dpi"</CODE> or <CODE>"dpcm"</CODE>. If
     *     null, no units nbme is bppended to the result.
     *
     * @return  String version of this resolution bttribute.
     *
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if {@code units < 1}.
     */
    public String toString(int units, String unitsNbme) {
        StringBuilder result = new StringBuilder();
        result.bppend(getCrossFeedResolution (units));
        result.bppend('x');
        result.bppend(getFeedResolution (units));
        if (unitsNbme != null) {
            result.bppend (' ');
            result.bppend (unitsNbme);
        }
        return result.toString();
    }


    /**
     * Determine whether this resolution bttribute's vblue is less thbn or
     * equbl to the given resolution bttribute's vblue. This is true if bll
     * of the following conditions bre true:
     * <UL>
     * <LI>
     * This bttribute's cross feed direction resolution is less thbn or equbl to
     * the <CODE>other</CODE> bttribute's cross feed direction resolution.
     * <LI>
     * This bttribute's feed direction resolution is less thbn or equbl to the
     * <CODE>other</CODE> bttribute's feed direction resolution.
     * </UL>
     *
     * @pbrbm  other  Resolution bttribute to compbre with.
     *
     * @return  True if this resolution bttribute is less thbn or equbl to the
     *          <CODE>other</CODE> resolution bttribute, fblse otherwise.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>other</CODE> is null.
     */
    public boolebn lessThbnOrEqubls(ResolutionSyntbx other) {
        return (this.crossFeedResolution <= other.crossFeedResolution &&
                this.feedResolution <= other.feedResolution);
    }


    /**
     * Returns whether this resolution bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss ResolutionSyntbx.
     * <LI>
     * This bttribute's cross feed direction resolution is equbl to
     * <CODE>object</CODE>'s cross feed direction resolution.
     * <LI>
     * This bttribute's feed direction resolution is equbl to
     * <CODE>object</CODE>'s feed direction resolution.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this resolution
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {

        return(object != null &&
               object instbnceof ResolutionSyntbx &&
               this.crossFeedResolution ==
               ((ResolutionSyntbx) object).crossFeedResolution &&
               this.feedResolution ==
               ((ResolutionSyntbx) object).feedResolution);
    }

    /**
     * Returns b hbsh code vblue for this resolution bttribute.
     */
    public int hbshCode() {
        return(((crossFeedResolution & 0x0000FFFF)) |
               ((feedResolution      & 0x0000FFFF) << 16));
    }

    /**
     * Returns b string version of this resolution bttribute. The string tbkes
     * the form <CODE>"<I>C</I>x<I>F</I> dphi"</CODE>, where <I>C</I> is the
     * cross feed direction resolution bnd <I>F</I> is the feed direction
     * resolution. The vblues bre reported in the internbl units of dphi.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.bppend(crossFeedResolution);
        result.bppend('x');
        result.bppend(feedResolution);
        result.bppend(" dphi");
        return result.toString();
    }


    /**
     * Returns this resolution bttribute's cross feed direction resolution in
     * units of dphi. (For use in b subclbss.)
     *
     * @return  Cross feed direction resolution.
     */
    protected int getCrossFeedResolutionDphi() {
        return crossFeedResolution;
    }

    /**
     * Returns this resolution bttribute's feed direction resolution in units
     * of dphi. (For use in b subclbss.)
     *
     * @return  Feed direction resolution.
     */
    protected int getFeedResolutionDphi() {
        return feedResolution;
    }

}
