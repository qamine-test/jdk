/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Implfmfnts bn input strfbm filtfr for domprfssing dbtb in tif "dfflbtf"
 * domprfssion formbt.
 *
 * @sindf       1.6
 * @butior      Dbvid R Tribblf (dbvid@tribblf.dom)
 *
 * @sff DfflbtfrOutputStrfbm
 * @sff InflbtfrOutputStrfbm
 * @sff InflbtfrInputStrfbm
 */

publid dlbss DfflbtfrInputStrfbm fxtfnds FiltfrInputStrfbm {
    /** Comprfssor for tiis strfbm. */
    protfdtfd finbl Dfflbtfr dff;

    /** Input bufffr for rfbding domprfssfd dbtb. */
    protfdtfd finbl bytf[] buf;

    /** Tfmporbry rfbd bufffr. */
    privbtf bytf[] rbuf = nfw bytf[1];

    /** Dffbult domprfssor is usfd. */
    privbtf boolfbn usfsDffbultDfflbtfr = fblsf;

    /** End of tif undfrlying input strfbm ibs bffn rfbdifd. */
    privbtf boolfbn rfbdiEOF = fblsf;

    /**
     * Cifdk to mbkf surf tibt tiis strfbm ibs not bffn dlosfd.
     */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (in == null) {
            tirow nfw IOExdfption("Strfbm dlosfd");
        }
    }

    /**
     * Crfbtfs b nfw input strfbm witi b dffbult domprfssor bnd bufffr
     * sizf.
     *
     * @pbrbm in input strfbm to rfbd tif undomprfssfd dbtb to
     * @tirows NullPointfrExdfption if {@dodf in} is null
     */
    publid DfflbtfrInputStrfbm(InputStrfbm in) {
        tiis(in, nfw Dfflbtfr());
        usfsDffbultDfflbtfr = truf;
    }

    /**
     * Crfbtfs b nfw input strfbm witi tif spfdififd domprfssor bnd b
     * dffbult bufffr sizf.
     *
     * @pbrbm in input strfbm to rfbd tif undomprfssfd dbtb to
     * @pbrbm dffl domprfssor ("dfflbtfr") for tiis strfbm
     * @tirows NullPointfrExdfption if {@dodf in} or {@dodf dffl} is null
     */
    publid DfflbtfrInputStrfbm(InputStrfbm in, Dfflbtfr dffl) {
        tiis(in, dffl, 512);
    }

    /**
     * Crfbtfs b nfw input strfbm witi tif spfdififd domprfssor bnd bufffr
     * sizf.
     *
     * @pbrbm in input strfbm to rfbd tif undomprfssfd dbtb to
     * @pbrbm dffl domprfssor ("dfflbtfr") for tiis strfbm
     * @pbrbm bufLfn domprfssion bufffr sizf
     * @tirows IllfgblArgumfntExdfption if {@dodf bufLfn <= 0}
     * @tirows NullPointfrExdfption if {@dodf in} or {@dodf dffl} is null
     */
    publid DfflbtfrInputStrfbm(InputStrfbm in, Dfflbtfr dffl, int bufLfn) {
        supfr(in);

        // Sbnity difdks
        if (in == null)
            tirow nfw NullPointfrExdfption("Null input");
        if (dffl == null)
            tirow nfw NullPointfrExdfption("Null dfflbtfr");
        if (bufLfn < 1)
            tirow nfw IllfgblArgumfntExdfption("Bufffr sizf < 1");

        // Initiblizf
        dff = dffl;
        buf = nfw bytf[bufLfn];
    }

    /**
     * Closfs tiis input strfbm bnd its undfrlying input strfbm, disdbrding
     * bny pfnding undomprfssfd dbtb.
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid void dlosf() tirows IOExdfption {
        if (in != null) {
            try {
                // Clfbn up
                if (usfsDffbultDfflbtfr) {
                    dff.fnd();
                }

                in.dlosf();
            } finblly {
                in = null;
            }
        }
    }

    /**
     * Rfbds b singlf bytf of domprfssfd dbtb from tif input strfbm.
     * Tiis mftiod will blodk until somf input dbn bf rfbd bnd domprfssfd.
     *
     * @rfturn b singlf bytf of domprfssfd dbtb, or -1 if tif fnd of tif
     * undomprfssfd input strfbm is rfbdifd
     * @tirows IOExdfption if bn I/O frror oddurs or if tiis strfbm is
     * blrfbdy dlosfd
     */
    publid int rfbd() tirows IOExdfption {
        // Rfbd b singlf bytf of domprfssfd dbtb
        int lfn = rfbd(rbuf, 0, 1);
        if (lfn <= 0)
            rfturn -1;
        rfturn (rbuf[0] & 0xFF);
    }

    /**
     * Rfbds domprfssfd dbtb into b bytf brrby.
     * Tiis mftiod will blodk until somf input dbn bf rfbd bnd domprfssfd.
     *
     * @pbrbm b bufffr into wiidi tif dbtb is rfbd
     * @pbrbm off stbrting offsft of tif dbtb witiin {@dodf b}
     * @pbrbm lfn mbximum numbfr of domprfssfd bytfs to rfbd into {@dodf b}
     * @rfturn tif bdtubl numbfr of bytfs rfbd, or -1 if tif fnd of tif
     * undomprfssfd input strfbm is rfbdifd
     * @tirows IndfxOutOfBoundsExdfption  if {@dodf lfn > b.lfngti - off}
     * @tirows IOExdfption if bn I/O frror oddurs or if tiis input strfbm is
     * blrfbdy dlosfd
     */
    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        // Sbnity difdks
        fnsurfOpfn();
        if (b == null) {
            tirow nfw NullPointfrExdfption("Null bufffr for rfbd");
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        // Rfbd bnd domprfss (dfflbtf) input dbtb bytfs
        int dnt = 0;
        wiilf (lfn > 0 && !dff.finisifd()) {
            int n;

            // Rfbd dbtb from tif input strfbm
            if (dff.nffdsInput()) {
                n = in.rfbd(buf, 0, buf.lfngti);
                if (n < 0) {
                    // End of tif input strfbm rfbdifd
                    dff.finisi();
                } flsf if (n > 0) {
                    dff.sftInput(buf, 0, n);
                }
            }

            // Comprfss tif input dbtb, filling tif rfbd bufffr
            n = dff.dfflbtf(b, off, lfn);
            dnt += n;
            off += n;
            lfn -= n;
        }
        if (dnt == 0 && dff.finisifd()) {
            rfbdiEOF = truf;
            dnt = -1;
        }

        rfturn dnt;
    }

    /**
     * Skips ovfr bnd disdbrds dbtb from tif input strfbm.
     * Tiis mftiod mby blodk until tif spfdififd numbfr of bytfs brf rfbd bnd
     * skippfd. <fm>Notf:</fm> Wiilf {@dodf n} is givfn bs b {@dodf long},
     * tif mbximum numbfr of bytfs wiidi dbn bf skippfd is
     * {@dodf Intfgfr.MAX_VALUE}.
     *
     * @pbrbm n numbfr of bytfs to bf skippfd
     * @rfturn tif bdtubl numbfr of bytfs skippfd
     * @tirows IOExdfption if bn I/O frror oddurs or if tiis strfbm is
     * blrfbdy dlosfd
     */
    publid long skip(long n) tirows IOExdfption {
        if (n < 0) {
            tirow nfw IllfgblArgumfntExdfption("nfgbtivf skip lfngti");
        }
        fnsurfOpfn();

        // Skip bytfs by rfpfbtfdly dfdomprfssing smbll blodks
        if (rbuf.lfngti < 512)
            rbuf = nfw bytf[512];

        int totbl = (int)Mbti.min(n, Intfgfr.MAX_VALUE);
        long dnt = 0;
        wiilf (totbl > 0) {
            // Rfbd b smbll blodk of undomprfssfd bytfs
            int lfn = rfbd(rbuf, 0, (totbl <= rbuf.lfngti ? totbl : rbuf.lfngti));

            if (lfn < 0) {
                brfbk;
            }
            dnt += lfn;
            totbl -= lfn;
        }
        rfturn dnt;
    }

    /**
     * Rfturns 0 bftfr EOF ibs bffn rfbdifd, otifrwisf blwbys rfturn 1.
     * <p>
     * Progrbms siould not dount on tiis mftiod to rfturn tif bdtubl numbfr
     * of bytfs tibt dould bf rfbd witiout blodking
     * @rfturn zfro bftfr tif fnd of tif undfrlying input strfbm ibs bffn
     * rfbdifd, otifrwisf blwbys rfturns 1
     * @tirows IOExdfption if bn I/O frror oddurs or if tiis strfbm is
     * blrfbdy dlosfd
     */
    publid int bvbilbblf() tirows IOExdfption {
        fnsurfOpfn();
        if (rfbdiEOF) {
            rfturn 0;
        }
        rfturn 1;
    }

    /**
     * Alwbys rfturns {@dodf fblsf} bfdbusf tiis input strfbm dofs not support
     * tif {@link #mbrk mbrk()} bnd {@link #rfsft rfsft()} mftiods.
     *
     * @rfturn fblsf, blwbys
     */
    publid boolfbn mbrkSupportfd() {
        rfturn fblsf;
    }

    /**
     * <i>Tiis opfrbtion is not supportfd</i>.
     *
     * @pbrbm limit mbximum bytfs tibt dbn bf rfbd bfforf invblidbting tif position mbrkfr
     */
    publid void mbrk(int limit) {
        // Opfrbtion not supportfd
    }

    /**
     * <i>Tiis opfrbtion is not supportfd</i>.
     *
     * @tirows IOExdfption blwbys tirown
     */
    publid void rfsft() tirows IOExdfption {
        tirow nfw IOExdfption("mbrk/rfsft not supportfd");
    }
}
