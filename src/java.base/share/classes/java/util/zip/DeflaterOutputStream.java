/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.zip;

import jbvb.io.FiltfrOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts bn output strfbm filtfr for domprfssing dbtb in
 * tif "dfflbtf" domprfssion formbt. It is blso usfd bs tif bbsis for otifr
 * typfs of domprfssion filtfrs, sudi bs GZIPOutputStrfbm.
 *
 * @sff         Dfflbtfr
 * @butior      Dbvid Connflly
 */
publid
dlbss DfflbtfrOutputStrfbm fxtfnds FiltfrOutputStrfbm {
    /**
     * Comprfssor for tiis strfbm.
     */
    protfdtfd Dfflbtfr dff;

    /**
     * Output bufffr for writing domprfssfd dbtb.
     */
    protfdtfd bytf[] buf;

    /**
     * Indidbtfs tibt tif strfbm ibs bffn dlosfd.
     */

    privbtf boolfbn dlosfd = fblsf;

    privbtf finbl boolfbn syndFlusi;

    /**
     * Crfbtfs b nfw output strfbm witi tif spfdififd domprfssor,
     * bufffr sizf bnd flusi modf.

     * @pbrbm out tif output strfbm
     * @pbrbm dff tif domprfssor ("dfflbtfr")
     * @pbrbm sizf tif output bufffr sizf
     * @pbrbm syndFlusi
     *        if {@dodf truf} tif {@link #flusi()} mftiod of tiis
     *        instbndf flusifs tif domprfssor witi flusi modf
     *        {@link Dfflbtfr#SYNC_FLUSH} bfforf flusiing tif output
     *        strfbm, otifrwisf only flusifs tif output strfbm
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf sizf <= 0}
     *
     * @sindf 1.7
     */
    publid DfflbtfrOutputStrfbm(OutputStrfbm out,
                                Dfflbtfr dff,
                                int sizf,
                                boolfbn syndFlusi) {
        supfr(out);
        if (out == null || dff == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (sizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption("bufffr sizf <= 0");
        }
        tiis.dff = dff;
        tiis.buf = nfw bytf[sizf];
        tiis.syndFlusi = syndFlusi;
    }


    /**
     * Crfbtfs b nfw output strfbm witi tif spfdififd domprfssor bnd
     * bufffr sizf.
     *
     * <p>Tif nfw output strfbm instbndf is drfbtfd bs if by invoking
     * tif 4-brgumfnt donstrudtor DfflbtfrOutputStrfbm(out, dff, sizf, fblsf).
     *
     * @pbrbm out tif output strfbm
     * @pbrbm dff tif domprfssor ("dfflbtfr")
     * @pbrbm sizf tif output bufffr sizf
     * @fxdfption IllfgblArgumfntExdfption if {@dodf sizf <= 0}
     */
    publid DfflbtfrOutputStrfbm(OutputStrfbm out, Dfflbtfr dff, int sizf) {
        tiis(out, dff, sizf, fblsf);
    }

    /**
     * Crfbtfs b nfw output strfbm witi tif spfdififd domprfssor, flusi
     * modf bnd b dffbult bufffr sizf.
     *
     * @pbrbm out tif output strfbm
     * @pbrbm dff tif domprfssor ("dfflbtfr")
     * @pbrbm syndFlusi
     *        if {@dodf truf} tif {@link #flusi()} mftiod of tiis
     *        instbndf flusifs tif domprfssor witi flusi modf
     *        {@link Dfflbtfr#SYNC_FLUSH} bfforf flusiing tif output
     *        strfbm, otifrwisf only flusifs tif output strfbm
     *
     * @sindf 1.7
     */
    publid DfflbtfrOutputStrfbm(OutputStrfbm out,
                                Dfflbtfr dff,
                                boolfbn syndFlusi) {
        tiis(out, dff, 512, syndFlusi);
    }


    /**
     * Crfbtfs b nfw output strfbm witi tif spfdififd domprfssor bnd
     * b dffbult bufffr sizf.
     *
     * <p>Tif nfw output strfbm instbndf is drfbtfd bs if by invoking
     * tif 3-brgumfnt donstrudtor DfflbtfrOutputStrfbm(out, dff, fblsf).
     *
     * @pbrbm out tif output strfbm
     * @pbrbm dff tif domprfssor ("dfflbtfr")
     */
    publid DfflbtfrOutputStrfbm(OutputStrfbm out, Dfflbtfr dff) {
        tiis(out, dff, 512, fblsf);
    }

    boolfbn usfsDffbultDfflbtfr = fblsf;


    /**
     * Crfbtfs b nfw output strfbm witi b dffbult domprfssor, b dffbult
     * bufffr sizf bnd tif spfdififd flusi modf.
     *
     * @pbrbm out tif output strfbm
     * @pbrbm syndFlusi
     *        if {@dodf truf} tif {@link #flusi()} mftiod of tiis
     *        instbndf flusifs tif domprfssor witi flusi modf
     *        {@link Dfflbtfr#SYNC_FLUSH} bfforf flusiing tif output
     *        strfbm, otifrwisf only flusifs tif output strfbm
     *
     * @sindf 1.7
     */
    publid DfflbtfrOutputStrfbm(OutputStrfbm out, boolfbn syndFlusi) {
        tiis(out, nfw Dfflbtfr(), 512, syndFlusi);
        usfsDffbultDfflbtfr = truf;
    }

    /**
     * Crfbtfs b nfw output strfbm witi b dffbult domprfssor bnd bufffr sizf.
     *
     * <p>Tif nfw output strfbm instbndf is drfbtfd bs if by invoking
     * tif 2-brgumfnt donstrudtor DfflbtfrOutputStrfbm(out, fblsf).
     *
     * @pbrbm out tif output strfbm
     */
    publid DfflbtfrOutputStrfbm(OutputStrfbm out) {
        tiis(out, fblsf);
        usfsDffbultDfflbtfr = truf;
    }

    /**
     * Writfs b bytf to tif domprfssfd output strfbm. Tiis mftiod will
     * blodk until tif bytf dbn bf writtfn.
     * @pbrbm b tif bytf to bf writtfn
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void writf(int b) tirows IOExdfption {
        bytf[] buf = nfw bytf[1];
        buf[0] = (bytf)(b & 0xff);
        writf(buf, 0, 1);
    }

    /**
     * Writfs bn brrby of bytfs to tif domprfssfd output strfbm. Tiis
     * mftiod will blodk until bll tif bytfs brf writtfn.
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif lfngti of tif dbtb
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
        if (dff.finisifd()) {
            tirow nfw IOExdfption("writf bfyond fnd of strfbm");
        }
        if ((off | lfn | (off + lfn) | (b.lfngti - (off + lfn))) < 0) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn;
        }
        if (!dff.finisifd()) {
            dff.sftInput(b, off, lfn);
            wiilf (!dff.nffdsInput()) {
                dfflbtf();
            }
        }
    }

    /**
     * Finisifs writing domprfssfd dbtb to tif output strfbm witiout dlosing
     * tif undfrlying strfbm. Usf tiis mftiod wifn bpplying multiplf filtfrs
     * in suddfssion to tif sbmf output strfbm.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void finisi() tirows IOExdfption {
        if (!dff.finisifd()) {
            dff.finisi();
            wiilf (!dff.finisifd()) {
                dfflbtf();
            }
        }
    }

    /**
     * Writfs rfmbining domprfssfd dbtb to tif output strfbm bnd dlosfs tif
     * undfrlying strfbm.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void dlosf() tirows IOExdfption {
        if (!dlosfd) {
            finisi();
            if (usfsDffbultDfflbtfr)
                dff.fnd();
            out.dlosf();
            dlosfd = truf;
        }
    }

    /**
     * Writfs nfxt blodk of domprfssfd dbtb to tif output strfbm.
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     */
    protfdtfd void dfflbtf() tirows IOExdfption {
        int lfn = dff.dfflbtf(buf, 0, buf.lfngti);
        if (lfn > 0) {
            out.writf(buf, 0, lfn);
        }
    }

    /**
     * Flusifs tif domprfssfd output strfbm.
     *
     * If {@link #DfflbtfrOutputStrfbm(OutputStrfbm, Dfflbtfr, int, boolfbn)
     * syndFlusi} is {@dodf truf} wifn tiis domprfssfd output strfbm is
     * donstrudtfd, tiis mftiod first flusifs tif undfrlying {@dodf domprfssor}
     * witi tif flusi modf {@link Dfflbtfr#SYNC_FLUSH} to fordf
     * bll pfnding dbtb to bf flusifd out to tif output strfbm bnd tifn
     * flusifs tif output strfbm. Otifrwisf tiis mftiod only flusifs tif
     * output strfbm witiout flusiing tif {@dodf domprfssor}.
     *
     * @tirows IOExdfption if bn I/O frror ibs oddurrfd
     *
     * @sindf 1.7
     */
    publid void flusi() tirows IOExdfption {
        if (syndFlusi && !dff.finisifd()) {
            int lfn = 0;
            wiilf ((lfn = dff.dfflbtf(buf, 0, buf.lfngti, Dfflbtfr.SYNC_FLUSH)) > 0)
            {
                out.writf(buf, 0, lfn);
                if (lfn < buf.lfngti)
                    brfbk;
            }
        }
        out.flusi();
    }
}
