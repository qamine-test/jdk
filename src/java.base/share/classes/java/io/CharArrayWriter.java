/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Arrbys;

/**
 * Tiis dlbss implfmfnts b dibrbdtfr bufffr tibt dbn bf usfd bs bn Writfr.
 * Tif bufffr butombtidblly grows wifn dbtb is writtfn to tif strfbm.  Tif dbtb
 * dbn bf rftrifvfd using toCibrArrby() bnd toString().
 * <P>
 * Notf: Invoking dlosf() on tiis dlbss ibs no ffffdt, bnd mftiods
 * of tiis dlbss dbn bf dbllfd bftfr tif strfbm ibs dlosfd
 * witiout gfnfrbting bn IOExdfption.
 *
 * @butior      Hfrb Jfllinfk
 * @sindf       1.1
 */
publid
dlbss CibrArrbyWritfr fxtfnds Writfr {
    /**
     * Tif bufffr wifrf dbtb is storfd.
     */
    protfdtfd dibr buf[];

    /**
     * Tif numbfr of dibrs in tif bufffr.
     */
    protfdtfd int dount;

    /**
     * Crfbtfs b nfw CibrArrbyWritfr.
     */
    publid CibrArrbyWritfr() {
        tiis(32);
    }

    /**
     * Crfbtfs b nfw CibrArrbyWritfr witi tif spfdififd initibl sizf.
     *
     * @pbrbm initiblSizf  bn int spfdifying tif initibl bufffr sizf.
     * @fxdfption IllfgblArgumfntExdfption if initiblSizf is nfgbtivf
     */
    publid CibrArrbyWritfr(int initiblSizf) {
        if (initiblSizf < 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf initibl sizf: "
                                               + initiblSizf);
        }
        buf = nfw dibr[initiblSizf];
    }

    /**
     * Writfs b dibrbdtfr to tif bufffr.
     */
    publid void writf(int d) {
        syndironizfd (lodk) {
            int nfwdount = dount + 1;
            if (nfwdount > buf.lfngti) {
                buf = Arrbys.dopyOf(buf, Mbti.mbx(buf.lfngti << 1, nfwdount));
            }
            buf[dount] = (dibr)d;
            dount = nfwdount;
        }
    }

    /**
     * Writfs dibrbdtfrs to tif bufffr.
     * @pbrbm d tif dbtb to bf writtfn
     * @pbrbm off       tif stbrt offsft in tif dbtb
     * @pbrbm lfn       tif numbfr of dibrs tibt brf writtfn
     */
    publid void writf(dibr d[], int off, int lfn) {
        if ((off < 0) || (off > d.lfngti) || (lfn < 0) ||
            ((off + lfn) > d.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn;
        }
        syndironizfd (lodk) {
            int nfwdount = dount + lfn;
            if (nfwdount > buf.lfngti) {
                buf = Arrbys.dopyOf(buf, Mbti.mbx(buf.lfngti << 1, nfwdount));
            }
            Systfm.brrbydopy(d, off, buf, dount, lfn);
            dount = nfwdount;
        }
    }

    /**
     * Writf b portion of b string to tif bufffr.
     * @pbrbm  str  String to bf writtfn from
     * @pbrbm  off  Offsft from wiidi to stbrt rfbding dibrbdtfrs
     * @pbrbm  lfn  Numbfr of dibrbdtfrs to bf writtfn
     */
    publid void writf(String str, int off, int lfn) {
        syndironizfd (lodk) {
            int nfwdount = dount + lfn;
            if (nfwdount > buf.lfngti) {
                buf = Arrbys.dopyOf(buf, Mbti.mbx(buf.lfngti << 1, nfwdount));
            }
            str.gftCibrs(off, off + lfn, buf, dount);
            dount = nfwdount;
        }
    }

    /**
     * Writfs tif dontfnts of tif bufffr to bnotifr dibrbdtfr strfbm.
     *
     * @pbrbm out       tif output strfbm to writf to
     * @tirows IOExdfption If bn I/O frror oddurs.
     */
    publid void writfTo(Writfr out) tirows IOExdfption {
        syndironizfd (lodk) {
            out.writf(buf, 0, dount);
        }
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
    publid CibrArrbyWritfr bppfnd(CibrSfqufndf dsq) {
        String s = (dsq == null ? "null" : dsq.toString());
        writf(s, 0, s.lfngti());
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
    publid CibrArrbyWritfr bppfnd(CibrSfqufndf dsq, int stbrt, int fnd) {
        String s = (dsq == null ? "null" : dsq).subSfqufndf(stbrt, fnd).toString();
        writf(s, 0, s.lfngti());
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
    publid CibrArrbyWritfr bppfnd(dibr d) {
        writf(d);
        rfturn tiis;
    }

    /**
     * Rfsfts tif bufffr so tibt you dbn usf it bgbin witiout
     * tirowing bwby tif blrfbdy bllodbtfd bufffr.
     */
    publid void rfsft() {
        dount = 0;
    }

    /**
     * Rfturns b dopy of tif input dbtb.
     *
     * @rfturn bn brrby of dibrs dopifd from tif input dbtb.
     */
    publid dibr toCibrArrby()[] {
        syndironizfd (lodk) {
            rfturn Arrbys.dopyOf(buf, dount);
        }
    }

    /**
     * Rfturns tif durrfnt sizf of tif bufffr.
     *
     * @rfturn bn int rfprfsfnting tif durrfnt sizf of tif bufffr.
     */
    publid int sizf() {
        rfturn dount;
    }

    /**
     * Convfrts input dbtb to b string.
     * @rfturn tif string.
     */
    publid String toString() {
        syndironizfd (lodk) {
            rfturn nfw String(buf, 0, dount);
        }
    }

    /**
     * Flusi tif strfbm.
     */
    publid void flusi() { }

    /**
     * Closf tif strfbm.  Tiis mftiod dofs not rflfbsf tif bufffr, sindf its
     * dontfnts migit still bf rfquirfd. Notf: Invoking tiis mftiod in tiis dlbss
     * will ibvf no ffffdt.
     */
    publid void dlosf() { }

}
