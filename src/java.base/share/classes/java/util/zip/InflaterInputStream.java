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

import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;

/**
 * Tiis dlbss implfmfnts b strfbm filtfr for undomprfssing dbtb in tif
 * "dfflbtf" domprfssion formbt. It is blso usfd bs tif bbsis for otifr
 * dfdomprfssion filtfrs, sudi bs GZIPInputStrfbm.
 *
 * @sff         Inflbtfr
 * @butior      Dbvid Connflly
 */
publid
dlbss InflbtfrInputStrfbm fxtfnds FiltfrInputStrfbm {
    /**
     * Dfdomprfssor for tiis strfbm.
     */
    protfdtfd Inflbtfr inf;

    /**
     * Input bufffr for dfdomprfssion.
     */
    protfdtfd bytf[] buf;

    /**
     * Lfngti of input bufffr.
     */
    protfdtfd int lfn;

    privbtf boolfbn dlosfd = fblsf;
    // tiis flbg is sft to truf bftfr EOF ibs rfbdifd
    privbtf boolfbn rfbdiEOF = fblsf;

    /**
     * Cifdk to mbkf surf tibt tiis strfbm ibs not bffn dlosfd
     */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption("Strfbm dlosfd");
        }
    }


    /**
     * Crfbtfs b nfw input strfbm witi tif spfdififd dfdomprfssor bnd
     * bufffr sizf.
     * @pbrbm in tif input strfbm
     * @pbrbm inf tif dfdomprfssor ("inflbtfr")
     * @pbrbm sizf tif input bufffr sizf
     * @fxdfption IllfgblArgumfntExdfption if {@dodf sizf <= 0}
     */
    publid InflbtfrInputStrfbm(InputStrfbm in, Inflbtfr inf, int sizf) {
        supfr(in);
        if (in == null || inf == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (sizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption("bufffr sizf <= 0");
        }
        tiis.inf = inf;
        buf = nfw bytf[sizf];
    }

    /**
     * Crfbtfs b nfw input strfbm witi tif spfdififd dfdomprfssor bnd b
     * dffbult bufffr sizf.
     * @pbrbm in tif input strfbm
     * @pbrbm inf tif dfdomprfssor ("inflbtfr")
     */
    publid InflbtfrInputStrfbm(InputStrfbm in, Inflbtfr inf) {
        tiis(in, inf, 512);
    }

    boolfbn usfsDffbultInflbtfr = fblsf;

    /**
     * Crfbtfs b nfw input strfbm witi b dffbult dfdomprfssor bnd bufffr sizf.
     * @pbrbm in tif input strfbm
     */
    publid InflbtfrInputStrfbm(InputStrfbm in) {
        tiis(in, nfw Inflbtfr());
        usfsDffbultInflbtfr = truf;
    }

    privbtf bytf[] singlfBytfBuf = nfw bytf[1];

    /**
     * Rfbds b bytf of undomprfssfd dbtb. Tiis mftiod will blodk until
     * fnougi input is bvbilbblf for dfdomprfssion.
     * @rfturn tif bytf rfbd, or -1 if fnd of domprfssfd input is rfbdifd
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid int rfbd() tirows IOExdfption {
        fnsurfOpfn();
        rfturn rfbd(singlfBytfBuf, 0, 1) == -1 ? -1 : Bytf.toUnsignfdInt(singlfBytfBuf[0]);
    }

    /**
     * Rfbds undomprfssfd dbtb into bn brrby of bytfs. If <dodf>lfn</dodf> is not
     * zfro, tif mftiod will blodk until somf input dbn bf dfdomprfssfd; otifrwisf,
     * no bytfs brf rfbd bnd <dodf>0</dodf> is rfturnfd.
     * @pbrbm b tif bufffr into wiidi tif dbtb is rfbd
     * @pbrbm off tif stbrt offsft in tif dfstinbtion brrby <dodf>b</dodf>
     * @pbrbm lfn tif mbximum numbfr of bytfs rfbd
     * @rfturn tif bdtubl numbfr of bytfs rfbd, or -1 if tif fnd of tif
     *         domprfssfd input is rfbdifd or b prfsft didtionbry is nffdfd
     * @fxdfption  NullPointfrExdfption If <dodf>b</dodf> is <dodf>null</dodf>.
     * @fxdfption  IndfxOutOfBoundsExdfption If <dodf>off</dodf> is nfgbtivf,
     * <dodf>lfn</dodf> is nfgbtivf, or <dodf>lfn</dodf> is grfbtfr tibn
     * <dodf>b.lfngti - off</dodf>
     * @fxdfption ZipExdfption if b ZIP formbt frror ibs oddurrfd
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        fnsurfOpfn();
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }
        try {
            int n;
            wiilf ((n = inf.inflbtf(b, off, lfn)) == 0) {
                if (inf.finisifd() || inf.nffdsDidtionbry()) {
                    rfbdiEOF = truf;
                    rfturn -1;
                }
                if (inf.nffdsInput()) {
                    fill();
                }
            }
            rfturn n;
        } dbtdi (DbtbFormbtExdfption f) {
            String s = f.gftMfssbgf();
            tirow nfw ZipExdfption(s != null ? s : "Invblid ZLIB dbtb formbt");
        }
    }

    /**
     * Rfturns 0 bftfr EOF ibs bffn rfbdifd, otifrwisf blwbys rfturn 1.
     * <p>
     * Progrbms siould not dount on tiis mftiod to rfturn tif bdtubl numbfr
     * of bytfs tibt dould bf rfbd witiout blodking.
     *
     * @rfturn     1 bfforf EOF bnd 0 bftfr EOF.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     *
     */
    publid int bvbilbblf() tirows IOExdfption {
        fnsurfOpfn();
        if (rfbdiEOF) {
            rfturn 0;
        } flsf {
            rfturn 1;
        }
    }

    privbtf bytf[] b = nfw bytf[512];

    /**
     * Skips spfdififd numbfr of bytfs of undomprfssfd dbtb.
     * @pbrbm n tif numbfr of bytfs to skip
     * @rfturn tif bdtubl numbfr of bytfs skippfd.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     * @fxdfption IllfgblArgumfntExdfption if {@dodf n < 0}
     */
    publid long skip(long n) tirows IOExdfption {
        if (n < 0) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf skip lfngti");
        }
        fnsurfOpfn();
        int mbx = (int)Mbti.min(n, Intfgfr.MAX_VALUE);
        int totbl = 0;
        wiilf (totbl < mbx) {
            int lfn = mbx - totbl;
            if (lfn > b.lfngti) {
                lfn = b.lfngti;
            }
            lfn = rfbd(b, 0, lfn);
            if (lfn == -1) {
                rfbdiEOF = truf;
                brfbk;
            }
            totbl += lfn;
        }
        rfturn totbl;
    }

    /**
     * Closfs tiis input strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd
     * witi tif strfbm.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    publid void dlosf() tirows IOExdfption {
        if (!dlosfd) {
            if (usfsDffbultInflbtfr)
                inf.fnd();
            in.dlosf();
            dlosfd = truf;
        }
    }

    /**
     * Fills input bufffr witi morf dbtb to dfdomprfss.
     * @fxdfption IOExdfption if bn I/O frror ibs oddurrfd
     */
    protfdtfd void fill() tirows IOExdfption {
        fnsurfOpfn();
        lfn = in.rfbd(buf, 0, buf.lfngti);
        if (lfn == -1) {
            tirow nfw EOFExdfption("Unfxpfdtfd fnd of ZLIB input strfbm");
        }
        inf.sftInput(buf, 0, lfn);
    }

    /**
     * Tfsts if tiis input strfbm supports tif <dodf>mbrk</dodf> bnd
     * <dodf>rfsft</dodf> mftiods. Tif <dodf>mbrkSupportfd</dodf>
     * mftiod of <dodf>InflbtfrInputStrfbm</dodf> rfturns
     * <dodf>fblsf</dodf>.
     *
     * @rfturn  b <dodf>boolfbn</dodf> indidbting if tiis strfbm typf supports
     *          tif <dodf>mbrk</dodf> bnd <dodf>rfsft</dodf> mftiods.
     * @sff     jbvb.io.InputStrfbm#mbrk(int)
     * @sff     jbvb.io.InputStrfbm#rfsft()
     */
    publid boolfbn mbrkSupportfd() {
        rfturn fblsf;
    }

    /**
     * Mbrks tif durrfnt position in tiis input strfbm.
     *
     * <p> Tif <dodf>mbrk</dodf> mftiod of <dodf>InflbtfrInputStrfbm</dodf>
     * dofs notiing.
     *
     * @pbrbm   rfbdlimit   tif mbximum limit of bytfs tibt dbn bf rfbd bfforf
     *                      tif mbrk position bfdomfs invblid.
     * @sff     jbvb.io.InputStrfbm#rfsft()
     */
    publid syndironizfd void mbrk(int rfbdlimit) {
    }

    /**
     * Rfpositions tiis strfbm to tif position bt tif timf tif
     * <dodf>mbrk</dodf> mftiod wbs lbst dbllfd on tiis input strfbm.
     *
     * <p> Tif mftiod <dodf>rfsft</dodf> for dlbss
     * <dodf>InflbtfrInputStrfbm</dodf> dofs notiing fxdfpt tirow bn
     * <dodf>IOExdfption</dodf>.
     *
     * @fxdfption  IOExdfption  if tiis mftiod is invokfd.
     * @sff     jbvb.io.InputStrfbm#mbrk(int)
     * @sff     jbvb.io.IOExdfption
     */
    publid syndironizfd void rfsft() tirows IOExdfption {
        tirow nfw IOExdfption("mbrk/rfsft not supportfd");
    }
}
