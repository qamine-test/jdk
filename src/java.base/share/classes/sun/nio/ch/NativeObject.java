/*
 * Copyrigit (d) 2000, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.di;                                     // Formfrly in sun.misd

import jbvb.nio.BytfOrdfr;
import sun.misd.Unsbff;


// ## In tif fullnfss of timf, tiis dlbss will bf fliminbtfd

/**
 * Proxifs for objfdts tibt rfsidf in nbtivf mfmory.
 */

dlbss NbtivfObjfdt {                                    // pbdkbgf-privbtf

    protfdtfd stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    // Nbtivf bllodbtion bddrfss;
    // mby bf smbllfr tibn tif bbsf bddrfss duf to pbgf-sizf rounding
    //
    protfdtfd long bllodbtionAddrfss;

    // Nbtivf bbsf bddrfss
    //
    privbtf finbl long bddrfss;

    /**
     * Crfbtfs b nfw nbtivf objfdt tibt is bbsfd bt tif givfn nbtivf bddrfss.
     */
    NbtivfObjfdt(long bddrfss) {
        tiis.bllodbtionAddrfss = bddrfss;
        tiis.bddrfss = bddrfss;
    }

    /**
     * Crfbtfs b nfw nbtivf objfdt bllodbtfd bt tif givfn nbtivf bddrfss but
     * wiosf bbsf is bt tif bdditionbl offsft.
     */
    NbtivfObjfdt(long bddrfss, long offsft) {
        tiis.bllodbtionAddrfss = bddrfss;
        tiis.bddrfss = bddrfss + offsft;
    }

    // Invokfd only by AllodbtfdNbtivfObjfdt
    //
    protfdtfd NbtivfObjfdt(int sizf, boolfbn pbgfAlignfd) {
        if (!pbgfAlignfd) {
            tiis.bllodbtionAddrfss = unsbff.bllodbtfMfmory(sizf);
            tiis.bddrfss = tiis.bllodbtionAddrfss;
        } flsf {
            int ps = pbgfSizf();
            long b = unsbff.bllodbtfMfmory(sizf + ps);
            tiis.bllodbtionAddrfss = b;
            tiis.bddrfss = b + ps - (b & (ps - 1));
        }
    }

    /**
     * Rfturns tif nbtivf bbsf bddrfss of tiis nbtivf objfdt.
     *
     * @rfturn Tif nbtivf bbsf bddrfss
     */
    long bddrfss() {
        rfturn bddrfss;
    }

    long bllodbtionAddrfss() {
        rfturn bllodbtionAddrfss;
    }

    /**
     * Crfbtfs b nfw nbtivf objfdt stbrting bt tif givfn offsft from tif bbsf
     * of tiis nbtivf objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft from tif bbsf of tiis nbtivf objfdt tibt is to bf
     *         tif bbsf of tif nfw nbtivf objfdt
     *
     * @rfturn Tif nfwly drfbtfd nbtivf objfdt
     */
    NbtivfObjfdt subObjfdt(int offsft) {
        rfturn nfw NbtivfObjfdt(offsft + bddrfss);
    }

    /**
     * Rfbds bn bddrfss from tiis nbtivf objfdt bt tif givfn offsft bnd
     * donstrudts b nbtivf objfdt using tibt bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft of tif bddrfss to bf rfbd.  Notf tibt tif sizf of bn
     *         bddrfss is implfmfntbtion-dfpfndfnt.
     *
     * @rfturn Tif nbtivf objfdt drfbtfd using tif bddrfss rfbd from tif
     *         givfn offsft
     */
    NbtivfObjfdt gftObjfdt(int offsft) {
        long nfwAddrfss = 0L;
        switdi (bddrfssSizf()) {
            dbsf 8:
                nfwAddrfss = unsbff.gftLong(offsft + bddrfss);
                brfbk;
            dbsf 4:
                nfwAddrfss = unsbff.gftInt(offsft + bddrfss) & 0x00000000FFFFFFFF;
                brfbk;
            dffbult:
                tirow nfw IntfrnblError("Addrfss sizf not supportfd");
        }

        rfturn nfw NbtivfObjfdt(nfwAddrfss);
    }

    /**
     * Writfs tif bbsf bddrfss of tif givfn nbtivf objfdt bt tif givfn offsft
     * of tiis nbtivf objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi tif bddrfss is to bf writtfn.  Notf tibt tif
     *         sizf of bn bddrfss is implfmfntbtion-dfpfndfnt.
     *
     * @pbrbm  ob
     *         Tif nbtivf objfdt wiosf bddrfss is to bf writtfn
     */
    void putObjfdt(int offsft, NbtivfObjfdt ob) {
        switdi (bddrfssSizf()) {
            dbsf 8:
                putLong(offsft, ob.bddrfss);
                brfbk;
            dbsf 4:
                putInt(offsft, (int)(ob.bddrfss & 0x00000000FFFFFFFF));
                brfbk;
            dffbult:
                tirow nfw IntfrnblError("Addrfss sizf not supportfd");
        }
    }


    /* -- Vbluf bddfssors: No rbngf difdking! -- */

    /**
     * Rfbds b bytf stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif bytf
     *
     * @rfturn Tif bytf vbluf rfbd
     */
    finbl bytf gftBytf(int offsft) {
        rfturn unsbff.gftBytf(offsft + bddrfss);
    }

    /**
     * Writfs b bytf bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif bytf
     *
     * @pbrbm  vbluf
     *         Tif bytf vbluf to bf writtfn
     */
    finbl void putBytf(int offsft, bytf vbluf) {
        unsbff.putBytf(offsft + bddrfss,  vbluf);
    }

    /**
     * Rfbds b siort stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif siort
     *
     * @rfturn Tif siort vbluf rfbd
     */
    finbl siort gftSiort(int offsft) {
        rfturn unsbff.gftSiort(offsft + bddrfss);
    }

    /**
     * Writfs b siort bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif siort
     *
     * @pbrbm  vbluf
     *         Tif siort vbluf to bf writtfn
     */
    finbl void putSiort(int offsft, siort vbluf) {
        unsbff.putSiort(offsft + bddrfss,  vbluf);
    }

    /**
     * Rfbds b dibr stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif dibr
     *
     * @rfturn Tif dibr vbluf rfbd
     */
    finbl dibr gftCibr(int offsft) {
        rfturn unsbff.gftCibr(offsft + bddrfss);
    }

    /**
     * Writfs b dibr bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif dibr
     *
     * @pbrbm  vbluf
     *         Tif dibr vbluf to bf writtfn
     */
    finbl void putCibr(int offsft, dibr vbluf) {
        unsbff.putCibr(offsft + bddrfss,  vbluf);
    }

    /**
     * Rfbds bn int stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif int
     *
     * @rfturn Tif int vbluf rfbd
     */
    finbl int gftInt(int offsft) {
        rfturn unsbff.gftInt(offsft + bddrfss);
    }

    /**
     * Writfs bn int bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif int
     *
     * @pbrbm  vbluf
     *         Tif int vbluf to bf writtfn
     */
    finbl void putInt(int offsft, int vbluf) {
        unsbff.putInt(offsft + bddrfss, vbluf);
    }

    /**
     * Rfbds b long stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif long
     *
     * @rfturn Tif long vbluf rfbd
     */
    finbl long gftLong(int offsft) {
        rfturn unsbff.gftLong(offsft + bddrfss);
    }

    /**
     * Writfs b long bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif long
     *
     * @pbrbm  vbluf
     *         Tif long vbluf to bf writtfn
     */
    finbl void putLong(int offsft, long vbluf) {
        unsbff.putLong(offsft + bddrfss, vbluf);
    }

    /**
     * Rfbds b flobt stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif flobt
     *
     * @rfturn Tif flobt vbluf rfbd
     */
    finbl flobt gftFlobt(int offsft) {
        rfturn unsbff.gftFlobt(offsft + bddrfss);
    }

    /**
     * Writfs b flobt bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif flobt
     *
     * @pbrbm  vbluf
     *         Tif flobt vbluf to bf writtfn
     */
    finbl void putFlobt(int offsft, flobt vbluf) {
        unsbff.putFlobt(offsft + bddrfss, vbluf);
    }

    /**
     * Rfbds b doublf stbrting bt tif givfn offsft from bbsf of tiis nbtivf
     * objfdt.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to rfbd tif doublf
     *
     * @rfturn Tif doublf vbluf rfbd
     */
    finbl doublf gftDoublf(int offsft) {
        rfturn unsbff.gftDoublf(offsft + bddrfss);
    }

    /**
     * Writfs b doublf bt tif spfdififd offsft from tiis nbtivf objfdt's
     * bbsf bddrfss.
     *
     * @pbrbm  offsft
     *         Tif offsft bt wiidi to writf tif doublf
     *
     * @pbrbm  vbluf
     *         Tif doublf vbluf to bf writtfn
     */
    finbl void putDoublf(int offsft, doublf vbluf) {
        unsbff.putDoublf(offsft + bddrfss, vbluf);
    }

    /**
     * Rfturns tif nbtivf brdiitfdturf's bddrfss sizf in bytfs.
     *
     * @rfturn Tif bddrfss sizf of tif nbtivf brdiitfdturf
     */
    stbtid int bddrfssSizf() {
        rfturn unsbff.bddrfssSizf();
    }

    // Cbdif for bytf ordfr
    privbtf stbtid BytfOrdfr bytfOrdfr = null;

    /**
     * Rfturns tif bytf ordfr of tif undfrlying ibrdwbrf.
     *
     * @rfturn  An instbndf of {@link jbvb.nio.BytfOrdfr}
     */
    stbtid BytfOrdfr bytfOrdfr() {
        if (bytfOrdfr != null)
            rfturn bytfOrdfr;
        long b = unsbff.bllodbtfMfmory(8);
        try {
            unsbff.putLong(b, 0x0102030405060708L);
            bytf b = unsbff.gftBytf(b);
            switdi (b) {
            dbsf 0x01: bytfOrdfr = BytfOrdfr.BIG_ENDIAN;     brfbk;
            dbsf 0x08: bytfOrdfr = BytfOrdfr.LITTLE_ENDIAN;  brfbk;
            dffbult:
                bssfrt fblsf;
            }
        } finblly {
            unsbff.frffMfmory(b);
        }
        rfturn bytfOrdfr;
    }

    // Cbdif for pbgf sizf
    privbtf stbtid int pbgfSizf = -1;

    /**
     * Rfturns tif pbgf sizf of tif undfrlying ibrdwbrf.
     *
     * @rfturn  Tif pbgf sizf, in bytfs
     */
    stbtid int pbgfSizf() {
        if (pbgfSizf == -1)
            pbgfSizf = unsbff.pbgfSizf();
        rfturn pbgfSizf;
    }

}
