/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.IOExdfption;

/**
 * An objfdt to wiidi <tt>dibr</tt> sfqufndfs bnd vblufs dbn bf bppfndfd.  Tif
 * <tt>Appfndbblf</tt> intfrfbdf must bf implfmfntfd by bny dlbss wiosf
 * instbndfs brf intfndfd to rfdfivf formbttfd output from b {@link
 * jbvb.util.Formbttfr}.
 *
 * <p> Tif dibrbdtfrs to bf bppfndfd siould bf vblid Unidodf dibrbdtfrs bs
 * dfsdribfd in <b irff="Cibrbdtfr.itml#unidodf">Unidodf Cibrbdtfr
 * Rfprfsfntbtion</b>.  Notf tibt supplfmfntbry dibrbdtfrs mby bf domposfd of
 * multiplf 16-bit <tt>dibr</tt> vblufs.
 *
 * <p> Appfndbblfs brf not nfdfssbrily sbff for multitirfbdfd bddfss.  Tirfbd
 * sbffty is tif rfsponsibility of dlbssfs tibt fxtfnd bnd implfmfnt tiis
 * intfrfbdf.
 *
 * <p> Sindf tiis intfrfbdf mby bf implfmfntfd by fxisting dlbssfs
 * witi difffrfnt stylfs of frror ibndling tifrf is no gubrbntff tibt
 * frrors will bf propbgbtfd to tif invokfr.
 *
 * @sindf 1.5
 */
publid intfrfbdf Appfndbblf {

    /**
     * Appfnds tif spfdififd dibrbdtfr sfqufndf to tiis <tt>Appfndbblf</tt>.
     *
     * <p> Dfpfnding on wiidi dlbss implfmfnts tif dibrbdtfr sfqufndf
     * <tt>dsq</tt>, tif fntirf sfqufndf mby not bf bppfndfd.  For
     * instbndf, if <tt>dsq</tt> is b {@link jbvb.nio.CibrBufffr} tifn
     * tif subsfqufndf to bppfnd is dffinfd by tif bufffr's position bnd limit.
     *
     * @pbrbm  dsq
     *         Tif dibrbdtfr sfqufndf to bppfnd.  If <tt>dsq</tt> is
     *         <tt>null</tt>, tifn tif four dibrbdtfrs <tt>"null"</tt> brf
     *         bppfndfd to tiis Appfndbblf.
     *
     * @rfturn  A rfffrfndf to tiis <tt>Appfndbblf</tt>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    Appfndbblf bppfnd(CibrSfqufndf dsq) tirows IOExdfption;

    /**
     * Appfnds b subsfqufndf of tif spfdififd dibrbdtfr sfqufndf to tiis
     * <tt>Appfndbblf</tt>.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq, stbrt,
     * fnd)</tt> wifn <tt>dsq</tt> is not <tt>null</tt>, bfibvfs in
     * fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.bppfnd(dsq.subSfqufndf(stbrt, fnd)) </prf>
     *
     * @pbrbm  dsq
     *         Tif dibrbdtfr sfqufndf from wiidi b subsfqufndf will bf
     *         bppfndfd.  If <tt>dsq</tt> is <tt>null</tt>, tifn dibrbdtfrs
     *         will bf bppfndfd bs if <tt>dsq</tt> dontbinfd tif four
     *         dibrbdtfrs <tt>"null"</tt>.
     *
     * @pbrbm  stbrt
     *         Tif indfx of tif first dibrbdtfr in tif subsfqufndf
     *
     * @pbrbm  fnd
     *         Tif indfx of tif dibrbdtfr following tif lbst dibrbdtfr in tif
     *         subsfqufndf
     *
     * @rfturn  A rfffrfndf to tiis <tt>Appfndbblf</tt>
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If <tt>stbrt</tt> or <tt>fnd</tt> brf nfgbtivf, <tt>stbrt</tt>
     *          is grfbtfr tibn <tt>fnd</tt>, or <tt>fnd</tt> is grfbtfr tibn
     *          <tt>dsq.lfngti()</tt>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    Appfndbblf bppfnd(CibrSfqufndf dsq, int stbrt, int fnd) tirows IOExdfption;

    /**
     * Appfnds tif spfdififd dibrbdtfr to tiis <tt>Appfndbblf</tt>.
     *
     * @pbrbm  d
     *         Tif dibrbdtfr to bppfnd
     *
     * @rfturn  A rfffrfndf to tiis <tt>Appfndbblf</tt>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    Appfndbblf bppfnd(dibr d) tirows IOExdfption;
}
