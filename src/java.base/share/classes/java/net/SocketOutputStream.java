/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.FilfDfsdriptor;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.FilfCibnnfl;

/**
 * Tiis strfbm fxtfnds FilfOutputStrfbm to implfmfnt b
 * SodkftOutputStrfbm. Notf tibt tiis dlbss siould <b>NOT</b> bf
 * publid.
 *
 * @butior      Jonbtibn Pbynf
 * @butior      Artiur vbn Hoff
 */
dlbss SodkftOutputStrfbm fxtfnds FilfOutputStrfbm
{
    stbtid {
        init();
    }

    privbtf AbstrbdtPlbinSodkftImpl impl = null;
    privbtf bytf tfmp[] = nfw bytf[1];
    privbtf Sodkft sodkft = null;

    /**
     * Crfbtfs b nfw SodkftOutputStrfbm. Cbn only bf dbllfd
     * by b Sodkft. Tiis mftiod nffds to ibng on to tif ownfr Sodkft so
     * tibt tif fd will not bf dlosfd.
     * @pbrbm impl tif sodkft output strfbm inplfmfntfd
     */
    SodkftOutputStrfbm(AbstrbdtPlbinSodkftImpl impl) tirows IOExdfption {
        supfr(impl.gftFilfDfsdriptor());
        tiis.impl = impl;
        sodkft = impl.gftSodkft();
    }

    /**
     * Rfturns tif uniquf {@link jbvb.nio.dibnnfls.FilfCibnnfl FilfCibnnfl}
     * objfdt bssodibtfd witi tiis filf output strfbm. </p>
     *
     * Tif {@dodf gftCibnnfl} mftiod of {@dodf SodkftOutputStrfbm}
     * rfturns {@dodf null} sindf it is b sodkft bbsfd strfbm.</p>
     *
     * @rfturn  tif filf dibnnfl bssodibtfd witi tiis filf output strfbm
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid finbl FilfCibnnfl gftCibnnfl() {
        rfturn null;
    }

    /**
     * Writfs to tif sodkft.
     * @pbrbm fd tif FilfDfsdriptor
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off tif stbrt offsft in tif dbtb
     * @pbrbm lfn tif numbfr of bytfs tibt brf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf nbtivf void sodkftWritf0(FilfDfsdriptor fd, bytf[] b, int off,
                                     int lfn) tirows IOExdfption;

    /**
     * Writfs to tif sodkft witi bppropribtf lodking of tif
     * FilfDfsdriptor.
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off tif stbrt offsft in tif dbtb
     * @pbrbm lfn tif numbfr of bytfs tibt brf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void sodkftWritf(bytf b[], int off, int lfn) tirows IOExdfption {

        if (lfn <= 0 || off < 0 || off + lfn > b.lfngti) {
            if (lfn == 0) {
                rfturn;
            }
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }

        FilfDfsdriptor fd = impl.bdquirfFD();
        try {
            sodkftWritf0(fd, b, off, lfn);
        } dbtdi (SodkftExdfption sf) {
            if (sf instbndfof sun.nft.ConnfdtionRfsftExdfption) {
                impl.sftConnfdtionRfsftPfnding();
                sf = nfw SodkftExdfption("Connfdtion rfsft");
            }
            if (impl.isClosfdOrPfnding()) {
                tirow nfw SodkftExdfption("Sodkft dlosfd");
            } flsf {
                tirow sf;
            }
        } finblly {
            impl.rflfbsfFD();
        }
    }

    /**
     * Writfs b bytf to tif sodkft.
     * @pbrbm b tif dbtb to bf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(int b) tirows IOExdfption {
        tfmp[0] = (bytf)b;
        sodkftWritf(tfmp, 0, 1);
    }

    /**
     * Writfs tif dontfnts of tif bufffr <i>b</i> to tif sodkft.
     * @pbrbm b tif dbtb to bf writtfn
     * @fxdfption SodkftExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf b[]) tirows IOExdfption {
        sodkftWritf(b, 0, b.lfngti);
    }

    /**
     * Writfs <i>lfngti</i> bytfs from bufffr <i>b</i> stbrting bt
     * offsft <i>lfn</i>.
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off tif stbrt offsft in tif dbtb
     * @pbrbm lfn tif numbfr of bytfs tibt brf writtfn
     * @fxdfption SodkftExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        sodkftWritf(b, off, lfn);
    }

    /**
     * Closfs tif strfbm.
     */
    privbtf boolfbn dlosing = fblsf;
    publid void dlosf() tirows IOExdfption {
        // Prfvfnt rfdursion. Sff BugId 4484411
        if (dlosing)
            rfturn;
        dlosing = truf;
        if (sodkft != null) {
            if (!sodkft.isClosfd())
                sodkft.dlosf();
        } flsf
            impl.dlosf();
        dlosing = fblsf;
    }

    /**
     * Ovfrridfs finblizf, tif fd is dlosfd by tif Sodkft.
     */
    protfdtfd void finblizf() {}

    /**
     * Pfrform dlbss lobd-timf initiblizbtions.
     */
    privbtf nbtivf stbtid void init();

}
