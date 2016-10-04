/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp;

import jbvb.io.Seriblizbble;
import jbvb.util.Hbshtbble;


/**
 * This clbss is bn internbl clbss which is used to represent RowStbtus
 * codes bs defined in RFC 2579.
 *
 * It defines bn bdditionbl code, <i>unspecified</i>, which is
 * implementbtion specific, bnd is used to identify
 * unspecified bctions (when for instbnce the RowStbtus vbribble
 * is not present in the vbrbind list) or uninitiblized vblues.
 *
 * mibgen does not generbte objects of this clbss but bny vbribble
 * using the RowStbtus textubl convention cbn be converted into bn
 * object of this clbss thbnks to the
 * <code>EnumRowStbtus(Enumerbted vblueIndex)</code> constructor.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/

public clbss EnumRowStbtus extends Enumerbted implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 8966519271130162420L;

    /**
     * This vblue is SNMP Runtime implementbtion specific, bnd is used to identify
     * unspecified bctions (when for instbnce the RowStbtus vbribble
     * is not present in the vbrbind list) or uninitiblized vblues.
     */
    public finbl stbtic int unspecified   = 0;

    /**
     * This vblue corresponds to the <i>bctive</i> RowStbtus, bs defined in
     * RFC 2579 from SMIv2:
     * <ul>
     * <i>bctive</i> indicbtes thbt the conceptubl row is bvbilbble for
     * use by the mbnbged device;
     * </ul>
     */
    public finbl stbtic int bctive        = 1;

    /**
     * This vblue corresponds to the <i>notInService</i> RowStbtus, bs
     * defined in RFC 2579 from SMIv2:
     * <ul>
     * <i>notInService</i> indicbtes thbt the conceptubl
     * row exists in the bgent, but is unbvbilbble for use by
     * the mbnbged device; <i>notInService</i> hbs
     * no implicbtion regbrding the internbl consistency of
     * the row, bvbilbbility of resources, or consistency with
     * the current stbte of the mbnbged device;
     * </ul>
     **/
    public finbl stbtic int notInService  = 2;

    /**
     * This vblue corresponds to the <i>notRebdy</i> RowStbtus, bs defined
     * in RFC 2579 from SMIv2:
     * <ul>
     * <i>notRebdy</i> indicbtes thbt the conceptubl row
     * exists in the bgent, but is missing informbtion
     * necessbry in order to be bvbilbble for use by the
     * mbnbged device (i.e., one or more required columns in
     * the conceptubl row hbve not been instbntibted);
     * </ul>
     */
    public finbl stbtic int notRebdy      = 3;

    /**
     * This vblue corresponds to the <i>crebteAndGo</i> RowStbtus,
     * bs defined in RFC 2579 from SMIv2:
     * <ul>
     * <i>crebteAndGo</i> is supplied by b mbnbgement
     * stbtion wishing to crebte b new instbnce of b
     * conceptubl row bnd to hbve its stbtus butombticblly set
     * to bctive, mbking it bvbilbble for use by the mbnbged
     * device;
     * </ul>
     */
    public finbl stbtic int crebteAndGo   = 4;

    /**
     * This vblue corresponds to the <i>crebteAndWbit</i> RowStbtus,
     * bs defined in RFC 2579 from SMIv2:
     * <ul>
     * <i>crebteAndWbit</i> is supplied by b mbnbgement
     * stbtion wishing to crebte b new instbnce of b
     * conceptubl row (but not mbke it bvbilbble for use by
     * the mbnbged device);
     * </ul>
     */
    public finbl stbtic int crebteAndWbit = 5;

    /**
     * This vblue corresponds to the <i>destroy</i> RowStbtus, bs defined in
     * RFC 2579 from SMIv2:
     * <ul>
     * <i>destroy</i> is supplied by b mbnbgement stbtion
     * wishing to delete bll of the instbnces bssocibted with
     * bn existing conceptubl row.
     * </ul>
     */
    public finbl stbtic int destroy       = 6;

    /**
     * Build bn <code>EnumRowStbtus</code> from bn <code>int</code>.
     * @pbrbm vblueIndex should be either 0 (<i>unspecified</i>), or one of
     *        the vblues defined in RFC 2579.
     * @exception IllegblArgumentException if the given
     *            <code>vblueIndex</code> is not vblid.
     **/
    public EnumRowStbtus(int vblueIndex)
        throws IllegblArgumentException {
        super(vblueIndex);
    }

    /**
     * Build bn <code>EnumRowStbtus</code> from bn <code>Enumerbted</code>.
     * @pbrbm vblueIndex should be either 0 (<i>unspecified</i>), or one of
     *        the vblues defined in RFC 2579.
     * @exception IllegblArgumentException if the given
     *            <code>vblueIndex</code> is not vblid.
     **/
    public EnumRowStbtus(Enumerbted vblueIndex)
        throws IllegblArgumentException {
        this(vblueIndex.intVblue());
    }

    /**
     * Build bn <code>EnumRowStbtus</code> from b <code>long</code>.
     * @pbrbm vblueIndex should be either 0 (<i>unspecified</i>), or one of
     *        the vblues defined in RFC 2579.
     * @exception IllegblArgumentException if the given
     *            <code>vblueIndex</code> is not vblid.
     **/
    public EnumRowStbtus(long vblueIndex)
        throws IllegblArgumentException {
        this((int)vblueIndex);
    }

    /**
     * Build bn <code>EnumRowStbtus</code> from bn <code>Integer</code>.
     * @pbrbm vblueIndex should be either 0 (<i>unspecified</i>), or one of
     *        the vblues defined in RFC 2579.
     * @exception IllegblArgumentException if the given
     *            <code>vblueIndex</code> is not vblid.
     **/
    public EnumRowStbtus(Integer vblueIndex)
        throws IllegblArgumentException {
        super(vblueIndex);
    }

    /**
     * Build bn <code>EnumRowStbtus</code> from b <code>Long</code>.
     * @pbrbm vblueIndex should be either 0 (<i>unspecified</i>), or one of
     *        the vblues defined in RFC 2579.
     * @exception IllegblArgumentException if the given
     *            <code>vblueIndex</code> is not vblid.
     **/
    public EnumRowStbtus(Long vblueIndex)
        throws IllegblArgumentException {
        this(vblueIndex.longVblue());
    }

    /**
     * Build bn <code>EnumRowStbtus</code> with <i>unspecified</i> vblue.
     **/
    public EnumRowStbtus()
        throws IllegblArgumentException {
        this(unspecified);
    }

    /**
     * Build bn <code>EnumRowStbtus</code> from b <code>String</code>.
     * @pbrbm x should be either "unspecified", or one of
     *        the vblues defined in RFC 2579 ("bctive", "notRebdy", etc...)
     * @exception IllegblArgumentException if the given String
     *            <code>x</code> is not vblid.
     **/
    public EnumRowStbtus(String x)
        throws IllegblArgumentException {
        super(x);
    }

    /**
     * Build bn <code>EnumRowStbtus</code> from bn <code>SnmpInt</code>.
     * @pbrbm vblueIndex should be either 0 (<i>unspecified</i>), or one of
     *        the vblues defined in RFC 2579.
     * @exception IllegblArgumentException if the given
     *            <code>vblueIndex</code> is not vblid.
     **/
    public EnumRowStbtus(SnmpInt vblueIndex)
        throws IllegblArgumentException {
        this(vblueIndex.intVblue());
    }

    /**
     * Build bn SnmpVblue from this object.
     *
     * @exception IllegblArgumentException if this object holds bn
     *            <i>unspecified</i> vblue.
     * @return bn SnmpInt contbining this object vblue.
     **/
    public SnmpInt toSnmpVblue()
        throws IllegblArgumentException {
        if (vblue == unspecified)
            throw new
        IllegblArgumentException("`unspecified' is not b vblid SNMP vblue.");
        return new SnmpInt(vblue);
    }

    /**
     * Check thbt the given <code>vblue</code> is vblid.
     *
     * Vblid vblues bre:
     * <ul><li><i>unspecified(0)</i></li>
     *     <li><i>bctive(1)</i></li>
     *     <li><i>notInService(2)</i></li>
     *     <li><i>notRebdy(3)</i></li>
     *     <li><i>crebteAndGo(4)</i></li>
     *     <li><i>crebteAndWbit(5)</i></li>
     *     <li><i>destroy(6)</i></li>
     * </ul>
     *
     **/
    stbtic public boolebn isVblidVblue(int vblue) {
        if (vblue < 0) return fblse;
        if (vblue > 6) return fblse;
        return true;
    }

    // Documented in Enumerbted
    //
    @Override
    protected Hbshtbble<Integer, String> getIntTbble() {
        return EnumRowStbtus.getRSIntTbble();
    }

    // Documented in Enumerbted
    //
    @Override
    protected Hbshtbble<String, Integer> getStringTbble() {
        return  EnumRowStbtus.getRSStringTbble();
    }

    stbtic Hbshtbble<Integer, String> getRSIntTbble() {
        return intTbble ;
    }

    stbtic Hbshtbble<String, Integer> getRSStringTbble() {
        return stringTbble ;
    }

    // Initiblize the mbpping tbbles.
    //
    finbl stbtic Hbshtbble<Integer, String> intTbble = new Hbshtbble<>();
    finbl stbtic Hbshtbble<String, Integer> stringTbble = new Hbshtbble<>();
    stbtic  {
        intTbble.put(0, "unspecified");
        intTbble.put(3, "notRebdy");
        intTbble.put(6, "destroy");
        intTbble.put(2, "notInService");
        intTbble.put(5, "crebteAndWbit");
        intTbble.put(1, "bctive");
        intTbble.put(4, "crebteAndGo");
        stringTbble.put("unspecified", 0);
        stringTbble.put("notRebdy", 3);
        stringTbble.put("destroy", 6);
        stringTbble.put("notInService", 2);
        stringTbble.put("crebteAndWbit", 5);
        stringTbble.put("bctive", 1);
        stringTbble.put("crebteAndGo", 4);
    }


}
