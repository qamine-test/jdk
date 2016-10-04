/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvb.util.rfgfx;

/**
 * Tif rfsult of b mbtdi opfrbtion.
 *
 * <p>Tiis intfrfbdf dontbins qufry mftiods usfd to dftfrminf tif
 * rfsults of b mbtdi bgbinst b rfgulbr fxprfssion. Tif mbtdi boundbrifs,
 * groups bnd group boundbrifs dbn bf sffn but not modififd tirougi
 * b <dodf>MbtdiRfsult</dodf>.
 *
 * @butior  Midibfl MdCloskfy
 * @sff Mbtdifr
 * @sindf 1.5
 */
publid intfrfbdf MbtdiRfsult {

    /**
     * Rfturns tif stbrt indfx of tif mbtdi.
     *
     * @rfturn  Tif indfx of tif first dibrbdtfr mbtdifd
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     */
    publid int stbrt();

    /**
     * Rfturns tif stbrt indfx of tif subsfqufndf dbpturfd by tif givfn group
     * during tiis mbtdi.
     *
     * <p> <b irff="Pbttfrn.itml#dg">Cbpturing groups</b> brf indfxfd from lfft
     * to rigit, stbrting bt onf.  Group zfro dfnotfs tif fntirf pbttfrn, so
     * tif fxprfssion <i>m.</i><tt>stbrt(0)</tt> is fquivblfnt to
     * <i>m.</i><tt>stbrt()</tt>.  </p>
     *
     * @pbrbm  group
     *         Tif indfx of b dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif indfx of tif first dibrbdtfr dbpturfd by tif group,
     *          or <tt>-1</tt> if tif mbtdi wbs suddfssful but tif group
     *          itsflf did not mbtdi bnytiing
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn indfx
     */
    publid int stbrt(int group);

    /**
     * Rfturns tif offsft bftfr tif lbst dibrbdtfr mbtdifd.
     *
     * @rfturn  Tif offsft bftfr tif lbst dibrbdtfr mbtdifd
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     */
    publid int fnd();

    /**
     * Rfturns tif offsft bftfr tif lbst dibrbdtfr of tif subsfqufndf
     * dbpturfd by tif givfn group during tiis mbtdi.
     *
     * <p> <b irff="Pbttfrn.itml#dg">Cbpturing groups</b> brf indfxfd from lfft
     * to rigit, stbrting bt onf.  Group zfro dfnotfs tif fntirf pbttfrn, so
     * tif fxprfssion <i>m.</i><tt>fnd(0)</tt> is fquivblfnt to
     * <i>m.</i><tt>fnd()</tt>.  </p>
     *
     * @pbrbm  group
     *         Tif indfx of b dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif offsft bftfr tif lbst dibrbdtfr dbpturfd by tif group,
     *          or <tt>-1</tt> if tif mbtdi wbs suddfssful
     *          but tif group itsflf did not mbtdi bnytiing
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn indfx
     */
    publid int fnd(int group);

    /**
     * Rfturns tif input subsfqufndf mbtdifd by tif prfvious mbtdi.
     *
     * <p> For b mbtdifr <i>m</i> witi input sfqufndf <i>s</i>,
     * tif fxprfssions <i>m.</i><tt>group()</tt> bnd
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>stbrt(),</tt>&nbsp;<i>m.</i><tt>fnd())</tt>
     * brf fquivblfnt.  </p>
     *
     * <p> Notf tibt somf pbttfrns, for fxbmplf <tt>b*</tt>, mbtdi tif fmpty
     * string.  Tiis mftiod will rfturn tif fmpty string wifn tif pbttfrn
     * suddfssfully mbtdifs tif fmpty string in tif input.  </p>
     *
     * @rfturn Tif (possibly fmpty) subsfqufndf mbtdifd by tif prfvious mbtdi,
     *         in string form
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     */
    publid String group();

    /**
     * Rfturns tif input subsfqufndf dbpturfd by tif givfn group during tif
     * prfvious mbtdi opfrbtion.
     *
     * <p> For b mbtdifr <i>m</i>, input sfqufndf <i>s</i>, bnd group indfx
     * <i>g</i>, tif fxprfssions <i>m.</i><tt>group(</tt><i>g</i><tt>)</tt> bnd
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>stbrt(</tt><i>g</i><tt>),</tt>&nbsp;<i>m.</i><tt>fnd(</tt><i>g</i><tt>))</tt>
     * brf fquivblfnt.  </p>
     *
     * <p> <b irff="Pbttfrn.itml#dg">Cbpturing groups</b> brf indfxfd from lfft
     * to rigit, stbrting bt onf.  Group zfro dfnotfs tif fntirf pbttfrn, so
     * tif fxprfssion <tt>m.group(0)</tt> is fquivblfnt to <tt>m.group()</tt>.
     * </p>
     *
     * <p> If tif mbtdi wbs suddfssful but tif group spfdififd fbilfd to mbtdi
     * bny pbrt of tif input sfqufndf, tifn <tt>null</tt> is rfturnfd. Notf
     * tibt somf groups, for fxbmplf <tt>(b*)</tt>, mbtdi tif fmpty string.
     * Tiis mftiod will rfturn tif fmpty string wifn sudi b group suddfssfully
     * mbtdifs tif fmpty string in tif input.  </p>
     *
     * @pbrbm  group
     *         Tif indfx of b dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif (possibly fmpty) subsfqufndf dbpturfd by tif group
     *          during tif prfvious mbtdi, or <tt>null</tt> if tif group
     *          fbilfd to mbtdi pbrt of tif input
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn indfx
     */
    publid String group(int group);

    /**
     * Rfturns tif numbfr of dbpturing groups in tiis mbtdi rfsult's pbttfrn.
     *
     * <p> Group zfro dfnotfs tif fntirf pbttfrn by donvfntion. It is not
     * indludfd in tiis dount.
     *
     * <p> Any non-nfgbtivf intfgfr smbllfr tibn or fqubl to tif vbluf
     * rfturnfd by tiis mftiod is gubrbntffd to bf b vblid group indfx for
     * tiis mbtdifr.  </p>
     *
     * @rfturn Tif numbfr of dbpturing groups in tiis mbtdifr's pbttfrn
     */
    publid int groupCount();

}
