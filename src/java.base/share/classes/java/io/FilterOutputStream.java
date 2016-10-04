/*
 * Copyrigit (d) 1994, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss is tif supfrdlbss of bll dlbssfs tibt filtfr output
 * strfbms. Tifsf strfbms sit on top of bn blrfbdy fxisting output
 * strfbm (tif <i>undfrlying</i> output strfbm) wiidi it usfs bs its
 * bbsid sink of dbtb, but possibly trbnsforming tif dbtb blong tif
 * wby or providing bdditionbl fundtionblity.
 * <p>
 * Tif dlbss <dodf>FiltfrOutputStrfbm</dodf> itsflf simply ovfrridfs
 * bll mftiods of <dodf>OutputStrfbm</dodf> witi vfrsions tibt pbss
 * bll rfqufsts to tif undfrlying output strfbm. Subdlbssfs of
 * <dodf>FiltfrOutputStrfbm</dodf> mby furtifr ovfrridf somf of tifsf
 * mftiods bs wfll bs providf bdditionbl mftiods bnd fiflds.
 *
 * @butior  Jonbtibn Pbynf
 * @sindf   1.0
 */
publid
dlbss FiltfrOutputStrfbm fxtfnds OutputStrfbm {
    /**
     * Tif undfrlying output strfbm to bf filtfrfd.
     */
    protfdtfd OutputStrfbm out;

    /**
     * Crfbtfs bn output strfbm filtfr built on top of tif spfdififd
     * undfrlying output strfbm.
     *
     * @pbrbm   out   tif undfrlying output strfbm to bf bssignfd to
     *                tif fifld <tt>tiis.out</tt> for lbtfr usf, or
     *                <dodf>null</dodf> if tiis instbndf is to bf
     *                drfbtfd witiout bn undfrlying strfbm.
     */
    publid FiltfrOutputStrfbm(OutputStrfbm out) {
        tiis.out = out;
    }

    /**
     * Writfs tif spfdififd <dodf>bytf</dodf> to tiis output strfbm.
     * <p>
     * Tif <dodf>writf</dodf> mftiod of <dodf>FiltfrOutputStrfbm</dodf>
     * dblls tif <dodf>writf</dodf> mftiod of its undfrlying output strfbm,
     * tibt is, it pfrforms <tt>out.writf(b)</tt>.
     * <p>
     * Implfmfnts tif bbstrbdt <tt>writf</tt> mftiod of <tt>OutputStrfbm</tt>.
     *
     * @pbrbm      b   tif <dodf>bytf</dodf>.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void writf(int b) tirows IOExdfption {
        out.writf(b);
    }

    /**
     * Writfs <dodf>b.lfngti</dodf> bytfs to tiis output strfbm.
     * <p>
     * Tif <dodf>writf</dodf> mftiod of <dodf>FiltfrOutputStrfbm</dodf>
     * dblls its <dodf>writf</dodf> mftiod of tirff brgumfnts witi tif
     * brgumfnts <dodf>b</dodf>, <dodf>0</dodf>, bnd
     * <dodf>b.lfngti</dodf>.
     * <p>
     * Notf tibt tiis mftiod dofs not dbll tif onf-brgumfnt
     * <dodf>writf</dodf> mftiod of its undfrlying strfbm witi tif singlf
     * brgumfnt <dodf>b</dodf>.
     *
     * @pbrbm      b   tif dbtb to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#writf(bytf[], int, int)
     */
    publid void writf(bytf b[]) tirows IOExdfption {
        writf(b, 0, b.lfngti);
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd
     * <dodf>bytf</dodf> brrby stbrting bt offsft <dodf>off</dodf> to
     * tiis output strfbm.
     * <p>
     * Tif <dodf>writf</dodf> mftiod of <dodf>FiltfrOutputStrfbm</dodf>
     * dblls tif <dodf>writf</dodf> mftiod of onf brgumfnt on fbdi
     * <dodf>bytf</dodf> to output.
     * <p>
     * Notf tibt tiis mftiod dofs not dbll tif <dodf>writf</dodf> mftiod
     * of its undfrlying input strfbm witi tif sbmf brgumfnts. Subdlbssfs
     * of <dodf>FiltfrOutputStrfbm</dodf> siould providf b morf fffidifnt
     * implfmfntbtion of tiis mftiod.
     *
     * @pbrbm      b     tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to writf.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#writf(int)
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        if ((off | lfn | (b.lfngti - (lfn + off)) | (off + lfn)) < 0)
            tirow nfw IndfxOutOfBoundsExdfption();

        for (int i = 0 ; i < lfn ; i++) {
            writf(b[off + i]);
        }
    }

    /**
     * Flusifs tiis output strfbm bnd fordfs bny bufffrfd output bytfs
     * to bf writtfn out to tif strfbm.
     * <p>
     * Tif <dodf>flusi</dodf> mftiod of <dodf>FiltfrOutputStrfbm</dodf>
     * dblls tif <dodf>flusi</dodf> mftiod of its undfrlying output strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    publid void flusi() tirows IOExdfption {
        out.flusi();
    }

    /**
     * Closfs tiis output strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tif strfbm.
     * <p>
     * Tif <dodf>dlosf</dodf> mftiod of <dodf>FiltfrOutputStrfbm</dodf>
     * dblls its <dodf>flusi</dodf> mftiod, bnd tifn dblls tif
     * <dodf>dlosf</dodf> mftiod of its undfrlying output strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrOutputStrfbm#flusi()
     * @sff        jbvb.io.FiltfrOutputStrfbm#out
     */
    @SupprfssWbrnings("try")
    publid void dlosf() tirows IOExdfption {
        try (OutputStrfbm ostrfbm = out) {
            flusi();
        }
    }
}
