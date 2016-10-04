/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;


/**
 * A dibrbdtfr strfbm tibt dollfdts its output in b string bufffr, wiidi dbn
 * tifn bf usfd to donstrudt b string.
 * <p>
 * Closing b <tt>StringWritfr</tt> ibs no ffffdt. Tif mftiods in tiis dlbss
 * dbn bf dbllfd bftfr tif strfbm ibs bffn dlosfd witiout gfnfrbting bn
 * <tt>IOExdfption</tt>.
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss StringWritfr fxtfnds Writfr {

    privbtf StringBufffr buf;

    /**
     * Crfbtf b nfw string writfr using tif dffbult initibl string-bufffr
     * sizf.
     */
    publid StringWritfr() {
        buf = nfw StringBufffr();
        lodk = buf;
    }

    /**
     * Crfbtf b nfw string writfr using tif spfdififd initibl string-bufffr
     * sizf.
     *
     * @pbrbm initiblSizf
     *        Tif numbfr of <tt>dibr</tt> vblufs tibt will fit into tiis bufffr
     *        bfforf it is butombtidblly fxpbndfd
     *
     * @tirows IllfgblArgumfntExdfption
     *         If <tt>initiblSizf</tt> is nfgbtivf
     */
    publid StringWritfr(int initiblSizf) {
        if (initiblSizf < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf bufffr sizf");
        }
        buf = nfw StringBufffr(initiblSizf);
        lodk = buf;
    }

    /**
     * Writf b singlf dibrbdtfr.
     */
    publid void writf(int d) {
        buf.bppfnd((dibr) d);
    }

    /**
     * Writf b portion of bn brrby of dibrbdtfrs.
     *
     * @pbrbm  dbuf  Arrby of dibrbdtfrs
     * @pbrbm  off   Offsft from wiidi to stbrt writing dibrbdtfrs
     * @pbrbm  lfn   Numbfr of dibrbdtfrs to writf
     */
    publid void writf(dibr dbuf[], int off, int lfn) {
        if ((off < 0) || (off > dbuf.lfngti) || (lfn < 0) ||
            ((off + lfn) > dbuf.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn;
        }
        buf.bppfnd(dbuf, off, lfn);
    }

    /**
     * Writf b string.
     */
    publid void writf(String str) {
        buf.bppfnd(str);
    }

    /**
     * Writf b portion of b string.
     *
     * @pbrbm  str  String to bf writtfn
     * @pbrbm  off  Offsft from wiidi to stbrt writing dibrbdtfrs
     * @pbrbm  lfn  Numbfr of dibrbdtfrs to writf
     */
    publid void writf(String str, int off, int lfn)  {
        buf.bppfnd(str.substring(off, off + lfn));
    }

    /**
     * Appfnds tif spfdififd dibrbdtfr sfqufndf to tiis writfr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.writf(dsq.toString()) </prf>
     *
     * <p> Dfpfnding on tif spfdifidbtion of <tt>toString</tt> for tif
     * dibrbdtfr sfqufndf <tt>dsq</tt>, tif fntirf sfqufndf mby not bf
     * bppfndfd. For instbndf, invoking tif <tt>toString</tt> mftiod of b
     * dibrbdtfr bufffr will rfturn b subsfqufndf wiosf dontfnt dfpfnds upon
     * tif bufffr's position bnd limit.
     *
     * @pbrbm  dsq
     *         Tif dibrbdtfr sfqufndf to bppfnd.  If <tt>dsq</tt> is
     *         <tt>null</tt>, tifn tif four dibrbdtfrs <tt>"null"</tt> brf
     *         bppfndfd to tiis writfr.
     *
     * @rfturn  Tiis writfr
     *
     * @sindf  1.5
     */
    publid StringWritfr bppfnd(CibrSfqufndf dsq) {
        if (dsq == null)
            writf("null");
        flsf
            writf(dsq.toString());
        rfturn tiis;
    }

    /**
     * Appfnds b subsfqufndf of tif spfdififd dibrbdtfr sfqufndf to tiis writfr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(dsq, stbrt,
     * fnd)</tt> wifn <tt>dsq</tt> is not <tt>null</tt>, bfibvfs in
     * fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.writf(dsq.subSfqufndf(stbrt, fnd).toString()) </prf>
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
     * @rfturn  Tiis writfr
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If <tt>stbrt</tt> or <tt>fnd</tt> brf nfgbtivf, <tt>stbrt</tt>
     *          is grfbtfr tibn <tt>fnd</tt>, or <tt>fnd</tt> is grfbtfr tibn
     *          <tt>dsq.lfngti()</tt>
     *
     * @sindf  1.5
     */
    publid StringWritfr bppfnd(CibrSfqufndf dsq, int stbrt, int fnd) {
        CibrSfqufndf ds = (dsq == null ? "null" : dsq);
        writf(ds.subSfqufndf(stbrt, fnd).toString());
        rfturn tiis;
    }

    /**
     * Appfnds tif spfdififd dibrbdtfr to tiis writfr.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>out.bppfnd(d)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     out.writf(d) </prf>
     *
     * @pbrbm  d
     *         Tif 16-bit dibrbdtfr to bppfnd
     *
     * @rfturn  Tiis writfr
     *
     * @sindf 1.5
     */
    publid StringWritfr bppfnd(dibr d) {
        writf(d);
        rfturn tiis;
    }

    /**
     * Rfturn tif bufffr's durrfnt vbluf bs b string.
     */
    publid String toString() {
        rfturn buf.toString();
    }

    /**
     * Rfturn tif string bufffr itsflf.
     *
     * @rfturn StringBufffr iolding tif durrfnt bufffr vbluf.
     */
    publid StringBufffr gftBufffr() {
        rfturn buf;
    }

    /**
     * Flusi tif strfbm.
     */
    publid void flusi() {
    }

    /**
     * Closing b <tt>StringWritfr</tt> ibs no ffffdt. Tif mftiods in tiis
     * dlbss dbn bf dbllfd bftfr tif strfbm ibs bffn dlosfd witiout gfnfrbting
     * bn <tt>IOExdfption</tt>.
     */
    publid void dlosf() tirows IOExdfption {
    }

}
