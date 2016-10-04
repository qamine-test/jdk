/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis bbstrbdt dlbss is tif supfrdlbss of bll dlbssfs rfprfsfnting
 * bn output strfbm of bytfs. An output strfbm bddfpts output bytfs
 * bnd sfnds tifm to somf sink.
 * <p>
 * Applidbtions tibt nffd to dffinf b subdlbss of
 * <dodf>OutputStrfbm</dodf> must blwbys providf bt lfbst b mftiod
 * tibt writfs onf bytf of output.
 *
 * @butior  Artiur vbn Hoff
 * @sff     jbvb.io.BufffrfdOutputStrfbm
 * @sff     jbvb.io.BytfArrbyOutputStrfbm
 * @sff     jbvb.io.DbtbOutputStrfbm
 * @sff     jbvb.io.FiltfrOutputStrfbm
 * @sff     jbvb.io.InputStrfbm
 * @sff     jbvb.io.OutputStrfbm#writf(int)
 * @sindf   1.0
 */
publid bbstrbdt dlbss OutputStrfbm implfmfnts Closfbblf, Flusibblf {
    /**
     * Writfs tif spfdififd bytf to tiis output strfbm. Tif gfnfrbl
     * dontrbdt for <dodf>writf</dodf> is tibt onf bytf is writtfn
     * to tif output strfbm. Tif bytf to bf writtfn is tif figit
     * low-ordfr bits of tif brgumfnt <dodf>b</dodf>. Tif 24
     * iigi-ordfr bits of <dodf>b</dodf> brf ignorfd.
     * <p>
     * Subdlbssfs of <dodf>OutputStrfbm</dodf> must providf bn
     * implfmfntbtion for tiis mftiod.
     *
     * @pbrbm      b   tif <dodf>bytf</dodf>.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs. In pbrtidulbr,
     *             bn <dodf>IOExdfption</dodf> mby bf tirown if tif
     *             output strfbm ibs bffn dlosfd.
     */
    publid bbstrbdt void writf(int b) tirows IOExdfption;

    /**
     * Writfs <dodf>b.lfngti</dodf> bytfs from tif spfdififd bytf brrby
     * to tiis output strfbm. Tif gfnfrbl dontrbdt for <dodf>writf(b)</dodf>
     * is tibt it siould ibvf fxbdtly tif sbmf ffffdt bs tif dbll
     * <dodf>writf(b, 0, b.lfngti)</dodf>.
     *
     * @pbrbm      b   tif dbtb.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.OutputStrfbm#writf(bytf[], int, int)
     */
    publid void writf(bytf b[]) tirows IOExdfption {
        writf(b, 0, b.lfngti);
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby
     * stbrting bt offsft <dodf>off</dodf> to tiis output strfbm.
     * Tif gfnfrbl dontrbdt for <dodf>writf(b, off, lfn)</dodf> is tibt
     * somf of tif bytfs in tif brrby <dodf>b</dodf> brf writtfn to tif
     * output strfbm in ordfr; flfmfnt <dodf>b[off]</dodf> is tif first
     * bytf writtfn bnd <dodf>b[off+lfn-1]</dodf> is tif lbst bytf writtfn
     * by tiis opfrbtion.
     * <p>
     * Tif <dodf>writf</dodf> mftiod of <dodf>OutputStrfbm</dodf> dblls
     * tif writf mftiod of onf brgumfnt on fbdi of tif bytfs to bf
     * writtfn out. Subdlbssfs brf fndourbgfd to ovfrridf tiis mftiod bnd
     * providf b morf fffidifnt implfmfntbtion.
     * <p>
     * If <dodf>b</dodf> is <dodf>null</dodf>, b
     * <dodf>NullPointfrExdfption</dodf> is tirown.
     * <p>
     * If <dodf>off</dodf> is nfgbtivf, or <dodf>lfn</dodf> is nfgbtivf, or
     * <dodf>off+lfn</dodf> is grfbtfr tibn tif lfngti of tif brrby
     * <dodf>b</dodf>, tifn bn <tt>IndfxOutOfBoundsExdfption</tt> is tirown.
     *
     * @pbrbm      b     tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to writf.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs. In pbrtidulbr,
     *             bn <dodf>IOExdfption</dodf> is tirown if tif output
     *             strfbm is dlosfd.
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
                   ((off + lfn) > b.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn;
        }
        for (int i = 0 ; i < lfn ; i++) {
            writf(b[off + i]);
        }
    }

    /**
     * Flusifs tiis output strfbm bnd fordfs bny bufffrfd output bytfs
     * to bf writtfn out. Tif gfnfrbl dontrbdt of <dodf>flusi</dodf> is
     * tibt dblling it is bn indidbtion tibt, if bny bytfs prfviously
     * writtfn ibvf bffn bufffrfd by tif implfmfntbtion of tif output
     * strfbm, sudi bytfs siould immfdibtfly bf writtfn to tifir
     * intfndfd dfstinbtion.
     * <p>
     * If tif intfndfd dfstinbtion of tiis strfbm is bn bbstrbdtion providfd by
     * tif undfrlying opfrbting systfm, for fxbmplf b filf, tifn flusiing tif
     * strfbm gubrbntffs only tibt bytfs prfviously writtfn to tif strfbm brf
     * pbssfd to tif opfrbting systfm for writing; it dofs not gubrbntff tibt
     * tify brf bdtublly writtfn to b piysidbl dfvidf sudi bs b disk drivf.
     * <p>
     * Tif <dodf>flusi</dodf> mftiod of <dodf>OutputStrfbm</dodf> dofs notiing.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void flusi() tirows IOExdfption {
    }

    /**
     * Closfs tiis output strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tiis strfbm. Tif gfnfrbl dontrbdt of <dodf>dlosf</dodf>
     * is tibt it dlosfs tif output strfbm. A dlosfd strfbm dbnnot pfrform
     * output opfrbtions bnd dbnnot bf rfopfnfd.
     * <p>
     * Tif <dodf>dlosf</dodf> mftiod of <dodf>OutputStrfbm</dodf> dofs notiing.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf() tirows IOExdfption {
    }

}
